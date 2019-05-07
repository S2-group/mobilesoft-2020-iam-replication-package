package com.xharma.chatbin.activity;

import android.app.FragmentManager;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.xharma.chatbin.adapter.AddAppAdapter;
import com.xharma.chatbin.adapter.AddAppAdapter.customRemoveListener;
import com.xharma.chatbin.common.PrefManager;
import com.xharma.chatbin.common.SQLiteHelper;
import com.xharma.chatbin.entity.AppEntity;
import com.xharma.chatbin.frags.AddAppFrag;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddAppActivity
  extends AppCompatActivity
  implements AddAppAdapter.customRemoveListener
{
  private AdView adView;
  private LinearLayout addApp;
  private int appCount;
  private ListView appList;
  private AddAppAdapter arrayAdapter;
  private SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
  private final int maxAppCount = 5;
  private ArrayList<AppEntity> newList;
  private PrefManager prefManager;
  private SQLiteHelper sqLiteHelper;
  private Toolbar toolbar;
  
  public AddAppActivity() {}
  
  public void loadAdditionalApps()
  {
    this.newList = new ArrayList();
    String str1 = this.prefManager.getAdditionalPackages();
    Object localObject1 = getPackageManager();
    int i = 0;
    localObject1 = ((PackageManager)localObject1).getInstalledPackages(0);
    while (i < ((List)localObject1).size())
    {
      Object localObject3 = (PackageInfo)((List)localObject1).get(i);
      if ((((PackageInfo)localObject3).applicationInfo.flags & 0x1) == 0)
      {
        String str2 = ((PackageInfo)localObject3).applicationInfo.packageName;
        Object localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append(str2);
        ((StringBuilder)localObject2).append("@");
        if ((str1.contains(((StringBuilder)localObject2).toString())) && (!str2.equals("com.whatsapp")) && (!str2.contains("chatbin")))
        {
          localObject2 = ((PackageInfo)localObject3).applicationInfo.loadLabel(getPackageManager()).toString();
          localObject3 = ((PackageInfo)localObject3).applicationInfo.loadIcon(getPackageManager());
          AppEntity localAppEntity = new AppEntity();
          localAppEntity.setAppName((String)localObject2);
          localAppEntity.setPkgName(str2);
          localAppEntity.setAppIcon((Drawable)localObject3);
          this.newList.add(localAppEntity);
        }
      }
      i += 1;
    }
    this.arrayAdapter = new AddAppAdapter(this, this.newList);
    this.arrayAdapter.setCustomRemoveListener(this);
    this.appList.setAdapter(this.arrayAdapter);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2131427355);
    this.toolbar = ((Toolbar)findViewById(2131296568));
    setSupportActionBar(this.toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    this.appList = ((ListView)findViewById(2131296308));
    this.prefManager = new PrefManager(this);
    this.sqLiteHelper = new SQLiteHelper(this);
    this.addApp = ((LinearLayout)findViewById(2131296307));
    if (this.prefManager.getAdditionalPackageCount() == 0) {
      this.prefManager.setFullPkgList("");
    }
    this.addApp.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        AddAppActivity.access$002(AddAppActivity.this, AddAppActivity.this.prefManager.getAdditionalPackageCount());
        if (AddAppActivity.this.appCount < 5)
        {
          paramAnonymousView = new Bundle();
          localObject = AddAppActivity.this.getFragmentManager();
          paramAnonymousView.putString("from", "activity");
          AddAppFrag localAddAppFrag = new AddAppFrag();
          localAddAppFrag.setArguments(paramAnonymousView);
          localAddAppFrag.show((FragmentManager)localObject, "App Fragment");
          return;
        }
        paramAnonymousView = AddAppActivity.this.getLayoutInflater().inflate(2131427373, (ViewGroup)AddAppActivity.this.findViewById(2131296258));
        Object localObject = new Toast(AddAppActivity.this.getApplicationContext());
        ((TextView)paramAnonymousView.findViewById(2131296271)).setText("You can not add more than 5 apps, Please remove any app to add more");
        ((Toast)localObject).setDuration(1);
        ((Toast)localObject).setView(paramAnonymousView);
        ((Toast)localObject).show();
      }
    });
    loadAdditionalApps();
    try
    {
      this.adView = new AdView(this, getString(2131624044), AdSize.BANNER_HEIGHT_50);
      ((LinearLayout)findViewById(2131296471)).addView(this.adView);
      this.adView.setAdListener(new AdListener()
      {
        public void onAdClicked(Ad paramAnonymousAd) {}
        
        public void onAdLoaded(Ad paramAnonymousAd) {}
        
        public void onError(Ad paramAnonymousAd, AdError paramAnonymousAdError) {}
        
        public void onLoggingImpression(Ad paramAnonymousAd) {}
      });
      this.adView.loadAd();
      return;
    }
    catch (Exception paramBundle)
    {
      Calendar localCalendar = Calendar.getInstance();
      PrintStream localPrintStream = System.out;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Current time => ");
      localStringBuilder.append(localCalendar.getTime());
      localPrintStream.println(localStringBuilder.toString());
      this.sqLiteHelper.insertExceptionRecord(paramBundle.toString(), this.df.format(localCalendar.getTime()));
    }
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (paramMenuItem.getItemId() != 16908332) {
      return super.onOptionsItemSelected(paramMenuItem);
    }
    finish();
    return true;
  }
  
  public void onRemoveClickListner(int paramInt, String paramString)
  {
    try
    {
      localObject2 = this.prefManager.getAdditionalPackages();
      if ((localObject2 != null) && (!"".equals(localObject2)))
      {
        localStringBuilder = new StringBuilder();
        localStringBuilder.append(paramString);
        localStringBuilder.append("@");
        localObject1 = localObject2;
        if (((String)localObject2).contains(localStringBuilder.toString()))
        {
          localObject1 = new StringBuilder();
          ((StringBuilder)localObject1).append(paramString);
          ((StringBuilder)localObject1).append("@");
          localObject1 = ((String)localObject2).replace(((StringBuilder)localObject1).toString(), "");
          this.prefManager.setAdditionalPackageCountMinus();
        }
        this.prefManager.setFullPkgList((String)localObject1);
        loadAdditionalApps();
        return;
      }
    }
    catch (Exception paramString)
    {
      Object localObject1 = Calendar.getInstance();
      Object localObject2 = System.out;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("issue => ");
      localStringBuilder.append(paramString.toString());
      localStringBuilder.append(" Current time => ");
      localStringBuilder.append(((Calendar)localObject1).getTime());
      ((PrintStream)localObject2).println(localStringBuilder.toString());
      localObject2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
      this.sqLiteHelper.insertExceptionRecord(paramString.toString(), ((SimpleDateFormat)localObject2).format(((Calendar)localObject1).getTime()));
    }
  }
  
  protected void onResume()
  {
    super.onResume();
    loadAdditionalApps();
  }
}
