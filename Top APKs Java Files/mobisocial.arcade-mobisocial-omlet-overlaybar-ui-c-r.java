package mobisocial.omlet.overlaybar.ui.c;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.LabeledIntent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.pm.ResolveInfo.DisplayNameComparator;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.ResultReceiver;
import android.preference.PreferenceManager;
import android.provider.DocumentsContract;
import android.provider.MediaStore.Audio.Media;
import android.provider.MediaStore.Images.Media;
import android.provider.MediaStore.Video.Media;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.x;
import android.support.v4.content.FileProvider;
import android.text.Html;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.StyleSpan;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import android.widget.Toast;
import com.a.a.c.b.h;
import d.ab;
import d.ac;
import d.e;
import glrecorder.lib.R.color;
import glrecorder.lib.R.id;
import glrecorder.lib.R.layout;
import glrecorder.lib.R.plurals;
import glrecorder.lib.R.raw;
import glrecorder.lib.R.string;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import mobisocial.c.a.a;
import mobisocial.c.b.a;
import mobisocial.c.b.b;
import mobisocial.longdan.LDObjects.BlobReferenceObj;
import mobisocial.longdan.LDObjects.User;
import mobisocial.longdan.b.aay;
import mobisocial.longdan.b.abv;
import mobisocial.longdan.b.abw;
import mobisocial.longdan.b.aem;
import mobisocial.longdan.b.aeo;
import mobisocial.longdan.b.aeq;
import mobisocial.longdan.b.aet;
import mobisocial.longdan.b.afc;
import mobisocial.longdan.b.aft;
import mobisocial.longdan.b.afv;
import mobisocial.longdan.b.agh;
import mobisocial.longdan.b.agp;
import mobisocial.longdan.b.ahe;
import mobisocial.longdan.b.ahq;
import mobisocial.longdan.b.ahz;
import mobisocial.longdan.b.aia;
import mobisocial.longdan.b.ajh;
import mobisocial.longdan.b.aji;
import mobisocial.longdan.b.ajr;
import mobisocial.longdan.b.akt;
import mobisocial.longdan.b.aly;
import mobisocial.longdan.b.amn;
import mobisocial.longdan.b.ana;
import mobisocial.longdan.b.anz;
import mobisocial.longdan.b.aom;
import mobisocial.longdan.b.aoy;
import mobisocial.longdan.b.apb;
import mobisocial.longdan.b.apd;
import mobisocial.longdan.b.ape;
import mobisocial.longdan.b.bg;
import mobisocial.longdan.b.by;
import mobisocial.longdan.b.cq;
import mobisocial.longdan.b.eo;
import mobisocial.longdan.b.er;
import mobisocial.longdan.b.fg;
import mobisocial.longdan.b.kz;
import mobisocial.longdan.b.lg;
import mobisocial.longdan.b.lm;
import mobisocial.longdan.b.ln;
import mobisocial.longdan.b.mb;
import mobisocial.longdan.b.of;
import mobisocial.longdan.b.og;
import mobisocial.longdan.b.qk;
import mobisocial.longdan.b.ql;
import mobisocial.longdan.b.ui;
import mobisocial.longdan.b.zs;
import mobisocial.longdan.b.zt;
import mobisocial.longdan.b.zv;
import mobisocial.longdan.b.zy;
import mobisocial.longdan.exception.LongdanException;
import mobisocial.longdan.net.WsRpcConnectionHandler;
import mobisocial.omlet.OmletGameSDK;
import mobisocial.omlet.OmletGameSDK.OverlayPermissionChecker;
import mobisocial.omlet.b.e.a;
import mobisocial.omlet.b.f;
import mobisocial.omlet.b.q.a;
import mobisocial.omlet.b.t.a;
import mobisocial.omlet.e.c.f;
import mobisocial.omlet.overlaybar.ui.b.s.b;
import mobisocial.omlet.overlaybar.util.MediaUploadIntentService.f;
import mobisocial.omlet.overlaychat.ChatProxyActivity;
import mobisocial.omlet.overlaychat.ChooserActivity;
import mobisocial.omlet.overlaychat.MediaShareActivity;
import mobisocial.omlet.overlaychat.RequestPermissionActivity;
import mobisocial.omlet.overlaychat.viewhandlers.FloatingButtonViewHandler;
import mobisocial.omlet.util.ClipboardShareActivity;
import mobisocial.omlet.util.aa;
import mobisocial.omlet.util.al;
import mobisocial.omlet.util.t;
import mobisocial.omlet.util.w;
import mobisocial.omlib.api.OmletAuthApi;
import mobisocial.omlib.api.OmlibApiManager;
import mobisocial.omlib.client.ClientAnalyticsUtils;
import mobisocial.omlib.client.ClientAuthUtils;
import mobisocial.omlib.client.ClientAuthUtils.JwtCallback;
import mobisocial.omlib.client.ClientBlobUtils;
import mobisocial.omlib.client.LongdanClient;
import mobisocial.omlib.client.config.AppConfiguration;
import mobisocial.omlib.client.config.AppConfigurationFactory;
import mobisocial.omlib.db.OMSQLiteHelper;
import mobisocial.omlib.db.entity.OMAccount;
import mobisocial.omlib.db.entity.OMFeed;
import mobisocial.omlib.db.entity.OMMessage;
import mobisocial.omlib.db.entity.OMObject;
import mobisocial.omlib.model.AccountProfile;
import mobisocial.omlib.model.PresenceState;
import mobisocial.omlib.sendable.GifSendable;
import mobisocial.omlib.ui.toast.OMToast;
import mobisocial.omlib.ui.util.UIHelper;
import mobisocial.omlib.ui.util.UIHelper.StreamUriOnClickListener;
import mobisocial.omlib.ui.util.Utils;
import org.json.JSONException;
import org.json.JSONObject;

public final class r
{
  public static Class a;
  public static Class b;
  public static String[] c;
  static final MessageDigest d;
  static PackageInfo e;
  static long f;
  public static Comparator<b.afv> g;
  public static Comparator<b.afv> h;
  private static final int[] i = { 0, 400, 800, 1100, 1400, 1700, 2000, 2300, 2600, 3000, 3800 };
  private static final HashMap<r.a.a, r.a> j;
  private static Boolean k;
  private static final Set<String> l = new HashSet();
  private static Boolean m;
  
  static
  {
    c = new String[] { "Alt. Account", "Best Friend", "Friend", "Sister", "Brother", "Mother", "Father", "Daughter", "Son", "Cousin", "Stream Mod" };
    j = new HashMap();
    j.put(r.a.a.Rookie, new r.a(R.raw.oma_ic_anniversary_rookie, R.raw.oma_ic_anniversary_gray_star_1, R.raw.oma_ic_anniversary_gray_star_2, R.string.omp_badge_rookie, R.color.oma_badge_rookie_ececec, null));
    j.put(r.a.a.Junior, new r.a(R.raw.oma_ic_anniversary_junior, R.raw.oma_ic_anniversary_red_star_1, R.raw.oma_ic_anniversary_red_star_2, R.string.omp_badge_junior, R.color.oma_badge_junior_e8219f, null));
    j.put(r.a.a.Elite, new r.a(R.raw.oma_ic_anniversary_elite, R.raw.oma_ic_anniversary_copper_star_1, R.raw.oma_ic_anniversary_copper_star_2, R.string.omp_badge_elite, R.color.oma_badge_elite_d85e4d, null));
    j.put(r.a.a.Pro, new r.a(R.raw.oma_ic_anniversary_pro, R.raw.oma_ic_anniversary_blue_star_1, R.raw.oma_ic_anniversary_blue_star_2, R.string.omp_badge_pro, R.color.oma_badge_pro_02f2f2, null));
    j.put(r.a.a.Legendary, new r.a(R.raw.oma_ic_anniversary_legend, R.raw.oma_ic_anniversary_yellow_star_1, R.raw.oma_ic_anniversary_yellow_star_2, R.string.omp_badge_legendary, R.color.oma_badge_legendary_ffd34f, null));
    try
    {
      d = MessageDigest.getInstance("MD5");
      l.add("com.facebook.katana");
      l.add("com.whatsapp");
      l.add("com.facebook.orca");
      g = new r.8();
      h = new r.9();
      return;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      throw new RuntimeException(localNoSuchAlgorithmException);
    }
  }
  
  public static boolean A(Context paramContext)
  {
    return a(paramContext, r.c.Yt);
  }
  
  public static boolean B(Context paramContext)
  {
    return a(paramContext, r.c.Fb);
  }
  
  public static boolean C(Context paramContext)
  {
    return a(paramContext, r.c.YtChat);
  }
  
  public static boolean D(Context paramContext)
  {
    AudioManager localAudioManager = (AudioManager)paramContext.getSystemService("audio");
    boolean bool1 = false;
    if (localAudioManager != null)
    {
      try
      {
        boolean bool2 = E(paramContext);
        bool1 = bool2 | false;
        try
        {
          bool2 = localAudioManager.isWiredHeadsetOn();
          return bool1 | bool2;
        }
        catch (Exception paramContext) {}
        mobisocial.c.c.a("Arcade", "error checking headset state", paramContext);
      }
      catch (Exception paramContext) {}
      return bool1;
    }
    mobisocial.c.c.a("Arcade", "cannot find AudioManager");
    return false;
  }
  
  public static boolean E(Context paramContext)
  {
    paramContext = w.a(paramContext);
    if (paramContext != null)
    {
      if (Build.VERSION.SDK_INT < 23) {
        return paramContext.isBluetoothA2dpOn();
      }
      paramContext = paramContext.getDevices(2);
      int i1 = paramContext.length;
      int n = 0;
      while (n < i1)
      {
        int i2 = paramContext[n].getType();
        if ((i2 != 8) && (i2 != 22)) {
          n += 1;
        } else {
          return true;
        }
      }
    }
    return false;
  }
  
  public static Intent F(Context paramContext)
  {
    Intent localIntent = new Intent("mobisocial.arcade.action.VIEW_TOKEN_STORE");
    localIntent.setPackage(paramContext.getPackageName());
    if (!(paramContext instanceof Activity))
    {
      localIntent.addFlags(276824064);
      if (!paramContext.getPackageName().equals(OmletGameSDK.getLatestPackage())) {
        localIntent.addFlags(32768);
      }
    }
    localIntent.addFlags(131072);
    localIntent.putExtra("extraStartPip", false);
    return localIntent;
  }
  
  public static void G(Context paramContext)
  {
    paramContext.startActivity(F(paramContext));
  }
  
