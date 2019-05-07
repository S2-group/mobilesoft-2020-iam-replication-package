package indian.plusone.phone.launcher.themeui.request;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.res.AssetManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.os.Build.VERSION;
import indian.plusone.phone.launcher.themeui.adapter.BaseAdapter;
import indian.plusone.phone.launcher.themeui.adapter.IBaseThemeLoader;
import indian.plusone.phone.launcher.themeui.model.IModel;
import indian.plusone.phone.launcher.themeui.model.Model;
import indian.plusone.phone.launcher.themeui.model.ThemeModel;
import indian.plusone.phone.launcher.thstore.ThemeUtilities;
import java.io.IOException;
import java.io.InputStream;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.ResponseBody;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;

public class LoadTask
{
  public LoadTask() {}
  
  public static int getApiVersionByPackage(Context paramContext, String paramString)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramString, 0).versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return 1;
  }
  
  public static JSONObject getCachedTheme(Context paramContext)
  {
    try
    {
      paramContext = ThemeUtilities.get().readResponse(paramContext, "theme_3");
      return paramContext;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static List<String> getInstalledPackages(Context paramContext)
  {
    localArrayList = new ArrayList();
    try
    {
      paramContext = paramContext.getPackageManager();
      Object localObject1 = new Intent("indian.plusone.phone.launcher.intent.action.THEME_LAUNCHER_META");
      ((Intent)localObject1).addCategory("android.intent.category.DEFAULT");
      localObject1 = paramContext.queryIntentActivities((Intent)localObject1, 64);
      if (!((List)localObject1).isEmpty())
      {
        localObject1 = ((List)localObject1).iterator();
        for (;;)
        {
          if (!((Iterator)localObject1).hasNext()) {
            return localArrayList;
          }
          Object localObject2 = (ResolveInfo)((Iterator)localObject1).next();
          try
          {
            localObject2 = ((ResolveInfo)localObject2).activityInfo.packageName;
            paramContext.getPackageInfo((String)localObject2, 0);
            localArrayList.add(localObject2);
          }
          catch (Exception localException)
          {
            localException.printStackTrace();
          }
        }
      }
      return localArrayList;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static List<ThemeModel> getInstalledTheme(Context paramContext)
  {
    localArrayList = new ArrayList();
    try
    {
      paramContext = paramContext.getPackageManager();
      Object localObject1 = new Intent("indian.plusone.phone.launcher.intent.action.THEME_LAUNCHER_META");
      ((Intent)localObject1).addCategory("android.intent.category.DEFAULT");
      localObject1 = paramContext.queryIntentActivities((Intent)localObject1, 64);
      if (!((List)localObject1).isEmpty())
      {
        localObject1 = ((List)localObject1).iterator();
        for (;;)
        {
          if (!((Iterator)localObject1).hasNext()) {
            return localArrayList;
          }
          Object localObject2 = (ResolveInfo)((Iterator)localObject1).next();
          try
          {
            String str = ((ResolveInfo)localObject2).activityInfo.packageName;
            localObject2 = ((ResolveInfo)localObject2).loadLabel(paramContext).toString();
            PackageInfo localPackageInfo = paramContext.getPackageInfo(str, 0);
            int i = localPackageInfo.versionCode;
            long l = localPackageInfo.lastUpdateTime;
            localArrayList.add(new ThemeModel(paramContext.getResourcesForApplication(str), i, l, str, (String)localObject2));
          }
          catch (Exception localException)
          {
            localException.printStackTrace();
          }
        }
      }
      return localArrayList;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static List<String> getLwps(Context paramContext)
  {
    localArrayList = new ArrayList();
    try
    {
      paramContext = paramContext.getPackageManager();
      Object localObject = new Intent("indian.plusone.phone.launcher.intent.action.THEME_LWP_META");
      ((Intent)localObject).addCategory("android.intent.category.DEFAULT");
      paramContext = paramContext.queryIntentActivities((Intent)localObject, 64);
      if (!paramContext.isEmpty())
      {
        paramContext = paramContext.iterator();
        for (;;)
        {
          if (!paramContext.hasNext()) {
            return localArrayList;
          }
          localObject = (ResolveInfo)paramContext.next();
          try
          {
            localArrayList.add(((ResolveInfo)localObject).activityInfo.packageName);
          }
          catch (Exception localException)
          {
            localException.printStackTrace();
          }
        }
      }
      return localArrayList;
    }
    catch (Exception paramContext) {}
  }
  
  public static final Comparator<IModel> getRelatedComparator(IModel paramIModel)
  {
    new Comparator()
    {
      public final int compare(IModel paramAnonymousIModel1, IModel paramAnonymousIModel2)
      {
        int j = 0;
        int i = 0;
        Iterator localIterator = LoadTask.this.getTags().iterator();
        if (!localIterator.hasNext())
        {
          i -= j;
          if (i != 0) {
            return i;
          }
          if (paramAnonymousIModel1.getTime() >= paramAnonymousIModel2.getTime()) {
            break label191;
          }
          j = 1;
          label59:
          i = j;
          if (j == 0) {
            if (paramAnonymousIModel1.getDownloads() <= paramAnonymousIModel2.getDownloads()) {
              break label197;
            }
          }
        }
        label191:
        label197:
        for (j = 1;; j = -1)
        {
          i = j;
          if (j == 0) {
            i = this.val$collator.compare(paramAnonymousIModel1.getName().trim(), paramAnonymousIModel2.getName().trim());
          }
          return i;
          String str = (String)localIterator.next();
          int k = j;
          if (paramAnonymousIModel1.getTags().contains(str)) {
            k = j + 1;
          }
          j = k;
          if (!paramAnonymousIModel2.getTags().contains(str)) {
            break;
          }
          i += 1;
          j = k;
          break;
          j = -1;
          break label59;
        }
        return i;
      }
    };
  }
  
  public static int getVersionByPackage(Context paramContext, String paramString)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramString, 0).versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return 1;
  }
  
  @SuppressLint({"NewApi"})
  public static boolean isNetworkAvailable(Context paramContext)
  {
    paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
    if (paramContext == null) {}
    int i;
    for (;;)
    {
      return false;
      if (Build.VERSION.SDK_INT < 21) {
        break;
      }
      localObject = paramContext.getAllNetworks();
      if (localObject != null)
      {
        i = 0;
        while (i < localObject.length)
        {
          if ((localObject[i] != null) && (paramContext.getNetworkInfo(localObject[i]).isConnected())) {
            return true;
          }
          i += 1;
        }
      }
    }
    Object localObject = paramContext.getAllNetworkInfo();
    if (localObject != null) {
      i = 0;
    }
    for (;;)
    {
      if (i >= localObject.length)
      {
        paramContext = paramContext.getActiveNetworkInfo();
        if ((paramContext == null) || (!paramContext.isConnected())) {
          break;
        }
        return true;
      }
      if (localObject[i].getState() == NetworkInfo.State.CONNECTED) {
        return true;
      }
      i += 1;
    }
  }
  
  public static void load(Context paramContext, final int paramInt, final IBaseThemeLoader<IModel> paramIBaseThemeLoader)
  {
    Object localObject = new OkHttpClient.Builder().cache(new Cache(paramContext.getCacheDir(), 10485760L)).addInterceptor(new Interceptor()
    {
      public okhttp3.Response intercept(Interceptor.Chain paramAnonymousChain)
        throws IOException
      {
        Request localRequest = paramAnonymousChain.request();
        if (LoadTask.isNetworkAvailable(LoadTask.this)) {}
        for (localRequest = localRequest.newBuilder().header("Cache-Control", "public, max-age=600").build();; localRequest = localRequest.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=604800").build()) {
          return paramAnonymousChain.proceed(localRequest);
        }
      }
    }).build();
    localObject = (ITask)new Retrofit.Builder().baseUrl("http://13.232.147.172").client((OkHttpClient)localObject).build().create(ITask.class);
    if (paramInt == 3) {
      localObject = ((ITask)localObject).loadThemes();
    }
    for (;;)
    {
      ((Call)localObject).enqueue(new Callback()
      {
        public void onFailure(Call<ResponseBody> paramAnonymousCall, Throwable paramAnonymousThrowable)
        {
          try
          {
            paramAnonymousCall = ThemeUtilities.get().readResponse(LoadTask.this, "theme_" + paramInt);
            if (paramAnonymousCall != null)
            {
              LoadTask.onResultThemes(LoadTask.this, paramAnonymousCall, paramInt, paramIBaseThemeLoader);
              return;
            }
            paramIBaseThemeLoader.setItems(new ArrayList());
            return;
          }
          catch (Exception paramAnonymousCall)
          {
            paramAnonymousCall.printStackTrace();
            paramIBaseThemeLoader.setItems(new ArrayList());
          }
        }
        
        public void onResponse(Call<ResponseBody> paramAnonymousCall, retrofit2.Response<ResponseBody> paramAnonymousResponse)
        {
          try
          {
            if (paramAnonymousResponse.isSuccessful()) {
              paramAnonymousCall = new JSONObject(((ResponseBody)paramAnonymousResponse.body()).string());
            }
            paramAnonymousCall.printStackTrace();
          }
          catch (Exception paramAnonymousCall)
          {
            try
            {
              ThemeUtilities.get().writeResponse(LoadTask.this, "theme_" + paramInt, paramAnonymousCall.toString(2));
              while (paramAnonymousCall != null)
              {
                LoadTask.onResultThemes(LoadTask.this, paramAnonymousCall, paramInt, paramIBaseThemeLoader);
                return;
                paramAnonymousCall = ThemeUtilities.get().readResponse(LoadTask.this, "theme_" + paramInt);
              }
              paramIBaseThemeLoader.setItems(new ArrayList());
              return;
            }
            catch (Exception paramAnonymousCall)
            {
              for (;;) {}
            }
            paramAnonymousCall = paramAnonymousCall;
          }
          paramIBaseThemeLoader.setItems(new ArrayList());
        }
      });
      return;
      if (paramInt == 2) {
        localObject = ((ITask)localObject).loadLocker();
      } else {
        localObject = ((ITask)localObject).loadLwp();
      }
    }
  }
  
  public static String loadJSONFromAsset(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getAssets().open("theme/" + paramString);
      paramString = new byte[paramContext.available()];
      paramContext.read(paramString);
      paramContext.close();
      paramContext = new String(paramString, "UTF-8");
      return paramContext;
    }
    catch (IOException paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  /* Error */
  public static void loadMine(Context paramContext, IBaseThemeLoader<IModel> paramIBaseThemeLoader)
  {
    // Byte code:
    //   0: new 65	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 66	java/util/ArrayList:<init>	()V
    //   7: astore 6
    //   9: aload_0
    //   10: invokevirtual 30	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   13: astore 7
    //   15: new 68	android/content/Intent
    //   18: dup
    //   19: ldc 70
    //   21: invokespecial 73	android/content/Intent:<init>	(Ljava/lang/String;)V
    //   24: astore 8
    //   26: aload 8
    //   28: ldc 75
    //   30: invokevirtual 79	android/content/Intent:addCategory	(Ljava/lang/String;)Landroid/content/Intent;
    //   33: pop
    //   34: aload_0
    //   35: invokevirtual 335	android/content/Context:getPackageName	()Ljava/lang/String;
    //   38: astore 9
    //   40: aload 7
    //   42: aload 9
    //   44: iconst_0
    //   45: invokevirtual 36	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   48: astore 10
    //   50: aload 10
    //   52: getfield 42	android/content/pm/PackageInfo:versionCode	I
    //   55: istore_2
    //   56: aload 10
    //   58: getfield 135	android/content/pm/PackageInfo:lastUpdateTime	J
    //   61: lstore_3
    //   62: new 137	indian/plusone/phone/launcher/themeui/model/ThemeModel
    //   65: dup
    //   66: aload_0
    //   67: invokevirtual 339	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   70: iload_2
    //   71: lload_3
    //   72: aload 9
    //   74: ldc_w 341
    //   77: invokespecial 144	indian/plusone/phone/launcher/themeui/model/ThemeModel:<init>	(Landroid/content/res/Resources;IJLjava/lang/String;Ljava/lang/String;)V
    //   80: astore_0
    //   81: aload_0
    //   82: lconst_0
    //   83: invokeinterface 347 3 0
    //   88: aload 6
    //   90: aload_0
    //   91: invokeinterface 118 2 0
    //   96: pop
    //   97: aload 7
    //   99: aload 8
    //   101: bipush 64
    //   103: invokevirtual 83	android/content/pm/PackageManager:queryIntentActivities	(Landroid/content/Intent;I)Ljava/util/List;
    //   106: astore_0
    //   107: aload_0
    //   108: invokeinterface 89 1 0
    //   113: ifne +23 -> 136
    //   116: aload_0
    //   117: invokeinterface 93 1 0
    //   122: astore_0
    //   123: aload_0
    //   124: invokeinterface 98 1 0
    //   129: istore 5
    //   131: iload 5
    //   133: ifne +12 -> 145
    //   136: aload_1
    //   137: aload 6
    //   139: invokeinterface 353 2 0
    //   144: return
    //   145: aload_0
    //   146: invokeinterface 102 1 0
    //   151: checkcast 104	android/content/pm/ResolveInfo
    //   154: astore 9
    //   156: aload 9
    //   158: getfield 108	android/content/pm/ResolveInfo:activityInfo	Landroid/content/pm/ActivityInfo;
    //   161: getfield 114	android/content/pm/ActivityInfo:packageName	Ljava/lang/String;
    //   164: astore 8
    //   166: aload 9
    //   168: aload 7
    //   170: invokevirtual 125	android/content/pm/ResolveInfo:loadLabel	(Landroid/content/pm/PackageManager;)Ljava/lang/CharSequence;
    //   173: invokeinterface 131 1 0
    //   178: astore 9
    //   180: aload 7
    //   182: aload 8
    //   184: iconst_0
    //   185: invokevirtual 36	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   188: astore 10
    //   190: aload 10
    //   192: getfield 42	android/content/pm/PackageInfo:versionCode	I
    //   195: istore_2
    //   196: aload 10
    //   198: getfield 135	android/content/pm/PackageInfo:lastUpdateTime	J
    //   201: lstore_3
    //   202: aload 6
    //   204: new 137	indian/plusone/phone/launcher/themeui/model/ThemeModel
    //   207: dup
    //   208: aload 7
    //   210: aload 8
    //   212: invokevirtual 141	android/content/pm/PackageManager:getResourcesForApplication	(Ljava/lang/String;)Landroid/content/res/Resources;
    //   215: iload_2
    //   216: lload_3
    //   217: aload 8
    //   219: aload 9
    //   221: invokespecial 144	indian/plusone/phone/launcher/themeui/model/ThemeModel:<init>	(Landroid/content/res/Resources;IJLjava/lang/String;Ljava/lang/String;)V
    //   224: invokeinterface 118 2 0
    //   229: pop
    //   230: goto -107 -> 123
    //   233: astore 8
    //   235: aload 8
    //   237: invokevirtual 61	java/lang/Exception:printStackTrace	()V
    //   240: goto -117 -> 123
    //   243: astore_0
    //   244: aload_0
    //   245: invokevirtual 61	java/lang/Exception:printStackTrace	()V
    //   248: goto -112 -> 136
    //   251: astore_0
    //   252: goto -155 -> 97
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	255	0	paramContext	Context
    //   0	255	1	paramIBaseThemeLoader	IBaseThemeLoader<IModel>
    //   55	161	2	i	int
    //   61	156	3	l	long
    //   129	3	5	bool	boolean
    //   7	196	6	localArrayList	ArrayList
    //   13	196	7	localPackageManager	PackageManager
    //   24	194	8	localObject1	Object
    //   233	3	8	localException	Exception
    //   38	182	9	localObject2	Object
    //   48	149	10	localPackageInfo	PackageInfo
    // Exception table:
    //   from	to	target	type
    //   156	230	233	java/lang/Exception
    //   9	34	243	java/lang/Exception
    //   97	123	243	java/lang/Exception
    //   123	131	243	java/lang/Exception
    //   145	156	243	java/lang/Exception
    //   235	240	243	java/lang/Exception
    //   34	97	251	java/lang/Exception
  }
  
  public static void loadRelated(Context paramContext, ThemeModel paramThemeModel, BaseAdapter<ThemeModel> paramBaseAdapter)
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject1;
    Object localObject2;
    int i;
    for (;;)
    {
      try
      {
        localObject1 = new ArrayList();
        localObject2 = getCachedTheme(paramContext).optJSONArray("themes");
        if (localObject2 != null)
        {
          i = 0;
          if (i < ((JSONArray)localObject2).length()) {
            continue;
          }
          paramContext = ((List)localObject1).iterator();
          label54:
          if (paramContext.hasNext()) {
            continue;
          }
          label63:
          label70:
          paramContext = ((List)localObject1).iterator();
          if (paramContext.hasNext()) {
            continue;
          }
          if (localArrayList.size() > 1) {
            Collections.sort(localArrayList, getRelatedComparator(paramThemeModel));
          }
        }
      }
      catch (Exception paramContext)
      {
        String str;
        continue;
      }
      paramBaseAdapter.setItems(localArrayList);
      return;
      try
      {
        ((List)localObject1).add(new ThemeModel(paramContext, ((JSONArray)localObject2).getJSONObject(i)));
        i += 1;
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
        continue;
      }
      localObject2 = (IModel)paramContext.next();
      if (!((IModel)localObject2).getPackageName().equals(paramThemeModel.getPackageName())) {
        break label54;
      }
      paramThemeModel.setDownloads(((IModel)localObject2).getDownloads());
      paramThemeModel.setTags(((IModel)localObject2).getTags());
      break label63;
      localObject1 = (IModel)paramContext.next();
      if ((!((IModel)localObject1).isDownloaded()) && (!((IModel)localObject1).getPackageName().equals(paramThemeModel.getPackageName())))
      {
        localObject2 = paramThemeModel.getTags().iterator();
        if (((Iterator)localObject2).hasNext())
        {
          str = (String)((Iterator)localObject2).next();
          if ((str.equals("TOP")) || (str.equals("NEW")) || (!((IModel)localObject1).getTags().contains(str))) {
            break label70;
          }
          localArrayList.add(localObject1);
        }
      }
    }
  }
  
  public static boolean needUpdate(Context paramContext, String paramString)
  {
    boolean bool = true;
    for (;;)
    {
      try
      {
        localJSONArray = getCachedTheme(paramContext).optJSONArray("themes");
        if (localJSONArray != null)
        {
          i = 0;
          j = localJSONArray.length();
          if (i < j) {
            continue;
          }
        }
      }
      catch (Exception paramContext)
      {
        JSONArray localJSONArray;
        int i;
        int j;
        continue;
      }
      bool = false;
      return bool;
      try
      {
        JSONObject localJSONObject = localJSONArray.getJSONObject(i);
        if (localJSONObject.optString("pack", "").equals(paramString))
        {
          j = localJSONObject.optInt("version", 1);
          int k = getVersionByPackage(paramContext, paramString);
          if (j > k) {
            continue;
          }
          return false;
        }
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
        i += 1;
      }
    }
  }
  
  private static void onResultThemes(Context paramContext, JSONObject paramJSONObject, int paramInt, IBaseThemeLoader<IModel> paramIBaseThemeLoader)
  {
    JSONArray localJSONArray = paramJSONObject.optJSONArray("themes");
    ArrayList localArrayList;
    int i;
    if (localJSONArray != null)
    {
      localArrayList = new ArrayList();
      i = 0;
    }
    for (;;)
    {
      if (i >= localJSONArray.length())
      {
        Collections.reverse(localArrayList);
        paramIBaseThemeLoader.setItems(localArrayList);
        return;
      }
      if (paramInt == 3) {}
      for (;;)
      {
        try
        {
          paramJSONObject = new ThemeModel(paramContext, localJSONArray.getJSONObject(i));
          localArrayList.add(paramJSONObject);
        }
        catch (JSONException paramJSONObject)
        {
          i += 1;
        }
        paramJSONObject = new Model(paramContext, paramInt, localJSONArray.getJSONObject(i));
        continue;
        paramIBaseThemeLoader.setItems(new ArrayList());
        return;
      }
    }
  }
  
  public static void sharePackageTheme(Activity paramActivity, ThemeModel paramThemeModel)
  {
    try
    {
      paramThemeModel = paramActivity.getString(2131231189, new Object[] { paramThemeModel.getName(), paramThemeModel.getPackageName() });
      Intent localIntent = new Intent("android.intent.action.SEND");
      localIntent.setType("text/plain");
      localIntent.putExtra("android.intent.extra.TEXT", paramThemeModel);
      paramActivity.startActivity(localIntent);
      return;
    }
    catch (Exception paramActivity) {}
  }
}
