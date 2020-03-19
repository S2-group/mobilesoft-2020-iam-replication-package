package net.geekstools.floatshort.PRO.Shortcut;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions.Builder;
import com.google.android.gms.common.AccountPicker;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuth.AuthStateListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigInfo;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings.Builder;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.google.firebase.storage.UploadTask.TaskSnapshot;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;
import net.geekstools.floatshort.PRO.BindServices;
import net.geekstools.floatshort.PRO.Category.CategoryHandler;
import net.geekstools.floatshort.PRO.LicenseValidator;
import net.geekstools.floatshort.PRO.Shortcut.nav.CardHybridAdapter;
import net.geekstools.floatshort.PRO.Shortcut.nav.SectionedGridRecyclerViewAdapter;
import net.geekstools.floatshort.PRO.Shortcut.nav.SectionedGridRecyclerViewAdapter.Section;
import net.geekstools.floatshort.PRO.Util.Free.InAppBilling;
import net.geekstools.floatshort.PRO.Util.Functions.FunctionsClass;
import net.geekstools.floatshort.PRO.Util.Functions.PublicVariable;
import net.geekstools.floatshort.PRO.Util.NavAdapter.NavDrawerItem;
import net.geekstools.floatshort.PRO.Util.NavAdapter.RecycleViewSmoothLayout;
import net.geekstools.floatshort.PRO.Util.UI.SimpleGestureFilterSwitch;
import net.geekstools.floatshort.PRO.Util.UI.SimpleGestureFilterSwitch.SimpleGestureListener;
import net.geekstools.imageview.customshapes.ShapesImage;

