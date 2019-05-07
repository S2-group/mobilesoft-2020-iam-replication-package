package me.dingtone.app.im.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.KeyguardManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.PowerManager;
import android.telephony.TelephonyManager;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import me.dingtone.app.im.activity.DTActivity;
import me.dingtone.app.im.activity.FeedbackMissingCreditsOfferListActivity;
import me.dingtone.app.im.activity.SuperofferwallActivity;
import me.dingtone.app.im.activity.WebViewActivity;
import me.dingtone.app.im.ad.b;
import me.dingtone.app.im.datatype.message.DTMessage;
import me.dingtone.app.im.h.a.l;
import me.dingtone.app.im.log.DTLog;
import me.dingtone.app.im.manager.DTApplication;
import me.dingtone.app.im.manager.el;

public class kl
{
  public static TextView a = null;
  
  public static int a(Context paramContext, float paramFloat)
  {
    return (int)(paramContext.getResources().getDisplayMetrics().density * paramFloat + 0.5F);
  }
  
  public static SpannableString a(String paramString, SpannableString paramSpannableString)
  {
    int i = paramString.indexOf("[");
    int j = paramString.indexOf("]");
    paramSpannableString.setSpan(new ForegroundColorSpan(-65536), i, j + 1, 33);
    return paramSpannableString;
  }
  
  public static String a(long paramLong)
  {
    DecimalFormat localDecimalFormat = new DecimalFormat(".00");
    if (paramLong < 1L) {
      return "0";
    }
    if (paramLong < 1024L) {
      return paramLong + "B";
    }
    if (paramLong < 1048576L)
    {
      d = paramLong / 1024.0D;
      return localDecimalFormat.format(d) + "K";
    }
    if (paramLong < 1073741824L)
    {
      d = paramLong / 1048576.0D;
      return localDecimalFormat.format(d) + "M";
    }
    double d = paramLong / 1.073741824E9D;
    return localDecimalFormat.format(d) + "G";
  }
  
  public static String a(Activity paramActivity, int paramInt)
  {
    Object localObject2 = "";
    int i = paramInt / 60;
    paramInt = i / 60;
    i %= 60;
    if (paramInt > 0)
    {
      if (paramInt > 1) {
        localObject2 = paramInt + " " + paramActivity.getString(a.l.history_call_hours);
      }
    }
    else
    {
      localObject1 = localObject2;
      if (i > 0) {
        if (i <= 1) {
          break label193;
        }
      }
    }
    label193:
    for (Object localObject1 = (String)localObject2 + " " + i + " " + paramActivity.getString(a.l.history_call_minutes);; localObject1 = (String)localObject2 + " " + i + " " + paramActivity.getString(a.l.history_call_minute))
    {
      localObject2 = localObject1;
      if (((String)localObject1).length() == 0)
      {
        localObject2 = "0 " + paramActivity.getString(a.l.history_call_minute);
        DTLog.e("PSTNCallManager", "the wait/block Time json result is wrong because not Bigger than 60seconds");
      }
      return localObject2;
      localObject2 = paramInt + " " + paramActivity.getString(a.l.history_call_hour);
      break;
    }
  }
  
  public static String a(Context paramContext)
  {
    if (paramContext == null) {
      return "";
    }
    if (b(paramContext)) {
      return "Phone";
    }
    return "Pad";
  }
  
