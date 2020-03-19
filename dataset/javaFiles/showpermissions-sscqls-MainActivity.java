/*
 * Copyright 2012, 2013 Qidu Lin
 * 
 * This file is part of ShowPermissions.
 * 
 * ShowPermissions is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * ShowPermissions is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * ShowPermissions. If not, see <http://www.gnu.org/licenses/>.
 */

package com.qidu.lin.showpermissions;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PermissionInfo;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.CheckBox;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingActivity;

public class MainActivity extends SlidingActivity
{

	private final class myadapter implements ExpandableListAdapter
	{
		public myadapter()
		{
			LinkedList<String> list = new LinkedList<String>();
			for (String key : permissionToPackagesMap.keySet())
			{

				if (shouldSkipPermission(key))
				{
					continue;
				}

				LinkedList<PackageInfo> piList = new LinkedList<PackageInfo>();
				for (PackageInfo appName : permissionToPackagesMap.get(key))
				{
					if (shouldSkipForHideGoogle(appName))
					{
						continue;
					}

					piList.add(appName);
				}

				if (piList.isEmpty())
				{
					continue;
				}

				Comparator<PackageInfo> comparator = new Comparator<PackageInfo>()
				{
					Collator x = Collator.getInstance();

					@Override
					public int compare(PackageInfo lhs, PackageInfo rhs)
					{
						String l = makeStringForPackage(pm, lhs);
						String r = makeStringForPackage(pm, rhs);
						return x.compare(l, r);
					}
				};
				Collections.sort(piList, comparator);

				PackageInfo s[] = piList.toArray(new PackageInfo[0]);
				amp.put(key, s);
				list.add(key);
			}

			Comparator<String> comparatorByName = new Comparator<String>()
			{
				Collator x = Collator.getInstance();

				@Override
				public int compare(String lhs, String rhs)
				{
					String l = makeStringForPermission(lhs);
					String r = makeStringForPermission(rhs);
					return x.compare(l, r);
				}
			};
			Comparator<String> comparatorByAppNumber = new Comparator<String>()
			{
				@Override
				public int compare(String lhs, String rhs)
				{
					return amp.get(rhs).length - amp.get(lhs).length;
				}
			};
			Comparator<String> comparatorByDefault = comparatorByName;
			Collections.sort(list, sortByName.isChecked() ? comparatorByName : sortByAppNumber.isChecked() ? comparatorByAppNumber
					: comparatorByDefault);
			keys = list.toArray(new String[0]);
		}

		String[] keys = null;

		HashMap<String, PackageInfo[]> amp = new HashMap<String, PackageInfo[]>();

		@Override
		public void unregisterDataSetObserver(DataSetObserver observer)
		{
			// TODO Auto-generated method stub
		}

		@Override
		public void registerDataSetObserver(DataSetObserver observer)
		{
			// TODO Auto-generated method stub
		}

		@Override
		public void onGroupExpanded(int groupPosition)
		{
			// TODO Auto-generated method stub
		}

		@Override
		public void onGroupCollapsed(int groupPosition)
		{
			// TODO Auto-generated method stub
		}

		@Override
		public boolean isEmpty()
		{
			return false;
		}

		@Override
		public boolean isChildSelectable(int groupPosition, int childPosition)
		{
			return true;
		}

		@Override
		public boolean hasStableIds()
		{
			return false;
		}

