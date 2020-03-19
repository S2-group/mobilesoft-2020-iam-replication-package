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
    //   55: ifnull +297 -> 352
    //   58: aload_3
    //   59: instanceof 33
    //   62: ifeq +290 -> 352
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
    //   103: astore_1
    //   104: aload 9
    //   106: astore_1
    //   107: aload_1
    //   108: astore_2
    //   109: aload 4
    //   111: astore_3
    //   112: ldc 17
    //   114: ldc 89
    //   116: invokestatic 87	com/openfeint/internal/OpenFeintInternal:log	(Ljava/lang/String;Ljava/lang/String;)V
    //   119: aload 4
    //   121: ifnull +11 -> 132
    //   124: aload 4
    //   126: invokevirtual 76	java/io/ObjectInputStream:close	()V
    //   129: goto +339 -> 468
    //   132: aload_1
    //   133: ifnull +335 -> 468
    //   136: aload_1
    //   137: invokevirtual 79	java/io/InputStream:close	()V
    //   140: goto +328 -> 468
    //   143: astore_1
    //   144: ldc 17
    //   146: ldc 81
    //   148: invokestatic 87	com/openfeint/internal/OpenFeintInternal:log	(Ljava/lang/String;Ljava/lang/String;)V
    //   151: goto +317 -> 468
    //   154: astore_1
    //   155: aload 5
    //   157: astore 4
    //   159: aload 10
    //   161: astore_1
    //   162: aload_1
    //   163: astore_2
    //   164: aload 4
    //   166: astore_3
    //   167: ldc 17
    //   169: ldc 91
    //   171: invokestatic 87	com/openfeint/internal/OpenFeintInternal:log	(Ljava/lang/String;Ljava/lang/String;)V
    //   174: aload 4
    //   176: ifnull +22 -> 198
    //   179: aload 4
    //   181: invokevirtual 76	java/io/ObjectInputStream:close	()V
    //   184: goto +284 -> 468
    //   187: astore_1
    //   188: ldc 17
    //   190: ldc 81
    //   192: invokestatic 87	com/openfeint/internal/OpenFeintInternal:log	(Ljava/lang/String;Ljava/lang/String;)V
    //   195: goto +273 -> 468
    //   198: aload_1
    //   199: ifnull +269 -> 468
    //   202: aload_1
    //   203: invokevirtual 79	java/io/InputStream:close	()V
    //   206: goto +262 -> 468
    //   209: astore_1
    //   210: aload 6
    //   212: astore 4
    //   214: aload 11
    //   216: astore_1
    //   217: aload_1
    //   218: astore_2
    //   219: aload 4
    //   221: astore_3
    //   222: ldc 17
    //   224: ldc 93
    //   226: invokestatic 87	com/openfeint/internal/OpenFeintInternal:log	(Ljava/lang/String;Ljava/lang/String;)V
    //   229: aload 4
    //   231: ifnull +22 -> 253
    //   234: aload 4
    //   236: invokevirtual 76	java/io/ObjectInputStream:close	()V
    //   239: goto +229 -> 468
    //   242: astore_1
    //   243: ldc 17
    //   245: ldc 81
    //   247: invokestatic 87	com/openfeint/internal/OpenFeintInternal:log	(Ljava/lang/String;Ljava/lang/String;)V
    //   250: goto +218 -> 468
    //   253: aload_1
    //   254: ifnull +214 -> 468
    //   257: aload_1
    //   258: invokevirtual 79	java/io/InputStream:close	()V
    //   261: goto +207 -> 468
    //   264: astore_1
    //   265: aload 7
    //   267: astore 4
    //   269: aload 12
    //   271: astore_1
    //   272: aload_1
    //   273: astore_2
    //   274: aload 4
    //   276: astore_3
    //   277: ldc 17
    //   279: ldc 95
    //   281: invokestatic 87	com/openfeint/internal/OpenFeintInternal:log	(Ljava/lang/String;Ljava/lang/String;)V
    //   284: aload 4
    //   286: ifnull +22 -> 308
    //   289: aload 4
    //   291: invokevirtual 76	java/io/ObjectInputStream:close	()V
    //   294: goto +174 -> 468
    //   297: astore_1
    //   298: ldc 17
    //   300: ldc 81
    //   302: invokestatic 87	com/openfeint/internal/OpenFeintInternal:log	(Ljava/lang/String;Ljava/lang/String;)V
    //   305: goto +163 -> 468
    //   308: aload_1
    //   309: ifnull +159 -> 468
    //   312: aload_1
    //   313: invokevirtual 79	java/io/InputStream:close	()V
    //   316: goto +152 -> 468
    //   319: astore_1
    //   320: aload_3
    //   321: ifnull +9 -> 330
    //   324: aload_3
    //   325: invokevirtual 76	java/io/ObjectInputStream:close	()V
    //   328: aload_1
    //   329: athrow
    //   330: aload_2
    //   331: ifnull -3 -> 328
    //   334: aload_2
    //   335: invokevirtual 79	java/io/InputStream:close	()V
    //   338: goto -10 -> 328
    //   341: astore_2
    //   342: ldc 17
    //   344: ldc 81
    //   346: invokestatic 87	com/openfeint/internal/OpenFeintInternal:log	(Ljava/lang/String;Ljava/lang/String;)V
    //   349: goto -21 -> 328
    //   352: aload_2
    //   353: ifnull +10 -> 363
    //   356: aload_2
    //   357: invokevirtual 76	java/io/ObjectInputStream:close	()V
    //   360: goto +108 -> 468
    //   363: aload_1
    //   364: ifnull +18 -> 382
    //   367: aload_1
    //   368: invokevirtual 79	java/io/InputStream:close	()V
    //   371: goto +97 -> 468
    //   374: astore_1
    //   375: ldc 17
    //   377: ldc 81
    //   379: invokestatic 87	com/openfeint/internal/OpenFeintInternal:log	(Ljava/lang/String;Ljava/lang/String;)V
    //   382: goto +86 -> 468
    //   385: astore 4
    //   387: aload_1
    //   388: astore_2
    //   389: aload 8
    //   391: astore_3
    //   392: aload 4
    //   394: astore_1
    //   395: goto -75 -> 320
    //   398: astore 4
    //   400: aload_2
    //   401: astore_3
    //   402: aload_1
    //   403: astore_2
    //   404: aload 4
    //   406: astore_1
    //   407: goto -87 -> 320
    //   410: astore_2
    //   411: aload 7
    //   413: astore 4
    //   415: goto -143 -> 272
    //   418: astore_3
    //   419: aload_2
    //   420: astore 4
    //   422: goto -150 -> 272
    //   425: astore_2
    //   426: aload 6
    //   428: astore 4
    //   430: goto -213 -> 217
    //   433: astore_3
    //   434: aload_2
    //   435: astore 4
    //   437: goto -220 -> 217
    //   440: astore_2
    //   441: aload 5
    //   443: astore 4
    //   445: goto -283 -> 162
    //   448: astore_3
    //   449: aload_2
    //   450: astore 4
    //   452: goto -290 -> 162
    //   455: astore_2
    //   456: goto -349 -> 107
    //   459: astore_3
    //   460: aload_2
    //   461: astore 4
    //   463: goto -356 -> 107
    //   466: aload_3
    //   467: areturn
    //   468: aconst_null
    //   469: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	470	0	this	SyncedStore
    //   0	470	1	paramFile	File
    //   10	325	2	localObject1	Object
    //   341	16	2	localIOException1	IOException
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
    //   29	261	4	localObject3	Object
    //   385	8	4	localObject4	Object
    //   398	7	4	localObject5	Object
    //   413	49	4	localObject6	Object
    //   15	427	5	localObject7	Object
    //   18	409	6	localObject8	Object
    //   21	391	7	localObject9	Object
    //   26	364	8	localObject10	Object
    //   12	93	9	localObject11	Object
    //   1	159	10	localObject12	Object
    //   4	211	11	localObject13	Object
    //   7	263	12	localObject14	Object
    // Exception table:
    //   from	to	target	type
    //   74	78	92	java/io/IOException
    //   85	89	92	java/io/IOException
    //   31	40	103	java/io/FileNotFoundException
    //   124	129	143	java/io/IOException
    //   136	140	143	java/io/IOException
    //   31	40	154	java/io/StreamCorruptedException
    //   179	184	187	java/io/IOException
    //   202	206	187	java/io/IOException
    //   31	40	209	java/io/IOException
    //   234	239	242	java/io/IOException
    //   257	261	242	java/io/IOException
    //   31	40	264	java/lang/ClassNotFoundException
    //   289	294	297	java/io/IOException
    //   312	316	297	java/io/IOException
    //   31	40	319	finally
    //   112	119	319	finally
    //   167	174	319	finally
    //   222	229	319	finally
    //   277	284	319	finally
    //   324	328	341	java/io/IOException
    //   334	338	341	java/io/IOException
    //   356	360	374	java/io/IOException
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
    for (;;)
    {
      try
      {
        localObject4 = this.mContext.getPackageManager().getInstalledApplications(0);
        localObject1 = null;
        i = n;
        localIterator = ((List)localObject4).iterator();
        i = n;
        if (localIterator.hasNext()) {
          continue;
        }
        i = n;
        localObject3 = localFile.getCanonicalPath();
        j = m;
        if (localObject1 != null)
        {
          j = m;
          i = n;
          if (((String)localObject3).startsWith(((ApplicationInfo)localObject1).dataDir))
          {
            i = n;
            localObject3 = ((String)localObject3).substring(((ApplicationInfo)localObject1).dataDir.length());
            i = n;
            localObject4 = ((List)localObject4).iterator();
            localObject1 = localFile;
            j = k;
            i = j;
            if (((Iterator)localObject4).hasNext()) {
              continue;
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
        Object localObject4;
        Object localObject1;
        Iterator localIterator;
        Object localObject3;
        int j;
        long l2;
        long l3;
        OpenFeintInternal.log("DistributedPrefs", "broken");
        this.mLock.writeLock().unlock();
        continue;
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
      return;
      i = n;
      localObject3 = (ApplicationInfo)localIterator.next();
      i = n;
      if (((ApplicationInfo)localObject3).packageName.equals(this.mContext.getPackageName()))
      {
        localObject1 = localObject3;
        continue;
        i = j;
        localFile = new File(((ApplicationInfo)((Iterator)localObject4).next()).dataDir, (String)localObject3);
        i = j;
        l2 = ((File)localObject1).lastModified();
        i = j;
        l3 = localFile.lastModified();
        if (l2 < l3)
        {
          j = 1;
          localObject1 = localFile;
        }
      }
    }
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
    //   15: invokevirtual 226	java/util/concurrent/locks/ReentrantReadWriteLock:readLock	()Ljava/util/concurrent/locks/ReentrantReadWriteLock$ReadLock;
    //   18: invokevirtual 229	java/util/concurrent/locks/ReentrantReadWriteLock$ReadLock:lock	()V
    //   21: aload 5
    //   23: astore_3
    //   24: aload_0
    //   25: getfield 31	com/openfeint/internal/SyncedStore:mContext	Landroid/content/Context;
    //   28: ldc 14
    //   30: iconst_1
    //   31: invokevirtual 234	android/content/Context:openFileOutput	(Ljava/lang/String;I)Ljava/io/FileOutputStream;
    //   34: astore_1
    //   35: aload_1
    //   36: astore 4
    //   38: aload 5
    //   40: astore_3
    //   41: aload_1
    //   42: astore_2
    //   43: new 236	java/io/ObjectOutputStream
    //   46: dup
    //   47: aload_1
    //   48: invokespecial 239	java/io/ObjectOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   51: astore 5
    //   53: aload 5
    //   55: aload_0
    //   56: getfield 36	com/openfeint/internal/SyncedStore:mMap	Ljava/util/HashMap;
    //   59: invokevirtual 243	java/io/ObjectOutputStream:writeObject	(Ljava/lang/Object;)V
    //   62: aload 5
    //   64: ifnull +169 -> 233
    //   67: aload 5
    //   69: invokevirtual 244	java/io/ObjectOutputStream:close	()V
    //   72: aload_0
    //   73: getfield 41	com/openfeint/internal/SyncedStore:mLock	Ljava/util/concurrent/locks/ReentrantReadWriteLock;
    //   76: invokevirtual 226	java/util/concurrent/locks/ReentrantReadWriteLock:readLock	()Ljava/util/concurrent/locks/ReentrantReadWriteLock$ReadLock;
    //   79: invokevirtual 245	java/util/concurrent/locks/ReentrantReadWriteLock$ReadLock:unlock	()V
    //   82: return
    //   83: astore_1
    //   84: aload 4
    //   86: astore_1
    //   87: aload 6
    //   89: astore 4
    //   91: aload 4
    //   93: astore_3
    //   94: aload_1
    //   95: astore_2
    //   96: ldc 17
    //   98: ldc -9
    //   100: invokestatic 87	com/openfeint/internal/OpenFeintInternal:log	(Ljava/lang/String;Ljava/lang/String;)V
    //   103: aload 4
    //   105: ifnull +19 -> 124
    //   108: aload 4
    //   110: invokevirtual 244	java/io/ObjectOutputStream:close	()V
    //   113: aload_0
    //   114: getfield 41	com/openfeint/internal/SyncedStore:mLock	Ljava/util/concurrent/locks/ReentrantReadWriteLock;
    //   117: invokevirtual 226	java/util/concurrent/locks/ReentrantReadWriteLock:readLock	()Ljava/util/concurrent/locks/ReentrantReadWriteLock$ReadLock;
    //   120: invokevirtual 245	java/util/concurrent/locks/ReentrantReadWriteLock$ReadLock:unlock	()V
    //   123: return
    //   124: aload_1
    //   125: ifnull -12 -> 113
    //   128: aload_1
    //   129: invokevirtual 250	java/io/OutputStream:close	()V
    //   132: goto -19 -> 113
    //   135: astore_1
    //   136: ldc 17
    //   138: ldc 81
    //   140: invokestatic 87	com/openfeint/internal/OpenFeintInternal:log	(Ljava/lang/String;Ljava/lang/String;)V
    //   143: aload_0
    //   144: getfield 41	com/openfeint/internal/SyncedStore:mLock	Ljava/util/concurrent/locks/ReentrantReadWriteLock;
    //   147: invokevirtual 226	java/util/concurrent/locks/ReentrantReadWriteLock:readLock	()Ljava/util/concurrent/locks/ReentrantReadWriteLock$ReadLock;
    //   150: invokevirtual 245	java/util/concurrent/locks/ReentrantReadWriteLock$ReadLock:unlock	()V
    //   153: return
    //   154: astore_1
    //   155: aload_0
    //   156: getfield 41	com/openfeint/internal/SyncedStore:mLock	Ljava/util/concurrent/locks/ReentrantReadWriteLock;
    //   159: invokevirtual 226	java/util/concurrent/locks/ReentrantReadWriteLock:readLock	()Ljava/util/concurrent/locks/ReentrantReadWriteLock$ReadLock;
    //   162: invokevirtual 245	java/util/concurrent/locks/ReentrantReadWriteLock$ReadLock:unlock	()V
    //   165: aload_1
    //   166: athrow
    //   167: astore_1
    //   168: aload_3
    //   169: ifnull +19 -> 188
    //   172: aload_3
    //   173: invokevirtual 244	java/io/ObjectOutputStream:close	()V
    //   176: aload_0
    //   177: getfield 41	com/openfeint/internal/SyncedStore:mLock	Ljava/util/concurrent/locks/ReentrantReadWriteLock;
    //   180: invokevirtual 226	java/util/concurrent/locks/ReentrantReadWriteLock:readLock	()Ljava/util/concurrent/locks/ReentrantReadWriteLock$ReadLock;
    //   183: invokevirtual 245	java/util/concurrent/locks/ReentrantReadWriteLock$ReadLock:unlock	()V
    //   186: aload_1
    //   187: athrow
    //   188: aload_2
    //   189: ifnull -13 -> 176
    //   192: aload_2
    //   193: invokevirtual 250	java/io/OutputStream:close	()V
    //   196: goto -20 -> 176
    //   199: astore_2
    //   200: ldc 17
    //   202: ldc 81
    //   204: invokestatic 87	com/openfeint/internal/OpenFeintInternal:log	(Ljava/lang/String;Ljava/lang/String;)V
    //   207: aload_0
    //   208: getfield 41	com/openfeint/internal/SyncedStore:mLock	Ljava/util/concurrent/locks/ReentrantReadWriteLock;
    //   211: invokevirtual 226	java/util/concurrent/locks/ReentrantReadWriteLock:readLock	()Ljava/util/concurrent/locks/ReentrantReadWriteLock$ReadLock;
    //   214: invokevirtual 245	java/util/concurrent/locks/ReentrantReadWriteLock$ReadLock:unlock	()V
    //   217: goto -31 -> 186
    //   220: astore_1
    //   221: aload_0
    //   222: getfield 41	com/openfeint/internal/SyncedStore:mLock	Ljava/util/concurrent/locks/ReentrantReadWriteLock;
    //   225: invokevirtual 226	java/util/concurrent/locks/ReentrantReadWriteLock:readLock	()Ljava/util/concurrent/locks/ReentrantReadWriteLock$ReadLock;
    //   228: invokevirtual 245	java/util/concurrent/locks/ReentrantReadWriteLock$ReadLock:unlock	()V
    //   231: aload_1
    //   232: athrow
    //   233: aload_1
    //   234: ifnull -162 -> 72
    //   237: aload_1
    //   238: invokevirtual 250	java/io/OutputStream:close	()V
    //   241: goto -169 -> 72
    //   244: astore_1
    //   245: ldc 17
    //   247: ldc 81
    //   249: invokestatic 87	com/openfeint/internal/OpenFeintInternal:log	(Ljava/lang/String;Ljava/lang/String;)V
    //   252: aload_0
    //   253: getfield 41	com/openfeint/internal/SyncedStore:mLock	Ljava/util/concurrent/locks/ReentrantReadWriteLock;
    //   256: invokevirtual 226	java/util/concurrent/locks/ReentrantReadWriteLock:readLock	()Ljava/util/concurrent/locks/ReentrantReadWriteLock$ReadLock;
    //   259: invokevirtual 245	java/util/concurrent/locks/ReentrantReadWriteLock$ReadLock:unlock	()V
    //   262: return
    //   263: astore_1
    //   264: aload_0
    //   265: getfield 41	com/openfeint/internal/SyncedStore:mLock	Ljava/util/concurrent/locks/ReentrantReadWriteLock;
    //   268: invokevirtual 226	java/util/concurrent/locks/ReentrantReadWriteLock:readLock	()Ljava/util/concurrent/locks/ReentrantReadWriteLock$ReadLock;
    //   271: invokevirtual 245	java/util/concurrent/locks/ReentrantReadWriteLock$ReadLock:unlock	()V
    //   274: aload_1
    //   275: athrow
    //   276: astore 4
    //   278: aload 5
    //   280: astore_3
    //   281: aload_1
    //   282: astore_2
    //   283: aload 4
    //   285: astore_1
    //   286: goto -118 -> 168
    //   289: astore_2
    //   290: aload 5
    //   292: astore 4
    //   294: goto -203 -> 91
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	297	0	this	SyncedStore
    //   34	14	1	localFileOutputStream	java.io.FileOutputStream
    //   83	1	1	localIOException1	IOException
    //   86	43	1	localObject1	Object
    //   135	1	1	localIOException2	IOException
    //   154	12	1	localObject2	Object
    //   167	20	1	localObject3	Object
    //   220	18	1	localObject4	Object
    //   244	1	1	localIOException3	IOException
    //   263	19	1	localObject5	Object
    //   285	1	1	localObject6	Object
    //   1	192	2	localObject7	Object
    //   199	1	2	localIOException4	IOException
    //   282	1	2	localObject8	Object
    //   289	1	2	localIOException5	IOException
    //   23	258	3	localObject9	Object
    //   3	106	4	localObject10	Object
    //   276	8	4	localObject11	Object
    //   292	1	4	localObject12	Object
    //   6	285	5	localObjectOutputStream	java.io.ObjectOutputStream
    //   9	79	6	localObject13	Object
    // Exception table:
    //   from	to	target	type
    //   24	35	83	java/io/IOException
    //   43	53	83	java/io/IOException
    //   108	113	135	java/io/IOException
    //   128	132	135	java/io/IOException
    //   108	113	154	finally
    //   128	132	154	finally
    //   136	143	154	finally
    //   24	35	167	finally
    //   43	53	167	finally
    //   96	103	167	finally
    //   172	176	199	java/io/IOException
    //   192	196	199	java/io/IOException
    //   172	176	220	finally
    //   192	196	220	finally
    //   200	207	220	finally
    //   67	72	244	java/io/IOException
    //   237	241	244	java/io/IOException
    //   67	72	263	finally
    //   237	241	263	finally
    //   245	252	263	finally
    //   53	62	276	finally
    //   53	62	289	java/io/IOException
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
