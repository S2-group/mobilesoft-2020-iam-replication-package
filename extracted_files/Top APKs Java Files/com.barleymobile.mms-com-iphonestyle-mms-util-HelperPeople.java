package com.iphonestyle.mms.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.media.AudioManager;
import android.media.ExifInterface;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.ContactsContract.Data;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.widget.Toast;
import com.android.common.inbuymodule.InappBuy;
import com.android.common.inbuymodule.UpdateVersion;
import com.common.bubble.module.data.BubbleUtils;
import com.common.sms.ui.module.message.BaseMessageApplication;
import com.crazystudio.mms.core.R.string;
import com.google.iphonestyle.mms.pdu.GenericPdu;
import com.google.iphonestyle.mms.pdu.PduBody;
import com.google.iphonestyle.mms.pdu.PduPart;
import com.google.iphonestyle.mms.pdu.PduPersister;
import com.iphonestyle.mms.ConstSetting;
import com.iphonestyle.mms.ThemeEle;
import com.iphonestyle.mms.ui.ComposeMessageActivity;
import com.iphonestyle.mms.ui.ConversationList;
import com.iphonestyle.mms.ui.MessagingPreferenceActivity;
import com.iphonestyle.mms.ui.MyTempFileProvider;
import com.iphonestyle.mms.ui.cb.ShowChoiceListCb;
import com.iphonestyle.mms.ui.ios.CommonSettingInfo;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;

public class HelperPeople
{
  public static String BUBBLE_COME;
  public static String BUBBLE_GO;
  public static String BUY_CHECK = "cHJlZl9rZXlfY2hlY2tidXlfZmxhZw==";
  public static String COLOR_ENABLE;
  public static String COLOR_RECV;
  public static String COLOR_SEND;
  private static final int DATE_FORMAT_D_M_Y = 1;
  private static final int DATE_FORMAT_D_M_Y_NUM = 4;
  private static final int DATE_FORMAT_M_D_Y = 0;
  private static final int DATE_FORMAT_M_D_Y_NUM = 3;
  private static final int DATE_FORMAT_Y_M_D = 2;
  private static final int DATE_FORMAT_Y_M_D_NUM = 5;
  private static final String PERSIST_CLS = "com.google.iphonestyle.mms.pdu.PduPersister";
  private static String RECV_CODE;
  public static final int SDK_2_3_VERSION = 9;
  public static final int SDK_3_0_VERSION = 11;
  public static final int SDK_4_0_VERSION = 14;
  public static final int SDK_4_1_VERSION = 16;
  public static final int SDK_4_2_VERSION = 17;
  public static final int SDK_4_4_VERSION = 19;
  private static String SEND_CODE;
  public static final String TAG = "HelperPeople";
  public static String TEXT_COLOR_ENABLE;
  public static String TEXT_COLOR_RECV;
  public static String TEXT_COLOR_SEND;
  private static String TEXT_RECV_CODE;
  private static String TEXT_SEND_CODE;
  private static String mAPNInfo;
  private static HashMap<String, Integer> mCacheColor = new HashMap();
  private static HashMap<String, Drawable> mCacheDrawable;
  private static String mCarrierNumber;
  private static int mDay;
  private static int mHeight;
  private static int mHour;
  private static HelperPeople mInstance;
  private static Process mLogcatProc;
  private static int mMaxBubbleWidth;
  private static int mMinute;
  private static Bitmap mMmsBackground;
  private static int mMonth;
  private static final String[] mMonthString;
  static SharedPreferences mPrefs;
  private static int mSecond;
  private static String[] mSnapshotSelect;
  private static int mTotalLocalTime;
  private static int mTotalThemeTime;
  private static int mWeek;
  private static int mWidth = 0;
  private static int mYear;
  static MediaPlayer player;
  private int aaa = 1000;
  private String str = "test";
  
  static
  {
    mHeight = 0;
    mInstance = null;
    mPrefs = null;
    mTotalThemeTime = 0;
    mMonthString = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sep", "Oct", "Nov", "Dec" };
    mYear = 0;
    mMonth = 0;
    mDay = 0;
    mWeek = 0;
    mHour = 0;
    mMinute = 0;
    mSecond = 0;
    mSnapshotSelect = new String[] { "Save to Gallery", "Send with Mms", "Send by email" };
    mTotalLocalTime = 0;
    mMaxBubbleWidth = 0;
    mLogcatProc = null;
    player = new MediaPlayer();
    mAPNInfo = "";
    mCarrierNumber = "";
    mMmsBackground = null;
    COLOR_ENABLE = "cHJlZl9rZXlfY29udl9idWJibGVfY29sb3JfZW5hYmxl";
    COLOR_RECV = "cHJlZl9rZXlfY29udl9idWJibGVfY29sb3JfcmVjdg==";
    COLOR_SEND = "cHJlZl9rZXlfY29udl9idWJibGVfY29sb3Jfc2VuZA==";
    TEXT_COLOR_ENABLE = "cHJlZl9rZXlfY29udl9idWJibGVfdGV4dF9jb2xvcl9lbmFibGU=";
    TEXT_COLOR_RECV = "cHJlZl9rZXlfY29udl9idWJibGVfdGV4dF9jb2xvcl9yZWN2";
    TEXT_COLOR_SEND = "cHJlZl9rZXlfY29udl9idWJibGVfdGV4dF9jb2xvcl9zZW5k";
    RECV_CODE = "cHJlZl90aXRsZV9jb252X2J1YmJsZV9yZWN2X2NvbG9y";
    SEND_CODE = "cHJlZl90aXRsZV9jb252X2J1YmJsZV9zZW5kX2NvbG9y";
    TEXT_RECV_CODE = "cHJlZl90aXRsZV9jb252X2J1YmJsZV9yZWN2X3RleHRfY29sb3I=";
    TEXT_SEND_CODE = "cHJlZl90aXRsZV9jb252X2J1YmJsZV9zZW5kX3RleHRfY29sb3I=";
    BUBBLE_GO = "YnViYmxlX2dvX2RlZmF1bHQ=";
    BUBBLE_COME = "YnViYmxlX2NvbWVfZGVmYXVsdA==";
    mCacheDrawable = new HashMap();
  }
  
  public HelperPeople() {}
  
  public static boolean buyTest(Context paramContext)
  {
    return buyTest(paramContext, true);
  }
  
  public static boolean buyTest(Context paramContext, boolean paramBoolean)
  {
    boolean bool1;
    if (!ConstSetting.mHasRemoveAdsItem) {
      bool1 = false;
    }
    boolean bool2;
    do
    {
      do
      {
        return bool1;
        bool1 = UpdateVersion.getStatus(paramContext);
        bool2 = InappBuy.getInstance(paramContext).getStatus() | bool1;
        bool1 = bool2;
      } while (bool2);
      bool1 = bool2;
    } while (!paramBoolean);
    return bool2;
  }
  
