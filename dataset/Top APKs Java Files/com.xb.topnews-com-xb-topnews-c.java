package com.xb.topnews;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Process;
import android.os.RemoteException;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.CookieManager;
import android.webkit.URLUtil;
import com.google.gson.Gson;
import com.xb.topnews.ad.ssp.bean.SspAdvert;
import com.xb.topnews.ad.ssp.bean.asset.AdObject;
import com.xb.topnews.ad.ssp.bean.asset.AdObject.AdLink;
import com.xb.topnews.ad.ssp.bean.asset.AdObject.AdLink.AdCheckInstall;
import com.xb.topnews.analytics.event.AnalyticsPush;
import com.xb.topnews.analytics.event.AnalyticsPush.PushAction;
import com.xb.topnews.h.aa;
import com.xb.topnews.h.q;
import com.xb.topnews.h.z;
import com.xb.topnews.live.AppPushService;
import com.xb.topnews.live.e;
import com.xb.topnews.live.f;
import com.xb.topnews.net.api.StatisticsAPI;
import com.xb.topnews.net.api.StatisticsAPI.ReadSource;
import com.xb.topnews.net.api.StatisticsAPI.a;
import com.xb.topnews.net.api.StatisticsAPI.b;
import com.xb.topnews.net.api.StatisticsAPI.d;
import com.xb.topnews.net.api.c.a;
import com.xb.topnews.net.api.g.a;
import com.xb.topnews.net.bean.AppConfig.Setting;
import com.xb.topnews.net.bean.Channel;
import com.xb.topnews.net.bean.ILinkSources;
import com.xb.topnews.net.bean.News;
import com.xb.topnews.net.bean.News.AdvertDesc;
import com.xb.topnews.net.bean.News.ItemType;
import com.xb.topnews.net.bean.News.NewsPic;
import com.xb.topnews.net.bean.News.NewsPic.PicItem;
import com.xb.topnews.net.bean.NoticMsg;
import com.xb.topnews.net.bean.NoticMsg.MsgType;
import com.xb.topnews.net.bean.NoticMsg.NoticNews;
import com.xb.topnews.net.bean.RemoteConfig;
import com.xb.topnews.net.bean.RemoteConfig.CommentConfig;
import com.xb.topnews.net.bean.RemoteConfig.KeepLive;
import com.xb.topnews.net.bean.RemoteConfig.WebViewConfig;
import com.xb.topnews.net.bean.SspLinkSources;
import com.xb.topnews.net.bean.User;
import com.xb.topnews.net.bean.User.HomepageType;
import com.xb.topnews.net.core.l;
import com.xb.topnews.stat.a.a;
import com.xb.topnews.views.ImageViewActivity;
import com.xb.topnews.views.MomentsVideoPlayerActivity;
import com.xb.topnews.views.VideoPlayerActivity;
import com.xb.topnews.views.moments.MomentsDetailActivity;
import com.xb.topnews.views.user.BusinessPageActivity;
import com.xb.topnews.views.user.UserPageActivity;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

public final class c
{
  public static float a()
  {
    int i = com.xb.topnews.config.c.n();
    float f = 1.0F;
    switch (i)
    {
    default: 
      return 1.0F;
    case 3: 
      return 1.6F;
    case 2: 
      return 1.3F;
    case 0: 
      f = 0.8F;
    }
    return f;
  }
  
  private static Intent a(Context paramContext, ILinkSources paramILinkSources, String paramString1, String paramString2, boolean paramBoolean)
  {
    if (TextUtils.isEmpty(paramString2)) {
      return null;
    }
    Intent localIntent = com.xb.topnews.net.a.createMapIntent(NewsApplication.a(), paramString2);
    if (localIntent != null) {
      return localIntent;
    }
    if (!paramString2.startsWith("vntopnews:"))
    {
      if (paramString2.startsWith("vntopnewslocal:")) {
        return null;
      }
      if (URLUtil.isNetworkUrl(paramString2)) {
        return v.a(paramContext, paramILinkSources, paramString2, paramString1, paramBoolean);
      }
      if (paramString2.startsWith("intent://"))
      {
        try
        {
          paramILinkSources = Intent.parseUri(paramString2, 1);
          paramILinkSources.addCategory("android.intent.category.BROWSABLE");
          paramILinkSources.setComponent(null);
          if (Build.VERSION.SDK_INT >= 15) {
            paramILinkSources.setSelector(null);
          }
          int i = paramContext.getPackageManager().queryIntentActivities(paramILinkSources, 0).size();
          if (i > 0) {
            return paramILinkSources;
          }
        }
        catch (Exception paramContext)
        {
          paramContext.printStackTrace();
        }
        return null;
      }
      return new Intent("android.intent.action.VIEW", Uri.parse(paramString2));
    }
    return null;
  }
  
