package com.ecovacs.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;
import android.provider.MediaStore.Images.Media;
import android.support.v4.app.NotificationManagerCompat;
import android.telephony.PhoneNumberUtils;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ToolPhone
{
  public ToolPhone() {}
  
  public static void callPhone(Context paramContext, String paramString)
  {
    paramString = new Intent("android.intent.action.CALL", Uri.parse("tel:" + paramString));
    try
    {
      paramContext.startActivity(paramString);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static boolean checkNotificationEnable(Context paramContext)
  {
    return NotificationManagerCompat.from(paramContext).areNotificationsEnabled();
  }
  
  public static Bitmap getChoosedImage(Activity paramActivity, Intent paramIntent)
  {
    if (paramIntent == null) {
      return null;
    }
    Object localObject2 = null;
    ContentResolver localContentResolver = paramActivity.getContentResolver();
    Object localObject1 = localObject2;
    try
    {
      Uri localUri = paramIntent.getData();
      localObject1 = localObject2;
      paramIntent = MediaStore.Images.Media.getBitmap(localContentResolver, localUri);
      localObject1 = paramIntent;
      paramActivity = paramActivity.managedQuery(localUri, new String[] { "_data" }, null, null, null);
      localObject1 = paramIntent;
      int i = paramActivity.getColumnIndexOrThrow("_data");
      localObject1 = paramIntent;
      paramActivity.moveToFirst();
      localObject1 = paramIntent;
      paramActivity.getString(i);
      localObject1 = paramIntent;
      paramActivity.close();
      return paramIntent;
    }
    catch (Exception paramActivity)
    {
      Log.e("ToolPhone", paramActivity.getMessage());
    }
    return localObject1;
  }
  
  public static String getChoosedPhoneNumber(Activity paramActivity, int paramInt, Intent paramIntent)
  {
    Object localObject1 = "";
    Object localObject2 = localObject1;
    if (-1 == paramInt)
    {
      localObject2 = paramActivity.managedQuery(paramIntent.getData(), null, null, null, null);
      ((Cursor)localObject2).moveToFirst();
      paramIntent = (Intent)localObject1;
      if (((Cursor)localObject2).getInt(((Cursor)localObject2).getColumnIndex("has_phone_number")) > 0)
      {
        paramIntent = ((Cursor)localObject2).getString(((Cursor)localObject2).getColumnIndex("_id"));
        Cursor localCursor = paramActivity.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, "contact_id = " + paramIntent, null, null);
        paramIntent = (Intent)localObject1;
        if (localCursor.moveToFirst())
        {
          if (!localCursor.isAfterLast())
          {
            paramInt = localCursor.getColumnIndex("data1");
            int i = localCursor.getInt(localCursor.getColumnIndex("data2"));
            paramActivity = localCursor.getString(paramInt);
            switch (i)
            {
            }
            for (;;)
            {
              localCursor.moveToNext();
              break;
              localObject1 = paramActivity;
            }
          }
          paramIntent = (Intent)localObject1;
          if (!localCursor.isClosed())
          {
            localCursor.close();
            paramIntent = (Intent)localObject1;
          }
        }
      }
      ((Cursor)localObject2).close();
      localObject2 = paramIntent;
    }
    return localObject2;
  }
  
  public static boolean isAppAvailable(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    if (paramContext != null)
    {
      int i = 0;
      while (i < paramContext.size())
      {
        if (((PackageInfo)paramContext.get(i)).packageName.equals(paramString)) {
          return true;
        }
        i += 1;
      }
    }
    return false;
  }
  
  public static boolean isExistActivity(Context paramContext, String paramString1, String paramString2)
  {
    Boolean localBoolean = Boolean.valueOf(true);
    Intent localIntent = new Intent();
    localIntent.setClassName(paramString1, paramString2);
    if (paramContext.getPackageManager().resolveActivity(localIntent, 0) == null) {
      paramString1 = Boolean.valueOf(false);
    }
    for (;;)
    {
      return paramString1.booleanValue();
      if (localIntent.resolveActivity(paramContext.getPackageManager()) == null)
      {
        paramString1 = Boolean.valueOf(false);
      }
      else
      {
        paramString1 = localBoolean;
        if (paramContext.getPackageManager().queryIntentActivities(localIntent, 0).size() == 0) {
          paramString1 = Boolean.valueOf(false);
        }
      }
    }
  }
  
  public static boolean isInstalledApp(Context paramContext, String paramString)
  {
    if ((paramString == null) || ("".equals(paramString))) {
      return false;
    }
    try
    {
      paramContext.getPackageManager().getApplicationInfo(paramString, 8192);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  public static boolean isMobileNO(String paramString)
  {
    return Pattern.compile("^1[3|4|5|7|8][0-9]\\d{8}$").matcher(paramString).matches();
  }
  
  public static boolean isRunning(Context paramContext, String paramString)
  {
    paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses().iterator();
    while (paramContext.hasNext())
    {
      String str = ((ActivityManager.RunningAppProcessInfo)paramContext.next()).processName;
      if ((str != null) && (str.equals(paramString))) {
        return true;
      }
    }
    return false;
  }
  
  public static int obtainRunningProcessId(Context paramContext, String paramString)
  {
    paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses();
    if ((paramContext == null) || (paramContext.size() == 0)) {}
    ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo;
    String str;
    do
    {
      while (!paramContext.hasNext())
      {
        return -1;
        paramContext = paramContext.iterator();
      }
      localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)paramContext.next();
      str = localRunningAppProcessInfo.processName;
    } while ((str == null) || (!str.equals(paramString)));
    return localRunningAppProcessInfo.pid;
  }
  
  public static void openOfficeByWPS(Context paramContext, String paramString)
  {
    try
    {
      if (!new File(paramString).exists())
      {
        Toast.makeText(paramContext, paramString + "文件路径不存在", 0).show();
        return;
      }
      Intent localIntent = new Intent();
      localIntent.addFlags(268435456);
      localIntent.addCategory("android.intent.category.DEFAULT");
      localIntent.setClassName("cn.wps.moffice_eng", "cn.wps.moffice.documentmanager.PreStartActivity2");
      localIntent.setData(Uri.fromFile(new File(paramString)));
      paramContext.startActivity(localIntent);
      return;
    }
    catch (ActivityNotFoundException paramString)
    {
      Toast.makeText(paramContext, "本地未安装WPS", 0).show();
      return;
    }
    catch (Exception paramString)
    {
      Toast.makeText(paramContext, "打开文档失败", 0).show();
    }
  }
  
  public static void openPDFFile(Context paramContext, String paramString)
  {
    try
    {
      paramString = new File(paramString);
      if (paramString.exists())
      {
        paramString = Uri.fromFile(paramString);
        Intent localIntent = new Intent("android.intent.action.VIEW");
        localIntent.setDataAndType(paramString, "application/pdf");
        localIntent.setFlags(67108864);
        paramContext.startActivity(localIntent);
      }
      return;
    }
    catch (Exception paramString)
    {
      Toast.makeText(paramContext, "未检测到可打开PDF相关软件", 0).show();
    }
  }
  
  public static void openWebSite(Context paramContext, String paramString)
  {
    paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(paramString)));
  }
  
  public static void openWeiBo(Context paramContext, String paramString)
  {
    if (isAppAvailable(paramContext, "com.sina.weibo"))
    {
      localIntent = new Intent("android.intent.action.VIEW");
      localIntent.setData(Uri.parse("sinaweibo://userinfo?uid=" + paramString));
      paramContext.startActivity(Intent.createChooser(localIntent, "Weibo"));
      return;
    }
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setData(Uri.parse("http://weibo.cn/qr/userinfo?uid=" + paramString));
    paramContext.startActivity(Intent.createChooser(localIntent, "Weibo"));
  }
  
  public static void openWordFile(Context paramContext, String paramString)
  {
    try
    {
      paramString = new File(paramString);
      if (paramString.exists())
      {
        paramString = Uri.fromFile(paramString);
        Intent localIntent = new Intent("android.intent.action.VIEW");
        localIntent.addCategory("android.intent.category.DEFAULT");
        localIntent.addFlags(268435456);
        localIntent.setDataAndType(paramString, "application/msword");
        paramContext.startActivity(localIntent);
      }
      return;
    }
    catch (Exception paramString)
    {
      Toast.makeText(paramContext, "未检测到可打开Word文档相关软件", 0).show();
    }
  }
  
  public static void sendMessage(Context paramContext, final String paramString1, String paramString2)
  {
    PendingIntent localPendingIntent1 = PendingIntent.getBroadcast(paramContext, 0, new Intent("SENT_SMS_ACTION"), 0);
    paramContext.registerReceiver(new BroadcastReceiver()new IntentFilter
    {
      public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
      {
        switch (getResultCode())
        {
        case 0: 
        case 1: 
        case 2: 
        default: 
          return;
        }
        Toast.makeText(this.val$mContext, "短信发送成功", 0).show();
      }
    }, new IntentFilter("SENT_SMS_ACTION"));
    PendingIntent localPendingIntent2 = PendingIntent.getBroadcast(paramContext, 0, new Intent("DELIVERED_SMS_ACTION"), 0);
    paramContext.registerReceiver(new BroadcastReceiver()new IntentFilter
    {
      public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
      {
        Toast.makeText(this.val$mContext, paramString1 + "已经成功接收", 0).show();
      }
    }, new IntentFilter("DELIVERED_SMS_ACTION"));
    paramContext = SmsManager.getDefault();
    paramString2 = paramContext.divideMessage(paramString2).iterator();
    while (paramString2.hasNext()) {
      paramContext.sendTextMessage(paramString1, null, (String)paramString2.next(), localPendingIntent1, localPendingIntent2);
    }
  }
  
  public static void startWechat(Context paramContext)
  {
    Intent localIntent = new Intent();
    ComponentName localComponentName = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.LauncherUI");
    localIntent.setAction("android.intent.action.MAIN");
    localIntent.addCategory("android.intent.category.LAUNCHER");
    localIntent.addFlags(268435456);
    localIntent.setComponent(localComponentName);
    paramContext.startActivity(localIntent);
  }
  
  public static void toCallPhoneActivity(Context paramContext, String paramString)
  {
    paramContext.startActivity(new Intent("android.intent.action.DIAL", Uri.parse("tel:" + paramString)));
  }
  
  public static void toCameraActivity(Activity paramActivity, int paramInt)
  {
    paramActivity.startActivityForResult(new Intent("android.media.action.IMAGE_CAPTURE"), paramInt);
  }
  
  public static void toChooseContactsList(Activity paramActivity, int paramInt)
  {
    paramActivity.startActivityForResult(new Intent("android.intent.action.PICK", ContactsContract.Contacts.CONTENT_URI), paramInt);
  }
  
  public static void toImagePickerActivity(Activity paramActivity, int paramInt)
  {
    Intent localIntent = new Intent("android.intent.action.PICK", null);
    localIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
    paramActivity.startActivityForResult(localIntent, paramInt);
  }
  
  public static void toSendMessageActivity(Context paramContext, String paramString1, String paramString2)
  {
    if (PhoneNumberUtils.isGlobalPhoneNumber(paramString1))
    {
      paramString1 = new Intent("android.intent.action.VIEW", Uri.parse("smsto:" + paramString1));
      paramString1.putExtra("sms_body", paramString2);
      paramContext.startActivity(paramString1);
    }
  }
  
  public static void toSettingActivity(Context paramContext)
  {
    paramContext.startActivity(new Intent("android.settings.SETTINGS"));
  }
  
  public static void toWIFISettingActivity(Context paramContext)
  {
    paramContext.startActivity(new Intent("android.settings.WIFI_SETTINGS"));
  }
}
