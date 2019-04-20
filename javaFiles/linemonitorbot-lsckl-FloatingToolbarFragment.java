package com.kovaciny.linemonitorbot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;
import static com.kovaciny.linemonitorbot.MainActivity.DEBUG;

public class FloatingToolbarFragment extends Fragment implements View.OnClickListener {

    private ImageButton mImgBtn_calculator;
    private ImageButton mImgBtn_flashlight;
    private ImageButton mImgBtn_pomodroido;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.floating_toolbar_fragment, container, false);
        
        mImgBtn_calculator = (ImageButton) rootView.findViewById(R.id.btn_calculator);
        mImgBtn_calculator.setOnClickListener(this);
        
        mImgBtn_flashlight = (ImageButton) rootView.findViewById(R.id.btn_flashlight);
        mImgBtn_flashlight.setOnClickListener(this);        

        if (DEBUG) {
            mImgBtn_pomodroido = (ImageButton) rootView.findViewById(R.id.btn_pomodroido);
            mImgBtn_pomodroido.setOnClickListener(this);
            mImgBtn_pomodroido.setVisibility(ImageButton.VISIBLE);
        }
        
        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case (R.id.btn_calculator):
                if (!launchAppByName("calcul", "calc", "calculator", "")) {
                    Toast.makeText(getActivity(), "No calculator app found. Try downloading a different calculator.", Toast.LENGTH_LONG).show();
                }
                break;
            case (R.id.btn_flashlight):
                if (!launchAppByName("flashlight", "", "flashlight", "com.devuni.flashlight")) {
                    Toast.makeText(getActivity(), "No flashlight app found. Try downloading the suggested flashlight app from the tips page.", Toast.LENGTH_LONG).show();
                }
                break;
            case (R.id.btn_pomodroido):
                launchAppByName("pomodroido", "net.artifix.pomodroido", "pomodroido", "net.artifix.pomodroido.free");
                break;
            default:
        }
    }

    /*
     * Attempts to launch a type of app by looking at its package name and app name. 
     * Returns whether it found an app to launch or not.
     */
    private boolean launchAppByName(String nameMustContainThis, String bestNameWouldStartWithThis, String appIsCalledThis,
            String specificPackageIfPresent) {
        final PackageManager pm = getActivity().getPackageManager();
        Intent intent = pm.getLaunchIntentForPackage(specificPackageIfPresent);
        if (intent == null) {
            //search the list of packages for some other matching app.
            ArrayList<HashMap<String,Object>> items = new ArrayList<HashMap<String,Object>>();
            List<PackageInfo> packs = pm.getInstalledPackages(0);
            for (PackageInfo pi : packs) {
                String currentPackName = pi.packageName.toString().toLowerCase(Locale.US);
                if( currentPackName.contains(nameMustContainThis)){
                    HashMap<String, Object> map = new HashMap<String, Object>();
                    map.put("appName", pi.applicationInfo.loadLabel(pm));
                    map.put("packageName", pi.packageName);
                    items.add(map);
                }
            }
            if (items.size() >= 1) {
                //Search the list for the first calculator whose appname starts with your preferred string.
                //If you don't find one, take the first app in the list.
                String packageName = "";
                boolean found = false;
                for (HashMap<String, Object> hm : items) {
                    if (!found) {
                        String appName = (String) hm.get("appName");
                        if (appName.toLowerCase(Locale.US).startsWith(bestNameWouldStartWithThis)) {
                            packageName = (String) hm.get("packageName");
                            found = true;
                        }
                    }
                }
                if (!found) {
                    packageName = (String) items.get(0).get("packageName");
                }
                intent = pm.getLaunchIntentForPackage(packageName);
            }
        }
        
        if (intent != null) {
            startActivity(intent);
            return true;
        } else return false;
    }

}
