package com.mapps.android.share;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Message;
import com.b.a.a.a.n;
import com.b.a.a.a.q;
import com.b.a.a.a.t;
import com.b.a.a.b;
import com.b.a.a.c.d;
import com.b.a.a.c.g;
import com.b.a.a.c.h;
import com.mapps.android.network.ParamManager;
import com.mapps.android.network.UrlManager;
import java.io.PrintStream;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Track
{
  public static final String AB_INTERVAL = "ab_interval";
  public static final String DEFAULTINTERVAL = "0";
  public static final String DEFAULTISINTERVAL = "1";
  public static final String Detarget = "0";
  public static String FAIL;
  public static final String INTERVAL_USE_NO = "0";
  public static final String INTERVAL_USE_YES = "1";
  public static final int NETWORK_3G = 2;
  public static final int NETWORK_NONE = 4;
  public static final int NETWORK_WIFI = 1;
  public static final int NETWORK_WIMAX = 3;
  public static final String SHAREDFILE = "mezzo";
  public static String SUCCESS;
  public static final String Target = "1";
  public static String advertise_ID = "";
  private static Track b = null;
  public static boolean isStart;
  public static boolean ispkgSend;
  public static String mdeviceModel;
  public static String mdeviceVersion = Build.VERSION.RELEASE;
  public static Context pctx;
  private com.b.a.a.a a;
  private b c = new b()
  {
    public void hide() {}
    
    public void show() {}
  };
  
  static
  {
    mdeviceModel = Build.MODEL;
    pctx = null;
    SUCCESS = "1";
    FAIL = "0";
    isStart = false;
    ispkgSend = true;
  }
  
  private Track() {}
  
  private void a(String paramString1, String paramString2, String paramString3, int paramInt, com.b.a.a.c.a paramA)
  {
    com.b.a.e.d("★★★★★★★★★★★★★★★★★★★★★★★★★★★★InitInfo");
    String str = UrlManager.getInstance().urlPkgConf(pctx) + new ParamManager(pctx).getParamInitInfo();
    Message localMessage = new Message();
    localMessage.arg1 = paramInt;
    Track.TrackAdInfo localTrackAdInfo = new Track.TrackAdInfo(this);
    localTrackAdInfo.a = paramString1;
    localTrackAdInfo.b = paramString2;
    localTrackAdInfo.c = paramString3;
    localMessage.obj = localTrackAdInfo;
    paramString1 = new d(pctx, str, localMessage);
    paramString1.a(paramA);
    this.a = new com.b.a.a.a(pctx, new Handler(), false, false);
    this.a.a(this.c);
    this.a.execute(new g[] { paramString1 });
  }
  
  private void a(String paramString1, String paramString2, String paramString3, com.b.a.a.c.a paramA, Track.TrackCompleteListener paramTrackCompleteListener)
  {
    paramString3 = getPreferences(pctx, "pkg_target_period");
    if ("".equals(paramString3))
    {
      paramTrackCompleteListener.onTrackComplete();
      return;
    }
    if (!isStart) {
      isStart = true;
    }
    try
    {
      l1 = checkTime(pctx, paramString1, paramString2);
      l2 = Long.parseLong(paramString3);
      com.b.a.e.e("=pkg_target_period : " + l2 + "min =savetime : " + l1 + "min");
      if (l1 != -1L)
      {
        if (l1 < l2) {
          break label370;
        }
        com.b.a.e.e("PkgList 요청 시도");
      }
    }
    catch (Exception paramString3)
    {
      for (;;)
      {
        long l1;
        long l2;
        Message localMessage;
        paramString3.printStackTrace();
      }
    }
    com.b.a.e.e("요청");
    paramString3 = new SimpleDateFormat("yyyy-MM-dd a hh:mm:ss").format(new Date());
    savePreferences(pctx, "pkgtime" + paramString1 + paramString2, paramString3);
    savePreferences(pctx, "package_start" + paramString1 + paramString2, "package_start");
    savePreferences(pctx, "package_info", "");
    setAppPreferencesBoolean(pctx, "pkg_flag", false);
    paramString3 = UrlManager.getInstance().urlPkgList(pctx) + new ParamManager(pctx).getParamPkgList(paramString1, paramString2);
    paramTrackCompleteListener = new Track.TrackAdInfo(this);
    localMessage = new Message();
    paramTrackCompleteListener.a = paramString1;
    paramTrackCompleteListener.b = paramString2;
    localMessage.obj = paramTrackCompleteListener;
    com.b.a.e.d("★★★★★★★★★★★★★★★★★★★★★★★★★★★★PkgAgList");
    paramString1 = new com.b.a.a.c.i(pctx, paramString3, localMessage);
    paramString1.a(paramA);
    this.a = new com.b.a.a.a(pctx, new Handler(), false, false);
    this.a.a(this.c);
    this.a.execute(new g[] { paramString1 });
    return;
    label370:
    com.b.a.e.e("PkgList 안보냄(" + (l2 - l1) + ")");
    isStart = false;
    paramTrackCompleteListener.onTrackComplete();
  }
  
  public static Track getInstance()
  {
    if (b == null) {
      b = new Track();
    }
    return b;
  }
  
  /* Error */
  public void SendTagetRequest(final Context paramContext, String paramString1, String paramString2, String paramString3, final Track.TrackCompleteListener paramTrackCompleteListener)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: putstatic 74	com/mapps/android/share/Track:pctx	Landroid/content/Context;
    //   6: aload_0
    //   7: aload_2
    //   8: aload_3
    //   9: aload 4
    //   11: new 10	com/mapps/android/share/Track$3
    //   14: dup
    //   15: aload_0
    //   16: aload_1
    //   17: aload 5
    //   19: invokespecial 285	com/mapps/android/share/Track$3:<init>	(Lcom/mapps/android/share/Track;Landroid/content/Context;Lcom/mapps/android/share/Track$TrackCompleteListener;)V
    //   22: aload 5
    //   24: invokespecial 287	com/mapps/android/share/Track:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/b/a/a/c/a;Lcom/mapps/android/share/Track$TrackCompleteListener;)V
    //   27: aload_0
    //   28: monitorexit
    //   29: return
    //   30: astore_1
    //   31: aload 5
    //   33: ifnull +10 -> 43
    //   36: aload 5
    //   38: invokeinterface 202 1 0
    //   43: aload_1
    //   44: invokevirtual 278	java/lang/Exception:printStackTrace	()V
    //   47: goto -20 -> 27
    //   50: astore_1
    //   51: aload_0
    //   52: monitorexit
    //   53: aload_1
    //   54: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	55	0	this	Track
    //   0	55	1	paramContext	Context
    //   0	55	2	paramString1	String
    //   0	55	3	paramString2	String
    //   0	55	4	paramString3	String
    //   0	55	5	paramTrackCompleteListener	Track.TrackCompleteListener
    // Exception table:
    //   from	to	target	type
    //   6	27	30	java/lang/Exception
    //   2	6	50	finally
    //   6	27	50	finally
    //   36	43	50	finally
    //   43	47	50	finally
  }
  
  public long checkTime(Context paramContext, String paramString1, String paramString2)
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd a hh:mm:ss");
    long l2 = -1L;
    long l1 = l2;
    if ("package_start".equals(getPreferences(paramContext, "package_start" + paramString1 + paramString2)))
    {
      paramContext = getPreferences(paramContext, "pkgtime" + paramString1 + paramString2);
      l1 = l2;
      if (paramContext != null)
      {
        l1 = l2;
        if (!"".equals(paramContext))
        {
          l1 = localSimpleDateFormat.parse(paramContext, new ParsePosition(0)).getTime();
          l1 = (Calendar.getInstance().getTime().getTime() - l1) / 60000L;
        }
      }
    }
    return l1;
  }
  
  public String getActivity(Context paramContext)
  {
    return ((ActivityManager.RunningTaskInfo)((ActivityManager)paramContext.getSystemService("activity")).getRunningTasks(1).get(0)).topActivity.getClassName().toString();
  }
  
  public String getAdid(Context paramContext)
  {
    return "&m_id=" + ShareUtil.getInstance().getgoogleAdvertiseID(paramContext);
  }
  
  public String getAdidv2(Context paramContext)
  {
    return "&d_adid=" + ShareUtil.getInstance().getgoogleAdvertiseID(paramContext);
  }
  
  public boolean getAppPreferencesBoolean(Context paramContext, String paramString)
  {
    return paramContext.getSharedPreferences(paramString, 0).getBoolean(paramString, false);
  }
  
  public void getAppTest(Context paramContext)
  {
    paramContext = getApplicationList(paramContext, true).replace("&m_package=", "").split("\\^");
    System.out.println(paramContext.length);
    int i = 0;
    for (;;)
    {
      if (i >= paramContext.length) {
        return;
      }
      System.out.println(paramContext[i]);
      i += 1;
    }
  }
  
  public void getApparrList(Context paramContext, String paramString, Track.TrackCompleteListener paramTrackCompleteListener)
  {
    try
    {
      com.b.a.e.d("LOG : ShareUtil.getInstance().m_publisher " + ShareUtil.getInstance().m_publisher);
      com.b.a.e.d("LOG : ShareUtil.getInstance().m_media " + ShareUtil.getInstance().m_media);
      if ((!"".equals(ShareUtil.getInstance().m_publisher)) && (!"".equals(ShareUtil.getInstance().m_media))) {
        SendTagetRequest(paramContext, ShareUtil.getInstance().m_publisher, ShareUtil.getInstance().m_media, paramString, paramTrackCompleteListener);
      }
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      com.b.a.e.c("getApparrList error");
    }
  }
  
  public String getApplicationList(Context paramContext, boolean paramBoolean)
  {
    localObject2 = "&m_package=";
    localObject1 = localObject2;
    if (paramBoolean) {}
    for (;;)
    {
      try
      {
        localIterator = paramContext.getPackageManager().getInstalledApplications(128).iterator();
        paramContext = "&m_package=";
        localObject1 = paramContext;
      }
      catch (Exception paramContext)
      {
        Iterator localIterator;
        localObject1 = localObject2;
        continue;
      }
      try
      {
        paramBoolean = localIterator.hasNext();
        if (paramBoolean) {
          continue;
        }
      }
      catch (Exception paramContext)
      {
        continue;
      }
      return ((String)localObject1).trim();
      localObject2 = (ApplicationInfo)localIterator.next();
      if ((((ApplicationInfo)localObject2).flags & 0x1) != 0)
      {
        paramContext = (Context)localObject1;
        if ((((ApplicationInfo)localObject2).flags & 0x80) != 0) {
          paramContext = localObject1 + ((ApplicationInfo)localObject2).packageName + "^";
        }
      }
      else
      {
        paramContext = localObject1 + ((ApplicationInfo)localObject2).packageName + "^";
      }
    }
  }
  
  public boolean getIeVersion(Context paramContext, String paramString)
  {
    boolean bool = false;
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramString, 0);
      if (paramContext != null) {
        bool = true;
      }
      return bool;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;)
      {
        if (com.b.a.e.a) {
          paramContext.printStackTrace();
        }
        paramContext = null;
      }
    }
  }
  
  public String getInterval(Context paramContext, String paramString1, String paramString2, String paramString3, int paramInt, String paramString4)
  {
    paramString1 = paramContext.getSharedPreferences("mezzo", 0).getString(paramString1 + "_" + paramString2 + "_" + paramString3 + "_" + paramString4 + "_" + paramInt + "_" + "ab_interval", "0");
    paramContext = paramString1;
    if ("".equals(paramString1)) {
      paramContext = "0";
    }
    return paramContext;
  }
  
  public String getPreferences(Context paramContext, String paramString)
  {
    return paramContext.getSharedPreferences("mezzo", 0).getString(paramString, "");
  }
  
  public boolean getpkgSend()
  {
    return ispkgSend;
  }
  
  public void removeAllPreferences(Context paramContext)
  {
    paramContext = paramContext.getSharedPreferences("mezzo", 0).edit();
    paramContext.clear();
    paramContext.commit();
  }
  
  public void removePreferences(Context paramContext, String paramString)
  {
    paramContext = paramContext.getSharedPreferences("mezzo", 0).edit();
    paramContext.remove(paramString);
    paramContext.commit();
  }
  
  /* Error */
  public void requestConf(final Context paramContext, String paramString1, String paramString2, String paramString3, int paramInt1, final int paramInt2, final String paramString4, final Track.TrackCompleteListener paramTrackCompleteListener)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: putstatic 74	com/mapps/android/share/Track:pctx	Landroid/content/Context;
    //   6: aload_0
    //   7: aload_2
    //   8: aload_3
    //   9: aload 4
    //   11: iload 6
    //   13: new 8	com/mapps/android/share/Track$2
    //   16: dup
    //   17: aload_0
    //   18: aload_1
    //   19: iload 6
    //   21: aload 7
    //   23: aload 8
    //   25: invokespecial 498	com/mapps/android/share/Track$2:<init>	(Lcom/mapps/android/share/Track;Landroid/content/Context;ILjava/lang/String;Lcom/mapps/android/share/Track$TrackCompleteListener;)V
    //   28: invokespecial 500	com/mapps/android/share/Track:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILcom/b/a/a/c/a;)V
    //   31: aload_0
    //   32: monitorexit
    //   33: return
    //   34: astore_1
    //   35: aload 8
    //   37: ifnull +10 -> 47
    //   40: aload 8
    //   42: invokeinterface 202 1 0
    //   47: aload_1
    //   48: invokevirtual 278	java/lang/Exception:printStackTrace	()V
    //   51: goto -20 -> 31
    //   54: astore_1
    //   55: aload_0
    //   56: monitorexit
    //   57: aload_1
    //   58: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	59	0	this	Track
    //   0	59	1	paramContext	Context
    //   0	59	2	paramString1	String
    //   0	59	3	paramString2	String
    //   0	59	4	paramString3	String
    //   0	59	5	paramInt1	int
    //   0	59	6	paramInt2	int
    //   0	59	7	paramString4	String
    //   0	59	8	paramTrackCompleteListener	Track.TrackCompleteListener
    // Exception table:
    //   from	to	target	type
    //   6	31	34	java/lang/Exception
    //   2	6	54	finally
    //   6	31	54	finally
    //   40	47	54	finally
    //   47	51	54	finally
  }
  
  public void savePreferences(Context paramContext, String paramString1, String paramString2)
  {
    paramContext = paramContext.getSharedPreferences("mezzo", 0).edit();
    paramContext.putString(paramString1, paramString2);
    paramContext.commit();
  }
  
  public boolean selectPTaget(Context paramContext, String paramString1, String paramString2)
  {
    boolean bool2 = false;
    paramString2 = paramString2.split("\\^");
    int i = 0;
    boolean bool1;
    if (i >= paramString2.length) {
      bool1 = true;
    }
    do
    {
      return bool1;
      if (!"1".equals(paramString1)) {
        break;
      }
      bool1 = bool2;
    } while (!getIeVersion(paramContext, paramString2[i]));
    while (!getIeVersion(paramContext, paramString2[i]))
    {
      i += 1;
      break;
    }
    return false;
  }
  
  public boolean selectPTaget(Context paramContext, String paramString1, String paramString2, boolean paramBoolean)
  {
    boolean bool2 = false;
    paramString2 = paramString2.split("\\^");
    com.b.a.e.d("isAnd : " + paramBoolean);
    int i = 0;
    boolean bool1;
    if (i >= paramString2.length) {
      bool1 = paramBoolean;
    }
    do
    {
      return bool1;
      if (!paramBoolean) {
        break label102;
      }
      if (!"1".equals(paramString1)) {
        break;
      }
      bool1 = bool2;
    } while (!getIeVersion(paramContext, paramString2[i]));
    label102:
    label125:
    do
    {
      do
      {
        do
        {
          i += 1;
          break;
        } while (!getIeVersion(paramContext, paramString2[i]));
        return false;
        if (!"1".equals(paramString1)) {
          break label125;
        }
      } while (!getIeVersion(paramContext, paramString2[i]));
      return true;
    } while (getIeVersion(paramContext, paramString2[i]));
    return true;
  }
  
  public void sendPackage(boolean paramBoolean)
  {
    ispkgSend = paramBoolean;
  }
  
  public void setAppPreferencesBoolean(Context paramContext, String paramString, boolean paramBoolean)
  {
    paramContext = paramContext.getSharedPreferences(paramString, 0).edit();
    paramContext.putBoolean(paramString, paramBoolean);
    paramContext.commit();
  }
  
  public void setInterval(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, int paramInt, String paramString5)
  {
    paramContext = paramContext.getSharedPreferences("mezzo", 0).edit();
    paramContext.putString(paramString2 + "_" + paramString3 + "_" + paramString4 + "_" + paramString5 + "_" + paramInt + "_" + "ab_interval", paramString1);
    paramContext.commit();
  }
  
  public void timec()
  {
    Object localObject = Calendar.getInstance();
    ((Calendar)localObject).getTimeInMillis();
    localObject = ((Calendar)localObject).getTime().toString();
    System.out.println((String)localObject);
  }
}
