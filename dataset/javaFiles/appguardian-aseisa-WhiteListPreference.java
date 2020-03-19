package edu.iub.seclab.appguardian;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Comparator;

import android.app.ListFragment;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class WhiteListPreference extends ListFragment {

	MyCustomAdapter dataAdapter = null;
	AssetManager am;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_whitelist,
				container, false);

		PackageManager pm = getActivity().getPackageManager();
		List<ApplicationInfo> apps = pm.getInstalledApplications(0);

		ArrayList<WhiteListItem> WhiteListItemList = new ArrayList<WhiteListItem>();

		for (ApplicationInfo app : apps) {
			WhiteListItem WhiteListItem = new WhiteListItem(
					(String) app.loadLabel(pm), app.packageName,
					app.loadIcon(pm), false);
			WhiteListItemList.add(WhiteListItem);
		}
		Collections.sort(WhiteListItemList, new Comparator<WhiteListItem>() {
			public int compare(WhiteListItem o1, WhiteListItem o2) {
				return o1.getName().compareToIgnoreCase(o2.getName());
			}
		});

		am = getActivity().getAssets();

		dataAdapter = new MyCustomAdapter(getActivity(), R.xml.white_list_item,
				WhiteListItemList);
		// Assign adapter to ListView
		setListAdapter(dataAdapter);
		return rootView;
	}

	private class MyCustomAdapter extends ArrayAdapter<WhiteListItem> {

		private ArrayList<WhiteListItem> WhiteListItemList;
		private ArrayList<String> WhiteListFileList = new ArrayList<String>();
		private ArrayList<String> TargetListFileList = new ArrayList<String>();

		private class ViewHolder {
			ImageView icon;
			TextView name;
			CheckBox checked;
		}

		public MyCustomAdapter(Context context, int textViewResourceId,
				ArrayList<WhiteListItem> WhiteListItemList) {
			super(context, textViewResourceId, WhiteListItemList);
			this.WhiteListItemList = new ArrayList<WhiteListItem>();
			this.WhiteListItemList.addAll(WhiteListItemList);
			readList("whitelist.txt", WhiteListFileList);
			readList("targetlist.txt", TargetListFileList);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder viewHolder; // view lookup cache stored in tag
			if (convertView == null) {
				viewHolder = new ViewHolder();
				LayoutInflater inflater = LayoutInflater.from(getContext());
				convertView = inflater.inflate(R.xml.white_list_item, parent,
						false);
				viewHolder.icon = (ImageView) convertView
						.findViewById(R.id.imageView1);
				viewHolder.name = (TextView) convertView
						.findViewById(R.id.textView1);
				viewHolder.checked = (CheckBox) convertView
						.findViewById(R.id.checkBox1);
				convertView.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) convertView.getTag();
			}
			viewHolder.checked.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					CheckBox cb = (CheckBox) v;
					WhiteListItem WhiteListItem = (WhiteListItem) cb.getTag();
					WhiteListItem.setSelected(cb.isChecked());
					if (cb.isChecked() == true) {
						WhiteListFileList.add(WhiteListItem.getPackageName());
					} else {
						WhiteListFileList.remove(WhiteListFileList
								.indexOf(WhiteListItem.getPackageName()));
					}
					writeList("whitelist.txt", WhiteListFileList);
				}
			});
			WhiteListItem WhiteListItem = WhiteListItemList.get(position);

			WhiteListItem.setSelected(WhiteListFileList.contains(WhiteListItem
					.getPackageName()));

			viewHolder.name.setText(WhiteListItem.getName());
			viewHolder.checked.setChecked(WhiteListItem.isSelected());
			viewHolder.icon.setImageDrawable(WhiteListItem.getIcon());
			viewHolder.checked.setTag(WhiteListItem);
			if (TargetListFileList.contains(WhiteListItem.getPackageName())) {
				viewHolder.checked.setEnabled(false);
			}
			return convertView;
		}

		private void readList(String filename, ArrayList<String> list) {
			list.clear();
			InputStream is;
			try {
				if (isFilePresent(filename)) {
					is = getActivity().openFileInput(filename);
				} else {
					is = am.open(filename);
				}
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(is));
				String line = reader.readLine();
				while (line != null) {
					list.add(line);
					line = reader.readLine();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		private void writeList(String filename, ArrayList<String> list) {
			try {
				FileOutputStream outputStream = getActivity().openFileOutput(filename, Context.MODE_PRIVATE);
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
						outputStream));

				for (int i = 0; i < list.size(); i++) {
					bw.write(list.get(i));
					bw.newLine();
				}
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public boolean isFilePresent(String filename) {
		    String path = getContext().getFilesDir().getAbsolutePath() + "/" + filename;
		    File file = new File(path);
		    return file.exists();
		}
	}

}
