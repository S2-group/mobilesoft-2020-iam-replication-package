package com.example.passmsg;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Application;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.unity3d.player.UnityPlayer;
import com.unity3d.player.UnityPlayerActivity;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity
  extends UnityPlayerActivity
{
  public static ArrayList<PendingIntent> allintents = new ArrayList();
  static SharedPreferences.Editor editor;
  private static String gameTitle;
  public static MainActivity mee;
  private static String possibleEmail;
  static SharedPreferences preferences;
  public static String pubID = "0000";
  private static SharedPreferences sharedPreferences;
  int playStoreInstalled = -1;
  
  static
  {
    gameTitle = "Game Name";
    possibleEmail = "no";
  }
  
  public MainActivity()
  {
    mee = this;
    System.out.println("Balutm MainActivity Unity5 Match3");
  }
  
  public static String ADMOBISLOADED()
  {
    System.out.println("ADMOBISLOADED result in java=" + Admob.IsInterstitialLoaded.booleanValue());
    if (!Admob.IsInterstitialLoaded.booleanValue())
    {
      System.out.println("balatm ADMOBISLOADED not loaded yet..");
      return "no";
    }
    return "yes";
  }
  
  public static void CancleAllLocalNotifs()
  {
    AlarmManager localAlarmManager = (AlarmManager)UnityPlayer.currentActivity.getSystemService("alarm");
    int i;
    if ((localAlarmManager != null) && (allintents != null) && (allintents.size() > 0)) {
      i = 0;
    }
    for (;;)
    {
      if (i >= allintents.size()) {
        return;
      }
      localAlarmManager.cancel((PendingIntent)allintents.get(i));
      i += 1;
    }
  }
  
  public static void LOADADMOB()
  {
    if (!Admob.IsInterstitialLoaded.booleanValue())
    {
      new Admob(UnityPlayer.currentActivity);
      System.out.println("LOADADMOB in java");
      return;
    }
    System.out.println("balatm LOADADMOB already loaded..Its Ready..");
  }
  
  public static void RATE2016(String paramString1, final String paramString2, final String paramString3, final String paramString4, final int paramInt)
  {
    Log.d("iopixel-rateme-unity", "rateMyApplication");
    UnityPlayer.currentActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        MainActivity.sharedPreferences = UnityPlayer.currentActivity.getApplicationContext().getSharedPreferences("rate2016", 0);
        if (MainActivity.sharedPreferences.getBoolean("done", false))
        {
          System.out.println("Already rated...");
          return;
        }
        AlertDialog.Builder localBuilder = new AlertDialog.Builder(UnityPlayer.currentActivity);
        localBuilder.setCancelable(true);
        localBuilder.setTitle(MainActivity.this);
        localBuilder.setMessage(paramString2);
        localBuilder.setPositiveButton(paramString3, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            paramAnonymous2DialogInterface.dismiss();
            if (this.val$stepAt == 1) {
              MainActivity.RATE2016(this.val$GameName, "Please rate 5 Star, It will help us alot", "No,thanks", "Okay", 3);
            }
            do
            {
              return;
              if (this.val$stepAt == 2)
              {
                MainActivity.sharedPreferences = UnityPlayer.currentActivity.getApplicationContext().getSharedPreferences("rate2016", 0);
                paramAnonymous2DialogInterface = MainActivity.sharedPreferences.edit();
                paramAnonymous2DialogInterface.putBoolean("done", true);
                paramAnonymous2DialogInterface.commit();
                MainActivity.sendGmail(this.val$GameName + " Rating", "Please write feedback");
                return;
              }
            } while (this.val$stepAt != 3);
            MainActivity.sharedPreferences = UnityPlayer.currentActivity.getApplicationContext().getSharedPreferences("rate2016", 0);
            paramAnonymous2DialogInterface = MainActivity.sharedPreferences.edit();
            paramAnonymous2DialogInterface.putBoolean("done", true);
            paramAnonymous2DialogInterface.commit();
            paramAnonymous2DialogInterface = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + UnityPlayer.currentActivity.getApplicationContext().getPackageName()));
            UnityPlayer.currentActivity.startActivity(paramAnonymous2DialogInterface);
          }
        });
        localBuilder.setNegativeButton(paramString4, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            paramAnonymous2DialogInterface.dismiss();
            if (this.val$stepAt == 1) {
              MainActivity.RATE2016(this.val$GameName, "Would you mind giving us some feedback?", "No,thanks", "Okay", 2);
            }
          }
        });
        localBuilder.create().show();
      }
    });
  }
  
  public static String SHOWADMOB()
  {
    String str = Admob.displayInterstitial();
    System.out.println("SHOWADMOB result in java=" + str);
    return str;
  }
  
  public static void SHOWPOPUP(String paramString1, final String paramString2, final String paramString3)
  {
    Log.d("iopixel-rateme-unity", "rateMyApplication");
    UnityPlayer.currentActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        AlertDialog.Builder localBuilder = new AlertDialog.Builder(UnityPlayer.currentActivity);
        localBuilder.setCancelable(false);
        localBuilder.setTitle(MainActivity.gameTitle);
        localBuilder.setMessage(MainActivity.this);
        localBuilder.setPositiveButton(paramString2, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            UnityPlayer.UnitySendMessage("GameConfigs2015", "OnPopupClicked", "yes");
            paramAnonymous2DialogInterface.dismiss();
          }
        });
        if (paramString3.length() > 1) {
          localBuilder.setNegativeButton(paramString3, new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
            {
              UnityPlayer.UnitySendMessage("GameConfigs2015", "OnPopupClicked", "cancel");
              paramAnonymous2DialogInterface.dismiss();
            }
          });
        }
        localBuilder.create().show();
      }
    });
  }
  
  public static void SHOWPOPUP(final String paramString1, final String paramString2, final String paramString3, String paramString4)
  {
    Log.d("iopixel-rateme-unity", "rateMyApplication");
    UnityPlayer.currentActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        AlertDialog.Builder localBuilder = new AlertDialog.Builder(UnityPlayer.currentActivity);
        localBuilder.setCancelable(false);
        if (MainActivity.this.length() > 2) {
          localBuilder.setTitle(MainActivity.this);
        }
        for (;;)
        {
          localBuilder.setMessage(paramString1);
          localBuilder.setPositiveButton(paramString2, new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
            {
              UnityPlayer.UnitySendMessage("GameConfigs2015", "OnPopupClicked", "yes");
              paramAnonymous2DialogInterface.dismiss();
            }
          });
          if (paramString3.length() > 1) {
            localBuilder.setNegativeButton(paramString3, new DialogInterface.OnClickListener()
            {
              public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
              {
                UnityPlayer.UnitySendMessage("GameConfigs2015", "OnPopupClicked", "cancel");
                paramAnonymous2DialogInterface.dismiss();
              }
            });
          }
          localBuilder.create().show();
          return;
          localBuilder.setTitle(MainActivity.gameTitle);
        }
      }
    });
  }
  
  public static void SHOWPOPUP(final String paramString1, final String paramString2, final String paramString3, String paramString4, final String paramString5)
  {
    Log.d("iopixel-rateme-unity", "rateMyApplication");
    UnityPlayer.currentActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        AlertDialog.Builder localBuilder = new AlertDialog.Builder(UnityPlayer.currentActivity);
        localBuilder.setCancelable(false);
        if (MainActivity.this.length() > 2) {
          localBuilder.setTitle(MainActivity.this);
        }
        for (;;)
        {
          localBuilder.setMessage(paramString1);
          localBuilder.setPositiveButton(paramString2, new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
            {
              UnityPlayer.UnitySendMessage("GameConfigs2015", "OnPopupClicked", "yes_" + this.val$idd);
              paramAnonymous2DialogInterface.dismiss();
            }
          });
          if (paramString3.length() > 1) {
            localBuilder.setNegativeButton(paramString3, new DialogInterface.OnClickListener()
            {
              public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
              {
                UnityPlayer.UnitySendMessage("GameConfigs2015", "OnPopupClicked", "cancel_" + this.val$idd);
                paramAnonymous2DialogInterface.dismiss();
              }
            });
          }
          localBuilder.create().show();
          return;
          localBuilder.setTitle(MainActivity.gameTitle);
        }
      }
    });
  }
  
  public static void SHOWRATING(String paramString)
  {
    Log.d("iopixel-rateme-unity", "rateMyApplication");
    UnityPlayer.currentActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        AlertDialog.Builder localBuilder = new AlertDialog.Builder(UnityPlayer.currentActivity);
        localBuilder.setCancelable(true);
        localBuilder.setTitle(MainActivity.gameTitle);
        localBuilder.setMessage(MainActivity.this);
        localBuilder.setPositiveButton("Rate", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            paramAnonymous2DialogInterface = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + UnityPlayer.currentActivity.getApplicationContext().getPackageName()));
            UnityPlayer.UnitySendMessage("GameConfigs2015", "OnRateClicked", "yes");
            UnityPlayer.currentActivity.startActivity(paramAnonymous2DialogInterface);
          }
        });
        localBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            UnityPlayer.UnitySendMessage("GameConfigs2015", "OnCancelClicked", "Cancel");
            paramAnonymous2DialogInterface.dismiss();
          }
        });
        localBuilder.create().show();
      }
    });
  }
  
  public static void SHOWSUBCRIBE()
  {
    Log.d("iopixel-rateme-unity", "rateMyApplication");
    UnityPlayer.currentActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        AlertDialog.Builder localBuilder = new AlertDialog.Builder(UnityPlayer.currentActivity);
        localBuilder.setCancelable(true);
        localBuilder.setMessage("Subscribe to our weekly “Gaming Newsletter”! \nBy clicking yes, you agree to our privacy policy.");
        TextView localTextView = new TextView(UnityPlayer.currentActivity);
        localTextView.setText(Html.fromHtml("<a href=\"http://timuz.com/mobilegames/privacypolicy.html\"\">Our Privacy Policy</a>"));
        localTextView.setClickable(true);
        localTextView.setMovementMethod(LinkMovementMethod.getInstance());
        localBuilder.setView(localTextView);
        localBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            System.out.println("Balutm OnEmail yes in Java..");
            paramAnonymous2DialogInterface = Patterns.EMAIL_ADDRESS;
            for (;;)
            {
              try
              {
                arrayOfAccount = AccountManager.get(UnityPlayer.currentActivity.getApplicationContext()).getAccounts();
                int i = arrayOfAccount.length;
                paramAnonymous2Int = 0;
                if (paramAnonymous2Int < i) {
                  continue;
                }
              }
              catch (Exception paramAnonymous2DialogInterface)
              {
                Account[] arrayOfAccount;
                Account localAccount;
                System.out.println("Unity balutm can't get Accounts" + paramAnonymous2DialogInterface.getMessage());
                continue;
              }
              UnityPlayer.UnitySendMessage("GameConfigs2015", "OnEmail", MainActivity.possibleEmail);
              MainActivity.editor.putString("news_subcribed", "news_yes");
              MainActivity.editor.commit();
              return;
              localAccount = arrayOfAccount[paramAnonymous2Int];
              if (paramAnonymous2DialogInterface.matcher(localAccount.name).matches()) {
                MainActivity.possibleEmail = localAccount.name;
              }
              paramAnonymous2Int += 1;
            }
          }
        });
        localBuilder.setNegativeButton("No", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            UnityPlayer.UnitySendMessage("GameConfigs2015", "OnEmail", "no");
            paramAnonymous2DialogInterface.dismiss();
          }
        });
        localBuilder.create().show();
      }
    });
  }
  
  public static void SetIDsToJar(String paramString1, String paramString2)
  {
    pubID = paramString1;
    gameTitle = paramString2;
    System.out.println("Balutm June2015 Got Values To Jar=" + paramString1 + " " + UnityPlayer.currentActivity.getApplicationContext().getPackageName() + " " + paramString2);
    getadID(UnityPlayer.currentActivity);
  }
  
  public static void SetIDsToJar(String paramString1, String paramString2, String paramString3)
  {
    pubID = paramString1;
    gameTitle = paramString2;
    System.out.println("Balutm June2015 Got Values To Jar=" + paramString1 + " " + UnityPlayer.currentActivity.getApplicationContext().getPackageName() + " " + paramString2);
  }
  
  public static void copyToClip(String paramString)
  {
    if (Build.VERSION.SDK_INT < 11)
    {
      ((android.text.ClipboardManager)UnityPlayer.currentActivity.getSystemService("clipboard")).setText(paramString);
      return;
    }
    ((android.content.ClipboardManager)UnityPlayer.currentActivity.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("Copied Text", paramString));
  }
  
  public static void createLocalNotification(String paramString1, String paramString2, long paramLong)
  {
    AlarmManager localAlarmManager = (AlarmManager)UnityPlayer.currentActivity.getSystemService("alarm");
    Intent localIntent = new Intent("android.media.action.DISPLAY_NOTIFICATION");
    localIntent.putExtra("body", paramString1);
    localIntent.putExtra("title", paramString2);
    localIntent.addCategory("android.intent.category.DEFAULT");
    paramString1 = Calendar.getInstance();
    paramString2 = PendingIntent.getBroadcast(UnityPlayer.currentActivity, (int)paramString1.getTimeInMillis(), localIntent, 134217728);
    paramString1.add(14, (int)paramLong);
    localAlarmManager.set(0, paramString1.getTimeInMillis(), paramString2);
    allintents.add(paramString2);
  }
  
  public static void dsfd() {}
  
  public static String getEmailNow()
  {
    Pattern localPattern = Patterns.EMAIL_ADDRESS;
    for (;;)
    {
      try
      {
        arrayOfAccount = AccountManager.get(UnityPlayer.currentActivity.getApplicationContext()).getAccounts();
        int j = arrayOfAccount.length;
        i = 0;
        if (i < j) {
          continue;
        }
      }
      catch (Exception localException)
      {
        Account[] arrayOfAccount;
        int i;
        Account localAccount;
        System.out.println("Unity balutm can't get Accounts" + localException.getMessage());
        continue;
      }
      return possibleEmail;
      localAccount = arrayOfAccount[i];
      if (localPattern.matcher(localAccount.name).matches()) {
        possibleEmail = localAccount.name;
      }
      i += 1;
    }
  }
  
  public static String getLocalNotifValues()
  {
    if ((UnityPlayer.currentActivity.getIntent().hasExtra("fromlocal")) && (UnityPlayer.currentActivity.getIntent().getStringExtra("fromlocal") != null) && (UnityPlayer.currentActivity.getIntent().getStringExtra("fromlocal").length() > 1)) {
      return UnityPlayer.currentActivity.getIntent().getStringExtra("fromlocal");
    }
    return "";
  }
  
  public static String getPackageID()
  {
    return UnityPlayer.currentActivity.getApplicationContext().getPackageName();
  }
  
  public static String getReferrernow()
  {
    SharedPreferences localSharedPreferences = UnityPlayer.currentActivity.getApplicationContext().getSharedPreferences("balutm", 0);
    System.out.println("balutm  referrersharedpref= " + localSharedPreferences);
    if ((localSharedPreferences.getString("tracked", "no") != "yes") && (localSharedPreferences.getString("referrer", "").length() > 2))
    {
      System.out.println("balutm jar sent referrer to unity");
      SharedPreferences.Editor localEditor = localSharedPreferences.edit();
      localEditor.putString("tracked", "yes");
      localEditor.commit();
      return localSharedPreferences.getString("referrer", "");
    }
    System.out.println("balutm referrer already sent or no referrer..");
    return "no";
  }
  
  public static String getVersionCode()
  {
    try
    {
      int i = UnityPlayer.currentActivity.getApplicationContext().getPackageManager().getPackageInfo(UnityPlayer.currentActivity.getApplicationContext().getPackageName(), 0).versionCode;
      return String.valueOf(i);
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      localNameNotFoundException.printStackTrace();
    }
    return "";
  }
  
  public static String getVersionName()
  {
    try
    {
      String str = UnityPlayer.currentActivity.getApplicationContext().getPackageManager().getPackageInfo(UnityPlayer.currentActivity.getApplicationContext().getPackageName(), 0).versionName;
      return str;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      localNameNotFoundException.printStackTrace();
    }
    return "";
  }
  
  public static void getadID(Activity paramActivity)
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        try
        {
          String str = AdvertisingIdClientInfo.getAdvertisingIdInfo(MainActivity.this).getId();
          System.out.println("BtmAdID=" + str);
          UnityPlayer.UnitySendMessage("GameConfigs2015", "onAdId", str);
          return;
        }
        catch (Exception localException)
        {
          System.out.println("BtmAdID=" + localException.getMessage());
        }
      }
    }).start();
  }
  
  public static String isInstalled(String paramString)
  {
    try
    {
      UnityPlayer.currentActivity.getApplicationContext().getPackageManager().getPackageInfo(paramString, 128);
      return "true";
    }
    catch (PackageManager.NameNotFoundException paramString) {}
    return "false";
  }
  
  public static void sendGmail(String paramString1, final String paramString2)
  {
    UnityPlayer.currentActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        Intent localIntent = new Intent();
        localIntent.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
        localIntent.putExtra("android.intent.extra.SUBJECT", MainActivity.this);
        localIntent.putExtra("android.intent.extra.TEXT", paramString2);
        localIntent.putExtra("android.intent.extra.EMAIL", new String[] { "bikeracingmaniagame@gmail.com" });
        try
        {
          UnityPlayer.currentActivity.startActivity(localIntent);
          return;
        }
        catch (ActivityNotFoundException localActivityNotFoundException) {}
      }
    });
  }
  
  public static void sendGmail(final String paramString1, final String paramString2, String paramString3)
  {
    UnityPlayer.currentActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        String str = MainActivity.this;
        try
        {
          Intent localIntent = new Intent("android.intent.action.VIEW");
          localIntent.setType("message/rfc822");
          localIntent.setData(Uri.parse("mailto:"));
          localIntent.putExtra("android.intent.extra.EMAIL", new String[] { str });
          localIntent.putExtra("android.intent.extra.SUBJECT", paramString1);
          localIntent.putExtra("android.intent.extra.TEXT", paramString2);
          UnityPlayer.currentActivity.startActivity(localIntent);
          return;
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
        }
      }
    });
  }
  
  public static void sendMail()
  {
    Intent localIntent = new Intent("android.intent.action.SEND");
    localIntent.setType("message/rfc822");
    localIntent.putExtra("android.intent.extra.EMAIL", new String[] { "recipient@example.com" });
    localIntent.putExtra("android.intent.extra.SUBJECT", "subject of email");
    localIntent.putExtra("android.intent.extra.TEXT", "body of email");
    try
    {
      UnityPlayer.currentActivity.startActivity(Intent.createChooser(localIntent, "Send mail..."));
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      Toast.makeText(UnityPlayer.currentActivity, "There are no email clients installed.", 0).show();
    }
  }
  
  public static void shareFB(String paramString)
  {
    UnityPlayer.currentActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        Intent localIntent = new Intent();
        localIntent.setAction("android.intent.action.SEND");
        localIntent.setType("text/plain");
        localIntent.putExtra("android.intent.extra.TEXT", MainActivity.this);
        localIntent.setPackage("com.facebook.katana");
        System.out.println("before sharing..FB");
        try
        {
          UnityPlayer.currentActivity.startActivityForResult(localIntent, 1);
          return;
        }
        catch (ActivityNotFoundException localActivityNotFoundException)
        {
          MainActivity.showUnityMsg("Please Install Facebook APP");
        }
      }
    });
  }
  
  public static void shareFB(String paramString1, final String paramString2)
  {
    UnityPlayer.currentActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        Intent localIntent = new Intent();
        localIntent.setAction("android.intent.action.SEND");
        localIntent.setType("text/plain");
        localIntent.putExtra("android.intent.extra.TEXT", MainActivity.this);
        localIntent.putExtra("android.intent.extra.SUBJECT", paramString2);
        localIntent.setPackage("com.facebook.katana");
        System.out.println("before sharing..FB");
        try
        {
          UnityPlayer.currentActivity.startActivityForResult(localIntent, 1);
          return;
        }
        catch (ActivityNotFoundException localActivityNotFoundException)
        {
          MainActivity.showUnityMsg("Please Install Facebook APP");
        }
      }
    });
  }
  
  public static void shareFBMessenger(String paramString)
  {
    UnityPlayer.currentActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        Intent localIntent = new Intent();
        localIntent.setAction("android.intent.action.SEND");
        localIntent.putExtra("android.intent.extra.TEXT", MainActivity.this);
        localIntent.setType("text/plain");
        localIntent.setPackage("com.facebook.orca");
        try
        {
          UnityPlayer.currentActivity.startActivity(localIntent);
          return;
        }
        catch (ActivityNotFoundException localActivityNotFoundException)
        {
          MainActivity.showUnityMsg("Please Install Facebook Messenger");
        }
      }
    });
  }
  
  public static void shareTwitter(String paramString)
  {
    UnityPlayer.currentActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        Intent localIntent = new Intent("android.intent.action.SEND");
        localIntent.putExtra("android.intent.extra.TEXT", MainActivity.this);
        localIntent.setType("text/plain");
        Object localObject = UnityPlayer.currentActivity.getApplicationContext().getPackageManager().queryIntentActivities(localIntent, 65536);
        int i = 0;
        localObject = ((List)localObject).iterator();
        if (!((Iterator)localObject).hasNext()) {}
        for (;;)
        {
          if (i == 0) {
            break label127;
          }
          UnityPlayer.currentActivity.startActivity(localIntent);
          return;
          ResolveInfo localResolveInfo = (ResolveInfo)((Iterator)localObject).next();
          if (!localResolveInfo.activityInfo.packageName.startsWith("com.twitter.android")) {
            break;
          }
          localIntent.setClassName(localResolveInfo.activityInfo.packageName, localResolveInfo.activityInfo.name);
          i = 1;
        }
        label127:
        MainActivity.showUnityMsg("Twitter app isn't found");
      }
    });
  }
  
  public static void shareWhatsapp(String paramString)
  {
    UnityPlayer.currentActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        Intent localIntent = new Intent();
        localIntent.setAction("android.intent.action.SEND");
        localIntent.putExtra("android.intent.extra.TEXT", MainActivity.this);
        localIntent.setType("text/plain");
        localIntent.setPackage("com.whatsapp");
        try
        {
          UnityPlayer.currentActivity.startActivity(localIntent);
          return;
        }
        catch (ActivityNotFoundException localActivityNotFoundException)
        {
          MainActivity.showUnityMsg("Whatsapp have not been installed.");
        }
      }
    });
  }
  
  public static void showPromoCode(String paramString1, final String paramString2)
  {
    System.out.println("Balutm showPromoCode");
    UnityPlayer.currentActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        final EditText localEditText = new EditText(UnityPlayer.currentActivity);
        new AlertDialog.Builder(UnityPlayer.currentActivity).setTitle(MainActivity.gameTitle).setMessage(MainActivity.this).setView(localEditText).setPositiveButton("Ok", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            paramAnonymous2DialogInterface = localEditText.getText().toString().replaceAll("\\s+", "").toLowerCase(Locale.ENGLISH);
            if (paramAnonymous2DialogInterface.equals(this.val$Gamecode.toLowerCase(Locale.ENGLISH)))
            {
              System.out.println("Balutm onPromoCode in jar=yes");
              UnityPlayer.UnitySendMessage("GameConfigs2015", "onPromoCode", "yes");
              return;
            }
            System.out.println("Balutm onPromoCode in jarno=" + this.val$Gamecode + "==" + paramAnonymous2DialogInterface);
            UnityPlayer.UnitySendMessage("GameConfigs2015", "onPromoCode", paramAnonymous2DialogInterface);
          }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int) {}
        }).show();
      }
    });
  }
  
  public static void showUnityMsg(String paramString)
  {
    UnityPlayer.currentActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        System.out.println("ShowUnityMsg=" + MainActivity.this);
        Toast.makeText(UnityPlayer.currentActivity, MainActivity.this, 1).show();
      }
    });
  }
  
  public String getInstallerPackageName(String paramString)
  {
    String str;
    if (paramString != null) {
      str = paramString;
    }
    try
    {
      if (paramString.length() == 0) {
        str = UnityPlayer.currentActivity.getApplicationContext().getPackageName();
      }
      paramString = UnityPlayer.currentActivity.getApplication().getPackageManager().getInstallerPackageName(str);
      return paramString;
    }
    catch (Exception paramString) {}
    return "";
  }
  
  public boolean isAppPlayStoreGenuine()
  {
    return isAppPlayStoreGenuine(null);
  }
  
  public boolean isAppPlayStoreGenuine(String paramString)
  {
    if (!isPlayStoreInstalled()) {}
    do
    {
      return false;
      paramString = getInstallerPackageName(paramString);
    } while ((paramString == null) || (paramString.length() <= 0));
    return true;
  }
  
  public boolean isPlayStoreInstalled()
  {
    Iterator localIterator;
    if (this.playStoreInstalled < 0)
    {
      localIterator = UnityPlayer.currentActivity.getApplication().getPackageManager().getInstalledPackages(8192).iterator();
      if (localIterator.hasNext()) {
        break label46;
      }
    }
    for (;;)
    {
      if (this.playStoreInstalled <= 0) {
        break label87;
      }
      return true;
      label46:
      String str = ((PackageInfo)localIterator.next()).packageName;
      if ((!str.equals("com.google.market")) && (!str.equals("com.android.vending"))) {
        break;
      }
      this.playStoreInstalled = 1;
    }
    label87:
    return false;
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if ((paramIntent != null) && (paramInt1 == 1))
    {
      System.out.println("Got From FB=");
      showUnityMsg("Shared..?");
      UnityPlayer.UnitySendMessage("GameConfigs2015", "onFbShare", paramInt2);
    }
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    mee = this;
    super.onCreate(paramBundle);
  }
}
