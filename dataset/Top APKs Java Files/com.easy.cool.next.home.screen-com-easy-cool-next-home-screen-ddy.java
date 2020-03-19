package com.easy.cool.next.home.screen;

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
import com.easy.cool.next.home.screen.desktop.widget.LauncherAppWidgetProviderInfo;
import com.easy.cool.next.home.screen.model.LauncherProvider;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class ddy
{
  private static final String Code = ddy.class.getSimpleName();
  private final ArrayList<Long> B = new ArrayList();
  private final ArrayList<ContentProviderOperation> C = new ArrayList();
  private final int D;
  private final HashSet<String> F;
  private final bwd I;
  private final int L;
  private final ArrayList<S> S = new ArrayList();
  private final Context V;
  private final ContentValues Z = new ContentValues();
  private final int a;
  private final int b;
  private final boolean c;
  private final boolean d;
  
  ddy(Context paramContext, bwd paramBwd, HashSet<String> paramHashSet, Point paramPoint1, Point paramPoint2)
  {
    this.V = paramContext;
    this.F = paramHashSet;
    this.I = paramBwd;
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
  
  private ArrayList<S> Code(int paramInt1, int paramInt2, int paramInt3, ArrayList<S> paramArrayList, float[] paramArrayOfFloat)
  {
    drn localDrn = new drn(this.a, this.b);
    localDrn.Code(0, 0, this.a, paramInt3, true);
    label44:
    ArrayList localArrayList1;
    ArrayList localArrayList2;
    if (this.c)
    {
      if (!this.d) {
        break label198;
      }
      localArrayList1 = new ArrayList();
      localArrayList2 = new ArrayList();
      paramArrayList = paramArrayList.iterator();
    }
    for (;;)
    {
      if (!paramArrayList.hasNext()) {
        break label265;
      }
      S localS = (S)paramArrayList.next();
      if (((localS.c <= paramInt1) && (localS.e + localS.c > paramInt1)) || ((localS.d <= paramInt2) && (localS.f + localS.d > paramInt2)))
      {
        localArrayList2.add(localS);
        if (localS.c >= paramInt1) {
          localS.c -= 1;
        }
        if (localS.d < paramInt2) {
          continue;
        }
        localS.d -= 1;
        continue;
        paramInt1 = Integer.MAX_VALUE;
        break;
        label198:
        paramInt2 = Integer.MAX_VALUE;
        break label44;
      }
      if (localS.c > paramInt1) {
        localS.c -= 1;
      }
      if (localS.d > paramInt2) {
        localS.d -= 1;
      }
      localArrayList1.add(localS);
      localDrn.Code(localS, true);
    }
    label265:
    paramArrayList = new yU(localDrn, localArrayList2, paramInt3);
    paramArrayList.Code();
    localArrayList1.addAll(paramArrayList.I);
    paramArrayOfFloat[0] = paramArrayList.Code;
    paramArrayOfFloat[1] = paramArrayList.V;
    return localArrayList1;
  }
  
  public static void Code(int paramInt1, int paramInt2)
  {
    erw.Code(ded.Code).V("migration_src_workspace_size", V(paramInt1, paramInt2));
  }
  
  private void Code(long paramLong)
  {
    Object localObject3 = V(paramLong);
    int i = Integer.MAX_VALUE;
    int k = Integer.MAX_VALUE;
    float f2 = Float.MAX_VALUE;
    float f1 = Float.MAX_VALUE;
    Object localObject4 = new float[2];
    Object localObject1 = null;
    int m = 0;
    float f3;
    int j;
    label72:
    Object localObject2;
    if (m < this.D)
    {
      int n = this.L - 1;
      f3 = f1;
      j = i;
      i = k;
      f1 = f2;
      f2 = f3;
      k = n;
      if (k < 0) {
        break label643;
      }
      localObject2 = Code(m, k, 0, V((ArrayList)localObject3), (float[])localObject4);
      if ((localObject4[0] >= f1) && ((localObject4[0] != f1) || (localObject4[1] >= f2))) {
        break label630;
      }
      f2 = localObject4[0];
      f1 = localObject4[1];
      if (this.c)
      {
        j = m;
        label145:
        if (!this.d) {
          break label282;
        }
        i = k;
        localObject1 = localObject2;
        label160:
        if (this.d) {
          break label289;
        }
      }
    }
    for (;;)
    {
      if (!this.c)
      {
        k = j;
        j = i;
      }
      for (;;)
      {
        efu.V(Code, String.format(Locale.ENGLISH, "Removing row %d, column %d on screen %d", new Object[] { Integer.valueOf(j), Integer.valueOf(k), Long.valueOf(paramLong) }));
        localObject2 = new drv();
        localObject3 = V((ArrayList)localObject3).iterator();
        for (;;)
        {
          if (((Iterator)localObject3).hasNext())
          {
            localObject4 = (S)((Iterator)localObject3).next();
            ((drv)localObject2).put(((S)localObject4).D, localObject4);
            continue;
            break label145;
            label282:
            localObject1 = localObject2;
            break label160;
            label289:
            k -= 1;
            f3 = f2;
            f2 = f1;
            f1 = f3;
            break label72;
            m += 1;
            k = i;
            i = j;
            break;
          }
        }
        if (localObject1 != null)
        {
          localObject3 = ((ArrayList)localObject1).iterator();
          while (((Iterator)localObject3).hasNext())
          {
            localObject4 = (S)((Iterator)localObject3).next();
            S localS = (S)((drv)localObject2).get(((S)localObject4).D);
            ((drv)localObject2).remove(((S)localObject4).D);
            if (!((S)localObject4).V(localS)) {
              Code((S)localObject4);
            }
          }
        }
        localObject2 = ((drv)localObject2).iterator();
        while (((Iterator)localObject2).hasNext())
        {
          localObject3 = (S)((Iterator)localObject2).next();
          this.S.add(localObject3);
        }
        if ((!this.S.isEmpty()) && (f2 == 0.0F))
        {
          localObject2 = new drn(this.a, this.b);
          ((drn)localObject2).Code(0, 0, this.a, 0, true);
          localObject1 = ((ArrayList)localObject1).iterator();
          while (((Iterator)localObject1).hasNext()) {
            ((drn)localObject2).Code((S)((Iterator)localObject1).next(), true);
          }
          localObject1 = new yU((drn)localObject2, V(this.S), 0, true);
          ((yU)localObject1).Code();
          if (((yU)localObject1).Code == 0.0F)
          {
            localObject1 = ((yU)localObject1).I.iterator();
            while (((Iterator)localObject1).hasNext())
            {
              localObject2 = (S)((Iterator)localObject1).next();
              ((S)localObject2).b = paramLong;
              Code((S)localObject2);
            }
            this.S.clear();
          }
        }
        return;
        j = k;
        k = i;
      }
      label630:
      f3 = f1;
      f1 = f2;
      f2 = f3;
      break label160;
      label643:
      f3 = f1;
      f1 = f2;
      f2 = f3;
    }
  }
  
  private void Code(String paramString)
    throws Exception
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
    erw localErw = erw.Code(ded.Code);
    Object localObject3 = dea.Code().S();
    String str = V(((bwd)localObject3).S, ((bwd)localObject3).C);
    Object localObject2 = localErw.Code("migration_src_workspace_size", str);
    if (str.equals(localObject2)) {
      return true;
    }
    long l = System.currentTimeMillis();
    for (;;)
    {
      try
      {
        HashSet localHashSet = V(paramContext);
        localObject3 = new Point(((bwd)localObject3).S, ((bwd)localObject3).C);
        localObject2 = I((String)localObject2);
        if (!new Y(localHashSet, paramContext).Code((Point)localObject2, (Point)localObject3)) {
          break label333;
        }
        i = 1;
        if (i != 0)
        {
          paramContext = paramContext.getContentResolver().query(dey.yU.Code, null, null, null, null);
          if (paramContext == null) {
            break label327;
          }
        }
        efu.Code(Code, "Workspace migration completed in " + (System.currentTimeMillis() - l));
      }
      catch (Exception paramContext)
      {
        paramContext = paramContext;
        efu.B(Code, "Error during grid migration: " + paramContext);
        return false;
      }
      finally
      {
        efu.Code(Code, "Workspace migration completed in " + (System.currentTimeMillis() - l));
        localErw.V("migration_src_workspace_size", str);
      }
      localErw.V("migration_src_workspace_size", str);
      return true;
      label327:
      boolean bool = false;
      continue;
      label333:
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
  
  private static Point I(String paramString)
  {
    paramString = paramString.split(",");
    return new Point(Integer.parseInt(paramString[0]), Integer.parseInt(paramString[1]));
  }
  
  private static String V(int paramInt1, int paramInt2)
  {
    return String.format(Locale.ENGLISH, "%d,%d", new Object[] { Integer.valueOf(paramInt1), Integer.valueOf(paramInt2) });
  }
  
  private ArrayList<S> V(long paramLong)
  {
    String str = "container = -100 AND screen = " + paramLong;
    Cursor localCursor = Code(new String[] { "_id", "itemType", "cellX", "cellY", "spanX", "spanY", "intent", "appWidgetProvider", "appWidgetId" }, str);
    int j = localCursor.getColumnIndexOrThrow("_id");
    int k = localCursor.getColumnIndexOrThrow("itemType");
    int m = localCursor.getColumnIndexOrThrow("cellX");
    int n = localCursor.getColumnIndexOrThrow("cellY");
    int i1 = localCursor.getColumnIndexOrThrow("spanX");
    int i2 = localCursor.getColumnIndexOrThrow("spanY");
    int i3 = localCursor.getColumnIndexOrThrow("intent");
    int i4 = localCursor.getColumnIndexOrThrow("appWidgetProvider");
    int i5 = localCursor.getColumnIndexOrThrow("appWidgetId");
    ArrayList localArrayList = new ArrayList();
    for (;;)
    {
      S localS;
      try
      {
        if (!localCursor.moveToNext()) {
          break label813;
        }
        localS = new S();
        localS.D = localCursor.getLong(j);
        localS.L = localCursor.getInt(k);
        localS.c = localCursor.getInt(m);
        localS.d = localCursor.getInt(n);
        localS.e = localCursor.getInt(i1);
        localS.f = localCursor.getInt(i2);
        localS.b = paramLong;
        try
        {
          switch (localS.L)
          {
          case 3: 
            throw new Exception("Invalid item type");
          }
        }
        catch (Exception localException)
        {
          efu.V(Code, "Removing item " + localS.D + ", exception: " + localException);
          this.B.add(Long.valueOf(localS.D));
        }
        continue;
        Code(localCursor.getString(i3));
      }
      finally
      {
        localCursor.close();
      }
      float f;
      if (localS.L == 0)
      {
        f = 0.8F;
        label478:
        localS.Code = f;
      }
      for (;;)
      {
        localArrayList.add(localS);
        break;
        f = 1.0F;
        break label478;
        localS.Code = Math.max(2.0F, 0.6F * localS.e * localS.f);
        Object localObject2 = ComponentName.unflattenFromString(localCursor.getString(i4));
        if (localS.L == 4)
        {
          V(((ComponentName)localObject2).getPackageName());
          i = localCursor.getInt(i5);
          localObject2 = bnh.Code(this.V).V(i);
          if (localObject2 == null)
          {
            localObject2 = null;
            if (localObject2 == null) {
              break label713;
            }
            if (((Point)localObject2).x <= 0) {
              break label693;
            }
            i = ((Point)localObject2).x;
            label611:
            localS.g = i;
            if (((Point)localObject2).y <= 0) {
              break label703;
            }
            i = ((Point)localObject2).y;
            label633:
            localS.h = i;
          }
        }
        for (;;)
        {
          if ((localS.g <= this.a) && (localS.h <= this.b)) {
            break label769;
          }
          throw new Exception("Widget can't be resized down to fit the grid");
          localObject2 = ((LauncherAppWidgetProviderInfo)localObject2).Code(this.I, this.V);
          break;
          label693:
          i = localS.e;
          break label611;
          label703:
          i = localS.f;
          break label633;
          label713:
          localS.h = 2;
          localS.g = 2;
          continue;
          localObject2 = (cix)Class.forName(((ComponentName)localObject2).getClassName()).newInstance();
          localS.g = ((cix)localObject2).S();
          localS.h = ((cix)localObject2).F();
        }
        label769:
        continue;
        int i = I(localS.D);
        if (i == 0) {
          throw new Exception("Folder is empty");
        }
        localS.Code = (i * 0.5F);
      }
      label813:
      localCursor.close();
      return localArrayList;
    }
  }
  
  private static ArrayList<S> V(ArrayList<S> paramArrayList)
  {
    ArrayList localArrayList = new ArrayList(paramArrayList.size());
    paramArrayList = paramArrayList.iterator();
    while (paramArrayList.hasNext()) {
      localArrayList.add(((S)paramArrayList.next()).Code());
    }
    return localArrayList;
  }
  
  private static HashSet<String> V(Context paramContext)
  {
    HashSet localHashSet = new HashSet();
    Iterator localIterator = paramContext.getPackageManager().getInstalledPackages(8192).iterator();
    while (localIterator.hasNext()) {
      localHashSet.add(((PackageInfo)localIterator.next()).packageName);
    }
    localHashSet.addAll(bnr.Code(paramContext).Code().keySet());
    return localHashSet;
  }
  
  private void V(String paramString)
    throws Exception
  {
    if (!this.F.contains(paramString)) {
      throw new Exception("Package not available");
    }
  }
  
  private boolean V()
    throws Exception
  {
    if (!this.C.isEmpty()) {
      this.V.getContentResolver().applyBatch(LauncherProvider.Code, this.C);
    }
    if (!this.B.isEmpty())
    {
      efu.V(Code, "Removing items: " + TextUtils.join(", ", this.B));
      this.V.getContentResolver().delete(dey.yU.Code, dsy.Code("_id", this.B), null);
    }
    return (!this.C.isEmpty()) || (!this.B.isEmpty());
  }
  
  protected Cursor Code(String[] paramArrayOfString, String paramString)
  {
    return this.V.getContentResolver().query(dey.yU.Code, paramArrayOfString, paramString, null, null, null);
  }
  
  protected void Code(S paramS)
  {
    this.Z.clear();
    paramS.Code(this.Z);
    this.C.add(ContentProviderOperation.newUpdate(dey.yU.Code(paramS.D)).withValues(this.Z).build());
  }
  
  boolean Code()
    throws Exception
  {
    int i = 0;
    ArrayList localArrayList = dee.Code(this.V);
    if (localArrayList.isEmpty()) {
      throw new Exception("Unable to get workspace screens");
    }
    Object localObject1 = localArrayList.iterator();
    long l;
    while (((Iterator)localObject1).hasNext())
    {
      l = ((Long)((Iterator)localObject1).next()).longValue();
      efu.V(Code, "Migrating " + l);
      Code(l);
    }
    if (!this.S.isEmpty())
    {
      localObject1 = new drv();
      Object localObject2 = this.S.iterator();
      S localS;
      while (((Iterator)localObject2).hasNext())
      {
        localS = (S)((Iterator)localObject2).next();
        ((drv)localObject1).put(localS.D, localS);
      }
      do
      {
        localObject2 = new yU(new drn(this.a, this.b), V(this.S), 0, true);
        ((yU)localObject2).Code();
        if (((yU)localObject2).I.size() > 0)
        {
          l = LauncherProvider.I();
          localArrayList.add(Long.valueOf(l));
          localObject2 = ((yU)localObject2).I.iterator();
          while (((Iterator)localObject2).hasNext())
          {
            localS = (S)((Iterator)localObject2).next();
            if (!this.S.remove(((drv)localObject1).get(localS.D))) {
              throw new Exception("Unable to find matching items");
            }
            localS.b = l;
            Code(localS);
          }
        }
        throw new Exception("None of the items can be placed on an empty screen");
      } while (!this.S.isEmpty());
      localObject1 = dey.T.Code;
      this.C.add(ContentProviderOperation.newDelete((Uri)localObject1).build());
      int j = localArrayList.size();
      while (i < j)
      {
        localObject2 = new ContentValues();
        ((ContentValues)localObject2).put("_id", Long.valueOf(((Long)localArrayList.get(i)).longValue()));
        ((ContentValues)localObject2).put("screenRank", Integer.valueOf(i));
        this.C.add(ContentProviderOperation.newInsert((Uri)localObject1).withValues((ContentValues)localObject2).build());
        i += 1;
      }
    }
    return V();
  }
  
  public static class S
    extends ddz
    implements Comparable<S>
  {
    float Code;
    
    S() {}
    
    public int Code(S paramS)
    {
      if (this.L == 4)
      {
        if (paramS.L == 4) {
          return paramS.f * paramS.e - this.e * this.f;
        }
        return -1;
      }
      if (paramS.L == 4) {
        return 1;
      }
      return Float.compare(paramS.Code, this.Code);
    }
    
    public S Code()
    {
      S localS = new S();
      localS.Code(this);
      localS.Code = this.Code;
      localS.g = this.g;
      localS.h = this.h;
      return localS;
    }
    
    void Code(ContentValues paramContentValues)
    {
      paramContentValues.put("screen", Long.valueOf(this.b));
      paramContentValues.put("cellX", Integer.valueOf(this.c));
      paramContentValues.put("cellY", Integer.valueOf(this.d));
      paramContentValues.put("spanX", Integer.valueOf(this.e));
      paramContentValues.put("spanY", Integer.valueOf(this.f));
    }
    
    boolean V(S paramS)
    {
      return (paramS.c == this.c) && (paramS.d == this.d) && (paramS.e == this.e) && (paramS.f == this.f) && (paramS.b == this.b);
    }
  }
  
  public static class Y
  {
    private final HashSet<String> Code;
    private final Context V;
    
    Y(HashSet<String> paramHashSet, Context paramContext)
    {
      this.Code = paramHashSet;
      this.V = paramContext;
    }
    
    boolean Code(Point paramPoint1, Point paramPoint2)
      throws Exception
    {
      boolean bool2 = false;
      boolean bool3 = false;
      if (!paramPoint2.equals(paramPoint1))
      {
        if (paramPoint1.x < paramPoint2.x) {
          paramPoint1.x = paramPoint2.x;
        }
        boolean bool1 = bool3;
        if (paramPoint1.y < paramPoint2.y)
        {
          paramPoint1.y = paramPoint2.y;
          bool1 = bool3;
        }
        for (;;)
        {
          bool2 = bool1;
          if (paramPoint2.equals(paramPoint1)) {
            break;
          }
          Point localPoint = new Point(paramPoint1);
          if (paramPoint2.x < localPoint.x) {
            localPoint.x -= 1;
          }
          if (paramPoint2.y < localPoint.y) {
            localPoint.y -= 1;
          }
          if (V(paramPoint1, localPoint)) {
            bool1 = true;
          }
          paramPoint1.set(localPoint.x, localPoint.y);
        }
      }
      return bool2;
    }
    
    boolean V(Point paramPoint1, Point paramPoint2)
      throws Exception
    {
      return new ddy(this.V, dea.Code().S(), this.Code, paramPoint1, paramPoint2).Code();
    }
  }
  
  class yU
  {
    private final ArrayList<ddy.S> B;
    private final drn C;
    float Code = Float.MAX_VALUE;
    private final int F;
    ArrayList<ddy.S> I;
    private final boolean S;
    float V = Float.MAX_VALUE;
    
    yU(ArrayList<ddy.S> paramArrayList, int paramInt)
    {
      this(paramArrayList, paramInt, i, false);
    }
    
    yU(ArrayList<ddy.S> paramArrayList, int paramInt, boolean paramBoolean)
    {
      this.C = paramArrayList;
      this.B = paramInt;
      boolean bool;
      this.S = bool;
      this.F = paramBoolean;
      Collections.sort(this.B);
    }
    
    public void Code()
    {
      Code(0, 0.0F, 0.0F, new ArrayList());
    }
    
    public void Code(int paramInt, float paramFloat1, float paramFloat2, ArrayList<ddy.S> paramArrayList)
    {
      if ((paramFloat1 >= this.Code) || ((paramFloat1 == this.Code) && (paramFloat2 >= this.V))) {
        return;
      }
      if (paramInt >= this.B.size())
      {
        this.Code = paramFloat1;
        this.V = paramFloat2;
        this.I = ddy.Code(paramArrayList);
        return;
      }
      ddy.S localS = (ddy.S)this.B.get(paramInt);
      int i2 = localS.c;
      int i3 = localS.d;
      ArrayList localArrayList = new ArrayList(paramArrayList.size() + 1);
      localArrayList.addAll(paramArrayList);
      localArrayList.add(localS);
      int k;
      int m;
      int i;
      label156:
      int j;
      if ((localS.e > 1) || (localS.f > 1))
      {
        k = localS.e;
        m = localS.f;
        i = this.F;
        if (i < ddy.Code(ddy.this))
        {
          j = 0;
          label171:
          if (j < ddy.V(ddy.this))
          {
            if (j == i2) {
              break label1076;
            }
            localS.c = j;
          }
        }
      }
      label658:
      label673:
      label711:
      label1055:
      label1061:
      label1076:
      for (float f1 = 1.0F + paramFloat2;; f1 = paramFloat2)
      {
        float f2 = f1;
        if (i != i3)
        {
          localS.d = i;
          f2 = f1 + 1.0F;
        }
        f1 = f2;
        if (this.S) {
          f1 = paramFloat2;
        }
        if (this.C.Code(j, i, k, m))
        {
          this.C.Code(localS, true);
          Code(paramInt + 1, paramFloat1, f1, localArrayList);
          this.C.Code(localS, false);
        }
        if ((k > localS.g) && (this.C.Code(j, i, k - 1, m)))
        {
          localS.e -= 1;
          this.C.Code(localS, true);
          Code(paramInt + 1, paramFloat1, 1.0F + f1, localArrayList);
          this.C.Code(localS, false);
          localS.e += 1;
        }
        if ((m > localS.h) && (this.C.Code(j, i, k, m - 1)))
        {
          localS.f -= 1;
          this.C.Code(localS, true);
          Code(paramInt + 1, paramFloat1, 1.0F + f1, localArrayList);
          this.C.Code(localS, false);
          localS.f += 1;
        }
        if ((m > localS.h) && (k > localS.g) && (this.C.Code(j, i, k - 1, m - 1)))
        {
          localS.e -= 1;
          localS.f -= 1;
          this.C.Code(localS, true);
          Code(paramInt + 1, paramFloat1, f1 + 2.0F, localArrayList);
          this.C.Code(localS, false);
          localS.e += 1;
          localS.f += 1;
        }
        localS.c = i2;
        localS.d = i3;
        j += 1;
        break label171;
        i += 1;
        break label156;
        Code(paramInt + 1, localS.Code + paramFloat1, paramFloat2, paramArrayList);
        return;
        int n = Integer.MAX_VALUE;
        m = Integer.MAX_VALUE;
        k = Integer.MAX_VALUE;
        i = this.F;
        int i1;
        if (i < ddy.Code(ddy.this))
        {
          j = 0;
          if (j < ddy.V(ddy.this))
          {
            if (this.C.Code[j][i] != 0) {
              break label1061;
            }
            if (this.S)
            {
              i1 = 0;
              if (i1 >= n) {
                break label1061;
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
          break label673;
          i1 = (localS.c - j) * (localS.c - j) + (localS.d - i) * (localS.d - i);
          break label711;
          i += 1;
          break label658;
          if ((m < ddy.V(ddy.this)) && (k < ddy.Code(ddy.this)))
          {
            if (m == i2) {
              break label1055;
            }
            localS.c = m;
          }
          for (f1 = 1.0F + paramFloat2;; f1 = paramFloat2)
          {
            f2 = f1;
            if (k != i3)
            {
              localS.d = k;
              f2 = f1 + 1.0F;
            }
            f1 = f2;
            if (this.S) {
              f1 = paramFloat2;
            }
            this.C.Code(localS, true);
            Code(paramInt + 1, paramFloat1, f1, localArrayList);
            this.C.Code(localS, false);
            localS.c = i2;
            localS.d = i3;
            if ((paramInt + 1 >= this.B.size()) || (((ddy.S)this.B.get(paramInt + 1)).Code < localS.Code) || (this.S)) {
              break;
            }
            Code(paramInt + 1, localS.Code + paramFloat1, paramFloat2, paramArrayList);
            return;
            paramInt += 1;
            while (paramInt < this.B.size())
            {
              paramFloat1 += ((ddy.S)this.B.get(paramInt)).Code;
              paramInt += 1;
            }
            Code(this.B.size(), localS.Code + paramFloat1, paramFloat2, paramArrayList);
            return;
          }
          i1 = n;
          n = k;
        }
      }
    }
  }
}
