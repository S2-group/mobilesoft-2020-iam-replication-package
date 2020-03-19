/*
* Copyright (C) 2015 Ayelen Chavez y Joaquín Rinaudo
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*
* Contributors:
*
* Ayelen Chavez ashy.on.line@gmail.com
* Joaquin Rinaudo jmrinaudo@gmail.com
*
*/
package com.codingbad.vpntoggle.fragment;

import com.codingbad.library.fragment.AbstractFragment;
import com.codingbad.vpntoggle.R;
import com.codingbad.vpntoggle.adapter.ItemsAdapter;
import com.codingbad.vpntoggle.model.ApplicationItem;
import com.codingbad.vpntoggle.model.ApplicationsStatus;

import android.Manifest;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import roboguice.inject.InjectView;

/**
 * Created by ayi on 6/26/15.
 */
public class ApplicationsListFragment extends AbstractFragment<ApplicationsListFragment.Callbacks>
        implements View.OnClickListener, SearchView.OnQueryTextListener {

    private static final String LIST_STATE = "listState";

    private static final String SEARCH_STATE = "searchState";

    @InjectView(R.id.fragment_list_recyclerview)
    private RecyclerView recyclerView;

    private LinearLayoutManager layoutManager;

    private ApplicationsStatus applications;

    private ItemsAdapter adapter;

    private List<ApplicationItem> searchItems;

    private SearchView searchView;

    public static Fragment newInstance() {
        return new ApplicationsListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_applicationslist, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRecyclerView();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_apply) {
            applications.apply();
            callbacks.onChangesApplied(applications.getApplicationItems());
            Snackbar.make(getActivity().findViewById(android.R.id.content),
                    "Changes has been applied", Snackbar.LENGTH_LONG)
                    .setAction("Undo", this)
                    .setActionTextColor(getResources().getColor(R.color.accent))
                    .show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();

        if (applications == null) {
            List<ApplicationItem> allApplications = getDeviceApplications();

            List<ApplicationItem> items = callbacks.getApplicationsSavedStatus();
            int index;
            for (ApplicationItem applicationItem : items) {
                index = allApplications.indexOf(applicationItem);
                if (index != -1) {
                    allApplications.get(index).setState(applicationItem.getState());
                }
            }
            applications = new ApplicationsStatus(allApplications);
        }

        List<ApplicationItem> search = null;

        // searchItems stored searched values when device was rotated
        if (searchItems != null) {
            search = searchItems;
            searchItems = null;
        } else if (adapter != null) {
            // adapter is alive if fragment was not destroyed - app minimized, for example
            search = adapter.getSearchApplicationItems();
        }

        // if still null, show all the applications
        if (search == null) {
            search = applications.getApplicationItems();
        }

        adapter = new ItemsAdapter();

        adapter.setItems(search);
        recyclerView.setAdapter(adapter);
    }

    private void setupRecyclerView() {
        recyclerView.setHasFixedSize(true);
        // set layout manager which positions items in the screen
        layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            ArrayList<ApplicationItem> applications = savedInstanceState
                    .getParcelableArrayList(LIST_STATE);
            this.applications = new ApplicationsStatus(applications);
            searchItems = savedInstanceState.getParcelableArrayList(SEARCH_STATE);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (applications != null) {
            outState.putParcelableArrayList(LIST_STATE,
                    (ArrayList<? extends Parcelable>) applications.getApplicationItems());
        }

        if (adapter != null) {
            outState.putParcelableArrayList(SEARCH_STATE,
                    (ArrayList<? extends Parcelable>) adapter.getSearchApplicationItems());
        }
    }

    private ArrayList<ApplicationItem> getDeviceApplications() {
        final PackageManager packageManager = getActivity().getPackageManager();
        List<ApplicationInfo> installedApplications = packageManager
                .getInstalledApplications(PackageManager.GET_META_DATA);

        Map<Integer, ApplicationItem> applicationItemMap = new HashMap<Integer, ApplicationItem>();
        for (ApplicationInfo applicationInfo : installedApplications) {
            if (PackageManager.PERMISSION_GRANTED == packageManager
                    .checkPermission(Manifest.permission.INTERNET, applicationInfo.packageName)) {
                String appName = (String) (applicationInfo != null ? packageManager
                        .getApplicationLabel(applicationInfo) : "(unknown)");
                Uri appIconUri = null;

                if (applicationInfo.icon != 0) {
                    appIconUri = Uri.parse("android.resource://" + applicationInfo.packageName + "/"
                            + applicationInfo.icon);
                }

                int appUid = applicationInfo.uid;

                if (applicationItemMap.containsKey(appUid)) {
                    ApplicationItem applicationItem = applicationItemMap.get(appUid);
                    applicationItem.addApplication(appName);
                    applicationItemMap.put(appUid, applicationItem);
                } else {
                    applicationItemMap
                            .put(appUid, new ApplicationItem(appIconUri, appName, appUid));
                }
            }
        }

        return new ArrayList<ApplicationItem>(applicationItemMap.values());
    }

    @Override
    public void onClick(View v) {
        // UNDO apply
        searchView.setQuery("", false);
        searchView.clearFocus();
        callbacks.onChangesApplied(applications.undo());
        adapter = new ItemsAdapter();
        adapter.addItemList(applications.getApplicationItems());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        final List<ApplicationItem> filteredModelList = filter(applications.getApplicationItems(),
                query);
        adapter.animateTo(filteredModelList);
        recyclerView.scrollToPosition(0);
        return true;
    }

    private List<ApplicationItem> filter(List<ApplicationItem> items, String query) {
        query = query.toLowerCase();

        final List<ApplicationItem> filteredApplicationItems = new ArrayList<>();
        for (ApplicationItem applicationItem : items) {
            if (applicationItem.getApplicationName().toLowerCase().contains(query)) {
                filteredApplicationItems.add(applicationItem);
            }
        }
        return filteredApplicationItems;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.menu_main, menu);
        // Retrieve the SearchView and plug it into SearchManager
        searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        searchView.setOnQueryTextListener(this);
    }

    public interface Callbacks {

        void onChangesApplied(List<ApplicationItem> applications);

        List<ApplicationItem> getApplicationsSavedStatus();
    }
}
