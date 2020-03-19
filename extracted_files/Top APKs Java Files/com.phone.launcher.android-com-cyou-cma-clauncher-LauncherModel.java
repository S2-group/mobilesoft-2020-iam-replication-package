package com.cyou.cma.clauncher;

import android.annotation.TargetApi;
import android.appwidget.AppWidgetProviderInfo;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.content.OperationApplicationException;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Parcelable;
import android.os.Process;
import android.os.RemoteException;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import com.cyou.cma.a.a;
import java.lang.ref.WeakReference;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LauncherModel
  extends BroadcastReceiver
{
  private static final String[] D = { "com.whatsapp", "com.facebook.katana", "com.facebook.orca", "com.tencent.mm", "com.bsb.hike", "com.sgiggle.production", "com.viber.voip", "com.instagram.android", "com.twitter.android", "jp.naver.line.android", "com.skype.raider" };
  private static final HandlerThread E;
  private static final Handler F;
  private static int R;
  private static int S;
  private static final Collator V = Collator.getInstance();
  static final HashMap<Long, by> d;
  static final ArrayList<by> e;
  static final ArrayList<ck> f;
  static final ArrayList<co> g;
  static final HashMap<Long, bc> h;
  static final HashMap<Object, byte[]> i;
  public static final Comparator<ap> m = new Comparator() {};
  public static final Comparator<f> n = new Comparator() {};
  public static final Comparator<Folder> o = new Comparator() {};
  public static final Comparator<f> p = new Comparator() {};
  public static final Comparator<f> q = new Comparator() {};
  public static final Comparator<ap> r = new Comparator() {};
  public static final Comparator<by> s = new Comparator() {};
  public static final Comparator<f> t = new Comparator() {};
  public static final Comparator<f> u = new Comparator() {};
  public static final Comparator<AppWidgetProviderInfo> v = new Comparator() {};
  private final Object A = new Object();
  private ae B = new ae();
  private cr C;
  private boolean G;
  private boolean H;
  private boolean I;
  private boolean J;
  private boolean K;
  private boolean L;
  private boolean M;
  private boolean N = false;
  private WeakReference<cp> O;
  private bs P;
  private Bitmap Q;
  private int T;
  private cq U;
  boolean a = true;
  aw b;
  public c c;
  protected int j;
  public ArrayList<cs> k = new ArrayList();
  public ArrayList<cs> l = new ArrayList();
  private final boolean w;
  private int x;
  private int y;
  private final LauncherApplication z;
  
  static
  {
    HandlerThread localHandlerThread = new HandlerThread("launcher-loader");
    E = localHandlerThread;
    localHandlerThread.start();
    F = new Handler(E.getLooper());
    d = new HashMap();
    e = new ArrayList();
    f = new ArrayList();
    g = new ArrayList();
    h = new HashMap();
    i = new HashMap();
  }
  
  @TargetApi(11)
  LauncherModel(LauncherApplication paramLauncherApplication, bs paramBs)
  {
    boolean bool;
    if (com.cyou.cma.clauncher.b.c.c()) {
      if (!Environment.isExternalStorageEmulated()) {
        bool = true;
      }
    }
    for (;;)
    {
      this.w = bool;
      this.G = false;
      this.H = false;
      this.I = false;
      this.N = false;
      this.a = true;
      this.K = false;
      this.L = false;
      this.J = false;
      this.z = paramLauncherApplication;
      this.c = new c(paramBs);
      this.P = paramBs;
      try
      {
        this.Q = ej.a(this.P.b(), paramLauncherApplication, 2);
        paramBs = paramLauncherApplication.getResources();
        this.y = paramBs.getInteger(2131623938);
        this.x = paramBs.getInteger(2131623939);
        this.j = paramBs.getConfiguration().mcc;
        a.a.a(paramLauncherApplication.getBaseContext());
        return;
        bool = false;
        continue;
        this.w = "mounted".equals(Environment.getExternalStorageState());
      }
      catch (Exception paramBs)
      {
        for (;;) {}
      }
    }
  }
  
  static int a(long paramLong, int paramInt1, int paramInt2, int paramInt3)
  {
    return ((int)paramLong & 0xFF) << 24 | (paramInt1 & 0xFF) << 16 | (paramInt2 & 0xFF) << 8 | paramInt3 & 0xFF;
  }
  
  public static int a(String paramString1, String paramString2)
  {
    return V.compare(paramString1, paramString2);
  }
  
  static ComponentName a(ResolveInfo paramResolveInfo)
  {
    if (paramResolveInfo.activityInfo != null) {
      return new ComponentName(paramResolveInfo.activityInfo.packageName, paramResolveInfo.activityInfo.name);
    }
    return new ComponentName(paramResolveInfo.serviceInfo.packageName, paramResolveInfo.serviceInfo.name);
  }
  
  private static Bitmap a(Cursor paramCursor, int paramInt, Context paramContext)
  {
    paramCursor = paramCursor.getBlob(paramInt);
    try
    {
      paramCursor = ej.a(BitmapFactory.decodeByteArray(paramCursor, 0, paramCursor.length), paramContext);
      return paramCursor;
    }
    catch (Exception paramCursor) {}
    return null;
  }
  
  public static Handler a()
  {
    return F;
  }
  
  static bc a(Context paramContext, HashMap<Long, bc> paramHashMap, long paramLong)
  {
    Object localObject = null;
    Cursor localCursor = paramContext.getContentResolver().query(cz.a, null, "_id=? and (itemType=? or itemType=?)", new String[] { String.valueOf(paramLong), "2" }, null);
    for (;;)
    {
      try
      {
        int i1;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        if (localCursor.moveToFirst())
        {
          i1 = localCursor.getColumnIndexOrThrow("itemType");
          i2 = localCursor.getColumnIndexOrThrow("title");
          i3 = localCursor.getColumnIndexOrThrow("container");
          i4 = localCursor.getColumnIndexOrThrow("screen");
          i5 = localCursor.getColumnIndexOrThrow("cellX");
          i6 = localCursor.getColumnIndexOrThrow("cellY");
        }
        switch (localCursor.getInt(i1))
        {
        case 2: 
          paramContext.l = localCursor.getString(i2);
          paramContext.q = paramLong;
          paramContext.s = localCursor.getInt(i3);
          paramContext.t = localCursor.getInt(i4);
          paramContext.u = localCursor.getInt(i5);
          paramContext.v = localCursor.getInt(i6);
          return paramContext;
          paramContext = c(paramHashMap, paramLong);
          continue;
          return null;
        }
      }
      finally
      {
        localCursor.close();
      }
      paramContext = localObject;
    }
  }
  
  private ef a(Cursor paramCursor, Context paramContext, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, Intent paramIntent)
  {
    Object localObject2 = null;
    Object localObject3 = null;
    ef localEf = new ef();
    localEf.r = 1;
    localEf.a = paramCursor.getString(paramInt5);
    switch (paramCursor.getInt(paramInt1))
    {
    default: 
      paramCursor = Bitmap.createBitmap(this.Q);
      localEf.d = true;
      localEf.c = false;
    }
    for (;;)
    {
      localEf.f = paramCursor;
      return localEf;
      String str1 = paramCursor.getString(paramInt2);
      String str2 = paramCursor.getString(paramInt3);
      Object localObject4 = paramContext.getPackageManager();
      localEf.c = false;
      if ((paramIntent != null) && (paramIntent.getComponent() != null))
      {
        paramIntent = paramIntent.getComponent();
        if (paramIntent != null) {
          paramIntent.getClassName();
        }
      }
      Object localObject1 = localObject2;
      for (;;)
      {
        try
        {
          localObject4 = ((PackageManager)localObject4).getResourcesForApplication(str1);
          paramIntent = localObject3;
          if (localObject4 != null)
          {
            localObject1 = localObject2;
            paramInt1 = ((Resources)localObject4).getIdentifier(str2, null, null);
            localObject1 = localObject2;
            paramIntent = this.P.a((Resources)localObject4, paramInt1);
            localObject1 = localObject2;
            if (!com.cyou.cma.b.g.equalsIgnoreCase(str1)) {
              continue;
            }
            paramInt1 = 2;
            localObject1 = localObject2;
            paramIntent = ej.a(paramIntent, paramContext, paramInt1);
          }
          localObject1 = paramIntent;
          localEf.c = true;
          localObject1 = paramIntent;
        }
        catch (Exception paramIntent)
        {
          continue;
        }
        paramIntent = (Intent)localObject1;
        if (localObject1 == null) {
          paramIntent = a(paramCursor, paramInt4, paramContext);
        }
        paramCursor = paramIntent;
        if (paramIntent != null) {
          break;
        }
        paramCursor = Bitmap.createBitmap(this.Q);
        localEf.d = true;
        break;
        paramInt1 = 1;
      }
      paramCursor = a(paramCursor, paramInt4, paramContext);
      if (paramCursor == null)
      {
        paramCursor = Bitmap.createBitmap(this.Q);
        localEf.c = false;
        localEf.d = true;
      }
      else
      {
        localEf.c = true;
      }
    }
  }
  
  private static String a(String paramString)
  {
    paramString = Pattern.compile("component=(.*?);").matcher(paramString);
    if (paramString.find()) {
      return paramString.group(1);
    }
    return "";
  }
  
  /* Error */
  static ArrayList<by> a(Context paramContext)
  {
    // Byte code:
    //   0: new 181	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 182	java/util/ArrayList:<init>	()V
    //   7: astore 8
    //   9: aload_0
    //   10: invokevirtual 416	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   13: getstatic 421	com/cyou/cma/clauncher/cz:a	Landroid/net/Uri;
    //   16: bipush 7
    //   18: anewarray 125	java/lang/String
    //   21: dup
    //   22: iconst_0
    //   23: ldc_w 440
    //   26: aastore
    //   27: dup
    //   28: iconst_1
    //   29: ldc_w 448
    //   32: aastore
    //   33: dup
    //   34: iconst_2
    //   35: ldc_w 450
    //   38: aastore
    //   39: dup
    //   40: iconst_3
    //   41: ldc_w 452
    //   44: aastore
    //   45: dup
    //   46: iconst_4
    //   47: ldc_w 454
    //   50: aastore
    //   51: dup
    //   52: iconst_5
    //   53: ldc_w 569
    //   56: aastore
    //   57: dup
    //   58: bipush 6
    //   60: ldc_w 571
    //   63: aastore
    //   64: aconst_null
    //   65: aconst_null
    //   66: aconst_null
    //   67: invokevirtual 435	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   70: astore_0
    //   71: aload_0
    //   72: ldc_w 440
    //   75: invokeinterface 444 2 0
    //   80: istore_1
    //   81: aload_0
    //   82: ldc_w 448
    //   85: invokeinterface 444 2 0
    //   90: istore_2
    //   91: aload_0
    //   92: ldc_w 450
    //   95: invokeinterface 444 2 0
    //   100: istore_3
    //   101: aload_0
    //   102: ldc_w 452
    //   105: invokeinterface 444 2 0
    //   110: istore 4
    //   112: aload_0
    //   113: ldc_w 454
    //   116: invokeinterface 444 2 0
    //   121: istore 5
    //   123: aload_0
    //   124: ldc_w 569
    //   127: invokeinterface 444 2 0
    //   132: istore 6
    //   134: aload_0
    //   135: ldc_w 571
    //   138: invokeinterface 444 2 0
    //   143: istore 7
    //   145: aload_0
    //   146: invokeinterface 574 1 0
    //   151: ifeq +128 -> 279
    //   154: new 576	com/cyou/cma/clauncher/by
    //   157: dup
    //   158: invokespecial 577	com/cyou/cma/clauncher/by:<init>	()V
    //   161: astore 9
    //   163: aload 9
    //   165: aload_0
    //   166: iload 4
    //   168: invokeinterface 457 2 0
    //   173: putfield 578	com/cyou/cma/clauncher/by:u	I
    //   176: aload 9
    //   178: aload_0
    //   179: iload 5
    //   181: invokeinterface 457 2 0
    //   186: putfield 579	com/cyou/cma/clauncher/by:v	I
    //   189: aload 9
    //   191: aload_0
    //   192: iload 6
    //   194: invokeinterface 457 2 0
    //   199: putfield 581	com/cyou/cma/clauncher/by:w	I
    //   202: aload 9
    //   204: aload_0
    //   205: iload 7
    //   207: invokeinterface 457 2 0
    //   212: putfield 582	com/cyou/cma/clauncher/by:x	I
    //   215: aload 9
    //   217: aload_0
    //   218: iload_2
    //   219: invokeinterface 457 2 0
    //   224: i2l
    //   225: putfield 583	com/cyou/cma/clauncher/by:s	J
    //   228: aload 9
    //   230: aload_0
    //   231: iload_1
    //   232: invokeinterface 457 2 0
    //   237: putfield 584	com/cyou/cma/clauncher/by:r	I
    //   240: aload 9
    //   242: aload_0
    //   243: iload_3
    //   244: invokeinterface 457 2 0
    //   249: putfield 585	com/cyou/cma/clauncher/by:t	I
    //   252: aload 8
    //   254: aload 9
    //   256: invokevirtual 588	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   259: pop
    //   260: goto -115 -> 145
    //   263: astore 9
    //   265: aload 8
    //   267: invokevirtual 591	java/util/ArrayList:clear	()V
    //   270: aload_0
    //   271: invokeinterface 479 1 0
    //   276: aload 8
    //   278: areturn
    //   279: aload_0
    //   280: invokeinterface 479 1 0
    //   285: aload 8
    //   287: areturn
    //   288: astore 8
    //   290: aload_0
    //   291: invokeinterface 479 1 0
    //   296: aload 8
    //   298: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	299	0	paramContext	Context
    //   80	152	1	i1	int
    //   90	129	2	i2	int
    //   100	144	3	i3	int
    //   110	57	4	i4	int
    //   121	59	5	i5	int
    //   132	61	6	i6	int
    //   143	63	7	i7	int
    //   7	279	8	localArrayList	ArrayList
    //   288	9	8	localObject	Object
    //   161	94	9	localBy	by
    //   263	1	9	localException	Exception
    // Exception table:
    //   from	to	target	type
    //   145	260	263	java/lang/Exception
    //   145	260	288	finally
    //   265	270	288	finally
  }
  
  static void a(int paramInt1, int paramInt2)
  {
    R = paramInt1;
    S = paramInt2;
  }
  
  private static void a(Context paramContext, final ContentValues paramContentValues, by paramBy, final String paramString)
  {
    final long l1 = paramBy.q;
    final Uri localUri = cz.a(l1);
    paramContext = new Runnable()
    {
      public final void run()
      {
        try
        {
          LauncherModel.this.update(localUri, paramContentValues, null, null);
          by localBy = (by)LauncherModel.d.get(Long.valueOf(l1));
          if (localBy == null) {}
          label131:
          do
          {
            return;
            if (paramString != localBy)
            {
              StringBuilder localStringBuilder = new StringBuilder("item: ");
              if (paramString != null)
              {
                str = paramString.toString();
                localStringBuilder = localStringBuilder.append(str).append(" modelItem: ");
                if (localBy == null) {
                  break label131;
                }
              }
              for (String str = localBy.toString();; str = "null")
              {
                Log.e("Launcher.Model", str + "Error: ItemInfo passed to " + this.f + " doesn't match original");
                return;
                str = "null";
                break;
              }
            }
            if ((localBy.s != -100L) && (localBy.s != -101L)) {
              break;
            }
          } while (LauncherModel.e.contains(localBy));
          LauncherModel.e.add(localBy);
          return;
          LauncherModel.e.remove(localBy);
          return;
        }
        catch (Exception localException)
        {
          for (;;) {}
        }
      }
    };
    if (E.getThreadId() == Process.myTid())
    {
      paramContext.run();
      return;
    }
    F.post(paramContext);
  }
  
  static void a(Context paramContext, final bc paramBc)
  {
    paramContext = new Runnable()
    {
      public final void run()
      {
        LauncherModel.this.delete(cz.a(paramBc.q), null, null);
        LauncherModel.d.remove(Long.valueOf(paramBc.q));
        LauncherModel.h.remove(Long.valueOf(paramBc.q));
        LauncherModel.i.remove(paramBc);
        LauncherModel.e.remove(paramBc);
        LauncherModel.this.delete(cz.b, "container=" + paramBc.q, null);
        Iterator localIterator = ((ew)paramBc).a.iterator();
        while (localIterator.hasNext())
        {
          by localBy = (by)localIterator.next();
          LauncherModel.d.remove(Long.valueOf(localBy.q));
          LauncherModel.i.remove(localBy);
        }
      }
    };
    if (E.getThreadId() == Process.myTid())
    {
      paramContext.run();
      return;
    }
    F.post(paramContext);
  }
  
  static void a(Context paramContext, by paramBy)
  {
    ContentValues localContentValues = new ContentValues();
    if (paramBy.s == -200L)
    {
      paramBy = (ap)paramBy;
      localContentValues.put("title", paramBy.l.toString());
      b.a(paramContext, localContentValues, paramBy, false);
      return;
    }
    paramBy.a(localContentValues);
    paramBy.y = false;
    a(paramContext, localContentValues, paramBy, "updateItemInDatabase");
  }
  
  static void a(Context paramContext, by paramBy, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    paramBy.w = paramInt3;
    paramBy.x = paramInt4;
    paramBy.u = paramInt1;
    paramBy.v = paramInt2;
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("container", Long.valueOf(paramBy.s));
    localContentValues.put("spanX", Integer.valueOf(paramInt3));
    localContentValues.put("spanY", Integer.valueOf(paramInt4));
    localContentValues.put("cellX", Integer.valueOf(paramInt1));
    localContentValues.put("cellY", Integer.valueOf(paramInt2));
    a(paramContext, localContentValues, paramBy, "resizeItemInDatabase");
  }
  
  static void a(Context paramContext, by paramBy, long paramLong, int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramBy.q == -1L)
    {
      c(paramContext, paramBy, paramLong, paramInt1, paramInt2, paramInt3);
      return;
    }
    b(paramContext, paramBy, paramLong, paramInt1, paramInt2, paramInt3);
  }
  
  private static void a(ct paramCt)
  {
    F.post(paramCt);
  }
  
  static boolean a(Context paramContext, String paramString, Intent paramIntent)
  {
    boolean bool = false;
    paramIntent.setFlags(268435456);
    if (paramIntent.getComponent() != null) {
      paramIntent.setPackage(paramIntent.getComponent().getPackageName());
    }
    paramIntent = paramIntent.toUri(0);
    paramContext = paramContext.getContentResolver().query(cz.a, new String[] { "title", "intent" }, "title=?", new String[] { paramString }, null);
    if (paramContext != null) {}
    for (;;)
    {
      try
      {
        paramContext.moveToFirst();
        bool = a(paramIntent).equals(a(paramContext.getString(1)));
        if (bool)
        {
          bool = true;
          return bool;
        }
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
        return false;
      }
      finally
      {
        paramContext.close();
      }
      bool = false;
    }
  }
  
  private static boolean a(List<ApplicationInfo> paramList, String paramString)
  {
    boolean bool2 = false;
    int i1 = 0;
    for (;;)
    {
      boolean bool1 = bool2;
      if (i1 < paramList.size())
      {
        if (paramString.equals(((ApplicationInfo)paramList.get(i1)).packageName)) {
          bool1 = true;
        }
      }
      else {
        return bool1;
      }
      i1 += 1;
    }
  }
  
  static void b(final Context paramContext, final by paramBy)
  {
    paramContext = new Runnable()
    {
      public final void run()
      {
        try
        {
          LauncherModel.this.delete(this.b, null, null);
          switch (paramBy.r)
          {
          }
          for (;;)
          {
            LauncherModel.d.remove(Long.valueOf(paramBy.q));
            LauncherModel.i.remove(paramBy);
            return;
            LauncherModel.h.remove(Long.valueOf(paramBy.q));
            LauncherModel.e.remove(paramBy);
            continue;
            Object localObject;
            ComponentName localComponentName;
            String str;
            if ((paramBy instanceof ef))
            {
              localObject = (ef)paramBy;
              if (!TextUtils.isEmpty(((ef)localObject).j)) {
                break label323;
              }
              if ((paramContext != null) && (((ef)localObject).b != null))
              {
                localComponentName = ((ef)localObject).b.getComponent();
                if (localComponentName != null)
                {
                  str = localComponentName.getClassName();
                  if ((!"com.cyou.cma.opti.center.OptiCenterActivity".equals(str)) && (!com.cyou.cma.b.j.equals(str)))
                  {
                    if (!"com.cyou.cma.recommend.RecommendAppActivity".equals(str)) {
                      break label244;
                    }
                    "CLocker".equals(((ef)localObject).b.getStringExtra("recommend_app"));
                  }
                }
              }
            }
            for (;;)
            {
              LauncherModel.e.remove(paramBy);
              break;
              label244:
              if (!"com.cyou.cma.clockscreen.activity.SplashActivity".equals(str))
              {
                localObject = localComponentName.getPackageName();
                if ((!"com.mobogenie.markets".equals(localObject)) && (!"com.mobogenie".equals(localObject)) && (!"com.android.vending".equals(localObject))) {
                  if ("com.cyou.cma.recommend.RecommendDownloadActivity".equals(str))
                  {
                    ((ef)paramBy).a.toString();
                  }
                  else
                  {
                    "com.cyou.cma.cleanmemory.CleanMemoryWidgetInfo".equals(str);
                    continue;
                    label323:
                    if ((!"mms".equals(((ef)localObject).j)) && (!"dial".equals(((ef)localObject).j)) && (!"camera".equals(((ef)localObject).j)) && (!"browser".equals(((ef)localObject).j)) && (!"setting".equals(((ef)localObject).j))) {
                      "gallery".equals(((ef)localObject).j);
                    }
                  }
                }
              }
            }
            LauncherModel.f.remove((ck)paramBy);
            continue;
            LauncherModel.g.remove((co)paramBy);
          }
        }
        catch (Exception localException)
        {
          for (;;) {}
        }
      }
    };
    if (E.getThreadId() == Process.myTid())
    {
      paramContext.run();
      return;
    }
    F.post(paramContext);
  }
  
  static void b(Context paramContext, by paramBy, long paramLong, int paramInt1, int paramInt2, int paramInt3)
  {
    paramBy.s = paramLong;
    paramBy.u = paramInt2;
    paramBy.v = paramInt3;
    if (((paramContext instanceof Launcher)) && (paramLong == -101L)) {}
    for (paramBy.t = ((Launcher)paramContext).N().a(paramInt2, paramInt3);; paramBy.t = paramInt1)
    {
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("container", Long.valueOf(paramBy.s));
      localContentValues.put("cellX", Integer.valueOf(paramBy.u));
      localContentValues.put("cellY", Integer.valueOf(paramBy.v));
      localContentValues.put("screen", Integer.valueOf(paramBy.t));
      if (paramBy.s >= 0L) {
        localContentValues.put("app_index", Integer.valueOf(((ef)paramBy).H));
      }
      a(paramContext, localContentValues, paramBy, "moveItemInDatabase");
      return;
    }
  }
  
  public static int c()
  {
    return R;
  }
  
  private static bc c(HashMap<Long, bc> paramHashMap, long paramLong)
  {
    bc localBc = (bc)paramHashMap.get(Long.valueOf(paramLong));
    Object localObject = localBc;
    if (localBc == null)
    {
      localObject = new ew();
      paramHashMap.put(Long.valueOf(paramLong), localObject);
    }
    return localObject;
  }
  
  static void c(Context paramContext, final by paramBy, long paramLong, int paramInt1, int paramInt2, int paramInt3)
  {
    paramBy.s = paramLong;
    paramBy.u = paramInt2;
    paramBy.v = paramInt3;
    final ContentValues localContentValues;
    ContentResolver localContentResolver;
    if (((paramContext instanceof Launcher)) && (paramInt1 < 0) && (paramLong == -101L))
    {
      paramBy.t = ((Launcher)paramContext).N().a(paramInt2, paramInt3);
      localContentValues = new ContentValues();
      localContentResolver = paramContext.getContentResolver();
      paramBy.a(localContentValues);
      paramContext = (LauncherApplication)paramContext.getApplicationContext();
      if ((paramContext != null) && (paramContext.e != null)) {
        break label223;
      }
    }
    label223:
    for (paramBy.q = SystemClock.uptimeMillis();; paramBy.q = paramContext.e.b())
    {
      localContentValues.put("folder_type", paramBy.D);
      localContentValues.put("app_index", Integer.valueOf(paramBy.H));
      localContentValues.put("_id", Long.valueOf(paramBy.q));
      paramInt1 = paramBy.u;
      paramInt2 = paramBy.v;
      localContentValues.put("cellX", Integer.valueOf(paramInt1));
      localContentValues.put("cellY", Integer.valueOf(paramInt2));
      paramContext = new Runnable()
      {
        public final void run()
        {
          for (;;)
          {
            try
            {
              ContentResolver localContentResolver = LauncherModel.this;
              if (!this.b) {
                continue;
              }
              localUri = cz.a;
              localContentResolver.insert(localUri, localContentValues);
            }
            catch (Exception localException)
            {
              Uri localUri;
              continue;
            }
            if (!LauncherModel.d.containsKey(Long.valueOf(paramBy.q))) {
              continue;
            }
            Log.e("LauncherModel", "Error: ItemInfo id (" + paramBy.q + ") passed to addItemToDatabase already exists." + paramBy.toString());
            return;
            localUri = cz.b;
          }
          LauncherModel.d.put(Long.valueOf(paramBy.q), paramBy);
          switch (paramBy.r)
          {
          case 3: 
          case 6: 
          default: 
            return;
          case 0: 
          case 1: 
          case 2: 
            while ((paramBy.s == -100L) || (paramBy.s == -101L))
            {
              LauncherModel.e.add(paramBy);
              return;
              LauncherModel.h.put(Long.valueOf(paramBy.q), (bc)paramBy);
            }
          case 4: 
            LauncherModel.f.add((ck)paramBy);
            return;
          case 5: 
            LauncherModel.e.add(paramBy);
            return;
          }
          LauncherModel.g.add((co)paramBy);
        }
      };
      if (E.getThreadId() != Process.myTid()) {
        break label237;
      }
      paramContext.run();
      return;
      paramBy.t = paramInt1;
      break;
    }
    label237:
    F.post(paramContext);
  }
  
  public static int d()
  {
    return S;
  }
  
  private ArrayList<f> d(Context paramContext)
  {
    Object localObject1;
    Object localObject2;
    ArrayList localArrayList;
    if (!this.N)
    {
      localObject1 = paramContext.getPackageManager();
      localObject2 = new Intent("android.intent.action.MAIN", null);
      ((Intent)localObject2).addCategory("android.intent.category.LAUNCHER");
      localArrayList = new ArrayList();
    }
    label255:
    for (;;)
    {
      try
      {
        localObject2 = ((PackageManager)localObject1).queryIntentActivities((Intent)localObject2, 0);
        int i3 = ((List)localObject2).size();
        int i2 = 0;
        int i1 = 1;
        if (i2 < i3)
        {
          Object localObject3 = (ResolveInfo)((List)localObject2).get(i2);
          if (com.cyou.cma.as.b(((ResolveInfo)localObject3).activityInfo.applicationInfo.packageName, ((ResolveInfo)localObject3).activityInfo.name)) {
            break label255;
          }
          localObject3 = new f((PackageManager)localObject1, (ResolveInfo)localObject3, this.P, null);
          if (this.c.a.contains(localObject3)) {
            break label255;
          }
          ((f)localObject3).H = (b.a(paramContext) + i1);
          i1 += 1;
          ((f)localObject3).s = -200L;
          ((f)localObject3).G = 1;
          ((f)localObject3).r = 0;
          localArrayList.add(localObject3);
          i2 += 1;
          continue;
        }
        localObject1 = localArrayList.iterator();
      }
      catch (Exception paramContext)
      {
        return localArrayList;
      }
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (f)((Iterator)localObject1).next();
        this.c.a.add(localObject2);
        b.a(paramContext, (ap)localObject2, false);
      }
      return localArrayList;
      return null;
    }
  }
  
  private ArrayList<f> e(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject = null;
    try
    {
      List localList = paramContext.getPackageManager().getInstalledApplications(8192);
      localObject = localList;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Log.e("Launcher.Model", localException.getMessage());
      }
      if ((this.T <= 0) || (localObject.size() == this.T)) {
        break label74;
      }
      this.T = 0;
      return localArrayList;
      label74:
      this.T = 0;
      Iterator localIterator = this.c.a.iterator();
      while (localIterator.hasNext())
      {
        f localF = (f)localIterator.next();
        if ((localF.h) && (!a(localObject, localF.d.getPackageName())))
        {
          localIterator.remove();
          localArrayList.add(localF);
          b.a(paramContext, localF.q);
        }
      }
    }
    if (localObject == null) {
      return localArrayList;
    }
    return localArrayList;
  }
  
  private void o()
  {
    for (;;)
    {
      synchronized (this.A)
      {
        p();
        this.H = false;
        this.G = false;
        if (this.O != null)
        {
          ??? = (cp)this.O.get();
          if ((??? != null) && (!((cp)???).V()))
          {
            i1 = 1;
            if (i1 != 0) {
              a(this.z, false, false);
            }
            return;
          }
        }
      }
      int i1 = 0;
    }
  }
  
  private boolean p()
  {
    boolean bool1 = false;
    boolean bool2 = false;
    cr localCr = this.C;
    if (localCr != null)
    {
      bool1 = bool2;
      if (localCr.a()) {
        bool1 = true;
      }
      localCr.b();
    }
    return bool1;
  }
  
  final ef a(Context paramContext, Intent paramIntent)
  {
    Object localObject = null;
    Intent localIntent = (Intent)paramIntent.getParcelableExtra("android.intent.extra.shortcut.INTENT");
    String str = paramIntent.getStringExtra("android.intent.extra.shortcut.NAME");
    Parcelable localParcelable = paramIntent.getParcelableExtra("android.intent.extra.shortcut.ICON");
    if (localIntent == null)
    {
      Log.e("Launcher.Model", "Can't construct ShorcutInfo with null intent");
      return null;
    }
    ef localEf = new ef();
    if ((localParcelable != null) && ((localParcelable instanceof Bitmap)))
    {
      paramContext = ej.a(new as((Bitmap)localParcelable), paramContext, 1);
      localEf.g = ((Bitmap)localParcelable);
      paramIntent = (Intent)localObject;
    }
    for (;;)
    {
      localObject = paramContext;
      if (paramContext == null)
      {
        localObject = Bitmap.createBitmap(this.Q);
        localEf.d = true;
      }
      localEf.f = ((Bitmap)localObject);
      localEf.a = str;
      localEf.b = localIntent;
      localEf.e = paramIntent;
      localEf.c = true;
      return localEf;
      localParcelable = paramIntent.getParcelableExtra("android.intent.extra.shortcut.ICON_RESOURCE");
      if ((localParcelable != null) && ((localParcelable instanceof Intent.ShortcutIconResource)))
      {
        try
        {
          paramIntent = (Intent.ShortcutIconResource)localParcelable;
        }
        catch (Exception paramContext)
        {
          try
          {
            localObject = paramContext.getPackageManager().getResourcesForApplication(paramIntent.packageName);
            int i1 = ((Resources)localObject).getIdentifier(paramIntent.resourceName, null, null);
            localObject = this.P.a((Resources)localObject, i1);
            if ((localObject instanceof BitmapDrawable)) {
              localEf.g = ((BitmapDrawable)localObject).getBitmap();
            }
            paramContext = ej.a((Drawable)localObject, paramContext, 1);
          }
          catch (Exception paramContext)
          {
            for (;;)
            {
              paramContext = paramIntent;
            }
          }
          paramContext = paramContext;
          paramContext = null;
        }
        Log.w("Launcher.Model", "Could not load shortcut icon: " + localParcelable);
        localObject = null;
        paramIntent = paramContext;
        paramContext = (Context)localObject;
      }
      else
      {
        paramContext = null;
        paramIntent = (Intent)localObject;
      }
    }
  }
  
  public final ef a(PackageManager paramPackageManager, Intent paramIntent, Context paramContext)
  {
    return a(paramPackageManager, paramIntent, paramContext, null, -1, -1, null);
  }
  
  public final ef a(PackageManager paramPackageManager, Intent paramIntent, Context paramContext, Cursor paramCursor, int paramInt1, int paramInt2, HashMap<Object, CharSequence> paramHashMap)
  {
    Bitmap localBitmap = null;
    localComponentName = paramIntent.getComponent();
    if (localComponentName == null) {}
    for (;;)
    {
      return null;
      ef localEf = new ef();
      try
      {
        boolean bool = paramPackageManager.getPackageInfo(localComponentName.getPackageName(), 0).applicationInfo.enabled;
        if (!bool) {}
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        for (;;)
        {
          ResolveInfo localResolveInfo;
          Log.d("Launcher.Model", "getPackInfo failed for package " + localComponentName.getPackageName());
          continue;
          localEf.a = localNameNotFoundException.activityInfo.loadLabel(paramPackageManager);
          if (paramHashMap != null) {
            paramHashMap.put(paramIntent, localEf.a);
          }
        }
      }
    }
    localResolveInfo = paramPackageManager.resolveActivity(paramIntent, 0);
    if (localResolveInfo != null) {
      localBitmap = this.P.a(localComponentName, localResolveInfo, paramHashMap);
    }
    paramIntent = localBitmap;
    if (localBitmap == null)
    {
      paramIntent = localBitmap;
      if (paramCursor != null) {
        paramIntent = a(paramCursor, paramInt1, paramContext);
      }
    }
    paramContext = paramIntent;
    if (paramIntent == null)
    {
      paramContext = Bitmap.createBitmap(this.Q);
      localEf.d = true;
    }
    localEf.f = paramContext;
    if (localResolveInfo != null)
    {
      paramIntent = a(localResolveInfo);
      if ((paramHashMap != null) && (paramHashMap.containsKey(paramIntent))) {
        localEf.a = ((CharSequence)paramHashMap.get(paramIntent));
      }
    }
    else
    {
      if ((localEf.a == null) && (paramCursor != null)) {
        localEf.a = paramCursor.getString(paramInt2);
      }
      if (localEf.a == null) {
        localEf.a = localComponentName.getClassName();
      }
      localEf.r = 0;
      return localEf;
    }
  }
  
  public final ef a(PackageManager paramPackageManager, Intent paramIntent, HashMap<Object, CharSequence> paramHashMap, String paramString)
  {
    ef localEf = new ef();
    ComponentName localComponentName1 = paramIntent.getComponent();
    if (localComponentName1 == null) {
      return null;
    }
    paramIntent = paramPackageManager.resolveActivity(paramIntent, 0);
    ComponentName localComponentName2;
    if (paramIntent != null)
    {
      localComponentName2 = a(paramIntent);
      if ((paramHashMap != null) && (paramHashMap.containsKey(localComponentName2))) {
        localEf.a = ((CharSequence)paramHashMap.get(localComponentName2));
      }
    }
    else
    {
      if (paramIntent == null) {
        break label139;
      }
    }
    label139:
    for (paramPackageManager = this.P.a(localComponentName1, localEf.a.toString(), paramString);; paramPackageManager = null)
    {
      localEf.r = 0;
      localEf.f = paramPackageManager;
      return localEf;
      localEf.a = paramIntent.activityInfo.loadLabel(paramPackageManager);
      if (paramHashMap == null) {
        break;
      }
      paramHashMap.put(localComponentName2, localEf.a);
      break;
    }
  }
  
  @TargetApi(12)
  final void a(Context paramContext, ef paramEf, byte[] paramArrayOfByte)
  {
    i1 = 0;
    if (paramArrayOfByte != null) {}
    for (;;)
    {
      try
      {
        paramArrayOfByte = BitmapFactory.decodeByteArray(paramArrayOfByte, 0, paramArrayOfByte.length);
        Bitmap localBitmap = paramEf.a(this.P);
        if (!com.cyou.cma.clauncher.b.c.f()) {
          continue;
        }
        boolean bool = paramArrayOfByte.sameAs(localBitmap);
        if (!bool) {
          i1 = 1;
        }
      }
      catch (Exception paramArrayOfByte)
      {
        i1 = 1;
        continue;
      }
      if (i1 != 0)
      {
        paramEf.y = true;
        a(paramContext, paramEf);
      }
      return;
      i1 = 1;
      continue;
      i1 = 1;
    }
  }
  
  public final void a(final Context paramContext, final ArrayList<ContentProviderOperation> paramArrayList, final ArrayList<by> paramArrayList1)
  {
    paramContext = new Runnable()
    {
      public final void run()
      {
        int j = paramArrayList1.size();
        int i = 0;
        if (i < j)
        {
          by localBy1 = (by)paramArrayList1.get(i);
          switch (localBy1.r)
          {
          }
          for (;;)
          {
            LauncherModel.d.remove(Long.valueOf(localBy1.q));
            LauncherModel.i.remove(localBy1);
            i += 1;
            break;
            LauncherModel.h.remove(Long.valueOf(localBy1.q));
            LauncherModel.e.remove(localBy1);
            Iterator localIterator = paramArrayList1.iterator();
            while (localIterator.hasNext())
            {
              by localBy2 = (by)localIterator.next();
              LauncherModel.d.remove(Long.valueOf(localBy2.q));
              LauncherModel.i.remove(localBy2);
            }
            LauncherModel.e.remove(localBy1);
            continue;
            LauncherModel.f.remove((ck)localBy1);
            continue;
            LauncherModel.g.remove((co)localBy1);
          }
        }
        try
        {
          paramContext.getContentResolver().applyBatch("com.phone.launcher.android.settings", paramArrayList);
          return;
        }
        catch (RemoteException localRemoteException)
        {
          localRemoteException.printStackTrace();
          return;
        }
        catch (OperationApplicationException localOperationApplicationException)
        {
          localOperationApplicationException.printStackTrace();
          return;
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
        }
      }
    };
    if (E.getThreadId() == Process.myTid())
    {
      paramContext.run();
      return;
    }
    F.post(paramContext);
  }
  
  public final void a(Context paramContext, boolean paramBoolean)
  {
    a(paramContext, paramBoolean, false);
  }
  
  public final void a(Context paramContext, boolean paramBoolean1, boolean paramBoolean2)
  {
    for (;;)
    {
      synchronized (this.A)
      {
        if ((this.O != null) && (this.O.get() != null))
        {
          if (!paramBoolean1)
          {
            if (!p()) {
              break label88;
            }
            break label83;
            this.C = new cr(this, paramContext, paramBoolean1, paramBoolean2);
            E.setPriority(5);
            F.post(this.C);
          }
        }
        else {
          return;
        }
      }
      label83:
      paramBoolean1 = true;
      continue;
      label88:
      paramBoolean1 = false;
    }
  }
  
  public final void a(cp paramCp)
  {
    synchronized (this.A)
    {
      this.O = new WeakReference(paramCp);
      return;
    }
  }
  
  final boolean a(HashMap<Object, byte[]> paramHashMap, ef paramEf, Cursor paramCursor, int paramInt)
  {
    if (!this.w) {}
    while ((paramEf.c) || (paramEf.d)) {
      return false;
    }
    paramHashMap.put(paramEf, paramCursor.getBlob(paramInt));
    return true;
  }
  
  public final void b()
  {
    F.post(new Runnable()
    {
      public final void run()
      {
        LauncherModel.a(LauncherModel.this);
      }
    });
  }
  
  public final void b(Context paramContext)
  {
    if ((this.O != null) && (this.O.get() != null))
    {
      this.U = new cq(this, paramContext);
      E.setPriority(5);
      F.post(this.U);
    }
  }
  
  public final void c(final Context paramContext)
  {
    if (this.J) {
      return;
    }
    paramContext = new Runnable()
    {
      public final void run()
      {
        LauncherModel.s(LauncherModel.this);
        final ArrayList localArrayList1 = LauncherModel.a(LauncherModel.this, paramContext);
        final ArrayList localArrayList2 = LauncherModel.b(LauncherModel.this, paramContext);
        final cp localCp = (cp)LauncherModel.e(LauncherModel.this).get();
        if (localCp != null) {
          LauncherModel.d(LauncherModel.this).a(new Runnable()
          {
            public final void run()
            {
              localCp.a(localArrayList1, localArrayList2);
              LauncherModel.t(LauncherModel.this);
              LauncherModel.u(LauncherModel.this);
            }
          });
        }
        LauncherModel.t(LauncherModel.this);
      }
    };
    F.post(paramContext);
  }
  
  public final void e()
  {
    if (this.K)
    {
      this.K = false;
      Log.i("app2", "delayBindWorkspace");
      cr.a(new cr(this, this.z, false));
    }
  }
  
  public final void f()
  {
    if (this.L)
    {
      this.L = false;
      Log.i("app2", "delayBindDrawer");
      cr.b(new cr(this, this.z, false));
    }
  }
  
  public final void g()
  {
    synchronized (this.A)
    {
      if (this.C != null) {
        this.C.b();
      }
      return;
    }
  }
  
  public final boolean h()
  {
    return this.H;
  }
  
  public final boolean i()
  {
    return this.N;
  }
  
  public final void j()
  {
    Log.d("Launcher.Model", "mCallbacks=" + this.O);
    f.a("Launcher.Model", "mAllAppsList.data", this.c.a);
    f.a("Launcher.Model", "mAllAppsList.added", this.c.b);
    f.a("Launcher.Model", "mAllAppsList.removed", this.c.c);
    f.a("Launcher.Model", "mAllAppsList.modified", this.c.d);
    if (this.C != null)
    {
      this.C.c();
      return;
    }
    Log.d("Launcher.Model", "mLoaderTask=null");
  }
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    String str2 = paramIntent.getAction();
    boolean bool = paramIntent.getBooleanExtra("livewallpaper", false);
    String str1;
    if (("android.intent.action.PACKAGE_CHANGED".equals(str2)) || ("android.intent.action.PACKAGE_REMOVED".equals(str2)) || (("android.intent.action.PACKAGE_ADDED".equals(str2)) && (!bool)))
    {
      str1 = paramIntent.getData().getSchemeSpecificPart();
      bool = paramIntent.getBooleanExtra("android.intent.extra.REPLACING", false);
      if ((str1 == null) || (str1.length() == 0)) {
        break label86;
      }
      label86:
      while (com.cyou.cma.b.g.equals(str1)) {
        return;
      }
      if ((com.cyou.elegant.theme.a.b.a(paramContext, str1)) && (!"android.intent.action.PACKAGE_ADDED".equals(str2))) {}
    }
    for (;;)
    {
      try
      {
        new com.cyou.elegant.theme.a.b(paramContext).a(str1);
        if ("android.intent.action.PACKAGE_CHANGED".equals(str2)) {
          break label234;
        }
        if (!"android.intent.action.PACKAGE_REMOVED".equals(str2)) {
          break label213;
        }
        if (bool) {
          break label493;
        }
        i1 = 3;
        if (i1 == 0) {
          break label86;
        }
        if (!this.H) {
          break label239;
        }
        a(new ct(this, i1, new String[] { str1 }));
        return;
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
        continue;
      }
      "android.intent.action.PACKAGE_REMOVED".equals(str2);
      continue;
      label213:
      if ("android.intent.action.PACKAGE_ADDED".equals(str2))
      {
        if (!bool)
        {
          i1 = 1;
          continue;
        }
        label234:
        i1 = 2;
        continue;
        label239:
        paramContext = new cs(this);
        paramContext.a = i1;
        paramContext.b = new String[] { str1 };
        this.k.add(paramContext);
        return;
        if ("android.intent.action.EXTERNAL_APPLICATIONS_AVAILABLE".equals(str2))
        {
          paramContext = paramIntent.getStringArrayExtra("android.intent.extra.changed_package_list");
          if (this.I)
          {
            a(new ct(this, 5, paramContext));
            return;
          }
          paramIntent = new cs(this);
          paramIntent.a = 5;
          paramIntent.b = paramContext;
          this.l.add(paramIntent);
          return;
        }
        if ("android.intent.action.EXTERNAL_APPLICATIONS_UNAVAILABLE".equals(str2)) {
          break;
        }
        if ("android.intent.action.LOCALE_CHANGED".equals(str2))
        {
          o();
          return;
        }
        if ("android.intent.action.CONFIGURATION_CHANGED".equals(str2))
        {
          paramContext = paramContext.getResources().getConfiguration();
          if (this.j != paramContext.mcc)
          {
            Log.d("Launcher.Model", "Reload apps on config change. curr_mcc:" + paramContext.mcc + " prevmcc:" + this.j);
            o();
          }
          this.j = paramContext.mcc;
          return;
        }
        if (((!"android.search.action.GLOBAL_SEARCH_ACTIVITY_CHANGED".equals(str2)) && (!"android.search.action.SEARCHABLES_CHANGED".equals(str2))) || (this.O == null)) {
          break;
        }
        this.O.get();
        return;
      }
      label493:
      int i1 = 0;
    }
  }
}
