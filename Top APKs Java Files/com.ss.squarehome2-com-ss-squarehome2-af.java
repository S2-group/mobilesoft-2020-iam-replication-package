package com.ss.squarehome2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ScrollView;
import android.widget.Toast;
import com.ss.b.d;
import com.ss.f.q;
import com.ss.f.q.a;
import com.ss.squarehome2.a.a;
import com.ss.squarehome2.preference.PersistentPaddingPreference;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class af
  extends ViewGroup
  implements SharedPreferences.OnSharedPreferenceChangeListener, al, bq
{
  private String a;
  protected ArrayList<be> b = new ArrayList();
  protected final be c;
  private be d;
  private MainActivity e;
  private int f;
  private Runnable g = new Runnable()
  {
    public void run()
    {
      int j = af.this.b.size();
      int i = 0;
      while (i < j)
      {
        ((be)af.this.b.get(i)).w();
        i += 1;
      }
    }
  };
  private boolean h;
  private Runnable i;
  private Drawable j;
  private int[] k;
  private Runnable l;
  private int m;
  private boolean n;
  private Comparator<be> o;
  private LinkedList<a> p;
  private int q;
  private Runnable r;
  private Rect s;
  
  public af(Context paramContext)
  {
    super(paramContext);
    int i1 = 0;
    this.h = false;
    this.i = new Runnable()
    {
      public void run()
      {
        int j = af.this.b.size();
        int i = 0;
        while (i < j)
        {
          ((be)af.this.b.get(i)).y();
          i += 1;
        }
      }
    };
    this.l = new Runnable()
    {
      public void run()
      {
        long l1 = System.currentTimeMillis();
        af.this.removeCallbacks(af.c(af.this));
        int i = 0;
        boolean bool = false;
        while (i < af.this.b.size())
        {
          be localBe = (be)af.this.b.get(i);
          bool |= localBe.getLayoutAnimator().a(localBe);
          i += 1;
        }
        if ((af.this.c.getLayoutAnimator().a(af.this.c) | bool))
        {
          long l2 = System.currentTimeMillis();
          af.this.postDelayed(af.c(af.this), Math.max(0L, 14L - (l2 - l1)));
        }
      }
    };
    this.n = false;
    this.o = new Comparator()
    {
      public int a(be paramAnonymousBe1, be paramAnonymousBe2)
      {
        int i = (int)paramAnonymousBe1.getLayoutAnimator().b();
        int j = (int)paramAnonymousBe2.getLayoutAnimator().b();
        if (i == j) {
          return paramAnonymousBe1.E() - paramAnonymousBe2.E();
        }
        return i - j;
      }
    };
    this.p = new LinkedList();
    this.q = -1;
    this.r = new Runnable()
    {
      public void run()
      {
        if (af.d(af.this) >= 0)
        {
          View localView = af.this.getChildAt(af.d(af.this));
          if ((localView instanceof bx))
          {
            ((bx)localView).j();
            af.this.performHapticFeedback(0);
          }
        }
      }
    };
    this.s = new Rect();
    this.e = ((MainActivity)paramContext);
    this.f = be.a(paramContext);
    this.c = new bj(paramContext, 2131165282, new Runnable()
    {
      public void run()
      {
        af.this.y();
      }
    })
    {
      protected void a(boolean paramAnonymousBoolean)
      {
        af.a(af.this).i();
      }
    };
    be localBe = this.c;
    if (this.e.G()) {
      i1 = 8;
    }
    localBe.setVisibility(i1);
    this.c.setLongClickable(true);
    this.c.setContentDescription(paramContext.getString(2131558403));
  }
  
  public af(Context paramContext, String paramString)
  {
    this(paramContext);
    this.a = paramString;
  }
  
  private boolean J()
  {
    if (TextUtils.isEmpty(this.a))
    {
      this.a = v.a();
      this.e.c();
    }
    boolean bool = cd.a(r(), new File(f.a(getContext(), "layout"), this.a));
    j();
    return bool;
  }
  
  private void K()
  {
    Object localObject = new ArrayList();
    this.e.r().b((List)localObject);
    if (((ArrayList)localObject).size() == 1)
    {
      a((be)((ArrayList)localObject).get(0));
      return;
    }
    if (((ArrayList)localObject).size() > 1)
    {
      localObject = ((ArrayList)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        be localBe = (be)((Iterator)localObject).next();
        this.b.add(localBe);
        addView(localBe);
        localBe.getLayoutAnimator().b(localBe);
        localBe.startAnimation(AnimationUtils.loadAnimation(getContext(), 2130771999));
      }
      u();
      A();
      w();
      z();
    }
  }
  
  private void L()
  {
    if ((getParent() instanceof ah))
    {
      ah localAh = getPageView();
      if (ak.b(getContext(), "hideScrollBar", false))
      {
        localAh.setVerticalScrollBarEnabled(false);
        return;
      }
      localAh.setVerticalScrollBarEnabled(H() ^ true);
    }
  }
  
  protected static JSONArray a(Context paramContext, String paramString)
  {
    paramContext = new File(f.a(paramContext, "layout"), paramString);
    if (paramContext.exists()) {
      return cd.b(paramContext);
    }
    return null;
  }
  
  private int c(int paramInt)
  {
    Object localObject = new Point();
    cd.a(this.e, (Point)localObject);
    int i1 = ((Point)localObject).y;
    int i2 = cd.e(this.e);
    int i3 = cd.g(this.e);
    localObject = PersistentPaddingPreference.a(getContext(), "tabletModePadding");
    return (i1 - (i2 + i3) - (((Rect)localObject).top + ((Rect)localObject).bottom)) / paramInt;
  }
  
  private void h(be paramBe)
  {
    ah localAh = (ah)getParent();
    int i1 = (int)paramBe.getLayoutAnimator().b();
    if (localAh.getScrollY() > i1)
    {
      localAh.smoothScrollTo(0, i1);
      return;
    }
    i1 = (int)paramBe.getLayoutAnimator().d();
    if (localAh.getScrollY() + localAh.getHeight() < i1) {
      localAh.smoothScrollTo(0, i1 - localAh.getHeight());
    }
  }
  
  private be i(be paramBe)
  {
    Object localObject4 = null;
    int i1 = 0;
    for (localObject1 = null; i1 < getChildCount(); localObject1 = localObject2)
    {
      try
      {
        be localBe = (be)getChildAt(i1);
        localObject2 = localObject1;
        if (localBe != paramBe) {
          if (!localBe.isFocusable())
          {
            localObject2 = localObject1;
            if (!(localBe instanceof x)) {}
          }
          else
          {
            localObject2 = localObject1;
            if (localBe.getLayoutAnimator().c() > paramBe.getLayoutAnimator().a()) {
              if (localBe.getLayoutAnimator().a() >= paramBe.getLayoutAnimator().c())
              {
                localObject2 = localObject1;
              }
              else
              {
                localObject2 = localObject1;
                if (localBe.getLayoutAnimator().d() <= paramBe.getLayoutAnimator().b()) {
                  if (localObject1 != null)
                  {
                    float f1 = localBe.getLayoutAnimator().d();
                    float f2 = localObject1.getLayoutAnimator().d();
                    localObject2 = localObject1;
                    if (f1 <= f2) {}
                  }
                  else
                  {
                    localObject2 = localBe;
                  }
                }
              }
            }
          }
        }
      }
      catch (Exception localException)
      {
        for (;;)
        {
          Object localObject2;
          Object localObject3 = localObject1;
        }
      }
      i1 += 1;
    }
    paramBe = localObject4;
    if (localObject1 != null)
    {
      paramBe = localObject4;
      if (localObject1.isFocusable()) {
        paramBe = localObject1;
      }
    }
    return paramBe;
  }
  
  private be j(be paramBe)
  {
    Object localObject4 = null;
    int i1 = 0;
    for (localObject1 = null; i1 < getChildCount(); localObject1 = localObject2)
    {
      try
      {
        be localBe = (be)getChildAt(i1);
        localObject2 = localObject1;
        if (localBe != paramBe) {
          if (!localBe.isFocusable())
          {
            localObject2 = localObject1;
            if (!(localBe instanceof x)) {}
          }
          else
          {
            localObject2 = localObject1;
            if (localBe.getLayoutAnimator().c() > paramBe.getLayoutAnimator().a()) {
              if (localBe.getLayoutAnimator().a() >= paramBe.getLayoutAnimator().c())
              {
                localObject2 = localObject1;
              }
              else
              {
                localObject2 = localObject1;
                if (localBe.getLayoutAnimator().b() >= paramBe.getLayoutAnimator().d()) {
                  if (localObject1 != null)
                  {
                    float f1 = localBe.getLayoutAnimator().b();
                    float f2 = localObject1.getLayoutAnimator().b();
                    localObject2 = localObject1;
                    if (f1 >= f2) {}
                  }
                  else
                  {
                    localObject2 = localBe;
                  }
                }
              }
            }
          }
        }
      }
      catch (Exception localException)
      {
        for (;;)
        {
          Object localObject2;
          Object localObject3 = localObject1;
        }
      }
      i1 += 1;
    }
    paramBe = localObject4;
    if (localObject1 != null)
    {
      paramBe = localObject4;
      if (localObject1.isFocusable()) {
        paramBe = localObject1;
      }
    }
    return paramBe;
  }
  
  void A()
  {
    Collections.sort(this.b, this.o);
  }
  
  void B()
  {
    this.p.clear();
    int i1 = 0;
    while (i1 < this.b.size())
    {
      this.p.add(new a((be)this.b.get(i1)));
      i1 += 1;
    }
  }
  
  void C()
  {
    this.p.clear();
  }
  
  void D()
  {
    removeAllViews();
    this.b.clear();
    Iterator localIterator = this.p.iterator();
    while (localIterator.hasNext())
    {
      a localA = (a)localIterator.next();
      this.b.add(localA.a());
    }
    this.p.clear();
    s();
    u();
    w();
  }
  
  protected void E()
  {
    this.q = -1;
    removeCallbacks(this.r);
  }
  
  protected void F()
  {
    this.q = -1;
    removeCallbacks(this.r);
  }
  
  protected void G()
  {
    this.q = -1;
    removeCallbacks(this.r);
  }
  
  public boolean H()
  {
    return (this.e.K()) && (this.e.G());
  }
  
  protected boolean I()
  {
    int i1 = 0;
    boolean bool2;
    for (boolean bool1 = false; i1 < this.b.size(); bool1 = bool2)
    {
      be localBe = (be)this.b.get(i1);
      bool2 = bool1;
      if ((localBe instanceof x)) {
        bool2 = bool1 | ((x)localBe).b();
      }
      i1 += 1;
    }
    return bool1;
  }
  
  protected int a()
  {
    return Math.max(((View)getParent()).getMeasuredHeight(), getSuggestedMinimumHeight());
  }
  
  public void a(int paramInt)
  {
    if (!this.e.G())
    {
      u();
      x();
    }
  }
  
  public void a(int paramInt1, int paramInt2)
  {
    LinkedList localLinkedList = new LinkedList();
    Iterator localIterator = this.b.iterator();
    while (localIterator.hasNext())
    {
      be localBe = (be)localIterator.next();
      if ((localBe instanceof x)) {
        localLinkedList.addAll(((x)localBe).getLayout().b);
      } else {
        localLinkedList.add(localBe);
      }
    }
    com.ss.c.b.a(localLinkedList, paramInt1, paramInt2, this.f, 75L);
  }
  
  public void a(long paramLong)
  {
    Context localContext = getContext();
    Iterator localIterator = this.b.iterator();
    while (localIterator.hasNext())
    {
      be localBe = (be)localIterator.next();
      if ((localBe instanceof x))
      {
        ((x)localBe).getLayout().a(paramLong);
      }
      else if (cd.b(localBe))
      {
        ScaleAnimation localScaleAnimation = new ScaleAnimation(0.0F, 1.0F, 0.0F, 1.0F, 1, 0.5F, 1, 0.5F);
        localScaleAnimation.setInterpolator(AnimationUtils.loadInterpolator(localContext, 17432584));
        localScaleAnimation.setDuration(250L);
        localScaleAnimation.setStartOffset(paramLong + (Math.random() * 250.0D));
        localScaleAnimation.setFillBefore(true);
        localBe.startAnimation(localScaleAnimation);
      }
    }
  }
  
  void a(Intent paramIntent)
  {
    a(new bw(getContext(), paramIntent));
  }
  
  protected void a(MotionEvent paramMotionEvent)
  {
    ce.a((int)paramMotionEvent.getRawX(), (int)paramMotionEvent.getRawY());
  }
  
  protected void a(View paramView, long paramLong) {}
  
  void a(com.ss.launcher.utils.h paramH)
  {
    a(new bw(getContext(), paramH));
  }
  
  public void a(be paramBe)
  {
    if (!i()) {}
    for (int i1 = this.c.E();; i1 = Math.max(0, Math.min(this.c.E(), getNumColumns() - paramBe.h())))
    {
      a(paramBe, i1, this.c.getTop());
      return;
    }
  }
  
  void a(be paramBe, int paramInt)
  {
    this.b.remove(paramBe);
    paramBe.clearAnimation();
    removeView(paramBe);
    int i1 = 0;
    while ((i1 < this.b.size()) && ((int)((be)this.b.get(i1)).getLayoutAnimator().b() < paramInt - this.m)) {
      i1 += 1;
    }
    this.b.add(i1, paramBe);
    addView(paramBe);
    u();
    paramBe.getLayoutAnimator().b(paramBe);
    w();
  }
  
  protected void a(be paramBe, int paramInt1, int paramInt2)
  {
    paramBe.setXPosition(paramInt1);
    paramInt1 = 0;
    while ((paramInt1 < this.b.size()) && (((be)this.b.get(paramInt1)).getTop() < paramInt2)) {
      paramInt1 += 1;
    }
    try
    {
      addView(paramBe);
      this.b.add(paramInt1, paramBe);
      u();
      A();
      w();
      paramBe.getLayoutAnimator().b(paramBe);
      paramBe.A();
      z();
      return;
    }
    catch (SecurityException paramBe)
    {
      for (;;) {}
    }
    Toast.makeText(getContext(), 2131558584, 1).show();
  }
  
  public void a(be paramBe, int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.copyTypes(TypeTransformer.java:311)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.fixTypes(TypeTransformer.java:226)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:207)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
  }
  
  void a(be paramBe1, be paramBe2)
  {
    int i1 = this.b.indexOf(paramBe1);
    this.b.remove(paramBe1);
    paramBe1.clearAnimation();
    removeView(paramBe1);
    paramBe2.setXPosition(paramBe1.E());
    this.b.add(i1, paramBe2);
    addView(paramBe2);
    u();
    A();
    w();
  }
  
  protected void a(final bx paramBx, x paramX)
  {
    paramX.p();
    paramX.measure(0, 0);
    paramX.getLayout().u();
    paramX.getLayout().x();
    int i2;
    if (paramBx.getContainer() == this)
    {
      i1 = (int)paramBx.getLayoutAnimator().d();
      i2 = paramBx.getTop();
    }
    else
    {
      paramBx = (be)paramBx.getContainer();
      i1 = (int)paramBx.getLayoutAnimator().d();
      i2 = paramBx.getTop();
    }
    a(paramX, i1);
    paramBx = (ah)getParent();
    final int i1 = Math.min(i2, (int)paramX.getLayoutAnimator().d() - paramBx.getHeight() + h());
    if (i1 > paramBx.getScrollY()) {
      paramBx.post(new Runnable()
      {
        public void run()
        {
          paramBx.smoothScrollTo(0, i1);
        }
      });
    }
  }
  
  protected void a(x paramX)
  {
    paramX.q();
    if (getActivity().d())
    {
      postDelayed(new Runnable()
      {
        public void run()
        {
          af.this.u();
          af.this.w();
        }
      }, f.a(getContext(), 150L));
      return;
    }
    g(paramX);
    u();
    x();
  }
  
  public void a(String paramString)
  {
    this.a = paramString;
    if (ak.a(getContext(), "tabletMode", false))
    {
      if (TextUtils.isEmpty(this.a))
      {
        paramString = null;
      }
      else
      {
        JSONArray localJSONArray = a(getContext(), this.a);
        paramString = localJSONArray;
        if (localJSONArray == null) {
          paramString = q();
        }
      }
      a(paramString, true);
      return;
    }
    paramString = new q.a()
    {
      private JSONArray b;
      
      public void a()
      {
        if (TextUtils.isEmpty(af.b(af.this))) {}
        for (JSONArray localJSONArray = null;; localJSONArray = af.this.q())
        {
          this.b = localJSONArray;
          return;
          this.b = af.a(af.this.getContext(), af.b(af.this));
          if (this.b != null) {
            break;
          }
        }
      }
      
      public void run()
      {
        af.this.a(this.b, true);
      }
    };
    Application.d().a(paramString);
  }
  
  protected void a(JSONArray paramJSONArray, boolean paramBoolean)
  {
    this.b.clear();
    if (paramJSONArray != null)
    {
      int i2 = paramJSONArray.length();
      int i1 = 0;
      while (i1 < i2)
      {
        for (;;)
        {
          try
          {
            localJSONObject = paramJSONArray.getJSONObject(i1);
            if ((paramBoolean) && (be.c(localJSONObject)))
            {
              localBw = this.e.n().a();
              localObject = localBw;
              if (localBw == null) {
                continue;
              }
            }
          }
          catch (JSONException localJSONException)
          {
            JSONObject localJSONObject;
            bw localBw;
            Object localObject;
            label96:
            localJSONException.printStackTrace();
          }
          try
          {
            localBw.d(localJSONObject);
            localObject = localBw;
          }
          catch (Exception localException) {}
        }
        localObject = null;
        break label96;
        localObject = be.a(getContext(), localJSONObject);
        if (localObject != null) {
          this.b.add(localObject);
        }
        i1 += 1;
      }
    }
    s();
    u();
    x();
  }
  
  public void a(boolean paramBoolean, int paramInt)
  {
    int i1 = 0;
    int i3;
    for (int i2 = 0; i1 < this.b.size(); i2 = i3)
    {
      be localBe = (be)this.b.get(i1);
      if (localBe.M())
      {
        localBe.setEffectOnly(paramBoolean);
        localBe.setStyle(paramInt);
        i3 = 1;
      }
      else
      {
        i3 = i2;
        if ((localBe instanceof bq))
        {
          ((bq)localBe).a(paramBoolean, paramInt);
          i3 = i2;
        }
      }
      i1 += 1;
    }
    if (i2 != 0) {
      z();
    }
  }
  
  public void a(boolean paramBoolean, List<be> paramList)
  {
    int i1 = this.b.size() - 1;
    int i3;
    for (int i2 = 0; i1 >= 0; i2 = i3)
    {
      be localBe = (be)this.b.get(i1);
      if (localBe.M())
      {
        this.b.remove(i1);
        localBe.clearAnimation();
        removeView(localBe);
        localBe.setChecked(false);
        if (paramList != null) {
          paramList.add(localBe);
        }
        if (paramBoolean) {
          localBe.B();
        }
        i3 = 1;
      }
      else
      {
        i3 = i2;
        if ((localBe instanceof bq))
        {
          ((bq)localBe).a(paramBoolean, paramList);
          i3 = i2;
        }
      }
      i1 -= 1;
    }
    if (i2 != 0)
    {
      u();
      A();
      w();
      z();
    }
  }
  
  public boolean a(d paramD)
  {
    return paramD.a() instanceof be;
  }
  
  @SuppressLint({"RtlHardcoded"})
  boolean a(MainActivity paramMainActivity)
  {
    if ((!paramMainActivity.G()) && (this.b.size() > 1))
    {
      be localBe = (be)this.b.get(0);
      if (!cd.b(localBe)) {
        return false;
      }
      return paramMainActivity.a(4, localBe, 2131558954, false, null, null, 3);
    }
    return false;
  }
  
  public void addView(View paramView)
  {
    ViewGroup.MarginLayoutParams localMarginLayoutParams2 = (ViewGroup.MarginLayoutParams)paramView.getLayoutParams();
    ViewGroup.MarginLayoutParams localMarginLayoutParams1 = localMarginLayoutParams2;
    if (localMarginLayoutParams2 == null)
    {
      localMarginLayoutParams1 = new ViewGroup.MarginLayoutParams(-2, -2);
      localMarginLayoutParams1.topMargin = -1;
      localMarginLayoutParams1.leftMargin = -1;
    }
    super.addView(paramView, localMarginLayoutParams1);
  }
  
  protected int b()
  {
    boolean bool = i();
    int i2 = 0;
    int i1 = i2;
    if (bool)
    {
      i1 = i2;
      if (cd.b(this.e))
      {
        i1 = i2;
        if (!ak.a(this.e, "hideStatus", false)) {
          i1 = cd.e(getActivity());
        }
      }
    }
    return i1;
  }
  
  protected void b(int paramInt)
  {
    Object localObject;
    switch (paramInt)
    {
    default: 
      return;
    case 2131165412: 
      br.d(this.e, this);
      return;
    case 2131165406: 
      br.e(this.e, this);
      return;
    case 2131165398: 
      br.b(this.e, this);
      return;
    case 2131165369: 
      K();
      return;
    case 2131165353: 
      br.c(this.e, this);
      return;
    case 2131165324: 
      localObject = new bt(getContext());
      break;
    case 2131165319: 
      localObject = bs.d(getContext());
      break;
    case 2131165318: 
      localObject = new bw(getContext(), 2);
      break;
    case 2131165286: 
      localObject = new bw(getContext(), 1);
      a((be)localObject);
      return;
    }
    br.a(this.e, this);
  }
  
  protected void b(be paramBe, int paramInt1, int paramInt2)
  {
    int i1 = 0;
    View localView;
    while (i1 < getChildCount())
    {
      localView = getChildAt(i1);
      if ((localView instanceof be))
      {
        ((be)localView).getLayoutAnimator().a(this.s);
        if (this.s.contains(paramInt1, paramInt2)) {
          break;
        }
      }
      i1 += 1;
    }
    paramInt1 = i1;
    if (getChildCount() == i1) {
      paramInt1 = -1;
    }
    if (paramInt1 != this.q)
    {
      removeCallbacks(this.r);
      this.q = paramInt1;
      if (paramInt1 >= 0)
      {
        localView = getChildAt(paramInt1);
        if (((localView instanceof bx)) && (localView != paramBe)) {
          postDelayed(this.r, 800L);
        }
      }
    }
  }
  
  public boolean b(be paramBe)
  {
    if (g(paramBe))
    {
      z();
      return true;
    }
    return false;
  }
  
  public void c()
  {
    Iterator localIterator = this.b.iterator();
    while (localIterator.hasNext()) {
      ((be)localIterator.next()).B();
    }
    new File(f.a(getContext(), "layout"), this.a).delete();
  }
  
  public void c(be paramBe)
  {
    paramBe.setChecked(paramBe.M() ^ true);
    this.e.N();
  }
  
  public void c(boolean paramBoolean)
  {
    this.h = paramBoolean;
  }
  
  public boolean d()
  {
    boolean bool2 = I();
    Object localObject = (ScrollView)getParent();
    boolean bool1 = bool2;
    if (localObject != null)
    {
      bool1 = bool2;
      if (((ScrollView)localObject).getScrollY() > 0)
      {
        ((ScrollView)localObject).smoothScrollTo(0, 0);
        bool1 = true;
      }
    }
    localObject = this.b.iterator();
    while (((Iterator)localObject).hasNext()) {
      ((be)((Iterator)localObject).next()).v();
    }
    return bool1;
  }
  
  public boolean d(be paramBe)
  {
    return this.d == paramBe;
  }
  
  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
  {
    if (this.d != null)
    {
      int i2 = paramKeyEvent.getKeyCode();
      int i1 = 4;
      if ((i2 != 4) && (i2 != 111)) {
        switch (i2)
        {
        default: 
          break;
        case 22: 
          if ((paramKeyEvent.getAction() != 1) || ((this.d instanceof bt))) {
            break label910;
          }
          if (i()) {
            i2 = getNumColumns() - 1;
          } else {
            i2 = getNumColumns();
          }
          if ((this.d.E() >= i2) && (this.d.G())) {
            break label910;
          }
          if (!this.d.G())
          {
            paramKeyEvent = this.d;
            if (this.d.H()) {
              i1 = 1;
            } else if (!this.d.I()) {
              i1 = 0;
            }
            paramKeyEvent.setHalfPosition(0x2 | i1);
          }
          else
          {
            this.d.setXPosition(this.d.E() + 1);
            paramKeyEvent = this.d;
            if (this.d.H()) {
              i1 = 1;
            } else if (!this.d.I()) {
              i1 = 0;
            }
            paramKeyEvent.setHalfPosition(i1);
          }
          break;
        case 21: 
          if ((paramKeyEvent.getAction() != 1) || ((this.d instanceof bt)) || ((this.d.E() <= 0) && (!this.d.G()))) {
            break label910;
          }
          if (this.d.G())
          {
            paramKeyEvent = this.d;
            if (this.d.H()) {
              i1 = 1;
            } else if (!this.d.I()) {
              i1 = 0;
            }
            paramKeyEvent.setHalfPosition(i1);
          }
          else
          {
            this.d.setXPosition(this.d.E() - 1);
            paramKeyEvent = this.d;
            if (this.d.H()) {
              i1 = 1;
            } else if (!this.d.I()) {
              i1 = 0;
            }
            paramKeyEvent.setHalfPosition(0x2 | i1);
          }
          i1 = this.b.indexOf(this.d);
          if (i1 > 0)
          {
            paramKeyEvent = this.b;
            i1 -= 1;
            paramKeyEvent = (be)paramKeyEvent.get(i1);
            if ((paramKeyEvent.getLayoutAnimator().c() == this.d.getLayoutAnimator().a()) && (paramKeyEvent.getLayoutAnimator().b() >= this.d.getLayoutAnimator().b()))
            {
              this.b.remove(this.d);
              this.b.add(i1, this.d);
            }
          }
          u();
          A();
          w();
          this.n = true;
          break;
        case 20: 
          if (paramKeyEvent.getAction() != 1) {
            break label910;
          }
          float f1 = Float.MAX_VALUE;
          i1 = this.b.indexOf(this.d) + 1;
          while (i1 < this.b.size())
          {
            paramKeyEvent = (be)this.b.get(i1);
            float f2 = f1;
            if (paramKeyEvent.getLayoutAnimator().c() > this.d.getLayoutAnimator().a()) {
              if (paramKeyEvent.getLayoutAnimator().a() >= this.d.getLayoutAnimator().c())
              {
                f2 = f1;
              }
              else
              {
                if (paramKeyEvent.getLayoutAnimator().b() > f1) {
                  break;
                }
                f2 = paramKeyEvent.getLayoutAnimator().b();
              }
            }
            i1 += 1;
            f1 = f2;
          }
          if (i1 > this.b.size()) {
            break label910;
          }
          this.b.remove(this.d);
          paramKeyEvent = this.b;
          i2 = i1 - 1;
        case 19: 
          for (;;)
          {
            paramKeyEvent.add(i2, this.d);
            break;
            if ((paramKeyEvent.getAction() != 1) || (this.d.getLayoutAnimator().b() <= 0.0F)) {
              break label910;
            }
            i1 = this.b.indexOf(this.d) - 1;
            int i3;
            for (i2 = -1; i1 >= 0; i2 = i3)
            {
              paramKeyEvent = (be)this.b.get(i1);
              i3 = i2;
              if (paramKeyEvent.getLayoutAnimator().c() > this.d.getLayoutAnimator().a()) {
                if (paramKeyEvent.getLayoutAnimator().a() >= this.d.getLayoutAnimator().c())
                {
                  i3 = i2;
                }
                else
                {
                  i3 = i2;
                  if (paramKeyEvent.getLayoutAnimator().d() == this.d.getLayoutAnimator().b()) {
                    i3 = i1;
                  }
                }
              }
              i1 -= 1;
            }
            if (i2 < 0) {
              break label910;
            }
            this.b.remove(this.d);
            paramKeyEvent = this.b;
          }
        }
      }
      if ((paramKeyEvent.getAction() == 0) && (this.d != null)) {
        setMoving(null);
      }
      label910:
      if ((this.n) && (this.d != null)) {
        h(this.d);
      }
      return true;
    }
    this.d = null;
    return super.dispatchKeyEvent(paramKeyEvent);
  }
  
  protected boolean drawChild(Canvas paramCanvas, View paramView, long paramLong)
  {
    boolean bool = super.drawChild(paramCanvas, paramView, paramLong);
    if (this.d == paramView)
    {
      if (this.j == null) {
        this.j = getResources().getDrawable(2131165365);
      }
      int i1 = paramView.getLeft() + paramView.getWidth() / 2;
      int i2 = paramView.getTop() + paramView.getHeight() / 2;
      int i4 = Math.min(paramView.getWidth(), paramView.getHeight());
      int i3 = Math.min(this.j.getIntrinsicWidth(), i4) / 2;
      i4 = Math.min(this.j.getIntrinsicHeight(), i4) / 2;
      this.j.setBounds(i1 - i3, i2 - i4, i1 + i3, i2 + i4);
      this.j.draw(paramCanvas);
    }
    return bool;
  }
  
  public void e()
  {
    int i2 = getChildCount();
    int i1 = 0;
    while (i1 < i2)
    {
      be localBe = (be)getChildAt(i1);
      localBe.l_();
      localBe.invalidate();
      i1 += 1;
    }
  }
  
  public void e(be paramBe)
  {
    ((ah)getParent()).a(paramBe);
  }
  
  public boolean equals(Object paramObject)
  {
    return (this == paramObject) || (((paramObject instanceof af)) && (this.a.equals(((af)paramObject).a)));
  }
  
  public void f()
  {
    View localView = (View)getParent();
    int i2 = localView.getScrollY();
    int i3 = localView.getHeight();
    int i4 = getChildCount();
    int i1 = 0;
    while (i1 < i4)
    {
      localView = getChildAt(i1);
      if ((localView.getBottom() > i2) && (localView.getTop() < i3 + i2)) {
        localView.invalidate();
      }
      i1 += 1;
    }
  }
  
  public boolean f(be paramBe)
  {
    return this.b.contains(paramBe);
  }
  
  public boolean g()
  {
    if (this.b.size() == 0) {
      return true;
    }
    boolean bool = ((be)this.b.get(0)).D();
    int i1 = 1;
    while (i1 < this.b.size())
    {
      if (((be)this.b.get(i1)).D() != bool) {
        return true;
      }
      i1 += 1;
    }
    return bool;
  }
  
  boolean g(be paramBe)
  {
    if (this.b.remove(paramBe))
    {
      paramBe.clearAnimation();
      removeView(paramBe);
      u();
      A();
      w();
      return true;
    }
    return false;
  }
  
  protected MainActivity getActivity()
  {
    return this.e;
  }
  
  public int getDesiredPageWidthInTabletMode()
  {
    return getNumColumns() * this.f + this.f / 2;
  }
  
  protected com.ss.b.c getDnDClient()
  {
    return (com.ss.b.c)getParent();
  }
  
  protected int getNumColumns()
  {
    boolean bool = i();
    int i2 = 1;
    Object localObject;
    if (bool)
    {
      localObject = getActivity().s();
      return Math.max(1, (((View)localObject).getMeasuredWidth() - ((View)localObject).getPaddingLeft() - ((View)localObject).getPaddingRight()) / this.f);
    }
    int i5 = this.b.size();
    int i1 = 0;
    while (i1 < i5)
    {
      localObject = (be)this.b.get(i1);
      int i3;
      if ((localObject instanceof bt))
      {
        i3 = i2;
      }
      else
      {
        int i4 = ((be)localObject).E() + ((be)localObject).h();
        i3 = i2;
        if (i4 > i2) {
          i3 = i4;
        }
      }
      i1 += 1;
      i2 = i3;
    }
    return i2;
  }
  
  public String getPageId()
  {
    return this.a;
  }
  
  public ah getPageView()
  {
    ah localAh2 = (ah)getParent();
    ah localAh1 = localAh2;
    if (localAh2 == null) {
      localAh1 = new ah(getContext(), this);
    }
    return localAh1;
  }
  
  protected int getStartId()
  {
    return 100;
  }
  
  public int getTileStyle()
  {
    if (this.b.size() == 0) {
      return -1;
    }
    int i2 = ((be)this.b.get(0)).getStyle();
    int i1 = 1;
    while (i1 < this.b.size())
    {
      if (((be)this.b.get(i1)).getStyle() != i2) {
        return -1;
      }
      i1 += 1;
    }
    return i2;
  }
  
  protected int h()
  {
    boolean bool = i();
    int i2 = 0;
    int i1 = i2;
    if (bool)
    {
      i1 = i2;
      if (cd.b(getActivity()))
      {
        if (ak.a(this.e, "hideNavi", false)) {
          return 0;
        }
        i1 = cd.g(getActivity());
      }
    }
    return i1;
  }
  
  protected boolean i()
  {
    return ak.a(getContext(), "tabletMode", false) ^ true;
  }
  
  protected void j() {}
  
  public boolean k()
  {
    return this.h;
  }
  
  public boolean l()
  {
    return true;
  }
  
  public boolean m()
  {
    return getParent() instanceof ah;
  }
  
  public boolean n()
  {
    int i1 = 0;
    while (i1 < this.b.size())
    {
      be localBe = (be)this.b.get(i1);
      if (localBe.M()) {
        return true;
      }
      if (((localBe instanceof bq)) && (((bq)localBe).n())) {
        return true;
      }
      i1 += 1;
    }
    return false;
  }
  
  public void o()
  {
    int i1 = 0;
    while (i1 < this.b.size())
    {
      be localBe = (be)this.b.get(i1);
      if ((localBe instanceof bq)) {
        ((bq)localBe).o();
      } else {
        localBe.setChecked(false);
      }
      i1 += 1;
    }
  }
  
  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    if (getChildCount() == 0)
    {
      s();
      u();
      x();
    }
    L();
    this.e.a(getDnDClient());
    PreferenceManager.getDefaultSharedPreferences(getContext()).registerOnSharedPreferenceChangeListener(this);
    Application.a(this.g, true);
    Application.a(this.i);
  }
  
  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    this.e.b(getDnDClient());
    PreferenceManager.getDefaultSharedPreferences(getContext()).unregisterOnSharedPreferenceChangeListener(this);
    Application.c(this.g);
    Application.b(this.i);
    if ((getParent() instanceof ah)) {
      I();
    }
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.m = b();
    paramInt2 = getChildCount();
    paramInt1 = 0;
    while (paramInt1 < paramInt2)
    {
      View localView = getChildAt(paramInt1);
      ViewGroup.MarginLayoutParams localMarginLayoutParams = (ViewGroup.MarginLayoutParams)localView.getLayoutParams();
      localView.layout(localMarginLayoutParams.leftMargin, this.m + localMarginLayoutParams.topMargin, localMarginLayoutParams.leftMargin + localView.getMeasuredWidth(), this.m + localMarginLayoutParams.topMargin + localView.getMeasuredHeight());
      paramInt1 += 1;
    }
    if (!m.b().equals("0")) {
      f();
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int i1 = a();
    this.m = b();
    int i6 = h();
    int i7 = getChildCount();
    int i3 = 0;
    int i4;
    for (paramInt2 = i3; i3 < i7; paramInt2 = i4)
    {
      View localView = getChildAt(i3);
      ViewGroup.MarginLayoutParams localMarginLayoutParams = (ViewGroup.MarginLayoutParams)localView.getLayoutParams();
      int i5 = localMarginLayoutParams.width;
      int i2 = localMarginLayoutParams.width;
      i4 = 1073741824;
      if (i2 < 0) {
        i2 = 0;
      } else {
        i2 = 1073741824;
      }
      i5 = View.MeasureSpec.makeMeasureSpec(i5, i2);
      int i8 = localMarginLayoutParams.height;
      i2 = i4;
      if (localMarginLayoutParams.height < 0) {
        i2 = 0;
      }
      localView.measure(i5, View.MeasureSpec.makeMeasureSpec(i8, i2));
      if (localView.getVisibility() == 8)
      {
        i4 = paramInt2;
      }
      else
      {
        i4 = this.m + localMarginLayoutParams.topMargin + localView.getMeasuredHeight() + i6;
        i2 = i1;
        if (i4 > i1) {
          i2 = i4;
        }
        i5 = localMarginLayoutParams.leftMargin + localView.getMeasuredWidth();
        i4 = paramInt2;
        i1 = i2;
        if (i5 > paramInt2)
        {
          i4 = i5;
          i1 = i2;
        }
      }
      i3 += 1;
    }
    if (i()) {
      paramInt2 = Math.max(getSuggestedMinimumWidth(), View.MeasureSpec.getSize(paramInt1));
    }
    setMeasuredDimension(paramInt2, i1);
  }
  
  public void onSharedPreferenceChanged(SharedPreferences paramSharedPreferences, String paramString)
  {
    if (paramString.equals("locked"))
    {
      paramSharedPreferences = this.c;
      Context localContext = getContext();
      int i1 = 0;
      if (ak.a(localContext, paramString, false)) {
        i1 = 8;
      }
      paramSharedPreferences.setVisibility(i1);
      paramSharedPreferences = new Runnable()
      {
        public void run()
        {
          af.e(af.this);
        }
      };
    }
    else
    {
      if (!paramString.equals("disablePageScroll")) {
        return;
      }
      paramSharedPreferences = new Runnable()
      {
        public void run()
        {
          af.e(af.this);
        }
      };
    }
    post(paramSharedPreferences);
  }
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    if (paramInt1 != paramInt3)
    {
      u();
      if (this.e.d())
      {
        w();
        return;
      }
      x();
    }
  }
  
  @SuppressLint({"ClickableViewAccessibility"})
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    switch (paramMotionEvent.getAction())
    {
    default: 
      break;
    case 1: 
      a(paramMotionEvent);
      break;
    case 0: 
      return true;
    }
    return super.onTouchEvent(paramMotionEvent);
  }
  
  public void p() {}
  
  protected JSONArray q()
  {
    Object localObject1 = getActivity();
    Object localObject2 = new Point();
    cd.a((Activity)localObject1, (Point)localObject2);
    if ((localObject1 instanceof TvActivity)) {}
    for (;;)
    {
      try
      {
        localObject1 = new JSONArray(cd.a(getContext().getAssets().open("tv")));
        localObject3 = getContext().getPackageManager();
      }
      catch (Exception localException1)
      {
        Object localObject3;
        int i2;
        int i4;
        int i1;
        return null;
      }
      try
      {
        localObject2 = com.ss.launcher.utils.b.a();
        localObject3 = ((PackageManager)localObject3).getInstalledPackages(128).iterator();
        i2 = 0;
        i4 = 4;
        if (((Iterator)localObject3).hasNext())
        {
          Object localObject4 = (PackageInfo)((Iterator)localObject3).next();
          localObject4 = ((com.ss.launcher.utils.b)localObject2).b(getContext(), ((PackageInfo)localObject4).packageName, null).iterator();
          i1 = i4;
          int i3 = i2;
          if (((Iterator)localObject4).hasNext())
          {
            Object localObject5 = (com.ss.launcher.utils.c)((Iterator)localObject4).next();
            if (!((com.ss.launcher.utils.c)localObject5).b().getPackageName().equals(getContext().getPackageName()))
            {
              localObject5 = new bw(getContext(), (com.ss.launcher.utils.c)localObject5);
              ((bw)localObject5).setXPosition(i4);
              ((bw)localObject5).setWidthCount(2);
              ((JSONArray)localObject1).put(((bw)localObject5).C());
              if (i4 >= 4)
              {
                i3 = i2 + 1;
                i1 = 0;
              }
              else
              {
                i1 = i4 + 2;
                i3 = i2;
              }
              i4 = i1;
              i2 = i3;
              if (i3 < 3) {}
            }
          }
          else
          {
            i4 = i1;
            i2 = i3;
            if (i3 < 3) {}
          }
        }
        else
        {
          return localObject1;
        }
      }
      catch (Exception localException4)
      {
        return localException1;
      }
    }
    if (ak.a(getContext(), "tabletMode", false))
    {
      i1 = Math.max(3, ((Point)localObject2).y / this.f - 2);
      while (i1 >= 3)
      {
        try
        {
          localObject1 = getContext().getAssets();
          localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append("t_");
          ((StringBuilder)localObject2).append(i1);
          localObject1 = new JSONArray(cd.a(((AssetManager)localObject1).open(((StringBuilder)localObject2).toString())));
          return localObject1;
        }
        catch (Exception localException2)
        {
          for (;;) {}
        }
        i1 -= 1;
      }
    }
    else
    {
      i1 = Math.max(3, Math.min(((Point)localObject2).x, ((Point)localObject2).y) / this.f);
      while (i1 >= 3)
      {
        try
        {
          localObject1 = getContext().getAssets();
          localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append("p_");
          ((StringBuilder)localObject2).append(i1);
          localObject1 = new JSONArray(cd.a(((AssetManager)localObject1).open(((StringBuilder)localObject2).toString())));
          return localObject1;
        }
        catch (Exception localException3)
        {
          for (;;) {}
        }
        i1 -= 1;
      }
      return null;
    }
  }
  
  protected JSONArray r()
  {
    JSONArray localJSONArray = new JSONArray();
    int i2 = this.b.size();
    int i1 = 0;
    while (i1 < i2)
    {
      Object localObject = (be)this.b.get(i1);
      try
      {
        localObject = ((be)localObject).C();
        if (localObject != null) {
          localJSONArray.put(localObject);
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
      i1 += 1;
    }
    return localJSONArray;
  }
  
  protected void s()
  {
    removeAllViews();
    int i2 = this.b.size();
    int i1 = 0;
    while (i1 < i2)
    {
      addView((be)this.b.get(i1));
      i1 += 1;
    }
    addView(this.c);
  }
  
  public void setEffectOnly(boolean paramBoolean)
  {
    int i1 = 0;
    while (i1 < this.b.size())
    {
      ((be)this.b.get(i1)).setEffectOnly(paramBoolean);
      i1 += 1;
    }
    z();
  }
  
  public void setMoving(be paramBe)
  {
    if (this.d != paramBe)
    {
      this.d = paramBe;
      invalidate();
      if (this.d != null) {
        A();
      }
      for (;;)
      {
        this.n = false;
        return;
        if (!this.n) {
          break;
        }
        z();
      }
    }
  }
  
  public void setTileStyle(int paramInt)
  {
    int i1 = 0;
    while (i1 < this.b.size())
    {
      ((be)this.b.get(i1)).setStyle(paramInt);
      i1 += 1;
    }
    z();
  }
  
  protected void t()
  {
    Iterator localIterator = this.b.iterator();
    while (localIterator.hasNext())
    {
      be localBe = (be)localIterator.next();
      removeView(localBe);
      if (localBe.getType() == 0) {
        this.e.n().a((bw)localBe);
      }
    }
  }
  
  protected void u()
  {
    int i7 = getNumColumns() * 2;
    if (i()) {
      i1 = i7;
    } else {
      i1 = i7 + 2;
    }
    Object localObject = this.k;
    int i6 = 0;
    if ((localObject != null) && (this.k.length == i1)) {
      i1 = 0;
    }
    while (i1 < this.k.length)
    {
      this.k[i1] = 0;
      i1 += 1;
      continue;
      this.k = new int[i1];
    }
    int i8 = this.b.size();
    int i3 = 0;
    int i1 = i3;
    int i4;
    int i5;
    while (i3 < i8)
    {
      localObject = (be)this.b.get(i3);
      if (((be)localObject).E() == -1) {
        i2 = i1;
      } else {
        i2 = ((be)localObject).E() * 2;
      }
      if (((be)localObject).G())
      {
        i2 += 1;
        i1 = i2;
        if (i2 <= i7) {}
      }
      else
      {
        do
        {
          i1 = 0;
          break;
          i1 = i2;
        } while (((be)localObject).h() * 2 + i2 > i7);
      }
      if (((be)localObject).H()) {
        i2 = i1 + 1;
      } else if (i()) {
        i2 = Math.min(((be)localObject).h() * 2 + i1, i7);
      } else if (((be)localObject).h() == 100000) {
        i2 = i1 + i7;
      } else {
        i2 = ((be)localObject).h() * 2 + i1;
      }
      i4 = 0;
      i5 = i1;
      while (i5 < i2)
      {
        i4 = Math.max(this.k[i5], i4);
        i5 += 1;
      }
      i5 = i1;
      while (i5 < i2)
      {
        this.k[i5] = (((be)localObject).a_(this.f) + i4);
        i5 += 1;
      }
      ((be)localObject).getLayoutAnimator().a(i1 * this.f / 2, i4, this.f * i2 / 2, ((be)localObject).a_(this.f) + i4);
      if (i2 < i7) {
        i1 = i2;
      } else {
        i1 = 0;
      }
      i3 += 1;
    }
    int i2 = Math.max(this.k[0], this.k[1]);
    i3 = 0;
    i1 = 2;
    while (i1 < i7)
    {
      i5 = Math.max(this.k[i1], this.k[(i1 + 1)]);
      i4 = i2;
      if (i5 < i2)
      {
        i3 = i1;
        i4 = i5;
      }
      i1 += 2;
      i2 = i4;
    }
    i1 = c(this.f);
    if ((!i()) && (this.f + i2 > i1 * this.f))
    {
      this.c.setXPosition(i7 / 2);
      this.c.getLayoutAnimator().a(this.f * i7 / 2, 0, (i7 + 2) * this.f / 2, this.f);
    }
    else
    {
      this.c.setXPosition(i3 / 2);
      this.c.getLayoutAnimator().a(this.f * i3 / 2, i2, (i3 + 2) * this.f / 2, this.f + i2);
    }
    i2 = 0;
    for (;;)
    {
      i1 = i6;
      if (i2 >= getChildCount()) {
        break;
      }
      getChildAt(i2).setId(getStartId() + i2);
      i2 += 1;
    }
    while (i1 < getChildCount())
    {
      localObject = getChildAt(i1);
      if (((View)localObject).isFocusable())
      {
        be localBe1 = (be)localObject;
        be localBe2 = i(localBe1);
        if (localBe2 != null) {
          i2 = localBe2.getId();
        } else {
          i2 = -1;
        }
        ((View)localObject).setNextFocusUpId(i2);
        localBe1 = j(localBe1);
        if (localBe1 != null) {
          i2 = localBe1.getId();
        } else {
          i2 = -1;
        }
        ((View)localObject).setNextFocusDownId(i2);
      }
      i1 += 1;
    }
  }
  
  protected void v()
  {
    if ((ak.a(getContext(), "disablePageScroll", false)) && (this.e.G()) && ((getParent() instanceof ah))) {
      getPageView().smoothScrollTo(0, 0);
    }
  }
  
  protected void w()
  {
    post(this.l);
  }
  
  protected void x()
  {
    int i2 = this.b.size();
    int i1 = 0;
    while (i1 < i2)
    {
      be localBe = (be)this.b.get(i1);
      localBe.getLayoutAnimator().b(localBe);
      i1 += 1;
    }
    this.c.getLayoutAnimator().b(this.c);
  }
  
  public void y()
  {
    Resources localResources = this.e.getResources();
    Integer[] arrayOfInteger1;
    if (this.e.r().d())
    {
      arrayOfInteger1 = new Integer[9];
      arrayOfInteger1[0] = Integer.valueOf(2131165284);
      arrayOfInteger1[1] = Integer.valueOf(2131165398);
      arrayOfInteger1[2] = Integer.valueOf(2131165353);
      arrayOfInteger1[3] = Integer.valueOf(2131165412);
      arrayOfInteger1[4] = Integer.valueOf(2131165324);
      arrayOfInteger1[5] = Integer.valueOf(2131165319);
      arrayOfInteger1[6] = Integer.valueOf(2131165406);
      arrayOfInteger1[7] = Integer.valueOf(2131165286);
      arrayOfInteger1[8] = Integer.valueOf(2131165318);
    }
    for (int i1 = 2130837515;; i1 = 2130837520)
    {
      arrayOfString = localResources.getStringArray(i1);
      break;
      arrayOfInteger1 = new Integer[10];
      arrayOfInteger1[0] = Integer.valueOf(2131165369);
      arrayOfInteger1[1] = Integer.valueOf(2131165284);
      arrayOfInteger1[2] = Integer.valueOf(2131165398);
      arrayOfInteger1[3] = Integer.valueOf(2131165353);
      arrayOfInteger1[4] = Integer.valueOf(2131165412);
      arrayOfInteger1[5] = Integer.valueOf(2131165324);
      arrayOfInteger1[6] = Integer.valueOf(2131165319);
      arrayOfInteger1[7] = Integer.valueOf(2131165406);
      arrayOfInteger1[8] = Integer.valueOf(2131165286);
      arrayOfInteger1[9] = Integer.valueOf(2131165318);
    }
    final Integer[] arrayOfInteger2 = a.a(this.e, arrayOfInteger1);
    String[] arrayOfString = a.a(this.e, arrayOfString);
    com.ss.view.c.a(cd.b(this.e), this.e, null, localResources.getString(2131558403), arrayOfInteger1, arrayOfString, null, 1, 0, localResources.getDimensionPixelSize(2131099716), false, 0, new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        af.this.b(arrayOfInteger2[paramAnonymousInt].intValue());
      }
    }, null);
  }
  
  public void z()
  {
    if (!J()) {
      Toast.makeText(getContext(), 2131558584, 1).show();
    }
  }
  
  private static class a
  {
    be a;
    int b;
    int c;
    int d;
    
    a(be paramBe)
    {
      this.a = paramBe;
      this.b = paramBe.E();
      this.c = paramBe.h();
      this.d = paramBe.J();
    }
    
    be a()
    {
      this.a.setXPosition(this.b);
      this.a.setWidthCount(this.c);
      this.a.setHeightCount(this.d);
      return this.a;
    }
  }
}
