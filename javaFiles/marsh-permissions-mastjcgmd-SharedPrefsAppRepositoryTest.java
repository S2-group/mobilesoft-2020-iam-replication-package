package com.guavabot.marshpermissions.data;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.guavabot.marshpermissions.domain.entity.App;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import rx.observers.TestSubscriber;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.not;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.verify;

/**
 * <p>Created by Ivan on 1/3/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class SharedPrefsAppRepositoryTest {

    @Mock
    PackageManager mPackageManager;
    @Mock
    HiddenPackages mHiddenPackages;

    private SharedPrefsAppRepository mTested;

    @Before
    public void setUp() throws Exception {
        mTested = new SharedPrefsAppRepository(mPackageManager, mHiddenPackages);

        ArrayList<PackageInfo> list = new ArrayList<>();

        PackageInfo pck1 = new PackageInfo();
        pck1.packageName = "package1";
        ApplicationInfo app1 = new ApplicationInfo();
        app1.targetSdkVersion = 22;
        given(mPackageManager.getApplicationLabel(app1)).willReturn("uno");
        pck1.applicationInfo = app1;
        list.add(pck1);

        PackageInfo pck2 = new PackageInfo();
        pck2.packageName = "package2";
        ApplicationInfo app2 = new ApplicationInfo();
        app2.targetSdkVersion = 23;
        given(mPackageManager.getApplicationLabel(app2)).willReturn("dos");
        pck2.applicationInfo = app2;
        list.add(pck2);

        PackageInfo pck3 = new PackageInfo();
        pck3.packageName = "package3";
        ApplicationInfo app3 = new ApplicationInfo();
        app3.targetSdkVersion = 24;
        given(mPackageManager.getApplicationLabel(app3)).willReturn(null);
        pck3.applicationInfo = app3;
        list.add(pck3);

        given(mPackageManager.getInstalledPackages(PackageManager.GET_PERMISSIONS))
                .willReturn(list);
        HashSet<String> hiddenAppsSet = new HashSet<>();
        hiddenAppsSet.add("package2");
        given(mHiddenPackages.get())
                .willReturn(hiddenAppsSet);
    }

    @Test
    public void shouldOnlyReturnAppsThatTargetMarshmallow() {
        TestSubscriber<List<App>> subscriber = new TestSubscriber<>();
        mTested.findAppsMarshmallow()
                .subscribe(subscriber);

        subscriber.assertValueCount(1);
        subscriber.assertCompleted();
        List<App> result = subscriber.getOnNextEvents().get(0);
        assertThat(result).containsOnly(
                new App("package2", "dos", true, Collections.emptySet()),
                new App("package3", null, false, Collections.emptySet()));
    }

    @Test
    public void shouldReturnHotUpdatesObservable() {
        TestSubscriber<Void> subscriber = new TestSubscriber<>();
        mTested.hiddenAppsUpdate()
                .subscribe(subscriber);

        subscriber.assertNoValues();
        subscriber.assertNoTerminalEvent();
    }

    @Test
    public void shouldPushUpdateWhenSettingAppHidden() {
        TestSubscriber<Void> subscriber = new TestSubscriber<>();
        mTested.hiddenAppsUpdate()
                .subscribe(subscriber);

        mTested.setAppHidden("package4")
                .subscribe();

        subscriber.assertValueCount(1);
        subscriber.assertNoTerminalEvent();
    }

    @Test
    public void shouldAddAppWhenSettingAppHidden() {
        mTested.setAppHidden("package4")
                .subscribe();

        verify(mHiddenPackages).set((Set<String>) argThat(hasItems("package4")));
    }

    @Test
    public void shouldRemoveAppWhenSettingAppNotHidden() {
        mTested.setAppNotHidden("package2")
                .subscribe();

        verify(mHiddenPackages).set((Set<String>) argThat(not(hasItems("package2"))));
    }
}