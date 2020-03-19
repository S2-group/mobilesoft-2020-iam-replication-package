package com.changdupay.util;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.changdupay.a.a.b.e;
import com.changdupay.a.a.b.i;
import com.changdupay.app.PayResultActivity;
import com.changdupay.d.c;
import com.changdupay.l.b.i;
import com.changdupay.l.e.ab;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class aa
{
  public static final String a = "CDPayConfig";
  public static final String b = "money_key";
  public static final String c = "save_pay_info";
  public static double d = 10.0D;
  public static Integer[] e = { Integer.valueOf(3), Integer.valueOf(19), Integer.valueOf(14), Integer.valueOf(18) };
  public static Integer[] f = { Integer.valueOf(b.i.eN), Integer.valueOf(b.i.eP), Integer.valueOf(b.i.eP), Integer.valueOf(b.i.eO) };
  public static Integer[] g = { Integer.valueOf(b.e.Q), Integer.valueOf(b.e.T), Integer.valueOf(b.e.T), Integer.valueOf(b.e.S) };
  public static Integer[] h = { Integer.valueOf(b.e.bi), Integer.valueOf(b.e.bm), Integer.valueOf(b.e.bm), Integer.valueOf(b.e.bi) };
  public static Integer[] i = { Integer.valueOf(18) };
  
  public aa() {}
  
  public static float a(int paramInt, float paramFloat)
  {
    if (e.b() == null) {
      return paramFloat;
    }
    return TypedValue.applyDimension(paramInt, paramFloat, e.b().getResources().getDisplayMetrics());
  }
  
  public static int a(float paramFloat)
  {
    return (int)(b(paramFloat) + 0.5F);
  }
  
  public static a a(Context paramContext, int paramInt, String paramString)
  {
    paramInt = Arrays.asList(e).indexOf(Integer.valueOf(paramInt));
    if (paramInt >= 0)
    {
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append(paramContext.getString(f[paramInt].intValue()));
      ((StringBuilder)localObject).append(" (");
      ((StringBuilder)localObject).append((int)Double.parseDouble(paramString));
      ((StringBuilder)localObject).append("元)");
      paramString = ((StringBuilder)localObject).toString();
      localObject = paramContext.getResources().getDrawable(h[paramInt].intValue());
      paramContext = paramContext.getResources().getDrawable(g[paramInt].intValue());
      a localA = new a();
      localA.b = ((Drawable)localObject);
      localA.a = paramString;
      localA.c = paramContext;
      return localA;
    }
    return null;
  }
  
  public static Boolean a()
  {
    try
    {
      Object localObject = e.b().getFilesDir();
      if (localObject != null)
      {
        localObject = ((File)localObject).getAbsolutePath();
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append((String)localObject);
        localStringBuilder.append("/");
        localStringBuilder.append("defaultconfig.xml");
        if (new File(localStringBuilder.toString()).exists()) {
          return Boolean.valueOf(true);
        }
        return Boolean.valueOf(false);
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return Boolean.valueOf(false);
  }
  
  public static Boolean a(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    int j = 0;
    while (j < paramContext.size())
    {
      if (((PackageInfo)paramContext.get(j)).packageName.equalsIgnoreCase(paramString)) {
        return Boolean.valueOf(true);
      }
      j += 1;
    }
    return Boolean.valueOf(false);
  }
  
  public static String a(Context paramContext, int paramInt)
  {
    paramInt = Arrays.asList(e).indexOf(Integer.valueOf(paramInt));
    if (paramInt >= 0) {
      return paramContext.getString(f[paramInt].intValue());
    }
    return null;
  }
  
  /* Error */
  public static String a(java.io.InputStream paramInputStream)
  {
    // Byte code:
    //   0: new 229	java/io/BufferedReader
    //   3: dup
    //   4: new 231	java/io/InputStreamReader
    //   7: dup
    //   8: aload_0
    //   9: invokespecial 234	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   12: invokespecial 237	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   15: astore_2
    //   16: new 121	java/lang/StringBuilder
    //   19: dup
    //   20: invokespecial 122	java/lang/StringBuilder:<init>	()V
    //   23: astore_1
    //   24: aload_2
    //   25: invokevirtual 240	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   28: astore_3
    //   29: aload_3
    //   30: ifnull +12 -> 42
    //   33: aload_1
    //   34: aload_3
    //   35: invokevirtual 134	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   38: pop
    //   39: goto -15 -> 24
    //   42: aload_0
    //   43: invokevirtual 245	java/io/InputStream:close	()V
    //   46: goto +24 -> 70
    //   49: astore_0
    //   50: aload_0
    //   51: invokevirtual 246	java/io/IOException:printStackTrace	()V
    //   54: goto +16 -> 70
    //   57: astore_1
    //   58: goto +17 -> 75
    //   61: astore_2
    //   62: aload_2
    //   63: invokevirtual 246	java/io/IOException:printStackTrace	()V
    //   66: aload_0
    //   67: invokevirtual 245	java/io/InputStream:close	()V
    //   70: aload_1
    //   71: invokevirtual 151	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   74: areturn
    //   75: aload_0
    //   76: invokevirtual 245	java/io/InputStream:close	()V
    //   79: goto +8 -> 87
    //   82: astore_0
    //   83: aload_0
    //   84: invokevirtual 246	java/io/IOException:printStackTrace	()V
    //   87: aload_1
    //   88: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	89	0	paramInputStream	java.io.InputStream
    //   23	11	1	localStringBuilder	StringBuilder
    //   57	31	1	localObject	Object
    //   15	10	2	localBufferedReader	java.io.BufferedReader
    //   61	2	2	localIOException	IOException
    //   28	7	3	str	String
    // Exception table:
    //   from	to	target	type
    //   42	46	49	java/io/IOException
    //   66	70	49	java/io/IOException
    //   24	29	57	finally
    //   33	39	57	finally
    //   62	66	57	finally
    //   24	29	61	java/io/IOException
    //   33	39	61	java/io/IOException
    //   75	79	82	java/io/IOException
  }
  
  public static String a(String paramString)
  {
    return e.b().getSharedPreferences("CDPayConfig", 0).getString(paramString, "");
  }
  
  public static String a(String paramString, Context paramContext)
  {
    if (TextUtils.isEmpty(paramString)) {
      return "";
    }
    if (paramString.length() >= 7)
    {
      paramString.substring(0, 4);
      paramString = paramString.substring(5, 7);
      Object localObject = new Date();
      Calendar.getInstance().setTime((Date)localObject);
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(paramString);
      ((StringBuilder)localObject).append(paramContext.getString(b.i.bu));
      return ((StringBuilder)localObject).toString();
    }
    return "";
  }
  
  private static void a(ActionBar paramActionBar, boolean paramBoolean)
  {
    try
    {
      Method localMethod = Class.forName("android.app.ActionBar").getMethod("setActionBarViewCollapsable", new Class[] { Boolean.TYPE });
      try
      {
        localMethod.invoke(paramActionBar, new Object[] { Boolean.valueOf(paramBoolean) });
        return;
      }
      catch (InvocationTargetException paramActionBar)
      {
        paramActionBar.printStackTrace();
        return;
      }
      catch (IllegalAccessException paramActionBar)
      {
        paramActionBar.printStackTrace();
        return;
      }
      catch (IllegalArgumentException paramActionBar)
      {
        paramActionBar.printStackTrace();
        return;
      }
      return;
    }
    catch (ClassNotFoundException paramActionBar)
    {
      paramActionBar.printStackTrace();
      return;
    }
    catch (NoSuchMethodException paramActionBar)
    {
      paramActionBar.printStackTrace();
      return;
    }
    catch (SecurityException paramActionBar)
    {
      paramActionBar.printStackTrace();
    }
  }
  
  public static void a(Activity paramActivity)
  {
    ((InputMethodManager)paramActivity.getSystemService("input_method")).hideSoftInputFromWindow(paramActivity.getCurrentFocus().getWindowToken(), 2);
  }
  
  public static void a(Context paramContext, int paramInt, double paramDouble)
  {
    if (d(paramContext) == null) {
      b(paramContext, paramInt, paramDouble);
    }
  }
  
  public static void a(Context paramContext, GridView paramGridView, int paramInt)
  {
    Object localObject = paramGridView.getAdapter();
    if (localObject == null) {
      return;
    }
    int m = ((ListAdapter)localObject).getCount();
    int k = m / paramInt;
    int j = k;
    if (m > 0)
    {
      j = k;
      if (m % paramInt != 0) {
        j = k + 1;
      }
    }
    paramInt = 0;
    if (j > 0)
    {
      localObject = ((ListAdapter)localObject).getView(0, null, null);
      ((View)localObject).measure(0, 0);
      paramInt = ((View)localObject).getMeasuredHeight() * j + j * g.a(paramContext, 10.0F);
    }
    paramContext = paramGridView.getLayoutParams();
    paramContext.height = paramInt;
    paramGridView.setLayoutParams(paramContext);
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2)
  {
    if (paramContext != null)
    {
      paramString1 = paramContext.getPackageManager().getLaunchIntentForPackage(paramString1);
      if (!TextUtils.isEmpty(paramString2)) {
        paramString1.putExtra("params", paramString2);
      }
      if (paramString1 != null) {
        paramContext.startActivity(paramString1);
      }
    }
  }
  
  public static void a(Context paramContext, boolean paramBoolean)
  {
    paramContext = paramContext.getSharedPreferences("CDPayConfig", 0).edit();
    paramContext.putBoolean("hasLogined", paramBoolean);
    paramContext.commit();
  }
  
  public static void a(ListView paramListView)
  {
    ListAdapter localListAdapter = paramListView.getAdapter();
    if (localListAdapter == null) {
      return;
    }
    int j = 0;
    int k = 0;
    while (j < localListAdapter.getCount())
    {
      localObject = localListAdapter.getView(j, null, paramListView);
      ((View)localObject).measure(0, 0);
      k += ((View)localObject).getMeasuredHeight();
      j += 1;
    }
    Object localObject = paramListView.getLayoutParams();
    ((ViewGroup.LayoutParams)localObject).height = (k + paramListView.getDividerHeight() * (localListAdapter.getCount() - 1));
    paramListView.setLayoutParams((ViewGroup.LayoutParams)localObject);
  }
  
  public static void a(String paramString, int paramInt)
  {
    SharedPreferences.Editor localEditor = e.b().getSharedPreferences("CDPayConfig", 0).edit();
    localEditor.putInt(paramString, paramInt);
    localEditor.commit();
  }
  
  public static void a(String paramString, Activity paramActivity)
  {
    Intent localIntent = new Intent(e.b(), PayResultActivity.class);
    localIntent.putExtra("bSuccess", true);
    localIntent.putExtra("payResultMsg", paramString);
    localIntent.putExtra("needQuitOrNot", true);
    if (paramActivity != null)
    {
      paramActivity.startActivityForResult(localIntent, 9112);
      return;
    }
    e.b().startActivity(localIntent);
  }
  
  public static void a(String paramString1, String paramString2)
  {
    SharedPreferences.Editor localEditor = e.b().getSharedPreferences("CDPayConfig", 0).edit();
    localEditor.putString(paramString1, paramString2);
    localEditor.commit();
  }
  
  public static boolean a(Activity paramActivity, int paramInt)
  {
    return (b(paramActivity) & 0xF0) == paramInt;
  }
  
  public static boolean a(Context paramContext)
  {
    return paramContext.getSharedPreferences("CDPayConfig", 0).getBoolean("hasLogined", false);
  }
  
  public static boolean a(Context paramContext, ab paramAb)
  {
    if ((paramContext != null) && (b(paramContext, com.changdupay.j.a.c)))
    {
      h.a(paramContext);
      Intent localIntent = paramContext.getPackageManager().getLaunchIntentForPackage(com.changdupay.j.a.c);
      if (localIntent != null)
      {
        localIntent.putExtra("requestContent", paramAb);
        if (!(paramContext instanceof Activity)) {
          paramContext.startActivity(localIntent);
        }
      }
      return true;
    }
    return false;
  }
  
  public static double b(Context paramContext)
  {
    try
    {
      double d1 = Double.parseDouble(paramContext.getPackageManager().getPackageInfo("com.changdupay.android.app", 0).versionName);
      return d1;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return 0.0D;
  }
  
  public static float b(float paramFloat)
  {
    if (e.b() == null) {
      return paramFloat;
    }
    return TypedValue.applyDimension(1, paramFloat, e.b().getResources().getDisplayMetrics());
  }
  
  public static int b(Activity paramActivity)
  {
    if (paramActivity != null)
    {
      paramActivity = paramActivity.getWindow();
      if (paramActivity != null)
      {
        paramActivity = paramActivity.getAttributes();
        if (paramActivity != null) {
          return paramActivity.softInputMode;
        }
      }
    }
    return 0;
  }
  
  public static int b(String paramString)
  {
    return e.b().getSharedPreferences("CDPayConfig", 0).getInt(paramString, 9999);
  }
  
  public static String b(String paramString, Context paramContext)
  {
    if (TextUtils.isEmpty(paramString)) {
      return "";
    }
    if (paramString.length() >= "2013-11-11 00:00:00".length())
    {
      paramString.substring(0, 4);
      paramString.substring(5, 7);
      paramString.substring(8, 10);
      paramContext = new Date();
      Calendar.getInstance().setTime(paramContext);
      paramContext = paramString.substring(11, 19);
      try
      {
        paramString = String.format("%s %s", new Object[] { paramString.substring(5, 10), paramContext });
        return paramString;
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
      }
    }
    return "";
  }
  
  /* Error */
  public static void b(Context paramContext, int paramInt, double paramDouble)
  {
    // Byte code:
    //   0: aload_0
    //   1: ldc 10
    //   3: iconst_0
    //   4: invokevirtual 251	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   7: astore 5
    //   9: new 547	java/io/ByteArrayOutputStream
    //   12: dup
    //   13: invokespecial 548	java/io/ByteArrayOutputStream:<init>	()V
    //   16: astore 6
    //   18: new 550	java/io/DataOutputStream
    //   21: dup
    //   22: aload 6
    //   24: invokespecial 553	java/io/DataOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   27: astore 7
    //   29: getstatic 36	com/changdupay/util/aa:e	[Ljava/lang/Integer;
    //   32: invokestatic 113	java/util/Arrays:asList	([Ljava/lang/Object;)Ljava/util/List;
    //   35: iload_1
    //   36: invokestatic 34	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   39: invokeinterface 557 2 0
    //   44: ifeq +168 -> 212
    //   47: getstatic 73	com/changdupay/util/aa:i	[Ljava/lang/Integer;
    //   50: invokestatic 113	java/util/Arrays:asList	([Ljava/lang/Object;)Ljava/util/List;
    //   53: iload_1
    //   54: invokestatic 34	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   57: invokeinterface 557 2 0
    //   62: ifne +150 -> 212
    //   65: aload_0
    //   66: invokestatic 560	com/changdupay/util/aa:c	(Landroid/content/Context;)Ljava/lang/String;
    //   69: astore_0
    //   70: aload_0
    //   71: invokestatic 563	java/lang/Double:valueOf	(Ljava/lang/String;)Ljava/lang/Double;
    //   74: invokevirtual 567	java/lang/Double:doubleValue	()D
    //   77: putstatic 569	com/changdupay/util/aa:d	D
    //   80: dload_2
    //   81: getstatic 569	com/changdupay/util/aa:d	D
    //   84: dcmpl
    //   85: ifle +6 -> 91
    //   88: goto +7 -> 95
    //   91: getstatic 569	com/changdupay/util/aa:d	D
    //   94: dstore_2
    //   95: new 121	java/lang/StringBuilder
    //   98: dup
    //   99: invokespecial 122	java/lang/StringBuilder:<init>	()V
    //   102: astore_0
    //   103: aload_0
    //   104: dload_2
    //   105: invokevirtual 572	java/lang/StringBuilder:append	(D)Ljava/lang/StringBuilder;
    //   108: pop
    //   109: aload_0
    //   110: ldc -3
    //   112: invokevirtual 134	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   115: pop
    //   116: aload_0
    //   117: invokevirtual 151	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   120: invokevirtual 576	java/lang/String:getBytes	()[B
    //   123: astore_0
    //   124: aload_0
    //   125: arraylength
    //   126: istore 4
    //   128: aload 7
    //   130: iload_1
    //   131: invokevirtual 580	java/io/DataOutputStream:writeInt	(I)V
    //   134: aload 7
    //   136: iload 4
    //   138: invokevirtual 580	java/io/DataOutputStream:writeInt	(I)V
    //   141: aload 7
    //   143: aload_0
    //   144: invokevirtual 584	java/io/DataOutputStream:write	([B)V
    //   147: aload 6
    //   149: invokevirtual 585	java/io/ByteArrayOutputStream:close	()V
    //   152: aload 7
    //   154: invokevirtual 586	java/io/DataOutputStream:close	()V
    //   157: goto +15 -> 172
    //   160: astore_0
    //   161: goto +39 -> 200
    //   164: astore_0
    //   165: aload_0
    //   166: invokevirtual 194	java/lang/Exception:printStackTrace	()V
    //   169: goto -22 -> 147
    //   172: aload 6
    //   174: invokevirtual 587	java/io/ByteArrayOutputStream:toString	()Ljava/lang/String;
    //   177: astore_0
    //   178: aload 5
    //   180: invokeinterface 426 1 0
    //   185: ldc 16
    //   187: aload_0
    //   188: invokeinterface 475 3 0
    //   193: invokeinterface 437 1 0
    //   198: pop
    //   199: return
    //   200: aload 6
    //   202: invokevirtual 585	java/io/ByteArrayOutputStream:close	()V
    //   205: aload 7
    //   207: invokevirtual 586	java/io/DataOutputStream:close	()V
    //   210: aload_0
    //   211: athrow
    //   212: return
    //   213: astore_0
    //   214: goto -134 -> 80
    //   217: astore_0
    //   218: goto -46 -> 172
    //   221: astore 5
    //   223: goto -13 -> 210
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	226	0	paramContext	Context
    //   0	226	1	paramInt	int
    //   0	226	2	paramDouble	double
    //   126	11	4	j	int
    //   7	172	5	localSharedPreferences	SharedPreferences
    //   221	1	5	localException	Exception
    //   16	185	6	localByteArrayOutputStream	java.io.ByteArrayOutputStream
    //   27	179	7	localDataOutputStream	java.io.DataOutputStream
    // Exception table:
    //   from	to	target	type
    //   128	147	160	finally
    //   165	169	160	finally
    //   128	147	164	java/lang/Exception
    //   70	80	213	java/lang/Throwable
    //   147	157	217	java/lang/Exception
    //   200	210	221	java/lang/Exception
  }
  
  public static void b(String paramString, int paramInt)
  {
    if (e.b() == null) {
      return;
    }
    Object localObject = e.b();
    int k = 0;
    localObject = ((Context)localObject).getSharedPreferences("CDPayConfig", 0).edit();
    ArrayList localArrayList = u.a().b(i.c).a;
    int j = 0;
    while (j < localArrayList.size())
    {
      if (TextUtils.equals(paramString, ((v.d)localArrayList.get(j)).a))
      {
        int m = ((v.d)localArrayList.get(j)).h;
        ((v.d)localArrayList.get(j)).h = paramInt;
        j = m;
        break label117;
      }
      j += 1;
    }
    j = 9999;
    label117:
    while (k < localArrayList.size())
    {
      if ((!TextUtils.equals(paramString, ((v.d)localArrayList.get(k)).a)) && (((v.d)localArrayList.get(k)).h != 9999) && ((((v.d)localArrayList.get(k)).h <= j) || (j == 9999)))
      {
        v.d localD = (v.d)localArrayList.get(k);
        localD.h += 1;
        ((SharedPreferences.Editor)localObject).putInt(((v.d)localArrayList.get(k)).a, ((v.d)localArrayList.get(k)).h);
      }
      k += 1;
    }
    ((SharedPreferences.Editor)localObject).putInt(paramString, paramInt);
    ((SharedPreferences.Editor)localObject).commit();
  }
  
  public static void b(String paramString, Activity paramActivity)
  {
    Intent localIntent = new Intent(e.b(), PayResultActivity.class);
    localIntent.putExtra("bSuccess", false);
    localIntent.putExtra("payResultMsg", paramString);
    localIntent.putExtra("needQuitOrNot", true);
    if (paramActivity != null)
    {
      paramActivity.startActivityForResult(localIntent, 9112);
      return;
    }
    e.b().startActivity(localIntent);
  }
  
  public static void b(String paramString1, String paramString2)
  {
    try
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("chmod ");
      localStringBuilder.append(paramString1);
      localStringBuilder.append(" ");
      localStringBuilder.append(paramString2);
      paramString1 = localStringBuilder.toString();
      Runtime.getRuntime().exec(paramString1);
      return;
    }
    catch (IOException paramString1)
    {
      paramString1.printStackTrace();
    }
  }
  
  private static boolean b()
  {
    try
    {
      boolean bool = ((Boolean)Class.forName("android.os.Build").getMethod("hasSmartBar", new Class[0]).invoke(null, new Object[0])).booleanValue();
      return bool;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      if (Build.DEVICE.equals("mx2")) {
        return true;
      }
      if (!Build.DEVICE.equals("mx")) {
        return !Build.DEVICE.equals("m9");
      }
    }
    return false;
  }
  
  public static boolean b(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    for (;;)
    {
      int j;
      try
      {
        List localList = paramContext.getInstalledPackages(0);
        j = 0;
        if (j >= localList.size()) {
          break label89;
        }
        if (((PackageInfo)localList.get(j)).packageName.equalsIgnoreCase(paramString))
        {
          bool = true;
          if (paramContext.getPackageInfo(paramString, 8192) != null) {
            bool = true;
          }
          paramContext = paramContext.getPackageInfo(paramString, 1);
          if (paramContext != null) {
            return true;
          }
          return bool;
        }
      }
      catch (Exception paramContext)
      {
        return false;
      }
      j += 1;
      continue;
      label89:
      boolean bool = false;
    }
  }
  
  public static int c(String paramString)
  {
    return e.b().getSharedPreferences("CDPayConfig", 0).getInt(paramString, 0);
  }
  
  public static String c(Context paramContext)
  {
    return paramContext.getSharedPreferences("CDPayConfig", 0).getString("money_key", "");
  }
  
  public static void c(Activity paramActivity)
  {
    if (paramActivity != null) {
      try
      {
        ((InputMethodManager)paramActivity.getSystemService("input_method")).hideSoftInputFromWindow(paramActivity.getCurrentFocus().getWindowToken(), 2);
        return;
      }
      catch (Exception paramActivity)
      {
        paramActivity.printStackTrace();
      }
    }
  }
  
  public static void c(Context paramContext, String paramString)
  {
    a(paramContext, paramString, null);
  }
  
  /* Error */
  @android.annotation.TargetApi(5)
  public static android.util.Pair<Integer, String> d(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: ldc 10
    //   3: iconst_0
    //   4: invokevirtual 251	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   7: ldc 16
    //   9: aconst_null
    //   10: invokeinterface 258 3 0
    //   15: astore_0
    //   16: aload_0
    //   17: ifnonnull +5 -> 22
    //   20: aconst_null
    //   21: areturn
    //   22: new 660	java/io/ByteArrayInputStream
    //   25: dup
    //   26: aload_0
    //   27: invokevirtual 576	java/lang/String:getBytes	()[B
    //   30: invokespecial 662	java/io/ByteArrayInputStream:<init>	([B)V
    //   33: astore_0
    //   34: new 664	java/io/DataInputStream
    //   37: dup
    //   38: aload_0
    //   39: invokespecial 665	java/io/DataInputStream:<init>	(Ljava/io/InputStream;)V
    //   42: astore_2
    //   43: aload_2
    //   44: invokevirtual 668	java/io/DataInputStream:readInt	()I
    //   47: istore_1
    //   48: aload_2
    //   49: invokevirtual 668	java/io/DataInputStream:readInt	()I
    //   52: newarray byte
    //   54: astore_3
    //   55: aload_2
    //   56: aload_3
    //   57: invokevirtual 672	java/io/DataInputStream:read	([B)I
    //   60: pop
    //   61: new 219	java/lang/String
    //   64: dup
    //   65: aload_3
    //   66: invokespecial 673	java/lang/String:<init>	([B)V
    //   69: astore_3
    //   70: getstatic 73	com/changdupay/util/aa:i	[Ljava/lang/Integer;
    //   73: invokestatic 113	java/util/Arrays:asList	([Ljava/lang/Object;)Ljava/util/List;
    //   76: iload_1
    //   77: invokestatic 34	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   80: invokeinterface 557 2 0
    //   85: ifne +26 -> 111
    //   88: new 675	android/util/Pair
    //   91: dup
    //   92: iload_1
    //   93: invokestatic 34	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   96: aload_3
    //   97: invokespecial 678	android/util/Pair:<init>	(Ljava/lang/Object;Ljava/lang/Object;)V
    //   100: astore_3
    //   101: aload_0
    //   102: invokevirtual 679	java/io/ByteArrayInputStream:close	()V
    //   105: aload_2
    //   106: invokevirtual 680	java/io/DataInputStream:close	()V
    //   109: aload_3
    //   110: areturn
    //   111: aload_0
    //   112: invokevirtual 679	java/io/ByteArrayInputStream:close	()V
    //   115: aload_2
    //   116: invokevirtual 680	java/io/DataInputStream:close	()V
    //   119: aconst_null
    //   120: areturn
    //   121: astore_3
    //   122: goto +11 -> 133
    //   125: astore_3
    //   126: aload_3
    //   127: invokevirtual 194	java/lang/Exception:printStackTrace	()V
    //   130: goto -19 -> 111
    //   133: aload_0
    //   134: invokevirtual 679	java/io/ByteArrayInputStream:close	()V
    //   137: aload_2
    //   138: invokevirtual 680	java/io/DataInputStream:close	()V
    //   141: aload_3
    //   142: athrow
    //   143: astore_0
    //   144: aload_3
    //   145: areturn
    //   146: astore_0
    //   147: aconst_null
    //   148: areturn
    //   149: astore_0
    //   150: goto -9 -> 141
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	153	0	paramContext	Context
    //   47	46	1	j	int
    //   42	96	2	localDataInputStream	java.io.DataInputStream
    //   54	56	3	localObject1	Object
    //   121	1	3	localObject2	Object
    //   125	20	3	localException	Exception
    // Exception table:
    //   from	to	target	type
    //   43	101	121	finally
    //   126	130	121	finally
    //   43	101	125	java/lang/Exception
    //   101	109	143	java/lang/Exception
    //   111	119	146	java/lang/Exception
    //   133	141	149	java/lang/Exception
  }
  
  public static String d(String paramString)
  {
    if (paramString != null) {
      return paramString.replaceAll("\"", "");
    }
    return null;
  }
  
  public static void d(Activity paramActivity)
  {
    if (paramActivity != null) {
      if (b())
      {
        paramActivity = paramActivity.getActionBar();
        if (paramActivity != null)
        {
          a(paramActivity, true);
          paramActivity.setDisplayOptions(0);
        }
      }
      else
      {
        paramActivity.requestWindowFeature(1);
      }
    }
  }
  
  public static void d(Context paramContext, String paramString)
  {
    if (paramContext != null)
    {
      Intent localIntent = new Intent("android.intent.action.VIEW");
      localIntent.setDataAndType(Uri.fromFile(new File(paramString)), "application/vnd.android.package-archive");
      paramContext.startActivity(localIntent);
    }
  }
  
  public static void e(Activity paramActivity)
  {
    com.changdupay.d.a localA = new com.changdupay.d.a();
    localA.b = paramActivity;
    c.a.a(1, 10008, localA);
  }
  
  public static void e(Context paramContext, String paramString)
  {
    paramContext.getSharedPreferences("CDPayConfig", 0).edit().putString("money_key", paramString).commit();
  }
  
  public static boolean e(String paramString)
  {
    if (paramString.length() != 11) {
      return false;
    }
    paramString = paramString.getBytes();
    int j = 0;
    while (j < paramString.length) {
      if (paramString[j] >= 48)
      {
        if (paramString[j] > 57) {
          return false;
        }
        j += 1;
      }
      else
      {
        return false;
      }
    }
    return true;
  }
  
  public static String f(String paramString)
  {
    return paramString.replace("+", "~").replace("/", "@").replace("=", "$");
  }
  
  public static class a
  {
    public String a;
    public Drawable b;
    public Drawable c;
    
    public a() {}
  }
}