  public static Intent a(Context paramContext, User paramUser, c.a paramA)
  {
    if (User.HomepageType.BUSINESS == paramUser.getHomepage()) {
      return BusinessPageActivity.a(paramContext, paramUser, paramA);
    }
    return UserPageActivity.a(paramContext, paramUser, paramA);
  }
  
  public static String a(int paramInt)
  {
    if (paramInt < 10000) {
      return String.valueOf(paramInt);
    }
    return String.format("%.1fk", new Object[] { Float.valueOf(paramInt / 1000.0F) });
  }
  
  public static String a(Context paramContext, int paramInt)
  {
    if (paramInt > 0)
    {
      String str = a(paramInt);
      return paramContext.getResources().getString(2131690088, new Object[] { str });
    }
    return "";
  }
  
  public static String a(Context paramContext, long paramLong)
  {
    if (paramLong <= 0L) {
      return "";
    }
    long l = System.currentTimeMillis() / 1000L - paramLong;
    if (l < 0L) {
      return paramContext.getString(2131689889);
    }
    int j = (int)(l % 2592000L / 86400L);
    int i = 0;
    if (j > 0)
    {
      Date localDate = new Date(paramLong * 1000L);
      paramContext = Calendar.getInstance();
      paramContext.setTime(localDate);
      if (paramContext.get(1) == Calendar.getInstance().get(1)) {
        i = 1;
      }
      if (i != 0) {
        paramContext = new SimpleDateFormat("dd-MM HH:mm", Locale.getDefault());
      } else {
        paramContext = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
      }
      return paramContext.format(localDate);
    }
    i = (int)(l % 86400L / 3600L);
    if (i > 0) {
      return paramContext.getString(2131689886, new Object[] { Integer.valueOf(i) });
    }
    i = (int)(l % 3600L / 60L);
    if (i > 0) {
      return paramContext.getString(2131689887, new Object[] { Integer.valueOf(i) });
    }
    return paramContext.getString(2131689889);
  }
  
