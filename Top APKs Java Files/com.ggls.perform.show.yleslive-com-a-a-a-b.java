package com.a.a.a;

import android.app.Activity;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.os.IBinder;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import com.dongby.android.sdk.a;
import com.dongby.android.sdk.application.DobyApp;
import com.dongby.android.sdk.util.d;
import com.dongby.android.sdk.util.q;
import com.dongby.android.sdk.util.t;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Pattern;

public class b
{
  public static String a(Activity paramActivity)
  {
    try
    {
      Object localObject2 = new Intent("android.intent.action.MAIN");
      ((Intent)localObject2).addCategory("android.intent.category.HOME");
      Object localObject1 = paramActivity.getPackageManager();
      int i = 0;
      localObject2 = ((PackageManager)localObject1).resolveActivity((Intent)localObject2, 0);
      if (localObject2 == null) {
        return null;
      }
      localObject1 = ((PackageManager)localObject1).queryContentProviders(((ResolveInfo)localObject2).activityInfo.packageName, ((ResolveInfo)localObject2).activityInfo.applicationInfo.uid, 8);
      if (localObject1 != null) {
        while (i < ((List)localObject1).size())
        {
          localObject2 = (ProviderInfo)((List)localObject1).get(i);
          if ((((ProviderInfo)localObject2).readPermission != null) && (Pattern.matches(".*launcher.*READ_SETTINGS", ((ProviderInfo)localObject2).readPermission)))
          {
            localObject1 = ((ProviderInfo)localObject2).authority;
            return localObject1;
          }
          i += 1;
        }
      }
      return null;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      q.a(paramActivity, localException);
    }
  }
  
  public static String a(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageName();
      return paramContext;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return "";
  }
  
  public static List<String> a()
  {
    localArrayList = new ArrayList();
    try
    {
      Iterator localIterator = DobyApp.app().getPackageManager().getInstalledPackages(0).iterator();
      while (localIterator.hasNext()) {
        localArrayList.add(((PackageInfo)localIterator.next()).packageName);
      }
      return localArrayList;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      q.a(DobyApp.app(), localException);
    }
  }
  
