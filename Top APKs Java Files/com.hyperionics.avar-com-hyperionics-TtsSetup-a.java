package com.hyperionics.TtsSetup;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Build.VERSION;
import android.speech.tts.TextToSpeech;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.CheckBox;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class a
{
  private static Application a = ;
  private static ArrayList b = new ArrayList();
  
  public static float a(float paramFloat, Context paramContext)
  {
    return paramContext.getResources().getDisplayMetrics().densityDpi / 160.0F * paramFloat;
  }
  
  public static float a(int paramInt)
  {
    return (((0xFF0000 & paramInt) >> 16) * 0.299F + ((0xFF00 & paramInt) >> 8) * 0.587F + (paramInt & 0xFF) * 0.114F) / 256.0F;
  }
  
  public static int a(String paramString1, String paramString2)
  {
    int i = 0;
    int k = 0;
    if (a == null) {
      return k;
    }
    AssetManager localAssetManager = a.getAssets();
    String str1 = paramString2;
    if (paramString2 == null) {
      str1 = a.getFilesDir().toString();
    }
    for (;;)
    {
      try
      {
        paramString2 = localAssetManager.list(paramString1);
        int j = paramString2.length;
        if (j == 0) {
          break;
        }
        int m = paramString2.length;
        j = 0;
        k = i;
        if (j >= m) {
          break;
        }
        String str2 = paramString2[j];
        String str3 = paramString1 + "/" + str2;
        try
        {
          InputStream localInputStream = localAssetManager.open(str3);
          Object localObject = str1 + "/" + str2;
          long l = g();
          File localFile = new File((String)localObject);
          if ((l > 0L) && (localFile.exists()) && (localFile.lastModified() >= l))
          {
            localInputStream.close();
          }
          else
          {
            new File((String)localObject).getParentFile().mkdirs();
            localObject = new FileOutputStream((String)localObject);
            b.a(localInputStream, (OutputStream)localObject);
            localInputStream.close();
            ((OutputStream)localObject).flush();
            ((OutputStream)localObject).close();
            i += 1;
          }
        }
        catch (IOException localIOException)
        {
          i += a(str3, str1 + "/" + str2);
        }
        j += 1;
      }
      catch (IOException paramString1)
      {
        return 0;
      }
    }
  }
  
  public static Application a()
  {
    if (a == null) {}
    try
    {
      a = (Application)Class.forName("android.app.ActivityThread").getMethod("currentApplication", new Class[0]).invoke(null, (Object[])null);
      return a;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
  }
  
  public static b a(Context paramContext, boolean paramBoolean, String paramString1, String paramString2, c paramC)
  {
    return a(paramContext, paramBoolean, paramString1, paramString2, paramC, false, null);
  }
  
  public static b a(Context paramContext, boolean paramBoolean1, String paramString1, String paramString2, c paramC, boolean paramBoolean2, DialogInterface.OnCancelListener paramOnCancelListener)
  {
    b localB = new b(paramContext);
    if (paramC != null)
    {
      localB.b = paramC;
      paramC.a = localB;
    }
    if (paramBoolean1)
    {
      if (paramString1 != null) {
        break label87;
      }
      paramString1 = "";
      if (paramString2 != null) {
        break label90;
      }
      paramString2 = "";
    }
    label87:
    label90:
    for (;;)
    {
      localB.a = ProgressDialog.show(paramContext, paramString1, paramString2, true, paramBoolean2, paramOnCancelListener);
      if (Build.VERSION.SDK_INT < 11) {
        break label93;
      }
      localB.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
      return localB;
      break;
    }
    label93:
    localB.execute(new Void[0]);
    return localB;
  }
  
  public static String a(TextToSpeech paramTextToSpeech)
  {
    int i = 0;
    if (paramTextToSpeech != null)
    {
      Object localObject = paramTextToSpeech.getClass();
      try
      {
        String str = (String)((Class)localObject).getMethod("getCurrentEngine", new Class[0]).invoke(paramTextToSpeech, (Object[])null);
        if (str != null) {
          return str;
        }
        return "";
      }
      catch (Exception localException)
      {
        try
        {
          localObject = ((Class)localObject).getDeclaredField("mCachedParams");
          ((Field)localObject).setAccessible(true);
          paramTextToSpeech = (String[])((Field)localObject).get(paramTextToSpeech);
          while (i < paramTextToSpeech.length - 1)
          {
            if (paramTextToSpeech[i].equals("engine"))
            {
              paramTextToSpeech = paramTextToSpeech[(i + 1)];
              return paramTextToSpeech;
            }
            i += 1;
          }
          return "";
        }
        catch (Exception paramTextToSpeech)
        {
          d.a("Exception " + paramTextToSpeech);
        }
      }
    }
  }
  
  public static StringBuilder a(StringBuilder paramStringBuilder)
  {
    int i = 0;
    localStringBuilder = paramStringBuilder;
    if (paramStringBuilder == null) {
      localStringBuilder = new StringBuilder();
    }
    localStringBuilder.append("Android: " + Build.MODEL + ", brand = " + Build.BRAND + ", OS = " + Build.VERSION.RELEASE + ", SDK version = " + Build.VERSION.SDK_INT + "\n");
    localStringBuilder.append("System lang: " + Locale.getDefault() + "\n");
    Object localObject;
    if (Build.VERSION.SDK_INT >= 16)
    {
      paramStringBuilder = (ActivityManager)a.getSystemService("activity");
      localObject = new ActivityManager.MemoryInfo();
      paramStringBuilder.getMemoryInfo((ActivityManager.MemoryInfo)localObject);
      localStringBuilder.append("totalMem =" + ((ActivityManager.MemoryInfo)localObject).totalMem + "\n");
      localStringBuilder.append("availMem =" + ((ActivityManager.MemoryInfo)localObject).availMem + "\n");
      localStringBuilder.append("lowMemory =" + ((ActivityManager.MemoryInfo)localObject).lowMemory + "\n");
    }
    localStringBuilder.append('\n');
    paramStringBuilder = a.getPackageManager().getInstalledPackages(0);
    while (i < paramStringBuilder.size())
    {
      localObject = (PackageInfo)paramStringBuilder.get(i);
      if ((((PackageInfo)localObject).applicationInfo.flags & 0x1) == 0)
      {
        localStringBuilder.append(((PackageInfo)localObject).packageName);
        localStringBuilder.append('\n');
      }
      i += 1;
    }
    localStringBuilder.append('\n');
    try
    {
      paramStringBuilder = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec(new String[] { "logcat", "-d", "-b", "all", "-v", "threadtime" }).getInputStream()));
      for (;;)
      {
        localObject = paramStringBuilder.readLine();
        if (localObject == null) {
          break;
        }
        if ((!((String)localObject).contains("E eglCodecCommon:")) && (!((String)localObject).contains("W OpenGLRenderer:")))
        {
          localStringBuilder.append((String)localObject);
          localStringBuilder.append('\n');
        }
      }
      return localStringBuilder;
    }
    catch (IOException paramStringBuilder)
    {
      d.c("getLogcat() failed" + paramStringBuilder);
      localStringBuilder.append('\n');
    }
  }
  
  public static void a(Activity paramActivity, String paramString1, String paramString2, String paramString3)
  {
    paramString1 = new Intent("android.intent.action.SENDTO", Uri.fromParts("mailto", paramString1, null));
    paramString1.putExtra("android.intent.extra.SUBJECT", paramString2);
    paramString1.putExtra("android.intent.extra.TEXT", paramString3);
    paramActivity.startActivity(Intent.createChooser(paramString1, "Select an Email Client:"));
  }
  
  public static void a(Context paramContext, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, boolean paramBoolean, a paramA)
  {
    View localView = View.inflate(paramContext, e.e.checkbox, null);
    final CheckBox localCheckBox = (CheckBox)localView.findViewById(e.d.checkbox);
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(paramContext);
    if (paramInt1 > 0) {
      localBuilder.setTitle(paramInt1);
    }
    if (paramInt2 > 0) {
      localBuilder.setMessage(paramInt2);
    }
    localBuilder.setPositiveButton(paramInt3, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        this.a.a(paramAnonymousDialogInterface, localCheckBox.isChecked());
      }
    });
    localBuilder.setOnCancelListener(new DialogInterface.OnCancelListener()
    {
      public void onCancel(DialogInterface paramAnonymousDialogInterface)
      {
        this.a.onCancel(paramAnonymousDialogInterface);
      }
    });
    if (paramInt4 != 0) {
      localBuilder.setNegativeButton(paramInt4, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          this.a.b(paramAnonymousDialogInterface, localCheckBox.isChecked());
        }
      });
    }
    if (paramInt5 != 0)
    {
      localCheckBox.setChecked(paramBoolean);
      localCheckBox.setText(paramInt5);
      if (Build.VERSION.SDK_INT < 11)
      {
        paramA = paramContext.getTheme().obtainStyledAttributes(new int[] { 16842801 });
        paramInt1 = paramA.getColor(0, 16777215);
        paramA.recycle();
        if (a(paramInt1) > 0.5D) {
          localCheckBox.setTextColor(paramContext.getResources().getColor(e.b.lltgray));
        }
      }
      localBuilder.setView(localView);
    }
    localBuilder.create().show();
  }
  
  public static void a(InputStream paramInputStream, OutputStream paramOutputStream)
  {
    byte[] arrayOfByte = new byte['Ѐ'];
    for (;;)
    {
      int i = paramInputStream.read(arrayOfByte);
      if (i == -1) {
        break;
      }
      paramOutputStream.write(arrayOfByte, 0, i);
    }
    paramOutputStream.flush();
  }
  
  public static boolean a(Application paramApplication)
  {
    a = paramApplication;
    CldWrapper.initNativeLib(paramApplication);
    d();
    return true;
  }
  
  public static boolean a(File paramFile)
  {
    try
    {
      if (!paramFile.isDirectory()) {
        return false;
      }
      File.createTempFile("test", "tmp", paramFile).delete();
      return true;
    }
    catch (IOException paramFile) {}
    return false;
  }
  
  public static boolean a(File paramFile1, File paramFile2)
  {
    localFileOutputStream = null;
    try
    {
      paramFile1 = new FileInputStream(paramFile1);
      if (paramFile2 == null) {}
    }
    catch (IOException paramFile1)
    {
      try
      {
        localFileOutputStream = new FileOutputStream(paramFile2);
      }
      catch (IOException paramFile2)
      {
        for (;;)
        {
          localFileOutputStream = null;
          paramFile2 = paramFile1;
          paramFile1 = localFileOutputStream;
        }
      }
      try
      {
        a(paramFile1, localFileOutputStream);
        paramFile1.close();
        localFileOutputStream.close();
        return true;
      }
      catch (IOException paramFile2)
      {
        paramFile2 = paramFile1;
        paramFile1 = localFileOutputStream;
        break label40;
      }
      paramFile1 = paramFile1;
      paramFile1 = null;
      paramFile2 = localFileOutputStream;
    }
    try
    {
      label40:
      paramFile2.close();
      if (paramFile1 == null) {}
    }
    catch (IOException paramFile2)
    {
      try
      {
        paramFile1.close();
        return false;
        paramFile2 = paramFile2;
      }
      catch (IOException paramFile1)
      {
        for (;;) {}
      }
    }
  }
  
  public static boolean a(String paramString)
  {
    Object localObject = null;
    try
    {
      paramString = a.getPackageManager().getInstallerPackageName(paramString);
      return (paramString != null) && (paramString.equals("com.android.vending"));
    }
    catch (Exception paramString)
    {
      for (;;)
      {
        paramString = localObject;
      }
    }
  }
  
  public static int b(String paramString)
  {
    try
    {
      if (paramString.startsWith("#")) {
        return Integer.parseInt(paramString.substring(1), 16);
      }
      int i = Integer.parseInt(paramString);
      return i;
    }
    catch (NumberFormatException paramString) {}
    return 0;
  }
  
  public static Context b()
  {
    if (a() != null) {
      return a().getApplicationContext();
    }
    return null;
  }
  
  public static long c(String paramString)
  {
    try
    {
      long l = Long.parseLong(paramString);
      return l;
    }
    catch (NumberFormatException paramString) {}
    return 0L;
  }
  
  public static void c()
  {
    Iterator localIterator = b.iterator();
    while (localIterator.hasNext()) {
      ((File)localIterator.next()).delete();
    }
  }
  
  public static float d(String paramString)
  {
    try
    {
      float f = Float.parseFloat(paramString);
      return f;
    }
    catch (NumberFormatException paramString) {}
    return 0.0F;
  }
  
  /* Error */
  public static long d()
  {
    // Byte code:
    //   0: getstatic 30	com/hyperionics/TtsSetup/a:a	Landroid/app/Application;
    //   3: invokevirtual 335	android/app/Application:getPackageManager	()Landroid/content/pm/PackageManager;
    //   6: astore 4
    //   8: aload 4
    //   10: getstatic 30	com/hyperionics/TtsSetup/a:a	Landroid/app/Application;
    //   13: invokevirtual 630	android/app/Application:getApplicationContext	()Landroid/content/Context;
    //   16: invokevirtual 662	android/content/Context:getPackageName	()Ljava/lang/String;
    //   19: bipush 64
    //   21: invokevirtual 666	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   24: astore 5
    //   26: aload 5
    //   28: getfield 669	android/content/pm/PackageInfo:firstInstallTime	J
    //   31: lstore_0
    //   32: lload_0
    //   33: lstore_2
    //   34: lload_0
    //   35: lconst_0
    //   36: lcmp
    //   37: ifne +115 -> 152
    //   40: new 80	java/io/File
    //   43: dup
    //   44: new 92	java/lang/StringBuilder
    //   47: dup
    //   48: invokespecial 93	java/lang/StringBuilder:<init>	()V
    //   51: invokestatic 28	com/hyperionics/TtsSetup/a:a	()Landroid/app/Application;
    //   54: invokevirtual 630	android/app/Application:getApplicationContext	()Landroid/content/Context;
    //   57: invokevirtual 672	android/content/Context:getExternalCacheDir	()Ljava/io/File;
    //   60: invokevirtual 675	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   63: invokevirtual 97	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   66: ldc_w 677
    //   69: invokevirtual 97	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   72: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   75: invokespecial 111	java/io/File:<init>	(Ljava/lang/String;)V
    //   78: astore 4
    //   80: lload_0
    //   81: lstore_2
    //   82: aload 4
    //   84: ifnull +68 -> 152
    //   87: aload 4
    //   89: invokevirtual 115	java/io/File:exists	()Z
    //   92: ifne +13 -> 105
    //   95: lload_0
    //   96: lstore_2
    //   97: aload 4
    //   99: invokevirtual 129	java/io/File:mkdirs	()Z
    //   102: ifeq +50 -> 152
    //   105: new 80	java/io/File
    //   108: dup
    //   109: new 92	java/lang/StringBuilder
    //   112: dup
    //   113: invokespecial 93	java/lang/StringBuilder:<init>	()V
    //   116: aload 4
    //   118: invokevirtual 675	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   121: invokevirtual 97	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   124: ldc_w 679
    //   127: invokevirtual 97	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   130: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   133: invokespecial 111	java/io/File:<init>	(Ljava/lang/String;)V
    //   136: astore 4
    //   138: aload 4
    //   140: invokevirtual 115	java/io/File:exists	()Z
    //   143: ifeq +33 -> 176
    //   146: aload 4
    //   148: invokevirtual 118	java/io/File:lastModified	()J
    //   151: lstore_2
    //   152: lload_2
    //   153: lreturn
    //   154: astore 4
    //   156: lconst_0
    //   157: lstore_0
    //   158: goto -126 -> 32
    //   161: astore 4
    //   163: lconst_0
    //   164: lstore_0
    //   165: goto -133 -> 32
    //   168: astore 4
    //   170: aconst_null
    //   171: astore 4
    //   173: goto -93 -> 80
    //   176: new 131	java/io/FileOutputStream
    //   179: dup
    //   180: aload 4
    //   182: invokespecial 596	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   185: astore 4
    //   187: aload 4
    //   189: aload 5
    //   191: getfield 683	android/content/pm/PackageInfo:signatures	[Landroid/content/pm/Signature;
    //   194: iconst_0
    //   195: aaload
    //   196: invokevirtual 689	android/content/pm/Signature:toByteArray	()[B
    //   199: invokevirtual 692	java/io/OutputStream:write	([B)V
    //   202: aload 4
    //   204: invokevirtual 142	java/io/OutputStream:flush	()V
    //   207: aload 4
    //   209: invokevirtual 143	java/io/OutputStream:close	()V
    //   212: lload_0
    //   213: lreturn
    //   214: astore 4
    //   216: lload_0
    //   217: lreturn
    //   218: astore 4
    //   220: lconst_0
    //   221: lreturn
    //   222: astore 4
    //   224: lload_0
    //   225: lreturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   31	194	0	l1	long
    //   33	120	2	l2	long
    //   6	141	4	localObject1	Object
    //   154	1	4	localNoSuchFieldError	NoSuchFieldError
    //   161	1	4	localException1	Exception
    //   168	1	4	localException2	Exception
    //   171	37	4	localObject2	Object
    //   214	1	4	localException3	Exception
    //   218	1	4	localNameNotFoundException1	PackageManager.NameNotFoundException
    //   222	1	4	localNameNotFoundException2	PackageManager.NameNotFoundException
    //   24	166	5	localPackageInfo	PackageInfo
    // Exception table:
    //   from	to	target	type
    //   26	32	154	java/lang/NoSuchFieldError
    //   26	32	161	java/lang/Exception
    //   40	80	168	java/lang/Exception
    //   176	212	214	java/lang/Exception
    //   8	26	218	android/content/pm/PackageManager$NameNotFoundException
    //   26	32	218	android/content/pm/PackageManager$NameNotFoundException
    //   40	80	222	android/content/pm/PackageManager$NameNotFoundException
    //   87	95	222	android/content/pm/PackageManager$NameNotFoundException
    //   97	105	222	android/content/pm/PackageManager$NameNotFoundException
    //   105	152	222	android/content/pm/PackageManager$NameNotFoundException
    //   176	212	222	android/content/pm/PackageManager$NameNotFoundException
  }
  
  public static boolean e()
  {
    AccessibilityManager localAccessibilityManager = (AccessibilityManager)a.getSystemService("accessibility");
    boolean bool = localAccessibilityManager.isEnabled();
    if (Build.VERSION.SDK_INT >= 14) {
      return localAccessibilityManager.isTouchExplorationEnabled() & bool;
    }
    return bool;
  }
  
  public static boolean e(String paramString)
  {
    return a(new File(paramString));
  }
  
  public static boolean f()
  {
    return a(a.getPackageName());
  }
  
  public static long g()
  {
    if (Build.VERSION.SDK_INT < 10) {
      return 0L;
    }
    try
    {
      long l = a.getPackageManager().getPackageInfo(a.getPackageName(), 0).lastUpdateTime;
      return l;
    }
    catch (NoSuchFieldError localNoSuchFieldError)
    {
      return 0L;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException) {}
    return 0L;
  }
  
  public static abstract class a
  {
    public a() {}
    
    public abstract void a(DialogInterface paramDialogInterface, boolean paramBoolean);
    
    public void b(DialogInterface paramDialogInterface, boolean paramBoolean) {}
    
    public void onCancel(DialogInterface paramDialogInterface)
    {
      paramDialogInterface.dismiss();
    }
  }
  
  public static class b
    extends AsyncTask
  {
    ProgressDialog a = null;
    a.c b = null;
    Context c;
    
    private b() {}
    
    protected b(Context paramContext)
    {
      this.c = paramContext;
    }
    
    protected Boolean a(Void... paramVarArgs)
    {
      return Boolean.valueOf(this.b.b());
    }
    
    protected void a(Boolean paramBoolean)
    {
      if (this.a != null) {}
      try
      {
        this.a.dismiss();
        if (this.b != null)
        {
          this.b.a(paramBoolean.booleanValue());
          this.b = null;
        }
        return;
      }
      catch (Exception localException)
      {
        for (;;) {}
      }
    }
    
    public void a(final String paramString)
    {
      if ((this.a != null) && (this.c != null) && ((this.c instanceof Activity))) {
        ((Activity)this.c).runOnUiThread(new Runnable()
        {
          public void run()
          {
            a.b.this.a.setMessage(paramString);
          }
        });
      }
    }
    
    protected void b(Boolean paramBoolean)
    {
      super.onCancelled();
      if (this.a != null) {}
      try
      {
        this.a.dismiss();
        if (this.b != null)
        {
          this.b.a(paramBoolean.booleanValue());
          this.b = null;
        }
        return;
      }
      catch (Exception localException)
      {
        for (;;) {}
      }
    }
    
    protected void onPreExecute() {}
  }
  
  public static abstract class c
  {
    a.b a = null;
    
    public c() {}
    
    public a.b a()
    {
      return this.a;
    }
    
    public void a(boolean paramBoolean) {}
    
    public abstract boolean b();
  }
}
