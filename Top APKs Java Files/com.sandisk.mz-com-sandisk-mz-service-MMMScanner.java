package com.sandisk.mz.service;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import com.sandisk.mz.Utils;
import com.sandisk.mz.provider.ProviderConstants;
import com.sandisk.mz.reports.ReportManager;
import com.sandisk.mz.util.MountInfoManager;
import com.sandisk.mz.util.StoragePathConverter;
import com.sandisk.mz.util.StoragePathConverter.VolumeInfo;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class MMMScanner
{
  private static final String TAG = "MMMScanner";
  public static boolean bStopBackup;
  private Context mContext;
  private String mExternalPath = null;
  private HashMap<String, Integer> mFileCache;
  protected InfoStorage[][] mInfoStorage = (InfoStorage[][])Array.newInstance(InfoStorage.class, new int[] { 2, 8 });
  private String mInternalNandPath = null;
  private long[] mTotalFileCount = new long[2];
  private long[] mTotalStorageSize = new long[2];
  private long[] mUsedStorageSize = new long[2];
  
  public MMMScanner(Context paramContext)
  {
    this.mContext = paramContext;
    paramContext = StoragePathConverter.getInstance();
    this.mExternalPath = paramContext.getRealExternalStorageDirectoryPath();
    Log.i("MMMScanner", "mExternalPath = " + this.mExternalPath);
    int i = paramContext.getFixedStorageCount();
    if (i > 1)
    {
      paramContext = paramContext.getFixedStorage(i - 1);
      if (paramContext != null) {
        this.mInternalNandPath = paramContext.getPath();
      }
    }
    bStopBackup = false;
    Log.i("MMMScanner", "mInternalNandPath = " + this.mInternalNandPath);
  }
  
  private void cleanExternalRecord(String paramString)
  {
    if (paramString != null)
    {
      paramString = "fullpath LIKE '" + paramString + "%' ";
      com.sandisk.mz.provider.MMMProvider.mUpdateTimer = 0L;
      this.mContext.getContentResolver().delete(ProviderConstants.CONTENT_URI, paramString, null);
    }
  }
  
  private void getAppSummaryInfo(int paramInt)
  {
    Object localObject1 = new StringBuilder();
    this.mInfoStorage[paramInt][4] = new InfoStorage();
    if (paramInt == 0) {}
    for (;;)
    {
      try
      {
        ((StringBuilder)localObject1).append("category = 0");
        ((StringBuilder)localObject1).append(" AND ");
        ((StringBuilder)localObject1).append("MMMFileType = 4");
        str = ((StringBuilder)localObject1).toString();
        localObject1 = null;
      }
      catch (SQLiteException localSQLiteException)
      {
        String str;
        Cursor localCursor;
        Log.e("MMMScanner", localSQLiteException.getStackTrace().toString());
        return;
      }
      try
      {
        localCursor = this.mContext.getContentResolver().query(ProviderConstants.CONTENT_URI, new String[] { "count(*) AS _count" }, str, null, null);
        if (localCursor != null)
        {
          localObject1 = localCursor;
          if (localCursor.moveToFirst())
          {
            localObject1 = localCursor;
            this.mInfoStorage[paramInt][4].fileCount = localCursor.getLong(0);
          }
        }
        if (localCursor != null) {
          localCursor.close();
        }
        localObject1 = localCursor;
      }
      finally
      {
        if (localSQLiteException != null) {
          localSQLiteException.close();
        }
      }
      try
      {
        localCursor = this.mContext.getContentResolver().query(ProviderConstants.CONTENT_URI, new String[] { "sum(TotalBytes) AS _total" }, str, null, null);
        if (localCursor != null)
        {
          localObject1 = localCursor;
          if (localCursor.moveToFirst())
          {
            localObject1 = localCursor;
            this.mInfoStorage[paramInt][4].storageSize = localCursor.getLong(0);
          }
        }
        return;
      }
      finally
      {
        if (localSQLiteException == null) {
          break label252;
        }
        localSQLiteException.close();
      }
      ((StringBuilder)localObject1).append("category = 1");
    }
    label252:
  }
  
  private void getAppsInfo()
  {
    if (this.mContext == null) {
      return;
    }
    PackageManager localPackageManager;
    Iterator localIterator;
    if (this.mFileCache == null)
    {
      this.mFileCache = new HashMap();
      localPackageManager = this.mContext.getPackageManager();
      localIterator = localPackageManager.getInstalledPackages(8192).iterator();
    }
    for (;;)
    {
      if (!localIterator.hasNext()) {
        break label122;
      }
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      ApplicationInfo localApplicationInfo = localPackageInfo.applicationInfo;
      if ((localApplicationInfo.flags & 0x80) != 0)
      {
        insertApkInfo(localPackageManager, localPackageInfo);
        continue;
        this.mFileCache.clear();
        break;
      }
      if ((localApplicationInfo.flags & 0x1) == 0) {
        insertApkInfo(localPackageManager, localPackageInfo);
      }
    }
    label122:
    postScanforApp();
  }
  
  private InfoStorage getFileInfo(int paramInt1, int paramInt2)
  {
    return getFileInfo(this.mContext, paramInt1, paramInt2);
  }
  
  /* Error */
  public static InfoStorage getFileInfo(Context paramContext, int paramInt1, int paramInt2)
  {
    // Byte code:
    //   0: new 6	com/sandisk/mz/service/MMMScanner$InfoStorage
    //   3: dup
    //   4: invokespecial 133	com/sandisk/mz/service/MMMScanner$InfoStorage:<init>	()V
    //   7: astore 6
    //   9: new 64	java/lang/StringBuilder
    //   12: dup
    //   13: invokespecial 65	java/lang/StringBuilder:<init>	()V
    //   16: astore_3
    //   17: aload_3
    //   18: new 64	java/lang/StringBuilder
    //   21: dup
    //   22: invokespecial 65	java/lang/StringBuilder:<init>	()V
    //   25: ldc -18
    //   27: invokevirtual 71	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   30: iload_1
    //   31: invokevirtual 241	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   34: invokevirtual 74	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   37: invokevirtual 71	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   40: pop
    //   41: aload_3
    //   42: ldc -119
    //   44: invokevirtual 71	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   47: pop
    //   48: aload_3
    //   49: new 64	java/lang/StringBuilder
    //   52: dup
    //   53: invokespecial 65	java/lang/StringBuilder:<init>	()V
    //   56: ldc -13
    //   58: invokevirtual 71	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   61: iload_2
    //   62: invokevirtual 241	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   65: invokevirtual 74	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   68: invokevirtual 71	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   71: pop
    //   72: aload_3
    //   73: invokevirtual 74	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   76: astore 7
    //   78: getstatic 122	com/sandisk/mz/provider/ProviderConstants:CONTENT_URI	Landroid/net/Uri;
    //   81: astore_3
    //   82: iload_1
    //   83: iconst_1
    //   84: if_icmple +7 -> 91
    //   87: getstatic 246	com/sandisk/mz/provider/ProviderConstants:CONTENT_URI_CLOUD	Landroid/net/Uri;
    //   90: astore_3
    //   91: aconst_null
    //   92: astore 4
    //   94: aload_0
    //   95: invokevirtual 116	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   98: aload_3
    //   99: iconst_1
    //   100: anewarray 141	java/lang/String
    //   103: dup
    //   104: iconst_0
    //   105: ldc -113
    //   107: aastore
    //   108: aload 7
    //   110: aconst_null
    //   111: aconst_null
    //   112: invokevirtual 147	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   115: astore 5
    //   117: aload 5
    //   119: ifnull +34 -> 153
    //   122: aload 5
    //   124: astore 4
    //   126: aload 5
    //   128: invokeinterface 153 1 0
    //   133: ifeq +20 -> 153
    //   136: aload 5
    //   138: astore 4
    //   140: aload 6
    //   142: aload 5
    //   144: iconst_0
    //   145: invokeinterface 157 2 0
    //   150: putfield 160	com/sandisk/mz/service/MMMScanner$InfoStorage:fileCount	J
    //   153: aload 5
    //   155: ifnull +10 -> 165
    //   158: aload 5
    //   160: invokeinterface 163 1 0
    //   165: aload 5
    //   167: astore 4
    //   169: aload_0
    //   170: invokevirtual 116	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   173: aload_3
    //   174: iconst_1
    //   175: anewarray 141	java/lang/String
    //   178: dup
    //   179: iconst_0
    //   180: ldc -91
    //   182: aastore
    //   183: aload 7
    //   185: aconst_null
    //   186: aconst_null
    //   187: invokevirtual 147	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   190: astore_0
    //   191: aload_0
    //   192: ifnull +30 -> 222
    //   195: aload_0
    //   196: astore 4
    //   198: aload_0
    //   199: invokeinterface 153 1 0
    //   204: ifeq +18 -> 222
    //   207: aload_0
    //   208: astore 4
    //   210: aload 6
    //   212: aload_0
    //   213: iconst_0
    //   214: invokeinterface 157 2 0
    //   219: putfield 168	com/sandisk/mz/service/MMMScanner$InfoStorage:storageSize	J
    //   222: aload_0
    //   223: ifnull +59 -> 282
    //   226: aload_0
    //   227: invokeinterface 163 1 0
    //   232: aload 6
    //   234: areturn
    //   235: astore_0
    //   236: aload 4
    //   238: ifnull +10 -> 248
    //   241: aload 4
    //   243: invokeinterface 163 1 0
    //   248: aload_0
    //   249: athrow
    //   250: astore_0
    //   251: ldc 11
    //   253: aload_0
    //   254: invokevirtual 174	android/database/sqlite/SQLiteException:getStackTrace	()[Ljava/lang/StackTraceElement;
    //   257: invokevirtual 175	java/lang/Object:toString	()Ljava/lang/String;
    //   260: invokestatic 178	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   263: pop
    //   264: aload 6
    //   266: areturn
    //   267: astore_0
    //   268: aload 4
    //   270: ifnull +10 -> 280
    //   273: aload 4
    //   275: invokeinterface 163 1 0
    //   280: aload_0
    //   281: athrow
    //   282: aload 6
    //   284: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	285	0	paramContext	Context
    //   0	285	1	paramInt1	int
    //   0	285	2	paramInt2	int
    //   16	158	3	localObject1	Object
    //   92	182	4	localObject2	Object
    //   115	51	5	localCursor	Cursor
    //   7	276	6	localInfoStorage	InfoStorage
    //   76	108	7	str	String
    // Exception table:
    //   from	to	target	type
    //   94	117	235	finally
    //   126	136	235	finally
    //   140	153	235	finally
    //   17	82	250	android/database/sqlite/SQLiteException
    //   87	91	250	android/database/sqlite/SQLiteException
    //   158	165	250	android/database/sqlite/SQLiteException
    //   226	232	250	android/database/sqlite/SQLiteException
    //   241	248	250	android/database/sqlite/SQLiteException
    //   248	250	250	android/database/sqlite/SQLiteException
    //   273	280	250	android/database/sqlite/SQLiteException
    //   280	282	250	android/database/sqlite/SQLiteException
    //   169	191	267	finally
    //   198	207	267	finally
    //   210	222	267	finally
  }
  
  private void getOtherInfo(int paramInt)
  {
    Object localObject1 = new StringBuilder();
    this.mInfoStorage[paramInt][5] = new InfoStorage();
    if (paramInt == 0) {}
    for (;;)
    {
      try
      {
        ((StringBuilder)localObject1).append("category = 0");
        ((StringBuilder)localObject1).append(" AND ");
        ((StringBuilder)localObject1).append("MMMFileType = 5");
        str = ((StringBuilder)localObject1).toString();
        localObject1 = null;
      }
      catch (SQLiteException localSQLiteException)
      {
        String str;
        Cursor localCursor;
        Log.e("MMMScanner", localSQLiteException.getStackTrace().toString());
        return;
      }
      try
      {
        localCursor = this.mContext.getContentResolver().query(ProviderConstants.CONTENT_URI, new String[] { "count(*) AS _count" }, str, null, null);
        if (localCursor != null)
        {
          localObject1 = localCursor;
          if (localCursor.moveToFirst())
          {
            localObject1 = localCursor;
            this.mInfoStorage[paramInt][5].fileCount = localCursor.getLong(0);
          }
        }
        if (localCursor != null) {
          localCursor.close();
        }
        localObject1 = localCursor;
      }
      finally
      {
        if (localSQLiteException != null) {
          localSQLiteException.close();
        }
      }
      try
      {
        localCursor = this.mContext.getContentResolver().query(ProviderConstants.CONTENT_URI, new String[] { "sum(TotalBytes) AS _total" }, str, null, null);
        if (localCursor != null)
        {
          localObject1 = localCursor;
          if (localCursor.moveToFirst())
          {
            localObject1 = localCursor;
            this.mInfoStorage[paramInt][5].storageSize = localCursor.getLong(0);
          }
        }
        return;
      }
      finally
      {
        if (localSQLiteException == null) {
          break label252;
        }
        localSQLiteException.close();
      }
      ((StringBuilder)localObject1).append("category = 1");
    }
    label252:
  }
  
  private void insertApkInfo(PackageManager paramPackageManager, PackageInfo paramPackageInfo)
  {
    String str;
    int k;
    int j;
    long l1;
    long l2;
    Object localObject3;
    if (paramPackageInfo != null)
    {
      str = paramPackageInfo.packageName;
      k = 0;
      j = 0;
      localObject1 = new File(paramPackageInfo.applicationInfo.sourceDir);
      l1 = ((File)localObject1).lastModified();
      l2 = ((File)localObject1).length();
      localObject3 = null;
      localObject1 = null;
    }
    Object localObject2;
    try
    {
      Cursor localCursor = this.mContext.getContentResolver().query(ProviderConstants.CONTENT_URI, new String[] { "_id", "last_modif" }, "fullpath = ?", new String[] { str }, null);
      int i = k;
      localObject2 = localObject3;
      if (localCursor != null)
      {
        i = k;
        localObject2 = localObject3;
        localObject1 = localCursor;
        if (localCursor.getCount() > 0)
        {
          localObject1 = localCursor;
          localCursor.moveToFirst();
          localObject1 = localCursor;
          k = localCursor.getInt(0);
          i = j;
          localObject1 = localCursor;
          if (l1 == localCursor.getLong(1)) {
            i = 1;
          }
          localObject1 = localCursor;
          localObject2 = Uri.parse(ProviderConstants.CONTENT_URI + "/" + k);
        }
      }
      if (localCursor != null) {
        localCursor.close();
      }
      if (this.mFileCache != null) {
        this.mFileCache.put(str, Integer.valueOf(1));
      }
      if (i != 0) {
        return;
      }
    }
    finally
    {
      if (localObject1 != null) {
        ((Cursor)localObject1).close();
      }
    }
    Object localObject1 = new ContentValues();
    ((ContentValues)localObject1).put("fullpath", str);
    if ((this.mExternalPath != null) && (str.startsWith(this.mExternalPath + File.separator))) {
      ((ContentValues)localObject1).put("category", Integer.valueOf(1));
    }
    for (;;)
    {
      ((ContentValues)localObject1).put("MimeType", "apk");
      ((ContentValues)localObject1).put("MMMFileType", Integer.valueOf(4));
      ((ContentValues)localObject1).put("Title", paramPackageInfo.applicationInfo.loadLabel(paramPackageManager).toString());
      ((ContentValues)localObject1).put("TotalBytes", Long.valueOf(l2));
      ((ContentValues)localObject1).put("last_modif", Long.valueOf(l1));
      ((ContentValues)localObject1).put("Description", paramPackageInfo.applicationInfo.sourceDir);
      ((ContentValues)localObject1).put("lock", Integer.valueOf(1));
      if (localObject2 == null) {
        break;
      }
      this.mContext.getContentResolver().update((Uri)localObject2, (ContentValues)localObject1, null, null);
      return;
      if ((paramPackageInfo.applicationInfo.flags & 0x40000) != 0) {
        ((ContentValues)localObject1).put("category", Integer.valueOf(1));
      } else {
        ((ContentValues)localObject1).put("category", Integer.valueOf(0));
      }
    }
    this.mContext.getContentResolver().insert(ProviderConstants.CONTENT_URI, (ContentValues)localObject1);
  }
  
  private void insertDB(File paramFile)
  {
    if (Utils.insertFileInfoToDB(this.mContext, paramFile, this.mExternalPath, false, this.mFileCache) > 0) {}
  }
  
  public static void manfidScan()
  {
    try
    {
      Object localObject = new File("/sys/block/mmcblk1");
      if (((File)localObject).exists())
      {
        if (!((File)localObject).isDirectory()) {
          return;
        }
        localObject = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("cat /sys/block/mmcblk1/device/cid").getInputStream()));
        String str = ((BufferedReader)localObject).readLine();
        Log.d("MMMScanner", "CID of External Storage: " + str);
        ReportManager.getInstance().setSDCID(str);
        ReportManager.getInstance().setManufacturerID(true);
        ((BufferedReader)localObject).close();
        return;
      }
    }
    catch (IOException localIOException)
    {
      Log.e("MMMScanner", "Failure to read CID of External Storage");
      Log.e("MMMScanner", localIOException.getMessage());
      localIOException.printStackTrace();
    }
  }
  
  private void postScan()
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject1 = null;
    for (;;)
    {
      int i;
      try
      {
        Cursor localCursor = this.mContext.getContentResolver().query(ProviderConstants.CONTENT_URI, new String[] { "_id", "fullpath" }, null, null, null);
        if (localCursor != null)
        {
          localObject1 = localCursor;
          if (localCursor.moveToNext())
          {
            localObject1 = localCursor;
            boolean bool = bStopBackup;
            if (!bool) {
              continue;
            }
          }
        }
        if (localCursor != null) {
          localCursor.close();
        }
        this.mFileCache.clear();
        int j;
        if (localArrayList.size() > 0)
        {
          j = localArrayList.size() - 1;
          i = 0;
          if ((i <= j) && (!bStopBackup)) {}
        }
        else
        {
          return;
          localObject1 = localCursor;
          i = localCursor.getInt(0);
          localObject1 = localCursor;
          String str = localCursor.getString(1);
          if (str == null) {
            continue;
          }
          localObject1 = localCursor;
          if (this.mFileCache.containsKey(str)) {
            continue;
          }
          localObject1 = localCursor;
          localArrayList.add(Integer.valueOf(i));
          continue;
        }
        if (i != j) {
          break label219;
        }
      }
      finally
      {
        if (localObject1 != null) {
          ((Cursor)localObject1).close();
        }
      }
      com.sandisk.mz.provider.MMMProvider.mUpdateTimer = 0L;
      label219:
      int k = ((Integer)localArrayList.get(i)).intValue();
      localObject1 = ContentUris.withAppendedId(ProviderConstants.CONTENT_URI, k);
      this.mContext.getContentResolver().delete((Uri)localObject1, null, null);
      i += 1;
    }
  }
  
  private void postScanforApp()
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject1 = null;
    int i;
    try
    {
      Cursor localCursor = this.mContext.getContentResolver().query(ProviderConstants.CONTENT_URI, new String[] { "_id", "fullpath" }, "MMMFileType = 4 AND lock = 1", null, null);
      if (localCursor != null) {
        for (;;)
        {
          localObject1 = localCursor;
          if (!localCursor.moveToNext()) {
            break;
          }
          localObject1 = localCursor;
          i = localCursor.getInt(0);
          localObject1 = localCursor;
          String str = localCursor.getString(1);
          if (str != null)
          {
            localObject1 = localCursor;
            if (!this.mFileCache.containsKey(str))
            {
              localObject1 = localCursor;
              localArrayList.add(Integer.valueOf(i));
            }
          }
        }
      }
      if (localObject2 == null) {
        break label161;
      }
    }
    finally
    {
      if (localObject1 != null) {
        ((Cursor)localObject1).close();
      }
    }
    localObject2.close();
    label161:
    this.mFileCache.clear();
    if (localArrayList.size() > 0)
    {
      int j = localArrayList.size() - 1;
      i = 0;
      while (i <= j)
      {
        if (i == j) {
          com.sandisk.mz.provider.MMMProvider.mUpdateTimer = 0L;
        }
        int k = ((Integer)localArrayList.get(i)).intValue();
        localObject1 = ContentUris.withAppendedId(ProviderConstants.CONTENT_URI, k);
        this.mContext.getContentResolver().delete((Uri)localObject1, null, null);
        i += 1;
      }
    }
  }
  
  private void processDirectory()
  {
    String str;
    if (this.mFileCache == null)
    {
      this.mFileCache = new HashMap();
      Utils.initializeBulkValues();
      this.mTotalFileCount[0] = 0L;
      this.mTotalFileCount[1] = 0L;
      str = Environment.getExternalStorageDirectory().getAbsolutePath();
      if (str == null) {
        break label146;
      }
      recursiveScan(new File(str));
      if (!bStopBackup) {
        break label75;
      }
    }
    label75:
    label146:
    label185:
    do
    {
      do
      {
        do
        {
          return;
          this.mFileCache.clear();
          break;
          if ((this.mExternalPath == null) || (this.mExternalPath.startsWith(str + File.separator)) || (!MountInfoManager.isMounted(this.mExternalPath))) {
            break label146;
          }
          recursiveScan(new File(this.mExternalPath));
          manfidScan();
        } while (bStopBackup);
        if ((this.mInternalNandPath == null) || (this.mInternalNandPath.equalsIgnoreCase(str))) {
          break label185;
        }
        recursiveScan(new File(this.mInternalNandPath));
      } while (bStopBackup);
      Utils.flushBulkValues(this.mContext, this.mFileCache);
      Utils.clearBulkValues();
    } while (bStopBackup);
    postScan();
  }
  
  private void processUnmount(String paramString)
  {
    Log.i("MMMScanner", "processUnmount(" + paramString + ")");
    long l1 = System.currentTimeMillis();
    cleanExternalRecord(paramString);
    getTotalStorageInfo();
    int i = 0;
    while (i <= 3)
    {
      paramString = getFileInfo(0, i);
      if (paramString != null) {
        Utils.updateMemoryInfoByFile(this.mContext, 0, i, paramString.fileCount, paramString.storageSize);
      }
      i += 1;
    }
    Utils.sendMessage(this.mContext, 100L);
    long l2 = System.currentTimeMillis();
    Log.i("MMMScanner", "processUnmount()   total time: " + (l2 - l1) + "ms\n");
  }
  
  private void recursiveScan(File paramFile)
  {
    int i;
    Object localObject;
    if (paramFile != null)
    {
      paramFile = paramFile.listFiles();
      if (paramFile != null)
      {
        int j = paramFile.length;
        i = 0;
        if (i < j)
        {
          localObject = paramFile[i];
          if (!bStopBackup) {
            break label35;
          }
        }
      }
    }
    return;
    label35:
    if (((File)localObject).isDirectory()) {
      if ((!((File)localObject).isHidden()) && (!((File)localObject).getName().startsWith("."))) {
        recursiveScan((File)localObject);
      }
    }
    for (;;)
    {
      i += 1;
      break;
      insertDB((File)localObject);
      if ((this.mExternalPath != null) && (((File)localObject).getAbsolutePath().startsWith(this.mExternalPath + File.separator)))
      {
        localObject = this.mTotalFileCount;
        localObject[1] += 1L;
      }
      else
      {
        localObject = this.mTotalFileCount;
        localObject[0] += 1L;
      }
    }
  }
  
  private void updateFileCount()
  {
    int i = 0;
    while (i <= 1)
    {
      getOtherInfo(i);
      i += 1;
    }
    Utils.updateMemoryInfo(this.mContext, 0, -1, null, -1L, -1L, this.mInfoStorage[0][0].fileCount, this.mInfoStorage[0][0].storageSize, this.mInfoStorage[0][2].fileCount, this.mInfoStorage[0][2].storageSize, this.mInfoStorage[0][1].fileCount, this.mInfoStorage[0][1].storageSize, this.mInfoStorage[0][3].fileCount, this.mInfoStorage[0][3].storageSize, this.mInfoStorage[0][4].fileCount, this.mInfoStorage[0][4].storageSize, this.mInfoStorage[0][5].fileCount, this.mInfoStorage[0][5].storageSize);
    Utils.updateMemoryInfo(this.mContext, 1, -1, null, -1L, -1L, this.mInfoStorage[1][0].fileCount, this.mInfoStorage[1][0].storageSize, this.mInfoStorage[1][2].fileCount, this.mInfoStorage[1][2].storageSize, this.mInfoStorage[1][1].fileCount, this.mInfoStorage[1][1].storageSize, this.mInfoStorage[1][3].fileCount, this.mInfoStorage[1][3].storageSize, this.mInfoStorage[1][4].fileCount, this.mInfoStorage[1][4].storageSize, this.mInfoStorage[1][5].fileCount, this.mInfoStorage[1][5].storageSize);
  }
  
  private void updateFileCountExceptOther()
  {
    Utils.updateMemoryInfo(this.mContext, 0, -1, null, -1L, -1L, this.mInfoStorage[0][0].fileCount, this.mInfoStorage[0][0].storageSize, this.mInfoStorage[0][2].fileCount, this.mInfoStorage[0][2].storageSize, this.mInfoStorage[0][1].fileCount, this.mInfoStorage[0][1].storageSize, this.mInfoStorage[0][3].fileCount, this.mInfoStorage[0][3].storageSize, this.mInfoStorage[0][4].fileCount, this.mInfoStorage[0][4].storageSize, -1L, -1L);
    Utils.updateMemoryInfo(this.mContext, 1, -1, null, -1L, -1L, this.mInfoStorage[1][0].fileCount, this.mInfoStorage[1][0].storageSize, this.mInfoStorage[1][2].fileCount, this.mInfoStorage[1][2].storageSize, this.mInfoStorage[1][1].fileCount, this.mInfoStorage[1][1].storageSize, this.mInfoStorage[1][3].fileCount, this.mInfoStorage[1][3].storageSize, this.mInfoStorage[1][4].fileCount, this.mInfoStorage[1][4].storageSize, -1L, -1L);
  }
  
  public void cleanExternal(String paramString)
  {
    Log.i("MMMScanner", "cleanExternal(" + paramString + ")");
    Log.i("MMMScanner", "mExternalPath = " + this.mExternalPath);
    if ((this.mExternalPath != null) && (paramString != null) && (this.mExternalPath.compareToIgnoreCase(paramString) == 0)) {}
    do
    {
      try
      {
        long l1 = System.currentTimeMillis();
        Utils.enableMemoryInfo(this.mContext, 1, 0);
        Utils.sendMessage(this.mContext, 100L);
        cleanExternalRecord(this.mExternalPath);
        Utils.sendMessage(this.mContext, 100L);
        long l2 = System.currentTimeMillis();
        Log.i("MMMScanner", "cleanExternal()   total time: " + (l2 - l1) + "ms\n");
        return;
      }
      catch (SQLException paramString)
      {
        Log.e("MMMScanner", "SQLException in HQMEScanner.cleanExternal()", paramString);
        return;
      }
      catch (UnsupportedOperationException paramString)
      {
        Log.e("MMMScanner", "UnsupportedOperationException in HQMEScanner.cleanExternal()", paramString);
        return;
      }
      if ((this.mExternalPath != null) && (paramString != null) && (paramString.startsWith(this.mExternalPath)))
      {
        processUnmount(paramString);
        return;
      }
    } while ((this.mInternalNandPath == null) || (paramString == null) || (!paramString.startsWith(this.mInternalNandPath)));
    processUnmount(paramString);
  }
  
  public void getTotalStorageInfo()
  {
    if (this.mContext == null) {
      return;
    }
    this.mUsedStorageSize[0] = 0L;
    this.mUsedStorageSize[1] = 0L;
    this.mTotalStorageSize[0] = 0L;
    this.mTotalStorageSize[1] = 0L;
    File localFile = Environment.getDataDirectory();
    if (this.mInternalNandPath != null)
    {
      localFile = new File(this.mInternalNandPath);
      this.mTotalStorageSize[0] = Utils.totalSpace(localFile);
      this.mUsedStorageSize[0] = Utils.usedSpace(localFile);
    }
    for (;;)
    {
      if (this.mExternalPath != null)
      {
        localFile = new File(this.mExternalPath);
        if (localFile.exists())
        {
          this.mTotalStorageSize[1] = Utils.totalSpace(localFile);
          this.mUsedStorageSize[1] = Utils.usedSpace(localFile);
        }
      }
      Utils.updateMemoryInfo(this.mContext, 0, 1, "Internal", this.mTotalStorageSize[0], this.mUsedStorageSize[0], -1L, -1L, -1L, -1L, -1L, -1L, -1L, -1L, -1L, -1L, -1L, -1L);
      if ((this.mExternalPath == null) || (!MountInfoManager.isMounted(this.mExternalPath))) {
        break;
      }
      Utils.updateMemoryInfo(this.mContext, 1, 1, "External", this.mTotalStorageSize[1], this.mUsedStorageSize[1], -1L, -1L, -1L, -1L, -1L, -1L, -1L, -1L, -1L, -1L, -1L, -1L);
      return;
      this.mTotalStorageSize[0] = Utils.totalSpace(localFile);
      this.mUsedStorageSize[0] = Utils.usedSpace(localFile);
    }
    Utils.enableMemoryInfo(this.mContext, 1, 0);
  }
  
  public void scanDirectories()
  {
    int i;
    int j;
    for (;;)
    {
      try
      {
        bStopBackup = false;
        l1 = System.currentTimeMillis();
        getTotalStorageInfo();
        Utils.sendMessage(this.mContext, 100L);
        if (bStopBackup) {
          return;
        }
        processDirectory();
        if (bStopBackup) {
          break;
        }
        i = 0;
      }
      catch (SQLException localSQLException)
      {
        long l1;
        Log.e("MMMScanner", "SQLException in HQMEScanner.scanDirectories()", localSQLException);
        return;
      }
      catch (UnsupportedOperationException localUnsupportedOperationException)
      {
        Log.e("MMMScanner", "UnsupportedOperationException in HQMEScanner.scanDirectories()", localUnsupportedOperationException);
      }
      if (j > 3) {
        break label204;
      }
      this.mInfoStorage[i][j] = getFileInfo(i, j);
      if (bStopBackup) {
        break;
      }
      j += 1;
    }
    label193:
    label204:
    label209:
    for (;;)
    {
      getAppsInfo();
      if (!bStopBackup)
      {
        i = 0;
        while (i <= 1)
        {
          getAppSummaryInfo(i);
          if (bStopBackup) {
            break label193;
          }
          i += 1;
        }
        updateFileCount();
        Utils.sendMessage(this.mContext, 100L);
        long l2 = System.currentTimeMillis();
        Log.w("MMMScanner", " scanDirectories()  total time: " + (l2 - l1) + "ms\n");
        return;
      }
      return;
      for (;;)
      {
        if (i > 1) {
          break label209;
        }
        j = 0;
        break;
        i += 1;
      }
    }
  }
  
  public void updateAppInfo()
  {
    int j;
    int i;
    for (;;)
    {
      try
      {
        l1 = System.currentTimeMillis();
        getTotalStorageInfo();
        if (!bStopBackup) {
          break label165;
        }
        return;
      }
      catch (SQLException localSQLException)
      {
        long l1;
        Log.e("MMMScanner", "SQLException in HQMEScanner.updateAppInfo()", localSQLException);
        return;
      }
      catch (UnsupportedOperationException localUnsupportedOperationException)
      {
        Log.e("MMMScanner", "UnsupportedOperationException in HQMEScanner.updateAppInfo()", localUnsupportedOperationException);
      }
      if (j > 3) {
        break label177;
      }
      this.mInfoStorage[i][j] = getFileInfo(i, j);
      if (bStopBackup) {
        break;
      }
      j += 1;
    }
    label164:
    label165:
    label177:
    label182:
    for (;;)
    {
      getAppsInfo();
      if (!bStopBackup)
      {
        i = 0;
        while (i <= 1)
        {
          getAppSummaryInfo(i);
          if (bStopBackup) {
            break label164;
          }
          i += 1;
        }
        updateFileCountExceptOther();
        Utils.sendMessage(this.mContext, 100L);
        long l2 = System.currentTimeMillis();
        Log.i("MMMScanner", "updateAppInfo()   total time: " + (l2 - l1) + "ms\n");
        return;
      }
      return;
      i = 0;
      for (;;)
      {
        if (i > 1) {
          break label182;
        }
        j = 0;
        break;
        i += 1;
      }
    }
  }
  
  public static class InfoStorage
  {
    public long fileCount;
    public long storageSize;
    
    public InfoStorage() {}
  }
}
