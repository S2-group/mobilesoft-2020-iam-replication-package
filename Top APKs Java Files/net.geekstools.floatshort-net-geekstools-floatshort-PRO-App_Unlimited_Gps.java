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

public class App_Unlimited_Gps
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
  
  public App_Unlimited_Gps() {}
  
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
    //   56: invokevirtual 228	net/geekstools/floatshort/PRO/App_Unlimited_Gps:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   59: checkcast 230	android/view/WindowManager
    //   62: putfield 232	net/geekstools/floatshort/PRO/App_Unlimited_Gps:windowManager	Landroid/view/WindowManager;
    //   65: aload_0
    //   66: ldc -22
    //   68: invokevirtual 228	net/geekstools/floatshort/PRO/App_Unlimited_Gps:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   71: checkcast 236	android/view/LayoutInflater
    //   74: astore 5
    //   76: aload_0
    //   77: getfield 166	net/geekstools/floatshort/PRO/App_Unlimited_Gps:allowMove	[Z
    //   80: iload_3
    //   81: iconst_1
    //   82: bastore
    //   83: aload_0
    //   84: getfield 146	net/geekstools/floatshort/PRO/App_Unlimited_Gps:packages	[Ljava/lang/String;
    //   87: iload_3
    //   88: aload_1
    //   89: ldc -18
    //   91: invokevirtual 244	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   94: aastore
    //   95: aload_0
    //   96: getfield 156	net/geekstools/floatshort/PRO/App_Unlimited_Gps:floatingView	[Landroid/view/ViewGroup;
    //   99: iload_3
    //   100: aload 5
    //   102: ldc -11
    //   104: aconst_null
    //   105: iconst_0
    //   106: invokevirtual 249	android/view/LayoutInflater:inflate	(ILandroid/view/ViewGroup;Z)Landroid/view/View;
    //   109: checkcast 154	android/view/ViewGroup
    //   112: aastore
    //   113: aload_0
    //   114: getfield 160	net/geekstools/floatshort/PRO/App_Unlimited_Gps:controlIcon	[Lnet/geekstools/imageview/customshapes/ShapesImage;
    //   117: iload_3
    //   118: aload_0
    //   119: getfield 116	net/geekstools/floatshort/PRO/App_Unlimited_Gps:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
    //   122: aload_0
    //   123: getfield 156	net/geekstools/floatshort/PRO/App_Unlimited_Gps:floatingView	[Landroid/view/ViewGroup;
    //   126: iload_3
    //   127: aaload
    //   128: ldc -6
    //   130: invokevirtual 254	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:initShapesImage	(Landroid/view/ViewGroup;I)Lnet/geekstools/imageview/customshapes/ShapesImage;
    //   133: aastore
    //   134: aload_0
    //   135: getfield 162	net/geekstools/floatshort/PRO/App_Unlimited_Gps:shapedIcon	[Lnet/geekstools/imageview/customshapes/ShapesImage;
    //   138: iload_3
    //   139: aload_0
    //   140: getfield 116	net/geekstools/floatshort/PRO/App_Unlimited_Gps:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
    //   143: aload_0
    //   144: getfield 156	net/geekstools/floatshort/PRO/App_Unlimited_Gps:floatingView	[Landroid/view/ViewGroup;
    //   147: iload_3
    //   148: aaload
    //   149: ldc -1
    //   151: invokevirtual 254	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:initShapesImage	(Landroid/view/ViewGroup;I)Lnet/geekstools/imageview/customshapes/ShapesImage;
    //   154: aastore
    //   155: aload_0
    //   156: getfield 164	net/geekstools/floatshort/PRO/App_Unlimited_Gps:notificationDot	[Lnet/geekstools/imageview/customshapes/ShapesImage;
    //   159: astore_1
    //   160: aload_0
    //   161: getfield 116	net/geekstools/floatshort/PRO/App_Unlimited_Gps:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
    //   164: astore 5
    //   166: aload_0
    //   167: getfield 156	net/geekstools/floatshort/PRO/App_Unlimited_Gps:floatingView	[Landroid/view/ViewGroup;
    //   170: iload_3
    //   171: aaload
    //   172: astore 6
    //   174: aload_0
    //   175: getfield 116	net/geekstools/floatshort/PRO/App_Unlimited_Gps:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
    //   178: invokevirtual 259	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:checkStickyEdge	()Z
    //   181: ifeq +1075 -> 1256
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
    //   203: getfield 170	net/geekstools/floatshort/PRO/App_Unlimited_Gps:touchingDelay	[Z
    //   206: iload_3
    //   207: iconst_0
    //   208: bastore
    //   209: aload_0
    //   210: getfield 172	net/geekstools/floatshort/PRO/App_Unlimited_Gps:StickyEdge	[Z
    //   213: iload_3
    //   214: iconst_0
    //   215: bastore
    //   216: aload_0
    //   217: getfield 174	net/geekstools/floatshort/PRO/App_Unlimited_Gps:openIt	[Z
    //   220: iload_3
    //   221: iconst_1
    //   222: bastore
    //   223: aload_0
    //   224: getfield 146	net/geekstools/floatshort/PRO/App_Unlimited_Gps:packages	[Ljava/lang/String;
    //   227: iload_3
    //   228: aaload
    //   229: aload_0
    //   230: ldc_w 261
    //   233: invokevirtual 265	net/geekstools/floatshort/PRO/App_Unlimited_Gps:getString	(I)Ljava/lang/String;
    //   236: invokevirtual 269	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   239: ifeq +307 -> 546
    //   242: iconst_1
    //   243: istore_2
    //   244: iload_2
    //   245: iload_3
    //   246: if_icmpge +271 -> 517
    //   249: aload_0
    //   250: getfield 156	net/geekstools/floatshort/PRO/App_Unlimited_Gps:floatingView	[Landroid/view/ViewGroup;
    //   253: ifnull +257 -> 510
    //   256: aload_0
    //   257: getfield 156	net/geekstools/floatshort/PRO/App_Unlimited_Gps:floatingView	[Landroid/view/ViewGroup;
    //   260: iload_2
    //   261: aaload
    //   262: invokevirtual 272	android/view/ViewGroup:isShown	()Z
    //   265: istore 4
    //   267: iload 4
    //   269: ifeq +189 -> 458
    //   272: aload_0
    //   273: getfield 232	net/geekstools/floatshort/PRO/App_Unlimited_Gps:windowManager	Landroid/view/WindowManager;
    //   276: aload_0
    //   277: getfield 156	net/geekstools/floatshort/PRO/App_Unlimited_Gps:floatingView	[Landroid/view/ViewGroup;
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
    //   302: invokevirtual 111	net/geekstools/floatshort/PRO/App_Unlimited_Gps:getApplicationContext	()Landroid/content/Context;
    //   305: invokestatic 287	android/preference/PreferenceManager:getDefaultSharedPreferences	(Landroid/content/Context;)Landroid/content/SharedPreferences;
    //   308: ldc_w 289
    //   311: iconst_1
    //   312: invokeinterface 295 3 0
    //   317: ifne +193 -> 510
    //   320: new 240	android/content/Intent
    //   323: dup
    //   324: aload_0
    //   325: invokevirtual 111	net/geekstools/floatshort/PRO/App_Unlimited_Gps:getApplicationContext	()Landroid/content/Context;
    //   328: ldc_w 297
    //   331: invokespecial 300	android/content/Intent:<init>	(Landroid/content/Context;Ljava/lang/Class;)V
    //   334: astore_1
    //   335: aload_0
    //   336: aload_1
    //   337: invokevirtual 304	net/geekstools/floatshort/PRO/App_Unlimited_Gps:stopService	(Landroid/content/Intent;)Z
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
    //   368: invokevirtual 111	net/geekstools/floatshort/PRO/App_Unlimited_Gps:getApplicationContext	()Landroid/content/Context;
    //   371: invokestatic 287	android/preference/PreferenceManager:getDefaultSharedPreferences	(Landroid/content/Context;)Landroid/content/SharedPreferences;
    //   374: ldc_w 289
    //   377: iconst_1
    //   378: invokeinterface 295 3 0
    //   383: ifne +127 -> 510
    //   386: new 240	android/content/Intent
    //   389: dup
    //   390: aload_0
    //   391: invokevirtual 111	net/geekstools/floatshort/PRO/App_Unlimited_Gps:getApplicationContext	()Landroid/content/Context;
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
    //   419: invokevirtual 111	net/geekstools/floatshort/PRO/App_Unlimited_Gps:getApplicationContext	()Landroid/content/Context;
    //   422: invokestatic 287	android/preference/PreferenceManager:getDefaultSharedPreferences	(Landroid/content/Context;)Landroid/content/SharedPreferences;
    //   425: ldc_w 289
    //   428: iconst_1
    //   429: invokeinterface 295 3 0
    //   434: ifne +22 -> 456
    //   437: aload_0
    //   438: new 240	android/content/Intent
    //   441: dup
    //   442: aload_0
    //   443: invokevirtual 111	net/geekstools/floatshort/PRO/App_Unlimited_Gps:getApplicationContext	()Landroid/content/Context;
    //   446: ldc_w 297
    //   449: invokespecial 300	android/content/Intent:<init>	(Landroid/content/Context;Ljava/lang/Class;)V
    //   452: invokevirtual 304	net/geekstools/floatshort/PRO/App_Unlimited_Gps:stopService	(Landroid/content/Intent;)Z
    //   455: pop
    //   456: aload_1
    //   457: athrow
    //   458: getstatic 281	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:floatingCounter	I
    //   461: ifne +49 -> 510
    //   464: aload_0
    //   465: invokevirtual 111	net/geekstools/floatshort/PRO/App_Unlimited_Gps:getApplicationContext	()Landroid/content/Context;
    //   468: invokestatic 287	android/preference/PreferenceManager:getDefaultSharedPreferences	(Landroid/content/Context;)Landroid/content/SharedPreferences;
    //   471: ldc_w 289
    //   474: iconst_1
    //   475: invokeinterface 295 3 0
    //   480: ifne +30 -> 510
    //   483: aload_0
    //   484: new 240	android/content/Intent
    //   487: dup
    //   488: aload_0
    //   489: invokevirtual 111	net/geekstools/floatshort/PRO/App_Unlimited_Gps:getApplicationContext	()Landroid/content/Context;
    //   492: ldc_w 297
    //   495: invokespecial 300	android/content/Intent:<init>	(Landroid/content/Context;Ljava/lang/Class;)V
    //   498: invokevirtual 304	net/geekstools/floatshort/PRO/App_Unlimited_Gps:stopService	(Landroid/content/Intent;)Z
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
    //   517: aload_0
    //   518: getfield 309	net/geekstools/floatshort/PRO/App_Unlimited_Gps:broadcastReceiver	Landroid/content/BroadcastReceiver;
    //   521: ifnull +19 -> 540
    //   524: aload_0
    //   525: aload_0
    //   526: getfield 309	net/geekstools/floatshort/PRO/App_Unlimited_Gps:broadcastReceiver	Landroid/content/BroadcastReceiver;
    //   529: invokevirtual 313	net/geekstools/floatshort/PRO/App_Unlimited_Gps:unregisterReceiver	(Landroid/content/BroadcastReceiver;)V
    //   532: goto +8 -> 540
    //   535: astore_1
    //   536: aload_1
    //   537: invokevirtual 307	java/lang/Exception:printStackTrace	()V
    //   540: aload_0
    //   541: invokevirtual 316	net/geekstools/floatshort/PRO/App_Unlimited_Gps:stopSelf	()V
    //   544: iconst_2
    //   545: ireturn
    //   546: aload_0
    //   547: getfield 179	net/geekstools/floatshort/PRO/App_Unlimited_Gps:mapPackageNameStartId	Ljava/util/Map;
    //   550: aload_0
    //   551: getfield 146	net/geekstools/floatshort/PRO/App_Unlimited_Gps:packages	[Ljava/lang/String;
    //   554: iload_3
    //   555: aaload
    //   556: iload_3
    //   557: invokestatic 322	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   560: invokeinterface 328 3 0
    //   565: pop
    //   566: aload_0
    //   567: getfield 116	net/geekstools/floatshort/PRO/App_Unlimited_Gps:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
    //   570: aload_0
    //   571: getfield 146	net/geekstools/floatshort/PRO/App_Unlimited_Gps:packages	[Ljava/lang/String;
    //   574: iload_3
    //   575: aaload
    //   576: invokevirtual 332	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:appInstalledOrNot	(Ljava/lang/String;)Z
    //   579: ifne +5 -> 584
    //   582: iconst_2
    //   583: ireturn
    //   584: aload_0
    //   585: getfield 150	net/geekstools/floatshort/PRO/App_Unlimited_Gps:appIcon	[Landroid/graphics/drawable/Drawable;
    //   588: iload_3
    //   589: aload_0
    //   590: getfield 116	net/geekstools/floatshort/PRO/App_Unlimited_Gps:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
    //   593: aload_0
    //   594: getfield 146	net/geekstools/floatshort/PRO/App_Unlimited_Gps:packages	[Ljava/lang/String;
    //   597: iload_3
    //   598: aaload
    //   599: invokevirtual 336	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:shapedAppIcon	(Ljava/lang/String;)Landroid/graphics/drawable/Drawable;
    //   602: aastore
    //   603: aload_0
    //   604: getfield 152	net/geekstools/floatshort/PRO/App_Unlimited_Gps:iconColor	[I
    //   607: iload_3
    //   608: aload_0
    //   609: getfield 116	net/geekstools/floatshort/PRO/App_Unlimited_Gps:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
    //   612: aload_0
    //   613: getfield 116	net/geekstools/floatshort/PRO/App_Unlimited_Gps:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
    //   616: aload_0
    //   617: getfield 146	net/geekstools/floatshort/PRO/App_Unlimited_Gps:packages	[Ljava/lang/String;
    //   620: iload_3
    //   621: aaload
    //   622: invokevirtual 338	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:appIcon	(Ljava/lang/String;)Landroid/graphics/drawable/Drawable;
    //   625: invokevirtual 342	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:extractDominantColor	(Landroid/graphics/drawable/Drawable;)I
    //   628: iastore
    //   629: aload_0
    //   630: getfield 162	net/geekstools/floatshort/PRO/App_Unlimited_Gps:shapedIcon	[Lnet/geekstools/imageview/customshapes/ShapesImage;
    //   633: iload_3
    //   634: aaload
    //   635: aload_0
    //   636: getfield 116	net/geekstools/floatshort/PRO/App_Unlimited_Gps:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
    //   639: aload_0
    //   640: getfield 146	net/geekstools/floatshort/PRO/App_Unlimited_Gps:packages	[Ljava/lang/String;
    //   643: iload_3
    //   644: aaload
    //   645: invokevirtual 336	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:shapedAppIcon	(Ljava/lang/String;)Landroid/graphics/drawable/Drawable;
    //   648: invokevirtual 346	net/geekstools/imageview/customshapes/ShapesImage:setImageDrawable	(Landroid/graphics/drawable/Drawable;)V
    //   651: aload_0
    //   652: aload_0
    //   653: aload_0
    //   654: getfield 146	net/geekstools/floatshort/PRO/App_Unlimited_Gps:packages	[Ljava/lang/String;
    //   657: iload_3
    //   658: aaload
    //   659: iconst_0
    //   660: invokevirtual 350	net/geekstools/floatshort/PRO/App_Unlimited_Gps:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   663: putfield 352	net/geekstools/floatshort/PRO/App_Unlimited_Gps:sharedPrefPosition	Landroid/content/SharedPreferences;
    //   666: goto +8 -> 674
    //   669: astore_1
    //   670: aload_1
    //   671: invokevirtual 307	java/lang/Exception:printStackTrace	()V
    //   674: aload_0
    //   675: aload_0
    //   676: getfield 82	net/geekstools/floatshort/PRO/App_Unlimited_Gps:xInit	I
    //   679: bipush 13
    //   681: iadd
    //   682: putfield 82	net/geekstools/floatshort/PRO/App_Unlimited_Gps:xInit	I
    //   685: aload_0
    //   686: aload_0
    //   687: getfield 84	net/geekstools/floatshort/PRO/App_Unlimited_Gps:yInit	I
    //   690: bipush 13
    //   692: iadd
    //   693: putfield 84	net/geekstools/floatshort/PRO/App_Unlimited_Gps:yInit	I
    //   696: aload_0
    //   697: aload_0
    //   698: getfield 352	net/geekstools/floatshort/PRO/App_Unlimited_Gps:sharedPrefPosition	Landroid/content/SharedPreferences;
    //   701: ldc_w 354
    //   704: aload_0
    //   705: getfield 82	net/geekstools/floatshort/PRO/App_Unlimited_Gps:xInit	I
    //   708: invokeinterface 358 3 0
    //   713: putfield 360	net/geekstools/floatshort/PRO/App_Unlimited_Gps:xPos	I
    //   716: aload_0
    //   717: aload_0
    //   718: getfield 352	net/geekstools/floatshort/PRO/App_Unlimited_Gps:sharedPrefPosition	Landroid/content/SharedPreferences;
    //   721: ldc_w 362
    //   724: aload_0
    //   725: getfield 84	net/geekstools/floatshort/PRO/App_Unlimited_Gps:yInit	I
    //   728: invokeinterface 358 3 0
    //   733: putfield 364	net/geekstools/floatshort/PRO/App_Unlimited_Gps:yPos	I
    //   736: aload_0
    //   737: getfield 140	net/geekstools/floatshort/PRO/App_Unlimited_Gps:params	[Landroid/view/WindowManager$LayoutParams;
    //   740: iload_3
    //   741: aload_0
    //   742: getfield 116	net/geekstools/floatshort/PRO/App_Unlimited_Gps:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
    //   745: getstatic 367	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:HW	I
    //   748: aload_0
    //   749: getfield 360	net/geekstools/floatshort/PRO/App_Unlimited_Gps:xPos	I
    //   752: aload_0
    //   753: getfield 364	net/geekstools/floatshort/PRO/App_Unlimited_Gps:yPos	I
    //   756: invokevirtual 371	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:normalLayoutParams	(III)Landroid/view/WindowManager$LayoutParams;
    //   759: aastore
    //   760: aload_0
    //   761: getfield 232	net/geekstools/floatshort/PRO/App_Unlimited_Gps:windowManager	Landroid/view/WindowManager;
    //   764: aload_0
    //   765: getfield 156	net/geekstools/floatshort/PRO/App_Unlimited_Gps:floatingView	[Landroid/view/ViewGroup;
    //   768: iload_3
    //   769: aaload
    //   770: aload_0
    //   771: getfield 140	net/geekstools/floatshort/PRO/App_Unlimited_Gps:params	[Landroid/view/WindowManager$LayoutParams;
    //   774: iload_3
    //   775: aaload
    //   776: invokeinterface 375 3 0
    //   781: aload_0
    //   782: getfield 162	net/geekstools/floatshort/PRO/App_Unlimited_Gps:shapedIcon	[Lnet/geekstools/imageview/customshapes/ShapesImage;
    //   785: iload_3
    //   786: aaload
    //   787: aload_0
    //   788: getfield 116	net/geekstools/floatshort/PRO/App_Unlimited_Gps:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
    //   791: ldc_w 377
    //   794: sipush 255
    //   797: invokevirtual 380	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:readDefaultPreference	(Ljava/lang/String;I)I
    //   800: invokevirtual 384	net/geekstools/imageview/customshapes/ShapesImage:setImageAlpha	(I)V
    //   803: aload_0
    //   804: getfield 156	net/geekstools/floatshort/PRO/App_Unlimited_Gps:floatingView	[Landroid/view/ViewGroup;
    //   807: iload_3
    //   808: aaload
    //   809: new 6	net/geekstools/floatshort/PRO/App_Unlimited_Gps$1
    //   812: dup
    //   813: aload_0
    //   814: invokespecial 387	net/geekstools/floatshort/PRO/App_Unlimited_Gps$1:<init>	(Lnet/geekstools/floatshort/PRO/App_Unlimited_Gps;)V
    //   817: invokevirtual 391	android/view/ViewGroup:setOnFocusChangeListener	(Landroid/view/View$OnFocusChangeListener;)V
    //   820: aload_0
    //   821: getfield 156	net/geekstools/floatshort/PRO/App_Unlimited_Gps:floatingView	[Landroid/view/ViewGroup;
    //   824: iload_3
    //   825: aaload
    //   826: new 8	net/geekstools/floatshort/PRO/App_Unlimited_Gps$2
    //   829: dup
    //   830: aload_0
    //   831: iload_3
    //   832: invokespecial 394	net/geekstools/floatshort/PRO/App_Unlimited_Gps$2:<init>	(Lnet/geekstools/floatshort/PRO/App_Unlimited_Gps;I)V
    //   835: invokevirtual 398	android/view/ViewGroup:setOnTouchListener	(Landroid/view/View$OnTouchListener;)V
    //   838: aload_0
    //   839: getfield 156	net/geekstools/floatshort/PRO/App_Unlimited_Gps:floatingView	[Landroid/view/ViewGroup;
    //   842: iload_3
    //   843: aaload
    //   844: new 18	net/geekstools/floatshort/PRO/App_Unlimited_Gps$3
    //   847: dup
    //   848: aload_0
    //   849: iload_3
    //   850: invokespecial 399	net/geekstools/floatshort/PRO/App_Unlimited_Gps$3:<init>	(Lnet/geekstools/floatshort/PRO/App_Unlimited_Gps;I)V
    //   853: invokevirtual 403	android/view/ViewGroup:setOnClickListener	(Landroid/view/View$OnClickListener;)V
    //   856: aload_0
    //   857: getfield 164	net/geekstools/floatshort/PRO/App_Unlimited_Gps:notificationDot	[Lnet/geekstools/imageview/customshapes/ShapesImage;
    //   860: iload_3
    //   861: aaload
    //   862: new 20	net/geekstools/floatshort/PRO/App_Unlimited_Gps$4
    //   865: dup
    //   866: aload_0
    //   867: iload_3
    //   868: invokespecial 404	net/geekstools/floatshort/PRO/App_Unlimited_Gps$4:<init>	(Lnet/geekstools/floatshort/PRO/App_Unlimited_Gps;I)V
    //   871: invokevirtual 405	net/geekstools/imageview/customshapes/ShapesImage:setOnClickListener	(Landroid/view/View$OnClickListener;)V
    //   874: aload_0
    //   875: getfield 164	net/geekstools/floatshort/PRO/App_Unlimited_Gps:notificationDot	[Lnet/geekstools/imageview/customshapes/ShapesImage;
    //   878: iload_3
    //   879: aaload
    //   880: new 22	net/geekstools/floatshort/PRO/App_Unlimited_Gps$5
    //   883: dup
    //   884: aload_0
    //   885: iload_3
    //   886: invokespecial 406	net/geekstools/floatshort/PRO/App_Unlimited_Gps$5:<init>	(Lnet/geekstools/floatshort/PRO/App_Unlimited_Gps;I)V
    //   889: invokevirtual 410	net/geekstools/imageview/customshapes/ShapesImage:setOnLongClickListener	(Landroid/view/View$OnLongClickListener;)V
    //   892: ldc 2
    //   894: invokevirtual 204	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   897: astore_1
    //   898: new 412	android/content/IntentFilter
    //   901: dup
    //   902: invokespecial 413	android/content/IntentFilter:<init>	()V
    //   905: astore 5
    //   907: new 191	java/lang/StringBuilder
    //   910: dup
    //   911: invokespecial 192	java/lang/StringBuilder:<init>	()V
    //   914: astore 6
    //   916: aload 6
    //   918: ldc_w 415
    //   921: invokevirtual 208	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   924: pop
    //   925: aload 6
    //   927: aload_1
    //   928: invokevirtual 208	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   931: pop
    //   932: aload 5
    //   934: aload 6
    //   936: invokevirtual 216	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   939: invokevirtual 418	android/content/IntentFilter:addAction	(Ljava/lang/String;)V
    //   942: new 191	java/lang/StringBuilder
    //   945: dup
    //   946: invokespecial 192	java/lang/StringBuilder:<init>	()V
    //   949: astore 6
    //   951: aload 6
    //   953: ldc_w 420
    //   956: invokevirtual 208	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   959: pop
    //   960: aload 6
    //   962: aload_1
    //   963: invokevirtual 208	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   966: pop
    //   967: aload 5
    //   969: aload 6
    //   971: invokevirtual 216	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   974: invokevirtual 418	android/content/IntentFilter:addAction	(Ljava/lang/String;)V
    //   977: new 191	java/lang/StringBuilder
    //   980: dup
    //   981: invokespecial 192	java/lang/StringBuilder:<init>	()V
    //   984: astore 6
    //   986: aload 6
    //   988: ldc_w 422
    //   991: invokevirtual 208	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   994: pop
    //   995: aload 6
    //   997: aload_1
    //   998: invokevirtual 208	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1001: pop
    //   1002: aload 5
    //   1004: aload 6
    //   1006: invokevirtual 216	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1009: invokevirtual 418	android/content/IntentFilter:addAction	(Ljava/lang/String;)V
    //   1012: new 191	java/lang/StringBuilder
    //   1015: dup
    //   1016: invokespecial 192	java/lang/StringBuilder:<init>	()V
    //   1019: astore 6
    //   1021: aload 6
    //   1023: ldc_w 424
    //   1026: invokevirtual 208	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1029: pop
    //   1030: aload 6
    //   1032: aload_1
    //   1033: invokevirtual 208	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1036: pop
    //   1037: aload 5
    //   1039: aload 6
    //   1041: invokevirtual 216	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1044: invokevirtual 418	android/content/IntentFilter:addAction	(Ljava/lang/String;)V
    //   1047: new 191	java/lang/StringBuilder
    //   1050: dup
    //   1051: invokespecial 192	java/lang/StringBuilder:<init>	()V
    //   1054: astore 6
    //   1056: aload 6
    //   1058: ldc_w 426
    //   1061: invokevirtual 208	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1064: pop
    //   1065: aload 6
    //   1067: aload_1
    //   1068: invokevirtual 208	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1071: pop
    //   1072: aload 5
    //   1074: aload 6
    //   1076: invokevirtual 216	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1079: invokevirtual 418	android/content/IntentFilter:addAction	(Ljava/lang/String;)V
    //   1082: new 191	java/lang/StringBuilder
    //   1085: dup
    //   1086: invokespecial 192	java/lang/StringBuilder:<init>	()V
    //   1089: astore 6
    //   1091: aload 6
    //   1093: ldc_w 428
    //   1096: invokevirtual 208	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1099: pop
    //   1100: aload 6
    //   1102: aload_1
    //   1103: invokevirtual 208	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1106: pop
    //   1107: aload 5
    //   1109: aload 6
    //   1111: invokevirtual 216	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1114: invokevirtual 418	android/content/IntentFilter:addAction	(Ljava/lang/String;)V
    //   1117: aload 5
    //   1119: ldc_w 430
    //   1122: invokevirtual 418	android/content/IntentFilter:addAction	(Ljava/lang/String;)V
    //   1125: aload 5
    //   1127: ldc_w 432
    //   1130: invokevirtual 418	android/content/IntentFilter:addAction	(Ljava/lang/String;)V
    //   1133: aload 5
    //   1135: ldc_w 434
    //   1138: invokevirtual 418	android/content/IntentFilter:addAction	(Ljava/lang/String;)V
    //   1141: aload 5
    //   1143: ldc_w 436
    //   1146: invokevirtual 418	android/content/IntentFilter:addAction	(Ljava/lang/String;)V
    //   1149: aload_0
    //   1150: new 24	net/geekstools/floatshort/PRO/App_Unlimited_Gps$6
    //   1153: dup
    //   1154: aload_0
    //   1155: aload_1
    //   1156: iload_3
    //   1157: invokespecial 439	net/geekstools/floatshort/PRO/App_Unlimited_Gps$6:<init>	(Lnet/geekstools/floatshort/PRO/App_Unlimited_Gps;Ljava/lang/String;I)V
    //   1160: putfield 309	net/geekstools/floatshort/PRO/App_Unlimited_Gps:broadcastReceiver	Landroid/content/BroadcastReceiver;
    //   1163: aload_0
    //   1164: aload_0
    //   1165: getfield 309	net/geekstools/floatshort/PRO/App_Unlimited_Gps:broadcastReceiver	Landroid/content/BroadcastReceiver;
    //   1168: aload 5
    //   1170: invokevirtual 443	net/geekstools/floatshort/PRO/App_Unlimited_Gps:registerReceiver	(Landroid/content/BroadcastReceiver;Landroid/content/IntentFilter;)Landroid/content/Intent;
    //   1173: pop
    //   1174: new 191	java/lang/StringBuilder
    //   1177: dup
    //   1178: invokespecial 192	java/lang/StringBuilder:<init>	()V
    //   1181: astore_1
    //   1182: aload_1
    //   1183: aload_0
    //   1184: getfield 146	net/geekstools/floatshort/PRO/App_Unlimited_Gps:packages	[Ljava/lang/String;
    //   1187: iload_3
    //   1188: aaload
    //   1189: invokevirtual 208	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1192: pop
    //   1193: aload_1
    //   1194: ldc_w 445
    //   1197: invokevirtual 208	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1200: pop
    //   1201: aload_0
    //   1202: aload_1
    //   1203: invokevirtual 216	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1206: invokevirtual 449	net/geekstools/floatshort/PRO/App_Unlimited_Gps:getFileStreamPath	(Ljava/lang/String;)Ljava/io/File;
    //   1209: invokevirtual 454	java/io/File:exists	()Z
    //   1212: ifeq +29 -> 1241
    //   1215: aload_0
    //   1216: new 240	android/content/Intent
    //   1219: dup
    //   1220: ldc_w 434
    //   1223: invokespecial 456	android/content/Intent:<init>	(Ljava/lang/String;)V
    //   1226: ldc_w 458
    //   1229: aload_0
    //   1230: getfield 146	net/geekstools/floatshort/PRO/App_Unlimited_Gps:packages	[Ljava/lang/String;
    //   1233: iload_3
    //   1234: aaload
    //   1235: invokevirtual 462	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   1238: invokevirtual 466	net/geekstools/floatshort/PRO/App_Unlimited_Gps:sendBroadcast	(Landroid/content/Intent;)V
    //   1241: aload_0
    //   1242: getfield 116	net/geekstools/floatshort/PRO/App_Unlimited_Gps:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
    //   1245: invokevirtual 469	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:serviceMode	()I
    //   1248: ireturn
    //   1249: astore_1
    //   1250: aload_1
    //   1251: invokevirtual 307	java/lang/Exception:printStackTrace	()V
    //   1254: iconst_2
    //   1255: ireturn
    //   1256: ldc_w 470
    //   1259: istore_2
    //   1260: goto -1069 -> 191
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1263	0	this	App_Unlimited_Gps
    //   0	1263	1	paramIntent	Intent
    //   0	1263	2	paramInt1	int
    //   0	1263	3	paramInt2	int
    //   265	3	4	bool	boolean
    //   3	1166	5	localObject1	Object
    //   12	1098	6	localObject2	Object
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
    //   517	532	535	java/lang/Exception
    //   651	666	669	java/lang/Exception
    //   76	184	1249	java/lang/Exception
    //   191	223	1249	java/lang/Exception
  }
}
