import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.a;
import android.support.v7.widget.RecyclerView.b;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.luutinhit.customui.GoogleProgressBar;
import com.luutinhit.customui.RelativeLayoutAnim;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public final class ckx
  extends RelativeLayout
  implements View.OnClickListener, View.OnLongClickListener, ciy.a
{
  private String a = "FavoriteView";
  private Context b;
  private PackageManager c;
  private Animation d;
  private Animation e;
  private RelativeLayoutAnim f;
  private RelativeLayoutAnim g;
  private RecyclerView h;
  private GoogleProgressBar i;
  private ciy j;
  private ArrayList<String> k = new ArrayList(Arrays.asList(new String[] { "none", "none", "none", "none", "none", "none", "none", "none" }));
  private ArrayList<String> l = new ArrayList(Arrays.asList(new String[] { "none", "none", "none", "none", "none", "none", "none", "none" }));
  private ArrayList<ckr> m;
  private ckp n;
  private boolean o = false;
  private LinearLayout p;
  private ckx.c q;
  private ckx.b r;
  
  public ckx(Context paramContext)
  {
    super(paramContext);
    this.b = paramContext;
    ((LayoutInflater)paramContext.getSystemService("layout_inflater")).inflate(2131427392, this, true);
    this.d = AnimationUtils.loadAnimation(paramContext, 2130771994);
    this.e = AnimationUtils.loadAnimation(paramContext, 2130771990);
    this.e.setAnimationListener(new Animation.AnimationListener()
    {
      public final void onAnimationEnd(Animation paramAnonymousAnimation) {}
      
      public final void onAnimationRepeat(Animation paramAnonymousAnimation) {}
      
      public final void onAnimationStart(Animation paramAnonymousAnimation)
      {
        ckx.a(ckx.this);
        if (ckx.b(ckx.this) != null) {
          ckx.b(ckx.this).a(2131296372);
        }
      }
    });
    this.c = paramContext.getPackageManager();
    this.n = cko.a(paramContext);
    this.k = a("favorite_action_choose");
    new StringBuilder("mListChooseAction: ").append(this.k);
    this.f = ((RelativeLayoutAnim)findViewById(2131296363));
    this.g = ((RelativeLayoutAnim)findViewById(2131296409));
    this.h = ((RecyclerView)findViewById(2131296419));
    this.h.a(new cja());
    paramContext = new GridLayoutManager(this.b, 3);
    this.h.setLayoutManager(paramContext);
    this.j = new ciy(this.b);
    this.j.b = this;
    ((ImageView)findViewById(2131296560)).setOnClickListener(this);
    ((ImageView)findViewById(2131296561)).setOnClickListener(this);
    this.i = ((GoogleProgressBar)findViewById(2131296421));
    paramContext = (LinearLayout)findViewById(2131296364);
    LinearLayout localLinearLayout1 = (LinearLayout)findViewById(2131296365);
    LinearLayout localLinearLayout2 = (LinearLayout)findViewById(2131296366);
    LinearLayout localLinearLayout3 = (LinearLayout)findViewById(2131296367);
    LinearLayout localLinearLayout4 = (LinearLayout)findViewById(2131296368);
    LinearLayout localLinearLayout5 = (LinearLayout)findViewById(2131296369);
    LinearLayout localLinearLayout6 = (LinearLayout)findViewById(2131296370);
    LinearLayout localLinearLayout7 = (LinearLayout)findViewById(2131296371);
    paramContext.setOnClickListener(this);
    localLinearLayout1.setOnClickListener(this);
    localLinearLayout2.setOnClickListener(this);
    localLinearLayout3.setOnClickListener(this);
    localLinearLayout4.setOnClickListener(this);
    localLinearLayout5.setOnClickListener(this);
    localLinearLayout6.setOnClickListener(this);
    localLinearLayout7.setOnClickListener(this);
    paramContext.setOnLongClickListener(this);
    localLinearLayout1.setOnLongClickListener(this);
    localLinearLayout2.setOnLongClickListener(this);
    localLinearLayout3.setOnLongClickListener(this);
    localLinearLayout4.setOnLongClickListener(this);
    localLinearLayout5.setOnLongClickListener(this);
    localLinearLayout6.setOnLongClickListener(this);
    localLinearLayout7.setOnLongClickListener(this);
    if ((this.k != null) && (this.k.size() == 8))
    {
      if ((this.k.get(0) != null) && (!((String)this.k.get(0)).equals("none"))) {
        a(paramContext, (String)this.k.get(0));
      }
      if ((this.k.get(1) != null) && (!((String)this.k.get(1)).equals("none"))) {
        a(localLinearLayout1, (String)this.k.get(1));
      }
      if ((this.k.get(2) != null) && (!((String)this.k.get(2)).equals("none"))) {
        a(localLinearLayout2, (String)this.k.get(2));
      }
      if ((this.k.get(3) != null) && (!((String)this.k.get(3)).equals("none"))) {
        a(localLinearLayout3, (String)this.k.get(3));
      }
      if ((this.k.get(4) != null) && (!((String)this.k.get(4)).equals("none"))) {
        a(localLinearLayout4, (String)this.k.get(4));
      }
      if ((this.k.get(5) != null) && (!((String)this.k.get(5)).equals("none"))) {
        a(localLinearLayout5, (String)this.k.get(5));
      }
      if ((this.k.get(6) != null) && (!((String)this.k.get(6)).equals("none"))) {
        a(localLinearLayout6, (String)this.k.get(6));
      }
      if ((this.k.get(7) != null) && (!((String)this.k.get(7)).equals("none"))) {
        a(localLinearLayout7, (String)this.k.get(7));
      }
    }
  }
  
  private ArrayList<String> a(String paramString)
  {
    try
    {
      paramString = TextUtils.split(this.n.getString(paramString, TextUtils.join("‚‗‚", this.l)), "‚‗‚");
      Arrays.toString(paramString);
      paramString = new ArrayList(Arrays.asList(paramString));
      return paramString;
    }
    catch (Throwable paramString) {}
    return this.l;
  }
  
  private void a()
  {
    this.f.setVisibility(0);
    this.g.setVisibility(8);
  }
  
  private void a(LinearLayout paramLinearLayout, String paramString)
  {
    paramLinearLayout = (ImageView)paramLinearLayout.getChildAt(0);
    if (paramLinearLayout != null) {
      paramLinearLayout.setImageDrawable(b(paramString));
    }
  }
  
  private Drawable b(String paramString)
  {
    try
    {
      paramString = this.c.getApplicationIcon(paramString);
      return paramString;
    }
    catch (Throwable paramString)
    {
      paramString.getMessage();
    }
    return null;
  }
  
  private void b()
  {
    this.f.setVisibility(8);
    this.g.setVisibility(0);
    if (!this.o) {
      new ckx.a().execute(new Void[0]);
    }
  }
  
  public final void a(int paramInt)
  {
    try
    {
      ckr localCkr;
      if ((this.p != null) && (this.m != null))
      {
        localCkr = (ckr)this.m.get(paramInt);
        ImageView localImageView = (ImageView)this.p.getChildAt(0);
        if (localImageView != null) {
          localImageView.setImageDrawable(localCkr.a);
        }
        if (this.p.getTag() == null) {
          break label92;
        }
      }
      label92:
      for (paramInt = Integer.parseInt(this.p.getTag().toString());; paramInt = 0)
      {
        this.k.set(paramInt, localCkr.c);
        a();
        return;
      }
      return;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.getMessage();
    }
  }
  
  public final void onClick(View paramView)
  {
    if (paramView != null)
    {
      if (paramView.getId() != 2131296560) {
        break label22;
      }
      startAnimation(this.e);
    }
    for (;;)
    {
      return;
      label22:
      if (paramView.getId() == 2131296561)
      {
        a();
        return;
      }
      if ((paramView instanceof LinearLayout))
      {
        this.p = ((LinearLayout)paramView);
        if (this.p.getTag() != null) {}
        for (int i1 = Integer.parseInt(this.p.getTag().toString());; i1 = 0)
        {
          paramView = this.k;
          if ((this.k == null) || (this.k.size() != 8) || (i1 >= 8)) {
            break;
          }
          paramView = (String)this.k.get(i1);
          if (!paramView.equals("none")) {
            break label136;
          }
          b();
          return;
        }
        try
        {
          label136:
          paramView = this.c.getLaunchIntentForPackage(paramView);
          paramView.setFlags(268435456);
          this.b.startActivity(paramView);
          if (this.r != null)
          {
            this.r.a();
            return;
          }
        }
        catch (Throwable paramView)
        {
          Toast.makeText(this.b, 2131624005, 0).show();
        }
      }
    }
  }
  
  protected final void onDetachedFromWindow()
  {
    for (;;)
    {
      try
      {
        localObject = this.k;
      }
      catch (Throwable localThrowable2)
      {
        Object localObject;
        localThrowable2.getMessage();
        continue;
      }
      try
      {
        localObject = (String[])((ArrayList)localObject).toArray(new String[((ArrayList)localObject).size()]);
        this.n.edit().putString("favorite_action_choose", TextUtils.join("‚‗‚", (Object[])localObject)).apply();
        a();
        if (this.m != null)
        {
          this.m.clear();
          this.m = null;
        }
        if (this.j != null)
        {
          localObject = this.j;
          if (((ciy)localObject).a != null)
          {
            int i1 = ((ciy)localObject).a.size();
            ((ciy)localObject).a.clear();
            ((ciy)localObject).a(0, i1);
            ((RecyclerView.a)localObject).d.b();
          }
        }
        this.o = false;
        if (this.h != null)
        {
          this.h.removeAllViews();
          this.h.removeAllViewsInLayout();
          this.h.setAdapter(null);
        }
        super.onDetachedFromWindow();
        return;
      }
      catch (Throwable localThrowable1)
      {
        localThrowable1.getMessage();
      }
    }
  }
  
  public final boolean onLongClick(View paramView)
  {
    if ((paramView != null) && ((paramView instanceof LinearLayout)))
    {
      this.p = ((LinearLayout)paramView);
      b();
      return true;
    }
    return false;
  }
  
  protected final void onWindowVisibilityChanged(int paramInt)
  {
    super.onWindowVisibilityChanged(paramInt);
    if (paramInt == 0)
    {
      setEnabled(true);
      if (this.d != null) {
        startAnimation(this.d);
      }
    }
    if ((paramInt == 0) && (this.o))
    {
      if (this.h != null) {
        this.h.setVisibility(0);
      }
      if (this.i != null) {
        this.i.setVisibility(8);
      }
    }
  }
  
  public final void setApplicationStartedListener(ckx.b paramB)
  {
    this.r = paramB;
  }
  
  public final void setOnPreviousPressListener(ckx.c paramC)
  {
    this.q = paramC;
  }
  
  final class a
    extends AsyncTask<Void, Void, ArrayList<ckr>>
  {
    a() {}
    
    private ArrayList<ckr> a()
    {
      ckx.a(ckx.this);
      ArrayList localArrayList = new ArrayList();
      try
      {
        Iterator localIterator = ckx.d(ckx.this).getInstalledApplications(128).iterator();
        while (localIterator.hasNext())
        {
          ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
          if ((ckx.d(ckx.this).getLaunchIntentForPackage(localApplicationInfo.packageName) != null) && (!ckx.d(ckx.this).getLaunchIntentForPackage(localApplicationInfo.packageName).toString().isEmpty()))
          {
            ckr localCkr = new ckr();
            localCkr.b = localApplicationInfo.loadLabel(ckx.d(ckx.this)).toString();
            localCkr.c = localApplicationInfo.packageName;
            localCkr.a = localApplicationInfo.loadIcon(ckx.d(ckx.this));
            localArrayList.add(localCkr);
          }
        }
        Collections.sort(localArrayList, new Comparator() {});
      }
      catch (Throwable localThrowable)
      {
        ckx.a(ckx.this);
        localThrowable.getMessage();
        return localArrayList;
      }
      localArrayList.add(0, new ckr(ckx.e(ckx.this).getResources().getString(2131624064), "none", ckx.e(ckx.this).getResources().getDrawable(2131230809)));
      return localArrayList;
    }
    
    protected final void onPreExecute()
    {
      ckx.a(ckx.this);
      if (ckx.c(ckx.this) != null) {
        ckx.c(ckx.this).setVisibility(0);
      }
      super.onPreExecute();
    }
  }
  
  static abstract interface b
  {
    public abstract void a();
  }
  
  static abstract interface c
  {
    public abstract void a(int paramInt);
  }
}
