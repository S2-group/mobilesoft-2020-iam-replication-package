package com.mapps.android.track;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
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
import com.mapps.android.network.ParamManager;
import com.mapps.android.network.UrlManager;
import com.mapps.android.share.ShareUtil;
import com.mezzo.common.MzLog;
import com.mezzo.common.network.Nt;
import com.mezzo.common.network.Nt.OnProgressbarListener;
import com.mezzo.common.network.data.DataAD;
import com.mezzo.common.network.data.DataListAD;
import com.mezzo.common.network.data.DataListSection;
import com.mezzo.common.network.data.DataNTInitInfo;
import com.mezzo.common.network.data.DataNTPkgAgList;
import com.mezzo.common.network.data.DataSection;
import com.mezzo.common.network.request.OnConnectionListener;
import com.mezzo.common.network.request.RequestInitInfo;
import com.mezzo.common.network.request.RequestNTCommon;
import com.mezzo.common.network.request.RequestNTCommon.CONNECTION;
import com.mezzo.common.network.request.RequestPkgAgList;
import java.io.PrintStream;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@SuppressLint({"NewApi", "SimpleDateFormat"})
@TargetApi(4)
public class Track
{
  public static final String AB_INTERVAL = "ab_interval";
  public static final String DEFAULTINTERVAL = "5";
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
  private static Track instance = null;
  public static boolean isStart;
  public static boolean ispkgSend;
  public static String mdeviceModel;
  public static String mdeviceVersion = Build.VERSION.RELEASE;
  public static Context pctx;
  private Nt nt;
  private Nt.OnProgressbarListener ntOnProgressbarListener = new Nt.OnProgressbarListener()
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
  
  public static Track getInstance()
  {
    if (instance == null) {
      instance = new Track();
    }
    return instance;
  }
  
  private void requestServerPkgConf(String paramString1, String paramString2, String paramString3, int paramInt, OnConnectionListener paramOnConnectionListener)
    throws Exception
  {
    String str = UrlManager.getInstance().urlPkgConf(pctx) + new ParamManager(pctx).getParamInitInfo();
    Message localMessage = new Message();
    localMessage.arg1 = paramInt;
    TrackAdInfo localTrackAdInfo = new TrackAdInfo();
    localTrackAdInfo.pub_no = paramString1;
    localTrackAdInfo.media_no = paramString2;
    localTrackAdInfo.section_no = paramString3;
    localMessage.obj = localTrackAdInfo;
    paramString1 = new RequestInitInfo(pctx, str, localMessage);
    paramString1.setConnectionListener(paramOnConnectionListener);
    this.nt = new Nt(pctx, new Handler(), false, false);
    this.nt.setProgressbarListener(this.ntOnProgressbarListener);
    this.nt.execute(new RequestNTCommon[] { paramString1 });
  }
  