public class HybridViewOff
  extends Activity
  implements View.OnClickListener, View.OnLongClickListener, View.OnTouchListener, SimpleGestureFilterSwitch.SimpleGestureListener
{
  Drawable AppIcon;
  String AppName = "Application";
  RelativeLayout MainView;
  String PackageName;
  ListView actionElementsList;
  Activity activity;
  List<ApplicationInfo> applicationInfoList;
  int[] counter;
  private FirebaseAuth firebaseAuth;
  FirebaseRemoteConfig firebaseRemoteConfig;
  String[] freqApps;
  LinearLayout freqView;
  HorizontalScrollView freqlist;
  RelativeLayout fullActionButton;
  FunctionsClass functionsClass;
  int hybridItem = 0;
  NavigableMap<String, Integer> indexItems;
  List<String> indexList;
  LinearLayout indexView;
  Intent intent;
  int lastIntentItem = 0;
  int limitedCountLine;
  boolean loadFreq = false;
  ImageView loadLogo;
  RecyclerView loadView;
  int loadViewPosition = 0;
  ProgressBar loadingBarLTR;
  RelativeLayout loadingSplash;
  Map<String, Integer> mapIndex;
  ArrayList<NavDrawerItem> navDrawerItems;
  ScrollView nestedScrollView;
  RecyclerView.Adapter recyclerViewAdapter;
  GridLayoutManager recyclerViewLayoutManager;
  List<SectionedGridRecyclerViewAdapter.Section> sections;
  SimpleGestureFilterSwitch simpleGestureFilterSwitch;
  
  public HybridViewOff() {}
  
  private void LoadFrequentlyApps()
  {
    try
    {
      this.freqView.removeAllViews();
      this.counter = new int[25];
      this.freqApps = this.intent.getStringArrayExtra("freq");
      int j = this.intent.getIntExtra("num", -1);
      if (getFileStreamPath("Frequently").exists())
      {
        this.functionsClass.removeLine(".categoryInfo", "Frequently");
        deleteFile("Frequently");
      }
      this.functionsClass.saveFileAppendLine(".categoryInfo", "Frequently");
      this.freqlist = ((HorizontalScrollView)findViewById(2131296418));
      Object localObject1 = (LayerDrawable)getResources().getDrawable(2131230954);
      ((GradientDrawable)((LayerDrawable)localObject1).findDrawableByLayerId(2131296312)).setTint(this.functionsClass.setColorAlpha(PublicVariable.primaryColor, 155.0F));
      this.freqlist.setBackground((Drawable)localObject1);
      this.freqlist.setVisibility(0);
      localObject1 = (RelativeLayout)getLayoutInflater().inflate(2131427391, null);
      int i = 0;
      Object localObject2;
      while (i < j)
      {
        localObject1 = (RelativeLayout)getLayoutInflater().inflate(2131427391, null);
        localObject2 = this.functionsClass.initShapesImage((ViewGroup)localObject1, 2131296417);
        ((ShapesImage)localObject2).setId(i);
        ((ShapesImage)localObject2).setOnClickListener(this);
        ((ShapesImage)localObject2).setOnLongClickListener(this);
        ((ShapesImage)localObject2).setImageDrawable(this.functionsClass.shapedAppIcon(this.freqApps[i]));
        this.freqView.addView((View)localObject1);
        this.functionsClass.saveFileAppendLine("Frequently", this.freqApps[i]);
        localObject1 = this.functionsClass;
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append(this.freqApps[i]);
        ((StringBuilder)localObject2).append("Frequently");
        ((FunctionsClass)localObject1).saveFile(((StringBuilder)localObject2).toString(), this.freqApps[i]);
        i += 1;
      }
      this.functionsClass.addAppShortcuts();
      if (this.functionsClass.appThemeTransparent())
      {
        localObject1 = new RelativeLayout.LayoutParams(-1, this.functionsClass.DpToInteger(50));
        ((RelativeLayout.LayoutParams)localObject1).topMargin = 0;
        ((RelativeLayout.LayoutParams)localObject1).addRule(3, 2131296617);
        this.freqlist.setLayoutParams((ViewGroup.LayoutParams)localObject1);
        localObject1 = (Button)findViewById(2131296618);
        localObject2 = (Button)findViewById(2131296616);
        RippleDrawable localRippleDrawable = (RippleDrawable)getResources().getDrawable(2131230901);
        Object localObject3 = (GradientDrawable)localRippleDrawable.findDrawableByLayerId(2131296413);
        GradientDrawable localGradientDrawable1 = (GradientDrawable)localRippleDrawable.findDrawableByLayerId(2131296311);
        GradientDrawable localGradientDrawable2 = (GradientDrawable)localRippleDrawable.findDrawableByLayerId(16908334);
        if (this.functionsClass.appThemeTransparent())
        {
          localRippleDrawable.setColor(ColorStateList.valueOf(PublicVariable.primaryColorOpposite));
          ((GradientDrawable)localObject3).setColor(this.functionsClass.setColorAlpha(PublicVariable.primaryColor, 255.0F));
          if (this.functionsClass.returnAPI() > 21) {
            localGradientDrawable1.setTint(this.functionsClass.setColorAlpha(PublicVariable.primaryColor, 155.0F));
          } else {
            localGradientDrawable1.setColor(this.functionsClass.setColorAlpha(PublicVariable.primaryColor, 155.0F));
          }
          localGradientDrawable2.setColor(PublicVariable.primaryColorOpposite);
        }
        else
        {
          localRippleDrawable.setColor(ColorStateList.valueOf(PublicVariable.primaryColorOpposite));
          ((GradientDrawable)localObject3).setColor(PublicVariable.primaryColor);
          localGradientDrawable1.setTint(PublicVariable.primaryColor);
          localGradientDrawable2.setColor(PublicVariable.primaryColorOpposite);
        }
        localObject3 = (RippleDrawable)getResources().getDrawable(2131230879);
        localGradientDrawable2 = (GradientDrawable)((RippleDrawable)localObject3).findDrawableByLayerId(2131296413);
        GradientDrawable localGradientDrawable3 = (GradientDrawable)((RippleDrawable)localObject3).findDrawableByLayerId(2131296311);
        GradientDrawable localGradientDrawable4 = (GradientDrawable)((RippleDrawable)localObject3).findDrawableByLayerId(16908334);
        if (this.functionsClass.appThemeTransparent())
        {
          ((RippleDrawable)localObject3).setColor(ColorStateList.valueOf(PublicVariable.primaryColor));
          localGradientDrawable2.setColor(this.functionsClass.setColorAlpha(PublicVariable.primaryColorOpposite, 255.0F));
          if (this.functionsClass.returnAPI() > 21) {
            localGradientDrawable3.setTint(this.functionsClass.setColorAlpha(PublicVariable.primaryColorOpposite, 175.0F));
          } else {
            localGradientDrawable1.setColor(this.functionsClass.setColorAlpha(PublicVariable.primaryColorOpposite, 175.0F));
          }
          localGradientDrawable4.setColor(PublicVariable.primaryColor);
        }
        else
        {
          ((RippleDrawable)localObject3).setColor(ColorStateList.valueOf(PublicVariable.primaryColor));
          localGradientDrawable2.setColor(PublicVariable.primaryColorOpposite);
          localGradientDrawable3.setTint(PublicVariable.primaryColorOpposite);
          localGradientDrawable4.setColor(PublicVariable.primaryColor);
        }
        ((Button)localObject1).setBackground(localRippleDrawable);
        ((Button)localObject2).setBackground((Drawable)localObject3);
        return;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
  {
    this.simpleGestureFilterSwitch.onTouchEvent(paramMotionEvent);
    return super.dispatchTouchEvent(paramMotionEvent);
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt1 == 666)
    {
      paramIntent = GoogleSignIn.getSignedInAccountFromIntent(paramIntent);
      try
      {
        paramIntent = GoogleAuthProvider.getCredential(((GoogleSignInAccount)paramIntent.getResult(ApiException.class)).getIdToken(), null);
        this.firebaseAuth.signInWithCredential(paramIntent).addOnCompleteListener(this, new OnCompleteListener()
        {
          public void onComplete(@NonNull Task<AuthResult> paramAnonymousTask)
          {
            if (paramAnonymousTask.isSuccessful())
            {
              paramAnonymousTask = HybridViewOff.this.firebaseAuth.getCurrentUser();
              if (paramAnonymousTask != null)
              {
                HybridViewOff.this.functionsClass.savePreference(".BETA", "testerEmail", paramAnonymousTask.getEmail());
                new Handler().postDelayed(new Runnable()
                {
                  public void run()
                  {
                    try
                    {
                      Object localObject = new StringBuilder();
                      ((StringBuilder)localObject).append("/data/data/");
                      ((StringBuilder)localObject).append(HybridViewOff.this.getPackageName());
                      ((StringBuilder)localObject).append("/shared_prefs/.BETA.xml");
                      localObject = Uri.fromFile(new File(((StringBuilder)localObject).toString()));
                      FirebaseStorage localFirebaseStorage = FirebaseStorage.getInstance();
                      StringBuilder localStringBuilder = new StringBuilder();
                      localStringBuilder.append("/betaTesters/API");
                      localStringBuilder.append(HybridViewOff.this.functionsClass.returnAPI());
                      localStringBuilder.append("/");
                      localStringBuilder.append(HybridViewOff.this.functionsClass.readPreference(".BETA", "testerEmail", null));
                      localFirebaseStorage.getReference(localStringBuilder.toString()).putFile((Uri)localObject).addOnFailureListener(new OnFailureListener()
                      {
                        public void onFailure(@NonNull Exception paramAnonymous3Exception)
                        {
                          paramAnonymous3Exception.printStackTrace();
                        }
                      }).addOnSuccessListener(new OnSuccessListener()
                      {
                        public void onSuccess(UploadTask.TaskSnapshot paramAnonymous3TaskSnapshot)
                        {
                          System.out.println("Firebase Activities Done Successfully");
                          HybridViewOff.this.functionsClass.Toast(HybridViewOff.this.getString(2131623988), 48);
                        }
                      });
                      return;
                    }
                    catch (Exception localException)
                    {
                      localException.printStackTrace();
                    }
                  }
                }, 333L);
              }
            }
          }
        });
        return;
      }
      catch (ApiException paramIntent)
      {
        paramIntent.printStackTrace();
        return;
      }
    }
    if (paramInt1 == 777) {
      try
      {
        paramIntent = paramIntent.getStringExtra("authAccount");
        startActivity(new Intent(getApplicationContext(), InAppBilling.class).putExtra("UserEmailAddress", paramIntent));
        return;
      }
      catch (Exception paramIntent)
      {
        paramIntent.printStackTrace();
      }
    }
  }
  
  public void onBackPressed()
  {
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.addCategory("android.intent.category.HOME");
    localIntent.setFlags(268435456);
    startActivity(localIntent);
    this.activity.overridePendingTransition(17432576, 17432577);
  }
  
  public void onClick(View paramView)
  {
    if ((paramView instanceof ImageView))
    {
      int i = ((ImageView)paramView).getId();
      this.functionsClass.runUnlimitedShortcutsService(this.freqApps[i]);
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2131427450);
    this.nestedScrollView = ((ScrollView)findViewById(2131296495));
    this.loadView = ((RecyclerView)findViewById(2131296475));
    this.indexView = ((LinearLayout)findViewById(2131296583));
    this.freqView = ((LinearLayout)findViewById(2131296416));
    this.MainView = ((RelativeLayout)findViewById(2131296260));
    this.fullActionButton = ((RelativeLayout)findViewById(2131296420));
    this.loadingSplash = ((RelativeLayout)findViewById(2131296482));
    this.actionElementsList = ((ListView)findViewById(2131296286));
    this.simpleGestureFilterSwitch = new SimpleGestureFilterSwitch(getApplicationContext(), this);
    this.functionsClass = new FunctionsClass(getApplicationContext(), this, getClass().getSimpleName());
    this.functionsClass.ChangeLog(this, false);
    this.activity = this;
    this.recyclerViewLayoutManager = new RecycleViewSmoothLayout(getApplicationContext(), this.functionsClass.columnCount(105), 1, false);
    this.loadView.setLayoutManager(this.recyclerViewLayoutManager);
    this.indexList = new ArrayList();
    this.sections = new ArrayList();
    this.indexItems = new TreeMap();
    if (this.functionsClass.appThemeTransparent() == true) {
      this.functionsClass.setThemeColor(this.MainView, true, getString(2131624066), "");
    } else {
      this.functionsClass.setThemeColor(this.MainView, false, getString(2131624066), "");
    }
    this.applicationInfoList = new ArrayList();
    this.navDrawerItems = new ArrayList();
    this.mapIndex = new LinkedHashMap();
    this.intent = getIntent();
    new LoadApplicationsOffLimited(null).execute(new Void[0]);
    paramBundle = (Button)findViewById(2131296618);
    Button localButton = (Button)findViewById(2131296616);
    paramBundle.setTextColor(getResources().getColor(2131099732));
    localButton.setTextColor(getResources().getColor(2131099732));
    if ((PublicVariable.themeLightDark) && (this.functionsClass.appThemeTransparent()))
    {
      paramBundle.setTextColor(getResources().getColor(2131099703));
      localButton.setTextColor(getResources().getColor(2131099703));
    }
    RippleDrawable localRippleDrawable = (RippleDrawable)getResources().getDrawable(2131230900);
    Object localObject = (GradientDrawable)localRippleDrawable.findDrawableByLayerId(2131296413);
    GradientDrawable localGradientDrawable1 = (GradientDrawable)localRippleDrawable.findDrawableByLayerId(2131296311);
    GradientDrawable localGradientDrawable2 = (GradientDrawable)localRippleDrawable.findDrawableByLayerId(16908334);
    if (this.functionsClass.appThemeTransparent())
    {
      localRippleDrawable.setColor(ColorStateList.valueOf(PublicVariable.primaryColorOpposite));
      ((GradientDrawable)localObject).setColor(this.functionsClass.setColorAlpha(PublicVariable.primaryColor, 255.0F));
      if (this.functionsClass.returnAPI() > 21) {
        localGradientDrawable1.setTint(this.functionsClass.setColorAlpha(PublicVariable.primaryColor, 155.0F));
      } else {
        localGradientDrawable1.setColor(this.functionsClass.setColorAlpha(PublicVariable.primaryColor, 155.0F));
      }
      localGradientDrawable2.setColor(PublicVariable.primaryColorOpposite);
    }
    else
    {
      localRippleDrawable.setColor(ColorStateList.valueOf(PublicVariable.primaryColorOpposite));
      ((GradientDrawable)localObject).setColor(PublicVariable.primaryColor);
      localGradientDrawable1.setTint(PublicVariable.primaryColor);
      localGradientDrawable2.setColor(PublicVariable.primaryColorOpposite);
    }
    localObject = (RippleDrawable)getResources().getDrawable(2131230878);
    localGradientDrawable2 = (GradientDrawable)((RippleDrawable)localObject).findDrawableByLayerId(2131296413);
    GradientDrawable localGradientDrawable3 = (GradientDrawable)((RippleDrawable)localObject).findDrawableByLayerId(2131296311);
    GradientDrawable localGradientDrawable4 = (GradientDrawable)((RippleDrawable)localObject).findDrawableByLayerId(16908334);
    if (this.functionsClass.appThemeTransparent())
    {
      ((RippleDrawable)localObject).setColor(ColorStateList.valueOf(PublicVariable.primaryColor));
      localGradientDrawable2.setColor(this.functionsClass.setColorAlpha(PublicVariable.primaryColorOpposite, 255.0F));
      if (this.functionsClass.returnAPI() > 21) {
        localGradientDrawable3.setTint(this.functionsClass.setColorAlpha(PublicVariable.primaryColorOpposite, 175.0F));
      } else {
        localGradientDrawable1.setColor(this.functionsClass.setColorAlpha(PublicVariable.primaryColorOpposite, 175.0F));
      }
      localGradientDrawable4.setColor(PublicVariable.primaryColor);
    }
    else
    {
      ((RippleDrawable)localObject).setColor(ColorStateList.valueOf(PublicVariable.primaryColor));
      localGradientDrawable2.setColor(PublicVariable.primaryColorOpposite);
      localGradientDrawable3.setTint(PublicVariable.primaryColorOpposite);
      localGradientDrawable4.setColor(PublicVariable.primaryColor);
    }
    paramBundle.setBackground(localRippleDrawable);
    localButton.setBackground((Drawable)localObject);
    localButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        try
        {
          HybridViewOff.this.functionsClass.overrideBackPressToClass(CategoryHandler.class, ActivityOptions.makeCustomAnimation(HybridViewOff.this.getApplicationContext(), 2130771989, 2130771990));
          return;
        }
        catch (Exception paramAnonymousView)
        {
          paramAnonymousView.printStackTrace();
        }
      }
    });
    this.firebaseAuth = FirebaseAuth.getInstance();
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(2131492864, paramMenu);
    MenuItem localMenuItem = paramMenu.findItem(2131296411);
    paramMenu = paramMenu.findItem(2131296535);
    LayerDrawable localLayerDrawable1 = (LayerDrawable)getResources().getDrawable(2131230884);
    LayerDrawable localLayerDrawable2 = (LayerDrawable)getResources().getDrawable(2131230892);
    GradientDrawable localGradientDrawable1 = (GradientDrawable)localLayerDrawable1.findDrawableByLayerId(2131296312);
    GradientDrawable localGradientDrawable2 = (GradientDrawable)localLayerDrawable2.findDrawableByLayerId(2131296312);
    localGradientDrawable1.setColor(this.functionsClass.optionMenuColor());
    localGradientDrawable2.setColor(this.functionsClass.optionMenuColor());
    localMenuItem.setIcon(localLayerDrawable1);
    paramMenu.setIcon(localLayerDrawable2);
    return true;
  }
  
  public void onDestroy()
  {
    super.onDestroy();
    PublicVariable.inMemory = false;
    if ((this.functionsClass.SystemCache()) || (this.functionsClass.automationFeatureEnable())) {
      startService(new Intent(getApplicationContext(), BindServices.class));
    }
    try
    {
      FirebaseAuth.getInstance().getCurrentUser().reload();
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public boolean onLongClick(View paramView)
  {
    if ((paramView instanceof ImageView))
    {
      int i = ((ImageView)paramView).getId();
      this.functionsClass.openApplication(this.freqApps[i]);
    }
    return true;
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    int i = paramMenuItem.getItemId();
    if (i != 16908332)
    {
      if (i != 2131296411)
      {
        if (i == 2131296535)
        {
          if (this.fullActionButton.isShown()) {
            PublicVariable.actionCenter = false;
          }
          if (!PublicVariable.recoveryCenter) {
            this.functionsClass.recoveryOption(this.fullActionButton, this.actionElementsList, this.fullActionButton.isShown());
          } else {
            this.functionsClass.closeRecoveryMenuOption(this.fullActionButton, this.actionElementsList);
          }
        }
      }
      else
      {
        if (this.fullActionButton.isShown()) {
          PublicVariable.recoveryCenter = false;
        }
        if (!PublicVariable.actionCenter) {
          this.functionsClass.menuOption(this.fullActionButton, this.actionElementsList, this.fullActionButton.isShown());
        } else {
          this.functionsClass.closeMenuOption(this.fullActionButton, this.actionElementsList);
        }
      }
    }
    else if (this.firebaseRemoteConfig.getLong(this.functionsClass.versionCodeRemoteConfigKey()) > this.functionsClass.appVersionCode(getPackageName())) {
      this.functionsClass.upcomingChangeLog(this, this.firebaseRemoteConfig.getString(this.functionsClass.upcomingChangeLogRemoteConfigKey()), String.valueOf(this.firebaseRemoteConfig.getLong(this.functionsClass.versionCodeRemoteConfigKey())));
    } else {
      startActivityForResult(AccountPicker.newChooseAccountIntent(null, null, new String[] { "com.google" }, true, null, null, null, null), 777);
    }
    return super.onOptionsItemSelected(paramMenuItem);
  }
  
  public void onPause()
  {
    super.onPause();
    this.functionsClass.addAppShortcuts();
    this.functionsClass.savePreference("LoadView", "LoadViewPosition", this.recyclerViewLayoutManager.findFirstVisibleItemPosition());
    if (PublicVariable.actionCenter == true) {
      this.functionsClass.closeMenuOption(this.fullActionButton, this.actionElementsList);
    }
    if (PublicVariable.recoveryCenter == true) {
      this.functionsClass.closeRecoveryMenuOption(this.fullActionButton, this.actionElementsList);
    }
    this.functionsClass.savePreference("OpenMode", "openClassName", getClass().getSimpleName());
  }
  
  public boolean onPrepareOptionsMenu(Menu paramMenu)
  {
    return super.onPrepareOptionsMenu(paramMenu);
  }
  
  public void onResume()
  {
    super.onResume();
    PublicVariable.inMemory = true;
    this.firebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
    FirebaseRemoteConfigSettings localFirebaseRemoteConfigSettings = new FirebaseRemoteConfigSettings.Builder().setDeveloperModeEnabled(false).build();
    this.firebaseRemoteConfig.setConfigSettings(localFirebaseRemoteConfigSettings);
    this.firebaseRemoteConfig.setDefaults(2131820545);
    long l;
    if (this.firebaseRemoteConfig.getInfo().getConfigSettings().isDeveloperModeEnabled()) {
      l = 0L;
    } else {
      l = 780L;
    }
    this.firebaseRemoteConfig.fetch(l).addOnCompleteListener(this, new OnCompleteListener()
    {
      public void onComplete(@NonNull Task<Void> paramAnonymousTask)
      {
        if (paramAnonymousTask.isSuccessful())
        {
          HybridViewOff.this.firebaseRemoteConfig.activateFetched();
          if (HybridViewOff.this.firebaseRemoteConfig.getLong(HybridViewOff.this.functionsClass.versionCodeRemoteConfigKey()) > HybridViewOff.this.functionsClass.appVersionCode(HybridViewOff.this.getPackageName()))
          {
            HybridViewOff.this.getActionBar().setDisplayHomeAsUpEnabled(true);
            paramAnonymousTask = (LayerDrawable)HybridViewOff.this.getDrawable(2131230951);
            ((BitmapDrawable)paramAnonymousTask.findDrawableByLayerId(2131296443)).setTint(PublicVariable.primaryColor);
            paramAnonymousTask = HybridViewOff.this.functionsClass.drawableToBitmap(paramAnonymousTask);
            paramAnonymousTask = Bitmap.createScaledBitmap(paramAnonymousTask, paramAnonymousTask.getWidth() / 4, paramAnonymousTask.getHeight() / 4, false);
            paramAnonymousTask = new BitmapDrawable(HybridViewOff.this.getResources(), paramAnonymousTask);
            HybridViewOff.this.activity.getActionBar().setHomeAsUpIndicator(paramAnonymousTask);
            HybridViewOff.this.functionsClass.notificationCreator(HybridViewOff.this.getString(2131624217), HybridViewOff.this.firebaseRemoteConfig.getString(HybridViewOff.this.functionsClass.upcomingChangeLogSummaryConfigKey()), (int)HybridViewOff.this.firebaseRemoteConfig.getLong(HybridViewOff.this.functionsClass.versionCodeRemoteConfigKey()));
          }
        }
      }
    });
  }
  
  public void onStart()
  {
    super.onStart();
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction(getString(2131624092));
    BroadcastReceiver local2 = new BroadcastReceiver()
    {
      public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
      {
        if (paramAnonymousIntent.getAction().equals(HybridViewOff.this.getString(2131624092)))
        {
          HybridViewOff.this.functionsClass.dialogueLicense(HybridViewOff.this);
          new Handler().postDelayed(new Runnable()
          {
            public void run()
            {
              HybridViewOff.this.stopService(new Intent(HybridViewOff.this.getApplicationContext(), LicenseValidator.class));
            }
          }, 1000L);
        }
      }
    };
    registerReceiver(local2, localIntentFilter);
    try
    {
      if ((!getFileStreamPath(".License").exists()) && (this.functionsClass.networkConnection() == true)) {
        startService(new Intent(getApplicationContext(), LicenseValidator.class));
      }
      this.loadView.addOnScrollListener(new RecyclerView.OnScrollListener()
      {
        public void onScrollStateChanged(RecyclerView paramAnonymousRecyclerView, int paramAnonymousInt)
        {
          super.onScrollStateChanged(paramAnonymousRecyclerView, paramAnonymousInt);
        }
        
        public void onScrolled(RecyclerView paramAnonymousRecyclerView, int paramAnonymousInt1, int paramAnonymousInt2)
        {
          super.onScrolled(paramAnonymousRecyclerView, paramAnonymousInt1, paramAnonymousInt2);
          HybridViewOff.this.loadViewPosition = HybridViewOff.this.recyclerViewLayoutManager.findFirstVisibleItemPosition();
        }
      });
    }
    catch (Exception localException1)
    {
      try
      {
        unregisterReceiver(local2);
      }
      catch (Exception localException3)
      {
        for (;;) {}
      }
      localException1 = localException1;
      localException1.printStackTrace();
    }
    if (this.functionsClass.SystemCache()) {
      startService(new Intent(getApplicationContext(), BindServices.class));
    }
    try
    {
      if (this.functionsClass.networkConnection())
      {
        this.firebaseAuth.addAuthStateListener(new FirebaseAuth.AuthStateListener()
        {
          public void onAuthStateChanged(@NonNull FirebaseAuth paramAnonymousFirebaseAuth)
          {
            if (paramAnonymousFirebaseAuth.getCurrentUser() == null) {
              HybridViewOff.this.functionsClass.savePreference(".BETA", "testerEmail", null);
            }
          }
        });
        if ((this.functionsClass.readPreference(".BETA", "isBetaTester", false)) && (this.functionsClass.readPreference(".BETA", "testerEmail", null) == null))
        {
          GoogleSignInClient localGoogleSignInClient = GoogleSignIn.getClient(this, new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(2131624228)).requestEmail().build());
          try
          {
            localGoogleSignInClient.signOut();
            localGoogleSignInClient.revokeAccess();
          }
          catch (Exception localException4)
          {
            localException4.printStackTrace();
          }
          startActivityForResult(localGoogleSignInClient.getSignInIntent(), 666);
          return;
        }
      }
    }
    catch (Exception localException2)
    {
      localException2.printStackTrace();
    }
  }
  
  public void onStop()
  {
    super.onStop();
  }
  
  public void onSwipe(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      
    case 3: 
      try
      {
        this.functionsClass.overrideBackPressToClass(CategoryHandler.class, ActivityOptions.makeCustomAnimation(getApplicationContext(), 2130771989, 2130771990));
        return;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
  }
  
  public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    if ((paramView instanceof TextView))
    {
      paramView = (TextView)paramView;
      this.nestedScrollView.smoothScrollTo(0, (int)this.loadView.getChildAt(((Integer)this.mapIndex.get(paramView.getText().toString())).intValue()).getY());
    }
    return true;
  }
  
  private class LoadApplicationsIndex
    extends AsyncTask<Void, Void, Void>
  {
    private LoadApplicationsIndex() {}
    
    protected Void doInBackground(Void... paramVarArgs)
    {
      int j = HybridViewOff.this.indexList.size();
      int i = 0;
      while (i < j)
      {
        try
        {
          paramVarArgs = (String)HybridViewOff.this.indexList.get(i);
          if (HybridViewOff.this.mapIndex.get(paramVarArgs) == null) {
            HybridViewOff.this.mapIndex.put(paramVarArgs, Integer.valueOf(i));
          }
        }
        catch (Exception paramVarArgs)
        {
          paramVarArgs.printStackTrace();
        }
        i += 1;
      }
      return null;
    }
    
    protected void onPostExecute(Void paramVoid)
    {
      super.onPostExecute(paramVoid);
      paramVoid = (LayerDrawable)HybridViewOff.this.getResources().getDrawable(2131230887);
      ((GradientDrawable)paramVoid.findDrawableByLayerId(2131296312)).setColor(0);
      Iterator localIterator = new ArrayList(HybridViewOff.this.mapIndex.keySet()).iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        TextView localTextView = (TextView)HybridViewOff.this.getLayoutInflater().inflate(2131427467, null);
        localTextView.setBackground(paramVoid);
        localTextView.setText(str.toUpperCase());
        localTextView.setTextColor(PublicVariable.colorLightDarkOpposite);
        localTextView.setOnTouchListener(HybridViewOff.this);
        HybridViewOff.this.indexView.addView(localTextView);
      }
    }
    
    protected void onPreExecute()
    {
      super.onPreExecute();
      HybridViewOff.this.indexView.removeAllViews();
    }
  }
  
  private class LoadApplicationsOff
    extends AsyncTask<Void, Void, Void>
  {
    private LoadApplicationsOff() {}
    
    protected Void doInBackground(Void... paramVarArgs)
    {
      for (;;)
      {
        int m;
        int j;
        try
        {
          m = HybridViewOff.this.limitedCountLine;
          i = 1;
          if (m < HybridViewOff.this.applicationInfoList.size())
          {
            paramVarArgs = HybridViewOff.this.getPackageManager().getLaunchIntentForPackage(((ApplicationInfo)HybridViewOff.this.applicationInfoList.get(m)).packageName);
            j = i;
            if (paramVarArgs == null) {
              break label509;
            }
            int k = i;
            try
            {
              paramVarArgs = HybridViewOff.this.functionsClass.appName(((ApplicationInfo)HybridViewOff.this.applicationInfoList.get(m)).packageName).substring(0, 1).toUpperCase();
              if (m == 0)
              {
                j = i;
              }
              else
              {
                j = i;
                k = i;
                if (!HybridViewOff.this.functionsClass.appName(((ApplicationInfo)HybridViewOff.this.applicationInfoList.get(HybridViewOff.this.lastIntentItem)).packageName).substring(0, 1).toUpperCase().equals(paramVarArgs))
                {
                  k = i;
                  HybridViewOff.this.sections.add(new SectionedGridRecyclerViewAdapter.Section(HybridViewOff.this.hybridItem, paramVarArgs));
                  k = i;
                  HybridViewOff.this.indexList.add(paramVarArgs);
                  j = 1;
                }
              }
              k = j;
              HybridViewOff.this.PackageName = ((ApplicationInfo)HybridViewOff.this.applicationInfoList.get(m)).packageName;
              k = j;
              HybridViewOff.this.AppName = HybridViewOff.this.functionsClass.appName(HybridViewOff.this.PackageName);
              k = j;
              HybridViewOff.this.AppIcon = HybridViewOff.this.functionsClass.shapedAppIcon(HybridViewOff.this.PackageName);
              k = j;
              HybridViewOff.this.navDrawerItems.add(new NavDrawerItem(HybridViewOff.this.AppName, HybridViewOff.this.PackageName, HybridViewOff.this.AppIcon));
              k = j;
              HybridViewOff.this.indexList.add(paramVarArgs);
              k = j;
              NavigableMap localNavigableMap = HybridViewOff.this.indexItems;
              i = j + 1;
              try
              {
                localNavigableMap.put(paramVarArgs, Integer.valueOf(j));
                HybridViewOff.this.hybridItem += 1;
                HybridViewOff.this.lastIntentItem = m;
                j = i;
              }
              catch (Exception paramVarArgs)
              {
                j = i;
              }
              paramVarArgs.printStackTrace();
            }
            catch (Exception paramVarArgs)
            {
              j = k;
            }
            break label509;
          }
          HybridViewOff.this.recyclerViewAdapter = new CardHybridAdapter(HybridViewOff.this.activity, HybridViewOff.this.getApplicationContext(), HybridViewOff.this.navDrawerItems);
          HybridViewOff.this.recyclerViewAdapter.notifyDataSetChanged();
        }
        catch (Exception paramVarArgs)
        {
          paramVarArgs.printStackTrace();
          cancel(true);
          HybridViewOff.this.finish();
        }
        return null;
        label509:
        m += 1;
        int i = j;
      }
    }
    
    protected void onPostExecute(Void paramVoid)
    {
      super.onPostExecute(paramVoid);
      paramVoid = new SectionedGridRecyclerViewAdapter.Section[HybridViewOff.this.sections.size()];
      SectionedGridRecyclerViewAdapter localSectionedGridRecyclerViewAdapter = new SectionedGridRecyclerViewAdapter(HybridViewOff.this.getApplicationContext(), 2131427453, 2131296569, HybridViewOff.this.loadView, HybridViewOff.this.recyclerViewAdapter);
      localSectionedGridRecyclerViewAdapter.setSections((SectionedGridRecyclerViewAdapter.Section[])HybridViewOff.this.sections.toArray(paramVoid));
      HybridViewOff.this.loadView.setAdapter(localSectionedGridRecyclerViewAdapter);
      if (HybridViewOff.this.loadViewPosition == 0) {
        HybridViewOff.this.recyclerViewLayoutManager.scrollToPosition(HybridViewOff.this.getSharedPreferences("LoadView", 0).getInt("LoadViewPosition", 0));
      } else {
        HybridViewOff.this.recyclerViewLayoutManager.scrollToPosition(HybridViewOff.this.loadViewPosition);
      }
      new HybridViewOff.LoadApplicationsIndex(HybridViewOff.this, null).execute(new Void[0]);
      try
      {
        paramVoid = HybridViewOff.this.getIntent();
        if ((paramVoid.hasExtra("goHome")) && (paramVoid.getBooleanExtra("goHome", false)))
        {
          paramVoid = new Intent("android.intent.action.MAIN");
          paramVoid.addCategory("android.intent.category.HOME");
          paramVoid.setFlags(268435456);
          HybridViewOff.this.startActivity(paramVoid);
          return;
        }
      }
      catch (Exception paramVoid)
      {
        paramVoid.printStackTrace();
        HybridViewOff.this.finish();
      }
    }
    
    protected void onPreExecute()
    {
      super.onPreExecute();
    }
  }
  
  private class LoadApplicationsOffLimited
    extends AsyncTask<Void, Void, Void>
  {
    private LoadApplicationsOffLimited() {}
    
    /* Error */
    protected Void doInBackground(Void... paramVarArgs)
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 16	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/HybridViewOff;
      //   4: aload_0
      //   5: getfield 16	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/HybridViewOff;
      //   8: invokevirtual 36	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff:getApplicationContext	()Landroid/content/Context;
      //   11: invokevirtual 42	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
      //   14: iconst_0
      //   15: invokevirtual 48	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
      //   18: putfield 52	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff:applicationInfoList	Ljava/util/List;
      //   21: aload_0
      //   22: getfield 16	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/HybridViewOff;
      //   25: getfield 52	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff:applicationInfoList	Ljava/util/List;
      //   28: new 54	android/content/pm/ApplicationInfo$DisplayNameComparator
      //   31: dup
      //   32: aload_0
      //   33: getfield 16	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/HybridViewOff;
      //   36: invokevirtual 55	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff:getPackageManager	()Landroid/content/pm/PackageManager;
      //   39: invokespecial 58	android/content/pm/ApplicationInfo$DisplayNameComparator:<init>	(Landroid/content/pm/PackageManager;)V
      //   42: invokestatic 64	java/util/Collections:sort	(Ljava/util/List;Ljava/util/Comparator;)V
      //   45: aload_0
      //   46: getfield 16	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/HybridViewOff;
      //   49: aload_0
      //   50: getfield 16	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/HybridViewOff;
      //   53: getfield 52	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff:applicationInfoList	Ljava/util/List;
      //   56: invokeinterface 70 1 0
      //   61: iconst_3
      //   62: idiv
      //   63: putfield 74	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff:limitedCountLine	I
      //   66: iconst_1
      //   67: istore_2
      //   68: iconst_0
      //   69: istore 5
      //   71: iload 5
      //   73: aload_0
      //   74: getfield 16	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/HybridViewOff;
      //   77: getfield 74	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff:limitedCountLine	I
      //   80: if_icmpge +416 -> 496
      //   83: aload_0
      //   84: getfield 16	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/HybridViewOff;
      //   87: invokevirtual 55	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff:getPackageManager	()Landroid/content/pm/PackageManager;
      //   90: aload_0
      //   91: getfield 16	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/HybridViewOff;
      //   94: getfield 52	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff:applicationInfoList	Ljava/util/List;
      //   97: iload 5
      //   99: invokeinterface 78 2 0
      //   104: checkcast 80	android/content/pm/ApplicationInfo
      //   107: getfield 84	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
      //   110: invokevirtual 88	android/content/pm/PackageManager:getLaunchIntentForPackage	(Ljava/lang/String;)Landroid/content/Intent;
      //   113: astore_1
      //   114: iload_2
      //   115: istore_3
      //   116: aload_1
      //   117: ifnull +673 -> 790
      //   120: iload_2
      //   121: istore 4
      //   123: aload_0
      //   124: getfield 16	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/HybridViewOff;
      //   127: getfield 92	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
      //   130: aload_0
      //   131: getfield 16	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/HybridViewOff;
      //   134: getfield 52	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff:applicationInfoList	Ljava/util/List;
      //   137: iload 5
      //   139: invokeinterface 78 2 0
      //   144: checkcast 80	android/content/pm/ApplicationInfo
      //   147: getfield 84	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
      //   150: invokevirtual 98	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:appName	(Ljava/lang/String;)Ljava/lang/String;
      //   153: iconst_0
      //   154: iconst_1
      //   155: invokevirtual 104	java/lang/String:substring	(II)Ljava/lang/String;
      //   158: invokevirtual 108	java/lang/String:toUpperCase	()Ljava/lang/String;
      //   161: astore_1
      //   162: iload 5
      //   164: ifne +8 -> 172
      //   167: iload_2
      //   168: istore_3
      //   169: goto +108 -> 277
      //   172: iload_2
      //   173: istore_3
      //   174: iload_2
      //   175: istore 4
      //   177: aload_0
      //   178: getfield 16	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/HybridViewOff;
      //   181: getfield 92	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
      //   184: aload_0
      //   185: getfield 16	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/HybridViewOff;
      //   188: getfield 52	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff:applicationInfoList	Ljava/util/List;
      //   191: aload_0
      //   192: getfield 16	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/HybridViewOff;
      //   195: getfield 111	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff:lastIntentItem	I
      //   198: invokeinterface 78 2 0
      //   203: checkcast 80	android/content/pm/ApplicationInfo
      //   206: getfield 84	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
      //   209: invokevirtual 98	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:appName	(Ljava/lang/String;)Ljava/lang/String;
      //   212: iconst_0
      //   213: iconst_1
      //   214: invokevirtual 104	java/lang/String:substring	(II)Ljava/lang/String;
      //   217: invokevirtual 108	java/lang/String:toUpperCase	()Ljava/lang/String;
      //   220: aload_1
      //   221: invokevirtual 115	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   224: ifne +53 -> 277
      //   227: iload_2
      //   228: istore 4
      //   230: aload_0
      //   231: getfield 16	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/HybridViewOff;
      //   234: getfield 118	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff:sections	Ljava/util/List;
      //   237: new 120	net/geekstools/floatshort/PRO/Shortcut/nav/SectionedGridRecyclerViewAdapter$Section
      //   240: dup
      //   241: aload_0
      //   242: getfield 16	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/HybridViewOff;
      //   245: getfield 123	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff:hybridItem	I
      //   248: aload_1
      //   249: invokespecial 126	net/geekstools/floatshort/PRO/Shortcut/nav/SectionedGridRecyclerViewAdapter$Section:<init>	(ILjava/lang/CharSequence;)V
      //   252: invokeinterface 129 2 0
      //   257: pop
      //   258: iload_2
      //   259: istore 4
      //   261: aload_0
      //   262: getfield 16	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/HybridViewOff;
      //   265: getfield 132	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff:indexList	Ljava/util/List;
      //   268: aload_1
      //   269: invokeinterface 129 2 0
      //   274: pop
      //   275: iconst_1
      //   276: istore_3
      //   277: iload_3
      //   278: istore 4
      //   280: aload_0
      //   281: getfield 16	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/HybridViewOff;
      //   284: aload_0
      //   285: getfield 16	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/HybridViewOff;
      //   288: getfield 52	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff:applicationInfoList	Ljava/util/List;
      //   291: iload 5
      //   293: invokeinterface 78 2 0
      //   298: checkcast 80	android/content/pm/ApplicationInfo
      //   301: getfield 84	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
      //   304: putfield 135	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff:PackageName	Ljava/lang/String;
      //   307: iload_3
      //   308: istore 4
      //   310: aload_0
      //   311: getfield 16	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/HybridViewOff;
      //   314: aload_0
      //   315: getfield 16	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/HybridViewOff;
      //   318: getfield 92	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
      //   321: aload_0
      //   322: getfield 16	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/HybridViewOff;
      //   325: getfield 135	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff:PackageName	Ljava/lang/String;
      //   328: invokevirtual 98	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:appName	(Ljava/lang/String;)Ljava/lang/String;
      //   331: putfield 138	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff:AppName	Ljava/lang/String;
      //   334: iload_3
      //   335: istore 4
      //   337: aload_0
      //   338: getfield 16	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/HybridViewOff;
      //   341: aload_0
      //   342: getfield 16	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/HybridViewOff;
      //   345: getfield 92	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
      //   348: aload_0
      //   349: getfield 16	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/HybridViewOff;
      //   352: getfield 135	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff:PackageName	Ljava/lang/String;
      //   355: invokevirtual 142	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:shapedAppIcon	(Ljava/lang/String;)Landroid/graphics/drawable/Drawable;
      //   358: putfield 146	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff:AppIcon	Landroid/graphics/drawable/Drawable;
      //   361: iload_3
      //   362: istore 4
      //   364: aload_0
      //   365: getfield 16	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/HybridViewOff;
      //   368: getfield 150	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff:navDrawerItems	Ljava/util/ArrayList;
      //   371: new 152	net/geekstools/floatshort/PRO/Util/NavAdapter/NavDrawerItem
      //   374: dup
      //   375: aload_0
      //   376: getfield 16	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/HybridViewOff;
      //   379: getfield 138	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff:AppName	Ljava/lang/String;
      //   382: aload_0
      //   383: getfield 16	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/HybridViewOff;
      //   386: getfield 135	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff:PackageName	Ljava/lang/String;
      //   389: aload_0
      //   390: getfield 16	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/HybridViewOff;
      //   393: getfield 146	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff:AppIcon	Landroid/graphics/drawable/Drawable;
      //   396: invokespecial 155	net/geekstools/floatshort/PRO/Util/NavAdapter/NavDrawerItem:<init>	(Ljava/lang/String;Ljava/lang/String;Landroid/graphics/drawable/Drawable;)V
      //   399: invokevirtual 158	java/util/ArrayList:add	(Ljava/lang/Object;)Z
      //   402: pop
      //   403: iload_3
      //   404: istore 4
      //   406: aload_0
      //   407: getfield 16	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/HybridViewOff;
      //   410: getfield 132	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff:indexList	Ljava/util/List;
      //   413: aload_1
      //   414: invokeinterface 129 2 0
      //   419: pop
      //   420: iload_3
      //   421: istore 4
      //   423: aload_0
      //   424: getfield 16	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/HybridViewOff;
      //   427: getfield 162	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff:indexItems	Ljava/util/NavigableMap;
      //   430: astore 6
      //   432: iload_3
      //   433: iconst_1
      //   434: iadd
      //   435: istore_2
      //   436: aload 6
      //   438: aload_1
      //   439: iload_3
      //   440: invokestatic 168	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
      //   443: invokeinterface 174 3 0
      //   448: pop
      //   449: aload_0
      //   450: getfield 16	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/HybridViewOff;
      //   453: aload_0
      //   454: getfield 16	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/HybridViewOff;
      //   457: getfield 123	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff:hybridItem	I
      //   460: iconst_1
      //   461: iadd
      //   462: putfield 123	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff:hybridItem	I
      //   465: aload_0
      //   466: getfield 16	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/HybridViewOff;
      //   469: iload 5
      //   471: putfield 111	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff:lastIntentItem	I
      //   474: iload_2
      //   475: istore_3
      //   476: goto +314 -> 790
      //   479: astore_1
      //   480: iload_2
      //   481: istore_3
      //   482: goto +7 -> 489
      //   485: astore_1
      //   486: iload 4
      //   488: istore_3
      //   489: aload_1
      //   490: invokevirtual 177	java/lang/Exception:printStackTrace	()V
      //   493: goto +297 -> 790
      //   496: aload_0
      //   497: getfield 16	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/HybridViewOff;
      //   500: new 179	net/geekstools/floatshort/PRO/Shortcut/nav/CardHybridAdapter
      //   503: dup
      //   504: aload_0
      //   505: getfield 16	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/HybridViewOff;
      //   508: getfield 183	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff:activity	Landroid/app/Activity;
      //   511: aload_0
      //   512: getfield 16	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/HybridViewOff;
      //   515: invokevirtual 36	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff:getApplicationContext	()Landroid/content/Context;
      //   518: aload_0
      //   519: getfield 16	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/HybridViewOff;
      //   522: getfield 150	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff:navDrawerItems	Ljava/util/ArrayList;
      //   525: invokespecial 186	net/geekstools/floatshort/PRO/Shortcut/nav/CardHybridAdapter:<init>	(Landroid/app/Activity;Landroid/content/Context;Ljava/util/ArrayList;)V
      //   528: putfield 190	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff:recyclerViewAdapter	Landroid/support/v7/widget/RecyclerView$Adapter;
      //   531: aload_0
      //   532: getfield 16	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/HybridViewOff;
      //   535: getfield 190	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff:recyclerViewAdapter	Landroid/support/v7/widget/RecyclerView$Adapter;
      //   538: invokevirtual 195	android/support/v7/widget/RecyclerView$Adapter:notifyDataSetChanged	()V
      //   541: aload_0
      //   542: getfield 16	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/HybridViewOff;
      //   545: getfield 199	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff:intent	Landroid/content/Intent;
      //   548: ldc -55
      //   550: invokevirtual 207	android/content/Intent:getStringArrayExtra	(Ljava/lang/String;)[Ljava/lang/String;
      //   553: ifnull +158 -> 711
      //   556: aload_0
      //   557: getfield 16	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/HybridViewOff;
      //   560: aload_0
      //   561: getfield 16	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/HybridViewOff;
      //   564: getfield 199	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff:intent	Landroid/content/Intent;
      //   567: ldc -55
      //   569: invokevirtual 207	android/content/Intent:getStringArrayExtra	(Ljava/lang/String;)[Ljava/lang/String;
      //   572: putfield 211	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff:freqApps	[Ljava/lang/String;
      //   575: aload_0
      //   576: getfield 16	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/HybridViewOff;
      //   579: getfield 199	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff:intent	Landroid/content/Intent;
      //   582: ldc -43
      //   584: iconst_m1
      //   585: invokevirtual 217	android/content/Intent:getIntExtra	(Ljava/lang/String;I)I
      //   588: istore_2
      //   589: aload_0
      //   590: getfield 16	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/HybridViewOff;
      //   593: getfield 211	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff:freqApps	[Ljava/lang/String;
      //   596: putstatic 220	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:freqApps	[Ljava/lang/String;
      //   599: iload_2
      //   600: putstatic 223	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:freqLength	I
      //   603: iload_2
      //   604: iconst_1
      //   605: if_icmple +106 -> 711
      //   608: aload_0
      //   609: getfield 16	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/HybridViewOff;
      //   612: iconst_1
      //   613: putfield 227	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff:loadFreq	Z
      //   616: goto +95 -> 711
      //   619: astore_1
      //   620: goto +93 -> 713
      //   623: astore_1
      //   624: aload_1
      //   625: invokevirtual 177	java/lang/Exception:printStackTrace	()V
      //   628: aload_0
      //   629: iconst_1
      //   630: invokevirtual 231	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff$LoadApplicationsOffLimited:cancel	(Z)Z
      //   633: pop
      //   634: aload_0
      //   635: getfield 16	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/HybridViewOff;
      //   638: invokevirtual 234	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff:finish	()V
      //   641: aload_0
      //   642: getfield 16	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/HybridViewOff;
      //   645: getfield 199	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff:intent	Landroid/content/Intent;
      //   648: ldc -55
      //   650: invokevirtual 207	android/content/Intent:getStringArrayExtra	(Ljava/lang/String;)[Ljava/lang/String;
      //   653: ifnull +58 -> 711
      //   656: aload_0
      //   657: getfield 16	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/HybridViewOff;
      //   660: aload_0
      //   661: getfield 16	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/HybridViewOff;
      //   664: getfield 199	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff:intent	Landroid/content/Intent;
      //   667: ldc -55
      //   669: invokevirtual 207	android/content/Intent:getStringArrayExtra	(Ljava/lang/String;)[Ljava/lang/String;
      //   672: putfield 211	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff:freqApps	[Ljava/lang/String;
      //   675: aload_0
      //   676: getfield 16	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/HybridViewOff;
      //   679: getfield 199	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff:intent	Landroid/content/Intent;
      //   682: ldc -43
      //   684: iconst_m1
      //   685: invokevirtual 217	android/content/Intent:getIntExtra	(Ljava/lang/String;I)I
      //   688: istore_2
      //   689: aload_0
      //   690: getfield 16	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/HybridViewOff;
      //   693: getfield 211	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff:freqApps	[Ljava/lang/String;
      //   696: putstatic 220	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:freqApps	[Ljava/lang/String;
      //   699: iload_2
      //   700: putstatic 223	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:freqLength	I
      //   703: iload_2
      //   704: iconst_1
      //   705: if_icmple +6 -> 711
      //   708: goto -100 -> 608
      //   711: aconst_null
      //   712: areturn
      //   713: aload_0
      //   714: getfield 16	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/HybridViewOff;
      //   717: getfield 199	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff:intent	Landroid/content/Intent;
      //   720: ldc -55
      //   722: invokevirtual 207	android/content/Intent:getStringArrayExtra	(Ljava/lang/String;)[Ljava/lang/String;
      //   725: ifnull +63 -> 788
      //   728: aload_0
      //   729: getfield 16	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/HybridViewOff;
      //   732: aload_0
      //   733: getfield 16	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/HybridViewOff;
      //   736: getfield 199	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff:intent	Landroid/content/Intent;
      //   739: ldc -55
      //   741: invokevirtual 207	android/content/Intent:getStringArrayExtra	(Ljava/lang/String;)[Ljava/lang/String;
      //   744: putfield 211	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff:freqApps	[Ljava/lang/String;
      //   747: aload_0
      //   748: getfield 16	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/HybridViewOff;
      //   751: getfield 199	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff:intent	Landroid/content/Intent;
      //   754: ldc -43
      //   756: iconst_m1
      //   757: invokevirtual 217	android/content/Intent:getIntExtra	(Ljava/lang/String;I)I
      //   760: istore_2
      //   761: aload_0
      //   762: getfield 16	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/HybridViewOff;
      //   765: getfield 211	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff:freqApps	[Ljava/lang/String;
      //   768: putstatic 220	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:freqApps	[Ljava/lang/String;
      //   771: iload_2
      //   772: putstatic 223	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:freqLength	I
      //   775: iload_2
      //   776: iconst_1
      //   777: if_icmple +11 -> 788
      //   780: aload_0
      //   781: getfield 16	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/HybridViewOff;
      //   784: iconst_1
      //   785: putfield 227	net/geekstools/floatshort/PRO/Shortcut/HybridViewOff:loadFreq	Z
      //   788: aload_1
      //   789: athrow
      //   790: iload 5
      //   792: iconst_1
      //   793: iadd
      //   794: istore 5
      //   796: iload_3
      //   797: istore_2
      //   798: goto -727 -> 71
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	801	0	this	LoadApplicationsOffLimited
      //   0	801	1	paramVarArgs	Void[]
      //   67	731	2	i	int
      //   115	682	3	j	int
      //   121	366	4	k	int
      //   69	726	5	m	int
      //   430	7	6	localNavigableMap	NavigableMap
      // Exception table:
      //   from	to	target	type
      //   436	474	479	java/lang/Exception
      //   123	162	485	java/lang/Exception
      //   177	227	485	java/lang/Exception
      //   230	258	485	java/lang/Exception
      //   261	275	485	java/lang/Exception
      //   280	307	485	java/lang/Exception
      //   310	334	485	java/lang/Exception
      //   337	361	485	java/lang/Exception
      //   364	403	485	java/lang/Exception
      //   406	420	485	java/lang/Exception
      //   423	432	485	java/lang/Exception
      //   0	66	619	finally
      //   71	114	619	finally
      //   123	162	619	finally
      //   177	227	619	finally
      //   230	258	619	finally
      //   261	275	619	finally
      //   280	307	619	finally
      //   310	334	619	finally
      //   337	361	619	finally
      //   364	403	619	finally
      //   406	420	619	finally
      //   423	432	619	finally
      //   436	474	619	finally
      //   489	493	619	finally
      //   496	541	619	finally
      //   624	641	619	finally
      //   0	66	623	java/lang/Exception
      //   71	114	623	java/lang/Exception
      //   489	493	623	java/lang/Exception
      //   496	541	623	java/lang/Exception
    }
    
    protected void onPostExecute(Void paramVoid)
    {
      super.onPostExecute(paramVoid);
      if (!HybridViewOff.this.loadFreq)
      {
        HybridViewOff.this.freqlist = ((HorizontalScrollView)HybridViewOff.this.findViewById(2131296418));
        HybridViewOff.this.MainView.removeView(HybridViewOff.this.freqlist);
        paramVoid = new RelativeLayout.LayoutParams(-1, -1);
        paramVoid.addRule(3, 2131296617);
        if (HybridViewOff.this.functionsClass.LoadAds()) {
          paramVoid.addRule(2, 2131296287);
        }
        HybridViewOff.this.nestedScrollView.setLayoutParams(paramVoid);
      }
      paramVoid = new SectionedGridRecyclerViewAdapter.Section[HybridViewOff.this.sections.size()];
      SectionedGridRecyclerViewAdapter localSectionedGridRecyclerViewAdapter = new SectionedGridRecyclerViewAdapter(HybridViewOff.this.getApplicationContext(), 2131427453, 2131296569, HybridViewOff.this.loadView, HybridViewOff.this.recyclerViewAdapter);
      localSectionedGridRecyclerViewAdapter.setSections((SectionedGridRecyclerViewAdapter.Section[])HybridViewOff.this.sections.toArray(paramVoid));
      HybridViewOff.this.loadView.setAdapter(localSectionedGridRecyclerViewAdapter);
      paramVoid = AnimationUtils.loadAnimation(HybridViewOff.this.getApplicationContext(), 17432577);
      HybridViewOff.this.loadingSplash.startAnimation(paramVoid);
      paramVoid.setAnimationListener(new Animation.AnimationListener()
      {
        public void onAnimationEnd(Animation paramAnonymousAnimation)
        {
          HybridViewOff.this.loadingSplash = ((RelativeLayout)HybridViewOff.this.findViewById(2131296482));
          HybridViewOff.this.loadingSplash.setVisibility(4);
        }
        
        public void onAnimationRepeat(Animation paramAnonymousAnimation) {}
        
        public void onAnimationStart(Animation paramAnonymousAnimation)
        {
          if (HybridViewOff.this.loadFreq == true) {
            HybridViewOff.this.LoadFrequentlyApps();
          }
          ((LinearLayout)HybridViewOff.this.findViewById(2131296617)).setVisibility(0);
          new HybridViewOff.LoadApplicationsOff(HybridViewOff.this, null).execute(new Void[0]);
        }
      });
    }
    
    protected void onPreExecute()
    {
      super.onPreExecute();
      HybridViewOff.this.loadingSplash = ((RelativeLayout)HybridViewOff.this.findViewById(2131296482));
      if (HybridViewOff.this.functionsClass.appThemeTransparent() == true) {
        HybridViewOff.this.loadingSplash.setBackgroundColor(0);
      } else {
        HybridViewOff.this.loadingSplash.setBackgroundColor(HybridViewOff.this.getWindow().getNavigationBarColor());
      }
      HybridViewOff.this.loadingBarLTR = ((ProgressBar)HybridViewOff.this.findViewById(2131296480));
      if (PublicVariable.themeLightDark) {
        HybridViewOff.this.loadingBarLTR.getIndeterminateDrawable().setColorFilter(PublicVariable.themeTextColor, PorterDuff.Mode.MULTIPLY);
      } else if (!PublicVariable.themeLightDark) {
        HybridViewOff.this.loadingBarLTR.getIndeterminateDrawable().setColorFilter(PublicVariable.themeColor, PorterDuff.Mode.MULTIPLY);
      }
      HybridViewOff.this.loadLogo = ((ImageView)HybridViewOff.this.findViewById(2131296479));
      LayerDrawable localLayerDrawable = (LayerDrawable)HybridViewOff.this.getDrawable(2131230933);
      ((BitmapDrawable)localLayerDrawable.findDrawableByLayerId(2131296443)).setTint(PublicVariable.primaryColor);
      HybridViewOff.this.loadLogo.setImageDrawable(localLayerDrawable);
      HybridViewOff.this.indexView.removeAllViews();
    }
  }
}
