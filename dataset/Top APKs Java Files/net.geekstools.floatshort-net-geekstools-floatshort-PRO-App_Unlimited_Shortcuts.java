package net.geekstools.floatshort.PRO;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
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

public class App_Unlimited_Shortcuts
  extends Service
{
  boolean[] StickyEdge;
  WindowManager.LayoutParams[] StickyEdgeParams;
  boolean[] allowMove;
  Drawable[] appIcon;
  int array;
  BroadcastReceiver broadcastReceiver;
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
  String[] packages;
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
  
  public App_Unlimited_Shortcuts() {}
  
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
    this.packages = new String[this.array];
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
    this.mapPackageNameStartId = new LinkedHashMap();
  }
  
  /* Error */
  public int onStartCommand(final Intent paramIntent, int paramInt1, final int paramInt2)
  {
    // Byte code:
    //   0: getstatic 189	java/lang/System:out	Ljava/io/PrintStream;
    //   3: astore 5
    //   5: new 191	java/lang/StringBuilder
    //   8: dup
    //   9: invokespecial 192	java/lang/StringBuilder:<init>	()V
    //   12: astore 6
    //   14: aload 6
    //   16: aload_0
    //   17: invokevirtual 198	java/lang/Object:getClass	()Ljava/lang/Class;
    //   20: invokevirtual 204	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   23: invokevirtual 208	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   26: pop
    //   27: aload 6
    //   29: ldc -46
    //   31: invokevirtual 208	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   34: pop
    //   35: aload 6
    //   37: iload_3
    //   38: invokevirtual 213	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   41: pop
    //   42: aload 5
    //   44: aload 6
    //   46: invokevirtual 216	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   49: invokevirtual 222	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   52: aload_0
    //   53: aload_0
    //   54: ldc -32
    //   56: invokevirtual 228	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   59: checkcast 230	android/view/WindowManager
    //   62: putfield 232	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:windowManager	Landroid/view/WindowManager;
    //   65: aload_0
    //   66: ldc -22
    //   68: invokevirtual 228	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   71: checkcast 236	android/view/LayoutInflater
    //   74: astore 5
    //   76: aload_0
    //   77: getfield 166	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:allowMove	[Z
    //   80: iload_3
    //   81: iconst_1
    //   82: bastore
    //   83: aload_0
    //   84: getfield 146	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:packages	[Ljava/lang/String;
    //   87: iload_3
    //   88: aload_1
    //   89: ldc -18
    //   91: invokevirtual 244	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   94: aastore
    //   95: aload_0
    //   96: getfield 156	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:floatingView	[Landroid/view/ViewGroup;
    //   99: iload_3
    //   100: aload 5
    //   102: ldc -11
    //   104: aconst_null
    //   105: iconst_0
    //   106: invokevirtual 249	android/view/LayoutInflater:inflate	(ILandroid/view/ViewGroup;Z)Landroid/view/View;
    //   109: checkcast 154	android/view/ViewGroup
    //   112: aastore
    //   113: aload_0
    //   114: getfield 160	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:controlIcon	[Lnet/geekstools/imageview/customshapes/ShapesImage;
    //   117: iload_3
    //   118: aload_0
    //   119: getfield 116	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
    //   122: aload_0
    //   123: getfield 156	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:floatingView	[Landroid/view/ViewGroup;
    //   126: iload_3
    //   127: aaload
    //   128: ldc -6
    //   130: invokevirtual 254	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:initShapesImage	(Landroid/view/ViewGroup;I)Lnet/geekstools/imageview/customshapes/ShapesImage;
    //   133: aastore
    //   134: aload_0
    //   135: getfield 162	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:shapedIcon	[Lnet/geekstools/imageview/customshapes/ShapesImage;
    //   138: iload_3
    //   139: aload_0
    //   140: getfield 116	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
    //   143: aload_0
    //   144: getfield 156	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:floatingView	[Landroid/view/ViewGroup;
    //   147: iload_3
    //   148: aaload
    //   149: ldc -1
    //   151: invokevirtual 254	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:initShapesImage	(Landroid/view/ViewGroup;I)Lnet/geekstools/imageview/customshapes/ShapesImage;
    //   154: aastore
    //   155: aload_0
    //   156: getfield 164	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:notificationDot	[Lnet/geekstools/imageview/customshapes/ShapesImage;
    //   159: astore_1
    //   160: aload_0
    //   161: getfield 116	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
    //   164: astore 5
    //   166: aload_0
    //   167: getfield 156	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:floatingView	[Landroid/view/ViewGroup;
    //   170: iload_3
    //   171: aaload
    //   172: astore 6
    //   174: aload_0
    //   175: getfield 116	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
    //   178: invokevirtual 259	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:checkStickyEdge	()Z
    //   181: ifeq +1105 -> 1286
    //   184: ldc_w 260
    //   187: istore_2
    //   188: goto +3 -> 191
    //   191: aload_1
    //   192: iload_3
    //   193: aload 5
    //   195: aload 6
    //   197: iload_2
    //   198: invokevirtual 254	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:initShapesImage	(Landroid/view/ViewGroup;I)Lnet/geekstools/imageview/customshapes/ShapesImage;
    //   201: aastore
    //   202: aload_0
    //   203: getfield 170	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:touchingDelay	[Z
    //   206: iload_3
    //   207: iconst_0
    //   208: bastore
    //   209: aload_0
    //   210: getfield 172	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:StickyEdge	[Z
    //   213: iload_3
    //   214: iconst_0
    //   215: bastore
    //   216: aload_0
    //   217: getfield 174	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:openIt	[Z
    //   220: iload_3
    //   221: iconst_1
    //   222: bastore
    //   223: aload_0
    //   224: getfield 146	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:packages	[Ljava/lang/String;
    //   227: iload_3
    //   228: aaload
    //   229: aload_0
    //   230: ldc_w 261
    //   233: invokevirtual 265	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:getString	(I)Ljava/lang/String;
    //   236: invokevirtual 269	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   239: ifeq +317 -> 556
    //   242: iconst_1
    //   243: istore_2
    //   244: iload_2
    //   245: iload_3
    //   246: if_icmpge +271 -> 517
    //   249: aload_0
    //   250: getfield 156	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:floatingView	[Landroid/view/ViewGroup;
    //   253: ifnull +257 -> 510
    //   256: aload_0
    //   257: getfield 156	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:floatingView	[Landroid/view/ViewGroup;
    //   260: iload_2
    //   261: aaload
    //   262: invokevirtual 272	android/view/ViewGroup:isShown	()Z
    //   265: istore 4
    //   267: iload 4
    //   269: ifeq +189 -> 458
    //   272: aload_0
    //   273: getfield 232	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:windowManager	Landroid/view/WindowManager;
    //   276: aload_0
    //   277: getfield 156	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:floatingView	[Landroid/view/ViewGroup;
    //   280: iload_2
    //   281: aaload
    //   282: invokeinterface 276 2 0
    //   287: getstatic 281	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:floatingCounter	I
    //   290: iconst_1
    //   291: isub
    //   292: putstatic 281	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:floatingCounter	I
    //   295: getstatic 281	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:floatingCounter	I
    //   298: ifne +212 -> 510
    //   301: aload_0
    //   302: invokevirtual 111	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:getApplicationContext	()Landroid/content/Context;
    //   305: invokestatic 287	android/preference/PreferenceManager:getDefaultSharedPreferences	(Landroid/content/Context;)Landroid/content/SharedPreferences;
    //   308: ldc_w 289
    //   311: iconst_1
    //   312: invokeinterface 295 3 0
    //   317: ifne +193 -> 510
    //   320: new 240	android/content/Intent
    //   323: dup
    //   324: aload_0
    //   325: invokevirtual 111	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:getApplicationContext	()Landroid/content/Context;
    //   328: ldc_w 297
    //   331: invokespecial 300	android/content/Intent:<init>	(Landroid/content/Context;Ljava/lang/Class;)V
    //   334: astore_1
    //   335: aload_0
    //   336: aload_1
    //   337: invokevirtual 304	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:stopService	(Landroid/content/Intent;)Z
    //   340: pop
    //   341: goto +169 -> 510
    //   344: astore_1
    //   345: goto +59 -> 404
    //   348: astore_1
    //   349: aload_1
    //   350: invokevirtual 307	java/lang/Exception:printStackTrace	()V
    //   353: getstatic 281	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:floatingCounter	I
    //   356: iconst_1
    //   357: isub
    //   358: putstatic 281	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:floatingCounter	I
    //   361: getstatic 281	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:floatingCounter	I
    //   364: ifne +146 -> 510
    //   367: aload_0
    //   368: invokevirtual 111	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:getApplicationContext	()Landroid/content/Context;
    //   371: invokestatic 287	android/preference/PreferenceManager:getDefaultSharedPreferences	(Landroid/content/Context;)Landroid/content/SharedPreferences;
    //   374: ldc_w 289
    //   377: iconst_1
    //   378: invokeinterface 295 3 0
    //   383: ifne +127 -> 510
    //   386: new 240	android/content/Intent
    //   389: dup
    //   390: aload_0
    //   391: invokevirtual 111	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:getApplicationContext	()Landroid/content/Context;
    //   394: ldc_w 297
    //   397: invokespecial 300	android/content/Intent:<init>	(Landroid/content/Context;Ljava/lang/Class;)V
    //   400: astore_1
    //   401: goto -66 -> 335
    //   404: getstatic 281	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:floatingCounter	I
    //   407: iconst_1
    //   408: isub
    //   409: putstatic 281	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:floatingCounter	I
    //   412: getstatic 281	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:floatingCounter	I
    //   415: ifne +41 -> 456
    //   418: aload_0
    //   419: invokevirtual 111	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:getApplicationContext	()Landroid/content/Context;
    //   422: invokestatic 287	android/preference/PreferenceManager:getDefaultSharedPreferences	(Landroid/content/Context;)Landroid/content/SharedPreferences;
    //   425: ldc_w 289
    //   428: iconst_1
    //   429: invokeinterface 295 3 0
    //   434: ifne +22 -> 456
    //   437: aload_0
    //   438: new 240	android/content/Intent
    //   441: dup
    //   442: aload_0
    //   443: invokevirtual 111	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:getApplicationContext	()Landroid/content/Context;
    //   446: ldc_w 297
    //   449: invokespecial 300	android/content/Intent:<init>	(Landroid/content/Context;Ljava/lang/Class;)V
    //   452: invokevirtual 304	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:stopService	(Landroid/content/Intent;)Z
    //   455: pop
    //   456: aload_1
    //   457: athrow
    //   458: getstatic 281	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:floatingCounter	I
    //   461: ifne +49 -> 510
    //   464: aload_0
    //   465: invokevirtual 111	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:getApplicationContext	()Landroid/content/Context;
    //   468: invokestatic 287	android/preference/PreferenceManager:getDefaultSharedPreferences	(Landroid/content/Context;)Landroid/content/SharedPreferences;
    //   471: ldc_w 289
    //   474: iconst_1
    //   475: invokeinterface 295 3 0
    //   480: ifne +30 -> 510
    //   483: aload_0
    //   484: new 240	android/content/Intent
    //   487: dup
    //   488: aload_0
    //   489: invokevirtual 111	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:getApplicationContext	()Landroid/content/Context;
    //   492: ldc_w 297
    //   495: invokespecial 300	android/content/Intent:<init>	(Landroid/content/Context;Ljava/lang/Class;)V
    //   498: invokevirtual 304	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:stopService	(Landroid/content/Intent;)Z
    //   501: pop
    //   502: goto +8 -> 510
    //   505: astore_1
    //   506: aload_1
    //   507: invokevirtual 307	java/lang/Exception:printStackTrace	()V
    //   510: iload_2
    //   511: iconst_1
    //   512: iadd
    //   513: istore_2
    //   514: goto -270 -> 244
    //   517: getstatic 311	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:FloatingShortcuts	Ljava/util/ArrayList;
    //   520: invokevirtual 316	java/util/ArrayList:clear	()V
    //   523: iconst_m1
    //   524: putstatic 319	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:shortcutsCounter	I
    //   527: aload_0
    //   528: getfield 321	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:broadcastReceiver	Landroid/content/BroadcastReceiver;
    //   531: ifnull +19 -> 550
    //   534: aload_0
    //   535: aload_0
    //   536: getfield 321	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:broadcastReceiver	Landroid/content/BroadcastReceiver;
    //   539: invokevirtual 325	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:unregisterReceiver	(Landroid/content/BroadcastReceiver;)V
    //   542: goto +8 -> 550
    //   545: astore_1
    //   546: aload_1
    //   547: invokevirtual 307	java/lang/Exception:printStackTrace	()V
    //   550: aload_0
    //   551: invokevirtual 328	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:stopSelf	()V
    //   554: iconst_2
    //   555: ireturn
    //   556: aload_0
    //   557: getfield 179	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:mapPackageNameStartId	Ljava/util/Map;
    //   560: aload_0
    //   561: getfield 146	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:packages	[Ljava/lang/String;
    //   564: iload_3
    //   565: aaload
    //   566: iload_3
    //   567: invokestatic 334	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   570: invokeinterface 340 3 0
    //   575: pop
    //   576: aload_0
    //   577: getfield 116	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
    //   580: aload_0
    //   581: getfield 146	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:packages	[Ljava/lang/String;
    //   584: iload_3
    //   585: aaload
    //   586: invokevirtual 344	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:appInstalledOrNot	(Ljava/lang/String;)Z
    //   589: ifne +5 -> 594
    //   592: iconst_2
    //   593: ireturn
    //   594: aload_0
    //   595: getfield 116	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
    //   598: aload_0
    //   599: getfield 146	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:packages	[Ljava/lang/String;
    //   602: iload_3
    //   603: aaload
    //   604: invokevirtual 347	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:saveUnlimitedShortcutsService	(Ljava/lang/String;)V
    //   607: aload_0
    //   608: getfield 116	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
    //   611: invokevirtual 350	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:updateRecoverShortcuts	()V
    //   614: aload_0
    //   615: getfield 150	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:appIcon	[Landroid/graphics/drawable/Drawable;
    //   618: iload_3
    //   619: aload_0
    //   620: getfield 116	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
    //   623: aload_0
    //   624: getfield 146	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:packages	[Ljava/lang/String;
    //   627: iload_3
    //   628: aaload
    //   629: invokevirtual 354	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:shapedAppIcon	(Ljava/lang/String;)Landroid/graphics/drawable/Drawable;
    //   632: aastore
    //   633: aload_0
    //   634: getfield 152	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:iconColor	[I
    //   637: iload_3
    //   638: aload_0
    //   639: getfield 116	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
    //   642: aload_0
    //   643: getfield 116	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
    //   646: aload_0
    //   647: getfield 146	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:packages	[Ljava/lang/String;
    //   650: iload_3
    //   651: aaload
    //   652: invokevirtual 356	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:appIcon	(Ljava/lang/String;)Landroid/graphics/drawable/Drawable;
    //   655: invokevirtual 360	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:extractDominantColor	(Landroid/graphics/drawable/Drawable;)I
    //   658: iastore
    //   659: aload_0
    //   660: getfield 162	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:shapedIcon	[Lnet/geekstools/imageview/customshapes/ShapesImage;
    //   663: iload_3
    //   664: aaload
    //   665: aload_0
    //   666: getfield 116	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
    //   669: aload_0
    //   670: getfield 146	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:packages	[Ljava/lang/String;
    //   673: iload_3
    //   674: aaload
    //   675: invokevirtual 354	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:shapedAppIcon	(Ljava/lang/String;)Landroid/graphics/drawable/Drawable;
    //   678: invokevirtual 364	net/geekstools/imageview/customshapes/ShapesImage:setImageDrawable	(Landroid/graphics/drawable/Drawable;)V
    //   681: aload_0
    //   682: aload_0
    //   683: aload_0
    //   684: getfield 146	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:packages	[Ljava/lang/String;
    //   687: iload_3
    //   688: aaload
    //   689: iconst_0
    //   690: invokevirtual 368	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   693: putfield 370	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:sharedPrefPosition	Landroid/content/SharedPreferences;
    //   696: goto +8 -> 704
    //   699: astore_1
    //   700: aload_1
    //   701: invokevirtual 307	java/lang/Exception:printStackTrace	()V
    //   704: aload_0
    //   705: aload_0
    //   706: getfield 82	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:xInit	I
    //   709: bipush 13
    //   711: iadd
    //   712: putfield 82	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:xInit	I
    //   715: aload_0
    //   716: aload_0
    //   717: getfield 84	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:yInit	I
    //   720: bipush 13
    //   722: iadd
    //   723: putfield 84	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:yInit	I
    //   726: aload_0
    //   727: aload_0
    //   728: getfield 370	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:sharedPrefPosition	Landroid/content/SharedPreferences;
    //   731: ldc_w 372
    //   734: aload_0
    //   735: getfield 82	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:xInit	I
    //   738: invokeinterface 376 3 0
    //   743: putfield 378	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:xPos	I
    //   746: aload_0
    //   747: aload_0
    //   748: getfield 370	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:sharedPrefPosition	Landroid/content/SharedPreferences;
    //   751: ldc_w 380
    //   754: aload_0
    //   755: getfield 84	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:yInit	I
    //   758: invokeinterface 376 3 0
    //   763: putfield 382	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:yPos	I
    //   766: aload_0
    //   767: getfield 140	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:params	[Landroid/view/WindowManager$LayoutParams;
    //   770: iload_3
    //   771: aload_0
    //   772: getfield 116	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
    //   775: getstatic 385	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:HW	I
    //   778: aload_0
    //   779: getfield 378	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:xPos	I
    //   782: aload_0
    //   783: getfield 382	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:yPos	I
    //   786: invokevirtual 389	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:normalLayoutParams	(III)Landroid/view/WindowManager$LayoutParams;
    //   789: aastore
    //   790: aload_0
    //   791: getfield 232	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:windowManager	Landroid/view/WindowManager;
    //   794: aload_0
    //   795: getfield 156	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:floatingView	[Landroid/view/ViewGroup;
    //   798: iload_3
    //   799: aaload
    //   800: aload_0
    //   801: getfield 140	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:params	[Landroid/view/WindowManager$LayoutParams;
    //   804: iload_3
    //   805: aaload
    //   806: invokeinterface 393 3 0
    //   811: aload_0
    //   812: getfield 162	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:shapedIcon	[Lnet/geekstools/imageview/customshapes/ShapesImage;
    //   815: iload_3
    //   816: aaload
    //   817: aload_0
    //   818: getfield 116	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
    //   821: ldc_w 395
    //   824: sipush 255
    //   827: invokevirtual 398	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:readDefaultPreference	(Ljava/lang/String;I)I
    //   830: invokevirtual 402	net/geekstools/imageview/customshapes/ShapesImage:setImageAlpha	(I)V
    //   833: aload_0
    //   834: getfield 156	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:floatingView	[Landroid/view/ViewGroup;
    //   837: iload_3
    //   838: aaload
    //   839: new 6	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts$1
    //   842: dup
    //   843: aload_0
    //   844: invokespecial 405	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts$1:<init>	(Lnet/geekstools/floatshort/PRO/App_Unlimited_Shortcuts;)V
    //   847: invokevirtual 409	android/view/ViewGroup:setOnFocusChangeListener	(Landroid/view/View$OnFocusChangeListener;)V
    //   850: aload_0
    //   851: getfield 156	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:floatingView	[Landroid/view/ViewGroup;
    //   854: iload_3
    //   855: aaload
    //   856: new 8	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts$2
    //   859: dup
    //   860: aload_0
    //   861: iload_3
    //   862: invokespecial 412	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts$2:<init>	(Lnet/geekstools/floatshort/PRO/App_Unlimited_Shortcuts;I)V
    //   865: invokevirtual 416	android/view/ViewGroup:setOnTouchListener	(Landroid/view/View$OnTouchListener;)V
    //   868: aload_0
    //   869: getfield 156	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:floatingView	[Landroid/view/ViewGroup;
    //   872: iload_3
    //   873: aaload
    //   874: new 18	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts$3
    //   877: dup
    //   878: aload_0
    //   879: iload_3
    //   880: invokespecial 417	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts$3:<init>	(Lnet/geekstools/floatshort/PRO/App_Unlimited_Shortcuts;I)V
    //   883: invokevirtual 421	android/view/ViewGroup:setOnClickListener	(Landroid/view/View$OnClickListener;)V
    //   886: aload_0
    //   887: getfield 164	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:notificationDot	[Lnet/geekstools/imageview/customshapes/ShapesImage;
    //   890: iload_3
    //   891: aaload
    //   892: new 20	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts$4
    //   895: dup
    //   896: aload_0
    //   897: iload_3
    //   898: invokespecial 422	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts$4:<init>	(Lnet/geekstools/floatshort/PRO/App_Unlimited_Shortcuts;I)V
    //   901: invokevirtual 423	net/geekstools/imageview/customshapes/ShapesImage:setOnClickListener	(Landroid/view/View$OnClickListener;)V
    //   904: aload_0
    //   905: getfield 164	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:notificationDot	[Lnet/geekstools/imageview/customshapes/ShapesImage;
    //   908: iload_3
    //   909: aaload
    //   910: new 22	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts$5
    //   913: dup
    //   914: aload_0
    //   915: iload_3
    //   916: invokespecial 424	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts$5:<init>	(Lnet/geekstools/floatshort/PRO/App_Unlimited_Shortcuts;I)V
    //   919: invokevirtual 428	net/geekstools/imageview/customshapes/ShapesImage:setOnLongClickListener	(Landroid/view/View$OnLongClickListener;)V
    //   922: ldc 2
    //   924: invokevirtual 204	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   927: astore_1
    //   928: new 430	android/content/IntentFilter
    //   931: dup
    //   932: invokespecial 431	android/content/IntentFilter:<init>	()V
    //   935: astore 5
    //   937: new 191	java/lang/StringBuilder
    //   940: dup
    //   941: invokespecial 192	java/lang/StringBuilder:<init>	()V
    //   944: astore 6
    //   946: aload 6
    //   948: ldc_w 433
    //   951: invokevirtual 208	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   954: pop
    //   955: aload 6
    //   957: aload_1
    //   958: invokevirtual 208	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   961: pop
    //   962: aload 5
    //   964: aload 6
    //   966: invokevirtual 216	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   969: invokevirtual 436	android/content/IntentFilter:addAction	(Ljava/lang/String;)V
    //   972: new 191	java/lang/StringBuilder
    //   975: dup
    //   976: invokespecial 192	java/lang/StringBuilder:<init>	()V
    //   979: astore 6
    //   981: aload 6
    //   983: ldc_w 438
    //   986: invokevirtual 208	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   989: pop
    //   990: aload 6
    //   992: aload_1
    //   993: invokevirtual 208	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   996: pop
    //   997: aload 5
    //   999: aload 6
    //   1001: invokevirtual 216	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1004: invokevirtual 436	android/content/IntentFilter:addAction	(Ljava/lang/String;)V
    //   1007: new 191	java/lang/StringBuilder
    //   1010: dup
    //   1011: invokespecial 192	java/lang/StringBuilder:<init>	()V
    //   1014: astore 6
    //   1016: aload 6
    //   1018: ldc_w 440
    //   1021: invokevirtual 208	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1024: pop
    //   1025: aload 6
    //   1027: aload_1
    //   1028: invokevirtual 208	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1031: pop
    //   1032: aload 5
    //   1034: aload 6
    //   1036: invokevirtual 216	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1039: invokevirtual 436	android/content/IntentFilter:addAction	(Ljava/lang/String;)V
    //   1042: new 191	java/lang/StringBuilder
    //   1045: dup
    //   1046: invokespecial 192	java/lang/StringBuilder:<init>	()V
    //   1049: astore 6
    //   1051: aload 6
    //   1053: ldc_w 442
    //   1056: invokevirtual 208	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1059: pop
    //   1060: aload 6
    //   1062: aload_1
    //   1063: invokevirtual 208	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1066: pop
    //   1067: aload 5
    //   1069: aload 6
    //   1071: invokevirtual 216	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1074: invokevirtual 436	android/content/IntentFilter:addAction	(Ljava/lang/String;)V
    //   1077: new 191	java/lang/StringBuilder
    //   1080: dup
    //   1081: invokespecial 192	java/lang/StringBuilder:<init>	()V
    //   1084: astore 6
    //   1086: aload 6
    //   1088: ldc_w 444
    //   1091: invokevirtual 208	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1094: pop
    //   1095: aload 6
    //   1097: aload_1
    //   1098: invokevirtual 208	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1101: pop
    //   1102: aload 5
    //   1104: aload 6
    //   1106: invokevirtual 216	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1109: invokevirtual 436	android/content/IntentFilter:addAction	(Ljava/lang/String;)V
    //   1112: new 191	java/lang/StringBuilder
    //   1115: dup
    //   1116: invokespecial 192	java/lang/StringBuilder:<init>	()V
    //   1119: astore 6
    //   1121: aload 6
    //   1123: ldc_w 446
    //   1126: invokevirtual 208	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1129: pop
    //   1130: aload 6
    //   1132: aload_1
    //   1133: invokevirtual 208	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1136: pop
    //   1137: aload 5
    //   1139: aload 6
    //   1141: invokevirtual 216	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1144: invokevirtual 436	android/content/IntentFilter:addAction	(Ljava/lang/String;)V
    //   1147: aload 5
    //   1149: ldc_w 448
    //   1152: invokevirtual 436	android/content/IntentFilter:addAction	(Ljava/lang/String;)V
    //   1155: aload 5
    //   1157: ldc_w 450
    //   1160: invokevirtual 436	android/content/IntentFilter:addAction	(Ljava/lang/String;)V
    //   1163: aload 5
    //   1165: ldc_w 452
    //   1168: invokevirtual 436	android/content/IntentFilter:addAction	(Ljava/lang/String;)V
    //   1171: aload 5
    //   1173: ldc_w 454
    //   1176: invokevirtual 436	android/content/IntentFilter:addAction	(Ljava/lang/String;)V
    //   1179: aload_0
    //   1180: new 24	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts$6
    //   1183: dup
    //   1184: aload_0
    //   1185: aload_1
    //   1186: iload_3
    //   1187: invokespecial 457	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts$6:<init>	(Lnet/geekstools/floatshort/PRO/App_Unlimited_Shortcuts;Ljava/lang/String;I)V
    //   1190: putfield 321	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:broadcastReceiver	Landroid/content/BroadcastReceiver;
    //   1193: aload_0
    //   1194: aload_0
    //   1195: getfield 321	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:broadcastReceiver	Landroid/content/BroadcastReceiver;
    //   1198: aload 5
    //   1200: invokevirtual 461	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:registerReceiver	(Landroid/content/BroadcastReceiver;Landroid/content/IntentFilter;)Landroid/content/Intent;
    //   1203: pop
    //   1204: new 191	java/lang/StringBuilder
    //   1207: dup
    //   1208: invokespecial 192	java/lang/StringBuilder:<init>	()V
    //   1211: astore_1
    //   1212: aload_1
    //   1213: aload_0
    //   1214: getfield 146	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:packages	[Ljava/lang/String;
    //   1217: iload_3
    //   1218: aaload
    //   1219: invokevirtual 208	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1222: pop
    //   1223: aload_1
    //   1224: ldc_w 463
    //   1227: invokevirtual 208	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1230: pop
    //   1231: aload_0
    //   1232: aload_1
    //   1233: invokevirtual 216	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1236: invokevirtual 467	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:getFileStreamPath	(Ljava/lang/String;)Ljava/io/File;
    //   1239: invokevirtual 472	java/io/File:exists	()Z
    //   1242: ifeq +29 -> 1271
    //   1245: aload_0
    //   1246: new 240	android/content/Intent
    //   1249: dup
    //   1250: ldc_w 452
    //   1253: invokespecial 474	android/content/Intent:<init>	(Ljava/lang/String;)V
    //   1256: ldc_w 476
    //   1259: aload_0
    //   1260: getfield 146	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:packages	[Ljava/lang/String;
    //   1263: iload_3
    //   1264: aaload
    //   1265: invokevirtual 480	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   1268: invokevirtual 484	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:sendBroadcast	(Landroid/content/Intent;)V
    //   1271: aload_0
    //   1272: getfield 116	net/geekstools/floatshort/PRO/App_Unlimited_Shortcuts:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
    //   1275: invokevirtual 487	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:serviceMode	()I
    //   1278: ireturn
    //   1279: astore_1
    //   1280: aload_1
    //   1281: invokevirtual 307	java/lang/Exception:printStackTrace	()V
    //   1284: iconst_2
    //   1285: ireturn
    //   1286: ldc_w 488
    //   1289: istore_2
    //   1290: goto -1099 -> 191
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1293	0	this	App_Unlimited_Shortcuts
    //   0	1293	1	paramIntent	Intent
    //   0	1293	2	paramInt1	int
    //   0	1293	3	paramInt2	int
    //   265	3	4	bool	boolean
    //   3	1196	5	localObject1	Object
    //   12	1128	6	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   272	287	344	finally
    //   349	353	344	finally
    //   272	287	348	java/lang/Exception
    //   249	267	505	java/lang/Exception
    //   287	335	505	java/lang/Exception
    //   335	341	505	java/lang/Exception
    //   353	401	505	java/lang/Exception
    //   404	456	505	java/lang/Exception
    //   456	458	505	java/lang/Exception
    //   458	502	505	java/lang/Exception
    //   527	542	545	java/lang/Exception
    //   681	696	699	java/lang/Exception
    //   76	184	1279	java/lang/Exception
    //   191	223	1279	java/lang/Exception
  }
}
