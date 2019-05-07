package jack.martin.online;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.i;
import android.support.v4.app.j;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import in.srain.cube.views.GridViewWithHeaderAndFooter;
import jack.martin.mykeyboard.myphotokeyboard.SubThemeActivity;
import jack.martin.mykeyboard.myphotokeyboard.y;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@TargetApi(11)
public class e
  extends i
{
  int a = 0;
  View ae;
  ImageView af;
  int ag;
  int ah;
  private String ai = "THEME_PREFS";
  ArrayList<f> b = new ArrayList();
  View c;
  String d;
  c e;
  String[] f;
  boolean g;
  SharedPreferences h;
  GridViewWithHeaderAndFooter i;
  
  public e() {}
  
  private void b()
  {
    if (this.h != null)
    {
      this.d = this.h.getString("selectedTheme", "0galaxy");
      this.g = this.h.getBoolean("onlineThemeSelected", false);
      if (!this.g)
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("file:///android_asset/albums/");
        ((StringBuilder)localObject).append(this.h.getString("folderName", "0galaxy"));
        ((StringBuilder)localObject).append(".png");
      }
      for (Object localObject = ((StringBuilder)localObject).toString();; localObject = this.h.getString("packName", ListOnlineThemeActivity.p.getPackageName()))
      {
        this.d = ((String)localObject);
        break;
      }
      y.bi = this.h.getBoolean("staticTheme", true);
    }
    if (y.bi)
    {
      this.af.setVisibility(0);
      this.d = "Static Selected";
    }
    else
    {
      this.af.setVisibility(8);
    }
    this.e.a(this.d);
  }
  
  private String[] b(String paramString)
  {
    return ListOnlineThemeActivity.p.getAssets().list(paramString);
  }
  
  public View a(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.c = paramLayoutInflater.inflate(2131361905, paramViewGroup, false);
    this.h = ListOnlineThemeActivity.p.getSharedPreferences(this.ai, 0);
    paramLayoutInflater = k().getResources().getDisplayMetrics();
    this.ag = paramLayoutInflater.widthPixels;
    this.ah = paramLayoutInflater.heightPixels;
    this.d = this.h.getString("selectedTheme", "0galaxy");
    this.g = this.h.getBoolean("onlineThemeSelected", false);
    if (!this.g)
    {
      paramLayoutInflater = new StringBuilder();
      paramLayoutInflater.append("file:///android_asset/albums");
      paramLayoutInflater.append(this.h.getString("folderName", "0galaxy"));
      paramLayoutInflater.append(".png");
    }
    for (paramLayoutInflater = paramLayoutInflater.toString();; paramLayoutInflater = this.h.getString("packName", ListOnlineThemeActivity.p.getPackageName()))
    {
      this.d = paramLayoutInflater;
      break;
    }
    this.i = ((GridViewWithHeaderAndFooter)this.c.findViewById(2131230990));
    this.ae = ListOnlineThemeActivity.p.getLayoutInflater().inflate(2131361876, null);
    this.ae.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramAnonymousView = new Intent(e.this.k(), SubThemeActivity.class);
        paramAnonymousView.putExtra("staticTheme", true);
        paramAnonymousView.putExtra("folderName", "staticTheme");
        paramAnonymousView.putExtra("folderPosition", 0);
        e.this.startActivityForResult(paramAnonymousView, 125);
      }
    });
    return this.c;
  }
  
  public void a(int paramInt1, int paramInt2, Intent paramIntent)
  {
    b();
    super.a(paramInt1, paramInt2, paramIntent);
  }
  
  public void d()
  {
    if (this.b != null) {
      this.b.clear();
    }
    if (this.h != null)
    {
      this.d = this.h.getString("selectedTheme", "0galaxy");
      this.g = this.h.getBoolean("onlineThemeSelected", false);
      if (!this.g)
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("file:///android_asset/albums/");
        ((StringBuilder)localObject).append(this.h.getString("folderName", "0galaxy"));
        ((StringBuilder)localObject).append(".png");
      }
      for (Object localObject = ((StringBuilder)localObject).toString();; localObject = this.h.getString("packName", ListOnlineThemeActivity.p.getPackageName()))
      {
        this.d = ((String)localObject);
        break;
      }
      y.bi = this.h.getBoolean("staticTheme", true);
    }
    if (y.bk) {
      new a(null).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[0]);
    } else {
      new a(null).execute(new String[0]);
    }
    super.d();
  }
  
  private class a
    extends AsyncTask<String, Void, ArrayList<f>>
    implements AdapterView.OnItemClickListener
  {
    private a() {}
    
    protected ArrayList<f> a(String... paramVarArgs)
    {
      try
      {
        e.this.f = e.a(e.this, "albums");
      }
      catch (IOException paramVarArgs)
      {
        paramVarArgs.printStackTrace();
      }
      int i = 0;
      Object localObject1;
      while (i < e.this.f.length)
      {
        paramVarArgs = e.this.b;
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("file:///android_asset/albums/");
        ((StringBuilder)localObject1).append(e.this.f[i]);
        paramVarArgs.add(new f(((StringBuilder)localObject1).toString(), true));
        i += 1;
      }
      for (;;)
      {
        try
        {
          localObject1 = ListOnlineThemeActivity.p.getPackageManager().getInstalledApplications(128);
          paramVarArgs = ListOnlineThemeActivity.p.getPackageName();
          localObject1 = ((List)localObject1).iterator();
          if (((Iterator)localObject1).hasNext())
          {
            localObject2 = (ApplicationInfo)((Iterator)localObject1).next();
            if ((!((ApplicationInfo)localObject2).packageName.contains("jack.martin.mykeyboard.myphotokeyboard")) || (((ApplicationInfo)localObject2).packageName.equals(paramVarArgs))) {
              continue;
            }
            e.this.b.add(new f(((ApplicationInfo)localObject2).packageName, false));
            continue;
          }
        }
        catch (Exception paramVarArgs)
        {
          Object localObject2;
          continue;
        }
        try
        {
          paramVarArgs = new Intent("android.intent.action.MAIN");
          paramVarArgs.addCategory("android.intent.category.HOME");
          localObject1 = ListOnlineThemeActivity.p.getPackageManager().queryIntentActivities(paramVarArgs, 0);
          paramVarArgs = ListOnlineThemeActivity.p.getPackageName();
          localObject1 = ((List)localObject1).iterator();
          if (((Iterator)localObject1).hasNext())
          {
            localObject2 = (ResolveInfo)((Iterator)localObject1).next();
            if ((!((ResolveInfo)localObject2).activityInfo.packageName.contains("jack.martin.mykeyboard.myphotokeyboard")) || (((ResolveInfo)localObject2).activityInfo.packageName.equals(paramVarArgs))) {
              continue;
            }
            e.this.b.add(new f(((ResolveInfo)localObject2).activityInfo.packageName, false));
          }
        }
        catch (Exception paramVarArgs) {}
      }
      return e.this.b;
    }
    
    protected void a(ArrayList<f> paramArrayList)
    {
      e.this.af = ((ImageView)e.this.ae.findViewById(2131231031));
      if (y.bi)
      {
        e.this.af.setVisibility(0);
        e.this.d = "Static Selected";
      }
      else
      {
        e.this.af.setVisibility(8);
      }
      e.this.e = new c(ListOnlineThemeActivity.p, e.this.b, e.this.d);
      if (e.this.i.getHeaderViewCount() == 0) {
        e.this.i.a(e.this.ae);
      }
      e.this.i.setAdapter(e.this.e);
      e.this.i.setEnabled(true);
      e.this.i.setOnItemClickListener(this);
      ListOnlineThemeActivity.p.h();
      super.onPostExecute(paramArrayList);
    }
    
    public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
    {
      if (e.this.h.getBoolean("doRate", false))
      {
        paramAdapterView = e.this.h.edit();
        paramAdapterView.putInt("rateCount", 2);
        paramAdapterView.commit();
      }
      paramAdapterView = (f)e.this.b.get(paramInt);
      if (paramAdapterView.b)
      {
        paramAdapterView = new Intent(e.this.k(), SubThemeActivity.class);
        paramAdapterView.putExtra("folderName", e.this.f[paramInt]);
        paramAdapterView.putExtra("folderPosition", paramInt + 1);
        e.this.startActivityForResult(paramAdapterView, 125);
        return;
      }
      paramView = new Intent(e.this.k(), OnlineSubThemeActivity.class);
      paramView.putExtra("packName", paramAdapterView.a);
      e.this.startActivityForResult(paramView, 125);
    }
    
    protected void onPreExecute()
    {
      ListOnlineThemeActivity.p.g();
      e.this.i.setEnabled(false);
      super.onPreExecute();
    }
  }
}
