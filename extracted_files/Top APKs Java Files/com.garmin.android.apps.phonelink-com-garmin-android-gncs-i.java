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
import android.support.annotation.aa;
import android.support.annotation.ae;
import android.support.annotation.z;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.app.NotificationCompat.c;
import android.text.TextUtils;
import com.garmin.android.framework.util.b.a;
import com.garmin.android.gncs.persistence.GNCSNotificationAction;
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

public class i
{
  public static final int a = 98;
  public static final int b = 99;
  public static final String c = "@%Z";
  private static String e = "SYSTEM_CHANNEL_ID";
  private static final String g = "([\\u20a0-\\u32ff\\ud83c\\udc00-\\ud83d\\udeff\\udbb9\\udce5-\\udbb9\\udcee])";
  private static final String h = "([\\u3041-\\u3096\\u30A0-\\u30FF\\u3400-\\u4DB5\\u4E00-\\u9FCB\\uF900-\\uFA6A\\u2E80-\\u2FD5\\uFF5F-\\uFF9F\\u3000-\\u303F])";
  private final String d = "access_required";
  private int f = m.g.gcm3_notificationbar_icon_connect;
  
  protected i() {}
  
  public static List<GNCSNotificationAction> a(List<GNCSNotificationAction> paramList)
  {
    ArrayList localArrayList = new ArrayList();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      GNCSNotificationAction localGNCSNotificationAction = (GNCSNotificationAction)paramList.next();
      if (!b(localGNCSNotificationAction.b)) {
        localArrayList.add(localGNCSNotificationAction);
      } else if ((Locale.getDefault().getCountry().equalsIgnoreCase("JP")) && (c(localGNCSNotificationAction.b))) {
        localArrayList.add(localGNCSNotificationAction);
      }
    }
    return localArrayList;
  }
  
  public static void a(StatusBarNotification paramStatusBarNotification, GNCSNotificationInfo paramGNCSNotificationInfo, String paramString)
  {
    if ((Build.VERSION.SDK_INT >= 21) && (paramStatusBarNotification.getNotification() != null) && (paramStatusBarNotification.getNotification().category != null) && (paramStatusBarNotification.getNotification().category.equals("sys"))) {}
    label364:
    label402:
    int i;
    label449:
    label678:
    label750:
    label815:
    label832:
    label988:
    label1972:
    label2007:
    label2217:
    label2261:
    label2307:
    label2353:
    label2395:
    label2441:
    label2485:
    label2527:
    label2571:
    label2613:
    label2692:
    label2746:
    label2800:
    label2828:
    label2856:
    do
    {
      return;
      com.garmin.android.c.b.a("GNCSDump", "=================================================================================");
      com.garmin.android.c.b.a("GNCSDump", paramString + " : " + System.currentTimeMillis());
      com.garmin.android.c.b.a("GNCSDump", "=================================================================================");
      Object localObject2;
      try
      {
        com.garmin.android.c.b.a("GNCSDump", "PackageName: " + paramStatusBarNotification.getPackageName());
        com.garmin.android.c.b.a("GNCSDump", "Id: " + paramStatusBarNotification.getId());
        if (Build.VERSION.SDK_INT >= 21)
        {
          com.garmin.android.c.b.a("GNCSDump", "Key: " + d(paramStatusBarNotification.getKey()));
          com.garmin.android.c.b.a("GNCSDump", "GroupKey: " + d(paramStatusBarNotification.getGroupKey()));
        }
        if (Build.VERSION.SDK_INT >= 24)
        {
          localObject1 = new StringBuilder().append("IsGroup: ");
          if (!paramStatusBarNotification.isGroup()) {
            break label3802;
          }
          paramString = "Yes";
          com.garmin.android.c.b.a("GNCSDump", paramString);
          com.garmin.android.c.b.a("GNCSDump", "OverrideGroupKey: " + d(paramStatusBarNotification.getOverrideGroupKey()));
        }
        com.garmin.android.c.b.a("GNCSDump", "PostTime: " + paramStatusBarNotification.getPostTime());
        com.garmin.android.c.b.a("GNCSDump", "Tag: " + d(paramStatusBarNotification.getTag()));
        localObject1 = new StringBuilder().append("IsClearable: ");
        if (!paramStatusBarNotification.isClearable()) {
          break label3809;
        }
        paramString = "Yes";
        com.garmin.android.c.b.a("GNCSDump", paramString);
        localObject1 = new StringBuilder().append("IsOngoing: ");
        if (!paramStatusBarNotification.isOngoing()) {
          break label3816;
        }
        paramString = "Yes";
        com.garmin.android.c.b.a("GNCSDump", paramString);
        paramString = paramStatusBarNotification.getNotification();
        if (paramString == null) {
          break label2856;
        }
        if ((paramString.actions == null) || (paramString.actions.length <= 0)) {
          break label832;
        }
        com.garmin.android.c.b.a("GNCSDump", "Actions >>>");
        i = 0;
        if (i >= paramString.actions.length) {
          break label832;
        }
        localObject1 = paramString.actions[i];
        if (localObject1 == null) {
          break label3795;
        }
        com.garmin.android.c.b.a("GNCSDump", "     title: " + ((Notification.Action)localObject1).title);
        localObject2 = new StringBuilder().append("        actionIntent: ");
        if (((Notification.Action)localObject1).actionIntent == null) {
          break label678;
        }
        paramStatusBarNotification = "Yes";
      }
      catch (Exception paramStatusBarNotification)
      {
        for (;;)
        {
          com.garmin.android.c.b.a("GNCSDump", "Exception thrown while dumping notification: " + paramStatusBarNotification.getMessage());
          return;
          paramStatusBarNotification = "No";
          continue;
          com.garmin.android.c.b.a("GNCSDump", "        Extra " + (String)localObject2 + ": null");
        }
      }
      catch (Throwable paramStatusBarNotification)
      {
        com.garmin.android.c.b.a("GNCSDump", "Throwable thrown while dumping notification: " + paramStatusBarNotification.getMessage());
        return;
      }
      com.garmin.android.c.b.a("GNCSDump", paramStatusBarNotification);
      if (Build.VERSION.SDK_INT >= 21)
      {
        paramStatusBarNotification = ((Notification.Action)localObject1).getExtras();
        if (paramStatusBarNotification != null)
        {
          localObject1 = paramStatusBarNotification.keySet().iterator();
          for (;;)
          {
            if (!((Iterator)localObject1).hasNext()) {
              break label750;
            }
            localObject2 = (String)((Iterator)localObject1).next();
            if (paramStatusBarNotification.get((String)localObject2) == null) {
              break;
            }
            com.garmin.android.c.b.a("GNCSDump", "        Extra " + (String)localObject2 + ": " + paramStatusBarNotification.get((String)localObject2).toString());
          }
        }
        paramStatusBarNotification = paramString.actions[i].getRemoteInputs();
        if ((paramStatusBarNotification != null) && (paramStatusBarNotification.length > 0)) {
          com.garmin.android.c.b.a("GNCSDump", "        Action requires remote inputs");
        }
      }
      if (Build.VERSION.SDK_INT < 24) {
        break label3795;
      }
      Object localObject1 = new StringBuilder().append("        Allow Generated Replies: ");
      if (!paramString.actions[i].getAllowGeneratedReplies()) {
        break label3823;
      }
      paramStatusBarNotification = "Yes";
      com.garmin.android.c.b.a("GNCSDump", paramStatusBarNotification);
      break label3795;
      if ((Build.VERSION.SDK_INT >= 21) && (paramString.category != null))
      {
        paramStatusBarNotification = paramString.category;
        i = -1;
      }
      switch (paramStatusBarNotification.hashCode())
      {
      case 92895825: 
        com.garmin.android.c.b.a("GNCSDump", "Category: " + paramString.category);
        if (paramString.contentIntent != null) {
          com.garmin.android.c.b.a("GNCSDump", "ContentIntent: " + paramString.contentIntent.getCreatorPackage());
        }
      case 3045982: 
      case 96619420: 
      case 100709: 
      case 96891546: 
      case 108417: 
      case -1001078227: 
      case 106940687: 
      case -1028636743: 
      case -518602638: 
      case 1984153269: 
      case -897050771: 
      case -892481550: 
      case 114381: 
      case 1052964649: 
        try
        {
          paramStatusBarNotification = (Intent)PendingIntent.class.getDeclaredMethod("getIntent", new Class[0]).invoke(paramString.contentIntent, new Object[0]);
          if (paramStatusBarNotification != null)
          {
            com.garmin.android.c.b.a("GNCSDump", "     Action: " + paramStatusBarNotification.getAction());
            com.garmin.android.c.b.a("GNCSDump", "     Component: " + paramStatusBarNotification.getComponent().flattenToShortString());
          }
        }
        catch (IllegalAccessException paramStatusBarNotification)
        {
          for (;;) {}
        }
        catch (NoSuchMethodException paramStatusBarNotification)
        {
          for (;;) {}
        }
        catch (InvocationTargetException paramStatusBarNotification)
        {
          for (;;) {}
        }
        if (paramString.deleteIntent != null) {
          com.garmin.android.c.b.a("GNCSDump", "DeleteIntent: " + paramString.deleteIntent.getCreatorPackage());
        }
        try
        {
          paramStatusBarNotification = (Intent)PendingIntent.class.getDeclaredMethod("getIntent", new Class[0]).invoke(paramString.deleteIntent, new Object[0]);
          if (paramStatusBarNotification != null)
          {
            com.garmin.android.c.b.a("GNCSDump", "     Action: " + paramStatusBarNotification.getAction());
            if (paramStatusBarNotification.getComponent() != null) {
              com.garmin.android.c.b.a("GNCSDump", "     Component: " + paramStatusBarNotification.getComponent().flattenToShortString());
            }
          }
        }
        catch (IllegalAccessException paramStatusBarNotification)
        {
          for (;;) {}
        }
        catch (NoSuchMethodException paramStatusBarNotification)
        {
          for (;;) {}
        }
        catch (InvocationTargetException paramStatusBarNotification)
        {
          for (;;) {}
        }
        if (paramString.extras != null)
        {
          com.garmin.android.c.b.a("GNCSDump", "Extras >>>");
          localObject1 = paramString.extras.keySet().iterator();
          for (;;)
          {
            if (((Iterator)localObject1).hasNext())
            {
              localObject2 = (String)((Iterator)localObject1).next();
              Object localObject3;
              if (((String)localObject2).equals("android.textLines"))
              {
                paramStatusBarNotification = paramString.extras.getCharSequenceArray("android.textLines");
                localObject3 = new StringBuilder();
                i = 0;
                for (;;)
                {
                  if (i < paramStatusBarNotification.length)
                  {
                    if (((StringBuilder)localObject3).length() > 0) {
                      ((StringBuilder)localObject3).append("\n");
                    }
                    ((StringBuilder)localObject3).append(paramStatusBarNotification[i]);
                    i += 1;
                    continue;
                    if (!paramStatusBarNotification.equals("alarm")) {
                      break label3830;
                    }
                    i = 0;
                    break label3830;
                    if (!paramStatusBarNotification.equals("call")) {
                      break label3830;
                    }
                    i = 1;
                    break label3830;
                    if (!paramStatusBarNotification.equals("email")) {
                      break label3830;
                    }
                    i = 2;
                    break label3830;
                    if (!paramStatusBarNotification.equals("err")) {
                      break label3830;
                    }
                    i = 3;
                    break label3830;
                    if (!paramStatusBarNotification.equals("event")) {
                      break label3830;
                    }
                    i = 4;
                    break label3830;
                    if (!paramStatusBarNotification.equals("msg")) {
                      break label3830;
                    }
                    i = 5;
                    break label3830;
                    if (!paramStatusBarNotification.equals("progress")) {
                      break label3830;
                    }
                    i = 6;
                    break label3830;
                    if (!paramStatusBarNotification.equals("promo")) {
                      break label3830;
                    }
                    i = 7;
                    break label3830;
                    if (!paramStatusBarNotification.equals("recommendation")) {
                      break label3830;
                    }
                    i = 8;
                    break label3830;
                    if (!paramStatusBarNotification.equals("reminder")) {
                      break label3830;
                    }
                    i = 9;
                    break label3830;
                    if (!paramStatusBarNotification.equals("service")) {
                      break label3830;
                    }
                    i = 10;
                    break label3830;
                    if (!paramStatusBarNotification.equals("social")) {
                      break label3830;
                    }
                    i = 11;
                    break label3830;
                    if (!paramStatusBarNotification.equals("status")) {
                      break label3830;
                    }
                    i = 12;
                    break label3830;
                    if (!paramStatusBarNotification.equals("sys")) {
                      break label3830;
                    }
                    i = 13;
                    break label3830;
                    if (!paramStatusBarNotification.equals("transport")) {
                      break label3830;
                    }
                    i = 14;
                    break label3830;
                    com.garmin.android.c.b.a("GNCSDump", "Category: Alarm");
                    break;
                    com.garmin.android.c.b.a("GNCSDump", "Category: Call");
                    break;
                    com.garmin.android.c.b.a("GNCSDump", "Category: Email");
                    break;
                    com.garmin.android.c.b.a("GNCSDump", "Category: Error");
                    break;
                    com.garmin.android.c.b.a("GNCSDump", "Category: Event");
                    break;
                    com.garmin.android.c.b.a("GNCSDump", "Category: Message");
                    break;
                    com.garmin.android.c.b.a("GNCSDump", "Category: Progress");
                    break;
                    com.garmin.android.c.b.a("GNCSDump", "Category: Promo");
                    break;
                    com.garmin.android.c.b.a("GNCSDump", "Category: Recommendation");
                    break;
                    com.garmin.android.c.b.a("GNCSDump", "Category: Reminder");
                    break;
                    com.garmin.android.c.b.a("GNCSDump", "Category: Service");
                    break;
                    com.garmin.android.c.b.a("GNCSDump", "Category: Social");
                    break;
                    com.garmin.android.c.b.a("GNCSDump", "Category: Status");
                    break;
                    com.garmin.android.c.b.a("GNCSDump", "Category: System");
                    break;
                    com.garmin.android.c.b.a("GNCSDump", "Category: Transport");
                    break;
                    com.garmin.android.c.b.a("GNCSDump", "Category: Unknown");
                    break;
                  }
                }
                com.garmin.android.c.b.a("GNCSDump", "     " + (String)localObject2 + ":\n" + d(((StringBuilder)localObject3).toString()));
              }
              else
              {
                localObject3 = paramString.extras.get((String)localObject2);
                if (localObject3 != null)
                {
                  paramStatusBarNotification = localObject3.toString();
                  i = -1;
                  switch (((String)localObject2).hashCode())
                  {
                  case 22486478: 
                  case -1897567786: 
                  case 150183834: 
                  case -489846293: 
                  case -1730887922: 
                  case 967367924: 
                  case -1036913332: 
                  case -2079427047: 
                  case 1297754027: 
                    for (;;)
                    {
                      localObject2 = new StringBuilder().append("     ").append((String)localObject2).append(": ");
                      if (localObject3 != null) {
                        break label3963;
                      }
                      paramStatusBarNotification = "";
                      com.garmin.android.c.b.a("GNCSDump", paramStatusBarNotification);
                      break;
                      if (!((String)localObject2).equals("android.bigText")) {
                        break label3907;
                      }
                      i = 0;
                      break label3907;
                      if (!((String)localObject2).equals("android.conversationTitle")) {
                        break label3907;
                      }
                      i = 1;
                      break label3907;
                      if (!((String)localObject2).equals("android.infoText")) {
                        break label3907;
                      }
                      i = 2;
                      break label3907;
                      if (!((String)localObject2).equals("android.messages")) {
                        break label3907;
                      }
                      i = 3;
                      break label3907;
                      if (!((String)localObject2).equals("android.subText")) {
                        break label3907;
                      }
                      i = 4;
                      break label3907;
                      if (!((String)localObject2).equals("android.summaryText")) {
                        break label3907;
                      }
                      i = 5;
                      break label3907;
                      if (!((String)localObject2).equals("android.text")) {
                        break label3907;
                      }
                      i = 6;
                      break label3907;
                      if (!((String)localObject2).equals("android.title")) {
                        break label3907;
                      }
                      i = 7;
                      break label3907;
                      if (!((String)localObject2).equals("android.title.big")) {
                        break label3907;
                      }
                      i = 8;
                      break label3907;
                      paramStatusBarNotification = d(paramStatusBarNotification);
                    }
                  }
                }
              }
            }
          }
        }
        com.garmin.android.c.b.a("GNCSDump", "Flags>>>>>");
        localObject1 = new StringBuilder().append("     AUTO_CANCEL: ");
        if ((paramString.flags & 0x10) != 16) {
          break label3972;
        }
        paramStatusBarNotification = "Yes";
        com.garmin.android.c.b.a("GNCSDump", paramStatusBarNotification);
        localObject1 = new StringBuilder().append("     FOREGROUND_SERVICE: ");
        if ((paramString.flags & 0x40) != 64) {
          break label3979;
        }
        paramStatusBarNotification = "Yes";
        com.garmin.android.c.b.a("GNCSDump", paramStatusBarNotification);
        localObject1 = new StringBuilder().append("     GROUP_SUMMARY: ");
        if ((paramString.flags & 0x200) != 512) {
          break label3986;
        }
        paramStatusBarNotification = "Yes";
        com.garmin.android.c.b.a("GNCSDump", paramStatusBarNotification);
        localObject1 = new StringBuilder().append("     HIGH_PRIORITY: ");
        if ((paramString.flags & 0x80) != 128) {
          break label3993;
        }
        paramStatusBarNotification = "Yes";
        com.garmin.android.c.b.a("GNCSDump", paramStatusBarNotification);
        localObject1 = new StringBuilder().append("     INSISTENT: ");
        if ((paramString.flags & 0x4) != 4) {
          break label4000;
        }
        paramStatusBarNotification = "Yes";
        com.garmin.android.c.b.a("GNCSDump", paramStatusBarNotification);
        localObject1 = new StringBuilder().append("     LOCAL_ONLY: ");
        if ((paramString.flags & 0x100) != 256) {
          break label4007;
        }
        paramStatusBarNotification = "Yes";
        com.garmin.android.c.b.a("GNCSDump", paramStatusBarNotification);
        localObject1 = new StringBuilder().append("     NO_CLEAR: ");
        if ((paramString.flags & 0x20) != 32) {
          break label4014;
        }
        paramStatusBarNotification = "Yes";
        com.garmin.android.c.b.a("GNCSDump", paramStatusBarNotification);
        localObject1 = new StringBuilder().append("     ONGOING_EVENT: ");
        if ((paramString.flags & 0x2) != 2) {
          break label4021;
        }
        paramStatusBarNotification = "Yes";
        com.garmin.android.c.b.a("GNCSDump", paramStatusBarNotification);
        localObject1 = new StringBuilder().append("     ONLY_ALERT_ONCE: ");
        if ((paramString.flags & 0x8) != 8) {
          break label4028;
        }
        paramStatusBarNotification = "Yes";
        com.garmin.android.c.b.a("GNCSDump", paramStatusBarNotification);
        localObject1 = new StringBuilder().append("     SHOW_LIGHTS: ");
        if ((paramString.flags & 0x1) != 1) {
          break label4035;
        }
        paramStatusBarNotification = "Yes";
        com.garmin.android.c.b.a("GNCSDump", paramStatusBarNotification);
        com.garmin.android.c.b.a("GNCSDump", "NumberEvents: " + paramString.number);
        switch (paramString.priority)
        {
        case 0: 
          com.garmin.android.c.b.a("GNCSDump", "Priority: " + paramString.priority);
          localObject1 = new StringBuilder().append("TickerText: ");
          if (paramString.tickerText != null) {
            break label3703;
          }
          paramStatusBarNotification = "";
          com.garmin.android.c.b.a("GNCSDump", paramStatusBarNotification);
          if (Build.VERSION.SDK_INT >= 21) {}
          switch (paramString.visibility)
          {
          case 0: 
            com.garmin.android.c.b.a("GNCSDump", "Visibility: " + paramString.visibility);
            com.garmin.android.c.b.a("GNCSDump", "When: " + paramString.when);
            com.garmin.android.c.b.a("GNCSDump", "");
            com.garmin.android.c.b.a("GNCSDump", "=== GNCSParsedNotification (Parsed Notification) ===");
            com.garmin.android.c.b.a("GNCSDump", "");
            com.garmin.android.c.b.a("GNCSDump", "title: " + d(paramGNCSNotificationInfo.d));
            com.garmin.android.c.b.a("GNCSDump", "subTitle: " + d(paramGNCSNotificationInfo.e));
            com.garmin.android.c.b.a("GNCSDump", "message: " + d(paramGNCSNotificationInfo.f));
            com.garmin.android.c.b.a("GNCSDump", "positiveAction: " + paramGNCSNotificationInfo.g);
            com.garmin.android.c.b.a("GNCSDump", "negativeAction: " + paramGNCSNotificationInfo.h);
            com.garmin.android.c.b.a("GNCSDump", "status: " + paramGNCSNotificationInfo.b.name());
            com.garmin.android.c.b.a("GNCSDump", "");
            com.garmin.android.c.b.a("GNCSDump", "cacheId: " + paramGNCSNotificationInfo.k);
            com.garmin.android.c.b.a("GNCSDump", "cacheKey: " + d(paramGNCSNotificationInfo.j));
            com.garmin.android.c.b.a("GNCSDump", "notificationId: " + paramGNCSNotificationInfo.l);
            com.garmin.android.c.b.a("GNCSDump", "notificationKey: " + d(paramGNCSNotificationInfo.m));
            com.garmin.android.c.b.a("GNCSDump", "packageName: " + paramGNCSNotificationInfo.n);
            com.garmin.android.c.b.a("GNCSDump", "Tag: " + d(paramGNCSNotificationInfo.o));
          }
          break;
        }
        break;
      }
    } while (paramGNCSNotificationInfo == null);
    paramString = new StringBuilder().append("isGroup: ");
    if (paramGNCSNotificationInfo.D) {}
    for (paramStatusBarNotification = "Yes";; paramStatusBarNotification = "No/Unknown")
    {
      com.garmin.android.c.b.a("GNCSDump", paramStatusBarNotification);
      com.garmin.android.c.b.a("GNCSDump", "numEvents: " + paramGNCSNotificationInfo.C);
      com.garmin.android.c.b.a("GNCSDump", "priority: " + paramGNCSNotificationInfo.B);
      com.garmin.android.c.b.a("GNCSDump", "tickerText: " + d(paramGNCSNotificationInfo.E));
      com.garmin.android.c.b.a("GNCSDump", "postTime: " + paramGNCSNotificationInfo.s);
      com.garmin.android.c.b.a("GNCSDump", "when: " + paramGNCSNotificationInfo.t);
      com.garmin.android.c.b.a("GNCSDump", "notificationType: " + paramGNCSNotificationInfo.r);
      com.garmin.android.c.b.a("GNCSDump", "flags: " + paramGNCSNotificationInfo.x);
      com.garmin.android.c.b.a("GNCSDump", "phoneNumberFlags: " + paramGNCSNotificationInfo.z);
      com.garmin.android.c.b.a("GNCSDump", "notificationFlags: " + paramGNCSNotificationInfo.x);
      paramString = new StringBuilder().append("groupKey: ");
      if (paramGNCSNotificationInfo.p == null)
      {
        paramStatusBarNotification = "None";
        label3565:
        com.garmin.android.c.b.a("GNCSDump", paramStatusBarNotification);
        paramString = new StringBuilder().append("overrideGroupKey: ");
        if (paramGNCSNotificationInfo.q != null) {
          break label3760;
        }
      }
      label3703:
      label3760:
      for (paramStatusBarNotification = "None";; paramStatusBarNotification = d(paramGNCSNotificationInfo.q))
      {
        com.garmin.android.c.b.a("GNCSDump", paramStatusBarNotification);
        com.garmin.android.c.b.a("GNCSDump", "phone number: " + d(paramGNCSNotificationInfo.i));
        return;
        com.garmin.android.c.b.a("GNCSDump", "Priority: Default");
        break;
        com.garmin.android.c.b.a("GNCSDump", "Priority: High");
        break;
        com.garmin.android.c.b.a("GNCSDump", "Priority: Low");
        break;
        com.garmin.android.c.b.a("GNCSDump", "Priority: Max");
        break;
        com.garmin.android.c.b.a("GNCSDump", "Priority: Min");
        break;
        paramStatusBarNotification = d(paramString.tickerText.toString());
        break label2746;
        com.garmin.android.c.b.a("GNCSDump", "Visibility: Private");
        break label2828;
        com.garmin.android.c.b.a("GNCSDump", "Visibility: Public");
        break label2828;
        com.garmin.android.c.b.a("GNCSDump", "Visibility: Secret");
        break label2828;
        paramStatusBarNotification = paramGNCSNotificationInfo.p;
        break label3565;
      }
      label3795:
      i += 1;
      break label449;
      label3802:
      paramString = "No";
      break;
      label3809:
      paramString = "No";
      break label364;
      label3816:
      paramString = "No";
      break label402;
      label3823:
      paramStatusBarNotification = "No";
      break label815;
      label3830:
      switch (i)
      {
      }
      break label988;
      label3907:
      switch (i)
      {
      }
      break label1972;
      label3963:
      break label2007;
      break label2692;
      break label2800;
      label3972:
      paramStatusBarNotification = "No";
      break label2217;
      label3979:
      paramStatusBarNotification = "No";
      break label2261;
      label3986:
      paramStatusBarNotification = "No";
      break label2307;
      label3993:
      paramStatusBarNotification = "No";
      break label2353;
      label4000:
      paramStatusBarNotification = "No";
      break label2395;
      label4007:
      paramStatusBarNotification = "No";
      break label2441;
      label4014:
      paramStatusBarNotification = "No";
      break label2485;
      label4021:
      paramStatusBarNotification = "No";
      break label2527;
      label4028:
      paramStatusBarNotification = "No";
      break label2571;
      label4035:
      paramStatusBarNotification = "No";
      break label2613;
    }
  }
  
  public static void a(String paramString)
  {
    e = paramString;
  }
  
  public static boolean a(GNCSNotificationInfo paramGNCSNotificationInfo)
  {
    if (paramGNCSNotificationInfo == null) {}
    while ((TextUtils.isEmpty(paramGNCSNotificationInfo.d)) && (TextUtils.isEmpty(paramGNCSNotificationInfo.e)) && (TextUtils.isEmpty(paramGNCSNotificationInfo.f))) {
      return true;
    }
    return false;
  }
  
  private void b(Context paramContext, boolean paramBoolean)
  {
    if ((a(paramContext)) && (!a())) {
      GNCSListenerService.a(paramContext);
    }
    if (!paramBoolean)
    {
      long l = System.currentTimeMillis();
      if (com.garmin.android.gncs.settings.d.a().d(paramContext) > l - 86400000L)
      {
        com.garmin.android.c.b.a("Not notifying user about notification service not running since it has not been 24hours since the last time.");
        return;
      }
    }
    new Handler().postDelayed(new i.1(this, paramContext), 5000L);
  }
  
  private static boolean b(String paramString)
  {
    try
    {
      boolean bool = Pattern.compile("([\\u20a0-\\u32ff\\ud83c\\udc00-\\ud83d\\udeff\\udbb9\\udce5-\\udbb9\\udcee])").matcher(paramString).find();
      if (bool) {
        return true;
      }
    }
    catch (Exception paramString) {}
    return false;
  }
  
  private static boolean c(String paramString)
  {
    try
    {
      boolean bool = Pattern.compile("([\\u3041-\\u3096\\u30A0-\\u30FF\\u3400-\\u4DB5\\u4E00-\\u9FCB\\uF900-\\uFA6A\\u2E80-\\u2FD5\\uFF5F-\\uFF9F\\u3000-\\u303F])").matcher(paramString).find();
      if (bool) {
        return true;
      }
    }
    catch (Exception paramString) {}
    return false;
  }
  
  private static String d(String paramString)
  {
    int j = 100;
    if (paramString == null) {
      return "";
    }
    int k = paramString.length();
    int i = 0;
    if (k > 100) {
      i = 1;
    }
    for (;;)
    {
      paramString = new char[j];
      Arrays.fill(paramString, '*');
      if (i != 0)
      {
        paramString[(j - 2)] = 45;
        paramString[(j - 1)] = 62;
      }
      return new String(paramString);
      j = k;
    }
  }
  
  public static List<ResolveInfo> d(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().queryIntentActivities(new Intent("android.intent.action.DIAL"), 0);
      return paramContext;
    }
    catch (Exception paramContext) {}
    return new ArrayList();
  }
  
  public static boolean d(@aa Context paramContext, @z String paramString)
  {
    Object localObject = paramContext;
    if (paramContext == null) {}
    try
    {
      paramContext = com.garmin.android.gncs.settings.b.a().m();
      localObject = paramContext;
      if (paramContext == null) {
        return false;
      }
      paramContext = d((Context)localObject);
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
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  public static List<String> e(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    for (;;)
    {
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
          int i1 = 0;
          int i = 0;
          k = 0;
          j = 0;
          int i2 = i;
          int m = k;
          int n = j;
          String str;
          if (i1 < i3)
          {
            str = arrayOfString[i1];
            if (str.equals("android.permission.RECEIVE_MMS"))
            {
              if ((k == 0) || (i == 0)) {
                break label237;
              }
              n = 1;
              m = k;
              i2 = i;
            }
          }
          else
          {
            if ((n == 0) || (m == 0) || (i2 == 0)) {
              continue;
            }
            localArrayList.add(localPackageInfo.packageName);
            continue;
          }
          if (str.equals("android.permission.RECEIVE_SMS"))
          {
            if ((j == 0) || (i == 0)) {
              break label242;
            }
            m = 1;
            i2 = i;
            n = j;
            continue;
          }
          boolean bool = str.equals("android.permission.READ_SMS");
          if (bool)
          {
            if ((j != 0) && (k != 0))
            {
              i2 = 1;
              m = k;
              n = j;
              continue;
              continue;
            }
            i = 1;
          }
          i1 += 1;
          continue;
        }
        else
        {
          return localArrayList;
        }
      }
      catch (Exception paramContext) {}
      label237:
      int j = 1;
      continue;
      label242:
      int k = 1;
    }
  }
  
  public static boolean e(@z Context paramContext, @z String paramString)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (paramContext != null)
    {
      paramContext = Telephony.Sms.getDefaultSmsPackage(paramContext.getApplicationContext());
      bool1 = bool2;
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
    }
    return bool1;
  }
  
  @ae(b=26)
  private static boolean g(Context paramContext)
  {
    return ((CompanionDeviceManager)paramContext.getSystemService(CompanionDeviceManager.class)).getAssociations().size() > 0;
  }
  
  public String a(Context paramContext, String paramString)
  {
    localObject2 = null;
    Object localObject1;
    if ((paramString == null) || (paramString.length() == 0)) {
      localObject1 = "";
    }
    do
    {
      return localObject1;
      localObject1 = paramString;
    } while (android.support.v4.app.d.b(paramContext, "android.permission.READ_CONTACTS") != 0);
    for (;;)
    {
      try
      {
        localObject1 = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(paramString));
        localObject1 = paramContext.getContentResolver().query((Uri)localObject1, new String[] { "display_name" }, null, null, null);
        if (localObject1 == null) {
          continue;
        }
      }
      catch (Exception paramContext)
      {
        paramContext = localObject2;
        continue;
        paramContext = null;
        continue;
        paramContext = null;
        continue;
      }
      try
      {
        if (((Cursor)localObject1).moveToFirst()) {
          paramContext = ((Cursor)localObject1).getString(((Cursor)localObject1).getColumnIndexOrThrow("display_name"));
        }
      }
      finally
      {
        try
        {
          ((Cursor)localObject1).close();
          localObject1 = paramString;
          if (paramContext == null) {
            break;
          }
          return paramContext;
        }
        catch (Exception localException)
        {
          continue;
        }
        paramContext = finally;
        ((Cursor)localObject1).close();
      }
    }
  }
  
  public void a(int paramInt)
  {
    this.f = paramInt;
  }
  
  public void a(Context paramContext, boolean paramBoolean)
  {
    a(paramContext, paramBoolean, false);
  }
  
  public void a(Context paramContext, boolean paramBoolean1, boolean paramBoolean2)
  {
    if ((a(paramContext)) && (a())) {}
    CompanionDeviceManager localCompanionDeviceManager;
    do
    {
      return;
      if ((!paramBoolean2) || (Build.VERSION.SDK_INT < 26)) {
        break label97;
      }
      localCompanionDeviceManager = (CompanionDeviceManager)paramContext.getSystemService(CompanionDeviceManager.class);
      if (localCompanionDeviceManager.getAssociations().size() <= 0) {
        break;
      }
      paramContext = new ComponentName(paramContext.getApplicationContext().getPackageName(), GNCSListenerService.class.getName());
    } while (localCompanionDeviceManager.hasNotificationAccess(paramContext));
    localCompanionDeviceManager.requestNotificationAccess(paramContext);
    return;
    b(paramContext, paramBoolean1);
    return;
    label97:
    b(paramContext, paramBoolean1);
  }
  
  public boolean a()
  {
    return GNCSListenerService.a();
  }
  
  public boolean a(Context paramContext)
  {
    String str1 = Settings.Secure.getString(paramContext.getContentResolver(), "enabled_notification_listeners");
    String str2 = GNCSListenerService.class.getSimpleName();
    paramContext = paramContext.getPackageName();
    return (str1 != null) && (str1.contains(str2)) && (str1.contains(paramContext));
  }
  
  public String b(@z Context paramContext, String paramString)
  {
    String str;
    if (paramString.equals("com.garmin.gncs.phone.incoming")) {
      str = paramContext.getString(m.l.incoming_call_type);
    }
    for (;;)
    {
      return str;
      if (paramString.equals("com.garmin.gncs.phone.missed")) {
        return paramContext.getString(m.l.missed_call_type);
      }
      if (paramString.equals("com.garmin.gncs.voicemail")) {
        return paramContext.getString(m.l.voicemail_type);
      }
      if (paramString.equals("com.garmin.gncs.sms")) {
        return paramContext.getString(m.l.sms_type);
      }
      if (paramString.equals("com.garmin.gncs.calendar")) {
        return paramContext.getString(m.l.calendar_type);
      }
      if (paramString.equals("com.google.android.googlequicksearchbox")) {
        return "Google Now";
      }
      str = paramString;
      if (paramContext != null) {
        try
        {
          paramContext = paramContext.getPackageManager();
          ApplicationInfo localApplicationInfo = paramContext.getApplicationInfo(paramString, 0);
          str = paramString;
          if (localApplicationInfo != null)
          {
            paramContext = paramContext.getApplicationLabel(localApplicationInfo).toString();
            return paramContext;
          }
        }
        catch (Exception paramContext) {}
      }
    }
    return paramString;
  }
  
  public void b(Context paramContext)
  {
    a(paramContext, false);
  }
  
  public Drawable c(Context paramContext, String paramString)
  {
    if (paramString.equals("com.garmin.gncs.phone.incoming")) {}
    for (;;)
    {
      return null;
      if ((!paramString.equals("com.garmin.gncs.phone.missed")) && (!paramString.equals("com.garmin.gncs.voicemail")) && (!paramString.equals("com.garmin.gncs.sms")) && (!paramString.equals("com.garmin.gncs.calendar")))
      {
        paramContext = paramContext.getPackageManager();
        try
        {
          paramString = paramContext.getPackageInfo(paramString, 0);
          if (paramString != null)
          {
            paramContext = paramString.applicationInfo.loadIcon(paramContext);
            return paramContext;
          }
        }
        catch (PackageManager.NameNotFoundException paramContext) {}
      }
    }
    return null;
  }
  
  public void c(Context paramContext)
  {
    Object localObject = paramContext.getString(m.l.missing_sms_permission);
    Intent localIntent = new Intent();
    localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
    localIntent.setData(Uri.fromParts("package", paramContext.getPackageName(), null));
    if (Build.VERSION.SDK_INT >= 26)
    {
      NotificationChannel localNotificationChannel = new NotificationChannel("access_required", paramContext.getString(m.l.notification_access_required), 4);
      ((NotificationManager)paramContext.getSystemService("notification")).createNotificationChannel(localNotificationChannel);
    }
    for (localObject = new Notification.Builder(paramContext).setSmallIcon(this.f).setContentTitle(paramContext.getString(m.l.notification_access_required)).setContentText((CharSequence)localObject).setStyle(new Notification.BigTextStyle().bigText((CharSequence)localObject)).setAutoCancel(true).setVibrate(new long[] { 500L, 1000L }).setPriority(2).setCategory("recommendation").addAction(0, paramContext.getString(m.l.open_settings), PendingIntent.getActivity(paramContext, 0, localIntent, 134217728)).setChannelId("access_required").build();; localObject = new NotificationCompat.Builder(paramContext).a(this.f).a(paramContext.getString(m.l.notification_access_required)).b((CharSequence)localObject).a(new NotificationCompat.c().c((CharSequence)localObject)).e(true).a(new long[] { 500L, 1000L }).d(2).a("recommendation").a(0, paramContext.getString(m.l.open_settings), PendingIntent.getActivity(paramContext, 0, localIntent, 134217728)).c())
    {
      ((NotificationManager)paramContext.getSystemService("notification")).notify(99, (Notification)localObject);
      return;
    }
  }
  
  public static class a
    extends a
  {
    public a() {}
    
    public void a()
    {
      a(i.class, new i.a.1(this));
    }
  }
  
  public final class b
  {
    public static final String a = "com.garmin.gncs.phone.incoming";
    public static final String b = "com.garmin.gncs.phone.missed";
    public static final String c = "com.garmin.gncs.voicemail";
    public static final String d = "com.garmin.gncs.sms";
    public static final String e = "com.garmin.gncs.calendar";
    
    public b() {}
  }
}
