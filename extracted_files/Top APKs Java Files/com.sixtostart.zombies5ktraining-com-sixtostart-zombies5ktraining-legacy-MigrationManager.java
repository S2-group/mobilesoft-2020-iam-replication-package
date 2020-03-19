package com.sixtostart.zombies5ktraining.legacy;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.sixtostart.gameengine.audio.runEvents.AudioRunEventInfo.ICON_ID;
import com.sixtostart.gameengine.models.documents.files.DocumentBundle;
import com.sixtostart.gameengine.models.scripts.MissionMetadataScript;
import com.sixtostart.gameengine.models.scripts.MissionScript;
import com.sixtostart.gameengine.models.scripts.SceneScript;
import com.sixtostart.zombies5k.orm.models.GPSPosition;
import com.sixtostart.zombies5k.orm.models.RunEvent;
import com.sixtostart.zombies5k.orm.models.RunRecord;
import com.sixtostart.zombies5k.orm.models.base.Event;
import com.sixtostart.zombies5k.orm.models.events.MissionPausedEvent;
import com.sixtostart.zombies5k.orm.models.events.NewTrackEvent;
import com.sixtostart.zombies5k.orm.models.events.PartStartedEvent;
import com.sixtostart.zombies5ktraining.app.account.PlayerRepository;
import com.sixtostart.zombies5ktraining.app.account.model.Player;
import com.sixtostart.zombies5ktraining.app.workout.DocumentBundleRepository;
import com.sixtostart.zombies5ktraining.app.workout.run.RunLocationRepository;
import com.sixtostart.zombies5ktraining.app.workout.run.RunRepository;
import com.sixtostart.zombies5ktraining.app.workout.run.model.AudioEvent;
import com.sixtostart.zombies5ktraining.app.workout.run.model.PauseEvent;
import com.sixtostart.zombies5ktraining.app.workout.run.model.Run;
import com.sixtostart.zombies5ktraining.app.workout.run.model.RunLocation;
import dagger.Lazy;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import sixtostart.com.runengine2.utils.DistanceSpan;
import sixtostart.com.runengine2.utils.DistanceUnit;
import sixtostart.com.runengine2.utils.TimeSpan;

public class MigrationManager
{
  private static final String ALL_RUNS_URL = "content://com.sixtostart.zombies5k.LegacyContentProvider/allruns";
  private static final String AUTHROITY = "com.sixtostart.zombies5k.LegacyContentProvider";
  private static final String GPS_URL = "content://com.sixtostart.zombies5k.LegacyContentProvider/gps";
  private static final String LAST_MIGRATION_KEY = "LAST_MIGRATION_KEY";
  private static final String LEGACY_PACKAGE_NAME = "com.sixtostart.zombies5k";
  private static final String MIGRATION_PREF_KEY = "MIGRATION_PREF_KEY";
  private static final String MISSIONS_URL = "content://com.sixtostart.zombies5k.LegacyContentProvider/missions";
  private static final String MISSION_EVENTS_URL = "content://com.sixtostart.zombies5k.LegacyContentProvider/missionevents";
  private static final String RUNS_URL = "content://com.sixtostart.zombies5k.LegacyContentProvider/runs";
  private static final String RUN_EVENTS_URL = "content://com.sixtostart.zombies5k.LegacyContentProvider/runevents";
  private static final String TAG = "MigrationManager";
  private static ThreadLocal<SimpleDateFormat> dateFormatThreadLocal = new ThreadLocal()
  {
    protected SimpleDateFormat initialValue()
    {
      return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S", Locale.getDefault());
    }
  };
  private final Context context;
  private final Lazy<DocumentBundleRepository> documentBundleRepository;
  private final Lazy<PlayerRepository> playerRepositoryLazy;
  private final Lazy<RunLocationRepository> runLocationRepository;
  private final Lazy<RunRepository> runRepository;
  
  @Inject
  public MigrationManager(Context paramContext, Lazy<RunRepository> paramLazy, Lazy<RunLocationRepository> paramLazy1, Lazy<DocumentBundleRepository> paramLazy2, Lazy<PlayerRepository> paramLazy3)
  {
    this.context = paramContext;
    this.runRepository = paramLazy;
    this.runLocationRepository = paramLazy1;
    this.documentBundleRepository = paramLazy2;
    this.playerRepositoryLazy = paramLazy3;
  }
  
