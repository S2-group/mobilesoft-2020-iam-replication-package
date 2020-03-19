package com.emoji.face.sticker.home.screen;

import android.appwidget.AppWidgetProviderInfo;
import android.content.ComponentName;
import android.content.ContentProviderOperation;
import android.content.ContentProviderOperation.Builder;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Point;
import android.net.Uri;
import android.text.TextUtils;
import com.emoji.face.sticker.home.screen.desktop.widget.LauncherAppWidgetProviderInfo;
import com.emoji.face.sticker.home.screen.model.LauncherProvider;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class csn
{
  private static final String Code = csn.class.getSimpleName();
  private final ArrayList<Long> B = new ArrayList();
  private final ArrayList<ContentProviderOperation> C = new ArrayList();
  private final int D;
  private final HashSet<String> F;
  private final bkn I;
  private final int L;
  private final ArrayList<aux> S = new ArrayList();
  private final Context V;
  private final ContentValues Z = new ContentValues();
  private final int a;
  private final int b;
  private final boolean c;
  private final boolean d;
  
  csn(Context paramContext, bkn paramBkn, HashSet<String> paramHashSet, Point paramPoint1, Point paramPoint2)
  {
    this.V = paramContext;
    this.F = paramHashSet;
    this.I = paramBkn;
    this.D = paramPoint1.x;
    this.L = paramPoint1.y;
    this.a = paramPoint2.x;
    this.b = paramPoint2.y;
    if (this.a < this.D)
    {
      bool1 = true;
      this.c = bool1;
      if (this.b >= this.L) {
        break label150;
      }
    }
    label150:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      this.d = bool1;
      return;
      bool1 = false;
      break;
    }
  }
  
  private Cursor Code(String[] paramArrayOfString, String paramString)
  {
    return this.V.getContentResolver().query(ctl.nul.Code, paramArrayOfString, paramString, null, null, null);
  }
  
  public static void Code(int paramInt1, int paramInt2)
  {
    hsz.Code(css.Code).V("migration_src_workspace_size", V(paramInt1, paramInt2));
  }
  
  private void Code(long paramLong)
  {
    Object localObject3 = V(paramLong);
    int i = Integer.MAX_VALUE;
    int n = Integer.MAX_VALUE;
    float f1 = Float.MAX_VALUE;
    float f4 = Float.MAX_VALUE;
    Object localObject4 = new float[2];
    Object localObject1 = null;
    int k = 0;
    Object localObject2 = localObject1;
    float f2 = f1;
    int j = n;
    int m = i;
    label77:
    Object localObject5;
    int i1;
    label141:
    label302:
    label372:
    float f3;
    if (k < this.D)
    {
      m = this.L - 1;
      f2 = f4;
      j = i;
      i = n;
      if (m >= 0)
      {
        Object localObject6 = V((ArrayList)localObject3);
        localObject5 = new dfo(this.a, this.b);
        ((dfo)localObject5).Code(0, 0, this.a, 0, true);
        ArrayList localArrayList;
        if (this.c)
        {
          n = k;
          if (!this.d) {
            break label302;
          }
          i1 = m;
          localObject2 = new ArrayList();
          localArrayList = new ArrayList();
          localObject6 = ((ArrayList)localObject6).iterator();
        }
        for (;;)
        {
          if (!((Iterator)localObject6).hasNext()) {
            break label372;
          }
          aux localAux = (aux)((Iterator)localObject6).next();
          if (((localAux.d <= n) && (localAux.f + localAux.d > n)) || ((localAux.e <= i1) && (localAux.g + localAux.e > i1)))
          {
            localArrayList.add(localAux);
            if (localAux.d >= n) {
              localAux.d -= 1;
            }
            if (localAux.e < i1) {
              continue;
            }
            localAux.e -= 1;
            continue;
            n = Integer.MAX_VALUE;
            break;
            i1 = Integer.MAX_VALUE;
            break label141;
          }
          if (localAux.d > n) {
            localAux.d -= 1;
          }
          if (localAux.e > i1) {
            localAux.e -= 1;
          }
          ((ArrayList)localObject2).add(localAux);
          ((dfo)localObject5).Code(localAux, true);
        }
        localObject5 = new nul((dfo)localObject5, localArrayList);
        ((nul)localObject5).Code();
        ((ArrayList)localObject2).addAll(((nul)localObject5).I);
        localObject4[0] = ((nul)localObject5).Code;
        localObject4[1] = ((nul)localObject5).V;
        if ((localObject4[0] >= f1) && ((localObject4[0] != f1) || (localObject4[1] >= f2))) {
          break label1040;
        }
        f2 = localObject4[0];
        f3 = localObject4[1];
        if (this.c)
        {
          j = k;
          label471:
          if (!this.d) {
            break label535;
          }
          i1 = m;
        }
      }
    }
    for (;;)
    {
      localObject1 = localObject2;
      f4 = f3;
      f1 = f2;
      n = i1;
      i = j;
      if (this.d)
      {
        m -= 1;
        localObject1 = localObject2;
        f1 = f2;
        i = i1;
        f2 = f3;
        break label77;
        break label471;
        label535:
        i1 = i;
        continue;
        n = i;
        i = j;
        f4 = f2;
      }
      localObject2 = localObject1;
      f2 = f1;
      j = n;
      m = i;
      if (this.c)
      {
        k += 1;
        break;
      }
      String.format(Locale.ENGLISH, "Removing row %d, column %d on screen %d", new Object[] { Integer.valueOf(j), Integer.valueOf(m), Long.valueOf(paramLong) });
      localObject1 = new dfv();
      localObject3 = V((ArrayList)localObject3).iterator();
      while (((Iterator)localObject3).hasNext())
      {
        localObject4 = (aux)((Iterator)localObject3).next();
        ((dfv)localObject1).put(((aux)localObject4).L, localObject4);
      }
      if (localObject2 != null)
      {
        localObject3 = ((ArrayList)localObject2).iterator();
        label823:
        while (((Iterator)localObject3).hasNext())
        {
          localObject4 = (aux)((Iterator)localObject3).next();
          localObject5 = (aux)((dfv)localObject1).get(((aux)localObject4).L);
          ((dfv)localObject1).remove(((aux)localObject4).L);
          if ((((aux)localObject5).d == ((aux)localObject4).d) && (((aux)localObject5).e == ((aux)localObject4).e) && (((aux)localObject5).f == ((aux)localObject4).f) && (((aux)localObject5).g == ((aux)localObject4).g) && (((aux)localObject5).c == ((aux)localObject4).c)) {}
          for (i = 1;; i = 0)
          {
            if (i != 0) {
              break label823;
            }
            Code((aux)localObject4);
            break;
          }
        }
      }
      localObject1 = ((dfv)localObject1).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject3 = (aux)((Iterator)localObject1).next();
        this.S.add(localObject3);
      }
      if ((!this.S.isEmpty()) && (f2 == 0.0F))
      {
        localObject1 = new dfo(this.a, this.b);
        ((dfo)localObject1).Code(0, 0, this.a, 0, true);
        localObject2 = ((ArrayList)localObject2).iterator();
        while (((Iterator)localObject2).hasNext()) {
          ((dfo)localObject1).Code((aux)((Iterator)localObject2).next(), true);
        }
        localObject1 = new nul((dfo)localObject1, V(this.S), true);
        ((nul)localObject1).Code();
        if (((nul)localObject1).Code == 0.0F)
        {
          localObject1 = ((nul)localObject1).I.iterator();
          while (((Iterator)localObject1).hasNext())
          {
            localObject2 = (aux)((Iterator)localObject1).next();
            ((aux)localObject2).c = paramLong;
            Code((aux)localObject2);
          }
          this.S.clear();
        }
      }
      return;
      label1040:
      localObject2 = localObject1;
      f3 = f2;
      f2 = f1;
      i1 = i;
    }
  }
  
  private void Code(aux paramAux)
  {
    this.Z.clear();
    ContentValues localContentValues = this.Z;
    localContentValues.put("screen", Long.valueOf(paramAux.c));
    localContentValues.put("cellX", Integer.valueOf(paramAux.d));
    localContentValues.put("cellY", Integer.valueOf(paramAux.e));
    localContentValues.put("spanX", Integer.valueOf(paramAux.f));
    localContentValues.put("spanY", Integer.valueOf(paramAux.g));
    this.C.add(ContentProviderOperation.newUpdate(ctl.nul.Code(paramAux.L)).withValues(this.Z).build());
  }
  
  private void Code(String paramString)
  {
    paramString = Intent.parseUri(paramString, 0);
    if (paramString.getComponent() != null) {
      V(paramString.getComponent().getPackageName());
    }
    while (paramString.getPackage() == null) {
      return;
    }
    V(paramString.getPackage());
  }
  
  static boolean Code(Context paramContext)
  {
    hsz localHsz = hsz.Code(css.Code);
    Object localObject3 = csp.Code().e;
    String str = V(((bkn)localObject3).S, ((bkn)localObject3).C);
    Object localObject2 = localHsz.Code("migration_src_workspace_size", str);
    if (str.equals(localObject2)) {
      return true;
    }
    long l = System.currentTimeMillis();
    for (;;)
    {
      try
      {
        HashSet localHashSet = new HashSet();
        Iterator localIterator = paramContext.getPackageManager().getInstalledPackages(8192).iterator();
        if (localIterator.hasNext())
        {
          localHashSet.add(((PackageInfo)localIterator.next()).packageName);
          continue;
        }
        new StringBuilder("Workspace migration completed in ").append(System.currentTimeMillis() - l);
      }
      catch (Exception paramContext)
      {
        new StringBuilder("Error during grid migration: ").append(paramContext);
        new StringBuilder("Workspace migration completed in ").append(System.currentTimeMillis() - l);
        localHsz.V("migration_src_workspace_size", str);
        return false;
        localHashSet.addAll(bcg.Code(paramContext).Code().keySet());
        localObject3 = new Point(((bkn)localObject3).S, ((bkn)localObject3).C);
        localObject2 = ((String)localObject2).split(",");
        localObject2 = new Point(Integer.parseInt(localObject2[0]), Integer.parseInt(localObject2[1]));
        if (!new con(localHashSet, paramContext).Code((Point)localObject2, (Point)localObject3)) {
          break label383;
        }
        i = 1;
        if (i != 0)
        {
          paramContext = paramContext.getContentResolver().query(ctl.nul.Code, null, null, null, null);
          if (paramContext == null) {
            break label377;
          }
        }
      }
      finally
      {
        try
        {
          bool = paramContext.moveToNext();
          paramContext.close();
          if (bool) {
            break label347;
          }
          throw new Exception("Removed every thing during grid resize");
        }
        finally
        {
          paramContext.close();
        }
        paramContext = finally;
        new StringBuilder("Workspace migration completed in ").append(System.currentTimeMillis() - l);
        localHsz.V("migration_src_workspace_size", str);
      }
      label347:
      localHsz.V("migration_src_workspace_size", str);
      return true;
      label377:
      boolean bool = false;
      continue;
      label383:
      int i = 0;
    }
  }
  
  private int I(long paramLong)
  {
    int i = 0;
    Object localObject1 = "container = " + paramLong;
    localObject1 = Code(new String[] { "_id", "intent" }, (String)localObject1);
    if (localObject1 == null) {
      return 0;
    }
    try
    {
      for (;;)
      {
        boolean bool = ((Cursor)localObject1).moveToNext();
        if (!bool) {
          break;
        }
        try
        {
          Code(((Cursor)localObject1).getString(1));
          i += 1;
        }
        catch (Exception localException)
        {
          this.B.add(Long.valueOf(((Cursor)localObject1).getLong(0)));
        }
      }
    }
    finally
    {
      ((Cursor)localObject1).close();
    }
    return i;
  }
  
  private static String V(int paramInt1, int paramInt2)
  {
    return String.format(Locale.ENGLISH, "%d,%d", new Object[] { Integer.valueOf(paramInt1), Integer.valueOf(paramInt2) });
  }
  
  private ArrayList<aux> V(long paramLong)
  {
    String str = "container = -100 AND screen = " + paramLong;
    Cursor localCursor = Code(new String[] { "_id", "itemType", "cellX", "cellY", "spanX", "spanY", "intent", "appWidgetProvider", "appWidgetId" }, str);
    int k = localCursor.getColumnIndexOrThrow("_id");
    int m = localCursor.getColumnIndexOrThrow("itemType");
    int n = localCursor.getColumnIndexOrThrow("cellX");
    int i1 = localCursor.getColumnIndexOrThrow("cellY");
    int i2 = localCursor.getColumnIndexOrThrow("spanX");
    int i3 = localCursor.getColumnIndexOrThrow("spanY");
    int i4 = localCursor.getColumnIndexOrThrow("intent");
    int i5 = localCursor.getColumnIndexOrThrow("appWidgetProvider");
    int i6 = localCursor.getColumnIndexOrThrow("appWidgetId");
    ArrayList localArrayList = new ArrayList();
    aux localAux;
    try
    {
      while (localCursor.moveToNext())
      {
        localAux = new aux();
        localAux.L = localCursor.getLong(k);
        localAux.a = localCursor.getInt(m);
        localAux.d = localCursor.getInt(n);
        localAux.e = localCursor.getInt(i1);
        localAux.f = localCursor.getInt(i2);
        localAux.g = localCursor.getInt(i3);
        localAux.c = paramLong;
        try
        {
          switch (localAux.a)
          {
          case 3: 
            throw new Exception("Invalid item type");
          }
        }
        catch (Exception localException)
        {
          new StringBuilder("Removing item ").append(localAux.L).append(", exception: ").append(localException);
          this.B.add(Long.valueOf(localAux.L));
        }
      }
      Code(localCursor.getString(i4));
    }
    finally
    {
      localCursor.close();
    }
    if (localAux.a == 0) {}
    for (float f = 0.8F;; f = 1.0F)
    {
      localAux.Code = f;
      label473:
      localArrayList.add(localAux);
      break;
    }
    localAux.Code = Math.max(2.0F, 0.6F * localAux.f * localAux.g);
    Object localObject2 = ComponentName.unflattenFromString(localCursor.getString(i5));
    int i;
    if (localAux.a == 4)
    {
      V(((ComponentName)localObject2).getPackageName());
      i = localCursor.getInt(i6);
      localObject2 = bbw.Code(this.V).Code(i);
      if (localObject2 == null)
      {
        localObject2 = null;
        break label844;
        label583:
        if (localObject2 == null) {
          break label747;
        }
        if (((Point)localObject2).x > 0)
        {
          i = ((Point)localObject2).x;
          label603:
          localAux.h = i;
          if (((Point)localObject2).y <= 0) {
            break label737;
          }
          i = ((Point)localObject2).y;
          label625:
          localAux.i = i;
          label632:
          if ((localAux.h <= this.a) && (localAux.i <= this.b)) {
            break label787;
          }
          throw new Exception("Widget can't be resized down to fit the grid");
        }
      }
      else
      {
        localObject2 = LauncherAppWidgetProviderInfo.Code((AppWidgetProviderInfo)localObject2);
        break label844;
        label677:
        if ((((LauncherAppWidgetProviderInfo)localObject2).resizeMode & 0x1) == 0) {
          break label855;
        }
        i = ((LauncherAppWidgetProviderInfo)localObject2).Z;
        label694:
        if ((((LauncherAppWidgetProviderInfo)localObject2).resizeMode & 0x2) == 0) {
          break label861;
        }
      }
    }
    label737:
    label747:
    label787:
    label844:
    label855:
    label861:
    for (int j = ((LauncherAppWidgetProviderInfo)localObject2).B;; j = -1)
    {
      localObject2 = new Point(i, j);
      break label583;
      i = localAux.f;
      break label603;
      i = localAux.g;
      break label625;
      localAux.i = 2;
      localAux.h = 2;
      break label632;
      Class.forName(((ComponentName)localObject2).getClassName()).newInstance();
      localAux.h = 4;
      localAux.i = 1;
      break label632;
      break label473;
      i = I(localAux.L);
      if (i == 0) {
        throw new Exception("Folder is empty");
      }
      localAux.Code = (i * 0.5F);
      break label473;
      localCursor.close();
      return localArrayList;
      break;
      if (localObject2 != null) {
        break label677;
      }
      localObject2 = null;
      break label583;
      i = -1;
      break label694;
    }
  }
  
  private static ArrayList<aux> V(ArrayList<aux> paramArrayList)
  {
    ArrayList localArrayList = new ArrayList(paramArrayList.size());
    paramArrayList = paramArrayList.iterator();
    while (paramArrayList.hasNext())
    {
      aux localAux1 = (aux)paramArrayList.next();
      aux localAux2 = new aux();
      localAux2.Code(localAux1);
      localAux2.Code = localAux1.Code;
      localAux2.h = localAux1.h;
      localAux2.i = localAux1.i;
      localArrayList.add(localAux2);
    }
    return localArrayList;
  }
  
  private void V(String paramString)
  {
    if (!this.F.contains(paramString)) {
      throw new Exception("Package not available");
    }
  }
  
  final boolean Code()
  {
    ArrayList localArrayList = cst.Code(this.V);
    if (localArrayList.isEmpty()) {
      throw new Exception("Unable to get workspace screens");
    }
    Object localObject1 = localArrayList.iterator();
    while (((Iterator)localObject1).hasNext()) {
      Code(((Long)((Iterator)localObject1).next()).longValue());
    }
    if (!this.S.isEmpty())
    {
      localObject1 = new dfv();
      Object localObject2 = this.S.iterator();
      aux localAux;
      while (((Iterator)localObject2).hasNext())
      {
        localAux = (aux)((Iterator)localObject2).next();
        ((dfv)localObject1).put(localAux.L, localAux);
      }
      do
      {
        localObject2 = new nul(new dfo(this.a, this.b), V(this.S), true);
        ((nul)localObject2).Code();
        if (((nul)localObject2).I.size() > 0)
        {
          long l = LauncherProvider.I();
          localArrayList.add(Long.valueOf(l));
          localObject2 = ((nul)localObject2).I.iterator();
          while (((Iterator)localObject2).hasNext())
          {
            localAux = (aux)((Iterator)localObject2).next();
            if (!this.S.remove(((dfv)localObject1).get(localAux.L))) {
              throw new Exception("Unable to find matching items");
            }
            localAux.c = l;
            Code(localAux);
          }
        }
        throw new Exception("None of the items can be placed on an empty screen");
      } while (!this.S.isEmpty());
      localObject1 = ctl.com1.Code;
      this.C.add(ContentProviderOperation.newDelete((Uri)localObject1).build());
      int j = localArrayList.size();
      int i = 0;
      while (i < j)
      {
        localObject2 = new ContentValues();
        ((ContentValues)localObject2).put("_id", Long.valueOf(((Long)localArrayList.get(i)).longValue()));
        ((ContentValues)localObject2).put("screenRank", Integer.valueOf(i));
        this.C.add(ContentProviderOperation.newInsert((Uri)localObject1).withValues((ContentValues)localObject2).build());
        i += 1;
      }
    }
    if (!this.C.isEmpty()) {
      this.V.getContentResolver().applyBatch(LauncherProvider.Code, this.C);
    }
    if (!this.B.isEmpty())
    {
      new StringBuilder("Removing items: ").append(TextUtils.join(", ", this.B));
      this.V.getContentResolver().delete(ctl.nul.Code, dgx.Code("_id", this.B), null);
    }
    return (!this.C.isEmpty()) || (!this.B.isEmpty());
  }
  
  public static final class aux
    extends cso
    implements Comparable<aux>
  {
    float Code;
    
    aux() {}
  }
  
  public static final class con
  {
    private final HashSet<String> Code;
    private final Context V;
    
    con(HashSet<String> paramHashSet, Context paramContext)
    {
      this.Code = paramHashSet;
      this.V = paramContext;
    }
    
    final boolean Code(Point paramPoint1, Point paramPoint2)
    {
      boolean bool2;
      Point localPoint;
      if (!paramPoint2.equals(paramPoint1))
      {
        if (paramPoint1.x < paramPoint2.x) {
          paramPoint1.x = paramPoint2.x;
        }
        if (paramPoint1.y < paramPoint2.y) {
          paramPoint1.y = paramPoint2.y;
        }
        boolean bool1 = false;
        bool2 = bool1;
        if (paramPoint2.equals(paramPoint1)) {
          break label169;
        }
        localPoint = new Point(paramPoint1);
        if (paramPoint2.x < localPoint.x) {
          localPoint.x -= 1;
        }
        if (paramPoint2.y < localPoint.y) {
          localPoint.y -= 1;
        }
        if (!new csn(this.V, csp.Code().e, this.Code, paramPoint1, localPoint).Code()) {
          break label172;
        }
        bool1 = true;
      }
      label169:
      label172:
      for (;;)
      {
        paramPoint1.set(localPoint.x, localPoint.y);
        break;
        bool2 = false;
        return bool2;
      }
    }
  }
  
  final class nul
  {
    private final ArrayList<csn.aux> B;
    private final dfo C;
    float Code = Float.MAX_VALUE;
    private final int F;
    ArrayList<csn.aux> I;
    private final boolean S;
    float V = Float.MAX_VALUE;
    
    nul(ArrayList<csn.aux> paramArrayList, int paramInt)
    {
      this(paramArrayList, paramInt, false);
    }
    
    nul(ArrayList<csn.aux> paramArrayList, int paramInt)
    {
      this.C = paramArrayList;
      this.B = paramInt;
      boolean bool;
      this.S = bool;
      this.F = 0;
      Collections.sort(this.B);
    }
    
    private void Code(int paramInt, float paramFloat1, float paramFloat2, ArrayList<csn.aux> paramArrayList)
    {
      if ((paramFloat1 >= this.Code) || ((paramFloat1 == this.Code) && (paramFloat2 >= this.V))) {
        label27:
        return;
      }
      if (paramInt >= this.B.size())
      {
        this.Code = paramFloat1;
        this.V = paramFloat2;
        this.I = csn.Code(paramArrayList);
        return;
      }
      csn.aux localAux = (csn.aux)this.B.get(paramInt);
      int i2 = localAux.d;
      int i3 = localAux.e;
      ArrayList localArrayList = new ArrayList(paramArrayList.size() + 1);
      localArrayList.addAll(paramArrayList);
      localArrayList.add(localAux);
      int k;
      int m;
      int i;
      label156:
      int j;
      if ((localAux.f > 1) || (localAux.g > 1))
      {
        k = localAux.f;
        m = localAux.g;
        i = this.F;
        if (i < csn.Code(csn.this))
        {
          j = 0;
          label171:
          if (j < csn.V(csn.this))
          {
            if (j == i2) {
              break label1067;
            }
            localAux.d = j;
          }
        }
      }
      label655:
      label670:
      label708:
      label1046:
      label1052:
      label1067:
      for (float f1 = 1.0F + paramFloat2;; f1 = paramFloat2)
      {
        float f2 = f1;
        if (i != i3)
        {
          localAux.e = i;
          f2 = f1 + 1.0F;
        }
        f1 = f2;
        if (this.S) {
          f1 = paramFloat2;
        }
        if (this.C.Code(j, i, k, m))
        {
          this.C.Code(localAux, true);
          Code(paramInt + 1, paramFloat1, f1, localArrayList);
          this.C.Code(localAux, false);
        }
        if ((k > localAux.h) && (this.C.Code(j, i, k - 1, m)))
        {
          localAux.f -= 1;
          this.C.Code(localAux, true);
          Code(paramInt + 1, paramFloat1, 1.0F + f1, localArrayList);
          this.C.Code(localAux, false);
          localAux.f += 1;
        }
        if ((m > localAux.i) && (this.C.Code(j, i, k, m - 1)))
        {
          localAux.g -= 1;
          this.C.Code(localAux, true);
          Code(paramInt + 1, paramFloat1, 1.0F + f1, localArrayList);
          this.C.Code(localAux, false);
          localAux.g += 1;
        }
        if ((m > localAux.i) && (k > localAux.h) && (this.C.Code(j, i, k - 1, m - 1)))
        {
          localAux.f -= 1;
          localAux.g -= 1;
          this.C.Code(localAux, true);
          Code(paramInt + 1, paramFloat1, f1 + 2.0F, localArrayList);
          this.C.Code(localAux, false);
          localAux.f += 1;
          localAux.g += 1;
        }
        localAux.d = i2;
        localAux.e = i3;
        j += 1;
        break label171;
        i += 1;
        break label156;
        paramInt += 1;
        paramFloat1 += localAux.Code;
        break;
        int n = Integer.MAX_VALUE;
        m = Integer.MAX_VALUE;
        k = Integer.MAX_VALUE;
        i = this.F;
        int i1;
        if (i < csn.Code(csn.this))
        {
          j = 0;
          if (j < csn.V(csn.this))
          {
            if (this.C.Code[j][i] != 0) {
              break label1052;
            }
            if (this.S)
            {
              i1 = 0;
              if (i1 >= n) {
                break label1052;
              }
              n = i;
              m = j;
            }
          }
        }
        for (k = i1;; k = i1)
        {
          i1 = j + 1;
          j = k;
          k = n;
          n = j;
          j = i1;
          break label670;
          i1 = (localAux.d - j) * (localAux.d - j) + (localAux.e - i) * (localAux.e - i);
          break label708;
          i += 1;
          break label655;
          if ((m < csn.V(csn.this)) && (k < csn.Code(csn.this)))
          {
            if (m == i2) {
              break label1046;
            }
            localAux.d = m;
          }
          for (f1 = 1.0F + paramFloat2;; f1 = paramFloat2)
          {
            f2 = f1;
            if (k != i3)
            {
              localAux.e = k;
              f2 = f1 + 1.0F;
            }
            f1 = f2;
            if (this.S) {
              f1 = paramFloat2;
            }
            this.C.Code(localAux, true);
            Code(paramInt + 1, paramFloat1, f1, localArrayList);
            this.C.Code(localAux, false);
            localAux.d = i2;
            localAux.e = i3;
            if ((paramInt + 1 >= this.B.size()) || (((csn.aux)this.B.get(paramInt + 1)).Code < localAux.Code) || (this.S)) {
              break label27;
            }
            paramInt += 1;
            paramFloat1 += localAux.Code;
            break;
            paramInt += 1;
            while (paramInt < this.B.size())
            {
              paramFloat1 += ((csn.aux)this.B.get(paramInt)).Code;
              paramInt += 1;
            }
            paramInt = this.B.size();
            paramFloat1 += localAux.Code;
            break;
          }
          i1 = n;
          n = k;
        }
      }
    }
    
    public final void Code()
    {
      Code(0, 0.0F, 0.0F, new ArrayList());
    }
  }
}
