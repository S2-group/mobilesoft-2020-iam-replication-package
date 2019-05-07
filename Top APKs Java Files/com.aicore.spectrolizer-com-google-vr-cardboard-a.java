package com.google.vr.cardboard;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.PowerManager;
import android.provider.Settings.Secure;
import android.util.Log;
import android.view.Window;
import java.util.Iterator;
import java.util.List;

public class a
{
  private static final String a = a.class.getSimpleName();
  private static int b = 0;
  
  private a() {}
  
  /* Error */
  public static void a(int paramInt)
  {
    // Byte code:
    //   0: invokestatic 42	com/google/vr/cardboard/a:b	()Z
    //   3: ifne +4 -> 7
    //   6: return
    //   7: ldc 44
    //   9: ldc 46
    //   11: iconst_1
    //   12: anewarray 18	java/lang/Class
    //   15: dup
    //   16: iconst_0
    //   17: getstatic 52	java/lang/Integer:TYPE	Ljava/lang/Class;
    //   20: aastore
    //   21: invokevirtual 56	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   24: astore_1
    //   25: aload_1
    //   26: aconst_null
    //   27: iconst_1
    //   28: anewarray 4	java/lang/Object
    //   31: dup
    //   32: iconst_0
    //   33: iload_0
    //   34: invokestatic 60	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   37: aastore
    //   38: invokevirtual 66	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   41: pop
    //   42: return
    //   43: astore_1
    //   44: getstatic 24	com/google/vr/cardboard/a:a	Ljava/lang/String;
    //   47: astore_2
    //   48: aload_1
    //   49: invokestatic 71	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   52: astore_1
    //   53: aload_2
    //   54: new 73	java/lang/StringBuilder
    //   57: dup
    //   58: aload_1
    //   59: invokestatic 71	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   62: invokevirtual 77	java/lang/String:length	()I
    //   65: bipush 30
    //   67: iadd
    //   68: invokespecial 79	java/lang/StringBuilder:<init>	(I)V
    //   71: ldc 81
    //   73: invokevirtual 85	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   76: aload_1
    //   77: invokevirtual 85	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   80: invokevirtual 88	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   83: invokestatic 94	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   86: pop
    //   87: return
    //   88: astore_1
    //   89: invokestatic 97	com/google/vr/cardboard/a:c	()Z
    //   92: ifeq +47 -> 139
    //   95: getstatic 24	com/google/vr/cardboard/a:a	Ljava/lang/String;
    //   98: astore_2
    //   99: aload_1
    //   100: invokestatic 71	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   103: astore_1
    //   104: aload_2
    //   105: new 73	java/lang/StringBuilder
    //   108: dup
    //   109: aload_1
    //   110: invokestatic 71	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   113: invokevirtual 77	java/lang/String:length	()I
    //   116: bipush 38
    //   118: iadd
    //   119: invokespecial 79	java/lang/StringBuilder:<init>	(I)V
    //   122: ldc 99
    //   124: invokevirtual 85	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   127: aload_1
    //   128: invokevirtual 85	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   131: invokevirtual 88	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   134: invokestatic 94	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   137: pop
    //   138: return
    //   139: getstatic 24	com/google/vr/cardboard/a:a	Ljava/lang/String;
    //   142: astore_2
    //   143: aload_1
    //   144: invokestatic 71	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   147: astore_1
    //   148: aload_2
    //   149: new 73	java/lang/StringBuilder
    //   152: dup
    //   153: aload_1
    //   154: invokestatic 71	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   157: invokevirtual 77	java/lang/String:length	()I
    //   160: bipush 38
    //   162: iadd
    //   163: invokespecial 79	java/lang/StringBuilder:<init>	(I)V
    //   166: ldc 99
    //   168: invokevirtual 85	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   171: aload_1
    //   172: invokevirtual 85	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   175: invokevirtual 88	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   178: invokestatic 102	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   181: pop
    //   182: return
    //   183: astore_1
    //   184: goto -140 -> 44
    //   187: astore_1
    //   188: goto -144 -> 44
    //   191: astore_1
    //   192: goto -103 -> 89
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	195	0	paramInt	int
    //   24	2	1	localMethod	java.lang.reflect.Method
    //   43	6	1	localInvocationTargetException	java.lang.reflect.InvocationTargetException
    //   52	25	1	str1	String
    //   88	12	1	localRuntimeException1	RuntimeException
    //   103	69	1	str2	String
    //   183	1	1	localRuntimeException2	RuntimeException
    //   187	1	1	localIllegalAccessException	IllegalAccessException
    //   191	1	1	localNoSuchMethodException	NoSuchMethodException
    //   47	102	2	str3	String
    // Exception table:
    //   from	to	target	type
    //   25	42	43	java/lang/reflect/InvocationTargetException
    //   7	25	88	java/lang/RuntimeException
    //   25	42	183	java/lang/RuntimeException
    //   25	42	187	java/lang/IllegalAccessException
    //   7	25	191	java/lang/NoSuchMethodException
  }
  
