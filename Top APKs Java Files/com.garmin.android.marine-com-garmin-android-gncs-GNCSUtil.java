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
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat.b;
import android.support.v4.app.NotificationCompat.c;
import android.text.TextUtils;
import com.garmin.android.framework.util.designpattern.Factory;
import com.garmin.android.framework.util.inject.Configuration;
import com.garmin.android.gncs.persistence.GNCSNotificationAction;
import com.garmin.android.gncs.settings.GNCSConfig;
import com.garmin.android.gncs.settings.GNCSNotificationAccessActivity;
import com.garmin.android.gncs.settings.GNCSSettings;
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
    com.garmin.android.g.a.a("GNCSDump", "=================================================================================");
    Object localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append(paramString);
    ((StringBuilder)localObject1).append(" : ");
    ((StringBuilder)localObject1).append(System.currentTimeMillis());
    com.garmin.android.g.a.a("GNCSDump", ((StringBuilder)localObject1).toString());
    com.garmin.android.g.a.a("GNCSDump", "=================================================================================");
    label387:
    label428:
    int i;
    for (;;)
    {
      try
      {
        paramString = new StringBuilder("PackageName: ");
        paramString.append(paramStatusBarNotification.getPackageName());
        com.garmin.android.g.a.a("GNCSDump", paramString.toString());
        paramString = new StringBuilder("Id: ");
        paramString.append(paramStatusBarNotification.getId());
        com.garmin.android.g.a.a("GNCSDump", paramString.toString());
        if (Build.VERSION.SDK_INT >= 21)
        {
          paramString = new StringBuilder("Key: ");
          paramString.append(obfuscate(paramStatusBarNotification.getKey()));
          com.garmin.android.g.a.a("GNCSDump", paramString.toString());
          paramString = new StringBuilder("GroupKey: ");
          paramString.append(obfuscate(paramStatusBarNotification.getGroupKey()));
          com.garmin.android.g.a.a("GNCSDump", paramString.toString());
        }
        if (Build.VERSION.SDK_INT >= 24)
        {
          localObject1 = new StringBuilder("IsGroup: ");
          if (!paramStatusBarNotification.isGroup()) {
            break label3871;
          }
          paramString = "Yes";
          ((StringBuilder)localObject1).append(paramString);
          com.garmin.android.g.a.a("GNCSDump", ((StringBuilder)localObject1).toString());
          paramString = new StringBuilder("OverrideGroupKey: ");
          paramString.append(obfuscate(paramStatusBarNotification.getOverrideGroupKey()));
          com.garmin.android.g.a.a("GNCSDump", paramString.toString());
        }
        paramString = new StringBuilder("PostTime: ");
        paramString.append(paramStatusBarNotification.getPostTime());
        com.garmin.android.g.a.a("GNCSDump", paramString.toString());
        paramString = new StringBuilder("Tag: ");
        paramString.append(obfuscate(paramStatusBarNotification.getTag()));
        com.garmin.android.g.a.a("GNCSDump", paramString.toString());
        localObject1 = new StringBuilder("IsClearable: ");
        if (!paramStatusBarNotification.isClearable()) {
          break label3878;
        }
        paramString = "Yes";
        ((StringBuilder)localObject1).append(paramString);
        com.garmin.android.g.a.a("GNCSDump", ((StringBuilder)localObject1).toString());
        localObject1 = new StringBuilder("IsOngoing: ");
        if (!paramStatusBarNotification.isOngoing()) {
          break label3885;
        }
        paramString = "Yes";
        ((StringBuilder)localObject1).append(paramString);
        com.garmin.android.g.a.a("GNCSDump", ((StringBuilder)localObject1).toString());
        localObject1 = paramStatusBarNotification.getNotification();
        if (localObject1 != null)
        {
          if ((((Notification)localObject1).actions != null) && (((Notification)localObject1).actions.length > 0))
          {
            com.garmin.android.g.a.a("GNCSDump", "Actions >>>");
            i = 0;
            label482:
            if (i < ((Notification)localObject1).actions.length)
            {
              paramString = localObject1.actions[i];
              if (paramString == null) {
                break label3906;
              }
              paramStatusBarNotification = new StringBuilder("     title: ");
              paramStatusBarNotification.append(paramString.title);
              com.garmin.android.g.a.a("GNCSDump", paramStatusBarNotification.toString());
              localObject2 = new StringBuilder("        actionIntent: ");
              if (paramString.actionIntent == null) {
                break label3892;
              }
              paramStatusBarNotification = "Yes";
              label556:
              ((StringBuilder)localObject2).append(paramStatusBarNotification);
              com.garmin.android.g.a.a("GNCSDump", ((StringBuilder)localObject2).toString());
              if (Build.VERSION.SDK_INT >= 21)
              {
                paramStatusBarNotification = paramString.getExtras();
                if (paramStatusBarNotification != null)
                {
                  paramString = paramStatusBarNotification.keySet().iterator();
                  if (paramString.hasNext())
                  {
                    localObject2 = (String)paramString.next();
                    if (paramStatusBarNotification.get((String)localObject2) != null)
                    {
                      localObject3 = new StringBuilder("        Extra ");
                      ((StringBuilder)localObject3).append((String)localObject2);
                      ((StringBuilder)localObject3).append(": ");
                      ((StringBuilder)localObject3).append(paramStatusBarNotification.get((String)localObject2).toString());
                      com.garmin.android.g.a.a("GNCSDump", ((StringBuilder)localObject3).toString());
                      continue;
                    }
                    localObject3 = new StringBuilder("        Extra ");
                    ((StringBuilder)localObject3).append((String)localObject2);
                    ((StringBuilder)localObject3).append(": null");
                    com.garmin.android.g.a.a("GNCSDump", ((StringBuilder)localObject3).toString());
                    continue;
                  }
                }
                paramStatusBarNotification = localObject1.actions[i].getRemoteInputs();
                if ((paramStatusBarNotification != null) && (paramStatusBarNotification.length > 0)) {
                  com.garmin.android.g.a.a("GNCSDump", "        Action requires remote inputs");
                }
              }
              if (Build.VERSION.SDK_INT < 24) {
                break label3906;
              }
              paramString = new StringBuilder("        Allow Generated Replies: ");
              if (!localObject1.actions[i].getAllowGeneratedReplies()) {
                break label3899;
              }
              paramStatusBarNotification = "Yes";
              label789:
              paramString.append(paramStatusBarNotification);
              com.garmin.android.g.a.a("GNCSDump", paramString.toString());
              break label3906;
            }
          }
          if ((Build.VERSION.SDK_INT >= 21) && (((Notification)localObject1).category != null)) {
            paramStatusBarNotification = ((Notification)localObject1).category;
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
            break label3915;
            label1197:
            continue;
            com.garmin.android.g.a.a("GNCSDump", "Category: Transport");
            continue;
            com.garmin.android.g.a.a("GNCSDump", "Category: System");
            continue;
            com.garmin.android.g.a.a("GNCSDump", "Category: Status");
            continue;
            com.garmin.android.g.a.a("GNCSDump", "Category: Social");
            continue;
            com.garmin.android.g.a.a("GNCSDump", "Category: Service");
            continue;
            com.garmin.android.g.a.a("GNCSDump", "Category: Reminder");
            continue;
            com.garmin.android.g.a.a("GNCSDump", "Category: Recommendation");
            continue;
            com.garmin.android.g.a.a("GNCSDump", "Category: Promo");
            continue;
            com.garmin.android.g.a.a("GNCSDump", "Category: Progress");
            continue;
            com.garmin.android.g.a.a("GNCSDump", "Category: Message");
            continue;
            com.garmin.android.g.a.a("GNCSDump", "Category: Event");
            continue;
            com.garmin.android.g.a.a("GNCSDump", "Category: Error");
            continue;
            com.garmin.android.g.a.a("GNCSDump", "Category: Email");
            continue;
            com.garmin.android.g.a.a("GNCSDump", "Category: Call");
            continue;
            com.garmin.android.g.a.a("GNCSDump", "Category: Alarm");
            continue;
            paramStatusBarNotification = new StringBuilder("Category: ");
            paramStatusBarNotification.append(((Notification)localObject1).category);
            com.garmin.android.g.a.a("GNCSDump", paramStatusBarNotification.toString());
            continue;
            com.garmin.android.g.a.a("GNCSDump", "Category: Unknown");
            if (((Notification)localObject1).contentIntent != null)
            {
              paramStatusBarNotification = new StringBuilder("ContentIntent: ");
              paramStatusBarNotification.append(((Notification)localObject1).contentIntent.getCreatorPackage());
              com.garmin.android.g.a.a("GNCSDump", paramStatusBarNotification.toString());
            }
            break;
          }
        }
      }
      catch (Throwable paramStatusBarNotification)
      {
        Object localObject2;
        Object localObject3;
        Object localObject4;
        paramGNCSNotificationInfo = new StringBuilder("Throwable thrown while dumping notification: ");
        paramGNCSNotificationInfo.append(paramStatusBarNotification.getMessage());
        com.garmin.android.g.a.a("GNCSDump", paramGNCSNotificationInfo.toString());
        return;
      }
      catch (Exception paramStatusBarNotification)
      {
        paramGNCSNotificationInfo = new StringBuilder("Exception thrown while dumping notification: ");
        paramGNCSNotificationInfo.append(paramStatusBarNotification.getMessage());
        com.garmin.android.g.a.a("GNCSDump", paramGNCSNotificationInfo.toString());
        return;
      }
      try
      {
        paramStatusBarNotification = (Intent)PendingIntent.class.getDeclaredMethod("getIntent", new Class[0]).invoke(((Notification)localObject1).contentIntent, new Object[0]);
        if (paramStatusBarNotification != null)
        {
          paramString = new StringBuilder("     Action: ");
          paramString.append(paramStatusBarNotification.getAction());
          com.garmin.android.g.a.a("GNCSDump", paramString.toString());
          paramString = new StringBuilder("     Component: ");
          paramString.append(paramStatusBarNotification.getComponent().flattenToShortString());
          com.garmin.android.g.a.a("GNCSDump", paramString.toString());
        }
      }
      catch (NoSuchMethodException|InvocationTargetException|IllegalAccessException paramStatusBarNotification) {}
    }
    if (((Notification)localObject1).deleteIntent != null)
    {
      paramStatusBarNotification = new StringBuilder("DeleteIntent: ");
      paramStatusBarNotification.append(((Notification)localObject1).deleteIntent.getCreatorPackage());
      com.garmin.android.g.a.a("GNCSDump", paramStatusBarNotification.toString());
    }
    try
    {
      paramStatusBarNotification = (Intent)PendingIntent.class.getDeclaredMethod("getIntent", new Class[0]).invoke(((Notification)localObject1).deleteIntent, new Object[0]);
      if (paramStatusBarNotification != null)
      {
        paramString = new StringBuilder("     Action: ");
        paramString.append(paramStatusBarNotification.getAction());
        com.garmin.android.g.a.a("GNCSDump", paramString.toString());
        if (paramStatusBarNotification.getComponent() != null)
        {
          paramString = new StringBuilder("     Component: ");
          paramString.append(paramStatusBarNotification.getComponent().flattenToShortString());
          com.garmin.android.g.a.a("GNCSDump", paramString.toString());
        }
      }
    }
    catch (NoSuchMethodException|InvocationTargetException|IllegalAccessException paramStatusBarNotification)
    {
      label1714:
      label2104:
      label2141:
      label2197:
      label2242:
      label2289:
      label2336:
      label2379:
      label2426:
      label2471:
      label2514:
      label2559:
      label2602:
      label2688:
      label2776:
      label2872:
      label2938:
      for (;;) {}
    }
    if (((Notification)localObject1).extras != null)
    {
      com.garmin.android.g.a.a("GNCSDump", "Extras >>>");
      localObject2 = ((Notification)localObject1).extras.keySet().iterator();
      if (((Iterator)localObject2).hasNext())
      {
        localObject3 = (String)((Iterator)localObject2).next();
        if (((String)localObject3).equals("android.textLines"))
        {
          paramString = ((Notification)localObject1).extras.getCharSequenceArray("android.textLines");
          paramStatusBarNotification = new StringBuilder();
          i = 0;
          while (i < paramString.length)
          {
            if (paramStatusBarNotification.length() > 0) {
              paramStatusBarNotification.append("\n");
            }
            paramStatusBarNotification.append(paramString[i]);
            i += 1;
          }
          paramString = new StringBuilder("     ");
          paramString.append((String)localObject3);
          paramString.append(":\n");
          paramStatusBarNotification = obfuscate(paramStatusBarNotification.toString());
        }
        else
        {
          localObject4 = ((Notification)localObject1).extras.get((String)localObject3);
          if (localObject4 == null) {
            break label4054;
          }
          paramStatusBarNotification = localObject4.toString();
        }
      }
    }
    switch (((String)localObject3).hashCode())
    {
    case 1297754027: 
      if (((String)localObject3).equals("android.title.big")) {
        i = 8;
      }
      break;
    case 967367924: 
      if (((String)localObject3).equals("android.summaryText")) {
        i = 5;
      }
      break;
    case 150183834: 
      if (((String)localObject3).equals("android.infoText")) {
        i = 2;
      }
      break;
    case 22486478: 
      if (((String)localObject3).equals("android.bigText")) {
        i = 0;
      }
      break;
    case -489846293: 
      if (((String)localObject3).equals("android.messages")) {
        i = 3;
      }
      break;
    case -1036913332: 
      if (((String)localObject3).equals("android.text")) {
        i = 6;
      }
      break;
    case -1730887922: 
      if (((String)localObject3).equals("android.subText")) {
        i = 4;
      }
      break;
    case -1897567786: 
      if (((String)localObject3).equals("android.conversationTitle")) {
        i = 1;
      }
      break;
    case -2079427047: 
      if (((String)localObject3).equals("android.title"))
      {
        i = 7;
        break label3997;
        paramStatusBarNotification = obfuscate(paramStatusBarNotification);
        paramString = new StringBuilder("     ");
        paramString.append((String)localObject3);
        paramString.append(": ");
        if (localObject4 != null) {
          break label4051;
        }
        paramStatusBarNotification = "";
        paramString.append(paramStatusBarNotification);
        com.garmin.android.g.a.a("GNCSDump", paramString.toString());
        break label4054;
        com.garmin.android.g.a.a("GNCSDump", "Flags>>>>>");
        paramString = new StringBuilder("     AUTO_CANCEL: ");
        if ((((Notification)localObject1).flags & 0x10) != 16) {
          break label4057;
        }
        paramStatusBarNotification = "Yes";
        paramString.append(paramStatusBarNotification);
        com.garmin.android.g.a.a("GNCSDump", paramString.toString());
        paramString = new StringBuilder("     FOREGROUND_SERVICE: ");
        if ((((Notification)localObject1).flags & 0x40) != 64) {
          break label4064;
        }
        paramStatusBarNotification = "Yes";
        paramString.append(paramStatusBarNotification);
        com.garmin.android.g.a.a("GNCSDump", paramString.toString());
        paramString = new StringBuilder("     GROUP_SUMMARY: ");
        if ((((Notification)localObject1).flags & 0x200) != 512) {
          break label4071;
        }
        paramStatusBarNotification = "Yes";
        paramString.append(paramStatusBarNotification);
        com.garmin.android.g.a.a("GNCSDump", paramString.toString());
        paramString = new StringBuilder("     HIGH_PRIORITY: ");
        if ((((Notification)localObject1).flags & 0x80) != 128) {
          break label4078;
        }
        paramStatusBarNotification = "Yes";
        paramString.append(paramStatusBarNotification);
        com.garmin.android.g.a.a("GNCSDump", paramString.toString());
        paramString = new StringBuilder("     INSISTENT: ");
        if ((((Notification)localObject1).flags & 0x4) != 4) {
          break label4085;
        }
        paramStatusBarNotification = "Yes";
        paramString.append(paramStatusBarNotification);
        com.garmin.android.g.a.a("GNCSDump", paramString.toString());
        paramString = new StringBuilder("     LOCAL_ONLY: ");
        if ((((Notification)localObject1).flags & 0x100) != 256) {
          break label4092;
        }
        paramStatusBarNotification = "Yes";
        paramString.append(paramStatusBarNotification);
        com.garmin.android.g.a.a("GNCSDump", paramString.toString());
        paramString = new StringBuilder("     NO_CLEAR: ");
        if ((((Notification)localObject1).flags & 0x20) != 32) {
          break label4099;
        }
        paramStatusBarNotification = "Yes";
        paramString.append(paramStatusBarNotification);
        com.garmin.android.g.a.a("GNCSDump", paramString.toString());
        paramString = new StringBuilder("     ONGOING_EVENT: ");
        if ((((Notification)localObject1).flags & 0x2) != 2) {
          break label4106;
        }
        paramStatusBarNotification = "Yes";
        paramString.append(paramStatusBarNotification);
        com.garmin.android.g.a.a("GNCSDump", paramString.toString());
        paramString = new StringBuilder("     ONLY_ALERT_ONCE: ");
        if ((((Notification)localObject1).flags & 0x8) != 8) {
          break label4113;
        }
        paramStatusBarNotification = "Yes";
        paramString.append(paramStatusBarNotification);
        com.garmin.android.g.a.a("GNCSDump", paramString.toString());
        paramString = new StringBuilder("     SHOW_LIGHTS: ");
        if ((((Notification)localObject1).flags & 0x1) != 1) {
          break label4120;
        }
        paramStatusBarNotification = "Yes";
        paramString.append(paramStatusBarNotification);
        com.garmin.android.g.a.a("GNCSDump", paramString.toString());
        paramStatusBarNotification = new StringBuilder("NumberEvents: ");
        paramStatusBarNotification.append(((Notification)localObject1).number);
        com.garmin.android.g.a.a("GNCSDump", paramStatusBarNotification.toString());
        switch (((Notification)localObject1).priority)
        {
        case 2: 
          break;
          com.garmin.android.g.a.a("GNCSDump", "Priority: Max");
          break;
        case 1: 
          com.garmin.android.g.a.a("GNCSDump", "Priority: High");
          break;
        case 0: 
          com.garmin.android.g.a.a("GNCSDump", "Priority: Default");
          break;
        case -1: 
          com.garmin.android.g.a.a("GNCSDump", "Priority: Low");
          break;
        case -2: 
          com.garmin.android.g.a.a("GNCSDump", "Priority: Min");
          break label2776;
          paramStatusBarNotification = new StringBuilder("Priority: ");
          paramStatusBarNotification.append(((Notification)localObject1).priority);
          com.garmin.android.g.a.a("GNCSDump", paramStatusBarNotification.toString());
          paramString = new StringBuilder("TickerText: ");
          if (((Notification)localObject1).tickerText == null) {
            paramStatusBarNotification = "";
          } else {
            paramStatusBarNotification = obfuscate(((Notification)localObject1).tickerText.toString());
          }
          paramString.append(paramStatusBarNotification);
          com.garmin.android.g.a.a("GNCSDump", paramString.toString());
          if (Build.VERSION.SDK_INT >= 21) {}
          switch (((Notification)localObject1).visibility)
          {
          case 1: 
            break;
            com.garmin.android.g.a.a("GNCSDump", "Visibility: Public");
            break;
          case 0: 
            com.garmin.android.g.a.a("GNCSDump", "Visibility: Private");
            break;
          case -1: 
            com.garmin.android.g.a.a("GNCSDump", "Visibility: Secret");
            break label2938;
            paramStatusBarNotification = new StringBuilder("Visibility: ");
            paramStatusBarNotification.append(((Notification)localObject1).visibility);
            com.garmin.android.g.a.a("GNCSDump", paramStatusBarNotification.toString());
            paramStatusBarNotification = new StringBuilder("When: ");
            paramStatusBarNotification.append(((Notification)localObject1).when);
            com.garmin.android.g.a.a("GNCSDump", paramStatusBarNotification.toString());
            com.garmin.android.g.a.a("GNCSDump", "");
            com.garmin.android.g.a.a("GNCSDump", "=== GNCSParsedNotification (Parsed Notification) ===");
            com.garmin.android.g.a.a("GNCSDump", "");
            paramStatusBarNotification = new StringBuilder("title: ");
            paramStatusBarNotification.append(obfuscate(paramGNCSNotificationInfo.title));
            com.garmin.android.g.a.a("GNCSDump", paramStatusBarNotification.toString());
            paramStatusBarNotification = new StringBuilder("subTitle: ");
            paramStatusBarNotification.append(obfuscate(paramGNCSNotificationInfo.subTitle));
            com.garmin.android.g.a.a("GNCSDump", paramStatusBarNotification.toString());
            paramStatusBarNotification = new StringBuilder("message: ");
            paramStatusBarNotification.append(obfuscate(paramGNCSNotificationInfo.message));
            com.garmin.android.g.a.a("GNCSDump", paramStatusBarNotification.toString());
            paramStatusBarNotification = new StringBuilder("positiveAction: ");
            paramStatusBarNotification.append(paramGNCSNotificationInfo.positiveAction);
            com.garmin.android.g.a.a("GNCSDump", paramStatusBarNotification.toString());
            paramStatusBarNotification = new StringBuilder("negativeAction: ");
            paramStatusBarNotification.append(paramGNCSNotificationInfo.negativeAction);
            com.garmin.android.g.a.a("GNCSDump", paramStatusBarNotification.toString());
            paramStatusBarNotification = new StringBuilder("status: ");
            paramStatusBarNotification.append(paramGNCSNotificationInfo.status.name());
            com.garmin.android.g.a.a("GNCSDump", paramStatusBarNotification.toString());
            com.garmin.android.g.a.a("GNCSDump", "");
            paramStatusBarNotification = new StringBuilder("cacheId: ");
            paramStatusBarNotification.append(paramGNCSNotificationInfo.cacheId);
            com.garmin.android.g.a.a("GNCSDump", paramStatusBarNotification.toString());
            paramStatusBarNotification = new StringBuilder("cacheKey: ");
            paramStatusBarNotification.append(obfuscate(paramGNCSNotificationInfo.cacheKey));
            com.garmin.android.g.a.a("GNCSDump", paramStatusBarNotification.toString());
            paramStatusBarNotification = new StringBuilder("notificationId: ");
            paramStatusBarNotification.append(paramGNCSNotificationInfo.notificationId);
            com.garmin.android.g.a.a("GNCSDump", paramStatusBarNotification.toString());
            paramStatusBarNotification = new StringBuilder("notificationKey: ");
            paramStatusBarNotification.append(obfuscate(paramGNCSNotificationInfo.notificationKey));
            com.garmin.android.g.a.a("GNCSDump", paramStatusBarNotification.toString());
            paramStatusBarNotification = new StringBuilder("packageName: ");
            paramStatusBarNotification.append(paramGNCSNotificationInfo.packageName);
            com.garmin.android.g.a.a("GNCSDump", paramStatusBarNotification.toString());
            paramStatusBarNotification = new StringBuilder("Tag: ");
            paramStatusBarNotification.append(obfuscate(paramGNCSNotificationInfo.tag));
            com.garmin.android.g.a.a("GNCSDump", paramStatusBarNotification.toString());
            if (paramGNCSNotificationInfo != null)
            {
              paramString = new StringBuilder("isGroup: ");
              if (!paramGNCSNotificationInfo.isGroup) {
                break label4133;
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
      com.garmin.android.g.a.a("GNCSDump", paramString.toString());
      paramStatusBarNotification = new StringBuilder("numEvents: ");
      paramStatusBarNotification.append(paramGNCSNotificationInfo.numEvents);
      com.garmin.android.g.a.a("GNCSDump", paramStatusBarNotification.toString());
      paramStatusBarNotification = new StringBuilder("priority: ");
      paramStatusBarNotification.append(paramGNCSNotificationInfo.priority);
      com.garmin.android.g.a.a("GNCSDump", paramStatusBarNotification.toString());
      paramStatusBarNotification = new StringBuilder("tickerText: ");
      paramStatusBarNotification.append(obfuscate(paramGNCSNotificationInfo.tickerText));
      com.garmin.android.g.a.a("GNCSDump", paramStatusBarNotification.toString());
      paramStatusBarNotification = new StringBuilder("postTime: ");
      paramStatusBarNotification.append(paramGNCSNotificationInfo.postTime);
      com.garmin.android.g.a.a("GNCSDump", paramStatusBarNotification.toString());
      paramStatusBarNotification = new StringBuilder("when: ");
      paramStatusBarNotification.append(paramGNCSNotificationInfo.when);
      com.garmin.android.g.a.a("GNCSDump", paramStatusBarNotification.toString());
      paramStatusBarNotification = new StringBuilder("notificationType: ");
      paramStatusBarNotification.append(paramGNCSNotificationInfo.type);
      com.garmin.android.g.a.a("GNCSDump", paramStatusBarNotification.toString());
      paramStatusBarNotification = new StringBuilder("flags: ");
      paramStatusBarNotification.append(paramGNCSNotificationInfo.notificationFlags);
      com.garmin.android.g.a.a("GNCSDump", paramStatusBarNotification.toString());
      paramStatusBarNotification = new StringBuilder("phoneNumberFlags: ");
      paramStatusBarNotification.append(paramGNCSNotificationInfo.extraFlags);
      com.garmin.android.g.a.a("GNCSDump", paramStatusBarNotification.toString());
      paramStatusBarNotification = new StringBuilder("notificationFlags: ");
      paramStatusBarNotification.append(paramGNCSNotificationInfo.notificationFlags);
      com.garmin.android.g.a.a("GNCSDump", paramStatusBarNotification.toString());
      paramString = new StringBuilder("groupKey: ");
      if (paramGNCSNotificationInfo.groupKey == null) {
        paramStatusBarNotification = "None";
      } else {
        paramStatusBarNotification = paramGNCSNotificationInfo.groupKey;
      }
      paramString.append(paramStatusBarNotification);
      com.garmin.android.g.a.a("GNCSDump", paramString.toString());
      paramString = new StringBuilder("overrideGroupKey: ");
      if (paramGNCSNotificationInfo.overrideGroupKey == null) {
        paramStatusBarNotification = "None";
      } else {
        paramStatusBarNotification = obfuscate(paramGNCSNotificationInfo.overrideGroupKey);
      }
      paramString.append(paramStatusBarNotification);
      com.garmin.android.g.a.a("GNCSDump", paramString.toString());
      paramStatusBarNotification = new StringBuilder("phone number: ");
      paramStatusBarNotification.append(obfuscate(paramGNCSNotificationInfo.phoneNumber));
      com.garmin.android.g.a.a("GNCSDump", paramStatusBarNotification.toString());
      return;
      label3871:
      paramString = "No";
      break;
      label3878:
      paramString = "No";
      break label387;
      label3885:
      paramString = "No";
      break label428;
      label3892:
      paramStatusBarNotification = "No";
      break label556;
      label3899:
      paramStatusBarNotification = "No";
      break label789;
      label3906:
      i += 1;
      break label482;
      i = -1;
      label3915:
      switch (i)
      {
      }
      break label1197;
      i = -1;
      label3997:
      switch (i)
      {
      }
      break label2104;
      label4051:
      break label2141;
      label4054:
      break label1714;
      label4057:
      paramStatusBarNotification = "No";
      break label2197;
      label4064:
      paramStatusBarNotification = "No";
      break label2242;
      label4071:
      paramStatusBarNotification = "No";
      break label2289;
      label4078:
      paramStatusBarNotification = "No";
      break label2336;
      label4085:
      paramStatusBarNotification = "No";
      break label2379;
      label4092:
      paramStatusBarNotification = "No";
      break label2426;
      label4099:
      paramStatusBarNotification = "No";
      break label2471;
      label4106:
      paramStatusBarNotification = "No";
      break label2514;
      label4113:
      paramStatusBarNotification = "No";
      break label2559;
      label4120:
      paramStatusBarNotification = "No";
      break label2602;
      break label2688;
      break label2872;
      label4133:
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
  
  @RequiresApi(api=26)
  private static boolean hasLinkedDevices(Context paramContext)
  {
    return ((CompanionDeviceManager)paramContext.getSystemService(CompanionDeviceManager.class)).getAssociations().size() > 0;
  }
  
  public static boolean isDefaultSmsPackage(@NonNull Context paramContext, @NonNull String paramString)
  {
    if (paramContext != null)
    {
      paramContext = Telephony.Sms.getDefaultSmsPackage(paramContext.getApplicationContext());
      return (paramContext != null) && (paramString != null) && (paramContext.equals(paramString));
    }
    return false;
  }
  
  public static boolean isDialerPackage(@Nullable Context paramContext, @NonNull String paramString)
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
          i = 0;
          j = 0;
          m = k;
          i2 = i;
          n = j;
          if (i1 < i3)
          {
            String str = arrayOfString[i1];
            if (str.equals("android.permission.RECEIVE_MMS"))
            {
              if (k == 0) {
                break label213;
              }
              if (i == 0) {
                break label213;
              }
            }
            else
            {
              if (str.equals("android.permission.RECEIVE_SMS"))
              {
                if (j == 0) {
                  break label230;
                }
                if (i != 0) {
                  break label218;
                }
                break label230;
              }
              if (!str.equals("android.permission.READ_SMS")) {
                break label249;
              }
              if (j == 0) {
                break label247;
              }
              if (k != 0) {
                break label235;
              }
              break label247;
            }
          }
          else
          {
            if ((n == 0) || (m == 0) || (i2 == 0)) {
              continue;
            }
            localArrayList.add(localPackageInfo.packageName);
          }
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
      int n = 1;
      int m = k;
      int i2 = i;
      continue;
      label213:
      int j = 1;
      continue;
      label218:
      m = 1;
      i2 = i;
      n = j;
      continue;
      label230:
      int k = 1;
      continue;
      label235:
      i2 = 1;
      m = k;
      n = j;
      continue;
      label247:
      int i = 1;
      label249:
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
      paramString[98] = 45;
      paramString[99] = 62;
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
        com.garmin.android.g.a.a("Not notifying user about notification service not running since it has not been 24hours since the last time.");
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
            if (GNCSUtil.hasLinkedDevices(paramContext))
            {
              localObject2 = new Intent(paramContext, GNCSNotificationAccessActivity.class);
              ((Notification.Builder)localObject1).setContentIntent(PendingIntent.getActivity(paramContext, 0, (Intent)localObject2, 134217728));
              com.garmin.android.g.a.a("Using smart notification dialog because we have linked devices.");
            }
            else
            {
              localObject2 = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
              ((Notification.Builder)localObject1).setContentIntent(PendingIntent.getActivity(paramContext, 0, (Intent)localObject2, 134217728));
              com.garmin.android.g.a.a("Using device settings because we do not have linked devices.");
            }
          }
          localObject1 = ((Notification.Builder)localObject1).build();
        }
        else
        {
          localObject1 = new NotificationCompat.c(paramContext).a(GNCSUtil.this.notificationResId).a(paramContext.getString(R.string.notification_access_required)).b((CharSequence)localObject1).a(new NotificationCompat.b().a((CharSequence)localObject1));
          ((NotificationCompat.c)localObject1).b(16);
          localObject1 = ((NotificationCompat.c)localObject1).a(new long[] { 500L, 1000L });
          ((NotificationCompat.c)localObject1).k = 2;
          ((NotificationCompat.c)localObject1).z = "recommendation";
          if (!GNCSUtil.this.isNotificationAccessEnabled(paramContext))
          {
            localObject2 = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
            ((NotificationCompat.c)localObject1).e = PendingIntent.getActivity(paramContext, 0, (Intent)localObject2, 134217728);
          }
          localObject1 = ((NotificationCompat.c)localObject1).b();
        }
        ((NotificationManager)paramContext.getSystemService("notification")).notify(98, (Notification)localObject1);
        paramContext.sendBroadcast(new Intent("com.garmin.android.gncs.NOTIFICATIONS_NOT_ENABLED"), com.garmin.android.g.a.a(paramContext));
        GNCSSettings.getSettings().setLastUserNotified(paramContext, System.currentTimeMillis());
        return;
      }
    }, 5000L);
  }
  
  public String getPackageDisplayName(@NonNull Context paramContext, String paramString)
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
      if (android.support.v4.app.a.a(paramContext, "android.permission.READ_CONTACTS") != 0) {
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
    if ((paramBoolean2) && (Build.VERSION.SDK_INT >= 26))
    {
      CompanionDeviceManager localCompanionDeviceManager = (CompanionDeviceManager)paramContext.getSystemService(CompanionDeviceManager.class);
      if (localCompanionDeviceManager.getAssociations().size() > 0)
      {
        paramContext = new ComponentName(paramContext.getApplicationContext().getPackageName(), GNCSListenerService.class.getName());
        if (!localCompanionDeviceManager.hasNotificationAccess(paramContext)) {
          localCompanionDeviceManager.requestNotificationAccess(paramContext);
        }
        return;
      }
      showNotification(paramContext, paramBoolean1);
      return;
    }
    showNotification(paramContext, paramBoolean1);
  }
  
  public void notifySmsPermissionRequired(Context paramContext)
  {
    Object localObject2 = paramContext.getString(R.string.missing_sms_permission);
    Object localObject1 = new Intent();
    ((Intent)localObject1).setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
    ((Intent)localObject1).setData(Uri.fromParts("package", paramContext.getPackageName(), null));
    if (Build.VERSION.SDK_INT >= 26)
    {
      NotificationChannel localNotificationChannel = new NotificationChannel("access_required", paramContext.getString(R.string.notification_access_required), 4);
      ((NotificationManager)paramContext.getSystemService("notification")).createNotificationChannel(localNotificationChannel);
      localObject1 = new Notification.Builder(paramContext).setSmallIcon(this.notificationResId).setContentTitle(paramContext.getString(R.string.notification_access_required)).setContentText((CharSequence)localObject2).setStyle(new Notification.BigTextStyle().bigText((CharSequence)localObject2)).setAutoCancel(true).setVibrate(new long[] { 500L, 1000L }).setPriority(2).setCategory("recommendation").addAction(0, paramContext.getString(R.string.open_settings), PendingIntent.getActivity(paramContext, 0, (Intent)localObject1, 134217728)).setChannelId("access_required").build();
    }
    else
    {
      localObject2 = new NotificationCompat.c(paramContext).a(this.notificationResId).a(paramContext.getString(R.string.notification_access_required)).b((CharSequence)localObject2).a(new NotificationCompat.b().a((CharSequence)localObject2));
      ((NotificationCompat.c)localObject2).b(16);
      localObject2 = ((NotificationCompat.c)localObject2).a(new long[] { 500L, 1000L });
      ((NotificationCompat.c)localObject2).k = 2;
      ((NotificationCompat.c)localObject2).z = "recommendation";
      localObject1 = ((NotificationCompat.c)localObject2).a(0, paramContext.getString(R.string.open_settings), PendingIntent.getActivity(paramContext, 0, (Intent)localObject1, 134217728)).b();
    }
    ((NotificationManager)paramContext.getSystemService("notification")).notify(99, (Notification)localObject1);
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