  private void requestServerPkgList(String paramString1, String paramString2, String paramString3, OnConnectionListener paramOnConnectionListener, TrackCompleteListener paramTrackCompleteListener)
    throws Exception
  {
    paramString3 = getPreferences(pctx, "pkg_target_period" + paramString1 + paramString2);
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
      MzLog.i("=pkg_target_period : " + l2 + "min =savetime : " + l1 + "min");
      if (l1 != -1L)
      {
        if (l1 < l2) {
          break label405;
        }
        MzLog.i("sent to SendTagetConfRequest");
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
    MzLog.i("요청");
    paramString3 = new SimpleDateFormat("yyyy-MM-dd a hh:mm:ss").format(new Date());
    savePreferences(pctx, "pkgtime" + paramString1 + paramString2, paramString3);
    savePreferences(pctx, "package_start" + paramString1 + paramString2, "package_start");
    savePreferences(pctx, "package_info", "");
    setAppPreferencesBoolean(pctx, "pkg_flag" + paramString1 + paramString2, false);
    paramString3 = UrlManager.getInstance().urlPkgList(pctx) + new ParamManager(pctx).getParamPkgList(paramString1, paramString2);
    paramTrackCompleteListener = new TrackAdInfo();
    localMessage = new Message();
    paramTrackCompleteListener.pub_no = paramString1;
    paramTrackCompleteListener.media_no = paramString2;
    localMessage.obj = paramTrackCompleteListener;
    paramString1 = new RequestPkgAgList(pctx, paramString3, localMessage);
    paramString1.setConnectionListener(paramOnConnectionListener);
    this.nt = new Nt(pctx, new Handler(), false, false);
    this.nt.setProgressbarListener(this.ntOnProgressbarListener);
    this.nt.execute(new RequestNTCommon[] { paramString1 });
    return;
    label405:
    MzLog.i("It had already been SendTagetConfRequest(" + (l2 - l1) + ")");
    isStart = false;
    paramTrackCompleteListener.onTrackComplete();
  }
  
  /* Error */
  public void SendTagetRequest(final Context paramContext, final String paramString1, final String paramString2, String paramString3, final TrackCompleteListener paramTrackCompleteListener)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: putstatic 87	com/mapps/android/track/Track:pctx	Landroid/content/Context;
    //   6: aload_0
    //   7: aload_2
    //   8: aload_3
    //   9: aload 4
    //   11: new 10	com/mapps/android/track/Track$3
    //   14: dup
    //   15: aload_0
    //   16: aload_1
    //   17: aload_2
    //   18: aload_3
    //   19: aload 5
    //   21: invokespecial 295	com/mapps/android/track/Track$3:<init>	(Lcom/mapps/android/track/Track;Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Lcom/mapps/android/track/Track$TrackCompleteListener;)V
    //   24: aload 5
    //   26: invokespecial 297	com/mapps/android/track/Track:requestServerPkgList	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/mezzo/common/network/request/OnConnectionListener;Lcom/mapps/android/track/Track$TrackCompleteListener;)V
    //   29: aload_0
    //   30: monitorexit
    //   31: return
    //   32: astore_1
    //   33: aload 5
    //   35: ifnull +10 -> 45
    //   38: aload 5
    //   40: invokeinterface 214 1 0
    //   45: aload_1
    //   46: invokevirtual 290	java/lang/Exception:printStackTrace	()V
    //   49: goto -20 -> 29
    //   52: astore_1
    //   53: aload_0
    //   54: monitorexit
    //   55: aload_1
    //   56: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	57	0	this	Track
    //   0	57	1	paramContext	Context
    //   0	57	2	paramString1	String
    //   0	57	3	paramString2	String
    //   0	57	4	paramString3	String
    //   0	57	5	paramTrackCompleteListener	TrackCompleteListener
    // Exception table:
    //   from	to	target	type
    //   6	29	32	java/lang/Exception
    //   2	6	52	finally
    //   6	29	52	finally
    //   38	45	52	finally
    //   45	49	52	finally
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
  
  public void getApparrList(Context paramContext, String paramString, TrackCompleteListener paramTrackCompleteListener)
  {
    try
    {
      if ((!"".equals(ShareUtil.getInstance().m_publisher)) && (!"".equals(ShareUtil.getInstance().m_media))) {
        SendTagetRequest(paramContext, ShareUtil.getInstance().m_publisher, ShareUtil.getInstance().m_media, paramString, paramTrackCompleteListener);
      }
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      MzLog.e("getApparrList error");
    }
  }
  
  public String getApplicationList(Context paramContext, boolean paramBoolean)
  {
    Object localObject2 = "&m_package=";
    Object localObject1 = localObject2;
    if (paramBoolean) {
      localObject1 = localObject2;
    }
    for (;;)
    {
      try
      {
        localIterator = paramContext.getPackageManager().getInstalledApplications(128).iterator();
        paramContext = (Context)localObject2;
        localObject1 = paramContext;
        paramBoolean = localIterator.hasNext();
        if (paramBoolean) {
          continue;
        }
        localObject1 = paramContext;
      }
      catch (Exception paramContext)
      {
        Iterator localIterator;
        continue;
      }
      return ((String)localObject1).trim();
      localObject1 = paramContext;
      localObject2 = (ApplicationInfo)localIterator.next();
      localObject1 = paramContext;
      if ((((ApplicationInfo)localObject2).flags & 0x1) != 0)
      {
        localObject1 = paramContext;
        if ((((ApplicationInfo)localObject2).flags & 0x80) != 0)
        {
          localObject1 = paramContext;
          paramContext = paramContext + ((ApplicationInfo)localObject2).packageName + "^";
        }
      }
      else
      {
        localObject1 = paramContext;
        paramContext = paramContext + ((ApplicationInfo)localObject2).packageName + "^";
      }
    }
  }
  
  public boolean getIeVersion(Context paramContext, String paramString)
  {
    boolean bool = false;
    Object localObject = null;
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramString, 0);
      if (paramContext != null) {
        bool = true;
      }
      return bool;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      for (;;)
      {
        paramContext = localObject;
        if (MzLog.ISLOG)
        {
          paramString.printStackTrace();
          paramContext = localObject;
        }
      }
    }
  }
  
  public String getInterval(Context paramContext, String paramString1, String paramString2, String paramString3, int paramInt, String paramString4)
  {
    paramString1 = paramContext.getSharedPreferences("mezzo", 0).getString(paramString1 + "_" + paramString2 + "_" + paramString3 + "_" + paramString4 + "_" + paramInt + "_" + "ab_interval", "5");
    paramContext = paramString1;
    if ("".equals(paramString1)) {
      paramContext = "5";
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
  public void requestConf(final Context paramContext, final String paramString1, final String paramString2, String paramString3, int paramInt1, final int paramInt2, final String paramString4, final TrackCompleteListener paramTrackCompleteListener)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: putstatic 87	com/mapps/android/track/Track:pctx	Landroid/content/Context;
    //   6: aload_0
    //   7: aload_2
    //   8: aload_3
    //   9: aload 4
    //   11: iload 6
    //   13: new 8	com/mapps/android/track/Track$2
    //   16: dup
    //   17: aload_0
    //   18: aload_1
    //   19: iload 6
    //   21: aload 7
    //   23: aload_2
    //   24: aload_3
    //   25: aload 8
    //   27: invokespecial 506	com/mapps/android/track/Track$2:<init>	(Lcom/mapps/android/track/Track;Landroid/content/Context;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/mapps/android/track/Track$TrackCompleteListener;)V
    //   30: invokespecial 508	com/mapps/android/track/Track:requestServerPkgConf	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILcom/mezzo/common/network/request/OnConnectionListener;)V
    //   33: aload_0
    //   34: monitorexit
    //   35: return
    //   36: astore_1
    //   37: aload 8
    //   39: ifnull +10 -> 49
    //   42: aload 8
    //   44: invokeinterface 214 1 0
    //   49: aload_1
    //   50: invokevirtual 290	java/lang/Exception:printStackTrace	()V
    //   53: goto -20 -> 33
    //   56: astore_1
    //   57: aload_0
    //   58: monitorexit
    //   59: aload_1
    //   60: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	61	0	this	Track
    //   0	61	1	paramContext	Context
    //   0	61	2	paramString1	String
    //   0	61	3	paramString2	String
    //   0	61	4	paramString3	String
    //   0	61	5	paramInt1	int
    //   0	61	6	paramInt2	int
    //   0	61	7	paramString4	String
    //   0	61	8	paramTrackCompleteListener	TrackCompleteListener
    // Exception table:
    //   from	to	target	type
    //   6	33	36	java/lang/Exception
    //   2	6	56	finally
    //   6	33	56	finally
    //   42	49	56	finally
    //   49	53	56	finally
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
  
  public class TrackAdInfo
  {
    String media_no;
    String pub_no;
    String section_no;
    
    public TrackAdInfo() {}
  }
  
  public static abstract interface TrackCompleteListener
  {
    public abstract void onTrackComplete();
  }
}
