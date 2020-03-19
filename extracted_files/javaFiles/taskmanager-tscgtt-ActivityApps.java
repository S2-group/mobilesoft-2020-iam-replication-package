package com.ggstudios.tools.taskmanager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.view.ActionMode;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

public class ActivityApps extends SherlockFragment implements OnSharedPreferenceChangeListener {
	private static final String TAG = "ActivityApps";

	private static final int MSG_RELOAD_ICONS = 1, MSG_DONE_LOADING_APPS = 2;
	private static final int REQUEST_UNINSTALL = 1;
	private static final int SORT_BY_NOTHING = 0, SORT_BY_NAME = 1;

	private View rootView;
	private ListView lvApps;
	private PackageManager packageMgr;
	private List<ApplicationInfo> appInfoList;
	private ApplicationListAdapter adapter;
	private ActionBar actionBar;
	private ActionMode actionMode;
	private HashMap<String, ApplicationItem> cached;
	private ProgressBar progressBar;
	private SharedPreferences prefs;

	SparseBooleanArray checkedItems = new SparseBooleanArray();

	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			switch(msg.what){
			case MSG_RELOAD_ICONS:
				hideProgressBar();
				adapter.updateIcons((HashMap<String, Drawable>) msg.obj);
				break;
			case MSG_DONE_LOADING_APPS:
				hideProgressBar();
				adapter.updateApps((List<ApplicationItem>) msg.obj);
				break;
			default:
				break;
			}
		}
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.activity_apps, container, false);

		prefs = Preferences.getInstance().getPrefs();
		
		lvApps = (ListView) rootView.findViewById(R.id.lvApps);
		progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
		
		List<ApplicationItem> appList = new ArrayList<ApplicationItem>();
		cached = new HashMap<String, ApplicationItem>();
		adapter = new ApplicationListAdapter(getActivity(), appList);
		lvApps.setAdapter(adapter);
		lvApps.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		lvApps.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				if(lvApps.isItemChecked(position)){
					checkedItems.append(position, true);
				} else {
					checkedItems.delete(position);
				}
				lvApps.setItemChecked(position, lvApps.isItemChecked(position));

				if (checkedItems.size() != 0) {
					if (actionMode == null) {
						actionMode = getSherlockActivity().startActionMode(actionModeCallback);
					}
				} else {
					if (actionMode != null) {
						actionMode.finish();
					}
				}
			}

		});
		
		packageMgr = getActivity().getPackageManager();

		refreshAppList();
		prefs.registerOnSharedPreferenceChangeListener(this);

		return rootView;
	}
	
	@Override
	public void onDestroy(){
		super.onDestroy();

		prefs.unregisterOnSharedPreferenceChangeListener(this);
	}

	private void refreshAppList() {
		getActivity().runOnUiThread(new Runnable(){

			@Override
			public void run() {
				showProgressBar();
			}
			
		});
		new Thread(){
			public void run(){
				Drawable defaultIcon = getResources().getDrawable(android.R.drawable.sym_def_app_icon);

				//get a list of installed apps.
				//appInfoList = packageMgr.getInstalledApplications(PackageManager.GET_META_DATA);
				appInfoList = packageMgr.getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);

				List<ApplicationItem> items = new ArrayList<ApplicationItem>();

				boolean hideSystemApps = prefs.getBoolean(Keys.PREF_HIDE_SYSTEM_APPS, false);
				
				for (ApplicationInfo appInfo : appInfoList) {
					final ApplicationItem item = new ApplicationItem();
					item.icon = defaultIcon; //packageMgr.getApplicationIcon(packageInfo);
					item.appName = packageMgr.getApplicationLabel(appInfo).toString();
					item.pkgName = appInfo.packageName;

					if ((appInfo.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0) {
						// Updated system app
						item.appType = ApplicationItem.TYPE_SYSTEM_UPDATED;
					} else if ((appInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
						item.appType = ApplicationItem.TYPE_NORMAL;
					} else {
						item.appType = ApplicationItem.TYPE_SYSTEM;
					}

					if(item.appType != ApplicationItem.TYPE_SYSTEM || !hideSystemApps) {
						cached.put(appInfo.packageName, item);
						items.add(item);
					}
				}
				
				int sortPref = Integer.parseInt(prefs.getString(Keys.PREF_SORT_APPS_BY, "0"));
				if (sortPref == SORT_BY_NAME) {
					Collections.sort(items, new Comparator<ApplicationItem>(){

						@Override
						public int compare(ApplicationItem lhs,
								ApplicationItem rhs) {
							return lhs.appName.toLowerCase(Locale.US).compareTo(rhs.appName.toLowerCase(Locale.US));
						}
						
					});
				}

				Message msg = handler.obtainMessage(MSG_DONE_LOADING_APPS);
				msg.obj = items;
				handler.sendMessage(msg);

				int appCount = appInfoList.size();

				Map<String, Drawable> map = new HashMap<String, Drawable>();
				for (int i = (appCount - 1); i >= 0; i--) {
					ApplicationInfo appInfo = appInfoList.get(i);
					map.put(appInfo.packageName, appInfo.loadIcon(packageMgr));
				}

				msg = handler.obtainMessage(MSG_RELOAD_ICONS);
				msg.obj = map;
				handler.sendMessage(msg);
			}
		}.start();
	}
	
	private void hideProgressBar(){
		progressBar.setVisibility(View.INVISIBLE);
	}
	
	private void showProgressBar(){
		progressBar.setVisibility(View.VISIBLE);
	}

	private ActionMode.Callback actionModeCallback = new ActionMode.Callback() {

		// Called when the action mode is created; startActionMode() was called
		@Override
		public boolean onCreateActionMode(ActionMode mode, Menu menu) {
			// Inflate a menu resource providing context menu items
			MenuInflater inflater = mode.getMenuInflater();
			inflater.inflate(R.menu.activity_applications_context_menu, menu);
			return true;
		}

		// Called each time the action mode is shown. Always called after onCreateActionMode, but
		// may be called multiple times if the mode is invalidated.
		@Override
		public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
			return false; // Return false if nothing is done
		}

		// Called when the user selects a contextual menu item
		@Override
		public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
			switch (item.getItemId()) {
			case R.id.item_delete:
				for (int i = 0 ; i < checkedItems.size(); i++) {
					Intent intent = new Intent(Intent.ACTION_DELETE);
					intent.setData(Uri.parse("package:" + adapter.appList.get(checkedItems.keyAt(i)).pkgName));
					startActivityForResult(intent, REQUEST_UNINSTALL);
				}

				if (actionMode != null) {
					actionMode.finish();
				}
				return true;
			default:
				return false;
			}
		}

		// Called when the user exits the action mode
		@Override
		public void onDestroyActionMode(ActionMode mode) {
			actionMode = null;

			lvApps.clearChoices();
			checkedItems.clear();
			adapter.notifyDataSetChanged();
		}
	};

	@Override
	public void onActivityResult (int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_UNINSTALL) {
			refreshAppList();
		}
	}
	
	private class ViewHolder {
		ImageView icon;
		TextView txtAppName;
		TextView txtDetails;
	}

	private class ApplicationItem {
		public static final int
		TYPE_SYSTEM = 1, TYPE_SYSTEM_UPDATED = 2, TYPE_NORMAL = 0;

		Drawable icon;
		String appName;
		String pkgName;
		int appType = 0;

		public void refreshIcon(Drawable ic) {
			icon = ic;
		}
	}

	private class ApplicationListAdapter extends BaseAdapter implements ListAdapter {
		List<ApplicationItem> appList;
		LayoutInflater inflater;

		ApplicationListAdapter(Context con, List<ApplicationItem> list){
			this.appList = list;
			inflater = (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		@Override
		public int getCount() {
			return appList.size();
		}

		@Override
		public Object getItem(int index) {
			return appList.get(index);
		}

		@Override
		public long getItemId(int index) {
			return index;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			ApplicationItem item = appList.get(position);

			if(convertView == null){
				convertView = inflater.inflate(R.layout.application_item, parent, false);

				holder = new ViewHolder();
				holder.icon = (ImageView) convertView.findViewById(R.id.icon);
				holder.txtAppName = (TextView) convertView.findViewById(R.id.txtAppName);

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			holder.icon.setImageDrawable(item.icon);
			holder.txtAppName.setText(item.appName);

			switch(item.appType){
			case ApplicationItem.TYPE_SYSTEM:
				holder.txtAppName.setTextColor(0xFFFF0000);
				break;
			case ApplicationItem.TYPE_NORMAL:
				holder.txtAppName.setTextColor(0xFFFFFFFF);
				break;
			case ApplicationItem.TYPE_SYSTEM_UPDATED:
				holder.txtAppName.setTextColor(0xFFFFDD00);
				break;
			}
			return convertView;
		}

		public void updateApps(List<ApplicationItem> items) {
			if(items == null) return;

			appList = items;
			notifyDataSetChanged();
		}

		public void updateIcons(HashMap<String, Drawable> iconSet) {
			if(iconSet == null) return;
			Set<String> keys  = iconSet.keySet();
			boolean changed = false;
			for (String key : keys) {
				Drawable ic = iconSet.get(key);
				if (ic != null) {
					ApplicationItem aInfo = cached.get(key);
					if (aInfo != null) {
						aInfo.refreshIcon(ic);
						changed = true;
					}
				}
			}
			if (changed) {
				notifyDataSetChanged();
			}
		}
	}
	
	@Override
	public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
		if (key.equals(Keys.PREF_HIDE_SYSTEM_APPS)) {
			refreshAppList();
		} else if (key.equals(Keys.PREF_SORT_APPS_BY)) {
			refreshAppList();
		}
	}
}