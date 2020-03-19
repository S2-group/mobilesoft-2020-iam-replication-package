/*
 * @(#)MainActivity.java
 * Date : 2014. 12. 31.
 * Copyright: (C) 2014 by devsunset All right reserved.
 */
package kr.pe.devsunset.aps;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * <PRE>
 * AppPermissionScan Main
 * </PRE>
 * 
 * @author devsunset
 * @version 1.0
 * @since AppPermissionScan 1.0
 */

public class MainActivity extends BaseActivity implements OnClickListener {
	public Context mContext = null;
	private Button btnGo; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
		setContentView(R.layout.main_activity);
		initActivity();
		
		@SuppressWarnings("unused")
		PermissionUtil pu = new PermissionUtil(this);
	}
	
	private void initActivity(){
		btnGo = (Button)findViewById(R.id.btnGo);
		btnGo.setOnClickListener(this);
	}
	
	
	@Override
	public void onClick(View v) {
		//installAppList();
		//appPermissionList();
		
		Intent intent = new Intent(this, AppContent.class);
		startActivity(intent);
		finish();
	}
	
	@SuppressWarnings("unused")
	private void installAppList() {
		StringBuffer sb = new StringBuffer();
		
     	PackageManager pm = mContext.getPackageManager();
		List<ApplicationInfo> list = pm.getInstalledApplications(0);

		String name = null;
		//String path = null;
		//Drawable icon = null;

		for (ApplicationInfo applicationInfo : list) {
			if ((ApplicationInfo.FLAG_UPDATED_SYSTEM_APP & applicationInfo.flags) != 0
					|| (applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
				name = String.valueOf(applicationInfo.loadLabel(pm));
				//path = applicationInfo.packageName; 
				//icon = applicationInfo.loadIcon(pm); 
				sb.append("■■■ App Name : "+name+"\n");
			}
		}
		//sb.toString()
	}
	
	@SuppressLint("NewApi")
	private void appPermissionList() {
		PackageManager pm = mContext.getPackageManager();
     	List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);
     	StringBuffer sb = new StringBuffer();
		for (ApplicationInfo applicationInfo : packages) {
		   try {
		      PackageInfo packageInfo = pm.getPackageInfo(applicationInfo.packageName, PackageManager.GET_PERMISSIONS);
		      sb.append("■■■ App Name : "+packageInfo.applicationInfo.loadLabel(pm).toString()+" - "+packageInfo.packageName+"\n");
		      
		      String[] requestedPermissions = packageInfo.requestedPermissions;
		      
		      if(requestedPermissions != null) {
		         for (int i = 0; i < requestedPermissions.length; i++) {
		        	 sb.append("=>"+requestedPermissions[i]+"\n");
		         }
		      }
		      
		   } catch (NameNotFoundException e) {
		      e.printStackTrace();
		   }
		}
		//sb.toString()
	}

//	app 설치
//    void installApp(String fileName) {
//        Intent intent = new Intent(Intent.ACTION_VIEW)
//            .setDataAndType(Uri.parse("file://" + fileName), "application/vnd.android.package-archive");
//        startActivity(intent);
//    }

	
//	app 제거
//    void removeApp(String packageName) {
//        Intent intent = new Intent(Intent.ACTION_DELETE)
//            .setData(Uri.parse("package:" + packageName));
//        startActivity(intent);
//    }
}
