package com.sensotools.appphonetosdcardmover;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.sensotools.appphonetosdcardmover.adapter.FileArrayAdapter;
import com.sensotools.appphonetosdcardmover.util.Item;
import com.sensotools.appphonetosdcardmover.util.Log;
import com.sensotools.appphonetosdcardmover.util.Utility;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class AppMoverActivity
  extends Activity
  implements View.OnClickListener
{
  public static FileArrayAdapter adapter;
  public static List<Item> externalApkList;
  public static List<Item> internalApkList;
  public static boolean isInternalApp = true;
  public static boolean isTaskRunning = false;
  private Button btnExternal;
  private Button btnGetMore;
  private Button btnInternal;
  private TextView emptyText;
  private boolean isFirstTime = true;
  private ListView lvApplication;
  private View mProgressBar;
  private TextView mProgressBarText;
  
  public AppMoverActivity() {}
  
  private void addListner()
  {
    this.btnGetMore.setOnClickListener(this);
    this.btnInternal.setOnClickListener(this);
    this.btnExternal.setOnClickListener(this);
  }
  
  private void bindView()
  {
    this.emptyText = ((TextView)findViewById(2131165222));
    this.btnInternal = ((Button)findViewById(2131165220));
    this.btnExternal = ((Button)findViewById(2131165221));
    this.lvApplication = ((ListView)findViewById(2131165223));
    this.mProgressBar = findViewById(2131165224);
    this.mProgressBarText = ((TextView)findViewById(2131165225));
    this.btnGetMore = ((Button)findViewById(2131165217));
    Utility.setHeaderFont(this, (TextView)findViewById(2131165218));
    Utility.setFont(this, this.mProgressBarText);
    Utility.setFont(this, this.emptyText);
  }
  
  @SuppressLint({"DefaultLocale"})
  public static String humanReadableByteCount(long paramLong, boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (int i = 1000; paramLong < i; i = 1024) {
      return paramLong + " B";
    }
    int j = (int)(Math.log(paramLong) / Math.log(i));
    StringBuilder localStringBuilder;
    if (paramBoolean)
    {
      str = "kMGTPE";
      localStringBuilder = new StringBuilder(String.valueOf(str.charAt(j - 1)));
      if (!paramBoolean) {
        break label144;
      }
    }
    label144:
    for (String str = "";; str = "i")
    {
      str = str;
      return String.format("%.1f %sB", new Object[] { Double.valueOf(paramLong / Math.pow(i, j)), str });
      str = "KMGTPE";
      break;
    }
  }
  
  private void init()
  {
    internalApkList = new ArrayList();
    externalApkList = new ArrayList();
  }
  
  private void setProgressBarProgress(int paramInt1, int paramInt2) {}
  
  private void showProgressBar(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.mProgressBar.setVisibility(0);
      return;
    }
    this.mProgressBar.startAnimation(AnimationUtils.loadAnimation(this, 17432577));
    this.mProgressBar.setVisibility(8);
  }
  
  boolean isMovable(String paramString)
    throws Exception
  {
    boolean bool2 = true;
    paramString = createPackageContext(paramString, 0).getAssets().openXmlResourceParser("AndroidManifest.xml");
    int i = paramString.getEventType();
    boolean bool1;
    if (i == 1)
    {
      bool1 = false;
      return bool1;
    }
    switch (i)
    {
    }
    label152:
    for (;;)
    {
      i = paramString.nextToken();
      break;
      if (paramString.getName().matches("manifest"))
      {
        i = 0;
        for (;;)
        {
          if (i >= paramString.getAttributeCount()) {
            break label152;
          }
          if (paramString.getAttributeName(i).matches("installLocation"))
          {
            bool1 = bool2;
            switch (Integer.parseInt(paramString.getAttributeValue(i)))
            {
            }
            break;
          }
          i += 1;
        }
      }
    }
  }
  
  public void loadAd()
  {
    final InterstitialAd localInterstitialAd = new InterstitialAd(this);
    localInterstitialAd.setAdUnitId(getString(2131099700));
    localInterstitialAd.loadAd(new AdRequest.Builder().build());
    localInterstitialAd.setAdListener(new AdListener()
    {
      public void onAdFailedToLoad(int paramAnonymousInt) {}
      
      public void onAdLoaded()
      {
        if ((localInterstitialAd != null) && (localInterstitialAd.isLoaded())) {
          localInterstitialAd.show();
        }
      }
    });
    ((AdView)findViewById(2131165219)).loadAd(new AdRequest.Builder().build());
  }
  
  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    case 2131165218: 
    case 2131165219: 
    default: 
      return;
    case 2131165220: 
      this.btnInternal.setEnabled(false);
      this.btnExternal.setEnabled(true);
      isInternalApp = true;
      if (internalApkList.size() == 0) {
        this.emptyText.setVisibility(0);
      }
      for (;;)
      {
        adapter.setItem(internalApkList);
        adapter.notifyDataSetChanged();
        return;
        this.emptyText.setVisibility(8);
      }
    case 2131165217: 
      paramView = new Intent("android.intent.action.VIEW");
      paramView.setData(Uri.parse("market://search?q=pub:coin4apps"));
      startActivity(paramView);
      loadAd();
      return;
    }
    isInternalApp = false;
    if (externalApkList.size() == 0) {
      this.emptyText.setVisibility(0);
    }
    for (;;)
    {
      adapter.setItem(externalApkList);
      adapter.notifyDataSetChanged();
      this.btnInternal.setEnabled(true);
      this.btnExternal.setEnabled(false);
      return;
      this.emptyText.setVisibility(8);
    }
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903040);
    loadAd();
    bindView();
    init();
    addListner();
    this.isFirstTime = true;
    new TaskScanApp().execute(new Void[0]);
  }
  
  protected void onResume()
  {
    super.onResume();
    if (!this.isFirstTime) {}
    try
    {
      PackageManager localPackageManager = getPackageManager();
      if (isInternalApp)
      {
        if (localPackageManager.getApplicationInfo(((Item)internalApkList.get(FileArrayAdapter.AppPosition)).getPackageName(), 0).sourceDir.startsWith("/mnt/asec/"))
        {
          externalApkList.add((Item)internalApkList.get(FileArrayAdapter.AppPosition));
          internalApkList.remove(FileArrayAdapter.AppPosition);
        }
        for (;;)
        {
          adapter.notifyDataSetChanged();
          Collections.sort(externalApkList);
          Collections.sort(internalApkList);
          return;
          Toast.makeText(this, ((Item)internalApkList.get(FileArrayAdapter.AppPosition)).getName() + " was not successfully moved. Please try again .", 1).show();
        }
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        new TaskScanApp().execute(new Void[0]);
        continue;
        externalApkList.get(FileArrayAdapter.AppPosition);
        if (localException.getApplicationInfo(((Item)externalApkList.get(FileArrayAdapter.AppPosition)).getPackageName(), 0).sourceDir.startsWith("/data/app/"))
        {
          internalApkList.add((Item)externalApkList.get(FileArrayAdapter.AppPosition));
          externalApkList.remove(FileArrayAdapter.AppPosition);
        }
        else
        {
          Toast.makeText(this, ((Item)internalApkList.get(FileArrayAdapter.AppPosition)).getName() + " was not successfully moved. Please try again .", 1).show();
        }
      }
    }
  }
  
  public class TaskScanApp
    extends AsyncTask<Void, Integer, Void>
  {
    private int mAppCount = 0;
    
    public TaskScanApp() {}
    
    private void getApkList()
    {
      PackageManager localPackageManager = AppMoverActivity.this.getPackageManager();
      List localList = localPackageManager.getInstalledPackages(0);
      Iterator localIterator = localList.iterator();
      Object localObject;
      label176:
      File localFile;
      String str3;
      Drawable localDrawable;
      for (;;)
      {
        if (!localIterator.hasNext()) {
          return;
        }
        localObject = (PackageInfo)localIterator.next();
        int i = this.mAppCount + 1;
        this.mAppCount = i;
        publishProgress(new Integer[] { Integer.valueOf(i), Integer.valueOf(localList.size()) });
        String str1 = ((PackageInfo)localObject).packageName;
        if (!((PackageInfo)localObject).packageName.equals(AppMoverActivity.this.getPackageName()))
        {
          try
          {
            localApplicationInfo = localPackageManager.getApplicationInfo(((PackageInfo)localObject).packageName, 0);
            localObject = ((PackageInfo)localObject).versionName;
            if (!localApplicationInfo.sourceDir.startsWith("/data/app/"))
            {
              bool1 = localApplicationInfo.sourceDir.startsWith("/mnt/asec/");
              if (!bool1) {
                continue;
              }
            }
            bool1 = true;
          }
          catch (Exception localException1)
          {
            ApplicationInfo localApplicationInfo;
            boolean bool1;
            boolean bool2;
            String str2;
            Log.d("Log", "Name not found", localException1);
          }
          try
          {
            bool2 = AppMoverActivity.this.isMovable(str1);
            bool1 = bool2;
          }
          catch (Exception localException2)
          {
            localException2.printStackTrace();
            break label176;
            AppMoverActivity.externalApkList.add(new Item(localDrawable, localException2, localException1, (String)localObject, str3, localFile.getAbsolutePath()));
          }
          if (bool1)
          {
            str2 = localApplicationInfo.loadLabel(AppMoverActivity.this.getPackageManager()).toString();
            localFile = new File(localApplicationInfo.publicSourceDir);
            str3 = AppMoverActivity.humanReadableByteCount(localFile.length(), true);
            localDrawable = localPackageManager.getApplicationIcon(str1);
            if (localApplicationInfo.sourceDir.startsWith("/data/app/"))
            {
              AppMoverActivity.internalApkList.add(new Item(localDrawable, str2, str1, (String)localObject, str3, localFile.getAbsolutePath()));
              continue;
            }
          }
        }
      }
    }
    
    protected Void doInBackground(Void... paramVarArgs)
    {
      getApkList();
      return null;
    }
    
    protected void onCancelled()
    {
      super.onCancelled();
      AppMoverActivity.isTaskRunning = false;
    }
    
    protected void onPostExecute(Void paramVoid)
    {
      super.onPostExecute(paramVoid);
      AppMoverActivity.isTaskRunning = false;
      Collections.sort(AppMoverActivity.internalApkList);
      Collections.sort(AppMoverActivity.externalApkList);
      AppMoverActivity.this.showProgressBar(false);
      if ((AppMoverActivity.internalApkList.size() == 0) && (AppMoverActivity.externalApkList.size() == 0))
      {
        AppMoverActivity.this.emptyText.setVisibility(0);
        Toast.makeText(AppMoverActivity.this.getApplicationContext(), "No one Item is Install", 1).show();
        AppMoverActivity.this.lvApplication.setVisibility(0);
        AppMoverActivity.this.isFirstTime = false;
        return;
      }
      AppMoverActivity.adapter = new FileArrayAdapter(AppMoverActivity.this, 2130903041);
      if ((AppMoverActivity.internalApkList.size() > 0) && (AppMoverActivity.isInternalApp))
      {
        AppMoverActivity.adapter.setItem(AppMoverActivity.internalApkList);
        AppMoverActivity.this.btnExternal.setEnabled(true);
      }
      for (;;)
      {
        AppMoverActivity.this.lvApplication.setAdapter(AppMoverActivity.adapter);
        AppMoverActivity.adapter.notifyDataSetChanged();
        break;
        AppMoverActivity.adapter.setItem(AppMoverActivity.externalApkList);
        AppMoverActivity.this.btnInternal.setEnabled(true);
      }
    }
    
    protected void onPreExecute()
    {
      super.onPreExecute();
      AppMoverActivity.this.showProgressBar(true);
      AppMoverActivity.this.btnExternal.setEnabled(false);
      AppMoverActivity.this.btnInternal.setEnabled(false);
      AppMoverActivity.this.lvApplication.setVisibility(8);
      AppMoverActivity.isTaskRunning = true;
    }
    
    protected void onProgressUpdate(Integer... paramVarArgs)
    {
      super.onProgressUpdate(paramVarArgs);
      AppMoverActivity.this.setProgressBarProgress(paramVarArgs[0].intValue(), paramVarArgs[1].intValue());
    }
  }
}
