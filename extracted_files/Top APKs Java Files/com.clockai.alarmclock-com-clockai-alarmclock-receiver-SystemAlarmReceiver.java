package com.clockai.alarmclock.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.view.Display;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import com.clockai.alarmclock.app.AppApplication;
import com.clockai.alarmclock.utils.HP;
import com.clockai.alarmclock.widget.animation.ColorfulLampView;
import com.ox.component.WJ;
import com.ox.component.utils.thread.ThreadPool;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SystemAlarmReceiver
  extends BroadcastReceiver
{
  private static String sb = "SystemAlarm";
  String Aw = ".ALARM_DONE";
  private List<String> LE = new ArrayList();
  ColorfulLampView Ox = new ColorfulLampView(WJ.WJ());
  String WJ = ".ALARM_ALERT";
  private List<String> Ws = new ArrayList();
  String ZC = ".ALARM_ALERT_DISMISS";
  String bW = ".alarm.ALARM_ALERT";
  String gn = ".ALARM_APPWIDGET_UPDATE";
  String xf = ".ALARM_ALERT";
  
  public SystemAlarmReceiver() {}
  
  public static SystemAlarmReceiver WJ()
  {
    return WJ.WJ();
  }
  
  /* Error */
  private String ZC()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: new 83	java/io/BufferedReader
    //   6: dup
    //   7: new 85	java/io/InputStreamReader
    //   10: dup
    //   11: invokestatic 91	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   14: ldc 93
    //   16: invokevirtual 97	java/lang/Runtime:exec	(Ljava/lang/String;)Ljava/lang/Process;
    //   19: invokevirtual 103	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   22: invokespecial 106	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   25: sipush 1024
    //   28: invokespecial 109	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   31: astore_2
    //   32: ldc 111
    //   34: astore_3
    //   35: ldc 111
    //   37: astore_1
    //   38: aload_3
    //   39: ifnull +35 -> 74
    //   42: new 113	java/lang/StringBuilder
    //   45: dup
    //   46: invokespecial 114	java/lang/StringBuilder:<init>	()V
    //   49: aload_1
    //   50: invokevirtual 118	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   53: aload_3
    //   54: invokevirtual 118	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   57: ldc 120
    //   59: invokevirtual 118	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   62: invokevirtual 123	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   65: astore_1
    //   66: aload_2
    //   67: invokevirtual 126	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   70: astore_3
    //   71: goto -33 -> 38
    //   74: aload_1
    //   75: invokevirtual 131	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   78: ldc -123
    //   80: invokevirtual 137	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   83: ifeq +20 -> 103
    //   86: ldc -123
    //   88: astore_3
    //   89: aload_3
    //   90: astore_1
    //   91: aload_2
    //   92: ifnull +9 -> 101
    //   95: aload_2
    //   96: invokevirtual 140	java/io/BufferedReader:close	()V
    //   99: aload_3
    //   100: astore_1
    //   101: aload_1
    //   102: areturn
    //   103: aload_1
    //   104: invokevirtual 131	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   107: ldc -114
    //   109: invokevirtual 137	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   112: ifeq +21 -> 133
    //   115: ldc -114
    //   117: astore_1
    //   118: aload_2
    //   119: ifnull -18 -> 101
    //   122: aload_2
    //   123: invokevirtual 140	java/io/BufferedReader:close	()V
    //   126: ldc -114
    //   128: areturn
    //   129: astore_1
    //   130: ldc -114
    //   132: areturn
    //   133: aload_1
    //   134: invokevirtual 131	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   137: ldc -112
    //   139: invokevirtual 137	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   142: ifeq +21 -> 163
    //   145: ldc -112
    //   147: astore_1
    //   148: aload_2
    //   149: ifnull -48 -> 101
    //   152: aload_2
    //   153: invokevirtual 140	java/io/BufferedReader:close	()V
    //   156: ldc -112
    //   158: areturn
    //   159: astore_1
    //   160: ldc -112
    //   162: areturn
    //   163: aload 4
    //   165: astore_1
    //   166: aload_2
    //   167: ifnull -66 -> 101
    //   170: aload_2
    //   171: invokevirtual 140	java/io/BufferedReader:close	()V
    //   174: aconst_null
    //   175: areturn
    //   176: astore_1
    //   177: aconst_null
    //   178: areturn
    //   179: astore_1
    //   180: aconst_null
    //   181: astore_2
    //   182: aload 4
    //   184: astore_1
    //   185: aload_2
    //   186: ifnull -85 -> 101
    //   189: aload_2
    //   190: invokevirtual 140	java/io/BufferedReader:close	()V
    //   193: aconst_null
    //   194: areturn
    //   195: astore_1
    //   196: aconst_null
    //   197: areturn
    //   198: astore_1
    //   199: aconst_null
    //   200: astore_2
    //   201: aload_2
    //   202: ifnull +7 -> 209
    //   205: aload_2
    //   206: invokevirtual 140	java/io/BufferedReader:close	()V
    //   209: aload_1
    //   210: athrow
    //   211: astore_1
    //   212: ldc -123
    //   214: areturn
    //   215: astore_2
    //   216: goto -7 -> 209
    //   219: astore_1
    //   220: goto -19 -> 201
    //   223: astore_1
    //   224: goto -42 -> 182
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	227	0	this	SystemAlarmReceiver
    //   37	81	1	str1	String
    //   129	5	1	localException1	Exception
    //   147	1	1	str2	String
    //   159	1	1	localException2	Exception
    //   165	1	1	localObject1	Object
    //   176	1	1	localException3	Exception
    //   179	1	1	localException4	Exception
    //   184	1	1	localObject2	Object
    //   195	1	1	localException5	Exception
    //   198	12	1	localObject3	Object
    //   211	1	1	localException6	Exception
    //   219	1	1	localObject4	Object
    //   223	1	1	localException7	Exception
    //   31	175	2	localBufferedReader	java.io.BufferedReader
    //   215	1	2	localException8	Exception
    //   34	66	3	str3	String
    //   1	182	4	localObject5	Object
    // Exception table:
    //   from	to	target	type
    //   122	126	129	java/lang/Exception
    //   152	156	159	java/lang/Exception
    //   170	174	176	java/lang/Exception
    //   3	32	179	java/lang/Exception
    //   189	193	195	java/lang/Exception
    //   3	32	198	finally
    //   95	99	211	java/lang/Exception
    //   205	209	215	java/lang/Exception
    //   42	71	219	finally
    //   74	86	219	finally
    //   103	115	219	finally
    //   133	145	219	finally
    //   42	71	223	java/lang/Exception
    //   74	86	223	java/lang/Exception
    //   103	115	223	java/lang/Exception
    //   133	145	223	java/lang/Exception
  }
  
  public void Aw()
  {
    WindowManager localWindowManager = (WindowManager)WJ.WJ().getSystemService("window");
    WindowManager.LayoutParams localLayoutParams = AppApplication.Aw().WJ();
    localLayoutParams.type = 2010;
    localLayoutParams.format = 1;
    localLayoutParams.flags = 24;
    localLayoutParams.flags |= 0x80000;
    localLayoutParams.flags |= 0x40000;
    localLayoutParams.flags |= 0x200;
    localLayoutParams.alpha = 1.0F;
    localLayoutParams.gravity = 51;
    localLayoutParams.x = 0;
    localLayoutParams.y = 0;
    localLayoutParams.width = localWindowManager.getDefaultDisplay().getWidth();
    localLayoutParams.height = (localWindowManager.getDefaultDisplay().getHeight() - HP.ZC());
    xf();
    try
    {
      localWindowManager.addView(this.Ox, localLayoutParams);
      this.Ox.setVisibility(0);
      this.Ox.setStartAnim(true);
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localLayoutParams.type = 2005;
        localWindowManager.addView(this.Ox, localLayoutParams);
      }
    }
  }
  
  public void WJ(Context paramContext)
  {
    Object localObject1 = ZC();
    this.LE.clear();
    this.Ws.clear();
    if (localObject1 != null)
    {
      if (!((String)localObject1).equals("samsung")) {
        break label262;
      }
      this.LE.add("com.samsung.sec.android.clockpackage" + this.bW);
      this.Ws.add("com.sec.android.clockpackage" + this.gn);
      this.Ws.add("com.samsung.sec.android.clockpackage.alarm.ALARM_STOPPED_IN_ALERT");
    }
    for (;;)
    {
      localObject2 = paramContext.getPackageManager().getInstalledApplications(0);
      localObject1 = new ArrayList();
      localObject2 = ((List)localObject2).iterator();
      while (((Iterator)localObject2).hasNext())
      {
        localObject3 = ((ApplicationInfo)((Iterator)localObject2).next()).packageName;
        if ((!((String)localObject3).contains(paramContext.getPackageName())) && ((((String)localObject3).equals("com.android.deskclock")) || (((String)localObject3).equals("com.google.android.deskclock"))))
        {
          this.LE.add("com.android.deskclock" + this.WJ);
          this.Ws.add("com.android.deskclock" + this.Aw);
        }
      }
      label262:
      if (((String)localObject1).equals("oneplus"))
      {
        this.LE.add("com.oneplus.deskclock" + this.WJ);
        this.Ws.add("com.oneplus.deskclock" + this.Aw);
      }
      else if (((String)localObject1).equals("oppo"))
      {
        this.LE.add("com.oppo.alarmclock.alarmclock" + this.xf);
        this.Ws.add("com.oppo.alarmclock.alarmclock" + this.ZC);
      }
    }
    Object localObject2 = new IntentFilter();
    Object localObject3 = this.LE.iterator();
    while (((Iterator)localObject3).hasNext()) {
      ((List)localObject1).add((String)((Iterator)localObject3).next());
    }
    localObject3 = this.Ws.iterator();
    while (((Iterator)localObject3).hasNext()) {
      ((List)localObject1).add((String)((Iterator)localObject3).next());
    }
    localObject1 = ((List)localObject1).iterator();
    while (((Iterator)localObject1).hasNext()) {
      ((IntentFilter)localObject2).addAction((String)((Iterator)localObject1).next());
    }
    paramContext.registerReceiver(this, (IntentFilter)localObject2);
  }
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    paramContext = paramIntent.getAction();
    if (paramContext != null)
    {
      paramIntent = this.LE.iterator();
      while (paramIntent.hasNext()) {
        if (((String)paramIntent.next()).equals(paramContext))
        {
          ThreadPool.Aw(new Runnable()
          {
            public void run() {}
          }, 3000L);
          ThreadPool.Aw(new Runnable()
          {
            public void run()
            {
              SystemAlarmReceiver.this.Aw();
            }
          }, 1000L);
        }
      }
    }
    do
    {
      return;
      while (!paramIntent.hasNext()) {
        paramIntent = this.Ws.iterator();
      }
    } while (!((String)paramIntent.next()).equals(paramContext));
    xf();
  }
  
  public void xf()
  {
    try
    {
      WindowManager localWindowManager = (WindowManager)WJ.WJ().getSystemService("window");
      this.Ox.setVisibility(8);
      this.Ox.setStartAnim(false);
      localWindowManager.removeView(this.Ox);
      return;
    }
    catch (Exception localException) {}
  }
  
  private static class WJ
  {
    private static SystemAlarmReceiver WJ = new SystemAlarmReceiver();
  }
}
