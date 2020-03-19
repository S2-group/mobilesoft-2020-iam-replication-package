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
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class PackageUtils
{
  private static final Comparator<AminoPackage> AMINO_PACKAGE_COMP = new Comparator()
  {
    public int compare(PackageUtils.AminoPackage paramAnonymousAminoPackage1, PackageUtils.AminoPackage paramAnonymousAminoPackage2)
    {
      int i = paramAnonymousAminoPackage1.communityId - paramAnonymousAminoPackage2.communityId;
      if (i == 0)
      {
        if (paramAnonymousAminoPackage1.appId == 0)
        {
          if (paramAnonymousAminoPackage2.appId == 0) {
            return 0;
          }
          return -1;
        }
        if (paramAnonymousAminoPackage2.appId == 0) {
          return 1;
        }
        return paramAnonymousAminoPackage2.appId - paramAnonymousAminoPackage1.appId;
      }
      return i;
    }
  };
  private Context context;
  private AminoPackage[] listPackageCache;
  private PackageManager pm;
  
  public PackageUtils(Context paramContext)
  {
    this.context = paramContext;
    if (paramContext == null) {
      paramContext = null;
    } else {
      paramContext = paramContext.getPackageManager();
    }
    this.pm = paramContext;
  }
  
  public static int compareVersionName(String paramString1, String paramString2)
  {
    paramString1 = StringUtils.split(paramString1, ".");
    paramString2 = StringUtils.split(paramString2, ".");
    int k = Math.min(paramString1.size(), paramString2.size());
    int j = 0;
    while (j < k)
    {
      int i = parseVersionSec((String)paramString1.get(j));
      int m = parseVersionSec((String)paramString2.get(j));
      if (i < m) {
        i = -1;
      } else if (i == m) {
        i = 0;
      } else {
        i = 1;
      }
      if (i != 0) {
        return i;
      }
      j += 1;
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
  
  private static int parseVersionSec(String paramString)
  {
    int j = paramString.length();
    int i = 0;
    while (i < j)
    {
      int k = paramString.charAt(i);
      if ((k < 48) || (k > 57)) {
        break;
      }
      i += 1;
    }
    if (i == 0) {
      return 0;
    }
    return Integer.parseInt(paramString.substring(0, i));
  }
  
  public boolean acmBroadcast()
  {
    Object localObject = this.context.getPackageManager();
    Intent localIntent = new Intent("com.narvii.amino.acm.BROADCAST");
    boolean bool2 = false;
    localObject = ((PackageManager)localObject).resolveActivity(localIntent, 0);
    boolean bool1 = bool2;
    if (localObject != null)
    {
      bool1 = bool2;
      if (((ResolveInfo)localObject).activityInfo != null) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public void createAmino(int paramInt)
  {
    try
    {
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append(getAcmScheme());
      ((StringBuilder)localObject).append("://template/");
      ((StringBuilder)localObject).append(paramInt);
      localObject = ((StringBuilder)localObject).toString();
      this.context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse((String)localObject)));
      return;
    }
    catch (Exception localException) {}
  }
  
  public void downloadAcm()
  {
    String str = getAcmPackageName();
    try
    {
      localContext = this.context;
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("market://details?id=");
      localStringBuilder.append(str);
      localContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(localStringBuilder.toString())));
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      Context localContext;
      StringBuilder localStringBuilder;
      for (;;) {}
    }
    localContext = this.context;
    localStringBuilder = new StringBuilder();
    localStringBuilder.append("http://play.google.com/store/apps/details?id=");
    localStringBuilder.append(str);
    localContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(localStringBuilder.toString())));
  }
  
  public String getAcmPackageName()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("com.narvii.amino.manager");
    localStringBuilder.append(getPackageSuffix());
    return localStringBuilder.toString();
  }
  
  public String getAcmScheme()
  {
    if (NVApplication.DEBUG) {
      return "pebkitmanager";
    }
    return "narviimanager";
  }
  
  public int getAppIdFromPackageName(String paramString)
  {
    int i = paramString.lastIndexOf(".x");
    if (i < 0) {
      return 0;
    }
    int j = paramString.indexOf('.', i + 3);
    if (j < 0) {
      paramString = paramString.substring(i + 2);
    } else {
      paramString = paramString.substring(i + 2, j);
    }
    i = paramString.indexOf('_');
    if (i > 0) {}
    try
    {
      i = Integer.parseInt(paramString.substring(i + 1));
      return i;
    }
    catch (Exception paramString) {}
    return 0;
    return 0;
  }
  
  public String getAppName()
  {
    String str2 = getAppName(this.context.getPackageName());
    String str1 = str2;
    if (TextUtils.isEmpty(str2)) {
      str1 = "Amino";
    }
    return str1;
  }
  
  public String getAppName(String paramString)
  {
    for (;;)
    {
      try
      {
        PackageManager localPackageManager = this.context.getPackageManager();
        paramString = localPackageManager.getApplicationInfo(paramString, 0);
        if (paramString != null)
        {
          paramString = localPackageManager.getApplicationLabel(paramString);
          paramString = (String)paramString;
          return paramString;
        }
      }
      catch (Exception paramString)
      {
        return null;
      }
      paramString = null;
    }
  }
  
  public int getCommunityIdFromPackageName()
  {
    return getCommunityIdFromPackageName(this.context.getPackageName());
  }
  
  public int getCommunityIdFromPackageName(String paramString)
  {
    int i = paramString.lastIndexOf(".x");
    if (i < 0) {
      return 0;
    }
    int j = paramString.indexOf('.', i + 3);
    if (j < 0) {
      paramString = paramString.substring(i + 2);
    } else {
      paramString = paramString.substring(i + 2, j);
    }
    i = paramString.indexOf('_');
    String str = paramString;
    if (i > 0) {
      str = paramString.substring(0, i);
    }
    try
    {
      i = Integer.parseInt(str);
      return i;
    }
    catch (Exception paramString) {}
    return 0;
  }
  
  public int getCommunityIdFromScheme(String paramString)
  {
    String str = getSchemePrefix();
    if (paramString.startsWith(str)) {}
    try
    {
      int i = Integer.parseInt(paramString.substring(str.length()));
      return i;
    }
    catch (Exception paramString)
    {
      for (;;) {}
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
      return null;
    }
    catch (Exception localException) {}
    return null;
  }
  
  public String getKeychainAuthorities(AminoPackage paramAminoPackage)
  {
    StringBuilder localStringBuilder = new StringBuilder("com.narvii.amino");
    localStringBuilder.append(getPackageSuffix());
    localStringBuilder.append(".account");
    if (paramAminoPackage.communityId == 0)
    {
      localStringBuilder.append(".master");
    }
    else
    {
      localStringBuilder.append(".x");
      localStringBuilder.append(paramAminoPackage.communityId);
      if (paramAminoPackage.appId != 0)
      {
        localStringBuilder.append('_');
        localStringBuilder.append(paramAminoPackage.appId);
      }
    }
    return localStringBuilder.toString();
  }
  
  public String getMasterPackageName()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("com.narvii.amino.master");
    localStringBuilder.append(getPackageSuffix());
    return localStringBuilder.toString();
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
    if (paramInt == 0) {
      return getMasterPackageName();
    }
    if (this.listPackageCache == null) {
      this.listPackageCache = listAminoPackages();
    }
    Object localObject1 = this.listPackageCache;
    int j = localObject1.length;
    int i = 0;
    while (i < j)
    {
      Object localObject2 = localObject1[i];
      if (localObject2.communityId == paramInt) {
        return localObject2.packageName;
      }
      i += 1;
    }
    localObject1 = new StringBuilder("com.narvii.amino");
    ((StringBuilder)localObject1).append(".x");
    ((StringBuilder)localObject1).append(paramInt);
    ((StringBuilder)localObject1).append(getPackageSuffix());
    return ((StringBuilder)localObject1).toString();
  }
  
  public String getPackageSuffix()
  {
    if (this.context.getPackageName().endsWith(".dev")) {
      return ".dev";
    }
    return "";
  }
  
  public String getPermalinkHost(boolean paramBoolean)
  {
    String str;
    if (NVApplication.DEBUG) {
      str = NVApplication.MAIN_HOST;
    } else {
      str = ".aminoapps.com";
    }
    if (paramBoolean) {
      return str.substring(1);
    }
    return str;
  }
  
  public int getPrimaryVersion()
  {
    ArrayList localArrayList = StringUtils.split(getVersionName(), ".");
    if (localArrayList.size() > 0) {
      return Integer.parseInt((String)localArrayList.get(0));
    }
    return 1;
  }
  
  public String getScheme(int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(getSchemePrefix());
    localStringBuilder.append(paramInt);
    return localStringBuilder.toString();
  }
  
  public int getSecondaryVersion()
  {
    ArrayList localArrayList = StringUtils.split(getVersionName(), ".");
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
    catch (Exception localException)
    {
      for (;;) {}
    }
    return 1;
  }
  
  public String getVersionName()
  {
    try
    {
      String str = this.pm.getPackageInfo(this.context.getPackageName(), 0).versionName;
      return str;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return "1.0.0";
  }
  
  public boolean installedAcm()
  {
    Object localObject;
    try
    {
      PackageInfo localPackageInfo = this.pm.getPackageInfo(new PackageUtils(this.context).getAcmPackageName(), 0);
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      localNameNotFoundException.printStackTrace();
      localObject = null;
    }
    return localObject != null;
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
  
  public boolean isInstalled(String paramString)
  {
    try
    {
      paramString = this.pm.getPackageInfo(paramString, 0);
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      paramString.printStackTrace();
      paramString = null;
    }
    return paramString != null;
  }
  
  public boolean isInstalledFromGooglePlay()
  {
    return "com.android.vending".equals(this.context.getPackageManager().getInstallerPackageName(this.context.getPackageName()));
  }
  
  public boolean isMasterInstalled()
  {
    return isPackageInstalled(getMasterPackageName());
  }
  
  public boolean isNativeAminoScheme(String paramString)
  {
    String str = getSchemePrefix();
    boolean bool = false;
    if ((paramString != null) && (paramString.startsWith(str)))
    {
      paramString = paramString.substring(str.length());
      if (paramString.length() == 0) {
        return true;
      }
    }
    try
    {
      int i = Integer.parseInt(paramString);
      if (i > 0) {
        bool = true;
      }
      return bool;
    }
    catch (Exception paramString) {}
    return getAcmScheme().equals(paramString);
    return false;
  }
  
  public boolean isPackageInstalled(String paramString)
  {
    if (this.context.getPackageName().equals(paramString)) {
      return true;
    }
    try
    {
      paramString = this.pm.getPackageInfo(paramString, 128);
      if (paramString != null) {
        return true;
      }
    }
    catch (Exception paramString)
    {
      for (;;) {}
    }
    return false;
  }
  
  public boolean isPermalinkHost(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(".");
    localStringBuilder.append(paramString);
    return localStringBuilder.toString().endsWith(getPermalinkHost(false));
  }
  
  public void launchAcm()
  {
    try
    {
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append(getAcmScheme());
      ((StringBuilder)localObject).append("://default");
      localObject = ((StringBuilder)localObject).toString();
      this.context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse((String)localObject)));
      return;
    }
    catch (Exception localException) {}
  }
  
  public AminoPackage[] listAminoPackages()
  {
    try
    {
      Object localObject1 = this.context.getPackageManager();
      Object localObject3 = ((PackageManager)localObject1).getInstalledApplications(128);
      boolean bool = NVApplication.DEBUG;
      Object localObject2 = new HashSet();
      localObject3 = ((List)localObject3).iterator();
      while (((Iterator)localObject3).hasNext())
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject3).next();
        if ((localApplicationInfo.packageName.startsWith("com.narvii.amino.")) && (isDevPackage(localApplicationInfo.packageName) == bool)) {
          ((HashSet)localObject2).add(localApplicationInfo.packageName);
        }
      }
      if (((HashSet)localObject2).size() <= 1)
      {
        ((HashSet)localObject2).clear();
        localObject3 = ((PackageManager)localObject1).queryIntentActivities(new Intent("com.narvii.intent.action.MAIN"), 131072).iterator();
        while (((Iterator)localObject3).hasNext())
        {
          localObject1 = (ResolveInfo)((Iterator)localObject3).next();
          if (((ResolveInfo)localObject1).activityInfo == null) {
            localObject1 = null;
          } else {
            localObject1 = ((ResolveInfo)localObject1).activityInfo.packageName;
          }
          if ((localObject1 != null) && (((String)localObject1).startsWith("com.narvii.amino.")) && (isDevPackage((String)localObject1) == bool)) {
            ((HashSet)localObject2).add(localObject1);
          }
        }
      }
      localObject1 = new ArrayList();
      localObject2 = ((HashSet)localObject2).iterator();
      while (((Iterator)localObject2).hasNext())
      {
        localObject3 = (String)((Iterator)localObject2).next();
        i = getCommunityIdFromPackageName((String)localObject3);
        if (i > 0) {
          ((ArrayList)localObject1).add(new AminoPackage(i, getAppIdFromPackageName((String)localObject3), (String)localObject3));
        } else if ((i == 0) && (((String)localObject3).equals(getMasterPackageName()))) {
          ((ArrayList)localObject1).add(new AminoPackage(i, 0, (String)localObject3));
        }
      }
      Collections.sort((List)localObject1, AMINO_PACKAGE_COMP);
      localObject1 = (AminoPackage[])((ArrayList)localObject1).toArray(new AminoPackage[((ArrayList)localObject1).size()]);
      return localObject1;
    }
    catch (Throwable localThrowable)
    {
      Log.e("fail to list amino packages", localThrowable);
      String str = this.context.getPackageName();
      int i = getCommunityIdFromPackageName(str);
      if (i > 0) {
        return new AminoPackage[] { new AminoPackage(i, getAppIdFromPackageName(str), str) };
      }
      if ((i == 0) && (str.equals(getMasterPackageName()))) {
        return new AminoPackage[] { new AminoPackage(i, 0, str) };
      }
    }
    return new AminoPackage[0];
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
      localIntent.addCategory("android.intent.category.LAUNCHER");
      localIntent.setClassName(paramString, ((ResolveInfo)localObject).activityInfo.name);
      localIntent.setFlags(268435456);
      this.context.startActivity(localIntent);
      return true;
    }
    catch (Exception paramString) {}
    return false;
  }
  
  public void openGooglePlay(String paramString)
  {
    openGooglePlayWithNativeLink(paramString, null, null);
  }
  
  public void openGooglePlayWithNativeLink(String paramString1, String paramString2, String paramString3)
  {
    openGooglePlayWithNativeLink(paramString1, paramString2, paramString3, null);
  }
  
  public void openGooglePlayWithNativeLink(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    Object localObject1 = null;
    Object localObject2 = null;
    try
    {
      if (isGooglePlayInstalled())
      {
        localObject1 = localObject2;
        if (!TextUtils.isEmpty(paramString2))
        {
          localObject1 = new StringBuilder();
          ((StringBuilder)localObject1).append("deferred_link=");
          ((StringBuilder)localObject1).append(UriUtils.encodeURIComponent(paramString2.trim()));
          localObject1 = ((StringBuilder)localObject1).toString();
        }
        paramString2 = (String)localObject1;
        if (!TextUtils.isEmpty(paramString3))
        {
          if (localObject1 == null)
          {
            paramString2 = "";
          }
          else
          {
            paramString2 = new StringBuilder();
            paramString2.append((String)localObject1);
            paramString2.append("&");
            paramString2 = paramString2.toString();
          }
          localObject1 = new StringBuilder();
          ((StringBuilder)localObject1).append(paramString2);
          ((StringBuilder)localObject1).append("amino_tracking_id=");
          ((StringBuilder)localObject1).append(UriUtils.encodeURIComponent(paramString3));
          paramString2 = ((StringBuilder)localObject1).toString();
        }
        localObject1 = paramString2;
        if (!TextUtils.isEmpty(paramString4))
        {
          if (paramString2 == null)
          {
            paramString2 = "";
          }
          else
          {
            paramString3 = new StringBuilder();
            paramString3.append(paramString2);
            paramString3.append("&");
            paramString2 = paramString3.toString();
          }
          paramString3 = new StringBuilder();
          paramString3.append(paramString2);
          paramString3.append(paramString4);
          localObject1 = paramString3.toString();
        }
      }
      paramString2 = new StringBuilder();
      paramString2.append("market://details?id=");
      paramString2.append(paramString1);
      paramString3 = paramString2.toString();
      paramString2 = paramString3;
      if (!TextUtils.isEmpty((CharSequence)localObject1))
      {
        paramString2 = new StringBuilder();
        paramString2.append(paramString3);
        paramString2.append("&referrer=");
        paramString2.append(UriUtils.encodeURIComponent((String)localObject1));
        paramString2 = paramString2.toString();
      }
      this.context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(paramString2)));
      return;
    }
    catch (ActivityNotFoundException paramString2)
    {
      for (;;) {}
    }
    paramString2 = new StringBuilder();
    paramString2.append("http://play.google.com/store/apps/details?id=");
    paramString2.append(paramString1);
    paramString1 = new Intent("android.intent.action.VIEW", Uri.parse(paramString2.toString()));
    this.context.startActivity(paramString1);
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
    public final int appId;
    public final int communityId;
    public final String packageName;
    
    public AminoPackage(int paramInt1, int paramInt2, String paramString)
    {
      this.communityId = paramInt1;
      this.appId = paramInt2;
      this.packageName = paramString;
    }
  }
}
