package com.whitfield.james.simplenetworkspeedmonitor.manager;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.pnikosis.materialishprogress.ProgressWheel;
import com.whitfield.james.simplenetworkspeedmonitor.R;
import com.whitfield.james.simplenetworkspeedmonitor.objects.PackageNetwork;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;

/**
 * Created by jwhit on 05/02/2016.
 */
public class ApplicationTrafficMonitorFragment extends android.support.v4.app.Fragment {

    public static final String TAG = "AppMonitorFragment";
    private ActionBar actionBar;

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutmanager;
    private RecyclerView.Adapter adapter;
    private ProgressWheel wheel;
    private Boolean initial;

    private MenuItem miHelp;

    Comparator<PackageNetwork> comparatorReceived;

    Timer timer;

    ArrayList<PackageNetwork> appsInfo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initial = false;
        appsInfo = new ArrayList<>();
        adapter = new ApplicationAdapter(appsInfo);

        comparatorReceived = new Comparator<PackageNetwork>() {
            @Override
            public int compare(PackageNetwork lhs, PackageNetwork rhs) {

                Long receivedRhs = rhs.getBytesReceievd();
                Long receivedLhs = lhs.getBytesReceievd();

                if(receivedLhs > receivedRhs){
                    return 1;
                }else if(receivedLhs < receivedRhs){
                    return -1;
                }else {
                    return 0;
                }
            }
        };

        setHasOptionsMenu(true);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        miHelp = menu.add("Help");
        miHelp.setIcon(R.drawable.ic_help_white_24dp);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        int id = item.getItemId();
        if(id == miHelp.getItemId()){
            DialogFragment dialogFragment = new AppMonitorHelpDialogFragment();
            dialogFragment.show(getFragmentManager(),"helpDialog");
            return true;
        }


        return super.onOptionsItemSelected(item);



    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view;

        actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        actionBar.setSubtitle("Application Traffic Report");

        view = inflater.inflate(R.layout.fragment_list,container,false);

        recyclerView = (RecyclerView) view.findViewById(R.id.lvRecords);
        recyclerView.setHasFixedSize(true);
        layoutmanager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutmanager);

        wheel = (ProgressWheel) view.findViewById(R.id.pwResponse);
        wheel.setBarColor(getResources().getColor(R.color.colorAccent));

        recyclerView.setAdapter(adapter);

        setViewValues();
        return view;
    }

    private void setViewValues() {

        setupData();

    }

    @Override
    public void onResume() {
        super.onResume();
        new ApplicationInfoTask().execute();
    }

    private void setupData() {


//        new ApplicationInfoTask().execute();
        /*final android.os.Handler handler = new android.os.Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                new ApplicationInfoTask().execute();
                handler.postDelayed(this,10000);
            }
        },0);*/


    }

    private void startSpin(){

        if(!initial) {
            recyclerView.setVisibility(View.GONE);
            wheel.setVisibility(View.VISIBLE);
            wheel.spin();
        }

    }

    private void stopSpin(){

        if(!initial) {
            recyclerView.setVisibility(View.VISIBLE);
            wheel.setVisibility(View.GONE);
            wheel.stopSpinning();
            initial = true;
        }
    }

    public class ApplicationInfoTask extends AsyncTask<Void, Integer, String> {




        @Override
        protected String doInBackground(Void... params) {

            PackageManager packageManager = getContext().getPackageManager();
            ArrayList<ApplicationInfo> applicationInfos = (ArrayList<ApplicationInfo>) packageManager
                    .getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);

            for (ApplicationInfo applicationInfo : applicationInfos){
                appsInfo.add(new PackageNetwork(applicationInfo,packageManager));
            }


            Collections.sort(appsInfo,comparatorReceived);
            Collections.reverse(appsInfo);

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            appsInfo.clear();
            startSpin();

        }


        @Override
        protected void onPostExecute(String state) {

            adapter.notifyDataSetChanged();
            stopSpin();
//            Toast.makeText(getContext(),"Update",Toast.LENGTH_SHORT).show();
            super.onPostExecute(null);
        }


    }

    public class UpdateTimerTask extends TimerTask{

        @Override
        public void run() {
            /*for (PackageNetwork packageNetwork : appsInfo) {
                packageNetwork.updateNetworkDetails();
            }
            Collections.sort(appsInfo, comparatorReceived);
            Collections.reverse(appsInfo);
            adapter.notifyDataSetChanged();
            Toast.makeText(getContext(),"Update",Toast.LENGTH_SHORT).show();*/

        }
    }
}
