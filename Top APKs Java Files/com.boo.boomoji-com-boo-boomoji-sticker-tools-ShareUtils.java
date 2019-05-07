package com.boo.boomoji.sticker.tools;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build.VERSION;
import android.support.v4.content.FileProvider;
import com.boo.boomoji.Controller.AppUpdataController.InterfaceManagement;
import com.boo.boomoji.Controller.BooMojiApplication;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ShareUtils
{
  public static final String FACEBOOK = "com.facebook.katana";
  public static final String INSTAGRAM = "com.instagram.android";
  public static final String MUSICLY = "com.zhiliaoapp.musically";
  public static final String SNAPCHAT = "com.snapchat.android";
  public static final String TWITTER = "com.twitter.android";
  
  public ShareUtils() {}
  
  private static boolean isAppInstalled(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    int i = 0;
    paramContext = paramContext.getInstalledPackages(0);
    ArrayList localArrayList = new ArrayList();
    if (paramContext != null) {
      while (i < paramContext.size())
      {
        localArrayList.add(((PackageInfo)paramContext.get(i)).packageName);
        i += 1;
      }
    }
    return localArrayList.contains(paramString);
  }
  
  public static void shareSns(String paramString1, boolean paramBoolean, String paramString2, Activity paramActivity)
  {
    if (!new InterfaceManagement().isNetworkConnected(BooMojiApplication.getAppContext())) {
      return;
    }
    if (paramString1.equals("send_to_instagram")) {
      return;
    }
    if (paramString1.equals("send_to_musical.ly")) {}
    try
    {
      paramActivity.startActivityForResult(paramActivity.getPackageManager().getLaunchIntentForPackage("com.zhiliaoapp.musically"), 100);
      return;
    }
    catch (Exception paramString1)
    {
      Intent localIntent;
      return;
    }
    if (paramString1.equals("send_to_snapchat")) {}
    try
    {
      paramActivity.startActivityForResult(paramActivity.getPackageManager().getLaunchIntentForPackage("com.snapchat.android"), 100);
      return;
    }
    catch (Exception paramString1) {}
    if (paramString1.equals("send_to_facebook"))
    {
      if (!isAppInstalled(paramActivity, "com.facebook.katana")) {
        return;
      }
      localIntent = new Intent("android.intent.action.SEND");
      paramString1 = new File(paramString2);
      if ((paramString1 != null) && (paramString1.exists()) && (paramString1.isFile()))
      {
        if (paramBoolean) {
          localIntent.setType("image/*");
        } else {
          localIntent.setType("video/*");
        }
        if (Build.VERSION.SDK_INT >= 26) {
          paramString1 = FileProvider.getUriForFile(paramActivity, "com.boo.chat.fileprovider", paramString1);
        } else {
          paramString1 = Uri.fromFile(paramString1);
        }
        localIntent.putExtra("android.intent.extra.STREAM", paramString1);
      }
      localIntent.putExtra("android.intent.extra.SUBJECT", "");
      localIntent.putExtra("android.intent.extra.TEXT", "");
      localIntent.setFlags(268435456);
      localIntent.setPackage("com.facebook.katana");
      paramActivity.startActivityForResult(Intent.createChooser(localIntent, "Boo"), 100);
      return;
    }
    if (paramString1.equals("send_to_twitter"))
    {
      if (!isAppInstalled(paramActivity, "com.twitter.android")) {
        return;
      }
      localIntent = new Intent("android.intent.action.SEND");
      paramString1 = new File(paramString2);
      if ((paramString1 != null) && (paramString1.exists()) && (paramString1.isFile()))
      {
        if (paramBoolean) {
          localIntent.setType("image/*");
        } else {
          localIntent.setType("video/*");
        }
        if (Build.VERSION.SDK_INT >= 26) {
          paramString1 = FileProvider.getUriForFile(paramActivity, "com.boo.chat.fileprovider", paramString1);
        } else {
          paramString1 = Uri.fromFile(paramString1);
        }
        localIntent.putExtra("android.intent.extra.STREAM", paramString1);
      }
      localIntent.putExtra("android.intent.extra.SUBJECT", "");
      localIntent.putExtra("android.intent.extra.TEXT", "");
      localIntent.setFlags(268435456);
      localIntent.setPackage("com.twitter.android");
      paramActivity.startActivityForResult(Intent.createChooser(localIntent, "Boo"), 100);
      return;
    }
    localIntent = new Intent("android.intent.action.SEND");
    if (!paramBoolean)
    {
      paramString1 = new File(paramString2);
      if ((paramString1 != null) && (paramString1.exists()) && (paramString1.isFile()))
      {
        localIntent.setType("video/*");
        if (Build.VERSION.SDK_INT >= 26) {
          paramString1 = FileProvider.getUriForFile(paramActivity, "com.boo.chat.fileprovider", paramString1);
        } else {
          paramString1 = Uri.fromFile(paramString1);
        }
        localIntent.putExtra("android.intent.extra.STREAM", paramString1);
      }
    }
    else
    {
      paramString1 = new File(paramString2);
      localIntent.setType("image/*");
      if (Build.VERSION.SDK_INT >= 26) {
        paramString1 = FileProvider.getUriForFile(paramActivity, "com.boo.chat.fileprovider", paramString1);
      } else {
        paramString1 = Uri.fromFile(paramString1);
      }
      localIntent.putExtra("android.intent.extra.STREAM", paramString1);
    }
    localIntent.putExtra("android.intent.extra.SUBJECT", "");
    localIntent.putExtra("android.intent.extra.TEXT", "");
    localIntent.setFlags(268435456);
    paramActivity.startActivityForResult(Intent.createChooser(localIntent, "Boo"), 100);
    return;
  }
}
