package com.tajchert.hours.ui;


import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tajchert.hours.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;
 
public class AppArrayAdapter extends ArrayAdapter<String> {
	private final Context context;
	private final static ArrayList<String> appNames = new ArrayList<String>();
	private final static TreeMap<String, Drawable> appIconsAndNames = new TreeMap<String, Drawable>();
	public static ArrayList<ApplicationInfo> appInstalled = new ArrayList<ApplicationInfo>();
 
	public AppArrayAdapter(Context context, PackageManager pck) {
		
		super(context, R.layout.list_apps, appNames);
		appIconsAndNames.clear();
		appNames.clear();
		appInstalled = (ArrayList<ApplicationInfo>) pck.getInstalledApplications(PackageManager.GET_META_DATA);
		ArrayList<ApplicationInfo> apps = new ArrayList<ApplicationInfo>();
		Drawable appIcon;
		for(ApplicationInfo app : appInstalled){
        	String appName = (String) pck.getApplicationLabel(app);
        	if(appName != null && appName.length() > 0){
        		appIcon = pck.getApplicationIcon(app);
        		appNames.add(appName);
        		appIconsAndNames.put(appName, appIcon);
        	}
        }
		Collections.sort(appNames, String.CASE_INSENSITIVE_ORDER);
		appInstalled = apps;
		this.context = context;
	}
 
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
		View rowView = inflater.inflate(R.layout.list_apps, parent, false);
		TextView textView = (TextView) rowView.findViewById(R.id.label);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.logo);
		textView.setText(appNames.get(position));
		imageView.setImageDrawable(appIconsAndNames.get(appNames.get(position)));
		
 
		return rowView;
	}
}