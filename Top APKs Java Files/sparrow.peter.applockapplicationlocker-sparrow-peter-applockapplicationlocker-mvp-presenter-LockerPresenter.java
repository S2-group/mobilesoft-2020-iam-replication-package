package sparrow.peter.applockapplicationlocker.mvp.presenter;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.NativeExpressAdView;
import com.google.android.gms.analytics.Tracker;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import sparrow.peter.applockapplicationlocker.dependency.MyApplication;
import sparrow.peter.applockapplicationlocker.mvp.LockerMVP.ProvidedModelOps;
import sparrow.peter.applockapplicationlocker.mvp.LockerMVP.ProvidedPresenterOps;
import sparrow.peter.applockapplicationlocker.mvp.LockerMVP.RequiredPresenterOps;
import sparrow.peter.applockapplicationlocker.mvp.LockerMVP.RequiredViewOps;
import sparrow.peter.applockapplicationlocker.mvp.views.MainActivity;
import sparrow.peter.applockapplicationlocker.mvp.views.adapter.LockerRecyclerView.LockerItemViewHolder;
import sparrow.peter.applockapplicationlocker.mvp.views.adapter.LockerRecyclerView.NativeExpressAdViewHolder;
import sparrow.peter.applockapplicationlocker.mvp.views.pojo.LockerItem;

