package de.cellular.lib.a.d;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class b
  extends AsyncTask<Void, Void, g>
{
  private Context a;
  private String b;
  private String c;
  private String d;
  private i<Boolean> e;
  private boolean f;
  private boolean g;
  private String h = "market://details?id=";
  
  public b(Context paramContext, String paramString1, String paramString2, String paramString3, i<Boolean> paramI)
  {
    this(paramContext, paramString1, paramString2, paramString3, paramI, false);
  }
  
  public b(Context paramContext, String paramString1, String paramString2, String paramString3, i<Boolean> paramI, boolean paramBoolean)
  {
    this.a = paramContext;
    this.b = paramString1;
    this.c = paramString2;
    this.d = paramString3;
    this.e = paramI;
    this.f = paramBoolean;
    this.g = false;
  }
  
  private g a(JSONObject paramJSONObject, SharedPreferences paramSharedPreferences)
  {
    g localG = new g();
    paramSharedPreferences = paramSharedPreferences.edit();
    for (;;)
    {
      int i;
      String str;
      try
      {
        a(null, paramJSONObject.getJSONObject("app_specific_definitions"), paramSharedPreferences);
        try
        {
          g.a(localG, h.valueOf(paramJSONObject.getString("update_type").toUpperCase(Locale.US)));
          if (localG.a() == h.c) {
            break label273;
          }
          g.a(localG, paramJSONObject.getInt("app_version_available"));
          g.a(localG, paramJSONObject.getString("update_url"));
          if ((!d(localG)) && (paramJSONObject.has("replace_strings")) && (!paramJSONObject.isNull("replace_strings")))
          {
            paramJSONObject = paramJSONObject.getJSONObject("replace_strings");
            if (paramJSONObject.names() == null) {
              break label265;
            }
            paramJSONObject = a(paramJSONObject);
            g.b(localG, paramJSONObject.getString("title"));
            g.c(localG, paramJSONObject.getString("message"));
            if (paramJSONObject.has("alternative_message")) {
              g.d(localG, paramJSONObject.getString("alternative_message"));
            }
            paramJSONObject = paramJSONObject.getJSONArray("buttons");
            i = 0;
            if (i >= paramJSONObject.length()) {
              break label273;
            }
            JSONObject localJSONObject = paramJSONObject.getJSONObject(i);
            str = localJSONObject.getString("label");
            if (!localJSONObject.has("update_button")) {
              break label255;
            }
            g.e(localG, str);
          }
        }
        catch (IllegalArgumentException localIllegalArgumentException)
        {
          de.cellular.lib.a.b.a.b("Invalid update_type", localIllegalArgumentException);
          continue;
        }
        paramJSONObject = paramJSONObject.getJSONObject("update_strings");
      }
      finally
      {
        paramSharedPreferences.commit();
      }
      continue;
      label255:
      g.f(localG, str);
      break label302;
      label265:
      g.a(localG, h.c);
      label273:
      if (this.g) {
        paramSharedPreferences.putLong("last_config_update", System.currentTimeMillis());
      }
      paramSharedPreferences.commit();
      return localG;
      label302:
      i += 1;
    }
  }
  
  private JSONObject a(JSONArray paramJSONArray)
  {
    Locale localLocale = Locale.getDefault();
    int i = 0;
    JSONObject localJSONObject;
    String str1;
    String str2;
    while (i < paramJSONArray.length())
    {
      localJSONObject = paramJSONArray.optJSONObject(i);
      str1 = localJSONObject.optString("languageCode");
      str2 = localJSONObject.optString("regionCode");
      if ((localJSONObject != null) && (!str2.isEmpty()) && (localLocale.getLanguage().equalsIgnoreCase(str1)) && (localLocale.getCountry().equalsIgnoreCase(str2))) {
        return localJSONObject;
      }
      i += 1;
    }
    i = 0;
    while (i < paramJSONArray.length())
    {
      localJSONObject = paramJSONArray.optJSONObject(i);
      str1 = localJSONObject.optString("languageCode");
      str2 = localJSONObject.optString("regionCode");
      if ((localJSONObject != null) && (str2.isEmpty()) && (localLocale.getLanguage().equalsIgnoreCase(str1))) {
        return localJSONObject;
      }
      i += 1;
    }
    return paramJSONArray.optJSONObject(0);
  }
  
  private JSONObject a(JSONObject paramJSONObject)
  {
    Locale localLocale = Locale.getDefault();
    if (paramJSONObject.has(localLocale.toString())) {
      return paramJSONObject.getJSONObject(localLocale.toString());
    }
    if (paramJSONObject.has(localLocale.getLanguage())) {
      return paramJSONObject.getJSONObject(localLocale.getLanguage());
    }
    return paramJSONObject.getJSONObject(paramJSONObject.names().getString(0));
  }
  
  private void a(String paramString, Object paramObject, SharedPreferences.Editor paramEditor)
  {
    if ((paramObject == null) || (paramObject.equals(JSONObject.NULL))) {
      paramEditor.remove(paramString);
    }
    for (;;)
    {
      if (de.cellular.lib.a.b.a.b()) {
        de.cellular.lib.a.b.a.b(paramString + ": " + paramObject);
      }
      JSONArray localJSONArray;
      do
      {
        return;
        if ((paramObject instanceof Boolean))
        {
          paramEditor.putBoolean(paramString, ((Boolean)paramObject).booleanValue());
          break;
        }
        if ((paramObject instanceof Number))
        {
          paramEditor.putInt(paramString, ((Number)paramObject).intValue());
          break;
        }
        if (!(paramObject instanceof JSONObject)) {
          break label216;
        }
        paramObject = (JSONObject)paramObject;
        localJSONArray = paramObject.names();
      } while (localJSONArray == null);
      int i = 0;
      label132:
      String str;
      if (i < localJSONArray.length())
      {
        str = localJSONArray.getString(i);
        if (paramString != null) {
          break label177;
        }
        a(str, paramObject.get(str), paramEditor);
      }
      for (;;)
      {
        i += 1;
        break label132;
        break;
        label177:
        a(paramString + "_" + str, paramObject.get(str), paramEditor);
      }
      label216:
      paramEditor.putString(paramString, paramObject.toString());
    }
  }
  
  private g b(JSONObject paramJSONObject, SharedPreferences paramSharedPreferences)
  {
    g localG = new g();
    SharedPreferences.Editor localEditor = paramSharedPreferences.edit();
    for (;;)
    {
      int i;
      String str;
      try
      {
        if (!paramJSONObject.has("appUpdate")) {
          break label337;
        }
        paramSharedPreferences = (JSONObject)paramJSONObject.remove("appUpdate");
        a(null, paramJSONObject, localEditor);
        if (paramSharedPreferences == null) {
          break label306;
        }
        try
        {
          g.a(localG, h.valueOf(paramSharedPreferences.getString("type").toUpperCase(Locale.US)));
          if (localG.a() == h.c) {
            break label306;
          }
          g.a(localG, paramSharedPreferences.getInt("appVersion"));
          g.a(localG, paramSharedPreferences.getString("url"));
          if ((!d(localG)) && (paramSharedPreferences.has("replaceStrings")) && (!paramSharedPreferences.isNull("replaceStrings")))
          {
            paramJSONObject = paramSharedPreferences.optJSONArray("replaceStrings");
            if ((paramJSONObject == null) || (paramJSONObject.length() <= 0)) {
              break label298;
            }
            paramJSONObject = a(paramJSONObject);
            g.b(localG, paramJSONObject.getString("title"));
            g.c(localG, paramJSONObject.getString("message"));
            if (paramJSONObject.has("alternativeMessage")) {
              g.d(localG, paramJSONObject.getString("alternativeMessage"));
            }
            paramJSONObject = paramJSONObject.getJSONArray("buttons");
            i = 0;
            if (i >= paramJSONObject.length()) {
              break label306;
            }
            paramSharedPreferences = paramJSONObject.getJSONObject(i);
            str = paramSharedPreferences.getString("label");
            if (!paramSharedPreferences.has("updateButton")) {
              break label288;
            }
            g.e(localG, str);
          }
        }
        catch (IllegalArgumentException paramJSONObject)
        {
          de.cellular.lib.a.b.a.b("Invalid type", paramJSONObject);
          continue;
        }
        paramJSONObject = paramSharedPreferences.optJSONArray("updateStrings");
      }
      finally
      {
        localEditor.commit();
      }
      continue;
      label288:
      g.f(localG, str);
      break label342;
      label298:
      g.a(localG, h.c);
      label306:
      if (this.g) {
        localEditor.putLong("last_config_update", System.currentTimeMillis());
      }
      localEditor.commit();
      return localG;
      label337:
      paramSharedPreferences = null;
      continue;
      label342:
      i += 1;
    }
  }
  
  private String c(String paramString)
  {
    return paramString.replace("http://", "").replace("https://", "").replace("play.google.com/store/apps/details?id=", "").replace("market://details?id=", "").replace("amzn://apps/android?p=", "");
  }
  
  private boolean d(g paramG)
  {
    String str = this.a.getPackageName();
    if ((paramG != null) && (paramG.c() != null)) {
      return paramG.c().contains(str);
    }
    return false;
  }
  
  public g a(String paramString, SharedPreferences paramSharedPreferences)
  {
    paramString = new JSONObject(paramString);
    if (paramString.has("app_specific_definitions")) {
      return a(paramString, paramSharedPreferences);
    }
    return b(paramString, paramSharedPreferences);
  }
  
  protected g a(Void... paramVarArgs)
  {
    int j = 0;
    if (de.cellular.lib.a.b.a.b()) {
      de.cellular.lib.a.b.a.b("Downloading config feed " + this.b);
    }
    if (a()) {
      this.h = "amzn://apps/android?p=";
    }
    Object localObject = this.a.getSharedPreferences(this.d, 0);
    if ((!((SharedPreferences)localObject).contains("last_config_update")) || (this.f)) {}
    for (int i = 1;; i = 0)
    {
      try
      {
        paramVarArgs = a(b(this.b), (SharedPreferences)localObject);
        i = j;
      }
      catch (IOException paramVarArgs)
      {
        for (;;)
        {
          de.cellular.lib.a.b.a.c("Error downloading config feed", paramVarArgs);
          paramVarArgs = null;
        }
      }
      catch (JSONException paramVarArgs)
      {
        for (;;)
        {
          de.cellular.lib.a.b.a.c("Error parsing config feed", paramVarArgs);
          paramVarArgs = null;
        }
      }
      if ((i == 0) || (this.c == null)) {
        break label253;
      }
      if (de.cellular.lib.a.b.a.c()) {
        de.cellular.lib.a.b.a.c("Using fallback configuration " + this.c);
      }
      try
      {
        localObject = a(a(this.c), (SharedPreferences)localObject);
        paramVarArgs = (Void[])localObject;
      }
      catch (IOException localIOException)
      {
        for (;;)
        {
          de.cellular.lib.a.b.a.c("Error reading asset feed", localIOException);
        }
      }
      catch (JSONException localJSONException)
      {
        de.cellular.lib.a.b.a.c("Error parsing config feed", localJSONException);
      }
      if ((this.f) && ((this.e instanceof a)))
      {
        de.cellular.lib.a.b.a.c("Running app upgrade");
        ((a)this.e).a();
      }
      return paramVarArgs;
    }
  }
  
  /* Error */
  public String a(String paramString)
  {
    // Byte code:
    //   0: new 255	java/lang/StringBuilder
    //   3: dup
    //   4: invokespecial 256	java/lang/StringBuilder:<init>	()V
    //   7: astore_3
    //   8: new 400	java/io/BufferedReader
    //   11: dup
    //   12: new 402	java/io/InputStreamReader
    //   15: dup
    //   16: aload_0
    //   17: getfield 35	de/cellular/lib/a/d/b:a	Landroid/content/Context;
    //   20: invokevirtual 406	android/content/Context:getAssets	()Landroid/content/res/AssetManager;
    //   23: aload_1
    //   24: invokevirtual 412	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   27: ldc_w 414
    //   30: invokespecial 417	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;Ljava/lang/String;)V
    //   33: invokespecial 420	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   36: astore_2
    //   37: aload_2
    //   38: invokevirtual 423	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   41: astore_1
    //   42: aload_1
    //   43: ifnull +23 -> 66
    //   46: aload_3
    //   47: aload_1
    //   48: invokevirtual 260	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   51: pop
    //   52: goto -15 -> 37
    //   55: astore_1
    //   56: aload_2
    //   57: ifnull +7 -> 64
    //   60: aload_2
    //   61: invokevirtual 426	java/io/BufferedReader:close	()V
    //   64: aload_1
    //   65: athrow
    //   66: aload_2
    //   67: ifnull +7 -> 74
    //   70: aload_2
    //   71: invokevirtual 426	java/io/BufferedReader:close	()V
    //   74: aload_3
    //   75: invokevirtual 266	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   78: areturn
    //   79: astore_1
    //   80: goto -6 -> 74
    //   83: astore_2
    //   84: goto -20 -> 64
    //   87: astore_1
    //   88: aconst_null
    //   89: astore_2
    //   90: goto -34 -> 56
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	93	0	this	b
    //   0	93	1	paramString	String
    //   36	35	2	localBufferedReader	java.io.BufferedReader
    //   83	1	2	localIOException	IOException
    //   89	1	2	localObject	Object
    //   7	68	3	localStringBuilder	StringBuilder
    // Exception table:
    //   from	to	target	type
    //   37	42	55	finally
    //   46	52	55	finally
    //   70	74	79	java/io/IOException
    //   60	64	83	java/io/IOException
    //   8	37	87	finally
  }
  
  protected void a(g paramG)
  {
    try
    {
      int i = this.a.getPackageManager().getPackageInfo(this.a.getPackageName(), 0).versionCode;
      if ((paramG != null) && (paramG.a() != h.c) && (i < paramG.b()))
      {
        if (d(paramG))
        {
          b(paramG);
          return;
        }
        c(paramG);
        return;
      }
    }
    catch (PackageManager.NameNotFoundException paramG)
    {
      de.cellular.lib.a.b.a.c("Error getting versionCode", paramG);
      this.e.a(Boolean.valueOf(true));
      return;
    }
    this.e.a(Boolean.valueOf(true));
  }
  
  protected boolean a()
  {
    return de.cellular.lib.a.a.a.a(this.a);
  }
  
  /* Error */
  public String b(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: new 255	java/lang/StringBuilder
    //   5: dup
    //   6: invokespecial 256	java/lang/StringBuilder:<init>	()V
    //   9: astore 4
    //   11: new 468	java/net/URL
    //   14: dup
    //   15: aload_1
    //   16: invokespecial 469	java/net/URL:<init>	(Ljava/lang/String;)V
    //   19: invokevirtual 473	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   22: checkcast 475	java/net/HttpURLConnection
    //   25: astore_1
    //   26: aload_1
    //   27: sipush 20000
    //   30: invokevirtual 479	java/net/HttpURLConnection:setConnectTimeout	(I)V
    //   33: aload_1
    //   34: sipush 20000
    //   37: invokevirtual 482	java/net/HttpURLConnection:setReadTimeout	(I)V
    //   40: new 400	java/io/BufferedReader
    //   43: dup
    //   44: new 402	java/io/InputStreamReader
    //   47: dup
    //   48: aload_1
    //   49: invokevirtual 486	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   52: ldc_w 414
    //   55: invokespecial 417	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;Ljava/lang/String;)V
    //   58: invokespecial 420	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   61: astore_2
    //   62: aload_2
    //   63: invokevirtual 423	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   66: astore_3
    //   67: aload_3
    //   68: ifnull +38 -> 106
    //   71: aload 4
    //   73: aload_3
    //   74: invokevirtual 260	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   77: pop
    //   78: goto -16 -> 62
    //   81: astore 4
    //   83: aload_1
    //   84: astore_3
    //   85: aload 4
    //   87: astore_1
    //   88: aload_2
    //   89: ifnull +7 -> 96
    //   92: aload_2
    //   93: invokevirtual 426	java/io/BufferedReader:close	()V
    //   96: aload_3
    //   97: ifnull +7 -> 104
    //   100: aload_3
    //   101: invokevirtual 489	java/net/HttpURLConnection:disconnect	()V
    //   104: aload_1
    //   105: athrow
    //   106: aload_0
    //   107: iconst_1
    //   108: putfield 47	de/cellular/lib/a/d/b:g	Z
    //   111: aload_2
    //   112: ifnull +7 -> 119
    //   115: aload_2
    //   116: invokevirtual 426	java/io/BufferedReader:close	()V
    //   119: aload_1
    //   120: ifnull +7 -> 127
    //   123: aload_1
    //   124: invokevirtual 489	java/net/HttpURLConnection:disconnect	()V
    //   127: aload 4
    //   129: invokevirtual 266	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   132: areturn
    //   133: astore_2
    //   134: goto -15 -> 119
    //   137: astore_2
    //   138: goto -42 -> 96
    //   141: astore_1
    //   142: aconst_null
    //   143: astore_2
    //   144: goto -56 -> 88
    //   147: astore 4
    //   149: aconst_null
    //   150: astore_2
    //   151: aload_1
    //   152: astore_3
    //   153: aload 4
    //   155: astore_1
    //   156: goto -68 -> 88
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	159	0	this	b
    //   0	159	1	paramString	String
    //   61	55	2	localBufferedReader	java.io.BufferedReader
    //   133	1	2	localIOException1	IOException
    //   137	1	2	localIOException2	IOException
    //   143	8	2	localObject1	Object
    //   1	152	3	str	String
    //   9	63	4	localStringBuilder	StringBuilder
    //   81	47	4	localObject2	Object
    //   147	7	4	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   62	67	81	finally
    //   71	78	81	finally
    //   106	111	81	finally
    //   115	119	133	java/io/IOException
    //   92	96	137	java/io/IOException
    //   11	26	141	finally
    //   26	62	147	finally
  }
  
  protected void b(g paramG)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this.a);
    localBuilder.setCancelable(false);
    localBuilder.setTitle(paramG.d());
    localBuilder.setMessage(paramG.e());
    localBuilder.setPositiveButton(paramG.g(), new c(this, paramG));
    if (paramG.a() == h.b) {
      localBuilder.setNegativeButton(paramG.h(), new d(this));
    }
    if (paramG.a() != h.c)
    {
      localBuilder.show();
      return;
    }
    this.e.a(Boolean.valueOf(true));
  }
  
  protected void c(g paramG)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this.a);
    localBuilder.setCancelable(false);
    localBuilder.setTitle(paramG.d());
    PackageManager localPackageManager = this.a.getPackageManager();
    String str = c(paramG.c());
    Object localObject = localPackageManager.getInstalledPackages(128).iterator();
    for (;;)
    {
      if (((Iterator)localObject).hasNext()) {
        if (((PackageInfo)((Iterator)localObject).next()).packageName.equalsIgnoreCase(str)) {
          if (paramG.h() != null)
          {
            localObject = paramG.h();
            localBuilder.setNegativeButton((CharSequence)localObject, new e(this));
          }
        }
      }
    }
    for (int i = 1;; i = 0)
    {
      localBuilder.setPositiveButton(paramG.g(), new f(this, localPackageManager, str, paramG));
      if (i != 0) {
        localBuilder.setMessage(paramG.f());
      }
      for (;;)
      {
        localBuilder.show();
        return;
        localObject = "Deinstallieren";
        break;
        localBuilder.setMessage(paramG.e());
      }
    }
  }
}
