package aa.com.summit.beam.utils;

import aa.com.summit.beam.app.BeamApp;
import aa.com.summit.beam.receivers.BeamCallReceiver;
import aa.com.summit.beam.views.activity.HomeActivity;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.location.Address;
import android.media.ExifInterface;
import android.os.Binder;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Process;
import android.provider.Settings;
import android.provider.Settings.Global;
import android.provider.Telephony.Sms;
import android.renderscript.Allocation;
import android.renderscript.Allocation.MipmapControl;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.PhoneNumberUtils;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.util.DisplayMetrics;
import android.util.Patterns;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.webkit.URLUtil;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.a.a.h;
import com.google.android.gms.maps.model.LatLng;
import com.summit.portal.controllers.s;
import com.summit.utils.Log;
import com.summit.utils.k;
import com.verizon.bvolte.mms.db.Media;
import com.verizon.bvolte.mms.db.MediaType;
import com.verizon.bvolte.mms.db.MessageItem;
import com.verizon.bvolte.mms.db.MessageItem.GroupMode;
import com.verizon.bvolte.mms.db.MessageStatus;
import com.verizon.bvolte.mms.db.MessageType;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import nexos.callhistory.a.a;
import nexos.m.c.b;
import org.joda.time.DateTime;

public class m
{
  private static final String[] a = { "content://com.google.android", "content://com.sec.android", "content://com.android.chrome", "content://com.android.providers.downloads" };
  private static final Pattern b = Pattern.compile("\\s*(?:\\+?(\\d{1,3}))?([-. (]*(\\d{3})[-. )]*)?((\\d{3})[-. ]*(\\d{2,4})(?:[-.x ]*(\\d+))?)\\s*");
  
  public static int a(Context paramContext, float paramFloat)
  {
    return (int)(paramContext.getResources().getDisplayMetrics().density * paramFloat + 0.5F);
  }
  
  public static int a(Context paramContext, String paramString, boolean paramBoolean)
  {
    int i = 0;
    if (paramBoolean) {
      return Color.parseColor(paramContext.getString(2131689517));
    }
    if (a(paramString)) {
      return Color.parseColor(paramContext.getString(2131689518));
    }
    paramContext = paramContext.getString(2131689519).split("-");
    if (paramString != null) {}
    for (;;)
    {
      try
      {
        int j = Math.abs(paramString.hashCode()) % paramContext.length;
        if (j >= 0)
        {
          int k = paramContext.length;
          i = j;
          if (j < k) {}
        }
        else
        {
          i = 0;
        }
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
        continue;
      }
      return Color.parseColor(paramContext[i]);
      i = 0;
    }
  }
  
  public static int a(Context paramContext, HashMap paramHashMap)
  {
    String[] arrayOfString = paramContext.getString(2131689519).split("-");
    if (paramHashMap.size() >= arrayOfString.length) {
      return Color.parseColor(paramContext.getString(2131689518));
    }
    ArrayList localArrayList = new ArrayList();
    int j = arrayOfString.length;
    int i = 0;
    while (i < j)
    {
      localArrayList.add(Integer.valueOf(Color.parseColor(arrayOfString[i])));
      i += 1;
    }
    paramHashMap = paramHashMap.entrySet().iterator();
    while (paramHashMap.hasNext()) {
      localArrayList.remove((Integer)((Map.Entry)paramHashMap.next()).getValue());
    }
    if (localArrayList.size() > 0) {
      return ((Integer)localArrayList.get(0)).intValue();
    }
    return Color.parseColor(paramContext.getString(2131689518));
  }
  
  public static int a(File paramFile)
  {
    int i = 0;
    try
    {
      int j = new ExifInterface(paramFile.getAbsolutePath()).getAttributeInt("Orientation", 1);
      if (j == 8) {
        i = 270;
      }
      do
      {
        return i;
        if (j == 3) {
          return 180;
        }
      } while (j != 6);
      return 90;
    }
    catch (Exception paramFile)
    {
      paramFile.printStackTrace();
    }
    return 0;
  }
  
  public static Bitmap a(Bitmap paramBitmap, int paramInt)
  {
    Matrix localMatrix = new Matrix();
    localMatrix.postRotate(paramInt);
    return Bitmap.createBitmap(paramBitmap, 0, 0, paramBitmap.getWidth(), paramBitmap.getHeight(), localMatrix, true);
  }
  
