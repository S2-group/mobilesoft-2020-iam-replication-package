package net.geekstools.floatshort.PRO;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Handler;
import android.os.IBinder;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import net.geekstools.floatshort.PRO.Util.Functions.FunctionsClass;
import net.geekstools.floatshort.PRO.Util.Functions.PublicVariable;
import net.geekstools.imageview.customshapes.ShapesImage;

public class App_Unlimited_HIS
  extends Service
{
  boolean[] StickyEdge;
  WindowManager.LayoutParams[] StickyEdgeParams;
  ActivityInfo[] activityInfo;
  boolean[] allowMove;
  Drawable[] appIcon;
  int array;
  BroadcastReceiver broadcastReceiver;
  String[] className;
  ComponentName[] componentName;
  ShapesImage[] controlIcon;
  Handler delayHandler = new Handler();
  Runnable delayRunnable = null;
  ViewGroup[] floatingView;
  FunctionsClass functionsClass;
  Handler getbackHandler = new Handler();
  Runnable getbackRunnable = null;
  Handler handlerPressHold = new Handler();
  int[] iconColor;
  Map<String, Integer> mapPackageNameStartId;
  WindowManager.LayoutParams moveDetection;
  ShapesImage[] notificationDot;
  boolean[] openIt;
  String[] packageName;
  WindowManager.LayoutParams[] params;
  boolean[] remove;
  Runnable runnablePressHold = null;
  ShapesImage[] shapedIcon;
  SharedPreferences sharedPrefPosition;
  boolean[] touchingDelay;
  WindowManager windowManager;
  int xInit = 13;
  int xMove;
  int xPos;
  int yInit = 13;
  int yMove;
  int yPos;
  
  public App_Unlimited_HIS() {}
  
  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }
  
  public void onCreate()
  {
    super.onCreate();
    this.functionsClass = new FunctionsClass(getApplicationContext());
    this.array = (getApplicationContext().getPackageManager().getInstalledApplications(0).size() * 2);
    this.params = new WindowManager.LayoutParams[this.array];
    this.StickyEdgeParams = new WindowManager.LayoutParams[this.array];
    this.packageName = new String[this.array];
    this.className = new String[this.array];
    this.appIcon = new Drawable[this.array];
    this.iconColor = new int[this.array];
    this.floatingView = new ViewGroup[this.array];
    this.controlIcon = new ShapesImage[this.array];
    this.shapedIcon = new ShapesImage[this.array];
    this.notificationDot = new ShapesImage[this.array];
    this.allowMove = new boolean[this.array];
    this.remove = new boolean[this.array];
    this.touchingDelay = new boolean[this.array];
    this.StickyEdge = new boolean[this.array];
    this.openIt = new boolean[this.array];
    this.componentName = new ComponentName[this.array];
    this.activityInfo = new ActivityInfo[this.array];
    this.mapPackageNameStartId = new LinkedHashMap();
  }
  
  /* Error */
  public int onStartCommand(final Intent paramIntent, int paramInt1, final int paramInt2)
  {
    // Byte code:
    //   0: getstatic 204	java/lang/System:out	Ljava/io/PrintStream;
    //   3: astore 5
    //   5: new 206	java/lang/StringBuilder
    //   8: dup
    //   9: invokespecial 207	java/lang/StringBuilder:<init>	()V
    //   12: astore 6
    //   14: aload 6
    //   16: aload_0
    //   17: invokevirtual 213	java/lang/Object:getClass	()Ljava/lang/Class;
    //   20: invokevirtual 219	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   23: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   26: pop
    //   27: aload 6
    //   29: ldc -31
    //   31: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   34: pop
    //   35: aload 6
    //   37: iload_3
    //   38: invokevirtual 228	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   41: pop
    //   42: aload 5
    //   44: aload 6
    //   46: invokevirtual 231	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   49: invokevirtual 237	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   52: aload_0
    //   53: aload_0
    //   54: ldc -17
    //   56: invokevirtual 243	net/geekstools/floatshort/PRO/App_Unlimited_HIS:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   59: checkcast 245	android/view/WindowManager
    //   62: putfield 247	net/geekstools/floatshort/PRO/App_Unlimited_HIS:windowManager	Landroid/view/WindowManager;
    //   65: aload_0
    //   66: ldc -7
    //   68: invokevirtual 243	net/geekstools/floatshort/PRO/App_Unlimited_HIS:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   71: checkcast 251	android/view/LayoutInflater
    //   74: astore 5
    //   76: aload_0
    //   77: getfield 173	net/geekstools/floatshort/PRO/App_Unlimited_HIS:allowMove	[Z
    //   80: iload_3
    //   81: iconst_1
    //   82: bastore
    //   83: aload_0
    //   84: getfield 151	net/geekstools/floatshort/PRO/App_Unlimited_HIS:packageName	[Ljava/lang/String;
    //   87: iload_3
    //   88: aload_1
    //   89: ldc -4
    //   91: invokevirtual 258	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   94: aastore
    //   95: aload_0
    //   96: getfield 151	net/geekstools/floatshort/PRO/App_Unlimited_HIS:packageName	[Ljava/lang/String;
    //   99: iload_3
    //   100: aaload
    //   101: aload_0
    //   102: ldc_w 259
    //   105: invokevirtual 263	net/geekstools/floatshort/PRO/App_Unlimited_HIS:getString	(I)Ljava/lang/String;
    //   108: invokevirtual 267	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   111: ifne +192 -> 303
    //   114: aload_0
    //   115: getfield 153	net/geekstools/floatshort/PRO/App_Unlimited_HIS:className	[Ljava/lang/String;
    //   118: iload_3
    //   119: aload_1
    //   120: ldc_w 268
    //   123: invokevirtual 258	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   126: aastore
    //   127: aload_0
    //   128: getfield 185	net/geekstools/floatshort/PRO/App_Unlimited_HIS:componentName	[Landroid/content/ComponentName;
    //   131: iload_3
    //   132: new 183	android/content/ComponentName
    //   135: dup
    //   136: aload_0
    //   137: getfield 151	net/geekstools/floatshort/PRO/App_Unlimited_HIS:packageName	[Ljava/lang/String;
    //   140: iload_3
    //   141: aaload
    //   142: aload_0
    //   143: getfield 153	net/geekstools/floatshort/PRO/App_Unlimited_HIS:className	[Ljava/lang/String;
    //   146: iload_3
    //   147: aaload
    //   148: invokespecial 271	android/content/ComponentName:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   151: aastore
    //   152: aload_0
    //   153: getfield 189	net/geekstools/floatshort/PRO/App_Unlimited_HIS:activityInfo	[Landroid/content/pm/ActivityInfo;
    //   156: iload_3
    //   157: aload_0
    //   158: invokevirtual 272	net/geekstools/floatshort/PRO/App_Unlimited_HIS:getPackageManager	()Landroid/content/pm/PackageManager;
    //   161: aload_0
    //   162: getfield 185	net/geekstools/floatshort/PRO/App_Unlimited_HIS:componentName	[Landroid/content/ComponentName;
    //   165: iload_3
    //   166: aaload
    //   167: iconst_0
    //   168: invokevirtual 276	android/content/pm/PackageManager:getActivityInfo	(Landroid/content/ComponentName;I)Landroid/content/pm/ActivityInfo;
    //   171: aastore
    //   172: aload_0
    //   173: getfield 163	net/geekstools/floatshort/PRO/App_Unlimited_HIS:floatingView	[Landroid/view/ViewGroup;
    //   176: iload_3
    //   177: aload 5
    //   179: ldc_w 277
    //   182: aconst_null
    //   183: iconst_0
    //   184: invokevirtual 281	android/view/LayoutInflater:inflate	(ILandroid/view/ViewGroup;Z)Landroid/view/View;
    //   187: checkcast 161	android/view/ViewGroup
    //   190: aastore
    //   191: aload_0
    //   192: getfield 167	net/geekstools/floatshort/PRO/App_Unlimited_HIS:controlIcon	[Lnet/geekstools/imageview/customshapes/ShapesImage;
    //   195: iload_3
    //   196: aload_0
    //   197: getfield 121	net/geekstools/floatshort/PRO/App_Unlimited_HIS:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
    //   200: aload_0
    //   201: getfield 163	net/geekstools/floatshort/PRO/App_Unlimited_HIS:floatingView	[Landroid/view/ViewGroup;
    //   204: iload_3
    //   205: aaload
    //   206: ldc_w 282
    //   209: invokevirtual 286	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:initShapesImage	(Landroid/view/ViewGroup;I)Lnet/geekstools/imageview/customshapes/ShapesImage;
    //   212: aastore
    //   213: aload_0
    //   214: getfield 169	net/geekstools/floatshort/PRO/App_Unlimited_HIS:shapedIcon	[Lnet/geekstools/imageview/customshapes/ShapesImage;
    //   217: iload_3
    //   218: aload_0
    //   219: getfield 121	net/geekstools/floatshort/PRO/App_Unlimited_HIS:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
    //   222: aload_0
    //   223: getfield 163	net/geekstools/floatshort/PRO/App_Unlimited_HIS:floatingView	[Landroid/view/ViewGroup;
    //   226: iload_3
    //   227: aaload
    //   228: ldc_w 287
    //   231: invokevirtual 286	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:initShapesImage	(Landroid/view/ViewGroup;I)Lnet/geekstools/imageview/customshapes/ShapesImage;
    //   234: aastore
    //   235: aload_0
    //   236: getfield 171	net/geekstools/floatshort/PRO/App_Unlimited_HIS:notificationDot	[Lnet/geekstools/imageview/customshapes/ShapesImage;
    //   239: astore_1
    //   240: aload_0
    //   241: getfield 121	net/geekstools/floatshort/PRO/App_Unlimited_HIS:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
    //   244: astore 5
    //   246: aload_0
    //   247: getfield 163	net/geekstools/floatshort/PRO/App_Unlimited_HIS:floatingView	[Landroid/view/ViewGroup;
    //   250: iload_3
    //   251: aaload
    //   252: astore 6
    //   254: aload_0
    //   255: getfield 121	net/geekstools/floatshort/PRO/App_Unlimited_HIS:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
    //   258: invokevirtual 291	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:checkStickyEdge	()Z
    //   261: ifeq +1125 -> 1386
    //   264: ldc_w 292
    //   267: istore_2
    //   268: goto +3 -> 271
    //   271: aload_1
    //   272: iload_3
    //   273: aload 5
    //   275: aload 6
    //   277: iload_2
    //   278: invokevirtual 286	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:initShapesImage	(Landroid/view/ViewGroup;I)Lnet/geekstools/imageview/customshapes/ShapesImage;
    //   281: aastore
    //   282: aload_0
    //   283: getfield 177	net/geekstools/floatshort/PRO/App_Unlimited_HIS:touchingDelay	[Z
    //   286: iload_3
    //   287: iconst_0
    //   288: bastore
    //   289: aload_0
    //   290: getfield 179	net/geekstools/floatshort/PRO/App_Unlimited_HIS:StickyEdge	[Z
    //   293: iload_3
    //   294: iconst_0
    //   295: bastore
    //   296: aload_0
    //   297: getfield 181	net/geekstools/floatshort/PRO/App_Unlimited_HIS:openIt	[Z
    //   300: iload_3
    //   301: iconst_1
    //   302: bastore
    //   303: aload_0
    //   304: getfield 151	net/geekstools/floatshort/PRO/App_Unlimited_HIS:packageName	[Ljava/lang/String;
    //   307: iload_3
    //   308: aaload
    //   309: aload_0
    //   310: ldc_w 259
    //   313: invokevirtual 263	net/geekstools/floatshort/PRO/App_Unlimited_HIS:getString	(I)Ljava/lang/String;
    //   316: invokevirtual 267	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   319: ifeq +307 -> 626
    //   322: iconst_1
    //   323: istore_2
    //   324: iload_2
    //   325: iload_3
    //   326: if_icmpge +271 -> 597
    //   329: aload_0
    //   330: getfield 163	net/geekstools/floatshort/PRO/App_Unlimited_HIS:floatingView	[Landroid/view/ViewGroup;
    //   333: ifnull +257 -> 590
    //   336: aload_0
    //   337: getfield 163	net/geekstools/floatshort/PRO/App_Unlimited_HIS:floatingView	[Landroid/view/ViewGroup;
    //   340: iload_2
    //   341: aaload
    //   342: invokevirtual 295	android/view/ViewGroup:isShown	()Z
    //   345: istore 4
    //   347: iload 4
    //   349: ifeq +189 -> 538
    //   352: aload_0
    //   353: getfield 247	net/geekstools/floatshort/PRO/App_Unlimited_HIS:windowManager	Landroid/view/WindowManager;
    //   356: aload_0
    //   357: getfield 163	net/geekstools/floatshort/PRO/App_Unlimited_HIS:floatingView	[Landroid/view/ViewGroup;
    //   360: iload_2
    //   361: aaload
    //   362: invokeinterface 299 2 0
    //   367: getstatic 304	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:floatingCounter	I
    //   370: iconst_1
    //   371: isub
    //   372: putstatic 304	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:floatingCounter	I
    //   375: getstatic 304	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:floatingCounter	I
    //   378: ifne +212 -> 590
    //   381: aload_0
    //   382: invokevirtual 116	net/geekstools/floatshort/PRO/App_Unlimited_HIS:getApplicationContext	()Landroid/content/Context;
    //   385: invokestatic 310	android/preference/PreferenceManager:getDefaultSharedPreferences	(Landroid/content/Context;)Landroid/content/SharedPreferences;
    //   388: ldc_w 312
    //   391: iconst_1
    //   392: invokeinterface 318 3 0
    //   397: ifne +193 -> 590
    //   400: new 254	android/content/Intent
    //   403: dup
    //   404: aload_0
    //   405: invokevirtual 116	net/geekstools/floatshort/PRO/App_Unlimited_HIS:getApplicationContext	()Landroid/content/Context;
    //   408: ldc_w 320
    //   411: invokespecial 323	android/content/Intent:<init>	(Landroid/content/Context;Ljava/lang/Class;)V
    //   414: astore_1
    //   415: aload_0
    //   416: aload_1
    //   417: invokevirtual 327	net/geekstools/floatshort/PRO/App_Unlimited_HIS:stopService	(Landroid/content/Intent;)Z
    //   420: pop
    //   421: goto +169 -> 590
    //   424: astore_1
    //   425: goto +59 -> 484
    //   428: astore_1
    //   429: aload_1
    //   430: invokevirtual 330	java/lang/Exception:printStackTrace	()V
    //   433: getstatic 304	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:floatingCounter	I
    //   436: iconst_1
    //   437: isub
    //   438: putstatic 304	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:floatingCounter	I
    //   441: getstatic 304	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:floatingCounter	I
    //   444: ifne +146 -> 590
    //   447: aload_0
    //   448: invokevirtual 116	net/geekstools/floatshort/PRO/App_Unlimited_HIS:getApplicationContext	()Landroid/content/Context;
    //   451: invokestatic 310	android/preference/PreferenceManager:getDefaultSharedPreferences	(Landroid/content/Context;)Landroid/content/SharedPreferences;
    //   454: ldc_w 312
    //   457: iconst_1
    //   458: invokeinterface 318 3 0
    //   463: ifne +127 -> 590
    //   466: new 254	android/content/Intent
    //   469: dup
    //   470: aload_0
    //   471: invokevirtual 116	net/geekstools/floatshort/PRO/App_Unlimited_HIS:getApplicationContext	()Landroid/content/Context;
    //   474: ldc_w 320
    //   477: invokespecial 323	android/content/Intent:<init>	(Landroid/content/Context;Ljava/lang/Class;)V
    //   480: astore_1
    //   481: goto -66 -> 415
    //   484: getstatic 304	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:floatingCounter	I
    //   487: iconst_1
    //   488: isub
    //   489: putstatic 304	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:floatingCounter	I
    //   492: getstatic 304	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:floatingCounter	I
    //   495: ifne +41 -> 536
    //   498: aload_0
    //   499: invokevirtual 116	net/geekstools/floatshort/PRO/App_Unlimited_HIS:getApplicationContext	()Landroid/content/Context;
    //   502: invokestatic 310	android/preference/PreferenceManager:getDefaultSharedPreferences	(Landroid/content/Context;)Landroid/content/SharedPreferences;
    //   505: ldc_w 312
    //   508: iconst_1
    //   509: invokeinterface 318 3 0
    //   514: ifne +22 -> 536
    //   517: aload_0
    //   518: new 254	android/content/Intent
    //   521: dup
    //   522: aload_0
    //   523: invokevirtual 116	net/geekstools/floatshort/PRO/App_Unlimited_HIS:getApplicationContext	()Landroid/content/Context;
    //   526: ldc_w 320
    //   529: invokespecial 323	android/content/Intent:<init>	(Landroid/content/Context;Ljava/lang/Class;)V
    //   532: invokevirtual 327	net/geekstools/floatshort/PRO/App_Unlimited_HIS:stopService	(Landroid/content/Intent;)Z
    //   535: pop
    //   536: aload_1
    //   537: athrow
    //   538: getstatic 304	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:floatingCounter	I
    //   541: ifne +49 -> 590
    //   544: aload_0
    //   545: invokevirtual 116	net/geekstools/floatshort/PRO/App_Unlimited_HIS:getApplicationContext	()Landroid/content/Context;
    //   548: invokestatic 310	android/preference/PreferenceManager:getDefaultSharedPreferences	(Landroid/content/Context;)Landroid/content/SharedPreferences;
    //   551: ldc_w 312
    //   554: iconst_1
    //   555: invokeinterface 318 3 0
    //   560: ifne +30 -> 590
    //   563: aload_0
    //   564: new 254	android/content/Intent
    //   567: dup
    //   568: aload_0
    //   569: invokevirtual 116	net/geekstools/floatshort/PRO/App_Unlimited_HIS:getApplicationContext	()Landroid/content/Context;
    //   572: ldc_w 320
    //   575: invokespecial 323	android/content/Intent:<init>	(Landroid/content/Context;Ljava/lang/Class;)V
    //   578: invokevirtual 327	net/geekstools/floatshort/PRO/App_Unlimited_HIS:stopService	(Landroid/content/Intent;)Z
    //   581: pop
    //   582: goto +8 -> 590
    //   585: astore_1
    //   586: aload_1
    //   587: invokevirtual 330	java/lang/Exception:printStackTrace	()V
    //   590: iload_2
    //   591: iconst_1
    //   592: iadd
    //   593: istore_2
    //   594: goto -270 -> 324
    //   597: aload_0
    //   598: getfield 332	net/geekstools/floatshort/PRO/App_Unlimited_HIS:broadcastReceiver	Landroid/content/BroadcastReceiver;
    //   601: ifnull +19 -> 620
    //   604: aload_0
    //   605: aload_0
    //   606: getfield 332	net/geekstools/floatshort/PRO/App_Unlimited_HIS:broadcastReceiver	Landroid/content/BroadcastReceiver;
    //   609: invokevirtual 336	net/geekstools/floatshort/PRO/App_Unlimited_HIS:unregisterReceiver	(Landroid/content/BroadcastReceiver;)V
    //   612: goto +8 -> 620
    //   615: astore_1
    //   616: aload_1
    //   617: invokevirtual 330	java/lang/Exception:printStackTrace	()V
    //   620: aload_0
    //   621: invokevirtual 339	net/geekstools/floatshort/PRO/App_Unlimited_HIS:stopSelf	()V
    //   624: iconst_2
    //   625: ireturn
    //   626: aload_0
    //   627: getfield 194	net/geekstools/floatshort/PRO/App_Unlimited_HIS:mapPackageNameStartId	Ljava/util/Map;
    //   630: aload_0
    //   631: getfield 153	net/geekstools/floatshort/PRO/App_Unlimited_HIS:className	[Ljava/lang/String;
    //   634: iload_3
    //   635: aaload
    //   636: iload_3
    //   637: invokestatic 345	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   640: invokeinterface 351 3 0
    //   645: pop
    //   646: aload_0
    //   647: getfield 121	net/geekstools/floatshort/PRO/App_Unlimited_HIS:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
    //   650: aload_0
    //   651: getfield 151	net/geekstools/floatshort/PRO/App_Unlimited_HIS:packageName	[Ljava/lang/String;
    //   654: iload_3
    //   655: aaload
    //   656: invokevirtual 355	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:appInstalledOrNot	(Ljava/lang/String;)Z
    //   659: ifne +5 -> 664
    //   662: iconst_2
    //   663: ireturn
    //   664: aload_0
    //   665: getfield 121	net/geekstools/floatshort/PRO/App_Unlimited_HIS:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
    //   668: aload_0
    //   669: getfield 151	net/geekstools/floatshort/PRO/App_Unlimited_HIS:packageName	[Ljava/lang/String;
    //   672: iload_3
    //   673: aaload
    //   674: invokevirtual 358	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:saveUnlimitedShortcutsService	(Ljava/lang/String;)V
    //   677: aload_0
    //   678: getfield 157	net/geekstools/floatshort/PRO/App_Unlimited_HIS:appIcon	[Landroid/graphics/drawable/Drawable;
    //   681: iload_3
    //   682: aload_0
    //   683: getfield 121	net/geekstools/floatshort/PRO/App_Unlimited_HIS:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
    //   686: aload_0
    //   687: getfield 189	net/geekstools/floatshort/PRO/App_Unlimited_HIS:activityInfo	[Landroid/content/pm/ActivityInfo;
    //   690: iload_3
    //   691: aaload
    //   692: invokevirtual 362	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:shapedAppIcon	(Landroid/content/pm/ActivityInfo;)Landroid/graphics/drawable/Drawable;
    //   695: aastore
    //   696: aload_0
    //   697: getfield 159	net/geekstools/floatshort/PRO/App_Unlimited_HIS:iconColor	[I
    //   700: iload_3
    //   701: aload_0
    //   702: getfield 121	net/geekstools/floatshort/PRO/App_Unlimited_HIS:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
    //   705: aload_0
    //   706: getfield 121	net/geekstools/floatshort/PRO/App_Unlimited_HIS:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
    //   709: aload_0
    //   710: getfield 189	net/geekstools/floatshort/PRO/App_Unlimited_HIS:activityInfo	[Landroid/content/pm/ActivityInfo;
    //   713: iload_3
    //   714: aaload
    //   715: invokevirtual 364	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:appIcon	(Landroid/content/pm/ActivityInfo;)Landroid/graphics/drawable/Drawable;
    //   718: invokevirtual 368	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:extractDominantColor	(Landroid/graphics/drawable/Drawable;)I
    //   721: iastore
    //   722: aload_0
    //   723: getfield 169	net/geekstools/floatshort/PRO/App_Unlimited_HIS:shapedIcon	[Lnet/geekstools/imageview/customshapes/ShapesImage;
    //   726: iload_3
    //   727: aaload
    //   728: aload_0
    //   729: getfield 121	net/geekstools/floatshort/PRO/App_Unlimited_HIS:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
    //   732: aload_0
    //   733: getfield 189	net/geekstools/floatshort/PRO/App_Unlimited_HIS:activityInfo	[Landroid/content/pm/ActivityInfo;
    //   736: iload_3
    //   737: aaload
    //   738: invokevirtual 362	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:shapedAppIcon	(Landroid/content/pm/ActivityInfo;)Landroid/graphics/drawable/Drawable;
    //   741: invokevirtual 372	net/geekstools/imageview/customshapes/ShapesImage:setImageDrawable	(Landroid/graphics/drawable/Drawable;)V
    //   744: aload_0
    //   745: aload_0
    //   746: aload_0
    //   747: getfield 153	net/geekstools/floatshort/PRO/App_Unlimited_HIS:className	[Ljava/lang/String;
    //   750: iload_3
    //   751: aaload
    //   752: iconst_0
    //   753: invokevirtual 376	net/geekstools/floatshort/PRO/App_Unlimited_HIS:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   756: putfield 378	net/geekstools/floatshort/PRO/App_Unlimited_HIS:sharedPrefPosition	Landroid/content/SharedPreferences;
    //   759: goto +8 -> 767
    //   762: astore_1
    //   763: aload_1
    //   764: invokevirtual 330	java/lang/Exception:printStackTrace	()V
    //   767: aload_0
    //   768: getfield 121	net/geekstools/floatshort/PRO/App_Unlimited_HIS:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
    //   771: ldc_w 380
    //   774: bipush 39
    //   776: invokevirtual 384	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:readDefaultPreference	(Ljava/lang/String;I)I
    //   779: putstatic 386	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:size	I
    //   782: iconst_1
    //   783: getstatic 386	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:size	I
    //   786: i2f
    //   787: aload_0
    //   788: invokevirtual 116	net/geekstools/floatshort/PRO/App_Unlimited_HIS:getApplicationContext	()Landroid/content/Context;
    //   791: invokevirtual 390	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   794: invokevirtual 396	android/content/res/Resources:getDisplayMetrics	()Landroid/util/DisplayMetrics;
    //   797: invokestatic 402	android/util/TypedValue:applyDimension	(IFLandroid/util/DisplayMetrics;)F
    //   800: f2i
    //   801: putstatic 405	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:HW	I
    //   804: aload_0
    //   805: aload_0
    //   806: getfield 87	net/geekstools/floatshort/PRO/App_Unlimited_HIS:xInit	I
    //   809: bipush 13
    //   811: iadd
    //   812: putfield 87	net/geekstools/floatshort/PRO/App_Unlimited_HIS:xInit	I
    //   815: aload_0
    //   816: aload_0
    //   817: getfield 89	net/geekstools/floatshort/PRO/App_Unlimited_HIS:yInit	I
    //   820: bipush 13
    //   822: iadd
    //   823: putfield 89	net/geekstools/floatshort/PRO/App_Unlimited_HIS:yInit	I
    //   826: aload_0
    //   827: aload_0
    //   828: getfield 378	net/geekstools/floatshort/PRO/App_Unlimited_HIS:sharedPrefPosition	Landroid/content/SharedPreferences;
    //   831: ldc_w 407
    //   834: aload_0
    //   835: getfield 87	net/geekstools/floatshort/PRO/App_Unlimited_HIS:xInit	I
    //   838: invokeinterface 410 3 0
    //   843: putfield 412	net/geekstools/floatshort/PRO/App_Unlimited_HIS:xPos	I
    //   846: aload_0
    //   847: aload_0
    //   848: getfield 378	net/geekstools/floatshort/PRO/App_Unlimited_HIS:sharedPrefPosition	Landroid/content/SharedPreferences;
    //   851: ldc_w 414
    //   854: aload_0
    //   855: getfield 89	net/geekstools/floatshort/PRO/App_Unlimited_HIS:yInit	I
    //   858: invokeinterface 410 3 0
    //   863: putfield 416	net/geekstools/floatshort/PRO/App_Unlimited_HIS:yPos	I
    //   866: aload_0
    //   867: getfield 145	net/geekstools/floatshort/PRO/App_Unlimited_HIS:params	[Landroid/view/WindowManager$LayoutParams;
    //   870: iload_3
    //   871: aload_0
    //   872: getfield 121	net/geekstools/floatshort/PRO/App_Unlimited_HIS:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
    //   875: getstatic 405	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:HW	I
    //   878: aload_0
    //   879: getfield 412	net/geekstools/floatshort/PRO/App_Unlimited_HIS:xPos	I
    //   882: aload_0
    //   883: getfield 416	net/geekstools/floatshort/PRO/App_Unlimited_HIS:yPos	I
    //   886: invokevirtual 420	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:normalLayoutParams	(III)Landroid/view/WindowManager$LayoutParams;
    //   889: aastore
    //   890: aload_0
    //   891: getfield 247	net/geekstools/floatshort/PRO/App_Unlimited_HIS:windowManager	Landroid/view/WindowManager;
    //   894: aload_0
    //   895: getfield 163	net/geekstools/floatshort/PRO/App_Unlimited_HIS:floatingView	[Landroid/view/ViewGroup;
    //   898: iload_3
    //   899: aaload
    //   900: aload_0
    //   901: getfield 145	net/geekstools/floatshort/PRO/App_Unlimited_HIS:params	[Landroid/view/WindowManager$LayoutParams;
    //   904: iload_3
    //   905: aaload
    //   906: invokeinterface 424 3 0
    //   911: aload_0
    //   912: getfield 169	net/geekstools/floatshort/PRO/App_Unlimited_HIS:shapedIcon	[Lnet/geekstools/imageview/customshapes/ShapesImage;
    //   915: iload_3
    //   916: aaload
    //   917: aload_0
    //   918: getfield 121	net/geekstools/floatshort/PRO/App_Unlimited_HIS:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
    //   921: ldc_w 426
    //   924: sipush 255
    //   927: invokevirtual 384	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:readDefaultPreference	(Ljava/lang/String;I)I
    //   930: invokevirtual 430	net/geekstools/imageview/customshapes/ShapesImage:setImageAlpha	(I)V
    //   933: aload_0
    //   934: getfield 163	net/geekstools/floatshort/PRO/App_Unlimited_HIS:floatingView	[Landroid/view/ViewGroup;
    //   937: iload_3
    //   938: aaload
    //   939: new 6	net/geekstools/floatshort/PRO/App_Unlimited_HIS$1
    //   942: dup
    //   943: aload_0
    //   944: invokespecial 433	net/geekstools/floatshort/PRO/App_Unlimited_HIS$1:<init>	(Lnet/geekstools/floatshort/PRO/App_Unlimited_HIS;)V
    //   947: invokevirtual 437	android/view/ViewGroup:setOnFocusChangeListener	(Landroid/view/View$OnFocusChangeListener;)V
    //   950: aload_0
    //   951: getfield 163	net/geekstools/floatshort/PRO/App_Unlimited_HIS:floatingView	[Landroid/view/ViewGroup;
    //   954: iload_3
    //   955: aaload
    //   956: new 8	net/geekstools/floatshort/PRO/App_Unlimited_HIS$2
    //   959: dup
    //   960: aload_0
    //   961: iload_3
    //   962: invokespecial 440	net/geekstools/floatshort/PRO/App_Unlimited_HIS$2:<init>	(Lnet/geekstools/floatshort/PRO/App_Unlimited_HIS;I)V
    //   965: invokevirtual 444	android/view/ViewGroup:setOnTouchListener	(Landroid/view/View$OnTouchListener;)V
    //   968: aload_0
    //   969: getfield 163	net/geekstools/floatshort/PRO/App_Unlimited_HIS:floatingView	[Landroid/view/ViewGroup;
    //   972: iload_3
    //   973: aaload
    //   974: new 18	net/geekstools/floatshort/PRO/App_Unlimited_HIS$3
    //   977: dup
    //   978: aload_0
    //   979: iload_3
    //   980: invokespecial 445	net/geekstools/floatshort/PRO/App_Unlimited_HIS$3:<init>	(Lnet/geekstools/floatshort/PRO/App_Unlimited_HIS;I)V
    //   983: invokevirtual 449	android/view/ViewGroup:setOnClickListener	(Landroid/view/View$OnClickListener;)V
    //   986: aload_0
    //   987: getfield 171	net/geekstools/floatshort/PRO/App_Unlimited_HIS:notificationDot	[Lnet/geekstools/imageview/customshapes/ShapesImage;
    //   990: iload_3
    //   991: aaload
    //   992: new 20	net/geekstools/floatshort/PRO/App_Unlimited_HIS$4
    //   995: dup
    //   996: aload_0
    //   997: iload_3
    //   998: invokespecial 450	net/geekstools/floatshort/PRO/App_Unlimited_HIS$4:<init>	(Lnet/geekstools/floatshort/PRO/App_Unlimited_HIS;I)V
    //   1001: invokevirtual 451	net/geekstools/imageview/customshapes/ShapesImage:setOnClickListener	(Landroid/view/View$OnClickListener;)V
    //   1004: aload_0
    //   1005: getfield 171	net/geekstools/floatshort/PRO/App_Unlimited_HIS:notificationDot	[Lnet/geekstools/imageview/customshapes/ShapesImage;
    //   1008: iload_3
    //   1009: aaload
    //   1010: new 22	net/geekstools/floatshort/PRO/App_Unlimited_HIS$5
    //   1013: dup
    //   1014: aload_0
    //   1015: iload_3
    //   1016: invokespecial 452	net/geekstools/floatshort/PRO/App_Unlimited_HIS$5:<init>	(Lnet/geekstools/floatshort/PRO/App_Unlimited_HIS;I)V
    //   1019: invokevirtual 456	net/geekstools/imageview/customshapes/ShapesImage:setOnLongClickListener	(Landroid/view/View$OnLongClickListener;)V
    //   1022: ldc 2
    //   1024: invokevirtual 219	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   1027: astore_1
    //   1028: new 458	android/content/IntentFilter
    //   1031: dup
    //   1032: invokespecial 459	android/content/IntentFilter:<init>	()V
    //   1035: astore 5
    //   1037: new 206	java/lang/StringBuilder
    //   1040: dup
    //   1041: invokespecial 207	java/lang/StringBuilder:<init>	()V
    //   1044: astore 6
    //   1046: aload 6
    //   1048: ldc_w 461
    //   1051: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1054: pop
    //   1055: aload 6
    //   1057: aload_1
    //   1058: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1061: pop
    //   1062: aload 5
    //   1064: aload 6
    //   1066: invokevirtual 231	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1069: invokevirtual 464	android/content/IntentFilter:addAction	(Ljava/lang/String;)V
    //   1072: new 206	java/lang/StringBuilder
    //   1075: dup
    //   1076: invokespecial 207	java/lang/StringBuilder:<init>	()V
    //   1079: astore 6
    //   1081: aload 6
    //   1083: ldc_w 466
    //   1086: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1089: pop
    //   1090: aload 6
    //   1092: aload_1
    //   1093: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1096: pop
    //   1097: aload 5
    //   1099: aload 6
    //   1101: invokevirtual 231	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1104: invokevirtual 464	android/content/IntentFilter:addAction	(Ljava/lang/String;)V
    //   1107: new 206	java/lang/StringBuilder
    //   1110: dup
    //   1111: invokespecial 207	java/lang/StringBuilder:<init>	()V
    //   1114: astore 6
    //   1116: aload 6
    //   1118: ldc_w 468
    //   1121: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1124: pop
    //   1125: aload 6
    //   1127: aload_1
    //   1128: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1131: pop
    //   1132: aload 5
    //   1134: aload 6
    //   1136: invokevirtual 231	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1139: invokevirtual 464	android/content/IntentFilter:addAction	(Ljava/lang/String;)V
    //   1142: new 206	java/lang/StringBuilder
    //   1145: dup
    //   1146: invokespecial 207	java/lang/StringBuilder:<init>	()V
    //   1149: astore 6
    //   1151: aload 6
    //   1153: ldc_w 470
    //   1156: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1159: pop
    //   1160: aload 6
    //   1162: aload_1
    //   1163: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1166: pop
    //   1167: aload 5
    //   1169: aload 6
    //   1171: invokevirtual 231	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1174: invokevirtual 464	android/content/IntentFilter:addAction	(Ljava/lang/String;)V
    //   1177: new 206	java/lang/StringBuilder
    //   1180: dup
    //   1181: invokespecial 207	java/lang/StringBuilder:<init>	()V
    //   1184: astore 6
    //   1186: aload 6
    //   1188: ldc_w 472
    //   1191: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1194: pop
    //   1195: aload 6
    //   1197: aload_1
    //   1198: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1201: pop
    //   1202: aload 5
    //   1204: aload 6
    //   1206: invokevirtual 231	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1209: invokevirtual 464	android/content/IntentFilter:addAction	(Ljava/lang/String;)V
    //   1212: new 206	java/lang/StringBuilder
    //   1215: dup
    //   1216: invokespecial 207	java/lang/StringBuilder:<init>	()V
    //   1219: astore 6
    //   1221: aload 6
    //   1223: ldc_w 474
    //   1226: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1229: pop
    //   1230: aload 6
    //   1232: aload_1
    //   1233: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1236: pop
    //   1237: aload 5
    //   1239: aload 6
    //   1241: invokevirtual 231	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1244: invokevirtual 464	android/content/IntentFilter:addAction	(Ljava/lang/String;)V
    //   1247: aload 5
    //   1249: ldc_w 476
    //   1252: invokevirtual 464	android/content/IntentFilter:addAction	(Ljava/lang/String;)V
    //   1255: aload 5
    //   1257: ldc_w 478
    //   1260: invokevirtual 464	android/content/IntentFilter:addAction	(Ljava/lang/String;)V
    //   1263: aload 5
    //   1265: ldc_w 480
    //   1268: invokevirtual 464	android/content/IntentFilter:addAction	(Ljava/lang/String;)V
    //   1271: aload 5
    //   1273: ldc_w 482
    //   1276: invokevirtual 464	android/content/IntentFilter:addAction	(Ljava/lang/String;)V
    //   1279: aload_0
    //   1280: new 24	net/geekstools/floatshort/PRO/App_Unlimited_HIS$6
    //   1283: dup
    //   1284: aload_0
    //   1285: aload_1
    //   1286: iload_3
    //   1287: invokespecial 485	net/geekstools/floatshort/PRO/App_Unlimited_HIS$6:<init>	(Lnet/geekstools/floatshort/PRO/App_Unlimited_HIS;Ljava/lang/String;I)V
    //   1290: putfield 332	net/geekstools/floatshort/PRO/App_Unlimited_HIS:broadcastReceiver	Landroid/content/BroadcastReceiver;
    //   1293: aload_0
    //   1294: aload_0
    //   1295: getfield 332	net/geekstools/floatshort/PRO/App_Unlimited_HIS:broadcastReceiver	Landroid/content/BroadcastReceiver;
    //   1298: aload 5
    //   1300: invokevirtual 489	net/geekstools/floatshort/PRO/App_Unlimited_HIS:registerReceiver	(Landroid/content/BroadcastReceiver;Landroid/content/IntentFilter;)Landroid/content/Intent;
    //   1303: pop
    //   1304: new 206	java/lang/StringBuilder
    //   1307: dup
    //   1308: invokespecial 207	java/lang/StringBuilder:<init>	()V
    //   1311: astore_1
    //   1312: aload_1
    //   1313: aload_0
    //   1314: getfield 151	net/geekstools/floatshort/PRO/App_Unlimited_HIS:packageName	[Ljava/lang/String;
    //   1317: iload_3
    //   1318: aaload
    //   1319: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1322: pop
    //   1323: aload_1
    //   1324: ldc_w 491
    //   1327: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1330: pop
    //   1331: aload_0
    //   1332: aload_1
    //   1333: invokevirtual 231	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1336: invokevirtual 495	net/geekstools/floatshort/PRO/App_Unlimited_HIS:getFileStreamPath	(Ljava/lang/String;)Ljava/io/File;
    //   1339: invokevirtual 500	java/io/File:exists	()Z
    //   1342: ifeq +29 -> 1371
    //   1345: aload_0
    //   1346: new 254	android/content/Intent
    //   1349: dup
    //   1350: ldc_w 480
    //   1353: invokespecial 502	android/content/Intent:<init>	(Ljava/lang/String;)V
    //   1356: ldc_w 504
    //   1359: aload_0
    //   1360: getfield 151	net/geekstools/floatshort/PRO/App_Unlimited_HIS:packageName	[Ljava/lang/String;
    //   1363: iload_3
    //   1364: aaload
    //   1365: invokevirtual 508	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   1368: invokevirtual 512	net/geekstools/floatshort/PRO/App_Unlimited_HIS:sendBroadcast	(Landroid/content/Intent;)V
    //   1371: aload_0
    //   1372: getfield 121	net/geekstools/floatshort/PRO/App_Unlimited_HIS:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
    //   1375: invokevirtual 515	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:serviceMode	()I
    //   1378: ireturn
    //   1379: astore_1
    //   1380: aload_1
    //   1381: invokevirtual 330	java/lang/Exception:printStackTrace	()V
    //   1384: iconst_2
    //   1385: ireturn
    //   1386: ldc_w 516
    //   1389: istore_2
    //   1390: goto -1119 -> 271
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1393	0	this	App_Unlimited_HIS
    //   0	1393	1	paramIntent	Intent
    //   0	1393	2	paramInt1	int
    //   0	1393	3	paramInt2	int
    //   345	3	4	bool	boolean
    //   3	1296	5	localObject1	Object
    //   12	1228	6	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   352	367	424	finally
    //   429	433	424	finally
    //   352	367	428	java/lang/Exception
    //   329	347	585	java/lang/Exception
    //   367	415	585	java/lang/Exception
    //   415	421	585	java/lang/Exception
    //   433	481	585	java/lang/Exception
    //   484	536	585	java/lang/Exception
    //   536	538	585	java/lang/Exception
    //   538	582	585	java/lang/Exception
    //   597	612	615	java/lang/Exception
    //   744	759	762	java/lang/Exception
    //   76	264	1379	java/lang/Exception
    //   271	303	1379	java/lang/Exception
  }
}
