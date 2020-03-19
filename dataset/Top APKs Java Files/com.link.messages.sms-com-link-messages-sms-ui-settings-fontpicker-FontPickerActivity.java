package com.link.messages.sms.ui.settings.fontpicker;

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
import android.support.v4.app.m;
import android.support.v4.app.r;
import android.support.v4.app.u;
import android.support.v4.view.ViewPager;
import android.support.v7.a.g;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import com.google.a.f;
import com.link.messages.sms.util.p;
import com.link.messages.sms.util.v;
import com.link.messages.sms.views.SlidingTabLayout;
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
  extends g
{
  private ViewPager n;
  private HashMap<String, ArrayList<com.link.messages.external.theme.b.b>> o = new HashMap();
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
        p.e("", "update font exception happens. " + ???.getMessage());
      }
    }
  };
  
  public FontPickerActivity() {}
  
  private ArrayList<com.link.messages.external.theme.b.b> a(AssetManager paramAssetManager, String paramString)
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
          com.link.messages.external.theme.b.b localB = new com.link.messages.external.theme.b.b();
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
          this.o = ((HashMap)arrayOfString.a(localStringBuffer.toString(), new com.google.a.c.a() {}.getType()));
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
    c localC = (c)f().a("android:switcher:2131820791:" + this.n.getCurrentItem());
    if ((localC != null) && (localC.b())) {
      return;
    }
    super.onBackPressed();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968694);
    paramBundle = (Toolbar)findViewById(2131820717);
    a(paramBundle);
    paramBundle.setNavigationIcon(2130838154);
    paramBundle.setNavigationOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        FontPickerActivity.this.finish();
      }
    });
    paramBundle = g();
    if (paramBundle != null) {
      paramBundle.a(2131296611);
    }
    this.n = ((ViewPager)findViewById(2131820791));
    this.n.setAdapter(new a(f()));
    paramBundle = (SlidingTabLayout)findViewById(2131820789);
    paramBundle.a(2130968832, 16908308);
    paramBundle.setSelectedIndicatorColors(new int[] { getResources().getColor(2131689781) });
    paramBundle.setDistributeEvenly(true);
    paramBundle.setViewPager(this.n);
    ((FloatingActionButton)findViewById(2131820739)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        v.a(FontPickerActivity.this, "market://details?id=com.kkkeyboard.emoji.keyboard.font.keyboardfont");
      }
    });
  }
  
  protected void onPause()
  {
    super.onPause();
    com.facebook.a.a.b(this);
    MobclickAgent.onPause(this);
  }
  
  protected void onResume()
  {
    super.onResume();
    MobclickAgent.onResume(this);
    com.facebook.a.a.a(this);
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
    extends u
  {
    public a(r paramR)
    {
      super();
    }
    
    public m a(int paramInt)
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
    
    public int b()
    {
      return 2;
    }
    
    public CharSequence c(int paramInt)
    {
      switch (paramInt)
      {
      default: 
        return super.c(paramInt);
      case 0: 
        return FontPickerActivity.this.getResources().getString(2131296612);
      }
      return FontPickerActivity.this.getResources().getString(2131296613);
    }
  }
}
