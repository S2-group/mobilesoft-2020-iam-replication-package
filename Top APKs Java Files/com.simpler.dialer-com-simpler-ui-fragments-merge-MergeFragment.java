package com.simpler.ui.fragments.merge;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.simpler.interfaces.OnMainActivityInteractionListener;
import com.simpler.logic.ConfigurationLogic;
import com.simpler.logic.LogicManager;
import com.simpler.logic.MergeLogic;
import com.simpler.logic.RateLogic;
import com.simpler.logic.SettingsLogic;
import com.simpler.logic.UpgradeLogic;
import com.simpler.ui.activities.DownloadSimplerContactsActivity;
import com.simpler.ui.activities.DownloadSimplerContactsActivity.AppDownloadType;
import com.simpler.ui.activities.MergeActivity;
import com.simpler.ui.fragments.BaseFragment;
import com.simpler.utils.AnalyticsUtils;
import com.simpler.utils.FilesUtils;
import com.simpler.utils.Logger;
import com.simpler.utils.ThemeUtils;
import com.simpler.utils.UiUtils;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MergeFragment
  extends BaseFragment
  implements View.OnClickListener
{
  public boolean _isButtonClicked;
  private ProgressBar a;
  private LinearLayout b;
  private TextView c;
  private MergeLogic d;
  private OnMainActivityInteractionListener e;
  
  public MergeFragment() {}
  
  private void a()
  {
    if (a("com.simpler.backup"))
    {
      startActivity(getActivity().getPackageManager().getLaunchIntentForPackage("com.simpler.backup"));
      AnalyticsUtils.switchToSimplerBackup();
      return;
    }
    Intent localIntent = new Intent(getActivity(), DownloadSimplerContactsActivity.class);
    localIntent.putExtra("app_promote_type", DownloadSimplerContactsActivity.AppDownloadType.BACKUP);
    startActivity(localIntent);
  }
  
  private void a(View paramView)
  {
    ImageView localImageView1 = (ImageView)paramView.findViewById(2131558473);
    ImageView localImageView2 = (ImageView)paramView.findViewById(2131558903);
    this.c = ((TextView)paramView.findViewById(2131558662));
    this.a = ((ProgressBar)paramView.findViewById(2131558648));
    this.b = ((LinearLayout)paramView.findViewById(2131558829));
    localImageView1.setOnClickListener(this);
    this.b.setOnClickListener(this);
    ((TextView)paramView.findViewById(2131558613)).setTextColor(getResources().getColor(ThemeUtils.getSubtitleColor()));
    int i = SettingsLogic.getPrimaryColor();
    this.c.setTextColor(i);
    this.a.getIndeterminateDrawable().setColorFilter(i, PorterDuff.Mode.SRC_IN);
    localImageView1.setOnTouchListener(new w(this, localImageView2));
    localImageView1.setColorFilter(i, PorterDuff.Mode.SRC_IN);
    localImageView2.setColorFilter(getActivity().getResources().getColor(ThemeUtils.getBigButtonPressedBackground()), PorterDuff.Mode.SRC_IN);
  }
  
  private boolean a(String paramString)
  {
    Iterator localIterator = getActivity().getPackageManager().getInstalledApplications(0).iterator();
    while (localIterator.hasNext()) {
      if (((ApplicationInfo)localIterator.next()).packageName.equals(paramString)) {
        return true;
      }
    }
    return false;
  }
  
  private void b()
  {
    if (a("com.simpler.contacts"))
    {
      startActivity(getActivity().getPackageManager().getLaunchIntentForPackage("com.simpler.contacts"));
      AnalyticsUtils.switchToSimplerContacts();
      return;
    }
    Intent localIntent = new Intent(getActivity(), DownloadSimplerContactsActivity.class);
    localIntent.putExtra("app_promote_type", DownloadSimplerContactsActivity.AppDownloadType.CONTACTS);
    startActivity(localIntent);
  }
  
  private void c()
  {
    if (this.d.isFindingDuplicates())
    {
      Logger.i("Simpler", "---- merge: busy wait");
      long l = TimeUnit.SECONDS.toMillis(2L);
      new Handler().postDelayed(new v(this), l);
    }
    do
    {
      return;
      if (!this.d.isDuplicatesFound()) {
        break;
      }
      Logger.i("Simpler", "---- merge: dup found");
      d();
    } while (!this._isButtonClicked);
    this._isButtonClicked = false;
    dismissProgressDialog();
    e();
    return;
    Logger.i("Simpler", "---- merge: start task");
    new x(this, null).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
  }
  
  private void d()
  {
    if ((!isAdded()) || (getActivity() == null)) {
      return;
    }
    int i = this.d.getTotalDuplicatesCount();
    this.c.setText(String.valueOf(i));
    this.c.setVisibility(0);
    if (this.b.getVisibility() != 0)
    {
      i = getResources().getInteger(17694722);
      this.b.setAlpha(0.0F);
      this.b.setVisibility(0);
      this.b.animate().alpha(1.0F).setDuration(i).setListener(null);
      this.a.setAlpha(1.0F);
      this.a.setVisibility(8);
      this.a.animate().alpha(0.0F).setDuration(i).setListener(null);
    }
    UiUtils.keepScreenOn(getActivity(), false);
  }
  
  private void e()
  {
    if (this.d.getTotalDuplicatesCount() <= 0)
    {
      Toast.makeText(getActivity(), 2131165718, 1).show();
      return;
    }
    Intent localIntent = new Intent(getActivity(), MergeActivity.class);
    Bundle localBundle = getActivity().getIntent().getExtras();
    if ((localBundle == null) || (!localBundle.containsKey("merge_activity_called_from"))) {
      localIntent.putExtra("merge_activity_called_from", "Merge fragment");
    }
    startActivityForResult(localIntent, 102);
    getActivity().overridePendingTransition(2130968591, 2130968604);
  }
  
  public void onAttach(Context paramContext)
  {
    super.onAttach(paramContext);
    if ((paramContext instanceof OnMainActivityInteractionListener)) {}
    try
    {
      this.e = ((OnMainActivityInteractionListener)paramContext);
      return;
    }
    catch (ClassCastException localClassCastException)
    {
      Logger.e("Simpler", paramContext.getClass().getSimpleName() + " must implement OnSettingsInteractionListener");
      Logger.e("Simpler", localClassCastException);
    }
  }
  
  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default: 
      return;
    }
    if (this.d.isFindingDuplicates())
    {
      showProgressDialog();
      this._isButtonClicked = true;
      return;
    }
    dismissProgressDialog();
    e();
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.d = LogicManager.getInstance().getMergeLogic();
  }
  
  public void onCreateOptionsMenu(Menu paramMenu, MenuInflater paramMenuInflater)
  {
    super.onCreateOptionsMenu(paramMenu, paramMenuInflater);
    paramMenuInflater.inflate(2131623949, paramMenu);
    paramMenuInflater = ConfigurationLogic.getInstance();
    if ((!paramMenuInflater.isUpgradeButtonVisible()) || (paramMenuInflater.isDiffValueValid()))
    {
      paramMenu.findItem(2131559005).setVisible(false);
      paramMenu.findItem(2131559005).setEnabled(false);
    }
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    return paramLayoutInflater.inflate(2130903235, paramViewGroup, false);
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    default: 
    case 2131559004: 
    case 2131559026: 
    case 2131559003: 
      for (;;)
      {
        return super.onOptionsItemSelected(paramMenuItem);
        if (this.e != null)
        {
          this.e.openSettingsActivity();
          continue;
          a();
          continue;
          b();
        }
      }
    }
    paramMenuItem = new Intent(getActivity(), UpgradeLogic.getInstance().getUpgradeActivityClass());
    paramMenuItem.putExtra("go_pro_action", "Overflow menu click");
    startActivity(paramMenuItem);
    getActivity().overridePendingTransition(2130968591, 2130968604);
    return true;
  }
  
  public void onResume()
  {
    super.onResume();
    c();
    LogicManager.getInstance().getRateLogic().checkShowRateDialog(getActivity());
    if (FilesUtils.getBooleanFromPreferences("recreate_option_menu", false))
    {
      getActivity().invalidateOptionsMenu();
      FilesUtils.saveToPreferences("recreate_option_menu", false);
    }
  }
  
  public void onViewCreated(View paramView, @Nullable Bundle paramBundle)
  {
    super.onViewCreated(paramView, paramBundle);
    a(paramView);
    this._isButtonClicked = false;
    setHasOptionsMenu(false);
    setThemeValues(paramView);
  }
  
  protected void setThemeValues(View paramView)
  {
    paramView.setBackgroundResource(ThemeUtils.getScreenBackgroundColor());
    ((TextView)paramView.findViewById(2131558917)).setTextColor(getResources().getColor(ThemeUtils.getSubtitleColor()));
  }
}
