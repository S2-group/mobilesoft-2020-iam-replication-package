package com.garmin.android.gncs;

import android.app.Notification;
import android.app.Notification.Action;
import android.app.Notification.BigTextStyle;
import android.app.Notification.Builder;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.companion.CompanionDeviceManager;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract.PhoneLookup;
import android.provider.Settings.Secure;
import android.provider.Telephony.Sms;
import android.service.notification.StatusBarNotification;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat.BigTextStyle;
import android.support.v4.app.NotificationCompat.Builder;
import android.text.TextUtils;
import com.garmin.android.framework.util.designpattern.Factory;
import com.garmin.android.framework.util.inject.Configuration;
import com.garmin.android.gncs.persistence.GNCSNotificationAction;
import com.garmin.android.gncs.settings.GNCSConfig;
import com.garmin.android.gncs.settings.GNCSNotificationAccessActivity;
import com.garmin.android.gncs.settings.GNCSSettings;
import com.garmin.android.util.GNCSCoreUtil;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GNCSUtil
{
  public static final String MISSED_CALL_CONTACT_DIVIDER = "@%Z";
  public static final int NOTIFICATION_ID = 98;
  public static final int SMS_NOTIFICATION_ID = 99;
  private static String channelId = "SYSTEM_CHANNEL_ID";
  private static final String japaneseRegex = "([\\u3041-\\u3096\\u30A0-\\u30FF\\u3400-\\u4DB5\\u4E00-\\u9FCB\\uF900-\\uFA6A\\u2E80-\\u2FD5\\uFF5F-\\uFF9F\\u3000-\\u303F])";
  private static final String regex = "([\\u20a0-\\u32ff\\ud83c\\udc00-\\ud83d\\udeff\\udbb9\\udce5-\\udbb9\\udcee])";
  private final String ACCESS_REQUIRED_CHANNEL_ID = "access_required";
  private int notificationResId = R.drawable.gcm3_notificationbar_icon_connect;
  
  protected GNCSUtil() {}
  
  public static void dumpNotificationInfo(StatusBarNotification paramStatusBarNotification, GNCSNotificationInfo paramGNCSNotificationInfo, String paramString)
  {
    if ((Build.VERSION.SDK_INT >= 21) && (paramStatusBarNotification.getNotification() != null) && (paramStatusBarNotification.getNotification().category != null) && (paramStatusBarNotification.getNotification().category.equals("sys"))) {
      return;
    }
    GNCSCoreUtil.tracelog("GNCSDump", "=================================================================================");
    Object localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append(paramString);
    ((StringBuilder)localObject1).append(" : ");
    ((StringBuilder)localObject1).append(System.currentTimeMillis());
    GNCSCoreUtil.tracelog("GNCSDump", ((StringBuilder)localObject1).toString());
    GNCSCoreUtil.tracelog("GNCSDump", "=================================================================================");
    label434:
    label481:
    int i;
    for (;;)
    {
      try
      {
        paramString = new StringBuilder();
        paramString.append("PackageName: ");
        paramString.append(paramStatusBarNotification.getPackageName());
        GNCSCoreUtil.tracelog("GNCSDump", paramString.toString());
        paramString = new StringBuilder();
        paramString.append("Id: ");
        paramString.append(paramStatusBarNotification.getId());
        GNCSCoreUtil.tracelog("GNCSDump", paramString.toString());
        if (Build.VERSION.SDK_INT >= 21)
        {
          paramString = new StringBuilder();
          paramString.append("Key: ");
          paramString.append(obfuscate(paramStatusBarNotification.getKey()));
          GNCSCoreUtil.tracelog("GNCSDump", paramString.toString());
          paramString = new StringBuilder();
          paramString.append("GroupKey: ");
          paramString.append(obfuscate(paramStatusBarNotification.getGroupKey()));
          GNCSCoreUtil.tracelog("GNCSDump", paramString.toString());
        }
        if (Build.VERSION.SDK_INT >= 24)
        {
          localObject1 = new StringBuilder();
          ((StringBuilder)localObject1).append("IsGroup: ");
          if (!paramStatusBarNotification.isGroup()) {
            break label4266;
          }
          paramString = "Yes";
          ((StringBuilder)localObject1).append(paramString);
          GNCSCoreUtil.tracelog("GNCSDump", ((StringBuilder)localObject1).toString());
          paramString = new StringBuilder();
          paramString.append("OverrideGroupKey: ");
          paramString.append(obfuscate(paramStatusBarNotification.getOverrideGroupKey()));
          GNCSCoreUtil.tracelog("GNCSDump", paramString.toString());
        }
        paramString = new StringBuilder();
        paramString.append("PostTime: ");
        paramString.append(paramStatusBarNotification.getPostTime());
        GNCSCoreUtil.tracelog("GNCSDump", paramString.toString());
        paramString = new StringBuilder();
        paramString.append("Tag: ");
        paramString.append(obfuscate(paramStatusBarNotification.getTag()));
        GNCSCoreUtil.tracelog("GNCSDump", paramString.toString());
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("IsClearable: ");
        if (!paramStatusBarNotification.isClearable()) {
          break label4273;
        }
        paramString = "Yes";
        ((StringBuilder)localObject1).append(paramString);
        GNCSCoreUtil.tracelog("GNCSDump", ((StringBuilder)localObject1).toString());
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("IsOngoing: ");
        if (!paramStatusBarNotification.isOngoing()) {
          break label4280;
        }
        paramString = "Yes";
        ((StringBuilder)localObject1).append(paramString);
        GNCSCoreUtil.tracelog("GNCSDump", ((StringBuilder)localObject1).toString());
        paramString = paramStatusBarNotification.getNotification();
        if (paramString != null)
        {
          if ((paramString.actions != null) && (paramString.actions.length > 0))
          {
            GNCSCoreUtil.tracelog("GNCSDump", "Actions >>>");
            i = 0;
            label531:
            if (i < paramString.actions.length)
            {
              localObject1 = paramString.actions[i];
              if (localObject1 == null) {
                break label4301;
              }
              paramStatusBarNotification = new StringBuilder();
              paramStatusBarNotification.append("     title: ");
              paramStatusBarNotification.append(((Notification.Action)localObject1).title);
              GNCSCoreUtil.tracelog("GNCSDump", paramStatusBarNotification.toString());
              localObject2 = new StringBuilder();
              ((StringBuilder)localObject2).append("        actionIntent: ");
              if (((Notification.Action)localObject1).actionIntent == null) {
                break label4287;
              }
              paramStatusBarNotification = "Yes";
              label621:
              ((StringBuilder)localObject2).append(paramStatusBarNotification);
              GNCSCoreUtil.tracelog("GNCSDump", ((StringBuilder)localObject2).toString());
              if (Build.VERSION.SDK_INT >= 21)
              {
                paramStatusBarNotification = ((Notification.Action)localObject1).getExtras();
                if (paramStatusBarNotification != null)
                {
                  localObject1 = paramStatusBarNotification.keySet().iterator();
                  if (((Iterator)localObject1).hasNext())
                  {
                    localObject2 = (String)((Iterator)localObject1).next();
                    if (paramStatusBarNotification.get((String)localObject2) != null)
                    {
                      localObject3 = new StringBuilder();
                      ((StringBuilder)localObject3).append("        Extra ");
                      ((StringBuilder)localObject3).append((String)localObject2);
                      ((StringBuilder)localObject3).append(": ");
                      ((StringBuilder)localObject3).append(paramStatusBarNotification.get((String)localObject2).toString());
                      GNCSCoreUtil.tracelog("GNCSDump", ((StringBuilder)localObject3).toString());
                      continue;
                    }
                    localObject3 = new StringBuilder();
                    ((StringBuilder)localObject3).append("        Extra ");
                    ((StringBuilder)localObject3).append((String)localObject2);
                    ((StringBuilder)localObject3).append(": null");
                    GNCSCoreUtil.tracelog("GNCSDump", ((StringBuilder)localObject3).toString());
                    continue;
                  }
                }
                paramStatusBarNotification = paramString.actions[i].getRemoteInputs();
                if ((paramStatusBarNotification != null) && (paramStatusBarNotification.length > 0)) {
                  GNCSCoreUtil.tracelog("GNCSDump", "        Action requires remote inputs");
                }
              }
              if (Build.VERSION.SDK_INT < 24) {
                break label4301;
              }
              localObject1 = new StringBuilder();
              ((StringBuilder)localObject1).append("        Allow Generated Replies: ");
              if (!paramString.actions[i].getAllowGeneratedReplies()) {
                break label4294;
              }
              paramStatusBarNotification = "Yes";
              label874:
              ((StringBuilder)localObject1).append(paramStatusBarNotification);
              GNCSCoreUtil.tracelog("GNCSDump", ((StringBuilder)localObject1).toString());
              break label4301;
            }
          }
          if ((Build.VERSION.SDK_INT >= 21) && (paramString.category != null)) {
            paramStatusBarNotification = paramString.category;
          }
          switch (paramStatusBarNotification.hashCode())
          {
          case 1984153269: 
            if (!paramStatusBarNotification.equals("service")) {
              break;
            }
            i = 10;
            break;
          case 1052964649: 
            if (!paramStatusBarNotification.equals("transport")) {
              break;
            }
            i = 14;
            break;
          case 106940687: 
            if (!paramStatusBarNotification.equals("promo")) {
              break;
            }
            i = 7;
            break;
          case 96891546: 
            if (!paramStatusBarNotification.equals("event")) {
              break;
            }
            i = 4;
            break;
          case 96619420: 
            if (!paramStatusBarNotification.equals("email")) {
              break;
            }
            i = 2;
            break;
          case 92895825: 
            if (!paramStatusBarNotification.equals("alarm")) {
              break;
            }
            i = 0;
            break;
          case 3045982: 
            if (!paramStatusBarNotification.equals("call")) {
              break;
            }
            i = 1;
            break;
          case 114381: 
            if (!paramStatusBarNotification.equals("sys")) {
              break;
            }
            i = 13;
            break;
          case 108417: 
            if (!paramStatusBarNotification.equals("msg")) {
              break;
            }
            i = 5;
            break;
          case 100709: 
            if (!paramStatusBarNotification.equals("err")) {
              break;
            }
            i = 3;
            break;
          case -518602638: 
            if (!paramStatusBarNotification.equals("reminder")) {
              break;
            }
            i = 9;
            break;
          case -892481550: 
            if (!paramStatusBarNotification.equals("status")) {
              break;
            }
            i = 12;
            break;
          case -897050771: 
            if (!paramStatusBarNotification.equals("social")) {
              break;
            }
            i = 11;
            break;
          case -1001078227: 
            if (!paramStatusBarNotification.equals("progress")) {
              break;
            }
            i = 6;
            break;
          case -1028636743: 
            if (!paramStatusBarNotification.equals("recommendation")) {
              break;
            }
            i = 8;
            break label4310;
            label1281:
            continue;
            GNCSCoreUtil.tracelog("GNCSDump", "Category: Transport");
            continue;
            GNCSCoreUtil.tracelog("GNCSDump", "Category: System");
            continue;
            GNCSCoreUtil.tracelog("GNCSDump", "Category: Status");
            continue;
            GNCSCoreUtil.tracelog("GNCSDump", "Category: Social");
            continue;
            GNCSCoreUtil.tracelog("GNCSDump", "Category: Service");
            continue;
            GNCSCoreUtil.tracelog("GNCSDump", "Category: Reminder");
            continue;
            GNCSCoreUtil.tracelog("GNCSDump", "Category: Recommendation");
            continue;
            GNCSCoreUtil.tracelog("GNCSDump", "Category: Promo");
            continue;
            GNCSCoreUtil.tracelog("GNCSDump", "Category: Progress");
            continue;
            GNCSCoreUtil.tracelog("GNCSDump", "Category: Message");
            continue;
            GNCSCoreUtil.tracelog("GNCSDump", "Category: Event");
            continue;
            GNCSCoreUtil.tracelog("GNCSDump", "Category: Error");
            continue;
            GNCSCoreUtil.tracelog("GNCSDump", "Category: Email");
            continue;
            GNCSCoreUtil.tracelog("GNCSDump", "Category: Call");
            continue;
            GNCSCoreUtil.tracelog("GNCSDump", "Category: Alarm");
            continue;
            paramStatusBarNotification = new StringBuilder();
            paramStatusBarNotification.append("Category: ");
            paramStatusBarNotification.append(paramString.category);
            GNCSCoreUtil.tracelog("GNCSDump", paramStatusBarNotification.toString());
            continue;
            GNCSCoreUtil.tracelog("GNCSDump", "Category: Unknown");
            if (paramString.contentIntent != null)
            {
              paramStatusBarNotification = new StringBuilder();
              paramStatusBarNotification.append("ContentIntent: ");
              paramStatusBarNotification.append(paramString.contentIntent.getCreatorPackage());
              GNCSCoreUtil.tracelog("GNCSDump", paramStatusBarNotification.toString());
            }
            break;
          }
        }
      }
      catch (Throwable paramStatusBarNotification)
      {
        Object localObject2;
        Object localObject3;
        StringBuilder localStringBuilder;
        paramGNCSNotificationInfo = new StringBuilder();
        paramGNCSNotificationInfo.append("Throwable thrown while dumping notification: ");
        paramGNCSNotificationInfo.append(paramStatusBarNotification.getMessage());
        GNCSCoreUtil.tracelog("GNCSDump", paramGNCSNotificationInfo.toString());
        return;
      }
      catch (Exception paramStatusBarNotification)
      {
        paramGNCSNotificationInfo = new StringBuilder();
        paramGNCSNotificationInfo.append("Exception thrown while dumping notification: ");
        paramGNCSNotificationInfo.append(paramStatusBarNotification.getMessage());
        GNCSCoreUtil.tracelog("GNCSDump", paramGNCSNotificationInfo.toString());
      }
      try
      {
        paramStatusBarNotification = (Intent)PendingIntent.class.getDeclaredMethod("getIntent", new Class[0]).invoke(paramString.contentIntent, new Object[0]);
        if (paramStatusBarNotification != null)
        {
          localObject1 = new StringBuilder();
          ((StringBuilder)localObject1).append("     Action: ");
          ((StringBuilder)localObject1).append(paramStatusBarNotification.getAction());
          GNCSCoreUtil.tracelog("GNCSDump", ((StringBuilder)localObject1).toString());
          localObject1 = new StringBuilder();
          ((StringBuilder)localObject1).append("     Component: ");
          ((StringBuilder)localObject1).append(paramStatusBarNotification.getComponent().flattenToShortString());
          GNCSCoreUtil.tracelog("GNCSDump", ((StringBuilder)localObject1).toString());
        }
      }
      catch (NoSuchMethodException|InvocationTargetException|IllegalAccessException paramStatusBarNotification) {}
    }
    if (paramString.deleteIntent != null)
    {
      paramStatusBarNotification = new StringBuilder();
      paramStatusBarNotification.append("DeleteIntent: ");
      paramStatusBarNotification.append(paramString.deleteIntent.getCreatorPackage());
      GNCSCoreUtil.tracelog("GNCSDump", paramStatusBarNotification.toString());
    }
    try
    {
      paramStatusBarNotification = (Intent)PendingIntent.class.getDeclaredMethod("getIntent", new Class[0]).invoke(paramString.deleteIntent, new Object[0]);
      if (paramStatusBarNotification != null)
      {
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("     Action: ");
        ((StringBuilder)localObject1).append(paramStatusBarNotification.getAction());
        GNCSCoreUtil.tracelog("GNCSDump", ((StringBuilder)localObject1).toString());
        if (paramStatusBarNotification.getComponent() != null)
        {
          localObject1 = new StringBuilder();
          ((StringBuilder)localObject1).append("     Component: ");
          ((StringBuilder)localObject1).append(paramStatusBarNotification.getComponent().flattenToShortString());
          GNCSCoreUtil.tracelog("GNCSDump", ((StringBuilder)localObject1).toString());
        }
      }
    }
    catch (NoSuchMethodException|InvocationTargetException|IllegalAccessException paramStatusBarNotification)
    {
      label1840:
      label2260:
      label2367:
      label2420:
      label2475:
      label2530:
      label2581:
      label2636:
      label2689:
      label2740:
      label2793:
      label2844:
      label2932:
      label3024:
      label3124:
      label3194:
      for (;;) {}
    }
    if (paramString.extras != null)
    {
      GNCSCoreUtil.tracelog("GNCSDump", "Extras >>>");
      localObject1 = paramString.extras.keySet().iterator();
      if (((Iterator)localObject1).hasNext())
      {
        localObject2 = (String)((Iterator)localObject1).next();
        if (((String)localObject2).equals("android.textLines"))
        {
          localObject3 = paramString.extras.getCharSequenceArray("android.textLines");
          paramStatusBarNotification = new StringBuilder();
          i = 0;
          while (i < localObject3.length)
          {
            if (paramStatusBarNotification.length() > 0) {
              paramStatusBarNotification.append("\n");
            }
            paramStatusBarNotification.append(localObject3[i]);
            i += 1;
          }
          localObject3 = new StringBuilder();
          ((StringBuilder)localObject3).append("     ");
          ((StringBuilder)localObject3).append((String)localObject2);
          ((StringBuilder)localObject3).append(":\n");
          ((StringBuilder)localObject3).append(obfuscate(paramStatusBarNotification.toString()));
          GNCSCoreUtil.tracelog("GNCSDump", ((StringBuilder)localObject3).toString());
          break label4387;
        }
        localObject3 = paramString.extras.get((String)localObject2);
        if (localObject3 == null) {
          break label4387;
        }
        paramStatusBarNotification = localObject3.toString();
      }
    }
    switch (((String)localObject2).hashCode())
    {
    case 1297754027: 
      if (((String)localObject2).equals("android.title.big")) {
        i = 8;
      }
      break;
    case 967367924: 
      if (((String)localObject2).equals("android.summaryText")) {
        i = 5;
      }
      break;
    case 150183834: 
      if (((String)localObject2).equals("android.infoText")) {
        i = 2;
      }
      break;
    case 22486478: 
      if (((String)localObject2).equals("android.bigText")) {
        i = 0;
      }
      break;
    case -489846293: 
      if (((String)localObject2).equals("android.messages")) {
        i = 3;
      }
      break;
    case -1036913332: 
      if (((String)localObject2).equals("android.text")) {
        i = 6;
      }
      break;
    case -1730887922: 
      if (((String)localObject2).equals("android.subText")) {
        i = 4;
      }
      break;
    case -1897567786: 
      if (((String)localObject2).equals("android.conversationTitle")) {
        i = 1;
      }
      break;
    case -2079427047: 
      if (((String)localObject2).equals("android.title"))
      {
        i = 7;
        break label4392;
        paramStatusBarNotification = obfuscate(paramStatusBarNotification);
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("     ");
        localStringBuilder.append((String)localObject2);
        localStringBuilder.append(": ");
        if (localObject3 == null) {
          paramStatusBarNotification = "";
        }
        localStringBuilder.append(paramStatusBarNotification);
        GNCSCoreUtil.tracelog("GNCSDump", localStringBuilder.toString());
        break label4387;
        GNCSCoreUtil.tracelog("GNCSDump", "Flags>>>>>");
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("     AUTO_CANCEL: ");
        if ((paramString.flags & 0x10) != 16) {
          break label4447;
        }
        paramStatusBarNotification = "Yes";
        ((StringBuilder)localObject1).append(paramStatusBarNotification);
        GNCSCoreUtil.tracelog("GNCSDump", ((StringBuilder)localObject1).toString());
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("     FOREGROUND_SERVICE: ");
        if ((paramString.flags & 0x40) != 64) {
          break label4454;
        }
        paramStatusBarNotification = "Yes";
        ((StringBuilder)localObject1).append(paramStatusBarNotification);
        GNCSCoreUtil.tracelog("GNCSDump", ((StringBuilder)localObject1).toString());
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("     GROUP_SUMMARY: ");
        if ((paramString.flags & 0x200) != 512) {
          break label4461;
        }
        paramStatusBarNotification = "Yes";
        ((StringBuilder)localObject1).append(paramStatusBarNotification);
        GNCSCoreUtil.tracelog("GNCSDump", ((StringBuilder)localObject1).toString());
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("     HIGH_PRIORITY: ");
        if ((paramString.flags & 0x80) != 128) {
          break label4468;
        }
        paramStatusBarNotification = "Yes";
        ((StringBuilder)localObject1).append(paramStatusBarNotification);
        GNCSCoreUtil.tracelog("GNCSDump", ((StringBuilder)localObject1).toString());
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("     INSISTENT: ");
        if ((paramString.flags & 0x4) != 4) {
          break label4475;
        }
        paramStatusBarNotification = "Yes";
        ((StringBuilder)localObject1).append(paramStatusBarNotification);
        GNCSCoreUtil.tracelog("GNCSDump", ((StringBuilder)localObject1).toString());
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("     LOCAL_ONLY: ");
        if ((paramString.flags & 0x100) != 256) {
          break label4482;
        }
        paramStatusBarNotification = "Yes";
        ((StringBuilder)localObject1).append(paramStatusBarNotification);
        GNCSCoreUtil.tracelog("GNCSDump", ((StringBuilder)localObject1).toString());
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("     NO_CLEAR: ");
        if ((paramString.flags & 0x20) != 32) {
          break label4489;
        }
        paramStatusBarNotification = "Yes";
        ((StringBuilder)localObject1).append(paramStatusBarNotification);
        GNCSCoreUtil.tracelog("GNCSDump", ((StringBuilder)localObject1).toString());
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("     ONGOING_EVENT: ");
        if ((paramString.flags & 0x2) != 2) {
          break label4496;
        }
        paramStatusBarNotification = "Yes";
        ((StringBuilder)localObject1).append(paramStatusBarNotification);
        GNCSCoreUtil.tracelog("GNCSDump", ((StringBuilder)localObject1).toString());
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("     ONLY_ALERT_ONCE: ");
        if ((paramString.flags & 0x8) != 8) {
          break label4503;
        }
        paramStatusBarNotification = "Yes";
        ((StringBuilder)localObject1).append(paramStatusBarNotification);
        GNCSCoreUtil.tracelog("GNCSDump", ((StringBuilder)localObject1).toString());
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("     SHOW_LIGHTS: ");
        if ((paramString.flags & 0x1) != 1) {
          break label4510;
        }
        paramStatusBarNotification = "Yes";
        ((StringBuilder)localObject1).append(paramStatusBarNotification);
        GNCSCoreUtil.tracelog("GNCSDump", ((StringBuilder)localObject1).toString());
        paramStatusBarNotification = new StringBuilder();
        paramStatusBarNotification.append("NumberEvents: ");
        paramStatusBarNotification.append(paramString.number);
        GNCSCoreUtil.tracelog("GNCSDump", paramStatusBarNotification.toString());
        switch (paramString.priority)
        {
        case 2: 
          break;
          GNCSCoreUtil.tracelog("GNCSDump", "Priority: Max");
          break;
        case 1: 
          GNCSCoreUtil.tracelog("GNCSDump", "Priority: High");
          break;
        case 0: 
          GNCSCoreUtil.tracelog("GNCSDump", "Priority: Default");
          break;
        case -1: 
          GNCSCoreUtil.tracelog("GNCSDump", "Priority: Low");
          break;
        case -2: 
          GNCSCoreUtil.tracelog("GNCSDump", "Priority: Min");
          break label3024;
          paramStatusBarNotification = new StringBuilder();
          paramStatusBarNotification.append("Priority: ");
          paramStatusBarNotification.append(paramString.priority);
          GNCSCoreUtil.tracelog("GNCSDump", paramStatusBarNotification.toString());
          localObject1 = new StringBuilder();
          ((StringBuilder)localObject1).append("TickerText: ");
          if (paramString.tickerText == null) {
            paramStatusBarNotification = "";
          } else {
            paramStatusBarNotification = obfuscate(paramString.tickerText.toString());
          }
          ((StringBuilder)localObject1).append(paramStatusBarNotification);
          GNCSCoreUtil.tracelog("GNCSDump", ((StringBuilder)localObject1).toString());
          if (Build.VERSION.SDK_INT >= 21) {}
          switch (paramString.visibility)
          {
          case 1: 
            break;
            GNCSCoreUtil.tracelog("GNCSDump", "Visibility: Public");
            break;
          case 0: 
            GNCSCoreUtil.tracelog("GNCSDump", "Visibility: Private");
            break;
          case -1: 
            GNCSCoreUtil.tracelog("GNCSDump", "Visibility: Secret");
            break label3194;
            paramStatusBarNotification = new StringBuilder();
            paramStatusBarNotification.append("Visibility: ");
            paramStatusBarNotification.append(paramString.visibility);
            GNCSCoreUtil.tracelog("GNCSDump", paramStatusBarNotification.toString());
            paramStatusBarNotification = new StringBuilder();
            paramStatusBarNotification.append("When: ");
            paramStatusBarNotification.append(paramString.when);
            GNCSCoreUtil.tracelog("GNCSDump", paramStatusBarNotification.toString());
            GNCSCoreUtil.tracelog("GNCSDump", "");
            GNCSCoreUtil.tracelog("GNCSDump", "=== GNCSParsedNotification (Parsed Notification) ===");
            GNCSCoreUtil.tracelog("GNCSDump", "");
            paramStatusBarNotification = new StringBuilder();
            paramStatusBarNotification.append("title: ");
            paramStatusBarNotification.append(obfuscate(paramGNCSNotificationInfo.title));
            GNCSCoreUtil.tracelog("GNCSDump", paramStatusBarNotification.toString());
            paramStatusBarNotification = new StringBuilder();
            paramStatusBarNotification.append("subTitle: ");
            paramStatusBarNotification.append(obfuscate(paramGNCSNotificationInfo.subTitle));
            GNCSCoreUtil.tracelog("GNCSDump", paramStatusBarNotification.toString());
            paramStatusBarNotification = new StringBuilder();
            paramStatusBarNotification.append("message: ");
            paramStatusBarNotification.append(obfuscate(paramGNCSNotificationInfo.message));
            GNCSCoreUtil.tracelog("GNCSDump", paramStatusBarNotification.toString());
            paramStatusBarNotification = new StringBuilder();
            paramStatusBarNotification.append("positiveAction: ");
            paramStatusBarNotification.append(paramGNCSNotificationInfo.positiveAction);
            GNCSCoreUtil.tracelog("GNCSDump", paramStatusBarNotification.toString());
            paramStatusBarNotification = new StringBuilder();
            paramStatusBarNotification.append("negativeAction: ");
            paramStatusBarNotification.append(paramGNCSNotificationInfo.negativeAction);
            GNCSCoreUtil.tracelog("GNCSDump", paramStatusBarNotification.toString());
            paramStatusBarNotification = new StringBuilder();
            paramStatusBarNotification.append("status: ");
            paramStatusBarNotification.append(paramGNCSNotificationInfo.status.name());
            GNCSCoreUtil.tracelog("GNCSDump", paramStatusBarNotification.toString());
            GNCSCoreUtil.tracelog("GNCSDump", "");
            paramStatusBarNotification = new StringBuilder();
            paramStatusBarNotification.append("cacheId: ");
            paramStatusBarNotification.append(paramGNCSNotificationInfo.cacheId);
            GNCSCoreUtil.tracelog("GNCSDump", paramStatusBarNotification.toString());
            paramStatusBarNotification = new StringBuilder();
            paramStatusBarNotification.append("cacheKey: ");
            paramStatusBarNotification.append(obfuscate(paramGNCSNotificationInfo.cacheKey));
            GNCSCoreUtil.tracelog("GNCSDump", paramStatusBarNotification.toString());
            paramStatusBarNotification = new StringBuilder();
            paramStatusBarNotification.append("notificationId: ");
            paramStatusBarNotification.append(paramGNCSNotificationInfo.notificationId);
            GNCSCoreUtil.tracelog("GNCSDump", paramStatusBarNotification.toString());
            paramStatusBarNotification = new StringBuilder();
            paramStatusBarNotification.append("notificationKey: ");
            paramStatusBarNotification.append(obfuscate(paramGNCSNotificationInfo.notificationKey));
            GNCSCoreUtil.tracelog("GNCSDump", paramStatusBarNotification.toString());
            paramStatusBarNotification = new StringBuilder();
            paramStatusBarNotification.append("packageName: ");
            paramStatusBarNotification.append(paramGNCSNotificationInfo.packageName);
            GNCSCoreUtil.tracelog("GNCSDump", paramStatusBarNotification.toString());
            paramStatusBarNotification = new StringBuilder();
            paramStatusBarNotification.append("Tag: ");
            paramStatusBarNotification.append(obfuscate(paramGNCSNotificationInfo.tag));
            GNCSCoreUtil.tracelog("GNCSDump", paramStatusBarNotification.toString());
            if (paramGNCSNotificationInfo != null)
            {
              paramString = new StringBuilder();
              paramString.append("isGroup: ");
              if (!paramGNCSNotificationInfo.isGroup) {
                break label4523;
              }
              paramStatusBarNotification = "Yes";
            }
            break;
          }
        }
      }
      break;
    }
    for (;;)
    {
      paramString.append(paramStatusBarNotification);
      GNCSCoreUtil.tracelog("GNCSDump", paramString.toString());
      paramStatusBarNotification = new StringBuilder();
      paramStatusBarNotification.append("numEvents: ");
      paramStatusBarNotification.append(paramGNCSNotificationInfo.numEvents);
      GNCSCoreUtil.tracelog("GNCSDump", paramStatusBarNotification.toString());
      paramStatusBarNotification = new StringBuilder();
      paramStatusBarNotification.append("priority: ");
      paramStatusBarNotification.append(paramGNCSNotificationInfo.priority);
      GNCSCoreUtil.tracelog("GNCSDump", paramStatusBarNotification.toString());
      paramStatusBarNotification = new StringBuilder();
      paramStatusBarNotification.append("tickerText: ");
      paramStatusBarNotification.append(obfuscate(paramGNCSNotificationInfo.tickerText));
      GNCSCoreUtil.tracelog("GNCSDump", paramStatusBarNotification.toString());
      paramStatusBarNotification = new StringBuilder();
      paramStatusBarNotification.append("postTime: ");
      paramStatusBarNotification.append(paramGNCSNotificationInfo.postTime);
      GNCSCoreUtil.tracelog("GNCSDump", paramStatusBarNotification.toString());
      paramStatusBarNotification = new StringBuilder();
      paramStatusBarNotification.append("when: ");
      paramStatusBarNotification.append(paramGNCSNotificationInfo.when);
      GNCSCoreUtil.tracelog("GNCSDump", paramStatusBarNotification.toString());
      paramStatusBarNotification = new StringBuilder();
      paramStatusBarNotification.append("notificationType: ");
      paramStatusBarNotification.append(paramGNCSNotificationInfo.type);
      GNCSCoreUtil.tracelog("GNCSDump", paramStatusBarNotification.toString());
      paramStatusBarNotification = new StringBuilder();
      paramStatusBarNotification.append("flags: ");
      paramStatusBarNotification.append(paramGNCSNotificationInfo.notificationFlags);
      GNCSCoreUtil.tracelog("GNCSDump", paramStatusBarNotification.toString());
      paramStatusBarNotification = new StringBuilder();
      paramStatusBarNotification.append("phoneNumberFlags: ");
      paramStatusBarNotification.append(paramGNCSNotificationInfo.extraFlags);
      GNCSCoreUtil.tracelog("GNCSDump", paramStatusBarNotification.toString());
      paramStatusBarNotification = new StringBuilder();
      paramStatusBarNotification.append("notificationFlags: ");
      paramStatusBarNotification.append(paramGNCSNotificationInfo.notificationFlags);
      GNCSCoreUtil.tracelog("GNCSDump", paramStatusBarNotification.toString());
      paramString = new StringBuilder();
      paramString.append("groupKey: ");
      if (paramGNCSNotificationInfo.groupKey == null) {
        paramStatusBarNotification = "None";
      } else {
        paramStatusBarNotification = paramGNCSNotificationInfo.groupKey;
      }
      paramString.append(paramStatusBarNotification);
      GNCSCoreUtil.tracelog("GNCSDump", paramString.toString());
      paramString = new StringBuilder();
      paramString.append("overrideGroupKey: ");
      if (paramGNCSNotificationInfo.overrideGroupKey == null) {
        paramStatusBarNotification = "None";
      } else {
        paramStatusBarNotification = obfuscate(paramGNCSNotificationInfo.overrideGroupKey);
      }
      paramString.append(paramStatusBarNotification);
      GNCSCoreUtil.tracelog("GNCSDump", paramString.toString());
      paramStatusBarNotification = new StringBuilder();
      paramStatusBarNotification.append("phone number: ");
      paramStatusBarNotification.append(obfuscate(paramGNCSNotificationInfo.phoneNumber));
      GNCSCoreUtil.tracelog("GNCSDump", paramStatusBarNotification.toString());
      return;
      return;
      label4266:
      paramString = "No";
      break;
      label4273:
      paramString = "No";
      break label434;
      label4280:
      paramString = "No";
      break label481;
      label4287:
      paramStatusBarNotification = "No";
      break label621;
      label4294:
      paramStatusBarNotification = "No";
      break label874;
      label4301:
      i += 1;
      break label531;
      i = -1;
      label4310:
      switch (i)
      {
      }
      break label1281;
      label4387:
      break label1840;
      i = -1;
      label4392:
      switch (i)
      {
      }
      break label2260;
      label4447:
      paramStatusBarNotification = "No";
      break label2367;
      label4454:
      paramStatusBarNotification = "No";
      break label2420;
      label4461:
      paramStatusBarNotification = "No";
      break label2475;
      label4468:
      paramStatusBarNotification = "No";
      break label2530;
      label4475:
      paramStatusBarNotification = "No";
      break label2581;
      label4482:
      paramStatusBarNotification = "No";
      break label2636;
      label4489:
      paramStatusBarNotification = "No";
      break label2689;
      label4496:
      paramStatusBarNotification = "No";
      break label2740;
      label4503:
      paramStatusBarNotification = "No";
      break label2793;
      label4510:
      paramStatusBarNotification = "No";
      break label2844;
      break label2932;
      break label3124;
      label4523:
      paramStatusBarNotification = "No/Unknown";
    }
  }
  
  public static List<GNCSNotificationAction> filterEmojiActions(List<GNCSNotificationAction> paramList)
  {
    ArrayList localArrayList = new ArrayList();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      GNCSNotificationAction localGNCSNotificationAction = (GNCSNotificationAction)paramList.next();
      if (!isEmoji(localGNCSNotificationAction.title)) {
        localArrayList.add(localGNCSNotificationAction);
      } else if ((Locale.getDefault().getCountry().equalsIgnoreCase("JP")) && (isJapanese(localGNCSNotificationAction.title))) {
        localArrayList.add(localGNCSNotificationAction);
      }
    }
    return localArrayList;
  }
  
  private static boolean hasLinkedDevices(Context paramContext)
  {
    return ((CompanionDeviceManager)paramContext.getSystemService(CompanionDeviceManager.class)).getAssociations().size() > 0;
  }
  
  public static boolean isDefaultSmsPackage(Context paramContext, String paramString)
  {
    boolean bool2 = false;
    if (paramContext != null)
    {
      paramContext = Telephony.Sms.getDefaultSmsPackage(paramContext.getApplicationContext());
      boolean bool1 = bool2;
      if (paramContext != null)
      {
        bool1 = bool2;
        if (paramString != null)
        {
          bool1 = bool2;
          if (paramContext.equals(paramString)) {
            bool1 = true;
          }
        }
      }
      return bool1;
    }
    return false;
  }
  
  public static boolean isDialerPackage(Context paramContext, String paramString)
  {
    Object localObject = paramContext;
    if (paramContext == null) {}
    try
    {
      paramContext = GNCSConfig.getInstance().getApplicationContext();
      localObject = paramContext;
      if (paramContext == null) {
        return false;
      }
      paramContext = loadDialerPackages((Context)localObject);
      if (paramContext != null)
      {
        paramContext = paramContext.iterator();
        while (paramContext.hasNext())
        {
          localObject = (ResolveInfo)paramContext.next();
          if ((((ResolveInfo)localObject).activityInfo != null) && (((ResolveInfo)localObject).activityInfo.packageName != null))
          {
            boolean bool = ((ResolveInfo)localObject).activityInfo.packageName.equals(paramString);
            if (bool) {
              return true;
            }
          }
        }
      }
      return false;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  private static boolean isEmoji(String paramString)
  {
    try
    {
      boolean bool = Pattern.compile("([\\u20a0-\\u32ff\\ud83c\\udc00-\\ud83d\\udeff\\udbb9\\udce5-\\udbb9\\udcee])").matcher(paramString).find();
      if (bool) {
        return true;
      }
    }
    catch (Exception paramString)
    {
      for (;;) {}
    }
    return false;
  }
  
  public static boolean isEmptyNotification(GNCSNotificationInfo paramGNCSNotificationInfo)
  {
    if (paramGNCSNotificationInfo == null) {
      return true;
    }
    return (TextUtils.isEmpty(paramGNCSNotificationInfo.title)) && (TextUtils.isEmpty(paramGNCSNotificationInfo.subTitle)) && (TextUtils.isEmpty(paramGNCSNotificationInfo.message));
  }
  
  private static boolean isJapanese(String paramString)
  {
    try
    {
      boolean bool = Pattern.compile("([\\u3041-\\u3096\\u30A0-\\u30FF\\u3400-\\u4DB5\\u4E00-\\u9FCB\\uF900-\\uFA6A\\u2E80-\\u2FD5\\uFF5F-\\uFF9F\\u3000-\\u303F])").matcher(paramString).find();
      if (bool) {
        return true;
      }
    }
    catch (Exception paramString)
    {
      for (;;) {}
    }
    return false;
  }
  
  public static List<ResolveInfo> loadDialerPackages(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().queryIntentActivities(new Intent("android.intent.action.DIAL"), 0);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return new ArrayList();
  }
  
  public static List<String> loadSmsPackages(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    for (;;)
    {
      int i1;
      try
      {
        paramContext = paramContext.getPackageManager().getInstalledPackages(4096).iterator();
        if (paramContext.hasNext())
        {
          PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
          if (localPackageInfo.requestedPermissions == null) {
            continue;
          }
          String[] arrayOfString = localPackageInfo.requestedPermissions;
          int i3 = arrayOfString.length;
          i1 = 0;
          k = 0;
          i = k;
          j = i;
          int m = k;
          int i2 = i;
          int n = j;
          if (i1 < i3)
          {
            String str = arrayOfString[i1];
            if (str.equals("android.permission.RECEIVE_MMS"))
            {
              if ((k == 0) || (i == 0)) {
                break label228;
              }
              n = 1;
              m = k;
              i2 = i;
            }
            else if (str.equals("android.permission.RECEIVE_SMS"))
            {
              if ((j == 0) || (i == 0)) {
                break label233;
              }
              m = 1;
              i2 = i;
              n = j;
            }
            else
            {
              if (!str.equals("android.permission.READ_SMS")) {
                break label240;
              }
              if ((j == 0) || (k == 0)) {
                break label238;
              }
              i2 = 1;
              m = k;
              n = j;
            }
          }
          if ((n == 0) || (m == 0) || (i2 == 0)) {
            continue;
          }
          localArrayList.add(localPackageInfo.packageName);
        }
        else
        {
          return localArrayList;
        }
      }
      catch (Exception paramContext)
      {
        return localArrayList;
      }
      label228:
      int j = 1;
      continue;
      label233:
      int k = 1;
      continue;
      label238:
      int i = 1;
      label240:
      i1 += 1;
    }
  }
  
  private static String obfuscate(String paramString)
  {
    if (paramString == null) {
      return "";
    }
    int k = paramString.length();
    int j = 0;
    int i = k;
    if (k > 100)
    {
      j = 1;
      i = 100;
    }
    paramString = new char[i];
    Arrays.fill(paramString, '*');
    if (j != 0)
    {
      paramString[(i - 2)] = 45;
      paramString[(i - 1)] = 62;
    }
    return new String(paramString);
  }
  
  public static void setNotificationChannel(String paramString)
  {
    channelId = paramString;
  }
  
  private void showNotification(final Context paramContext, boolean paramBoolean)
  {
    if ((isNotificationAccessEnabled(paramContext)) && (!isNotificationServiceBound())) {
      GNCSListenerService.requestRebind(paramContext);
    }
    if (!paramBoolean)
    {
      long l = System.currentTimeMillis();
      if (GNCSSettings.getSettings().getLastUserNotified(paramContext) > l - 86400000L)
      {
        GNCSCoreUtil.tracelog("Not notifying user about notification service not running since it has not been 24hours since the last time.");
        return;
      }
    }
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        Object localObject1;
        if (!GNCSUtil.this.isNotificationAccessEnabled(paramContext))
        {
          localObject1 = paramContext.getString(R.string.notification_access_required_message);
        }
        else
        {
          if (GNCSUtil.this.isNotificationServiceBound()) {
            return;
          }
          localObject1 = paramContext.getString(R.string.notification_access_terminated_message);
        }
        Object localObject2;
        if (Build.VERSION.SDK_INT >= 26)
        {
          localObject2 = new NotificationChannel("access_required", paramContext.getString(R.string.notification_access_required), 4);
          ((NotificationManager)paramContext.getSystemService("notification")).createNotificationChannel((NotificationChannel)localObject2);
          localObject1 = new Notification.Builder(paramContext).setSmallIcon(GNCSUtil.this.notificationResId).setContentTitle(paramContext.getString(R.string.notification_access_required)).setContentText((CharSequence)localObject1).setStyle(new Notification.BigTextStyle().bigText((CharSequence)localObject1)).setAutoCancel(true).setVibrate(new long[] { 500L, 1000L }).setPriority(2).setCategory("recommendation").setChannelId("access_required");
          if (!GNCSUtil.this.isNotificationAccessEnabled(paramContext)) {
            if ((GNCSUtil.hasLinkedDevices(paramContext)) && (Build.VERSION.SDK_INT >= 27))
            {
              localObject2 = new Intent(paramContext, GNCSNotificationAccessActivity.class);
              ((Notification.Builder)localObject1).setContentIntent(PendingIntent.getActivity(paramContext, 0, (Intent)localObject2, 134217728));
              GNCSCoreUtil.tracelog("Using smart notification dialog because we have linked devices.");
            }
            else
            {
              localObject2 = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
              ((Notification.Builder)localObject1).setContentIntent(PendingIntent.getActivity(paramContext, 0, (Intent)localObject2, 134217728));
              GNCSCoreUtil.tracelog("Using device settings because we do not have linked devices.");
            }
          }
          localObject1 = ((Notification.Builder)localObject1).build();
        }
        else
        {
          localObject1 = new NotificationCompat.Builder(paramContext).setSmallIcon(GNCSUtil.this.notificationResId).setContentTitle(paramContext.getString(R.string.notification_access_required)).setContentText((CharSequence)localObject1).setStyle(new NotificationCompat.BigTextStyle().bigText((CharSequence)localObject1)).setAutoCancel(true).setVibrate(new long[] { 500L, 1000L }).setPriority(2).setCategory("recommendation");
          if (!GNCSUtil.this.isNotificationAccessEnabled(paramContext))
          {
            localObject2 = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
            ((NotificationCompat.Builder)localObject1).setContentIntent(PendingIntent.getActivity(paramContext, 0, (Intent)localObject2, 134217728));
          }
          localObject1 = ((NotificationCompat.Builder)localObject1).build();
        }
        ((NotificationManager)paramContext.getSystemService("notification")).notify(98, (Notification)localObject1);
        paramContext.sendBroadcast(new Intent("com.garmin.android.gncs.NOTIFICATIONS_NOT_ENABLED"), GNCSCoreUtil.getPermission(paramContext));
        GNCSSettings.getSettings().setLastUserNotified(paramContext, System.currentTimeMillis());
        return;
      }
    }, 5000L);
  }
  
  public String getPackageDisplayName(Context paramContext, String paramString)
  {
    if (paramString.equals("com.garmin.gncs.phone.incoming")) {
      return paramContext.getString(R.string.incoming_call_type);
    }
    if (paramString.equals("com.garmin.gncs.phone.missed")) {
      return paramContext.getString(R.string.missed_call_type);
    }
    if (paramString.equals("com.garmin.gncs.voicemail")) {
      return paramContext.getString(R.string.voicemail_type);
    }
    if (paramString.equals("com.garmin.gncs.sms")) {
      return paramContext.getString(R.string.sms_type);
    }
    if (paramString.equals("com.garmin.gncs.calendar")) {
      return paramContext.getString(R.string.calendar_type);
    }
    if (paramString.equals("com.google.android.googlequicksearchbox")) {
      return "Google Now";
    }
    if (paramContext != null) {}
    try
    {
      paramContext = paramContext.getPackageManager();
      ApplicationInfo localApplicationInfo = paramContext.getApplicationInfo(paramString, 0);
      if (localApplicationInfo != null)
      {
        paramContext = paramContext.getApplicationLabel(localApplicationInfo).toString();
        return paramContext;
      }
      return paramString;
    }
    catch (Exception paramContext) {}
    return paramString;
    return paramString;
  }
  
  public Drawable getPackageDrawable(Context paramContext, String paramString)
  {
    if (paramString.equals("com.garmin.gncs.phone.incoming")) {
      return null;
    }
    if (paramString.equals("com.garmin.gncs.phone.missed")) {
      return null;
    }
    if (paramString.equals("com.garmin.gncs.voicemail")) {
      return null;
    }
    if (paramString.equals("com.garmin.gncs.sms")) {
      return null;
    }
    if (paramString.equals("com.garmin.gncs.calendar")) {
      return null;
    }
    paramContext = paramContext.getPackageManager();
    try
    {
      paramString = paramContext.getPackageInfo(paramString, 0);
      if (paramString != null)
      {
        paramContext = paramString.applicationInfo.loadIcon(paramContext);
        return paramContext;
      }
      return null;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return null;
  }
  
  public boolean isNotificationAccessEnabled(Context paramContext)
  {
    String str1 = Settings.Secure.getString(paramContext.getContentResolver(), "enabled_notification_listeners");
    String str2 = GNCSListenerService.class.getSimpleName();
    paramContext = paramContext.getPackageName();
    return (str1 != null) && (str1.contains(str2)) && (str1.contains(paramContext));
  }
  
  public boolean isNotificationServiceBound()
  {
    return GNCSListenerService.isNotificationServiceBound();
  }
  
  public String lookupContact(Context paramContext, String paramString)
  {
    if ((paramString != null) && (paramString.length() != 0))
    {
      if (ActivityCompat.checkSelfPermission(paramContext, "android.permission.READ_CONTACTS") != 0) {
        return paramString;
      }
      Object localObject4 = null;
      Object localObject2 = null;
      Object localObject3 = null;
      localObject1 = localObject2;
      for (;;)
      {
        try
        {
          localObject5 = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(paramString));
          localObject1 = localObject2;
          localObject5 = paramContext.getContentResolver().query((Uri)localObject5, new String[] { "display_name" }, null, null, null);
          paramContext = localObject4;
          if (localObject5 != null) {
            paramContext = localObject3;
          }
        }
        catch (Exception paramContext)
        {
          Object localObject5;
          paramContext = (Context)localObject1;
          continue;
        }
        try
        {
          if (((Cursor)localObject5).moveToFirst()) {
            paramContext = ((Cursor)localObject5).getString(((Cursor)localObject5).getColumnIndexOrThrow("display_name"));
          }
          localObject1 = paramContext;
          ((Cursor)localObject5).close();
        }
        finally
        {
          localObject1 = localObject2;
          ((Cursor)localObject5).close();
          localObject1 = localObject2;
        }
      }
      return localObject1;
    }
    return "";
  }
  
  public void notifyNotificationAccessRequired(Context paramContext)
  {
    notifyNotificationAccessRequired(paramContext, false);
  }
  
  public void notifyNotificationAccessRequired(Context paramContext, boolean paramBoolean)
  {
    notifyNotificationAccessRequired(paramContext, paramBoolean, false);
  }
  
  public void notifyNotificationAccessRequired(Context paramContext, boolean paramBoolean1, boolean paramBoolean2)
  {
    if ((isNotificationAccessEnabled(paramContext)) && (isNotificationServiceBound())) {
      return;
    }
    if ((paramBoolean2) && (Build.VERSION.SDK_INT >= 27))
    {
      CompanionDeviceManager localCompanionDeviceManager = (CompanionDeviceManager)paramContext.getSystemService(CompanionDeviceManager.class);
      if (localCompanionDeviceManager.getAssociations().size() > 0)
      {
        paramContext = new ComponentName(paramContext.getApplicationContext().getPackageName(), GNCSListenerService.class.getName());
        if (!localCompanionDeviceManager.hasNotificationAccess(paramContext)) {
          localCompanionDeviceManager.requestNotificationAccess(paramContext);
        }
      }
      else
      {
        showNotification(paramContext, paramBoolean1);
      }
    }
    else
    {
      showNotification(paramContext, paramBoolean1);
    }
  }
  
  public void notifySmsPermissionRequired(Context paramContext)
  {
    Object localObject = paramContext.getString(R.string.missing_sms_permission);
    Intent localIntent = new Intent();
    localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
    localIntent.setData(Uri.fromParts("package", paramContext.getPackageName(), null));
    if (Build.VERSION.SDK_INT >= 26)
    {
      NotificationChannel localNotificationChannel = new NotificationChannel("access_required", paramContext.getString(R.string.notification_access_required), 4);
      ((NotificationManager)paramContext.getSystemService("notification")).createNotificationChannel(localNotificationChannel);
      localObject = new Notification.Builder(paramContext).setSmallIcon(this.notificationResId).setContentTitle(paramContext.getString(R.string.notification_access_required)).setContentText((CharSequence)localObject).setStyle(new Notification.BigTextStyle().bigText((CharSequence)localObject)).setAutoCancel(true).setVibrate(new long[] { 500L, 1000L }).setPriority(2).setCategory("recommendation").addAction(0, paramContext.getString(R.string.open_settings), PendingIntent.getActivity(paramContext, 0, localIntent, 134217728)).setChannelId("access_required").build();
    }
    else
    {
      localObject = new NotificationCompat.Builder(paramContext).setSmallIcon(this.notificationResId).setContentTitle(paramContext.getString(R.string.notification_access_required)).setContentText((CharSequence)localObject).setStyle(new NotificationCompat.BigTextStyle().bigText((CharSequence)localObject)).setAutoCancel(true).setVibrate(new long[] { 500L, 1000L }).setPriority(2).setCategory("recommendation").addAction(0, paramContext.getString(R.string.open_settings), PendingIntent.getActivity(paramContext, 0, localIntent, 134217728)).build();
    }
    ((NotificationManager)paramContext.getSystemService("notification")).notify(99, (Notification)localObject);
  }
  
  public void setNotificationIconResId(int paramInt)
  {
    this.notificationResId = paramInt;
  }
  
  public static class InjectorConfiguration
    extends Configuration
  {
    public InjectorConfiguration() {}
    
    public void configure()
    {
      bindSingleton(GNCSUtil.class, new Factory()
      {
        public GNCSUtil create()
        {
          return new GNCSUtil();
        }
      });
    }
  }
  
  public final class Packages
  {
    public static final String CALENDAR_PACKAGE_NAME = "com.garmin.gncs.calendar";
    public static final String INCOMING_PACKAGE_NAME = "com.garmin.gncs.phone.incoming";
    public static final String MISSED_PACKAGE_NAME = "com.garmin.gncs.phone.missed";
    public static final String SMS_PACKAGE_NAME = "com.garmin.gncs.sms";
    public static final String VOICEMAIL_PACKAGE_NAME = "com.garmin.gncs.voicemail";
    
    public Packages() {}
  }
}
