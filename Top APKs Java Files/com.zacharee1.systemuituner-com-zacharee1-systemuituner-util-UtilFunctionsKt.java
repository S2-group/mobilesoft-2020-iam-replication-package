package com.zacharee1.systemuituner.util;

import android.app.Activity;
import android.app.AppOpsManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.preference.SwitchPreference;
import android.provider.Settings.Global;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.support.v7.app.AlertDialog.Builder;
import android.text.TextUtils;
import android.util.TypedValue;
import android.widget.ListAdapter;
import com.zacharee1.systemuituner.activites.MainActivity;
import com.zacharee1.systemuituner.activites.OptionsActivity;
import com.zacharee1.systemuituner.activites.info.SettingWriteFailed;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000j\n\000\n\002\020\013\n\000\n\002\020\016\n\000\n\002\020\021\n\002\b\002\n\002\030\002\n\002\b\003\n\002\030\002\n\000\n\002\030\002\n\002\b\003\n\002\020!\n\002\030\002\n\002\020 \n\002\b\005\n\002\030\002\n\002\b\002\n\002\020\002\n\002\b\002\n\002\020\007\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\020\000\n\002\b\003\032\006\020\000\032\0020\001\032!\020\002\032\004\030\0010\0032\022\020\004\032\n\022\006\b\001\022\0020\0030\005\"\0020\003¢\006\002\020\006\032\034\020\007\032\0020\001*\0020\b2\b\020\t\032\004\030\0010\0032\006\020\n\032\0020\001\032B\020\013\032\"\022\f\022\n \r*\004\030\0010\0030\0030\fj\020\022\f\022\n \r*\004\030\0010\0030\003`\016*\0020\b2\026\020\017\032\022\022\004\022\0020\0030\fj\b\022\004\022\0020\003`\016\032\n\020\020\032\0020\001*\0020\b\032.\020\021\032&\022\f\022\n \r*\004\030\0010\0230\023 \r*\022\022\f\022\n \r*\004\030\0010\0230\023\030\0010\0240\022*\0020\b\032\024\020\025\032\0020\001*\0020\b2\b\020\026\032\004\030\0010\003\032\n\020\027\032\0020\001*\0020\b\032\n\020\030\032\0020\001*\0020\b\032\022\020\031\032\0020\001*\0020\0322\006\020\033\032\0020\003\032\024\020\034\032\0020\035*\0020\b2\b\020\036\032\004\030\0010\003\032\022\020\037\032\0020 *\0020\b2\006\020!\032\0020 \032\n\020\"\032\0020\035*\0020#\032\n\020$\032\0020\035*\0020%\032\036\020&\032\0020\001*\0020\b2\b\020\t\032\004\030\0010\0032\b\020'\032\004\030\0010(\032\036\020)\032\0020\001*\0020\b2\b\020\t\032\004\030\0010\0032\b\020'\032\004\030\0010(\032\036\020*\032\0020\001*\0020\b2\b\020\t\032\004\030\0010\0032\b\020'\032\004\030\0010(¨\006+"}, d2={"checkMIUI", "", "runCommand", "", "commands", "", "([Ljava/lang/String;)Ljava/lang/String;", "changeBlacklist", "Landroid/content/Context;", "key", "remove", "checkPermissions", "Ljava/util/ArrayList;", "kotlin.jvm.PlatformType", "Lkotlin/collections/ArrayList;", "permissions", "checkSamsung", "getInstalledApps", "", "Landroid/content/pm/ApplicationInfo;", "", "hasSpecificPerm", "permission", "hasUsage", "isInDarkMode", "isPackageInstalled", "Landroid/content/pm/PackageManager;", "packageName", "launchErrorActivity", "", "baseCommand", "pxToDp", "", "px", "startUp", "Landroid/app/Activity;", "updateBlacklistSwitches", "Landroid/preference/PreferenceFragment;", "writeGlobal", "value", "", "writeSecure", "writeSystem", "app_release"}, k=2, mv={1, 1, 10})
public final class UtilFunctionsKt
{
  public static final boolean changeBlacklist(@NotNull Context paramContext, @Nullable String paramString, boolean paramBoolean)
  {
    Intrinsics.checkParameterIsNotNull(paramContext, "$receiver");
    boolean bool = false;
    if (paramString != null)
    {
      Object localObject1 = Settings.Secure.getString(paramContext.getContentResolver(), "icon_blacklist");
      if (localObject1 == null) {
        localObject1 = "";
      }
      localObject1 = new TreeSet((Collection)StringsKt.split$default((CharSequence)localObject1, new String[] { "," }, false, 0, 6, null));
      Object localObject2 = (Iterable)new TreeSet((Collection)StringsKt.split$default((CharSequence)paramString, new String[] { "," }, false, 0, 6, null));
      paramString = (Collection)new ArrayList();
      localObject2 = ((Iterable)localObject2).iterator();
      while (((Iterator)localObject2).hasNext())
      {
        Object localObject3 = ((Iterator)localObject2).next();
        String str = (String)localObject3;
        if (paramBoolean) {
          bool = ((TreeSet)localObject1).contains(str);
        } else if (!((TreeSet)localObject1).contains(str)) {
          bool = true;
        } else {
          bool = false;
        }
        if (bool) {
          paramString.add(localObject3);
        }
      }
      paramString = ((Iterable)paramString).iterator();
      while (paramString.hasNext())
      {
        localObject2 = (String)paramString.next();
        if (paramBoolean) {
          ((TreeSet)localObject1).remove(localObject2);
        } else {
          ((TreeSet)localObject1).add(localObject2);
        }
      }
      paramString = TextUtils.join((CharSequence)",", (Iterable)localObject1);
      if (paramString == null) {
        paramString = "";
      }
      bool = writeSecure(paramContext, "icon_blacklist", paramString);
    }
    return bool;
  }
  