  public static Bitmap a(Bitmap paramBitmap, ImageView paramImageView, int paramInt)
  {
    int k = paramBitmap.getWidth();
    int m = paramBitmap.getHeight();
    int i;
    int j;
    if (paramImageView != null)
    {
      i = paramImageView.getWidth();
      j = paramImageView.getHeight();
    }
    for (;;)
    {
      if ((i == 0) || (j == 0))
      {
        j = paramInt;
        i = paramInt;
        paramInt = j;
      }
      for (;;)
      {
        float f1 = k / m;
        float f2 = paramInt / i;
        paramImageView = new Rect();
        if (f1 == f2)
        {
          paramImageView.top = 0;
          paramImageView.bottom = m;
          paramImageView.left = 0;
          paramImageView.right = k;
        }
        for (;;)
        {
          Bitmap localBitmap = Bitmap.createBitmap(paramInt, i, Bitmap.Config.ARGB_8888);
          Canvas localCanvas = new Canvas(localBitmap);
          Paint localPaint = new Paint();
          Rect localRect = new Rect(0, 0, paramInt, i);
          localPaint.setAntiAlias(true);
          localPaint.setFilterBitmap(true);
          localPaint.setDither(true);
          localCanvas.drawARGB(0, 0, 0, 0);
          localPaint.setColor(-1);
          localCanvas.drawCircle(paramInt / 2, i / 2, paramInt / 2, localPaint);
          localPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
          localCanvas.drawBitmap(paramBitmap, paramImageView, localRect, localPaint);
          return localBitmap;
          if (f1 > f2)
          {
            j = (k - paramInt * m / i) / 2;
            paramImageView.top = 0;
            paramImageView.bottom = m;
            paramImageView.left = j;
            paramImageView.right = (k - j);
          }
          else
          {
            j = (m - i * k / paramInt) / 2;
            paramImageView.top = j;
            paramImageView.bottom = (m - j);
            paramImageView.left = 0;
            paramImageView.right = k;
          }
        }
        paramInt = i;
        i = j;
      }
      j = 0;
      i = 0;
    }
  }
  
  public static Bitmap a(View paramView, int paramInt1, int paramInt2)
  {
    try
    {
      paramView.measure(View.MeasureSpec.makeMeasureSpec(paramInt1, 1073741824), View.MeasureSpec.makeMeasureSpec(paramInt2, 1073741824));
      paramView.layout(0, 0, paramInt1, paramInt2);
      Bitmap localBitmap = Bitmap.createBitmap(paramInt1, paramInt2, Bitmap.Config.ARGB_8888);
      paramView.draw(new Canvas(localBitmap));
      return localBitmap;
    }
    catch (Exception paramView)
    {
      paramView.printStackTrace();
    }
    return null;
  }
  
  public static android.net.Uri a(LatLng paramLatLng, String paramString)
  {
    return android.net.Uri.parse("geo:0,0?q=" + paramLatLng.latitude + "," + paramLatLng.longitude + "(" + paramString + ")");
  }
  
  public static String a(Context paramContext, MessageItem paramMessageItem)
  {
    boolean bool = a(paramMessageItem);
    Log.a(new Object[] { "UICommons: getLastMsg: hasMedia=", Boolean.valueOf(paramMessageItem.hasMedia()), ", isDownloadingMedia=", Boolean.valueOf(bool) });
    if (bool)
    {
      if (paramMessageItem.isInbound()) {
        return paramContext.getString(2131690154);
      }
      return paramContext.getString(2131690155);
    }
    if (paramMessageItem.hasMedia(MediaType.IMAGE))
    {
      if (paramMessageItem.isInbound()) {
        return paramContext.getString(2131690150);
      }
      return paramContext.getString(2131690157);
    }
    if (paramMessageItem.hasMedia(MediaType.VIDEO))
    {
      if (paramMessageItem.isInbound()) {
        return paramContext.getString(2131690153);
      }
      return paramContext.getString(2131690160);
    }
    if (paramMessageItem.hasMedia(MediaType.VCARD))
    {
      if (paramMessageItem.isInbound()) {
        return paramContext.getString(2131690149);
      }
      return paramContext.getString(2131690156);
    }
    if (paramMessageItem.hasMedia(MediaType.LOCATION))
    {
      if (paramMessageItem.isInbound()) {
        return paramContext.getString(2131690151);
      }
      return paramContext.getString(2131690158);
    }
    if (paramMessageItem.hasMedia())
    {
      if (paramMessageItem.isInbound()) {
        return paramContext.getString(2131690152);
      }
      return paramContext.getString(2131690159);
    }
    return paramMessageItem.body;
  }
  
  public static String a(Context paramContext, a paramA)
  {
    return DateUtils.formatDateTime(paramContext, paramA.g, 1).replace("AM", "am").replace("PM", "pm");
  }
  
  public static String a(Context paramContext, b paramB)
  {
    return DateUtils.formatDateTime(paramContext, paramB.b, 1).replace("AM", "am").replace("PM", "pm");
  }
  
  public static String a(Address paramAddress)
  {
    Object localObject2 = "";
    if (paramAddress.getSubThoroughfare() != null) {
      localObject2 = "" + paramAddress.getSubThoroughfare();
    }
    Object localObject1 = localObject2;
    if (paramAddress.getThoroughfare() != null)
    {
      localObject1 = (String)localObject2 + " ";
      localObject1 = (String)localObject1 + paramAddress.getThoroughfare();
    }
    localObject2 = localObject1;
    if (paramAddress.getLocality() != null)
    {
      localObject1 = (String)localObject1 + " ";
      localObject2 = (String)localObject1 + paramAddress.getLocality();
    }
    localObject1 = localObject2;
    if (paramAddress.getAdminArea() != null)
    {
      localObject1 = (String)localObject2 + ", ";
      localObject1 = (String)localObject1 + paramAddress.getAdminArea();
    }
    localObject2 = localObject1;
    if (paramAddress.getPostalCode() != null)
    {
      localObject1 = (String)localObject1 + " ";
      localObject2 = (String)localObject1 + paramAddress.getPostalCode();
    }
    return localObject2;
  }
  
