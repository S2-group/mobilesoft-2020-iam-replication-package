package com.optimizer.test.module.messagesecurity.data;

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
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.ihs.commons.e.c;
import com.ihs.commons.e.i;
import com.optimizer.test.module.messagesecurity.d;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class MessageSecurityProvider
  extends ContentProvider
{
  private static final ArrayList<String> a = new ArrayList() {};
  private UriMatcher b;
  private a c;
  
  public MessageSecurityProvider() {}
  
  public static Uri a(Context paramContext)
  {
    return Uri.parse("content://" + paramContext.getPackageName() + ".message_security/message_security_notifications");
  }
  
  public static void a(String paramString)
  {
    com.ihs.app.framework.a.a().getContentResolver().delete(a(com.ihs.app.framework.a.a()), "package_name=?", new String[] { paramString });
  }
  
  public static void a(boolean paramBoolean)
  {
    Bundle localBundle = new Bundle();
    localBundle.putBoolean("EXTRA_SWITCH_STATE", paramBoolean);
    c.a(c(com.ihs.app.framework.a.a()), "METHOD_SET_SWITCH", null, localBundle);
    if (!paramBoolean)
    {
      localBundle = new Bundle();
      localBundle.putBoolean("EXTRA_IS_USER_CLOSED", true);
      c.a(d(com.ihs.app.framework.a.a()), "METHOD_SET_USER_CLOSED", null, localBundle);
    }
  }
  
  public static boolean a()
  {
    Bundle localBundle = c.a(d(com.ihs.app.framework.a.a()), "METHOD_GET_FIRST_GUIDE_FLAG", null, null);
    return (localBundle != null) && (localBundle.getBoolean("EXTRA_IS_FIRST_GUIDE_FLAG"));
  }
  
  public static Uri b(Context paramContext)
  {
    return Uri.parse("content://" + paramContext.getPackageName() + ".message_security/keep_apps");
  }
  
  public static void b()
  {
    Bundle localBundle = new Bundle();
    localBundle.putBoolean("EXTRA_IS_FIRST_GUIDE_FLAG", false);
    c.a(d(com.ihs.app.framework.a.a()), "METHOD_SET_FIRST_GUIDE_FLAG", null, localBundle);
  }
  
  public static Uri c(Context paramContext)
  {
    return Uri.parse("content://" + paramContext.getPackageName() + ".message_security/switcher");
  }
  
  public static boolean c()
  {
    Bundle localBundle = c.a(d(com.ihs.app.framework.a.a()), "METHOD_GET_APP_PROTECT_BY_APPLOCK_SWITCH", null, null);
    return (localBundle != null) && (localBundle.getBoolean("EXTRA_SWITCH_STATE"));
  }
  
  private static Uri d(Context paramContext)
  {
    return Uri.parse("content://" + paramContext.getPackageName() + ".message_security/");
  }
  
  public static void d()
  {
    Bundle localBundle = new Bundle();
    localBundle.putBoolean("EXTRA_SWITCH_STATE", true);
    c.a(d(com.ihs.app.framework.a.a()), "METHOD_SET_APP_PROTECT_BY_APPLOCK_SWITCH", null, localBundle);
  }
  
  public static boolean e()
  {
    Bundle localBundle = c.a(c(com.ihs.app.framework.a.a()), "METHOD_GET_SWITCH", null, null);
    return (localBundle != null) && (localBundle.getBoolean("EXTRA_SWITCH_STATE"));
  }
  
  /* Error */
  public static List<com.optimizer.test.module.notificationorganizer.data.b> f()
  {
    // Byte code:
    //   0: invokestatic 56	com/ihs/app/framework/a:a	()Landroid/content/Context;
    //   3: invokevirtual 60	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   6: invokestatic 56	com/ihs/app/framework/a:a	()Landroid/content/Context;
    //   9: invokestatic 62	com/optimizer/test/module/messagesecurity/data/MessageSecurityProvider:a	(Landroid/content/Context;)Landroid/net/Uri;
    //   12: aconst_null
    //   13: ldc -128
    //   15: iconst_1
    //   16: anewarray 66	java/lang/String
    //   19: dup
    //   20: iconst_0
    //   21: invokestatic 133	com/optimizer/test/module/notificationorganizer/a:a	()J
    //   24: invokestatic 137	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   27: aastore
    //   28: ldc -117
    //   30: invokevirtual 143	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   33: astore_0
    //   34: aload_0
    //   35: ifnonnull +11 -> 46
    //   38: new 145	java/util/ArrayList
    //   41: dup
    //   42: invokespecial 146	java/util/ArrayList:<init>	()V
    //   45: areturn
    //   46: new 145	java/util/ArrayList
    //   49: dup
    //   50: invokespecial 146	java/util/ArrayList:<init>	()V
    //   53: astore_1
    //   54: aload_0
    //   55: invokeinterface 151 1 0
    //   60: ifeq +126 -> 186
    //   63: new 153	com/optimizer/test/module/notificationorganizer/data/b
    //   66: dup
    //   67: invokespecial 154	com/optimizer/test/module/notificationorganizer/data/b:<init>	()V
    //   70: astore_2
    //   71: aload_2
    //   72: aload_0
    //   73: aload_0
    //   74: ldc -100
    //   76: invokeinterface 160 2 0
    //   81: invokeinterface 164 2 0
    //   86: putfield 167	com/optimizer/test/module/notificationorganizer/data/b:d	Ljava/lang/String;
    //   89: aload_2
    //   90: aload_0
    //   91: aload_0
    //   92: ldc -87
    //   94: invokeinterface 160 2 0
    //   99: invokeinterface 173 2 0
    //   104: putfield 177	com/optimizer/test/module/notificationorganizer/data/b:j	J
    //   107: aload_2
    //   108: aload_0
    //   109: aload_0
    //   110: ldc -77
    //   112: invokeinterface 160 2 0
    //   117: invokeinterface 164 2 0
    //   122: putfield 182	com/optimizer/test/module/notificationorganizer/data/b:h	Ljava/lang/String;
    //   125: aload_2
    //   126: aload_0
    //   127: aload_0
    //   128: ldc -72
    //   130: invokeinterface 160 2 0
    //   135: invokeinterface 164 2 0
    //   140: putfield 187	com/optimizer/test/module/notificationorganizer/data/b:g	Ljava/lang/String;
    //   143: aload_2
    //   144: aload_0
    //   145: aload_0
    //   146: ldc -67
    //   148: invokeinterface 160 2 0
    //   153: invokeinterface 193 2 0
    //   158: i2l
    //   159: putfield 195	com/optimizer/test/module/notificationorganizer/data/b:a	J
    //   162: aload_1
    //   163: aload_2
    //   164: invokeinterface 201 2 0
    //   169: pop
    //   170: goto -116 -> 54
    //   173: astore_2
    //   174: aload_2
    //   175: invokevirtual 204	java/lang/Exception:printStackTrace	()V
    //   178: aload_0
    //   179: invokeinterface 207 1 0
    //   184: aload_1
    //   185: areturn
    //   186: aload_0
    //   187: invokeinterface 207 1 0
    //   192: aload_1
    //   193: areturn
    //   194: astore_1
    //   195: aload_0
    //   196: invokeinterface 207 1 0
    //   201: aload_1
    //   202: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   33	163	0	localCursor	Cursor
    //   53	140	1	localArrayList	ArrayList
    //   194	8	1	localObject	Object
    //   70	94	2	localB	com.optimizer.test.module.notificationorganizer.data.b
    //   173	2	2	localException	Exception
    // Exception table:
    //   from	to	target	type
    //   54	170	173	java/lang/Exception
    //   54	170	194	finally
    //   174	178	194	finally
  }
  
  public static void g()
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("notification_remind_status", Integer.valueOf(1));
    com.ihs.app.framework.a.a().getContentResolver().update(a(com.ihs.app.framework.a.a()), localContentValues, "notification_remind_status=?", new String[] { "0" });
  }
  
  public static void h()
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("notification_read_status", Integer.valueOf(1));
    com.ihs.app.framework.a.a().getContentResolver().update(a(com.ihs.app.framework.a.a()), localContentValues, "notification_read_status=?", new String[] { "0" });
  }
  
  /* Error */
  public static List<com.optimizer.test.module.notificationorganizer.data.b> i()
  {
    // Byte code:
    //   0: new 145	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 146	java/util/ArrayList:<init>	()V
    //   7: astore_1
    //   8: invokestatic 56	com/ihs/app/framework/a:a	()Landroid/content/Context;
    //   11: invokevirtual 60	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   14: invokestatic 56	com/ihs/app/framework/a:a	()Landroid/content/Context;
    //   17: invokestatic 62	com/optimizer/test/module/messagesecurity/data/MessageSecurityProvider:a	(Landroid/content/Context;)Landroid/net/Uri;
    //   20: aconst_null
    //   21: ldc -21
    //   23: iconst_1
    //   24: anewarray 66	java/lang/String
    //   27: dup
    //   28: iconst_0
    //   29: ldc -29
    //   31: aastore
    //   32: aconst_null
    //   33: invokevirtual 143	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   36: astore_0
    //   37: aload_0
    //   38: ifnonnull +5 -> 43
    //   41: aload_1
    //   42: areturn
    //   43: aload_0
    //   44: invokeinterface 151 1 0
    //   49: ifeq +126 -> 175
    //   52: new 153	com/optimizer/test/module/notificationorganizer/data/b
    //   55: dup
    //   56: invokespecial 154	com/optimizer/test/module/notificationorganizer/data/b:<init>	()V
    //   59: astore_2
    //   60: aload_2
    //   61: aload_0
    //   62: aload_0
    //   63: ldc -100
    //   65: invokeinterface 160 2 0
    //   70: invokeinterface 164 2 0
    //   75: putfield 167	com/optimizer/test/module/notificationorganizer/data/b:d	Ljava/lang/String;
    //   78: aload_2
    //   79: aload_0
    //   80: aload_0
    //   81: ldc -87
    //   83: invokeinterface 160 2 0
    //   88: invokeinterface 173 2 0
    //   93: putfield 177	com/optimizer/test/module/notificationorganizer/data/b:j	J
    //   96: aload_2
    //   97: aload_0
    //   98: aload_0
    //   99: ldc -77
    //   101: invokeinterface 160 2 0
    //   106: invokeinterface 164 2 0
    //   111: putfield 182	com/optimizer/test/module/notificationorganizer/data/b:h	Ljava/lang/String;
    //   114: aload_2
    //   115: aload_0
    //   116: aload_0
    //   117: ldc -72
    //   119: invokeinterface 160 2 0
    //   124: invokeinterface 164 2 0
    //   129: putfield 187	com/optimizer/test/module/notificationorganizer/data/b:g	Ljava/lang/String;
    //   132: aload_2
    //   133: aload_0
    //   134: aload_0
    //   135: ldc -67
    //   137: invokeinterface 160 2 0
    //   142: invokeinterface 193 2 0
    //   147: i2l
    //   148: putfield 195	com/optimizer/test/module/notificationorganizer/data/b:a	J
    //   151: aload_1
    //   152: aload_2
    //   153: invokeinterface 201 2 0
    //   158: pop
    //   159: goto -116 -> 43
    //   162: astore_2
    //   163: aload_2
    //   164: invokevirtual 204	java/lang/Exception:printStackTrace	()V
    //   167: aload_0
    //   168: invokeinterface 207 1 0
    //   173: aload_1
    //   174: areturn
    //   175: aload_0
    //   176: invokeinterface 207 1 0
    //   181: goto -8 -> 173
    //   184: astore_1
    //   185: aload_0
    //   186: invokeinterface 207 1 0
    //   191: aload_1
    //   192: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   36	150	0	localCursor	Cursor
    //   7	167	1	localArrayList	ArrayList
    //   184	8	1	localObject	Object
    //   59	94	2	localB	com.optimizer.test.module.notificationorganizer.data.b
    //   162	2	2	localException	Exception
    // Exception table:
    //   from	to	target	type
    //   43	159	162	java/lang/Exception
    //   43	159	184	finally
    //   163	167	184	finally
  }
  
  /* Error */
  public static List<com.optimizer.test.module.notificationorganizer.data.b> j()
  {
    // Byte code:
    //   0: new 145	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 146	java/util/ArrayList:<init>	()V
    //   7: astore_1
    //   8: invokestatic 56	com/ihs/app/framework/a:a	()Landroid/content/Context;
    //   11: invokevirtual 60	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   14: invokestatic 56	com/ihs/app/framework/a:a	()Landroid/content/Context;
    //   17: invokestatic 62	com/optimizer/test/module/messagesecurity/data/MessageSecurityProvider:a	(Landroid/content/Context;)Landroid/net/Uri;
    //   20: aconst_null
    //   21: ldc -31
    //   23: iconst_1
    //   24: anewarray 66	java/lang/String
    //   27: dup
    //   28: iconst_0
    //   29: ldc -29
    //   31: aastore
    //   32: aconst_null
    //   33: invokevirtual 143	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   36: astore_0
    //   37: aload_0
    //   38: ifnonnull +5 -> 43
    //   41: aload_1
    //   42: areturn
    //   43: aload_0
    //   44: invokeinterface 151 1 0
    //   49: ifeq +126 -> 175
    //   52: new 153	com/optimizer/test/module/notificationorganizer/data/b
    //   55: dup
    //   56: invokespecial 154	com/optimizer/test/module/notificationorganizer/data/b:<init>	()V
    //   59: astore_2
    //   60: aload_2
    //   61: aload_0
    //   62: aload_0
    //   63: ldc -100
    //   65: invokeinterface 160 2 0
    //   70: invokeinterface 164 2 0
    //   75: putfield 167	com/optimizer/test/module/notificationorganizer/data/b:d	Ljava/lang/String;
    //   78: aload_2
    //   79: aload_0
    //   80: aload_0
    //   81: ldc -87
    //   83: invokeinterface 160 2 0
    //   88: invokeinterface 173 2 0
    //   93: putfield 177	com/optimizer/test/module/notificationorganizer/data/b:j	J
    //   96: aload_2
    //   97: aload_0
    //   98: aload_0
    //   99: ldc -77
    //   101: invokeinterface 160 2 0
    //   106: invokeinterface 164 2 0
    //   111: putfield 182	com/optimizer/test/module/notificationorganizer/data/b:h	Ljava/lang/String;
    //   114: aload_2
    //   115: aload_0
    //   116: aload_0
    //   117: ldc -72
    //   119: invokeinterface 160 2 0
    //   124: invokeinterface 164 2 0
    //   129: putfield 187	com/optimizer/test/module/notificationorganizer/data/b:g	Ljava/lang/String;
    //   132: aload_2
    //   133: aload_0
    //   134: aload_0
    //   135: ldc -67
    //   137: invokeinterface 160 2 0
    //   142: invokeinterface 193 2 0
    //   147: i2l
    //   148: putfield 195	com/optimizer/test/module/notificationorganizer/data/b:a	J
    //   151: aload_1
    //   152: aload_2
    //   153: invokeinterface 201 2 0
    //   158: pop
    //   159: goto -116 -> 43
    //   162: astore_2
    //   163: aload_2
    //   164: invokevirtual 204	java/lang/Exception:printStackTrace	()V
    //   167: aload_0
    //   168: invokeinterface 207 1 0
    //   173: aload_1
    //   174: areturn
    //   175: aload_0
    //   176: invokeinterface 207 1 0
    //   181: goto -8 -> 173
    //   184: astore_1
    //   185: aload_0
    //   186: invokeinterface 207 1 0
    //   191: aload_1
    //   192: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   36	150	0	localCursor	Cursor
    //   7	167	1	localArrayList	ArrayList
    //   184	8	1	localObject	Object
    //   59	94	2	localB	com.optimizer.test.module.notificationorganizer.data.b
    //   162	2	2	localException	Exception
    // Exception table:
    //   from	to	target	type
    //   43	159	162	java/lang/Exception
    //   43	159	184	finally
    //   163	167	184	finally
  }
  
  private ArrayList<String> k()
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
    localObject1 = d.b();
    ((List)localObject1).addAll(a);
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
        return paramString2;
        if (!paramString1.equals("METHOD_GET_KEEP_MESSAGE_PRIVATE_APP_LIST")) {
          break;
        }
        i = 0;
        break;
        if (!paramString1.equals("METHOD_ADD_APP_TO_MESSAGE_PRIVATE_APP_LIST")) {
          break;
        }
        i = 1;
        break;
        if (!paramString1.equals("METHOD_REMOVE_APP_FROM_MESSAGE_PRIVATE_APP_LIST")) {
          break;
        }
        i = 2;
        break;
        if (!paramString1.equals("METHOD_IS_APP_IN_SECURITY_LIST")) {
          break;
        }
        i = 3;
        break;
        if (!paramString1.equals("METHOD_GET_FIRST_GUIDE_FLAG")) {
          break;
        }
        i = 4;
        break;
        if (!paramString1.equals("METHOD_SET_FIRST_GUIDE_FLAG")) {
          break;
        }
        i = 5;
        break;
        if (!paramString1.equals("METHOD_SET_SWITCH")) {
          break;
        }
        i = 6;
        break;
        if (!paramString1.equals("METHOD_GET_SWITCH")) {
          break;
        }
        i = 7;
        break;
        if (!paramString1.equals("METHOD_SET_APP_PROTECT_BY_APPLOCK_SWITCH")) {
          break;
        }
        i = 8;
        break;
        if (!paramString1.equals("METHOD_GET_APP_PROTECT_BY_APPLOCK_SWITCH")) {
          break;
        }
        i = 9;
        break;
        paramString2.putStringArrayList("EXTRA_APP_PACKAGE_NAME_LIST", k());
        return paramString2;
        paramString1 = k();
      } while (paramString1.contains(paramBundle.getString("EXTRA_APP_PACKAGE_NAME")));
      paramString1.add(paramBundle.getString("EXTRA_APP_PACKAGE_NAME"));
      i.a(getContext(), "optimizer_message_security").c("PREF_KEY_KEEP_MESSAGE_PRIVATE_APP_LIST", TextUtils.join(";", paramString1));
      getContext().getContentResolver().notifyChange(b(getContext()), null);
      return paramString2;
      paramString1 = k();
    } while (!paramString1.remove(paramBundle.getString("EXTRA_APP_PACKAGE_NAME")));
    i.a(getContext(), "optimizer_message_security").c("PREF_KEY_KEEP_MESSAGE_PRIVATE_APP_LIST", TextUtils.join(";", paramString1));
    getContext().getContentResolver().notifyChange(b(getContext()), null);
    return paramString2;
    paramString2.putBoolean("EXTRA_IS_APP_IN_SECURITY_LIST", k().contains(paramBundle.getString("EXTRA_APP_PACKAGE_NAME")));
    return paramString2;
    paramString2.putBoolean("EXTRA_IS_FIRST_GUIDE_FLAG", i.a(getContext(), "optimizer_message_security").a("PREF_KEY_FIRST_GUIDE_FLAG", true));
    return paramString2;
    i.a(getContext(), "optimizer_message_security").c("PREF_KEY_FIRST_GUIDE_FLAG", paramBundle.getBoolean("EXTRA_IS_FIRST_GUIDE_FLAG"));
    return paramString2;
    i.a(getContext(), "optimizer_message_security").c("PREF_KEY_SWITCHER", paramBundle.getBoolean("EXTRA_SWITCH_STATE"));
    return paramString2;
    paramString2.putBoolean("EXTRA_SWITCH_STATE", i.a(getContext(), "optimizer_message_security").a("PREF_KEY_SWITCHER", false));
    return paramString2;
    i.a(getContext(), "optimizer_message_security").c("PREF_KEY_APP_PROTECT_BY_APPLOCK_SWITCH", paramBundle.getBoolean("EXTRA_SWITCH_STATE"));
    return paramString2;
    paramString2.putBoolean("EXTRA_SWITCH_STATE", i.a(getContext(), "optimizer_message_security").a("PREF_KEY_APP_PROTECT_BY_APPLOCK_SWITCH", false));
    return paramString2;
  }
  
  public int delete(Uri paramUri, String paramString, String[] paramArrayOfString)
  {
    switch (this.b.match(paramUri))
    {
    default: 
      return -1;
    }
    try
    {
      i = this.c.getWritableDatabase().delete("MessageSecurityBlockNotifications", paramString, paramArrayOfString);
      try
      {
        getContext().getContentResolver().notifyChange(paramUri, null);
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
  
  public String getType(Uri paramUri)
  {
    return null;
  }
  
  public Uri insert(Uri paramUri, ContentValues paramContentValues)
  {
    switch (this.b.match(paramUri))
    {
    }
    long l;
    for (;;)
    {
      return null;
      try
      {
        l = this.c.getWritableDatabase().insert("MessageSecurityBlockNotifications", null, paramContentValues);
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
    getContext().getContentResolver().notifyChange(paramUri, null);
    getContext().getContentResolver().notifyChange(a(getContext()), null);
    return paramUri;
  }
  
  public boolean onCreate()
  {
    this.b = new UriMatcher(-1);
    this.b.addURI(com.ihs.app.framework.a.a().getPackageName() + ".message_security", "message_security_notifications", 1);
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
        paramUri = this.c.getReadableDatabase().query("MessageSecurityBlockNotifications", paramArrayOfString1, paramString1, paramArrayOfString2, null, null, paramString2, "500");
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
    int i = 0;
    switch (this.b.match(paramUri))
    {
    default: 
      return 0;
    }
    try
    {
      int j = this.c.getWritableDatabase().update("MessageSecurityBlockNotifications", paramContentValues, paramString, paramArrayOfString);
      i = j;
      getContext().getContentResolver().notifyChange(paramUri, null);
      return j;
    }
    catch (Exception paramUri)
    {
      paramUri.printStackTrace();
    }
    return i;
  }
}
