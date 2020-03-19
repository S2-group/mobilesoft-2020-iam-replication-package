package custom.online.adslib;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class e
  extends AsyncTask
{
  static JSONArray a;
  static boolean b;
  static boolean c = false;
  String d = "http://www.gammaplay.com/newestapp/appinfo";
  Context e;
  public d f;
  SharedPreferences g;
  public f h;
  a i;
  
  public e(Context paramContext)
  {
    this.e = paramContext;
    this.g = paramContext.getSharedPreferences("ADS_APPS", 0);
  }
  
  public static boolean c()
  {
    return c;
  }
  
  /* Error */
  protected String a(String... paramVarArgs)
  {
    // Byte code:
    //   0: new 52	java/net/URL
    //   3: dup
    //   4: aload_0
    //   5: getfield 34	custom/online/adslib/e:d	Ljava/lang/String;
    //   8: invokespecial 55	java/net/URL:<init>	(Ljava/lang/String;)V
    //   11: invokevirtual 59	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   14: checkcast 61	java/net/HttpURLConnection
    //   17: astore_1
    //   18: aload_1
    //   19: iconst_1
    //   20: invokevirtual 65	java/net/HttpURLConnection:setDoOutput	(Z)V
    //   23: new 67	java/io/BufferedInputStream
    //   26: dup
    //   27: aload_1
    //   28: invokevirtual 71	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   31: invokespecial 74	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
    //   34: astore_1
    //   35: new 76	java/io/BufferedReader
    //   38: dup
    //   39: new 78	java/io/InputStreamReader
    //   42: dup
    //   43: aload_1
    //   44: ldc 80
    //   46: invokespecial 83	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;Ljava/lang/String;)V
    //   49: invokespecial 86	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   52: astore_2
    //   53: new 88	java/lang/StringBuilder
    //   56: dup
    //   57: invokespecial 89	java/lang/StringBuilder:<init>	()V
    //   60: astore_3
    //   61: aload_2
    //   62: invokevirtual 93	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   65: astore 4
    //   67: aload 4
    //   69: ifnull +54 -> 123
    //   72: aload_3
    //   73: aload 4
    //   75: invokevirtual 97	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   78: pop
    //   79: goto -18 -> 61
    //   82: astore_1
    //   83: ldc 99
    //   85: new 88	java/lang/StringBuilder
    //   88: dup
    //   89: invokespecial 89	java/lang/StringBuilder:<init>	()V
    //   92: ldc 101
    //   94: invokevirtual 97	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   97: aload_1
    //   98: invokevirtual 104	java/lang/Exception:toString	()Ljava/lang/String;
    //   101: invokevirtual 97	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   104: invokevirtual 105	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   107: invokestatic 110	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   110: pop
    //   111: aconst_null
    //   112: areturn
    //   113: astore_1
    //   114: aload_1
    //   115: invokevirtual 113	java/lang/Exception:printStackTrace	()V
    //   118: aconst_null
    //   119: astore_1
    //   120: goto -85 -> 35
    //   123: aload_1
    //   124: invokevirtual 118	java/io/InputStream:close	()V
    //   127: aload_3
    //   128: invokevirtual 105	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   131: astore_1
    //   132: aload_1
    //   133: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	134	0	this	e
    //   0	134	1	paramVarArgs	String[]
    //   52	10	2	localBufferedReader	java.io.BufferedReader
    //   60	68	3	localStringBuilder	StringBuilder
    //   65	9	4	str	String
    // Exception table:
    //   from	to	target	type
    //   35	61	82	java/lang/Exception
    //   61	67	82	java/lang/Exception
    //   72	79	82	java/lang/Exception
    //   123	132	82	java/lang/Exception
    //   0	35	113	java/lang/Exception
  }
  
  public void a()
  {
    int k = 1;
    int j;
    if (a != null)
    {
      a(a);
      j = 0;
      if (!b) {
        break label133;
      }
    }
    label133:
    while (j == 0)
    {
      return;
      j = k;
      if (this.g.getString("jsonCacheData", null) == null) {
        break;
      }
      j = k;
      if (this.g.getLong("jsonCacheTime", -1L) <= 0L) {
        break;
      }
      j = k;
      if (System.currentTimeMillis() - this.g.getLong("jsonCacheTime", 0L) > 86400000L) {
        break;
      }
      j = k;
      try
      {
        if (c) {
          break;
        }
        a(new JSONArray(this.g.getString("jsonCacheData", null)));
        j = 0;
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
        j = k;
      }
      break;
    }
    execute(new String[0]);
  }
  
  public void a(JSONArray paramJSONArray)
  {
    JSONArray localJSONArray = null;
    int j = 0;
    JSONObject localJSONObject;
    int m;
    int k;
    try
    {
      if (paramJSONArray.length() <= 0) {
        return;
      }
      if (this.g.getString("jsonData", null) == null) {
        break label260;
      }
      localJSONArray = new JSONArray(this.g.getString("jsonData", null));
      j = 0;
    }
    catch (Exception paramJSONArray)
    {
      for (;;)
      {
        Object localObject;
        paramJSONArray.printStackTrace();
        return;
        if (m != 0) {
          break label321;
        }
        j += 1;
        continue;
        do
        {
          j += 1;
          if (j >= paramJSONArray.length()) {
            break;
          }
          localJSONObject = paramJSONArray.getJSONObject(j);
          bool = a(localJSONObject.getString("appid"));
        } while (bool);
      }
    }
    catch (Error paramJSONArray)
    {
      label249:
      label260:
      return;
    }
    if (j < paramJSONArray.length())
    {
      localJSONObject = paramJSONArray.getJSONObject(j);
      m = 1;
      k = 0;
      if (k < localJSONArray.length())
      {
        localObject = localJSONArray.getJSONObject(k);
        if ((0 == 0) && (localJSONObject.getString("appid").equals(((JSONObject)localObject).getString("appid")))) {
          break label311;
        }
        if (!a(localJSONObject.getString("appid"))) {
          break label314;
        }
        break label311;
      }
      if (localJSONArray.length() == 0)
      {
        if (a(localJSONObject.getString("appid"))) {
          break label249;
        }
        break label321;
      }
    }
    label311:
    label314:
    label321:
    for (;;)
    {
      if (localJSONObject != null)
      {
        a = paramJSONArray;
        paramJSONArray = localJSONObject.getString("appid");
        localObject = localJSONObject.getString("imgurl");
        this.i = a.a(localJSONObject.getString("name"), paramJSONArray, this.f);
        this.i.a((FragmentActivity)this.e, (String)localObject);
        if (this.h != null)
        {
          this.h.a(localJSONArray, this);
          return;
          boolean bool;
          localJSONObject = null;
          continue;
          localJSONObject = null;
          break label321;
        }
      }
      return;
      m = 0;
      k += 1;
      break;
    }
  }
  
  public boolean a(String paramString)
  {
    Iterator localIterator = this.e.getPackageManager().getInstalledPackages(0).iterator();
    while (localIterator.hasNext()) {
      if (((PackageInfo)localIterator.next()).packageName.equals(paramString)) {
        return true;
      }
    }
    return false;
  }
  
  public void b()
  {
    try
    {
      c = true;
      if ((a != null) && (a.length() > 0) && (this.i != null))
      {
        JSONObject localJSONObject = a.getJSONObject(0);
        Object localObject = localJSONObject.getString("appid");
        localJSONObject.getString("imgurl");
        localJSONObject.getString("name");
        if (this.f != null) {
          this.f.c();
        }
        this.i.show(((FragmentActivity)this.e).getSupportFragmentManager(), "addialog");
        new JSONObject().put("appid", localObject);
        localObject = new JSONArray();
        ((JSONArray)localObject).put(localJSONObject);
        this.g.edit().putString("jsonData", ((JSONArray)localObject).toString()).commit();
        a = null;
      }
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      return;
    }
    catch (Error localError) {}catch (IllegalStateException localIllegalStateException) {}
  }
  
  protected void b(String paramString)
  {
    b = false;
    if (paramString == null) {
      return;
    }
    try
    {
      paramString = new JSONArray(paramString);
      a(paramString);
      this.g.edit().putString("jsonCacheData", paramString.toString()).putLong("jsonCacheTime", System.currentTimeMillis()).commit();
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
      return;
    }
    catch (Error paramString) {}
  }
  
  protected void onPreExecute()
  {
    super.onPreExecute();
    b = true;
  }
}