  public static String a(LatLng paramLatLng)
  {
    return "https://www.google.com/maps/@" + paramLatLng.latitude + "," + paramLatLng.longitude + ",15z";
  }
  
  public static String a(String paramString1, String paramString2)
  {
    return e(paramString1) + ", " + e(paramString2);
  }
  
  public static List a(List paramList)
  {
    if ((paramList != null) && (paramList.size() > 0))
    {
      int i = paramList.size() - 1;
      while (i > -1)
      {
        if (b((MessageItem)paramList.get(i))) {
          paramList.remove(i);
        }
        i -= 1;
      }
    }
    Log.a(new Object[] { "UICommons: getFilteredMessageItems: messageItemList=", paramList });
    return paramList;
  }
  
  public static void a(long paramLong, char[] paramArrayOfChar)
  {
    int j = (int)paramLong % 60;
    int k = (int)(paramLong / 60L) % 60;
    int m = (int)(paramLong / 3600L) % 99;
    int i;
    if (paramArrayOfChar.length > 5)
    {
      i = 3;
      if (m < 10)
      {
        paramArrayOfChar[0] = '0';
        paramArrayOfChar[1] = Character.forDigit(m, 10);
        paramArrayOfChar[2] = ':';
      }
    }
    for (;;)
    {
      if (k < 10)
      {
        paramArrayOfChar[i] = '0';
        paramArrayOfChar[(i + 1)] = Character.forDigit(k, 10);
      }
      for (;;)
      {
        paramArrayOfChar[(i + 2)] = ':';
        if (j >= 10) {
          break label202;
        }
        paramArrayOfChar[(i + 3)] = '0';
        paramArrayOfChar[(i + 4)] = Character.forDigit(j, 10);
        return;
        if (m > 99)
        {
          paramArrayOfChar[0] = '0';
          paramArrayOfChar[1] = '0';
          break;
        }
        paramArrayOfChar[0] = Character.forDigit(m / 10, 10);
        paramArrayOfChar[1] = Character.forDigit(m % 10, 10);
        break;
        paramArrayOfChar[i] = Character.forDigit(k / 10, 10);
        paramArrayOfChar[(i + 1)] = Character.forDigit(k % 10, 10);
      }
      label202:
      paramArrayOfChar[(i + 3)] = Character.forDigit(j / 10, 10);
      paramArrayOfChar[(i + 4)] = Character.forDigit(j % 10, 10);
      return;
      i = 0;
    }
  }
  
  public static void a(Activity paramActivity, String paramString)
  {
    try
    {
      Intent localIntent = new Intent("android.intent.action.VIEW", android.net.Uri.parse("market://details?id=" + paramString));
      if (a(paramActivity, localIntent))
      {
        paramActivity.startActivity(localIntent);
        return;
      }
      paramString = new Intent("android.intent.action.VIEW", android.net.Uri.parse("http://play.google.com/store/apps/details?id=" + paramString));
      if (a(paramActivity, paramString))
      {
        paramActivity.startActivity(paramString);
        return;
      }
    }
    catch (Exception paramActivity)
    {
      paramActivity.printStackTrace();
      return;
    }
    Toast.makeText(paramActivity, paramActivity.getString(2131690124), 1).show();
  }
  
  public static void a(Context paramContext)
  {
    HomeActivity.a(paramContext, false);
  }
  
  @SuppressLint({"NewApi"})
  public static void a(Context paramContext, Bitmap paramBitmap, float paramFloat)
  {
    if (paramContext != null)
    {
      Object localObject = RenderScript.create(paramContext);
      paramContext = Allocation.createFromBitmap((RenderScript)localObject, paramBitmap, Allocation.MipmapControl.MIPMAP_NONE, 1);
      Allocation localAllocation = Allocation.createTyped((RenderScript)localObject, paramContext.getType());
      localObject = ScriptIntrinsicBlur.create((RenderScript)localObject, Element.U8_4((RenderScript)localObject));
      ((ScriptIntrinsicBlur)localObject).setRadius(paramFloat);
      ((ScriptIntrinsicBlur)localObject).setInput(paramContext);
      ((ScriptIntrinsicBlur)localObject).forEach(localAllocation);
      localAllocation.copyTo(paramBitmap);
    }
  }
  
  public static void a(Context paramContext, View paramView)
  {
    Log.a(new Object[] { "CustomEditText: hideKeyboard: " });
    ((InputMethodManager)paramContext.getSystemService("input_method")).hideSoftInputFromWindow(paramView.getWindowToken(), 0);
  }
  
