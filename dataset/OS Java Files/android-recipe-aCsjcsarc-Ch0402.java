package jp.co.se.android.recipe.chapter04;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Ch0402 extends Activity {

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch0403_main);

        mListView = (ListView) findViewById(android.R.id.list);

        ArrayList<String> appList = createAppList();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, appList);
        mListView.setAdapter(adapter);
    }

    private ArrayList<String> createAppList() {
        ArrayList<String> list = new ArrayList<String>();

        PackageManager pm = getPackageManager();
        List<ApplicationInfo> appInfoList = pm
                .getInstalledApplications(PackageManager.GET_META_DATA);
        for (Iterator<ApplicationInfo> i = appInfoList.iterator(); i.hasNext();) {
            ApplicationInfo appInfo = i.next();

            String appLabel = pm.getApplicationLabel(appInfo).toString();
            list.add(appLabel);
        }

        return list;
    }
}
