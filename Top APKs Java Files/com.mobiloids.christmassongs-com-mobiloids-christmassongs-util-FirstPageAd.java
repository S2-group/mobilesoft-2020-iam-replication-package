package com.mobiloids.christmassongs.util;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;

public class FirstPageAd
  extends Activity
{
  public static final String PREFS_NAME = "Scores";
  private static final String TAG_GAME = "christmassongs";
  private static final String TAG_GAME_LINK = "first_game_link";
  private static final String TAG_GAME_NAME = "first_game_name";
  private static final String TAG_IMAGE_LINK = "first_image_link";
  private static final String TAG_PID = "pid";
  private static final String TAG_SHOW_TIME = "show_time";
  private static final String TAG_SUCCESS = "success";
  private static String url_all_products = "http://www.mobiloids.com/android_connect/christmassongs.php";
  private int DEFAULT_SHOW_INTERVAL = 3;
  private int FORCE_REQUEST_COUNT = 3;
  private int SHOW_ADD_SECONDS = 15;
  private ImageView adImg;
  ArrayList<HashMap<String, String>> allprefs;
  private TextView gameName;
  private ImageView install;
  private int interval;
  private boolean isSetOnce = true;
  JSONParser jParser = new JSONParser();
  private LoadGame lg;
  private ProgressDialog pDialog;
  private int played_games;
  JSONArray products = null;
  ArrayList<HashMap<String, Object>> productsList;
  private ImageView skip;
  private Activity thisActivity = null;
  
  public FirstPageAd() {}
  
  private Drawable loadImageFromWebOperations(String paramString)
  {
    try
    {
      paramString = Drawable.createFromStream((InputStream)new URL(paramString).getContent(), "src name");
      return paramString;
    }
    catch (Exception paramString)
    {
      System.out.println("Exc=" + paramString);
    }
    return null;
  }
  
  public boolean hasConnection()
  {
    Object localObject = (ConnectivityManager)getSystemService("connectivity");
    NetworkInfo localNetworkInfo = ((ConnectivityManager)localObject).getNetworkInfo(1);
    if ((localNetworkInfo != null) && (localNetworkInfo.isConnected())) {}
    do
    {
      do
      {
        return true;
        localNetworkInfo = ((ConnectivityManager)localObject).getNetworkInfo(0);
      } while ((localNetworkInfo != null) && (localNetworkInfo.isConnected()));
      localObject = ((ConnectivityManager)localObject).getActiveNetworkInfo();
    } while ((localObject != null) && (((NetworkInfo)localObject).isConnected()));
    return false;
  }
  
  public void onBackPressed()
  {
    super.onBackPressed();
    finish();
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903069);
    this.thisActivity = this;
    this.adImg = ((ImageView)findViewById(2131492976));
    this.gameName = ((TextView)findViewById(2131492975));
    this.skip = ((ImageView)findViewById(2131492977));
    this.install = ((ImageView)findViewById(2131492978));
    this.skip.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (FirstPageAd.this.lg != null) {
          FirstPageAd.this.lg.cancel(true);
        }
        Log.i("SKIP CLICKED ", "");
        FirstPageAd.this.finish();
      }
    });
    this.adImg.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramAnonymousView = new Intent("android.intent.action.VIEW");
        paramAnonymousView.setData(Uri.parse(((HashMap)FirstPageAd.this.productsList.get(0)).get("first_game_link").toString()));
        FirstPageAd.this.startActivity(paramAnonymousView);
      }
    });
    this.install.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramAnonymousView = new Intent("android.intent.action.VIEW");
        paramAnonymousView.setData(Uri.parse(((HashMap)FirstPageAd.this.productsList.get(0)).get("first_game_link").toString()));
        FirstPageAd.this.startActivity(paramAnonymousView);
      }
    });
    this.install.setVisibility(4);
    this.adImg.setVisibility(4);
    if (hasConnection())
    {
      paramBundle = getSharedPreferences("Scores", 0);
      this.played_games = paramBundle.getInt("PLAYED_GAMES", 0);
      SharedPreferences.Editor localEditor = paramBundle.edit();
      int i = this.played_games + 1;
      this.played_games = i;
      localEditor.putInt("PLAYED_GAMES", i);
      localEditor.commit();
      this.interval = paramBundle.getInt("INTERVAL", this.DEFAULT_SHOW_INTERVAL);
      if ((paramBundle.getBoolean("isset", true)) && (this.played_games == this.DEFAULT_SHOW_INTERVAL))
      {
        localEditor.putInt("PLAYED_GAMES", 0);
        localEditor.putBoolean("isset", false);
        localEditor.commit();
      }
      if (this.played_games % this.interval == 0)
      {
        this.productsList = new ArrayList();
        try
        {
          Class.forName("android.os.AsyncTask");
          this.lg = new LoadGame();
          this.lg.execute(new String[0]);
          return;
        }
        catch (ClassNotFoundException paramBundle)
        {
          for (;;)
          {
            paramBundle.printStackTrace();
            finish();
          }
        }
      }
      if (this.lg != null) {
        this.lg.cancel(true);
      }
      this.thisActivity.finish();
      return;
    }
    if (this.lg != null) {
      this.lg.cancel(true);
    }
    this.thisActivity.finish();
  }
  
  class LoadGame
    extends AsyncTask<String, String, Drawable>
  {
    LoadGame() {}
    
    /* Error */
    protected Drawable doInBackground(String... paramVarArgs)
    {
      // Byte code:
      //   0: aconst_null
      //   1: astore 4
      //   3: new 28	java/util/ArrayList
      //   6: dup
      //   7: invokespecial 29	java/util/ArrayList:<init>	()V
      //   10: astore 5
      //   12: aconst_null
      //   13: astore_1
      //   14: aload_0
      //   15: getfield 14	com/mobiloids/christmassongs/util/FirstPageAd$LoadGame:this$0	Lcom/mobiloids/christmassongs/util/FirstPageAd;
      //   18: getfield 33	com/mobiloids/christmassongs/util/FirstPageAd:jParser	Lcom/mobiloids/christmassongs/util/JSONParser;
      //   21: invokestatic 37	com/mobiloids/christmassongs/util/FirstPageAd:access$600	()Ljava/lang/String;
      //   24: ldc 39
      //   26: aload 5
      //   28: invokevirtual 45	com/mobiloids/christmassongs/util/JSONParser:makeHttpRequest	(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)Lorg/json/JSONObject;
      //   31: astore 5
      //   33: aload 5
      //   35: astore_1
      //   36: aload_1
      //   37: ifnull +229 -> 266
      //   40: aload_1
      //   41: ldc 47
      //   43: invokevirtual 53	org/json/JSONObject:getInt	(Ljava/lang/String;)I
      //   46: iconst_1
      //   47: if_icmpne +188 -> 235
      //   50: aload_0
      //   51: getfield 14	com/mobiloids/christmassongs/util/FirstPageAd$LoadGame:this$0	Lcom/mobiloids/christmassongs/util/FirstPageAd;
      //   54: aload_1
      //   55: ldc 55
      //   57: invokevirtual 59	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
      //   60: putfield 63	com/mobiloids/christmassongs/util/FirstPageAd:products	Lorg/json/JSONArray;
      //   63: iconst_0
      //   64: istore_2
      //   65: iload_2
      //   66: aload_0
      //   67: getfield 14	com/mobiloids/christmassongs/util/FirstPageAd$LoadGame:this$0	Lcom/mobiloids/christmassongs/util/FirstPageAd;
      //   70: getfield 63	com/mobiloids/christmassongs/util/FirstPageAd:products	Lorg/json/JSONArray;
      //   73: invokevirtual 69	org/json/JSONArray:length	()I
      //   76: if_icmpge +159 -> 235
      //   79: aload_0
      //   80: getfield 14	com/mobiloids/christmassongs/util/FirstPageAd$LoadGame:this$0	Lcom/mobiloids/christmassongs/util/FirstPageAd;
      //   83: getfield 63	com/mobiloids/christmassongs/util/FirstPageAd:products	Lorg/json/JSONArray;
      //   86: iload_2
      //   87: invokevirtual 73	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
      //   90: astore 7
      //   92: aload 7
      //   94: ldc 75
      //   96: invokevirtual 79	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   99: astore_1
      //   100: aload 7
      //   102: ldc 81
      //   104: invokevirtual 79	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   107: astore 4
      //   109: aload 7
      //   111: ldc 83
      //   113: invokevirtual 79	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   116: astore 5
      //   118: aload 7
      //   120: ldc 85
      //   122: invokevirtual 79	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   125: astore 6
      //   127: aload 7
      //   129: ldc 87
      //   131: invokevirtual 53	org/json/JSONObject:getInt	(Ljava/lang/String;)I
      //   134: istore_3
      //   135: new 89	java/util/HashMap
      //   138: dup
      //   139: invokespecial 90	java/util/HashMap:<init>	()V
      //   142: astore 7
      //   144: aload 7
      //   146: ldc 75
      //   148: aload_1
      //   149: invokevirtual 94	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   152: pop
      //   153: aload 7
      //   155: ldc 81
      //   157: aload 4
      //   159: invokevirtual 94	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   162: pop
      //   163: aload 7
      //   165: ldc 83
      //   167: aload 5
      //   169: invokevirtual 94	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   172: pop
      //   173: aload 7
      //   175: ldc 85
      //   177: aload 6
      //   179: invokevirtual 94	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   182: pop
      //   183: aload 7
      //   185: ldc 87
      //   187: iload_3
      //   188: invokestatic 100	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
      //   191: invokevirtual 94	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   194: pop
      //   195: aload_0
      //   196: getfield 14	com/mobiloids/christmassongs/util/FirstPageAd$LoadGame:this$0	Lcom/mobiloids/christmassongs/util/FirstPageAd;
      //   199: getfield 104	com/mobiloids/christmassongs/util/FirstPageAd:productsList	Ljava/util/ArrayList;
      //   202: aload 7
      //   204: invokevirtual 108	java/util/ArrayList:add	(Ljava/lang/Object;)Z
      //   207: pop
      //   208: iload_2
      //   209: iconst_1
      //   210: iadd
      //   211: istore_2
      //   212: goto -147 -> 65
      //   215: astore 5
      //   217: aload 5
      //   219: invokevirtual 111	java/net/UnknownHostException:printStackTrace	()V
      //   222: goto -186 -> 36
      //   225: astore 5
      //   227: aload 5
      //   229: invokevirtual 112	java/lang/UnknownError:printStackTrace	()V
      //   232: goto -196 -> 36
      //   235: aload_0
      //   236: getfield 14	com/mobiloids/christmassongs/util/FirstPageAd$LoadGame:this$0	Lcom/mobiloids/christmassongs/util/FirstPageAd;
      //   239: aload_0
      //   240: getfield 14	com/mobiloids/christmassongs/util/FirstPageAd$LoadGame:this$0	Lcom/mobiloids/christmassongs/util/FirstPageAd;
      //   243: getfield 104	com/mobiloids/christmassongs/util/FirstPageAd:productsList	Ljava/util/ArrayList;
      //   246: iconst_0
      //   247: invokevirtual 116	java/util/ArrayList:get	(I)Ljava/lang/Object;
      //   250: checkcast 89	java/util/HashMap
      //   253: ldc 83
      //   255: invokevirtual 119	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
      //   258: invokevirtual 124	java/lang/Object:toString	()Ljava/lang/String;
      //   261: invokestatic 128	com/mobiloids/christmassongs/util/FirstPageAd:access$700	(Lcom/mobiloids/christmassongs/util/FirstPageAd;Ljava/lang/String;)Landroid/graphics/drawable/Drawable;
      //   264: astore 4
      //   266: aload 4
      //   268: areturn
      //   269: astore_1
      //   270: aload_1
      //   271: invokevirtual 129	org/json/JSONException:printStackTrace	()V
      //   274: aconst_null
      //   275: areturn
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	276	0	this	LoadGame
      //   0	276	1	paramVarArgs	String[]
      //   64	148	2	i	int
      //   134	54	3	j	int
      //   1	266	4	localObject1	Object
      //   10	158	5	localObject2	Object
      //   215	3	5	localUnknownHostException	java.net.UnknownHostException
      //   225	3	5	localUnknownError	UnknownError
      //   125	53	6	str	String
      //   90	113	7	localObject3	Object
      // Exception table:
      //   from	to	target	type
      //   14	33	215	java/net/UnknownHostException
      //   14	33	225	java/lang/UnknownError
      //   40	63	269	org/json/JSONException
      //   65	208	269	org/json/JSONException
      //   235	266	269	org/json/JSONException
    }
    
    protected void onPostExecute(Drawable paramDrawable)
    {
      try
      {
        FirstPageAd.this.pDialog.dismiss();
        FirstPageAd.access$502(FirstPageAd.this, null);
        if ((FirstPageAd.this.productsList == null) || (FirstPageAd.this.productsList.isEmpty()))
        {
          FirstPageAd.this.thisActivity.finish();
          return;
        }
      }
      catch (Exception localException)
      {
        for (;;)
        {
          FirstPageAd.this.thisActivity.finish();
        }
        if (paramDrawable != null) {}
        try
        {
          FirstPageAd.this.adImg.setImageDrawable(paramDrawable);
          FirstPageAd.this.adImg.setVisibility(0);
          FirstPageAd.this.install.setVisibility(0);
          FirstPageAd.this.gameName.setText(((HashMap)FirstPageAd.this.productsList.get(0)).get("first_game_name").toString());
          int i = ((Integer)((HashMap)FirstPageAd.this.productsList.get(0)).get("show_time")).intValue();
          paramDrawable = FirstPageAd.this.getSharedPreferences("Scores", 0).edit();
          paramDrawable.putInt("INTERVAL", i);
          paramDrawable.commit();
          String str = ((HashMap)FirstPageAd.this.productsList.get(0)).get("first_game_link").toString().substring(20);
          Iterator localIterator = FirstPageAd.this.getPackageManager().getInstalledApplications(128).iterator();
          while (localIterator.hasNext())
          {
            ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
            if (localApplicationInfo.packageName.equals(str))
            {
              paramDrawable.putString(localApplicationInfo.packageName, localApplicationInfo.packageName.toString());
              paramDrawable.commit();
            }
          }
          paramDrawable.putString("CURRENT_AD_LINK", str);
          paramDrawable.commit();
          return;
        }
        catch (ArrayIndexOutOfBoundsException paramDrawable) {}
      }
    }
    
    protected void onPreExecute()
    {
      super.onPreExecute();
      int k = 0;
      int i = 0;
      SharedPreferences localSharedPreferences = FirstPageAd.this.getSharedPreferences("Scores", 0);
      Object[] arrayOfObject = localSharedPreferences.getAll().keySet().toArray();
      if (FirstPageAd.this.played_games % (FirstPageAd.this.interval * FirstPageAd.this.FORCE_REQUEST_COUNT) != 0)
      {
        int j = 0;
        for (;;)
        {
          k = i;
          if (j >= arrayOfObject.length) {
            break;
          }
          if (arrayOfObject[j].equals(localSharedPreferences.getString("CURRENT_AD_LINK", "")))
          {
            if (FirstPageAd.this.lg != null) {
              FirstPageAd.this.lg.cancel(true);
            }
            i = 1;
            FirstPageAd.this.thisActivity.finish();
          }
          j += 1;
        }
      }
      if (k == 0)
      {
        FirstPageAd.access$502(FirstPageAd.this, new ProgressDialog(FirstPageAd.this));
        FirstPageAd.this.pDialog.setMessage("Loading Game. Please wait...");
        FirstPageAd.this.pDialog.setIndeterminate(false);
        FirstPageAd.this.pDialog.setCancelable(true);
        FirstPageAd.this.pDialog.show();
      }
    }
  }
}
