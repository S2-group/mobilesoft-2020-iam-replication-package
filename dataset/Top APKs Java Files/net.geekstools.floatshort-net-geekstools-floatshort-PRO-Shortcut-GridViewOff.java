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
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
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
import net.geekstools.floatshort.PRO.BindServices;
import net.geekstools.floatshort.PRO.Category.CategoryHandler;
import net.geekstools.floatshort.PRO.LicenseValidator;
import net.geekstools.floatshort.PRO.Shortcut.nav.CardGridAdapter;
import net.geekstools.floatshort.PRO.Util.Free.InAppBilling;
import net.geekstools.floatshort.PRO.Util.Functions.FunctionsClass;
import net.geekstools.floatshort.PRO.Util.Functions.PublicVariable;
import net.geekstools.floatshort.PRO.Util.NavAdapter.NavDrawerItem;
import net.geekstools.floatshort.PRO.Util.UI.SimpleGestureFilterSwitch;
import net.geekstools.floatshort.PRO.Util.UI.SimpleGestureFilterSwitch.SimpleGestureListener;
import net.geekstools.imageview.customshapes.ShapesImage;

public class GridViewOff
  extends Activity
  implements View.OnClickListener, View.OnLongClickListener, View.OnTouchListener, SimpleGestureFilterSwitch.SimpleGestureListener
{
  Drawable AppIcon;
  String AppName = "Application";
  RelativeLayout MainView;
  String PackageName;
  ListView actionElementsList;
  Activity activity;
  CardGridAdapter adapter;
  List<ApplicationInfo> applicationInfoList;
  int[] counter;
  private FirebaseAuth firebaseAuth;
  FirebaseRemoteConfig firebaseRemoteConfig;
  String[] freqApps;
  Drawable[] freqAppsIcons;
  LinearLayout freqView;
  HorizontalScrollView freqlist;
  RelativeLayout fullActionButton;
  FunctionsClass functionsClass;
  LinearLayout indexView;
  Intent intent;
  int limitedCountLine;
  boolean loadFreq = false;
  ImageView loadLogo;
  GridView loadView;
  int loadViewPosition = 0;
  ProgressBar loadingBarLTR;
  RelativeLayout loadingSplash;
  Map<String, Integer> mapIndex;
  ArrayList<NavDrawerItem> navDrawerItems;
  ArrayList<NavDrawerItem> navDrawerItemsInit;
  SimpleGestureFilterSwitch simpleGestureFilterSwitch;
  
  public GridViewOff() {}
  
  private void LoadFrequentlyApps()
  {
    try
    {
      this.freqView.removeAllViews();
      this.freqAppsIcons = new Drawable[25];
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
              paramAnonymousTask = GridViewOff.this.firebaseAuth.getCurrentUser();
              if (paramAnonymousTask != null)
              {
                GridViewOff.this.functionsClass.savePreference(".BETA", "testerEmail", paramAnonymousTask.getEmail());
                new Handler().postDelayed(new Runnable()
                {
                  public void run()
                  {
                    try
                    {
                      Object localObject = new StringBuilder();
                      ((StringBuilder)localObject).append("/data/data/");
                      ((StringBuilder)localObject).append(GridViewOff.this.getPackageName());
                      ((StringBuilder)localObject).append("/shared_prefs/.BETA.xml");
                      localObject = Uri.fromFile(new File(((StringBuilder)localObject).toString()));
                      FirebaseStorage localFirebaseStorage = FirebaseStorage.getInstance();
                      StringBuilder localStringBuilder = new StringBuilder();
                      localStringBuilder.append("/betaTesters/API");
                      localStringBuilder.append(GridViewOff.this.functionsClass.returnAPI());
                      localStringBuilder.append("/");
                      localStringBuilder.append(GridViewOff.this.functionsClass.readPreference(".BETA", "testerEmail", null));
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
                          GridViewOff.this.functionsClass.Toast(GridViewOff.this.getString(2131623988), 48);
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
    setContentView(2131427449);
    this.loadView = ((GridView)findViewById(2131296429));
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
    if (this.functionsClass.appThemeTransparent() == true) {
      this.functionsClass.setThemeColor(this.MainView, true, getString(2131624066), "");
    } else {
      this.functionsClass.setThemeColor(this.MainView, false, getString(2131624066), "");
    }
    this.navDrawerItemsInit = new ArrayList();
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
          GridViewOff.this.functionsClass.overrideBackPressToClass(CategoryHandler.class, ActivityOptions.makeCustomAnimation(GridViewOff.this.getApplicationContext(), 2130771989, 2130771990));
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
    GradientDrawable localGradientDrawable1 = (GradientDrawable)localLayerDrawable1.findDrawableByLayerId(2131296312);
    LayerDrawable localLayerDrawable2 = (LayerDrawable)getResources().getDrawable(2131230892);
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
    this.functionsClass.savePreference("LoadView", "LoadViewPosition", this.loadView.getFirstVisiblePosition());
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
          GridViewOff.this.firebaseRemoteConfig.activateFetched();
          if (GridViewOff.this.firebaseRemoteConfig.getLong(GridViewOff.this.functionsClass.versionCodeRemoteConfigKey()) > GridViewOff.this.functionsClass.appVersionCode(GridViewOff.this.getPackageName()))
          {
            GridViewOff.this.getActionBar().setDisplayHomeAsUpEnabled(true);
            paramAnonymousTask = (LayerDrawable)GridViewOff.this.getDrawable(2131230951);
            ((BitmapDrawable)paramAnonymousTask.findDrawableByLayerId(2131296443)).setTint(PublicVariable.primaryColor);
            paramAnonymousTask = GridViewOff.this.functionsClass.drawableToBitmap(paramAnonymousTask);
            paramAnonymousTask = Bitmap.createScaledBitmap(paramAnonymousTask, paramAnonymousTask.getWidth() / 4, paramAnonymousTask.getHeight() / 4, false);
            paramAnonymousTask = new BitmapDrawable(GridViewOff.this.getResources(), paramAnonymousTask);
            GridViewOff.this.activity.getActionBar().setHomeAsUpIndicator(paramAnonymousTask);
            GridViewOff.this.functionsClass.notificationCreator(GridViewOff.this.getString(2131624217), GridViewOff.this.firebaseRemoteConfig.getString(GridViewOff.this.functionsClass.upcomingChangeLogSummaryConfigKey()), (int)GridViewOff.this.firebaseRemoteConfig.getLong(GridViewOff.this.functionsClass.versionCodeRemoteConfigKey()));
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
        if (paramAnonymousIntent.getAction().equals(GridViewOff.this.getString(2131624092)))
        {
          GridViewOff.this.functionsClass.dialogueLicense(GridViewOff.this);
          new Handler().postDelayed(new Runnable()
          {
            public void run()
            {
              GridViewOff.this.stopService(new Intent(GridViewOff.this.getApplicationContext(), LicenseValidator.class));
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
      this.loadView.setOnScrollListener(new AbsListView.OnScrollListener()
      {
        public void onScroll(AbsListView paramAnonymousAbsListView, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
        {
          GridViewOff.this.loadViewPosition = paramAnonymousInt1;
        }
        
        public void onScrollStateChanged(AbsListView paramAnonymousAbsListView, int paramAnonymousInt) {}
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
              GridViewOff.this.functionsClass.savePreference(".BETA", "testerEmail", null);
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
      return;
    case 4: 
      System.out.println("Swipe Right");
      return;
    }
    System.out.println("Swipe Left");
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
  
  public boolean onTouch(final View paramView, MotionEvent paramMotionEvent)
  {
    if ((paramView instanceof TextView))
    {
      paramView = (TextView)paramView;
      this.loadView.post(new Runnable()
      {
        public void run()
        {
          GridViewOff.this.loadView.smoothScrollToPositionFromTop(((Integer)GridViewOff.this.mapIndex.get(paramView.getText().toString())).intValue(), 0, 213);
        }
      });
    }
    return true;
  }
  
  private class LoadApplicationsIndex
    extends AsyncTask<Void, Void, Void>
  {
    private LoadApplicationsIndex() {}
    
    protected Void doInBackground(Void... paramVarArgs)
    {
      int i = 0;
      while (i < GridViewOff.this.navDrawerItems.size())
      {
        try
        {
          paramVarArgs = ((NavDrawerItem)GridViewOff.this.navDrawerItems.get(i)).getDesc().substring(0, 1).toUpperCase();
          if (GridViewOff.this.mapIndex.get(paramVarArgs) == null) {
            GridViewOff.this.mapIndex.put(paramVarArgs, Integer.valueOf(i));
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
      paramVoid = (LayerDrawable)GridViewOff.this.getResources().getDrawable(2131230887);
      ((GradientDrawable)paramVoid.findDrawableByLayerId(2131296312)).setColor(0);
      Iterator localIterator = new ArrayList(GridViewOff.this.mapIndex.keySet()).iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        TextView localTextView = (TextView)GridViewOff.this.getLayoutInflater().inflate(2131427467, null);
        localTextView.setBackground(paramVoid);
        localTextView.setText(str.toUpperCase());
        localTextView.setTextColor(PublicVariable.colorLightDarkOpposite);
        localTextView.setOnTouchListener(GridViewOff.this);
        GridViewOff.this.indexView.addView(localTextView);
      }
    }
    
    protected void onPreExecute()
    {
      super.onPreExecute();
      GridViewOff.this.indexView.removeAllViews();
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
        int i;
        try
        {
          i = GridViewOff.this.limitedCountLine;
          if (i < GridViewOff.this.applicationInfoList.size())
          {
            paramVarArgs = GridViewOff.this.getPackageManager().getLaunchIntentForPackage(((ApplicationInfo)GridViewOff.this.applicationInfoList.get(i)).packageName);
            if (paramVarArgs == null) {
              break label250;
            }
            try
            {
              GridViewOff.this.PackageName = ((ApplicationInfo)GridViewOff.this.applicationInfoList.get(i)).packageName;
              GridViewOff.this.AppName = GridViewOff.this.functionsClass.appName(GridViewOff.this.PackageName);
              GridViewOff.this.AppIcon = GridViewOff.this.functionsClass.shapedAppIcon(GridViewOff.this.PackageName);
              GridViewOff.this.navDrawerItems.add(new NavDrawerItem(GridViewOff.this.AppName, GridViewOff.this.PackageName, GridViewOff.this.AppIcon));
            }
            catch (Exception paramVarArgs)
            {
              paramVarArgs.printStackTrace();
            }
          }
          GridViewOff.this.adapter = new CardGridAdapter(GridViewOff.this.activity, GridViewOff.this.getApplicationContext(), GridViewOff.this.navDrawerItems);
          GridViewOff.this.adapter.notifyDataSetChanged();
        }
        catch (Exception paramVarArgs)
        {
          paramVarArgs.printStackTrace();
          cancel(true);
          GridViewOff.this.finish();
        }
        return null;
        label250:
        i += 1;
      }
    }
    
    protected void onPostExecute(Void paramVoid)
    {
      super.onPostExecute(paramVoid);
      GridViewOff.this.loadView.setAdapter(GridViewOff.this.adapter);
      if (GridViewOff.this.loadViewPosition == 0) {
        GridViewOff.this.loadView.setSelection(GridViewOff.this.getSharedPreferences("LoadView", 0).getInt("LoadViewPosition", 0));
      } else {
        GridViewOff.this.loadView.setSelection(GridViewOff.this.loadViewPosition);
      }
      new GridViewOff.LoadApplicationsIndex(GridViewOff.this, null).execute(new Void[0]);
      try
      {
        paramVoid = GridViewOff.this.getIntent();
        if ((paramVoid.hasExtra("goHome")) && (paramVoid.getBooleanExtra("goHome", false)))
        {
          paramVoid = new Intent("android.intent.action.MAIN");
          paramVoid.addCategory("android.intent.category.HOME");
          paramVoid.setFlags(268435456);
          GridViewOff.this.startActivity(paramVoid);
          return;
        }
      }
      catch (Exception paramVoid)
      {
        paramVoid.printStackTrace();
        GridViewOff.this.finish();
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
      //   1: getfield 16	net/geekstools/floatshort/PRO/Shortcut/GridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/GridViewOff;
      //   4: astore_1
      //   5: aload_0
      //   6: getfield 16	net/geekstools/floatshort/PRO/Shortcut/GridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/GridViewOff;
      //   9: invokevirtual 36	net/geekstools/floatshort/PRO/Shortcut/GridViewOff:getApplicationContext	()Landroid/content/Context;
      //   12: invokevirtual 42	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
      //   15: astore_3
      //   16: iconst_0
      //   17: istore_2
      //   18: aload_1
      //   19: aload_3
      //   20: iconst_0
      //   21: invokevirtual 48	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
      //   24: putfield 52	net/geekstools/floatshort/PRO/Shortcut/GridViewOff:applicationInfoList	Ljava/util/List;
      //   27: aload_0
      //   28: getfield 16	net/geekstools/floatshort/PRO/Shortcut/GridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/GridViewOff;
      //   31: getfield 52	net/geekstools/floatshort/PRO/Shortcut/GridViewOff:applicationInfoList	Ljava/util/List;
      //   34: new 54	android/content/pm/ApplicationInfo$DisplayNameComparator
      //   37: dup
      //   38: aload_0
      //   39: getfield 16	net/geekstools/floatshort/PRO/Shortcut/GridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/GridViewOff;
      //   42: invokevirtual 55	net/geekstools/floatshort/PRO/Shortcut/GridViewOff:getPackageManager	()Landroid/content/pm/PackageManager;
      //   45: invokespecial 58	android/content/pm/ApplicationInfo$DisplayNameComparator:<init>	(Landroid/content/pm/PackageManager;)V
      //   48: invokestatic 64	java/util/Collections:sort	(Ljava/util/List;Ljava/util/Comparator;)V
      //   51: aload_0
      //   52: getfield 16	net/geekstools/floatshort/PRO/Shortcut/GridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/GridViewOff;
      //   55: aload_0
      //   56: getfield 16	net/geekstools/floatshort/PRO/Shortcut/GridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/GridViewOff;
      //   59: getfield 52	net/geekstools/floatshort/PRO/Shortcut/GridViewOff:applicationInfoList	Ljava/util/List;
      //   62: invokeinterface 70 1 0
      //   67: iconst_3
      //   68: idiv
      //   69: putfield 74	net/geekstools/floatshort/PRO/Shortcut/GridViewOff:limitedCountLine	I
      //   72: iload_2
      //   73: aload_0
      //   74: getfield 16	net/geekstools/floatshort/PRO/Shortcut/GridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/GridViewOff;
      //   77: getfield 74	net/geekstools/floatshort/PRO/Shortcut/GridViewOff:limitedCountLine	I
      //   80: if_icmpge +161 -> 241
      //   83: aload_0
      //   84: getfield 16	net/geekstools/floatshort/PRO/Shortcut/GridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/GridViewOff;
      //   87: invokevirtual 55	net/geekstools/floatshort/PRO/Shortcut/GridViewOff:getPackageManager	()Landroid/content/pm/PackageManager;
      //   90: aload_0
      //   91: getfield 16	net/geekstools/floatshort/PRO/Shortcut/GridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/GridViewOff;
      //   94: getfield 52	net/geekstools/floatshort/PRO/Shortcut/GridViewOff:applicationInfoList	Ljava/util/List;
      //   97: iload_2
      //   98: invokeinterface 78 2 0
      //   103: checkcast 80	android/content/pm/ApplicationInfo
      //   106: getfield 84	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
      //   109: invokevirtual 88	android/content/pm/PackageManager:getLaunchIntentForPackage	(Ljava/lang/String;)Landroid/content/Intent;
      //   112: astore_1
      //   113: aload_1
      //   114: ifnull +421 -> 535
      //   117: aload_0
      //   118: getfield 16	net/geekstools/floatshort/PRO/Shortcut/GridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/GridViewOff;
      //   121: aload_0
      //   122: getfield 16	net/geekstools/floatshort/PRO/Shortcut/GridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/GridViewOff;
      //   125: getfield 52	net/geekstools/floatshort/PRO/Shortcut/GridViewOff:applicationInfoList	Ljava/util/List;
      //   128: iload_2
      //   129: invokeinterface 78 2 0
      //   134: checkcast 80	android/content/pm/ApplicationInfo
      //   137: getfield 84	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
      //   140: putfield 91	net/geekstools/floatshort/PRO/Shortcut/GridViewOff:PackageName	Ljava/lang/String;
      //   143: aload_0
      //   144: getfield 16	net/geekstools/floatshort/PRO/Shortcut/GridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/GridViewOff;
      //   147: aload_0
      //   148: getfield 16	net/geekstools/floatshort/PRO/Shortcut/GridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/GridViewOff;
      //   151: getfield 95	net/geekstools/floatshort/PRO/Shortcut/GridViewOff:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
      //   154: aload_0
      //   155: getfield 16	net/geekstools/floatshort/PRO/Shortcut/GridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/GridViewOff;
      //   158: getfield 91	net/geekstools/floatshort/PRO/Shortcut/GridViewOff:PackageName	Ljava/lang/String;
      //   161: invokevirtual 101	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:appName	(Ljava/lang/String;)Ljava/lang/String;
      //   164: putfield 104	net/geekstools/floatshort/PRO/Shortcut/GridViewOff:AppName	Ljava/lang/String;
      //   167: aload_0
      //   168: getfield 16	net/geekstools/floatshort/PRO/Shortcut/GridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/GridViewOff;
      //   171: aload_0
      //   172: getfield 16	net/geekstools/floatshort/PRO/Shortcut/GridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/GridViewOff;
      //   175: getfield 95	net/geekstools/floatshort/PRO/Shortcut/GridViewOff:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
      //   178: aload_0
      //   179: getfield 16	net/geekstools/floatshort/PRO/Shortcut/GridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/GridViewOff;
      //   182: getfield 91	net/geekstools/floatshort/PRO/Shortcut/GridViewOff:PackageName	Ljava/lang/String;
      //   185: invokevirtual 108	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:shapedAppIcon	(Ljava/lang/String;)Landroid/graphics/drawable/Drawable;
      //   188: putfield 112	net/geekstools/floatshort/PRO/Shortcut/GridViewOff:AppIcon	Landroid/graphics/drawable/Drawable;
      //   191: aload_0
      //   192: getfield 16	net/geekstools/floatshort/PRO/Shortcut/GridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/GridViewOff;
      //   195: getfield 116	net/geekstools/floatshort/PRO/Shortcut/GridViewOff:navDrawerItems	Ljava/util/ArrayList;
      //   198: new 118	net/geekstools/floatshort/PRO/Util/NavAdapter/NavDrawerItem
      //   201: dup
      //   202: aload_0
      //   203: getfield 16	net/geekstools/floatshort/PRO/Shortcut/GridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/GridViewOff;
      //   206: getfield 104	net/geekstools/floatshort/PRO/Shortcut/GridViewOff:AppName	Ljava/lang/String;
      //   209: aload_0
      //   210: getfield 16	net/geekstools/floatshort/PRO/Shortcut/GridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/GridViewOff;
      //   213: getfield 91	net/geekstools/floatshort/PRO/Shortcut/GridViewOff:PackageName	Ljava/lang/String;
      //   216: aload_0
      //   217: getfield 16	net/geekstools/floatshort/PRO/Shortcut/GridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/GridViewOff;
      //   220: getfield 112	net/geekstools/floatshort/PRO/Shortcut/GridViewOff:AppIcon	Landroid/graphics/drawable/Drawable;
      //   223: invokespecial 121	net/geekstools/floatshort/PRO/Util/NavAdapter/NavDrawerItem:<init>	(Ljava/lang/String;Ljava/lang/String;Landroid/graphics/drawable/Drawable;)V
      //   226: invokevirtual 127	java/util/ArrayList:add	(Ljava/lang/Object;)Z
      //   229: pop
      //   230: goto +305 -> 535
      //   233: astore_1
      //   234: aload_1
      //   235: invokevirtual 130	java/lang/Exception:printStackTrace	()V
      //   238: goto +297 -> 535
      //   241: aload_0
      //   242: getfield 16	net/geekstools/floatshort/PRO/Shortcut/GridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/GridViewOff;
      //   245: new 132	net/geekstools/floatshort/PRO/Shortcut/nav/CardGridAdapter
      //   248: dup
      //   249: aload_0
      //   250: getfield 16	net/geekstools/floatshort/PRO/Shortcut/GridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/GridViewOff;
      //   253: getfield 136	net/geekstools/floatshort/PRO/Shortcut/GridViewOff:activity	Landroid/app/Activity;
      //   256: aload_0
      //   257: getfield 16	net/geekstools/floatshort/PRO/Shortcut/GridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/GridViewOff;
      //   260: invokevirtual 36	net/geekstools/floatshort/PRO/Shortcut/GridViewOff:getApplicationContext	()Landroid/content/Context;
      //   263: aload_0
      //   264: getfield 16	net/geekstools/floatshort/PRO/Shortcut/GridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/GridViewOff;
      //   267: getfield 116	net/geekstools/floatshort/PRO/Shortcut/GridViewOff:navDrawerItems	Ljava/util/ArrayList;
      //   270: invokespecial 139	net/geekstools/floatshort/PRO/Shortcut/nav/CardGridAdapter:<init>	(Landroid/app/Activity;Landroid/content/Context;Ljava/util/ArrayList;)V
      //   273: putfield 143	net/geekstools/floatshort/PRO/Shortcut/GridViewOff:adapter	Lnet/geekstools/floatshort/PRO/Shortcut/nav/CardGridAdapter;
      //   276: aload_0
      //   277: getfield 16	net/geekstools/floatshort/PRO/Shortcut/GridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/GridViewOff;
      //   280: getfield 143	net/geekstools/floatshort/PRO/Shortcut/GridViewOff:adapter	Lnet/geekstools/floatshort/PRO/Shortcut/nav/CardGridAdapter;
      //   283: invokevirtual 146	net/geekstools/floatshort/PRO/Shortcut/nav/CardGridAdapter:notifyDataSetChanged	()V
      //   286: aload_0
      //   287: getfield 16	net/geekstools/floatshort/PRO/Shortcut/GridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/GridViewOff;
      //   290: getfield 150	net/geekstools/floatshort/PRO/Shortcut/GridViewOff:intent	Landroid/content/Intent;
      //   293: ldc -104
      //   295: invokevirtual 158	android/content/Intent:getStringArrayExtra	(Ljava/lang/String;)[Ljava/lang/String;
      //   298: ifnull +158 -> 456
      //   301: aload_0
      //   302: getfield 16	net/geekstools/floatshort/PRO/Shortcut/GridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/GridViewOff;
      //   305: aload_0
      //   306: getfield 16	net/geekstools/floatshort/PRO/Shortcut/GridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/GridViewOff;
      //   309: getfield 150	net/geekstools/floatshort/PRO/Shortcut/GridViewOff:intent	Landroid/content/Intent;
      //   312: ldc -104
      //   314: invokevirtual 158	android/content/Intent:getStringArrayExtra	(Ljava/lang/String;)[Ljava/lang/String;
      //   317: putfield 162	net/geekstools/floatshort/PRO/Shortcut/GridViewOff:freqApps	[Ljava/lang/String;
      //   320: aload_0
      //   321: getfield 16	net/geekstools/floatshort/PRO/Shortcut/GridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/GridViewOff;
      //   324: getfield 150	net/geekstools/floatshort/PRO/Shortcut/GridViewOff:intent	Landroid/content/Intent;
      //   327: ldc -92
      //   329: iconst_m1
      //   330: invokevirtual 168	android/content/Intent:getIntExtra	(Ljava/lang/String;I)I
      //   333: istore_2
      //   334: aload_0
      //   335: getfield 16	net/geekstools/floatshort/PRO/Shortcut/GridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/GridViewOff;
      //   338: getfield 162	net/geekstools/floatshort/PRO/Shortcut/GridViewOff:freqApps	[Ljava/lang/String;
      //   341: putstatic 171	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:freqApps	[Ljava/lang/String;
      //   344: iload_2
      //   345: putstatic 174	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:freqLength	I
      //   348: iload_2
      //   349: iconst_1
      //   350: if_icmple +106 -> 456
      //   353: aload_0
      //   354: getfield 16	net/geekstools/floatshort/PRO/Shortcut/GridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/GridViewOff;
      //   357: iconst_1
      //   358: putfield 178	net/geekstools/floatshort/PRO/Shortcut/GridViewOff:loadFreq	Z
      //   361: goto +95 -> 456
      //   364: astore_1
      //   365: goto +93 -> 458
      //   368: astore_1
      //   369: aload_1
      //   370: invokevirtual 130	java/lang/Exception:printStackTrace	()V
      //   373: aload_0
      //   374: iconst_1
      //   375: invokevirtual 182	net/geekstools/floatshort/PRO/Shortcut/GridViewOff$LoadApplicationsOffLimited:cancel	(Z)Z
      //   378: pop
      //   379: aload_0
      //   380: getfield 16	net/geekstools/floatshort/PRO/Shortcut/GridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/GridViewOff;
      //   383: invokevirtual 185	net/geekstools/floatshort/PRO/Shortcut/GridViewOff:finish	()V
      //   386: aload_0
      //   387: getfield 16	net/geekstools/floatshort/PRO/Shortcut/GridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/GridViewOff;
      //   390: getfield 150	net/geekstools/floatshort/PRO/Shortcut/GridViewOff:intent	Landroid/content/Intent;
      //   393: ldc -104
      //   395: invokevirtual 158	android/content/Intent:getStringArrayExtra	(Ljava/lang/String;)[Ljava/lang/String;
      //   398: ifnull +58 -> 456
      //   401: aload_0
      //   402: getfield 16	net/geekstools/floatshort/PRO/Shortcut/GridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/GridViewOff;
      //   405: aload_0
      //   406: getfield 16	net/geekstools/floatshort/PRO/Shortcut/GridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/GridViewOff;
      //   409: getfield 150	net/geekstools/floatshort/PRO/Shortcut/GridViewOff:intent	Landroid/content/Intent;
      //   412: ldc -104
      //   414: invokevirtual 158	android/content/Intent:getStringArrayExtra	(Ljava/lang/String;)[Ljava/lang/String;
      //   417: putfield 162	net/geekstools/floatshort/PRO/Shortcut/GridViewOff:freqApps	[Ljava/lang/String;
      //   420: aload_0
      //   421: getfield 16	net/geekstools/floatshort/PRO/Shortcut/GridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/GridViewOff;
      //   424: getfield 150	net/geekstools/floatshort/PRO/Shortcut/GridViewOff:intent	Landroid/content/Intent;
      //   427: ldc -92
      //   429: iconst_m1
      //   430: invokevirtual 168	android/content/Intent:getIntExtra	(Ljava/lang/String;I)I
      //   433: istore_2
      //   434: aload_0
      //   435: getfield 16	net/geekstools/floatshort/PRO/Shortcut/GridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/GridViewOff;
      //   438: getfield 162	net/geekstools/floatshort/PRO/Shortcut/GridViewOff:freqApps	[Ljava/lang/String;
      //   441: putstatic 171	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:freqApps	[Ljava/lang/String;
      //   444: iload_2
      //   445: putstatic 174	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:freqLength	I
      //   448: iload_2
      //   449: iconst_1
      //   450: if_icmple +6 -> 456
      //   453: goto -100 -> 353
      //   456: aconst_null
      //   457: areturn
      //   458: aload_0
      //   459: getfield 16	net/geekstools/floatshort/PRO/Shortcut/GridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/GridViewOff;
      //   462: getfield 150	net/geekstools/floatshort/PRO/Shortcut/GridViewOff:intent	Landroid/content/Intent;
      //   465: ldc -104
      //   467: invokevirtual 158	android/content/Intent:getStringArrayExtra	(Ljava/lang/String;)[Ljava/lang/String;
      //   470: ifnull +63 -> 533
      //   473: aload_0
      //   474: getfield 16	net/geekstools/floatshort/PRO/Shortcut/GridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/GridViewOff;
      //   477: aload_0
      //   478: getfield 16	net/geekstools/floatshort/PRO/Shortcut/GridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/GridViewOff;
      //   481: getfield 150	net/geekstools/floatshort/PRO/Shortcut/GridViewOff:intent	Landroid/content/Intent;
      //   484: ldc -104
      //   486: invokevirtual 158	android/content/Intent:getStringArrayExtra	(Ljava/lang/String;)[Ljava/lang/String;
      //   489: putfield 162	net/geekstools/floatshort/PRO/Shortcut/GridViewOff:freqApps	[Ljava/lang/String;
      //   492: aload_0
      //   493: getfield 16	net/geekstools/floatshort/PRO/Shortcut/GridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/GridViewOff;
      //   496: getfield 150	net/geekstools/floatshort/PRO/Shortcut/GridViewOff:intent	Landroid/content/Intent;
      //   499: ldc -92
      //   501: iconst_m1
      //   502: invokevirtual 168	android/content/Intent:getIntExtra	(Ljava/lang/String;I)I
      //   505: istore_2
      //   506: aload_0
      //   507: getfield 16	net/geekstools/floatshort/PRO/Shortcut/GridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/GridViewOff;
      //   510: getfield 162	net/geekstools/floatshort/PRO/Shortcut/GridViewOff:freqApps	[Ljava/lang/String;
      //   513: putstatic 171	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:freqApps	[Ljava/lang/String;
      //   516: iload_2
      //   517: putstatic 174	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:freqLength	I
      //   520: iload_2
      //   521: iconst_1
      //   522: if_icmple +11 -> 533
      //   525: aload_0
      //   526: getfield 16	net/geekstools/floatshort/PRO/Shortcut/GridViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/GridViewOff;
      //   529: iconst_1
      //   530: putfield 178	net/geekstools/floatshort/PRO/Shortcut/GridViewOff:loadFreq	Z
      //   533: aload_1
      //   534: athrow
      //   535: iload_2
      //   536: iconst_1
      //   537: iadd
      //   538: istore_2
      //   539: goto -467 -> 72
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	542	0	this	LoadApplicationsOffLimited
      //   0	542	1	paramVarArgs	Void[]
      //   17	522	2	i	int
      //   15	5	3	localPackageManager	PackageManager
      // Exception table:
      //   from	to	target	type
      //   117	230	233	java/lang/Exception
      //   0	16	364	finally
      //   18	72	364	finally
      //   72	113	364	finally
      //   117	230	364	finally
      //   234	238	364	finally
      //   241	286	364	finally
      //   369	386	364	finally
      //   0	16	368	java/lang/Exception
      //   18	72	368	java/lang/Exception
      //   72	113	368	java/lang/Exception
      //   234	238	368	java/lang/Exception
      //   241	286	368	java/lang/Exception
    }
    
    protected void onPostExecute(Void paramVoid)
    {
      super.onPostExecute(paramVoid);
      if (!GridViewOff.this.loadFreq)
      {
        GridViewOff.this.freqlist = ((HorizontalScrollView)GridViewOff.this.findViewById(2131296418));
        GridViewOff.this.MainView.removeView(GridViewOff.this.freqlist);
        paramVoid = new RelativeLayout.LayoutParams(-1, -1);
        paramVoid.addRule(3, 2131296617);
        if (GridViewOff.this.functionsClass.LoadAds()) {
          paramVoid.addRule(2, 2131296287);
        }
        GridViewOff.this.loadView.setLayoutParams(paramVoid);
      }
      GridViewOff.this.loadView.setAdapter(GridViewOff.this.adapter);
      paramVoid = AnimationUtils.loadAnimation(GridViewOff.this.getApplicationContext(), 17432577);
      GridViewOff.this.loadingSplash.startAnimation(paramVoid);
      paramVoid.setAnimationListener(new Animation.AnimationListener()
      {
        public void onAnimationEnd(Animation paramAnonymousAnimation)
        {
          GridViewOff.this.loadingSplash.setVisibility(4);
        }
        
        public void onAnimationRepeat(Animation paramAnonymousAnimation) {}
        
        public void onAnimationStart(Animation paramAnonymousAnimation)
        {
          if (GridViewOff.this.loadFreq == true) {
            GridViewOff.this.LoadFrequentlyApps();
          }
          ((LinearLayout)GridViewOff.this.findViewById(2131296617)).setVisibility(0);
          new GridViewOff.LoadApplicationsOff(GridViewOff.this, null).execute(new Void[0]);
        }
      });
    }
    
    protected void onPreExecute()
    {
      super.onPreExecute();
      GridViewOff.this.loadingSplash = ((RelativeLayout)GridViewOff.this.findViewById(2131296482));
      if (GridViewOff.this.functionsClass.appThemeTransparent() == true) {
        GridViewOff.this.loadingSplash.setBackgroundColor(0);
      } else {
        GridViewOff.this.loadingSplash.setBackgroundColor(GridViewOff.this.getWindow().getNavigationBarColor());
      }
      GridViewOff.this.loadingBarLTR = ((ProgressBar)GridViewOff.this.findViewById(2131296480));
      if (PublicVariable.themeLightDark) {
        GridViewOff.this.loadingBarLTR.getIndeterminateDrawable().setColorFilter(PublicVariable.themeTextColor, PorterDuff.Mode.MULTIPLY);
      } else if (!PublicVariable.themeLightDark) {
        GridViewOff.this.loadingBarLTR.getIndeterminateDrawable().setColorFilter(PublicVariable.themeColor, PorterDuff.Mode.MULTIPLY);
      }
      GridViewOff.this.loadLogo = ((ImageView)GridViewOff.this.findViewById(2131296479));
      LayerDrawable localLayerDrawable = (LayerDrawable)GridViewOff.this.getDrawable(2131230933);
      ((BitmapDrawable)localLayerDrawable.findDrawableByLayerId(2131296443)).setTint(PublicVariable.primaryColor);
      GridViewOff.this.loadLogo.setImageDrawable(localLayerDrawable);
      GridViewOff.this.loadView.clearChoices();
      GridViewOff.this.indexView.removeAllViews();
    }
  }
}
