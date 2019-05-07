package com.livestream.android.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.CamcorderProfile;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore.Video.Media;
import android.provider.MediaStore.Video.Thumbnails;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.util.Patterns;
import android.util.TypedValue;
import android.view.Display;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;
import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;
import com.livestream.android.activity.BaseTabEmbeddedOrmActivity;
import com.livestream.android.api.ApiLocalException;
import com.livestream.android.api.ApiLocalException.Type;
import com.livestream.android.api.LSApi;
import com.livestream.android.api.LSService.ServiceState;
import com.livestream.android.db.SyncEnabledApiObject;
import com.livestream.android.dialog.LSDialogBuilder;
import com.livestream.android.entity.Broadcaster;
import com.livestream.android.listeners.GlobalLayoutListener;
import com.livestream.android.listeners.LSAlertDialogDelegate;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

public class LSUtils
{
  private static final int DEFAULT_STATUS_BAR_HEIGHT_DIP = 25;
  private static final String TWITTER_PACKAGE_NAME = "com.twitter.android";
  
  public LSUtils() {}
  
  public static <T extends SyncEnabledApiObject> boolean addOrUpdateObjectInCollection(ArrayList<T> paramArrayList, T paramT)
  {
    int j = paramArrayList.size();
    int i = 0;
    while (i < j)
    {
      if (((SyncEnabledApiObject)paramArrayList.get(i)).getLocalId() == paramT.getLocalId())
      {
        paramArrayList.set(i, paramT);
        return false;
      }
      i += 1;
    }
    paramArrayList.add(paramT);
    return true;
  }
  
  public static String capitalizeFirstLetter(String paramString)
  {
    return paramString.substring(0, 1).toUpperCase() + paramString.substring(1);
  }
  
  public static void clearTempDataAndCache(Context paramContext)
  {
    paramContext = ImageManager.getInstance().getImageLoader();
    paramContext.clearMemoryCache();
    paramContext.clearDiscCache();
    deleteFilesFromDirectory(Constants.Path.TEMP_PICTURE);
    deleteFilesFromDirectory(Constants.Path.TEMP_VIDEO);
    deleteFilesFromDirectory(Constants.Path.FTP_UPLOADS);
    deleteFilesFromDirectory(Constants.Path.TEMP_DIRECTORY);
  }
  
  public static void closeInputStream(InputStream paramInputStream)
  {
    try
    {
      paramInputStream.close();
      return;
    }
    catch (Exception paramInputStream)
    {
      paramInputStream.printStackTrace();
    }
  }
  
  public static void closeOutputStream(OutputStream paramOutputStream)
  {
    try
    {
      paramOutputStream.close();
      return;
    }
    catch (Exception paramOutputStream)
    {
      paramOutputStream.printStackTrace();
    }
  }
  
  public static int convertDipToPx(int paramInt, Context paramContext)
  {
    paramContext = paramContext.getResources();
    return (int)TypedValue.applyDimension(1, paramInt, paramContext.getDisplayMetrics());
  }
  
  public static String convertSecondsToTime(long paramLong)
  {
    long l1;
    long l2;
    if (paramLong >= 60L)
    {
      l1 = paramLong % 60L;
      l2 = paramLong / 60L;
      if (l2 < 60L) {
        break label116;
      }
      paramLong = l2 % 60L;
      label37:
      l2 /= 60L;
      if (l2 < 24L) {
        break label122;
      }
      l2 %= 24L;
    }
    label116:
    label122:
    for (;;)
    {
      return pad((int)l2) + ":" + pad((int)paramLong) + ":" + pad((int)l1);
      l1 = paramLong;
      break;
      paramLong = l2;
      break label37;
    }
  }
  
  public static byte[] convertStreamToByteArray(InputStream paramInputStream)
  {
    byte[] arrayOfByte2 = null;
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    byte[] arrayOfByte3 = new byte['䀀'];
    for (;;)
    {
      arrayOfByte1 = arrayOfByte2;
      try
      {
        int i = paramInputStream.read(arrayOfByte3, 0, arrayOfByte3.length);
        if (i != -1)
        {
          arrayOfByte1 = arrayOfByte2;
          localByteArrayOutputStream.write(arrayOfByte3, 0, i);
        }
      }
      catch (Exception paramInputStream)
      {
        paramInputStream.printStackTrace();
        return arrayOfByte1;
      }
    }
    byte[] arrayOfByte1 = arrayOfByte2;
    localByteArrayOutputStream.flush();
    arrayOfByte1 = arrayOfByte2;
    arrayOfByte2 = localByteArrayOutputStream.toByteArray();
    arrayOfByte1 = arrayOfByte2;
    localByteArrayOutputStream.close();
    arrayOfByte1 = arrayOfByte2;
    closeInputStream(paramInputStream);
    return arrayOfByte2;
  }
  
