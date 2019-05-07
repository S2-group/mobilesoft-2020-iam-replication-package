package com.weapons.guns100.weaponsound;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.ads.Ad;
import com.facebook.ads.AdChoicesView;
import com.facebook.ads.AdError;
import com.facebook.ads.AdIconView;
import com.facebook.ads.MediaView;
import com.facebook.ads.NativeAdListener;
import com.facebook.ads.NativeBannerAd;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.AdView;
import java.util.ArrayList;
import java.util.List;

public class Main
  extends AppCompatActivity
  implements View.OnClickListener
{
  private LinearLayout adView;
  private AdView adView1;
  private Dialog dialog;
  private NiftyDialogBuilder dialogBuilder;
  private DrawerLayout drawerLayout;
  private int i = 0;
  private Intent intent;
  private int j = 0;
  private CircleMenuLayout mCircleMenuLayout;
  private Intent mIntent;
  private int[] mItemImgs = { 2131231043, 2131231272, 2131231115, 2131230878, 2131231178, 2131231239, 2131231041, 2131231160, 2131231176, 2131231101 };
  private String[] mItemTexts = { "Lightsaber ", "Knife", "Missle", "Bomb", "RPG", "Stungun", "Lasergun", "Pistol", "Rifle", "Other" };
  private More mMoreapp;
  private ImageView menu;
  private Intent myIntent;
  private NativeBannerAd nativeBannerAd;
  private RelativeLayout nativeBannerAdContainer;
  private NavigationView navigationView;
  private int prot = 0;
  private SharedPreferences sp;
  
  public Main() {}
  
  private void ExitPrompt()
  {
    Typeface.createFromAsset(getAssets(), "fonts/AntiqueOLIVE.ttf");
    int k = 0;
    int m = 0;
    while (k < MoreappData.mores.length)
    {
      this.mMoreapp = MoreappData.mores[k];
      if (!isAvilible(this, this.mMoreapp.mUri))
      {
        this.dialog = new Dialog(this, 2131689869);
        this.dialog.setContentView(2131427374);
        Object localObject1 = this.dialog.getWindow();
        Object localObject2 = ((Window)localObject1).getAttributes();
        ((WindowManager.LayoutParams)localObject2).alpha = 1.0F;
        ((Window)localObject1).setGravity(17);
        ((Window)localObject1).setWindowAnimations(2131689875);
        ((Window)localObject1).getDecorView().setBackgroundResource(2131230934);
        ((Window)localObject1).setAttributes((WindowManager.LayoutParams)localObject2);
        this.dialog.setCanceledOnTouchOutside(false);
        this.dialog.getWindow().setLayout(-2, -2);
        Object localObject3 = (TextView)this.dialog.findViewById(2131296490);
        localObject1 = (ImageView)this.dialog.findViewById(2131296366);
        localObject2 = (ImageView)this.dialog.findViewById(2131296322);
        ((TextView)localObject3).setText(this.mMoreapp.mDis);
        ((ImageView)localObject1).setBackgroundResource(this.mMoreapp.mImgResId);
        ((ImageView)localObject2).setVisibility(0);
        localObject3 = (Button)this.dialog.findViewById(2131296263);
        Button localButton1 = (Button)this.dialog.findViewById(2131296262);
        Button localButton2 = (Button)this.dialog.findViewById(2131296340);
        localButton1.setText("Exit");
        this.dialog.show();
        ((ImageView)localObject1).setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            paramAnonymousView = new StringBuilder();
            paramAnonymousView.append("https://play.google.com/store/apps/details?id=");
            paramAnonymousView.append(Main.this.mMoreapp.mUri);
            paramAnonymousView = new Intent("android.intent.action.VIEW", Uri.parse(paramAnonymousView.toString()));
            try
            {
              Main.this.startActivity(paramAnonymousView);
            }
            catch (ActivityNotFoundException paramAnonymousView)
            {
              StringBuilder localStringBuilder;
              for (;;) {}
            }
            paramAnonymousView = Main.this;
            localStringBuilder = new StringBuilder();
            localStringBuilder.append("market://details?id=");
            localStringBuilder.append(Main.this.mMoreapp.mUri);
            paramAnonymousView.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(localStringBuilder.toString())));
            Main.this.dialog.dismiss();
          }
        });
        localButton2.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            paramAnonymousView = new StringBuilder();
            paramAnonymousView.append("https://play.google.com/store/apps/details?id=");
            paramAnonymousView.append(Main.this.mMoreapp.mUri);
            paramAnonymousView = new Intent("android.intent.action.VIEW", Uri.parse(paramAnonymousView.toString()));
            try
            {
              Main.this.startActivity(paramAnonymousView);
            }
            catch (ActivityNotFoundException paramAnonymousView)
            {
              StringBuilder localStringBuilder;
              for (;;) {}
            }
            paramAnonymousView = Main.this;
            localStringBuilder = new StringBuilder();
            localStringBuilder.append("market://details?id=");
            localStringBuilder.append(Main.this.mMoreapp.mUri);
            paramAnonymousView.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(localStringBuilder.toString())));
            Main.this.dialog.dismiss();
          }
        });
        ((Button)localObject3).setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            Main.this.rate();
            Main.this.dialog.dismiss();
          }
        });
        localButton1.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            Main.this.dialog.dismiss();
            Main.this.finish();
          }
        });
        ((ImageView)localObject2).setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            Main.this.dialog.dismiss();
          }
        });
        k = MoreappData.mores.length;
      }
      else
      {
        m += 1;
      }
      k += 1;
    }
    if (m == MoreappData.mores.length) {
      exitdialog();
    }
  }
  
  private void exitdialog()
  {
    new AlertDialog.Builder(this).setTitle("Prompt").setIcon(2131231016).setMessage("Are you sure to exit this game?").setPositiveButton("Yes", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        Main.this.finish();
      }
    }).setNeutralButton("Rate App", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        Main.this.rate();
      }
    }).setNegativeButton("No", null).create().show();
  }
  
  private void inflateAd(NativeBannerAd paramNativeBannerAd)
  {
    paramNativeBannerAd.unregisterView();
    this.nativeBannerAdContainer = ((RelativeLayout)findViewById(2131296401));
    Object localObject1 = LayoutInflater.from(this);
    Object localObject2 = this.nativeBannerAdContainer;
    int k = 0;
    this.adView = ((LinearLayout)((LayoutInflater)localObject1).inflate(2131427385, (ViewGroup)localObject2, false));
    this.nativeBannerAdContainer.addView(this.adView);
    ((RelativeLayout)this.adView.findViewById(2131296294)).addView(new AdChoicesView(this, paramNativeBannerAd, true), 0);
    Object localObject3 = (TextView)this.adView.findViewById(2131296400);
    TextView localTextView1 = (TextView)this.adView.findViewById(2131296398);
    TextView localTextView2 = (TextView)this.adView.findViewById(2131296399);
    localObject1 = (AdIconView)this.adView.findViewById(2131296402);
    localObject2 = (Button)this.adView.findViewById(2131296397);
    ((Button)localObject2).setText(paramNativeBannerAd.getAdCallToAction());
    if (!paramNativeBannerAd.hasCallToAction()) {
      k = 4;
    }
    ((Button)localObject2).setVisibility(k);
    ((TextView)localObject3).setText(paramNativeBannerAd.getAdvertiserName());
    localTextView1.setText(paramNativeBannerAd.getAdSocialContext());
    localTextView2.setText(paramNativeBannerAd.getSponsoredTranslation());
    localObject3 = new ArrayList();
    ((List)localObject3).add(localObject2);
    paramNativeBannerAd.registerViewForInteraction(this.adView, (MediaView)localObject1, (List)localObject3);
  }
  
  private boolean isAvilible(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    int k = 0;
    paramContext = paramContext.getInstalledPackages(0);
    ArrayList localArrayList = new ArrayList();
    if (paramContext != null) {
      while (k < paramContext.size())
      {
        localArrayList.add(((PackageInfo)paramContext.get(k)).packageName);
        k += 1;
      }
    }
    return localArrayList.contains(paramString);
  }
  
  private void privatepolicy()
  {
    try
    {
      startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://sites.google.com/view/weaponsounds")));
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException) {}
  }
  
  private void promtapp()
  {
    Typeface.createFromAsset(getAssets(), "fonts/AntiqueOLIVE.ttf");
    int k = 0;
    while (k < MoreappData.mores.length)
    {
      this.mMoreapp = MoreappData.mores[k];
      if (!isAvilible(this, this.mMoreapp.mUri))
      {
        this.dialog = new Dialog(this, 2131689869);
        this.dialog.setContentView(2131427376);
        Object localObject1 = this.dialog.getWindow();
        Object localObject2 = ((Window)localObject1).getAttributes();
        ((WindowManager.LayoutParams)localObject2).alpha = 1.0F;
        ((Window)localObject1).setGravity(17);
        ((Window)localObject1).setWindowAnimations(2131689875);
        ((Window)localObject1).getDecorView().setBackgroundResource(2131230934);
        ((Window)localObject1).setAttributes((WindowManager.LayoutParams)localObject2);
        this.dialog.setCanceledOnTouchOutside(false);
        this.dialog.setCancelable(false);
        this.dialog.getWindow().setLayout(-2, -2);
        localObject1 = (TextView)this.dialog.findViewById(2131296490);
        localObject2 = (ImageView)this.dialog.findViewById(2131296366);
        ((TextView)localObject1).setText(this.mMoreapp.mDis);
        ((ImageView)localObject2).setBackgroundResource(this.mMoreapp.mImgResId);
        localObject1 = (Button)this.dialog.findViewById(2131296263);
        localObject2 = (Button)this.dialog.findViewById(2131296262);
        this.dialog.show();
        ((Button)localObject1).setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            paramAnonymousView = new StringBuilder();
            paramAnonymousView.append("market://details?id=");
            paramAnonymousView.append(Main.this.mMoreapp.mUri);
            paramAnonymousView = new Intent("android.intent.action.VIEW", Uri.parse(paramAnonymousView.toString()));
            try
            {
              Main.this.startActivity(paramAnonymousView);
            }
            catch (ActivityNotFoundException paramAnonymousView)
            {
              StringBuilder localStringBuilder;
              for (;;) {}
            }
            paramAnonymousView = Main.this;
            localStringBuilder = new StringBuilder();
            localStringBuilder.append("http://play.google.com/store/apps/details?id=");
            localStringBuilder.append(Main.this.mMoreapp.mUri);
            paramAnonymousView.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(localStringBuilder.toString())));
            Main.this.dialog.dismiss();
          }
        });
        ((Button)localObject2).setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            Main.this.dialog.dismiss();
          }
        });
        k = MoreappData.mores.length;
      }
      k += 1;
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
    ((StringBuilder)localObject).append("http://play.google.com/store/apps/details?id=");
    ((StringBuilder)localObject).append(getPackageName());
    startActivity(new Intent("android.intent.action.VIEW", Uri.parse(((StringBuilder)localObject).toString())));
  }
  
  private void share()
  {
    Intent localIntent = new Intent();
    localIntent.setAction("android.intent.action.SEND");
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Share a funny app ");
    localStringBuilder.append(getString(2131623967));
    localStringBuilder.append(" on Google Play Store, \n You can Download it from Here https://play.google.com/store/apps/details?id=");
    localStringBuilder.append(getPackageName());
    localIntent.putExtra("android.intent.extra.TEXT", localStringBuilder.toString());
    localIntent.setType("text/plain");
    startActivity(localIntent);
  }
  
  public void onBackPressed()
  {
    super.onBackPressed();
  }
  
  public void onClick(View paramView)
  {
    int k = paramView.getId();
    this.intent = null;
    if (k != 2131296385) {
      return;
    }
    if (this.drawerLayout.isDrawerOpen(this.navigationView))
    {
      this.drawerLayout.closeDrawer(this.navigationView);
      return;
    }
    this.drawerLayout.openDrawer(this.navigationView);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    setTheme(2131689773);
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    getWindow().setFlags(1024, 1024);
    setContentView(2131427384);
    promtapp();
    this.adView1 = ((AdView)findViewById(2131296293));
    this.nativeBannerAd = new NativeBannerAd(this, getResources().getString(2131623977));
    this.nativeBannerAd.setAdListener(new NativeAdListener()
    {
      public void onAdClicked(Ad paramAnonymousAd) {}
      
      public void onAdLoaded(Ad paramAnonymousAd)
      {
        Main.this.adView1.setVisibility(4);
        if (Main.this.nativeBannerAd != null)
        {
          if (Main.this.nativeBannerAd != paramAnonymousAd) {
            return;
          }
          Main.this.inflateAd(Main.this.nativeBannerAd);
          return;
        }
      }
      
      public void onError(Ad paramAnonymousAd, AdError paramAnonymousAdError)
      {
        Main.this.adView1.setVisibility(0);
        paramAnonymousAd = new AdRequest.Builder().build();
        Main.this.adView1.loadAd(paramAnonymousAd);
      }
      
      public void onLoggingImpression(Ad paramAnonymousAd) {}
      
      public void onMediaDownloaded(Ad paramAnonymousAd) {}
    });
    this.nativeBannerAd.loadAd();
    this.sp = getSharedPreferences("guanka", 0);
    this.prot = this.sp.getInt("PROMT", 0);
    this.prot += 1;
    paramBundle = this.sp.edit();
    paramBundle.putInt("PROMT", this.prot);
    paramBundle.commit();
    this.mCircleMenuLayout = ((CircleMenuLayout)findViewById(2131296363));
    this.mCircleMenuLayout.setMenuItemIconsAndTexts(this.mItemImgs, this.mItemTexts);
    this.mCircleMenuLayout.setOnMenuItemClickListener(new CircleMenuLayout.OnMenuItemClickListener()
    {
      public void itemCenterClick(View paramAnonymousView)
      {
        Toast.makeText(Main.this, "you can rotate the circle to select weapon!", 0).show();
      }
      
      public void itemClick(View paramAnonymousView, int paramAnonymousInt)
      {
        Main.access$308(Main.this);
        switch (paramAnonymousInt)
        {
        default: 
          return;
        case 9: 
          Main.access$402(Main.this, new Intent());
          Main.this.myIntent.setClass(Main.this, otherweaponshow.class);
          Main.this.myIntent.putExtra("otherweapon_id", paramAnonymousInt);
          Main.this.myIntent.putExtra("click_time0", Main.this.j);
          Main.this.startActivity(Main.this.myIntent);
          return;
        case 8: 
          Main.access$402(Main.this, new Intent());
          Main.this.myIntent.setClass(Main.this, rifleshoot.class);
          Main.this.myIntent.putExtra("otherweapon_id", paramAnonymousInt);
          Main.this.myIntent.putExtra("click_time", Main.this.j);
          Main.this.startActivity(Main.this.myIntent);
          return;
        case 7: 
          Main.access$402(Main.this, new Intent());
          Main.this.myIntent.setClass(Main.this, handgunshoot.class);
          Main.this.myIntent.putExtra("otherweapon_id", paramAnonymousInt);
          Main.this.myIntent.putExtra("click_time", Main.this.j);
          Main.this.startActivity(Main.this.myIntent);
          return;
        case 6: 
          Main.access$402(Main.this, new Intent());
          Main.this.myIntent.setClass(Main.this, gunshow.class);
          Main.this.myIntent.putExtra("otherweapon_id", paramAnonymousInt);
          Main.this.startActivity(Main.this.myIntent);
          return;
        case 5: 
          Main.access$402(Main.this, new Intent());
          Main.this.myIntent.setClass(Main.this, stungun.class);
          Main.this.myIntent.putExtra("otherweapon_id", paramAnonymousInt);
          Main.this.startActivity(Main.this.myIntent);
          return;
        case 4: 
          Main.access$402(Main.this, new Intent());
          Main.this.myIntent.setClass(Main.this, gunshow.class);
          Main.this.myIntent.putExtra("otherweapon_id", paramAnonymousInt);
          Main.this.startActivity(Main.this.myIntent);
          return;
        case 3: 
          Main.access$402(Main.this, new Intent());
          Main.this.myIntent.setClass(Main.this, weaponshoot.class);
          Main.this.myIntent.putExtra("otherweapon_id", paramAnonymousInt);
          Main.this.startActivity(Main.this.myIntent);
          return;
        case 2: 
          Main.access$402(Main.this, new Intent());
          Main.this.myIntent.setClass(Main.this, weaponshoot.class);
          Main.this.myIntent.putExtra("otherweapon_id", paramAnonymousInt);
          Main.this.startActivity(Main.this.myIntent);
          return;
        case 1: 
          Main.access$402(Main.this, new Intent());
          Main.this.myIntent.setClass(Main.this, knife.class);
          Main.this.startActivity(Main.this.myIntent);
          return;
        }
        Main.access$402(Main.this, new Intent());
        Main.this.myIntent.setClass(Main.this, Lightsaber.class);
        Main.this.startActivity(Main.this.myIntent);
      }
    });
    this.drawerLayout = ((DrawerLayout)findViewById(2131296292));
    this.navigationView = ((NavigationView)findViewById(2131296403));
    this.menu = ((ImageView)findViewById(2131296385));
    this.navigationView.getHeaderView(0);
    this.menu.setOnClickListener(this);
    this.navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener()
    {
      public boolean onNavigationItemSelected(@NonNull MenuItem paramAnonymousMenuItem)
      {
        int i = paramAnonymousMenuItem.getItemId();
        if (i == 2131296393) {
          Main.this.promtapp();
        } else if (i == 2131296460) {
          Main.this.share();
        } else if (i == 2131296425) {
          Main.this.rate();
        } else if (i == 2131296419) {
          Main.this.privatepolicy();
        }
        Main.this.drawerLayout.closeDrawer(8388611);
        return true;
      }
    });
  }
  
  protected void onDestroy()
  {
    if (this.nativeBannerAd != null)
    {
      this.nativeBannerAd.unregisterView();
      this.nativeBannerAd = null;
    }
    super.onDestroy();
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if ((paramInt == 4) && (paramKeyEvent.getRepeatCount() == 0))
    {
      ExitPrompt();
      return true;
    }
    return true;
  }
  
  protected void onPause()
  {
    super.onPause();
  }
  
  protected void onResume()
  {
    super.onResume();
  }
}
