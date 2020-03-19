package com.garena.android.gpns.strategy;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.garena.android.gpns.utility.AppLogger;
import java.util.Iterator;
import java.util.List;

public class CompetitiveBootStrategy
  extends BootStrategy
{
  public static final int INTENT_ACTION_COMMAND_KILL = 2;
  public static final int INTENT_ACTION_COMMAND_QUERY = 1;
  public static final String INTENT_ACTION_KEY = "_action";
  public static final String INTENT_COMPONENT_NAME = "component";
  public static final String INTENT_PROCESS_ID = "process_id";
  public static final String INTENT_QUERY_INIT = "com.garena.android.gpns.enquiry";
  public static final String INTENT_QUERY_RESPONSE = "com.garena.android.gpns.check";
  public static final String INTENT_QUERY_VERSION = "query_version";
  public static final String INTENT_STATUS = "status_is_running";
  private String candidatePackage = "";
  private int count;
  IntentFilter filterResponse = new IntentFilter("com.garena.android.gpns.check");
  private Runnable finalCheck = new Runnable()
  {
    public void run()
    {
      if (CompetitiveBootStrategy.this.count > 0)
      {
        AppLogger.d("execute final check:" + CompetitiveBootStrategy.this.count);
        CompetitiveBootStrategy.access$002(CompetitiveBootStrategy.this, 1);
        Intent localIntent = new Intent();
        localIntent.putExtra("status_is_running", localIntent);
        localIntent.putExtra("query_version", CompetitiveBootStrategy.this.maxVersion);
        localIntent.putExtra("component", new ComponentName(CompetitiveBootStrategy.this.candidatePackage, "com.garena.android.gpns.GNotificationService"));
        CompetitiveBootStrategy.this.onResponse(CompetitiveBootStrategy.this.mContext, localIntent);
      }
    }
  };
  private Handler handler;
  private boolean isWinnerRunning = false;
  BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      AppLogger.d("receive feedback!");
      CompetitiveBootStrategy.this.onResponse(paramAnonymousContext, paramAnonymousIntent);
    }
  };
  private Context mContext;
  private int maxVersion = 0;
  private ComponentName winningCandidateComponent;
  private int winningVersion = 0;
  
  public CompetitiveBootStrategy(BootStrategy.BootStrategyListener paramBootStrategyListener)
  {
    this.mListener = paramBootStrategyListener;
  }
  
  public static boolean isSuperior(String paramString1, String paramString2)
  {
    return paramString1.compareTo(paramString2) > 0;
  }
  
  private void killService(Context paramContext, ComponentName paramComponentName)
  {
    AppLogger.i("Attempt to kill:" + paramComponentName.flattenToString());
    Intent localIntent = new Intent("com.garena.android.gpns.enquiry");
    localIntent.setPackage(paramComponentName.getPackageName());
    localIntent.putExtra("_action", 2);
    paramContext.sendBroadcast(localIntent);
  }
  
  protected void fireStrategy(Context paramContext)
  {
    Intent localIntent;
    if (paramContext != null)
    {
      this.mContext = paramContext;
      localIntent = new Intent("com.garena.android.gpns.enquiry");
      localIntent.putExtra("_action", 1);
      localIntent.putExtra("component", paramContext.getPackageName());
      this.count = paramContext.getPackageManager().queryBroadcastReceivers(localIntent, 64).size();
      if (this.count == 0) {
        this.mListener.onStrategySuccess();
      }
    }
    else
    {
      return;
    }
    Iterator localIterator = paramContext.getPackageManager().getInstalledApplications(128).iterator();
    while (localIterator.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
      if (localApplicationInfo.metaData != null)
      {
        Integer localInteger = Integer.valueOf(localApplicationInfo.metaData.getInt("com.garena.sdk.push.version", -1));
        if ((localInteger.intValue() > this.maxVersion) || ((localInteger.intValue() == this.maxVersion) && (isSuperior(localApplicationInfo.packageName, this.candidatePackage))))
        {
          AppLogger.d("we have a better choice now:" + localApplicationInfo.packageName);
          this.maxVersion = localInteger.intValue();
          this.candidatePackage = localApplicationInfo.packageName;
        }
        else
        {
          AppLogger.d("service_version:" + localInteger + " " + localApplicationInfo.packageName);
        }
      }
    }
    this.handler = new Handler(Looper.getMainLooper());
    this.handler.postDelayed(this.finalCheck, 10000L);
    paramContext.getApplicationContext().registerReceiver(this.mBroadcastReceiver, this.filterResponse);
    paramContext.sendBroadcast(localIntent);
  }
  
  protected void onResponse(Context paramContext, Intent paramIntent)
  {
    this.count -= 1;
    AppLogger.d("receive query response on" + paramContext.getPackageName() + " count:" + this.count);
    boolean bool = paramIntent.getBooleanExtra("status_is_running", false);
    int i = paramIntent.getIntExtra("query_version", 0);
    ComponentName localComponentName = (ComponentName)paramIntent.getParcelableExtra("component");
    StringBuilder localStringBuilder = new StringBuilder().append("responder: ").append(localComponentName).append(" v:").append(i);
    if (bool)
    {
      paramIntent = " running";
      AppLogger.d(paramIntent);
      if (!bool) {
        break label428;
      }
      if (i >= 4) {
        break label252;
      }
      killService(paramContext, localComponentName);
      label145:
      if (this.count == 0)
      {
        AppLogger.i("get all feedback");
        this.handler.removeCallbacks(this.finalCheck);
        if (!this.isWinnerRunning) {
          break label652;
        }
        AppLogger.i("start & bind service:" + this.winningCandidateComponent.flattenToString());
        this.mListener.onStrategyFailure(this.winningCandidateComponent);
      }
    }
    for (;;)
    {
      this.mListener = null;
      paramContext.getApplicationContext().unregisterReceiver(this.mBroadcastReceiver);
      this.handler = null;
      this.mContext = null;
      return;
      paramIntent = " not running";
      break;
      label252:
      if (i > this.winningVersion)
      {
        if ((this.isWinnerRunning) && (this.winningCandidateComponent != null)) {
          killService(paramContext, this.winningCandidateComponent);
        }
        this.winningVersion = i;
        this.winningCandidateComponent = localComponentName;
        AppLogger.d("a winner is:" + this.winningCandidateComponent.flattenToString());
        this.isWinnerRunning = true;
        break label145;
      }
      if (isSuperior(localComponentName.getPackageName(), this.winningCandidateComponent.getPackageName()))
      {
        if (this.isWinnerRunning)
        {
          killService(paramContext, this.winningCandidateComponent);
          this.isWinnerRunning = false;
        }
        this.winningVersion = i;
        this.winningCandidateComponent = localComponentName;
        AppLogger.d("b winner is:" + this.winningCandidateComponent.flattenToString());
        this.isWinnerRunning = true;
        break label145;
      }
      killService(paramContext, localComponentName);
      break label145;
      label428:
      if (i > this.winningVersion)
      {
        if ((this.isWinnerRunning) && (this.winningCandidateComponent != null)) {
          killService(paramContext, this.winningCandidateComponent);
        }
        this.winningCandidateComponent = localComponentName;
        AppLogger.d("c winner is:" + this.winningCandidateComponent.flattenToString());
        this.winningVersion = i;
        this.isWinnerRunning = false;
        break label145;
      }
      if (i != this.winningVersion) {
        break label145;
      }
      if (this.winningCandidateComponent == null)
      {
        this.winningCandidateComponent = localComponentName;
        AppLogger.d("c winner is:" + this.winningCandidateComponent.flattenToString());
        this.winningVersion = i;
        this.isWinnerRunning = false;
        break label145;
      }
      if (!isSuperior(localComponentName.getPackageName(), this.winningCandidateComponent.getPackageName())) {
        break label145;
      }
      if (this.isWinnerRunning) {
        killService(paramContext, this.winningCandidateComponent);
      }
      this.winningCandidateComponent = localComponentName;
      AppLogger.d("c winner is:" + this.winningCandidateComponent.flattenToString());
      this.winningVersion = i;
      this.isWinnerRunning = false;
      break label145;
      label652:
      AppLogger.i("no service running");
      if ((this.winningVersion > 0) && (!paramContext.getPackageName().equals(this.winningCandidateComponent.getPackageName())))
      {
        AppLogger.i("found a better candidate rather than myself " + paramContext.getPackageName());
        AppLogger.i("start & bind service:" + this.winningCandidateComponent.flattenToString());
        paramIntent = new Intent();
        paramIntent.setComponent(this.winningCandidateComponent);
        paramContext.startService(paramIntent);
        this.mListener.onStrategyFailure(this.winningCandidateComponent);
      }
      else
      {
        AppLogger.i("I am the candidate to run " + paramContext.getPackageName());
        this.mListener.onStrategySuccess();
      }
    }
  }
}
