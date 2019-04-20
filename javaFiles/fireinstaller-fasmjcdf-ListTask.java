package com.dlka.fireinstaller;

import android.app.ListActivity;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Query the packagemanager for a list of all installed apps that are not system
 * apps. Populate a listview with the result.
 *
 * @author patrick
 */
public class ListTask extends
        AsyncTask<Object, Object, ArrayList<SortablePackageInfo>> {

    private ListActivity listActivity;
    private int layout;

    /**
     * New task
     *
     * @param listActivity the activity to report back to
     * @param layout       layout id to pass to the AppAdaptier
     */
    public ListTask(ListActivity listActivity, int layout) {
        this.listActivity = listActivity;
        this.layout = layout;
    }

    @Override
    protected ArrayList<SortablePackageInfo> doInBackground(Object... params) {
        ArrayList<SortablePackageInfo> ret = new ArrayList<SortablePackageInfo>();
        PackageManager pm = listActivity.getPackageManager();
        List<PackageInfo> list = pm.getInstalledPackages(0);
        SortablePackageInfo spitmp[] = new SortablePackageInfo[list.size()];
        Iterator<PackageInfo> it = list.iterator();
        //	AnnotationsSource aSource = new AnnotationsSource(listActivity);
        //	aSource.open();
        int idx = 0;
        while (it.hasNext()) {
            PackageInfo info = it.next();
            try {
                ApplicationInfo ai = pm.getApplicationInfo(info.packageName, 0);
                    spitmp[idx] = new SortablePackageInfo();
                    spitmp[idx].packageName = info.packageName;
                    spitmp[idx].sourceDir = ai.sourceDir;
                    spitmp[idx].displayName = pm
                            .getApplicationLabel(info.applicationInfo).toString();
                    spitmp[idx].appInfo = ai;
                    //TODO needing this? //spitmp[idx].uid = info.applicationInfo.uid;
                    idx++;

            } catch (NameNotFoundException exp) {
            }
        }
        // Reminder: the copying is necessary because we are filtering away the
        // system apps.
        SortablePackageInfo spi[] = new SortablePackageInfo[idx];
        System.arraycopy(spitmp, 0, spi, 0, idx);
        Arrays.sort(spi);
        for (int i = 0; i < spi.length; i++) {
            spi[i].selected = false;//unselect all apps on createView onStart
            ret.add(spi[i]);
        }
        return ret;
    }

    @Override
    protected void onPreExecute() {
        listActivity.setProgressBarIndeterminate(true);
        listActivity.setProgressBarVisibility(true);
    }

    @Override
    protected void onPostExecute(ArrayList<SortablePackageInfo> result) {
        super.onPostExecute(result);
        listActivity.setListAdapter(new AppAdapter(listActivity, layout, result,
                layout));
        listActivity.setProgressBarIndeterminate(false);
        listActivity.setProgressBarVisibility(false);
    }

}
