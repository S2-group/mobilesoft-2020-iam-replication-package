package com.emoji.messages.sms.ui.settings.theme;

import android.animation.AnimatorSet;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import com.emoji.messages.external.theme.c;
import com.emoji.messages.sms.ui.e;
import com.emoji.messages.sms.ui.p;
import com.emoji.messages.sms.ui.settings.wallpaper.h;
import com.emoji.messages.sms.util.ae;
import com.emoji.messages.sms.util.j;
import com.emoji.messages.sms.widget.materialdialogs.d.a;
import com.emoji.messages.sms.widget.materialdialogs.d.b;
import com.umeng.analytics.MobclickAgent;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

public class a
  extends e
{
  public static final int[] b = { 2130838302, 2130838505 };
  private String aa;
  private AnimatorSet ab;
  private boolean ac;
  private int ad = 1;
  private final BroadcastReceiver ae = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      if ("emoji.keyboard.emoticonkeyboard.KAKA_THEME_CHANGED".equals(paramAnonymousIntent.getAction()))
      {
        paramAnonymousIntent = paramAnonymousIntent.getExtras().getString("theme_pkg");
        String str = PreferenceManager.getDefaultSharedPreferences(a.b(a.this)).getString("keyboard_theme_pkg", "");
        if ((paramAnonymousIntent != null) && (paramAnonymousIntent.equals(str)) && (a.a(a.this) != null) && (a.a(a.this).getCount() > 1)) {
          a.a(a.this, 0);
        }
        if (!a.a(a.this, paramAnonymousContext, paramAnonymousIntent)) {
          break label191;
        }
        paramAnonymousContext = new a.b(a.this, null);
        paramAnonymousContext.a = 17;
        paramAnonymousContext.b = p.b(a.b(a.this), paramAnonymousIntent);
        paramAnonymousContext.d = paramAnonymousIntent;
        paramAnonymousContext.e = false;
        paramAnonymousContext.c = 0;
        if (!a.c(a.this).containsKey(paramAnonymousContext.d)) {
          a.c(a.this).put(paramAnonymousContext.d, paramAnonymousContext);
        }
      }
      for (;;)
      {
        a.a(a.this).notifyDataSetChanged();
        return;
        label191:
        if (a.c(a.this).containsKey(paramAnonymousIntent)) {
          a.c(a.this).remove(paramAnonymousIntent);
        }
      }
    }
  };
  private Activity c;
  private GridView d;
  private a e;
  private int[] f;
  private String[] g;
  private final LinkedHashMap<String, b> h = new LinkedHashMap();
  private c i = new c();
  
  public a() {}
  
  private void a(int paramInt)
  {
    j.a(this.c, "theme_set");
    SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.c);
    SharedPreferences.Editor localEditor = localSharedPreferences.edit();
    localEditor.putString("keyboard_theme_id", String.valueOf(((b)this.i.getItem(paramInt)).a)).apply();
    localEditor.putString("keyboard_theme_pkg", ((b)this.i.getItem(paramInt)).d).apply();
    localEditor.putString("keyboard_theme_name", ((b)this.i.getItem(paramInt)).b).apply();
    this.aa = ((b)this.i.getItem(paramInt)).b;
    this.i.notifyDataSetChanged();
    com.emoji.messages.external.theme.d.a().c();
    if ((localSharedPreferences.getBoolean("pref_key_use_custom_settings", false)) && (!this.ac)) {
      ad();
    }
  }
  
  private void ad()
  {
    new d.a(i()).a(2131428200).b(2131755049).a(new TextView(i())).e(2131427604).i(2131755022).g(2131427603).k(2131755022).a(new d.b()
    {
      public void a(com.emoji.messages.sms.widget.materialdialogs.d paramAnonymousD)
      {
        h.a(a.this.i());
      }
      
      public void b(com.emoji.messages.sms.widget.materialdialogs.d paramAnonymousD) {}
    }).a().show();
    this.ac = true;
  }
  
  private void ae()
  {
    this.h.clear();
    ac();
    SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.c);
    Object localObject1;
    if (!localSharedPreferences.getBoolean("keyboard_theme_scanned", false))
    {
      localSharedPreferences.edit().putString("keyboard_theme_pkgs_installed", "").apply();
      localObject1 = this.c.getPackageManager();
    }
    label392:
    for (;;)
    {
      try
      {
        localObject2 = ((PackageManager)localObject1).getInstalledPackages(0);
        localObject1 = "";
        localObject2 = ((List)localObject2).iterator();
        if (((Iterator)localObject2).hasNext())
        {
          localObject3 = (PackageInfo)((Iterator)localObject2).next();
          if ((!c.a(((PackageInfo)localObject3).packageName)) || (!c.a(this.c, ((PackageInfo)localObject3).packageName))) {
            break label392;
          }
          localObject1 = (String)localObject1 + ((PackageInfo)localObject3).packageName + ",";
          break label392;
        }
        localSharedPreferences.edit().putString("keyboard_theme_pkgs_installed", (String)localObject1).apply();
      }
      catch (NullPointerException localNullPointerException)
      {
        Object localObject2;
        Object localObject3;
        int k;
        int j;
        String[] arrayOfString;
        localNullPointerException.printStackTrace();
        continue;
      }
      localSharedPreferences.edit().putBoolean("keyboard_theme_scanned", true).apply();
      localObject1 = localSharedPreferences.getString("keyboard_theme_pkgs_installed", "");
      if (!TextUtils.isEmpty((CharSequence)localObject1))
      {
        localObject1 = ((String)localObject1).split(",", -1);
        k = localObject1.length;
        j = 0;
        if (j < k)
        {
          localObject2 = localObject1[j];
          if (c.a((String)localObject2))
          {
            localObject3 = new b(null);
            ((b)localObject3).a = 17;
            ((b)localObject3).b = p.b(this.c, (String)localObject2);
            arrayOfString = ((String)localObject2).split("\\.");
            if ((arrayOfString.length > 0) && (this.aa.equals(arrayOfString[(arrayOfString.length - 1)]))) {
              localSharedPreferences.edit().putString("keyboard_theme_name", ((b)localObject3).b).apply();
            }
            ((b)localObject3).d = ((String)localObject2);
            ((b)localObject3).c = 0;
            ((b)localObject3).e = false;
            this.h.put(((b)localObject3).d, localObject3);
          }
          j += 1;
          continue;
        }
      }
      return;
    }
  }
  
  private int b(int paramInt1, int paramInt2)
  {
    int j = j().getInteger(2131558434);
    if (paramInt2 < j) {
      return paramInt2;
    }
    return paramInt1 - (paramInt2 + 1 - j);
  }
  
  private boolean c(Context paramContext, String paramString)
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
  
  public View a(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    paramLayoutInflater = paramLayoutInflater.inflate(2130968762, paramViewGroup, false);
    this.d = ((GridView)paramLayoutInflater.findViewById(2131886833));
    this.d.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        a.a(a.this, paramAnonymousInt);
      }
    });
    this.d.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
    {
      public boolean onItemLongClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, final int paramAnonymousInt, long paramAnonymousLong)
      {
        if (paramAnonymousInt + 1 > a.b.length)
        {
          new d.a(a.this.i()).a(2131427591).d(2131427590).e(2131427556).g(2131427501).i(2131755022).k(2131755022).a(new d.b()
          {
            public void a(com.emoji.messages.sms.widget.materialdialogs.d paramAnonymous2D)
            {
              paramAnonymous2D = new Intent("android.intent.action.DELETE", Uri.fromParts("package", ((a.b)a.a(a.this).getItem(paramAnonymousInt)).d, null));
              a.this.startActivityForResult(paramAnonymous2D, 101);
            }
            
            public void b(com.emoji.messages.sms.widget.materialdialogs.d paramAnonymous2D) {}
          }).a().show();
          return true;
        }
        return false;
      }
    });
    int j = j().getInteger(2131558409);
    this.d.setNumColumns(j);
    a(this.d);
    this.d.setAdapter(this.i);
    this.aa = PreferenceManager.getDefaultSharedPreferences(this.c).getString("keyboard_theme_name", "");
    this.i.notifyDataSetChanged();
    return paramLayoutInflater;
  }
  
  public void a(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.a(paramInt1, paramInt2, paramIntent);
    if (paramInt1 == 101)
    {
      PreferenceManager.getDefaultSharedPreferences(this.c).edit().putBoolean("keyboard_theme_scanned", false).apply();
      ae();
      this.i.notifyDataSetChanged();
    }
  }
  
  public void a(Activity paramActivity)
  {
    super.a(paramActivity);
    this.c = paramActivity;
    this.ac = false;
    this.ad = j().getConfiguration().orientation;
  }
  
  void ac()
  {
    this.f = j().getIntArray(2131361854);
    this.g = j().getStringArray(2131361853);
    b localB1 = new b(null);
    localB1.a = this.f[0];
    localB1.b = this.g[0];
    localB1.d = "";
    localB1.c = b[0];
    b localB2 = new b(null);
    localB2.a = this.f[1];
    localB2.b = this.g[1];
    localB2.d = "";
    localB2.c = b[1];
    this.h.put(localB1.b, localB1);
    this.h.put(localB2.b, localB2);
  }
  
  public void c()
  {
    super.c();
  }
  
  public void d()
  {
    super.d();
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    if (paramConfiguration.orientation != this.ad)
    {
      if ((this.d != null) && (this.i != null))
      {
        int j = j().getInteger(2131558409);
        this.d.setNumColumns(j);
        this.i.notifyDataSetChanged();
      }
      this.ad = paramConfiguration.orientation;
    }
  }
  
  public void v()
  {
    super.v();
    MobclickAgent.onPageStart(getClass().getSimpleName());
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("emoji.keyboard.emoticonkeyboard.KAKA_THEME_CHANGED");
    this.c.registerReceiver(this.ae, localIntentFilter);
    this.e = new a();
    this.e.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    if ((PreferenceManager.getDefaultSharedPreferences(this.c).getBoolean("IS_FIRST_LOCAL", true)) && (this.ab != null)) {
      this.ab.start();
    }
  }
  
  public void w()
  {
    super.w();
    MobclickAgent.onPageEnd(getClass().getSimpleName());
    this.c.unregisterReceiver(this.ae);
    this.e.cancel(true);
    if ((PreferenceManager.getDefaultSharedPreferences(this.c).getBoolean("IS_FIRST_LOCAL", true)) && (this.ab != null)) {
      this.ab.cancel();
    }
  }
  
  public void x()
  {
    this.h.clear();
    if (this.d != null) {
      this.d.setAdapter(null);
    }
    System.gc();
    super.x();
  }
  
  class a
    extends AsyncTask<Void, Void, Void>
  {
    a() {}
    
    protected Void a(Void... paramVarArgs)
    {
      a.e(a.this);
      return null;
    }
    
    protected void a(Void paramVoid)
    {
      a.a(a.this, PreferenceManager.getDefaultSharedPreferences(a.b(a.this)).getString("keyboard_theme_name", "Default"));
      a.a(a.this).notifyDataSetChanged();
    }
  }
  
  private class b
  {
    int a;
    String b = "";
    int c;
    String d = "";
    boolean e = false;
    
    private b() {}
  }
  
  final class c
    extends BaseAdapter
  {
    c() {}
    
    public int getCount()
    {
      return a.c(a.this).size();
    }
    
    public Object getItem(int paramInt)
    {
      paramInt = a.a(a.this, a.c(a.this).size(), paramInt);
      a.b[] arrayOfB = (a.b[])a.c(a.this).values().toArray(new a.b[0]);
      if (paramInt < arrayOfB.length) {
        return arrayOfB[paramInt];
      }
      return new a.b(a.this, null);
    }
    
    public long getItemId(int paramInt)
    {
      return 0L;
    }
    
    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      Object localObject;
      if (paramView == null)
      {
        paramView = a.b(a.this).getLayoutInflater().inflate(2130968963, paramViewGroup, false);
        paramViewGroup = new a.d(null);
        paramViewGroup.a = ((TextView)paramView.findViewById(2131886705));
        paramViewGroup.b = ((ImageView)paramView.findViewById(2131886703));
        paramViewGroup.c = ((ImageView)paramView.findViewById(2131887322));
        paramView.setTag(paramViewGroup);
        localObject = paramView.getLayoutParams();
        ((ViewGroup.LayoutParams)localObject).height = a.this.j().getDimensionPixelSize(2131296263);
        paramView.setLayoutParams((ViewGroup.LayoutParams)localObject);
        localObject = (a.b)getItem(paramInt);
        paramViewGroup.a.setText(((a.b)localObject).b);
        if (((a.b)localObject).c == 0) {
          break label195;
        }
        paramViewGroup.b.setImageDrawable(a.this.j().getDrawable(((a.b)localObject).c));
      }
      for (;;)
      {
        if (!a.d(a.this).equals(((a.b)localObject).b)) {
          break label229;
        }
        paramViewGroup.c.setVisibility(0);
        return paramView;
        paramViewGroup = (a.d)paramView.getTag();
        break;
        label195:
        Drawable localDrawable = c.a(a.b(a.this), ((a.b)localObject).d, "msg_preview_img");
        paramViewGroup.b.setImageDrawable(ae.b(localDrawable));
      }
      label229:
      paramViewGroup.c.setVisibility(8);
      return paramView;
    }
  }
  
  private static class d
  {
    TextView a;
    ImageView b;
    ImageView c;
    
    private d() {}
  }
}
