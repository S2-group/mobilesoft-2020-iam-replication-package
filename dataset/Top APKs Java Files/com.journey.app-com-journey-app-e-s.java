package com.journey.app.e;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.text.Html;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout.LayoutParams;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.l.a;
import androidx.drawerlayout.widget.DrawerLayout;
import com.commonsware.cwac.provider.StreamProvider;
import com.journey.app.AddOnChooserActivity;
import com.journey.app.AddOnMembershipActivity;
import com.journey.app.AddOnPremiumActivity;
import com.journey.app.UpsellMembershipActivity;
import com.journey.app.UpsellMembershipDialogActivity;
import com.journey.app.c.b;
import com.journey.app.custom.CustomTypefaceSpan;
import com.journey.app.custom.ab;
import com.journey.app.custom.ac;
import com.journey.app.custom.q;
import com.journey.app.object.d;
import com.journey.app.sync.SyncService;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.util.TreeSet;
import org.apache.commons.io.FilenameUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class s
{
  public static final String[] a = { "image/*", "video/*", "audio/*" };
  public static final String[] b = { "image/*" };
  public static final String[] c = { ".gif", ".sticker", ".mp4", ".3gp", ".webm", ".mp3" };
  public static final String[] d = { ".jpg", ".jpeg", ".png", ".bmp", ".webp" };
  public static final String[] e = { ".jpg", ".jpeg", ".png", ".bmp", ".webp", ".gif", ".sticker", ".mp4", ".3gp", ".webm" };
  
  public static void A(Context paramContext, String paramString)
  {
    paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext).edit();
    String str = c("cloud");
    if (paramString == null) {
      paramString = "false";
    }
    paramContext.putString(str, c(paramString)).commit();
  }
  
  public static boolean A(Context paramContext)
  {
    return (PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean("markdown", true)) && (ar(paramContext));
  }
  
  public static void B(Context paramContext, String paramString)
  {
    paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext).edit();
    String str = c("cloud-source");
    if (paramString == null) {
      paramString = "";
    }
    paramContext.putString(str, paramString).commit();
  }
  
  public static boolean B(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean("sync", false);
  }
  
  private static String C(Context paramContext, String paramString)
  {
    paramContext = new File(paramContext.getApplicationInfo().dataDir, paramString);
    if (!paramContext.exists()) {
      paramContext.mkdirs();
    }
    return paramContext.getAbsolutePath();
  }
  
  public static boolean C(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean("roam", false);
  }
  
  public static TreeSet<String> D(Context paramContext)
  {
    paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext);
    TreeSet localTreeSet = new TreeSet();
    int i = 1;
    while (i < 8)
    {
      localTreeSet.add(String.valueOf(i));
      i += 1;
    }
    paramContext = paramContext.getStringSet("reminder_day_2", localTreeSet);
    localTreeSet = new TreeSet();
    localTreeSet.addAll(paramContext);
    return localTreeSet;
  }
  
  public static int E(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getInt("reminder_hour_2", 20);
  }
  
  public static int F(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getInt("reminder_min_2", 0);
  }
  
  public static String G(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getString("first_day_of_week", "-1");
  }
  
  public static String H(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getString("time_format", "-1");
  }
  
  public static String I(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getString("temp_unit", "0");
  }
  
  public static int J(Context paramContext)
  {
    return Integer.valueOf(I(paramContext)).intValue();
  }
  
  public static String K(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getString("font_family", "2");
  }
  
  public static String L(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getString("font_size", "0");
  }
  
  public static String M(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getString("line_spacing", "1");
  }
  
  public static int N(Context paramContext)
  {
    paramContext = L(paramContext);
    try
    {
      int i = Integer.valueOf(paramContext).intValue();
      return i;
    }
    catch (NumberFormatException paramContext)
    {
      paramContext.printStackTrace();
    }
    return 0;
  }
  
  public static float O(Context paramContext)
  {
    paramContext = M(paramContext);
    try
    {
      float f = Float.valueOf(paramContext).floatValue();
      return f;
    }
    catch (NumberFormatException paramContext)
    {
      paramContext.printStackTrace();
    }
    return 1.15F;
  }
  
  public static String P(Context paramContext)
  {
    float f = O(paramContext);
    paramContext = new DecimalFormat("#.##");
    paramContext.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.US));
    double d1 = f;
    Double.isNaN(d1);
    return paramContext.format(d1 + 0.5D);
  }
  
  public static String Q(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getString("UserName", "");
  }
  
  public static String R(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getString("ProfilePicLoc", "");
  }
  
  public static String S(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getString("Google-Acc-Name", "");
  }
  
  public static String T(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getString("Google-Changes-Page-Token", "");
  }
  
  public static long U(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getLong("Last-Sync-Date-2", -1L);
  }
  
  public static String V(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getString("Last-Sync-Status-2", "");
  }
  
  public static boolean W(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean("nightmode", false);
  }
  
  public static String X(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getString("passcode_timeout", "30");
  }
  
  public static int Y(Context paramContext)
  {
    try
    {
      int i = Integer.valueOf(X(paramContext)).intValue();
      return i;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return 30;
  }
  
  public static String Z(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getString("pin", "");
  }
  
  public static double a(double paramDouble)
  {
    return paramDouble * 9.0D / 5.0D + 32.0D;
  }
  
  public static float a(Context paramContext, int paramInt1, int paramInt2)
  {
    return paramContext.getResources().getDimension(paramInt2) / paramContext.getResources().getDisplayMetrics().density + paramInt1 * 2;
  }
  
  public static int a(Context paramContext, boolean paramBoolean)
  {
    if (paramBoolean) {
      return paramContext.getResources().getColor(2131099947);
    }
    return paramContext.getResources().getColor(2131099945);
  }
  
  public static int a(BitmapFactory.Options paramOptions, int paramInt1, int paramInt2)
  {
    int k = paramOptions.outHeight;
    int m = paramOptions.outWidth;
    int j = 1;
    int i = 1;
    if ((k > paramInt2) || (m > paramInt1))
    {
      k /= 2;
      m /= 2;
      for (;;)
      {
        j = i;
        if (k / i <= paramInt2) {
          break;
        }
        j = i;
        if (m / i <= paramInt1) {
          break;
        }
        i *= 2;
      }
    }
    return j;
  }
  
  public static int a(boolean paramBoolean)
  {
    if (paramBoolean) {
      return 10;
    }
    return 4;
  }
  
  public static long a(long paramLong)
  {
    if (paramLong > 0L) {
      return paramLong + 14400000L;
    }
    return 0L;
  }
  
  public static Bitmap a(File paramFile, int paramInt)
  {
    Object localObject1 = null;
    int i;
    Object localObject3;
    do
    {
      try
      {
        Object localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append("Trying to decode bitmap of height: ");
        ((StringBuilder)localObject2).append(paramInt);
        Log.d("", ((StringBuilder)localObject2).toString());
        localObject2 = b(paramFile, paramInt);
        i = paramInt;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        i = paramInt / 2;
        localObject3 = localObject1;
      }
      if (localObject3 != null) {
        break;
      }
      localObject1 = localObject3;
      paramInt = i;
    } while (i > 100);
    return localObject3;
  }
  
  public static Bitmap a(String paramString, int paramInt1, int paramInt2)
  {
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    localOptions.inJustDecodeBounds = true;
    BitmapFactory.decodeFile(paramString, localOptions);
    localOptions.inSampleSize = a(localOptions, paramInt1, paramInt2);
    localOptions.inJustDecodeBounds = false;
    return BitmapFactory.decodeFile(paramString, localOptions);
  }
  
  public static Typeface a(AssetManager paramAssetManager, String paramString)
  {
    if (paramString.equals("0")) {
      return r.a(paramAssetManager, "typeface/Archer-Semibold-Pro.otf");
    }
    if (paramString.equals("1")) {
      return r.a(paramAssetManager, "typeface/IdealSansSSm-Book.otf");
    }
    if (paramString.equals("2")) {
      return Typeface.SANS_SERIF;
    }
    if (paramString.equals("3")) {
      return r.a(paramAssetManager, "typeface/RobotoSlab-Regular.ttf");
    }
    if (paramString.equals("4")) {
      return r.a(paramAssetManager, "typeface/Merriweather-Regular.ttf");
    }
    if (paramString.equals("5")) {
      return r.a(paramAssetManager, "typeface/Verlag-Book.otf");
    }
    return Typeface.SANS_SERIF;
  }
  
  public static Location a(LocationManager paramLocationManager)
  {
    Iterator localIterator = paramLocationManager.getAllProviders().iterator();
    Object localObject = null;
    long l1 = Long.MIN_VALUE;
    float f1 = Float.MAX_VALUE;
    while (localIterator.hasNext())
    {
      Location localLocation = paramLocationManager.getLastKnownLocation((String)localIterator.next());
      if (localLocation != null)
      {
        float f2 = localLocation.getAccuracy();
        long l2 = localLocation.getTime();
        long l3 = 120000;
        if ((l2 > l3) && (f2 < f1)) {
          f1 = f2;
        }
        for (;;)
        {
          localObject = localLocation;
          l1 = l2;
          break;
          if ((l2 >= l3) || (f1 != Float.MAX_VALUE) || (l2 <= l1)) {
            break;
          }
        }
      }
    }
    return localObject;
  }
  
  public static Uri a(File paramFile)
  {
    return StreamProvider.a("com.journey.app.fileprovider", paramFile);
  }
  
  public static SpannableStringBuilder a(SpannableStringBuilder paramSpannableStringBuilder)
  {
    String str = paramSpannableStringBuilder.toString();
    int i = 0;
    while ((str.length() > 0) && (str.startsWith("\n")))
    {
      str = str.substring(1);
      i += 1;
    }
    int j = 0;
    while ((str.length() > 0) && (str.endsWith("\n")))
    {
      str = str.substring(0, str.length() - 1);
      j += 1;
    }
    return paramSpannableStringBuilder.delete(0, i).delete(paramSpannableStringBuilder.length() - j, paramSpannableStringBuilder.length());
  }
  
  public static File a(Context paramContext, String paramString)
  {
    return new File(ax(paramContext), paramString);
  }
  
  public static String a()
  {
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("");
    ((StringBuilder)localObject).append("ewfii293ujd6d8s^8dj3*(4j1*NnzkalhO");
    localObject = ((StringBuilder)localObject).toString();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append((String)localObject);
    localStringBuilder.append("1z99&&;'[wdfiwJHF*38UCID(KCL@*jjW92");
    return localStringBuilder.toString();
  }
  
  public static String a(int paramInt)
  {
    return String.format("#%02x%02x%02x", new Object[] { Integer.valueOf(Color.red(paramInt)), Integer.valueOf(Color.green(paramInt)), Integer.valueOf(Color.blue(paramInt)) });
  }
  
  public static String a(long paramLong, boolean paramBoolean)
  {
    if (paramBoolean) {
      i = 1000;
    } else {
      i = 1024;
    }
    if (paramLong < i)
    {
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append(paramLong);
      ((StringBuilder)localObject1).append(" B");
      return ((StringBuilder)localObject1).toString();
    }
    double d1 = paramLong;
    double d2 = Math.log(d1);
    double d3 = i;
    int i = (int)(d2 / Math.log(d3));
    Object localObject2 = new StringBuilder();
    if (paramBoolean) {
      localObject1 = "kMGTPE";
    } else {
      localObject1 = "KMGTPE";
    }
    ((StringBuilder)localObject2).append(((String)localObject1).charAt(i - 1));
    if (paramBoolean) {
      localObject1 = "";
    } else {
      localObject1 = "i";
    }
    ((StringBuilder)localObject2).append((String)localObject1);
    Object localObject1 = ((StringBuilder)localObject2).toString();
    localObject2 = Locale.US;
    d2 = Math.pow(d3, i);
    Double.isNaN(d1);
    return String.format((Locale)localObject2, "%.1f %sB", new Object[] { Double.valueOf(d1 / d2), localObject1 });
  }
  
  public static String a(Context paramContext, float paramFloat)
  {
    paramContext = new StringBuilder();
    paramContext.append(String.valueOf(paramFloat));
    paramContext.append("px");
    return paramContext.toString();
  }
  
  public static String a(Context paramContext, String paramString, File paramFile)
  {
    paramString = a(paramString, paramFile.getName());
    paramContext = a(paramContext, paramString);
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Save to: ");
    localStringBuilder.append(paramString);
    Log.d("", localStringBuilder.toString());
    try
    {
      p.a(paramFile, paramContext);
    }
    catch (IOException paramString)
    {
      paramString.printStackTrace();
    }
    return paramContext.getName();
  }
  
  public static String a(Context paramContext, String paramString1, String paramString2, InputStream paramInputStream)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString1);
    localStringBuilder.append("-");
    String str = paramString2;
    if (!paramString2.startsWith(localStringBuilder.toString())) {
      str = a(paramString1, paramString2);
    }
    paramContext = a(paramContext, str);
    paramString1 = new StringBuilder();
    paramString1.append("Save to: ");
    paramString1.append(str);
    Log.d("", paramString1.toString());
    try
    {
      p.a(paramInputStream, paramContext);
    }
    catch (IOException paramString1)
    {
      paramString1.printStackTrace();
    }
    return paramContext.getName();
  }
  
  public static String a(String paramString1, String paramString2)
  {
    String str = Long.toHexString(Double.doubleToLongBits(Math.random()));
    int i;
    if (str.length() < 5) {
      i = 4;
    } else {
      i = str.length();
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString1);
    localStringBuilder.append("-");
    localStringBuilder.append(str.substring(0, i));
    localStringBuilder.append(p.c(paramString2));
    return localStringBuilder.toString();
  }
  
  private static String a(String paramString1, String paramString2, int paramInt)
  {
    String str1;
    if ((paramInt & 0x1) == 1) {
      str1 = "bold";
    } else {
      str1 = "normal";
    }
    String str2;
    if ((paramInt & 0x2) == 2) {
      str2 = "italic";
    } else {
      str2 = "normal";
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("@font-face {\n    font-family: '");
    localStringBuilder.append(paramString1);
    localStringBuilder.append("';\n    src: url(");
    localStringBuilder.append(paramString2);
    localStringBuilder.append(");\n    font-weight: ");
    localStringBuilder.append(str1);
    localStringBuilder.append(";\n    font-style: ");
    localStringBuilder.append(str2);
    localStringBuilder.append(";\n}\n");
    return localStringBuilder.toString();
  }
  
  public static String a(Date paramDate)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(String.valueOf(paramDate.getTime()));
    localStringBuilder.append("-");
    localStringBuilder.append(Long.toHexString(Double.doubleToLongBits(Math.random())));
    return localStringBuilder.toString();
  }
  
  public static String a(Date paramDate, String paramString)
  {
    return a(paramDate, paramString, null);
  }
  
  public static String a(Date paramDate, String paramString, TimeZone paramTimeZone)
  {
    if ((!paramString.equals("12")) && (!paramString.equals("24")))
    {
      paramString = DateFormat.getTimeInstance(3, Locale.getDefault());
      if (paramTimeZone != null) {
        paramString.setTimeZone(paramTimeZone);
      }
      return paramString.format(paramDate).toLowerCase(Locale.US);
    }
    if (paramString.equals("12")) {
      paramString = "hh:mm a";
    } else {
      paramString = "HH:mm";
    }
    paramString = new SimpleDateFormat(paramString, Locale.getDefault());
    if (paramTimeZone != null) {
      paramString.setTimeZone(paramTimeZone);
    }
    return paramString.format(paramDate).toLowerCase(Locale.US);
  }
  
  public static String a(Date paramDate, TimeZone paramTimeZone)
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("dd", Locale.getDefault());
    if (paramTimeZone != null) {
      localSimpleDateFormat.setTimeZone(paramTimeZone);
    }
    return localSimpleDateFormat.format(paramDate);
  }
  
  public static String a(JSONObject paramJSONObject)
    throws JSONException
  {
    return paramJSONObject.getString("text");
  }
  
  private static String a(byte[] paramArrayOfByte)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int j = paramArrayOfByte.length;
    int i = 0;
    while (i < j)
    {
      String str = Integer.toHexString(paramArrayOfByte[i] & 0xFF);
      if (str.length() == 1) {
        localStringBuilder.append('0');
      }
      localStringBuilder.append(str);
      i += 1;
    }
    return localStringBuilder.toString();
  }
  
  @TargetApi(21)
  public static void a(Activity paramActivity)
  {
    if (g()) {
      paramActivity.getWindow().setStatusBarColor(Color.parseColor("#44000000"));
    }
  }
  
  @TargetApi(21)
  public static void a(Activity paramActivity, int paramInt)
  {
    if (g()) {
      paramActivity.getWindow().setStatusBarColor(paramInt);
    }
  }
  
  public static void a(Activity paramActivity, PorterDuffColorFilter paramPorterDuffColorFilter)
  {
    if ((paramActivity != null) && ((paramActivity instanceof ac))) {
      paramActivity = ((ac)paramActivity).r_().getNavigationIcon();
    } else {
      paramActivity = null;
    }
    if (paramActivity != null) {
      paramActivity.setColorFilter(paramPorterDuffColorFilter);
    }
  }
  
  public static void a(Activity paramActivity, Menu paramMenu, boolean paramBoolean)
  {
    int i = a(paramActivity, paramBoolean);
    q.a(paramActivity, paramMenu, Color.rgb(Color.red(i), Color.green(i), Color.blue(i)), Color.alpha(i));
    b(paramActivity, paramBoolean);
    c(paramActivity, paramBoolean);
  }
  
  @TargetApi(19)
  public static void a(Activity paramActivity, androidx.appcompat.app.a paramA, int paramInt1, int paramInt2)
  {
    try
    {
      paramInt1 = paramActivity.getResources().getColor(paramInt1);
      if (paramInt2 >= 0) {
        paramA.a(paramActivity.getResources().getDrawable(paramInt2));
      } else {
        paramA.a(new ColorDrawable(paramInt1));
      }
      return;
    }
    finally {}
  }
  
  public static void a(Activity paramActivity, String paramString, ArrayList<String> paramArrayList, ArrayList<Uri> paramArrayList1, boolean paramBoolean)
  {
    String str = paramString.trim();
    if (paramBoolean) {
      str = Html.fromHtml(paramString.replace("<i>", "_").replace("</i>", "_").replace("<em>", "_").replace("</em>", "_").replace("<bold>", "*").replace("</bold>", "*").replace("<strong>", "*").replace("</strong>", "*").replace("<h1>", "*").replace("</h1>", "*").replace("<h2>", "*").replace("</h3>", "*").replace("<h3>", "*").replace("</h2>", "*").replace("<h4>", "*").replace("</h4>", "*").replace("<h5>", "*").replace("</h5>", "*").replace("<h6>", "*").replace("</h6>", "*")).toString().trim();
    }
    paramArrayList.add("journeyapp");
    int i = 0;
    while (i < paramArrayList.size())
    {
      paramString = new StringBuilder();
      paramString.append("#");
      paramString.append((String)paramArrayList.get(i));
      paramArrayList.set(i, paramString.toString());
      i += 1;
    }
    paramString = new StringBuilder();
    paramString.append(str);
    paramString.append("\n");
    paramString.append(TextUtils.join(" ", paramArrayList));
    str = paramString.toString();
    paramArrayList = l.a.a(paramActivity).a("text/plain").a(str).a().setPackage("com.google.android.apps.plus");
    paramString = paramArrayList;
    if (paramArrayList1 != null)
    {
      paramString = paramArrayList;
      if (paramArrayList1.size() > 0)
      {
        paramString = (Uri)paramArrayList1.get(0);
        paramArrayList = p.a(paramString.getPath());
        paramString = l.a.a(paramActivity).a(str).a(paramArrayList).a(paramString).a().setPackage("com.google.android.apps.plus");
      }
    }
    paramString = Intent.createChooser(paramString, paramActivity.getResources().getString(2131886688));
    paramArrayList = paramArrayList1.iterator();
    while (paramArrayList.hasNext())
    {
      paramArrayList1 = (Uri)paramArrayList.next();
      a(paramActivity.getApplicationContext(), paramString, paramArrayList1);
    }
    paramActivity.startActivity(paramString);
  }
  
  public static void a(Activity paramActivity, String paramString, ArrayList<Uri> paramArrayList, boolean paramBoolean)
  {
    Intent localIntent = new Intent("android.intent.action.SEND_MULTIPLE");
    if (paramBoolean)
    {
      localIntent.setType("text/html");
      localIntent.putExtra("android.intent.extra.TEXT", Html.fromHtml(paramString));
      localIntent.putParcelableArrayListExtra("android.intent.extra.STREAM", paramArrayList);
      paramString = paramArrayList.iterator();
      while (paramString.hasNext())
      {
        paramArrayList = (Uri)paramString.next();
        a(paramActivity.getApplicationContext(), localIntent, paramArrayList);
      }
      paramActivity.startActivity(Intent.createChooser(localIntent, paramActivity.getResources().getString(2131886688)));
      return;
    }
    localIntent.setType("text/plain");
    localIntent.putExtra("android.intent.extra.TEXT", paramString);
    localIntent.putParcelableArrayListExtra("android.intent.extra.STREAM", paramArrayList);
    paramActivity.startActivity(Intent.createChooser(localIntent, paramActivity.getResources().getString(2131886688)));
  }
  
  public static void a(Activity paramActivity, String paramString, boolean paramBoolean)
  {
    Intent localIntent = new Intent("android.intent.action.SEND");
    if (paramBoolean)
    {
      localIntent.setType("text/html");
      localIntent.putExtra("android.intent.extra.TEXT", Html.fromHtml(paramString));
      paramActivity.startActivity(Intent.createChooser(localIntent, paramActivity.getResources().getString(2131886688)));
      return;
    }
    localIntent.setType("text/plain");
    localIntent.putExtra("android.intent.extra.TEXT", paramString);
    paramActivity.startActivity(Intent.createChooser(localIntent, paramActivity.getResources().getString(2131886688)));
  }
  
  public static void a(Activity paramActivity, boolean paramBoolean)
  {
    Resources localResources = paramActivity.getResources();
    int i;
    if (paramBoolean) {
      i = 2131099687;
    } else {
      i = 2131099917;
    }
    a(paramActivity, localResources.getColor(i));
  }
  
  public static void a(Activity paramActivity, boolean paramBoolean, int paramInt)
  {
    switch ()
    {
    default: 
      if (paramInt == -1)
      {
        d(paramActivity, paramBoolean);
        return;
      }
      break;
    case 4: 
      a(paramActivity, false, false);
      return;
    case 3: 
      a(paramActivity, true, false);
      return;
    case 2: 
      paramActivity.startActivity(new Intent(paramActivity, AddOnMembershipActivity.class));
      return;
    case 1: 
      paramActivity.startActivity(new Intent(paramActivity, AddOnPremiumActivity.class));
      return;
    }
    a(paramActivity, paramBoolean, Integer.valueOf(paramInt));
  }
  
  public static void a(Activity paramActivity, boolean paramBoolean, Integer paramInteger)
  {
    Intent localIntent = new Intent(paramActivity, AddOnChooserActivity.class);
    int i;
    if (paramInteger != null) {
      i = 1;
    } else {
      i = 2;
    }
    localIntent.putExtra("type", i);
    if (paramInteger != null) {
      localIntent.putExtra("feature", paramInteger);
    }
    paramActivity.startActivity(localIntent);
  }
  
  public static void a(Activity paramActivity, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (paramBoolean1) {
      localObject = UpsellMembershipActivity.class;
    } else {
      localObject = UpsellMembershipDialogActivity.class;
    }
    Object localObject = new Intent(paramActivity, (Class)localObject);
    if (paramBoolean2) {
      ((Intent)localObject).putExtra("NO_BACK_PRESS_KEY", true);
    }
    paramActivity.startActivity((Intent)localObject);
  }
  
  public static void a(Context paramContext)
  {
    try
    {
      paramContext = ViewConfiguration.get(paramContext);
      Field localField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
      if (localField != null)
      {
        localField.setAccessible(true);
        localField.setBoolean(paramContext, false);
      }
      return;
    }
    catch (Exception paramContext) {}
  }
  
  public static void a(Context paramContext, int paramInt)
  {
    paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext).edit();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("today-ad-called-int-");
    localStringBuilder.append(paramInt);
    paramContext.putInt(localStringBuilder.toString(), 2429).commit();
  }
  
  public static void a(Context paramContext, long paramLong)
  {
    PreferenceManager.getDefaultSharedPreferences(paramContext).edit().putString(c("cloud-expiry"), String.valueOf(paramLong)).commit();
  }
  
  public static void a(Context paramContext, Intent paramIntent, Uri paramUri)
  {
    if (g())
    {
      paramIntent.addFlags(2);
      return;
    }
    paramIntent = paramContext.getPackageManager().queryIntentActivities(paramIntent, 65536).iterator();
    while (paramIntent.hasNext()) {
      paramContext.grantUriPermission(((ResolveInfo)paramIntent.next()).activityInfo.packageName, paramUri, 3);
    }
  }
  
  public static void a(Context paramContext, MenuItem paramMenuItem, boolean paramBoolean)
  {
    int i = a(paramContext, paramBoolean);
    q.a(paramMenuItem, i, Color.alpha(i));
  }
  
  public static void a(Context paramContext, Long paramLong)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("last sync date set: ");
    localStringBuilder.append(paramLong);
    Log.d("", localStringBuilder.toString());
    PreferenceManager.getDefaultSharedPreferences(paramContext).edit().putLong("Last-Sync-Date-2", paramLong.longValue()).commit();
  }
  
  public static void a(Context paramContext, Set<String> paramSet)
  {
    PreferenceManager.getDefaultSharedPreferences(paramContext).edit().putStringSet("reminder_day_2", paramSet).commit();
  }
  
  private static void a(ViewGroup.LayoutParams paramLayoutParams, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if ((paramLayoutParams instanceof FrameLayout.LayoutParams))
    {
      paramLayoutParams = (FrameLayout.LayoutParams)paramLayoutParams;
      paramLayoutParams.leftMargin = paramInt1;
      paramLayoutParams.rightMargin = paramInt2;
      paramLayoutParams.topMargin = paramInt3;
      paramLayoutParams.bottomMargin = paramInt4;
      return;
    }
    if ((paramLayoutParams instanceof RelativeLayout.LayoutParams))
    {
      paramLayoutParams = (RelativeLayout.LayoutParams)paramLayoutParams;
      paramLayoutParams.leftMargin = paramInt1;
      paramLayoutParams.rightMargin = paramInt2;
      paramLayoutParams.topMargin = paramInt3;
      paramLayoutParams.bottomMargin = paramInt4;
      return;
    }
    if ((paramLayoutParams instanceof LinearLayout.LayoutParams))
    {
      paramLayoutParams = (LinearLayout.LayoutParams)paramLayoutParams;
      paramLayoutParams.leftMargin = paramInt1;
      paramLayoutParams.rightMargin = paramInt2;
      paramLayoutParams.topMargin = paramInt3;
      paramLayoutParams.bottomMargin = paramInt4;
    }
  }
  
  @TargetApi(19)
  public static void a(Window paramWindow)
  {
    if (f()) {
      paramWindow.addFlags(67108864);
    }
  }
  
  public static void a(androidx.appcompat.app.a paramA, Typeface paramTypeface, CharSequence paramCharSequence)
  {
    if (paramA != null)
    {
      SpannableString localSpannableString = new SpannableString(paramCharSequence);
      localSpannableString.setSpan(new CustomTypefaceSpan("", paramTypeface), 0, localSpannableString.length(), 33);
      try
      {
        paramA.a(localSpannableString);
      }
      catch (IllegalArgumentException paramTypeface)
      {
        paramTypeface.printStackTrace();
        paramA.a(paramCharSequence.toString());
      }
      paramA.b(null);
    }
  }
  
  public static void a(androidx.appcompat.app.a paramA, Typeface paramTypeface, CharSequence paramCharSequence1, CharSequence paramCharSequence2)
  {
    if (paramA != null)
    {
      a(paramA, paramTypeface, paramCharSequence1);
      paramCharSequence1 = new SpannableString(paramCharSequence2);
      paramCharSequence1.setSpan(new CustomTypefaceSpan("", paramTypeface), 0, paramCharSequence1.length(), 33);
      try
      {
        paramA.b(paramCharSequence1);
        return;
      }
      catch (IllegalArgumentException paramTypeface)
      {
        paramTypeface.printStackTrace();
        paramA.b(paramCharSequence2.toString());
      }
    }
  }
  
  @TargetApi(19)
  public static void a(DrawerLayout paramDrawerLayout, Activity paramActivity, androidx.appcompat.app.a paramA, int paramInt)
  {
    try
    {
      paramA.a(new ColorDrawable(paramActivity.getResources().getColor(paramInt)));
      paramDrawerLayout.setStatusBarBackgroundColor(paramActivity.getResources().getColor(paramInt));
      return;
    }
    finally
    {
      paramDrawerLayout = finally;
      throw paramDrawerLayout;
    }
  }
  
  public static void a(b paramB, int paramInt, String paramString, File paramFile)
  {
    if (paramFile.exists()) {
      paramFile.delete();
    }
    paramB.b(paramInt);
    if (!paramString.isEmpty()) {
      paramB.a(new d(paramString, ""));
    }
  }
  
  public static boolean a(Activity paramActivity, String paramString)
  {
    paramActivity = paramActivity.getPackageManager().getInstalledApplications(0).iterator();
    while (paramActivity.hasNext()) {
      if (((ApplicationInfo)paramActivity.next()).packageName.contains(paramString)) {
        return true;
      }
    }
    return false;
  }
  
  private static boolean a(Context paramContext, String paramString, int paramInt)
  {
    long l = PreferenceManager.getDefaultSharedPreferences(paramContext).getLong(paramString, 0L);
    return Calendar.getInstance().getTimeInMillis() - l > paramInt * 60 * 60 * 1000;
  }
  
  public static boolean a(View paramView, Configuration paramConfiguration)
  {
    int i = (int)TypedValue.applyDimension(1, 2.0F, paramView.getContext().getResources().getDisplayMetrics());
    return a(paramView, paramConfiguration, i * 18, i * 60, 0, false, false);
  }
  
  private static boolean a(View paramView, Configuration paramConfiguration, int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramView != null)
    {
      if ((paramView.getContext().getResources().getConfiguration().screenLayout & 0xF) == 3) {
        paramInt3 = 1;
      } else {
        paramInt3 = 0;
      }
      int i;
      if ((paramView.getContext().getResources().getConfiguration().screenLayout & 0xF) == 4) {
        i = 1;
      } else {
        i = 0;
      }
      paramView = paramView.getLayoutParams();
      if ((paramConfiguration.orientation == 2) && ((paramInt3 != 0) || (i != 0)))
      {
        a(paramView, paramInt2, paramInt2, paramInt1, 0);
        return true;
      }
      if ((paramConfiguration.orientation == 1) && (paramInt3 == 0) && (i == 0))
      {
        a(paramView, 0, 0, 0, 0);
        return false;
      }
      a(paramView, 0, 0, 0, 0);
      return false;
    }
    return false;
  }
  
  private static boolean a(View paramView, Configuration paramConfiguration, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (paramView != null)
    {
      int j;
      if ((paramView.getContext().getResources().getConfiguration().screenLayout & 0xF) == 3) {
        j = 1;
      } else {
        j = 0;
      }
      int k;
      if ((paramView.getContext().getResources().getConfiguration().screenLayout & 0xF) == 4) {
        k = 1;
      } else {
        k = 0;
      }
      int i;
      if (paramBoolean1) {
        i = paramInt3;
      } else {
        i = 0;
      }
      if (!paramBoolean2) {
        paramInt3 = 0;
      }
      if ((paramConfiguration.orientation == 2) && ((j != 0) || (k != 0)))
      {
        paramView.setPadding(paramInt2, paramInt1, paramInt2, i);
        return true;
      }
      if ((paramConfiguration.orientation == 1) && (j == 0) && (k == 0))
      {
        paramView.setPadding(0, 0, 0, paramInt3);
        return false;
      }
      if (paramConfiguration.orientation == 2) {
        paramInt3 = i;
      }
      paramView.setPadding(0, 0, 0, paramInt3);
      return false;
    }
    return false;
  }
  
  public static boolean a(View paramView, Configuration paramConfiguration, boolean paramBoolean)
  {
    if (paramView != null)
    {
      int i;
      if ((paramView.getContext().getResources().getConfiguration().screenLayout & 0xF) == 3) {
        i = 1;
      } else {
        i = 0;
      }
      int j;
      if ((paramView.getContext().getResources().getConfiguration().screenLayout & 0xF) == 4) {
        j = 1;
      } else {
        j = 0;
      }
      int k = (int)TypedValue.applyDimension(1, 2.0F, paramView.getContext().getResources().getDisplayMetrics());
      paramView = paramView.getLayoutParams();
      if ((paramConfiguration.orientation == 2) && ((i != 0) || (j != 0)))
      {
        j = k * 60;
        i = k * 18;
        if (!paramBoolean) {
          i = 0;
        }
        a(paramView, j, j, i, 0);
        return true;
      }
      if ((paramConfiguration.orientation == 1) && (i == 0) && (j == 0))
      {
        a(paramView, 0, 0, 0, 0);
        return false;
      }
      a(paramView, 0, 0, 0, 0);
      return false;
    }
    return false;
  }
  
  public static boolean a(View paramView, Configuration paramConfiguration, boolean paramBoolean1, boolean paramBoolean2)
  {
    return a(paramView, paramConfiguration, true, paramBoolean1, paramBoolean2);
  }
  
  public static boolean a(View paramView, Configuration paramConfiguration, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    if (paramView != null)
    {
      int k;
      if ((paramView.getContext().getResources().getConfiguration().screenLayout & 0xF) == 3) {
        k = 1;
      } else {
        k = 0;
      }
      int m;
      if ((paramView.getContext().getResources().getConfiguration().screenLayout & 0xF) == 4) {
        m = 1;
      } else {
        m = 0;
      }
      int n = (int)TypedValue.applyDimension(1, 2.0F, paramView.getContext().getResources().getDisplayMetrics());
      double d1;
      int i;
      if (paramBoolean2)
      {
        d1 = n * 36;
        Double.isNaN(d1);
        i = (int)(d1 * 1.5D);
      }
      else
      {
        i = 0;
      }
      int j;
      if (paramBoolean3)
      {
        d1 = n * 36;
        Double.isNaN(d1);
        j = (int)(d1 * 1.5D);
      }
      else
      {
        j = 0;
      }
      if ((paramConfiguration.orientation == 2) && ((k != 0) || (m != 0)))
      {
        k = n * 60;
        j = n * 18;
        if (!paramBoolean1) {
          j = 0;
        }
        paramView.setPadding(k, j, k, i);
        return true;
      }
      if ((paramConfiguration.orientation == 1) && (k == 0) && (m == 0))
      {
        paramView.setPadding(0, 0, 0, j);
        return false;
      }
      if (paramConfiguration.orientation != 2) {
        i = j;
      }
      paramView.setPadding(0, 0, 0, i);
      return false;
    }
    return false;
  }
  
  public static boolean a(String paramString)
  {
    paramString = FilenameUtils.getExtension(paramString).toLowerCase();
    List localList = Arrays.asList(c);
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(".");
    localStringBuilder.append(paramString);
    return localList.contains(localStringBuilder.toString());
  }
  
  public static boolean a(Locale paramLocale)
  {
    return (paramLocale.equals(Locale.CHINESE)) || (paramLocale.equals(Locale.CHINA)) || (paramLocale.equals(Locale.JAPAN)) || (paramLocale.equals(Locale.JAPANESE)) || (paramLocale.equals(Locale.TRADITIONAL_CHINESE)) || (paramLocale.equals(Locale.TAIWAN)) || (paramLocale.equals(Locale.SIMPLIFIED_CHINESE)) || (paramLocale.equals(Locale.KOREAN)) || (paramLocale.equals(Locale.KOREA));
  }
  
  private static String aA(Context paramContext)
  {
    return C(paramContext, "BACKUP");
  }
  
  public static int aa(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getInt(c("member-discount-percent"), 0);
  }
  
  public static long ab(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getLong(c("radical-member-datetime"), -1L);
  }
  
  public static long ac(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getLong(c("radical-premium-datetime"), -1L);
  }
  
  public static int ad(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getInt(c("premium-discount-percent"), 0);
  }
  
  public static String ae(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getString(c("theme"), "default");
  }
  
  public static void af(Context paramContext)
  {
    PreferenceManager.getDefaultSharedPreferences(paramContext).edit().putInt("firstrun", 139).commit();
  }
  
  public static boolean ag(Context paramContext)
  {
    paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext);
    boolean bool = false;
    if (paramContext.getInt("firstrun", 0) != 139) {
      bool = true;
    }
    return bool;
  }
  
  public static String ah(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getString("oauth_token", "");
  }
  
  public static String ai(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getString("oauth_verifier", "");
  }
  
  public static String aj(Context paramContext)
  {
    return paramContext.getSharedPreferences("wp", 0).getString("wp-username", "");
  }
  
  public static String ak(Context paramContext)
  {
    return paramContext.getSharedPreferences("wp", 0).getString("wp-password", "");
  }
  
  public static String al(Context paramContext)
  {
    return paramContext.getSharedPreferences("wp", 0).getString("wp-xmlrpc", "");
  }
  
  public static boolean am(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean("wants_lock_publish", false);
  }
  
  public static boolean an(Context paramContext)
  {
    return a(paramContext, "feed-dl-today", 3);
  }
  
  public static void ao(Context paramContext)
  {
    t(paramContext, "feed-dl-today");
  }
  
  public static boolean ap(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    boolean bool = false;
    try
    {
      int i = localPackageManager.getPackageInfo(paramContext.getPackageName(), 0).applicationInfo.flags;
      if ((i & 0x40000) == 262144) {
        bool = true;
      }
      return bool;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public static File aq(Context paramContext)
  {
    return new File(paramContext.getExternalFilesDir(null), "log.txt");
  }
  
  public static boolean ar(Context paramContext)
  {
    return m.e(paramContext);
  }
  
  public static String as(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getString(c("cloud-purchase-sku"), "");
  }
  
  public static String at(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getString(c("cloud-purchase-token"), "");
  }
  
  public static boolean au(Context paramContext)
  {
    paramContext = m.b(paramContext);
    return (paramContext != null) && (paramContext.equals(c("com.journey.sub.ultimate_month_2018")));
  }
  
  public static boolean av(Context paramContext)
  {
    paramContext = m.b(paramContext);
    return (paramContext != null) && (paramContext.equals(c("com.journey.sub.ultimate_year_2018")));
  }
  
  public static void aw(Context paramContext)
  {
    if ((paramContext != null) && (!TextUtils.isEmpty(S(paramContext)))) {
      try
      {
        paramContext.startService(new Intent(paramContext, SyncService.class));
        return;
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
      }
    }
  }
  
  private static String ax(Context paramContext)
  {
    return C(paramContext, "PHOTOS");
  }
  
  private static String ay(Context paramContext)
  {
    return C(paramContext, "PROFILE");
  }
  
  private static String az(Context paramContext)
  {
    return C(paramContext, "WALLPAPER");
  }
  
  public static int b(boolean paramBoolean)
  {
    if (paramBoolean) {
      return 2131951820;
    }
    return 2131951821;
  }
  
  private static Bitmap b(File paramFile, int paramInt)
    throws OutOfMemoryError
  {
    paramFile = paramFile.getAbsolutePath();
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    int i = 1;
    localOptions.inJustDecodeBounds = true;
    BitmapFactory.decodeFile(paramFile, localOptions);
    int j = localOptions.outHeight;
    int k = localOptions.outWidth;
    localOptions.inPreferredConfig = Bitmap.Config.RGB_565;
    if (j > paramInt) {
      i = Math.round(j / paramInt);
    }
    localOptions.inSampleSize = i;
    localOptions.inJustDecodeBounds = false;
    paramFile = BitmapFactory.decodeFile(paramFile, localOptions);
    if (paramFile != null) {
      return Bitmap.createBitmap(paramFile, 0, 0, paramFile.getWidth(), paramFile.getHeight());
    }
    return null;
  }
  
  public static com.journey.app.object.e b(AssetManager paramAssetManager, String paramString)
  {
    if (paramString.equals("0")) {
      return new com.journey.app.object.e(r.a(paramAssetManager, "typeface/Archer-Semibold-Pro.otf"), r.a(paramAssetManager, "typeface/Archer-Bold-Pro.otf"), r.a(paramAssetManager, "typeface/Archer-SembdItal-Pro.otf"), r.a(paramAssetManager, "typeface/Archer-BoldItal-Pro.otf"));
    }
    if (paramString.equals("1")) {
      return new com.journey.app.object.e(r.a(paramAssetManager, "typeface/IdealSansSSm-Book.otf"), r.a(paramAssetManager, "typeface/IdealSansSSm-Bold.otf"), r.a(paramAssetManager, "typeface/IdealSansSSm-BookItalic.otf"), r.a(paramAssetManager, "typeface/IdealSansSSm-BoldItalic.otf"));
    }
    if (paramString.equals("2")) {
      return new com.journey.app.object.e(Typeface.SANS_SERIF, Typeface.create(Typeface.SANS_SERIF, 1), Typeface.create(Typeface.SANS_SERIF, 2), Typeface.create(Typeface.SANS_SERIF, 3));
    }
    if (paramString.equals("3")) {
      return new com.journey.app.object.e(r.a(paramAssetManager, "typeface/RobotoSlab-Regular.ttf"));
    }
    if (paramString.equals("4")) {
      return new com.journey.app.object.e(r.a(paramAssetManager, "typeface/Merriweather-Regular.ttf"), r.a(paramAssetManager, "typeface/Merriweather-Italic.ttf"), 2);
    }
    if (paramString.equals("5")) {
      return new com.journey.app.object.e(r.a(paramAssetManager, "typeface/Verlag-Book.otf"), r.a(paramAssetManager, "typeface/Verlag-Bold.otf"), r.a(paramAssetManager, "typeface/Verlag-BookItalic.otf"), r.a(paramAssetManager, "typeface/Verlag-BoldItalic.otf"));
    }
    return new com.journey.app.object.e(Typeface.SANS_SERIF, Typeface.create(Typeface.SANS_SERIF, 1), Typeface.create(Typeface.SANS_SERIF, 2), Typeface.create(Typeface.SANS_SERIF, 3));
  }
  
  public static File b()
  {
    File localFile = new File(new File(Environment.getExternalStorageDirectory(), "Pictures"), "Journey");
    if (!localFile.exists()) {
      localFile.mkdirs();
    }
    return localFile;
  }
  
  public static File b(Context paramContext, String paramString)
  {
    return new File(t(paramContext), paramString);
  }
  
  public static String b(Date paramDate)
  {
    return new SimpleDateFormat("d", Locale.getDefault()).format(paramDate);
  }
  
  public static String b(Date paramDate, TimeZone paramTimeZone)
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("EEE", Locale.getDefault());
    if (paramTimeZone != null) {
      localSimpleDateFormat.setTimeZone(paramTimeZone);
    }
    return localSimpleDateFormat.format(paramDate);
  }
  
  public static void b(Activity paramActivity, String paramString)
  {
    androidx.browser.a.a localA = new androidx.browser.a.a.a().a(paramActivity.getResources().getColor(2131099890)).b(-1).a(true).a();
    try
    {
      localA.a(paramActivity, Uri.parse(paramString));
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
      ab.a(paramActivity, 5);
    }
  }
  
  public static void b(Activity paramActivity, String paramString, ArrayList<String> paramArrayList, ArrayList<Uri> paramArrayList1, boolean paramBoolean)
  {
    if ((paramArrayList1 != null) && (paramArrayList1.size() > 0))
    {
      int i = 0;
      Uri localUri = (Uri)paramArrayList1.get(0);
      String str = p.a(localUri.getPath());
      paramArrayList1 = paramString.trim();
      if (paramBoolean) {
        paramArrayList1 = Html.fromHtml(paramString).toString().trim();
      }
      paramArrayList.add("journeyapp");
      while (i < paramArrayList.size())
      {
        paramString = new StringBuilder();
        paramString.append("#");
        paramString.append((String)paramArrayList.get(i));
        paramArrayList.set(i, paramString.toString());
        i += 1;
      }
      paramString = new StringBuilder();
      paramString.append(paramArrayList1);
      paramString.append("\n");
      paramString.append(TextUtils.join(" ", paramArrayList));
      paramString = paramString.toString();
      paramArrayList = new Intent("android.intent.action.SEND");
      paramArrayList.setType(str);
      paramArrayList.putExtra("android.intent.extra.STREAM", localUri);
      paramArrayList.putExtra("android.intent.extra.TEXT", paramString);
      paramArrayList.setPackage("com.instagram.android");
      paramString = Intent.createChooser(paramArrayList, paramActivity.getResources().getString(2131886688));
      a(paramActivity.getApplicationContext(), paramString, localUri);
      paramActivity.startActivity(paramString);
    }
  }
  
  public static void b(Activity paramActivity, boolean paramBoolean)
  {
    Drawable localDrawable;
    if ((paramActivity != null) && ((paramActivity instanceof ac))) {
      localDrawable = ((ac)paramActivity).r_().getOverflowIcon();
    } else {
      localDrawable = null;
    }
    if (localDrawable != null) {
      localDrawable.setColorFilter(new PorterDuffColorFilter(a(paramActivity, paramBoolean), PorterDuff.Mode.MULTIPLY));
    }
  }
  
  public static void b(Context paramContext, int paramInt)
  {
    if ((paramInt < 0) && (paramInt > 23)) {
      return;
    }
    PreferenceManager.getDefaultSharedPreferences(paramContext).edit().putInt("reminder_hour_2", paramInt).commit();
  }
  
  public static void b(Context paramContext, boolean paramBoolean)
  {
    PreferenceManager.getDefaultSharedPreferences(paramContext).edit().putBoolean("wants-fingerprint", paramBoolean).commit();
  }
  
  public static boolean b(Activity paramActivity)
  {
    return a(paramActivity, "com.google.android.apps.plus");
  }
  
  public static boolean b(Context paramContext)
  {
    return (paramContext.getResources().getConfiguration().screenLayout & 0xF) >= 3;
  }
  
  public static boolean b(Context paramContext, int paramInt1, int paramInt2)
  {
    long l1 = ab(paramContext);
    long l2 = a(l1);
    if (l2 <= new Date().getTime())
    {
      Calendar localCalendar = Calendar.getInstance();
      localCalendar.setTime(new Date());
      if (new Date().getTime() - l2 <= 14400000L) {
        paramInt1 = paramInt2;
      }
      localCalendar.add(10, paramInt1);
      PreferenceManager.getDefaultSharedPreferences(paramContext).edit().putLong(c("radical-member-datetime"), localCalendar.getTime().getTime()).commit();
      return true;
    }
    paramContext = new StringBuilder();
    paramContext.append("Next radical time: ");
    paramContext.append(new Date(l1));
    Log.d("Helper", paramContext.toString());
    return false;
  }
  
  public static boolean b(View paramView, Configuration paramConfiguration)
  {
    return a(paramView, paramConfiguration, 0, (int)TypedValue.applyDimension(1, 2.0F, paramView.getContext().getResources().getDisplayMetrics()) * 60, 0);
  }
  
  public static boolean b(String paramString)
  {
    paramString = FilenameUtils.getExtension(paramString).toLowerCase();
    ArrayList localArrayList = new ArrayList(Arrays.asList(d));
    localArrayList.addAll(new ArrayList(Arrays.asList(c)));
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(".");
    localStringBuilder.append(paramString);
    return localArrayList.contains(localStringBuilder.toString());
  }
  
  public static com.b.a.e c(boolean paramBoolean)
  {
    if (paramBoolean) {
      return com.b.a.e.b;
    }
    return com.b.a.e.a;
  }
  
  public static File c()
  {
    File localFile = new File(Environment.getExternalStorageDirectory(), "Download");
    if (!localFile.exists()) {
      localFile.mkdirs();
    }
    return localFile;
  }
  
  public static String c(String paramString)
  {
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append(paramString);
    ((StringBuilder)localObject).append(a());
    paramString = ((StringBuilder)localObject).toString();
    try
    {
      localObject = MessageDigest.getInstance("MD5");
      ((MessageDigest)localObject).update(paramString.getBytes());
      for (paramString = new BigInteger(1, ((MessageDigest)localObject).digest()).toString(16); paramString.length() < 32; paramString = ((StringBuilder)localObject).toString())
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("0");
        ((StringBuilder)localObject).append(paramString);
      }
      return paramString;
    }
    catch (NoSuchAlgorithmException paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }
  
  public static String c(Date paramDate)
  {
    return new SimpleDateFormat("MMMM", Locale.getDefault()).format(paramDate);
  }
  
  public static String c(Date paramDate, TimeZone paramTimeZone)
  {
    DateFormat localDateFormat = DateFormat.getDateInstance(1, Locale.getDefault());
    if (paramTimeZone != null) {
      localDateFormat.setTimeZone(paramTimeZone);
    }
    return localDateFormat.format(paramDate);
  }
  
  public static void c(Activity paramActivity, boolean paramBoolean)
  {
    a(paramActivity, new PorterDuffColorFilter(a(paramActivity, paramBoolean), PorterDuff.Mode.MULTIPLY));
  }
  
  public static void c(Context paramContext, int paramInt)
  {
    if ((paramInt < 0) && (paramInt > 59)) {
      return;
    }
    PreferenceManager.getDefaultSharedPreferences(paramContext).edit().putInt("reminder_min_2", paramInt).commit();
  }
  
  public static void c(Context paramContext, String paramString)
  {
    PreferenceManager.getDefaultSharedPreferences(paramContext).edit().putString("UserName", paramString).commit();
  }
  
  public static void c(Context paramContext, boolean paramBoolean)
  {
    PreferenceManager.getDefaultSharedPreferences(paramContext).edit().putBoolean("fit", paramBoolean).commit();
  }
  
  public static boolean c(Activity paramActivity)
  {
    return a(paramActivity, "com.instagram.android");
  }
  
  @TargetApi(17)
  public static boolean c(Context paramContext)
  {
    return (Build.VERSION.SDK_INT >= 17) && (paramContext.getResources().getConfiguration().getLayoutDirection() == 1);
  }
  
  public static boolean c(Context paramContext, int paramInt1, int paramInt2)
  {
    long l1 = ac(paramContext);
    long l2 = a(l1);
    if (l2 <= new Date().getTime())
    {
      Calendar localCalendar = Calendar.getInstance();
      localCalendar.setTime(new Date());
      if (new Date().getTime() - l2 <= 14400000L) {
        paramInt1 = paramInt2;
      }
      localCalendar.add(10, paramInt1);
      paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext);
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Setting Next radical time: ");
      localStringBuilder.append(localCalendar.getTime());
      Log.d("Helper", localStringBuilder.toString());
      paramContext.edit().putLong(c("radical-premium-datetime"), localCalendar.getTime().getTime()).commit();
      return true;
    }
    paramContext = new StringBuilder();
    paramContext.append("Next radical time: ");
    paramContext.append(new Date(l1));
    Log.d("Helper", paramContext.toString());
    return false;
  }
  
  public static String d(String paramString)
  {
    if (paramString != null)
    {
      paramString = new StringTokenizer(paramString);
      StringBuffer localStringBuffer = new StringBuffer();
      while (paramString.hasMoreTokens())
      {
        String str = paramString.nextToken();
        localStringBuffer.append(str.substring(0, 1).toUpperCase(Locale.US));
        localStringBuffer.append(str.substring(1));
        localStringBuffer.append(' ');
      }
      return localStringBuffer.toString().trim();
    }
    return "";
  }
  
  public static String d(Date paramDate)
  {
    return b(paramDate, null);
  }
  
  public static void d(Activity paramActivity)
  {
    paramActivity.startActivityForResult(new com.google.android.gms.appinvite.a.a(paramActivity.getResources().getString(2131886661)).a(String.format(paramActivity.getResources().getString(2131886583), new Object[] { paramActivity.getResources().getString(2131886154) })).a(Uri.parse("https://f459h.app.goo.gl/go")).a(1, "258745827837-dk8s36vl5csmungbp05v2imk8drttd3d.apps.googleusercontent.com").a(), 0);
  }
  
  public static void d(Activity paramActivity, boolean paramBoolean)
  {
    a(paramActivity, paramBoolean, null);
  }
  
  public static void d(Context paramContext, int paramInt)
  {
    PreferenceManager.getDefaultSharedPreferences(paramContext).edit().putInt(c("member-discount-percent"), paramInt).commit();
  }
  
  public static void d(Context paramContext, String paramString)
  {
    PreferenceManager.getDefaultSharedPreferences(paramContext).edit().putString("ProfilePicLoc", paramString).commit();
  }
  
  public static void d(Context paramContext, boolean paramBoolean)
  {
    PreferenceManager.getDefaultSharedPreferences(paramContext).edit().putBoolean("sync", paramBoolean).commit();
  }
  
  public static boolean d()
  {
    return Locale.getDefault().getISO3Language().equals(Locale.ENGLISH.getISO3Language());
  }
  
  public static boolean d(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean("wants-fingerprint", false);
  }
  
  public static String e(Date paramDate)
  {
    return c(paramDate, null);
  }
  
  public static JSONObject e(String paramString)
    throws JSONException
  {
    JSONObject localJSONObject = new JSONObject();
    localJSONObject.put("text", paramString);
    return localJSONObject;
  }
  
  public static void e(Activity paramActivity, boolean paramBoolean)
  {
    a(paramActivity, paramBoolean, -1);
  }
  
  public static void e(Context paramContext, int paramInt)
  {
    PreferenceManager.getDefaultSharedPreferences(paramContext).edit().putInt(c("premium-discount-percent"), paramInt).commit();
  }
  
  public static void e(Context paramContext, String paramString)
  {
    PreferenceManager.getDefaultSharedPreferences(paramContext).edit().putString("Google-Acc-Name", paramString).commit();
    paramContext = new StringBuilder();
    paramContext.append("google acc name set: ");
    paramContext.append(paramString);
    Log.d("", paramContext.toString());
  }
  
  public static void e(Context paramContext, boolean paramBoolean)
  {
    PreferenceManager.getDefaultSharedPreferences(paramContext).edit().putBoolean("nightmode", paramBoolean).commit();
  }
  
  public static boolean e()
  {
    return Build.VERSION.SDK_INT >= 17;
  }
  
  public static boolean e(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean("auto_location", true);
  }
  
  public static int f(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getInt("addon-seen-int", 0);
  }
  
  public static int f(Context paramContext, int paramInt)
  {
    float f = paramContext.getResources().getDisplayMetrics().density;
    return Math.round(paramInt * f);
  }
  
  public static int f(String paramString)
  {
    int i = 0;
    if ((paramString != null) && (paramString.length() > 0))
    {
      paramString = paramString.split(" |\n|\t");
      int m = paramString.length;
      int k;
      for (int j = 0; i < m; j = k)
      {
        Object localObject = paramString[i];
        k = j;
        if (localObject.length() > 0)
        {
          k = j;
          if (!localObject.equals("-"))
          {
            k = j;
            if (!localObject.equals("—")) {
              k = j + 1;
            }
          }
        }
        i += 1;
      }
      return j;
    }
    return 0;
  }
  
  public static String f(Date paramDate)
  {
    return new SimpleDateFormat("EEEE", Locale.getDefault()).format(paramDate);
  }
  
  public static void f(Context paramContext, String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("google changes start page token set: ");
    localStringBuilder.append(paramString);
    Log.d("", localStringBuilder.toString());
    PreferenceManager.getDefaultSharedPreferences(paramContext).edit().putString("Google-Changes-Page-Token", paramString).commit();
  }
  
  public static void f(Context paramContext, boolean paramBoolean)
  {
    PreferenceManager.getDefaultSharedPreferences(paramContext).edit().putString(c("bought1"), c(String.valueOf(paramBoolean))).commit();
  }
  
  public static boolean f()
  {
    return Build.VERSION.SDK_INT >= 19;
  }
  
  public static int g(String paramString)
  {
    if ((paramString != null) && (paramString.length() > 0)) {
      return paramString.length();
    }
    return 0;
  }
  
  public static Drawable g(Context paramContext, int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("a");
    localStringBuilder.append(String.valueOf(paramInt));
    return v(paramContext, localStringBuilder.toString());
  }
  
  public static void g(Context paramContext)
  {
    PreferenceManager.getDefaultSharedPreferences(paramContext).edit().putInt("addon-seen-int", f(paramContext) + 1).commit();
  }
  
  public static void g(Context paramContext, String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("last sync status set: ");
    localStringBuilder.append(paramString);
    Log.d("", localStringBuilder.toString());
    PreferenceManager.getDefaultSharedPreferences(paramContext).edit().putString("Last-Sync-Status-2", paramString).commit();
  }
  
  public static void g(Context paramContext, boolean paramBoolean)
  {
    PreferenceManager.getDefaultSharedPreferences(paramContext).edit().putString(c("bought3"), c(String.valueOf(paramBoolean))).commit();
  }
  
  public static boolean g()
  {
    return Build.VERSION.SDK_INT >= 21;
  }
  
  public static int h(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getInt("rate-seen-int", 0);
  }
  
  public static String h(Context paramContext, int paramInt)
  {
    try
    {
      paramContext = paramContext.getAssets();
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("images/activities/a");
      localStringBuilder.append(paramInt);
      localStringBuilder.append(".svg");
      paramContext = p.a(paramContext.open(localStringBuilder.toString()));
      return paramContext;
    }
    catch (IOException paramContext)
    {
      paramContext.printStackTrace();
    }
    return "";
  }
  
  public static String h(String paramString)
  {
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append(a("Verlag", "typeface/Verlag-Book.otf", 0));
    ((StringBuilder)localObject).append(a("Verlag", "typeface/Verlag-Bold.otf", 1));
    ((StringBuilder)localObject).append(a("Verlag", "typeface/Verlag-BoldItalic.otf", 3));
    ((StringBuilder)localObject).append(a("Verlag", "typeface/Verlag-BookItalic.otf", 2));
    localObject = ((StringBuilder)localObject).toString();
    StringBuilder localStringBuilder;
    if (paramString.equals("0"))
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append((String)localObject);
      localStringBuilder.append(a(i(paramString), "typeface/Archer-Semibold-Pro.otf", 0));
      localStringBuilder.append(a(i(paramString), "typeface/Archer-Bold-Pro.otf", 1));
      localStringBuilder.append(a(i(paramString), "typeface/Archer-BoldItal-Pro.otf", 3));
      localStringBuilder.append(a(i(paramString), "typeface/Archer-SembdItal-Pro.otf", 2));
      return localStringBuilder.toString();
    }
    if (paramString.equals("1"))
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append((String)localObject);
      localStringBuilder.append(a(i(paramString), "typeface/IdealSansSSm-Book.otf", 0));
      localStringBuilder.append(a(i(paramString), "typeface/IdealSansSSm-Bold.otf", 1));
      localStringBuilder.append(a(i(paramString), "typeface/IdealSansSSm-BoldItalic.otf", 3));
      localStringBuilder.append(a(i(paramString), "typeface/IdealSansSSm-BookItalic.otf", 2));
      return localStringBuilder.toString();
    }
    if (paramString.equals("2")) {
      return localObject;
    }
    if (paramString.equals("3"))
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append((String)localObject);
      localStringBuilder.append(a(i(paramString), "typeface/RobotoSlab-Regular.ttf", 0));
      return localStringBuilder.toString();
    }
    if (paramString.equals("4"))
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append((String)localObject);
      localStringBuilder.append(a(i(paramString), "typeface/Merriweather-Regular.ttf", 0));
      localStringBuilder.append(a(i(paramString), "typeface/Merriweather-Italic.ttf", 2));
      return localStringBuilder.toString();
    }
    if (paramString.equals("5")) {
      return localObject;
    }
    return localObject;
  }
  
  public static void h(Context paramContext, String paramString)
  {
    PreferenceManager.getDefaultSharedPreferences(paramContext).edit().putString("pin", paramString).commit();
  }
  
  public static void h(Context paramContext, boolean paramBoolean)
  {
    PreferenceManager.getDefaultSharedPreferences(paramContext).edit().putBoolean("wants_lock_publish", paramBoolean).commit();
  }
  
  public static boolean h()
  {
    return Build.VERSION.SDK_INT >= 23;
  }
  
  public static String i(String paramString)
  {
    if (paramString.equals("0")) {
      return "Archer";
    }
    if (paramString.equals("1")) {
      return "IdealSans";
    }
    if (paramString.equals("2")) {
      return "sans-serif";
    }
    if (paramString.equals("3")) {
      return "RobotoSlab";
    }
    if (paramString.equals("4")) {
      return "Merriweather";
    }
    if (paramString.equals("5")) {
      return "Verlag";
    }
    return "sans-serif";
  }
  
  public static void i(Context paramContext)
  {
    PreferenceManager.getDefaultSharedPreferences(paramContext).edit().putInt("rate-seen-int", h(paramContext) + 1).commit();
  }
  
  public static void i(Context paramContext, String paramString)
  {
    PreferenceManager.getDefaultSharedPreferences(paramContext).edit().putString(c("bought2"), c(paramString)).commit();
  }
  
  public static boolean i()
  {
    return Build.VERSION.SDK_INT >= 24;
  }
  
  public static String j(String paramString)
  {
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("SHA-256");
      if (f()) {
        paramString = localMessageDigest.digest(paramString.getBytes(StandardCharsets.UTF_8));
      } else {
        paramString = localMessageDigest.digest(paramString.getBytes("UTF-8"));
      }
      paramString = a(paramString);
      return paramString;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return "";
  }
  
  public static void j(Context paramContext)
  {
    PreferenceManager.getDefaultSharedPreferences(paramContext).edit().putInt("rate-called-int", 13330).commit();
  }
  
  public static void j(Context paramContext, String paramString)
  {
    PreferenceManager.getDefaultSharedPreferences(paramContext).edit().putString(c("theme"), paramString).commit();
  }
  
  public static boolean j()
  {
    return Build.VERSION.SDK_INT >= 25;
  }
  
  public static void k(Context paramContext, String paramString)
  {
    PreferenceManager.getDefaultSharedPreferences(paramContext).edit().putString("oauth_token", paramString).commit();
  }
  
  public static boolean k()
  {
    return Build.VERSION.SDK_INT >= 26;
  }
  
  public static boolean k(Context paramContext)
  {
    paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext);
    boolean bool = false;
    if (paramContext.getInt("rate-called-int", 0) == 13330) {
      bool = true;
    }
    return bool;
  }
  
  public static int l()
  {
    int i = Calendar.getInstance().get(11);
    if (((i > 19) && (i <= 24)) || ((i >= 0) && (i <= 5))) {
      return 1;
    }
    if ((i > 5) && (i <= 7)) {
      return 2;
    }
    if ((i > 7) && (i <= 17)) {
      return 3;
    }
    if ((i > 17) && (i <= 19)) {
      return 4;
    }
    return 3;
  }
  
  public static void l(Context paramContext)
  {
    PreferenceManager.getDefaultSharedPreferences(paramContext).edit().putInt("invite-called-int", 456).commit();
  }
  
  public static void l(Context paramContext, String paramString)
  {
    PreferenceManager.getDefaultSharedPreferences(paramContext).edit().putString("oauth_verifier", paramString).commit();
  }
  
  public static String m(Context paramContext)
  {
    return C(paramContext, "CACHE");
  }
  
  public static void m(Context paramContext, String paramString)
  {
    PreferenceManager.getDefaultSharedPreferences(paramContext).edit().putString("oauth_token_twitter", paramString).commit();
  }
  
  public static String n(Context paramContext)
  {
    return C(paramContext, "COMPRESS");
  }
  
  public static void n(Context paramContext, String paramString)
  {
    PreferenceManager.getDefaultSharedPreferences(paramContext).edit().putString("oauth_verifier_twitter", paramString).commit();
  }
  
  public static String o(Context paramContext)
  {
    return C(paramContext, "PROMO");
  }
  
  public static void o(Context paramContext, String paramString)
  {
    paramContext.getSharedPreferences("wp", 0).edit().putString("wp-username", paramString).commit();
  }
  
  public static File p(Context paramContext)
  {
    return new File(ay(paramContext), "user.png");
  }
  
  public static void p(Context paramContext, String paramString)
  {
    paramContext.getSharedPreferences("wp", 0).edit().putString("wp-password", paramString).commit();
  }
  
  public static File q(Context paramContext)
  {
    return new File(az(paramContext), "wallpaper.jpg");
  }
  
  public static void q(Context paramContext, String paramString)
  {
    paramContext.getSharedPreferences("wp", 0).edit().putString("wp-xmlrpc", paramString).commit();
  }
  
  public static String r(Context paramContext)
  {
    return new File(aA(paramContext), "feed.json").getAbsolutePath();
  }
  
  public static void r(Context paramContext, String paramString)
  {
    paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext).edit();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("shown-festive-promo-");
    localStringBuilder.append(paramString);
    paramContext.putBoolean(localStringBuilder.toString(), true).commit();
  }
  
  public static String s(Context paramContext)
  {
    return new File(aA(paramContext), "backup.json").getAbsolutePath();
  }
  
  public static boolean s(Context paramContext, String paramString)
  {
    paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext);
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("shown-festive-promo-");
    localStringBuilder.append(paramString);
    return paramContext.getBoolean(localStringBuilder.toString(), false);
  }
  
  public static String t(Context paramContext)
  {
    paramContext = paramContext.getExternalFilesDir(null);
    if (paramContext == null) {
      return "";
    }
    return paramContext.getAbsolutePath();
  }
  
  public static void t(Context paramContext, String paramString)
  {
    paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext);
    Calendar localCalendar = Calendar.getInstance();
    paramContext.edit().putLong(paramString, localCalendar.getTimeInMillis()).commit();
  }
  
  public static File u(Context paramContext)
  {
    paramContext = new File(C(paramContext, "URI_PHOTOS"));
    if (!paramContext.exists()) {
      paramContext.mkdirs();
    }
    return paramContext;
  }
  
  public static void u(Context paramContext, String paramString)
  {
    PreferenceManager.getDefaultSharedPreferences(paramContext).edit().putString("fit-seen-id", paramString).commit();
  }
  
  public static Drawable v(Context paramContext, String paramString)
  {
    int i = paramContext.getResources().getIdentifier(paramString, "drawable", paramContext.getPackageName());
    if (i > 0) {
      return androidx.appcompat.a.a.a.b(paramContext, i);
    }
    return androidx.appcompat.a.a.a.b(paramContext, 2131230921);
  }
  
  public static void v(Context paramContext)
  {
    paramContext = new File(m(paramContext));
    if ((paramContext.exists()) && (paramContext.isDirectory()))
    {
      paramContext = paramContext.listFiles();
      int j = paramContext.length;
      int i = 0;
      while (i < j)
      {
        Object localObject = paramContext[i];
        if (localObject.isFile()) {
          try
          {
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append("Deleting... ");
            localStringBuilder.append(localObject.getAbsolutePath());
            Log.d("Helper", localStringBuilder.toString());
            localObject.delete();
          }
          catch (Exception localException)
          {
            localException.printStackTrace();
          }
        }
        i += 1;
      }
    }
  }
  
  public static boolean w(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean("reminder_2", false);
  }
  
  public static boolean w(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getApplicationInfo(paramString, 0);
      return paramContext != null;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  public static String x(Context paramContext, String paramString)
    throws IOException
  {
    StringBuilder localStringBuilder = new StringBuilder();
    paramContext = new BufferedReader(new InputStreamReader(paramContext.getAssets().open(paramString), "UTF-8"));
    for (;;)
    {
      paramString = paramContext.readLine();
      if (paramString == null) {
        break;
      }
      localStringBuilder.append(paramString);
    }
    paramContext.close();
    return localStringBuilder.toString();
  }
  
  public static boolean x(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean("save_photo", true);
  }
  
  public static void y(Context paramContext, String paramString)
  {
    paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext).edit();
    String str = c("cloud-purchase-sku");
    if (paramString == null) {
      paramString = "";
    }
    paramContext.putString(str, paramString).commit();
  }
  
  public static boolean y(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean("throwback", true);
  }
  
  public static void z(Context paramContext, String paramString)
  {
    paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext).edit();
    String str = c("cloud-purchase-token");
    if (paramString == null) {
      paramString = "";
    }
    paramContext.putString(str, paramString).commit();
  }
  
  public static boolean z(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean("fit", false);
  }
}
