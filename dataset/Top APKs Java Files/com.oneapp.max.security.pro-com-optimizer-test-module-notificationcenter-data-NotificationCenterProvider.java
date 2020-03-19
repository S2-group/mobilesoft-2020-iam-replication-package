package com.optimizer.test.module.notificationcenter.data;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.ihs.commons.e.c;
import com.ihs.commons.e.i;
import com.optimizer.test.f;
import com.optimizer.test.g.r;
import com.optimizer.test.module.notificationcenter.d;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

public class NotificationCenterProvider
  extends ContentProvider
{
  public static final List<String> a = new NotificationCenterProvider.1();
  public static final ArrayList<String> b = new NotificationCenterProvider.2();
  private UriMatcher c;
  
  public NotificationCenterProvider() {}
  
  public static int A()
  {
    Object localObject2;
    try
    {
      Object localObject1 = com.ihs.app.framework.a.a().getContentResolver();
      Uri localUri = e(com.ihs.app.framework.a.a());
      String str = g(false);
      long l = com.optimizer.test.module.notificationorganizer.a.a(7);
      localObject1 = ((ContentResolver)localObject1).query(localUri, new String[] { "Count(*) as count" }, str, new String[] { String.valueOf(l) }, null);
      if (localObject1 == null) {
        return 0;
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localObject2 = null;
      }
    }
    for (;;)
    {
      try
      {
        if (localObject2.moveToFirst())
        {
          i = localObject2.getInt(localObject2.getColumnIndex("count"));
          return i;
        }
      }
      finally
      {
        localObject2.close();
      }
      int i = 0;
    }
  }
  
  /* Error */
  public static List<com.optimizer.test.module.notificationorganizer.data.b> B()
  {
    // Byte code:
    //   0: invokestatic 37	com/ihs/app/framework/a:a	()Landroid/content/Context;
    //   3: invokevirtual 43	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   6: invokestatic 37	com/ihs/app/framework/a:a	()Landroid/content/Context;
    //   9: invokestatic 47	com/optimizer/test/module/notificationcenter/data/NotificationCenterProvider:e	(Landroid/content/Context;)Landroid/net/Uri;
    //   12: aconst_null
    //   13: iconst_1
    //   14: invokestatic 51	com/optimizer/test/module/notificationcenter/data/NotificationCenterProvider:g	(Z)Ljava/lang/String;
    //   17: iconst_1
    //   18: anewarray 58	java/lang/String
    //   21: dup
    //   22: iconst_0
    //   23: bipush 7
    //   25: invokestatic 56	com/optimizer/test/module/notificationorganizer/a:a	(I)J
    //   28: invokestatic 64	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   31: aastore
    //   32: ldc 93
    //   34: invokevirtual 70	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   37: astore_0
    //   38: aload_0
    //   39: ifnonnull +17 -> 56
    //   42: new 95	java/util/ArrayList
    //   45: dup
    //   46: invokespecial 96	java/util/ArrayList:<init>	()V
    //   49: areturn
    //   50: astore_0
    //   51: aconst_null
    //   52: astore_0
    //   53: goto -15 -> 38
    //   56: new 95	java/util/ArrayList
    //   59: dup
    //   60: invokespecial 96	java/util/ArrayList:<init>	()V
    //   63: astore_1
    //   64: aload_0
    //   65: invokeinterface 99 1 0
    //   70: ifeq +144 -> 214
    //   73: new 101	com/optimizer/test/module/notificationorganizer/data/b
    //   76: dup
    //   77: invokespecial 102	com/optimizer/test/module/notificationorganizer/data/b:<init>	()V
    //   80: astore_2
    //   81: aload_2
    //   82: aload_0
    //   83: aload_0
    //   84: ldc 104
    //   86: invokeinterface 82 2 0
    //   91: invokeinterface 108 2 0
    //   96: putfield 112	com/optimizer/test/module/notificationorganizer/data/b:d	Ljava/lang/String;
    //   99: aload_2
    //   100: aload_0
    //   101: aload_0
    //   102: ldc 114
    //   104: invokeinterface 82 2 0
    //   109: invokeinterface 117 2 0
    //   114: putfield 121	com/optimizer/test/module/notificationorganizer/data/b:j	J
    //   117: aload_2
    //   118: aload_0
    //   119: aload_0
    //   120: ldc 123
    //   122: invokeinterface 82 2 0
    //   127: invokeinterface 108 2 0
    //   132: putfield 126	com/optimizer/test/module/notificationorganizer/data/b:h	Ljava/lang/String;
    //   135: aload_2
    //   136: aload_0
    //   137: aload_0
    //   138: ldc -128
    //   140: invokeinterface 82 2 0
    //   145: invokeinterface 108 2 0
    //   150: putfield 130	com/optimizer/test/module/notificationorganizer/data/b:g	Ljava/lang/String;
    //   153: aload_2
    //   154: aload_0
    //   155: aload_0
    //   156: ldc -124
    //   158: invokeinterface 82 2 0
    //   163: invokeinterface 86 2 0
    //   168: i2l
    //   169: putfield 134	com/optimizer/test/module/notificationorganizer/data/b:a	J
    //   172: aload_2
    //   173: aload_0
    //   174: aload_0
    //   175: ldc -120
    //   177: invokeinterface 82 2 0
    //   182: invokeinterface 108 2 0
    //   187: putfield 139	com/optimizer/test/module/notificationorganizer/data/b:k	Ljava/lang/String;
    //   190: aload_1
    //   191: aload_2
    //   192: invokeinterface 145 2 0
    //   197: pop
    //   198: goto -134 -> 64
    //   201: astore_2
    //   202: aload_2
    //   203: invokevirtual 148	java/lang/Exception:printStackTrace	()V
    //   206: aload_0
    //   207: invokeinterface 89 1 0
    //   212: aload_1
    //   213: areturn
    //   214: aload_0
    //   215: invokeinterface 89 1 0
    //   220: aload_1
    //   221: areturn
    //   222: astore_1
    //   223: aload_0
    //   224: invokeinterface 89 1 0
    //   229: aload_1
    //   230: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   37	2	0	localCursor	Cursor
    //   50	1	0	localException1	Exception
    //   52	172	0	localObject1	Object
    //   63	158	1	localArrayList	ArrayList
    //   222	8	1	localObject2	Object
    //   80	112	2	localB	com.optimizer.test.module.notificationorganizer.data.b
    //   201	2	2	localException2	Exception
    // Exception table:
    //   from	to	target	type
    //   0	38	50	java/lang/Exception
    //   64	198	201	java/lang/Exception
    //   64	198	222	finally
    //   202	206	222	finally
  }
  
  /* Error */
  public static com.optimizer.test.module.notificationorganizer.data.b C()
  {
    // Byte code:
    //   0: invokestatic 37	com/ihs/app/framework/a:a	()Landroid/content/Context;
    //   3: invokevirtual 43	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   6: invokestatic 37	com/ihs/app/framework/a:a	()Landroid/content/Context;
    //   9: invokestatic 47	com/optimizer/test/module/notificationcenter/data/NotificationCenterProvider:e	(Landroid/content/Context;)Landroid/net/Uri;
    //   12: aconst_null
    //   13: ldc -102
    //   15: aconst_null
    //   16: ldc 114
    //   18: invokevirtual 70	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   21: astore_1
    //   22: aload_1
    //   23: ifnonnull +11 -> 34
    //   26: aconst_null
    //   27: areturn
    //   28: astore_0
    //   29: aconst_null
    //   30: astore_1
    //   31: goto -9 -> 22
    //   34: aconst_null
    //   35: astore_0
    //   36: aload_1
    //   37: invokeinterface 99 1 0
    //   42: ifeq +125 -> 167
    //   45: new 101	com/optimizer/test/module/notificationorganizer/data/b
    //   48: dup
    //   49: invokespecial 102	com/optimizer/test/module/notificationorganizer/data/b:<init>	()V
    //   52: astore_2
    //   53: aload_2
    //   54: aload_1
    //   55: aload_1
    //   56: ldc 104
    //   58: invokeinterface 82 2 0
    //   63: invokeinterface 108 2 0
    //   68: putfield 112	com/optimizer/test/module/notificationorganizer/data/b:d	Ljava/lang/String;
    //   71: aload_2
    //   72: aload_1
    //   73: aload_1
    //   74: ldc 114
    //   76: invokeinterface 82 2 0
    //   81: invokeinterface 117 2 0
    //   86: putfield 121	com/optimizer/test/module/notificationorganizer/data/b:j	J
    //   89: aload_2
    //   90: aload_1
    //   91: aload_1
    //   92: ldc 123
    //   94: invokeinterface 82 2 0
    //   99: invokeinterface 108 2 0
    //   104: putfield 126	com/optimizer/test/module/notificationorganizer/data/b:h	Ljava/lang/String;
    //   107: aload_2
    //   108: aload_1
    //   109: aload_1
    //   110: ldc -128
    //   112: invokeinterface 82 2 0
    //   117: invokeinterface 108 2 0
    //   122: putfield 130	com/optimizer/test/module/notificationorganizer/data/b:g	Ljava/lang/String;
    //   125: aload_2
    //   126: aload_1
    //   127: aload_1
    //   128: ldc -124
    //   130: invokeinterface 82 2 0
    //   135: invokeinterface 86 2 0
    //   140: i2l
    //   141: putfield 134	com/optimizer/test/module/notificationorganizer/data/b:a	J
    //   144: aload_2
    //   145: aload_1
    //   146: aload_1
    //   147: ldc -120
    //   149: invokeinterface 82 2 0
    //   154: invokeinterface 108 2 0
    //   159: putfield 139	com/optimizer/test/module/notificationorganizer/data/b:k	Ljava/lang/String;
    //   162: aload_2
    //   163: astore_0
    //   164: goto -128 -> 36
    //   167: aload_1
    //   168: invokeinterface 89 1 0
    //   173: aload_0
    //   174: areturn
    //   175: astore_3
    //   176: aload_2
    //   177: astore_0
    //   178: aload_3
    //   179: astore_2
    //   180: aload_2
    //   181: invokevirtual 148	java/lang/Exception:printStackTrace	()V
    //   184: aload_1
    //   185: invokeinterface 89 1 0
    //   190: aload_0
    //   191: areturn
    //   192: astore_0
    //   193: aload_1
    //   194: invokeinterface 89 1 0
    //   199: aload_0
    //   200: athrow
    //   201: astore_2
    //   202: goto -22 -> 180
    // Local variable table:
    //   start	length	slot	name	signature
    //   28	1	0	localException1	Exception
    //   35	156	0	localObject1	Object
    //   192	8	0	localObject2	Object
    //   21	173	1	localCursor	Cursor
    //   52	129	2	localObject3	Object
    //   201	1	2	localException2	Exception
    //   175	4	3	localException3	Exception
    // Exception table:
    //   from	to	target	type
    //   0	22	28	java/lang/Exception
    //   53	162	175	java/lang/Exception
    //   36	53	192	finally
    //   53	162	192	finally
    //   180	184	192	finally
    //   36	53	201	java/lang/Exception
  }
  
  public static int D()
  {
    Bundle localBundle = c.a(a(com.ihs.app.framework.a.a()), "METHOD_GET_BLOCKED_AND_TIME_VALID_NOTIFICATION_COUNT", null, null);
    if (localBundle == null) {
      return 0;
    }
    return localBundle.getInt("EXTRA_BLOCKED_NOTIFICATION_COUNT", 0);
  }
  
  public static List<String> E()
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
  
  public static int F()
  {
    Bundle localBundle = c.a(a(com.ihs.app.framework.a.a()), "METHOD_GET_PRIVATE_MSG_COUNT", null, null);
    if (localBundle == null) {
      return 0;
    }
    return localBundle.getInt("EXTRA_BLOCKED_NOTIFICATION_COUNT", 0);
  }
  
  public static List<String> G()
  {
    Object localObject = c.a(a(com.ihs.app.framework.a.a()), "METHOD_GET_PRIVATE_MSG_APP_LIST", null, null);
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
  
  public static void H()
  {
    Object localObject = d(true);
    List localList = t();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      com.optimizer.test.module.notificationorganizer.data.b localB = (com.optimizer.test.module.notificationorganizer.data.b)((Iterator)localObject).next();
      if (localList.contains(localB.d))
      {
        ContentValues localContentValues = new ContentValues();
        localContentValues.put("notification_type", Integer.valueOf(1));
        com.ihs.app.framework.a.a().getContentResolver().update(b(com.ihs.app.framework.a.a()), localContentValues, "id = " + localB.a, null);
      }
    }
  }
  
  private ArrayList<String> K()
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
  
  private ArrayList<String> L()
  {
    i localI = i.a(getContext(), "optimizer_message_security");
    Object localObject1 = localI.a("PREF_KEY_KEEP_MESSAGE_PRIVATE_APP_LIST", null);
    ArrayList localArrayList = new ArrayList();
    if (!TextUtils.isEmpty((CharSequence)localObject1)) {
      localArrayList.addAll(Arrays.asList(((String)localObject1).split(";")));
    }
    while (localI.a("PREF_KEY_PRIVATE_APP_LIST_INITIALIZED", false)) {
      return localArrayList;
    }
    Object localObject2 = com.ihs.app.framework.a.a().getPackageManager().getInstalledPackages(0);
    localObject1 = d.c();
    ((List)localObject1).addAll(b);
    localObject2 = ((List)localObject2).iterator();
    while (((Iterator)localObject2).hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject2).next();
      if (((List)localObject1).contains(localPackageInfo.packageName)) {
        localArrayList.add(localPackageInfo.packageName);
      }
    }
    localI.c("PREF_KEY_KEEP_MESSAGE_PRIVATE_APP_LIST", TextUtils.join(";", localArrayList));
    localI.c("PREF_KEY_PRIVATE_APP_LIST_INITIALIZED", true);
    return localArrayList;
  }
  
  public static Uri a(Context paramContext)
  {
    return Uri.parse("content://" + paramContext.getPackageName() + ".notification_center/");
  }
  
  private void a(Runnable paramRunnable)
  {
    f.a().a.execute(new NotificationCenterProvider.7(this, paramRunnable));
  }
  
  public static void a(String paramString)
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("EXTRA_APP_PACKAGE_NAME", paramString);
    c.a(f(com.ihs.app.framework.a.a()), "METHOD_ADD_APP_TO_MESSAGE_PRIVATE_APP_LIST", null, localBundle);
  }
  
  public static void a(ArrayList<String> paramArrayList)
  {
    Bundle localBundle = new Bundle();
    localBundle.putStringArrayList("EXTRA_APP_PACKAGE_NAME_LIST", paramArrayList);
    c.a(f(com.ihs.app.framework.a.a()), "METHOD_UPDATE_PRIVATE_MESSAGE_APP_LIST", null, localBundle);
  }
  
  public static void a(boolean paramBoolean)
  {
    if ((TextUtils.equals(net.appcloudbox.autopilot.b.a("topic-1529906157749-350", "private_msg_app_select", "default"), "guidepage")) && (f())) {
      e(paramBoolean);
    }
    for (;;)
    {
      if (!paramBoolean)
      {
        Bundle localBundle = new Bundle();
        localBundle.putBoolean("EXTRA_IS_USER_CLOSED", true);
        c.a(a(com.ihs.app.framework.a.a()), "METHOD_SET_USER_CLOSED", null, localBundle);
      }
      if ((paramBoolean) && (r.a(com.ihs.app.framework.a.a()))) {
        x();
      }
      return;
      e(paramBoolean);
      b(paramBoolean);
    }
  }
  
  public static boolean a()
  {
    Bundle localBundle = c.a(g(com.ihs.app.framework.a.a()), "METHOD_GET_NOTIFICATION_ORGANIZER_SWITCH", null, null);
    return (localBundle != null) && (localBundle.getBoolean("EXTRA_SWITCH_STATE"));
  }
  
  public static Uri b(Context paramContext)
  {
    return Uri.parse("content://" + paramContext.getPackageName() + ".notification_center/block_notifications");
  }
  
  private void b(Runnable paramRunnable)
  {
    f.a().a.execute(new NotificationCenterProvider.8(this, paramRunnable));
  }
  
  public static void b(String paramString)
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("EXTRA_APP_PACKAGE_NAME", paramString);
    c.a(f(com.ihs.app.framework.a.a()), "METHOD_REMOVE_APP_FROM_MESSAGE_PRIVATE_APP_LIST", null, localBundle);
  }
  
  public static void b(boolean paramBoolean)
  {
    Bundle localBundle = new Bundle();
    localBundle.putBoolean("EXTRA_SWITCH_STATE", paramBoolean);
    c.a(g(com.ihs.app.framework.a.a()), "METHOD_SET_PRIVATE_MESSAGE_SWITCH", null, localBundle);
    localBundle = new Bundle();
    localBundle.putBoolean("EXTRA_IS_PRIVATE_MESSAGE_ENABLE_ONCE", true);
    c.a(a(com.ihs.app.framework.a.a()), "METHOD_SET_PRIVATE_MESSAGE_ENABLE_ONCE", null, localBundle);
  }
  
  public static boolean b()
  {
    Bundle localBundle = c.a(g(com.ihs.app.framework.a.a()), "METHOD_GET_PRIVATE_MESSAGE_SWITCH", null, null);
    return (localBundle != null) && (localBundle.getBoolean("EXTRA_SWITCH_STATE"));
  }
  
  public static Uri c(Context paramContext)
  {
    return Uri.parse("content://" + paramContext.getPackageName() + ".notification_center/block_apps");
  }
  
  public static void c(String paramString)
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("EXTRA_APP_PACKAGE_NAME", paramString);
    c.a(c(com.ihs.app.framework.a.a()), "METHOD_ADD_APP_TO_UNBLOCK_LIST", null, localBundle);
  }
  
  public static void c(boolean paramBoolean)
  {
    Bundle localBundle = new Bundle();
    localBundle.putBoolean("EXTRA_IS_CUSTOM_HEADS_UP", paramBoolean);
    c.a(a(com.ihs.app.framework.a.a()), "METHOD_SET_CUSTOM_HEADS_UP_FLAG", null, localBundle);
  }
  
  public static boolean c()
  {
    return (a()) || (b());
  }
  
  public static Uri d(Context paramContext)
  {
    return Uri.parse("content://" + paramContext.getPackageName() + ".notification_center/unblock_list_changed");
  }
  
  /* Error */
  public static List<com.optimizer.test.module.notificationorganizer.data.b> d(boolean paramBoolean)
  {
    // Byte code:
    //   0: invokestatic 37	com/ihs/app/framework/a:a	()Landroid/content/Context;
    //   3: invokevirtual 43	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   6: invokestatic 37	com/ihs/app/framework/a:a	()Landroid/content/Context;
    //   9: invokestatic 226	com/optimizer/test/module/notificationcenter/data/NotificationCenterProvider:b	(Landroid/content/Context;)Landroid/net/Uri;
    //   12: aconst_null
    //   13: iload_0
    //   14: invokestatic 249	com/optimizer/test/module/notificationcenter/data/NotificationCenterProvider:f	(Z)Ljava/lang/String;
    //   17: iconst_1
    //   18: anewarray 58	java/lang/String
    //   21: dup
    //   22: iconst_0
    //   23: bipush 7
    //   25: invokestatic 56	com/optimizer/test/module/notificationorganizer/a:a	(I)J
    //   28: invokestatic 64	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   31: aastore
    //   32: ldc 93
    //   34: invokevirtual 70	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   37: astore_1
    //   38: aload_1
    //   39: ifnonnull +17 -> 56
    //   42: new 95	java/util/ArrayList
    //   45: dup
    //   46: invokespecial 96	java/util/ArrayList:<init>	()V
    //   49: areturn
    //   50: astore_1
    //   51: aconst_null
    //   52: astore_1
    //   53: goto -15 -> 38
    //   56: new 95	java/util/ArrayList
    //   59: dup
    //   60: invokespecial 96	java/util/ArrayList:<init>	()V
    //   63: astore_2
    //   64: aload_1
    //   65: invokeinterface 99 1 0
    //   70: ifeq +144 -> 214
    //   73: new 101	com/optimizer/test/module/notificationorganizer/data/b
    //   76: dup
    //   77: invokespecial 102	com/optimizer/test/module/notificationorganizer/data/b:<init>	()V
    //   80: astore_3
    //   81: aload_3
    //   82: aload_1
    //   83: aload_1
    //   84: ldc 104
    //   86: invokeinterface 82 2 0
    //   91: invokeinterface 108 2 0
    //   96: putfield 112	com/optimizer/test/module/notificationorganizer/data/b:d	Ljava/lang/String;
    //   99: aload_3
    //   100: aload_1
    //   101: aload_1
    //   102: ldc 114
    //   104: invokeinterface 82 2 0
    //   109: invokeinterface 117 2 0
    //   114: putfield 121	com/optimizer/test/module/notificationorganizer/data/b:j	J
    //   117: aload_3
    //   118: aload_1
    //   119: aload_1
    //   120: ldc 123
    //   122: invokeinterface 82 2 0
    //   127: invokeinterface 108 2 0
    //   132: putfield 126	com/optimizer/test/module/notificationorganizer/data/b:h	Ljava/lang/String;
    //   135: aload_3
    //   136: aload_1
    //   137: aload_1
    //   138: ldc -128
    //   140: invokeinterface 82 2 0
    //   145: invokeinterface 108 2 0
    //   150: putfield 130	com/optimizer/test/module/notificationorganizer/data/b:g	Ljava/lang/String;
    //   153: aload_3
    //   154: aload_1
    //   155: aload_1
    //   156: ldc -124
    //   158: invokeinterface 82 2 0
    //   163: invokeinterface 86 2 0
    //   168: i2l
    //   169: putfield 134	com/optimizer/test/module/notificationorganizer/data/b:a	J
    //   172: aload_3
    //   173: aload_1
    //   174: aload_1
    //   175: ldc -120
    //   177: invokeinterface 82 2 0
    //   182: invokeinterface 108 2 0
    //   187: putfield 139	com/optimizer/test/module/notificationorganizer/data/b:k	Ljava/lang/String;
    //   190: aload_2
    //   191: aload_3
    //   192: invokeinterface 145 2 0
    //   197: pop
    //   198: goto -134 -> 64
    //   201: astore_3
    //   202: aload_3
    //   203: invokevirtual 148	java/lang/Exception:printStackTrace	()V
    //   206: aload_1
    //   207: invokeinterface 89 1 0
    //   212: aload_2
    //   213: areturn
    //   214: aload_1
    //   215: invokeinterface 89 1 0
    //   220: aload_2
    //   221: areturn
    //   222: astore_2
    //   223: aload_1
    //   224: invokeinterface 89 1 0
    //   229: aload_2
    //   230: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	231	0	paramBoolean	boolean
    //   37	2	1	localCursor	Cursor
    //   50	1	1	localException1	Exception
    //   52	172	1	localObject1	Object
    //   63	158	2	localArrayList	ArrayList
    //   222	8	2	localObject2	Object
    //   80	112	3	localB	com.optimizer.test.module.notificationorganizer.data.b
    //   201	2	3	localException2	Exception
    // Exception table:
    //   from	to	target	type
    //   0	38	50	java/lang/Exception
    //   64	198	201	java/lang/Exception
    //   64	198	222	finally
    //   202	206	222	finally
  }
  
  public static void d()
  {
    c.a(g(com.ihs.app.framework.a.a()), "METHOD_CLEAR_PRIVATE_MESSAGE_APP_LIST", null, null);
  }
  
  public static void d(String paramString)
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("EXTRA_APP_PACKAGE_NAME", paramString);
    c.a(c(com.ihs.app.framework.a.a()), "METHOD_REMOVE_APP_FROM_UNBLOCK_LIST", null, localBundle);
  }
  
  public static Uri e(Context paramContext)
  {
    return Uri.parse("content://" + paramContext.getPackageName() + ".notification_center/message_security_notifications");
  }
  
  public static void e()
  {
    c.a(g(com.ihs.app.framework.a.a()), "METHOD_FILL_JUNK_NOTIFICATION_APP_LIST", null, null);
  }
  
  private static void e(boolean paramBoolean)
  {
    Bundle localBundle = new Bundle();
    localBundle.putBoolean("EXTRA_SWITCH_STATE", paramBoolean);
    c.a(g(com.ihs.app.framework.a.a()), "METHOD_SET_NOTIFICATION_ORGANIZER_SWITCH", null, localBundle);
  }
  
  public static Uri f(Context paramContext)
  {
    return Uri.parse("content://" + paramContext.getPackageName() + ".notification_center/keep_apps");
  }
  
  private static String f(boolean paramBoolean)
  {
    if (paramBoolean) {
      return "post_time>? AND " + "notification_type = 0";
    }
    Object localObject = c.a(c(com.ihs.app.framework.a.a()), "METHOD_GET_UNBLOCKED_APP_LIST", null, null);
    if (localObject == null) {
      return "post_time>? AND " + "notification_type = 0";
    }
    ArrayList localArrayList = ((Bundle)localObject).getStringArrayList("EXTRA_APP_PACKAGE_NAME_LIST");
    if (localArrayList == null) {
      return "post_time>? AND " + "notification_type = 0";
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
    return (String)localObject + ") AND (post_time>?) AND " + "notification_type = 0";
  }
  
  public static boolean f()
  {
    Bundle localBundle = c.a(a(com.ihs.app.framework.a.a()), "METHOD_GET_FIRST_GUIDE_FLAG", null, null);
    return (localBundle != null) && (localBundle.getBoolean("EXTRA_IS_FIRST_GUIDE_FLAG"));
  }
  
  public static Uri g(Context paramContext)
  {
    return Uri.parse("content://" + paramContext.getPackageName() + ".notification_center/switcher");
  }
  
  private static String g(boolean paramBoolean)
  {
    if (paramBoolean) {
      return "post_time>? AND " + "notification_type = 1";
    }
    Object localObject = c.a(f(com.ihs.app.framework.a.a()), "METHOD_GET_KEEP_MESSAGE_PRIVATE_APP_LIST", null, null);
    if (localObject == null) {
      return "post_time>? AND " + "notification_type = 1";
    }
    ArrayList localArrayList = ((Bundle)localObject).getStringArrayList("EXTRA_APP_PACKAGE_NAME_LIST");
    if (localArrayList == null) {
      return "post_time>? AND " + "notification_type = 1";
    }
    localObject = "package_name IN (";
    if (!localArrayList.isEmpty()) {
      localObject = "package_name IN (" + "'GUIDE_ITEM_PACKAGE_NAME_PRIVATE_MSG'";
    }
    int i = 0;
    while (i < localArrayList.size())
    {
      localObject = (String)localObject + ",'" + (String)localArrayList.get(i) + "'";
      i += 1;
    }
    return (String)localObject + ") AND (post_time>? AND " + "notification_type = 1" + ")";
  }
  
  public static void g()
  {
    Bundle localBundle = new Bundle();
    localBundle.putBoolean("EXTRA_IS_FIRST_GUIDE_FLAG", false);
    c.a(a(com.ihs.app.framework.a.a()), "METHOD_SET_FIRST_GUIDE_FLAG", null, localBundle);
  }
  
  public static Uri h(Context paramContext)
  {
    return Uri.parse("content://" + paramContext.getPackageName() + ".notification_center/update_unread_private_message");
  }
  
  public static boolean h()
  {
    Bundle localBundle = c.a(a(com.ihs.app.framework.a.a()), "METHOD_IS_CUSTOM_HEADS_UP", null, null);
    return (localBundle != null) && (localBundle.getBoolean("EXTRA_IS_CUSTOM_HEADS_UP"));
  }
  
  public static void i()
  {
    c.a(a(com.ihs.app.framework.a.a()), "METHOD_UPDATE_CLEAN_TIME", null, null);
  }
  
  public static void j()
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("notification_read_status", Integer.valueOf(1));
    com.ihs.app.framework.a.a().getContentResolver().update(e(com.ihs.app.framework.a.a()), localContentValues, "notification_read_status=?", new String[] { "0" });
  }
  
  public static void k()
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("notification_read_status", Integer.valueOf(1));
    com.ihs.app.framework.a.a().getContentResolver().update(b(com.ihs.app.framework.a.a()), localContentValues, "notification_read_status=?", new String[] { "0" });
  }
  
  public static void l()
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("notification_remind_status", Integer.valueOf(1));
    com.ihs.app.framework.a.a().getContentResolver().update(e(com.ihs.app.framework.a.a()), localContentValues, "notification_remind_status=?", new String[] { "0" });
  }
  
  public static void m()
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("notification_remind_status", Integer.valueOf(1));
    com.ihs.app.framework.a.a().getContentResolver().update(b(com.ihs.app.framework.a.a()), localContentValues, "notification_remind_status=?", new String[] { "0" });
  }
  
  /* Error */
  public static List<com.optimizer.test.module.notificationorganizer.data.b> n()
  {
    // Byte code:
    //   0: new 95	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 96	java/util/ArrayList:<init>	()V
    //   7: astore_1
    //   8: invokestatic 37	com/ihs/app/framework/a:a	()Landroid/content/Context;
    //   11: invokevirtual 43	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   14: invokestatic 37	com/ihs/app/framework/a:a	()Landroid/content/Context;
    //   17: invokestatic 47	com/optimizer/test/module/notificationcenter/data/NotificationCenterProvider:e	(Landroid/content/Context;)Landroid/net/Uri;
    //   20: aconst_null
    //   21: ldc_w 533
    //   24: iconst_1
    //   25: anewarray 58	java/lang/String
    //   28: dup
    //   29: iconst_0
    //   30: ldc_w 535
    //   33: aastore
    //   34: aconst_null
    //   35: invokevirtual 70	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   38: astore_0
    //   39: aload_0
    //   40: ifnonnull +11 -> 51
    //   43: aload_1
    //   44: areturn
    //   45: astore_0
    //   46: aconst_null
    //   47: astore_0
    //   48: goto -9 -> 39
    //   51: aload_0
    //   52: invokeinterface 99 1 0
    //   57: ifeq +144 -> 201
    //   60: new 101	com/optimizer/test/module/notificationorganizer/data/b
    //   63: dup
    //   64: invokespecial 102	com/optimizer/test/module/notificationorganizer/data/b:<init>	()V
    //   67: astore_2
    //   68: aload_2
    //   69: aload_0
    //   70: aload_0
    //   71: ldc 104
    //   73: invokeinterface 82 2 0
    //   78: invokeinterface 108 2 0
    //   83: putfield 112	com/optimizer/test/module/notificationorganizer/data/b:d	Ljava/lang/String;
    //   86: aload_2
    //   87: aload_0
    //   88: aload_0
    //   89: ldc 114
    //   91: invokeinterface 82 2 0
    //   96: invokeinterface 117 2 0
    //   101: putfield 121	com/optimizer/test/module/notificationorganizer/data/b:j	J
    //   104: aload_2
    //   105: aload_0
    //   106: aload_0
    //   107: ldc 123
    //   109: invokeinterface 82 2 0
    //   114: invokeinterface 108 2 0
    //   119: putfield 126	com/optimizer/test/module/notificationorganizer/data/b:h	Ljava/lang/String;
    //   122: aload_2
    //   123: aload_0
    //   124: aload_0
    //   125: ldc -128
    //   127: invokeinterface 82 2 0
    //   132: invokeinterface 108 2 0
    //   137: putfield 130	com/optimizer/test/module/notificationorganizer/data/b:g	Ljava/lang/String;
    //   140: aload_2
    //   141: aload_0
    //   142: aload_0
    //   143: ldc -124
    //   145: invokeinterface 82 2 0
    //   150: invokeinterface 86 2 0
    //   155: i2l
    //   156: putfield 134	com/optimizer/test/module/notificationorganizer/data/b:a	J
    //   159: aload_2
    //   160: aload_0
    //   161: aload_0
    //   162: ldc -120
    //   164: invokeinterface 82 2 0
    //   169: invokeinterface 108 2 0
    //   174: putfield 139	com/optimizer/test/module/notificationorganizer/data/b:k	Ljava/lang/String;
    //   177: aload_1
    //   178: aload_2
    //   179: invokeinterface 145 2 0
    //   184: pop
    //   185: goto -134 -> 51
    //   188: astore_2
    //   189: aload_2
    //   190: invokevirtual 148	java/lang/Exception:printStackTrace	()V
    //   193: aload_0
    //   194: invokeinterface 89 1 0
    //   199: aload_1
    //   200: areturn
    //   201: aload_0
    //   202: invokeinterface 89 1 0
    //   207: goto -8 -> 199
    //   210: astore_1
    //   211: aload_0
    //   212: invokeinterface 89 1 0
    //   217: aload_1
    //   218: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   38	2	0	localCursor	Cursor
    //   45	1	0	localException1	Exception
    //   47	165	0	localObject1	Object
    //   7	193	1	localArrayList	ArrayList
    //   210	8	1	localObject2	Object
    //   67	112	2	localB	com.optimizer.test.module.notificationorganizer.data.b
    //   188	2	2	localException2	Exception
    // Exception table:
    //   from	to	target	type
    //   8	39	45	java/lang/Exception
    //   51	185	188	java/lang/Exception
    //   51	185	210	finally
    //   189	193	210	finally
  }
  
  public static int o()
  {
    Object localObject1;
    try
    {
      Cursor localCursor = com.ihs.app.framework.a.a().getContentResolver().query(b(com.ihs.app.framework.a.a()), new String[] { "Count(*) as count" }, "notification_remind_status=?", new String[] { "0" }, null);
      if (localCursor == null) {
        return 0;
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localObject1 = null;
      }
    }
    for (;;)
    {
      try
      {
        if (localObject1.moveToFirst())
        {
          i = localObject1.getInt(localObject1.getColumnIndex("count"));
          return i;
        }
      }
      finally
      {
        localObject1.close();
      }
      int i = 0;
    }
  }
  
  public static int p()
  {
    Object localObject1;
    try
    {
      Cursor localCursor = com.ihs.app.framework.a.a().getContentResolver().query(e(com.ihs.app.framework.a.a()), new String[] { "Count(*) as count" }, "notification_remind_status=?", new String[] { "0" }, null);
      if (localCursor == null) {
        return 0;
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localObject1 = null;
      }
    }
    for (;;)
    {
      try
      {
        if (localObject1.moveToFirst())
        {
          i = localObject1.getInt(localObject1.getColumnIndex("count"));
          return i;
        }
      }
      finally
      {
        localObject1.close();
      }
      int i = 0;
    }
  }
  
  public static int q()
  {
    Object localObject1;
    try
    {
      Cursor localCursor = com.ihs.app.framework.a.a().getContentResolver().query(e(com.ihs.app.framework.a.a()), new String[] { "Count(*) as count" }, "notification_read_status=?", new String[] { "0" }, null);
      if (localCursor == null) {
        return 0;
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localObject1 = null;
      }
    }
    for (;;)
    {
      try
      {
        if (localObject1.moveToFirst())
        {
          i = localObject1.getInt(localObject1.getColumnIndex("count"));
          return i;
        }
      }
      finally
      {
        localObject1.close();
      }
      int i = 0;
    }
  }
  
  public static int r()
  {
    Object localObject1;
    try
    {
      Cursor localCursor = com.ihs.app.framework.a.a().getContentResolver().query(b(com.ihs.app.framework.a.a()), new String[] { "Count(*) as count" }, "notification_read_status=?", new String[] { "0" }, null);
      if (localCursor == null) {
        return 0;
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localObject1 = null;
      }
    }
    for (;;)
    {
      try
      {
        if (localObject1.moveToFirst())
        {
          i = localObject1.getInt(localObject1.getColumnIndex("count"));
          return i;
        }
      }
      finally
      {
        localObject1.close();
      }
      int i = 0;
    }
  }
  
  public static List<String> s()
  {
    try
    {
      Object localObject1 = com.ihs.app.framework.a.a().getContentResolver();
      localObject3 = com.ihs.app.framework.a.a();
      localObject3 = Uri.parse("content://" + ((Context)localObject3).getPackageName() + ".notification_center/block_notifications_app");
      String str = f(false);
      long l = com.optimizer.test.module.notificationorganizer.a.a(7);
      localObject1 = ((ContentResolver)localObject1).query((Uri)localObject3, new String[] { "package_name" }, str, new String[] { String.valueOf(l) }, "post_time DESC");
      if (localObject1 == null) {
        return new ArrayList();
      }
    }
    catch (Exception localException)
    {
      Object localObject2;
      for (;;)
      {
        localObject2 = null;
      }
      Object localObject3 = new ArrayList();
      try
      {
        if (localObject2.moveToNext()) {
          ((List)localObject3).add(localObject2.getString(localObject2.getColumnIndex("package_name")));
        }
        return localList;
      }
      finally
      {
        localObject2.close();
      }
    }
  }
  
  public static List<String> t()
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject = c.a(f(com.ihs.app.framework.a.a()), "METHOD_GET_KEEP_MESSAGE_PRIVATE_APP_LIST", null, null);
    if (localObject != null)
    {
      localObject = ((Bundle)localObject).getStringArrayList("EXTRA_APP_PACKAGE_NAME_LIST");
      if (localObject != null) {
        localArrayList.addAll((Collection)localObject);
      }
    }
    return localArrayList;
  }
  
  public static List<String> u()
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject = c.a(c(com.ihs.app.framework.a.a()), "METHOD_GET_UNBLOCKED_APP_LIST", null, null);
    if (localObject != null)
    {
      localObject = ((Bundle)localObject).getStringArrayList("EXTRA_APP_PACKAGE_NAME_LIST");
      if (localObject != null) {
        localArrayList.addAll((Collection)localObject);
      }
    }
    return localArrayList;
  }
  
  public static long v()
  {
    Bundle localBundle = c.a(a(com.ihs.app.framework.a.a()), "METHOD_GET_CLEAN_TIME", null, null);
    if (localBundle == null) {
      return 0L;
    }
    return localBundle.getLong("EXTRA_CLEAN_TIME");
  }
  
  public static boolean w()
  {
    Bundle localBundle = c.a(a(com.ihs.app.framework.a.a()), "METHOD_IS_NOTIFICATION_ORGANIZER_ENABLE_ONCE", null, null);
    return (localBundle != null) && (localBundle.getBoolean("EXTRA_IS_NOTIFICATION_ORGANIZER_ENABLE_ONCE"));
  }
  
  public static void x()
  {
    Bundle localBundle = new Bundle();
    localBundle.putBoolean("EXTRA_IS_NOTIFICATION_ORGANIZER_ENABLE_ONCE", true);
    c.a(a(com.ihs.app.framework.a.a()), "METHOD_SET_NOTIFICATION_ORGANIZER_ENABLE_ONCE", null, localBundle);
  }
  
  public static boolean y()
  {
    Bundle localBundle = c.a(a(com.ihs.app.framework.a.a()), "METHOD_IS_PRIVATE_MESSAGE_ENABLE_ONCE", null, null);
    return (localBundle != null) && (localBundle.getBoolean("EXTRA_IS_PRIVATE_MESSAGE_ENABLE_ONCE"));
  }
  
  public static int z()
  {
    Object localObject2;
    try
    {
      Object localObject1 = com.ihs.app.framework.a.a().getContentResolver();
      Uri localUri = b(com.ihs.app.framework.a.a());
      String str = f(false);
      long l = com.optimizer.test.module.notificationorganizer.a.a(7);
      localObject1 = ((ContentResolver)localObject1).query(localUri, new String[] { "Count(*) as count" }, str, new String[] { String.valueOf(l) }, null);
      if (localObject1 == null) {
        return 0;
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localObject2 = null;
      }
    }
    for (;;)
    {
      try
      {
        if (localObject2.moveToFirst())
        {
          i = localObject2.getInt(localObject2.getColumnIndex("count"));
          return i;
        }
      }
      finally
      {
        localObject2.close();
      }
      int i = 0;
    }
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
        do
        {
          do
          {
            return paramString2;
            if (!paramString1.equals("METHOD_SET_NOTIFICATION_ORGANIZER_SWITCH")) {
              break;
            }
            i = 0;
            break;
            if (!paramString1.equals("METHOD_GET_NOTIFICATION_ORGANIZER_SWITCH")) {
              break;
            }
            i = 1;
            break;
            if (!paramString1.equals("METHOD_SET_PRIVATE_MESSAGE_SWITCH")) {
              break;
            }
            i = 2;
            break;
            if (!paramString1.equals("METHOD_GET_PRIVATE_MESSAGE_SWITCH")) {
              break;
            }
            i = 3;
            break;
            if (!paramString1.equals("METHOD_UPDATE_CLEAN_TIME")) {
              break;
            }
            i = 4;
            break;
            if (!paramString1.equals("METHOD_GET_CLEAN_TIME")) {
              break;
            }
            i = 5;
            break;
            if (!paramString1.equals("METHOD_ADD_APP_TO_UNBLOCK_LIST")) {
              break;
            }
            i = 6;
            break;
            if (!paramString1.equals("METHOD_REMOVE_APP_FROM_UNBLOCK_LIST")) {
              break;
            }
            i = 7;
            break;
            if (!paramString1.equals("METHOD_FILL_JUNK_NOTIFICATION_APP_LIST")) {
              break;
            }
            i = 8;
            break;
            if (!paramString1.equals("METHOD_GET_UNBLOCKED_APP_LIST")) {
              break;
            }
            i = 9;
            break;
            if (!paramString1.equals("METHOD_GET_LAST_MAKING_TOAST_TIME")) {
              break;
            }
            i = 10;
            break;
            if (!paramString1.equals("METHOD_SET_LAST_MAKING_TOAST_TIME")) {
              break;
            }
            i = 11;
            break;
            if (!paramString1.equals("METHOD_GET_FIRST_GUIDE_FLAG")) {
              break;
            }
            i = 12;
            break;
            if (!paramString1.equals("METHOD_SET_FIRST_GUIDE_FLAG")) {
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
            if (!paramString1.equals("METHOD_GET_BLOCKED_AND_TIME_VALID_NOTIFICATION_COUNT")) {
              break;
            }
            i = 16;
            break;
            if (!paramString1.equals("METHOD_GET_BLOCKED_AND_TIME_VALID_APP_PACKAGE_NAME_LIST")) {
              break;
            }
            i = 17;
            break;
            if (!paramString1.equals("METHOD_IS_NOTIFICATION_ORGANIZER_ENABLE_ONCE")) {
              break;
            }
            i = 18;
            break;
            if (!paramString1.equals("METHOD_SET_NOTIFICATION_ORGANIZER_ENABLE_ONCE")) {
              break;
            }
            i = 19;
            break;
            if (!paramString1.equals("METHOD_IS_PRIVATE_MESSAGE_ENABLE_ONCE")) {
              break;
            }
            i = 20;
            break;
            if (!paramString1.equals("METHOD_SET_PRIVATE_MESSAGE_ENABLE_ONCE")) {
              break;
            }
            i = 21;
            break;
            if (!paramString1.equals("METHOD_GET_KEEP_MESSAGE_PRIVATE_APP_LIST")) {
              break;
            }
            i = 22;
            break;
            if (!paramString1.equals("METHOD_ADD_APP_TO_MESSAGE_PRIVATE_APP_LIST")) {
              break;
            }
            i = 23;
            break;
            if (!paramString1.equals("METHOD_CLEAR_PRIVATE_MESSAGE_APP_LIST")) {
              break;
            }
            i = 24;
            break;
            if (!paramString1.equals("METHOD_UPDATE_PRIVATE_MESSAGE_APP_LIST")) {
              break;
            }
            i = 25;
            break;
            if (!paramString1.equals("METHOD_REMOVE_APP_FROM_MESSAGE_PRIVATE_APP_LIST")) {
              break;
            }
            i = 26;
            break;
            if (!paramString1.equals("METHOD_IS_APP_IN_SECURITY_LIST")) {
              break;
            }
            i = 27;
            break;
            if (!paramString1.equals("METHOD_GET_PRIVATE_MSG_COUNT")) {
              break;
            }
            i = 28;
            break;
            if (!paramString1.equals("METHOD_GET_PRIVATE_MSG_APP_LIST")) {
              break;
            }
            i = 29;
            break;
            if (!paramString1.equals("METHOD_IS_CUSTOM_HEADS_UP")) {
              break;
            }
            i = 30;
            break;
            if (!paramString1.equals("METHOD_SET_CUSTOM_HEADS_UP_FLAG")) {
              break;
            }
            i = 31;
            break;
            i.a(getContext(), "optimizer_notification_organizer").c("PREF_KEY_SWITCHER", paramBundle.getBoolean("EXTRA_SWITCH_STATE"));
            com.ihs.app.framework.a.a().getContentResolver().notifyChange(g(getContext()), null);
            return paramString2;
            paramString2.putBoolean("EXTRA_SWITCH_STATE", i.a(getContext(), "optimizer_notification_organizer").a("PREF_KEY_SWITCHER", false));
            return paramString2;
            i.a(getContext(), "optimizer_message_security").c("PREF_KEY_SWITCHER", paramBundle.getBoolean("EXTRA_SWITCH_STATE"));
            com.ihs.app.framework.a.a().getContentResolver().notifyChange(g(getContext()), null);
            return paramString2;
            paramString2.putBoolean("EXTRA_SWITCH_STATE", i.a(getContext(), "optimizer_message_security").a("PREF_KEY_SWITCHER", false));
            return paramString2;
            i.a(getContext(), "optimizer_notification_organizer").b("PREF_KEY_CLEAN_TIME", System.currentTimeMillis());
            return paramString2;
            paramString2.putLong("EXTRA_CLEAN_TIME", i.a(getContext(), "optimizer_notification_organizer").a("PREF_KEY_CLEAN_TIME", 0L));
            return paramString2;
            paramString1 = K();
          } while (paramString1.contains(paramBundle.getString("EXTRA_APP_PACKAGE_NAME")));
          paramString1.add(paramBundle.getString("EXTRA_APP_PACKAGE_NAME"));
          i.a(getContext(), "optimizer_notification_organizer").c("PREF_KEY_UNBLOCK_APP_LIST", TextUtils.join(";", paramString1));
          com.ihs.app.framework.a.a().getContentResolver().notifyChange(c(getContext()), null);
          com.ihs.app.framework.a.a().getContentResolver().notifyChange(d(getContext()), null);
          return paramString2;
          paramString1 = K();
        } while (!paramString1.remove(paramBundle.getString("EXTRA_APP_PACKAGE_NAME")));
        i.a(getContext(), "optimizer_notification_organizer").c("PREF_KEY_UNBLOCK_APP_LIST", TextUtils.join(";", paramString1));
        com.ihs.app.framework.a.a().getContentResolver().notifyChange(c(getContext()), null);
        com.ihs.app.framework.a.a().getContentResolver().notifyChange(d(getContext()), null);
        return paramString2;
        paramString1 = L();
        paramBundle = new ArrayList();
        Iterator localIterator = com.ihs.app.framework.a.a().getPackageManager().getInstalledPackages(0).iterator();
        while (localIterator.hasNext())
        {
          PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
          if (!paramString1.contains(localPackageInfo.packageName)) {
            paramBundle.add(localPackageInfo.packageName);
          }
        }
        i.a(getContext(), "optimizer_notification_organizer").c("PREF_KEY_UNBLOCK_APP_LIST", TextUtils.join(";", paramBundle));
        return paramString2;
        paramString2.putStringArrayList("EXTRA_APP_PACKAGE_NAME_LIST", K());
        return paramString2;
        paramString2.putLong("EXTRA_KEY_TIME_MILLIS", i.a(getContext(), "optimizer_notification_center").a("PREF_KEY_TIME_MILLIS", 0L));
        return paramString2;
        i.a(getContext(), "optimizer_notification_center").b("PREF_KEY_TIME_MILLIS", paramBundle.getLong("EXTRA_KEY_TIME_MILLIS"));
        return paramString2;
        paramString2.putBoolean("EXTRA_IS_FIRST_GUIDE_FLAG", i.a(getContext(), "optimizer_notification_center").a("PREF_KEY_FIRST_GUIDE_FLAG", true));
        return paramString2;
        i.a(getContext(), "optimizer_notification_center").c("PREF_KEY_FIRST_GUIDE_FLAG", paramBundle.getBoolean("EXTRA_IS_FIRST_GUIDE_FLAG"));
        return paramString2;
        paramString2.putBoolean("EXTRA_IS_USER_CLOSED", i.a(getContext(), "optimizer_notification_organizer").a("PREF_KEY_IS_USER_CLOSED", false));
        return paramString2;
        i.a(getContext(), "optimizer_notification_organizer").c("PREF_KEY_IS_USER_CLOSED", paramBundle.getBoolean("EXTRA_IS_USER_CLOSED"));
        return paramString2;
        paramString2.putInt("EXTRA_BLOCKED_NOTIFICATION_COUNT", i.a(getContext(), "optimizer_notification_organizer").a("PREF_KEY_BLOCKED_AND_TIME_VALID_NOTIFICATION_COUNT", 0));
        return paramString2;
        paramString2.putStringArrayList("EXTRA_APP_PACKAGE_NAME_LIST", new ArrayList(Arrays.asList(i.a(getContext(), "optimizer_notification_organizer").a("PREF_KEY_BLOCKED_AND_TIME_VALID_APP_PACKAGE_NAME_LIST", "").split(";"))));
        return paramString2;
        paramString2.putBoolean("EXTRA_IS_NOTIFICATION_ORGANIZER_ENABLE_ONCE", i.a(getContext(), "optimizer_notification_organizer").a("PREF_KEY_IS_NOTIFICATION_ORGANIZER_ENABLE_ONCE", false));
        return paramString2;
        i.a(getContext(), "optimizer_notification_organizer").c("PREF_KEY_IS_NOTIFICATION_ORGANIZER_ENABLE_ONCE", paramBundle.getBoolean("EXTRA_IS_NOTIFICATION_ORGANIZER_ENABLE_ONCE"));
        return paramString2;
        paramString2.putBoolean("EXTRA_IS_PRIVATE_MESSAGE_ENABLE_ONCE", i.a(getContext(), "optimizer_message_security").a("PREF_KEY_IS_PRIVATE_MESSAGE_ENABLE_ONCE", false));
        return paramString2;
        i.a(getContext(), "optimizer_message_security").c("PREF_KEY_IS_PRIVATE_MESSAGE_ENABLE_ONCE", paramBundle.getBoolean("EXTRA_IS_PRIVATE_MESSAGE_ENABLE_ONCE"));
        return paramString2;
        paramString2.putStringArrayList("EXTRA_APP_PACKAGE_NAME_LIST", L());
        return paramString2;
        paramString1 = L();
      } while (paramString1.contains(paramBundle.getString("EXTRA_APP_PACKAGE_NAME")));
      paramString1.add(paramBundle.getString("EXTRA_APP_PACKAGE_NAME"));
      i.a(getContext(), "optimizer_message_security").c("PREF_KEY_KEEP_MESSAGE_PRIVATE_APP_LIST", TextUtils.join(";", paramString1));
      getContext().getContentResolver().notifyChange(f(getContext()), null);
      return paramString2;
      paramString1 = i.a(getContext(), "optimizer_message_security");
      paramString1.c("PREF_KEY_KEEP_MESSAGE_PRIVATE_APP_LIST", "");
      paramString1.c("PREF_KEY_PRIVATE_APP_LIST_INITIALIZED", true);
      return paramString2;
      paramString1 = paramBundle.getStringArrayList("EXTRA_APP_PACKAGE_NAME_LIST");
      i.a(getContext(), "optimizer_message_security").c("PREF_KEY_KEEP_MESSAGE_PRIVATE_APP_LIST", TextUtils.join(";", paramString1));
      getContext().getContentResolver().notifyChange(f(getContext()), null);
      return paramString2;
      paramString1 = L();
    } while (!paramString1.remove(paramBundle.getString("EXTRA_APP_PACKAGE_NAME")));
    i.a(getContext(), "optimizer_message_security").c("PREF_KEY_KEEP_MESSAGE_PRIVATE_APP_LIST", TextUtils.join(";", paramString1));
    getContext().getContentResolver().notifyChange(f(getContext()), null);
    return paramString2;
    paramString2.putBoolean("EXTRA_IS_APP_IN_SECURITY_LIST", L().contains(paramBundle.getString("EXTRA_APP_PACKAGE_NAME")));
    return paramString2;
    paramString2.putInt("EXTRA_BLOCKED_NOTIFICATION_COUNT", i.a(getContext(), "optimizer_message_security").a("PREF_KEY_PRIVATE_MSG_COUNT", 0));
    return paramString2;
    paramString2.putStringArrayList("EXTRA_APP_PACKAGE_NAME_LIST", new ArrayList(Arrays.asList(i.a(getContext(), "optimizer_message_security").a("PREF_KEY_PRIVATE_MSG_APP_LIST", "").split(";"))));
    return paramString2;
    paramString2.putBoolean("EXTRA_IS_CUSTOM_HEADS_UP", i.a(getContext(), "optimizer_notification_center").a("PREF_KEY_IS_CUSTOM_HEADS_UP", true));
    return paramString2;
    i.a(getContext(), "optimizer_notification_center").c("PREF_KEY_IS_CUSTOM_HEADS_UP", paramBundle.getBoolean("EXTRA_IS_CUSTOM_HEADS_UP"));
    return paramString2;
  }
  
  public int delete(Uri paramUri, String paramString, String[] paramArrayOfString)
  {
    int i;
    if (!TextUtils.isEmpty(paramString))
    {
      paramString = paramString + " AND ";
      switch (this.c.match(paramUri))
      {
      case 2: 
      default: 
        i = -1;
      }
    }
    for (;;)
    {
      return i;
      paramString = " ";
      break;
      paramUri = paramString + "notification_type = 0";
      try
      {
        j = com.optimizer.test.module.notificationorganizer.data.a.a().a(paramUri, paramArrayOfString);
        i = j;
        if (j > 0) {
          try
          {
            a(new NotificationCenterProvider.5(this));
            return j;
          }
          catch (Exception paramUri) {}
        }
      }
      catch (Exception paramUri)
      {
        for (;;)
        {
          int j = 0;
        }
      }
    }
    paramUri.printStackTrace();
    return j;
    paramUri = paramString + "notification_type = 1";
    for (;;)
    {
      try
      {
        j = com.optimizer.test.module.notificationorganizer.data.a.a().a(paramUri, paramArrayOfString);
        i = j;
        if (j <= 0) {
          break;
        }
      }
      catch (Exception paramUri)
      {
        j = 0;
        continue;
      }
      try
      {
        b(new NotificationCenterProvider.6(this));
        return j;
      }
      catch (Exception paramUri)
      {
        paramUri.printStackTrace();
        return j;
      }
    }
  }
  
  public String getType(Uri paramUri)
  {
    return null;
  }
  
  public Uri insert(Uri paramUri, ContentValues paramContentValues)
  {
    switch (this.c.match(paramUri))
    {
    }
    long l;
    for (;;)
    {
      return null;
      try
      {
        paramContentValues.put("notification_type", Integer.valueOf(0));
        l = com.optimizer.test.module.notificationorganizer.data.a.a().a(paramContentValues);
        a(new NotificationCenterProvider.3(this));
        Uri localUri = ContentUris.withAppendedId(paramUri, l);
        return localUri;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
      try
      {
        paramContentValues.put("notification_type", Integer.valueOf(1));
        l = com.optimizer.test.module.notificationorganizer.data.a.a().a(paramContentValues);
        if ((!com.ihs.app.framework.a.b) || (l >= 0L)) {
          break;
        }
        throw new SQLiteException("Unable to insert " + paramContentValues + " for " + paramUri);
      }
      catch (Exception paramUri)
      {
        paramUri.printStackTrace();
      }
    }
    paramUri = ContentUris.withAppendedId(paramUri, l);
    b(new NotificationCenterProvider.4(this));
    return paramUri;
  }
  
  public boolean onCreate()
  {
    this.c = new UriMatcher(-1);
    this.c.addURI(com.ihs.app.framework.a.a().getPackageName() + ".notification_center", "block_notifications", 3);
    this.c.addURI(com.ihs.app.framework.a.a().getPackageName() + ".notification_center", "block_notifications_app", 4);
    this.c.addURI(com.ihs.app.framework.a.a().getPackageName() + ".notification_center", "message_security_notifications", 1);
    return true;
  }
  
  public Cursor query(Uri paramUri, String[] paramArrayOfString1, String paramString1, String[] paramArrayOfString2, String paramString2)
  {
    if (!TextUtils.isEmpty(paramString1)) {}
    for (paramString1 = paramString1 + " AND ";; paramString1 = " ") {
      switch (this.c.match(paramUri))
      {
      case 2: 
      default: 
        return null;
      }
    }
    paramUri = paramString1 + "notification_type = 0";
    return com.optimizer.test.module.notificationorganizer.data.a.a().a(paramArrayOfString1, paramUri, paramArrayOfString2, "", "", paramString2, "");
    paramUri = paramString1 + "notification_type = 0";
    return com.optimizer.test.module.notificationorganizer.data.a.a().a(paramArrayOfString1, paramUri, paramArrayOfString2, "package_name", "", paramString2, "");
    paramUri = paramString1 + "notification_type = 1";
    return com.optimizer.test.module.notificationorganizer.data.a.a().a(paramArrayOfString1, paramUri, paramArrayOfString2, "", "", paramString2, "");
  }
  
  public int update(Uri paramUri, ContentValues paramContentValues, String paramString, String[] paramArrayOfString)
  {
    if (!TextUtils.isEmpty(paramString)) {
      paramString = paramString + " AND ";
    }
    switch (this.c.match(paramUri))
    {
    case 2: 
    default: 
    case 3: 
      for (;;)
      {
        return 0;
        paramString = " ";
        break;
        try
        {
          paramUri = paramString + "notification_type = 0";
          com.optimizer.test.module.notificationorganizer.data.a.a().a(paramContentValues, paramUri, paramArrayOfString);
        }
        catch (Exception paramUri)
        {
          paramUri.printStackTrace();
        }
      }
    }
    try
    {
      paramUri = paramString + "notification_type = 1";
      i = com.optimizer.test.module.notificationorganizer.data.a.a().a(paramContentValues, paramUri, paramArrayOfString);
      try
      {
        getContext().getContentResolver().notifyChange(h(com.ihs.app.framework.a.a()), null);
        return i;
      }
      catch (Exception paramUri) {}
    }
    catch (Exception paramUri)
    {
      for (;;)
      {
        int i = 0;
      }
    }
    paramUri.printStackTrace();
    return i;
  }
}
