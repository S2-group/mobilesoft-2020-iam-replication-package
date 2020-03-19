package com.jwkj.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ClipData;
import android.content.ClipData.Item;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.Rect;
import android.location.LocationManager;
import android.media.MediaScannerConnection;
import android.net.DhcpInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Vibrator;
import android.provider.MediaStore.Images.Media;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.support.v7.widget.RecyclerView.h;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout.LayoutParams;
import com.google.a.e;
import com.google.a.r;
import com.jwkj.data.Contact;
import com.jwkj.data.DataManager;
import com.jwkj.data.DefenceArea;
import com.jwkj.data.SharedPreferencesManager;
import com.jwkj.entity.AlarmMessage;
import com.jwkj.entity.CountryCode.CountryCodeBean;
import com.jwkj.global.AppConfig.Relese;
import com.jwkj.global.CountryCodeList;
import com.jwkj.global.MyApp;
import com.jwkj.global.NpcCommon;
import com.jwkj.widget.NormalDialog;
import com.libhttp.entity.HttpResult;
import com.p2p.core.MediaPlayer;
import com.p2p.core.b;
import com.p2p.core.g.g;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.Key;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class Utils
{
  public static final long GB = 1073741824L;
  public static final long KB = 1024L;
  public static final long MB = 1048576L;
  private static String MID;
  private static String NumberRegex = "(\\d+)";
  private static String STRONG;
  private static String WEAK;
  public static long lastClickTime = 0L;
  private static int stoken;
  
  static
  {
    WEAK = "^[1-9]\\d*$|^[A-Z]+$|^[a-z]+$|^(.)\\1+$";
    MID = "^(?![A-Z]+$)(?![a-z]+$)(?!\\d+$)(?![\\W_]+$)\\S+$";
    STRONG = "^(?=.*[0-9].*)(?=.*[A-Z].*)(?=.*[a-z].*).{6,100}$";
    stoken = 2;
  }
  
  public Utils() {}
  
  public static byte ChangeBitTrue(byte paramByte, int paramInt)
  {
    return (byte)(1 << paramInt | paramByte);
  }
  
  public static byte ChangeByteFalse(byte paramByte, int paramInt)
  {
    return (byte)((0x7FFFFFFF ^ 1 << paramInt) & paramByte);
  }
  
  public static long ConvertLongbyTime(String paramString)
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    try
    {
      long l = localSimpleDateFormat.parse(paramString).getTime();
      return l;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return 0L;
  }
  
  public static long ConvertLongbyTime2(String paramString)
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    try
    {
      long l = localSimpleDateFormat.parse(paramString).getTime();
      return l;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return 0L;
  }
  
  public static String ConvertTimeByLong(long paramLong)
  {
    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(paramLong));
  }
  
  public static String ConvertTimeByString(long paramLong, String paramString)
  {
    if ((paramString == null) || (paramString.equals(""))) {
      return "";
    }
    try
    {
      paramString = new SimpleDateFormat(paramString);
      SimpleTimeZone localSimpleTimeZone = new SimpleTimeZone(0, "UTC");
      Date localDate = new Date(0L);
      paramString.setTimeZone(localSimpleTimeZone);
      localDate.setTime(paramLong);
      paramString = paramString.format(localDate);
      return paramString;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return "";
  }
  
  public static <T extends HttpResult> String GetToastCMDString(T paramT)
  {
    String str = paramT.getError_code();
    int i = -1;
    switch (str.hashCode())
    {
    }
    for (;;)
    {
      switch (i)
      {
      default: 
        return String.valueOf(paramT.getError_code());
        if (str.equals("10001"))
        {
          i = 0;
          continue;
          if (str.equals("10901061"))
          {
            i = 1;
            continue;
            if (str.equals("10000"))
            {
              i = 2;
              continue;
              if (str.equals("10901060")) {
                i = 3;
              }
            }
          }
        }
        break;
      }
    }
    return paramT.getError() + "(" + paramT.getError_code() + ")";
    return getStringByResouceID(2131166152);
  }
  
  public static String GetToastCMDString(String paramString1, String paramString2)
  {
    int i = -1;
    switch (paramString1.hashCode())
    {
    }
    for (;;)
    {
      switch (i)
      {
      default: 
        return String.valueOf(paramString1);
        if (paramString1.equals("10001"))
        {
          i = 0;
          continue;
          if (paramString1.equals("10901061"))
          {
            i = 1;
            continue;
            if (paramString1.equals("10000"))
            {
              i = 2;
              continue;
              if (paramString1.equals("10901060")) {
                i = 3;
              }
            }
          }
        }
        break;
      }
    }
    return paramString2 + "(" + paramString1 + ")";
    return getStringByResouceID(2131166152);
  }
  
  public static byte[] HexString2Bytes(String paramString)
  {
    paramString = paramString.getBytes();
    byte[] arrayOfByte = new byte[paramString.length / 2];
    int i = 0;
    while (i < arrayOfByte.length)
    {
      arrayOfByte[i] = uniteBytes(paramString[(i * 2)], paramString[(i * 2 + 1)]);
      i += 1;
    }
    return arrayOfByte;
  }
  
  public static <T> T JsonToEntity(String paramString, Class<T> paramClass)
  {
    e localE = new e();
    try
    {
      paramString = localE.a(paramString, paramClass);
      return paramString;
    }
    catch (r paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }
  
  public static void PhoneVibrator(Context paramContext)
  {
    ((Vibrator)paramContext.getSystemService("vibrator")).vibrate(200L);
  }
  
  public static String arryToString(byte[] paramArrayOfByte)
  {
    int j = paramArrayOfByte.length;
    int i = 0;
    while (i < paramArrayOfByte.length)
    {
      if (paramArrayOfByte[i] == 0) {
        j = i;
      }
      if (i > j) {
        paramArrayOfByte[i] = 0;
      }
      i += 1;
    }
    return new String(paramArrayOfByte).trim();
  }
  
  public static byte[] bmpToByteArray(Bitmap paramBitmap, boolean paramBoolean)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    paramBitmap.compress(Bitmap.CompressFormat.JPEG, 100, localByteArrayOutputStream);
    if (paramBoolean) {
      paramBitmap.recycle();
    }
    paramBitmap = localByteArrayOutputStream.toByteArray();
    try
    {
      localByteArrayOutputStream.close();
      return paramBitmap;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return paramBitmap;
  }
  
  public static String bytesToHexString(byte[] paramArrayOfByte)
  {
    StringBuilder localStringBuilder = new StringBuilder("");
    localStringBuilder.append("[");
    if ((paramArrayOfByte == null) || (paramArrayOfByte.length <= 0)) {
      return null;
    }
    int i = 0;
    while (i < paramArrayOfByte.length)
    {
      String str = Integer.toHexString(paramArrayOfByte[i] & 0xFF);
      if (str.length() < 2) {
        localStringBuilder.append(0);
      }
      localStringBuilder.append(str);
      if (i != paramArrayOfByte.length - 1) {
        localStringBuilder.append(", ");
      }
      i += 1;
    }
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
  
  public static int bytesToInt(byte[] paramArrayOfByte, int paramInt)
  {
    return paramArrayOfByte[paramInt] & 0xFF | (paramArrayOfByte[(paramInt + 1)] & 0xFF) << 8 | (paramArrayOfByte[(paramInt + 2)] & 0xFF) << 16 | (paramArrayOfByte[(paramInt + 3)] & 0xFF) << 24;
  }
  
  public static Bitmap changeImageView(String paramString, Bitmap paramBitmap)
  {
    int i = paramBitmap.getWidth();
    int j = paramBitmap.getHeight();
    Bitmap localBitmap = Bitmap.createBitmap(i, j, Bitmap.Config.ARGB_8888);
    Canvas localCanvas = new Canvas(localBitmap);
    Object localObject = new Paint();
    ((Paint)localObject).setDither(true);
    ((Paint)localObject).setFilterBitmap(true);
    Rect localRect2 = new Rect(0, 0, paramBitmap.getWidth(), paramBitmap.getHeight());
    Rect localRect1 = new Rect(0, 0, i, j);
    localCanvas.drawBitmap(paramBitmap, localRect2, localRect1, (Paint)localObject);
    paramBitmap = new Paint(257);
    paramBitmap.setTextSize(90.0F);
    paramBitmap.setColor(-1);
    localObject = paramBitmap.getFontMetricsInt();
    i = localRect1.top + (localRect1.bottom - localRect1.top - ((Paint.FontMetricsInt)localObject).bottom + ((Paint.FontMetricsInt)localObject).top) / 2 - ((Paint.FontMetricsInt)localObject).top;
    paramBitmap.setTextAlign(Paint.Align.CENTER);
    paramBitmap.setShadowLayer(30.0F, 0.0F, 0.0F, -16777216);
    localCanvas.drawText(paramString, localRect1.centerX() - stoken, i, paramBitmap);
    localCanvas.drawText(paramString, localRect1.centerX() + stoken, i, paramBitmap);
    localCanvas.drawText(paramString, localRect1.centerX(), i - stoken, paramBitmap);
    localCanvas.drawText(paramString, localRect1.centerX(), stoken + i, paramBitmap);
    localCanvas.drawText(paramString, localRect1.centerX() + stoken, stoken + i, paramBitmap);
    localCanvas.drawText(paramString, localRect1.centerX() - stoken, i - stoken, paramBitmap);
    localCanvas.drawText(paramString, localRect1.centerX() + stoken, i - stoken, paramBitmap);
    localCanvas.drawText(paramString, localRect1.centerX() - stoken, stoken + i, paramBitmap);
    paramBitmap.setColor(-65536);
    localCanvas.drawText(paramString, localRect1.centerX(), i, paramBitmap);
    localCanvas.save(31);
    localCanvas.restore();
    return localBitmap;
  }
  
  public static boolean checkPassword(String paramString)
  {
    boolean bool = false;
    if ((paramString.length() >= 10) || (!isNumeric(paramString)) || (paramString.charAt(0) != '0')) {
      bool = true;
    }
    return bool;
  }
  
  public static void clearClipboard()
  {
    ((ClipboardManager)MyApp.app.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("", ""));
  }
  
  public static boolean comparedDateIsMore(long paramLong, int paramInt)
  {
    return (paramLong <= 0L) || (System.currentTimeMillis() - paramLong >= 86400000 * paramInt);
  }
  
  public static String convertDeviceTime(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramInt1 + 2000 + "-");
    if (paramInt2 < 10)
    {
      localStringBuilder.append("0" + paramInt2 + "-");
      if (paramInt3 >= 10) {
        break label222;
      }
      localStringBuilder.append("0" + paramInt3 + " ");
      label115:
      if (paramInt4 >= 10) {
        break label251;
      }
      localStringBuilder.append("0" + paramInt4 + ":");
      label153:
      if (paramInt5 >= 10) {
        break label280;
      }
      localStringBuilder.append("0" + paramInt5);
    }
    for (;;)
    {
      return localStringBuilder.toString();
      localStringBuilder.append(paramInt2 + "-");
      break;
      label222:
      localStringBuilder.append(paramInt3 + " ");
      break label115;
      label251:
      localStringBuilder.append(paramInt4 + ":");
      break label153;
      label280:
      localStringBuilder.append("" + paramInt5);
    }
  }
  
  public static String convertPlanTime(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if (paramInt1 < 10)
    {
      localStringBuilder.append("0" + paramInt1 + ":");
      if (paramInt2 >= 10) {
        break label190;
      }
      localStringBuilder.append("0" + paramInt2 + "-");
      label85:
      if (paramInt3 >= 10) {
        break label219;
      }
      localStringBuilder.append("0" + paramInt3 + ":");
      label123:
      if (paramInt4 >= 10) {
        break label248;
      }
      localStringBuilder.append("0" + paramInt4);
    }
    for (;;)
    {
      return localStringBuilder.toString();
      localStringBuilder.append(paramInt1 + ":");
      break;
      label190:
      localStringBuilder.append(paramInt2 + "-");
      break label85;
      label219:
      localStringBuilder.append(paramInt3 + ":");
      break label123;
      label248:
      localStringBuilder.append("" + paramInt4);
    }
  }
  
  public static int copyfile(File paramFile1, File paramFile2, boolean paramBoolean1, boolean paramBoolean2)
  {
    int i = 0;
    int k = 0;
    int j;
    if (!paramFile1.exists()) {
      j = k;
    }
    label173:
    for (;;)
    {
      return j;
      j = k;
      if (paramFile1.isFile())
      {
        j = k;
        if (paramFile1.canRead())
        {
          if (!paramFile2.getParentFile().exists()) {
            paramFile2.getParentFile().mkdirs();
          }
          if ((paramFile2.exists()) && (paramBoolean1)) {
            paramFile2.delete();
          }
          FileInputStream localFileInputStream;
          try
          {
            localFileInputStream = new FileInputStream(paramFile1);
            paramFile2 = new FileOutputStream(paramFile2);
            byte[] arrayOfByte = new byte['Ѐ'];
            for (;;)
            {
              j = localFileInputStream.read(arrayOfByte);
              if (j <= 0) {
                break;
              }
              paramFile2.write(arrayOfByte, 0, j);
            }
            j = i;
          }
          catch (IOException paramFile2)
          {
            paramFile2.printStackTrace();
          }
          for (;;)
          {
            if (!paramBoolean2) {
              break label173;
            }
            j = i;
            if (!paramFile1.exists()) {
              break;
            }
            paramFile1.delete();
            return i;
            localFileInputStream.close();
            paramFile2.close();
            i = 1;
          }
        }
      }
    }
  }
  
  public static View createStatusView(Activity paramActivity, int paramInt)
  {
    int i = getStatusHeigh();
    paramActivity = new View(paramActivity);
    paramActivity.setLayoutParams(new LinearLayout.LayoutParams(-1, i));
    paramActivity.setBackgroundResource(paramInt);
    return paramActivity;
  }
  
  public static String decrypt(byte[] paramArrayOfByte, String paramString)
    throws Exception
  {
    SecureRandom localSecureRandom = new SecureRandom();
    paramString = new DESKeySpec(paramString.getBytes());
    paramString = SecretKeyFactory.getInstance("DES").generateSecret(paramString);
    Cipher localCipher = Cipher.getInstance("DES");
    localCipher.init(2, paramString, localSecureRandom);
    return new String(localCipher.doFinal(paramArrayOfByte));
  }
  
  public static byte[] decrypt(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
    throws Exception
  {
    paramArrayOfByte2 = new DESKeySpec(paramArrayOfByte2);
    paramArrayOfByte2 = SecretKeyFactory.getInstance("DES").generateSecret(paramArrayOfByte2);
    Cipher localCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
    localCipher.init(2, paramArrayOfByte2);
    return localCipher.doFinal(paramArrayOfByte1);
  }
  
  public static void deleteFile(File paramFile)
  {
    if (paramFile.isFile()) {
      paramFile.delete();
    }
    while (!paramFile.isDirectory()) {
      return;
    }
    File[] arrayOfFile = paramFile.listFiles();
    if ((arrayOfFile == null) || (arrayOfFile.length == 0))
    {
      paramFile.delete();
      return;
    }
    int j = arrayOfFile.length;
    int i = 0;
    while (i < j)
    {
      deleteFile(arrayOfFile[i]);
      i += 1;
    }
    paramFile.delete();
  }
  
  public static byte[] desCrypto(byte[] paramArrayOfByte, String paramString)
  {
    try
    {
      SecureRandom localSecureRandom = new SecureRandom();
      paramString = new DESKeySpec(paramString.getBytes());
      paramString = SecretKeyFactory.getInstance("DES").generateSecret(paramString);
      Cipher localCipher = Cipher.getInstance("DES");
      localCipher.init(1, paramString, localSecureRandom);
      paramArrayOfByte = localCipher.doFinal(paramArrayOfByte);
      return paramArrayOfByte;
    }
    catch (Throwable paramArrayOfByte)
    {
      paramArrayOfByte.printStackTrace();
    }
    return null;
  }
  
  public static byte[] desDecrypt(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
    throws Exception
  {
    paramArrayOfByte2 = toKey(paramArrayOfByte2);
    Cipher localCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
    localCipher.init(2, paramArrayOfByte2);
    return localCipher.doFinal(paramArrayOfByte1);
  }
  
  public static int dip2px(Context paramContext, float paramFloat)
  {
    return (int)(paramContext.getResources().getDisplayMetrics().density * paramFloat + 0.5F);
  }
  
  public static int dip2px(Context paramContext, int paramInt)
  {
    return (int)(paramContext.getResources().getDisplayMetrics().density * paramInt + 0.5F);
  }
  
  public static String displayFileSize(long paramLong)
  {
    if (paramLong >= 1073741824L) {
      return String.format("%.1f GB", new Object[] { Float.valueOf((float)paramLong / 1.07374182E9F) });
    }
    float f;
    String str;
    if (paramLong >= 1048576L)
    {
      f = (float)paramLong / 1048576.0F;
      if (f > 100.0F) {}
      for (str = "%.0f MB";; str = "%.1f MB") {
        return String.format(str, new Object[] { Float.valueOf(f) });
      }
    }
    if (paramLong >= 1024L)
    {
      f = (float)paramLong / 1024.0F;
      if (f > 100.0F) {}
      for (str = "%.0f KB";; str = "%.1f KB") {
        return String.format(str, new Object[] { Float.valueOf(f) });
      }
    }
    return String.format("%d B", new Object[] { Long.valueOf(paramLong) });
  }
  
  public static byte[] encrypt(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
    throws Exception
  {
    Cipher localCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
    DESKeySpec localDESKeySpec = new DESKeySpec(paramArrayOfByte2);
    localCipher.init(1, SecretKeyFactory.getInstance("DES").generateSecret(localDESKeySpec), new IvParameterSpec(paramArrayOfByte2));
    return localCipher.doFinal(paramArrayOfByte1);
  }
  
  public static byte[] gainWifiMode()
  {
    byte[] arrayOfByte = new byte[20];
    int i = 0;
    while (i < arrayOfByte.length)
    {
      arrayOfByte[i] = 0;
      i += 1;
    }
    return arrayOfByte;
  }
  
  public static String getAPDeviceIp(Context paramContext)
  {
    try
    {
      paramContext = intToInetAddress(((WifiManager)MyApp.app.getSystemService("wifi")).getDhcpInfo().serverAddress).getHostAddress();
      return paramContext;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return "192.168.1.1";
  }
  
  public static String getAlarmMessage(AlarmMessage paramAlarmMessage)
  {
    String str = getAlarmType(paramAlarmMessage.getAlarmId(), paramAlarmMessage.getAlarmType(), paramAlarmMessage.isSupport(), paramAlarmMessage.getGroup(), paramAlarmMessage.getItem(), "");
    paramAlarmMessage = new StringBuffer(getStringByResouceID(2131166155)).append("：").append(getDeviceName(paramAlarmMessage.getAlarmId()));
    paramAlarmMessage.append("\n").append(getStringByResouceID(2131165293)).append(str);
    return paramAlarmMessage.toString();
  }
  
  public static String getAlarmType(String paramString1, int paramInt1, boolean paramBoolean, int paramInt2, int paramInt3, String paramString2)
  {
    StringBuffer localStringBuffer;
    DefenceArea localDefenceArea;
    switch (paramInt1)
    {
    default: 
      paramString2 = getStringByResouceID(2131165863);
    case 999: 
      return paramString2;
    case 1: 
      localStringBuffer = new StringBuffer();
      if (paramBoolean)
      {
        if (paramInt2 >= 1) {
          break label294;
        }
        paramString2 = MyApp.app.getResources().getString(2131166011);
        localDefenceArea = new DefenceArea();
        localDefenceArea.setGroup(paramInt2);
        localDefenceArea.setItem(paramInt3);
        paramString1 = DataManager.findDefenceAreaByDeviceID(MyApp.app, paramString1, localDefenceArea);
        if (paramString1 != null) {
          break label311;
        }
      }
      for (paramString1 = paramString2 + ":" + MyApp.app.getResources().getString(2131165316) + ((paramInt2 - 1) * 8 + (paramInt3 + 1));; paramString1 = paramString2 + ":" + paramString1.getName())
      {
        localStringBuffer.append(getStringByResouceID(2131165294)).append("   ");
        localStringBuffer.append("\n");
        localStringBuffer.append(paramString1);
        return localStringBuffer.toString();
        paramString2 = MyApp.app.getResources().getString(2131166074);
        break;
      }
    case 2: 
      return getStringByResouceID(2131165295);
    case 3: 
      return getStringByResouceID(2131165296);
    case 6: 
      localStringBuffer = new StringBuffer();
      if (paramBoolean)
      {
        localStringBuffer.append(getStringByResouceID(2131165732)).append("   ");
        localStringBuffer.append("\n");
        if (paramInt2 >= 1) {
          break label516;
        }
        paramString2 = MyApp.app.getResources().getString(2131166011);
        localDefenceArea = new DefenceArea();
        localDefenceArea.setGroup(paramInt2);
        localDefenceArea.setItem(paramInt3);
        paramString1 = DataManager.findDefenceAreaByDeviceID(MyApp.app, paramString1, localDefenceArea);
        if (paramString1 != null) {
          break label533;
        }
      }
      for (paramString1 = paramString2 + ":" + MyApp.app.getResources().getString(2131165316) + ((paramInt2 - 1) * 8 + (paramInt3 + 1));; paramString1 = paramString2 + ":" + paramString1.getName())
      {
        localStringBuffer.append(paramString1);
        return localStringBuffer.toString();
        paramString2 = MyApp.app.getResources().getString(2131166074);
        break;
      }
    case 7: 
      return getStringByResouceID(2131165297);
    case 5: 
      return getStringByResouceID(2131165298);
    case 8: 
      return getStringByResouceID(2131165480);
    case 9: 
      return getStringByResouceID(2131165829);
    case 10: 
      return getStringByResouceID(2131165327);
    case 13: 
    case 54: 
      return getStringByResouceID(2131165612);
    case 15: 
      label294:
      label311:
      label516:
      label533:
      return getStringByResouceID(2131165996);
    }
    return getStringByResouceID(2131165523);
  }
  
  public static String getBitProcessingVersion(String paramString)
  {
    try
    {
      paramString = paramString.split("\\.");
      int i = Integer.parseInt(paramString[0]);
      int j = Integer.parseInt(paramString[1]);
      int k = Integer.parseInt(paramString[2]);
      int m = Integer.parseInt(paramString[3]);
      return String.valueOf(m | i << 24 | j << 16 | k << 8);
    }
    catch (Exception paramString) {}
    return "0";
  }
  
  public static int[] getByteBinnery(byte paramByte, boolean paramBoolean)
  {
    byte b2 = 7;
    byte b1 = 0;
    byte b3 = 0;
    int[] arrayOfInt = new int[8];
    if (paramBoolean)
    {
      b1 = 0;
      b2 = b3;
      while (b1 <= 7)
      {
        arrayOfInt[b2] = ((byte)(paramByte >> b1 & 0x1));
        b2 += 1;
        b1 += 1;
      }
    }
    while (b2 >= 0)
    {
      arrayOfInt[b1] = ((byte)(paramByte >> b2 & 0x1));
      b1 += 1;
      b2 -= 1;
    }
    return arrayOfInt;
  }
  
  public static String getCapturePath(String paramString)
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
    Date localDate = Calendar.getInstance(Locale.getDefault()).getTime();
    return paramString + "_" + localSimpleDateFormat.format(localDate) + ".jpg";
  }
  
  public static String getClipboardText()
  {
    Object localObject = (ClipboardManager)MyApp.app.getSystemService("clipboard");
    try
    {
      localObject = ((ClipboardManager)localObject).getPrimaryClip().getItemAt(0).getText().toString();
      return localObject;
    }
    catch (NullPointerException localNullPointerException) {}
    return "";
  }
  
  public static int getColorByResouce(int paramInt)
  {
    return MyApp.app.getResources().getColor(paramInt);
  }
  
  public static int getColorByResouse(int paramInt)
  {
    return MyApp.app.getResources().getColor(paramInt);
  }
  
  public static String getCurDateTimeStr()
  {
    return new SimpleDateFormat("yyyy-MM-dd HH_mm_ss").format(Long.valueOf(System.currentTimeMillis()));
  }
  
  public static CountryCode.CountryCodeBean getCurrentCountryCodeInfo()
  {
    Object localObject = MyApp.app.getResources().getConfiguration().locale.getCountry();
    if (!TextUtils.isEmpty((CharSequence)localObject))
    {
      if ((((String)localObject).equals("BQ")) || (((String)localObject).equals("CW")) || (((String)localObject).equals("SX"))) {
        localObject = CountryCodeList.getInstance().getCountryCodeInfo("NL");
      }
      CountryCode.CountryCodeBean localCountryCodeBean;
      do
      {
        return localObject;
        if (((String)localObject).equals("BV")) {
          return CountryCodeList.getInstance().getCountryCodeInfo("NO");
        }
        if (((String)localObject).equals("HM")) {
          return CountryCodeList.getInstance().getCountryCodeInfo("AU");
        }
        if (((String)localObject).equals("IO")) {
          return CountryCodeList.getInstance().getCountryCodeInfo("GB");
        }
        if (((String)localObject).equals("TF")) {
          return CountryCodeList.getInstance().getCountryCodeInfo("FR");
        }
        localCountryCodeBean = CountryCodeList.getInstance().getCountryCodeInfo((String)localObject);
        localObject = localCountryCodeBean;
      } while (localCountryCodeBean != null);
      return CountryCodeList.getInstance().getCountryCodeInfo("other");
    }
    return CountryCodeList.getInstance().getCountryCodeInfo("other");
  }
  
  public static String getDefenceAreaByGroup(Context paramContext, int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return "";
    case 0: 
      return paramContext.getResources().getString(2131166011);
    case 1: 
      return paramContext.getResources().getString(2131165615);
    case 2: 
      return paramContext.getResources().getString(2131166300);
    case 3: 
      return paramContext.getResources().getString(2131165325);
    case 4: 
      return paramContext.getResources().getString(2131165328);
    case 5: 
      return paramContext.getResources().getString(2131165702);
    case 6: 
      return paramContext.getResources().getString(2131165462);
    case 7: 
      return paramContext.getResources().getString(2131165525);
    }
    return paramContext.getResources().getString(2131165894);
  }
  
  public static String[] getDeleteAlarmIdArray(String[] paramArrayOfString, int paramInt)
  {
    int i = 0;
    if (paramArrayOfString.length == 1) {
      return new String[] { "0" };
    }
    String[] arrayOfString = new String[paramArrayOfString.length - 1];
    int k;
    for (int j = 0; i < paramArrayOfString.length; j = k)
    {
      k = j;
      if (paramInt != i)
      {
        arrayOfString[j] = paramArrayOfString[i];
        k = j + 1;
      }
      i += 1;
    }
    return arrayOfString;
  }
  
  public static String getDeviceName(String paramString)
  {
    Contact localContact = DataManager.findContactByActiveUserAndContactId(MyApp.app, NpcCommon.mThreeNum, paramString);
    if (localContact != null) {
      paramString = localContact.contactName;
    }
    return paramString;
  }
  
  public static int[] getDrawableWAndrH(int paramInt)
  {
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    localOptions.inJustDecodeBounds = true;
    BitmapFactory.decodeResource(MyApp.app.getResources(), paramInt, localOptions);
    int[] arrayOfInt = new int[2];
    arrayOfInt[0] = localOptions.outWidth;
    arrayOfInt[1] = localOptions.outHeight;
    Log.e("dxsTest", "w---h" + Arrays.toString(arrayOfInt));
    return arrayOfInt;
  }
  
  public static int[] getDrawableWAndrH(String paramString)
  {
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    localOptions.inJustDecodeBounds = true;
    BitmapFactory.decodeFile(paramString, localOptions);
    paramString = new int[2];
    paramString[0] = localOptions.outWidth;
    paramString[1] = localOptions.outHeight;
    Log.e("dxsTest", "w---h" + Arrays.toString(paramString));
    return paramString;
  }
  
  public static String getEmaiStringlimit24(String paramString)
  {
    String str1 = paramString;
    String str2;
    if (paramString.length() > 24) {
      try
      {
        int i = paramString.lastIndexOf("@");
        if (i > 0)
        {
          str1 = paramString.substring(i, paramString.length());
          String str3 = paramString.substring(0, i);
          i = str1.length();
          return str3.substring(0, 24 - i - 3) + "..." + str1;
        }
        str1 = paramString.substring(0, 24);
        return str1;
      }
      catch (StringIndexOutOfBoundsException localStringIndexOutOfBoundsException)
      {
        localStringIndexOutOfBoundsException.printStackTrace();
        str2 = paramString.substring(0, 24);
      }
    }
    return str2;
  }
  
  public static String getErrorWithCode(int paramInt1, int paramInt2)
  {
    return getErrorWithCode(paramInt1, String.valueOf(paramInt2));
  }
  
  public static String getErrorWithCode(int paramInt, String paramString)
  {
    try
    {
      String str = MyApp.app.getResources().getString(paramInt) + "(" + paramString + ")";
      return str;
    }
    catch (Resources.NotFoundException localNotFoundException)
    {
      localNotFoundException.printStackTrace();
    }
    return paramString;
  }
  
  public static String getFormatTellDate(Context paramContext, String paramString)
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy" + "-" + "MM" + "-" + "dd" + "-" + " HH:mm");
    try
    {
      paramContext = new Date(Long.parseLong(paramString));
      if (paramContext != null) {
        return localSimpleDateFormat.format(paramContext);
      }
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        paramContext = null;
      }
    }
    return "";
  }
  
  public static int getGapCountDay(String paramString1, String paramString2)
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Calendar localCalendar = null;
    for (;;)
    {
      try
      {
        paramString1 = localSimpleDateFormat.parse(paramString1.substring(0, paramString1.lastIndexOf(" ")));
        paramString2.printStackTrace();
      }
      catch (ParseException paramString2)
      {
        try
        {
          paramString2 = localSimpleDateFormat.parse(paramString2.substring(0, paramString2.lastIndexOf(" ")));
          localCalendar = Calendar.getInstance();
          localCalendar.setTime(paramString1);
          localCalendar.set(11, 0);
          localCalendar.set(12, 0);
          localCalendar.set(13, 0);
          localCalendar.set(14, 0);
          paramString1 = Calendar.getInstance();
          paramString1.setTime(paramString2);
          paramString1.set(11, 0);
          paramString1.set(12, 0);
          paramString1.set(13, 0);
          paramString1.set(14, 0);
          return (int)((paramString1.getTime().getTime() - localCalendar.getTime().getTime()) / 86400000L);
        }
        catch (ParseException paramString2)
        {
          for (;;) {}
        }
        paramString2 = paramString2;
        paramString1 = null;
      }
      paramString2 = localCalendar;
    }
  }
  
  public static HashMap getHash(String paramString)
  {
    int i = 0;
    try
    {
      HashMap localHashMap = new HashMap();
      String[] arrayOfString = paramString.split(",");
      int j = arrayOfString.length;
      for (;;)
      {
        paramString = localHashMap;
        if (i >= j) {
          break;
        }
        paramString = arrayOfString[i].split(":");
        localHashMap.put("" + paramString[0], paramString[1]);
        i += 1;
      }
      return paramString;
    }
    catch (Exception paramString)
    {
      paramString = null;
    }
  }
  
  public static File[] getHeaderImage(String paramString)
  {
    paramString = MyApp.app.getExternalFilesDir(null).listFiles(new FileFilter()
    {
      public boolean accept(File paramAnonymousFile)
      {
        return paramAnonymousFile.getName().startsWith(this.val$id);
      }
    });
    Arrays.sort(paramString, new FileUp());
    return paramString;
  }
  
  public static String getIntentAddress(Context paramContext, String paramString)
    throws IOException
  {
    paramContext = (WifiManager)MyApp.app.getSystemService("wifi");
    if (!paramContext.isWifiEnabled()) {
      paramContext.setWifiEnabled(true);
    }
    paramContext = intToIp(paramContext.getConnectionInfo().getIpAddress());
    return paramContext.substring(0, paramContext.lastIndexOf(".") + 1) + paramString;
  }
  
  public static InetAddress getIntentAddress(Context paramContext)
    throws IOException
  {
    paramContext = ((WifiManager)MyApp.app.getSystemService("wifi")).getDhcpInfo();
    int j = paramContext.ipAddress;
    int k = paramContext.netmask;
    int m = paramContext.netmask;
    paramContext = new byte[4];
    int i = 0;
    while (i < 4)
    {
      paramContext[i] = ((byte)((j & k | m ^ 0xFFFFFFFF) >> i * 8 & 0xFF));
      i += 1;
    }
    return InetAddress.getByAddress(paramContext);
  }
  
  public static int getNextItem(int[] paramArrayOfInt)
  {
    int i = 0;
    int j = 0;
    if (paramArrayOfInt.length == 0) {
      return j;
    }
    Arrays.sort(paramArrayOfInt);
    for (;;)
    {
      if (i >= paramArrayOfInt.length) {
        break label38;
      }
      j = i;
      if (Arrays.binarySearch(paramArrayOfInt, i) < 0) {
        break;
      }
      i += 1;
    }
    label38:
    return -1;
  }
  
  public static String getNumberFromeString(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return "";
    }
    paramString = Pattern.compile(NumberRegex).matcher(paramString);
    if (paramString.find()) {
      return paramString.group(1);
    }
    return "";
  }
  
  public static int getPassWordStatus(String paramString)
  {
    if (paramString.length() == 0) {
      return 0;
    }
    if ((paramString.length() < 6) || (isRuo(paramString))) {
      return 1;
    }
    if (paramString.matches(MID))
    {
      if (paramString.matches(STRONG)) {
        return 3;
      }
      return 2;
    }
    return -1;
  }
  
  public static int getPassWordStatus(String paramString, int paramInt)
  {
    if (paramString.length() == 0) {
      return 0;
    }
    if ((paramString.length() < paramInt) || (isRuo(paramString))) {
      return 1;
    }
    if (paramString.matches(MID))
    {
      if (paramString.matches(STRONG)) {
        return 3;
      }
      return 2;
    }
    return -1;
  }
  
  public static long getPermissionByIndex(long paramLong, int paramInt1, int paramInt2)
  {
    long l = Math.pow(2.0D, paramInt1);
    if (paramInt2 == 1) {
      return paramInt2 << paramInt1 | paramLong;
    }
    return Long.MAX_VALUE - l & paramLong;
  }
  
  public static String getPhoneIpdress()
  {
    WifiManager localWifiManager = (WifiManager)MyApp.app.getSystemService("wifi");
    if (!localWifiManager.isWifiEnabled()) {
      localWifiManager.setWifiEnabled(true);
    }
    return intToIp(localWifiManager.getConnectionInfo().getIpAddress());
  }
  
  public static int getPointFromPointPath(String paramString)
  {
    if ((paramString != null) && (paramString.length() >= 5)) {
      try
      {
        int i = Integer.parseInt(String.valueOf(paramString.charAt(paramString.lastIndexOf(".") - 1)));
        return i;
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
      }
    }
    return 0;
  }
  
  public static String getPrepointPath(String paramString, int paramInt)
  {
    return AppConfig.Relese.PREPOINTPATH + File.separator + paramString + "_" + paramInt + ".jpg";
  }
  
  public static String getPrivacyPolicyUrl()
  {
    try
    {
      String str = g.j(MyApp.app);
      return str;
    }
    catch (Exception localException) {}
    return "http://upg1.cloudlinks.cn/upg/common/clauses/privacy/";
  }
  
  public static String getRecentName(Context paramContext)
  {
    Object localObject2 = SharedPreferencesManager.getInstance().getData(paramContext, "gwell", "recentName_emailorphone");
    Object localObject1;
    if (localObject2 != null)
    {
      localObject1 = localObject2;
      if (!"".equals(localObject2)) {}
    }
    else
    {
      localObject1 = SharedPreferencesManager.getInstance().getData(paramContext, "gwell", "recentName");
    }
    if (localObject1 != null)
    {
      localObject2 = localObject1;
      if (!"".equals(localObject1)) {}
    }
    else
    {
      localObject2 = SharedPreferencesManager.getInstance().getData(paramContext, "gwell", "recentName_email");
    }
    return localObject2;
  }
  
  public static String getRecentPwd(Context paramContext)
  {
    Object localObject2 = SharedPreferencesManager.getInstance().getData(paramContext, "gwell", "recentPass_emailorphone");
    Object localObject1;
    if (localObject2 != null)
    {
      localObject1 = localObject2;
      if (!"".equals(localObject2)) {}
    }
    else
    {
      localObject1 = SharedPreferencesManager.getInstance().getData(paramContext, "gwell", "recentPass");
    }
    if (localObject1 != null)
    {
      localObject2 = localObject1;
      if (!"".equals(localObject1)) {}
    }
    else
    {
      localObject2 = SharedPreferencesManager.getInstance().getData(paramContext, "gwell", "recentPass_email");
    }
    return localObject2;
  }
  
  private static int getRecyclerViewItemTopInset(RecyclerView.LayoutParams paramLayoutParams)
  {
    try
    {
      Field localField = RecyclerView.LayoutParams.class.getDeclaredField("d");
      localField.setAccessible(true);
      int i = ((Rect)localField.get(paramLayoutParams)).top;
      return i;
    }
    catch (Exception paramLayoutParams)
    {
      paramLayoutParams.printStackTrace();
    }
    return 0;
  }
  
  public static List<String> getScreenShotImagePath(String paramString, int paramInt)
  {
    int i = 0;
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    paramString = new File(AppConfig.Relese.SCREENSHORT).listFiles(new FileFilter()
    {
      public boolean accept(File paramAnonymousFile)
      {
        if ((this.val$callId == null) || ("".equals(this.val$callId))) {
          return paramAnonymousFile.getName().endsWith(".jpg");
        }
        return paramAnonymousFile.getName().startsWith(this.val$callId);
      }
    });
    if (paramString != null)
    {
      int j = paramString.length;
      while (i < j)
      {
        FileInfo localFileInfo = new FileInfo();
        Object localObject = paramString[i];
        localFileInfo.path = localObject.getPath();
        localFileInfo.LastModified = localObject.lastModified();
        localArrayList2.add(localFileInfo);
        i += 1;
      }
      if (paramInt == 1) {
        Collections.sort(localArrayList2, new FileComparatorUp());
      }
      for (;;)
      {
        paramString = localArrayList2.iterator();
        while (paramString.hasNext()) {
          localArrayList1.add(((FileInfo)paramString.next()).path);
        }
        Collections.sort(localArrayList2, new FileComparatorDown());
      }
    }
    return localArrayList1;
  }
  
  public static String getScreenShotPath(Contact paramContact, int paramInt)
  {
    if (paramContact == null) {
      return "";
    }
    if (paramInt == 1)
    {
      localStringBuilder = new StringBuilder(AppConfig.Relese.SCREENSHOT_HEAD);
      localStringBuilder.append(File.separator);
      localStringBuilder.append(NpcCommon.mThreeNum);
      localStringBuilder.append(File.separator);
      localStringBuilder.append(paramContact.getRealContactID());
      localStringBuilder.append(".jpg");
      paramContact = new File(localStringBuilder.toString());
      if (!paramContact.getParentFile().exists()) {
        paramContact.getParentFile().mkdirs();
      }
      return localStringBuilder.toString();
    }
    StringBuilder localStringBuilder = new StringBuilder(AppConfig.Relese.SCREENSHORT);
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault());
    localStringBuilder.append(File.separator);
    localStringBuilder.append(paramContact.getVideoInfo());
    localStringBuilder.append("_");
    localStringBuilder.append(localSimpleDateFormat.format(new Date(System.currentTimeMillis())));
    localStringBuilder.append(".jpg");
    paramContact = new File(localStringBuilder.toString());
    if (!paramContact.getParentFile().exists()) {
      paramContact.getParentFile().mkdirs();
    }
    return localStringBuilder.toString();
  }
  
  public static int getShapeType(Contact paramContact, int paramInt)
  {
    int i;
    if (paramContact.is360Panorama()) {
      if (paramInt == 0) {
        i = 5;
      }
    }
    do
    {
      do
      {
        return i;
        if (paramInt == 1) {
          return 3;
        }
        if (paramInt == 2) {
          return 2;
        }
        if (paramInt == 3) {
          return 0;
        }
        i = paramInt;
      } while (paramInt != 4);
      return 0;
      if (paramInt == 0) {
        return 1;
      }
      i = paramInt;
    } while (paramInt != 1);
    return 2;
  }
  
  public static int getStatusHeigh()
  {
    int i = MyApp.app.getResources().getIdentifier("status_bar_height", "dimen", "android");
    return MyApp.app.getResources().getDimensionPixelSize(i);
  }
  
  public static int getStoreIDType(long paramLong1, long paramLong2)
  {
    if ((paramLong2 > paramLong1) || (paramLong1 - paramLong2 > 32767L)) {
      return 2;
    }
    return 0;
  }
  
  public static String getStringAddThreePoint(int paramInt)
  {
    try
    {
      String str = MyApp.app.getResources().getString(paramInt) + "...";
      return str;
    }
    catch (Resources.NotFoundException localNotFoundException)
    {
      localNotFoundException.printStackTrace();
    }
    return "";
  }
  
  public static String getStringByByte(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if (paramInt1 + paramInt2 > paramArrayOfByte.length) {
      return "";
    }
    byte[] arrayOfByte = new byte[paramInt2];
    System.arraycopy(paramArrayOfByte, paramInt1, arrayOfByte, 0, paramInt2);
    return new String(arrayOfByte).trim();
  }
  
  public static String getStringByResouceID(int paramInt)
  {
    return MyApp.app.getString(paramInt);
  }
  
  public static String getStringForId(int paramInt)
  {
    return MyApp.app.getResources().getString(paramInt);
  }
  
  public static String getStringFourAsterisk(String paramString)
  {
    if (paramString.length() <= 4) {
      return paramString;
    }
    StringBuffer localStringBuffer = new StringBuffer(paramString);
    localStringBuffer.replace((paramString.length() - 4) / 2, (paramString.length() - 4) / 2 + 4, "****");
    return localStringBuffer.toString();
  }
  
  public static SpannableStringBuilder getThreeColorSizeStyleString(String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
  {
    SpannableStringBuilder localSpannableStringBuilder = new SpannableStringBuilder(paramString1 + paramString2 + paramString3);
    localSpannableStringBuilder.setSpan(new ForegroundColorSpan(paramInt1), 0, paramString1.length(), 33);
    localSpannableStringBuilder.setSpan(new ForegroundColorSpan(paramInt2), paramString1.length(), paramString1.length() + paramString2.length(), 33);
    localSpannableStringBuilder.setSpan(new ForegroundColorSpan(paramInt3), paramString1.length() + paramString2.length(), paramString1.length() + paramString2.length() + paramString3.length(), 33);
    localSpannableStringBuilder.setSpan(new AbsoluteSizeSpan(paramInt4), 0, paramString1.length(), 33);
    localSpannableStringBuilder.setSpan(new AbsoluteSizeSpan(paramInt5), paramString1.length(), paramString1.length() + paramString2.length(), 33);
    localSpannableStringBuilder.setSpan(new AbsoluteSizeSpan(paramInt5), paramString1.length() + paramString2.length(), paramString1.length() + paramString2.length() + paramString3.length(), 33);
    return localSpannableStringBuilder;
  }
  
  public static String getTimeDate(String paramString)
  {
    String str = ConvertTimeByLong(System.currentTimeMillis());
    String[] arrayOfString = paramString.split(" ");
    int i = getGapCountDay(paramString, str);
    if (i == 0) {
      paramString = MyApp.app.getResources().getString(2131166185) + arrayOfString[1];
    }
    do
    {
      return paramString;
      if (i == 1) {
        return MyApp.app.getResources().getString(2131165248) + arrayOfString[1];
      }
    } while (i != 2);
    return MyApp.app.getResources().getString(2131165330) + arrayOfString[1];
  }
  
  public static SpannableStringBuilder getTwoColorSizeStyleString(String paramString1, String paramString2)
  {
    return getTwoColorSizeStyleString(paramString1, paramString2, MyApp.app.getResources().getColor(2131427595), MyApp.app.getResources().getColor(2131427345), (int)MyApp.app.getResources().getDimension(2131231254), (int)MyApp.app.getResources().getDimension(2131231254));
  }
  
  public static SpannableStringBuilder getTwoColorSizeStyleString(String paramString1, String paramString2, int paramInt1, int paramInt2)
  {
    return getTwoColorSizeStyleString(paramString1, paramString2, MyApp.app.getResources().getColor(2131427345), MyApp.app.getResources().getColor(2131427595), paramInt1, paramInt2);
  }
  
  public static SpannableStringBuilder getTwoColorSizeStyleString(String paramString1, String paramString2, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    SpannableStringBuilder localSpannableStringBuilder = new SpannableStringBuilder(paramString1 + paramString2);
    localSpannableStringBuilder.setSpan(new ForegroundColorSpan(paramInt1), 0, paramString1.length(), 33);
    localSpannableStringBuilder.setSpan(new ForegroundColorSpan(paramInt2), paramString1.length(), paramString1.length() + paramString2.length(), 33);
    localSpannableStringBuilder.setSpan(new AbsoluteSizeSpan(paramInt3), 0, paramString1.length(), 33);
    localSpannableStringBuilder.setSpan(new AbsoluteSizeSpan(paramInt4), paramString1.length(), paramString1.length() + paramString2.length(), 33);
    return localSpannableStringBuilder;
  }
  
  public static byte[] getUDPSetInitPwdData(String paramString1, String paramString2)
  {
    b.a();
    byte[] arrayOfByte1 = b.c(Integer.parseInt(paramString2));
    arrayOfByte1 = b.a().a(arrayOfByte1);
    int i = Integer.parseInt(paramString1);
    b.a();
    byte[] arrayOfByte2 = b.c(i);
    byte[] arrayOfByte3 = new byte[48];
    arrayOfByte3[0] = 48;
    System.arraycopy(arrayOfByte1, 0, arrayOfByte3, 4, arrayOfByte1.length);
    System.arraycopy(arrayOfByte2, 0, arrayOfByte3, 44, arrayOfByte2.length);
    Log.e("dxs", "mssage-->" + Arrays.toString(arrayOfByte3) + "pwd--->" + paramString2 + "app-->" + paramString1);
    return arrayOfByte3;
  }
  
  public static String getUrlValueByName(String paramString1, String paramString2)
  {
    String str = "";
    String[] arrayOfString = paramString1.substring(paramString1.indexOf("?") + 1).split("&");
    int j = arrayOfString.length;
    int i = 0;
    for (;;)
    {
      paramString1 = str;
      if (i < j)
      {
        paramString1 = arrayOfString[i];
        if (paramString1.contains(paramString2)) {
          paramString1 = paramString1.replace(paramString2 + "=", "");
        }
      }
      else
      {
        return paramString1;
      }
      i += 1;
    }
  }
  
  public static String getUserProtocolUrl()
  {
    return "http://faq.cloud-links.net/yoosee/protocol/index.html";
  }
  
  public static String getVideoRecodeName(String paramString, Contact paramContact)
    throws NoSuchFieldException, NullPointerException
  {
    if (!isSD()) {
      throw new NoSuchFieldException("noSD");
    }
    if (paramContact == null) {
      throw new NullPointerException("contact is null");
    }
    long l = System.currentTimeMillis();
    paramString = new File(AppConfig.Relese.LOCALVIDEO_PATH + File.separator + paramString + File.separator + paramContact.contactId);
    if (!paramString.exists()) {
      paramString.mkdirs();
    }
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault());
    paramContact = paramContact.getVideoInfo() + "_" + localSimpleDateFormat.format(new Date(l));
    return paramString.getPath() + File.separator + paramContact + ".mp4";
  }
  
  public static String getWifiName(String paramString)
  {
    String str = paramString;
    if (paramString != null)
    {
      str = paramString;
      if (paramString.length() > 2)
      {
        str = paramString;
        if (paramString.charAt(0) == '"') {
          str = paramString.substring(1, paramString.length() - 1);
        }
      }
    }
    return str;
  }
  
  public static int getlanguage()
  {
    Object localObject1 = MyApp.app.getResources().getConfiguration().locale;
    Object localObject2 = ((Locale)localObject1).getLanguage();
    Object localObject3 = ((Locale)localObject1).getCountry();
    localObject1 = localObject2;
    if (((String)localObject2).equals("zh")) {
      localObject1 = (String)localObject2 + "_" + (String)localObject3;
    }
    localObject2 = MyApp.app.getResources().getStringArray(2131492865);
    int i = 0;
    while (i < localObject2.length)
    {
      localObject3 = localObject2[i].split(",");
      if (localObject3[0].trim().equals(localObject1)) {
        try
        {
          int j = Integer.parseInt(localObject3[1]);
          return j;
        }
        catch (NumberFormatException localNumberFormatException) {}
      }
      i += 1;
    }
    return 3;
  }
  
  public static boolean hasDigit(String paramString)
  {
    boolean bool = false;
    if (Pattern.compile(".*\\d+.*").matcher(paramString).matches()) {
      bool = true;
    }
    return bool;
  }
  
  public static final byte[] hex2byte(String paramString)
    throws IllegalArgumentException
  {
    int i = 0;
    if (paramString.length() % 2 != 0) {
      throw new IllegalArgumentException();
    }
    char[] arrayOfChar = paramString.toCharArray();
    byte[] arrayOfByte = new byte[paramString.length() / 2];
    int k = paramString.length();
    int j = 0;
    while (j < k)
    {
      paramString = new StringBuilder().append("");
      int m = j + 1;
      arrayOfByte[i] = new Integer(Integer.parseInt(arrayOfChar[j] + arrayOfChar[m], 16) & 0xFF).byteValue();
      j = m + 1;
      i += 1;
    }
    return arrayOfByte;
  }
  
  public static void hindKeyBoard(View paramView)
  {
    InputMethodManager localInputMethodManager = (InputMethodManager)MyApp.app.getSystemService("input_method");
    if (localInputMethodManager != null) {
      localInputMethodManager.hideSoftInputFromWindow(paramView.getWindowToken(), 2);
    }
  }
  
  public static byte[] intToBytes(int paramInt)
  {
    return new byte[] { (byte)(paramInt & 0xFF), (byte)(paramInt >> 8 & 0xFF), (byte)(paramInt >> 16 & 0xFF), (byte)(paramInt >> 24 & 0xFF) };
  }
  
  public static int[] intToBytes2(int paramInt)
  {
    return new int[] { paramInt >> 24 & 0xFF, paramInt >> 16 & 0xFF, paramInt >> 8 & 0xFF, paramInt & 0xFF };
  }
  
  public static InetAddress intToInetAddress(int paramInt)
  {
    int i = (byte)(paramInt & 0xFF);
    int j = (byte)(paramInt >> 8 & 0xFF);
    int k = (byte)(paramInt >> 16 & 0xFF);
    int m = (byte)(paramInt >> 24 & 0xFF);
    try
    {
      InetAddress localInetAddress = InetAddress.getByAddress(new byte[] { i, j, k, m });
      return localInetAddress;
    }
    catch (UnknownHostException localUnknownHostException)
    {
      throw new AssertionError();
    }
  }
  
  public static String intToIp(int paramInt)
  {
    return (paramInt & 0xFF) + "." + (paramInt >> 8 & 0xFF) + "." + (paramInt >> 16 & 0xFF) + "." + (paramInt >> 24 & 0xFF);
  }
  
  public static boolean is360()
  {
    return (Build.MANUFACTURER.equalsIgnoreCase("QIKU")) || (Build.MANUFACTURER.equalsIgnoreCase("360"));
  }
  
  public static boolean is5GWifi(int paramInt)
  {
    boolean bool2 = false;
    String str = String.valueOf(paramInt);
    boolean bool1 = bool2;
    if (str.length() > 0)
    {
      bool1 = bool2;
      if (str.charAt(0) == '5') {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public static boolean is868Device(int paramInt1, int paramInt2)
  {
    return (paramInt1 == 7) && ((paramInt2 == 30) || (paramInt2 == 31) || (paramInt2 == 32) || (paramInt2 == 8) || (paramInt2 == 18) || (paramInt2 == 28));
  }
  
  public static boolean isBackground(Context paramContext)
  {
    Iterator localIterator = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses().iterator();
    while (localIterator.hasNext())
    {
      ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)localIterator.next();
      if (localRunningAppProcessInfo.processName.equals(paramContext.getPackageName()))
      {
        Log.i(paramContext.getPackageName(), "此appimportace =" + localRunningAppProcessInfo.importance + ",context.getClass().getName()=" + paramContext.getClass().getName());
        if (localRunningAppProcessInfo.importance != 100)
        {
          Log.i(paramContext.getPackageName(), "处于后台" + localRunningAppProcessInfo.processName);
          return true;
        }
        Log.i(paramContext.getPackageName(), "处于前台" + localRunningAppProcessInfo.processName);
        return false;
      }
    }
    return false;
  }
  
  public static boolean isCanGetMall()
  {
    long l1 = SharedPreferencesManager.getInstance().getMallSystemMessageTime(MyApp.app);
    long l2 = System.currentTimeMillis();
    if (l2 - l1 > 30000L)
    {
      SharedPreferencesManager.getInstance().putMallSystemMessageTime(MyApp.app, l2);
      return true;
    }
    return false;
  }
  
  public static boolean isContainsKeyValue(String paramString1, String paramString2, String paramString3)
  {
    boolean bool2 = true;
    boolean bool1;
    if ((TextUtils.isEmpty(paramString1)) || (paramString2 == null) || (paramString3 == null))
    {
      bool1 = false;
      return bool1;
    }
    paramString1 = paramString1.substring(paramString1.indexOf("?") + 1);
    if (paramString1.length() > 0)
    {
      paramString1 = paramString1.split("&");
      int j = paramString1.length;
      int i = 0;
      for (;;)
      {
        if (i >= j) {
          break label116;
        }
        String[] arrayOfString = paramString1[i].split("=");
        if ((arrayOfString.length == 2) && (paramString2.equals(arrayOfString[0])))
        {
          bool1 = bool2;
          if (paramString3.equals(arrayOfString[1])) {
            break;
          }
        }
        i += 1;
      }
    }
    label116:
    return false;
  }
  
  public static boolean isCoupon(String paramString)
  {
    return isContainsKeyValue(paramString, "type", "couponV1Limited");
  }
  
  public static boolean isDebug(Context paramContext)
  {
    boolean bool = false;
    try
    {
      int i = paramContext.getApplicationInfo().flags;
      if ((i & 0x2) != 0) {
        bool = true;
      }
      return bool;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  public static boolean isEmail(String paramString)
  {
    return (paramString.contains("@")) && (paramString.contains("."));
  }
  
  public static boolean isEmial(String paramString)
  {
    return isEmail(paramString);
  }
  
  public static boolean isEmpty(String paramString)
  {
    return (TextUtils.isEmpty(paramString)) || (paramString.equals("null")) || (paramString.equals("NULL"));
  }
  
  public static boolean isEqualStringArray(List<String> paramList1, List<String> paramList2)
  {
    if ((paramList1 == null) || (paramList2 == null) || (paramList1.size() != paramList2.size())) {
      return false;
    }
    return paramList1.containsAll(paramList2);
  }
  
  public static boolean isFacebookAvilible(Context paramContext)
  {
    boolean bool2 = false;
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    boolean bool1 = bool2;
    int i;
    if (paramContext != null) {
      i = 0;
    }
    for (;;)
    {
      bool1 = bool2;
      if (i < paramContext.size())
      {
        if (((PackageInfo)paramContext.get(i)).packageName.equals("com.facebook.katana")) {
          bool1 = true;
        }
      }
      else {
        return bool1;
      }
      i += 1;
    }
  }
  
  public static boolean isFastDoubleClick()
  {
    long l1 = System.currentTimeMillis();
    long l2 = lastClickTime;
    lastClickTime = l1;
    return l1 - l2 < 1000L;
  }
  
  public static boolean isFishPosContatct(int paramInt1, int paramInt2)
  {
    return (paramInt1 == 7) && (paramInt2 == 30);
  }
  
  public static boolean isForeground(Context paramContext, String paramString)
  {
    if ((paramContext == null) || (TextUtils.isEmpty(paramString))) {
      return false;
    }
    paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningTasks(1);
    return (paramContext != null) && (paramContext.size() > 0) && (paramString.equals(((ActivityManager.RunningTaskInfo)paramContext.get(0)).topActivity.getClassName()));
  }
  
  public static boolean isHXSTDevice(Contact paramContact)
  {
    return (paramContact != null) && (paramContact.getConfigFunction() >= 0) && (1 == (paramContact.getConfigFunction() >> 9 & 0x1));
  }
  
  public static boolean isHuaWei()
  {
    return Build.MANUFACTURER.equalsIgnoreCase("HUAWEI");
  }
  
  public static boolean isLineAvilible(Context paramContext)
  {
    boolean bool2 = false;
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    boolean bool1 = bool2;
    int i;
    if (paramContext != null) {
      i = 0;
    }
    for (;;)
    {
      bool1 = bool2;
      if (i < paramContext.size())
      {
        if (((PackageInfo)paramContext.get(i)).packageName.equals("jp.naver.line.android")) {
          bool1 = true;
        }
      }
      else {
        return bool1;
      }
      i += 1;
    }
  }
  
  public static boolean isMeiZu()
  {
    return Build.MANUFACTURER.equalsIgnoreCase("Meizu");
  }
  
  public static boolean isMobileNO(String paramString)
  {
    return (isNumeric(paramString)) && (paramString.length() > 5) && (paramString.length() < 16);
  }
  
  public static boolean isMobileNOAddCountryCode(String paramString)
  {
    return Pattern.compile("^\\+\\d{1,5}\\-{1}\\d{6,15}$", 2).matcher(paramString).matches();
  }
  
  public static boolean isNumeric(String paramString)
  {
    if ((paramString == null) || ("".equals(paramString))) {
      return false;
    }
    return Pattern.compile("[0-9]*").matcher(paramString).matches();
  }
  
  public static final boolean isOPen(Context paramContext)
  {
    paramContext = (LocationManager)paramContext.getSystemService("location");
    boolean bool1 = paramContext.isProviderEnabled("gps");
    boolean bool2 = paramContext.isProviderEnabled("network");
    return (bool1) || (bool2);
  }
  
  public static boolean isPanorama(int paramInt)
  {
    return (paramInt == 34) || (paramInt == 36) || (paramInt == 33) || (paramInt == 35);
  }
  
  public static boolean isRecyclerViewToTop(RecyclerView paramRecyclerView)
  {
    Object localObject;
    if (paramRecyclerView != null)
    {
      localObject = paramRecyclerView.getLayoutManager();
      if (localObject != null) {}
    }
    for (;;)
    {
      return true;
      if (((RecyclerView.h)localObject).getItemCount() != 0)
      {
        View localView;
        RecyclerView.LayoutParams localLayoutParams;
        if ((localObject instanceof LinearLayoutManager))
        {
          localObject = (LinearLayoutManager)localObject;
          if (paramRecyclerView.getChildCount() <= 0) {
            break label161;
          }
          localView = paramRecyclerView.getChildAt(0);
          if ((localView != null) && (localView.getMeasuredHeight() >= paramRecyclerView.getMeasuredHeight()))
          {
            if (Build.VERSION.SDK_INT < 14)
            {
              if ((!ViewCompat.canScrollVertically(paramRecyclerView, -1)) && (paramRecyclerView.getScrollY() <= 0)) {}
              for (boolean bool = true;; bool = false) {
                return bool;
              }
            }
            if (!ViewCompat.canScrollVertically(paramRecyclerView, -1)) {
              continue;
            }
            return false;
          }
          localView = paramRecyclerView.getChildAt(0);
          localLayoutParams = (RecyclerView.LayoutParams)localView.getLayoutParams();
        }
        label161:
        for (int i = localView.getTop() - localLayoutParams.topMargin - getRecyclerViewItemTopInset(localLayoutParams) - paramRecyclerView.getPaddingTop(); (((LinearLayoutManager)localObject).findFirstCompletelyVisibleItemPosition() >= 1) || (i != 0); i = 0) {
          return false;
        }
      }
    }
  }
  
  private static boolean isRuo(String paramString)
  {
    return paramString.matches(WEAK);
  }
  
  public static boolean isSD()
  {
    String str = Environment.getExternalStorageState();
    return (str.equals("mounted")) || (str.equals("shared"));
  }
  
  public static boolean isSmartHomeContatct(int paramInt1, int paramInt2)
  {
    return (paramInt1 == 7) && ((paramInt2 == 31) || (paramInt2 == 8) || (paramInt2 == 18) || (paramInt2 == 28));
  }
  
  public static boolean isSpecification(int paramInt)
  {
    return Build.VERSION.SDK_INT >= paramInt;
  }
  
  public static boolean isSupportPhoneRegister(String paramString)
  {
    boolean bool2 = false;
    String[] arrayOfString = SharedPreferencesManager.getInstance().getCountryCodeList(MyApp.app);
    int i = 0;
    for (;;)
    {
      boolean bool1 = bool2;
      if (i < arrayOfString.length)
      {
        if (arrayOfString[i].equals(paramString)) {
          bool1 = true;
        }
      }
      else {
        return bool1;
      }
      i += 1;
    }
  }
  
  public static <T extends HttpResult> boolean isTostCmd(T paramT)
  {
    boolean bool = true;
    paramT = paramT.getError_code();
    int i = -1;
    switch (paramT.hashCode())
    {
    }
    for (;;)
    {
      switch (i)
      {
      default: 
        bool = false;
      }
      return bool;
      if (paramT.equals("10000"))
      {
        i = 0;
        continue;
        if (paramT.equals("10001"))
        {
          i = 1;
          continue;
          if (paramT.equals("10901061"))
          {
            i = 2;
            continue;
            if (paramT.equals("10901060")) {
              i = 3;
            }
          }
        }
      }
    }
  }
  
  public static boolean isTostCmd(String paramString)
  {
    boolean bool = true;
    int i = -1;
    switch (paramString.hashCode())
    {
    }
    for (;;)
    {
      switch (i)
      {
      default: 
        bool = false;
      }
      return bool;
      if (paramString.equals("10000"))
      {
        i = 0;
        continue;
        if (paramString.equals("10001"))
        {
          i = 1;
          continue;
          if (paramString.equals("10901061"))
          {
            i = 2;
            continue;
            if (paramString.equals("10901060")) {
              i = 3;
            }
          }
        }
      }
    }
  }
  
  public static boolean isWeakPassword(String paramString)
  {
    return getPassWordStatus(paramString) < 2;
  }
  
  public static boolean isWeixinAvilible(Context paramContext)
  {
    boolean bool2 = false;
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    boolean bool1 = bool2;
    int i;
    if (paramContext != null) {
      i = 0;
    }
    for (;;)
    {
      bool1 = bool2;
      if (i < paramContext.size())
      {
        if (((PackageInfo)paramContext.get(i)).packageName.equals("com.tencent.mm")) {
          bool1 = true;
        }
      }
      else {
        return bool1;
      }
      i += 1;
    }
  }
  
  public static boolean isWhatsappAvilible(Context paramContext)
  {
    boolean bool2 = false;
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    boolean bool1 = bool2;
    int i;
    if (paramContext != null) {
      i = 0;
    }
    for (;;)
    {
      bool1 = bool2;
      if (i < paramContext.size())
      {
        if (((PackageInfo)paramContext.get(i)).packageName.equals("com.whatsapp")) {
          bool1 = true;
        }
      }
      else {
        return bool1;
      }
      i += 1;
    }
  }
  
  public static boolean isWifiOpen(ScanResult paramScanResult)
  {
    return (paramScanResult.capabilities.toLowerCase().indexOf("wep") == -1) && (paramScanResult.capabilities.toLowerCase().indexOf("wpa") == -1);
  }
  
  public static boolean isXiaoMi()
  {
    return Build.MANUFACTURER.equalsIgnoreCase("XIAOMI");
  }
  
  public static boolean isYooseePackge()
  {
    return "aom.yoosee".replace("a", "c").equals(MyApp.app.getPackageName());
  }
  
  public static boolean isZh(Context paramContext)
  {
    return paramContext.getResources().getConfiguration().locale.getLanguage().endsWith("zh");
  }
  
  public static boolean isZhMobileNO(String paramString)
  {
    return (isNumeric(paramString)) && (paramString.length() == 11) && (paramString.startsWith("1"));
  }
  
  public static boolean isZhOrEn()
  {
    String str = MyApp.app.getResources().getConfiguration().locale.getLanguage();
    return (str.equals("zh")) || (str.equals("en"));
  }
  
  public static String listToString(List<String> paramList)
  {
    String str = "";
    int i = 0;
    if (i < paramList.size())
    {
      if (i == paramList.size() - 1) {}
      for (str = str + (String)paramList.get(i);; str = str + (String)paramList.get(i) + "|")
      {
        i += 1;
        break;
      }
    }
    return str;
  }
  
  public static Bitmap montageBitmap(Bitmap paramBitmap1, Bitmap paramBitmap2, int paramInt1, int paramInt2)
  {
    paramInt1 = paramBitmap2.getWidth();
    paramInt2 = paramBitmap2.getHeight();
    paramBitmap1 = Bitmap.createScaledBitmap(paramBitmap1, paramInt1, paramInt2, true);
    Bitmap localBitmap = Bitmap.createBitmap(paramInt1, paramInt2, Bitmap.Config.ARGB_8888);
    Canvas localCanvas = new Canvas(localBitmap);
    localCanvas.drawBitmap(paramBitmap2, 0.0F, 0.0F, null);
    localCanvas.drawBitmap(paramBitmap1, 0.0F, 0.0F, null);
    return localBitmap;
  }
  
  public static String numToHex32(int paramInt)
  {
    return String.format("%08x", new Object[] { Integer.valueOf(paramInt) });
  }
  
  public static int px2dp(Context paramContext, float paramFloat)
  {
    return (int)(paramFloat / paramContext.getResources().getDisplayMetrics().density + 0.5F);
  }
  
  public static void saveBitmapToSDcard(Bitmap paramBitmap, String paramString)
    throws Exception
  {
    if (paramBitmap == null) {
      throw new Exception("Create Bitmap error");
    }
    paramString = new File(paramString);
    paramString.createNewFile();
    paramString = new FileOutputStream(paramString);
    paramBitmap.compress(Bitmap.CompressFormat.JPEG, 100, paramString);
    paramString.flush();
    paramString.close();
  }
  
  public static boolean saveImgToGallery(String paramString)
  {
    return saveImgToGallery(paramString, 0);
  }
  
  public static boolean saveImgToGallery(String paramString, int paramInt)
  {
    if (!isSD()) {}
    while ((paramString == null) || (paramString.length() <= 0)) {
      return false;
    }
    String str = "image/png";
    if (paramInt == 1) {
      str = "video/mpeg";
    }
    try
    {
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("datetaken", new Date().toString());
      localContentValues.put("mime_type", str);
      localContentValues.put("_data", paramString);
      MyApp.app.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, localContentValues);
      MediaScannerConnection.scanFile(MyApp.app, new String[] { AppConfig.Relese.SCREENSHORT, AppConfig.Relese.LOCALVIDEO_PATH }, null, null);
      return true;
    }
    catch (Exception paramString)
    {
      for (;;)
      {
        paramString.printStackTrace();
      }
    }
  }
  
  public static void setClipboard(String paramString)
  {
    ((ClipboardManager)MyApp.app.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText(paramString, paramString));
  }
  
  public static byte[] setDeviceApWifiPwd(String paramString)
  {
    int j = 0;
    byte[] arrayOfByte = new byte['Đ'];
    int i = 0;
    while (i < arrayOfByte.length)
    {
      arrayOfByte[0] = 0;
      i += 1;
    }
    arrayOfByte[0] = 2;
    arrayOfByte[8] = 1;
    paramString = paramString.getBytes();
    i = j;
    while (i < paramString.length)
    {
      arrayOfByte[(i + 144)] = paramString[i];
      i += 1;
    }
    return arrayOfByte;
  }
  
  public static void setHxstPrePoints(String paramString1, String paramString2, int paramInt1, int paramInt2)
  {
    if (paramString2.length() > 0)
    {
      int i = (byte)paramInt1;
      int j = (byte)paramInt2;
      b.a().b(paramString1, paramString2, new byte[] { -5, 0, i, j });
    }
  }
  
  public static void setKeepScreenOn(Window paramWindow)
  {
    paramWindow.addFlags(2097280);
  }
  
  public static void setKeepScreenOnAndShowLocked(Window paramWindow)
  {
    paramWindow.addFlags(2621568);
  }
  
  public static void setPrePoints(String paramString1, String paramString2, int paramInt1, int paramInt2)
  {
    if (paramString2.length() > 0)
    {
      int i = (byte)paramInt1;
      int j = (byte)paramInt2;
      b.a().b(paramString1, paramString2, new byte[] { 87, 0, i, j });
      if ((paramInt1 == 1) || (paramInt1 == 3)) {
        b.a().b(paramString1, paramString2, new byte[] { 87, 0, 2, 0 });
      }
    }
  }
  
  public static void setSelectMode(Contact paramContact, int paramInt)
  {
    paramInt = getShapeType(paramContact, paramInt);
    MediaPlayer.getInstance().selectPanormaMode(paramContact.subType, paramContact.fishPos, paramInt);
  }
  
  public static void setStatusColor(Activity paramActivity, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 23)
    {
      Object localObject = paramActivity.getWindow();
      ((Window)localObject).clearFlags(67108864);
      ((Window)localObject).addFlags(Integer.MIN_VALUE);
      ((Window)localObject).setStatusBarColor(0);
      localObject = createStatusView(paramActivity, paramInt);
      ViewGroup localViewGroup = (ViewGroup)paramActivity.getWindow().getDecorView();
      paramActivity.getWindow().getDecorView().setSystemUiVisibility(8192);
      localViewGroup.addView((View)localObject);
    }
  }
  
  public static void showPromptDialog(Context paramContext, int paramInt1, int paramInt2)
  {
    paramContext = new NormalDialog(paramContext, paramContext.getResources().getString(paramInt1), paramContext.getResources().getString(paramInt2), "", "");
    paramContext.setStyle(5);
    paramContext.showDialog();
  }
  
  public static void sleepThread(long paramLong)
  {
    try
    {
      Thread.sleep(paramLong);
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
      localInterruptedException.printStackTrace();
    }
  }
  
  public static int sp2px(Context paramContext, float paramFloat)
  {
    return (int)(paramContext.getResources().getDisplayMetrics().scaledDensity * paramFloat + 0.5F);
  }
  
  public static List<String> stringToList(String paramString)
  {
    paramString = paramString.split("\\|");
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (i < paramString.length)
    {
      localArrayList.add(paramString[i]);
      i += 1;
    }
    return localArrayList;
  }
  
  public static void toGPS(Context paramContext)
  {
    Intent localIntent = new Intent();
    localIntent.setAction("android.settings.LOCATION_SOURCE_SETTINGS");
    paramContext.startActivity(localIntent);
  }
  
  public static Key toKey(byte[] paramArrayOfByte)
    throws Exception
  {
    paramArrayOfByte = new DESKeySpec(paramArrayOfByte);
    return SecretKeyFactory.getInstance("DES").generateSecret(paramArrayOfByte);
  }
  
  public static byte uniteBytes(byte paramByte1, byte paramByte2)
  {
    return (byte)((byte)(Byte.decode("0x" + new String(new byte[] { paramByte1 })).byteValue() << 4) ^ Byte.decode("0x" + new String(new byte[] { paramByte2 })).byteValue());
  }
  
  public static String utc2Local(String paramString)
  {
    Object localObject = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    ((SimpleDateFormat)localObject).setTimeZone(TimeZone.getTimeZone("UTC"));
    try
    {
      localObject = ((SimpleDateFormat)localObject).parse(paramString);
      SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      localSimpleDateFormat.setTimeZone(TimeZone.getDefault());
      localObject = localSimpleDateFormat.format(Long.valueOf(((Date)localObject).getTime()));
      return localObject;
    }
    catch (ParseException localParseException)
    {
      localParseException.printStackTrace();
    }
    return paramString;
  }
  
  public static Bitmap zoomImage(Bitmap paramBitmap, double paramDouble1, double paramDouble2)
  {
    float f1 = paramBitmap.getWidth();
    float f2 = paramBitmap.getHeight();
    Matrix localMatrix = new Matrix();
    localMatrix.postScale((float)paramDouble1 / f1, (float)paramDouble2 / f2);
    return Bitmap.createBitmap(paramBitmap, 0, 0, (int)f1, (int)f2, localMatrix, true);
  }
  
  public static class FileComparatorDown
    implements Comparator<Utils.FileInfo>
  {
    public FileComparatorDown() {}
    
    public int compare(Utils.FileInfo paramFileInfo1, Utils.FileInfo paramFileInfo2)
    {
      if (paramFileInfo1.LastModified < paramFileInfo2.LastModified) {
        return -1;
      }
      return 1;
    }
  }
  
  public static class FileComparatorUp
    implements Comparator<Utils.FileInfo>
  {
    public FileComparatorUp() {}
    
    public int compare(Utils.FileInfo paramFileInfo1, Utils.FileInfo paramFileInfo2)
    {
      if (paramFileInfo1.LastModified < paramFileInfo2.LastModified) {
        return 1;
      }
      return -1;
    }
  }
  
  public static class FileInfo
  {
    long LastModified;
    String path;
    
    public FileInfo() {}
  }
  
  public static class FileUp
    implements Comparator<File>
  {
    public FileUp() {}
    
    public int compare(File paramFile1, File paramFile2)
    {
      return paramFile1.getName().compareToIgnoreCase(paramFile2.getName());
    }
  }
}
