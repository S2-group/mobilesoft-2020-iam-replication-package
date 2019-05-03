package com.lge.ia.manager.remote;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.lge.ia.util.Log;
import java.util.Iterator;
import java.util.List;

public class RemotePackageHandler
  extends BroadcastReceiver
{
  private static final String ACTION_CHANGED = "com.lge.ia.action.CHANGED";
  private static final String ACTION_RESTARTED = "com.lge.ia.action.RESTARTED";
  private static final String FILTER_TEST = "test";
  private static final String KEY_PACKAGE_NAME = "PackageName";
  private static final String KEY_TASK_NAME = "TaskName";
  private static final String[] PACKAGE_FILTER_LIA_TASK = { "com.lge.ia.task" };
  private static final String TAG = "RemotePackageHandler";
  private Context context;
  private OnPackageStatusListener listener;
  
  public RemotePackageHandler() {}
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    paramContext = TAG;
    Object localObject1 = new StringBuilder("onReceive action : ");
    ((StringBuilder)localObject1).append(paramIntent.getAction());
    ((StringBuilder)localObject1).append(" = ");
    ((StringBuilder)localObject1).append(paramIntent.getDataString());
    Log.d(paramContext, ((StringBuilder)localObject1).toString());
    boolean bool = paramIntent.getAction().equals("android.intent.action.PACKAGE_ADDED");
    int j = 0;
    int k = 0;
    int m = 0;
    int i = 0;
    Object localObject2;
    if (bool)
    {
      paramContext = paramIntent.getDataString().substring(8);
      paramIntent = PACKAGE_FILTER_LIA_TASK;
      j = paramIntent.length;
      for (;;)
      {
        if (i >= j) {
          return;
        }
        if ((paramContext.startsWith(paramIntent[i])) && (!paramContext.contains("test")))
        {
          localObject1 = TAG;
          localObject2 = new StringBuilder("LIA task pacakge added. (Receiving ACTION_PACKAGE_ADDED) : ");
          ((StringBuilder)localObject2).append(paramContext);
          Log.d((String)localObject1, ((StringBuilder)localObject2).toString());
          this.listener.onAdded(paramContext);
        }
        i += 1;
      }
    }
    if (paramIntent.getAction().equals("android.intent.action.PACKAGE_REMOVED"))
    {
      paramContext = paramIntent.getDataString().substring(8);
      paramIntent = PACKAGE_FILTER_LIA_TASK;
      k = paramIntent.length;
      i = j;
      for (;;)
      {
        if (i >= k) {
          return;
        }
        if ((paramContext.startsWith(paramIntent[i])) && (!paramContext.contains("test")))
        {
          localObject1 = TAG;
          localObject2 = new StringBuilder("LIA task pacakge removed : ");
          ((StringBuilder)localObject2).append(paramContext);
          Log.d((String)localObject1, ((StringBuilder)localObject2).toString());
          this.listener.onRemoved(paramContext);
        }
        i += 1;
      }
    }
    if (paramIntent.getAction().equals("com.lge.ia.action.RESTARTED"))
    {
      paramContext = paramIntent.getStringExtra("PackageName");
      localObject1 = TAG;
      localObject2 = new StringBuilder("RESTARTED! : ");
      ((StringBuilder)localObject2).append(paramContext);
      ((StringBuilder)localObject2).append(" - ");
      ((StringBuilder)localObject2).append(paramIntent.getDataString());
      Log.d((String)localObject1, ((StringBuilder)localObject2).toString());
      paramIntent = PACKAGE_FILTER_LIA_TASK;
      j = paramIntent.length;
      i = k;
      for (;;)
      {
        if (i >= j) {
          return;
        }
        if ((paramContext.startsWith(paramIntent[i])) && (!paramContext.contains("test")))
        {
          localObject1 = TAG;
          localObject2 = new StringBuilder("LIA task pacakge restarted. (Receiving Restarted Intent) : ");
          ((StringBuilder)localObject2).append(paramContext);
          Log.d((String)localObject1, ((StringBuilder)localObject2).toString());
          this.listener.onRestarted(paramContext);
        }
        i += 1;
      }
    }
    if (paramIntent.getAction().equals("com.lge.ia.action.CHANGED"))
    {
      paramContext = paramIntent.getStringExtra("PackageName");
      localObject1 = paramIntent.getStringExtra("TaskName");
      localObject2 = TAG;
      StringBuilder localStringBuilder = new StringBuilder("CHANGED! : ");
      localStringBuilder.append(paramContext);
      localStringBuilder.append(", ");
      localStringBuilder.append((String)localObject1);
      localStringBuilder.append(" - ");
      localStringBuilder.append(paramIntent.getDataString());
      Log.d((String)localObject2, localStringBuilder.toString());
      paramIntent = PACKAGE_FILTER_LIA_TASK;
      j = paramIntent.length;
      i = m;
      for (;;)
      {
        if (i >= j) {
          return;
        }
        if ((paramContext.startsWith(paramIntent[i])) && (!paramContext.contains("test")))
        {
          localObject2 = TAG;
          localStringBuilder = new StringBuilder("LIA task property changed. (Receiving Changed Intent) : ");
          localStringBuilder.append(paramContext);
          localStringBuilder.append(", ");
          localStringBuilder.append((String)localObject1);
          Log.d((String)localObject2, localStringBuilder.toString());
          this.listener.onChanged(paramContext);
        }
        i += 1;
      }
    }
  }
  
  public void register(Context paramContext, OnPackageStatusListener paramOnPackageStatusListener)
  {
    this.listener = paramOnPackageStatusListener;
    this.context = paramContext;
    paramContext = new IntentFilter();
    paramContext.addAction("android.intent.action.PACKAGE_ADDED");
    paramContext.addAction("android.intent.action.PACKAGE_REMOVED");
    paramContext.addDataScheme("package");
    this.context.registerReceiver(this, paramContext);
    paramContext = new IntentFilter();
    paramContext.addAction("com.lge.ia.action.RESTARTED");
    this.context.registerReceiver(this, paramContext);
    paramContext = new IntentFilter();
    paramContext.addAction("com.lge.ia.action.CHANGED");
    this.context.registerReceiver(this, paramContext);
    paramContext = this.context.getPackageManager();
    int i = 0;
    paramContext = paramContext.getInstalledPackages(0);
    paramOnPackageStatusListener = PACKAGE_FILTER_LIA_TASK;
    int j = paramOnPackageStatusListener.length;
    if (i >= j) {
      return;
    }
    String str1 = paramOnPackageStatusListener[i];
    Iterator localIterator = paramContext.iterator();
    for (;;)
    {
      if (!localIterator.hasNext())
      {
        i += 1;
        break;
      }
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      if ((localPackageInfo.packageName.startsWith(str1)) && (!localPackageInfo.packageName.contains("test")))
      {
        String str2 = TAG;
        StringBuilder localStringBuilder = new StringBuilder("LIA task pacakge added(Using PackageManager) : ");
        localStringBuilder.append(localPackageInfo.packageName);
        Log.d(str2, localStringBuilder.toString());
        this.listener.onAdded(localPackageInfo.packageName);
      }
    }
  }
  
  public void unregister()
  {
    this.context.unregisterReceiver(this);
    this.context = null;
    Log.d(TAG, "LIA task package listening finished");
  }
  
  public static abstract interface OnPackageStatusListener
  {
    public abstract void onAdded(String paramString);
    
    public abstract void onChanged(String paramString);
    
    public abstract void onRemoved(String paramString);
    
    public abstract void onRestarted(String paramString);
  }
}