  public static void a(Activity paramActivity, Bitmap paramBitmap, int paramInt, String paramString, ContentValues paramContentValues)
  {
    try
    {
      if (!a(paramActivity, paramString))
      {
        Intent localIntent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
        localIntent.putExtra("duplicate", false);
        if (paramBitmap != null) {
          localIntent.putExtra("android.intent.extra.shortcut.ICON", paramBitmap);
        } else if (paramInt != 0) {
          localIntent.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(paramActivity, paramInt));
        }
        localIntent.putExtra("android.intent.extra.shortcut.NAME", paramString);
        paramBitmap = new Intent();
        paramBitmap.setFlags(67108864);
        paramBitmap.setAction("android.intent.action.MAIN");
        paramBitmap.addCategory("android.intent.category.LAUNCHER");
        paramBitmap.setComponent(new ComponentName(paramActivity.getPackageName(), paramActivity.getLocalClassName()));
        if (paramContentValues != null)
        {
          paramString = paramContentValues.valueSet().iterator();
          while (paramString.hasNext())
          {
            paramContentValues = (Map.Entry)paramString.next();
            if ((paramContentValues != null) && (paramContentValues.getValue() != null)) {
              paramBitmap.putExtra((String)paramContentValues.getKey(), paramContentValues.getValue().toString());
            }
          }
        }
        localIntent.putExtra("android.intent.extra.shortcut.INTENT", paramBitmap);
        paramActivity.sendBroadcast(localIntent);
        return;
      }
    }
    catch (Exception paramBitmap)
    {
      paramBitmap.printStackTrace();
      q.a(paramActivity, paramBitmap);
    }
  }
  
  /* Error */
  public static boolean a(Activity paramActivity, String paramString)
  {
    // Byte code:
    //   0: invokestatic 232	java/lang/System:currentTimeMillis	()J
    //   3: pop2
    //   4: aload_0
    //   5: invokestatic 234	com/a/a/a/b:a	(Landroid/app/Activity;)Ljava/lang/String;
    //   8: astore_3
    //   9: aload_3
    //   10: ifnonnull +5 -> 15
    //   13: iconst_0
    //   14: ireturn
    //   15: invokestatic 232	java/lang/System:currentTimeMillis	()J
    //   18: pop2
    //   19: new 236	java/lang/StringBuilder
    //   22: dup
    //   23: invokespecial 237	java/lang/StringBuilder:<init>	()V
    //   26: astore 4
    //   28: aload 4
    //   30: ldc -17
    //   32: invokevirtual 243	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   35: pop
    //   36: aload 4
    //   38: aload_3
    //   39: invokevirtual 243	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   42: pop
    //   43: aload 4
    //   45: ldc -11
    //   47: invokevirtual 243	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   50: pop
    //   51: aload 4
    //   53: invokevirtual 246	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   56: astore 6
    //   58: aconst_null
    //   59: astore 4
    //   61: aconst_null
    //   62: astore 5
    //   64: aload 5
    //   66: astore_3
    //   67: aload 6
    //   69: invokestatic 252	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   72: astore 6
    //   74: aload 5
    //   76: astore_3
    //   77: aload_0
    //   78: invokevirtual 256	android/app/Activity:getContentResolver	()Landroid/content/ContentResolver;
    //   81: aload 6
    //   83: aconst_null
    //   84: ldc_w 258
    //   87: iconst_1
    //   88: anewarray 217	java/lang/String
    //   91: dup
    //   92: iconst_0
    //   93: aload_1
    //   94: aastore
    //   95: aconst_null
    //   96: invokevirtual 264	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   99: astore_1
    //   100: aload_1
    //   101: ifnull +46 -> 147
    //   104: aload_1
    //   105: invokeinterface 269 1 0
    //   110: istore_2
    //   111: iload_2
    //   112: ifeq +35 -> 147
    //   115: aload_1
    //   116: ifnull +18 -> 134
    //   119: aload_1
    //   120: invokeinterface 272 1 0
    //   125: ifne +9 -> 134
    //   128: aload_1
    //   129: invokeinterface 275 1 0
    //   134: iconst_1
    //   135: ireturn
    //   136: astore_0
    //   137: aload_1
    //   138: astore_3
    //   139: goto +76 -> 215
    //   142: astore 4
    //   144: goto +35 -> 179
    //   147: aload_1
    //   148: ifnull +65 -> 213
    //   151: aload_1
    //   152: invokeinterface 272 1 0
    //   157: ifne +56 -> 213
    //   160: aload_1
    //   161: invokeinterface 275 1 0
    //   166: iconst_0
    //   167: ireturn
    //   168: astore_0
    //   169: goto +46 -> 215
    //   172: astore_3
    //   173: aload 4
    //   175: astore_1
    //   176: aload_3
    //   177: astore 4
    //   179: aload_1
    //   180: astore_3
    //   181: aload 4
    //   183: invokevirtual 90	java/lang/Exception:printStackTrace	()V
    //   186: aload_1
    //   187: astore_3
    //   188: aload_0
    //   189: aload 4
    //   191: invokestatic 95	com/dongby/android/sdk/util/q:a	(Landroid/content/Context;Ljava/lang/Throwable;)V
    //   194: aload_1
    //   195: ifnull +18 -> 213
    //   198: aload_1
    //   199: invokeinterface 272 1 0
    //   204: ifne +9 -> 213
    //   207: aload_1
    //   208: invokeinterface 275 1 0
    //   213: iconst_0
    //   214: ireturn
    //   215: aload_3
    //   216: ifnull +18 -> 234
    //   219: aload_3
    //   220: invokeinterface 272 1 0
    //   225: ifne +9 -> 234
    //   228: aload_3
    //   229: invokeinterface 275 1 0
    //   234: aload_0
    //   235: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	236	0	paramActivity	Activity
    //   0	236	1	paramString	String
    //   110	2	2	bool	boolean
    //   8	131	3	localObject1	Object
    //   172	5	3	localException1	Exception
    //   180	49	3	str	String
    //   26	34	4	localStringBuilder	StringBuilder
    //   142	32	4	localException2	Exception
    //   177	13	4	localException3	Exception
    //   62	13	5	localObject2	Object
    //   56	26	6	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   104	111	136	finally
    //   104	111	142	java/lang/Exception
    //   67	74	168	finally
    //   77	100	168	finally
    //   181	186	168	finally
    //   188	194	168	finally
    //   67	74	172	java/lang/Exception
    //   77	100	172	java/lang/Exception
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    boolean bool = false;
    try
    {
      paramContext = paramContext.getPackageManager();
      t.e("archiveFilePath", new Object[] { paramString });
      paramContext = paramContext.getPackageArchiveInfo(paramString, 1);
      if (paramContext != null) {
        bool = true;
      }
      return bool;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  public static boolean a(View paramView, float paramFloat1, float paramFloat2)
  {
    boolean bool3 = b(paramView, paramFloat1, paramFloat2);
    boolean bool2 = false;
    int i = 0;
    boolean bool1 = true;
    if (bool3)
    {
      if ((paramView instanceof ViewGroup))
      {
        paramView = (ViewGroup)paramView;
        while (i < paramView.getChildCount())
        {
          bool1 = a(paramView.getChildAt(i), paramFloat1, paramFloat2);
          if (!bool1) {
            break;
          }
          i += 1;
        }
        break label99;
      }
      bool1 = bool2;
      if ((paramView instanceof EditText)) {
        break label99;
      }
      if ((paramView instanceof AppCompatEditText))
      {
        bool1 = bool2;
        break label99;
      }
    }
    bool1 = true;
    label99:
    paramView = new StringBuilder();
    paramView.append(bool1);
    paramView.append("");
    Log.i("message:", paramView.toString());
    return bool1;
  }
  
  public static int[] a(Context paramContext, int paramInt1, int paramInt2)
  {
    if ((paramInt1 > 0) && (paramInt2 > 0))
    {
      int i = d.p(paramContext);
      int j = d.o(paramContext);
      float f1 = paramInt2;
      float f2 = j;
      float f3 = f1 / f2;
      float f4 = paramInt1;
      float f5 = i;
      if (f3 > f4 / f5)
      {
        paramInt1 = (int)(f2 * 0.22F);
        paramInt2 = (int)(f4 / f1 * paramInt1);
      }
      else
      {
        paramInt2 = (int)(f5 * 0.38F);
        paramInt1 = (int)(f1 / f4 * paramInt2);
      }
      return new int[] { paramInt2, paramInt1 };
    }
    return new int[] { 60, 60 };
  }
  
  public static String b(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 64).signatures;
      StringBuilder localStringBuilder = new StringBuilder();
      int j = paramContext.length;
      int i = 0;
      while (i < j)
      {
        localStringBuilder.append(paramContext[i].toCharsString());
        i += 1;
      }
      paramContext = localStringBuilder.toString();
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return "sb";
  }
  
  public static void b(Activity paramActivity)
  {
    Object localObject = paramActivity.getCurrentFocus();
    if (localObject != null)
    {
      localObject = ((View)localObject).getWindowToken();
      if (localObject != null)
      {
        paramActivity = (InputMethodManager)paramActivity.getSystemService("input_method");
        if (paramActivity.isActive()) {
          paramActivity.hideSoftInputFromWindow((IBinder)localObject, 2);
        }
      }
    }
  }
  
  public static boolean b()
  {
    Object localObject;
    if (a.j == 2) {
      localObject = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    }
    try
    {
      localObject = ((SimpleDateFormat)localObject).parse(a.k);
      if (new Date().getTime() < ((Date)localObject).getTime())
      {
        return false;
        if (a.j == 3) {
          return false;
        }
        if (a.j == 1) {
          return true;
        }
      }
      return true;
    }
    catch (Exception localException) {}
    return false;
  }
  
  public static boolean b(View paramView, float paramFloat1, float paramFloat2)
  {
    int[] arrayOfInt = new int[2];
    paramView.getLocationOnScreen(arrayOfInt);
    boolean bool2 = false;
    int i = arrayOfInt[0];
    int j = arrayOfInt[1];
    boolean bool1 = bool2;
    if (paramFloat1 > i)
    {
      bool1 = bool2;
      if (paramFloat1 < i + paramView.getWidth())
      {
        bool1 = bool2;
        if (paramFloat2 > j)
        {
          bool1 = bool2;
          if (paramFloat2 < j + paramView.getHeight()) {
            bool1 = true;
          }
        }
      }
    }
    return bool1;
  }
  
  public static int c(Context paramContext)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      return i;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return 0;
  }
  
  /* Error */
  public static String c()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: new 236	java/lang/StringBuilder
    //   5: dup
    //   6: invokespecial 237	java/lang/StringBuilder:<init>	()V
    //   9: astore_0
    //   10: aload_0
    //   11: ldc_w 424
    //   14: invokevirtual 243	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   17: pop
    //   18: aload_0
    //   19: invokestatic 429	android/os/Process:myPid	()I
    //   22: invokevirtual 432	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   25: pop
    //   26: aload_0
    //   27: ldc_w 434
    //   30: invokevirtual 243	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   33: pop
    //   34: new 436	java/io/BufferedReader
    //   37: dup
    //   38: new 438	java/io/FileReader
    //   41: dup
    //   42: aload_0
    //   43: invokevirtual 246	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   46: invokespecial 439	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   49: invokespecial 442	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   52: astore_1
    //   53: aload_1
    //   54: astore_0
    //   55: aload_1
    //   56: invokevirtual 445	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   59: astore_3
    //   60: aload_3
    //   61: astore_2
    //   62: aload_1
    //   63: astore_0
    //   64: aload_3
    //   65: invokestatic 451	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   68: ifne +10 -> 78
    //   71: aload_1
    //   72: astore_0
    //   73: aload_3
    //   74: invokevirtual 454	java/lang/String:trim	()Ljava/lang/String;
    //   77: astore_2
    //   78: aload_1
    //   79: invokevirtual 455	java/io/BufferedReader:close	()V
    //   82: aload_2
    //   83: areturn
    //   84: astore_0
    //   85: aload_0
    //   86: invokevirtual 456	java/io/IOException:printStackTrace	()V
    //   89: aload_2
    //   90: areturn
    //   91: astore_2
    //   92: goto +10 -> 102
    //   95: astore_0
    //   96: goto +34 -> 130
    //   99: astore_2
    //   100: aconst_null
    //   101: astore_1
    //   102: aload_1
    //   103: astore_0
    //   104: aload_2
    //   105: invokevirtual 457	java/lang/Throwable:printStackTrace	()V
    //   108: aload_1
    //   109: ifnull +14 -> 123
    //   112: aload_1
    //   113: invokevirtual 455	java/io/BufferedReader:close	()V
    //   116: aconst_null
    //   117: areturn
    //   118: astore_0
    //   119: aload_0
    //   120: invokevirtual 456	java/io/IOException:printStackTrace	()V
    //   123: aconst_null
    //   124: areturn
    //   125: astore_1
    //   126: aload_0
    //   127: astore_2
    //   128: aload_1
    //   129: astore_0
    //   130: aload_2
    //   131: ifnull +15 -> 146
    //   134: aload_2
    //   135: invokevirtual 455	java/io/BufferedReader:close	()V
    //   138: goto +8 -> 146
    //   141: astore_1
    //   142: aload_1
    //   143: invokevirtual 456	java/io/IOException:printStackTrace	()V
    //   146: aload_0
    //   147: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   9	64	0	localObject1	Object
    //   84	2	0	localIOException1	java.io.IOException
    //   95	1	0	localObject2	Object
    //   103	1	0	localObject3	Object
    //   118	9	0	localIOException2	java.io.IOException
    //   129	18	0	localObject4	Object
    //   52	61	1	localBufferedReader	java.io.BufferedReader
    //   125	4	1	localObject5	Object
    //   141	2	1	localIOException3	java.io.IOException
    //   1	89	2	localObject6	Object
    //   91	1	2	localThrowable1	Throwable
    //   99	6	2	localThrowable2	Throwable
    //   127	8	2	localObject7	Object
    //   59	15	3	str	String
    // Exception table:
    //   from	to	target	type
    //   78	82	84	java/io/IOException
    //   55	60	91	java/lang/Throwable
    //   64	71	91	java/lang/Throwable
    //   73	78	91	java/lang/Throwable
    //   2	53	95	finally
    //   2	53	99	java/lang/Throwable
    //   112	116	118	java/io/IOException
    //   55	60	125	finally
    //   64	71	125	finally
    //   73	78	125	finally
    //   104	108	125	finally
    //   134	138	141	java/io/IOException
  }
  
  public static String d(Context paramContext)
    throws PackageManager.NameNotFoundException
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
      return paramContext;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static boolean e(Context paramContext)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    if (paramContext != null)
    {
      int i = 0;
      while (i < paramContext.size())
      {
        if (((PackageInfo)paramContext.get(i)).packageName.equals("com.tencent.mm")) {
          return true;
        }
        i += 1;
      }
    }
    return false;
  }
  
  public static boolean f(Context paramContext)
  {
    paramContext = a(paramContext);
    String str = c();
    return (str == null) || (str.equals(paramContext));
  }
  
  public static boolean g(Context paramContext)
  {
    if ((paramContext != null) && ((paramContext instanceof Activity)) && (!((Activity)paramContext).isFinishing())) {
      return true;
    }
    if (paramContext == null) {
      str = "mContext == null\n";
    } else if (!(paramContext instanceof Activity)) {
      str = "mContext is not Activity\n";
    } else if (((Activity)paramContext).isFinishing()) {
      str = "Activity isFinishing\n";
    } else {
      str = "";
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(str);
    localStringBuilder.append(d.e());
    String str = localStringBuilder.toString();
    if (paramContext != null) {
      q.b(paramContext, str);
    }
    return false;
  }
}
