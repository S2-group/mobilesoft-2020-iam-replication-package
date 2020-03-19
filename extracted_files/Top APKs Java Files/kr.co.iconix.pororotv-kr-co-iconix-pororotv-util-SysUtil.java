package kr.co.iconix.pororotv.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.AlertDialog.Builder;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Environment;
import android.provider.MediaStore.Images.Media;
import android.support.v4.content.res.ResourcesCompat;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.Html;
import android.text.Spanned;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.pororotv.util.PororoSdkLogger;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import kr.co.iconix.pororotv.PororoTvContent;
import kr.co.iconix.pororotv.PororoTvPopUp;
import kr.co.iconix.pororotv.PororoTvPopUp.POPUP;
import kr.co.iconix.pororotv.PororoTvSetting;
import kr.co.iconix.pororotv.control.Iconix;
import kr.co.iconix.pororotv.control.Iconix.PAGE;
import kr.co.iconix.pororotv.data.Define;
import kr.co.iconix.pororotv.data.Preferences;
import kr.co.iconix.pororotv.network.NetDto;
import kr.co.iconix.pororotv.payment.PayMentPlayStore;
import kr.co.iconix.pororotv.share.EmotionInfo;
import kr.co.iconix.pororotv.share.LoginInfo;
import kr.co.iconix.pororotv.view.NoticeDialog;
import kr.co.iconix.pororotv.view.NoticeDialog.OnNoticeListener;
import kr.co.iconix.pororotv.view.ProgressDialog;
import org.json.JSONException;
import org.json.JSONObject;

public class SysUtil
{
  public static final int ACTION_ALBUM = 999;
  public static final int ACTION_CAMERA = 998;
  public static final int ACTION_EMOTION = 1;
  public static final int ACTION_VIDEO = 997;
  public static final String ATTENDANCE_DAY = "E0010";
  public static final String ATTENDANCE_NORMAL = "E0012";
  public static final String ATTENDANCE_TICKET = "E0013";
  public static final String ATTENDANCE_WEEK = "E0011";
  private final String CAMERA_TYPE = "profileCamera.jpg";
  private final String FACE_PATH = "facepath";
  private final String FILE_TYPE = "profile.jpg";
  private final String IMAGE_FOLDER = "appImages";
  private int IMAGE_MAX_HEIGHT_SIZE = 320;
  private int IMAGE_MAX_WIDTH_SIZE = 320;
  private boolean REAL_SIZE = false;
  private String cacheImageForder = null;
  private ConnectivityManager connectManager;
  private Context context;
  private NoticeDialog dialog;
  private int[] emotinImage;
  public final int emotionImageSize = 140;
  public final int emotionMetroImageSize = 130;
  public final int emotionPadding = 15;
  private int height = 0;
  private String innerImageForder = null;
  boolean isLollipopOrAbove;
  private LoginInfo loginInfo;
  boolean mIsHoneyCombOrAbove;
  private ProgressDialog progress;
  private Resources res;
  private int width = 0;
  
  public SysUtil(Context paramContext)
  {
    boolean bool1;
    if (Build.VERSION.SDK_INT >= 11) {
      bool1 = true;
    }
    for (;;)
    {
      this.mIsHoneyCombOrAbove = bool1;
      if (Build.VERSION.SDK_INT >= 21)
      {
        bool1 = bool2;
        this.isLollipopOrAbove = bool1;
        this.emotinImage = new int[] { 2130838250, 2130838244, 2130838248, 2130838243, 2130838249 };
        this.context = paramContext;
        this.res = paramContext.getResources();
      }
      try
      {
        this.cacheImageForder = setMakeExCacheFolder(paramContext, "appImages");
        this.innerImageForder = setMakeFolder(paramContext, "appImages");
        this.innerImageForder = this.cacheImageForder;
        this.loginInfo = new LoginInfo(paramContext);
        return;
        bool1 = false;
        continue;
        bool1 = false;
      }
      catch (IOException localIOException)
      {
        for (;;)
        {
          Logger.e("IOException", localIOException);
        }
      }
    }
  }
  
  public SysUtil(Context paramContext, boolean paramBoolean)
  {
    boolean bool1;
    if (Build.VERSION.SDK_INT >= 11) {
      bool1 = true;
    }
    for (;;)
    {
      this.mIsHoneyCombOrAbove = bool1;
      if (Build.VERSION.SDK_INT >= 21)
      {
        bool1 = bool2;
        this.isLollipopOrAbove = bool1;
        this.emotinImage = new int[] { 2130838250, 2130838244, 2130838248, 2130838243, 2130838249 };
        this.context = paramContext;
        this.res = paramContext.getResources();
      }
      try
      {
        this.cacheImageForder = setMakeExCacheFolder(paramContext, "appImages");
        this.innerImageForder = setMakeFolder(paramContext, "appImages");
        this.innerImageForder = this.cacheImageForder;
        this.loginInfo = new LoginInfo(paramContext);
        if (paramBoolean) {
          this.progress = new ProgressDialog(paramContext, this.innerImageForder);
        }
        return;
        bool1 = false;
        continue;
        bool1 = false;
      }
      catch (IOException localIOException)
      {
        for (;;)
        {
          Logger.e("IOException", localIOException);
        }
      }
    }
  }
  
  private Bitmap getBitmap(ImageView paramImageView, Bitmap paramBitmap)
    throws IOException
  {
    if (paramBitmap == null) {}
    int k;
    int m;
    do
    {
      return paramBitmap;
      float f = this.context.getResources().getDisplayMetrics().density;
      int i = paramBitmap.getWidth();
      int j = paramBitmap.getHeight();
      k = (int)(i * f / 2.0F);
      m = (int)(j * f / 2.0F);
      PororoSdkLogger.d("DENSITY : [" + f + "]ImageView :W[" + i + "]H[" + j + "]");
    } while (paramImageView == null);
    paramImageView.getLayoutParams().width = k;
    paramImageView.getLayoutParams().height = m;
    paramImageView.setImageBitmap(paramBitmap);
    paramImageView.requestLayout();
    return paramBitmap;
  }
  
  private int getHeight(Context paramContext)
  {
    paramContext = ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay();
    if (Build.VERSION.SDK_INT > 11)
    {
      Point localPoint = new Point();
      paramContext.getSize(localPoint);
      return localPoint.y;
    }
    return paramContext.getHeight();
  }
  
  private HttpURLConnection getHttpConnection(URL paramURL)
    throws IOException
  {
    HttpURLConnection localHttpURLConnection = (HttpURLConnection)paramURL.openConnection();
    int i = localHttpURLConnection.getResponseCode();
    if (i != 302)
    {
      paramURL = localHttpURLConnection;
      if (i != 301) {}
    }
    else
    {
      paramURL = getHttpConnection(new URL(localHttpURLConnection.getHeaderField("Location")));
    }
    return paramURL;
  }
  
  private File getTempFile(String paramString)
  {
    paramString = new File(this.cacheImageForder + File.separator + paramString);
    if (!paramString.exists()) {}
    try
    {
      paramString.createNewFile();
      return paramString;
    }
    catch (IOException localIOException)
    {
      Logger.e("CREATE ERROR", localIOException);
    }
    return paramString;
  }
  
  private int getWidth(Context paramContext)
  {
    paramContext = ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay();
    if (Build.VERSION.SDK_INT > 11)
    {
      Point localPoint = new Point();
      paramContext.getSize(localPoint);
      return localPoint.x;
    }
    return paramContext.getWidth();
  }
  