  public static void a(Context paramContext, String paramString)
  {
    try
    {
      Log.a(new Object[] { "UICommons: startCallWithNativePhoneApp: uri=", paramString });
      if (paramString != null)
      {
        String str;
        if (!paramString.startsWith("tel:"))
        {
          str = paramString;
          if (!paramString.startsWith("sip:")) {}
        }
        else
        {
          str = new nexos.Uri(paramString).c();
        }
        BeamCallReceiver.a();
        paramString = "tel:" + str;
        Log.a(new Object[] { "UICommons: startCallWithNativePhoneApp: uriStr=", paramString });
        paramString = new Intent("android.intent.action.CALL", android.net.Uri.parse(paramString));
        if (!(paramContext instanceof Activity)) {
          paramString.addFlags(268435456);
        }
        paramContext.startActivity(paramString);
      }
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  /* Error */
  public static void a(Bitmap paramBitmap, File paramFile)
  {
    // Byte code:
    //   0: new 619	java/io/FileOutputStream
    //   3: dup
    //   4: aload_1
    //   5: invokespecial 622	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   8: astore_2
    //   9: aload_2
    //   10: astore_1
    //   11: aload_0
    //   12: getstatic 628	android/graphics/Bitmap$CompressFormat:PNG	Landroid/graphics/Bitmap$CompressFormat;
    //   15: bipush 80
    //   17: aload_2
    //   18: invokevirtual 632	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
    //   21: pop
    //   22: aload_2
    //   23: ifnull +7 -> 30
    //   26: aload_2
    //   27: invokevirtual 635	java/io/FileOutputStream:close	()V
    //   30: return
    //   31: astore_0
    //   32: aload_0
    //   33: invokevirtual 636	java/io/IOException:printStackTrace	()V
    //   36: return
    //   37: astore_3
    //   38: aconst_null
    //   39: astore_0
    //   40: aload_0
    //   41: astore_1
    //   42: aload_3
    //   43: invokevirtual 91	java/lang/Exception:printStackTrace	()V
    //   46: aload_0
    //   47: ifnull -17 -> 30
    //   50: aload_0
    //   51: invokevirtual 635	java/io/FileOutputStream:close	()V
    //   54: return
    //   55: astore_0
    //   56: aload_0
    //   57: invokevirtual 636	java/io/IOException:printStackTrace	()V
    //   60: return
    //   61: astore_0
    //   62: aconst_null
    //   63: astore_1
    //   64: aload_1
    //   65: ifnull +7 -> 72
    //   68: aload_1
    //   69: invokevirtual 635	java/io/FileOutputStream:close	()V
    //   72: aload_0
    //   73: athrow
    //   74: astore_1
    //   75: aload_1
    //   76: invokevirtual 636	java/io/IOException:printStackTrace	()V
    //   79: goto -7 -> 72
    //   82: astore_0
    //   83: goto -19 -> 64
    //   86: astore_3
    //   87: aload_2
    //   88: astore_0
    //   89: goto -49 -> 40
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	92	0	paramBitmap	Bitmap
    //   0	92	1	paramFile	File
    //   8	80	2	localFileOutputStream	java.io.FileOutputStream
    //   37	6	3	localException1	Exception
    //   86	1	3	localException2	Exception
    // Exception table:
    //   from	to	target	type
    //   26	30	31	java/io/IOException
    //   0	9	37	java/lang/Exception
    //   50	54	55	java/io/IOException
    //   0	9	61	finally
    //   68	72	74	java/io/IOException
    //   11	22	82	finally
    //   42	46	82	finally
    //   11	22	86	java/lang/Exception
  }
  
  public static void a(View paramView, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (paramView == null) {}
    for (;;)
    {
      return;
      if (!(paramView instanceof ViewGroup))
      {
        if (paramView.isEnabled() != paramBoolean1)
        {
          paramView.setEnabled(paramBoolean1);
          if (!paramBoolean2)
          {
            if (paramBoolean1) {}
            for (float f = 1.0F;; f = 0.3F)
            {
              paramView.setAlpha(f);
              return;
            }
          }
          if (paramBoolean1) {}
          for (paramView = ObjectAnimator.ofFloat(paramView, "alpha", new float[] { 0.3F, 1.0F });; paramView = ObjectAnimator.ofFloat(paramView, "alpha", new float[] { 1.0F, 0.3F }))
          {
            paramView.setDuration(200L);
            paramView.start();
            return;
          }
        }
      }
      else
      {
        paramView = (ViewGroup)paramView;
        paramView.setEnabled(paramBoolean1);
        int j = paramView.getChildCount();
        int i = 0;
        while (i < j)
        {
          a(paramView.getChildAt(i), paramBoolean1, paramBoolean2);
          i += 1;
        }
      }
    }
  }
  
  public static void a(ImageView paramImageView)
  {
    try
    {
      paramImageView = paramImageView.getDrawable();
      if ((paramImageView instanceof BitmapDrawable)) {
        ((BitmapDrawable)paramImageView).getBitmap().recycle();
      }
      return;
    }
    catch (Exception paramImageView) {}
  }
  
  public static boolean a()
  {
    Object localObject = BeamApp.s();
    if ((BeamApp.e()) && (localObject != null) && (((nexos.p.e)localObject).g) && (com.summit.portal.controllers.e.c()))
    {
      localObject = com.summit.portal.controllers.e.g().a("TotalComms.enableVoIP");
      String str = com.summit.portal.controllers.e.g().a("beIPVoiceCallAuth");
      if ((!"1".equals(localObject)) && (!"2".equals(localObject)) && ((str == null) || ("0".equals(str)))) {}
    }
    for (boolean bool = true;; bool = false)
    {
      Log.b(new Object[] { "UICommons: isHDVoiceCallEnabled: ", Boolean.valueOf(bool) });
      return bool;
    }
  }
  
  public static boolean a(long paramLong)
  {
    return new DateTime(paramLong).withTimeAtStartOfDay().isEqual(new DateTime(System.currentTimeMillis()).withTimeAtStartOfDay());
  }
  
  public static boolean a(Context paramContext, Intent paramIntent)
  {
    return paramIntent.resolveActivity(paramContext.getPackageManager()) != null;
  }
  
  public static boolean a(MessageItem paramMessageItem)
  {
    return (paramMessageItem.hasMedia()) && (paramMessageItem.media.getMedia()[(paramMessageItem.media.getMedia().length - 1)].source.startsWith("vmatxsls"));
  }
  
  public static boolean a(String paramString)
  {
    return (paramString == null) || (paramString.equals("")) || (paramString.equalsIgnoreCase("restricted")) || (paramString.equalsIgnoreCase("unknown")) || (paramString.equalsIgnoreCase("anonymous")) || (paramString.equalsIgnoreCase("unavailable")) || (paramString.equalsIgnoreCase("blocked"));
  }
  
  public static Bitmap b(Bitmap paramBitmap, int paramInt)
  {
    int i = paramBitmap.getWidth();
    int j = paramBitmap.getHeight();
    float f = i / j;
    if (f > 1.0F)
    {
      j = (int)(paramInt / f);
      i = paramInt;
    }
    for (;;)
    {
      return Bitmap.createScaledBitmap(paramBitmap, i, j, true);
      i = (int)(f * paramInt);
      j = paramInt;
    }
  }
  
  public static Iterable b(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    paramString = b.matcher(paramString);
    while (paramString.find()) {
      localArrayList.add(paramString.toMatchResult());
    }
    return localArrayList;
  }
  
  public static String b(LatLng paramLatLng)
  {
    return paramLatLng.latitude + ", " + paramLatLng.longitude;
  }
  
  public static boolean b()
  {
    Object localObject = BeamApp.s();
    if ((localObject != null) && (!((nexos.p.e)localObject).f)) {
      return false;
    }
    if (com.summit.portal.controllers.e.c())
    {
      localObject = com.summit.portal.controllers.e.g().a("TotalComms.enableVCoIP");
      String str = com.summit.portal.controllers.e.g().a("beIPVideoCallAuth");
      if ((!"1".equals(localObject)) && (!"2".equals(localObject)) && ((str == null) || ("0".equals(str)))) {}
    }
    for (boolean bool = true;; bool = false)
    {
      Log.b(new Object[] { "UICommons: isVideoCallEnabled: ", Boolean.valueOf(bool) });
      return bool;
    }
  }
  
  public static boolean b(long paramLong)
  {
    return new DateTime(paramLong).withTimeAtStartOfDay().isEqual(new DateTime().withTimeAtStartOfDay().minusDays(1));
  }
  
  public static boolean b(Context paramContext)
  {
    if (Settings.Global.getInt(paramContext.getContentResolver(), "airplane_mode_on", 0) != 0) {}
    for (boolean bool = true;; bool = false)
    {
      Log.a(new Object[] { "UICommons: isAirplaneModeOn: isAirplaneModeOn=", Boolean.valueOf(bool) });
      return bool;
    }
  }
  
  public static boolean b(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(0).iterator();
    while (paramContext.hasNext()) {
      if (paramString.equals(((PackageInfo)paramContext.next()).packageName))
      {
        Log.a(new Object[] { "UICommons: isPackageInstalledInDevice=true : packageName=", paramString });
        return true;
      }
    }
    Log.a(new Object[] { "UICommons: isPackageInstalledInDevice=false : packageName=", paramString });
    return false;
  }
  
  private static boolean b(MessageItem paramMessageItem)
  {
    boolean bool;
    if (paramMessageItem != null) {
      if ((TextUtils.isEmpty(paramMessageItem.body)) && (paramMessageItem.groupMode == MessageItem.GroupMode.GROUP) && (paramMessageItem.status == MessageStatus.AVAILABLE) && (paramMessageItem.type == MessageType.MMS)) {
        bool = true;
      }
    }
    for (;;)
    {
      Log.a(new Object[] { "UICommons: isMessageItemInvalid: isMessageItemInvalid=", Boolean.valueOf(bool) });
      return bool;
      bool = false;
      continue;
      bool = false;
    }
  }
  
  public static int c(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(0).iterator();
    while (paramContext.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
      if (paramString.equals(localPackageInfo.packageName))
      {
        Log.a(new Object[] { "UICommons: getVersionCode=", Integer.valueOf(localPackageInfo.versionCode), " : packageName=", paramString });
        return localPackageInfo.versionCode;
      }
    }
    Log.a(new Object[] { "UICommons: getVersionCode=NOT FOUND : packageName=", paramString });
    return -1;
  }
  
  public static String c()
  {
    String str = com.summit.portal.controllers.e.g().k();
    if (str == null)
    {
      Log.a(new Object[] { "UICommons", ": getLocalFormattedPhoneNumber: phoneNumber is null" });
      return "Unknown";
    }
    try
    {
      str = com.google.a.a.e.a().a(com.google.a.a.e.a().a(k.a(f(str)), "US"), h.b).replace("+1", "").replace("+", "");
      return str;
    }
    catch (com.google.a.a.c localC)
    {
      localC.printStackTrace();
    }
    return "Unknown";
  }
  
  public static String c(String paramString)
  {
    String str = "";
    if (TextUtils.isEmpty(paramString)) {
      return "#";
    }
    String[] arrayOfString2 = paramString.split(" ");
    String[] arrayOfString1 = arrayOfString2;
    if (arrayOfString2.length <= 1) {
      arrayOfString1 = paramString.split("-");
    }
    paramString = str;
    if (arrayOfString1.length > 0)
    {
      paramString = arrayOfString1[0];
      if (TextUtils.isEmpty(paramString)) {
        return "#";
      }
      int j = paramString.charAt(0);
      if (((j >= 65) && (j <= 90)) || ((j >= 97) && (j <= 122)))
      {
        i = 1;
        if ((j < 128) || (j > 255)) {
          break label140;
        }
        j = 1;
        label117:
        if ((i == 0) && (j == 0)) {
          break label145;
        }
      }
      label140:
      label145:
      for (int i = 1;; i = 0)
      {
        if (i != 0) {
          break label150;
        }
        return "#";
        i = 0;
        break;
        j = 0;
        break label117;
      }
      label150:
      paramString = "" + arrayOfString1[0].charAt(0);
    }
    str = paramString;
    if (arrayOfString1.length > 1)
    {
      str = paramString;
      if (arrayOfString1[(arrayOfString1.length - 1)].length() > 0)
      {
        char c = arrayOfString1[(arrayOfString1.length - 1)].substring(0, 1).charAt(0);
        if (((c < 'A') || (c > 'Z')) && ((c < 'a') || (c > 'z')))
        {
          str = paramString;
          if (c >= '0')
          {
            str = paramString;
            if (c > '9') {}
          }
        }
        else
        {
          str = paramString + c;
        }
      }
    }
    return str.toUpperCase();
  }
  
  public static boolean c(Context paramContext)
  {
    String str = Telephony.Sms.getDefaultSmsPackage(paramContext);
    paramContext = paramContext.getPackageName();
    if ((str != null) && (str.equals(paramContext))) {}
    for (boolean bool = true;; bool = false)
    {
      Log.a(new Object[] { "UICommons: isDefaultSMSApp: defaultSMSPackage=", str, ", myPackageName=", paramContext, ", isDefaultSMSApp=", Boolean.valueOf(bool) });
      return bool;
    }
  }
  
  public static String d(String paramString)
  {
    int k = 0;
    if (TextUtils.isEmpty(paramString)) {
      paramString = "";
    }
    StringBuilder localStringBuilder;
    String[] arrayOfString;
    do
    {
      return paramString;
      localStringBuilder = new StringBuilder();
      arrayOfString = paramString.split(" ");
    } while (arrayOfString.length <= 1);
    int i = 0;
    while (i < arrayOfString.length - 1)
    {
      localStringBuilder.append(arrayOfString[i] + " ");
      i += 1;
    }
    char c = arrayOfString[(arrayOfString.length - 1)].charAt(0);
    int j;
    if (((c >= 'A') && (c <= 'Z')) || ((c >= 'a') && (c <= 'z')))
    {
      i = 1;
      if ((c < '') || (c > 'ÿ')) {
        break label187;
      }
      j = 1;
      label143:
      if (i == 0)
      {
        i = k;
        if (j == 0) {}
      }
      else
      {
        i = 1;
      }
      if (i == 0) {
        break label192;
      }
      localStringBuilder.append(c);
      localStringBuilder.append(".");
    }
    for (;;)
    {
      return localStringBuilder.toString();
      i = 0;
      break;
      label187:
      j = 0;
      break label143;
      label192:
      localStringBuilder.append(arrayOfString[(arrayOfString.length - 1)]);
    }
  }
  
  public static boolean d()
  {
    return true;
  }
  
  public static boolean d(Context paramContext)
  {
    return (!BeamApp.c(paramContext)) && (!c(paramContext));
  }
  
  public static c e(Context paramContext)
  {
    c localC = c.a;
    try
    {
      int i = paramContext.getResources().getInteger(2131361807);
      if (i == 0) {
        return c.a;
      }
      if (i == 1) {
        return c.b;
      }
      paramContext = c.c;
      return paramContext;
    }
    catch (Throwable paramContext)
    {
      paramContext.printStackTrace();
    }
    return localC;
  }
  
  public static String e(String paramString)
  {
    int k = 0;
    if (TextUtils.isEmpty(paramString)) {
      paramString = "";
    }
    StringBuilder localStringBuilder;
    String[] arrayOfString;
    do
    {
      return paramString;
      localStringBuilder = new StringBuilder();
      arrayOfString = paramString.split(" ");
    } while (arrayOfString.length <= 1);
    int i = 0;
    while (i < arrayOfString.length - 1)
    {
      localStringBuilder.append(arrayOfString[i] + " ");
      i += 1;
    }
    char c = arrayOfString[(arrayOfString.length - 1)].charAt(0);
    int j;
    if (((c >= 'A') && (c <= 'Z')) || ((c >= 'a') && (c <= 'z')))
    {
      i = 1;
      if ((c < '') || (c > 'ÿ')) {
        break label178;
      }
      j = 1;
      label143:
      if (i == 0)
      {
        i = k;
        if (j == 0) {}
      }
      else
      {
        i = 1;
      }
      if (i == 0) {
        break label183;
      }
      localStringBuilder.append(c);
    }
    for (;;)
    {
      return localStringBuilder.toString();
      i = 0;
      break;
      label178:
      j = 0;
      break label143;
      label183:
      localStringBuilder.append(arrayOfString[(arrayOfString.length - 1)]);
    }
  }
  
  public static boolean e()
  {
    if (com.summit.portal.controllers.m.e().getPackageManager().hasSystemFeature("android.hardware.telephony"))
    {
      String str = ((TelephonyManager)com.summit.portal.controllers.m.e().getSystemService("phone")).getNetworkOperator();
      if ((str != null) && (!str.equals("")) && (!str.equals("00000"))) {
        return true;
      }
    }
    return false;
  }
  
  public static String f(String paramString)
  {
    if ((paramString != null) && (paramString.length() > 0))
    {
      String str = com.summit.portal.controllers.m.h(com.summit.portal.controllers.m.e());
      Log.b(new Object[] { "UICommons: formatToLocalPhoneNumber: mcc=", str });
      if ("716".equals(str)) {
        if ((paramString.startsWith("+")) && (!paramString.startsWith("+51"))) {
          str = "191200" + paramString.substring(1);
        }
      }
      for (;;)
      {
        Log.b(new Object[] { "UICommons: formatToLocalPhoneNumber: phoneNumber=", paramString, ", localNumber=", str });
        return str;
        str = nexos.contacts.db.d.c(paramString);
        continue;
        if ("730".equals(str))
        {
          if (paramString.startsWith("+569")) {
            str = paramString.substring(4);
          } else {
            str = paramString;
          }
        }
        else if ("732".equals(str))
        {
          if ((paramString.startsWith("+")) && (!paramString.startsWith("+57"))) {
            str = "00444" + paramString.substring(1);
          } else {
            str = nexos.contacts.db.d.c(paramString);
          }
        }
        else if (paramString.startsWith("+54")) {
          str = paramString;
        } else {
          str = nexos.contacts.db.d.c(paramString);
        }
      }
    }
    return paramString;
  }
  
  public static boolean f(Context paramContext)
  {
    if (!com.summit.portal.controllers.e.c()) {}
    nexos.d localD;
    do
    {
      return false;
      localD = com.summit.portal.controllers.e.f();
    } while ((localD == null) || (localD.getConnectionState() == 100) || (g(paramContext)));
    return true;
  }
  
  public static String g(String paramString)
  {
    String str = paramString;
    try
    {
      paramString = paramString.replace("+1", "").replace("+", "");
      str = paramString;
      paramString = PhoneNumberUtils.formatNumber(paramString, "US");
      return paramString;
    }
    catch (Exception paramString) {}
    return str;
  }
  
  public static boolean g(Context paramContext)
  {
    if (!com.summit.portal.controllers.e.d())
    {
      Log.a(new Object[] { "UICommons: isVolteCallAvailable: false, not signed in" });
      return false;
    }
    if (h(paramContext))
    {
      paramContext = (TelephonyManager)paramContext.getSystemService("phone");
      boolean bool = s.a().l();
      if (paramContext.getCallState() != 0) {}
      for (int i = 1; (i != 0) && (!bool); i = 0)
      {
        Log.a(new Object[] { "UICommons: isVolteCallAvailable: false, 1xCDMA with CS call active" });
        return false;
      }
    }
    Log.a(new Object[] { "UICommons: isVolteCallAvailable: true" });
    return true;
  }
  
  public static String h(String paramString)
  {
    try
    {
      String str = PhoneNumberUtils.formatNumber(paramString, "US");
      return str;
    }
    catch (Exception localException) {}
    return paramString;
  }
  
  public static boolean h(Context paramContext)
  {
    paramContext = ((TelephonyManager)paramContext.getSystemService("phone")).getAllCellInfo();
    if (paramContext != null)
    {
      paramContext = paramContext.iterator();
      while (paramContext.hasNext())
      {
        CellInfo localCellInfo = (CellInfo)paramContext.next();
        Log.a(new Object[] { "UICommons: isOnCDMA1x: cell:", localCellInfo });
        if ((localCellInfo != null) && (localCellInfo.isRegistered()))
        {
          Log.a(new Object[] { "UICommons: isOnCDMA1x: primary cell: ", localCellInfo.getClass().getSimpleName() });
          if ((localCellInfo instanceof CellInfoCdma))
          {
            Log.a(new Object[] { "UICommons: isOnCDMA1x: true" });
            return true;
          }
        }
      }
    }
    Log.a(new Object[] { "UICommons: isOnCDMA1x: false" });
    return false;
  }
  
  public static File i(Context paramContext)
  {
    paramContext = new File(paramContext.getExternalCacheDir().toString() + paramContext.getResources().getString(2131690531));
    if (!paramContext.exists()) {
      paramContext.mkdirs();
    }
    return paramContext;
  }
  
  public static String i(String paramString)
  {
    Object localObject = com.google.a.a.e.a();
    try
    {
      localObject = ((com.google.a.a.e)localObject).a(((com.google.a.a.e)localObject).a(paramString, "US"), h.b).replace("+1", "").replace("+", "").replace(" ", "");
      return localObject;
    }
    catch (com.google.a.a.c localC) {}
    return paramString.replace("+1", "").replace("+", "").replace(" ", "");
  }
  
  public static File j(Context paramContext)
  {
    paramContext = new File(Environment.getExternalStorageDirectory() + paramContext.getResources().getString(2131690026));
    if (!paramContext.exists()) {
      paramContext.mkdirs();
    }
    return paramContext;
  }
  
  public static String j(String paramString)
  {
    paramString = paramString.replace(" ", "").replace("(", "").replace(")", "").replace("-", "");
    if (paramString.startsWith("+")) {}
    do
    {
      return paramString;
      if ((paramString.startsWith("011")) && (paramString.length() > 11)) {
        return paramString.replaceFirst("011", "+");
      }
      if (paramString.length() == 10) {
        return "+1" + paramString;
      }
    } while ((paramString.length() != 11) || (!paramString.startsWith("1")));
    return "+" + paramString;
  }
  
  public static File k(Context paramContext)
  {
    paramContext = new File(Environment.getExternalStorageDirectory() + paramContext.getResources().getString(2131690041));
    if (!paramContext.exists()) {
      paramContext.mkdirs();
    }
    return paramContext;
  }
  
  public static boolean k(String paramString)
  {
    boolean bool2 = false;
    String[] arrayOfString = a;
    int j = arrayOfString.length;
    int i = 0;
    for (;;)
    {
      boolean bool1 = bool2;
      if (i < j)
      {
        if (paramString.startsWith(arrayOfString[i])) {
          bool1 = true;
        }
      }
      else {
        return bool1;
      }
      i += 1;
    }
  }
  
  public static File l(Context paramContext)
  {
    paramContext = new File(Environment.getExternalStorageDirectory() + paramContext.getResources().getString(2131689685));
    if (!paramContext.exists()) {
      paramContext.mkdirs();
    }
    return paramContext;
  }
  
  public static boolean l(String paramString)
  {
    return paramString.matches("^https?\\:\\/\\/(www\\.|maps\\.)?google(\\.[a-z]+){1,2}\\/maps\\/?\\??([^&]+&)*(((ll=)|@)-?[0-9]{1,2}\\.[0-9]+,-?[0-9]{1,3}\\.[0-9]+|q=[^&]+)+.*");
  }
  
  public static LatLng m(String paramString)
  {
    paramString = Pattern.compile("(-?\\d+\\.\\d+),(-?\\d+\\.\\d+)").matcher(paramString);
    if (paramString.find())
    {
      paramString = paramString.group(0).split(",");
      try
      {
        paramString = new LatLng(Double.parseDouble(paramString[0]), Double.parseDouble(paramString[1]));
        return paramString;
      }
      catch (NumberFormatException paramString)
      {
        paramString.printStackTrace();
      }
    }
    return null;
  }
  
  public static File m(Context paramContext)
  {
    paramContext = new File(Environment.getExternalStorageDirectory() + paramContext.getResources().getString(2131690196));
    if (!paramContext.exists()) {
      paramContext.mkdirs();
    }
    return paramContext;
  }
  
  public static String n(Context paramContext)
  {
    int i = Process.myPid();
    paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses().iterator();
    while (paramContext.hasNext())
    {
      ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)paramContext.next();
      if (localRunningAppProcessInfo.pid == i) {
        return localRunningAppProcessInfo.processName;
      }
    }
    return "";
  }
  
  public static String n(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    for (;;)
    {
      return null;
      paramString = paramString.split("(\\n|(\\s+))");
      int j = paramString.length;
      int i = 0;
      while (i < j)
      {
        CharSequence localCharSequence = paramString[i];
        if (Patterns.WEB_URL.matcher(localCharSequence).matches())
        {
          if ((URLUtil.isHttpUrl(localCharSequence)) || (URLUtil.isHttpsUrl(localCharSequence))) {
            return localCharSequence;
          }
          return "http://" + localCharSequence;
        }
        i += 1;
      }
    }
  }
  
  @TargetApi(23)
  public static boolean o(Context paramContext)
  {
    if ((Build.VERSION.SDK_INT < 23) || (Settings.canDrawOverlays(paramContext))) {}
    for (boolean bool = true;; bool = false)
    {
      Log.a(new Object[] { "UICommons: isSystemAlertPermissionGranted: result=", Boolean.valueOf(bool) });
      return bool;
    }
  }
  
  public static String p(Context paramContext)
  {
    int i = Binder.getCallingUid();
    paramContext = paramContext.getPackageManager().getPackagesForUid(i);
    if (paramContext == null) {
      return null;
    }
    return paramContext[0];
  }
}
