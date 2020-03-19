package com.syntellia.fleksy.personalization;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import android.support.annotation.WorkerThread;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.batch.BatchCallback;
import com.google.api.client.googleapis.batch.BatchRequest;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.Gmail.Builder;
import com.google.api.services.gmail.Gmail.Users;
import com.google.api.services.gmail.Gmail.Users.Messages;
import com.google.api.services.gmail.Gmail.Users.Messages.Get;
import com.google.api.services.gmail.Gmail.Users.Messages.List;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.Message;
import com.google.api.services.gmail.model.MessagePart;
import com.google.api.services.gmail.model.MessagePartBody;
import com.syntellia.fleksy.keyboard.Fleksy;
import com.syntellia.fleksy.keyboard.c;
import com.syntellia.fleksy.utils.a.b;
import com.twitter.sdk.android.core.a.o;
import com.twitter.sdk.android.core.f;
import com.twitter.sdk.android.core.m;
import com.twitter.sdk.android.core.n;
import com.twitter.sdk.android.core.p;
import com.twitter.sdk.android.core.services.StatusesService;
import com.twitter.sdk.android.core.t;
import com.twitter.sdk.android.core.u;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class a
{
  private static final String a = "personalization_words.json";
  private static String b = "timestamp";
  private static final String c = "content://sms/sent";
  private static final String d = "body";
  private static final long e = 100L;
  private static final int f = 1000;
  private static final int g = 200;
  private static final long h = 86400000L;
  private static String i = "sms";
  private static String j = "device";
  private static String k = "twitter";
  private static String l = "gmail";
  private static String[] m = { "sms", "device", "twitter", "gmail" };
  
  public a() {}
  
  /* Error */
  public static ArrayList<String> a(Context paramContext)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: new 67	java/util/ArrayList
    //   5: dup
    //   6: invokespecial 68	java/util/ArrayList:<init>	()V
    //   9: astore_3
    //   10: aload_0
    //   11: ldc 70
    //   13: invokestatic 75	com/syntellia/fleksy/utils/l:a	(Landroid/content/Context;Ljava/lang/String;)Z
    //   16: ifeq +122 -> 138
    //   19: ldc 20
    //   21: invokestatic 81	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   24: astore 4
    //   26: aload_0
    //   27: invokevirtual 87	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   30: aload 4
    //   32: iconst_1
    //   33: anewarray 53	java/lang/String
    //   36: dup
    //   37: iconst_0
    //   38: ldc 23
    //   40: aastore
    //   41: aconst_null
    //   42: aconst_null
    //   43: aconst_null
    //   44: invokevirtual 93	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   47: astore_0
    //   48: aload_0
    //   49: ifnull +35 -> 84
    //   52: aload_0
    //   53: invokeinterface 99 1 0
    //   58: ifeq +26 -> 84
    //   61: aload_3
    //   62: aload_0
    //   63: iconst_0
    //   64: invokeinterface 103 2 0
    //   69: invokevirtual 107	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   72: pop
    //   73: aload_0
    //   74: invokeinterface 110 1 0
    //   79: istore_1
    //   80: iload_1
    //   81: ifne -20 -> 61
    //   84: aload_0
    //   85: ifnull +53 -> 138
    //   88: aload_0
    //   89: invokeinterface 113 1 0
    //   94: aload_3
    //   95: areturn
    //   96: astore_0
    //   97: aconst_null
    //   98: astore_0
    //   99: aload_0
    //   100: ifnull +36 -> 136
    //   103: aload_0
    //   104: invokeinterface 113 1 0
    //   109: aconst_null
    //   110: areturn
    //   111: astore_3
    //   112: aload_2
    //   113: astore_0
    //   114: aload_3
    //   115: astore_2
    //   116: aload_0
    //   117: ifnull +9 -> 126
    //   120: aload_0
    //   121: invokeinterface 113 1 0
    //   126: aload_2
    //   127: athrow
    //   128: astore_2
    //   129: goto -13 -> 116
    //   132: astore_2
    //   133: goto -34 -> 99
    //   136: aconst_null
    //   137: areturn
    //   138: aload_3
    //   139: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	140	0	paramContext	Context
    //   79	2	1	bool	boolean
    //   1	126	2	localObject1	Object
    //   128	1	2	localObject2	Object
    //   132	1	2	localException	Exception
    //   9	86	3	localArrayList	ArrayList
    //   111	28	3	localArrayList1	ArrayList<String>
    //   24	7	4	localUri	android.net.Uri
    // Exception table:
    //   from	to	target	type
    //   26	48	96	java/lang/Exception
    //   26	48	111	finally
    //   52	61	128	finally
    //   61	80	128	finally
    //   52	61	132	java/lang/Exception
    //   61	80	132	java/lang/Exception
  }
  
  @WorkerThread
  public static void a(Context paramContext, n paramN, PersonalizationService.a paramA)
  {
    b(paramContext, paramN, null, new ArrayList(), paramA);
  }
  
  @WorkerThread
  public static void a(Context paramContext, String paramString, PersonalizationService.a paramA)
  {
    ArrayList localArrayList = new ArrayList();
    Gmail localGmail = new Gmail.Builder(AndroidHttp.newCompatibleTransport(), JacksonFactory.getDefaultInstance(), null).setApplicationName("Fleksy Cloud & Personalization").build();
    try
    {
      Object localObject1 = new ArrayList();
      ((List)localObject1).add("SENT");
      Object localObject2 = (ListMessagesResponse)localGmail.users().messages().list("me").setOauthToken(paramString).setIncludeSpamTrash(Boolean.valueOf(false)).setMaxResults(Long.valueOf(100L)).setLabelIds((List)localObject1).execute();
      localObject1 = ((ListMessagesResponse)localObject2).getMessages();
      if ((localObject1 != null) && (!((List)localObject1).isEmpty()))
      {
        localObject1 = new BatchRequest(AndroidHttp.newCompatibleTransport(), null);
        localObject2 = ((ListMessagesResponse)localObject2).getMessages().iterator();
        while (((Iterator)localObject2).hasNext())
        {
          Message localMessage = (Message)((Iterator)localObject2).next();
          ((BatchRequest)localObject1).queue(localGmail.users().messages().get("me", localMessage.getId()).setFormat("full").setOauthToken(paramString).buildHttpRequest(), Message.class, Void.class, new BatchCallback()
          {
            private static void a() {}
            
            private void a(Message paramAnonymousMessage)
            {
              if ((paramAnonymousMessage != null) && (paramAnonymousMessage.getPayload() != null) && (paramAnonymousMessage.getPayload().getParts() != null))
              {
                paramAnonymousMessage = paramAnonymousMessage.getPayload().getParts().iterator();
                while (paramAnonymousMessage.hasNext())
                {
                  MessagePart localMessagePart = (MessagePart)paramAnonymousMessage.next();
                  if (localMessagePart.getMimeType().equalsIgnoreCase("text/plain")) {
                    this.a.add(a.a(new String(localMessagePart.getBody().decodeData(), "UTF-8")));
                  }
                }
              }
            }
          });
        }
        ((BatchRequest)localObject1).execute();
      }
    }
    catch (Exception paramContext)
    {
      paramContext.toString();
      paramA.a(false);
      return;
    }
    paramA.a(paramContext, localArrayList);
  }
  
  public static void a(Context paramContext, String paramString, Set<String> paramSet, boolean paramBoolean)
  {
    JSONObject localJSONObject;
    JSONArray localJSONArray;
    Object localObject3;
    Object localObject2;
    try
    {
      localJSONObject = e(paramContext);
      localJSONArray = new JSONArray();
      localObject3 = null;
      localObject2 = null;
      paramSet = paramSet.iterator();
      while (paramSet.hasNext()) {
        localJSONArray.put((String)paramSet.next());
      }
      localObject1 = localObject2;
    }
    finally {}
    Object localObject1;
    paramSet = localObject3;
    for (;;)
    {
      try
      {
        localJSONObject.put(paramString, localJSONArray);
        localObject1 = localObject2;
        paramSet = localObject3;
        localJSONObject.put("timestamp", System.currentTimeMillis());
        localObject1 = localObject2;
        paramSet = localObject3;
        paramString = paramContext.openFileOutput("personalization_words.json", 0);
        localObject1 = paramString;
        paramSet = paramString;
        paramString.write(localJSONObject.toString().getBytes());
        localObject1 = paramString;
        paramSet = paramString;
        c.a(paramContext).a(Fleksy.c());
      }
      catch (Exception paramContext)
      {
        paramSet = (Set<String>)localObject1;
        paramContext.printStackTrace();
        if (localObject1 == null) {
          continue;
        }
        try
        {
          ((FileOutputStream)localObject1).close();
        }
        catch (IOException paramContext)
        {
          paramContext.printStackTrace();
        }
        continue;
      }
      finally
      {
        if (paramSet == null) {
          break label203;
        }
      }
      try
      {
        paramString.close();
        return;
      }
      catch (IOException paramContext)
      {
        paramContext.printStackTrace();
      }
    }
    try
    {
      paramSet.close();
      label203:
      throw paramContext;
    }
    catch (IOException paramString)
    {
      for (;;)
      {
        paramString.printStackTrace();
      }
    }
  }
  
  /* Error */
  public static void a(Context paramContext, JSONObject paramJSONObject)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: aload_0
    //   4: iconst_0
    //   5: invokestatic 346	com/syntellia/fleksy/personalization/a:a	(Landroid/content/Context;Z)V
    //   8: aconst_null
    //   9: astore_3
    //   10: aconst_null
    //   11: astore_2
    //   12: aload_0
    //   13: ldc 14
    //   15: iconst_0
    //   16: invokevirtual 312	android/content/Context:openFileOutput	(Ljava/lang/String;I)Ljava/io/FileOutputStream;
    //   19: astore 4
    //   21: aload 4
    //   23: astore_2
    //   24: aload 4
    //   26: astore_3
    //   27: aload 4
    //   29: aload_1
    //   30: invokevirtual 313	org/json/JSONObject:toString	()Ljava/lang/String;
    //   33: invokevirtual 317	java/lang/String:getBytes	()[B
    //   36: invokevirtual 323	java/io/FileOutputStream:write	([B)V
    //   39: aload 4
    //   41: astore_2
    //   42: aload 4
    //   44: astore_3
    //   45: aload_0
    //   46: invokestatic 328	com/syntellia/fleksy/keyboard/c:a	(Landroid/content/Context;)Lcom/syntellia/fleksy/keyboard/c;
    //   49: invokestatic 333	com/syntellia/fleksy/keyboard/Fleksy:c	()Lcom/syntellia/fleksy/keyboard/Fleksy;
    //   52: invokevirtual 336	com/syntellia/fleksy/keyboard/c:a	(Lcom/syntellia/fleksy/keyboard/Fleksy;)V
    //   55: aload 4
    //   57: ifnull +8 -> 65
    //   60: aload 4
    //   62: invokevirtual 337	java/io/FileOutputStream:close	()V
    //   65: ldc 2
    //   67: monitorexit
    //   68: return
    //   69: astore_0
    //   70: aload_0
    //   71: invokevirtual 340	java/io/IOException:printStackTrace	()V
    //   74: goto -9 -> 65
    //   77: astore_0
    //   78: ldc 2
    //   80: monitorexit
    //   81: aload_0
    //   82: athrow
    //   83: astore_0
    //   84: aload_2
    //   85: astore_3
    //   86: aload_0
    //   87: invokevirtual 341	java/lang/Exception:printStackTrace	()V
    //   90: aload_2
    //   91: ifnull -26 -> 65
    //   94: aload_2
    //   95: invokevirtual 337	java/io/FileOutputStream:close	()V
    //   98: goto -33 -> 65
    //   101: astore_0
    //   102: aload_0
    //   103: invokevirtual 340	java/io/IOException:printStackTrace	()V
    //   106: goto -41 -> 65
    //   109: astore_0
    //   110: aload_3
    //   111: ifnull +7 -> 118
    //   114: aload_3
    //   115: invokevirtual 337	java/io/FileOutputStream:close	()V
    //   118: aload_0
    //   119: athrow
    //   120: astore_1
    //   121: aload_1
    //   122: invokevirtual 340	java/io/IOException:printStackTrace	()V
    //   125: goto -7 -> 118
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	128	0	paramContext	Context
    //   0	128	1	paramJSONObject	JSONObject
    //   11	84	2	localObject1	Object
    //   9	106	3	localObject2	Object
    //   19	42	4	localFileOutputStream	FileOutputStream
    // Exception table:
    //   from	to	target	type
    //   60	65	69	java/io/IOException
    //   3	8	77	finally
    //   60	65	77	finally
    //   70	74	77	finally
    //   94	98	77	finally
    //   102	106	77	finally
    //   114	118	77	finally
    //   118	120	77	finally
    //   121	125	77	finally
    //   12	21	83	java/lang/Exception
    //   27	39	83	java/lang/Exception
    //   45	55	83	java/lang/Exception
    //   94	98	101	java/io/IOException
    //   12	21	109	finally
    //   27	39	109	finally
    //   45	55	109	finally
    //   86	90	109	finally
    //   114	118	120	java/io/IOException
  }
  
  public static void a(Context paramContext, boolean paramBoolean)
  {
    try
    {
      File localFile = paramContext.getFileStreamPath("personalization_words.json");
      if (localFile.exists()) {
        localFile.delete();
      }
      if (paramBoolean) {
        c.a(paramContext).a(Fleksy.c());
      }
      return;
    }
    finally {}
  }
  
  private static String b(String paramString)
  {
    localStringBuilder = new StringBuilder();
    paramString = new BufferedReader(new StringReader(paramString));
    int n = 0;
    try
    {
      for (;;)
      {
        String str = paramString.readLine();
        if ((str == null) || (n >= 30) || (str.startsWith(">")) || (str.contains("Forwarded message")) || ((str.startsWith("On")) && (str.contains(".com")))) {
          break;
        }
        localStringBuilder.append(str + "\n");
        n += 1;
      }
      return localStringBuilder.toString();
    }
    catch (IOException paramString) {}
  }
  
  public static ArrayList<String> b(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.addAll(g(paramContext));
    localArrayList.addAll(h(paramContext));
    localArrayList.addAll(i(paramContext));
    return localArrayList;
  }
  
  @WorkerThread
  private static void b(final Context paramContext, final n paramN, Long paramLong, ArrayList<String> paramArrayList, final PersonalizationService.a paramA)
  {
    t.a().a(paramN).b().userTimeline(Long.valueOf(paramN.b()), null, Integer.valueOf(200), null, paramLong, Boolean.valueOf(true), Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false), new f()
    {
      public final void a(m<List<o>> paramAnonymousM)
      {
        List localList = (List)paramAnonymousM.a;
        long l1 = 0L;
        int i = 0;
        while (i < localList.size())
        {
          o localO = (o)localList.get(i);
          this.a.add(localO.v);
          if (i == 0) {
            l1 = localO.h;
          }
          long l2 = l1;
          if (localO.h < l1) {
            l2 = localO.h;
          }
          i += 1;
          l1 = l2;
        }
        if ((this.a.size() >= 1000) || (((List)paramAnonymousM.a).isEmpty()) || (((List)paramAnonymousM.a).size() < 200))
        {
          paramA.a(paramContext, this.a);
          return;
        }
        a.a(paramContext, paramN, Long.valueOf(l1 - 1L), this.a, paramA);
      }
      
      public final void a(u paramAnonymousU)
      {
        PersonalizationService.a localA = paramA;
        paramAnonymousU.toString();
        localA.a(false);
      }
    });
  }
  
  public static void b(Context paramContext, boolean paramBoolean)
  {
    SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(paramContext);
    String str = paramContext.getString(2131690499);
    long l1 = localSharedPreferences.getLong(str, 0L);
    long l2 = System.currentTimeMillis();
    if (l2 - l1 > 86400000L)
    {
      localSharedPreferences.edit().putLong(str, l2).commit();
      PersonalizationService.a(paramContext, "device");
    }
  }
  
  public static Set<String> c(Context paramContext)
  {
    try
    {
      HashSet localHashSet = new HashSet();
      paramContext = e(paramContext);
      String[] arrayOfString = m;
      int n = 0;
      while (n < 4)
      {
        Object localObject = arrayOfString[n];
        boolean bool = paramContext.has((String)localObject);
        if (bool) {
          try
          {
            localObject = paramContext.getJSONArray((String)localObject);
            int i1 = 0;
            while (i1 < ((JSONArray)localObject).length())
            {
              localHashSet.add(((JSONArray)localObject).getString(i1));
              i1 += 1;
            }
            n += 1;
          }
          catch (JSONException localJSONException)
          {
            localJSONException.printStackTrace();
          }
        }
      }
      return localHashSet;
    }
    finally {}
  }
  
  public static ArrayList<String> d(Context paramContext)
  {
    ArrayList localArrayList;
    try
    {
      localArrayList = new ArrayList();
      paramContext = e(paramContext);
      String[] arrayOfString = m;
      int n = 0;
      for (;;)
      {
        if (n < 4)
        {
          String str = arrayOfString[n];
          try
          {
            if ((paramContext.has(str)) && (paramContext.get(str) != null)) {
              localArrayList.add(str);
            }
            n += 1;
          }
          catch (JSONException localJSONException)
          {
            for (;;)
            {
              localJSONException.printStackTrace();
            }
          }
        }
      }
    }
    finally {}
    return localArrayList;
  }
  
  /* Error */
  public static JSONObject e(Context paramContext)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: aload_0
    //   4: ldc 14
    //   6: invokevirtual 350	android/content/Context:getFileStreamPath	(Ljava/lang/String;)Ljava/io/File;
    //   9: astore_1
    //   10: aload_1
    //   11: invokevirtual 355	java/io/File:exists	()Z
    //   14: ifne +8 -> 22
    //   17: aload_1
    //   18: invokevirtual 498	java/io/File:createNewFile	()Z
    //   21: pop
    //   22: new 296	org/json/JSONObject
    //   25: dup
    //   26: invokespecial 499	org/json/JSONObject:<init>	()V
    //   29: astore_3
    //   30: aconst_null
    //   31: astore_2
    //   32: aconst_null
    //   33: astore_1
    //   34: aload_0
    //   35: new 352	java/io/File
    //   38: dup
    //   39: ldc 14
    //   41: invokespecial 500	java/io/File:<init>	(Ljava/lang/String;)V
    //   44: invokevirtual 503	java/io/File:getPath	()Ljava/lang/String;
    //   47: invokevirtual 507	android/content/Context:openFileInput	(Ljava/lang/String;)Ljava/io/FileInputStream;
    //   50: astore 4
    //   52: aload_3
    //   53: astore_0
    //   54: aload 4
    //   56: ifnull +150 -> 206
    //   59: aload 4
    //   61: astore_1
    //   62: aload 4
    //   64: astore_2
    //   65: new 363	java/io/BufferedReader
    //   68: dup
    //   69: new 509	java/io/InputStreamReader
    //   72: dup
    //   73: aload 4
    //   75: invokespecial 512	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   78: invokespecial 371	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   81: astore_0
    //   82: aload 4
    //   84: astore_1
    //   85: aload 4
    //   87: astore_2
    //   88: new 360	java/lang/StringBuilder
    //   91: dup
    //   92: invokespecial 361	java/lang/StringBuilder:<init>	()V
    //   95: astore 5
    //   97: aload 4
    //   99: astore_1
    //   100: aload 4
    //   102: astore_2
    //   103: aload_0
    //   104: invokevirtual 374	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   107: astore 6
    //   109: aload 6
    //   111: ifnull +45 -> 156
    //   114: aload 4
    //   116: astore_1
    //   117: aload 4
    //   119: astore_2
    //   120: aload 5
    //   122: aload 6
    //   124: invokevirtual 394	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   127: pop
    //   128: goto -31 -> 97
    //   131: astore_0
    //   132: aload_1
    //   133: astore_2
    //   134: aload_0
    //   135: invokevirtual 268	java/lang/Exception:toString	()Ljava/lang/String;
    //   138: pop
    //   139: aload_3
    //   140: astore_2
    //   141: aload_1
    //   142: ifnull +9 -> 151
    //   145: aload_1
    //   146: invokevirtual 515	java/io/InputStream:close	()V
    //   149: aload_3
    //   150: astore_2
    //   151: ldc 2
    //   153: monitorexit
    //   154: aload_2
    //   155: areturn
    //   156: aload 4
    //   158: astore_1
    //   159: aload 4
    //   161: astore_2
    //   162: aload 5
    //   164: invokevirtual 397	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   167: ifnull +73 -> 240
    //   170: aload 4
    //   172: astore_1
    //   173: aload 4
    //   175: astore_2
    //   176: aload 5
    //   178: invokevirtual 397	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   181: invokevirtual 516	java/lang/String:isEmpty	()Z
    //   184: ifne +56 -> 240
    //   187: aload 4
    //   189: astore_1
    //   190: aload 4
    //   192: astore_2
    //   193: new 296	org/json/JSONObject
    //   196: dup
    //   197: aload 5
    //   199: invokevirtual 397	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   202: invokespecial 517	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   205: astore_0
    //   206: aload_0
    //   207: astore_2
    //   208: aload 4
    //   210: ifnull -59 -> 151
    //   213: aload 4
    //   215: invokevirtual 515	java/io/InputStream:close	()V
    //   218: aload_0
    //   219: astore_2
    //   220: goto -69 -> 151
    //   223: astore_1
    //   224: aload_1
    //   225: invokevirtual 518	java/io/IOException:toString	()Ljava/lang/String;
    //   228: pop
    //   229: aload_0
    //   230: astore_2
    //   231: goto -80 -> 151
    //   234: astore_0
    //   235: ldc 2
    //   237: monitorexit
    //   238: aload_0
    //   239: athrow
    //   240: aload 4
    //   242: astore_1
    //   243: aload 4
    //   245: astore_2
    //   246: new 296	org/json/JSONObject
    //   249: dup
    //   250: invokespecial 499	org/json/JSONObject:<init>	()V
    //   253: astore_0
    //   254: goto -48 -> 206
    //   257: astore_0
    //   258: aload_0
    //   259: invokevirtual 518	java/io/IOException:toString	()Ljava/lang/String;
    //   262: pop
    //   263: aload_3
    //   264: astore_2
    //   265: goto -114 -> 151
    //   268: astore_0
    //   269: aload_2
    //   270: ifnull +7 -> 277
    //   273: aload_2
    //   274: invokevirtual 515	java/io/InputStream:close	()V
    //   277: aload_0
    //   278: athrow
    //   279: astore_1
    //   280: aload_1
    //   281: invokevirtual 518	java/io/IOException:toString	()Ljava/lang/String;
    //   284: pop
    //   285: goto -8 -> 277
    //   288: astore_1
    //   289: goto -267 -> 22
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	292	0	paramContext	Context
    //   9	181	1	localObject1	Object
    //   223	2	1	localIOException1	IOException
    //   242	1	1	localObject2	Object
    //   279	2	1	localIOException2	IOException
    //   288	1	1	localIOException3	IOException
    //   31	243	2	localObject3	Object
    //   29	235	3	localJSONObject	JSONObject
    //   50	194	4	localFileInputStream	java.io.FileInputStream
    //   95	103	5	localStringBuilder	StringBuilder
    //   107	16	6	str	String
    // Exception table:
    //   from	to	target	type
    //   34	52	131	java/lang/Exception
    //   65	82	131	java/lang/Exception
    //   88	97	131	java/lang/Exception
    //   103	109	131	java/lang/Exception
    //   120	128	131	java/lang/Exception
    //   162	170	131	java/lang/Exception
    //   176	187	131	java/lang/Exception
    //   193	206	131	java/lang/Exception
    //   246	254	131	java/lang/Exception
    //   213	218	223	java/io/IOException
    //   3	22	234	finally
    //   22	30	234	finally
    //   145	149	234	finally
    //   213	218	234	finally
    //   224	229	234	finally
    //   258	263	234	finally
    //   273	277	234	finally
    //   277	279	234	finally
    //   280	285	234	finally
    //   145	149	257	java/io/IOException
    //   34	52	268	finally
    //   65	82	268	finally
    //   88	97	268	finally
    //   103	109	268	finally
    //   120	128	268	finally
    //   134	139	268	finally
    //   162	170	268	finally
    //   176	187	268	finally
    //   193	206	268	finally
    //   246	254	268	finally
    //   273	277	279	java/io/IOException
    //   3	22	288	java/io/IOException
  }
  
  public static void f(Context paramContext)
  {
    int n = d(paramContext).size();
    if (n > 0) {
      b.a(paramContext, com.syntellia.fleksy.utils.a.a.d, 1, false);
    }
    if (n >= 3) {
      b.a(paramContext, com.syntellia.fleksy.utils.a.a.e, 3, false);
    }
  }
  
  /* Error */
  private static ArrayList<String> g(Context paramContext)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: new 67	java/util/ArrayList
    //   5: dup
    //   6: invokespecial 68	java/util/ArrayList:<init>	()V
    //   9: astore_2
    //   10: aload_0
    //   11: ldc_w 538
    //   14: invokestatic 75	com/syntellia/fleksy/utils/l:a	(Landroid/content/Context;Ljava/lang/String;)Z
    //   17: ifeq +110 -> 127
    //   20: aload_0
    //   21: invokevirtual 87	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   24: getstatic 544	android/provider/ContactsContract$Data:CONTENT_URI	Landroid/net/Uri;
    //   27: aconst_null
    //   28: ldc_w 546
    //   31: iconst_1
    //   32: anewarray 53	java/lang/String
    //   35: dup
    //   36: iconst_0
    //   37: ldc_w 548
    //   40: aastore
    //   41: ldc_w 550
    //   44: invokevirtual 93	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   47: astore_0
    //   48: aload_0
    //   49: ifnull +80 -> 129
    //   52: aload_0
    //   53: invokeinterface 110 1 0
    //   58: ifeq +71 -> 129
    //   61: aload_0
    //   62: aload_0
    //   63: ldc_w 552
    //   66: invokeinterface 556 2 0
    //   71: invokeinterface 103 2 0
    //   76: astore_1
    //   77: aload_0
    //   78: aload_0
    //   79: ldc_w 558
    //   82: invokeinterface 556 2 0
    //   87: invokeinterface 103 2 0
    //   92: astore_3
    //   93: aload_1
    //   94: ifnull +9 -> 103
    //   97: aload_2
    //   98: aload_1
    //   99: invokevirtual 107	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   102: pop
    //   103: aload_3
    //   104: ifnull -56 -> 48
    //   107: aload_2
    //   108: aload_3
    //   109: invokevirtual 107	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   112: pop
    //   113: goto -65 -> 48
    //   116: astore_1
    //   117: aload_0
    //   118: ifnull +9 -> 127
    //   121: aload_0
    //   122: invokeinterface 113 1 0
    //   127: aload_2
    //   128: areturn
    //   129: aload_0
    //   130: ifnull -3 -> 127
    //   133: aload_0
    //   134: invokeinterface 113 1 0
    //   139: aload_2
    //   140: areturn
    //   141: astore_2
    //   142: aload_1
    //   143: astore_0
    //   144: aload_2
    //   145: astore_1
    //   146: aload_0
    //   147: ifnull +9 -> 156
    //   150: aload_0
    //   151: invokeinterface 113 1 0
    //   156: aload_1
    //   157: athrow
    //   158: astore_1
    //   159: goto -13 -> 146
    //   162: astore_0
    //   163: aconst_null
    //   164: astore_0
    //   165: goto -48 -> 117
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	168	0	paramContext	Context
    //   1	98	1	str1	String
    //   116	27	1	localException	Exception
    //   145	12	1	localObject1	Object
    //   158	1	1	localObject2	Object
    //   9	131	2	localArrayList	ArrayList
    //   141	4	2	localObject3	Object
    //   92	17	3	str2	String
    // Exception table:
    //   from	to	target	type
    //   52	93	116	java/lang/Exception
    //   97	103	116	java/lang/Exception
    //   107	113	116	java/lang/Exception
    //   20	48	141	finally
    //   52	93	158	finally
    //   97	103	158	finally
    //   107	113	158	finally
    //   20	48	162	java/lang/Exception
  }
  
  /* Error */
  private static ArrayList<String> h(Context paramContext)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: new 67	java/util/ArrayList
    //   5: dup
    //   6: invokespecial 68	java/util/ArrayList:<init>	()V
    //   9: astore_3
    //   10: getstatic 561	android/provider/UserDictionary$Words:CONTENT_URI	Landroid/net/Uri;
    //   13: astore 4
    //   15: aload_0
    //   16: invokevirtual 87	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   19: aload 4
    //   21: iconst_1
    //   22: anewarray 53	java/lang/String
    //   25: dup
    //   26: iconst_0
    //   27: ldc_w 563
    //   30: aastore
    //   31: aconst_null
    //   32: aconst_null
    //   33: aconst_null
    //   34: invokevirtual 93	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   37: astore_0
    //   38: aload_0
    //   39: ifnull +35 -> 74
    //   42: aload_0
    //   43: invokeinterface 99 1 0
    //   48: ifeq +26 -> 74
    //   51: aload_3
    //   52: aload_0
    //   53: iconst_0
    //   54: invokeinterface 103 2 0
    //   59: invokevirtual 107	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   62: pop
    //   63: aload_0
    //   64: invokeinterface 110 1 0
    //   69: istore_1
    //   70: iload_1
    //   71: ifne -20 -> 51
    //   74: aload_0
    //   75: ifnull +9 -> 84
    //   78: aload_0
    //   79: invokeinterface 113 1 0
    //   84: aload_3
    //   85: areturn
    //   86: astore_0
    //   87: aconst_null
    //   88: astore_0
    //   89: aload_0
    //   90: ifnull -6 -> 84
    //   93: aload_0
    //   94: invokeinterface 113 1 0
    //   99: aload_3
    //   100: areturn
    //   101: astore_3
    //   102: aload_2
    //   103: astore_0
    //   104: aload_3
    //   105: astore_2
    //   106: aload_0
    //   107: ifnull +9 -> 116
    //   110: aload_0
    //   111: invokeinterface 113 1 0
    //   116: aload_2
    //   117: athrow
    //   118: astore_2
    //   119: goto -13 -> 106
    //   122: astore_2
    //   123: goto -34 -> 89
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	126	0	paramContext	Context
    //   69	2	1	bool	boolean
    //   1	116	2	localObject1	Object
    //   118	1	2	localObject2	Object
    //   122	1	2	localException	Exception
    //   9	91	3	localArrayList	ArrayList
    //   101	4	3	localObject3	Object
    //   13	7	4	localUri	android.net.Uri
    // Exception table:
    //   from	to	target	type
    //   15	38	86	java/lang/Exception
    //   15	38	101	finally
    //   42	51	118	finally
    //   51	70	118	finally
    //   42	51	122	java/lang/Exception
    //   51	70	122	java/lang/Exception
  }
  
  private static ArrayList<String> i(Context paramContext)
  {
    localArrayList = new ArrayList();
    paramContext = paramContext.getPackageManager();
    try
    {
      Iterator localIterator = paramContext.getInstalledApplications(128).iterator();
      while (localIterator.hasNext())
      {
        Object localObject = (ApplicationInfo)localIterator.next();
        if (paramContext.getLaunchIntentForPackage(((ApplicationInfo)localObject).packageName) != null)
        {
          localObject = (String)paramContext.getApplicationLabel((ApplicationInfo)localObject);
          if (localObject != null) {
            localArrayList.add(localObject);
          }
        }
      }
      return localArrayList;
    }
    catch (Exception paramContext) {}
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface a {}
}