  private boolean isAppExist(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getInstalledApplications(0);
    int j = paramContext.size();
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        return false;
      }
      if (((ApplicationInfo)paramContext.get(i)).packageName.indexOf(paramString) != -1) {
        return true;
      }
      i += 1;
    }
  }
  
  private void netWorkManagerInit(Context paramContext)
  {
    this.connectManager = ((ConnectivityManager)paramContext.getSystemService("connectivity"));
  }
  
  private Intent runApp(Iconix paramIconix, String paramString1, String paramString2)
  {
    if (paramString1 == null) {
      return null;
    }
    String str2 = "market://details?id=" + paramString1;
    String str1 = str2;
    if (isSktTstore(paramIconix))
    {
      str1 = str2;
      if (paramString2 != null) {
        str1 = "tstore://PRODUCT_VIEW/" + paramString2 + "/0";
      }
    }
    if (isAppExist(paramIconix, paramString1)) {
      return paramIconix.getPackageManager().getLaunchIntentForPackage(paramString1);
    }
    return new Intent("android.intent.action.VIEW", Uri.parse(str1));
  }
  
  private Bitmap setBitmapDeviceResize(Bitmap paramBitmap, float paramFloat)
  {
    float f = this.context.getResources().getDisplayMetrics().density;
    int j = paramBitmap.getWidth();
    int n = paramBitmap.getHeight();
    int k = (int)(j * f / paramFloat);
    int m = (int)(n * f / paramFloat);
    int i;
    if (k <= 0) {
      i = m;
    }
    for (;;)
    {
      return Bitmap.createScaledBitmap(paramBitmap, j, i, false);
      i = m;
      j = k;
      if (m <= 0)
      {
        i = n;
        j = k;
      }
    }
  }
  
  public boolean addFolder(String paramString)
  {
    boolean bool = false;
    paramString = new File(paramString);
    if (!paramString.exists())
    {
      Logger.debug(Logger.DEBUG.DEBUG, "addFolder add");
      if (paramString.mkdirs()) {
        bool = true;
      }
      return bool;
    }
    Logger.debug(Logger.DEBUG.DEBUG, "addFolder exists");
    return true;
  }
  
  public void callKakaoLink(Activity paramActivity)
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject1 = new Hashtable(1);
    ((Map)localObject1).put("os", "android");
    ((Map)localObject1).put("devicetype", "phone");
    ((Map)localObject1).put("installurl", "market://details?id=kr.co.iconix.pororotv");
    ((Map)localObject1).put("executeurl", Define.KAKAOEXURL);
    Object localObject2 = new Hashtable(1);
    ((Map)localObject2).put("os", "ios");
    ((Map)localObject2).put("devicetype", "phone");
    ((Map)localObject2).put("installurl", "http://itunes.apple.com/app/id591336560?mt=8");
    ((Map)localObject2).put("executeurl", Define.KAKAOEXURL);
    localArrayList.add(localObject1);
    localArrayList.add(localObject2);
    KakaoLink localKakaoLink = KakaoLink.getLink(paramActivity.getApplicationContext());
    if (!localKakaoLink.isAvailableIntent()) {
      return;
    }
    localObject2 = Define.kakaoMsg;
    Logger.debug(Logger.DEBUG.DEBUG, "KAKAOTALK : [" + (String)localObject2 + "]");
    if (localObject2 != null)
    {
      localObject1 = localObject2;
      if (((String)localObject2).trim().length() != 0) {}
    }
    else
    {
      localObject1 = paramActivity.getString(2131362086);
    }
    try
    {
      localKakaoLink.openKakaoAppLink(paramActivity, "market://details?id=kr.co.iconix.pororotv", (String)localObject1, paramActivity.getPackageName(), paramActivity.getPackageManager().getPackageInfo(paramActivity.getPackageName(), 0).versionName, paramActivity.getString(2131362086), "UTF-8", localArrayList);
      return;
    }
    catch (Exception paramActivity)
    {
      Logger.e("Exception", paramActivity);
    }
  }
  
  public Bitmap changeBitmapGray(Bitmap paramBitmap)
  {
    if (paramBitmap == null) {
      return paramBitmap;
    }
    Bitmap localBitmap = Bitmap.createBitmap(paramBitmap.getWidth(), paramBitmap.getHeight(), Bitmap.Config.ARGB_8888);
    Canvas localCanvas = new Canvas(localBitmap);
    Paint localPaint = new Paint();
    ColorMatrix localColorMatrix = new ColorMatrix();
    localColorMatrix.setSaturation(0.0F);
    localPaint.setColorFilter(new ColorMatrixColorFilter(localColorMatrix));
    localCanvas.drawBitmap(paramBitmap, 0.0F, 0.0F, localPaint);
    return localBitmap;
  }
  
  public JSONObject checkJsonObject(String paramString1, String paramString2)
  {
    if (paramString2 != null) {
      try
      {
        paramString2 = new JSONObject(paramString2);
        if (paramString2.isNull(paramString1)) {
          return null;
        }
        paramString2.getJSONObject(paramString1);
        if (paramString2.isNull(paramString1)) {}
        for (int i = 0; i != 0; i = 1)
        {
          Logger.debug(Logger.DEBUG.DEBUG, "checkJsonObject -> " + paramString1 + "not null");
          paramString1 = paramString2.getJSONObject(paramString1);
          return paramString1;
        }
        return null;
      }
      catch (JSONException paramString1)
      {
        Logger.e("JSONException", paramString1);
      }
    }
  }
  
  public boolean checkResponseCode(String paramString)
    throws JSONException
  {
    if (paramString == null) {
      throw new JSONException("json is Null");
    }
    int i = new JSONObject(paramString).getInt("ErrorCode");
    if ((i != 0) && (i != 401)) {
      return true;
    }
    if (i == 401)
    {
      initLogin();
      return true;
    }
    return false;
  }
  
  public boolean check_attendance(String paramString)
  {
    bool2 = false;
    Logger.debug(Logger.DEBUG.DEBUG, "SysUtil -> check_attendance() start");
    if (paramString == null) {
      return false;
    }
    try
    {
      paramString = new JSONObject(paramString);
      bool1 = bool2;
      if (!paramString.isNull("ImageUrl"))
      {
        paramString = paramString.getString("ImageUrl");
        if ((!"E0010".equals(paramString.trim())) && (!"E0011".equals(paramString.trim())) && (!"E0012".equals(paramString.trim())))
        {
          boolean bool3 = "E0013".equals(paramString.trim());
          bool1 = bool2;
          if (!bool3) {}
        }
        else
        {
          bool1 = true;
        }
      }
    }
    catch (JSONException paramString)
    {
      for (;;)
      {
        Logger.e("JSONException", paramString);
        boolean bool1 = bool2;
      }
    }
    Logger.debug(Logger.DEBUG.DEBUG, "SysUtil -> check_attendance() res : " + bool1);
    return bool1;
  }
  
  public void closeDialog()
  {
    if (this.dialog != null)
    {
      this.dialog.dismiss();
      this.dialog = null;
    }
  }
  
  public boolean deleteDir(String paramString)
  {
    int i = 0;
    Logger.debug(Logger.DEBUG.DEBUG, "deleteDir folderPath: " + paramString);
    paramString = new File(paramString);
    if (paramString.exists())
    {
      File[] arrayOfFile = paramString.listFiles();
      int j = arrayOfFile.length;
      if (i >= j)
      {
        paramString.delete();
        Logger.debug(Logger.DEBUG.DEBUG, "deleteDir success");
        return true;
      }
      File localFile = arrayOfFile[i];
      if (localFile.isDirectory()) {
        deleteDir(localFile.getAbsolutePath());
      }
      for (;;)
      {
        i += 1;
        break;
        localFile.delete();
      }
    }
    Logger.debug(Logger.DEBUG.DEBUG, "deleteDir fail");
    return false;
  }
  
  public void deleteImg(String paramString)
  {
    String[] arrayOfString = new File(this.innerImageForder).list();
    int j = arrayOfString.length;
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        return;
      }
      String str = arrayOfString[i];
      if (str.startsWith(paramString)) {
        new File(this.innerImageForder + File.separator + str).delete();
      }
      i += 1;
    }
  }
  
  public void deleteResource(String paramString)
  {
    paramString = new File(this.innerImageForder + File.separator + paramString);
    if (paramString.exists()) {
      paramString.delete();
    }
  }
  
  public void doDiffOfDate()
  {
    try
    {
      SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
      Date localDate = localSimpleDateFormat.parse("2015-04-01");
      long l = (localSimpleDateFormat.parse("2015-05-05").getTime() - localDate.getTime()) / 86400000L;
      System.out.println("날짜차이=" + l);
      return;
    }
    catch (ParseException localParseException)
    {
      localParseException.printStackTrace();
    }
  }
  
  public void downLoadLoading(String paramString)
    throws IOException
  {
    String str = this.innerImageForder + File.separator + "loading.zip";
    paramString = (HttpURLConnection)new URL(paramString).openConnection();
    paramString.getContentLength();
    DataInputStream localDataInputStream = new DataInputStream(paramString.getInputStream());
    FileOutputStream localFileOutputStream = new FileOutputStream(str);
    byte[] arrayOfByte = new byte['࿐'];
    int i = 0;
    for (;;)
    {
      int j = localDataInputStream.read(arrayOfByte);
      if (j < 0)
      {
        localDataInputStream.close();
        localFileOutputStream.close();
        paramString.disconnect();
        unzip(str, this.innerImageForder);
        return;
      }
      i += j;
      localFileOutputStream.write(arrayOfByte, 0, j);
      localFileOutputStream.flush();
    }
  }
  
  public TranslateAnimation downUpAnimation()
  {
    TranslateAnimation localTranslateAnimation = new TranslateAnimation(0.0F, 0.0F, 100.0F, 0.0F);
    localTranslateAnimation.setDuration(300L);
    localTranslateAnimation.setFillEnabled(true);
    return localTranslateAnimation;
  }
  
  public TranslateAnimation downUpAnimation(int paramInt)
  {
    TranslateAnimation localTranslateAnimation = new TranslateAnimation(0.0F, 0.0F, 0.0F, paramInt);
    localTranslateAnimation.setDuration(300L);
    localTranslateAnimation.setFillEnabled(true);
    return localTranslateAnimation;
  }
  
  public int editTextLength(EditText paramEditText)
  {
    return paramEditText.getText().toString().length();
  }
  
  public void emotinClear()
  {
    setEmotionMain(0);
    int i = 0;
    for (;;)
    {
      if (i >= 5) {
        return;
      }
      setEmotionVersion(i, null);
      deleteDir(getFacePath());
      i += 1;
    }
  }
  
  public void fileDel(String paramString1, String paramString2)
  {
    paramString1 = new File(paramString1 + File.separator + paramString2);
    if (paramString1.exists()) {
      paramString1.delete();
    }
  }
  
  public Bitmap getBitmap(String paramString)
    throws IOException
  {
    paramString = this.innerImageForder + File.separator + paramString;
    if (new File(paramString).exists())
    {
      BitmapFactory.Options localOptions = new BitmapFactory.Options();
      localOptions.inJustDecodeBounds = true;
      BitmapFactory.decodeFile(paramString, localOptions);
      localOptions.inJustDecodeBounds = false;
      localOptions.inPurgeable = true;
      if ((localOptions.outHeight >= getWindowHeight()) || (localOptions.outWidth >= getWindowWidth())) {
        localOptions.inSampleSize = ((int)Math.pow(2.0D, (int)Math.round(Math.log(getWindowWidth() / Math.max(localOptions.outHeight, localOptions.outWidth)) / Math.log(0.5D))));
      }
      return BitmapFactory.decodeFile(paramString, localOptions);
    }
    return null;
  }
  
  public String getCurrentDate()
  {
    String str = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
    Logger.debug(Logger.DEBUG.DEBUG, "현재 시간" + str);
    return str;
  }
  
  public DisplayMetrics getDisplayInfo(Context paramContext)
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics;
  }
  
  public Drawable getDrawable(int paramInt)
  {
    if (this.isLollipopOrAbove) {
      return ResourcesCompat.getDrawable(this.res, paramInt, this.context.getTheme());
    }
    return this.context.getResources().getDrawable(paramInt);
  }
  
  public ArrayList<Bitmap> getDrawableList(String paramString)
    throws IOException
  {
    int i = 0;
    String str1 = this.innerImageForder + File.separator;
    Object localObject = new File(str1);
    ArrayList localArrayList = new ArrayList();
    paramString = paramString.split("\\.")[0];
    localObject = ((File)localObject).list();
    int j = localObject.length;
    for (;;)
    {
      if (i >= j) {
        return localArrayList;
      }
      String str2 = localObject[i];
      if ((str2.startsWith(paramString)) && (new File(str1 + str2).exists())) {
        localArrayList.add(getBitmap(str2));
      }
      i += 1;
    }
  }
  
  public Drawable getDrawableResizeing(String paramString, int paramInt1, int paramInt2)
  {
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    localOptions.inJustDecodeBounds = true;
    BitmapFactory.decodeFile(paramString, localOptions);
    localOptions.inJustDecodeBounds = false;
    localOptions.inPurgeable = true;
    if ((localOptions.outWidth >= paramInt1) || (localOptions.outHeight >= paramInt2)) {
      localOptions.inSampleSize = ((int)Math.pow(2.0D, (int)Math.round(Math.log(paramInt1 / Math.max(localOptions.outHeight, localOptions.outWidth)) / Math.log(0.5D))));
    }
    paramString = BitmapFactory.decodeFile(paramString, localOptions);
    if (paramString == null) {
      return null;
    }
    return new BitmapDrawable(this.res, paramString);
  }
  
  public Drawable getEmotionDefaultImage(int paramInt)
  {
    return this.context.getResources().getDrawable(this.emotinImage[paramInt]);
  }
  
  public Drawable getEmotionDrawableResizeing(String paramString)
  {
    return getEmotionDrawableResizeing(paramString, false);
  }
  
  public Drawable getEmotionDrawableResizeing(String paramString, boolean paramBoolean)
  {
    Object localObject = new DisplayMetrics();
    ((WindowManager)this.context.getSystemService("window")).getDefaultDisplay().getMetrics((DisplayMetrics)localObject);
    int i = ((DisplayMetrics)localObject).densityDpi;
    float f = ((DisplayMetrics)localObject).density;
    localObject = new BitmapFactory.Options();
    ((BitmapFactory.Options)localObject).inJustDecodeBounds = true;
    BitmapFactory.decodeResource(this.context.getResources(), this.emotinImage[0], (BitmapFactory.Options)localObject);
    ((BitmapFactory.Options)localObject).inJustDecodeBounds = false;
    ((BitmapFactory.Options)localObject).inPurgeable = true;
    int j = ((BitmapFactory.Options)localObject).outWidth;
    i = ((BitmapFactory.Options)localObject).outHeight;
    localObject = new BitmapFactory.Options();
    ((BitmapFactory.Options)localObject).inJustDecodeBounds = true;
    BitmapFactory.decodeFile(paramString, (BitmapFactory.Options)localObject);
    ((BitmapFactory.Options)localObject).inJustDecodeBounds = false;
    ((BitmapFactory.Options)localObject).inPurgeable = true;
    Logger.debug(Logger.DEBUG.DEBUG, "getEmotionDrawableResizeing emotion default width : " + j + " height : " + i);
    Logger.debug(Logger.DEBUG.DEBUG, "getEmotionDrawableResizeing emotion width : " + ((BitmapFactory.Options)localObject).outWidth + " height : " + ((BitmapFactory.Options)localObject).outHeight);
    if ((((BitmapFactory.Options)localObject).outWidth >= j) || (((BitmapFactory.Options)localObject).outHeight >= i))
    {
      i = (int)Math.pow(2.0D, (int)Math.round(Math.log(j / Math.max(((BitmapFactory.Options)localObject).outHeight, ((BitmapFactory.Options)localObject).outWidth)) / Math.log(0.5D)));
      Logger.debug(Logger.DEBUG.DEBUG, "getEmotionDrawableResizeing sampleSize1 : " + i);
      ((BitmapFactory.Options)localObject).inSampleSize = i;
      Logger.debug(Logger.DEBUG.DEBUG, "getEmotionDrawableResizeing sampleSize2 : " + i);
    }
    paramString = BitmapFactory.decodeFile(paramString, (BitmapFactory.Options)localObject);
    i = j;
    if (paramBoolean) {
      i = Math.round(j * 3 / 2);
    }
    return new BitmapDrawable(this.res, resizeBitmapImage(paramString, i));
  }
  
  public Drawable getEmotionImage(int paramInt)
  {
    String str = getFacePath() + File.separator + Integer.toString(paramInt);
    if (!new File(str).exists()) {
      return getEmotionDefaultImage(paramInt);
    }
    return getEmotionDrawableResizeing(str);
  }
  
  public String getEmotionImagePath(int paramInt)
  {
    String str2 = getFacePath() + File.separator + Integer.toString(paramInt);
    String str1 = str2;
    if (!new File(str2).exists()) {
      str1 = this.context.getResources().getResourceName(this.emotinImage[paramInt]);
    }
    Logger.debug(Logger.DEBUG.DEBUG, "getEmotionImagePath : " + str1);
    return str1;
  }
  
  public int getEmotionMain()
  {
    Logger.debug(Logger.DEBUG.DEBUG, " util getEmotionMain : " + Preferences.getEmotionMain(this.context));
    return Preferences.getEmotionMain(this.context);
  }
  
  public String getEmotionVersion(int paramInt)
  {
    return Preferences.getEmotionVersion(this.context, paramInt);
  }
  
  public String getFacePath()
  {
    String str = this.context.getFilesDir().getAbsolutePath() + File.separator + "facepath";
    File localFile = new File(str);
    if (!localFile.exists()) {
      localFile.mkdir();
    }
    return str;
  }
  
  public int getFilesCount(String paramString1, String paramString2)
  {
    int j = 0;
    int k = 0;
    int m;
    for (int i = 0;; i = m)
    {
      for (;;)
      {
        try
        {
          paramString1 = paramString1 + File.separator + paramString2;
          paramString2 = new File(paramString1);
        }
        catch (Exception paramString1)
        {
          j = k;
          continue;
          k = 0;
          continue;
        }
        try
        {
          paramString2 = paramString2.listFiles();
          if (paramString2 != null) {
            continue;
          }
          return 0;
        }
        catch (Exception paramString1)
        {
          Logger.e("IOException getFilesCount", paramString1);
          i = j;
        }
      }
      j = i;
      if (k < paramString2.length)
      {
        j = i;
        if (paramString2[k].isDirectory())
        {
          j = i;
          Logger.debug(Logger.DEBUG.DEBUG, "getFilesCount" + paramString1 + " Directoryname : " + paramString2[k].getName());
          m = i;
        }
        else
        {
          j = i;
          m = i;
          if (paramString2[k].isFile())
          {
            m = i + 1;
            j = m;
            Logger.debug(Logger.DEBUG.DEBUG, "getFilesCount" + paramString1 + " filename : " + paramString2[k].getName() + "파일크기 : " + paramString2[k].length() + "bytes");
          }
        }
      }
      else
      {
        return i;
      }
      k += 1;
    }
  }
  
  public String getFilesCount(String paramString)
  {
    j = 0;
    k = 0;
    i = 0;
    for (;;)
    {
      try
      {
        localObject = new File(paramString);
      }
      catch (Exception paramString)
      {
        Object localObject;
        int m;
        j = k;
        continue;
        k = 0;
        continue;
        k += 1;
        i = m;
        continue;
      }
      try
      {
        localObject = ((File)localObject).listFiles();
        if (localObject != null) {
          continue;
        }
        return null;
      }
      catch (Exception paramString)
      {
        Logger.e("IOException getFilesCount", paramString);
        i = j;
      }
    }
    j = i;
    m = localObject.length;
    if (k >= m) {
      return String.valueOf(i);
    }
    j = i;
    if (localObject[k].isDirectory())
    {
      j = i;
      Logger.debug(Logger.DEBUG.DEBUG, "getFilesCount" + paramString + " Directoryname : " + localObject[k].getName());
      m = i;
    }
    else
    {
      j = i;
      m = i;
      if (localObject[k].isFile())
      {
        m = i + 1;
        j = m;
        Logger.debug(Logger.DEBUG.DEBUG, "getFilesCount" + paramString + " filename : " + localObject[k].getName() + "파일크기 : " + localObject[k].length() + "bytes");
      }
    }
  }
  
  public ArrayList<String> getFoldersName(String paramString)
  {
    localArrayList = new ArrayList();
    for (;;)
    {
      try
      {
        localObject = new File(paramString);
      }
      catch (Exception paramString)
      {
        Object localObject;
        continue;
        int i = 0;
        continue;
        i += 1;
        continue;
      }
      try
      {
        localObject = ((File)localObject).listFiles();
        Logger.debug(Logger.DEBUG.DEBUG, "getFoldersName files.length : " + localObject.length);
        if (localObject != null) {
          continue;
        }
        return null;
      }
      catch (Exception paramString)
      {
        Logger.e("IOException getFilesCount", paramString);
        return localArrayList;
      }
    }
    if (i >= localObject.length) {
      return localArrayList;
    }
    if (localObject[i].isDirectory())
    {
      localArrayList.add(localObject[i].getName());
      Logger.debug(Logger.DEBUG.DEBUG, "getFilesCount" + paramString + " Directoryname : " + localObject[i].getName());
    }
    else if (localObject[i].isFile())
    {
      Logger.debug(Logger.DEBUG.DEBUG, "getFilesCount" + paramString + " filename : " + localObject[i].getName() + "파일크기 : " + localObject[i].length() + "bytes");
    }
  }
  
  public String getGuestId()
  {
    String str = Preferences.getGuestId(this.context);
    Logger.debug(Logger.DEBUG.DEBUG, "sysUtil GuestId -> getGuestId :" + str);
    return str;
  }
  
  public Spanned getHtmlString(String paramString)
  {
    return Html.fromHtml(paramString);
  }
  
  public String getLastImgFileName(String paramString)
  {
    paramString = paramString.split("\\/");
    paramString = paramString[(paramString.length - 1)].split("\\.");
    if (paramString.length <= 1) {
      return "";
    }
    return paramString[0];
  }
  
  public int getLastProfileId(int paramInt)
  {
    int i = Preferences.getProfileId(this.context);
    if (i == 0)
    {
      setLastProfileId(paramInt);
      return paramInt;
    }
    Logger.debug(Logger.DEBUG.JDS, "SysUtil -> getLastProfileImgPath() serverProfileId : " + paramInt);
    Logger.debug(Logger.DEBUG.JDS, "SysUtil -> getLastProfileImgPath() appProfileID " + i);
    if (paramInt >= i) {}
    for (;;)
    {
      setLastProfileId(paramInt);
      Logger.debug(Logger.DEBUG.JDS, "SysUtil -> getLastProfileImgPath() lastId " + paramInt);
      return paramInt;
      paramInt = i;
    }
  }
  
  public long getLastStatisticsId()
  {
    return Preferences.getLastStatisticsId(this.context);
  }
  
  public AsyncTask<String, Void, Bitmap> getLocalChangImageBitmap(final ImageView paramImageView, String paramString, final int paramInt)
  {
    Logger.debug(Logger.DEBUG.DEBUG, "SysUtil -> getLocalChangImageBitmap() imgName :" + paramString);
    paramString = getStartsWithImg(paramString);
    if (paramString == null) {
      return null;
    }
    paramImageView = new AsyncTask()
    {
      protected Bitmap doInBackground(String... paramAnonymousVarArgs)
      {
        Logger.debug(Logger.DEBUG.DEBUG, "SysUtil -> doInBackground() params :" + paramAnonymousVarArgs);
        return SysUtil.this.loadBitMap(paramAnonymousVarArgs[0], paramInt);
      }
      
      protected void onPostExecute(Bitmap paramAnonymousBitmap)
      {
        Logger.debug(Logger.DEBUG.DEBUG, "SysUtil -> onPostExecute() result :" + paramAnonymousBitmap);
        super.onPostExecute(paramAnonymousBitmap);
        if ((paramAnonymousBitmap == null) || (paramInt == 0)) {
          try
          {
            SysUtil.this.getBitmap(paramImageView, paramAnonymousBitmap);
            return;
          }
          catch (IOException paramAnonymousBitmap)
          {
            paramAnonymousBitmap.printStackTrace();
            return;
          }
        }
        paramImageView.setImageBitmap(paramAnonymousBitmap);
      }
    };
    paramImageView.execute(new String[] { paramString });
    return paramImageView;
  }
  
  public AsyncTask<String, Void, Bitmap> getLocalImageBitmap(final ImageView paramImageView, String paramString, final int paramInt)
  {
    Logger.debug(Logger.DEBUG.DEBUG, "SysUtil -> getLocalImageBitmap() imgName :" + paramString);
    if (paramString == null) {
      return null;
    }
    paramImageView = new AsyncTask()
    {
      protected Bitmap doInBackground(String... paramAnonymousVarArgs)
      {
        Logger.debug(Logger.DEBUG.DEBUG, "SysUtil -> doInBackground() params :" + paramAnonymousVarArgs);
        return SysUtil.this.loadBitMap(paramAnonymousVarArgs[0], paramInt);
      }
      
      protected void onPostExecute(Bitmap paramAnonymousBitmap)
      {
        Logger.debug(Logger.DEBUG.DEBUG, "SysUtil -> onPostExecute() result :" + paramAnonymousBitmap);
        super.onPostExecute(paramAnonymousBitmap);
        if ((paramAnonymousBitmap == null) || (paramInt == 0)) {
          try
          {
            SysUtil.this.getBitmap(paramImageView, paramAnonymousBitmap);
            return;
          }
          catch (IOException paramAnonymousBitmap)
          {
            paramAnonymousBitmap.printStackTrace();
            return;
          }
        }
        paramImageView.setImageBitmap(paramAnonymousBitmap);
      }
    };
    paramImageView.execute(new String[] { paramString });
    return paramImageView;
  }
  
  public Bitmap getLocalMetroImageBitmap(int paramInt1, int paramInt2, String paramString)
  {
    this.IMAGE_MAX_WIDTH_SIZE = paramInt1;
    this.IMAGE_MAX_HEIGHT_SIZE = paramInt2;
    paramString = this.innerImageForder + File.separator + paramString;
    if (new File(paramString).exists())
    {
      BitmapFactory.Options localOptions = new BitmapFactory.Options();
      localOptions.inJustDecodeBounds = true;
      BitmapFactory.decodeFile(paramString, localOptions);
      localOptions.inJustDecodeBounds = false;
      localOptions.inPurgeable = true;
      if ((localOptions.outHeight >= this.IMAGE_MAX_HEIGHT_SIZE) || (localOptions.outWidth >= this.IMAGE_MAX_WIDTH_SIZE)) {
        localOptions.inSampleSize = ((int)Math.pow(2.0D, (int)Math.round(Math.log(this.IMAGE_MAX_WIDTH_SIZE / Math.max(localOptions.outHeight, localOptions.outWidth)) / Math.log(0.5D))));
      }
      try
      {
        paramString = BitmapFactory.decodeFile(paramString, localOptions);
        return paramString;
      }
      catch (OutOfMemoryError paramString)
      {
        Logger.e("Out Of Memory (getLocalMetroImageBitmap)", paramString);
      }
    }
    return null;
  }
  
  public AsyncTask<String, Void, Bitmap> getLocalMetroImageBitmap(final ImageView paramImageView, int paramInt1, int paramInt2, String paramString, final int paramInt3)
  {
    this.IMAGE_MAX_WIDTH_SIZE = paramInt1;
    this.IMAGE_MAX_HEIGHT_SIZE = paramInt2;
    if (paramString == null) {
      return null;
    }
    paramImageView = new AsyncTask()
    {
      protected Bitmap doInBackground(String... paramAnonymousVarArgs)
      {
        return SysUtil.this.loadBitMap(paramAnonymousVarArgs[0], paramInt3);
      }
      
      protected void onPostExecute(Bitmap paramAnonymousBitmap)
      {
        super.onPostExecute(paramAnonymousBitmap);
        if (paramAnonymousBitmap != null) {
          paramImageView.setImageBitmap(paramAnonymousBitmap);
        }
      }
    };
    if (this.mIsHoneyCombOrAbove)
    {
      paramImageView.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[] { paramString });
      return paramImageView;
    }
    paramImageView.execute(new String[] { paramString });
    return paramImageView;
  }
  
  public Bitmap getLocalSeasonImageBitmap(String paramString)
  {
    paramString = this.innerImageForder + File.separator + paramString;
    if (new File(paramString).exists())
    {
      BitmapFactory.Options localOptions = new BitmapFactory.Options();
      localOptions.inJustDecodeBounds = true;
      BitmapFactory.decodeFile(paramString, localOptions);
      localOptions.inJustDecodeBounds = false;
      localOptions.inPurgeable = true;
      if ((localOptions.outHeight >= this.IMAGE_MAX_HEIGHT_SIZE) || (localOptions.outWidth >= this.IMAGE_MAX_WIDTH_SIZE)) {
        localOptions.inSampleSize = ((int)Math.pow(2.0D, (int)Math.round(Math.log(this.IMAGE_MAX_WIDTH_SIZE / Math.max(localOptions.outHeight, localOptions.outWidth)) / Math.log(0.5D))));
      }
      try
      {
        paramString = BitmapFactory.decodeFile(paramString, localOptions);
        return paramString;
      }
      catch (OutOfMemoryError paramString)
      {
        Logger.e("Out Of Memory (getLocalImageBitmap)", paramString);
      }
    }
    return null;
  }
  
  public Spanned getMemberInfo()
  {
    return Html.fromHtml(this.res.getString(2131362194));
  }
  
  public int getNetworkType(Context paramContext)
  {
    if (this.connectManager == null) {
      netWorkManagerInit(paramContext);
    }
    if (this.connectManager == null) {}
    do
    {
      do
      {
        return 0;
      } while (this.connectManager.getActiveNetworkInfo() == null);
      paramContext = this.connectManager.getActiveNetworkInfo();
    } while (paramContext == null);
    int i = paramContext.getType();
    switch (i)
    {
    }
    for (;;)
    {
      return i;
      i = 4;
      continue;
      if (paramContext.getSubtype() == 14)
      {
        i = 4;
      }
      else
      {
        i = 2;
        continue;
        i = 1;
      }
    }
  }
  
  public String getNowKoreaTime()
  {
    return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.KOREA).format(new Date());
  }
  
  public String getPhoneNumber()
  {
    return ((TelephonyManager)this.context.getSystemService("phone")).getLine1Number();
  }
  
  public String getProfileAge(Context paramContext)
  {
    return Preferences.getProfileAge(paramContext);
  }
  
  public String getProfileName(Context paramContext)
  {
    return Preferences.getProfileName(paramContext);
  }
  
  public String getProfileSex(Context paramContext)
  {
    return Preferences.getProfileSex(paramContext);
  }
  
  public ProgressDialog getProgressDialog()
  {
    if (this.progress == null) {
      this.progress = new ProgressDialog(this.context, this.innerImageForder);
    }
    return this.progress;
  }
  
  public void getRatio(int paramInt1, int paramInt2)
  {
    int j;
    int i;
    if (paramInt1 < paramInt2)
    {
      j = paramInt1;
      i = paramInt2;
    }
    for (;;)
    {
      int k = j;
      if (k % i == 0)
      {
        paramInt1 /= i;
        paramInt2 /= i;
        Logger.debug(Logger.DEBUG.DEBUG, "최대 공략수 " + i);
        Logger.debug(Logger.DEBUG.DEBUG, "getRatio " + paramInt1 + " : " + paramInt2);
        return;
        j = paramInt2;
        i = paramInt1;
      }
      else
      {
        j = i;
        i = k % i;
      }
    }
  }
  
  public String getStartsWithImg(String paramString)
  {
    Object localObject2 = new File(this.innerImageForder);
    Object localObject1 = null;
    String[] arrayOfString1 = ((File)localObject2).list();
    int j = arrayOfString1.length;
    int i = 0;
    if (i >= j) {
      return localObject1;
    }
    String str = arrayOfString1[i];
    localObject2 = localObject1;
    String[] arrayOfString2;
    String[] arrayOfString3;
    if (str.startsWith(paramString))
    {
      if (localObject1 == null) {
        break label233;
      }
      arrayOfString2 = ((String)localObject1).split("_");
      arrayOfString3 = str.split("_");
      Logger.debug(Logger.DEBUG.JDS, "PAYMENT IMG name " + str);
      localObject2 = localObject1;
      if (arrayOfString2.length >= 2)
      {
        if (arrayOfString3.length >= 2) {
          break label138;
        }
        localObject2 = localObject1;
      }
    }
    for (;;)
    {
      i += 1;
      localObject1 = localObject2;
      break;
      label138:
      if (Long.parseLong(arrayOfString3[1]) > Long.parseLong(arrayOfString2[1])) {
        localObject1 = str;
      }
      for (;;)
      {
        Logger.debug(Logger.DEBUG.JDS, "PAYMENT IMG last name " + str);
        localObject2 = localObject1;
        break;
        new File(this.innerImageForder + File.separator + str).delete();
      }
      label233:
      localObject2 = str;
    }
  }
  
  public int getStatusBarHeight()
  {
    int j = 0;
    int i = j;
    if ((this.context.getResources().getConfiguration().screenLayout & 0xF) != 4)
    {
      int k = this.context.getResources().getIdentifier("status_bar_height", "dimen", "android");
      i = j;
      if (k > 0) {
        i = this.context.getResources().getDimensionPixelSize(k);
      }
    }
    Logger.debug(Logger.DEBUG.DEBUG, "statusHeight " + i);
    return i;
  }
  
  public Uri getTempCameraUri(String paramString)
    throws Exception
  {
    paramString = new File(paramString);
    if (paramString.exists()) {
      return Uri.fromFile(paramString);
    }
    throw new Exception();
  }
  
  public Uri getTempUri(String paramString)
    throws Exception
  {
    paramString = getTempFile(paramString);
    if (paramString != null) {
      return Uri.fromFile(paramString);
    }
    throw new Exception();
  }
  
  public void getURLCacheThumbNail(ImageView paramImageView, String paramString, BitmapCache paramBitmapCache)
    throws IOException
  {
    getURLCacheThumbNail(paramImageView, paramString, paramBitmapCache, null, false);
  }
  
  public void getURLCacheThumbNail(ImageView paramImageView, String paramString, BitmapCache paramBitmapCache, OnImgListener paramOnImgListener)
    throws IOException
  {
    getURLCacheThumbNail(paramImageView, paramString, paramBitmapCache, paramOnImgListener, false);
  }
  
  public void getURLCacheThumbNail(final ImageView paramImageView, final String paramString, final BitmapCache paramBitmapCache, final OnImgListener paramOnImgListener, final boolean paramBoolean)
    throws IOException
  {
    if ((paramBitmapCache != null) && (paramBitmapCache.isExist(paramString)))
    {
      Bitmap localBitmap = paramBitmapCache.getBitmap(paramString);
      if ((localBitmap != null) && (paramImageView != null))
      {
        paramImageView.setImageBitmap(localBitmap);
        if (paramOnImgListener != null) {
          paramOnImgListener.Event(paramImageView);
        }
        return;
      }
      Logger.debug(Logger.DEBUG.DEBUG, "IMG IS NULL");
    }
    new AsyncTask()
    {
      protected Bitmap doInBackground(String... paramAnonymousVarArgs)
      {
        Bitmap localBitmap = null;
        paramAnonymousVarArgs = localBitmap;
        try
        {
          localObject = new URL(paramString);
          paramAnonymousVarArgs = localBitmap;
          Logger.debug(Logger.DEBUG.DEBUG, "urlStr : [" + paramString + "]");
          paramAnonymousVarArgs = localBitmap;
          localObject = SysUtil.this.getHttpConnection((URL)localObject);
          paramAnonymousVarArgs = localBitmap;
          int i = ((HttpURLConnection)localObject).getContentLength();
          paramAnonymousVarArgs = localBitmap;
          Logger.debug(Logger.DEBUG.DEBUG, "IMAGE : RESPONSE_MESSAGE[" + ((HttpURLConnection)localObject).getResponseMessage() + "] | RESPONSE_CODE : [" + ((HttpURLConnection)localObject).getResponseCode() + "] | CONTENT SIZE : [" + i + "]");
          paramAnonymousVarArgs = localBitmap;
          if (((HttpURLConnection)localObject).getResponseCode() >= 400)
          {
            paramAnonymousVarArgs = localBitmap;
            Logger.netError((HttpURLConnection)localObject);
            return null;
          }
          paramAnonymousVarArgs = localBitmap;
          BitmapFactory.Options localOptions = new BitmapFactory.Options();
          paramAnonymousVarArgs = localBitmap;
          localOptions.inJustDecodeBounds = false;
          paramAnonymousVarArgs = localBitmap;
          localOptions.inPurgeable = true;
          paramAnonymousVarArgs = localBitmap;
          i = SysUtil.this.IMAGE_MAX_WIDTH_SIZE;
          paramAnonymousVarArgs = localBitmap;
          int j = SysUtil.this.IMAGE_MAX_HEIGHT_SIZE;
          paramAnonymousVarArgs = localBitmap;
          if (localOptions.outHeight < j)
          {
            paramAnonymousVarArgs = localBitmap;
            if (localOptions.outWidth < i) {}
          }
          else
          {
            paramAnonymousVarArgs = localBitmap;
            localOptions.inSampleSize = ((int)Math.pow(2.0D, (int)Math.round(Math.log(SysUtil.this.getWindowWidth() / Math.max(localOptions.outHeight, localOptions.outWidth)) / Math.log(0.5D))));
          }
          paramAnonymousVarArgs = localBitmap;
          localBitmap = BitmapFactory.decodeStream(((HttpURLConnection)localObject).getInputStream(), null, localOptions);
          if (localBitmap == null)
          {
            paramAnonymousVarArgs = localBitmap;
            Logger.debug(Logger.DEBUG.DEBUG, "getURLCacheThumbNail is NULL");
            return localBitmap;
          }
        }
        catch (Exception localException)
        {
          Logger.e("SysUtil getURLCacheThumbNail", localException);
          return paramAnonymousVarArgs;
        }
        paramAnonymousVarArgs = localException;
        Logger.debug(Logger.DEBUG.DEBUG, "getURLCacheThumbNail is NOT NULL");
        paramAnonymousVarArgs = localException;
        Object localObject = localException;
        if (paramBoolean)
        {
          paramAnonymousVarArgs = localException;
          localObject = SysUtil.this.setBitmapHdpiDeviceResize(localException);
        }
        return localObject;
      }
      
      protected void onPostExecute(Bitmap paramAnonymousBitmap)
      {
        super.onPostExecute(paramAnonymousBitmap);
        Logger.debug(Logger.DEBUG.DEBUG, "getURLCacheThumbNail RESPONSE");
        if ((paramAnonymousBitmap != null) && (paramImageView != null))
        {
          paramBitmapCache.addBitmap(paramString, paramAnonymousBitmap);
          paramImageView.setImageBitmap(paramAnonymousBitmap);
          if (paramOnImgListener != null) {
            paramOnImgListener.Event(paramImageView);
          }
          return;
        }
        Logger.debug(Logger.DEBUG.DEBUG, "IMG NULL");
      }
    }.execute(new String[] { paramString });
  }
  
  public void getURLResourceEmotin(final ImageView paramImageView, final String paramString1, final String paramString2, final String paramString3, final boolean paramBoolean)
    throws IOException
  {
    new AsyncTask()
    {
      protected Bitmap doInBackground(String... paramAnonymousVarArgs)
      {
        Object localObject = null;
        int i;
        int j;
        if (paramString3 != null)
        {
          str = paramString2 + File.separator + paramString3;
          localFile = new File(str);
          Logger.debug(Logger.DEBUG.DEBUG, "getURLResourceEmotin filepath : " + str);
          if ((localFile.exists()) && (paramBoolean)) {
            localFile.delete();
          }
          paramAnonymousVarArgs = (String[])localObject;
          if (localFile.exists())
          {
            paramAnonymousVarArgs = new BitmapFactory.Options();
            paramAnonymousVarArgs.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(str, paramAnonymousVarArgs);
            paramAnonymousVarArgs.inJustDecodeBounds = false;
            paramAnonymousVarArgs.inPurgeable = true;
            i = SysUtil.this.IMAGE_MAX_WIDTH_SIZE;
            j = SysUtil.this.IMAGE_MAX_HEIGHT_SIZE;
            if ((paramAnonymousVarArgs.outHeight >= j) || (paramAnonymousVarArgs.outWidth >= i)) {
              paramAnonymousVarArgs.inSampleSize = ((int)Math.pow(2.0D, (int)Math.round(Math.log(SysUtil.this.getWindowWidth() / Math.max(paramAnonymousVarArgs.outHeight, paramAnonymousVarArgs.outWidth)) / Math.log(0.5D))));
            }
          }
          try
          {
            paramAnonymousVarArgs = BitmapFactory.decodeFile(str, paramAnonymousVarArgs);
            if (paramAnonymousVarArgs == null) {
              break label291;
            }
            return paramAnonymousVarArgs;
          }
          catch (OutOfMemoryError paramAnonymousVarArgs)
          {
            for (;;)
            {
              Logger.e("Out Of Memory [" + SysUtil.this.IMAGE_MAX_HEIGHT_SIZE + "|" + SysUtil.this.IMAGE_MAX_WIDTH_SIZE + "]", paramAnonymousVarArgs);
              localFile.delete();
              paramAnonymousVarArgs = (String[])localObject;
            }
          }
        }
        else
        {
          return null;
        }
        label291:
        String str = SysUtil.this.saveEmotionHttpImage(paramString1, paramString3);
        File localFile = new File(str);
        Logger.debug(Logger.DEBUG.DEBUG, "emotion image size server : " + localFile.length() + "bytes");
        localObject = paramAnonymousVarArgs;
        if (localFile.exists())
        {
          localObject = new BitmapFactory.Options();
          ((BitmapFactory.Options)localObject).inJustDecodeBounds = true;
          BitmapFactory.decodeFile(str, (BitmapFactory.Options)localObject);
          ((BitmapFactory.Options)localObject).inJustDecodeBounds = false;
          ((BitmapFactory.Options)localObject).inPurgeable = true;
          i = SysUtil.this.IMAGE_MAX_WIDTH_SIZE;
          j = SysUtil.this.IMAGE_MAX_HEIGHT_SIZE;
          if ((((BitmapFactory.Options)localObject).outHeight >= j) || (((BitmapFactory.Options)localObject).outWidth >= i)) {
            ((BitmapFactory.Options)localObject).inSampleSize = ((int)Math.pow(2.0D, (int)Math.round(Math.log(SysUtil.this.getWindowWidth() / Math.max(((BitmapFactory.Options)localObject).outHeight, ((BitmapFactory.Options)localObject).outWidth)) / Math.log(0.5D))));
          }
        }
        try
        {
          localObject = BitmapFactory.decodeFile(str, (BitmapFactory.Options)localObject);
          return localObject;
        }
        catch (OutOfMemoryError localOutOfMemoryError)
        {
          for (;;)
          {
            Logger.e("Out Of Memory [" + SysUtil.this.IMAGE_MAX_HEIGHT_SIZE + "|" + SysUtil.this.IMAGE_MAX_WIDTH_SIZE + "]", localOutOfMemoryError);
            localFile.delete();
            String[] arrayOfString = paramAnonymousVarArgs;
          }
        }
      }
      
      protected void onPostExecute(Bitmap paramAnonymousBitmap)
      {
        super.onPostExecute(paramAnonymousBitmap);
        if ((paramAnonymousBitmap != null) && (paramImageView != null)) {
          paramImageView.setImageBitmap(paramAnonymousBitmap);
        }
      }
    }.execute(new String[] { paramString1 });
  }
  
  public AsyncTask<String, Void, Bitmap> getURLResourceImg(ImageView paramImageView, String paramString)
  {
    return getURLResourceImg(paramImageView, paramString, false);
  }
  
  public AsyncTask<String, Void, Bitmap> getURLResourceImg(final ImageView paramImageView, String paramString, final boolean paramBoolean)
  {
    if (paramString == null) {
      return null;
    }
    new BitmapLoader(this.innerImageForder + File.separator, this.context).loadBitmapTo(paramString, paramImageView, (LinearLayout)View.inflate(this.context, 2130903109, new LinearLayout(this.context)), new BitmapLoader.OnCompleteListener()
    {
      public void onResult(Bitmap paramAnonymousBitmap)
      {
        Logger.debug(Logger.DEBUG.DEBUG, "getURLResourceImg onResult");
        if (paramBoolean)
        {
          paramAnonymousBitmap = SysUtil.this.changeBitmapGray(paramAnonymousBitmap);
          paramImageView.setImageBitmap(paramAnonymousBitmap);
        }
      }
    });
  }
  
  public void getURLResourcePopUp(ImageView paramImageView, String paramString1, String paramString2)
    throws IOException
  {
    getURLResourcePopUp(paramImageView, paramString1, paramString2, new OnImgPopupListener()
    {
      public void onImgPopup(Bitmap paramAnonymousBitmap) {}
    });
  }
  
  public void getURLResourcePopUp(final ImageView paramImageView, final String paramString1, final String paramString2, final OnImgListener paramOnImgListener)
    throws IOException
  {
    new AsyncTask()
    {
      protected Bitmap doInBackground(String... paramAnonymousVarArgs)
      {
        Object localObject = null;
        int i;
        int j;
        if (paramString2 != null)
        {
          str = SysUtil.this.innerImageForder + File.separator + paramString2;
          localFile = new File(str);
          paramAnonymousVarArgs = (String[])localObject;
          if (localFile.exists())
          {
            paramAnonymousVarArgs = new BitmapFactory.Options();
            paramAnonymousVarArgs.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(str, paramAnonymousVarArgs);
            paramAnonymousVarArgs.inJustDecodeBounds = false;
            paramAnonymousVarArgs.inPurgeable = true;
            i = SysUtil.this.getWindowWidth();
            j = SysUtil.this.getWindowHeight();
            if ((paramAnonymousVarArgs.outHeight >= j) || (paramAnonymousVarArgs.outWidth >= i)) {
              paramAnonymousVarArgs.inSampleSize = ((int)Math.pow(2.0D, (int)Math.round(Math.log(SysUtil.this.getWindowWidth() / Math.max(paramAnonymousVarArgs.outHeight, paramAnonymousVarArgs.outWidth)) / Math.log(0.5D))));
            }
          }
          try
          {
            paramAnonymousVarArgs = BitmapFactory.decodeFile(str, paramAnonymousVarArgs);
            if (paramAnonymousVarArgs == null) {
              break label250;
            }
            return paramAnonymousVarArgs;
          }
          catch (OutOfMemoryError paramAnonymousVarArgs)
          {
            for (;;)
            {
              Logger.e("Out Of Memory [" + SysUtil.this.IMAGE_MAX_HEIGHT_SIZE + "|" + SysUtil.this.IMAGE_MAX_WIDTH_SIZE + "]", paramAnonymousVarArgs);
              localFile.delete();
              paramAnonymousVarArgs = (String[])localObject;
            }
          }
        }
        else
        {
          return null;
        }
        label250:
        String str = SysUtil.this.saveHttpImage(paramString1, paramString2);
        File localFile = new File(str);
        localObject = paramAnonymousVarArgs;
        if (localFile.exists())
        {
          localObject = new BitmapFactory.Options();
          ((BitmapFactory.Options)localObject).inJustDecodeBounds = true;
          BitmapFactory.decodeFile(str, (BitmapFactory.Options)localObject);
          ((BitmapFactory.Options)localObject).inJustDecodeBounds = false;
          ((BitmapFactory.Options)localObject).inPurgeable = true;
          i = SysUtil.this.getWindowWidth();
          j = SysUtil.this.getWindowHeight();
          if ((((BitmapFactory.Options)localObject).outHeight >= j) || (((BitmapFactory.Options)localObject).outWidth >= i)) {
            ((BitmapFactory.Options)localObject).inSampleSize = ((int)Math.pow(2.0D, (int)Math.round(Math.log(SysUtil.this.getWindowWidth() / Math.max(((BitmapFactory.Options)localObject).outHeight, ((BitmapFactory.Options)localObject).outWidth)) / Math.log(0.5D))));
          }
        }
        try
        {
          localObject = BitmapFactory.decodeFile(str, (BitmapFactory.Options)localObject);
          return localObject;
        }
        catch (OutOfMemoryError localOutOfMemoryError)
        {
          for (;;)
          {
            Logger.e("Out Of Memory [" + SysUtil.this.IMAGE_MAX_HEIGHT_SIZE + "|" + SysUtil.this.IMAGE_MAX_WIDTH_SIZE + "]", localOutOfMemoryError);
            localFile.delete();
            String[] arrayOfString = paramAnonymousVarArgs;
          }
        }
      }
      
      protected void onPostExecute(Bitmap paramAnonymousBitmap)
      {
        super.onPostExecute(paramAnonymousBitmap);
        if (paramAnonymousBitmap != null)
        {
          if (paramOnImgListener != null) {
            paramOnImgListener.Event(paramImageView);
          }
          if ((paramImageView == null) || (paramAnonymousBitmap == null)) {
            return;
          }
          float f = SysUtil.this.context.getResources().getDisplayMetrics().density;
          paramAnonymousBitmap = Bitmap.createScaledBitmap(paramAnonymousBitmap, (int)(paramAnonymousBitmap.getWidth() * f / 1.5F), (int)(paramAnonymousBitmap.getHeight() * f / 1.5F), false);
          Logger.debug(Logger.DEBUG.DEBUG, "BITMAP SIZE : " + paramAnonymousBitmap.getWidth() + "||" + paramAnonymousBitmap.getHeight() + "||" + paramImageView.getWidth());
          paramImageView.setImageBitmap(paramAnonymousBitmap);
          paramImageView.invalidate();
          return;
        }
        Logger.debug(Logger.DEBUG.DEBUG, "IMAGE_NAME NOT");
      }
    }.execute(new String[] { paramString1 });
  }
  
  public void getURLResourcePopUp(final ImageView paramImageView, final String paramString1, final String paramString2, final OnImgPopupListener paramOnImgPopupListener)
    throws IOException
  {
    new AsyncTask()
    {
      protected Bitmap doInBackground(String... paramAnonymousVarArgs)
      {
        Object localObject = null;
        if (paramString2 != null)
        {
          str = SysUtil.this.innerImageForder + File.separator + paramString2;
          localFile = new File(str);
          paramAnonymousVarArgs = (String[])localObject;
          if (localFile.exists())
          {
            paramAnonymousVarArgs = new BitmapFactory.Options();
            paramAnonymousVarArgs.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(str, paramAnonymousVarArgs);
            paramAnonymousVarArgs.inJustDecodeBounds = false;
          }
          try
          {
            Logger.debug(Logger.DEBUG.DEBUG, "FILE EXISTS11 : " + paramString2);
            paramAnonymousVarArgs = BitmapFactory.decodeFile(str, paramAnonymousVarArgs);
            if (paramAnonymousVarArgs == null) {
              break label185;
            }
            return paramAnonymousVarArgs;
          }
          catch (OutOfMemoryError paramAnonymousVarArgs)
          {
            for (;;)
            {
              Logger.e("Out Of Memory [" + SysUtil.this.IMAGE_MAX_HEIGHT_SIZE + "|" + SysUtil.this.IMAGE_MAX_WIDTH_SIZE + "]", paramAnonymousVarArgs);
              localFile.delete();
              paramAnonymousVarArgs = (String[])localObject;
            }
          }
        }
        else
        {
          return null;
        }
        label185:
        String str = SysUtil.this.saveHttpImage(paramString1, paramString2);
        File localFile = new File(str);
        localObject = paramAnonymousVarArgs;
        if (localFile.exists())
        {
          localObject = new BitmapFactory.Options();
          ((BitmapFactory.Options)localObject).inJustDecodeBounds = true;
          BitmapFactory.decodeFile(str, (BitmapFactory.Options)localObject);
          ((BitmapFactory.Options)localObject).inJustDecodeBounds = false;
        }
        try
        {
          localObject = BitmapFactory.decodeFile(str, (BitmapFactory.Options)localObject);
          return localObject;
        }
        catch (OutOfMemoryError localOutOfMemoryError)
        {
          for (;;)
          {
            Logger.e("Out Of Memory [" + SysUtil.this.IMAGE_MAX_HEIGHT_SIZE + "|" + SysUtil.this.IMAGE_MAX_WIDTH_SIZE + "]", localOutOfMemoryError);
            localFile.delete();
            String[] arrayOfString = paramAnonymousVarArgs;
          }
        }
      }
      
      protected void onPostExecute(Bitmap paramAnonymousBitmap)
      {
        super.onPostExecute(paramAnonymousBitmap);
        if (paramOnImgPopupListener != null) {
          paramOnImgPopupListener.onImgPopup(paramAnonymousBitmap);
        }
        if (paramAnonymousBitmap != null)
        {
          if (paramImageView != null)
          {
            int i = SysUtil.this.getWidth(SysUtil.this.context);
            int j = i - i * 200 / 1280;
            int m = paramAnonymousBitmap.getWidth();
            int n = paramAnonymousBitmap.getHeight();
            i = j;
            int k = j * n / m;
            j = i;
            if (SysUtil.this.isSixteenNine(m, n))
            {
              j = i / 16 * 16;
              k = j / 16 * 9;
            }
            ViewGroup.LayoutParams localLayoutParams = paramImageView.getLayoutParams();
            if (localLayoutParams != null)
            {
              localLayoutParams.width = j;
              localLayoutParams.height = k;
              paramImageView.setLayoutParams(localLayoutParams);
            }
            Logger.debug(Logger.DEBUG.DEBUG, "POPUP_IMG : " + m + "|" + n + "|" + j + "|" + k);
            paramImageView.setImageBitmap(paramAnonymousBitmap);
          }
          return;
        }
        Logger.debug(Logger.DEBUG.DEBUG, "IMAGE_NAME NOT");
      }
    }.execute(new String[] { paramString1 });
  }
  
  public void getURLResourceThumbNail(final ImageView paramImageView, final String paramString1, final String paramString2)
    throws IOException
  {
    new AsyncTask()
    {
      protected Bitmap doInBackground(String... paramAnonymousVarArgs)
      {
        Object localObject = null;
        int i;
        int j;
        if (paramString2 != null)
        {
          str = SysUtil.this.innerImageForder + File.separator + paramString2;
          localFile = new File(str);
          paramAnonymousVarArgs = (String[])localObject;
          if (localFile.exists())
          {
            paramAnonymousVarArgs = new BitmapFactory.Options();
            paramAnonymousVarArgs.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(str, paramAnonymousVarArgs);
            paramAnonymousVarArgs.inJustDecodeBounds = false;
            paramAnonymousVarArgs.inPurgeable = true;
            i = SysUtil.this.IMAGE_MAX_WIDTH_SIZE;
            j = SysUtil.this.IMAGE_MAX_HEIGHT_SIZE;
            if ((paramAnonymousVarArgs.outHeight >= j) || (paramAnonymousVarArgs.outWidth >= i)) {
              paramAnonymousVarArgs.inSampleSize = ((int)Math.pow(2.0D, (int)Math.round(Math.log(SysUtil.this.getWindowWidth() / Math.max(paramAnonymousVarArgs.outHeight, paramAnonymousVarArgs.outWidth)) / Math.log(0.5D))));
            }
          }
          try
          {
            paramAnonymousVarArgs = BitmapFactory.decodeFile(str, paramAnonymousVarArgs);
            if (paramAnonymousVarArgs == null) {
              break label250;
            }
            return paramAnonymousVarArgs;
          }
          catch (OutOfMemoryError paramAnonymousVarArgs)
          {
            for (;;)
            {
              Logger.e("Out Of Memory [" + SysUtil.this.IMAGE_MAX_HEIGHT_SIZE + "|" + SysUtil.this.IMAGE_MAX_WIDTH_SIZE + "]", paramAnonymousVarArgs);
              localFile.delete();
              paramAnonymousVarArgs = (String[])localObject;
            }
          }
        }
        else
        {
          return null;
        }
        label250:
        String str = SysUtil.this.saveHttpImage(paramString1, paramString2);
        File localFile = new File(str);
        localObject = paramAnonymousVarArgs;
        if (localFile.exists())
        {
          localObject = new BitmapFactory.Options();
          ((BitmapFactory.Options)localObject).inJustDecodeBounds = true;
          BitmapFactory.decodeFile(str, (BitmapFactory.Options)localObject);
          ((BitmapFactory.Options)localObject).inJustDecodeBounds = false;
          ((BitmapFactory.Options)localObject).inPurgeable = true;
          i = SysUtil.this.IMAGE_MAX_WIDTH_SIZE;
          j = SysUtil.this.IMAGE_MAX_HEIGHT_SIZE;
          if ((((BitmapFactory.Options)localObject).outHeight >= j) || (((BitmapFactory.Options)localObject).outWidth >= i)) {
            ((BitmapFactory.Options)localObject).inSampleSize = ((int)Math.pow(2.0D, (int)Math.round(Math.log(SysUtil.this.getWindowWidth() / Math.max(((BitmapFactory.Options)localObject).outHeight, ((BitmapFactory.Options)localObject).outWidth)) / Math.log(0.5D))));
          }
        }
        try
        {
          localObject = BitmapFactory.decodeFile(str, (BitmapFactory.Options)localObject);
          return localObject;
        }
        catch (OutOfMemoryError localOutOfMemoryError)
        {
          for (;;)
          {
            Logger.e("Out Of Memory [" + SysUtil.this.IMAGE_MAX_HEIGHT_SIZE + "|" + SysUtil.this.IMAGE_MAX_WIDTH_SIZE + "]", localOutOfMemoryError);
            localFile.delete();
            String[] arrayOfString = paramAnonymousVarArgs;
          }
        }
      }
      
      protected void onPostExecute(Bitmap paramAnonymousBitmap)
      {
        super.onPostExecute(paramAnonymousBitmap);
        if ((paramAnonymousBitmap != null) && (paramImageView != null)) {
          paramImageView.setImageBitmap(paramAnonymousBitmap);
        }
      }
    }.execute(new String[] { paramString1 });
  }
  
  public Spanned getVerionNoHtml()
    throws Exception
  {
    return Html.fromHtml(String.format(this.res.getString(2131362301), new Object[] { getVersion(), Float.valueOf(Define.UPDATEVERSION) }));
  }
  
  public String getVersion()
    throws PackageManager.NameNotFoundException
  {
    Activity localActivity = (Activity)this.context;
    return localActivity.getPackageManager().getPackageInfo(localActivity.getClass().getPackage().getName(), 128).versionName;
  }
  
  public int getWindowHeight()
  {
    this.height = getHeight(this.context);
    return this.height;
  }
  
  public int getWindowWidth()
  {
    this.width = getWidth(this.context);
    return this.width;
  }
  
  public boolean in_array(int[] paramArrayOfInt, int paramInt)
  {
    int i = 0;
    for (;;)
    {
      if (i >= paramArrayOfInt.length) {
        return false;
      }
      if ((paramArrayOfInt[i] == paramInt) && (paramArrayOfInt[i] != 0)) {
        return true;
      }
      i += 1;
    }
  }
  
  public boolean in_array(String[] paramArrayOfString, String paramString)
  {
    int i = 0;
    for (;;)
    {
      if (i >= paramArrayOfString.length) {
        return false;
      }
      if (paramArrayOfString[i] == paramString) {
        return true;
      }
      i += 1;
    }
  }
  
  public void initLogin()
  {
    this.loginInfo.initLogin();
  }
  
  public boolean isAppCheck(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getInstalledApplications(0);
    int j = paramContext.size();
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        return false;
      }
      if (((ApplicationInfo)paramContext.get(i)).packageName.startsWith(paramString)) {
        return true;
      }
      i += 1;
    }
  }
  
  public boolean isAppCheck(Context paramContext, String[] paramArrayOfString)
  {
    boolean bool = false;
    paramContext = paramContext.getPackageManager().getInstalledApplications(0);
    int k = paramContext.size();
    int i = 0;
    if (i >= k) {
      return bool;
    }
    ApplicationInfo localApplicationInfo = (ApplicationInfo)paramContext.get(i);
    int m = paramArrayOfString.length;
    int j = 0;
    for (;;)
    {
      if (j >= m) {}
      for (;;)
      {
        i += 1;
        break;
        String str = paramArrayOfString[j];
        if (!localApplicationInfo.packageName.startsWith(str)) {
          break label89;
        }
        bool = true;
      }
      label89:
      j += 1;
    }
  }
  
  public boolean isAppRunCheck(Context paramContext, String[] paramArrayOfString)
  {
    boolean bool = false;
    paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses().iterator();
    label86:
    for (;;)
    {
      if (!paramContext.hasNext()) {
        return bool;
      }
      ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)paramContext.next();
      int j = paramArrayOfString.length;
      int i = 0;
      for (;;)
      {
        if (i >= j) {
          break label86;
        }
        String str = paramArrayOfString[i];
        if (localRunningAppProcessInfo.processName.startsWith(str))
        {
          bool = true;
          break;
        }
        i += 1;
      }
    }
  }
  
  public boolean isAvailableIntent(Context paramContext, String paramString)
  {
    paramString = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
    paramContext = paramContext.getPackageManager().queryIntentActivities(paramString, 65536);
    if (paramContext == null) {}
    while (paramContext.size() <= 0) {
      return false;
    }
    return true;
  }
  
  public boolean isEditTextCompare(EditText paramEditText1, EditText paramEditText2)
  {
    paramEditText1 = paramEditText1.getText().toString().trim();
    return paramEditText2.getText().toString().trim().equals(paramEditText1);
  }
  
  public boolean isElapsedDay(String paramString)
  {
    Object localObject = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    Logger.debug(Logger.DEBUG.DEBUG, "popuptest 저장된시간 : " + paramString + " 현재 시간 : " + getCurrentDate());
    for (boolean bool1 = true;; bool1 = true)
    {
      try
      {
        paramString = ((SimpleDateFormat)localObject).parse(paramString);
        localObject = ((SimpleDateFormat)localObject).parse(getCurrentDate());
        boolean bool2 = paramString.toString().equalsIgnoreCase(((Date)localObject).toString());
        if (!bool2) {
          continue;
        }
        bool1 = false;
      }
      catch (ParseException paramString)
      {
        for (;;)
        {
          paramString.printStackTrace();
        }
      }
      Logger.debug(Logger.DEBUG.DEBUG, "시간차" + bool1);
      return bool1;
    }
  }
  
  public boolean isEmail(String paramString)
  {
    if (paramString == null) {
      return false;
    }
    return Pattern.matches("[\\w\\~\\-\\.]+@[\\w\\~\\-]+(\\.[\\w\\~\\-]+)+", paramString.trim());
  }
  
  public boolean isEmailType(EditText paramEditText)
  {
    return paramEditText.getText().toString().trim().contains("@");
  }
  
  public boolean isEmotionResultComplete(int paramInt1, int paramInt2)
  {
    boolean bool = false;
    Logger.debug(Logger.DEBUG.DEBUG, "isEmotionResultComplete REQUEST CODE : " + paramInt1 + " | RESULTCODE CODE : " + paramInt2);
    if (paramInt2 != -1) {
      return false;
    }
    switch (paramInt1)
    {
    }
    for (;;)
    {
      return bool;
      bool = true;
    }
  }
  
  public boolean isEmptyEditText(View paramView)
  {
    if ((paramView instanceof EditText)) {
      return "".equals(((EditText)paramView).getText().toString().trim());
    }
    if ((paramView instanceof TextView)) {
      return "".equals(((TextView)paramView).getText().toString().trim());
    }
    return false;
  }
  
  public boolean isFileExists(String paramString1, String paramString2)
  {
    return new File(paramString1 + File.separator + paramString2).exists();
  }
  
  public boolean isKakaoInstalled(Context paramContext)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext.getApplicationInfo("com.kakao.talk", 128);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  public boolean isMobileNetwork(Context paramContext)
  {
    if (this.connectManager == null) {
      netWorkManagerInit(paramContext);
    }
    if (this.connectManager == null) {}
    do
    {
      do
      {
        return false;
      } while (this.connectManager.getActiveNetworkInfo() == null);
      paramContext = this.connectManager.getActiveNetworkInfo();
    } while (paramContext == null);
    switch (paramContext.getType())
    {
    case 1: 
    default: 
      return false;
    case 0: 
      return true;
    }
    return true;
  }
  
  public boolean isNetworkEnable(Context paramContext)
  {
    int j = 0;
    boolean bool2 = false;
    if (this.connectManager == null) {
      netWorkManagerInit(paramContext);
    }
    if (this.connectManager == null) {}
    int i;
    boolean bool1;
    NetworkInfo localNetworkInfo;
    do
    {
      return false;
      i = j;
      bool1 = bool2;
      if (this.connectManager.getActiveNetworkInfo() == null) {
        break;
      }
      localNetworkInfo = this.connectManager.getActiveNetworkInfo();
    } while (localNetworkInfo == null);
    switch (localNetworkInfo.getType())
    {
    default: 
      bool1 = bool2;
      i = j;
    }
    for (;;)
    {
      if (!Preferences.isNetworkAvailable(paramContext)) {
        break label146;
      }
      if ((!bool1) && (i == 0)) {
        break;
      }
      return true;
      i = 1;
      bool1 = bool2;
      continue;
      bool1 = true;
      i = j;
      continue;
      i = 1;
      bool1 = bool2;
    }
    label146:
    return bool1;
  }
  
  public boolean isPopupListAble(String paramString1, String paramString2)
  {
    Logger.debug(Logger.DEBUG.DEBUG, "popuptest isPopupListAble start ");
    HashMap localHashMap1 = (HashMap)Preferences.getPopupList(this.context);
    if (paramString2 != null)
    {
      paramString1 = localHashMap1.keySet().iterator();
      do
      {
        if (!paramString1.hasNext()) {
          return true;
        }
      } while (!paramString2.equals((String)localHashMap1.get((String)paramString1.next())));
      return false;
    }
    Logger.debug(Logger.DEBUG.DEBUG, "popuptest isPopupListAble oldListMap.size() : " + localHashMap1.size());
    if (localHashMap1.size() == 0) {
      return true;
    }
    HashMap localHashMap2 = new HashMap();
    paramString1 = checkJsonObject("PopupInfo", paramString1);
    int i;
    for (;;)
    {
      if (paramString1 == null)
      {
        Logger.debug(Logger.DEBUG.DEBUG, "popuptest isPopupListAble newListMap.size() : " + localHashMap2.size());
        i = 0;
        paramString1 = localHashMap1.keySet().iterator();
        if (paramString1.hasNext()) {
          break;
        }
        Logger.debug(Logger.DEBUG.DEBUG, "popuptest isPopupListAble newListMap.size() : " + localHashMap2.size() + " ListCount : " + i);
        if (localHashMap2.size() <= i) {
          break label498;
        }
        return true;
      }
      try
      {
        if (!paramString1.isNull("ImageUrl"))
        {
          paramString2 = paramString1.getString("ImageUrl");
          Logger.debug(Logger.DEBUG.DEBUG, "popuptest isPopupListAble url : " + paramString2);
          localHashMap2.put("ImageUrl", paramString2);
        }
        paramString2 = checkJsonObject("PopupInfo", paramString1.toString());
        paramString1 = paramString2;
      }
      catch (Exception paramString2)
      {
        Logger.debug(Logger.DEBUG.DEBUG, "popuptest isPopupListAble EXception : " + paramString2.toString());
      }
    }
    paramString2 = (String)localHashMap1.get((String)paramString1.next());
    Logger.debug(Logger.DEBUG.DEBUG, "popuptest isPopupListAble 앱껏 : " + paramString2);
    Iterator localIterator = localHashMap2.keySet().iterator();
    int j = i;
    for (;;)
    {
      i = j;
      if (!localIterator.hasNext()) {
        break;
      }
      String str = (String)localIterator.next();
      Logger.debug(Logger.DEBUG.DEBUG, "popuptest isPopupListAble 서버것 : " + (String)localHashMap2.get(str));
      if (paramString2.equalsIgnoreCase((String)localHashMap2.get(str)))
      {
        Logger.debug(Logger.DEBUG.DEBUG, "popuptest isPopupListAble 같은 url : " + (String)localHashMap2.get(str));
        j += 1;
      }
    }
    label498:
    return false;
  }
  
  public boolean isProfileEmotion()
  {
    return Preferences.isProfileEmotion(this.context);
  }
  
  public boolean isProfileGender()
  {
    return Preferences.isProfileGender(this.context);
  }
  
  public boolean isSDCARDMOUNTED()
  {
    return Environment.getExternalStorageState().equals("mounted");
  }
  
  public boolean isSixteenNine(int paramInt1, int paramInt2)
  {
    return paramInt1 / 16 * 9 == paramInt2;
  }
  
  public boolean isSktTstore(Context paramContext)
  {
    paramContext = paramContext.getPackageManager().getInstalledApplications(0);
    int j = paramContext.size();
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        return false;
      }
      if (((ApplicationInfo)paramContext.get(i)).packageName.indexOf("com.skt.skaf.A000Z00040") != -1) {
        return true;
      }
      i += 1;
    }
  }
  
  public boolean isTablet()
  {
    return Math.min(this.res.getDisplayMetrics().widthPixels, this.res.getDisplayMetrics().heightPixels) / this.res.getDisplayMetrics().densityDpi > 2.0F;
  }
  
  public void loadAlbum(Iconix paramIconix)
  {
    try
    {
      Uri localUri = getTempUri("profile.jpg");
      new Intent();
      Intent localIntent = new Intent("android.intent.action.PICK");
      localIntent.setType("vnd.android.cursor.dir/image");
      localIntent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
      localIntent.setType("image/*");
      localIntent.putExtra("crop", "true");
      localIntent.putExtra("aspectX", 3);
      localIntent.putExtra("aspectY", 4);
      localIntent.putExtra("scale", true);
      localIntent.putExtra("output", localUri);
      localIntent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
      paramIconix.startActivityForResult(localIntent, 999);
      return;
    }
    catch (Exception paramIconix)
    {
      showToast(this.context, this.res.getString(2131362007));
    }
  }
  
  public Bitmap loadBitMap(String paramString, int paramInt)
  {
    Logger.debug(Logger.DEBUG.DEBUG, "SysUtil -> loadBitMap() fileName :" + paramString);
    String str = this.innerImageForder + File.separator + paramString;
    File localFile = new File(str);
    Object localObject1;
    Object localObject2;
    if (localFile.exists())
    {
      Logger.debug(Logger.DEBUG.DEBUG, "SysUtil -> loadBitMap() 파일 있음");
      localObject1 = new BitmapFactory.Options();
      ((BitmapFactory.Options)localObject1).inJustDecodeBounds = true;
      BitmapFactory.decodeFile(str, (BitmapFactory.Options)localObject1);
      ((BitmapFactory.Options)localObject1).inJustDecodeBounds = false;
      ((BitmapFactory.Options)localObject1).inPurgeable = true;
      if ((((BitmapFactory.Options)localObject1).outHeight >= this.IMAGE_MAX_HEIGHT_SIZE) || (((BitmapFactory.Options)localObject1).outWidth >= this.IMAGE_MAX_WIDTH_SIZE)) {
        ((BitmapFactory.Options)localObject1).inSampleSize = ((int)Math.pow(2.0D, (int)Math.round(Math.log(this.IMAGE_MAX_WIDTH_SIZE / Math.max(((BitmapFactory.Options)localObject1).outHeight, ((BitmapFactory.Options)localObject1).outWidth)) / Math.log(0.5D))));
      }
      try
      {
        localObject2 = BitmapFactory.decodeFile(str, (BitmapFactory.Options)localObject1);
        localObject1 = localObject2;
        if (localObject2 == null) {
          localFile.delete();
        }
        try
        {
          paramString = saveImage(paramString, paramInt);
          localObject1 = new BitmapFactory.Options();
          Logger.e("Out Of Memory [" + this.IMAGE_MAX_HEIGHT_SIZE + "|" + this.IMAGE_MAX_WIDTH_SIZE + "]", paramString);
        }
        catch (OutOfMemoryError paramString)
        {
          try
          {
            ((BitmapFactory.Options)localObject1).inJustDecodeBounds = true;
            BitmapFactory.decodeFile(str, (BitmapFactory.Options)localObject1);
            ((BitmapFactory.Options)localObject1).inJustDecodeBounds = false;
            ((BitmapFactory.Options)localObject1).inPurgeable = true;
            if ((((BitmapFactory.Options)localObject1).outHeight >= this.IMAGE_MAX_HEIGHT_SIZE) || (((BitmapFactory.Options)localObject1).outWidth >= this.IMAGE_MAX_WIDTH_SIZE)) {
              ((BitmapFactory.Options)localObject1).inSampleSize = ((int)Math.pow(2.0D, (int)Math.round(Math.log(this.IMAGE_MAX_WIDTH_SIZE / Math.max(((BitmapFactory.Options)localObject1).outHeight, ((BitmapFactory.Options)localObject1).outWidth)) / Math.log(0.5D))));
            }
            localObject1 = BitmapFactory.decodeFile(paramString, (BitmapFactory.Options)localObject1);
            return localObject1;
          }
          catch (OutOfMemoryError paramString)
          {
            for (;;) {}
          }
          paramString = paramString;
        }
        return localObject2;
      }
      catch (OutOfMemoryError paramString)
      {
        Logger.e("Out Of Memory [" + this.IMAGE_MAX_HEIGHT_SIZE + "|" + this.IMAGE_MAX_WIDTH_SIZE + "]", paramString);
      }
    }
    for (;;)
    {
      return null;
      Logger.debug(Logger.DEBUG.DEBUG, "SysUtil -> loadBitMap() 파일 없음");
      try
      {
        localObject1 = saveImage(paramString, paramInt);
        localObject2 = new BitmapFactory.Options();
        ((BitmapFactory.Options)localObject2).inJustDecodeBounds = true;
        BitmapFactory.decodeFile((String)localObject1, (BitmapFactory.Options)localObject2);
        ((BitmapFactory.Options)localObject2).inJustDecodeBounds = false;
        ((BitmapFactory.Options)localObject2).inPurgeable = true;
        if ((((BitmapFactory.Options)localObject2).outHeight >= this.IMAGE_MAX_HEIGHT_SIZE) || (((BitmapFactory.Options)localObject2).outWidth >= this.IMAGE_MAX_WIDTH_SIZE)) {
          ((BitmapFactory.Options)localObject2).inSampleSize = ((int)Math.pow(2.0D, (int)Math.round(Math.log(this.IMAGE_MAX_WIDTH_SIZE / Math.max(((BitmapFactory.Options)localObject2).outHeight, ((BitmapFactory.Options)localObject2).outWidth)) / Math.log(0.5D))));
        }
        localObject1 = BitmapFactory.decodeFile((String)localObject1, (BitmapFactory.Options)localObject2);
        return localObject1;
      }
      catch (OutOfMemoryError localOutOfMemoryError)
      {
        Logger.e("Out Of Memory (loadBitMap [saveImage f.exists FALSE] [" + paramString + "]", localOutOfMemoryError);
      }
    }
  }
  
  public void loadCamera(Iconix paramIconix)
  {
    try
    {
      Uri localUri = getTempUri("profileCamera.jpg");
      Intent localIntent = new Intent();
      localIntent.setAction("android.media.action.IMAGE_CAPTURE");
      localIntent.putExtra("return-data", true);
      localIntent.putExtra("output", localUri);
      localIntent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
      paramIconix.startActivityForResult(localIntent, 998);
      return;
    }
    catch (Exception paramIconix)
    {
      showToast(this.context, this.res.getString(2131362007));
    }
  }
  
  public void loadCameraToAlbum(Iconix paramIconix, String paramString)
  {
    try
    {
      paramString = getTempCameraUri(paramString);
      Uri localUri = getTempUri("profile.jpg");
      Intent localIntent = new Intent("com.android.camera.action.CROP");
      localIntent.setDataAndType(paramString, "image/*");
      localIntent.putExtra("crop", "true");
      localIntent.putExtra("aspectX", 3);
      localIntent.putExtra("aspectY", 4);
      localIntent.putExtra("scale", true);
      localIntent.putExtra("output", localUri);
      localIntent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
      paramIconix.startActivityForResult(localIntent, 999);
      return;
    }
    catch (Exception paramIconix)
    {
      showToast(this.context, this.res.getString(2131362007));
    }
  }
  
  public void loadFindVideo(Iconix paramIconix)
  {
    Intent localIntent = new Intent();
    localIntent.setAction("android.intent.action.GET_CONTENT");
    localIntent.setType("video/*");
    paramIconix.startActivityForResult(localIntent, 997);
  }
  
  public Intent makeScheme(Iconix paramIconix, String paramString1, String paramString2, String paramString3, String paramString4)
  {
    if ((paramString3 == null) || (paramString2 == null) || (paramString1 == null)) {
      return null;
    }
    String str = String.format("%s://%s", new Object[] { paramString1, paramString2 });
    paramString2 = "market://details?id=" + paramString3;
    paramString1 = paramString2;
    if (isSktTstore(paramIconix))
    {
      paramString1 = paramString2;
      if (paramString4 != null) {
        paramString1 = "tstore://PRODUCT_VIEW/" + paramString4 + "/0";
      }
    }
    if (isAppExist(paramIconix, paramString3)) {
      return new Intent("android.intent.action.VIEW", Uri.parse(str));
    }
    return new Intent("android.intent.action.VIEW", Uri.parse(paramString1));
  }
  
  public String replaceZero(String paramString)
  {
    if (paramString.charAt(0) == '0') {
      return replaceZero(paramString.substring(1));
    }
    return paramString;
  }
  
  public Bitmap resizeBitmapImage(Bitmap paramBitmap, int paramInt)
  {
    int k = paramBitmap.getWidth();
    int m = paramBitmap.getHeight();
    int j = k;
    int i = m;
    float f;
    if (k > m) {
      if (paramInt < k)
      {
        f = paramInt / k;
        i = (int)(m * f);
        j = paramInt;
      }
    }
    for (;;)
    {
      Logger.debug(Logger.DEBUG.DEBUG, "resizeBitmapImage width : " + j + " height : " + i);
      return Bitmap.createScaledBitmap(paramBitmap, j, i, true);
      if (paramInt < m)
      {
        f = paramInt / m;
        j = (int)(k * f);
        i = paramInt;
      }
    }
  }
  
  public String resultComplete(Iconix paramIconix, int paramInt1, int paramInt2)
  {
    Object localObject = null;
    if (paramInt2 != -1) {
      return null;
    }
    switch (paramInt1)
    {
    default: 
      paramIconix = localObject;
    }
    for (;;)
    {
      return paramIconix;
      loadCameraToAlbum(paramIconix, this.cacheImageForder + File.separator + "profileCamera.jpg");
      paramIconix = localObject;
      continue;
      paramIconix = this.cacheImageForder + File.separator + "profile.jpg";
    }
  }
  
  public String saveEmotionHttpImage(String paramString1, String paramString2)
  {
    if (this.innerImageForder == null)
    {
      Logger.net("innerImageForder 가 없습니다");
      paramString2 = null;
    }
    String str;
    File localFile;
    do
    {
      do
      {
        do
        {
          return paramString2;
          str = getFacePath() + File.separator + paramString2;
          localFile = new File(str);
          paramString2 = str;
        } while (paramString1 == null);
        paramString2 = str;
      } while ("null".equals(paramString1));
      paramString2 = str;
    } while ("".equals(paramString1));
    if (localFile.exists()) {
      localFile.delete();
    }
    for (;;)
    {
      int i;
      FileOutputStream localFileOutputStream;
      byte[] arrayOfByte;
      int j;
      try
      {
        localFile.createNewFile();
        i = paramString1.lastIndexOf("/");
        if (paramString1.lastIndexOf("?") >= 0)
        {
          if (paramString1.lastIndexOf("=") >= 0) {
            i = paramString1.lastIndexOf("=");
          }
        }
        else
        {
          paramString2 = paramString1.substring(0, i + 1);
          paramString1 = URLEncoder.encode(paramString1.substring(i + 1), "UTF-8").replaceAll("\\+", "%20");
          paramString1 = paramString2 + paramString1;
          paramString2 = new URL(paramString1);
          Logger.debug(Logger.DEBUG.DEBUG, "urlStr : [" + paramString1 + "]");
          paramString1 = getHttpConnection(paramString2);
          i = paramString1.getContentLength();
          Logger.debug(Logger.DEBUG.DEBUG, "IMAGE : RESPONSE_MESSAGE[" + paramString1.getResponseMessage() + "] | RESPONSE_CODE : [" + paramString1.getResponseCode() + "] | CONTENT SIZE : [" + i + "]");
          if (paramString1.getResponseCode() < 400) {
            continue;
          }
          Logger.netError(paramString1);
          return str;
        }
      }
      catch (IOException paramString1)
      {
        Logger.e("httpImg IOException", paramString1);
        paramString2 = str;
        if (!localFile.exists()) {
          break;
        }
        localFile.delete();
        return str;
        i = paramString1.lastIndexOf("?");
        continue;
        paramString2 = new DataInputStream(paramString1.getInputStream());
        localFileOutputStream = new FileOutputStream(localFile);
        arrayOfByte = new byte['࿐'];
        i = 0;
        j = paramString2.read(arrayOfByte);
        if (j < 0)
        {
          paramString2.close();
          localFileOutputStream.close();
          paramString1.disconnect();
          return str;
        }
      }
      catch (Exception paramString1)
      {
        Logger.e("httpImg Exception", paramString1);
        return str;
      }
      i += j;
      localFileOutputStream.write(arrayOfByte, 0, j);
      localFileOutputStream.flush();
    }
  }
  
  public String saveHttpImage(String paramString1, String paramString2)
  {
    if (this.innerImageForder == null)
    {
      Logger.net("innerImageForder 가 없습니다");
      paramString2 = null;
    }
    String str;
    File localFile;
    do
    {
      do
      {
        do
        {
          return paramString2;
          str = this.innerImageForder + File.separator + paramString2;
          localFile = new File(str);
          paramString2 = str;
        } while (paramString1 == null);
        paramString2 = str;
      } while ("null".equals(paramString1));
      paramString2 = str;
    } while ("".equals(paramString1));
    if (localFile.exists()) {
      localFile.delete();
    }
    for (;;)
    {
      int i;
      FileOutputStream localFileOutputStream;
      byte[] arrayOfByte;
      int j;
      try
      {
        localFile.createNewFile();
        i = paramString1.lastIndexOf("/");
        if (paramString1.lastIndexOf("?") >= 0)
        {
          if (paramString1.lastIndexOf("=") >= 0) {
            i = paramString1.lastIndexOf("=");
          }
        }
        else
        {
          paramString2 = paramString1.substring(0, i + 1);
          paramString1 = URLEncoder.encode(paramString1.substring(i + 1), "UTF-8").replaceAll("\\+", "%20");
          paramString1 = paramString2 + paramString1;
          paramString2 = new URL(paramString1);
          Logger.debug(Logger.DEBUG.DEBUG, "urlStr : [" + paramString1 + "]");
          paramString1 = getHttpConnection(paramString2);
          i = paramString1.getContentLength();
          Logger.debug(Logger.DEBUG.DEBUG, "IMAGE : RESPONSE_MESSAGE[" + paramString1.getResponseMessage() + "] | RESPONSE_CODE : [" + paramString1.getResponseCode() + "] | CONTENT SIZE : [" + i + "]");
          if (paramString1.getResponseCode() < 400) {
            continue;
          }
          Logger.netError(paramString1);
          return str;
        }
      }
      catch (IOException paramString1)
      {
        Logger.e("httpImg IOException", paramString1);
        paramString2 = str;
        if (!localFile.exists()) {
          break;
        }
        localFile.delete();
        return str;
        i = paramString1.lastIndexOf("?");
        continue;
        paramString2 = new DataInputStream(paramString1.getInputStream());
        localFileOutputStream = new FileOutputStream(localFile);
        arrayOfByte = new byte['࿐'];
        i = 0;
        j = paramString2.read(arrayOfByte);
        if (j < 0)
        {
          paramString2.close();
          localFileOutputStream.close();
          paramString1.disconnect();
          return str;
        }
      }
      catch (Exception paramString1)
      {
        Logger.e("httpImg Exception", paramString1);
        return str;
      }
      i += j;
      localFileOutputStream.write(arrayOfByte, 0, j);
      localFileOutputStream.flush();
    }
  }
  
  public String saveImage(String paramString, int paramInt)
  {
    Logger.debug(Logger.DEBUG.DEBUG, "saveImage  imgName :  " + paramString);
    if (this.innerImageForder == null) {
      localObject1 = null;
    }
    Object localObject2;
    File localFile;
    do
    {
      do
      {
        do
        {
          do
          {
            return localObject1;
            localObject1 = paramString;
            localObject2 = localObject1;
            if (((String)localObject1).startsWith("http")) {
              localObject2 = Base64.encodeToString(localObject1.split("_")[0].getBytes(), 2).replaceAll("/", "-") + "_" + localObject1.split("_")[1];
            }
            localObject2 = this.innerImageForder + File.separator + (String)localObject2;
            localFile = new File((String)localObject2);
            localObject1 = localObject2;
          } while (paramString == null);
          localObject1 = localObject2;
        } while ("null".equals(paramString));
        localObject1 = localObject2;
      } while ("".equals(paramString));
      localObject1 = localObject2;
    } while (localFile.exists());
    Object localObject3 = new File(this.innerImageForder).list();
    Object localObject1 = paramString.split("_");
    if (localObject1.length > 1) {
      deleteImg(localObject1[0]);
    }
    int i;
    for (;;)
    {
      try
      {
        localFile.createNewFile();
        if ((paramInt != 2) && (paramInt != 12)) {
          break label538;
        }
        localObject1 = paramString.split("_");
        paramString = NetDto.getVideoImageUrl(paramString.replaceAll("_" + localObject1[(localObject1.length - 1)], ""));
        Logger.debug(Logger.DEBUG.DEBUG, "getVideoImageUrl IMG_URL : " + paramString);
        paramString = new URL(paramString);
        paramString = getHttpConnection(paramString);
        paramInt = paramString.getContentLength();
        Logger.debug(Logger.DEBUG.DEBUG, "IMAGE : RESPONSE_MESSAGE[" + paramString.getResponseMessage() + "] | RESPONSE_CODE : [" + paramString.getResponseCode() + "] | CONTENT SIZE : [" + paramInt + "]");
        if (paramString.getResponseCode() < 400) {
          break label813;
        }
        Logger.netError(paramString);
        return localObject2;
      }
      catch (IOException paramString)
      {
        Logger.e("saveImg IOException", paramString);
        localObject1 = localObject2;
      }
      if (!localFile.exists()) {
        break;
      }
      localFile.delete();
      return localObject2;
      localObject1 = localObject1[0];
      int j = 0;
      int k = localObject3.length;
      i = 0;
      for (;;)
      {
        if (i >= k) {}
        for (i = j; i != 0; i = 1)
        {
          return localObject1;
          localObject4 = localObject3[i];
          if (!((String)localObject4).startsWith((String)localObject1)) {
            break label531;
          }
          localObject1 = this.innerImageForder + File.separator + (String)localObject4;
        }
        break;
        label531:
        i += 1;
      }
      label538:
      if (paramInt == 3)
      {
        paramString = paramString.replaceAll("PROFILE_", "");
        localObject1 = localObject2;
        if ("0".equals(paramString)) {
          break;
        }
        paramString = NetDto.getProfileImgeUrl(paramString);
        Logger.debug(Logger.DEBUG.DEBUG, "IMG_URL : " + paramString);
        paramString = new URL(paramString);
        continue;
      }
      if (paramInt == 4)
      {
        paramString = paramString.replaceAll("PROFILE_", "");
        localObject1 = localObject2;
        if ("0".equals(paramString)) {
          break;
        }
        paramString = NetDto.getProfileImgeNewUrl(paramString);
        Logger.debug(Logger.DEBUG.DEBUG, "IMG_URL : " + paramString);
        paramString = new URL(paramString);
        continue;
      }
      if (paramInt == 5)
      {
        paramString = NetDto.getQuizStoryImgeUrl(paramString);
        Logger.debug(Logger.DEBUG.DEBUG, "IMG_URL : " + paramString);
        paramString = new URL(paramString);
      }
      else if (paramString.startsWith("Product"))
      {
        paramString = NetDto.getStoreImageUrl(paramString);
        Logger.debug(Logger.DEBUG.DEBUG, "IMG_URL : " + paramString);
        paramString = new URL(paramString);
      }
      else
      {
        paramString = NetDto.getImageUrl(paramString);
        Logger.debug(Logger.DEBUG.DEBUG, "IMG_URL : " + paramString);
        paramString = new URL(paramString);
      }
    }
    label813:
    localObject1 = new DataInputStream(paramString.getInputStream());
    localObject3 = new FileOutputStream(localFile);
    Object localObject4 = new byte['࿐'];
    paramInt = 0;
    for (;;)
    {
      i = ((DataInputStream)localObject1).read((byte[])localObject4);
      if (i < 0)
      {
        ((DataInputStream)localObject1).close();
        ((FileOutputStream)localObject3).close();
        paramString.disconnect();
        return localObject2;
      }
      paramInt += i;
      ((FileOutputStream)localObject3).write((byte[])localObject4, 0, i);
      ((FileOutputStream)localObject3).flush();
    }
  }
  
  public boolean sendSchemeUrl(Iconix paramIconix, String paramString)
  {
    if (paramString == null) {
      return false;
    }
    Object localObject2;
    Object localObject3;
    Object localObject1;
    Object localObject4;
    int j;
    int i;
    Object localObject5;
    if (paramString.startsWith("intent://"))
    {
      localObject2 = null;
      localObject3 = "";
      localObject1 = null;
      localObject4 = null;
      String[] arrayOfString = paramString.split(";");
      j = arrayOfString.length;
      i = 0;
      paramString = (String)localObject4;
      if (i >= j)
      {
        paramString = makeScheme(paramIconix, (String)localObject3, (String)localObject2, (String)localObject1, paramString);
        if (paramString == null) {
          return false;
        }
      }
      else
      {
        Object localObject7 = arrayOfString[i];
        String str;
        Object localObject6;
        if (((String)localObject7).startsWith("intent"))
        {
          localObject5 = localObject7.split("#")[0].replaceAll("intent://", "");
          str = paramString;
          localObject6 = localObject1;
          localObject4 = localObject3;
        }
        for (;;)
        {
          i += 1;
          localObject3 = localObject4;
          localObject2 = localObject5;
          localObject1 = localObject6;
          paramString = str;
          break;
          if (((String)localObject7).startsWith("scheme"))
          {
            localObject7 = ((String)localObject7).split("=");
            localObject4 = localObject3;
            localObject5 = localObject2;
            localObject6 = localObject1;
            str = paramString;
            if (localObject7.length > 1)
            {
              localObject4 = localObject7[1];
              localObject5 = localObject2;
              localObject6 = localObject1;
              str = paramString;
            }
          }
          else if (((String)localObject7).startsWith("package"))
          {
            localObject7 = ((String)localObject7).split("=");
            localObject4 = localObject3;
            localObject5 = localObject2;
            localObject6 = localObject1;
            str = paramString;
            if (localObject7.length > 1)
            {
              localObject6 = localObject7[1];
              localObject4 = localObject3;
              localObject5 = localObject2;
              str = paramString;
            }
          }
          else
          {
            localObject4 = localObject3;
            localObject5 = localObject2;
            localObject6 = localObject1;
            str = paramString;
            if (((String)localObject7).startsWith("tstore"))
            {
              localObject7 = ((String)localObject7).split("=");
              localObject4 = localObject3;
              localObject5 = localObject2;
              localObject6 = localObject1;
              str = paramString;
              if (localObject7.length > 1)
              {
                str = localObject7[1];
                localObject4 = localObject3;
                localObject5 = localObject2;
                localObject6 = localObject1;
              }
            }
          }
        }
      }
      paramIconix.bgmPause();
      paramIconix.startActivity(paramString);
      return true;
    }
    if (paramString.startsWith("app://"))
    {
      localObject1 = null;
      localObject2 = null;
      localObject4 = paramString.replaceAll("app://", "").split(";");
      j = localObject4.length;
      i = 0;
      paramString = (String)localObject2;
      if (i >= j)
      {
        paramString = runApp(paramIconix, (String)localObject1, paramString);
        if (paramString == null) {
          return false;
        }
      }
      else
      {
        localObject5 = localObject4[i];
        if (((String)localObject5).startsWith("package"))
        {
          localObject5 = ((String)localObject5).split("=");
          localObject2 = localObject1;
          localObject3 = paramString;
          if (localObject5.length > 1)
          {
            localObject2 = localObject5[1];
            localObject3 = paramString;
          }
        }
        for (;;)
        {
          i += 1;
          localObject1 = localObject2;
          paramString = (String)localObject3;
          break;
          localObject2 = localObject1;
          localObject3 = paramString;
          if (((String)localObject5).startsWith("tstore"))
          {
            localObject5 = ((String)localObject5).split("=");
            localObject2 = localObject1;
            localObject3 = paramString;
            if (localObject5.length > 1)
            {
              localObject3 = localObject5[1];
              localObject2 = localObject1;
            }
          }
        }
      }
      paramIconix.bgmPause();
      paramIconix.startActivity(paramString);
      return true;
    }
    if (paramString.startsWith("page://"))
    {
      localObject4 = paramString.replaceAll("page://", "").split(";");
      localObject1 = null;
      paramString = null;
      j = localObject4.length;
      i = 0;
      if (i >= j)
      {
        if (localObject1 == null) {
          return true;
        }
      }
      else
      {
        localObject5 = localObject4[i];
        if (((String)localObject5).startsWith("type"))
        {
          localObject5 = ((String)localObject5).split("=");
          localObject2 = paramString;
          localObject3 = localObject1;
          if (localObject5.length > 1)
          {
            localObject3 = localObject5[1];
            localObject2 = paramString;
          }
        }
        for (;;)
        {
          i += 1;
          paramString = (String)localObject2;
          localObject1 = localObject3;
          break;
          localObject2 = paramString;
          localObject3 = localObject1;
          if (((String)localObject5).startsWith("data"))
          {
            localObject5 = ((String)localObject5).split("=");
            localObject2 = paramString;
            localObject3 = localObject1;
            if (localObject5.length > 1)
            {
              localObject2 = localObject5[1];
              localObject3 = localObject1;
            }
          }
        }
      }
      localObject1 = ((String)localObject1).trim();
      if (paramString != null) {
        paramString.trim();
      }
      paramString = Iconix.PAGE.valueOf((String)localObject1);
      switch (paramString)
      {
      }
      for (;;)
      {
        return true;
        if ((this.loginInfo != null) && (this.loginInfo.isLogin()))
        {
          showNoticeDone(2131362074);
          return false;
        }
        paramIconix.loadPage(paramIconix.WEB_ACTIVITY, Iconix.PAGE.SETTING_MEMEBER_PAGE, false);
        continue;
        if ((this.loginInfo != null) && (this.loginInfo.isLogin()))
        {
          paramIconix.loadPage(PayMentPlayStore.class, paramString, false);
        }
        else
        {
          paramIconix.loadPopUpPage(PororoTvPopUp.class, paramString, PororoTvPopUp.POPUP.LOGIN, null, false);
          continue;
          if ((this.loginInfo != null) && (this.loginInfo.isLogin()))
          {
            paramIconix.loadPage(PororoTvSetting.class, paramString, false);
          }
          else
          {
            paramIconix.loadPopUpPage(PororoTvPopUp.class, paramString, PororoTvPopUp.POPUP.LOGIN, null, false);
            continue;
            if ((this.loginInfo != null) && (this.loginInfo.isLogin())) {
              paramIconix.loadPage(PororoTvContent.class, paramString, null, false);
            } else {
              paramIconix.loadPopUpPage(PororoTvPopUp.class, paramString, PororoTvPopUp.POPUP.LOGIN, null, false);
            }
          }
        }
      }
    }
    return false;
  }
  
  public Bitmap setBitmapHdpiDeviceResize(Bitmap paramBitmap)
  {
    return setBitmapDeviceResize(paramBitmap, 1.0F);
  }
  
  public void setEmotionMain(int paramInt)
  {
    Preferences.setEmotionMain(this.context, paramInt);
  }
  
  public void setEmotionVersion(int paramInt, String paramString)
  {
    Logger.debug(Logger.DEBUG.DEBUG, " util setEmotionVersion version : " + paramString);
    Preferences.setEmotionVersion(this.context, paramInt, paramString);
  }
  
  public void setGuestId(String paramString)
  {
    Logger.debug(Logger.DEBUG.DEBUG, "sysUtil GuestId -> setGuestId :" + paramString);
    Preferences.setGuestId(this.context, paramString);
  }
  
  public void setImageResource(ImageView paramImageView, int paramInt)
  {
    try
    {
      paramImageView.setImageResource(paramInt);
      return;
    }
    catch (OutOfMemoryError paramImageView)
    {
      Logger.e("OUT OF MEMORY", paramImageView);
    }
  }
  
  public void setLastProfileId(int paramInt)
  {
    Preferences.setProfileId(this.context, paramInt);
  }
  
  public void setLastStatisticsId(long paramLong)
  {
    if (paramLong != 0L) {
      Preferences.setLastStatisticsId(this.context, paramLong);
    }
  }
  
  public String setMakeExCacheFolder(Context paramContext, String paramString)
    throws IOException
  {
    paramContext = paramContext.getExternalCacheDir();
    if (paramContext == null) {
      throw new IOException();
    }
    paramContext = paramContext.getAbsolutePath() + File.separator + paramString;
    paramString = new File(paramContext);
    if (!paramString.exists()) {
      paramString.mkdir();
    }
    return paramContext;
  }
  
  public String setMakeFolder(Context paramContext, String paramString)
    throws IOException
  {
    paramContext = paramContext.getFilesDir().getAbsolutePath() + File.separator + paramString;
    paramString = new File(paramContext);
    if (!paramString.exists()) {
      paramString.mkdir();
    }
    return paramContext;
  }
  
  public String setMakeFolderExternal(Context paramContext, String paramString)
    throws IOException
  {
    paramContext = paramContext.getExternalFilesDir(null).getAbsolutePath() + File.separator + paramString;
    paramString = new File(paramContext);
    if (!paramString.exists()) {
      paramString.mkdir();
    }
    return paramContext;
  }
  
  public String setMakeSubFolder(Context paramContext, String paramString1, String paramString2)
    throws IOException
  {
    paramContext = paramString1 + File.separator + paramString2;
    paramString1 = new File(paramContext);
    if (!paramString1.exists()) {
      paramString1.mkdir();
    }
    return paramContext;
  }
  
  public void setMaxImgSize(int paramInt1, int paramInt2)
  {
    this.IMAGE_MAX_WIDTH_SIZE = paramInt1;
    this.IMAGE_MAX_HEIGHT_SIZE = paramInt2;
  }
  
  public void setProfileAge(String paramString)
  {
    Date localDate = new Date(System.currentTimeMillis());
    int i = Integer.parseInt(new SimpleDateFormat("yyyy").format(localDate));
    int j = Integer.parseInt(paramString);
    Preferences.setProfileAge(this.context, Integer.toString(i - j + 1));
  }
  
  public void setProfileEmotion(boolean paramBoolean)
  {
    Preferences.setProfileEmotion(this.context, paramBoolean);
  }
  
  public void setProfileGender(boolean paramBoolean)
  {
    Preferences.setProfileGender(this.context, paramBoolean);
  }
  
  public void setProfileName(String paramString)
  {
    Preferences.setProfileName(this.context, paramString);
    Logger.debug(Logger.DEBUG.DEBUG, "setProfileName : " + paramString);
  }
  
  public void setProfileSex(String paramString)
  {
    Preferences.setProfileSex(this.context, paramString);
  }
  
  public void setServerDataSync(ArrayList<EmotionInfo> paramArrayList)
  {
    if (paramArrayList.size() == 0)
    {
      emotinClear();
      return;
    }
    Iterator localIterator = paramArrayList.iterator();
    while (localIterator.hasNext())
    {
      EmotionInfo localEmotionInfo = (EmotionInfo)localIterator.next();
      int i = localEmotionInfo.getEmotionType();
      String str1 = localEmotionInfo.getImageVersion();
      String str2 = getEmotionVersion(i);
      String str3 = localEmotionInfo.getImageUrl();
      boolean bool = localEmotionInfo.isDefault();
      BigInteger localBigInteger;
      if (!str1.equalsIgnoreCase(str2))
      {
        localBigInteger = new BigInteger(str1);
        paramArrayList = new BigInteger("0");
        if (str2 != null) {
          paramArrayList = new BigInteger(str2);
        }
        if (localBigInteger.compareTo(paramArrayList) != 0) {
          break label234;
        }
        Logger.debug(Logger.DEBUG.DEBUG, "version 동일 ");
        label138:
        Logger.debug(Logger.DEBUG.DEBUG, "emotion serverVersion : " + str1 + " appversion : " + str2);
        if (localBigInteger.compareTo(paramArrayList) == 1) {
          Logger.debug(Logger.DEBUG.DEBUG, "setServerDataSync 서버 이미지 버전이 더 높다  ");
        }
      }
      try
      {
        getURLResourceEmotin(null, str3, getFacePath(), Integer.toString(i), true);
        setEmotionVersion(localEmotionInfo.getEmotionType(), localEmotionInfo.getImageVersion());
        if (!bool) {
          continue;
        }
        setEmotionMain(i);
        continue;
        label234:
        if (localBigInteger.compareTo(paramArrayList) == -1)
        {
          Logger.debug(Logger.DEBUG.DEBUG, "version 파라미터보다 작은 수입니다. ");
          break label138;
        }
        if (localBigInteger.compareTo(paramArrayList) != 1) {
          break label138;
        }
        Logger.debug(Logger.DEBUG.DEBUG, "version 파라미터보다 큰 수입니다. ");
      }
      catch (Exception paramArrayList)
      {
        for (;;)
        {
          Logger.debug(Logger.DEBUG.DEBUG, "setServerDataSync EXception : " + paramArrayList.toString());
        }
      }
    }
  }
  
  public void showDataPicker(int paramInt1, int paramInt2, int paramInt3, DatePickerDialog.OnDateSetListener paramOnDateSetListener)
  {
    new DatePickerDialog(this.context, paramOnDateSetListener, paramInt1, paramInt2, paramInt3).show();
  }
  
  public AlertDialog.Builder showListAlert(Context paramContext, String paramString, int paramInt, String[] paramArrayOfString, DialogInterface.OnClickListener paramOnClickListener)
  {
    int i = paramInt;
    if (paramInt == -1) {
      i = 17367057;
    }
    paramArrayOfString = new ArrayAdapter(paramContext, i, paramArrayOfString);
    paramContext = new AlertDialog.Builder(paramContext);
    if (paramString != null) {
      paramContext.setTitle(paramString);
    }
    paramContext.setAdapter(paramArrayOfString, paramOnClickListener);
    return paramContext;
  }
  
  public NoticeDialog showNoticeDialog()
  {
    if (this.dialog != null)
    {
      this.dialog.dismiss();
      this.dialog = null;
    }
    this.dialog = new NoticeDialog(this.context);
    return this.dialog;
  }
  
  public void showNoticeDone(int paramInt)
  {
    NoticeDialog.OnNoticeListener local1 = new NoticeDialog.OnNoticeListener()
    {
      public void onNoticeResult(View paramAnonymousView)
      {
        SysUtil.this.dialog.dismiss();
      }
    };
    if (this.dialog != null)
    {
      this.dialog.dismiss();
      this.dialog = null;
    }
    this.dialog = new NoticeDialog(this.context);
    this.dialog.setText(paramInt);
    this.dialog.setLeftButton(2131362107, local1);
    this.dialog.show();
  }
  
  public void showNoticeDone(int paramInt, NoticeDialog.OnNoticeListener paramOnNoticeListener)
  {
    if (this.dialog != null)
    {
      this.dialog.dismiss();
      this.dialog = null;
    }
    this.dialog = new NoticeDialog(this.context);
    this.dialog.setText(paramInt);
    this.dialog.setLeftButton(2131362107, paramOnNoticeListener);
    this.dialog.show();
  }
  
  public void showNoticeDone(String paramString)
  {
    NoticeDialog.OnNoticeListener local2 = new NoticeDialog.OnNoticeListener()
    {
      public void onNoticeResult(View paramAnonymousView)
      {
        SysUtil.this.dialog.dismiss();
      }
    };
    if (this.dialog != null)
    {
      this.dialog.dismiss();
      this.dialog = null;
    }
    if (("null".equals(paramString)) || ("".equals(paramString)))
    {
      showNoticeDone(2131362037);
      return;
    }
    this.dialog = new NoticeDialog(this.context);
    this.dialog.setText(paramString);
    this.dialog.setLeftButton(2131362107, local2);
    this.dialog.show();
  }
  
  public void showNoticeDone(String paramString, NoticeDialog.OnNoticeListener paramOnNoticeListener)
  {
    if (this.dialog != null)
    {
      this.dialog.dismiss();
      this.dialog = null;
    }
    this.dialog = new NoticeDialog(this.context);
    this.dialog.setText(paramString);
    this.dialog.setLeftButton(2131362107, paramOnNoticeListener);
    this.dialog.show();
  }
  
  public void showNoticeTwoBtn(int paramInt1, int paramInt2, int paramInt3, NoticeDialog.OnNoticeListener paramOnNoticeListener)
  {
    if (this.dialog != null)
    {
      this.dialog.dismiss();
      this.dialog = null;
    }
    this.dialog = new NoticeDialog(this.context);
    this.dialog.setText(paramInt1);
    this.dialog.setLeftButton(paramInt2, paramOnNoticeListener);
    this.dialog.setRightButton(paramInt3, paramOnNoticeListener);
    this.dialog.show();
  }
  
  public void showNoticeTwoBtn(Spanned paramSpanned, int paramInt1, int paramInt2, NoticeDialog.OnNoticeListener paramOnNoticeListener)
  {
    if (this.dialog != null)
    {
      this.dialog.dismiss();
      this.dialog = null;
    }
    this.dialog = new NoticeDialog(this.context);
    this.dialog.setText(paramSpanned);
    this.dialog.setLeftButton(paramInt1, paramOnNoticeListener);
    this.dialog.setRightButton(paramInt2, paramOnNoticeListener);
    this.dialog.show();
  }
  
  public void showNoticeTwoBtn(String paramString, int paramInt1, int paramInt2, NoticeDialog.OnNoticeListener paramOnNoticeListener)
  {
    if (this.dialog != null)
    {
      this.dialog.dismiss();
      this.dialog = null;
    }
    this.dialog = new NoticeDialog(this.context);
    this.dialog.setText(paramString);
    this.dialog.setLeftButton(paramInt1, paramOnNoticeListener);
    this.dialog.setRightButton(paramInt2, paramOnNoticeListener);
    this.dialog.show();
  }
  
  public void showToast(Context paramContext, String paramString)
  {
    if (((paramContext instanceof Context)) && (paramString != null)) {
      Toast.makeText(paramContext, paramString, 0).show();
    }
  }
  
  /* Error */
  public boolean unzip(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: getstatic 576	kr/co/iconix/pororotv/util/Logger$DEBUG:DEBUG	Lkr/co/iconix/pororotv/util/Logger$DEBUG;
    //   3: new 374	java/lang/StringBuilder
    //   6: dup
    //   7: ldc_w 2073
    //   10: invokespecial 379	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   13: aload_1
    //   14: invokevirtual 388	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   17: ldc_w 2075
    //   20: invokevirtual 388	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   23: aload_2
    //   24: invokevirtual 388	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   27: invokevirtual 399	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   30: invokestatic 582	kr/co/iconix/pororotv/util/Logger:debug	(Lkr/co/iconix/pororotv/util/Logger$DEBUG;Ljava/lang/String;)V
    //   33: new 2077	java/util/zip/ZipInputStream
    //   36: dup
    //   37: new 2079	java/io/BufferedInputStream
    //   40: dup
    //   41: new 2081	java/io/FileInputStream
    //   44: dup
    //   45: aload_1
    //   46: invokespecial 2082	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
    //   49: invokespecial 2083	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
    //   52: invokespecial 2084	java/util/zip/ZipInputStream:<init>	(Ljava/io/InputStream;)V
    //   55: astore 5
    //   57: aconst_null
    //   58: astore 4
    //   60: aload 5
    //   62: invokevirtual 2088	java/util/zip/ZipInputStream:getNextEntry	()Ljava/util/zip/ZipEntry;
    //   65: astore 4
    //   67: aload 4
    //   69: ifnonnull +22 -> 91
    //   72: aload 5
    //   74: invokevirtual 2089	java/util/zip/ZipInputStream:close	()V
    //   77: new 468	java/io/File
    //   80: dup
    //   81: aload_1
    //   82: invokespecial 478	java/io/File:<init>	(Ljava/lang/String;)V
    //   85: invokevirtual 786	java/io/File:delete	()Z
    //   88: pop
    //   89: iconst_1
    //   90: ireturn
    //   91: aload 4
    //   93: invokevirtual 2092	java/util/zip/ZipEntry:getName	()Ljava/lang/String;
    //   96: astore 6
    //   98: getstatic 576	kr/co/iconix/pororotv/util/Logger$DEBUG:DEBUG	Lkr/co/iconix/pororotv/util/Logger$DEBUG;
    //   101: new 374	java/lang/StringBuilder
    //   104: dup
    //   105: ldc_w 2094
    //   108: invokespecial 379	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   111: aload 6
    //   113: invokevirtual 388	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   116: invokevirtual 399	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   119: invokestatic 582	kr/co/iconix/pororotv/util/Logger:debug	(Lkr/co/iconix/pororotv/util/Logger$DEBUG;Ljava/lang/String;)V
    //   122: new 468	java/io/File
    //   125: dup
    //   126: new 374	java/lang/StringBuilder
    //   129: dup
    //   130: aload_2
    //   131: invokestatic 474	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   134: invokespecial 379	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   137: getstatic 477	java/io/File:separator	Ljava/lang/String;
    //   140: invokevirtual 388	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   143: aload 6
    //   145: invokevirtual 388	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   148: invokevirtual 399	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   151: invokespecial 478	java/io/File:<init>	(Ljava/lang/String;)V
    //   154: astore 6
    //   156: getstatic 576	kr/co/iconix/pororotv/util/Logger$DEBUG:DEBUG	Lkr/co/iconix/pororotv/util/Logger$DEBUG;
    //   159: new 374	java/lang/StringBuilder
    //   162: dup
    //   163: ldc_w 2096
    //   166: invokespecial 379	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   169: aload 6
    //   171: invokevirtual 2097	java/io/File:toString	()Ljava/lang/String;
    //   174: invokevirtual 388	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   177: invokevirtual 399	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   180: invokestatic 582	kr/co/iconix/pororotv/util/Logger:debug	(Lkr/co/iconix/pororotv/util/Logger$DEBUG;Ljava/lang/String;)V
    //   183: aload 6
    //   185: invokevirtual 482	java/io/File:exists	()Z
    //   188: ifeq +9 -> 197
    //   191: aload 6
    //   193: invokevirtual 786	java/io/File:delete	()Z
    //   196: pop
    //   197: aload 4
    //   199: invokevirtual 2098	java/util/zip/ZipEntry:isDirectory	()Z
    //   202: ifeq +19 -> 221
    //   205: aload 6
    //   207: invokevirtual 585	java/io/File:mkdirs	()Z
    //   210: pop
    //   211: goto -151 -> 60
    //   214: astore_1
    //   215: aload_1
    //   216: invokevirtual 2099	java/io/FileNotFoundException:printStackTrace	()V
    //   219: iconst_0
    //   220: ireturn
    //   221: sipush 4096
    //   224: newarray byte
    //   226: astore 7
    //   228: new 2101	java/io/BufferedOutputStream
    //   231: dup
    //   232: new 866	java/io/FileOutputStream
    //   235: dup
    //   236: aload 6
    //   238: invokespecial 1764	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   241: sipush 4096
    //   244: invokespecial 2104	java/io/BufferedOutputStream:<init>	(Ljava/io/OutputStream;I)V
    //   247: astore 4
    //   249: aload 5
    //   251: aload 7
    //   253: iconst_0
    //   254: sipush 4096
    //   257: invokevirtual 2107	java/util/zip/ZipInputStream:read	([BII)I
    //   260: istore_3
    //   261: iload_3
    //   262: iconst_m1
    //   263: if_icmpne +16 -> 279
    //   266: aload 4
    //   268: invokevirtual 2108	java/io/BufferedOutputStream:flush	()V
    //   271: aload 4
    //   273: invokevirtual 2109	java/io/BufferedOutputStream:close	()V
    //   276: goto -216 -> 60
    //   279: aload 4
    //   281: aload 7
    //   283: iconst_0
    //   284: iload_3
    //   285: invokevirtual 2110	java/io/BufferedOutputStream:write	([BII)V
    //   288: goto -39 -> 249
    //   291: astore_1
    //   292: goto -77 -> 215
    //   295: astore_1
    //   296: aload_1
    //   297: invokevirtual 2111	java/io/IOException:printStackTrace	()V
    //   300: iconst_0
    //   301: ireturn
    //   302: astore_1
    //   303: goto -7 -> 296
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	306	0	this	SysUtil
    //   0	306	1	paramString1	String
    //   0	306	2	paramString2	String
    //   260	25	3	i	int
    //   58	222	4	localObject1	Object
    //   55	195	5	localZipInputStream	java.util.zip.ZipInputStream
    //   96	141	6	localObject2	Object
    //   226	56	7	arrayOfByte	byte[]
    // Exception table:
    //   from	to	target	type
    //   60	67	214	java/io/FileNotFoundException
    //   72	89	214	java/io/FileNotFoundException
    //   91	197	214	java/io/FileNotFoundException
    //   197	211	214	java/io/FileNotFoundException
    //   221	249	214	java/io/FileNotFoundException
    //   33	57	291	java/io/FileNotFoundException
    //   249	261	291	java/io/FileNotFoundException
    //   266	276	291	java/io/FileNotFoundException
    //   279	288	291	java/io/FileNotFoundException
    //   33	57	295	java/io/IOException
    //   249	261	295	java/io/IOException
    //   266	276	295	java/io/IOException
    //   279	288	295	java/io/IOException
    //   60	67	302	java/io/IOException
    //   72	89	302	java/io/IOException
    //   91	197	302	java/io/IOException
    //   197	211	302	java/io/IOException
    //   221	249	302	java/io/IOException
  }
  
  public TranslateAnimation upDownAnimation()
  {
    TranslateAnimation localTranslateAnimation = new TranslateAnimation(0.0F, 0.0F, 0.0F, 100.0F);
    localTranslateAnimation.setDuration(300L);
    localTranslateAnimation.setFillEnabled(true);
    return localTranslateAnimation;
  }
  
  public TranslateAnimation upDownAnimation(int paramInt)
  {
    TranslateAnimation localTranslateAnimation = new TranslateAnimation(0.0F, 0.0F, paramInt, 0.0F);
    localTranslateAnimation.setDuration(300L);
    localTranslateAnimation.setFillEnabled(true);
    return localTranslateAnimation;
  }
  
  public static abstract interface OnImgListener
  {
    public abstract void Event(View paramView);
  }
  
  public static abstract interface OnImgPopupListener
  {
    public abstract void onImgPopup(Bitmap paramBitmap);
  }
}
