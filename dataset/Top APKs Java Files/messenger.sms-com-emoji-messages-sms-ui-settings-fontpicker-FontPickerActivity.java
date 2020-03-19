package com.emoji.messages.sms.ui.settings.fontpicker;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.l;
import android.support.v4.app.t;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.d;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import com.emoji.messages.sms.util.w;
import com.emoji.messages.sms.views.SlidingTabLayout;
import com.facebook.appevents.AppEventsLogger;
import com.google.a.f;
import com.umeng.analytics.MobclickAgent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class FontPickerActivity
  extends d
{
  private ViewPager n;
  private HashMap<String, ArrayList<com.emoji.messages.external.theme.a.b>> o = new HashMap();
  private Thread p;
  private b q;
  private Object r = new Object();
  private Runnable s = new Runnable()
  {
    public void run()
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
            Object localObject = FontPickerActivity.this.createPackageContext(localPackageInfo.packageName, 2).getAssets();
            localObject = FontPickerActivity.a(FontPickerActivity.this, (AssetManager)localObject, localPackageInfo.packageName);
            if (!((ArrayList)localObject).isEmpty())
            {
              FontPickerActivity.b(FontPickerActivity.this).put(localPackageInfo.packageName, localObject);
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
  private Handler t = new Handler()
  {
    public void handleMessage(Message arg1)
    {
      super.handleMessage(???);
      switch (???.what)
      {
      default: 
        return;
      }
      if (FontPickerActivity.e(FontPickerActivity.this) != null) {
        synchronized (FontPickerActivity.f(FontPickerActivity.this))
        {
          FontPickerActivity.e(FontPickerActivity.this).a((ArrayList[])FontPickerActivity.b(FontPickerActivity.this).values().toArray(new ArrayList[0]));
          return;
        }
      }
      try
      {
        sendMessageAtFrontOfQueue(???);
        return;
      }
      catch (Exception ???)
      {
        com.emoji.messages.sms.util.q.e("", "update font exception happens. " + ???.getMessage());
      }
    }
  };
  
  public FontPickerActivity() {}
  
  private ArrayList<com.emoji.messages.external.theme.a.b> a(AssetManager paramAssetManager, String paramString)
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
          com.emoji.messages.external.theme.a.b localB = new com.emoji.messages.external.theme.a.b();
          localB.c = ("fonts/" + str);
          localB.d = str.replace(".ttf", "");
          localB.e = paramString;
          localB.a = 4;
          localArrayList.add(localB);
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
  
  private boolean a(Context paramContext, String paramString)
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
  
  private void k()
  {
    int i = 0;
    f localF = new f();
    try
    {
      Object localObject = openFileInput("other_apps_font_info_file");
      StringBuffer localStringBuffer = new StringBuffer("");
      byte[] arrayOfByte = new byte['Ѐ'];
      int j;
      for (;;)
      {
        j = ((FileInputStream)localObject).read(arrayOfByte);
        if (j == -1) {
          break;
        }
        localStringBuffer.append(new String(arrayOfByte, 0, j));
      }
      String[] arrayOfString;
      return;
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      localFileNotFoundException.printStackTrace();
      for (;;)
      {
        arrayOfString = (String[])this.o.keySet().toArray(new String[0]);
        j = arrayOfString.length;
        while (i < j)
        {
          localObject = arrayOfString[i];
          if (!a(this, (String)localObject)) {
            this.o.remove(localObject);
          }
          i += 1;
        }
        if (!TextUtils.isEmpty(localStringBuffer.toString())) {
          this.o = ((HashMap)arrayOfString.a(localStringBuffer.toString(), new com.google.a.c.a() {}.b()));
        }
      }
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        localIOException.printStackTrace();
      }
    }
  }
  
  private void l()
  {
    String str = new f().a(this.o);
    try
    {
      FileOutputStream localFileOutputStream = openFileOutput("other_apps_font_info_file", 0);
      localFileOutputStream.write(str.getBytes());
      localFileOutputStream.close();
      return;
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      localFileNotFoundException.printStackTrace();
      return;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
  }
  
  public void onBackPressed()
  {
    c localC = (c)e().a("android:switcher:2131886402:" + this.n.getCurrentItem());
    if ((localC != null) && (localC.ac())) {
      return;
    }
    super.onBackPressed();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968747);
    paramBundle = (Toolbar)findViewById(2131886347);
    a(paramBundle);
    paramBundle.setNavigationIcon(2130838133);
    paramBundle.setNavigationOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        FontPickerActivity.this.finish();
      }
    });
    paramBundle = g();
    if (paramBundle != null) {
      paramBundle.a(2131427686);
    }
    this.n = ((ViewPager)findViewById(2131886402));
    this.n.setAdapter(new a(e()));
    paramBundle = (SlidingTabLayout)findViewById(2131886400);
    paramBundle.a(2130968936, 16908308);
    paramBundle.setSelectedIndicatorColors(new int[] { getResources().getColor(2131755244) });
    paramBundle.setDistributeEvenly(true);
    paramBundle.setViewPager(this.n);
    ((FloatingActionButton)findViewById(2131886348)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        w.a(FontPickerActivity.this, "market://details?id=com.kkkeyboard.emoji.keyboard.font.keyboardfont");
      }
    });
  }
  
  protected void onPause()
  {
    super.onPause();
    AppEventsLogger.deactivateApp(this);
    MobclickAgent.onPause(this);
  }
  
  protected void onResume()
  {
    super.onResume();
    MobclickAgent.onResume(this);
    AppEventsLogger.activateApp(this);
  }
  
  protected void onStart()
  {
    super.onStart();
    this.p = new Thread(this.s);
    this.p.start();
  }
  
  protected void onStop()
  {
    super.onStop();
    if (this.p.isAlive()) {
      this.p.interrupt();
    }
    this.t.removeCallbacksAndMessages(null);
  }
  
  private class a
    extends t
  {
    public a(android.support.v4.app.q paramQ)
    {
      super();
    }
    
    public l a(int paramInt)
    {
      switch (paramInt)
      {
      default: 
        return null;
      case 0: 
        return new a();
      }
      FontPickerActivity.a(FontPickerActivity.this, new b());
      return FontPickerActivity.e(FontPickerActivity.this);
    }
    
    public int getCount()
    {
      return 2;
    }
    
    public CharSequence getPageTitle(int paramInt)
    {
      switch (paramInt)
      {
      default: 
        return super.getPageTitle(paramInt);
      case 0: 
        return FontPickerActivity.this.getResources().getString(2131427687);
      }
      return FontPickerActivity.this.getResources().getString(2131427688);
    }
  }
}
