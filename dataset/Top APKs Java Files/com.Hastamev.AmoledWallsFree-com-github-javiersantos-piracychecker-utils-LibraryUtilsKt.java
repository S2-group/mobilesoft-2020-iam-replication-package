package com.github.javiersantos.piracychecker.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.opengl.GLES20;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.util.Base64;
import com.github.javiersantos.piracychecker.R.string;
import com.github.javiersantos.piracychecker.enums.AppType;
import com.github.javiersantos.piracychecker.enums.InstallerID;
import com.github.javiersantos.piracychecker.enums.PirateApp;
import java.io.File;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000F\n\000\n\002\020\016\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\004\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020\013\n\002\b\003\n\002\030\002\n\002\b\013\n\002\030\002\n\002\b\003\n\002\020 \n\002\030\002\n\002\b\003\032\034\020\t\032\b\022\004\022\0020\0130\n2\f\020\f\032\b\022\004\022\0020\0130\nH\002\032\020\020\r\032\0020\0162\006\020\017\032\0020\016H\000\032\b\020\020\032\0020\016H\002\032\036\020\021\032\004\030\0010\022*\0020\0022\006\020\023\032\0020\0012\006\020\024\032\0020\001H\000\032<\020\025\032\004\030\0010\013*\0020\0022\006\020\026\032\0020\0162\006\020\027\032\0020\0162\006\020\030\032\0020\0162\006\020\031\032\0020\0162\f\020\f\032\b\022\004\022\0020\0130\nH\001\032\f\020\032\032\0020\016*\0020\002H\002\032\f\020\033\032\0020\016*\0020\002H\000\032\026\020\034\032\0020\016*\0020\0022\b\020\035\032\004\030\0010\036H\002\032\024\020\020\032\0020\016*\0020\0022\006\020\037\032\0020\001H\002\032\032\020 \032\0020\016*\0020\0022\f\020!\032\b\022\004\022\0020#0\"H\000\032\026\020$\032\0020\016*\0020\0022\b\020%\032\004\030\0010\001H\000\"\025\020\000\032\0020\001*\0020\0028F¢\006\006\032\004\b\003\020\004\"\025\020\000\032\0020\001*\0020\0058F¢\006\006\032\004\b\003\020\006\"\025\020\007\032\0020\001*\0020\0028G¢\006\006\032\004\b\b\020\004¨\006&"}, d2={"apkSignature", "", "Landroid/content/Context;", "getApkSignature", "(Landroid/content/Context;)Ljava/lang/String;", "Landroid/support/v4/app/Fragment;", "(Landroid/support/v4/app/Fragment;)Ljava/lang/String;", "currentSignature", "getCurrentSignature", "getApps", "Ljava/util/ArrayList;", "Lcom/github/javiersantos/piracychecker/enums/PirateApp;", "extraApps", "isInEmulator", "", "deepCheck", "shouldAskPermission", "buildUnlicensedDialog", "Landroid/support/v7/app/AlertDialog;", "title", "content", "getPirateApp", "lpf", "stores", "folders", "apks", "hasPermissions", "isDebug", "isIntentAvailable", "intent", "Landroid/content/Intent;", "permission", "verifyInstallerId", "installerID", "", "Lcom/github/javiersantos/piracychecker/enums/InstallerID;", "verifySigningCertificate", "appSignature", "library_release"}, k=2, mv={1, 1, 10})
public final class LibraryUtilsKt
{
  @Nullable
  public static final AlertDialog buildUnlicensedDialog(@NotNull Context paramContext, @NotNull String paramString1, @NotNull String paramString2)
  {
    Intrinsics.checkParameterIsNotNull(paramContext, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramString1, "title");
    Intrinsics.checkParameterIsNotNull(paramString2, "content");
    Context localContext;
    if (!(paramContext instanceof Activity)) {
      localContext = null;
    } else {
      localContext = paramContext;
    }
    if ((Activity)localContext != null)
    {
      if (((Activity)paramContext).isFinishing()) {
        return null;
      }
      return new AlertDialog.Builder(paramContext).setCancelable(false).setTitle((CharSequence)paramString1).setMessage((CharSequence)paramString2).setPositiveButton((CharSequence)paramContext.getString(R.string.app_unlicensed_close), (DialogInterface.OnClickListener)new LibraryUtilsKt.buildUnlicensedDialog..inlined.let.lambda.1(paramContext, paramString1, paramString2)).create();
    }
    return null;
  }
  
  @NotNull
  public static final String getApkSignature(@NotNull Context paramContext)
  {
    Intrinsics.checkParameterIsNotNull(paramContext, "$receiver");
    return getCurrentSignature(paramContext);
  }
  
  @NotNull
  public static final String getApkSignature(@NotNull Fragment paramFragment)
  {
    Intrinsics.checkParameterIsNotNull(paramFragment, "$receiver");
    paramFragment = paramFragment.getContext();
    if (paramFragment == null) {
      Intrinsics.throwNpe();
    }
    Intrinsics.checkExpressionValueIsNotNull(paramFragment, "context!!");
    return getApkSignature(paramFragment);
  }
  
