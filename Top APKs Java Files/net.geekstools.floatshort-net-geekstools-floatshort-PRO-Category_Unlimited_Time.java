package net.geekstools.floatshort.PRO;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.IBinder;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.RelativeLayout;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import net.geekstools.floatshort.PRO.Util.Functions.FunctionsClass;
import net.geekstools.floatshort.PRO.Util.Functions.PublicVariable;
import net.geekstools.imageview.customshapes.ShapesImage;

public class Category_Unlimited_Time
  extends Service
{
  boolean[] StickyEdge;
  WindowManager.LayoutParams[] StickyEdgeParams;
  boolean[] allowMove;
  int array;
  BroadcastReceiver broadcastReceiver;
  String[] categoryName;
  int categorySize;
  ViewGroup[] floatingView;
  ShapesImage four;
  FunctionsClass functionsClass;
  Handler handlerPressHold = new Handler();
  Map<String, Integer> mapCategoryNameStartId;
  Map<String, String> mapContentCategoryName;
  ShapesImage[] notificationDot;
  String notificationPackage;
  ShapesImage one;
  boolean[] openIt;
  WindowManager.LayoutParams[] params;
  ShapesImage[] pin;
  Runnable runnablePressHold = null;
  SharedPreferences sharedPrefPosition;
  boolean showNotificationDot = false;
  ShapesImage three;
  boolean[] touchingDelay;
  boolean[] trans;
  ShapesImage two;
  RelativeLayout wholeCategoryFloating;
  WindowManager windowManager;
  int xInit = 19;
  int xMove;
  int xPos;
  int yInit = 19;
  int yMove;
  int yPos;
  
  public Category_Unlimited_Time() {}
  
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
    this.floatingView = new ViewGroup[this.array];
    this.categoryName = new String[this.array];
    this.pin = new ShapesImage[this.array];
    this.notificationDot = new ShapesImage[this.array];
    this.allowMove = new boolean[this.array];
    this.touchingDelay = new boolean[this.array];
    this.trans = new boolean[this.array];
    this.StickyEdge = new boolean[this.array];
    this.openIt = new boolean[this.array];
    this.mapContentCategoryName = new LinkedHashMap();
    this.mapCategoryNameStartId = new LinkedHashMap();
  }
  
  public void onDestroy()
  {
    super.onDestroy();
  }
  
  /* Error */
  public int onStartCommand(final Intent paramIntent, int paramInt1, final int paramInt2)
  {
    // Byte code:
    //   0: getstatic 185	java/lang/System:out	Ljava/io/PrintStream;
    //   3: astore 5
    //   5: new 187	java/lang/StringBuilder
    //   8: dup
    //   9: invokespecial 188	java/lang/StringBuilder:<init>	()V
    //   12: astore 6
    //   14: aload 6
    //   16: aload_0
    //   17: invokevirtual 194	java/lang/Object:getClass	()Ljava/lang/Class;
    //   20: invokevirtual 200	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   23: invokevirtual 204	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   26: pop
    //   27: aload 6
    //   29: ldc -50
    //   31: invokevirtual 204	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   34: pop
    //   35: aload 6
    //   37: iload_3
    //   38: invokevirtual 209	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   41: pop
    //   42: aload 5
    //   44: aload 6
    //   46: invokevirtual 212	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   49: invokevirtual 218	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   52: aload_0
    //   53: aload_0
    //   54: ldc -36
    //   56: invokevirtual 224	net/geekstools/floatshort/PRO/Category_Unlimited_Time:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   59: checkcast 226	android/view/WindowManager
    //   62: putfield 228	net/geekstools/floatshort/PRO/Category_Unlimited_Time:windowManager	Landroid/view/WindowManager;
    //   65: aload_0
    //   66: getfield 149	net/geekstools/floatshort/PRO/Category_Unlimited_Time:categoryName	[Ljava/lang/String;
    //   69: iload_3
    //   70: aload_1
    //   71: ldc -27
    //   73: invokevirtual 235	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   76: aastore
    //   77: aload_0
    //   78: getfield 159	net/geekstools/floatshort/PRO/Category_Unlimited_Time:touchingDelay	[Z
    //   81: iload_3
    //   82: iconst_0
    //   83: bastore
    //   84: aload_0
    //   85: getfield 163	net/geekstools/floatshort/PRO/Category_Unlimited_Time:StickyEdge	[Z
    //   88: iload_3
    //   89: iconst_0
    //   90: bastore
    //   91: aload_0
    //   92: getfield 157	net/geekstools/floatshort/PRO/Category_Unlimited_Time:allowMove	[Z
    //   95: iload_3
    //   96: iconst_1
    //   97: bastore
    //   98: aload_0
    //   99: getfield 165	net/geekstools/floatshort/PRO/Category_Unlimited_Time:openIt	[Z
    //   102: iload_3
    //   103: iconst_1
    //   104: bastore
    //   105: aload_0
    //   106: ldc -19
    //   108: invokevirtual 224	net/geekstools/floatshort/PRO/Category_Unlimited_Time:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   111: checkcast 239	android/view/LayoutInflater
    //   114: astore_1
    //   115: aload_0
    //   116: getfield 145	net/geekstools/floatshort/PRO/Category_Unlimited_Time:floatingView	[Landroid/view/ViewGroup;
    //   119: iload_3
    //   120: aload_1
    //   121: ldc -16
    //   123: aconst_null
    //   124: iconst_0
    //   125: invokevirtual 244	android/view/LayoutInflater:inflate	(ILandroid/view/ViewGroup;Z)Landroid/view/View;
    //   128: checkcast 143	android/view/ViewGroup
    //   131: aastore
    //   132: getstatic 248	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:size	I
    //   135: bipush 13
    //   137: if_icmpeq +101 -> 238
    //   140: getstatic 248	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:size	I
    //   143: bipush 26
    //   145: if_icmpne +6 -> 151
    //   148: goto +90 -> 238
    //   151: getstatic 248	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:size	I
    //   154: bipush 39
    //   156: if_icmpeq +56 -> 212
    //   159: getstatic 248	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:size	I
    //   162: bipush 52
    //   164: if_icmpne +6 -> 170
    //   167: goto +45 -> 212
    //   170: getstatic 248	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:size	I
    //   173: bipush 65
    //   175: if_icmpeq +11 -> 186
    //   178: getstatic 248	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:size	I
    //   181: bipush 78
    //   183: if_icmpne +78 -> 261
    //   186: aload_0
    //   187: getfield 145	net/geekstools/floatshort/PRO/Category_Unlimited_Time:floatingView	[Landroid/view/ViewGroup;
    //   190: iload_3
    //   191: aload_1
    //   192: ldc -7
    //   194: aconst_null
    //   195: iconst_0
    //   196: invokevirtual 244	android/view/LayoutInflater:inflate	(ILandroid/view/ViewGroup;Z)Landroid/view/View;
    //   199: checkcast 143	android/view/ViewGroup
    //   202: aastore
    //   203: aload_0
    //   204: bipush 48
    //   206: putfield 251	net/geekstools/floatshort/PRO/Category_Unlimited_Time:categorySize	I
    //   209: goto +52 -> 261
    //   212: aload_0
    //   213: getfield 145	net/geekstools/floatshort/PRO/Category_Unlimited_Time:floatingView	[Landroid/view/ViewGroup;
    //   216: iload_3
    //   217: aload_1
    //   218: ldc -16
    //   220: aconst_null
    //   221: iconst_0
    //   222: invokevirtual 244	android/view/LayoutInflater:inflate	(ILandroid/view/ViewGroup;Z)Landroid/view/View;
    //   225: checkcast 143	android/view/ViewGroup
    //   228: aastore
    //   229: aload_0
    //   230: bipush 36
    //   232: putfield 251	net/geekstools/floatshort/PRO/Category_Unlimited_Time:categorySize	I
    //   235: goto +26 -> 261
    //   238: aload_0
    //   239: getfield 145	net/geekstools/floatshort/PRO/Category_Unlimited_Time:floatingView	[Landroid/view/ViewGroup;
    //   242: iload_3
    //   243: aload_1
    //   244: ldc -4
    //   246: aconst_null
    //   247: iconst_0
    //   248: invokevirtual 244	android/view/LayoutInflater:inflate	(ILandroid/view/ViewGroup;Z)Landroid/view/View;
    //   251: checkcast 143	android/view/ViewGroup
    //   254: aastore
    //   255: aload_0
    //   256: bipush 24
    //   258: putfield 251	net/geekstools/floatshort/PRO/Category_Unlimited_Time:categorySize	I
    //   261: aload_0
    //   262: aload_0
    //   263: getfield 145	net/geekstools/floatshort/PRO/Category_Unlimited_Time:floatingView	[Landroid/view/ViewGroup;
    //   266: iload_3
    //   267: aaload
    //   268: ldc -3
    //   270: invokevirtual 257	android/view/ViewGroup:findViewById	(I)Landroid/view/View;
    //   273: checkcast 259	android/widget/RelativeLayout
    //   276: putfield 261	net/geekstools/floatshort/PRO/Category_Unlimited_Time:wholeCategoryFloating	Landroid/widget/RelativeLayout;
    //   279: aload_0
    //   280: aload_0
    //   281: getfield 115	net/geekstools/floatshort/PRO/Category_Unlimited_Time:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
    //   284: aload_0
    //   285: getfield 145	net/geekstools/floatshort/PRO/Category_Unlimited_Time:floatingView	[Landroid/view/ViewGroup;
    //   288: iload_3
    //   289: aaload
    //   290: ldc_w 262
    //   293: invokevirtual 266	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:initShapesImage	(Landroid/view/ViewGroup;I)Lnet/geekstools/imageview/customshapes/ShapesImage;
    //   296: putfield 268	net/geekstools/floatshort/PRO/Category_Unlimited_Time:one	Lnet/geekstools/imageview/customshapes/ShapesImage;
    //   299: aload_0
    //   300: aload_0
    //   301: getfield 115	net/geekstools/floatshort/PRO/Category_Unlimited_Time:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
    //   304: aload_0
    //   305: getfield 145	net/geekstools/floatshort/PRO/Category_Unlimited_Time:floatingView	[Landroid/view/ViewGroup;
    //   308: iload_3
    //   309: aaload
    //   310: ldc_w 269
    //   313: invokevirtual 266	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:initShapesImage	(Landroid/view/ViewGroup;I)Lnet/geekstools/imageview/customshapes/ShapesImage;
    //   316: putfield 271	net/geekstools/floatshort/PRO/Category_Unlimited_Time:two	Lnet/geekstools/imageview/customshapes/ShapesImage;
    //   319: aload_0
    //   320: aload_0
    //   321: getfield 115	net/geekstools/floatshort/PRO/Category_Unlimited_Time:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
    //   324: aload_0
    //   325: getfield 145	net/geekstools/floatshort/PRO/Category_Unlimited_Time:floatingView	[Landroid/view/ViewGroup;
    //   328: iload_3
    //   329: aaload
    //   330: ldc_w 272
    //   333: invokevirtual 266	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:initShapesImage	(Landroid/view/ViewGroup;I)Lnet/geekstools/imageview/customshapes/ShapesImage;
    //   336: putfield 274	net/geekstools/floatshort/PRO/Category_Unlimited_Time:three	Lnet/geekstools/imageview/customshapes/ShapesImage;
    //   339: aload_0
    //   340: aload_0
    //   341: getfield 115	net/geekstools/floatshort/PRO/Category_Unlimited_Time:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
    //   344: aload_0
    //   345: getfield 145	net/geekstools/floatshort/PRO/Category_Unlimited_Time:floatingView	[Landroid/view/ViewGroup;
    //   348: iload_3
    //   349: aaload
    //   350: ldc_w 275
    //   353: invokevirtual 266	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:initShapesImage	(Landroid/view/ViewGroup;I)Lnet/geekstools/imageview/customshapes/ShapesImage;
    //   356: putfield 277	net/geekstools/floatshort/PRO/Category_Unlimited_Time:four	Lnet/geekstools/imageview/customshapes/ShapesImage;
    //   359: aload_0
    //   360: getfield 153	net/geekstools/floatshort/PRO/Category_Unlimited_Time:pin	[Lnet/geekstools/imageview/customshapes/ShapesImage;
    //   363: iload_3
    //   364: aload_0
    //   365: getfield 115	net/geekstools/floatshort/PRO/Category_Unlimited_Time:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
    //   368: aload_0
    //   369: getfield 145	net/geekstools/floatshort/PRO/Category_Unlimited_Time:floatingView	[Landroid/view/ViewGroup;
    //   372: iload_3
    //   373: aaload
    //   374: ldc_w 278
    //   377: invokevirtual 266	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:initShapesImage	(Landroid/view/ViewGroup;I)Lnet/geekstools/imageview/customshapes/ShapesImage;
    //   380: aastore
    //   381: aload_0
    //   382: getfield 155	net/geekstools/floatshort/PRO/Category_Unlimited_Time:notificationDot	[Lnet/geekstools/imageview/customshapes/ShapesImage;
    //   385: astore_1
    //   386: aload_0
    //   387: getfield 115	net/geekstools/floatshort/PRO/Category_Unlimited_Time:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
    //   390: astore 5
    //   392: aload_0
    //   393: getfield 145	net/geekstools/floatshort/PRO/Category_Unlimited_Time:floatingView	[Landroid/view/ViewGroup;
    //   396: iload_3
    //   397: aaload
    //   398: astore 6
    //   400: aload_0
    //   401: getfield 115	net/geekstools/floatshort/PRO/Category_Unlimited_Time:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
    //   404: invokevirtual 282	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:checkStickyEdge	()Z
    //   407: ifeq +10 -> 417
    //   410: ldc_w 283
    //   413: istore_2
    //   414: goto +7 -> 421
    //   417: ldc_w 284
    //   420: istore_2
    //   421: aload_1
    //   422: iload_3
    //   423: aload 5
    //   425: aload 6
    //   427: iload_2
    //   428: invokevirtual 266	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:initShapesImage	(Landroid/view/ViewGroup;I)Lnet/geekstools/imageview/customshapes/ShapesImage;
    //   431: aastore
    //   432: aload_0
    //   433: getfield 115	net/geekstools/floatshort/PRO/Category_Unlimited_Time:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
    //   436: invokevirtual 287	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:shapesImageId	()I
    //   439: tableswitch	default:+37->476, 0:+37->476, 1:+86->525, 2:+75->514, 3:+64->503, 4:+53->492, 5:+42->481
    //   476: aconst_null
    //   477: astore_1
    //   478: goto +55 -> 533
    //   481: aload_0
    //   482: ldc_w 288
    //   485: invokevirtual 292	net/geekstools/floatshort/PRO/Category_Unlimited_Time:getDrawable	(I)Landroid/graphics/drawable/Drawable;
    //   488: astore_1
    //   489: goto +44 -> 533
    //   492: aload_0
    //   493: ldc_w 293
    //   496: invokevirtual 292	net/geekstools/floatshort/PRO/Category_Unlimited_Time:getDrawable	(I)Landroid/graphics/drawable/Drawable;
    //   499: astore_1
    //   500: goto +33 -> 533
    //   503: aload_0
    //   504: ldc_w 294
    //   507: invokevirtual 292	net/geekstools/floatshort/PRO/Category_Unlimited_Time:getDrawable	(I)Landroid/graphics/drawable/Drawable;
    //   510: astore_1
    //   511: goto +22 -> 533
    //   514: aload_0
    //   515: ldc_w 295
    //   518: invokevirtual 292	net/geekstools/floatshort/PRO/Category_Unlimited_Time:getDrawable	(I)Landroid/graphics/drawable/Drawable;
    //   521: astore_1
    //   522: goto +11 -> 533
    //   525: aload_0
    //   526: ldc_w 296
    //   529: invokevirtual 292	net/geekstools/floatshort/PRO/Category_Unlimited_Time:getDrawable	(I)Landroid/graphics/drawable/Drawable;
    //   532: astore_1
    //   533: aload_1
    //   534: ifnull +27 -> 561
    //   537: aload_1
    //   538: getstatic 299	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:primaryColor	I
    //   541: invokevirtual 305	android/graphics/drawable/Drawable:setTint	(I)V
    //   544: aload_1
    //   545: aload_0
    //   546: getfield 115	net/geekstools/floatshort/PRO/Category_Unlimited_Time:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
    //   549: ldc_w 307
    //   552: sipush 255
    //   555: invokevirtual 311	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:readDefaultPreference	(Ljava/lang/String;I)I
    //   558: invokevirtual 314	android/graphics/drawable/Drawable:setAlpha	(I)V
    //   561: aload_0
    //   562: getfield 261	net/geekstools/floatshort/PRO/Category_Unlimited_Time:wholeCategoryFloating	Landroid/widget/RelativeLayout;
    //   565: aload_1
    //   566: invokevirtual 318	android/widget/RelativeLayout:setBackground	(Landroid/graphics/drawable/Drawable;)V
    //   569: aload_0
    //   570: getfield 149	net/geekstools/floatshort/PRO/Category_Unlimited_Time:categoryName	[Ljava/lang/String;
    //   573: iload_3
    //   574: aaload
    //   575: aload_0
    //   576: ldc_w 319
    //   579: invokevirtual 323	net/geekstools/floatshort/PRO/Category_Unlimited_Time:getString	(I)Ljava/lang/String;
    //   582: invokevirtual 327	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   585: ifeq +317 -> 902
    //   588: iconst_1
    //   589: istore_2
    //   590: iload_2
    //   591: iload_3
    //   592: if_icmpge +271 -> 863
    //   595: aload_0
    //   596: getfield 145	net/geekstools/floatshort/PRO/Category_Unlimited_Time:floatingView	[Landroid/view/ViewGroup;
    //   599: ifnull +257 -> 856
    //   602: aload_0
    //   603: getfield 145	net/geekstools/floatshort/PRO/Category_Unlimited_Time:floatingView	[Landroid/view/ViewGroup;
    //   606: iload_2
    //   607: aaload
    //   608: invokevirtual 330	android/view/ViewGroup:isShown	()Z
    //   611: istore 4
    //   613: iload 4
    //   615: ifeq +189 -> 804
    //   618: aload_0
    //   619: getfield 228	net/geekstools/floatshort/PRO/Category_Unlimited_Time:windowManager	Landroid/view/WindowManager;
    //   622: aload_0
    //   623: getfield 145	net/geekstools/floatshort/PRO/Category_Unlimited_Time:floatingView	[Landroid/view/ViewGroup;
    //   626: iload_2
    //   627: aaload
    //   628: invokeinterface 334 2 0
    //   633: getstatic 337	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:floatingCounter	I
    //   636: iconst_1
    //   637: isub
    //   638: putstatic 337	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:floatingCounter	I
    //   641: getstatic 337	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:floatingCounter	I
    //   644: ifne +212 -> 856
    //   647: aload_0
    //   648: invokevirtual 110	net/geekstools/floatshort/PRO/Category_Unlimited_Time:getApplicationContext	()Landroid/content/Context;
    //   651: invokestatic 343	android/preference/PreferenceManager:getDefaultSharedPreferences	(Landroid/content/Context;)Landroid/content/SharedPreferences;
    //   654: ldc_w 345
    //   657: iconst_1
    //   658: invokeinterface 351 3 0
    //   663: ifne +193 -> 856
    //   666: new 231	android/content/Intent
    //   669: dup
    //   670: aload_0
    //   671: invokevirtual 110	net/geekstools/floatshort/PRO/Category_Unlimited_Time:getApplicationContext	()Landroid/content/Context;
    //   674: ldc_w 353
    //   677: invokespecial 356	android/content/Intent:<init>	(Landroid/content/Context;Ljava/lang/Class;)V
    //   680: astore_1
    //   681: aload_0
    //   682: aload_1
    //   683: invokevirtual 360	net/geekstools/floatshort/PRO/Category_Unlimited_Time:stopService	(Landroid/content/Intent;)Z
    //   686: pop
    //   687: goto +169 -> 856
    //   690: astore_1
    //   691: goto +59 -> 750
    //   694: astore_1
    //   695: aload_1
    //   696: invokevirtual 363	java/lang/Exception:printStackTrace	()V
    //   699: getstatic 337	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:floatingCounter	I
    //   702: iconst_1
    //   703: isub
    //   704: putstatic 337	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:floatingCounter	I
    //   707: getstatic 337	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:floatingCounter	I
    //   710: ifne +146 -> 856
    //   713: aload_0
    //   714: invokevirtual 110	net/geekstools/floatshort/PRO/Category_Unlimited_Time:getApplicationContext	()Landroid/content/Context;
    //   717: invokestatic 343	android/preference/PreferenceManager:getDefaultSharedPreferences	(Landroid/content/Context;)Landroid/content/SharedPreferences;
    //   720: ldc_w 345
    //   723: iconst_1
    //   724: invokeinterface 351 3 0
    //   729: ifne +127 -> 856
    //   732: new 231	android/content/Intent
    //   735: dup
    //   736: aload_0
    //   737: invokevirtual 110	net/geekstools/floatshort/PRO/Category_Unlimited_Time:getApplicationContext	()Landroid/content/Context;
    //   740: ldc_w 353
    //   743: invokespecial 356	android/content/Intent:<init>	(Landroid/content/Context;Ljava/lang/Class;)V
    //   746: astore_1
    //   747: goto -66 -> 681
    //   750: getstatic 337	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:floatingCounter	I
    //   753: iconst_1
    //   754: isub
    //   755: putstatic 337	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:floatingCounter	I
    //   758: getstatic 337	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:floatingCounter	I
    //   761: ifne +41 -> 802
    //   764: aload_0
    //   765: invokevirtual 110	net/geekstools/floatshort/PRO/Category_Unlimited_Time:getApplicationContext	()Landroid/content/Context;
    //   768: invokestatic 343	android/preference/PreferenceManager:getDefaultSharedPreferences	(Landroid/content/Context;)Landroid/content/SharedPreferences;
    //   771: ldc_w 345
    //   774: iconst_1
    //   775: invokeinterface 351 3 0
    //   780: ifne +22 -> 802
    //   783: aload_0
    //   784: new 231	android/content/Intent
    //   787: dup
    //   788: aload_0
    //   789: invokevirtual 110	net/geekstools/floatshort/PRO/Category_Unlimited_Time:getApplicationContext	()Landroid/content/Context;
    //   792: ldc_w 353
    //   795: invokespecial 356	android/content/Intent:<init>	(Landroid/content/Context;Ljava/lang/Class;)V
    //   798: invokevirtual 360	net/geekstools/floatshort/PRO/Category_Unlimited_Time:stopService	(Landroid/content/Intent;)Z
    //   801: pop
    //   802: aload_1
    //   803: athrow
    //   804: getstatic 337	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:floatingCounter	I
    //   807: ifne +49 -> 856
    //   810: aload_0
    //   811: invokevirtual 110	net/geekstools/floatshort/PRO/Category_Unlimited_Time:getApplicationContext	()Landroid/content/Context;
    //   814: invokestatic 343	android/preference/PreferenceManager:getDefaultSharedPreferences	(Landroid/content/Context;)Landroid/content/SharedPreferences;
    //   817: ldc_w 345
    //   820: iconst_1
    //   821: invokeinterface 351 3 0
    //   826: ifne +30 -> 856
    //   829: aload_0
    //   830: new 231	android/content/Intent
    //   833: dup
    //   834: aload_0
    //   835: invokevirtual 110	net/geekstools/floatshort/PRO/Category_Unlimited_Time:getApplicationContext	()Landroid/content/Context;
    //   838: ldc_w 353
    //   841: invokespecial 356	android/content/Intent:<init>	(Landroid/content/Context;Ljava/lang/Class;)V
    //   844: invokevirtual 360	net/geekstools/floatshort/PRO/Category_Unlimited_Time:stopService	(Landroid/content/Intent;)Z
    //   847: pop
    //   848: goto +8 -> 856
    //   851: astore_1
    //   852: aload_1
    //   853: invokevirtual 363	java/lang/Exception:printStackTrace	()V
    //   856: iload_2
    //   857: iconst_1
    //   858: iadd
    //   859: istore_2
    //   860: goto -270 -> 590
    //   863: getstatic 367	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:FloatingCategories	Ljava/util/ArrayList;
    //   866: invokevirtual 372	java/util/ArrayList:clear	()V
    //   869: iconst_m1
    //   870: putstatic 375	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:categoriesCounter	I
    //   873: aload_0
    //   874: getfield 377	net/geekstools/floatshort/PRO/Category_Unlimited_Time:broadcastReceiver	Landroid/content/BroadcastReceiver;
    //   877: ifnull +19 -> 896
    //   880: aload_0
    //   881: aload_0
    //   882: getfield 377	net/geekstools/floatshort/PRO/Category_Unlimited_Time:broadcastReceiver	Landroid/content/BroadcastReceiver;
    //   885: invokevirtual 381	net/geekstools/floatshort/PRO/Category_Unlimited_Time:unregisterReceiver	(Landroid/content/BroadcastReceiver;)V
    //   888: goto +8 -> 896
    //   891: astore_1
    //   892: aload_1
    //   893: invokevirtual 363	java/lang/Exception:printStackTrace	()V
    //   896: aload_0
    //   897: invokevirtual 384	net/geekstools/floatshort/PRO/Category_Unlimited_Time:stopSelf	()V
    //   900: iconst_2
    //   901: ireturn
    //   902: aload_0
    //   903: getfield 172	net/geekstools/floatshort/PRO/Category_Unlimited_Time:mapCategoryNameStartId	Ljava/util/Map;
    //   906: aload_0
    //   907: getfield 149	net/geekstools/floatshort/PRO/Category_Unlimited_Time:categoryName	[Ljava/lang/String;
    //   910: iload_3
    //   911: aaload
    //   912: iload_3
    //   913: invokestatic 390	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   916: invokeinterface 396 3 0
    //   921: pop
    //   922: aload_0
    //   923: getfield 115	net/geekstools/floatshort/PRO/Category_Unlimited_Time:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
    //   926: aload_0
    //   927: getfield 149	net/geekstools/floatshort/PRO/Category_Unlimited_Time:categoryName	[Ljava/lang/String;
    //   930: iload_3
    //   931: aaload
    //   932: invokevirtual 400	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:readFileLine	(Ljava/lang/String;)[Ljava/lang/String;
    //   935: astore_1
    //   936: aload_1
    //   937: ifnull +288 -> 1225
    //   940: aload_1
    //   941: arraylength
    //   942: ifle +91 -> 1033
    //   945: iconst_0
    //   946: istore_2
    //   947: iload_2
    //   948: aload_1
    //   949: arraylength
    //   950: if_icmpge +83 -> 1033
    //   953: aload_0
    //   954: getfield 170	net/geekstools/floatshort/PRO/Category_Unlimited_Time:mapContentCategoryName	Ljava/util/Map;
    //   957: aload_1
    //   958: iload_2
    //   959: aaload
    //   960: aload_0
    //   961: getfield 149	net/geekstools/floatshort/PRO/Category_Unlimited_Time:categoryName	[Ljava/lang/String;
    //   964: iload_3
    //   965: aaload
    //   966: invokeinterface 396 3 0
    //   971: pop
    //   972: new 187	java/lang/StringBuilder
    //   975: dup
    //   976: invokespecial 188	java/lang/StringBuilder:<init>	()V
    //   979: astore 5
    //   981: aload 5
    //   983: aload_1
    //   984: iload_2
    //   985: aaload
    //   986: invokevirtual 204	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   989: pop
    //   990: aload 5
    //   992: ldc_w 402
    //   995: invokevirtual 204	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   998: pop
    //   999: aload_0
    //   1000: aload 5
    //   1002: invokevirtual 212	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1005: invokevirtual 406	net/geekstools/floatshort/PRO/Category_Unlimited_Time:getFileStreamPath	(Ljava/lang/String;)Ljava/io/File;
    //   1008: invokevirtual 411	java/io/File:exists	()Z
    //   1011: ifeq +15 -> 1026
    //   1014: aload_0
    //   1015: iconst_1
    //   1016: putfield 91	net/geekstools/floatshort/PRO/Category_Unlimited_Time:showNotificationDot	Z
    //   1019: aload_0
    //   1020: aload_1
    //   1021: iload_2
    //   1022: aaload
    //   1023: putfield 413	net/geekstools/floatshort/PRO/Category_Unlimited_Time:notificationPackage	Ljava/lang/String;
    //   1026: iload_2
    //   1027: iconst_1
    //   1028: iadd
    //   1029: istore_2
    //   1030: goto -83 -> 947
    //   1033: aload_0
    //   1034: getfield 268	net/geekstools/floatshort/PRO/Category_Unlimited_Time:one	Lnet/geekstools/imageview/customshapes/ShapesImage;
    //   1037: aload_0
    //   1038: getfield 115	net/geekstools/floatshort/PRO/Category_Unlimited_Time:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
    //   1041: aload_1
    //   1042: iconst_0
    //   1043: aaload
    //   1044: invokevirtual 417	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:shapedAppIcon	(Ljava/lang/String;)Landroid/graphics/drawable/Drawable;
    //   1047: invokevirtual 420	net/geekstools/imageview/customshapes/ShapesImage:setImageDrawable	(Landroid/graphics/drawable/Drawable;)V
    //   1050: aload_0
    //   1051: getfield 268	net/geekstools/floatshort/PRO/Category_Unlimited_Time:one	Lnet/geekstools/imageview/customshapes/ShapesImage;
    //   1054: aload_0
    //   1055: getfield 115	net/geekstools/floatshort/PRO/Category_Unlimited_Time:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
    //   1058: ldc_w 307
    //   1061: sipush 255
    //   1064: invokevirtual 311	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:readDefaultPreference	(Ljava/lang/String;I)I
    //   1067: invokevirtual 423	net/geekstools/imageview/customshapes/ShapesImage:setImageAlpha	(I)V
    //   1070: goto +11 -> 1081
    //   1073: aload_0
    //   1074: getfield 268	net/geekstools/floatshort/PRO/Category_Unlimited_Time:one	Lnet/geekstools/imageview/customshapes/ShapesImage;
    //   1077: aconst_null
    //   1078: invokevirtual 420	net/geekstools/imageview/customshapes/ShapesImage:setImageDrawable	(Landroid/graphics/drawable/Drawable;)V
    //   1081: aload_0
    //   1082: getfield 271	net/geekstools/floatshort/PRO/Category_Unlimited_Time:two	Lnet/geekstools/imageview/customshapes/ShapesImage;
    //   1085: aload_0
    //   1086: getfield 115	net/geekstools/floatshort/PRO/Category_Unlimited_Time:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
    //   1089: aload_1
    //   1090: iconst_1
    //   1091: aaload
    //   1092: invokevirtual 417	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:shapedAppIcon	(Ljava/lang/String;)Landroid/graphics/drawable/Drawable;
    //   1095: invokevirtual 420	net/geekstools/imageview/customshapes/ShapesImage:setImageDrawable	(Landroid/graphics/drawable/Drawable;)V
    //   1098: aload_0
    //   1099: getfield 271	net/geekstools/floatshort/PRO/Category_Unlimited_Time:two	Lnet/geekstools/imageview/customshapes/ShapesImage;
    //   1102: aload_0
    //   1103: getfield 115	net/geekstools/floatshort/PRO/Category_Unlimited_Time:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
    //   1106: ldc_w 307
    //   1109: sipush 255
    //   1112: invokevirtual 311	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:readDefaultPreference	(Ljava/lang/String;I)I
    //   1115: invokevirtual 423	net/geekstools/imageview/customshapes/ShapesImage:setImageAlpha	(I)V
    //   1118: goto +11 -> 1129
    //   1121: aload_0
    //   1122: getfield 271	net/geekstools/floatshort/PRO/Category_Unlimited_Time:two	Lnet/geekstools/imageview/customshapes/ShapesImage;
    //   1125: aconst_null
    //   1126: invokevirtual 420	net/geekstools/imageview/customshapes/ShapesImage:setImageDrawable	(Landroid/graphics/drawable/Drawable;)V
    //   1129: aload_0
    //   1130: getfield 274	net/geekstools/floatshort/PRO/Category_Unlimited_Time:three	Lnet/geekstools/imageview/customshapes/ShapesImage;
    //   1133: aload_0
    //   1134: getfield 115	net/geekstools/floatshort/PRO/Category_Unlimited_Time:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
    //   1137: aload_1
    //   1138: iconst_2
    //   1139: aaload
    //   1140: invokevirtual 417	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:shapedAppIcon	(Ljava/lang/String;)Landroid/graphics/drawable/Drawable;
    //   1143: invokevirtual 420	net/geekstools/imageview/customshapes/ShapesImage:setImageDrawable	(Landroid/graphics/drawable/Drawable;)V
    //   1146: aload_0
    //   1147: getfield 274	net/geekstools/floatshort/PRO/Category_Unlimited_Time:three	Lnet/geekstools/imageview/customshapes/ShapesImage;
    //   1150: aload_0
    //   1151: getfield 115	net/geekstools/floatshort/PRO/Category_Unlimited_Time:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
    //   1154: ldc_w 307
    //   1157: sipush 255
    //   1160: invokevirtual 311	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:readDefaultPreference	(Ljava/lang/String;I)I
    //   1163: invokevirtual 423	net/geekstools/imageview/customshapes/ShapesImage:setImageAlpha	(I)V
    //   1166: goto +11 -> 1177
    //   1169: aload_0
    //   1170: getfield 274	net/geekstools/floatshort/PRO/Category_Unlimited_Time:three	Lnet/geekstools/imageview/customshapes/ShapesImage;
    //   1173: aconst_null
    //   1174: invokevirtual 420	net/geekstools/imageview/customshapes/ShapesImage:setImageDrawable	(Landroid/graphics/drawable/Drawable;)V
    //   1177: aload_0
    //   1178: getfield 277	net/geekstools/floatshort/PRO/Category_Unlimited_Time:four	Lnet/geekstools/imageview/customshapes/ShapesImage;
    //   1181: aload_0
    //   1182: getfield 115	net/geekstools/floatshort/PRO/Category_Unlimited_Time:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
    //   1185: aload_1
    //   1186: iconst_3
    //   1187: aaload
    //   1188: invokevirtual 417	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:shapedAppIcon	(Ljava/lang/String;)Landroid/graphics/drawable/Drawable;
    //   1191: invokevirtual 420	net/geekstools/imageview/customshapes/ShapesImage:setImageDrawable	(Landroid/graphics/drawable/Drawable;)V
    //   1194: aload_0
    //   1195: getfield 277	net/geekstools/floatshort/PRO/Category_Unlimited_Time:four	Lnet/geekstools/imageview/customshapes/ShapesImage;
    //   1198: aload_0
    //   1199: getfield 115	net/geekstools/floatshort/PRO/Category_Unlimited_Time:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
    //   1202: ldc_w 307
    //   1205: sipush 255
    //   1208: invokevirtual 311	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:readDefaultPreference	(Ljava/lang/String;I)I
    //   1211: invokevirtual 423	net/geekstools/imageview/customshapes/ShapesImage:setImageAlpha	(I)V
    //   1214: goto +11 -> 1225
    //   1217: aload_0
    //   1218: getfield 277	net/geekstools/floatshort/PRO/Category_Unlimited_Time:four	Lnet/geekstools/imageview/customshapes/ShapesImage;
    //   1221: aconst_null
    //   1222: invokevirtual 420	net/geekstools/imageview/customshapes/ShapesImage:setImageDrawable	(Landroid/graphics/drawable/Drawable;)V
    //   1225: iconst_1
    //   1226: aload_0
    //   1227: getfield 251	net/geekstools/floatshort/PRO/Category_Unlimited_Time:categorySize	I
    //   1230: iconst_2
    //   1231: imul
    //   1232: i2f
    //   1233: aload_0
    //   1234: invokevirtual 427	net/geekstools/floatshort/PRO/Category_Unlimited_Time:getResources	()Landroid/content/res/Resources;
    //   1237: invokevirtual 433	android/content/res/Resources:getDisplayMetrics	()Landroid/util/DisplayMetrics;
    //   1240: invokestatic 439	android/util/TypedValue:applyDimension	(IFLandroid/util/DisplayMetrics;)F
    //   1243: f2i
    //   1244: istore_2
    //   1245: aload_0
    //   1246: aload_0
    //   1247: aload_0
    //   1248: getfield 149	net/geekstools/floatshort/PRO/Category_Unlimited_Time:categoryName	[Ljava/lang/String;
    //   1251: iload_3
    //   1252: aaload
    //   1253: iconst_0
    //   1254: invokevirtual 443	net/geekstools/floatshort/PRO/Category_Unlimited_Time:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   1257: putfield 445	net/geekstools/floatshort/PRO/Category_Unlimited_Time:sharedPrefPosition	Landroid/content/SharedPreferences;
    //   1260: aload_0
    //   1261: aload_0
    //   1262: getfield 87	net/geekstools/floatshort/PRO/Category_Unlimited_Time:xInit	I
    //   1265: bipush 13
    //   1267: iadd
    //   1268: putfield 87	net/geekstools/floatshort/PRO/Category_Unlimited_Time:xInit	I
    //   1271: aload_0
    //   1272: aload_0
    //   1273: getfield 89	net/geekstools/floatshort/PRO/Category_Unlimited_Time:yInit	I
    //   1276: bipush 13
    //   1278: iadd
    //   1279: putfield 89	net/geekstools/floatshort/PRO/Category_Unlimited_Time:yInit	I
    //   1282: aload_0
    //   1283: aload_0
    //   1284: getfield 445	net/geekstools/floatshort/PRO/Category_Unlimited_Time:sharedPrefPosition	Landroid/content/SharedPreferences;
    //   1287: ldc_w 447
    //   1290: aload_0
    //   1291: getfield 87	net/geekstools/floatshort/PRO/Category_Unlimited_Time:xInit	I
    //   1294: invokeinterface 450 3 0
    //   1299: putfield 452	net/geekstools/floatshort/PRO/Category_Unlimited_Time:xPos	I
    //   1302: aload_0
    //   1303: aload_0
    //   1304: getfield 445	net/geekstools/floatshort/PRO/Category_Unlimited_Time:sharedPrefPosition	Landroid/content/SharedPreferences;
    //   1307: ldc_w 454
    //   1310: aload_0
    //   1311: getfield 89	net/geekstools/floatshort/PRO/Category_Unlimited_Time:yInit	I
    //   1314: invokeinterface 450 3 0
    //   1319: putfield 456	net/geekstools/floatshort/PRO/Category_Unlimited_Time:yPos	I
    //   1322: aload_0
    //   1323: getfield 139	net/geekstools/floatshort/PRO/Category_Unlimited_Time:params	[Landroid/view/WindowManager$LayoutParams;
    //   1326: iload_3
    //   1327: aload_0
    //   1328: getfield 115	net/geekstools/floatshort/PRO/Category_Unlimited_Time:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
    //   1331: iload_2
    //   1332: aload_0
    //   1333: getfield 452	net/geekstools/floatshort/PRO/Category_Unlimited_Time:xPos	I
    //   1336: aload_0
    //   1337: getfield 456	net/geekstools/floatshort/PRO/Category_Unlimited_Time:yPos	I
    //   1340: invokevirtual 460	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:normalLayoutParams	(III)Landroid/view/WindowManager$LayoutParams;
    //   1343: aastore
    //   1344: aload_0
    //   1345: getfield 228	net/geekstools/floatshort/PRO/Category_Unlimited_Time:windowManager	Landroid/view/WindowManager;
    //   1348: aload_0
    //   1349: getfield 145	net/geekstools/floatshort/PRO/Category_Unlimited_Time:floatingView	[Landroid/view/ViewGroup;
    //   1352: iload_3
    //   1353: aaload
    //   1354: aload_0
    //   1355: getfield 139	net/geekstools/floatshort/PRO/Category_Unlimited_Time:params	[Landroid/view/WindowManager$LayoutParams;
    //   1358: iload_3
    //   1359: aaload
    //   1360: invokeinterface 464 3 0
    //   1365: ldc 2
    //   1367: invokevirtual 200	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   1370: astore_1
    //   1371: new 466	android/content/IntentFilter
    //   1374: dup
    //   1375: invokespecial 467	android/content/IntentFilter:<init>	()V
    //   1378: astore 5
    //   1380: new 187	java/lang/StringBuilder
    //   1383: dup
    //   1384: invokespecial 188	java/lang/StringBuilder:<init>	()V
    //   1387: astore 6
    //   1389: aload 6
    //   1391: ldc_w 469
    //   1394: invokevirtual 204	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1397: pop
    //   1398: aload 6
    //   1400: aload_1
    //   1401: invokevirtual 204	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1404: pop
    //   1405: aload 5
    //   1407: aload 6
    //   1409: invokevirtual 212	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1412: invokevirtual 472	android/content/IntentFilter:addAction	(Ljava/lang/String;)V
    //   1415: new 187	java/lang/StringBuilder
    //   1418: dup
    //   1419: invokespecial 188	java/lang/StringBuilder:<init>	()V
    //   1422: astore 6
    //   1424: aload 6
    //   1426: ldc_w 474
    //   1429: invokevirtual 204	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1432: pop
    //   1433: aload 6
    //   1435: aload_1
    //   1436: invokevirtual 204	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1439: pop
    //   1440: aload 5
    //   1442: aload 6
    //   1444: invokevirtual 212	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1447: invokevirtual 472	android/content/IntentFilter:addAction	(Ljava/lang/String;)V
    //   1450: new 187	java/lang/StringBuilder
    //   1453: dup
    //   1454: invokespecial 188	java/lang/StringBuilder:<init>	()V
    //   1457: astore 6
    //   1459: aload 6
    //   1461: ldc_w 476
    //   1464: invokevirtual 204	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1467: pop
    //   1468: aload 6
    //   1470: aload_1
    //   1471: invokevirtual 204	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1474: pop
    //   1475: aload 5
    //   1477: aload 6
    //   1479: invokevirtual 212	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1482: invokevirtual 472	android/content/IntentFilter:addAction	(Ljava/lang/String;)V
    //   1485: new 187	java/lang/StringBuilder
    //   1488: dup
    //   1489: invokespecial 188	java/lang/StringBuilder:<init>	()V
    //   1492: astore 6
    //   1494: aload 6
    //   1496: ldc_w 478
    //   1499: invokevirtual 204	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1502: pop
    //   1503: aload 6
    //   1505: aload_1
    //   1506: invokevirtual 204	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1509: pop
    //   1510: aload 5
    //   1512: aload 6
    //   1514: invokevirtual 212	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1517: invokevirtual 472	android/content/IntentFilter:addAction	(Ljava/lang/String;)V
    //   1520: new 187	java/lang/StringBuilder
    //   1523: dup
    //   1524: invokespecial 188	java/lang/StringBuilder:<init>	()V
    //   1527: astore 6
    //   1529: aload 6
    //   1531: ldc_w 480
    //   1534: invokevirtual 204	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1537: pop
    //   1538: aload 6
    //   1540: aload_1
    //   1541: invokevirtual 204	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1544: pop
    //   1545: aload 5
    //   1547: aload 6
    //   1549: invokevirtual 212	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1552: invokevirtual 472	android/content/IntentFilter:addAction	(Ljava/lang/String;)V
    //   1555: aload 5
    //   1557: ldc_w 482
    //   1560: invokevirtual 472	android/content/IntentFilter:addAction	(Ljava/lang/String;)V
    //   1563: aload 5
    //   1565: ldc_w 484
    //   1568: invokevirtual 472	android/content/IntentFilter:addAction	(Ljava/lang/String;)V
    //   1571: aload 5
    //   1573: ldc_w 486
    //   1576: invokevirtual 472	android/content/IntentFilter:addAction	(Ljava/lang/String;)V
    //   1579: aload 5
    //   1581: ldc_w 488
    //   1584: invokevirtual 472	android/content/IntentFilter:addAction	(Ljava/lang/String;)V
    //   1587: aload_0
    //   1588: new 6	net/geekstools/floatshort/PRO/Category_Unlimited_Time$1
    //   1591: dup
    //   1592: aload_0
    //   1593: aload_1
    //   1594: iload_3
    //   1595: invokespecial 491	net/geekstools/floatshort/PRO/Category_Unlimited_Time$1:<init>	(Lnet/geekstools/floatshort/PRO/Category_Unlimited_Time;Ljava/lang/String;I)V
    //   1598: putfield 377	net/geekstools/floatshort/PRO/Category_Unlimited_Time:broadcastReceiver	Landroid/content/BroadcastReceiver;
    //   1601: aload_0
    //   1602: aload_0
    //   1603: getfield 377	net/geekstools/floatshort/PRO/Category_Unlimited_Time:broadcastReceiver	Landroid/content/BroadcastReceiver;
    //   1606: aload 5
    //   1608: invokevirtual 495	net/geekstools/floatshort/PRO/Category_Unlimited_Time:registerReceiver	(Landroid/content/BroadcastReceiver;Landroid/content/IntentFilter;)Landroid/content/Intent;
    //   1611: pop
    //   1612: aload_0
    //   1613: getfield 145	net/geekstools/floatshort/PRO/Category_Unlimited_Time:floatingView	[Landroid/view/ViewGroup;
    //   1616: iload_3
    //   1617: aaload
    //   1618: new 18	net/geekstools/floatshort/PRO/Category_Unlimited_Time$2
    //   1621: dup
    //   1622: aload_0
    //   1623: iload_3
    //   1624: aload_1
    //   1625: invokespecial 498	net/geekstools/floatshort/PRO/Category_Unlimited_Time$2:<init>	(Lnet/geekstools/floatshort/PRO/Category_Unlimited_Time;ILjava/lang/String;)V
    //   1628: invokevirtual 502	android/view/ViewGroup:setOnTouchListener	(Landroid/view/View$OnTouchListener;)V
    //   1631: aload_0
    //   1632: getfield 145	net/geekstools/floatshort/PRO/Category_Unlimited_Time:floatingView	[Landroid/view/ViewGroup;
    //   1635: iload_3
    //   1636: aaload
    //   1637: new 24	net/geekstools/floatshort/PRO/Category_Unlimited_Time$3
    //   1640: dup
    //   1641: aload_0
    //   1642: iload_3
    //   1643: aload_1
    //   1644: invokespecial 503	net/geekstools/floatshort/PRO/Category_Unlimited_Time$3:<init>	(Lnet/geekstools/floatshort/PRO/Category_Unlimited_Time;ILjava/lang/String;)V
    //   1647: invokevirtual 507	android/view/ViewGroup:setOnClickListener	(Landroid/view/View$OnClickListener;)V
    //   1650: aload_0
    //   1651: getfield 155	net/geekstools/floatshort/PRO/Category_Unlimited_Time:notificationDot	[Lnet/geekstools/imageview/customshapes/ShapesImage;
    //   1654: iload_3
    //   1655: aaload
    //   1656: new 26	net/geekstools/floatshort/PRO/Category_Unlimited_Time$4
    //   1659: dup
    //   1660: aload_0
    //   1661: iload_3
    //   1662: invokespecial 510	net/geekstools/floatshort/PRO/Category_Unlimited_Time$4:<init>	(Lnet/geekstools/floatshort/PRO/Category_Unlimited_Time;I)V
    //   1665: invokevirtual 511	net/geekstools/imageview/customshapes/ShapesImage:setOnClickListener	(Landroid/view/View$OnClickListener;)V
    //   1668: aload_0
    //   1669: getfield 155	net/geekstools/floatshort/PRO/Category_Unlimited_Time:notificationDot	[Lnet/geekstools/imageview/customshapes/ShapesImage;
    //   1672: iload_3
    //   1673: aaload
    //   1674: new 28	net/geekstools/floatshort/PRO/Category_Unlimited_Time$5
    //   1677: dup
    //   1678: aload_0
    //   1679: iload_3
    //   1680: invokespecial 512	net/geekstools/floatshort/PRO/Category_Unlimited_Time$5:<init>	(Lnet/geekstools/floatshort/PRO/Category_Unlimited_Time;I)V
    //   1683: invokevirtual 516	net/geekstools/imageview/customshapes/ShapesImage:setOnLongClickListener	(Landroid/view/View$OnLongClickListener;)V
    //   1686: aload_0
    //   1687: getfield 91	net/geekstools/floatshort/PRO/Category_Unlimited_Time:showNotificationDot	Z
    //   1690: ifeq +27 -> 1717
    //   1693: aload_0
    //   1694: new 231	android/content/Intent
    //   1697: dup
    //   1698: ldc_w 486
    //   1701: invokespecial 518	android/content/Intent:<init>	(Ljava/lang/String;)V
    //   1704: ldc_w 520
    //   1707: aload_0
    //   1708: getfield 413	net/geekstools/floatshort/PRO/Category_Unlimited_Time:notificationPackage	Ljava/lang/String;
    //   1711: invokevirtual 524	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   1714: invokevirtual 528	net/geekstools/floatshort/PRO/Category_Unlimited_Time:sendBroadcast	(Landroid/content/Intent;)V
    //   1717: aload_0
    //   1718: getfield 115	net/geekstools/floatshort/PRO/Category_Unlimited_Time:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
    //   1721: invokevirtual 531	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:serviceMode	()I
    //   1724: ireturn
    //   1725: astore_1
    //   1726: aload_1
    //   1727: invokevirtual 363	java/lang/Exception:printStackTrace	()V
    //   1730: iconst_2
    //   1731: ireturn
    //   1732: astore 5
    //   1734: goto -661 -> 1073
    //   1737: astore 5
    //   1739: goto -618 -> 1121
    //   1742: astore 5
    //   1744: goto -575 -> 1169
    //   1747: astore_1
    //   1748: goto -531 -> 1217
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1751	0	this	Category_Unlimited_Time
    //   0	1751	1	paramIntent	Intent
    //   0	1751	2	paramInt1	int
    //   0	1751	3	paramInt2	int
    //   611	3	4	bool	boolean
    //   3	1604	5	localObject1	Object
    //   1732	1	5	localException1	Exception
    //   1737	1	5	localException2	Exception
    //   1742	1	5	localException3	Exception
    //   12	1536	6	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   618	633	690	finally
    //   695	699	690	finally
    //   618	633	694	java/lang/Exception
    //   595	613	851	java/lang/Exception
    //   633	681	851	java/lang/Exception
    //   681	687	851	java/lang/Exception
    //   699	747	851	java/lang/Exception
    //   750	802	851	java/lang/Exception
    //   802	804	851	java/lang/Exception
    //   804	848	851	java/lang/Exception
    //   873	888	891	java/lang/Exception
    //   65	105	1725	java/lang/Exception
    //   1033	1070	1732	java/lang/Exception
    //   1081	1118	1737	java/lang/Exception
    //   1129	1166	1742	java/lang/Exception
    //   1177	1214	1747	java/lang/Exception
  }
}
