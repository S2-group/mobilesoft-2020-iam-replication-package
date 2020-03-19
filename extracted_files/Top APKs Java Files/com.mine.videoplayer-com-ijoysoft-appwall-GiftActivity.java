package com.ijoysoft.appwall;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.umeng.analytics.MobclickAgent;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GiftActivity
  extends Activity
{
  public static String BASE_URL;
  public static final String DOWNLOAD_ICON_PATH = Environment.getExternalStorageDirectory().getPath() + "/gift/";
  public static String LIST_FILE_NAME;
  private static ArrayList<GiftEntity> resulted = null;
  private static SharedPreferences sh;
  private GridView giftGridView;
  private GiftListViewAdapter giftListViewAdapter;
  private Handler handler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      switch (paramAnonymousMessage.what)
      {
      }
      for (;;)
      {
        super.handleMessage(paramAnonymousMessage);
        return;
        GiftActivity.this.iv_gift_loading.setVisibility(0);
        GiftActivity.this.iv_gift_loading.startAnimation(AnimationUtils.loadAnimation(GiftActivity.this, R.anim.loading));
        continue;
        GiftActivity.this.iv_gift_loading.setVisibility(8);
        GiftActivity.this.iv_gift_loading.clearAnimation();
      }
    }
  };
  private ImageView iv_gift_loading;
  private AppBroadcastReceiver mAppBroadcastReceiver;
  
  static
  {
    BASE_URL = "http://1.appgift.sinaapp.com/";
    LIST_FILE_NAME = "giftList.xml";
  }
  
  public GiftActivity() {}
  
  private void findView()
  {
    this.iv_gift_loading = ((ImageView)findViewById(R.id.iv_gift_loading));
    TextView localTextView = (TextView)findViewById(R.id.gift_link_disclaimer);
    this.giftGridView = ((GridView)findViewById(R.id.gridview));
    this.giftListViewAdapter = new GiftListViewAdapter(this, null, this.giftGridView);
    this.giftGridView.setAdapter(this.giftListViewAdapter);
    this.giftGridView.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        GiftActivity.access$102(GiftActivity.this.getSharedPreferences("settings", 0));
        paramAnonymousAdapterView = GiftActivity.sh.edit();
        paramAnonymousAdapterView.putBoolean(((GiftEntity)GiftActivity.this.giftListViewAdapter.getItem(paramAnonymousInt)).getPackageName(), true);
        Log.i("changle-list", ((GiftEntity)GiftActivity.this.giftListViewAdapter.getItem(paramAnonymousInt)).getPackageName());
        paramAnonymousAdapterView.commit();
        if (GiftActivity.this.checkBrowser(((GiftEntity)GiftActivity.this.giftListViewAdapter.getItem(paramAnonymousInt)).getPackageName()))
        {
          Toast.makeText(GiftActivity.this, "APP already installed", 0).show();
          return;
        }
        paramAnonymousAdapterView = ((GiftEntity)GiftActivity.this.giftListViewAdapter.getItem(paramAnonymousInt)).getPackageName();
        paramAnonymousView = ((GiftEntity)GiftActivity.this.giftListViewAdapter.getItem(paramAnonymousInt)).getMarketUrl();
        GiftActivity.this.goToMarket(paramAnonymousAdapterView, paramAnonymousView);
        GiftActivity.this.giftListViewAdapter.notifyDataSetChanged();
        paramAnonymousAdapterView = new HashMap();
        paramAnonymousAdapterView.put("AppName", ((GiftEntity)GiftActivity.this.giftListViewAdapter.getItem(paramAnonymousInt)).getTitle());
        paramAnonymousAdapterView.put("ListArray", String.valueOf(paramAnonymousInt));
        MobclickAgent.onEvent(GiftActivity.this, "which_app_clicked", paramAnonymousAdapterView);
      }
    });
    findViewById(R.id.back).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        GiftActivity.this.finish();
      }
    });
  }
  
  private boolean isAvilible(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    ArrayList localArrayList = new ArrayList();
    if (paramContext != null)
    {
      int i = 0;
      while (i < paramContext.size())
      {
        localArrayList.add(((PackageInfo)paramContext.get(i)).packageName);
        i += 1;
      }
      return localArrayList.contains(paramString);
    }
    if (isAvilible(this, "com.skype.android.verizon"))
    {
      paramContext = new Intent();
      paramContext.setComponent(new ComponentName("com.skype.android.verizon", "com.skype.android.verizon.SkypeActivity"));
      startActivityForResult(paramContext, -1);
    }
    return true;
  }
  
  public static void setResult(ArrayList<GiftEntity> paramArrayList)
  {
    resulted = paramArrayList;
  }
  
  public boolean checkBrowser(String paramString)
  {
    if ((paramString == null) || ("".equals(paramString))) {
      return false;
    }
    try
    {
      getPackageManager().getApplicationInfo(paramString, 8192);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramString) {}
    return false;
  }
  
  public int getNewNum(ArrayList<GiftEntity> paramArrayList)
  {
    SharedPreferences localSharedPreferences = getSharedPreferences("settings", 0);
    if (paramArrayList != null)
    {
      int i = 0;
      int j = 0;
      for (;;)
      {
        k = i;
        if (j >= paramArrayList.size()) {
          break;
        }
        k = i;
        if (!localSharedPreferences.getBoolean(((GiftEntity)paramArrayList.get(j)).getPackageName(), false)) {
          k = i + 1;
        }
        j += 1;
        i = k;
      }
    }
    int k = 0;
    return k;
  }
  
  public void goToMarket(String paramString1, String paramString2)
  {
    try
    {
      PackageInfo localPackageInfo = getPackageManager().getPackageInfo("com.android.vending", 0);
      if (localPackageInfo == null)
      {
        startActivity(new Intent("android.intent.action.VIEW", Uri.parse(paramString2)));
        return;
      }
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;)
      {
        Object localObject = null;
      }
      paramString1 = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + paramString1));
      paramString1.setClassName("com.android.vending", "com.google.android.finsky.activities.MainActivity");
      paramString1.setFlags(268435456);
      startActivity(paramString1);
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    requestWindowFeature(1);
    setContentView(R.layout.activity_gift);
    this.mAppBroadcastReceiver = new AppBroadcastReceiver();
    Object localObject = new IntentFilter();
    ((IntentFilter)localObject).addAction("android.intent.action.PACKAGE_ADDED");
    ((IntentFilter)localObject).addAction("android.intent.action.PACKAGE_REMOVED");
    ((IntentFilter)localObject).addDataScheme("package");
    registerReceiver(this.mAppBroadcastReceiver, (IntentFilter)localObject);
    findView();
    this.handler.sendEmptyMessage(0);
    updateView(resulted);
    if (!AppWallConfig.isFirstTimeInApp(this))
    {
      AppWallConfig.isNeedSecondUrl = true;
      localObject = PropertiesUtil.getURL();
      String str = PropertiesUtil.getFile();
      NetMusicXmlParseTask localNetMusicXmlParseTask = new NetMusicXmlParseTask(new NetMusicXmlParseTask.HttpCallBack()
      {
        public void handleResult(ArrayList<GiftEntity> paramAnonymousArrayList)
        {
          if (paramAnonymousArrayList != null) {
            GiftActivity.this.updateView(paramAnonymousArrayList);
          }
        }
      });
      localNetMusicXmlParseTask.execute(new String[] { (String)localObject + str });
      localNetMusicXmlParseTask.getStatus();
    }
    super.onCreate(paramBundle);
  }
  
  protected void onDestroy()
  {
    if (this.mAppBroadcastReceiver != null) {
      unregisterReceiver(this.mAppBroadcastReceiver);
    }
    super.onDestroy();
  }
  
  protected void onResume()
  {
    super.onResume();
  }
  
  protected void onStop()
  {
    Intent localIntent = new Intent();
    localIntent.setAction("popularize.newnum2");
    localIntent.putExtra("newnum2", getNewNum(resulted) + "");
    sendBroadcast(localIntent);
    super.onStop();
  }
  
  public void updateView(ArrayList<GiftEntity> paramArrayList)
  {
    if (paramArrayList != null)
    {
      localObject = new ArrayList();
      int i = 0;
      while (i < paramArrayList.size())
      {
        if (!isAvilible(this, ((GiftEntity)paramArrayList.get(i)).getPackageName())) {
          ((ArrayList)localObject).add(paramArrayList.get(i));
        }
        i += 1;
      }
      this.giftListViewAdapter.changeData((ArrayList)localObject);
      resulted = (ArrayList)localObject;
      paramArrayList = getSharedPreferences("settings", 0);
      if (!paramArrayList.getBoolean(((GiftEntity)((ArrayList)localObject).get(0)).getPackageName(), false))
      {
        Log.i("changle-jar", "new : true");
        paramArrayList = paramArrayList.edit();
        paramArrayList.putBoolean(((GiftEntity)((ArrayList)localObject).get(0)).getPackageName(), true);
        paramArrayList.commit();
        if (!checkBrowser(((GiftEntity)((ArrayList)localObject).get(0)).getPackageName())) {
          break label217;
        }
        Toast.makeText(this, "APP is installed", 0).show();
      }
      for (;;)
      {
        paramArrayList = getSharedPreferences("title", 0).edit();
        paramArrayList.putInt("title_num", getNewNum((ArrayList)localObject));
        paramArrayList.commit();
        this.handler.sendEmptyMessage(1);
        return;
        label217:
        paramArrayList = ((GiftEntity)((ArrayList)localObject).get(0)).getMarketUrl();
        goToMarket(((GiftEntity)((ArrayList)localObject).get(0)).getPackageName(), paramArrayList);
        this.giftListViewAdapter.notifyDataSetChanged();
      }
    }
    paramArrayList = PropertiesUtil.getSecondURL();
    Object localObject = PropertiesUtil.getSecondFile();
    NetMusicXmlParseTask localNetMusicXmlParseTask = new NetMusicXmlParseTask(new NetMusicXmlParseTask.HttpCallBack()
    {
      public void handleResult(ArrayList<GiftEntity> paramAnonymousArrayList)
      {
        if (paramAnonymousArrayList != null) {
          GiftActivity.this.updateView(paramAnonymousArrayList);
        }
      }
    });
    localNetMusicXmlParseTask.execute(new String[] { paramArrayList + (String)localObject });
    localNetMusicXmlParseTask.getStatus();
  }
  
  public class AppBroadcastReceiver
    extends BroadcastReceiver
  {
    private final String ADD_APP = "android.intent.action.PACKAGE_ADDED";
    private final String REMOVE_APP = "android.intent.action.PACKAGE_REMOVED";
    
    public AppBroadcastReceiver() {}
    
    public void onReceive(Context paramContext, Intent paramIntent)
    {
      paramContext = paramIntent.getAction();
      if ("android.intent.action.PACKAGE_ADDED".equals(paramContext)) {
        Log.i("changle-rece", "add");
      }
      if ("android.intent.action.PACKAGE_REMOVED".equals(paramContext))
      {
        Log.i("changle-rece", paramIntent.getDataString());
        paramContext = GiftActivity.this.getSharedPreferences("settings", 0).edit();
        paramContext.putBoolean("packageName", true);
        paramContext.commit();
      }
    }
  }
}
