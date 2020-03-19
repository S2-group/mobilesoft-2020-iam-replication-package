/*
        Copyright (C) <2014>  <Patrick Gray MacDowell>

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
package com.pgmacdesign.rsrtoolbox;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

public class Calculator extends Activity {

	//Global Variables
	
	
	//Main - When the activity starts
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calculator);
		
		//This first one should try to run the default calculator. Seems to be erroring out on HTCs, will add second section
		ArrayList<HashMap<String,Object>> items =new ArrayList<HashMap<String,Object>>();
		
		try{
		     ApplicationInfo info = getPackageManager()
		                             .getApplicationInfo("com.android.calculator2", 0 );

		    } catch( PackageManager.NameNotFoundException e ){
		     //application doesn't exist
		}
		
		//PackageManager pm;
		final PackageManager pm = getPackageManager();
		
		List<PackageInfo> packs = pm.getInstalledPackages(0);  
		
		for (PackageInfo pi : packs) {
			if( pi.packageName.toString().toLowerCase().contains("calcul")){
			    HashMap<String, Object> map = new HashMap<String, Object>();
			    map.put("appName", pi.applicationInfo.loadLabel(pm));
			    map.put("packageName", pi.packageName);
			    items.add(map);
			}
		}
	
		if(items.size()>=1){
			String packageName = (String) items.get(0).get("packageName");
			Intent i = pm.getLaunchIntentForPackage(packageName);
			if (i != null)
				startActivity(i);
				finish();
		}
		
			//Testing for HTC phones
				final String CALCULATOR_PACKAGE ="com.android.calculator2";
				final String CALCULATOR_CLASS ="com.android.calculator2.Calculator";
				  Intent intent = new Intent();

				    intent.setAction(Intent.ACTION_MAIN);
				         intent.addCategory(Intent.CATEGORY_LAUNCHER);
				        intent.setComponent(new ComponentName(
				         CALCULATOR_PACKAGE,
				         CALCULATOR_CLASS));
		        finish();
			
		
	}

	
	protected void onPause() {

		super.onPause();
		finish();
	}

}
