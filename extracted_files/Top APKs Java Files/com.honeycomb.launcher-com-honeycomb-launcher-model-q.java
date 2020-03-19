package com.honeycomb.launcher.model;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteFullException;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.text.TextUtils;
import com.honeycomb.launcher.b.i;
import com.honeycomb.launcher.b.l;
import com.honeycomb.launcher.c.s;
import com.honeycomb.launcher.core.theme.c;
import com.honeycomb.launcher.desktop.BubbleTextView;
import com.honeycomb.launcher.desktop.bg;
import com.ihs.commons.i.g;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.Stack;

public class q
{
  static final Object a = new Object();
  static final Object b = new Object();
  final s c = new s();
  final com.honeycomb.launcher.b.x d;
  final v e;
  final Handler f;
  private final HashMap g = new HashMap();
  private final Context h;
  private final PackageManager i;
  private final l j;
  private final HashMap k = new HashMap(50);
  private final int l;
  private final int m;
  private final int n;
  private final BitmapFactory.Options o;
  private String p;
  private Bitmap q;
  private Canvas r;
  private Paint s;
  
  public q(Context paramContext, bg paramBg)
  {
    this.h = paramContext;
    this.i = paramContext.getPackageManager();
    this.d = com.honeycomb.launcher.b.x.a(this.h);
    this.j = l.a(this.h);
    this.l = paramBg.m;
    this.e = new v(paramContext);
    this.f = new Handler(ae.m());
    this.m = paramContext.getResources().getColor(2131689631);
    this.n = paramContext.getResources().getColor(2131689632);
    this.o = new BitmapFactory.Options();
    this.o.inPreferredConfig = Bitmap.Config.RGB_565;
    c();
  }
  
