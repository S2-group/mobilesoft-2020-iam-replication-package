package com.geocomply.rdp;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import com.geocomply.core.Resources;
import com.geocomply.util.WeakContext;
import com.newrelic.agent.android.instrumentation.AsyncTaskInstrumentation;
import com.newrelic.agent.android.tracing.Trace;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ProcessManager
{
  private static ArrayList<String> sPackageNames;
  private WeakReference<Handler> mHandler;
  
  public ProcessManager()
  {
    initializeRDPList();
  }
  
  protected static List<String> getPackageNames()
  {
    return sPackageNames;
  }
  
  private static void initializeRDPList()
  {
    sPackageNames = new ArrayList();
    if (Resources.PACKAGE_NAMES.length > 0) {
      Collections.addAll(sPackageNames, Resources.PACKAGE_NAMES);
    }
  }
  
  public List<String> createRunningRDPLIst()
  {
    Object localObject = ((ActivityManager)WeakContext.getSystemService("activity")).getRunningAppProcesses();
    ArrayList localArrayList = new ArrayList();
    if (((List)localObject).size() > 0)
    {
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)((Iterator)localObject).next();
        if (sPackageNames.contains(localRunningAppProcessInfo.processName)) {
          localArrayList.add(localRunningAppProcessInfo.processName);
        }
      }
    }
    return localArrayList;
  }
  
  List<String> getInstalledAppsList()
  {
    Object localObject = WeakContext.getContext().getPackageManager().getInstalledApplications(128);
    ArrayList localArrayList = new ArrayList();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext()) {
      localArrayList.add(((ApplicationInfo)((Iterator)localObject).next()).packageName);
    }
    return localArrayList;
  }
  
  public boolean isRDPProcesesRunning()
  {
    Object localObject = ((ActivityManager)WeakContext.getSystemService("activity")).getRunningAppProcesses();
    if (((List)localObject).size() > 0)
    {
      boolean bool1 = false;
      localObject = ((List)localObject).iterator();
      for (;;)
      {
        bool2 = bool1;
        if (!((Iterator)localObject).hasNext()) {
          break;
        }
        ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)((Iterator)localObject).next();
        if (sPackageNames.contains(localRunningAppProcessInfo.processName)) {
          bool1 = true;
        }
      }
    }
    boolean bool2 = false;
    return bool2;
  }
  
  public void requestRunningProcessesList(Handler paramHandler)
  {
    this.mHandler = new WeakReference(paramHandler);
    paramHandler = new ProcessesInfo(null);
    Void[] arrayOfVoid = new Void[0];
    if (!(paramHandler instanceof AsyncTask))
    {
      paramHandler.execute(arrayOfVoid);
      return;
    }
    AsyncTaskInstrumentation.execute((AsyncTask)paramHandler, arrayOfVoid);
  }
  
  private class ProcessesInfo
    extends AsyncTask<Void, Void, List<String>>
  {
    public Trace _nr_trace;
    
    private ProcessesInfo() {}
    
    public void _nr_setTrace(Trace paramTrace)
    {
      try
      {
        this._nr_trace = paramTrace;
        return;
      }
      catch (Exception paramTrace) {}
    }
    
    protected List<String> doInBackground(Void... paramVarArgs)
    {
      Object localObject = ((ActivityManager)WeakContext.getSystemService("activity")).getRunningAppProcesses();
      paramVarArgs = new ArrayList();
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext()) {
        paramVarArgs.add(((ActivityManager.RunningAppProcessInfo)((Iterator)localObject).next()).processName);
      }
      return paramVarArgs;
    }
    
    protected void onPostExecute(List<String> paramList)
    {
      super.onPostExecute(paramList);
      Message localMessage = Message.obtain();
      localMessage.what = 102;
      localMessage.obj = paramList;
      paramList = (Handler)ProcessManager.this.mHandler.get();
      if (paramList != null) {
        paramList.sendMessage(localMessage);
      }
    }
  }
}
