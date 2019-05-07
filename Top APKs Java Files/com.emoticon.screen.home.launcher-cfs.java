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
import com.emoticon.screen.home.launcher.desktop.widget.LauncherAppWidgetProviderInfo;
import com.emoticon.screen.home.launcher.model.LauncherProvider;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class cfs
{
  private static final String a = cfs.class.getSimpleName();
  private final Context b;
  private final bez c;
  private final ContentValues d = new ContentValues();
  private final ArrayList<Long> e = new ArrayList();
  private final ArrayList<ContentProviderOperation> f = new ArrayList();
  private final ArrayList<cfs.a> g = new ArrayList();
  private final HashSet<String> h;
  private final int i;
  private final int j;
  private final int k;
  private final int l;
  private final boolean m;
  private final boolean n;
  
  cfs(Context paramContext, bez paramBez, HashSet<String> paramHashSet, Point paramPoint1, Point paramPoint2)
  {
    this.b = paramContext;
    this.h = paramHashSet;
    this.c = paramBez;
    this.i = paramPoint1.x;
    this.j = paramPoint1.y;
    this.k = paramPoint2.x;
    this.l = paramPoint2.y;
    if (this.k < this.i)
    {
      bool1 = true;
      this.m = bool1;
      if (this.l >= this.j) {
        break label150;
      }
    }
    label150:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      this.n = bool1;
      return;
      bool1 = false;
      break;
    }
  }
  
  private Cursor a(String[] paramArrayOfString, String paramString)
  {
    return this.b.getContentResolver().query(cgr.c.a, paramArrayOfString, paramString, null, null, null);
  }
  
  public static void a(int paramInt1, int paramInt2)
  {
    hbx.a(cfx.a).b("migration_src_workspace_size", b(paramInt1, paramInt2));
  }
  
  private void a(long paramLong)
  {
    Object localObject3 = b(paramLong);
    int i1 = Integer.MAX_VALUE;
    int i5 = Integer.MAX_VALUE;
    float f1 = Float.MAX_VALUE;
    float f4 = Float.MAX_VALUE;
    Object localObject4 = new float[2];
    Object localObject1 = null;
    int i3 = 0;
    Object localObject2 = localObject1;
    float f2 = f1;
    int i2 = i5;
    int i4 = i1;
    label77:
    Object localObject5;
    int i6;
    label141:
    label302:
    label372:
    float f3;
    if (i3 < this.i)
    {
      i4 = this.j - 1;
      f2 = f4;
      i2 = i1;
      i1 = i5;
      if (i4 >= 0)
      {
        Object localObject6 = b((ArrayList)localObject3);
        localObject5 = new ctz(this.k, this.l);
        ((ctz)localObject5).a(0, 0, this.k, 0, true);
        ArrayList localArrayList;
        if (this.m)
        {
          i5 = i3;
          if (!this.n) {
            break label302;
          }
          i6 = i4;
          localObject2 = new ArrayList();
          localArrayList = new ArrayList();
          localObject6 = ((ArrayList)localObject6).iterator();
        }
        for (;;)
        {
          if (!((Iterator)localObject6).hasNext()) {
            break label372;
          }
          cfs.a localA = (cfs.a)((Iterator)localObject6).next();
          if (((localA.o <= i5) && (localA.q + localA.o > i5)) || ((localA.p <= i6) && (localA.r + localA.p > i6)))
          {
            localArrayList.add(localA);
            if (localA.o >= i5) {
              localA.o -= 1;
            }
            if (localA.p < i6) {
              continue;
            }
            localA.p -= 1;
            continue;
            i5 = Integer.MAX_VALUE;
            break;
            i6 = Integer.MAX_VALUE;
            break label141;
          }
          if (localA.o > i5) {
            localA.o -= 1;
          }
          if (localA.p > i6) {
            localA.p -= 1;
          }
          ((ArrayList)localObject2).add(localA);
          ((ctz)localObject5).a(localA, true);
        }
        localObject5 = new cfs.c((ctz)localObject5, localArrayList);
        ((cfs.c)localObject5).a();
        ((ArrayList)localObject2).addAll(((cfs.c)localObject5).c);
        localObject4[0] = ((cfs.c)localObject5).a;
        localObject4[1] = ((cfs.c)localObject5).b;
        if ((localObject4[0] >= f1) && ((localObject4[0] != f1) || (localObject4[1] >= f2))) {
          break label1040;
        }
        f2 = localObject4[0];
        f3 = localObject4[1];
        if (this.m)
        {
          i2 = i3;
          label471:
          if (!this.n) {
            break label535;
          }
          i6 = i4;
        }
      }
    }
    for (;;)
    {
      localObject1 = localObject2;
      f4 = f3;
      f1 = f2;
      i5 = i6;
      i1 = i2;
      if (this.n)
      {
        i4 -= 1;
        localObject1 = localObject2;
        f1 = f2;
        i1 = i6;
        f2 = f3;
        break label77;
        break label471;
        label535:
        i6 = i1;
        continue;
        i5 = i1;
        i1 = i2;
        f4 = f2;
      }
      localObject2 = localObject1;
      f2 = f1;
      i2 = i5;
      i4 = i1;
      if (this.m)
      {
        i3 += 1;
        break;
      }
      String.format(Locale.ENGLISH, "Removing row %d, column %d on screen %d", new Object[] { Integer.valueOf(i2), Integer.valueOf(i4), Long.valueOf(paramLong) });
      localObject1 = new cug();
      localObject3 = b((ArrayList)localObject3).iterator();
      while (((Iterator)localObject3).hasNext())
      {
        localObject4 = (cfs.a)((Iterator)localObject3).next();
        ((cug)localObject1).put(((cfs.a)localObject4).k, localObject4);
      }
      if (localObject2 != null)
      {
        localObject3 = ((ArrayList)localObject2).iterator();
        label823:
        while (((Iterator)localObject3).hasNext())
        {
          localObject4 = (cfs.a)((Iterator)localObject3).next();
          localObject5 = (cfs.a)((cug)localObject1).get(((cfs.a)localObject4).k);
          ((cug)localObject1).remove(((cfs.a)localObject4).k);
          if ((((cfs.a)localObject5).o == ((cfs.a)localObject4).o) && (((cfs.a)localObject5).p == ((cfs.a)localObject4).p) && (((cfs.a)localObject5).q == ((cfs.a)localObject4).q) && (((cfs.a)localObject5).r == ((cfs.a)localObject4).r) && (((cfs.a)localObject5).n == ((cfs.a)localObject4).n)) {}
          for (i1 = 1;; i1 = 0)
          {
            if (i1 != 0) {
              break label823;
            }
            a((cfs.a)localObject4);
            break;
          }
        }
      }
      localObject1 = ((cug)localObject1).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject3 = (cfs.a)((Iterator)localObject1).next();
        this.g.add(localObject3);
      }
      if ((!this.g.isEmpty()) && (f2 == 0.0F))
      {
        localObject1 = new ctz(this.k, this.l);
        ((ctz)localObject1).a(0, 0, this.k, 0, true);
        localObject2 = ((ArrayList)localObject2).iterator();
        while (((Iterator)localObject2).hasNext()) {
          ((ctz)localObject1).a((cfs.a)((Iterator)localObject2).next(), true);
        }
        localObject1 = new cfs.c((ctz)localObject1, b(this.g), true);
        ((cfs.c)localObject1).a();
        if (((cfs.c)localObject1).a == 0.0F)
        {
          localObject1 = ((cfs.c)localObject1).c.iterator();
          while (((Iterator)localObject1).hasNext())
          {
            localObject2 = (cfs.a)((Iterator)localObject1).next();
            ((cfs.a)localObject2).n = paramLong;
            a((cfs.a)localObject2);
          }
          this.g.clear();
        }
      }
      return;
      label1040:
      localObject2 = localObject1;
      f3 = f2;
      f2 = f1;
      i6 = i1;
    }
  }
  
  private void a(cfs.a paramA)
  {
    this.d.clear();
    ContentValues localContentValues = this.d;
    localContentValues.put("screen", Long.valueOf(paramA.n));
    localContentValues.put("cellX", Integer.valueOf(paramA.o));
    localContentValues.put("cellY", Integer.valueOf(paramA.p));
    localContentValues.put("spanX", Integer.valueOf(paramA.q));
    localContentValues.put("spanY", Integer.valueOf(paramA.r));
    this.f.add(ContentProviderOperation.newUpdate(cgr.c.a(paramA.k)).withValues(this.d).build());
  }
  
  private void a(String paramString)
  {
    paramString = Intent.parseUri(paramString, 0);
    if (paramString.getComponent() != null) {
      b(paramString.getComponent().getPackageName());
    }
    while (paramString.getPackage() == null) {
      return;
    }
    b(paramString.getPackage());
  }
  
  static boolean a(Context paramContext)
  {
    hbx localHbx = hbx.a(cfx.a);
    Object localObject4 = cfu.a().o;
    String str = b(((bez)localObject4).g, ((bez)localObject4).f);
    Object localObject3 = localHbx.a("migration_src_workspace_size", str);
    if (str.equals(localObject3)) {
      return true;
    }
    long l1 = System.currentTimeMillis();
    for (;;)
    {
      try
      {
        Object localObject1 = localHbx.a("desktop_backup_grid", "");
        if (((String)localObject1).isEmpty())
        {
          localHbx.b("desktop_backup_grid", (String)localObject3);
          paramContext.getContentResolver().call(cgr.c.a, "backup_desktop", (String)localObject3, null);
        }
        if (str.equals(localObject1))
        {
          localHbx.d("desktop_backup_grid");
          paramContext.getContentResolver().call(cgr.c.a, "restore_to_desktop_backup", str, null);
          return true;
        }
        localObject1 = b(paramContext);
        localObject4 = new Point(((bez)localObject4).g, ((bez)localObject4).f);
        localObject3 = ((String)localObject3).split(",");
        localObject3 = new Point(Integer.parseInt(localObject3[0]), Integer.parseInt(localObject3[1]));
        if (!new cfs.b((HashSet)localObject1, paramContext).a((Point)localObject3, (Point)localObject4)) {
          break label428;
        }
        i1 = 1;
        if (i1 != 0)
        {
          paramContext = paramContext.getContentResolver().query(cgr.c.a, null, null, null, null);
          if (paramContext == null) {
            break label422;
          }
        }
        new StringBuilder("Workspace migration completed in ").append(System.currentTimeMillis() - l1);
      }
      catch (Exception paramContext)
      {
        paramContext = paramContext;
        new StringBuilder("Error during grid migration: ").append(paramContext);
        return false;
      }
      finally
      {
        new StringBuilder("Workspace migration completed in ").append(System.currentTimeMillis() - l1);
        localHbx.b("migration_src_workspace_size", str);
      }
      localHbx.b("migration_src_workspace_size", str);
      return true;
      label422:
      boolean bool = false;
      continue;
      label428:
      int i1 = 0;
    }
  }
  
  private static String b(int paramInt1, int paramInt2)
  {
    return String.format(Locale.ENGLISH, "%d,%d", new Object[] { Integer.valueOf(paramInt1), Integer.valueOf(paramInt2) });
  }
  
  private ArrayList<cfs.a> b(long paramLong)
  {
    String str = "container = -100 AND screen = " + paramLong;
    Cursor localCursor = a(new String[] { "_id", "itemType", "cellX", "cellY", "spanX", "spanY", "intent", "appWidgetProvider", "appWidgetId" }, str);
    int i3 = localCursor.getColumnIndexOrThrow("_id");
    int i4 = localCursor.getColumnIndexOrThrow("itemType");
    int i5 = localCursor.getColumnIndexOrThrow("cellX");
    int i6 = localCursor.getColumnIndexOrThrow("cellY");
    int i7 = localCursor.getColumnIndexOrThrow("spanX");
    int i8 = localCursor.getColumnIndexOrThrow("spanY");
    int i9 = localCursor.getColumnIndexOrThrow("intent");
    int i10 = localCursor.getColumnIndexOrThrow("appWidgetProvider");
    int i11 = localCursor.getColumnIndexOrThrow("appWidgetId");
    ArrayList localArrayList = new ArrayList();
    cfs.a localA;
    try
    {
      while (localCursor.moveToNext())
      {
        localA = new cfs.a();
        localA.k = localCursor.getLong(i3);
        localA.l = localCursor.getInt(i4);
        localA.o = localCursor.getInt(i5);
        localA.p = localCursor.getInt(i6);
        localA.q = localCursor.getInt(i7);
        localA.r = localCursor.getInt(i8);
        localA.n = paramLong;
        try
        {
          switch (localA.l)
          {
          case 3: 
            throw new Exception("Invalid item type");
          }
        }
        catch (Exception localException)
        {
          new StringBuilder("Removing item ").append(localA.k).append(", exception: ").append(localException);
          this.e.add(Long.valueOf(localA.k));
        }
      }
      a(localCursor.getString(i9));
    }
    finally
    {
      localCursor.close();
    }
    if (localA.l == 0) {}
    for (float f1 = 0.8F;; f1 = 1.0F)
    {
      localA.a = f1;
      label473:
      localArrayList.add(localA);
      break;
    }
    localA.a = Math.max(2.0F, 0.6F * localA.q * localA.r);
    Object localObject2 = ComponentName.unflattenFromString(localCursor.getString(i10));
    int i1;
    if (localA.l == 4)
    {
      b(((ComponentName)localObject2).getPackageName());
      i1 = localCursor.getInt(i11);
      localObject2 = avt.a(this.b).a(i1);
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
          i1 = ((Point)localObject2).x;
          label603:
          localA.s = i1;
          if (((Point)localObject2).y <= 0) {
            break label737;
          }
          i1 = ((Point)localObject2).y;
          label625:
          localA.t = i1;
          label632:
          if ((localA.s <= this.k) && (localA.t <= this.l)) {
            break label787;
          }
          throw new Exception("Widget can't be resized down to fit the grid");
        }
      }
      else
      {
        localObject2 = LauncherAppWidgetProviderInfo.a((AppWidgetProviderInfo)localObject2);
        break label844;
        label677:
        if ((((LauncherAppWidgetProviderInfo)localObject2).resizeMode & 0x1) == 0) {
          break label855;
        }
        i1 = ((LauncherAppWidgetProviderInfo)localObject2).d;
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
    for (int i2 = ((LauncherAppWidgetProviderInfo)localObject2).e;; i2 = -1)
    {
      localObject2 = new Point(i1, i2);
      break label583;
      i1 = localA.q;
      break label603;
      i1 = localA.r;
      break label625;
      localA.t = 2;
      localA.s = 2;
      break label632;
      Class.forName(((ComponentName)localObject2).getClassName()).newInstance();
      localA.s = 4;
      localA.t = 1;
      break label632;
      break label473;
      i1 = c(localA.k);
      if (i1 == 0) {
        throw new Exception("Folder is empty");
      }
      localA.a = (i1 * 0.5F);
      break label473;
      localCursor.close();
      return localArrayList;
      break;
      if (localObject2 != null) {
        break label677;
      }
      localObject2 = null;
      break label583;
      i1 = -1;
      break label694;
    }
  }
  
  private static ArrayList<cfs.a> b(ArrayList<cfs.a> paramArrayList)
  {
    ArrayList localArrayList = new ArrayList(paramArrayList.size());
    paramArrayList = paramArrayList.iterator();
    while (paramArrayList.hasNext())
    {
      cfs.a localA1 = (cfs.a)paramArrayList.next();
      cfs.a localA2 = new cfs.a();
      localA2.a(localA1);
      localA2.a = localA1.a;
      localA2.s = localA1.s;
      localA2.t = localA1.t;
      localArrayList.add(localA2);
    }
    return localArrayList;
  }
  
  private static HashSet<String> b(Context paramContext)
  {
    HashSet localHashSet = new HashSet();
    Iterator localIterator = paramContext.getPackageManager().getInstalledPackages(8192).iterator();
    while (localIterator.hasNext()) {
      localHashSet.add(((PackageInfo)localIterator.next()).packageName);
    }
    localHashSet.addAll(awd.a(paramContext).a().keySet());
    return localHashSet;
  }
  
  private void b(String paramString)
  {
    if (!this.h.contains(paramString)) {
      throw new Exception("Package not available");
    }
  }
  
  private int c(long paramLong)
  {
    int i1 = 0;
    Object localObject1 = "container = " + paramLong;
    localObject1 = a(new String[] { "_id", "intent" }, (String)localObject1);
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
          a(((Cursor)localObject1).getString(1));
          i1 += 1;
        }
        catch (Exception localException)
        {
          this.e.add(Long.valueOf(((Cursor)localObject1).getLong(0)));
        }
      }
    }
    finally
    {
      ((Cursor)localObject1).close();
    }
    return i1;
  }
  
  final boolean a()
  {
    ArrayList localArrayList = cfy.b(this.b);
    if (localArrayList.isEmpty()) {
      throw new Exception("Unable to get workspace screens");
    }
    Object localObject1 = localArrayList.iterator();
    while (((Iterator)localObject1).hasNext()) {
      a(((Long)((Iterator)localObject1).next()).longValue());
    }
    if (!this.g.isEmpty())
    {
      localObject1 = new cug();
      Object localObject2 = this.g.iterator();
      cfs.a localA;
      while (((Iterator)localObject2).hasNext())
      {
        localA = (cfs.a)((Iterator)localObject2).next();
        ((cug)localObject1).put(localA.k, localA);
      }
      do
      {
        localObject2 = new cfs.c(new ctz(this.k, this.l), b(this.g), true);
        ((cfs.c)localObject2).a();
        if (((cfs.c)localObject2).c.size() > 0)
        {
          long l1 = LauncherProvider.c();
          localArrayList.add(Long.valueOf(l1));
          localObject2 = ((cfs.c)localObject2).c.iterator();
          while (((Iterator)localObject2).hasNext())
          {
            localA = (cfs.a)((Iterator)localObject2).next();
            if (!this.g.remove(((cug)localObject1).get(localA.k))) {
              throw new Exception("Unable to find matching items");
            }
            localA.n = l1;
            a(localA);
          }
        }
        throw new Exception("None of the items can be placed on an empty screen");
      } while (!this.g.isEmpty());
      localObject1 = cgr.e.a;
      this.f.add(ContentProviderOperation.newDelete((Uri)localObject1).build());
      int i2 = localArrayList.size();
      int i1 = 0;
      while (i1 < i2)
      {
        localObject2 = new ContentValues();
        ((ContentValues)localObject2).put("_id", Long.valueOf(((Long)localArrayList.get(i1)).longValue()));
        ((ContentValues)localObject2).put("screenRank", Integer.valueOf(i1));
        this.f.add(ContentProviderOperation.newInsert((Uri)localObject1).withValues((ContentValues)localObject2).build());
        i1 += 1;
      }
    }
    if (!this.f.isEmpty()) {
      this.b.getContentResolver().applyBatch("com.emoticon.screen.home.launcher.settings", this.f);
    }
    if (!this.e.isEmpty())
    {
      new StringBuilder("Removing items: ").append(TextUtils.join(", ", this.e));
      this.b.getContentResolver().delete(cgr.c.a, cvg.a("_id", this.e), null);
    }
    return (!this.f.isEmpty()) || (!this.e.isEmpty());
  }
  
  public static final class a
    extends cft
    implements Comparable<a>
  {
    float a;
    
    a() {}
  }
  
  public static final class b
  {
    private final HashSet<String> a;
    private final Context b;
    
    b(HashSet<String> paramHashSet, Context paramContext)
    {
      this.a = paramHashSet;
      this.b = paramContext;
    }
    
    final boolean a(Point paramPoint1, Point paramPoint2)
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
        if (!new cfs(this.b, cfu.a().o, this.a, paramPoint1, localPoint).a()) {
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
  
  final class c
  {
    float a = Float.MAX_VALUE;
    float b = Float.MAX_VALUE;
    ArrayList<cfs.a> c;
    private final ArrayList<cfs.a> e;
    private final ctz f;
    private final boolean g;
    private final int h;
    
    c(ArrayList<cfs.a> paramArrayList, int paramInt)
    {
      this(paramArrayList, paramInt, false);
    }
    
    c(ArrayList<cfs.a> paramArrayList, int paramInt)
    {
      this.f = paramArrayList;
      this.e = paramInt;
      boolean bool;
      this.g = bool;
      this.h = 0;
      Collections.sort(this.e);
    }
    
    private void a(int paramInt, float paramFloat1, float paramFloat2, ArrayList<cfs.a> paramArrayList)
    {
      if ((paramFloat1 >= this.a) || ((paramFloat1 == this.a) && (paramFloat2 >= this.b))) {
        label27:
        return;
      }
      if (paramInt >= this.e.size())
      {
        this.a = paramFloat1;
        this.b = paramFloat2;
        this.c = cfs.a(paramArrayList);
        return;
      }
      cfs.a localA = (cfs.a)this.e.get(paramInt);
      int i2 = localA.o;
      int i3 = localA.p;
      ArrayList localArrayList = new ArrayList(paramArrayList.size() + 1);
      localArrayList.addAll(paramArrayList);
      localArrayList.add(localA);
      int k;
      int m;
      int i;
      label156:
      int j;
      if ((localA.q > 1) || (localA.r > 1))
      {
        k = localA.q;
        m = localA.r;
        i = this.h;
        if (i < cfs.a(cfs.this))
        {
          j = 0;
          label171:
          if (j < cfs.b(cfs.this))
          {
            if (j == i2) {
              break label1067;
            }
            localA.o = j;
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
          localA.p = i;
          f2 = f1 + 1.0F;
        }
        f1 = f2;
        if (this.g) {
          f1 = paramFloat2;
        }
        if (this.f.a(j, i, k, m))
        {
          this.f.a(localA, true);
          a(paramInt + 1, paramFloat1, f1, localArrayList);
          this.f.a(localA, false);
        }
        if ((k > localA.s) && (this.f.a(j, i, k - 1, m)))
        {
          localA.q -= 1;
          this.f.a(localA, true);
          a(paramInt + 1, paramFloat1, 1.0F + f1, localArrayList);
          this.f.a(localA, false);
          localA.q += 1;
        }
        if ((m > localA.t) && (this.f.a(j, i, k, m - 1)))
        {
          localA.r -= 1;
          this.f.a(localA, true);
          a(paramInt + 1, paramFloat1, 1.0F + f1, localArrayList);
          this.f.a(localA, false);
          localA.r += 1;
        }
        if ((m > localA.t) && (k > localA.s) && (this.f.a(j, i, k - 1, m - 1)))
        {
          localA.q -= 1;
          localA.r -= 1;
          this.f.a(localA, true);
          a(paramInt + 1, paramFloat1, f1 + 2.0F, localArrayList);
          this.f.a(localA, false);
          localA.q += 1;
          localA.r += 1;
        }
        localA.o = i2;
        localA.p = i3;
        j += 1;
        break label171;
        i += 1;
        break label156;
        paramInt += 1;
        paramFloat1 += localA.a;
        break;
        int n = Integer.MAX_VALUE;
        m = Integer.MAX_VALUE;
        k = Integer.MAX_VALUE;
        i = this.h;
        int i1;
        if (i < cfs.a(cfs.this))
        {
          j = 0;
          if (j < cfs.b(cfs.this))
          {
            if (this.f.a[j][i] != 0) {
              break label1052;
            }
            if (this.g)
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
          i1 = (localA.o - j) * (localA.o - j) + (localA.p - i) * (localA.p - i);
          break label708;
          i += 1;
          break label655;
          if ((m < cfs.b(cfs.this)) && (k < cfs.a(cfs.this)))
          {
            if (m == i2) {
              break label1046;
            }
            localA.o = m;
          }
          for (f1 = 1.0F + paramFloat2;; f1 = paramFloat2)
          {
            f2 = f1;
            if (k != i3)
            {
              localA.p = k;
              f2 = f1 + 1.0F;
            }
            f1 = f2;
            if (this.g) {
              f1 = paramFloat2;
            }
            this.f.a(localA, true);
            a(paramInt + 1, paramFloat1, f1, localArrayList);
            this.f.a(localA, false);
            localA.o = i2;
            localA.p = i3;
            if ((paramInt + 1 >= this.e.size()) || (((cfs.a)this.e.get(paramInt + 1)).a < localA.a) || (this.g)) {
              break label27;
            }
            paramInt += 1;
            paramFloat1 += localA.a;
            break;
            paramInt += 1;
            while (paramInt < this.e.size())
            {
              paramFloat1 += ((cfs.a)this.e.get(paramInt)).a;
              paramInt += 1;
            }
            paramInt = this.e.size();
            paramFloat1 += localA.a;
            break;
          }
          i1 = n;
          n = k;
        }
      }
    }
    
    public final void a()
    {
      a(0, 0.0F, 0.0F, new ArrayList());
    }
  }
}
