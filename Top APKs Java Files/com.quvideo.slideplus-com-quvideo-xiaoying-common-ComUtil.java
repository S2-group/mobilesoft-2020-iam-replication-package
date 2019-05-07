package com.quvideo.xiaoying.common;

import android.app.Activity;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.database.Cursor;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.OnScanCompletedListener;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore.Video.Media;
import android.support.v4.content.LocalBroadcastManager;
import android.text.InputFilter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.TypefaceSpan;
import android.widget.TextView;
import android.widget.Toast;
import com.quvideo.slideplus.app.sns.SnsResItem;
import com.quvideo.slideplus.model.ExportAnimResModel;
import com.quvideo.slideplus.util.EditUtils;
import com.quvideo.slideplus.util.ExportUtils;
import com.quvideo.slideplus.util.MediaExtendUtils;
import com.quvideo.slideplus.util.UICommonUtils;
import com.quvideo.xiaoying.datacenter.SocialConstDef;
import com.quvideo.xiaoying.util.AppContext;
import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class ComUtil
{
  private static String dWA = "Xz75qh84fogl9abCdeIjk2nPr3s6T1Umv0wy";
  
  public ComUtil() {}
  
  private static void a(MSize paramMSize)
  {
    paramMSize.width ^= paramMSize.height;
    paramMSize.height ^= paramMSize.width;
    paramMSize.width ^= paramMSize.height;
  }
  
  private static void b(Activity paramActivity, String paramString1, String paramString2)
  {
    try
    {
      Intent localIntent = new Intent("android.intent.action.SEND");
      localIntent.putExtra("android.intent.extra.STREAM", Uri.parse(paramString2));
      localIntent.setType("video/mp4");
      if ("com.instagram.android".equals(paramString1)) {
        localIntent.putExtra("android.intent.extra.TEXT", "#SlidePlus Made with @SlidePlus");
      }
      if (TextUtils.isEmpty(paramString1))
      {
        paramActivity.startActivity(Intent.createChooser(localIntent, paramActivity.getResources().getString(2131624601)));
        return;
      }
      if (paramString1.equals("xiaoying.custom.email"))
      {
        localIntent.setType("message/rfc822");
        localIntent.putExtra("android.intent.extra.SUBJECT", "share my slideshow");
        paramActivity.startActivity(localIntent);
        return;
      }
      localIntent.setPackage(paramString1);
      paramActivity.startActivity(localIntent);
      return;
    }
    catch (Throwable paramString1)
    {
      Toast.makeText(paramActivity, 2131624840, 0).show();
      paramString1.printStackTrace();
    }
  }
  
  public static MSize calcStreamSize4ImportVideo(MSize paramMSize1, MSize paramMSize2, boolean paramBoolean)
  {
    if (paramBoolean) {
      a(paramMSize2);
    }
    paramMSize1 = getTargetFitSize(new MSize(paramMSize2.width, paramMSize2.height), paramMSize1);
    MSize localMSize = new MSize(paramMSize1.width, paramMSize1.height);
    if (paramMSize1.width * paramMSize1.height > paramMSize2.width * paramMSize2.height)
    {
      localMSize.width = ExportUtils.calcAlignValue(paramMSize2.width, 4);
      localMSize.height = ExportUtils.calcAlignValue(paramMSize2.height, 4);
    }
    return localMSize;
  }
  
  public static MSize calcSurfaceSize(MSize paramMSize1, MSize paramMSize2)
  {
    MSize localMSize = new MSize();
    paramMSize2 = ExportUtils.calc16ByteAlignSize(calculateSurfaceSize(paramMSize1, paramMSize2, true));
    paramMSize1 = localMSize;
    if (paramMSize2 != null) {
      paramMSize1 = new MSize(paramMSize2.width, paramMSize2.height);
    }
    return paramMSize1;
  }
  
  public static MSize calculateSurfaceSize(MSize paramMSize1, MSize paramMSize2, boolean paramBoolean)
  {
    Object localObject = new MSize(640, 480);
    if ((paramMSize1 != null) && (paramMSize1.width > 0) && (paramMSize1.height > 0))
    {
      if ((paramMSize2 != null) && (paramMSize2.width > 0) && (paramMSize2.height > 0))
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("calculateSurfaceSize: input previewSize = ");
        ((StringBuilder)localObject).append(paramMSize1);
        ((StringBuilder)localObject).append(" input screenSize=");
        ((StringBuilder)localObject).append(paramMSize2);
        LogUtils.e("Util", ((StringBuilder)localObject).toString());
        if (paramBoolean) {
          paramMSize1 = new MSize(paramMSize1.width, paramMSize1.height);
        } else if (paramMSize1.width * paramMSize1.height > 691200) {
          paramMSize1 = new MSize(720, 960);
        } else {
          paramMSize1 = new MSize(paramMSize1.height, paramMSize1.width);
        }
        paramMSize1 = EditUtils.getFitInSize(paramMSize1, paramMSize2);
        ExportUtils.calc16ByteAlignSize(paramMSize1);
        paramMSize2 = new StringBuilder();
        paramMSize2.append("calculateSurfaceSize: output surface size = ");
        paramMSize2.append(paramMSize1);
        LogUtils.e("Util", paramMSize2.toString());
        return paramMSize1;
      }
      LogUtils.e("Util", "calculateSurfaceSize: input screenSize error");
      return localObject;
    }
    LogUtils.e("Util", "calculateSurfaceSize: input previewSize error");
    return localObject;
  }
  
  public static boolean checkExportInBackground()
  {
    int i = CpuFeatures.getCpuNumber();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("getCpuNumber : ");
    localStringBuilder.append(i);
    LogUtils.i("Util", localStringBuilder.toString());
    return false;
  }
  
  public static long createMagicCode(Activity paramActivity)
  {
    return paramActivity.getApplicationContext().hashCode();
  }
  
  public static void doFacebookFollow(Activity paramActivity)
  {
    try
    {
      if (getResolveInfoByPackagename(paramActivity.getPackageManager(), "com.facebook.katana", true) == null)
      {
        paramActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://www.facebook.com/")));
        return;
      }
      Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse("fb://page/409588592574897"));
      localIntent.setPackage("com.facebook.katana");
      paramActivity.startActivity(localIntent);
      return;
    }
    catch (Exception paramActivity)
    {
      paramActivity.printStackTrace();
    }
  }
  
  public static void doInstagramFollow(Activity paramActivity)
  {
    try
    {
      if (getResolveInfoByPackagename(paramActivity.getPackageManager(), "com.instagram.android", true) == null)
      {
        paramActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://instagram.com/slideplus")));
        return;
      }
      Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://instagram.com/_u/slideplus"));
      localIntent.setPackage("com.instagram.android");
      paramActivity.startActivity(localIntent);
      return;
    }
    catch (Exception paramActivity)
    {
      paramActivity.printStackTrace();
    }
  }
  
  public static int getCharacterNum(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return 0;
    }
    return paramString.length() + getChineseNum(paramString);
  }
  
  public static int getChineseNum(String paramString)
  {
    paramString = paramString.toCharArray();
    int i = 0;
    int k;
    for (int j = 0; i < paramString.length; j = k)
    {
      k = j;
      if ((char)(byte)paramString[i] != paramString[i]) {
        k = j + 1;
      }
      i += 1;
    }
    return j;
  }
  
  public static String getDeviceId(Context paramContext)
  {
    try
    {
      Cursor localCursor = paramContext.getContentResolver().query(SocialConstDef.getTableUri("AppGeneral"), new String[] { "value" }, "key = ?", new String[] { "DeviceGUID" }, null);
      paramContext = null;
      Object localObject = null;
      if (localCursor != null)
      {
        paramContext = localObject;
        if (localCursor.moveToFirst()) {
          paramContext = localCursor.getString(0);
        }
        localCursor.close();
      }
      boolean bool = TextUtils.isEmpty(paramContext);
      if (!bool) {
        return paramContext;
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return "";
  }
  
  public static String getDurationStrForUserBehavior(long paramLong)
  {
    if (paramLong < 10000L) {
      return "<10s";
    }
    if ((paramLong >= 10000L) && (paramLong <= 30000L)) {
      return "15s-30s";
    }
    if ((paramLong > 30000L) && (paramLong <= 60000L)) {
      return "30s-1m";
    }
    if ((paramLong > 60000L) && (paramLong <= 120000L)) {
      return "1m-2m";
    }
    return ">2m";
  }
  
  public static InputFilter getEditTextFileter(int paramInt)
  {
    return new ComUtil.3(paramInt, paramInt);
  }
  
  public static ExportAnimResModel getExportRes(SnsResItem paramSnsResItem)
  {
    ExportAnimResModel localExportAnimResModel = new ExportAnimResModel();
    localExportAnimResModel.mColorId = 2131034141;
    localExportAnimResModel.mIconDrawableId = 2131165308;
    if (paramSnsResItem != null)
    {
      int i = paramSnsResItem.iconFlag;
      if (i != 4)
      {
        if (i != 7)
        {
          if (i != 11)
          {
            if (i != 26)
            {
              if (i != 28)
              {
                if (i != 38)
                {
                  if (i != 1009)
                  {
                    switch (i)
                    {
                    default: 
                      switch (i)
                      {
                      default: 
                        localExportAnimResModel.mColorId = 2131034141;
                        localExportAnimResModel.mIconDrawableId = 2131165308;
                        return localExportAnimResModel;
                      case 1001: 
                        localExportAnimResModel.mColorId = 2131034141;
                        localExportAnimResModel.mIconDrawableId = 2131165308;
                        return localExportAnimResModel;
                      }
                      localExportAnimResModel.mColorId = 2131034141;
                      localExportAnimResModel.mIconDrawableId = 2131165308;
                      return localExportAnimResModel;
                    case 33: 
                      localExportAnimResModel.mColorId = 2131034140;
                      localExportAnimResModel.mIconDrawableId = 2131165307;
                      return localExportAnimResModel;
                    case 32: 
                      localExportAnimResModel.mColorId = 2131034146;
                      localExportAnimResModel.mIconDrawableId = 2131165314;
                      return localExportAnimResModel;
                    }
                    localExportAnimResModel.mColorId = 2131034142;
                    localExportAnimResModel.mIconDrawableId = 2131165309;
                    return localExportAnimResModel;
                  }
                  localExportAnimResModel.mColorId = 2131034141;
                  localExportAnimResModel.mIconDrawableId = 2131165308;
                  return localExportAnimResModel;
                }
                localExportAnimResModel.mColorId = 2131034143;
                localExportAnimResModel.mIconDrawableId = 2131165310;
                return localExportAnimResModel;
              }
              localExportAnimResModel.mColorId = 2131034139;
              localExportAnimResModel.mIconDrawableId = 2131165306;
              return localExportAnimResModel;
            }
            localExportAnimResModel.mColorId = 2131034147;
            localExportAnimResModel.mIconDrawableId = 2131165315;
            return localExportAnimResModel;
          }
          localExportAnimResModel.mColorId = 2131034144;
          localExportAnimResModel.mIconDrawableId = 2131165312;
          return localExportAnimResModel;
        }
        localExportAnimResModel.mColorId = 2131034145;
        localExportAnimResModel.mIconDrawableId = 2131165313;
        return localExportAnimResModel;
      }
      localExportAnimResModel.mColorId = 2131034138;
      localExportAnimResModel.mIconDrawableId = 2131165305;
    }
    return localExportAnimResModel;
  }
  
  public static String getFileExtFromAbPath(String paramString)
  {
    if (!TextUtils.isEmpty(paramString)) {
      try
      {
        int i = paramString.lastIndexOf(".");
        if (i > 0)
        {
          paramString = paramString.substring(i);
          return paramString;
        }
      }
      catch (Exception paramString)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("exception ex=");
        localStringBuilder.append(paramString.getMessage());
        LogUtils.e("Util", localStringBuilder.toString());
      }
    }
    return "";
  }
  
  public static String getFileNameFromAbPath(String paramString)
  {
    if (!TextUtils.isEmpty(paramString)) {
      try
      {
        int i = paramString.lastIndexOf(File.separator) + 1;
        int j = paramString.lastIndexOf(".");
        if ((i > 0) && (j > 0) && (i < j))
        {
          paramString = paramString.substring(i, j);
          return paramString;
        }
      }
      catch (Exception paramString)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("exception ex=");
        localStringBuilder.append(paramString.getMessage());
        LogUtils.e("Util", localStringBuilder.toString());
      }
    }
    return "";
  }
  
  public static String getFileParentPath(String paramString)
  {
    if (!TextUtils.isEmpty(paramString)) {
      try
      {
        int i = paramString.lastIndexOf(File.separator);
        if (i > 0)
        {
          localStringBuilder = new StringBuilder();
          localStringBuilder.append(paramString.substring(0, i));
          localStringBuilder.append("/");
          paramString = localStringBuilder.toString();
          return paramString;
        }
      }
      catch (Exception paramString)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("exception ex=");
        localStringBuilder.append(paramString.getMessage());
        LogUtils.e("Util", localStringBuilder.toString());
      }
    }
    return "";
  }
  
  public static AppContext getInitedAppContext(Context paramContext)
  {
    paramContext = new AppContext(paramContext);
    paramContext.init();
    return paramContext;
  }
  
  public static int getLockButtonResId(int paramInt)
  {
    if (paramInt == 21) {
      return 2131623983;
    }
    if (paramInt != 22)
    {
      if (paramInt == 23) {
        return 2131624656;
      }
      return 2131624656;
    }
    return 2131624656;
  }
  
  public static String getLockDes(Context paramContext, int paramInt)
  {
    if (paramInt == 21) {
      return paramContext.getString(2131623980);
    }
    if (paramInt == 22) {
      return paramContext.getString(2131623982, new Object[] { "Facebook" });
    }
    if (paramInt == 23) {
      return paramContext.getString(2131623982, new Object[] { "Instagram" });
    }
    return paramContext.getString(2131623980);
  }
  
  public static ResolveInfo getResolveInfoByPackagename(PackageManager paramPackageManager, String paramString, boolean paramBoolean)
  {
    Object localObject = new Intent("android.intent.action.SEND");
    if (paramBoolean) {
      ((Intent)localObject).setType("video/*");
    } else {
      ((Intent)localObject).setType("text/plain");
    }
    ((Intent)localObject).putExtra("android.intent.extra.STREAM", Uri.fromFile(new File("")));
    paramPackageManager = paramPackageManager.queryIntentActivities((Intent)localObject, 65536);
    int j = paramPackageManager.size();
    int i = 0;
    while (i < j)
    {
      localObject = (ResolveInfo)paramPackageManager.get(i);
      if (((ResolveInfo)localObject).activityInfo.packageName.equals(paramString)) {
        return localObject;
      }
      i += 1;
    }
    return null;
  }
  
  public static MSize getTargetFitSize(MSize paramMSize1, MSize paramMSize2)
  {
    if (paramMSize1 == null) {
      return null;
    }
    int i;
    if (paramMSize1.width > paramMSize1.height) {
      i = 1;
    } else {
      i = 0;
    }
    int j;
    if (i != 0) {
      j = paramMSize1.width;
    } else {
      j = paramMSize1.height;
    }
    int k;
    if (i != 0) {
      k = paramMSize1.height;
    } else {
      k = paramMSize1.width;
    }
    paramMSize1 = EditUtils.getFitInSize(new MSize(j, k), paramMSize2);
    ExportUtils.calc16ByteAlignSize(paramMSize1);
    paramMSize1 = ExportUtils.calc16ByteAlignSize(paramMSize1);
    if (i == 0)
    {
      i = paramMSize1.width;
      paramMSize1.width = paramMSize1.height;
      paramMSize1.height = i;
    }
    return paramMSize1;
  }
  
  public static String getUmengBitrate(long paramLong, boolean paramBoolean)
  {
    if (paramLong <= 0L) {
      return "0";
    }
    if (paramBoolean)
    {
      paramLong /= 1000L;
      if (paramLong < 1000L) {
        return "<1m";
      }
      if ((paramLong >= 1000L) && (paramLong < 2000L)) {
        return "1m-2m";
      }
      if ((paramLong >= 2000L) && (paramLong < 3000L)) {
        return "2m-3m";
      }
      if ((paramLong >= 3000L) && (paramLong <= 4000L)) {
        return "3m-4m";
      }
      return ">4.0m";
    }
    paramLong /= 1000L;
    if (paramLong < 500L) {
      return "<0.5m";
    }
    if ((paramLong >= 500L) && (paramLong < 1000L)) {
      return "0.5m-1m";
    }
    if ((paramLong >= 1000L) && (paramLong < 1500L)) {
      return "1m-1.5m";
    }
    if ((paramLong >= 1500L) && (paramLong <= 2000L)) {
      return "1.5m-2.0m";
    }
    return ">2.0m";
  }
  
  public static String getUmengVideoSize(long paramLong)
  {
    if (paramLong <= 0L) {
      return "0";
    }
    paramLong /= 1000000L;
    if (paramLong < 1L) {
      return "<1M";
    }
    if ((paramLong >= 1L) && (paramLong < 5L)) {
      return "1-5M";
    }
    if ((paramLong >= 5L) && (paramLong < 10L)) {
      return "5M-10M";
    }
    if ((paramLong >= 10L) && (paramLong < 50L)) {
      return "10M-50M";
    }
    if ((paramLong >= 50L) && (paramLong < 100L)) {
      return "50M-100M";
    }
    return ">100M";
  }
  
  public static String getUrlPage(String paramString)
  {
    String[] arrayOfString = paramString.split("[?]");
    String str = paramString;
    if (paramString.length() > 0)
    {
      str = paramString;
      if (arrayOfString.length > 1)
      {
        str = paramString;
        if (arrayOfString[0] != null) {
          str = arrayOfString[0];
        }
      }
    }
    return str;
  }
  
  public static String getUrlRelativePath(String paramString)
  {
    try
    {
      paramString = new URI(paramString).getPath();
      return paramString;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return "";
  }
  
  /* Error */
  public static String getVideoContentUriFromFilePath(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 261	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   4: astore_0
    //   5: ldc_w 516
    //   8: invokestatic 521	android/provider/MediaStore$Video$Media:getContentUri	(Ljava/lang/String;)Landroid/net/Uri;
    //   11: astore 4
    //   13: iconst_1
    //   14: anewarray 58	java/lang/String
    //   17: astore 5
    //   19: aload 5
    //   21: iconst_0
    //   22: ldc_w 523
    //   25: aastore
    //   26: aload_0
    //   27: aload 4
    //   29: aload 5
    //   31: ldc_w 525
    //   34: iconst_1
    //   35: anewarray 58	java/lang/String
    //   38: dup
    //   39: iconst_0
    //   40: aload_1
    //   41: aastore
    //   42: aconst_null
    //   43: invokevirtual 280	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   46: astore_1
    //   47: aload_1
    //   48: astore_0
    //   49: aload_1
    //   50: invokeinterface 285 1 0
    //   55: pop
    //   56: aload_1
    //   57: astore_0
    //   58: aload_1
    //   59: aload_1
    //   60: aload 5
    //   62: iconst_0
    //   63: aaload
    //   64: invokeinterface 528 2 0
    //   69: invokeinterface 532 2 0
    //   74: lstore_2
    //   75: aload_1
    //   76: astore_0
    //   77: aload_1
    //   78: invokeinterface 289 1 0
    //   83: lload_2
    //   84: ldc2_w 533
    //   87: lcmp
    //   88: ifeq +62 -> 150
    //   91: aload_1
    //   92: astore_0
    //   93: new 151	java/lang/StringBuilder
    //   96: dup
    //   97: invokespecial 152	java/lang/StringBuilder:<init>	()V
    //   100: astore 5
    //   102: aload_1
    //   103: astore_0
    //   104: aload 5
    //   106: aload 4
    //   108: invokevirtual 535	android/net/Uri:toString	()Ljava/lang/String;
    //   111: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   114: pop
    //   115: aload_1
    //   116: astore_0
    //   117: aload 5
    //   119: ldc_w 379
    //   122: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   125: pop
    //   126: aload_1
    //   127: astore_0
    //   128: aload 5
    //   130: lload_2
    //   131: invokevirtual 538	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   134: pop
    //   135: aload_1
    //   136: astore_0
    //   137: aload 5
    //   139: invokevirtual 169	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   142: astore 4
    //   144: aload 4
    //   146: astore_0
    //   147: goto +5 -> 152
    //   150: aconst_null
    //   151: astore_0
    //   152: aload_1
    //   153: ifnull +9 -> 162
    //   156: aload_1
    //   157: invokeinterface 289 1 0
    //   162: aload_0
    //   163: areturn
    //   164: astore 4
    //   166: goto +13 -> 179
    //   169: astore_0
    //   170: aconst_null
    //   171: astore_1
    //   172: goto +33 -> 205
    //   175: astore 4
    //   177: aconst_null
    //   178: astore_1
    //   179: aload_1
    //   180: astore_0
    //   181: aload 4
    //   183: invokevirtual 236	java/lang/Exception:printStackTrace	()V
    //   186: aload_1
    //   187: ifnull +9 -> 196
    //   190: aload_1
    //   191: invokeinterface 289 1 0
    //   196: aconst_null
    //   197: areturn
    //   198: astore 4
    //   200: aload_0
    //   201: astore_1
    //   202: aload 4
    //   204: astore_0
    //   205: aload_1
    //   206: ifnull +9 -> 215
    //   209: aload_1
    //   210: invokeinterface 289 1 0
    //   215: aload_0
    //   216: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	217	0	paramContext	Context
    //   0	217	1	paramString	String
    //   74	57	2	l	long
    //   11	134	4	localObject1	Object
    //   164	1	4	localException1	Exception
    //   175	7	4	localException2	Exception
    //   198	5	4	localObject2	Object
    //   17	121	5	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   49	56	164	java/lang/Exception
    //   58	75	164	java/lang/Exception
    //   77	83	164	java/lang/Exception
    //   93	102	164	java/lang/Exception
    //   104	115	164	java/lang/Exception
    //   117	126	164	java/lang/Exception
    //   128	135	164	java/lang/Exception
    //   137	144	164	java/lang/Exception
    //   26	47	169	finally
    //   26	47	175	java/lang/Exception
    //   49	56	198	finally
    //   58	75	198	finally
    //   77	83	198	finally
    //   93	102	198	finally
    //   104	115	198	finally
    //   117	126	198	finally
    //   128	135	198	finally
    //   137	144	198	finally
    //   181	186	198	finally
  }
  
  public static boolean isAppInstalled(Context paramContext, String paramString)
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
  
  public static boolean isDiskSpaceEnough()
  {
    if ("mounted".equals(Environment.getExternalStorageState())) {
      return com.quvideo.xiaoying.common.bitmapfun.util.Utils.getUsableSpace(Environment.getExternalStorageDirectory()) >= 104857600L;
    }
    return true;
  }
  
  public static boolean isNewVersion(String paramString1, String paramString2)
  {
    boolean bool1 = false;
    if (paramString1 != null) {
      if (paramString2 == null) {
        return false;
      }
    }
    for (;;)
    {
      String str1;
      try
      {
        if (paramString2.equals(paramString1)) {
          return false;
        }
        str1 = String.valueOf(paramString1.replace("@", ""));
        paramString1 = String.valueOf(paramString2);
        j = 0;
        i = 0;
        k = str1.indexOf('.', j);
        m = paramString1.indexOf('.', i);
        if (k == -1) {
          break label227;
        }
        str2 = str1.substring(0, k);
        paramString2 = str1.substring(k + 1);
        if (m != -1)
        {
          String str3 = paramString1.substring(0, m);
          str1 = paramString1.substring(m + 1);
          paramString1 = str3;
        }
        else
        {
          str1 = "";
        }
      }
      catch (Exception paramString1)
      {
        int j;
        int i;
        int k;
        int m;
        return false;
      }
      try
      {
        if (TextUtils.isEmpty(paramString1)) {
          i = 0;
        } else {
          i = Integer.parseInt(paramString1);
        }
        if (TextUtils.isEmpty(str2)) {
          j = 0;
        } else {
          j = Integer.parseInt(str2);
        }
        if (i != j)
        {
          if (j > i) {
            bool1 = true;
          }
          return bool1;
        }
        if (TextUtils.isEmpty(paramString2))
        {
          boolean bool2 = TextUtils.isEmpty(str1);
          if (bool2) {
            return false;
          }
        }
        paramString1 = str1;
        i = m;
        str1 = paramString2;
        j = k;
      }
      catch (Exception paramString1)
      {
        return true;
      }
      return false;
      label227:
      paramString2 = "";
      String str2 = str1;
    }
  }
  
  public static boolean isOnlineMusic(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return false;
    }
    return paramString.contains("/.cache/music");
  }
  
  public static boolean isSupportedContentUri(Uri paramUri)
  {
    return Utils.isSupportedContentUri(paramUri);
  }
  
  public static boolean isThemeMusic(String paramString)
  {
    boolean bool2 = TextUtils.isEmpty(paramString);
    boolean bool1 = true;
    if (bool2) {
      return true;
    }
    if (!paramString.contains("assets"))
    {
      if (paramString.contains("Templates")) {
        return true;
      }
      bool1 = false;
    }
    return bool1;
  }
  
  public static boolean isVideo(String paramString)
  {
    boolean bool2 = TextUtils.isEmpty(paramString);
    boolean bool1 = false;
    if (bool2) {
      return false;
    }
    if (MediaExtendUtils.getMediaQType(paramString) == 1) {
      return false;
    }
    if (MediaExtendUtils.getMediaQType(paramString) == 2) {
      bool1 = true;
    }
    return bool1;
  }
  
  public static void launchLockPage(Activity paramActivity, int paramInt)
  {
    if (paramInt == 21)
    {
      UICommonUtils.launchMarket(paramActivity);
      return;
    }
    if (paramInt == 22)
    {
      doFacebookFollow(paramActivity);
      return;
    }
    if (paramInt == 23) {
      doInstagramFollow(paramActivity);
    }
  }
  
  public static String parseInputUri(Uri paramUri, Context paramContext, boolean paramBoolean)
  {
    return Utils.parseInputUri(paramUri, paramContext, paramBoolean);
  }
  
  public static void scanFile2MediaStore(Context paramContext, String[] paramArrayOfString1, String[] paramArrayOfString2, MediaScannerConnection.OnScanCompletedListener paramOnScanCompletedListener)
  {
    if ((paramContext != null) && (paramArrayOfString1 != null))
    {
      if (paramArrayOfString1.length == 0) {
        return;
      }
      Uri localUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
      ContentResolver localContentResolver = paramContext.getContentResolver();
      MediaScannerConnection.scanFile(paramContext.getApplicationContext(), paramArrayOfString1, paramArrayOfString2, new ComUtil.1(localContentResolver, localUri, new String[] { "date_added", "date_modified" }, paramOnScanCompletedListener));
      return;
    }
  }
  
  public static void sendFinishSelfIntentReceiver(Context paramContext, String paramString)
  {
    paramString = new Intent(paramString);
    LocalBroadcastManager.getInstance(paramContext).sendBroadcast(paramString);
  }
  
  public static void setTips(Context paramContext, TextView paramTextView, String paramString)
  {
    if (paramContext == null) {
      return;
    }
    if (paramTextView != null)
    {
      Object localObject2 = UICommonUtils.parserNormalString(paramString);
      ArrayList localArrayList = UICommonUtils.parserSpecialString(paramString);
      int k = 0;
      paramString = "";
      int i = 0;
      while (i < Math.max(((ArrayList)localObject2).size(), localArrayList.size()))
      {
        localObject1 = paramString;
        if (i < ((ArrayList)localObject2).size())
        {
          localObject1 = new StringBuilder();
          ((StringBuilder)localObject1).append(paramString);
          ((StringBuilder)localObject1).append((String)((ArrayList)localObject2).get(i));
          localObject1 = ((StringBuilder)localObject1).toString();
        }
        paramString = (String)localObject1;
        if (i < localArrayList.size())
        {
          paramString = new StringBuilder();
          paramString.append((String)localObject1);
          paramString.append((String)localArrayList.get(i));
          paramString = paramString.toString();
        }
        i += 1;
      }
      paramTextView.setText(paramString);
      Object localObject1 = new SpannableString(paramTextView.getText());
      ((Spannable)localObject1).setSpan(new TypefaceSpan("sans-serif-light"), 0, paramString.length(), 33);
      ((Spannable)localObject1).setSpan(new AbsoluteSizeSpan((int)paramContext.getResources().getDimension(2131099725)), 0, paramString.length(), 33);
      i = 0;
      int j;
      for (;;)
      {
        j = k;
        if (i >= ((ArrayList)localObject2).size()) {
          break;
        }
        String str = (String)((ArrayList)localObject2).get(i);
        ((Spannable)localObject1).setSpan(new ForegroundColorSpan(paramContext.getResources().getColor(2131034444)), paramString.indexOf(str), paramString.indexOf(str) + str.length(), 33);
        i += 1;
      }
      while (j < localArrayList.size())
      {
        localObject2 = (String)localArrayList.get(j);
        ((Spannable)localObject1).setSpan(new ForegroundColorSpan(paramContext.getResources().getColor(2131034267)), paramString.indexOf((String)localObject2), paramString.indexOf((String)localObject2) + ((String)localObject2).length(), 33);
        j += 1;
      }
      paramTextView.setText((CharSequence)localObject1);
    }
  }
  
  public static void shareToApp(Activity paramActivity, String paramString1, String paramString2)
  {
    String str = getVideoContentUriFromFilePath(paramActivity.getApplicationContext(), paramString2);
    if (TextUtils.isEmpty(str))
    {
      paramString1 = new ComUtil.2(paramActivity, paramString1);
      scanFile2MediaStore(paramActivity, new String[] { paramString2 }, null, paramString1);
      return;
    }
    b(paramActivity, paramString1, str);
  }
  
  public static boolean startVideoUrlIntentShare(Activity paramActivity, String paramString1, String paramString2)
  {
    paramString1 = getResolveInfoByPackagename(paramActivity.getPackageManager(), paramString1, false);
    if (paramString1 == null)
    {
      Toast.makeText(paramActivity.getApplicationContext(), 2131624655, 1).show();
      return false;
    }
    try
    {
      Intent localIntent = new Intent("android.intent.action.SEND");
      localIntent.setType("text/plain");
      localIntent.putExtra("android.intent.extra.TEXT", paramString2);
      localIntent.setComponent(new ComponentName(paramString1.activityInfo.packageName, paramString1.activityInfo.name));
      paramActivity.startActivity(localIntent);
      return true;
    }
    catch (Throwable paramString1)
    {
      Toast.makeText(paramActivity.getApplicationContext(), 2131624840, 0).show();
      paramString1.printStackTrace();
    }
    return false;
  }
  
  public static String uid2digest(long paramLong)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    while (paramLong != 0L)
    {
      int i = (int)(paramLong % dWA.length());
      localStringBuilder.append(dWA.charAt(Math.abs(i)));
      paramLong /= dWA.length();
    }
    return localStringBuilder.toString();
  }
}
