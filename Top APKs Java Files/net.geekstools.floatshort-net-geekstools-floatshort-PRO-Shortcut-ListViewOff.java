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
import android.preference.PreferenceManager;
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
import net.geekstools.floatshort.PRO.Shortcut.nav.CardListAdapter;
import net.geekstools.floatshort.PRO.Util.Free.InAppBilling;
import net.geekstools.floatshort.PRO.Util.Functions.FunctionsClass;
import net.geekstools.floatshort.PRO.Util.Functions.PublicVariable;
import net.geekstools.floatshort.PRO.Util.NavAdapter.NavDrawerItem;
import net.geekstools.floatshort.PRO.Util.UI.SimpleGestureFilterSwitch;
import net.geekstools.floatshort.PRO.Util.UI.SimpleGestureFilterSwitch.SimpleGestureListener;
import net.geekstools.imageview.customshapes.ShapesImage;

public class ListViewOff
  extends Activity
  implements View.OnClickListener, View.OnLongClickListener, View.OnTouchListener, SimpleGestureFilterSwitch.SimpleGestureListener
{
  Drawable AppIcon;
  String AppName = "Application";
  RelativeLayout MainView;
  String PackageName;
  ListView actionElementsList;
  Activity activity;
  CardListAdapter adapter;
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
  ListView loadView;
  int loadViewPosition = 0;
  ProgressBar loadingBarLTR;
  RelativeLayout loadingSplash;
  Map<String, Integer> mapIndex;
  ArrayList<NavDrawerItem> navDrawerItems;
  SimpleGestureFilterSwitch simpleGestureFilterSwitch;
  
  public ListViewOff() {}
  
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
              paramAnonymousTask = ListViewOff.this.firebaseAuth.getCurrentUser();
              if (paramAnonymousTask != null)
              {
                ListViewOff.this.functionsClass.savePreference(".BETA", "testerEmail", paramAnonymousTask.getEmail());
                new Handler().postDelayed(new Runnable()
                {
                  public void run()
                  {
                    try
                    {
                      Object localObject = new StringBuilder();
                      ((StringBuilder)localObject).append("/data/data/");
                      ((StringBuilder)localObject).append(ListViewOff.this.getPackageName());
                      ((StringBuilder)localObject).append("/shared_prefs/.BETA.xml");
                      localObject = Uri.fromFile(new File(((StringBuilder)localObject).toString()));
                      FirebaseStorage localFirebaseStorage = FirebaseStorage.getInstance();
                      StringBuilder localStringBuilder = new StringBuilder();
                      localStringBuilder.append("/betaTesters/API");
                      localStringBuilder.append(ListViewOff.this.functionsClass.returnAPI());
                      localStringBuilder.append("/");
                      localStringBuilder.append(ListViewOff.this.functionsClass.readPreference(".BETA", "testerEmail", null));
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
                          ListViewOff.this.functionsClass.Toast(ListViewOff.this.getString(2131623988), 48);
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
    setContentView(2131427451);
    this.loadView = ((ListView)findViewById(2131296475));
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
          ListViewOff.this.functionsClass.overrideBackPressToClass(CategoryHandler.class, ActivityOptions.makeCustomAnimation(ListViewOff.this.getApplicationContext(), 2130771989, 2130771990));
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
    PublicVariable.inMemory = false;
    super.onDestroy();
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
          ListViewOff.this.firebaseRemoteConfig.activateFetched();
          if (ListViewOff.this.firebaseRemoteConfig.getLong(ListViewOff.this.functionsClass.versionCodeRemoteConfigKey()) > ListViewOff.this.functionsClass.appVersionCode(ListViewOff.this.getPackageName()))
          {
            ListViewOff.this.getActionBar().setDisplayHomeAsUpEnabled(true);
            paramAnonymousTask = (LayerDrawable)ListViewOff.this.getDrawable(2131230951);
            ((BitmapDrawable)paramAnonymousTask.findDrawableByLayerId(2131296443)).setTint(PublicVariable.primaryColor);
            paramAnonymousTask = ListViewOff.this.functionsClass.drawableToBitmap(paramAnonymousTask);
            paramAnonymousTask = Bitmap.createScaledBitmap(paramAnonymousTask, paramAnonymousTask.getWidth() / 4, paramAnonymousTask.getHeight() / 4, false);
            paramAnonymousTask = new BitmapDrawable(ListViewOff.this.getResources(), paramAnonymousTask);
            ListViewOff.this.activity.getActionBar().setHomeAsUpIndicator(paramAnonymousTask);
            ListViewOff.this.functionsClass.notificationCreator(ListViewOff.this.getString(2131624217), ListViewOff.this.firebaseRemoteConfig.getString(ListViewOff.this.functionsClass.upcomingChangeLogSummaryConfigKey()), (int)ListViewOff.this.firebaseRemoteConfig.getLong(ListViewOff.this.functionsClass.versionCodeRemoteConfigKey()));
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
        if (paramAnonymousIntent.getAction().equals(ListViewOff.this.getString(2131624092)))
        {
          ListViewOff.this.functionsClass.dialogueLicense(ListViewOff.this);
          new Handler().postDelayed(new Runnable()
          {
            public void run()
            {
              ListViewOff.this.stopService(new Intent(ListViewOff.this.getApplicationContext(), LicenseValidator.class));
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
          ListViewOff.this.loadViewPosition = paramAnonymousInt1;
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
              ListViewOff.this.functionsClass.savePreference(".BETA", "testerEmail", null);
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
          ListViewOff.this.loadView.smoothScrollToPositionFromTop(((Integer)ListViewOff.this.mapIndex.get(paramView.getText().toString())).intValue(), 0, 213);
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
      while (i < ListViewOff.this.navDrawerItems.size())
      {
        try
        {
          paramVarArgs = ((NavDrawerItem)ListViewOff.this.navDrawerItems.get(i)).getDesc().substring(0, 1).toUpperCase();
          if (ListViewOff.this.mapIndex.get(paramVarArgs) == null) {
            ListViewOff.this.mapIndex.put(paramVarArgs, Integer.valueOf(i));
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
      paramVoid = (LayerDrawable)ListViewOff.this.getResources().getDrawable(2131230887);
      ((GradientDrawable)paramVoid.findDrawableByLayerId(2131296312)).setColor(0);
      Iterator localIterator = new ArrayList(ListViewOff.this.mapIndex.keySet()).iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        TextView localTextView = (TextView)ListViewOff.this.getLayoutInflater().inflate(2131427467, null);
        localTextView.setBackground(paramVoid);
        localTextView.setText(str.toUpperCase());
        localTextView.setTextColor(PublicVariable.colorLightDarkOpposite);
        localTextView.setOnTouchListener(ListViewOff.this);
        ListViewOff.this.indexView.addView(localTextView);
      }
    }
    
    protected void onPreExecute()
    {
      super.onPreExecute();
      ListViewOff.this.indexView.removeAllViews();
    }
  }
  
  private class LoadApplicationsOff
    extends AsyncTask<Void, Void, Void>
  {
    private LoadApplicationsOff() {}
    
    /* Error */
    protected Void doInBackground(Void... paramVarArgs)
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 14	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOff:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   4: getfield 34	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:limitedCountLine	I
      //   7: istore_2
      //   8: iload_2
      //   9: aload_0
      //   10: getfield 14	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOff:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   13: getfield 38	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:applicationInfoList	Ljava/util/List;
      //   16: invokeinterface 44 1 0
      //   21: if_icmpge +164 -> 185
      //   24: aload_0
      //   25: getfield 14	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOff:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   28: invokevirtual 48	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:getApplicationContext	()Landroid/content/Context;
      //   31: invokevirtual 54	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
      //   34: aload_0
      //   35: getfield 14	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOff:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   38: getfield 38	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:applicationInfoList	Ljava/util/List;
      //   41: iload_2
      //   42: invokeinterface 58 2 0
      //   47: checkcast 60	android/content/pm/ApplicationInfo
      //   50: getfield 64	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
      //   53: invokevirtual 70	android/content/pm/PackageManager:getLaunchIntentForPackage	(Ljava/lang/String;)Landroid/content/Intent;
      //   56: astore_1
      //   57: aload_1
      //   58: ifnull +421 -> 479
      //   61: aload_0
      //   62: getfield 14	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOff:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   65: aload_0
      //   66: getfield 14	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOff:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   69: getfield 38	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:applicationInfoList	Ljava/util/List;
      //   72: iload_2
      //   73: invokeinterface 58 2 0
      //   78: checkcast 60	android/content/pm/ApplicationInfo
      //   81: getfield 64	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
      //   84: putfield 73	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:PackageName	Ljava/lang/String;
      //   87: aload_0
      //   88: getfield 14	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOff:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   91: aload_0
      //   92: getfield 14	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOff:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   95: getfield 77	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
      //   98: aload_0
      //   99: getfield 14	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOff:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   102: getfield 73	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:PackageName	Ljava/lang/String;
      //   105: invokevirtual 83	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:appName	(Ljava/lang/String;)Ljava/lang/String;
      //   108: putfield 86	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:AppName	Ljava/lang/String;
      //   111: aload_0
      //   112: getfield 14	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOff:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   115: aload_0
      //   116: getfield 14	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOff:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   119: getfield 77	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
      //   122: aload_0
      //   123: getfield 14	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOff:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   126: getfield 73	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:PackageName	Ljava/lang/String;
      //   129: invokevirtual 90	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:shapedAppIcon	(Ljava/lang/String;)Landroid/graphics/drawable/Drawable;
      //   132: putfield 94	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:AppIcon	Landroid/graphics/drawable/Drawable;
      //   135: aload_0
      //   136: getfield 14	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOff:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   139: getfield 98	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:navDrawerItems	Ljava/util/ArrayList;
      //   142: new 100	net/geekstools/floatshort/PRO/Util/NavAdapter/NavDrawerItem
      //   145: dup
      //   146: aload_0
      //   147: getfield 14	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOff:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   150: getfield 86	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:AppName	Ljava/lang/String;
      //   153: aload_0
      //   154: getfield 14	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOff:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   157: getfield 73	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:PackageName	Ljava/lang/String;
      //   160: aload_0
      //   161: getfield 14	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOff:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   164: getfield 94	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:AppIcon	Landroid/graphics/drawable/Drawable;
      //   167: invokespecial 103	net/geekstools/floatshort/PRO/Util/NavAdapter/NavDrawerItem:<init>	(Ljava/lang/String;Ljava/lang/String;Landroid/graphics/drawable/Drawable;)V
      //   170: invokevirtual 109	java/util/ArrayList:add	(Ljava/lang/Object;)Z
      //   173: pop
      //   174: goto +305 -> 479
      //   177: astore_1
      //   178: aload_1
      //   179: invokevirtual 112	java/lang/Exception:printStackTrace	()V
      //   182: goto +297 -> 479
      //   185: aload_0
      //   186: getfield 14	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOff:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   189: new 114	net/geekstools/floatshort/PRO/Shortcut/nav/CardListAdapter
      //   192: dup
      //   193: aload_0
      //   194: getfield 14	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOff:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   197: getfield 118	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:activity	Landroid/app/Activity;
      //   200: aload_0
      //   201: getfield 14	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOff:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   204: invokevirtual 48	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:getApplicationContext	()Landroid/content/Context;
      //   207: aload_0
      //   208: getfield 14	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOff:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   211: getfield 98	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:navDrawerItems	Ljava/util/ArrayList;
      //   214: invokespecial 121	net/geekstools/floatshort/PRO/Shortcut/nav/CardListAdapter:<init>	(Landroid/app/Activity;Landroid/content/Context;Ljava/util/ArrayList;)V
      //   217: putfield 125	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:adapter	Lnet/geekstools/floatshort/PRO/Shortcut/nav/CardListAdapter;
      //   220: aload_0
      //   221: getfield 14	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOff:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   224: getfield 125	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:adapter	Lnet/geekstools/floatshort/PRO/Shortcut/nav/CardListAdapter;
      //   227: invokevirtual 128	net/geekstools/floatshort/PRO/Shortcut/nav/CardListAdapter:notifyDataSetChanged	()V
      //   230: aload_0
      //   231: getfield 14	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOff:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   234: getfield 132	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:intent	Landroid/content/Intent;
      //   237: ldc -122
      //   239: invokevirtual 140	android/content/Intent:getStringArrayExtra	(Ljava/lang/String;)[Ljava/lang/String;
      //   242: ifnull +158 -> 400
      //   245: aload_0
      //   246: getfield 14	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOff:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   249: aload_0
      //   250: getfield 14	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOff:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   253: getfield 132	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:intent	Landroid/content/Intent;
      //   256: ldc -122
      //   258: invokevirtual 140	android/content/Intent:getStringArrayExtra	(Ljava/lang/String;)[Ljava/lang/String;
      //   261: putfield 144	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:freqApps	[Ljava/lang/String;
      //   264: aload_0
      //   265: getfield 14	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOff:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   268: getfield 132	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:intent	Landroid/content/Intent;
      //   271: ldc -110
      //   273: iconst_m1
      //   274: invokevirtual 150	android/content/Intent:getIntExtra	(Ljava/lang/String;I)I
      //   277: istore_2
      //   278: aload_0
      //   279: getfield 14	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOff:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   282: getfield 144	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:freqApps	[Ljava/lang/String;
      //   285: putstatic 153	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:freqApps	[Ljava/lang/String;
      //   288: iload_2
      //   289: putstatic 156	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:freqLength	I
      //   292: iload_2
      //   293: iconst_1
      //   294: if_icmple +106 -> 400
      //   297: aload_0
      //   298: getfield 14	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOff:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   301: iconst_1
      //   302: putfield 160	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:loadFreq	Z
      //   305: goto +95 -> 400
      //   308: astore_1
      //   309: goto +93 -> 402
      //   312: astore_1
      //   313: aload_1
      //   314: invokevirtual 112	java/lang/Exception:printStackTrace	()V
      //   317: aload_0
      //   318: iconst_1
      //   319: invokevirtual 164	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOff:cancel	(Z)Z
      //   322: pop
      //   323: aload_0
      //   324: getfield 14	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOff:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   327: invokevirtual 167	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:finish	()V
      //   330: aload_0
      //   331: getfield 14	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOff:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   334: getfield 132	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:intent	Landroid/content/Intent;
      //   337: ldc -122
      //   339: invokevirtual 140	android/content/Intent:getStringArrayExtra	(Ljava/lang/String;)[Ljava/lang/String;
      //   342: ifnull +58 -> 400
      //   345: aload_0
      //   346: getfield 14	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOff:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   349: aload_0
      //   350: getfield 14	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOff:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   353: getfield 132	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:intent	Landroid/content/Intent;
      //   356: ldc -122
      //   358: invokevirtual 140	android/content/Intent:getStringArrayExtra	(Ljava/lang/String;)[Ljava/lang/String;
      //   361: putfield 144	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:freqApps	[Ljava/lang/String;
      //   364: aload_0
      //   365: getfield 14	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOff:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   368: getfield 132	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:intent	Landroid/content/Intent;
      //   371: ldc -110
      //   373: iconst_m1
      //   374: invokevirtual 150	android/content/Intent:getIntExtra	(Ljava/lang/String;I)I
      //   377: istore_2
      //   378: aload_0
      //   379: getfield 14	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOff:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   382: getfield 144	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:freqApps	[Ljava/lang/String;
      //   385: putstatic 153	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:freqApps	[Ljava/lang/String;
      //   388: iload_2
      //   389: putstatic 156	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:freqLength	I
      //   392: iload_2
      //   393: iconst_1
      //   394: if_icmple +6 -> 400
      //   397: goto -100 -> 297
      //   400: aconst_null
      //   401: areturn
      //   402: aload_0
      //   403: getfield 14	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOff:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   406: getfield 132	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:intent	Landroid/content/Intent;
      //   409: ldc -122
      //   411: invokevirtual 140	android/content/Intent:getStringArrayExtra	(Ljava/lang/String;)[Ljava/lang/String;
      //   414: ifnull +63 -> 477
      //   417: aload_0
      //   418: getfield 14	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOff:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   421: aload_0
      //   422: getfield 14	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOff:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   425: getfield 132	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:intent	Landroid/content/Intent;
      //   428: ldc -122
      //   430: invokevirtual 140	android/content/Intent:getStringArrayExtra	(Ljava/lang/String;)[Ljava/lang/String;
      //   433: putfield 144	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:freqApps	[Ljava/lang/String;
      //   436: aload_0
      //   437: getfield 14	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOff:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   440: getfield 132	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:intent	Landroid/content/Intent;
      //   443: ldc -110
      //   445: iconst_m1
      //   446: invokevirtual 150	android/content/Intent:getIntExtra	(Ljava/lang/String;I)I
      //   449: istore_2
      //   450: aload_0
      //   451: getfield 14	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOff:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   454: getfield 144	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:freqApps	[Ljava/lang/String;
      //   457: putstatic 153	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:freqApps	[Ljava/lang/String;
      //   460: iload_2
      //   461: putstatic 156	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:freqLength	I
      //   464: iload_2
      //   465: iconst_1
      //   466: if_icmple +11 -> 477
      //   469: aload_0
      //   470: getfield 14	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOff:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   473: iconst_1
      //   474: putfield 160	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:loadFreq	Z
      //   477: aload_1
      //   478: athrow
      //   479: iload_2
      //   480: iconst_1
      //   481: iadd
      //   482: istore_2
      //   483: goto -475 -> 8
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	486	0	this	LoadApplicationsOff
      //   0	486	1	paramVarArgs	Void[]
      //   7	476	2	i	int
      // Exception table:
      //   from	to	target	type
      //   61	174	177	java/lang/Exception
      //   0	8	308	finally
      //   8	57	308	finally
      //   61	174	308	finally
      //   178	182	308	finally
      //   185	230	308	finally
      //   313	330	308	finally
      //   0	8	312	java/lang/Exception
      //   8	57	312	java/lang/Exception
      //   178	182	312	java/lang/Exception
      //   185	230	312	java/lang/Exception
    }
    
    protected void onPostExecute(Void paramVoid)
    {
      super.onPostExecute(paramVoid);
      ListViewOff.this.loadView.setAdapter(ListViewOff.this.adapter);
      if (ListViewOff.this.loadViewPosition == 0) {
        ListViewOff.this.loadView.setSelection(ListViewOff.this.getSharedPreferences("LoadView", 0).getInt("LoadViewPosition", 0));
      } else {
        ListViewOff.this.loadView.setSelection(ListViewOff.this.loadViewPosition);
      }
      new ListViewOff.LoadApplicationsIndex(ListViewOff.this, null).execute(new Void[0]);
      try
      {
        paramVoid = ListViewOff.this.getIntent();
        if ((paramVoid.hasExtra("goHome")) && (paramVoid.getBooleanExtra("goHome", false)))
        {
          paramVoid = new Intent("android.intent.action.MAIN");
          paramVoid.addCategory("android.intent.category.HOME");
          paramVoid.setFlags(268435456);
          ListViewOff.this.startActivity(paramVoid);
          return;
        }
      }
      catch (Exception paramVoid)
      {
        paramVoid.printStackTrace();
        ListViewOff.this.finish();
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
      //   1: getfield 16	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   4: astore_1
      //   5: aload_0
      //   6: getfield 16	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   9: invokevirtual 36	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:getApplicationContext	()Landroid/content/Context;
      //   12: invokevirtual 42	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
      //   15: astore_3
      //   16: iconst_0
      //   17: istore_2
      //   18: aload_1
      //   19: aload_3
      //   20: iconst_0
      //   21: invokevirtual 48	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
      //   24: putfield 52	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:applicationInfoList	Ljava/util/List;
      //   27: aload_0
      //   28: getfield 16	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   31: getfield 52	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:applicationInfoList	Ljava/util/List;
      //   34: new 54	android/content/pm/ApplicationInfo$DisplayNameComparator
      //   37: dup
      //   38: aload_0
      //   39: getfield 16	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   42: invokevirtual 55	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:getPackageManager	()Landroid/content/pm/PackageManager;
      //   45: invokespecial 58	android/content/pm/ApplicationInfo$DisplayNameComparator:<init>	(Landroid/content/pm/PackageManager;)V
      //   48: invokestatic 64	java/util/Collections:sort	(Ljava/util/List;Ljava/util/Comparator;)V
      //   51: aload_0
      //   52: getfield 16	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   55: aload_0
      //   56: getfield 16	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   59: getfield 52	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:applicationInfoList	Ljava/util/List;
      //   62: invokeinterface 70 1 0
      //   67: iconst_3
      //   68: idiv
      //   69: putfield 74	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:limitedCountLine	I
      //   72: iload_2
      //   73: aload_0
      //   74: getfield 16	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   77: getfield 74	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:limitedCountLine	I
      //   80: if_icmpge +164 -> 244
      //   83: aload_0
      //   84: getfield 16	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   87: invokevirtual 36	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:getApplicationContext	()Landroid/content/Context;
      //   90: invokevirtual 42	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
      //   93: aload_0
      //   94: getfield 16	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   97: getfield 52	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:applicationInfoList	Ljava/util/List;
      //   100: iload_2
      //   101: invokeinterface 78 2 0
      //   106: checkcast 80	android/content/pm/ApplicationInfo
      //   109: getfield 84	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
      //   112: invokevirtual 88	android/content/pm/PackageManager:getLaunchIntentForPackage	(Ljava/lang/String;)Landroid/content/Intent;
      //   115: astore_1
      //   116: aload_1
      //   117: ifnull +421 -> 538
      //   120: aload_0
      //   121: getfield 16	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   124: aload_0
      //   125: getfield 16	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   128: getfield 52	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:applicationInfoList	Ljava/util/List;
      //   131: iload_2
      //   132: invokeinterface 78 2 0
      //   137: checkcast 80	android/content/pm/ApplicationInfo
      //   140: getfield 84	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
      //   143: putfield 91	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:PackageName	Ljava/lang/String;
      //   146: aload_0
      //   147: getfield 16	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   150: aload_0
      //   151: getfield 16	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   154: getfield 95	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
      //   157: aload_0
      //   158: getfield 16	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   161: getfield 91	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:PackageName	Ljava/lang/String;
      //   164: invokevirtual 101	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:appName	(Ljava/lang/String;)Ljava/lang/String;
      //   167: putfield 104	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:AppName	Ljava/lang/String;
      //   170: aload_0
      //   171: getfield 16	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   174: aload_0
      //   175: getfield 16	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   178: getfield 95	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
      //   181: aload_0
      //   182: getfield 16	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   185: getfield 91	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:PackageName	Ljava/lang/String;
      //   188: invokevirtual 108	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:shapedAppIcon	(Ljava/lang/String;)Landroid/graphics/drawable/Drawable;
      //   191: putfield 112	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:AppIcon	Landroid/graphics/drawable/Drawable;
      //   194: aload_0
      //   195: getfield 16	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   198: getfield 116	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:navDrawerItems	Ljava/util/ArrayList;
      //   201: new 118	net/geekstools/floatshort/PRO/Util/NavAdapter/NavDrawerItem
      //   204: dup
      //   205: aload_0
      //   206: getfield 16	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   209: getfield 104	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:AppName	Ljava/lang/String;
      //   212: aload_0
      //   213: getfield 16	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   216: getfield 91	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:PackageName	Ljava/lang/String;
      //   219: aload_0
      //   220: getfield 16	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   223: getfield 112	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:AppIcon	Landroid/graphics/drawable/Drawable;
      //   226: invokespecial 121	net/geekstools/floatshort/PRO/Util/NavAdapter/NavDrawerItem:<init>	(Ljava/lang/String;Ljava/lang/String;Landroid/graphics/drawable/Drawable;)V
      //   229: invokevirtual 127	java/util/ArrayList:add	(Ljava/lang/Object;)Z
      //   232: pop
      //   233: goto +305 -> 538
      //   236: astore_1
      //   237: aload_1
      //   238: invokevirtual 130	java/lang/Exception:printStackTrace	()V
      //   241: goto +297 -> 538
      //   244: aload_0
      //   245: getfield 16	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   248: new 132	net/geekstools/floatshort/PRO/Shortcut/nav/CardListAdapter
      //   251: dup
      //   252: aload_0
      //   253: getfield 16	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   256: getfield 136	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:activity	Landroid/app/Activity;
      //   259: aload_0
      //   260: getfield 16	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   263: invokevirtual 36	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:getApplicationContext	()Landroid/content/Context;
      //   266: aload_0
      //   267: getfield 16	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   270: getfield 116	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:navDrawerItems	Ljava/util/ArrayList;
      //   273: invokespecial 139	net/geekstools/floatshort/PRO/Shortcut/nav/CardListAdapter:<init>	(Landroid/app/Activity;Landroid/content/Context;Ljava/util/ArrayList;)V
      //   276: putfield 143	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:adapter	Lnet/geekstools/floatshort/PRO/Shortcut/nav/CardListAdapter;
      //   279: aload_0
      //   280: getfield 16	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   283: getfield 143	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:adapter	Lnet/geekstools/floatshort/PRO/Shortcut/nav/CardListAdapter;
      //   286: invokevirtual 146	net/geekstools/floatshort/PRO/Shortcut/nav/CardListAdapter:notifyDataSetChanged	()V
      //   289: aload_0
      //   290: getfield 16	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   293: getfield 150	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:intent	Landroid/content/Intent;
      //   296: ldc -104
      //   298: invokevirtual 158	android/content/Intent:getStringArrayExtra	(Ljava/lang/String;)[Ljava/lang/String;
      //   301: ifnull +158 -> 459
      //   304: aload_0
      //   305: getfield 16	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   308: aload_0
      //   309: getfield 16	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   312: getfield 150	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:intent	Landroid/content/Intent;
      //   315: ldc -104
      //   317: invokevirtual 158	android/content/Intent:getStringArrayExtra	(Ljava/lang/String;)[Ljava/lang/String;
      //   320: putfield 162	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:freqApps	[Ljava/lang/String;
      //   323: aload_0
      //   324: getfield 16	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   327: getfield 150	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:intent	Landroid/content/Intent;
      //   330: ldc -92
      //   332: iconst_m1
      //   333: invokevirtual 168	android/content/Intent:getIntExtra	(Ljava/lang/String;I)I
      //   336: istore_2
      //   337: aload_0
      //   338: getfield 16	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   341: getfield 162	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:freqApps	[Ljava/lang/String;
      //   344: putstatic 171	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:freqApps	[Ljava/lang/String;
      //   347: iload_2
      //   348: putstatic 174	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:freqLength	I
      //   351: iload_2
      //   352: iconst_1
      //   353: if_icmple +106 -> 459
      //   356: aload_0
      //   357: getfield 16	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   360: iconst_1
      //   361: putfield 178	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:loadFreq	Z
      //   364: goto +95 -> 459
      //   367: astore_1
      //   368: goto +93 -> 461
      //   371: astore_1
      //   372: aload_1
      //   373: invokevirtual 130	java/lang/Exception:printStackTrace	()V
      //   376: aload_0
      //   377: iconst_1
      //   378: invokevirtual 182	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOffLimited:cancel	(Z)Z
      //   381: pop
      //   382: aload_0
      //   383: getfield 16	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   386: invokevirtual 185	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:finish	()V
      //   389: aload_0
      //   390: getfield 16	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   393: getfield 150	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:intent	Landroid/content/Intent;
      //   396: ldc -104
      //   398: invokevirtual 158	android/content/Intent:getStringArrayExtra	(Ljava/lang/String;)[Ljava/lang/String;
      //   401: ifnull +58 -> 459
      //   404: aload_0
      //   405: getfield 16	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   408: aload_0
      //   409: getfield 16	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   412: getfield 150	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:intent	Landroid/content/Intent;
      //   415: ldc -104
      //   417: invokevirtual 158	android/content/Intent:getStringArrayExtra	(Ljava/lang/String;)[Ljava/lang/String;
      //   420: putfield 162	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:freqApps	[Ljava/lang/String;
      //   423: aload_0
      //   424: getfield 16	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   427: getfield 150	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:intent	Landroid/content/Intent;
      //   430: ldc -92
      //   432: iconst_m1
      //   433: invokevirtual 168	android/content/Intent:getIntExtra	(Ljava/lang/String;I)I
      //   436: istore_2
      //   437: aload_0
      //   438: getfield 16	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   441: getfield 162	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:freqApps	[Ljava/lang/String;
      //   444: putstatic 171	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:freqApps	[Ljava/lang/String;
      //   447: iload_2
      //   448: putstatic 174	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:freqLength	I
      //   451: iload_2
      //   452: iconst_1
      //   453: if_icmple +6 -> 459
      //   456: goto -100 -> 356
      //   459: aconst_null
      //   460: areturn
      //   461: aload_0
      //   462: getfield 16	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   465: getfield 150	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:intent	Landroid/content/Intent;
      //   468: ldc -104
      //   470: invokevirtual 158	android/content/Intent:getStringArrayExtra	(Ljava/lang/String;)[Ljava/lang/String;
      //   473: ifnull +63 -> 536
      //   476: aload_0
      //   477: getfield 16	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   480: aload_0
      //   481: getfield 16	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   484: getfield 150	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:intent	Landroid/content/Intent;
      //   487: ldc -104
      //   489: invokevirtual 158	android/content/Intent:getStringArrayExtra	(Ljava/lang/String;)[Ljava/lang/String;
      //   492: putfield 162	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:freqApps	[Ljava/lang/String;
      //   495: aload_0
      //   496: getfield 16	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   499: getfield 150	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:intent	Landroid/content/Intent;
      //   502: ldc -92
      //   504: iconst_m1
      //   505: invokevirtual 168	android/content/Intent:getIntExtra	(Ljava/lang/String;I)I
      //   508: istore_2
      //   509: aload_0
      //   510: getfield 16	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   513: getfield 162	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:freqApps	[Ljava/lang/String;
      //   516: putstatic 171	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:freqApps	[Ljava/lang/String;
      //   519: iload_2
      //   520: putstatic 174	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:freqLength	I
      //   523: iload_2
      //   524: iconst_1
      //   525: if_icmple +11 -> 536
      //   528: aload_0
      //   529: getfield 16	net/geekstools/floatshort/PRO/Shortcut/ListViewOff$LoadApplicationsOffLimited:this$0	Lnet/geekstools/floatshort/PRO/Shortcut/ListViewOff;
      //   532: iconst_1
      //   533: putfield 178	net/geekstools/floatshort/PRO/Shortcut/ListViewOff:loadFreq	Z
      //   536: aload_1
      //   537: athrow
      //   538: iload_2
      //   539: iconst_1
      //   540: iadd
      //   541: istore_2
      //   542: goto -470 -> 72
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	545	0	this	LoadApplicationsOffLimited
      //   0	545	1	paramVarArgs	Void[]
      //   17	525	2	i	int
      //   15	5	3	localPackageManager	android.content.pm.PackageManager
      // Exception table:
      //   from	to	target	type
      //   120	233	236	java/lang/Exception
      //   0	16	367	finally
      //   18	72	367	finally
      //   72	116	367	finally
      //   120	233	367	finally
      //   237	241	367	finally
      //   244	289	367	finally
      //   372	389	367	finally
      //   0	16	371	java/lang/Exception
      //   18	72	371	java/lang/Exception
      //   72	116	371	java/lang/Exception
      //   237	241	371	java/lang/Exception
      //   244	289	371	java/lang/Exception
    }
    
    protected void onPostExecute(Void paramVoid)
    {
      super.onPostExecute(paramVoid);
      if (!ListViewOff.this.loadFreq)
      {
        ListViewOff.this.freqlist = ((HorizontalScrollView)ListViewOff.this.findViewById(2131296418));
        ListViewOff.this.MainView.removeView(ListViewOff.this.freqlist);
        paramVoid = new RelativeLayout.LayoutParams(-1, -1);
        paramVoid.addRule(3, 2131296617);
        if (ListViewOff.this.functionsClass.LoadAds()) {
          paramVoid.addRule(2, 2131296287);
        }
        ListViewOff.this.loadView.setLayoutParams(paramVoid);
      }
      ListViewOff.this.loadView.setAdapter(ListViewOff.this.adapter);
      paramVoid = AnimationUtils.loadAnimation(ListViewOff.this.getApplicationContext(), 17432577);
      ListViewOff.this.loadingSplash.startAnimation(paramVoid);
      paramVoid.setAnimationListener(new Animation.AnimationListener()
      {
        public void onAnimationEnd(Animation paramAnonymousAnimation)
        {
          ListViewOff.this.loadingSplash.setVisibility(4);
        }
        
        public void onAnimationRepeat(Animation paramAnonymousAnimation) {}
        
        public void onAnimationStart(Animation paramAnonymousAnimation)
        {
          if (ListViewOff.this.loadFreq == true) {
            ListViewOff.this.LoadFrequentlyApps();
          }
          ((LinearLayout)ListViewOff.this.findViewById(2131296617)).setVisibility(0);
          new ListViewOff.LoadApplicationsOff(ListViewOff.this, null).execute(new Void[0]);
        }
      });
    }
    
    protected void onPreExecute()
    {
      super.onPreExecute();
      ListViewOff.this.loadingSplash = ((RelativeLayout)ListViewOff.this.findViewById(2131296482));
      if (ListViewOff.this.functionsClass.appThemeTransparent() == true) {
        ListViewOff.this.loadingSplash.setBackgroundColor(0);
      } else {
        ListViewOff.this.loadingSplash.setBackgroundColor(ListViewOff.this.getWindow().getNavigationBarColor());
      }
      ListViewOff.this.loadingBarLTR = ((ProgressBar)ListViewOff.this.findViewById(2131296480));
      PreferenceManager.getDefaultSharedPreferences(ListViewOff.this.getApplicationContext());
      if (PublicVariable.themeLightDark) {
        ListViewOff.this.loadingBarLTR.getIndeterminateDrawable().setColorFilter(PublicVariable.themeTextColor, PorterDuff.Mode.MULTIPLY);
      } else if (!PublicVariable.themeLightDark) {
        ListViewOff.this.loadingBarLTR.getIndeterminateDrawable().setColorFilter(PublicVariable.themeColor, PorterDuff.Mode.MULTIPLY);
      }
      ListViewOff.this.loadLogo = ((ImageView)ListViewOff.this.findViewById(2131296479));
      LayerDrawable localLayerDrawable = (LayerDrawable)ListViewOff.this.getDrawable(2131230933);
      ((BitmapDrawable)localLayerDrawable.findDrawableByLayerId(2131296443)).setTint(PublicVariable.primaryColor);
      ListViewOff.this.loadLogo.setImageDrawable(localLayerDrawable);
      ListViewOff.this.loadView.clearChoices();
      ListViewOff.this.indexView.removeAllViews();
    }
  }
}