  private static final ArrayList getApps(ArrayList paramArrayList)
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject1 = AppType.PIRATE;
    localArrayList.add(new PirateApp("LuckyPatcher", new String[] { "c", "o", "m", ".", "c", "h", "e", "l", "p", "u", "s", ".", "l", "a", "c", "k", "y", "p", "a", "t", "c", "h" }, (AppType)localObject1));
    localObject1 = AppType.PIRATE;
    localArrayList.add(new PirateApp("LuckyPatcher", new String[] { "c", "o", "m", ".", "d", "i", "m", "o", "n", "v", "i", "d", "e", "o", ".", "l", "u", "c", "k", "y", "p", "a", "t", "c", "h", "e", "r" }, (AppType)localObject1));
    localObject1 = AppType.PIRATE;
    localArrayList.add(new PirateApp("LuckyPatcher", new String[] { "c", "o", "m", ".", "f", "o", "r", "p", "d", "a", ".", "l", "p" }, (AppType)localObject1));
    localObject1 = AppType.PIRATE;
    localArrayList.add(new PirateApp("LuckyPatcher", new String[] { "c", "o", "m", ".", "a", "n", "d", "r", "o", "i", "d", ".", "v", "e", "n", "d", "i", "n", "g", ".", "b", "i", "l", "l", "i", "n", "g", ".", "I", "n", "A", "p", "p", "B", "i", "l", "l", "i", "n", "g", "S", "e", "r", "v", "i", "c", "e" }, (AppType)localObject1));
    localObject1 = AppType.PIRATE;
    localArrayList.add(new PirateApp("LuckyPatcher", new String[] { "c", "o", "m", ".", "a", "n", "d", "r", "o", "i", "d", ".", "v", "e", "n", "d", "i", "n", "g", ".", "b", "i", "l", "l", "i", "n", "g", ".", "I", "n", "A", "p", "p", "B", "i", "l", "l", "i", "n", "g", "S", "o", "r", "v", "i", "c", "e" }, (AppType)localObject1));
    localObject1 = AppType.PIRATE;
    localArrayList.add(new PirateApp("LuckyPatcher", new String[] { "c", "o", "m", ".", "a", "n", "d", "r", "o", "i", "d", ".", "v", "e", "n", "d", "i", "n", "c" }, (AppType)localObject1));
    localObject1 = AppType.PIRATE;
    localArrayList.add(new PirateApp("UretPatcher", new String[] { "u", "r", "e", "t", ".", "j", "a", "s", "i", "2", "1", "6", "9", ".", "p", "a", "t", "c", "h", "e", "r" }, (AppType)localObject1));
    localObject1 = AppType.PIRATE;
    localArrayList.add(new PirateApp("UretPatcher", new String[] { "z", "o", "n", "e", ".", "j", "a", "s", "i", "2", "1", "6", "9", ".", "u", "r", "e", "t", "p", "a", "t", "c", "h", "e", "r" }, (AppType)localObject1));
    localObject1 = AppType.PIRATE;
    localArrayList.add(new PirateApp("ActionLauncherPatcher", new String[] { "p", ".", "j", "a", "s", "i", "2", "1", "6", "9", ".", "a", "l", "3" }, (AppType)localObject1));
    localObject1 = AppType.PIRATE;
    localArrayList.add(new PirateApp("Freedom", new String[] { "c", "c", ".", "m", "a", "d", "k", "i", "t", "e", ".", "f", "r", "e", "e", "d", "o", "m" }, (AppType)localObject1));
    localObject1 = AppType.PIRATE;
    localArrayList.add(new PirateApp("Freedom", new String[] { "c", "c", ".", "c", "z", ".", "m", "a", "d", "k", "i", "t", "e", ".", "f", "r", "e", "e", "d", "o", "m" }, (AppType)localObject1));
    localObject1 = AppType.PIRATE;
    localArrayList.add(new PirateApp("CreeHack", new String[] { "o", "r", "g", ".", "c", "r", "e", "e", "p", "l", "a", "y", "s", ".", "h", "a", "c", "k" }, (AppType)localObject1));
    localObject1 = AppType.PIRATE;
    localArrayList.add(new PirateApp("Game Hacker", new String[] { "o", "r", "g", ".", "s", "b", "t", "o", "o", "l", "s", ".", "g", "a", "m", "e", "h", "a", "c", "k" }, (AppType)localObject1));
    localObject1 = AppType.PIRATE;
    localArrayList.add(new PirateApp("Game Killer Cheats", new String[] { "c", "o", "m", ".", "z", "u", "n", "e", ".", "g", "a", "m", "e", "k", "i", "l", "l", "e", "r" }, (AppType)localObject1));
    localObject1 = AppType.PIRATE;
    localArrayList.add(new PirateApp("AGK - App Killer", new String[] { "c", "o", "m", ".", "a", "a", "g", ".", "k", "i", "l", "l", "e", "r" }, (AppType)localObject1));
    localObject1 = AppType.PIRATE;
    localArrayList.add(new PirateApp("Game Killer", new String[] { "c", "o", "m", ".", "k", "i", "l", "l", "e", "r", "a", "p", "p", ".", "g", "a", "m", "e", "k", "i", "l", "l", "e", "r" }, (AppType)localObject1));
    localObject1 = AppType.PIRATE;
    localArrayList.add(new PirateApp("Game Killer", new String[] { "c", "n", ".", "l", "m", ".", "s", "q" }, (AppType)localObject1));
    localObject1 = AppType.PIRATE;
    localArrayList.add(new PirateApp("Game CheatIng Hacker", new String[] { "n", "e", "t", ".", "s", "c", "h", "w", "a", "r", "z", "i", "s", ".", "g", "a", "m", "e", "_", "c", "i", "h" }, (AppType)localObject1));
    localObject1 = AppType.PIRATE;
    localArrayList.add(new PirateApp("Game Hacker", new String[] { "c", "o", "m", ".", "b", "a", "s", "e", "a", "p", "p", "f", "u", "l", "l", ".", "f", "w", "d" }, (AppType)localObject1));
    localObject1 = AppType.PIRATE;
    localArrayList.add(new PirateApp("Content Guard Disabler", new String[] { "c", "o", "m", ".", "g", "i", "t", "h", "u", "b", ".", "o", "n", "e", "m", "i", "n", "u", "s", "o", "n", "e", ".", "d", "i", "s", "a", "b", "l", "e", "c", "o", "n", "t", "e", "n", "t", "g", "u", "a", "r", "d" }, (AppType)localObject1));
    localObject1 = AppType.PIRATE;
    localArrayList.add(new PirateApp("Content Guard Disabler", new String[] { "c", "o", "m", ".", "o", "n", "e", "m", "i", "n", "u", "s", "o", "n", "e", ".", "d", "i", "s", "a", "b", "l", "e", "c", "o", "n", "t", "e", "n", "t", "g", "u", "a", "r", "d" }, (AppType)localObject1));
    localObject1 = AppType.STORE;
    localArrayList.add(new PirateApp("Aptoide", new String[] { "c", "m", ".", "a", "p", "t", "o", "i", "d", "e", ".", "p", "t" }, (AppType)localObject1));
    localObject1 = AppType.STORE;
    localArrayList.add(new PirateApp("BlackMart", new String[] { "o", "r", "g", ".", "b", "l", "a", "c", "k", "m", "a", "r", "t", ".", "m", "a", "r", "k", "e", "t" }, (AppType)localObject1));
    localObject1 = AppType.STORE;
    localArrayList.add(new PirateApp("BlackMart", new String[] { "c", "o", "m", ".", "b", "l", "a", "c", "k", "m", "a", "r", "t", "a", "l", "p", "h", "a" }, (AppType)localObject1));
    localObject1 = AppType.STORE;
    localArrayList.add(new PirateApp("Mobogenie", new String[] { "c", "o", "m", ".", "m", "o", "b", "o", "g", "e", "n", "i", "e" }, (AppType)localObject1));
    localObject1 = AppType.STORE;
    localArrayList.add(new PirateApp("1Mobile", new String[] { "m", "e", ".", "o", "n", "e", "m", "o", "b", "i", "l", "e", ".", "a", "n", "d", "r", "o", "i", "d" }, (AppType)localObject1));
    localObject1 = AppType.STORE;
    localArrayList.add(new PirateApp("GetApk", new String[] { "c", "o", "m", ".", "r", "e", "p", "o", "d", "r", "o", "i", "d", ".", "a", "p", "p" }, (AppType)localObject1));
    localObject1 = AppType.STORE;
    localArrayList.add(new PirateApp("GetJar", new String[] { "c", "o", "m", ".", "g", "e", "t", "j", "a", "r", ".", "r", "e", "w", "a", "r", "d", "s" }, (AppType)localObject1));
    localObject1 = AppType.STORE;
    localArrayList.add(new PirateApp("SlideMe", new String[] { "c", "o", "m", ".", "s", "l", "i", "d", "e", "m", "e", ".", "s", "a", "m", ".", "m", "a", "n", "a", "g", "e", "r" }, (AppType)localObject1));
    localObject1 = AppType.STORE;
    localArrayList.add(new PirateApp("ACMarket", new String[] { "n", "e", "t", ".", "a", "p", "p", "c", "a", "k", "e" }, (AppType)localObject1));
    localObject1 = AppType.STORE;
    localArrayList.add(new PirateApp("ACMarket", new String[] { "a", "c", ".", "m", "a", "r", "k", "e", "t", ".", "s", "t", "o", "r", "e" }, (AppType)localObject1));
    localObject1 = AppType.STORE;
    localArrayList.add(new PirateApp("AppCake", new String[] { "c", "o", "m", ".", "a", "p", "p", "c", "a", "k", "e" }, (AppType)localObject1));
    localObject1 = AppType.STORE;
    localArrayList.add(new PirateApp("Z Market", new String[] { "c", "o", "m", ".", "z", "m", "a", "p", "p" }, (AppType)localObject1));
    localObject1 = AppType.STORE;
    localArrayList.add(new PirateApp("Modded Play Store", new String[] { "c", "o", "m", ".", "d", "v", ".", "m", "a", "r", "k", "e", "t", "m", "o", "d", ".", "i", "n", "s", "t", "a", "l", "l", "e", "r" }, (AppType)localObject1));
    localObject1 = AppType.STORE;
    localArrayList.add(new PirateApp("Mobilism Market", new String[] { "o", "r", "g", ".", "m", "o", "b", "i", "l", "i", "s", "m", ".", "a", "n", "d", "r", "o", "i", "d" }, (AppType)localObject1));
    localObject1 = AppType.STORE;
    localArrayList.add(new PirateApp("All-in-one Downloader", new String[] { "c", "o", "m", ".", "a", "l", "l", "i", "n", "o", "n", "e", ".", "f", "r", "e", "e" }, (AppType)localObject1));
    localArrayList.addAll((Collection)paramArrayList);
    localObject1 = (Iterable)localArrayList;
    paramArrayList = new HashSet();
    localArrayList = new ArrayList();
    localObject1 = ((Iterable)localObject1).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      Object localObject2 = ((Iterator)localObject1).next();
      if (paramArrayList.add(((PirateApp)localObject2).getPackageName())) {
        localArrayList.add(localObject2);
      }
    }
    return new ArrayList((Collection)localArrayList);
  }
  
  @SuppressLint({"PackageManagerGetSignatures"})
  @NotNull
  public static final String getCurrentSignature(@NotNull Context paramContext)
  {
    Intrinsics.checkParameterIsNotNull(paramContext, "$receiver");
    for (;;)
    {
      try
      {
        Signature[] arrayOfSignature = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 64).signatures;
        int j = arrayOfSignature.length;
        paramContext = "";
        i = 0;
        localObject = paramContext;
        if (i < j) {
          localObject = arrayOfSignature[i];
        }
      }
      catch (Exception paramContext)
      {
        int i;
        Object localObject;
        MessageDigest localMessageDigest;
        continue;
      }
      try
      {
        localMessageDigest = MessageDigest.getInstance("SHA");
        localMessageDigest.update(((Signature)localObject).toByteArray());
        localObject = Base64.encodeToString(localMessageDigest.digest(), 0);
        Intrinsics.checkExpressionValueIsNotNull(localObject, "Base64.encodeToString(me…digest(), Base64.DEFAULT)");
        i += 1;
        paramContext = (Context)localObject;
      }
      catch (Exception localException)
      {
        Context localContext = paramContext;
      }
    }
    localObject = "";
    if (localObject == null) {
      throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
    }
    return StringsKt.trim((CharSequence)localObject).toString();
  }
  
  @SuppressLint({"SdCardPath"})
  @Nullable
  public static final PirateApp getPirateApp(@NotNull Context paramContext, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, @NotNull ArrayList paramArrayList)
  {
    Intrinsics.checkParameterIsNotNull(paramContext, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramArrayList, "extraApps");
    if ((!paramBoolean1) && (!paramBoolean2) && (paramArrayList.isEmpty())) {
      return null;
    }
    Object localObject2 = getApps(paramArrayList);
    try
    {
      localObject3 = paramContext.getPackageManager();
      if (localObject3 == null) {
        break label1173;
      }
      paramArrayList = ((PackageManager)localObject3).getInstalledApplications(128);
    }
    catch (Exception paramArrayList)
    {
      for (;;)
      {
        Object localObject3;
        Object localObject4;
        boolean bool1;
        Object localObject1;
        Object localObject5;
        Object localObject6;
        int m;
        int i1;
        int i2;
        continue;
        paramArrayList = null;
        continue;
        int i = 0;
        continue;
        int j = 0;
        continue;
        int k = 0;
        if ((i == 0) && (j == 0)) {
          if (k != 0)
          {
            continue;
            bool1 = false;
            continue;
            i = 0;
            continue;
            i = 1;
            continue;
            j = 0;
            continue;
            j = 1;
          }
        }
      }
    }
    localObject4 = ((ArrayList)localObject2).iterator();
    bool1 = false;
    while (((Iterator)localObject4).hasNext())
    {
      localObject1 = (PirateApp)((Iterator)localObject4).next();
      if ((!paramBoolean1) || (((PirateApp)localObject1).getType() != AppType.PIRATE)) {
        break label1179;
      }
      i = 1;
      if ((!paramBoolean2) || (((PirateApp)localObject1).getType() != AppType.STORE)) {
        break label1185;
      }
      j = 1;
      if (((PirateApp)localObject1).getType() != AppType.OTHER) {
        break label1191;
      }
      k = 1;
      break label1194;
      if (paramArrayList == null) {
        break label1212;
      }
      localObject5 = (Iterable)paramArrayList;
      if (((localObject5 instanceof Collection)) && (((Collection)localObject5).isEmpty())) {
        break label1212;
      }
      localObject5 = ((Iterable)localObject5).iterator();
      do
      {
        if (!((Iterator)localObject5).hasNext()) {
          break;
        }
        localObject6 = ((ApplicationInfo)((Iterator)localObject5).next()).packageName;
        Intrinsics.checkExpressionValueIsNotNull(localObject6, "it.packageName");
      } while (!StringsKt.contains$default((CharSequence)localObject6, (CharSequence)((PirateApp)localObject1).getPackageName(), false, 2, null));
      bool1 = true;
      boolean bool2 = bool1;
      if (!bool1) {
        bool2 = isIntentAvailable(paramContext, ((PackageManager)localObject3).getLaunchIntentForPackage(((PirateApp)localObject1).getPackageName()));
      }
      bool1 = bool2;
      if (bool1)
      {
        paramArrayList = (ArrayList)localObject1;
        break label307;
      }
    }
    paramArrayList = null;
    label307:
    if (!paramBoolean3)
    {
      localObject1 = paramArrayList;
      if (!paramBoolean4) {}
    }
    else
    {
      localObject1 = paramArrayList;
      if (paramArrayList == null)
      {
        localObject1 = paramArrayList;
        if (hasPermissions(paramContext))
        {
          paramContext = ((ArrayList)localObject2).iterator();
          k = 0;
          j = k;
          m = j;
          label1100:
          label1123:
          do
          {
            localObject1 = paramArrayList;
            if (!paramContext.hasNext()) {
              break;
            }
            localObject1 = (PirateApp)paramContext.next();
            localObject2 = ((PirateApp)localObject1).getPackageName();
            i = k;
            if (paramBoolean4)
            {
              i1 = k;
              i2 = j;
            }
            try
            {
              localObject3 = new StringBuilder("/data/app/");
              i1 = k;
              i2 = j;
              ((StringBuilder)localObject3).append((String)localObject2);
              i1 = k;
              i2 = j;
              ((StringBuilder)localObject3).append("-1/base.apk");
              i1 = k;
              i2 = j;
              localObject3 = new File(((StringBuilder)localObject3).toString());
              i1 = k;
              i2 = j;
              localObject4 = new StringBuilder("/data/app/");
              i1 = k;
              i2 = j;
              ((StringBuilder)localObject4).append((String)localObject2);
              i1 = k;
              i2 = j;
              ((StringBuilder)localObject4).append("-2/base.apk");
              i1 = k;
              i2 = j;
              localObject4 = new File(((StringBuilder)localObject4).toString());
              i1 = k;
              i2 = j;
              localObject5 = new StringBuilder("/data/app/");
              i1 = k;
              i2 = j;
              ((StringBuilder)localObject5).append((String)localObject2);
              i1 = k;
              i2 = j;
              ((StringBuilder)localObject5).append(".apk");
              i1 = k;
              i2 = j;
              localObject5 = new File(((StringBuilder)localObject5).toString());
              i1 = k;
              i2 = j;
              localObject6 = new StringBuilder("/data/data/");
              i1 = k;
              i2 = j;
              ((StringBuilder)localObject6).append((String)localObject2);
              i1 = k;
              i2 = j;
              ((StringBuilder)localObject6).append(".apk");
              i1 = k;
              i2 = j;
              localObject6 = new File(((StringBuilder)localObject6).toString());
              i1 = k;
              i2 = j;
              if (((File)localObject3).exists()) {
                break label1224;
              }
              i1 = k;
              i2 = j;
              if (((File)localObject4).exists()) {
                break label1224;
              }
              i1 = k;
              i2 = j;
              if (((File)localObject5).exists()) {
                break label1224;
              }
              i1 = k;
              i2 = j;
              if (!((File)localObject6).exists()) {
                break label1218;
              }
            }
            catch (Exception localException1)
            {
              for (;;)
              {
                int n = i1;
                k = i2;
              }
            }
            n = i;
            k = j;
            if (paramBoolean3)
            {
              i1 = i;
              i2 = j;
              localObject3 = new StringBuilder("/data/data/");
              i1 = i;
              i2 = j;
              ((StringBuilder)localObject3).append((String)localObject2);
              i1 = i;
              i2 = j;
              localObject3 = new File(((StringBuilder)localObject3).toString());
              i1 = i;
              i2 = j;
              localObject4 = new StringBuilder();
              i1 = i;
              i2 = j;
              ((StringBuilder)localObject4).append(Environment.getExternalStorageDirectory());
              i1 = i;
              i2 = j;
              ((StringBuilder)localObject4).append("/Android/data/");
              i1 = i;
              i2 = j;
              ((StringBuilder)localObject4).append((String)localObject2);
              i1 = i;
              i2 = j;
              localObject4 = new File(((StringBuilder)localObject4).toString());
              i1 = i;
              i2 = j;
              if (((File)localObject3).exists()) {
                break label1236;
              }
              i1 = i;
              i2 = j;
              if (!((File)localObject4).exists()) {
                break label1230;
              }
              break label1236;
              i1 = i;
              i2 = j;
              localObject3 = new File("/data/app/");
              n = i;
              k = j;
              i1 = i;
              i2 = j;
              if (((File)localObject3).exists())
              {
                i1 = i;
                i2 = j;
                localObject3 = ((File)localObject3).listFiles();
                i1 = i;
                i2 = j;
                n = localObject3.length;
                k = m;
                m = 0;
              }
            }
            for (;;)
            {
              if (m < n) {
                localObject4 = localObject3[m];
              }
              try
              {
                Intrinsics.checkExpressionValueIsNotNull(localObject4, "f");
                localObject4 = ((File)localObject4).getName();
                Intrinsics.checkExpressionValueIsNotNull(localObject4, "f.name");
              }
              catch (Exception localException2)
              {
                for (;;) {}
              }
              try
              {
                paramBoolean1 = StringsKt.startsWith$default((String)localObject4, (String)localObject2, false, 2, null);
                if (paramBoolean1) {
                  k = 1;
                }
                m += 1;
              }
              catch (Exception localException3)
              {
                break label1100;
              }
            }
            m = k;
            k = i;
            i = j;
            break label1123;
            i = k;
            k = n;
            if ((m != 0) || (k != 0)) {
              break;
            }
            j = i;
          } while (i == 0);
        }
      }
    }
    return localObject1;
  }
  
  private static final boolean hasPermissions(@NotNull Context paramContext)
  {
    boolean bool1 = false;
    try
    {
      if ((Build.VERSION.SDK_INT >= 16) && (shouldAskPermission(paramContext, "android.permission.READ_EXTERNAL_STORAGE")))
      {
        if (paramContext == null) {
          throw new TypeCastException("null cannot be cast to non-null type android.app.Activity");
        }
        boolean bool2 = ActivityCompat.shouldShowRequestPermissionRationale((Activity)paramContext, "android.permission.READ_EXTERNAL_STORAGE");
        if (bool2) {}
      }
      else
      {
        bool1 = true;
      }
      return bool1;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  public static final boolean isDebug(@NotNull Context paramContext)
  {
    Intrinsics.checkParameterIsNotNull(paramContext, "$receiver");
    return (paramContext.getApplicationInfo().flags & 0x2) != 0;
  }
  
  public static final boolean isInEmulator(boolean paramBoolean)
  {
    Object localObject = Build.PRODUCT;
    Intrinsics.checkExpressionValueIsNotNull(localObject, "Build.PRODUCT");
    if (!StringsKt.contains$default((CharSequence)localObject, (CharSequence)"sdk", false, 2, null))
    {
      localObject = Build.PRODUCT;
      Intrinsics.checkExpressionValueIsNotNull(localObject, "Build.PRODUCT");
      if (!StringsKt.contains$default((CharSequence)localObject, (CharSequence)"Andy", false, 2, null))
      {
        localObject = Build.PRODUCT;
        Intrinsics.checkExpressionValueIsNotNull(localObject, "Build.PRODUCT");
        if (!StringsKt.contains$default((CharSequence)localObject, (CharSequence)"ttVM_Hdragon", false, 2, null))
        {
          localObject = Build.PRODUCT;
          Intrinsics.checkExpressionValueIsNotNull(localObject, "Build.PRODUCT");
          if (!StringsKt.contains$default((CharSequence)localObject, (CharSequence)"google_sdk", false, 2, null))
          {
            localObject = Build.PRODUCT;
            Intrinsics.checkExpressionValueIsNotNull(localObject, "Build.PRODUCT");
            if (!StringsKt.contains$default((CharSequence)localObject, (CharSequence)"Droid4X", false, 2, null))
            {
              localObject = Build.PRODUCT;
              Intrinsics.checkExpressionValueIsNotNull(localObject, "Build.PRODUCT");
              if (!StringsKt.contains$default((CharSequence)localObject, (CharSequence)"nox", false, 2, null))
              {
                localObject = Build.PRODUCT;
                Intrinsics.checkExpressionValueIsNotNull(localObject, "Build.PRODUCT");
                if (!StringsKt.contains$default((CharSequence)localObject, (CharSequence)"sdk_x86", false, 2, null))
                {
                  localObject = Build.PRODUCT;
                  Intrinsics.checkExpressionValueIsNotNull(localObject, "Build.PRODUCT");
                  if (!StringsKt.contains$default((CharSequence)localObject, (CharSequence)"sdk_google", false, 2, null))
                  {
                    localObject = Build.PRODUCT;
                    Intrinsics.checkExpressionValueIsNotNull(localObject, "Build.PRODUCT");
                    if (!StringsKt.contains$default((CharSequence)localObject, (CharSequence)"vbox86p", false, 2, null))
                    {
                      j = 0;
                      break label307;
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
    j = 1;
    label307:
    if ((!Intrinsics.areEqual(Build.MANUFACTURER, "unknown")) && (!Intrinsics.areEqual(Build.MANUFACTURER, "Genymotion")))
    {
      localObject = Build.MANUFACTURER;
      Intrinsics.checkExpressionValueIsNotNull(localObject, "Build.MANUFACTURER");
      if (!StringsKt.contains$default((CharSequence)localObject, (CharSequence)"Andy", false, 2, null))
      {
        localObject = Build.MANUFACTURER;
        Intrinsics.checkExpressionValueIsNotNull(localObject, "Build.MANUFACTURER");
        if (!StringsKt.contains$default((CharSequence)localObject, (CharSequence)"MIT", false, 2, null))
        {
          localObject = Build.MANUFACTURER;
          Intrinsics.checkExpressionValueIsNotNull(localObject, "Build.MANUFACTURER");
          if (!StringsKt.contains$default((CharSequence)localObject, (CharSequence)"nox", false, 2, null))
          {
            localObject = Build.MANUFACTURER;
            Intrinsics.checkExpressionValueIsNotNull(localObject, "Build.MANUFACTURER");
            i = j;
            if (!StringsKt.contains$default((CharSequence)localObject, (CharSequence)"TiantianVM", false, 2, null)) {
              break label469;
            }
          }
        }
      }
    }
    i = j + 1;
    label469:
    if ((!Intrinsics.areEqual(Build.BRAND, "generic")) && (!Intrinsics.areEqual(Build.BRAND, "generic_x86")) && (!Intrinsics.areEqual(Build.BRAND, "TTVM")))
    {
      localObject = Build.BRAND;
      Intrinsics.checkExpressionValueIsNotNull(localObject, "Build.BRAND");
      j = i;
      if (!StringsKt.contains$default((CharSequence)localObject, (CharSequence)"Andy", false, 2, null)) {}
    }
    else
    {
      j = i + 1;
    }
    localObject = Build.DEVICE;
    Intrinsics.checkExpressionValueIsNotNull(localObject, "Build.DEVICE");
    if (!StringsKt.contains$default((CharSequence)localObject, (CharSequence)"generic", false, 2, null))
    {
      localObject = Build.DEVICE;
      Intrinsics.checkExpressionValueIsNotNull(localObject, "Build.DEVICE");
      if (!StringsKt.contains$default((CharSequence)localObject, (CharSequence)"generic_x86", false, 2, null))
      {
        localObject = Build.DEVICE;
        Intrinsics.checkExpressionValueIsNotNull(localObject, "Build.DEVICE");
        if (!StringsKt.contains$default((CharSequence)localObject, (CharSequence)"Andy", false, 2, null))
        {
          localObject = Build.DEVICE;
          Intrinsics.checkExpressionValueIsNotNull(localObject, "Build.DEVICE");
          if (!StringsKt.contains$default((CharSequence)localObject, (CharSequence)"ttVM_Hdragon", false, 2, null))
          {
            localObject = Build.DEVICE;
            Intrinsics.checkExpressionValueIsNotNull(localObject, "Build.DEVICE");
            if (!StringsKt.contains$default((CharSequence)localObject, (CharSequence)"Droid4X", false, 2, null))
            {
              localObject = Build.DEVICE;
              Intrinsics.checkExpressionValueIsNotNull(localObject, "Build.DEVICE");
              if (!StringsKt.contains$default((CharSequence)localObject, (CharSequence)"nox", false, 2, null))
              {
                localObject = Build.DEVICE;
                Intrinsics.checkExpressionValueIsNotNull(localObject, "Build.DEVICE");
                if (!StringsKt.contains$default((CharSequence)localObject, (CharSequence)"generic_x86_64", false, 2, null))
                {
                  localObject = Build.DEVICE;
                  Intrinsics.checkExpressionValueIsNotNull(localObject, "Build.DEVICE");
                  i = j;
                  if (!StringsKt.contains$default((CharSequence)localObject, (CharSequence)"vbox86p", false, 2, null)) {
                    break label814;
                  }
                }
              }
            }
          }
        }
      }
    }
    i = j + 1;
    label814:
    if ((!Intrinsics.areEqual(Build.MODEL, "sdk")) && (!Intrinsics.areEqual(Build.MODEL, "google_sdk")))
    {
      localObject = Build.MODEL;
      Intrinsics.checkExpressionValueIsNotNull(localObject, "Build.MODEL");
      if (!StringsKt.contains$default((CharSequence)localObject, (CharSequence)"Droid4X", false, 2, null))
      {
        localObject = Build.MODEL;
        Intrinsics.checkExpressionValueIsNotNull(localObject, "Build.MODEL");
        if (!StringsKt.contains$default((CharSequence)localObject, (CharSequence)"TiantianVM", false, 2, null))
        {
          localObject = Build.MODEL;
          Intrinsics.checkExpressionValueIsNotNull(localObject, "Build.MODEL");
          if ((!StringsKt.contains$default((CharSequence)localObject, (CharSequence)"Andy", false, 2, null)) && (!Intrinsics.areEqual(Build.MODEL, "Android SDK built for x86_64")))
          {
            j = i;
            if (!Intrinsics.areEqual(Build.MODEL, "Android SDK built for x86")) {
              break label967;
            }
          }
        }
      }
    }
    j = i + 1;
    label967:
    if ((!Intrinsics.areEqual(Build.HARDWARE, "goldfish")) && (!Intrinsics.areEqual(Build.HARDWARE, "vbox86")))
    {
      localObject = Build.HARDWARE;
      Intrinsics.checkExpressionValueIsNotNull(localObject, "Build.HARDWARE");
      if (!StringsKt.contains$default((CharSequence)localObject, (CharSequence)"nox", false, 2, null))
      {
        localObject = Build.HARDWARE;
        Intrinsics.checkExpressionValueIsNotNull(localObject, "Build.HARDWARE");
        k = j;
        if (!StringsKt.contains$default((CharSequence)localObject, (CharSequence)"ttVM_x86", false, 2, null)) {
          break label1063;
        }
      }
    }
    k = j + 1;
    label1063:
    localObject = Build.FINGERPRINT;
    Intrinsics.checkExpressionValueIsNotNull(localObject, "Build.FINGERPRINT");
    if (!StringsKt.contains$default((CharSequence)localObject, (CharSequence)"generic", false, 2, null))
    {
      localObject = Build.FINGERPRINT;
      Intrinsics.checkExpressionValueIsNotNull(localObject, "Build.FINGERPRINT");
      if (!StringsKt.contains$default((CharSequence)localObject, (CharSequence)"generic/sdk/generic", false, 2, null))
      {
        localObject = Build.FINGERPRINT;
        Intrinsics.checkExpressionValueIsNotNull(localObject, "Build.FINGERPRINT");
        if (!StringsKt.contains$default((CharSequence)localObject, (CharSequence)"generic_x86/sdk_x86/generic_x86", false, 2, null))
        {
          localObject = Build.FINGERPRINT;
          Intrinsics.checkExpressionValueIsNotNull(localObject, "Build.FINGERPRINT");
          if (!StringsKt.contains$default((CharSequence)localObject, (CharSequence)"Andy", false, 2, null))
          {
            localObject = Build.FINGERPRINT;
            Intrinsics.checkExpressionValueIsNotNull(localObject, "Build.FINGERPRINT");
            if (!StringsKt.contains$default((CharSequence)localObject, (CharSequence)"ttVM_Hdragon", false, 2, null))
            {
              localObject = Build.FINGERPRINT;
              Intrinsics.checkExpressionValueIsNotNull(localObject, "Build.FINGERPRINT");
              if (!StringsKt.contains$default((CharSequence)localObject, (CharSequence)"generic_x86_64", false, 2, null))
              {
                localObject = Build.FINGERPRINT;
                Intrinsics.checkExpressionValueIsNotNull(localObject, "Build.FINGERPRINT");
                if (!StringsKt.contains$default((CharSequence)localObject, (CharSequence)"generic/google_sdk/generic", false, 2, null))
                {
                  localObject = Build.FINGERPRINT;
                  Intrinsics.checkExpressionValueIsNotNull(localObject, "Build.FINGERPRINT");
                  if (!StringsKt.contains$default((CharSequence)localObject, (CharSequence)"vbox86p", false, 2, null))
                  {
                    localObject = Build.FINGERPRINT;
                    Intrinsics.checkExpressionValueIsNotNull(localObject, "Build.FINGERPRINT");
                    i = k;
                    if (!StringsKt.contains$default((CharSequence)localObject, (CharSequence)"generic/vbox86p/vbox86p", false, 2, null)) {
                      break label1366;
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
    i = k + 1;
    label1366:
    k = i;
    if (paramBoolean) {}
    for (;;)
    {
      try
      {
        localObject = GLES20.glGetString(7937);
        j = i;
        if (localObject != null) {
          if (!StringsKt.contains$default((CharSequence)localObject, (CharSequence)"Bluestacks", false, 2, null))
          {
            paramBoolean = StringsKt.contains$default((CharSequence)localObject, (CharSequence)"Translator", false, 2, null);
            j = i;
            if (!paramBoolean) {}
          }
          else
          {
            j = i + 10;
          }
        }
      }
      catch (Exception localException1)
      {
        j = i;
        continue;
      }
      try
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append(Environment.getExternalStorageDirectory());
        ((StringBuilder)localObject).append(File.separatorChar);
        ((StringBuilder)localObject).append("windows");
        ((StringBuilder)localObject).append(File.separatorChar);
        ((StringBuilder)localObject).append("BstSharedFolder");
        paramBoolean = new File(((StringBuilder)localObject).toString()).exists();
        k = j;
        if (paramBoolean) {
          k = j + 10;
        }
      }
      catch (Exception localException2)
      {
        k = j;
      }
    }
    return k > 3;
  }
  
  private static final boolean isIntentAvailable(@NotNull Context paramContext, Intent paramIntent)
  {
    boolean bool2 = false;
    try
    {
      paramContext = paramContext.getPackageManager().queryIntentActivities(paramIntent, 65536);
      boolean bool1 = bool2;
      if (paramContext != null)
      {
        boolean bool3 = ((Collection)paramContext).isEmpty();
        bool1 = bool2;
        if (!bool3) {
          bool1 = true;
        }
      }
      return bool1;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  private static final boolean shouldAskPermission()
  {
    return Build.VERSION.SDK_INT >= 23;
  }
  
  private static final boolean shouldAskPermission(@NotNull Context paramContext, String paramString)
  {
    return (shouldAskPermission()) && (ActivityCompat.checkSelfPermission(paramContext, paramString) != 0);
  }
  
  public static final boolean verifyInstallerId(@NotNull Context paramContext, @NotNull List paramList)
  {
    Intrinsics.checkParameterIsNotNull(paramContext, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramList, "installerID");
    ArrayList localArrayList = new ArrayList();
    paramContext = paramContext.getPackageManager().getInstallerPackageName(paramContext.getPackageName());
    paramList = paramList.iterator();
    while (paramList.hasNext()) {
      localArrayList.addAll((Collection)((InstallerID)paramList.next()).toIDs());
    }
    return (paramContext != null) && (localArrayList.contains(paramContext));
  }
  
  public static final boolean verifySigningCertificate(@NotNull Context paramContext, @Nullable String paramString)
  {
    Intrinsics.checkParameterIsNotNull(paramContext, "$receiver");
    if (paramString != null) {
      return Intrinsics.areEqual(getCurrentSignature(paramContext), paramString);
    }
    return false;
  }
}
