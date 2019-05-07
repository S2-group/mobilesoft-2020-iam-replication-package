package com.narvii.util;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.narvii.app.NVApplication;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class PackageUtils
{
  private static final Comparator<AminoPackage> AMINO_PACKAGE_COMP = new Comparator()
  {
    public int compare(PackageUtils.AminoPackage paramAnonymousAminoPackage1, PackageUtils.AminoPackage paramAnonymousAminoPackage2)
    {
      return paramAnonymousAminoPackage1.communityId - paramAnonymousAminoPackage2.communityId;
    }
  };
  private Context context;
  private PackageManager pm;
  
  public PackageUtils(Context paramContext)
  {
    this.context = paramContext;
    if (paramContext == null) {}
    for (paramContext = null;; paramContext = paramContext.getPackageManager())
    {
      this.pm = paramContext;
      return;
    }
  }
  
  public static int compareVersionName(String paramString1, String paramString2)
  {
    paramString1 = Utils.split(paramString1, ".");
    paramString2 = Utils.split(paramString2, ".");
    int i = 0;
    int m = Math.min(paramString1.size(), paramString2.size());
    while (i < m)
    {
      String str1 = (String)paramString1.get(i);
      int j = 0;
      int k = str1.length();
      int n;
      if (j < k)
      {
        n = str1.charAt(j);
        if ((n < 48) || (n > 57)) {
          j = 0;
        }
      }
      do
      {
        return j;
        j += 1;
        break;
        String str2 = (String)paramString2.get(i);
        j = 0;
        k = str2.length();
        while (j < k)
        {
          n = str2.charAt(j);
          if ((n < 48) || (n > 57)) {
            return 0;
          }
          j += 1;
        }
        k = str1.compareTo(str2);
        j = k;
      } while (k != 0);
      i += 1;
    }
    return 0;
  }
  
  private String getSchemePrefix()
  {
    if (NVApplication.DEBUG) {
      return "pebkitapp";
    }
    return "narviiapp";
  }
  
  public boolean acmBroadcast()
  {
    boolean bool2 = false;
    ResolveInfo localResolveInfo = this.context.getPackageManager().resolveActivity(new Intent("com.narvii.amino.acm.BROADCAST"), 0);
    boolean bool1 = bool2;
    if (localResolveInfo != null)
    {
      bool1 = bool2;
      if (localResolveInfo.activityInfo != null) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public void downloadAcm()
  {
    String str = getAcmPackageName();
    try
    {
      this.context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + str)));
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      this.context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=" + str)));
    }
  }
  
  public String getAcmPackageName()
  {
    return "com.narvii.amino.manager" + getPackageSuffix();
  }
  
  public String getAcmScheme()
  {
    if (NVApplication.DEBUG) {
      return "pebkitmanager";
    }
    return "narviimanager";
  }
  
  public String getAppName()
  {
    try
    {
      Object localObject = this.context.getPackageManager();
      ApplicationInfo localApplicationInfo = ((PackageManager)localObject).getApplicationInfo(this.context.getApplicationInfo().packageName, 0);
      if (localApplicationInfo != null) {}
      for (localObject = ((PackageManager)localObject).getApplicationLabel(localApplicationInfo);; localObject = "Amino") {
        return (String)localObject;
      }
      return "Amino";
    }
    catch (Exception localException) {}
  }
  
  public int getCommunityIdFromPackageName()
  {
    return getCommunityIdFromPackageName(this.context.getPackageName());
  }
  
  public int getCommunityIdFromPackageName(String paramString)
  {
    String str = paramString;
    if (paramString.endsWith(".dev")) {
      str = paramString.substring(0, paramString.length() - ".dev".length());
    }
    int i = 0;
    int j = str.lastIndexOf(".x");
    if (j > 0) {}
    try
    {
      i = Integer.parseInt(str.substring(j + 2));
      return i;
    }
    catch (Exception paramString) {}
    return 0;
  }
  
  public int getCommunityIdFromScheme(String paramString)
  {
    String str = getSchemePrefix();
    if (paramString.startsWith(str)) {
      try
      {
        int i = Integer.parseInt(paramString.substring(str.length()));
        return i;
      }
      catch (Exception paramString) {}
    }
    return 0;
  }
  
  public Locale getForceLocale()
  {
    try
    {
      Object localObject2 = this.context.getPackageManager().getApplicationInfo(this.context.getPackageName(), 128);
      if (((ApplicationInfo)localObject2).metaData != null)
      {
        Object localObject1 = ((ApplicationInfo)localObject2).metaData.getString("com.narvii.forceLang");
        if (TextUtils.isEmpty((CharSequence)localObject1)) {
          return null;
        }
        localObject2 = ((ApplicationInfo)localObject2).metaData.getString("com.narvii.forceCountry");
        if (TextUtils.isEmpty((CharSequence)localObject2)) {
          return new Locale((String)localObject1);
        }
        localObject1 = new Locale((String)localObject1, (String)localObject2);
        return localObject1;
      }
    }
    catch (Exception localException) {}
    return null;
  }
  
  public String getKeychainAuthorities(int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder("com.narvii.amino");
    localStringBuilder.append(getPackageSuffix());
    localStringBuilder.append(".account");
    if (paramInt == 0) {
      localStringBuilder.append(".master");
    }
    for (;;)
    {
      return localStringBuilder.toString();
      localStringBuilder.append(".x").append(paramInt);
    }
  }
  
  public String getMasterPackageName()
  {
    return "com.narvii.amino.master" + getPackageSuffix();
  }
  
  public String getMasterScheme()
  {
    if (NVApplication.DEBUG) {
      return "pebkitapp";
    }
    return "narviiapp";
  }
  
  public String getPackageName(int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder("com.narvii.amino");
    if (paramInt == 0) {
      localStringBuilder.append(".master");
    }
    for (;;)
    {
      localStringBuilder.append(getPackageSuffix());
      return localStringBuilder.toString();
      localStringBuilder.append(".x").append(paramInt);
    }
  }
  
  public String getPackageSuffix()
  {
    if (this.context.getPackageName().endsWith(".dev")) {
      return ".dev";
    }
    return "";
  }
  
  public int getPrimaryVersion()
  {
    ArrayList localArrayList = Utils.split(getVersionName(), ".");
    if (localArrayList.size() > 0) {
      return Integer.parseInt((String)localArrayList.get(0));
    }
    return 1;
  }
  
  public String getScheme(int paramInt)
  {
    return getSchemePrefix() + paramInt;
  }
  
  public int getSecondaryVersion()
  {
    ArrayList localArrayList = Utils.split(getVersionName(), ".");
    if (localArrayList.size() > 1) {
      return Integer.parseInt((String)localArrayList.get(1));
    }
    return 0;
  }
  
  public int getVersionCode()
  {
    try
    {
      int i = this.pm.getPackageInfo(this.context.getPackageName(), 0).versionCode;
      return i;
    }
    catch (Exception localException) {}
    return 1;
  }
  
  public String getVersionName()
  {
    try
    {
      String str = this.pm.getPackageInfo(this.context.getPackageName(), 0).versionName;
      return str;
    }
    catch (Exception localException) {}
    return "1.0.0";
  }
  
  public boolean installedAcm()
  {
    try
    {
      localPackageInfo = this.pm.getPackageInfo(new PackageUtils(this.context).getAcmPackageName(), 0);
      if (localPackageInfo == null) {
        return false;
      }
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;)
      {
        PackageInfo localPackageInfo = null;
        localNameNotFoundException.printStackTrace();
      }
    }
    return true;
  }
  
  public boolean isCommunityInstalled(int paramInt)
  {
    return isPackageInstalled(getPackageName(paramInt));
  }
  
  public boolean isDevPackage(String paramString)
  {
    return paramString.endsWith(".dev");
  }
  
  public boolean isGooglePlayInstalled()
  {
    return isPackageInstalled("com.android.vending");
  }
  
  public boolean isInstalledFromGooglePlay()
  {
    return "com.android.vending".equals(this.context.getPackageManager().getInstallerPackageName(this.context.getPackageName()));
  }
  
  public boolean isMasterInstalled()
  {
    return isPackageInstalled(getMasterPackageName());
  }
  
  public boolean isPackageInstalled(String paramString)
  {
    if (this.context.getPackageName().equals(paramString)) {}
    for (;;)
    {
      return true;
      try
      {
        paramString = this.pm.getPackageInfo(paramString, 128);
        if (paramString != null) {}
      }
      catch (Exception paramString)
      {
        for (;;) {}
      }
    }
    return false;
  }
  
  public boolean isPermalinkHost(String paramString)
  {
    if (NVApplication.DEBUG) {}
    for (String str = ".pebkit.com";; str = ".aminoapps.com") {
      return ("." + paramString).endsWith(str);
    }
  }
  
  public void launchAcm()
  {
    try
    {
      String str = getAcmScheme() + "://default";
      this.context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
      return;
    }
    catch (Exception localException) {}
  }
  
  public AminoPackage[] listAminoPackages()
  {
    try
    {
      Object localObject2 = this.context.getPackageManager().getInstalledApplications(128);
      boolean bool = NVApplication.DEBUG;
      ArrayList localArrayList = new ArrayList();
      localObject2 = ((List)localObject2).iterator();
      int i;
      while (((Iterator)localObject2).hasNext())
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject2).next();
        if ((localApplicationInfo.packageName.startsWith("com.narvii.amino.")) && (isDevPackage(localApplicationInfo.packageName) == bool))
        {
          i = getCommunityIdFromPackageName(localApplicationInfo.packageName);
          if ((i > 0) || ((i == 0) && (localApplicationInfo.packageName.equals(getMasterPackageName())))) {
            localArrayList.add(new AminoPackage(i, localApplicationInfo.packageName));
          }
        }
      }
      Object localObject1;
      return new AminoPackage[0];
    }
    catch (Throwable localThrowable)
    {
      Log.e("fail to list amino packages", localThrowable);
      localObject1 = this.context.getPackageName();
      i = getCommunityIdFromPackageName((String)localObject1);
      if ((i > 0) || ((i == 0) && (((String)localObject1).equals(getMasterPackageName()))))
      {
        return new AminoPackage[] { new AminoPackage(i, (String)localObject1) };
        Collections.sort((List)localObject1, AMINO_PACKAGE_COMP);
        localObject1 = (AminoPackage[])((ArrayList)localObject1).toArray(new AminoPackage[((ArrayList)localObject1).size()]);
        return localObject1;
      }
    }
  }
  
  public boolean openCommunity(int paramInt)
  {
    return openCommunity(getPackageName(paramInt));
  }
  
  public boolean openCommunity(String paramString)
  {
    Object localObject = new Intent("android.intent.action.MAIN");
    ((Intent)localObject).addCategory("android.intent.category.LAUNCHER");
    ((Intent)localObject).setPackage(paramString);
    try
    {
      localObject = this.pm.resolveActivity((Intent)localObject, 0);
      Intent localIntent = new Intent("android.intent.action.MAIN");
      return false;
    }
    catch (Exception paramString)
    {
      try
      {
        localIntent.addCategory("android.intent.category.LAUNCHER");
        localIntent.setClassName(paramString, ((ResolveInfo)localObject).activityInfo.name);
        localIntent.setFlags(268435456);
        this.context.startActivity(localIntent);
        return true;
      }
      catch (Exception paramString) {}
      paramString = paramString;
      return false;
    }
  }
  
  public void openGooglePlay(String paramString)
  {
    openGooglePlayWithNativeLink(paramString, null, null);
  }
  
  public void openGooglePlayWithNativeLink(String paramString1, String paramString2, String paramString3)
  {
    Object localObject = null;
    String str = null;
    try
    {
      if (isGooglePlayInstalled())
      {
        if (!TextUtils.isEmpty(paramString2)) {
          str = "deferred_link=" + UriUtils.encodeURIComponent(paramString2.trim());
        }
        localObject = str;
        if (!TextUtils.isEmpty(paramString3)) {
          if (str != null) {
            break label176;
          }
        }
      }
      label176:
      for (paramString2 = "";; paramString2 = str + "&")
      {
        localObject = paramString2 + "amino_tracking_id=" + UriUtils.encodeURIComponent(paramString3);
        paramString3 = "market://details?id=" + paramString1;
        paramString2 = paramString3;
        if (!TextUtils.isEmpty((CharSequence)localObject)) {
          paramString2 = paramString3 + "&referrer=" + UriUtils.encodeURIComponent((String)localObject);
        }
        this.context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(paramString2)));
        return;
      }
      return;
    }
    catch (ActivityNotFoundException paramString2)
    {
      paramString1 = new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=" + paramString1));
      this.context.startActivity(paramString1);
    }
  }
  
  public void openOrInstallCommunity(int paramInt)
  {
    openOrInstallCommunity(getPackageName(paramInt));
  }
  
  public void openOrInstallCommunity(String paramString)
  {
    if ((isPackageInstalled(paramString)) && (openCommunity(paramString))) {
      return;
    }
    openGooglePlay(paramString);
  }
  
  public boolean verifyPackageSignature(String paramString)
  {
    PackageManager localPackageManager = this.context.getPackageManager();
    try
    {
      paramString = localPackageManager.getPackageInfo(paramString, 64);
      if (paramString == null) {
        return false;
      }
      boolean bool = localPackageManager.getPackageInfo(this.context.getPackageName(), 64).signatures[0].equals(paramString.signatures[0]);
      return bool;
    }
    catch (Exception paramString) {}
    return false;
  }
  
  public static class AminoPackage
  {
    public final int communityId;
    public final String packageName;
    
    public AminoPackage(int paramInt, String paramString)
    {
      this.communityId = paramInt;
      this.packageName = paramString;
    }
  }
}
