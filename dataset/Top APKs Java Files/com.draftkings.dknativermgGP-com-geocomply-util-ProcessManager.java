package com.geocomply.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProcessManager
{
  private static final String APP_ID_PATTERN = "app_\\d+";
  private static final String TAG = "ProcessManager";
  
  static
  {
    if (Build.VERSION.SDK_INT >= 17)
    {
      APP_ID_PATTERN = "u\\d+_a\\d+";
      return;
    }
  }
  
  public ProcessManager() {}
  
  public static List<String> getAllowMockLocationApp(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    paramContext = paramContext.getPackageManager().getInstalledPackages(4096);
    if (paramContext != null)
    {
      paramContext = paramContext.iterator();
      label122:
      while (paramContext.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
        if (localPackageInfo.requestedPermissions != null)
        {
          String[] arrayOfString = localPackageInfo.requestedPermissions;
          int j = arrayOfString.length;
          int i = 0;
          for (;;)
          {
            if (i >= j) {
              break label122;
            }
            String str = arrayOfString[i];
            if ((str.contains("ALLOW_MOCK_LOCATIONS")) || (str.contains("ACCESS_MOCK_LOCATION")))
            {
              localArrayList.add(localPackageInfo.packageName);
              break;
            }
            i += 1;
          }
        }
      }
    }
    return localArrayList;
  }
  
  public static List<String> getRunningProcesses()
  {
    ArrayList localArrayList = new ArrayList();
    for (;;)
    {
      String str;
      try
      {
        Object localObject1 = new ArrayList();
        Object localObject2;
        try
        {
          BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("toolbox ps -p -P -x -c").getInputStream()));
          localObject2 = localBufferedReader.readLine();
          if (localObject2 == null) {
            break label160;
          }
          ((List)localObject1).add(localObject2);
          continue;
          localObject1 = ((List)localObject1).iterator();
        }
        catch (IOException localIOException)
        {
          localIOException.printStackTrace();
        }
        if (((Iterator)localObject1).hasNext())
        {
          str = (String)((Iterator)localObject1).next();
          try
          {
            localObject2 = new ProcessInfo(str, null);
            if (((ProcessInfo)localObject2).getPackageName() == null) {
              continue;
            }
            localArrayList.add(((ProcessInfo)localObject2).getPackageName());
          }
          catch (Exception localException2)
          {
            Log.d("ProcessManager", "Failed parsing line " + str);
          }
          continue;
        }
        return localArrayList;
      }
      catch (Exception localException1)
      {
        localException1.printStackTrace();
      }
      label160:
      str.close();
    }
  }
  
  private static class ProcessInfo
    implements Parcelable
  {
    public static final Parcelable.Creator<ProcessInfo> CREATOR = new Parcelable.Creator()
    {
      public ProcessManager.ProcessInfo createFromParcel(Parcel paramAnonymousParcel)
      {
        return new ProcessManager.ProcessInfo(paramAnonymousParcel, null);
      }
      
      public ProcessManager.ProcessInfo[] newArray(int paramAnonymousInt)
      {
        return new ProcessManager.ProcessInfo[paramAnonymousInt];
      }
    };
    public final int cpu;
    public final String name;
    public final int niceness;
    public final String pc;
    public final int pid;
    public final String policy;
    public final int ppid;
    public final int priority;
    public final int realTimePriority;
    public final long rss;
    public final int schedulingPolicy;
    public final String state;
    public final long systemTime;
    public final int uid;
    public final String user;
    public final long userTime;
    public final long vsize;
    public final String wchan;
    
    private ProcessInfo(Parcel paramParcel)
    {
      this.user = paramParcel.readString();
      this.uid = paramParcel.readInt();
      this.pid = paramParcel.readInt();
      this.ppid = paramParcel.readInt();
      this.vsize = paramParcel.readLong();
      this.rss = paramParcel.readLong();
      this.cpu = paramParcel.readInt();
      this.priority = paramParcel.readInt();
      this.niceness = paramParcel.readInt();
      this.realTimePriority = paramParcel.readInt();
      this.schedulingPolicy = paramParcel.readInt();
      this.policy = paramParcel.readString();
      this.wchan = paramParcel.readString();
      this.pc = paramParcel.readString();
      this.state = paramParcel.readString();
      this.name = paramParcel.readString();
      this.userTime = paramParcel.readLong();
      this.systemTime = paramParcel.readLong();
    }
    
    private ProcessInfo(String paramString)
    {
      paramString = paramString.split("\\s+");
      this.user = paramString[0];
      this.uid = android.os.Process.getUidForName(this.user);
      this.pid = Integer.parseInt(paramString[1]);
      this.ppid = Integer.parseInt(paramString[2]);
      this.vsize = (Integer.parseInt(paramString[3]) * 1024);
      this.rss = (Integer.parseInt(paramString[4]) * 1024);
      this.cpu = Integer.parseInt(paramString[5]);
      this.priority = Integer.parseInt(paramString[6]);
      this.niceness = Integer.parseInt(paramString[7]);
      this.realTimePriority = Integer.parseInt(paramString[8]);
      this.schedulingPolicy = Integer.parseInt(paramString[9]);
      if (paramString.length == 16)
      {
        this.policy = "";
        this.wchan = paramString[10];
        this.pc = paramString[11];
        this.state = paramString[12];
        this.name = paramString[13];
        this.userTime = (Integer.parseInt(paramString[14].split(":")[1].replace(",", "")) * 1000);
        this.systemTime = (Integer.parseInt(paramString[15].split(":")[1].replace(")", "")) * 1000);
        return;
      }
      this.policy = paramString[10];
      this.wchan = paramString[11];
      this.pc = paramString[12];
      this.state = paramString[13];
      this.name = paramString[14];
      this.userTime = (Integer.parseInt(paramString[15].split(":")[1].replace(",", "")) * 1000);
      this.systemTime = (Integer.parseInt(paramString[16].split(":")[1].replace(")", "")) * 1000);
    }
    
    public int describeContents()
    {
      return 0;
    }
    
    public ApplicationInfo getApplicationInfo(Context paramContext, int paramInt)
      throws PackageManager.NameNotFoundException
    {
      String str = getPackageName();
      if (str == null) {
        throw new PackageManager.NameNotFoundException(this.name + " is not an application process");
      }
      return paramContext.getPackageManager().getApplicationInfo(str, paramInt);
    }
    
    public PackageInfo getPackageInfo(Context paramContext, int paramInt)
      throws PackageManager.NameNotFoundException
    {
      String str = getPackageName();
      if (str == null) {
        throw new PackageManager.NameNotFoundException(this.name + " is not an application process");
      }
      return paramContext.getPackageManager().getPackageInfo(str, paramInt);
    }
    
    public String getPackageName()
    {
      if (!this.user.matches(ProcessManager.APP_ID_PATTERN)) {
        return null;
      }
      if (this.name.contains(":")) {
        return this.name.split(":")[0];
      }
      return this.name;
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      paramParcel.writeString(this.user);
      paramParcel.writeInt(this.uid);
      paramParcel.writeInt(this.pid);
      paramParcel.writeInt(this.ppid);
      paramParcel.writeLong(this.vsize);
      paramParcel.writeLong(this.rss);
      paramParcel.writeInt(this.cpu);
      paramParcel.writeInt(this.priority);
      paramParcel.writeInt(this.niceness);
      paramParcel.writeInt(this.realTimePriority);
      paramParcel.writeInt(this.schedulingPolicy);
      paramParcel.writeString(this.policy);
      paramParcel.writeString(this.wchan);
      paramParcel.writeString(this.pc);
      paramParcel.writeString(this.state);
      paramParcel.writeString(this.name);
      paramParcel.writeLong(this.userTime);
      paramParcel.writeLong(this.systemTime);
    }
  }
}
