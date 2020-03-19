package com.amazon.alexa.accessorykit;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.annotation.VisibleForTesting;
import android.text.TextUtils;
import com.amazon.alexa.accessory.Accessories;
import com.amazon.alexa.accessory.AccessorySessionListener;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.protocol.Common.ErrorCode;
import com.amazon.alexa.accessory.repositories.state.StateFeature;
import com.amazon.alexa.accessorykit.metrics.AccessoryMetrics;
import com.dee.app.metrics.MetricsService;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import io.reactivex.Completable;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;

public class AccessoryModule
  extends ReactContextBaseJavaModule
{
  private static final String MODULE_NAME = "AbaSettingsBridge";
  private final Accessories accessories;
  private final AccessoryInteractor accessoryInteractor;
  private final AccessoryActivityLauncher activityLauncher;
  private final AccessorySessionListener globalSessionListener;
  private final Handler mainThreadHandler;
  private final AccessoryMetrics metricsHelper;
  private final ModelTransformer modelTransformer;
  private final AccessoryScanManager scanManager;
  
  @VisibleForTesting
  AccessoryModule(ReactApplicationContext paramReactApplicationContext, Accessories paramAccessories, Handler paramHandler, AccessoryInteractor paramAccessoryInteractor, AccessoryScanManager paramAccessoryScanManager, ModelTransformer paramModelTransformer, AccessoryActivityLauncher paramAccessoryActivityLauncher, AccessorySessionListener paramAccessorySessionListener, MetricsService paramMetricsService)
  {
    super(paramReactApplicationContext);
    this.accessories = paramAccessories;
    this.accessoryInteractor = paramAccessoryInteractor;
    this.mainThreadHandler = paramHandler;
    this.modelTransformer = paramModelTransformer;
    this.scanManager = paramAccessoryScanManager;
    this.activityLauncher = paramAccessoryActivityLauncher;
    this.globalSessionListener = paramAccessorySessionListener;
    this.metricsHelper = new AccessoryMetrics(paramMetricsService);
  }
  
  public AccessoryModule(ReactApplicationContext paramReactApplicationContext, Accessories paramAccessories, MetricsService paramMetricsService)
  {
    super(paramReactApplicationContext);
    NativeContainerProvider localNativeContainerProvider = new NativeContainerProvider();
    this.accessories = paramAccessories;
    this.accessoryInteractor = new AccessoryInteractor(paramAccessories);
    this.mainThreadHandler = new Handler(paramReactApplicationContext.getMainLooper());
    this.activityLauncher = new AccessoryActivityLauncher(paramReactApplicationContext);
    this.modelTransformer = new ModelTransformer(localNativeContainerProvider);
    this.scanManager = new AccessoryScanManager(paramReactApplicationContext, paramAccessories.getScanner(), this.modelTransformer, this.accessoryInteractor);
    this.globalSessionListener = new GlobalSessionListener(paramReactApplicationContext, localNativeContainerProvider);
    this.metricsHelper = new AccessoryMetrics(paramMetricsService);
  }
  
  private static Completable errorCodeToCompletable(Common.ErrorCode paramErrorCode)
  {
    if (paramErrorCode == Common.ErrorCode.SUCCESS) {
      return Completable.complete();
    }
    return Completable.error(new IOException("Error code wasn't success"));
  }
  
  private boolean isPackageInstalledAndEnabled(PackageManager paramPackageManager, String paramString)
  {
    paramPackageManager = paramPackageManager.getInstalledApplications(0).iterator();
    while (paramPackageManager.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)paramPackageManager.next();
      if ((localApplicationInfo.packageName.equals(paramString)) && (localApplicationInfo.enabled)) {
        return true;
      }
    }
    return false;
  }
  
  @ReactMethod
  public void awaitUntilAccessoryStateBooleanIsTrue(String paramString, int paramInt, Promise paramPromise)
  {
    this.mainThreadHandler.post(AccessoryModule..Lambda.30.lambdaFactory$(this, paramString, paramPromise, paramInt));
  }
  
  @ReactMethod
  public void awaitUntilDiscoverableOverBluetoothClassic(String paramString, Promise paramPromise)
  {
    awaitUntilAccessoryStateBooleanIsTrue(paramString, StateFeature.BLUETOOTH_CLASSIC_DISCOVERABLE.toInteger(), paramPromise);
  }
  
  @ReactMethod
  public void completeSetup(String paramString, boolean paramBoolean, Promise paramPromise)
  {
    this.mainThreadHandler.post(AccessoryModule..Lambda.17.lambdaFactory$(this, paramString, paramPromise, paramBoolean));
  }
  
  @ReactMethod
  public void createSession(String paramString, Promise paramPromise)
  {
    this.mainThreadHandler.post(AccessoryModule..Lambda.19.lambdaFactory$(this, paramString, paramPromise));
  }
  
  @ReactMethod
  public void deregisterDevice(String paramString, Promise paramPromise)
  {
    paramPromise.resolve(Boolean.valueOf(true));
  }
  
  @ReactMethod
  public void doAssistantOverride(String paramString, boolean paramBoolean, Promise paramPromise)
  {
    this.mainThreadHandler.post(AccessoryModule..Lambda.14.lambdaFactory$(this, paramString, paramPromise, paramBoolean));
  }
  
  @ReactMethod
  public void getAccessories(Promise paramPromise)
  {
    this.mainThreadHandler.post(AccessoryModule..Lambda.5.lambdaFactory$(this, paramPromise));
  }
  
  @ReactMethod
  public void getAccessoryStateBoolean(String paramString, int paramInt, Promise paramPromise)
  {
    this.mainThreadHandler.post(AccessoryModule..Lambda.26.lambdaFactory$(this, paramString, paramPromise, paramInt));
  }
  
  @ReactMethod
  public void getAccessoryStateInteger(String paramString, int paramInt, Promise paramPromise)
  {
    this.mainThreadHandler.post(AccessoryModule..Lambda.27.lambdaFactory$(this, paramString, paramPromise, paramInt));
  }
  
  @ReactMethod
  public void getBTInformation(String paramString, Promise paramPromise)
  {
    this.mainThreadHandler.post(AccessoryModule..Lambda.7.lambdaFactory$(this, paramString, paramPromise));
  }
  
  @Nullable
  public Map<String, Object> getConstants()
  {
    return ImmutableMap.builder().put("eventName", "ama-accessory-found").put("connectionEventName", "ama-accessory-connection").build();
  }
  
  @ReactMethod
  public void getFirmwareInformation(String paramString, Promise paramPromise)
  {
    this.mainThreadHandler.post(AccessoryModule..Lambda.8.lambdaFactory$(this, paramString, paramPromise));
  }
  
  public String getName()
  {
    return "AbaSettingsBridge";
  }
  
  @ReactMethod
  public void getNeedsSetup(String paramString, Promise paramPromise)
  {
    this.mainThreadHandler.post(AccessoryModule..Lambda.15.lambdaFactory$(this, paramString, paramPromise));
  }
  
  @ReactMethod
  public void getRegisteredName(String paramString, Promise paramPromise)
  {
    paramPromise.resolve("John Doe");
  }
  
  @ReactMethod
  public void getSession(String paramString, Promise paramPromise)
  {
    this.mainThreadHandler.post(AccessoryModule..Lambda.18.lambdaFactory$(this, paramString, paramPromise));
  }
  
  @ReactMethod
  public void isConnected(String paramString, Promise paramPromise)
  {
    this.mainThreadHandler.post(AccessoryModule..Lambda.25.lambdaFactory$(this, paramString, paramPromise));
  }
  
  @ReactMethod
  public void isDiscoverableOverBluetoothClassic(String paramString, Promise paramPromise)
  {
    getAccessoryStateBoolean(paramString, StateFeature.BLUETOOTH_CLASSIC_DISCOVERABLE.toInteger(), paramPromise);
  }
  
  @ReactMethod
  public void launchCustomIntent(String paramString1, String paramString2, Promise paramPromise)
  {
    Preconditions.notNull(paramString1, "packageName");
    PackageManager localPackageManager = getReactApplicationContext().getPackageManager();
    if (!isPackageInstalledAndEnabled(localPackageManager, paramString1))
    {
      paramPromise.reject(new IllegalArgumentException("Application not installed " + paramString1));
      return;
    }
    if (TextUtils.isEmpty(paramString2))
    {
      paramString2 = localPackageManager.getLaunchIntentForPackage(paramString1);
      if (paramString2 != null)
      {
        this.activityLauncher.startActivity(paramString2, paramPromise);
        return;
      }
      paramPromise.reject(new IllegalArgumentException("No Intent to launch " + paramString1));
      return;
    }
    Intent localIntent = new Intent().setPackage(paramString1).setClassName(paramString1, paramString2);
    if (localIntent.resolveActivity(localPackageManager) == null)
    {
      paramPromise.reject(new IllegalArgumentException("Failed to resolve custom intent " + paramString1 + ":" + paramString2));
      return;
    }
    this.activityLauncher.startActivity(localIntent, paramPromise);
  }
  
  @ReactMethod
  public void needsAssistantOverride(String paramString, Promise paramPromise)
  {
    this.mainThreadHandler.post(AccessoryModule..Lambda.13.lambdaFactory$(this, paramString, paramPromise));
  }
  
  @ReactMethod
  public void releaseSession(String paramString, Promise paramPromise)
  {
    this.mainThreadHandler.post(AccessoryModule..Lambda.20.lambdaFactory$(this, paramString, paramPromise));
  }
  
  @ReactMethod
  public void removeDevice(String paramString, Promise paramPromise)
  {
    this.mainThreadHandler.post(AccessoryModule..Lambda.6.lambdaFactory$(this, paramString, paramPromise));
  }
  
  @ReactMethod
  public void requestBluetoothPermissions(Promise paramPromise)
  {
    this.mainThreadHandler.post(AccessoryModule..Lambda.21.lambdaFactory$(this, paramPromise));
  }
  
  @ReactMethod
  public void requestIgnoreBatteryOptimizations(Promise paramPromise)
  {
    this.mainThreadHandler.post(AccessoryModule..Lambda.22.lambdaFactory$(this, paramPromise));
  }
  
  @ReactMethod
  public void requestTransportUpgrade(String paramString, Promise paramPromise)
  {
    this.mainThreadHandler.post(AccessoryModule..Lambda.12.lambdaFactory$(this, paramString, paramPromise));
  }
  
  @ReactMethod
  public void requestUpdateDeviceInformation(String paramString1, String paramString2, Promise paramPromise)
  {
    this.mainThreadHandler.post(AccessoryModule..Lambda.10.lambdaFactory$(this, paramString1, paramPromise, paramString2));
  }
  
  @ReactMethod
  public void scanForDevices(Promise paramPromise)
  {
    this.mainThreadHandler.post(AccessoryModule..Lambda.3.lambdaFactory$(this, paramPromise));
  }
  
  @ReactMethod
  public void sendLogs(String paramString, Promise paramPromise)
  {
    this.mainThreadHandler.post(AccessoryModule..Lambda.9.lambdaFactory$(this, paramString, paramPromise));
  }
  
  @ReactMethod
  public void setAccessoryStateBoolean(String paramString, int paramInt, boolean paramBoolean, Promise paramPromise)
  {
    this.mainThreadHandler.post(AccessoryModule..Lambda.28.lambdaFactory$(this, paramString, paramPromise, paramInt, paramBoolean));
  }
  
  @ReactMethod
  public void setAccessoryStateInteger(String paramString, int paramInt1, int paramInt2, Promise paramPromise)
  {
    this.mainThreadHandler.post(AccessoryModule..Lambda.29.lambdaFactory$(this, paramString, paramPromise, paramInt1, paramInt2));
  }
  
  @ReactMethod
  public void shouldRequestEnableBluetooth(Promise paramPromise)
  {
    this.mainThreadHandler.post(AccessoryModule..Lambda.23.lambdaFactory$(this, paramPromise));
  }
  
  @ReactMethod
  public void shouldRequestIgnoreBatteryOptimizations(Promise paramPromise)
  {
    this.mainThreadHandler.post(AccessoryModule..Lambda.24.lambdaFactory$(this, paramPromise));
  }
  
  @ReactMethod
  public void shouldTransportUpgrade(String paramString, Promise paramPromise)
  {
    this.mainThreadHandler.post(AccessoryModule..Lambda.11.lambdaFactory$(this, paramString, paramPromise));
  }
  
  @ReactMethod
  public void startConnectionEvents()
  {
    this.mainThreadHandler.post(AccessoryModule..Lambda.1.lambdaFactory$(this));
  }
  
  @ReactMethod
  public void startSetup(String paramString, Promise paramPromise)
  {
    this.mainThreadHandler.post(AccessoryModule..Lambda.16.lambdaFactory$(this, paramString, paramPromise));
  }
  
  @ReactMethod
  public void stopConnectionEvents()
  {
    this.mainThreadHandler.post(AccessoryModule..Lambda.2.lambdaFactory$(this));
  }
  
  @ReactMethod
  public void stopScan(Promise paramPromise)
  {
    this.mainThreadHandler.post(AccessoryModule..Lambda.4.lambdaFactory$(this, paramPromise));
  }
}
