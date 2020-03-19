package anagog.pd.internal;

import android.content.ContentValues;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Build.VERSION;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public final class bc
{
  private static long a = 5967798878615811901L;
  private static char[] c = { 97, -18611, 28170, 9668, -9045, -27579, 19215, 713, -18044, 28992, -12496, 30730, -24232, -5504, 5058, 23297, -31728, -12841, 95, -18604, 28190, 31044, -12696, 5933, 23784, -23135, -4766, 12863, 31680, -16206, 2160, 20795, -22834, 16507, -2196, 11809, 26091, -25415, -11148, -4546, 22788, -32682, -13426, 13004, 31247, 17244, -2970, 11580, 26358, -24672, -10392, 2103, 16891, -1351, -22411, 8039, -14789, -29193, 29875, 15435, -7373, -21781, 4527, -9865, -32707, 30656, 16059, -6589, -21240, 5317, 24550, -5898, 12707, 31337, -31989, -13333, 5280, 23904, -6602, 12010, 30620, -32676, -14053, 4566, 83, -18615, 28181, 9671, -9084, -27564, 19210, -21155, 6735, -15597, -30497, 29083, 14702, -6640, -20540, 5270, -9123, -31475, 29421, 15272, -7316, -22507, 7941, -14768, -29286, 29929, 15373, -7357, -21865, 4562, -9976, -32686, 30633, 16107, 67, -18606, 28175, 9689, -9088, -27564, 19228, 95, -18604, 28190, 9610, -9004, -27634, 17071, -2679, 11465, 26371, -24995, -10593, 2517, 16413, -1186, 13210, 27356, -2198, 16449, -26341, -11575, 11258, 25419, -17407, -2617, 20106, -31154, -8340, 10488, 25012, -18153, -3523, 19429, -31561, -9104, 5424, 28410, -22582, -178, 18528, -32475, -9499, 4996, 27496, -23520, -544, 13995, -29075 };
  private static final String[] d = { c(45, '䌕', 9).intern() };
  private static int e = 0;
  private static int h = 1;
  private c b;
  
  public bc(c paramC)
  {
    this.b = paramC;
  }
  
  private void a(ArrayList<String> paramArrayList, PackageManager paramPackageManager)
  {
    if (paramArrayList.isEmpty()) {
      return;
    }
    int j = Build.VERSION.SDK_INT;
    SQLiteDatabase localSQLiteDatabase = this.b.getWritableDatabase();
    localSQLiteDatabase.beginTransaction();
    ContentValues localContentValues = new ContentValues();
    for (;;)
    {
      try
      {
        paramArrayList = paramArrayList.iterator();
        if (paramArrayList.hasNext()) {
          break label136;
        }
        i = 70;
        if (i != 3)
        {
          localSQLiteDatabase.setTransactionSuccessful();
          localSQLiteDatabase.endTransaction();
          return;
        }
        localObject = (String)paramArrayList.next();
      }
      finally
      {
        try
        {
          Object localObject = paramPackageManager.getPackageInfo((String)localObject, 0);
          b(j, localContentValues, (PackageInfo)localObject);
          localSQLiteDatabase.insert(c(0, '\000', 10).intern(), null, localContentValues);
        }
        catch (Throwable localThrowable) {}
        paramArrayList = finally;
        localSQLiteDatabase.endTransaction();
      }
      continue;
      label136:
      int i = 3;
    }
  }
  
  private ArrayList<String> b()
  {
    Object localObject1 = c(10, 53059, 8).intern();
    Object localObject2 = this.b.getReadableDatabase();
    label149:
    for (;;)
    {
      try
      {
        localObject2 = ((SQLiteDatabase)localObject2).query(c(0, '\000', 10).intern(), az.a, (String)localObject1, d, null, null, null);
        if (localObject2 != null) {
          i = 38;
        } else {
          i = 14;
        }
        if (i != 38)
        {
          i = h + 13;
          e = i % 128;
          return null;
        }
        localObject1 = new ArrayList(((Cursor)localObject2).getCount());
        int i = 62;
      }
      catch (SQLiteException localSQLiteException1)
      {
        try
        {
          if (((Cursor)localObject2).moveToNext()) {
            break label149;
          }
          i = 5;
          if (i != 62)
          {
            ((Cursor)localObject2).close();
            return localObject1;
          }
          ((ArrayList)localObject1).add(((Cursor)localObject2).getString(0));
          continue;
          return null;
        }
        catch (SQLiteException localSQLiteException2)
        {
          return localSQLiteException1;
        }
        localSQLiteException1 = localSQLiteException1;
      }
    }
  }
  
  private static void b(int paramInt, ContentValues paramContentValues, PackageInfo paramPackageInfo)
  {
    int i = paramPackageInfo.applicationInfo.flags;
    int j = 1;
    if ((i & 0x1) == 0) {
      i = 1;
    } else {
      i = 0;
    }
    boolean bool1;
    if (i != 1) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    if (paramInt < 12) {
      i = 63;
    } else {
      i = 12;
    }
    if (i == 12)
    {
      i = h + 125;
      e = i % 128;
      if ((paramPackageInfo.applicationInfo.flags & 0x200000) == 0) {
        i = 0;
      } else {
        i = 1;
      }
      if (i == 1) {}
    }
    else
    {
      bool2 = false;
      break label136;
    }
    i = h + 105;
    e = i % 128;
    if (i % 2 != 0) {}
    boolean bool2 = true;
    label136:
    if (paramInt < 9) {
      paramInt = j;
    } else {
      paramInt = 0;
    }
    long l1 = 0L;
    long l2;
    if (paramInt != 0)
    {
      l2 = 0L;
    }
    else
    {
      paramInt = h + 23;
      e = paramInt % 128;
      if (paramInt % 2 != 0) {}
      l1 = paramPackageInfo.firstInstallTime;
      l2 = paramPackageInfo.lastUpdateTime;
    }
    paramContentValues.put(c(18, '\000', 3).intern(), Integer.valueOf(paramPackageInfo.packageName.hashCode()));
    paramContentValues.put(c(21, '礴', 12).intern(), paramPackageInfo.packageName);
    paramContentValues.put(c(33, '䀨', 6).intern(), Boolean.valueOf(bool1));
    paramContentValues.put(c(39, 61005, 6).intern(), c(45, '䌕', 9).intern());
    paramContentValues.put(c(54, 43059, 16).intern(), Long.valueOf(l1));
    paramContentValues.put(c(70, '循', 14).intern(), Long.valueOf(l2));
    paramContentValues.put(c(84, '\000', 7).intern(), Boolean.valueOf(bool2));
    paramContentValues.put(c(91, 44315, 14).intern(), Integer.valueOf(0));
    paramContentValues.put(c(105, 43097, 13).intern(), Integer.valueOf(0));
    paramContentValues.put(c(118, '\000', 7).intern(), Integer.valueOf(0));
  }
  
  private void b(ArrayList<Integer> paramArrayList)
  {
    if (paramArrayList.isEmpty()) {
      return;
    }
    String str = c(125, '\000', 6).intern();
    SQLiteDatabase localSQLiteDatabase = this.b.getWritableDatabase();
    localSQLiteDatabase.beginTransaction();
    try
    {
      ContentValues localContentValues = new ContentValues();
      paramArrayList = paramArrayList.iterator();
      for (;;)
      {
        boolean bool = paramArrayList.hasNext();
        if (!bool) {
          i = 58;
        } else {
          i = 49;
        }
        if (i == 58) {
          break;
        }
        int i = e + 103;
        h = i % 128;
        Integer localInteger = (Integer)paramArrayList.next();
        localContentValues.put(c(39, 61005, 6).intern(), c(131, '䋺', 11).intern());
        localSQLiteDatabase.update(c(0, '\000', 10).intern(), localContentValues, str, new String[] { String.valueOf(localInteger) });
      }
      localSQLiteDatabase.setTransactionSuccessful();
      return;
    }
    finally
    {
      localSQLiteDatabase.endTransaction();
    }
  }
  
  private void b(List<PackageInfo> paramList)
  {
    int j = Build.VERSION.SDK_INT;
    SQLiteDatabase localSQLiteDatabase = this.b.getWritableDatabase();
    localSQLiteDatabase.beginTransaction();
    ContentValues localContentValues = new ContentValues();
    for (;;)
    {
      try
      {
        paramList = paramList.iterator();
        if (!paramList.hasNext())
        {
          i = 58;
          if (i != 58)
          {
            b(j, localContentValues, (PackageInfo)paramList.next());
            localSQLiteDatabase.insert(c(0, '\000', 10).intern(), null, localContentValues);
            continue;
          }
          localSQLiteDatabase.setTransactionSuccessful();
          return;
        }
      }
      finally
      {
        localSQLiteDatabase.endTransaction();
      }
      int i = 9;
    }
  }
  
  private static String c(int paramInt1, char paramChar, int paramInt2)
  {
    char[] arrayOfChar = new char[paramInt2];
    int i = 0;
    int j;
    if (i >= paramInt2) {
      j = 0;
    } else {
      j = 1;
    }
    if (j != 0)
    {
      j = e + 11;
      h = j % 128;
      if (j % 2 != 0) {
        j = 1;
      } else {
        j = 0;
      }
      if (j != 1) {
        arrayOfChar[i] = ((char)(int)(c[(paramInt1 + i)] ^ i * a ^ paramChar));
      }
      for (;;)
      {
        i += 1;
        break;
        arrayOfChar[i] = ((char)(int)(c[(paramInt1 + i)] ^ i * a ^ paramChar));
      }
    }
    return new String(arrayOfChar);
  }
  
  public final void a()
  {
    SQLiteDatabase localSQLiteDatabase = this.b.getWritableDatabase();
    localSQLiteDatabase.execSQL(c(142, 63278, 31).intern());
    this.b.onCreate(localSQLiteDatabase);
  }
  
  public final Cursor c(String[] paramArrayOfString)
  {
    SQLiteDatabase localSQLiteDatabase = this.b.getReadableDatabase();
    try
    {
      paramArrayOfString = localSQLiteDatabase.query(c(0, '\000', 10).intern(), paramArrayOfString, null, null, null, null, null);
      return paramArrayOfString;
    }
    catch (SQLiteException paramArrayOfString)
    {
      for (;;) {}
    }
    return null;
  }
  
  public final ArrayList<Integer> d(PackageManager paramPackageManager)
  {
    try
    {
      Object localObject1 = paramPackageManager.getInstalledPackages(0);
      ArrayList localArrayList = new ArrayList(((List)localObject1).size());
      Object localObject2 = ((List)localObject1).iterator();
      while (((Iterator)localObject2).hasNext()) {
        localArrayList.add(((PackageInfo)((Iterator)localObject2).next()).packageName);
      }
      localObject2 = b();
      if ((localObject2 != null) && (!((ArrayList)localObject2).isEmpty()))
      {
        localObject1 = (ArrayList)localArrayList.clone();
        ((ArrayList)localObject1).removeAll((Collection)localObject2);
        ((ArrayList)localObject2).removeAll(localArrayList);
        a((ArrayList)localObject1, paramPackageManager);
        paramPackageManager = ay.a((Collection)localObject2);
        b(paramPackageManager);
        paramPackageManager.addAll(ay.a((Collection)localObject1));
        return paramPackageManager;
      }
      b((List)localObject1);
      return ay.a(localArrayList);
    }
    catch (Throwable paramPackageManager)
    {
      for (;;) {}
    }
    return null;
  }
  
  public final Cursor e(String[] paramArrayOfString, ArrayList<Integer> paramArrayList)
  {
    SQLiteDatabase localSQLiteDatabase = this.b.getReadableDatabase();
    try
    {
      paramArrayOfString = localSQLiteDatabase.query(c(0, '\000', 10).intern(), paramArrayOfString, ay.b(paramArrayList.size()), ay.c(paramArrayList), null, null, null);
      return paramArrayOfString;
    }
    catch (SQLiteException paramArrayOfString)
    {
      for (;;) {}
    }
    return null;
  }
}
