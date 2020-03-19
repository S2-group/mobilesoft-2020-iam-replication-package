package com.blankj.utilcode.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.ActivityManager.RunningTaskInfo;
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
import com.dongby.android.sdk.h.d;
import com.dongby.android.sdk.h.m;
import com.dongby.android.sdk.h.p;
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
    for (;;)
    {
      int i;
      try
      {
        Object localObject2 = new Intent("android.intent.action.MAIN");
        ((Intent)localObject2).addCategory("android.intent.category.HOME");
        Object localObject1 = paramActivity.getPackageManager();
        localObject2 = ((PackageManager)localObject1).resolveActivity((Intent)localObject2, 0);
        if (localObject2 == null) {
          return null;
        }
        localObject1 = ((PackageManager)localObject1).queryContentProviders(((ResolveInfo)localObject2).activityInfo.packageName, ((ResolveInfo)localObject2).activityInfo.applicationInfo.uid, 8);
        if (localObject1 != null)
        {
          i = 0;
          if (i < ((List)localObject1).size())
          {
            localObject2 = (ProviderInfo)((List)localObject1).get(i);
            if ((((ProviderInfo)localObject2).readPermission == null) || (!Pattern.matches(".*launcher.*READ_SETTINGS", ((ProviderInfo)localObject2).readPermission))) {
              break label127;
            }
            localObject1 = ((ProviderInfo)localObject2).authority;
            return localObject1;
          }
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        m.a(paramActivity, localException);
      }
      return null;
      label127:
      i += 1;
    }
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
      m.a(DobyApp.app(), localException);
    }
  }
  
  public static void a(Activity paramActivity, Bitmap paramBitmap, int paramInt, String paramString, ContentValues paramContentValues)
  {
    Intent localIntent;
    for (;;)
    {
      try
      {
        if (!a(paramActivity, paramString))
        {
          localIntent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
          localIntent.putExtra("duplicate", false);
          if (paramBitmap != null)
          {
            localIntent.putExtra("android.intent.extra.shortcut.ICON", paramBitmap);
            localIntent.putExtra("android.intent.extra.shortcut.NAME", paramString);
            paramBitmap = new Intent();
            paramBitmap.setFlags(67108864);
            paramBitmap.setAction("android.intent.action.MAIN");
            paramBitmap.addCategory("android.intent.category.LAUNCHER");
            paramBitmap.setComponent(new ComponentName(paramActivity.getPackageName(), paramActivity.getLocalClassName()));
            if (paramContentValues == null) {
              break;
            }
            paramString = paramContentValues.valueSet().iterator();
            if (!paramString.hasNext()) {
              break;
            }
            paramContentValues = (Map.Entry)paramString.next();
            if ((paramContentValues == null) || (paramContentValues.getValue() == null)) {
              continue;
            }
            paramBitmap.putExtra((String)paramContentValues.getKey(), paramContentValues.getValue().toString());
            continue;
          }
        }
        else
        {
          return;
        }
      }
      catch (Exception paramBitmap)
      {
        paramBitmap.printStackTrace();
        m.a(paramActivity, paramBitmap);
      }
      if (paramInt != 0) {
        localIntent.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(paramActivity, paramInt));
      }
    }
    localIntent.putExtra("android.intent.extra.shortcut.INTENT", paramBitmap);
    paramActivity.sendBroadcast(localIntent);
  }
  
  /* Error */
  public static boolean a(Activity paramActivity, String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: invokestatic 226	java/lang/System:currentTimeMillis	()J
    //   5: pop2
    //   6: aload_0
    //   7: invokestatic 228	com/blankj/utilcode/util/b:a	(Landroid/app/Activity;)Ljava/lang/String;
    //   10: astore 4
    //   12: aload 4
    //   14: ifnonnull +5 -> 19
    //   17: iconst_0
    //   18: ireturn
    //   19: invokestatic 226	java/lang/System:currentTimeMillis	()J
    //   22: pop2
    //   23: new 230	java/lang/StringBuilder
    //   26: dup
    //   27: invokespecial 231	java/lang/StringBuilder:<init>	()V
    //   30: ldc -23
    //   32: invokevirtual 237	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   35: aload 4
    //   37: invokevirtual 237	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   40: ldc -17
    //   42: invokevirtual 237	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   45: invokevirtual 240	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   48: astore 4
    //   50: aload 4
    //   52: invokestatic 246	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   55: astore 4
    //   57: aload_0
    //   58: invokevirtual 250	android/app/Activity:getContentResolver	()Landroid/content/ContentResolver;
    //   61: aload 4
    //   63: aconst_null
    //   64: ldc -4
    //   66: iconst_1
    //   67: anewarray 203	java/lang/String
    //   70: dup
    //   71: iconst_0
    //   72: aload_1
    //   73: aastore
    //   74: aconst_null
    //   75: invokevirtual 258	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   78: astore_1
    //   79: aload_1
    //   80: astore_3
    //   81: aload_3
    //   82: ifnull +37 -> 119
    //   85: aload_3
    //   86: astore_1
    //   87: aload_3
    //   88: invokeinterface 263 1 0
    //   93: istore_2
    //   94: iload_2
    //   95: ifeq +24 -> 119
    //   98: aload_3
    //   99: ifnull +18 -> 117
    //   102: aload_3
    //   103: invokeinterface 266 1 0
    //   108: ifne +9 -> 117
    //   111: aload_3
    //   112: invokeinterface 269 1 0
    //   117: iconst_1
    //   118: ireturn
    //   119: aload_3
    //   120: ifnull +18 -> 138
    //   123: aload_3
    //   124: invokeinterface 266 1 0
    //   129: ifne +9 -> 138
    //   132: aload_3
    //   133: invokeinterface 269 1 0
    //   138: iconst_0
    //   139: ireturn
    //   140: astore 4
    //   142: aconst_null
    //   143: astore_3
    //   144: aload_3
    //   145: astore_1
    //   146: aload 4
    //   148: invokevirtual 90	java/lang/Exception:printStackTrace	()V
    //   151: aload_3
    //   152: astore_1
    //   153: aload_0
    //   154: aload 4
    //   156: invokestatic 95	com/dongby/android/sdk/h/m:a	(Landroid/content/Context;Ljava/lang/Throwable;)V
    //   159: aload_3
    //   160: ifnull -22 -> 138
    //   163: aload_3
    //   164: invokeinterface 266 1 0
    //   169: ifne -31 -> 138
    //   172: aload_3
    //   173: invokeinterface 269 1 0
    //   178: goto -40 -> 138
    //   181: astore_0
    //   182: aload_3
    //   183: astore_1
    //   184: aload_1
    //   185: ifnull +18 -> 203
    //   188: aload_1
    //   189: invokeinterface 266 1 0
    //   194: ifne +9 -> 203
    //   197: aload_1
    //   198: invokeinterface 269 1 0
    //   203: aload_0
    //   204: athrow
    //   205: astore_0
    //   206: goto -22 -> 184
    //   209: astore 4
    //   211: goto -67 -> 144
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	214	0	paramActivity	Activity
    //   0	214	1	paramString	String
    //   93	2	2	bool	boolean
    //   1	182	3	str	String
    //   10	52	4	localObject	Object
    //   140	15	4	localException1	Exception
    //   209	1	4	localException2	Exception
    // Exception table:
    //   from	to	target	type
    //   50	79	140	java/lang/Exception
    //   50	79	181	finally
    //   87	94	205	finally
    //   146	151	205	finally
    //   153	159	205	finally
    //   87	94	209	java/lang/Exception
  }
  
  public static boolean a(Context paramContext)
  {
    try
    {
      List localList = ((ActivityManager)paramContext.getSystemService("activity")).getRunningTasks(1);
      if ((localList != null) && (localList.size() > 0))
      {
        boolean bool = ((ActivityManager.RunningTaskInfo)localList.get(0)).topActivity.getPackageName().equals(paramContext.getPackageName());
        if (!bool) {
          return true;
        }
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      m.a(paramContext, localException);
    }
    return false;
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningServices(30);
    if ((paramContext == null) || (paramContext.isEmpty())) {}
    for (;;)
    {
      return false;
      int i = 0;
      while (i < paramContext.size())
      {
        if (((ActivityManager.RunningServiceInfo)paramContext.get(i)).service.getClassName().equals(paramString) == true) {
          return true;
        }
        i += 1;
      }
    }
  }
  
  public static boolean a(View paramView, float paramFloat1, float paramFloat2)
  {
    int i = 0;
    boolean bool3 = true;
    boolean bool2 = true;
    boolean bool1 = bool3;
    if (b(paramView, paramFloat1, paramFloat2))
    {
      if (!(paramView instanceof ViewGroup)) {
        break label100;
      }
      paramView = (ViewGroup)paramView;
      bool1 = bool2;
      if (i < paramView.getChildCount())
      {
        bool1 = a(paramView.getChildAt(i), paramFloat1, paramFloat2);
        if (bool1) {
          break label93;
        }
      }
    }
    for (;;)
    {
      Log.i("message:", bool1 + "");
      return bool1;
      label93:
      i += 1;
      break;
      label100:
      if (!(paramView instanceof EditText))
      {
        bool1 = bool3;
        if (!(paramView instanceof AppCompatEditText)) {}
      }
      else
      {
        bool1 = false;
      }
    }
  }
  
  public static int[] a(Context paramContext, int paramInt1, int paramInt2)
  {
    if ((paramInt1 <= 0) || (paramInt2 <= 0)) {
      return new int[] { 60, 60 };
    }
    int i = d.m(paramContext);
    int j = d.l(paramContext);
    if (paramInt2 / j > paramInt1 / i)
    {
      i = (int)(0.22F * j);
      paramInt1 = (int)(paramInt1 / paramInt2 * i);
      paramInt2 = i;
    }
    for (;;)
    {
      return new int[] { paramInt1, paramInt2 };
      i = (int)(i * 0.38F);
      paramInt2 = (int)(paramInt2 / paramInt1 * i);
      paramInt1 = i;
    }
  }
  
  public static String b(Context paramContext)
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
    if (a.i == 2) {
      localObject = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    }
    try
    {
      localObject = ((SimpleDateFormat)localObject).parse(a.j);
      if (new Date().getTime() < ((Date)localObject).getTime())
      {
        do
        {
          return false;
        } while (a.i == 3);
        if (a.i == 1) {
          return true;
        }
      }
      return true;
    }
    catch (Exception localException) {}
    return false;
  }
  
  public static boolean b(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getPackageManager();
      p.e("archiveFilePath", new Object[] { paramString });
      paramContext = paramContext.getPackageArchiveInfo(paramString, 1);
      if (paramContext != null) {
        return true;
      }
    }
    catch (Exception paramContext)
    {
      return false;
    }
    return false;
  }
  
  public static boolean b(View paramView, float paramFloat1, float paramFloat2)
  {
    int[] arrayOfInt = new int[2];
    paramView.getLocationOnScreen(arrayOfInt);
    int i = arrayOfInt[0];
    int j = arrayOfInt[1];
    return (paramFloat1 > i) && (paramFloat1 < i + paramView.getWidth()) && (paramFloat2 > j) && (paramFloat2 < j + paramView.getHeight());
  }
  
  public static String c(Context paramContext)
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
  
  public static int d(Context paramContext)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return 0;
  }
  
  public static String e(Context paramContext)
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
    return "";
  }
  
  public static boolean f(Context paramContext)
  {
    boolean bool2 = false;
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    boolean bool1 = bool2;
    int i;
    if (paramContext != null) {
      i = 0;
    }
    for (;;)
    {
      bool1 = bool2;
      if (i < paramContext.size())
      {
        if (((PackageInfo)paramContext.get(i)).packageName.equals("com.tencent.mm")) {
          bool1 = true;
        }
      }
      else {
        return bool1;
      }
      i += 1;
    }
  }
}