  private ContentValues a(Bitmap paramBitmap, String paramString, int paramInt)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("icon", com.honeycomb.launcher.c.x.a(paramBitmap));
    localContentValues.put("label", paramString);
    localContentValues.put("system_state", this.p);
    if (paramInt == 0)
    {
      localContentValues.put("icon_low_res", com.honeycomb.launcher.c.x.a(Bitmap.createScaledBitmap(paramBitmap, paramBitmap.getWidth() / 5, paramBitmap.getHeight() / 5, true)));
      return localContentValues;
    }
    try
    {
      if (this.q == null)
      {
        this.q = Bitmap.createBitmap(paramBitmap.getWidth() / 5, paramBitmap.getHeight() / 5, Bitmap.Config.RGB_565);
        this.r = new Canvas(this.q);
        this.s = new Paint(3);
      }
      this.r.drawColor(paramInt);
      this.r.drawBitmap(paramBitmap, new Rect(0, 0, paramBitmap.getWidth(), paramBitmap.getHeight()), new Rect(0, 0, this.q.getWidth(), this.q.getHeight()), this.s);
      localContentValues.put("icon_low_res", com.honeycomb.launcher.c.x.a(this.q));
      return localContentValues;
    }
    finally {}
  }
  
  private static Bitmap a(Cursor paramCursor, int paramInt, BitmapFactory.Options paramOptions)
  {
    paramCursor = paramCursor.getBlob(paramInt);
    try
    {
      paramCursor = BitmapFactory.decodeByteArray(paramCursor, 0, paramCursor.length, paramOptions);
      return paramCursor;
    }
    catch (Exception paramCursor) {}
    return null;
  }
  
  private Bitmap a(u paramU, com.honeycomb.launcher.b.w paramW)
  {
    if (paramU.a == null) {
      return a(paramW);
    }
    return paramU.a;
  }
  
  private Drawable a(Resources paramResources, int paramInt)
  {
    try
    {
      paramResources = paramResources.getDrawableForDensity(paramInt, this.l);
      if (paramResources != null) {
        return paramResources;
      }
    }
    catch (Resources.NotFoundException paramResources)
    {
      for (;;)
      {
        paramResources = null;
      }
    }
    return b();
  }
  
  private u a(ComponentName paramComponentName, i paramI, com.honeycomb.launcher.b.w paramW, boolean paramBoolean1, boolean paramBoolean2)
  {
    j localJ = new j(paramComponentName, paramW);
    u localU2 = (u)this.k.get(localJ);
    u localU1;
    if (localU2 != null)
    {
      localU1 = localU2;
      if (localU2.d)
      {
        localU1 = localU2;
        if (paramBoolean2) {}
      }
    }
    else
    {
      localU2 = new u();
      this.k.put(localJ, localU2);
      if (!a(localJ, localU2, paramBoolean2))
      {
        if (paramI == null) {
          break label178;
        }
        paramComponentName = z.a().f();
        localU2.a = com.honeycomb.launcher.c.x.a(paramI.a(this.h, paramComponentName, this.l), this.h);
      }
    }
    for (;;)
    {
      localU1 = localU2;
      if (TextUtils.isEmpty(localU2.b))
      {
        localU1 = localU2;
        if (paramI != null)
        {
          localU2.b = paramI.c();
          localU2.c = this.d.a(localU2.b, paramW);
          localU1 = localU2;
        }
      }
      return localU1;
      label178:
      if (paramBoolean1)
      {
        paramComponentName = a(paramComponentName.getPackageName(), paramW, false);
        if (paramComponentName != null)
        {
          localU2.a = paramComponentName.a;
          localU2.b = paramComponentName.b;
          localU2.c = paramComponentName.c;
        }
      }
      if (localU2.a == null) {
        localU2.a = a(paramW);
      }
    }
  }
  
  private u a(String paramString, com.honeycomb.launcher.b.w paramW, boolean paramBoolean)
  {
    j localJ = d(paramString, paramW);
    Object localObject2 = (u)this.k.get(localJ);
    Object localObject1;
    int i1;
    if (localObject2 != null)
    {
      localObject1 = localObject2;
      if (((u)localObject2).d)
      {
        localObject1 = localObject2;
        if (paramBoolean) {}
      }
    }
    else
    {
      localObject1 = new u();
      if (a(localJ, (u)localObject1, paramBoolean)) {
        break label259;
      }
      try
      {
        if (com.honeycomb.launcher.b.w.a().equals(paramW))
        {
          i1 = 0;
          paramString = this.i.getPackageInfo(paramString, i1);
          localObject2 = paramString.applicationInfo;
          if (localObject2 != null) {
            break label145;
          }
          throw new PackageManager.NameNotFoundException("ApplicationInfo is null");
        }
      }
      catch (PackageManager.NameNotFoundException paramString)
      {
        i1 = 0;
      }
    }
    for (;;)
    {
      if (i1 != 0) {
        this.k.put(localJ, localObject1);
      }
      return localObject1;
      i1 = 8192;
      break;
      label145:
      ((u)localObject1).a = com.honeycomb.launcher.c.x.a(this.d.a(((ApplicationInfo)localObject2).loadIcon(this.i), paramW), this.h);
      ((u)localObject1).b = ((ApplicationInfo)localObject2).loadLabel(this.i);
      ((u)localObject1).c = this.d.a(((u)localObject1).b, paramW);
      ((u)localObject1).d = false;
      a(a(((u)localObject1).a, ((u)localObject1).b.toString(), this.n), localJ.b, paramString, this.d.a(paramW));
      i1 = 1;
      continue;
      label259:
      i1 = 1;
    }
  }
  
  private void a(ContentValues paramContentValues, ComponentName paramComponentName, PackageInfo paramPackageInfo, long paramLong)
  {
    paramContentValues.put("componentName", paramComponentName.flattenToString());
    paramContentValues.put("profileId", Long.valueOf(paramLong));
    paramContentValues.put("lastUpdated", Long.valueOf(paramPackageInfo.lastUpdateTime));
    paramContentValues.put("version", Integer.valueOf(paramPackageInfo.versionCode));
    try
    {
      this.e.getWritableDatabase().insertWithOnConflict("icons", null, paramContentValues, 5);
      return;
    }
    catch (SQLiteFullException paramContentValues)
    {
      g.d("Launcher.IconCache", "Database is full (perhaps because the storage is full), abort insertion");
    }
  }
  
  private void a(com.honeycomb.launcher.b.w paramW, List paramList, Set paramSet)
  {
    long l1 = this.d.a(paramW);
    Object localObject3 = this.h.getPackageManager();
    HashMap localHashMap = new HashMap();
    try
    {
      Object localObject1 = ((PackageManager)localObject3).getInstalledPackages(8192);
      localObject1 = ((List)localObject1).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject3 = (PackageInfo)((Iterator)localObject1).next();
        localHashMap.put(((PackageInfo)localObject3).packageName, localObject3);
      }
    }
    catch (RuntimeException localRuntimeException)
    {
      try
      {
        localObject2 = ((PackageManager)localObject3).getInstalledPackages(0);
      }
      catch (RuntimeException paramW) {}
      Object localObject2 = new HashMap();
      paramList = paramList.iterator();
      while (paramList.hasNext())
      {
        localObject3 = (i)paramList.next();
        ((HashMap)localObject2).put(((i)localObject3).a(), localObject3);
      }
      paramList = this.e.getReadableDatabase();
      localObject3 = Long.toString(l1);
      localObject3 = paramList.query("icons", new String[] { "rowid", "componentName", "lastUpdated", "version", "system_state" }, "profileId = ? ", new String[] { localObject3 }, null, null, null);
      int i1 = ((Cursor)localObject3).getColumnIndex("componentName");
      int i2 = ((Cursor)localObject3).getColumnIndex("lastUpdated");
      int i3 = ((Cursor)localObject3).getColumnIndex("version");
      int i4 = ((Cursor)localObject3).getColumnIndex("rowid");
      int i5 = ((Cursor)localObject3).getColumnIndex("system_state");
      HashSet localHashSet = new HashSet();
      paramList = new Stack();
      while (((Cursor)localObject3).moveToNext())
      {
        ComponentName localComponentName = ComponentName.unflattenFromString(((Cursor)localObject3).getString(i1));
        PackageInfo localPackageInfo = (PackageInfo)localHashMap.get(localComponentName.getPackageName());
        if (localPackageInfo == null)
        {
          if (!paramSet.contains(localComponentName.getPackageName()))
          {
            a(localComponentName, paramW);
            localHashSet.add(Integer.valueOf(((Cursor)localObject3).getInt(i4)));
          }
        }
        else if ((localPackageInfo.applicationInfo.flags & 0x1000000) == 0)
        {
          long l2 = ((Cursor)localObject3).getLong(i2);
          int i6 = ((Cursor)localObject3).getInt(i3);
          i localI = (i)((HashMap)localObject2).remove(localComponentName);
          if ((i6 != localPackageInfo.versionCode) || (l2 != localPackageInfo.lastUpdateTime) || (!TextUtils.equals(this.p, ((Cursor)localObject3).getString(i5)))) {
            if (localI == null)
            {
              a(localComponentName, paramW);
              localHashSet.add(Integer.valueOf(((Cursor)localObject3).getInt(i4)));
            }
            else
            {
              paramList.add(localI);
            }
          }
        }
      }
      ((Cursor)localObject3).close();
      if (!localHashSet.isEmpty()) {
        this.e.getWritableDatabase().delete("icons", com.honeycomb.launcher.c.x.a("rowid", localHashSet), null);
      }
      if ((!((HashMap)localObject2).isEmpty()) || (!paramList.isEmpty()))
      {
        paramW = new Stack();
        paramW.addAll(((HashMap)localObject2).values());
        new x(this, l1, localHashMap, paramW, paramList).a();
      }
      return;
    }
  }
  
  private boolean a(j paramJ, u paramU, boolean paramBoolean)
  {
    for (;;)
    {
      Object localObject3;
      synchronized (b)
      {
        localObject3 = this.e.getReadableDatabase();
        if (!paramBoolean) {
          break label258;
        }
        localObject1 = "icon_low_res";
        String str1 = paramJ.b.flattenToString();
        String str2 = Long.toString(this.d.a(paramJ.c));
        localObject3 = ((SQLiteDatabase)localObject3).query("icons", new String[] { localObject1, "label" }, "componentName = ? AND profileId = ?", new String[] { str1, str2 }, null, null, null);
        if (localObject3 == null) {
          return false;
        }
      }
      for (;;)
      {
        try
        {
          if (!((Cursor)localObject3).moveToNext()) {
            continue;
          }
          if (paramBoolean)
          {
            localObject1 = this.o;
            paramU.a = a((Cursor)localObject3, 0, (BitmapFactory.Options)localObject1);
            paramU.d = paramBoolean;
            paramU.b = ((Cursor)localObject3).getString(1);
            if (paramU.b != null) {
              continue;
            }
            paramU.b = "";
            paramU.c = "";
          }
        }
        catch (SQLiteException paramJ)
        {
          try
          {
            ((Cursor)localObject3).close();
            return false;
          }
          catch (Exception paramJ)
          {
            return false;
          }
        }
        finally
        {
          try
          {
            ((Cursor)localObject3).close();
            throw paramJ;
          }
          catch (Exception paramJ)
          {
            return false;
          }
        }
        try
        {
          ((Cursor)localObject3).close();
          return true;
        }
        catch (Exception paramJ)
        {
          try
          {
            ((Cursor)localObject3).close();
            return false;
          }
          catch (Exception paramJ)
          {
            return false;
          }
          paramJ = paramJ;
          return false;
        }
        localObject1 = null;
        continue;
        paramU.c = this.d.a(paramU.b, paramJ.c);
      }
      label258:
      Object localObject1 = "icon";
    }
  }
  
  private Bitmap b(com.honeycomb.launcher.b.w paramW)
  {
    Object localObject = b();
    paramW = this.d.a((Drawable)localObject, paramW);
    localObject = Bitmap.createBitmap(Math.max(paramW.getIntrinsicWidth(), 1), Math.max(paramW.getIntrinsicHeight(), 1), Bitmap.Config.ARGB_8888);
    Canvas localCanvas = new Canvas((Bitmap)localObject);
    paramW.setBounds(0, 0, ((Bitmap)localObject).getWidth(), ((Bitmap)localObject).getHeight());
    paramW.draw(localCanvas);
    localCanvas.setBitmap(null);
    return localObject;
  }
  
  private Drawable b()
  {
    return a(Resources.getSystem(), 17629184);
  }
  
  private void c()
  {
    this.p = Locale.getDefault().toString();
  }
  
  private void c(String paramString, com.honeycomb.launcher.b.w paramW)
  {
    HashSet localHashSet = new HashSet();
    Iterator localIterator = this.k.keySet().iterator();
    while (localIterator.hasNext())
    {
      j localJ = (j)localIterator.next();
      if ((localJ.b.getPackageName().equals(paramString)) && (localJ.c.equals(paramW))) {
        localHashSet.add(localJ);
      }
    }
    paramString = localHashSet.iterator();
    while (paramString.hasNext())
    {
      paramW = (j)paramString.next();
      this.k.remove(paramW);
    }
  }
  
  private static j d(String paramString, com.honeycomb.launcher.b.w paramW)
  {
    return new j(new ComponentName(paramString, paramString + "."), paramW);
  }
  
  ContentValues a(i paramI, boolean paramBoolean)
  {
    Object localObject1 = new j(paramI.a(), paramI.b());
    Object localObject2;
    if (!paramBoolean)
    {
      localObject2 = (u)this.k.get(localObject1);
      if ((localObject2 != null) && (!((u)localObject2).d))
      {
        localObject1 = localObject2;
        if (((u)localObject2).a != null) {
          break label59;
        }
      }
    }
    for (localObject1 = null;; localObject1 = null)
    {
      label59:
      localObject2 = localObject1;
      if (localObject1 == null)
      {
        localObject2 = new u();
        localObject1 = z.a().f();
        ((u)localObject2).a = com.honeycomb.launcher.c.x.a(paramI.a(this.h, (c)localObject1, this.l), this.h);
      }
      ((u)localObject2).b = paramI.c();
      ((u)localObject2).c = this.d.a(((u)localObject2).b, paramI.b());
      this.k.put(new j(paramI.a(), paramI.b()), localObject2);
      return a(((u)localObject2).a, ((u)localObject2).b.toString(), this.m);
    }
  }
  
  /* Error */
  public Bitmap a(android.content.Intent paramIntent, com.honeycomb.launcher.b.w paramW)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: invokevirtual 628	android/content/Intent:getComponent	()Landroid/content/ComponentName;
    //   6: astore_3
    //   7: aload_3
    //   8: ifnonnull +13 -> 21
    //   11: aload_0
    //   12: aload_2
    //   13: invokevirtual 236	com/honeycomb/launcher/model/q:a	(Lcom/honeycomb/launcher/b/w;)Landroid/graphics/Bitmap;
    //   16: astore_1
    //   17: aload_0
    //   18: monitorexit
    //   19: aload_1
    //   20: areturn
    //   21: aload_0
    //   22: aload_3
    //   23: aload_0
    //   24: getfield 88	com/honeycomb/launcher/model/q:j	Lcom/honeycomb/launcher/b/l;
    //   27: aload_1
    //   28: aload_2
    //   29: invokevirtual 631	com/honeycomb/launcher/b/l:a	(Landroid/content/Intent;Lcom/honeycomb/launcher/b/w;)Lcom/honeycomb/launcher/b/i;
    //   32: aload_2
    //   33: iconst_1
    //   34: iconst_0
    //   35: invokespecial 633	com/honeycomb/launcher/model/q:a	(Landroid/content/ComponentName;Lcom/honeycomb/launcher/b/i;Lcom/honeycomb/launcher/b/w;ZZ)Lcom/honeycomb/launcher/model/u;
    //   38: getfield 233	com/honeycomb/launcher/model/u:a	Landroid/graphics/Bitmap;
    //   41: astore_1
    //   42: goto -25 -> 17
    //   45: astore_1
    //   46: aload_0
    //   47: monitorexit
    //   48: aload_1
    //   49: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	50	0	this	q
    //   0	50	1	paramIntent	android.content.Intent
    //   0	50	2	paramW	com.honeycomb.launcher.b.w
    //   6	17	3	localComponentName	ComponentName
    // Exception table:
    //   from	to	target	type
    //   2	7	45	finally
    //   11	17	45	finally
    //   21	42	45	finally
  }
  
  public Bitmap a(com.honeycomb.launcher.b.w paramW)
  {
    try
    {
      if (!this.g.containsKey(paramW)) {
        this.g.put(paramW, b(paramW));
      }
      paramW = (Bitmap)this.g.get(paramW);
      return paramW;
    }
    finally {}
  }
  
  public Drawable a(ActivityInfo paramActivityInfo)
  {
    try
    {
      Resources localResources = this.i.getResourcesForApplication(paramActivityInfo.applicationInfo);
      if (localResources != null)
      {
        int i1 = paramActivityInfo.getIconResource();
        if (i1 != 0) {
          return a(localResources, i1);
        }
      }
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;)
      {
        Object localObject = null;
      }
    }
    return b();
  }
  
  public Drawable a(String paramString, int paramInt)
  {
    try
    {
      paramString = this.i.getResourcesForApplication(paramString);
      if ((paramString != null) && (paramInt != 0)) {
        return a(paramString, paramInt);
      }
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      for (;;)
      {
        paramString = null;
      }
    }
    return b();
  }
  
  public w a(BubbleTextView paramBubbleTextView, y paramY)
  {
    paramBubbleTextView = new r(this, paramY, paramBubbleTextView);
    this.f.post(paramBubbleTextView);
    return new w(paramBubbleTextView, this.f);
  }
  
  public void a()
  {
    this.k.clear();
    v.a(this.e, this.e.getWritableDatabase());
  }
  
  public void a(ComponentName paramComponentName, com.honeycomb.launcher.b.w paramW)
  {
    try
    {
      this.k.remove(new j(paramComponentName, paramW));
      return;
    }
    finally
    {
      paramComponentName = finally;
      throw paramComponentName;
    }
  }
  
  void a(i paramI, PackageInfo paramPackageInfo, long paramLong)
  {
    a(a(paramI, false), paramI.a(), paramPackageInfo, paramLong);
  }
  
  public void a(AppInfo paramAppInfo)
  {
    try
    {
      u localU = a(paramAppInfo.e, null, paramAppInfo.v, false, paramAppInfo.c);
      if ((localU.a != null) && (!a(localU.a, paramAppInfo.v)))
      {
        paramAppInfo.s = com.honeycomb.launcher.c.x.a(localU.b);
        paramAppInfo.b = localU.a;
        paramAppInfo.t = localU.c;
        paramAppInfo.c = localU.d;
      }
      return;
    }
    finally
    {
      paramAppInfo = finally;
      throw paramAppInfo;
    }
  }
  
  public void a(AppInfo paramAppInfo, i paramI, boolean paramBoolean)
  {
    if (paramI == null) {}
    for (;;)
    {
      try
      {
        localW = paramAppInfo.v;
        paramI = a(paramAppInfo.e, paramI, localW, false, paramBoolean);
        paramAppInfo.s = com.honeycomb.launcher.c.x.a(paramI.b);
        paramAppInfo.t = paramI.c;
        paramAppInfo.c = paramI.d;
        paramAppInfo.b = a(paramI, localW);
        return;
      }
      finally {}
      com.honeycomb.launcher.b.w localW = paramI.b();
    }
  }
  
  public void a(cp paramCp, ComponentName paramComponentName, i paramI, com.honeycomb.launcher.b.w paramW, boolean paramBoolean1, boolean paramBoolean2)
  {
    try
    {
      paramComponentName = a(paramComponentName, paramI, paramW, paramBoolean1, paramBoolean2);
      paramCp.s = com.honeycomb.launcher.c.x.a(paramComponentName.b);
      paramCp.d = a(paramComponentName.a, paramW);
      paramCp.e = paramComponentName.d;
      paramCp.a(a(paramComponentName, paramW));
      return;
    }
    finally
    {
      paramCp = finally;
      throw paramCp;
    }
  }
  
  /* Error */
  public void a(cp paramCp, android.content.Intent paramIntent, com.honeycomb.launcher.b.w paramW, boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_2
    //   3: invokevirtual 628	android/content/Intent:getComponent	()Landroid/content/ComponentName;
    //   6: astore 5
    //   8: aload 5
    //   10: ifnonnull +32 -> 42
    //   13: aload_1
    //   14: aload_0
    //   15: aload_3
    //   16: invokevirtual 236	com/honeycomb/launcher/model/q:a	(Lcom/honeycomb/launcher/b/w;)Landroid/graphics/Bitmap;
    //   19: invokevirtual 712	com/honeycomb/launcher/model/cp:a	(Landroid/graphics/Bitmap;)V
    //   22: aload_1
    //   23: ldc_w 559
    //   26: putfield 707	com/honeycomb/launcher/model/cp:s	Ljava/lang/CharSequence;
    //   29: aload_1
    //   30: iconst_1
    //   31: putfield 708	com/honeycomb/launcher/model/cp:d	Z
    //   34: aload_1
    //   35: iconst_0
    //   36: putfield 710	com/honeycomb/launcher/model/cp:e	Z
    //   39: aload_0
    //   40: monitorexit
    //   41: return
    //   42: aload_0
    //   43: aload_1
    //   44: aload 5
    //   46: aload_0
    //   47: getfield 88	com/honeycomb/launcher/model/q:j	Lcom/honeycomb/launcher/b/l;
    //   50: aload_2
    //   51: aload_3
    //   52: invokevirtual 631	com/honeycomb/launcher/b/l:a	(Landroid/content/Intent;Lcom/honeycomb/launcher/b/w;)Lcom/honeycomb/launcher/b/i;
    //   55: aload_3
    //   56: iconst_1
    //   57: iload 4
    //   59: invokevirtual 715	com/honeycomb/launcher/model/q:a	(Lcom/honeycomb/launcher/model/cp;Landroid/content/ComponentName;Lcom/honeycomb/launcher/b/i;Lcom/honeycomb/launcher/b/w;ZZ)V
    //   62: goto -23 -> 39
    //   65: astore_1
    //   66: aload_0
    //   67: monitorexit
    //   68: aload_1
    //   69: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	70	0	this	q
    //   0	70	1	paramCp	cp
    //   0	70	2	paramIntent	android.content.Intent
    //   0	70	3	paramW	com.honeycomb.launcher.b.w
    //   0	70	4	paramBoolean	boolean
    //   6	39	5	localComponentName	ComponentName
    // Exception table:
    //   from	to	target	type
    //   2	8	65	finally
    //   13	39	65	finally
    //   42	62	65	finally
  }
  
  public void a(Runnable paramRunnable)
  {
    this.f.post(new t(this, paramRunnable));
  }
  
  /* Error */
  public void a(String paramString, com.honeycomb.launcher.b.w paramW)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: aload_2
    //   5: invokevirtual 723	com/honeycomb/launcher/model/q:b	(Ljava/lang/String;Lcom/honeycomb/launcher/b/w;)V
    //   8: aload_0
    //   9: getfield 74	com/honeycomb/launcher/model/q:i	Landroid/content/pm/PackageManager;
    //   12: aload_1
    //   13: sipush 8192
    //   16: invokevirtual 328	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   19: astore 5
    //   21: aload_0
    //   22: getfield 81	com/honeycomb/launcher/model/q:d	Lcom/honeycomb/launcher/b/x;
    //   25: aload_2
    //   26: invokevirtual 365	com/honeycomb/launcher/b/x:a	(Lcom/honeycomb/launcher/b/w;)J
    //   29: lstore_3
    //   30: aload_0
    //   31: getfield 88	com/honeycomb/launcher/model/q:j	Lcom/honeycomb/launcher/b/l;
    //   34: aload_1
    //   35: aload_2
    //   36: invokevirtual 726	com/honeycomb/launcher/b/l:a	(Ljava/lang/String;Lcom/honeycomb/launcher/b/w;)Ljava/util/List;
    //   39: invokeinterface 438 1 0
    //   44: astore_1
    //   45: aload_1
    //   46: invokeinterface 444 1 0
    //   51: ifeq +34 -> 85
    //   54: aload_0
    //   55: aload_1
    //   56: invokeinterface 448 1 0
    //   61: checkcast 276	com/honeycomb/launcher/b/i
    //   64: aload 5
    //   66: lload_3
    //   67: invokevirtual 728	com/honeycomb/launcher/model/q:a	(Lcom/honeycomb/launcher/b/i;Landroid/content/pm/PackageInfo;J)V
    //   70: goto -25 -> 45
    //   73: astore_1
    //   74: ldc_w 419
    //   77: ldc_w 730
    //   80: aload_1
    //   81: invokestatic 735	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   84: pop
    //   85: aload_0
    //   86: monitorexit
    //   87: return
    //   88: astore_1
    //   89: aload_0
    //   90: monitorexit
    //   91: aload_1
    //   92: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	93	0	this	q
    //   0	93	1	paramString	String
    //   0	93	2	paramW	com.honeycomb.launcher.b.w
    //   29	38	3	l1	long
    //   19	46	5	localPackageInfo	PackageInfo
    // Exception table:
    //   from	to	target	type
    //   8	45	73	android/content/pm/PackageManager$NameNotFoundException
    //   45	70	73	android/content/pm/PackageManager$NameNotFoundException
    //   2	8	88	finally
    //   8	45	88	finally
    //   45	70	88	finally
    //   74	85	88	finally
  }
  
  public void a(String paramString, com.honeycomb.launcher.b.w paramW, Bitmap paramBitmap, CharSequence paramCharSequence)
  {
    try
    {
      c(paramString, paramW);
      j localJ = d(paramString, paramW);
      paramW = (u)this.k.get(localJ);
      paramString = paramW;
      if (paramW == null)
      {
        paramString = new u();
        this.k.put(localJ, paramString);
      }
      if (!TextUtils.isEmpty(paramCharSequence)) {
        paramString.b = paramCharSequence;
      }
      if (paramBitmap != null) {
        paramString.a = com.honeycomb.launcher.c.x.a(paramBitmap, this.h);
      }
      return;
    }
    finally {}
  }
  
  public void a(String paramString, com.honeycomb.launcher.b.w paramW, boolean paramBoolean, co paramCo)
  {
    try
    {
      paramString = a(paramString, paramW, paramBoolean);
      paramCo.a = a(paramString, paramW);
      paramCo.s = com.honeycomb.launcher.c.x.a(paramString.b);
      paramCo.b = paramString.d;
      paramCo.t = paramString.c;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void a(Set paramSet)
  {
    this.f.removeCallbacksAndMessages(a);
    c();
    Iterator localIterator = this.d.b().iterator();
    com.honeycomb.launcher.b.w localW;
    List localList;
    if (localIterator.hasNext())
    {
      localW = (com.honeycomb.launcher.b.w)localIterator.next();
      localList = this.j.a(null, localW);
      if ((localList != null) && (!localList.isEmpty())) {}
    }
    else
    {
      return;
    }
    if (com.honeycomb.launcher.b.w.a().equals(localW)) {}
    for (Set localSet = paramSet;; localSet = Collections.emptySet())
    {
      a(localW, localList, localSet);
      break;
    }
  }
  
  public boolean a(Bitmap paramBitmap, com.honeycomb.launcher.b.w paramW)
  {
    return this.g.get(paramW) == paramBitmap;
  }
  
  public void b(String paramString, com.honeycomb.launcher.b.w paramW)
  {
    try
    {
      c(paramString, paramW);
      long l1 = this.d.a(paramW);
      this.e.getWritableDatabase().delete("icons", "componentName LIKE ? AND profileId = ?", new String[] { paramString + "/%", Long.toString(l1) });
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
}