  private Date convertDate(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return null;
    }
    try
    {
      Date localDate = ((SimpleDateFormat)dateFormatThreadLocal.get()).parse(paramString);
      return localDate;
    }
    catch (ParseException localParseException)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Failed to convert string:");
      localStringBuilder.append(paramString);
      Log.e("MigrationManager", localStringBuilder.toString(), localParseException);
    }
    return null;
  }
  
  /* Error */
  private List<RunRecord> getAllRunRecords(Context paramContext)
  {
    // Byte code:
    //   0: ldc 10
    //   2: invokestatic 127	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   5: astore 7
    //   7: aload_1
    //   8: invokevirtual 133	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   11: aload 7
    //   13: aconst_null
    //   14: aconst_null
    //   15: aconst_null
    //   16: aconst_null
    //   17: invokevirtual 139	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   20: astore 9
    //   22: aconst_null
    //   23: astore_1
    //   24: aconst_null
    //   25: astore 7
    //   27: aload 9
    //   29: ifnull +286 -> 315
    //   32: aload 7
    //   34: astore_1
    //   35: new 141	java/util/ArrayList
    //   38: dup
    //   39: aload 9
    //   41: invokeinterface 147 1 0
    //   46: invokespecial 150	java/util/ArrayList:<init>	(I)V
    //   49: astore 8
    //   51: aload 7
    //   53: astore_1
    //   54: aload 9
    //   56: invokeinterface 154 1 0
    //   61: ifeq +195 -> 256
    //   64: aload 7
    //   66: astore_1
    //   67: aload 9
    //   69: aload 9
    //   71: ldc -100
    //   73: invokeinterface 160 2 0
    //   78: invokeinterface 164 2 0
    //   83: istore_2
    //   84: aload 7
    //   86: astore_1
    //   87: aload_0
    //   88: aload 9
    //   90: aload 9
    //   92: ldc -90
    //   94: invokeinterface 160 2 0
    //   99: invokeinterface 170 2 0
    //   104: invokespecial 172	com/sixtostart/zombies5ktraining/legacy/MigrationManager:convertDate	(Ljava/lang/String;)Ljava/util/Date;
    //   107: astore 10
    //   109: aload 7
    //   111: astore_1
    //   112: aload_0
    //   113: aload 9
    //   115: aload 9
    //   117: ldc -82
    //   119: invokeinterface 160 2 0
    //   124: invokeinterface 170 2 0
    //   129: invokespecial 172	com/sixtostart/zombies5ktraining/legacy/MigrationManager:convertDate	(Ljava/lang/String;)Ljava/util/Date;
    //   132: astore 11
    //   134: aload 7
    //   136: astore_1
    //   137: aload 9
    //   139: aload 9
    //   141: ldc -80
    //   143: invokeinterface 160 2 0
    //   148: invokeinterface 164 2 0
    //   153: istore_3
    //   154: aload 7
    //   156: astore_1
    //   157: aload 9
    //   159: aload 9
    //   161: ldc -78
    //   163: invokeinterface 160 2 0
    //   168: invokeinterface 164 2 0
    //   173: istore 4
    //   175: aload 7
    //   177: astore_1
    //   178: aload 9
    //   180: aload 9
    //   182: ldc -78
    //   184: invokeinterface 160 2 0
    //   189: invokeinterface 164 2 0
    //   194: istore 5
    //   196: iconst_1
    //   197: istore 6
    //   199: iload 5
    //   201: iconst_1
    //   202: if_icmpne +152 -> 354
    //   205: goto +3 -> 208
    //   208: aload 7
    //   210: astore_1
    //   211: aload 8
    //   213: new 180	com/sixtostart/zombies5k/orm/models/RunRecord
    //   216: dup
    //   217: iload_2
    //   218: aload 10
    //   220: aload 11
    //   222: iload_3
    //   223: iload 4
    //   225: iload 6
    //   227: invokestatic 186	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   230: invokespecial 189	com/sixtostart/zombies5k/orm/models/RunRecord:<init>	(ILjava/util/Date;Ljava/util/Date;IILjava/lang/Boolean;)V
    //   233: invokeinterface 195 2 0
    //   238: pop
    //   239: aload 7
    //   241: astore_1
    //   242: aload 9
    //   244: invokeinterface 198 1 0
    //   249: istore 6
    //   251: iload 6
    //   253: ifne -189 -> 64
    //   256: aload 8
    //   258: astore_1
    //   259: goto +56 -> 315
    //   262: astore 7
    //   264: goto +11 -> 275
    //   267: astore 7
    //   269: aload 7
    //   271: astore_1
    //   272: aload 7
    //   274: athrow
    //   275: aload 9
    //   277: ifnull +35 -> 312
    //   280: aload_1
    //   281: ifnull +24 -> 305
    //   284: aload 9
    //   286: invokeinterface 201 1 0
    //   291: goto +21 -> 312
    //   294: astore 8
    //   296: aload_1
    //   297: aload 8
    //   299: invokevirtual 205	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   302: goto +10 -> 312
    //   305: aload 9
    //   307: invokeinterface 201 1 0
    //   312: aload 7
    //   314: athrow
    //   315: aload_1
    //   316: astore 7
    //   318: aload 9
    //   320: ifnull +31 -> 351
    //   323: aload 9
    //   325: invokeinterface 201 1 0
    //   330: aload_1
    //   331: areturn
    //   332: astore_1
    //   333: ldc 38
    //   335: ldc -49
    //   337: aload_1
    //   338: invokestatic 116	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   341: pop
    //   342: new 141	java/util/ArrayList
    //   345: dup
    //   346: invokespecial 208	java/util/ArrayList:<init>	()V
    //   349: astore 7
    //   351: aload 7
    //   353: areturn
    //   354: iconst_0
    //   355: istore 6
    //   357: goto -149 -> 208
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	360	0	this	MigrationManager
    //   0	360	1	paramContext	Context
    //   83	135	2	i	int
    //   153	70	3	j	int
    //   173	51	4	k	int
    //   194	9	5	m	int
    //   197	159	6	bool	boolean
    //   5	235	7	localUri	android.net.Uri
    //   262	1	7	localObject1	Object
    //   267	46	7	localThrowable1	Throwable
    //   316	36	7	localObject2	Object
    //   49	208	8	localArrayList	ArrayList
    //   294	4	8	localThrowable2	Throwable
    //   20	304	9	localCursor	android.database.Cursor
    //   107	112	10	localDate1	Date
    //   132	89	11	localDate2	Date
    // Exception table:
    //   from	to	target	type
    //   35	51	262	finally
    //   54	64	262	finally
    //   67	84	262	finally
    //   87	109	262	finally
    //   112	134	262	finally
    //   137	154	262	finally
    //   157	175	262	finally
    //   178	196	262	finally
    //   211	239	262	finally
    //   242	251	262	finally
    //   272	275	262	finally
    //   35	51	267	java/lang/Throwable
    //   54	64	267	java/lang/Throwable
    //   67	84	267	java/lang/Throwable
    //   87	109	267	java/lang/Throwable
    //   112	134	267	java/lang/Throwable
    //   137	154	267	java/lang/Throwable
    //   157	175	267	java/lang/Throwable
    //   178	196	267	java/lang/Throwable
    //   211	239	267	java/lang/Throwable
    //   242	251	267	java/lang/Throwable
    //   284	291	294	java/lang/Throwable
    //   7	22	332	java/lang/SecurityException
    //   284	291	332	java/lang/SecurityException
    //   296	302	332	java/lang/SecurityException
    //   305	312	332	java/lang/SecurityException
    //   312	315	332	java/lang/SecurityException
    //   323	330	332	java/lang/SecurityException
  }
  
  /* Error */
  private List<RunRecord> getAllUnsynchedAnonymousRunRecords(Context paramContext)
  {
    // Byte code:
    //   0: ldc 32
    //   2: invokestatic 127	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   5: astore 7
    //   7: aload_1
    //   8: invokevirtual 133	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   11: aload 7
    //   13: aconst_null
    //   14: aconst_null
    //   15: aconst_null
    //   16: aconst_null
    //   17: invokevirtual 139	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   20: astore 9
    //   22: aconst_null
    //   23: astore_1
    //   24: aconst_null
    //   25: astore 7
    //   27: aload 9
    //   29: ifnull +286 -> 315
    //   32: aload 7
    //   34: astore_1
    //   35: new 141	java/util/ArrayList
    //   38: dup
    //   39: aload 9
    //   41: invokeinterface 147 1 0
    //   46: invokespecial 150	java/util/ArrayList:<init>	(I)V
    //   49: astore 8
    //   51: aload 7
    //   53: astore_1
    //   54: aload 9
    //   56: invokeinterface 154 1 0
    //   61: ifeq +195 -> 256
    //   64: aload 7
    //   66: astore_1
    //   67: aload 9
    //   69: aload 9
    //   71: ldc -100
    //   73: invokeinterface 160 2 0
    //   78: invokeinterface 164 2 0
    //   83: istore_2
    //   84: aload 7
    //   86: astore_1
    //   87: aload_0
    //   88: aload 9
    //   90: aload 9
    //   92: ldc -90
    //   94: invokeinterface 160 2 0
    //   99: invokeinterface 170 2 0
    //   104: invokespecial 172	com/sixtostart/zombies5ktraining/legacy/MigrationManager:convertDate	(Ljava/lang/String;)Ljava/util/Date;
    //   107: astore 10
    //   109: aload 7
    //   111: astore_1
    //   112: aload_0
    //   113: aload 9
    //   115: aload 9
    //   117: ldc -82
    //   119: invokeinterface 160 2 0
    //   124: invokeinterface 170 2 0
    //   129: invokespecial 172	com/sixtostart/zombies5ktraining/legacy/MigrationManager:convertDate	(Ljava/lang/String;)Ljava/util/Date;
    //   132: astore 11
    //   134: aload 7
    //   136: astore_1
    //   137: aload 9
    //   139: aload 9
    //   141: ldc -80
    //   143: invokeinterface 160 2 0
    //   148: invokeinterface 164 2 0
    //   153: istore_3
    //   154: aload 7
    //   156: astore_1
    //   157: aload 9
    //   159: aload 9
    //   161: ldc -78
    //   163: invokeinterface 160 2 0
    //   168: invokeinterface 164 2 0
    //   173: istore 4
    //   175: aload 7
    //   177: astore_1
    //   178: aload 9
    //   180: aload 9
    //   182: ldc -78
    //   184: invokeinterface 160 2 0
    //   189: invokeinterface 164 2 0
    //   194: istore 5
    //   196: iconst_1
    //   197: istore 6
    //   199: iload 5
    //   201: iconst_1
    //   202: if_icmpne +152 -> 354
    //   205: goto +3 -> 208
    //   208: aload 7
    //   210: astore_1
    //   211: aload 8
    //   213: new 180	com/sixtostart/zombies5k/orm/models/RunRecord
    //   216: dup
    //   217: iload_2
    //   218: aload 10
    //   220: aload 11
    //   222: iload_3
    //   223: iload 4
    //   225: iload 6
    //   227: invokestatic 186	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   230: invokespecial 189	com/sixtostart/zombies5k/orm/models/RunRecord:<init>	(ILjava/util/Date;Ljava/util/Date;IILjava/lang/Boolean;)V
    //   233: invokeinterface 195 2 0
    //   238: pop
    //   239: aload 7
    //   241: astore_1
    //   242: aload 9
    //   244: invokeinterface 198 1 0
    //   249: istore 6
    //   251: iload 6
    //   253: ifne -189 -> 64
    //   256: aload 8
    //   258: astore_1
    //   259: goto +56 -> 315
    //   262: astore 7
    //   264: goto +11 -> 275
    //   267: astore 7
    //   269: aload 7
    //   271: astore_1
    //   272: aload 7
    //   274: athrow
    //   275: aload 9
    //   277: ifnull +35 -> 312
    //   280: aload_1
    //   281: ifnull +24 -> 305
    //   284: aload 9
    //   286: invokeinterface 201 1 0
    //   291: goto +21 -> 312
    //   294: astore 8
    //   296: aload_1
    //   297: aload 8
    //   299: invokevirtual 205	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   302: goto +10 -> 312
    //   305: aload 9
    //   307: invokeinterface 201 1 0
    //   312: aload 7
    //   314: athrow
    //   315: aload_1
    //   316: astore 7
    //   318: aload 9
    //   320: ifnull +31 -> 351
    //   323: aload 9
    //   325: invokeinterface 201 1 0
    //   330: aload_1
    //   331: areturn
    //   332: astore_1
    //   333: ldc 38
    //   335: ldc -44
    //   337: aload_1
    //   338: invokestatic 116	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   341: pop
    //   342: new 141	java/util/ArrayList
    //   345: dup
    //   346: invokespecial 208	java/util/ArrayList:<init>	()V
    //   349: astore 7
    //   351: aload 7
    //   353: areturn
    //   354: iconst_0
    //   355: istore 6
    //   357: goto -149 -> 208
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	360	0	this	MigrationManager
    //   0	360	1	paramContext	Context
    //   83	135	2	i	int
    //   153	70	3	j	int
    //   173	51	4	k	int
    //   194	9	5	m	int
    //   197	159	6	bool	boolean
    //   5	235	7	localUri	android.net.Uri
    //   262	1	7	localObject1	Object
    //   267	46	7	localThrowable1	Throwable
    //   316	36	7	localObject2	Object
    //   49	208	8	localArrayList	ArrayList
    //   294	4	8	localThrowable2	Throwable
    //   20	304	9	localCursor	android.database.Cursor
    //   107	112	10	localDate1	Date
    //   132	89	11	localDate2	Date
    // Exception table:
    //   from	to	target	type
    //   35	51	262	finally
    //   54	64	262	finally
    //   67	84	262	finally
    //   87	109	262	finally
    //   112	134	262	finally
    //   137	154	262	finally
    //   157	175	262	finally
    //   178	196	262	finally
    //   211	239	262	finally
    //   242	251	262	finally
    //   272	275	262	finally
    //   35	51	267	java/lang/Throwable
    //   54	64	267	java/lang/Throwable
    //   67	84	267	java/lang/Throwable
    //   87	109	267	java/lang/Throwable
    //   112	134	267	java/lang/Throwable
    //   137	154	267	java/lang/Throwable
    //   157	175	267	java/lang/Throwable
    //   178	196	267	java/lang/Throwable
    //   211	239	267	java/lang/Throwable
    //   242	251	267	java/lang/Throwable
    //   284	291	294	java/lang/Throwable
    //   7	22	332	java/lang/SecurityException
    //   284	291	332	java/lang/SecurityException
    //   296	302	332	java/lang/SecurityException
    //   305	312	332	java/lang/SecurityException
    //   312	315	332	java/lang/SecurityException
    //   323	330	332	java/lang/SecurityException
  }
  
  /* Error */
  private Pair<Integer, String> getFirstMissionCode(int paramInt)
  {
    // Byte code:
    //   0: ldc 26
    //   2: invokestatic 127	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   5: astore_3
    //   6: aload_0
    //   7: getfield 65	com/sixtostart/zombies5ktraining/legacy/MigrationManager:context	Landroid/content/Context;
    //   10: invokevirtual 133	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   13: astore 4
    //   15: new 99	java/lang/StringBuilder
    //   18: dup
    //   19: invokespecial 100	java/lang/StringBuilder:<init>	()V
    //   22: astore 5
    //   24: aload 5
    //   26: ldc -40
    //   28: invokevirtual 106	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   31: pop
    //   32: aload 5
    //   34: iload_1
    //   35: invokevirtual 219	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   38: pop
    //   39: aload 4
    //   41: aload_3
    //   42: aconst_null
    //   43: aconst_null
    //   44: aconst_null
    //   45: aload 5
    //   47: invokevirtual 110	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   50: invokevirtual 139	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   53: astore 6
    //   55: iconst_0
    //   56: istore_1
    //   57: aconst_null
    //   58: astore 4
    //   60: aload 6
    //   62: ifnull +115 -> 177
    //   65: aload 4
    //   67: astore_3
    //   68: aload 6
    //   70: invokeinterface 154 1 0
    //   75: ifeq +102 -> 177
    //   78: aload 4
    //   80: astore_3
    //   81: aload 6
    //   83: iconst_0
    //   84: invokeinterface 164 2 0
    //   89: istore_1
    //   90: aload 4
    //   92: astore_3
    //   93: aload 6
    //   95: iconst_1
    //   96: invokeinterface 170 2 0
    //   101: astore 5
    //   103: aload 4
    //   105: astore_3
    //   106: aload 6
    //   108: invokeinterface 198 1 0
    //   113: istore_2
    //   114: iload_2
    //   115: ifne -37 -> 78
    //   118: aload 5
    //   120: astore_3
    //   121: goto +58 -> 179
    //   124: astore 4
    //   126: goto +11 -> 137
    //   129: astore 4
    //   131: aload 4
    //   133: astore_3
    //   134: aload 4
    //   136: athrow
    //   137: aload 6
    //   139: ifnull +35 -> 174
    //   142: aload_3
    //   143: ifnull +24 -> 167
    //   146: aload 6
    //   148: invokeinterface 201 1 0
    //   153: goto +21 -> 174
    //   156: astore 5
    //   158: aload_3
    //   159: aload 5
    //   161: invokevirtual 205	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   164: goto +10 -> 174
    //   167: aload 6
    //   169: invokeinterface 201 1 0
    //   174: aload 4
    //   176: athrow
    //   177: aconst_null
    //   178: astore_3
    //   179: aload 6
    //   181: ifnull +10 -> 191
    //   184: aload 6
    //   186: invokeinterface 201 1 0
    //   191: aload_3
    //   192: ifnull +23 -> 215
    //   195: new 221	android/util/Pair
    //   198: dup
    //   199: iload_1
    //   200: invokestatic 226	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   203: aload_3
    //   204: ldc -28
    //   206: ldc -40
    //   208: invokevirtual 234	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   211: invokespecial 237	android/util/Pair:<init>	(Ljava/lang/Object;Ljava/lang/Object;)V
    //   214: areturn
    //   215: aconst_null
    //   216: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	217	0	this	MigrationManager
    //   0	217	1	paramInt	int
    //   113	2	2	bool	boolean
    //   5	199	3	localObject1	Object
    //   13	91	4	localContentResolver	android.content.ContentResolver
    //   124	1	4	localObject2	Object
    //   129	46	4	localThrowable1	Throwable
    //   22	97	5	localObject3	Object
    //   156	4	5	localThrowable2	Throwable
    //   53	132	6	localCursor	android.database.Cursor
    // Exception table:
    //   from	to	target	type
    //   68	78	124	finally
    //   81	90	124	finally
    //   93	103	124	finally
    //   106	114	124	finally
    //   134	137	124	finally
    //   68	78	129	java/lang/Throwable
    //   81	90	129	java/lang/Throwable
    //   93	103	129	java/lang/Throwable
    //   106	114	129	java/lang/Throwable
    //   146	153	156	java/lang/Throwable
  }
  
  private void setMigrationDate(Context paramContext)
  {
    paramContext.getSharedPreferences("MIGRATION_PREF_KEY", 0).edit().putLong("LAST_MIGRATION_KEY", System.currentTimeMillis()).apply();
  }
  
  public Pair<Run, List<RunLocation>> convertFromLegacy(RunRecord paramRunRecord)
  {
    Run localRun = new Run();
    Object localObject2 = ((PlayerRepository)this.playerRepositoryLazy.get()).getCurrentPlayer();
    Object localObject1 = null;
    if ((localObject2 != null) && (((Player)localObject2).getId() != 0L))
    {
      localRun.setPlayerId(Long.valueOf(((Player)localObject2).getId()));
      localRun.setIsAnonymous(Boolean.valueOf(false));
    }
    else
    {
      localRun.setPlayerId(null);
      localRun.setIsAnonymous(Boolean.valueOf(true));
    }
    Object localObject3 = getFirstMissionCode(paramRunRecord.getId());
    if (localObject3 != null)
    {
      localRun.setMission((String)((Pair)localObject3).second);
      localObject2 = getMissionEvents(((Integer)((Pair)localObject3).first).intValue());
      localObject1 = (DocumentBundle)((DocumentBundleRepository)this.documentBundleRepository.get()).getDocumentBundle(localRun.getMission()).blockingFirst();
    }
    else
    {
      localObject2 = null;
    }
    Object localObject4 = new HashMap();
    Object localObject6;
    if ((localObject1 != null) && (((DocumentBundle)localObject1).getScript() != null))
    {
      localObject5 = ((MissionScript)((DocumentBundle)localObject1).getScript()).getScenes();
      if (localObject5 != null)
      {
        localObject5 = ((List)localObject5).iterator();
        while (((Iterator)localObject5).hasNext())
        {
          localObject6 = (SceneScript)((Iterator)localObject5).next();
          ((HashMap)localObject4).put(((SceneScript)localObject6).getIdentifier(), localObject6);
        }
      }
      if (((MissionScript)((DocumentBundle)localObject1).getScript()).getMetadata() != null)
      {
        localRun.setEventName(((MissionScript)((DocumentBundle)localObject1).getScript()).getMetadata().getTitle());
        localRun.setShareText(((MissionScript)((DocumentBundle)localObject1).getScript()).getMetadata().getShareText());
      }
    }
    Object localObject5 = getRunEvents(paramRunRecord.getId());
    int i;
    if (localObject2 != null) {
      i = ((List)localObject2).size();
    } else {
      i = 0;
    }
    int j = i;
    if (localObject5 != null) {
      j = i + ((List)localObject5).size();
    }
    if (j > 0)
    {
      localObject6 = new ArrayList(j);
      if (localObject2 != null) {
        ((List)localObject6).addAll((Collection)localObject2);
      }
      if (localObject5 != null) {
        ((List)localObject6).addAll((Collection)localObject5);
      }
      localObject2 = ((List)localObject6).iterator();
      while (((Iterator)localObject2).hasNext())
      {
        localObject5 = RunEvent.upgrade((Event)((Iterator)localObject2).next());
        if (((Event)localObject5).getTime() != null)
        {
          if ((localObject5 instanceof MissionPausedEvent))
          {
            localObject6 = new PauseEvent();
            ((PauseEvent)localObject6).setStartTime(((Event)localObject5).getTime());
            ((PauseEvent)localObject6).setEndTime(new Date(((PauseEvent)localObject6).getStartTime().getTime() + (((MissionPausedEvent)localObject5).getDuration() * 1000.0F)));
            localRun.addPauseEvent((PauseEvent)localObject6);
          }
          for (;;)
          {
            break;
            if ((localObject5 instanceof PartStartedEvent))
            {
              localObject6 = new AudioEvent();
              ((AudioEvent)localObject6).setStartTime(((Event)localObject5).getTime());
              ((AudioEvent)localObject6).setEndTime(new Date(((AudioEvent)localObject6).getStartTime().getTime() + 30000L));
              ((AudioEvent)localObject6).setIconId(AudioRunEventInfo.ICON_ID.CLIP);
              if (localObject3 != null)
              {
                Object localObject7 = new StringBuilder();
                ((StringBuilder)localObject7).append((String)((Pair)localObject3).second);
                ((StringBuilder)localObject7).append(String.format("-%02d", new Object[] { Integer.valueOf(((PartStartedEvent)localObject5).getSeq()) }));
                localObject7 = ((StringBuilder)localObject7).toString();
                ((AudioEvent)localObject6).setSceneIdentifier((String)localObject7);
                if (localObject1 != null)
                {
                  localObject7 = (SceneScript)((HashMap)localObject4).get(localObject7);
                  if (localObject7 != null)
                  {
                    ((AudioEvent)localObject6).setPreCompletionTitle(((SceneScript)localObject7).getPreCompletionTitle());
                    ((AudioEvent)localObject6).setPostCompletionTitle(((SceneScript)localObject7).getPostCompletionTitle());
                    ((AudioEvent)localObject6).setCaption(((SceneScript)localObject7).getPostCompletionShortSummary());
                  }
                }
              }
              ((AudioEvent)localObject6).setCompleted(((PartStartedEvent)localObject5).getAudioCompleted());
              localRun.addAudioEvent((AudioEvent)localObject6);
            }
            else if ((localObject5 instanceof NewTrackEvent))
            {
              localObject6 = new AudioEvent();
              ((AudioEvent)localObject6).setStartTime(((Event)localObject5).getTime());
              ((AudioEvent)localObject6).setEndTime(new Date(((AudioEvent)localObject6).getStartTime().getTime() + 30000L));
              ((AudioEvent)localObject6).setIconId(AudioRunEventInfo.ICON_ID.MUSIC);
              localObject5 = (NewTrackEvent)localObject5;
              ((AudioEvent)localObject6).setPreCompletionTitle(((NewTrackEvent)localObject5).getTrackName());
              ((AudioEvent)localObject6).setPostCompletionTitle(((AudioEvent)localObject6).getPreCompletionTitle());
              ((AudioEvent)localObject6).setCaption(((NewTrackEvent)localObject5).getArtistName());
              localRun.addAudioEvent((AudioEvent)localObject6);
            }
          }
        }
      }
    }
    localObject1 = new ArrayList();
    localObject2 = getGPSPosition(paramRunRecord.getId());
    if (localObject2 != null)
    {
      localObject2 = ((List)localObject2).iterator();
      while (((Iterator)localObject2).hasNext())
      {
        localObject3 = (GPSPosition)((Iterator)localObject2).next();
        localObject4 = new RunLocation();
        ((RunLocation)localObject4).setProvider("5k Legacy Provider");
        ((RunLocation)localObject4).setLongitude(Double.valueOf(((GPSPosition)localObject3).getLongitude()));
        ((RunLocation)localObject4).setLatitude(Double.valueOf(((GPSPosition)localObject3).getLatitude()));
        ((RunLocation)localObject4).setAltitude(Double.valueOf(((GPSPosition)localObject3).getAltitude()));
        ((RunLocation)localObject4).setAccuracy(Float.valueOf((float)((GPSPosition)localObject3).getAccuracy()));
        ((RunLocation)localObject4).setTime(Long.valueOf(((GPSPosition)localObject3).getTime().getTime()));
        ((RunLocation)localObject4).setRunLocalId(localRun.getLocalId());
        ((List)localObject1).add(localObject4);
      }
    }
    localRun.setStartTime(paramRunRecord.getStarted());
    localRun.setEndTime(paramRunRecord.getEnded());
    localRun.setSteps(paramRunRecord.getPaces());
    if (localRun.getStartTime() == null) {
      if (((List)localObject1).isEmpty()) {
        localRun.setStartTime(new Date(0L));
      } else {
        localRun.setStartTime(new Date(((RunLocation)((List)localObject1).get(0)).getTime().longValue()));
      }
    }
    if (localRun.getEndTime() == null) {
      if (((List)localObject1).isEmpty()) {
        localRun.setEndTime(new Date(localRun.getStartTime().getTime() + 1L));
      } else {
        localRun.setEndTime(new Date(((RunLocation)((List)localObject1).get(((List)localObject1).size() - 1)).getTime().longValue()));
      }
    }
    localRun.setTotalTime(new TimeSpan(TimeUnit.MILLISECONDS, localRun.getEndTime().getTime() - localRun.getStartTime().getTime()));
    localRun.setTotalDistance(new DistanceSpan(DistanceUnit.METERS, paramRunRecord.getDistance()));
    return new Pair(localRun, localObject1);
  }
  
  public boolean doesContentProviderExists(Context paramContext)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(8).iterator();
    for (;;)
    {
      boolean bool = paramContext.hasNext();
      int i = 0;
      if (!bool) {
        break;
      }
      ProviderInfo[] arrayOfProviderInfo = ((PackageInfo)paramContext.next()).providers;
      if (arrayOfProviderInfo != null)
      {
        int j = arrayOfProviderInfo.length;
        while (i < j)
        {
          if ("com.sixtostart.zombies5k.LegacyContentProvider".equalsIgnoreCase(arrayOfProviderInfo[i].authority)) {
            return true;
          }
          i += 1;
        }
      }
    }
    return false;
  }
  
  /* Error */
  public List<GPSPosition> getGPSPosition(int paramInt)
  {
    // Byte code:
    //   0: ldc 16
    //   2: invokestatic 127	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   5: astore 5
    //   7: aload_0
    //   8: getfield 65	com/sixtostart/zombies5ktraining/legacy/MigrationManager:context	Landroid/content/Context;
    //   11: invokevirtual 133	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   14: astore 6
    //   16: new 99	java/lang/StringBuilder
    //   19: dup
    //   20: invokespecial 100	java/lang/StringBuilder:<init>	()V
    //   23: astore 7
    //   25: aload 7
    //   27: ldc -40
    //   29: invokevirtual 106	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   32: pop
    //   33: aload 7
    //   35: iload_1
    //   36: invokevirtual 219	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   39: pop
    //   40: aload 6
    //   42: aload 5
    //   44: aconst_null
    //   45: aconst_null
    //   46: aconst_null
    //   47: aload 7
    //   49: invokevirtual 110	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   52: invokevirtual 139	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   55: astore 8
    //   57: aconst_null
    //   58: astore 5
    //   60: aconst_null
    //   61: astore 6
    //   63: aload 8
    //   65: ifnull +232 -> 297
    //   68: aload 6
    //   70: astore 5
    //   72: new 141	java/util/ArrayList
    //   75: dup
    //   76: aload 8
    //   78: invokeinterface 147 1 0
    //   83: invokespecial 150	java/util/ArrayList:<init>	(I)V
    //   86: astore 7
    //   88: aload 6
    //   90: astore 5
    //   92: aload 8
    //   94: invokeinterface 154 1 0
    //   99: ifeq +135 -> 234
    //   102: aload 6
    //   104: astore 5
    //   106: aload 8
    //   108: aload 8
    //   110: ldc_w 667
    //   113: invokeinterface 160 2 0
    //   118: invokeinterface 671 2 0
    //   123: dstore_2
    //   124: aload 6
    //   126: astore 5
    //   128: aload 7
    //   130: new 521	com/sixtostart/zombies5k/orm/models/GPSPosition
    //   133: dup
    //   134: aload 8
    //   136: aload 8
    //   138: ldc_w 673
    //   141: invokeinterface 160 2 0
    //   146: invokeinterface 671 2 0
    //   151: aload 8
    //   153: aload 8
    //   155: ldc_w 675
    //   158: invokeinterface 160 2 0
    //   163: invokeinterface 671 2 0
    //   168: aload 8
    //   170: aload 8
    //   172: ldc_w 677
    //   175: invokeinterface 160 2 0
    //   180: invokeinterface 671 2 0
    //   185: dload_2
    //   186: aload_0
    //   187: aload 8
    //   189: aload 8
    //   191: ldc_w 679
    //   194: invokeinterface 160 2 0
    //   199: invokeinterface 170 2 0
    //   204: invokespecial 172	com/sixtostart/zombies5ktraining/legacy/MigrationManager:convertDate	(Ljava/lang/String;)Ljava/util/Date;
    //   207: invokespecial 682	com/sixtostart/zombies5k/orm/models/GPSPosition:<init>	(DDDDLjava/util/Date;)V
    //   210: invokeinterface 195 2 0
    //   215: pop
    //   216: aload 6
    //   218: astore 5
    //   220: aload 8
    //   222: invokeinterface 198 1 0
    //   227: istore 4
    //   229: iload 4
    //   231: ifne -129 -> 102
    //   234: aload 7
    //   236: astore 5
    //   238: goto +59 -> 297
    //   241: astore 6
    //   243: goto +12 -> 255
    //   246: astore 6
    //   248: aload 6
    //   250: astore 5
    //   252: aload 6
    //   254: athrow
    //   255: aload 8
    //   257: ifnull +37 -> 294
    //   260: aload 5
    //   262: ifnull +25 -> 287
    //   265: aload 8
    //   267: invokeinterface 201 1 0
    //   272: goto +22 -> 294
    //   275: astore 7
    //   277: aload 5
    //   279: aload 7
    //   281: invokevirtual 205	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   284: goto +10 -> 294
    //   287: aload 8
    //   289: invokeinterface 201 1 0
    //   294: aload 6
    //   296: athrow
    //   297: aload 8
    //   299: ifnull +10 -> 309
    //   302: aload 8
    //   304: invokeinterface 201 1 0
    //   309: aload 5
    //   311: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	312	0	this	MigrationManager
    //   0	312	1	paramInt	int
    //   123	63	2	d	double
    //   227	3	4	bool	boolean
    //   5	305	5	localObject1	Object
    //   14	203	6	localContentResolver	android.content.ContentResolver
    //   241	1	6	localObject2	Object
    //   246	49	6	localThrowable1	Throwable
    //   23	212	7	localObject3	Object
    //   275	5	7	localThrowable2	Throwable
    //   55	248	8	localCursor	android.database.Cursor
    // Exception table:
    //   from	to	target	type
    //   72	88	241	finally
    //   92	102	241	finally
    //   106	124	241	finally
    //   128	216	241	finally
    //   220	229	241	finally
    //   252	255	241	finally
    //   72	88	246	java/lang/Throwable
    //   92	102	246	java/lang/Throwable
    //   106	124	246	java/lang/Throwable
    //   128	216	246	java/lang/Throwable
    //   220	229	246	java/lang/Throwable
    //   265	272	275	java/lang/Throwable
  }
  
  public Date getLastMigrationDate(Context paramContext)
  {
    return new Date(paramContext.getSharedPreferences("MIGRATION_PREF_KEY", 0).getLong("LAST_MIGRATION_KEY", 0L));
  }
  
  /* Error */
  public List<Event> getMissionEvents(int paramInt)
  {
    // Byte code:
    //   0: ldc 29
    //   2: invokestatic 127	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   5: astore 9
    //   7: aload_0
    //   8: getfield 65	com/sixtostart/zombies5ktraining/legacy/MigrationManager:context	Landroid/content/Context;
    //   11: invokevirtual 133	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   14: astore 10
    //   16: new 99	java/lang/StringBuilder
    //   19: dup
    //   20: invokespecial 100	java/lang/StringBuilder:<init>	()V
    //   23: astore 11
    //   25: aload 11
    //   27: ldc -40
    //   29: invokevirtual 106	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   32: pop
    //   33: aload 11
    //   35: iload_1
    //   36: invokevirtual 219	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   39: pop
    //   40: aload 10
    //   42: aload 9
    //   44: aconst_null
    //   45: aconst_null
    //   46: aconst_null
    //   47: aload 11
    //   49: invokevirtual 110	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   52: invokevirtual 139	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   55: astore 12
    //   57: aconst_null
    //   58: astore 9
    //   60: aconst_null
    //   61: astore 10
    //   63: aload 12
    //   65: ifnull +422 -> 487
    //   68: aload 10
    //   70: astore 9
    //   72: new 141	java/util/ArrayList
    //   75: dup
    //   76: aload 12
    //   78: invokeinterface 147 1 0
    //   83: invokespecial 150	java/util/ArrayList:<init>	(I)V
    //   86: astore 11
    //   88: aload 10
    //   90: astore 9
    //   92: aload 12
    //   94: invokeinterface 154 1 0
    //   99: ifeq +325 -> 424
    //   102: aload 10
    //   104: astore 9
    //   106: aload 12
    //   108: aload 12
    //   110: ldc_w 691
    //   113: invokeinterface 160 2 0
    //   118: invokeinterface 164 2 0
    //   123: istore_1
    //   124: aload 10
    //   126: astore 9
    //   128: aload 12
    //   130: aload 12
    //   132: ldc_w 693
    //   135: invokeinterface 160 2 0
    //   140: invokeinterface 697 2 0
    //   145: fstore_2
    //   146: aload 10
    //   148: astore 9
    //   150: aload 12
    //   152: aload 12
    //   154: ldc_w 699
    //   157: invokeinterface 160 2 0
    //   162: invokeinterface 697 2 0
    //   167: fstore_3
    //   168: aload 10
    //   170: astore 9
    //   172: aload 12
    //   174: aload 12
    //   176: ldc_w 701
    //   179: invokeinterface 160 2 0
    //   184: invokeinterface 697 2 0
    //   189: fstore 4
    //   191: aload 10
    //   193: astore 9
    //   195: aload 12
    //   197: aload 12
    //   199: ldc_w 703
    //   202: invokeinterface 160 2 0
    //   207: invokeinterface 697 2 0
    //   212: fstore 5
    //   214: aload 10
    //   216: astore 9
    //   218: aload 12
    //   220: aload 12
    //   222: ldc_w 705
    //   225: invokeinterface 160 2 0
    //   230: invokeinterface 164 2 0
    //   235: istore 6
    //   237: aload 10
    //   239: astore 9
    //   241: aload 12
    //   243: aload 12
    //   245: ldc_w 707
    //   248: invokeinterface 160 2 0
    //   253: invokeinterface 164 2 0
    //   258: istore 7
    //   260: aload 10
    //   262: astore 9
    //   264: aload 12
    //   266: aload 12
    //   268: ldc_w 709
    //   271: invokeinterface 160 2 0
    //   276: invokeinterface 170 2 0
    //   281: astore 13
    //   283: aload 10
    //   285: astore 9
    //   287: aload 12
    //   289: aload 12
    //   291: ldc_w 711
    //   294: invokeinterface 160 2 0
    //   299: invokeinterface 164 2 0
    //   304: iconst_1
    //   305: if_icmpne +197 -> 502
    //   308: iconst_1
    //   309: istore 8
    //   311: goto +3 -> 314
    //   314: aload 10
    //   316: astore 9
    //   318: aload 11
    //   320: new 713	com/sixtostart/zombies5k/orm/models/MissionEvent
    //   323: dup
    //   324: iload_1
    //   325: fload_2
    //   326: fload_3
    //   327: fload 4
    //   329: fload 5
    //   331: iload 6
    //   333: iload 7
    //   335: aload 13
    //   337: iload 8
    //   339: aload_0
    //   340: aload 12
    //   342: aload 12
    //   344: ldc_w 715
    //   347: invokeinterface 160 2 0
    //   352: invokeinterface 170 2 0
    //   357: invokespecial 172	com/sixtostart/zombies5ktraining/legacy/MigrationManager:convertDate	(Ljava/lang/String;)Ljava/util/Date;
    //   360: aload 12
    //   362: aload 12
    //   364: ldc -100
    //   366: invokeinterface 160 2 0
    //   371: invokeinterface 164 2 0
    //   376: aload_0
    //   377: aload 12
    //   379: aload 12
    //   381: ldc_w 679
    //   384: invokeinterface 160 2 0
    //   389: invokeinterface 170 2 0
    //   394: invokespecial 172	com/sixtostart/zombies5ktraining/legacy/MigrationManager:convertDate	(Ljava/lang/String;)Ljava/util/Date;
    //   397: invokespecial 718	com/sixtostart/zombies5k/orm/models/MissionEvent:<init>	(IFFFFIILjava/lang/String;ZLjava/util/Date;ILjava/util/Date;)V
    //   400: invokeinterface 195 2 0
    //   405: pop
    //   406: aload 10
    //   408: astore 9
    //   410: aload 12
    //   412: invokeinterface 198 1 0
    //   417: istore 8
    //   419: iload 8
    //   421: ifne -319 -> 102
    //   424: aload 11
    //   426: astore 9
    //   428: goto +59 -> 487
    //   431: astore 10
    //   433: goto +12 -> 445
    //   436: astore 10
    //   438: aload 10
    //   440: astore 9
    //   442: aload 10
    //   444: athrow
    //   445: aload 12
    //   447: ifnull +37 -> 484
    //   450: aload 9
    //   452: ifnull +25 -> 477
    //   455: aload 12
    //   457: invokeinterface 201 1 0
    //   462: goto +22 -> 484
    //   465: astore 11
    //   467: aload 9
    //   469: aload 11
    //   471: invokevirtual 205	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   474: goto +10 -> 484
    //   477: aload 12
    //   479: invokeinterface 201 1 0
    //   484: aload 10
    //   486: athrow
    //   487: aload 12
    //   489: ifnull +10 -> 499
    //   492: aload 12
    //   494: invokeinterface 201 1 0
    //   499: aload 9
    //   501: areturn
    //   502: iconst_0
    //   503: istore 8
    //   505: goto -191 -> 314
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	508	0	this	MigrationManager
    //   0	508	1	paramInt	int
    //   145	181	2	f1	float
    //   167	160	3	f2	float
    //   189	139	4	f3	float
    //   212	118	5	f4	float
    //   235	97	6	i	int
    //   258	76	7	j	int
    //   309	195	8	bool	boolean
    //   5	495	9	localObject1	Object
    //   14	393	10	localContentResolver	android.content.ContentResolver
    //   431	1	10	localObject2	Object
    //   436	49	10	localThrowable1	Throwable
    //   23	402	11	localObject3	Object
    //   465	5	11	localThrowable2	Throwable
    //   55	438	12	localCursor	android.database.Cursor
    //   281	55	13	str	String
    // Exception table:
    //   from	to	target	type
    //   72	88	431	finally
    //   92	102	431	finally
    //   106	124	431	finally
    //   128	146	431	finally
    //   150	168	431	finally
    //   172	191	431	finally
    //   195	214	431	finally
    //   218	237	431	finally
    //   241	260	431	finally
    //   264	283	431	finally
    //   287	308	431	finally
    //   318	406	431	finally
    //   410	419	431	finally
    //   442	445	431	finally
    //   72	88	436	java/lang/Throwable
    //   92	102	436	java/lang/Throwable
    //   106	124	436	java/lang/Throwable
    //   128	146	436	java/lang/Throwable
    //   150	168	436	java/lang/Throwable
    //   172	191	436	java/lang/Throwable
    //   195	214	436	java/lang/Throwable
    //   218	237	436	java/lang/Throwable
    //   241	260	436	java/lang/Throwable
    //   264	283	436	java/lang/Throwable
    //   287	308	436	java/lang/Throwable
    //   318	406	436	java/lang/Throwable
    //   410	419	436	java/lang/Throwable
    //   455	462	465	java/lang/Throwable
  }
  
  /* Error */
  public List<Event> getRunEvents(int paramInt)
  {
    // Byte code:
    //   0: ldc 35
    //   2: invokestatic 127	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   5: astore 8
    //   7: aload_0
    //   8: getfield 65	com/sixtostart/zombies5ktraining/legacy/MigrationManager:context	Landroid/content/Context;
    //   11: invokevirtual 133	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   14: astore 9
    //   16: new 99	java/lang/StringBuilder
    //   19: dup
    //   20: invokespecial 100	java/lang/StringBuilder:<init>	()V
    //   23: astore 10
    //   25: aload 10
    //   27: ldc -40
    //   29: invokevirtual 106	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   32: pop
    //   33: aload 10
    //   35: iload_1
    //   36: invokevirtual 219	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   39: pop
    //   40: aload 9
    //   42: aload 8
    //   44: aconst_null
    //   45: aconst_null
    //   46: aconst_null
    //   47: aload 10
    //   49: invokevirtual 110	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   52: invokevirtual 139	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   55: astore 11
    //   57: aconst_null
    //   58: astore 8
    //   60: aconst_null
    //   61: astore 9
    //   63: aload 11
    //   65: ifnull +422 -> 487
    //   68: aload 9
    //   70: astore 8
    //   72: new 141	java/util/ArrayList
    //   75: dup
    //   76: aload 11
    //   78: invokeinterface 147 1 0
    //   83: invokespecial 150	java/util/ArrayList:<init>	(I)V
    //   86: astore 10
    //   88: aload 9
    //   90: astore 8
    //   92: aload 11
    //   94: invokeinterface 154 1 0
    //   99: ifeq +325 -> 424
    //   102: aload 9
    //   104: astore 8
    //   106: aload 11
    //   108: aload 11
    //   110: ldc_w 691
    //   113: invokeinterface 160 2 0
    //   118: invokeinterface 164 2 0
    //   123: istore_1
    //   124: aload 9
    //   126: astore 8
    //   128: aload 11
    //   130: aload 11
    //   132: ldc_w 693
    //   135: invokeinterface 160 2 0
    //   140: invokeinterface 697 2 0
    //   145: fstore_2
    //   146: aload 9
    //   148: astore 8
    //   150: aload 11
    //   152: aload 11
    //   154: ldc_w 699
    //   157: invokeinterface 160 2 0
    //   162: invokeinterface 697 2 0
    //   167: fstore_3
    //   168: aload 9
    //   170: astore 8
    //   172: aload 11
    //   174: aload 11
    //   176: ldc_w 701
    //   179: invokeinterface 160 2 0
    //   184: invokeinterface 697 2 0
    //   189: fstore 4
    //   191: aload 9
    //   193: astore 8
    //   195: aload 11
    //   197: aload 11
    //   199: ldc_w 703
    //   202: invokeinterface 160 2 0
    //   207: invokeinterface 697 2 0
    //   212: fstore 5
    //   214: aload 9
    //   216: astore 8
    //   218: aload 11
    //   220: aload 11
    //   222: ldc_w 705
    //   225: invokeinterface 160 2 0
    //   230: invokeinterface 164 2 0
    //   235: istore 6
    //   237: aload 9
    //   239: astore 8
    //   241: aload 11
    //   243: aload 11
    //   245: ldc_w 709
    //   248: invokeinterface 160 2 0
    //   253: invokeinterface 170 2 0
    //   258: astore 12
    //   260: aload 9
    //   262: astore 8
    //   264: aload 11
    //   266: aload 11
    //   268: ldc_w 721
    //   271: invokeinterface 160 2 0
    //   276: invokeinterface 170 2 0
    //   281: astore 13
    //   283: aload 9
    //   285: astore 8
    //   287: aload 11
    //   289: aload 11
    //   291: ldc_w 711
    //   294: invokeinterface 160 2 0
    //   299: invokeinterface 164 2 0
    //   304: iconst_1
    //   305: if_icmpne +197 -> 502
    //   308: iconst_1
    //   309: istore 7
    //   311: goto +3 -> 314
    //   314: aload 9
    //   316: astore 8
    //   318: aload 10
    //   320: new 401	com/sixtostart/zombies5k/orm/models/RunEvent
    //   323: dup
    //   324: iload_1
    //   325: fload_2
    //   326: fload_3
    //   327: fload 4
    //   329: fload 5
    //   331: iload 6
    //   333: aload 12
    //   335: aload 13
    //   337: iload 7
    //   339: aload_0
    //   340: aload 11
    //   342: aload 11
    //   344: ldc_w 715
    //   347: invokeinterface 160 2 0
    //   352: invokeinterface 170 2 0
    //   357: invokespecial 172	com/sixtostart/zombies5ktraining/legacy/MigrationManager:convertDate	(Ljava/lang/String;)Ljava/util/Date;
    //   360: aload 11
    //   362: aload 11
    //   364: ldc -100
    //   366: invokeinterface 160 2 0
    //   371: invokeinterface 164 2 0
    //   376: aload_0
    //   377: aload 11
    //   379: aload 11
    //   381: ldc_w 679
    //   384: invokeinterface 160 2 0
    //   389: invokeinterface 170 2 0
    //   394: invokespecial 172	com/sixtostart/zombies5ktraining/legacy/MigrationManager:convertDate	(Ljava/lang/String;)Ljava/util/Date;
    //   397: invokespecial 724	com/sixtostart/zombies5k/orm/models/RunEvent:<init>	(IFFFFILjava/lang/String;Ljava/lang/String;ZLjava/util/Date;ILjava/util/Date;)V
    //   400: invokeinterface 195 2 0
    //   405: pop
    //   406: aload 9
    //   408: astore 8
    //   410: aload 11
    //   412: invokeinterface 198 1 0
    //   417: istore 7
    //   419: iload 7
    //   421: ifne -319 -> 102
    //   424: aload 10
    //   426: astore 8
    //   428: goto +59 -> 487
    //   431: astore 9
    //   433: goto +12 -> 445
    //   436: astore 9
    //   438: aload 9
    //   440: astore 8
    //   442: aload 9
    //   444: athrow
    //   445: aload 11
    //   447: ifnull +37 -> 484
    //   450: aload 8
    //   452: ifnull +25 -> 477
    //   455: aload 11
    //   457: invokeinterface 201 1 0
    //   462: goto +22 -> 484
    //   465: astore 10
    //   467: aload 8
    //   469: aload 10
    //   471: invokevirtual 205	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   474: goto +10 -> 484
    //   477: aload 11
    //   479: invokeinterface 201 1 0
    //   484: aload 9
    //   486: athrow
    //   487: aload 11
    //   489: ifnull +10 -> 499
    //   492: aload 11
    //   494: invokeinterface 201 1 0
    //   499: aload 8
    //   501: areturn
    //   502: iconst_0
    //   503: istore 7
    //   505: goto -191 -> 314
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	508	0	this	MigrationManager
    //   0	508	1	paramInt	int
    //   145	181	2	f1	float
    //   167	160	3	f2	float
    //   189	139	4	f3	float
    //   212	118	5	f4	float
    //   235	97	6	i	int
    //   309	195	7	bool	boolean
    //   5	495	8	localObject1	Object
    //   14	393	9	localContentResolver	android.content.ContentResolver
    //   431	1	9	localObject2	Object
    //   436	49	9	localThrowable1	Throwable
    //   23	402	10	localObject3	Object
    //   465	5	10	localThrowable2	Throwable
    //   55	438	11	localCursor	android.database.Cursor
    //   258	76	12	str1	String
    //   281	55	13	str2	String
    // Exception table:
    //   from	to	target	type
    //   72	88	431	finally
    //   92	102	431	finally
    //   106	124	431	finally
    //   128	146	431	finally
    //   150	168	431	finally
    //   172	191	431	finally
    //   195	214	431	finally
    //   218	237	431	finally
    //   241	260	431	finally
    //   264	283	431	finally
    //   287	308	431	finally
    //   318	406	431	finally
    //   410	419	431	finally
    //   442	445	431	finally
    //   72	88	436	java/lang/Throwable
    //   92	102	436	java/lang/Throwable
    //   106	124	436	java/lang/Throwable
    //   128	146	436	java/lang/Throwable
    //   150	168	436	java/lang/Throwable
    //   172	191	436	java/lang/Throwable
    //   195	214	436	java/lang/Throwable
    //   218	237	436	java/lang/Throwable
    //   241	260	436	java/lang/Throwable
    //   264	283	436	java/lang/Throwable
    //   287	308	436	java/lang/Throwable
    //   318	406	436	java/lang/Throwable
    //   410	419	436	java/lang/Throwable
    //   455	462	465	java/lang/Throwable
  }
  
  public boolean isAlreadyMigrated(RunRecord paramRunRecord)
  {
    return ((RunRepository)this.runRepository.get()).firstWhereSync(paramRunRecord.getStarted(), paramRunRecord.getEnded()) != null;
  }
  
  public boolean isLegacyAppInstalled()
  {
    Iterator localIterator = this.context.getPackageManager().getInstalledApplications(128).iterator();
    while (localIterator.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
      if ((localApplicationInfo.enabled) && ("com.sixtostart.zombies5k".equals(localApplicationInfo.packageName))) {
        return true;
      }
    }
    return false;
  }
  
  public int migrateAllRuns(Context paramContext)
  {
    int j = 0;
    int i = 0;
    try
    {
      boolean bool = doesContentProviderExists(paramContext);
      if (!bool) {
        return 0;
      }
      Object localObject1 = getAllRunRecords(paramContext);
      if (localObject1 != null)
      {
        localObject1 = ((List)localObject1).iterator();
        for (;;)
        {
          j = i;
          if (!((Iterator)localObject1).hasNext()) {
            break;
          }
          Object localObject2 = (RunRecord)((Iterator)localObject1).next();
          if (!isAlreadyMigrated((RunRecord)localObject2))
          {
            localObject2 = convertFromLegacy((RunRecord)localObject2);
            ((RunRepository)this.runRepository.get()).save((Run)((Pair)localObject2).first).blockingGet();
            ((RunLocationRepository)this.runLocationRepository.get()).addLocations((List)((Pair)localObject2).second).blockingAwait();
            i += 1;
          }
        }
      }
      setMigrationDate(paramContext);
      return j;
    }
    finally {}
  }
  
  public int migrateAnonRuns(Context paramContext)
  {
    int i = 0;
    int j = 0;
    try
    {
      boolean bool = doesContentProviderExists(paramContext);
      if (!bool) {
        return 0;
      }
      Date localDate = getLastMigrationDate(paramContext);
      Object localObject1 = getAllUnsynchedAnonymousRunRecords(paramContext);
      if (localObject1 != null)
      {
        localObject1 = ((List)localObject1).iterator();
        i = j;
        while (((Iterator)localObject1).hasNext())
        {
          Object localObject2 = (RunRecord)((Iterator)localObject1).next();
          if ((((RunRecord)localObject2).getStarted() != null) && (localDate.before(((RunRecord)localObject2).getStarted())) && (!isAlreadyMigrated((RunRecord)localObject2)))
          {
            localObject2 = convertFromLegacy((RunRecord)localObject2);
            ((RunRepository)this.runRepository.get()).save((Run)((Pair)localObject2).first).blockingGet();
            ((RunLocationRepository)this.runLocationRepository.get()).addLocations((List)((Pair)localObject2).second).blockingAwait();
            i += 1;
          }
        }
        setMigrationDate(paramContext);
      }
      return i;
    }
    finally {}
  }
}
