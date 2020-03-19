package com.android.launcher3;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import com.android.launcher3.b.f;
import com.android.launcher3.b.i;
import com.android.launcher3.b.o;
import com.android.launcher3.b.p;
import com.android.launcher3.timmystudios.utilities.g;
import com.android.launcher3.util.a;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.Stack;

public class ac
{
  static final Object a = new Object();
  final az b = new az();
  final p c;
  final b d;
  final Handler e;
  private final HashMap<o, Bitmap> f = new HashMap();
  private final Context g;
  private final PackageManager h;
  private final i i;
  private final HashMap<a, a> j = new HashMap(50);
  private final int k;
  private final int l;
  private final int m;
  private final BitmapFactory.Options n;
  private String o;
  private Bitmap p;
  private Canvas q;
  private Paint r;
  
  public ac(Context paramContext, ag paramAg)
  {
    this.g = paramContext;
    this.h = paramContext.getPackageManager();
    this.c = p.a(this.g);
    this.i = i.a(this.g);
    this.k = paramAg.k;
    this.d = new b(paramContext);
    this.e = new Handler(ar.h());
    this.l = paramContext.getResources().getColor(2131099934);
    this.m = paramContext.getResources().getColor(2131099935);
    this.n = new BitmapFactory.Options();
    this.n.inPreferredConfig = Bitmap.Config.RGB_565;
    b();
  }
  
