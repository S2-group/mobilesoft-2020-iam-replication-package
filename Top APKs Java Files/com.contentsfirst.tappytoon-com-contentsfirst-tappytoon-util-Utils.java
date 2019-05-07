package com.contentsfirst.tappytoon.util;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Vibrator;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import com.contentsfirst.tappytoon.application.TappytoonApplication;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils
{
  public static final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}\\@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})+");
  public static final Pattern PHONE_PATTERN = Pattern.compile("(\\+[0-9]+[\\- \\.]*)?(\\([0-9]+\\)[\\- \\.]*)?([0-9][0-9\\- \\.][0-9\\- \\.]+[0-9])");
  
  public Utils() {}
  
  public static boolean checkInstalledPackage(String paramString)
  {
    Iterator localIterator = TappytoonApplication.getBaseApplicationContext().getPackageManager().getInstalledApplications(8192).iterator();
    while (localIterator.hasNext()) {
      if (((ApplicationInfo)localIterator.next()).packageName.equals(paramString)) {
        return true;
      }
    }
    return false;
  }
  
  public static int convertDpToPixel(float paramFloat)
  {
    return (int)(TappytoonApplication.getBaseApplicationContext().getResources().getDisplayMetrics().densityDpi / 160.0F * paramFloat);
  }
  
  public static int convertPixelsToDp(float paramFloat)
  {
    return (int)(paramFloat / (TappytoonApplication.getBaseApplicationContext().getResources().getDisplayMetrics().densityDpi / 160.0F));
  }
  
  public static void copyToClipboard(String paramString)
  {
    TappytoonApplication localTappytoonApplication = TappytoonApplication.getBaseApplicationContext();
    if (Build.VERSION.SDK_INT < 11)
    {
      ((android.text.ClipboardManager)localTappytoonApplication.getSystemService("clipboard")).setText(paramString);
      return;
    }
    ((android.content.ClipboardManager)localTappytoonApplication.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("hey_temp_messages", paramString));
  }
  
  public static File createFilename(File paramFile)
  {
    Object localObject;
    if (createNewFile(paramFile))
    {
      localObject = paramFile;
      return localObject;
    }
    String str2 = paramFile.getName();
    int i = str2.lastIndexOf(".");
    String str1;
    if (i != -1) {
      str1 = str2.substring(0, i);
    }
    for (str2 = str2.substring(i);; str2 = "")
    {
      i = 0;
      for (;;)
      {
        localObject = paramFile;
        if (createNewFile(paramFile)) {
          break;
        }
        localObject = paramFile;
        if (i >= 9999) {
          break;
        }
        i += 1;
        localObject = str1 + i + str2;
        paramFile = new File(paramFile.getParent(), (String)localObject);
      }
      str1 = str2;
    }
  }
  
  private static boolean createNewFile(File paramFile)
  {
    try
    {
      boolean bool = paramFile.createNewFile();
      return bool;
    }
    catch (IOException paramFile) {}
    return false;
  }
  
  public static void deleteFile(String paramString)
  {
    paramString = new File(paramString);
    try
    {
      if (paramString.exists()) {
        paramString.delete();
      }
      return;
    }
    catch (Exception paramString)
    {
      paramString.getStackTrace();
    }
  }
  
  public static int dpToPx(Resources paramResources, int paramInt)
  {
    return (int)TypedValue.applyDimension(1, paramInt, paramResources.getDisplayMetrics());
  }
  
  public static boolean existFile(String paramString)
  {
    paramString = new File(paramString);
    try
    {
      boolean bool = paramString.exists();
      if (bool) {}
      return true;
    }
    catch (Exception paramString) {}
    return false;
  }
  
  public static int getDisplayHeight(Context paramContext)
  {
    return ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getHeight();
  }
  
  public static int getDisplayWidth(Context paramContext)
  {
    if (("google_sdk".equals(Build.PRODUCT)) || ("eeepc".equals(Build.PRODUCT))) {
      return 480;
    }
    return ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getWidth();
  }
  
  public static String getHashKey(Context paramContext)
  {
    MessageDigest localMessageDigest = null;
    try
    {
      Signature[] arrayOfSignature = TappytoonApplication.getBaseApplicationContext().getPackageManager().getPackageInfo(paramContext.getPackageName(), 64).signatures;
      paramContext = localMessageDigest;
      if (arrayOfSignature.length < 0)
      {
        paramContext = arrayOfSignature[0];
        localMessageDigest = MessageDigest.getInstance("SHA");
        localMessageDigest.update(paramContext.toByteArray());
        Log.i("Hash Key", Base64.encodeToString(localMessageDigest.digest(), 0));
        paramContext = Base64.encodeToString(localMessageDigest.digest(), 0);
      }
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      return null;
    }
    catch (NoSuchAlgorithmException paramContext) {}
    return null;
  }
  
  public static void hideKeyboard(EditText paramEditText)
  {
    ((InputMethodManager)TappytoonApplication.getBaseApplicationContext().getSystemService("input_method")).hideSoftInputFromWindow(paramEditText.getWindowToken(), 0);
  }
  
  public static String humanReadableByteCount(long paramLong, boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (int i = 1000; paramLong < i; i = 1024) {
      return paramLong + " B";
    }
    int j = (int)(Math.log(paramLong) / Math.log(i));
    StringBuilder localStringBuilder = new StringBuilder();
    if (paramBoolean)
    {
      str = "kMGTPE";
      localStringBuilder = localStringBuilder.append(str.charAt(j - 1));
      if (!paramBoolean) {
        break label152;
      }
    }
    label152:
    for (String str = "";; str = "i")
    {
      str = str;
      return String.format("%.1f %sB", new Object[] { Double.valueOf(paramLong / Math.pow(i, j)), str });
      str = "KMGTPE";
      break;
    }
  }
  
  public static boolean isExternalStorageMounted()
  {
    return Environment.getExternalStorageState().equals("mounted");
  }
  
  public static boolean isNetworkState()
  {
    ConnectivityManager localConnectivityManager = (ConnectivityManager)TappytoonApplication.getBaseApplicationContext().getSystemService("connectivity");
    if (localConnectivityManager.getActiveNetworkInfo() != null) {}
    switch (localConnectivityManager.getActiveNetworkInfo().getType())
    {
    default: 
      return false;
    case 1: 
      return true;
    case 0: 
      return true;
    }
    return true;
  }
  
  public static boolean isValidEmail(String paramString)
  {
    return EMAIL_ADDRESS_PATTERN.matcher(paramString).matches();
  }
  
  public static boolean isValidPhone(String paramString)
  {
    return PHONE_PATTERN.matcher(paramString).matches();
  }
  
  public static void printKeyHash(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 64).signatures;
      int j = paramContext.length;
      int i = 0;
      while (i < j)
      {
        Object localObject = paramContext[i];
        MessageDigest localMessageDigest = MessageDigest.getInstance("SHA");
        localMessageDigest.update(localObject.toByteArray());
        Log.d("KeyHash:", Base64.encodeToString(localMessageDigest.digest(), 0));
        i += 1;
      }
      return;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      Log.e("name not found", paramContext.toString());
      return;
    }
    catch (NoSuchAlgorithmException paramContext)
    {
      Log.e("no such an algorithm", paramContext.toString());
      return;
    }
    catch (Exception paramContext)
    {
      Log.e("exception", paramContext.toString());
    }
  }
  
  public static String readableFileSize(long paramLong)
  {
    if (paramLong <= 0L) {
      return "0";
    }
    int i = (int)(Math.log10(paramLong) / Math.log10(1024.0D));
    return new DecimalFormat("#,##0.#").format(paramLong / Math.pow(1024.0D, i)) + " " + new String[] { "B", "KB", "MB", "GB", "TB" }[i];
  }
  
  public static void recursiveRecycle(View paramView)
  {
    if (paramView == null) {
      return;
    }
    paramView.setBackgroundResource(0);
    paramView.setBackgroundDrawable(null);
    ViewGroup localViewGroup;
    int j;
    int i;
    if ((paramView instanceof ViewGroup))
    {
      localViewGroup = (ViewGroup)paramView;
      j = localViewGroup.getChildCount();
      i = 0;
    }
    for (;;)
    {
      if (i < j) {}
      try
      {
        recursiveRecycle(localViewGroup.getChildAt(i));
        i += 1;
        continue;
        if (!(paramView instanceof AdapterView)) {}
        try
        {
          localViewGroup.removeAllViews();
          if ((paramView instanceof ImageView))
          {
            ((ImageView)paramView).setImageBitmap(null);
            ((ImageView)paramView).setImageDrawable(null);
            ((ImageView)paramView).setBackgroundResource(0);
            ((ImageView)paramView).setBackgroundDrawable(null);
          }
          return;
        }
        catch (Exception localException1)
        {
          for (;;) {}
        }
      }
      catch (Exception localException2)
      {
        for (;;) {}
      }
    }
  }
  
  public static void recursiveRecycle(List<WeakReference<View>> paramList)
  {
    paramList = paramList.iterator();
    while (paramList.hasNext()) {
      recursiveRecycle((View)((WeakReference)paramList.next()).get());
    }
  }
  
  public static void removeAllCookie(Context paramContext)
  {
    CookieSyncManager.createInstance(paramContext);
    paramContext = CookieManager.getInstance();
    paramContext.removeSessionCookie();
    paramContext.removeExpiredCookie();
    paramContext.removeAllCookie();
    CookieSyncManager.getInstance().sync();
  }
  
  public static void sendSms(String paramString)
  {
    if ((paramString == null) || (paramString.equals(""))) {
      return;
    }
    paramString = new Intent("android.intent.action.SENDTO", Uri.parse("smsto:" + paramString));
    paramString.putExtra("sms_body", "");
    TappytoonApplication.getBaseApplicationContext().startActivity(paramString);
  }
  
  public static void showKeyboard(EditText paramEditText)
  {
    InputMethodManager localInputMethodManager = (InputMethodManager)TappytoonApplication.getBaseApplicationContext().getSystemService("input_method");
    localInputMethodManager.showSoftInput(paramEditText, 1);
    localInputMethodManager.toggleSoftInput(2, 1);
    paramEditText.requestFocus();
  }
  
  public static void vibrate(long paramLong)
  {
    ((Vibrator)TappytoonApplication.getBaseApplicationContext().getSystemService("vibrator")).vibrate(paramLong);
  }
  
  public static class ByteLengthFilter
    implements InputFilter
  {
    private String mCharset;
    protected int mMaxByte;
    
    public ByteLengthFilter(int paramInt)
    {
      this.mMaxByte = paramInt;
    }
    
    public ByteLengthFilter(int paramInt, String paramString)
    {
      this.mMaxByte = paramInt;
      this.mCharset = paramString;
    }
    
    private int getByteLength(String paramString)
    {
      return paramString.getBytes().length;
    }
    
    protected int calculateMaxLength(String paramString)
    {
      if (getByteLength(paramString) == 0) {
        return 0;
      }
      return this.mMaxByte - (getByteLength(paramString) - paramString.length());
    }
    
    public CharSequence filter(CharSequence paramCharSequence, int paramInt1, int paramInt2, Spanned paramSpanned, int paramInt3, int paramInt4)
    {
      String str = new String();
      str = str + paramSpanned.subSequence(0, paramInt3);
      str = str + paramCharSequence.subSequence(paramInt1, paramInt2);
      paramInt4 = calculateMaxLength(str + paramSpanned.subSequence(paramInt4, paramSpanned.length())) - (paramSpanned.length() - (paramInt4 - paramInt3));
      paramInt3 = paramInt4;
      if (paramInt4 < 0) {
        paramInt3 = 0;
      }
      paramInt4 = plusMaxLength(paramSpanned.toString(), paramCharSequence.toString(), paramInt1);
      if ((paramInt3 <= 0) && (paramInt4 <= 0)) {
        return "";
      }
      if (paramInt3 >= paramInt2 - paramInt1) {
        return null;
      }
      if ((paramSpanned.length() == 0) && (paramInt4 <= 0)) {
        return paramCharSequence.subSequence(paramInt1, paramInt1 + paramInt3);
      }
      if (paramInt4 <= 0) {
        return paramCharSequence.subSequence(paramInt1, paramCharSequence.length() - 1 + paramInt1);
      }
      return paramCharSequence.subSequence(paramInt1, paramInt1 + paramInt4);
    }
    
    protected int plusMaxLength(String paramString1, String paramString2, int paramInt)
    {
      int i = paramString2.length();
      int j = this.mMaxByte;
      int k = getByteLength(paramString1.toString());
      while (getByteLength(paramString2.subSequence(paramInt, paramInt + i).toString()) > j - k) {
        i -= 1;
      }
      return i;
    }
  }
}
