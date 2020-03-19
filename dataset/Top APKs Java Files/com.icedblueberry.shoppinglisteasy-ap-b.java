package ap;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Environment;
import android.preference.PreferenceManager;
import ax.m;
import com.icedblueberry.todo.MyApplication;
import com.icedblueberry.todo.f;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public enum b
{
  private final String bTS = "d2d5a6c731bc654110634921bd08060b";
  private m bTT;
  public volatile boolean bTU = false;
  public volatile boolean bTV = false;
  private String bTW;
  
  private b() {}
  
  private void RA()
  {
    k("RegisteredSuccesfully", 0);
    k("DragDropCount", 0);
  }
  
  @TargetApi(17)
  private static boolean RD()
  {
    return (e.Sa()) && (MyApplication.getContext().getResources().getConfiguration().getLayoutDirection() == 1);
  }
  
  private int fb(String paramString)
  {
    SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext());
    int i = localSharedPreferences.getInt(paramString, 0) + 1;
    localSharedPreferences.edit().putInt(paramString, i).commit();
    return i;
  }
  
  private int fc(String paramString)
  {
    return PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext()).getInt(paramString, 0);
  }
  
  private void k(String paramString, int paramInt)
  {
    PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext()).edit().putInt(paramString, paramInt).commit();
  }
  
  /* Error */
  private void t(Activity paramActivity)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: invokestatic 64	com/icedblueberry/todo/MyApplication:getContext	()Landroid/content/Context;
    //   5: astore 9
    //   7: invokestatic 123	java/util/Calendar:getInstance	()Ljava/util/Calendar;
    //   10: bipush 6
    //   12: invokevirtual 127	java/util/Calendar:get	(I)I
    //   15: istore_2
    //   16: aload 9
    //   18: invokestatic 91	android/preference/PreferenceManager:getDefaultSharedPreferences	(Landroid/content/Context;)Landroid/content/SharedPreferences;
    //   21: astore 7
    //   23: aload 7
    //   25: ldc -127
    //   27: iconst_m1
    //   28: invokeinterface 97 3 0
    //   33: istore_3
    //   34: iload_3
    //   35: iconst_m1
    //   36: if_icmpne +110 -> 146
    //   39: ldc -125
    //   41: astore 5
    //   43: ldc -125
    //   45: astore 6
    //   47: aload_0
    //   48: iconst_1
    //   49: putfield 33	ap/b:bTU	Z
    //   52: aload_0
    //   53: invokevirtual 134	ap/b:Ry	()V
    //   56: aload_0
    //   57: invokevirtual 137	ap/b:Rz	()V
    //   60: aload 9
    //   62: invokevirtual 141	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   65: aload 9
    //   67: invokevirtual 145	android/content/Context:getPackageName	()Ljava/lang/String;
    //   70: iconst_0
    //   71: invokevirtual 151	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   74: getfield 156	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   77: astore 8
    //   79: aload 9
    //   81: invokevirtual 141	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   84: aload 9
    //   86: invokevirtual 145	android/content/Context:getPackageName	()Ljava/lang/String;
    //   89: iconst_0
    //   90: invokevirtual 151	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   93: getfield 160	android/content/pm/PackageInfo:versionCode	I
    //   96: istore 4
    //   98: ldc -94
    //   100: iload 4
    //   102: invokestatic 167	ao/b:j	(Ljava/lang/String;I)V
    //   105: new 169	org/json/JSONObject
    //   108: dup
    //   109: invokespecial 171	org/json/JSONObject:<init>	()V
    //   112: astore 9
    //   114: aload 9
    //   116: ldc -83
    //   118: aload 8
    //   120: invokevirtual 177	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   123: pop
    //   124: aload 9
    //   126: ldc -94
    //   128: iload 4
    //   130: invokevirtual 180	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   133: pop
    //   134: aload_0
    //   135: getfield 182	ap/b:bTT	Lax/m;
    //   138: aload 9
    //   140: invokevirtual 188	ax/m:q	(Lorg/json/JSONObject;)V
    //   143: goto +11 -> 154
    //   146: ldc -66
    //   148: astore 5
    //   150: ldc -64
    //   152: astore 6
    //   154: new 194	java/lang/StringBuilder
    //   157: dup
    //   158: ldc -60
    //   160: invokespecial 199	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   163: astore 8
    //   165: aload 8
    //   167: iload_3
    //   168: invokevirtual 203	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   171: pop
    //   172: aload 8
    //   174: ldc -51
    //   176: invokevirtual 208	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   179: pop
    //   180: aload 8
    //   182: iload_2
    //   183: invokevirtual 203	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   186: pop
    //   187: iload_2
    //   188: iload_3
    //   189: if_icmpeq +259 -> 448
    //   192: aload_0
    //   193: ldc -46
    //   195: invokespecial 212	ap/b:fb	(Ljava/lang/String;)I
    //   198: istore_3
    //   199: iload_3
    //   200: iconst_3
    //   201: if_icmplt +265 -> 466
    //   204: ldc -42
    //   206: astore 5
    //   208: goto +258 -> 466
    //   211: aload_0
    //   212: iconst_1
    //   213: putfield 35	ap/b:bTV	Z
    //   216: aload 7
    //   218: invokeinterface 101 1 0
    //   223: ldc -127
    //   225: iload_2
    //   226: invokeinterface 107 3 0
    //   231: invokeinterface 110 1 0
    //   236: pop
    //   237: aload 7
    //   239: invokeinterface 101 1 0
    //   244: ldc -40
    //   246: aload 5
    //   248: invokeinterface 220 3 0
    //   253: invokeinterface 110 1 0
    //   258: pop
    //   259: aload_0
    //   260: ldc -34
    //   262: invokespecial 224	ap/b:fc	(Ljava/lang/String;)I
    //   265: istore_2
    //   266: aload_0
    //   267: ldc 43
    //   269: invokespecial 224	ap/b:fc	(Ljava/lang/String;)I
    //   272: pop
    //   273: invokestatic 230	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   276: invokevirtual 233	java/util/Locale:getLanguage	()Ljava/lang/String;
    //   279: astore 8
    //   281: new 169	org/json/JSONObject
    //   284: dup
    //   285: invokespecial 171	org/json/JSONObject:<init>	()V
    //   288: astore 7
    //   290: aload_0
    //   291: ldc 48
    //   293: invokespecial 224	ap/b:fc	(Ljava/lang/String;)I
    //   296: istore 4
    //   298: aload 7
    //   300: ldc -21
    //   302: aload 5
    //   304: invokevirtual 177	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   307: pop
    //   308: aload 7
    //   310: ldc -19
    //   312: iload_3
    //   313: invokevirtual 180	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   316: pop
    //   317: aload 7
    //   319: ldc -17
    //   321: iconst_0
    //   322: invokevirtual 180	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   325: pop
    //   326: aload 7
    //   328: ldc -15
    //   330: iload_2
    //   331: invokevirtual 180	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   334: pop
    //   335: aload 7
    //   337: ldc -13
    //   339: aload 6
    //   341: invokevirtual 177	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   344: pop
    //   345: aload 7
    //   347: ldc -11
    //   349: iload 4
    //   351: invokevirtual 180	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   354: pop
    //   355: aload 7
    //   357: ldc -9
    //   359: aload 8
    //   361: invokevirtual 177	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   364: pop
    //   365: new 169	org/json/JSONObject
    //   368: dup
    //   369: invokespecial 171	org/json/JSONObject:<init>	()V
    //   372: astore 6
    //   374: aload 6
    //   376: ldc -7
    //   378: aload 5
    //   380: invokevirtual 177	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   383: pop
    //   384: aload_0
    //   385: getfield 182	ap/b:bTT	Lax/m;
    //   388: aload 6
    //   390: invokevirtual 252	ax/m:p	(Lorg/json/JSONObject;)V
    //   393: aload_0
    //   394: ldc -2
    //   396: aload 7
    //   398: invokevirtual 258	ap/b:c	(Ljava/lang/String;Lorg/json/JSONObject;)V
    //   401: new 260	java/util/HashMap
    //   404: dup
    //   405: invokespecial 261	java/util/HashMap:<init>	()V
    //   408: astore 6
    //   410: aload 6
    //   412: ldc -21
    //   414: aload 5
    //   416: invokeinterface 266 3 0
    //   421: pop
    //   422: aload 6
    //   424: ldc -19
    //   426: iload_3
    //   427: invokestatic 272	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   430: invokeinterface 266 3 0
    //   435: pop
    //   436: aload_1
    //   437: invokevirtual 278	java/lang/Object:getClass	()Ljava/lang/Class;
    //   440: invokevirtual 283	java/lang/Class:getName	()Ljava/lang/String;
    //   443: pop
    //   444: aload_0
    //   445: invokespecial 285	ap/b:RA	()V
    //   448: aload_0
    //   449: monitorexit
    //   450: return
    //   451: astore_1
    //   452: aload_0
    //   453: monitorexit
    //   454: aload_1
    //   455: athrow
    //   456: astore 8
    //   458: goto -304 -> 154
    //   461: astore 6
    //   463: goto -70 -> 393
    //   466: iload_3
    //   467: bipush 10
    //   469: if_icmplt +8 -> 477
    //   472: ldc_w 287
    //   475: astore 5
    //   477: iload_3
    //   478: bipush 20
    //   480: if_icmplt +8 -> 488
    //   483: ldc_w 289
    //   486: astore 5
    //   488: iload_3
    //   489: bipush 50
    //   491: if_icmplt +8 -> 499
    //   494: ldc_w 291
    //   497: astore 5
    //   499: iload_3
    //   500: bipush 100
    //   502: if_icmplt -291 -> 211
    //   505: ldc_w 293
    //   508: astore 5
    //   510: goto -299 -> 211
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	513	0	this	b
    //   0	513	1	paramActivity	Activity
    //   15	316	2	i	int
    //   33	470	3	j	int
    //   96	254	4	k	int
    //   41	468	5	str	String
    //   45	378	6	localObject1	Object
    //   461	1	6	localJSONException	JSONException
    //   21	376	7	localObject2	Object
    //   77	283	8	localObject3	Object
    //   456	1	8	localException	Exception
    //   5	134	9	localObject4	Object
    // Exception table:
    //   from	to	target	type
    //   2	34	451	finally
    //   47	60	451	finally
    //   60	143	451	finally
    //   154	187	451	finally
    //   192	199	451	finally
    //   211	298	451	finally
    //   298	393	451	finally
    //   393	448	451	finally
    //   60	143	456	java/lang/Exception
    //   298	393	461	org/json/JSONException
  }
  
  public void RB()
  {
    c("ReminderSet", null);
  }
  
  public void RC()
  {
    c("ReminderNotify", null);
  }
  
  public String RE()
  {
    ao.b.R("TestVersion", "version_stable");
    return ao.b.getString("TestVersion", null);
  }
  
  public void RF()
  {
    this.bTT.d("VideoAdStart", null);
  }
  
  public void RG()
  {
    this.bTT.d("VideoReward", null);
  }
  
  public void RH()
  {
    this.bTT.d("CleanList", null);
  }
  
  public int RI()
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("EmptyStart", 0);
      this.bTT.p(localJSONObject);
      return 0;
    }
    catch (Exception localException) {}
    return 0;
  }
  
  public boolean RJ()
  {
    return fb("launch_count_for_app") == 12;
  }
  
  public void RK()
  {
    this.bTT.d("RateApp", null);
  }
  
  public void RL()
  {
    this.bTT.d("Search", null);
  }
  
  public int RM()
  {
    return fc("DelTest");
  }
  
  public void RN()
  {
    int i = e.br(1, 2);
    k("SortTest", i);
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("SortTest", i);
      this.bTT.p(localJSONObject);
      return;
    }
    catch (Exception localException) {}
  }
  
  public int RO()
  {
    return fc("SortTest");
  }
  
  public void RP()
  {
    if (fb("DragDropCount") == 1) {
      this.bTT.d("DragDrop", null);
    }
  }
  
  public void RQ()
  {
    this.bTT.d("NameSet", null);
  }
  
  public void RR()
  {
    this.bTT.d("ListAccess", null);
  }
  
  public void RS()
  {
    this.bTT.d("ListShared", null);
  }
  
  public void RT()
  {
    int i = e.br(1, 2);
    k("BannerTest", i);
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("BannerTest", i);
      this.bTT.p(localJSONObject);
      return;
    }
    catch (Exception localException) {}
  }
  
  public int RU()
  {
    return fc("BannerTest");
  }
  
  public void RV()
  {
    int i = e.br(1, 2);
    k("FirstScrLrg", i);
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("FirstScrLrg", i);
      this.bTT.p(localJSONObject);
      return;
    }
    catch (Exception localException) {}
  }
  
  public int RW()
  {
    return fc("FirstScrLrg");
  }
  
  public void RX()
  {
    this.bTT.d("SendInvis2", null);
  }
  
  public void Rv()
  {
    Context localContext = MyApplication.getContext();
    this.bTT = m.L(localContext, this.bTS);
    JSONObject localJSONObject = new JSONObject();
    for (;;)
    {
      try
      {
        localJSONObject.put("UTeleportPublisher", localContext.getPackageManager().getPackageInfo(localContext.getPackageName(), 0).versionName);
        localJSONObject.put("TestVersion", RE());
        this.bTW = null;
      }
      catch (Exception localException1)
      {
        String str1;
        String str3;
        return;
      }
      try
      {
        str1 = Rw();
      }
      catch (Exception localException2)
      {
        continue;
      }
      try
      {
        str3 = Rx();
      }
      catch (Exception localException3)
      {
        continue;
      }
      try
      {
        this.bTW = PreferenceManager.getDefaultSharedPreferences(localContext).getString("mymetaltype", null);
      }
      catch (Exception localException4)
      {
        continue;
        String str2 = "LTR";
      }
    }
    str1 = "NotInitalized";
    str3 = "NotInitialized";
    localJSONObject.put("DaysUsed", fc("TotalDaysUsed"));
    localJSONObject.put("Csr", str1);
    localJSONObject.put("Photo360", str3);
    if (this.bTW != null) {
      localJSONObject.put("Metal", this.bTW);
    }
    if (RD())
    {
      str1 = "RTL";
      localJSONObject.put("LangDirection", str1);
      this.bTT.p(localJSONObject);
      return;
    }
  }
  
  public String Rw()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    d.a[] arrayOfA = d.a.values();
    int j = arrayOfA.length;
    int i = 0;
    while (i < j)
    {
      d.a localA = arrayOfA[i];
      try
      {
        if (d.a(localA))
        {
          localStringBuilder.append(localA.RY());
          localStringBuilder.append(",");
        }
      }
      catch (Exception localException)
      {
        localException.getMessage();
      }
      i += 1;
    }
    return localStringBuilder.toString();
  }
  
  public String Rx()
  {
    String str1 = "";
    String str2 = str1;
    try
    {
      Iterator localIterator = MyApplication.getContext().getPackageManager().getInstalledApplications(128).iterator();
      for (;;)
      {
        str2 = str1;
        if (!localIterator.hasNext()) {
          break;
        }
        str2 = str1;
        String str3 = ((ApplicationInfo)localIterator.next()).packageName;
        if (str3 != null)
        {
          str2 = str1;
          if (!str3.toLowerCase().contains("theta"))
          {
            str2 = str1;
            if (!str3.toLowerCase().contains("gear360")) {}
          }
          else
          {
            str2 = str1;
            StringBuilder localStringBuilder = new StringBuilder();
            str2 = str1;
            localStringBuilder.append(str1);
            str2 = str1;
            localStringBuilder.append(str3);
            str2 = str1;
            localStringBuilder.append(",");
            str2 = str1;
            str1 = localStringBuilder.toString();
          }
        }
      }
      return str1;
    }
    catch (Exception localException) {}
    return str2;
  }
  
  public void Ry()
  {
    try
    {
      if (!Environment.getExternalStorageState().equals("mounted")) {
        fd("SdCardUnAvailable");
      }
      return;
    }
    catch (Exception localException)
    {
      new StringBuilder("Exception while checking for SD Card: ").append(localException);
    }
  }
  
  public void Rz()
  {
    Object localObject = MyApplication.getContext();
    try
    {
      localObject = ((Context)localObject).getPackageManager();
    }
    catch (Exception localException)
    {
      label19:
      new StringBuilder("Exception while checking for market ").append(localException);
      return;
    }
    try
    {
      ((PackageManager)localObject).getPackageInfo("com.android.vending", 0);
      return;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      break label19;
    }
    fd("NoMarketFound");
  }
  
  public void bL(boolean paramBoolean)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("HasAds", paramBoolean);
      this.bTT.p(localJSONObject);
      return;
    }
    catch (JSONException localJSONException)
    {
      for (;;) {}
    }
  }
  
  protected void c(String paramString, JSONObject paramJSONObject)
  {
    this.bTT.d(paramString, paramJSONObject);
  }
  
  public void fd(String paramString)
  {
    new HashMap().put("ErrorString", paramString);
  }
  
  public void fe(String paramString)
  {
    int i = fb("AdClickCount");
    JSONObject localJSONObject1 = new JSONObject();
    JSONObject localJSONObject2 = new JSONObject();
    try
    {
      localJSONObject1.put("AdClickCount", i);
      localJSONObject2.put("TypeOfClick", paramString);
    }
    catch (JSONException paramString)
    {
      paramString.printStackTrace();
    }
    this.bTT.p(localJSONObject1);
    c("AdClicked", localJSONObject2);
  }
  
  public void ff(String paramString)
  {
    boolean bool;
    if (paramString != null) {
      bool = f.eR(paramString);
    } else {
      bool = false;
    }
    paramString = new JSONObject();
    int i = fb("tasksTotalCount");
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("TaskCount", i);
      paramString.put("Grocery", bool);
      this.bTT.p(localJSONObject);
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
    }
    c("TaskCreated", paramString);
  }
  
  public void fg(String paramString)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("how", paramString);
      this.bTT.d("EditRow", localJSONObject);
      return;
    }
    catch (JSONException paramString)
    {
      for (;;) {}
    }
  }
  
  public void flush()
  {
    this.bTT.flush();
  }
  
  public void r(Activity paramActivity)
  {
    t(paramActivity);
  }
  
  public void s(Activity paramActivity)
  {
    t(paramActivity);
  }
}
