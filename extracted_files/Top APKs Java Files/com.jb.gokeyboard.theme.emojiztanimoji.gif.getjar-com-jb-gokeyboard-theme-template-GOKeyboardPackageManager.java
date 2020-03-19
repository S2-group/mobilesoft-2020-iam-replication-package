package com.jb.gokeyboard.theme.template;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.text.TextUtils;
import com.jb.gokeyboard.theme.template.statistics.BaseStatisticHelper;
import com.jb.gokeyboard.theme.template.thread.pool.MessageHandler;
import com.jb.gokeyboard.theme.template.util.AppUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class GOKeyboardPackageManager
  extends BroadcastReceiver
{
  public static final String FONT_PACKAGE_NAME_PREFIX = "com.jb.gokeyboard.font.";
  public static final String FONT_PACKAGE_NAME_PREFIX_OLD = "com.jb.gokeyboard.plugin.font.";
  public static final String FONT_STYLE = "fonts";
  public static final String OTHER_STYLE = "other";
  public static final String PAD_THEME_PACK_PREFIX = "com.jb.gokeyboard.pad.theme.";
  public static final String STICKER_PACK_PREFIX = "com.jb.gokeyboard.sticker";
  public static final String THEME_PACK_PREFIX = "com.jb.gokeyboard.theme.";
  private static GOKeyboardPackageManager sInstance = null;
  private CopyOnWriteArrayList<GoKeyboardInstallStateObserver> mGoKeyboardInstallStateObservers = new CopyOnWriteArrayList();
  private Map<String, List<PackageInfo>> mInstalledPackages = new HashMap();
  private byte[] mLock = new byte[0];
  private CopyOnWriteArrayList<PackageReceiverObserver> mPackageReceiverObservers = new CopyOnWriteArrayList();
  
  public GOKeyboardPackageManager() {}
  
  private void addPackageToList(PackageInfo paramPackageInfo)
  {
    if ((paramPackageInfo == null) || (TextUtils.isEmpty(paramPackageInfo.packageName))) {}
    String str;
    do
    {
      return;
      str = getPackageStyle(paramPackageInfo.packageName);
    } while (TextUtils.isEmpty(str));
    synchronized (this.mLock)
    {
      List localList = (List)this.mInstalledPackages.get(str);
      Object localObject = localList;
      if (localList == null)
      {
        localObject = new ArrayList();
        this.mInstalledPackages.put(str, localObject);
      }
      ((List)localObject).add(paramPackageInfo);
      return;
    }
  }
  
  public static GOKeyboardPackageManager getInstance()
  {
    try
    {
      if (sInstance == null) {
        sInstance = new GOKeyboardPackageManager();
      }
      GOKeyboardPackageManager localGOKeyboardPackageManager = sInstance;
      return localGOKeyboardPackageManager;
    }
    finally {}
  }
  
  private String getPackageStyle(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return null;
    }
    if ((paramString.startsWith("com.jb.gokeyboard.font.")) || (paramString.startsWith("com.jb.gokeyboard.plugin.font."))) {
      return "fonts";
    }
    return "other";
  }
  
  private void loadPackagesInfo(Context paramContext)
  {
    if (paramContext == null) {
      throw new IllegalArgumentException("Context cannot be null!");
    }
    synchronized (this.mLock)
    {
      Object localObject = paramContext.getPackageManager();
      paramContext = null;
      try
      {
        localObject = ((PackageManager)localObject).getInstalledPackages(0);
        paramContext = (Context)localObject;
      }
      catch (Exception localException)
      {
        for (;;)
        {
          localException.printStackTrace();
        }
      }
      if ((paramContext == null) || (paramContext.size() <= 0)) {
        return;
      }
    }
    paramContext = paramContext.iterator();
    while (paramContext.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
      ApplicationInfo localApplicationInfo = localPackageInfo.applicationInfo;
      if ((localApplicationInfo != null) && ((localApplicationInfo.flags & 0x1) == 0)) {
        addPackageToList(localPackageInfo);
      }
    }
  }
  
  private void notifyInstallStateChange(boolean paramBoolean)
  {
    synchronized (this.mGoKeyboardInstallStateObservers)
    {
      Iterator localIterator = this.mGoKeyboardInstallStateObservers.iterator();
      while (localIterator.hasNext())
      {
        GoKeyboardInstallStateObserver localGoKeyboardInstallStateObserver = (GoKeyboardInstallStateObserver)localIterator.next();
        if (localGoKeyboardInstallStateObserver != null) {
          localGoKeyboardInstallStateObserver.onGoKeyboardInstallStateChange(paramBoolean);
        }
      }
    }
  }
  
  private boolean removePackageFromList(String paramString)
  {
    Object localObject = getPackageStyle(paramString);
    if (TextUtils.isEmpty((CharSequence)localObject)) {
      return false;
    }
    synchronized (this.mLock)
    {
      localObject = (List)this.mInstalledPackages.get(localObject);
      if (localObject == null) {
        return false;
      }
    }
    Iterator localIterator = ((List)localObject).iterator();
    while (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      if ((localPackageInfo != null) && (paramString.equals(localPackageInfo.packageName)))
      {
        ((List)localObject).remove(localPackageInfo);
        return true;
      }
    }
    return false;
  }
  
  private void updateGoKeyboardInstallState(Context paramContext)
  {
    if (AppUtils.hasInstalledGOKeyBoard(paramContext))
    {
      if (!ThemeApplication.sIsInstalled) {
        notifyInstallStateChange(true);
      }
      ThemeApplication.sIsInstalled = true;
      return;
    }
    if (ThemeApplication.sIsInstalled) {
      notifyInstallStateChange(false);
    }
    ThemeApplication.sIsInstalled = false;
  }
  
  public void addInstallStateObserver(GoKeyboardInstallStateObserver paramGoKeyboardInstallStateObserver)
  {
    if (this.mGoKeyboardInstallStateObservers != null) {
      synchronized (this.mGoKeyboardInstallStateObservers)
      {
        if (!this.mGoKeyboardInstallStateObservers.contains(paramGoKeyboardInstallStateObserver)) {
          this.mGoKeyboardInstallStateObservers.add(paramGoKeyboardInstallStateObserver);
        }
        return;
      }
    }
  }
  
  public void addObserver(PackageReceiverObserver paramPackageReceiverObserver)
  {
    if (this.mPackageReceiverObservers != null) {
      synchronized (this.mPackageReceiverObservers)
      {
        if (!this.mPackageReceiverObservers.contains(paramPackageReceiverObserver)) {
          this.mPackageReceiverObservers.add(paramPackageReceiverObserver);
        }
        return;
      }
    }
  }
  
  public List<PackageInfo> getPackageList(String paramString)
  {
    synchronized (this.mLock)
    {
      paramString = (List)this.mInstalledPackages.get(paramString);
      if ((paramString == null) || (paramString.size() <= 0)) {
        return null;
      }
      paramString = new ArrayList(paramString);
      return paramString;
    }
  }
  
  public void initPackagesInfo(final Context paramContext)
  {
    MessageHandler.postRunnable(new Runnable()
    {
      public void run()
      {
        GOKeyboardPackageManager.this.loadPackagesInfo(paramContext);
      }
    });
  }
  
  public void onReceive(Context arg1, Intent paramIntent)
  {
    String str2 = paramIntent.getAction();
    String str1 = paramIntent.getData().getSchemeSpecificPart();
    if (TextUtils.isEmpty(str1)) {}
    do
    {
      PackageReceiverObserver localPackageReceiverObserver;
      do
      {
        boolean bool;
        do
        {
          return;
          updateGoKeyboardInstallState(???);
          bool = paramIntent.getBooleanExtra("android.intent.extra.REPLACING", false);
          if ((!"android.intent.action.PACKAGE_ADDED".equals(str2)) || (bool)) {
            break;
          }
          Object localObject = ???.getPackageManager();
          paramIntent = null;
          try
          {
            localObject = ((PackageManager)localObject).getPackageInfo(str1, 0);
            paramIntent = (Intent)localObject;
          }
          catch (PackageManager.NameNotFoundException localNameNotFoundException)
          {
            for (;;)
            {
              localNameNotFoundException.printStackTrace();
            }
            return;
          }
        } while (paramIntent == null);
        BaseStatisticHelper.uploadAppDistributionStatistics(???, str2, str1);
        addPackageToList(paramIntent);
        synchronized (this.mPackageReceiverObservers)
        {
          paramIntent = this.mPackageReceiverObservers.iterator();
          while (paramIntent.hasNext())
          {
            localObject = (PackageReceiverObserver)paramIntent.next();
            if (localObject != null) {
              ((PackageReceiverObserver)localObject).onPackageAdd(str1);
            }
          }
        }
        if (("android.intent.action.PACKAGE_REMOVED".equals(str2)) && (!bool))
        {
          removePackageFromList(str1);
          synchronized (this.mPackageReceiverObservers)
          {
            paramIntent = this.mPackageReceiverObservers.iterator();
            while (paramIntent.hasNext())
            {
              localPackageReceiverObserver = (PackageReceiverObserver)paramIntent.next();
              if (localPackageReceiverObserver != null) {
                localPackageReceiverObserver.onPackageRemove(str1);
              }
            }
          }
          return;
        }
      } while (!"android.intent.action.PACKAGE_REPLACED".equals(str2));
      removePackageFromList(str1);
      paramIntent = ???.getPackageManager();
      ??? = null;
      try
      {
        paramIntent = paramIntent.getPackageInfo(str1, 0);
        ??? = paramIntent;
      }
      catch (Throwable paramIntent)
      {
        for (;;)
        {
          paramIntent.printStackTrace();
        }
      }
    } while (??? == null);
    addPackageToList(???);
    synchronized (this.mPackageReceiverObservers)
    {
      paramIntent = this.mPackageReceiverObservers.iterator();
      while (paramIntent.hasNext())
      {
        localPackageReceiverObserver = (PackageReceiverObserver)paramIntent.next();
        if (localPackageReceiverObserver != null) {
          localPackageReceiverObserver.onPackageReplace(str1);
        }
      }
    }
  }
  
  public void removeInstallStateObserver(GoKeyboardInstallStateObserver paramGoKeyboardInstallStateObserver)
  {
    if (this.mGoKeyboardInstallStateObservers != null) {
      synchronized (this.mGoKeyboardInstallStateObservers)
      {
        if (this.mGoKeyboardInstallStateObservers.contains(paramGoKeyboardInstallStateObserver)) {
          this.mGoKeyboardInstallStateObservers.remove(paramGoKeyboardInstallStateObserver);
        }
        return;
      }
    }
  }
  
  public void removeObserver(PackageReceiverObserver paramPackageReceiverObserver)
  {
    if (this.mPackageReceiverObservers != null) {
      synchronized (this.mPackageReceiverObservers)
      {
        if (this.mPackageReceiverObservers.contains(paramPackageReceiverObserver)) {
          this.mPackageReceiverObservers.remove(paramPackageReceiverObserver);
        }
        return;
      }
    }
  }
  
  static abstract interface GoKeyboardInstallStateObserver
  {
    public abstract void onGoKeyboardInstallStateChange(boolean paramBoolean);
  }
  
  public static abstract interface PackageReceiverObserver
  {
    public abstract void onPackageAdd(String paramString);
    
    public abstract void onPackageRemove(String paramString);
    
    public abstract void onPackageReplace(String paramString);
  }
}
