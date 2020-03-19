package o;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipData.Item;
import android.content.ClipboardManager;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaScannerConnection;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.Uri;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.os.SystemClock;
import android.os.Vibrator;
import android.provider.ContactsContract.Contacts;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.telephony.SmsManager;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.style.TypefaceSpan;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.util.Patterns;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import com.crashlytics.android.Crashlytics;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.PhoneNumberUtil.if;
import com.google.i18n.phonenumbers.PhoneNumberUtil.ˊ;
import com.pinger.common.app.PingerApplication;
import com.pinger.common.logger.ServerLogger;
import com.pinger.common.store.Preferences.AUx;
import com.pinger.common.store.Preferences.auX;
import com.pinger.common.store.Preferences.con;
import com.pinger.common.store.Preferences.if;
import com.pinger.common.store.Preferences.ʽ.If;
import com.pinger.common.store.Preferences.ˎ;
import com.pinger.common.store.Preferences.ˏ;
import com.pinger.common.store.Preferences.ͺ.If;
import com.pinger.common.store.Preferences.ͺ.ˊ;
import com.pinger.common.store.Preferences.ͺ.ˋ;
import com.pinger.common.store.Preferences.ᐝ;
import com.pinger.textfree.call.activities.CallScreen;
import com.pinger.textfree.call.activities.ConversationActivity;
import com.pinger.textfree.call.activities.InboxActivity;
import com.pinger.textfree.call.activities.WebViewActivity;
import com.pinger.textfree.call.activities.base.TFActivity;
import com.pinger.textfree.call.app.TFApplication;
import com.pinger.textfree.call.app.TFService;
import com.pinger.textfree.call.beans.ContactAddressAndName;
import com.pinger.textfree.call.messages.NativeCommunicationsSyncService;
import com.pinger.textfree.call.swipe.SwipeLayout;
import com.pinger.textfree.call.swipe.SwipeLayout.ᐝ;
import com.pinger.textfree.call.ui.ConversationMediaContainer.iF;
import com.pinger.textfree.call.ui.FormValidationEditText;
import com.pinger.textfree.call.util.VoicemailController;
import com.pinger.textfree.call.voice.managers.VoiceManager;
import com.pinger.voice.ConnectionQuality;
import com.pinger.voice.PTAPICallBase;
import com.pinger.voice.PhoneAddress;
import com.pinger.voice.client.PTAPISoftphoneAsync;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ahi
{
  public ahi() {}
  
  public static class AUX
  {
    private static String[] ˊ = { "http://www.netshark.us/valve/sound/sleighbells3.wav", "http://stephane.brechet.free.fr/Sons/new/13_BAD.WAV", "http://har-bal.com/reference/brass/C_trumpet_F4.wav" };
    private static Random ˋ = new Random();
    private static String[] ˎ = { "http://images.alphacoders.com/462/462944.jpg", "http://www.hdwallpapersnew.net/wp-content/uploads/2015/11/skater-snow-skiing-desktop-background-wide-high-resolution-wallpaper-free.jpg", "http://www.hdwallpapersnew.net/wp-content/uploads/2015/11/extremism-skiing-wide-high-definition-wallpaper-for-deskop-background-download-skiing-free.jpg", "http://wallpaper.pickywallpapers.com/1920x1080/skiing.jpg" };
    private static String[] ˏ = { "http://www.templates.com/wp-content/uploads/2010/02/Salto-Ski-Wallpaper.jpg", "http://www.techcredo.com/wp-content/uploads/2010/09/wallpaper_ski.jpg", "http://www.templates.com/wp-content/uploads/2010/02/Skiing-Wallpaper.jpg", "http://blacktomato.com/wp-content/uploads/2009/02/ski_content_resized.jpg", "http://coresites-cdn.factorymedia.com/skiunion/wp-content/uploads/2012/01/wallpaper-flash.jpg" };
    
    public AUX() {}
    
    private static String ˎ(int paramInt)
    {
      StringBuilder localStringBuilder = new StringBuilder(paramInt);
      int i = 0;
      while (i < paramInt)
      {
        localStringBuilder.append((char)(ˋ.nextInt(26) + 97));
        i += 1;
      }
      return localStringBuilder.toString();
    }
    
    public static String ॱ(int paramInt)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      int i = 0;
      while (i < paramInt)
      {
        localStringBuilder.append(ˎ(ˋ.nextInt(20))).append(" ");
        i += 1;
      }
      return localStringBuilder.toString();
    }
    
    public static void ॱ(ahi.AUX.ˊ paramˊ, int paramInt1, int paramInt2, byte paramByte)
    {
      VZ.ॱ(TFApplication.ˏॱ().getApplicationContext());
      ArrayList localArrayList = new ArrayList();
      long l2 = Math.abs(ˋ.nextLong()) % 10000000000L;
      int i = 0;
      while (i < paramInt1 * paramInt2)
      {
        long l1 = l2;
        if (i % paramInt2 == 0) {
          l1 = l2 + 1L;
        }
        byte b;
        if (i % 5 == 4)
        {
          if (i % 2 == 0) {
            b = 1;
          } else {
            b = 2;
          }
          localArrayList.add(new UB(String.valueOf(l1), (byte)1, (byte)4, b, "", System.currentTimeMillis(), ˊ[ˋ.nextInt(3)], paramByte));
        }
        else if (i % 5 == 3)
        {
          if (i % 2 == 0) {
            b = 1;
          } else {
            b = 2;
          }
          localArrayList.add(new UB(String.valueOf(l1), (byte)1, (byte)6, b, "", System.currentTimeMillis(), ˏ[ˋ.nextInt(5)], paramByte));
        }
        else if ((i % 5 == 0) || (i % 5 == 1) || (i % 5 == 2))
        {
          l2 = i;
          if (i % 2 != 0) {
            b = 1;
          } else {
            b = 2;
          }
          localArrayList.add(new UB(String.valueOf(l1), (byte)5, l2, b, ॱ(ˋ.nextInt(20)), (byte)1, System.currentTimeMillis(), 0L, false, paramByte, (byte)1));
        }
        i += 1;
        l2 = l1;
      }
      ahi.AuX.ˋ(new ahs(localArrayList, paramˊ), "Insert dummy conversation items for performance testing");
    }
  }
  
  public static class AUx
  {
    public AUx() {}
    
    public static void ˊ(Context paramContext, String paramString)
    {
      paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(paramString)));
    }
    
    public static void ˋ(Context paramContext, String paramString)
    {
      paramString = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
      paramString.setPackage(TFApplication.ˏॱ().getPackageName());
      ahi.ͺ.ˋ(paramContext, paramString, -1);
    }
    
    public static boolean ˋ(String paramString)
    {
      return (!TextUtils.isEmpty(paramString)) && (paramString.contains("pinger.zendesk.com"));
    }
    
    public static String ˎ(Context paramContext)
    {
      return paramContext.getString(2131231971);
    }
    
    public static String ˎ(String paramString)
    {
      if (!TextUtils.isEmpty(paramString))
      {
        String str = paramString;
        if (paramString.startsWith("mailto:")) {
          str = paramString.replace("mailto:", "");
        }
        return str;
      }
      return null;
    }
    
    public static String ˏ(String paramString)
    {
      if (!TextUtils.isEmpty(paramString))
      {
        Matcher localMatcher = Patterns.WEB_URL.matcher(paramString);
        if (localMatcher.find()) {
          return paramString.substring(localMatcher.start(), localMatcher.end());
        }
      }
      return "";
    }
    
    public static String ॱ(Context paramContext, Uri paramUri)
    {
      for (;;)
      {
        try
        {
          String str = paramUri.getScheme();
          if ("content".equals(str))
          {
            paramContext = paramContext.getContentResolver().query(paramUri, new String[] { "_data" }, null, null, null);
            if (paramContext == null) {}
          }
          try
          {
            if (paramContext.moveToFirst())
            {
              str = paramContext.getString(0);
              return str;
            }
            agY.ˊ(paramContext);
          }
          finally
          {
            agY.ˊ(paramContext);
          }
          paramContext = paramUri.getPath();
          return paramContext;
        }
        catch (Throwable paramContext)
        {
          PY.ˊ().ˎ(Level.SEVERE, paramContext);
        }
        return paramUri.toString();
      }
    }
    
    public static void ॱ(Context paramContext, String paramString)
    {
      Intent localIntent = new Intent(paramContext, WebViewActivity.class);
      localIntent.putExtra("web_url", paramString);
      ahi.ͺ.ˋ(paramContext, localIntent, -1);
    }
    
    public static boolean ॱ(String paramString)
    {
      return (!TextUtils.isEmpty(paramString)) && (paramString.toLowerCase().startsWith("http"));
    }
  }
  
  public static class AuX
  {
    public static ArrayList<Character> ˊ;
    private static final byte[] ˋ = { 27, -59, -103, 14, 11, -12, 12, -5, -8, -7 };
    private static int ˎ = 0;
    private static int ˏ = 1;
    private static int ॱ = 187;
    
    public AuX() {}
    
    public static void ʻ(String paramString)
    {
      ˏ(new aht(paramString), true, "Insert outgoing call with duration 0");
    }
    
    public static boolean ʻ()
    {
      return false;
    }
    
    public static String ʼ(String paramString)
    {
      return ˏ(paramString, (int)(ahi.Con.ˊ().widthPixels * aha.ˊ), (int)(ahi.Con.ˊ().heightPixels * aha.ˋ));
    }
    
    public static boolean ʼ()
    {
      try
      {
        Object localObject = Class.forName("o.UI").newInstance();
        Field localField = localObject.getClass().getDeclaredField("field1");
        localField.setAccessible(true);
        localObject = (String)localField.get(localObject);
        return false;
      }
      catch (Exception localException) {}
      return true;
    }
    
    public static Pair<String, String> ʽ(String paramString)
    {
      int i = paramString.indexOf(":");
      if (i != -1) {
        return new Pair(paramString.substring(0, i), paramString.substring(i + 1, paramString.length()).trim());
      }
      return null;
    }
    
    public static File ˊ(String paramString)
    {
      paramString = new File(paramString);
      FileInputStream localFileInputStream = new FileInputStream(paramString);
      File localFile = new File(Preferences.AUx.ˎ());
      if (localFile.exists()) {
        localFile.delete();
      }
      localFile.createNewFile();
      FileOutputStream localFileOutputStream = new FileOutputStream(localFile);
      ZipOutputStream localZipOutputStream = new ZipOutputStream(localFileOutputStream);
      localZipOutputStream.putNextEntry(new ZipEntry("pinger.log"));
      ahr.ॱ(localFileInputStream, localZipOutputStream);
      localZipOutputStream.closeEntry();
      localFileInputStream.close();
      localZipOutputStream.close();
      localFileOutputStream.close();
      paramString.delete();
      return localFile;
    }
    
    public static Integer ˊ(SharedPreferences paramSharedPreferences, String paramString)
    {
      if (paramSharedPreferences.contains(paramString)) {
        return Integer.valueOf(paramSharedPreferences.getInt(paramString, 0));
      }
      return null;
    }
    
    public static String ˊ()
    {
      String str1 = Build.MANUFACTURER;
      String str2 = Build.MODEL;
      if (str2.startsWith(str1)) {
        return ahi.ˈ.ˎ(str2);
      }
      return ahi.ˈ.ˎ(str1) + " " + str2;
    }
    
    @SuppressLint({"NewApi"})
    public static <Params> void ˊ(AsyncTask<Params, ?, ?> paramAsyncTask, Params... paramVarArgs)
    {
      if (Build.VERSION.SDK_INT >= 11)
      {
        paramAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, paramVarArgs);
        return;
      }
      paramAsyncTask.execute(paramVarArgs);
    }
    
    public static void ˊ(FragmentActivity paramFragmentActivity, Message paramMessage)
    {
      paramMessage = (aee.ˊ)paramMessage.obj;
      Pair[] arrayOfPair = new Pair[4];
      arrayOfPair[0] = Pair.create(paramMessage.ˊ(), Integer.valueOf(2131231528));
      arrayOfPair[1] = Pair.create(paramMessage.ˎ(), Integer.valueOf(2131231530));
      arrayOfPair[2] = Pair.create(paramMessage.ʻ(), Integer.valueOf(2131231529));
      arrayOfPair[3] = Pair.create(paramMessage.ᐝ(), Integer.valueOf(2131231531));
      StringBuilder localStringBuilder = new StringBuilder();
      ArrayList localArrayList = new ArrayList();
      int i = arrayOfPair.length;
      i = 0;
      while (i < 4)
      {
        paramMessage = arrayOfPair[i];
        Object localObject = (List)paramMessage.first;
        int j = ((Integer)paramMessage.second).intValue();
        if (!((List)localObject).isEmpty())
        {
          localObject = ahi.ᐝ.ˊ((List)localObject);
          localArrayList.addAll(Arrays.asList((Object[])localObject));
          if (TextUtils.isEmpty(localStringBuilder)) {
            paramMessage = "";
          } else {
            paramMessage = "\n\n";
          }
          localStringBuilder.append(paramMessage).append(paramFragmentActivity.getString(j, new Object[] { TextUtils.join(",", (Object[])localObject) }));
        }
        i += 1;
      }
      if (!TextUtils.isEmpty(localStringBuilder))
      {
        paramMessage = ahf.ॱ(localStringBuilder.toString(), null);
        paramMessage.getArguments().putCharSequenceArrayList("rejected_members_key", localArrayList);
        ahf.ˊ(paramFragmentActivity.getSupportFragmentManager(), paramMessage, "rejected_members_dialog");
      }
    }
    
    public static void ˊ(FragmentActivity paramFragmentActivity, String paramString)
    {
      ahf.ˏ(paramFragmentActivity.getSupportFragmentManager(), "invite_dialog");
    }
    
    public static void ˊ(FragmentActivity paramFragmentActivity, List<String> paramList, adT.ˋ paramˋ, String paramString1, String paramString2)
    {
      ahf.ˊ(paramFragmentActivity.getSupportFragmentManager(), ahf.ˊ(paramFragmentActivity), "invite_dialog");
      new adT(paramList, paramˋ, paramString1, paramString2).ॱˊ();
    }
    
    public static void ˊ(String paramString1, String paramString2)
    {
      long l = Preferences.if.ˍ();
      if (System.currentTimeMillis() - l >= 86400000L)
      {
        ServerLogger.sendLogsToServer(paramString2);
        return;
      }
      PY.ˊ().info(paramString1 + " " + paramString2 + ", but not starting log upload. Next upload will be allowed after " + ahb.ˊ(86400000L + l) + " (UTC)");
    }
    
    public static boolean ˊ(Runnable paramRunnable)
    {
      if ((paramRunnable != null) && (Build.VERSION.SDK_INT < 21))
      {
        paramRunnable.run();
        return true;
      }
      return false;
    }
    
    public static String ˊॱ()
    {
      boolean bool;
      if ((κ.ˏ) && (TFService.getTFInstance().getProfile() != null)) {
        bool = true;
      } else {
        bool = false;
      }
      ν.ˎ(bool, "Profile is null");
      if (!TextUtils.isEmpty(Preferences.ͺ.ˊ.ˊॱ())) {
        return Preferences.ͺ.ˊ.ˊॱ();
      }
      if (TFService.getTFInstance().getProfile() != null)
      {
        if (!TextUtils.isEmpty(TFService.getTFInstance().getProfile().ˊ())) {
          return TFService.getTFInstance().getProfile().ˊ();
        }
        return TFService.getTFInstance().getProfile().ॱ();
      }
      return "";
    }
    
    public static String ˊॱ(String paramString)
    {
      PY.iF localIF = new PY.iF("CCN");
      localIF.ˋ("start");
      String str2 = null;
      String str1 = paramString;
      if (!paramString.startsWith("+")) {
        str1 = "+" + paramString;
      }
      paramString = PhoneNumberUtil.getInstance();
      localIF.ˋ("habemus instance");
      try
      {
        paramString = paramString.parse(str1, "US");
        localIF.ˋ("parsed");
        paramString = PhoneNumberUtil.getInstance().getRegionCodeForNumber(paramString);
      }
      catch (NumberParseException paramString)
      {
        PY.ˊ().log(Level.WARNING, "Number " + str1 + " could not be parsed using libphonenumber library. Message: " + paramString.getMessage());
        paramString = str2;
      }
      localIF.ˋ("ta-daa");
      str2 = paramString;
      if (paramString == null) {
        str2 = "US";
      }
      PY.ˊ().info("Helper.getCountryCodeFromPhoneNumber returns " + str2 + " for phone number " + str1);
      return str2;
    }
    
    public static byte ˋ(String paramString)
    {
      if ("userId".equals(paramString)) {
        return 3;
      }
      if ("phone".equals(paramString)) {
        return 1;
      }
      if ("email".equals(paramString)) {
        return 2;
      }
      ʢ.ॱ(κ.ˏ, "Invalid address type: " + paramString);
      return 0;
    }
    
    public static String ˋ()
    {
      return "Unknown Number";
    }
    
    public static String ˋ(Context paramContext)
    {
      paramContext = ((TelephonyManager)paramContext.getSystemService("phone")).getSimCountryIso();
      if (TextUtils.isEmpty(paramContext)) {
        return "";
      }
      return paramContext.toUpperCase();
    }
    
    public static String ˋ(Bundle paramBundle)
    {
      StringBuilder localStringBuilder = new StringBuilder("[");
      int i = 1;
      Iterator localIterator = paramBundle.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        if (i == 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append(str);
        localStringBuilder.append(" = ");
        localStringBuilder.append(paramBundle.get(str));
        i = 0;
      }
      localStringBuilder.append("]");
      return localStringBuilder.toString();
    }
    
    public static String ˋ(String paramString, int paramInt)
    {
      if (TextUtils.isEmpty(paramString)) {
        return "";
      }
      int i = paramString.length();
      if (i < paramInt) {
        return paramString;
      }
      return paramString.substring(i - paramInt, i);
    }
    
    public static String ˋ(String paramString1, String paramString2)
    {
      if (TextUtils.isEmpty(paramString2)) {
        return paramString1;
      }
      paramString2 = ahi.ʼ.ˊ(paramString2);
      if (paramString1.contains(":")) {
        paramString1 = ahi.ʽ.ॱ(paramString1.substring(0, paramString1.indexOf(":"))) + ": ";
      } else {
        paramString1 = "";
      }
      Resources localResources = TFApplication.ˏॱ().getResources();
      switch (ahh.ॱ[paramString2.ordinal()])
      {
      default: 
        break;
      case 1: 
        return paramString1 + localResources.getString(2131231584);
      case 2: 
        return paramString1 + localResources.getString(2131231585);
      case 3: 
        return paramString1 + localResources.getString(2131231583);
      }
      return paramString1 + localResources.getString(2131231584);
    }
    
    public static void ˋ(long paramLong, String paramString, boolean paramBoolean)
    {
      ˊ(new agq(null, paramString, paramLong, paramBoolean), new Void[0]);
    }
    
    public static void ˋ(Runnable paramRunnable, String paramString)
    {
      ˏ(paramRunnable, false, paramString);
    }
    
    public static void ˋ(String paramString1, String paramString2, boolean paramBoolean)
    {
      ˊ(new agq(paramString1, paramString2, -1L, paramBoolean), new Void[0]);
    }
    
    public static String ˋॱ(String paramString)
    {
      Object localObject = MessageDigest.getInstance("MD5");
      ((MessageDigest)localObject).update(paramString.getBytes());
      paramString = ((MessageDigest)localObject).digest();
      localObject = new StringBuilder();
      int j = paramString.length;
      int i = 0;
      while (i < j)
      {
        ((StringBuilder)localObject).append(Integer.toString((paramString[i] & 0xFF) + 256, 16).substring(1));
        i += 1;
      }
      return ((StringBuilder)localObject).toString();
    }
    
    public static Long ˎ(SharedPreferences paramSharedPreferences, String paramString)
    {
      if (paramSharedPreferences.contains(paramString)) {
        return Long.valueOf(paramSharedPreferences.getLong(paramString, 0L));
      }
      return null;
    }
    
    public static String ˎ()
    {
      if ((TFService.getTFInstance().getProfile().ˋˋ() != null) && (!TextUtils.isEmpty(TFService.getTFInstance().getProfile().ˋˋ().ˊ()))) {
        return ahi.ˏ.ˋ(TFService.getTFInstance().getProfile().ˋˋ().ˊ());
      }
      return null;
    }
    
    public static String ˎ(TextView paramTextView)
    {
      return paramTextView.getText().toString();
    }
    
    public static String ˎ(Integer paramInteger, String paramString, boolean paramBoolean)
    {
      Integer localInteger = paramInteger;
      if (paramInteger == null) {
        localInteger = Integer.valueOf(3);
      }
      paramInteger = PingerApplication.ˋ();
      switch (localInteger.intValue())
      {
      default: 
        break;
      case 1: 
        paramInteger = paramInteger.getString(2131231175);
        break;
      case 4: 
        paramInteger = paramInteger.getString(2131231176);
        break;
      case 2: 
        paramInteger = paramInteger.getString(2131231178);
        break;
      case 3: 
        paramInteger = paramInteger.getString(2131231177);
        break;
      }
      if (!TextUtils.isEmpty(paramString)) {
        paramInteger = paramString;
      } else {
        paramInteger = paramInteger.getString(2131231174);
      }
      paramString = paramInteger;
      if (paramBoolean) {
        paramString = paramInteger.toLowerCase();
      }
      return paramString;
    }
    
    public static String ˎ(String paramString)
    {
      if (ॱˊ(paramString))
      {
        paramString = paramString.split(",");
        return paramString[(paramString.length - 1)];
      }
      return null;
    }
    
    private static String ˏ(byte paramByte1, int paramInt, byte paramByte2)
    {
      break label300;
      int n = paramByte2;
      int j;
      int m = j;
      paramByte2 = b1;
      paramInt = n + m + 2;
      paramByte1 = paramByte2;
      paramByte2 = paramInt;
      paramInt = j;
      break label58;
      label31:
      byte b1 = 63;
      break label208;
      int i1;
      for (;;)
      {
        b1 = 44;
        j = paramByte1;
        paramByte1 = paramByte2;
        break label208;
        paramByte2 = b1 + i1 + 2;
        label58:
        j = ˏ + 101;
        ˎ = j % 128;
        if (j % 2 != 0)
        {
          j = paramByte1;
          paramByte1 = paramByte2;
          break;
        }
      }
      for (;;)
      {
        paramByte1 = ˎ + 51;
        ˏ = paramByte1 % 128;
        byte[] arrayOfByte2;
        if (paramByte1 % 2 == 0)
        {
          paramByte1 = paramInt;
          paramInt = paramByte2;
        }
        else
        {
          break label290;
          int i = (byte)paramByte1;
          paramByte2 = j + 1;
          arrayOfByte2[j] = i;
          j = paramInt + 1;
          if (paramByte2 != b2)
          {
            paramInt = paramByte2;
            paramByte2 = j;
            break label359;
          }
          do
          {
            return new String(arrayOfByte2, 0);
            i = (byte)paramByte2;
            paramInt = k + 1;
            arrayOfByte2[k] = i;
            j = m + 1;
          } while (paramInt == b2);
          paramByte1 = paramByte2;
          paramByte2 = j;
          break label359;
          label208:
          k = j;
          paramByte2 = paramByte1;
          m = paramInt;
          switch (b1)
          {
          }
          break label31;
        }
        for (;;)
        {
          n = b1;
          m = i1;
          paramByte2 = paramByte1;
          j = paramInt;
          switch (k)
          {
          }
          k = 0;
          continue;
          label290:
          k = 1;
          paramByte1 = paramInt;
          paramInt = paramByte2;
        }
        label300:
        int k = 0;
        b1 = 0;
        j = 3 - paramByte2 * 2;
        byte b2 = paramByte1 * 4 + 7;
        try
        {
          byte[] arrayOfByte1 = ˋ;
          paramByte2 = 97 - paramInt * 4;
          arrayOfByte2 = new byte[b2];
          if (arrayOfByte1 == null) {
            break;
          }
          m = j;
        }
        catch (Exception localException)
        {
          throw localException;
        }
        label359:
        b1 = paramByte1;
        i1 = localException[paramByte2];
      }
    }
    
    public static String ˏ(int paramInt)
    {
      String str = "";
      int i = 0;
      while (i < paramInt)
      {
        int j = (int)(Math.random() * 10.0D);
        str = str + j;
        i += 1;
      }
      return str;
    }
    
    public static String ˏ(Context paramContext, boolean paramBoolean)
    {
      Object localObject1 = ˋ(paramContext);
      Object localObject2 = "US";
      if (!TextUtils.isEmpty((CharSequence)localObject1))
      {
        localObject1 = ((String)localObject1).toUpperCase();
      }
      else
      {
        Locale localLocale = Locale.getDefault();
        localObject1 = localObject2;
        if (localLocale != null)
        {
          localObject1 = localObject2;
          if (!TextUtils.isEmpty(localLocale.getCountry())) {
            localObject1 = localLocale.getCountry().toUpperCase();
          }
        }
      }
      if (paramBoolean)
      {
        paramBoolean = false;
        try
        {
          paramContext = Class.forName(paramContext.getPackageName() + ".util.CountryManager");
          localObject2 = paramContext.getMethod("getInstance", new Class[0]);
          boolean bool = ((Boolean)paramContext.getMethod("isSupportedCountry", new Class[] { String.class }).invoke(((Method)localObject2).invoke(null, new Object[0]), new Object[] { localObject1 })).booleanValue();
          paramBoolean = bool;
        }
        catch (Exception paramContext)
        {
          paramContext.printStackTrace();
        }
        if (paramBoolean) {
          return localObject1;
        }
        return "US";
      }
      return localObject1;
    }
    
    public static String ˏ(String paramString)
    {
      if (ahi.ʿ.ʻ(paramString)) {
        return "unknown_number";
      }
      return paramString;
    }
    
    public static String ˏ(String paramString, int paramInt1, int paramInt2)
    {
      return ʄ.ˎ(paramString, paramInt1, paramInt2);
    }
    
    public static List<PackageInfo> ˏ()
    {
      ArrayList localArrayList = new ArrayList(3);
      Iterator localIterator = PingerApplication.ˋ().getPackageManager().getInstalledPackages(128).iterator();
      while (localIterator.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
        String str = localPackageInfo.packageName;
        if ((str.startsWith("com.pinger")) && (!str.equals(TFApplication.ˏॱ().getPackageName()))) {
          localArrayList.add(localPackageInfo);
        }
      }
      return localArrayList;
    }
    
    public static void ˏ(SharedPreferences.Editor paramEditor, String paramString, Integer paramInteger)
    {
      if (paramInteger != null) {
        paramEditor.putInt(paramString, paramInteger.intValue());
      }
    }
    
    public static void ˏ(Runnable paramRunnable, boolean paramBoolean, String paramString)
    {
      String str;
      if (!TextUtils.isEmpty(paramString)) {
        str = paramString;
      } else {
        str = "";
      }
      paramRunnable = new Thread(paramRunnable, str);
      int i;
      if (paramBoolean) {
        i = 1;
      } else {
        i = 5;
      }
      paramRunnable.setPriority(i);
      if (paramBoolean)
      {
        long l = (new Random().nextInt(10) + 1) * 10;
        PY.ˊ().info("executeOnThread: Low priority thread [ID:" + paramRunnable.getId() + ", " + paramString + "] after: " + l + ", ms");
        TFApplication.ˏॱ().ˊ().postDelayed(new ahq(paramRunnable), l);
        return;
      }
      PY.ˊ().info("executeOnThread: Priority thread [ID:" + paramRunnable.getId() + ", " + paramString + "]");
      paramRunnable.start();
    }
    
    @TargetApi(21)
    public static boolean ˏ(Runnable paramRunnable)
    {
      if ((paramRunnable != null) && (Build.VERSION.SDK_INT >= 21))
      {
        paramRunnable.run();
        return true;
      }
      return false;
    }
    
    public static byte ॱ(String paramString)
    {
      if ("phone".equals(paramString)) {
        return 1;
      }
      if ("username".equals(paramString)) {
        return 5;
      }
      ʢ.ॱ(κ.ˏ, "Invalid address type: " + paramString);
      return 0;
    }
    
    public static String ॱ()
    {
      String str2 = TFService.getTFInstance().getProfile().ˊ();
      String str1 = str2;
      if (str2 == null)
      {
        str1 = str2;
        if (TFService.getTFInstance().getProfile().ˋˋ() != null)
        {
          str2 = TFService.getTFInstance().getProfile().ˋˋ().ॱ();
          str1 = str2;
          if (str2 == null) {
            str1 = TFService.getTFInstance().getProfile().ˋˋ().ˏ();
          }
        }
      }
      return str1;
    }
    
    public static Qr ॱ(Qg paramQg, Qp... paramVarArgs)
    {
      boolean bool;
      if ((κ.ˏ) && (paramQg != null)) {
        bool = true;
      } else {
        bool = false;
      }
      ν.ˎ(bool, "the listener should be valid!");
      if ((κ.ˏ) && (paramVarArgs != null)) {
        bool = true;
      } else {
        bool = false;
      }
      ν.ˎ(bool, "the requests array should be valid!");
      Qr localQr = new Qr();
      if (paramVarArgs != null)
      {
        if (paramQg != null)
        {
          int j = paramVarArgs.length;
          int i = 0;
          while (i < j)
          {
            paramVarArgs[i].ॱ(paramQg);
            i += 1;
          }
        }
        localQr.ˊ(paramVarArgs);
      }
      return localQr;
    }
    
    public static void ॱ(Context paramContext)
    {
      break label30;
      int i = ˏ + 53;
      ˎ = i % 128;
      if (i % 2 == 0)
      {
        break label390;
        label30:
        if (Build.VERSION.SDK_INT < 21) {
          break label379;
        }
        break label279;
        i = 1;
        break label412;
      }
      label49:
      int j;
      Object localObject1;
      label144:
      Object localObject2;
      int k;
      int m;
      switch (i)
      {
      default: 
        break;
      case 37: 
        for (;;)
        {
          switch (i)
          {
          default: 
            break label493;
            if (paramContext != null) {
              break label285;
            }
            break label343;
            i = 1;
            break label315;
            paramContext.setColorFilter(j, PorterDuff.Mode.SRC_IN);
            ((Drawable)localObject1).setColorFilter(j, PorterDuff.Mode.SRC_IN);
            return;
            i = 14;
          }
        }
        try
        {
          localObject1 = paramContext.getResources();
          j = ((Resources)localObject1).getColor(2131689644);
          localObject1 = paramContext.getResources();
          i = ˎ + 97;
        }
        catch (Exception paramContext)
        {
          try
          {
            localObject2 = ˏ((byte)0, 0, (byte)0);
            localObject2 = ((String)localObject2).intern();
            k = ((Resources)localObject1).getIdentifier("overscroll_glow", "drawable", (String)localObject2);
            localObject1 = paramContext.getResources();
            localObject2 = ˏ((byte)0, 0, (byte)0);
            localObject2 = ((String)localObject2).intern();
            m = ((Resources)localObject1).getIdentifier("overscroll_edge", "drawable", (String)localObject2);
            if (k > 0) {
              break label487;
            }
          }
          catch (Exception paramContext)
          {
            throw paramContext;
          }
          paramContext = paramContext;
          throw paramContext;
        }
        ˏ = i % 128;
        if (i % 2 != 0)
        {
          break label445;
          label279:
          i = 76;
          break label588;
          for (;;)
          {
            label285:
            i = 0;
            switch (i)
            {
            case 0: 
            default: 
              label290:
              break label385;
              switch (i)
              {
              case 0: 
              default: 
                label315:
                break label390;
                label343:
                i = 1;
                break label353;
                label348:
                i = 0;
                break label412;
                label353:
                switch (i)
                {
                }
                break;
              }
              break;
            }
          }
        }
        break;
      }
      for (;;)
      {
        label379:
        i = 82;
        break label588;
        label385:
        label390:
        label406:
        label412:
        label439:
        label445:
        do
        {
          i = 0;
          break label290;
          i = 0;
          break label315;
          if (m > 0) {
            break label144;
          }
          break label493;
          i = 37;
          break;
          switch (i)
          {
          case 1: 
          default: 
            break label348;
            i = 35;
            break label49;
            localObject1 = paramContext.getResources().getDrawable(m);
            paramContext = paramContext.getResources().getDrawable(k);
          }
        } while (localObject1 != null);
        break label583;
        if (paramContext != null) {
          break;
        }
        break label348;
        return;
        for (;;)
        {
          label487:
          i = 84;
          break label553;
          label493:
          i = 88;
          break;
          localObject2 = PorterDuff.Mode.SRC_IN;
          paramContext.setColorFilter(j, (PorterDuff.Mode)localObject2);
          paramContext = PorterDuff.Mode.SRC_IN;
          ((Drawable)localObject1).setColorFilter(j, paramContext);
          return;
          i = ˎ + 59;
          ˏ = i % 128;
          if (i % 2 == 0) {
            break label439;
          }
          break label406;
          i = 96;
          label553:
          switch (i)
          {
          }
        }
        label583:
        i = 1;
        break label290;
        label588:
        switch (i)
        {
        }
      }
    }
    
    public static void ॱ(SharedPreferences.Editor paramEditor, String paramString, Long paramLong)
    {
      if (paramLong != null) {
        paramEditor.putLong(paramString, paramLong.longValue());
      }
    }
    
    public static void ॱ(boolean paramBoolean)
    {
      if (paramBoolean)
      {
        Preferences.if.ˊ(System.currentTimeMillis());
        return;
      }
      Preferences.if.ˊ(-1L);
    }
    
    public static boolean ॱˊ(String paramString)
    {
      return (paramString.startsWith("data:image/")) && (paramString.split(",").length >= 2);
    }
    
    public static boolean ॱˋ(String paramString)
    {
      return paramString.toLowerCase().equals("unknown_number");
    }
    
    public static byte ᐝ(String paramString)
    {
      if ("normal".equals(paramString)) {
        return 1;
      }
      if ("error".equals(paramString)) {
        return 3;
      }
      if ("info".equals(paramString)) {
        return 4;
      }
      if ("html".equals(paramString)) {
        return 2;
      }
      if ("flagshipGroup".equals(paramString)) {
        return 5;
      }
      if ("flagshipGroupSystem".equals(paramString)) {
        return 6;
      }
      ʢ.ॱ(κ.ˏ, "Invalid message type: " + paramString);
      return 0;
    }
    
    public static boolean ᐝ()
    {
      int i = (int)(Preferences.ˏ.ˎ() * 100.0F);
      return new Random().nextInt(100) + 1 <= i;
    }
  }
  
  public static class Aux
  {
    public static final Object ˎ = new ahk();
    
    public Aux() {}
    
    public static Object ˊ(Object paramObject)
    {
      if (paramObject == null) {
        return ˎ;
      }
      if (((paramObject instanceof JSONArray)) || ((paramObject instanceof JSONObject))) {
        return paramObject;
      }
      if (paramObject.equals(ˎ)) {
        return paramObject;
      }
      if ((paramObject instanceof Collection)) {}
      try
      {
        paramObject = new JSONArray((Collection)paramObject);
        return paramObject;
      }
      catch (Exception paramObject) {}
      if (paramObject.getClass().isArray())
      {
        paramObject = new ahi.ʻ(paramObject);
        return paramObject;
      }
      if ((paramObject instanceof Map))
      {
        paramObject = new JSONObject((Map)paramObject);
        return paramObject;
      }
      if (((paramObject instanceof Boolean)) || ((paramObject instanceof Byte)) || ((paramObject instanceof Character)) || ((paramObject instanceof Double)) || ((paramObject instanceof Float)) || ((paramObject instanceof Integer)) || ((paramObject instanceof Long)) || ((paramObject instanceof Short)) || ((paramObject instanceof String))) {
        return paramObject;
      }
      if (paramObject.getClass().getPackage().getName().startsWith("java."))
      {
        paramObject = paramObject.toString();
        return paramObject;
      }
      return null;
    }
    
    public static Bundle ˋ(JSONObject paramJSONObject)
    {
      Bundle localBundle = new Bundle();
      Iterator localIterator = paramJSONObject.keys();
      while (localIterator.hasNext())
      {
        String str1 = (String)localIterator.next();
        JSONArray localJSONArray = paramJSONObject.optJSONArray(str1);
        Object localObject = Double.valueOf(paramJSONObject.optDouble(str1));
        String str2 = paramJSONObject.optString(str1);
        if ((localJSONArray != null) && (localJSONArray.length() <= 0))
        {
          localBundle.putStringArray(str1, new String[0]);
        }
        else
        {
          int i;
          if ((localJSONArray != null) && (!Double.isNaN(localJSONArray.optDouble(0))))
          {
            localObject = new double[localJSONArray.length()];
            i = 0;
            while (i < localJSONArray.length())
            {
              localObject[i] = localJSONArray.optDouble(i);
              i += 1;
            }
            localBundle.putDoubleArray(str1, (double[])localObject);
          }
          else if ((localJSONArray != null) && (localJSONArray.optString(0) != null))
          {
            localObject = new String[localJSONArray.length()];
            i = 0;
            while (i < localJSONArray.length())
            {
              localObject[i] = localJSONArray.optString(i);
              i += 1;
            }
            localBundle.putStringArray(str1, (String[])localObject);
          }
          else if (!((Double)localObject).isNaN())
          {
            localBundle.putDouble(str1, ((Double)localObject).doubleValue());
          }
          else if (str2 != null)
          {
            localBundle.putString(str1, str2);
          }
        }
      }
      return localBundle;
    }
    
    public static JSONObject ˏ(Bundle paramBundle)
    {
      JSONObject localJSONObject = new JSONObject();
      Iterator localIterator = paramBundle.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        Object localObject = paramBundle.get(str);
        if ((localObject instanceof Bundle)) {}
        try
        {
          localJSONObject.put(str, ˏ((Bundle)localObject));
          break label102;
          if (Build.VERSION.SDK_INT >= 19) {
            localJSONObject.put(str, JSONObject.wrap(localObject));
          } else {
            localJSONObject.put(str, ˊ(localObject));
          }
        }
        catch (JSONException localJSONException)
        {
          label102:
          localJSONException.printStackTrace();
        }
      }
      return localJSONObject;
    }
  }
  
  public static class Con
  {
    private static int ʽ;
    private static int ˊ;
    private static final byte[] ˊॱ;
    private static Float ˋ;
    private static Integer ˎ;
    private static int ˏ;
    private static Integer ॱ;
    
    /* Error */
    static
    {
      // Byte code:
      //   0: goto +6 -> 6
      //   3: astore_0
      //   4: aload_0
      //   5: athrow
      //   6: bipush 10
      //   8: newarray byte
      //   10: dup
      //   11: iconst_0
      //   12: ldc 25
      //   14: bastore
      //   15: dup
      //   16: iconst_1
      //   17: ldc 26
      //   19: bastore
      //   20: dup
      //   21: iconst_2
      //   22: ldc 27
      //   24: bastore
      //   25: dup
      //   26: iconst_3
      //   27: ldc 28
      //   29: bastore
      //   30: dup
      //   31: iconst_4
      //   32: ldc 29
      //   34: bastore
      //   35: dup
      //   36: iconst_5
      //   37: ldc 30
      //   39: bastore
      //   40: dup
      //   41: bipush 6
      //   43: ldc 31
      //   45: bastore
      //   46: dup
      //   47: bipush 7
      //   49: ldc 32
      //   51: bastore
      //   52: dup
      //   53: bipush 8
      //   55: ldc 33
      //   57: bastore
      //   58: dup
      //   59: bipush 9
      //   61: ldc 34
      //   63: bastore
      //   64: putstatic 36	o/ahi$Con:ˊॱ	[B
      //   67: sipush 231
      //   70: putstatic 38	o/ahi$Con:ʽ	I
      //   73: iconst_0
      //   74: putstatic 40	o/ahi$Con:ˏ	I
      //   77: iconst_1
      //   78: putstatic 42	o/ahi$Con:ˊ	I
      //   81: return
      //   82: astore_0
      //   83: aload_0
      //   84: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   3	2	0	localException1	Exception
      //   82	2	0	localException2	Exception
      // Exception table:
      //   from	to	target	type
      //   67	73	3	java/lang/Exception
      //   73	77	3	java/lang/Exception
      //   77	81	3	java/lang/Exception
      //   6	67	82	java/lang/Exception
    }
    
    public Con() {}
    
    public static int ˊ(int paramInt)
    {
      return (int)(paramInt * ˋ());
    }
    
    public static int ˊ(Context paramContext)
    {
      break label5;
      int i;
      return i;
      label5:
      int k = 0;
      int m = paramContext.getResources().getIdentifier("status_bar_height", "dimen", ॱ(0, (short)0, 0).intern());
      if (m <= 0) {
        break label194;
      }
      int j;
      for (;;)
      {
        j = 0;
        label42:
        i = k;
        switch (j)
        {
        }
      }
      for (;;)
      {
        label71:
        i = 68;
        break label82;
        label77:
        j = 0;
        break;
        label82:
        switch (i)
        {
        }
      }
      for (;;)
      {
        i = ˊ + 59;
        ˏ = i % 128;
        if (i % 2 != 0) {
          break label77;
        }
        for (;;)
        {
          i = k;
          switch (j)
          {
          case 1: 
          default: 
            break label77;
            i = ˏ + 29;
            ˊ = i % 128;
            if (i % 2 == 0) {
              break label71;
            }
            break label199;
            label194:
            j = 1;
            break label42;
            label199:
            i = 42;
            break label82;
            return Math.round(paramContext.getResources().getDimensionPixelSize(m));
            j = 1;
          }
        }
        k = Math.round(paramContext.getResources().getDimensionPixelSize(m));
      }
      return k;
    }
    
    public static DisplayMetrics ˊ()
    {
      Display localDisplay = ((WindowManager)PingerApplication.ˋ().getSystemService("window")).getDefaultDisplay();
      DisplayMetrics localDisplayMetrics = new DisplayMetrics();
      localDisplay.getMetrics(localDisplayMetrics);
      return localDisplayMetrics;
    }
    
    public static float ˋ()
    {
      if (ˋ == null) {
        ॱ();
      }
      return ˋ.floatValue();
    }
    
    public static String ˋ(Context paramContext)
    {
      paramContext = (ClipboardManager)paramContext.getSystemService("clipboard");
      if (paramContext.getPrimaryClip() != null)
      {
        paramContext = paramContext.getPrimaryClip().getItemAt(0);
        if (paramContext != null)
        {
          if (!TextUtils.isEmpty(paramContext.getText())) {
            return paramContext.getText().toString();
          }
          return "";
        }
      }
      return "";
    }
    
    public static void ˋ(Activity paramActivity, Menu paramMenu, String paramString)
    {
      if (paramString.startsWith("mailto:")) {
        paramMenu.add(0, 2131624965, 0, 2131231846);
      } else if ((paramString.startsWith("http://")) || (paramString.startsWith("https://"))) {
        paramMenu.add(0, 2131624966, 0, 2131231845);
      } else if (paramString.startsWith("geo:")) {
        paramMenu.add(0, 2131624967, 0, 2131231844);
      } else {
        paramActivity.getMenuInflater().inflate(2131755025, paramMenu);
      }
      paramActivity.getMenuInflater().inflate(2131755013, paramMenu);
    }
    
    public static void ˋ(Context paramContext, EditText paramEditText)
    {
      ((InputMethodManager)paramContext.getSystemService("input_method")).showSoftInput(paramEditText, 1);
    }
    
    public static void ˋ(Context paramContext, String paramString)
    {
      ˋ(paramContext, "info", paramString);
    }
    
    public static void ˋ(Context paramContext, String paramString1, String paramString2)
    {
      ((ClipboardManager)paramContext.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText(paramString1, paramString2));
    }
    
    public static void ˋ(View paramView)
    {
      paramView.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), 0, 0.0F, 0.0F, 0));
      paramView.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), 1, 0.0F, 0.0F, 0));
    }
    
    public static boolean ˋ(View paramView, RecyclerView paramRecyclerView)
    {
      if ((paramView instanceof SwipeLayout))
      {
        int i;
        if (((SwipeLayout)paramView).ˏॱ() == SwipeLayout.ᐝ.Open) {
          i = 1;
        } else {
          i = 0;
        }
        int j;
        if (paramRecyclerView.getScrollState() == 0) {
          j = 1;
        } else {
          j = 0;
        }
        return (j != 0) && (i == 0);
      }
      return false;
    }
    
    public static int ˎ()
    {
      if (ˎ == null) {
        ॱ();
      }
      return ˎ.intValue();
    }
    
    public static int ˎ(ConnectionQuality paramConnectionQuality)
    {
      switch (ahh.ˊ[paramConnectionQuality.ordinal()])
      {
      default: 
        break;
      case 1: 
      case 2: 
        return 2130837752;
      case 3: 
        return 2130837753;
      case 4: 
        return 2130837754;
      case 5: 
      case 6: 
      case 7: 
        return 2130837751;
      }
      return -1;
    }
    
    public static String ˎ(Context paramContext, boolean paramBoolean)
    {
      Object localObject1 = null;
      String str = ahi.AuX.ˎ();
      if (paramBoolean) {
        localObject1 = Preferences.if.ˋˊ();
      }
      Object localObject2;
      if (TextUtils.isEmpty((CharSequence)localObject1))
      {
        localObject2 = ahi.AuX.ॱ();
      }
      else
      {
        localObject2 = localObject1;
        if (!((String)localObject1).equals(ahi.AuX.ॱ()))
        {
          str = null;
          localObject2 = localObject1;
        }
      }
      localObject1 = new StringBuilder().append(paramContext.getString(2131231116)).append("\n").append(Preferences.ͺ.ˋ.ˋ()).append("\n\n").append(paramContext.getString(2131231112)).append("\n").append(paramContext.getString(2131231734)).append("\n\n").append(paramContext.getString(2131231113)).append("\n");
      if (TextUtils.isEmpty((CharSequence)localObject2)) {
        localObject2 = paramContext.getString(2131231114, new Object[] { paramContext.getString(2131231734) });
      }
      paramContext = ((StringBuilder)localObject1).append((String)localObject2).append("\n").append(paramContext.getString(2131231115)).append("\n");
      if (TextUtils.isEmpty(str)) {
        str = "-";
      }
      return str;
    }
    
    public static void ˎ(TextView paramTextView, CharSequence paramCharSequence, int paramInt1, int paramInt2, View.OnClickListener paramOnClickListener, boolean paramBoolean, int paramInt3)
    {
      paramCharSequence = SpannableString.valueOf(paramCharSequence);
      paramCharSequence.setSpan(new QU(paramOnClickListener, paramBoolean, paramInt3), paramInt1, paramInt2, 33);
      paramTextView.setText(paramCharSequence);
      if ((!(paramTextView.getMovementMethod() instanceof LinkMovementMethod)) && (paramTextView.getLinksClickable())) {
        paramTextView.setMovementMethod(LinkMovementMethod.getInstance());
      }
    }
    
    public static boolean ˎ(TFActivity paramTFActivity)
    {
      return (paramTFActivity != null) && (!paramTFActivity.isFinishing()) && (!paramTFActivity.isActivityStopped());
    }
    
    public static int ˏ()
    {
      if (ॱ == null) {
        ॱ();
      }
      return ॱ.intValue();
    }
    
    public static int ˏ(ConnectionQuality paramConnectionQuality)
    {
      switch (ahh.ˊ[paramConnectionQuality.ordinal()])
      {
      default: 
        break;
      case 1: 
      case 2: 
        return 2131231078;
      case 3: 
        return 2131231079;
      case 4: 
        return 2131231063;
      case 5: 
        return 2131231076;
      case 6: 
        return 2131231062;
      case 7: 
        return 2131231077;
      }
      return -1;
    }
    
    public static boolean ˏ(Fragment paramFragment)
    {
      return (paramFragment != null) && (paramFragment.getActivity() != null) && (!paramFragment.getActivity().isFinishing());
    }
    
    public static Rect ॱ(View paramView)
    {
      boolean bool;
      if ((κ.ˏ) && (paramView != null)) {
        bool = true;
      } else {
        bool = false;
      }
      ν.ˎ(bool, "View should not be null!");
      Rect localRect = new Rect();
      int[] arrayOfInt = new int[2];
      paramView.getLocationInWindow(arrayOfInt);
      localRect.set(arrayOfInt[0], arrayOfInt[1], arrayOfInt[0] + paramView.getWidth(), arrayOfInt[1] + paramView.getHeight());
      return localRect;
    }
    
    private static String ॱ(int paramInt1, short paramShort, int paramInt2)
    {
      break label25;
      byte[] arrayOfByte1;
      int i = arrayOfByte1[paramInt1];
      paramInt2 = paramShort;
      short s;
      paramShort = s;
      break label77;
      label16:
      break label77;
      int j;
      label25:
      byte[] arrayOfByte2;
      do
      {
        i = j;
        break;
        i = 7 - paramInt1 * 2;
        paramInt2 = 97 - paramInt2 * 3;
        arrayOfByte1 = ˊॱ;
        paramInt1 = 4 - paramShort * 4;
        paramShort = -1;
        arrayOfByte2 = new byte[i];
        j = i - 1;
      } while (arrayOfByte1 == null);
      i = paramShort;
      paramShort = paramInt2;
      for (;;)
      {
        label77:
        paramInt2 = paramInt2 + i + 2;
        paramInt1 += 1;
        i = paramShort;
        paramShort = paramInt2;
        do
        {
          return new String(arrayOfByte2, 0);
          s = i + 1;
          arrayOfByte2[s] = ((byte)paramShort);
        } while (s == j);
        break;
        s = ˏ + 77;
        ˊ = s % 128;
        if (s % 2 == 0) {
          break label16;
        }
      }
    }
    
    private static void ॱ()
    {
      Display localDisplay = ((WindowManager)PingerApplication.ˋ().getSystemService("window")).getDefaultDisplay();
      DisplayMetrics localDisplayMetrics = new DisplayMetrics();
      localDisplay.getMetrics(localDisplayMetrics);
      ˋ = Float.valueOf(localDisplayMetrics.density);
      if (localDisplay.getHeight() > localDisplay.getWidth())
      {
        ॱ = Integer.valueOf(localDisplay.getWidth());
        ˎ = Integer.valueOf(localDisplay.getHeight());
        return;
      }
      ॱ = Integer.valueOf(localDisplay.getHeight());
      ˎ = Integer.valueOf(localDisplay.getWidth());
    }
    
    public static void ॱ(Context paramContext, View paramView)
    {
      ((InputMethodManager)paramContext.getSystemService("input_method")).hideSoftInputFromWindow(paramView.getWindowToken(), 0);
    }
    
    public static void ॱ(View paramView, RelativeLayout.LayoutParams paramLayoutParams)
    {
      if (paramView.getLayoutParams() == paramLayoutParams) {
        return;
      }
      paramView.setLayoutParams(paramLayoutParams);
    }
    
    public static void ॱ(EditText paramEditText, boolean paramBoolean)
    {
      int i = paramEditText.getSelectionStart();
      int j = paramEditText.getSelectionEnd();
      PasswordTransformationMethod localPasswordTransformationMethod;
      if (paramBoolean) {
        localPasswordTransformationMethod = null;
      } else {
        localPasswordTransformationMethod = PasswordTransformationMethod.getInstance();
      }
      paramEditText.setTransformationMethod(localPasswordTransformationMethod);
      paramEditText.setSelection(i, j);
    }
    
    public static void ॱ(TextView paramTextView, TypefaceSpan paramTypefaceSpan, String paramString1, String paramString2)
    {
      SpannableStringBuilder localSpannableStringBuilder = new SpannableStringBuilder(paramString1);
      if (!TextUtils.isEmpty(paramString2))
      {
        int i = paramString1.toLowerCase().indexOf(paramString2.toLowerCase());
        if ((i >= 0) && (paramTypefaceSpan != null)) {
          localSpannableStringBuilder.setSpan(paramTypefaceSpan, i, paramString2.length() + i, 33);
        }
      }
      paramTextView.setText(localSpannableStringBuilder);
    }
    
    public static void ॱ(TextView paramTextView, CharSequence paramCharSequence, int paramInt1, int paramInt2, View.OnClickListener paramOnClickListener, boolean paramBoolean)
    {
      ˎ(paramTextView, paramCharSequence, paramInt1, paramInt2, paramOnClickListener, paramBoolean, 0);
    }
  }
  
  public static class IF
  {
    public IF() {}
    
    private static int ˎ(Context paramContext)
    {
      try
      {
        int i = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(paramContext);
        return i;
      }
      catch (Throwable paramContext)
      {
        ahz.iF.ॱ(paramContext, "Google Play Services check failed");
        boolean bool = κ.ˏ;
        ʢ.ॱ(false, "Google Play Services check failed due to ->" + paramContext.getMessage());
      }
      return Integer.MIN_VALUE;
    }
    
    public static boolean ˏ(Context paramContext)
    {
      return ˎ(paramContext) == 0;
    }
  }
  
  public static class If
  {
    public If() {}
    
    public static List<agd> ˏ(List<agd> paramList1, List<agd> paramList2)
    {
      ArrayList localArrayList = new ArrayList();
      paramList1 = paramList1.iterator();
      while (paramList1.hasNext())
      {
        agd localAgd = (agd)paramList1.next();
        if (!Preferences.ᐝ.ˊ(localAgd))
        {
          PY.ˊ().log(Level.INFO, "Class of Service: Feature enabled: " + localAgd);
          localArrayList.add(localAgd);
        }
        Preferences.ᐝ.ˏ(localAgd, true);
      }
      paramList1 = paramList2.iterator();
      while (paramList1.hasNext())
      {
        paramList2 = (agd)paramList1.next();
        if (Preferences.ᐝ.ˊ(paramList2))
        {
          localArrayList.add(paramList2);
          PY.ˊ().log(Level.INFO, "Class of Service: Feature disabled: " + paramList2);
        }
        Preferences.ᐝ.ˏ(paramList2, false);
      }
      return localArrayList;
    }
  }
  
  public static class aUX
  {
    public aUX() {}
    
    private static String ʼ(JSONArray paramJSONArray)
    {
      int i = 0;
      try
      {
        while (i < paramJSONArray.length())
        {
          UP localUP = new UP(paramJSONArray.getJSONObject(i), i);
          if (localUP.ˊ())
          {
            paramJSONArray = localUP.ˋ();
            return paramJSONArray;
          }
          i += 1;
        }
      }
      catch (JSONException paramJSONArray)
      {
        PY.ˊ().ˎ(Level.SEVERE, paramJSONArray);
      }
      return null;
    }
    
    private static String ˊ(String paramString1, String paramString2)
    {
      for (;;)
      {
        int i;
        int k;
        try
        {
          paramString1 = new JSONArray(paramString1);
          j = 1;
          i = 0;
          if (i < paramString1.length())
          {
            k = j;
            if (paramString1.getJSONObject(i).getString("label").contains(paramString2)) {
              k = j + 1;
            }
          }
          else
          {
            paramString1 = paramString2 + " " + j;
            return paramString1;
          }
        }
        catch (JSONException paramString1)
        {
          PY.ˊ().ˎ(Level.SEVERE, paramString1);
          return null;
        }
        i += 1;
        int j = k;
      }
    }
    
    public static UP ˊ()
    {
      try
      {
        if (Preferences.con.ˋ() != null)
        {
          UP localUP = ˋ(new JSONArray(Preferences.con.ˋ()));
          return localUP;
        }
        return null;
      }
      catch (JSONException localJSONException)
      {
        PY.ˊ().ˎ(Level.SEVERE, localJSONException);
      }
      return null;
    }
    
    public static Uu ˊ(String paramString)
    {
      try
      {
        JSONArray localJSONArray = new JSONArray(Preferences.ˎ.ॱ());
        int i = 0;
        while (i < localJSONArray.length())
        {
          Uu localUu = new Uu(localJSONArray.getJSONObject(i), i);
          if (!TextUtils.isEmpty(paramString))
          {
            boolean bool = localUu.ˏ().equals(paramString);
            if (bool) {
              return localUu;
            }
          }
          i += 1;
        }
      }
      catch (JSONException paramString)
      {
        PY.ˊ().ˎ(Level.SEVERE, paramString);
      }
      return null;
    }
    
    private static Uu ˊ(JSONArray paramJSONArray)
    {
      int i = 0;
      try
      {
        while (i < paramJSONArray.length())
        {
          Uu localUu = new Uu(paramJSONArray.getJSONObject(i), i);
          boolean bool = localUu.ˊ();
          if (bool) {
            return localUu;
          }
          i += 1;
        }
      }
      catch (JSONException paramJSONArray)
      {
        PY.ˊ().ˎ(Level.SEVERE, paramJSONArray);
      }
      return null;
    }
    
    public static int ˊॱ()
    {
      if (Preferences.con.ˋ() != null) {
        try
        {
          int i = new JSONArray(Preferences.con.ˋ()).length();
          return i - 1;
        }
        catch (JSONException localJSONException)
        {
          PY.ˊ().ˎ(Level.SEVERE, localJSONException);
        }
      }
      return 0;
    }
    
    public static String ˋ()
    {
      return ˊ(Preferences.ˎ.ॱ(), TFApplication.ˏॱ().getApplicationContext().getResources().getString(2131231132));
    }
    
    private static UP ˋ(JSONArray paramJSONArray)
    {
      int i = 0;
      try
      {
        while (i < paramJSONArray.length())
        {
          UP localUP = new UP(paramJSONArray.getJSONObject(i), i);
          boolean bool = localUP.ˊ();
          if (bool) {
            return localUP;
          }
          i += 1;
        }
      }
      catch (JSONException paramJSONArray)
      {
        PY.ˊ().ˎ(Level.SEVERE, paramJSONArray);
      }
      return null;
    }
    
    private static String ˎ(JSONArray paramJSONArray)
    {
      int i = 0;
      try
      {
        while (i < paramJSONArray.length())
        {
          Uu localUu = new Uu(paramJSONArray.getJSONObject(i), i);
          if (localUu.ˊ())
          {
            paramJSONArray = localUu.ˋ();
            return paramJSONArray;
          }
          i += 1;
        }
      }
      catch (JSONException paramJSONArray)
      {
        PY.ˊ().ˎ(Level.SEVERE, paramJSONArray);
      }
      return null;
    }
    
    public static Uu ˎ()
    {
      try
      {
        if (Preferences.ˎ.ॱ() != null)
        {
          Uu localUu = ˊ(new JSONArray(Preferences.ˎ.ॱ()));
          return localUu;
        }
        return null;
      }
      catch (JSONException localJSONException)
      {
        PY.ˊ().ˎ(Level.SEVERE, localJSONException);
      }
      return null;
    }
    
    public static int ˏ()
    {
      if (Preferences.ˎ.ॱ() != null) {
        try
        {
          int i = new JSONArray(Preferences.ˎ.ॱ()).length();
          return i - 1;
        }
        catch (JSONException localJSONException)
        {
          PY.ˊ().ˎ(Level.SEVERE, localJSONException);
        }
      }
      return 0;
    }
    
    public static UP ˏ(String paramString)
    {
      try
      {
        JSONArray localJSONArray = new JSONArray(Preferences.con.ˋ());
        int i = 0;
        while (i < localJSONArray.length())
        {
          UP localUP = new UP(localJSONArray.getJSONObject(i), i);
          if (!TextUtils.isEmpty(paramString))
          {
            boolean bool = localUP.ˏ().equals(paramString);
            if (bool) {
              return localUP;
            }
          }
          i += 1;
        }
      }
      catch (JSONException paramString)
      {
        PY.ˊ().ˎ(Level.SEVERE, paramString);
      }
      return null;
    }
    
    public static void ˏ(JSONArray paramJSONArray)
    {
      paramJSONArray = ʼ(paramJSONArray);
      if (!TextUtils.isEmpty(paramJSONArray)) {
        Preferences.con.ˊ(paramJSONArray);
      }
    }
    
    public static String ॱ()
    {
      return ˊ(Preferences.con.ˋ(), TFApplication.ˏॱ().getApplicationContext().getResources().getString(2131231133));
    }
    
    public static void ॱ(JSONArray paramJSONArray)
    {
      paramJSONArray = ˎ(paramJSONArray);
      if (!TextUtils.isEmpty(paramJSONArray)) {
        Preferences.ˎ.ˏ(paramJSONArray);
      }
    }
  }
  
  public static class aUx
  {
    public aUx() {}
    
    public static void ˎ(long paramLong, Activity paramActivity)
    {
      Intent localIntent = new Intent("android.intent.action.EDIT");
      localIntent.putExtra("finishActivityOnSaveCompleted", true);
      localIntent.setData(ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, paramLong));
      paramActivity.startActivityForResult(localIntent, 1014);
    }
    
    public static void ˎ(String paramString, Activity paramActivity)
    {
      Intent localIntent = new Intent("android.intent.action.INSERT_OR_EDIT");
      localIntent.setType("vnd.android.cursor.item/contact");
      if (ahi.ˌ.ʽ(paramString)) {
        localIntent.putExtra("phone", paramString);
      } else if (ahi.ˌ.ʼ(paramString)) {
        localIntent.putExtra("email", paramString);
      } else {
        localIntent.putExtra("name", paramString);
      }
      paramActivity.startActivityForResult(localIntent, 1014);
    }
    
    public static void ˎ(String paramString1, String paramString2, Activity paramActivity)
    {
      Intent localIntent = new Intent("android.intent.action.INSERT", ContactsContract.Contacts.CONTENT_URI);
      String str = null;
      if (ahi.ˌ.ʽ(paramString1)) {
        str = "phone";
      } else if (ahi.ˌ.ʼ(paramString1)) {
        str = "email";
      }
      if (str != null) {
        localIntent.putExtra(str, paramString1);
      }
      if ((!TextUtils.isEmpty(paramString2)) && (!ahi.ˌ.ʽ(paramString2))) {
        localIntent.putExtra("name", paramString2);
      }
      paramActivity.startActivityForResult(localIntent, 1014);
    }
    
    public static void ˏ(boolean paramBoolean)
    {
      Preferences.ͺ.If.ॱ(paramBoolean);
      if (paramBoolean)
      {
        NativeCommunicationsSyncService.ˎ(TFApplication.ˏॱ().getApplicationContext(), false);
        return;
      }
      NativeCommunicationsSyncService.ˎ(TFApplication.ˏॱ().getApplicationContext());
    }
  }
  
  public static class auX
  {
    public auX() {}
    
    public static String ˊ(Context paramContext)
    {
      return paramContext.getString(2131231848);
    }
    
    public static String ˊ(boolean paramBoolean)
    {
      Resources localResources = PingerApplication.ˋ().getResources();
      String str = ॱ();
      if (str == null)
      {
        if (paramBoolean) {
          return localResources.getString(2131231679);
        }
        return localResources.getString(2131231097);
      }
      return str;
    }
    
    public static boolean ˊ()
    {
      return (Preferences.ͺ.If.ᐝ()) && (Preferences.auX.ॱ());
    }
    
    public static boolean ˋ()
    {
      NetworkInfo[] arrayOfNetworkInfo = ((ConnectivityManager)PingerApplication.ˋ().getSystemService("connectivity")).getAllNetworkInfo();
      boolean bool = false;
      int i = 0;
      while ((!bool) && (i < arrayOfNetworkInfo.length)) {
        if (arrayOfNetworkInfo[i].isConnected()) {
          bool = true;
        } else {
          i += 1;
        }
      }
      return bool;
    }
    
    public static String ˎ()
    {
      return ˊ(false);
    }
    
    public static boolean ˎ(Context paramContext)
    {
      paramContext = (TelephonyManager)paramContext.getSystemService("phone");
      return (paramContext != null) && (paramContext.getCallState() != 0);
    }
    
    public static int ˏ(String paramString)
    {
      if (TextUtils.isEmpty(paramString)) {
        return 0;
      }
      int j = 0;
      int i = 0;
      while (i < paramString.length())
      {
        j += ॱ(paramString.charAt(i));
        i += 1;
      }
      return j;
    }
    
    public static Pair<Integer, String> ˏ()
    {
      Object localObject = ((ConnectivityManager)PingerApplication.ˋ().getSystemService("connectivity")).getActiveNetworkInfo();
      int j = Integer.MIN_VALUE;
      String str2 = null;
      int i = j;
      String str1 = str2;
      if (localObject != null)
      {
        i = j;
        str1 = str2;
        if (((NetworkInfo)localObject).isAvailable())
        {
          i = j;
          str1 = str2;
          if (((NetworkInfo)localObject).isConnected()) {
            if (((NetworkInfo)localObject).getType() != 0)
            {
              i = j;
              str1 = str2;
              if (((NetworkInfo)localObject).getType() != 1) {}
            }
            else
            {
              j = ((NetworkInfo)localObject).getType();
              i = j;
              str1 = str2;
              if (j == 1)
              {
                localObject = ((WifiManager)PingerApplication.ˋ().getApplicationContext().getSystemService("wifi")).getConnectionInfo();
                i = j;
                str1 = str2;
                if (localObject != null)
                {
                  i = j;
                  str1 = str2;
                  if (!TextUtils.isEmpty(((WifiInfo)localObject).getSSID()))
                  {
                    str2 = ((WifiInfo)localObject).getSSID();
                    i = j;
                    str1 = str2;
                    if (Build.VERSION.SDK_INT >= 16)
                    {
                      i = j;
                      str1 = str2;
                      if (str2.startsWith("\""))
                      {
                        i = j;
                        str1 = str2;
                        if (str2.endsWith("\""))
                        {
                          str2.substring(1, str2.length() - 1);
                          str1 = str2;
                          i = j;
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
      return Pair.create(Integer.valueOf(i), str1);
    }
    
    public static boolean ˏ(int paramInt)
    {
      NetworkInfo localNetworkInfo = ((ConnectivityManager)PingerApplication.ˋ().getSystemService("connectivity")).getNetworkInfo(paramInt);
      return (localNetworkInfo != null) && (localNetworkInfo.isAvailable()) && ((localNetworkInfo.getState() == NetworkInfo.State.CONNECTED) || (localNetworkInfo.getState() == NetworkInfo.State.CONNECTING));
    }
    
    public static int ॱ(char paramChar)
    {
      if (ahi.AuX.ˊ == null)
      {
        ahi.AuX.ˊ = new ArrayList();
        ahi.AuX.ˊ.add(Character.valueOf('^'));
        ahi.AuX.ˊ.add(Character.valueOf('{'));
        ahi.AuX.ˊ.add(Character.valueOf('}'));
        ahi.AuX.ˊ.add(Character.valueOf(']'));
        ahi.AuX.ˊ.add(Character.valueOf('['));
        ahi.AuX.ˊ.add(Character.valueOf('~'));
        ahi.AuX.ˊ.add(Character.valueOf('\\'));
        ahi.AuX.ˊ.add(Character.valueOf('\f'));
        ahi.AuX.ˊ.add(Character.valueOf('€'));
        ahi.AuX.ˊ.add(Character.valueOf('|'));
      }
      if (ahi.AuX.ˊ.contains(Character.valueOf(paramChar))) {
        return 2;
      }
      return 1;
    }
    
    public static String ॱ()
    {
      TelephonyManager localTelephonyManager = (TelephonyManager)TFApplication.ˋ().getSystemService("phone");
      if ((TextUtils.isEmpty(localTelephonyManager.getNetworkOperatorName())) || (localTelephonyManager.getNetworkType() == 0)) {
        return null;
      }
      return localTelephonyManager.getNetworkOperatorName();
    }
    
    public static List<UQ> ॱ(Context paramContext)
    {
      ArrayList localArrayList = new ArrayList();
      int i;
      if (Build.VERSION.SDK_INT >= 16) {
        i = 1;
      } else {
        i = 0;
      }
      Object localObject2 = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getNetworkInfo(1);
      Object localObject3 = (WifiManager)paramContext.getSystemService("wifi");
      String str = ((WifiManager)localObject3).getConnectionInfo().getBSSID();
      Object localObject1 = ((WifiManager)localObject3).getConnectionInfo().getSSID();
      paramContext = (Context)localObject1;
      if (i != 0)
      {
        paramContext = (Context)localObject1;
        if (localObject1 != null)
        {
          paramContext = (Context)localObject1;
          if (((String)localObject1).startsWith("\""))
          {
            paramContext = (Context)localObject1;
            if (((String)localObject1).endsWith("\"")) {
              paramContext = ((String)localObject1).substring(1, ((String)localObject1).length() - 1);
            }
          }
        }
      }
      boolean bool2 = ((NetworkInfo)localObject2).isConnected();
      if (((WifiManager)localObject3).getScanResults() != null)
      {
        localObject3 = ((WifiManager)localObject3).getScanResults().iterator();
        while (((Iterator)localObject3).hasNext())
        {
          ScanResult localScanResult = (ScanResult)((Iterator)localObject3).next();
          if (localScanResult != null)
          {
            localObject2 = localScanResult.SSID;
            localObject1 = localObject2;
            if (i != 0)
            {
              localObject1 = localObject2;
              if (((String)localObject2).startsWith("\""))
              {
                localObject1 = localObject2;
                if (((String)localObject2).endsWith("\"")) {
                  localObject1 = ((String)localObject2).substring(1, ((String)localObject2).length() - 1);
                }
              }
            }
            localObject2 = localScanResult.BSSID;
            boolean bool3 = false;
            boolean bool1 = bool3;
            if (TextUtils.equals((CharSequence)localObject2, str))
            {
              bool1 = bool3;
              if (TextUtils.equals((CharSequence)localObject1, paramContext)) {
                bool1 = bool2;
              }
            }
            localArrayList.add(new UQ(bool1, (String)localObject1, (String)localObject2));
          }
        }
      }
      return localArrayList;
    }
    
    public static void ॱ(Context paramContext, UB paramUB)
    {
      Object localObject = new Intent("com.pinger.textfree.SMS_SENT");
      ((Intent)localObject).putExtra("conversation_item", paramUB);
      paramContext = PendingIntent.getBroadcast(paramContext, 0, (Intent)localObject, 134217728);
      localObject = SmsManager.getDefault();
      ArrayList localArrayList1 = ((SmsManager)localObject).divideMessage(paramUB.getMessageText());
      if (localArrayList1.size() > 1)
      {
        ArrayList localArrayList2 = new ArrayList(localArrayList1.size());
        int i = 0;
        while (i < localArrayList1.size())
        {
          localArrayList2.add(paramContext);
          i += 1;
        }
        ((SmsManager)localObject).sendMultipartTextMessage(paramUB.getAddress(), null, localArrayList1, localArrayList2, null);
        return;
      }
      ((SmsManager)localObject).sendTextMessage(paramUB.getAddress(), null, paramUB.getMessageText(), paramContext, null);
    }
  }
  
  public static class aux
  {
    public aux() {}
    
    public static String ˊ(String paramString)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      paramString = paramString.split(", ");
      int j = 1;
      int k = paramString.length;
      int i = 0;
      while (i < k)
      {
        String str = paramString[i];
        if (j == 0) {
          localStringBuilder.append(", ");
        } else {
          j = 0;
        }
        localStringBuilder.append(ahi.ʽ.ॱ(str));
        i += 1;
      }
      return localStringBuilder.toString();
    }
    
    public static List<UK> ˊ(String paramString1, String paramString2, boolean paramBoolean)
    {
      String[] arrayOfString = new String[0];
      if (!TextUtils.isEmpty(paramString2)) {
        arrayOfString = paramString2.split(",");
      }
      paramString1 = ˋ(paramString1.split(", "), arrayOfString, paramString2, paramBoolean);
      Collections.sort(paramString1);
      return paramString1;
    }
    
    public static String ˋ(ArrayList<ContactAddressAndName> paramArrayList, boolean paramBoolean)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      int i = 1;
      paramArrayList = paramArrayList.iterator();
      while (paramArrayList.hasNext())
      {
        ContactAddressAndName localContactAddressAndName = (ContactAddressAndName)paramArrayList.next();
        if (i == 0) {
          localStringBuilder.append(", ");
        } else {
          i = 0;
        }
        localStringBuilder.append(localContactAddressAndName.ॱ());
      }
      return ॱ(localStringBuilder.toString(), paramBoolean);
    }
    
    private static List<UK> ˋ(String[] paramArrayOfString1, String[] paramArrayOfString2, String paramString, boolean paramBoolean)
    {
      ArrayList localArrayList = new ArrayList();
      int k = 0;
      int j = 0;
      while (j < paramArrayOfString1.length)
      {
        UK localUK = new UK();
        String str = paramArrayOfString1[j].trim();
        int m = 1;
        int i = m;
        if (paramBoolean) {
          if (ahi.ˌ.ʽ(str))
          {
            i = m;
            if (ahi.ʿ.ˋ(ahi.ʿ.ˎ(str, true)).equals(ahi.ʿ.ˋ(TFService.getTFInstance().getProfile().ʿ()))) {
              i = 0;
            }
          }
          else
          {
            i = m;
            if (str.equals(TFService.getTFInstance().getProfile().ˏ())) {
              i = 0;
            }
          }
        }
        if ((i != 0) || ((i == 0) && (k != 0)))
        {
          localUK.ˋ(str);
          if (!TextUtils.isEmpty(paramString)) {
            localUK.ˊ(paramArrayOfString2[j].trim());
          }
          localArrayList.add(localUK);
        }
        else
        {
          k = 1;
        }
        j += 1;
      }
      return localArrayList;
    }
    
    public static String ˏ(String paramString)
    {
      return ˏ(ॱ(paramString, null));
    }
    
    public static String ˏ(List<UK> paramList)
    {
      if (paramList.size() < 1) {
        return null;
      }
      StringBuilder localStringBuilder = new StringBuilder();
      int i = 1;
      paramList = paramList.iterator();
      while (paramList.hasNext())
      {
        UK localUK = (UK)paramList.next();
        if (i != 0)
        {
          localStringBuilder.append(localUK.ˊ());
          i = 0;
        }
        else
        {
          localStringBuilder.append(", ");
          localStringBuilder.append(localUK.ˊ());
        }
      }
      return localStringBuilder.toString();
    }
    
    public static String ॱ(String paramString, boolean paramBoolean)
    {
      return ˏ(ˊ(paramString, null, paramBoolean));
    }
    
    public static String ॱ(ArrayList<ContactAddressAndName> paramArrayList)
    {
      return ˋ(paramArrayList, false);
    }
    
    public static String ॱ(List<UK> paramList)
    {
      if (paramList.size() < 1) {
        return null;
      }
      StringBuilder localStringBuilder = new StringBuilder();
      int i = 1;
      paramList = paramList.iterator();
      while (paramList.hasNext())
      {
        UK localUK = (UK)paramList.next();
        if (i != 0)
        {
          localStringBuilder.append(localUK.ˎ());
          i = 0;
        }
        else
        {
          localStringBuilder.append(", ");
          localStringBuilder.append(localUK.ˎ());
        }
      }
      return localStringBuilder.toString();
    }
    
    public static List<UK> ॱ(String paramString1, String paramString2)
    {
      return ˊ(paramString1, paramString2, false);
    }
  }
  
  public static class cOn
  {
    public cOn() {}
    
    public static String ˊ(String paramString)
    {
      return paramString.replaceAll("[^0-9.]", "");
    }
    
    public static <T> List<List<T>> ˏ(List<T> paramList, int paramInt)
    {
      ArrayList localArrayList = new ArrayList();
      int j = paramList.size();
      int i = 0;
      while (i < j)
      {
        localArrayList.add(new ArrayList(paramList.subList(i, Math.min(j, i + paramInt))));
        i += paramInt;
      }
      return localArrayList;
    }
  }
  
  public static class con
  {
    public con() {}
    
    public static void ˊ()
    {
      File localFile1 = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_RINGTONES).getAbsolutePath() + File.separator + "sideline_ringtone.mp3");
      File localFile2 = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_NOTIFICATIONS).getAbsolutePath() + File.separator + "sideline_ringtone.mp3");
      ˊ(localFile1);
      ˊ(localFile2);
      MediaScannerConnection.scanFile(PingerApplication.ˋ().getApplicationContext(), new String[] { localFile1.getAbsolutePath() }, null, null);
    }
    
    private static void ˊ(File paramFile)
    {
      if ((paramFile != null) && (paramFile.exists())) {
        ahi.ʼ.ˋ(paramFile, false);
      }
    }
    
    public static Uri ˋ(int paramInt)
    {
      Uri localUri = ˏ(paramInt);
      File localFile = ˎ(paramInt);
      if ((localUri == null) || (!localFile.exists()))
      {
        if (!ˋ(PingerApplication.ˋ(), paramInt)) {
          return RingtoneManager.getActualDefaultRingtoneUri(PingerApplication.ˋ(), paramInt);
        }
        return ˏ(paramInt);
      }
      return localUri;
    }
    
    public static void ˋ(Ringtone paramRingtone, boolean paramBoolean)
    {
      if (paramRingtone != null) {
        try
        {
          if (paramRingtone.isPlaying()) {
            paramRingtone.stop();
          }
          paramRingtone.setStreamType(5);
          paramRingtone.play();
        }
        catch (Throwable paramRingtone)
        {
          ʢ.ॱ(κ.ˏ, "Cannot play ringtone");
        }
      }
      if (paramBoolean) {
        ((Vibrator)PingerApplication.ˋ().getApplicationContext().getSystemService("vibrator")).vibrate(1000L);
      }
    }
    
    public static boolean ˋ(Context paramContext, int paramInt)
    {
      if (paramInt == 2) {
        localObject1 = "sounds/Pinger_Notification.mp3";
      } else if (paramInt == 1) {
        localObject1 = paramContext.getString(2131231790);
      } else {
        localObject1 = null;
      }
      Object localObject2 = ˎ(paramInt);
      if ((!((File)localObject2).getParentFile().exists()) && (!((File)localObject2).getParentFile().mkdirs())) {
        return false;
      }
      if ((!((File)localObject2).exists()) && (!TextUtils.isEmpty((CharSequence)localObject1))) {
        ॱ(paramContext, (String)localObject1, ((File)localObject2).getAbsolutePath());
      }
      if (!((File)localObject2).exists()) {
        return false;
      }
      Object localObject1 = new Semaphore(0, true);
      localObject2 = ((File)localObject2).getAbsolutePath();
      ahu localAhu = new ahu(paramInt, (Semaphore)localObject1);
      MediaScannerConnection.scanFile(paramContext, new String[] { localObject2 }, new String[] { "audio/mp3" }, localAhu);
      try
      {
        ((Semaphore)localObject1).acquire();
      }
      catch (InterruptedException paramContext)
      {
        paramContext.printStackTrace();
      }
      return ˏ(paramInt) != null;
    }
    
    public static File ˎ(int paramInt)
    {
      String str;
      if (paramInt == 2) {
        str = Environment.DIRECTORY_NOTIFICATIONS;
      } else if (paramInt == 1) {
        str = Environment.DIRECTORY_RINGTONES;
      } else {
        str = null;
      }
      boolean bool;
      if ((κ.ˏ) && (!TextUtils.isEmpty(str))) {
        bool = true;
      } else {
        bool = false;
      }
      ν.ˎ(bool, "Invalid type = " + paramInt);
      StringBuilder localStringBuilder = new StringBuilder().append(Environment.getExternalStoragePublicDirectory(str).getAbsolutePath()).append(File.separator);
      if (paramInt == 2) {
        str = "Pinger.mp3";
      } else {
        str = PingerApplication.ˋ().getString(2131231791);
      }
      return new File(String.valueOf(str));
    }
    
    public static void ˎ(Ringtone paramRingtone)
    {
      ˋ(paramRingtone, ((AudioManager)PingerApplication.ˋ().getApplicationContext().getSystemService("audio")).shouldVibrate(0));
    }
    
    public static Uri ˏ(int paramInt)
    {
      if (paramInt == 2) {
        return Preferences.if.ʽ();
      }
      if (paramInt == 1) {
        return Preferences.if.ˊॱ();
      }
      return null;
    }
    
    private static boolean ॱ(Context paramContext, String paramString1, String paramString2)
    {
      Object localObject3 = null;
      Context localContext = null;
      Object localObject8 = null;
      Object localObject7 = null;
      Object localObject6 = null;
      byte[] arrayOfByte = null;
      Object localObject1 = localObject7;
      Object localObject2 = arrayOfByte;
      Object localObject4 = localObject8;
      Object localObject5 = localObject6;
      try
      {
        paramContext = paramContext.getAssets().openFd(paramString1);
        localContext = paramContext;
        localObject1 = localObject7;
        localObject2 = arrayOfByte;
        localObject3 = paramContext;
        localObject4 = localObject8;
        localObject5 = localObject6;
        paramString1 = paramContext.createInputStream();
        localContext = paramContext;
        localObject1 = paramString1;
        localObject2 = arrayOfByte;
        localObject3 = paramContext;
        localObject4 = paramString1;
        localObject5 = localObject6;
        paramString2 = new FileOutputStream(new File(paramString2));
        localContext = paramContext;
        localObject1 = paramString1;
        localObject2 = paramString2;
        localObject3 = paramContext;
        localObject4 = paramString1;
        localObject5 = paramString2;
        arrayOfByte = new byte['Ѐ'];
        for (;;)
        {
          localContext = paramContext;
          localObject1 = paramString1;
          localObject2 = paramString2;
          localObject3 = paramContext;
          localObject4 = paramString1;
          localObject5 = paramString2;
          int i = paramString1.read(arrayOfByte);
          if (i <= 0) {
            break;
          }
          localContext = paramContext;
          localObject1 = paramString1;
          localObject2 = paramString2;
          localObject3 = paramContext;
          localObject4 = paramString1;
          localObject5 = paramString2;
          paramString2.write(arrayOfByte, 0, i);
        }
        if (paramString1 != null) {}
        try
        {
          paramString1.close();
          if (paramString2 != null) {
            paramString2.close();
          }
          if (paramContext != null) {
            paramContext.close();
          }
          return true;
        }
        catch (IOException paramContext)
        {
          paramContext.printStackTrace();
          return true;
        }
        return false;
      }
      catch (IOException paramContext)
      {
        localObject3 = localContext;
        localObject4 = localObject1;
        localObject5 = localObject2;
        paramContext.printStackTrace();
        if (localObject1 != null) {}
        try
        {
          ((FileInputStream)localObject1).close();
          if (localObject2 != null) {
            ((FileOutputStream)localObject2).close();
          }
          if (localContext != null) {
            localContext.close();
          }
        }
        catch (IOException paramContext)
        {
          paramContext.printStackTrace();
        }
      }
      finally
      {
        if (localObject4 != null) {}
        try
        {
          ((FileInputStream)localObject4).close();
          if (localObject5 != null) {
            ((FileOutputStream)localObject5).close();
          }
          if (localObject3 != null) {
            ((AssetFileDescriptor)localObject3).close();
          }
        }
        catch (IOException paramString1)
        {
          paramString1.printStackTrace();
        }
      }
    }
  }
  
  public static class iF
  {
    public iF() {}
    
    public static void ˎ()
    {
      ahi.AuX.ˋ(new ahg(), "Delete contact addresses with email only");
    }
    
    public static String ॱ(String paramString1, String paramString2, byte paramByte)
    {
      if (!TextUtils.isEmpty(paramString1))
      {
        paramString1 = ahi.ʿ.ˎ(Integer.valueOf(paramString1), paramString2, Ut.onPinger(paramByte));
        if (!TextUtils.isEmpty(paramString1)) {
          return paramString1;
        }
      }
      return null;
    }
  }
  
  public static class if
  {
    public if() {}
    
    public static String ˎ()
    {
      Object localObject = null;
      try
      {
        Account[] arrayOfAccount = AccountManager.get(PingerApplication.ˋ()).getAccountsByType("com.google");
        localObject = arrayOfAccount;
      }
      catch (Exception localException)
      {
        PY.ˊ().ˎ(Level.SEVERE, localException);
      }
      if ((localObject != null) && (localObject.length != 0)) {
        return localObject[0].name;
      }
      return null;
    }
    
    public static boolean ˏ()
    {
      return (ˎ() != null) && (TFApplication.ˏॱ().ॱˎ());
    }
  }
  
  private static class ʻ
  {
    private final List<Object> ˏ;
    
    public ʻ()
    {
      this.ˏ = new ArrayList();
    }
    
    public ʻ(Object paramObject)
    {
      if (!paramObject.getClass().isArray()) {
        throw new JSONException("Not a primitive array: " + paramObject.getClass());
      }
      int j = Array.getLength(paramObject);
      this.ˏ = new ArrayList(j);
      int i = 0;
      while (i < j)
      {
        ॱ(ahi.Aux.ˊ(Array.get(paramObject, i)));
        i += 1;
      }
    }
    
    public ʻ ॱ(Object paramObject)
    {
      this.ˏ.add(paramObject);
      return this;
    }
  }
  
  public static class ʼ
  {
    public ʼ() {}
    
    public static void ʻ()
    {
      Context localContext = PingerApplication.ˋ().getApplicationContext();
      Intent localIntent = new Intent("com.android.music.musicservicecommand");
      localIntent.putExtra("command", "togglepause");
      localContext.sendBroadcast(localIntent);
    }
    
    public static boolean ʻ(String paramString)
    {
      if (TextUtils.isEmpty(paramString)) {
        return false;
      }
      if (!new File(paramString).exists()) {
        return false;
      }
      BitmapFactory.Options localOptions = new BitmapFactory.Options();
      localOptions.inJustDecodeBounds = true;
      BitmapFactory.decodeFile(paramString, localOptions);
      return (localOptions.outWidth > 0) && (localOptions.outHeight > 0);
    }
    
    public static String ʼ()
    {
      return Environment.getExternalStorageDirectory().getPath() + File.separator + PingerApplication.ˋ().getString(2131231745);
    }
    
    public static boolean ʼ(String paramString)
    {
      return ॱ(paramString, true);
    }
    
    public static File ʽ(String paramString)
    {
      StringBuilder localStringBuilder = new StringBuilder().append(ʼ()).append(File.separator).append("media_").append(paramString);
      if (!paramString.endsWith(".jpg")) {
        paramString = ".jpg";
      } else {
        paramString = "";
      }
      return new File(paramString);
    }
    
    public static String ʽ()
    {
      String str = Environment.getExternalStorageDirectory().getAbsolutePath() + "/TextfreeRecordings/" + "Upload/";
      File localFile = new File(str);
      if (!localFile.exists()) {
        localFile.mkdirs();
      }
      return str + "record." + "wav";
    }
    
    public static ConversationMediaContainer.iF ˊ(String paramString)
    {
      if (!TextUtils.isEmpty(paramString))
      {
        String str = paramString.toLowerCase();
        if ((str.endsWith("3gpp")) || (str.endsWith("3gp")) || (str.endsWith("mp4"))) {
          return ConversationMediaContainer.iF.VIDEO;
        }
        if ((str.endsWith("amr")) || (str.endsWith("wav")) || (str.endsWith("mp3")) || (str.endsWith("ogg"))) {
          return ConversationMediaContainer.iF.AUDIO;
        }
        if ((str.endsWith("jpg")) || (str.endsWith("jpeg")) || (str.endsWith("png")) || (str.endsWith("gif")) || (TFApplication.ˏॱ().ʼॱ().ˊ(paramString))) {
          return ConversationMediaContainer.iF.IMAGE;
        }
        return ConversationMediaContainer.iF.UNKNOWN;
      }
      return ConversationMediaContainer.iF.NONE;
    }
    
    public static String ˊ()
    {
      String str = Environment.getExternalStorageDirectory().getAbsolutePath() + "/TextfreeRecordings/";
      File localFile = new File(str);
      if (!localFile.exists()) {
        localFile.mkdirs();
      }
      return str + "record." + "wav";
    }
    
    public static boolean ˊ(Context paramContext, String paramString)
    {
      return (paramString.contains(paramContext.getString(2131231583))) || (paramString.contains(paramContext.getString(2131231585))) || (paramString.contains(paramContext.getString(2131231584)));
    }
    
    public static boolean ˊ(String paramString1, String paramString2)
    {
      return new File(paramString1).renameTo(new File(paramString2));
    }
    
    public static boolean ˊॱ()
    {
      return Environment.getExternalStorageState().equals("mounted");
    }
    
    public static boolean ˊॱ(String paramString)
    {
      Object localObject2 = null;
      Object localObject1 = null;
      System.setProperty("http.keepAlive", "false");
      boolean bool2 = false;
      bool1 = bool2;
      if (ˊ(paramString) == ConversationMediaContainer.iF.IMAGE) {
        try
        {
          HttpURLConnection localHttpURLConnection = (HttpURLConnection)new URL(paramString).openConnection();
          localObject1 = localHttpURLConnection;
          localObject2 = localHttpURLConnection;
          localHttpURLConnection.setRequestMethod("HEAD");
          localObject1 = localHttpURLConnection;
          localObject2 = localHttpURLConnection;
          localHttpURLConnection.setConnectTimeout(2000);
          localObject1 = localHttpURLConnection;
          localObject2 = localHttpURLConnection;
          localHttpURLConnection.setReadTimeout(2000);
          localObject1 = localHttpURLConnection;
          localObject2 = localHttpURLConnection;
          String str = localHttpURLConnection.getHeaderField("Content-Type");
          localObject1 = localHttpURLConnection;
          localObject2 = localHttpURLConnection;
          if (!TextUtils.isEmpty(str))
          {
            localObject1 = localHttpURLConnection;
            localObject2 = localHttpURLConnection;
            bool1 = str.startsWith("image/");
            if (bool1)
            {
              bool1 = true;
              break label146;
            }
          }
          bool1 = false;
          label146:
          bool2 = bool1;
          bool1 = bool2;
          if (localHttpURLConnection != null)
          {
            localHttpURLConnection.disconnect();
            try
            {
              if (localHttpURLConnection.getInputStream() != null) {
                localHttpURLConnection.getInputStream().close();
              }
              return bool2;
            }
            catch (IOException paramString)
            {
              return bool2;
            }
          }
          return bool1;
        }
        catch (IOException localIOException2)
        {
          localObject2 = localObject1;
          PY.ˊ().log(Level.INFO, "Could not determine the image type for " + paramString + " caused by " + localIOException2.getMessage());
          bool1 = bool2;
          if (localObject1 != null)
          {
            localObject1.disconnect();
            try
            {
              if (localObject1.getInputStream() != null) {
                localObject1.getInputStream().close();
              }
              return false;
            }
            catch (IOException paramString)
            {
              return false;
            }
          }
        }
        finally
        {
          if (localObject2 != null)
          {
            localObject2.disconnect();
            try
            {
              if (localObject2.getInputStream() != null) {
                localObject2.getInputStream().close();
              }
            }
            catch (IOException localIOException1) {}
          }
        }
      }
    }
    
    public static String ˋ()
    {
      return ˏ("png");
    }
    
    public static String ˋ(Bitmap paramBitmap)
    {
      String str = ˋ();
      aha.ˊ(paramBitmap, str, Bitmap.CompressFormat.PNG, 100);
      return str;
    }
    
    public static void ˋ(File paramFile, boolean paramBoolean)
    {
      if (paramFile != null)
      {
        File localFile = null;
        if (paramFile.exists())
        {
          if (paramBoolean) {
            localFile = paramFile.getParentFile();
          }
          paramFile.delete();
          if ((localFile != null) && (localFile.exists())) {
            localFile.delete();
          }
        }
      }
    }
    
    public static void ˋ(byte[] paramArrayOfByte, String paramString)
    {
      if (paramArrayOfByte != null) {
        try
        {
          paramString = new FileOutputStream(paramString);
          paramString.write(paramArrayOfByte);
          paramString.close();
          return;
        }
        catch (IOException paramArrayOfByte)
        {
          PY.ˊ().ˎ(Level.SEVERE, paramArrayOfByte);
        }
      }
    }
    
    public static boolean ˋ(String paramString)
    {
      return new File(paramString).exists();
    }
    
    public static boolean ˋ(String paramString1, String paramString2)
    {
      return (ʼ(paramString1)) && (ॱ(paramString1, paramString2) != null);
    }
    
    public static String ˋॱ(String paramString)
    {
      return paramString.replace("file://", "");
    }
    
    public static File ˎ(String paramString1, String paramString2)
    {
      if (TextUtils.isEmpty(paramString1)) {
        paramString1 = "";
      }
      paramString1 = Uri.parse(paramString1);
      if (paramString1.toString().startsWith("file")) {
        paramString1 = new File(Uri.decode(paramString1.toString().replace("file://", ""))).getName();
      } else {
        paramString1 = paramString2;
      }
      paramString2 = new StringBuilder().append(ʼ()).append(File.separator).append("media_").append(paramString1);
      if (!paramString1.endsWith(".jpg")) {
        paramString1 = ".jpg";
      } else {
        paramString1 = "";
      }
      return new File(paramString1);
    }
    
    public static String ˎ()
    {
      return "photo_tmp_" + System.currentTimeMillis();
    }
    
    public static String ˎ(String paramString)
    {
      Object localObject7 = null;
      Object localObject6 = null;
      Object localObject5 = null;
      FileOutputStream localFileOutputStream2 = null;
      Object localObject4 = null;
      Object localObject1 = localObject6;
      FileOutputStream localFileOutputStream1 = localFileOutputStream2;
      Object localObject2 = localObject7;
      Object localObject3 = localObject5;
      try
      {
        String str = ˋ();
        localObject1 = localObject6;
        localFileOutputStream1 = localFileOutputStream2;
        localObject4 = str;
        localObject2 = localObject7;
        localObject3 = localObject5;
        File localFile = new File(str);
        localObject1 = localObject6;
        localFileOutputStream1 = localFileOutputStream2;
        localObject4 = str;
        localObject2 = localObject7;
        localObject3 = localObject5;
        paramString = new URL(paramString).openStream();
        localObject1 = paramString;
        localFileOutputStream1 = localFileOutputStream2;
        localObject4 = str;
        localObject2 = paramString;
        localObject3 = localObject5;
        localFileOutputStream2 = new FileOutputStream(localFile);
        localObject1 = paramString;
        localFileOutputStream1 = localFileOutputStream2;
        localObject4 = str;
        localObject2 = paramString;
        localObject3 = localFileOutputStream2;
        ahr.ॱ(paramString, localFileOutputStream2);
        if (paramString != null) {}
        try
        {
          paramString.close();
          if (localFileOutputStream2 != null) {
            localFileOutputStream2.close();
          }
          return str;
        }
        catch (IOException paramString)
        {
          PY.ˊ().ˎ(Level.SEVERE, paramString);
          return str;
        }
        ॱ(localObject4);
      }
      catch (IOException paramString)
      {
        localObject2 = localObject1;
        localObject3 = localFileOutputStream1;
        PY.ˊ().ˎ(Level.SEVERE, paramString);
        if (localObject1 != null) {}
        try
        {
          ((InputStream)localObject1).close();
          if (localFileOutputStream1 != null) {
            localFileOutputStream1.close();
          }
        }
        catch (IOException paramString)
        {
          PY.ˊ().ˎ(Level.SEVERE, paramString);
        }
      }
      finally
      {
        if (localObject2 != null) {}
        try
        {
          ((InputStream)localObject2).close();
          if (localObject3 != null) {
            ((OutputStream)localObject3).close();
          }
        }
        catch (IOException localIOException)
        {
          PY.ˊ().ˎ(Level.SEVERE, localIOException);
        }
      }
      return null;
    }
    
    public static void ˎ(String paramString, boolean paramBoolean)
    {
      ˋ(new File(paramString), paramBoolean);
    }
    
    public static boolean ˎ(Context paramContext, String paramString)
    {
      return paramString.matches(Environment.getExternalStorageDirectory().getPath() + paramContext.getCacheDir() + "/" + "tf" + "\\d{1,19}.jpg");
    }
    
    /* Error */
    public static File ˏ(File paramFile1, File paramFile2)
    {
      // Byte code:
      //   0: aload_0
      //   1: invokevirtual 58	java/io/File:exists	()Z
      //   4: ifne +5 -> 9
      //   7: aconst_null
      //   8: areturn
      //   9: aconst_null
      //   10: astore 4
      //   12: aconst_null
      //   13: astore_2
      //   14: aconst_null
      //   15: astore 7
      //   17: aconst_null
      //   18: astore 6
      //   20: aload 6
      //   22: astore_3
      //   23: aload 7
      //   25: astore 5
      //   27: new 413	java/io/FileInputStream
      //   30: dup
      //   31: aload_0
      //   32: invokespecial 414	java/io/FileInputStream:<init>	(Ljava/io/File;)V
      //   35: invokevirtual 418	java/io/FileInputStream:getChannel	()Ljava/nio/channels/FileChannel;
      //   38: astore_0
      //   39: aload_0
      //   40: astore_2
      //   41: aload 6
      //   43: astore_3
      //   44: aload_0
      //   45: astore 4
      //   47: aload 7
      //   49: astore 5
      //   51: new 327	java/io/FileOutputStream
      //   54: dup
      //   55: aload_1
      //   56: invokespecial 382	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
      //   59: invokevirtual 419	java/io/FileOutputStream:getChannel	()Ljava/nio/channels/FileChannel;
      //   62: astore 6
      //   64: aload 6
      //   66: ifnull +31 -> 97
      //   69: aload_0
      //   70: ifnull +27 -> 97
      //   73: aload_0
      //   74: astore_2
      //   75: aload 6
      //   77: astore_3
      //   78: aload_0
      //   79: astore 4
      //   81: aload 6
      //   83: astore 5
      //   85: aload 6
      //   87: aload_0
      //   88: lconst_0
      //   89: aload_0
      //   90: invokevirtual 424	java/nio/channels/FileChannel:size	()J
      //   93: invokevirtual 428	java/nio/channels/FileChannel:transferFrom	(Ljava/nio/channels/ReadableByteChannel;JJ)J
      //   96: pop2
      //   97: aload_0
      //   98: ifnull +7 -> 105
      //   101: aload_0
      //   102: invokevirtual 429	java/nio/channels/FileChannel:close	()V
      //   105: aload 6
      //   107: ifnull +8 -> 115
      //   110: aload 6
      //   112: invokevirtual 429	java/nio/channels/FileChannel:close	()V
      //   115: aload_1
      //   116: areturn
      //   117: astore_0
      //   118: invokestatic 283	o/PY:ˊ	()Lo/PY;
      //   121: getstatic 336	java/util/logging/Level:SEVERE	Ljava/util/logging/Level;
      //   124: ldc_w 431
      //   127: invokevirtual 300	o/PY:log	(Ljava/util/logging/Level;Ljava/lang/String;)V
      //   130: aload_1
      //   131: areturn
      //   132: astore_0
      //   133: aload_2
      //   134: astore 4
      //   136: aload_3
      //   137: astore 5
      //   139: invokestatic 283	o/PY:ˊ	()Lo/PY;
      //   142: getstatic 336	java/util/logging/Level:SEVERE	Ljava/util/logging/Level;
      //   145: ldc_w 433
      //   148: invokevirtual 300	o/PY:log	(Ljava/util/logging/Level;Ljava/lang/String;)V
      //   151: aload_2
      //   152: ifnull +7 -> 159
      //   155: aload_2
      //   156: invokevirtual 429	java/nio/channels/FileChannel:close	()V
      //   159: aload_3
      //   160: ifnull +7 -> 167
      //   163: aload_3
      //   164: invokevirtual 429	java/nio/channels/FileChannel:close	()V
      //   167: aload_1
      //   168: areturn
      //   169: astore_0
      //   170: invokestatic 283	o/PY:ˊ	()Lo/PY;
      //   173: getstatic 336	java/util/logging/Level:SEVERE	Ljava/util/logging/Level;
      //   176: ldc_w 431
      //   179: invokevirtual 300	o/PY:log	(Ljava/util/logging/Level;Ljava/lang/String;)V
      //   182: aload_1
      //   183: areturn
      //   184: astore_0
      //   185: aload 4
      //   187: ifnull +8 -> 195
      //   190: aload 4
      //   192: invokevirtual 429	java/nio/channels/FileChannel:close	()V
      //   195: aload 5
      //   197: ifnull +8 -> 205
      //   200: aload 5
      //   202: invokevirtual 429	java/nio/channels/FileChannel:close	()V
      //   205: goto +16 -> 221
      //   208: astore_1
      //   209: invokestatic 283	o/PY:ˊ	()Lo/PY;
      //   212: getstatic 336	java/util/logging/Level:SEVERE	Ljava/util/logging/Level;
      //   215: ldc_w 431
      //   218: invokevirtual 300	o/PY:log	(Ljava/util/logging/Level;Ljava/lang/String;)V
      //   221: aload_0
      //   222: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	223	0	paramFile1	File
      //   0	223	1	paramFile2	File
      //   13	143	2	localFile	File
      //   22	142	3	localFileChannel1	java.nio.channels.FileChannel
      //   10	181	4	localObject1	Object
      //   25	176	5	localObject2	Object
      //   18	93	6	localFileChannel2	java.nio.channels.FileChannel
      //   15	33	7	localObject3	Object
      // Exception table:
      //   from	to	target	type
      //   101	105	117	java/io/IOException
      //   110	115	117	java/io/IOException
      //   27	39	132	java/io/IOException
      //   51	64	132	java/io/IOException
      //   85	97	132	java/io/IOException
      //   155	159	169	java/io/IOException
      //   163	167	169	java/io/IOException
      //   27	39	184	finally
      //   51	64	184	finally
      //   85	97	184	finally
      //   139	151	184	finally
      //   190	195	208	java/io/IOException
      //   200	205	208	java/io/IOException
    }
    
    public static File ˏ(File paramFile, String paramString)
    {
      paramString = new File(paramString);
      try
      {
        paramString.createNewFile();
        paramFile = ˏ(paramFile, paramString);
        return paramFile;
      }
      catch (IOException paramFile)
      {
        PY.ˊ().log(Level.SEVERE, "Something went wrong while trying to create a file");
      }
      return null;
    }
    
    public static File ˏ(String paramString1, String paramString2)
    {
      return ˏ(new File(paramString1), paramString2);
    }
    
    public static String ˏ()
    {
      String str = Environment.getExternalStorageDirectory().getAbsolutePath() + "/TFShareImages/";
      File localFile = new File(str);
      if (!localFile.exists()) {
        localFile.mkdirs();
      }
      return str + ˎ();
    }
    
    public static String ˏ(String paramString)
    {
      File localFile = new File(agn.ˊ);
      if ((!localFile.exists()) && (!localFile.mkdirs())) {
        PY.ˊ().log(Level.WARNING, String.format("folder could not be created: %s", new Object[] { localFile }));
      }
      paramString = String.format("photos_tmp_%s." + paramString, new Object[] { Long.valueOf(System.currentTimeMillis()) });
      return localFile.getPath() + File.separator + "._" + paramString;
    }
    
    private static void ˏ(File paramFile)
    {
      if (paramFile.isDirectory())
      {
        File[] arrayOfFile = paramFile.listFiles();
        int j = arrayOfFile.length;
        int i = 0;
        while (i < j)
        {
          ˏ(arrayOfFile[i]);
          i += 1;
        }
      }
      paramFile.delete();
    }
    
    public static boolean ˏ(Bitmap paramBitmap)
    {
      boolean bool;
      if ((κ.ˏ) && (paramBitmap != null) && (paramBitmap.getWidth() > 0) && (paramBitmap.getHeight() > 0)) {
        bool = true;
      } else {
        bool = false;
      }
      ν.ˎ(bool, "Wrong bitmap to measure");
      int i = paramBitmap.getWidth();
      int m = paramBitmap.getHeight();
      if ((i > 0) && (m > 0))
      {
        int j = paramBitmap.getPixel(5, 5);
        int n = paramBitmap.getPixel(i - 5, 5);
        int k = paramBitmap.getPixel(5, m - 5);
        m = paramBitmap.getPixel(i - 5, m - 5);
        i = 0;
        if (Color.alpha(j) == 0) {
          i = 0 + 1;
        }
        j = i;
        if (Color.alpha(n) == 0) {
          j = i + 1;
        }
        i = j;
        if (Color.alpha(k) == 0) {
          i = j + 1;
        }
        j = i;
        if (Color.alpha(m) == 0) {
          j = i + 1;
        }
        return j >= 3;
      }
      return false;
    }
    
    public static File ॱ(String paramString1, String paramString2)
    {
      String str = paramString1;
      if (paramString1.startsWith("file")) {
        str = paramString1.replace("file://", "");
      }
      paramString1 = paramString2;
      if (paramString2.startsWith("file")) {
        paramString1 = paramString2.replace("file://", "");
      }
      return ˏ(str, paramString1);
    }
    
    public static String ॱ()
    {
      Object localObject = Environment.getExternalStorageDirectory().getPath();
      File localFile = new File((String)localObject, "AudioRecorder");
      if (!localFile.exists()) {
        localFile.mkdirs();
      }
      localObject = new File((String)localObject, "record_temp.raw");
      if (((File)localObject).exists()) {
        ((File)localObject).delete();
      }
      long l = System.currentTimeMillis();
      return localFile.getAbsolutePath() + "/" + l + "record_temp.raw";
    }
    
    public static void ॱ(String paramString)
    {
      if (!TextUtils.isEmpty(paramString)) {
        ˎ(paramString, false);
      }
    }
    
    public static void ॱ(String paramString1, String paramString2, FragmentActivity paramFragmentActivity)
    {
      new ahn(paramString1, paramString2, paramFragmentActivity).execute(new Void[0]);
    }
    
    public static boolean ॱ(String paramString, boolean paramBoolean)
    {
      boolean bool;
      if ((κ.ˏ) && (!TextUtils.isEmpty(paramString))) {
        bool = true;
      } else {
        bool = false;
      }
      ν.ˎ(bool, "Media path is null or empty");
      String str = paramString;
      if (paramBoolean)
      {
        str = paramString;
        if (paramString.startsWith("file")) {
          str = paramString.replace("file://", "");
        }
      }
      return new File(str).exists();
    }
    
    public static boolean ॱˋ(String paramString)
    {
      return paramString.startsWith("file://");
    }
    
    public static void ᐝ()
    {
      File localFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/TextfreeRecordings/");
      if (localFile.exists()) {
        ˏ(localFile);
      }
    }
    
    public static boolean ᐝ(String paramString)
    {
      return paramString.startsWith("file");
    }
  }
  
  public static class ʽ
  {
    public ʽ() {}
    
    public static final String ˋ(String paramString)
    {
      if ((TextUtils.isEmpty(paramString)) || (TextUtils.isEmpty(paramString.trim()))) {
        return null;
      }
      String str = paramString.trim();
      if (!ahi.ˌ.ʽ(str))
      {
        paramString = str.split(" ");
        if (paramString.length <= 1)
        {
          str = str.substring(0, 1);
          paramString = "";
        }
        else
        {
          str = paramString[0].substring(0, 1);
          paramString = paramString[(paramString.length - 1)].substring(0, 1);
        }
      }
      else
      {
        return null;
      }
      return (str + paramString).toUpperCase(Locale.US);
    }
    
    public static String ॱ(String paramString)
    {
      if (TextUtils.isEmpty(paramString)) {
        return "";
      }
      if (ahi.ˌ.ʽ(paramString))
      {
        localObject = ahi.ˏ.ˋ(paramString);
        if (!TextUtils.isEmpty((CharSequence)localObject)) {
          return localObject;
        }
        return paramString;
      }
      Object localObject = TextUtils.split(paramString, " ");
      if ((localObject != null) && (localObject.length > 1)) {
        return localObject[0];
      }
      return paramString;
    }
  }
  
  public static class ʾ
  {
    public ʾ() {}
    
    public static String ˊ(int paramInt)
    {
      switch (paramInt)
      {
      default: 
        break;
      case 1: 
        return TFApplication.ˏॱ().getApplicationContext().getString(2131231851);
      case 2: 
        return TFApplication.ˏॱ().getApplicationContext().getString(2131232007);
      case 3: 
        return TFApplication.ˏॱ().getApplicationContext().getString(2131232004);
      case 4: 
        return TFApplication.ˏॱ().getApplicationContext().getString(2131231802);
      }
      return null;
    }
    
    public static void ॱ(Context paramContext)
    {
      Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + TFApplication.ˏॱ().getPackageName()));
      localIntent.addFlags(268435456);
      try
      {
        paramContext.startActivity(localIntent);
        return;
      }
      catch (ActivityNotFoundException paramContext)
      {
        PY.ˊ().ˎ(Level.SEVERE, paramContext);
        throw paramContext;
      }
    }
  }
  
  public static class ʿ
  {
    public ʿ() {}
    
    public static boolean ʻ(String paramString)
    {
      return (TextUtils.isEmpty(paramString)) || (paramString.toUpperCase().equals("RESTRICTED")) || (paramString.toUpperCase().equals("UNKNOWN NUMBER"));
    }
    
    public static String ʼ(String paramString)
    {
      return ˎ(paramString, false);
    }
    
    public static boolean ʽ(String paramString)
    {
      boolean bool;
      if ((κ.ˏ) && (!TextUtils.isEmpty(paramString))) {
        bool = true;
      } else {
        bool = false;
      }
      ν.ˎ(bool, "Calling number is empty or null");
      paramString = ˏ(paramString, true);
      String str1 = TFService.getTFInstance().getProfile().ˎˎ();
      String str2 = TFService.getTFInstance().getProfile().ʿ();
      return ((!TextUtils.isEmpty(str2)) && (ˎ(paramString, str2))) || ((!TextUtils.isEmpty(str1)) && (ˎ(paramString, str1)));
    }
    
    public static String ˊ(Integer paramInteger, String paramString)
    {
      return ˋ(paramInteger, paramString, true, false);
    }
    
    public static String ˊ(String paramString)
    {
      paramString = ˏ(paramString, true);
      if (paramString.length() >= 10)
      {
        int i;
        if (paramString.length() == 11) {
          i = 1;
        } else {
          i = 0;
        }
        return paramString.substring(i, paramString.length() - 7);
      }
      return "";
    }
    
    private static wo.ˋ ˊ(String paramString1, String paramString2)
    {
      try
      {
        wo.ˋ localˋ = PhoneNumberUtil.getInstance().parse(paramString1, paramString2);
        return localˋ;
      }
      catch (NumberParseException localNumberParseException)
      {
        PY.ˊ().log(Level.WARNING, "Number " + paramString1 + " countryCode = " + paramString2 + " could not be parsed using libphonenumber library. Message: " + localNumberParseException.getMessage());
      }
      return null;
    }
    
    public static String ˊॱ(String paramString)
    {
      if (!TextUtils.isEmpty(paramString))
      {
        String str = paramString;
        if (paramString.startsWith("tel:")) {
          str = paramString.replace("tel:", "");
        }
        if (ahi.ˌ.ʽ(str)) {
          return ˋ(str);
        }
      }
      return null;
    }
    
    public static String ˋ(Integer paramInteger, String paramString, boolean paramBoolean1, boolean paramBoolean2)
    {
      Integer localInteger = paramInteger;
      if (paramInteger == null) {
        localInteger = Integer.valueOf(7);
      }
      paramInteger = PingerApplication.ˋ();
      switch (localInteger.intValue())
      {
      default: 
        break;
      case 1: 
        paramInteger = paramInteger.getString(2131231471);
        break;
      case 2: 
        paramInteger = paramInteger.getString(2131231475);
        break;
      case 3: 
        paramInteger = paramInteger.getString(2131231483);
        break;
      case 4: 
        paramInteger = paramInteger.getString(2131231470);
        break;
      case 5: 
        paramInteger = paramInteger.getString(2131231469);
        break;
      case 6: 
        paramInteger = paramInteger.getString(2131231478);
        break;
      case 7: 
        paramInteger = paramInteger.getString(2131231476);
        break;
      case 8: 
        paramInteger = paramInteger.getString(2131231465);
        break;
      case 9: 
        paramInteger = paramInteger.getString(2131231466);
        break;
      case 10: 
        paramInteger = paramInteger.getString(2131231473);
        break;
      case 11: 
        paramInteger = paramInteger.getString(2131231472);
        break;
      case 12: 
        paramInteger = paramInteger.getString(2131231473);
        break;
      case 13: 
        paramInteger = paramInteger.getString(2131231477);
        break;
      case 14: 
        paramInteger = paramInteger.getString(2131231480);
        break;
      case 15: 
        paramInteger = paramInteger.getString(2131231481);
        break;
      case 16: 
        paramInteger = paramInteger.getString(2131231482);
        break;
      case 17: 
        paramInteger = paramInteger.getString(2131231484);
        break;
      case 18: 
        paramInteger = paramInteger.getString(2131231485);
        break;
      case 19: 
        paramInteger = paramInteger.getString(2131231464);
        break;
      case 20: 
        paramInteger = paramInteger.getString(2131231474);
        break;
      }
      if (paramString != null) {
        paramInteger = paramString;
      } else if (paramBoolean2) {
        paramInteger = paramInteger.getString(2131231479);
      } else {
        paramInteger = paramInteger.getString(2131231468);
      }
      paramString = paramInteger;
      if (paramBoolean1) {
        paramString = paramInteger.toLowerCase();
      }
      return paramString;
    }
    
    @Deprecated
    public static String ˋ(String paramString)
    {
      boolean bool;
      if ((κ.ˏ) && (!TextUtils.isEmpty(Preferences.ͺ.ˋ.ʻ()))) {
        bool = true;
      } else {
        bool = false;
      }
      ν.ˎ(bool, "User has no account country code");
      PhoneNumberUtil localPhoneNumberUtil = PhoneNumberUtil.getInstance();
      wo.ˋ localˋ = ˊ(paramString, Preferences.ͺ.ˋ.ʻ());
      if (localˋ == null) {
        return paramString;
      }
      return localPhoneNumberUtil.format(localˋ, PhoneNumberUtil.ˊ.E164);
    }
    
    public static String ˎ(Context paramContext, String paramString)
    {
      if (paramContext.getResources().getBoolean(2131558406))
      {
        if (paramContext.getResources().getBoolean(2131558407)) {
          paramContext = TFService.getTFInstance().getProfile().ˎˎ();
        } else {
          paramContext = TFService.getTFInstance().getProfile().ʿ();
        }
        return ˏ(ˊ(paramContext), paramString, (byte)1);
      }
      return ॱ(paramString, (byte)1);
    }
    
    public static String ˎ(Integer paramInteger, String paramString, boolean paramBoolean)
    {
      return ˋ(paramInteger, paramString, true, paramBoolean);
    }
    
    public static String ˎ(String paramString)
    {
      boolean bool;
      if ((κ.ˏ) && (!TextUtils.isEmpty(paramString))) {
        bool = true;
      } else {
        bool = false;
      }
      ν.ˎ(bool, "Address cannot be empty");
      if (!ahi.ˌ.ʽ(paramString)) {
        return paramString;
      }
      if (ahi.ˌ.ॱˋ(paramString)) {
        return ॱ(paramString, (byte)1);
      }
      return ˋ(paramString);
    }
    
    public static String ˎ(String paramString, boolean paramBoolean)
    {
      return ˏ(paramString, paramBoolean, true);
    }
    
    public static boolean ˎ(String paramString1, String paramString2)
    {
      boolean bool = ˏ(paramString1, paramString2, 8);
      if (bool != ˏ(paramString1, paramString2, 10))
      {
        paramString1 = PhoneNumberUtil.getInstance().isNumberMatch(paramString1, paramString2);
        return (paramString1 == PhoneNumberUtil.if.EXACT_MATCH) || (paramString1 == PhoneNumberUtil.if.NSN_MATCH) || (paramString1 == PhoneNumberUtil.if.SHORT_NSN_MATCH);
      }
      return bool;
    }
    
    public static String ˏ(String paramString)
    {
      return ahi.AuX.ˋ(paramString, 8);
    }
    
    public static String ˏ(String paramString1, String paramString2, byte paramByte)
    {
      if ((paramString2 != null) && (paramByte == 1))
      {
        String str = ˏ(paramString2, true);
        if (!TextUtils.isEmpty(str))
        {
          paramString2 = str;
          if (str.length() == 7) {
            paramString2 = paramString1 + str;
          }
          return ॱ(paramString2, paramByte);
        }
      }
      return ॱ(paramString2, paramByte);
    }
    
    public static String ˏ(String paramString, boolean paramBoolean)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      if (!TextUtils.isEmpty(paramString))
      {
        int i = 0;
        while (i < paramString.length())
        {
          char c = paramString.charAt(i);
          if ((Character.isDigit(c)) || ((!paramBoolean) && (localStringBuilder.length() == 0) && (c == '+'))) {
            localStringBuilder.append(c);
          }
          i += 1;
        }
      }
      return localStringBuilder.toString();
    }
    
    public static String ˏ(String paramString, boolean paramBoolean1, boolean paramBoolean2)
    {
      if (TextUtils.isEmpty(paramString)) {
        return paramString;
      }
      if (ahi.ˌ.ʽ(paramString)) {
        return ˏ(paramString, paramBoolean1);
      }
      if (paramBoolean2) {
        return paramString.trim().toLowerCase();
      }
      return paramString;
    }
    
    public static String ˏ(boolean paramBoolean)
    {
      String str1 = ((TelephonyManager)TFApplication.ˋ().getSystemService("phone")).getLine1Number();
      if ((TextUtils.isEmpty(str1)) || (!paramBoolean)) {
        str1 = ahi.ˏ.ˋ(str1);
      }
      String str2;
      if (!ahi.ˌ.ͺ(str1))
      {
        str2 = str1;
        if (Build.VERSION.SDK_INT >= 22)
        {
          List localList = SubscriptionManager.from(TFApplication.ˋ()).getActiveSubscriptionInfoList();
          str2 = str1;
          if (localList != null)
          {
            str2 = str1;
            if (localList.size() > 1) {
              str2 = null;
            }
          }
        }
      }
      else
      {
        str2 = null;
      }
      if (str2 != null) {
        return str2.trim();
      }
      return null;
    }
    
    public static boolean ˏ(String paramString1, String paramString2)
    {
      paramString1 = ahi.AuX.ˏ(paramString1);
      paramString2 = ahi.AuX.ˏ(paramString2);
      if ((paramString1 == null) || (paramString2 == null)) {
        return false;
      }
      return ॱ(ˎ(paramString1, false), ˎ(paramString2, false));
    }
    
    private static boolean ˏ(String paramString1, String paramString2, int paramInt)
    {
      int i;
      if (paramString1.startsWith("00"))
      {
        i = 1;
        paramString1 = paramString1.substring(2);
      }
      else if (paramString1.startsWith("+"))
      {
        i = 1;
        paramString1 = paramString1.substring(1);
      }
      else
      {
        i = 0;
      }
      int j;
      if (paramString2.startsWith("00"))
      {
        j = 1;
        paramString2 = paramString2.substring(2);
      }
      else if (paramString2.startsWith("+"))
      {
        j = 1;
        paramString2 = paramString2.substring(1);
      }
      else
      {
        j = 0;
      }
      String str2;
      String str1;
      if (i != 0)
      {
        str2 = paramString1;
        str1 = paramString2;
        if (j != 0) {}
      }
      else
      {
        str2 = paramString1;
        str1 = paramString2;
        if (paramString1.length() >= paramInt)
        {
          str2 = paramString1;
          str1 = paramString2;
          if (paramString2.length() >= paramInt)
          {
            str2 = paramString1.substring(paramString1.length() - paramInt);
            str1 = paramString2.substring(paramString2.length() - paramInt);
          }
        }
      }
      return str2.equals(str1);
    }
    
    public static String ॱ()
    {
      if ("ES".equalsIgnoreCase(Preferences.ͺ.ˋ.ʻ())) {
        return "+34602245000";
      }
      return "+14107746437";
    }
    
    public static String ॱ(String paramString)
    {
      boolean bool;
      if ((κ.ˏ) && (!TextUtils.isEmpty(paramString))) {
        bool = true;
      } else {
        bool = false;
      }
      ν.ˎ(bool, "Don't call this method with an empty string");
      if ((!TextUtils.isEmpty(paramString)) && (!paramString.startsWith("+"))) {
        return "+" + paramString;
      }
      return paramString;
    }
    
    public static String ॱ(String paramString, byte paramByte)
    {
      if ((paramString != null) && (paramByte == 1))
      {
        String str = ˏ(paramString, false);
        if (!TextUtils.isEmpty(str))
        {
          if (!str.startsWith("+"))
          {
            if ((str.startsWith("00")) && (str.length() > 9)) {
              return "+" + str.substring(2);
            }
            if (str.length() > 10) {
              return "+" + str;
            }
          }
          return str;
        }
      }
      return paramString;
    }
    
    public static boolean ॱ(String paramString1, String paramString2)
    {
      if ((ahi.ˌ.ʽ(paramString1)) && (ahi.ˌ.ʽ(paramString2))) {
        return ˎ(paramString1, paramString2);
      }
      return paramString1.equals(paramString2);
    }
    
    public static String ᐝ(String paramString)
    {
      if (!TextUtils.isEmpty(paramString))
      {
        String str = paramString;
        if (paramString.startsWith("tel:")) {
          str = paramString.replace("tel:", "");
        }
        return str;
      }
      return null;
    }
  }
  
  public static final class ˈ
  {
    public ˈ() {}
    
    private static String ˊ(String paramString)
    {
      boolean bool;
      if (!TextUtils.isEmpty(paramString)) {
        bool = true;
      } else {
        bool = false;
      }
      ν.ˎ(bool, "VCard String to be parsed is empty");
      StringBuilder localStringBuilder = new StringBuilder();
      ArrayList localArrayList = new ArrayList();
      if (paramString.split("\n").length > 3)
      {
        paramString = paramString.split("\n");
        int j = paramString.length;
        int i = 0;
        while (i < j)
        {
          Object localObject = paramString[i].split(":");
          if (localObject.length > 1)
          {
            String str = localObject[0];
            localObject = ahi.ʿ.ˋ(localObject[1]);
            if (str.startsWith("TEL"))
            {
              str = ॱ(str) + " - " + ahi.ʿ.ˋ((String)localObject);
              if (!localArrayList.contains(str)) {
                localArrayList.add(str);
              }
            }
            else if (str.startsWith("FN"))
            {
              localStringBuilder.append("--- ").append((String)localObject).append("---").append("\n");
            }
          }
          i += 1;
        }
        paramString = localArrayList.iterator();
        while (paramString.hasNext()) {
          localStringBuilder.append((String)paramString.next()).append("\n");
        }
      }
      return localStringBuilder.toString();
    }
    
    private static String ˋ(String paramString)
    {
      if ((paramString == null) || (paramString.length() == 0)) {
        return "";
      }
      char c = paramString.charAt(0);
      if (Character.isUpperCase(c)) {
        return paramString;
      }
      return Character.toUpperCase(c) + paramString.substring(1);
    }
    
    public static String ˎ(Context paramContext, String paramString)
    {
      boolean bool;
      if ((κ.ˏ) && (!TextUtils.isEmpty(paramString))) {
        bool = true;
      } else {
        bool = false;
      }
      ν.ˎ(bool, "VCard path name is empty");
      Object localObject3 = null;
      Object localObject2 = null;
      FileInputStream localFileInputStream2 = null;
      byte[] arrayOfByte = null;
      Object localObject4 = null;
      AssetFileDescriptor localAssetFileDescriptor = null;
      FileInputStream localFileInputStream1 = localFileInputStream2;
      Object localObject1 = arrayOfByte;
      for (;;)
      {
        try
        {
          if (paramString.startsWith("content:"))
          {
            localFileInputStream1 = localFileInputStream2;
            localObject1 = arrayOfByte;
            localAssetFileDescriptor = paramContext.getContentResolver().openAssetFileDescriptor(Uri.parse(paramString), "r");
            paramContext = localObject3;
            paramString = localObject4;
            if (localAssetFileDescriptor != null)
            {
              localFileInputStream1 = localFileInputStream2;
              localObject1 = arrayOfByte;
              localFileInputStream2 = localAssetFileDescriptor.createInputStream();
              localFileInputStream1 = localFileInputStream2;
              localObject1 = localFileInputStream2;
              arrayOfByte = new byte[(int)localAssetFileDescriptor.getDeclaredLength()];
              localFileInputStream1 = localFileInputStream2;
              localObject1 = localFileInputStream2;
              paramContext = localObject3;
              paramString = localFileInputStream2;
              if (localFileInputStream2.read(arrayOfByte) != -1)
              {
                localFileInputStream1 = localFileInputStream2;
                localObject1 = localFileInputStream2;
                paramContext = new String(arrayOfByte);
                paramString = localFileInputStream2;
              }
            }
          }
          else
          {
            localFileInputStream1 = localFileInputStream2;
            localObject1 = arrayOfByte;
            paramContext = ˏ(paramString.replace("file://", ""));
            paramString = localAssetFileDescriptor;
            if (paramString != null) {}
            try
            {
              paramString.close();
            }
            catch (IOException paramString)
            {
              PY.ˊ().log(Level.SEVERE, "Couldn't close the file streams.");
            }
            return ˊ(paramContext);
          }
        }
        catch (IOException paramContext)
        {
          localObject1 = localFileInputStream1;
          PY.ˊ().info("Error when reading contents from vCard");
          if (localFileInputStream1 != null) {}
          try
          {
            localFileInputStream1.close();
            paramContext = localObject2;
          }
          catch (IOException paramContext)
          {
            PY.ˊ().log(Level.SEVERE, "Couldn't close the file streams.");
            paramContext = localObject2;
          }
        }
        finally
        {
          if (localObject1 != null) {}
          try
          {
            ((FileInputStream)localObject1).close();
          }
          catch (IOException paramString)
          {
            PY.ˊ().log(Level.SEVERE, "Couldn't close the file streams.");
          }
        }
      }
    }
    
    private static String ˏ(String paramString)
    {
      boolean bool;
      if ((κ.ˏ) && (!TextUtils.isEmpty(paramString))) {
        bool = true;
      } else {
        bool = false;
      }
      ν.ˎ(bool, "VCard path name is empty");
      Object localObject2 = new File(paramString);
      Object localObject1 = null;
      paramString = null;
      ν.ˎ(((File)localObject2).exists(), "VCard File was not found");
      if (((File)localObject2).exists())
      {
        if (((File)localObject2).length() == 0L) {
          bool = true;
        } else {
          bool = false;
        }
        ν.ˎ(bool, "File is empty");
        Object localObject3 = new StringBuilder((int)((File)localObject2).length());
        try
        {
          localObject2 = new Scanner((File)localObject2);
          paramString = (String)localObject2;
          localObject1 = localObject2;
          String str2 = System.getProperty("line.separator");
          for (;;)
          {
            paramString = (String)localObject2;
            localObject1 = localObject2;
            if (!((Scanner)localObject2).hasNextLine()) {
              break;
            }
            paramString = (String)localObject2;
            localObject1 = localObject2;
            ((StringBuilder)localObject3).append(((Scanner)localObject2).nextLine()).append(str2);
          }
          paramString = (String)localObject2;
          localObject1 = localObject2;
          localObject3 = ((StringBuilder)localObject3).toString();
          if (localObject2 != null) {
            ((Scanner)localObject2).close();
          }
          return localObject3;
        }
        catch (IOException localIOException)
        {
          str1 = paramString;
          PY.ˊ().info("Error when reading contents from vCard");
          return null;
        }
        finally
        {
          String str1;
          if (str1 != null) {
            str1.close();
          }
        }
      }
      return null;
    }
    
    private static String ॱ(String paramString)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      paramString = paramString.split(";");
      if (paramString.length > 1)
      {
        int i = 1;
        while (i < paramString.length)
        {
          if (paramString[i].equalsIgnoreCase("CELL")) {
            localStringBuilder.append(ˋ("Mobile"));
          } else if (paramString[i].equalsIgnoreCase("VOICE")) {
            localStringBuilder.append(ˋ("Other"));
          } else {
            localStringBuilder.append(ˋ(paramString[i].toLowerCase())).append(" ");
          }
          i += 1;
        }
      }
      return localStringBuilder.toString();
    }
  }
  
  public static class ˉ
  {
    public ˉ() {}
    
    public static Intent ˋ(String paramString)
    {
      return ˏ(new File(paramString));
    }
    
    public static Intent ˎ(Intent paramIntent)
    {
      ArrayList localArrayList = new ArrayList();
      Object localObject = TFApplication.ˋ().getPackageManager().queryIntentActivities(paramIntent, 0);
      if (!((List)localObject).isEmpty())
      {
        localObject = ((List)localObject).iterator();
        while (((Iterator)localObject).hasNext())
        {
          ResolveInfo localResolveInfo = (ResolveInfo)((Iterator)localObject).next();
          if (!localResolveInfo.activityInfo.applicationInfo.packageName.equals(TFApplication.ˋ().getPackageName()))
          {
            Intent localIntent = (Intent)paramIntent.clone();
            localIntent.setPackage(localResolveInfo.activityInfo.packageName);
            localIntent.setClassName(localResolveInfo.activityInfo.packageName, localResolveInfo.activityInfo.name);
            localArrayList.add(localIntent);
          }
        }
        paramIntent = Intent.createChooser((Intent)localArrayList.remove(localArrayList.size() - 1), TFApplication.ˋ().getResources().getText(2131231602));
        paramIntent.putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[])localArrayList.toArray(new Parcelable[0]));
        return paramIntent;
      }
      return Intent.createChooser(paramIntent, TFApplication.ˋ().getResources().getText(2131231602));
    }
    
    public static Intent ˏ(File paramFile)
    {
      Intent localIntent = ॱ();
      localIntent.setType("image/*");
      localIntent.putExtra("android.intent.extra.STREAM", Uri.fromFile(paramFile));
      return localIntent;
    }
    
    public static Intent ˏ(String paramString)
    {
      return ॱ("", paramString);
    }
    
    private static Intent ॱ()
    {
      return new Intent("android.intent.action.SEND");
    }
    
    public static Intent ॱ(String paramString1, String paramString2)
    {
      Intent localIntent = ॱ();
      localIntent.setType("text/plain");
      localIntent.putExtra("android.intent.extra.SUBJECT", paramString1);
      localIntent.putExtra("android.intent.extra.TEXT", paramString2);
      return localIntent;
    }
  }
  
  public static class ˊ
  {
    public ˊ() {}
    
    public static String ˎ(StackTraceElement[] paramArrayOfStackTraceElement)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      int j = paramArrayOfStackTraceElement.length;
      int i = 0;
      while (i < j)
      {
        localStringBuilder.append(paramArrayOfStackTraceElement[i].toString()).append("\n");
        i += 1;
      }
      return localStringBuilder.toString();
    }
  }
  
  public static final class ˋ
  {
    public ˋ() {}
    
    public static int ॱ(Context paramContext)
    {
      return ॱ(paramContext, new int[] { 2130772072 });
    }
    
    private static int ॱ(Context paramContext, int[] paramArrayOfInt)
    {
      return paramContext.obtainStyledAttributes(paramArrayOfInt).getResourceId(0, 0);
    }
  }
  
  public static class ˌ
  {
    public static final Pattern ˎ = Pattern.compile("^[a-zA-Z]{1}[a-zA-Z0-9]{2}[ ]{0,1}[a-zA-Z0-9]{3}$");
    
    public ˌ() {}
    
    public static boolean ʻ(String paramString)
    {
      if (TextUtils.isEmpty(paramString)) {
        return false;
      }
      int i = 0;
      while (i < paramString.length())
      {
        if (!Character.isDigit(paramString.charAt(i))) {
          return false;
        }
        i += 1;
      }
      return true;
    }
    
    public static boolean ʼ(String paramString)
    {
      return (!TextUtils.isEmpty(paramString)) && (Patterns.EMAIL_ADDRESS.matcher(paramString).matches());
    }
    
    public static boolean ʽ(String paramString)
    {
      if ((TextUtils.isEmpty(paramString)) || (TextUtils.isEmpty(paramString.trim()))) {
        return false;
      }
      paramString = paramString.trim();
      int j;
      if (paramString.startsWith("+")) {
        j = 1;
      } else {
        j = 0;
      }
      int i = j;
      if (j == 0) {
        if (paramString.startsWith("(+")) {
          i = 2;
        } else {
          i = 0;
        }
      }
      boolean bool = true;
      while ((bool) && (i < paramString.length()))
      {
        j = paramString.charAt(i);
        if (((j < 48) || (j > 57)) && (j != 40) && (j != 41) && (j != 32) && (j != 45)) {
          bool = false;
        } else {
          i += 1;
        }
      }
      return bool;
    }
    
    public static boolean ˊ(FormValidationEditText paramFormValidationEditText)
    {
      paramFormValidationEditText.setErrorTextViewVisibility(false);
      String str = paramFormValidationEditText.ॱ();
      int i = Integer.MIN_VALUE;
      if (!ʼ(str)) {
        i = 2131231208;
      }
      if (i != Integer.MIN_VALUE)
      {
        paramFormValidationEditText.setErrorText(ˏ().getString(i));
        paramFormValidationEditText.setErrorTextViewVisibility(true);
        return false;
      }
      paramFormValidationEditText.setErrorText("");
      paramFormValidationEditText.setErrorTextViewVisibility(false);
      return true;
    }
    
    public static boolean ˊ(String paramString)
    {
      int i = paramString.toLowerCase().charAt(0);
      return (i >= 97) && (i <= 122);
    }
    
    public static int ˊॱ(String paramString)
    {
      if (!ʼ(paramString)) {
        return 65334;
      }
      return 0;
    }
    
    public static boolean ˋ(FormValidationEditText paramFormValidationEditText)
    {
      paramFormValidationEditText.setErrorTextViewVisibility(false);
      String str = paramFormValidationEditText.ॱ();
      int i = Integer.MIN_VALUE;
      int j = ᐝ(str);
      if (j == 65338) {
        i = 2131231216;
      } else if (j == 65337) {
        i = 2131231247;
      }
      if (i != Integer.MIN_VALUE)
      {
        paramFormValidationEditText.setErrorText(ˏ().getString(i));
        paramFormValidationEditText.setErrorTextViewVisibility(true);
        return false;
      }
      paramFormValidationEditText.setErrorText("");
      return true;
    }
    
    public static boolean ˋ(String paramString)
    {
      wu localWu = wu.ॱ();
      return (!TextUtils.isEmpty(paramString)) && (localWu.ˊ(paramString, Preferences.ͺ.ˋ.ʻ()));
    }
    
    public static boolean ˋॱ(String paramString)
    {
      int i = 0;
      while (i < paramString.length())
      {
        int j = paramString.charAt(i);
        if (((j < 97) || (j > 122)) && ((j < 65) || (j > 90)) && (j != 44) && (j != 45) && (j != 32)) {
          return true;
        }
        i += 1;
      }
      return false;
    }
    
    public static boolean ˎ(Context paramContext)
    {
      return (paramContext.getResources().getConfiguration().screenLayout & 0xF) >= 3;
    }
    
    public static boolean ˎ(String paramString)
    {
      return (!TextUtils.isEmpty(paramString)) && (Patterns.EMAIL_ADDRESS.matcher(paramString).matches());
    }
    
    public static int ˏ(String paramString, boolean paramBoolean)
    {
      if ((TextUtils.isEmpty(paramString)) || ((!paramBoolean) && (paramString.length() < 6))) {
        return 65338;
      }
      if (paramString.contains(" ")) {
        return 65337;
      }
      return 0;
    }
    
    public static Context ˏ()
    {
      return TFApplication.ˋ().getApplicationContext();
    }
    
    public static String ˏ(Context paramContext, int paramInt)
    {
      switch (paramInt)
      {
      default: 
        break;
      case -198: 
        return paramContext.getString(2131231685);
      case -199: 
        return paramContext.getString(2131231686);
      case -200: 
        return paramContext.getString(2131230835);
      case -202: 
        return paramContext.getString(2131231684);
      }
      return "";
    }
    
    public static String ˏ(FormValidationEditText paramFormValidationEditText)
    {
      paramFormValidationEditText.setErrorTextViewVisibility(false);
      String str = paramFormValidationEditText.ॱ();
      if (ˏ(paramFormValidationEditText.ˎ()))
      {
        paramFormValidationEditText.setErrorText(ˏ().getString(2131231236));
        paramFormValidationEditText.setErrorTextViewVisibility(true);
        return "Username Missing";
      }
      if (!ˊ(str))
      {
        paramFormValidationEditText.setErrorTextViewVisibility(true);
        paramFormValidationEditText.setErrorText(ˏ().getString(2131231250));
        return "Username Not Starting With Letter";
      }
      if (str.length() < 3)
      {
        paramFormValidationEditText.setErrorTextViewVisibility(true);
        paramFormValidationEditText.setErrorText(ˏ().getString(2131231218));
        return "Username Too Short";
      }
      if (str.length() > 12)
      {
        paramFormValidationEditText.setErrorTextViewVisibility(true);
        paramFormValidationEditText.setErrorText(ˏ().getString(2131231218));
        return "Username Too Long";
      }
      if (str.contains(" "))
      {
        paramFormValidationEditText.setErrorTextViewVisibility(true);
        paramFormValidationEditText.setErrorText(ˏ().getString(2131231249));
        return "Username Contains Spaces";
      }
      str = ॱ(str);
      if (str != null)
      {
        paramFormValidationEditText.setErrorTextViewVisibility(true);
        paramFormValidationEditText.setErrorText(String.format(ˏ().getString(2131231249), new Object[] { str }));
        return "Username Contains Bad Characters";
      }
      paramFormValidationEditText.setErrorText("");
      return null;
    }
    
    public static boolean ˏ(TextView paramTextView)
    {
      return ahi.AuX.ˎ(paramTextView).length() == 0;
    }
    
    public static boolean ˏ(String paramString)
    {
      return (paramString.equals(Op.ˏ)) || (paramString.equals(Op.ˎ));
    }
    
    public static boolean ͺ(String paramString)
    {
      if (!TextUtils.isEmpty(paramString))
      {
        paramString = ahi.ʿ.ˏ(paramString, true);
        try
        {
          int i = Integer.parseInt(paramString);
          return i == 0;
        }
        catch (NumberFormatException paramString)
        {
          return false;
        }
      }
      return false;
    }
    
    public static String ॱ(String paramString)
    {
      Object localObject1 = null;
      int i = 0;
      while ((localObject1 == null) && (i < paramString.length()))
      {
        char c = paramString.charAt(i);
        Object localObject2;
        if (c >= 'a')
        {
          localObject2 = localObject1;
          if (c <= 'z') {}
        }
        else if (c >= 'A')
        {
          localObject2 = localObject1;
          if (c <= 'Z') {}
        }
        else if (c >= '0')
        {
          localObject2 = localObject1;
          if (c <= '9') {}
        }
        else
        {
          localObject2 = localObject1;
          if (c != '_')
          {
            localObject2 = localObject1;
            if (c != '-')
            {
              localObject2 = localObject1;
              if (c != '.') {
                localObject2 = "" + c;
              }
            }
          }
        }
        i += 1;
        localObject1 = localObject2;
      }
      return localObject1;
    }
    
    public static boolean ॱ(Context paramContext)
    {
      return paramContext.getResources().getConfiguration().orientation == 2;
    }
    
    public static boolean ॱ(FormValidationEditText paramFormValidationEditText)
    {
      paramFormValidationEditText.setErrorTextViewVisibility(false);
      String str2 = paramFormValidationEditText.ॱ();
      int i = Integer.MIN_VALUE;
      String str1 = str2;
      if (!TextUtils.isEmpty(str2)) {
        str1 = str2.trim();
      }
      if ((TextUtils.isEmpty(str1)) || (ˋॱ(str1))) {
        i = 2131231213;
      }
      if (i != Integer.MIN_VALUE)
      {
        paramFormValidationEditText.setErrorText(ˏ().getString(i));
        paramFormValidationEditText.setErrorTextViewVisibility(true);
        return false;
      }
      paramFormValidationEditText.setErrorText("");
      return true;
    }
    
    public static boolean ॱˊ(String paramString)
    {
      return (paramString != null) && (paramString.length() == 3) && (ʻ(paramString));
    }
    
    public static boolean ॱˋ(String paramString)
    {
      return ((paramString.startsWith("+")) || (paramString.startsWith("00"))) && (paramString.length() > 9);
    }
    
    public static int ᐝ(String paramString)
    {
      return ˏ(paramString, false);
    }
  }
  
  public static class ˎ
  {
    private static int ˊ = 0;
    private static int ˋ;
    private static int ˎ = 1;
    public static final Map<String, String> ˏ;
    private static final byte[] ॱ = { 80, -127, 57, -20, 9, -3, 17, -17, 6 };
    
    static
    {
      ˋ = 206;
      ˏ = new HashMap();
      ˏ.put("username", "*");
      ˏ.put("password", "*");
      ˏ.put(ˊ(1, 1, (byte)-1).intern(), "*");
      ˏ.put("token", "*");
      ˏ.put("messageTxt", "*");
      ˏ.put(ˊ(0, 0, (byte)1).intern(), "*");
      ˏ.put("messageText", "*");
      ˏ.put("senderName", "*");
    }
    
    public ˎ() {}
    
    private static String ˊ(int paramInt1, int paramInt2, byte paramByte)
    {
      break label21;
      int i;
      int j;
      paramByte = i + -j + 2;
      label14:
      label21:
      byte[] arrayOfByte1;
      byte[] arrayOfByte2;
      int n;
      for (;;)
      {
        int k = 92;
        break label75;
        i = 0;
        k = 0;
        paramByte += 4;
        arrayOfByte1 = ॱ;
        j = 116 - paramInt2 * 4;
        paramInt1 = 4 - paramInt1;
        arrayOfByte2 = new byte[paramInt1];
        n = paramInt1 - 1;
        int m;
        if (arrayOfByte1 != null) {
          m = paramByte;
        }
        label75:
        label129:
        do
        {
          k = 8;
          i = paramInt1;
          j = paramByte;
          m = paramInt2;
          switch (k)
          {
          default: 
            break label14;
            i = n;
            j = paramByte;
            paramInt1 = k;
            paramInt2 = paramByte;
            break;
          case 92: 
            do
            {
              return new String(arrayOfByte2, 0);
              paramInt2 = m + 1;
              arrayOfByte2[i] = ((byte)j);
            } while (i == n);
            paramInt1 = i;
            break label193;
            i = ˊ + 53;
            ˎ = i % 128;
          }
        } while (i % 2 == 0);
      }
      for (;;)
      {
        label193:
        i = j;
        paramInt1 += 1;
        j = arrayOfByte1[paramInt2];
        break;
        paramInt2 += 1;
        arrayOfByte2[paramInt1] = ((byte)paramByte);
        if (paramInt1 == n) {
          break label129;
        }
        j = paramByte;
      }
    }
    
    private static JSONArray ˊ(JSONArray paramJSONArray)
    {
      if ((paramJSONArray != null) && (paramJSONArray.length() > 0))
      {
        JSONArray localJSONArray = new JSONArray();
        int i = 0;
        while (i < paramJSONArray.length())
        {
          Object localObject = paramJSONArray.get(i);
          if ((localObject instanceof JSONArray)) {
            localJSONArray.put(i, ˊ((JSONArray)localObject));
          } else if ((localObject instanceof JSONObject)) {
            localJSONArray.put(i, ˎ((JSONObject)localObject));
          } else {
            localJSONArray.put(i, localObject);
          }
          i += 1;
        }
        return localJSONArray;
      }
      return paramJSONArray;
    }
    
    public static JSONObject ˎ(JSONObject paramJSONObject)
    {
      if (paramJSONObject != null)
      {
        JSONObject localJSONObject = new JSONObject();
        Iterator localIterator = paramJSONObject.keys();
        while (localIterator.hasNext())
        {
          String str = (String)localIterator.next();
          Object localObject = paramJSONObject.get(str);
          if ((localObject instanceof JSONArray)) {
            localJSONObject.put(str, ˊ((JSONArray)localObject));
          } else if ((localObject instanceof JSONObject)) {
            localJSONObject.put(str, ˎ((JSONObject)localObject));
          } else {
            localJSONObject.put(str, ˏ(str, localObject, ˏ));
          }
        }
        return localJSONObject;
      }
      return paramJSONObject;
    }
    
    private static Object ˏ(String paramString, Object paramObject, Map<String, String> paramMap)
    {
      if ((!TextUtils.isEmpty(paramString)) && (paramObject != null) && (paramMap != null) && (paramMap.containsKey(paramString))) {
        return "***";
      }
      return paramObject;
    }
  }
  
  public static class ˏ
  {
    public static final Pattern ॱ = Pattern.compile("\\s*(\"[^\"]*\"|[^<>\"]+)\\s*<([^<>]+)>\\s*");
    
    public ˏ() {}
    
    public static String ˊ(String paramString)
    {
      boolean bool;
      if ((κ.ˏ) && (!TextUtils.isEmpty(paramString))) {
        bool = true;
      } else {
        bool = false;
      }
      ν.ˎ(bool, "Formatting an invalid number, empty or null, number == " + paramString);
      try
      {
        String str = ॱ(paramString, PhoneNumberUtil.ˊ.NATIONAL);
        return str;
      }
      catch (NumberParseException localNumberParseException) {}
      return paramString;
    }
    
    public static String ˋ(String paramString)
    {
      return ॱ(paramString, false);
    }
    
    public static String ˋ(List<UK> paramList)
    {
      boolean bool;
      if ((Rl.ˏ) && (paramList != null) && (paramList.size() > 0)) {
        bool = true;
      } else {
        bool = false;
      }
      ν.ˎ(bool, "Members list can not be empty");
      return ˎ(new ahi.ᐝ.iF(paramList));
    }
    
    public static String ˎ(Context paramContext, long paramLong, int paramInt)
    {
      int j = 0;
      int i = 0;
      ArrayList localArrayList = new ArrayList();
      if (paramLong >= 3600L)
      {
        int k = (int)(paramLong / 3600L);
        paramInt = 2131231796;
        localArrayList.add(Integer.valueOf(k));
      }
      if (paramLong >= 60L) {
        i = (int)(paramLong / 60L % 60L);
      }
      localArrayList.add(Integer.valueOf(i));
      i = j;
      if (paramLong >= 0L) {
        i = (int)(paramLong % 60L);
      }
      localArrayList.add(Integer.valueOf(i));
      return String.format(paramContext.getString(paramInt), localArrayList.toArray(new Integer[] { Integer.valueOf(localArrayList.size()) }));
    }
    
    public static String ˎ(String paramString)
    {
      String str = Preferences.ͺ.ˋ.ʻ();
      PhoneNumberUtil localPhoneNumberUtil = PhoneNumberUtil.getInstance();
      wo.ˋ localˋ = localPhoneNumberUtil.parse(paramString, str);
      if (localPhoneNumberUtil.isValidNumberForRegion(localˋ, str)) {
        return ॱ(paramString, PhoneNumberUtil.ˊ.NATIONAL);
      }
      if (ahi.ˌ.ॱˋ(paramString))
      {
        str = paramString;
        if (paramString.startsWith("00")) {
          str = "+" + paramString.substring(2, paramString.length());
        }
        return localPhoneNumberUtil.format(localPhoneNumberUtil.parse(str, String.valueOf(localPhoneNumberUtil.parse(str, null).getPreferredDomesticCarrierCode())), PhoneNumberUtil.ˊ.INTERNATIONAL);
      }
      return localPhoneNumberUtil.formatInOriginalFormat(localˋ, str);
    }
    
    private static String ˎ(ahi.ᐝ.if paramIf)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      int i = 0;
      while (i < paramIf.ˏ())
      {
        String str = paramIf.ˏ(i).trim();
        if (ahi.ˌ.ʽ(str)) {
          str = ˋ(str);
        }
        localStringBuilder.append(str);
        if (i < paramIf.ˏ() - 1) {
          localStringBuilder.append(", ");
        }
        i += 1;
      }
      return localStringBuilder.toString();
    }
    
    public static String ˏ(long paramLong)
    {
      return new SimpleDateFormat("HH:mm:ss.SSS").format(new Date(paramLong));
    }
    
    public static String ˏ(String paramString)
    {
      return ॱ(paramString, true);
    }
    
    public static String ॱ(long paramLong)
    {
      Date localDate = new Date(paramLong);
      SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      localSimpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
      return localSimpleDateFormat.format(localDate);
    }
    
    public static String ॱ(String paramString)
    {
      vY localVY = PhoneNumberUtil.getInstance().getAsYouTypeFormatter(Preferences.ͺ.ˋ.ʻ());
      if ((!TextUtils.isEmpty(paramString)) && (!paramString.contains("*")) && (!paramString.contains("#")) && (!paramString.contains(",")))
      {
        localVY.ˊ();
        String str = "";
        int i = 0;
        while (i < paramString.length())
        {
          str = localVY.ˏ(paramString.charAt(i));
          i += 1;
        }
        return str;
      }
      return paramString;
    }
    
    public static String ॱ(String paramString, PhoneNumberUtil.ˊ paramˊ)
    {
      String str = Preferences.ͺ.ˋ.ʻ();
      PhoneNumberUtil localPhoneNumberUtil = PhoneNumberUtil.getInstance();
      wo.ˋ localˋ = localPhoneNumberUtil.parse(paramString, str);
      if (paramˊ == PhoneNumberUtil.ˊ.NATIONAL)
      {
        if (!ahi.ˌ.ॱˋ(paramString)) {
          return localPhoneNumberUtil.formatInOriginalFormat(localˋ, str);
        }
        if ((paramString.startsWith(String.valueOf("+" + localˋ.getCountryCode()))) || (paramString.startsWith(String.valueOf(localˋ.getCountryCode())))) {
          return localPhoneNumberUtil.format(localˋ, paramˊ);
        }
        return localPhoneNumberUtil.format(localˋ.clearCountryCode(), paramˊ);
      }
      return localPhoneNumberUtil.format(localˋ, paramˊ);
    }
    
    public static String ॱ(String paramString, boolean paramBoolean)
    {
      boolean bool;
      if ((κ.ˏ) && (!TextUtils.isEmpty(paramString))) {
        bool = true;
      } else {
        bool = false;
      }
      ν.ˎ(bool, "Formatting an invalid number, empty or null, number == " + paramString);
      if (TextUtils.isEmpty(paramString)) {
        return paramString;
      }
      String str = ahi.ʿ.ʼ(paramString);
      if (paramBoolean) {}
      label81:
      try
      {
        str = ॱ(str, PhoneNumberUtil.ˊ.INTERNATIONAL);
        paramString = str;
        break label81;
        str = ˎ(str);
        paramString = str;
      }
      catch (NumberParseException localNumberParseException) {}
      if ((κ.ˏ) && (!TextUtils.isEmpty(paramString))) {
        paramBoolean = true;
      } else {
        paramBoolean = false;
      }
      Ϛ.ˊ(paramBoolean, "Formatted number is empty or null, number = " + paramString);
      return paramString;
    }
    
    public static String ॱ(String[] paramArrayOfString)
    {
      boolean bool;
      if ((Rl.ˏ) && (paramArrayOfString != null) && (paramArrayOfString.length > 0)) {
        bool = true;
      } else {
        bool = false;
      }
      ν.ˎ(bool, "Members list can not be empty");
      return ˎ(new ahi.ᐝ.If(paramArrayOfString));
    }
  }
  
  public static class ͺ
  {
    public ͺ() {}
    
    public static Intent ˊ(Context paramContext, boolean paramBoolean1, String paramString1, long paramLong1, String paramString2, String paramString3, String paramString4, ArrayList<ContactAddressAndName> paramArrayList, long paramLong2, String paramString5, String paramString6, boolean paramBoolean2)
    {
      paramContext = new Intent(paramContext, ConversationActivity.class);
      PZ.CONVERSATION.infest(paramContext);
      paramContext.putExtra("started_from_inbox_screen", paramBoolean1);
      paramContext.putExtra("address_E164", paramString1);
      paramContext.putExtra("group_id", paramLong1);
      paramContext.putExtra("display_name_or_address", paramString2);
      paramContext.putExtra("contact_or_group_picture_url", paramString3);
      paramContext.putExtra("group_members", paramString4);
      paramContext.putParcelableArrayListExtra("group_member_list", paramArrayList);
      paramContext.putExtra("thread_id", paramLong2);
      paramContext.putExtra("thread_draft_message", paramString5);
      paramContext.putExtra("thread_draft_image_path", paramString6);
      paramContext.putExtra("group_active", paramBoolean2);
      paramContext.putExtra("conversation_mode", 2);
      paramContext.setFlags(1);
      return paramContext;
    }
    
    private static String ˊ(Context paramContext)
    {
      return ˎ(paramContext, -1);
    }
    
    public static void ˊ(Activity paramActivity)
    {
      try
      {
        paramActivity.setVolumeControlStream(2);
        PY.ˊ().log(Level.INFO, "Call volume bound to ptapi call");
        return;
      }
      catch (Exception localException)
      {
        PY.ˊ().ˎ(Level.SEVERE, localException);
        paramActivity.setVolumeControlStream(Integer.MIN_VALUE);
        PY.ˊ().log(Level.INFO, "Call volume bound to ringer");
        boolean bool;
        if ((κ.ˏ) && (paramActivity.getVolumeControlStream() == Integer.MIN_VALUE)) {
          bool = true;
        } else {
          bool = false;
        }
        Ϛ.ˊ(bool, "volume streams are different after set");
      }
    }
    
    public static void ˊ(Context paramContext, Intent paramIntent)
    {
      paramIntent.putExtra("finish_all", true);
      paramContext.startActivity(paramIntent);
    }
    
    public static void ˊ(Context paramContext, String paramString)
    {
      boolean bool;
      if ((κ.ˏ) && (!TextUtils.isEmpty(paramString))) {
        bool = true;
      } else {
        bool = false;
      }
      ʢ.ॱ(bool, "Trying to start conversation with an empty phone number");
      if ((κ.ˏ) && (ahi.ˌ.ʽ(paramString))) {
        bool = true;
      } else {
        bool = false;
      }
      ʢ.ॱ(bool, "Trying to start conversation but the address is not a phone number");
      ahi.AuX.ˊ(new ahm(ahi.ʿ.ˎ(paramContext, paramString), paramContext), new Boolean[0]);
    }
    
    public static Intent ˋ(Context paramContext, String[] paramArrayOfString)
    {
      return ˋ(paramContext, paramArrayOfString, false);
    }
    
    public static Intent ˋ(Context paramContext, String[] paramArrayOfString, boolean paramBoolean)
    {
      int i;
      if (paramBoolean) {
        i = 2131231093;
      } else {
        i = 2131231645;
      }
      return ॱ(paramArrayOfString, paramContext.getString(i), ˊ(paramContext));
    }
    
    public static String ˋ()
    {
      return "support@pinger.com";
    }
    
    public static void ˋ(Context paramContext)
    {
      paramContext.startActivity(Intent.createChooser(ॱ(new String[] { ˋ() }, null, null), paramContext.getString(2131231668)));
    }
    
    public static void ˋ(Context paramContext, Intent paramIntent, Class... paramVarArgs)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      if (paramVarArgs != null)
      {
        int i = 0;
        while (i < paramVarArgs.length)
        {
          if (i != 0) {
            localStringBuilder.append(",");
          }
          localStringBuilder.append(paramVarArgs[i].getName());
          i += 1;
        }
      }
      paramIntent.putExtra("finish_exclude_class", localStringBuilder.toString());
      paramIntent.putExtra("finish_all", true);
      paramContext.startActivity(paramIntent);
    }
    
    public static Intent ˎ(Context paramContext, String[] paramArrayOfString)
    {
      return ॱ(paramArrayOfString, paramContext.getString(2131231036), ॱ(paramContext));
    }
    
    private static String ˎ(Context paramContext, int paramInt)
    {
      return ˎ(paramContext, paramInt, null);
    }
    
    public static String ˎ(Context paramContext, int paramInt, String paramString)
    {
      UL localUL = TFService.getTFInstance().getProfile();
      StringBuilder localStringBuilder = new StringBuilder();
      if (paramInt <= 0) {
        paramInt = 2131231644;
      }
      localStringBuilder.append(paramContext.getString(paramInt));
      localStringBuilder.append(paramContext.getString(2131231162));
      if (!TextUtils.isEmpty(paramString)) {
        localStringBuilder.append(paramString);
      }
      String str = localUL.ᐝॱ();
      if (!TextUtils.isEmpty(localUL.ʿ())) {
        paramString = localUL.ʿ();
      } else {
        paramString = Preferences.ʽ.If.ॱ();
      }
      localStringBuilder.append(paramContext.getString(2131231324, new Object[] { str, paramString, Build.BRAND, Build.MODEL, Build.VERSION.RELEASE, TFApplication.ˏॱ().getPackageName(), TFApplication.ˏॱ().ʻ() }));
      return localStringBuilder.toString();
    }
    
    public static void ˎ(Activity paramActivity)
    {
      PTAPISoftphoneAsync localPTAPISoftphoneAsync = VoiceManager.ˏ().ʿ();
      boolean bool;
      if ((κ.ˏ) && (localPTAPISoftphoneAsync != null)) {
        bool = true;
      } else {
        bool = false;
      }
      ν.ˎ(bool, "client is null or inactive when trying to bind, not allowed");
      for (;;)
      {
        try
        {
          if (paramActivity.getVolumeControlStream() != localPTAPISoftphoneAsync.getInCallStream())
          {
            paramActivity.setVolumeControlStream(localPTAPISoftphoneAsync.getInCallStream());
            if ((!κ.ˏ) || (paramActivity.getVolumeControlStream() != localPTAPISoftphoneAsync.getInCallStream())) {
              break label162;
            }
            bool = true;
            Ϛ.ˊ(bool, "volume streams are different after set");
            PY.ˊ().log(Level.INFO, "Call volume bound to ptapi call");
          }
          else
          {
            PY.ˊ().log(Level.INFO, "Call volume already bound to ptapi call");
          }
          return;
        }
        catch (Exception localException)
        {
          PY.ˊ().ˎ(Level.SEVERE, localException);
          paramActivity.setVolumeControlStream(Integer.MIN_VALUE);
          PY.ˊ().log(Level.INFO, "Call volume bound to ringer");
          if ((κ.ˏ) && (paramActivity.getVolumeControlStream() == Integer.MIN_VALUE)) {
            bool = true;
          } else {
            bool = false;
          }
          Ϛ.ˊ(bool, "volume streams are different after set");
          return;
        }
        label162:
        bool = false;
      }
    }
    
    public static void ˎ(Context paramContext)
    {
      paramContext.startActivity(Intent.createChooser(ˎ(paramContext, new String[] { ˋ() }), paramContext.getString(2131231668)));
    }
    
    private static void ˎ(Context paramContext, Intent paramIntent, int paramInt)
    {
      if ((paramContext != null) && ((paramContext instanceof Activity)))
      {
        paramContext = (Activity)paramContext;
        if (paramInt == -1) {
          paramContext.startActivity(paramIntent);
        } else {
          paramContext.startActivityForResult(paramIntent, paramInt);
        }
        return;
      }
      paramContext.startActivity(paramIntent);
    }
    
    public static void ˎ(Context paramContext, Class paramClass)
    {
      ˊ(paramContext, new Intent(paramContext, paramClass));
    }
    
    public static void ˎ(Context paramContext, Class paramClass, PZ paramPZ)
    {
      paramClass = new Intent(paramContext, paramClass);
      paramPZ.infest(paramClass);
      ˊ(paramContext, paramClass);
    }
    
    private static void ˎ(FragmentActivity paramFragmentActivity, String paramString1, String paramString2, Bundle paramBundle, boolean paramBoolean)
    {
      boolean bool;
      if ((κ.ˏ) && (!TextUtils.isEmpty(paramString1))) {
        bool = true;
      } else {
        bool = false;
      }
      ν.ˎ(bool, "calling number should be valid!");
      if (ahi.ˌ.ˋ(paramString1))
      {
        if (TFApplication.ˏॱ().getApplicationContext().getResources().getBoolean(2131558412)) {
          ahf.ˊ(paramFragmentActivity.getSupportFragmentManager(), Yk.ˊ(paramFragmentActivity, paramString1), null);
        } else {
          ahi.ι.ˎ(paramString1, paramFragmentActivity);
        }
        agm.ˏ().ˎ("Call emergency number");
        return;
      }
      if (ahi.auX.ˋ())
      {
        adY.ˎ().ˊ("Make Call (attempt or connected)", "from", "conversation");
        Intent localIntent = new Intent(paramFragmentActivity, CallScreen.class);
        localIntent.putExtra("navigate_to_call_contact_address", new Ut(paramString1, paramString1, (byte)1));
        if (paramBoolean) {
          PZ.GOTO_CALL_THEN_CONVERSATION.infest(localIntent);
        } else {
          PZ.GOTO_CALL_THEN_RETURN.infest(localIntent);
        }
        localIntent.putExtra("contact_address_address", paramString1);
        localIntent.putExtra("contact_address_name", paramString2);
        if (!ahi.AuX.ˏ(new ahp(paramBundle, localIntent, paramFragmentActivity))) {
          paramFragmentActivity.startActivity(localIntent);
        }
        return;
      }
      ahf.ˊ(paramFragmentActivity.getSupportFragmentManager(), ahf.ॱ(paramFragmentActivity.getString(2131231239), null), null);
      adY.ˎ().ˋ("Make Call (attempt or connected)", "error or connected", "NO INTERNET", true);
    }
    
    public static void ˎ(FragmentActivity paramFragmentActivity, String paramString1, String paramString2, View paramView, boolean paramBoolean)
    {
      ahz.If.ˎ("Call initiated");
      Pf.ˎ("Last Call initiated").ˊ(new Pk[] { Pf.ˋ.APPBOY }).ˎ();
      Pf.ˎ("Call initiated").ˊ(new Pk[] { Pf.ˋ.FB }).ˎ();
      if (!ahi.ˌ.ʽ(paramString1))
      {
        ahf.ˊ(paramFragmentActivity.getSupportFragmentManager(), ahf.ॱ(paramFragmentActivity.getString(2131231084), null), "call_invalid_numbers");
        return;
      }
      if (ahi.ʿ.ʽ(paramString1))
      {
        ahf.ˊ(paramFragmentActivity.getSupportFragmentManager(), ahf.ॱ(paramFragmentActivity.getString(2131231088, new Object[] { paramFragmentActivity.getString(2131231734) }), null), null);
        return;
      }
      Intent localIntent = new Intent(paramFragmentActivity, CallScreen.class);
      Bundle localBundle = new Bundle();
      ahi.AuX.ˏ(new ahl(paramView, paramFragmentActivity, localBundle, localIntent));
      paramView = VoiceManager.ˏ().ˊ();
      if ((paramView != null) && (ahi.ʿ.ˏ(paramString1, paramView.getPhoneAddress().getNumber())))
      {
        adY.ˎ().ˊ("Make Call (attempt or connected)", "from", "conversation");
        if (paramBoolean) {
          PZ.GOTO_CALL_THEN_CONVERSATION.infest(localIntent);
        } else {
          PZ.GOTO_CALL_THEN_RETURN.infest(localIntent);
        }
        localIntent.putExtra("contact_address_address", paramString1);
        localIntent.putExtra("contact_address_name", paramString2);
        localIntent.putExtra("call_id", paramView.getCallId());
        if (!ahi.AuX.ˏ(new aho(paramFragmentActivity, localIntent, localBundle))) {
          paramFragmentActivity.startActivity(localIntent);
        }
        return;
      }
      if ((Preferences.auX.ॱ()) && (VoiceManager.ˏ().ˊ() == null))
      {
        if (VoicemailController.ʻ().ॱ())
        {
          ahf.ˊ(paramFragmentActivity.getSupportFragmentManager(), ahf.ॱ(paramFragmentActivity.getString(2131231189), paramFragmentActivity.getString(2131231190)), null);
          return;
        }
        ahi.ι.ˋ(paramString1, paramFragmentActivity);
        return;
      }
      if ((VoiceManager.ˏ().ˋ()) || (ahi.auX.ˎ(paramFragmentActivity.getApplicationContext())))
      {
        ahf.ˋ(paramFragmentActivity);
        return;
      }
      if (VoicemailController.ʻ().ॱ())
      {
        ahf.ˊ(paramFragmentActivity.getSupportFragmentManager(), ahf.ॱ(paramFragmentActivity.getString(2131231189), paramFragmentActivity.getString(2131231190)), null);
        return;
      }
      ˎ(paramFragmentActivity, paramString1, paramString2, localBundle, paramBoolean);
    }
    
    public static Intent ˏ(String[] paramArrayOfString)
    {
      return ॱ(paramArrayOfString, null, null);
    }
    
    public static void ˏ(Activity paramActivity)
    {
      paramActivity.setVolumeControlStream(Integer.MIN_VALUE);
      PY.ˊ().log(Level.INFO, "Call volume bound to ringer");
      boolean bool;
      if ((κ.ˏ) && (paramActivity.getVolumeControlStream() == Integer.MIN_VALUE)) {
        bool = true;
      } else {
        bool = false;
      }
      Ϛ.ˊ(bool, "volume streams are different after set");
    }
    
    public static void ˏ(Context paramContext)
    {
      ॱ(paramContext, false);
    }
    
    public static Intent ॱ(Context paramContext, boolean paramBoolean1, String paramString1, int paramInt, String paramString2, String paramString3, long paramLong1, long paramLong2, String paramString4, String paramString5, boolean paramBoolean2)
    {
      return ॱ(paramContext, paramBoolean1, paramString1, paramInt, paramString2, paramString3, paramLong1, paramLong2, paramString4, paramString5, paramBoolean2, false, false, null);
    }
    
    public static Intent ॱ(Context paramContext, boolean paramBoolean1, String paramString1, int paramInt, String paramString2, String paramString3, long paramLong1, long paramLong2, String paramString4, String paramString5, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, String paramString6)
    {
      if (!paramBoolean3)
      {
        boolean bool;
        if ((κ.ˏ) && (!TextUtils.isEmpty(paramString1))) {
          bool = true;
        } else {
          bool = false;
        }
        ν.ˎ(bool, "address is invalid: " + paramString1);
        if ((κ.ˏ) && (!TextUtils.isEmpty(paramString2))) {
          bool = true;
        } else {
          bool = false;
        }
        ν.ˎ(bool, "displayName is invalid: " + paramString2);
      }
      paramContext = new Intent(paramContext, ConversationActivity.class);
      PZ.CONVERSATION.infest(paramContext);
      paramContext.putExtra("started_from_inbox_screen", paramBoolean1);
      paramContext.putExtra("address_E164", paramString1);
      paramContext.putExtra("address_type", paramInt);
      paramContext.putExtra("display_name_or_address", paramString2);
      paramContext.putExtra("contact_or_group_picture_url", paramString3);
      paramContext.putExtra("native_contact_id", paramLong1);
      paramContext.putExtra("thread_id", paramLong2);
      paramContext.putExtra("thread_draft_message", paramString4);
      paramContext.putExtra("thread_draft_image_path", paramString5);
      paramContext.putExtra("conversation_mode", 0);
      paramContext.putExtra("call_after_init", paramBoolean2);
      paramContext.putExtra("is_empty_conversation", paramBoolean3);
      paramContext.putExtra("open_sticker_category_list", paramBoolean4);
      paramContext.putExtra("sticker_search_term", paramString6);
      paramContext.setFlags(1);
      return paramContext;
    }
    
    public static Intent ॱ(Context paramContext, String[] paramArrayOfString, boolean paramBoolean, String paramString)
    {
      Intent localIntent = new Intent("android.intent.action.SENDTO");
      localIntent.setType("message/rfc822");
      localIntent.setData(Uri.parse("mailto:"));
      localIntent.putExtra("android.intent.extra.EMAIL", paramArrayOfString);
      paramArrayOfString = ahi.if.ˎ();
      if (!TextUtils.isEmpty(paramArrayOfString)) {
        localIntent.putExtra("android.intent.extra.CC", new String[] { paramArrayOfString });
      }
      if (TextUtils.isEmpty(paramString))
      {
        int i;
        if (paramBoolean) {
          i = 2131231093;
        } else {
          i = 2131231645;
        }
        paramString = paramContext.getString(i);
      }
      localIntent.putExtra("android.intent.extra.SUBJECT", paramString);
      localIntent.putExtra("android.intent.extra.TEXT", ˊ(paramContext));
      return localIntent;
    }
    
    public static Intent ॱ(String[] paramArrayOfString, String paramString1, String paramString2)
    {
      Intent localIntent = new Intent("android.intent.action.SENDTO");
      localIntent.setType("message/rfc822");
      localIntent.setData(Uri.parse("mailto:"));
      localIntent.putExtra("android.intent.extra.EMAIL", paramArrayOfString);
      paramArrayOfString = ahi.if.ˎ();
      if (!TextUtils.isEmpty(paramArrayOfString)) {
        localIntent.putExtra("android.intent.extra.CC", new String[] { paramArrayOfString });
      }
      localIntent.putExtra("android.intent.extra.SUBJECT", paramString1);
      localIntent.putExtra("android.intent.extra.TEXT", paramString2);
      return localIntent;
    }
    
    public static <T> T ॱ(int paramInt)
    {
      Object localObject1 = null;
      for (;;)
      {
        try
        {
          String str = PingerApplication.ˋ().getString(paramInt);
          localObject1 = str;
          PY.ˊ().info("initDynamicComponent with class: " + str);
          localObject1 = str;
          Object localObject2 = Class.forName(str).newInstance();
          localObject1 = str;
          if (κ.ˏ)
          {
            localObject1 = str;
            if (localObject2.getClass().isAnnotationPresent(Rb.class))
            {
              bool = true;
              localObject1 = str;
              ʢ.ॱ(bool, "Dynamic component must be annotated with DxIgnore: " + str);
              return localObject2;
            }
          }
        }
        catch (Exception localException)
        {
          PY.ˊ().ˎ(Level.SEVERE, localException);
          bool = κ.ˏ;
          ʢ.ॱ(false, "Cannot find dynamic component: " + localObject1);
          return null;
        }
        boolean bool = false;
      }
    }
    
    private static String ॱ(Context paramContext)
    {
      return ˎ(paramContext, 2131231037) + " beta";
    }
    
    public static void ॱ()
    {
      Context localContext = TFApplication.ˏॱ().getApplicationContext();
      Intent localIntent = new Intent(localContext, InboxActivity.class);
      localIntent.addFlags(268435456);
      ˊ(localContext, localIntent);
      try
      {
        throw new Exception("Cannot start ConversationActivity as getArguments() returns null");
      }
      catch (Exception localException)
      {
        PY.ˊ().log(Level.INFO, "Cannot start ConversationActivity as getArguments() returns null");
        Crashlytics.logException(localException);
      }
    }
    
    public static void ॱ(Context paramContext, boolean paramBoolean)
    {
      ॱ(paramContext, paramBoolean, null, null);
    }
    
    public static void ॱ(Context paramContext, boolean paramBoolean, String paramString1, String paramString2)
    {
      if (TextUtils.isEmpty(paramString1)) {
        paramString1 = ˋ();
      }
      paramContext.startActivity(Intent.createChooser(ॱ(paramContext, new String[] { paramString1 }, paramBoolean, paramString2), paramContext.getString(2131231668)));
    }
  }
  
  public static class ᐝ
  {
    public ᐝ() {}
    
    public static void ˊ()
    {
      new Thread(new ahj()).start();
    }
    
    private static String[] ˋ(List<Pair<String, String>> paramList)
    {
      String[] arrayOfString = new String[paramList.size()];
      int i = 0;
      while (i < paramList.size())
      {
        Object localObject = (Pair)paramList.get(i);
        if (TextUtils.isEmpty((CharSequence)((Pair)localObject).second)) {
          localObject = (String)((Pair)localObject).first;
        } else {
          localObject = (String)((Pair)localObject).second;
        }
        arrayOfString[i] = localObject;
        i += 1;
      }
      return arrayOfString;
    }
    
    public static boolean ˏ(Map<String, Long> paramMap)
    {
      Iterator localIterator = paramMap.keySet().iterator();
      while (localIterator.hasNext())
      {
        long l = ((Long)paramMap.get((String)localIterator.next())).longValue();
        if ((l >= 0L) && (Wg.ॱ(l) <= 0)) {
          return true;
        }
      }
      return false;
    }
  }
  
  public static class ι
  {
    public ι() {}
    
    public static void ˊ(String paramString, Activity paramActivity)
    {
      if (Build.VERSION.SDK_INT >= 22)
      {
        ˋ(paramString, paramActivity);
        return;
      }
      ˎ(paramString, paramActivity);
    }
    
    public static void ˋ(String paramString, Activity paramActivity)
    {
      ॱ(paramActivity, null, null, paramString, 1012);
    }
    
    public static void ˎ(String paramString, Activity paramActivity)
    {
      ॱ(paramActivity, new Intent("android.intent.action.DIAL"), null, paramString, 1012);
    }
    
    private static void ॱ(Context paramContext, Intent paramIntent, String paramString1, String paramString2, int paramInt)
    {
      boolean bool;
      if ((κ.ˏ) && (!TextUtils.isEmpty(paramString2))) {
        bool = true;
      } else {
        bool = false;
      }
      ν.ˎ(bool, "real number can not be empty");
      if ((κ.ˏ) && (paramContext != null)) {
        bool = true;
      } else {
        bool = false;
      }
      ν.ˎ(bool, "can not be both activity, and activity starter empty");
      if (paramIntent == null)
      {
        paramIntent = new Intent("android.intent.action.CALL");
        paramIntent.addFlags(268435456);
      }
      paramIntent.setData(Uri.parse("tel:" + paramString2));
      List localList = paramContext.getPackageManager().queryIntentActivities(paramIntent, 65536);
      Object localObject2 = null;
      if ((κ.ˏ) && (localList != null)) {
        bool = true;
      } else {
        bool = false;
      }
      ν.ˎ(bool, "result can not be empty");
      Iterator localIterator = localList.iterator();
      Object localObject1;
      for (;;)
      {
        localObject1 = localObject2;
        if (!localIterator.hasNext()) {
          break;
        }
        localObject1 = (ResolveInfo)localIterator.next();
        if ((0 == 0) && ((((ResolveInfo)localObject1).activityInfo.packageName.contains("com.android")) || (((ResolveInfo)localObject1).activityInfo.packageName.contains("com.google.android"))))
        {
          localObject1 = ((ResolveInfo)localObject1).activityInfo;
          break;
        }
      }
      if (!TextUtils.isEmpty(paramString1))
      {
        paramString1 = "tel:" + Uri.encode(new StringBuilder().append(paramString1).append(",,").toString()) + "#" + Uri.encode(paramString2);
        paramIntent.putExtra("com.android.phone.extra.GATEWAY_PROVIDER_PACKAGE", paramContext.getPackageName());
        paramIntent.putExtra("com.android.phone.extra.GATEWAY_URI", paramString1);
      }
      if (localObject1 != null)
      {
        paramIntent.setClassName(((ActivityInfo)localObject1).packageName, ((ActivityInfo)localObject1).name);
        ahi.ͺ.ˋ(paramContext, paramIntent, paramInt);
        return;
      }
      if (localList.size() > 0)
      {
        paramIntent = Intent.createChooser(paramIntent, null);
        paramIntent.addFlags(268435456);
        ahi.ͺ.ˋ(paramContext, paramIntent, paramInt);
        return;
      }
      Toast.makeText(paramContext, "No default calling application found on device!", 0).show();
    }
  }
}
