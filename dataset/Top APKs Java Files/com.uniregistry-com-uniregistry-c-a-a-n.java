package com.uniregistry.c.a.a;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.support.v4.content.c;
import android.support.v7.widget.GridLayout;
import android.support.v7.widget.GridLayout.LayoutParams;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import com.uniregistry.a.aag;
import com.uniregistry.c.k;
import com.uniregistry.manager.t;
import com.uniregistry.manager.x;
import com.uniregistry.model.User;
import com.uniregistry.model.email.Email;
import com.uniregistry.model.email.Referral;
import com.uniregistry.model.email.ReferralMessage;
import com.uniregistry.model.email.ReferralMessages;
import com.uniregistry.model.email.ReferralProfile;
import com.uniregistry.model.email.ReferralPromo;
import com.uniregistry.model.email.ShareMedia;
import com.uniregistry.network.UniregistryApi.EndpointInterface;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.d.b.j;
import rx.b.g;
import rx.m;
import rx.schedulers.Schedulers;

public final class n
  extends k
{
  private final List<kotlin.f<ShareMedia, String>> a;
  private ReferralMessages b;
  private final Activity c;
  private final a d;
  
  public n(Activity paramActivity, a paramA)
  {
    this.c = paramActivity;
    this.d = paramA;
    this.a = kotlin.a.h.a(new kotlin.f[] { new kotlin.f(ShareMedia.FACEBOOK, "com.facebook.orca"), new kotlin.f(ShareMedia.WHATSAPP, "com.whatsapp"), new kotlin.f(ShareMedia.TWITTER, "com.twitter.android"), new kotlin.f(ShareMedia.MESSAGES, "messages"), new kotlin.f(ShareMedia.SHARE_DIALOG, "share_dialog") });
    this.compositeSubscription = new rx.h.b();
  }
  
  private final View a(CharSequence paramCharSequence, Drawable paramDrawable, String paramString)
  {
    Object localObject2 = this.c.getLayoutInflater();
    Object localObject1 = null;
    localObject2 = (aag)android.databinding.e.a(((LayoutInflater)localObject2).inflate(2131493275, null));
    if (localObject2 != null)
    {
      TextView localTextView = ((aag)localObject2).d;
      if (localTextView != null) {
        localTextView.setText(paramCharSequence);
      }
    }
    if (localObject2 != null)
    {
      paramCharSequence = ((aag)localObject2).c;
      if (paramCharSequence != null) {
        paramCharSequence.setImageDrawable(paramDrawable);
      }
    }
    if (localObject2 != null)
    {
      paramCharSequence = ((aag)localObject2).f();
      if (paramCharSequence != null) {
        paramCharSequence.setTag(paramString);
      }
    }
    paramCharSequence = new GridLayout.LayoutParams(GridLayout.spec(Integer.MIN_VALUE, 1.0F), GridLayout.spec(Integer.MIN_VALUE, 1.0F));
    int i = x.a(100.0F, (Context)this.c);
    paramCharSequence.height = i;
    paramCharSequence.width = i;
    if (localObject2 != null)
    {
      paramDrawable = ((aag)localObject2).f();
      if (paramDrawable != null) {
        paramDrawable.setLayoutParams((ViewGroup.LayoutParams)paramCharSequence);
      }
    }
    if ((j.a(paramString, "space") ^ true))
    {
      if (localObject2 != null)
      {
        paramCharSequence = ((aag)localObject2).f();
        if (paramCharSequence != null) {
          paramCharSequence.setClickable(true);
        }
      }
      if (localObject2 != null)
      {
        paramCharSequence = ((aag)localObject2).f();
        if (paramCharSequence != null) {
          paramCharSequence.setFocusable(true);
        }
      }
      if (localObject2 != null)
      {
        paramCharSequence = ((aag)localObject2).f();
        if (paramCharSequence != null) {
          paramCharSequence.setOnClickListener((View.OnClickListener)new b(this));
        }
      }
    }
    else if (localObject2 != null)
    {
      paramCharSequence = ((aag)localObject2).f();
      if (paramCharSequence != null) {
        paramCharSequence.setVisibility(4);
      }
    }
    paramCharSequence = localObject1;
    if (localObject2 != null) {
      paramCharSequence = ((aag)localObject2).f();
    }
    return paramCharSequence;
  }
  
  public final void a()
  {
    Object localObject = this.c.getPackageManager();
    localObject = rx.f.a((Iterable)((PackageManager)localObject).getInstalledApplications(128)).b(Schedulers.io()).c((rx.b.e)new c(this)).b((rx.b.f)new d((PackageManager)localObject)).a(rx.a.b.a.a()).f((rx.b.e)new e(this, (PackageManager)localObject)).k().a((rx.b.b)new f(this), (rx.b.b)new g(this));
    this.compositeSubscription.a((m)localObject);
  }
  
  public final void b()
  {
    Object localObject = this.sessionManager;
    j.a(localObject, "sessionManager");
    localObject = ((t)localObject).f();
    if (localObject != null) {
      localObject = ((User)localObject).getToken();
    } else {
      localObject = null;
    }
    rx.f localF1 = this.service.referralProfile((String)localObject, true, true);
    localObject = this.service.referralMessages((String)localObject, true, true);
    rx.f localF2 = this.service.referralPromo(true, true);
    this.d.onLoading(true);
    localObject = rx.f.a(localF1, localF2, (rx.f)localObject, (g)h.a).b(Schedulers.io()).a(rx.a.b.a.a()).a((rx.b.b)new i(this), (rx.b.b)new j(this));
    this.compositeSubscription.a((m)localObject);
  }
  
  public final String c()
  {
    Object localObject = this.b;
    if (localObject != null)
    {
      localObject = ((ReferralMessages)localObject).getReferralMessages();
      if (localObject != null)
      {
        Iterator localIterator = ((Iterable)localObject).iterator();
        while (localIterator.hasNext())
        {
          localObject = localIterator.next();
          int i;
          if (((ReferralMessage)localObject).getTargetName() == ShareMedia.MESSAGES) {
            i = 1;
          } else {
            i = 0;
          }
          if (i != 0) {
            break label73;
          }
        }
        localObject = null;
        label73:
        localObject = (ReferralMessage)localObject;
        if (localObject != null)
        {
          localObject = ((ReferralMessage)localObject).getMessage();
          if (localObject != null) {
            return localObject;
          }
        }
      }
    }
    return "";
  }
  
  public final Activity d()
  {
    return this.c;
  }
  
  public final a e()
  {
    return this.d;
  }
  
  public static abstract interface a
    extends com.uniregistry.b.a
  {
    public abstract void onAppClicked(String paramString, CharSequence paramCharSequence);
    
    public abstract void onLoading(boolean paramBoolean);
    
    public abstract void onReferralBalance(CharSequence paramCharSequence, boolean paramBoolean);
    
    public abstract void onReferralLoad(CharSequence paramCharSequence1, CharSequence paramCharSequence2, CharSequence paramCharSequence3);
    
    public abstract void onShareableApps(List<? extends View> paramList);
  }
  
  static final class b
    implements View.OnClickListener
  {
    b(n paramN) {}
    
    public final void onClick(View paramView)
    {
      Object localObject3 = ((Iterable)n.a(this.a)).iterator();
      Object localObject2;
      do
      {
        boolean bool = ((Iterator)localObject3).hasNext();
        localObject2 = null;
        if (!bool) {
          break;
        }
        localObject1 = ((Iterator)localObject3).next();
        localObject4 = (String)((kotlin.f)localObject1).b();
        j.a(paramView, "it");
      } while (!j.a(localObject4, paramView.getTag()));
      break label78;
      Object localObject1 = null;
      label78:
      localObject1 = (kotlin.f)localObject1;
      if (localObject1 != null) {
        localObject1 = (ShareMedia)((kotlin.f)localObject1).a();
      } else {
        localObject1 = null;
      }
      localObject3 = n.b(this.a);
      int i;
      if (localObject3 != null)
      {
        localObject3 = ((ReferralMessages)localObject3).getReferralMessages();
        if (localObject3 != null)
        {
          localObject4 = ((Iterable)localObject3).iterator();
          while (((Iterator)localObject4).hasNext())
          {
            localObject3 = ((Iterator)localObject4).next();
            if (((ReferralMessage)localObject3).getTargetName() == localObject1) {
              i = 1;
            } else {
              i = 0;
            }
            if (i != 0)
            {
              localObject1 = localObject3;
              break label197;
            }
          }
          localObject1 = null;
          label197:
          localObject1 = (ReferralMessage)localObject1;
          if (localObject1 != null) {
            break label313;
          }
        }
      }
      localObject1 = n.b(this.a);
      if (localObject1 != null)
      {
        localObject1 = ((ReferralMessages)localObject1).getReferralMessages();
        if (localObject1 != null)
        {
          localObject3 = ((Iterable)localObject1).iterator();
          while (((Iterator)localObject3).hasNext())
          {
            localObject1 = (ReferralMessage)((Iterator)localObject3).next();
            if (((ReferralMessage)localObject1).getTargetName() == ShareMedia.SHARE_DIALOG) {
              i = 1;
            } else {
              i = 0;
            }
            if (i != 0) {
              break label313;
            }
          }
          throw ((Throwable)new NoSuchElementException("Collection contains no element matching the predicate."));
        }
      }
      localObject1 = null;
      label313:
      localObject3 = this.a.e();
      j.a(paramView, "it");
      Object localObject4 = paramView.getTag().toString();
      paramView = localObject2;
      if (localObject1 != null) {
        paramView = ((ReferralMessage)localObject1).getMessage();
      }
      ((n.a)localObject3).onAppClicked((String)localObject4, (CharSequence)paramView);
    }
  }
  
  static final class c<T, R>
    implements rx.b.e<ApplicationInfo, Boolean>
  {
    c(n paramN) {}
    
    public final boolean a(ApplicationInfo paramApplicationInfo)
    {
      Object localObject = (Iterable)n.a(this.a);
      Collection localCollection = (Collection)new ArrayList(kotlin.a.h.a((Iterable)localObject, 10));
      localObject = ((Iterable)localObject).iterator();
      while (((Iterator)localObject).hasNext()) {
        localCollection.add((String)((kotlin.f)((Iterator)localObject).next()).b());
      }
      return ((List)localCollection).contains(paramApplicationInfo.packageName);
    }
  }
  
  static final class d<T1, T2, R>
    implements rx.b.f<ApplicationInfo, ApplicationInfo, Integer>
  {
    d(PackageManager paramPackageManager) {}
    
    public final int a(ApplicationInfo paramApplicationInfo1, ApplicationInfo paramApplicationInfo2)
    {
      return paramApplicationInfo1.loadLabel(this.a).toString().compareTo(paramApplicationInfo2.loadLabel(this.a).toString());
    }
  }
  
  static final class e<T, R>
    implements rx.b.e<T, R>
  {
    e(n paramN, PackageManager paramPackageManager) {}
    
    public final View a(ApplicationInfo paramApplicationInfo)
    {
      n localN = this.a;
      CharSequence localCharSequence = paramApplicationInfo.loadLabel(this.b);
      j.a(localCharSequence, "it.loadLabel(pm)");
      Drawable localDrawable = paramApplicationInfo.loadIcon(this.b);
      j.a(localDrawable, "it.loadIcon(pm)");
      paramApplicationInfo = paramApplicationInfo.packageName;
      j.a(paramApplicationInfo, "it.packageName");
      return n.a(localN, localCharSequence, localDrawable, paramApplicationInfo);
    }
  }
  
  static final class f<T>
    implements rx.b.b<List<View>>
  {
    f(n paramN) {}
    
    public final void a(List<View> paramList)
    {
      Object localObject1 = this.a;
      Object localObject2 = this.a.d().getString(2131821645);
      j.a(localObject2, "context.getString(R.string.more_options)");
      localObject2 = (CharSequence)localObject2;
      Object localObject3 = c.a((Context)this.a.d(), 2131231132);
      if (localObject3 == null) {
        j.a();
      }
      j.a(localObject3, "ContextCompat.getDrawabl…awable.ic_more_options)!!");
      localObject1 = n.a((n)localObject1, (CharSequence)localObject2, (Drawable)localObject3, "more");
      localObject2 = this.a;
      localObject3 = this.a.d().getString(2131821645);
      j.a(localObject3, "context.getString(R.string.more_options)");
      localObject3 = (CharSequence)localObject3;
      Drawable localDrawable = c.a((Context)this.a.d(), 2131231132);
      if (localDrawable == null) {
        j.a();
      }
      j.a(localDrawable, "ContextCompat.getDrawabl…awable.ic_more_options)!!");
      localObject2 = n.a((n)localObject2, (CharSequence)localObject3, localDrawable, "space");
      if (paramList.size() >= 3)
      {
        paramList.add(localObject2);
        paramList.add(localObject1);
      }
      else
      {
        paramList.add(localObject1);
      }
      localObject1 = this.a.e();
      j.a(paramList, "apps");
      ((n.a)localObject1).onShareableApps(paramList);
    }
  }
  
  static final class g<T>
    implements rx.b.b<Throwable>
  {
    g(n paramN) {}
    
    public final void a(Throwable paramThrowable)
    {
      this.a.loadGenericError((Context)this.a.d(), paramThrowable, (com.uniregistry.b.a)this.a.e());
    }
  }
  
  static final class h<T1, T2, T3, R>
    implements g<T1, T2, T3, R>
  {
    public static final h a = new h();
    
    h() {}
    
    public final kotlin.h<ReferralProfile, ReferralPromo, ReferralMessages> a(ReferralProfile paramReferralProfile, ReferralPromo paramReferralPromo, ReferralMessages paramReferralMessages)
    {
      return new kotlin.h(paramReferralProfile, paramReferralPromo, paramReferralMessages);
    }
  }
  
  static final class i<T>
    implements rx.b.b<kotlin.h<? extends ReferralProfile, ? extends ReferralPromo, ? extends ReferralMessages>>
  {
    i(n paramN) {}
    
    public final void a(kotlin.h<ReferralProfile, ReferralPromo, ReferralMessages> paramH)
    {
      Object localObject1 = (ReferralProfile)paramH.a();
      Object localObject4 = (ReferralPromo)paramH.b();
      n.a(this.a, (ReferralMessages)paramH.c());
      Object localObject3 = x.h().format(((ReferralPromo)localObject4).getCreditReferral());
      paramH = ((ReferralProfile)localObject1).getReferral();
      if (paramH != null)
      {
        paramH = paramH.getEmail();
        if (paramH != null)
        {
          paramH = paramH.getBalanceUsd();
          break label72;
        }
      }
      paramH = null;
      label72:
      Object localObject2 = x.h().format(paramH);
      Object localObject5 = x.h().format(((ReferralPromo)localObject4).getCreditNewUser());
      localObject4 = this.a.d();
      boolean bool = true;
      localObject4 = ((Activity)localObject4).getString(2131822112, new Object[] { localObject3 });
      localObject5 = this.a.d().getString(2131821921, new Object[] { localObject3, localObject5 });
      localObject3 = this.a.e();
      j.a(localObject4, "title");
      localObject4 = (CharSequence)localObject4;
      j.a(localObject5, "desc");
      localObject5 = (CharSequence)localObject5;
      localObject1 = ((ReferralProfile)localObject1).getReferral();
      if (localObject1 != null)
      {
        localObject1 = ((Referral)localObject1).getEmail();
        if (localObject1 != null)
        {
          localObject1 = ((Email)localObject1).getReferralCode();
          if (localObject1 != null)
          {
            localObject1 = (CharSequence)localObject1;
            break label229;
          }
        }
      }
      localObject1 = (CharSequence)"";
      label229:
      ((n.a)localObject3).onReferralLoad((CharSequence)localObject4, (CharSequence)localObject5, (CharSequence)localObject1);
      localObject1 = this.a.e();
      j.a(localObject2, "balance");
      localObject2 = (CharSequence)localObject2;
      if (paramH == null) {
        j.a();
      }
      if (paramH.floatValue() <= 0.0F) {
        bool = false;
      }
      ((n.a)localObject1).onReferralBalance((CharSequence)localObject2, bool);
      this.a.e().onLoading(false);
    }
  }
  
  static final class j<T>
    implements rx.b.b<Throwable>
  {
    j(n paramN) {}
    
    public final void a(Throwable paramThrowable)
    {
      this.a.e().onLoading(false);
      this.a.loadGenericError((Context)this.a.d(), paramThrowable, (com.uniregistry.b.a)this.a.e());
    }
  }
}