  public static String a(String paramString, StatisticsAPI.d paramD)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("utm_source", paramD.destName);
    localHashMap.put("utm_medium", "main_android");
    localHashMap.put("utm_campaign", "client_share");
    paramD = com.xb.topnews.config.c.g();
    try
    {
      localHashMap.put("share", Base64.encodeToString(String.valueOf(Long.parseLong(paramD) ^ 0x1339FFE).getBytes(), 2));
      return a(paramString, localHashMap);
    }
    catch (NumberFormatException paramD)
    {
      for (;;) {}
    }
  }
  
  public static String a(String paramString1, String paramString2, String paramString3)
  {
    if (!TextUtils.isEmpty(paramString2))
    {
      if (TextUtils.isEmpty(paramString3)) {
        return paramString1;
      }
      StringBuilder localStringBuilder = new StringBuilder(2048);
      localStringBuilder.append(paramString1);
      if (!paramString1.contains("?")) {
        localStringBuilder.append("?");
      } else {
        localStringBuilder.append("&");
      }
      try
      {
        localStringBuilder.append(URLEncoder.encode(paramString2, "UTF-8"));
        localStringBuilder.append("=");
        localStringBuilder.append(URLEncoder.encode(paramString3, "UTF-8"));
      }
      catch (UnsupportedEncodingException paramString1)
      {
        paramString1.printStackTrace();
      }
      return localStringBuilder.toString();
    }
    return paramString1;
  }
  
  public static String a(String paramString, Map<String, String> paramMap)
  {
    StringBuilder localStringBuilder = new StringBuilder(2048);
    localStringBuilder.append(paramString);
    if (!paramString.contains("?")) {
      localStringBuilder.append("?");
    } else if (!paramString.endsWith("&")) {
      localStringBuilder.append("&");
    }
    paramMap = paramMap.entrySet().iterator();
    for (int i = 1; paramMap.hasNext(); i = 0)
    {
      if (i == 0) {
        localStringBuilder.append("&");
      }
      paramString = (Map.Entry)paramMap.next();
      try
      {
        localStringBuilder.append(URLEncoder.encode((String)paramString.getKey(), "UTF-8"));
        localStringBuilder.append("=");
        if (paramString.getValue() == null) {
          paramString = "";
        } else {
          paramString = (String)paramString.getValue();
        }
        localStringBuilder.append(URLEncoder.encode(paramString, "UTF-8"));
      }
      catch (UnsupportedEncodingException paramString)
      {
        paramString.printStackTrace();
      }
    }
    return localStringBuilder.toString();
  }
  
  public static void a(Activity paramActivity, ILinkSources paramILinkSources, String paramString1, String paramString2, boolean paramBoolean)
  {
    a(paramActivity, paramILinkSources, paramString1, paramString2, paramBoolean, null);
  }
  
  private static void a(Activity paramActivity, ILinkSources paramILinkSources, String paramString1, String paramString2, boolean paramBoolean, Integer paramInteger)
  {
    if (TextUtils.isEmpty(paramString2)) {
      return;
    }
    Activity localActivity = paramActivity;
    if (paramActivity == null) {
      localActivity = NewsApplication.a().c();
    }
    if (localActivity == null) {
      return;
    }
    paramActivity = a(localActivity, paramILinkSources, paramString1, paramString2, paramBoolean);
    if (paramActivity == null) {
      return;
    }
    if (paramInteger != null) {
      com.xb.topnews.stat.a.a(paramActivity, paramInteger);
    }
    try
    {
      localActivity.startActivity(paramActivity);
      return;
    }
    catch (Exception paramActivity)
    {
      Log.e("DeepLink", paramActivity.getMessage(), paramActivity);
    }
  }
  
  public static void a(Activity paramActivity, News paramNews, Channel paramChannel, StatisticsAPI.ReadSource paramReadSource, View paramView, String paramString)
  {
    if (paramActivity == null) {
      return;
    }
    NewsApplication.a();
    NewsApplication.g();
    FileOutputStream localFileOutputStream = null;
    Object localObject1;
    if (paramChannel != null) {
      localObject1 = paramChannel.getCid();
    } else {
      localObject1 = null;
    }
    Object localObject2;
    if (paramNews.isMoments()) {
      localObject2 = g.a.MOMENTS;
    } else {
      localObject2 = null;
    }
    StatisticsAPI.a((String)localObject1, (g.a)localObject2, paramNews.getContentId(), paramNews.getAlg(), paramNews.getSessionId(), paramReadSource);
    int i = 1;
    paramNews.setRead(true);
    if (paramNews.getLinkType() == 1)
    {
      a(paramActivity, "", paramNews.getLink(), paramNews.isLinkTrusted());
      return;
    }
    com.xb.topnews.b.b.a().h();
    if (paramNews.getItemType() != null) {
      localObject2 = paramNews.getItemType();
    } else {
      localObject2 = News.ItemType.SMALL_IMG;
    }
    switch (c.2.a[localObject2.ordinal()])
    {
    default: 
      paramNews = v.a(paramActivity, paramChannel, paramNews);
      if (paramReadSource != null) {
        paramNews.putExtra("extra.read_src", paramReadSource.ordinal());
      }
      break;
    case 5: 
    case 6: 
      paramNews = VideoPlayerActivity.a(paramActivity, paramChannel, paramNews);
      if (paramReadSource != null) {
        paramNews.putExtra("extra.read_src", paramReadSource.ordinal());
      }
      paramActivity.startActivity(paramNews);
      return;
    case 2: 
    case 3: 
    case 4: 
      paramChannel = localFileOutputStream;
      if (paramView != null)
      {
        localObject2 = new int[4];
        paramView.getLocationOnScreen((int[])localObject2);
        localObject2[2] = paramView.getWidth();
        localObject2[3] = paramView.getHeight();
        paramChannel = (Channel)localObject2;
        if (paramString == null) {
          try
          {
            paramView.setDrawingCacheEnabled(true);
            paramChannel = paramView.getDrawingCache();
            paramView = new File(paramActivity.getCacheDir(), "preview");
            if (paramView.exists()) {
              paramView.delete();
            }
            localFileOutputStream = new FileOutputStream(paramView);
            paramChannel.compress(Bitmap.CompressFormat.PNG, 100, localFileOutputStream);
            localFileOutputStream.close();
            paramView = Uri.fromFile(paramView).toString();
            paramChannel = (Channel)localObject2;
          }
          catch (Exception paramChannel)
          {
            paramChannel.printStackTrace();
            paramChannel = (Channel)localObject2;
          }
        }
      }
      paramView = paramString;
      localObject1 = ImageViewActivity.a(paramActivity, (String)localObject1, paramNews);
      if ((!paramNews.isSinglePic()) || (!paramNews.getPics()[0].isLongPic())) {
        i = 0;
      }
      if ((i == 0) && (paramChannel != null) && (paramString != null))
      {
        ((Intent)localObject1).putExtra("location", paramChannel);
        ((Intent)localObject1).putExtra("transition_image", paramView);
      }
      if (paramReadSource != null) {
        ((Intent)localObject1).putExtra("extra.read_src", paramReadSource.ordinal());
      }
      paramActivity.startActivity((Intent)localObject1);
      paramActivity.overridePendingTransition(0, 0);
      return;
    case 1: 
      paramActivity.startActivity(MomentsDetailActivity.a(paramActivity, paramNews, paramReadSource));
      return;
    }
    if (u.a(paramActivity.getApplicationContext()).h().isMultiProcess())
    {
      paramActivity.startActivityForResult(paramNews, 1700);
      return;
    }
    paramActivity.startActivity(paramNews);
  }
  
  public static void a(Activity paramActivity, News paramNews, Channel paramChannel, StatisticsAPI.ReadSource paramReadSource, boolean paramBoolean)
  {
    if (paramActivity == null) {
      return;
    }
    NewsApplication.a();
    NewsApplication.g();
    Object localObject = null;
    String str;
    if (paramChannel != null) {
      str = paramChannel.getCid();
    } else {
      str = null;
    }
    if (paramNews.isMoments()) {
      localObject = g.a.MOMENTS;
    }
    StatisticsAPI.a(str, (g.a)localObject, paramNews.getContentId(), paramNews.getAlg(), paramNews.getSessionId(), paramReadSource);
    paramNews.setRead(true);
    if ((paramNews.getLinkType() != 1) && (g.a.LINK != paramNews.getContentType()))
    {
      com.xb.topnews.b.b.a().h();
      if (paramNews.getItemType() != null) {
        localObject = paramNews.getItemType();
      } else {
        localObject = News.ItemType.SMALL_IMG;
      }
      switch (c.2.a[localObject.ordinal()])
      {
      default: 
        paramChannel = v.a(paramActivity, paramChannel, paramNews);
        if (paramBoolean) {
          paramChannel.setAction("action.show_comment");
        }
        break;
      case 5: 
      case 6: 
        paramNews = VideoPlayerActivity.a(paramActivity, paramChannel, paramNews);
        if (paramBoolean) {
          paramNews.setAction("action.show_comment");
        }
        if (paramReadSource != null) {
          paramNews.putExtra("extra.read_src", paramReadSource.ordinal());
        }
        paramActivity.startActivity(paramNews);
        return;
      case 2: 
      case 3: 
      case 4: 
        paramNews = ImageViewActivity.a(paramActivity, str, paramNews);
        if (paramReadSource != null) {
          paramNews.putExtra("extra.read_src", paramReadSource.ordinal());
        }
        paramActivity.startActivity(paramNews);
        paramActivity.overridePendingTransition(0, 0);
        return;
      case 1: 
        if (paramNews.isVideoMoments())
        {
          paramNews = MomentsVideoPlayerActivity.a(NewsApplication.a().getApplicationContext(), paramChannel, paramNews);
          break label361;
        }
        if (paramBoolean) {
          paramNews = MomentsDetailActivity.a(paramActivity, paramNews, paramReadSource, "comment");
        } else {
          paramNews = MomentsDetailActivity.a(paramActivity, paramNews, paramReadSource);
        }
        paramActivity.startActivity(paramNews);
        return;
      }
      if (paramReadSource != null) {
        paramChannel.putExtra("extra.read_src", paramReadSource.ordinal());
      }
      paramNews = paramChannel;
      if (u.a(paramActivity.getApplicationContext()).h().isMultiProcess())
      {
        paramActivity.startActivityForResult(paramChannel, 1700);
        return;
      }
      label361:
      paramActivity.startActivity(paramNews);
      return;
    }
    a(paramActivity, "", paramNews.getLink(), paramNews.isLinkTrusted());
  }
  
  public static void a(Activity paramActivity, NoticMsg paramNoticMsg)
  {
    Object localObject = new AnalyticsPush(paramNoticMsg.getSource());
    ((AnalyticsPush)localObject).network = q.a(paramActivity);
    ((AnalyticsPush)localObject).action = AnalyticsPush.PushAction.OPEN;
    ((AnalyticsPush)localObject).appOpened = NewsApplication.a().b();
    ((AnalyticsPush)localObject).msgId = paramNoticMsg.getMsgId();
    com.xb.topnews.analytics.b.c((com.xb.topnews.analytics.a)localObject);
    localObject = paramNoticMsg.getNoticNews();
    if ((NoticMsg.MsgType.NEWS == paramNoticMsg.getMsgType()) && (localObject != null))
    {
      StatisticsAPI.a(null, null, ((NoticMsg.NoticNews)localObject).getContentId(), -1, null, StatisticsAPI.ReadSource.PUSH);
      paramNoticMsg = v.a(paramActivity, ((NoticMsg.NoticNews)localObject).getContentId(), null, paramNoticMsg.getText(), paramNoticMsg.getLink());
      paramNoticMsg.putExtra("extra.read_src", StatisticsAPI.ReadSource.PUSH.ordinal());
      com.xb.topnews.stat.a.a(paramNoticMsg, Integer.valueOf(a.a.b.a.hashCode()));
      paramActivity.startActivity(paramNoticMsg);
      paramActivity.overridePendingTransition(0, 0);
      return;
    }
    a(paramActivity, null, null, paramNoticMsg.getLink(), true, Integer.valueOf(a.a.b.a.hashCode()));
  }
  
  public static void a(Activity paramActivity, com.xb.topnews.ui.b paramB, News paramNews, int paramInt)
  {
    Object localObject2 = null;
    Object localObject1;
    if (paramB != null)
    {
      localObject1 = new int[4];
      paramB.getLocationOnScreen((int[])localObject1);
      localObject1[2] = paramB.getWidth();
      localObject1[3] = paramB.getHeight();
      localObject2 = paramB.getCoverUrl();
      paramB = (com.xb.topnews.ui.b)localObject1;
      localObject1 = localObject2;
    }
    else
    {
      localObject1 = null;
      paramB = (com.xb.topnews.ui.b)localObject2;
    }
    paramNews = paramNews.getPics();
    localObject2 = new String[paramNews.length];
    String[] arrayOfString = new String[paramNews.length];
    int i = 0;
    while (i < paramNews.length)
    {
      localObject2[i] = paramNews[i].getCoverThumb();
      if (paramNews[i].getMp4() != null) {
        arrayOfString[i] = paramNews[i].getMp4().getUrl();
      } else {
        arrayOfString[i] = paramNews[i].getLarge().getUrl();
      }
      i += 1;
    }
    paramNews = ImageViewActivity.a(paramActivity, (String[])localObject2, arrayOfString, paramInt);
    if ((paramB != null) && (localObject1 != null))
    {
      paramNews.putExtra("location", paramB);
      paramNews.putExtra("transition_image", (String)localObject1);
    }
    paramActivity.startActivity(paramNews);
    paramActivity.overridePendingTransition(0, 0);
  }
  
  public static void a(Activity paramActivity, String paramString1, String paramString2, boolean paramBoolean)
  {
    a(paramActivity, null, paramString1, paramString2, paramBoolean, null);
  }
  
  public static void a(android.support.v7.app.d paramD)
  {
    Object localObject = paramD.getTheme().obtainStyledAttributes(new int[] { 2130968734, 2130968735 });
    int i = ((TypedArray)localObject).getColor(0, paramD.getResources().getColor(2131099835));
    int j = ((TypedArray)localObject).getColor(1, paramD.getResources().getColor(2131099836));
    ((TypedArray)localObject).recycle();
    localObject = paramD.getSupportActionBar();
    if (localObject != null) {
      ((android.support.v7.app.a)localObject).a(new ColorDrawable(i));
    }
    if (Build.VERSION.SDK_INT >= 21)
    {
      paramD = paramD.getWindow();
      if (j != 0)
      {
        paramD.clearFlags(67108864);
        paramD.setStatusBarColor(j);
        return;
      }
      paramD.addFlags(67108864);
    }
  }
  
  public static void a(android.support.v7.app.d paramD, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      paramD.getWindow().setNavigationBarColor(paramInt);
    }
  }
  
  public static void a(SspAdvert paramSspAdvert)
  {
    Activity localActivity = NewsApplication.a().c();
    if (localActivity == null) {
      return;
    }
    Object localObject = paramSspAdvert.getAdObject().getLink();
    String str = ((AdObject.AdLink)localObject).getUrl();
    boolean bool = ((AdObject.AdLink)localObject).isTrusted();
    a(localActivity, new SspLinkSources(0, paramSspAdvert.getCrid()), "", str, bool);
    localObject = ((AdObject.AdLink)localObject).getCheckInstall();
    if (localObject != null) {
      com.xb.topnews.ad.a.c.a(localActivity.getApplicationContext()).a(paramSspAdvert.getOrderId(), ((AdObject.AdLink.AdCheckInstall)localObject).getPackageName(), ((AdObject.AdLink.AdCheckInstall)localObject).getTimeout(), ((AdObject.AdLink.AdCheckInstall)localObject).getFreq());
    }
  }
  
  public static void a(News paramNews, Channel paramChannel, StatisticsAPI.ReadSource paramReadSource)
  {
    a(NewsApplication.a().c(), paramNews, paramChannel, paramReadSource, false);
  }
  
  public static void a(News paramNews, String paramString, StatisticsAPI.b paramB)
  {
    Activity localActivity = NewsApplication.a().c();
    if (localActivity == null) {
      return;
    }
    boolean bool = true;
    paramNews.setRead(true);
    News.AdvertDesc localAdvertDesc = paramNews.getAdvertDesc();
    String str1 = localAdvertDesc.getAdvertId();
    String str2 = localAdvertDesc.getGroupId();
    StatisticsAPI.a(paramString, paramNews.getContentId(), str1, str2, paramNews.getAlg(), paramB, StatisticsAPI.a.CONTENT, paramNews.getClickId());
    paramNews = localAdvertDesc.getLink();
    if (localAdvertDesc.getTrusted() <= 0) {
      bool = false;
    }
    a(localActivity, "", paramNews, bool);
  }
  
  public static void a(RemoteConfig.KeepLive paramKeepLive)
  {
    RemoteConfig.KeepLive localKeepLive = paramKeepLive;
    if (paramKeepLive == null) {
      localKeepLive = u.b();
    }
    Object localObject = new com.xb.topnews.live.b();
    ((com.xb.topnews.live.b)localObject).a = localKeepLive.isKeepLive();
    ((com.xb.topnews.live.b)localObject).b = localKeepLive.getBlockSuspendTime();
    ((com.xb.topnews.live.b)localObject).c = localKeepLive.getBlockSuspendShowTime();
    ((com.xb.topnews.live.b)localObject).d = localKeepLive.isBlockKill();
    ((com.xb.topnews.live.b)localObject).e = localKeepLive.isBlockService();
    com.xb.topnews.live.d localD = com.xb.topnews.live.d.a();
    paramKeepLive = NewsApplication.a();
    long l = com.xb.topnews.live.d.a().f();
    boolean bool;
    if (((com.xb.topnews.live.b)localObject).b != l) {
      bool = true;
    } else {
      bool = false;
    }
    localD.a = ((com.xb.topnews.live.b)localObject);
    if ((com.xb.topnews.live.d.d(paramKeepLive)) && (bool))
    {
      localObject = new StringBuilder("blockSuspendTime change from ");
      ((StringBuilder)localObject).append(l);
      ((StringBuilder)localObject).append(" to ");
      ((StringBuilder)localObject).append(com.xb.topnews.live.d.a().f());
      ((StringBuilder)localObject).append(", startTaskTimer");
      com.xb.topnews.live.g.a(paramKeepLive);
    }
    else
    {
      localObject = new StringBuilder("processName: ");
      ((StringBuilder)localObject).append(com.xb.topnews.live.d.l());
      ((StringBuilder)localObject).append(", blockSuspendTimeChanged: ");
      ((StringBuilder)localObject).append(bool);
      ((StringBuilder)localObject).append(", don't startTaskTimer");
    }
    if (com.xb.topnews.live.d.d(paramKeepLive))
    {
      if (com.xb.topnews.live.d.a().a.a)
      {
        if (com.xb.topnews.live.d.c(paramKeepLive)) {
          com.xb.topnews.live.d.a(paramKeepLive);
        }
      }
      else if (AppPushService.a()) {
        paramKeepLive.stopService(new Intent(paramKeepLive, AppPushService.class));
      }
      if (com.xb.topnews.live.d.a().i())
      {
        com.xb.topnews.live.d.a().k();
      }
      else
      {
        paramKeepLive = com.xb.topnews.live.d.a();
        if (paramKeepLive.d != null)
        {
          paramKeepLive = paramKeepLive.d;
          if (paramKeepLive.c != null) {
            try
            {
              int i = paramKeepLive.c.a();
              localObject = new StringBuilder("kill block service process, myPid:");
              ((StringBuilder)localObject).append(Process.myPid());
              ((StringBuilder)localObject).append(", blockPid:");
              ((StringBuilder)localObject).append(i);
              e.a("KeepLive Process", ((StringBuilder)localObject).toString());
              if ((paramKeepLive.c == null) && (!paramKeepLive.b))
              {
                e.a("KeepLive Process", "unbindService, already unbinded");
              }
              else
              {
                e.a("KeepLive Process", "unbindService");
                paramKeepLive.a.unbindService(paramKeepLive);
              }
              paramKeepLive.c = null;
              paramKeepLive.b = false;
              Process.killProcess(i);
            }
            catch (RemoteException paramKeepLive)
            {
              paramKeepLive.printStackTrace();
            }
          }
          if (paramKeepLive.b)
          {
            e.a("KeepLive Process", "killBlockProcess, unbindService");
            paramKeepLive.a.unbindService(paramKeepLive);
          }
          else
          {
            e.a("KeepLive Process", "killBlockProcess, not binded");
          }
        }
      }
    }
    if (aa.f(NewsApplication.a()))
    {
      com.xb.topnews.fcm.a.c.a(localKeepLive.getReshowNotifyTimeout());
      com.xb.topnews.fcm.a.c.a(localKeepLive.getReshowNotifyCount());
      com.xb.topnews.fcm.a.c.b(localKeepLive.getNotifyMaxWhenBoot());
    }
  }
  
  public static void a(String paramString)
  {
    Object localObject3 = new HashMap();
    if (com.xb.topnews.config.c.p()) {
      localObject1 = "night";
    } else {
      localObject1 = "default";
    }
    ((Map)localObject3).put("theme", localObject1);
    ((Map)localObject3).put("client", j.f);
    ((Map)localObject3).put("version", "3.4.1");
    ((Map)localObject3).put("flavor", "main");
    ((Map)localObject3).put("uid", com.xb.topnews.config.c.g());
    ((Map)localObject3).put("did", com.xb.topnews.config.c.E());
    ((Map)localObject3).put("login_token", com.xb.topnews.config.c.t());
    Object localObject1 = null;
    try
    {
      String str = z.b(paramString);
      localObject1 = str;
    }
    catch (MalformedURLException localMalformedURLException)
    {
      localMalformedURLException.printStackTrace();
    }
    if (localObject1 == null) {
      return;
    }
    Object localObject2 = new ArrayList();
    localObject3 = ((Map)localObject3).entrySet().iterator();
    while (((Iterator)localObject3).hasNext())
    {
      Map.Entry localEntry = (Map.Entry)((Iterator)localObject3).next();
      ((List)localObject2).add(String.format("%s=%s; Domain=%s; Path = /", new Object[] { (String)localEntry.getKey(), (String)localEntry.getValue(), localObject1 }));
    }
    localObject1 = NewsApplication.a().getApplicationContext();
    if ((u.b((Context)localObject1)) && (!aa.g((Context)localObject1)))
    {
      localObject1 = ((List)localObject2).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (String)((Iterator)localObject1).next();
        CookieManager.getInstance().setCookie(paramString, (String)localObject2);
      }
      aa.c();
      return;
    }
    NewsApplication.a().a(paramString, (List)localObject2);
    new StringBuilder("read cookie: ").append(NewsApplication.a().a(paramString));
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramString, 0);
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;) {}
    }
    paramContext = null;
    return paramContext != null;
  }
  
  public static boolean a(User paramUser1, User paramUser2)
  {
    return (paramUser1 != null) && (paramUser2 != null) && (paramUser1.getId() == paramUser2.getId());
  }
  
  public static Channel[] a(Context paramContext)
  {
    for (;;)
    {
      try
      {
        paramContext = new Scanner(paramContext.getAssets().open("default_channels.json")).useDelimiter("\\A");
        if (paramContext.hasNext())
        {
          paramContext = paramContext.next();
          paramContext = (Channel[])com.xb.topnews.net.core.g.a.fromJson(paramContext, [Lcom.xb.topnews.net.bean.Channel.class);
          return paramContext;
        }
      }
      catch (IOException paramContext)
      {
        paramContext.printStackTrace();
        return null;
      }
      paramContext = "";
    }
  }
  
  public static String b(Context paramContext)
  {
    Object localObject = b(paramContext, 1);
    paramContext = new ArrayList();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
      if ((localPackageInfo.applicationInfo.flags & 0x1) == 0)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(localPackageInfo.packageName);
        localStringBuilder.append(localPackageInfo.versionName);
        paramContext.add(localStringBuilder.toString());
      }
    }
    Collections.sort(paramContext);
    localObject = new StringBuilder();
    paramContext = paramContext.iterator();
    while (paramContext.hasNext()) {
      ((StringBuilder)localObject).append((String)paramContext.next());
    }
    return l.a(((StringBuilder)localObject).toString());
  }
  
  public static String b(String paramString)
  {
    HashMap localHashMap = new HashMap();
    AppConfig.Setting localSetting = com.xb.topnews.config.c.I();
    if ((localSetting != null) && (localSetting.isPending())) {
      localHashMap.put("pending", "1");
    }
    localHashMap.put("uid", com.xb.topnews.config.c.g());
    localHashMap.put("client", j.f);
    localHashMap.put("version", "3.4.1");
    localHashMap.put("did", j.a);
    localHashMap.put("unique_id", com.xb.topnews.config.c.F());
    localHashMap.put("flavor", "main");
    return a(paramString, localHashMap);
  }
  
  /* Error */
  public static List<PackageInfo> b(Context paramContext, int paramInt)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: aload_0
    //   4: invokevirtual 89	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   7: astore_3
    //   8: aload_3
    //   9: iload_1
    //   10: invokevirtual 1237	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   13: astore_0
    //   14: ldc 2
    //   16: monitorexit
    //   17: aload_0
    //   18: areturn
    //   19: astore_0
    //   20: aload_0
    //   21: invokevirtual 104	java/lang/Exception:printStackTrace	()V
    //   24: new 1101	java/util/ArrayList
    //   27: dup
    //   28: invokespecial 1102	java/util/ArrayList:<init>	()V
    //   31: astore 5
    //   33: aconst_null
    //   34: astore 4
    //   36: aconst_null
    //   37: astore_2
    //   38: aload_2
    //   39: astore_0
    //   40: invokestatic 1243	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   43: ldc_w 1245
    //   46: invokevirtual 1249	java/lang/Runtime:exec	(Ljava/lang/String;)Ljava/lang/Process;
    //   49: astore 6
    //   51: aload_2
    //   52: astore_0
    //   53: new 1251	java/io/BufferedReader
    //   56: dup
    //   57: new 1253	java/io/InputStreamReader
    //   60: dup
    //   61: aload 6
    //   63: invokevirtual 1259	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   66: invokespecial 1260	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   69: invokespecial 1263	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   72: astore_2
    //   73: aload_2
    //   74: invokevirtual 1266	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   77: astore_0
    //   78: aload_0
    //   79: ifnull +31 -> 110
    //   82: aload 5
    //   84: aload_3
    //   85: aload_0
    //   86: aload_0
    //   87: bipush 58
    //   89: invokevirtual 1269	java/lang/String:indexOf	(I)I
    //   92: iconst_1
    //   93: iadd
    //   94: invokevirtual 1272	java/lang/String:substring	(I)Ljava/lang/String;
    //   97: iload_1
    //   98: invokevirtual 1137	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   101: invokeinterface 1108 2 0
    //   106: pop
    //   107: goto -34 -> 73
    //   110: aload 6
    //   112: invokevirtual 1275	java/lang/Process:waitFor	()I
    //   115: pop
    //   116: aload_2
    //   117: invokevirtual 1276	java/io/BufferedReader:close	()V
    //   120: goto +52 -> 172
    //   123: astore_0
    //   124: aload_0
    //   125: invokevirtual 1184	java/io/IOException:printStackTrace	()V
    //   128: goto +44 -> 172
    //   131: astore_3
    //   132: aload_2
    //   133: astore_0
    //   134: aload_3
    //   135: astore_2
    //   136: goto +42 -> 178
    //   139: astore_3
    //   140: goto +11 -> 151
    //   143: astore_2
    //   144: goto +34 -> 178
    //   147: astore_3
    //   148: aload 4
    //   150: astore_2
    //   151: aload_2
    //   152: astore_0
    //   153: aload_3
    //   154: invokevirtual 104	java/lang/Exception:printStackTrace	()V
    //   157: aload_2
    //   158: ifnull +14 -> 172
    //   161: aload_2
    //   162: invokevirtual 1276	java/io/BufferedReader:close	()V
    //   165: goto +7 -> 172
    //   168: astore_0
    //   169: goto -45 -> 124
    //   172: ldc 2
    //   174: monitorexit
    //   175: aload 5
    //   177: areturn
    //   178: aload_0
    //   179: ifnull +15 -> 194
    //   182: aload_0
    //   183: invokevirtual 1276	java/io/BufferedReader:close	()V
    //   186: goto +8 -> 194
    //   189: astore_0
    //   190: aload_0
    //   191: invokevirtual 1184	java/io/IOException:printStackTrace	()V
    //   194: aload_2
    //   195: athrow
    //   196: astore_0
    //   197: ldc 2
    //   199: monitorexit
    //   200: aload_0
    //   201: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	202	0	paramContext	Context
    //   0	202	1	paramInt	int
    //   37	99	2	localObject1	Object
    //   143	1	2	localObject2	Object
    //   150	45	2	localObject3	Object
    //   7	78	3	localPackageManager	PackageManager
    //   131	4	3	localObject4	Object
    //   139	1	3	localException1	Exception
    //   147	7	3	localException2	Exception
    //   34	115	4	localObject5	Object
    //   31	145	5	localArrayList	ArrayList
    //   49	62	6	localProcess	Process
    // Exception table:
    //   from	to	target	type
    //   8	14	19	java/lang/Exception
    //   116	120	123	java/io/IOException
    //   73	78	131	finally
    //   82	107	131	finally
    //   110	116	131	finally
    //   73	78	139	java/lang/Exception
    //   82	107	139	java/lang/Exception
    //   110	116	139	java/lang/Exception
    //   40	51	143	finally
    //   53	73	143	finally
    //   153	157	143	finally
    //   40	51	147	java/lang/Exception
    //   53	73	147	java/lang/Exception
    //   161	165	168	java/io/IOException
    //   182	186	189	java/io/IOException
    //   3	8	196	finally
    //   8	14	196	finally
    //   20	33	196	finally
    //   116	120	196	finally
    //   124	128	196	finally
    //   161	165	196	finally
    //   182	186	196	finally
    //   190	194	196	finally
    //   194	196	196	finally
  }
  
  public static void b(Context paramContext, User paramUser, c.a paramA)
  {
    paramUser = a(paramContext, paramUser, paramA);
    paramUser.addFlags(268435456);
    paramContext.startActivity(paramUser);
  }
  
  public static String c(Context paramContext)
  {
    if (ContextCompat.checkSelfPermission(paramContext, "android.permission.GET_ACCOUNTS") == 0)
    {
      paramContext = ((AccountManager)paramContext.getSystemService("account")).getAccounts();
      int j = paramContext.length;
      int i = 0;
      while (i < j)
      {
        Object localObject = paramContext[i];
        if (TextUtils.equals(localObject.type, "com.facebook.auth.login")) {
          return localObject.name;
        }
        i += 1;
      }
    }
    return null;
  }
  
  public static String c(String paramString)
  {
    try
    {
      String str = URLDecoder.decode(paramString);
      return str;
    }
    catch (Exception localException) {}
    return paramString;
  }
  
  public static boolean d(Context paramContext)
  {
    if (com.xb.topnews.config.c.z()) {
      return false;
    }
    paramContext = u.a(paramContext).a;
    if (paramContext != null) {
      paramContext = paramContext.getCommentConfig();
    } else {
      paramContext = null;
    }
    return (paramContext == null) || (paramContext.isNeedSignAgreement());
  }
  
  public static boolean d(String paramString)
  {
    return (paramString.endsWith(".jpg")) || (paramString.endsWith(".png")) || (paramString.endsWith(".jpeg")) || (paramString.endsWith(".gif")) || (paramString.endsWith(".bmp"));
  }
}
