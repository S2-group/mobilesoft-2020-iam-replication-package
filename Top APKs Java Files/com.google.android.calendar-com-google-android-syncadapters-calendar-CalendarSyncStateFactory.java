package com.google.android.syncadapters.calendar;

import android.accounts.Account;
import android.content.ContentProviderClient;
import android.content.ContentProviderOperation;
import android.content.ContentProviderOperation.Builder;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Entity;
import android.content.EntityIterator;
import android.content.SyncResult;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.CalendarContract.Calendars;
import android.provider.CalendarContract.Events;
import android.provider.CalendarContract.SyncState;
import android.provider.SyncStateContract.Helpers;
import android.util.Pair;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apiary.ParseException;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.apps.calendar.syncadapters.timely.sql.ColumnConstants;
import com.google.android.apps.calendar.syncadapters.timely.sql.SQLiteDatabaseUtils;
import com.google.android.calendar.api.habit.HabitIdTypeUtil;
import com.google.android.calendar.api.habit.HabitSyncUtils;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.gsf.Gservices;
import com.google.api.client.googleapis.services.AbstractGoogleClient;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.http.HttpResponseException;
import com.google.api.services.calendar.Calendar.Events;
import com.google.api.services.calendar.Calendar.Events.Get;
import com.google.api.services.calendar.Calendar.Habits;
import com.google.api.services.calendar.Calendar.Habits.List;
import com.google.api.services.calendar.CalendarRequest;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Habit;
import com.google.api.services.calendar.model.HabitData;
import com.google.api.services.calendar.model.Habits;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class CalendarSyncStateFactory
{
  private static final String[] QUERY_PROJECTION = { "_id", "sync_events", "cal_sync1", "cal_sync4", "cal_sync5" };
  private static final String TAG = LogUtils.getLogTag(CalendarSyncStateFactory.class);
  
  public CalendarSyncStateFactory() {}
  
  private static void addTimelyDataToExistingEvents(CalendarSyncAdapterApiary paramCalendarSyncAdapterApiary, ContentProviderClient paramContentProviderClient, Account paramAccount, CalendarSyncState paramCalendarSyncState)
    throws RemoteException, IOException, ParseException
  {
    Cursor localCursor = ProviderHelper.asSyncAdapter(paramAccount).query(paramContentProviderClient, CalendarContract.Calendars.CONTENT_URI, new String[] { "_id", "ownerAccount" }, "sync_events=?", new String[] { "1" }, null);
    String[] arrayOfString = new String[2];
    if (Clock.mockedTimestamp > 0L) {}
    for (long l = Clock.mockedTimestamp;; l = System.currentTimeMillis())
    {
      arrayOfString[0] = String.valueOf(l);
      if (localCursor == null) {
        return;
      }
      try
      {
        String str;
        TimeRange localTimeRange;
        do
        {
          if (!localCursor.moveToNext()) {
            break;
          }
          l = localCursor.getLong(0);
          str = localCursor.getString(1);
          arrayOfString[1] = String.valueOf(l);
          localTimeRange = Utilities.getEventsRange(paramContentProviderClient, ColumnConstants.WHERE_DT_START_GT_AND_EVENTS_CALENDAR_ID, arrayOfString);
        } while ((localTimeRange == null) || (paramCalendarSyncAdapterApiary.saveTimelyDataForEventRange(paramContentProviderClient, paramAccount, str, l, localTimeRange, paramCalendarSyncState)));
        throw new IOException("The upgrade was cancelled.");
      }
      finally
      {
        localCursor.close();
      }
    }
    localCursor.close();
  }
  
  private static CalendarSyncState create(ContentProviderClient paramContentProviderClient, Account paramAccount, Context paramContext)
    throws RemoteException, ParseException
  {
    ContentValues localContentValues = new ContentValues();
    CalendarSyncState localCalendarSyncState = new CalendarSyncState(15);
    paramContext = paramContext.getApplicationContext().getPackageName();
    try
    {
      localCalendarSyncState.data.put("package", paramContext);
      localContentValues.put("data", localCalendarSyncState.data.toString().getBytes());
      localContentValues.put("account_name", paramAccount.name);
      localContentValues.put("account_type", paramAccount.type);
      return new CalendarSyncState(ProviderHelper.asSyncAdapter(paramAccount).insert(paramContentProviderClient, CalendarContract.SyncState.CONTENT_URI, localContentValues), localCalendarSyncState);
    }
    catch (JSONException paramContext)
    {
      for (;;)
      {
        LogUtils.e("CalendarSyncAdapter", paramContext, "Failed to set syncing package.", new Object[0]);
      }
    }
  }
  
  private static Method findUpgradeMethod(int paramInt)
  {
    try
    {
      Method localMethod = CalendarSyncStateFactory.class.getMethod(22 + "upgradeFrom" + paramInt, new Class[] { CalendarSyncAdapterApiary.class, CalendarSyncState.class, Context.class, ContentProviderClient.class, Account.class });
      return localMethod;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      throw new IllegalStateException(41 + "Missing upgrade from version: " + paramInt, localNoSuchMethodException);
    }
  }
  
  public static CalendarSyncState getOrCreate(CalendarSyncAdapterApiary paramCalendarSyncAdapterApiary, Context paramContext, ContentProviderClient paramContentProviderClient, Account paramAccount)
    throws RemoteException, IOException, ParseException
  {
    Object localObject1 = SyncStateContract.Helpers.getWithUri(paramContentProviderClient, CalendarContract.SyncState.CONTENT_URI, paramAccount);
    if (localObject1 == null) {
      return create(paramContentProviderClient, paramAccount, paramContext);
    }
    localObject1 = CalendarSyncStateUtils.fromBytes(ProviderHelper.toAsSyncAdapterUri((Uri)((Pair)localObject1).first, paramAccount), (byte[])((Pair)localObject1).second, paramContentProviderClient, paramAccount);
    if (localObject1 == null)
    {
      LogUtils.w(TAG, "Can't upgrade, wipe and resync", new Object[0]);
      LogUtils.i(TAG, "Upgrading to Apiary Sync Adapter", new Object[0]);
      wipeEventsAndCalendars(paramContext, paramContentProviderClient, paramAccount);
      LogUtils.d(TAG, "Requesting full sync in CalendarSyncState#getSyncStateForWipeAndResync", new Object[0]);
      paramCalendarSyncAdapterApiary = new Bundle();
      localObject1 = Features.instance;
      if (localObject1 == null) {
        throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
      }
      ((FeatureConfig)localObject1).fishfood_debug();
      ContentResolver.requestSync(paramAccount, "com.android.calendar", paramCalendarSyncAdapterApiary);
      return create(paramContentProviderClient, paramAccount, paramContext);
    }
    ((CalendarSyncState)localObject1).originalVersion = ((CalendarSyncState)localObject1).getVersion();
    for (int i = ((CalendarSyncState)localObject1).originalVersion; i < 15; i = upgradeFrom(i, paramCalendarSyncAdapterApiary, (CalendarSyncState)localObject1, paramContext, paramContentProviderClient, paramAccount)) {}
    if (i > 15)
    {
      LogUtils.w(TAG, "Wipe Data on Downgrade from %d to %d", new Object[] { Integer.valueOf(i), Integer.valueOf(15) });
      wipeEventsAndCalendars(paramContext, paramContentProviderClient, paramAccount);
      ((CalendarSyncState)localObject1).reset();
      LogUtils.d(TAG, "Requesting full sync in CalendarSyncState#forceFullSync", new Object[0]);
      localObject2 = new Bundle();
      localObject3 = Features.instance;
      if (localObject3 == null) {
        throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
      }
      ((FeatureConfig)localObject3).fishfood_debug();
      ContentResolver.requestSync(paramAccount, "com.android.calendar", (Bundle)localObject2);
      ((CalendarSyncState)localObject1).setVersion(15);
      SyncStateContract.Helpers.update(paramContentProviderClient, ((CalendarSyncState)localObject1).uri, ((CalendarSyncState)localObject1).data.toString().getBytes());
    }
    if (!((CalendarSyncState)localObject1).isJellyBean())
    {
      CalendarSyncStateUtils.transformSyncIds(paramContentProviderClient, paramAccount, "%\n%", new CalendarSyncStateFactory.1());
      ((CalendarSyncState)localObject1).setJellyBean(true);
      SyncStateContract.Helpers.update(paramContentProviderClient, ((CalendarSyncState)localObject1).uri, ((CalendarSyncState)localObject1).data.toString().getBytes());
    }
    Object localObject2 = ((CalendarSyncState)localObject1).getSyncingPackage();
    Object localObject3 = paramContext.getApplicationContext().getPackageName();
    if ((((String)localObject3).equals(localObject2)) || (("com.google.android.syncadapters.calendar".equals(localObject2)) && ("com.google.android.calendar".equals(localObject3)))) {}
    try
    {
      addTimelyDataToExistingEvents(paramCalendarSyncAdapterApiary, paramContentProviderClient, paramAccount, (CalendarSyncState)localObject1);
      ((CalendarSyncState)localObject1).setSyncingPackage((String)localObject3);
      SyncStateContract.Helpers.update(paramContentProviderClient, ((CalendarSyncState)localObject1).uri, ((CalendarSyncState)localObject1).data.toString().getBytes());
      HtcMailIssueResyncTrigger.process((CalendarSyncState)localObject1, paramContext, paramContentProviderClient, paramAccount);
      return localObject1;
    }
    catch (ParseException paramCalendarSyncAdapterApiary)
    {
      throw new IllegalStateException("Failed to upgrade package", paramCalendarSyncAdapterApiary);
    }
  }
  
  /* Error */
  private static int upgradeFrom(int paramInt, CalendarSyncAdapterApiary paramCalendarSyncAdapterApiary, CalendarSyncState paramCalendarSyncState, Context paramContext, ContentProviderClient paramContentProviderClient, Account paramAccount)
    throws RemoteException, IOException
  {
    // Byte code:
    //   0: iload_0
    //   1: invokestatic 394	com/google/android/syncadapters/calendar/CalendarSyncStateFactory:findUpgradeMethod	(I)Ljava/lang/reflect/Method;
    //   4: astore 8
    //   6: aload 8
    //   8: new 2	com/google/android/syncadapters/calendar/CalendarSyncStateFactory
    //   11: dup
    //   12: invokespecial 395	com/google/android/syncadapters/calendar/CalendarSyncStateFactory:<init>	()V
    //   15: iconst_5
    //   16: anewarray 4	java/lang/Object
    //   19: dup
    //   20: iconst_0
    //   21: aload_1
    //   22: aastore
    //   23: dup
    //   24: iconst_1
    //   25: aload_2
    //   26: aastore
    //   27: dup
    //   28: iconst_2
    //   29: aload_3
    //   30: aastore
    //   31: dup
    //   32: iconst_3
    //   33: aload 4
    //   35: aastore
    //   36: dup
    //   37: iconst_4
    //   38: aload 5
    //   40: aastore
    //   41: invokevirtual 401	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   44: checkcast 330	java/lang/Integer
    //   47: invokevirtual 404	java/lang/Integer:intValue	()I
    //   50: istore 6
    //   52: iload 6
    //   54: ifge +244 -> 298
    //   57: iload_0
    //   58: iconst_1
    //   59: iadd
    //   60: istore 7
    //   62: aload_2
    //   63: getfield 148	com/google/android/syncadapters/calendar/CalendarSyncState:data	Lorg/json/JSONObject;
    //   66: ldc_w 406
    //   69: iload 7
    //   71: invokevirtual 409	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   74: pop
    //   75: aload 4
    //   77: aload_2
    //   78: getfield 344	com/google/android/syncadapters/calendar/CalendarSyncState:uri	Landroid/net/Uri;
    //   81: aload_2
    //   82: getfield 148	com/google/android/syncadapters/calendar/CalendarSyncState:data	Lorg/json/JSONObject;
    //   85: invokevirtual 160	org/json/JSONObject:toString	()Ljava/lang/String;
    //   88: invokevirtual 164	java/lang/String:getBytes	()[B
    //   91: invokestatic 348	android/provider/SyncStateContract$Helpers:update	(Landroid/content/ContentProviderClient;Landroid/net/Uri;[B)V
    //   94: getstatic 18	com/google/android/syncadapters/calendar/CalendarSyncStateFactory:TAG	Ljava/lang/String;
    //   97: ldc_w 411
    //   100: iconst_2
    //   101: anewarray 4	java/lang/Object
    //   104: dup
    //   105: iconst_0
    //   106: iload_0
    //   107: invokestatic 333	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   110: aastore
    //   111: dup
    //   112: iconst_1
    //   113: iload 7
    //   115: invokestatic 333	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   118: aastore
    //   119: invokestatic 276	com/android/calendarcommon2/LogUtils:i	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)I
    //   122: pop
    //   123: iload 7
    //   125: ireturn
    //   126: aload 8
    //   128: invokevirtual 414	java/lang/reflect/Method:getName	()Ljava/lang/String;
    //   131: astore_1
    //   132: new 227	java/lang/IllegalStateException
    //   135: dup
    //   136: new 206	java/lang/StringBuilder
    //   139: dup
    //   140: aload_1
    //   141: invokestatic 301	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   144: invokevirtual 417	java/lang/String:length	()I
    //   147: bipush 80
    //   149: iadd
    //   150: invokespecial 207	java/lang/StringBuilder:<init>	(I)V
    //   153: ldc_w 419
    //   156: invokevirtual 213	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   159: aload_1
    //   160: invokevirtual 213	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   163: ldc_w 421
    //   166: invokevirtual 213	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   169: iload 6
    //   171: invokevirtual 216	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   174: ldc_w 423
    //   177: invokevirtual 213	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   180: iload_0
    //   181: invokevirtual 216	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   184: ldc_w 425
    //   187: invokevirtual 213	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   190: invokevirtual 217	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   193: invokespecial 426	java/lang/IllegalStateException:<init>	(Ljava/lang/String;)V
    //   196: athrow
    //   197: astore_1
    //   198: aload_1
    //   199: invokevirtual 430	java/lang/reflect/InvocationTargetException:getCause	()Ljava/lang/Throwable;
    //   202: astore_2
    //   203: aload_2
    //   204: ifnonnull +46 -> 250
    //   207: new 227	java/lang/IllegalStateException
    //   210: dup
    //   211: ldc_w 432
    //   214: aload_1
    //   215: invokespecial 232	java/lang/IllegalStateException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   218: athrow
    //   219: astore_1
    //   220: ldc -62
    //   222: aload_1
    //   223: ldc_w 434
    //   226: iconst_0
    //   227: anewarray 4	java/lang/Object
    //   230: invokestatic 200	com/android/calendarcommon2/LogUtils:e	(Ljava/lang/String;Ljava/lang/Throwable;Ljava/lang/String;[Ljava/lang/Object;)I
    //   233: pop
    //   234: goto -159 -> 75
    //   237: astore_1
    //   238: new 227	java/lang/IllegalStateException
    //   241: dup
    //   242: ldc_w 436
    //   245: aload_1
    //   246: invokespecial 232	java/lang/IllegalStateException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   249: athrow
    //   250: aload_2
    //   251: instanceof 438
    //   254: ifeq +8 -> 262
    //   257: aload_2
    //   258: checkcast 438	java/lang/RuntimeException
    //   261: athrow
    //   262: aload_2
    //   263: instanceof 40
    //   266: ifeq +8 -> 274
    //   269: aload_2
    //   270: checkcast 40	android/os/RemoteException
    //   273: athrow
    //   274: aload_2
    //   275: instanceof 42
    //   278: ifeq +8 -> 286
    //   281: aload_2
    //   282: checkcast 42	java/io/IOException
    //   285: athrow
    //   286: new 227	java/lang/IllegalStateException
    //   289: dup
    //   290: ldc_w 436
    //   293: aload_2
    //   294: invokespecial 232	java/lang/IllegalStateException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   297: athrow
    //   298: iload 6
    //   300: iload_0
    //   301: if_icmple -175 -> 126
    //   304: iload 6
    //   306: istore 7
    //   308: iload 6
    //   310: bipush 15
    //   312: if_icmple -250 -> 62
    //   315: goto -189 -> 126
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	318	0	paramInt	int
    //   0	318	1	paramCalendarSyncAdapterApiary	CalendarSyncAdapterApiary
    //   0	318	2	paramCalendarSyncState	CalendarSyncState
    //   0	318	3	paramContext	Context
    //   0	318	4	paramContentProviderClient	ContentProviderClient
    //   0	318	5	paramAccount	Account
    //   50	263	6	i	int
    //   60	247	7	j	int
    //   4	123	8	localMethod	Method
    // Exception table:
    //   from	to	target	type
    //   6	52	197	java/lang/reflect/InvocationTargetException
    //   62	75	197	java/lang/reflect/InvocationTargetException
    //   75	123	197	java/lang/reflect/InvocationTargetException
    //   126	197	197	java/lang/reflect/InvocationTargetException
    //   220	234	197	java/lang/reflect/InvocationTargetException
    //   62	75	219	org/json/JSONException
    //   6	52	237	java/lang/IllegalAccessException
    //   62	75	237	java/lang/IllegalAccessException
    //   75	123	237	java/lang/IllegalAccessException
    //   126	197	237	java/lang/IllegalAccessException
    //   220	234	237	java/lang/IllegalAccessException
  }
  
  /* Error */
  private static void wipeEventsAndCalendars(Context paramContext, ContentProviderClient paramContentProviderClient, Account paramAccount)
    throws RemoteException, ParseException
  {
    // Byte code:
    //   0: aload_2
    //   1: invokestatic 50	com/google/android/syncadapters/calendar/ProviderHelper:asSyncAdapter	(Landroid/accounts/Account;)Lcom/google/android/syncadapters/calendar/ProviderHelper;
    //   4: aload_1
    //   5: getstatic 56	android/provider/CalendarContract$Calendars:CONTENT_URI	Landroid/net/Uri;
    //   8: iconst_3
    //   9: anewarray 20	java/lang/String
    //   12: dup
    //   13: iconst_0
    //   14: ldc_w 442
    //   17: aastore
    //   18: dup
    //   19: iconst_1
    //   20: ldc 24
    //   22: aastore
    //   23: dup
    //   24: iconst_2
    //   25: ldc_w 444
    //   28: aastore
    //   29: aconst_null
    //   30: aconst_null
    //   31: aconst_null
    //   32: invokevirtual 66	com/google/android/syncadapters/calendar/ProviderHelper:query	(Landroid/content/ContentProviderClient;Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   35: astore 4
    //   37: aload 4
    //   39: ifnull +155 -> 194
    //   42: aload_2
    //   43: getfield 174	android/accounts/Account:name	Ljava/lang/String;
    //   46: invokestatic 301	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   49: astore_3
    //   50: aload_3
    //   51: invokevirtual 417	java/lang/String:length	()I
    //   54: ifeq +207 -> 261
    //   57: ldc_w 446
    //   60: aload_3
    //   61: invokevirtual 450	java/lang/String:concat	(Ljava/lang/String;)Ljava/lang/String;
    //   64: astore_3
    //   65: new 452	java/io/PrintWriter
    //   68: dup
    //   69: aload_0
    //   70: aload_3
    //   71: iconst_0
    //   72: invokevirtual 456	android/content/Context:openFileOutput	(Ljava/lang/String;I)Ljava/io/FileOutputStream;
    //   75: invokespecial 459	java/io/PrintWriter:<init>	(Ljava/io/OutputStream;)V
    //   78: astore_0
    //   79: aload 4
    //   81: invokeinterface 82 1 0
    //   86: ifeq +199 -> 285
    //   89: aload 4
    //   91: iconst_0
    //   92: invokeinterface 90 2 0
    //   97: astore_3
    //   98: aload_0
    //   99: ldc_w 461
    //   102: iconst_3
    //   103: anewarray 4	java/lang/Object
    //   106: dup
    //   107: iconst_0
    //   108: aload 4
    //   110: iconst_1
    //   111: invokeinterface 465 2 0
    //   116: invokestatic 333	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   119: aastore
    //   120: dup
    //   121: iconst_1
    //   122: aload 4
    //   124: iconst_2
    //   125: invokeinterface 465 2 0
    //   130: invokestatic 333	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   133: aastore
    //   134: dup
    //   135: iconst_2
    //   136: aload_3
    //   137: aastore
    //   138: invokevirtual 469	java/io/PrintWriter:printf	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/io/PrintWriter;
    //   141: pop
    //   142: goto -63 -> 79
    //   145: astore_3
    //   146: aload_0
    //   147: invokevirtual 470	java/io/PrintWriter:close	()V
    //   150: aload_3
    //   151: athrow
    //   152: astore_0
    //   153: getstatic 18	com/google/android/syncadapters/calendar/CalendarSyncStateFactory:TAG	Ljava/lang/String;
    //   156: aload_0
    //   157: ldc_w 472
    //   160: iconst_2
    //   161: anewarray 4	java/lang/Object
    //   164: dup
    //   165: iconst_0
    //   166: ldc_w 446
    //   169: aastore
    //   170: dup
    //   171: iconst_1
    //   172: getstatic 18	com/google/android/syncadapters/calendar/CalendarSyncStateFactory:TAG	Ljava/lang/String;
    //   175: aload_2
    //   176: getfield 174	android/accounts/Account:name	Ljava/lang/String;
    //   179: invokestatic 476	com/android/calendarcommon2/LogUtils:sanitizeAccountName	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   182: aastore
    //   183: invokestatic 200	com/android/calendarcommon2/LogUtils:e	(Ljava/lang/String;Ljava/lang/Throwable;Ljava/lang/String;[Ljava/lang/Object;)I
    //   186: pop
    //   187: aload 4
    //   189: invokeinterface 115 1 0
    //   194: aload_2
    //   195: invokestatic 50	com/google/android/syncadapters/calendar/ProviderHelper:asSyncAdapter	(Landroid/accounts/Account;)Lcom/google/android/syncadapters/calendar/ProviderHelper;
    //   198: aload_1
    //   199: getstatic 56	android/provider/CalendarContract$Calendars:CONTENT_URI	Landroid/net/Uri;
    //   202: ldc_w 478
    //   205: iconst_2
    //   206: anewarray 20	java/lang/String
    //   209: dup
    //   210: iconst_0
    //   211: aload_2
    //   212: getfield 174	android/accounts/Account:name	Ljava/lang/String;
    //   215: aastore
    //   216: dup
    //   217: iconst_1
    //   218: aload_2
    //   219: getfield 182	android/accounts/Account:type	Ljava/lang/String;
    //   222: aastore
    //   223: invokevirtual 482	com/google/android/syncadapters/calendar/ProviderHelper:delete	(Landroid/content/ContentProviderClient;Landroid/net/Uri;Ljava/lang/String;[Ljava/lang/String;)I
    //   226: pop
    //   227: aload_2
    //   228: invokestatic 50	com/google/android/syncadapters/calendar/ProviderHelper:asSyncAdapter	(Landroid/accounts/Account;)Lcom/google/android/syncadapters/calendar/ProviderHelper;
    //   231: aload_1
    //   232: getstatic 485	android/provider/CalendarContract$Events:CONTENT_URI	Landroid/net/Uri;
    //   235: ldc_w 478
    //   238: iconst_2
    //   239: anewarray 20	java/lang/String
    //   242: dup
    //   243: iconst_0
    //   244: aload_2
    //   245: getfield 174	android/accounts/Account:name	Ljava/lang/String;
    //   248: aastore
    //   249: dup
    //   250: iconst_1
    //   251: aload_2
    //   252: getfield 182	android/accounts/Account:type	Ljava/lang/String;
    //   255: aastore
    //   256: invokevirtual 482	com/google/android/syncadapters/calendar/ProviderHelper:delete	(Landroid/content/ContentProviderClient;Landroid/net/Uri;Ljava/lang/String;[Ljava/lang/String;)I
    //   259: pop
    //   260: return
    //   261: new 20	java/lang/String
    //   264: dup
    //   265: ldc_w 446
    //   268: invokespecial 486	java/lang/String:<init>	(Ljava/lang/String;)V
    //   271: astore_3
    //   272: goto -207 -> 65
    //   275: astore_0
    //   276: aload 4
    //   278: invokeinterface 115 1 0
    //   283: aload_0
    //   284: athrow
    //   285: aload_0
    //   286: invokevirtual 470	java/io/PrintWriter:close	()V
    //   289: goto -102 -> 187
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	292	0	paramContext	Context
    //   0	292	1	paramContentProviderClient	ContentProviderClient
    //   0	292	2	paramAccount	Account
    //   49	88	3	str1	String
    //   145	6	3	localObject	Object
    //   271	1	3	str2	String
    //   35	242	4	localCursor	Cursor
    // Exception table:
    //   from	to	target	type
    //   79	142	145	finally
    //   65	79	152	java/io/FileNotFoundException
    //   146	152	152	java/io/FileNotFoundException
    //   285	289	152	java/io/FileNotFoundException
    //   42	65	275	finally
    //   65	79	275	finally
    //   146	152	275	finally
    //   153	187	275	finally
    //   261	272	275	finally
    //   285	289	275	finally
  }
  
  public int upgradeFrom0(CalendarSyncAdapterApiary paramCalendarSyncAdapterApiary, CalendarSyncState paramCalendarSyncState, Context paramContext, ContentProviderClient paramContentProviderClient, Account paramAccount)
    throws RemoteException, IOException, ParseException
  {
    wipeEventsAndCalendars(paramContext, paramContentProviderClient, paramAccount);
    paramCalendarSyncState.reset();
    LogUtils.d(TAG, "Requesting full sync in CalendarSyncState#forceFullSync", new Object[0]);
    paramCalendarSyncAdapterApiary = new Bundle();
    paramCalendarSyncState = Features.instance;
    if (paramCalendarSyncState == null) {
      throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
    }
    ((FeatureConfig)paramCalendarSyncState).fishfood_debug();
    ContentResolver.requestSync(paramAccount, "com.android.calendar", paramCalendarSyncAdapterApiary);
    return -1;
  }
  
  public int upgradeFrom1(CalendarSyncAdapterApiary paramCalendarSyncAdapterApiary, CalendarSyncState paramCalendarSyncState, Context paramContext, ContentProviderClient paramContentProviderClient, Account paramAccount)
    throws RemoteException, IOException, ParseException
  {
    paramCalendarSyncAdapterApiary = new ContentValues();
    paramCalendarSyncAdapterApiary.put("allowedReminders", "0,1,2");
    ProviderHelper.asSyncAdapter(paramAccount).update(paramContentProviderClient, CalendarContract.Calendars.CONTENT_URI, paramCalendarSyncAdapterApiary, "account_name=? AND account_type=?", new String[] { paramAccount.name, paramAccount.type });
    return -1;
  }
  
  public int upgradeFrom10(CalendarSyncAdapterApiary paramCalendarSyncAdapterApiary, CalendarSyncState paramCalendarSyncState, Context paramContext, ContentProviderClient paramContentProviderClient, Account paramAccount)
    throws RemoteException, IOException, ParseException
  {
    return paramCalendarSyncAdapterApiary.restoreGrooveDataForEvents(paramContentProviderClient, paramAccount);
  }
  
  public int upgradeFrom11(CalendarSyncAdapterApiary paramCalendarSyncAdapterApiary, CalendarSyncState paramCalendarSyncState, Context paramContext, ContentProviderClient paramContentProviderClient, Account paramAccount)
  {
    return -1;
  }
  
  public int upgradeFrom12(CalendarSyncAdapterApiary paramCalendarSyncAdapterApiary, CalendarSyncState paramCalendarSyncState, Context paramContext, ContentProviderClient paramContentProviderClient, Account paramAccount)
  {
    return -1;
  }
  
  public int upgradeFrom13(CalendarSyncAdapterApiary paramCalendarSyncAdapterApiary, CalendarSyncState paramCalendarSyncState, Context paramContext, ContentProviderClient paramContentProviderClient, Account paramAccount)
    throws RemoteException, IOException, ParseException
  {
    paramCalendarSyncState = new Calendar.Habits(paramCalendarSyncAdapterApiary.client);
    Calendar.Habits.List localList = new Calendar.Habits.List(paramCalendarSyncState, paramAccount.name);
    paramCalendarSyncState.this$0.initialize(localList);
    String[] arrayOfString = new String[1];
    ContentValues localContentValues = new ContentValues();
    do
    {
      Habits localHabits = paramCalendarSyncAdapterApiary.getHabitsListLogged(paramContext, paramContentProviderClient, localList, paramAccount);
      List localList1 = localHabits.items;
      int i = 0;
      if (i < localList1.size())
      {
        Habit localHabit = (Habit)localList1.get(i);
        paramCalendarSyncState = localHabit.id;
        String str = localHabit.habitData.type;
        if (str == null) {}
        for (;;)
        {
          localContentValues.clear();
          localContentValues.put("sync_data8", paramCalendarSyncState);
          arrayOfString[0] = localHabit.id;
          int j = ProviderHelper.asSyncAdapter(paramAccount).update(paramContentProviderClient, CalendarContract.Events.CONTENT_URI, localContentValues, "sync_data8=?", arrayOfString);
          LogUtils.v("CalendarSyncAdapter", "Account: %s, Habit: %s, Value: %s, Updated: %d", new Object[] { paramAccount.name, localHabit.habitData.summary, paramCalendarSyncState, Integer.valueOf(j) });
          if (j > 0) {
            paramContext.getContentResolver().notifyChange(ProviderHelper.toAsSyncAdapterUri(CalendarContract.Events.CONTENT_URI, paramAccount), null, true);
          }
          i += 1;
          break;
          paramCalendarSyncState = HabitIdTypeUtil.createHabitIdTypeStringFromApiType(paramCalendarSyncState, HabitSyncUtils.serverTypeToApiType(str));
        }
      }
      localList.pageToken = localHabits.nextPageToken;
    } while (localList.pageToken != null);
    return -1;
  }
  
  public int upgradeFrom14(CalendarSyncAdapterApiary paramCalendarSyncAdapterApiary, CalendarSyncState paramCalendarSyncState, Context paramContext, ContentProviderClient paramContentProviderClient, Account paramAccount)
    throws RemoteException, IOException, ParseException
  {
    return -1;
  }
  
  public int upgradeFrom15(CalendarSyncAdapterApiary paramCalendarSyncAdapterApiary, CalendarSyncState paramCalendarSyncState, Context paramContext, ContentProviderClient paramContentProviderClient, Account paramAccount)
    throws RemoteException, IOException, ParseException
  {
    paramCalendarSyncAdapterApiary = new ContentValues(1);
    paramCalendarSyncAdapterApiary.put("allowedReminders", "0,1,2,4");
    ProviderHelper.asSyncAdapter(paramAccount).update(paramContentProviderClient, CalendarContract.Calendars.CONTENT_URI, paramCalendarSyncAdapterApiary, "account_name=? AND account_type=?", new String[] { paramAccount.name, paramAccount.type });
    return -1;
  }
  
  public int upgradeFrom2(CalendarSyncAdapterApiary paramCalendarSyncAdapterApiary, CalendarSyncState paramCalendarSyncState, Context paramContext, ContentProviderClient paramContentProviderClient, Account paramAccount)
    throws RemoteException, IOException, ParseException
  {
    paramContext = paramCalendarSyncAdapterApiary.getContext().getContentResolver();
    paramCalendarSyncState = ProviderHelper.asSyncAdapter(paramAccount).query(paramContentProviderClient, CalendarContract.Calendars.CONTENT_URI, new String[] { "cal_sync1" }, "sync_events=1", null, null);
    if (paramCalendarSyncState != null)
    {
      for (;;)
      {
        ArrayList localArrayList;
        try
        {
          int i = Gservices.getInt(paramContext, "google_calendar_sync_max_attendees", 50);
          if (!paramCalendarSyncState.moveToNext()) {
            break;
          }
          String str = paramCalendarSyncState.getString(0);
          EventHandler localEventHandler = new EventHandler(paramCalendarSyncAdapterApiary.client, paramAccount, paramContentProviderClient, paramContext, str, paramCalendarSyncAdapterApiary.syncHooks, paramCalendarSyncAdapterApiary.requestExecutor, paramCalendarSyncAdapterApiary.timelySync);
          try
          {
            EntityIterator localEntityIterator = localEventHandler.newEntityIterator(SQLiteDatabaseUtils.makeWhere(new String[] { "cal_sync1=?", "dirty=0", "lastSynced=0", "_sync_id IS NOT NULL", "(guestsCanInviteOthers=0 OR guestsCanSeeGuests=0)" }), new String[] { str }, -1);
            try
            {
              localSyncResult = new SyncResult();
              localArrayList = new ArrayList();
              if (!localEntityIterator.hasNext()) {
                break label391;
              }
              localEntity = (Entity)localEntityIterator.next();
              localObject2 = localEntity.getEntityValues().getAsString("_sync_id");
            }
            finally
            {
              try
              {
                SyncResult localSyncResult;
                CalendarRequestExecutor localCalendarRequestExecutor = paramCalendarSyncAdapterApiary.requestExecutor;
                Calendar.Events localEvents = new Calendar.Events(paramCalendarSyncAdapterApiary.client);
                Object localObject2 = new Calendar.Events.Get(localEvents, str, (String)localObject2);
                localEvents.this$0.initialize((AbstractGoogleClientRequest)localObject2);
                ((Calendar.Events.Get)localObject2).maxAttendees = Integer.valueOf(i);
                localEventHandler.applyItemToEntity(localArrayList, (Event)localCalendarRequestExecutor.execute("API: Get Event", (CalendarRequest)localObject2), localEntity, false, localSyncResult, null);
                j = localArrayList.size();
                if (j <= 20) {
                  continue;
                }
                LogUtils.i("CalendarSyncAdapter", "Repairing %d events", new Object[] { Integer.valueOf(j) });
                Utilities.applyOperationsAsSyncAdapter(paramContentProviderClient, paramAccount, localArrayList);
              }
              catch (HttpResponseException localHttpResponseException)
              {
                Entity localEntity;
                SyncLog.logErrorType(localHttpResponseException, localEntity, "Failed to resync event");
              }
              localObject1 = finally;
            }
          }
          catch (ParseException localParseException)
          {
            LogUtils.wtf("CalendarSyncAdapter", localParseException, "Failed to repair events on upgrade.", new Object[0]);
          }
          continue;
        }
        finally
        {
          paramCalendarSyncState.close();
        }
        label391:
        int j = localArrayList.size();
        if (localArrayList.size() > 0)
        {
          LogUtils.i("CalendarSyncAdapter", "Repairing %d events", new Object[] { Integer.valueOf(j) });
          Utilities.applyOperationsAsSyncAdapter(paramContentProviderClient, paramAccount, localArrayList);
        }
        localParseException.close();
      }
      paramCalendarSyncState.close();
    }
    return -1;
  }
  
  public int upgradeFrom3(CalendarSyncAdapterApiary paramCalendarSyncAdapterApiary, CalendarSyncState paramCalendarSyncState, Context paramContext, ContentProviderClient paramContentProviderClient, Account paramAccount)
    throws RemoteException, IOException, ParseException
  {
    return -1;
  }
  
  public int upgradeFrom4(CalendarSyncAdapterApiary paramCalendarSyncAdapterApiary, CalendarSyncState paramCalendarSyncState, Context paramContext, ContentProviderClient paramContentProviderClient, Account paramAccount)
    throws RemoteException, IOException, ParseException
  {
    paramCalendarSyncState = ProviderHelper.asClient().query(paramContentProviderClient, CalendarContract.Events.CONTENT_URI, null, "rrule LIKE '%;UNTIL=%'", null, null);
    if (paramCalendarSyncState != null) {
      paramContext = new ArrayList();
    }
    for (;;)
    {
      ProviderHelper localProviderHelper;
      try
      {
        if (!paramCalendarSyncState.moveToNext()) {
          break label276;
        }
        localObject = paramCalendarSyncState.getString(paramCalendarSyncState.getColumnIndexOrThrow("rrule"));
        paramCalendarSyncAdapterApiary = Utilities.sanitizeRecurrence((String)localObject);
        if (paramCalendarSyncAdapterApiary.equals(localObject)) {
          continue;
        }
        localObject = new ContentValues();
        long l = paramCalendarSyncState.getLong(paramCalendarSyncState.getColumnIndexOrThrow("_id"));
        ((ContentValues)localObject).put("_id", Long.valueOf(l));
        ((ContentValues)localObject).put("rrule", paramCalendarSyncAdapterApiary);
        DatabaseUtils.cursorIntToContentValuesIfPresent(paramCalendarSyncState, (ContentValues)localObject, "eventStatus");
        DatabaseUtils.cursorLongToContentValuesIfPresent(paramCalendarSyncState, (ContentValues)localObject, "dtstart");
        DatabaseUtils.cursorLongToContentValuesIfPresent(paramCalendarSyncState, (ContentValues)localObject, "dtend");
        DatabaseUtils.cursorStringToContentValuesIfPresent(paramCalendarSyncState, (ContentValues)localObject, "duration");
        DatabaseUtils.cursorStringToContentValuesIfPresent(paramCalendarSyncState, (ContentValues)localObject, "eventTimezone");
        DatabaseUtils.cursorStringToContentValuesIfPresent(paramCalendarSyncState, (ContentValues)localObject, "allDay");
        DatabaseUtils.cursorStringToContentValuesIfPresent(paramCalendarSyncState, (ContentValues)localObject, "rdate");
        DatabaseUtils.cursorStringToContentValuesIfPresent(paramCalendarSyncState, (ContentValues)localObject, "exrule");
        DatabaseUtils.cursorStringToContentValuesIfPresent(paramCalendarSyncState, (ContentValues)localObject, "exdate");
        localProviderHelper = ProviderHelper.asSyncAdapter(paramAccount);
        paramCalendarSyncAdapterApiary = ContentUris.withAppendedId(CalendarContract.Events.CONTENT_URI, l);
        if (localProviderHelper.account == null) {
          break label257;
        }
        i = 1;
      }
      finally
      {
        Object localObject;
        paramCalendarSyncState.close();
      }
      paramContext.add(ContentProviderOperation.newUpdate(paramCalendarSyncAdapterApiary).withValues((ContentValues)localObject).build());
      continue;
      label257:
      int i = 0;
      label276:
      while (i != 0)
      {
        paramCalendarSyncAdapterApiary = ProviderHelper.toAsSyncAdapterUri(paramCalendarSyncAdapterApiary, localProviderHelper.account);
        break;
        paramCalendarSyncState.close();
        paramCalendarSyncAdapterApiary = new ArrayList();
        paramCalendarSyncState = (ArrayList)paramContext;
        int k = paramCalendarSyncState.size();
        i = 0;
        while (i < k)
        {
          paramContext = paramCalendarSyncState.get(i);
          int j = i + 1;
          paramCalendarSyncAdapterApiary.add((ContentProviderOperation)paramContext);
          i = j;
          if (paramCalendarSyncAdapterApiary.size() > 100)
          {
            CalendarSyncStateUtils.applyOperationsAsSyncAdapter(paramContentProviderClient, paramAccount, paramCalendarSyncAdapterApiary);
            paramCalendarSyncAdapterApiary.clear();
            i = j;
          }
        }
        if (paramCalendarSyncAdapterApiary.size() > 0) {
          CalendarSyncStateUtils.applyOperationsAsSyncAdapter(paramContentProviderClient, paramAccount, paramCalendarSyncAdapterApiary);
        }
        return -1;
      }
    }
  }
  
  public int upgradeFrom5(CalendarSyncAdapterApiary paramCalendarSyncAdapterApiary, CalendarSyncState paramCalendarSyncState, Context paramContext, ContentProviderClient paramContentProviderClient, Account paramAccount)
    throws RemoteException, IOException, ParseException
  {
    paramCalendarSyncAdapterApiary = paramContext.getPackageManager().getInstalledPackages(128).iterator();
    int i = 0;
    if (paramCalendarSyncAdapterApiary.hasNext())
    {
      if (!"com.google.android.syncadapters.calendar".equals(((PackageInfo)paramCalendarSyncAdapterApiary.next()).applicationInfo.packageName)) {
        break label436;
      }
      i = 1;
    }
    label436:
    for (;;)
    {
      break;
      if (i == 0) {
        return -1;
      }
      Uri localUri = CalendarContract.Calendars.CONTENT_URI;
      paramCalendarSyncAdapterApiary = ProviderHelper.asClient().query(paramContentProviderClient, localUri, QUERY_PROJECTION, "account_name=? AND account_type=?", new String[] { paramAccount.name, paramAccount.type }, null);
      if (paramCalendarSyncAdapterApiary != null)
      {
        try
        {
          if (paramCalendarSyncAdapterApiary.moveToFirst())
          {
            paramContext = new ArrayList();
            do
            {
              i = paramCalendarSyncAdapterApiary.getInt(paramCalendarSyncAdapterApiary.getColumnIndex("sync_events"));
              if (i == 1) {
                return -1;
              }
              paramContext.add(paramCalendarSyncAdapterApiary.getString(paramCalendarSyncAdapterApiary.getColumnIndex("cal_sync1")));
            } while (paramCalendarSyncAdapterApiary.moveToNext());
            ContentValues localContentValues = new ContentValues();
            localContentValues.put("sync_events", Integer.valueOf(1));
            localContentValues.put("visible", Integer.valueOf(1));
            ProviderHelper.asClient().update(paramContentProviderClient, localUri, localContentValues, "account_name=? AND account_type=? AND cal_sync4=? AND cal_sync5=?", new String[] { paramAccount.name, paramAccount.type, "1", "0" });
            paramContext = (ArrayList)paramContext;
            int j = paramContext.size();
            i = 0;
            while (i < j)
            {
              paramAccount = paramContext.get(i);
              i += 1;
              paramAccount = paramCalendarSyncState.getFeedState((String)paramAccount);
              if (paramAccount != null) {
                paramAccount.clear();
              }
              SyncStateContract.Helpers.update(paramContentProviderClient, paramCalendarSyncState.uri, paramCalendarSyncState.data.toString().getBytes());
            }
          }
          wipeEventsAndCalendars(paramContext, paramContentProviderClient, paramAccount);
        }
        finally
        {
          paramCalendarSyncAdapterApiary.close();
        }
        paramCalendarSyncState.reset();
        LogUtils.d(TAG, "Requesting full sync in CalendarSyncState#forceFullSync", new Object[0]);
        paramCalendarSyncState = new Bundle();
        paramContext = Features.instance;
        if (paramContext == null) {
          throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        ((FeatureConfig)paramContext).fishfood_debug();
        ContentResolver.requestSync(paramAccount, "com.android.calendar", paramCalendarSyncState);
        paramCalendarSyncAdapterApiary.close();
      }
      return -1;
    }
  }
  
  public int upgradeFrom6(CalendarSyncAdapterApiary paramCalendarSyncAdapterApiary, CalendarSyncState paramCalendarSyncState, Context paramContext, ContentProviderClient paramContentProviderClient, Account paramAccount)
    throws RemoteException, IOException
  {
    return -1;
  }
  
  public int upgradeFrom7(CalendarSyncAdapterApiary paramCalendarSyncAdapterApiary, CalendarSyncState paramCalendarSyncState, Context paramContext, ContentProviderClient paramContentProviderClient, Account paramAccount)
    throws RemoteException, IOException, ParseException
  {
    addTimelyDataToExistingEvents(paramCalendarSyncAdapterApiary, paramContentProviderClient, paramAccount, paramCalendarSyncState);
    return -1;
  }
  
  public int upgradeFrom8(CalendarSyncAdapterApiary paramCalendarSyncAdapterApiary, CalendarSyncState paramCalendarSyncState, Context paramContext, ContentProviderClient paramContentProviderClient, Account paramAccount)
    throws RemoteException, IOException, ParseException
  {
    paramContext = ProviderHelper.asSyncAdapter(paramAccount).query(paramContentProviderClient, CalendarContract.Events.CONTENT_URI, new String[] { "_id", "sync_data9", "sync_data10" }, "sync_data9='true' OR sync_data9='false'", null, null);
    if (paramContext != null) {}
    for (;;)
    {
      int i;
      try
      {
        if (paramContext.moveToFirst())
        {
          int j = paramContext.getColumnIndex("sync_data9");
          int k = paramContext.getColumnIndex("sync_data10");
          ArrayList localArrayList = new ArrayList();
          long l = paramContext.getLong(paramContext.getColumnIndexOrThrow("_id"));
          if ((j == -1) || (paramContext.isNull(j))) {
            continue;
          }
          paramCalendarSyncAdapterApiary = paramContext.getString(j);
          if ((k == -1) || (paramContext.isNull(k))) {
            continue;
          }
          paramCalendarSyncState = paramContext.getString(k);
          localObject = EventHandler.upgradeTimelyExtrasFlags(paramCalendarSyncAdapterApiary, paramCalendarSyncState);
          LogUtils.i(TAG, "Packing SYNC_DATA9, SYNC_DATA10 for event %d  From: %s, %s  To: %s", new Object[] { Long.valueOf(l), paramCalendarSyncAdapterApiary, paramCalendarSyncState, localObject });
          paramCalendarSyncState = new ContentValues(3);
          paramCalendarSyncState.put("_id", Long.valueOf(l));
          paramCalendarSyncState.put("sync_data9", (String)localObject);
          paramCalendarSyncState.put("sync_data10", "");
          paramCalendarSyncAdapterApiary = ContentUris.withAppendedId(CalendarContract.Events.CONTENT_URI, l);
          localObject = ProviderHelper.asSyncAdapter(paramAccount);
          if (((ProviderHelper)localObject).account == null) {
            continue;
          }
          i = 1;
          break label380;
          localArrayList.add(ContentProviderOperation.newUpdate(paramCalendarSyncAdapterApiary).withYieldAllowed(true).withValues(paramCalendarSyncState).build());
          if (localArrayList.size() > 100)
          {
            CalendarSyncStateUtils.applyOperationsAsSyncAdapter(paramContentProviderClient, paramAccount, localArrayList);
            localArrayList.clear();
          }
          if (paramContext.moveToNext()) {
            continue;
          }
          if (!localArrayList.isEmpty()) {
            CalendarSyncStateUtils.applyOperationsAsSyncAdapter(paramContentProviderClient, paramAccount, localArrayList);
          }
        }
        return -1;
        paramCalendarSyncAdapterApiary = null;
        continue;
        paramCalendarSyncState = null;
        continue;
        i = 0;
      }
      finally
      {
        Object localObject;
        paramContext.close();
      }
      paramCalendarSyncAdapterApiary = ProviderHelper.toAsSyncAdapterUri(paramCalendarSyncAdapterApiary, ((ProviderHelper)localObject).account);
      continue;
      label380:
      if (i != 0) {}
    }
  }
  
  public int upgradeFrom9(CalendarSyncAdapterApiary paramCalendarSyncAdapterApiary, CalendarSyncState paramCalendarSyncState, Context paramContext, ContentProviderClient paramContentProviderClient, Account paramAccount)
    throws RemoteException, IOException, ParseException
  {
    if (paramCalendarSyncState.originalVersion > 7) {
      addTimelyDataToExistingEvents(paramCalendarSyncAdapterApiary, paramContentProviderClient, paramAccount, paramCalendarSyncState);
    }
    return -1;
  }
}