  public static boolean checkApkExist(Context paramContext, String paramString)
  {
    Object localObject = null;
    try
    {
      paramContext = paramContext.createPackageContext(paramString, 2);
      return paramContext != null;
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        paramContext = localObject;
      }
    }
  }
  
  public static void checkBubble(Context paramContext, Intent paramIntent, CommonSettingInfo paramCommonSettingInfo, String paramString, int paramInt)
  {
    if (paramInt == 1) {}
    for (String str1 = RECV_CODE;; str1 = SEND_CODE)
    {
      str1 = testDecode64(str1);
      paramInt = getLocalResourceId(paramContext, "string", str1);
      String str2 = getLocalResourceString(paramContext, "string", str1);
      if (paramCommonSettingInfo.getTitleId() == paramInt)
      {
        paramIntent.putExtra("id", paramInt + "");
        setDynamicIntent(paramContext, paramIntent, getCustomBubbleColorValue(), getCustomBubbleColor(), paramString, str2);
        UpdateVersion.onEventChangeBubble(paramContext, str1);
        paramContext.startActivity(paramIntent);
      }
      return;
    }
  }
  
  public static void checkBubbleText(Context paramContext, Intent paramIntent, CommonSettingInfo paramCommonSettingInfo, String paramString, int paramInt)
  {
    if (paramInt == 1) {}
    for (String str1 = TEXT_RECV_CODE;; str1 = TEXT_SEND_CODE)
    {
      str1 = testDecode64(str1);
      paramInt = getLocalResourceId(paramContext, "string", str1);
      String str2 = getLocalResourceString(paramContext, "string", str1);
      if (paramCommonSettingInfo.getTitleId() == paramInt)
      {
        paramIntent.putExtra("id", paramInt + "");
        setDynamicIntent(paramContext, paramIntent, getCustomBubbleTextColorValue(), getCustomBubbleTexColor(), paramString, str2);
        UpdateVersion.onEventChangeBubble(paramContext, str1);
        paramContext.startActivity(paramIntent);
      }
      return;
    }
  }
  
  public static boolean checkDefaultSmsApp(Context paramContext)
  {
    Object localObject1 = "";
    if (Build.VERSION.SDK_INT < 19) {
      return true;
    }
    try
    {
      Object localObject2 = Class.forName("android.provider.Telephony$Sms");
      getParamTypes((Class)localObject2, "getDefaultSmsPackage");
      localObject2 = (String)((Class)localObject2).getDeclaredMethod("getDefaultSmsPackage", new Class[] { Context.class }).invoke(null, new Object[] { paramContext });
      localObject1 = localObject2;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
      }
    }
    if ((localObject1 != null) && (((String)localObject1).equalsIgnoreCase(paramContext.getPackageName())))
    {
      setBlockOptions(paramContext);
      return true;
    }
    return false;
  }
  
  private static boolean checkGooglePlay(Context paramContext)
  {
    Object localObject = null;
    try
    {
      paramContext = paramContext.createPackageContext("com.android.vending", 2);
      if (paramContext != null) {
        return true;
      }
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
        paramContext = localObject;
      }
    }
    return false;
  }
  
  public static boolean checkPersitCall(Context paramContext, PduPersister paramPduPersister, String paramString, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 17) {}
    while (getPersistParamNum(paramContext, paramPduPersister, paramString) != paramInt) {
      return false;
    }
    return false;
  }
  
  private static String checkSnapshotPath()
  {
    String str1 = null;
    Object localObject;
    if (!MessagingPreferenceActivity.hasSdcard()) {
      localObject = null;
    }
    File localFile;
    do
    {
      return localObject;
      localObject = Environment.getExternalStorageDirectory();
      if (localObject != null) {
        str1 = ((File)localObject).getPath() + "/mms-snapshot/";
      }
      localFile = new File(str1);
      localObject = str1;
    } while (localFile.exists());
    localFile.mkdir();
    return str1;
  }
  
  public static void cleanCacheRes()
  {
    if (mCacheDrawable != null) {
      mCacheDrawable.clear();
    }
    if (mCacheColor != null) {
      mCacheColor.clear();
    }
  }
  
  public static void cleanOrientationFile(Context paramContext, Bitmap paramBitmap, Uri paramUri)
  {
    paramContext = new File(checkSnapshotPath() + "IMG_Orientation.jpg");
    if (paramContext.exists()) {
      paramContext.delete();
    }
  }
  
  public static String convertFetchStatsTime(long paramLong)
  {
    if (paramLong < 0L) {
      return "-1";
    }
    int j = (int)(paramLong / 1000L);
    int i = j;
    if (j >= 10) {
      i = 10;
    }
    return "" + i;
  }
  
  /* Error */
  public static int copyFile(InputStream paramInputStream, String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aconst_null
    //   3: astore 4
    //   5: new 473	java/io/FileOutputStream
    //   8: dup
    //   9: aload_1
    //   10: invokespecial 474	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
    //   13: astore_1
    //   14: sipush 4096
    //   17: newarray byte
    //   19: astore_3
    //   20: aload_0
    //   21: aload_3
    //   22: invokevirtual 480	java/io/InputStream:read	([B)I
    //   25: istore_2
    //   26: iload_2
    //   27: ifle +24 -> 51
    //   30: aload_1
    //   31: aload_3
    //   32: iconst_0
    //   33: iload_2
    //   34: invokevirtual 486	java/io/OutputStream:write	([BII)V
    //   37: goto -17 -> 20
    //   40: astore_0
    //   41: aload_1
    //   42: ifnull +7 -> 49
    //   45: aload_1
    //   46: invokevirtual 489	java/io/OutputStream:close	()V
    //   49: iconst_m1
    //   50: ireturn
    //   51: aload_1
    //   52: invokevirtual 489	java/io/OutputStream:close	()V
    //   55: aload_1
    //   56: ifnull +7 -> 63
    //   59: aload_1
    //   60: invokevirtual 489	java/io/OutputStream:close	()V
    //   63: iconst_0
    //   64: ireturn
    //   65: astore_0
    //   66: ldc 59
    //   68: ldc_w 491
    //   71: aload_0
    //   72: invokestatic 497	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   75: pop
    //   76: goto -13 -> 63
    //   79: astore_0
    //   80: ldc 59
    //   82: ldc_w 491
    //   85: aload_0
    //   86: invokestatic 497	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   89: pop
    //   90: iconst_m1
    //   91: ireturn
    //   92: astore_0
    //   93: aload_3
    //   94: astore_1
    //   95: aload_1
    //   96: ifnull +7 -> 103
    //   99: aload_1
    //   100: invokevirtual 489	java/io/OutputStream:close	()V
    //   103: aload_0
    //   104: athrow
    //   105: astore_1
    //   106: ldc 59
    //   108: ldc_w 491
    //   111: aload_1
    //   112: invokestatic 497	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   115: pop
    //   116: goto -13 -> 103
    //   119: astore_0
    //   120: goto -25 -> 95
    //   123: astore_0
    //   124: aload 4
    //   126: astore_1
    //   127: goto -86 -> 41
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	130	0	paramInputStream	InputStream
    //   0	130	1	paramString	String
    //   25	9	2	i	int
    //   1	93	3	arrayOfByte	byte[]
    //   3	122	4	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   14	20	40	java/lang/Exception
    //   20	26	40	java/lang/Exception
    //   30	37	40	java/lang/Exception
    //   51	55	40	java/lang/Exception
    //   59	63	65	java/io/IOException
    //   45	49	79	java/io/IOException
    //   5	14	92	finally
    //   99	103	105	java/io/IOException
    //   14	20	119	finally
    //   20	26	119	finally
    //   30	37	119	finally
    //   51	55	119	finally
    //   5	14	123	java/lang/Exception
  }
  
  private static int dip2px(Context paramContext, float paramFloat)
  {
    return (int)TypedValue.applyDimension(1, paramFloat, paramContext.getResources().getDisplayMetrics());
  }
  
  public static String dumpHexStr(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    if (i < paramString.length())
    {
      char c = paramString.charAt(i);
      if (c < '') {
        localStringBuilder.append(c);
      }
      for (;;)
      {
        localStringBuilder.append(" ");
        i += 1;
        break;
        localStringBuilder.append(Integer.toHexString(c));
      }
    }
    return localStringBuilder.toString();
  }
  
  private static String findDefaultValue(String paramString)
  {
    Object localObject2 = "";
    int i = 0;
    Object localObject1 = localObject2;
    if (i < ShowChoiceListCb.SETTINGS_INT.length)
    {
      if (paramString.equalsIgnoreCase(ShowChoiceListCb.SETTINGS_INT[i][1])) {
        localObject1 = ShowChoiceListCb.SETTINGS_INT[i][2];
      }
    }
    else {
      i = 0;
    }
    for (;;)
    {
      localObject2 = localObject1;
      if (i < ShowChoiceListCb.SETTINGS.length)
      {
        if (paramString.equalsIgnoreCase(ShowChoiceListCb.SETTINGS[i][1])) {
          localObject2 = ShowChoiceListCb.SETTINGS[i][2];
        }
      }
      else
      {
        return localObject2;
        i += 1;
        break;
      }
      i += 1;
    }
  }
  
  public static String getApn(Context paramContext)
  {
    String str1 = "";
    Object localObject = getCarrierCode(paramContext);
    if ((mCarrierNumber.equalsIgnoreCase((String)localObject)) && (!TextUtils.isEmpty(mAPNInfo))) {
      return mAPNInfo;
    }
    mCarrierNumber = (String)localObject;
    paramContext = new DefaultHttpClient();
    localObject = new HttpGet("http://marsoffset.goforandroid.com/GoSmsMarService/apnClient.do?pver=129&number=" + (String)localObject);
    try
    {
      paramContext = paramContext.execute((HttpUriRequest)localObject);
      if (paramContext.getStatusLine().getStatusCode() != 200) {
        return "";
      }
      paramContext = paramContext.getEntity().getContent();
      localObject = new ByteArrayOutputStream();
      byte[] arrayOfByte = new byte['Ā'];
      for (;;)
      {
        int i = paramContext.read(arrayOfByte);
        if (i == -1) {
          break;
        }
        ((ByteArrayOutputStream)localObject).write(arrayOfByte, 0, i);
      }
      mAPNInfo = paramContext;
    }
    catch (IOException paramContext)
    {
      Log.e("error", paramContext.getLocalizedMessage());
      paramContext = str1;
    }
    for (;;)
    {
      Log.e("HelperPeople", "apn string:" + paramContext);
      return paramContext;
      paramContext = new String(((ByteArrayOutputStream)localObject).toByteArray());
    }
  }
  
  public static int getAppVersionCode(Context paramContext)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      return i;
    }
    catch (Exception paramContext)
    {
      Log.e("VersionInfo", "Exception", paramContext);
    }
    return 0;
  }
  
  public static String getAppVersionName(Context paramContext)
  {
    Object localObject1 = "";
    Object localObject2;
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
      if (paramContext != null)
      {
        localObject1 = paramContext;
        localObject2 = paramContext;
        if (paramContext.length() > 0) {}
      }
      else
      {
        return "";
      }
    }
    catch (Exception paramContext)
    {
      Log.e("VersionInfo", "Exception", paramContext);
      localObject2 = localObject1;
    }
    return localObject2;
  }
  
  public static boolean getBooleanKey(Context paramContext, String paramString, boolean paramBoolean)
  {
    paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext);
    boolean bool = paramBoolean;
    if (paramContext != null) {
      bool = paramContext.getBoolean(paramString, paramBoolean);
    }
    return bool;
  }
  
  public static boolean getBooleanKey2(Context paramContext, String paramString, boolean paramBoolean)
  {
    paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext);
    boolean bool = paramBoolean;
    if (paramContext != null) {
      bool = paramContext.getBoolean(testDecode64(paramString), paramBoolean);
    }
    return bool;
  }
  
  public static int getBubbleWidth(Context paramContext)
  {
    int i = PreferenceManager.getDefaultSharedPreferences(paramContext).getInt("pref_key_message_bubble_custom_width", 300);
    if (mMaxBubbleWidth == 0) {
      mMaxBubbleWidth = dip2px(paramContext, i);
    }
    return mMaxBubbleWidth;
  }
  
  public static String getCarrierCode(Context paramContext)
  {
    paramContext = (TelephonyManager)paramContext.getSystemService("phone");
    if (paramContext != null) {
      return paramContext.getNetworkOperator();
    }
    return "";
  }
  
  public static String getConversationIPhoneDate(Context paramContext, long paramLong)
  {
    Object localObject = Calendar.getInstance();
    ((Calendar)localObject).setTimeInMillis(paramLong);
    int i = ((Calendar)localObject).get(1);
    int j = ((Calendar)localObject).get(2);
    int k = ((Calendar)localObject).get(5);
    int m = ((Calendar)localObject).get(7);
    ((Calendar)localObject).get(11);
    ((Calendar)localObject).get(12);
    ((Calendar)localObject).get(13);
    localObject = paramContext.getResources().getStringArray(getLocalResourceId(paramContext, "array", "week_list"));
    Calendar.getInstance();
    for (;;)
    {
      try
      {
        if ((mYear == i) && (mMonth == j) && (mDay == k)) {
          return getTime(paramContext, paramLong);
        }
        if ((mYear == i) && (mMonth == j) && (mDay - 1 == k)) {
          return "Yesterday";
        }
        if ((mYear == i) && (mMonth == j) && (mDay - k > 0) && (mDay - k <= 7)) {
          return String.format("%s", new Object[] { localObject[(m - 1)] });
        }
        m = mPrefs.getInt("pref_key_date_format", 0);
        if ((m != 0) && (m != 3)) {
          break;
        }
        if (i == mYear) {
          return String.format("%d/%d", new Object[] { Integer.valueOf(j + 1), Integer.valueOf(k) });
        }
        return String.format("%d/%d/%d", new Object[] { Integer.valueOf(j + 1), Integer.valueOf(k), Integer.valueOf(i) });
      }
      catch (Exception paramContext)
      {
        label301:
        paramContext.printStackTrace();
        return null;
      }
    }
    if (mYear != i) {
      return String.format("%d/%d/%d", new Object[] { Integer.valueOf(k), Integer.valueOf(j + 1), Integer.valueOf(i) });
    }
    return String.format("%d/%d", new Object[] { Integer.valueOf(k), Integer.valueOf(j + 1) });
    for (;;)
    {
      if (mYear != i) {
        return String.format("%d/%d/%d", new Object[] { Integer.valueOf(i), Integer.valueOf(j + 1), Integer.valueOf(k) });
      }
      paramContext = String.format("%d/%d", new Object[] { Integer.valueOf(j + 1), Integer.valueOf(k) });
      return paramContext;
      if ((m == 1) || (m == 4)) {
        break label301;
      }
      if (m != 2) {
        if (m != 5) {
          break;
        }
      }
    }
  }
  
  public static String getCoutryIso(Context paramContext)
  {
    paramContext = (TelephonyManager)paramContext.getSystemService("phone");
    if (paramContext != null) {
      return paramContext.getNetworkCountryIso();
    }
    return "";
  }
  
  public static void getCurrentSnapshot(Activity paramActivity)
  {
    Object localObject = paramActivity.getWindow().getDecorView();
    paramActivity = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
    ((View)localObject).draw(new Canvas(paramActivity));
    try
    {
      localObject = new FileOutputStream(ConstSetting.SNAPSHOT);
      paramActivity.compress(Bitmap.CompressFormat.PNG, 100, (OutputStream)localObject);
      ((FileOutputStream)localObject).close();
      return;
    }
    catch (Exception paramActivity)
    {
      paramActivity.printStackTrace();
    }
  }
  
  public static void getCurrentSnapshot(Activity paramActivity, String paramString)
  {
    View localView = paramActivity.getWindow().getDecorView();
    paramActivity = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
    localView.draw(new Canvas(paramActivity));
    try
    {
      paramString = new FileOutputStream(paramString);
      paramActivity.compress(Bitmap.CompressFormat.PNG, 100, paramString);
      paramString.close();
      return;
    }
    catch (Exception paramActivity)
    {
      paramActivity.printStackTrace();
    }
  }
  
  public static String getCurrentTheme(Context paramContext)
  {
    paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext);
    if (paramContext == null) {
      paramContext = ThemeEle.DEFAULT;
    }
    String str1;
    do
    {
      return paramContext;
      str1 = paramContext.getString("pref_key_theme_name", ThemeEle.DEFAULT);
      paramContext = str1;
    } while (!str1.contains("."));
    ThemeEle.PREFIX = "";
    return str1;
  }
  
  public static String[] getCustomBubbleColor()
  {
    return new String[] { "Black", "Blue", "Green", "Red", "Yellow", "Magenta", "Cyan", "Custom" };
  }
  
  public static String[] getCustomBubbleColorValue()
  {
    return new String[] { "#ff000001", "Blue", "Green", "Red", "Yellow", "Magenta", "Cyan", "Custom" };
  }
  
  public static String[] getCustomBubbleTexColor()
  {
    return new String[] { "White", "Black", "Blue", "Green", "Red", "Yellow", "Magenta", "Cyan", "Custom" };
  }
  
  public static String[] getCustomBubbleTextColorValue()
  {
    return new String[] { "White", "Black", "Blue", "Green", "Red", "Yellow", "Magenta", "Cyan", "Custom" };
  }
  
  private static String getDateString()
  {
    Calendar localCalendar = Calendar.getInstance();
    return String.format("%d%d%d-%02d%02d%02d", new Object[] { Integer.valueOf(localCalendar.get(1)), Integer.valueOf(localCalendar.get(2) + 1), Integer.valueOf(localCalendar.get(5)), Integer.valueOf(localCalendar.get(11)), Integer.valueOf(localCalendar.get(12)), Integer.valueOf(localCalendar.get(13)) });
  }
  
  public static String getEmojiPlugin(Context paramContext)
  {
    return paramContext.getPackageName() + ".emoji";
  }
  
  public static String getFormatDateCompose(Context paramContext)
  {
    int i = PreferenceManager.getDefaultSharedPreferences(paramContext).getInt("pref_key_date_format", 0);
    paramContext = "";
    if (i == 0) {
      paramContext = String.format("%d %d, %d", new Object[] { mMonthString[mMonth], Integer.valueOf(mDay), Integer.valueOf(mYear) });
    }
    do
    {
      return paramContext;
      if (i == 1) {
        return String.format("%d %d, %d", new Object[] { Integer.valueOf(mDay), mMonthString[mMonth], Integer.valueOf(mYear) });
      }
      if (i == 2) {
        return String.format("%d %d, %d", new Object[] { Integer.valueOf(mYear), mMonthString[mMonth], Integer.valueOf(mDay) });
      }
      if (i == 3) {
        return String.format("%d %d, %d", new Object[] { Integer.valueOf(mMonth + 1), Integer.valueOf(mDay), Integer.valueOf(mYear) });
      }
      if (i == 4) {
        return String.format("%d %d, %d", new Object[] { Integer.valueOf(mDay), Integer.valueOf(mMonth + 1), Integer.valueOf(mYear) });
      }
    } while (i != 5);
    return String.format("%d %d, %d", new Object[] { Integer.valueOf(mYear), Integer.valueOf(mMonth + 1), Integer.valueOf(mDay) });
  }
  
  public static String getFormatDateList(Context paramContext)
  {
    int i = PreferenceManager.getDefaultSharedPreferences(paramContext).getInt("pref_key_date_format", 0);
    paramContext = "";
    if ((i == 0) || (i == 3)) {
      paramContext = String.format("%d/%d/%d", new Object[] { Integer.valueOf(mMonth + 1), Integer.valueOf(mDay), Integer.valueOf(mYear % 100) });
    }
    do
    {
      return paramContext;
      if ((i == 1) || (i == 4)) {
        return String.format("%d/%d/%d", new Object[] { Integer.valueOf(mDay), Integer.valueOf(mMonth + 1), Integer.valueOf(mYear % 100) });
      }
    } while ((i != 2) && (i != 5));
    return String.format("%d/%d/%d", new Object[] { Integer.valueOf(mYear), Integer.valueOf(mMonth + 1), Integer.valueOf(mDay) });
  }
  
  public static String getIPhoneDate(Context paramContext, long paramLong)
  {
    Object localObject1 = Calendar.getInstance();
    ((Calendar)localObject1).setTimeInMillis(paramLong);
    int i = ((Calendar)localObject1).get(1);
    int j = ((Calendar)localObject1).get(2);
    int k = ((Calendar)localObject1).get(5);
    ((Calendar)localObject1).get(11);
    ((Calendar)localObject1).get(12);
    ((Calendar)localObject1).get(13);
    localObject1 = null;
    String str1 = null;
    localObject2 = localObject1;
    try
    {
      int m = mPrefs.getInt("pref_key_date_format", 0);
      if (m == 0)
      {
        localObject2 = localObject1;
        if (i != mYear)
        {
          localObject2 = localObject1;
          str1 = String.format("%s %d, %d", new Object[] { mMonthString[j], Integer.valueOf(k), Integer.valueOf(i) });
        }
      }
      else
      {
        if (m != 3) {
          break label303;
        }
        localObject2 = str1;
        if (i == mYear) {
          break label266;
        }
        localObject2 = str1;
        localObject1 = String.format("%d/%d/%d", new Object[] { Integer.valueOf(j + 1), Integer.valueOf(k), Integer.valueOf(i) });
      }
      for (;;)
      {
        localObject2 = localObject1;
        return (String)localObject1 + ", " + getTime(paramContext, paramLong);
        localObject2 = localObject1;
        str1 = String.format("%s %d", new Object[] { mMonthString[j], Integer.valueOf(k) });
        break;
        label266:
        localObject2 = str1;
        localObject1 = String.format("%d/%d", new Object[] { Integer.valueOf(j + 1), Integer.valueOf(k) });
        continue;
        label303:
        if (m == 1)
        {
          localObject2 = str1;
          localObject1 = String.format("%d %s, %d", new Object[] { Integer.valueOf(k), mMonthString[j], Integer.valueOf(i) });
        }
        else if (m == 4)
        {
          localObject2 = str1;
          localObject1 = String.format("%d/%d/%d", new Object[] { Integer.valueOf(k), Integer.valueOf(j + 1), Integer.valueOf(i) });
        }
        else if (m == 2)
        {
          localObject2 = str1;
          localObject1 = String.format("%d %s, %d", new Object[] { Integer.valueOf(i), mMonthString[j], Integer.valueOf(k) });
        }
        else
        {
          localObject1 = str1;
          if (m == 5)
          {
            localObject2 = str1;
            if (mYear != i)
            {
              localObject2 = str1;
              localObject1 = String.format("%d/%d/%d", new Object[] { Integer.valueOf(i), Integer.valueOf(j + 1), Integer.valueOf(k) });
            }
            else
            {
              localObject2 = str1;
              localObject1 = String.format("%d/%d", new Object[] { Integer.valueOf(j + 1), Integer.valueOf(k) });
            }
          }
        }
      }
      return localObject2;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static HelperPeople getInstance(Context paramContext)
  {
    init(paramContext);
    return mInstance;
  }
  
  public static int getIntKey(Context paramContext, String paramString, int paramInt)
  {
    paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext);
    int i = paramInt;
    if (paramContext != null) {
      i = paramContext.getInt(paramString, paramInt);
    }
    return i;
  }
  
  public static String getLanguage(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getString("pref_key_language_name", "en");
  }
  
  public static int getLocalResId(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    Resources localResources = paramContext.getResources();
    int j = localResources.getIdentifier(paramString2 + paramString3, paramString1, paramContext.getPackageName());
    int i = j;
    if (j <= 0) {
      i = localResources.getIdentifier(ThemeEle.DEFAULT + paramString3, paramString1, paramContext.getPackageName());
    }
    j = i;
    if (i <= 0) {
      j = localResources.getIdentifier(paramString3, paramString1, paramContext.getPackageName());
    }
    return j;
  }
  
  public static int getLocalResourceId(Context paramContext, String paramString1, String paramString2)
  {
    long l = System.currentTimeMillis();
    int i = paramContext.getResources().getIdentifier(paramString2, paramString1, paramContext.getPackageName());
    mTotalLocalTime = (int)(mTotalLocalTime + (System.currentTimeMillis() - l));
    return i;
  }
  
  public static String getLocalResourceString(Context paramContext, String paramString1, String paramString2)
  {
    long l = System.currentTimeMillis();
    paramContext = paramContext.getString(paramContext.getResources().getIdentifier(paramString2, paramString1, paramContext.getPackageName()));
    mTotalLocalTime = (int)(mTotalLocalTime + (System.currentTimeMillis() - l));
    return paramContext;
  }
  
  public static String getLocalResourceStringById(Context paramContext, String paramString, int paramInt)
  {
    long l = System.currentTimeMillis();
    paramContext.getResources();
    paramContext = paramContext.getString(paramInt);
    mTotalLocalTime = (int)(mTotalLocalTime + (System.currentTimeMillis() - l));
    return paramContext;
  }
  
  public static Bitmap getMmsBackground(Context paramContext, int paramInt1, int paramInt2)
  {
    if (mMmsBackground != null) {
      return mMmsBackground;
    }
    int i = getLocalResourceId(paramContext, "drawable", testDecode64(BUBBLE_COME));
    paramContext.getResources().getDrawable(i);
    paramContext = (NinePatchDrawable)paramContext.getResources().getDrawable(i);
    paramContext.setColorFilter(-16777216, PorterDuff.Mode.MULTIPLY);
    Bitmap localBitmap = Bitmap.createBitmap(paramInt1, paramInt2, Bitmap.Config.ARGB_8888);
    Canvas localCanvas = new Canvas(localBitmap);
    if (paramContext != null)
    {
      paramContext.setBounds(0, 0, paramInt1, paramInt2);
      paramContext.draw(localCanvas);
    }
    mMmsBackground = localBitmap;
    return localBitmap;
  }
  
  private static String getMmsLog(Context paramContext, String paramString)
  {
    paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext);
    String str1 = paramContext.getString("pref_route_test_result", paramString);
    str1 = str1 + "\n";
    str1 = str1 + paramContext.getString("pref_http_test_result", paramString);
    str1 = str1 + "\n";
    return str1 + paramContext.getString("pref_mms_data_status_result", paramString);
  }
  
  public static String getMonth(int paramInt)
  {
    return mMonthString[paramInt];
  }
  
  public static int getOrientation(Context paramContext, Bitmap paramBitmap, Uri paramUri)
  {
    int j = -1;
    Object localObject = null;
    paramBitmap = null;
    for (;;)
    {
      try
      {
        paramContext = paramContext.getContentResolver().openInputStream(paramUri);
        paramBitmap = paramContext;
        localObject = paramContext;
        paramUri = checkSnapshotPath() + "IMG_Orientation.jpg";
        paramBitmap = paramContext;
        localObject = paramContext;
        long l = System.currentTimeMillis();
        paramBitmap = paramContext;
        localObject = paramContext;
        copyFile(paramContext, paramUri);
        paramBitmap = paramContext;
        localObject = paramContext;
        Log.e("HelperPeople", "copyFile:" + (System.currentTimeMillis() - l));
        paramBitmap = paramContext;
        localObject = paramContext;
        i = getOrientation(paramUri);
        j = i;
      }
      catch (IOException paramContext)
      {
        int i;
        localObject = paramBitmap;
        Log.e("HelperPeople", "IOException caught while opening or reading stream", paramContext);
        localObject = paramBitmap;
        if (!(paramContext instanceof FileNotFoundException)) {
          continue;
        }
        localObject = paramBitmap;
        paramContext.printStackTrace();
        if (paramBitmap == null) {
          continue;
        }
        try
        {
          paramBitmap.close();
          return -1;
        }
        catch (IOException paramContext)
        {
          Log.e("HelperPeople", "IOException caught while closing stream", paramContext);
          return -1;
        }
      }
      finally
      {
        if (localObject == null) {
          break label207;
        }
      }
      try
      {
        paramContext.close();
        j = i;
        return j;
      }
      catch (IOException paramContext)
      {
        Log.e("HelperPeople", "IOException caught while closing stream", paramContext);
        return i;
      }
    }
    try
    {
      ((InputStream)localObject).close();
      label207:
      throw paramContext;
    }
    catch (IOException paramBitmap)
    {
      for (;;)
      {
        Log.e("HelperPeople", "IOException caught while closing stream", paramBitmap);
      }
    }
  }
  
  public static int getOrientation(Context paramContext, Uri paramUri)
  {
    paramContext = paramContext.getContentResolver().query(paramUri, null, null, null, null);
    int j = -1;
    if (paramContext != null) {
      i = j;
    }
    try
    {
      paramContext.moveToFirst();
      i = j;
      paramContext.getString(paramContext.getColumnIndex("_data"));
      i = j;
      int m = paramContext.getColumnIndex("orientation");
      k = j;
      if (m >= 0)
      {
        i = j;
        k = paramContext.getInt(m);
      }
      i = k;
      paramContext.close();
      return k;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      k = i;
    }
    int i = j;
    int k = j;
    if (paramUri.getPath() != null)
    {
      i = j;
      j = getOrientation(paramUri.getPath());
      return j;
    }
    return k;
  }
  
  private static int getOrientation(String paramString)
  {
    Object localObject = null;
    try
    {
      paramString = new ExifInterface(paramString);
      j = paramString.getAttributeInt("Orientation", -1);
      i = -1;
      if (j == 6)
      {
        i = 90;
        return i;
      }
    }
    catch (IOException paramString)
    {
      for (;;)
      {
        int j;
        int i;
        paramString.printStackTrace();
        paramString = localObject;
        continue;
        if (j == 3) {
          i = 180;
        } else if (j == 8) {
          i = 270;
        }
      }
    }
  }
  
  public static Class[] getParamTypes(Class paramClass, String paramString)
  {
    Object localObject = null;
    Method[] arrayOfMethod = paramClass.getDeclaredMethods();
    int i = 0;
    paramClass = localObject;
    if (i < arrayOfMethod.length)
    {
      if (!arrayOfMethod[i].getName().equals(paramString)) {}
      for (;;)
      {
        i += 1;
        break;
        paramClass = arrayOfMethod[i].getParameterTypes();
      }
    }
    return paramClass;
  }
  
  public static int getPersistParamNum(Context paramContext, PduPersister paramPduPersister, String paramString)
  {
    paramContext = getParamTypes(paramPduPersister.getClass(), paramString);
    int i = 0;
    while (i < paramContext.length)
    {
      LogUtils.e(i + " param: " + paramContext[i]);
      i += 1;
    }
    return paramContext.length;
  }
  
  public static int getProcessPID(Context paramContext)
  {
    Iterator localIterator = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses().iterator();
    while (localIterator.hasNext())
    {
      ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)localIterator.next();
      if (localRunningAppProcessInfo.processName.equals(paramContext.getPackageName())) {
        return localRunningAppProcessInfo.pid;
      }
    }
    return 0;
  }
  
  public static String getProductName()
  {
    Field[] arrayOfField = Build.class.getDeclaredFields();
    String str1 = "";
    int j = arrayOfField.length;
    int i = 0;
    for (;;)
    {
      Object localObject1 = str1;
      if (i < j) {
        localObject1 = arrayOfField[i];
      }
      try
      {
        ((Field)localObject1).setAccessible(true);
        Object localObject2 = ((Field)localObject1).get(null);
        if (((Field)localObject1).getName().equalsIgnoreCase("PRODUCT"))
        {
          localObject1 = localObject2.toString();
          return localObject1;
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        i += 1;
      }
    }
  }
  
  public static int getQuality(Context paramContext)
  {
    int i = 80;
    int j = PreferenceManager.getDefaultSharedPreferences(paramContext).getInt("pref_key_mms_message_size_new", 1048576);
    if (j == 307200) {
      i = 85;
    }
    do
    {
      return i;
      if (j == 512000) {
        return 90;
      }
      if (j == 716800) {
        return 95;
      }
    } while (j < 1048576);
    return 100;
  }
  
  public static int getScreenHeight(Context paramContext)
  {
    return ((Activity)paramContext).getApplicationContext().getResources().getDisplayMetrics().heightPixels;
  }
  
  public static int getScreenWidth()
  {
    return mWidth;
  }
  
  public static int getScreenWidth(Context paramContext)
  {
    return ((Activity)paramContext).getApplicationContext().getResources().getDisplayMetrics().widthPixels;
  }
  
  public static int getSdkVersion()
  {
    return Build.VERSION.SDK_INT;
  }
  
  public static String getStatusbarInfo(Context paramContext)
  {
    paramContext = (TelephonyManager)paramContext.getSystemService("phone");
    if (paramContext != null) {
      return paramContext.getNetworkCountryIso();
    }
    return "";
  }
  
  public static String getStringKey(Context paramContext, String paramString1, String paramString2)
  {
    SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(paramContext);
    paramContext = paramString2;
    if (localSharedPreferences != null) {
      paramContext = localSharedPreferences.getString(paramString1, paramString2);
    }
    return paramContext;
  }
  
  public static String getStringKey2(Context paramContext, String paramString1, String paramString2)
  {
    SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(paramContext);
    paramContext = paramString2;
    if (localSharedPreferences != null) {
      paramContext = localSharedPreferences.getString(testDecode64(paramString1), paramString2);
    }
    return paramContext;
  }
  
  public static String getTheme(Context paramContext)
  {
    Object localObject3 = PreferenceManager.getDefaultSharedPreferences(paramContext);
    Object localObject2 = "";
    Object localObject1 = localObject2;
    if (localObject3 != null)
    {
      localObject3 = ((SharedPreferences)localObject3).getString("pref_key_theme_name", ThemeEle.DEFAULT);
      localObject1 = localObject2;
      if (((String)localObject3).contains(".")) {
        localObject1 = localObject3;
      }
    }
    localObject2 = localObject1;
    if (TextUtils.isEmpty(localObject1)) {
      localObject2 = paramContext.getPackageName();
    }
    return localObject2;
  }
  
  public static int getThemeColor(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    int i = NewThemeManager.get().getColorNoDefault(paramString3);
    if (i != 0) {
      return i;
    }
    return getThemeColor(paramContext, getTheme(paramContext), paramString1, paramString2, paramString3);
  }
  
  public static int getThemeColor(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    i = 0;
    str1 = paramString1;
    if (TextUtils.isEmpty(paramString1) == true) {
      str1 = ConversationList.getThemeName(paramContext);
    }
    if ((mCacheColor != null) && (mCacheColor.containsKey(paramString3 + ":" + paramString4))) {
      return ((Integer)mCacheColor.get(paramString3 + ":" + paramString4)).intValue();
    }
    if (localPackageManager == null) {
      return 0;
    }
    long l = System.currentTimeMillis();
    for (;;)
    {
      try
      {
        paramString1 = localPackageManager.getResourcesForApplication(str1);
        j = paramString1.getIdentifier(paramString3 + paramString4, paramString2, str1);
        if (j <= 0) {
          continue;
        }
        j = paramString1.getColor(j);
        i = j;
      }
      catch (PackageManager.NameNotFoundException paramString1)
      {
        paramString1 = paramContext.getResources();
        int j = paramString1.getIdentifier(paramString4, paramString2, paramContext.getPackageName());
        if (j <= 0) {
          continue;
        }
        i = paramString1.getColor(j);
        continue;
        LogUtils.e("HelperPeopleRES ERROR pkg:" + str1 + " name:" + paramString4);
        continue;
      }
      if (mCacheColor != null) {
        mCacheColor.put(paramString3 + ":" + paramString4, Integer.valueOf(i));
      }
      mTotalThemeTime = (int)(mTotalThemeTime + (System.currentTimeMillis() - l));
      LogUtils.e("HelperPeople LoadRes, type:" + paramString2 + " name:" + paramString4 + " time:" + (System.currentTimeMillis() - l) + " total:" + mTotalThemeTime);
      return i;
      paramString1 = paramContext.getResources();
      j = paramString1.getIdentifier(paramString4, paramString2, paramContext.getPackageName());
      if (j > 0)
      {
        j = paramString1.getColor(j);
        i = j;
      }
      else
      {
        LogUtils.e("HelperPeopleRES ERROR pkg:" + str1 + " name:" + paramString4);
      }
    }
  }
  
  public static ColorStateList getThemeColorList(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    ColorStateList localColorStateList = NewThemeManager.get().getColorStateListNoDefault(paramString3);
    if (localColorStateList != null) {
      return localColorStateList;
    }
    return getThemeColorList(paramContext, getTheme(paramContext), paramString1, paramString2, paramString3);
  }
  
  public static ColorStateList getThemeColorList(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    localObject = null;
    str1 = paramString1;
    if (TextUtils.isEmpty(paramString1) == true) {
      str1 = ConversationList.getThemeName(paramContext);
    }
    if (localPackageManager == null) {
      return null;
    }
    long l = System.currentTimeMillis();
    for (;;)
    {
      try
      {
        paramString1 = localPackageManager.getResourcesForApplication(str1);
        i = paramString1.getIdentifier(paramString3 + paramString4, paramString2, str1);
        if (i <= 0) {
          continue;
        }
        paramString1 = paramString1.getColorStateList(i);
        paramContext = paramString1;
      }
      catch (PackageManager.NameNotFoundException paramString1)
      {
        paramString1 = paramContext.getResources();
        int i = paramString1.getIdentifier(paramString4, paramString2, paramContext.getPackageName());
        if (i <= 0) {
          continue;
        }
        paramContext = paramString1.getColorStateList(i);
        continue;
        LogUtils.e("HelperPeopleRES ERROR pkg:" + str1 + " name:" + paramString4);
        paramContext = localObject;
        continue;
      }
      mTotalThemeTime = (int)(mTotalThemeTime + (System.currentTimeMillis() - l));
      LogUtils.e("HelperPeople LoadRes, type:" + paramString2 + " name:" + paramString4 + " time:" + (System.currentTimeMillis() - l) + " total:" + mTotalThemeTime);
      return paramContext;
      paramString1 = paramContext.getResources();
      i = paramString1.getIdentifier(paramString4, paramString2, paramContext.getPackageName());
      if (i > 0)
      {
        paramString1 = paramString1.getColorStateList(i);
        paramContext = paramString1;
      }
      else
      {
        LogUtils.e("HelperPeopleRES ERROR pkg:" + str1 + " name:" + paramString4);
        paramContext = localObject;
      }
    }
  }
  
  public static float getThemeDimen(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    f1 = 0.0F;
    str1 = paramString1;
    if (TextUtils.isEmpty(paramString1) == true) {
      str1 = ConversationList.getThemeName(paramContext);
    }
    if (localPackageManager == null) {
      return 0.0F;
    }
    long l = System.currentTimeMillis();
    for (;;)
    {
      try
      {
        paramString1 = localPackageManager.getResourcesForApplication(str1);
        i = paramString1.getIdentifier(paramString3 + paramString4, paramString2, str1);
        if (i <= 0) {
          continue;
        }
        f2 = paramString1.getDimension(i);
        f1 = f2;
      }
      catch (PackageManager.NameNotFoundException paramString1)
      {
        float f2;
        paramString1 = paramContext.getResources();
        int i = paramString1.getIdentifier(paramString4, paramString2, paramContext.getPackageName());
        if (i <= 0) {
          continue;
        }
        f1 = paramString1.getDimension(i);
        continue;
        LogUtils.e("HelperPeopleRES ERROR pkg:" + str1 + " name:" + paramString4);
        continue;
      }
      mTotalThemeTime = (int)(mTotalThemeTime + (System.currentTimeMillis() - l));
      LogUtils.e("HelperPeople LoadRes, type:" + paramString2 + " name:" + paramString4 + " time:" + (System.currentTimeMillis() - l) + " total:" + mTotalThemeTime);
      return f1;
      paramString1 = paramContext.getResources();
      i = paramString1.getIdentifier(paramString4, paramString2, paramContext.getPackageName());
      if (i > 0)
      {
        f2 = paramString1.getDimension(i);
        f1 = f2;
      }
      else
      {
        LogUtils.e("HelperPeopleRES ERROR pkg:" + str1 + " name:" + paramString4);
      }
    }
  }
  
  public static int getThemeDimen(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    int i = NewThemeManager.get().getDimensionNoDefault(paramString3);
    if (i > 0) {
      return i;
    }
    return (int)getThemeDimen(paramContext, getTheme(paramContext), paramString1, paramString2, paramString3);
  }
  
  public static Drawable getThemeDrawable(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    Drawable localDrawable = NewThemeManager.get().getDrawableNoDefault(paramString3);
    if (localDrawable != null) {
      return localDrawable;
    }
    return getThemeDrawable(paramContext, getTheme(paramContext), paramString1, paramString2, paramString3);
  }
  
  public static Drawable getThemeDrawable(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4)
  {
    long l = System.currentTimeMillis();
    if ((mCacheDrawable != null) && (mCacheDrawable.containsKey(paramString3 + ":" + paramString4))) {
      return (Drawable)mCacheDrawable.get(paramString3 + ":" + paramString4);
    }
    paramContext = getThemeDrawableNoCache(paramContext, paramString1, paramString2, paramString3, paramString4);
    if ((mCacheDrawable == null) || (paramContext == null) || ((paramContext instanceof NinePatchDrawable))) {}
    for (;;)
    {
      mTotalThemeTime = (int)(mTotalThemeTime + (System.currentTimeMillis() - l));
      LogUtils.e("HelperPeople LoadRes, type:" + paramString2 + " name:" + paramString4 + " time:" + (System.currentTimeMillis() - l) + " total:" + mTotalThemeTime);
      return paramContext;
      mCacheDrawable.put(paramString3 + ":" + paramString4, paramContext);
    }
  }
  
  public static Drawable getThemeDrawableDefault(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    return getThemeDrawable(paramContext, "", paramString1, paramString2, paramString3);
  }
  
  public static Drawable getThemeDrawableNoCache(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    localObject = null;
    str1 = paramString1;
    if (TextUtils.isEmpty(paramString1) == true) {
      str1 = ConversationList.getThemeName(paramContext);
    }
    if (localPackageManager == null) {
      return null;
    }
    long l = System.currentTimeMillis();
    for (;;)
    {
      try
      {
        paramString1 = localPackageManager.getResourcesForApplication(str1);
        i = paramString1.getIdentifier(paramString3 + paramString4, paramString2, str1);
        if (i <= 0) {
          continue;
        }
        paramString1 = paramString1.getDrawable(i);
        paramContext = paramString1;
      }
      catch (PackageManager.NameNotFoundException paramString1)
      {
        if (!TextUtils.equals(ThemeEle.NO_RED_POT_THE_NAV_THEME_BTN, paramString4)) {
          continue;
        }
        return null;
        paramString1 = paramContext.getResources();
        int i = paramString1.getIdentifier(paramString4, paramString2, paramContext.getPackageName());
        if (i <= 0) {
          continue;
        }
        paramContext = paramString1.getDrawable(i);
        continue;
        LogUtils.e("HelperPeopleRES ERROR pkg:" + str1 + " name:" + paramString4);
        paramContext = localObject;
        continue;
      }
      mTotalThemeTime = (int)(mTotalThemeTime + (System.currentTimeMillis() - l));
      LogUtils.e("HelperPeople LoadRes, type:" + paramString2 + " name:" + paramString4 + " time:" + (System.currentTimeMillis() - l) + " total:" + mTotalThemeTime);
      return paramContext;
      if (TextUtils.equals(ThemeEle.NO_RED_POT_THE_NAV_THEME_BTN, paramString4)) {
        return null;
      }
      paramString1 = paramContext.getResources();
      i = paramString1.getIdentifier(paramString4, paramString2, paramContext.getPackageName());
      if (i > 0)
      {
        paramString1 = paramString1.getDrawable(i);
        paramContext = paramString1;
      }
      else
      {
        LogUtils.e("HelperPeopleRES ERROR pkg:" + str1 + " name:" + paramString4);
        paramContext = localObject;
      }
    }
  }
  
  public static Drawable getThemeDrawableNoDefault(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    Drawable localDrawable = NewThemeManager.get().getDrawableNoDefault(paramString3);
    if (localDrawable != null) {
      return localDrawable;
    }
    return getThemeDrawableNoDefault(paramContext, getTheme(paramContext), paramString1, paramString2, paramString3);
  }
  
  public static Drawable getThemeDrawableNoDefault(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    localObject = null;
    if ((mCacheDrawable != null) && (mCacheDrawable.containsKey(paramString3 + ":" + paramString4))) {
      return (Drawable)mCacheDrawable.get(paramString3 + ":" + paramString4);
    }
    String str1 = paramString1;
    if (TextUtils.isEmpty(paramString1) == true) {
      str1 = ConversationList.getThemeName(paramContext);
    }
    if (localPackageManager == null) {
      return null;
    }
    long l = System.currentTimeMillis();
    try
    {
      paramString1 = localPackageManager.getResourcesForApplication(str1);
      int i = paramString1.getIdentifier(paramString3 + paramString4, paramString2, str1);
      paramContext = localObject;
      if (i > 0) {
        paramContext = paramString1.getDrawable(i);
      }
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;)
      {
        paramContext = localObject;
      }
    }
    if ((mCacheDrawable == null) || (paramContext == null) || ((paramContext instanceof NinePatchDrawable))) {}
    for (;;)
    {
      mTotalThemeTime = (int)(mTotalThemeTime + (System.currentTimeMillis() - l));
      LogUtils.e("HelperPeople LoadRes, type:" + paramString2 + " name:" + paramString4 + " time:" + (System.currentTimeMillis() - l) + " total:" + mTotalThemeTime);
      return paramContext;
      mCacheDrawable.put(paramString3 + ":" + paramString4, paramContext);
    }
  }
  
  public static int getThemeNum(Context paramContext, String[] paramArrayOfString)
  {
    List localList = paramContext.getPackageManager().getInstalledPackages(0);
    int i = 0;
    int j = 0;
    while (j < localList.size())
    {
      PackageInfo localPackageInfo = (PackageInfo)localList.get(j);
      int m;
      if (localPackageInfo.packageName.equalsIgnoreCase(paramContext.getPackageName()))
      {
        m = i;
        j += 1;
        i = m;
      }
      else
      {
        int n = paramArrayOfString.length;
        int k = 0;
        for (;;)
        {
          m = i;
          if (k >= n) {
            break;
          }
          String str1 = paramArrayOfString[k];
          m = i;
          if (localPackageInfo.packageName.contains(str1)) {
            m = i + 1;
          }
          k += 1;
          i = m;
        }
      }
    }
    return i;
  }
  
  public static int getThreadID(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getInt("pref_key_thread_id", 0);
  }
  
  public static String getTime(Context paramContext, long paramLong)
  {
    return DateUtils.formatDateTime(paramContext, paramLong, 0x80B00 | 0x1);
  }
  
  public static String getToday(Context paramContext, long paramLong)
  {
    paramContext = Calendar.getInstance();
    paramContext.setTimeInMillis(paramLong);
    int i = paramContext.get(1);
    int j = paramContext.get(2);
    int k = paramContext.get(5);
    return i + "" + j + "" + k;
  }
  
  public static String getTommrrow(Context paramContext, long paramLong)
  {
    paramContext = Calendar.getInstance();
    paramContext.setTimeInMillis(86400000L + paramLong);
    int i = paramContext.get(1);
    int j = paramContext.get(2);
    int k = paramContext.get(5);
    return i + "" + j + "" + k;
  }
  
  public static long getTommrrowLong(Context paramContext, long paramLong)
  {
    return 86400000L + paramLong;
  }
  
  public static void hideStatusbar(Context paramContext)
  {
    if ((paramContext instanceof ComposeMessageActivity)) {}
    while (!(paramContext instanceof ConversationList)) {
      return;
    }
  }
  
  public static void init(Context paramContext)
  {
    mInstance = new HelperPeople();
    mPrefs = PreferenceManager.getDefaultSharedPreferences(paramContext);
    initCurrentDate();
    MessagingPreferenceActivity.checkNewBubble(paramContext);
    if ((paramContext instanceof Activity)) {}
    try
    {
      paramContext = ((Activity)paramContext).getApplicationContext().getResources().getDisplayMetrics();
      mWidth = paramContext.widthPixels;
      mHeight = paramContext.heightPixels;
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  private static void initCurrentDate()
  {
    Calendar localCalendar = Calendar.getInstance();
    mYear = localCalendar.get(1);
    mMonth = localCalendar.get(2);
    mDay = localCalendar.get(5);
    mWeek = localCalendar.get(7);
    mHour = localCalendar.get(11);
    mMinute = localCalendar.get(12);
    mSecond = localCalendar.get(13);
  }
  
  private static int isExistMobileNum(ArrayList<HashMap<Object, Object>> paramArrayList, String paramString)
  {
    int i = 0;
    while (i < paramArrayList.size())
    {
      if (paramString.equalsIgnoreCase((String)((HashMap)paramArrayList.get(i)).get("num"))) {
        return i;
      }
      i += 1;
    }
    return -1;
  }
  
  private static boolean isFileExist(String paramString)
  {
    try
    {
      boolean bool = new File(paramString).exists();
      if (bool) {
        return true;
      }
    }
    catch (Exception paramString) {}
    return false;
  }
  
  public static boolean isInternalTheme(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    while (paramString.contains(".")) {
      return false;
    }
    return true;
  }
  
  public static void loadBitmap(String paramString)
    throws FileNotFoundException
  {
    try
    {
      BitmapFactory.decodeStream(new FileInputStream(ConstSetting.SNAPSHOT));
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public static Uri persist(PduPersister paramPduPersister, GenericPdu paramGenericPdu, Uri paramUri, String paramString)
  {
    try
    {
      Class[] arrayOfClass = getParamTypes(paramPduPersister.getClass(), paramString);
      paramString = Class.forName("com.google.iphonestyle.mms.pdu.PduPersister").getMethod(paramString, arrayOfClass);
      paramString.setAccessible(true);
      paramPduPersister = (Uri)paramString.invoke(paramPduPersister, new Object[] { paramGenericPdu, paramUri, Boolean.valueOf(true), Boolean.valueOf(true), null });
      return paramPduPersister;
    }
    catch (Exception paramPduPersister)
    {
      paramPduPersister.printStackTrace();
    }
    return null;
  }
  
  public static Uri persistPart(PduPersister paramPduPersister, PduPart paramPduPart, long paramLong, String paramString)
  {
    try
    {
      Class[] arrayOfClass = getParamTypes(paramPduPersister.getClass(), paramString);
      paramString = Class.forName("com.google.iphonestyle.mms.pdu.PduPersister").getMethod(paramString, arrayOfClass);
      paramString.setAccessible(true);
      paramPduPersister = (Uri)paramString.invoke(paramPduPersister, new Object[] { paramPduPart, Long.valueOf(paramLong), null });
      return paramPduPersister;
    }
    catch (Exception paramPduPersister)
    {
      paramPduPersister.printStackTrace();
    }
    return null;
  }
  
  public static void playAssetsRingtone(Context paramContext, String paramString)
  {
    try
    {
      paramContext.getAssets().open(paramString);
      paramString = paramContext.getAssets().openFd(paramString);
      Log.e("notification sound", "player sound start!!\n");
      if (paramString != null)
      {
        if (player == null) {
          player = new MediaPlayer();
        }
        player.reset();
        player.setDataSource(paramString.getFileDescriptor(), paramString.getStartOffset(), paramString.getLength());
        if (((AudioManager)paramContext.getSystemService("audio")).getStreamVolume(5) != 0)
        {
          player.setAudioStreamType(5);
          player.setLooping(false);
          player.prepare();
          player.start();
          player.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
          {
            public void onCompletion(MediaPlayer paramAnonymousMediaPlayer)
            {
              Log.e("notification sound", "player sound over!!\n");
              HelperPeople.player.reset();
            }
          });
          return;
        }
        Log.e("notification sound", "getStreamVolume failed!!\n");
        return;
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      return;
    }
    Log.e("notification sound", "fd null!!\n");
  }
  
  public static void playRingtone(Context paramContext, int paramInt)
  {
    try
    {
      AssetFileDescriptor localAssetFileDescriptor = paramContext.getResources().openRawResourceFd(paramInt);
      if (localAssetFileDescriptor != null)
      {
        MediaPlayer localMediaPlayer = new MediaPlayer();
        localMediaPlayer.setDataSource(localAssetFileDescriptor.getFileDescriptor(), localAssetFileDescriptor.getStartOffset(), localAssetFileDescriptor.getLength());
        if (((AudioManager)paramContext.getSystemService("audio")).getStreamVolume(5) != 0)
        {
          localMediaPlayer.setAudioStreamType(5);
          localMediaPlayer.setLooping(false);
          localMediaPlayer.prepare();
          localMediaPlayer.start();
        }
      }
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static void playRingtone(Context paramContext, String paramString)
  {
    try
    {
      if (new File(paramString).exists())
      {
        paramString = Uri.parse(paramString);
        MediaPlayer localMediaPlayer = new MediaPlayer();
        localMediaPlayer.setDataSource(paramContext, paramString);
        if (((AudioManager)paramContext.getSystemService("audio")).getStreamVolume(5) != 0)
        {
          localMediaPlayer.setAudioStreamType(5);
          localMediaPlayer.setLooping(false);
          localMediaPlayer.prepare();
          localMediaPlayer.start();
        }
      }
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static void print_static(String paramString)
  {
    Log.e("TEST", "v=" + paramString);
  }
  
  private static void quickCopyFile(FileInputStream paramFileInputStream, String paramString)
  {
    int i = 1048576;
    Object localObject3 = null;
    ByteBuffer localByteBuffer = null;
    Object localObject2 = localByteBuffer;
    Object localObject1 = localObject3;
    for (;;)
    {
      try
      {
        FileOutputStream localFileOutputStream = new FileOutputStream(paramString);
        localObject2 = localByteBuffer;
        localObject1 = localObject3;
        paramString = paramFileInputStream.getChannel();
        localObject2 = localByteBuffer;
        localObject1 = localObject3;
        paramFileInputStream = localFileOutputStream.getChannel();
        localObject2 = paramFileInputStream;
        localObject1 = paramFileInputStream;
        if (paramString.position() >= paramString.size()) {
          continue;
        }
        localObject2 = paramFileInputStream;
        localObject1 = paramFileInputStream;
        if (paramString.size() - paramString.position() >= i) {
          continue;
        }
        localObject2 = paramFileInputStream;
        localObject1 = paramFileInputStream;
        i = (int)(paramString.size() - paramString.position());
      }
      catch (IOException paramFileInputStream)
      {
        localObject1 = localObject2;
        paramFileInputStream.printStackTrace();
        if (localObject2 == null) {
          continue;
        }
        try
        {
          if (((FileChannel)localObject2).isOpen()) {
            ((FileChannel)localObject2).close();
          }
          return;
        }
        catch (IOException paramFileInputStream)
        {
          paramFileInputStream.printStackTrace();
          return;
        }
        i = 1048576;
        continue;
        if (paramFileInputStream == null) {
          continue;
        }
        try
        {
          if (!paramFileInputStream.isOpen()) {
            continue;
          }
          paramFileInputStream.close();
          return;
        }
        catch (IOException paramFileInputStream)
        {
          paramFileInputStream.printStackTrace();
          return;
        }
      }
      finally
      {
        if (localObject1 == null) {
          break label242;
        }
      }
      localObject2 = paramFileInputStream;
      localObject1 = paramFileInputStream;
      localByteBuffer = ByteBuffer.allocateDirect(i);
      localObject2 = paramFileInputStream;
      localObject1 = paramFileInputStream;
      paramString.read(localByteBuffer);
      localObject2 = paramFileInputStream;
      localObject1 = paramFileInputStream;
      localByteBuffer.flip();
      localObject2 = paramFileInputStream;
      localObject1 = paramFileInputStream;
      paramFileInputStream.write(localByteBuffer);
      localObject2 = paramFileInputStream;
      localObject1 = paramFileInputStream;
      paramFileInputStream.force(false);
    }
    try
    {
      if (((FileChannel)localObject1).isOpen()) {
        ((FileChannel)localObject1).close();
      }
      label242:
      throw paramFileInputStream;
    }
    catch (IOException paramString)
    {
      for (;;)
      {
        paramString.printStackTrace();
      }
    }
  }
  
  public static void rate(Context paramContext, String paramString)
  {
    try
    {
      if (checkGooglePlay(paramContext))
      {
        rateMarket(paramContext, paramString);
        return;
      }
      rateBrowser(paramContext, paramString);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  private static void rateBrowser(Context paramContext, String paramString)
  {
    paramString = Uri.parse("http://market.android.com/details?id=" + paramString);
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setData(paramString);
    paramContext.startActivity(localIntent);
  }
  
  private static void rateMarket(Context paramContext, String paramString)
  {
    paramString = Uri.parse("market://details?id=" + paramString);
    Intent localIntent = new Intent();
    localIntent.setData(paramString);
    paramContext.startActivity(localIntent);
  }
  
  public static String readSDFile(String paramString)
  {
    if (!isFileExist(paramString)) {
      return "";
    }
    try
    {
      paramString = new FileInputStream(paramString);
      byte[] arrayOfByte = new byte[paramString.available()];
      if (paramString.read(arrayOfByte) > 0)
      {
        paramString = new String(arrayOfByte);
        return paramString;
      }
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return "";
  }
  
  public static String replaceEmojitail(String paramString)
  {
    String str1 = "";
    if (paramString != null) {
      str1 = Pattern.compile(Pattern.quote("️")).matcher(paramString).replaceAll("");
    }
    return str1;
  }
  
  public static void resetMaxBubbleWidth()
  {
    mMaxBubbleWidth = 0;
  }
  
  public static void saveThreadID(Context paramContext, int paramInt)
  {
    paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext).edit();
    paramContext.putInt("pref_key_thread_id", paramInt);
    paramContext.commit();
  }
  
  public static void searchMobileNum(Context paramContext, String paramString)
  {
    String str1 = "";
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    Cursor localCursor = paramContext.getContentResolver().query(ContactsContract.Data.CONTENT_URI, new String[] { "_id", "data1", "data2", "data3", "display_name" }, "contact_id=? AND mimetype='vnd.android.cursor.item/phone_v2'", new String[] { paramString }, null);
    if ((localCursor == null) || (localCursor.getCount() <= 0)) {
      return;
    }
    localCursor.moveToFirst();
    paramString = str1;
    str1 = localCursor.getString(1);
    String str2 = localCursor.getString(4);
    String str3 = localCursor.getString(3);
    paramContext = localCursor.getString(2);
    if (paramContext.equals(String.valueOf(2)))
    {
      paramContext = "mobile";
      localArrayList1.add(str1);
      HashMap localHashMap = new HashMap();
      localHashMap.put("num", str1);
      localHashMap.put("name", str2);
      localArrayList2.add(localHashMap);
    }
    for (;;)
    {
      paramString = paramString + "num:" + str1 + " name:" + str2 + " label:" + str3 + " type:" + paramContext + "\n";
      if (localCursor.moveToNext()) {
        break;
      }
      return;
      if (paramContext.equals(String.valueOf(3))) {
        paramContext = "work";
      } else if (paramContext.equals(String.valueOf(1))) {
        paramContext = "home";
      } else {
        paramContext = "other";
      }
    }
  }
  
  public static void selectMobileNum(Context paramContext, String paramString, CharSequence[] paramArrayOfCharSequence, final ArrayList<HashMap<Object, Object>> paramArrayList)
  {
    ArrayList localArrayList = new ArrayList();
    new AlertDialog.Builder(paramContext).setTitle("Choose Mobile:" + paramString).setMultiChoiceItems(paramArrayOfCharSequence, new boolean[paramArrayOfCharSequence.length], new DialogInterface.OnMultiChoiceClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt, boolean paramAnonymousBoolean)
      {
        if (paramAnonymousBoolean) {
          if (HelperPeople.isExistMobileNum(this.val$result, (String)((HashMap)paramArrayList.get(paramAnonymousInt)).get("num")) == -1) {
            this.val$result.add(paramArrayList.get(paramAnonymousInt));
          }
        }
        do
        {
          return;
          paramAnonymousInt = HelperPeople.isExistMobileNum(this.val$result, (String)((HashMap)paramArrayList.get(paramAnonymousInt)).get("num"));
        } while (paramAnonymousInt < 0);
        this.val$result.remove(paramAnonymousInt);
      }
    }).setPositiveButton("OK", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousInt = 0;
        while (paramAnonymousInt < this.val$result.size()) {
          paramAnonymousInt += 1;
        }
      }
    }).setNegativeButton("Cancel", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.dismiss();
      }
    }).create().show();
  }
  
  public static void sendExceptionText(Context paramContext, String paramString)
  {
    try
    {
      String str1 = "This is the uncaught exception of " + ConstSetting.mAPPTag + " - to " + ConstSetting.EMAIL;
      Intent localIntent = new Intent("android.intent.action.SEND");
      paramString = str1 + "\nException: " + paramString;
      localIntent.putExtra("subject", "Uncaught Exception");
      localIntent.putExtra("body", paramString);
      localIntent.putExtra("android.intent.extra.EMAIL", new String[] { "" + ConstSetting.EMAIL });
      localIntent.setType("text/plain");
      paramContext.startActivity(localIntent);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static void sendFeedback(Context paramContext)
  {
    try
    {
      String str1 = "Feedback to " + ConstSetting.mAPPTag + ": " + ConstSetting.EMAIL + ":\n\n";
      Intent localIntent = new Intent("android.intent.action.SEND");
      localIntent.putExtra("subject", "Messages feedback");
      localIntent.putExtra("body", str1);
      localIntent.putExtra("android.intent.extra.EMAIL", new String[] { "" + ConstSetting.EMAIL_DOT });
      localIntent.setType("text/plain");
      paramContext.startActivity(localIntent);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static void sendGoNativeNew(String paramString)
  {
    Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
    try
    {
      loadBitmap("test");
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public static void sendMMSLogText(Context paramContext)
  {
    MessagingPreferenceActivity.executeMmscTest(paramContext);
  }
  
  public static void sendMMSTestResult(Context paramContext, String paramString)
  {
    try
    {
      Intent localIntent = new Intent("android.intent.action.SEND");
      String str2 = getMmsLog(paramContext, paramString);
      String str1 = str2;
      if (TextUtils.isEmpty(str2)) {
        str1 = paramString;
      }
      localIntent.putExtra("subject", "MMS Test Result");
      localIntent.putExtra("body", str1);
      Log.e("HelperPeople", str1);
      localIntent.putExtra("android.intent.extra.STREAM", Uri.fromFile(paramContext.getFileStreamPath(LogUtil.LOGFILE)));
      localIntent.putExtra("android.intent.extra.EMAIL", new String[] { "" + ConstSetting.EMAIL });
      localIntent.setType("text/plain");
      paramContext.startActivity(localIntent);
      copyFile(paramContext.openFileInput(LogUtil.LOGFILE), "/sdcard/iphonemms.log");
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static void sendMMSTestResultOld(Context paramContext, String paramString)
  {
    try
    {
      String str1 = "This is the test result of MMS message in " + ConstSetting.mAPPTag + " - to " + ConstSetting.EMAIL + "\n";
      Intent localIntent = new Intent("android.intent.action.SEND");
      String str2 = getMmsLog(paramContext, paramString);
      if (TextUtils.isEmpty(str2)) {}
      for (paramString = str1 + paramString;; paramString = str1 + str2)
      {
        localIntent.putExtra("subject", "MMS Test Result");
        localIntent.putExtra("body", paramString);
        LogUtils.e(paramString);
        localIntent.putExtra("android.intent.extra.EMAIL", new String[] { "" + ConstSetting.EMAIL });
        localIntent.setType("text/plain");
        paramContext.startActivity(localIntent);
        return;
      }
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  private static void sendSnapshot(Context paramContext)
  {
    for (;;)
    {
      try
      {
        String str1 = "This is the snapshot for " + ConstSetting.mAPPTag + " - to " + ConstSetting.EMAIL;
        File localFile = new File(ConstSetting.SNAPSHOT);
        Intent localIntent = new Intent("android.intent.action.SEND");
        localIntent.putExtra("subject", "snapshot file");
        localIntent.putExtra("body", str1 + "\n\n\nSnap file path:" + ConstSetting.SNAPSHOT + "[" + getAppVersionName(paramContext) + "]");
        localIntent.putExtra("android.intent.extra.STREAM", Uri.fromFile(localFile));
        localIntent.putExtra("android.intent.extra.EMAIL", new String[] { "" + ConstSetting.EMAIL });
        if (localFile.getName().endsWith(".gz"))
        {
          localIntent.setType("application/x-gzip");
          paramContext.startActivity(localIntent);
          return;
        }
        if (localFile.getName().endsWith(".txt")) {
          localIntent.setType("text/plain");
        } else {
          localIntent.setType("application/octet-stream");
        }
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
        return;
      }
    }
  }
  
  public static void sentEmojiText(Context paramContext, String paramString)
  {
    try
    {
      String str1 = "This is the emoji text for " + ConstSetting.mAPPTag + " - to " + ConstSetting.EMAIL;
      Intent localIntent = new Intent("android.intent.action.SEND");
      paramString = str1 + "\nEmoji content: " + dumpHexStr(paramString);
      localIntent.putExtra("subject", "Emoji text");
      localIntent.putExtra("body", paramString);
      localIntent.putExtra("android.intent.extra.EMAIL", new String[] { "" + ConstSetting.EMAIL });
      localIntent.setType("text/plain");
      paramContext.startActivity(localIntent);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  private static void setBlockOptions(Context paramContext)
  {
    paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext).edit();
    paramContext.putBoolean("pref_key_block_other_smsapp", true);
    paramContext.putBoolean("pref_key_block_other_smsapp_mms", true);
    paramContext.commit();
  }
  
  public static void setCurrentTheme(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(paramContext);
    if (localSharedPreferences == null) {
      return;
    }
    if (TextUtils.isEmpty(paramString)) {
      paramContext = ThemeEle.DEFAULT;
    }
    for (;;)
    {
      localSharedPreferences.edit().putString("pref_key_theme_name", paramContext).commit();
      return;
      paramContext = paramString;
      if (paramString.contains("."))
      {
        ThemeEle.PREFIX = "";
        paramContext = paramString;
      }
    }
  }
  
  public static void setDefaultSMS(Context paramContext)
  {
    if (Build.VERSION.SDK_INT >= 19)
    {
      setDefaultSms(paramContext);
      return;
    }
    try
    {
      Intent localIntent = new Intent("android.intent.action.MAIN");
      localIntent.addCategory("android.intent.category.DEFAULT");
      localIntent.setType("vnd.android-dir/mms-sms");
      localIntent.addFlags(4194304);
      paramContext.startActivity(localIntent);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static void setDefaultSms(Context paramContext)
  {
    Intent localIntent = new Intent("android.provider.Telephony.ACTION_CHANGE_DEFAULT");
    localIntent.putExtra("package", paramContext.getPackageName());
    localIntent.setFlags(268435456);
    paramContext.startActivity(localIntent);
    setBlockOptions(paramContext);
  }
  
  public static Intent setDynamicIntent(Context paramContext, Intent paramIntent, String[] paramArrayOfString1, String[] paramArrayOfString2, String paramString1, String paramString2)
  {
    paramIntent.putExtra("dynamicarrayvalue", paramArrayOfString1);
    paramIntent.putExtra("dynamicarrayname", paramArrayOfString2);
    paramIntent.putExtra("key", paramString1);
    paramIntent.putExtra("defaultvalue", findDefaultValue(paramString1));
    paramIntent.putExtra("dialogtitle", paramString2);
    return paramIntent;
  }
  
  public static void setLanguage(Context paramContext, String paramString)
  {
    paramContext = paramContext.getResources();
    DisplayMetrics localDisplayMetrics = paramContext.getDisplayMetrics();
    Configuration localConfiguration = paramContext.getConfiguration();
    localConfiguration.locale = new Locale(paramString.toLowerCase());
    paramContext.updateConfiguration(localConfiguration, localDisplayMetrics);
  }
  
  public static void setLanguage_error(Context paramContext, String paramString)
  {
    paramString = new Locale(paramString);
    Locale.setDefault(paramString);
    Configuration localConfiguration = new Configuration();
    localConfiguration.locale = paramString;
    paramContext.getResources().updateConfiguration(localConfiguration, null);
  }
  
  public static void setThemeBubbleResMap(Context paramContext)
  {
    HashMap localHashMap = new HashMap();
    String str1;
    if (NewThemeManager.get().isNewTheme())
    {
      NewThemeManager.get();
      str1 = NewThemeManager.getPkgName();
    }
    try
    {
      if (NewThemeManager.get().isNewTheme()) {
        break label224;
      }
      str2 = ThemeEle.THE_THREAD_BUBBLE_IN_TEXTCOLOR;
      label38:
      localHashMap.put("onemmstheme_conv_in_text_color", Integer.valueOf(BubbleUtils.getColorId(paramContext, str1, str2)));
      if (NewThemeManager.get().isNewTheme()) {
        break label254;
      }
      str2 = ThemeEle.THE_THREAD_BUBBLE_OUT_TEXTCOLOR;
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
        paramContext = new HashMap();
        continue;
        String str2 = "onemmstheme_conv_out_text_color";
        continue;
        str2 = "onemmstheme_conv_in_bubble";
        continue;
        str2 = "onemmstheme_conv_out_bubble";
        continue;
        str2 = "onemmstheme_conv_in_bubble_no_arrow";
      }
    }
    localHashMap.put("onemmstheme_conv_out_text_color", Integer.valueOf(BubbleUtils.getColorId(paramContext, str1, str2)));
    if (!NewThemeManager.get().isNewTheme())
    {
      str2 = ThemeEle.THE_THREAD_BUBBLE_IN;
      localHashMap.put("onemmstheme_conv_in_bubble", Integer.valueOf(BubbleUtils.getDrawableId(paramContext, str1, str2)));
      if (NewThemeManager.get().isNewTheme()) {
        break label268;
      }
      str2 = ThemeEle.THE_THREAD_BUBBLE_OUT;
      localHashMap.put("onemmstheme_conv_out_bubble", Integer.valueOf(BubbleUtils.getDrawableId(paramContext, str1, str2)));
      if (NewThemeManager.get().isNewTheme()) {
        break label275;
      }
      str2 = ThemeEle.THE_THREAD_BUBBLE_IN;
      localHashMap.put("onemmstheme_conv_in_bubble_no_arrow", Integer.valueOf(BubbleUtils.getDrawableId(paramContext, str1, str2)));
      if (!NewThemeManager.get().isNewTheme()) {}
      for (str2 = ThemeEle.THE_THREAD_BUBBLE_OUT;; str2 = "onemmstheme_conv_out_bubble_no_arrow")
      {
        localHashMap.put("onemmstheme_conv_out_bubble_no_arrow", Integer.valueOf(BubbleUtils.getDrawableId(paramContext, str1, str2)));
        paramContext = localHashMap;
        BaseMessageApplication.getBaseApplication().setResMap(str1, paramContext);
        return;
        str1 = getTheme(paramContext);
        break;
        label224:
        str2 = "onemmstheme_conv_in_text_color";
        break label38;
      }
    }
  }
  
  public static void showStatusbar(Context paramContext)
  {
    if ((paramContext instanceof ComposeMessageActivity)) {}
    while (!(paramContext instanceof ConversationList)) {
      return;
    }
  }
  
  public static void startLogcat(Context paramContext) {}
  
  public static void test64code()
  {
    int i = 0;
    while (i < new String[0].length) {
      i += 1;
    }
  }
  
  public static String testDecode64(String paramString)
  {
    return new String(Base64.decode(paramString, 0));
  }
  
  private static String translate_country(Context paramContext, String[] paramArrayOfString, Field[] paramArrayOfField, String paramString, int paramInt, boolean paramBoolean)
  {
    Object localObject1 = "<resources xmlns:xliff=\"urn:oasis:names:tc:xliff:document:1.2\">\n" + paramString + "\n";
    setLanguage(paramContext, paramString);
    int i = 0;
    for (;;)
    {
      Object localObject2;
      try
      {
        if (i < paramArrayOfField.length)
        {
          int j = getLocalResourceId(paramContext, "string", paramArrayOfField[i].getName());
          if ((paramInt != -1) && (j < paramInt))
          {
            localObject2 = localObject1;
          }
          else
          {
            String str1 = getLocalResourceString(paramContext, "string", paramArrayOfField[i].getName());
            if (str1 != null)
            {
              localObject2 = localObject1;
              if (str1.equalsIgnoreCase(paramArrayOfString[i]) != true) {}
            }
            else if (paramBoolean == true)
            {
              localObject2 = TranslateUtil.translate(paramArrayOfString[i], "en", paramString);
              localObject2 = "\t<string name=\"" + paramArrayOfField[i].getName() + "\">\"" + (String)localObject2 + "\"</string>\n";
              Log.e("Translate", "result:" + (String)localObject2);
              localObject2 = (String)localObject1 + (String)localObject2;
            }
            else
            {
              localObject2 = paramArrayOfString[i];
              continue;
            }
          }
        }
        else
        {
          return (String)localObject1 + "</resources>";
        }
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
      }
      i += 1;
      localObject1 = localObject2;
    }
  }
  
  private static String translate_country_2(Context paramContext, String[] paramArrayOfString, Field[] paramArrayOfField, String paramString, int paramInt, boolean paramBoolean)
  {
    Object localObject1 = "<resources xmlns:xliff=\"urn:oasis:names:tc:xliff:document:1.2\">\n" + paramString + "\n";
    setLanguage(paramContext, paramString);
    int i = 0;
    for (;;)
    {
      Object localObject2;
      try
      {
        if (i < paramArrayOfField.length)
        {
          int j = getLocalResourceId(paramContext, "string", paramArrayOfField[i].getName());
          if ((paramInt != -1) && (j < paramInt))
          {
            localObject2 = localObject1;
          }
          else
          {
            String str1 = getLocalResourceString(paramContext, "string", paramArrayOfField[i].getName());
            if (str1 != null)
            {
              localObject2 = localObject1;
              if (str1.equalsIgnoreCase(paramArrayOfString[i]) != true) {}
            }
            else if (paramBoolean == true)
            {
              localObject2 = TranslateUtil.translate(paramArrayOfString[i], "en", paramString);
              localObject2 = "\t<string name=\"" + paramArrayOfField[i].getName() + "\">\"" + (String)localObject2 + "\"</string>\n";
              Log.e("Translate", "result:" + (String)localObject2);
              localObject2 = (String)localObject1 + (String)localObject2;
            }
            else
            {
              localObject2 = str1;
              continue;
            }
          }
        }
        else
        {
          return (String)localObject1 + "</resources>";
        }
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
      }
      i += 1;
      localObject1 = localObject2;
    }
  }
  
  public static void translate_newadd(Context paramContext)
  {
    Field[] arrayOfField = R.string.class.getDeclaredFields();
    String[] arrayOfString = new String[arrayOfField.length];
    setLanguage(paramContext, "en");
    int i = 0;
    while (i < arrayOfField.length)
    {
      arrayOfString[i] = new String(getLocalResourceString(paramContext, "string", arrayOfField[i].getName()));
      i += 1;
    }
    i = getLocalResourceId(paramContext, "string", "pref_title_bubble_setting");
    String str1 = "" + translate_country(paramContext, arrayOfString, arrayOfField, "fr", i, true);
    str1 = str1 + translate_country(paramContext, arrayOfString, arrayOfField, "es", i, true);
    str1 = str1 + translate_country(paramContext, arrayOfString, arrayOfField, "de", i, true);
    str1 = str1 + translate_country(paramContext, arrayOfString, arrayOfField, "ja", i, true);
    str1 = str1 + translate_country(paramContext, arrayOfString, arrayOfField, "ko", i, true);
    str1 = str1 + translate_country(paramContext, arrayOfString, arrayOfField, "ru", i, true);
    str1 = str1 + translate_country(paramContext, arrayOfString, arrayOfField, "sv", i, true);
    str1 = str1 + translate_country(paramContext, arrayOfString, arrayOfField, "ar", i, true);
    writeSDFile("/sdcard/string-newadd.xml", str1 + translate_country(paramContext, arrayOfString, arrayOfField, "it", i, true));
  }
  
  public static boolean updatePart(PduPersister paramPduPersister, PduBody paramPduBody, Uri paramUri, String paramString)
  {
    try
    {
      Class[] arrayOfClass = getParamTypes(paramPduPersister.getClass(), paramString);
      paramString = Class.forName("com.google.iphonestyle.mms.pdu.PduPersister").getMethod(paramString, arrayOfClass);
      paramString.setAccessible(true);
      paramString.invoke(paramPduPersister, new Object[] { paramUri, paramPduBody, null });
      return true;
    }
    catch (Exception paramPduPersister)
    {
      paramPduPersister.printStackTrace();
    }
    return false;
  }
  
  public static void writeSDFile(String paramString1, String paramString2)
  {
    try
    {
      paramString1 = new FileOutputStream(paramString1);
      paramString1.write(paramString2.getBytes());
      paramString1.close();
      return;
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
  }
  
  public Object Load(Context paramContext, String paramString1, String paramString2, Object[] paramArrayOfObject)
  {
    try
    {
      paramContext = Class.forName(paramString1);
      paramContext.getConstructor(new Class[] { Context.class }).newInstance(new Object[] { Context.class });
      paramContext.getMethod(paramString2, getParamTypes(paramContext, paramString2)).setAccessible(true);
      return null;
    }
    catch (Exception paramContext)
    {
      System.err.println(paramContext);
    }
    return null;
  }
  
  public void doSnapshot(final Activity paramActivity)
  {
    PreferenceManager.getDefaultSharedPreferences(paramActivity);
    Object localObject = new AlertDialog.Builder(paramActivity);
    final ChoiceOnClickListener localChoiceOnClickListener = new ChoiceOnClickListener();
    ((AlertDialog.Builder)localObject).setSingleChoiceItems(mSnapshotSelect, 0, localChoiceOnClickListener);
    ((AlertDialog.Builder)localObject).setTitle(getLocalResourceString(paramActivity, "string", "attach_snapshot"));
    ((AlertDialog.Builder)localObject).setPositiveButton("OK", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousInt = localChoiceOnClickListener.getWhich();
        Object localObject;
        if (paramAnonymousInt == 0)
        {
          localObject = HelperPeople.access$000();
          if (localObject != null)
          {
            localObject = (String)localObject + HelperPeople.access$100() + ".png";
            HelperPeople.getCurrentSnapshot(paramActivity, (String)localObject);
            Toast.makeText(paramActivity, "Snapshot:" + (String)localObject, 2000).show();
            localObject = Uri.parse("file://" + (String)localObject);
            paramActivity.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", (Uri)localObject));
          }
        }
        for (;;)
        {
          paramAnonymousDialogInterface.dismiss();
          return;
          if (paramAnonymousInt == 1)
          {
            localObject = MyTempFileProvider.getScrapPath(paramActivity);
            HelperPeople.getCurrentSnapshot(paramActivity, (String)localObject);
            if ((paramActivity instanceof ComposeMessageActivity)) {
              paramActivity.finish();
            }
            paramActivity.startActivity(ComposeMessageActivity.createIntent(paramActivity, -2L));
          }
          else if (paramAnonymousInt == 2)
          {
            HelperPeople.getCurrentSnapshot(paramActivity);
            HelperPeople.sendSnapshot(paramActivity);
          }
        }
      }
    });
    ((AlertDialog.Builder)localObject).setNegativeButton("Cancel", null);
    localObject = ((AlertDialog.Builder)localObject).create();
    hideStatusbar(paramActivity);
    ((Dialog)localObject).show();
    ((Dialog)localObject).setOnDismissListener(new DialogInterface.OnDismissListener()
    {
      public void onDismiss(DialogInterface paramAnonymousDialogInterface)
      {
        HelperPeople.showStatusbar(paramActivity);
      }
    });
  }
  
  public void print(int paramInt)
  {
    Log.e("TEST", "v=" + paramInt);
  }
  
  public void print(String paramString)
  {
    Log.e("TEST", "v=" + paramString);
    try
    {
      throw new NullPointerException();
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  class ChoiceOnClickListener
    implements DialogInterface.OnClickListener
  {
    private int which = 0;
    
    ChoiceOnClickListener() {}
    
    public int getWhich()
    {
      return this.which;
    }
    
    public void onClick(DialogInterface paramDialogInterface, int paramInt)
    {
      this.which = paramInt;
    }
  }
}
