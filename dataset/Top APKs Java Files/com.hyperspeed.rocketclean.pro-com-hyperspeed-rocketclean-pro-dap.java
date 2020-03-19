package com.hyperspeed.rocketclean.pro;

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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

public class dap
  extends ContentProvider
{
  public static final List<String> m = new ArrayList() {};
  public static final ArrayList<String> n = new ArrayList() {};
  private UriMatcher mn;
  
  public dap() {}
  
  public static int a()
  {
    Bundle localBundle = cgb.m(m(cep.m()), "METHOD_GET_BLOCKED_AND_TIME_VALID_NOTIFICATION_COUNT", null, null);
    if (localBundle == null) {
      return 0;
    }
    return localBundle.getInt("EXTRA_BLOCKED_NOTIFICATION_COUNT", 0);
  }
  
  public static Uri b(Context paramContext)
  {
    return Uri.parse("content://" + paramContext.getPackageName() + ".blocked_notification/unblock_list_changed");
  }
  
  private static String b(boolean paramBoolean)
  {
    if (paramBoolean) {
      return "post_time>? AND " + "notification_type = 0";
    }
    Object localObject = cgb.m(mn(cep.m()), "METHOD_GET_UNBLOCKED_APP_LIST", null, null);
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
  
  public static boolean b()
  {
    Bundle localBundle = cgb.m(m(cep.m()), "METHOD_IS_CUSTOM_HEADS_UP", null, null);
    return (localBundle != null) && (localBundle.getBoolean("EXTRA_IS_CUSTOM_HEADS_UP"));
  }
  
  public static Uri bv(Context paramContext)
  {
    return Uri.parse("content://" + paramContext.getPackageName() + ".blocked_notification/switcher");
  }
  
  public static List<String> bv()
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject = cgb.m(mn(cep.m()), "METHOD_GET_UNBLOCKED_APP_LIST", null, null);
    if (localObject != null)
    {
      localObject = ((Bundle)localObject).getStringArrayList("EXTRA_APP_PACKAGE_NAME_LIST");
      if (localObject != null) {
        localArrayList.addAll((Collection)localObject);
      }
    }
    return localArrayList;
  }
  
  public static long c()
  {
    Bundle localBundle = cgb.m(m(cep.m()), "METHOD_GET_CLEAN_TIME", null, null);
    if (localBundle == null) {
      return 0L;
    }
    return localBundle.getLong("EXTRA_CLEAN_TIME");
  }
  
  private static Uri c(Context paramContext)
  {
    return Uri.parse("content://" + paramContext.getPackageName() + ".blocked_notification/keep_apps");
  }
  
  public static void cx()
  {
    Bundle localBundle = new Bundle();
    localBundle.putBoolean("EXTRA_IS_JUNK_NOTIFICATION_ENABLE_ONCE", true);
    cgb.m(m(cep.m()), "METHOD_SET_JUNK_NOTIFICATION_ENABLE_ONCE", null, localBundle);
  }
  
  private static List<String> df()
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject = g();
    if (localObject == null) {
      return localArrayList;
    }
    localObject = epn.c((Map)localObject, new String[] { "Default", "PrivateMessageCheckList" });
    if (localObject == null) {
      return localArrayList;
    }
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext()) {
      localArrayList.add(((String)((Iterator)localObject).next()).trim());
    }
    return localArrayList;
  }
  
  private ArrayList<String> f()
  {
    cgh localCgh = cgh.m(getContext(), "optimizer_message_security");
    Object localObject1 = localCgh.m("PREF_KEY_KEEP_MESSAGE_PRIVATE_APP_LIST", null);
    ArrayList localArrayList = new ArrayList();
    if (!TextUtils.isEmpty((CharSequence)localObject1)) {
      localArrayList.addAll(Arrays.asList(((String)localObject1).split(";")));
    }
    while (localCgh.m("PREF_KEY_PRIVATE_APP_LIST_INITIALIZED", false)) {
      return localArrayList;
    }
    Object localObject2 = cep.m().getPackageManager().getInstalledPackages(0);
    localObject1 = df();
    ((List)localObject1).addAll(n);
    localObject2 = ((List)localObject2).iterator();
    while (((Iterator)localObject2).hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject2).next();
      if (((List)localObject1).contains(localPackageInfo.packageName)) {
        localArrayList.add(localPackageInfo.packageName);
      }
    }
    localCgh.mn("PREF_KEY_KEEP_MESSAGE_PRIVATE_APP_LIST", TextUtils.join(";", localArrayList));
    localCgh.mn("PREF_KEY_PRIVATE_APP_LIST_INITIALIZED", true);
    return localArrayList;
  }
  
  /* Error */
  private static Map<String, Object> g()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aconst_null
    //   4: astore_3
    //   5: ldc_w 307
    //   8: invokestatic 311	com/hyperspeed/rocketclean/pro/epq:m	(Ljava/lang/String;)Z
    //   11: pop
    //   12: invokestatic 51	com/hyperspeed/rocketclean/pro/cep:m	()Landroid/content/Context;
    //   15: invokevirtual 315	android/content/Context:getAssets	()Landroid/content/res/AssetManager;
    //   18: astore_1
    //   19: aload_1
    //   20: ldc_w 307
    //   23: invokevirtual 321	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   26: astore_1
    //   27: aload_1
    //   28: astore_2
    //   29: aload_1
    //   30: invokestatic 326	com/hyperspeed/rocketclean/pro/epl:m	(Ljava/io/InputStream;)Ljava/util/Map;
    //   33: astore 5
    //   35: aload_3
    //   36: astore_2
    //   37: aload 5
    //   39: ifnull +22 -> 61
    //   42: aload_1
    //   43: astore_2
    //   44: aload 5
    //   46: invokeinterface 329 1 0
    //   51: istore_0
    //   52: aload_3
    //   53: astore_2
    //   54: iload_0
    //   55: ifne +6 -> 61
    //   58: aload 5
    //   60: astore_2
    //   61: aload_2
    //   62: astore_3
    //   63: aload_1
    //   64: ifnull +9 -> 73
    //   67: aload_1
    //   68: invokevirtual 334	java/io/InputStream:close	()V
    //   71: aload_2
    //   72: astore_3
    //   73: aload_3
    //   74: areturn
    //   75: astore_1
    //   76: aload_1
    //   77: invokevirtual 337	java/io/IOException:printStackTrace	()V
    //   80: aload_2
    //   81: areturn
    //   82: astore_3
    //   83: aconst_null
    //   84: astore_1
    //   85: aload_1
    //   86: astore_2
    //   87: aload_3
    //   88: invokevirtual 338	java/lang/Exception:printStackTrace	()V
    //   91: aload 4
    //   93: astore_3
    //   94: aload_1
    //   95: ifnull -22 -> 73
    //   98: aload_1
    //   99: invokevirtual 334	java/io/InputStream:close	()V
    //   102: aconst_null
    //   103: areturn
    //   104: astore_1
    //   105: aload_1
    //   106: invokevirtual 337	java/io/IOException:printStackTrace	()V
    //   109: aconst_null
    //   110: areturn
    //   111: astore_1
    //   112: aconst_null
    //   113: astore_2
    //   114: aload_2
    //   115: ifnull +7 -> 122
    //   118: aload_2
    //   119: invokevirtual 334	java/io/InputStream:close	()V
    //   122: aload_1
    //   123: athrow
    //   124: astore_2
    //   125: aload_2
    //   126: invokevirtual 337	java/io/IOException:printStackTrace	()V
    //   129: goto -7 -> 122
    //   132: astore_1
    //   133: goto -19 -> 114
    //   136: astore_3
    //   137: goto -52 -> 85
    // Local variable table:
    //   start	length	slot	name	signature
    //   51	4	0	bool	boolean
    //   18	50	1	localObject1	Object
    //   75	2	1	localIOException1	java.io.IOException
    //   84	15	1	localObject2	Object
    //   104	2	1	localIOException2	java.io.IOException
    //   111	12	1	localObject3	Object
    //   132	1	1	localObject4	Object
    //   28	91	2	localObject5	Object
    //   124	2	2	localIOException3	java.io.IOException
    //   4	70	3	localObject6	Object
    //   82	6	3	localException1	Exception
    //   93	1	3	localObject7	Object
    //   136	1	3	localException2	Exception
    //   1	91	4	localObject8	Object
    //   33	26	5	localMap	Map
    // Exception table:
    //   from	to	target	type
    //   67	71	75	java/io/IOException
    //   19	27	82	java/lang/Exception
    //   98	102	104	java/io/IOException
    //   19	27	111	finally
    //   118	122	124	java/io/IOException
    //   29	35	132	finally
    //   44	52	132	finally
    //   87	91	132	finally
    //   29	35	136	java/lang/Exception
    //   44	52	136	java/lang/Exception
  }
  
  public static Uri m(Context paramContext)
  {
    return Uri.parse("content://" + paramContext.getPackageName() + ".blocked_notification/");
  }
  
  private void m(final Runnable paramRunnable)
  {
    crj.m().m.execute(new Runnable()
    {
      /* Error */
      public final void run()
      {
        // Byte code:
        //   0: iconst_0
        //   1: istore_2
        //   2: invokestatic 33	com/hyperspeed/rocketclean/pro/dap:s	()Ljava/lang/String;
        //   5: astore 6
        //   7: invokestatic 38	com/hyperspeed/rocketclean/pro/dan:m	()Lcom/hyperspeed/rocketclean/pro/dan;
        //   10: astore 5
        //   12: invokestatic 43	com/hyperspeed/rocketclean/pro/daq:m	()J
        //   15: lstore_3
        //   16: aload 5
        //   18: iconst_1
        //   19: anewarray 45	java/lang/String
        //   22: dup
        //   23: iconst_0
        //   24: ldc 47
        //   26: aastore
        //   27: aload 6
        //   29: iconst_1
        //   30: anewarray 45	java/lang/String
        //   33: dup
        //   34: iconst_0
        //   35: lload_3
        //   36: invokestatic 51	java/lang/String:valueOf	(J)Ljava/lang/String;
        //   39: aastore
        //   40: ldc 53
        //   42: ldc 53
        //   44: ldc 53
        //   46: ldc 55
        //   48: invokevirtual 58	com/hyperspeed/rocketclean/pro/dan:m	([Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
        //   51: astore 5
        //   53: iload_2
        //   54: istore_1
        //   55: aload 5
        //   57: ifnull +37 -> 94
        //   60: aload 5
        //   62: invokeinterface 64 1 0
        //   67: ifeq +242 -> 309
        //   70: aload 5
        //   72: aload 5
        //   74: ldc 66
        //   76: invokeinterface 70 2 0
        //   81: invokeinterface 74 2 0
        //   86: istore_1
        //   87: aload 5
        //   89: invokeinterface 77 1 0
        //   94: new 79	java/util/ArrayList
        //   97: dup
        //   98: invokespecial 80	java/util/ArrayList:<init>	()V
        //   101: astore 7
        //   103: invokestatic 38	com/hyperspeed/rocketclean/pro/dan:m	()Lcom/hyperspeed/rocketclean/pro/dan;
        //   106: astore 5
        //   108: invokestatic 43	com/hyperspeed/rocketclean/pro/daq:m	()J
        //   111: lstore_3
        //   112: aload 5
        //   114: iconst_1
        //   115: anewarray 45	java/lang/String
        //   118: dup
        //   119: iconst_0
        //   120: ldc 82
        //   122: aastore
        //   123: aload 6
        //   125: iconst_1
        //   126: anewarray 45	java/lang/String
        //   129: dup
        //   130: iconst_0
        //   131: lload_3
        //   132: invokestatic 51	java/lang/String:valueOf	(J)Ljava/lang/String;
        //   135: aastore
        //   136: ldc 82
        //   138: ldc 53
        //   140: ldc 84
        //   142: ldc 86
        //   144: invokevirtual 58	com/hyperspeed/rocketclean/pro/dan:m	([Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
        //   147: astore 5
        //   149: aload 5
        //   151: ifnull +49 -> 200
        //   154: aload 5
        //   156: invokeinterface 89 1 0
        //   161: ifeq +126 -> 287
        //   164: aload 7
        //   166: aload 5
        //   168: aload 5
        //   170: ldc 82
        //   172: invokeinterface 70 2 0
        //   177: invokeinterface 93 2 0
        //   182: invokeinterface 99 2 0
        //   187: pop
        //   188: goto -34 -> 154
        //   191: astore 6
        //   193: aload 5
        //   195: invokeinterface 77 1 0
        //   200: ldc 101
        //   202: aload 7
        //   204: invokestatic 107	android/text/TextUtils:join	(Ljava/lang/CharSequence;Ljava/lang/Iterable;)Ljava/lang/String;
        //   207: astore 5
        //   209: new 109	android/os/Handler
        //   212: dup
        //   213: invokestatic 115	android/os/Looper:getMainLooper	()Landroid/os/Looper;
        //   216: invokespecial 118	android/os/Handler:<init>	(Landroid/os/Looper;)V
        //   219: new 13	com/hyperspeed/rocketclean/pro/dap$7$1
        //   222: dup
        //   223: aload_0
        //   224: iload_1
        //   225: aload 5
        //   227: invokespecial 121	com/hyperspeed/rocketclean/pro/dap$7$1:<init>	(Lcom/hyperspeed/rocketclean/pro/dap$7;ILjava/lang/String;)V
        //   230: invokevirtual 125	android/os/Handler:post	(Ljava/lang/Runnable;)Z
        //   233: pop
        //   234: return
        //   235: astore 5
        //   237: aload 5
        //   239: invokevirtual 128	java/lang/Exception:printStackTrace	()V
        //   242: aconst_null
        //   243: astore 5
        //   245: goto -192 -> 53
        //   248: astore 7
        //   250: aload 5
        //   252: invokeinterface 77 1 0
        //   257: iload_2
        //   258: istore_1
        //   259: goto -165 -> 94
        //   262: astore 6
        //   264: aload 5
        //   266: invokeinterface 77 1 0
        //   271: aload 6
        //   273: athrow
        //   274: astore 5
        //   276: aload 5
        //   278: invokevirtual 128	java/lang/Exception:printStackTrace	()V
        //   281: aconst_null
        //   282: astore 5
        //   284: goto -135 -> 149
        //   287: aload 5
        //   289: invokeinterface 77 1 0
        //   294: goto -94 -> 200
        //   297: astore 6
        //   299: aload 5
        //   301: invokeinterface 77 1 0
        //   306: aload 6
        //   308: athrow
        //   309: iconst_0
        //   310: istore_1
        //   311: goto -224 -> 87
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	314	0	this	7
        //   54	257	1	i	int
        //   1	257	2	j	int
        //   15	117	3	l	long
        //   10	216	5	localObject1	Object
        //   235	3	5	localException1	Exception
        //   243	22	5	localObject2	Object
        //   274	3	5	localException2	Exception
        //   282	18	5	localObject3	Object
        //   5	119	6	str	String
        //   191	1	6	localException3	Exception
        //   262	10	6	localObject4	Object
        //   297	10	6	localObject5	Object
        //   101	102	7	localArrayList	ArrayList
        //   248	1	7	localException4	Exception
        // Exception table:
        //   from	to	target	type
        //   154	188	191	java/lang/Exception
        //   7	53	235	java/lang/Exception
        //   60	87	248	java/lang/Exception
        //   60	87	262	finally
        //   103	149	274	java/lang/Exception
        //   154	188	297	finally
      }
    });
  }
  
  public static void m(String paramString)
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("EXTRA_APP_PACKAGE_NAME", paramString);
    cgb.m(mn(cep.m()), "METHOD_ADD_APP_TO_UNBLOCK_LIST", null, localBundle);
  }
  
  public static void m(boolean paramBoolean)
  {
    Bundle localBundle = new Bundle();
    localBundle.putBoolean("EXTRA_SWITCH_STATE", paramBoolean);
    cgb.m(bv(cep.m()), "METHOD_SET_JUNK_NOTIFICATION_SWITCH", null, localBundle);
    if ((paramBoolean) && (dyb.m(cep.m()))) {
      cx();
    }
  }
  
  public static boolean m()
  {
    Bundle localBundle = cgb.m(bv(cep.m()), "METHOD_GET_JUNK_NOTIFICATION_SWITCH", null, null);
    return (localBundle != null) && (localBundle.getBoolean("EXTRA_SWITCH_STATE"));
  }
  
  public static Uri mn(Context paramContext)
  {
    return Uri.parse("content://" + paramContext.getPackageName() + ".blocked_notification/block_apps");
  }
  
  /* Error */
  public static List<dao> mn(boolean paramBoolean)
  {
    // Byte code:
    //   0: invokestatic 51	com/hyperspeed/rocketclean/pro/cep:m	()Landroid/content/Context;
    //   3: invokevirtual 390	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   6: invokestatic 51	com/hyperspeed/rocketclean/pro/cep:m	()Landroid/content/Context;
    //   9: invokestatic 392	com/hyperspeed/rocketclean/pro/dap:n	(Landroid/content/Context;)Landroid/net/Uri;
    //   12: aconst_null
    //   13: iload_0
    //   14: invokestatic 394	com/hyperspeed/rocketclean/pro/dap:b	(Z)Ljava/lang/String;
    //   17: iconst_1
    //   18: anewarray 130	java/lang/String
    //   21: dup
    //   22: iconst_0
    //   23: invokestatic 398	com/hyperspeed/rocketclean/pro/daq:m	()J
    //   26: invokestatic 402	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   29: aastore
    //   30: ldc_w 404
    //   33: invokevirtual 410	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   36: astore_1
    //   37: aload_1
    //   38: ifnonnull +17 -> 55
    //   41: new 151	java/util/ArrayList
    //   44: dup
    //   45: invokespecial 152	java/util/ArrayList:<init>	()V
    //   48: areturn
    //   49: astore_1
    //   50: aconst_null
    //   51: astore_1
    //   52: goto -15 -> 37
    //   55: new 151	java/util/ArrayList
    //   58: dup
    //   59: invokespecial 152	java/util/ArrayList:<init>	()V
    //   62: astore_2
    //   63: aload_1
    //   64: invokeinterface 415 1 0
    //   69: ifeq +150 -> 219
    //   72: new 417	com/hyperspeed/rocketclean/pro/dao
    //   75: dup
    //   76: invokespecial 418	com/hyperspeed/rocketclean/pro/dao:<init>	()V
    //   79: astore_3
    //   80: aload_3
    //   81: aload_1
    //   82: aload_1
    //   83: ldc_w 420
    //   86: invokeinterface 424 2 0
    //   91: invokeinterface 428 2 0
    //   96: putfield 430	com/hyperspeed/rocketclean/pro/dao:b	Ljava/lang/String;
    //   99: aload_3
    //   100: aload_1
    //   101: aload_1
    //   102: ldc_w 432
    //   105: invokeinterface 424 2 0
    //   110: invokeinterface 435 2 0
    //   115: putfield 439	com/hyperspeed/rocketclean/pro/dao:z	J
    //   118: aload_3
    //   119: aload_1
    //   120: aload_1
    //   121: ldc_w 441
    //   124: invokeinterface 424 2 0
    //   129: invokeinterface 428 2 0
    //   134: putfield 444	com/hyperspeed/rocketclean/pro/dao:x	Ljava/lang/String;
    //   137: aload_3
    //   138: aload_1
    //   139: aload_1
    //   140: ldc_w 446
    //   143: invokeinterface 424 2 0
    //   148: invokeinterface 428 2 0
    //   153: putfield 448	com/hyperspeed/rocketclean/pro/dao:c	Ljava/lang/String;
    //   156: aload_3
    //   157: aload_1
    //   158: aload_1
    //   159: ldc_w 450
    //   162: invokeinterface 424 2 0
    //   167: invokeinterface 453 2 0
    //   172: i2l
    //   173: putfield 455	com/hyperspeed/rocketclean/pro/dao:m	J
    //   176: aload_3
    //   177: aload_1
    //   178: aload_1
    //   179: ldc_w 457
    //   182: invokeinterface 424 2 0
    //   187: invokeinterface 428 2 0
    //   192: putfield 459	com/hyperspeed/rocketclean/pro/dao:a	Ljava/lang/String;
    //   195: aload_2
    //   196: aload_3
    //   197: invokeinterface 229 2 0
    //   202: pop
    //   203: goto -140 -> 63
    //   206: astore_3
    //   207: aload_3
    //   208: invokevirtual 338	java/lang/Exception:printStackTrace	()V
    //   211: aload_1
    //   212: invokeinterface 460 1 0
    //   217: aload_2
    //   218: areturn
    //   219: aload_1
    //   220: invokeinterface 460 1 0
    //   225: aload_2
    //   226: areturn
    //   227: astore_3
    //   228: aload_1
    //   229: invokeinterface 460 1 0
    //   234: aload_2
    //   235: areturn
    //   236: astore_2
    //   237: aload_1
    //   238: invokeinterface 460 1 0
    //   243: aload_2
    //   244: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	245	0	paramBoolean	boolean
    //   36	2	1	localCursor	Cursor
    //   49	1	1	localException1	Exception
    //   51	187	1	localObject1	Object
    //   62	173	2	localArrayList	ArrayList
    //   236	8	2	localObject2	Object
    //   79	118	3	localDao	dao
    //   206	2	3	localException2	Exception
    //   227	1	3	localOutOfMemoryError	OutOfMemoryError
    // Exception table:
    //   from	to	target	type
    //   0	37	49	java/lang/Exception
    //   63	203	206	java/lang/Exception
    //   63	203	227	java/lang/OutOfMemoryError
    //   63	203	236	finally
    //   207	211	236	finally
  }
  
  public static void mn()
  {
    Bundle localBundle = new Bundle();
    localBundle.putBoolean("EXTRA_IS_FIRST_GUIDE_FLAG", false);
    cgb.m(m(cep.m()), "METHOD_SET_FIRST_GUIDE_FLAG", null, localBundle);
  }
  
  public static Uri n(Context paramContext)
  {
    return Uri.parse("content://" + paramContext.getPackageName() + ".blocked_notification/block_notifications");
  }
  
  private void n(final Runnable paramRunnable)
  {
    crj.m().m.execute(new Runnable()
    {
      /* Error */
      public final void run()
      {
        // Byte code:
        //   0: iconst_0
        //   1: istore_2
        //   2: invokestatic 33	com/hyperspeed/rocketclean/pro/dap:d	()Ljava/lang/String;
        //   5: astore 6
        //   7: invokestatic 38	com/hyperspeed/rocketclean/pro/dan:m	()Lcom/hyperspeed/rocketclean/pro/dan;
        //   10: astore 5
        //   12: invokestatic 43	com/hyperspeed/rocketclean/pro/daq:m	()J
        //   15: lstore_3
        //   16: aload 5
        //   18: iconst_1
        //   19: anewarray 45	java/lang/String
        //   22: dup
        //   23: iconst_0
        //   24: ldc 47
        //   26: aastore
        //   27: aload 6
        //   29: iconst_1
        //   30: anewarray 45	java/lang/String
        //   33: dup
        //   34: iconst_0
        //   35: lload_3
        //   36: invokestatic 51	java/lang/String:valueOf	(J)Ljava/lang/String;
        //   39: aastore
        //   40: ldc 53
        //   42: ldc 53
        //   44: ldc 53
        //   46: ldc 55
        //   48: invokevirtual 58	com/hyperspeed/rocketclean/pro/dan:m	([Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
        //   51: astore 5
        //   53: iload_2
        //   54: istore_1
        //   55: aload 5
        //   57: ifnull +37 -> 94
        //   60: aload 5
        //   62: invokeinterface 64 1 0
        //   67: ifeq +252 -> 319
        //   70: aload 5
        //   72: aload 5
        //   74: ldc 66
        //   76: invokeinterface 70 2 0
        //   81: invokeinterface 74 2 0
        //   86: istore_1
        //   87: aload 5
        //   89: invokeinterface 77 1 0
        //   94: new 79	java/util/ArrayList
        //   97: dup
        //   98: invokespecial 80	java/util/ArrayList:<init>	()V
        //   101: astore 7
        //   103: invokestatic 38	com/hyperspeed/rocketclean/pro/dan:m	()Lcom/hyperspeed/rocketclean/pro/dan;
        //   106: astore 5
        //   108: invokestatic 43	com/hyperspeed/rocketclean/pro/daq:m	()J
        //   111: lstore_3
        //   112: aload 5
        //   114: iconst_1
        //   115: anewarray 45	java/lang/String
        //   118: dup
        //   119: iconst_0
        //   120: ldc 82
        //   122: aastore
        //   123: aload 6
        //   125: iconst_1
        //   126: anewarray 45	java/lang/String
        //   129: dup
        //   130: iconst_0
        //   131: lload_3
        //   132: invokestatic 51	java/lang/String:valueOf	(J)Ljava/lang/String;
        //   135: aastore
        //   136: ldc 82
        //   138: ldc 53
        //   140: ldc 84
        //   142: ldc 86
        //   144: invokevirtual 58	com/hyperspeed/rocketclean/pro/dan:m	([Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
        //   147: astore 5
        //   149: aload 5
        //   151: ifnull +54 -> 205
        //   154: aload 5
        //   156: invokeinterface 89 1 0
        //   161: ifeq +136 -> 297
        //   164: aload 7
        //   166: aload 5
        //   168: aload 5
        //   170: ldc 82
        //   172: invokeinterface 70 2 0
        //   177: invokeinterface 93 2 0
        //   182: invokeinterface 99 2 0
        //   187: pop
        //   188: goto -34 -> 154
        //   191: astore 6
        //   193: aload 6
        //   195: invokevirtual 102	java/lang/Exception:printStackTrace	()V
        //   198: aload 5
        //   200: invokeinterface 77 1 0
        //   205: ldc 104
        //   207: aload 7
        //   209: invokestatic 110	android/text/TextUtils:join	(Ljava/lang/CharSequence;Ljava/lang/Iterable;)Ljava/lang/String;
        //   212: astore 5
        //   214: new 112	android/os/Handler
        //   217: dup
        //   218: invokestatic 118	android/os/Looper:getMainLooper	()Landroid/os/Looper;
        //   221: invokespecial 121	android/os/Handler:<init>	(Landroid/os/Looper;)V
        //   224: new 13	com/hyperspeed/rocketclean/pro/dap$8$1
        //   227: dup
        //   228: aload_0
        //   229: iload_1
        //   230: aload 5
        //   232: invokespecial 124	com/hyperspeed/rocketclean/pro/dap$8$1:<init>	(Lcom/hyperspeed/rocketclean/pro/dap$8;ILjava/lang/String;)V
        //   235: invokevirtual 128	android/os/Handler:post	(Ljava/lang/Runnable;)Z
        //   238: pop
        //   239: return
        //   240: astore 5
        //   242: aload 5
        //   244: invokevirtual 102	java/lang/Exception:printStackTrace	()V
        //   247: aconst_null
        //   248: astore 5
        //   250: goto -197 -> 53
        //   253: astore 7
        //   255: aload 7
        //   257: invokevirtual 102	java/lang/Exception:printStackTrace	()V
        //   260: aload 5
        //   262: invokeinterface 77 1 0
        //   267: iload_2
        //   268: istore_1
        //   269: goto -175 -> 94
        //   272: astore 6
        //   274: aload 5
        //   276: invokeinterface 77 1 0
        //   281: aload 6
        //   283: athrow
        //   284: astore 5
        //   286: aload 5
        //   288: invokevirtual 102	java/lang/Exception:printStackTrace	()V
        //   291: aconst_null
        //   292: astore 5
        //   294: goto -145 -> 149
        //   297: aload 5
        //   299: invokeinterface 77 1 0
        //   304: goto -99 -> 205
        //   307: astore 6
        //   309: aload 5
        //   311: invokeinterface 77 1 0
        //   316: aload 6
        //   318: athrow
        //   319: iconst_0
        //   320: istore_1
        //   321: goto -234 -> 87
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	324	0	this	8
        //   54	267	1	i	int
        //   1	267	2	j	int
        //   15	117	3	l	long
        //   10	221	5	localObject1	Object
        //   240	3	5	localException1	Exception
        //   248	27	5	localObject2	Object
        //   284	3	5	localException2	Exception
        //   292	18	5	localObject3	Object
        //   5	119	6	str	String
        //   191	3	6	localException3	Exception
        //   272	10	6	localObject4	Object
        //   307	10	6	localObject5	Object
        //   101	107	7	localArrayList	ArrayList
        //   253	3	7	localException4	Exception
        // Exception table:
        //   from	to	target	type
        //   154	188	191	java/lang/Exception
        //   7	53	240	java/lang/Exception
        //   60	87	253	java/lang/Exception
        //   60	87	272	finally
        //   255	260	272	finally
        //   103	149	284	java/lang/Exception
        //   154	188	307	finally
        //   193	198	307	finally
      }
    });
  }
  
  public static void n(String paramString)
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("EXTRA_APP_PACKAGE_NAME", paramString);
    cgb.m(mn(cep.m()), "METHOD_REMOVE_APP_FROM_UNBLOCK_LIST", null, localBundle);
  }
  
  public static void n(boolean paramBoolean)
  {
    Bundle localBundle = new Bundle();
    localBundle.putBoolean("EXTRA_IS_CUSTOM_HEADS_UP", paramBoolean);
    cgb.m(m(cep.m()), "METHOD_SET_CUSTOM_HEADS_UP_FLAG", null, localBundle);
  }
  
  public static boolean n()
  {
    Bundle localBundle = cgb.m(m(cep.m()), "METHOD_GET_FIRST_GUIDE_FLAG", null, null);
    return (localBundle != null) && (localBundle.getBoolean("EXTRA_IS_FIRST_GUIDE_FLAG"));
  }
  
  private ArrayList<String> sd()
  {
    Object localObject = cgh.m(getContext(), "optimizer_notification_organizer").m("PREF_KEY_UNBLOCK_APP_LIST", null);
    ArrayList localArrayList = new ArrayList();
    if (TextUtils.isEmpty((CharSequence)localObject))
    {
      localObject = m;
      Iterator localIterator = cep.m().getPackageManager().getInstalledPackages(0).iterator();
      while (localIterator.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
        if (((List)localObject).contains(localPackageInfo.packageName)) {
          localArrayList.add(localPackageInfo.packageName);
        }
      }
      if (!localArrayList.contains(cep.m().getPackageName())) {
        localArrayList.add(cep.m().getPackageName());
      }
      cgh.m(getContext(), "optimizer_notification_organizer").mn("PREF_KEY_UNBLOCK_APP_LIST", TextUtils.join(";", localArrayList));
      return localArrayList;
    }
    return new ArrayList(Arrays.asList(((String)localObject).split(";")));
  }
  
  public static Uri v(Context paramContext)
  {
    return Uri.parse("content://" + paramContext.getPackageName() + ".blocked_notification/message_security_notifications");
  }
  
  public static List<String> v()
  {
    try
    {
      Object localObject1 = cep.m().getContentResolver();
      localObject3 = cep.m();
      localObject3 = Uri.parse("content://" + ((Context)localObject3).getPackageName() + ".blocked_notification/block_notifications_app");
      String str = b(false);
      long l = daq.m();
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
  
  public static boolean x()
  {
    Bundle localBundle = cgb.m(m(cep.m()), "METHOD_IS_JUNK_NOTIFICATION_ENABLE_ONCE", null, null);
    return (localBundle != null) && (localBundle.getBoolean("EXTRA_IS_JUNK_NOTIFICATION_ENABLE_ONCE"));
  }
  
  public static int z()
  {
    Object localObject2;
    try
    {
      Object localObject1 = cep.m().getContentResolver();
      Uri localUri = n(cep.m());
      String str = b(false);
      long l = daq.m();
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
  
  public static List<String> za()
  {
    Object localObject = cgb.m(m(cep.m()), "METHOD_GET_BLOCKED_AND_TIME_VALID_APP_PACKAGE_NAME_LIST", null, null);
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
            if (!paramString1.equals("METHOD_SET_JUNK_NOTIFICATION_SWITCH")) {
              break;
            }
            i = 0;
            break;
            if (!paramString1.equals("METHOD_GET_JUNK_NOTIFICATION_SWITCH")) {
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
            if (!paramString1.equals("METHOD_GET_PRIVATE_MESSAGE_FIRST_GUIDE_FLAG")) {
              break;
            }
            i = 14;
            break;
            if (!paramString1.equals("METHOD_SET_PRIVATE_MESSAGE_FIRST_GUIDE_FLAG")) {
              break;
            }
            i = 15;
            break;
            if (!paramString1.equals("METHOD_IS_USER_CLOSED")) {
              break;
            }
            i = 16;
            break;
            if (!paramString1.equals("METHOD_SET_USER_CLOSED")) {
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
            if (!paramString1.equals("METHOD_IS_JUNK_NOTIFICATION_ENABLE_ONCE")) {
              break;
            }
            i = 20;
            break;
            if (!paramString1.equals("METHOD_SET_JUNK_NOTIFICATION_ENABLE_ONCE")) {
              break;
            }
            i = 21;
            break;
            if (!paramString1.equals("METHOD_IS_PRIVATE_MESSAGE_ENABLE_ONCE")) {
              break;
            }
            i = 22;
            break;
            if (!paramString1.equals("METHOD_SET_PRIVATE_MESSAGE_ENABLE_ONCE")) {
              break;
            }
            i = 23;
            break;
            if (!paramString1.equals("METHOD_GET_KEEP_MESSAGE_PRIVATE_APP_LIST")) {
              break;
            }
            i = 24;
            break;
            if (!paramString1.equals("METHOD_ADD_APP_TO_MESSAGE_PRIVATE_APP_LIST")) {
              break;
            }
            i = 25;
            break;
            if (!paramString1.equals("METHOD_CLEAR_PRIVATE_MESSAGE_APP_LIST")) {
              break;
            }
            i = 26;
            break;
            if (!paramString1.equals("METHOD_UPDATE_PRIVATE_MESSAGE_APP_LIST")) {
              break;
            }
            i = 27;
            break;
            if (!paramString1.equals("METHOD_REMOVE_APP_FROM_MESSAGE_PRIVATE_APP_LIST")) {
              break;
            }
            i = 28;
            break;
            if (!paramString1.equals("METHOD_IS_APP_IN_SECURITY_LIST")) {
              break;
            }
            i = 29;
            break;
            if (!paramString1.equals("METHOD_GET_PRIVATE_MSG_COUNT")) {
              break;
            }
            i = 30;
            break;
            if (!paramString1.equals("METHOD_GET_PRIVATE_MSG_APP_LIST")) {
              break;
            }
            i = 31;
            break;
            if (!paramString1.equals("METHOD_IS_CUSTOM_HEADS_UP")) {
              break;
            }
            i = 32;
            break;
            if (!paramString1.equals("METHOD_SET_CUSTOM_HEADS_UP_FLAG")) {
              break;
            }
            i = 33;
            break;
            cgh.m(getContext(), "optimizer_notification_organizer").mn("PREF_KEY_SWITCHER", paramBundle.getBoolean("EXTRA_SWITCH_STATE"));
            cep.m().getContentResolver().notifyChange(bv(getContext()), null);
            return paramString2;
            paramString2.putBoolean("EXTRA_SWITCH_STATE", cgh.m(getContext(), "optimizer_notification_organizer").m("PREF_KEY_SWITCHER", false));
            return paramString2;
            cgh.m(getContext(), "optimizer_message_security").mn("PREF_KEY_SWITCHER", paramBundle.getBoolean("EXTRA_SWITCH_STATE"));
            cep.m().getContentResolver().notifyChange(bv(getContext()), null);
            return paramString2;
            paramString2.putBoolean("EXTRA_SWITCH_STATE", cgh.m(getContext(), "optimizer_message_security").m("PREF_KEY_SWITCHER", false));
            return paramString2;
            cgh.m(getContext(), "optimizer_notification_organizer").n("PREF_KEY_CLEAN_TIME", System.currentTimeMillis());
            return paramString2;
            paramString2.putLong("EXTRA_CLEAN_TIME", cgh.m(getContext(), "optimizer_notification_organizer").m("PREF_KEY_CLEAN_TIME", 0L));
            return paramString2;
            paramString1 = sd();
          } while (paramString1.contains(paramBundle.getString("EXTRA_APP_PACKAGE_NAME")));
          paramString1.add(paramBundle.getString("EXTRA_APP_PACKAGE_NAME"));
          cgh.m(getContext(), "optimizer_notification_organizer").mn("PREF_KEY_UNBLOCK_APP_LIST", TextUtils.join(";", paramString1));
          cep.m().getContentResolver().notifyChange(mn(getContext()), null);
          cep.m().getContentResolver().notifyChange(b(getContext()), null);
          return paramString2;
          paramString1 = sd();
        } while (!paramString1.remove(paramBundle.getString("EXTRA_APP_PACKAGE_NAME")));
        cgh.m(getContext(), "optimizer_notification_organizer").mn("PREF_KEY_UNBLOCK_APP_LIST", TextUtils.join(";", paramString1));
        cep.m().getContentResolver().notifyChange(mn(getContext()), null);
        cep.m().getContentResolver().notifyChange(b(getContext()), null);
        return paramString2;
        paramString1 = f();
        paramBundle = new ArrayList();
        Iterator localIterator = cep.m().getPackageManager().getInstalledPackages(0).iterator();
        while (localIterator.hasNext())
        {
          PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
          if (!paramString1.contains(localPackageInfo.packageName)) {
            paramBundle.add(localPackageInfo.packageName);
          }
        }
        cgh.m(getContext(), "optimizer_notification_organizer").mn("PREF_KEY_UNBLOCK_APP_LIST", TextUtils.join(";", paramBundle));
        return paramString2;
        paramString2.putStringArrayList("EXTRA_APP_PACKAGE_NAME_LIST", sd());
        return paramString2;
        paramString2.putLong("EXTRA_KEY_TIME_MILLIS", cgh.m(getContext(), "optimizer_notification_organizer").m("PREF_KEY_TIME_MILLIS", 0L));
        return paramString2;
        cgh.m(getContext(), "optimizer_notification_organizer").n("PREF_KEY_TIME_MILLIS", paramBundle.getLong("EXTRA_KEY_TIME_MILLIS"));
        return paramString2;
        paramString2.putBoolean("EXTRA_IS_FIRST_GUIDE_FLAG", cgh.m(getContext(), "optimizer_notification_organizer").m("PREF_KEY_FIRST_GUIDE_FLAG", true));
        return paramString2;
        cgh.m(getContext(), "optimizer_notification_organizer").mn("PREF_KEY_FIRST_GUIDE_FLAG", paramBundle.getBoolean("EXTRA_IS_FIRST_GUIDE_FLAG"));
        return paramString2;
        paramString2.putBoolean("EXTRA_IS_FIRST_GUIDE_FLAG", cgh.m(getContext(), "optimizer_message_security").m("PREF_KEY_FIRST_GUIDE_FLAG", true));
        return paramString2;
        cgh.m(getContext(), "optimizer_message_security").mn("PREF_KEY_FIRST_GUIDE_FLAG", paramBundle.getBoolean("EXTRA_IS_FIRST_GUIDE_FLAG"));
        return paramString2;
        paramString2.putBoolean("EXTRA_IS_USER_CLOSED", cgh.m(getContext(), "optimizer_notification_organizer").m("PREF_KEY_IS_USER_CLOSED", false));
        return paramString2;
        cgh.m(getContext(), "optimizer_notification_organizer").mn("PREF_KEY_IS_USER_CLOSED", paramBundle.getBoolean("EXTRA_IS_USER_CLOSED"));
        return paramString2;
        paramString2.putInt("EXTRA_BLOCKED_NOTIFICATION_COUNT", cgh.m(getContext(), "optimizer_notification_organizer").m("PREF_KEY_BLOCKED_AND_TIME_VALID_NOTIFICATION_COUNT", 0));
        return paramString2;
        paramString2.putStringArrayList("EXTRA_APP_PACKAGE_NAME_LIST", new ArrayList(Arrays.asList(cgh.m(getContext(), "optimizer_notification_organizer").m("PREF_KEY_BLOCKED_AND_TIME_VALID_APP_PACKAGE_NAME_LIST", "").split(";"))));
        return paramString2;
        paramString2.putBoolean("EXTRA_IS_JUNK_NOTIFICATION_ENABLE_ONCE", cgh.m(getContext(), "optimizer_notification_organizer").m("PREF_KEY_IS_NOTIFICATION_ORGANIZER_ENABLE_ONCE", false));
        return paramString2;
        cgh.m(getContext(), "optimizer_notification_organizer").mn("PREF_KEY_IS_NOTIFICATION_ORGANIZER_ENABLE_ONCE", paramBundle.getBoolean("EXTRA_IS_JUNK_NOTIFICATION_ENABLE_ONCE"));
        return paramString2;
        paramString2.putBoolean("EXTRA_IS_PRIVATE_MESSAGE_ENABLE_ONCE", cgh.m(getContext(), "optimizer_message_security").m("PREF_KEY_IS_PRIVATE_MESSAGE_ENABLE_ONCE", false));
        return paramString2;
        cgh.m(getContext(), "optimizer_message_security").mn("PREF_KEY_IS_PRIVATE_MESSAGE_ENABLE_ONCE", paramBundle.getBoolean("EXTRA_IS_PRIVATE_MESSAGE_ENABLE_ONCE"));
        return paramString2;
        paramString2.putStringArrayList("EXTRA_APP_PACKAGE_NAME_LIST", f());
        return paramString2;
        paramString1 = f();
      } while (paramString1.contains(paramBundle.getString("EXTRA_APP_PACKAGE_NAME")));
      paramString1.add(paramBundle.getString("EXTRA_APP_PACKAGE_NAME"));
      cgh.m(getContext(), "optimizer_message_security").mn("PREF_KEY_KEEP_MESSAGE_PRIVATE_APP_LIST", TextUtils.join(";", paramString1));
      getContext().getContentResolver().notifyChange(c(getContext()), null);
      return paramString2;
      paramString1 = cgh.m(getContext(), "optimizer_message_security");
      paramString1.mn("PREF_KEY_KEEP_MESSAGE_PRIVATE_APP_LIST", "");
      paramString1.mn("PREF_KEY_PRIVATE_APP_LIST_INITIALIZED", true);
      return paramString2;
      paramString1 = paramBundle.getStringArrayList("EXTRA_APP_PACKAGE_NAME_LIST");
      cgh.m(getContext(), "optimizer_message_security").mn("PREF_KEY_KEEP_MESSAGE_PRIVATE_APP_LIST", TextUtils.join(";", paramString1));
      getContext().getContentResolver().notifyChange(c(getContext()), null);
      return paramString2;
      paramString1 = f();
    } while (!paramString1.remove(paramBundle.getString("EXTRA_APP_PACKAGE_NAME")));
    cgh.m(getContext(), "optimizer_message_security").mn("PREF_KEY_KEEP_MESSAGE_PRIVATE_APP_LIST", TextUtils.join(";", paramString1));
    getContext().getContentResolver().notifyChange(c(getContext()), null);
    return paramString2;
    paramString2.putBoolean("EXTRA_IS_APP_IN_SECURITY_LIST", f().contains(paramBundle.getString("EXTRA_APP_PACKAGE_NAME")));
    return paramString2;
    paramString2.putInt("EXTRA_BLOCKED_NOTIFICATION_COUNT", cgh.m(getContext(), "optimizer_message_security").m("PREF_KEY_PRIVATE_MSG_COUNT", 0));
    return paramString2;
    paramString2.putStringArrayList("EXTRA_APP_PACKAGE_NAME_LIST", new ArrayList(Arrays.asList(cgh.m(getContext(), "optimizer_message_security").m("PREF_KEY_PRIVATE_MSG_APP_LIST", "").split(";"))));
    return paramString2;
    paramString2.putBoolean("EXTRA_IS_CUSTOM_HEADS_UP", cgh.m(getContext(), "optimizer_notification_organizer").m("PREF_KEY_IS_CUSTOM_HEADS_UP", true));
    return paramString2;
    cgh.m(getContext(), "optimizer_notification_organizer").mn("PREF_KEY_IS_CUSTOM_HEADS_UP", paramBundle.getBoolean("EXTRA_IS_CUSTOM_HEADS_UP"));
    return paramString2;
  }
  
  public int delete(Uri paramUri, String paramString, String[] paramArrayOfString)
  {
    int i;
    if (!TextUtils.isEmpty(paramString))
    {
      paramString = paramString + " AND ";
      switch (this.mn.match(paramUri))
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
        j = dan.m().m(paramUri, paramArrayOfString);
        i = j;
        if (j > 0) {
          try
          {
            m(new Runnable()
            {
              public final void run()
              {
                cep.m().getContentResolver().notifyChange(dap.n(cep.m()), null);
              }
            });
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
        j = dan.m().m(paramUri, paramArrayOfString);
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
        n(new Runnable()
        {
          public final void run() {}
        });
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
    switch (this.mn.match(paramUri))
    {
    }
    long l;
    for (;;)
    {
      return null;
      try
      {
        paramContentValues.put("notification_type", Integer.valueOf(0));
        l = dan.m().m(paramContentValues);
        m(new Runnable()
        {
          public final void run()
          {
            cep.m().getContentResolver().notifyChange(dap.n(cep.m()), null);
          }
        });
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
        l = dan.m().m(paramContentValues);
        if ((!cep.n) || (l >= 0L)) {
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
    n(new Runnable()
    {
      public final void run()
      {
        cep.m().getContentResolver().notifyChange(dap.v(dap.this.getContext()), null);
      }
    });
    return paramUri;
  }
  
  public boolean onCreate()
  {
    this.mn = new UriMatcher(-1);
    this.mn.addURI(cep.m().getPackageName() + ".blocked_notification", "block_notifications", 3);
    this.mn.addURI(cep.m().getPackageName() + ".blocked_notification", "block_notifications_app", 4);
    this.mn.addURI(cep.m().getPackageName() + ".blocked_notification", "message_security_notifications", 1);
    this.mn.addURI(cep.m().getPackageName() + ".blocked_notification", "message_security_notifications_app", 2);
    return true;
  }
  
  public Cursor query(Uri paramUri, String[] paramArrayOfString1, String paramString1, String[] paramArrayOfString2, String paramString2)
  {
    if (!TextUtils.isEmpty(paramString1)) {}
    for (paramString1 = paramString1 + " AND ";; paramString1 = " ") {
      switch (this.mn.match(paramUri))
      {
      default: 
        return null;
      }
    }
    paramUri = paramString1 + "notification_type = 0";
    return dan.m().m(paramArrayOfString1, paramUri, paramArrayOfString2, "", "", paramString2, "");
    paramUri = paramString1 + "notification_type = 0";
    return dan.m().m(paramArrayOfString1, paramUri, paramArrayOfString2, "package_name", "", paramString2, "");
    paramUri = paramString1 + "notification_type = 1";
    return dan.m().m(paramArrayOfString1, paramUri, paramArrayOfString2, "", "", paramString2, "");
    paramUri = paramString1 + "notification_type = 1";
    return dan.m().m(paramArrayOfString1, paramUri, paramArrayOfString2, "package_name", "", paramString2, "");
  }
  
  public int update(Uri paramUri, ContentValues paramContentValues, String paramString, String[] paramArrayOfString)
  {
    if (!TextUtils.isEmpty(paramString)) {
      paramString = paramString + " AND ";
    }
    switch (this.mn.match(paramUri))
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
          dan.m().m(paramContentValues, paramUri, paramArrayOfString);
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
      i = dan.m().m(paramContentValues, paramUri, paramArrayOfString);
      try
      {
        paramUri = getContext().getContentResolver();
        paramContentValues = cep.m();
        paramUri.notifyChange(Uri.parse("content://" + paramContentValues.getPackageName() + ".blocked_notification/update_unread_private_message"), null);
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
