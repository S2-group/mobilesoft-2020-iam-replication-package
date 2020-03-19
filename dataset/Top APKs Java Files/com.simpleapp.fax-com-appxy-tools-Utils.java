package com.appxy.tools;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.Base64;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.simpleapp.adpter.FaxReceiptDao;
import com.simpleapp.tinyscanfree.Activity_IapCredits;
import com.simpleapp.tinyscanfree.Activity_Main;
import com.simpleapp.tinyscanfree.LoginActivity;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils
{
  private static int SCALE = 2;
  private static AlertDialog.Builder warnDialog;
  private static Dialog warnDialog11;
  
  private Utils() {}
  
  public static void closeKeyBoard(Activity paramActivity, EditText paramEditText)
  {
    ((InputMethodManager)paramActivity.getSystemService("input_method")).hideSoftInputFromWindow(paramEditText.getWindowToken(), 0);
  }
  
  @SuppressLint({"SimpleDateFormat"})
  public static String dateToString1(Date paramDate)
  {
    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(paramDate);
  }
  
  @SuppressLint({"SimpleDateFormat"})
  public static String dateToString2(Date paramDate)
  {
    return new SimpleDateFormat("dd,MMM yyyy, HH:mm", Locale.US).format(paramDate);
  }
  
  public static void decoderBase64File(String paramString1, String paramString2)
    throws Exception
  {
    paramString1 = Base64.decode(paramString1, 0);
    paramString2 = new FileOutputStream(paramString2);
    paramString2.write(paramString1);
    paramString2.close();
  }
  
  public static String encodeBase64File(String paramString)
    throws Exception
  {
    Object localObject = new File(paramString);
    paramString = new FileInputStream((File)localObject);
    localObject = new byte[(int)((File)localObject).length()];
    paramString.read((byte[])localObject);
    paramString.close();
    return Base64.encodeToString((byte[])localObject, 0);
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
    return new SimpleDateFormat("MMM dd HH:mm", Locale.US).format(paramDate);
  }
  
  @SuppressLint({"SimpleDateFormat"})
  public static String getDate_new129(Date paramDate)
  {
    return new SimpleDateFormat("yyyy, MMM dd, HH:mm", Locale.US).format(paramDate);
  }
  
  @SuppressLint({"SimpleDateFormat"})
  public static String getDatetoString(Date paramDate)
  {
    return new SimpleDateFormat("yyyy-MM-dd", Locale.US).format(paramDate);
  }
  
  @SuppressLint({"SimpleDateFormat"})
  public static String getDatetoString1(Date paramDate)
  {
    return new SimpleDateFormat("yyyy MM dd HH:mm:ss", Locale.US).format(paramDate);
  }
  
  @SuppressLint({"SimpleDateFormat"})
  public static String getDatetoString1111(Date paramDate)
  {
    return new SimpleDateFormat("yyyyMMddHHmmss", Locale.US).format(paramDate);
  }
  
  public static long getFileSize(File paramFile)
    throws Exception
  {
    if (paramFile.exists())
    {
      paramFile = new FileInputStream(paramFile);
      long l = paramFile.available();
      paramFile.close();
      return l;
    }
    paramFile.createNewFile();
    return 0L;
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
  
  public static Bitmap getSDCardImg(String paramString)
  {
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    localOptions.inPreferredConfig = Bitmap.Config.RGB_565;
    localOptions.inPurgeable = true;
    localOptions.inInputShareable = true;
    return BitmapFactory.decodeFile(paramString, localOptions);
  }
  
  @SuppressLint({"SimpleDateFormat"})
  public static Date getStringToDate(String paramString)
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("MMM dd HH:mm", Locale.US);
    try
    {
      paramString = localSimpleDateFormat.parse(paramString);
      return paramString;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return new Date();
  }
  
  @SuppressLint({"SimpleDateFormat"})
  public static Date getStringToDate1(String paramString)
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy MM dd HH:mm:ss", Locale.US);
    try
    {
      paramString = localSimpleDateFormat.parse(paramString);
      return paramString;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return new Date();
  }
  
  @SuppressLint({"SimpleDateFormat"})
  public static Date getStringToDate_new129(String paramString)
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy, MMM dd, HH:mm", Locale.US);
    try
    {
      paramString = localSimpleDateFormat.parse(paramString);
      return paramString;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return new Date();
  }
  
  public static Paint getTextPaint(Activity paramActivity)
  {
    Paint localPaint = new Paint(257);
    localPaint.setAntiAlias(true);
    localPaint.setTextSize(SCALE * 21);
    localPaint.setStrokeWidth(5.0F);
    localPaint.setTypeface(Typeface.create(Typeface.SANS_SERIF, 1));
    localPaint.setColor(ContextCompat.getColor(paramActivity, 2131623952));
    return localPaint;
  }
  
  public static Paint getTextPaint1(Activity paramActivity)
  {
    Paint localPaint = new Paint(257);
    localPaint.setAntiAlias(true);
    localPaint.setTextSize(SCALE * 23);
    localPaint.setStrokeWidth(4.0F);
    localPaint.setTypeface(Typeface.DEFAULT);
    localPaint.setColor(ContextCompat.getColor(paramActivity, 2131623952));
    return localPaint;
  }
  
  public static Paint getTextPaint2(Activity paramActivity, int paramInt)
  {
    Paint localPaint = new Paint(257);
    localPaint.setAntiAlias(true);
    localPaint.setTextSize(SCALE * 23);
    localPaint.setStrokeWidth(4.0F);
    localPaint.setTypeface(Typeface.DEFAULT);
    if (paramInt == 2) {
      localPaint.setColor(ContextCompat.getColor(paramActivity, 2131624053));
    }
    while (paramInt != 4) {
      return localPaint;
    }
    localPaint.setColor(ContextCompat.getColor(paramActivity, 2131624064));
    return localPaint;
  }
  
  public static Paint getTextPaint_line(Activity paramActivity)
  {
    Paint localPaint = new Paint(257);
    localPaint.setAntiAlias(true);
    localPaint.setTextSize(SCALE * 18);
    localPaint.setStrokeWidth(4.0F);
    localPaint.setTypeface(Typeface.DEFAULT);
    localPaint.setColor(ContextCompat.getColor(paramActivity, 2131623952));
    return localPaint;
  }
  
  public static String getTotalTime(long paramLong, Context paramContext)
  {
    if (paramLong > 3600L) {
      return (int)(paramLong / 3600L) + " " + paramContext.getResources().getString(2131231012) + " " + (int)(paramLong % 3600L / 60L) + " " + paramContext.getResources().getString(2131231030) + " " + paramLong % 60L + "  " + paramContext.getResources().getString(2131231101);
    }
    if (paramLong > 60L) {
      return (int)(paramLong / 60L) + " " + paramContext.getResources().getString(2131231031) + " " + paramLong % 60L + " " + paramContext.getResources().getString(2131231105);
    }
    return paramLong + " " + paramContext.getResources().getString(2131231105);
  }
  
  public static String getWebsiteDatetime(String paramString)
  {
    try
    {
      paramString = new URL(paramString).openConnection();
      paramString.connect();
      paramString = new Date(paramString.getDate());
      paramString = new SimpleDateFormat("yyyy-MM-dd", Locale.US).format(paramString);
      return paramString;
    }
    catch (MalformedURLException paramString)
    {
      paramString.printStackTrace();
      return getDatetoString(new Date());
    }
    catch (IOException paramString)
    {
      for (;;)
      {
        paramString.printStackTrace();
      }
    }
  }
  
  public static String getstring()
  {
    Timestamp localTimestamp = new Timestamp(System.currentTimeMillis() + 28800000L);
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
    localSimpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
    return localSimpleDateFormat.format(localTimestamp);
  }
  
  public static String getstring1()
  {
    Timestamp localTimestamp = new Timestamp(System.currentTimeMillis() + 28800000L);
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.US);
    localSimpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
    return localSimpleDateFormat.format(localTimestamp);
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
  
  public static String initFaxReceiptImage(FaxReceiptDao paramFaxReceiptDao, Activity paramActivity, boolean paramBoolean)
  {
    Bitmap localBitmap = Bitmap.createBitmap((int)(PageSize.A4.getWidth() * SCALE), (int)(PageSize.A4.getHeight() * SCALE), Bitmap.Config.ARGB_8888);
    Canvas localCanvas = new Canvas(localBitmap);
    localCanvas.setDrawFilter(new PaintFlagsDrawFilter(0, 3));
    localCanvas.drawColor(-1);
    Object localObject = new Paint(257);
    ((Paint)localObject).setAntiAlias(true);
    ((Paint)localObject).setTextSize(SCALE * 50);
    ((Paint)localObject).setTypeface(Typeface.DEFAULT);
    ((Paint)localObject).setColor(ContextCompat.getColor(paramActivity, 2131623952));
    if (paramBoolean) {
      localCanvas.drawText(paramActivity.getResources().getString(2131230847), PageSize.A4.getWidth() * SCALE - getTextPaint_line(paramActivity).measureText(paramActivity.getResources().getString(2131230847)) - SCALE * 20, PageSize.A4.getHeight() * SCALE - SCALE * 10, getTextPaint_line(paramActivity));
    }
    localCanvas.drawText(paramActivity.getResources().getString(2131230986).toUpperCase(), PageSize.A4.getWidth() - ((Paint)localObject).measureText(paramActivity.getResources().getString(2131230986).toUpperCase()) / 2.0F, SCALE * 90, (Paint)localObject);
    localObject = dateToString2(new Date());
    localCanvas.drawText((String)localObject, PageSize.A4.getWidth() - getTextPaint(paramActivity).measureText((String)localObject) / 2.0F, SCALE * 120, getTextPaint(paramActivity));
    localCanvas.drawLine(SCALE * 52, SCALE * 130, SCALE * 552, SCALE * 130, getTextPaint_line(paramActivity));
    localCanvas.drawText(paramActivity.getResources().getString(2131230983) + ": ", SCALE * 80, SCALE * 172, getTextPaint(paramActivity));
    localCanvas.drawText("#" + paramFaxReceiptDao.getFaxID(), SCALE * 260, SCALE * 172, getTextPaint1(paramActivity));
    localCanvas.drawText(paramActivity.getResources().getString(2131231050) + ": ", SCALE * 80, SCALE * 212, getTextPaint(paramActivity));
    localCanvas.drawText("+" + paramFaxReceiptDao.getFaxnumber(), SCALE * 260, SCALE * 212, getTextPaint1(paramActivity));
    localCanvas.drawText(paramActivity.getResources().getString(2131231057) + ": ", SCALE * 80, SCALE * 252, getTextPaint(paramActivity));
    localCanvas.drawText(paramFaxReceiptDao.getFaxPages(), SCALE * 260, SCALE * 252, getTextPaint1(paramActivity));
    localCanvas.drawText(paramActivity.getResources().getString(2131230866) + ": ", SCALE * 80, SCALE * 292, getTextPaint(paramActivity));
    localCanvas.drawText(paramFaxReceiptDao.getFaxCost(), SCALE * 260, SCALE * 292, getTextPaint1(paramActivity));
    localCanvas.drawText(paramActivity.getResources().getString(2131231095) + ": ", SCALE * 80, SCALE * 332, getTextPaint(paramActivity));
    localCanvas.drawText(paramFaxReceiptDao.getRequestTimer(), SCALE * 260, SCALE * 332, getTextPaint1(paramActivity));
    localCanvas.drawText(paramActivity.getResources().getString(2131231163) + ": ", SCALE * 80, SCALE * 372, getTextPaint(paramActivity));
    localCanvas.drawText(paramFaxReceiptDao.getDurationTime(), SCALE * 260, SCALE * 372, getTextPaint1(paramActivity));
    localCanvas.drawText(paramActivity.getResources().getString(2131230865) + ": ", SCALE * 80, SCALE * 412, getTextPaint(paramActivity));
    localCanvas.drawText(paramFaxReceiptDao.getCompletedTimer(), SCALE * 260, SCALE * 412, getTextPaint1(paramActivity));
    localCanvas.drawText(paramActivity.getResources().getString(2131231128) + ": ", SCALE * 80, SCALE * 452, getTextPaint(paramActivity));
    if (paramActivity.getResources().getString(2131230979).equals(paramFaxReceiptDao.getFaxStatus())) {
      localCanvas.drawText(paramFaxReceiptDao.getFaxStatus(), SCALE * 260, SCALE * 452, getTextPaint2(paramActivity, 2));
    }
    for (;;)
    {
      localObject = new TextPaint();
      ((TextPaint)localObject).setTextSize(40.0F);
      ((TextPaint)localObject).setColor(ContextCompat.getColor(paramActivity, 2131624053));
      if ((paramFaxReceiptDao.getFaxErrorMsg() != null) && (!paramFaxReceiptDao.getFaxErrorMsg().equals("")))
      {
        localCanvas.drawText(paramActivity.getResources().getString(2131230975) + ": ", SCALE * 80, SCALE * 492, getTextPaint(paramActivity));
        localObject = new StaticLayout(paramFaxReceiptDao.getFaxErrorMsg(), (TextPaint)localObject, (int)(PageSize.A4.getWidth() * 2.0F - 300.0F), Layout.Alignment.ALIGN_NORMAL, 1.0F, 0.0F, true);
        localCanvas.save();
        localCanvas.translate(SCALE * 80, SCALE * 502);
        ((StaticLayout)localObject).draw(localCanvas);
        localCanvas.restore();
      }
      localCanvas.save(31);
      localCanvas.restore();
      return save(localBitmap, paramFaxReceiptDao, paramActivity);
      localCanvas.drawText(paramFaxReceiptDao.getFaxStatus(), SCALE * 260, SCALE * 452, getTextPaint2(paramActivity, 4));
    }
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
  
  public static boolean isPad(Context paramContext)
  {
    return (paramContext.getResources().getConfiguration().screenLayout & 0xF) >= 3;
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
  
  public static void launchPickerImage(Activity paramActivity)
  {
    Intent localIntent = new Intent("android.intent.action.GET_CONTENT");
    localIntent.setType("image/*");
    try
    {
      paramActivity.startActivityForResult(localIntent, 10);
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      Toast.makeText(paramActivity, paramActivity.getResources().getString(2131231168), 0).show();
    }
  }
  
  public static void launchPickerPDF(Activity paramActivity)
  {
    Intent localIntent = new Intent("android.intent.action.GET_CONTENT");
    localIntent.setType("application/pdf");
    try
    {
      paramActivity.startActivityForResult(localIntent, 11);
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      Toast.makeText(paramActivity, paramActivity.getResources().getString(2131231168), 0).show();
    }
  }
  
  public static File makefolder(Activity paramActivity)
  {
    if ("mounted".equals(Environment.getExternalStorageState())) {}
    for (paramActivity = new File(Environment.getExternalStorageDirectory() + "/SimpleFax/temporary");; paramActivity = new File(paramActivity.getFilesDir() + "/SimpleFax/temporary"))
    {
      paramActivity.mkdirs();
      return paramActivity;
    }
  }
  
  public static File makefolder_receipt()
  {
    File localFile = new File(Environment.getExternalStorageDirectory() + "/SimpleFax/receipt/");
    if (!localFile.exists()) {
      localFile.mkdirs();
    }
    return localFile;
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
  
  @SuppressLint({"SimpleDateFormat"})
  public static String save(Bitmap paramBitmap, FaxReceiptDao paramFaxReceiptDao, Activity paramActivity)
  {
    if (isExistSDCard())
    {
      paramActivity = makefolder_receipt();
      paramFaxReceiptDao = paramFaxReceiptDao.getFaxID() + "_" + paramFaxReceiptDao.getFaxnumber() + ".jpg";
      paramFaxReceiptDao = paramActivity.getPath() + "/" + paramFaxReceiptDao;
      paramActivity = new File(paramFaxReceiptDao);
      try
      {
        paramActivity = new BufferedOutputStream(new FileOutputStream(paramActivity));
        paramBitmap.printStackTrace();
      }
      catch (FileNotFoundException paramBitmap)
      {
        try
        {
          paramBitmap.compress(Bitmap.CompressFormat.JPEG, 100, paramActivity);
          paramActivity.flush();
          paramActivity.close();
          return paramFaxReceiptDao;
        }
        catch (IOException paramBitmap)
        {
          for (;;) {}
        }
        catch (FileNotFoundException paramBitmap)
        {
          for (;;) {}
        }
        paramBitmap = paramBitmap;
        paramBitmap.printStackTrace();
        return "";
      }
      catch (IOException paramBitmap) {}
      return "";
    }
    else
    {
      Toast.makeText(paramActivity, paramActivity.getResources().getString(2131231104), 0).show();
      return "";
    }
  }
  
  public static void setNitifatime(SharedPreferences paramSharedPreferences, Context paramContext)
  {
    SharedPreferences.Editor localEditor = paramSharedPreferences.edit();
    Calendar localCalendar1 = Calendar.getInstance();
    Calendar localCalendar2 = Calendar.getInstance();
    int i = localCalendar2.get(1);
    int j = localCalendar2.get(2);
    int k = localCalendar2.get(5);
    localCalendar2.set(1, i);
    localCalendar2.set(2, j);
    localCalendar2.set(5, k);
    localCalendar2.set(11, Integer.valueOf(paramSharedPreferences.getString("remind_hours", "10")).intValue());
    localCalendar2.set(12, Integer.valueOf(paramSharedPreferences.getString("remind_minutes", "00")).intValue());
    localCalendar2.set(13, 0);
    if (localCalendar2.getTimeInMillis() >= localCalendar1.getTimeInMillis())
    {
      localEditor.putString("enrncredits_notification_time", localCalendar2.getTimeInMillis() + "");
      localEditor.commit();
    }
    for (;;)
    {
      paramContext.startService(new Intent(paramContext, PollingService.class));
      return;
      localCalendar1.setTimeInMillis(localCalendar1.getTimeInMillis() + 86400000L);
      i = localCalendar1.get(1);
      j = localCalendar1.get(2);
      k = localCalendar1.get(5);
      localCalendar1.set(1, i);
      localCalendar1.set(2, j);
      localCalendar1.set(5, k);
      localCalendar1.set(11, Integer.valueOf(paramSharedPreferences.getString("remind_hours", "10")).intValue());
      localCalendar1.set(12, Integer.valueOf(paramSharedPreferences.getString("remind_minutes", "00")).intValue());
      localCalendar1.set(13, 0);
      localEditor.putString("enrncredits_notification_time", localCalendar1.getTimeInMillis() + "");
      localEditor.commit();
    }
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
      ((Intent)localObject).setData(Uri.parse("market://details?id=com.simpleapp.fax"));
      ((Intent)localObject).setPackage(localApplicationInfo.packageName);
      paramActivity.startActivity((Intent)localObject);
      return;
    }
    try
    {
      paramActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.simpleapp.fax")));
      return;
    }
    catch (Exception paramActivity)
    {
      paramActivity.printStackTrace();
    }
  }
  
  public static void showGooglePlayPDFviewer(Activity paramActivity)
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
      ((Intent)localObject).setData(Uri.parse("market://details?id=com.easyinc.pdfviewer"));
      ((Intent)localObject).setPackage(localApplicationInfo.packageName);
      paramActivity.startActivity((Intent)localObject);
      return;
    }
    try
    {
      paramActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.easyinc.pdfviewer")));
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
  
  public static void showTipsSaveDraft(Activity paramActivity, String paramString1, String paramString2)
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
  
  @SuppressLint({"InflateParams"})
  public static void showdengluDialogTip(Activity paramActivity, String paramString1, String paramString2)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(paramActivity);
    localBuilder.setTitle(paramString1).setMessage(paramString2).setPositiveButton(paramActivity.getResources().getString(2131231054), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.dismiss();
        paramAnonymousDialogInterface = new Intent(this.val$mActivity, LoginActivity.class);
        this.val$mActivity.startActivity(paramAnonymousDialogInterface);
      }
    }).setNegativeButton(paramActivity.getResources().getString(2131230849), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.dismiss();
      }
    });
    paramActivity = localBuilder.create();
    paramActivity.setCanceledOnTouchOutside(true);
    paramActivity.show();
  }
  
  @SuppressLint({"InflateParams"})
  public static void showpurchaseDialogTip(Activity paramActivity, String paramString1, String paramString2)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(paramActivity);
    localBuilder.setTitle(paramString1).setMessage(paramString2).setPositiveButton(paramActivity.getResources().getString(2131231054), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.dismiss();
        paramAnonymousDialogInterface = new Intent(this.val$mActivity, Activity_IapCredits.class);
        this.val$mActivity.startActivity(paramAnonymousDialogInterface);
      }
    }).setNegativeButton(paramActivity.getResources().getString(2131230849), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.dismiss();
      }
    });
    paramActivity = localBuilder.create();
    paramActivity.setCanceledOnTouchOutside(true);
    paramActivity.show();
  }
  
  @SuppressLint({"InflateParams"})
  public static void showresult(final Activity paramActivity, String paramString1, String paramString2, int paramInt)
  {
    warnDialog = new AlertDialog.Builder(paramActivity);
    warnDialog.setTitle(paramString1).setMessage(paramString2).setPositiveButton(paramActivity.getResources().getString(2131231054), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.dismiss();
        if (this.val$index == 1)
        {
          paramActivity.finish();
          paramAnonymousDialogInterface = new Intent(paramActivity, Activity_Main.class);
          paramAnonymousDialogInterface.setFlags(67108864);
          paramActivity.startActivity(paramAnonymousDialogInterface);
        }
      }
    });
    try
    {
      if ((warnDialog11 != null) && (warnDialog11.isShowing()) && (!paramActivity.isFinishing())) {
        warnDialog11.dismiss();
      }
      warnDialog11 = null;
    }
    catch (IllegalArgumentException paramString1)
    {
      for (;;)
      {
        paramString1 = paramString1;
        warnDialog11 = null;
      }
    }
    catch (Exception paramString1)
    {
      for (;;)
      {
        paramString1 = paramString1;
        warnDialog11 = null;
      }
    }
    finally
    {
      paramActivity = finally;
      warnDialog11 = null;
      throw paramActivity;
    }
    warnDialog11 = warnDialog.create();
    warnDialog11.setCanceledOnTouchOutside(false);
    if (!paramActivity.isFinishing()) {
      warnDialog11.show();
    }
  }
}
