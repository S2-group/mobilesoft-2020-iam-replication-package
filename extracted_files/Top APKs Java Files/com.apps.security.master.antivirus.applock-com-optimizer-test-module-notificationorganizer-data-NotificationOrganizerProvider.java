package com.optimizer.test.module.notificationorganizer.data;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.ihs.commons.e.c;
import com.ihs.commons.e.i;
import com.optimizer.test.e;
import com.optimizer.test.f.o;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

public class NotificationOrganizerProvider
  extends ContentProvider
{
  public static final List<String> a = new ArrayList() {};
  private UriMatcher b;
  private a c;
  
  public NotificationOrganizerProvider() {}
  
  public static Uri a(Context paramContext)
  {
    return Uri.parse("content://" + paramContext.getPackageName() + ".notification_organizer/");
  }
  
  private void a(final Runnable paramRunnable)
  {
    e.a().a.execute(new Runnable()
    {
      /* Error */
      public final void run()
      {
        // Byte code:
        //   0: iconst_0
        //   1: istore_1
        //   2: aload_0
        //   3: getfield 20	com/optimizer/test/module/notificationorganizer/data/NotificationOrganizerProvider$4:b	Lcom/optimizer/test/module/notificationorganizer/data/NotificationOrganizerProvider;
        //   6: invokestatic 32	com/optimizer/test/module/notificationorganizer/data/NotificationOrganizerProvider:a	(Lcom/optimizer/test/module/notificationorganizer/data/NotificationOrganizerProvider;)Lcom/optimizer/test/module/notificationorganizer/data/a;
        //   9: astore 4
        //   11: invokestatic 36	com/optimizer/test/module/notificationorganizer/data/NotificationOrganizerProvider:l	()Ljava/lang/String;
        //   14: astore 5
        //   16: invokestatic 41	com/optimizer/test/module/notificationorganizer/a:a	()J
        //   19: lstore_2
        //   20: aload 4
        //   22: iconst_1
        //   23: anewarray 43	java/lang/String
        //   26: dup
        //   27: iconst_0
        //   28: ldc 45
        //   30: aastore
        //   31: aload 5
        //   33: iconst_1
        //   34: anewarray 43	java/lang/String
        //   37: dup
        //   38: iconst_0
        //   39: lload_2
        //   40: invokestatic 49	java/lang/String:valueOf	(J)Ljava/lang/String;
        //   43: aastore
        //   44: aconst_null
        //   45: aconst_null
        //   46: ldc 51
        //   48: invokevirtual 56	com/optimizer/test/module/notificationorganizer/data/a:a	([Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
        //   51: astore 4
        //   53: aload 4
        //   55: ifnull +37 -> 92
        //   58: aload 4
        //   60: invokeinterface 62 1 0
        //   65: ifeq +223 -> 288
        //   68: aload 4
        //   70: aload 4
        //   72: ldc 64
        //   74: invokeinterface 68 2 0
        //   79: invokeinterface 72 2 0
        //   84: istore_1
        //   85: aload 4
        //   87: invokeinterface 75 1 0
        //   92: new 77	java/util/ArrayList
        //   95: dup
        //   96: invokespecial 78	java/util/ArrayList:<init>	()V
        //   99: astore 5
        //   101: aload_0
        //   102: getfield 20	com/optimizer/test/module/notificationorganizer/data/NotificationOrganizerProvider$4:b	Lcom/optimizer/test/module/notificationorganizer/data/NotificationOrganizerProvider;
        //   105: invokestatic 32	com/optimizer/test/module/notificationorganizer/data/NotificationOrganizerProvider:a	(Lcom/optimizer/test/module/notificationorganizer/data/NotificationOrganizerProvider;)Lcom/optimizer/test/module/notificationorganizer/data/a;
        //   108: astore 4
        //   110: invokestatic 36	com/optimizer/test/module/notificationorganizer/data/NotificationOrganizerProvider:l	()Ljava/lang/String;
        //   113: astore 6
        //   115: invokestatic 41	com/optimizer/test/module/notificationorganizer/a:a	()J
        //   118: lstore_2
        //   119: aload 4
        //   121: iconst_1
        //   122: anewarray 43	java/lang/String
        //   125: dup
        //   126: iconst_0
        //   127: ldc 80
        //   129: aastore
        //   130: aload 6
        //   132: iconst_1
        //   133: anewarray 43	java/lang/String
        //   136: dup
        //   137: iconst_0
        //   138: lload_2
        //   139: invokestatic 49	java/lang/String:valueOf	(J)Ljava/lang/String;
        //   142: aastore
        //   143: ldc 80
        //   145: ldc 82
        //   147: ldc 84
        //   149: invokevirtual 56	com/optimizer/test/module/notificationorganizer/data/a:a	([Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
        //   152: astore 4
        //   154: aload 4
        //   156: ifnull +97 -> 253
        //   159: aload 4
        //   161: invokeinterface 87 1 0
        //   166: ifeq +80 -> 246
        //   169: aload 5
        //   171: aload 4
        //   173: aload 4
        //   175: ldc 80
        //   177: invokeinterface 68 2 0
        //   182: invokeinterface 91 2 0
        //   187: invokeinterface 97 2 0
        //   192: pop
        //   193: goto -34 -> 159
        //   196: astore 5
        //   198: aload 4
        //   200: invokeinterface 75 1 0
        //   205: aload 5
        //   207: athrow
        //   208: astore 4
        //   210: aload 4
        //   212: invokevirtual 100	java/lang/Exception:printStackTrace	()V
        //   215: aconst_null
        //   216: astore 4
        //   218: goto -165 -> 53
        //   221: astore 5
        //   223: aload 4
        //   225: invokeinterface 75 1 0
        //   230: aload 5
        //   232: athrow
        //   233: astore 4
        //   235: aload 4
        //   237: invokevirtual 100	java/lang/Exception:printStackTrace	()V
        //   240: aconst_null
        //   241: astore 4
        //   243: goto -89 -> 154
        //   246: aload 4
        //   248: invokeinterface 75 1 0
        //   253: ldc 102
        //   255: aload 5
        //   257: invokestatic 108	android/text/TextUtils:join	(Ljava/lang/CharSequence;Ljava/lang/Iterable;)Ljava/lang/String;
        //   260: astore 4
        //   262: new 110	android/os/Handler
        //   265: dup
        //   266: invokestatic 116	android/os/Looper:getMainLooper	()Landroid/os/Looper;
        //   269: invokespecial 119	android/os/Handler:<init>	(Landroid/os/Looper;)V
        //   272: new 13	com/optimizer/test/module/notificationorganizer/data/NotificationOrganizerProvider$4$1
        //   275: dup
        //   276: aload_0
        //   277: iload_1
        //   278: aload 4
        //   280: invokespecial 122	com/optimizer/test/module/notificationorganizer/data/NotificationOrganizerProvider$4$1:<init>	(Lcom/optimizer/test/module/notificationorganizer/data/NotificationOrganizerProvider$4;ILjava/lang/String;)V
        //   283: invokevirtual 126	android/os/Handler:post	(Ljava/lang/Runnable;)Z
        //   286: pop
        //   287: return
        //   288: iconst_0
        //   289: istore_1
        //   290: goto -205 -> 85
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	293	0	this	4
        //   1	289	1	i	int
        //   19	120	2	l	long
        //   9	190	4	localObject1	Object
        //   208	3	4	localException1	Exception
        //   216	8	4	localObject2	Object
        //   233	3	4	localException2	Exception
        //   241	38	4	str1	String
        //   14	156	5	localObject3	Object
        //   196	10	5	localObject4	Object
        //   221	35	5	localIterable	Iterable
        //   113	18	6	str2	String
        // Exception table:
        //   from	to	target	type
        //   159	193	196	finally
        //   2	53	208	java/lang/Exception
        //   58	85	221	finally
        //   101	154	233	java/lang/Exception
      }
    });
  }
  
  public static void a(boolean paramBoolean)
  {
    Bundle localBundle = new Bundle();
    localBundle.putBoolean("EXTRA_SWITCH_STATE", paramBoolean);
    c.a(e(com.ihs.app.framework.a.a()), "METHOD_SET_SWITCH", null, localBundle);
    if (!paramBoolean)
    {
      localBundle = new Bundle();
      localBundle.putBoolean("EXTRA_IS_USER_CLOSED", true);
      c.a(a(com.ihs.app.framework.a.a()), "METHOD_SET_USER_CLOSED", null, localBundle);
    }
    if ((paramBoolean) && (o.a(com.ihs.app.framework.a.a()))) {
      f();
    }
  }
  
  public static boolean a()
  {
    Bundle localBundle = c.a(e(com.ihs.app.framework.a.a()), "METHOD_GET_SWITCH", null, null);
    return (localBundle != null) && (localBundle.getBoolean("EXTRA_SWITCH_STATE"));
  }
  
  public static Uri b(Context paramContext)
  {
    return Uri.parse("content://" + paramContext.getPackageName() + ".notification_organizer/block_notifications");
  }
  
  /* Error */
  public static List<b> b(boolean paramBoolean)
  {
    // Byte code:
    //   0: invokestatic 94	com/ihs/app/framework/a:a	()Landroid/content/Context;
    //   3: invokevirtual 134	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   6: invokestatic 94	com/ihs/app/framework/a:a	()Landroid/content/Context;
    //   9: invokestatic 136	com/optimizer/test/module/notificationorganizer/data/NotificationOrganizerProvider:b	(Landroid/content/Context;)Landroid/net/Uri;
    //   12: aconst_null
    //   13: iload_0
    //   14: invokestatic 140	com/optimizer/test/module/notificationorganizer/data/NotificationOrganizerProvider:d	(Z)Ljava/lang/String;
    //   17: iconst_1
    //   18: anewarray 142	java/lang/String
    //   21: dup
    //   22: iconst_0
    //   23: invokestatic 147	com/optimizer/test/module/notificationorganizer/a:a	()J
    //   26: invokestatic 151	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   29: aastore
    //   30: ldc -103
    //   32: invokevirtual 159	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   35: astore_1
    //   36: aload_1
    //   37: ifnonnull +11 -> 48
    //   40: new 161	java/util/ArrayList
    //   43: dup
    //   44: invokespecial 162	java/util/ArrayList:<init>	()V
    //   47: areturn
    //   48: new 161	java/util/ArrayList
    //   51: dup
    //   52: invokespecial 162	java/util/ArrayList:<init>	()V
    //   55: astore_2
    //   56: aload_1
    //   57: invokeinterface 167 1 0
    //   62: ifeq +126 -> 188
    //   65: new 169	com/optimizer/test/module/notificationorganizer/data/b
    //   68: dup
    //   69: invokespecial 170	com/optimizer/test/module/notificationorganizer/data/b:<init>	()V
    //   72: astore_3
    //   73: aload_3
    //   74: aload_1
    //   75: aload_1
    //   76: ldc -84
    //   78: invokeinterface 176 2 0
    //   83: invokeinterface 180 2 0
    //   88: putfield 183	com/optimizer/test/module/notificationorganizer/data/b:d	Ljava/lang/String;
    //   91: aload_3
    //   92: aload_1
    //   93: aload_1
    //   94: ldc -71
    //   96: invokeinterface 176 2 0
    //   101: invokeinterface 189 2 0
    //   106: putfield 193	com/optimizer/test/module/notificationorganizer/data/b:j	J
    //   109: aload_3
    //   110: aload_1
    //   111: aload_1
    //   112: ldc -61
    //   114: invokeinterface 176 2 0
    //   119: invokeinterface 180 2 0
    //   124: putfield 198	com/optimizer/test/module/notificationorganizer/data/b:h	Ljava/lang/String;
    //   127: aload_3
    //   128: aload_1
    //   129: aload_1
    //   130: ldc -56
    //   132: invokeinterface 176 2 0
    //   137: invokeinterface 180 2 0
    //   142: putfield 203	com/optimizer/test/module/notificationorganizer/data/b:g	Ljava/lang/String;
    //   145: aload_3
    //   146: aload_1
    //   147: aload_1
    //   148: ldc -51
    //   150: invokeinterface 176 2 0
    //   155: invokeinterface 209 2 0
    //   160: i2l
    //   161: putfield 211	com/optimizer/test/module/notificationorganizer/data/b:a	J
    //   164: aload_2
    //   165: aload_3
    //   166: invokeinterface 217 2 0
    //   171: pop
    //   172: goto -116 -> 56
    //   175: astore_3
    //   176: aload_3
    //   177: invokevirtual 220	java/lang/Exception:printStackTrace	()V
    //   180: aload_1
    //   181: invokeinterface 223 1 0
    //   186: aload_2
    //   187: areturn
    //   188: aload_1
    //   189: invokeinterface 223 1 0
    //   194: aload_2
    //   195: areturn
    //   196: astore_2
    //   197: aload_1
    //   198: invokeinterface 223 1 0
    //   203: aload_2
    //   204: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	205	0	paramBoolean	boolean
    //   35	163	1	localCursor	Cursor
    //   55	140	2	localArrayList	ArrayList
    //   196	8	2	localObject	Object
    //   72	94	3	localB	b
    //   175	2	3	localException	Exception
    // Exception table:
    //   from	to	target	type
    //   56	172	175	java/lang/Exception
    //   56	172	196	finally
    //   176	180	196	finally
  }
  
  public static boolean b()
  {
    Bundle localBundle = c.a(a(com.ihs.app.framework.a.a()), "METHOD_GET_FIRST_GUIDE_FLAG", null, null);
    return (localBundle != null) && (localBundle.getBoolean("EXTRA_IS_FIRST_GUIDE_FLAG"));
  }
  
  public static Uri c(Context paramContext)
  {
    return Uri.parse("content://" + paramContext.getPackageName() + ".notification_organizer/block_apps");
  }
  
  public static void c()
  {
    Bundle localBundle = new Bundle();
    localBundle.putBoolean("EXTRA_IS_FIRST_GUIDE_FLAG", false);
    c.a(a(com.ihs.app.framework.a.a()), "METHOD_SET_FIRST_GUIDE_FLAG", null, localBundle);
  }
  
  public static void c(boolean paramBoolean)
  {
    Bundle localBundle = new Bundle();
    localBundle.putBoolean("EXTRA_IS_CUSTOM_HEADS_UP", paramBoolean);
    c.a(a(com.ihs.app.framework.a.a()), "METHOD_SET_CUSTOM_HEADS_UP_FLAG", null, localBundle);
  }
  
  public static Uri d(Context paramContext)
  {
    return Uri.parse("content://" + paramContext.getPackageName() + ".notification_organizer/unblock_list_changed");
  }
  
  private static String d(boolean paramBoolean)
  {
    if (paramBoolean) {
      return "post_time>? ";
    }
    Object localObject = c.a(c(com.ihs.app.framework.a.a()), "METHOD_GET_UNBLOCKED_APP_LIST", null, null);
    if (localObject == null) {
      return "post_time>? ";
    }
    ArrayList localArrayList = ((Bundle)localObject).getStringArrayList("EXTRA_APP_PACKAGE_NAME_LIST");
    if (localArrayList == null) {
      return "post_time>? ";
    }
    localObject = "package_name NOT IN (";
    if (!localArrayList.isEmpty()) {
      localObject = "package_name NOT IN (" + "'" + (String)localArrayList.get(0) + "'";
    }
    int i = 1;
    while (i < localArrayList.size())
    {
      localObject = (String)localObject + ",'" + (String)localArrayList.get(i) + "'";
      i += 1;
    }
    return (String)localObject + ") AND (post_time>?)";
  }
  
  public static void d()
  {
    c.a(a(com.ihs.app.framework.a.a()), "METHOD_UPDATE_CLEAN_TIME", null, null);
  }
  
  public static Uri e(Context paramContext)
  {
    return Uri.parse("content://" + paramContext.getPackageName() + ".notification_organizer/switcher");
  }
  
  public static boolean e()
  {
    Bundle localBundle = c.a(a(com.ihs.app.framework.a.a()), "METHOD_IS_NOTIFICATION_ORGANIZER_ENABLE_ONCE", null, null);
    return (localBundle != null) && (localBundle.getBoolean("EXTRA_IS_NOTIFICATION_ORGANIZER_ENABLE_ONCE"));
  }
  
  public static void f()
  {
    Bundle localBundle = new Bundle();
    localBundle.putBoolean("EXTRA_IS_NOTIFICATION_ORGANIZER_ENABLE_ONCE", true);
    c.a(a(com.ihs.app.framework.a.a()), "METHOD_SET_NOTIFICATION_ORGANIZER_ENABLE_ONCE", null, localBundle);
  }
  
  public static List<String> g()
  {
    Object localObject1 = com.ihs.app.framework.a.a().getContentResolver();
    Object localObject2 = com.ihs.app.framework.a.a();
    localObject2 = Uri.parse("content://" + ((Context)localObject2).getPackageName() + ".notification_organizer/block_notifications_app");
    String str = d(false);
    long l = com.optimizer.test.module.notificationorganizer.a.a();
    localObject1 = ((ContentResolver)localObject1).query((Uri)localObject2, new String[] { "package_name" }, str, new String[] { String.valueOf(l) }, "post_time DESC");
    if (localObject1 == null) {
      return new ArrayList();
    }
    localObject2 = new ArrayList();
    try
    {
      if (((Cursor)localObject1).moveToNext()) {
        ((List)localObject2).add(((Cursor)localObject1).getString(((Cursor)localObject1).getColumnIndex("package_name")));
      }
      return localList;
    }
    finally
    {
      ((Cursor)localObject1).close();
    }
  }
  
  public static int h()
  {
    Object localObject1 = com.ihs.app.framework.a.a().getContentResolver();
    Uri localUri = b(com.ihs.app.framework.a.a());
    String str = d(false);
    long l = com.optimizer.test.module.notificationorganizer.a.a();
    localObject1 = ((ContentResolver)localObject1).query(localUri, new String[] { "Count(*) count" }, str, new String[] { String.valueOf(l) }, null);
    if (localObject1 == null) {
      return 0;
    }
    for (;;)
    {
      try
      {
        if (((Cursor)localObject1).moveToFirst())
        {
          i = ((Cursor)localObject1).getInt(((Cursor)localObject1).getColumnIndex("count"));
          return i;
        }
      }
      finally
      {
        ((Cursor)localObject1).close();
      }
      int i = 0;
    }
  }
  
  public static boolean i()
  {
    Bundle localBundle = c.a(a(com.ihs.app.framework.a.a()), "METHOD_IS_CUSTOM_HEADS_UP", null, null);
    return (localBundle != null) && (localBundle.getBoolean("EXTRA_IS_CUSTOM_HEADS_UP"));
  }
  
  public static int j()
  {
    Bundle localBundle = c.a(a(com.ihs.app.framework.a.a()), "METHOD_GET_BLOCKED_AND_TIME_VALID_NOTIFICATION_COUNT", null, null);
    if (localBundle == null) {
      return 0;
    }
    return localBundle.getInt("EXTRA_BLOCKED_NOTIFICATION_COUNT", 0);
  }
  
  public static List<String> k()
  {
    Object localObject = c.a(a(com.ihs.app.framework.a.a()), "METHOD_GET_BLOCKED_AND_TIME_VALID_APP_PACKAGE_NAME_LIST", null, null);
    if (localObject == null) {
      localObject = new ArrayList();
    }
    ArrayList localArrayList;
    do
    {
      return localObject;
      localArrayList = ((Bundle)localObject).getStringArrayList("EXTRA_APP_PACKAGE_NAME_LIST");
      localObject = localArrayList;
    } while (localArrayList != null);
    return new ArrayList();
  }
  
  private ArrayList<String> m()
  {
    Object localObject = i.a(getContext(), "optimizer_notification_organizer").a("PREF_KEY_UNBLOCK_APP_LIST", null);
    ArrayList localArrayList = new ArrayList();
    if (TextUtils.isEmpty((CharSequence)localObject))
    {
      localObject = a;
      Iterator localIterator = com.ihs.app.framework.a.a().getPackageManager().getInstalledPackages(0).iterator();
      while (localIterator.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
        if (((List)localObject).contains(localPackageInfo.packageName)) {
          localArrayList.add(localPackageInfo.packageName);
        }
      }
      if (!localArrayList.contains(com.ihs.app.framework.a.a().getPackageName())) {
        localArrayList.add(com.ihs.app.framework.a.a().getPackageName());
      }
      i.a(getContext(), "optimizer_notification_organizer").c("PREF_KEY_UNBLOCK_APP_LIST", TextUtils.join(";", localArrayList));
      return localArrayList;
    }
    return new ArrayList(Arrays.asList(((String)localObject).split(";")));
  }
  
  public Bundle call(String paramString1, String paramString2, Bundle paramBundle)
  {
    paramString2 = new Bundle();
    int i = -1;
    switch (paramString1.hashCode())
    {
    default: 
      switch (i)
      {
      }
      break;
    }
    do
    {
      do
      {
        boolean bool;
        do
        {
          return paramString2;
          if (!paramString1.equals("METHOD_SET_SWITCH")) {
            break;
          }
          i = 0;
          break;
          if (!paramString1.equals("METHOD_GET_SWITCH")) {
            break;
          }
          i = 1;
          break;
          if (!paramString1.equals("METHOD_UPDATE_CLEAN_TIME")) {
            break;
          }
          i = 2;
          break;
          if (!paramString1.equals("METHOD_GET_CLEAN_TIME")) {
            break;
          }
          i = 3;
          break;
          if (!paramString1.equals("METHOD_ADD_APP_TO_UNBLOCK_LIST")) {
            break;
          }
          i = 4;
          break;
          if (!paramString1.equals("METHOD_REMOVE_APP_FROM_UNBLOCK_LIST")) {
            break;
          }
          i = 5;
          break;
          if (!paramString1.equals("METHOD_GET_UNBLOCKED_APP_LIST")) {
            break;
          }
          i = 6;
          break;
          if (!paramString1.equals("METHOD_GET_LAST_MAKING_TOAST_TIME")) {
            break;
          }
          i = 7;
          break;
          if (!paramString1.equals("METHOD_SET_LAST_MAKING_TOAST_TIME")) {
            break;
          }
          i = 8;
          break;
          if (!paramString1.equals("METHOD_GET_FIRST_GUIDE_FLAG")) {
            break;
          }
          i = 9;
          break;
          if (!paramString1.equals("METHOD_SET_FIRST_GUIDE_FLAG")) {
            break;
          }
          i = 10;
          break;
          if (!paramString1.equals("METHOD_HAS_BEEN_SWITCHED_OFF")) {
            break;
          }
          i = 11;
          break;
          if (!paramString1.equals("METHOD_GET_HIGHLIGHT_FLAG")) {
            break;
          }
          i = 12;
          break;
          if (!paramString1.equals("METHOD_SET_HIGHLIGHT_FLAT")) {
            break;
          }
          i = 13;
          break;
          if (!paramString1.equals("METHOD_IS_USER_CLOSED")) {
            break;
          }
          i = 14;
          break;
          if (!paramString1.equals("METHOD_SET_USER_CLOSED")) {
            break;
          }
          i = 15;
          break;
          if (!paramString1.equals("METHOD_SET_CUSTOM_HEADS_UP_FLAG")) {
            break;
          }
          i = 16;
          break;
          if (!paramString1.equals("METHOD_IS_CUSTOM_HEADS_UP")) {
            break;
          }
          i = 17;
          break;
          if (!paramString1.equals("METHOD_GET_BLOCKED_AND_TIME_VALID_NOTIFICATION_COUNT")) {
            break;
          }
          i = 18;
          break;
          if (!paramString1.equals("METHOD_GET_BLOCKED_AND_TIME_VALID_APP_PACKAGE_NAME_LIST")) {
            break;
          }
          i = 19;
          break;
          if (!paramString1.equals("METHOD_IS_NOTIFICATION_ORGANIZER_ENABLE_ONCE")) {
            break;
          }
          i = 20;
          break;
          if (!paramString1.equals("METHOD_SET_NOTIFICATION_ORGANIZER_ENABLE_ONCE")) {
            break;
          }
          i = 21;
          break;
          bool = paramBundle.getBoolean("EXTRA_SWITCH_STATE");
          paramString1 = i.a(getContext(), "optimizer_notification_organizer");
          paramString1.c("PREF_KEY_SWITCHER", bool);
          com.ihs.app.framework.a.a().getContentResolver().notifyChange(e(getContext()), null);
        } while (bool);
        paramString1.c("PREF_KEY_SWITCH_OFF_BEFORE_FLAG", true);
        return paramString2;
        paramString2.putBoolean("EXTRA_SWITCH_STATE", i.a(getContext(), "optimizer_notification_organizer").a("PREF_KEY_SWITCHER", false));
        return paramString2;
        i.a(getContext(), "optimizer_notification_organizer").b("PREF_KEY_CLEAN_TIME", System.currentTimeMillis());
        return paramString2;
        paramString2.putLong("EXTRA_CLEAN_TIME", i.a(getContext(), "optimizer_notification_organizer").a("PREF_KEY_CLEAN_TIME", 0L));
        return paramString2;
        paramString1 = m();
      } while (paramString1.contains(paramBundle.getString("EXTRA_APP_PACKAGE_NAME")));
      paramString1.add(paramBundle.getString("EXTRA_APP_PACKAGE_NAME"));
      i.a(getContext(), "optimizer_notification_organizer").c("PREF_KEY_UNBLOCK_APP_LIST", TextUtils.join(";", paramString1));
      com.ihs.app.framework.a.a().getContentResolver().notifyChange(c(getContext()), null);
      com.ihs.app.framework.a.a().getContentResolver().notifyChange(d(getContext()), null);
      return paramString2;
      paramString1 = m();
    } while (!paramString1.remove(paramBundle.getString("EXTRA_APP_PACKAGE_NAME")));
    i.a(getContext(), "optimizer_notification_organizer").c("PREF_KEY_UNBLOCK_APP_LIST", TextUtils.join(";", paramString1));
    com.ihs.app.framework.a.a().getContentResolver().notifyChange(c(getContext()), null);
    com.ihs.app.framework.a.a().getContentResolver().notifyChange(d(getContext()), null);
    return paramString2;
    paramString2.putStringArrayList("EXTRA_APP_PACKAGE_NAME_LIST", m());
    return paramString2;
    paramString2.putLong("EXTRA_KEY_TIME_MILLIS", i.a(getContext(), "optimizer_notification_organizer").a("PREF_KEY_TIME_MILLIS", 0L));
    return paramString2;
    i.a(getContext(), "optimizer_notification_organizer").b("PREF_KEY_TIME_MILLIS", paramBundle.getLong("EXTRA_KEY_TIME_MILLIS"));
    return paramString2;
    paramString2.putBoolean("EXTRA_IS_FIRST_GUIDE_FLAG", i.a(getContext(), "optimizer_notification_organizer").a("PREF_KEY_FIRST_GUIDE_FLAG", true));
    return paramString2;
    i.a(getContext(), "optimizer_notification_organizer").c("PREF_KEY_FIRST_GUIDE_FLAG", paramBundle.getBoolean("EXTRA_IS_FIRST_GUIDE_FLAG"));
    return paramString2;
    paramString2.putBoolean("EXTRA_HAS_BEEN_SWITCHED_OFF", i.a(getContext(), "optimizer_notification_organizer").a("PREF_KEY_SWITCH_OFF_BEFORE_FLAG", false));
    return paramString2;
    paramString2.putBoolean("EXTRA_IS_HIGHLIGHT", i.a(getContext(), "optimizer_notification_organizer").a("PREF_KEY_HIGHLIGHT_FLAG", true));
    return paramString2;
    i.a(getContext(), "optimizer_notification_organizer").c("PREF_KEY_HIGHLIGHT_FLAG", paramBundle.getBoolean("EXTRA_IS_HIGHLIGHT"));
    return paramString2;
    paramString2.putBoolean("EXTRA_IS_USER_CLOSED", i.a(getContext(), "optimizer_notification_organizer").a("PREF_KEY_IS_USER_CLOSED", false));
    return paramString2;
    i.a(getContext(), "optimizer_notification_organizer").c("PREF_KEY_IS_USER_CLOSED", paramBundle.getBoolean("EXTRA_IS_USER_CLOSED"));
    return paramString2;
    i.a(getContext(), "optimizer_notification_organizer").c("PREF_KEY_IS_CUSTOM_HEADS_UP", paramBundle.getBoolean("EXTRA_IS_CUSTOM_HEADS_UP"));
    return paramString2;
    paramString2.putBoolean("EXTRA_IS_CUSTOM_HEADS_UP", i.a(getContext(), "optimizer_notification_organizer").a("PREF_KEY_IS_CUSTOM_HEADS_UP", true));
    return paramString2;
    paramString2.putInt("EXTRA_BLOCKED_NOTIFICATION_COUNT", i.a(getContext(), "optimizer_notification_organizer").a("PREF_KEY_BLOCKED_AND_TIME_VALID_NOTIFICATION_COUNT", 0));
    return paramString2;
    paramString2.putStringArrayList("EXTRA_APP_PACKAGE_NAME_LIST", new ArrayList(Arrays.asList(i.a(getContext(), "optimizer_notification_organizer").a("PREF_KEY_BLOCKED_AND_TIME_VALID_APP_PACKAGE_NAME_LIST", "").split(";"))));
    return paramString2;
    paramString2.putBoolean("EXTRA_IS_NOTIFICATION_ORGANIZER_ENABLE_ONCE", i.a(getContext(), "optimizer_notification_organizer").a("PREF_KEY_IS_NOTIFICATION_ORGANIZER_ENABLE_ONCE", false));
    return paramString2;
    i.a(getContext(), "optimizer_notification_organizer").c("PREF_KEY_IS_NOTIFICATION_ORGANIZER_ENABLE_ONCE", paramBundle.getBoolean("EXTRA_IS_NOTIFICATION_ORGANIZER_ENABLE_ONCE"));
    return paramString2;
  }
  
  public int delete(Uri paramUri, String paramString, String[] paramArrayOfString)
  {
    int j;
    switch (this.b.match(paramUri))
    {
    default: 
      j = -1;
    }
    for (;;)
    {
      return j;
      try
      {
        i = this.c.getWritableDatabase().delete("BlockNotifications", paramString, paramArrayOfString);
        j = i;
        if (i > 0) {
          try
          {
            a(new Runnable()
            {
              public final void run()
              {
                com.ihs.app.framework.a.a().getContentResolver().notifyChange(NotificationOrganizerProvider.b(com.ihs.app.framework.a.a()), null);
              }
            });
            return i;
          }
          catch (Exception paramUri) {}
        }
      }
      catch (Exception paramUri)
      {
        for (;;)
        {
          int i = 0;
        }
      }
    }
    paramUri.printStackTrace();
    return i;
  }
  
  public String getType(Uri paramUri)
  {
    return null;
  }
  
  public Uri insert(Uri paramUri, ContentValues paramContentValues)
  {
    switch (this.b.match(paramUri))
    {
    default: 
      return null;
    }
    try
    {
      long l = this.c.getWritableDatabase().insert("BlockNotifications", null, paramContentValues);
      a(new Runnable()
      {
        public final void run()
        {
          com.ihs.app.framework.a.a().getContentResolver().notifyChange(NotificationOrganizerProvider.b(com.ihs.app.framework.a.a()), null);
        }
      });
      paramUri = ContentUris.withAppendedId(paramUri, l);
      return paramUri;
    }
    catch (Exception paramUri)
    {
      paramUri.printStackTrace();
    }
    return null;
  }
  
  public boolean onCreate()
  {
    this.b = new UriMatcher(-1);
    this.b.addURI(com.ihs.app.framework.a.a().getPackageName() + ".notification_organizer", "block_notifications", 3);
    this.b.addURI(com.ihs.app.framework.a.a().getPackageName() + ".notification_organizer", "block_notifications_app", 4);
    this.c = new a(getContext());
    return true;
  }
  
  public Cursor query(Uri paramUri, String[] paramArrayOfString1, String paramString1, String[] paramArrayOfString2, String paramString2)
  {
    switch (this.b.match(paramUri))
    {
    }
    for (;;)
    {
      return null;
      try
      {
        paramUri = this.c.a(paramArrayOfString1, paramString1, paramArrayOfString2, null, paramString2, "500");
        return paramUri;
      }
      catch (Exception paramUri)
      {
        paramUri.printStackTrace();
      }
      try
      {
        paramUri = this.c.a(paramArrayOfString1, paramString1, paramArrayOfString2, "package_name", paramString2, "7");
        return paramUri;
      }
      catch (Exception paramUri)
      {
        paramUri.printStackTrace();
      }
    }
  }
  
  public int update(Uri paramUri, ContentValues paramContentValues, String paramString, String[] paramArrayOfString)
  {
    return 0;
  }
}