  /* Error */
  public static String a(File paramFile)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: new 163	java/io/BufferedReader
    //   5: dup
    //   6: new 165	java/io/FileReader
    //   9: dup
    //   10: aload_0
    //   11: invokespecial 168	java/io/FileReader:<init>	(Ljava/io/File;)V
    //   14: invokespecial 171	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   17: astore_1
    //   18: aload_1
    //   19: astore_0
    //   20: new 173	java/lang/StringBuffer
    //   23: dup
    //   24: invokespecial 174	java/lang/StringBuffer:<init>	()V
    //   27: astore_2
    //   28: aload_1
    //   29: astore_0
    //   30: aload_1
    //   31: invokevirtual 177	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   34: astore 4
    //   36: aload 4
    //   38: ifnull +54 -> 92
    //   41: aload_1
    //   42: astore_0
    //   43: aload_2
    //   44: new 69	java/lang/StringBuilder
    //   47: dup
    //   48: invokespecial 71	java/lang/StringBuilder:<init>	()V
    //   51: aload 4
    //   53: invokevirtual 80	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   56: ldc -77
    //   58: invokevirtual 80	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   61: invokevirtual 84	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   64: invokevirtual 182	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   67: pop
    //   68: goto -40 -> 28
    //   71: astore_2
    //   72: aload_1
    //   73: astore_0
    //   74: aload_2
    //   75: invokevirtual 185	java/io/IOException:printStackTrace	()V
    //   78: aload_3
    //   79: astore_0
    //   80: aload_1
    //   81: ifnull +9 -> 90
    //   84: aload_1
    //   85: invokevirtual 188	java/io/BufferedReader:close	()V
    //   88: aload_3
    //   89: astore_0
    //   90: aload_0
    //   91: areturn
    //   92: aload_1
    //   93: astore_0
    //   94: aload_2
    //   95: invokevirtual 189	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   98: astore_2
    //   99: aload_2
    //   100: astore_0
    //   101: aload_1
    //   102: ifnull -12 -> 90
    //   105: aload_1
    //   106: invokevirtual 188	java/io/BufferedReader:close	()V
    //   109: aload_2
    //   110: areturn
    //   111: astore_0
    //   112: aload_0
    //   113: invokevirtual 185	java/io/IOException:printStackTrace	()V
    //   116: aload_2
    //   117: areturn
    //   118: astore_0
    //   119: aload_0
    //   120: invokevirtual 185	java/io/IOException:printStackTrace	()V
    //   123: aconst_null
    //   124: areturn
    //   125: astore_1
    //   126: aconst_null
    //   127: astore_0
    //   128: aload_0
    //   129: ifnull +7 -> 136
    //   132: aload_0
    //   133: invokevirtual 188	java/io/BufferedReader:close	()V
    //   136: aload_1
    //   137: athrow
    //   138: astore_0
    //   139: aload_0
    //   140: invokevirtual 185	java/io/IOException:printStackTrace	()V
    //   143: goto -7 -> 136
    //   146: astore_1
    //   147: goto -19 -> 128
    //   150: astore_2
    //   151: aconst_null
    //   152: astore_1
    //   153: goto -81 -> 72
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	156	0	paramFile	File
    //   17	89	1	localBufferedReader	java.io.BufferedReader
    //   125	12	1	localObject1	Object
    //   146	1	1	localObject2	Object
    //   152	1	1	localObject3	Object
    //   27	17	2	localStringBuffer	StringBuffer
    //   71	24	2	localIOException1	IOException
    //   98	19	2	str1	String
    //   150	1	2	localIOException2	IOException
    //   1	88	3	localObject4	Object
    //   34	18	4	str2	String
    // Exception table:
    //   from	to	target	type
    //   20	28	71	java/io/IOException
    //   30	36	71	java/io/IOException
    //   43	68	71	java/io/IOException
    //   94	99	71	java/io/IOException
    //   105	109	111	java/io/IOException
    //   84	88	118	java/io/IOException
    //   2	18	125	finally
    //   132	136	138	java/io/IOException
    //   20	28	146	finally
    //   30	36	146	finally
    //   43	68	146	finally
    //   74	78	146	finally
    //   94	99	146	finally
    //   2	18	150	java/io/IOException
  }
  
  public static String a(String paramString)
  {
    String str;
    if (paramString.endsWith(".00")) {
      str = paramString.substring(0, paramString.length() - 3);
    }
    do
    {
      return str;
      str = paramString;
    } while (!paramString.endsWith(".0"));
    return paramString.substring(0, paramString.length() - 2);
  }
  
  public static String a(String paramString, int paramInt)
  {
    if ((paramString == null) || (paramString.length() <= paramInt)) {
      return paramString;
    }
    return paramString.substring(0, paramInt) + "...";
  }
  
  public static String a(DTMessage paramDTMessage)
  {
    if ((paramDTMessage.getMsgType() == 2) || (paramDTMessage.getMsgType() == 91)) {
      return "jpg";
    }
    if ((paramDTMessage.getMsgType() == 6) || (paramDTMessage.getMsgType() == 92)) {
      return "jpg";
    }
    return "tmp";
  }
  
  public static void a(int paramInt)
  {
    DTActivity localDTActivity = DTApplication.f().k();
    if (localDTActivity != null) {
      Toast.makeText(localDTActivity, localDTActivity.getString(paramInt), 1).show();
    }
  }
  
  public static void a(Activity paramActivity)
  {
    if (paramActivity == null)
    {
      DTLog.e("ToolsForAll", "ShowInputMethod...activity == null");
      return;
    }
    ((InputMethodManager)paramActivity.getSystemService("input_method")).toggleSoftInput(0, 2);
  }
  
  public static void a(Activity paramActivity, EditText paramEditText)
  {
    if (paramActivity == null)
    {
      DTLog.e("ToolsForAll", "CollapseSoftInputMethod...activity == null");
      return;
    }
    if (paramEditText != null)
    {
      ((InputMethodManager)paramActivity.getSystemService("input_method")).hideSoftInputFromWindow(paramEditText.getWindowToken(), 0);
      return;
    }
    DTLog.e("ToolsForAll", "CollapseSoftInputMethod...inputText == null");
  }
  
  public static void a(Activity paramActivity, String paramString)
  {
    if (me.dingtone.app.im.u.a.O.equals(paramString))
    {
      DTLog.i("WebViewActivity", "WebViewActivity...cn offerwall");
      if (el.a().cE()) {
        b.b().a(15, paramActivity);
      }
    }
    do
    {
      return;
      b.b().r(paramActivity);
      return;
      if (me.dingtone.app.im.u.a.P.equals(paramString))
      {
        DTLog.i("WebViewActivity", "WebViewActivity...en offerwall...isChinaversion=" + el.a().cE());
        if (el.a().cE())
        {
          b.b().a(15, paramActivity);
          return;
        }
        paramActivity.startActivity(new Intent(paramActivity, SuperofferwallActivity.class));
        return;
      }
    } while (!me.dingtone.app.im.u.a.Q.equals(paramString));
    paramActivity.startActivity(new Intent(paramActivity, FeedbackMissingCreditsOfferListActivity.class));
  }
  
  public static void a(Context paramContext, Uri paramUri, String paramString)
  {
    try
    {
      paramContext = paramContext.getContentResolver().openAssetFileDescriptor(paramUri, "r").createInputStream();
      paramUri = new FileOutputStream(new File(paramString));
      paramString = new byte['Ѐ'];
      for (;;)
      {
        int i = paramContext.read(paramString);
        if (i <= 0) {
          break;
        }
        paramUri.write(paramString, 0, i);
      }
      return;
    }
    catch (FileNotFoundException paramContext)
    {
      paramContext.printStackTrace();
      return;
      paramContext.close();
      paramUri.close();
      return;
    }
    catch (IOException paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static void a(Context paramContext, View paramView)
  {
    ((InputMethodManager)paramContext.getSystemService("input_method")).showSoftInput(paramView, 2);
  }
  
  public static void a(TextView paramTextView)
  {
    paramTextView.getPaint().setFlags(8);
    paramTextView.getPaint().setAntiAlias(true);
  }
  
  /* Error */
  public static void a(File paramFile1, File paramFile2)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: new 393	java/io/BufferedInputStream
    //   5: dup
    //   6: new 357	java/io/FileInputStream
    //   9: dup
    //   10: aload_0
    //   11: invokespecial 394	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   14: invokespecial 397	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
    //   17: astore_0
    //   18: new 399	java/io/BufferedOutputStream
    //   21: dup
    //   22: new 351	java/io/FileOutputStream
    //   25: dup
    //   26: aload_1
    //   27: invokespecial 355	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   30: invokespecial 402	java/io/BufferedOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   33: astore_1
    //   34: sipush 5120
    //   37: newarray byte
    //   39: astore_3
    //   40: aload_0
    //   41: aload_3
    //   42: invokevirtual 403	java/io/BufferedInputStream:read	([B)I
    //   45: istore_2
    //   46: iload_2
    //   47: iconst_m1
    //   48: if_icmpeq +38 -> 86
    //   51: aload_1
    //   52: aload_3
    //   53: iconst_0
    //   54: iload_2
    //   55: invokevirtual 404	java/io/BufferedOutputStream:write	([BII)V
    //   58: goto -18 -> 40
    //   61: astore 4
    //   63: aload_0
    //   64: astore_3
    //   65: aload 4
    //   67: astore_0
    //   68: aload_3
    //   69: ifnull +7 -> 76
    //   72: aload_3
    //   73: invokevirtual 405	java/io/BufferedInputStream:close	()V
    //   76: aload_1
    //   77: ifnull +7 -> 84
    //   80: aload_1
    //   81: invokevirtual 406	java/io/BufferedOutputStream:close	()V
    //   84: aload_0
    //   85: athrow
    //   86: aload_1
    //   87: invokevirtual 409	java/io/BufferedOutputStream:flush	()V
    //   90: aload_0
    //   91: ifnull +7 -> 98
    //   94: aload_0
    //   95: invokevirtual 405	java/io/BufferedInputStream:close	()V
    //   98: aload_1
    //   99: ifnull +7 -> 106
    //   102: aload_1
    //   103: invokevirtual 406	java/io/BufferedOutputStream:close	()V
    //   106: return
    //   107: astore_0
    //   108: aconst_null
    //   109: astore_1
    //   110: goto -42 -> 68
    //   113: astore 4
    //   115: aconst_null
    //   116: astore_1
    //   117: aload_0
    //   118: astore_3
    //   119: aload 4
    //   121: astore_0
    //   122: goto -54 -> 68
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	125	0	paramFile1	File
    //   0	125	1	paramFile2	File
    //   45	10	2	i	int
    //   1	118	3	localObject1	Object
    //   61	5	4	localObject2	Object
    //   113	7	4	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   34	40	61	finally
    //   40	46	61	finally
    //   51	58	61	finally
    //   86	90	61	finally
    //   2	18	107	finally
    //   18	34	113	finally
  }
  
  /* Error */
  public static void a(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnull +10 -> 11
    //   4: aload_1
    //   5: invokevirtual 415	java/lang/String:isEmpty	()Z
    //   8: ifeq +4 -> 12
    //   11: return
    //   12: new 417	java/io/DataOutputStream
    //   15: dup
    //   16: new 351	java/io/FileOutputStream
    //   19: dup
    //   20: aload_1
    //   21: invokespecial 418	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
    //   24: invokespecial 419	java/io/DataOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   27: astore_2
    //   28: aload_2
    //   29: astore_1
    //   30: aload_2
    //   31: aload_0
    //   32: invokevirtual 423	java/lang/String:getBytes	()[B
    //   35: invokevirtual 426	java/io/DataOutputStream:write	([B)V
    //   38: aload_2
    //   39: astore_1
    //   40: aload_2
    //   41: invokevirtual 427	java/io/DataOutputStream:flush	()V
    //   44: aload_2
    //   45: ifnull -34 -> 11
    //   48: aload_2
    //   49: invokevirtual 428	java/io/DataOutputStream:close	()V
    //   52: return
    //   53: astore_0
    //   54: aload_0
    //   55: invokevirtual 185	java/io/IOException:printStackTrace	()V
    //   58: return
    //   59: astore_3
    //   60: aconst_null
    //   61: astore_0
    //   62: aload_0
    //   63: astore_1
    //   64: aload_3
    //   65: invokestatic 433	com/crashlytics/android/a:a	(Ljava/lang/Throwable;)V
    //   68: aload_0
    //   69: ifnull -58 -> 11
    //   72: aload_0
    //   73: invokevirtual 428	java/io/DataOutputStream:close	()V
    //   76: return
    //   77: astore_0
    //   78: aload_0
    //   79: invokevirtual 185	java/io/IOException:printStackTrace	()V
    //   82: return
    //   83: astore_0
    //   84: aconst_null
    //   85: astore_1
    //   86: aload_1
    //   87: ifnull +7 -> 94
    //   90: aload_1
    //   91: invokevirtual 428	java/io/DataOutputStream:close	()V
    //   94: aload_0
    //   95: athrow
    //   96: astore_1
    //   97: aload_1
    //   98: invokevirtual 185	java/io/IOException:printStackTrace	()V
    //   101: goto -7 -> 94
    //   104: astore_0
    //   105: goto -19 -> 86
    //   108: astore_3
    //   109: aload_2
    //   110: astore_0
    //   111: goto -49 -> 62
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	114	0	paramString1	String
    //   0	114	1	paramString2	String
    //   27	83	2	localDataOutputStream	java.io.DataOutputStream
    //   59	6	3	localThrowable1	Throwable
    //   108	1	3	localThrowable2	Throwable
    // Exception table:
    //   from	to	target	type
    //   48	52	53	java/io/IOException
    //   12	28	59	java/lang/Throwable
    //   72	76	77	java/io/IOException
    //   12	28	83	finally
    //   90	94	96	java/io/IOException
    //   30	38	104	finally
    //   40	44	104	finally
    //   64	68	104	finally
    //   30	38	108	java/lang/Throwable
    //   40	44	108	java/lang/Throwable
  }
  
  public static void a(String paramString1, String paramString2, Context paramContext)
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("title_text", paramString2);
    localBundle.putString("URL", paramString1);
    paramString1 = new Intent(paramContext, WebViewActivity.class);
    paramString1.addFlags(268435456);
    paramString1.putExtras(localBundle);
    paramContext.startActivity(paramString1);
  }
  
  public static boolean a()
  {
    boolean bool = el.a().cQ();
    DTLog.d("ToolsForAll", "isShowCheckInAnim...isEarnCreditByAD=" + bool);
    if (!bool) {}
    do
    {
      return false;
      bool = el.a().cR();
      DTLog.d("ToolsForAll", "isShowCheckInAnim...isFirstGoIntoGetCreditView=" + bool);
    } while (bool);
    long l1 = el.a().cS();
    if (l1 <= 0L)
    {
      DTLog.d("ToolsForAll", "isShowCheckInAnim...clickedCheckInIconTime <= 0");
      return true;
    }
    long l2 = new Date().getTime();
    if (ln.a(l1, l2))
    {
      DTLog.d("ToolsForAll", "isShowCheckInAnim...isClickedCheckInIconToday = true");
      return false;
    }
    if (l2 - el.a().cT() > 604800000L) {}
    for (bool = true;; bool = false)
    {
      DTLog.d("ToolsForAll", "isShowCheckInAnim...isCheckedInOldWeek=" + bool);
      if (!bool) {
        break;
      }
      return true;
    }
    if (l2 - l1 > 604800000L) {}
    for (bool = true;; bool = false)
    {
      DTLog.d("ToolsForAll", "isShowCheckInAnim...isClickedInOldWeek=" + bool);
      if (!bool) {
        break;
      }
      return true;
    }
  }
  
  public static byte[] a(Uri paramUri)
  {
    ByteArrayOutputStream localByteArrayOutputStream;
    try
    {
      byte[] arrayOfByte = new byte['Ѐ'];
      InputStream localInputStream = DTApplication.f().getContentResolver().openInputStream(paramUri);
      localByteArrayOutputStream = new ByteArrayOutputStream();
      for (;;)
      {
        int i = localInputStream.read(arrayOfByte);
        if (i == -1) {
          break;
        }
        localByteArrayOutputStream.write(arrayOfByte, 0, i);
      }
      localIOException1.printStackTrace();
    }
    catch (IOException localIOException1)
    {
      paramUri = null;
    }
    for (;;)
    {
      return paramUri;
      paramUri = localByteArrayOutputStream.toByteArray();
      try
      {
        localByteArrayOutputStream.close();
        localIOException1.close();
        return paramUri;
      }
      catch (IOException localIOException2) {}
    }
  }
  
  public static String b(long paramLong)
  {
    if (paramLong == 0L) {
      return "0B";
    }
    Locale localLocale = ln.a();
    if (paramLong < 1024L) {
      return String.format(localLocale, "%.1f", new Object[] { Double.valueOf(paramLong) }) + "B";
    }
    if (paramLong < 1048576L) {
      return String.format(localLocale, "%.1f", new Object[] { Double.valueOf(paramLong / 1024.0D) }) + "KB";
    }
    if (paramLong < 1073741824L) {
      return String.format(localLocale, "%.1f", new Object[] { Double.valueOf(paramLong / 1048576.0D) }) + "MB";
    }
    return String.format(localLocale, "%.1f", new Object[] { Double.valueOf(paramLong / 1.073741824E9D) }) + "GB";
  }
  
  public static String b(String paramString)
  {
    String str2 = "$";
    String str1 = str2;
    try
    {
      DTLog.d("ToolsForAll", "currencyCode=" + paramString);
      str1 = str2;
      paramString = Currency.getInstance(paramString).getSymbol();
      str1 = paramString;
      DTLog.d("ToolsForAll", "priceStr=" + paramString);
      return paramString;
    }
    catch (IllegalArgumentException paramString)
    {
      DTLog.e("ToolsForAll", "IllegalArgumentException e");
    }
    return str1;
  }
  
  public static String b(String paramString1, String paramString2)
  {
    if (paramString1 == null) {
      return null;
    }
    String str = paramString1.replace("|", "_");
    paramString1 = f(str);
    paramString2 = String.format("%s-%s", new Object[] { str, paramString2 });
    paramString1 = paramString1 + paramString2 + ".mp3";
    DTLog.d("ToolsForAll", "getSaveFilePathForSMSVoiceMessage...fullPath=" + paramString1);
    return paramString1;
  }
  
  public static String b(DTMessage paramDTMessage)
  {
    if ((paramDTMessage.getMsgType() == 2) || (paramDTMessage.getMsgType() == 91)) {
      return "jpg";
    }
    if ((paramDTMessage.getMsgType() == 6) || (paramDTMessage.getMsgType() == 92)) {
      return "mp4";
    }
    return "tmp";
  }
  
  public static void b(Activity paramActivity)
  {
    if (DTActivity.v() == 0) {}
    try
    {
      paramActivity.startActivity(new Intent(paramActivity, me.dingtone.app.im.u.a.a));
      return;
    }
    catch (Exception paramActivity)
    {
      com.crashlytics.android.a.a(paramActivity);
    }
  }
  
  public static boolean b()
  {
    Object localObject = (WindowManager)DTApplication.f().getSystemService("window");
    if (localObject != null)
    {
      localObject = ((WindowManager)localObject).getDefaultDisplay();
      DisplayMetrics localDisplayMetrics = new DisplayMetrics();
      ((Display)localObject).getMetrics(localDisplayMetrics);
      return Math.sqrt(Math.pow(localDisplayMetrics.widthPixels / localDisplayMetrics.xdpi, 2.0D) + Math.pow(localDisplayMetrics.heightPixels / localDisplayMetrics.ydpi, 2.0D)) >= 6.0D;
    }
    if (!b(DTApplication.f().getApplicationContext())) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public static boolean b(Context paramContext)
  {
    Context localContext = paramContext;
    if (paramContext == null) {
      localContext = DTApplication.f().getApplicationContext();
    }
    paramContext = (TelephonyManager)localContext.getSystemService("phone");
    if (paramContext != null) {
      return 5 == paramContext.getSimState();
    }
    return false;
  }
  
  public static String c()
  {
    String str = ix.h + "ChatBg/";
    File localFile = new File(str);
    if (!localFile.exists()) {
      localFile.mkdirs();
    }
    return str;
  }
  
  public static void c(Context paramContext)
  {
    if (paramContext == null) {}
    View localView;
    do
    {
      return;
      localView = ((Activity)paramContext).getWindow().peekDecorView();
    } while ((localView == null) || (localView.getWindowToken() == null));
    ((InputMethodManager)paramContext.getSystemService("input_method")).hideSoftInputFromWindow(localView.getWindowToken(), 0);
  }
  
  public static boolean c(String paramString)
  {
    if (paramString != null) {
      try
      {
        if (paramString.isEmpty()) {
          return false;
        }
        boolean bool = Pattern.compile("[0-9]*").matcher(paramString).matches();
        return bool;
      }
      catch (NullPointerException paramString)
      {
        DTLog.e("ToolsForAll", "IsNumeric...NullPointerException");
      }
    }
    return false;
  }
  
  public static String d()
  {
    String str = ix.h + "LinkPreview/";
    File localFile = new File(str);
    if (!localFile.exists()) {
      localFile.mkdirs();
    }
    return str;
  }
  
  public static void d(Context paramContext)
  {
    ((InputMethodManager)paramContext.getSystemService("input_method")).toggleSoftInput(0, 2);
  }
  
  public static boolean d(String paramString)
  {
    if (paramString != null) {
      try
      {
        if (paramString.isEmpty()) {
          return false;
        }
        boolean bool = Pattern.compile("[0-9+]*").matcher(paramString).matches();
        return bool;
      }
      catch (NullPointerException paramString)
      {
        DTLog.e("ToolsForAll", "IsNumeric...NullPointerException");
      }
    }
    return false;
  }
  
  public static int e(String paramString)
  {
    return (int)new File(paramString).length();
  }
  
  public static String e()
  {
    return ix.h + "locationtemp.jpg";
  }
  
  public static boolean e(Context paramContext)
  {
    try
    {
      if (((PowerManager)paramContext.getSystemService("power")).isScreenOn())
      {
        boolean bool = ((KeyguardManager)paramContext.getSystemService("keyguard")).inKeyguardRestrictedInputMode();
        if (bool) {
          return false;
        }
      }
      else
      {
        return false;
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return true;
  }
  
  public static int f()
  {
    try
    {
      int i = DTApplication.f().getPackageManager().getPackageInfo(DTApplication.f().getPackageName(), 0).versionCode;
      return i;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return 1;
  }
  
  public static String f(String paramString)
  {
    paramString = paramString.replace("|", "_");
    String str = ix.g + paramString + "/";
    File localFile = new File(str);
    if ((!localFile.exists()) && (!localFile.mkdirs())) {
      DTLog.e("ToolsForAll", "GetDownloadPathOfMessage conId = " + paramString + " create direct failed path = " + str);
    }
    return str;
  }
  
  public static Long g(String paramString)
  {
    try
    {
      long l = Long.parseLong(paramString);
      return Long.valueOf(l);
    }
    catch (NumberFormatException paramString)
    {
      com.crashlytics.android.a.a(paramString);
    }
    return Long.valueOf(0L);
  }
  
  public static String g()
  {
    try
    {
      String str = DTApplication.f().getPackageManager().getPackageInfo(DTApplication.f().getPackageName(), 0).versionName;
      return str;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return "";
  }
  
  public static String h(String paramString)
  {
    String str = "";
    if (paramString != null) {
      str = Pattern.compile("\\s*|\t|\r|\n").matcher(paramString).replaceAll("");
    }
    return str;
  }
  
  public static void h()
  {
    Object localObject = ((ActivityManager)DTApplication.f().getApplicationContext().getSystemService("activity")).getRunningAppProcesses();
    if (localObject != null)
    {
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        String str = ((ActivityManager.RunningAppProcessInfo)((Iterator)localObject).next()).processName;
        DTLog.i("ToolsForAll", "running progress name:" + str);
      }
    }
  }
  
  public static String i(String paramString)
  {
    int j = 0;
    if ((paramString == null) || ("".equals(paramString))) {
      return paramString;
    }
    for (;;)
    {
      int i;
      int k;
      try
      {
        Object localObject = paramString.split(" ");
        StringBuilder localStringBuilder = new StringBuilder();
        i = 0;
        if (i < localObject.length)
        {
          String str2 = localObject[i].trim();
          k = j;
          if (str2 != null)
          {
            k = j;
            if (!"".equals(str2))
            {
              String str1 = str2.substring(0, 1).toUpperCase();
              str2 = str2.substring(1);
              str1 = str1 + str2.toLowerCase();
              if (j > 0) {
                localStringBuilder.append(" ");
              }
              localStringBuilder.append(str1);
              k = j + 1;
            }
          }
        }
        else
        {
          localObject = localStringBuilder.toString();
          return localObject;
        }
      }
      catch (Exception localException)
      {
        return paramString;
      }
      i += 1;
      j = k;
    }
  }
  
  public static void i()
  {
    Iterator localIterator = DTApplication.f().getPackageManager().getInstalledPackages(0).iterator();
    while (localIterator.hasNext())
    {
      Object localObject = (PackageInfo)localIterator.next();
      String str1 = ((PackageInfo)localObject).applicationInfo.loadLabel(DTApplication.f().getPackageManager()).toString();
      String str2 = ((PackageInfo)localObject).packageName;
      localObject = ((PackageInfo)localObject).versionName;
      DTLog.i("ToolsForAll", "Installed package info -- appName:" + str1 + ", packageName:" + str2 + ", versionName:" + (String)localObject);
    }
  }
  
  public static boolean j()
  {
    boolean bool = true;
    long l = jd.h();
    if (l == 0L) {
      return false;
    }
    Calendar localCalendar1 = Calendar.getInstance();
    localCalendar1.setTimeInMillis(l);
    Calendar localCalendar2 = Calendar.getInstance();
    localCalendar2.setTimeInMillis(System.currentTimeMillis());
    if ((localCalendar1.get(1) == localCalendar2.get(1)) && (localCalendar1.get(2) == localCalendar2.get(2)) && (localCalendar1.get(5) == localCalendar2.get(5))) {}
    for (;;)
    {
      DTLog.d("ToolsForAll", "check isAutoClearVideoVoiceToday:" + bool);
      return bool;
      bool = false;
    }
  }
  
  public static String k()
  {
    Object localObject4 = null;
    if (ix.d()) {}
    for (;;)
    {
      try
      {
        Object localObject1 = DTApplication.f().getExternalFilesDir(null);
        Object localObject3 = localObject1;
        if (localObject1 == null) {
          localObject3 = DTApplication.f().getFilesDir();
        }
        localObject1 = localObject4;
        if (localObject3 != null) {
          localObject1 = ((File)localObject3).getAbsolutePath();
        }
        return localObject1;
      }
      catch (Exception localException)
      {
        com.crashlytics.android.a.a(localException);
      }
      Object localObject2 = null;
    }
  }
}
