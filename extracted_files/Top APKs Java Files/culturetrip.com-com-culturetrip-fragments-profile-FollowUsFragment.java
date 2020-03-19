package com.culturetrip.fragments.profile;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.culturetrip.base.AbstractActivity;
import com.culturetrip.base.AbstractFragment;
import com.culturetrip.libs.data.beans.UserBean;
import com.culturetrip.utils.ClientLog;
import com.culturetrip.utils.report.MixpanelEvent;
import java.util.Iterator;
import java.util.List;

public class FollowUsFragment
  extends AbstractFragment
{
  private static final String LOG_TAG = "UserProfileFragment";
  
  public FollowUsFragment() {}
  
  private void init()
  {
    AbstractActivity localAbstractActivity = getMyActivity();
    initFacebook(localAbstractActivity);
    initTwitter(localAbstractActivity);
    initPinterest(localAbstractActivity);
    initInstagram(localAbstractActivity);
    initYoutube(localAbstractActivity);
  }
  
  private void initFacebook(final AbstractActivity paramAbstractActivity)
  {
    initRow(findViewInFragmentRootView(2131230981), paramAbstractActivity.getString(2131689635), 2131165429, new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        FollowUsFragment.this.reportClick(paramAbstractActivity, "facebook");
        try
        {
          paramAbstractActivity.getPackageManager().getPackageInfo("com.facebook.katana", 0);
          paramAbstractActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("fb://page/238808916142153")));
          return;
        }
        catch (Exception paramAnonymousView)
        {
          for (;;) {}
        }
        paramAbstractActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://www.facebook.com/culturetrip")));
      }
    });
  }
  
  private void initInstagram(final AbstractActivity paramAbstractActivity)
  {
    initRow(findViewInFragmentRootView(2131230982), paramAbstractActivity.getString(2131689636), 2131165430, new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        FollowUsFragment.this.reportClick(paramAbstractActivity, "instagram");
        if (FollowUsFragment.this.isPackageExisted(paramAbstractActivity, "com.instagram.android"))
        {
          paramAnonymousView = new Intent("android.intent.action.VIEW", Uri.parse("http://instagram.com/_u/culturetrip"));
          paramAnonymousView.setPackage("com.instagram.android");
          paramAbstractActivity.startActivity(paramAnonymousView);
          return;
        }
        FollowUsFragment.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://instagram.com/culturetrip")));
      }
    });
  }
  
  private void initPinterest(final AbstractActivity paramAbstractActivity)
  {
    initRow(findViewInFragmentRootView(2131230983), paramAbstractActivity.getString(2131689637), 2131165431, new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        FollowUsFragment.this.reportClick(paramAbstractActivity, "pinterest");
        try
        {
          FollowUsFragment.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("pinterest://www.pinterest.com/theculturetrip")));
          return;
        }
        catch (Exception paramAnonymousView)
        {
          for (;;) {}
        }
        FollowUsFragment.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://www.pinterest.com/theculturetrip")));
      }
    });
  }
  
  private void initRow(View paramView, String paramString, int paramInt, View.OnClickListener paramOnClickListener)
  {
    ImageView localImageView = (ImageView)paramView.findViewById(2131231328);
    localImageView.setColorFilter(null);
    localImageView.setImageResource(paramInt);
    ((TextView)paramView.findViewById(2131231329)).setText(paramString);
    paramView.setOnClickListener(paramOnClickListener);
  }
  
  private void initTwitter(final AbstractActivity paramAbstractActivity)
  {
    initRow(findViewInFragmentRootView(2131230984), paramAbstractActivity.getString(2131689639), 2131165432, new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        FollowUsFragment.this.reportClick(paramAbstractActivity, "twitter");
        try
        {
          paramAbstractActivity.getPackageManager().getPackageInfo("com.twitter.android", 0);
          paramAbstractActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("twitter://user?screen_name=CultureTrip")));
          return;
        }
        catch (Exception paramAnonymousView)
        {
          for (;;) {}
        }
        paramAbstractActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://twitter.com/CultureTrip")));
      }
    });
  }
  
  private void initYoutube(final AbstractActivity paramAbstractActivity)
  {
    initRow(findViewInFragmentRootView(2131230985), paramAbstractActivity.getString(2131689640), 2131165433, new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        FollowUsFragment.this.reportClick(paramAbstractActivity, "youtube");
        if (FollowUsFragment.this.isPackageExisted(paramAbstractActivity, "com.google.android.youtube"))
        {
          paramAnonymousView = new Intent("android.intent.action.VIEW");
          paramAnonymousView.setPackage("com.google.android.youtube");
          paramAnonymousView.setData(Uri.parse("https://www.youtube.com/c/culturetrip"));
          paramAbstractActivity.startActivity(paramAnonymousView);
          return;
        }
        FollowUsFragment.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://www.youtube.com/c/culturetrip")));
      }
    });
  }
  
  private boolean isPackageExisted(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getInstalledApplications(0).iterator();
    while (paramContext.hasNext()) {
      if (((ApplicationInfo)paramContext.next()).packageName.equals(paramString)) {
        return true;
      }
    }
    return false;
  }
  
  public static FollowUsFragment newInstance()
  {
    FollowUsFragment localFollowUsFragment = new FollowUsFragment();
    localFollowUsFragment.setArguments(new Bundle());
    return localFollowUsFragment;
  }
  
  private void reportClick(AbstractActivity paramAbstractActivity, String paramString)
  {
    boolean bool = UserBean.isAnonymous(paramAbstractActivity);
    paramAbstractActivity = new MixpanelEvent("a follow click", "user profile follow us").addProp("action", paramString);
    paramAbstractActivity.addProp("is signed in", Boolean.valueOf(bool ^ true));
    report(paramAbstractActivity);
  }
  
  protected void doOnActivityCreated(Bundle paramBundle)
  {
    ClientLog.i("UserProfileFragment", "doOnActivityCreated");
    init();
  }
  
  protected View doOnCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    ClientLog.i("UserProfileFragment", "doOnCreateView");
    return paramLayoutInflater.inflate(2131361886, paramViewGroup, false);
  }
  
  protected void doOnStop() {}
  
  public void onCreateOptionsMenu(Menu paramMenu, MenuInflater paramMenuInflater) {}
  
  public void onFragmentVisible(AbstractActivity paramAbstractActivity)
  {
    MixpanelEvent.setSource("user profile follow us");
    showWhiteStatusBar(paramAbstractActivity);
  }
  
  public final boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    return super.onOptionsItemSelected(paramMenuItem);
  }
}
