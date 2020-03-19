package in.myinnos.AppManager.ui;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SnapHelper;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.CustomEvent;
import com.crashlytics.android.answers.RatingEvent;
import com.crashlytics.android.answers.SearchEvent;
import com.crashlytics.android.answers.ShareEvent;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.miguelcatalan.materialsearchview.MaterialSearchView.OnQueryTextListener;
import com.miguelcatalan.materialsearchview.MaterialSearchView.SearchViewListener;
import com.onesignal.OSPermissionState;
import com.onesignal.OSPermissionSubscriptionState;
import com.onesignal.OSSubscriptionState;
import com.onesignal.OneSignal;
import in.myinnos.AppManager.AboutActivity;
import in.myinnos.AppManager.adapters.UserAppsAdapter;
import in.myinnos.AppManager.model.UserAppModel;
import in.myinnos.AppManager.utils.FastScroller.FastScrollRecyclerView;
import in.myinnos.AppManager.utils.HelperClass;
import in.myinnos.AppManager.utils.Remember.Remember;
import in.myinnos.AppManager.utils.recycleviewListners.GravitySnapHelper;
import in.myinnos.AppManager.utils.recycleviewListners.RecyclerItemClickListener;
import in.myinnos.AppManager.utils.recycleviewListners.RecyclerItemClickListener.OnItemClickListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class BaseActivity
  extends AppCompatActivity
{
  private Boolean ad_status = Boolean.valueOf(true);
  @BindView(2131230875)
  LinearLayout liNewAdView;
  private AdView mAdView;
  private InterstitialAd mInterstitialAd;
  private Menu menu;
  @BindView(2131231009)
  FastScrollRecyclerView recyclerView;
  @BindView(2131230940)
  MaterialSearchView searchView;
  @BindView(2131230987)
  Toolbar toolbar;
  private UserAppsAdapter userAppsAdapter;
  
  public BaseActivity() {}
  
  private List<UserAppModel> getAppsList()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = getPackageManager().getInstalledPackages(0).iterator();
    while (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      if ((localPackageInfo.applicationInfo.flags & 0x1) == 0)
      {
        UserAppModel localUserAppModel = new UserAppModel();
        localUserAppModel.setTitle(localPackageInfo.applicationInfo.loadLabel(getPackageManager()).toString());
        localUserAppModel.setPackageName(localPackageInfo.packageName);
        localUserAppModel.setIcon(localPackageInfo.applicationInfo.loadIcon(getPackageManager()));
        localArrayList.add(localUserAppModel);
      }
    }
    Collections.sort(localArrayList, new Comparator()
    {
      public int compare(UserAppModel paramAnonymousUserAppModel1, UserAppModel paramAnonymousUserAppModel2)
      {
        return paramAnonymousUserAppModel1.getTitle().compareToIgnoreCase(paramAnonymousUserAppModel2.getTitle());
      }
    });
    return localArrayList;
  }
  
  private void setupToolbar()
  {
    this.toolbar.setTitle(2131689639);
    setSupportActionBar(this.toolbar);
    if (getSupportActionBar() != null)
    {
      getSupportActionBar().setDisplayHomeAsUpEnabled(false);
      getSupportActionBar().setDisplayShowHomeEnabled(false);
      getSupportActionBar().setDisplayShowTitleEnabled(false);
    }
  }
  
  public void onBackPressed()
  {
    if (this.searchView.isSearchOpen())
    {
      this.searchView.closeSearch();
      return;
    }
    super.onBackPressed();
    overridePendingTransition(2130771985, 2130771986);
    finish();
  }
  
  protected void onCreate(@Nullable Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2131427357);
    ButterKnife.bind(this);
    setupToolbar();
    paramBundle = new JSONObject();
    try
    {
      paramBundle.put("App", "AppStore");
      OneSignal.sendTags(paramBundle);
    }
    catch (JSONException paramBundle)
    {
      paramBundle.printStackTrace();
    }
    paramBundle = OneSignal.getPermissionSubscriptionState();
    paramBundle.getPermissionStatus().getEnabled();
    paramBundle.getSubscriptionStatus().getSubscribed();
    paramBundle.getSubscriptionStatus().getUserSubscriptionSetting();
    paramBundle.getSubscriptionStatus().getUserId();
    paramBundle.getSubscriptionStatus().getPushToken();
    MobileAds.initialize(this, "ca-app-pub-8299650657845552~3108017839");
    paramBundle = new AdView(this);
    paramBundle.setAdSize(AdSize.BANNER);
    paramBundle.setAdUnitId("ca-app-pub-8299650657845552/8246660802");
    this.mAdView = ((AdView)findViewById(2131230758));
    paramBundle = new AdRequest.Builder().addTestDevice("B3EEABB8EE11C2BE770B684D95219ECB").addTestDevice("60238ac59d484062").addTestDevice("92584CF8EA59F54D3112E09BA665FA16").addTestDevice("4DA0BEB0888AB51A1A51D97945CCDF11").build();
    this.mAdView.loadAd(paramBundle);
    this.mInterstitialAd = new InterstitialAd(this);
    this.mInterstitialAd.setAdUnitId("ca-app-pub-8299650657845552/2135623610");
    this.mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice("B3EEABB8EE11C2BE770B684D95219ECB").addTestDevice("60238ac59d484062").addTestDevice("92584CF8EA59F54D3112E09BA665FA16").addTestDevice("4DA0BEB0888AB51A1A51D97945CCDF11").build());
    this.mInterstitialAd.setAdListener(new AdListener()
    {
      public void onAdClosed()
      {
        Log.d("Load Ad", "onAdClosed");
        if (BaseActivity.a(BaseActivity.this).booleanValue())
        {
          AdRequest localAdRequest = new AdRequest.Builder().addTestDevice("B3EEABB8EE11C2BE770B684D95219ECB").addTestDevice("60238ac59d484062").build();
          BaseActivity.b(BaseActivity.this).loadAd(localAdRequest);
        }
      }
      
      public void onAdFailedToLoad(int paramAnonymousInt)
      {
        Log.d("Load Ad", "onAdFailedToLoad");
      }
      
      public void onAdLoaded()
      {
        Log.d("Load Ad", "onAdLoaded");
      }
    });
    this.recyclerView.setHasFixedSize(true);
    this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
    this.userAppsAdapter = new UserAppsAdapter(getAppsList(), this.recyclerView);
    this.recyclerView.setAdapter(this.userAppsAdapter);
    this.recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener()
    {
      public void onItemClick(View paramAnonymousView, int paramAnonymousInt)
      {
        ImageView localImageView = (ImageView)paramAnonymousView.findViewById(2131230765);
        String str = BaseActivity.this.getString(2131689635);
        Remember.putString("APP_KEY", ((TextView)paramAnonymousView.findViewById(2131230766)).getText().toString().trim());
        if (BaseActivity.a(BaseActivity.this).booleanValue())
        {
          BaseActivity.a(BaseActivity.this, Boolean.valueOf(false));
        }
        else
        {
          BaseActivity.a(BaseActivity.this, Boolean.valueOf(true));
          if (BaseActivity.b(BaseActivity.this).isLoaded())
          {
            BaseActivity.b(BaseActivity.this).show();
            Answers.getInstance().logCustom((CustomEvent)((CustomEvent)new CustomEvent("Ad Viewed").putCustomAttribute("View", "Base")).putCustomAttribute("Ad Type", "Interstitial"));
          }
          else
          {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
          }
        }
        if (Build.VERSION.SDK_INT >= 21)
        {
          paramAnonymousView = ActivityOptions.makeSceneTransitionAnimation(BaseActivity.this, localImageView, str);
          BaseActivity.this.startActivity(new Intent(BaseActivity.this, InfoViewActivity.class), paramAnonymousView.toBundle());
          return;
        }
        BaseActivity.this.startActivity(new Intent(BaseActivity.this, InfoViewActivity.class));
      }
    }));
    new GravitySnapHelper(48).attachToRecyclerView(this.recyclerView);
    if (HelperClass.appInstalledOrNot("in.myinnos.batteryinfopro", this)) {
      this.liNewAdView.setVisibility(8);
    }
    setSearchView();
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(2131492865, paramMenu);
    paramMenu = paramMenu.findItem(2131230752);
    this.searchView.setMenuItem(paramMenu);
    return true;
  }
  
  public void onDestroy()
  {
    super.onDestroy();
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    int i = paramMenuItem.getItemId();
    if (i != 2131230734)
    {
      switch (i)
      {
      default: 
        return super.onOptionsItemSelected(paramMenuItem);
      case 2131230885: 
        HelperClass.shareApp(this);
        Answers.getInstance().logShare(new ShareEvent().putMethod("Menu").putContentId(HelperClass.getAndroidId(this)));
        return true;
      case 2131230884: 
        HelperClass.rateApp(this);
        Answers.getInstance().logRating(new RatingEvent().putRating(5).putContentId(HelperClass.getAndroidId(this)));
        return true;
      }
      startActivity(new Intent(this, AboutActivity.class));
      Answers.getInstance().logCustom((CustomEvent)new CustomEvent("About").putCustomAttribute("Android_Id", HelperClass.getAndroidId(this)));
    }
    return true;
  }
  
  public void onPause()
  {
    super.onPause();
  }
  
  public void onResume()
  {
    super.onResume();
  }
  
  @OnClick({2131230873})
  public void openBatteryBond()
  {
    HelperClass.batteryBondApp(this);
  }
  
  public void setSearchView()
  {
    this.searchView.setVoiceSearch(false);
    this.searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener()
    {
      public boolean onQueryTextChange(String paramAnonymousString)
      {
        BaseActivity.c(BaseActivity.this).getFilter().filter(paramAnonymousString);
        return false;
      }
      
      public boolean onQueryTextSubmit(String paramAnonymousString)
      {
        return false;
      }
    });
    this.searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener()
    {
      public void onSearchViewClosed() {}
      
      public void onSearchViewShown()
      {
        Answers.getInstance().logSearch((SearchEvent)new SearchEvent().putCustomAttribute("ANDROID_ID", HelperClass.getAndroidId(BaseActivity.this)));
      }
    });
  }
}
