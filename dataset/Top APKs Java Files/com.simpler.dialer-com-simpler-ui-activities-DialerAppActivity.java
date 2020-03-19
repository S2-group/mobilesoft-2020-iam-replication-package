package com.simpler.ui.activities;

import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.provider.CallLog.Calls;
import android.provider.ContactsContract.Contacts;
import android.provider.Settings.System;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.Tab;
import android.support.design.widget.TabLayout.TabLayoutOnPageChangeListener;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.Html;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewPropertyAnimator;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import com.algolia.search.Index;
import com.algolia.search.SearchQuery;
import com.algolia.search.SearchResult;
import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.ShowcaseView.Builder;
import com.github.amlcurran.showcaseview.targets.ViewTarget;
import com.simpler.application.SimplerApplication;
import com.simpler.data.ShowCaseInfo;
import com.simpler.data.contact.AlgoContact;
import com.simpler.logic.ConfigurationLogic;
import com.simpler.logic.ContactsLogic;
import com.simpler.logic.ContactsLogic.OnAlgoliaSearchResultListener;
import com.simpler.logic.LogicManager;
import com.simpler.logic.LoginLogic;
import com.simpler.logic.PackageLogic;
import com.simpler.logic.RateLogic;
import com.simpler.logic.SettingsLogic;
import com.simpler.logic.UpgradeLogic;
import com.simpler.logic.UploadLogic;
import com.simpler.receivers.MissedCallsNotificationClickReceiver;
import com.simpler.ui.fragments.home.CallLogFragment.OnCallLogFragmentInteractionListener;
import com.simpler.ui.fragments.home.FavoritesFragment.OnFavoritesFragmentInteractionListener;
import com.simpler.ui.views.AppSectionsViewPager;
import com.simpler.ui.views.DialpadView;
import com.simpler.ui.views.DialpadView.OnDialpadListener;
import com.simpler.ui.views.SearchResultsView;
import com.simpler.ui.views.SearchResultsView.OnSearchResultsScrollListener;
import com.simpler.utils.AnalyticsUtils;
import com.simpler.utils.AnalyticsUtils.AnalyticsDialType;
import com.simpler.utils.DialogUtils;
import com.simpler.utils.FilesUtils;
import com.simpler.utils.PermissionUtils;
import com.simpler.utils.ThemeUtils;
import com.simpler.utils.UiUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DialerAppActivity
  extends BaseAppLauncherActivity
  implements ContactsLogic.OnAlgoliaSearchResultListener, CallLogFragment.OnCallLogFragmentInteractionListener, FavoritesFragment.OnFavoritesFragmentInteractionListener, DialpadView.OnDialpadListener, SearchResultsView.OnSearchResultsScrollListener
{
  private TabLayout a;
  private cy b;
  private FloatingActionButton c;
  private Handler d;
  private DialpadView e;
  private Runnable f;
  private AppSectionsViewPager g;
  private CardView h;
  private EditText i;
  private View j;
  private View k;
  private SearchResultsView l;
  private ShowcaseView m;
  private int n;
  private int o;
  private int p;
  private int q;
  private int r;
  private boolean s = false;
  private boolean t = false;
  private boolean u;
  
  public DialerAppActivity() {}
  
  private void A()
  {
    this.d.postDelayed(this.f, 1000L);
  }
  
  private void B()
  {
    this.d.removeCallbacks(this.f);
    if (this.n == 0) {
      this.n = C();
    }
    if (this.n != 0) {
      a(this.n);
    }
  }
  
  private int C()
  {
    int i1 = this.c.getHeight();
    Object localObject = getWindowManager().getDefaultDisplay();
    Point localPoint = new Point();
    ((Display)localObject).getSize(localPoint);
    int i2 = localPoint.y;
    localObject = new int[2];
    this.c.getLocationOnScreen((int[])localObject);
    return i1 + (i2 - i1 - localObject[1]);
  }
  
  private boolean D()
  {
    boolean bool = false;
    try
    {
      String str = E().getVoiceMailNumber();
      if (str != null) {
        bool = true;
      }
      return bool;
    }
    catch (SecurityException localSecurityException)
    {
      localSecurityException.printStackTrace();
    }
    return false;
  }
  
  private TelephonyManager E()
  {
    return (TelephonyManager)getSystemService("phone");
  }
  
  private void F()
  {
    ((InputMethodManager)getSystemService("input_method")).hideSoftInputFromWindow(this.i.getWindowToken(), 0);
  }
  
  private int G()
  {
    int i2 = SettingsLogic.getInstance().getStartupScreen();
    int i1 = i2;
    if (i2 < 0) {
      i1 = FilesUtils.getIntFromPreferences("last_used_home_tab_2", 0);
    }
    return i1;
  }
  
  private void H()
  {
    UploadLogic.getInstance().checkAndRunInitialBackup(this);
    LoginLogic.getInstance().checkSendAppDetails(this);
  }
  
  private void I()
  {
    LogicManager.getInstance().getRateLogic().checkShowRateDialog(this);
  }
  
  private void J()
  {
    Object localObject1 = getIntent();
    if (localObject1 == null) {}
    Cursor localCursor;
    do
    {
      do
      {
        for (;;)
        {
          return;
          String str2 = ((Intent)localObject1).getAction();
          String str1 = ((Intent)localObject1).getType();
          try
          {
            if (("android.intent.action.DIAL".equals(str2)) || ("android.intent.action.VIEW".equals(str2)))
            {
              localObject1 = ((Intent)localObject1).getData();
              if (localObject1 != null) {
                if ("tel".equals(((Uri)localObject1).getScheme()))
                {
                  this.a.setVisibility(8);
                  this.g.setVisibility(8);
                  showDialer(false);
                  str1 = ((Uri)localObject1).getSchemeSpecificPart();
                  this.e.setPhoneNumber(str1);
                  return;
                }
              }
            }
          }
          catch (Exception localException)
          {
            localException.printStackTrace();
            return;
          }
        }
      } while ((!"vnd.android.cursor.item/contact".equals(localException)) && (!"vnd.android.cursor.item/phone_v2".equals(localException)));
      localCursor = getContentResolver().query((Uri)localObject1, new String[] { "data1" }, null, null, null);
    } while (localCursor == null);
    try
    {
      if (localCursor.moveToFirst())
      {
        this.a.setVisibility(8);
        this.g.setVisibility(8);
        showDialer(false);
        localObject1 = localCursor.getString(0);
        this.e.setPhoneNumber((String)localObject1);
      }
      return;
    }
    finally
    {
      localCursor.close();
    }
  }
  
  private void a()
  {
    this.l.setScrollListener(this);
    this.l.setDialListener(new cr(this));
  }
  
  private void a(float paramFloat)
  {
    ObjectAnimator localObjectAnimator = ObjectAnimator.ofFloat(this.c, View.TRANSLATION_Y, new float[] { paramFloat });
    int i1 = getResources().getInteger(17694720);
    Interpolator localInterpolator = AnimationUtils.loadInterpolator(this, 17432580);
    localObjectAnimator.setDuration(i1);
    localObjectAnimator.setInterpolator(localInterpolator);
    localObjectAnimator.start();
  }
  
  private void a(float paramFloat, boolean paramBoolean)
  {
    ObjectAnimator localObjectAnimator = ObjectAnimator.ofFloat(this.c, View.TRANSLATION_X, new float[] { paramFloat });
    localObjectAnimator.addListener(new co(this, paramBoolean));
    try
    {
      localObjectAnimator.setDuration(SimplerApplication.getContext().getResources().getInteger(17694720));
      localObjectAnimator.setInterpolator(AnimationUtils.loadInterpolator(this, 17432580));
      localObjectAnimator.start();
      return;
    }
    catch (Exception localException)
    {
      if (paramBoolean)
      {
        y();
        return;
      }
      z();
    }
  }
  
  private void a(int paramInt)
  {
    int i1 = 0;
    if (i1 < this.b.getCount())
    {
      Object localObject = this.a.getTabAt(i1);
      int i2;
      if (localObject != null)
      {
        if (i1 != paramInt) {
          break label80;
        }
        i2 = 255;
        label37:
        if (i1 != paramInt) {
          break label87;
        }
      }
      label80:
      label87:
      for (int i3 = -1;; i3 = -16777216)
      {
        localObject = ((TabLayout.Tab)localObject).getIcon();
        if (localObject != null)
        {
          ((Drawable)localObject).setAlpha(i2);
          ((Drawable)localObject).setColorFilter(i3, PorterDuff.Mode.SRC_IN);
        }
        i1 += 1;
        break;
        i2 = 150;
        break label37;
      }
    }
    b(paramInt);
  }
  
  private void a(boolean paramBoolean)
  {
    int i1 = 0;
    if (this.t) {
      return;
    }
    this.t = true;
    this.e.setVisibility(0);
    if (this == null) {}
    for (Object localObject = SimplerApplication.getContext();; localObject = this)
    {
      if (paramBoolean) {
        localObject = AnimationUtils.loadAnimation((Context)localObject, 2130968599);
      }
      for (;;)
      {
        ((Animation)localObject).setAnimationListener(new cn(this, i1));
        this.e.startAnimation((Animation)localObject);
        return;
        localObject = AnimationUtils.loadAnimation((Context)localObject, 2130968598);
        i1 = 8;
      }
    }
  }
  
  private void b()
  {
    FilesUtils.saveToPreferences("show_showcase", false);
    this.r = 0;
    Object localObject2 = new ArrayList();
    ((ArrayList)localObject2).add(new ShowCaseInfo(new ViewTarget(2131558572, this), getString(2131165871), getString(2131165268), 2));
    if ((this.a.getChildAt(0) instanceof ViewGroup))
    {
      localObject1 = (ViewGroup)this.a.getChildAt(0);
      if (((ViewGroup)localObject1).getChildCount() == 2)
      {
        ((ArrayList)localObject2).add(new ShowCaseInfo(new ViewTarget(((ViewGroup)localObject1).getChildAt(0)), getString(2131165637), getString(2131165348), 0));
        ((ArrayList)localObject2).add(new ShowCaseInfo(new ViewTarget(((ViewGroup)localObject1).getChildAt(1)), getString(2131165562), getString(2131165734), 2));
      }
    }
    int i1 = 0;
    if (i1 < ((ArrayList)localObject2).size())
    {
      ShowCaseInfo localShowCaseInfo = (ShowCaseInfo)((ArrayList)localObject2).get(i1);
      if (i1 + 1 == ((ArrayList)localObject2).size()) {}
      for (localObject1 = getString(2131165410);; localObject1 = getString(2131165497))
      {
        localObject1 = Html.fromHtml(String.format("<br><h3>%s</h3></br>", new Object[] { localObject1 }));
        localShowCaseInfo.setText(localShowCaseInfo.getText() + localObject1.toString());
        i1 += 1;
        break;
      }
    }
    Object localObject1 = new cs(this, (ArrayList)localObject2);
    localObject2 = (ShowCaseInfo)((ArrayList)localObject2).get(this.r);
    this.m = new ShowcaseView.Builder(this, true).setTarget(((ShowCaseInfo)localObject2).getTarget()).setContentTitle(((ShowCaseInfo)localObject2).getTitle()).setContentText(((ShowCaseInfo)localObject2).getText()).setOnClickListener((View.OnClickListener)localObject1).hideOnTouchOutside().build();
    this.m.hideButton();
  }
  
  private void b(int paramInt)
  {
    FilesUtils.saveToPreferences("last_used_home_tab_2", paramInt);
  }
  
  private void c()
  {
    this.h = ((CardView)findViewById(2131558843));
    this.i = ((EditText)findViewById(2131558813));
    this.h.setVisibility(0);
    ImageView localImageView = (ImageView)findViewById(2131558814);
    localImageView.setColorFilter(Color.parseColor("#909090"), PorterDuff.Mode.SRC_IN);
    localImageView.setAlpha(0.0F);
    localImageView.setOnClickListener(new ct(this));
    clearSearchBoxFocus();
    this.i.setOnFocusChangeListener(new cu(this));
    int i1 = getResources().getInteger(17694720);
    this.i.addTextChangedListener(new cv(this, localImageView, i1));
    i1 = FilesUtils.getIntFromPreferences("search_bar_width_2", -1);
    if (i1 > 0) {
      this.h.getLayoutParams().width = i1;
    }
    this.k = findViewById(2131558675);
    this.k.setVisibility(0);
    this.k.setOnClickListener(new cw(this));
    this.j = findViewById(2131558919);
    this.j.setOnClickListener(new cx(this));
  }
  
  private void c(int paramInt)
  {
    I();
    if ((paramInt == 680) || (this.e == null))
    {
      j();
      return;
    }
    this.e.init();
  }
  
  private void d()
  {
    clearSearchBoxFocus();
    hideDialer();
  }
  
  private void e()
  {
    LogicManager.getInstance().getContactsLogic().setAlgoliaListener(this);
    x();
    hideDialer();
    f();
    this.c.setVisibility(8);
    setTabsVisibility(false);
    this.l.setMode(SearchResultsView.MODE_CONTACTS);
    this.q = 200;
  }
  
  private void f()
  {
    this.k.setVisibility(8);
    this.j.setVisibility(0);
  }
  
  private void g()
  {
    this.j.setVisibility(8);
    this.k.setVisibility(0);
  }
  
  private void h()
  {
    if (m())
    {
      startActivity(getPackageManager().getLaunchIntentForPackage("com.simpler.contacts"));
      AnalyticsUtils.switchToSimplerContacts();
      return;
    }
    Intent localIntent = new Intent(this, DownloadSimplerContactsActivity.class);
    localIntent.putExtra("app_promote_type", DownloadSimplerContactsActivity.AppDownloadType.CONTACTS);
    startActivity(localIntent);
  }
  
  private void i()
  {
    SettingsLogic.getInstance().showColorsDialog(this, new ce(this));
  }
  
  private void j()
  {
    runOnUiThread(new cf(this, this));
  }
  
  private void k()
  {
    try
    {
      Intent localIntent = new Intent("android.intent.action.INSERT", ContactsContract.Contacts.CONTENT_URI);
      localIntent.putExtra("finishActivityOnSaveCompleted", true);
      startActivity(localIntent);
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      localActivityNotFoundException.printStackTrace();
      AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
      localBuilder.setTitle(getString(2131165380));
      localBuilder.setMessage(getString(2131165737));
      localBuilder.setPositiveButton(2131165517, null);
      localBuilder.show();
    }
  }
  
  private void l()
  {
    startActivityForResult(new Intent(this, SettingsActivity.class), 679);
  }
  
  private boolean m()
  {
    Iterator localIterator = getPackageManager().getInstalledApplications(0).iterator();
    while (localIterator.hasNext()) {
      if (((ApplicationInfo)localIterator.next()).packageName.equals("com.simpler.contacts")) {
        return true;
      }
    }
    return false;
  }
  
  private void n()
  {
    this.e.setOnDialpadListener(this);
    this.e.setState(2);
  }
  
  private void o()
  {
    if (Build.VERSION.SDK_INT < 21)
    {
      localObject = (RelativeLayout.LayoutParams)this.c.getLayoutParams();
      ((RelativeLayout.LayoutParams)localObject).setMargins(0, 0, UiUtils.dpToPx(8), 0);
      this.c.setLayoutParams((ViewGroup.LayoutParams)localObject);
    }
    Object localObject = ColorStateList.valueOf(SettingsLogic.getPrimaryColor());
    this.c.setBackgroundTintList((ColorStateList)localObject);
    this.c.setRippleColor(ThemeUtils.getFabRippleColor(ThemeUtils.getTabStripColor()));
    y();
    r();
    this.c.setOnClickListener(new cg(this));
  }
  
  private void p()
  {
    this.g.setAdapter(this.b);
    this.g.setOffscreenPageLimit(this.b.getCount() - 1);
    this.g.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(this.a));
    this.g.addOnPageChangeListener(new ch(this));
  }
  
  private void q()
  {
    this.a.setBackgroundColor(SettingsLogic.getPrimaryColor());
    Drawable localDrawable1 = getResources().getDrawable(2130837811);
    if (localDrawable1 != null) {
      localDrawable1.setColorFilter(-1, PorterDuff.Mode.SRC_IN);
    }
    Drawable localDrawable2 = getResources().getDrawable(2130837690);
    if (localDrawable2 != null) {
      localDrawable2.setColorFilter(-1, PorterDuff.Mode.SRC_IN);
    }
    this.a.addTab(this.a.newTab().setIcon(localDrawable1));
    this.a.addTab(this.a.newTab().setIcon(localDrawable2));
    this.a.setOnTabSelectedListener(new ci(this));
  }
  
  private void r()
  {
    this.o = FilesUtils.getIntFromPreferences("dialpad_button_anim_offset_2", 0);
    int i1 = FilesUtils.getIntFromPreferences("dialpad_button_layout_height", 0);
    if ((this.o < 0) && (i1 > 0))
    {
      this.e.setButtonLayoutHeight(i1);
      return;
    }
    this.c.getViewTreeObserver().addOnPreDrawListener(new cj(this));
  }
  
  private void s()
  {
    switch (this.e.getState())
    {
    default: 
      return;
    case 0: 
      t();
      return;
    case 1: 
      a(true);
      z();
      this.e.setState(0);
      return;
    }
    showDialer(true);
  }
  
  private void t()
  {
    w();
    if (this.e.isDigitsEmpty())
    {
      String str = u();
      if (str != null) {
        this.e.setPhoneNumber(str);
      }
      return;
    }
    dialPhoneNumber(this.e.getPhoneNumber());
    AnalyticsUtils.dial(AnalyticsUtils.AnalyticsDialType.DIALPAD_DIGITS);
    this.p = 301;
  }
  
  private String u()
  {
    if (!PermissionUtils.hasPhonePermissions(this)) {
      return null;
    }
    Cursor localCursor = getContentResolver().query(CallLog.Calls.CONTENT_URI, new String[] { "number", "type" }, "type = ?", new String[] { String.valueOf(2) }, "date DESC");
    if (localCursor == null) {
      return null;
    }
    int i1 = localCursor.getColumnIndex("number");
    if (localCursor.getCount() > 0)
    {
      localCursor.moveToNext();
      String str = localCursor.getString(i1);
      localCursor.close();
      return str;
    }
    localCursor.close();
    return null;
  }
  
  private void v()
  {
    this.e.postDelayed(new ck(this), 2500L);
  }
  
  private void w()
  {
    if (this.u) {
      ((Vibrator)getSystemService("vibrator")).vibrate(16);
    }
  }
  
  private void x()
  {
    this.g.animate().alpha(0.0F).setDuration('').setListener(new cl(this));
  }
  
  private void y()
  {
    this.c.setImageResource(2130837736);
  }
  
  private void z()
  {
    this.c.setImageResource(2130837723);
  }
  
  public void clearSearchBoxFocus()
  {
    this.i.clearFocus();
  }
  
  public void dialPhoneNumber(String paramString)
  {
    Intent localIntent = new Intent("android.intent.action.CALL");
    localIntent.setData(Uri.parse("tel:" + Uri.encode(paramString)));
    if (localIntent.resolveActivity(getPackageManager()) != null)
    {
      startActivity(localIntent);
      LogicManager.getInstance().getRateLogic().increaseUserActions();
      v();
    }
  }
  
  public void hideDialer()
  {
    if (!this.t)
    {
      onDialpadQueryTextChange("");
      if (((this.e.getState() == 0) || (this.e.getState() == 1)) && (this.p > -1)) {
        AnalyticsUtils.dialpadEvent(this.p);
      }
      if (this.e.getState() == 0) {
        a(false);
      }
      a(0.0F, true);
      this.e.setState(2);
      setTabsVisibility(true);
      this.g.setVisibility(0);
      g();
      this.e.clearDigitsEditText();
    }
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    switch (paramInt1)
    {
    }
    long l1;
    do
    {
      do
      {
        return;
        if (paramInt2 == -1) {
          H();
        }
        c(paramInt2);
        return;
      } while (paramInt2 != -1);
      l1 = paramIntent.getLongExtra("selected_contact_arg", -1L);
    } while (l1 <= 0L);
    LogicManager.getInstance().getContactsLogic().startUpdateContactFavoriteRunnable(this, l1, true);
  }
  
  public void onAddPhoneNumberClick(String paramString)
  {
    String str1 = getString(2131165250);
    String str2 = getString(2131165330);
    String str3 = getString(2131165252);
    paramString = new cq(this, paramString);
    paramString = DialogUtils.createTraditionalListDialog(this, str1, new String[] { str2, str3 }, paramString);
    paramString.setCanceledOnTouchOutside(true);
    paramString.show();
    AnalyticsUtils.dialpadEvent(304);
    this.p = -1;
  }
  
  public void onAlgoliaSearchResult(Index<AlgoContact> paramIndex, SearchResult<AlgoContact> paramSearchResult, SearchQuery paramSearchQuery)
  {
    if (200 == this.q) {
      this.q = 201;
    }
    paramSearchQuery = paramSearchQuery.getQueryString();
    if (!paramSearchQuery.isEmpty())
    {
      this.l.setItems(paramIndex, paramSearchResult, paramSearchQuery);
      this.l.setVisibility(0);
      return;
    }
    this.l.setVisibility(8);
  }
  
  public void onBackPressed()
  {
    if (this.j.getVisibility() == 0)
    {
      d();
      return;
    }
    super.onBackPressed();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903075);
    setSupportActionBar((Toolbar)findViewById(2131558541));
    setActivityColors();
    this.g = ((AppSectionsViewPager)findViewById(2131558543));
    this.a = ((TabLayout)findViewById(2131558542));
    this.c = ((FloatingActionButton)findViewById(2131558572));
    this.e = ((DialpadView)findViewById(2131558567));
    this.l = ((SearchResultsView)findViewById(2131558571));
    this.b = new cy(this, getSupportFragmentManager());
    findViewById(2131558540).setBackgroundResource(ThemeUtils.getScreenBackgroundColor());
    c();
    q();
    p();
    o();
    n();
    a();
    paramBundle = getSupportActionBar();
    if (paramBundle != null)
    {
      paramBundle.setDisplayShowTitleEnabled(false);
      paramBundle.setDisplayShowHomeEnabled(false);
    }
    if (PackageLogic.getInstance().isFirstRun(getPackageName()))
    {
      startWelcomeFlow();
      finish();
      return;
    }
    J();
    if (FilesUtils.getBooleanFromPreferences("show_showcase", true)) {
      b();
    }
    this.d = new Handler();
    this.f = new cb(this);
    this.u = LogicManager.getInstance().getSettingsLogic().getVibrateOnKeypress();
    if (MissedCallsNotificationClickReceiver.ACTION_OPEN_CALL_LOG_HISTORY.equals(getIntent().getAction())) {}
    for (int i1 = 1;; i1 = G())
    {
      this.g.setCurrentItem(i1);
      a(i1);
      paramBundle = findViewById(2131558570);
      if (paramBundle != null) {
        paramBundle.setOnClickListener(new cm(this));
      }
      if (PermissionUtils.hasDialerAppPermissions(this)) {
        break;
      }
      paramBundle = new Intent(this, AppPermissionsActivity.class);
      paramBundle.putExtra("ARG_LAUNCH_MODE", 1);
      startActivity(paramBundle);
      finish();
      return;
    }
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(2131623942, paramMenu);
    ConfigurationLogic localConfigurationLogic = ConfigurationLogic.getInstance();
    if ((!localConfigurationLogic.isUpgradeButtonVisible()) || (localConfigurationLogic.isDiffValueValid()))
    {
      paramMenu.findItem(2131559005).setVisible(false);
      paramMenu.findItem(2131559005).setEnabled(false);
    }
    if (FilesUtils.getIntFromPreferences("search_bar_width_2", -1) < 0) {
      this.h.getViewTreeObserver().addOnGlobalLayoutListener(new cd(this));
    }
    return true;
  }
  
  public void onDialpadQueryTextChange(String paramString)
  {
    if (this._index != null)
    {
      paramString = new SearchQuery(paramString);
      paramString.setMaxHitsToRetrieve(50);
      ArrayList localArrayList = new ArrayList();
      localArrayList.add("search_tag_all_contacts");
      paramString.addORTagsFilter(localArrayList);
      this._index.asyncSearch(paramString);
    }
  }
  
  public void onDismissDialerClick()
  {
    hideDialer();
  }
  
  public void onFragmentEditModeCreated()
  {
    if (this.e.getState() != 2) {
      hideDialer();
    }
    setTabsVisibility(false);
    B();
  }
  
  public void onFragmentEditModeDestroyed()
  {
    setTabsVisibility(true);
    a(0.0F);
  }
  
  public void onFragmentScrollDragging()
  {
    if (this.e.getState() != 2) {
      hideDialer();
    }
    B();
    F();
  }
  
  public void onFragmentScrollIdle()
  {
    A();
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    default: 
      return super.onOptionsItemSelected(paramMenuItem);
    case 2131559004: 
      l();
      return true;
    case 2131559003: 
      h();
      return true;
    case 2131559016: 
      k();
      return true;
    case 2131559017: 
      i();
      return true;
    }
    paramMenuItem = new Intent(this, UpgradeLogic.getInstance().getUpgradeActivityClass());
    paramMenuItem.putExtra("go_pro_action", "Overflow menu click");
    startActivity(paramMenuItem);
    overridePendingTransition(2130968591, 2130968604);
    return true;
  }
  
  protected void onResume()
  {
    super.onResume();
    if (FilesUtils.getBooleanFromPreferences("recreate_option_menu", false))
    {
      invalidateOptionsMenu();
      FilesUtils.saveToPreferences("recreate_option_menu", false);
    }
    if (FilesUtils.getBooleanFromPreferences("reopen_theme_dialog", false))
    {
      i();
      FilesUtils.saveToPreferences("reopen_theme_dialog", false);
    }
  }
  
  public void onSearchBoxLostFocus()
  {
    g();
    this.i.getText().clear();
    F();
    new Handler().postDelayed(new cc(this), 200L);
    setTabsVisibility(true);
    AnalyticsUtils.searchEvent(this.q);
  }
  
  public void onSearchResultsScrollDragging()
  {
    if (this.e.getState() == 0)
    {
      this.e.setState(1);
      a(false);
      y();
    }
    F();
  }
  
  public void onSearchResultsScrollIdle() {}
  
  public void onVoiceMailClick()
  {
    int i1 = 0;
    if (D())
    {
      dialPhoneNumber(E().getVoiceMailNumber());
      return;
    }
    if (Settings.System.getInt(getContentResolver(), "airplane_mode_on", 0) != 0) {
      i1 = 1;
    }
    if (i1 != 0)
    {
      UiUtils.makeToast(getString(2131165875));
      return;
    }
    UiUtils.makeToast(getString(2131165876));
  }
  
  public void setTabsVisibility(boolean paramBoolean)
  {
    this.g.setEnabled(paramBoolean);
    if (paramBoolean) {}
    for (float f1 = 0.0F;; f1 = -this.a.getHeight())
    {
      int i1 = SimplerApplication.getContext().getResources().getInteger(17694720);
      ObjectAnimator localObjectAnimator = ObjectAnimator.ofFloat(this.a, View.TRANSLATION_Y, new float[] { f1 });
      localObjectAnimator.addListener(new cp(this, paramBoolean));
      localObjectAnimator.setDuration(i1);
      localObjectAnimator.start();
      return;
    }
  }
  
  public void showDialer(boolean paramBoolean)
  {
    this.l.setMode(SearchResultsView.MODE_DIALER);
    if (!this.t)
    {
      if (!paramBoolean) {
        break label75;
      }
      a(this.o, false);
      setTabsVisibility(false);
      a(true);
    }
    for (;;)
    {
      this.e.setState(0);
      x();
      f();
      LogicManager.getInstance().getContactsLogic().setAlgoliaListener(this);
      this.p = 300;
      return;
      label75:
      z();
      if (this.c.getX() == 0.0F) {
        this.c.setX(this.o);
      }
      setTabsVisibility(false);
      this.e.setVisibility(0);
    }
  }
}