public class LockerPresenter
  implements LockerMVP.ProvidedPresenterOps, LockerMVP.RequiredPresenterOps
{
  private List<Object> mItems;
  private LockerMVP.ProvidedModelOps mModel;
  Tracker mTracker;
  private WeakReference<LockerMVP.RequiredViewOps> mView;
  private Typeface rbMedium;
  private Typeface rbRegular;
  
  public LockerPresenter(LockerMVP.RequiredViewOps paramRequiredViewOps)
  {
    this.mView = new WeakReference(paramRequiredViewOps);
    this.mTracker = ((MyApplication)((MainActivity)paramRequiredViewOps.getActivityContext()).getApplication()).getDefaultTracker();
    this.rbMedium = Typeface.createFromAsset(paramRequiredViewOps.getAppContext().getAssets(), "font/Roboto-Medium.ttf");
    this.rbRegular = Typeface.createFromAsset(paramRequiredViewOps.getAppContext().getAssets(), "font/Roboto-Regular.ttf");
  }
  
  private void addLockerItems()
  {
    PackageManager localPackageManager = ((LockerMVP.RequiredViewOps)this.mView.get()).getAppContext().getPackageManager();
    Iterator localIterator = localPackageManager.getInstalledApplications(0).iterator();
    while (localIterator.hasNext())
    {
      Object localObject = (ApplicationInfo)localIterator.next();
      String str1 = ((ApplicationInfo)localObject).packageName;
      if ((isInLauncherMenu(str1, localPackageManager)) && (!((LockerMVP.RequiredViewOps)this.mView.get()).getAppContext().getPackageName().equals(str1)))
      {
        Drawable localDrawable = localPackageManager.getApplicationIcon((ApplicationInfo)localObject);
        String str2 = localPackageManager.getApplicationLabel((ApplicationInfo)localObject).toString();
        if ((((ApplicationInfo)localObject).flags & 0x1) != 0) {}
        for (localObject = this.mModel.getAppResources().getString(2131230765);; localObject = this.mModel.getAppResources().getString(2131230766))
        {
          this.mItems.add(new LockerItem(localDrawable, str2, (String)localObject, str1));
          break;
        }
      }
    }
    Collections.sort(this.mItems, new Comparator()
    {
      public int compare(Object paramAnonymousObject1, Object paramAnonymousObject2)
      {
        return ((LockerItem)paramAnonymousObject1).title.compareToIgnoreCase(((LockerItem)paramAnonymousObject2).title);
      }
    });
  }
  
  private void addNativeExpressAds()
  {
    int i = 0;
    while (i < this.mItems.size())
    {
      this.mItems.add(i, new NativeExpressAdView(getActivityContext()));
      i += 8;
    }
  }
  
  private void bindAdView(CardView paramCardView, View paramView)
  {
    if (paramCardView.getChildCount() > 0) {
      paramCardView.removeAllViews();
    }
    try
    {
      paramCardView.addView(paramView);
      return;
    }
    catch (Exception paramCardView) {}
  }
  
  private boolean isInLauncherMenu(String paramString, PackageManager paramPackageManager)
  {
    paramString = paramPackageManager.getLaunchIntentForPackage(paramString);
    return (paramString != null) && (paramString.hasCategory("android.intent.category.LAUNCHER"));
  }
  
  private void loadNativeExpressAd(final int paramInt)
  {
    if (paramInt >= this.mItems.size()) {
      return;
    }
    NativeExpressAdView localNativeExpressAdView = (NativeExpressAdView)this.mItems.get(paramInt);
    localNativeExpressAdView.setAdListener(new AdListener()
    {
      public void onAdFailedToLoad(int paramAnonymousInt)
      {
        LockerPresenter.this.loadNativeExpressAd(paramInt + 8);
      }
      
      public void onAdLoaded()
      {
        LockerPresenter.this.loadNativeExpressAd(paramInt + 8);
      }
    });
    localNativeExpressAdView.loadAd(new AdRequest.Builder().addTestDevice("42DA58C91CE435DFB6C0A3CD84572738").build());
  }
  
  private void setUpAndLoadNativeExpressAds()
  {
    ((LockerMVP.RequiredViewOps)this.mView.get()).getRecyclerView().post(new Runnable()
    {
      public void run()
      {
        float f = LockerPresenter.this.mModel.getAppResources().getDisplayMetrics().density;
        int i = 0;
        while (i < LockerPresenter.this.mItems.size())
        {
          AdSize localAdSize = new AdSize((int)(((LockerMVP.RequiredViewOps)LockerPresenter.this.mView.get()).getRecyclerView().getWidth() / f), 150);
          NativeExpressAdView localNativeExpressAdView = (NativeExpressAdView)LockerPresenter.this.mItems.get(i);
          localNativeExpressAdView.setAdSize(localAdSize);
          localNativeExpressAdView.setAdUnitId(LockerPresenter.this.mModel.getAppString(2131230826));
          i += 8;
        }
        LockerPresenter.this.loadNativeExpressAd(0);
      }
    });
  }
  
  public Context getActivityContext()
  {
    return ((LockerMVP.RequiredViewOps)this.mView.get()).getActivityContext();
  }
  
  public Context getAppContext()
  {
    return ((LockerMVP.RequiredViewOps)this.mView.get()).getAppContext();
  }
  
  public int getItemCount()
  {
    return this.mItems.size();
  }
  
  public int getItemViewType(int paramInt)
  {
    if (paramInt % 8 == 0) {
      return 1;
    }
    return 0;
  }
  
  public void onBindViewHolder(RecyclerView.ViewHolder paramViewHolder, int paramInt)
  {
    switch (getItemViewType(paramInt))
    {
    default: 
      paramViewHolder = (LockerRecyclerView.NativeExpressAdViewHolder)paramViewHolder;
      localObject = (NativeExpressAdView)this.mItems.get(paramInt);
      bindAdView((CardView)paramViewHolder.itemView, (View)localObject);
      return;
    }
    paramViewHolder = (LockerRecyclerView.LockerItemViewHolder)paramViewHolder;
    Object localObject = (LockerItem)this.mItems.get(paramInt);
    paramViewHolder.icon.setImageDrawable(((LockerItem)localObject).icon);
    paramViewHolder.title.setTypeface(this.rbMedium);
    paramViewHolder.title.setText(((LockerItem)localObject).title);
    paramViewHolder.description.setTypeface(this.rbRegular);
    paramViewHolder.description.setText(((LockerItem)localObject).description);
    paramViewHolder.lockState.setImageResource(this.mModel.getImageID(((LockerItem)localObject).packageName));
    paramViewHolder.itemView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        LockerPresenter.this.onListItemClick(paramAnonymousView, this.val$item.packageName);
      }
    });
  }
  
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup paramViewGroup, int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return new LockerRecyclerView.NativeExpressAdViewHolder(LayoutInflater.from(getAppContext()).inflate(2130968629, paramViewGroup, false));
    }
    return new LockerRecyclerView.LockerItemViewHolder(LayoutInflater.from(getAppContext()).inflate(2130968628, paramViewGroup, false));
  }
  
  public void onDestroy(boolean paramBoolean) {}
  
  public void onListItemClick(View paramView, String paramString)
  {
    if (this.mModel.getAllLockedPackages().contains(paramString))
    {
      this.mModel.deleteLockedPackage(paramString);
      ((LockerMVP.RequiredViewOps)this.mView.get()).showToast(2131230793);
    }
    for (;;)
    {
      ((LockerMVP.RequiredViewOps)this.mView.get()).getRecyclerView().getAdapter().notifyDataSetChanged();
      return;
      this.mModel.insertLockedPackage(paramString);
      ((LockerMVP.RequiredViewOps)this.mView.get()).showToast(2131230791);
    }
  }
  
  public void prepareItems()
  {
    this.mItems = new ArrayList();
    addLockerItems();
    addNativeExpressAds();
    setUpAndLoadNativeExpressAds();
  }
  
  public void setModel(LockerMVP.ProvidedModelOps paramProvidedModelOps)
  {
    this.mModel = paramProvidedModelOps;
  }
  
  public void setView(LockerMVP.RequiredViewOps paramRequiredViewOps)
  {
    this.mView = new WeakReference(paramRequiredViewOps);
  }
}
