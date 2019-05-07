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

public class adx
{
  private static final String a = adx.class.getSimpleName();
  private static int b = 0;
  
  private adx() {}
  
  private static int a(Context paramContext)
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
  
  private static void a(Context paramContext, int paramInt1, int paramInt2, DialogInterface.OnClickListener paramOnClickListener)
  {
    paramContext = new AlertDialog.Builder(paramContext, aej.e.GvrDialogTheme);
    paramContext.setMessage(paramInt1).setTitle(aej.d.dialog_title_warning).setPositiveButton(paramInt2, paramOnClickListener).setNegativeButton(aej.d.cancel_button, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
    });
    paramContext.create().show();
  }
  
  private static boolean a(Context paramContext, int paramInt)
  {
    if (paramInt == -1)
    {
      a(paramContext, aej.d.dialog_vr_core_not_installed, aej.d.go_to_playstore_button, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          paramAnonymousDialogInterface = new Intent("android.intent.action.VIEW");
          paramAnonymousDialogInterface.setData(Uri.parse("market://details?id=com.google.vr.vrcore"));
          paramAnonymousDialogInterface.setPackage("com.android.vending");
          try
          {
            adx.this.startActivity(paramAnonymousDialogInterface);
            return;
          }
          catch (ActivityNotFoundException paramAnonymousDialogInterface)
          {
            Log.e(adx.a(), "Google Play Services is not installed, unable to download VrCore.");
          }
        }
      });
      return false;
    }
    if (paramInt == -2)
    {
      a(paramContext, aej.d.dialog_vr_core_not_enabled, aej.d.go_to_vr_listeners_settings_button, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          adx.this.startActivity(new Intent("android.settings.VR_LISTENER_SETTINGS"));
        }
      });
      return false;
    }
    return true;
  }
  
  private static boolean b()
  {
    return (b >= 24) || (Build.VERSION.SDK_INT >= 24);
  }
  
  private static boolean c()
  {
    return (b >= 25) || ("NMR1".equals(Build.VERSION.CODENAME)) || (Build.VERSION.SDK_INT >= 25);
  }
  
  public static boolean isVrModeSupported(Context paramContext)
  {
    return (b()) && (paramContext.getPackageManager().hasSystemFeature("android.software.vr.mode"));
  }
  
  public static boolean setSustainedPerformanceMode(Activity paramActivity, boolean paramBoolean)
  {
    if (!b()) {
      return false;
    }
    if (!((PowerManager)paramActivity.getSystemService("power")).isSustainedPerformanceModeSupported())
    {
      Log.d(a, "Sustained performance mode is not supported on this device.");
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
  
  public static boolean setVrModeEnabled(Activity paramActivity, boolean paramBoolean)
  {
    return setVrModeEnabled(paramActivity, paramBoolean, 1);
  }
  
  public static boolean setVrModeEnabled(Activity paramActivity, boolean paramBoolean, int paramInt)
  {
    if (!isVrModeSupported(paramActivity)) {
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
        if (((paramInt & 0x1) != 0) && (a(paramActivity, a(paramActivity))))
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
  
  /* Error */
  public static void setVrThread(int paramInt)
  {
    // Byte code:
    //   0: invokestatic 175	adx:b	()Z
    //   3: ifne +4 -> 7
    //   6: return
    //   7: ldc_w 277
    //   10: ldc_w 278
    //   13: iconst_1
    //   14: anewarray 18	java/lang/Class
    //   17: dup
    //   18: iconst_0
    //   19: getstatic 284	java/lang/Integer:TYPE	Ljava/lang/Class;
    //   22: aastore
    //   23: invokevirtual 288	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   26: astore_1
    //   27: aload_1
    //   28: aconst_null
    //   29: iconst_1
    //   30: anewarray 4	java/lang/Object
    //   33: dup
    //   34: iconst_0
    //   35: iload_0
    //   36: invokestatic 291	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   39: aastore
    //   40: invokevirtual 297	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   43: pop
    //   44: return
    //   45: astore_1
    //   46: getstatic 24	adx:a	Ljava/lang/String;
    //   49: astore_2
    //   50: aload_1
    //   51: invokestatic 237	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   54: astore_1
    //   55: aload_2
    //   56: new 239	java/lang/StringBuilder
    //   59: dup
    //   60: aload_1
    //   61: invokestatic 237	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   64: invokevirtual 243	java/lang/String:length	()I
    //   67: bipush 30
    //   69: iadd
    //   70: invokespecial 246	java/lang/StringBuilder:<init>	(I)V
    //   73: ldc_w 299
    //   76: invokevirtual 252	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   79: aload_1
    //   80: invokevirtual 252	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   83: invokevirtual 255	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   86: invokestatic 213	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   89: pop
    //   90: return
    //   91: astore_1
    //   92: invokestatic 301	adx:c	()Z
    //   95: ifeq +48 -> 143
    //   98: getstatic 24	adx:a	Ljava/lang/String;
    //   101: astore_2
    //   102: aload_1
    //   103: invokestatic 237	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   106: astore_1
    //   107: aload_2
    //   108: new 239	java/lang/StringBuilder
    //   111: dup
    //   112: aload_1
    //   113: invokestatic 237	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   116: invokevirtual 243	java/lang/String:length	()I
    //   119: bipush 38
    //   121: iadd
    //   122: invokespecial 246	java/lang/StringBuilder:<init>	(I)V
    //   125: ldc_w 303
    //   128: invokevirtual 252	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   131: aload_1
    //   132: invokevirtual 252	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   135: invokevirtual 255	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   138: invokestatic 213	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   141: pop
    //   142: return
    //   143: getstatic 24	adx:a	Ljava/lang/String;
    //   146: astore_2
    //   147: aload_1
    //   148: invokestatic 237	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   151: astore_1
    //   152: aload_2
    //   153: new 239	java/lang/StringBuilder
    //   156: dup
    //   157: aload_1
    //   158: invokestatic 237	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   161: invokevirtual 243	java/lang/String:length	()I
    //   164: bipush 38
    //   166: iadd
    //   167: invokespecial 246	java/lang/StringBuilder:<init>	(I)V
    //   170: ldc_w 303
    //   173: invokevirtual 252	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   176: aload_1
    //   177: invokevirtual 252	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   180: invokevirtual 255	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   183: invokestatic 258	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   186: pop
    //   187: return
    //   188: astore_1
    //   189: goto -143 -> 46
    //   192: astore_1
    //   193: goto -147 -> 46
    //   196: astore_1
    //   197: goto -105 -> 92
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	200	0	paramInt	int
    //   26	2	1	localMethod	java.lang.reflect.Method
    //   45	6	1	localInvocationTargetException	java.lang.reflect.InvocationTargetException
    //   54	26	1	str1	String
    //   91	12	1	localRuntimeException1	RuntimeException
    //   106	71	1	str2	String
    //   188	1	1	localRuntimeException2	RuntimeException
    //   192	1	1	localIllegalAccessException	IllegalAccessException
    //   196	1	1	localNoSuchMethodException	NoSuchMethodException
    //   49	104	2	str3	String
    // Exception table:
    //   from	to	target	type
    //   27	44	45	java/lang/reflect/InvocationTargetException
    //   7	27	91	java/lang/RuntimeException
    //   27	44	188	java/lang/RuntimeException
    //   27	44	192	java/lang/IllegalAccessException
    //   7	27	196	java/lang/NoSuchMethodException
  }
}
