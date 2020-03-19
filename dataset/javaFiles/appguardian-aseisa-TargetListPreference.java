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

import android.app.ActivityManager;
import android.app.ListFragment;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class TargetListPreference extends ListFragment {

	TargetAdapter dataAdapter = null;
	AssetManager am;
	Messenger mService = null;
	
	boolean mBound;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        if (!isMyServiceRunning(AppGuardianService.class)) {
        	getActivity().startService(new Intent(getActivity(), AppGuardianService.class));
        }
        
		getActivity().bindService(
				new Intent(getActivity(), AppGuardianService.class),
				mConnection, 0);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_targetlist,
				container, false);

		PackageManager pm = getActivity().getPackageManager();
		List<ApplicationInfo> apps = pm.getInstalledApplications(0);

		ArrayList<WhiteListItem> TargetListItemList = new ArrayList<WhiteListItem>();

		for (ApplicationInfo app : apps) {
			if ((app.flags & ApplicationInfo.FLAG_SYSTEM) != 1) {
				WhiteListItem WhiteListItem = new WhiteListItem(
						(String) app.loadLabel(pm), app.packageName,
						app.loadIcon(pm), false);
				TargetListItemList.add(WhiteListItem);
			}
		}
		Collections.sort(TargetListItemList, new Comparator<WhiteListItem>() {
			public int compare(WhiteListItem o1, WhiteListItem o2) {
				return o1.getName().compareToIgnoreCase(o2.getName());
			}
		});

		am = getActivity().getAssets();

		dataAdapter = new TargetAdapter(getActivity(), R.xml.white_list_item,
				TargetListItemList);
		// Assign adapter to ListView
		setListAdapter(dataAdapter);
		return rootView;
	}
	
	@Override
	public void onDestroy() {
	    if (mBound) {
        	getActivity().unbindService(mConnection);
            mBound = false;
        }
	    super.onDestroy();
	}

	private class TargetAdapter extends ArrayAdapter<WhiteListItem> {

		private ArrayList<WhiteListItem> TargetListItemList;
		private ArrayList<String> TargetListFileList = new ArrayList<String>();
		private ArrayList<String> WhiteListFileList = new ArrayList<String>();

		private class ViewHolder {
			ImageView icon;
			TextView name;
			CheckBox checked;
		}

		public TargetAdapter(Context context, int textViewResourceId,
				ArrayList<WhiteListItem> TargetListItemList) {
			super(context, textViewResourceId, TargetListItemList);
			this.TargetListItemList = new ArrayList<WhiteListItem>();
			this.TargetListItemList.addAll(TargetListItemList);
			readList("targetlist.txt", TargetListFileList);
			readList("whitelist.txt", WhiteListFileList);
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
						TargetListFileList.add(WhiteListItem.getPackageName());
					} else {
						TargetListFileList.remove(TargetListFileList
								.indexOf(WhiteListItem.getPackageName()));
					}
					writeList("targetlist.txt", TargetListFileList);
					if (!WhiteListFileList.contains(WhiteListItem.getPackageName())) {
						WhiteListFileList.add(WhiteListItem.getPackageName());
						writeList("whitelist.txt", WhiteListFileList);
					}
					Message msg = Message.obtain(null, AppGuardianService.MSG_TARGETS_CHANGED, 0, 0);
			        try {
			            mService.send(msg);
			        } catch (RemoteException e) {
			            e.printStackTrace();
			        }
				}
			});
			WhiteListItem WhiteListItem = TargetListItemList.get(position);

			WhiteListItem.setSelected(TargetListFileList.contains(WhiteListItem
					.getPackageName()));

			viewHolder.name.setText(WhiteListItem.getName());
			viewHolder.checked.setChecked(WhiteListItem.isSelected());
			viewHolder.icon.setImageDrawable(WhiteListItem.getIcon());
			viewHolder.checked.setTag(WhiteListItem);
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
	
	private boolean isMyServiceRunning(Class<?> serviceClass) {
	    ActivityManager manager = (ActivityManager) getActivity().getSystemService(Context.ACTIVITY_SERVICE);
	    for (RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
	        if (serviceClass.getName().equals(service.service.getClassName())) {
	            return true;
	        }
	    }
	    return false;
	}
	
	private ServiceConnection mConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            // This is called when the connection with the service has been
            // established, giving us the object we can use to
            // interact with the service.  We are communicating with the
            // service using a Messenger, so here we get a client-side
            // representation of that from the raw IBinder object.
            mService = new Messenger(service);
            mBound = true;
        }

        public void onServiceDisconnected(ComponentName className) {
            // This is called when the connection with the service has been
            // unexpectedly disconnected -- that is, its process crashed.
            mService = null;
            mBound = false;
        }
    };

}
