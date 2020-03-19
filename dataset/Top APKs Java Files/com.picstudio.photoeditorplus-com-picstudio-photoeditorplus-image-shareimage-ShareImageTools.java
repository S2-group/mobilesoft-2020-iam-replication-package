package com.picstudio.photoeditorplus.image.shareimage;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Parcelable;
import android.text.TextUtils;
import com.picstudio.photoeditorplus.CameraApp;
import com.picstudio.photoeditorplus.image.BitmapBean;
import com.picstudio.photoeditorplus.image.MediaTypeUtil;
import com.picstudio.photoeditorplus.sdk.SdkCheckUtil;
import com.picstudio.photoeditorplus.utils.PreferenceConfig;
import com.picstudio.photoeditorplus.utils.RegionUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ShareImageTools
{
  public static final String FACEBOOK_SEND_PIC_TO_SHARE_ACTIVITY_NAME1 = "com.facebook.composer.shareintent.ImplicitShareIntentHandlerDefaultAlias";
  public static final String FACEBOOK_SEND_PIC_TO_SHARE_ACTIVITY_NAME2 = "com.facebook.composer.shareintent.ImplicitShareIntentHandler";
  public static final String FACEBOOK_SEND_PIC_TO_SHARE_PACKAGE_NAME = "com.facebook.katana";
  public static final String GOSMS_SEND_PIC_TO_FRIEND_ACTIVITY_NAME = "com.jb.gosms.ui.ComposeMessageActivity";
  public static final String GOSMS_SEND_PIC_TO_FRIEND_PACKAGE_NAME = "com.jb.gosms";
  public static final String INSTAGRAM_SEND_PIC_TO_SHARE_ACTIVITY_NAME = "com.instagram.android.activity.ShareHandlerActivity";
  public static final String INSTAGRAM_SEND_PIC_TO_SHARE_PACKAGE_NAME = "com.instagram.android";
  public static final String PRINT_LOCAL_PACKAGE_NAME = "print_local";
  public static final String QQ_SEND_PIC_TO_KONGJIAN_ACTIVITY_NAME = "com.qzonex.module.operation.ui.QZonePublishMoodActivity";
  public static final String QQ_SEND_PIC_TO_KONGJIAN_PACKAGE_NAME = "com.qzone";
  public static final String SINA_SEND_PIC_TO_PENGYOUQUAN_ACTIVITY_NAME = "com.sina.weibo.ComposerDispatchActivity";
  public static final String SINA_SEND_PIC_TO_PENGYOUQUAN_PACKAGE_NAME = "com.sina.weibo";
  public static final String WEIXIN_SEND_PIC_TO_PENGYOUQUAN_ACTIVITY_NAME = "com.tencent.mm.ui.tools.ShareToTimeLineUI";
  public static final String WEIXIN_SEND_PIC_TO_PENGYOUQUAN_PACKAGE_NAME = "com.tencent.mm";
  public static final String WHATSAPP_SEND_PIC_TO_SHARE_ACTIVITY_NAME = "com.whatsapp.ContactPicker";
  public static final String WHATSAPP_SEND_PIC_TO_SHARE_PACKAGE_NAME = "com.whatsapp";
  
  public ShareImageTools() {}
  
  private static String buildTransaction(String paramString)
  {
    if (paramString == null) {
      return String.valueOf(System.currentTimeMillis());
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString);
    localStringBuilder.append(System.currentTimeMillis());
    return localStringBuilder.toString();
  }
  
  private static List<ResolveInfo> getAllSendImageOrVideoTools(Context paramContext, boolean paramBoolean)
  {
    paramContext = paramContext.getPackageManager();
    Intent localIntent = new Intent("android.intent.action.SEND");
    if (paramBoolean) {
      localIntent.setType("image/*");
    } else {
      localIntent.setType("video/*");
    }
    localIntent.addCategory("android.intent.category.DEFAULT");
    return paramContext.queryIntentActivities(localIntent, 65536);
  }
  
  private static List<ResolveInfo> getAllSendMutilMediaTools(Context paramContext, int paramInt1, int paramInt2)
  {
    paramContext = paramContext.getPackageManager();
    Intent localIntent = new Intent();
    if ((paramInt1 <= 1) && (paramInt2 <= 1) && ((paramInt1 <= 0) || (paramInt2 <= 0))) {
      localIntent.setAction("android.intent.action.SEND");
    } else {
      localIntent.setAction("android.intent.action.SEND_MULTIPLE");
    }
    if ((paramInt1 > 0) && (paramInt2 > 0)) {
      localIntent.setType("media/*");
    } else if (paramInt2 > 0) {
      localIntent.setType("video/*");
    } else {
      localIntent.setType("image/*");
    }
    localIntent.addCategory("android.intent.category.DEFAULT");
    return paramContext.queryIntentActivities(localIntent, 65536);
  }
  
  private static List<ResolveInfo> getAllSendTextTools(Context paramContext)
  {
    paramContext = paramContext.getPackageManager();
    Intent localIntent = new Intent("android.intent.action.SEND");
    localIntent.setType("text/*");
    localIntent.addCategory("android.intent.category.DEFAULT");
    return paramContext.queryIntentActivities(localIntent, 65536);
  }
  
  public static List<ShareImageItem.ShareImageItemData> getAllShareMutilMediaTools(Context paramContext, int paramInt1, int paramInt2)
  {
    ArrayList localArrayList = new ArrayList();
    PackageManager localPackageManager = paramContext.getPackageManager();
    paramContext = getAllSendMutilMediaTools(paramContext, paramInt1, paramInt2).iterator();
    while (paramContext.hasNext())
    {
      ResolveInfo localResolveInfo = (ResolveInfo)paramContext.next();
      if (!localResolveInfo.activityInfo.packageName.equals("com.picstudio.photoeditorplus")) {
        localArrayList.add(new ShareImageItem.ShareImageItemData(localResolveInfo.activityInfo.packageName, localResolveInfo.activityInfo.name, localResolveInfo.loadIcon(localPackageManager), localResolveInfo.loadLabel(localPackageManager).toString()));
      }
    }
    return localArrayList;
  }
  
  public static List<ShareImageItem.ShareImageItemData> getAllShareTextTools(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    PackageManager localPackageManager = paramContext.getPackageManager();
    paramContext = getAllSendTextTools(paramContext).iterator();
    while (paramContext.hasNext())
    {
      ResolveInfo localResolveInfo = (ResolveInfo)paramContext.next();
      if (!localResolveInfo.activityInfo.packageName.equals("com.picstudio.photoeditorplus")) {
        localArrayList.add(new ShareImageItem.ShareImageItemData(localResolveInfo.activityInfo.packageName, localResolveInfo.activityInfo.name, localResolveInfo.loadIcon(localPackageManager), localResolveInfo.loadLabel(localPackageManager).toString()));
      }
    }
    return localArrayList;
  }
  
  public static List<ShareImageItem.ShareImageItemData> getAllShareTools(Context paramContext, boolean paramBoolean)
  {
    ArrayList localArrayList = new ArrayList();
    PackageManager localPackageManager = paramContext.getPackageManager();
    paramContext = getAllSendImageOrVideoTools(paramContext, paramBoolean).iterator();
    while (paramContext.hasNext())
    {
      ResolveInfo localResolveInfo = (ResolveInfo)paramContext.next();
      if (!localResolveInfo.activityInfo.packageName.equals("com.picstudio.photoeditorplus")) {
        localArrayList.add(new ShareImageItem.ShareImageItemData(localResolveInfo.activityInfo.packageName, localResolveInfo.activityInfo.name, localResolveInfo.loadIcon(localPackageManager), localResolveInfo.loadLabel(localPackageManager).toString()));
      }
    }
    return localArrayList;
  }
  
  public static boolean getAppIsInstalled(Context paramContext, String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return false;
    }
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramString, 0);
      return paramContext != null;
    }
    catch (Throwable paramContext) {}
    return false;
  }
  
  public static boolean getAppIsInstalled(Context paramContext, String[] paramArrayOfString)
  {
    if (paramArrayOfString == null) {
      return false;
    }
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledPackages(0).iterator();
      while (paramContext.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
        int j = paramArrayOfString.length;
        int i = 0;
        while (i < j)
        {
          String str = paramArrayOfString[i];
          boolean bool = localPackageInfo.packageName.equals(str);
          if (bool) {
            return true;
          }
          i += 1;
        }
      }
      return false;
    }
    catch (Throwable paramContext) {}
    return false;
  }
  
  private static int getCountTools(Context paramContext, List<ShareImageItem.ShareImageItemData> paramList, boolean paramBoolean, int paramInt)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    paramContext = getAllSendImageOrVideoTools(paramContext, paramBoolean);
    int m = paramContext.size();
    int j = 0;
    int i;
    for (int k = 0; j < m; k = i)
    {
      ResolveInfo localResolveInfo = (ResolveInfo)paramContext.get(j);
      i = k;
      if (!isDefaultTools(localResolveInfo.activityInfo.packageName, localResolveInfo.activityInfo.name))
      {
        paramList.add(new ShareImageItem.ShareImageItemData(localResolveInfo.activityInfo.packageName, localResolveInfo.activityInfo.name, localResolveInfo.loadIcon(localPackageManager), localResolveInfo.loadLabel(localPackageManager).toString()));
        k += 1;
        i = k;
        if (k == paramInt) {
          return k;
        }
      }
      j += 1;
    }
    return k;
  }
  
  private static int getCountTools(Context paramContext, List<ShareImageItem.ShareImageItemData> paramList, boolean paramBoolean, int paramInt, String[] paramArrayOfString1, String[] paramArrayOfString2)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    paramContext = getAllSendImageOrVideoTools(paramContext, paramBoolean);
    int m = paramContext.size();
    int j = 0;
    int i;
    for (int k = 0; j < m; k = i)
    {
      ResolveInfo localResolveInfo = (ResolveInfo)paramContext.get(j);
      i = k;
      if (!isDefaultTools(localResolveInfo.activityInfo.packageName, localResolveInfo.activityInfo.name))
      {
        i = k;
        if (!isFilterTools(localResolveInfo, paramArrayOfString1, paramArrayOfString2))
        {
          paramList.add(new ShareImageItem.ShareImageItemData(localResolveInfo.activityInfo.packageName, localResolveInfo.activityInfo.name, localResolveInfo.loadIcon(localPackageManager), localResolveInfo.loadLabel(localPackageManager).toString()));
          k += 1;
          i = k;
          if (k == paramInt) {
            return k;
          }
        }
      }
      j += 1;
    }
    return k;
  }
  
  public static String getPkgName()
  {
    return CameraApp.getApplication().getPackageName();
  }
  
  private static void getStoreTools(Context paramContext, List<ShareImageItem.ShareImageItemData> paramList, boolean paramBoolean)
  {
    if (paramBoolean) {}
    try
    {
      Object localObject1 = PreferenceConfig.e();
      Object localObject2 = PreferenceConfig.f();
      Object localObject3 = PreferenceConfig.g();
      Object localObject4 = PreferenceConfig.h();
      break label47;
      localObject1 = PreferenceConfig.i();
      localObject2 = PreferenceConfig.j();
      localObject3 = PreferenceConfig.k();
      localObject4 = PreferenceConfig.l();
      label47:
      boolean bool1 = TextUtils.isEmpty((CharSequence)localObject1);
      boolean bool2 = TextUtils.isEmpty((CharSequence)localObject3);
      if ((!bool1) && (!bool2))
      {
        bool1 = isDefaultTools((String)localObject1, (String)localObject2);
        bool2 = isDefaultTools((String)localObject3, (String)localObject4);
        if ((bool1) && (bool2))
        {
          getCountTools(paramContext, paramList, paramBoolean, 2);
          return;
        }
        if ((!bool1) && (!bool2))
        {
          bool1 = getAppIsInstalled(paramContext, (String)localObject1);
          bool2 = getAppIsInstalled(paramContext, (String)localObject3);
          if ((bool1) && (bool2))
          {
            paramContext = paramContext.getPackageManager();
            ActivityInfo localActivityInfo1 = paramContext.getActivityInfo(new ComponentName((String)localObject1, (String)localObject2), 65536);
            ActivityInfo localActivityInfo2 = paramContext.getActivityInfo(new ComponentName((String)localObject3, (String)localObject4), 65536);
            paramList.add(new ShareImageItem.ShareImageItemData((String)localObject1, (String)localObject2, localActivityInfo1.loadIcon(paramContext), localActivityInfo1.loadLabel(paramContext).toString()));
            paramList.add(new ShareImageItem.ShareImageItemData((String)localObject3, (String)localObject4, localActivityInfo2.loadIcon(paramContext), localActivityInfo2.loadLabel(paramContext).toString()));
            return;
          }
          if ((!bool1) && (!bool2))
          {
            getCountTools(paramContext, paramList, paramBoolean, 2);
            return;
          }
          if (bool1)
          {
            localObject3 = paramContext.getPackageManager();
            localObject4 = ((PackageManager)localObject3).getActivityInfo(new ComponentName((String)localObject1, (String)localObject2), 65536);
            paramList.add(new ShareImageItem.ShareImageItemData((String)localObject1, (String)localObject2, ((ActivityInfo)localObject4).loadIcon((PackageManager)localObject3), ((ActivityInfo)localObject4).loadLabel((PackageManager)localObject3).toString()));
            getCountTools(paramContext, paramList, paramBoolean, 1, new String[] { localObject1 }, new String[] { localObject2 });
            return;
          }
          localObject1 = paramContext.getPackageManager();
          localObject2 = ((PackageManager)localObject1).getActivityInfo(new ComponentName((String)localObject3, (String)localObject4), 65536);
          paramList.add(new ShareImageItem.ShareImageItemData((String)localObject3, (String)localObject4, ((ActivityInfo)localObject2).loadIcon((PackageManager)localObject1), ((ActivityInfo)localObject2).loadLabel((PackageManager)localObject1).toString()));
          getCountTools(paramContext, paramList, paramBoolean, 1, new String[] { localObject3 }, new String[] { localObject4 });
          return;
        }
        if (bool1)
        {
          if (getAppIsInstalled(paramContext, (String)localObject3))
          {
            localObject1 = paramContext.getPackageManager();
            localObject2 = ((PackageManager)localObject1).getActivityInfo(new ComponentName((String)localObject3, (String)localObject4), 65536);
            paramList.add(new ShareImageItem.ShareImageItemData((String)localObject3, (String)localObject4, ((ActivityInfo)localObject2).loadIcon((PackageManager)localObject1), ((ActivityInfo)localObject2).loadLabel((PackageManager)localObject1).toString()));
            getCountTools(paramContext, paramList, paramBoolean, 1, new String[] { localObject3 }, new String[] { localObject4 });
            return;
          }
          getCountTools(paramContext, paramList, paramBoolean, 2);
          return;
        }
        if (getAppIsInstalled(paramContext, (String)localObject1))
        {
          localObject3 = paramContext.getPackageManager();
          localObject4 = ((PackageManager)localObject3).getActivityInfo(new ComponentName((String)localObject1, (String)localObject2), 65536);
          paramList.add(new ShareImageItem.ShareImageItemData((String)localObject1, (String)localObject2, ((ActivityInfo)localObject4).loadIcon((PackageManager)localObject3), ((ActivityInfo)localObject4).loadLabel((PackageManager)localObject3).toString()));
          getCountTools(paramContext, paramList, paramBoolean, 1, new String[] { localObject1 }, new String[] { localObject2 });
          return;
        }
        getCountTools(paramContext, paramList, paramBoolean, 2);
        return;
      }
      if ((bool1) && (bool2))
      {
        getCountTools(paramContext, paramList, paramBoolean, 2);
        return;
      }
      if (!bool1)
      {
        if (isDefaultTools((String)localObject1, (String)localObject2))
        {
          getCountTools(paramContext, paramList, paramBoolean, 2);
          return;
        }
        if (getAppIsInstalled(paramContext, (String)localObject1))
        {
          localObject3 = paramContext.getPackageManager();
          localObject4 = ((PackageManager)localObject3).getActivityInfo(new ComponentName((String)localObject1, (String)localObject2), 65536);
          paramList.add(new ShareImageItem.ShareImageItemData((String)localObject1, (String)localObject2, ((ActivityInfo)localObject4).loadIcon((PackageManager)localObject3), ((ActivityInfo)localObject4).loadLabel((PackageManager)localObject3).toString()));
          getCountTools(paramContext, paramList, paramBoolean, 1, new String[] { localObject1 }, new String[] { localObject2 });
          return;
        }
        getCountTools(paramContext, paramList, paramBoolean, 2);
        return;
      }
      if (isDefaultTools((String)localObject3, (String)localObject4))
      {
        getCountTools(paramContext, paramList, paramBoolean, 2);
        return;
      }
      if (getAppIsInstalled(paramContext, (String)localObject3))
      {
        localObject1 = paramContext.getPackageManager();
        localObject2 = ((PackageManager)localObject1).getActivityInfo(new ComponentName((String)localObject3, (String)localObject4), 65536);
        paramList.add(new ShareImageItem.ShareImageItemData((String)localObject3, (String)localObject4, ((ActivityInfo)localObject2).loadIcon((PackageManager)localObject1), ((ActivityInfo)localObject2).loadLabel((PackageManager)localObject1).toString()));
        getCountTools(paramContext, paramList, paramBoolean, 1, new String[] { localObject3 }, new String[] { localObject4 });
        return;
      }
      getCountTools(paramContext, paramList, paramBoolean, 2);
      return;
    }
    catch (Throwable paramContext) {}
  }
  
  @SuppressLint({"NewApi"})
  public static List<ShareImageItem.ShareImageItemData> getTop3ShareTools(Context paramContext, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
  {
    ArrayList localArrayList = new ArrayList();
    Resources localResources = paramContext.getResources();
    if (RegionUtil.c())
    {
      localArrayList.add(new ShareImageItem.ShareImageItemData("com.tencent.mm", "com.tencent.mm.ui.tools.ShareToTimeLineUI", localResources.getDrawable(2131232172), localResources.getString(2131624768)));
      localArrayList.add(new ShareImageItem.ShareImageItemData("com.qzone", "com.qzonex.module.operation.ui.QZonePublishMoodActivity", localResources.getDrawable(2131232171), localResources.getString(2131624647)));
      localArrayList.add(new ShareImageItem.ShareImageItemData("com.sina.weibo", "com.sina.weibo.ComposerDispatchActivity", localResources.getDrawable(2131232173), localResources.getString(2131625017)));
    }
    else
    {
      localArrayList.add(new ShareImageItem.ShareImageItemData("com.facebook.katana", "com.facebook.composer.shareintent.ImplicitShareIntentHandlerDefaultAlias", localResources.getDrawable(2131232161), localResources.getString(2131624322)));
      localArrayList.add(new ShareImageItem.ShareImageItemData("com.whatsapp", "com.whatsapp.ContactPicker", localResources.getDrawable(2131232184), localResources.getString(2131625337)));
      localArrayList.add(new ShareImageItem.ShareImageItemData("com.instagram.android", "com.instagram.android.activity.ShareHandlerActivity", localResources.getDrawable(2131232180), localResources.getString(2131624643)));
    }
    if ((paramInt == 1) && (paramBoolean1))
    {
      localArrayList.add(new ShareImageItem.ShareImageItemData("com.jb.gosms", "com.jb.gosms.ui.ComposeMessageActivity", localResources.getDrawable(2131232165), localResources.getString(2131624448)));
      if ((paramBoolean2) && (Build.VERSION.SDK_INT >= 19)) {
        localArrayList.add(new ShareImageItem.ShareImageItemData("print_local", "", localResources.getDrawable(2131232170), localResources.getString(2131624993)));
      }
      getStoreTools(paramContext, localArrayList, true);
    }
    localArrayList.add(new ShareImageItem.ShareImageItemData(null, null, localResources.getDrawable(2131232182), localResources.getString(2131624693)));
    return localArrayList;
  }
  
  public static boolean isDefaultTools(String paramString1, String paramString2)
  {
    if (RegionUtil.c()) {
      return (("com.tencent.mm".equals(paramString1)) && ("com.tencent.mm.ui.tools.ShareToTimeLineUI".equals(paramString2))) || (("com.qzone".equals(paramString1)) && ("com.qzonex.module.operation.ui.QZonePublishMoodActivity".equals(paramString2))) || (("com.sina.weibo".equals(paramString1)) && ("com.sina.weibo.ComposerDispatchActivity".equals(paramString2))) || (("com.jb.gosms".equals(paramString1)) && ("com.jb.gosms.ui.ComposeMessageActivity".equals(paramString2))) || ("print_local".equals(paramString1));
    }
    return (("com.facebook.katana".equals(paramString1)) && (("com.facebook.composer.shareintent.ImplicitShareIntentHandlerDefaultAlias".equals(paramString2)) || ("com.facebook.composer.shareintent.ImplicitShareIntentHandler".equals(paramString2)))) || (("com.whatsapp".equals(paramString1)) && ("com.whatsapp.ContactPicker".equals(paramString2))) || (("com.instagram.android".equals(paramString1)) && ("com.instagram.android.activity.ShareHandlerActivity".equals(paramString2))) || (("com.jb.gosms".equals(paramString1)) && ("com.jb.gosms.ui.ComposeMessageActivity".equals(paramString2))) || ("print_local".equals(paramString1));
  }
  
  public static boolean isFacebookInstalled(Context paramContext)
  {
    return getAppIsInstalled(paramContext, new String[] { "com.facebook.katana", "com.facebook.lite" });
  }
  
  private static boolean isFilterTools(ResolveInfo paramResolveInfo, String[] paramArrayOfString1, String[] paramArrayOfString2)
  {
    int j = paramArrayOfString1.length;
    String str = paramResolveInfo.activityInfo.packageName;
    paramResolveInfo = paramResolveInfo.activityInfo.name;
    int i = 0;
    while (i < j)
    {
      if ((paramArrayOfString1[i].equals(str)) && (paramArrayOfString2[i].equals(paramResolveInfo))) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  public static boolean startCommonShareActivity(Context paramContext, String paramString1, String paramString2, Uri paramUri, boolean paramBoolean)
  {
    Intent localIntent = new Intent("android.intent.action.SEND");
    boolean bool2 = SdkCheckUtil.b();
    boolean bool1 = true;
    if (bool2) {
      localIntent.addFlags(1);
    }
    localIntent.setComponent(new ComponentName(paramString1, paramString2));
    if (paramBoolean) {
      localIntent.setType("image/*");
    } else {
      localIntent.setType("video/*");
    }
    localIntent.putExtra("android.intent.extra.STREAM", paramUri);
    try
    {
      paramContext.startActivity(localIntent);
      return true;
    }
    catch (Throwable paramString2)
    {
      label161:
      for (;;) {}
    }
    paramString2 = getAllShareTools(paramContext, paramBoolean).iterator();
    while (paramString2.hasNext())
    {
      paramUri = (ShareImageItem.ShareImageItemData)paramString2.next();
      if (paramUri.a().equals(paramString1))
      {
        localIntent.setComponent(new ComponentName(paramUri.a(), paramUri.b()));
        paramBoolean = bool1;
        break label161;
      }
    }
    paramBoolean = false;
    bool1 = paramBoolean;
    if (paramBoolean) {}
    try
    {
      paramContext.startActivity(localIntent);
      return paramBoolean;
    }
    catch (Throwable paramContext)
    {
      for (;;) {}
    }
    bool1 = false;
    return bool1;
  }
  
  public static boolean startCommonShareMutilMediaActivity(Context paramContext, String paramString1, String paramString2, ArrayList<Uri> paramArrayList, int paramInt1, int paramInt2)
  {
    Intent localIntent;
    if (paramArrayList != null)
    {
      if (paramArrayList.size() == 0) {
        return false;
      }
      localIntent = new Intent();
      bool1 = true;
      if ((paramInt1 <= 1) && (paramInt2 <= 1) && ((paramInt1 <= 0) || (paramInt2 <= 0))) {
        localIntent.setAction("android.intent.action.SEND");
      } else {
        localIntent.setAction("android.intent.action.SEND_MULTIPLE");
      }
      localIntent.setComponent(new ComponentName(paramString1, paramString2));
      if ((paramInt1 > 0) && (paramInt2 > 0)) {
        localIntent.setType("media/*");
      } else if (paramInt2 > 0) {
        localIntent.setType("video/*");
      } else {
        localIntent.setType("image/*");
      }
      if (localIntent.getAction().equals("android.intent.action.SEND_MULTIPLE")) {
        localIntent.putParcelableArrayListExtra("android.intent.extra.STREAM", paramArrayList);
      } else {
        localIntent.putExtra("android.intent.extra.STREAM", (Parcelable)paramArrayList.get(0));
      }
    }
    try
    {
      paramContext.startActivity(localIntent);
      return true;
    }
    catch (Throwable paramString2)
    {
      boolean bool2;
      for (;;) {}
    }
    paramString2 = getAllShareMutilMediaTools(paramContext, paramInt1, paramInt2).iterator();
    do
    {
      if (!paramString2.hasNext()) {
        break;
      }
      paramArrayList = (ShareImageItem.ShareImageItemData)paramString2.next();
    } while (!paramArrayList.a().equals(paramString1));
    localIntent.setComponent(new ComponentName(paramArrayList.a(), paramArrayList.b()));
    break label251;
    boolean bool1 = false;
    label251:
    bool2 = bool1;
    if (bool1) {}
    try
    {
      paramContext.startActivity(localIntent);
      return bool1;
    }
    catch (Throwable paramContext)
    {
      for (;;) {}
    }
    bool2 = false;
    return bool2;
    return false;
  }
  
  public static boolean startCommonShareTextActivity(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    Intent localIntent = new Intent("android.intent.action.SEND");
    localIntent.setComponent(new ComponentName(paramString1, paramString2));
    localIntent.setType("text/plain");
    localIntent.putExtra("android.intent.extra.TEXT", paramString3);
    boolean bool = true;
    try
    {
      paramContext.startActivity(localIntent);
      return true;
    }
    catch (Throwable paramString2)
    {
      paramString2.printStackTrace();
      paramString2 = getAllShareTextTools(paramContext).iterator();
      while (paramString2.hasNext())
      {
        paramString3 = (ShareImageItem.ShareImageItemData)paramString2.next();
        if (paramString3.a().equals(paramString1))
        {
          localIntent.setComponent(new ComponentName(paramString3.a(), paramString3.b()));
          break label128;
        }
      }
      bool = false;
    }
    try
    {
      label128:
      paramContext.startActivity(localIntent);
      return bool;
    }
    catch (Throwable paramContext)
    {
      for (;;) {}
    }
    return false;
  }
  
  public static boolean startInstagramPrivateShareActivity(Context paramContext, String paramString1, String paramString2, File paramFile, boolean paramBoolean)
  {
    Intent localIntent;
    if ((paramFile != null) && (paramFile.exists()))
    {
      localIntent = new Intent("android.intent.action.SEND");
      localIntent.setComponent(new ComponentName(paramString1, paramString2));
      if (paramBoolean) {
        localIntent.setType("image/*");
      } else {
        localIntent.setType("video/*");
      }
      localIntent.putExtra("android.intent.extra.STREAM", SdkCheckUtil.a(paramContext, paramFile));
      localIntent.putExtra("android.intent.extra.TEXT", "#XPE");
      bool = true;
    }
    try
    {
      paramContext.startActivity(localIntent);
      return true;
    }
    catch (Throwable paramString2)
    {
      for (;;) {}
    }
    paramString2 = getAllShareTools(paramContext, paramBoolean).iterator();
    while (paramString2.hasNext())
    {
      paramFile = (ShareImageItem.ShareImageItemData)paramString2.next();
      if (paramFile.a().equals(paramString1))
      {
        localIntent.setComponent(new ComponentName(paramFile.a(), paramFile.b()));
        paramBoolean = bool;
        break label171;
      }
    }
    paramBoolean = false;
    label171:
    boolean bool = paramBoolean;
    if (paramBoolean) {}
    try
    {
      paramContext.startActivity(localIntent);
      return paramBoolean;
    }
    catch (Throwable paramContext)
    {
      for (;;) {}
    }
    bool = false;
    return bool;
    return false;
  }
  
  public static boolean startInstagramShareActivity(Context paramContext, String paramString1, String paramString2, Uri paramUri, boolean paramBoolean)
  {
    Intent localIntent = new Intent("android.intent.action.SEND");
    localIntent.setComponent(new ComponentName(paramString1, paramString2));
    if (paramBoolean) {
      localIntent.setType("image/*");
    } else {
      localIntent.setType("video/*");
    }
    localIntent.putExtra("android.intent.extra.STREAM", paramUri);
    localIntent.putExtra("android.intent.extra.TEXT", "#XPE");
    boolean bool = true;
    try
    {
      paramContext.startActivity(localIntent);
      return true;
    }
    catch (Throwable paramString2)
    {
      for (;;) {}
    }
    paramString2 = getAllShareTools(paramContext, paramBoolean).iterator();
    while (paramString2.hasNext())
    {
      paramUri = (ShareImageItem.ShareImageItemData)paramString2.next();
      if (paramUri.a().equals(paramString1))
      {
        localIntent.setComponent(new ComponentName(paramUri.a(), paramUri.b()));
        paramBoolean = bool;
        break label156;
      }
    }
    paramBoolean = false;
    label156:
    bool = paramBoolean;
    if (paramBoolean) {}
    try
    {
      paramContext.startActivity(localIntent);
      return paramBoolean;
    }
    catch (Throwable paramContext)
    {
      for (;;) {}
    }
    bool = false;
    return bool;
  }
  
  public static boolean startPrivateShareActivity(Context paramContext, String paramString1, String paramString2, File paramFile, boolean paramBoolean)
  {
    Intent localIntent;
    if ((paramFile != null) && (paramFile.exists()))
    {
      localIntent = new Intent("android.intent.action.SEND");
      localIntent.setComponent(new ComponentName(paramString1, paramString2));
      if (paramBoolean) {
        localIntent.setType("image/*");
      } else {
        localIntent.setType("video/*");
      }
      localIntent.putExtra("android.intent.extra.STREAM", SdkCheckUtil.a(paramContext, paramFile));
      bool = true;
    }
    try
    {
      paramContext.startActivity(localIntent);
      return true;
    }
    catch (Throwable paramString2)
    {
      for (;;) {}
    }
    paramString2 = getAllShareTools(paramContext, paramBoolean).iterator();
    while (paramString2.hasNext())
    {
      paramFile = (ShareImageItem.ShareImageItemData)paramString2.next();
      if (paramFile.a().equals(paramString1))
      {
        localIntent.setComponent(new ComponentName(paramFile.a(), paramFile.b()));
        paramBoolean = bool;
        break label159;
      }
    }
    paramBoolean = false;
    label159:
    boolean bool = paramBoolean;
    if (paramBoolean) {}
    try
    {
      paramContext.startActivity(localIntent);
      return paramBoolean;
    }
    catch (Throwable paramContext)
    {
      for (;;) {}
    }
    bool = false;
    return bool;
    return false;
  }
  
  public static boolean startShareActivity(Context paramContext, String paramString1, String paramString2, BitmapBean paramBitmapBean)
  {
    return startCommonShareActivity(paramContext, paramString1, paramString2, paramBitmapBean.mUri, MediaTypeUtil.c(paramBitmapBean.mType));
  }
  
  public static boolean startShareTextActivity(Context paramContext, String paramString1, String paramString2, String[] paramArrayOfString)
  {
    return startCommonShareTextActivity(paramContext, paramString1, paramString2, paramArrayOfString[1]);
  }
}
