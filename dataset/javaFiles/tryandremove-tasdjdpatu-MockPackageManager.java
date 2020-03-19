/*
 * Copyright (c) 2015 Markus Poeschl
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.poeschl.apps.tryandremove.utils;

import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageItemInfo;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import de.poeschl.apps.tryandremove.R;
import timber.log.Timber;

/**
 * Created by Markus Pöschl on 22.12.14.
 */
public class MockPackageManager extends android.test.mock.MockPackageManager {

    private static final String MOCK_PACKAGE = "de.mock.Dummy";

    private Application app;
    private List<PackageInfo> mockedApps;

    @Inject
    public MockPackageManager(Application app) {
        this.app = app;
        Timber.v("Init mocked package manager");

        mockedApps = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            ApplicationInfo appInfo = new ApplicationInfo();

            appInfo.icon = createRandomResource();
            appInfo.packageName = MOCK_PACKAGE + i;

            PackageInfo temp = new PackageInfo();
            temp.applicationInfo = appInfo;

            temp.packageName = MOCK_PACKAGE + i;
            temp.applicationInfo.nonLocalizedLabel = temp.packageName.substring(temp.packageName.lastIndexOf('.') + 1, temp.packageName.length());
            mockedApps.add(temp);
        }
    }

    @Override
    public List<ApplicationInfo> getInstalledApplications(int flags) {
        List<ApplicationInfo> appInfos = new LinkedList<>();

        for (PackageInfo packInfo : getInstalledPackages(flags)) {
            appInfos.add(packInfo.applicationInfo);
        }

        return appInfos;
    }

    @Override
    public List<PackageInfo> getInstalledPackages(int flags) {
        return mockedApps;
    }

    @Override
    public Drawable getDefaultActivityIcon() {
        return app.getResources().getDrawable(R.drawable.abc_ic_clear_mtrl_alpha);
    }

    @Override
    public ApplicationInfo getApplicationInfo(String packageName, int flags) throws NameNotFoundException {
        ApplicationInfo result = null;

        if (packageName.contains("Added")) {
            ApplicationInfo appInfo = new ApplicationInfo();
            appInfo.icon = createRandomResource();

            appInfo.nonLocalizedLabel = packageName.substring(packageName.lastIndexOf('.') + 1, packageName.length());
            result = appInfo;

        } else {
            for (PackageInfo info : mockedApps) {
                if (info.packageName.equals(packageName)) {
                    result = info.applicationInfo;
                    break;
                }
            }
        }
        return result;
    }

    public Drawable loadItemIcon(PackageItemInfo info, ApplicationInfo appInfo) {
        return app.getResources().getDrawable(appInfo.icon);
    }

    @Override
    public Drawable getDrawable(String packageName, int resid, ApplicationInfo appInfo) {
        return app.getResources().getDrawable(appInfo.icon);
    }

    private Drawable createRandomDrawable() {
        return app.getResources().getDrawable(createRandomResource());
    }

    private int createRandomResource() {
        int iconRes = R.drawable.ic_menu_info;
        int rand = new Random().nextInt(6);

        switch (rand) {
            case 0:
                iconRes = android.R.drawable.sym_def_app_icon;
                break;
            case 1:
                iconRes = R.drawable.ic_launcher_app;
                break;
            case 2:
                iconRes = android.R.drawable.sym_call_missed;
                break;
            case 3:
                iconRes = android.R.drawable.btn_star_big_on;
                break;
            case 4:
                iconRes = android.R.drawable.presence_video_busy;
                break;
            case 5:
                iconRes = R.drawable.square_app;
                break;
        }

        return iconRes;
    }
}
