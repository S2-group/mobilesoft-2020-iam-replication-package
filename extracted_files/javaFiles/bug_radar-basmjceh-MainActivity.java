/** Copyright © 2015 Denys Zelenchuk.
 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.**/

package com.error.hunter;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends ListActivity {
	
	Bundle bundle = new Bundle(); 
	String[] lines;
	SharedPreferences settings;
	SharedPreferences.Editor editor;
	ListView lw;
	ListView listView = null;
	List<String> packagesList = null;
	ArrayAdapter<String> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	    
	    SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
		boolean showSystemPackages = sharedPref.getBoolean("pref_system_packages", false);

		packagesList = getPackages(showSystemPackages);
		adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_multiple_choice, packagesList);
		setListAdapter(adapter);

        listView = getListView();
        
        listView.setItemsCanFocus(false);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        
		settings = getSharedPreferences("eCatcher", 0);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
		return true;
	}
	
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		super.onSaveInstanceState(savedInstanceState);
	}
	
	@Override
	public void onPause(){
		super.onPause();
	}
	
	@Override
	public void onResume(){
		super.onResume();
		
		SharedPreferences sharedPref2 = getSharedPreferences("eCatcher", 0);
		int counter = sharedPref2.getInt("counter", 0);
		
		if ((counter%2) != 0){
        	listView.clearChoices();
        	listView.invalidateViews();
        	
    		SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
    		boolean showSystemPackages = sharedPref.getBoolean("pref_system_packages", false);
    		
    		adapter.clear();
    		packagesList = getPackages(showSystemPackages);
    		adapter.addAll(packagesList);
    		adapter.notifyDataSetChanged();
		}
		
        final Button button = (Button) findViewById(R.id.button_monitor);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {            	
            	
            	List<String> words = new ArrayList<String>(); 
            	Set<String> set;
            	
            	int count = listView.getCheckedItemCount();            
            	SparseBooleanArray sparseBooleanArray = listView.getCheckedItemPositions();
            	if(count!=0){
            		for(int i=0; i<count; i++) {
            			if(sparseBooleanArray.get(i) == true) {
            				words.add(listView.getItemAtPosition(i).toString());
            			}
            		}
            		set = new HashSet<String>(words);
            	} else {
            		set = new HashSet<String>(packagesList);
            	}
            	
				editor = settings.edit();
				editor.putStringSet("ARRAY", set);
				editor.commit();
				//bundle.putStringArray("ARRAY", lines); 
				
				Intent intent = new Intent(MainActivity.this, ListenService.class);
            	startService(intent);
            	startServiceToast();
            	MainActivity.this.finish();
            }
        });
        
        final Button buttonClear = (Button) findViewById(R.id.button_clear);
        buttonClear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	listView.clearChoices();
            	listView.invalidateViews();
            }
        });
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.action_settings:
	        	Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
				//intent.putExtras(bundle);
	        	startActivityForResult(intent, 1);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	private List<String> getPackages(boolean showSystemPackages){
		
		final Comparator<String> cmp = new Comparator<String>() {
			public int compare(String str1, String str2) {
				return str1.compareTo(str2);
			}
		};
		
		final List<String> installedApps = new ArrayList<String>();

		//installedApps
		final PackageManager pm = getPackageManager();
		List<ApplicationInfo> apps = pm.getInstalledApplications(0);
		
		for(ApplicationInfo app : apps) {
			if(showSystemPackages){
				installedApps.add(app.packageName);
			} else {
			    if((app.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) == 1) {
                    //checks for flags; if flagged, check if updated system app
			        installedApps.add(app.packageName);
			    } else if ((app.flags & ApplicationInfo.FLAG_SYSTEM) == 1) {
                    //it's a system app, not interested
			        //Discard this one
			    } else {
			        //in this case, it should be a user-installed app
			        installedApps.add(app.packageName);
			    }
			}
		}
		
		Collections.sort(installedApps, cmp);
		
		return installedApps;
	}
	
	private void startServiceToast(){
		
		Handler h = new Handler(MainActivity.this.getMainLooper());
        h.post(new Runnable() {
            public void run() {
            	Toast.makeText(MainActivity.this, MainActivity.this.getString(R.string.monitoring_started),Toast.LENGTH_LONG).show();
            }
        });
	}
}
