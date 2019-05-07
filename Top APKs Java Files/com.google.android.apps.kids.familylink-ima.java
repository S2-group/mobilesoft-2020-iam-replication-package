import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PermissionInfo;
import android.os.Binder;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.UserManager;
import android.provider.Settings.Secure;
import com.google.android.chimera.Activity;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public final class ima
{
  public static final int a = 8192;
  public static final ComponentName b = new ComponentName("com.google.android.gms", "com.google.android.gms.kids.account.receiver.ProfileOwnerReceiver");
  private static final Intent c = new Intent().setPackage("com.android.vending").setAction("com.google.android.finsky.BIND_PLAY_INSTALL_SERVICE");
  private static Object d;
  
  private static List a(Context paramContext, String paramString)
  {
    localArrayList = new ArrayList();
    try
    {
      paramContext = paramContext.getPackageManager().queryPermissionsByGroup(paramString, 128);
      if (paramContext != null)
      {
        paramContext = paramContext.iterator();
        while (paramContext.hasNext()) {
          localArrayList.add(((PermissionInfo)paramContext.next()).name);
        }
      }
      return localArrayList;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      imb.e("Utils", "permissions not found for group = %s", new Object[] { paramString });
    }
  }
  
  public static List a(Context paramContext, String paramString1, String paramString2)
  {
    List localList = a(paramContext, paramString2);
    paramString2 = new ArrayList();
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramString1, 4096).requestedPermissions;
      int j = paramContext.length;
      int i = 0;
      while (i < j)
      {
        Object localObject = paramContext[i];
        if (localList.contains(localObject)) {
          paramString2.add(localObject);
        }
        i += 1;
      }
      return paramString2;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      imb.a("Utils", paramContext, "Invalid package name, %s", new Object[] { paramString1 });
    }
  }
  
  public static void a(DevicePolicyManager paramDevicePolicyManager)
  {
    Bundle localBundle = paramDevicePolicyManager.getApplicationRestrictions(b, "com.android.chrome");
    localBundle.putBoolean("ForceBrowserSignin", true);
    paramDevicePolicyManager.setApplicationRestrictions(b, "com.android.chrome", localBundle);
  }
  
  public static void a(Context paramContext, boolean paramBoolean)
  {
    if ((!paramBoolean) || (hma.a(paramContext).a()))
    {
      a(paramContext, paramBoolean, 51314688);
      ContentResolver localContentResolver = paramContext.getContentResolver();
      ilw localIlw = ilw.a(paramContext);
      if (paramBoolean)
      {
        if (!localIlw.b())
        {
          int i = Settings.Secure.getInt(localContentResolver, "lock_screen_show_notifications", 0);
          localIlw.a.edit().putInt("lock_screen_show_notifications", i).apply();
        }
        Settings.Secure.putInt(localContentResolver, "lock_screen_show_notifications", 0);
      }
      else if (localIlw.b())
      {
        Settings.Secure.putInt(localContentResolver, "lock_screen_show_notifications", localIlw.a.getInt("lock_screen_show_notifications", 0));
        localIlw.a.edit().remove("lock_screen_show_notifications").apply();
      }
      c(paramContext, paramBoolean);
      if (((Boolean)imc.a.b()).booleanValue())
      {
        f(paramContext, paramBoolean);
        return;
      }
      d(paramContext, paramBoolean);
      return;
    }
  }
  
  @SuppressLint({"PrivateApi", "WrongConstant"})
  private static void a(Context paramContext, boolean paramBoolean, int paramInt)
  {
    for (;;)
    {
      try
      {
        if (d == null) {
          d = paramContext.getApplicationContext().getSystemService("statusbar");
        }
        Method localMethod = Class.forName("android.app.StatusBarManager").getMethod("disable", new Class[] { Integer.TYPE });
        Object localObject = d;
        if (!paramBoolean)
        {
          paramInt = 0;
          localMethod.invoke(localObject, new Object[] { Integer.valueOf(paramInt) });
          ilq.a(paramContext).a(paramBoolean);
          return;
        }
      }
      catch (Exception paramContext)
      {
        imb.b("Utils", paramContext, "Could not disable status bar", new Object[0]);
        return;
      }
    }
  }
  
  public static boolean a(Context paramContext)
  {
    paramContext = (DevicePolicyManager)paramContext.getSystemService("device_policy");
    return (paramContext.isAdminActive(b)) && (paramContext.isProfileOwnerApp(b.getPackageName()));
  }
  
  public static boolean a(Context paramContext, int paramInt)
  {
    if (paramContext != null)
    {
      try
      {
        gxr.a(paramContext).a(paramInt);
      }
      catch (ReflectiveOperationException|SecurityException localReflectiveOperationException)
      {
        break label72;
      }
      catch (NoSuchMethodError localNoSuchMethodError) {}
      gxr.class.getMethod("verifyUidIsGoogleSigned", new Class[] { PackageManager.class, Integer.TYPE }).invoke(gxr.a(paramContext), new Object[] { paramContext.getPackageManager(), Integer.valueOf(paramInt) });
      return true;
      label72:
      imb.a("Utils", localNoSuchMethodError, "Signature verification failed", new Object[0]);
      return "com.google.android.apps.kids.familylink".equals(paramContext.getPackageManager().getNameForUid(Binder.getCallingUid()));
    }
    return false;
  }
  
  public static boolean a(Activity paramActivity)
  {
    String str = hlp.a(paramActivity);
    if (str != null)
    {
      try
      {
        gxr.a(paramActivity).a(str);
      }
      catch (ReflectiveOperationException|SecurityException paramActivity)
      {
        break label74;
      }
      catch (NoSuchMethodError localNoSuchMethodError) {}
      gxr.class.getMethod("verifyPackageIsGoogleSigned", new Class[] { PackageManager.class, String.class }).invoke(gxr.a(paramActivity), new Object[] { paramActivity.getPackageManager(), str });
      return true;
      label74:
      imb.a("Utils", paramActivity, "Signature verification failed", new Object[0]);
      return "com.google.android.apps.kids.familylink".equals(str);
    }
    imb.e("Utils", "Was the Activity started with startActivityForResult?", new Object[0]);
    return false;
  }
  
  public static int b(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    try
    {
      int i = localPackageManager.getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      imb.e(null, "Could not get package info for gmscore", new Object[0]);
    }
    return 0;
  }
  
  public static void b(Context paramContext, boolean paramBoolean)
  {
    a(paramContext, paramBoolean, 65994752);
  }
  
  public static void c(Context paramContext)
  {
    if (a(paramContext))
    {
      DevicePolicyManager localDevicePolicyManager = (DevicePolicyManager)paramContext.getSystemService("device_policy");
      paramContext = paramContext.getPackageManager();
      try
      {
        paramContext.getPackageInfo("com.google.android.apps.kids.familylink", 0);
        paramContext = localDevicePolicyManager.getApplicationRestrictions(b, "com.google.android.apps.kids.familylink");
        if (paramContext.getBoolean("turn_off_familylink_supervision_key"))
        {
          paramContext.putBoolean("turn_off_familylink_supervision_key", false);
          localDevicePolicyManager.setApplicationRestrictions(b, "com.google.android.apps.kids.familylink", paramContext);
          return;
        }
      }
      catch (PackageManager.NameNotFoundException paramContext)
      {
        imb.e("System", "FamilyLink not found", new Object[0]);
        return;
      }
    }
  }
  
  public static void c(Context paramContext, boolean paramBoolean)
  {
    paramContext = (DevicePolicyManager)paramContext.getSystemService("device_policy");
    try
    {
      paramContext.setCameraDisabled(b, paramBoolean);
      return;
    }
    catch (SecurityException paramContext)
    {
      imb.b(null, paramContext, "Can't disable camera", new Object[0]);
    }
  }
  
  public static void d(Context paramContext)
  {
    if (hhl.c())
    {
      paramContext = (DevicePolicyManager)paramContext.getSystemService("device_policy");
      try
      {
        paramContext.setAutoTimeRequired(b, true);
        return;
      }
      catch (SecurityException paramContext)
      {
        imb.b(null, paramContext, "Can't enforce auto time", new Object[0]);
        return;
      }
    }
  }
  
  public static void d(Context paramContext, boolean paramBoolean)
  {
    DevicePolicyManager localDevicePolicyManager = (DevicePolicyManager)paramContext.getSystemService("device_policy");
    if (paramBoolean) {}
    try
    {
      localDevicePolicyManager.addUserRestriction(b, "no_create_windows");
      return;
    }
    catch (SecurityException localSecurityException)
    {
      imb.e("Utils", "Secondary user", new Object[0]);
      f(paramContext, paramBoolean);
    }
    localDevicePolicyManager.clearUserRestriction(b, "no_create_windows");
    return;
  }
  
  @TargetApi(24)
  public static void e(Context paramContext)
  {
    paramContext = (DevicePolicyManager)paramContext.getSystemService("device_policy");
    Bundle localBundle = paramContext.getApplicationRestrictions(b, "com.android.chrome");
    if (localBundle.containsKey("ForceBrowserSignin"))
    {
      localBundle.remove("ForceBrowserSignin");
      paramContext.setApplicationRestrictions(b, "com.android.chrome", localBundle);
    }
    paramContext.clearProfileOwner(b);
    if (!hhl.b()) {
      try
      {
        paramContext.removeActiveAdmin(b);
        return;
      }
      catch (SecurityException paramContext)
      {
        imb.b(null, paramContext, "Can't remove admin", new Object[0]);
        return;
      }
    }
  }
  
  @TargetApi(25)
  public static void e(Context paramContext, boolean paramBoolean)
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.copyTypes(TypeTransformer.java:311)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.fixTypes(TypeTransformer.java:226)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:207)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
  }
  
  @TargetApi(23)
  public static void f(Context paramContext)
  {
    if (hhl.a()) {
      try
      {
        ((DevicePolicyManager)paramContext.getSystemService("device_policy")).addUserRestriction(b, "no_safe_boot");
        return;
      }
      catch (SecurityException paramContext)
      {
        imb.b(null, paramContext, "Can't set DISALLOW_SAFE_BOOT", new Object[0]);
        return;
      }
    }
  }
  
  private static void f(Context paramContext, boolean paramBoolean)
  {
    ilw localIlw = ilw.a(paramContext);
    ilq localIlq = ilq.a(paramContext);
    Object localObject2;
    if (paramBoolean)
    {
      localObject1 = localIlw.a();
      localObject2 = j(paramContext).iterator();
      while (((Iterator)localObject2).hasNext())
      {
        String str = (String)((Iterator)localObject2).next();
        if (localIlq.a(str))
        {
          localIlq.a(str, false);
          paramContext = String.valueOf(str);
          if (paramContext.length() == 0) {
            paramContext = new String("Disallow draw over other apps : ");
          } else {
            paramContext = "Disallow draw over other apps : ".concat(paramContext);
          }
          imb.d("Utils", paramContext, new Object[0]);
          ((Set)localObject1).add(str);
        }
      }
      localIlw.a.edit().putStringSet("pref_kids_apps_with_draw_over_other_apps_permission", (Set)localObject1).apply();
      return;
    }
    if (hhl.a()) {
      paramContext = localIlw.a();
    } else {
      paramContext = j(paramContext);
    }
    Object localObject1 = paramContext.iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (String)((Iterator)localObject1).next();
      paramContext = String.valueOf(localObject2);
      if (paramContext.length() == 0) {
        paramContext = new String("Allow draw over other apps : ");
      } else {
        paramContext = "Allow draw over other apps : ".concat(paramContext);
      }
      imb.d("Utils", paramContext, new Object[0]);
      localIlq.a((String)localObject2, true);
    }
    localIlw.a.edit().remove("pref_kids_apps_with_draw_over_other_apps_permission").apply();
  }
  
  public static void g(Context paramContext)
  {
    if (!a(paramContext))
    {
      imb.e("Utils", "Not the profile owner. Skipping refresh of active admin.", new Object[0]);
      return;
    }
    if (b(paramContext) >= 10400000)
    {
      DevicePolicyManager localDevicePolicyManager = (DevicePolicyManager)paramContext.getSystemService("device_policy");
      int i = 0;
      while (i < 9)
      {
        int j = new int[] { 7, 8, 9, 6, 3, 0, 2, 1, 4 }[i];
        if (localDevicePolicyManager.hasGrantedPolicy(b, j)) {
          i += 1;
        } else {
          try
          {
            if (Build.VERSION.SDK_INT < 24) {
              localObject1 = ((UserManager)paramContext.getSystemService("user")).getUserRestrictions();
            } else {
              localObject1 = ((DevicePolicyManager)paramContext.getSystemService("device_policy")).getUserRestrictions(b);
            }
            localObject2 = DevicePolicyManager.class.getMethod("setActiveAdmin", new Class[] { ComponentName.class, Boolean.TYPE });
            if (localObject2 != null)
            {
              ((Method)localObject2).invoke(localDevicePolicyManager, new Object[] { b, Boolean.valueOf(true) });
              imb.d("Utils", "Successfully refreshed device admin", new Object[0]);
              Iterator localIterator = ((Bundle)localObject1).keySet().iterator();
              while (localIterator.hasNext())
              {
                String str = (String)localIterator.next();
                if (((Bundle)localObject1).getBoolean(str, false))
                {
                  localObject2 = String.valueOf(str);
                  if (((String)localObject2).length() != 0) {
                    localObject2 = "Setting user restriction: ".concat((String)localObject2);
                  } else {
                    localObject2 = new String("Setting user restriction: ");
                  }
                  imb.c("Utils", (String)localObject2, new Object[0]);
                  localDevicePolicyManager.addUserRestriction(b, str);
                }
              }
              paramContext.sendBroadcast(new Intent("com.google.android.apps.kids.kidsetup.action.FORCE_SYNC").setPackage("com.google.android.apps.kids.familylink"));
            }
            return;
          }
          catch (Exception paramContext)
          {
            Object localObject1 = String.valueOf(paramContext);
            Object localObject2 = new StringBuilder(String.valueOf(localObject1).length() + 44);
            ((StringBuilder)localObject2).append("Unexpected exception when refreshing admin: ");
            ((StringBuilder)localObject2).append((String)localObject1);
            imb.b("Utils", paramContext, ((StringBuilder)localObject2).toString(), new Object[0]);
            return;
          }
        }
      }
      imb.d("Utils", "The admin has all policies. Skipping refresh.", new Object[0]);
      return;
    }
    imb.e("Utils", "Below V10. Skipping refresh of active admin. current version : %s", new Object[] { Integer.valueOf(b(paramContext)) });
  }
  
  public static void h(Context paramContext)
  {
    paramContext = (DevicePolicyManager)paramContext.getSystemService("device_policy");
    try
    {
      ComponentName localComponentName1 = new ComponentName("com.google.android.apps.youtube.kids", (String)imc.d.b());
      imb.d(null, "Setting YouTube Kids prferred activity...", new Object[0]);
      ComponentName localComponentName2 = b;
      IntentFilter localIntentFilter = new IntentFilter("android.intent.action.VIEW");
      localIntentFilter.addCategory("android.intent.category.DEFAULT");
      localIntentFilter.addCategory("android.intent.category.BROWSABLE");
      localIntentFilter.addDataScheme("http");
      localIntentFilter.addDataScheme("https");
      localIntentFilter.addDataAuthority("youtube.com", null);
      localIntentFilter.addDataAuthority("www.youtube.com", null);
      localIntentFilter.addDataAuthority("m.youtube.com", null);
      localIntentFilter.addDataAuthority("youtu.be", null);
      localIntentFilter.addDataPath("/", 0);
      paramContext.addPersistentPreferredActivity(localComponentName2, localIntentFilter, localComponentName1);
      return;
    }
    catch (SecurityException paramContext)
    {
      imb.a(null, paramContext, "Can't set YouTube preferred activity!", new Object[0]);
    }
  }
  
  /* Error */
  public static void i(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 58	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   4: ldc_w 588
    //   7: iconst_0
    //   8: invokevirtual 107	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   11: pop
    //   12: aconst_null
    //   13: ldc_w 590
    //   16: iconst_0
    //   17: anewarray 4	java/lang/Object
    //   20: invokestatic 508	imb:c	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
    //   23: return
    //   24: astore_3
    //   25: new 592	gwx
    //   28: dup
    //   29: invokespecial 593	gwx:<init>	()V
    //   32: astore_3
    //   33: invokestatic 598	hkj:a	()Lhkj;
    //   36: astore 4
    //   38: getstatic 45	ima:c	Landroid/content/Intent;
    //   41: astore 5
    //   43: aload_0
    //   44: invokevirtual 602	java/lang/Object:getClass	()Ljava/lang/Class;
    //   47: invokevirtual 605	java/lang/Class:getName	()Ljava/lang/String;
    //   50: astore 6
    //   52: aload 5
    //   54: invokevirtual 609	android/content/Intent:getComponent	()Landroid/content/ComponentName;
    //   57: astore 7
    //   59: aload 7
    //   61: ifnull +28 -> 89
    //   64: aload_0
    //   65: aload 7
    //   67: invokevirtual 295	android/content/ComponentName:getPackageName	()Ljava/lang/String;
    //   70: invokestatic 614	hew:a	(Landroid/content/Context;Ljava/lang/String;)Z
    //   73: ifeq +16 -> 89
    //   76: ldc_w 616
    //   79: ldc_w 618
    //   82: invokestatic 624	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   85: pop
    //   86: goto +538 -> 624
    //   89: aload_0
    //   90: aload 5
    //   92: aload_3
    //   93: iconst_1
    //   94: invokevirtual 628	android/content/Context:bindService	(Landroid/content/Intent;Landroid/content/ServiceConnection;I)Z
    //   97: istore_2
    //   98: getstatic 631	hkj:a	Z
    //   101: ifne +6 -> 107
    //   104: goto +22 -> 126
    //   107: iload_2
    //   108: ifeq +18 -> 126
    //   111: aload 4
    //   113: aload_0
    //   114: aload_3
    //   115: invokestatic 634	hkj:a	(Landroid/content/ServiceConnection;)Ljava/lang/String;
    //   118: aload 6
    //   120: aload 5
    //   122: iconst_2
    //   123: invokevirtual 637	hkj:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Landroid/content/Intent;I)V
    //   126: iload_2
    //   127: ifeq +497 -> 624
    //   130: ldc_w 639
    //   133: invokestatic 644	hhi:checkNotMainThread	(Ljava/lang/String;)V
    //   136: aload_3
    //   137: getfield 645	gwx:a	Z
    //   140: ifne +412 -> 552
    //   143: aload_3
    //   144: iconst_1
    //   145: putfield 645	gwx:a	Z
    //   148: aload_3
    //   149: getfield 648	gwx:b	Ljava/util/concurrent/BlockingQueue;
    //   152: invokeinterface 653 1 0
    //   157: checkcast 655	android/os/IBinder
    //   160: invokestatic 660	gsj:a	(Landroid/os/IBinder;)Lgsi;
    //   163: astore 5
    //   165: new 135	android/os/Bundle
    //   168: dup
    //   169: invokespecial 661	android/os/Bundle:<init>	()V
    //   172: astore 6
    //   174: aload 6
    //   176: ldc_w 663
    //   179: iconst_1
    //   180: invokevirtual 665	android/os/Bundle:putInt	(Ljava/lang/String;I)V
    //   183: aload 6
    //   185: ldc_w 667
    //   188: iconst_0
    //   189: invokevirtual 139	android/os/Bundle:putBoolean	(Ljava/lang/String;Z)V
    //   192: aload 6
    //   194: ldc_w 669
    //   197: iconst_0
    //   198: invokevirtual 139	android/os/Bundle:putBoolean	(Ljava/lang/String;Z)V
    //   201: aload 6
    //   203: ldc_w 671
    //   206: iconst_0
    //   207: invokevirtual 139	android/os/Bundle:putBoolean	(Ljava/lang/String;Z)V
    //   210: aload 5
    //   212: aload_0
    //   213: invokevirtual 350	android/content/Context:getPackageName	()Ljava/lang/String;
    //   216: ldc_w 588
    //   219: aload 6
    //   221: invokeinterface 676 4 0
    //   226: astore 5
    //   228: aload 5
    //   230: ldc_w 678
    //   233: bipush -4
    //   235: invokevirtual 679	android/os/Bundle:getInt	(Ljava/lang/String;I)I
    //   238: istore_1
    //   239: iload_1
    //   240: bipush -5
    //   242: if_icmpeq +291 -> 533
    //   245: iload_1
    //   246: bipush -4
    //   248: if_icmpeq +176 -> 424
    //   251: iload_1
    //   252: bipush -3
    //   254: if_icmpeq +127 -> 381
    //   257: iload_1
    //   258: bipush -2
    //   260: if_icmpeq +78 -> 338
    //   263: iload_1
    //   264: iconst_m1
    //   265: if_icmpeq +73 -> 338
    //   268: iload_1
    //   269: ifeq +55 -> 324
    //   272: new 517	java/lang/StringBuilder
    //   275: dup
    //   276: bipush 77
    //   278: invokespecial 519	java/lang/StringBuilder:<init>	(I)V
    //   281: astore 5
    //   283: aload 5
    //   285: ldc_w 681
    //   288: invokevirtual 525	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   291: pop
    //   292: aload 5
    //   294: iload_1
    //   295: invokevirtual 684	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   298: pop
    //   299: aload 5
    //   301: ldc_w 686
    //   304: invokevirtual 525	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   307: pop
    //   308: aconst_null
    //   309: aload 5
    //   311: invokevirtual 528	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   314: iconst_0
    //   315: anewarray 4	java/lang/Object
    //   318: invokestatic 688	imb:f	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
    //   321: goto +223 -> 544
    //   324: aconst_null
    //   325: ldc_w 690
    //   328: iconst_0
    //   329: anewarray 4	java/lang/Object
    //   332: invokestatic 508	imb:c	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
    //   335: goto +209 -> 544
    //   338: new 517	java/lang/StringBuilder
    //   341: dup
    //   342: bipush 61
    //   344: invokespecial 519	java/lang/StringBuilder:<init>	(I)V
    //   347: astore 5
    //   349: aload 5
    //   351: ldc_w 692
    //   354: invokevirtual 525	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   357: pop
    //   358: aload 5
    //   360: iload_1
    //   361: invokevirtual 684	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   364: pop
    //   365: aconst_null
    //   366: aload 5
    //   368: invokevirtual 528	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   371: iconst_0
    //   372: anewarray 4	java/lang/Object
    //   375: invokestatic 688	imb:f	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
    //   378: goto +166 -> 544
    //   381: new 517	java/lang/StringBuilder
    //   384: dup
    //   385: bipush 61
    //   387: invokespecial 519	java/lang/StringBuilder:<init>	(I)V
    //   390: astore 5
    //   392: aload 5
    //   394: ldc_w 692
    //   397: invokevirtual 525	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   400: pop
    //   401: aload 5
    //   403: iload_1
    //   404: invokevirtual 684	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   407: pop
    //   408: aconst_null
    //   409: aload 5
    //   411: invokevirtual 528	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   414: iconst_0
    //   415: anewarray 4	java/lang/Object
    //   418: invokestatic 688	imb:f	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
    //   421: goto +123 -> 544
    //   424: aload 5
    //   426: ldc_w 694
    //   429: invokevirtual 698	android/os/Bundle:getBundle	(Ljava/lang/String;)Landroid/os/Bundle;
    //   432: astore 6
    //   434: aload 6
    //   436: ldc_w 700
    //   439: invokevirtual 703	android/os/Bundle:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   442: astore 5
    //   444: aload 6
    //   446: ldc_w 705
    //   449: invokevirtual 703	android/os/Bundle:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   452: astore 6
    //   454: new 517	java/lang/StringBuilder
    //   457: dup
    //   458: aload 5
    //   460: invokestatic 444	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   463: invokevirtual 447	java/lang/String:length	()I
    //   466: bipush 91
    //   468: iadd
    //   469: aload 6
    //   471: invokestatic 444	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   474: invokevirtual 447	java/lang/String:length	()I
    //   477: iadd
    //   478: invokespecial 519	java/lang/StringBuilder:<init>	(I)V
    //   481: astore 7
    //   483: aload 7
    //   485: ldc_w 707
    //   488: invokevirtual 525	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   491: pop
    //   492: aload 7
    //   494: aload 5
    //   496: invokevirtual 525	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   499: pop
    //   500: aload 7
    //   502: ldc_w 709
    //   505: invokevirtual 525	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   508: pop
    //   509: aload 7
    //   511: aload 6
    //   513: invokevirtual 525	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   516: pop
    //   517: aconst_null
    //   518: aload 7
    //   520: invokevirtual 528	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   523: iconst_0
    //   524: anewarray 4	java/lang/Object
    //   527: invokestatic 688	imb:f	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
    //   530: goto +14 -> 544
    //   533: aconst_null
    //   534: ldc_w 590
    //   537: iconst_0
    //   538: anewarray 4	java/lang/Object
    //   541: invokestatic 508	imb:c	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
    //   544: aload 4
    //   546: aload_0
    //   547: aload_3
    //   548: invokevirtual 712	hkj:a	(Landroid/content/Context;Landroid/content/ServiceConnection;)V
    //   551: return
    //   552: new 714	java/lang/IllegalStateException
    //   555: dup
    //   556: ldc_w 716
    //   559: invokespecial 717	java/lang/IllegalStateException:<init>	(Ljava/lang/String;)V
    //   562: athrow
    //   563: astore 5
    //   565: goto +49 -> 614
    //   568: astore 5
    //   570: aconst_null
    //   571: aload 5
    //   573: ldc_w 719
    //   576: iconst_0
    //   577: anewarray 4	java/lang/Object
    //   580: invokestatic 122	imb:a	(Ljava/lang/String;Ljava/lang/Throwable;Ljava/lang/String;[Ljava/lang/Object;)V
    //   583: aload 4
    //   585: aload_0
    //   586: aload_3
    //   587: invokevirtual 712	hkj:a	(Landroid/content/Context;Landroid/content/ServiceConnection;)V
    //   590: return
    //   591: astore 5
    //   593: aconst_null
    //   594: aload 5
    //   596: ldc_w 719
    //   599: iconst_0
    //   600: anewarray 4	java/lang/Object
    //   603: invokestatic 122	imb:a	(Ljava/lang/String;Ljava/lang/Throwable;Ljava/lang/String;[Ljava/lang/Object;)V
    //   606: aload 4
    //   608: aload_0
    //   609: aload_3
    //   610: invokevirtual 712	hkj:a	(Landroid/content/Context;Landroid/content/ServiceConnection;)V
    //   613: return
    //   614: aload 4
    //   616: aload_0
    //   617: aload_3
    //   618: invokevirtual 712	hkj:a	(Landroid/content/Context;Landroid/content/ServiceConnection;)V
    //   621: aload 5
    //   623: athrow
    //   624: aconst_null
    //   625: ldc_w 721
    //   628: iconst_0
    //   629: anewarray 4	java/lang/Object
    //   632: invokestatic 100	imb:e	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
    //   635: aload 4
    //   637: aload_0
    //   638: aload_3
    //   639: invokevirtual 712	hkj:a	(Landroid/content/Context;Landroid/content/ServiceConnection;)V
    //   642: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	643	0	paramContext	Context
    //   238	166	1	i	int
    //   97	30	2	bool	boolean
    //   24	1	3	localNameNotFoundException	PackageManager.NameNotFoundException
    //   32	607	3	localGwx	gwx
    //   36	600	4	localHkj	hkj
    //   41	454	5	localObject1	Object
    //   563	1	5	localObject2	Object
    //   568	4	5	localRemoteException	android.os.RemoteException
    //   591	31	5	localInterruptedException	InterruptedException
    //   50	462	6	localObject3	Object
    //   57	462	7	localObject4	Object
    // Exception table:
    //   from	to	target	type
    //   0	23	24	android/content/pm/PackageManager$NameNotFoundException
    //   130	239	563	finally
    //   272	321	563	finally
    //   324	335	563	finally
    //   338	378	563	finally
    //   381	421	563	finally
    //   424	530	563	finally
    //   533	544	563	finally
    //   552	563	563	finally
    //   570	583	563	finally
    //   593	606	563	finally
    //   130	239	568	android/os/RemoteException
    //   272	321	568	android/os/RemoteException
    //   324	335	568	android/os/RemoteException
    //   338	378	568	android/os/RemoteException
    //   381	421	568	android/os/RemoteException
    //   424	530	568	android/os/RemoteException
    //   533	544	568	android/os/RemoteException
    //   552	563	568	android/os/RemoteException
    //   130	239	591	java/lang/InterruptedException
    //   272	321	591	java/lang/InterruptedException
    //   324	335	591	java/lang/InterruptedException
    //   338	378	591	java/lang/InterruptedException
    //   381	421	591	java/lang/InterruptedException
    //   424	530	591	java/lang/InterruptedException
    //   533	544	591	java/lang/InterruptedException
    //   552	563	591	java/lang/InterruptedException
  }
  
  private static Set j(Context paramContext)
  {
    HashSet localHashSet = new HashSet();
    paramContext = paramContext.getPackageManager().getInstalledPackages(4096).iterator();
    while (paramContext.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
      if ((localPackageInfo.applicationInfo.flags & 0x81) == 0)
      {
        String[] arrayOfString = localPackageInfo.requestedPermissions;
        int j = 0;
        if (arrayOfString != null)
        {
          int k = arrayOfString.length;
          i = 0;
          while (i < k) {
            if (!"android.permission.SYSTEM_ALERT_WINDOW".equals(arrayOfString[i]))
            {
              i += 1;
            }
            else
            {
              i = 1;
              break label112;
            }
          }
        }
        int i = j;
        label112:
        if (i != 0) {
          localHashSet.add(localPackageInfo.packageName);
        }
      }
    }
    localHashSet.remove("com.google.android.apps.kids.familylink");
    return localHashSet;
  }
}
