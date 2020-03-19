package flipboard.toolbox;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.os.StatFs;
import android.provider.Settings.Secure;
import android.text.Html;
import android.text.Spanned;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Patterns;
import android.util.Property;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class a
{
  public static DisplayMetrics a;
  public static String b;
  public static boolean c = false;
  public static final Handler d = new Handler(Looper.getMainLooper());
  private static int e = Integer.MIN_VALUE;
  private static int f = Integer.MIN_VALUE;
  private static int g = Integer.MIN_VALUE;
  private static int h = Integer.MIN_VALUE;
  private static int i = Integer.MIN_VALUE;
  
  public static int a(float paramFloat)
  {
    return Math.round(paramFloat * c() / 375.0F);
  }
  
  public static int a(float paramFloat, int paramInt)
  {
    return Math.round(paramFloat * paramInt / 667.0F);
  }
  
  public static int a(float paramFloat, Context paramContext)
  {
    return (int)(paramFloat * paramContext.getResources().getDisplayMetrics().density + 0.5F);
  }
  
  public static int a(int paramInt1, int paramInt2)
  {
    float f1 = Color.alpha(paramInt1) / 255.0F;
    float f2 = 1.0F - f1;
    float f3 = Color.red(paramInt1);
    float f4 = Color.red(paramInt2);
    float f5 = Color.green(paramInt1);
    float f6 = Color.green(paramInt2);
    float f7 = Color.blue(paramInt1);
    float f8 = Color.blue(paramInt2);
    return Color.rgb((int)(f3 * f1 + f4 * f2), (int)(f5 * f1 + f6 * f2), (int)(f7 * f1 + f8 * f2));
  }
  
  public static int a(int paramInt1, int paramInt2, float paramFloat)
  {
    float f1 = 1.0F - paramFloat;
    int j = (int)(Color.red(paramInt1) * f1 + Color.red(paramInt2) * paramFloat);
    int k = (int)(Color.green(paramInt1) * f1 + Color.green(paramInt2) * paramFloat);
    int m = (int)(Color.blue(paramInt1) * f1 + Color.blue(paramInt2) * paramFloat);
    return Color.argb((int)(Color.alpha(paramInt1) - (Color.alpha(paramInt1) - Color.alpha(paramInt2)) * paramFloat), j, k, m);
  }
  
  public static int a(int paramInt, Context paramContext)
  {
    float f1 = paramContext.getResources().getDisplayMetrics().density;
    return (int)(paramInt / f1 + 0.5F);
  }
  
  public static int a(Context paramContext, float paramFloat)
  {
    return Math.round(TypedValue.applyDimension(1, paramFloat, paramContext.getResources().getDisplayMetrics()));
  }
  
  public static int a(Context paramContext, int paramInt)
  {
    if (h == Integer.MIN_VALUE)
    {
      paramContext = paramContext.getResources();
      int j = paramContext.getIdentifier("navigation_bar_height", "dimen", "android");
      int k = 0;
      if (j > 0) {
        j = paramContext.getDimensionPixelSize(j);
      } else {
        j = 0;
      }
      h = j;
      int m = paramContext.getIdentifier("navigation_bar_height_landscape", "dimen", "android");
      j = k;
      if (m > 0) {
        j = paramContext.getDimensionPixelSize(m);
      }
      if (j == 0) {
        i = h;
      } else {
        i = j;
      }
    }
    if (paramInt == 1) {
      return h;
    }
    return i;
  }
  
  public static long a(File paramFile)
  {
    if ((paramFile != null) && (paramFile.exists())) {
      return d(paramFile.getAbsolutePath());
    }
    return 0L;
  }
  
  public static Intent a(Context paramContext, String paramString1, String paramString2, List<String> paramList)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setData(Uri.parse(paramString1));
    Object localObject = paramContext.getPackageManager().queryIntentActivities(localIntent, 65600);
    paramContext = new ArrayList();
    localObject = ((List)localObject).iterator();
    int j;
    for (;;)
    {
      boolean bool = ((Iterator)localObject).hasNext();
      j = 1;
      if (!bool) {
        break;
      }
      ResolveInfo localResolveInfo = (ResolveInfo)((Iterator)localObject).next();
      if ((localResolveInfo.filter != null) && (localResolveInfo.filter.countDataPaths() > 1) && ((paramList == null) || (!paramList.contains(localResolveInfo.activityInfo.packageName)))) {
        paramContext.add(localResolveInfo);
      }
    }
    switch (paramContext.size())
    {
    default: 
      localIntent.setComponent(new ComponentName(((ResolveInfo)paramContext.get(0)).activityInfo.packageName, ((ResolveInfo)paramContext.get(0)).activityInfo.name));
      paramList = new Intent[paramContext.size() - 1];
      break;
    case 1: 
      localIntent.setComponent(new ComponentName(((ResolveInfo)paramContext.get(0)).activityInfo.packageName, ((ResolveInfo)paramContext.get(0)).activityInfo.name));
      return localIntent;
    case 0: 
      return null;
    }
    while (j < paramContext.size())
    {
      localObject = new Intent("android.intent.action.VIEW");
      ((Intent)localObject).setData(Uri.parse(paramString1));
      ((Intent)localObject).setComponent(new ComponentName(((ResolveInfo)paramContext.get(j)).activityInfo.packageName, ((ResolveInfo)paramContext.get(j)).activityInfo.name));
      paramList[(j - 1)] = localObject;
      j += 1;
    }
    paramContext = Intent.createChooser(localIntent, paramString2);
    paramContext.putExtra("android.intent.extra.INITIAL_INTENTS", paramList);
    return paramContext;
  }
  
  public static <T extends View> T a(View paramView, Class<T> paramClass)
  {
    for (;;)
    {
      paramView = paramView.getParent();
      if (paramClass.isInstance(paramView)) {
        return (View)paramView;
      }
      if (!(paramView instanceof View)) {
        break;
      }
      paramView = (View)paramView;
    }
    return null;
  }
  
  public static File a(Context paramContext, String paramString1, String paramString2)
  {
    paramContext = paramContext.getFilesDir();
    if (paramContext != null)
    {
      paramContext = new File(new File(paramContext, paramString1), paramString2);
      if (!paramContext.getParentFile().exists()) {
        paramContext.getParentFile().mkdirs();
      }
      m.b(paramContext.getParentFile());
    }
    try
    {
      boolean bool = paramContext.createNewFile();
      if (bool) {
        return paramContext;
      }
      return null;
    }
    catch (IOException paramContext) {}
    return null;
  }
  
  public static String a(String paramString1, String paramString2)
  {
    paramString2 = paramString2.replace(" ", "");
    StringBuilder localStringBuilder = new StringBuilder();
    if (paramString1 != null) {
      localStringBuilder.append(paramString1);
    }
    localStringBuilder.append(paramString2.toLowerCase());
    return localStringBuilder.toString();
  }
  
  public static List<View> a(View paramView1, View paramView2)
  {
    ArrayList localArrayList = new ArrayList();
    if ((paramView1.getParent() instanceof ViewGroup))
    {
      ViewGroup localViewGroup = (ViewGroup)paramView1.getParent();
      int j = 0;
      while (j < localViewGroup.getChildCount())
      {
        View localView = localViewGroup.getChildAt(j);
        if (localView != paramView1) {
          localArrayList.add(localView);
        }
        j += 1;
      }
      if (localViewGroup != paramView2) {
        localArrayList.addAll(a(localViewGroup, paramView2));
      }
    }
    return localArrayList;
  }
  
  public static Set<String> a(String paramString, int paramInt)
  {
    HashSet localHashSet = new HashSet();
    paramString = Patterns.WEB_URL.matcher(paramString);
    while (paramString.find()) {
      if (paramString.group().length() >= paramInt) {
        localHashSet.add(paramString.group());
      }
    }
    return localHashSet;
  }
  
  public static void a(int paramInt, Runnable paramRunnable)
  {
    d.postDelayed(paramRunnable, paramInt);
  }
  
  public static void a(Activity paramActivity)
  {
    if (paramActivity != null)
    {
      InputMethodManager localInputMethodManager = (InputMethodManager)paramActivity.getSystemService("input_method");
      paramActivity = paramActivity.getCurrentFocus();
      if (paramActivity != null) {
        localInputMethodManager.hideSoftInputFromWindow(paramActivity.getWindowToken(), 2);
      }
    }
  }
  
  public static void a(Context paramContext, Paint paramPaint)
  {
    paramPaint.setFlags(paramPaint.getFlags() | 0x1);
    paramPaint.setHinting(1);
  }
  
  public static void a(Context paramContext, Uri paramUri)
  {
    paramContext.startActivity(new Intent("android.intent.action.VIEW", paramUri));
  }
  
  public static void a(Context paramContext, View paramView, int paramInt)
  {
    ((InputMethodManager)paramContext.getSystemService("input_method")).showSoftInput(paramView, paramInt);
  }
  
  public static void a(Context paramContext, String paramString)
  {
    ((ClipboardManager)paramContext.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("From Flipboard", paramString));
  }
  
  public static void a(View paramView, int paramInt)
  {
    if (paramView.getLayerType() != paramInt) {
      paramView.setLayerType(paramInt, null);
    }
  }
  
  public static void a(View paramView1, View paramView2, boolean paramBoolean)
  {
    if ((paramView1 != paramView2) && ((paramView1.getParent() instanceof ViewGroup)))
    {
      paramView1 = (ViewGroup)paramView1.getParent();
      paramView1.setClipChildren(paramBoolean);
      paramView1.setClipToPadding(paramBoolean);
      a(paramView1, paramView2, paramBoolean);
    }
  }
  
  public static void a(WebView paramWebView, String paramString)
  {
    if (Build.VERSION.SDK_INT >= 19)
    {
      paramWebView.evaluateJavascript(paramString, null);
      return;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("javascript:");
    localStringBuilder.append(paramString);
    paramWebView.loadUrl(localStringBuilder.toString());
  }
  
  public static void a(TextView paramTextView, int paramInt)
  {
    if (m.b(paramTextView.getResources().getString(paramInt)))
    {
      paramTextView.setVisibility(8);
      return;
    }
    paramTextView.setVisibility(0);
    paramTextView.setText(paramInt);
  }
  
  public static void a(TextView paramTextView, CharSequence paramCharSequence)
  {
    if (m.a(paramCharSequence))
    {
      paramTextView.setVisibility(8);
      return;
    }
    paramTextView.setVisibility(0);
    paramTextView.setText(paramCharSequence);
  }
  
  public static void a(Runnable paramRunnable)
  {
    d.post(paramRunnable);
  }
  
  public static boolean a()
  {
    return Thread.currentThread() == Looper.getMainLooper().getThread();
  }
  
  public static boolean a(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    try
    {
      int j = localPackageManager.getApplicationInfo(paramContext.getPackageName(), 128).flags;
      return (j & 0x1) != 0;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public static boolean a(Context paramContext, Intent paramIntent)
  {
    return paramContext.getPackageManager().resolveActivity(paramIntent, 65536) != null;
  }
  
  public static boolean a(View paramView)
  {
    int[] arrayOfInt = new int[2];
    paramView.getLocationOnScreen(arrayOfInt);
    boolean bool2 = false;
    int j = arrayOfInt[0];
    int k = arrayOfInt[1];
    int m = c();
    if ((j >= 0) && (j < m)) {
      j = 1;
    } else {
      j = 0;
    }
    m = b();
    if ((k >= 0) && (k < m)) {
      k = 1;
    } else {
      k = 0;
    }
    boolean bool1 = bool2;
    if (k != 0)
    {
      bool1 = bool2;
      if (j != 0) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public static boolean a(String paramString)
  {
    boolean bool = a();
    if (bool) {
      if (!c)
      {
        Log.e("flipboard", String.format("Only a background thread is allowed to do that: %s", new Object[] { paramString }));
      }
      else
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Only a background thread is allowed to do that - called from ");
        localStringBuilder.append(paramString);
        localStringBuilder.append("\ncurrentThread = ");
        localStringBuilder.append(Thread.currentThread().getName());
        localStringBuilder.append("\nMain Looper Thread = ");
        localStringBuilder.append(Looper.getMainLooper().getThread().getName());
        throw new RuntimeException(localStringBuilder.toString());
      }
    }
    return bool ^ true;
  }
  
  public static int[] a(View paramView1, View paramView2, int[] paramArrayOfInt)
  {
    int[] arrayOfInt = paramArrayOfInt;
    if (paramArrayOfInt == null) {
      arrayOfInt = new int[2];
    }
    paramView2.getLocationOnScreen(arrayOfInt);
    int j = arrayOfInt[0];
    int k = arrayOfInt[1];
    paramView1.getLocationOnScreen(arrayOfInt);
    int m = arrayOfInt[0];
    int n = arrayOfInt[1];
    arrayOfInt[0] = (m - j);
    arrayOfInt[1] = (n - k);
    return arrayOfInt;
  }
  
  public static float b(int paramInt1, int paramInt2)
  {
    float f1 = 2001.0F / paramInt2;
    float f2 = 1125.0F / paramInt1;
    if ((f1 >= 1.0F) && (f2 >= 1.0F)) {
      return 1.0F;
    }
    return Math.min(f1, f2);
  }
  
  public static int b()
  {
    return a.heightPixels;
  }
  
  public static int b(float paramFloat)
  {
    return a(paramFloat / 3.0F);
  }
  
  public static int b(float paramFloat, int paramInt)
  {
    return a(paramFloat / 3.0F, paramInt);
  }
  
  public static int b(Context paramContext)
  {
    return b() - c(paramContext);
  }
  
  public static ObjectAnimator b(View paramView)
  {
    Property localProperty = View.TRANSLATION_X;
    Keyframe localKeyframe1 = Keyframe.ofFloat(0.0F, 0.0F);
    float f1 = -10;
    Keyframe localKeyframe2 = Keyframe.ofFloat(0.1F, f1);
    float f2 = 10;
    return ObjectAnimator.ofPropertyValuesHolder(paramView, new PropertyValuesHolder[] { PropertyValuesHolder.ofKeyframe(localProperty, new Keyframe[] { localKeyframe1, localKeyframe2, Keyframe.ofFloat(0.26F, f2), Keyframe.ofFloat(0.42F, f1), Keyframe.ofFloat(0.58F, f2), Keyframe.ofFloat(0.74F, f1), Keyframe.ofFloat(0.9F, f2), Keyframe.ofFloat(1.0F, 0.0F) }) }).setDuration(500L);
  }
  
  @TargetApi(19)
  public static void b(Activity paramActivity)
  {
    if (Build.VERSION.SDK_INT < 16)
    {
      paramActivity.getWindow().setFlags(1024, 1024);
      return;
    }
    int j = 1798;
    if (Build.VERSION.SDK_INT >= 19) {
      j = 3846;
    }
    paramActivity.getWindow().getDecorView().setSystemUiVisibility(j);
  }
  
  public static boolean b(Context paramContext, String paramString)
  {
    if (paramString != null) {
      try
      {
        paramContext = paramContext.getPackageManager().getInstalledPackages(0).iterator();
        while (paramContext.hasNext())
        {
          boolean bool = ((PackageInfo)paramContext.next()).packageName.equals(paramString);
          if (bool) {
            return true;
          }
        }
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
      }
    }
    return false;
  }
  
  public static boolean b(String paramString)
  {
    boolean bool = a();
    if (!bool)
    {
      if (!c)
      {
        Log.e("flipboard", String.format("Only the UI thread is allowed to do that: %s", new Object[] { paramString }));
        return bool;
      }
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Only the UI thread is allowed to do that - called from ");
      localStringBuilder.append(paramString);
      localStringBuilder.append("\ncurrentThread = ");
      localStringBuilder.append(Thread.currentThread().getName());
      localStringBuilder.append("\nMain Looper Thread = ");
      localStringBuilder.append(Looper.getMainLooper().getThread().getName());
      throw new RuntimeException(localStringBuilder.toString());
    }
    return bool;
  }
  
  public static int c()
  {
    return a.widthPixels;
  }
  
  public static int c(Context paramContext)
  {
    if (g == Integer.MIN_VALUE)
    {
      paramContext = paramContext.getResources();
      int j = 0;
      int k = paramContext.getIdentifier("status_bar_height", "dimen", "android");
      if (k > 0) {
        j = paramContext.getDimensionPixelSize(k);
      }
      g = j;
    }
    return g;
  }
  
  public static Spanned c(String paramString)
  {
    if (Build.VERSION.SDK_INT >= 24) {
      return Html.fromHtml(paramString, 0);
    }
    return Html.fromHtml(paramString);
  }
  
  @TargetApi(16)
  public static void c(Activity paramActivity)
  {
    if (Build.VERSION.SDK_INT < 16)
    {
      paramActivity.getWindow().clearFlags(1024);
      return;
    }
    paramActivity.getWindow().getDecorView().setSystemUiVisibility(256);
  }
  
  public static int d(Activity paramActivity)
  {
    if (e == Integer.MIN_VALUE) {
      g(paramActivity);
    }
    return e;
  }
  
  public static long d(String paramString)
  {
    paramString = new StatFs(paramString);
    long l1;
    long l2;
    if (Build.VERSION.SDK_INT > 17)
    {
      l1 = paramString.getFreeBlocksLong();
      l2 = paramString.getBlockSizeLong();
    }
    else
    {
      l1 = paramString.getFreeBlocks();
      l2 = paramString.getBlockSize();
    }
    return l1 * l2;
  }
  
  public static String d(Context paramContext)
  {
    String str = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
    if (str != null)
    {
      paramContext = str;
      if (!str.equals("9774d56d682e549c")) {}
    }
    else
    {
      paramContext = UUID.randomUUID().toString();
    }
    return paramContext;
  }
  
  public static int e(Activity paramActivity)
  {
    if (f == Integer.MIN_VALUE) {
      g(paramActivity);
    }
    return f;
  }
  
  /* Error */
  public static android.util.Pair<String, Boolean> e(Context paramContext)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 714	flipboard/toolbox/a:b	Ljava/lang/String;
    //   6: astore 4
    //   8: iconst_0
    //   9: istore_3
    //   10: iload_3
    //   11: istore_2
    //   12: aload 4
    //   14: ifnonnull +93 -> 107
    //   17: getstatic 448	android/os/Build$VERSION:SDK_INT	I
    //   20: istore_1
    //   21: iload_1
    //   22: bipush 17
    //   24: if_icmplt +8 -> 32
    //   27: iconst_1
    //   28: istore_1
    //   29: goto +5 -> 34
    //   32: iconst_0
    //   33: istore_1
    //   34: iload_1
    //   35: ifeq +10 -> 45
    //   38: aload_0
    //   39: invokestatic 716	flipboard/toolbox/a:g	(Landroid/content/Context;)Ljava/lang/String;
    //   42: putstatic 714	flipboard/toolbox/a:b	Ljava/lang/String;
    //   45: getstatic 714	flipboard/toolbox/a:b	Ljava/lang/String;
    //   48: ifnonnull +10 -> 58
    //   51: aload_0
    //   52: invokestatic 718	flipboard/toolbox/a:h	(Landroid/content/Context;)Ljava/lang/String;
    //   55: putstatic 714	flipboard/toolbox/a:b	Ljava/lang/String;
    //   58: iload_3
    //   59: istore_2
    //   60: getstatic 714	flipboard/toolbox/a:b	Ljava/lang/String;
    //   63: ifnull +44 -> 107
    //   66: getstatic 714	flipboard/toolbox/a:b	Ljava/lang/String;
    //   69: astore_0
    //   70: getstatic 714	flipboard/toolbox/a:b	Ljava/lang/String;
    //   73: getstatic 724	java/text/Normalizer$Form:NFD	Ljava/text/Normalizer$Form;
    //   76: invokestatic 730	java/text/Normalizer:normalize	(Ljava/lang/CharSequence;Ljava/text/Normalizer$Form;)Ljava/lang/String;
    //   79: putstatic 714	flipboard/toolbox/a:b	Ljava/lang/String;
    //   82: getstatic 714	flipboard/toolbox/a:b	Ljava/lang/String;
    //   85: ldc_w 732
    //   88: ldc_w 285
    //   91: invokevirtual 735	java/lang/String:replaceAll	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   94: putstatic 714	flipboard/toolbox/a:b	Ljava/lang/String;
    //   97: aload_0
    //   98: getstatic 714	flipboard/toolbox/a:b	Ljava/lang/String;
    //   101: invokevirtual 643	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   104: iconst_1
    //   105: ixor
    //   106: istore_2
    //   107: new 737	android/util/Pair
    //   110: dup
    //   111: getstatic 714	flipboard/toolbox/a:b	Ljava/lang/String;
    //   114: iload_2
    //   115: invokestatic 743	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   118: invokespecial 746	android/util/Pair:<init>	(Ljava/lang/Object;Ljava/lang/Object;)V
    //   121: astore_0
    //   122: ldc 2
    //   124: monitorexit
    //   125: aload_0
    //   126: areturn
    //   127: astore_0
    //   128: ldc 2
    //   130: monitorexit
    //   131: aload_0
    //   132: athrow
    //   133: astore 4
    //   135: goto -90 -> 45
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	138	0	paramContext	Context
    //   20	15	1	j	int
    //   11	104	2	bool1	boolean
    //   9	50	3	bool2	boolean
    //   6	7	4	str	String
    //   133	1	4	localException	Exception
    // Exception table:
    //   from	to	target	type
    //   3	8	127	finally
    //   17	21	127	finally
    //   38	45	127	finally
    //   45	58	127	finally
    //   60	107	127	finally
    //   107	122	127	finally
    //   38	45	133	java/lang/Exception
  }
  
  public static int f(Activity paramActivity)
  {
    int k = paramActivity.getWindowManager().getDefaultDisplay().getRotation();
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    paramActivity.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    int m = localDisplayMetrics.widthPixels;
    int n = localDisplayMetrics.heightPixels;
    int j = 0;
    if (((k != 0) && (k != 2)) || ((n <= m) && (((k != 1) && (k != 3)) || (m <= n)))) {}
    switch (k)
    {
    default: 
      return 0;
      switch (k)
      {
      }
    case 3: 
      return 1;
    case 2: 
      return 8;
    case 1: 
      j = 9;
    }
    return j;
  }
  
  public static Context f(Context paramContext)
  {
    if ((!(paramContext instanceof Activity)) && ((paramContext instanceof ContextWrapper))) {
      return f(((ContextWrapper)paramContext).getBaseContext());
    }
    return paramContext;
  }
  
  private static String g(Context paramContext)
  {
    if (Build.VERSION.SDK_INT >= 17) {
      return WebSettings.getDefaultUserAgent(paramContext);
    }
    return null;
  }
  
  private static void g(Activity paramActivity)
  {
    paramActivity = paramActivity.getWindowManager().getDefaultDisplay();
    Object localObject;
    if (Build.VERSION.SDK_INT >= 17)
    {
      localObject = new Point();
      paramActivity.getRealSize((Point)localObject);
      if (((Point)localObject).x < ((Point)localObject).y)
      {
        e = ((Point)localObject).x;
        f = ((Point)localObject).y;
        return;
      }
      e = ((Point)localObject).y;
      f = ((Point)localObject).x;
      return;
    }
    try
    {
      localObject = Display.class.getMethod("getRawWidth", new Class[0]);
      Method localMethod = Display.class.getMethod("getRawHeight", new Class[0]);
      e = ((Integer)((Method)localObject).invoke(paramActivity, new Object[0])).intValue();
      f = ((Integer)localMethod.invoke(paramActivity, new Object[0])).intValue();
      return;
    }
    catch (Exception paramActivity)
    {
      for (;;) {}
    }
    e = 0;
    f = 0;
  }
  
  /* Error */
  private static String h(Context paramContext)
  {
    // Byte code:
    //   0: getstatic 448	android/os/Build$VERSION:SDK_INT	I
    //   3: bipush 16
    //   5: if_icmpge +33 -> 38
    //   8: ldc_w 815
    //   11: invokestatic 819	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   14: astore_2
    //   15: aload_2
    //   16: iconst_2
    //   17: anewarray 252	java/lang/Class
    //   20: dup
    //   21: iconst_0
    //   22: ldc 52
    //   24: aastore
    //   25: dup
    //   26: iconst_1
    //   27: ldc_w 450
    //   30: aastore
    //   31: invokevirtual 823	java/lang/Class:getDeclaredConstructor	([Ljava/lang/Class;)Ljava/lang/reflect/Constructor;
    //   34: astore_1
    //   35: goto +33 -> 68
    //   38: ldc_w 825
    //   41: invokestatic 819	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   44: astore_2
    //   45: aload_2
    //   46: iconst_2
    //   47: anewarray 252	java/lang/Class
    //   50: dup
    //   51: iconst_0
    //   52: ldc 52
    //   54: aastore
    //   55: dup
    //   56: iconst_1
    //   57: ldc_w 827
    //   60: invokestatic 819	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   63: aastore
    //   64: invokevirtual 823	java/lang/Class:getDeclaredConstructor	([Ljava/lang/Class;)Ljava/lang/reflect/Constructor;
    //   67: astore_1
    //   68: aload_1
    //   69: iconst_1
    //   70: invokevirtual 832	java/lang/reflect/Constructor:setAccessible	(Z)V
    //   73: aload_2
    //   74: ldc_w 834
    //   77: iconst_0
    //   78: anewarray 252	java/lang/Class
    //   81: invokevirtual 800	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   84: aload_1
    //   85: iconst_2
    //   86: anewarray 4	java/lang/Object
    //   89: dup
    //   90: iconst_0
    //   91: aload_0
    //   92: aastore
    //   93: dup
    //   94: iconst_1
    //   95: aconst_null
    //   96: aastore
    //   97: invokevirtual 838	java/lang/reflect/Constructor:newInstance	([Ljava/lang/Object;)Ljava/lang/Object;
    //   100: iconst_0
    //   101: anewarray 4	java/lang/Object
    //   104: invokevirtual 808	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   107: checkcast 287	java/lang/String
    //   110: astore_0
    //   111: aload_1
    //   112: iconst_0
    //   113: invokevirtual 832	java/lang/reflect/Constructor:setAccessible	(Z)V
    //   116: aload_0
    //   117: areturn
    //   118: astore_0
    //   119: aload_1
    //   120: iconst_0
    //   121: invokevirtual 832	java/lang/reflect/Constructor:setAccessible	(Z)V
    //   124: aload_0
    //   125: athrow
    //   126: aconst_null
    //   127: areturn
    //   128: astore_0
    //   129: goto -3 -> 126
    //   132: astore_1
    //   133: aload_0
    //   134: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	135	0	paramContext	Context
    //   34	86	1	localConstructor	java.lang.reflect.Constructor
    //   132	1	1	localException	Exception
    //   14	60	2	localClass	Class
    // Exception table:
    //   from	to	target	type
    //   73	111	118	finally
    //   0	35	128	java/lang/Exception
    //   38	68	128	java/lang/Exception
    //   68	73	128	java/lang/Exception
    //   119	126	128	java/lang/Exception
    //   111	116	132	java/lang/Exception
  }
}