  /* Error */
  public static String convertStreamToString(InputStream paramInputStream)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnull +119 -> 120
    //   4: new 194	java/io/StringWriter
    //   7: dup
    //   8: invokespecial 195	java/io/StringWriter:<init>	()V
    //   11: astore_2
    //   12: sipush 1024
    //   15: newarray char
    //   17: astore_3
    //   18: new 197	java/io/BufferedReader
    //   21: dup
    //   22: new 199	java/io/InputStreamReader
    //   25: dup
    //   26: aload_0
    //   27: ldc -55
    //   29: invokespecial 204	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;Ljava/lang/String;)V
    //   32: invokespecial 207	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   35: astore 4
    //   37: aload 4
    //   39: aload_3
    //   40: invokevirtual 212	java/io/Reader:read	([C)I
    //   43: istore_1
    //   44: iload_1
    //   45: iconst_m1
    //   46: if_icmpeq +27 -> 73
    //   49: aload_2
    //   50: aload_3
    //   51: iconst_0
    //   52: iload_1
    //   53: invokevirtual 217	java/io/Writer:write	([CII)V
    //   56: goto -19 -> 37
    //   59: astore_3
    //   60: aload_3
    //   61: invokevirtual 131	java/lang/Exception:printStackTrace	()V
    //   64: aload_0
    //   65: invokevirtual 128	java/io/InputStream:close	()V
    //   68: aload_2
    //   69: invokevirtual 218	java/lang/Object:toString	()Ljava/lang/String;
    //   72: areturn
    //   73: aload_0
    //   74: invokevirtual 128	java/io/InputStream:close	()V
    //   77: goto -9 -> 68
    //   80: astore_0
    //   81: aload_0
    //   82: invokevirtual 131	java/lang/Exception:printStackTrace	()V
    //   85: goto -17 -> 68
    //   88: astore_0
    //   89: aload_0
    //   90: athrow
    //   91: astore_0
    //   92: aload_0
    //   93: invokevirtual 131	java/lang/Exception:printStackTrace	()V
    //   96: goto -28 -> 68
    //   99: astore_0
    //   100: aload_0
    //   101: athrow
    //   102: astore_2
    //   103: aload_0
    //   104: invokevirtual 128	java/io/InputStream:close	()V
    //   107: aload_2
    //   108: athrow
    //   109: astore_0
    //   110: aload_0
    //   111: invokevirtual 131	java/lang/Exception:printStackTrace	()V
    //   114: goto -7 -> 107
    //   117: astore_0
    //   118: aload_0
    //   119: athrow
    //   120: ldc -36
    //   122: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	123	0	paramInputStream	InputStream
    //   43	10	1	i	int
    //   11	58	2	localStringWriter	java.io.StringWriter
    //   102	6	2	localObject	Object
    //   17	34	3	arrayOfChar	char[]
    //   59	2	3	localException	Exception
    //   35	3	4	localBufferedReader	java.io.BufferedReader
    // Exception table:
    //   from	to	target	type
    //   18	37	59	java/lang/Exception
    //   37	44	59	java/lang/Exception
    //   49	56	59	java/lang/Exception
    //   73	77	80	java/lang/Exception
    //   73	77	88	finally
    //   81	85	88	finally
    //   64	68	91	java/lang/Exception
    //   64	68	99	finally
    //   92	96	99	finally
    //   18	37	102	finally
    //   37	44	102	finally
    //   49	56	102	finally
    //   60	64	102	finally
    //   103	107	109	java/lang/Exception
    //   103	107	117	finally
    //   110	114	117	finally
  }
  
  public static String debugCollectionSize(Collection<?> paramCollection)
  {
    if (paramCollection == null) {
      return "NULL";
    }
    try
    {
      int i = paramCollection.size();
      return String.valueOf(i);
    }
    catch (NullPointerException paramCollection) {}
    return "NULL";
  }
  
  public static int[] decodeYUV420SP(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    int[] arrayOfInt = new int[paramInt1 * paramInt2 * 3];
    int n = 0;
    int i1 = 0;
    int i4;
    int i3;
    int i2;
    int k;
    int j;
    if (n < paramInt2)
    {
      i4 = 0;
      i3 = 0;
      i2 = 0;
      int i = paramInt1 * paramInt2 + (n >> 1) * paramInt1;
      if (i2 < paramInt1)
      {
        k = (paramArrayOfByte[i1] & 0xFF) - 16;
        j = k;
        if (k < 0) {
          j = 0;
        }
        if ((i2 & 0x1) != 0) {
          break label303;
        }
        k = i + 1;
        i3 = (paramArrayOfByte[i] & 0xFF) - 128;
        i4 = (paramArrayOfByte[k] & 0xFF) - 128;
        i = k + 1;
      }
    }
    label172:
    label180:
    label255:
    label273:
    label303:
    for (;;)
    {
      j *= 1192;
      k = j + i3 * 1634;
      int m = j - i3 * 833 - i4 * 400;
      int i5 = j + i4 * 2066;
      if (k < 0)
      {
        j = 0;
        if (m >= 0) {
          break label255;
        }
        k = 0;
        if (i5 >= 0) {
          break label273;
        }
        m = 0;
      }
      for (;;)
      {
        arrayOfInt[i1] = (0xFF000000 | j << 6 & 0xFF0000 | k >> 2 & 0xFF00 | m >> 10 & 0xFF);
        i2 += 1;
        i1 += 1;
        break;
        j = k;
        if (k <= 262143) {
          break label172;
        }
        j = 262143;
        break label172;
        k = m;
        if (m <= 262143) {
          break label180;
        }
        k = 262143;
        break label180;
        m = i5;
        if (i5 > 262143) {
          m = 262143;
        }
      }
      n += 1;
      break;
      return arrayOfInt;
    }
  }
  
  public static void deleteFilesFromDirectory(String paramString)
  {
    try
    {
      paramString = new File(paramString).listFiles();
      if (paramString != null)
      {
        int i = 0;
        while (i < paramString.length)
        {
          paramString[i].delete();
          i += 1;
        }
      }
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public static void dismissProgressDialog(Dialog paramDialog)
  {
    if (paramDialog != null) {}
    try
    {
      if ((paramDialog.getWindow() != null) && (paramDialog.isShowing())) {
        paramDialog.dismiss();
      }
      return;
    }
    catch (Exception paramDialog)
    {
      paramDialog.printStackTrace();
    }
  }
  
  public static void dismissTimer(Timer paramTimer)
  {
    if (paramTimer != null)
    {
      paramTimer.cancel();
      paramTimer.purge();
    }
  }
  
  public static void dismissTimers(Timer... paramVarArgs)
  {
    int i = 0;
    while (i < paramVarArgs.length)
    {
      dismissTimer(paramVarArgs[i]);
      i += 1;
    }
  }
  
  public static void extendChildViewTouchableArea(View paramView, final int paramInt)
  {
    try
    {
      final View localView = (View)paramView.getParent();
      localView.post(new Runnable()
      {
        public void run()
        {
          Object localObject = new Rect();
          this.val$view.getHitRect((Rect)localObject);
          ((Rect)localObject).right += paramInt;
          ((Rect)localObject).bottom += paramInt;
          localObject = new TouchDelegate((Rect)localObject, this.val$view);
          if (View.class.isInstance(localView)) {
            localView.setTouchDelegate((TouchDelegate)localObject);
          }
        }
      });
      return;
    }
    catch (Exception paramView)
    {
      paramView.printStackTrace();
    }
  }
  
  public static ViewGroup findRootView(Activity paramActivity)
  {
    return (ViewGroup)((ViewGroup)paramActivity.findViewById(16908290)).getChildAt(0);
  }
  
  public static int getActivityOrientation(Context paramContext)
  {
    return paramContext.getResources().getConfiguration().orientation;
  }
  
  public static int getApiLevel()
  {
    return Build.VERSION.SDK_INT;
  }
  
  public static String getApplicationVersion(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
      return paramContext;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return "";
  }
  
  public static int getConnectionType(Context paramContext)
  {
    Object localObject = null;
    try
    {
      paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
      if (paramContext == null) {
        return -1;
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
    return paramContext.getType();
  }
  
  public static String getFullModelName()
  {
    return capitalizeFirstLetter(Build.MANUFACTURER + " " + Build.MODEL);
  }
  
  public static <T> ArrayList<T> getItemsFromArray(ArrayList<T> paramArrayList, int paramInt)
  {
    if (paramArrayList == null) {
      return new ArrayList();
    }
    int j = paramArrayList.size();
    int i = paramInt;
    if (j < paramInt) {
      i = j;
    }
    return new ArrayList(paramArrayList.subList(0, i));
  }
  
  public static JSONObject getJSONObject(String paramString, JSONObject paramJSONObject)
  {
    try
    {
      paramJSONObject.getJSONObject(paramString);
      return null;
    }
    catch (JSONException paramString)
    {
      for (;;)
      {
        paramString.printStackTrace();
      }
    }
  }
  
  public static long getMaxAvailableMemory()
  {
    long l = Runtime.getRuntime().maxMemory();
    Log.d("MaxAvailableMemory " + l / 1048576);
    return l;
  }
  
  public static String getModelName()
  {
    return Build.MODEL;
  }
  
  public static List<Broadcaster> getOnlineBroadcasters(List<Broadcaster> paramList)
  {
    ArrayList localArrayList = new ArrayList();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      Broadcaster localBroadcaster = (Broadcaster)paramList.next();
      if (localBroadcaster.isOnline()) {
        localArrayList.add(localBroadcaster);
      }
    }
    return localArrayList;
  }
  
  public static Activity getParentActivityForDialogsAndSharingIfNeeded(Activity paramActivity)
  {
    if (((paramActivity instanceof BaseTabEmbeddedOrmActivity)) && (paramActivity.getParent() != null)) {
      return paramActivity.getParent();
    }
    return paramActivity;
  }
  
  public static String getPathByUri(Activity paramActivity, Uri paramUri)
  {
    if (paramUri == null) {
      return null;
    }
    Log.e("getPathByUri uri = " + paramUri.toString());
    try
    {
      paramActivity = paramActivity.getContentResolver().query(paramUri, new String[] { "_data" }, null, null, null);
      int i = paramActivity.getColumnIndexOrThrow("_data");
      paramActivity.moveToFirst();
      paramUri = paramActivity.getString(i);
      paramActivity.close();
      return paramUri;
    }
    catch (Exception paramActivity)
    {
      paramActivity.printStackTrace();
    }
    return null;
  }
  
  public static int getRealDeviceHeight(Context paramContext)
  {
    return ((Integer)getRealDisplaySize(paramContext).first).intValue();
  }
  
  public static int getRealDeviceWidth(Context paramContext)
  {
    return ((Integer)getRealDisplaySize(paramContext).second).intValue();
  }
  
  @SuppressLint({"NewApi"})
  public static Pair<Integer, Integer> getRealDisplaySize(Context paramContext)
  {
    int k = 0;
    j = 0;
    m = 0;
    Object localObject = new DisplayMetrics();
    paramContext = ((Activity)paramContext).getWindowManager().getDefaultDisplay();
    int i = k;
    for (;;)
    {
      try
      {
        if (Build.VERSION.SDK_INT < 16) {
          continue;
        }
        i = k;
        paramContext.getRealMetrics((DisplayMetrics)localObject);
        i = k;
        k = ((DisplayMetrics)localObject).heightPixels;
        i = k;
        j = ((DisplayMetrics)localObject).widthPixels;
        i = k;
      }
      catch (Exception paramContext)
      {
        Method localMethod;
        paramContext.printStackTrace();
        j = m;
        continue;
      }
      return new Pair(Integer.valueOf(i), Integer.valueOf(j));
      i = k;
      localObject = Display.class.getMethod("getRawHeight", new Class[0]);
      i = k;
      localMethod = Display.class.getMethod("getRawWidth", new Class[0]);
      try
      {
        i = ((Integer)((Method)localObject).invoke(paramContext, new Object[0])).intValue();
        j = i;
        k = ((Integer)localMethod.invoke(paramContext, new Object[0])).intValue();
        j = k;
      }
      catch (Exception paramContext)
      {
        i = j;
        paramContext.printStackTrace();
        i = j;
        j = m;
      }
    }
  }
  
  public static Bitmap getRoundedCornerBitmap(Bitmap paramBitmap, float paramFloat)
  {
    Bitmap localBitmap = Bitmap.createBitmap(paramBitmap.getWidth(), paramBitmap.getHeight(), Bitmap.Config.ARGB_8888);
    Canvas localCanvas = new Canvas(localBitmap);
    Paint localPaint = new Paint();
    Rect localRect = new Rect(0, 0, paramBitmap.getWidth(), paramBitmap.getHeight());
    RectF localRectF = new RectF(localRect);
    float f = paramFloat;
    if (paramFloat == 0.0F) {
      f = 16.0F;
    }
    localPaint.setAntiAlias(true);
    localCanvas.drawARGB(0, 0, 0, 0);
    localPaint.setColor(-12434878);
    localCanvas.drawRoundRect(localRectF, f, f, localPaint);
    localPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
    localCanvas.drawBitmap(paramBitmap, localRect, localRect, localPaint);
    return localBitmap;
  }
  
  public static int getScreenHeight(Context paramContext)
  {
    return paramContext.getResources().getDisplayMetrics().heightPixels;
  }
  
  public static int getScreenWidth(Context paramContext)
  {
    return paramContext.getResources().getDisplayMetrics().widthPixels;
  }
  
  public static int getStatusBarHeight(Activity paramActivity)
  {
    int i = convertDipToPx(25, paramActivity);
    paramActivity = paramActivity.getWindow();
    if (paramActivity != null)
    {
      Rect localRect = new Rect();
      paramActivity.getDecorView().getWindowVisibleDisplayFrame(localRect);
      i = localRect.top;
    }
    return i;
  }
  
  public static String getTextFromEditText(EditText paramEditText)
  {
    String str = paramEditText.getText().toString().trim();
    paramEditText = str;
    if (TextUtils.isEmpty(str)) {
      paramEditText = "";
    }
    return paramEditText;
  }
  
  public static Bitmap getVideoThumbnail(Activity paramActivity, String paramString)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    try
    {
      Object localObject3 = new File(paramString);
      localObject1 = localObject2;
      paramString = paramActivity.getContentResolver();
      localObject1 = localObject2;
      Uri localUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
      localObject1 = localObject2;
      localObject3 = ((File)localObject3).getName();
      localObject1 = localObject2;
      paramString = paramString.query(localUri, new String[] { "_id", "_display_name", "_data" }, "_display_name=?", new String[] { localObject3 }, null);
      localObject1 = localObject2;
      paramString.moveToFirst();
      localObject1 = localObject2;
      long l = paramString.getLong(paramString.getColumnIndex("_id"));
      localObject1 = localObject2;
      paramActivity = MediaStore.Video.Thumbnails.getThumbnail(paramActivity.getContentResolver(), l, 1, null);
      localObject1 = paramActivity;
      paramString.close();
      return paramActivity;
    }
    catch (Exception paramActivity)
    {
      paramActivity.printStackTrace();
    }
    return localObject1;
  }
  
  public static String getVideoThumbnailPath(Activity paramActivity, String paramString)
  {
    String str = "";
    Object localObject = str;
    try
    {
      Uri localUri = MediaStore.Video.Thumbnails.getContentUri("external");
      localObject = str;
      paramString = paramActivity.getContentResolver().query(localUri, new String[] { "_id", "_data" }, "video_id In ( select _id from video where _data =?)", new String[] { paramString }, null);
      paramActivity = str;
      if (paramString != null)
      {
        paramActivity = str;
        localObject = str;
        if (paramString.moveToFirst())
        {
          localObject = str;
          paramActivity = paramString.getString(paramString.getColumnIndex("_data"));
        }
      }
      localObject = paramActivity;
      paramString.close();
      return paramActivity;
    }
    catch (Exception paramActivity)
    {
      paramActivity.printStackTrace();
    }
    return localObject;
  }
  
  public static int getViewX(View paramView)
  {
    int i = paramView.getRight();
    paramView = (ViewGroup)paramView.getParent();
    while (paramView != null)
    {
      i += paramView.getRight();
      if (((paramView instanceof ViewGroup)) && (paramView.getId() != 16908290)) {
        paramView = (ViewGroup)paramView.getParent();
      } else {
        paramView = null;
      }
    }
    return i;
  }
  
  public static int getViewY(View paramView)
  {
    int i = paramView.getTop();
    paramView = (ViewGroup)paramView.getParent();
    while (paramView != null)
    {
      i += paramView.getTop();
      if (((paramView instanceof ViewGroup)) && (paramView.getId() != 16908290)) {
        paramView = (ViewGroup)paramView.getParent();
      } else {
        paramView = null;
      }
    }
    return i;
  }
  
  public static Rect getWindowVisibleDisplayFrame(Activity paramActivity)
  {
    Rect localRect = new Rect();
    Window localWindow = paramActivity.getWindow();
    if (localWindow != null) {
      localWindow.getDecorView().getWindowVisibleDisplayFrame(localRect);
    }
    int i = localRect.height();
    int j = localRect.width();
    if (getActivityOrientation(paramActivity) == 2)
    {
      paramActivity = localRect;
      if (i > j) {
        paramActivity = new Rect(localRect.left, localRect.top, i, j);
      }
    }
    do
    {
      return paramActivity;
      paramActivity = localRect;
    } while (j <= i);
    return new Rect(localRect.left, localRect.top, j, i);
  }
  
  @SuppressLint({"NewApi"})
  public static boolean hasSoftNavigationButtons(Context paramContext)
  {
    boolean bool = true;
    int i = getApiLevel();
    if (i < 11) {}
    do
    {
      return false;
      if ((i > 10) && (i < 14)) {
        return true;
      }
    } while (i < 14);
    if ((!ViewConfiguration.get(paramContext).hasPermanentMenuKey()) && (hasSoftwareNavigation(paramContext))) {}
    for (;;)
    {
      return bool;
      bool = false;
    }
  }
  
  private static boolean hasSoftwareNavigation(Context paramContext)
  {
    if (isLandscape(paramContext)) {}
    for (int i = getRealDeviceWidth(paramContext) - getScreenWidth(paramContext); i > 0; i = getRealDeviceHeight(paramContext) - getScreenHeight(paramContext) - getStatusBarHeight((Activity)paramContext)) {
      return true;
    }
    return false;
  }
  
  @SuppressLint({"NewApi"})
  public static void hideSoftNavigationButtons(View paramView)
  {
    if (getApiLevel() >= 14) {
      paramView.setSystemUiVisibility(2);
    }
  }
  
  @SuppressLint({"NewApi"})
  public static void hideStatusBar(View paramView)
  {
    if (getApiLevel() >= 14) {
      paramView.setSystemUiVisibility(4);
    }
  }
  
  @SuppressLint({"NewApi"})
  public static void hideStatusBarAndSoftNavigationButtons(View paramView)
  {
    if (paramView == null) {}
    while (getApiLevel() < 14) {
      return;
    }
    paramView.setSystemUiVisibility(6);
  }
  
  public static boolean isAndroid2x()
  {
    return Build.VERSION.SDK_INT < 11;
  }
  
  public static boolean isAppAvailableForIntent(Activity paramActivity, String paramString, Intent paramIntent, StringBuilder paramStringBuilder)
  {
    boolean bool2 = false;
    paramActivity = paramActivity.getPackageManager().queryIntentActivities(paramIntent, 0).iterator();
    do
    {
      bool1 = bool2;
      if (!paramActivity.hasNext()) {
        break;
      }
      paramIntent = (ResolveInfo)paramActivity.next();
    } while (!paramIntent.activityInfo.packageName.equals(paramString));
    paramStringBuilder.append(paramIntent.activityInfo.name);
    boolean bool1 = true;
    return bool1;
  }
  
  public static boolean isAppInstalled(Activity paramActivity, String paramString)
  {
    paramActivity = paramActivity.getPackageManager().getInstalledPackages(1).iterator();
    while (paramActivity.hasNext()) {
      if (((PackageInfo)paramActivity.next()).packageName.equals(paramString)) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean isCollectionNullOrEmpty(Collection<?> paramCollection)
  {
    return (paramCollection == null) || (paramCollection.isEmpty());
  }
  
  public static boolean isEmailValid(String paramString)
  {
    return Patterns.EMAIL_ADDRESS.matcher(paramString).matches();
  }
  
  public static boolean isLandscape(Context paramContext)
  {
    return paramContext.getResources().getConfiguration().orientation == 2;
  }
  
  public static boolean isMainThread()
  {
    return Looper.getMainLooper().getThread() == Thread.currentThread();
  }
  
  @SuppressLint({"NewApi"})
  public static boolean isMediaCodecSupportedByServer(int paramInt)
  {
    try
    {
      if (getApiLevel() <= 8) {
        return false;
      }
      CamcorderProfile localCamcorderProfile1 = CamcorderProfile.get(paramInt, 0);
      CamcorderProfile localCamcorderProfile2 = CamcorderProfile.get(paramInt, 1);
      if (localCamcorderProfile1.videoCodec == 2)
      {
        paramInt = localCamcorderProfile2.videoCodec;
        if (paramInt == 2) {
          return true;
        }
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }
  
  public static boolean isMediaCodecSupportedByServer(int paramInt, CamcorderProfile paramCamcorderProfile)
  {
    return paramCamcorderProfile.videoCodec != 2;
  }
  
  public static boolean isOfflineException(Throwable paramThrowable)
  {
    return (paramThrowable != null) && ((paramThrowable instanceof ApiLocalException)) && (((ApiLocalException)paramThrowable).getType() == ApiLocalException.Type.OFFLINE);
  }
  
  public static boolean isOnline(Context paramContext)
  {
    boolean bool2 = false;
    paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    boolean bool1 = bool2;
    if (paramContext != null)
    {
      bool1 = bool2;
      if (paramContext.isConnected()) {
        bool1 = true;
      }
    }
    AppSession.setOnline(bool1);
    return bool1;
  }
  
  public static boolean isServiceActive(Context paramContext)
  {
    return LSApi.getInstance(paramContext).getCurrentServiceState() != LSService.ServiceState.DESTROYED;
  }
  
  public static boolean isShareToEmailAvailable(Activity paramActivity)
  {
    boolean bool = false;
    Intent localIntent = new Intent("android.intent.action.SEND");
    localIntent.setType("message/rfc822");
    if (paramActivity.getPackageManager().resolveActivity(localIntent, 0) != null) {
      bool = true;
    }
    return bool;
  }
  
  public static boolean isShareToSmsAvailable(Activity paramActivity)
  {
    boolean bool = false;
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setData(Uri.parse("sms:"));
    if (paramActivity.getPackageManager().resolveActivity(localIntent, 0) != null) {
      bool = true;
    }
    return bool;
  }
  
  public static boolean isTwitterAppInstalled(Activity paramActivity)
  {
    Intent localIntent = new Intent("android.intent.action.SEND");
    localIntent.setType("text/plain");
    localIntent.putExtra("android.intent.extra.TEXT", "");
    return isAppAvailableForIntent(paramActivity, "com.twitter.android", localIntent, new StringBuilder());
  }
  
  public static boolean isUrlValid(String paramString)
  {
    return Patterns.WEB_URL.matcher(paramString).matches();
  }
  
  public static boolean isViewVisible(View paramView)
  {
    return paramView.getVisibility() == 0;
  }
  
  public static String makePath(String paramString1, String paramString2)
  {
    File localFile = new File(paramString1);
    if (!localFile.exists()) {
      localFile.mkdirs();
    }
    if (TextUtils.isEmpty(paramString2)) {
      return paramString1;
    }
    paramString1 = paramString1 + File.separator + paramString2;
    try
    {
      paramString2 = new File(paramString1);
      if (!paramString2.exists()) {
        paramString2.createNewFile();
      }
      return paramString1;
    }
    catch (IOException paramString2)
    {
      for (;;)
      {
        paramString2.printStackTrace();
      }
    }
  }
  
  public static String pad(long paramLong)
  {
    paramLong = Math.abs(paramLong);
    if (paramLong >= 10L) {
      return String.valueOf(paramLong);
    }
    return "0" + String.valueOf(paramLong);
  }
  
  public static void playVideoInExternalPlayer(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setDataAndType(Uri.parse(paramString), "video/mp4");
    paramContext.startActivity(localIntent);
  }
  
  public static void proxyOnConfigurationChangedForChildOfTabActivity(Activity paramActivity, Configuration paramConfiguration)
  {
    if ((paramActivity != null) && (!paramActivity.isFinishing())) {
      paramActivity.onConfigurationChanged(paramConfiguration);
    }
  }
  
  public static String readTextFile(File paramFile)
    throws IOException
  {
    StringBuilder localStringBuilder = new StringBuilder();
    String str = System.getProperty("line.separator");
    paramFile = new Scanner(new FileInputStream(paramFile), "UTF-8");
    try
    {
      if (paramFile.hasNextLine()) {
        localStringBuilder.append(paramFile.nextLine() + str);
      }
      return localObject.toString();
    }
    finally
    {
      paramFile.close();
    }
  }
  
  public static void recycleBitmap(Bitmap paramBitmap)
  {
    if (paramBitmap != null) {}
    try
    {
      if (!paramBitmap.isRecycled()) {
        paramBitmap.recycle();
      }
      return;
    }
    catch (Exception paramBitmap)
    {
      paramBitmap.printStackTrace();
    }
  }
  
  public static void registerOnGlobalLayoutListener(View paramView, final GlobalLayoutListener paramGlobalLayoutListener)
  {
    paramGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener()
    {
      @SuppressLint({"NewApi"})
      public void onGlobalLayout()
      {
        try
        {
          Log.d("OnGlobalLayoutListener for " + this.val$view.getClass().getName());
          if (LSUtils.getApiLevel() >= 16) {
            this.val$view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
          }
          for (;;)
          {
            try
            {
              paramGlobalLayoutListener.onGlobalLayout();
              return;
            }
            catch (Exception localException2)
            {
              localException2.printStackTrace();
            }
            this.val$view.getViewTreeObserver().removeGlobalOnLayoutListener(this);
          }
        }
        catch (Exception localException1)
        {
          for (;;)
          {
            localException1.printStackTrace();
          }
        }
        catch (NoSuchMethodError localNoSuchMethodError)
        {
          for (;;)
          {
            localNoSuchMethodError.printStackTrace();
          }
        }
      }
    };
    paramView.getViewTreeObserver().addOnGlobalLayoutListener(paramGlobalLayoutListener);
  }
  
  public static boolean shareToEmail(Activity paramActivity, String paramString1, String paramString2)
  {
    boolean bool = false;
    Intent localIntent = new Intent("android.intent.action.SEND");
    localIntent.setType("message/rfc822");
    localIntent.putExtra("android.intent.extra.SUBJECT", paramString1);
    localIntent.putExtra("android.intent.extra.TEXT", paramString2);
    if (paramActivity.getPackageManager().resolveActivity(localIntent, 0) != null)
    {
      paramActivity.startActivity(localIntent);
      bool = true;
    }
    return bool;
  }
  
  public static void shareToFacebook(Activity paramActivity, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, Facebook.DialogListener paramDialogListener)
  {
    shareToFacebook(paramActivity, paramString1, paramString2, paramString3, paramString4, paramString5, false, paramDialogListener);
  }
  
  public static void shareToFacebook(Activity paramActivity, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, boolean paramBoolean)
  {
    shareToFacebook(paramActivity, paramString1, paramString2, paramString3, paramString4, paramString5, paramBoolean, null);
  }
  
  public static void shareToFacebook(final Activity paramActivity, String paramString1, final String paramString2, final String paramString3, final String paramString4, final String paramString5, boolean paramBoolean, final Facebook.DialogListener paramDialogListener)
  {
    paramString1 = new LSAuthorization.LSAuthorizationFacebookAuthorizeListener()
    {
      public void onCancel() {}
      
      public void onError(Exception paramAnonymousException)
      {
        paramAnonymousException.printStackTrace();
        LSUtils.showErrorToast(paramActivity, paramAnonymousException);
      }
      
      public void onSuccess(Facebook paramAnonymousFacebook)
      {
        Log.d("Facebook ON SUCCESS");
        Bundle localBundle = new Bundle();
        if (this.val$name != null) {
          localBundle.putString("name", this.val$name);
        }
        if (paramString2 != null) {
          localBundle.putString("caption", paramString2);
        }
        if (paramString3 != null) {
          localBundle.putString("description", paramString3);
        }
        if (paramString5 != null) {
          localBundle.putString("picture", paramString5);
        }
        if (paramString4 != null) {
          localBundle.putString("link", paramString4);
        }
        Facebook.DialogListener localDialogListener = paramDialogListener;
        Object localObject = localDialogListener;
        if (localDialogListener == null) {
          localObject = new Facebook.DialogListener()
          {
            public void onCancel() {}
            
            public void onComplete(Bundle paramAnonymous2Bundle) {}
            
            public void onError(DialogError paramAnonymous2DialogError)
            {
              paramAnonymous2DialogError.printStackTrace();
              LSUtils.showErrorToast(LSUtils.3.this.val$activity, paramAnonymous2DialogError);
            }
            
            public void onFacebookError(FacebookError paramAnonymous2FacebookError)
            {
              paramAnonymous2FacebookError.printStackTrace();
              Log.printMethod();
              LSUtils.showErrorToast(LSUtils.3.this.val$activity, paramAnonymous2FacebookError);
            }
          };
        }
        paramAnonymousFacebook.dialog(this.val$activityForSharing, "feed", localBundle, (Facebook.DialogListener)localObject);
      }
    };
    LSAuthorization.getInstance(paramActivity).getAuthorizedFacebook(paramActivity, paramString1, paramBoolean);
  }
  
  public static boolean shareToSms(Activity paramActivity, String paramString)
  {
    boolean bool = false;
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setData(Uri.parse("sms:"));
    localIntent.putExtra("sms_body", paramString);
    if (paramActivity.getPackageManager().resolveActivity(localIntent, 0) != null)
    {
      paramActivity.startActivity(localIntent);
      bool = true;
    }
    return bool;
  }
  
  public static boolean shareToTwitterWithIntent(Activity paramActivity, String paramString1, String paramString2, String paramString3)
  {
    paramString1 = new Intent("android.intent.action.SEND");
    paramString1.setType("text/plain");
    paramString1.putExtra("android.intent.extra.TEXT", paramString3);
    paramString2 = new StringBuilder();
    if (isAppAvailableForIntent(paramActivity, "com.twitter.android", paramString1, paramString2))
    {
      paramString1.addCategory("android.intent.category.LAUNCHER");
      paramString1.setFlags(270532608);
      paramString1.setComponent(new ComponentName("com.twitter.android", paramString2.toString()));
      paramActivity.startActivity(paramString1);
      return true;
    }
    return false;
  }
  
  public static AlertDialog showAlertDialog(Activity paramActivity, String paramString1, String paramString2)
  {
    return LSDialogBuilder.showAlertDialog(paramActivity, paramString1, paramString2, "OK", null, 0, null, true, false, null);
  }
  
  public static AlertDialog showAlertDialogForResult(Activity paramActivity, String paramString1, String paramString2, LSAlertDialogDelegate paramLSAlertDialogDelegate)
  {
    return LSDialogBuilder.showAlertDialog(paramActivity, paramString1, paramString2, "OK", null, 0, null, true, false, paramLSAlertDialogDelegate);
  }
  
  public static AlertDialog showAlertDialogForResult(Activity paramActivity, String paramString1, String paramString2, String paramString3, String paramString4, LSAlertDialogDelegate paramLSAlertDialogDelegate)
  {
    return LSDialogBuilder.showAlertDialog(paramActivity, paramString1, paramString2, paramString3, paramString4, 0, null, TextUtils.isEmpty(paramString4), false, paramLSAlertDialogDelegate);
  }
  
  public static AlertDialog showAlertDialogForResult(Activity paramActivity, String paramString1, String paramString2, String paramString3, String paramString4, LSAlertDialogDelegate paramLSAlertDialogDelegate, int paramInt, Object paramObject, boolean paramBoolean1, boolean paramBoolean2)
  {
    return LSDialogBuilder.showAlertDialog(paramActivity, paramString1, paramString2, paramString3, paramString4, paramInt, paramObject, paramBoolean1, paramBoolean2, paramLSAlertDialogDelegate);
  }
  
  public static void showErrorToast(Activity paramActivity, final Throwable paramThrowable)
  {
    new Handler(paramActivity.getMainLooper()).post(new Runnable()
    {
      public void run()
      {
        String str2 = this.val$a.getString(2131493262);
        String str1 = str2;
        if (AppSettings.isDebugModeEnabled())
        {
          str1 = str2;
          if (!TextUtils.isEmpty(paramThrowable.getMessage()))
          {
            str1 = str2 + ": " + paramThrowable.getMessage();
            Log.e(paramThrowable.getMessage());
          }
        }
        LSUtils.showToast(this.val$a, str1);
      }
    });
  }
  
  public static ProgressDialog showProgressDialog(Activity paramActivity, int paramInt)
  {
    return LSDialogBuilder.showProgressDialog(paramActivity, paramActivity.getString(paramInt));
  }
  
  public static ProgressDialog showProgressDialog(Activity paramActivity, String paramString)
  {
    return LSDialogBuilder.showProgressDialog(paramActivity, paramString);
  }
  
  @SuppressLint({"NewApi"})
  public static void showSoftNavigationButtons(View paramView)
  {
    if (paramView == null) {}
    while (getApiLevel() < 14) {
      return;
    }
    paramView.setSystemUiVisibility(0);
  }
  
  @SuppressLint({"NewApi"})
  public static void showStatusBar(View paramView)
  {
    if (getApiLevel() >= 14) {
      paramView.setSystemUiVisibility(0);
    }
  }
  
  public static void showToast(Context paramContext, String paramString)
  {
    showToast(paramContext, paramString, 0, 0);
  }
  
  public static void showToast(Context paramContext, String paramString, int paramInt)
  {
    showToast(paramContext, paramString, 0, paramInt);
  }
  
  public static void showToast(Context paramContext, final String paramString, final int paramInt1, final int paramInt2)
  {
    new Handler(paramContext.getMainLooper()).post(new Runnable()
    {
      public void run()
      {
        try
        {
          Toast localToast = Toast.makeText(this.val$context.getApplicationContext(), paramString, paramInt2);
          if (paramInt1 == 0) {
            localToast.setGravity(17, 0, 0);
          }
          for (;;)
          {
            localToast.show();
            return;
            localToast.setGravity(paramInt1, 0, 0);
          }
          return;
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
        }
      }
    });
  }
  
  public static String string(Object... paramVarArgs)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int j = paramVarArgs.length;
    int i = 0;
    while (i < j)
    {
      localStringBuilder.append(paramVarArgs[i]);
      i += 1;
    }
    return localStringBuilder.toString();
  }
  
  public static String transformCount(int paramInt)
  {
    String[] arrayOfString = new String[4];
    arrayOfString[0] = "";
    arrayOfString[1] = "K";
    arrayOfString[2] = "M";
    arrayOfString[3] = "B";
    double d = paramInt;
    paramInt = 0;
    while (d >= 1000.0D)
    {
      d /= 1000.0D;
      paramInt += 1;
    }
    long l = d;
    int i = (int)(10.0D * (d - l));
    if ((l < 100L) && (i >= 1)) {
      return String.format("%d.%d%s", new Object[] { Long.valueOf(l), Integer.valueOf(i), arrayOfString[paramInt] });
    }
    return String.format("%d%s", new Object[] { Long.valueOf(l), arrayOfString[paramInt] });
  }
  
  public static void writeTextFile(File paramFile, String paramString)
    throws IOException
  {
    paramFile = new BufferedWriter(new FileWriter(paramFile));
    paramFile.write(paramString);
    paramFile.close();
  }
}
