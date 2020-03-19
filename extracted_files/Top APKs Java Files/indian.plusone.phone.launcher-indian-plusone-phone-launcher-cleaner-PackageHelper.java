package indian.plusone.phone.launcher.cleaner;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import android.os.Debug.MemoryInfo;
import indian.plusone.phone.launcher.cleaner.pm.AndroidAppProcess;
import indian.plusone.phone.launcher.cleaner.pm.ProcessManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class PackageHelper
{
  private final ActivityManager activityManager;
  private final Context context;
  private final PackageManager packageManager;
  
  public PackageHelper(Context paramContext)
  {
    this.context = paramContext;
    this.activityManager = ((ActivityManager)this.context.getSystemService("activity"));
    this.packageManager = this.context.getPackageManager();
  }
  
  private void cleanAll()
  {
    Iterator localIterator = this.context.getPackageManager().getInstalledApplications(0).iterator();
    for (;;)
    {
      if (!localIterator.hasNext()) {
        return;
      }
      ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
      if (((localApplicationInfo.flags & 0x80) == 0) && ((localApplicationInfo.flags & 0x1) == 0)) {
        try
        {
          if (!localApplicationInfo.packageName.equals("indian.plusone.phone.launcher")) {
            this.activityManager.killBackgroundProcesses(localApplicationInfo.packageName);
          }
        }
        catch (Exception localException) {}
      }
    }
  }
  
  /* Error */
  private long getTotalMemoryLegacy()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aconst_null
    //   4: astore 5
    //   6: new 89	java/io/RandomAccessFile
    //   9: dup
    //   10: ldc 91
    //   12: ldc 93
    //   14: invokespecial 96	java/io/RandomAccessFile:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   17: astore_3
    //   18: aload_3
    //   19: invokevirtual 100	java/io/RandomAccessFile:readLine	()Ljava/lang/String;
    //   22: ldc 102
    //   24: ldc 104
    //   26: invokevirtual 108	java/lang/String:replaceAll	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   29: invokestatic 114	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   32: lstore_1
    //   33: aload_3
    //   34: invokevirtual 117	java/io/RandomAccessFile:close	()V
    //   37: ldc2_w 118
    //   40: lload_1
    //   41: lmul
    //   42: lreturn
    //   43: astore_3
    //   44: aload_3
    //   45: invokevirtual 122	java/io/IOException:printStackTrace	()V
    //   48: goto -11 -> 37
    //   51: astore_3
    //   52: aload 5
    //   54: astore_3
    //   55: aload_3
    //   56: invokevirtual 117	java/io/RandomAccessFile:close	()V
    //   59: lconst_0
    //   60: lreturn
    //   61: astore_3
    //   62: aload_3
    //   63: invokevirtual 122	java/io/IOException:printStackTrace	()V
    //   66: goto -7 -> 59
    //   69: astore_3
    //   70: aload 4
    //   72: invokevirtual 117	java/io/RandomAccessFile:close	()V
    //   75: aload_3
    //   76: athrow
    //   77: astore 4
    //   79: aload 4
    //   81: invokevirtual 122	java/io/IOException:printStackTrace	()V
    //   84: goto -9 -> 75
    //   87: astore 5
    //   89: aload_3
    //   90: astore 4
    //   92: aload 5
    //   94: astore_3
    //   95: goto -25 -> 70
    //   98: astore 4
    //   100: goto -45 -> 55
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	103	0	this	PackageHelper
    //   32	9	1	l	long
    //   17	17	3	localRandomAccessFile	java.io.RandomAccessFile
    //   43	2	3	localIOException1	java.io.IOException
    //   51	1	3	localIOException2	java.io.IOException
    //   54	2	3	localObject1	Object
    //   61	2	3	localIOException3	java.io.IOException
    //   69	21	3	localObject2	Object
    //   94	1	3	localObject3	Object
    //   1	70	4	localObject4	Object
    //   77	3	4	localIOException4	java.io.IOException
    //   90	1	4	localObject5	Object
    //   98	1	4	localIOException5	java.io.IOException
    //   4	49	5	localObject6	Object
    //   87	6	5	localObject7	Object
    // Exception table:
    //   from	to	target	type
    //   33	37	43	java/io/IOException
    //   6	18	51	java/io/IOException
    //   55	59	61	java/io/IOException
    //   6	18	69	finally
    //   70	75	77	java/io/IOException
    //   18	33	87	finally
    //   18	33	98	java/io/IOException
  }
  
  public void clean(List<CleanModel> paramList)
  {
    if ((paramList == null) || (paramList.size() == 0)) {
      cleanAll();
    }
    for (;;)
    {
      return;
      paramList = paramList.iterator();
      while (paramList.hasNext()) {
        try
        {
          CleanModel localCleanModel = (CleanModel)paramList.next();
          this.activityManager.killBackgroundProcesses(localCleanModel.getPackageName());
        }
        catch (Exception localException) {}
      }
    }
  }
  
  public ArrayList<CleanModel> getAllRunningProcess()
  {
    String str1 = this.context.getPackageName();
    HashMap localHashMap = new HashMap();
    HashSet localHashSet = new HashSet();
    Iterator localIterator;
    if (Build.VERSION.SDK_INT <= 24) {
      localIterator = ProcessManager.getRunningAppProcesses().iterator();
    }
    for (;;)
    {
      if (!localIterator.hasNext())
      {
        localObject1 = new ArrayList();
        ((ArrayList)localObject1).addAll(localHashMap.values());
        return localObject1;
      }
      Object localObject3 = (AndroidAppProcess)localIterator.next();
      if (((AndroidAppProcess)localObject3).getPackageName().equals(str1)) {
        continue;
      }
      String str2 = ((AndroidAppProcess)localObject3).getPackageName();
      Object localObject2 = (CleanModel)localHashMap.get(str2);
      Object localObject1 = localObject2;
      if (localObject2 == null)
      {
        localObject1 = new CleanModel();
        ((CleanModel)localObject1).setPackageName(str2);
      }
      try
      {
        ((CleanModel)localObject1).setDrawable(this.packageManager.getApplicationIcon(str2));
        try
        {
          ((CleanModel)localObject1).setLabel(this.packageManager.getApplicationLabel(this.packageManager.getApplicationInfo(str2, 128)).toString());
          localHashMap.put(str2, localObject1);
          localObject2 = this.activityManager.getProcessMemoryInfo(new int[] { ((AndroidAppProcess)localObject3).pid });
          if ((localObject2 == null) || (localObject2.length <= 0)) {
            continue;
          }
          int i = localObject2[0].getTotalPss() / 1024;
          if (i <= 0) {
            continue;
          }
          ((CleanModel)localObject1).setSize(((CleanModel)localObject1).getSize() + i);
          localHashSet.add(Integer.valueOf(((AndroidAppProcess)localObject3).pid));
          continue;
          localIterator = this.activityManager.getRunningAppProcesses().iterator();
          label272:
          localObject3 = (ActivityManager.RunningAppProcessInfo)localIterator.next();
          if (!localObject3.pkgList[0].equals(str1))
          {
            str2 = localObject3.pkgList[0];
            localObject2 = (CleanModel)localHashMap.get(str2);
            localObject1 = localObject2;
            if (localObject2 == null)
            {
              localObject1 = new CleanModel();
              ((CleanModel)localObject1).setPackageName(str2);
            }
          }
          try
          {
            ((CleanModel)localObject1).setDrawable(this.packageManager.getApplicationIcon(str2));
            try
            {
              ((CleanModel)localObject1).setLabel(this.packageManager.getApplicationLabel(this.packageManager.getApplicationInfo(str2, 128)).toString());
              localHashMap.put(str2, localObject1);
              localObject2 = this.activityManager.getProcessMemoryInfo(new int[] { ((ActivityManager.RunningAppProcessInfo)localObject3).pid });
              if ((localObject2 != null) && (localObject2.length > 0))
              {
                i = localObject2[0].getTotalPss() / 1024;
                if (i > 0) {
                  break label445;
                }
              }
              for (;;)
              {
                if (localIterator.hasNext()) {
                  break label272;
                }
                break;
                label445:
                ((CleanModel)localObject1).setSize(((CleanModel)localObject1).getSize() + i);
                localHashSet.add(Integer.valueOf(((ActivityManager.RunningAppProcessInfo)localObject3).pid));
              }
            }
            catch (Exception localException1)
            {
              for (;;) {}
            }
          }
          catch (Exception localException2)
          {
            for (;;) {}
          }
        }
        catch (Exception localException3)
        {
          for (;;) {}
        }
      }
      catch (Exception localException4)
      {
        for (;;) {}
      }
    }
  }
  
  public int getAvailableMemoryInMB()
  {
    ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
    this.activityManager.getMemoryInfo(localMemoryInfo);
    return (int)((float)localMemoryInfo.availMem / 1024.0F / 1024.0F);
  }
  
  public int getTotalMemoryInMB()
  {
    ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
    this.activityManager.getMemoryInfo(localMemoryInfo);
    if (Build.VERSION.SDK_INT >= 16) {
      return (int)((float)localMemoryInfo.totalMem / 1024.0F / 1024.0F);
    }
    return (int)((float)getTotalMemoryLegacy() / 1024.0F / 1024.0F);
  }
}