  public static final boolean checkMIUI()
  {
    Object localObject2 = new ArrayList();
    ((ArrayList)localObject2).add("ro.miui.ui.version.code");
    ((ArrayList)localObject2).add("ro.miui.ui.version.name");
    ((ArrayList)localObject2).add("ro.miui.cust_variant");
    ((ArrayList)localObject2).add("ro.miui.has_cust_partition");
    ((ArrayList)localObject2).add("ro.miui.has_handy_mode_sf");
    ((ArrayList)localObject2).add("ro.miui.has_real_blur");
    ((ArrayList)localObject2).add("ro.miui.mcc");
    ((ArrayList)localObject2).add("ro.miui.mnc");
    ((ArrayList)localObject2).add("ro.miui.region");
    ((ArrayList)localObject2).add("ro.miui.version.code_time");
    Object localObject1 = new ArrayList();
    ((ArrayList)localObject1).addAll((Collection)localObject2);
    try
    {
      localObject2 = Class.forName("android.os.SystemProperties").getMethod("get", new Class[] { String.class });
      localObject1 = ((ArrayList)localObject1).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        int i = ((CharSequence)((Method)localObject2).invoke(null, new Object[] { (String)((Iterator)localObject1).next() }).toString()).length();
        if (i == 0) {
          i = 1;
        } else {
          i = 0;
        }
        if (i == 0) {
          return true;
        }
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }
  
  @NotNull
  public static final ArrayList<String> checkPermissions(@NotNull Context paramContext, @NotNull ArrayList<String> paramArrayList)
  {
    Intrinsics.checkParameterIsNotNull(paramContext, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramArrayList, "permissions");
    Object localObject1 = (Iterable)paramArrayList;
    paramArrayList = (Collection)new ArrayList();
    localObject1 = ((Iterable)localObject1).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      Object localObject2 = ((Iterator)localObject1).next();
      int i;
      if (paramContext.checkCallingOrSelfPermission((String)localObject2) != 0) {
        i = 1;
      } else {
        i = 0;
      }
      if (i != 0) {
        paramArrayList.add(localObject2);
      }
    }
    return new ArrayList((Collection)paramArrayList);
  }
  
  public static final boolean checkSamsung(@NotNull Context paramContext)
  {
    Intrinsics.checkParameterIsNotNull(paramContext, "$receiver");
    return paramContext.getPackageManager().hasSystemFeature("com.samsung.feature.samsung_experience_mobile");
  }
  
  public static final List<ApplicationInfo> getInstalledApps(@NotNull Context paramContext)
  {
    Intrinsics.checkParameterIsNotNull(paramContext, "$receiver");
    return paramContext.getPackageManager().getInstalledApplications(128);
  }
  
