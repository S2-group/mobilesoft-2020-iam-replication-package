/*
 * @(#)FragmentPermission.java
 * Date : 2014. 12. 31.
 * Copyright: (C) 2014 by devsunset All right reserved.
 */
package kr.pe.devsunset.aps;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

/**
 * <PRE>
 * AppPermissionScan FragmentPermission
 * </PRE>
 * 
 * @author devsunset
 * @version 1.0
 * @since AppPermissionScan 1.0
 */

public class FragmentPermission extends Fragment {
	
	public static final String TAG = "FragmentPermission";
	
	private PackageManager pm;
	private View mLoadingContainer;
	private ListView mListView = null;
	private IAAdapter mAdapter = null;
	
	
	private View view = null;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.app_info, container, false);
		view = v;
		setInit();
		return v;
	}

	private void setInit() {
		mLoadingContainer = view.findViewById(R.id.loading_container);
		mListView = (ListView)  view.findViewById(R.id.listApp);
		mAdapter = new IAAdapter(view.getContext());
		mListView.setAdapter(mAdapter);
	}
	
	@Override
	public void onResume() {
		super.onResume();
		startTask();
	}

	public void startTask() {
		new AppTask().execute();
	}

	private void setLoadingView(boolean isView) {
		if (isView) {
			mLoadingContainer.setVisibility(View.VISIBLE);
			mListView.setVisibility(View.GONE);
		} else {
			mListView.setVisibility(View.VISIBLE);
			mLoadingContainer.setVisibility(View.GONE);
		}
	}

	private class ViewHolder {
		public ImageView mIcon;
		public TextView mName;
		public TextView mPacakge;
		public TextView mPacakgeValue;
		public TextView mPermissionDesc;
		public TextView mPermission;
		public TextView mFirstInstallTime;
		public TextView mLastUpdateTime;
		public Button btnSearch;
		public Button btnDelete;
		public LinearLayout lApp;
	}

	@SuppressLint("ResourceAsColor")
	private class IAAdapter extends BaseAdapter {
		private Context mContext = null;

		private List<ApplicationInfo> mAppList = null;
		private ArrayList<AppInfo> mListData = new ArrayList<AppInfo>();

		public IAAdapter(Context mContext) {
			super();
			this.mContext = mContext;
		}

		public int getCount() {
			return mListData.size();
		}

		public Object getItem(int arg0) {
			return null;
		}

		public long getItemId(int arg0) {
			return 0;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if (convertView == null) {
				holder = new ViewHolder();

				LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = inflater.inflate(R.layout.app_permission_list_item, null);
				
				
				holder.lApp  = (LinearLayout) convertView.findViewById(R.id.listItemHeader);
				holder.mIcon = (ImageView) convertView.findViewById(R.id.app_icon);
				holder.mName = (TextView) convertView.findViewById(R.id.app_name);
				holder.mPacakge = (TextView) convertView.findViewById(R.id.app_package);
				holder.mPacakgeValue = (TextView) convertView.findViewById(R.id.app_package_value);
				holder.mPermissionDesc = (TextView) convertView.findViewById(R.id.app_permission_desc);
				holder.mPermission = (TextView) convertView.findViewById(R.id.app_permission_hidden);
				holder.mFirstInstallTime = (TextView) convertView.findViewById(R.id.installTime);
				holder.mLastUpdateTime = (TextView) convertView.findViewById(R.id.updateTime);
				holder.btnSearch = (Button) convertView.findViewById(R.id.btnSearch);
				holder.btnDelete = (Button) convertView.findViewById(R.id.btnDelete);
				
				convertView.setTag(holder);
				
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			holder.btnSearch.setOnClickListener(new OnClickListener() {  
                @Override  
                public void onClick(View v) {
	  				LinearLayout layout = (LinearLayout)v.getParent();
	  				TextView tvPackage = (TextView)layout.getChildAt(2);
	  				messageBox(view.getContext(), getString(R.string.common_info_title),(String) tvPackage.getText());
	  				//Toast.makeText(view.getContext(),tvPackage.getText(),Toast.LENGTH_SHORT).show();
                }
            });
			
			holder.btnDelete.setOnClickListener(new OnClickListener() {  
                @Override  
                public void onClick(View v) {
	  				LinearLayout layout = (LinearLayout)v.getParent();
	  				TextView tvPackage = (TextView)layout.getChildAt(3);
                    Intent intent = new Intent(Intent.ACTION_DELETE).setData(Uri.parse("package:" + tvPackage.getText()));
                    startActivity(intent);
                }
            });
			
			holder.btnDelete.setTag(position);
			
			AppInfo data = mListData.get(position);

			if (data.mIcon != null) {
				holder.mIcon.setImageDrawable(data.mIcon);
			}

			
			if(data.mFirstInstallTime == null || "".equals(data.mFirstInstallTime)){
				holder.mName.setText(data.mAppName+" ("+getString(R.string.access_denied)+")");   
				holder.btnSearch.setVisibility(View.GONE);
				//holder.btnDelete.setVisibility(View.GONE);
			}else{
				holder.mName.setText(data.mAppName+" (v"+data.mVersionName+")");
				if(data.mPermission == null || "".equals(data.mPermission)){
					holder.btnSearch.setVisibility(View.GONE);
				}else{
					holder.btnSearch.setVisibility(View.VISIBLE);
				}
				//holder.btnDelete.setVisibility(View.VISIBLE);
			}
			
			
			if("S".equals(data.mAppCategory)){
				holder.btnDelete.setVisibility(View.GONE);
				holder.lApp.setBackgroundColor(getResources().getColor(R.color.system_header));
				holder.mName.setBackgroundColor(getResources().getColor(R.color.system_header));
			}else{
				holder.btnDelete.setVisibility(View.VISIBLE);
				holder.lApp.setBackgroundColor(getResources().getColor(R.color.install_header));
				holder.mName.setBackgroundColor(getResources().getColor(R.color.install_header));
			}
					
			holder.mPacakge.setText(data.mAppPackge);
			holder.mPacakgeValue.setText(data.mAppPackge);
			holder.mPermissionDesc.setText(data.mPermissionDesc);
			holder.mPermission.setText(data.mPermission);
			holder.mFirstInstallTime.setText(data.mFirstInstallTime);
			holder.mLastUpdateTime.setText(data.mLastUpdateTime);
			

			return convertView;
		}

		@SuppressLint("NewApi")
		public void rebuild(){
			try{
				
				mListData.clear();
				
				if("[SELECT]".equals(AppContent.PERMISSION_CODE) || "[선택]".equals(AppContent.PERMISSION_CODE)){
					return;
				}
				
				if (mAppList == null) {
					pm = mContext.getPackageManager();
					mAppList = pm.getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES | PackageManager.GET_DISABLED_COMPONENTS);
				}
	
				AppInfo addInfo = null;
				ApplicationInfo info = null;
				PackageInfo packageInfo = null;
				TreeSet<String> tsPermission = null;
				for (ApplicationInfo app : mAppList) {
						info = app;
						addInfo = new AppInfo();
						addInfo.mIcon = app.loadIcon(pm);
						addInfo.mAppName = app.loadLabel(pm).toString();
						addInfo.mAppPackge = app.packageName;
						
						if ((info.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0) {
							addInfo.mAppCategory= "D";
						} else if ((info.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
							addInfo.mAppCategory= "D";
						}else{
							addInfo.mAppCategory= "S";
						}
						
						int intPermissionCount = 0;
							
						packageInfo = pm.getPackageInfo(app.packageName, PackageManager.GET_PERMISSIONS);
							
						
						boolean addFlag = false;
						if(packageInfo != null){
							addInfo.mVersionCode = packageInfo.versionCode+"";
							addInfo.mVersionName = packageInfo.versionName;
							addInfo.mFirstInstallTime = setDateFormat(packageInfo.firstInstallTime);
							addInfo.mLastUpdateTime = setDateFormat(packageInfo.lastUpdateTime);
							
						      String[] requestedPermissions = packageInfo.requestedPermissions;
						      
						      if(requestedPermissions != null) {
						    	 tsPermission = new TreeSet<String>();
						         for (int i = 0; i < requestedPermissions.length; i++) {
						        	 tsPermission.add(requestedPermissions[i]);
						         }
						         
						         Iterator<String> iterator = tsPermission.iterator();
						        
						         StringBuffer sb = new StringBuffer();
						         while (iterator.hasNext()){
						        	    String tempKey = iterator.next();
						        	 	
						        	    PackageManager packageManager = mContext.getPackageManager();
						  				PermissionInfo pinfo = null;
						  				PermissionGroupInfo pginfo = null;
						  				
						  				String desc1 = "";
						  				String desc2 = "";
						  				String desc3 = "";
						  				String desc4 = "";
						  				
						  				try {
											pinfo = packageManager.getPermissionInfo(tempKey, PackageManager.GET_META_DATA);
											pginfo = packageManager.getPermissionGroupInfo(pinfo.group, PackageManager.GET_META_DATA);
											desc1 = pginfo.loadLabel(packageManager).toString();
											desc2 = pginfo.loadDescription(packageManager).toString();
											desc3 = pinfo.loadLabel(packageManager).toString();
											desc4 = pinfo.loadDescription(packageManager).toString();
										} catch (Exception e) {
											//e.printStackTrace();
											 desc1 = "";
							  				 desc2 = "";
							  				 desc3 = "";
							  				 desc4 = "";
										}
						  				
						        	  tempKey = tempKey.replaceAll("android.permission.", "").replaceAll("com.android.launcher.permission.", "")
						        			 .replaceAll("com.android.browser.permission.", "").replaceAll("com.android.voicemail.permission.", "")
						        			 .replaceAll("com.android.alarm.permission.", "");
						        	 
						        	 sb.append( tempKey+"|"+desc1+"|"+desc2+"|"+desc3+"|"+desc4+ "\n");
						        	 
						        	 if(AppContent.PERMISSION_CODE.equals(tempKey) || (AppContent.PERMISSION_CODE.equals("APP_PERMISSION") && tempKey.indexOf(".") >-1)){
						        		 addFlag = true;
						        	 }
						         }
						         addInfo.mPermission = sb.toString();
						         
						         intPermissionCount = tsPermission.size();
						      }else{
						    	  addInfo.mPermission = "";
						      }
						}
						
						addInfo.mPermissionDesc = getString(R.string.app_permission)+" ("+intPermissionCount+")";
	
						if(addFlag){
							mListData.add(addInfo);
						}
						
				}
				Collections.sort(mListData, AppInfo.ALPHA_COMPARATOR_APP);
			}catch(Exception e){
				e.printStackTrace();		
			}
		}
	}
	
	@SuppressLint("SimpleDateFormat")
    private String setDateFormat(long time) {
        Date date = new Date(time);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = formatter.format(date);
        return strDate;
    }
	 
	private class AppTask extends AsyncTask<Void, Void, Void> {
		@Override
		protected void onPreExecute() {
			setLoadingView(true);
		}

		@Override
		protected Void doInBackground(Void... params) {
			mAdapter.rebuild();
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			mAdapter.notifyDataSetChanged();
			setLoadingView(false);
			AppContent.PERMISSION_SEARCH = true;
			AppContent.app_permission.setEnabled(true);
		}
	};
	
	public static void messageBox(Context context, String msgTitle, String msgText) {
		View layout = View.inflate(context, R.layout.common_alert_dialog, null);
		layout.findViewById(R.id.commonListDialogContentsTextView).setVisibility(View.VISIBLE);

		((TextView) layout.findViewById(R.id.commonListDialogTitleTextView)).setText(msgTitle);
		((TextView) layout.findViewById(R.id.commonListDialogContentsTextView)).setText(Html.fromHtml(PermissionUtil.makePermissionSearchStringToHtml(msgText)));

		final AlertDialog _ab = new AlertDialog.Builder(context).show();
		_ab.getWindow().setBackgroundDrawable(new ColorDrawable(0x0000ff00));
		_ab.setContentView(layout);
		_ab.findViewById(R.id.commonListDialogOkButton).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				_ab.dismiss();
			}
		});
	}
}