		@Override
		public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent)
		{

			TextView tv = null;
			if (convertView == null)
			{
				tv = new TextView(MainActivity.this);
			}
			else
			{
				tv = (TextView) convertView;
			}
			tv.setText(makeStringForPermission(keys[groupPosition]) + " (" + getChildrenCount(groupPosition) + ")");
			AbsListView.LayoutParams lp = new AbsListView.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
					ViewGroup.LayoutParams.WRAP_CONTENT);
			tv.setLayoutParams(lp);
			tv.setPadding(getGroupViewLeftPadding(), 0, 0, 0);
			tv.setTextSize(getGroupViewTextSize());
			tv.setMinHeight(getMinHeight());
			tv.setBackgroundResource(android.R.color.background_light);
			tv.setGravity(Gravity.CENTER_VERTICAL);
			return tv;
		}

		public int getGroupViewTextSize()
		{
			return 18;
		}

		public int getChildViewTextSize()
		{
			return 16;
		}

		public int getGroupViewLeftPadding()
		{
			return 70;
		}

		public int getChildViewLeftPadding()
		{
			return 100;
		}

		@Override
		public long getGroupId(int groupPosition)
		{
			return groupPosition;
		}

		@Override
		public int getGroupCount()
		{
			return keys.length;
		}

		@Override
		public Object getGroup(int groupPosition)
		{
			return amp.get(keys[groupPosition]);
		}

		@Override
		public long getCombinedGroupId(long groupPosition)
		{
			return groupPosition;
		}

		@Override
		public long getCombinedChildId(long groupId, long childId)
		{
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int getChildrenCount(int groupPosition)
		{
			return getPackageInfoList(groupPosition).length;
		}

		public PackageInfo[] getPackageInfoList(int groupPosition)
		{
			return (PackageInfo[]) getGroup(groupPosition);
		}

		@Override
		public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent)
		{
			TextView tv = null;
			if (convertView == null)
			{
				tv = new TextView(MainActivity.this);
			}
			else
			{
				tv = (TextView) convertView;
			}
			tv.setText(makeStringForPackage(pm, getPackageInfo(groupPosition, childPosition)));
			tv.setTextSize(getChildViewTextSize());
			tv.setPadding(getChildViewLeftPadding(), 0, 0, 0);
			tv.setMinHeight(getMinHeight());

			tv.setGravity(Gravity.CENTER_VERTICAL);
			return tv;
		}

		public int getMinHeight()
		{
			return 80;
		}

		public PackageInfo getPackageInfo(int groupPosition, int childPosition)
		{
			return (PackageInfo) getChild(groupPosition, childPosition);
		}

		@Override
		public long getChildId(int groupPosition, int childPosition)
		{
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public Object getChild(int groupPosition, int childPosition)
		{
			return getPackageInfoList(groupPosition)[childPosition];
		}

		@Override
		public boolean areAllItemsEnabled()
		{
			// TODO Auto-generated method stub
			return false;
		}
	}

	HashMap<String, List<PackageInfo>> permissionToPackagesMap = new HashMap<String, List<PackageInfo>>();
	HashMap<String, String> permissionToStringMap = new HashMap<String, String>(); // for
																					// sort
																					// speed
	HashMap<PackageInfo, String> packageInfoToStringMap = new HashMap<PackageInfo, String>(); // for
																								// sort
																								// speed
	private PackageManager pm;
	private CheckBox hideGoogle;
	private CheckBox androidPermissionOnly;
	private CheckBox showPermissionName;
	private CheckBox showPackageName;
	private CheckBox dangerousPermissionOnly;
	private CheckBox sortByName;
	private CheckBox sortByAppNumber;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setBehindContentView(R.layout.activity_behind);

		initSlidingMenu();
		initData();

		this.hideGoogle = (CheckBox) this.findViewById(R.id.hideGoogle);
		this.androidPermissionOnly = (CheckBox) this.findViewById(R.id.androidPermissionsOnly);
		this.showPermissionName = (CheckBox) this.findViewById(R.id.showPermissionName);
		this.showPackageName = (CheckBox) this.findViewById(R.id.showPackageName);
		this.dangerousPermissionOnly = (CheckBox) this.findViewById(R.id.dangerousPermissionOnly);
		this.sortByName = (CheckBox) this.findViewById(R.id.sortByName);
		this.sortByAppNumber = (CheckBox) this.findViewById(R.id.sortByAppNumber);
		new SettingSL().loadSettingsToUI();

		refresh(null);
	}

	public void initSlidingMenu()
	{
		SlidingMenu slidingMenu = getSlidingMenu();
		slidingMenu.setBehindWidth(300);
		slidingMenu.setShadowWidth(2);
		slidingMenu.setShadowDrawable(android.R.color.darker_gray);

	}

	public void refresh(View v)
	{
		if (v == sortByName)
		{
			sortByAppNumber.setChecked(false);
		}
		else if (v == sortByAppNumber)
		{
			sortByName.setChecked(false);
		}

		new SettingSL().saveSettingsFromUI();

		ExpandableListView lv = (ExpandableListView) findViewById(R.id.expandableListView1);
		lv.setOnChildClickListener(new OnChildClickListener()
		{

			@Override
			public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id)
			{
				return showAppOps();
			}

			// only work from 4.3. For those before 4.3, this method does
			// nothing.
			public boolean showAppOps()
			{
				try
				{
					Intent intent = new Intent();
					intent.setClassName("com.android.settings", "com.android.settings.Settings$AppOpsSummaryActivity");
					startActivity(intent);
				}
				catch (ActivityNotFoundException e)
				{

				}
				return true;
			}
		});
		ExpandableListAdapter adapter = new myadapter();
		lv.setAdapter(adapter);
	}

	private class SettingSL
	{
		final static String spname = "settings";

		private void saveSettingsFromUI()
		{
			SharedPreferences sp = MainActivity.this.getSharedPreferences(spname, MODE_PRIVATE);
			Editor edit = sp.edit();
			for (Elem e : elems)
			{
				edit.putBoolean(e.key, e.view.isChecked());
			}
			edit.commit();
		}

		private void loadSettingsToUI()
		{
			SharedPreferences sp = MainActivity.this.getSharedPreferences(spname, MODE_PRIVATE);
			for (Elem e : elems)
			{
				e.view.setChecked(sp.getBoolean(e.key, false));
			}
		}

		class Elem
		{
			public Elem(CheckBox view, String key)
			{
				super();
				this.view = view;
				this.key = key;
			}

			final public CheckBox view;
			final public String key;
		}

		final Elem[] elems;

		public SettingSL()
		{
			elems = new Elem[] { new Elem(hideGoogle, "hideGoogle"), new Elem(androidPermissionOnly, "androidPermissionOnly"),
					new Elem(showPermissionName, "showPermissionName"), new Elem(showPackageName, "showPackageName"),
					new Elem(dangerousPermissionOnly, "dangerousPermissionOnly"), new Elem(sortByName, "sortByName"),
					new Elem(sortByAppNumber, "sortByAppNumber") };
		}
	}

	SettingSL settingSL = null;

	private void initData()
	{
		pm = this.getPackageManager();
		List<PackageInfo> allPackages = pm.getInstalledPackages(PackageManager.GET_PERMISSIONS);
		for (PackageInfo packageInfo : allPackages)
		{
			if (packageInfo == null || packageInfo.requestedPermissions == null)
			{
				continue;
			}
			for (String permission : packageInfo.requestedPermissions)
			{
				List<PackageInfo> as = null;
				if (this.permissionToPackagesMap.containsKey(permission))
				{
					as = this.permissionToPackagesMap.get(permission);
				}
				else
				{
					as = new ArrayList<PackageInfo>();
				}

				as.add(packageInfo);
				this.permissionToPackagesMap.put(permission, as);
			}
		}
	}

	private boolean shouldSkipPermission(String permission)
	{
		return this.skipForAndroidPermissionsOnly(permission) || this.shouldSkipForDangerousPermissionsOnly(permission);
	}

	private boolean shouldSkipForHideGoogle(PackageInfo i)
	{
		if (!this.hideGoogle.isChecked())
		{
			return false;
		}
		return i.packageName.startsWith("com.google") || i.packageName.startsWith("com.android");
	}

	private boolean shouldSkipForDangerousPermissionsOnly(String permission)
	{
		if (!this.dangerousPermissionOnly.isChecked())
		{
			return false;
		}
		boolean dangerous = isSpecificPermissionLevel(permission, PermissionInfo.PROTECTION_DANGEROUS)
				|| isSpecificPermissionLevel(permission, PermissionInfo.PROTECTION_SIGNATURE)
				|| isSpecificPermissionLevel(permission, PermissionInfo.PROTECTION_SIGNATURE_OR_SYSTEM);
		return !dangerous;
	}

	private boolean isSpecificPermissionLevel(String permission, int level)
	{
		try
		{
			return pm.getPermissionInfo(permission, 0).protectionLevel == level;
		}
		catch (NameNotFoundException e)
		{
			return false;
		}
	}

	private boolean skipForAndroidPermissionsOnly(String permission)
	{
		if (!this.androidPermissionOnly.isChecked())
		{
			return false;
		}
		return !permission.startsWith("android.permission.");
	}

	private String makeStringForPermission(String permission)
	{
		if (permissionToStringMap.containsKey(permission))
		{
			return permissionToStringMap.get(permission);
		}

		String ret = null;
		try
		{
			String s = pm.getPermissionInfo(permission, 0).loadLabel(pm).toString();
			if (this.showPermissionName.isChecked())
			{
				s += "(" + permission + ")";
			}
			ret = s;
		}
		catch (NameNotFoundException e)
		{
			ret = permission;
		}

		permissionToStringMap.put(permission, ret);
		return ret;
	}

	private String makeStringForPackage(PackageManager pm, PackageInfo packageInfo)
	{
		if (packageInfoToStringMap.containsKey(packageInfo))
		{
			return packageInfoToStringMap.get(packageInfo);
		}

		String s = packageInfo.applicationInfo.loadLabel(pm).toString();
		if (this.showPackageName.isChecked())
		{
			s += " (" + packageInfo.packageName + ")";
		}
		packageInfoToStringMap.put(packageInfo, s);
		return s;
	}
}
