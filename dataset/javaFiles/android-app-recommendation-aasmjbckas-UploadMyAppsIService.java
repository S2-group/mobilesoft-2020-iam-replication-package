package br.com.kosawalabs.apprecommendation.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.kosawalabs.apprecommendation.R;
import br.com.kosawalabs.apprecommendation.data.DataCallback;
import br.com.kosawalabs.apprecommendation.data.DataError;
import br.com.kosawalabs.apprecommendation.data.disk.TokenDiskRepository;
import br.com.kosawalabs.apprecommendation.data.network.AppNetworkRepository;
import br.com.kosawalabs.apprecommendation.data.pojo.PackageName;

public class UploadMyAppsIService extends IntentService {
    private static final String TAG = UploadMyAppsIService.class.getSimpleName();

    private static final String ACTION_UPLOAD_APPS = "br.com.kosawalabs.apprecommendation.service.action.UPLOAD_APPS";

    private AppNetworkRepository appNetworkRepository;

    public UploadMyAppsIService() {
        super("UploadMyAppsIService");
    }

    public static void startActionUploadApps(Context context) {
        Intent intent = new Intent(context, UploadMyAppsIService.class);
        intent.setAction(ACTION_UPLOAD_APPS);
        context.startService(intent);
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        TokenDiskRepository tokenRepository = new TokenDiskRepository(this);
        appNetworkRepository = new AppNetworkRepository(tokenRepository.getToken());
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_UPLOAD_APPS.equals(action)) {
                handleActionUploadMyApps();
            }
        }
    }

    private void handleActionUploadMyApps() {
        final PackageManager pm = getPackageManager();
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);

        int count = 0;
        List<PackageName> packageNames = new ArrayList<>();
        for (ApplicationInfo packageInfo : packages) {
            if (isNotInBlackList(packageInfo)) {
                count++;
                Log.d(TAG, "Installed package :" + packageInfo.packageName);
                packageNames.add(new PackageName(packageInfo.packageName));
            }
        }
        Log.d(TAG, getString(R.string.toast_packages_found, count));
        sendPackages(packageNames);
    }

    private void sendPackages(List<PackageName> packageNames) {
        appNetworkRepository.sendMyApps(packageNames, new DataCallback<Void>() {
            @Override
            public void onSuccess(Void result) {
                Log.d(TAG, "My Apps sent");
            }

            @Override
            public void onError(DataError error) {
                Log.d(TAG, "My Apps not sent");
            }
        });
    }

    private boolean isNotInBlackList(ApplicationInfo packageInfo) {
        return !packageInfo.packageName.startsWith("android")
                && !packageInfo.packageName.startsWith("com.android.");
    }
}
