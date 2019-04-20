package me.iofel.packagelist;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Comparator;
import java.util.List;

public class MainActivity extends ActionBarActivity {
    AppInfoAdapter adapter;
    ListView lv;

    class AppInfo {
        String name, pname;
        Drawable icon;
    }

    class AppInfoAdapter extends ArrayAdapter<AppInfo> {
        public AppInfoAdapter() {
            super(MainActivity.this, R.layout.list_item);
        }

        @Override
        public View getView(int pos, View v, ViewGroup parent) {
            if (v == null) {
                v = MainActivity.this.getLayoutInflater().inflate(R.layout.list_item, parent, false);
            }

            AppInfo app = getItem(pos);
            ((TextView) v.findViewById(R.id.app_name)).setText(app.name);
            ((TextView) v.findViewById(R.id.app_pkg)).setText(app.pname);
            ((ImageView) v.findViewById(R.id.app_icon)).setImageDrawable(app.icon);
            return v;
        }

        @Override
        public void notifyDataSetChanged() {
            this.setNotifyOnChange(false);

            this.sort(new Comparator<AppInfo>() {
                @Override
                public int compare(AppInfo lhs, AppInfo rhs) {
                    return lhs.name.compareTo(rhs.name);
                }
            });


            this.setNotifyOnChange(true);

            super.notifyDataSetChanged();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.activity_main);

        adapter = new AppInfoAdapter();
        lv = (ListView) findViewById(R.id.listView);
        lv.setAdapter(adapter);
        new PackageListLoader().execute();
    }

    class PackageListLoader extends AsyncTask<Void, Integer, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            List<ApplicationInfo> applications = getPackageManager().getInstalledApplications(PackageManager.GET_META_DATA);

            for (ApplicationInfo app : applications) {
                final AppInfo info = new AppInfo();
                info.name = app.loadLabel(getPackageManager()).toString();
                info.pname = app.packageName;
                info.icon = app.loadIcon(getPackageManager());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.add(info);
                    }
                });
            }

            return null;
        }
    }
}
