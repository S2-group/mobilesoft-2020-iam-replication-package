package mobi.infolife.ezweather;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.umeng.analytics.MobclickAgent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import mobi.infolife.ezweather.e.a;
import mobi.infolife.utils.d;
import mobi.infolife.utils.y;

public class SwitchNotificationIconSetActivity
  extends Activity
{
  Context a;
  ListView b;
  LinearLayout c;
  List<b> d = new ArrayList();
  String e;
  a f;
  private View.OnClickListener g = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      switch (paramAnonymousView.getId())
      {
      default: 
        return;
      }
      SwitchNotificationIconSetActivity.this.finish();
      SwitchNotificationIconSetActivity.this.overridePendingTransition(0, 0);
    }
  };
  
  public SwitchNotificationIconSetActivity() {}
  
  public static List<PackageInfo> a(Context paramContext, int paramInt)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext = paramContext.getInstalledPackages(paramInt);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  private void a()
  {
    String str = e.c(this.a, this.e);
    if ((!"mobi.infolife.ezweather.iconset.default".equals(str)) && (!a.a(this.a.getApplicationContext(), str))) {
      a(this.e, "mobi.infolife.ezweather.iconset.default", this.a.getString(2131231428));
    }
    for (;;)
    {
      return;
      int i = 0;
      while (i < this.d.size())
      {
        if (str.equals(((b)this.d.get(i)).b()))
        {
          this.f.a(i);
          return;
        }
        i += 1;
      }
    }
  }
  
  private void a(Context paramContext)
  {
    Object localObject1 = new b(paramContext.getString(2131231428), "mobi.infolife.ezweather.iconset.default");
    Object localObject3 = new b(paramContext.getString(2131231428) + " New", "mobi.infolife.ezweather.iconset.default.new");
    this.d.add(localObject1);
    this.d.add(localObject3);
    localObject3 = paramContext.getPackageManager();
    Iterator localIterator = a(paramContext, 8192).iterator();
    paramContext = null;
    label234:
    for (;;)
    {
      if (localIterator.hasNext())
      {
        String str = ((PackageInfo)localIterator.next()).packageName;
        try
        {
          localObject1 = ((PackageManager)localObject3).getApplicationInfo(str, 128);
          paramContext = (Context)localObject1;
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          for (;;)
          {
            localNameNotFoundException.printStackTrace();
          }
          localObject2 = paramContext.metaData.getString("EZWEATHER_PLUGIN");
          if (localObject2 == null) {
            break label234;
          }
        }
        if (paramContext != null)
        {
          Object localObject2;
          if ((paramContext.metaData != null) && (!"".equals(localObject2))) {
            if ((((String)localObject2).equals("mobi.infolife.ezweather.plugin.iconset")) && (a(str)))
            {
              localObject2 = new b(((PackageManager)localObject3).getApplicationLabel(paramContext).toString(), str);
              this.d.add(localObject2);
            }
          }
        }
      }
      else
      {
        return;
      }
    }
  }
  
  private void a(String paramString1, String paramString2, String paramString3)
  {
    e.b(this.a, paramString1, paramString2);
    e.a(this.a, paramString1, paramString3);
    y.k(this.a);
  }
  
  private boolean a(String paramString)
  {
    Context localContext = d.c(this.a, paramString);
    if (localContext == null) {
      return false;
    }
    paramString = d.d(localContext, paramString);
    try
    {
      boolean bool = localContext.getResources().getBoolean(d.a(paramString, "bool", "isAllowChange"));
      return bool;
    }
    catch (Exception paramString) {}
    return false;
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903082);
    this.a = this;
    paramBundle = g.a(this.a).a("Roboto Regular.ttf");
    this.b = ((ListView)findViewById(2131689755));
    ((TextView)findViewById(2131689622)).setTypeface(paramBundle);
    this.c = ((LinearLayout)findViewById(2131689757));
    this.b.setDivider(null);
    this.e = getIntent().getStringExtra("notification_theme_package_name");
    a(this.a);
    this.f = new a(this.a);
    this.b.setAdapter(this.f);
    a();
    this.c.setOnClickListener(this.g);
    this.b.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        paramAnonymousAdapterView = (SwitchNotificationIconSetActivity.b)SwitchNotificationIconSetActivity.this.d.get(paramAnonymousInt);
        SwitchNotificationIconSetActivity.this.f.a(paramAnonymousInt);
        SwitchNotificationIconSetActivity.a(SwitchNotificationIconSetActivity.this, SwitchNotificationIconSetActivity.this.e, paramAnonymousAdapterView.b(), paramAnonymousAdapterView.a());
      }
    });
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(2131755012, paramMenu);
    return true;
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    return (paramMenuItem.getItemId() == 2131691031) || (super.onOptionsItemSelected(paramMenuItem));
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
  
  class a
    extends BaseAdapter
  {
    private LayoutInflater b;
    private int c = 0;
    
    a(Context paramContext)
    {
      this.b = LayoutInflater.from(paramContext);
    }
    
    int a()
    {
      return this.c;
    }
    
    void a(int paramInt)
    {
      this.c = paramInt;
      notifyDataSetChanged();
    }
    
    public int getCount()
    {
      return SwitchNotificationIconSetActivity.this.d.size();
    }
    
    public Object getItem(int paramInt)
    {
      return SwitchNotificationIconSetActivity.this.d.get(paramInt);
    }
    
    public long getItemId(int paramInt)
    {
      return paramInt;
    }
    
    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      Typeface localTypeface = g.a(SwitchNotificationIconSetActivity.this.a).a("roboto light.ttf");
      if (paramView == null)
      {
        paramView = this.b.inflate(2130903187, null);
        paramViewGroup = new SwitchNotificationIconSetActivity.a.a(this);
        paramViewGroup.a = ((LinearLayout)SwitchNotificationIconSetActivity.this.findViewById(2131690181));
        paramViewGroup.c = ((TextView)paramView.findViewById(2131690183));
        paramViewGroup.b = ((ImageView)paramView.findViewById(2131690182));
        paramView.setTag(paramViewGroup);
      }
      for (;;)
      {
        paramViewGroup.c.setText(((SwitchNotificationIconSetActivity.b)SwitchNotificationIconSetActivity.this.d.get(paramInt)).a());
        paramViewGroup.c.setTypeface(localTypeface);
        if (paramInt != a()) {
          break;
        }
        paramViewGroup.b.setImageResource(2130838012);
        return paramView;
        paramViewGroup = (SwitchNotificationIconSetActivity.a.a)paramView.getTag();
      }
      paramViewGroup.b.setImageResource(2130838011);
      return paramView;
    }
  }
  
  private class b
  {
    private String b;
    private String c;
    
    b(String paramString1, String paramString2)
    {
      this.b = paramString1;
      this.c = paramString2;
    }
    
    public String a()
    {
      return this.b;
    }
    
    public String b()
    {
      return this.c;
    }
  }
}
