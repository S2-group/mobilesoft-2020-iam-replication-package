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
import com.cyou.cma.as;
import java.lang.ref.WeakReference;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
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
  static final HashMap<Long, bv> d;
  static final ArrayList<bv> e;
  static final ArrayList<ch> f;
  static final ArrayList<cl> g;
  static final HashMap<Long, az> h;
  static final HashMap<Object, byte[]> i;
  public static final Comparator<am> m = new Comparator() {};
  public static final Comparator<f> n = new Comparator() {};
  public static final Comparator<Folder> o = new Comparator() {};
  public static final Comparator<f> p = new Comparator() {};
  public static final Comparator<f> q = new Comparator() {};
  public static final Comparator<am> r = new Comparator() {};
  public static final Comparator<bv> s = new Comparator() {};
  public static final Comparator<f> t = new Comparator() {};
  public static final Comparator<f> u = new Comparator() {};
  public static final Comparator<AppWidgetProviderInfo> v = new Comparator() {};
  private final Object A = new Object();
  private ab B = new ab();
  private co C;
  private boolean G;
  private boolean H;
  private boolean I;
  private boolean J;
  private boolean K;
  private boolean L;
  private boolean M;
  private boolean N = false;
  private WeakReference<cm> O;
  private bp P;
  private Bitmap Q;
  private int T;
  private cn U;
  boolean a = true;
  at b;
  public c c;
  protected int j;
  public ArrayList<cp> k = new ArrayList();
  public ArrayList<cp> l = new ArrayList();
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
  LauncherModel(LauncherApplication paramLauncherApplication, bp paramBp)
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
      this.c = new c(paramBp);
      this.P = paramBp;
      try
      {
        this.Q = eg.a(this.P.b(), paramLauncherApplication, 2);
        paramBp = paramLauncherApplication.getResources();
        this.y = paramBp.getInteger(2131623939);
        this.x = paramBp.getInteger(2131623940);
        this.j = paramBp.getConfiguration().mcc;
        a.a.a(paramLauncherApplication.getBaseContext());
        return;
        bool = false;
        continue;
        this.w = "mounted".equals(Environment.getExternalStorageState());
      }
      catch (Exception paramBp)
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
      paramCursor = eg.a(BitmapFactory.decodeByteArray(paramCursor, 0, paramCursor.length), paramContext);
      return paramCursor;
    }
    catch (Exception paramCursor) {}
    return null;
  }
  
  public static Handler a()
  {
    return F;
  }
  
  static az a(Context paramContext, HashMap<Long, az> paramHashMap, long paramLong)
  {
    Object localObject = null;
    Cursor localCursor = paramContext.getContentResolver().query(cw.a, null, "_id=? and (itemType=? or itemType=?)", new String[] { String.valueOf(paramLong), "2" }, null);
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
  
  private ec a(Cursor paramCursor, Context paramContext, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, Intent paramIntent)
  {
    Object localObject2 = null;
    Object localObject3 = null;
    ec localEc = new ec();
    localEc.r = 1;
    localEc.a = paramCursor.getString(paramInt5);
    switch (paramCursor.getInt(paramInt1))
    {
    default: 
      paramCursor = Bitmap.createBitmap(this.Q);
      localEc.d = true;
      localEc.c = false;
    }
    for (;;)
    {
      localEc.f = paramCursor;
      return localEc;
      String str1 = paramCursor.getString(paramInt2);
      String str2 = paramCursor.getString(paramInt3);
      Object localObject4 = paramContext.getPackageManager();
      localEc.c = false;
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
            paramIntent = eg.a(paramIntent, paramContext, paramInt1);
          }
          localObject1 = paramIntent;
          localEc.c = true;
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
        localEc.d = true;
        break;
        paramInt1 = 1;
      }
      paramCursor = a(paramCursor, paramInt4, paramContext);
      if (paramCursor == null)
      {
        paramCursor = Bitmap.createBitmap(this.Q);
        localEc.c = false;
        localEc.d = true;
      }
      else
      {
        localEc.c = true;
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
  static ArrayList<bv> a(Context paramContext)
  {
    // Byte code:
    //   0: new 181	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 182	java/util/ArrayList:<init>	()V
    //   7: astore 8
    //   9: aload_0
    //   10: invokevirtual 416	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   13: getstatic 421	com/cyou/cma/clauncher/cw:a	Landroid/net/Uri;
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
    //   154: new 576	com/cyou/cma/clauncher/bv
    //   157: dup
    //   158: invokespecial 577	com/cyou/cma/clauncher/bv:<init>	()V
    //   161: astore 9
    //   163: aload 9
    //   165: aload_0
    //   166: iload 4
    //   168: invokeinterface 457 2 0
    //   173: putfield 578	com/cyou/cma/clauncher/bv:u	I
    //   176: aload 9
    //   178: aload_0
    //   179: iload 5
    //   181: invokeinterface 457 2 0
    //   186: putfield 579	com/cyou/cma/clauncher/bv:v	I
    //   189: aload 9
    //   191: aload_0
    //   192: iload 6
    //   194: invokeinterface 457 2 0
    //   199: putfield 581	com/cyou/cma/clauncher/bv:w	I
    //   202: aload 9
    //   204: aload_0
    //   205: iload 7
    //   207: invokeinterface 457 2 0
    //   212: putfield 582	com/cyou/cma/clauncher/bv:x	I
    //   215: aload 9
    //   217: aload_0
    //   218: iload_2
    //   219: invokeinterface 457 2 0
    //   224: i2l
    //   225: putfield 583	com/cyou/cma/clauncher/bv:s	J
    //   228: aload 9
    //   230: aload_0
    //   231: iload_1
    //   232: invokeinterface 457 2 0
    //   237: putfield 584	com/cyou/cma/clauncher/bv:r	I
    //   240: aload 9
    //   242: aload_0
    //   243: iload_3
    //   244: invokeinterface 457 2 0
    //   249: putfield 585	com/cyou/cma/clauncher/bv:t	I
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
    //   161	94	9	localBv	bv
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
  
  private static void a(Context paramContext, final ContentValues paramContentValues, bv paramBv, final String paramString)
  {
    final long l1 = paramBv.q;
    final Uri localUri = cw.a(l1);
    paramContext = new Runnable()
    {
      public final void run()
      {
        try
        {
          LauncherModel.this.update(localUri, paramContentValues, null, null);
          bv localBv = (bv)LauncherModel.d.get(Long.valueOf(l1));
          if (localBv == null) {}
          label131:
          do
          {
            return;
            if (paramString != localBv)
            {
              StringBuilder localStringBuilder = new StringBuilder("item: ");
              if (paramString != null)
              {
                str = paramString.toString();
                localStringBuilder = localStringBuilder.append(str).append(" modelItem: ");
                if (localBv == null) {
                  break label131;
                }
              }
              for (String str = localBv.toString();; str = "null")
              {
                Log.e("Launcher.Model", str + "Error: ItemInfo passed to " + this.f + " doesn't match original");
                return;
                str = "null";
                break;
              }
            }
            if ((localBv.s != -100L) && (localBv.s != -101L)) {
              break;
            }
          } while (LauncherModel.e.contains(localBv));
          LauncherModel.e.add(localBv);
          return;
          LauncherModel.e.remove(localBv);
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
  
  static void a(Context paramContext, final az paramAz)
  {
    paramContext = new Runnable()
    {
      public final void run()
      {
        LauncherModel.this.delete(cw.a(paramAz.q), null, null);
        LauncherModel.d.remove(Long.valueOf(paramAz.q));
        LauncherModel.h.remove(Long.valueOf(paramAz.q));
        LauncherModel.i.remove(paramAz);
        LauncherModel.e.remove(paramAz);
        LauncherModel.this.delete(cw.b, "container=" + paramAz.q, null);
        Iterator localIterator = ((et)paramAz).a.iterator();
        while (localIterator.hasNext())
        {
          bv localBv = (bv)localIterator.next();
          LauncherModel.d.remove(Long.valueOf(localBv.q));
          LauncherModel.i.remove(localBv);
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
  
  static void a(Context paramContext, bv paramBv)
  {
    ContentValues localContentValues = new ContentValues();
    if (paramBv.s == -200L)
    {
      paramBv = (am)paramBv;
      localContentValues.put("title", paramBv.l.toString());
      b.a(paramContext, localContentValues, paramBv, false);
      return;
    }
    paramBv.a(localContentValues);
    paramBv.y = false;
    a(paramContext, localContentValues, paramBv, "updateItemInDatabase");
  }
  
  static void a(Context paramContext, bv paramBv, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    paramBv.w = paramInt3;
    paramBv.x = paramInt4;
    paramBv.u = paramInt1;
    paramBv.v = paramInt2;
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("container", Long.valueOf(paramBv.s));
    localContentValues.put("spanX", Integer.valueOf(paramInt3));
    localContentValues.put("spanY", Integer.valueOf(paramInt4));
    localContentValues.put("cellX", Integer.valueOf(paramInt1));
    localContentValues.put("cellY", Integer.valueOf(paramInt2));
    a(paramContext, localContentValues, paramBv, "resizeItemInDatabase");
  }
  
  static void a(Context paramContext, bv paramBv, long paramLong, int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramBv.q == -1L)
    {
      c(paramContext, paramBv, paramLong, paramInt1, paramInt2, paramInt3);
      return;
    }
    b(paramContext, paramBv, paramLong, paramInt1, paramInt2, paramInt3);
  }
  
  private static void a(cq paramCq)
  {
    F.post(paramCq);
  }
  
  static boolean a(Context paramContext, String paramString, Intent paramIntent)
  {
    boolean bool = false;
    paramIntent.setFlags(268435456);
    if (paramIntent.getComponent() != null) {
      paramIntent.setPackage(paramIntent.getComponent().getPackageName());
    }
    paramIntent = paramIntent.toUri(0);
    paramContext = paramContext.getContentResolver().query(cw.a, new String[] { "title", "intent" }, "title=?", new String[] { paramString }, null);
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
  
  static void b(final Context paramContext, final bv paramBv)
  {
    paramContext = new Runnable()
    {
      public final void run()
      {
        try
        {
          LauncherModel.this.delete(this.b, null, null);
          switch (paramBv.r)
          {
          }
          for (;;)
          {
            LauncherModel.d.remove(Long.valueOf(paramBv.q));
            LauncherModel.i.remove(paramBv);
            return;
            LauncherModel.h.remove(Long.valueOf(paramBv.q));
            LauncherModel.e.remove(paramBv);
            continue;
            Object localObject;
            ComponentName localComponentName;
            String str;
            if ((paramBv instanceof ec))
            {
              localObject = (ec)paramBv;
              if (!TextUtils.isEmpty(((ec)localObject).j)) {
                break label323;
              }
              if ((paramContext != null) && (((ec)localObject).b != null))
              {
                localComponentName = ((ec)localObject).b.getComponent();
                if (localComponentName != null)
                {
                  str = localComponentName.getClassName();
                  if ((!"com.cyou.cma.opti.center.OptiCenterActivity".equals(str)) && (!com.cyou.cma.b.j.equals(str)))
                  {
                    if (!"com.cyou.cma.recommend.RecommendAppActivity".equals(str)) {
                      break label244;
                    }
                    "CLocker".equals(((ec)localObject).b.getStringExtra("recommend_app"));
                  }
                }
              }
            }
            for (;;)
            {
              LauncherModel.e.remove(paramBv);
              break;
              label244:
              if (!"com.cyou.cma.clockscreen.activity.SplashActivity".equals(str))
              {
                localObject = localComponentName.getPackageName();
                if ((!"com.mobogenie.markets".equals(localObject)) && (!"com.mobogenie".equals(localObject)) && (!"com.android.vending".equals(localObject))) {
                  if ("com.cyou.cma.recommend.RecommendDownloadActivity".equals(str))
                  {
                    ((ec)paramBv).a.toString();
                  }
                  else
                  {
                    "com.cyou.cma.cleanmemory.CleanMemoryWidgetInfo".equals(str);
                    continue;
                    label323:
                    if ((!"mms".equals(((ec)localObject).j)) && (!"dial".equals(((ec)localObject).j)) && (!"camera".equals(((ec)localObject).j)) && (!"browser".equals(((ec)localObject).j)) && (!"setting".equals(((ec)localObject).j))) {
                      "gallery".equals(((ec)localObject).j);
                    }
                  }
                }
              }
            }
            LauncherModel.f.remove((ch)paramBv);
            continue;
            LauncherModel.g.remove((cl)paramBv);
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
  
  static void b(Context paramContext, bv paramBv, long paramLong, int paramInt1, int paramInt2, int paramInt3)
  {
    paramBv.s = paramLong;
    paramBv.u = paramInt2;
    paramBv.v = paramInt3;
    if (((paramContext instanceof Launcher)) && (paramLong == -101L)) {}
    for (paramBv.t = ((Launcher)paramContext).K().a(paramInt2, paramInt3);; paramBv.t = paramInt1)
    {
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("container", Long.valueOf(paramBv.s));
      localContentValues.put("cellX", Integer.valueOf(paramBv.u));
      localContentValues.put("cellY", Integer.valueOf(paramBv.v));
      localContentValues.put("screen", Integer.valueOf(paramBv.t));
      if (paramBv.s >= 0L) {
        localContentValues.put("app_index", Integer.valueOf(((ec)paramBv).H));
      }
      a(paramContext, localContentValues, paramBv, "moveItemInDatabase");
      return;
    }
  }
  
  public static int c()
  {
    return R;
  }
  
  private static az c(HashMap<Long, az> paramHashMap, long paramLong)
  {
    az localAz = (az)paramHashMap.get(Long.valueOf(paramLong));
    Object localObject = localAz;
    if (localAz == null)
    {
      localObject = new et();
      paramHashMap.put(Long.valueOf(paramLong), localObject);
    }
    return localObject;
  }
  
  static void c(Context paramContext, final bv paramBv, long paramLong, int paramInt1, int paramInt2, int paramInt3)
  {
    paramBv.s = paramLong;
    paramBv.u = paramInt2;
    paramBv.v = paramInt3;
    final ContentValues localContentValues;
    ContentResolver localContentResolver;
    if (((paramContext instanceof Launcher)) && (paramInt1 < 0) && (paramLong == -101L))
    {
      paramBv.t = ((Launcher)paramContext).K().a(paramInt2, paramInt3);
      localContentValues = new ContentValues();
      localContentResolver = paramContext.getContentResolver();
      paramBv.a(localContentValues);
      paramContext = (LauncherApplication)paramContext.getApplicationContext();
      if ((paramContext != null) && (paramContext.e != null)) {
        break label223;
      }
    }
    label223:
    for (paramBv.q = SystemClock.uptimeMillis();; paramBv.q = paramContext.e.a())
    {
      localContentValues.put("folder_type", paramBv.D);
      localContentValues.put("app_index", Integer.valueOf(paramBv.H));
      localContentValues.put("_id", Long.valueOf(paramBv.q));
      paramInt1 = paramBv.u;
      paramInt2 = paramBv.v;
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
              localUri = cw.a;
              localContentResolver.insert(localUri, localContentValues);
            }
            catch (Exception localException)
            {
              Uri localUri;
              continue;
            }
            if (!LauncherModel.d.containsKey(Long.valueOf(paramBv.q))) {
              continue;
            }
            Log.e("LauncherModel", "Error: ItemInfo id (" + paramBv.q + ") passed to addItemToDatabase already exists." + paramBv.toString());
            return;
            localUri = cw.b;
          }
          LauncherModel.d.put(Long.valueOf(paramBv.q), paramBv);
          switch (paramBv.r)
          {
          case 3: 
          case 6: 
          default: 
            return;
          case 0: 
          case 1: 
          case 2: 
            while ((paramBv.s == -100L) || (paramBv.s == -101L))
            {
              LauncherModel.e.add(paramBv);
              return;
              LauncherModel.h.put(Long.valueOf(paramBv.q), (az)paramBv);
            }
          case 4: 
            LauncherModel.f.add((ch)paramBv);
            return;
          case 5: 
            LauncherModel.e.add(paramBv);
            return;
          }
          LauncherModel.g.add((cl)paramBv);
        }
      };
      if (E.getThreadId() != Process.myTid()) {
        break label237;
      }
      paramContext.run();
      return;
      paramBv.t = paramInt1;
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
          if (as.b(((ResolveInfo)localObject3).activityInfo.applicationInfo.packageName, ((ResolveInfo)localObject3).activityInfo.name)) {
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
        b.a(paramContext, (am)localObject2, false);
      }
      return localArrayList;
      return null;
    }
  }
  
  private ArrayList<f> e(Context paramContext)
  {
    Object localObject2 = paramContext.getPackageManager();
    localObject1 = Collections.emptyList();
    try
    {
      localObject2 = ((PackageManager)localObject2).getInstalledApplications(8192);
      localObject1 = localObject2;
    }
    catch (Exception localException)
    {
      for (;;) {}
      this.T = 0;
      Iterator localIterator = this.c.a.iterator();
      while (localIterator.hasNext())
      {
        f localF = (f)localIterator.next();
        if ((localF.h) && (!a((List)localObject1, localF.d.getPackageName())))
        {
          localIterator.remove();
          localException.add(localF);
          b.a(paramContext, localF.q);
        }
      }
      return localException;
    }
    localObject2 = new ArrayList();
    if ((this.T > 0) && (((List)localObject1).size() != this.T))
    {
      this.T = 0;
      return localObject2;
    }
  }
  
  private void n()
  {
    for (;;)
    {
      synchronized (this.A)
      {
        o();
        this.H = false;
        this.G = false;
        if (this.O != null)
        {
          ??? = (cm)this.O.get();
          if ((??? != null) && (!((cm)???).S()))
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
  
  private boolean o()
  {
    boolean bool1 = false;
    boolean bool2 = false;
    co localCo = this.C;
    if (localCo != null)
    {
      bool1 = bool2;
      if (localCo.a()) {
        bool1 = true;
      }
      localCo.b();
    }
    return bool1;
  }
  
  final ec a(Context paramContext, Intent paramIntent)
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
    ec localEc = new ec();
    if ((localParcelable != null) && ((localParcelable instanceof Bitmap)))
    {
      paramContext = eg.a(new ap((Bitmap)localParcelable), paramContext, 1);
      localEc.g = ((Bitmap)localParcelable);
      paramIntent = (Intent)localObject;
    }
    for (;;)
    {
      localObject = paramContext;
      if (paramContext == null)
      {
        localObject = Bitmap.createBitmap(this.Q);
        localEc.d = true;
      }
      localEc.f = ((Bitmap)localObject);
      localEc.a = str;
      localEc.b = localIntent;
      localEc.e = paramIntent;
      localEc.c = true;
      return localEc;
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
              localEc.g = ((BitmapDrawable)localObject).getBitmap();
            }
            paramContext = eg.a((Drawable)localObject, paramContext, 1);
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
  
  public final ec a(PackageManager paramPackageManager, Intent paramIntent, Context paramContext)
  {
    return a(paramPackageManager, paramIntent, paramContext, null, -1, -1, null);
  }
  
  public final ec a(PackageManager paramPackageManager, Intent paramIntent, Context paramContext, Cursor paramCursor, int paramInt1, int paramInt2, HashMap<Object, CharSequence> paramHashMap)
  {
    Bitmap localBitmap = null;
    localComponentName = paramIntent.getComponent();
    if (localComponentName == null) {}
    for (;;)
    {
      return null;
      ec localEc = new ec();
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
          localEc.a = localNameNotFoundException.activityInfo.loadLabel(paramPackageManager);
          if (paramHashMap != null) {
            paramHashMap.put(paramIntent, localEc.a);
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
      localEc.d = true;
    }
    localEc.f = paramContext;
    if (localResolveInfo != null)
    {
      paramIntent = a(localResolveInfo);
      if ((paramHashMap != null) && (paramHashMap.containsKey(paramIntent))) {
        localEc.a = ((CharSequence)paramHashMap.get(paramIntent));
      }
    }
    else
    {
      if ((localEc.a == null) && (paramCursor != null)) {
        localEc.a = paramCursor.getString(paramInt2);
      }
      if (localEc.a == null) {
        localEc.a = localComponentName.getClassName();
      }
      localEc.r = 0;
      return localEc;
    }
  }
  
  public final ec a(PackageManager paramPackageManager, Intent paramIntent, HashMap<Object, CharSequence> paramHashMap, String paramString)
  {
    ec localEc = new ec();
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
        localEc.a = ((CharSequence)paramHashMap.get(localComponentName2));
      }
    }
    else
    {
      if (paramIntent == null) {
        break label139;
      }
    }
    label139:
    for (paramPackageManager = this.P.a(localComponentName1, localEc.a.toString(), paramString);; paramPackageManager = null)
    {
      localEc.r = 0;
      localEc.f = paramPackageManager;
      return localEc;
      localEc.a = paramIntent.activityInfo.loadLabel(paramPackageManager);
      if (paramHashMap == null) {
        break;
      }
      paramHashMap.put(localComponentName2, localEc.a);
      break;
    }
  }
  
  @TargetApi(12)
  final void a(Context paramContext, ec paramEc, byte[] paramArrayOfByte)
  {
    i1 = 0;
    if (paramArrayOfByte != null) {}
    for (;;)
    {
      try
      {
        paramArrayOfByte = BitmapFactory.decodeByteArray(paramArrayOfByte, 0, paramArrayOfByte.length);
        Bitmap localBitmap = paramEc.a(this.P);
        if (!com.cyou.cma.clauncher.b.c.d()) {
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
        paramEc.y = true;
        a(paramContext, paramEc);
      }
      return;
      i1 = 1;
      continue;
      i1 = 1;
    }
  }
  
  public final void a(final Context paramContext, final ArrayList<ContentProviderOperation> paramArrayList, final ArrayList<bv> paramArrayList1)
  {
    paramContext = new Runnable()
    {
      public final void run()
      {
        int j = paramArrayList1.size();
        int i = 0;
        if (i < j)
        {
          bv localBv1 = (bv)paramArrayList1.get(i);
          switch (localBv1.r)
          {
          }
          for (;;)
          {
            LauncherModel.d.remove(Long.valueOf(localBv1.q));
            LauncherModel.i.remove(localBv1);
            i += 1;
            break;
            LauncherModel.h.remove(Long.valueOf(localBv1.q));
            LauncherModel.e.remove(localBv1);
            Iterator localIterator = paramArrayList1.iterator();
            while (localIterator.hasNext())
            {
              bv localBv2 = (bv)localIterator.next();
              LauncherModel.d.remove(Long.valueOf(localBv2.q));
              LauncherModel.i.remove(localBv2);
            }
            LauncherModel.e.remove(localBv1);
            continue;
            LauncherModel.f.remove((ch)localBv1);
            continue;
            LauncherModel.g.remove((cl)localBv1);
          }
        }
        try
        {
          paramContext.getContentResolver().applyBatch("com.phone.launcher.lite.settings", paramArrayList);
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
            if (!o()) {
              break label88;
            }
            break label83;
            this.C = new co(this, paramContext, paramBoolean1, paramBoolean2);
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
  
  public final void a(cm paramCm)
  {
    synchronized (this.A)
    {
      this.O = new WeakReference(paramCm);
      return;
    }
  }
  
  final boolean a(HashMap<Object, byte[]> paramHashMap, ec paramEc, Cursor paramCursor, int paramInt)
  {
    if (!this.w) {}
    while ((paramEc.c) || (paramEc.d)) {
      return false;
    }
    paramHashMap.put(paramEc, paramCursor.getBlob(paramInt));
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
      this.U = new cn(this, paramContext);
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
        final cm localCm = (cm)LauncherModel.e(LauncherModel.this).get();
        if (localCm != null) {
          LauncherModel.d(LauncherModel.this).a(new Runnable()
          {
            public final void run()
            {
              localCm.a(localArrayList1, localArrayList2);
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
      co.a(new co(this, this.z, false));
    }
  }
  
  public final void f()
  {
    if (this.L)
    {
      this.L = false;
      Log.i("app2", "delayBindDrawer");
      co.b(new co(this, this.z, false));
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
        a(new cq(this, i1, new String[] { str1 }));
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
        paramContext = new cp(this);
        paramContext.a = i1;
        paramContext.b = new String[] { str1 };
        this.k.add(paramContext);
        return;
        if ("android.intent.action.EXTERNAL_APPLICATIONS_AVAILABLE".equals(str2))
        {
          paramContext = paramIntent.getStringArrayExtra("android.intent.extra.changed_package_list");
          if (this.I)
          {
            a(new cq(this, 5, paramContext));
            return;
          }
          paramIntent = new cp(this);
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
          n();
          return;
        }
        if ("android.intent.action.CONFIGURATION_CHANGED".equals(str2))
        {
          paramContext = paramContext.getResources().getConfiguration();
          if (this.j != paramContext.mcc)
          {
            Log.d("Launcher.Model", "Reload apps on config change. curr_mcc:" + paramContext.mcc + " prevmcc:" + this.j);
            n();
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
