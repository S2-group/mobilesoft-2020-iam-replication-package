package com.weaponsounds.gunsound.arma.weapons;

import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.AdView;
import com.startapp.android.publish.adsCommon.Ad;
import com.startapp.android.publish.adsCommon.StartAppAd;
import com.startapp.android.publish.adsCommon.StartAppSDK;
import com.startapp.android.publish.adsCommon.adListeners.AdDisplayListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pistol
  extends Activity
{
  private static int index;
  private static int index1;
  private static final String[] strs = { "Share App", "Rate App", "More App" };
  private AdView adView;
  private ImageButton back;
  private int count;
  private com.google.android.gms.ads.InterstitialAd interstitial;
  private com.facebook.ads.InterstitialAd interstitialAd = null;
  private int k;
  private ListView listView;
  private ListViewAdapter listViewAdapter;
  private More mMoreapp;
  private Weapon mWeapon;
  private MediaPlayer mp;
  private Intent myIntent;
  private List<Map<String, Object>> mylist;
  private ImageButton rate;
  private ImageButton setting;
  private ImageButton share;
  private List<Map<String, Object>> splitList;
  private StartAppAd startAppAd = new StartAppAd(this);
  private Typeface typeFace;
  
  public Pistol() {}
  
  private void initAD()
  {
    this.interstitial = new com.google.android.gms.ads.InterstitialAd(this);
    this.interstitial.setAdUnitId("ca-app-pub-1046678216386071/2215310391");
    AdRequest localAdRequest = new AdRequest.Builder().build();
    this.interstitial.loadAd(localAdRequest);
    this.interstitial.setAdListener(new AdListener()
    {
      public void onAdClosed()
      {
        Pistol.this.startActivity(Pistol.this.myIntent);
      }
      
      public void onAdFailedToLoad(int paramAnonymousInt) {}
      
      public void onAdLoaded() {}
    });
  }
  
  private boolean isAvilible(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    int i = 0;
    paramContext = paramContext.getInstalledPackages(0);
    ArrayList localArrayList = new ArrayList();
    if (paramContext != null) {
      while (i < paramContext.size())
      {
        localArrayList.add(((PackageInfo)paramContext.get(i)).packageName);
        i += 1;
      }
    }
    return localArrayList.contains(paramString);
  }
  
  private void loaddata()
  {
    this.mylist = new ArrayList();
    this.splitList = new ArrayList();
    setdata();
    this.listViewAdapter = new ListViewAdapter(this, this.mylist, this.splitList);
    this.listView.setAdapter(this.listViewAdapter);
  }
  
  private void promtapp()
  {
    int i = 0;
    while (i < MoreappData.mores.length)
    {
      this.mMoreapp = MoreappData.mores[i];
      if (!isAvilible(this, this.mMoreapp.mUri))
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("https://play.google.com/store/apps/details?id=");
        ((StringBuilder)localObject).append(this.mMoreapp.mUri);
        localObject = new Intent("android.intent.action.VIEW", Uri.parse(((StringBuilder)localObject).toString()));
      }
      try
      {
        startActivity((Intent)localObject);
      }
      catch (ActivityNotFoundException localActivityNotFoundException)
      {
        for (;;) {}
      }
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append("market://details?id=");
      ((StringBuilder)localObject).append(this.mMoreapp.mUri);
      startActivity(new Intent("android.intent.action.VIEW", Uri.parse(((StringBuilder)localObject).toString())));
      i = MoreappData.mores.length;
      i += 1;
    }
  }
  
  private void rate()
  {
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("market://details?id=");
    ((StringBuilder)localObject).append(getPackageName());
    localObject = new Intent("android.intent.action.VIEW", Uri.parse(((StringBuilder)localObject).toString()));
    try
    {
      startActivity((Intent)localObject);
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      for (;;) {}
    }
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("https://play.google.com/store/apps/details?id=");
    ((StringBuilder)localObject).append(getPackageName());
    startActivity(new Intent("android.intent.action.VIEW", Uri.parse(((StringBuilder)localObject).toString())));
  }
  
  private void selectDownload()
  {
    Typeface.createFromAsset(getAssets(), "fonts/distress.ttf");
    final Dialog localDialog = new Dialog(this, 2131493223);
    localDialog.setContentView(2131296318);
    Object localObject1 = localDialog.getWindow();
    Object localObject2 = ((Window)localObject1).getAttributes();
    ((WindowManager.LayoutParams)localObject2).alpha = 1.0F;
    ((Window)localObject1).setGravity(17);
    ((Window)localObject1).setWindowAnimations(2131493228);
    ((Window)localObject1).getDecorView().setBackgroundResource(2131099806);
    ((Window)localObject1).setAttributes((WindowManager.LayoutParams)localObject2);
    localDialog.setCanceledOnTouchOutside(false);
    localDialog.getWindow().setLayout(-2, -2);
    localObject1 = (ListView)localDialog.findViewById(2131165299);
    localObject2 = (Button)localDialog.findViewById(2131165256);
    ((ListView)localObject1).setAdapter(new ArrayAdapter(this, 17367043, strs));
    localDialog.show();
    ((Button)localObject2).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        Pistol.this.playSound(0);
        localDialog.dismiss();
      }
    });
    ((ListView)localObject1).setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        if (paramAnonymousInt == 0)
        {
          Pistol.this.playSound(0);
          Pistol.this.share();
        }
        else if (paramAnonymousInt == 1)
        {
          Pistol.this.playSound(0);
          Pistol.this.rate();
        }
        else if (paramAnonymousInt == 2)
        {
          Pistol.this.playSound(0);
          Pistol.this.promtapp();
        }
        localDialog.dismiss();
      }
    });
  }
  
  private void setdata()
  {
    this.k = PistolData.weapons.length;
    int i = 0;
    while (i < this.k)
    {
      this.mWeapon = PistolData.weapons[i];
      HashMap localHashMap = new HashMap();
      localHashMap.put("tFlag", "p");
      localHashMap.put("image", Integer.valueOf(this.mWeapon.mIconResId));
      localHashMap.put("title", this.mWeapon.mName);
      localHashMap.put("info", this.mWeapon.mInfo);
      if (i == index) {
        localHashMap.put("aim", Integer.valueOf(2131099741));
      } else {
        localHashMap.put("aim", Integer.valueOf(2131099740));
      }
      this.mylist.add(localHashMap);
      i += 1;
    }
  }
  
  private void share()
  {
    Intent localIntent = new Intent("android.intent.action.SEND");
    localIntent.setType("text/plain");
    localIntent.putExtra("android.intent.extra.SUBJECT", getString(2131427389));
    localIntent.putExtra("android.intent.extra.TEXT", "Example text");
    startActivity(Intent.createChooser(localIntent, getString(2131427388)));
  }
  
  private void showstartAD()
  {
    this.startAppAd.showAd(new AdDisplayListener()
    {
      public void adClicked(Ad paramAnonymousAd) {}
      
      public void adDisplayed(Ad paramAnonymousAd) {}
      
      public void adHidden(Ad paramAnonymousAd)
      {
        Pistol.this.startActivity(Pistol.this.myIntent);
      }
      
      public void adNotDisplayed(Ad paramAnonymousAd)
      {
        Pistol.this.startActivity(Pistol.this.myIntent);
      }
    });
  }
  
  public void onBackPressed()
  {
    this.startAppAd.onBackPressed();
    super.onBackPressed();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    setTheme(2131493139);
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    getWindow().setFlags(1024, 1024);
    StartAppSDK.init(this, "209253561", true);
    setContentView(2131296291);
    this.adView = ((AdView)findViewById(2131165220));
    paramBundle = new AdRequest.Builder().build();
    this.adView.loadAd(paramBundle);
    initAD();
    getIntent();
    this.typeFace = Typeface.createFromAsset(getAssets(), "fonts/gridview.ttf");
    this.listView = ((ListView)findViewById(2131165295));
    this.back = ((ImageButton)findViewById(2131165232));
    this.share = ((ImageButton)findViewById(2131165357));
    this.rate = ((ImageButton)findViewById(2131165331));
    this.setting = ((ImageButton)findViewById(2131165356));
    loaddata();
    this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        Pistol.access$008(Pistol.this);
        Pistol.access$102(paramAnonymousInt);
        Pistol.access$202(Pistol.this.listView.getFirstVisiblePosition());
        Pistol.this.loaddata();
        Pistol.this.listViewAdapter.notifyDataSetChanged();
        Pistol.this.listView.setSelection(Pistol.index1);
        Pistol.access$602(Pistol.this, new Intent());
        Pistol.this.myIntent.setClass(Pistol.this, Pistolplay.class);
        Pistol.this.myIntent.putExtra("Pistol_id", Pistol.index);
        if ((Pistol.this.count + 1) % 2 == 0)
        {
          if (Pistol.this.interstitial.isLoaded())
          {
            Pistol.this.interstitial.show();
            return;
          }
          Pistol.this.showstartAD();
          return;
        }
        paramAnonymousAdapterView = new AdRequest.Builder().build();
        Pistol.this.interstitial.loadAd(paramAnonymousAdapterView);
        Pistol.this.startAppAd.loadAd();
        Pistol.this.startActivity(Pistol.this.myIntent);
      }
    });
    this.listView.setOnFocusChangeListener(new View.OnFocusChangeListener()
    {
      public void onFocusChange(View paramAnonymousView, boolean paramAnonymousBoolean)
      {
        Pistol.this.listView.setSelection(Pistol.index);
      }
    });
    this.back.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        Pistol.this.finish();
      }
    });
    this.share.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        Pistol.this.share();
      }
    });
    this.rate.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        Pistol.this.rate();
      }
    });
    this.setting.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        Pistol.this.selectDownload();
      }
    });
  }
  
  protected void onDestroy()
  {
    if (this.interstitialAd != null) {
      this.interstitialAd.destroy();
    }
    if (this.mp != null)
    {
      this.mp.stop();
      this.mp.reset();
      this.mp.release();
      this.mp = null;
    }
    super.onDestroy();
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if ((paramInt == 4) && (paramKeyEvent.getRepeatCount() == 0))
    {
      finish();
      return true;
    }
    return true;
  }
  
  protected void onPause()
  {
    super.onPause();
    this.startAppAd.onPause();
  }
  
  protected void onResume()
  {
    super.onResume();
    this.startAppAd.onResume();
  }
  
  public void playSound(int paramInt)
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        if (Pistol.this.mp != null)
        {
          Pistol.this.mp.stop();
          Pistol.this.mp.reset();
          Pistol.this.mp.release();
          Pistol.access$1402(Pistol.this, null);
        }
      }
    });
    if (paramInt != 0) {
      return;
    }
    try
    {
      this.mp = MediaPlayer.create(this, 2131361809);
      this.mp.start();
      return;
    }
    catch (IllegalStateException localIllegalStateException)
    {
      localIllegalStateException.printStackTrace();
      return;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      localIllegalArgumentException.printStackTrace();
    }
  }
  
  private class ListViewAdapter
    extends BaseAdapter
  {
    private Context context;
    private LayoutInflater listContainer;
    private List<Map<String, Object>> listData;
    private List<String> listTag = null;
    private List<Map<String, Object>> splitData;
    private Typeface typeFace;
    
    public ListViewAdapter(List<Map<String, Object>> paramList1, List<Map<String, Object>> paramList2)
    {
      this.context = paramList1;
      this.listContainer = LayoutInflater.from(paramList1);
      this.listData = paramList2;
      Object localObject;
      this.splitData = localObject;
      this.typeFace = Typeface.createFromAsset(Pistol.this.getAssets(), "fonts/gridview.ttf");
    }
    
    public int getCount()
    {
      return this.listData.size();
    }
    
    public Object getItem(int paramInt)
    {
      return this.listData.get(paramInt);
    }
    
    public long getItemId(int paramInt)
    {
      return paramInt;
    }
    
    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      paramViewGroup = new ListItemView();
      if (paramView == null)
      {
        paramView = this.listContainer.inflate(2131296294, null);
        paramViewGroup.tflag = ((TextView)paramView.findViewById(2131165376));
        paramViewGroup.image = ((ImageView)paramView.findViewById(2131165187));
        paramViewGroup.title = ((TextView)paramView.findViewById(2131165189));
        paramViewGroup.info = ((TextView)paramView.findViewById(2131165190));
        paramViewGroup.aim = ((ImageView)paramView.findViewById(2131165225));
        paramView.setTag(paramViewGroup);
      }
      else
      {
        paramViewGroup = (ListItemView)paramView.getTag();
      }
      paramViewGroup.tflag.setText((String)((Map)this.listData.get(paramInt)).get("tFlag"));
      paramViewGroup.image.setBackgroundResource(((Integer)((Map)this.listData.get(paramInt)).get("image")).intValue());
      paramViewGroup.title.setText((String)((Map)this.listData.get(paramInt)).get("title"));
      paramViewGroup.info.setText((String)((Map)this.listData.get(paramInt)).get("info"));
      paramViewGroup.aim.setBackgroundResource(((Integer)((Map)this.listData.get(paramInt)).get("aim")).intValue());
      paramViewGroup.title.setTypeface(this.typeFace);
      paramViewGroup.info.setTypeface(this.typeFace);
      return paramView;
    }
    
    public boolean isEnabled(int paramInt)
    {
      if (this.splitData.contains(this.listData.get(paramInt))) {
        return false;
      }
      return super.isEnabled(paramInt);
    }
    
    public final class ListItemView
    {
      public ImageView aim;
      public ImageView image;
      public TextView info;
      public TextView tag;
      public TextView tflag;
      public TextView title;
      
      public ListItemView() {}
    }
  }
}