  private static void a(Context paramContext, int paramInt1, int paramInt2, DialogInterface.OnClickListener paramOnClickListener)
  {
    paramContext = new AlertDialog.Builder(paramContext, r.e.GvrDialogTheme);
    paramContext.setMessage(paramInt1).setTitle(r.d.dialog_title_warning).setPositiveButton(paramInt2, paramOnClickListener).setNegativeButton(r.d.cancel_button, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
    });
    paramContext.create().show();
  }
  
  public static boolean a(Activity paramActivity, boolean paramBoolean)
  {
    return a(paramActivity, paramBoolean, 1);
  }
  
  public static boolean a(Activity paramActivity, boolean paramBoolean, int paramInt)
  {
    if (!a(paramActivity)) {
      if (b()) {
        Log.d(a, "VR mode is not supported on this N device.");
      }
    }
    for (;;)
    {
      return false;
      Object localObject = new ComponentName("com.google.vr.vrcore", "com.google.vr.vrcore.common.VrCoreListenerService");
      try
      {
        paramActivity.setVrModeEnabled(paramBoolean, (ComponentName)localObject);
        return true;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        localObject = a;
        String str2 = String.valueOf(localNameNotFoundException);
        Log.w((String)localObject, String.valueOf(str2).length() + 25 + "No VR service component: " + str2);
        if (((paramInt & 0x1) != 0) && (b(paramActivity)) && (a(paramActivity, c(paramActivity))))
        {
          Log.w(a, "Failed to handle missing VrCore package.");
          return false;
        }
      }
      catch (UnsupportedOperationException localUnsupportedOperationException)
      {
        paramActivity = a;
        String str1 = String.valueOf(localUnsupportedOperationException);
        Log.w(paramActivity, String.valueOf(str1).length() + 23 + "Failed to set VR mode: " + str1);
      }
    }
    return false;
  }
  
  public static boolean a(Context paramContext)
  {
    return (b()) && (paramContext.getPackageManager().hasSystemFeature("android.software.vr.mode"));
  }
  
  private static boolean a(Context paramContext, int paramInt)
  {
    if (m.a()) {}
    do
    {
      return true;
      if (paramInt == -1)
      {
        a(paramContext, r.d.dialog_vr_core_not_installed, r.d.go_to_playstore_button, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
            paramAnonymousDialogInterface = new Intent("android.intent.action.VIEW");
            paramAnonymousDialogInterface.setData(Uri.parse("market://details?id=com.google.vr.vrcore"));
            paramAnonymousDialogInterface.setPackage("com.android.vending");
            try
            {
              a.this.startActivity(paramAnonymousDialogInterface);
              return;
            }
            catch (ActivityNotFoundException paramAnonymousDialogInterface)
            {
              Log.e(a.a(), "Google Play Services is not installed, unable to download VrCore.");
            }
          }
        });
        return false;
      }
    } while (paramInt != -2);
    a(paramContext, r.d.dialog_vr_core_not_enabled, r.d.go_to_vr_listeners_settings_button, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        a.this.startActivity(new Intent("android.settings.VR_LISTENER_SETTINGS"));
      }
    });
    return false;
  }
  
  private static boolean b()
  {
    return (b >= 24) || (Build.VERSION.SDK_INT >= 24);
  }
  
  public static boolean b(Activity paramActivity, boolean paramBoolean)
  {
    if (!b()) {
      return false;
    }
    if (!((PowerManager)paramActivity.getSystemService("power")).isSustainedPerformanceModeSupported()) {
      return false;
    }
    paramActivity = paramActivity.getWindow();
    if (paramActivity == null)
    {
      Log.e(a, "Activity does not have a window");
      return false;
    }
    paramActivity.setSustainedPerformanceMode(paramBoolean);
    return true;
  }
  
  private static boolean b(Context paramContext)
  {
    return (b()) && (paramContext.getPackageManager().hasSystemFeature("android.hardware.vr.high_performance"));
  }
  
  private static int c(Context paramContext)
  {
    Object localObject = paramContext.getPackageManager().getInstalledApplications(0).iterator();
    do
    {
      if (!((Iterator)localObject).hasNext()) {
        break;
      }
    } while (!((ApplicationInfo)((Iterator)localObject).next()).packageName.equals("com.google.vr.vrcore"));
    for (int i = 1;; i = 0)
    {
      if (i == 0) {
        return -1;
      }
      paramContext = Settings.Secure.getString(paramContext.getContentResolver(), "enabled_vr_listeners");
      localObject = new ComponentName("com.google.vr.vrcore", "com.google.vr.vrcore.common.VrCoreListenerService");
      if ((paramContext == null) || (!paramContext.contains(((ComponentName)localObject).flattenToString()))) {
        return -2;
      }
      return 0;
    }
  }
  
  private static boolean c()
  {
    return (b >= 25) || ("NMR1".equals(Build.VERSION.CODENAME)) || (Build.VERSION.SDK_INT >= 25);
  }
}
