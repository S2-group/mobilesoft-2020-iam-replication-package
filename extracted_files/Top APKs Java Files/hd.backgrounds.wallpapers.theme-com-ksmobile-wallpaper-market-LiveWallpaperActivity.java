package com.ksmobile.wallpaper.market;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ac;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView.h;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ViewSwitcher.ViewFactory;
import com.a.a.a.h;
import com.a.a.a.h.c;
import com.a.a.a.h.d;
import com.a.a.u;
import com.ksmobile.wallpaper.commonutils.a.a;
import com.ksmobile.wallpaper.commonutils.s;
import com.ksmobile.wallpaper.data.api.wallpaper.entity.WallPapersInfo;
import com.ksmobile.wallpaper.data.api.wallpaper.entity.Wallpaper;
import com.ksmobile.wallpaper.data.model.WallpaperFavoriteModel;
import com.ksmobile.wallpaper.data.model.WallpaperShowListModel;
import com.ksmobile.wallpaper.market.adapter.e;
import com.ksmobile.wallpaper.market.b.h.a;
import com.ksmobile.wallpaper.market.i.f;
import com.ksmobile.wallpaper.market.userbehavior.g;
import com.ksmobile.wallpaper.market.view.LoadMoreRecyclerView;
import com.ksmobile.wallpaper.market.view.LoadMoreRecyclerView.b;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LiveWallpaperActivity
  extends Activity
  implements View.OnClickListener, ViewSwitcher.ViewFactory
{
  private LoadMoreRecyclerView a;
  private e b;
  private com.ksmobile.wallpaper.market.b.h.b c;
  private c d;
  private WallpaperFavoriteModel e;
  private FrameLayout f;
  private ViewPager g;
  private b h;
  private Button i;
  private ImageView j;
  private TextView k;
  private ImageSwitcher l;
  private Wallpaper m;
  private boolean n = false;
  private boolean o;
  private a p;
  private d q;
  private Runnable r;
  private int s;
  private WallpaperShowListModel t;
  private long u;
  
  public LiveWallpaperActivity() {}
  
  public static Intent a(Context paramContext, Wallpaper paramWallpaper, boolean paramBoolean)
  {
    paramContext = new Intent(paramContext, LiveWallpaperActivity.class);
    paramContext.putExtra("open_live_wallpaper_info", paramWallpaper);
    paramContext.putExtra("open_live_wallpaper_type", paramBoolean);
    return paramContext;
  }
  
  private void a()
  {
    this.a = ((LoadMoreRecyclerView)findViewById(2131624144));
    this.i = ((Button)findViewById(2131624148));
    this.j = ((ImageView)findViewById(2131624146));
    this.l = ((ImageSwitcher)findViewById(2131624147));
    this.k = ((TextView)findViewById(2131624123));
    this.f = ((FrameLayout)findViewById(2131624149));
    this.g = ((ViewPager)findViewById(2131624150));
  }
  
  private void a(int paramInt)
  {
    if (this.s == 13) {}
    for (String str1 = "4";; str1 = "1")
    {
      String str2 = "0";
      if (this.m != null) {
        str2 = this.m.aliasTitle;
      }
      g.a(false, "beautify_apkwp_wallpaper_detail", new String[] { "class", str1, "click", paramInt + "", "wtype", "2", "wid", str2, "tab", "4" });
      return;
    }
  }
  
  private void a(final Wallpaper paramWallpaper)
  {
    if ((paramWallpaper == null) || (this.l == null)) {
      return;
    }
    this.l.setClickable(false);
    s.a(0, new Runnable()
    {
      public void run()
      {
        if (LiveWallpaperActivity.b(LiveWallpaperActivity.this) == null) {
          LiveWallpaperActivity.a(LiveWallpaperActivity.this, new WallpaperFavoriteModel(LiveWallpaperActivity.c(LiveWallpaperActivity.this).getContext()));
        }
        boolean bool = LiveWallpaperActivity.b(LiveWallpaperActivity.this).isFavoriteById(paramWallpaper);
        LiveWallpaperActivity.c(LiveWallpaperActivity.this).setSelected(bool);
        LiveWallpaperActivity.c(LiveWallpaperActivity.this).setInAnimation(null);
        if (bool) {
          LiveWallpaperActivity.c(LiveWallpaperActivity.this).setImageResource(2130837628);
        }
        for (;;)
        {
          LiveWallpaperActivity.c(LiveWallpaperActivity.this).setClickable(true);
          return;
          LiveWallpaperActivity.c(LiveWallpaperActivity.this).setImageResource(2130837626);
        }
      }
    });
  }
  
  private void a(String paramString1, String paramString2, JSONArray paramJSONArray)
  {
    if (this.t == null) {
      this.t = new WallpaperShowListModel();
    }
    if ((this.c != null) && (this.c.f() != null)) {
      this.t.reportShowWallpaperList(paramString1, paramString2, this.c.f().upack, paramJSONArray.toString());
    }
  }
  
  private void a(String paramString, boolean paramBoolean)
  {
    if (this.m == null) {
      return;
    }
    String str1;
    Object localObject;
    JSONArray localJSONArray;
    if (this.s == 13)
    {
      str1 = "124";
      localObject = new JSONObject();
      localJSONArray = new JSONArray();
      if (paramBoolean) {
        break label169;
      }
    }
    for (;;)
    {
      try
      {
        ((JSONObject)localObject).put("ac", paramString);
        ((JSONObject)localObject).put("pos", str1);
        ((JSONObject)localObject).put("id", this.m.aliasTitle);
        ((JSONObject)localObject).put("cpack", this.m.cpack);
        ((JSONObject)localObject).put("seq", "0");
        ((JSONObject)localObject).put("ctime", System.currentTimeMillis() + "");
        localJSONArray.put(localObject);
        a(paramString, str1, localJSONArray);
        return;
        str1 = "122";
      }
      catch (JSONException localJSONException1)
      {
        localJSONException1.printStackTrace();
        continue;
      }
      label169:
      if (this.b == null) {
        break;
      }
      String str2 = System.currentTimeMillis() - this.u + "";
      localObject = new JSONObject();
      try
      {
        ((JSONObject)localObject).put("ac", "11");
        ((JSONObject)localObject).put("pos", str1);
        ((JSONObject)localObject).put("id", this.m.aliasTitle);
        ((JSONObject)localObject).put("cpack", this.m.cpack);
        ((JSONObject)localObject).put("seq", "0");
        ((JSONObject)localObject).put("ktime", str2);
        localJSONArray.put(localObject);
        localObject = f.a(this.b.a);
        if (localObject != null)
        {
          i1 = 0;
          if (i1 >= ((JSONArray)localObject).length()) {}
        }
      }
      catch (JSONException localJSONException2)
      {
        try
        {
          for (;;)
          {
            int i1;
            localJSONArray.put((JSONObject)((JSONArray)localObject).get(i1));
            i1 += 1;
          }
          localJSONException2 = localJSONException2;
          localJSONException2.printStackTrace();
        }
        catch (JSONException localJSONException3)
        {
          for (;;)
          {
            localJSONException3.printStackTrace();
          }
        }
        this.b.a.clear();
      }
    }
  }
  
  private void b()
  {
    if (this.a != null)
    {
      Object localObject = (LiveWallpaperHeadView)LayoutInflater.from(this.a.getContext()).inflate(2130968632, null, false);
      ((LiveWallpaperHeadView)localObject).setClickListener(new e(null));
      this.a.h((View)localObject);
      ((LiveWallpaperHeadView)localObject).a(this.m.previews);
      ((LiveWallpaperHeadView)localObject).setAuthorName(this.m.title);
      this.a.setHasFixedSize(true);
      this.a.setVerticalScrollBarEnabled(false);
      localObject = new LinearLayoutManager(this.a.getContext());
      ((LinearLayoutManager)localObject).b(1);
      this.a.setLayoutManager((RecyclerView.h)localObject);
      if (this.b == null) {
        this.b = new e(this.a.getContext(), 11);
      }
      this.b.a(true);
      this.b.a(this);
      this.a.setAdapter(this.b);
      localObject = (FrameLayout)LayoutInflater.from(this.a.getContext()).inflate(2130968666, null);
      ProgressBar localProgressBar = (ProgressBar)findViewById(2131624234);
      if (localProgressBar != null) {
        localProgressBar.setIndeterminateDrawable(new com.ksmobile.wallpaper.market.view.d(this.a.getContext(), 3));
      }
      this.a.setLoadMoreView((View)localObject);
    }
    if (this.l != null)
    {
      this.l.setOnClickListener(this);
      this.l.setFactory(this);
      a(this.m);
    }
    if (this.i != null) {
      this.i.setOnClickListener(this);
    }
    if (this.j != null) {
      this.j.setOnClickListener(this);
    }
    if ((this.n) && (this.l != null)) {
      this.l.setVisibility(4);
    }
  }
  
  private void c()
  {
    if (this.a != null)
    {
      this.d = new c();
      this.c = new com.ksmobile.wallpaper.market.b.h.b(this.a.getContext(), this.d, this.m.aliasTitle, "122");
      this.a.setOnLoadMoreListener(new LoadMoreRecyclerView.b()
      {
        public void a()
        {
          LiveWallpaperActivity.a(LiveWallpaperActivity.this).d();
        }
      });
      this.c.a();
    }
    this.h = new b();
    this.g.setAdapter(this.h);
  }
  
  private void d()
  {
    int i1 = 1;
    final boolean bool1;
    if (a.a().i() < 1)
    {
      int i2 = a.a().f();
      int i3 = a.a().g();
      if (i2 < 3) {
        break label85;
      }
      bool1 = true;
      if (i3 < 2) {
        break label91;
      }
    }
    for (;;)
    {
      boolean bool2 = a.a().j();
      if (((bool1) || (i1 != 0)) && (bool2))
      {
        a.a().h();
        s.a(0, new Runnable()
        {
          public void run()
          {
            Intent localIntent = new Intent(LiveWallpaperActivity.this, RatingActivity.class);
            if (bool1) {}
            for (String str = "1";; str = "2")
            {
              localIntent.putExtra("START_BY", str);
              LiveWallpaperActivity.this.startActivity(localIntent);
              return;
            }
          }
        }, 500L);
      }
      return;
      label85:
      bool1 = false;
      break;
      label91:
      i1 = 0;
    }
  }
  
  private void e()
  {
    if (this.r == null) {
      this.r = new Runnable()
      {
        public void run()
        {
          LiveWallpaperActivity.a(LiveWallpaperActivity.this, true);
          if ((!LiveWallpaperActivity.this.isFinishing()) && (LiveWallpaperActivity.d(LiveWallpaperActivity.this) != null)) {
            LiveWallpaperActivity.d(LiveWallpaperActivity.this).setText(LiveWallpaperActivity.this.getString(2131165264));
          }
        }
      };
    }
    if (!TextUtils.isEmpty(this.m.mPackageName))
    {
      this.q = new d(this.m.mPackageName, this.r);
      s.a(2, this.q);
    }
  }
  
  public View makeView()
  {
    ImageView localImageView = new ImageView(this);
    localImageView.setImageResource(2130837626);
    return localImageView;
  }
  
  public void onBackPressed()
  {
    if ((this.f != null) && (this.f.getVisibility() == 0))
    {
      this.f.setVisibility(4);
      return;
    }
    super.onBackPressed();
  }
  
  public void onClick(View paramView)
  {
    if (2131624148 == paramView.getId()) {
      if (this.o)
      {
        startActivity(getPackageManager().getLaunchIntentForPackage(this.m.mPackageName));
        a(8);
        a("17", false);
      }
    }
    label347:
    label398:
    label403:
    do
    {
      return;
      com.ksmobile.wallpaper.market.i.d.a(this, this.m.lwpApkUrl + "&referrer=utm_source%3Dcml_wallpaperapk_lwp");
      a(1);
      a("12", false);
      return;
      if (2131624147 == paramView.getId())
      {
        if (this.e == null) {
          this.e = new WallpaperFavoriteModel(this);
        }
        Object localObject2 = "";
        Object localObject1 = localObject2;
        if (this.c != null)
        {
          localObject1 = localObject2;
          if (this.c.f() != null)
          {
            localObject1 = localObject2;
            if (this.c.f().upack != null) {
              localObject1 = this.c.f().upack;
            }
          }
        }
        this.e.favoriteOrNot(this.m, null, f.a(11), (String)localObject1, this.m.cpack, System.currentTimeMillis() + "");
        localObject1 = new ScaleAnimation(0.0F, 1.05F, 0.0F, 1.05F, 1, 0.5F, 1, 0.5F);
        ((ScaleAnimation)localObject1).setInterpolator(new OvershootInterpolator());
        localObject2 = new AlphaAnimation(0.0F, 1.0F);
        AnimationSet localAnimationSet = new AnimationSet(false);
        localAnimationSet.addAnimation((Animation)localObject1);
        localAnimationSet.addAnimation((Animation)localObject2);
        localAnimationSet.setDuration(400L);
        ((ImageSwitcher)paramView).setInAnimation(localAnimationSet);
        localObject1 = (ImageSwitcher)paramView;
        int i1;
        if (paramView.isSelected())
        {
          i1 = 2130837626;
          ((ImageSwitcher)localObject1).setImageResource(i1);
          if (paramView.isSelected()) {
            break label398;
          }
          i1 = 1;
          if (paramView.isSelected()) {
            break label403;
          }
        }
        for (boolean bool = true;; bool = false)
        {
          paramView.setSelected(bool);
          if (i1 != 0)
          {
            i1 = a.a().g();
            a.a().c(i1 + 1);
            d();
          }
          a(2);
          return;
          i1 = 2130837628;
          break;
          i1 = 0;
          break label347;
        }
      }
      if (2131624146 == paramView.getId())
      {
        a(51);
        finish();
        return;
      }
    } while ((paramView.getTag() == null) || (!(paramView.getTag() instanceof Wallpaper)));
    paramView = (Wallpaper)paramView.getTag();
    com.ksmobile.wallpaper.market.i.d.a(this, paramView.lwpApkUrl + "&referrer=utm_source%3Dcml_wallpaperapk_lwp");
    a(5);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    paramBundle = getIntent();
    if (paramBundle == null)
    {
      finish();
      return;
    }
    this.s = paramBundle.getIntExtra("type", -1);
    this.m = ((Wallpaper)paramBundle.getParcelableExtra("open_live_wallpaper_info"));
    if (this.m == null)
    {
      finish();
      return;
    }
    this.n = paramBundle.getBooleanExtra("open_live_wallpaper_type", false);
    setContentView(2130968630);
    a();
    b();
    c();
    e();
    this.u = System.currentTimeMillis();
    a(0);
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    if (this.q != null)
    {
      s.b(2, this.q);
      this.q = null;
    }
    if (this.r != null)
    {
      s.b(2, this.r);
      this.r = null;
    }
  }
  
  protected void onPause()
  {
    super.onPause();
    a("11", true);
    this.u = System.currentTimeMillis();
  }
  
  protected void onResume()
  {
    super.onResume();
    e();
  }
  
  private class a
    implements View.OnClickListener
  {
    private a() {}
    
    public void onClick(View paramView)
    {
      if ((LiveWallpaperActivity.j(LiveWallpaperActivity.this) != null) && (LiveWallpaperActivity.j(LiveWallpaperActivity.this).getVisibility() == 0)) {
        LiveWallpaperActivity.j(LiveWallpaperActivity.this).setVisibility(4);
      }
    }
  }
  
  private class b
    extends ac
  {
    private List<String> b = new ArrayList();
    
    public b() {}
    
    public Object a(ViewGroup paramViewGroup, int paramInt)
    {
      final ImageView localImageView = new ImageView(paramViewGroup.getContext());
      String str = a(paramInt);
      Object localObject = h.a(str, 0, 0, ImageView.ScaleType.CENTER_INSIDE);
      localObject = com.ksmobile.wallpaper.market.f.d.a().b().a().a((String)localObject);
      if (localObject == null) {
        com.ksmobile.wallpaper.market.f.d.a().b().a(str, new h.d()
        {
          public void onErrorResponse(u paramAnonymousU) {}
          
          public void onResponse(h.c paramAnonymousC, boolean paramAnonymousBoolean)
          {
            if (paramAnonymousC.b() != null) {
              localImageView.setImageBitmap(paramAnonymousC.b());
            }
          }
        });
      }
      localImageView.setImageBitmap((Bitmap)localObject);
      localImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
      if (LiveWallpaperActivity.l(LiveWallpaperActivity.this) == null) {
        LiveWallpaperActivity.a(LiveWallpaperActivity.this, new LiveWallpaperActivity.a(LiveWallpaperActivity.this, null));
      }
      localImageView.setOnClickListener(LiveWallpaperActivity.l(LiveWallpaperActivity.this));
      paramViewGroup.addView(localImageView);
      return localImageView;
    }
    
    public String a(int paramInt)
    {
      if ((paramInt < 0) || (paramInt >= this.b.size())) {
        return "";
      }
      return (String)this.b.get(paramInt);
    }
    
    public void a(ViewGroup paramViewGroup, int paramInt, Object paramObject)
    {
      paramViewGroup.removeView((ImageView)paramObject);
    }
    
    public void a(List<String> paramList)
    {
      this.b.clear();
      this.b.addAll(paramList);
    }
    
    public boolean a(View paramView, Object paramObject)
    {
      return paramView == paramObject;
    }
    
    public int b()
    {
      return this.b.size();
    }
  }
  
  private class c
    implements h.a
  {
    public c() {}
    
    public void a()
    {
      if ((LiveWallpaperActivity.e(LiveWallpaperActivity.this) != null) && (LiveWallpaperActivity.e(LiveWallpaperActivity.this).getLoadMoreView() != null))
      {
        LiveWallpaperActivity.e(LiveWallpaperActivity.this).getLoadMoreView().setVisibility(0);
        LiveWallpaperActivity.e(LiveWallpaperActivity.this).findViewById(2131624235).setVisibility(0);
        LiveWallpaperActivity.e(LiveWallpaperActivity.this).findViewById(2131624234).setVisibility(8);
      }
      if (LiveWallpaperActivity.f(LiveWallpaperActivity.this) != null) {
        LiveWallpaperActivity.f(LiveWallpaperActivity.this).setVisibility(8);
      }
    }
    
    public void a(List<Wallpaper> paramList)
    {
      if (LiveWallpaperActivity.e(LiveWallpaperActivity.this) != null) {
        LiveWallpaperActivity.e(LiveWallpaperActivity.this).s();
      }
      if (LiveWallpaperActivity.g(LiveWallpaperActivity.this) != null)
      {
        LiveWallpaperActivity.g(LiveWallpaperActivity.this).a(paramList);
        LiveWallpaperActivity.g(LiveWallpaperActivity.this).e();
      }
    }
    
    public void b()
    {
      if (LiveWallpaperActivity.e(LiveWallpaperActivity.this) != null)
      {
        if (LiveWallpaperActivity.e(LiveWallpaperActivity.this).getLoadMoreView() != null)
        {
          LiveWallpaperActivity.e(LiveWallpaperActivity.this).getLoadMoreView().setVisibility(8);
          LiveWallpaperActivity.e(LiveWallpaperActivity.this).findViewById(2131624235).setVisibility(8);
          LiveWallpaperActivity.e(LiveWallpaperActivity.this).findViewById(2131624234).setVisibility(0);
          LiveWallpaperActivity.e(LiveWallpaperActivity.this).findViewById(2131624236).setVisibility(8);
        }
        LiveWallpaperActivity.e(LiveWallpaperActivity.this).setCanLoadMore(false);
      }
    }
    
    public void c()
    {
      if ((LiveWallpaperActivity.e(LiveWallpaperActivity.this) != null) && (LiveWallpaperActivity.e(LiveWallpaperActivity.this).getLoadMoreView() != null))
      {
        LiveWallpaperActivity.e(LiveWallpaperActivity.this).getLoadMoreView().setVisibility(0);
        LiveWallpaperActivity.e(LiveWallpaperActivity.this).findViewById(2131624235).setVisibility(8);
        LiveWallpaperActivity.e(LiveWallpaperActivity.this).findViewById(2131624234).setVisibility(0);
        LiveWallpaperActivity.e(LiveWallpaperActivity.this).findViewById(2131624236).setVisibility(8);
      }
      if (LiveWallpaperActivity.f(LiveWallpaperActivity.this) != null) {
        LiveWallpaperActivity.f(LiveWallpaperActivity.this).setVisibility(8);
      }
    }
    
    public void d()
    {
      if ((LiveWallpaperActivity.e(LiveWallpaperActivity.this) != null) && (LiveWallpaperActivity.e(LiveWallpaperActivity.this).getLoadMoreView() != null))
      {
        LiveWallpaperActivity.e(LiveWallpaperActivity.this).getLoadMoreView().setVisibility(8);
        LiveWallpaperActivity.e(LiveWallpaperActivity.this).setCanLoadMore(false);
      }
      if (LiveWallpaperActivity.f(LiveWallpaperActivity.this) != null) {
        LiveWallpaperActivity.f(LiveWallpaperActivity.this).setVisibility(0);
      }
    }
    
    public Context e()
    {
      if (LiveWallpaperActivity.e(LiveWallpaperActivity.this) == null) {
        return LiveWallpaperActivity.this.getApplicationContext();
      }
      return LiveWallpaperActivity.e(LiveWallpaperActivity.this).getContext();
    }
  }
  
  private class d
    implements Runnable
  {
    private String b;
    private Runnable c;
    
    public d(String paramString, Runnable paramRunnable)
    {
      this.b = paramString;
      this.c = paramRunnable;
    }
    
    private boolean a(String paramString)
    {
      if (TextUtils.isEmpty(paramString)) {}
      for (;;)
      {
        return false;
        List localList = LiveWallpaperActivity.this.getPackageManager().getInstalledPackages(0);
        int i = 0;
        while (i < localList.size())
        {
          if (((PackageInfo)localList.get(i)).packageName.equals(paramString)) {
            return true;
          }
          i += 1;
        }
      }
    }
    
    public void run()
    {
      if ((a(this.b)) && (this.c != null)) {
        s.a(0, this.c);
      }
    }
  }
  
  private class e
    implements View.OnClickListener
  {
    private e() {}
    
    public void onClick(View paramView)
    {
      if ((LiveWallpaperActivity.h(LiveWallpaperActivity.this) != null) && (LiveWallpaperActivity.i(LiveWallpaperActivity.this).previews != null))
      {
        LiveWallpaperActivity.j(LiveWallpaperActivity.this).setVisibility(0);
        LiveWallpaperActivity.h(LiveWallpaperActivity.this).a(LiveWallpaperActivity.i(LiveWallpaperActivity.this).previews);
        LiveWallpaperActivity.h(LiveWallpaperActivity.this).c();
        if (paramView.getTag() == null) {
          break label103;
        }
      }
      label103:
      for (int i = ((Integer)paramView.getTag()).intValue();; i = 0)
      {
        LiveWallpaperActivity.k(LiveWallpaperActivity.this).a(i, false);
        LiveWallpaperActivity.a(LiveWallpaperActivity.this, 4);
        return;
      }
    }
  }
}
