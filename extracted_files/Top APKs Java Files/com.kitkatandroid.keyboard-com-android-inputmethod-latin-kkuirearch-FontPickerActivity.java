package com.android.inputmethod.latin.kkuirearch;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.o;
import android.support.v4.app.r;
import android.support.v4.view.ViewPager;
import android.support.v7.a.b;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import com.android.inputmethod.latin.kkuirearch.fragments.FontPickerOnSDFragment;
import com.android.inputmethod.latin.kkuirearch.fragments.FontPickerOtherAppsFragment;
import com.android.inputmethod.latin.kkuirearch.fragments.FragmentBackPressed;
import com.android.inputmethod.latin.kkuirearch.views.SlidingTabLayout;
import com.android.inputmethod.latin.kkuirearch.views.fab.FloatingActionButton;
import com.myandroid.a.a.d;
import com.umeng.analytics.MobclickAgent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class FontPickerActivity
  extends b
{
  private ViewPager a;
  private HashMap<String, ArrayList<com.android.inputmethod.latin.kkuirearch.extras.c>> b = new HashMap();
  private Thread c;
  private FontPickerOtherAppsFragment d;
  private Runnable e = new Runnable()
  {
    public final void run()
    {
      FontPickerActivity.a(FontPickerActivity.this);
      if (!FontPickerActivity.b(FontPickerActivity.this).isEmpty()) {
        FontPickerActivity.c(FontPickerActivity.this).sendEmptyMessage(0);
      }
      FontPickerActivity.d(FontPickerActivity.this);
      Iterator localIterator = FontPickerActivity.this.getPackageManager().getInstalledPackages(0).iterator();
      for (;;)
      {
        PackageInfo localPackageInfo;
        if (localIterator.hasNext())
        {
          localPackageInfo = (PackageInfo)localIterator.next();
          if (!Thread.currentThread().isInterrupted()) {}
        }
        else
        {
          return;
        }
        if (!FontPickerActivity.b(FontPickerActivity.this).containsKey(localPackageInfo.packageName)) {
          try
          {
            ArrayList localArrayList = FontPickerActivity.a(FontPickerActivity.this.createPackageContext(localPackageInfo.packageName, 2).getAssets(), localPackageInfo.packageName);
            if (!localArrayList.isEmpty())
            {
              FontPickerActivity.b(FontPickerActivity.this).put(localPackageInfo.packageName, localArrayList);
              FontPickerActivity.d(FontPickerActivity.this);
              if (!FontPickerActivity.c(FontPickerActivity.this).hasMessages(0)) {
                FontPickerActivity.c(FontPickerActivity.this).sendEmptyMessage(0);
              }
            }
          }
          catch (PackageManager.NameNotFoundException localNameNotFoundException)
          {
            localNameNotFoundException.printStackTrace();
          }
        }
      }
    }
  };
  private Handler f = new Handler()
  {
    public final void handleMessage(Message paramAnonymousMessage)
    {
      super.handleMessage(paramAnonymousMessage);
      switch (paramAnonymousMessage.what)
      {
      default: 
        return;
      }
      if (FontPickerActivity.e(FontPickerActivity.this) != null)
      {
        FontPickerActivity.e(FontPickerActivity.this).updateFontInfo((ArrayList[])FontPickerActivity.b(FontPickerActivity.this).values().toArray(new ArrayList[0]));
        return;
      }
      sendMessageAtFrontOfQueue(paramAnonymousMessage);
    }
  };
  
  public FontPickerActivity() {}
  
  private static boolean a(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext.getPackageInfo(paramString, 0);
      return true;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  private static ArrayList<com.android.inputmethod.latin.kkuirearch.extras.c> b(AssetManager paramAssetManager, String paramString)
  {
    localArrayList = new ArrayList();
    try
    {
      paramAssetManager = paramAssetManager.list("fonts");
      int j = paramAssetManager.length;
      int i = 0;
      while (i < j)
      {
        String str = paramAssetManager[i];
        if (str.toLowerCase().endsWith(".ttf"))
        {
          com.android.inputmethod.latin.kkuirearch.extras.c localC = new com.android.inputmethod.latin.kkuirearch.extras.c();
          localC.c = ("fonts/" + str);
          localC.d = str.replace(".ttf", "");
          localC.e = paramString;
          localC.a = 4;
          localArrayList.add(localC);
        }
        i += 1;
      }
      return localArrayList;
    }
    catch (IOException paramAssetManager)
    {
      paramAssetManager.printStackTrace();
    }
  }
  
  public void onBackPressed()
  {
    FragmentBackPressed localFragmentBackPressed = (FragmentBackPressed)getSupportFragmentManager().a("android:switcher:2131755294:" + this.a.getCurrentItem());
    if ((localFragmentBackPressed != null) && (localFragmentBackPressed.onBackPressed())) {
      return;
    }
    super.onBackPressed();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968684);
    paramBundle = (Toolbar)findViewById(2131755201);
    a(paramBundle);
    paramBundle.setNavigationIcon(2130837893);
    paramBundle.setNavigationOnClickListener(new View.OnClickListener()
    {
      public final void onClick(View paramAnonymousView)
      {
        FontPickerActivity.this.finish();
      }
    });
    paramBundle = super.c().b();
    if (paramBundle != null) {
      paramBundle.a(2131296764);
    }
    this.a = ((ViewPager)findViewById(2131755294));
    this.a.setAdapter(new a(getSupportFragmentManager()));
    paramBundle = (SlidingTabLayout)findViewById(2131755292);
    paramBundle.setCustomTabView$255f295(2130968826);
    paramBundle.setSelectedIndicatorColors(new int[] { getResources().getColor(2131689895) });
    paramBundle.setDistributeEvenly(true);
    paramBundle.setViewPager(this.a);
    ((FloatingActionButton)findViewById(2131755235)).setOnClickListener(new View.OnClickListener()
    {
      public final void onClick(View paramAnonymousView)
      {
        d.a(FontPickerActivity.this, "market://details?id=com.kkkeyboard.emoji.keyboard.font.keyboardfont");
      }
    });
  }
  
  protected void onPause()
  {
    super.onPause();
    MobclickAgent.onPause(this);
  }
  
  protected void onResume()
  {
    super.onResume();
    MobclickAgent.onResume(this);
  }
  
  protected void onStart()
  {
    super.onStart();
    this.c = new Thread(this.e);
    this.c.start();
  }
  
  protected void onStop()
  {
    super.onStop();
    if (this.c.isAlive()) {
      this.c.interrupt();
    }
    this.f.removeCallbacksAndMessages(null);
  }
  
  private final class a
    extends r
  {
    public a(o paramO)
    {
      super();
    }
    
    public final int getCount()
    {
      return 2;
    }
    
    public final Fragment getItem(int paramInt)
    {
      switch (paramInt)
      {
      default: 
        return null;
      case 0: 
        return new FontPickerOnSDFragment();
      }
      FontPickerActivity.a(FontPickerActivity.this, new FontPickerOtherAppsFragment());
      return FontPickerActivity.e(FontPickerActivity.this);
    }
    
    public final CharSequence getPageTitle(int paramInt)
    {
      switch (paramInt)
      {
      default: 
        return super.getPageTitle(paramInt);
      case 0: 
        return FontPickerActivity.this.getResources().getString(2131296765);
      }
      return FontPickerActivity.this.getResources().getString(2131296766);
    }
  }
}