  private ContentValues a(Bitmap paramBitmap, String paramString, int paramInt)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("icon", bj.a(paramBitmap));
    localContentValues.put("label", paramString);
    localContentValues.put("system_state", this.o);
    if (paramInt == 0)
    {
      localContentValues.put("icon_low_res", bj.a(Bitmap.createScaledBitmap(paramBitmap, paramBitmap.getWidth() / 5, paramBitmap.getHeight() / 5, true)));
      return localContentValues;
    }
    try
    {
      if (this.p == null)
      {
        this.p = Bitmap.createBitmap(paramBitmap.getWidth() / 5, paramBitmap.getHeight() / 5, Bitmap.Config.RGB_565);
        this.q = new Canvas(this.p);
        this.r = new Paint(3);
      }
      this.q.drawColor(paramInt);
      this.q.drawBitmap(paramBitmap, new Rect(0, 0, paramBitmap.getWidth(), paramBitmap.getHeight()), new Rect(0, 0, this.p.getWidth(), this.p.getHeight()), this.r);
      localContentValues.put("icon_low_res", bj.a(this.p));
      return localContentValues;
    }
    finally {}
  }
  
  private Bitmap a(ComponentName paramComponentName, Drawable paramDrawable, Context paramContext)
  {
    if (g.a() != null)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramComponentName.getPackageName());
      localStringBuilder.append("/");
      localStringBuilder.append(paramComponentName.getClassName());
      paramComponentName = localStringBuilder.toString();
      paramComponentName = g.a().e(paramComponentName);
      if (paramComponentName != null) {
        return paramComponentName;
      }
    }
    return bj.a(paramDrawable, paramContext);
  }
  
  private static Bitmap a(Cursor paramCursor, int paramInt, BitmapFactory.Options paramOptions)
  {
    paramCursor = paramCursor.getBlob(paramInt);
    try
    {
      paramCursor = BitmapFactory.decodeByteArray(paramCursor, 0, paramCursor.length, paramOptions);
      return paramCursor;
    }
    catch (Exception paramCursor)
    {
      for (;;) {}
    }
    return null;
  }
  
  private Bitmap a(a paramA, o paramO)
  {
    if (paramA.a == null) {
      return a(paramO);
    }
    return paramA.a;
  }
  
  private Drawable a()
  {
    return a(Resources.getSystem(), 17629184);
  }
  
  private Drawable a(Resources paramResources, int paramInt)
  {
    try
    {
      paramResources = paramResources.getDrawableForDensity(paramInt, this.k);
    }
    catch (Resources.NotFoundException paramResources)
    {
      for (;;) {}
    }
    paramResources = null;
    if (paramResources != null) {
      return paramResources;
    }
    return a();
  }
  
  private a a(ComponentName paramComponentName, f paramF, o paramO, boolean paramBoolean1, boolean paramBoolean2)
  {
    a localA = new a(paramComponentName, paramO);
    a localA2 = (a)this.j.get(localA);
    a localA1 = localA2;
    if (localA2 == null) {
      localA1 = new a();
    }
    if (paramF != null)
    {
      localA1.a = a(paramComponentName, paramF.a(this.k), this.g);
      if (TextUtils.isEmpty(localA1.b))
      {
        localA1.b = paramF.c();
        localA1.c = this.c.a(localA1.b, paramO);
      }
    }
    else
    {
      if (paramBoolean1)
      {
        paramComponentName = a(paramComponentName.getPackageName(), paramO, false);
        if (paramComponentName != null)
        {
          localA1.a = paramComponentName.a;
          localA1.b = paramComponentName.b;
          localA1.c = paramComponentName.c;
        }
      }
      if (localA1.a == null) {
        localA1.a = a(paramO);
      }
    }
    this.j.put(localA, localA1);
    return localA1;
  }
  
  private a a(String paramString, o paramO, boolean paramBoolean)
  {
    a localA = d(paramString, paramO);
    a localA2 = (a)this.j.get(localA);
    i1 = 8192;
    i2 = 0;
    a localA1;
    if ((localA2 != null) && ((!localA2.d) || (paramBoolean))) {
      localA1 = localA2;
    }
    try
    {
      if (o.a().equals(paramO)) {
        i1 = 0;
      }
      localA1 = localA2;
      paramString = this.h.getPackageInfo(paramString, i1).applicationInfo;
      if (paramString == null)
      {
        localA1 = localA2;
        throw new PackageManager.NameNotFoundException("ApplicationInfo is null");
      }
      localA1 = localA2;
      paramString = this.c.a(paramString.loadIcon(this.h), paramO);
      localA1 = localA2;
      localA2.a = a(localA.b, paramString, this.g);
      localA1 = localA2;
      break label387;
      localA2 = new a();
      localA1 = localA2;
      if (!a(localA, localA2, paramBoolean))
      {
        localA1 = localA2;
        if (o.a().equals(paramO)) {
          i1 = 0;
        }
        localA1 = localA2;
        paramString = this.h.getPackageInfo(paramString, i1);
        localA1 = localA2;
        ApplicationInfo localApplicationInfo = paramString.applicationInfo;
        if (localApplicationInfo == null)
        {
          localA1 = localA2;
          throw new PackageManager.NameNotFoundException("ApplicationInfo is null");
        }
        localA1 = localA2;
        Drawable localDrawable = this.c.a(localApplicationInfo.loadIcon(this.h), paramO);
        localA1 = localA2;
        localA2.a = a(localA.b, localDrawable, this.g);
        localA1 = localA2;
        localA2.b = localApplicationInfo.loadLabel(this.h);
        localA1 = localA2;
        localA2.c = this.c.a(localA2.b, paramO);
        localA1 = localA2;
        localA2.d = false;
        localA1 = localA2;
        a(a(localA2.a, localA2.b.toString(), this.m), localA.b, paramString, this.c.a(paramO));
        localA1 = localA2;
      }
      label387:
      i1 = 1;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      for (;;)
      {
        i1 = i2;
      }
    }
    if (i1 != 0) {
      this.j.put(localA, localA1);
    }
    return localA1;
  }
  
  private void a(ContentValues paramContentValues, ComponentName paramComponentName, PackageInfo paramPackageInfo, long paramLong)
  {
    paramContentValues.put("componentName", paramComponentName.flattenToString());
    paramContentValues.put("profileId", Long.valueOf(paramLong));
    paramContentValues.put("lastUpdated", Long.valueOf(paramPackageInfo.lastUpdateTime));
    paramContentValues.put("version", Integer.valueOf(paramPackageInfo.versionCode));
    this.d.getWritableDatabase().insertWithOnConflict("icons", null, paramContentValues, 5);
  }
  
  private void a(o paramO, List<f> paramList, Set<String> paramSet)
  {
    long l1 = this.c.a(paramO);
    Object localObject1 = this.g.getPackageManager();
    HashMap localHashMap = new HashMap();
    localObject1 = ((PackageManager)localObject1).getInstalledPackages(8192);
    if (localObject1 != null)
    {
      localObject1 = ((List)localObject1).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (PackageInfo)((Iterator)localObject1).next();
        localHashMap.put(((PackageInfo)localObject2).packageName, localObject2);
      }
    }
    localObject1 = new HashMap();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      localObject2 = (f)paramList.next();
      ((HashMap)localObject1).put(((f)localObject2).a(), localObject2);
    }
    paramList = this.d.getReadableDatabase();
    Object localObject2 = Long.toString(l1);
    localObject2 = paramList.query("icons", new String[] { "rowid", "componentName", "lastUpdated", "version", "system_state" }, "profileId = ? ", new String[] { localObject2 }, null, null, null);
    int i1 = ((Cursor)localObject2).getColumnIndex("componentName");
    int i3 = ((Cursor)localObject2).getColumnIndex("lastUpdated");
    int i2 = ((Cursor)localObject2).getColumnIndex("version");
    int i4 = ((Cursor)localObject2).getColumnIndex("rowid");
    int i5 = ((Cursor)localObject2).getColumnIndex("system_state");
    HashSet localHashSet = new HashSet();
    paramList = new Stack();
    while (((Cursor)localObject2).moveToNext())
    {
      ComponentName localComponentName = ComponentName.unflattenFromString(((Cursor)localObject2).getString(i1));
      PackageInfo localPackageInfo = (PackageInfo)localHashMap.get(localComponentName.getPackageName());
      if (localPackageInfo == null) {
        if (!paramSet.contains(localComponentName.getPackageName()))
        {
          a(localComponentName, paramO);
          localHashSet.add(Integer.valueOf(((Cursor)localObject2).getInt(i4)));
        }
      }
      while ((localPackageInfo.applicationInfo.flags & 0x1000000) != 0) {
        break;
      }
      long l2 = ((Cursor)localObject2).getLong(i3);
      int i6 = ((Cursor)localObject2).getInt(i2);
      f localF = (f)((HashMap)localObject1).remove(localComponentName);
      if ((i6 != localPackageInfo.versionCode) || (l2 != localPackageInfo.lastUpdateTime) || (!TextUtils.equals(this.o, ((Cursor)localObject2).getString(i5)))) {
        for (;;)
        {
          if (localF == null)
          {
            a(localComponentName, paramO);
            localHashSet.add(Integer.valueOf(((Cursor)localObject2).getInt(i4)));
          }
          else
          {
            paramList.add(localF);
          }
        }
      }
    }
    ((Cursor)localObject2).close();
    if (!localHashSet.isEmpty()) {
      this.d.getWritableDatabase().delete("icons", bj.a("rowid", localHashSet), null);
    }
    if ((!((HashMap)localObject1).isEmpty()) || (!paramList.isEmpty()))
    {
      paramO = new Stack();
      paramO.addAll(((HashMap)localObject1).values());
      new d(l1, localHashMap, paramO, paramList).a();
    }
  }
  
  private boolean a(a paramA, a paramA1, boolean paramBoolean)
  {
    Object localObject2 = this.d.getReadableDatabase();
    Object localObject1;
    if (paramBoolean) {
      localObject1 = "icon_low_res";
    } else {
      localObject1 = "icon";
    }
    String str1 = paramA.b.flattenToString();
    String str2 = Long.toString(this.c.a(paramA.c));
    localObject2 = ((SQLiteDatabase)localObject2).query("icons", new String[] { localObject1, "label" }, "componentName = ? AND profileId = ?", new String[] { str1, str2 }, null, null, null);
    for (;;)
    {
      try
      {
        if (((Cursor)localObject2).moveToNext())
        {
          if (paramBoolean)
          {
            localObject1 = this.n;
            paramA1.a = a((Cursor)localObject2, 0, (BitmapFactory.Options)localObject1);
            paramA1.d = paramBoolean;
            paramA1.b = ((Cursor)localObject2).getString(1);
            if (paramA1.b == null)
            {
              paramA1.b = "";
              paramA1.c = "";
            }
            else
            {
              paramA1.c = this.c.a(paramA1.b, paramA.c);
            }
            return true;
          }
        }
        else {
          return false;
        }
      }
      finally
      {
        ((Cursor)localObject2).close();
      }
      localObject1 = null;
    }
  }
  
  private Bitmap b(o paramO)
  {
    Object localObject = a();
    paramO = this.c.a((Drawable)localObject, paramO);
    localObject = Bitmap.createBitmap(Math.max(paramO.getIntrinsicWidth(), 1), Math.max(paramO.getIntrinsicHeight(), 1), Bitmap.Config.ARGB_8888);
    Canvas localCanvas = new Canvas((Bitmap)localObject);
    paramO.setBounds(0, 0, ((Bitmap)localObject).getWidth(), ((Bitmap)localObject).getHeight());
    paramO.draw(localCanvas);
    localCanvas.setBitmap(null);
    return localObject;
  }
  
  private void b()
  {
    this.o = Locale.getDefault().toString();
  }
  
  private void c(String paramString, o paramO)
  {
    HashSet localHashSet = new HashSet();
    Iterator localIterator = this.j.keySet().iterator();
    while (localIterator.hasNext())
    {
      a localA = (a)localIterator.next();
      if ((localA.b.getPackageName().equals(paramString)) && (localA.c.equals(paramO))) {
        localHashSet.add(localA);
      }
    }
    paramString = localHashSet.iterator();
    while (paramString.hasNext())
    {
      paramO = (a)paramString.next();
      this.j.remove(paramO);
    }
  }
  
  private static a d(String paramString, o paramO)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString);
    localStringBuilder.append(".");
    return new a(new ComponentName(paramString, localStringBuilder.toString()), paramO);
  }
  
  ContentValues a(f paramF, boolean paramBoolean)
  {
    Object localObject3 = new a(paramF.a(), paramF.b());
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (!paramBoolean)
    {
      localObject3 = (a)this.j.get(localObject3);
      localObject1 = localObject2;
      if (localObject3 != null)
      {
        localObject1 = localObject2;
        if (!((a)localObject3).d) {
          if (((a)localObject3).a == null) {
            localObject1 = localObject2;
          } else {
            localObject1 = localObject3;
          }
        }
      }
    }
    localObject2 = localObject1;
    if (localObject1 == null)
    {
      localObject2 = new a();
      ((a)localObject2).a = bj.a(paramF.a(this.k), this.g);
    }
    ((a)localObject2).b = paramF.c();
    ((a)localObject2).c = this.c.a(((a)localObject2).b, paramF.b());
    this.j.put(new a(paramF.a(), paramF.b()), localObject2);
    return a(((a)localObject2).a, ((a)localObject2).b.toString(), this.l);
  }
  
  public Bitmap a(Intent paramIntent, o paramO)
  {
    try
    {
      ComponentName localComponentName = paramIntent.getComponent();
      if (localComponentName == null)
      {
        paramIntent = a(paramO);
        return paramIntent;
      }
      paramIntent = a(localComponentName, this.i.a(paramIntent, paramO), paramO, true, false).a;
      return paramIntent;
    }
    finally {}
  }
  
  public Bitmap a(o paramO)
  {
    try
    {
      if (!this.f.containsKey(paramO)) {
        this.f.put(paramO, b(paramO));
      }
      paramO = (Bitmap)this.f.get(paramO);
      return paramO;
    }
    finally {}
  }
  
  public Drawable a(ActivityInfo paramActivityInfo)
  {
    try
    {
      localResources = this.h.getResourcesForApplication(paramActivityInfo.applicationInfo);
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      Resources localResources;
      int i1;
      for (;;) {}
    }
    localResources = null;
    if (localResources != null)
    {
      i1 = paramActivityInfo.getIconResource();
      if (i1 != 0) {
        return a(localResources, i1);
      }
    }
    return a();
  }
  
  public Drawable a(String paramString, int paramInt)
  {
    try
    {
      paramString = this.h.getResourcesForApplication(paramString);
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      for (;;) {}
    }
    paramString = null;
    if ((paramString != null) && (paramInt != 0)) {
      return a(paramString, paramInt);
    }
    return a();
  }
  
  public c a(final BubbleTextView paramBubbleTextView, final ai paramAi)
  {
    paramBubbleTextView = new Runnable()
    {
      public void run()
      {
        if ((paramAi instanceof e))
        {
          ac.this.a((e)paramAi, null, false);
        }
        else
        {
          Object localObject;
          if ((paramAi instanceof bf))
          {
            bf localBf = (bf)paramAi;
            ac localAc = ac.this;
            if (localBf.z != null) {
              localObject = localBf.z;
            } else {
              localObject = localBf.a;
            }
            localAc.a(localBf, (Intent)localObject, localBf.v, false);
          }
          else if ((paramAi instanceof com.android.launcher3.d.d))
          {
            localObject = (com.android.launcher3.d.d)paramAi;
            ac.this.a(((com.android.launcher3.d.d)localObject).c, ((com.android.launcher3.d.d)localObject).v, false, (com.android.launcher3.d.d)localObject);
          }
        }
        ac.this.b.execute(new Runnable()
        {
          public void run()
          {
            ac.1.this.b.a(ac.1.this.a);
          }
        });
      }
    };
    this.e.post(paramBubbleTextView);
    return new c(paramBubbleTextView, this.e);
  }
  
  public void a(ComponentName paramComponentName, o paramO)
  {
    try
    {
      this.j.remove(new a(paramComponentName, paramO));
      return;
    }
    finally
    {
      paramComponentName = finally;
      throw paramComponentName;
    }
  }
  
  void a(f paramF, PackageInfo paramPackageInfo, long paramLong)
  {
    a(a(paramF, false), paramF.a(), paramPackageInfo, paramLong);
  }
  
  public void a(bf paramBf, ComponentName paramComponentName, f paramF, o paramO, boolean paramBoolean1, boolean paramBoolean2)
  {
    try
    {
      paramComponentName = a(paramComponentName, paramF, paramO, paramBoolean1, paramBoolean2);
      paramBf.a(a(paramComponentName, paramO));
      paramBf.s = bj.a(paramComponentName.b);
      paramBf.c = a(paramComponentName.a, paramO);
      paramBf.d = paramComponentName.d;
      return;
    }
    finally
    {
      paramBf = finally;
      throw paramBf;
    }
  }
  
  public void a(bf paramBf, Intent paramIntent, o paramO, boolean paramBoolean)
  {
    try
    {
      ComponentName localComponentName = paramIntent.getComponent();
      if (localComponentName == null)
      {
        paramBf.a(a(paramO));
        paramBf.s = "";
        paramBf.c = true;
        paramBf.d = false;
      }
      else
      {
        a(paramBf, localComponentName, this.i.a(paramIntent, paramO), paramO, true, paramBoolean);
      }
      return;
    }
    finally {}
  }
  
  public void a(e paramE)
  {
    try
    {
      a localA = a(paramE.e, null, paramE.v, false, paramE.c);
      if ((localA.a != null) && (!a(localA.a, paramE.v)))
      {
        paramE.s = bj.a(localA.b);
        paramE.b = localA.a;
        paramE.t = localA.c;
        paramE.c = localA.d;
      }
      return;
    }
    finally
    {
      paramE = finally;
      throw paramE;
    }
  }
  
  public void a(e paramE, f paramF, boolean paramBoolean)
  {
    if (paramF == null) {}
    try
    {
      o localO = paramE.v;
      break label21;
      localO = paramF.b();
      label21:
      paramF = a(paramE.e, paramF, localO, false, paramBoolean);
      paramE.s = bj.a(paramF.b);
      paramE.b = a(paramF, localO);
      paramE.t = paramF.c;
      paramE.c = paramF.d;
      return;
    }
    finally
    {
      for (;;) {}
    }
    throw paramE;
  }
  
  /* Error */
  public void a(String paramString, o paramO)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: aload_2
    //   5: invokevirtual 710	com/android/launcher3/ac:b	(Ljava/lang/String;Lcom/android/launcher3/b/o;)V
    //   8: aload_0
    //   9: getfield 85	com/android/launcher3/ac:h	Landroid/content/pm/PackageManager;
    //   12: aload_1
    //   13: sipush 8192
    //   16: invokevirtual 358	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   19: astore 5
    //   21: aload_0
    //   22: getfield 92	com/android/launcher3/ac:c	Lcom/android/launcher3/b/p;
    //   25: aload_2
    //   26: invokevirtual 396	com/android/launcher3/b/p:a	(Lcom/android/launcher3/b/o;)J
    //   29: lstore_3
    //   30: aload_0
    //   31: getfield 99	com/android/launcher3/ac:i	Lcom/android/launcher3/b/i;
    //   34: aload_1
    //   35: aload_2
    //   36: invokevirtual 713	com/android/launcher3/b/i:a	(Ljava/lang/String;Lcom/android/launcher3/b/o;)Ljava/util/List;
    //   39: invokeinterface 457 1 0
    //   44: astore_1
    //   45: aload_1
    //   46: invokeinterface 463 1 0
    //   51: ifeq +22 -> 73
    //   54: aload_0
    //   55: aload_1
    //   56: invokeinterface 467 1 0
    //   61: checkcast 307	com/android/launcher3/b/f
    //   64: aload 5
    //   66: lload_3
    //   67: invokevirtual 715	com/android/launcher3/ac:a	(Lcom/android/launcher3/b/f;Landroid/content/pm/PackageInfo;J)V
    //   70: goto -25 -> 45
    //   73: aload_0
    //   74: monitorexit
    //   75: return
    //   76: astore_1
    //   77: ldc_w 717
    //   80: ldc_w 719
    //   83: aload_1
    //   84: invokestatic 724	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   87: pop
    //   88: aload_0
    //   89: monitorexit
    //   90: return
    //   91: astore_1
    //   92: aload_0
    //   93: monitorexit
    //   94: aload_1
    //   95: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	96	0	this	ac
    //   0	96	1	paramString	String
    //   0	96	2	paramO	o
    //   29	38	3	l1	long
    //   19	46	5	localPackageInfo	PackageInfo
    // Exception table:
    //   from	to	target	type
    //   8	45	76	android/content/pm/PackageManager$NameNotFoundException
    //   45	70	76	android/content/pm/PackageManager$NameNotFoundException
    //   2	8	91	finally
    //   8	45	91	finally
    //   45	70	91	finally
    //   77	88	91	finally
  }
  
  public void a(String paramString, o paramO, Bitmap paramBitmap, CharSequence paramCharSequence)
  {
    try
    {
      c(paramString, paramO);
      a localA = d(paramString, paramO);
      paramO = (a)this.j.get(localA);
      paramString = paramO;
      if (paramO == null)
      {
        paramString = new a();
        this.j.put(localA, paramString);
      }
      if (!TextUtils.isEmpty(paramCharSequence)) {
        paramString.b = paramCharSequence;
      }
      if (paramBitmap != null) {
        paramString.a = bj.a(paramBitmap, this.g);
      }
      return;
    }
    finally {}
  }
  
  public void a(String paramString, o paramO, boolean paramBoolean, com.android.launcher3.d.d paramD)
  {
    try
    {
      paramString = a(paramString, paramO, paramBoolean);
      paramD.a = a(paramString, paramO);
      paramD.s = bj.a(paramString.b);
      paramD.b = paramString.d;
      paramD.t = paramString.c;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void a(Set<String> paramSet)
  {
    this.e.removeCallbacksAndMessages(a);
    b();
    Iterator localIterator = this.c.b().iterator();
    while (localIterator.hasNext())
    {
      o localO = (o)localIterator.next();
      List localList = this.i.a(null, localO);
      if (localList != null)
      {
        if (localList.isEmpty()) {
          return;
        }
        Object localObject;
        if (o.a().equals(localO)) {
          localObject = paramSet;
        } else {
          localObject = Collections.emptySet();
        }
        a(localO, localList, (Set)localObject);
      }
      else {}
    }
  }
  
  public boolean a(Bitmap paramBitmap, o paramO)
  {
    return this.f.get(paramO) == paramBitmap;
  }
  
  public void b(String paramString, o paramO)
  {
    try
    {
      c(paramString, paramO);
      long l1 = this.c.a(paramO);
      paramO = this.d.getWritableDatabase();
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramString);
      localStringBuilder.append("/%");
      paramO.delete("icons", "componentName LIKE ? AND profileId = ?", new String[] { localStringBuilder.toString(), Long.toString(l1) });
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  static class a
  {
    public Bitmap a;
    public CharSequence b = "";
    public CharSequence c = "";
    public boolean d;
    
    a() {}
  }
  
  private static final class b
    extends SQLiteOpenHelper
  {
    public b(Context paramContext)
    {
      super("app_icons.db", null, 7);
    }
    
    private void a(SQLiteDatabase paramSQLiteDatabase)
    {
      paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS icons");
      onCreate(paramSQLiteDatabase);
    }
    
    public void onCreate(SQLiteDatabase paramSQLiteDatabase)
    {
      paramSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS icons (componentName TEXT NOT NULL, profileId INTEGER NOT NULL, lastUpdated INTEGER NOT NULL DEFAULT 0, version INTEGER NOT NULL DEFAULT 0, icon BLOB, icon_low_res BLOB, label TEXT, system_state TEXT, PRIMARY KEY (componentName, profileId) );");
    }
    
    public void onDowngrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
    {
      if (paramInt1 != paramInt2) {
        a(paramSQLiteDatabase);
      }
    }
    
    public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
    {
      if (paramInt1 != paramInt2) {
        a(paramSQLiteDatabase);
      }
    }
  }
  
  public static class c
  {
    private final Runnable a;
    private final Handler b;
    
    c(Runnable paramRunnable, Handler paramHandler)
    {
      this.a = paramRunnable;
      this.b = paramHandler;
    }
    
    public void a()
    {
      this.b.removeCallbacks(this.a);
    }
  }
  
  class d
    implements Runnable
  {
    private final long b;
    private final HashMap<String, PackageInfo> c;
    private final Stack<f> d;
    private final Stack<f> e;
    private final HashSet<String> f = new HashSet();
    
    d(HashMap<String, PackageInfo> paramHashMap, Stack<f> paramStack1, Stack<f> paramStack2)
    {
      this.b = ???;
      this.c = paramStack1;
      this.d = paramStack2;
      Object localObject;
      this.e = localObject;
    }
    
    public void a()
    {
      ac.this.e.postAtTime(this, ac.a, SystemClock.uptimeMillis() + 1L);
    }
    
    public void run()
    {
      Object localObject2;
      Object localObject4;
      if (!this.e.isEmpty())
      {
        ??? = (f)this.e.pop();
        localObject2 = ((f)???).a().flattenToString();
        localObject4 = ac.this.a((f)???, true);
        ac.this.d.getWritableDatabase().update("icons", (ContentValues)localObject4, "componentName = ? AND profileId = ?", new String[] { localObject2, Long.toString(this.b) });
        this.f.add(((f)???).a().getPackageName());
        if ((this.e.isEmpty()) && (!this.f.isEmpty())) {
          ak.a().g().a(this.f, ac.this.c.a(this.b));
        }
        a();
        return;
      }
      if (!this.d.isEmpty())
      {
        localObject2 = (f)this.d.pop();
        localObject4 = (PackageInfo)this.c.get(((f)localObject2).a().getPackageName());
        if (localObject4 != null) {
          synchronized (ac.this)
          {
            ac.this.a((f)localObject2, (PackageInfo)localObject4, this.b);
          }
        }
        if (!this.d.isEmpty()) {
          a();
        }
      }
    }
  }
}
