package com.weaponsounds.gunsound.arma.weapons;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextPaint;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.AdView;
import com.startapp.android.publish.adsCommon.StartAppAd;
import com.startapp.android.publish.adsCommon.StartAppSDK;
import java.util.ArrayList;
import java.util.List;

public class Main
  extends Activity
{
  private AdView adView;
  private ImageView btnhandgun;
  private ImageView btnmachinegun;
  private ImageView btnrifle;
  private Dialog dialog;
  private Typeface font;
  private Intent intent;
  private More mMoreapp;
  private SharedPreferences mPrefs;
  private MediaPlayer mp;
  private StartAppAd startAppAd = new StartAppAd(this);
  
  public Main() {}
  
  private void ExitPrompt()
  {
    this.font = Typeface.createFromAsset(getAssets(), "fonts/gridview.ttf");
    int i = 0;
    int j = 0;
    while (i < MoreappData.mores.length)
    {
      this.mMoreapp = MoreappData.mores[i];
      if (!isAvilible(this, this.mMoreapp.mUri))
      {
        this.dialog = new Dialog(this, 2131493223);
        this.dialog.setContentView(2131296288);
        Object localObject1 = this.dialog.getWindow();
        Object localObject2 = ((Window)localObject1).getAttributes();
        ((WindowManager.LayoutParams)localObject2).alpha = 1.0F;
        ((Window)localObject1).setGravity(17);
        ((Window)localObject1).setWindowAnimations(2131493227);
        ((Window)localObject1).getDecorView().setBackgroundResource(2131099804);
        ((Window)localObject1).setAttributes((WindowManager.LayoutParams)localObject2);
        this.dialog.setCanceledOnTouchOutside(false);
        this.dialog.getWindow().setLayout(-2, -2);
        Object localObject3 = (TextView)this.dialog.findViewById(2131165387);
        localObject1 = (ImageView)this.dialog.findViewById(2131165286);
        localObject2 = (ImageView)this.dialog.findViewById(2131165256);
        ((TextView)localObject3).setText(this.mMoreapp.mDis);
        ((TextView)localObject3).setTypeface(this.font);
        ((ImageView)localObject1).setBackgroundResource(this.mMoreapp.mImgResId);
        ((ImageView)localObject2).setVisibility(0);
        localObject3 = (Button)this.dialog.findViewById(2131165194);
        Button localButton1 = (Button)this.dialog.findViewById(2131165193);
        Button localButton2 = (Button)this.dialog.findViewById(2131165267);
        ((Button)localObject3).setTypeface(this.font);
        localButton1.setTypeface(this.font);
        localButton2.setTypeface(this.font);
        localButton1.setText("Exit");
        this.dialog.show();
        ((ImageView)localObject1).setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            Main.this.playsound(0);
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
            Main.this.playsound(0);
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
            Main.this.playsound(0);
            Main.this.rate();
            Main.this.dialog.dismiss();
          }
        });
        localButton1.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            Main.this.playsound(0);
            Main.this.dialog.dismiss();
            Main.this.finish();
          }
        });
        ((ImageView)localObject2).setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            Main.this.playsound(0);
            Main.this.dialog.dismiss();
          }
        });
        i = MoreappData.mores.length;
      }
      else
      {
        j += 1;
      }
      i += 1;
    }
    if (j == MoreappData.mores.length) {
      exitdialog();
    }
  }
  
  private void exitdialog()
  {
    new AlertDialog.Builder(this).setTitle("Prompt").setIcon(2131099860).setMessage("Are you sure to exit this game?").setPositiveButton("Yes", new DialogInterface.OnClickListener()
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
  
  private void promtapp()
  {
    Typeface localTypeface = Typeface.createFromAsset(getAssets(), "fonts/gridview.ttf");
    int i = 0;
    while (i < MoreappData.mores.length)
    {
      this.mMoreapp = MoreappData.mores[i];
      if (!isAvilible(this, this.mMoreapp.mUri))
      {
        this.dialog = new Dialog(this, 2131493223);
        this.dialog.setContentView(2131296290);
        Object localObject1 = this.dialog.getWindow();
        Object localObject2 = ((Window)localObject1).getAttributes();
        ((WindowManager.LayoutParams)localObject2).alpha = 1.0F;
        ((Window)localObject1).setGravity(17);
        ((Window)localObject1).setWindowAnimations(2131493227);
        ((Window)localObject1).getDecorView().setBackgroundResource(2131099804);
        ((Window)localObject1).setAttributes((WindowManager.LayoutParams)localObject2);
        this.dialog.setCanceledOnTouchOutside(false);
        this.dialog.setCancelable(false);
        this.dialog.getWindow().setLayout(-2, -2);
        Object localObject3 = (TextView)this.dialog.findViewById(2131165387);
        localObject1 = (ImageView)this.dialog.findViewById(2131165286);
        localObject2 = (TextView)this.dialog.findViewById(2131165322);
        ((TextView)localObject2).getPaint().setFlags(8);
        ((TextView)localObject3).setText(this.mMoreapp.mDis);
        ((TextView)localObject3).setTypeface(localTypeface);
        ((ImageView)localObject1).setBackgroundResource(this.mMoreapp.mImgResId);
        localObject3 = (Button)this.dialog.findViewById(2131165194);
        Button localButton = (Button)this.dialog.findViewById(2131165193);
        ((Button)localObject3).setTypeface(localTypeface);
        localButton.setTypeface(localTypeface);
        ((TextView)localObject2).setTypeface(localTypeface);
        this.dialog.show();
        ((ImageView)localObject1).setOnClickListener(new View.OnClickListener()
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
            localStringBuilder.append("https://play.google.com/store/apps/details?id=");
            localStringBuilder.append(Main.this.mMoreapp.mUri);
            paramAnonymousView.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(localStringBuilder.toString())));
            Main.this.dialog.dismiss();
          }
        });
        ((Button)localObject3).setOnClickListener(new View.OnClickListener()
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
        localButton.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            Main.this.dialog.dismiss();
          }
        });
        ((TextView)localObject2).setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            try
            {
              Main.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://sites.google.com/view/ScaryPhotoEditor")));
              return;
            }
            catch (ActivityNotFoundException paramAnonymousView)
            {
              paramAnonymousView.printStackTrace();
            }
          }
        });
        i = MoreappData.mores.length;
      }
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
  
  public void onBackPressed()
  {
    this.startAppAd.onBackPressed();
    super.onBackPressed();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    getWindow().setFlags(1024, 1024);
    StartAppSDK.init(this, "209253561", true);
    setContentView(2131296284);
    promtapp();
    this.adView = ((AdView)findViewById(2131165220));
    paramBundle = new AdRequest.Builder().build();
    this.adView.loadAd(paramBundle);
    this.font = Typeface.createFromAsset(getAssets(), "fonts/gridview.ttf");
    this.mPrefs = PreferenceManager.getDefaultSharedPreferences(this);
    this.btnhandgun = ((ImageView)findViewById(2131165278));
    this.btnrifle = ((ImageView)findViewById(2131165334));
    this.btnmachinegun = ((ImageView)findViewById(2131165300));
    this.btnhandgun.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        Main.this.playsound(0);
        Main.access$002(Main.this, new Intent());
        Main.this.intent.setClass(Main.this, Pistol.class);
        Main.this.startActivity(Main.this.intent);
      }
    });
    this.btnrifle.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        Main.this.playsound(0);
        Main.access$002(Main.this, new Intent());
        Main.this.intent.setClass(Main.this, Rifle.class);
        Main.this.startActivity(Main.this.intent);
      }
    });
    this.btnmachinegun.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        Main.this.playsound(0);
        Main.access$002(Main.this, new Intent());
        Main.this.intent.setClass(Main.this, Otherweapon.class);
        Main.this.startActivity(Main.this.intent);
      }
    });
  }
  
  protected void onDestroy()
  {
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
      ExitPrompt();
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
  
  protected void onStart()
  {
    super.onStart();
  }
  
  public void playsound(int paramInt)
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        if (Main.this.mp != null)
        {
          Main.this.mp.stop();
          Main.this.mp.reset();
          Main.this.mp.release();
          Main.access$102(Main.this, null);
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
}
