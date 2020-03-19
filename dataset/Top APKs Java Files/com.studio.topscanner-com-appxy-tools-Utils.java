package com.appxy.tools;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;
import com.flurry.android.FlurryAgent;
import com.simpleapp.tinyscanfree.MyApplication;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils
{
  public static final String dirPath = "/Android/data/com.studio.topscanner/files";
  
  private Utils() {}
  
  public static void closeKeyBoard(Activity paramActivity, EditText paramEditText)
  {
    ((InputMethodManager)paramActivity.getSystemService("input_method")).hideSoftInputFromWindow(paramEditText.getWindowToken(), 0);
  }
  
  @SuppressLint({"DefaultLocale"})
  public static boolean findAndGotoApp1(Activity paramActivity, String paramString, ArrayList<Uri> paramArrayList)
  {
    int j = 0;
    Intent localIntent = new Intent("android.intent.action.SEND_MULTIPLE");
    localIntent.setType("application/pdf");
    Object localObject = paramActivity.getPackageManager().queryIntentActivities(localIntent, 0);
    if (!((List)localObject).isEmpty())
    {
      localObject = ((List)localObject).iterator();
      ResolveInfo localResolveInfo;
      do
      {
        i = j;
        if (!((Iterator)localObject).hasNext()) {
          break;
        }
        localResolveInfo = (ResolveInfo)((Iterator)localObject).next();
      } while ((!localResolveInfo.activityInfo.packageName.toLowerCase().contains(paramString)) && (!localResolveInfo.activityInfo.name.toLowerCase().contains(paramString)));
      localIntent.putExtra("android.intent.extra.STREAM", paramArrayList);
      localIntent.setPackage(localResolveInfo.activityInfo.packageName);
      int i = 1;
      if (i != 0) {}
    }
    else
    {
      return false;
    }
    paramActivity.startActivity(Intent.createChooser(localIntent, "Fax"));
    return true;
  }
  
  public static String getAppVersionName(Context paramContext)
  {
    Object localObject1 = "";
    Object localObject2;
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
      localObject1 = paramContext;
      localObject2 = paramContext;
      if (TextUtils.isEmpty(paramContext)) {
        return "";
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      localObject2 = localObject1;
    }
    return localObject2;
  }
  
  @SuppressLint({"SimpleDateFormat"})
  public static String getDate(Date paramDate)
  {
    return new SimpleDateFormat("MMM dd, yyyy, HH:mm", Locale.US).format(paramDate);
  }
  
  @SuppressLint({"SimpleDateFormat"})
  public static String getDatetoString1(Date paramDate)
  {
    return new SimpleDateFormat("MMM dd yyyy HH:mm:ss", Locale.US).format(paramDate);
  }
  
  public static String getRealFilePath(Context paramContext, Uri paramUri)
  {
    if (paramUri == null) {}
    Object localObject1;
    for (;;)
    {
      return null;
      localObject1 = paramUri.getScheme();
      Object localObject3 = null;
      Object localObject2 = null;
      if (localObject1 == null) {
        return paramUri.getPath();
      }
      if ("file".equals(localObject1)) {
        return paramUri.getPath();
      }
      if ("content".equals(localObject1))
      {
        localObject1 = localObject3;
        try
        {
          paramUri = paramContext.getContentResolver().query(paramUri, new String[] { "_data" }, null, null, null);
          if (paramUri != null)
          {
            paramContext = localObject2;
            localObject1 = localObject3;
            if (paramUri.moveToFirst())
            {
              localObject1 = localObject3;
              int i = paramUri.getColumnIndex("_data");
              paramContext = localObject2;
              if (i > -1)
              {
                localObject1 = localObject3;
                paramContext = paramUri.getString(i);
              }
            }
            localObject1 = paramContext;
            paramUri.close();
            return paramContext;
          }
        }
        catch (Exception paramContext)
        {
          paramContext.printStackTrace();
        }
      }
    }
    return localObject1;
  }
  
  public static boolean hasFroyo()
  {
    return Build.VERSION.SDK_INT >= 8;
  }
  
  public static boolean hasGingerbread()
  {
    return Build.VERSION.SDK_INT >= 9;
  }
  
  public static boolean hasHoneycomb()
  {
    return Build.VERSION.SDK_INT >= 11;
  }
  
  public static boolean hasHoneycombMR1()
  {
    return Build.VERSION.SDK_INT >= 12;
  }
  
  public static boolean hasJellyBean()
  {
    return Build.VERSION.SDK_INT >= 16;
  }
  
  public static boolean isConnectedInternet(Activity paramActivity)
  {
    paramActivity = ((ConnectivityManager)paramActivity.getSystemService("connectivity")).getActiveNetworkInfo();
    return (paramActivity != null) && (paramActivity.isAvailable());
  }
  
  public static boolean isEmail_new(String paramString)
  {
    if ((paramString == null) || ("".equals(paramString))) {
      return false;
    }
    return Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*").matcher(paramString).matches();
  }
  
  public static boolean isExistSDCard()
  {
    return Environment.getExternalStorageState().equals("mounted");
  }
  
  public static boolean isPkgInstalled(Activity paramActivity, String paramString)
  {
    try
    {
      paramActivity = paramActivity.getPackageManager().getPackageInfo(paramString, 0);
      if (paramActivity == null) {
        return false;
      }
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      for (;;)
      {
        paramActivity = null;
        paramString.printStackTrace();
      }
    }
    return true;
  }
  
  public static void launchPicker(Activity paramActivity)
  {
    FlurryAgent.logEvent("6_selectimage");
    Intent localIntent = new Intent("android.intent.action.GET_CONTENT");
    localIntent.setType("image/*");
    try
    {
      paramActivity.startActivityForResult(localIntent, 13);
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      Toast.makeText(paramActivity, paramActivity.getResources().getString(2131165807), 0).show();
    }
  }
  
  public static File makefolder(SharedPreferences paramSharedPreferences, MyApplication paramMyApplication, Activity paramActivity)
  {
    if ("mounted".equals(Environment.getExternalStorageState())) {
      if ((paramSharedPreferences.getInt("SDCARD_PATH", 0) > 0) && (paramMyApplication.getSdcard_list().size() > 1)) {
        paramSharedPreferences = new File((String)paramMyApplication.getSdcard_list().get(paramSharedPreferences.getInt("SDCARD_PATH", 0)) + "/Android/data/com.studio.topscanner/files" + "/TopScanner/temporary/picture");
      }
    }
    for (;;)
    {
      paramSharedPreferences.mkdirs();
      return paramSharedPreferences;
      paramSharedPreferences = new File(Environment.getExternalStorageDirectory() + "/TopScanner/temporary/picture");
      continue;
      paramSharedPreferences = new File(paramActivity.getFilesDir() + "/TopScanner/temporary/picture");
    }
  }
  
  @SuppressLint({"InflateParams"})
  public static void resetPassword(Activity paramActivity, String paramString1, String paramString2)
  {
    paramActivity = new AlertDialog.Builder(paramActivity);
    paramActivity.setTitle(paramString1).setMessage(paramString2).setPositiveButton("OK", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.dismiss();
      }
    });
    paramActivity.create().show();
  }
  
  @SuppressLint({"DefaultLocale"})
  public static void sendFeedback(Activity paramActivity, MyApplication paramMyApplication)
  {
    Object localObject = new Intent("android.intent.action.SEND");
    ((Intent)localObject).setType("plain/text");
    ArrayList localArrayList = new ArrayList();
    localObject = paramActivity.getPackageManager().queryIntentActivities((Intent)localObject, 0);
    if (!((List)localObject).isEmpty())
    {
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        ResolveInfo localResolveInfo = (ResolveInfo)((Iterator)localObject).next();
        Intent localIntent = new Intent("android.intent.action.SEND_MULTIPLE");
        localIntent.setType("plain/text");
        localIntent.putExtra("android.intent.extra.SUBJECT", "Simple Scanner");
        if ((localResolveInfo.activityInfo.packageName.toLowerCase().contains("mail")) || (localResolveInfo.activityInfo.name.toLowerCase().contains("mail")) || (localResolveInfo.activityInfo.packageName.toLowerCase().contains("inbox")) || (localResolveInfo.activityInfo.packageName.toLowerCase().contains("com.fsck.k9")) || (localResolveInfo.activityInfo.name.toLowerCase().contains("blue")) || (localResolveInfo.activityInfo.packageName.toLowerCase().contains("outlook")))
        {
          localIntent.putExtra("android.intent.extra.EMAIL", new String[] { "simple.scanner@outlook.com" });
          localIntent.putExtra("android.intent.extra.SUBJECT", "Mini Simple Scanner Feedback");
          localIntent.putExtra("android.intent.extra.TEXT", paramMyApplication.getEmailInfo());
          localIntent.setPackage(localResolveInfo.activityInfo.packageName);
          localArrayList.add(localIntent);
        }
      }
      if (localArrayList.size() > 0)
      {
        paramMyApplication = Intent.createChooser((Intent)localArrayList.remove(0), paramActivity.getString(2131165563));
        paramMyApplication.putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[])localArrayList.toArray(new Parcelable[0]));
        paramActivity.startActivityForResult(paramMyApplication, 3);
      }
    }
    else
    {
      return;
    }
    Toast.makeText(paramActivity, "Can't find mail application", 0).show();
  }
  
  public static void showGooglePlayApplication(Activity paramActivity)
  {
    Object localObject = paramActivity.getPackageManager().getInstalledApplications(0);
    int j = ((List)localObject).size();
    ApplicationInfo localApplicationInfo = null;
    int i = 0;
    while (i < j)
    {
      if (((ApplicationInfo)((List)localObject).get(i)).packageName.equals("com.android.vending")) {
        localApplicationInfo = (ApplicationInfo)((List)localObject).get(i);
      }
      i += 1;
    }
    if (localApplicationInfo != null)
    {
      localObject = new Intent("android.intent.action.VIEW");
      ((Intent)localObject).setData(Uri.parse("market://details?id=com.studio.topscanner"));
      ((Intent)localObject).setPackage(localApplicationInfo.packageName);
      paramActivity.startActivity((Intent)localObject);
      return;
    }
    try
    {
      paramActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.studio.topscanner")));
      return;
    }
    catch (Exception paramActivity)
    {
      paramActivity.printStackTrace();
    }
  }
  
  public static void showGooglePlaysimplefax(Activity paramActivity)
  {
    Object localObject = paramActivity.getPackageManager().getInstalledApplications(0);
    int j = ((List)localObject).size();
    ApplicationInfo localApplicationInfo = null;
    int i = 0;
    while (i < j)
    {
      if (((ApplicationInfo)((List)localObject).get(i)).packageName.equals("com.android.vending")) {
        localApplicationInfo = (ApplicationInfo)((List)localObject).get(i);
      }
      i += 1;
    }
    if (localApplicationInfo != null)
    {
      localObject = new Intent("android.intent.action.VIEW");
      ((Intent)localObject).setData(Uri.parse("market://details?id=com.studio.topfax"));
      ((Intent)localObject).setPackage(localApplicationInfo.packageName);
      paramActivity.startActivity((Intent)localObject);
      return;
    }
    try
    {
      paramActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.studio.topfax")));
      return;
    }
    catch (Exception paramActivity)
    {
      paramActivity.printStackTrace();
    }
  }
  
  public static void showKeyboard(EditText paramEditText)
  {
    new Timer().schedule(new TimerTask()
    {
      public void run()
      {
        ((InputMethodManager)this.val$editText.getContext().getSystemService("input_method")).showSoftInput(this.val$editText, 0);
      }
    }, 200L);
  }
  
  public static void showProBuyDialog_IAP(Activity paramActivity, String paramString, IAPBuy paramIAPBuy)
  {
    String str = "IAP";
    if (!"".equals(paramString)) {
      str = "IAP(" + paramString + ")";
    }
    paramString = new AlertDialog.Builder(paramActivity);
    paramString.setTitle(paramActivity.getResources().getString(2131165595)).setMessage(paramActivity.getResources().getString(2131165811)).setNeutralButton(str, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        this.val$iapBuy.IAP_Buy();
      }
    }).setNegativeButton(paramActivity.getResources().getString(2131165274), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.cancel();
      }
    });
    paramString = paramString.create();
    paramString.setCanceledOnTouchOutside(true);
    if (!paramActivity.isFinishing()) {
      paramString.show();
    }
  }
}
