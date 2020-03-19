package com.d9lab.ati.whatiesdk.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.widget.TextView;
import java.io.PrintStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseUtil
{
  public ParseUtil() {}
  
  public static boolean compareDate(String paramString1, String paramString2)
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("HH:mm");
    System.out.println("dt1/dt2 " + paramString1 + "/" + paramString2);
    try
    {
      paramString1 = localSimpleDateFormat.parse(paramString1);
      paramString2 = localSimpleDateFormat.parse(paramString2);
      if (paramString1.getTime() > paramString2.getTime()) {
        return true;
      }
      long l1 = paramString1.getTime();
      long l2 = paramString2.getTime();
      if (l1 < l2) {
        return false;
      }
    }
    catch (ParseException paramString1)
    {
      paramString1.printStackTrace();
    }
    return false;
  }
  
  public static String dateToString(Date paramDate, String paramString)
  {
    String str = null;
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    if (paramString.equals("SHORT")) {
      str = DateFormat.getDateInstance(3).format(paramDate);
    }
    do
    {
      return str;
      if (paramString.equals("MEDIUM")) {
        return localSimpleDateFormat.format(paramDate);
      }
      if (paramString.equals("MEDIUMTX")) {
        return new SimpleDateFormat("yyyy年MM月dd日").format(paramDate);
      }
      if (paramString.equals("FULL")) {
        return DateFormat.getDateInstance(0).format(paramDate);
      }
      if (paramString.equals("FULLHm")) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(paramDate);
      }
      if (paramString.equals("FULLHmTx")) {
        return new SimpleDateFormat("yyyy年MM月dd日 HH:mm").format(paramDate);
      }
      if (paramString.equals("FULLHms")) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(paramDate);
      }
      if (paramString.equals("Hm")) {
        return new SimpleDateFormat("HH:mm").format(paramDate);
      }
      if (paramString.equals("ms")) {
        return new SimpleDateFormat("mm:ss").format(paramDate);
      }
    } while (!paramString.equals("Hms"));
    paramString = new SimpleDateFormat("HH:mm:ss");
    paramString.setTimeZone(TimeZone.getTimeZone("Asia/Beijing"));
    return paramString.format(paramDate);
  }
  
  public static int dip2px(Context paramContext, int paramInt)
  {
    return (int)(paramContext.getResources().getDisplayMetrics().density * paramInt + 0.5F);
  }
  
  public static void fixTextSize(TextView paramTextView, String paramString)
  {
    if (paramString.length() == 3)
    {
      paramTextView.setTextSize(2, 12.0F);
      paramTextView.setText(paramString);
      return;
    }
    if (paramString.length() > 3)
    {
      paramTextView.setTextSize(2, 10.0F);
      paramTextView.setText(paramString);
      return;
    }
    paramTextView.setTextSize(2, 14.0F);
    paramTextView.setText(paramString);
  }
  
  public static String getDayBefore(Date paramDate, int paramInt)
  {
    if (paramInt == 0) {
      return "今日方案";
    }
    Calendar localCalendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+8:00"));
    localCalendar.setTime(paramDate);
    localCalendar.set(5, localCalendar.get(5) + paramInt);
    switch (localCalendar.get(7))
    {
    default: 
      return "";
    case 1: 
      return "周日";
    case 2: 
      return "周一";
    case 3: 
      return "周二";
    case 4: 
      return "周三";
    case 5: 
      return "周四";
    case 6: 
      return "周五";
    }
    return "周六";
  }
  
  public static String getDecimalForOne(int paramInt1, int paramInt2)
  {
    double d = paramInt1 * 1.0D / (paramInt2 * 1.0D);
    return new DecimalFormat("#.0").format(d);
  }
  
  public static String getPercent(int paramInt1, int paramInt2)
  {
    double d = paramInt1 * 1.0D / (paramInt2 * 1.0D);
    return new DecimalFormat("##%").format(d);
  }
  
  public static String getPercentWithdecimal(int paramInt1, int paramInt2)
  {
    double d = paramInt1 * 1.0D / (paramInt2 * 1.0D);
    return new DecimalFormat("##.00%").format(d);
  }
  
  public static int getScreenHeight(Context paramContext)
  {
    return ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getHeight();
  }
  
  public static int getScreenWidth(Context paramContext)
  {
    return ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getWidth();
  }
  
  public static boolean hasSdcard()
  {
    return Environment.getExternalStorageState().equals("mounted");
  }
  
  private static boolean isAvilible(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    ArrayList localArrayList = new ArrayList();
    if (paramContext != null)
    {
      int i = 0;
      while (i < paramContext.size())
      {
        localArrayList.add(((PackageInfo)paramContext.get(i)).packageName);
        i += 1;
      }
    }
    return localArrayList.contains(paramString);
  }
  
  public static boolean isNotEmpty(String... paramVarArgs)
  {
    boolean bool2 = false;
    int j = paramVarArgs.length;
    int i = 0;
    while (i < j)
    {
      String str = paramVarArgs[i];
      bool1 = bool2;
      if (str == null) {
        return bool1;
      }
      bool1 = bool2;
      if (str.isEmpty()) {
        return bool1;
      }
      i += 1;
    }
    boolean bool1 = true;
    return bool1;
  }
  
  public static String md5(String paramString)
  {
    Object localObject = null;
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      localObject = localMessageDigest;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      for (;;)
      {
        int i;
        localNoSuchAlgorithmException.printStackTrace();
      }
    }
    ((MessageDigest)localObject).update(paramString.getBytes());
    paramString = new StringBuffer();
    localObject = ((MessageDigest)localObject).digest();
    i = 0;
    while (i < localObject.length)
    {
      int k = localObject[i];
      int j = k;
      if (k < 0) {
        j = k + 256;
      }
      if (j < 16) {
        paramString.append("0");
      }
      paramString.append(Integer.toHexString(j));
      i += 1;
    }
    return paramString.toString().toLowerCase();
  }
  
  public static int px2dip(Context paramContext, int paramInt)
  {
    float f = paramContext.getResources().getDisplayMetrics().density;
    return (int)(paramInt / f + 0.5F);
  }
  
  public static String removeDigital(String paramString)
  {
    return Pattern.compile("[\\d]").matcher(paramString).replaceAll("");
  }
  
  public static String removeLetter(String paramString)
  {
    return Pattern.compile("[a-zA-z]").matcher(paramString).replaceAll("");
  }
  
  public static int[] rgbStringToInts(String paramString)
  {
    paramString = paramString.split("_");
    int[] arrayOfInt = new int[3];
    int i = 0;
    while (i < 3)
    {
      arrayOfInt[i] = Integer.parseInt(paramString[i]);
      i += 1;
    }
    return arrayOfInt;
  }
  
  public static String[] splitPeriod(String paramString, int paramInt)
  {
    return paramString.split(",")[paramInt].split("-");
  }
  
  public static List<String> splitStringToList(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    if (paramString.contains(","))
    {
      paramString = paramString.split(",");
      int i = 0;
      while (i < paramString.length)
      {
        localArrayList.add(paramString[i]);
        i += 1;
      }
    }
    localArrayList.add(paramString);
    return localArrayList;
  }
  
  public static String[] splitTimeZone(String paramString)
  {
    return paramString.split(":");
  }
  
  public static Date stringToDate(String paramString1, String paramString2)
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    if (paramString2.equals("Hm")) {
      localSimpleDateFormat = new SimpleDateFormat("HH:mm");
    }
    try
    {
      paramString1 = localSimpleDateFormat.parse(paramString1);
      return paramString1;
    }
    catch (ParseException paramString1)
    {
      paramString1.printStackTrace();
    }
    return null;
  }
  
  public static Bitmap toRoundBitmap(Bitmap paramBitmap)
  {
    int i = paramBitmap.getWidth();
    int j = paramBitmap.getHeight();
    float f6;
    float f4;
    float f3;
    float f2;
    float f1;
    float f5;
    if (i <= j)
    {
      f6 = i / 2;
      f4 = i;
      f3 = i;
      f2 = i;
      f1 = i;
      j = i;
      f5 = 0.0F;
    }
    for (;;)
    {
      Bitmap localBitmap = Bitmap.createBitmap(j, i, Bitmap.Config.ARGB_8888);
      Canvas localCanvas = new Canvas(localBitmap);
      Paint localPaint = new Paint();
      Rect localRect1 = new Rect((int)f5, (int)0.0F, (int)f4, (int)f3);
      Rect localRect2 = new Rect((int)0.0F, (int)0.0F, (int)f2, (int)f1);
      new RectF(localRect2);
      localPaint.setAntiAlias(true);
      localCanvas.drawARGB(0, 0, 0, 0);
      localPaint.setColor(-12434878);
      localCanvas.drawCircle(f6, f6, f6, localPaint);
      localPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
      localCanvas.drawBitmap(paramBitmap, localRect1, localRect2, localPaint);
      return localBitmap;
      f6 = j / 2;
      f5 = (i - j) / 2;
      f4 = i - f5;
      f3 = j;
      f2 = j;
      f1 = j;
      i = j;
    }
  }
  
  public static String updatePlanTimeZone(String paramString1, String paramString2)
  {
    paramString2 = paramString2.split(",");
    String[] arrayOfString = splitPeriod(paramString1, 0);
    paramString1 = splitPeriod(paramString1, 1);
    arrayOfString[0] = paramString2[0];
    paramString1[0] = paramString2[1];
    return arrayOfString[0] + "-" + arrayOfString[1] + "," + paramString1[0] + "-" + paramString1[1];
  }
}
