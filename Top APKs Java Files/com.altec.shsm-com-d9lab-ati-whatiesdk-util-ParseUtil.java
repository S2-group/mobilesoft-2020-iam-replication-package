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
    PrintStream localPrintStream = System.out;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("dt1/dt2 ");
    localStringBuilder.append(paramString1);
    localStringBuilder.append("/");
    localStringBuilder.append(paramString2);
    localPrintStream.println(localStringBuilder.toString());
    try
    {
      paramString1 = localSimpleDateFormat.parse(paramString1);
      paramString2 = localSimpleDateFormat.parse(paramString2);
      if (paramString1.getTime() > paramString2.getTime()) {
        return true;
      }
      paramString1.getTime();
      paramString2.getTime();
      return false;
    }
    catch (ParseException paramString1)
    {
      paramString1.printStackTrace();
    }
    return false;
  }
  
  public static String dateToString(Date paramDate, String paramString)
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    int i;
    if (paramString.equals("SHORT"))
    {
      i = 3;
      paramString = DateFormat.getDateInstance(i);
    }
    for (;;)
    {
      return paramString.format(paramDate);
      if (paramString.equals("MEDIUM")) {
        return localSimpleDateFormat.format(paramDate);
      }
      if (paramString.equals("MEDIUMTX"))
      {
        paramString = new SimpleDateFormat("yyyy年MM月dd日");
      }
      else
      {
        if (paramString.equals("FULL"))
        {
          i = 0;
          break;
        }
        if (paramString.equals("FULLHm"))
        {
          paramString = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        }
        else if (paramString.equals("FULLHmTx"))
        {
          paramString = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        }
        else if (paramString.equals("FULLHms"))
        {
          paramString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
        else if (paramString.equals("Hm"))
        {
          paramString = new SimpleDateFormat("HH:mm");
        }
        else if (paramString.equals("ms"))
        {
          paramString = new SimpleDateFormat("mm:ss");
        }
        else
        {
          if (!paramString.equals("Hms")) {
            break label224;
          }
          paramString = new SimpleDateFormat("HH:mm:ss");
          paramString.setTimeZone(TimeZone.getTimeZone("Asia/Beijing"));
        }
      }
    }
    label224:
    return null;
  }
  
  public static int dip2px(Context paramContext, int paramInt)
  {
    float f = paramContext.getResources().getDisplayMetrics().density;
    return (int)(paramInt * f + 0.5F);
  }
  
  public static void fixTextSize(TextView paramTextView, String paramString)
  {
    float f;
    if (paramString.length() == 3) {
      f = 12.0F;
    }
    for (;;)
    {
      paramTextView.setTextSize(2, f);
      paramTextView.setText(paramString);
      return;
      if (paramString.length() > 3) {
        f = 10.0F;
      } else {
        f = 14.0F;
      }
    }
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
    case 7: 
      return "周六";
    case 6: 
      return "周五";
    case 5: 
      return "周四";
    case 4: 
      return "周三";
    case 3: 
      return "周二";
    case 2: 
      return "周一";
    }
    return "周日";
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
  
  public static boolean isNotEmpty(String... paramVarArgs)
  {
    int j = paramVarArgs.length;
    int i = 0;
    while (i < j)
    {
      String str = paramVarArgs[i];
      if ((str != null) && (!str.isEmpty())) {
        i += 1;
      } else {
        return false;
      }
    }
    return true;
  }
  
  public static String md5(String paramString)
  {
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      localNoSuchAlgorithmException.printStackTrace();
      arrayOfByte = null;
    }
    arrayOfByte.update(paramString.getBytes());
    paramString = new StringBuffer();
    byte[] arrayOfByte = arrayOfByte.digest();
    int i = 0;
    while (i < arrayOfByte.length)
    {
      int k = arrayOfByte[i];
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
    float f2;
    float f3;
    float f1;
    float f4;
    float f5;
    if (i <= j)
    {
      f2 = i / 2;
      f3 = i;
      f1 = f3;
      f4 = 0.0F;
      f5 = f1;
    }
    else
    {
      f1 = j / 2;
      f4 = (i - j) / 2;
      f2 = i;
      f5 = j;
      f3 = f2 - f4;
      i = j;
      f2 = f1;
    }
    Bitmap localBitmap = Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_8888);
    Canvas localCanvas = new Canvas(localBitmap);
    Paint localPaint = new Paint();
    Rect localRect1 = new Rect((int)f4, (int)0.0F, (int)f3, (int)f5);
    Rect localRect2 = new Rect((int)0.0F, (int)0.0F, (int)f5, (int)f5);
    new RectF(localRect2);
    localPaint.setAntiAlias(true);
    localCanvas.drawARGB(0, 0, 0, 0);
    localPaint.setColor(-12434878);
    localCanvas.drawCircle(f2, f2, f2, localPaint);
    localPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
    localCanvas.drawBitmap(paramBitmap, localRect1, localRect2, localPaint);
    return localBitmap;
  }
  
  public static String updatePlanTimeZone(String paramString1, String paramString2)
  {
    Object localObject = paramString2.split(",");
    paramString2 = splitPeriod(paramString1, 0);
    paramString1 = splitPeriod(paramString1, 1);
    paramString2[0] = localObject[0];
    paramString1[0] = localObject[1];
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append(paramString2[0]);
    ((StringBuilder)localObject).append("-");
    ((StringBuilder)localObject).append(paramString2[1]);
    ((StringBuilder)localObject).append(",");
    ((StringBuilder)localObject).append(paramString1[0]);
    ((StringBuilder)localObject).append("-");
    ((StringBuilder)localObject).append(paramString1[1]);
    return ((StringBuilder)localObject).toString();
  }
}
