package com.mobimvp.privacybox.ui.applock;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.app.ActionBar;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mobimvp.privacybox.Constants;
import com.mobimvp.privacybox.PBApplication;
import com.mobimvp.privacybox.R;
import com.mobimvp.privacybox.ui.BaseActivity;
import com.mobimvp.privacybox.ui.widgets.GridViewEx;
import com.mobimvp.privacybox.utility.APKFile.APKFileWithLockStatus;
import com.mobimvp.privacybox.utility.AsyncLoader;
import com.mobimvp.privacybox.utility.LockAppListUtil;

public class LockAppActivity extends BaseActivity   {
	
	private static class InstalledAppLoader extends AsyncLoader<LockAppActivity, ArrayList<APKFileWithLockStatus>, Integer> {
		@Override
		protected ArrayList<APKFileWithLockStatus> doInBackground() {
			LockAppActivity activity = getActivity();
			ArrayList<APKFileWithLockStatus> result = new ArrayList<APKFileWithLockStatus>();
			PackageManager pm = activity.getPackageManager();
			try {
				List<PackageInfo> pkgList = pm.getInstalledPackages(PackageManager.GET_UNINSTALLED_PACKAGES);
				LockAppListUtil lockSet = LockAppListUtil.getInstance();
				if (pkgList != null) {
					for (PackageInfo pkg : pkgList) {
						//如果存在入口Intent则不为空，如果为空表示不存在入口Intent,无需加密
						if(pm.getLaunchIntentForPackage(pkg.packageName)!=null){
							try {
								if(pkg.packageName.equals(activity.getPackageName()) || pkg.packageName.equals(Constants.PACKAGE_NAME))
									continue;
								if(lockSet.isInLockAppSet(pkg.packageName)){
									result.add(new APKFileWithLockStatus(pkg,APKFileWithLockStatus.APK_LOCKSTATUS_LOCK));
								}else result.add(new APKFileWithLockStatus(pkg,APKFileWithLockStatus.APK_LOCKSTATUS_UNLOCK));
							} catch (Exception e) {
							}
						}
					}
				}
			} catch (Exception e) {
			}
			Collections.sort(result, new APPLOCK_COMPARATOR<APKFileWithLockStatus>());
			return result;
		}

		@Override
		protected void onPostExecute(ArrayList<APKFileWithLockStatus> loadResult) {
			super.onPostExecute(loadResult);
			LockAppActivity activity = getActivity();
			activity.gridView.hideLoadingScreen();
			activity.packageList.clear();
			activity.packageList.addAll(loadResult);
			activity.gridAdapter.notifyDataSetChanged();
			activity.registerPackageReceiver();
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			LockAppActivity activity = getActivity();
			activity.gridView.showLoadingScreen(getActivity().getString(R.string.generic_loading));
		}

		@Override
		protected ArrayList<APKFileWithLockStatus> onSyncWithActivity(ArrayList<APKFileWithLockStatus> result) {
			return new ArrayList<APKFileWithLockStatus>(result);
		}
	}
	private static class APPLOCK_COMPARATOR<T extends APKFileWithLockStatus> implements Comparator<T>{
		@Override
		public int compare(APKFileWithLockStatus object1, APKFileWithLockStatus object2) {
			int t = object1.getLockstatus() - object2.getLockstatus();
			if(t!=0){
				return t;
			}else {
				Collator collator = Collator.getInstance();
				return collator.getCollationKey(object1.getLabel().toString()).compareTo(collator.getCollationKey(object2.getLabel().toString()));
			}
		}
		
	}

	private class MyAppGridAdapter extends BaseAdapter implements OnItemClickListener{

		@Override
		public int getCount() {
			return packageList.size();
		}

		@Override
		public APKFileWithLockStatus getItem(int arg0) {
			return packageList.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {
			return arg0;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			if(convertView == null){
				convertView = LayoutInflater.from(PBApplication.getApplication()).inflate(R.layout.lockapplist_item,null);
			}
			ImageView icon = (ImageView)convertView.findViewById(R.id.ItemImage);
			TextView pkgname = (TextView)convertView.findViewById(R.id.ItemText);
			RelativeLayout relativelayout = (RelativeLayout)convertView.findViewById(R.id.relativelayout);
			APKFileWithLockStatus appfile = getItem(position);
			if(appfile.isLock()){
				pkgname.setTextColor(Color.GRAY);
				pkgname.setText(appfile.getLabel());
				relativelayout.setBackgroundResource(R.drawable.grid_applockedselector_background);
				ImageView triangle = (ImageView)convertView.findViewById(R.id.ItemIamgeTriangle);
				triangle.setImageResource(R.drawable.ic_triangle);
				triangle.setVisibility(View.VISIBLE);
			}else{
				pkgname.setText(appfile.getLabel());
				relativelayout.setBackgroundResource(R.drawable.grid_appselector_background);
				ImageView triangle = (ImageView)convertView.findViewById(R.id.ItemIamgeTriangle);
				triangle.setVisibility(View.GONE);
			}
			icon.setImageDrawable(appfile.getIcon());
			return convertView;
		}

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			APKFileWithLockStatus appfile = packageList.get(arg2);
			appfile.setLock(!appfile.isLock());
			Collections.sort(packageList,new APPLOCK_COMPARATOR<APKFileWithLockStatus>());
			LockAppListUtil.getInstance().lockPackage(appfile.getPackageName(), appfile.isLock());
			this.notifyDataSetChanged();
		}

	}

	private BroadcastReceiver packageReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			loader = new InstalledAppLoader();
			loader.execute(LockAppActivity.this);
		}
	};



	private ArrayList<APKFileWithLockStatus> packageList;
	private GridViewEx gridView;
	private MyAppGridAdapter gridAdapter;

	private InstalledAppLoader loader;
	private boolean receiverRegistered;
	private ActionBar mActionBar;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mActionBar=getPrivacyActionBar();
		mActionBar.setTitle(getString(R.string.Privacy_lock_MainName));
		gridView = new GridViewEx(this);
		gridView.setEmptyText(getString(R.string.Privacy_Lock__None));
		packageList = new ArrayList<APKFileWithLockStatus>();
		gridAdapter = new MyAppGridAdapter();
		gridView.setAdapter(gridAdapter);
		gridView.setOnItemClickListener(gridAdapter);
		if (loader == null) {
			loader = new InstalledAppLoader();
		}
		setContentView(gridView);
	}

	protected void onResume() {
		super.onResume();
		loader.execute(this);
		gridView.setDefaultColumn();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		LockAppListUtil.getInstance().InsertLockApp();
		unregisterPackageReceiver();
	}
	
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterPackageReceiver();
		
	}
	
	private void registerPackageReceiver() {
		if (!receiverRegistered) {
			IntentFilter defaultFilter = new IntentFilter();
			defaultFilter.addAction(Intent.ACTION_PACKAGE_ADDED);
			defaultFilter.addAction(Intent.ACTION_PACKAGE_REMOVED);
			defaultFilter.addAction(Intent.ACTION_PACKAGE_REPLACED);
			defaultFilter.addDataScheme("package");
			registerReceiver(packageReceiver, defaultFilter);
			receiverRegistered = true;
		}
	}

	private void unregisterPackageReceiver() {
		if (receiverRegistered) {
			unregisterReceiver(packageReceiver);
			receiverRegistered = false;
		}
	}
		
}
