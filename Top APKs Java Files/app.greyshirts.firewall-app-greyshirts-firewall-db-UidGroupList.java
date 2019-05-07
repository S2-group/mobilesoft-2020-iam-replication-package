package app.greyshirts.firewall.db;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseArray;
import app.greyshirts.firewall.app.Filter.Profile;
import app.greyshirts.firewall.app.NonPkgApp;
import app.greyshirts.firewall.app.PackageNames;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class UidGroupList
{
  private static final String[] COLUMNS = { "groupName", "leaderPkgName", "sqrIn", "install", "hasAllow0", "hasDeny0", "policy0", "hasAllow1", "hasDeny1", "policy1", "type0", "type1", "leaderAppName", "serverStr", "serverStrType", "serverPort", "createdData", "filterRuleId", "_id" };
  private static List<PackageCacheEntry> packageCache = null;
  private static final Object packageCacheLock = new Object();
  private final ArrayList<UidGroupEntry> allGroups = new ArrayList();
  private final Map<String, UidGroupEntry> mapPkg2Group = new HashMap();
  private final SparseArray<UidGroupEntry> mapUid2Group = new SparseArray();
  
  public UidGroupList() {}
  
  /* Error */
  public static void clearPackageCache()
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 34	app/greyshirts/firewall/db/UidGroupList:packageCacheLock	Ljava/lang/Object;
    //   6: astore_0
    //   7: aload_0
    //   8: monitorenter
    //   9: aconst_null
    //   10: putstatic 29	app/greyshirts/firewall/db/UidGroupList:packageCache	Ljava/util/List;
    //   13: aload_0
    //   14: monitorexit
    //   15: ldc 2
    //   17: monitorexit
    //   18: return
    //   19: astore_1
    //   20: aload_0
    //   21: monitorexit
    //   22: aload_1
    //   23: athrow
    //   24: astore_0
    //   25: ldc 2
    //   27: monitorexit
    //   28: aload_0
    //   29: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   24	5	0	localObject2	Object
    //   19	4	1	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   9	15	19	finally
    //   20	22	19	finally
    //   3	9	24	finally
    //   22	24	24	finally
  }
  
  private void fillCustomFilterColumnsAndAddRows(MatrixCursor paramMatrixCursor, UidGroupEntry paramUidGroupEntry, Object[] paramArrayOfObject)
  {
    Iterator localIterator = paramUidGroupEntry.getCustomFilters().iterator();
    while (localIterator.hasNext())
    {
      UidGroupEntry.FilterEntry localFilterEntry = (UidGroupEntry.FilterEntry)localIterator.next();
      paramArrayOfObject[10] = Integer.valueOf(localFilterEntry.filterType0);
      paramArrayOfObject[11] = Integer.valueOf(localFilterEntry.filterType1);
      paramArrayOfObject[12] = localFilterEntry.leaderAppName;
      paramArrayOfObject[13] = localFilterEntry.serverStr;
      paramArrayOfObject[14] = Integer.valueOf(localFilterEntry.serverStrType);
      paramArrayOfObject[15] = Integer.valueOf(localFilterEntry.serverPort);
      paramArrayOfObject[16] = Long.valueOf(localFilterEntry.createDate);
      paramArrayOfObject[17] = Long.valueOf(localFilterEntry.id);
      paramArrayOfObject[18] = Long.valueOf(paramUidGroupEntry.uid << (int)(32L + localFilterEntry.id));
      paramMatrixCursor.addRow(paramArrayOfObject);
    }
  }
  
  private void fillPolicyColumns(UidGroupEntry paramUidGroupEntry, Object[] paramArrayOfObject)
  {
    int j = 1;
    paramArrayOfObject[0] = paramUidGroupEntry.getGroupName();
    paramArrayOfObject[1] = paramUidGroupEntry.getLeaderPkgName();
    paramArrayOfObject[2] = paramUidGroupEntry.getPkgs().getCommaJoinedString();
    if (isInstalled(paramUidGroupEntry))
    {
      paramArrayOfObject[3] = Integer.valueOf(0);
      if (!paramUidGroupEntry.hasAllowBeforeAll(Filter.Profile.PROFILE_WIFI)) {
        break label228;
      }
      i = 1;
      label54:
      paramArrayOfObject[4] = Integer.valueOf(i);
      if (!paramUidGroupEntry.hasDenyBeforeAll(Filter.Profile.PROFILE_WIFI)) {
        break label233;
      }
      i = 1;
      label73:
      paramArrayOfObject[5] = Integer.valueOf(i);
      paramArrayOfObject[6] = Integer.valueOf(paramUidGroupEntry.getPolicy(Filter.Profile.PROFILE_WIFI));
      if (!paramUidGroupEntry.hasAllowBeforeAll(Filter.Profile.PROFILE_MOBILE)) {
        break label238;
      }
      i = 1;
      label106:
      paramArrayOfObject[7] = Integer.valueOf(i);
      if (!paramUidGroupEntry.hasDenyBeforeAll(Filter.Profile.PROFILE_MOBILE)) {
        break label243;
      }
    }
    label228:
    label233:
    label238:
    label243:
    for (int i = j;; i = 0)
    {
      paramArrayOfObject[8] = Integer.valueOf(i);
      paramArrayOfObject[9] = Integer.valueOf(paramUidGroupEntry.getPolicy(Filter.Profile.PROFILE_MOBILE));
      paramArrayOfObject[10] = Integer.valueOf(128);
      paramArrayOfObject[11] = Integer.valueOf(128);
      paramArrayOfObject[12] = paramUidGroupEntry.getLeaderAppName();
      paramArrayOfObject[17] = null;
      paramArrayOfObject[16] = null;
      paramArrayOfObject[15] = null;
      paramArrayOfObject[14] = null;
      paramArrayOfObject[13] = null;
      paramArrayOfObject[18] = Long.valueOf(paramUidGroupEntry.uid << 32);
      return;
      paramArrayOfObject[3] = Integer.valueOf(1);
      break;
      i = 0;
      break label54;
      i = 0;
      break label73;
      i = 0;
      break label106;
    }
  }
  
  void createPackageCache(PackageManager paramPackageManager)
  {
    synchronized (packageCacheLock)
    {
      packageCache = new ArrayList();
      Object localObject2 = paramPackageManager.getInstalledPackages(4096);
      if (localObject2 != null)
      {
        localObject2 = ((List)localObject2).iterator();
        if (((Iterator)localObject2).hasNext())
        {
          PackageCacheEntry localPackageCacheEntry = new PackageCacheEntry((PackageInfo)((Iterator)localObject2).next(), paramPackageManager);
          packageCache.add(localPackageCacheEntry);
        }
      }
    }
  }
  
  public MatrixCursor getCombinedDataCursor(boolean paramBoolean)
  {
    Collections.sort(this.allGroups, UidGroupEntry.CASE_INSENSITIVE_COMPARATOR);
    MatrixCursor localMatrixCursor = new MatrixCursor(COLUMNS);
    Iterator localIterator = this.allGroups.iterator();
    while (localIterator.hasNext())
    {
      UidGroupEntry localUidGroupEntry = (UidGroupEntry)localIterator.next();
      Object localObject = localUidGroupEntry.getCustomFilters();
      if ((localUidGroupEntry.hasNetPerm()) || (!((List)localObject).isEmpty()) || (localUidGroupEntry.getPolicy(Filter.Profile.PROFILE_WIFI) != 0) || (localUidGroupEntry.getPolicy(Filter.Profile.PROFILE_MOBILE) != 0))
      {
        localObject = new Object[19];
        fillPolicyColumns(localUidGroupEntry, (Object[])localObject);
        localMatrixCursor.addRow((Object[])localObject);
        if (paramBoolean) {
          fillCustomFilterColumnsAndAddRows(localMatrixCursor, localUidGroupEntry, (Object[])localObject);
        }
      }
    }
    return localMatrixCursor;
  }
  
  public MatrixCursor getCustomFilterCursor(String paramString)
  {
    int j = 1;
    Object localObject = PackageNames.newInstanceFromCommaList(paramString);
    final Bundle localBundle = new Bundle();
    paramString = new MatrixCursor(COLUMNS)
    {
      public Bundle getExtras()
      {
        return localBundle;
      }
    };
    int k = ((PackageNames)localObject).length();
    int i = 0;
    UidGroupEntry localUidGroupEntry;
    for (;;)
    {
      if (i < k)
      {
        localUidGroupEntry = (UidGroupEntry)this.mapPkg2Group.get(((PackageNames)localObject).getAt(i));
        if (localUidGroupEntry == null)
        {
          i += 1;
        }
        else if (0 == 0)
        {
          localBundle.putString("groupName", localUidGroupEntry.getGroupName());
          localBundle.putString("leaderPkgName", localUidGroupEntry.getLeaderPkgName());
          localBundle.putString("sqrIn", localUidGroupEntry.getPkgs().getCommaJoinedString());
          if (!isInstalled(localUidGroupEntry)) {
            break label298;
          }
          i = 0;
          localBundle.putInt("install", i);
          if (!localUidGroupEntry.hasAllowBeforeAll(Filter.Profile.PROFILE_WIFI)) {
            break label303;
          }
          i = 1;
          label153:
          localBundle.putInt("hasAllow0", i);
          if (!localUidGroupEntry.hasDenyBeforeAll(Filter.Profile.PROFILE_WIFI)) {
            break label308;
          }
          i = 1;
          label174:
          localBundle.putInt("hasDeny0", i);
          localBundle.putInt("policy0", localUidGroupEntry.getPolicy(Filter.Profile.PROFILE_WIFI));
          if (!localUidGroupEntry.hasAllowBeforeAll(Filter.Profile.PROFILE_MOBILE)) {
            break label313;
          }
          i = 1;
          label210:
          localBundle.putInt("hasAllow1", i);
          if (!localUidGroupEntry.hasDenyBeforeAll(Filter.Profile.PROFILE_MOBILE)) {
            break label318;
          }
        }
      }
    }
    label298:
    label303:
    label308:
    label313:
    label318:
    for (i = j;; i = 0)
    {
      localBundle.putInt("hasDeny1", i);
      localBundle.putInt("policy1", localUidGroupEntry.getPolicy(Filter.Profile.PROFILE_MOBILE));
      localBundle.putString("leaderAppName", localUidGroupEntry.getLeaderAppName());
      localUidGroupEntry.getCustomFilters();
      localObject = new Object[19];
      fillPolicyColumns(localUidGroupEntry, (Object[])localObject);
      fillCustomFilterColumnsAndAddRows(paramString, localUidGroupEntry, (Object[])localObject);
      return paramString;
      i = 1;
      break;
      i = 0;
      break label153;
      i = 0;
      break label174;
      i = 0;
      break label210;
    }
  }
  
  public PackageNames getUninstalledPkgs()
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject = this.allGroups.iterator();
    while (((Iterator)localObject).hasNext())
    {
      UidGroupEntry localUidGroupEntry = (UidGroupEntry)((Iterator)localObject).next();
      if (!isInstalled(localUidGroupEntry)) {
        Collections.addAll(localArrayList, localUidGroupEntry.getPkgs().getPkgs());
      }
    }
    localObject = new String[localArrayList.size()];
    localArrayList.toArray((Object[])localObject);
    return PackageNames.newInstance((String[])localObject);
  }
  
  boolean isInstalled(UidGroupEntry paramUidGroupEntry)
  {
    if ((paramUidGroupEntry.getLeaderPkgName().startsWith("uid=")) && (paramUidGroupEntry.getGroupName().startsWith("Unknown"))) {}
    while (((paramUidGroupEntry.getLeaderPkgName().equals("mediaserver")) && (paramUidGroupEntry.getGroupName().equals("mediaserver"))) || (paramUidGroupEntry.getLeaderPkgName().startsWith("nonpkg.")) || (paramUidGroupEntry.uid != -1)) {
      return true;
    }
    return false;
  }
  
  public void loadFilters(DatabaseOpenHelper paramDatabaseOpenHelper)
  {
    paramDatabaseOpenHelper = paramDatabaseOpenHelper.getWritableDatabase();
    Object localObject2 = null;
    for (;;)
    {
      Cursor localCursor;
      Object localObject1;
      try
      {
        localCursor = paramDatabaseOpenHelper.query("filter", null, "pkgName<>'*'", null, null, null, "_id ASC");
        localObject2 = localCursor;
        int i = localCursor.getColumnIndexOrThrow("_id");
        localObject2 = localCursor;
        int j = localCursor.getColumnIndexOrThrow("serverIp");
        localObject2 = localCursor;
        int k = localCursor.getColumnIndexOrThrow("serverStrType");
        localObject2 = localCursor;
        int m = localCursor.getColumnIndexOrThrow("serverPort");
        localObject2 = localCursor;
        int n = localCursor.getColumnIndexOrThrow("protocol");
        localObject2 = localCursor;
        int i1 = localCursor.getColumnIndexOrThrow("createdData");
        localObject2 = localCursor;
        int i2 = localCursor.getColumnIndexOrThrow("type");
        localObject2 = localCursor;
        int i3 = localCursor.getColumnIndexOrThrow("type1");
        localObject2 = localCursor;
        int i4 = localCursor.getColumnIndexOrThrow("appName");
        localObject2 = localCursor;
        int i5 = localCursor.getColumnIndexOrThrow("pkgName");
        localObject2 = localCursor;
        int i6 = localCursor.getColumnIndexOrThrow("pkg2Name");
        localObject2 = localCursor;
        int i7 = localCursor.getColumnIndexOrThrow("pkg3Name");
        localObject2 = localCursor;
        int i8 = localCursor.getColumnIndexOrThrow("isPolicy");
        localObject2 = localCursor;
        localCursor.moveToFirst();
        localObject2 = localCursor;
        if (!localCursor.isAfterLast())
        {
          localObject2 = localCursor;
          String str1 = localCursor.getString(i5);
          localObject2 = localCursor;
          String str2 = localCursor.getString(i6);
          localObject2 = localCursor;
          String str3 = localCursor.getString(i7);
          localObject1 = null;
          localObject2 = localCursor;
          if (!TextUtils.isEmpty(str1))
          {
            localObject2 = localCursor;
            localObject1 = (UidGroupEntry)this.mapPkg2Group.get(str1);
          }
          paramDatabaseOpenHelper = (DatabaseOpenHelper)localObject1;
          if (localObject1 == null)
          {
            paramDatabaseOpenHelper = (DatabaseOpenHelper)localObject1;
            localObject2 = localCursor;
            if (!TextUtils.isEmpty(str2))
            {
              localObject2 = localCursor;
              paramDatabaseOpenHelper = (UidGroupEntry)this.mapPkg2Group.get(str2);
            }
          }
          localObject1 = paramDatabaseOpenHelper;
          if (paramDatabaseOpenHelper == null)
          {
            localObject1 = paramDatabaseOpenHelper;
            localObject2 = localCursor;
            if (!TextUtils.isEmpty(str3))
            {
              localObject2 = localCursor;
              localObject1 = (UidGroupEntry)this.mapPkg2Group.get(str3);
            }
          }
          paramDatabaseOpenHelper = (DatabaseOpenHelper)localObject1;
          if (localObject1 == null)
          {
            localObject2 = localCursor;
            localObject1 = new UidGroupEntry(-1);
            localObject2 = localCursor;
            this.allGroups.add(localObject1);
            localObject2 = localCursor;
            if (!TextUtils.isEmpty(str1))
            {
              localObject2 = localCursor;
              this.mapPkg2Group.put(str1, localObject1);
            }
            localObject2 = localCursor;
            if (!TextUtils.isEmpty(str2))
            {
              localObject2 = localCursor;
              this.mapPkg2Group.put(str2, localObject1);
            }
            paramDatabaseOpenHelper = (DatabaseOpenHelper)localObject1;
            localObject2 = localCursor;
            if (!TextUtils.isEmpty(str3))
            {
              localObject2 = localCursor;
              this.mapPkg2Group.put(str3, localObject1);
              paramDatabaseOpenHelper = (DatabaseOpenHelper)localObject1;
            }
          }
          localObject2 = localCursor;
          localObject1 = new UidGroupEntry.FilterEntry(localCursor.getLong(i), localCursor.getString(j), localCursor.getInt(k), localCursor.getInt(m), localCursor.getInt(n), localCursor.getLong(i1), localCursor.getInt(i2), localCursor.getInt(i3), localCursor.getString(i4), str1, str2, str3);
          localObject2 = localCursor;
          if (localCursor.getInt(i8) == 1)
          {
            localObject2 = localCursor;
            paramDatabaseOpenHelper.addPolicyFilter((UidGroupEntry.FilterEntry)localObject1);
            localObject2 = localCursor;
            localCursor.moveToNext();
          }
        }
        else
        {
          return;
        }
      }
      catch (Exception paramDatabaseOpenHelper)
      {
        if (localObject2 != null) {
          localObject2.close();
        }
      }
      localObject2 = localCursor;
      paramDatabaseOpenHelper.addCustomFilter((UidGroupEntry.FilterEntry)localObject1);
    }
  }
  
  public void loadInstalledApps(PackageManager paramPackageManager)
  {
    synchronized (packageCacheLock)
    {
      if (packageCache == null) {
        createPackageCache(paramPackageManager);
      }
      Iterator localIterator = packageCache.iterator();
      if (localIterator.hasNext())
      {
        PackageCacheEntry localPackageCacheEntry = (PackageCacheEntry)localIterator.next();
        int i = localPackageCacheEntry.uid;
        UidGroupEntry localUidGroupEntry = (UidGroupEntry)this.mapUid2Group.get(i);
        paramPackageManager = localUidGroupEntry;
        if (localUidGroupEntry == null)
        {
          paramPackageManager = new UidGroupEntry(i);
          this.allGroups.add(paramPackageManager);
          this.mapUid2Group.put(i, paramPackageManager);
        }
        this.mapPkg2Group.put(localPackageCacheEntry.packageName, paramPackageManager);
        paramPackageManager.addApp(new UidGroupEntry.AppEntry(localPackageCacheEntry.label, localPackageCacheEntry.packageName, localPackageCacheEntry.hasNetPerm));
      }
    }
  }
  
  public void loadNonPkgApps(Context paramContext)
  {
    paramContext = NonPkgApp.getIterator(paramContext);
    while (paramContext.hasNext())
    {
      NonPkgApp localNonPkgApp = (NonPkgApp)paramContext.next();
      if ((UidGroupEntry)this.mapPkg2Group.get(localNonPkgApp.getPkgName()) == null)
      {
        UidGroupEntry localUidGroupEntry = new UidGroupEntry(-1);
        this.allGroups.add(localUidGroupEntry);
        this.mapPkg2Group.put(localNonPkgApp.getPkgName(), localUidGroupEntry);
        localUidGroupEntry.addApp(new UidGroupEntry.AppEntry(localNonPkgApp.getAppName(), localNonPkgApp.getPkgName(), true));
      }
    }
  }
  
  static class PackageCacheEntry
  {
    final boolean hasNetPerm;
    final String label;
    final String packageName;
    final int uid;
    
    public PackageCacheEntry(PackageInfo paramPackageInfo, PackageManager paramPackageManager)
    {
      this.label = paramPackageInfo.applicationInfo.loadLabel(paramPackageManager).toString();
      this.packageName = paramPackageInfo.packageName;
      this.uid = paramPackageInfo.applicationInfo.uid;
      paramPackageInfo = paramPackageInfo.requestedPermissions;
      boolean bool2 = false;
      boolean bool1 = bool2;
      int j;
      int i;
      if (paramPackageInfo != null)
      {
        j = paramPackageInfo.length;
        i = 0;
      }
      for (;;)
      {
        bool1 = bool2;
        if (i < j)
        {
          if ("android.permission.INTERNET".equals(paramPackageInfo[i])) {
            bool1 = true;
          }
        }
        else
        {
          this.hasNetPerm = bool1;
          return;
        }
        i += 1;
      }
    }
  }
}
