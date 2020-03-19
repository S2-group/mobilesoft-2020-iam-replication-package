package com.newegg.app.activity.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.neweggjson.JsonParseException;
import com.newegg.app.activity.base.ClientActivity;
import com.newegg.app.activity.base.NeweggApp;
import com.newegg.app.activity.browse.TabStore;
import com.newegg.app.activity.combo.ComboBundleDetail;
import com.newegg.app.activity.combo.ComboDealsDetail;
import com.newegg.app.activity.myaccount.LogOut;
import com.newegg.app.activity.myaccount.Login;
import com.newegg.app.activity.product.DailyDeals;
import com.newegg.app.activity.product.ProductDetail;
import com.newegg.app.activity.product.SearchDefault;
import com.newegg.app.activity.product.SearchList;
import com.newegg.app.activity.product.SeeAllDeals;
import com.newegg.app.entity.browse.StoreContentInputInfo;
import com.newegg.app.entity.coremetrics.TechnicalPropertiesTag;
import com.newegg.app.entity.home.ShoppingGuideConfigEntity;
import com.newegg.app.entity.home.ShoppingGuideEntity;
import com.newegg.app.entity.home.SpotLightEntity;
import com.newegg.app.entity.product.DailyDealEntity;
import com.newegg.app.entity.product.ProductSearchAdvancedConditionInfo;
import com.newegg.app.entity.product.ShellShockerEntity;
import com.newegg.app.ui.adapters.DailyDealsAdapter;
import com.newegg.app.ui.adapters.PageControllAdapter;
import com.newegg.app.ui.adapters.ShellShockerAdapter;
import com.newegg.app.ui.widgets.CoverFlow;
import com.newegg.app.ui.widgets.PlaceHolderImageView;
import com.newegg.app.util.IntentUtil;
import com.newegg.app.webservices.ProductService;
import com.newegg.app.webservices.WebException;
import com.newegg.app.webservices.WebServiceAsyncTask;
import com.newegg.framework.cache.CacheStore;
import com.newegg.framework.util.StringUtil;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Home
  extends ClientActivity
  implements View.OnClickListener, AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener
{
  private Timer dailyDealItemDisplayTimer;
  private TimerTask dailyDealItemDisplayTimertask;
  private HorizontalScrollView dailyDealScrollView;
  private int dailyDealScrollWidth = 80;
  private int dailyDealcurrentIndex = 0;
  private List<DailyDealEntity> dailyDeals;
  Handler handler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      switch (paramAnonymousMessage.what)
      {
      }
      do
      {
        return;
        if (Home.this.dailyDealScrollView.hasFocus()) {
          Home.this.isDailyDealsStartScroll = false;
        }
      } while (!Home.this.isDailyDealsStartScroll);
      if (Home.this.dailyDealcurrentIndex < Home.this.dailyDeals.size() - 4)
      {
        paramAnonymousMessage = Home.this;
        paramAnonymousMessage.dailyDealcurrentIndex += 1;
        Home.this.dailyDealScrollView.smoothScrollTo(Home.this.dailyDealcurrentIndex * Home.this.dailyDealScrollWidth, 0);
        return;
      }
      Home.this.dailyDealItemDisplayTimer.cancel();
      Home.this.dailyDealItemDisplayTimertask.cancel();
    }
  };
  private boolean isDailyDealsStartScroll = true;
  private int lastCenterShellShockerPosition;
  private ShellShockerAdapter shellShockerAdapter;
  private CoverFlow shellShockerCoverFlow;
  private PageControllAdapter shellShockerPageControllAdapter;
  private List<ShellShockerEntity> shellShockers;
  private ShoppingGuideConfigEntity shoppingGuideConfig;
  private SpotLightEntity spotLight;
  
  public Home() {}
  
  private boolean isActivityExist(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    ArrayList localArrayList = new ArrayList();
    int i;
    if (paramContext != null) {
      i = 0;
    }
    for (;;)
    {
      if (i >= paramContext.size()) {
        return localArrayList.contains(paramString);
      }
      localArrayList.add(((PackageInfo)paramContext.get(i)).packageName);
      i += 1;
    }
  }
  
  private boolean isCameraCanUse()
  {
    try
    {
      PackageManager localPackageManager = getPackageManager();
      boolean bool = ((Boolean)PackageManager.class.getMethod("hasSystemFeature", new Class[] { String.class }).invoke(localPackageManager, new Object[] { "android.hardware.camera" })).booleanValue();
      return bool;
    }
    catch (Exception localException)
    {
      if (getPackageManager().queryIntentActivities(new Intent("android.media.action.IMAGE_CAPTURE"), 65536).size() > 0) {
        return true;
      }
    }
    return false;
  }
  
  private boolean isVoiceCanUse()
  {
    return NeweggApp.isIntentAvailable(new Intent("android.speech.action.RECOGNIZE_SPEECH"));
  }
  
  private void layoutContentView()
  {
    setContentView(2130903072);
    layoutNavigationBar();
    layoutShellShocker();
    layoutSpotLight();
    layoutDailyDeals();
    layoutShoppingGuide();
    layoutLogin();
    layoutLogout();
  }
  
  private void layoutDailyDeals()
  {
    ((PlaceHolderImageView)findViewById(2131427515)).setImageUrl("http://promotions.newegg.com/AppWorld/mobile/Android/dailydeals.png");
    if ((this.dailyDeals == null) || (this.dailyDeals.size() == 0)) {
      return;
    }
    ((LinearLayout)findViewById(2131427514)).setOnClickListener(this);
    this.dailyDealScrollView = ((HorizontalScrollView)findViewById(2131427517));
    this.dailyDealScrollView.setOnTouchListener(new View.OnTouchListener()
    {
      public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
      {
        Home.this.isDailyDealsStartScroll = false;
        return false;
      }
    });
    this.dailyDealcurrentIndex = 0;
    this.isDailyDealsStartScroll = true;
    int i = getScreenHeight();
    int j = getScreenWidth();
    if (j < i) {}
    for (this.dailyDealScrollWidth = (j / 4);; this.dailyDealScrollWidth = (j / 6))
    {
      LinearLayout localLinearLayout = (LinearLayout)findViewById(2131427518);
      localLinearLayout.removeAllViews();
      new DailyDealsAdapter(this.dailyDeals, this, this.dailyDealScrollWidth, this.dailyDealScrollWidth - 5).setDailyDealsLayout(localLinearLayout);
      localLinearLayout.setFocusable(false);
      startDailyDealyItemDisplayTimer();
      return;
    }
  }
  
  private void layoutLogin()
  {
    LinearLayout localLinearLayout = (LinearLayout)findViewById(2131427510);
    localLinearLayout.setOnClickListener(this);
    if (isLogined())
    {
      localLinearLayout.setVisibility(8);
      return;
    }
    localLinearLayout.setVisibility(0);
  }
  
  private void layoutLogout()
  {
    LinearLayout localLinearLayout = (LinearLayout)findViewById(2131427512);
    localLinearLayout.setOnClickListener(this);
    if (isLogined())
    {
      localLinearLayout.setVisibility(0);
      ((TextView)findViewById(2131427513)).setText(StringUtil.format(getString(2131230732), new Object[] { getAccountName() }));
      return;
    }
    localLinearLayout.setVisibility(8);
  }
  
  private void layoutNavigationBar()
  {
    Button localButton1 = (Button)findViewById(2131427522);
    localButton1.setOnClickListener(this);
    Button localButton2 = (Button)findViewById(2131427523);
    localButton2.setOnClickListener(this);
    Button localButton3 = (Button)findViewById(2131427525);
    localButton3.setOnClickListener(this);
    Button localButton4 = (Button)findViewById(2131427527);
    localButton4.setOnClickListener(this);
    ImageView localImageView1 = (ImageView)findViewById(2131427524);
    ImageView localImageView2 = (ImageView)findViewById(2131427526);
    boolean bool1 = isCameraCanUse();
    boolean bool2 = isVoiceCanUse();
    setNavigationButtonParams(localButton1);
    setNavigationButtonParams(localButton2);
    if (bool2) {
      setNavigationButtonParams(localButton4);
    }
    while (bool1)
    {
      setNavigationButtonParams(localButton3);
      return;
      localButton4.setVisibility(8);
      localImageView2.setVisibility(8);
    }
    localButton3.setVisibility(8);
    localImageView1.setVisibility(8);
  }
  
  private void layoutShellShocker()
  {
    RelativeLayout localRelativeLayout = (RelativeLayout)findViewById(2131427490);
    if (this.shellShockers == null)
    {
      localRelativeLayout.setVisibility(8);
      return;
    }
    localRelativeLayout.setVisibility(0);
    this.shellShockerCoverFlow = ((CoverFlow)findViewById(2131427491));
    this.shellShockerAdapter = new ShellShockerAdapter(this, this.shellShockers);
    this.shellShockerCoverFlow.setAdapter(this.shellShockerAdapter);
    this.shellShockerCoverFlow.setOnItemClickListener(this);
    int j = 0;
    int i = 0;
    for (;;)
    {
      if (i >= this.shellShockers.size())
      {
        this.shellShockerCoverFlow.setSelection(this.shellShockerAdapter.getStratIndex(j));
        this.lastCenterShellShockerPosition = this.shellShockerAdapter.getStratIndex(0);
        this.shellShockerCoverFlow.setOnItemSelectedListener(this);
        this.shellShockerPageControllAdapter = new PageControllAdapter(this, (LinearLayout)findViewById(2131427492), this.shellShockers.size());
        return;
      }
      if (((ShellShockerEntity)this.shellShockers.get(i)).getShellShockerStatusType() == 2) {
        j = i;
      }
      i += 1;
    }
  }
  
  private void layoutShopping0()
  {
    Object localObject = (LinearLayout)findViewById(2131427501);
    if (this.shoppingGuideConfig.getShoppingGuideList().size() < 1) {
      ((LinearLayout)localObject).setVisibility(8);
    }
    do
    {
      return;
      ((LinearLayout)localObject).setOnClickListener(this);
      ((LinearLayout)localObject).setVisibility(0);
      localObject = (ShoppingGuideEntity)this.shoppingGuideConfig.getShoppingGuideList().get(0);
      String str = ((ShoppingGuideEntity)localObject).getTitle();
      if (!StringUtil.isEmpty(str)) {
        ((TextView)findViewById(2131427502)).setText(str);
      }
      localObject = ((ShoppingGuideEntity)localObject).getDescription();
    } while (StringUtil.isEmpty((String)localObject));
    ((TextView)findViewById(2131427503)).setText((CharSequence)localObject);
  }
  
  private void layoutShopping1()
  {
    Object localObject = (LinearLayout)findViewById(2131427504);
    if (this.shoppingGuideConfig.getShoppingGuideList().size() < 2) {
      ((LinearLayout)localObject).setVisibility(8);
    }
    do
    {
      return;
      ((LinearLayout)localObject).setOnClickListener(this);
      ((LinearLayout)localObject).setVisibility(0);
      localObject = (ShoppingGuideEntity)this.shoppingGuideConfig.getShoppingGuideList().get(1);
      String str = ((ShoppingGuideEntity)localObject).getTitle();
      if (!StringUtil.isEmpty(str)) {
        ((TextView)findViewById(2131427505)).setText(str);
      }
      localObject = ((ShoppingGuideEntity)localObject).getDescription();
    } while (StringUtil.isEmpty((String)localObject));
    ((TextView)findViewById(2131427506)).setText((CharSequence)localObject);
  }
  
  private void layoutShopping2()
  {
    Object localObject = (LinearLayout)findViewById(2131427507);
    if (this.shoppingGuideConfig.getShoppingGuideList().size() < 3) {
      ((LinearLayout)localObject).setVisibility(8);
    }
    do
    {
      return;
      ((LinearLayout)localObject).setOnClickListener(this);
      ((LinearLayout)localObject).setVisibility(0);
      localObject = (ShoppingGuideEntity)this.shoppingGuideConfig.getShoppingGuideList().get(2);
      String str = ((ShoppingGuideEntity)localObject).getTitle();
      if (!StringUtil.isEmpty(str)) {
        ((TextView)findViewById(2131427508)).setText(str);
      }
      localObject = ((ShoppingGuideEntity)localObject).getDescription();
    } while (StringUtil.isEmpty((String)localObject));
    ((TextView)findViewById(2131427509)).setText((CharSequence)localObject);
  }
  
  private void layoutShoppingGuide()
  {
    LinearLayout localLinearLayout = (LinearLayout)findViewById(2131427500);
    if (this.shoppingGuideConfig == null)
    {
      localLinearLayout.setVisibility(8);
      return;
    }
    localLinearLayout.setVisibility(0);
    layoutShopping0();
    layoutShopping1();
    layoutShopping2();
  }
  
  private void layoutSpotLight()
  {
    ((PlaceHolderImageView)findViewById(2131427496)).setImageUrl("http://promotions.newegg.com/AppWorld/mobile/Android/eggxtra.png");
    Object localObject = (LinearLayout)findViewById(2131427493);
    if (this.spotLight == null)
    {
      ((LinearLayout)localObject).setVisibility(8);
      ((LinearLayout)localObject).setOnClickListener(null);
      return;
    }
    ((LinearLayout)localObject).setVisibility(0);
    ((LinearLayout)localObject).setOnClickListener(this);
    ((PlaceHolderImageView)findViewById(2131427494)).setImageUrl(this.spotLight.getProductImageUrl());
    localObject = (ImageView)findViewById(2131427495);
    if (this.spotLight.isHot())
    {
      ((ImageView)localObject).setVisibility(0);
      localObject = (TextView)findViewById(2131427497);
      if (!StringUtil.isEmpty(this.spotLight.getProductTitle())) {
        break label226;
      }
      ((TextView)localObject).setVisibility(8);
      label132:
      localObject = (TextView)findViewById(2131427498);
      if (!this.spotLight.isShowOriginalPrice()) {
        break label245;
      }
      ((TextView)localObject).setVisibility(0);
      ((TextView)localObject).setText("was " + this.spotLight.getOriginalPrice());
      ((TextView)localObject).setPaintFlags(((TextView)localObject).getPaintFlags() | 0x10);
    }
    for (;;)
    {
      ((TextView)findViewById(2131427499)).setText(this.spotLight.getFinalPrice());
      return;
      ((ImageView)localObject).setVisibility(8);
      break;
      label226:
      ((TextView)localObject).setVisibility(0);
      ((TextView)localObject).setText(this.spotLight.getProductTitle());
      break label132;
      label245:
      ((TextView)localObject).setVisibility(8);
    }
  }
  
  private void onShellShockerCoverFlowItemSelected(int paramInt)
  {
    this.lastCenterShellShockerPosition = paramInt;
    int i = this.shellShockers.size();
    this.shellShockerPageControllAdapter.setPageIndex(paramInt % i);
  }
  
  private void onShellShockerItemClick(int paramInt)
  {
    if (paramInt != this.lastCenterShellShockerPosition) {
      this.lastCenterShellShockerPosition = paramInt;
    }
    Intent localIntent;
    do
    {
      return;
      int i = this.shellShockers.size();
      ShellShockerEntity localShellShockerEntity = (ShellShockerEntity)this.shellShockers.get(paramInt % i);
      localIntent = null;
      if ((localShellShockerEntity.getShellShockerItemType() == 0) || (localShellShockerEntity.getShellShockerItemType() == 3))
      {
        localIntent = new Intent(this, ProductDetail.class);
        localIntent.putExtra("com.newegg.app.activity.product.ItemNumber", localShellShockerEntity.getShellShockItemNumber());
      }
      Bundle localBundle;
      if (localShellShockerEntity.getShellShockerItemType() == 1)
      {
        localIntent = new Intent(this, ComboDealsDetail.class);
        localBundle = new Bundle();
        localBundle.putBoolean("BUNDLE_BOOLEAN_IS_COME_FROM_SHELLSHOCKER", true);
        localIntent.putExtras(localBundle);
        localIntent.putExtra("com.newegg.app.activity.combo.ComboId", Integer.valueOf(localShellShockerEntity.getShellShockItemNumber()));
      }
      if (localShellShockerEntity.getShellShockerItemType() == 2)
      {
        localIntent = new Intent(this, ComboBundleDetail.class);
        localBundle = new Bundle();
        localBundle.putBoolean("BUNDLE_BOOLEAN_IS_COME_FROM_SHELLSHOCKER", true);
        localIntent.putExtras(localBundle);
        localIntent.putExtra("com.newegg.app.activity.combo.ComboId", Integer.valueOf(localShellShockerEntity.getShellShockItemNumber()));
      }
    } while (localIntent == null);
    startActivity(localIntent);
  }
  
  private void requestDailyDealsServiceData()
  {
    new WebServiceAsyncTask(this)
    {
      protected List<DailyDealEntity> callService()
        throws IOException, JsonParseException, WebException
      {
        return ProductService.self().getDailyDealsData();
      }
      
      protected void runWithResult(List<DailyDealEntity> paramAnonymousList)
      {
        if (paramAnonymousList != null)
        {
          Home.this.dailyDeals = paramAnonymousList;
          ((LinearLayout)Home.this.findViewById(2131427489)).setVisibility(0);
          Home.this.layoutDailyDeals();
        }
        Home.this.hiddenLoadding();
      }
    }.execute(new Void[0]);
  }
  
  private void requestShellShockersData()
  {
    new WebServiceAsyncTask(this)
    {
      protected List<ShellShockerEntity> callService()
        throws IOException, JsonParseException, WebException
      {
        return ProductService.self().getShellShockersData();
      }
      
      protected void runWithResult(List<ShellShockerEntity> paramAnonymousList)
        throws Exception
      {
        Home.this.shellShockers = paramAnonymousList;
        Home.this.layoutShellShocker();
      }
    }.execute(new Void[0]);
  }
  
  private void requestShoppingGuideCongfigServerData()
  {
    new WebServiceAsyncTask(this)
    {
      protected ShoppingGuideConfigEntity callService()
        throws IOException, JsonParseException, WebException
      {
        return ProductService.self().getShoppingGuideConfigData();
      }
      
      protected void runWithResult(ShoppingGuideConfigEntity paramAnonymousShoppingGuideConfigEntity)
        throws Exception
      {
        if (paramAnonymousShoppingGuideConfigEntity != null)
        {
          Home.this.shoppingGuideConfig = paramAnonymousShoppingGuideConfigEntity;
          Home.this.layoutShoppingGuide();
        }
      }
    }.execute(new Void[0]);
  }
  
  private void requestSpotlightServerData()
  {
    new WebServiceAsyncTask(this)
    {
      protected SpotLightEntity callService()
        throws IOException, JsonParseException, WebException
      {
        return ProductService.self().getSpotLightData();
      }
      
      protected void runWithResult(SpotLightEntity paramAnonymousSpotLightEntity)
        throws Exception
      {
        if (paramAnonymousSpotLightEntity != null)
        {
          Home.this.spotLight = paramAnonymousSpotLightEntity;
          Home.this.layoutSpotLight();
        }
      }
    }.execute(new Void[0]);
  }
  
  private void sendTechnicalPropertiesTag()
  {
    TechnicalPropertiesTag localTechnicalPropertiesTag = new TechnicalPropertiesTag("Http");
    localTechnicalPropertiesTag.setPageID("Homepage");
    localTechnicalPropertiesTag.setCategoryID("ADG10");
    localTechnicalPropertiesTag.sendTagRequest();
  }
  
  private void setNavigationButtonParams(Button paramButton)
  {
    paramButton.setTextColor(-16777216);
    int i = getScreenHeight();
    if (getScreenWidth() < i)
    {
      if (i < 480)
      {
        paramButton.setPadding(0, 20, 0, 0);
        return;
      }
      if (i > 480)
      {
        paramButton.setPadding(0, 45, 0, 0);
        return;
      }
      paramButton.setPadding(0, 30, 0, 0);
      return;
    }
    if (i < 300)
    {
      paramButton.setPadding(0, 20, 0, 0);
      return;
    }
    paramButton.setPadding(0, 40, 0, 0);
  }
  
  private void shoppingGuideOnItemClick(int paramInt)
  {
    ShoppingGuideEntity localShoppingGuideEntity = (ShoppingGuideEntity)this.shoppingGuideConfig.getShoppingGuideList().get(paramInt);
    Object localObject = localShoppingGuideEntity.getDataAccessMethod();
    Intent localIntent = null;
    if (((String)localObject).contains("store")) {
      if (localShoppingGuideEntity.getStore() != null) {}
    }
    label248:
    for (;;)
    {
      return;
      localIntent = new Intent(this, SeeAllDeals.class);
      localObject = new Bundle();
      ((Bundle)localObject).putSerializable("com.newegg.app.activity.browse.StoreContentInput", new StoreContentInputInfo(localShoppingGuideEntity.getStore()));
      ((Bundle)localObject).putString("com.newegg.app.activity.browse.Title", localShoppingGuideEntity.getTitle());
      localIntent.putExtras((Bundle)localObject);
      for (;;)
      {
        if (localIntent == null) {
          break label248;
        }
        startActivity(localIntent);
        return;
        if (((String)localObject).contains("search"))
        {
          if (localShoppingGuideEntity.getSearch() == null) {
            break;
          }
          localIntent = new Intent(this, SearchList.class);
          localIntent.putExtra("com.newegg.app.activity.product.SearchListKey", new ProductSearchAdvancedConditionInfo(localShoppingGuideEntity.getSearch()));
          localIntent.putExtra("BUNDLE_STRING_SHOPPING_GUIDE_TITLE", localShoppingGuideEntity.getTitle());
          continue;
        }
        if (((String)localObject).contains("xmlData"))
        {
          if (localShoppingGuideEntity.getXmlData() == null) {
            break;
          }
          localIntent = new Intent(this, SeeAllDeals.class);
          localObject = new Bundle();
          ((Bundle)localObject).putString("com.newegg.app.activity.browse.Title", localShoppingGuideEntity.getTitle());
          ((Bundle)localObject).putSerializable(SeeAllDeals.BUNDLE_SERIALIZABLE_XML_DATA, localShoppingGuideEntity.getXmlData());
          localIntent.putExtras((Bundle)localObject);
        }
      }
    }
  }
  
  private void spotLightOnClick()
  {
    String str = this.spotLight.getItemNumber();
    if (this.spotLight.isRegularCombo())
    {
      localIntent = new Intent(this, ComboDealsDetail.class);
      localIntent.putExtra("com.newegg.app.activity.combo.ComboId", Integer.valueOf(str));
      startActivity(localIntent);
      return;
    }
    if (this.spotLight.getIsCombo() == 1)
    {
      localIntent = new Intent(this, ComboBundleDetail.class);
      localIntent.putExtra("com.newegg.app.activity.combo.ComboId", Integer.valueOf(str));
      startActivity(localIntent);
      return;
    }
    Intent localIntent = new Intent(this, ProductDetail.class);
    localIntent.putExtra("com.newegg.app.activity.product.ItemNumber", str);
    startActivity(localIntent);
  }
  
  private void startBarcodeScanActivity()
  {
    if (isActivityExist(this, "com.google.zxing.client.android"))
    {
      sendConversionEventTag("BarcodeScan", "ADAppExclusive", "1", "3");
      sendPageViewTag("BarcodeScan", "Search");
      startActivityForResult(new Intent("com.google.zxing.client.android.SCAN"), 0);
      return;
    }
    Uri localUri = Uri.parse("market://details?id=com.google.zxing.client.android");
    try
    {
      startActivity(new Intent("android.intent.action.VIEW", localUri));
      return;
    }
    catch (Exception localException) {}
  }
  
  private void startDailyDealyItemDisplayTimer()
  {
    this.dailyDealItemDisplayTimertask = new TimerTask()
    {
      public void run()
      {
        Message localMessage = new Message();
        localMessage.what = 1;
        Home.this.handler.sendMessage(localMessage);
      }
    };
    this.dailyDealItemDisplayTimer = new Timer();
    this.dailyDealItemDisplayTimer.schedule(this.dailyDealItemDisplayTimertask, 5000L, 5000L);
  }
  
  private void startSearchListAvctivity(String paramString)
  {
    CacheStore.persistentStore().remove("NEWEGG_COM_SEARCHFILTER_SELECTOPTION");
    ProductSearchAdvancedConditionInfo localProductSearchAdvancedConditionInfo = ProductSearchAdvancedConditionInfo.instance();
    localProductSearchAdvancedConditionInfo.upcSearchConditionWithKeyword(paramString);
    IntentUtil.deliverToNextActivity(this, SearchList.class, "com.newegg.app.activity.product.SearchListKey", localProductSearchAdvancedConditionInfo);
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramInt1 != 0)
    {
      sendConversionEventTag("BarcodeScanFailed", "ADAppExclusive", "2", "0");
      return;
    }
    if (paramIntent != null)
    {
      startSearchListAvctivity(paramIntent.getStringExtra("SCAN_RESULT"));
      return;
    }
    sendConversionEventTag("BarcodeScanFailed", "ADAppExclusive", "2", "0");
  }
  
  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default: 
      return;
    case 2131427514: 
      IntentUtil.deliverToNextActivity(this, DailyDeals.class);
      return;
    case 2131427510: 
      NeweggApp.instace().setLoginBeforeCls(Home.class);
      IntentUtil.deliverToNextActivity(this, Login.class);
      return;
    case 2131427512: 
      IntentUtil.deliverToNextActivity(this, LogOut.class);
      return;
    case 2131427522: 
      IntentUtil.deliverToNextActivity(this, TabStore.class);
      return;
    case 2131427523: 
      IntentUtil.deliverToNextActivity(this, SearchDefault.class);
      return;
    case 2131427525: 
      startBarcodeScanActivity();
      return;
    case 2131427527: 
      IntentUtil.deliverToNextActivity(this, SearchDefault.class, "activity.Home", Boolean.valueOf(true));
      return;
    case 2131427493: 
      spotLightOnClick();
      return;
    case 2131427501: 
      shoppingGuideOnItemClick(0);
      return;
    case 2131427504: 
      shoppingGuideOnItemClick(1);
      return;
    }
    shoppingGuideOnItemClick(2);
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    this.shellShockerAdapter.setWindowOrientation(paramConfiguration.orientation);
    this.shellShockerCoverFlow.setSelection(this.shellShockerAdapter.getStratIndex(this.shellShockerPageControllAdapter.getPageIndex()));
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    layoutContentView();
    requestShellShockersData();
    requestShoppingGuideCongfigServerData();
    requestSpotlightServerData();
    sendTechnicalPropertiesTag();
  }
  
  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    switch (paramAdapterView.getId())
    {
    default: 
      return;
    }
    onShellShockerItemClick(paramInt);
  }
  
  public void onItemSelected(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    switch (paramAdapterView.getId())
    {
    default: 
      return;
    }
    onShellShockerCoverFlowItemSelected(paramInt);
  }
  
  public void onNothingSelected(AdapterView<?> paramAdapterView) {}
  
  protected void onStart()
  {
    super.onStart();
    showLoading();
    layoutLogin();
    layoutLogout();
    requestDailyDealsServiceData();
  }
}
