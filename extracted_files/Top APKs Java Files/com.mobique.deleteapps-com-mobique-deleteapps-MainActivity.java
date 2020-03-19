package com.mobique.deleteapps;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageButton;
import android.widget.TextView;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.MaterialDialog.Builder;
import com.afollestad.materialdialogs.MaterialDialog.SingleButtonCallback;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.mobique.adapter.AppInfo;
import com.mobique.adapter.DeleteAdapter;
import com.mobique.config.Delete;
import com.mobique.config.Uninstall;
import com.mobique.helper.NotificationHelper;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class MainActivity
  extends AppCompatActivity
  implements NavigationView.OnNavigationItemSelectedListener, SearchView.OnQueryTextListener
{
  public static ArrayList<String> allPackagesSelected = new ArrayList();
  public static int appCounter = 0;
  public static Context context;
  public static String packageDeleted = "";
  static TextView unInstall;
  int appTotal = 0;
  AdView bannerAdView;
  Boolean bool = Boolean.valueOf(false);
  CheckBox checkbox;
  private final int iSPermissionAccepted = 1960;
  InterstitialAd mInterstitialAd;
  private RecyclerView.LayoutManager mLayoutManager;
  private RecyclerView mRecyclerView;
  Uninstall prefManager;
  ArrayList<AppInfo> res = new ArrayList();
  ImageButton sort;
  int sortCounter = 0;
  
  public MainActivity() {}
  
  private boolean isSystemPackage(PackageInfo paramPackageInfo)
  {
    return false;
  }
  
  private void requestNewInterstitial()
  {
    AdRequest localAdRequest = new AdRequest.Builder().addTestDevice("B3EEABB8EE11C2BE770B684D95219ECB").build();
    this.mInterstitialAd.loadAd(localAdRequest);
  }
  
  public static void setTextForUninstallerCounterApp(Context paramContext, int paramInt)
  {
    if (paramInt > 0)
    {
      TextView localTextView = unInstall;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramContext.getString(2131558484));
      localStringBuilder.append("(");
      localStringBuilder.append(paramInt);
      localStringBuilder.append(")");
      localTextView.setText(localStringBuilder.toString());
      unInstall.setTypeface(null, 1);
      return;
    }
    unInstall.setText(paramContext.getString(2131558484));
    unInstall.setTypeface(null, 0);
  }
  
  private void showInterstitial()
  {
    if (this.mInterstitialAd.isLoaded()) {
      this.mInterstitialAd.show();
    }
  }
  
  void loadApp()
  {
    this.res.clear();
    List localList = getPackageManager().getInstalledPackages(0);
    int i = 0;
    while (i < localList.size())
    {
      PackageInfo localPackageInfo = (PackageInfo)localList.get(i);
      long l1;
      if ((!isSystemPackage(localPackageInfo)) && (!localPackageInfo.packageName.equals(getPackageName())) && (!localPackageInfo.packageName.startsWith("com.android")))
      {
        if (this.bool.booleanValue())
        {
          allPackagesSelected.add(localPackageInfo.packageName);
          appCounter += 1;
        }
        else
        {
          allPackagesSelected.clear();
          appCounter = 0;
        }
        l1 = Long.parseLong("0");
      }
      try
      {
        long l2 = new File(localPackageInfo.applicationInfo.sourceDir).length();
        localObject = Long.valueOf(l2);
      }
      catch (Exception localException)
      {
        Object localObject;
        for (;;) {}
      }
      localObject = Long.valueOf(l1);
      localObject = new AppInfo(localPackageInfo.applicationInfo.loadLabel(getPackageManager()).toString(), localPackageInfo.packageName, localPackageInfo.versionName, localPackageInfo.versionCode, localPackageInfo.applicationInfo.loadIcon(getPackageManager()), (Long)localObject, Long.valueOf(localPackageInfo.firstInstallTime), Boolean.valueOf(false));
      this.res.add(localObject);
      this.appTotal += 1;
      i += 1;
    }
    if (this.bool.booleanValue()) {
      setTextForUninstallerCounterApp(this, appCounter);
    } else {
      setTextForUninstallerCounterApp(this, appCounter);
    }
    Collections.sort(this.res, new Comparator()
    {
      public int compare(AppInfo paramAnonymousAppInfo1, AppInfo paramAnonymousAppInfo2)
      {
        return paramAnonymousAppInfo2.getDateInstalled().compareTo(paramAnonymousAppInfo1.getDateInstalled());
      }
    });
    localObject = new DeleteAdapter(this, this.res, this.bool.booleanValue());
    this.mRecyclerView.setAdapter((RecyclerView.Adapter)localObject);
    ((RecyclerView.Adapter)localObject).notifyDataSetChanged();
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt1 == 1)
    {
      if (paramInt2 == -1)
      {
        paramIntent = new Intent(this, UninstallActivity.class);
        paramIntent.putExtra("state", 2);
        startActivity(paramIntent);
        return;
      }
      if (paramInt2 == 0)
      {
        new Intent(this, UninstallActivity.class).putExtra("state", 2);
        return;
      }
      if (paramInt2 == 1) {
        new Intent(this, UninstallActivity.class).putExtra("state", 2);
      }
    }
  }
  
  public void onBackPressed()
  {
    DrawerLayout localDrawerLayout = (DrawerLayout)findViewById(2131230784);
    if (localDrawerLayout.isDrawerOpen(8388611))
    {
      localDrawerLayout.closeDrawer(8388611);
      return;
    }
    super.onBackPressed();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2131361820);
    Object localObject = (Toolbar)findViewById(2131230934);
    MobileAds.initialize(this, "ca-app-pub-2960717864767095~2726910369");
    context = getApplicationContext();
    setSupportActionBar((Toolbar)localObject);
    this.prefManager = new Uninstall(this);
    int i = this.prefManager.getUsageCount();
    this.prefManager.setUsageCount(i + 1);
    if ((this.prefManager.getShowRateUs()) && (this.prefManager.getUsageCount() >= 2))
    {
      if (Build.VERSION.SDK_INT >= 24) {
        paramBundle = Html.fromHtml(getString(2131558473, new Object[] { getString(2131558431) }), 0);
      } else {
        paramBundle = Html.fromHtml(getString(2131558473, new Object[] { getString(2131558431) }));
      }
      new MaterialDialog.Builder(this).iconRes(2131492864).limitIconToDefaultSize().title(2131558472).content(paramBundle).positiveText(2131558471).negativeText(2131558445).onPositive(new MaterialDialog.SingleButtonCallback()
      {
        public void onClick(@NonNull MaterialDialog paramAnonymousMaterialDialog, @NonNull DialogAction paramAnonymousDialogAction)
        {
          Delete.rateUs(MainActivity.this, Boolean.valueOf(paramAnonymousMaterialDialog.isPromptCheckBoxChecked()));
        }
      }).onNegative(new MaterialDialog.SingleButtonCallback()
      {
        public void onClick(@NonNull MaterialDialog paramAnonymousMaterialDialog, @NonNull DialogAction paramAnonymousDialogAction)
        {
          Delete.cancelRate(MainActivity.this, Boolean.valueOf(paramAnonymousMaterialDialog.isPromptCheckBoxChecked()));
        }
      }).checkBoxPromptRes(2131558438, false, null).show();
    }
    NotificationHelper.show(this);
    unInstall = (TextView)findViewById(2131230944);
    this.checkbox = ((CheckBox)findViewById(2131230764));
    this.sort = ((ImageButton)findViewById(2131230910));
    this.sort.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), 2131165325));
    unInstall.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (MainActivity.allPackagesSelected.size() > 0)
        {
          paramAnonymousView = MainActivity.allPackagesSelected.iterator();
          while (paramAnonymousView.hasNext())
          {
            String str = (String)paramAnonymousView.next();
            Intent localIntent = new Intent("android.intent.action.DELETE");
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append("package:");
            localStringBuilder.append(str);
            localIntent.setData(Uri.parse(localStringBuilder.toString()));
            localIntent.putExtra("android.intent.extra.RETURN_RESULT", true);
            MainActivity.this.startActivityForResult(localIntent, 1);
          }
          if (MainActivity.this.mInterstitialAd.isLoaded()) {
            MainActivity.this.mInterstitialAd.show();
          }
        }
        else
        {
          paramAnonymousView = new Intent(MainActivity.this, UninstallActivity.class);
          paramAnonymousView.putExtra("state", 1);
          MainActivity.this.startActivity(paramAnonymousView);
        }
      }
    });
    this.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
    {
      public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
      {
        if (paramAnonymousCompoundButton.isChecked())
        {
          MainActivity.this.bool = Boolean.valueOf(true);
          MainActivity.this.loadApp();
          return;
        }
        MainActivity.this.bool = Boolean.valueOf(false);
        MainActivity.this.loadApp();
      }
    });
    this.sort.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (MainActivity.this.sortCounter == 0)
        {
          paramAnonymousView = MainActivity.this;
          paramAnonymousView.sortCounter += 1;
          Collections.sort(MainActivity.this.res, new Comparator()
          {
            public int compare(AppInfo paramAnonymous2AppInfo1, AppInfo paramAnonymous2AppInfo2)
            {
              return paramAnonymous2AppInfo2.getSize().compareTo(paramAnonymous2AppInfo1.getSize());
            }
          });
          MainActivity.this.sort.setImageDrawable(null);
          MainActivity.this.sort.setImageDrawable(ContextCompat.getDrawable(MainActivity.this.getApplicationContext(), 2131165327));
        }
        else if (MainActivity.this.sortCounter == 1)
        {
          paramAnonymousView = MainActivity.this;
          paramAnonymousView.sortCounter += 1;
          Collections.sort(MainActivity.this.res, new Comparator()
          {
            public int compare(AppInfo paramAnonymous2AppInfo1, AppInfo paramAnonymous2AppInfo2)
            {
              return paramAnonymous2AppInfo1.getAppName().compareTo(paramAnonymous2AppInfo2.getAppName());
            }
          });
          MainActivity.this.sort.setImageDrawable(null);
          MainActivity.this.sort.setImageDrawable(ContextCompat.getDrawable(MainActivity.this.getApplicationContext(), 2131165326));
        }
        else if (MainActivity.this.sortCounter == 2)
        {
          MainActivity.this.sortCounter = 0;
          Collections.sort(MainActivity.this.res, new Comparator()
          {
            public int compare(AppInfo paramAnonymous2AppInfo1, AppInfo paramAnonymous2AppInfo2)
            {
              return paramAnonymous2AppInfo2.getDateInstalled().compareTo(paramAnonymous2AppInfo1.getDateInstalled());
            }
          });
          MainActivity.this.sort.setImageDrawable(null);
          MainActivity.this.sort.setImageDrawable(ContextCompat.getDrawable(MainActivity.this.getApplicationContext(), 2131165325));
        }
        paramAnonymousView = new DeleteAdapter(MainActivity.this, MainActivity.this.res, MainActivity.this.bool.booleanValue());
        MainActivity.this.mRecyclerView.setAdapter(paramAnonymousView);
        paramAnonymousView.notifyDataSetChanged();
      }
    });
    this.bannerAdView = ((AdView)findViewById(2131230748));
    paramBundle = new AdRequest.Builder().build();
    this.bannerAdView.loadAd(paramBundle);
    this.mInterstitialAd = new InterstitialAd(this);
    this.mInterstitialAd.setAdUnitId("ca-app-pub-2960717864767095/7104686763");
    this.mInterstitialAd.setAdListener(new AdListener()
    {
      public void onAdClosed()
      {
        MainActivity.this.requestNewInterstitial();
      }
    });
    requestNewInterstitial();
    this.mRecyclerView = ((RecyclerView)findViewById(2131230858));
    this.mRecyclerView.setHasFixedSize(true);
    this.mLayoutManager = new LinearLayoutManager(this);
    this.mRecyclerView.setLayoutManager(this.mLayoutManager);
    paramBundle = (DrawerLayout)findViewById(2131230784);
    localObject = new ActionBarDrawerToggle(this, paramBundle, (Toolbar)localObject, 2131558462, 2131558461);
    paramBundle.addDrawerListener((DrawerLayout.DrawerListener)localObject);
    ((ActionBarDrawerToggle)localObject).syncState();
    ((NavigationView)findViewById(2131230859)).setNavigationItemSelectedListener(this);
    loadApp();
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(2131427329, paramMenu);
    ((SearchView)paramMenu.findItem(2131230744).getActionView()).setOnQueryTextListener(this);
    return true;
  }
  
  public boolean onNavigationItemSelected(MenuItem paramMenuItem)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokeinterface 588 1 0
    //   6: istore_2
    //   7: iload_2
    //   8: ldc_w 589
    //   11: if_icmpne +10 -> 21
    //   14: aload_0
    //   15: invokevirtual 549	com/mobique/deleteapps/MainActivity:loadApp	()V
    //   18: goto +427 -> 445
    //   21: iload_2
    //   22: ldc_w 590
    //   25: if_icmpne +101 -> 126
    //   28: new 130	java/lang/StringBuilder
    //   31: dup
    //   32: invokespecial 131	java/lang/StringBuilder:<init>	()V
    //   35: astore_1
    //   36: aload_1
    //   37: ldc_w 592
    //   40: invokevirtual 142	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   43: pop
    //   44: aload_1
    //   45: aload_0
    //   46: invokevirtual 207	com/mobique/deleteapps/MainActivity:getPackageName	()Ljava/lang/String;
    //   49: invokevirtual 142	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   52: pop
    //   53: aload_0
    //   54: new 311	android/content/Intent
    //   57: dup
    //   58: ldc_w 594
    //   61: aload_1
    //   62: invokevirtual 153	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   65: invokestatic 600	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   68: invokespecial 603	android/content/Intent:<init>	(Ljava/lang/String;Landroid/net/Uri;)V
    //   71: invokevirtual 326	com/mobique/deleteapps/MainActivity:startActivity	(Landroid/content/Intent;)V
    //   74: goto +371 -> 445
    //   77: new 130	java/lang/StringBuilder
    //   80: dup
    //   81: invokespecial 131	java/lang/StringBuilder:<init>	()V
    //   84: astore_1
    //   85: aload_1
    //   86: ldc_w 605
    //   89: invokevirtual 142	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   92: pop
    //   93: aload_1
    //   94: aload_0
    //   95: invokevirtual 207	com/mobique/deleteapps/MainActivity:getPackageName	()Ljava/lang/String;
    //   98: invokevirtual 142	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   101: pop
    //   102: aload_0
    //   103: new 311	android/content/Intent
    //   106: dup
    //   107: ldc_w 594
    //   110: aload_1
    //   111: invokevirtual 153	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   114: invokestatic 600	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   117: invokespecial 603	android/content/Intent:<init>	(Ljava/lang/String;Landroid/net/Uri;)V
    //   120: invokevirtual 326	com/mobique/deleteapps/MainActivity:startActivity	(Landroid/content/Intent;)V
    //   123: goto +322 -> 445
    //   126: iload_2
    //   127: ldc_w 606
    //   130: if_icmpne +87 -> 217
    //   133: new 311	android/content/Intent
    //   136: dup
    //   137: ldc_w 608
    //   140: invokespecial 609	android/content/Intent:<init>	(Ljava/lang/String;)V
    //   143: astore_1
    //   144: aload_1
    //   145: ldc_w 611
    //   148: invokevirtual 615	android/content/Intent:setType	(Ljava/lang/String;)Landroid/content/Intent;
    //   151: pop
    //   152: new 130	java/lang/StringBuilder
    //   155: dup
    //   156: invokespecial 131	java/lang/StringBuilder:<init>	()V
    //   159: astore_3
    //   160: aload_3
    //   161: aload_0
    //   162: ldc_w 616
    //   165: invokevirtual 400	com/mobique/deleteapps/MainActivity:getString	(I)Ljava/lang/String;
    //   168: invokevirtual 142	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   171: pop
    //   172: aload_3
    //   173: ldc_w 618
    //   176: invokevirtual 142	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   179: pop
    //   180: aload_3
    //   181: aload_0
    //   182: invokevirtual 207	com/mobique/deleteapps/MainActivity:getPackageName	()Ljava/lang/String;
    //   185: invokevirtual 142	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   188: pop
    //   189: aload_3
    //   190: ldc_w 620
    //   193: invokevirtual 142	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   196: pop
    //   197: aload_1
    //   198: ldc_w 622
    //   201: aload_3
    //   202: invokevirtual 153	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   205: invokevirtual 625	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   208: pop
    //   209: aload_0
    //   210: aload_1
    //   211: invokevirtual 326	com/mobique/deleteapps/MainActivity:startActivity	(Landroid/content/Intent;)V
    //   214: goto +231 -> 445
    //   217: iload_2
    //   218: ldc_w 626
    //   221: if_icmpne +113 -> 334
    //   224: new 130	java/lang/StringBuilder
    //   227: dup
    //   228: invokespecial 131	java/lang/StringBuilder:<init>	()V
    //   231: astore_1
    //   232: aload_1
    //   233: ldc_w 628
    //   236: invokevirtual 142	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   239: pop
    //   240: aload_1
    //   241: aload_0
    //   242: invokevirtual 632	com/mobique/deleteapps/MainActivity:getResources	()Landroid/content/res/Resources;
    //   245: ldc_w 633
    //   248: invokevirtual 636	android/content/res/Resources:getString	(I)Ljava/lang/String;
    //   251: invokevirtual 142	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   254: pop
    //   255: aload_0
    //   256: new 311	android/content/Intent
    //   259: dup
    //   260: ldc_w 594
    //   263: aload_1
    //   264: invokevirtual 153	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   267: invokestatic 600	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   270: invokespecial 603	android/content/Intent:<init>	(Ljava/lang/String;Landroid/net/Uri;)V
    //   273: invokevirtual 326	com/mobique/deleteapps/MainActivity:startActivity	(Landroid/content/Intent;)V
    //   276: goto +169 -> 445
    //   279: new 130	java/lang/StringBuilder
    //   282: dup
    //   283: invokespecial 131	java/lang/StringBuilder:<init>	()V
    //   286: astore_1
    //   287: aload_1
    //   288: ldc_w 638
    //   291: invokevirtual 142	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   294: pop
    //   295: aload_1
    //   296: aload_0
    //   297: invokevirtual 632	com/mobique/deleteapps/MainActivity:getResources	()Landroid/content/res/Resources;
    //   300: ldc_w 633
    //   303: invokevirtual 636	android/content/res/Resources:getString	(I)Ljava/lang/String;
    //   306: invokevirtual 142	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   309: pop
    //   310: aload_0
    //   311: new 311	android/content/Intent
    //   314: dup
    //   315: ldc_w 594
    //   318: aload_1
    //   319: invokevirtual 153	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   322: invokestatic 600	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   325: invokespecial 603	android/content/Intent:<init>	(Ljava/lang/String;Landroid/net/Uri;)V
    //   328: invokevirtual 326	com/mobique/deleteapps/MainActivity:startActivity	(Landroid/content/Intent;)V
    //   331: goto +114 -> 445
    //   334: iload_2
    //   335: ldc_w 639
    //   338: if_icmpne +14 -> 352
    //   341: aload_0
    //   342: invokevirtual 642	com/mobique/deleteapps/MainActivity:finish	()V
    //   345: iconst_0
    //   346: invokestatic 647	java/lang/System:exit	(I)V
    //   349: goto +96 -> 445
    //   352: iload_2
    //   353: ldc_w 648
    //   356: if_icmpne +62 -> 418
    //   359: new 311	android/content/Intent
    //   362: dup
    //   363: ldc_w 650
    //   366: invokespecial 609	android/content/Intent:<init>	(Ljava/lang/String;)V
    //   369: astore_1
    //   370: new 130	java/lang/StringBuilder
    //   373: dup
    //   374: invokespecial 131	java/lang/StringBuilder:<init>	()V
    //   377: astore_3
    //   378: aload_3
    //   379: ldc_w 652
    //   382: invokevirtual 142	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   385: pop
    //   386: aload_3
    //   387: aload_0
    //   388: invokevirtual 207	com/mobique/deleteapps/MainActivity:getPackageName	()Ljava/lang/String;
    //   391: invokestatic 656	android/net/Uri:encode	(Ljava/lang/String;)Ljava/lang/String;
    //   394: invokevirtual 142	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   397: pop
    //   398: aload_1
    //   399: aload_3
    //   400: invokevirtual 153	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   403: invokestatic 600	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   406: invokevirtual 660	android/content/Intent:setData	(Landroid/net/Uri;)Landroid/content/Intent;
    //   409: pop
    //   410: aload_0
    //   411: aload_1
    //   412: invokevirtual 326	com/mobique/deleteapps/MainActivity:startActivity	(Landroid/content/Intent;)V
    //   415: goto +30 -> 445
    //   418: iload_2
    //   419: ldc_w 661
    //   422: if_icmpne +23 -> 445
    //   425: aload_0
    //   426: new 311	android/content/Intent
    //   429: dup
    //   430: ldc_w 594
    //   433: ldc_w 663
    //   436: invokestatic 600	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   439: invokespecial 603	android/content/Intent:<init>	(Ljava/lang/String;Landroid/net/Uri;)V
    //   442: invokevirtual 326	com/mobique/deleteapps/MainActivity:startActivity	(Landroid/content/Intent;)V
    //   445: aload_0
    //   446: ldc_w 328
    //   449: invokevirtual 332	com/mobique/deleteapps/MainActivity:findViewById	(I)Landroid/view/View;
    //   452: checkcast 334	android/support/v4/widget/DrawerLayout
    //   455: ldc_w 335
    //   458: invokevirtual 343	android/support/v4/widget/DrawerLayout:closeDrawer	(I)V
    //   461: iconst_1
    //   462: ireturn
    //   463: astore_1
    //   464: goto -387 -> 77
    //   467: astore_1
    //   468: goto -189 -> 279
    //   471: astore_1
    //   472: goto -27 -> 445
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	475	0	this	MainActivity
    //   0	475	1	paramMenuItem	MenuItem
    //   6	417	2	i	int
    //   159	241	3	localStringBuilder	StringBuilder
    // Exception table:
    //   from	to	target	type
    //   28	74	463	android/content/ActivityNotFoundException
    //   224	276	467	android/content/ActivityNotFoundException
    //   359	415	471	java/lang/Exception
    //   425	445	471	java/lang/Exception
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (paramMenuItem.getOrder() == 0) {}
    try
    {
      startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.mobique.boostphone")));
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      for (;;) {}
    }
    startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.mobique.boostphone")));
    return super.onOptionsItemSelected(paramMenuItem);
  }
  
  protected void onPause()
  {
    super.onPause();
  }
  
  public boolean onQueryTextChange(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (i < this.res.size())
    {
      if (((AppInfo)this.res.get(i)).getAppName().toLowerCase().contains(paramString.toLowerCase())) {
        localArrayList.add(this.res.get(i));
      }
      i += 1;
    }
    paramString = new DeleteAdapter(this, localArrayList, this.bool.booleanValue());
    this.mRecyclerView.setAdapter(paramString);
    paramString.notifyDataSetChanged();
    return false;
  }
  
  public boolean onQueryTextSubmit(String paramString)
  {
    Log.e("onQueryTextSubmit", paramString);
    return false;
  }
  
  public void onResume()
  {
    super.onResume();
    this.bool = Boolean.valueOf(false);
    loadApp();
  }
}
