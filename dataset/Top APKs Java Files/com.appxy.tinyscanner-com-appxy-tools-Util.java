package com.appxy.tools;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Environment;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfReader;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class Util
{
  public static final int SIZETYPE_B = 1;
  public static final int SIZETYPE_GB = 4;
  public static final int SIZETYPE_KB = 2;
  public static final int SIZETYPE_MB = 3;
  
  public Util() {}
  
  public static String FileName()
  {
    File[] arrayOfFile = new File(Environment.getExternalStorageDirectory() + "/MyTinyScan/").listFiles();
    if (arrayOfFile != null)
    {
      int i = 0;
      while (i < arrayOfFile.length)
      {
        if (!arrayOfFile[i].isDirectory())
        {
          String str = arrayOfFile[i].getName();
          if (str.trim().endsWith(".Tinyscanner_1_2_7")) {
            return str.trim();
          }
        }
        i += 1;
      }
    }
    return "";
  }
  
  private static double FormetFileSize(long paramLong, int paramInt)
  {
    DecimalFormat localDecimalFormat = new DecimalFormat("#.00");
    switch (paramInt)
    {
    default: 
      return 0.0D;
    case 1: 
      return Double.valueOf(localDecimalFormat.format(paramLong)).doubleValue();
    case 2: 
      return Double.valueOf(localDecimalFormat.format(paramLong / 1024.0D)).doubleValue();
    case 3: 
      return Double.valueOf(localDecimalFormat.format(paramLong / 1048576.0D)).doubleValue();
    }
    return Double.valueOf(localDecimalFormat.format(paramLong / 1.073741824E9D)).doubleValue();
  }
  
  private static String FormetFileSize(long paramLong)
  {
    Object localObject = new DecimalFormat("#.00");
    if (paramLong == 0L) {
      return "0B";
    }
    if (paramLong < 1024L) {
      localObject = ((DecimalFormat)localObject).format(paramLong) + "B";
    }
    for (;;)
    {
      return localObject;
      if (paramLong < 1048576L) {
        localObject = ((DecimalFormat)localObject).format(paramLong / 1024.0D) + "KB";
      } else if (paramLong < 1073741824L) {
        localObject = ((DecimalFormat)localObject).format(paramLong / 1048576.0D) + "MB";
      } else {
        localObject = ((DecimalFormat)localObject).format(paramLong / 1.073741824E9D) + "GB";
      }
    }
  }
  
  public static String FormetFileSize1(long paramLong)
  {
    DecimalFormat localDecimalFormat = new DecimalFormat("#.00");
    if (paramLong < 1024L) {
      return localDecimalFormat.format(paramLong) + "B";
    }
    if (paramLong < 1048576L) {
      return localDecimalFormat.format(paramLong / 1024.0D) + "K";
    }
    if (paramLong < 1073741824L) {
      return localDecimalFormat.format(paramLong / 1048576.0D) + "M";
    }
    return localDecimalFormat.format(paramLong / 1.073741824E9D) + "G";
  }
  
  @SuppressLint({"DefaultLocale"})
  public static String bytesToHex(byte[] paramArrayOfByte)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    while (i < paramArrayOfByte.length)
    {
      int j = paramArrayOfByte[i] & 0xFF;
      if (j < 16) {
        localStringBuilder.append("0");
      }
      localStringBuilder.append(Integer.toHexString(j).toUpperCase());
      i += 1;
    }
    return localStringBuilder.toString();
  }
  
  public static String dateToString(Date paramDate)
  {
    return new SimpleDateFormat("MMM dd yyyy", Locale.US).format(paramDate);
  }
  
  public static String dateToString1(Date paramDate)
  {
    return new SimpleDateFormat("MMM dd yyyy, HH:mm:ss", Locale.US).format(paramDate);
  }
  
  @SuppressLint({"DefaultLocale"})
  public static boolean findAndGotoApp1(Activity paramActivity, String paramString, ArrayList<Uri> paramArrayList, long paramLong, int paramInt)
  {
    int j = 0;
    Intent localIntent = new Intent("android.intent.action.SEND");
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
      localIntent.putExtra("android.intent.extra.STREAM", (Parcelable)paramArrayList.get(0));
      localIntent.setPackage(localResolveInfo.activityInfo.packageName);
      int i = 1;
      if (i == 0) {
        return false;
      }
      if (paramInt == 0)
      {
        if (paramLong > 10485760L)
        {
          showWarning(paramActivity);
          return true;
        }
      }
      else if (paramInt == 1)
      {
        if (getFilesizeAbout(paramLong, 1) > 10485760L)
        {
          showWarning(paramActivity);
          return true;
        }
      }
      else if ((paramInt == 2) && (getFilesizeAbout(paramLong, 2) > 10485760L))
      {
        showWarning(paramActivity);
        return true;
      }
      paramActivity.startActivity(Intent.createChooser(localIntent, "Select"));
      return true;
    }
    return false;
  }
  
  public static String getAutoFileOrFilesSize(String paramString)
  {
    paramString = new File(paramString);
    for (long l1 = 0L;; l1 = l2)
    {
      try
      {
        if (!paramString.isDirectory()) {
          break label30;
        }
        l2 = getFileSizes(paramString);
        l1 = l2;
      }
      catch (Exception paramString)
      {
        for (;;)
        {
          long l2;
          label30:
          paramString.printStackTrace();
        }
      }
      return FormetFileSize(l1);
      l2 = getFileSize(paramString);
    }
  }
  
  public static double getFileOrFilesSize(String paramString, int paramInt)
  {
    paramString = new File(paramString);
    for (long l1 = 0L;; l1 = l2)
    {
      try
      {
        if (!paramString.isDirectory()) {
          break label33;
        }
        l2 = getFileSizes(paramString);
        l1 = l2;
      }
      catch (Exception paramString)
      {
        for (;;)
        {
          long l2;
          label33:
          paramString.printStackTrace();
        }
      }
      return FormetFileSize(l1, paramInt);
      l2 = getFileSize(paramString);
    }
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
  
  private static long getFileSizes(File paramFile)
    throws Exception
  {
    long l = 0L;
    paramFile = paramFile.listFiles();
    int i = 0;
    if (i < paramFile.length)
    {
      if (paramFile[i].isDirectory()) {}
      for (l += getFileSizes(paramFile[i]);; l += getFileSize(paramFile[i]))
      {
        i += 1;
        break;
      }
    }
    return l;
  }
  
  public static long getFilesizeAbout(long paramLong, int paramInt)
  {
    if (paramLong < 1048576L)
    {
      if (paramInt == 1) {
        return paramLong * 60L / 100L;
      }
      return paramLong * 19L / 100L;
    }
    if (paramLong < 4194304L)
    {
      if (paramInt == 1) {
        return paramLong * 60L / 100L;
      }
      return paramLong * 15L / 100L;
    }
    if (paramLong < 15728640L)
    {
      if (paramInt == 1) {
        return 56L * paramLong / 100L;
      }
      return paramLong * 19L / 100L;
    }
    if (paramInt == 1) {
      return 53L * paramLong / 100L;
    }
    return paramLong * 15L / 100L;
  }
  
  @SuppressLint({"DefaultLocale"})
  public static String getIPAddress(boolean paramBoolean)
  {
    try
    {
      label43:
      Object localObject2;
      boolean bool;
      do
      {
        do
        {
          do
          {
            localObject1 = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
            break label43;
            Iterator localIterator;
            while (!localIterator.hasNext())
            {
              if (!((Iterator)localObject1).hasNext()) {
                break;
              }
              localIterator = Collections.list(((NetworkInterface)((Iterator)localObject1).next()).getInetAddresses()).iterator();
            }
            localObject2 = (InetAddress)localIterator.next();
          } while (((InetAddress)localObject2).isLoopbackAddress());
          localObject2 = ((InetAddress)localObject2).getHostAddress().toUpperCase();
          bool = InetAddressUtils.isIPv4Address((String)localObject2);
          if (!paramBoolean) {
            break;
          }
        } while (!bool);
        return localObject2;
      } while (bool);
      int i = ((String)localObject2).indexOf('%');
      Object localObject1 = localObject2;
      if (i >= 0)
      {
        localObject1 = ((String)localObject2).substring(0, i);
        return localObject1;
      }
    }
    catch (Exception localException)
    {
      String str = "";
      return str;
    }
  }
  
  public static String getMACAddress(String paramString)
  {
    try
    {
      Object localObject = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
      while (((Iterator)localObject).hasNext())
      {
        NetworkInterface localNetworkInterface = (NetworkInterface)((Iterator)localObject).next();
        if ((paramString == null) || (localNetworkInterface.getName().equalsIgnoreCase(paramString)))
        {
          paramString = localNetworkInterface.getHardwareAddress();
          if (paramString == null) {
            return "";
          }
          localObject = new StringBuilder();
          int i = 0;
          while (i < paramString.length)
          {
            ((StringBuilder)localObject).append(String.format("%02X:", new Object[] { Byte.valueOf(paramString[i]) }));
            i += 1;
          }
          if (((StringBuilder)localObject).length() > 0) {
            ((StringBuilder)localObject).deleteCharAt(((StringBuilder)localObject).length() - 1);
          }
          paramString = ((StringBuilder)localObject).toString();
          return paramString;
        }
      }
    }
    catch (Exception paramString) {}
    return "";
  }
  
  public static int getPdfPages(String paramString)
  {
    try
    {
      Document localDocument = new Document(new PdfReader(paramString).getPageSize(1));
      localDocument.open();
      int i = new PdfReader(paramString).getNumberOfPages();
      if (i != 0) {
        localDocument.close();
      }
      return i;
    }
    catch (IOException paramString)
    {
      paramString.printStackTrace();
    }
    return 0;
  }
  
  public static Long getTotalMemory(Context paramContext)
  {
    long l2 = 0L;
    long l1 = l2;
    try
    {
      paramContext = new BufferedReader(new FileReader("/proc/meminfo"), 8192);
      l1 = l2;
      String str1 = paramContext.readLine();
      l1 = l2;
      String[] arrayOfString = str1.split("\\s+");
      l1 = l2;
      int j = arrayOfString.length;
      int i = 0;
      while (i < j)
      {
        String str2 = arrayOfString[i];
        l1 = l2;
        Log.i(str1, str2 + "/t");
        i += 1;
      }
      l1 = l2;
      l2 = Long.valueOf(arrayOfString[1]).longValue();
      l1 = l2;
      paramContext.close();
      l1 = l2;
    }
    catch (IOException paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
      }
    }
    return Long.valueOf(l1);
  }
  
  public static byte[] getUTF8Bytes(String paramString)
  {
    try
    {
      paramString = paramString.getBytes("UTF-8");
      return paramString;
    }
    catch (Exception paramString) {}
    return null;
  }
  
  public static void hideKeyboard(View paramView)
  {
    InputMethodManager localInputMethodManager = (InputMethodManager)paramView.getContext().getSystemService("input_method");
    if (localInputMethodManager != null) {
      localInputMethodManager.hideSoftInputFromWindow(paramView.getWindowToken(), 0);
    }
  }
  
  public static boolean isOpenPdf(String paramString)
  {
    boolean bool3 = false;
    boolean bool1 = false;
    boolean bool2 = bool3;
    try
    {
      Document localDocument = new Document(new PdfReader(paramString).getPageSize(1));
      bool2 = bool3;
      localDocument.open();
      bool2 = bool3;
      if (new PdfReader(paramString).getNumberOfPages() != 0) {
        bool1 = true;
      }
      bool2 = bool1;
      localDocument.close();
      return bool1;
    }
    catch (IOException paramString)
    {
      paramString.printStackTrace();
    }
    return bool2;
  }
  
  public static boolean isPkgInstalled(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramString, 0);
      if (paramContext == null) {
        return false;
      }
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      for (;;)
      {
        paramContext = null;
        paramString.printStackTrace();
      }
    }
    return true;
  }
  
  public static String loadFileAsString(String paramString)
    throws IOException
  {
    BufferedInputStream localBufferedInputStream = new BufferedInputStream(new FileInputStream(paramString), 1024);
    for (;;)
    {
      int j;
      int i;
      int k;
      try
      {
        paramString = new ByteArrayOutputStream(1024);
        byte[] arrayOfByte = new byte['Ѐ'];
        j = 0;
        i = 0;
        k = localBufferedInputStream.read(arrayOfByte);
        if (k != -1)
        {
          if ((i == 0) && (arrayOfByte[0] == -17) && (arrayOfByte[1] == -69) && (arrayOfByte[2] == -65))
          {
            j = 1;
            paramString.write(arrayOfByte, 3, k - 3);
            break label170;
          }
          paramString.write(arrayOfByte, 0, k);
          break label170;
        }
      }
      finally {}
      try
      {
        localBufferedInputStream.close();
        throw paramString;
        if (j != 0) {}
        for (paramString = new String(paramString.toByteArray(), "UTF-8");; paramString = new String(paramString.toByteArray())) {
          try
          {
            localBufferedInputStream.close();
            return paramString;
          }
          catch (Exception localException1)
          {
            return paramString;
          }
        }
      }
      catch (Exception localException2)
      {
        for (;;) {}
      }
      label170:
      i += k;
    }
  }
  
  public static void showGooglePlayFax(Activity paramActivity)
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
      ((Intent)localObject).setData(Uri.parse("market://details?id=com.appxy.tinyfax"));
      ((Intent)localObject).setPackage(localApplicationInfo.packageName);
      paramActivity.startActivity((Intent)localObject);
      return;
    }
    paramActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=com.appxy.tinyfax")));
  }
  
  public static void showGooglePlayFax11(Activity paramActivity)
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
      ((Intent)localObject).setData(Uri.parse("market://details?id=com.appxy.tinyfaxintl"));
      ((Intent)localObject).setPackage(localApplicationInfo.packageName);
      paramActivity.startActivity((Intent)localObject);
      return;
    }
    paramActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=com.appxy.tinyfaxintl")));
  }
  
  public static void showGooglePlayFax_pro(Activity paramActivity)
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
      ((Intent)localObject).setData(Uri.parse("market://details?id=com.appxy.tinyfaxplus"));
      ((Intent)localObject).setPackage(localApplicationInfo.packageName);
      paramActivity.startActivity((Intent)localObject);
      return;
    }
    paramActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=com.appxy.tinyfaxplus")));
  }
  
  public static void showKeyboard(View paramView)
  {
    InputMethodManager localInputMethodManager = (InputMethodManager)paramView.getContext().getSystemService("input_method");
    if (localInputMethodManager != null)
    {
      paramView.requestFocus();
      localInputMethodManager.showSoftInput(paramView, 0);
    }
  }
  
  public static void showWarning(Context paramContext)
  {
    new AlertDialog.Builder(paramContext).setTitle("Warning").setMessage("Tiny Fax does not support PDF which is more than 10 M.").setPositiveButton("OK", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.dismiss();
      }
    }).create().show();
  }
  
  public static Date strToDate(String paramString)
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
    try
    {
      paramString = localSimpleDateFormat.parse(paramString);
      return paramString;
    }
    catch (ParseException paramString)
    {
      paramString.printStackTrace();
    }
    return new Date();
  }
  
  @SuppressLint({"SimpleDateFormat"})
  public static boolean stringToDate(String paramString)
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("MMM dd yyyy", Locale.US);
    try
    {
      localSimpleDateFormat.parse(paramString);
      return true;
    }
    catch (ParseException paramString)
    {
      paramString.printStackTrace();
    }
    return false;
  }
  
  @SuppressLint({"SimpleDateFormat"})
  public static Date stringToDate1(String paramString)
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("MMM dd yyyy, HH:mm:ss", Locale.US);
    try
    {
      paramString = localSimpleDateFormat.parse(paramString);
      return paramString;
    }
    catch (ParseException paramString)
    {
      paramString.printStackTrace();
    }
    return new Date();
  }
  
  public static void toggleSoftInput(View paramView)
  {
    paramView = (InputMethodManager)paramView.getContext().getSystemService("input_method");
    if (paramView != null) {
      paramView.toggleSoftInput(0, 0);
    }
  }
}