  public static final boolean hasSpecificPerm(@NotNull Context paramContext, @Nullable String paramString)
  {
    Intrinsics.checkParameterIsNotNull(paramContext, "$receiver");
    return paramContext.checkCallingOrSelfPermission(paramString) == 0;
  }
  
  public static final boolean hasUsage(@NotNull Context paramContext)
  {
    Intrinsics.checkParameterIsNotNull(paramContext, "$receiver");
    Object localObject = paramContext.getSystemService("appops");
    if (localObject == null) {
      throw new TypeCastException("null cannot be cast to non-null type android.app.AppOpsManager");
    }
    int i = ((AppOpsManager)localObject).checkOpNoThrow(43, paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 0).uid, paramContext.getPackageName());
    if (i == 3) {
      if (paramContext.checkCallingOrSelfPermission("android.permission.PACKAGE_USAGE_STATS") == 0) {
        return true;
      }
    }
    while (i != 0) {
      return false;
    }
    return true;
  }
  
  public static final boolean isInDarkMode(@NotNull Context paramContext)
  {
    Intrinsics.checkParameterIsNotNull(paramContext, "$receiver");
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean("dark_mode", false);
  }
  
  public static final boolean isPackageInstalled(@NotNull PackageManager paramPackageManager, @NotNull String paramString)
  {
    Intrinsics.checkParameterIsNotNull(paramPackageManager, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramString, "packageName");
    try
    {
      paramPackageManager.getPackageInfo(paramString, 0);
      return true;
    }
    catch (Exception paramPackageManager) {}
    return false;
  }
  
  public static final void launchErrorActivity(@NotNull Context paramContext, @Nullable String paramString)
  {
    Intrinsics.checkParameterIsNotNull(paramContext, "$receiver");
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("adb shell ");
    ((StringBuilder)localObject).append(paramString);
    paramString = ((StringBuilder)localObject).toString();
    localObject = new Intent(paramContext, SettingWriteFailed.class);
    ((Intent)localObject).setAction("android.intent.action.VIEW");
    ((Intent)localObject).addFlags(268435456);
    ((Intent)localObject).putExtra("command", paramString);
    paramContext.startActivity((Intent)localObject);
  }
  
  public static final float pxToDp(@NotNull Context paramContext, float paramFloat)
  {
    Intrinsics.checkParameterIsNotNull(paramContext, "$receiver");
    paramContext = paramContext.getResources();
    Intrinsics.checkExpressionValueIsNotNull(paramContext, "resources");
    return TypedValue.applyDimension(1, paramFloat, paramContext.getDisplayMetrics());
  }
  
  @Nullable
  public static final String runCommand(@NotNull String... paramVarArgs)
  {
    Intrinsics.checkParameterIsNotNull(paramVarArgs, "commands");
    try
    {
      Process localProcess = Runtime.getRuntime().exec("sh");
      Intrinsics.checkExpressionValueIsNotNull(localProcess, "comm");
      DataOutputStream localDataOutputStream = new DataOutputStream(localProcess.getOutputStream());
      int j = paramVarArgs.length;
      int i = 0;
      while (i < j)
      {
        localObject1 = paramVarArgs[i];
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append((String)localObject1);
        ((StringBuilder)localObject2).append("\n");
        localDataOutputStream.writeBytes(((StringBuilder)localObject2).toString());
        localDataOutputStream.flush();
        i += 1;
      }
      localDataOutputStream.writeBytes("exit\n");
      localDataOutputStream.flush();
      Object localObject2 = new BufferedReader((Reader)new InputStreamReader(localProcess.getInputStream()));
      Object localObject1 = new BufferedReader((Reader)new InputStreamReader(localProcess.getErrorStream()));
      StringBuilder localStringBuilder;
      for (paramVarArgs = "";; paramVarArgs = localStringBuilder.toString())
      {
        Object localObject3 = ((BufferedReader)localObject2).readLine();
        if (localObject3 == null) {
          for (;;)
          {
            localObject2 = ((BufferedReader)localObject1).readLine();
            if (localObject2 == null)
            {
              try
              {
                localProcess.waitFor();
              }
              catch (InterruptedException localInterruptedException)
              {
                localInterruptedException.printStackTrace();
              }
              localDataOutputStream.close();
              return paramVarArgs;
            }
            localObject3 = new StringBuilder();
            ((StringBuilder)localObject3).append(paramVarArgs);
            ((StringBuilder)localObject3).append((String)localObject2);
            ((StringBuilder)localObject3).append("\n");
            paramVarArgs = ((StringBuilder)localObject3).toString();
          }
        }
        localStringBuilder = new StringBuilder();
        localStringBuilder.append(paramVarArgs);
        localStringBuilder.append((String)localObject3);
        localStringBuilder.append("\n");
      }
      return null;
    }
    catch (IOException paramVarArgs)
    {
      paramVarArgs.printStackTrace();
    }
  }
  
  public static final void startUp(@NotNull Activity paramActivity)
  {
    Intrinsics.checkParameterIsNotNull(paramActivity, "$receiver");
    Context localContext = (Context)paramActivity;
    SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(localContext);
    if ((localSharedPreferences.getBoolean("first_start", true)) && (checkSamsung(localContext))) {
      localSharedPreferences.edit().putBoolean("safe_mode", true).apply();
    }
    try
    {
      new AlertDialog.Builder((Context)paramActivity).setTitle((CharSequence)paramActivity.getResources().getString(2131624215)).setMessage((CharSequence)paramActivity.getResources().getString(2131624268)).setPositiveButton((CharSequence)paramActivity.getResources().getString(2131624220), null).show();
      localSharedPreferences.edit().putBoolean("first_start", false).apply();
      if (localSharedPreferences.getBoolean("hide_welcome_screen", false))
      {
        paramActivity.startActivity(new Intent(localContext, OptionsActivity.class));
        return;
      }
      paramActivity.startActivity(new Intent(localContext, MainActivity.class));
      return;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
  }
  
  public static final void updateBlacklistSwitches(@NotNull PreferenceFragment paramPreferenceFragment)
  {
    Intrinsics.checkParameterIsNotNull(paramPreferenceFragment, "$receiver");
    Object localObject1 = paramPreferenceFragment.getActivity();
    Intrinsics.checkExpressionValueIsNotNull(localObject1, "activity");
    Object localObject2 = Settings.Secure.getString(((Activity)localObject1).getContentResolver(), "icon_blacklist");
    localObject1 = localObject2;
    if (localObject2 == null) {
      localObject1 = "";
    }
    localObject2 = (CharSequence)localObject1;
    localObject1 = new TreeSet((Collection)StringsKt.split$default((CharSequence)localObject2, new String[] { "," }, false, 0, 6, null));
    Object localObject3 = paramPreferenceFragment.getPreferenceScreen();
    Intrinsics.checkExpressionValueIsNotNull(localObject3, "preferenceScreen");
    localObject3 = ((PreferenceScreen)localObject3).getRootAdapter();
    Intrinsics.checkExpressionValueIsNotNull(localObject3, "preferenceScreen.rootAdapter");
    int k = ((ListAdapter)localObject3).getCount();
    int i = 0;
    while (i < k)
    {
      localObject3 = paramPreferenceFragment.getPreferenceScreen();
      Intrinsics.checkExpressionValueIsNotNull(localObject3, "preferenceScreen");
      localObject3 = ((PreferenceScreen)localObject3).getRootAdapter().getItem(i);
      if ((localObject3 instanceof SwitchPreference))
      {
        localObject3 = (SwitchPreference)localObject3;
        int j = 1;
        ((SwitchPreference)localObject3).setChecked(true);
        if (((CharSequence)localObject2).length() != 0) {
          j = 0;
        }
        if (j == 0)
        {
          Object localObject4 = ((SwitchPreference)localObject3).getKey();
          if (localObject4 != null)
          {
            Object localObject5 = (Iterable)new TreeSet((Collection)StringsKt.split$default((CharSequence)localObject4, new String[] { "," }, false, 0, 6, null));
            localObject4 = (Collection)new ArrayList();
            localObject5 = ((Iterable)localObject5).iterator();
            while (((Iterator)localObject5).hasNext())
            {
              Object localObject6 = ((Iterator)localObject5).next();
              if (((TreeSet)localObject1).contains((String)localObject6)) {
                ((Collection)localObject4).add(localObject6);
              }
            }
            localObject4 = ((Iterable)localObject4).iterator();
            while (((Iterator)localObject4).hasNext())
            {
              localObject5 = (String)((Iterator)localObject4).next();
              ((SwitchPreference)localObject3).setChecked(false);
            }
          }
        }
      }
      i += 1;
    }
  }
  
  public static final boolean writeGlobal(@NotNull Context paramContext, @Nullable String paramString, @Nullable Object paramObject)
  {
    Intrinsics.checkParameterIsNotNull(paramContext, "$receiver");
    if (paramString == null) {
      return false;
    }
    try
    {
      localContentResolver = paramContext.getContentResolver();
      if (paramObject == null) {
        break label156;
      }
      localObject1 = paramObject.toString();
    }
    catch (Exception localException)
    {
      for (;;)
      {
        ContentResolver localContentResolver;
        Object localObject1;
        boolean bool;
        continue;
        Object localObject2 = null;
      }
    }
    bool = Settings.Global.putString(localContentResolver, paramString, (String)localObject1);
    return bool;
    if (paramObject != null)
    {
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("settings put global ");
      ((StringBuilder)localObject1).append(paramString);
      ((StringBuilder)localObject1).append(' ');
      ((StringBuilder)localObject1).append(paramObject);
      paramString = ((StringBuilder)localObject1).toString();
    }
    else
    {
      paramObject = new StringBuilder();
      paramObject.append("settings delete global ");
      paramObject.append(paramString);
      paramString = paramObject.toString();
    }
    if (SuUtils.INSTANCE.testSudo())
    {
      SuUtils.sudo(new String[] { paramString });
      return true;
    }
    launchErrorActivity(paramContext, paramString);
    return false;
  }
  
  public static final boolean writeSecure(@NotNull Context paramContext, @Nullable String paramString, @Nullable Object paramObject)
  {
    Intrinsics.checkParameterIsNotNull(paramContext, "$receiver");
    if (paramString == null) {
      return false;
    }
    try
    {
      localContentResolver = paramContext.getContentResolver();
      if (paramObject == null) {
        break label156;
      }
      localObject1 = paramObject.toString();
    }
    catch (Exception localException)
    {
      for (;;)
      {
        ContentResolver localContentResolver;
        Object localObject1;
        boolean bool;
        continue;
        Object localObject2 = null;
      }
    }
    bool = Settings.Secure.putString(localContentResolver, paramString, (String)localObject1);
    return bool;
    if (paramObject != null)
    {
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("settings put secure ");
      ((StringBuilder)localObject1).append(paramString);
      ((StringBuilder)localObject1).append(' ');
      ((StringBuilder)localObject1).append(paramObject);
      paramString = ((StringBuilder)localObject1).toString();
    }
    else
    {
      paramObject = new StringBuilder();
      paramObject.append("settings delete secure ");
      paramObject.append(paramString);
      paramString = paramObject.toString();
    }
    if (SuUtils.INSTANCE.testSudo())
    {
      SuUtils.sudo(new String[] { paramString });
      return true;
    }
    launchErrorActivity(paramContext, paramString);
    return false;
  }
  
  public static final boolean writeSystem(@NotNull Context paramContext, @Nullable String paramString, @Nullable Object paramObject)
  {
    Intrinsics.checkParameterIsNotNull(paramContext, "$receiver");
    if (paramString == null) {
      return false;
    }
    try
    {
      localContentResolver = paramContext.getContentResolver();
      if (paramObject == null) {
        break label156;
      }
      localObject1 = paramObject.toString();
    }
    catch (Exception localException)
    {
      for (;;)
      {
        ContentResolver localContentResolver;
        Object localObject1;
        boolean bool;
        continue;
        Object localObject2 = null;
      }
    }
    bool = Settings.System.putString(localContentResolver, paramString, (String)localObject1);
    return bool;
    if (paramObject != null)
    {
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("settings put system ");
      ((StringBuilder)localObject1).append(paramString);
      ((StringBuilder)localObject1).append(' ');
      ((StringBuilder)localObject1).append(paramObject);
      paramString = ((StringBuilder)localObject1).toString();
    }
    else
    {
      paramObject = new StringBuilder();
      paramObject.append("settings delete system ");
      paramObject.append(paramString);
      paramString = paramObject.toString();
    }
    if (SuUtils.INSTANCE.testSudo())
    {
      SuUtils.sudo(new String[] { paramString });
      return true;
    }
    launchErrorActivity(paramContext, paramString);
    return false;
  }
}
