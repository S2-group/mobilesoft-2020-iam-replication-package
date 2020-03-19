package com.openfeint.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

public class SyncedStore
{
  private static final String FILENAME = "of_prefs";
  private static final String TAG = "DistributedPrefs";
  private Context mContext;
  private ReentrantReadWriteLock mLock;
  private HashMap<String, String> mMap;
  
  public SyncedStore(Context paramContext)
  {
    this.mContext = paramContext;
    this.mMap = new HashMap();
    this.mLock = new ReentrantReadWriteLock();
    load();
  }
  
  /* Error */
  private HashMap<String, String> mapFromStore(File paramFile)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 10
    //   3: aconst_null
    //   4: astore 11
    //   6: aconst_null
    //   7: astore 12
    //   9: aconst_null
    //   10: astore_2
    //   11: aconst_null
    //   12: astore 9
    //   14: aconst_null
    //   15: astore 5
    //   17: aconst_null
    //   18: astore 6
    //   20: aconst_null
    //   21: astore 7
    //   23: aconst_null
    //   24: astore_3
    //   25: aconst_null
    //   26: astore 8
    //   28: aconst_null
    //   29: astore 4
    //   31: new 61	java/io/FileInputStream
    //   34: dup
    //   35: aload_1
    //   36: invokespecial 64	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   39: astore_1
    //   40: new 66	java/io/ObjectInputStream
    //   43: dup
    //   44: aload_1
    //   45: invokespecial 69	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
    //   48: astore_2
    //   49: aload_2
    //   50: invokevirtual 73	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
    //   53: astore_3
    //   54: aload_3
    //   55: ifnull +48 -> 103
    //   58: aload_3
    //   59: instanceof 33
    //   62: ifeq +41 -> 103
    //   65: aload_3
    //   66: checkcast 33	java/util/HashMap
    //   69: astore_3
    //   70: aload_2
    //   71: ifnull +10 -> 81
    //   74: aload_2
    //   75: invokevirtual 76	java/io/ObjectInputStream:close	()V
    //   78: goto +388 -> 466
    //   81: aload_1
    //   82: ifnull +384 -> 466
    //   85: aload_1
    //   86: invokevirtual 79	java/io/InputStream:close	()V
    //   89: goto +377 -> 466
    //   92: astore_1
    //   93: ldc 17
    //   95: ldc 81
    //   97: invokestatic 87	com/openfeint/internal/OpenFeintInternal:log	(Ljava/lang/String;Ljava/lang/String;)V
    //   100: goto +366 -> 466
    //   103: aload_2
    //   104: ifnull +10 -> 114
    //   107: aload_2
    //   108: invokevirtual 76	java/io/ObjectInputStream:close	()V
    //   111: goto +357 -> 468
    //   114: aload_1
    //   115: ifnull +353 -> 468
    //   118: aload_1
    //   119: invokevirtual 79	java/io/InputStream:close	()V
    //   122: goto +346 -> 468
    //   125: astore_1
    //   126: ldc 17
    //   128: ldc 81
    //   130: invokestatic 87	com/openfeint/internal/OpenFeintInternal:log	(Ljava/lang/String;Ljava/lang/String;)V
    //   133: goto +335 -> 468
    //   136: astore_1
    //   137: aload 9
    //   139: astore_1
    //   140: aload_1
    //   141: astore_2
    //   142: aload 4
    //   144: astore_3
    //   145: ldc 17
    //   147: ldc 89
    //   149: invokestatic 87	com/openfeint/internal/OpenFeintInternal:log	(Ljava/lang/String;Ljava/lang/String;)V
    //   152: aload 4
    //   154: ifnull +22 -> 176
    //   157: aload 4
    //   159: invokevirtual 76	java/io/ObjectInputStream:close	()V
    //   162: goto +306 -> 468
    //   165: astore_1
    //   166: ldc 17
    //   168: ldc 81
    //   170: invokestatic 87	com/openfeint/internal/OpenFeintInternal:log	(Ljava/lang/String;Ljava/lang/String;)V
    //   173: goto +295 -> 468
    //   176: aload_1
    //   177: ifnull +291 -> 468
    //   180: aload_1
    //   181: invokevirtual 79	java/io/InputStream:close	()V
    //   184: goto +284 -> 468
    //   187: astore_1
    //   188: aload 5
    //   190: astore 4
    //   192: aload 10
    //   194: astore_1
    //   195: aload_1
    //   196: astore_2
    //   197: aload 4
    //   199: astore_3
    //   200: ldc 17
    //   202: ldc 91
    //   204: invokestatic 87	com/openfeint/internal/OpenFeintInternal:log	(Ljava/lang/String;Ljava/lang/String;)V
    //   207: aload 4
    //   209: ifnull +22 -> 231
    //   212: aload 4
    //   214: invokevirtual 76	java/io/ObjectInputStream:close	()V
    //   217: goto +251 -> 468
    //   220: astore_1
    //   221: ldc 17
    //   223: ldc 81
    //   225: invokestatic 87	com/openfeint/internal/OpenFeintInternal:log	(Ljava/lang/String;Ljava/lang/String;)V
    //   228: goto +240 -> 468
    //   231: aload_1
    //   232: ifnull +236 -> 468
    //   235: aload_1
    //   236: invokevirtual 79	java/io/InputStream:close	()V
    //   239: goto +229 -> 468
    //   242: astore_1
    //   243: aload 6
    //   245: astore 4
    //   247: aload 11
    //   249: astore_1
    //   250: aload_1
    //   251: astore_2
    //   252: aload 4
    //   254: astore_3
    //   255: ldc 17
    //   257: ldc 93
    //   259: invokestatic 87	com/openfeint/internal/OpenFeintInternal:log	(Ljava/lang/String;Ljava/lang/String;)V
    //   262: aload 4
    //   264: ifnull +22 -> 286
    //   267: aload 4
    //   269: invokevirtual 76	java/io/ObjectInputStream:close	()V
    //   272: goto +196 -> 468
    //   275: astore_1
    //   276: ldc 17
    //   278: ldc 81
    //   280: invokestatic 87	com/openfeint/internal/OpenFeintInternal:log	(Ljava/lang/String;Ljava/lang/String;)V
    //   283: goto +185 -> 468
    //   286: aload_1
    //   287: ifnull +181 -> 468
    //   290: aload_1
    //   291: invokevirtual 79	java/io/InputStream:close	()V
    //   294: goto +174 -> 468
    //   297: astore_1
    //   298: aload 7
    //   300: astore 4
    //   302: aload 12
    //   304: astore_1
    //   305: aload_1
    //   306: astore_2
    //   307: aload 4
    //   309: astore_3
    //   310: ldc 17
    //   312: ldc 95
    //   314: invokestatic 87	com/openfeint/internal/OpenFeintInternal:log	(Ljava/lang/String;Ljava/lang/String;)V
    //   317: aload 4
    //   319: ifnull +22 -> 341
    //   322: aload 4
    //   324: invokevirtual 76	java/io/ObjectInputStream:close	()V
    //   327: goto +141 -> 468
    //   330: astore_1
    //   331: ldc 17
    //   333: ldc 81
    //   335: invokestatic 87	com/openfeint/internal/OpenFeintInternal:log	(Ljava/lang/String;Ljava/lang/String;)V
    //   338: goto +130 -> 468
    //   341: aload_1
    //   342: ifnull +126 -> 468
    //   345: aload_1
    //   346: invokevirtual 79	java/io/InputStream:close	()V
    //   349: goto +119 -> 468
    //   352: astore_1
    //   353: aload_3
    //   354: ifnull +9 -> 363
    //   357: aload_3
    //   358: invokevirtual 76	java/io/ObjectInputStream:close	()V
    //   361: aload_1
    //   362: athrow
    //   363: aload_2
    //   364: ifnull -3 -> 361
    //   367: aload_2
    //   368: invokevirtual 79	java/io/InputStream:close	()V
    //   371: goto -10 -> 361
    //   374: astore_2
    //   375: ldc 17
    //   377: ldc 81
    //   379: invokestatic 87	com/openfeint/internal/OpenFeintInternal:log	(Ljava/lang/String;Ljava/lang/String;)V
    //   382: goto -21 -> 361
    //   385: astore 4
    //   387: aload_1
    //   388: astore_2
    //   389: aload 8
    //   391: astore_3
    //   392: aload 4
    //   394: astore_1
    //   395: goto -42 -> 353
    //   398: astore 4
    //   400: aload_2
    //   401: astore_3
    //   402: aload_1
    //   403: astore_2
    //   404: aload 4
    //   406: astore_1
    //   407: goto -54 -> 353
    //   410: astore_2
    //   411: aload 7
    //   413: astore 4
    //   415: goto -110 -> 305
    //   418: astore_3
    //   419: aload_2
    //   420: astore 4
    //   422: goto -117 -> 305
    //   425: astore_2
    //   426: aload 6
    //   428: astore 4
    //   430: goto -180 -> 250
    //   433: astore_3
    //   434: aload_2
    //   435: astore 4
    //   437: goto -187 -> 250
    //   440: astore_2
    //   441: aload 5
    //   443: astore 4
    //   445: goto -250 -> 195
    //   448: astore_3
    //   449: aload_2
    //   450: astore 4
    //   452: goto -257 -> 195
    //   455: astore_2
    //   456: goto -316 -> 140
    //   459: astore_3
    //   460: aload_2
    //   461: astore 4
    //   463: goto -323 -> 140
    //   466: aload_3
    //   467: areturn
    //   468: aconst_null
    //   469: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	470	0	this	SyncedStore
    //   0	470	1	paramFile	File
    //   10	358	2	localObject1	Object
    //   374	1	2	localIOException1	IOException
    //   388	16	2	localFile	File
    //   410	10	2	localClassNotFoundException1	ClassNotFoundException
    //   425	10	2	localIOException2	IOException
    //   440	10	2	localStreamCorruptedException1	java.io.StreamCorruptedException
    //   455	6	2	localFileNotFoundException1	java.io.FileNotFoundException
    //   24	378	3	localObject2	Object
    //   418	1	3	localClassNotFoundException2	ClassNotFoundException
    //   433	1	3	localIOException3	IOException
    //   448	1	3	localStreamCorruptedException2	java.io.StreamCorruptedException
    //   459	8	3	localFileNotFoundException2	java.io.FileNotFoundException
    //   29	294	4	localObject3	Object
    //   385	8	4	localObject4	Object
    //   398	7	4	localObject5	Object
    //   413	49	4	localObject6	Object
    //   15	427	5	localObject7	Object
    //   18	409	6	localObject8	Object
    //   21	391	7	localObject9	Object
    //   26	364	8	localObject10	Object
    //   12	126	9	localObject11	Object
    //   1	192	10	localObject12	Object
    //   4	244	11	localObject13	Object
    //   7	296	12	localObject14	Object
    // Exception table:
    //   from	to	target	type
    //   74	78	92	java/io/IOException
    //   85	89	92	java/io/IOException
    //   107	111	125	java/io/IOException
    //   118	122	125	java/io/IOException
    //   31	40	136	java/io/FileNotFoundException
    //   157	162	165	java/io/IOException
    //   180	184	165	java/io/IOException
    //   31	40	187	java/io/StreamCorruptedException
    //   212	217	220	java/io/IOException
    //   235	239	220	java/io/IOException
    //   31	40	242	java/io/IOException
    //   267	272	275	java/io/IOException
    //   290	294	275	java/io/IOException
    //   31	40	297	java/lang/ClassNotFoundException
    //   322	327	330	java/io/IOException
    //   345	349	330	java/io/IOException
    //   31	40	352	finally
    //   145	152	352	finally
    //   200	207	352	finally
    //   255	262	352	finally
    //   310	317	352	finally
    //   357	361	374	java/io/IOException
    //   367	371	374	java/io/IOException
    //   40	49	385	finally
    //   49	54	398	finally
    //   58	70	398	finally
    //   40	49	410	java/lang/ClassNotFoundException
    //   49	54	418	java/lang/ClassNotFoundException
    //   58	70	418	java/lang/ClassNotFoundException
    //   40	49	425	java/io/IOException
    //   49	54	433	java/io/IOException
    //   58	70	433	java/io/IOException
    //   40	49	440	java/io/StreamCorruptedException
    //   49	54	448	java/io/StreamCorruptedException
    //   58	70	448	java/io/StreamCorruptedException
    //   40	49	455	java/io/FileNotFoundException
    //   49	54	459	java/io/FileNotFoundException
    //   58	70	459	java/io/FileNotFoundException
  }
  
  Editor edit()
  {
    this.mLock.writeLock().lock();
    return new Editor();
  }
  
  public void load()
  {
    this.mMap = null;
    int m = 0;
    int n = 0;
    int k = 0;
    long l1 = System.currentTimeMillis();
    File localFile = this.mContext.getFileStreamPath("of_prefs");
    this.mLock.writeLock().lock();
    int i = n;
    try
    {
      Object localObject3 = this.mContext.getPackageManager().getInstalledApplications(0);
      String str = null;
      i = n;
      Iterator localIterator = ((List)localObject3).iterator();
      Object localObject1;
      do
      {
        localObject1 = str;
        i = n;
        if (!localIterator.hasNext()) {
          break;
        }
        i = n;
        localObject1 = (ApplicationInfo)localIterator.next();
        i = n;
      } while (!((ApplicationInfo)localObject1).packageName.equals(this.mContext.getPackageName()));
      i = n;
      str = localFile.getCanonicalPath();
      int j = m;
      if (localObject1 != null)
      {
        j = m;
        i = n;
        if (str.startsWith(((ApplicationInfo)localObject1).dataDir))
        {
          i = n;
          str = str.substring(((ApplicationInfo)localObject1).dataDir.length());
          i = n;
          localObject3 = ((List)localObject3).iterator();
          localObject1 = localFile;
          j = k;
          for (;;)
          {
            i = j;
            if (!((Iterator)localObject3).hasNext()) {
              break;
            }
            i = j;
            localFile = new File(((ApplicationInfo)((Iterator)localObject3).next()).dataDir, str);
            i = j;
            if (((File)localObject1).lastModified() < localFile.lastModified())
            {
              j = 1;
              localObject1 = localFile;
            }
          }
          i = j;
          this.mMap = mapFromStore((File)localObject1);
        }
      }
      i = j;
      if (this.mMap == null)
      {
        i = j;
        this.mMap = new HashMap();
      }
      this.mLock.writeLock().unlock();
      i = j;
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        long l2;
        OpenFeintInternal.log("DistributedPrefs", "broken");
        this.mLock.writeLock().unlock();
      }
    }
    finally
    {
      this.mLock.writeLock().unlock();
    }
    if (i != 0) {
      save();
    }
    l2 = System.currentTimeMillis();
    OpenFeintInternal.log("DistributedPrefs", "Loading prefs took " + new Long(l2 - l1).toString() + " millis");
  }
  
  Reader read()
  {
    this.mLock.readLock().lock();
    return new Reader();
  }
  
  /* Error */
  public void save()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aconst_null
    //   3: astore 4
    //   5: aconst_null
    //   6: astore 5
    //   8: aconst_null
    //   9: astore 6
    //   11: aload_0
    //   12: getfield 41	com/openfeint/internal/SyncedStore:mLock	Ljava/util/concurrent/locks/ReentrantReadWriteLock;
    //   15: invokevirtual 224	java/util/concurrent/locks/ReentrantReadWriteLock:readLock	()Ljava/util/concurrent/locks/ReentrantReadWriteLock$ReadLock;
    //   18: invokevirtual 227	java/util/concurrent/locks/ReentrantReadWriteLock$ReadLock:lock	()V
    //   21: aload 5
    //   23: astore_3
    //   24: aload_0
    //   25: getfield 31	com/openfeint/internal/SyncedStore:mContext	Landroid/content/Context;
    //   28: ldc 14
    //   30: iconst_1
    //   31: invokevirtual 232	android/content/Context:openFileOutput	(Ljava/lang/String;I)Ljava/io/FileOutputStream;
    //   34: astore_1
    //   35: aload_1
    //   36: astore 4
    //   38: aload 5
    //   40: astore_3
    //   41: aload_1
    //   42: astore_2
    //   43: new 234	java/io/ObjectOutputStream
    //   46: dup
    //   47: aload_1
    //   48: invokespecial 237	java/io/ObjectOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   51: astore 5
    //   53: aload 5
    //   55: aload_0
    //   56: getfield 36	com/openfeint/internal/SyncedStore:mMap	Ljava/util/HashMap;
    //   59: invokevirtual 241	java/io/ObjectOutputStream:writeObject	(Ljava/lang/Object;)V
    //   62: aload 5
    //   64: ifnull +19 -> 83
    //   67: aload 5
    //   69: invokevirtual 242	java/io/ObjectOutputStream:close	()V
    //   72: aload_0
    //   73: getfield 41	com/openfeint/internal/SyncedStore:mLock	Ljava/util/concurrent/locks/ReentrantReadWriteLock;
    //   76: invokevirtual 224	java/util/concurrent/locks/ReentrantReadWriteLock:readLock	()Ljava/util/concurrent/locks/ReentrantReadWriteLock$ReadLock;
    //   79: invokevirtual 243	java/util/concurrent/locks/ReentrantReadWriteLock$ReadLock:unlock	()V
    //   82: return
    //   83: aload_1
    //   84: ifnull -12 -> 72
    //   87: aload_1
    //   88: invokevirtual 246	java/io/OutputStream:close	()V
    //   91: goto -19 -> 72
    //   94: astore_1
    //   95: ldc 17
    //   97: ldc 81
    //   99: invokestatic 87	com/openfeint/internal/OpenFeintInternal:log	(Ljava/lang/String;Ljava/lang/String;)V
    //   102: aload_0
    //   103: getfield 41	com/openfeint/internal/SyncedStore:mLock	Ljava/util/concurrent/locks/ReentrantReadWriteLock;
    //   106: invokevirtual 224	java/util/concurrent/locks/ReentrantReadWriteLock:readLock	()Ljava/util/concurrent/locks/ReentrantReadWriteLock$ReadLock;
    //   109: invokevirtual 243	java/util/concurrent/locks/ReentrantReadWriteLock$ReadLock:unlock	()V
    //   112: goto -30 -> 82
    //   115: astore_1
    //   116: aload_0
    //   117: getfield 41	com/openfeint/internal/SyncedStore:mLock	Ljava/util/concurrent/locks/ReentrantReadWriteLock;
    //   120: invokevirtual 224	java/util/concurrent/locks/ReentrantReadWriteLock:readLock	()Ljava/util/concurrent/locks/ReentrantReadWriteLock$ReadLock;
    //   123: invokevirtual 243	java/util/concurrent/locks/ReentrantReadWriteLock$ReadLock:unlock	()V
    //   126: aload_1
    //   127: athrow
    //   128: astore_1
    //   129: aload 4
    //   131: astore_1
    //   132: aload 6
    //   134: astore 4
    //   136: aload 4
    //   138: astore_3
    //   139: aload_1
    //   140: astore_2
    //   141: ldc 17
    //   143: ldc -8
    //   145: invokestatic 87	com/openfeint/internal/OpenFeintInternal:log	(Ljava/lang/String;Ljava/lang/String;)V
    //   148: aload 4
    //   150: ifnull +19 -> 169
    //   153: aload 4
    //   155: invokevirtual 242	java/io/ObjectOutputStream:close	()V
    //   158: aload_0
    //   159: getfield 41	com/openfeint/internal/SyncedStore:mLock	Ljava/util/concurrent/locks/ReentrantReadWriteLock;
    //   162: invokevirtual 224	java/util/concurrent/locks/ReentrantReadWriteLock:readLock	()Ljava/util/concurrent/locks/ReentrantReadWriteLock$ReadLock;
    //   165: invokevirtual 243	java/util/concurrent/locks/ReentrantReadWriteLock$ReadLock:unlock	()V
    //   168: return
    //   169: aload_1
    //   170: ifnull -12 -> 158
    //   173: aload_1
    //   174: invokevirtual 246	java/io/OutputStream:close	()V
    //   177: goto -19 -> 158
    //   180: astore_1
    //   181: ldc 17
    //   183: ldc 81
    //   185: invokestatic 87	com/openfeint/internal/OpenFeintInternal:log	(Ljava/lang/String;Ljava/lang/String;)V
    //   188: aload_0
    //   189: getfield 41	com/openfeint/internal/SyncedStore:mLock	Ljava/util/concurrent/locks/ReentrantReadWriteLock;
    //   192: invokevirtual 224	java/util/concurrent/locks/ReentrantReadWriteLock:readLock	()Ljava/util/concurrent/locks/ReentrantReadWriteLock$ReadLock;
    //   195: invokevirtual 243	java/util/concurrent/locks/ReentrantReadWriteLock$ReadLock:unlock	()V
    //   198: return
    //   199: astore_1
    //   200: aload_0
    //   201: getfield 41	com/openfeint/internal/SyncedStore:mLock	Ljava/util/concurrent/locks/ReentrantReadWriteLock;
    //   204: invokevirtual 224	java/util/concurrent/locks/ReentrantReadWriteLock:readLock	()Ljava/util/concurrent/locks/ReentrantReadWriteLock$ReadLock;
    //   207: invokevirtual 243	java/util/concurrent/locks/ReentrantReadWriteLock$ReadLock:unlock	()V
    //   210: aload_1
    //   211: athrow
    //   212: astore_1
    //   213: aload_3
    //   214: ifnull +19 -> 233
    //   217: aload_3
    //   218: invokevirtual 242	java/io/ObjectOutputStream:close	()V
    //   221: aload_0
    //   222: getfield 41	com/openfeint/internal/SyncedStore:mLock	Ljava/util/concurrent/locks/ReentrantReadWriteLock;
    //   225: invokevirtual 224	java/util/concurrent/locks/ReentrantReadWriteLock:readLock	()Ljava/util/concurrent/locks/ReentrantReadWriteLock$ReadLock;
    //   228: invokevirtual 243	java/util/concurrent/locks/ReentrantReadWriteLock$ReadLock:unlock	()V
    //   231: aload_1
    //   232: athrow
    //   233: aload_2
    //   234: ifnull -13 -> 221
    //   237: aload_2
    //   238: invokevirtual 246	java/io/OutputStream:close	()V
    //   241: goto -20 -> 221
    //   244: astore_2
    //   245: ldc 17
    //   247: ldc 81
    //   249: invokestatic 87	com/openfeint/internal/OpenFeintInternal:log	(Ljava/lang/String;Ljava/lang/String;)V
    //   252: aload_0
    //   253: getfield 41	com/openfeint/internal/SyncedStore:mLock	Ljava/util/concurrent/locks/ReentrantReadWriteLock;
    //   256: invokevirtual 224	java/util/concurrent/locks/ReentrantReadWriteLock:readLock	()Ljava/util/concurrent/locks/ReentrantReadWriteLock$ReadLock;
    //   259: invokevirtual 243	java/util/concurrent/locks/ReentrantReadWriteLock$ReadLock:unlock	()V
    //   262: goto -31 -> 231
    //   265: astore_1
    //   266: aload_0
    //   267: getfield 41	com/openfeint/internal/SyncedStore:mLock	Ljava/util/concurrent/locks/ReentrantReadWriteLock;
    //   270: invokevirtual 224	java/util/concurrent/locks/ReentrantReadWriteLock:readLock	()Ljava/util/concurrent/locks/ReentrantReadWriteLock$ReadLock;
    //   273: invokevirtual 243	java/util/concurrent/locks/ReentrantReadWriteLock$ReadLock:unlock	()V
    //   276: aload_1
    //   277: athrow
    //   278: astore 4
    //   280: aload 5
    //   282: astore_3
    //   283: aload_1
    //   284: astore_2
    //   285: aload 4
    //   287: astore_1
    //   288: goto -75 -> 213
    //   291: astore_2
    //   292: aload 5
    //   294: astore 4
    //   296: goto -160 -> 136
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	299	0	this	SyncedStore
    //   34	54	1	localFileOutputStream	java.io.FileOutputStream
    //   94	1	1	localIOException1	IOException
    //   115	12	1	localObject1	Object
    //   128	1	1	localIOException2	IOException
    //   131	43	1	localObject2	Object
    //   180	1	1	localIOException3	IOException
    //   199	12	1	localObject3	Object
    //   212	20	1	localObject4	Object
    //   265	19	1	localObject5	Object
    //   287	1	1	localObject6	Object
    //   1	237	2	localObject7	Object
    //   244	1	2	localIOException4	IOException
    //   284	1	2	localObject8	Object
    //   291	1	2	localIOException5	IOException
    //   23	260	3	localObject9	Object
    //   3	151	4	localObject10	Object
    //   278	8	4	localObject11	Object
    //   294	1	4	localObject12	Object
    //   6	287	5	localObjectOutputStream	java.io.ObjectOutputStream
    //   9	124	6	localObject13	Object
    // Exception table:
    //   from	to	target	type
    //   67	72	94	java/io/IOException
    //   87	91	94	java/io/IOException
    //   67	72	115	finally
    //   87	91	115	finally
    //   95	102	115	finally
    //   24	35	128	java/io/IOException
    //   43	53	128	java/io/IOException
    //   153	158	180	java/io/IOException
    //   173	177	180	java/io/IOException
    //   153	158	199	finally
    //   173	177	199	finally
    //   181	188	199	finally
    //   24	35	212	finally
    //   43	53	212	finally
    //   141	148	212	finally
    //   217	221	244	java/io/IOException
    //   237	241	244	java/io/IOException
    //   217	221	265	finally
    //   237	241	265	finally
    //   245	252	265	finally
    //   53	62	278	finally
    //   53	62	291	java/io/IOException
  }
  
  public class Editor
  {
    public Editor() {}
    
    public void commit()
    {
      SyncedStore.this.save();
      SyncedStore.this.mLock.writeLock().unlock();
    }
    
    public Set<String> keySet()
    {
      return new HashSet(SyncedStore.this.mMap.keySet());
    }
    
    public void putString(String paramString1, String paramString2)
    {
      SyncedStore.this.mMap.put(paramString1, paramString2);
    }
    
    public void remove(String paramString)
    {
      SyncedStore.this.mMap.remove(paramString);
    }
  }
  
  public class Reader
  {
    public Reader() {}
    
    public void complete()
    {
      SyncedStore.this.mLock.readLock().unlock();
    }
    
    public String getString(String paramString1, String paramString2)
    {
      paramString1 = (String)SyncedStore.this.mMap.get(paramString1);
      if (paramString1 != null) {
        return paramString1;
      }
      return paramString2;
    }
    
    public Set<String> keySet()
    {
      return SyncedStore.this.mMap.keySet();
    }
  }
}
