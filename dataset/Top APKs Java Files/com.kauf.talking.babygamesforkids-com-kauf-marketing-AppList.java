package com.kauf.marketing;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings.Secure;
import android.text.TextUtils.TruncateAt;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.kauf.api.GoogleAnalytics;
import com.kauf.util.QualityAssurance;
import com.kauf.util.Store;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.ByteArrayBuffer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AppList
  extends Activity
  implements View.OnClickListener
{
  public static final String CALL_BY_DETAIL = "Detail";
  public static final String CALL_BY_FIRST_AD = "FirstAd";
  public static final String CALL_BY_TALKING = "Talking";
  private static final String CALL_BY_UNKNOWN = "unknown";
  private static final int CLASS_VERSION = 1;
  public static final String EXTRA_CALL_BY = "CallBy";
  public static final String INTENT_ACTION = "com.kauf.intent.action.applist";
  private static final String PATH_LOCAL = "marketing/applist/";
  private static final String PATH_SERVER = "https://android.maxpedia.org/android/ad/applist/";
  private static final String SEPERATOR_ELEMENT = "~§§~";
  private static final String SEPERATOR_PAIR = "~§~";
  private HashMap<String, Intent> appsInstalled = new HashMap();
  private BitmapFactory.Options bitmapFactoryOptions;
  private String callBy;
  private ArrayList<String> categoryTitle = new ArrayList();
  private DownloadFileAsync downloadFileAsync;
  private Handler handler = new Handler();
  private ArrayList<ImageView> imageViewItems = new ArrayList();
  private boolean isLayoutShowing = false;
  private ArrayList<ArrayList<String>> itemID = new ArrayList();
  private ArrayList<ArrayList<String>> itemIcon = new ArrayList();
  private ArrayList<ArrayList<String>> itemIntent = new ArrayList();
  private ArrayList<ArrayList<String>> itemTitle = new ArrayList();
  private int itemsPerLine = 4;
  private LinearLayout linearLayout;
  private StringBuilder ownAppsInstalled = new StringBuilder();
  private String params;
  private ProgressBar progressBar;
  private float scale;
  private Timer timer;
  private String userAgent;
  
  public AppList() {}
  
  private void getAppsInstalled()
  {
    this.appsInstalled.clear();
    try
    {
      Iterator localIterator = getPackageManager().getInstalledApplications(128).iterator();
      for (;;)
      {
        if (!localIterator.hasNext()) {
          return;
        }
        ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
        this.appsInstalled.put(localApplicationInfo.packageName, getPackageManager().getLaunchIntentForPackage(localApplicationInfo.packageName));
        if (localApplicationInfo.packageName.startsWith("com.kauf.")) {
          this.ownAppsInstalled.append(localApplicationInfo.packageName + "|");
        }
      }
      return;
    }
    catch (Exception localException) {}
  }
  
  private String getParams()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("version~§~1~§§~");
    localStringBuilder.append("call_by~§~" + this.callBy + "~§§~");
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    localStringBuilder.append("device_width~§~" + localDisplayMetrics.widthPixels + "~§§~");
    localStringBuilder.append("device_height~§~" + localDisplayMetrics.heightPixels + "~§§~");
    localStringBuilder.append("user_agent~§~" + this.userAgent + "~§§~");
    localStringBuilder.append("user_id~§~" + Settings.Secure.getString(getContentResolver(), "android_id") + "~§§~");
    localStringBuilder.append("app_name~§~" + getTitle().toString().replace(" ", "") + "~§§~");
    localStringBuilder.append("app_id~§~" + getPackageName() + "~§§~");
    localStringBuilder.append("app_la~§~" + Locale.getDefault().getLanguage() + "~§§~");
    localStringBuilder.append("app_ver~§~" + UserInfos.getVersionName(this) + "~§§~");
    localStringBuilder.append("vers_incr~§~" + Build.VERSION.INCREMENTAL + "~§§~");
    localStringBuilder.append("vers_rel~§~" + Build.VERSION.RELEASE + "~§§~");
    localStringBuilder.append("vers_sdk~§~" + Build.VERSION.SDK_INT + "~§§~");
    localStringBuilder.append("build_board~§~" + Build.BOARD + "~§§~");
    localStringBuilder.append("build_brand~§~" + Build.BRAND + "~§§~");
    localStringBuilder.append("build_dev~§~" + Build.DEVICE + "~§§~");
    localStringBuilder.append("build_display~§~" + Build.DISPLAY + "~§§~");
    localStringBuilder.append("build_fingerprint~§~" + Build.FINGERPRINT + "~§§~");
    localStringBuilder.append("build_host~§~" + Build.HOST + "~§§~");
    localStringBuilder.append("build_id~§~" + Build.ID + "~§§~");
    localStringBuilder.append("build_model~§~" + Build.MODEL + "~§§~");
    localStringBuilder.append("build_product~§~" + Build.PRODUCT + "~§§~");
    localStringBuilder.append("build_tags~§~" + Build.TAGS + "~§§~");
    localStringBuilder.append("build_time~§~" + String.valueOf(Build.TIME) + "~§§~");
    localStringBuilder.append("build_type~§~" + Build.TYPE + "~§§~");
    localStringBuilder.append("build_user~§~" + Build.USER + "~§§~");
    return localStringBuilder.toString();
  }
  
  private void pushData(final String paramString)
  {
    new Thread()
    {
      public void run()
      {
        try
        {
          URLConnection localURLConnection = new URL("https://android.maxpedia.org/android/ad/applist/statistic.pl?" + paramString).openConnection();
          localURLConnection.setConnectTimeout(5000);
          localURLConnection.setUseCaches(false);
          localURLConnection.connect();
          localURLConnection.getInputStream().close();
          return;
        }
        catch (Exception localException) {}catch (IOException localIOException) {}catch (MalformedURLException localMalformedURLException) {}
      }
    }.start();
  }
  
  private LinearLayout setCategory(int paramInt)
  {
    this.isLayoutShowing = true;
    stopTimeout();
    this.progressBar.setVisibility(8);
    LinearLayout localLinearLayout1 = new LinearLayout(this);
    localLinearLayout1.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
    localLinearLayout1.setOrientation(1);
    localLinearLayout1.setPadding(0, 0, 0, (int)(30.0F * this.scale + 0.5F));
    Object localObject1 = new TextView(this);
    ((TextView)localObject1).setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
    ((TextView)localObject1).setGravity(17);
    ((TextView)localObject1).setTextAppearance(this, 16973890);
    ((TextView)localObject1).setTextColor(Color.parseColor("#4e4e4e"));
    ((TextView)localObject1).setTypeface(null, 1);
    int i = (int)(1.5F * this.scale + 0.5F);
    ((TextView)localObject1).setShadowLayer(1.0F, i, i, -1);
    ((TextView)localObject1).setText((CharSequence)this.categoryTitle.get(paramInt));
    ((TextView)localObject1).setPadding(0, 0, 0, (int)(5.0F * this.scale + 0.5F));
    localLinearLayout1.addView((View)localObject1);
    localObject1 = new ArrayList();
    int k = ((ArrayList)this.itemTitle.get(paramInt)).size();
    i = 0;
    if (i >= k) {
      localObject1 = ((ArrayList)localObject1).iterator();
    }
    for (;;)
    {
      if (!((Iterator)localObject1).hasNext())
      {
        return localLinearLayout1;
        if (i % this.itemsPerLine == 0)
        {
          localLinearLayout2 = new LinearLayout(this);
          localLinearLayout2.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
          localLinearLayout2.setOrientation(0);
          ((ArrayList)localObject1).add(localLinearLayout2);
        }
        LinearLayout localLinearLayout2 = new LinearLayout(this);
        Object localObject2 = new LinearLayout.LayoutParams(-1, -2);
        ((LinearLayout.LayoutParams)localObject2).weight = 1.0F;
        localLinearLayout2.setLayoutParams((ViewGroup.LayoutParams)localObject2);
        localLinearLayout2.setOrientation(1);
        localLinearLayout2.setOnClickListener(this);
        localLinearLayout2.setTag(paramInt + "~§~" + i);
        int j = (int)(3.0F * this.scale + 0.5F);
        localLinearLayout2.setPadding(j, j, j, j);
        localObject2 = new FrameLayout(this);
        ((FrameLayout)localObject2).setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        Object localObject3 = new ImageView(this);
        ((ImageView)localObject3).setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        ((ImageView)localObject3).setAdjustViewBounds(true);
        j = (int)(40 / this.itemsPerLine * this.scale + 0.5F);
        ((ImageView)localObject3).setPadding(j, j, j, 0);
        ((ImageView)localObject3).setScaleType(ImageView.ScaleType.FIT_CENTER);
        this.imageViewItems.add(localObject3);
        setImage(paramInt, i, this.imageViewItems.size() - 1);
        ((FrameLayout)localObject2).addView((View)localObject3);
        if (this.appsInstalled.containsKey(((ArrayList)this.itemID.get(paramInt)).get(i)))
        {
          localObject3 = new LinearLayout(this);
          ((LinearLayout)localObject3).setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
          ((LinearLayout)localObject3).setOrientation(0);
          Object localObject4 = new View(this);
          LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-1, -2);
          localLayoutParams.weight = 0.5F;
          ((View)localObject4).setLayoutParams(localLayoutParams);
          ((LinearLayout)localObject3).addView((View)localObject4);
          localObject4 = new ImageView(this);
          localLayoutParams = new LinearLayout.LayoutParams(-1, -2);
          localLayoutParams.weight = 1.0F;
          ((ImageView)localObject4).setLayoutParams(localLayoutParams);
          ((ImageView)localObject4).setAdjustViewBounds(true);
          ((ImageView)localObject4).setScaleType(ImageView.ScaleType.FIT_CENTER);
          ((ImageView)localObject4).setImageResource(R.drawable.applist_check);
          ((LinearLayout)localObject3).addView((View)localObject4);
          ((FrameLayout)localObject2).addView((View)localObject3);
        }
        localLinearLayout2.addView((View)localObject2);
        localObject2 = new TextView(this);
        ((TextView)localObject2).setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        ((TextView)localObject2).setLines(1);
        ((TextView)localObject2).setMaxLines(2);
        ((TextView)localObject2).setEllipsize(TextUtils.TruncateAt.END);
        ((TextView)localObject2).setGravity(1);
        ((TextView)localObject2).setTextAppearance(this, 16973894);
        ((TextView)localObject2).setTypeface(null, 1);
        ((TextView)localObject2).setTextColor(Color.parseColor("#3e3e3e"));
        ((TextView)localObject2).setText((CharSequence)((ArrayList)this.itemTitle.get(paramInt)).get(i));
        j = (int)(1.0F * this.scale + 0.5F);
        ((TextView)localObject2).setShadowLayer(1.0F, j, j, -1);
        localLinearLayout2.addView((View)localObject2);
        ((LinearLayout)((ArrayList)localObject1).get(((ArrayList)localObject1).size() - 1)).addView(localLinearLayout2);
        if ((i + 1 == k) && (k % this.itemsPerLine != 0)) {
          j = 0;
        }
        for (;;)
        {
          if (j >= this.itemsPerLine - k % this.itemsPerLine)
          {
            i += 1;
            break;
          }
          localLinearLayout2 = new LinearLayout(this);
          localObject2 = new LinearLayout.LayoutParams(-1, -2);
          ((LinearLayout.LayoutParams)localObject2).weight = 1.0F;
          localLinearLayout2.setLayoutParams((ViewGroup.LayoutParams)localObject2);
          ((LinearLayout)((ArrayList)localObject1).get(((ArrayList)localObject1).size() - 1)).addView(localLinearLayout2);
          j += 1;
        }
      }
      localLinearLayout1.addView((LinearLayout)((Iterator)localObject1).next());
    }
  }
  
  private void setImage(int paramInt1, int paramInt2, int paramInt3)
  {
    String str = (String)((ArrayList)this.itemIcon.get(paramInt1)).get(paramInt2);
    File localFile = new File(getFilesDir(), "marketing/applist/" + str);
    if (!localFile.getParentFile().exists()) {
      localFile.getParentFile().mkdirs();
    }
    if (localFile.exists())
    {
      ((ImageView)this.imageViewItems.get(paramInt3)).setImageBitmap(BitmapFactory.decodeFile(localFile.getAbsolutePath(), this.bitmapFactoryOptions));
      return;
    }
    new DownloadIconAsync(null).execute(new String[] { "https://android.maxpedia.org/android/ad/applist/", str, String.valueOf(paramInt3) });
  }
  
  private void splitData(String paramString)
  {
    try
    {
      paramString = new JSONObject(paramString).getJSONArray("data");
      int i = 0;
      if (i >= paramString.length()) {
        return;
      }
      Object localObject = paramString.getJSONObject(i);
      this.categoryTitle.add(((JSONObject)localObject).getString("title"));
      if (i == 0) {
        this.itemsPerLine = ((JSONObject)localObject).getInt("items_per_line");
      }
      ArrayList localArrayList1 = new ArrayList();
      ArrayList localArrayList2 = new ArrayList();
      ArrayList localArrayList3 = new ArrayList();
      ArrayList localArrayList4 = new ArrayList();
      localObject = ((JSONObject)localObject).getJSONArray("items");
      int j = 0;
      for (;;)
      {
        if (j >= ((JSONArray)localObject).length())
        {
          this.itemID.add(localArrayList1);
          this.itemTitle.add(localArrayList2);
          this.itemIcon.add(localArrayList3);
          this.itemIntent.add(localArrayList4);
          this.linearLayout.addView(setCategory(i));
          i += 1;
          break;
        }
        JSONObject localJSONObject = ((JSONArray)localObject).getJSONObject(j);
        localArrayList1.add(localJSONObject.getString("id"));
        localArrayList2.add(localJSONObject.getString("title"));
        localArrayList3.add(localJSONObject.getString("icon"));
        localArrayList4.add(localJSONObject.getString("intent"));
        j += 1;
      }
      return;
    }
    catch (JSONException paramString)
    {
      finish();
    }
  }
  
  private void startTimeout()
  {
    this.timer = new Timer();
    this.timer.schedule(new TimerTask()
    {
      public void run()
      {
        AppList.this.handler.post(new Runnable()
        {
          public void run()
          {
            if (!AppList.this.isLayoutShowing) {
              AppList.this.finish();
            }
          }
        });
      }
    }, 4000L);
  }
  
  private void stopTimeout()
  {
    if (this.timer != null)
    {
      this.timer.cancel();
      this.timer = null;
    }
  }
  
  public void onClick(View paramView)
  {
    if (paramView.getId() == R.id.ImageViewAppListClose) {
      finish();
    }
    do
    {
      return;
      localObject1 = paramView.getTag().toString().split("~§~");
    } while (localObject1.length != 2);
    int i = Integer.valueOf(localObject1[0]).intValue();
    int j = Integer.valueOf(localObject1[1]).intValue();
    Object localObject1 = (String)((ArrayList)this.itemID.get(i)).get(j);
    Object localObject3;
    if (!((String)localObject1).equals(getPackageName()))
    {
      if (!this.appsInstalled.containsKey(localObject1)) {
        break label790;
      }
      localObject3 = (String)((ArrayList)this.itemIntent.get(i)).get(j);
      if (!((String)localObject3).equalsIgnoreCase("default")) {
        break label334;
      }
      if (QualityAssurance.isIntentAvailable(paramView.getContext(), (Intent)this.appsInstalled.get(localObject1)))
      {
        paramView = new StringBuilder();
        paramView.append("target=app");
      }
    }
    try
    {
      paramView.append("&click=" + URLEncoder.encode((String)localObject1, Charset.defaultCharset().name()));
      paramView.append("&category=default");
      Object localObject2 = this.params.split("~§§~");
      j = localObject2.length;
      i = 0;
      for (;;)
      {
        if (i >= j)
        {
          pushData(paramView.toString());
          startActivity((Intent)this.appsInstalled.get(localObject1));
          label257:
          finish();
          return;
        }
        localObject3 = localObject2[i].split("~§~");
        if (localObject3.length == 2) {}
        try
        {
          paramView.append("&" + localObject3[0] + "=" + URLEncoder.encode(localObject3[1], Charset.defaultCharset().name()));
          i += 1;
          continue;
          label334:
          localObject2 = new Intent("com.kauf.intent.action.applist." + (String)localObject3);
          ((Intent)localObject2).addCategory("android.intent.category.DEFAULT");
          ((Intent)localObject2).setFlags(268435456);
          ((Intent)localObject2).addFlags(67108864);
          if (QualityAssurance.isIntentAvailable(paramView.getContext(), (Intent)localObject2))
          {
            paramView = new StringBuilder();
            paramView.append("target=app");
          }
          try
          {
            paramView.append("&click=" + URLEncoder.encode((String)localObject1, Charset.defaultCharset().name()));
            paramView.append("&category=" + URLEncoder.encode((String)localObject3, Charset.defaultCharset().name()));
            localObject1 = this.params.split("~§§~");
            j = localObject1.length;
            i = 0;
            label497:
            if (i >= j)
            {
              pushData(paramView.toString());
              startActivity((Intent)localObject2);
              break label257;
            }
            localObject3 = localObject1[i].split("~§~");
            if (localObject3.length == 2) {}
            try
            {
              paramView.append("&" + localObject3[0] + "=" + URLEncoder.encode(localObject3[1], Charset.defaultCharset().name()));
              i += 1;
              break label497;
              if (!QualityAssurance.isIntentAvailable(paramView.getContext(), (Intent)this.appsInstalled.get(localObject1))) {
                break label257;
              }
              paramView = new StringBuilder();
              paramView.append("target=app");
              try
              {
                paramView.append("&click=" + URLEncoder.encode((String)localObject1, Charset.defaultCharset().name()));
                paramView.append("&category=default");
                localObject2 = this.params.split("~§§~");
                j = localObject2.length;
                i = 0;
                label686:
                if (i >= j)
                {
                  pushData(paramView.toString());
                  startActivity((Intent)this.appsInstalled.get(localObject1));
                  break label257;
                }
                localObject3 = localObject2[i].split("~§~");
                if (localObject3.length == 2) {}
                try
                {
                  paramView.append("&" + localObject3[0] + "=" + URLEncoder.encode(localObject3[1], Charset.defaultCharset().name()));
                  i += 1;
                  break label686;
                  label790:
                  localObject2 = new Intent("android.intent.action.VIEW", Uri.parse(Store.getProductLink((String)localObject1, "AppList")));
                  ((Intent)localObject2).setFlags(524288);
                  if (!QualityAssurance.isIntentAvailable(paramView.getContext(), (Intent)localObject2)) {
                    break label257;
                  }
                  paramView = new StringBuilder();
                  paramView.append("target=market");
                  try
                  {
                    paramView.append("&click=" + URLEncoder.encode((String)localObject1, Charset.defaultCharset().name()));
                    localObject1 = this.params.split("~§§~");
                    j = localObject1.length;
                    i = 0;
                    for (;;)
                    {
                      if (i >= j)
                      {
                        pushData(paramView.toString());
                        startActivity((Intent)localObject2);
                        break;
                      }
                      localObject3 = localObject1[i].split("~§~");
                      if (localObject3.length == 2) {}
                      try
                      {
                        paramView.append("&" + localObject3[0] + "=" + URLEncoder.encode(localObject3[1], Charset.defaultCharset().name()));
                        i += 1;
                      }
                      catch (UnsupportedEncodingException localUnsupportedEncodingException5)
                      {
                        for (;;) {}
                      }
                    }
                  }
                  catch (UnsupportedEncodingException localUnsupportedEncodingException1)
                  {
                    for (;;) {}
                  }
                }
                catch (UnsupportedEncodingException localUnsupportedEncodingException6)
                {
                  for (;;) {}
                }
              }
              catch (UnsupportedEncodingException localUnsupportedEncodingException3)
              {
                for (;;) {}
              }
            }
            catch (UnsupportedEncodingException localUnsupportedEncodingException7)
            {
              for (;;) {}
            }
          }
          catch (UnsupportedEncodingException localUnsupportedEncodingException2)
          {
            for (;;) {}
          }
        }
        catch (UnsupportedEncodingException localUnsupportedEncodingException8)
        {
          for (;;) {}
        }
      }
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException4)
    {
      for (;;) {}
    }
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.applist);
    this.progressBar = ((ProgressBar)findViewById(R.id.ProgressBarAppList));
    this.linearLayout = ((LinearLayout)findViewById(R.id.LinearLayoutAppListContainer));
    findViewById(R.id.ImageViewAppListClose).setOnClickListener(this);
    this.scale = getResources().getDisplayMetrics().density;
    this.bitmapFactoryOptions = new BitmapFactory.Options();
    this.bitmapFactoryOptions.inScaled = false;
    if (getIntent().getStringExtra("CallBy") == null) {}
    for (paramBundle = "unknown";; paramBundle = getIntent().getStringExtra("CallBy"))
    {
      this.callBy = paramBundle;
      getAppsInstalled();
      this.userAgent = new WebView(this).getSettings().getUserAgentString();
      this.params = getParams();
      this.downloadFileAsync = new DownloadFileAsync(null);
      this.downloadFileAsync.execute(new String[] { "https://android.maxpedia.org/android/ad/applist/applist.pl", this.params });
      return;
    }
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
  }
  
  protected void onStart()
  {
    super.onStart();
    GoogleAnalytics.activityStart(this);
    if (!this.isLayoutShowing) {
      startTimeout();
    }
  }
  
  protected void onStop()
  {
    super.onStop();
    GoogleAnalytics.activityStop(this);
    stopTimeout();
  }
  
  private final class DownloadFileAsync
    extends AsyncTask<String, String, String>
  {
    private DownloadFileAsync() {}
    
    protected String doInBackground(String... paramVarArgs)
    {
      try
      {
        localObject1 = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout((HttpParams)localObject1, 3000);
        HttpConnectionParams.setSoTimeout((HttpParams)localObject1, 5000);
        localObject1 = new DefaultHttpClient((HttpParams)localObject1);
        ((DefaultHttpClient)localObject1).getParams().setParameter("http.useragent", AppList.this.userAgent);
        localObject2 = new HttpPost(paramVarArgs[0]);
        localArrayList = new ArrayList(2);
        paramVarArgs = paramVarArgs[1].split("~§§~");
        j = paramVarArgs.length;
        i = 0;
      }
      catch (UnsupportedEncodingException paramVarArgs)
      {
        Object localObject1;
        Object localObject2;
        ArrayList localArrayList;
        int j;
        return "";
      }
      catch (IOException paramVarArgs)
      {
        for (;;) {}
      }
      catch (IllegalStateException paramVarArgs)
      {
        for (;;) {}
      }
      catch (ClientProtocolException paramVarArgs)
      {
        for (;;)
        {
          int i;
          continue;
          i += 1;
        }
      }
      if (i >= j)
      {
        localArrayList.add(new BasicNameValuePair("own_apps", AppList.this.ownAppsInstalled.toString()));
        ((HttpPost)localObject2).setEntity(new UrlEncodedFormEntity(localArrayList, "UTF-8"));
        paramVarArgs = ((DefaultHttpClient)localObject1).execute((HttpUriRequest)localObject2).getEntity().getContent();
        localObject1 = new BufferedInputStream(paramVarArgs);
        localObject2 = new ByteArrayBuffer(20);
      }
      for (;;)
      {
        i = ((BufferedInputStream)localObject1).read();
        if (i == -1)
        {
          ((BufferedInputStream)localObject1).close();
          paramVarArgs.close();
          return new String(((ByteArrayBuffer)localObject2).toByteArray());
          String[] arrayOfString = paramVarArgs[i].split("~§~");
          if (arrayOfString.length != 2) {
            break;
          }
          localArrayList.add(new BasicNameValuePair(arrayOfString[0], arrayOfString[1]));
          break;
        }
        ((ByteArrayBuffer)localObject2).append((byte)i);
      }
    }
    
    protected void onPostExecute(String paramString)
    {
      if (!AppList.this.isFinishing())
      {
        if (paramString.equals("")) {
          AppList.this.finish();
        }
      }
      else {
        return;
      }
      AppList.this.splitData(paramString);
    }
  }
  
  private class DownloadIconAsync
    extends AsyncTask<String, String, Bitmap>
  {
    private String filename;
    private int imageViewPosition;
    
    private DownloadIconAsync() {}
    
    protected Bitmap doInBackground(String... paramVarArgs)
    {
      this.filename = paramVarArgs[1];
      this.imageViewPosition = Integer.valueOf(paramVarArgs[2]).intValue();
      try
      {
        paramVarArgs = new URL(paramVarArgs[0] + paramVarArgs[1]).openConnection();
        paramVarArgs.setConnectTimeout(3000);
        paramVarArgs.setUseCaches(false);
        paramVarArgs.connect();
        paramVarArgs = paramVarArgs.getInputStream();
        Bitmap localBitmap = BitmapFactory.decodeStream(paramVarArgs);
        paramVarArgs.close();
        return localBitmap;
      }
      catch (Exception paramVarArgs)
      {
        return null;
      }
      catch (IOException paramVarArgs)
      {
        for (;;) {}
      }
      catch (MalformedURLException paramVarArgs)
      {
        for (;;) {}
      }
    }
    
    protected void onPostExecute(Bitmap paramBitmap)
    {
      if (paramBitmap != null) {
        if (!AppList.this.isFinishing()) {
          ((ImageView)AppList.this.imageViewItems.get(this.imageViewPosition)).setImageBitmap(paramBitmap);
        }
      }
      try
      {
        FileOutputStream localFileOutputStream = new FileOutputStream(new File(AppList.this.getFilesDir(), "marketing/applist/" + this.filename));
        paramBitmap.compress(Bitmap.CompressFormat.PNG, 90, localFileOutputStream);
        localFileOutputStream.flush();
        localFileOutputStream.close();
        return;
      }
      catch (Exception paramBitmap) {}
    }
  }
}