  private static Boolean H(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager();
      boolean bool = true;
      paramContext = paramContext.getPackageInfo("com.mojang.minecraftpe", 1);
      if (paramContext != null)
      {
        if (Integer.parseInt(paramContext.versionName.split("\\.")[1]) >= 16) {
          bool = false;
        }
        return Boolean.valueOf(bool);
      }
      throw new PackageManager.NameNotFoundException();
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;) {}
    }
    return null;
  }
  
  public static int a(long paramLong)
  {
    return b(paramLong).a;
  }
  
  public static int a(Context paramContext, int paramInt)
  {
    float f1 = paramContext.getResources().getDisplayMetrics().density;
    return (int)(paramInt * f1);
  }
  
  public static int a(BitmapFactory.Options paramOptions, int paramInt1, int paramInt2)
  {
    int i2 = paramOptions.outHeight;
    int i3 = paramOptions.outWidth;
    int i1 = 1;
    int n = 1;
    if ((i2 > paramInt2) || (i3 > paramInt1))
    {
      i2 /= 2;
      i3 /= 2;
      for (;;)
      {
        i1 = n;
        if (i2 / n < paramInt2) {
          break;
        }
        i1 = n;
        if (i3 / n < paramInt1) {
          break;
        }
        n *= 2;
      }
    }
    return i1;
  }
  
  public static int a(byte[] paramArrayOfByte, int paramInt)
  {
    int n = paramArrayOfByte[paramInt];
    int i1 = paramArrayOfByte[(paramInt + 1)];
    int i2 = paramArrayOfByte[(paramInt + 2)];
    return (paramArrayOfByte[(paramInt + 3)] & 0xFF) << 24 | n & 0xFF | (i1 & 0xFF) << 8 | (i2 & 0xFF) << 16;
  }
  
  public static long a(byte[] paramArrayOfByte)
  {
    long l1;
    int n;
    synchronized (d)
    {
      paramArrayOfByte = d.digest(paramArrayOfByte);
      l1 = 0L;
      n = 0;
      break label34;
      return l1;
    }
    label34:
    while (n < 8)
    {
      l1 = l1 << 8 | paramArrayOfByte[(0 + n)] & 0xFF;
      n += 1;
    }
  }
  
  public static AlertDialog a(Context paramContext, DialogInterface.OnClickListener paramOnClickListener)
  {
    String str = a(paramContext, mobisocial.omlet.overlaybar.util.j.b(paramContext).longValue());
    AlertDialog.Builder localBuilder = u(paramContext);
    localBuilder.setTitle(R.string.omp_videoPreviewFragment_dialog_video_upload_limit_title);
    localBuilder.setMessage(String.format(paramContext.getString(R.string.omp_videoPreviewFragment_dialog_video_upload_limit_message), new Object[] { str }));
    localBuilder.setPositiveButton(R.string.omp_videoPreviewFragment_dialog_video_upload_limit_positive, paramOnClickListener);
    return localBuilder.create();
  }
  
  public static AlertDialog a(Context paramContext, DialogInterface.OnClickListener paramOnClickListener1, DialogInterface.OnClickListener paramOnClickListener2, r.h paramH, Integer paramInteger)
  {
    return a(paramContext, paramOnClickListener1, paramOnClickListener2, true, paramH, paramInteger);
  }
  
  private static AlertDialog a(Context paramContext, DialogInterface.OnClickListener paramOnClickListener1, DialogInterface.OnClickListener paramOnClickListener2, boolean paramBoolean, r.h paramH, Integer paramInteger)
  {
    boolean bool = D(paramContext);
    Object localObject = new HashMap();
    ((HashMap)localObject).put("headset", Boolean.valueOf(bool));
    ((HashMap)localObject).put("type", paramH.name());
    ((HashMap)localObject).put("show", Boolean.valueOf(bool));
    OmlibApiManager.getInstance(paramContext).analytics().trackEvent(b.b.Megaphone, b.a.ShowHeadsetAlertIfNecessary, (Map)localObject);
    if (bool) {
      return null;
    }
    localObject = new AlertDialog.Builder(paramContext);
    int n;
    if (paramH == r.h.HeadsetDisconnected) {
      n = R.string.omp_stream_voice_chat_alert_title_headset_disconnected;
    } else {
      n = R.string.omp_stream_voice_chat_alert_title;
    }
    ((AlertDialog.Builder)localObject).setTitle(n);
    int i1 = R.string.omp_retry;
    if (paramH.name().startsWith("Streamer"))
    {
      n = R.string.omp_stream_voice_chat_alert_message_streamer;
    }
    else if (paramH == r.h.StartStreamAfterJoinChat)
    {
      n = R.string.omp_stream_voice_chat_alert_message_start_stream_after_join_chat;
    }
    else if (paramH == r.h.HeadsetDisconnected)
    {
      n = R.string.omp_stream_voice_chat_alert_message_headset_disconnected;
      i1 = R.string.omp_mute;
    }
    else
    {
      n = R.string.omp_stream_voice_chat_alert_message_headset_not_connected;
      i1 = R.string.omp_mute;
    }
    ((AlertDialog.Builder)localObject).setMessage(n);
    HashMap localHashMap = new HashMap();
    localHashMap.put("type", paramH.name());
    ((AlertDialog.Builder)localObject).setPositiveButton(i1, new r.20(localHashMap, paramContext, paramOnClickListener1));
    if (paramBoolean) {
      ((AlertDialog.Builder)localObject).setNegativeButton(R.string.omp_cancel, new r.22(localHashMap, paramContext, paramOnClickListener2));
    }
    paramContext = ((AlertDialog.Builder)localObject).create();
    if (paramInteger != null) {
      paramContext.getWindow().setType(paramInteger.intValue());
    }
    return paramContext;
  }
  
  public static AlertDialog a(Context paramContext, DialogInterface.OnClickListener paramOnClickListener, r.h paramH, Integer paramInteger)
  {
    return a(paramContext, paramOnClickListener, null, false, paramH, paramInteger);
  }
  
  private static AlertDialog a(Context paramContext, String paramString1, String paramString2, DialogInterface.OnClickListener paramOnClickListener)
  {
    paramString1 = new AlertDialog.Builder(paramContext).setTitle(paramString1).setMessage(paramString2).setCancelable(true).setPositiveButton(R.string.omp_discard, paramOnClickListener).setNegativeButton(R.string.oml_cancel, new r.19()).setOnCancelListener(new r.18()).create();
    if (!s(paramContext)) {
      paramString1.getWindow().setType(UIHelper.getWindowFlagForDialog());
    }
    return paramString1;
  }
  
  public static Intent a(Context paramContext, long paramLong, String paramString1, String paramString2)
  {
    paramContext = new Intent(paramContext, b);
    paramContext.putExtra("extraFeedId", paramLong);
    paramContext.putExtra("extraStoryObj", paramString1);
    paramContext.putExtra("shareCategory", paramString2);
    return paramContext;
  }
  
  public static Intent a(Context paramContext, long paramLong, String paramString1, String paramString2, String paramString3, boolean paramBoolean)
  {
    paramContext = new Intent(paramContext, b);
    paramContext.putExtra("extraFeedId", paramLong);
    paramContext.putExtra("android.intent.extra.TEXT", paramString1);
    paramContext.putExtra("android.intent.extra.STREAM", paramString3);
    paramContext.putExtra("shareCategory", paramString2);
    paramContext.putExtra("extraShareInUrl", paramBoolean);
    return paramContext;
  }
  
  public static Intent a(Context paramContext, String paramString)
  {
    paramContext = new Intent(paramContext, a);
    paramContext.putExtra("type", "text/plain");
    paramContext.putExtra("android.intent.extra.TEXT", paramString);
    return paramContext;
  }
  
  public static Intent a(Context paramContext, String paramString1, String paramString2)
  {
    paramString1 = a(paramContext, paramString1);
    if (!TextUtils.isEmpty(paramString2)) {}
    try
    {
      s.b localB = (s.b)mobisocial.b.a.a(paramString2, s.b.class);
      Intent localIntent = new Intent("android.intent.action.SEND");
      localIntent.putExtra("android.intent.extra.TEXT", localB.d);
      localIntent.setType("text/plain");
      paramString1.putExtra("android.intent.extra.INITIAL_INTENTS", a(paramContext, localIntent, null));
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    paramString1.putExtra("android.intent.extra.INITIAL_INTENTS", a(paramContext, paramString1, null));
    break label107;
    paramString1.putExtra("android.intent.extra.INITIAL_INTENTS", a(paramContext, paramString1, null));
    label107:
    paramString1.putExtra("extraHideCommunity", true);
    paramString1.putExtra("extraStoryObject", paramString2);
    paramString1.addFlags(276856832);
    return paramString1;
  }
  
  private static Intent a(String paramString1, String paramString2, String paramString3)
  {
    String str = paramString3;
    if (paramString3 == null)
    {
      paramString1 = b(paramString1, paramString2);
      paramString3 = new StringBuilder();
      paramString3.append("https://arcade.omlet.me/stream/");
      paramString3.append(paramString1);
      str = paramString3.toString();
    }
    paramString1 = new Intent("android.intent.action.SEND");
    paramString1.setType("text/plain");
    paramString1.putExtra("android.intent.extra.TEXT", str);
    paramString1.putExtra("streamerAccount", paramString2);
    return paramString1;
  }
  
  public static Bitmap a(Context paramContext, String paramString, int paramInt1, int paramInt2)
  {
    paramContext = mobisocial.c.a.a(paramContext, paramString);
    do
    {
      try
      {
        localObject1 = b(paramString);
        localObject2 = new BitmapFactory.Options();
        ((BitmapFactory.Options)localObject2).inSampleSize = a((BitmapFactory.Options)localObject1, paramInt1, paramInt2);
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("sampling image with dimens ");
        localStringBuilder.append(((BitmapFactory.Options)localObject1).outWidth);
        localStringBuilder.append(" ");
        localStringBuilder.append(((BitmapFactory.Options)localObject1).outHeight);
        localStringBuilder.append(" with sampleSize: ");
        localStringBuilder.append(((BitmapFactory.Options)localObject2).inSampleSize);
        mobisocial.c.c.d("Arcade", localStringBuilder.toString());
        ((BitmapFactory.Options)localObject2).inPreferredConfig = Bitmap.Config.ARGB_8888;
        if (paramContext == null) {
          break label237;
        }
        f1 = mobisocial.c.a.a(paramContext.a("Orientation", 1));
      }
      catch (OutOfMemoryError localOutOfMemoryError)
      {
        for (;;)
        {
          Object localObject1;
          Object localObject2;
          int n;
          continue;
          float f1 = 0.0F;
        }
      }
      if (f1 == 0.0F) {
        return BitmapFactory.decodeFile(paramString, (BitmapFactory.Options)localObject2);
      }
      localObject1 = BitmapFactory.decodeFile(paramString, (BitmapFactory.Options)localObject2);
      localObject2 = new Matrix();
      ((Matrix)localObject2).postRotate(f1);
      localObject1 = Bitmap.createBitmap((Bitmap)localObject1, 0, 0, ((Bitmap)localObject1).getWidth(), ((Bitmap)localObject1).getHeight(), (Matrix)localObject2, true);
      return localObject1;
      paramInt2 /= 2;
      n = paramInt1 / 2;
      if (paramInt2 < 10) {
        break;
      }
      paramInt1 = n;
    } while (n >= 10);
    return null;
  }
  
  public static Bitmap a(Bitmap paramBitmap)
  {
    Bitmap localBitmap = Bitmap.createBitmap(paramBitmap.getWidth(), paramBitmap.getHeight(), Bitmap.Config.ARGB_8888);
    Canvas localCanvas = new Canvas(localBitmap);
    Paint localPaint = new Paint();
    Rect localRect = new Rect(0, 0, paramBitmap.getWidth(), paramBitmap.getHeight());
    RectF localRectF = new RectF(localRect);
    localPaint.setAntiAlias(true);
    localCanvas.drawARGB(0, 0, 0, 0);
    localPaint.setColor(-65536);
    localCanvas.drawOval(localRectF, localPaint);
    localPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
    localCanvas.drawBitmap(paramBitmap, localRect, localRect, localPaint);
    paramBitmap.recycle();
    return localBitmap;
  }
  
  public static Bitmap a(Bitmap paramBitmap, int paramInt)
  {
    return a(paramBitmap, paramInt, null);
  }
  
  public static Bitmap a(Bitmap paramBitmap, int paramInt1, int paramInt2)
  {
    if (paramInt1 < paramInt2) {
      paramInt2 = (int)(paramBitmap.getHeight() / paramBitmap.getWidth() * paramInt1);
    } else {
      paramInt1 = (int)(paramBitmap.getWidth() / paramBitmap.getHeight() * paramInt2);
    }
    do
    {
      try
      {
        Bitmap localBitmap = Bitmap.createBitmap(paramInt1, paramInt2, Bitmap.Config.ARGB_8888);
        float f4 = paramInt1;
        float f1 = f4 / paramBitmap.getWidth();
        float f3 = paramInt2;
        float f2 = f3 / paramBitmap.getHeight();
        f4 /= 2.0F;
        f3 /= 2.0F;
        Object localObject = new Matrix();
        ((Matrix)localObject).setScale(f1, f2, f4, f3);
        Canvas localCanvas = new Canvas(localBitmap);
        localCanvas.setMatrix((Matrix)localObject);
        localObject = new Paint();
        ((Paint)localObject).setFilterBitmap(false);
        ((Paint)localObject).setDither(false);
        ((Paint)localObject).setAntiAlias(false);
        localCanvas.drawBitmap(paramBitmap, f4 - paramBitmap.getWidth() / 2, f3 - paramBitmap.getHeight() / 2, (Paint)localObject);
        return localBitmap;
      }
      catch (OutOfMemoryError localOutOfMemoryError)
      {
        int n;
        for (;;) {}
      }
      paramInt1 /= 2;
      n = paramInt2 / 2;
      if (paramInt1 < 10) {
        break;
      }
      paramInt2 = n;
    } while (n >= 10);
    return null;
  }
  
  public static Bitmap a(Bitmap paramBitmap, int paramInt, Bitmap[] paramArrayOfBitmap)
  {
    Bitmap localBitmap1 = Bitmap.createBitmap(34, 32, Bitmap.Config.ARGB_8888);
    Paint localPaint = new Paint();
    localPaint.setAntiAlias(false);
    localPaint.setDither(false);
    localPaint.setFilterBitmap(false);
    Canvas localCanvas = new Canvas(localBitmap1);
    if (paramBitmap.getHeight() >= 64)
    {
      localCanvas.drawBitmap(Bitmap.createBitmap(paramBitmap, 8, 8, 8, 8, null, false), 4.0F, 0.0F, localPaint);
      localCanvas.drawBitmap(Bitmap.createBitmap(paramBitmap, 40, 8, 8, 8, null, false), 4.0F, 0.0F, localPaint);
      localCanvas.drawBitmap(Bitmap.createBitmap(paramBitmap, 20, 20, 8, 12, null, false), 4.0F, 8.0F, localPaint);
      localCanvas.drawBitmap(Bitmap.createBitmap(paramBitmap, 20, 36, 8, 12, null, false), 4.0F, 8.0F, localPaint);
      localCanvas.drawBitmap(Bitmap.createBitmap(paramBitmap, 36, 52, 4, 12, null, false), 0.0F, 8.0F, localPaint);
      localCanvas.drawBitmap(Bitmap.createBitmap(paramBitmap, 44, 20, 4, 12, null, false), 12.0F, 8.0F, localPaint);
      localCanvas.drawBitmap(Bitmap.createBitmap(paramBitmap, 20, 52, 4, 12, null, false), 4.0F, 20.0F, localPaint);
      localCanvas.drawBitmap(Bitmap.createBitmap(paramBitmap, 4, 20, 4, 12, null, false), 8.0F, 20.0F, localPaint);
      localCanvas.drawBitmap(Bitmap.createBitmap(paramBitmap, 24, 8, 8, 8, null, false), 22.0F, 0.0F, localPaint);
      localCanvas.drawBitmap(Bitmap.createBitmap(paramBitmap, 56, 8, 8, 8, null, false), 22.0F, 0.0F, localPaint);
      localCanvas.drawBitmap(Bitmap.createBitmap(paramBitmap, 32, 20, 8, 12, null, false), 22.0F, 8.0F, localPaint);
      localCanvas.drawBitmap(Bitmap.createBitmap(paramBitmap, 32, 36, 8, 12, null, false), 22.0F, 8.0F, localPaint);
      localCanvas.drawBitmap(Bitmap.createBitmap(paramBitmap, 44, 52, 4, 12, null, false), 18.0F, 8.0F, localPaint);
      localCanvas.drawBitmap(Bitmap.createBitmap(paramBitmap, 52, 20, 4, 12, null, false), 30.0F, 8.0F, localPaint);
      localCanvas.drawBitmap(Bitmap.createBitmap(paramBitmap, 28, 52, 4, 12, null, false), 22.0F, 20.0F, localPaint);
      localCanvas.drawBitmap(Bitmap.createBitmap(paramBitmap, 12, 20, 4, 12, null, false), 26.0F, 20.0F, localPaint);
    }
    else
    {
      localCanvas.drawBitmap(Bitmap.createBitmap(paramBitmap, 8, 8, 8, 8, null, false), 4.0F, 0.0F, localPaint);
      localCanvas.drawBitmap(Bitmap.createBitmap(paramBitmap, 20, 20, 8, 12, null, false), 4.0F, 8.0F, localPaint);
      Bitmap localBitmap2 = Bitmap.createBitmap(paramBitmap, 44, 20, 4, 12, null, false);
      localCanvas.drawBitmap(localBitmap2, 0.0F, 8.0F, localPaint);
      localCanvas.drawBitmap(localBitmap2, 12.0F, 8.0F, localPaint);
      localBitmap2 = Bitmap.createBitmap(paramBitmap, 4, 20, 4, 12, null, false);
      localCanvas.drawBitmap(localBitmap2, 4.0F, 20.0F, localPaint);
      localCanvas.drawBitmap(localBitmap2, 8.0F, 20.0F, localPaint);
      localCanvas.drawBitmap(Bitmap.createBitmap(paramBitmap, 24, 8, 8, 8, null, false), 22.0F, 0.0F, localPaint);
      localCanvas.drawBitmap(Bitmap.createBitmap(paramBitmap, 32, 20, 8, 12, null, false), 22.0F, 8.0F, localPaint);
      localBitmap2 = Bitmap.createBitmap(paramBitmap, 48, 20, 4, 12, null, false);
      localCanvas.drawBitmap(localBitmap2, 18.0F, 8.0F, localPaint);
      localCanvas.drawBitmap(localBitmap2, 30.0F, 8.0F, localPaint);
      paramBitmap = Bitmap.createBitmap(paramBitmap, 12, 20, 4, 12, null, false);
      localCanvas.drawBitmap(paramBitmap, 22.0F, 20.0F, localPaint);
      localCanvas.drawBitmap(paramBitmap, 26.0F, 20.0F, localPaint);
    }
    if (paramArrayOfBitmap != null) {
      paramArrayOfBitmap[0] = localBitmap1;
    }
    return b(localBitmap1, paramInt);
  }
  
  public static Bitmap a(Drawable paramDrawable)
  {
    Object localObject;
    if ((paramDrawable instanceof BitmapDrawable))
    {
      localObject = (BitmapDrawable)paramDrawable;
      if (((BitmapDrawable)localObject).getBitmap() != null) {
        return ((BitmapDrawable)localObject).getBitmap();
      }
    }
    if ((paramDrawable.getIntrinsicWidth() > 0) && (paramDrawable.getIntrinsicHeight() > 0)) {
      localObject = Bitmap.createBitmap(paramDrawable.getIntrinsicWidth(), paramDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
    } else {
      localObject = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
    }
    Canvas localCanvas = new Canvas((Bitmap)localObject);
    paramDrawable.setBounds(0, 0, localCanvas.getWidth(), localCanvas.getHeight());
    paramDrawable.draw(localCanvas);
    return localObject;
  }
  
  public static Uri a(Uri paramUri, Context paramContext)
  {
    paramUri = paramContext.getContentResolver().openInputStream(paramUri);
    paramContext = OmlibApiManager.getInstance(paramContext).getLdClient().Blob.saveAndHashBlob(paramUri);
    if (paramUri != null) {
      paramUri.close();
    }
    return Uri.parse(paramContext.Source);
  }
  
  public static com.a.a.g.g a(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, Context paramContext)
  {
    float f1 = paramFloat3 / paramFloat4;
    if ((paramFloat3 != 0.0F) && (paramFloat4 != 0.0F))
    {
      if (paramFloat1 == -1.0F) {
        return (com.a.a.g.g)((com.a.a.g.g)((com.a.a.g.g)com.a.a.g.g.j(paramContext).a((int)(f1 * paramFloat2), (int)paramFloat2)).a(true)).a(h.b);
      }
      if (paramFloat2 == -1.0F) {
        return (com.a.a.g.g)((com.a.a.g.g)((com.a.a.g.g)com.a.a.g.g.j(paramContext).a((int)paramFloat1, (int)(paramFloat1 * (1.0F / f1)))).a(true)).a(h.b);
      }
      int n;
      int i1;
      if (paramFloat1 / paramFloat2 > f1)
      {
        n = (int)Math.min(paramFloat1, paramFloat3);
        i1 = (int)(n / f1);
      }
      else
      {
        i1 = (int)Math.min(paramFloat2, paramFloat4);
        n = (int)(f1 * i1);
      }
      return (com.a.a.g.g)((com.a.a.g.g)((com.a.a.g.g)com.a.a.g.g.j(paramContext).a(n, i1)).a(true)).a(h.b);
    }
    return (com.a.a.g.g)((com.a.a.g.g)((com.a.a.g.g)com.a.a.g.g.j(paramContext).a((int)paramFloat1, (int)paramFloat2)).a(true)).a(h.b);
  }
  
  public static com.a.a.g.g a(float paramFloat1, float paramFloat2, Integer paramInteger1, Integer paramInteger2, Context paramContext)
  {
    float f2 = 0.0F;
    float f1;
    if (paramInteger1 != null) {
      f1 = paramInteger1.intValue();
    } else {
      f1 = 0.0F;
    }
    if (paramInteger2 != null) {
      f2 = paramInteger2.intValue();
    }
    return a(paramFloat1, paramFloat2, f1, f2, paramContext);
  }
  
  public static com.a.a.g.g a(float paramFloat1, float paramFloat2, b.aem paramAem, Context paramContext)
  {
    Integer localInteger = paramAem.d;
    float f2 = 0.0F;
    float f1;
    if (localInteger != null) {
      f1 = paramAem.d.intValue();
    } else {
      f1 = 0.0F;
    }
    if (paramAem.e != null) {
      f2 = paramAem.e.intValue();
    }
    return a(paramFloat1, paramFloat2, f1, f2, paramContext);
  }
  
  public static com.a.a.g.g a(float paramFloat1, float paramFloat2, b.aet paramAet, Context paramContext)
  {
    Integer localInteger = paramAet.W;
    float f2 = 0.0F;
    float f1;
    if (localInteger != null) {
      f1 = paramAet.W.intValue();
    } else {
      f1 = 0.0F;
    }
    if (paramAet.V != null) {
      f2 = paramAet.V.intValue();
    }
    return a(paramFloat1, paramFloat2, f1, f2, paramContext);
  }
  
  public static com.a.a.g.g a(float paramFloat1, float paramFloat2, b.aft paramAft, Context paramContext)
  {
    if ((paramAft instanceof b.aet)) {
      return a(paramFloat1, paramFloat2, (b.aet)paramAft, paramContext);
    }
    if ((paramAft instanceof b.ape)) {
      return a(paramFloat1, paramFloat2, (b.ape)paramAft, paramContext);
    }
    if ((paramAft instanceof b.ajr)) {
      return a(paramFloat1, paramFloat2, (b.ajr)paramAft, paramContext);
    }
    if ((paramAft instanceof b.aem)) {
      return a(paramFloat1, paramFloat2, (b.aem)paramAft, paramContext);
    }
    if ((paramAft instanceof b.ahq)) {
      return a(paramFloat1, paramFloat2, (b.ahq)paramAft, paramContext);
    }
    return null;
  }
  
  public static com.a.a.g.g a(float paramFloat1, float paramFloat2, b.ahq paramAhq, Context paramContext)
  {
    Integer localInteger = paramAhq.d;
    float f2 = 0.0F;
    float f1;
    if (localInteger != null) {
      f1 = paramAhq.d.intValue();
    } else {
      f1 = 0.0F;
    }
    if (paramAhq.c != null) {
      f2 = paramAhq.c.intValue();
    }
    return a(paramFloat1, paramFloat2, f1, f2, paramContext);
  }
  
  public static com.a.a.g.g a(float paramFloat1, float paramFloat2, b.ajr paramAjr, Context paramContext)
  {
    Integer localInteger = paramAjr.d;
    float f2 = 0.0F;
    float f1;
    if (localInteger != null) {
      f1 = paramAjr.d.intValue();
    } else {
      f1 = 0.0F;
    }
    if (paramAjr.c != null) {
      f2 = paramAjr.c.intValue();
    }
    return a(paramFloat1, paramFloat2, f1, f2, paramContext);
  }
  
  public static com.a.a.g.g a(float paramFloat1, float paramFloat2, b.ape paramApe, Context paramContext)
  {
    Integer localInteger = paramApe.W;
    float f2 = 0.0F;
    float f1;
    if (localInteger != null) {
      f1 = paramApe.W.intValue();
    } else {
      f1 = 0.0F;
    }
    if (paramApe.V != null) {
      f2 = paramApe.V.intValue();
    }
    return a(paramFloat1, paramFloat2, f1, f2, paramContext);
  }
  
  public static File a(Context paramContext, Uri paramUri, boolean paramBoolean)
  {
    a.a localA2 = mobisocial.c.a.a(paramContext.getApplicationContext(), paramUri, 1920);
    if ((localA2 != null) && (localA2.a != null))
    {
      a.a localA1 = localA2;
      if (paramBoolean)
      {
        localA1 = localA2;
        if (localA2.a.length() >= 5242880L)
        {
          localA1 = mobisocial.c.a.a(paramContext.getApplicationContext(), paramUri, 1024);
          if ((localA1 == null) || (localA1.a == null)) {
            throw new IOException("null resized image");
          }
        }
      }
      return localA1.a;
    }
    throw new IOException("null resized image");
  }
  
  public static Boolean a(e paramE, Context paramContext)
  {
    try
    {
      boolean bool = new JSONObject(paramE.a().g().f()).optBoolean("success");
      if (bool) {
        paramContext.getSharedPreferences("prefClashRefresher", 0).edit().putLong("lastProfileRefreshTime", System.currentTimeMillis()).apply();
      }
      return Boolean.valueOf(bool);
    }
    catch (JSONException paramE)
    {
      mobisocial.c.c.a("Arcade", "JSONException", paramE);
    }
    catch (IOException paramE)
    {
      mobisocial.c.c.a("Arcade", "IOException", paramE);
    }
    return null;
  }
  
  public static CharSequence a(Context paramContext, Boolean paramBoolean, String paramString1, String paramString2)
  {
    if (Boolean.TRUE.equals(paramBoolean))
    {
      int n = R.string.omp_post_card_sub_header_2_items;
      paramBoolean = new StringBuilder();
      paramBoolean.append(paramString1);
      paramBoolean.append(" ");
      paramBoolean.append("[img]");
      paramBoolean = new SpannableStringBuilder(paramContext.getString(n, new Object[] { paramBoolean.toString(), paramString2 }));
      paramString2 = android.support.v4.content.c.a(paramContext, R.raw.oma_ic_privatecommunity_stormgray);
      n = UIHelper.convertDiptoPix(paramContext, 12);
      paramString2.setBounds(0, 0, n, n);
      paramBoolean.setSpan(new ImageSpan(paramString2, 1), paramString1.length() + 1, paramString1.length() + "[img]".length() + 1, 17);
      return paramBoolean;
    }
    return new SpannableStringBuilder(paramContext.getString(R.string.omp_post_card_sub_header_2_items, new Object[] { paramString1, paramString2 }));
  }
  
  public static CharSequence a(CharSequence paramCharSequence)
  {
    if (Build.VERSION.SDK_INT >= 18)
    {
      boolean bool = paramCharSequence instanceof Spanned;
      if (!bool) {
        return paramCharSequence;
      }
      if (bool)
      {
        paramCharSequence = new SpannableString(paramCharSequence);
        int i1 = paramCharSequence.length();
        int n = 0;
        ForegroundColorSpan[] arrayOfForegroundColorSpan = (ForegroundColorSpan[])paramCharSequence.getSpans(0, i1, ForegroundColorSpan.class);
        i1 = arrayOfForegroundColorSpan.length;
        while (n < i1)
        {
          ForegroundColorSpan localForegroundColorSpan = arrayOfForegroundColorSpan[n];
          paramCharSequence.setSpan(new ForegroundColorSpan(localForegroundColorSpan.getForegroundColor() | 0xFF000000), paramCharSequence.getSpanStart(localForegroundColorSpan), paramCharSequence.getSpanEnd(localForegroundColorSpan), paramCharSequence.getSpanFlags(localForegroundColorSpan));
          paramCharSequence.removeSpan(localForegroundColorSpan);
          n += 1;
        }
        return paramCharSequence;
      }
      return paramCharSequence;
    }
    return paramCharSequence;
  }
  
  public static String a(long paramLong, boolean paramBoolean)
  {
    if (paramLong < 1000L)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("");
      ((StringBuilder)localObject).append(paramLong);
      return ((StringBuilder)localObject).toString();
    }
    double d1 = paramLong;
    int n = (int)(Math.log(d1) / Math.log(1000.0D));
    if (!paramBoolean)
    {
      localObject = Locale.ENGLISH;
      d2 = Math.pow(1000.0D, n);
      Double.isNaN(d1);
      return String.format((Locale)localObject, "%.0f%c", new Object[] { Double.valueOf(d1 / d2), Character.valueOf("KMB".charAt(n - 1)) });
    }
    Object localObject = Locale.ENGLISH;
    double d2 = Math.pow(1000.0D, n);
    Double.isNaN(d1);
    return String.format((Locale)localObject, "%.1f%c", new Object[] { Double.valueOf(d1 / d2), Character.valueOf("KMB".charAt(n - 1)) });
  }
  
  public static String a(Context paramContext, long paramLong)
  {
    paramLong /= 1000L;
    int n = (int)(paramLong / 60L);
    int i1 = (int)(paramLong % 60L);
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append(Long.toString(n));
    ((StringBuilder)localObject).append(" ");
    ((StringBuilder)localObject).append(paramContext.getResources().getQuantityString(R.plurals.omp_minutes, n));
    String str = ((StringBuilder)localObject).toString();
    localObject = str;
    if (i1 > 0)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(str);
      ((StringBuilder)localObject).append(" ");
      ((StringBuilder)localObject).append(Long.toString(i1));
      ((StringBuilder)localObject).append(" ");
      ((StringBuilder)localObject).append(paramContext.getResources().getQuantityString(R.plurals.omp_seconds, i1));
      localObject = ((StringBuilder)localObject).toString();
    }
    return localObject;
  }
  
  public static String a(Context paramContext, Uri paramUri, String paramString, String[] paramArrayOfString)
  {
    try
    {
      paramContext = paramContext.getContentResolver().query(paramUri, new String[] { "_data" }, paramString, paramArrayOfString, null);
      if (paramContext != null) {
        try
        {
          if (paramContext.moveToFirst())
          {
            paramUri = paramContext.getString(paramContext.getColumnIndexOrThrow("_data"));
            if (paramContext != null) {
              paramContext.close();
            }
            return paramUri;
          }
        }
        finally
        {
          break label82;
        }
      }
      if (paramContext != null) {
        paramContext.close();
      }
      return null;
    }
    finally
    {
      paramContext = null;
      label82:
      if (paramContext != null) {
        paramContext.close();
      }
    }
  }
  
  public static String a(Context paramContext, OMFeed paramOMFeed)
  {
    if (paramOMFeed.communityInfo != null)
    {
      b.kz localKz = (b.kz)mobisocial.b.a.a(paramOMFeed.communityInfo, b.kz.class);
      if (localKz.b != null) {
        return String.format("%s - %s", new Object[] { localKz.b, Utils.formatFeedTimestamp(paramOMFeed.renderableTime, paramContext) });
      }
    }
    return Utils.formatFeedTimestamp(paramOMFeed.renderableTime, paramContext);
  }
  
  public static String a(Context paramContext, OMObject paramOMObject, String paramString)
  {
    try
    {
      switch (Integer.parseInt(paramOMObject.text))
      {
      case 1: 
        return paramContext.getString(R.string.omp_has_started_streaming, new Object[] { paramString });
      }
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    paramContext = paramContext.getString(R.string.omp_has_stopped_streaming, new Object[] { paramString });
    return paramContext;
    return null;
    return paramOMObject.text;
  }
  
  public static String a(String paramString, e.a paramA, Context paramContext)
  {
    Integer localInteger = paramA.c;
    int n = paramString.indexOf("\n====");
    Object localObject = paramString;
    if (n > 0) {
      localObject = paramString.substring(0, n);
    }
    paramString = new StringBuilder((String)localObject);
    if (localInteger != null)
    {
      n = i.length - 1;
      while (n >= 0)
      {
        if (localInteger.intValue() > i[n])
        {
          n += 1;
          break label84;
        }
        n -= 1;
      }
      n = 1;
      label84:
      paramString.append("\n==== ");
      paramString.append(paramContext.getString(R.string.omp_clash_arena, new Object[] { Integer.valueOf(n) }));
      paramString.append(" ====");
    }
    localObject = paramA.b;
    if (localObject != null)
    {
      paramString.append("\n==== ");
      paramString.append(paramContext.getString(R.string.omp_clash_highest_trophies, new Object[] { localObject }));
      paramString.append(" ====");
    }
    paramString.append("\n");
    paramContext = new StringBuilder();
    paramContext.append("https://statsroyale.com/profile/");
    paramContext.append(paramA.a);
    paramString.append(paramContext.toString());
    return paramString.toString();
  }
  
  public static String a(LDObjects.User paramUser)
  {
    if (paramUser == null) {
      return "???";
    }
    if ((paramUser.OmletId != null) && (paramUser.OmletId.b != null)) {
      return paramUser.OmletId.b;
    }
    if (paramUser.DisplayName != null) {
      return paramUser.DisplayName;
    }
    return "???";
  }
  
  public static String a(b.afc paramAfc)
  {
    if ((paramAfc != null) && (paramAfc.a != null))
    {
      if ((paramAfc.a.f != null) && (paramAfc.a.f.b != null)) {
        return paramAfc.a.f.b;
      }
      if (paramAfc.a.a != null) {
        return paramAfc.a.a;
      }
      return "???";
    }
    return "???";
  }
  
  public static String a(b.aft paramAft)
  {
    if (paramAft == null) {
      return null;
    }
    if ((paramAft instanceof b.aem)) {
      return ((b.aem)paramAft).c;
    }
    if ((paramAft instanceof b.aeq)) {
      return ((b.aeq)paramAft).U;
    }
    if ((paramAft instanceof b.aet)) {
      return ((b.aet)paramAft).U;
    }
    if ((paramAft instanceof b.by)) {
      return ((b.by)paramAft).U;
    }
    if ((paramAft instanceof b.ajr)) {
      return ((b.ajr)paramAft).b;
    }
    if ((paramAft instanceof b.ape)) {
      return ((b.ape)paramAft).U;
    }
    if ((paramAft instanceof b.ahq)) {
      return ((b.ahq)paramAft).b;
    }
    if ((paramAft instanceof b.ajh)) {
      return b((b.ajh)paramAft);
    }
    return null;
  }
  
  public static String a(b.ahz paramAhz)
  {
    if (paramAhz == null) {
      return "???";
    }
    if ((paramAhz.e != null) && (paramAhz.e.b != null)) {
      return paramAhz.e.b;
    }
    if (paramAhz.b != null) {
      return paramAhz.b;
    }
    return "???";
  }
  
  public static String a(b.aia paramAia)
  {
    if (paramAia == null) {
      return "???";
    }
    if ((paramAia.a.a.f != null) && (paramAia.a.a.f.b != null)) {
      return paramAia.a.a.f.b;
    }
    if (paramAia.a.a.c != null) {
      return paramAia.a.a.c;
    }
    return "???";
  }
  
  public static String a(b.amn paramAmn)
  {
    if ((paramAmn != null) && (paramAmn.n != null)) {
      return (String)paramAmn.n.get("StreamPreviewLink");
    }
    return null;
  }
  
  public static String a(b.aom paramAom)
  {
    if (paramAom == null) {
      return "???";
    }
    if ((paramAom.f != null) && (paramAom.f.b != null)) {
      return paramAom.f.b;
    }
    if (paramAom.c != null) {
      return paramAom.c;
    }
    return "???";
  }
  
  public static String a(b.eo paramEo)
  {
    if ("Event".equals(paramEo.a))
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("https://arcade.omlet.me/event/");
      localStringBuilder.append(paramEo.b);
      return localStringBuilder.toString();
    }
    if ("Managed".equals(paramEo.a))
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("https://arcade.omlet.me/community/");
      localStringBuilder.append(paramEo.b);
      return localStringBuilder.toString();
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("https://arcade.omlet.me/game/");
    localStringBuilder.append(paramEo.b);
    return localStringBuilder.toString();
  }
  
  public static String a(b.er paramEr)
  {
    b.eo localEo = paramEr.k;
    if (f.a(paramEr))
    {
      paramEr = new StringBuilder();
      paramEr.append("https://arcade.omlet.me/squad/");
      paramEr.append(localEo.b);
      return paramEr.toString();
    }
    if ("Managed".equals(localEo.a))
    {
      paramEr = new StringBuilder();
      paramEr.append("https://arcade.omlet.me/community/");
      paramEr.append(localEo.b);
      return paramEr.toString();
    }
    if ("Event".equals(localEo.a))
    {
      paramEr = new StringBuilder();
      paramEr.append("https://arcade.omlet.me/event/");
      paramEr.append(localEo.b);
      return paramEr.toString();
    }
    paramEr = new StringBuilder();
    paramEr.append("https://arcade.omlet.me/game/");
    paramEr.append(localEo.b);
    return paramEr.toString();
  }
  
  public static String a(OMFeed paramOMFeed)
  {
    if (paramOMFeed.communityInfo != null)
    {
      b.kz localKz = (b.kz)mobisocial.b.a.a(paramOMFeed.communityInfo, b.kz.class);
      return String.format("%s - %s", new Object[] { paramOMFeed.name, localKz.b });
    }
    return paramOMFeed.name;
  }
  
  public static String a(AccountProfile paramAccountProfile)
  {
    if (paramAccountProfile == null) {
      return "???";
    }
    if (paramAccountProfile.omletId != null) {
      return paramAccountProfile.omletId;
    }
    if (paramAccountProfile.name != null) {
      return paramAccountProfile.name;
    }
    return "???";
  }
  
  public static b.eo a(b.agh paramAgh)
  {
    b.eo localEo = new b.eo();
    if (paramAgh.a.equals("Game")) {
      localEo.a = "App";
    } else {
      localEo.a = "Managed";
    }
    localEo.b = paramAgh.b;
    localEo.c = null;
    return localEo;
  }
  
  public static mobisocial.omlet.e.c a(TextView paramTextView, ViewGroup paramViewGroup, x paramX, c.f paramF)
  {
    return a(paramTextView, paramViewGroup, paramX, paramF, null);
  }
  
  public static mobisocial.omlet.e.c a(TextView paramTextView, ViewGroup paramViewGroup, x paramX, c.f paramF, UIHelper.StreamUriOnClickListener paramStreamUriOnClickListener)
  {
    mobisocial.omlet.e.c[] arrayOfC = new mobisocial.omlet.e.c[1];
    UIHelper.wrapUrlSpans(paramTextView, paramStreamUriOnClickListener);
    SpannableString localSpannableString = new SpannableString(paramTextView.getText());
    paramStreamUriOnClickListener = Pattern.compile("(\\s|^)(@[A-Za-z0-9._]+)").matcher(localSpannableString);
    Context localContext = paramTextView.getContext();
    int n;
    int i1;
    while (paramStreamUriOnClickListener.find())
    {
      n = paramStreamUriOnClickListener.start(2);
      i1 = paramStreamUriOnClickListener.end(2);
      localSpannableString.setSpan(new r.12(paramTextView, n, i1, arrayOfC, localContext, paramViewGroup, paramX, paramF), n, i1, 18);
      localSpannableString.setSpan(new StyleSpan(1), n, i1, 18);
    }
    paramViewGroup = Pattern.compile("(\\s|^)(#[(?U)\\p{Alpha}0-9]+)").matcher(localSpannableString);
    while (paramViewGroup.find())
    {
      n = paramViewGroup.start(2);
      i1 = paramViewGroup.end(2);
      localSpannableString.setSpan(new r.21(paramTextView, n, i1, localContext), n, i1, 18);
    }
    if (paramTextView.getMovementMethod() == null) {
      paramTextView.setMovementMethod(LinkMovementMethod.getInstance());
    }
    paramTextView.setText(localSpannableString, TextView.BufferType.SPANNABLE);
    return arrayOfC[0];
  }
  
  public static r.d a(b.ajh paramAjh)
  {
    r.d localD = new r.d();
    Iterator localIterator = paramAjh.a.iterator();
    while (localIterator.hasNext())
    {
      Object localObject = (b.aji)localIterator.next();
      if ((((b.aji)localObject).e != null) && (localD.b == null))
      {
        localD.b = ((b.aji)localObject).e.a;
      }
      else
      {
        Integer localInteger;
        if (((b.aji)localObject).b != null)
        {
          if (((b.apb)((b.aji)localObject).b.a.get(0)).f == null) {
            paramAjh = ((b.apb)((b.aji)localObject).b.a.get(0)).d;
          } else {
            paramAjh = ((b.apb)((b.aji)localObject).b.a.get(0)).f;
          }
          localInteger = ((b.apb)((b.aji)localObject).b.a.get(0)).h;
          localObject = ((b.apb)((b.aji)localObject).b.a.get(0)).g;
          localD.a.add(new r.i(localInteger, (Integer)localObject, paramAjh, true));
        }
        else if (((b.aji)localObject).c != null)
        {
          if (((b.zv)((b.aji)localObject).c.a.get(0)).b == null) {
            paramAjh = ((b.zv)((b.aji)localObject).c.a.get(0)).a;
          } else {
            paramAjh = ((b.zv)((b.aji)localObject).c.a.get(0)).b;
          }
          localInteger = ((b.zv)((b.aji)localObject).c.a.get(0)).d;
          localObject = ((b.zv)((b.aji)localObject).c.a.get(0)).c;
          localD.a.add(new r.i(localInteger, (Integer)localObject, paramAjh, false));
        }
        else if (((b.aji)localObject).d != null)
        {
          paramAjh = ((b.aji)localObject).d.a.iterator();
          while (paramAjh.hasNext())
          {
            localObject = (b.abv)paramAjh.next();
            if (((b.abv)localObject).c != null) {
              localD.a.add(new r.i(((b.abv)localObject).d, ((b.abv)localObject).e, ((b.abv)localObject).c, false));
            }
          }
        }
        else if (((b.aji)localObject).f != null)
        {
          localD.c += ((b.aji)localObject).f.a.size();
          paramAjh = ((b.aji)localObject).f.a.iterator();
          while (paramAjh.hasNext())
          {
            localObject = (b.lm)paramAjh.next();
            if (((b.lm)localObject).e != null) {
              localD.a.add(new r.i(((b.lm)localObject).f, ((b.lm)localObject).g, ((b.lm)localObject).e, false, "Skin".equals(((b.lm)localObject).d)));
            }
          }
        }
      }
    }
    return localD;
  }
  
  public static r.e a(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    float f1 = paramInt4;
    float f2 = f1 / paramInt2;
    float f3 = paramInt3;
    float f4 = f3 / paramInt1;
    if (f2 > f4)
    {
      paramInt1 = (int)Math.ceil(f1 / f2);
      paramInt2 = (int)Math.ceil(f3 / f2);
    }
    else
    {
      paramInt1 = (int)Math.ceil(f1 / f4);
      paramInt2 = (int)Math.ceil(f3 / f4);
    }
    return new r.e(paramInt2, paramInt1);
  }
  
  public static r.i a(MediaUploadIntentService.f paramF)
  {
    Object localObject2;
    do
    {
      paramF = paramF.a.iterator();
      Object localObject1;
      while (!((Iterator)localObject1).hasNext())
      {
        do
        {
          do
          {
            if (!paramF.hasNext()) {
              break label288;
            }
            localObject1 = (b.aji)paramF.next();
            if (((b.aji)localObject1).b != null)
            {
              localObject1 = (b.apb)((b.aji)localObject1).b.a.get(0);
              if (((b.apb)localObject1).f == null) {
                paramF = ((b.apb)localObject1).d;
              } else {
                paramF = ((b.apb)localObject1).f;
              }
              return new r.i(((b.apb)localObject1).h, ((b.apb)localObject1).g, paramF, true);
            }
            if (((b.aji)localObject1).c != null)
            {
              localObject1 = (b.zv)((b.aji)localObject1).c.a.get(0);
              if (((b.zv)localObject1).b == null) {
                paramF = ((b.zv)localObject1).a;
              } else {
                paramF = ((b.zv)localObject1).b;
              }
              return new r.i(((b.zv)localObject1).d, ((b.zv)localObject1).c, paramF, false);
            }
            if (((b.aji)localObject1).d == null) {
              break;
            }
            localObject1 = ((b.aji)localObject1).d.a.iterator();
          } while (!((Iterator)localObject1).hasNext());
          localObject2 = (b.abv)((Iterator)localObject1).next();
          if (((b.abv)localObject2).c == null) {
            break;
          }
          return new r.i(((b.abv)localObject2).d, ((b.abv)localObject2).e, ((b.abv)localObject2).c, false);
        } while (((b.aji)localObject1).f == null);
        localObject1 = ((b.aji)localObject1).f.a.iterator();
      }
      localObject2 = (b.lm)((Iterator)localObject1).next();
    } while (((b.lm)localObject2).e == null);
    return new r.i(((b.lm)localObject2).f, ((b.lm)localObject2).g, ((b.lm)localObject2).e, false, true);
    label288:
    return null;
  }
  
  public static r.j a(Context paramContext, b.bg paramBg)
  {
    if (paramBg == null) {
      return null;
    }
    if (k == null) {
      k = Boolean.valueOf(k(paramContext));
    }
    boolean bool = l(paramContext);
    if (((a()) || (bool)) && (!TextUtils.isEmpty(paramBg.g))) {
      return new r.j(r.g.Amazon, paramBg.g);
    }
    if ((Boolean.TRUE.equals(k)) && (!TextUtils.isEmpty(paramBg.c))) {
      return new r.j(r.g.PlayStore, paramBg.c);
    }
    if (a()) {
      return null;
    }
    if (!TextUtils.isEmpty(paramBg.d)) {
      return new r.j(r.g.Baidu, paramBg.d);
    }
    if (!TextUtils.isEmpty(paramBg.e)) {
      return new r.j(r.g.Tencent, paramBg.e);
    }
    return null;
  }
  
  public static OMFeed a(OmlibApiManager paramOmlibApiManager, b.lg paramLg, b.ahe paramAhe)
  {
    return a(paramOmlibApiManager, paramLg, paramAhe, null);
  }
  
  public static OMFeed a(OmlibApiManager paramOmlibApiManager, b.lg paramLg, b.ahe paramAhe, String paramString)
  {
    return (OMFeed)paramOmlibApiManager.getLdClient().callOnDbThreadAndWait(new r.1(paramOmlibApiManager, paramLg, paramAhe, paramString));
  }
  
  public static void a(Activity paramActivity, String paramString, t.a paramA, d paramD, long paramLong)
  {
    mobisocial.omlet.overlaychat.viewhandlers.c localC = mobisocial.omlet.overlaychat.viewhandlers.c.t();
    if (localC != null) {
      localC.u();
    }
    if (aa.a(paramActivity))
    {
      paramActivity = new mobisocial.omlet.overlaychat.viewhandlers.c(paramString, paramA, paramActivity.getApplicationContext(), UIHelper.getWindowFlagForDialog(), paramD, paramLong);
      paramActivity.a(null);
      paramActivity.g();
    }
  }
  
  public static void a(Context paramContext)
  {
    Object localObject1 = paramContext.getSharedPreferences("prefClashRefresher", 0);
    long l1 = ((SharedPreferences)localObject1).getLong("lastRegexCheck", 0L);
    if (System.currentTimeMillis() - 3600000L > l1)
    {
      Object localObject2 = new b.of();
      try
      {
        localObject2 = (b.og)OmlibApiManager.getInstance(paramContext).getLdClient().msgClient().callSynchronous((b.aay)localObject2, b.og.class);
        paramContext = ((SharedPreferences)localObject1).edit();
        localObject1 = ((b.og)localObject2).a.entrySet().iterator();
        while (((Iterator)localObject1).hasNext())
        {
          localObject2 = (Map.Entry)((Iterator)localObject1).next();
          paramContext.putString((String)((Map.Entry)localObject2).getKey(), (String)((Map.Entry)localObject2).getValue());
        }
        paramContext.putLong("lastRegexCheck", System.currentTimeMillis());
        paramContext.apply();
        return;
      }
      catch (LongdanException paramContext)
      {
        mobisocial.c.c.d("Arcade", "Failed to get clash regex", paramContext);
      }
    }
  }
  
  private static void a(Context paramContext, Intent paramIntent, String paramString1, String paramString2)
  {
    a(paramContext, paramIntent, paramString1, paramString2, null);
  }
  
  private static void a(Context paramContext, Intent paramIntent, String paramString1, String paramString2, LabeledIntent paramLabeledIntent)
  {
    paramLabeledIntent = a(paramContext, paramIntent, paramLabeledIntent);
    String str = paramIntent.getStringExtra("android.intent.extra.TEXT");
    Intent localIntent = new Intent(paramContext, ChooserActivity.class);
    if (!(paramContext instanceof Activity)) {
      localIntent.addFlags(268468224);
    }
    localIntent.putExtra("android.intent.extra.INITIAL_INTENTS", paramLabeledIntent);
    localIntent.putExtra("omlet.intent.extra.CHOOSER_TITLE", paramString2);
    localIntent.putExtra("shareCategory", paramString1);
    localIntent.putExtra("extraIsGamerStats", paramIntent.getBooleanExtra("extraIsGamerStats", false));
    if (paramIntent.hasExtra("streamerAccount")) {
      localIntent.putExtra("streamerAccount", paramIntent.getStringExtra("streamerAccount"));
    }
    if (str != null)
    {
      localIntent.putExtra("android.intent.extra.TEXT", str);
      localIntent.setClass(paramContext, a);
    }
    paramContext.startActivity(localIntent);
  }
  
  public static void a(Context paramContext, Uri paramUri)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setFlags(268435457);
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramContext.getPackageName());
    localStringBuilder.append(".fileprovider");
    localIntent.setDataAndType(FileProvider.a(paramContext, localStringBuilder.toString(), new File(paramUri.getPath())), "minecraft/world");
    if (paramContext.getPackageManager().resolveActivity(localIntent, 0) != null)
    {
      paramContext.startActivity(localIntent);
      return;
    }
    if (Boolean.TRUE.equals(H(paramContext)))
    {
      OMToast.makeText(paramContext, paramContext.getString(R.string.minecraft_download_world_16_required), 1).show();
      return;
    }
    OMToast.makeText(paramContext, paramContext.getString(R.string.minecraft_download_world_failed_install_minecraft), 0).show();
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("from", paramString3);
    OmlibApiManager.getInstance(paramContext).analytics().trackEvent(b.b.Profile, b.a.Share, localHashMap);
    paramString3 = new Intent("android.intent.action.SEND");
    paramString3.setType("text/plain");
    paramString3.putExtra("android.intent.extra.TEXT", b(paramContext, paramString1, paramString2));
    if (paramString1.equals(OmlibApiManager.getInstance(paramContext).auth().getAccount())) {}
    for (paramString1 = b.b.MyProfile;; paramString1 = b.b.Profile)
    {
      paramString1 = paramString1.name();
      break;
    }
    a(paramContext, paramString3, paramString1, paramContext.getString(R.string.omp_share_profile));
  }
  
  public static void a(Context paramContext, String paramString, b.cq paramCq)
  {
    Intent localIntent = new Intent("mobisocial.arcade.action.VIEW_TRANSACTION_DIALOG");
    localIntent.setPackage(paramContext.getPackageName());
    if (!(paramContext instanceof Activity))
    {
      localIntent.addFlags(276824064);
      if (!paramContext.getPackageName().equals(OmletGameSDK.getLatestPackage())) {
        localIntent.addFlags(32768);
      }
    }
    localIntent.addFlags(131072);
    localIntent.putExtra("EXTRA_PRODUCT_TYPE", paramString);
    localIntent.putExtra("EXTRA_PRODUCT", mobisocial.b.a.b(paramCq));
    paramContext.startActivity(localIntent);
  }
  
  public static void a(Context paramContext, String paramString, PresenceState paramPresenceState, boolean paramBoolean)
  {
    a(paramContext, paramString, paramPresenceState, paramBoolean, null);
  }
  
  public static void a(Context paramContext, String paramString, PresenceState paramPresenceState, boolean paramBoolean, Integer paramInteger)
  {
    OMAccount localOMAccount = mobisocial.omlet.util.c.a(paramContext, paramString);
    if ((localOMAccount != null) && (localOMAccount.blocked))
    {
      al.b(paramContext, String.format(paramContext.getString(R.string.minecraft_join_blocked_user_game), new Object[] { localOMAccount.name }), -1).b();
      return;
    }
    if (a(paramPresenceState))
    {
      if (paramInteger != null)
      {
        new t(paramContext, paramInteger.intValue(), paramString, paramPresenceState, true).executeOnExecutor(OmlibApiManager.THREAD_POOL_EXECUTOR, new Void[0]);
        return;
      }
      new t(paramContext, paramString, paramPresenceState, true).executeOnExecutor(OmlibApiManager.THREAD_POOL_EXECUTOR, new Void[0]);
      return;
    }
    a(paramContext, paramString, paramPresenceState, paramBoolean, false);
  }
  
  public static void a(Context paramContext, String paramString, PresenceState paramPresenceState, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (!OmletGameSDK.getOverlayPermissionChecker().checkAndRequest(paramContext)) {
      return;
    }
    if (paramPresenceState.extraGameData == null) {
      return;
    }
    String str2 = (String)paramPresenceState.extraGameData.get("MCPEServerIdentifierB64");
    String str1 = (String)paramPresenceState.extraGameData.get("MCPEServerIdentifier");
    if (str2 != null) {
      paramPresenceState = Base64.decode(str2, 0);
    } else {
      paramPresenceState = null;
    }
    paramPresenceState = a(paramContext, str1, paramPresenceState, true, paramString);
    if (paramPresenceState == null) {
      return;
    }
    if ((!paramBoolean2) && (paramString != null))
    {
      a(paramContext, paramString, paramPresenceState, paramBoolean1);
      return;
    }
    b(paramContext, paramPresenceState, paramBoolean1);
  }
  
  public static void a(Context paramContext, String paramString, boolean paramBoolean)
  {
    OmlibApiManager.getInstance(paramContext).analytics().trackEvent(b.b.Community, b.a.Share);
    Intent localIntent = new Intent("android.intent.action.SEND");
    localIntent.setType("text/plain");
    StringBuilder localStringBuilder;
    if (paramBoolean)
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("https://arcade.omlet.me/community/");
      localStringBuilder.append(paramString);
      paramString = localStringBuilder.toString();
    }
    else
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("https://arcade.omlet.me/game/");
      localStringBuilder.append(paramString);
      paramString = localStringBuilder.toString();
    }
    localIntent.putExtra("android.intent.extra.TEXT", paramString);
    try
    {
      paramString = b(paramContext, paramString, false);
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      for (;;) {}
    }
    paramString = null;
    a(paramContext, localIntent, b.b.Community.name(), paramContext.getString(R.string.omp_share_community), paramString);
  }
  
  private static void a(Context paramContext, String paramString, String[] paramArrayOfString, boolean paramBoolean)
  {
    new r.26(paramContext, paramString, paramArrayOfString, paramBoolean).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
  }
  
  public static void a(Context paramContext, Map<String, Boolean> paramMap)
  {
    paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext);
    if (paramMap != null)
    {
      paramMap = paramMap.entrySet().iterator();
      while (paramMap.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)paramMap.next();
        SharedPreferences.Editor localEditor = paramContext.edit();
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("IN_APP_STREAM_");
        localStringBuilder.append((String)localEntry.getKey());
        localEditor.putBoolean(localStringBuilder.toString(), ((Boolean)localEntry.getValue()).booleanValue()).apply();
      }
    }
  }
  
  public static void a(Context paramContext, b.aft paramAft)
  {
    a(paramContext, paramAft, false);
  }
  
  public static void a(Context paramContext, b.aft paramAft, q.a paramA, Integer paramInteger)
  {
    b.anz localAnz = new b.anz();
    localAnz.a = new b.fg();
    HashSet localHashSet1 = new HashSet();
    HashSet localHashSet2 = new HashSet();
    Object localObject2 = LayoutInflater.from(paramContext).inflate(R.layout.omp_hide_content_dialog, null);
    ListView localListView = (ListView)((View)localObject2).findViewById(R.id.list);
    HashMap localHashMap = new HashMap();
    String[] arrayOfString = new String[paramAft.r.size() + 1];
    int n = 0;
    while (n < paramAft.r.size())
    {
      localHashMap.put(paramAft.r.get(n), a((b.agh)paramAft.q.get(n)));
      arrayOfString[n] = ((String)paramAft.r.get(n));
      n += 1;
    }
    arrayOfString[paramAft.r.size()] = b(paramAft);
    Object localObject1 = new AlertDialog.Builder(paramContext);
    ((AlertDialog.Builder)localObject1).setView((View)localObject2);
    ((AlertDialog.Builder)localObject1).setTitle(paramContext.getString(R.string.omp_hide_posts_from));
    localObject2 = new ArrayAdapter(paramContext, 17367056, arrayOfString);
    localListView.setAdapter((ListAdapter)localObject2);
    ((AlertDialog.Builder)localObject1).setPositiveButton(paramContext.getString(R.string.omp_hide), null);
    ((AlertDialog.Builder)localObject1).setNegativeButton(paramContext.getString(R.string.omp_dialog_cancel), new r.10());
    localObject1 = ((AlertDialog.Builder)localObject1).create();
    if (paramInteger != null) {
      ((AlertDialog)localObject1).getWindow().setType(paramInteger.intValue());
    } else if (!s(paramContext)) {
      ((AlertDialog)localObject1).getWindow().setType(UIHelper.getWindowFlagForDialog());
    }
    ((AlertDialog)localObject1).show();
    localListView.setOnItemClickListener(new r.11((ArrayAdapter)localObject2, localHashMap, arrayOfString, localHashSet1, localHashSet2, paramAft));
    ((AlertDialog)localObject1).getButton(-1).setOnClickListener(new r.13(paramContext, paramAft, paramA, localHashSet2, localHashSet1, localAnz, (AlertDialog)localObject1));
  }
  
  public static void a(Context paramContext, b.aft paramAft, boolean paramBoolean)
  {
    Object localObject2 = paramAft.F;
    Object localObject1 = localObject2;
    if (TextUtils.isEmpty((CharSequence)localObject2)) {
      localObject1 = paramAft.E;
    }
    if (localObject1 == null)
    {
      paramContext = new StringBuilder();
      paramContext.append("Post does not have url! Cannot share!\n");
      paramContext.append(paramAft.toString());
      mobisocial.c.c.a("Arcade", paramContext.toString());
      return;
    }
    localObject2 = new Intent("android.intent.action.SEND");
    ((Intent)localObject2).setType("text/plain");
    ((Intent)localObject2).putExtra("android.intent.extra.TEXT", (String)localObject1);
    ((Intent)localObject2).putExtra("extraIsGamerStats", paramBoolean);
    boolean bool2 = paramAft instanceof b.ajr;
    boolean bool1 = false;
    paramBoolean = false;
    if (bool2)
    {
      paramAft = paramContext.getString(R.string.omp_share_screenshot);
      paramBoolean = bool1;
    }
    else if ((paramAft instanceof b.aet))
    {
      paramAft = paramContext.getString(R.string.omp_share_mod);
      paramBoolean = bool1;
    }
    else if ((paramAft instanceof b.ape))
    {
      paramAft = paramContext.getString(R.string.omp_share_video);
      paramBoolean = bool1;
    }
    else if ((paramAft instanceof b.ahq))
    {
      paramAft = paramContext.getString(R.string.omp_share_quiz);
      paramBoolean = bool1;
    }
    else if ((paramAft instanceof b.aem))
    {
      if (((b.aem)paramAft).a == null) {
        paramBoolean = true;
      }
      paramAft = paramContext.getString(R.string.omp_share_text);
    }
    else if ((paramAft instanceof b.ajh))
    {
      paramAft = paramContext.getString(R.string.omp_share_story);
      paramBoolean = bool1;
    }
    else
    {
      paramAft = null;
      paramBoolean = bool1;
    }
    try
    {
      localObject1 = b(paramContext, (String)localObject1, paramBoolean);
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;) {}
    }
    localObject1 = null;
    a(paramContext, (Intent)localObject2, b.b.Profile.name(), paramAft, (LabeledIntent)localObject1);
  }
  
  public static void a(Context paramContext, b.eo paramEo)
  {
    new r.25(paramContext).executeOnExecutor(OmlibApiManager.THREAD_POOL_EXECUTOR, new b.eo[] { paramEo });
  }
  
  public static void a(Context paramContext, b.zs paramZs, b.aft paramAft, q.a paramA, Integer paramInteger)
  {
    b.anz localAnz = new b.anz();
    localAnz.a = new b.fg();
    HashSet localHashSet1 = new HashSet();
    HashSet localHashSet2 = new HashSet();
    Object localObject = LayoutInflater.from(paramContext).inflate(R.layout.omp_hide_content_dialog, null);
    ListView localListView = (ListView)((View)localObject).findViewById(R.id.list);
    HashMap localHashMap = new HashMap();
    boolean bool1;
    if ((paramZs.c != null) && (!paramZs.c.b.equalsIgnoreCase(paramZs.e.b))) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    boolean bool2 = paramZs.e.p;
    int i1 = paramAft.r.size();
    int n = i1;
    if (bool1) {
      n = i1 + 1;
    }
    i1 = n;
    if (!bool2) {
      i1 = n + 1;
    }
    String[] arrayOfString = new String[i1];
    n = 0;
    while (n < paramAft.r.size())
    {
      localHashMap.put(paramAft.r.get(n), a((b.agh)paramAft.q.get(n)));
      arrayOfString[n] = ((String)paramAft.r.get(n));
      n += 1;
    }
    if (bool1)
    {
      arrayOfString[(paramAft.r.size() + 0)] = paramContext.getResources().getString(R.string.omp_hide_user_recommendations, new Object[] { paramZs.c.c });
      n = 1;
    }
    else
    {
      n = 0;
    }
    if (!bool2) {
      arrayOfString[(paramAft.r.size() + n)] = paramContext.getResources().getString(R.string.omp_hide_user_posts, new Object[] { paramZs.e.c });
    }
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(paramContext);
    localBuilder.setView((View)localObject);
    localBuilder.setTitle(paramContext.getString(R.string.omp_hide_title));
    localListView.setAdapter(new ArrayAdapter(paramContext, 17367056, arrayOfString));
    localBuilder.setPositiveButton(paramContext.getString(R.string.omp_hide), null);
    localBuilder.setNegativeButton(paramContext.getString(R.string.omp_dialog_cancel), new r.14());
    localObject = localBuilder.create();
    if (paramInteger != null) {
      ((AlertDialog)localObject).getWindow().setType(paramInteger.intValue());
    } else if (!s(paramContext)) {
      ((AlertDialog)localObject).getWindow().setType(UIHelper.getWindowFlagForDialog());
    }
    ((AlertDialog)localObject).show();
    localListView.setOnItemClickListener(new r.15(paramAft, localHashMap, arrayOfString, localHashSet1, bool1, paramZs, localHashSet2));
    ((AlertDialog)localObject).getButton(-1).setOnClickListener(new r.16(paramContext, paramAft, paramA, localHashSet2, localHashSet1, localAnz, (AlertDialog)localObject));
  }
  
  public static void a(Context paramContext, e.a paramA, b.mb paramMb)
  {
    if (paramA != null)
    {
      paramMb.d.b = a(paramMb.d.b, paramA, paramContext);
      paramA = new b.akt();
      paramA.b = paramMb;
    }
    try
    {
      OmlibApiManager.getInstance(paramContext).getLdClient().msgClient().callSynchronous(paramA, b.aly.class);
      return;
    }
    catch (LongdanException paramContext)
    {
      for (;;) {}
    }
    mobisocial.c.c.b("Arcade", "failed to set gameId with new clash info");
  }
  
  public static void a(Context paramContext, OMFeed paramOMFeed, String paramString1, String paramString2)
  {
    paramContext = new mobisocial.omlet.overlaychat.viewhandlers.g(paramOMFeed, paramContext.getApplicationContext(), UIHelper.getWindowFlagForDialog(), paramString1, paramString2);
    paramContext.a(null);
    paramContext.g();
  }
  
  public static void a(Context paramContext, GifSendable paramGifSendable, Uri paramUri)
  {
    paramContext = OmlibApiManager.getInstance(paramContext);
    mobisocial.c.d.b(new r.23(paramContext, paramUri, paramGifSendable));
    paramGifSendable = new HashMap();
    paramGifSendable.put(mobisocial.c.b.b, mobisocial.c.b.d);
    paramContext.getLdClient().Analytics.trackEvent(b.b.Send.name(), b.a.Gif.name(), paramGifSendable);
  }
  
  public static void a(Intent paramIntent, int paramInt, Context paramContext)
  {
    if (!s(paramContext)) {
      paramIntent.addFlags(268435456);
    }
    if (paramIntent.resolveActivity(paramContext.getPackageManager()) != null)
    {
      paramContext.startActivity(paramIntent);
      return;
    }
    OMToast.makeText(paramContext, paramInt, 0).show();
  }
  
  public static void a(BottomNavigationView paramBottomNavigationView)
  {
    paramBottomNavigationView = (android.support.design.internal.c)paramBottomNavigationView.getChildAt(0);
    try
    {
      Object localObject = paramBottomNavigationView.getClass().getDeclaredField("mShiftingMode");
      ((Field)localObject).setAccessible(true);
      ((Field)localObject).setBoolean(paramBottomNavigationView, false);
      ((Field)localObject).setAccessible(false);
      int n = 0;
      while (n < paramBottomNavigationView.getChildCount())
      {
        localObject = (android.support.design.internal.a)paramBottomNavigationView.getChildAt(n);
        ((android.support.design.internal.a)localObject).setShiftingMode(false);
        ((android.support.design.internal.a)localObject).setChecked(((android.support.design.internal.a)localObject).getItemData().isChecked());
        n += 1;
      }
    }
    catch (NoSuchFieldException paramBottomNavigationView)
    {
      for (;;) {}
    }
    catch (IllegalAccessException paramBottomNavigationView)
    {
      for (;;) {}
    }
    Log.d("Arcade", "Unable to change value of shift mode");
    return;
    Log.d("Arcade", "Unable to get shift mode field");
  }
  
  public static void a(ImageView paramImageView, String paramString)
  {
    a(paramImageView, paramString, null);
  }
  
  public static void a(ImageView paramImageView, String paramString, r.b paramB)
  {
    paramImageView.getViewTreeObserver().addOnGlobalLayoutListener(new r.24(paramImageView, paramString, paramB));
  }
  
  public static void a(TextView paramTextView, ViewGroup paramViewGroup, x paramX)
  {
    a(paramTextView, paramViewGroup, paramX, null, null);
  }
  
  /* Error */
  public static void a(File paramFile1, File paramFile2)
  {
    // Byte code:
    //   0: new 2339	java/io/FileInputStream
    //   3: dup
    //   4: aload_0
    //   5: invokespecial 2342	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   8: astore_0
    //   9: new 2344	java/io/FileOutputStream
    //   12: dup
    //   13: aload_1
    //   14: invokespecial 2345	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   17: astore_1
    //   18: sipush 1024
    //   21: newarray byte
    //   23: astore_3
    //   24: aload_0
    //   25: aload_3
    //   26: invokevirtual 2349	java/io/InputStream:read	([B)I
    //   29: istore_2
    //   30: iload_2
    //   31: ifle +13 -> 44
    //   34: aload_1
    //   35: aload_3
    //   36: iconst_0
    //   37: iload_2
    //   38: invokevirtual 2355	java/io/OutputStream:write	([BII)V
    //   41: goto -17 -> 24
    //   44: aload_1
    //   45: invokevirtual 2356	java/io/OutputStream:close	()V
    //   48: aload_0
    //   49: invokevirtual 939	java/io/InputStream:close	()V
    //   52: return
    //   53: astore_3
    //   54: aload_1
    //   55: invokevirtual 2356	java/io/OutputStream:close	()V
    //   58: aload_3
    //   59: athrow
    //   60: astore_1
    //   61: aload_0
    //   62: invokevirtual 939	java/io/InputStream:close	()V
    //   65: aload_1
    //   66: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	67	0	paramFile1	File
    //   0	67	1	paramFile2	File
    //   29	9	2	n	int
    //   23	13	3	arrayOfByte	byte[]
    //   53	6	3	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   18	24	53	finally
    //   24	30	53	finally
    //   34	41	53	finally
    //   9	18	60	finally
    //   44	48	60	finally
    //   54	60	60	finally
  }
  
  private static void a(List<LabeledIntent> paramList, Context paramContext, String paramString)
  {
    Intent localIntent = new Intent(paramContext, ClipboardShareActivity.class);
    localIntent.putExtra("android.intent.extra.TEXT", paramString);
    localIntent.putExtra("clipboardIntent", true);
    paramContext = new LabeledIntent(localIntent, paramContext.getPackageName(), R.string.omp_copy_to_clipboard, 0);
    if (paramList.size() > 4)
    {
      paramList.add(4, paramContext);
      return;
    }
    paramList.add(paramContext);
  }
  
  public static boolean a()
  {
    return "Amazon".equals(Build.MANUFACTURER);
  }
  
  public static boolean a(long paramLong1, long paramLong2)
  {
    Calendar localCalendar1 = Calendar.getInstance();
    localCalendar1.setTimeInMillis(paramLong1);
    Calendar localCalendar2 = Calendar.getInstance();
    localCalendar2.setTimeInMillis(paramLong2);
    return (localCalendar1.get(1) == localCalendar2.get(1)) && (localCalendar1.get(6) == localCalendar2.get(6));
  }
  
  public static boolean a(Activity paramActivity)
  {
    if (paramActivity == null) {
      return true;
    }
    if (Build.VERSION.SDK_INT >= 17) {
      return paramActivity.isDestroyed();
    }
    return paramActivity.isFinishing();
  }
  
  public static boolean a(Context paramContext, Intent paramIntent)
  {
    if (ClientAuthUtils.JWT_CALLBACK != null)
    {
      if (ClientAuthUtils.JWT_CALLBACK.requiresLogin())
      {
        paramIntent = paramContext.getPackageManager().getLaunchIntentForPackage(paramContext.getPackageName());
        paramIntent.setFlags(268468224);
        paramContext.startActivity(paramIntent);
        return true;
      }
      if (!OmlibApiManager.getInstance(paramContext).auth().isAuthenticated())
      {
        Intent localIntent = OmletGameSDK.getStartSignInIntent(paramContext, b.a.SignedInPostDirect.name());
        if (paramIntent != null) {
          localIntent.putExtra("signinredirect", paramIntent);
        }
        localIntent.setFlags(268468224);
        paramContext.startActivity(localIntent);
        return true;
      }
    }
    return false;
  }
  
  public static boolean a(Context paramContext, String paramString, Long paramLong)
  {
    paramString = q(paramContext, paramString);
    if (paramString != null)
    {
      if ((paramLong != null) && (paramLong.longValue() != -1L))
      {
        paramLong = (OMMessage)OMSQLiteHelper.getInstance(paramContext).getObjectById(OMMessage.class, paramLong.longValue());
        if (paramLong != null) {
          paramString.putExtra("extraClickedMessage", mobisocial.b.a.a(paramLong));
        }
      }
      paramContext.startActivity(paramString);
      return true;
    }
    return false;
  }
  
  public static boolean a(Context paramContext, b.agp paramAgp)
  {
    return (B(paramContext)) && (c(paramAgp)) && (paramAgp.y != null) && (paramAgp.y.containsKey("StreamPreviewLink"));
  }
  
  public static boolean a(Context paramContext, b.amn paramAmn)
  {
    return (B(paramContext)) && (f(paramAmn)) && (paramAmn.n != null) && (paramAmn.n.containsKey("StreamPreviewLink"));
  }
  
  public static boolean a(Context paramContext, b.er paramEr)
  {
    r.j localJ = a(paramContext, paramEr.a);
    String str;
    if ((localJ != null) && (localJ.a != null))
    {
      str = localJ.a;
      if (paramEr.j != null)
      {
        HashSet localHashSet = new HashSet();
        paramEr = paramEr.j.iterator();
        while (paramEr.hasNext())
        {
          b.eo localEo = (b.eo)paramEr.next();
          if ("Android".equals(localEo.c)) {
            localHashSet.add(localEo.b);
          }
        }
        mobisocial.omlet.overlaybar.util.a.b.a(paramContext).a(localHashSet);
      }
      paramEr = str;
    }
    try
    {
      if (localJ.b == r.g.PlayStore)
      {
        paramEr = new StringBuilder();
        paramEr.append(str);
        paramEr.append("&referrer=utm_source%3Domlet%26utm_medium%3Dcommunity");
        paramEr = paramEr.toString();
      }
      paramEr = new Intent("android.intent.action.VIEW").setData(Uri.parse(paramEr));
      paramEr.addFlags(268435456);
      paramContext.startActivity(paramEr);
      return true;
    }
    catch (ActivityNotFoundException paramContext)
    {
      for (;;) {}
    }
    return false;
  }
  
  private static boolean a(Context paramContext, r.c paramC)
  {
    paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext);
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("IN_APP_STREAM_");
    localStringBuilder.append(paramC.name());
    return paramContext.getBoolean(localStringBuilder.toString(), false);
  }
  
  public static boolean a(Context paramContext, PresenceState paramPresenceState)
  {
    return (A(paramContext)) && (e(paramPresenceState)) && (paramPresenceState.streamMetadata != null) && (Boolean.TRUE.equals(paramPresenceState.streamMetadata.get("EmbeddedYouTubeLive")));
  }
  
  public static boolean a(Context paramContext, String[] paramArrayOfString, ResultReceiver paramResultReceiver)
  {
    return a(paramContext, paramArrayOfString, null, paramResultReceiver, false);
  }
  
  public static boolean a(Context paramContext, String[] paramArrayOfString, ResultReceiver paramResultReceiver, boolean paramBoolean)
  {
    return a(paramContext, paramArrayOfString, null, paramResultReceiver, paramBoolean);
  }
  
  public static boolean a(Context paramContext, String[] paramArrayOfString, Integer paramInteger)
  {
    return a(paramContext, paramArrayOfString, paramInteger, null, false);
  }
  
  public static boolean a(Context paramContext, String[] paramArrayOfString, Integer paramInteger, ResultReceiver paramResultReceiver, boolean paramBoolean)
  {
    ArrayList localArrayList = new ArrayList();
    int i1 = paramArrayOfString.length;
    boolean bool = false;
    int n = 0;
    Object localObject;
    while (n < i1)
    {
      localObject = paramArrayOfString[n];
      if (android.support.v4.content.c.b(paramContext, (String)localObject) != 0) {
        localArrayList.add(localObject);
      }
      n += 1;
    }
    if (localArrayList.size() > 0) {
      if ((paramInteger == null) && (paramResultReceiver == null))
      {
        paramArrayOfString = new Intent(paramContext, RequestPermissionActivity.class);
        if (!(paramContext instanceof Activity)) {
          paramArrayOfString.addFlags(268468224);
        }
        paramArrayOfString.putExtra("permission", (String[])localArrayList.toArray(new String[localArrayList.size()]));
        paramContext.startActivity(paramArrayOfString);
      }
      else
      {
        paramArrayOfString = new Bundle();
        paramArrayOfString.putInt("requestCode", 7);
        localObject = new Intent(paramContext, RequestPermissionActivity.class);
        ((Intent)localObject).putExtra("permission", (String[])localArrayList.toArray(new String[localArrayList.size()]));
        ((Intent)localObject).putExtra("audioOptional", paramBoolean);
        Bundle localBundle = new Bundle();
        if (paramInteger != null) {
          localBundle.putInt("requestCode", paramInteger.intValue());
        } else {
          localBundle.putParcelable("receiver", Utils.receiverForSending(paramResultReceiver));
        }
        paramContext.startActivity(ChatProxyActivity.a(paramContext, (Intent)localObject, 7, paramArrayOfString, localBundle));
      }
    }
    paramBoolean = bool;
    if (localArrayList.size() == 0) {
      paramBoolean = true;
    }
    return paramBoolean;
  }
  
  public static boolean a(Context paramContext, String[] paramArrayOfString, Integer paramInteger, boolean paramBoolean)
  {
    return a(paramContext, paramArrayOfString, paramInteger, null, paramBoolean);
  }
  
  public static boolean a(Uri paramUri)
  {
    return (m.a(paramUri)) || (b(paramUri));
  }
  
  public static boolean a(String paramString)
  {
    return paramString.indexOf("\n====") > 0;
  }
  
  public static boolean a(String paramString1, String paramString2)
  {
    return ((paramString1 == null) && (paramString2 == null)) || ((paramString1 != null) && (paramString1.equals(paramString2)));
  }
  
  public static boolean a(b.aem paramAem)
  {
    if ((paramAem.a != null) && (paramAem.c != null)) {
      return f(paramAem.a);
    }
    return false;
  }
  
  public static boolean a(b.afv paramAfv)
  {
    return (paramAfv.c != null) || (paramAfv.a != null) || (paramAfv.b != null) || (paramAfv.e != null) || (paramAfv.f != null) || (paramAfv.h != null) || (paramAfv.i != null);
  }
  
  public static boolean a(b.agp paramAgp)
  {
    return (paramAgp != null) && (paramAgp.y != null) && (!TextUtils.isEmpty((String)paramAgp.y.get("LiveJoinGameUrl")));
  }
  
  private static boolean a(b.agp paramAgp, String paramString)
  {
    return (paramAgp != null) && (paramAgp.p != null) && (paramAgp.p.toLowerCase().contains(paramString));
  }
  
  public static boolean a(b.agp paramAgp, boolean paramBoolean)
  {
    boolean bool = false;
    if ((paramBoolean) && (paramAgp.m == null) && (paramAgp.p != null) && (paramAgp.p.contains("twitch"))) {
      return false;
    }
    paramBoolean = bool;
    if (paramAgp.q != null)
    {
      paramBoolean = bool;
      if (paramAgp.q.containsKey("MCPEServerRunning"))
      {
        paramBoolean = bool;
        if (Boolean.TRUE.equals(paramAgp.q.get("MCPEServerRunning"))) {
          paramBoolean = true;
        }
      }
    }
    return paramBoolean;
  }
  
  private static boolean a(b.amn paramAmn, String paramString)
  {
    return (paramAmn != null) && (paramAmn.m != null) && (paramAmn.m.toLowerCase().contains(paramString));
  }
  
  public static boolean a(b.amn paramAmn, boolean paramBoolean)
  {
    boolean bool = false;
    if ((paramBoolean) && (paramAmn.j == null) && (paramAmn.m != null) && (paramAmn.m.contains("twitch"))) {
      return false;
    }
    paramBoolean = bool;
    if (paramAmn.x != null)
    {
      paramBoolean = bool;
      if (paramAmn.x.containsKey("MCPEServerRunning"))
      {
        paramBoolean = bool;
        if (Boolean.TRUE.equals(paramAmn.x.get("MCPEServerRunning"))) {
          paramBoolean = true;
        }
      }
    }
    return paramBoolean;
  }
  
  public static boolean a(mobisocial.omlet.b.a.c paramC)
  {
    boolean bool = false;
    if (paramC != null)
    {
      if (paramC.c == null) {
        return false;
      }
      if ((paramC.c instanceof b.ajh))
      {
        Object localObject1 = (b.ajh)paramC.c;
        Object localObject2 = a((b.ajh)localObject1);
        if ((((r.d)localObject2).a.size() == 1) && (((r.i)((r.d)localObject2).a.get(0)).d))
        {
          localObject1 = ((b.ajh)localObject1).a.iterator();
          while (((Iterator)localObject1).hasNext())
          {
            localObject2 = (b.aji)((Iterator)localObject1).next();
            if ((localObject2 != null) && (((b.aji)localObject2).b != null) && (((b.aji)localObject2).b.a != null) && (!((b.aji)localObject2).b.a.isEmpty()))
            {
              localObject2 = ((b.apb)((b.aji)localObject2).b.a.get(0)).a;
              if ((localObject2 != null) && (((String)localObject2).indexOf("http") == 0))
              {
                n = 1;
                break label188;
              }
            }
          }
        }
      }
      int n = 0;
      label188:
      if (("Video".equals(paramC.b)) || ("Bang".equals(paramC.b)) || (n != 0)) {
        bool = true;
      }
      return bool;
    }
    return false;
  }
  
  public static boolean a(PresenceState paramPresenceState)
  {
    return (paramPresenceState.extraGameData != null) && (paramPresenceState.extraGameData.containsKey("MCPEFollowingOnly")) && (Boolean.TRUE.equals(paramPresenceState.extraGameData.get("MCPEFollowingOnly")));
  }
  
  private static boolean a(PresenceState paramPresenceState, String paramString)
  {
    return (paramPresenceState != null) && (paramPresenceState.externalViewingLink != null) && (paramPresenceState.externalViewingLink.toLowerCase().contains(paramString));
  }
  
  public static LabeledIntent[] a(Context paramContext, Intent paramIntent, LabeledIntent paramLabeledIntent)
  {
    boolean bool1 = AppConfigurationFactory.getProvider(paramContext).getBoolean("omlet.chat.private");
    boolean bool2 = AppConfigurationFactory.getProvider(paramContext).getBoolean("omlet.chat");
    String str1 = paramIntent.getStringExtra("android.intent.extra.TEXT");
    if (((!bool1) && (!bool2)) || (str1 != null)) {
      paramLabeledIntent = null;
    }
    PackageManager localPackageManager = paramContext.getPackageManager();
    Object localObject = localPackageManager.queryIntentActivities(paramIntent, 0);
    ArrayList localArrayList = new ArrayList();
    if (paramLabeledIntent != null) {
      localArrayList.add(paramLabeledIntent);
    }
    Collections.sort((List)localObject, new ResolveInfo.DisplayNameComparator(localPackageManager));
    Collections.sort((List)localObject, new r.7());
    paramLabeledIntent = ((List)localObject).iterator();
    while (paramLabeledIntent.hasNext())
    {
      localObject = (ResolveInfo)paramLabeledIntent.next();
      if ("com.google.android.apps.docs".equals(((ResolveInfo)localObject).activityInfo.packageName))
      {
        paramLabeledIntent.remove();
      }
      else
      {
        String str2 = ((ResolveInfo)localObject).activityInfo.packageName;
        if (!str2.equals(paramContext.getPackageName()))
        {
          Intent localIntent = new Intent(paramIntent);
          localIntent.setComponent(new ComponentName(str2, ((ResolveInfo)localObject).activityInfo.name));
          localArrayList.add(new LabeledIntent(localIntent, str2, ((ResolveInfo)localObject).loadLabel(localPackageManager), ((ResolveInfo)localObject).icon));
        }
      }
    }
    if (str1 != null) {
      a(localArrayList, paramContext, str1);
    }
    return (LabeledIntent[])localArrayList.toArray(new LabeledIntent[localArrayList.size()]);
  }
  
  public static String[] a(Context paramContext, String paramString1, byte[] paramArrayOfByte, boolean paramBoolean, String paramString2)
  {
    for (;;)
    {
      try
      {
        localPackageInfo = f(paramContext);
        paramString2 = localPackageInfo.versionName.split("\\.");
        n = 1;
      }
      catch (PackageManager.NameNotFoundException paramString1)
      {
        PackageInfo localPackageInfo;
        int n;
        int i1;
        int i2;
        label182:
        continue;
      }
      try
      {
        try
        {
          i1 = Integer.parseInt(paramString2[0]);
          i2 = Integer.parseInt(paramString2[1]);
        }
        catch (Exception paramContext)
        {
          paramArrayOfByte = new StringBuilder();
          paramArrayOfByte.append("Error parsing mc identifier ");
          paramArrayOfByte.append(paramString1);
          Log.w("Arcade", paramArrayOfByte.toString(), paramContext);
          return null;
        }
      }
      catch (NumberFormatException paramString1)
      {
        continue;
      }
      try
      {
        paramArrayOfByte = a(paramString1, paramArrayOfByte);
        if (paramArrayOfByte != null)
        {
          paramString2 = paramArrayOfByte[3].split("\\.");
          int i3 = Integer.parseInt(paramString2[0]);
          int i4 = Integer.parseInt(paramString2[1]);
          paramString2 = localPackageInfo.versionName;
          if ((i1 != i3) || (i2 != i4))
          {
            if (!paramBoolean) {
              break label313;
            }
            mobisocial.c.d.a(new r.3(paramContext, paramString2, paramArrayOfByte));
            return null;
          }
        }
        else if (paramBoolean)
        {
          mobisocial.c.d.a(new r.4(paramContext));
          return null;
        }
        return paramArrayOfByte;
      }
      catch (NumberFormatException paramString1) {}
    }
    break label182;
    n = 0;
    if (paramBoolean)
    {
      if (n != 0)
      {
        mobisocial.c.d.a(new r.5(paramContext));
        return null;
      }
      mobisocial.c.d.a(new r.6(paramContext, mobisocial.omlet.b.a.a.a("com.mojang.minecraftpe")));
    }
    return null;
    if (!paramBoolean) {
      return null;
    }
    paramString1 = mobisocial.omlet.b.a.a.a("com.mojang.minecraftpe");
    paramArrayOfByte = new HashMap();
    paramArrayOfByte.put("packageName", paramString1.b);
    paramArrayOfByte.put("contentProvider", paramString2);
    OmlibApiManager.getInstance(paramContext).analytics().trackEvent(b.b.Stream, b.a.AppInstallClick, paramArrayOfByte);
    mobisocial.c.d.a(new r.2(paramContext, paramString1));
    return null;
    label313:
    return null;
  }
  
  public static String[] a(String paramString, byte[] paramArrayOfByte)
  {
    byte[] arrayOfByte = paramArrayOfByte;
    if (paramArrayOfByte == null) {
      arrayOfByte = paramString.getBytes();
    }
    paramString = b(arrayOfByte);
    paramArrayOfByte = new String[paramString.size()];
    int n = 0;
    while (n < paramString.size())
    {
      paramArrayOfByte[n] = new String((byte[])paramString.get(n));
      n += 1;
    }
    if (paramArrayOfByte.length > 3) {
      return paramArrayOfByte;
    }
    return null;
  }
  
  public static int b(Context paramContext, int paramInt)
  {
    float f1 = paramContext.getResources().getDisplayMetrics().density;
    return (int)(paramInt / f1);
  }
  
  public static AlertDialog b(Context paramContext, DialogInterface.OnClickListener paramOnClickListener)
  {
    return a(paramContext, paramContext.getString(R.string.omp_discard_send_dialog_title), paramContext.getString(R.string.omp_discard_send_dialog_text), paramOnClickListener);
  }
  
  public static Intent b(Context paramContext)
  {
    return b(paramContext, null);
  }
  
  public static Intent b(Context paramContext, String paramString)
  {
    if (paramString == null) {
      localObject = r(paramContext);
    } else {
      localObject = paramString;
    }
    Object localObject = a(paramContext, (String)localObject);
    ((Intent)localObject).putExtra("android.intent.extra.INITIAL_INTENTS", a(paramContext, p(paramContext, paramString), null));
    ((Intent)localObject).putExtra("omlet.intent.extra.CHOOSER_TITLE", paramContext.getString(R.string.omp_promote_stream_to));
    ((Intent)localObject).putExtra("extraHideCommunity", true);
    ((Intent)localObject).putExtra("extraIsStreaming", true);
    ((Intent)localObject).putExtra("shareCategory", b.b.GarenaInvite);
    ((Intent)localObject).putExtra("streamerAccount", OmlibApiManager.getInstance(paramContext).auth().getAccount());
    ((Intent)localObject).addFlags(276856832);
    return localObject;
  }
  
  private static LabeledIntent b(Context paramContext, String paramString, boolean paramBoolean)
  {
    Intent localIntent = new Intent(paramContext, MediaShareActivity.class);
    localIntent.setAction("SEND_TO_CHAT");
    localIntent.putExtra("EXTRA_SHARE_TEXT", paramString);
    if (paramBoolean) {
      localIntent.putExtra("SCRAPE_THUMBNAIL", true);
    }
    paramString = mobisocial.omlet.b.r.a(paramContext).b();
    if (paramString != null) {
      paramString = mobisocial.b.a.b(paramString);
    } else {
      paramString = null;
    }
    localIntent.putExtra("EXTRA_LAST_PUB_CHAT", paramString);
    paramString = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 0);
    paramContext = new LabeledIntent(localIntent, paramContext.getPackageName(), R.string.omp_share_in_chat, paramString.icon);
    paramContext.putExtra("shareInChat", true);
    return paramContext;
  }
  
  public static Bitmap b(Bitmap paramBitmap, int paramInt)
  {
    float f2 = paramBitmap.getWidth() / paramBitmap.getHeight();
    float f1 = paramInt;
    int n = (int)(f2 * f1);
    Bitmap localBitmap = Bitmap.createBitmap(n, paramInt, Bitmap.Config.ARGB_8888);
    float f4 = n;
    f2 = f4 / paramBitmap.getWidth();
    float f3 = f1 / paramBitmap.getHeight();
    f4 /= 2.0F;
    f1 /= 2.0F;
    Object localObject = new Matrix();
    ((Matrix)localObject).setScale(f2, f3, f4, f1);
    Canvas localCanvas = new Canvas(localBitmap);
    localCanvas.setMatrix((Matrix)localObject);
    localObject = new Paint();
    ((Paint)localObject).setFilterBitmap(false);
    ((Paint)localObject).setDither(false);
    ((Paint)localObject).setAntiAlias(false);
    localCanvas.drawBitmap(paramBitmap, f4 - paramBitmap.getWidth() / 2, f1 - paramBitmap.getHeight() / 2, (Paint)localObject);
    return localBitmap;
  }
  
  public static BitmapFactory.Options b(String paramString)
  {
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    localOptions.inJustDecodeBounds = true;
    BitmapFactory.decodeFile(paramString, localOptions);
    return localOptions;
  }
  
  public static String b(Context paramContext, long paramLong)
  {
    if (paramLong < 60000L)
    {
      paramContext = paramContext.getResources();
      n = R.plurals.oma_seconds;
      i1 = (int)(paramLong / 1000L);
      return paramContext.getQuantityString(n, i1, new Object[] { Integer.valueOf(i1) });
    }
    if (paramLong < 3600000L)
    {
      paramContext = paramContext.getResources();
      n = R.plurals.oma_minutes;
      i1 = (int)(paramLong / 60000L);
      return paramContext.getQuantityString(n, i1, new Object[] { Integer.valueOf(i1) });
    }
    if (paramLong < 86400000L)
    {
      paramContext = paramContext.getResources();
      n = R.plurals.oma_hours;
      i1 = (int)(paramLong / 3600000L);
      return paramContext.getQuantityString(n, i1, new Object[] { Integer.valueOf(i1) });
    }
    if (paramLong < 604800000L)
    {
      paramContext = paramContext.getResources();
      n = R.plurals.oma_days;
      i1 = (int)(paramLong / 86400000L);
      return paramContext.getQuantityString(n, i1, new Object[] { Integer.valueOf(i1) });
    }
    if (paramLong < 31449600000L)
    {
      paramContext = paramContext.getResources();
      n = R.plurals.oma_weeks;
      i1 = (int)(paramLong / 604800000L);
      return paramContext.getQuantityString(n, i1, new Object[] { Integer.valueOf(i1) });
    }
    paramContext = paramContext.getResources();
    int n = R.plurals.oma_years;
    int i1 = (int)(paramLong / 31449600000L);
    return paramContext.getQuantityString(n, i1, new Object[] { Integer.valueOf(i1) });
  }
  
  /* Error */
  public static String b(Context paramContext, Uri paramUri)
  {
    // Byte code:
    //   0: ldc_w 2881
    //   3: aload_1
    //   4: invokevirtual 2884	android/net/Uri:getScheme	()Ljava/lang/String;
    //   7: invokevirtual 345	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   10: ifeq +8 -> 18
    //   13: aload_1
    //   14: invokevirtual 1807	android/net/Uri:getPath	()Ljava/lang/String;
    //   17: areturn
    //   18: aload_0
    //   19: invokevirtual 912	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   22: aload_1
    //   23: iconst_1
    //   24: anewarray 58	java/lang/String
    //   27: dup
    //   28: iconst_0
    //   29: ldc_w 1253
    //   32: aastore
    //   33: aconst_null
    //   34: aconst_null
    //   35: aconst_null
    //   36: invokevirtual 1257	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   39: astore 4
    //   41: aload 4
    //   43: astore_3
    //   44: aload 4
    //   46: ldc_w 1253
    //   49: invokeinterface 1265 2 0
    //   54: istore_2
    //   55: aload 4
    //   57: astore_3
    //   58: aload 4
    //   60: invokeinterface 1262 1 0
    //   65: pop
    //   66: aload 4
    //   68: astore_3
    //   69: aload 4
    //   71: iload_2
    //   72: invokeinterface 1266 2 0
    //   77: astore 5
    //   79: aload 5
    //   81: ifnull +18 -> 99
    //   84: aload 4
    //   86: ifnull +10 -> 96
    //   89: aload 4
    //   91: invokeinterface 1267 1 0
    //   96: aload 5
    //   98: areturn
    //   99: aload 4
    //   101: astore_3
    //   102: new 259	java/lang/Exception
    //   105: dup
    //   106: invokespecial 2885	java/lang/Exception:<init>	()V
    //   109: athrow
    //   110: astore_0
    //   111: aconst_null
    //   112: astore_3
    //   113: goto +64 -> 177
    //   116: aconst_null
    //   117: astore 4
    //   119: aload 4
    //   121: astore_3
    //   122: aload_1
    //   123: aload_0
    //   124: invokestatic 2887	mobisocial/omlet/overlaybar/ui/c/r:a	(Landroid/net/Uri;Landroid/content/Context;)Landroid/net/Uri;
    //   127: invokevirtual 1807	android/net/Uri:getPath	()Ljava/lang/String;
    //   130: astore_1
    //   131: aload 4
    //   133: ifnull +10 -> 143
    //   136: aload 4
    //   138: invokeinterface 1267 1 0
    //   143: aload_1
    //   144: areturn
    //   145: astore_0
    //   146: goto +31 -> 177
    //   149: aload 4
    //   151: astore_3
    //   152: aload_0
    //   153: getstatic 2890	glrecorder/lib/R$string:oma_file_error	I
    //   156: iconst_0
    //   157: invokestatic 2252	mobisocial/omlib/ui/toast/OMToast:makeText	(Landroid/content/Context;II)Landroid/widget/Toast;
    //   160: invokevirtual 1838	android/widget/Toast:show	()V
    //   163: aload 4
    //   165: ifnull +10 -> 175
    //   168: aload 4
    //   170: invokeinterface 1267 1 0
    //   175: aconst_null
    //   176: areturn
    //   177: aload_3
    //   178: ifnull +9 -> 187
    //   181: aload_3
    //   182: invokeinterface 1267 1 0
    //   187: aload_0
    //   188: athrow
    //   189: astore_3
    //   190: goto -74 -> 116
    //   193: astore_3
    //   194: goto -75 -> 119
    //   197: astore_1
    //   198: goto -49 -> 149
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	201	0	paramContext	Context
    //   0	201	1	paramUri	Uri
    //   54	18	2	n	int
    //   43	139	3	localCursor1	Cursor
    //   189	1	3	localException1	Exception
    //   193	1	3	localException2	Exception
    //   39	130	4	localCursor2	Cursor
    //   77	20	5	str	String
    // Exception table:
    //   from	to	target	type
    //   18	41	110	finally
    //   44	55	145	finally
    //   58	66	145	finally
    //   69	79	145	finally
    //   102	110	145	finally
    //   122	131	145	finally
    //   152	163	145	finally
    //   18	41	189	java/lang/Exception
    //   44	55	193	java/lang/Exception
    //   58	66	193	java/lang/Exception
    //   69	79	193	java/lang/Exception
    //   102	110	193	java/lang/Exception
    //   122	131	197	java/io/IOException
  }
  
  public static String b(Context paramContext, String paramString1, String paramString2)
  {
    if (paramString2 != null)
    {
      paramContext = new StringBuilder();
      paramContext.append("https://arcade.omlet.me/profile/");
      paramContext.append(paramString2);
      return paramContext.toString();
    }
    paramContext = (OMAccount)OMSQLiteHelper.getInstance(paramContext).getObjectByKey(OMAccount.class, paramString1);
    if (paramContext == null) {
      return "https://arcade.omlet.me/";
    }
    if (paramContext.omletId != null)
    {
      paramString1 = new StringBuilder();
      paramString1.append("https://arcade.omlet.me/profile/");
      paramString1.append(paramContext.omletId);
      return paramString1.toString();
    }
    paramString1 = new StringBuilder();
    paramString1.append("https://arcade.omlet.me/profile/");
    paramString1.append(paramContext.account);
    return paramString1.toString();
  }
  
  private static String b(String paramString1, String paramString2)
  {
    if ((paramString1 != null) && (!paramString1.isEmpty())) {
      return paramString1;
    }
    return paramString2;
  }
  
  public static String b(b.aft paramAft)
  {
    String str;
    if (paramAft.z == null) {
      str = "";
    } else {
      str = paramAft.z.b;
    }
    if (!str.isEmpty()) {
      return str;
    }
    return paramAft.u;
  }
  
  public static String b(b.ajh paramAjh)
  {
    Object localObject2;
    do
    {
      paramAjh = paramAjh.a.iterator();
      Object localObject1;
      while (!((Iterator)localObject1).hasNext())
      {
        do
        {
          do
          {
            if (!paramAjh.hasNext()) {
              break label269;
            }
            localObject1 = (b.aji)paramAjh.next();
            if (((b.aji)localObject1).b != null)
            {
              if (((b.apb)((b.aji)localObject1).b.a.get(0)).f == null) {
                return ((b.apb)((b.aji)localObject1).b.a.get(0)).d;
              }
              return ((b.apb)((b.aji)localObject1).b.a.get(0)).f;
            }
            if (((b.aji)localObject1).c != null)
            {
              if (((b.zv)((b.aji)localObject1).c.a.get(0)).b == null) {
                return ((b.zv)((b.aji)localObject1).c.a.get(0)).a;
              }
              return ((b.zv)((b.aji)localObject1).c.a.get(0)).b;
            }
            if (((b.aji)localObject1).d == null) {
              break;
            }
            localObject1 = ((b.aji)localObject1).d.a.iterator();
          } while (!((Iterator)localObject1).hasNext());
          localObject2 = (b.abv)((Iterator)localObject1).next();
          if (((b.abv)localObject2).c == null) {
            break;
          }
          return ((b.abv)localObject2).c;
        } while (((b.aji)localObject1).f == null);
        localObject1 = ((b.aji)localObject1).f.a.iterator();
      }
      localObject2 = (b.lm)((Iterator)localObject1).next();
    } while (((b.lm)localObject2).e == null);
    return ((b.lm)localObject2).e;
    label269:
    return null;
  }
  
  public static ArrayList<byte[]> b(byte[] paramArrayOfByte)
  {
    ArrayList localArrayList = new ArrayList();
    int i1;
    for (int n = 0; n < paramArrayOfByte.length; n = i1 + 1)
    {
      i1 = n;
      while ((i1 < paramArrayOfByte.length) && (paramArrayOfByte[i1] != 59)) {
        i1 += 1;
      }
      localArrayList.add(Arrays.copyOfRange(paramArrayOfByte, n, i1));
    }
    return localArrayList;
  }
  
  public static n.c b(b.amn paramAmn)
  {
    if (e(paramAmn)) {
      return n.c.YouTube;
    }
    if (f(paramAmn)) {
      return n.c.Facebook;
    }
    return n.c.Omlet;
  }
  
  public static r.a b(long paramLong)
  {
    if (paramLong >= 50000L) {
      return (r.a)j.get(r.a.a.Legendary);
    }
    if (paramLong >= 10000L) {
      return (r.a)j.get(r.a.a.Pro);
    }
    if (paramLong >= 1000L) {
      return (r.a)j.get(r.a.a.Elite);
    }
    if (paramLong >= 100L) {
      return (r.a)j.get(r.a.a.Junior);
    }
    return (r.a)j.get(r.a.a.Rookie);
  }
  
  public static void b(Activity paramActivity)
  {
    if (paramActivity.getResources().getConfiguration().orientation == 2)
    {
      paramActivity.setRequestedOrientation(6);
      return;
    }
    paramActivity.setRequestedOrientation(7);
  }
  
  public static void b(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    a(paramContext, a(paramString1, paramString2, paramString3), b.b.Stream.name(), paramContext.getString(R.string.omp_share_stream));
  }
  
  public static void b(Context paramContext, b.eo paramEo)
  {
    OmlibApiManager.getInstance(paramContext).analytics().trackEvent(b.b.Squad, b.a.Share);
    Intent localIntent = new Intent("android.intent.action.SEND");
    localIntent.setType("text/plain");
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("https://arcade.omlet.me/squad/");
    localStringBuilder.append(paramEo.b);
    paramEo = localStringBuilder.toString();
    localIntent.putExtra("android.intent.extra.TEXT", paramEo);
    try
    {
      paramEo = b(paramContext, paramEo, false);
    }
    catch (PackageManager.NameNotFoundException paramEo)
    {
      for (;;) {}
    }
    paramEo = null;
    a(paramContext, localIntent, b.b.Squad.name(), paramContext.getString(R.string.omp_share_squad), paramEo);
  }
  
  public static void b(Context paramContext, b.er paramEr)
  {
    Intent localIntent = new Intent("mobisocial.arcade.action.VIEW_MANAGED_COMMUNITY");
    localIntent.putExtra("communityinfo", mobisocial.b.a.a(paramEr, b.er.class));
    if (!(paramContext instanceof Activity)) {
      localIntent.addFlags(268468224);
    }
    paramContext.startActivity(localIntent);
  }
  
  private static void b(Context paramContext, String[] paramArrayOfString, boolean paramBoolean)
  {
    Object localObject = mobisocial.omlet.overlaybar.util.a.b.a(paramContext);
    if (!((mobisocial.omlet.overlaybar.util.a.b)localObject).d("com.mojang.minecraftpe")) {
      ((mobisocial.omlet.overlaybar.util.a.b)localObject).b("com.mojang.minecraftpe", true);
    }
    if ((!FloatingButtonViewHandler.c(paramContext)) && (PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean("detectGames", false)))
    {
      OmletGameSDK.setFallbackPackage("com.mojang.minecraftpe");
      OmletGameSDK.getOverlayPermissionChecker().startOverlayManager(paramContext);
    }
    String str = "";
    if (paramArrayOfString.length > 8)
    {
      localObject = new SpannableStringBuilder(paramArrayOfString[7]);
      int i1;
      for (int n = 0; n < ((SpannableStringBuilder)localObject).length() - 1; n = i1 + 1)
      {
        i1 = n;
        if (Character.valueOf(((SpannableStringBuilder)localObject).charAt(n)).charValue() == '§')
        {
          ((SpannableStringBuilder)localObject).delete(n, n + 2);
          i1 = n - 1;
        }
      }
      str = ((SpannableStringBuilder)localObject).toString();
    }
    localObject = null;
    if (paramBoolean)
    {
      Intent localIntent = n(paramContext, "com.mojang.minecraftpe");
      localObject = localIntent;
      if (localIntent == null)
      {
        OMToast.makeText(paramContext, R.string.minecraft_install_minecraft, 0).show();
        return;
      }
    }
    paramArrayOfString = e(String.format(paramContext.getString(R.string.oma_minecraft_join_hint), new Object[] { str, paramArrayOfString[1] }));
    paramArrayOfString = al.b(paramContext.getApplicationContext(), paramArrayOfString, -2);
    paramArrayOfString.a(paramContext.getResources().getString(R.string.oma_got_it), new r.27(paramArrayOfString));
    paramArrayOfString.a(new r.28(paramContext));
    paramArrayOfString.b(-1);
    paramArrayOfString.a(240);
    paramArrayOfString.c(5);
    paramArrayOfString.b();
    if (paramBoolean) {
      paramContext.startActivity((Intent)localObject);
    }
    OmletGameSDK.closeGameChatOverlays();
  }
  
  public static boolean b(Context paramContext, Intent paramIntent)
  {
    if (!OmlibApiManager.getInstance(paramContext).auth().isAuthenticated())
    {
      Intent localIntent = OmletGameSDK.getStartSignInIntent(paramContext, b.a.SignedInPostDirect.name());
      if (paramIntent != null) {
        localIntent.putExtra("signinredirect", paramIntent);
      }
      localIntent.setFlags(268468224);
      paramContext.startActivity(localIntent);
      return true;
    }
    return false;
  }
  
  public static boolean b(Context paramContext, b.agp paramAgp)
  {
    return (c(paramContext, paramAgp)) || (a(paramContext, paramAgp));
  }
  
  public static boolean b(Context paramContext, b.amn paramAmn)
  {
    return (c(paramContext, paramAmn)) || (a(paramContext, paramAmn));
  }
  
  public static boolean b(Context paramContext, PresenceState paramPresenceState)
  {
    return ((a(paramContext, paramPresenceState)) || (c(paramContext, paramPresenceState))) && (!TextUtils.isEmpty(c(paramPresenceState)));
  }
  
  public static boolean b(Uri paramUri)
  {
    if (paramUri == null) {
      return false;
    }
    String str = paramUri.getHost();
    if (str == null) {
      return false;
    }
    if ((!str.endsWith(".omlet.me")) && (!str.equals("127.0.0.1")) && (!str.endsWith(".omapi.net"))) {
      return false;
    }
    paramUri = paramUri.getPath();
    if (paramUri == null) {
      return false;
    }
    return (paramUri.startsWith("/community/")) || (paramUri.startsWith("/game/"));
  }
  
  public static boolean b(b.afv paramAfv)
  {
    return (paramAfv.c != null) || (paramAfv.a != null) || (paramAfv.f != null) || ((paramAfv.e != null) && (paramAfv.e.U != null)) || (paramAfv.h != null) || ((paramAfv.b != null) && (paramAfv.b.c != null)) || ((paramAfv.i != null) && (b(paramAfv.i) != null));
  }
  
  private static boolean b(b.agp paramAgp)
  {
    return a(paramAgp, "youtube");
  }
  
  public static boolean b(PresenceState paramPresenceState)
  {
    return (paramPresenceState.extraGameData != null) && (paramPresenceState.extraGameData.containsKey("MCPEServerRunning")) && (Boolean.TRUE.equals(paramPresenceState.extraGameData.get("MCPEServerRunning")));
  }
  
  public static AlertDialog c(Context paramContext, DialogInterface.OnClickListener paramOnClickListener)
  {
    return a(paramContext, paramContext.getString(R.string.omp_discard_upload_dialog_title), paramContext.getString(R.string.omp_discard_upload_dialog_text), paramOnClickListener);
  }
  
  public static Intent c(Context paramContext, String paramString)
  {
    paramContext = new Intent(paramContext, a);
    paramContext.putExtra("type", "image/*");
    paramContext.putExtra("android.intent.extra.STREAM", paramString);
    return paramContext;
  }
  
  private static Intent c(String paramString1, String paramString2)
  {
    return a(paramString1, paramString2, null);
  }
  
  /* Error */
  public static Uri c(Context paramContext, Uri paramUri)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 912	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   4: aload_1
    //   5: iconst_1
    //   6: anewarray 58	java/lang/String
    //   9: dup
    //   10: iconst_0
    //   11: ldc_w 3084
    //   14: aastore
    //   15: aconst_null
    //   16: aconst_null
    //   17: aconst_null
    //   18: invokevirtual 1257	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   21: astore_0
    //   22: aload_0
    //   23: ldc_w 3084
    //   26: invokeinterface 1265 2 0
    //   31: istore_2
    //   32: aload_0
    //   33: invokeinterface 1262 1 0
    //   38: ifeq +32 -> 70
    //   41: getstatic 3090	android/provider/MediaStore$Images$Media:EXTERNAL_CONTENT_URI	Landroid/net/Uri;
    //   44: aload_0
    //   45: iload_2
    //   46: invokeinterface 3093 2 0
    //   51: invokestatic 1238	java/lang/Long:toString	(J)Ljava/lang/String;
    //   54: invokestatic 3097	android/net/Uri:withAppendedPath	(Landroid/net/Uri;Ljava/lang/String;)Landroid/net/Uri;
    //   57: astore_1
    //   58: aload_0
    //   59: ifnull +9 -> 68
    //   62: aload_0
    //   63: invokeinterface 1267 1 0
    //   68: aload_1
    //   69: areturn
    //   70: aload_0
    //   71: ifnull +9 -> 80
    //   74: aload_0
    //   75: invokeinterface 1267 1 0
    //   80: aconst_null
    //   81: areturn
    //   82: astore_1
    //   83: goto +9 -> 92
    //   86: goto +20 -> 106
    //   89: astore_1
    //   90: aconst_null
    //   91: astore_0
    //   92: aload_0
    //   93: ifnull +9 -> 102
    //   96: aload_0
    //   97: invokeinterface 1267 1 0
    //   102: aload_1
    //   103: athrow
    //   104: aconst_null
    //   105: astore_0
    //   106: aload_0
    //   107: ifnull +9 -> 116
    //   110: aload_0
    //   111: invokeinterface 1267 1 0
    //   116: aconst_null
    //   117: areturn
    //   118: astore_0
    //   119: goto -15 -> 104
    //   122: astore_1
    //   123: goto -37 -> 86
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	126	0	paramContext	Context
    //   0	126	1	paramUri	Uri
    //   31	15	2	n	int
    // Exception table:
    //   from	to	target	type
    //   22	58	82	finally
    //   0	22	89	finally
    //   0	22	118	java/lang/Exception
    //   22	58	122	java/lang/Exception
  }
  
  public static Spanned c(String paramString)
  {
    if (Build.VERSION.SDK_INT >= 24) {
      return Html.fromHtml(paramString, 0);
    }
    return Html.fromHtml(paramString);
  }
  
  public static String c(long paramLong)
  {
    paramLong /= 1000L;
    return String.format("%02d:%02d", new Object[] { Long.valueOf(paramLong / 60L), Long.valueOf(paramLong % 60L) });
  }
  
  public static String c(Context paramContext, long paramLong)
  {
    if (paramLong == 0L) {
      return "N/A";
    }
    long l3 = Calendar.getInstance().getTimeInMillis() - paramLong;
    long l1 = l3 / 86400000L;
    long l2 = l3 / 3600000L;
    l3 /= 1000L;
    if (l1 >= 4L)
    {
      paramContext = new Date(paramLong);
      return new SimpleDateFormat("M/d/y").format(paramContext);
    }
    if (l2 < -1L) {
      return paramContext.getResources().getString(R.string.omp_future);
    }
    int n;
    if (l1 == 0L)
    {
      if (l3 < 60L) {
        return paramContext.getResources().getString(R.string.omp_now);
      }
      if (l3 < 120L) {
        return paramContext.getResources().getString(R.string.omp_min_ago, new Object[] { "1" });
      }
      double d1;
      if (l3 < 3600L)
      {
        paramContext = paramContext.getResources();
        n = R.string.omp_min_ago;
        d1 = l3;
        Double.isNaN(d1);
        return paramContext.getString(n, new Object[] { Integer.toString((int)Math.floor(d1 / 60.0D)) });
      }
      if (l3 < 7200L) {
        return paramContext.getResources().getQuantityString(R.plurals.omp_hours_ago, 1, new Object[] { Integer.valueOf(1) });
      }
      if (l3 < 86400L)
      {
        paramContext = paramContext.getResources();
        n = R.plurals.omp_hours_ago;
        d1 = l3;
        Double.isNaN(d1);
        d1 /= 3600.0D;
        return paramContext.getQuantityString(n, (int)Math.floor(d1), new Object[] { Integer.valueOf((int)Math.floor(d1)) });
      }
    }
    if (l1 == 1L) {
      return paramContext.getResources().getString(R.string.omp_yesterday);
    }
    if (l1 < 4L)
    {
      paramContext = paramContext.getResources();
      n = R.plurals.omp_days_ago;
      int i1 = (int)l1;
      return paramContext.getQuantityString(n, i1, new Object[] { Integer.valueOf(i1) });
    }
    return null;
  }
  
  public static String c(b.aft paramAft)
  {
    if ((paramAft instanceof b.aet)) {
      return "Mod";
    }
    if ((paramAft instanceof b.by)) {
      return "Bang";
    }
    if ((paramAft instanceof b.ape)) {
      return "Video";
    }
    if ((paramAft instanceof b.ajr)) {
      return "Screenshot";
    }
    if ((paramAft instanceof b.aem)) {
      return "Message";
    }
    if ((paramAft instanceof b.aeq)) {
      return "Miniclip";
    }
    return null;
  }
  
  public static String c(b.amn paramAmn)
  {
    if (!TextUtils.isEmpty(paramAmn.r)) {
      return paramAmn.r;
    }
    return paramAmn.q;
  }
  
  public static String c(PresenceState paramPresenceState)
  {
    if ((paramPresenceState != null) && (paramPresenceState.streamMetadata != null)) {
      return (String)paramPresenceState.streamMetadata.get("StreamPreviewLink");
    }
    return null;
  }
  
  public static String c(byte[] paramArrayOfByte)
  {
    paramArrayOfByte = b(paramArrayOfByte);
    StringBuilder localStringBuilder = new StringBuilder();
    int n = 0;
    while (n < paramArrayOfByte.size())
    {
      localStringBuilder.append(new String((byte[])paramArrayOfByte.get(n)));
      n += 1;
    }
    return localStringBuilder.toString();
  }
  
  public static b.aft c(b.afv paramAfv)
  {
    if (paramAfv == null) {
      return null;
    }
    if (paramAfv.b != null) {
      return paramAfv.b;
    }
    if (paramAfv.d != null) {
      return paramAfv.d;
    }
    if (paramAfv.f != null) {
      return paramAfv.f;
    }
    if (paramAfv.e != null) {
      return paramAfv.e;
    }
    if (paramAfv.c != null) {
      return paramAfv.c;
    }
    if (paramAfv.a != null) {
      return paramAfv.a;
    }
    if (paramAfv.h != null) {
      return paramAfv.h;
    }
    if (paramAfv.i != null) {
      return paramAfv.i;
    }
    return null;
  }
  
  public static b.mb c(Context paramContext)
  {
    b.qk localQk = new b.qk();
    localQk.a = OmlibApiManager.getInstance(paramContext).auth().getAccount();
    localQk.b = mobisocial.omlet.b.a.a.a("com.supercell.clashroyale");
    try
    {
      paramContext = ((b.ql)OmlibApiManager.getInstance(paramContext).getLdClient().msgClient().callSynchronous(localQk, b.ql.class)).a;
      return paramContext;
    }
    catch (LongdanException paramContext)
    {
      mobisocial.c.c.d("Arcade", "Failed to get game id for clash refresh", paramContext);
    }
    return null;
  }
  
  public static r.i c(b.ajh paramAjh)
  {
    Object localObject2;
    do
    {
      paramAjh = paramAjh.a.iterator();
      Object localObject1;
      while (!((Iterator)localObject1).hasNext())
      {
        do
        {
          do
          {
            if (!paramAjh.hasNext()) {
              break label288;
            }
            localObject1 = (b.aji)paramAjh.next();
            if (((b.aji)localObject1).b != null)
            {
              localObject1 = (b.apb)((b.aji)localObject1).b.a.get(0);
              if (((b.apb)localObject1).f == null) {
                paramAjh = ((b.apb)localObject1).d;
              } else {
                paramAjh = ((b.apb)localObject1).f;
              }
              return new r.i(((b.apb)localObject1).h, ((b.apb)localObject1).g, paramAjh, true);
            }
            if (((b.aji)localObject1).c != null)
            {
              localObject1 = (b.zv)((b.aji)localObject1).c.a.get(0);
              if (((b.zv)localObject1).b == null) {
                paramAjh = ((b.zv)localObject1).a;
              } else {
                paramAjh = ((b.zv)localObject1).b;
              }
              return new r.i(((b.zv)localObject1).d, ((b.zv)localObject1).c, paramAjh, false);
            }
            if (((b.aji)localObject1).d == null) {
              break;
            }
            localObject1 = ((b.aji)localObject1).d.a.iterator();
          } while (!((Iterator)localObject1).hasNext());
          localObject2 = (b.abv)((Iterator)localObject1).next();
          if (((b.abv)localObject2).c == null) {
            break;
          }
          return new r.i(((b.abv)localObject2).d, ((b.abv)localObject2).e, ((b.abv)localObject2).c, false);
        } while (((b.aji)localObject1).f == null);
        localObject1 = ((b.aji)localObject1).f.a.iterator();
      }
      localObject2 = (b.lm)((Iterator)localObject1).next();
    } while (((b.lm)localObject2).e == null);
    return new r.i(((b.lm)localObject2).f, ((b.lm)localObject2).g, ((b.lm)localObject2).e, false, true);
    label288:
    return null;
  }
  
  public static void c(Activity paramActivity)
  {
    View localView = paramActivity.getCurrentFocus();
    if (localView != null) {
      ((InputMethodManager)paramActivity.getSystemService("input_method")).hideSoftInputFromWindow(localView.getWindowToken(), 0);
    }
  }
  
  public static void c(Context paramContext, String paramString1, String paramString2)
  {
    a(paramContext, c(paramString1, paramString2), b.b.Stream.name(), paramContext.getString(R.string.omp_share_stream));
  }
  
  public static void c(Context paramContext, b.eo paramEo)
  {
    Intent localIntent = new Intent("mobisocial.arcade.action.VIEW_SQUAD_COMMUNITY");
    localIntent.setPackage(paramContext.getPackageName());
    localIntent.putExtra("extra_community_id", mobisocial.b.a.a(paramEo, b.eo.class));
    if (!(paramContext instanceof Activity)) {
      localIntent.addFlags(268468224);
    }
    paramContext.startActivity(localIntent);
  }
  
  private static boolean c(Context paramContext, b.agp paramAgp)
  {
    return (A(paramContext)) && (b(paramAgp)) && (paramAgp.y != null) && (Boolean.TRUE.equals(paramAgp.y.get("EmbeddedYouTubeLive")));
  }
  
  private static boolean c(Context paramContext, b.amn paramAmn)
  {
    return (A(paramContext)) && (e(paramAmn)) && (paramAmn.n != null) && (Boolean.TRUE.equals(paramAmn.n.get("EmbeddedYouTubeLive")));
  }
  
  private static boolean c(Context paramContext, PresenceState paramPresenceState)
  {
    return (B(paramContext)) && (f(paramPresenceState)) && (paramPresenceState.streamMetadata != null) && (paramPresenceState.streamMetadata.containsKey("StreamPreviewLink"));
  }
  
  public static boolean c(Uri paramUri)
  {
    return "com.android.externalstorage.documents".equals(paramUri.getAuthority());
  }
  
  private static boolean c(b.agp paramAgp)
  {
    return a(paramAgp, "facebook");
  }
  
  public static long d(String paramString)
  {
    int i1 = paramString.length();
    long l1 = 1125899906842597L;
    int n = 0;
    while (n < i1)
    {
      l1 = l1 * 31L + paramString.charAt(n);
      n += 1;
    }
    return l1;
  }
  
  public static Intent d(Context paramContext, String paramString)
  {
    paramContext = new Intent(paramContext, a);
    paramContext.putExtra("type", "video/*");
    paramContext.putExtra("android.intent.extra.STREAM", paramString);
    return paramContext;
  }
  
  public static String d(long paramLong)
  {
    return NumberFormat.getNumberInstance(Locale.getDefault()).format(paramLong);
  }
  
  public static String d(Context paramContext, long paramLong)
  {
    return paramContext.getResources().getQuantityString(R.plurals.oma_saved_mins_ago, (int)TimeUnit.MILLISECONDS.toMinutes(paramLong), new Object[] { Integer.valueOf((int)TimeUnit.MILLISECONDS.toMinutes(paramLong)) });
  }
  
  public static b.agh d(b.aft paramAft)
  {
    paramAft = paramAft.q.iterator();
    while (paramAft.hasNext())
    {
      b.agh localAgh = (b.agh)paramAft.next();
      if ("Game".equals(localAgh.a)) {
        return localAgh;
      }
    }
    return null;
  }
  
  public static n.c d(PresenceState paramPresenceState)
  {
    if (e(paramPresenceState)) {
      return n.c.YouTube;
    }
    if (f(paramPresenceState)) {
      return n.c.Facebook;
    }
    return n.c.Omlet;
  }
  
  public static void d(Context paramContext, Uri paramUri)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setFlags(268435457);
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramContext.getPackageName());
    localStringBuilder.append(".fileprovider");
    localIntent.setDataAndType(FileProvider.a(paramContext, localStringBuilder.toString(), new File(paramUri.getPath())), "minecraft/pack");
    if (paramContext.getPackageManager().resolveActivity(localIntent, 0) != null)
    {
      paramContext.startActivity(localIntent);
      return;
    }
    if (Boolean.TRUE.equals(H(paramContext)))
    {
      OMToast.makeText(paramContext, paramContext.getString(R.string.minecraft_download_mod_16_required), 1).show();
      return;
    }
    OMToast.makeText(paramContext, paramContext.getString(R.string.minecraft_download_mod_failed_install_minecraft), 0).show();
  }
  
  public static void d(Context paramContext, b.eo paramEo)
  {
    Intent localIntent = new Intent("mobisocial.arcade.action.VIEW_MANAGED_COMMUNITY");
    localIntent.setPackage(paramContext.getPackageName());
    localIntent.putExtra("extra_community_id", mobisocial.b.a.a(paramEo, b.eo.class));
    if (!(paramContext instanceof Activity)) {
      localIntent.addFlags(268468224);
    }
    paramContext.startActivity(localIntent);
  }
  
  public static boolean d(Context paramContext)
  {
    Intent localIntent = paramContext.getPackageManager().getLaunchIntentForPackage("com.mojang.minecraftpe");
    if (localIntent != null)
    {
      paramContext.startActivity(localIntent);
      return true;
    }
    OMToast.makeText(paramContext, paramContext.getString(R.string.minecraft_download_world_failed_install_minecraft), 0).show();
    return false;
  }
  
  public static boolean d(Uri paramUri)
  {
    return "com.android.providers.downloads.documents".equals(paramUri.getAuthority());
  }
  
  public static boolean d(b.amn paramAmn)
  {
    return (paramAmn != null) && (paramAmn.n != null) && (!TextUtils.isEmpty((String)paramAmn.n.get("LiveJoinGameUrl")));
  }
  
  public static CharSequence e(String paramString)
  {
    if (Build.VERSION.SDK_INT >= 24) {
      return a(Html.fromHtml(paramString, 0));
    }
    return a(Html.fromHtml(paramString));
  }
  
  public static String e(Context paramContext, Uri paramUri)
  {
    int n;
    if (Build.VERSION.SDK_INT >= 19) {
      n = 1;
    } else {
      n = 0;
    }
    if (n != 0) {}
    for (;;)
    {
      try
      {
        if (DocumentsContract.isDocumentUri(paramContext, paramUri))
        {
          if (c(paramUri))
          {
            paramUri = DocumentsContract.getDocumentId(paramUri).split(":");
            if (!"primary".equalsIgnoreCase(paramUri[0])) {
              continue;
            }
            localObject = new StringBuilder();
            ((StringBuilder)localObject).append(Environment.getExternalStorageDirectory());
            ((StringBuilder)localObject).append("/");
            ((StringBuilder)localObject).append(paramUri[1]);
            return ((StringBuilder)localObject).toString();
          }
          if (d(paramUri))
          {
            paramUri = DocumentsContract.getDocumentId(paramUri);
            return a(paramContext, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(paramUri).longValue()), null, null);
          }
          if (!e(paramUri)) {
            continue;
          }
          localObject = DocumentsContract.getDocumentId(paramUri).split(":");
          paramUri = localObject[0];
          if ("image".equals(paramUri))
          {
            paramUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
          }
          else if ("video".equals(paramUri))
          {
            paramUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
          }
          else
          {
            if (!"audio".equals(paramUri)) {
              continue;
            }
            paramUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
          }
          return a(paramContext, paramUri, "_id=?", new String[] { localObject[1] });
        }
        if ("content".equalsIgnoreCase(paramUri.getScheme())) {
          return a(paramContext, paramUri, null, null);
        }
        if (!"file".equalsIgnoreCase(paramUri.getScheme())) {
          continue;
        }
        paramUri = paramUri.getPath();
        return paramUri;
      }
      catch (Exception localException)
      {
        Object localObject;
        continue;
      }
      paramUri = new HashMap();
      paramUri.put("Exception", ((Exception)localObject).toString());
      OmlibApiManager.getInstance(paramContext).analytics().trackEvent(b.b.Error, b.a.PictureImportFail, paramUri);
      return null;
      paramUri = null;
    }
  }
  
  public static void e(Context paramContext, String paramString)
  {
    OmletGameSDK.launchSignInActivity(paramContext, paramString);
  }
  
  public static boolean e(Context paramContext)
  {
    Intent localIntent = paramContext.getPackageManager().getLaunchIntentForPackage("com.mojang.minecraftpe");
    if (localIntent != null)
    {
      paramContext.startActivity(localIntent);
      return true;
    }
    OMToast.makeText(paramContext, paramContext.getString(R.string.minecraft_install_minecraft), 0).show();
    return false;
  }
  
  public static boolean e(Uri paramUri)
  {
    return "com.android.providers.media.documents".equals(paramUri.getAuthority());
  }
  
  private static boolean e(b.amn paramAmn)
  {
    return a(paramAmn, "youtube");
  }
  
  private static boolean e(PresenceState paramPresenceState)
  {
    return a(paramPresenceState, "youtube");
  }
  
  public static Intent f(Context paramContext, String paramString)
  {
    return OmletGameSDK.getStartSignInIntent(paramContext, paramString);
  }
  
  public static PackageInfo f(Context paramContext)
  {
    if (System.currentTimeMillis() - f < 1000L)
    {
      if (e != null) {
        return e;
      }
      throw new PackageManager.NameNotFoundException();
    }
    f = System.currentTimeMillis();
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo("com.mojang.minecraftpe", 1);
      e = paramContext;
      if (paramContext != null) {
        return paramContext;
      }
      throw new PackageManager.NameNotFoundException();
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    e = null;
    throw new PackageManager.NameNotFoundException();
  }
  
  public static boolean f(String paramString)
  {
    if ((paramString.contains("//www.facebook.com")) && (paramString.contains("/videos/"))) {
      return true;
    }
    if ((!paramString.contains("//www.youtube.com")) && (!paramString.contains("//youtu.be"))) {
      return paramString.contains("//m.youtube");
    }
    return true;
  }
  
  private static boolean f(b.amn paramAmn)
  {
    return a(paramAmn, "facebook");
  }
  
  private static boolean f(PresenceState paramPresenceState)
  {
    return a(paramPresenceState, "facebook");
  }
  
  public static void g(Context paramContext, String paramString)
  {
    a(paramContext, paramString, false);
  }
  
  public static boolean g(Context paramContext)
  {
    return o(paramContext, "android.permission.RECORD_AUDIO");
  }
  
  public static boolean g(String paramString)
  {
    return ("com.in.reallife".equals(paramString)) || ("com.in.creative".equals(paramString)) || ("com.in.anime".equals(paramString));
  }
  
  public static void h(Context paramContext, String paramString)
  {
    OmlibApiManager.getInstance(paramContext).analytics().trackEvent(b.b.Event, b.a.Share);
    Intent localIntent = new Intent("android.intent.action.SEND");
    localIntent.setType("text/plain");
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("https://arcade.omlet.me/event/");
    localStringBuilder.append(paramString);
    paramString = localStringBuilder.toString();
    localIntent.putExtra("android.intent.extra.TEXT", paramString);
    try
    {
      paramString = b(paramContext, paramString, false);
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      for (;;) {}
    }
    paramString = null;
    a(paramContext, localIntent, b.b.Event.name(), paramContext.getString(R.string.omp_share_community), paramString);
  }
  
  public static boolean h(Context paramContext)
  {
    return o(paramContext, "android.permission.CAMERA");
  }
  
  public static void i(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent("android.intent.action.SEND");
    localIntent.setType("text/plain");
    localIntent.putExtra("android.intent.extra.TEXT", paramString);
    try
    {
      paramString = b(paramContext, paramString, false);
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      for (;;) {}
    }
    paramString = null;
    a(paramContext, localIntent, "SquadInviteRoster", paramContext.getString(R.string.omp_share_squad), paramString);
  }
  
  public static boolean i(Context paramContext)
  {
    return o(paramContext, "android.permission.WRITE_EXTERNAL_STORAGE");
  }
  
  public static void j(Context paramContext, String paramString)
  {
    OMToast.makeText(paramContext, paramContext.getString(R.string.omp_updateCompleteActivity_copy_share_link), 0).show();
    if (Build.VERSION.SDK_INT < 11)
    {
      ((android.text.ClipboardManager)paramContext.getSystemService("clipboard")).setText(paramString);
      return;
    }
    ((android.content.ClipboardManager)paramContext.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText(paramContext.getString(R.string.omp_updateCompleteActivity_copied_text), paramString));
  }
  
  public static boolean j(Context paramContext)
  {
    return o(paramContext, "android.permission.READ_EXTERNAL_STORAGE");
  }
  
  public static boolean k(Context paramContext)
  {
    paramContext = paramContext.getPackageManager();
    if (Build.VERSION.SDK_INT >= 24) {
      paramContext = paramContext.getInstalledPackages(8192);
    } else {
      paramContext = paramContext.getInstalledPackages(8192);
    }
    paramContext = paramContext.iterator();
    while (paramContext.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
      if ((localPackageInfo.packageName.equals("com.google.market")) || (localPackageInfo.packageName.equals("com.android.vending"))) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean k(Context paramContext, String paramString)
  {
    paramString = q(paramContext, paramString);
    if (paramString != null)
    {
      paramString.putExtra("extraShowChat", true);
      paramContext.startActivity(paramString);
      return true;
    }
    return false;
  }
  
  public static boolean l(Context paramContext)
  {
    if (m == null)
    {
      paramContext = paramContext.getPackageManager().getInstallerPackageName(paramContext.getPackageName());
      boolean bool;
      if ((paramContext != null) && (paramContext.toLowerCase().contains(".amazon."))) {
        bool = true;
      } else {
        bool = false;
      }
      m = Boolean.valueOf(bool);
    }
    return m.booleanValue();
  }
  
  public static boolean l(Context paramContext, String paramString)
  {
    paramString = q(paramContext, paramString);
    if (paramString != null)
    {
      paramString.putExtra("extraOpenPosts", true);
      paramContext.startActivity(paramString);
      return true;
    }
    return false;
  }
  
  public static int m(Context paramContext)
  {
    int n = paramContext.getResources().getIdentifier("status_bar_height", "dimen", "android");
    if (n > 0) {
      return paramContext.getResources().getDimensionPixelSize(n);
    }
    return 0;
  }
  
  public static void m(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent("mobisocial.arcade.action.VIEW_COMMUNITY");
    localIntent.setPackage(paramContext.getPackageName());
    localIntent.putExtra("extra_community_id", mobisocial.b.a.b(mobisocial.omlet.b.a.a.a(paramString)));
    if (!(paramContext instanceof Activity)) {
      localIntent.addFlags(276824064);
    }
    if (!paramContext.getPackageName().equals(OmletGameSDK.getLatestPackage())) {
      localIntent.addFlags(32768);
    }
    paramContext.startActivity(localIntent);
  }
  
  public static Intent n(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getLaunchIntentForPackage(paramString);
      paramContext.addFlags(268435456);
      paramContext.putExtra("mobisocial.arcade.LAUNCH_FROM_ARCADE", true);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return null;
  }
  
  public static void n(Context paramContext)
  {
    paramContext.startActivity(o(paramContext));
  }
  
  public static Intent o(Context paramContext)
  {
    return OmletGameSDK.getStartSignInIntent(paramContext, b.a.SignedInGuestStarted.name());
  }
  
  private static boolean o(Context paramContext, String paramString)
  {
    return a(paramContext, new String[] { paramString }, null, null, false);
  }
  
  private static Intent p(Context paramContext, String paramString)
  {
    OMAccount localOMAccount = (OMAccount)OMSQLiteHelper.getInstance(paramContext).getObjectByKey(OMAccount.class, OmlibApiManager.getInstance(paramContext).auth().getAccount());
    if (localOMAccount != null) {
      return a(localOMAccount.omletId, localOMAccount.account, paramString);
    }
    return a(null, OmlibApiManager.getInstance(paramContext).auth().getAccount(), paramString);
  }
  
  public static void p(Context paramContext)
  {
    paramContext.startActivity(q(paramContext));
  }
  
  public static Intent q(Context paramContext)
  {
    paramContext = OmletGameSDK.getStartSignInIntent(paramContext, b.a.SignedInGuestStarted.name());
    paramContext.putExtra("setupForGuestRequest", true);
    return paramContext;
  }
  
  private static Intent q(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent("mobisocial.arcade.action.VIEW_PROFILE");
    localIntent.setPackage(paramContext.getPackageName());
    if (paramContext.getPackageManager().resolveActivity(localIntent, 65536) != null)
    {
      String str = paramString;
      if (paramString == null) {
        str = OmlibApiManager.getInstance(paramContext).auth().getAccount();
      }
      localIntent.putExtra("extraUserAccount", str);
      if (!s(paramContext)) {
        localIntent.addFlags(276824064);
      }
      if (!paramContext.getPackageName().equals(OmletGameSDK.getLatestPackage())) {
        localIntent.addFlags(32768);
      }
      return localIntent;
    }
    return null;
  }
  
  public static String r(Context paramContext)
  {
    Object localObject = (OMAccount)OMSQLiteHelper.getInstance(paramContext).getObjectByKey(OMAccount.class, OmlibApiManager.getInstance(paramContext).auth().getAccount());
    if (localObject != null) {
      paramContext = b(((OMAccount)localObject).omletId, ((OMAccount)localObject).account);
    } else {
      paramContext = OmlibApiManager.getInstance(paramContext).auth().getAccount();
    }
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("https://arcade.omlet.me/stream/");
    ((StringBuilder)localObject).append(paramContext);
    return ((StringBuilder)localObject).toString();
  }
  
  public static boolean s(Context paramContext)
  {
    if (paramContext == null) {
      return false;
    }
    if ((paramContext instanceof FragmentActivity)) {
      return true;
    }
    if ((paramContext instanceof Activity)) {
      return true;
    }
    if ((paramContext instanceof ContextWrapper)) {
      return s(((ContextWrapper)paramContext).getBaseContext());
    }
    return false;
  }
  
  public static boolean t(Context paramContext)
  {
    if (paramContext == null) {
      return true;
    }
    if ((paramContext instanceof FragmentActivity)) {
      return a((Activity)paramContext);
    }
    if ((paramContext instanceof Activity)) {
      return a((Activity)paramContext);
    }
    if ((paramContext instanceof ContextWrapper)) {
      return t(((ContextWrapper)paramContext).getBaseContext());
    }
    return false;
  }
  
  public static AlertDialog.Builder u(Context paramContext)
  {
    return new AlertDialog.Builder(new ContextThemeWrapper(paramContext, 16973939));
  }
  
  public static void v(Context paramContext)
  {
    Intent localIntent = new Intent("mobisocial.arcade.action.COMMUNITY_ONBOARDING");
    localIntent.setPackage(paramContext.getPackageName());
    if (!(paramContext instanceof Activity)) {
      localIntent.addFlags(276824064);
    }
    if (!paramContext.getPackageName().equals(OmletGameSDK.getLatestPackage())) {
      localIntent.addFlags(32768);
    }
    paramContext.startActivity(localIntent);
  }
  
  public static void w(Context paramContext)
  {
    Object localObject = new HashMap();
    ((Map)localObject).put("from", "community_picker_empty_view");
    OmlibApiManager.getInstance(paramContext).analytics().trackEvent(b.b.ManagedCommunity, b.a.BrowseCommunities, (Map)localObject);
    localObject = new Intent("mobisocial.arcade.action.VIEW_BROWSE_COMMUNITY");
    if (!(paramContext instanceof Activity)) {
      ((Intent)localObject).addFlags(276824064);
    }
    if (!paramContext.getPackageName().equals(OmletGameSDK.getLatestPackage())) {
      ((Intent)localObject).addFlags(32768);
    }
    paramContext.startActivity((Intent)localObject);
  }
  
  public static int x(Context paramContext)
  {
    return Integer.valueOf(((AudioManager)paramContext.getSystemService("audio")).getProperty("android.media.property.OUTPUT_SAMPLE_RATE")).intValue();
  }
  
  public static boolean y(Context paramContext)
  {
    if (PreferenceManager.getDefaultSharedPreferences(paramContext).contains("isOmletAdmin")) {
      return PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean("isOmletAdmin", false);
    }
    new r.17(paramContext).executeOnExecutor(OmlibApiManager.THREAD_POOL_EXECUTOR, new Void[0]);
    return false;
  }
  
  public static boolean z(Context paramContext)
  {
    return (paramContext.getApplicationInfo().flags & 0x2) != 0;
  }
}
