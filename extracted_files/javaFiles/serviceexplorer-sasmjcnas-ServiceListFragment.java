/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 John Schember <john@nachtimwald.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.nachtimwald.android.serviceexplorer;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ServiceListFragment extends Fragment implements MainActivity.FragmentFilterer {

    private static final String STATE_LISTPOS = "listpos";
    private SimpleAdapter mServicesAdapter;

    public static ServiceListFragment newInstance(boolean allServices) {
        ServiceListFragment f = new ServiceListFragment();
        Bundle args = new Bundle();
        args.putBoolean("allServices", allServices);
        f.setArguments(args);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_servicelist, container, false);
        ListView lv = (ListView) view.findViewById(R.id.serviceList);

        List<Map<String, String>> serviceData = new ArrayList<>();

        if (getArguments().getBoolean("allServices")) {
            for (PackageInfo packageInfo : getActivity().getPackageManager().getInstalledPackages(PackageManager.GET_SERVICES)) {
                if (packageInfo.services == null) {
                    continue;
                }
                for (ServiceInfo serviceInfo : packageInfo.services) {
                    Map<String, String> datum = new HashMap<>();
                    datum.put("name", serviceInfo.name);
                    datum.put("package", serviceInfo.packageName);
                    datum.put("search", serviceInfo.name.replace(".", " ").replace("$", " "));
                    serviceData.add(datum);
                }
            }
        } else {
            ActivityManager activityManager = (ActivityManager) getActivity().getSystemService(Context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningServiceInfo> runningServiceInfos = activityManager.getRunningServices(Integer.MAX_VALUE);
            for (ActivityManager.RunningServiceInfo info : runningServiceInfos) {
                Map<String, String> datum = new HashMap<>();
                datum.put("name", info.service.getClassName());
                datum.put("package", info.service.getPackageName());
                datum.put("search", info.service.getClassName().replace(".", " ").replace("$", " "));
                serviceData.add(datum);
            }
        }

        mServicesAdapter = new SimpleAdapter(getActivity(), serviceData, android.R.layout.simple_list_item_2, new String[]{"name", "package", "search"}, new int[]{android.R.id.text1, android.R.id.text2, 0});
        lv.setAdapter(mServicesAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getActivity(), DetailActivity.class);
                i.putExtra("name", ((HashMap<String, String>) parent.getItemAtPosition(position)).get("name"));
                i.putExtra("package", ((HashMap<String, String>) parent.getItemAtPosition(position)).get("package"));
                startActivity(i);
            }
        });

        if (savedInstanceState != null) {
            lv.onRestoreInstanceState(savedInstanceState.getParcelable(STATE_LISTPOS));
        }

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putParcelable(STATE_LISTPOS, ((ListView) getView().findViewById(R.id.serviceList)).onSaveInstanceState());
    }

    public void doFilter(String filter) {
        mServicesAdapter.getFilter().filter(filter);
    }
}