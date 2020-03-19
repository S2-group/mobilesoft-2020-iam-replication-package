package indian.plusone.phone.launcher.activities;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipData.Item;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.StyleSpan;
import android.util.Property;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import com.skyfishjy.library.RippleBackground;
import indian.plusone.phone.launcher.LauncherActivity;
import indian.plusone.phone.launcher.permission.PermissionLayout;
import indian.plusone.phone.launcher.pref.AppPref;
import indian.plusone.phone.launcher.themehelper.ThemeManager;
import indian.plusone.phone.launcher.themeui.request.LoadTask;
import indian.plusone.phone.launcher.thstore.ThemeUtilities;
import java.util.List;

public class SplashActivity
  extends Activity
{
  private PermissionLayout mPermission;
  private String packageName;
  private boolean starting = false;
  private boolean stepPermission = true;
  
  public SplashActivity() {}
  
  private ObjectAnimator getAnimator(View paramView, Property<View, Float> paramProperty, boolean paramBoolean)
  {
    float f2 = 1.0F;
    float f1;
    if (paramBoolean)
    {
      f1 = 0.4F;
      if (!paramBoolean) {
        break label52;
      }
    }
    for (;;)
    {
      paramView = ObjectAnimator.ofFloat(paramView, paramProperty, new float[] { f1, f2 });
      paramView.setRepeatCount(-1);
      paramView.setRepeatMode(2);
      return paramView;
      f1 = 1.0F;
      break;
      label52:
      f2 = 0.4F;
    }
  }
  
  private void launch()
  {
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.addCategory("android.intent.category.HOME");
    localIntent.setFlags(32768);
    localIntent.setFlags(268435456);
    try
    {
      localIntent.setPackage(getPackageName());
      startActivity(localIntent);
      return;
    }
    catch (Exception localException)
    {
      localIntent.setComponent(new ComponentName(getApplicationContext(), LauncherActivity.class));
      startActivity(localIntent);
      return;
    }
    finally
    {
      finish();
    }
  }
  
  public static String readLwpPackage(Context paramContext)
  {
    try
    {
      ClipboardManager localClipboardManager = (ClipboardManager)paramContext.getSystemService("clipboard");
      String str = localClipboardManager.getPrimaryClip().getItemAt(0).getText().toString().trim();
      if ((str != null) && (ThemeUtilities.get().isInstalled(paramContext, str)) && (LoadTask.getLwps(paramContext).contains(str)))
      {
        localClipboardManager.setPrimaryClip(ClipData.newPlainText("package", ""));
        return str;
      }
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  public static String readThemePackage(Context paramContext)
  {
    try
    {
      ClipboardManager localClipboardManager = (ClipboardManager)paramContext.getSystemService("clipboard");
      String str = localClipboardManager.getPrimaryClip().getItemAt(0).getText().toString().trim();
      if ((str != null) && (ThemeUtilities.get().isInstalled(paramContext, str)) && (LoadTask.getInstalledPackages(paramContext).contains(str)))
      {
        localClipboardManager.setPrimaryClip(ClipData.newPlainText("package", ""));
        return str;
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public void onBackPressed()
  {
    if (this.starting) {
      return;
    }
    super.onBackPressed();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968581);
    this.mPermission = ((PermissionLayout)findViewById(2131296309));
    this.packageName = readThemePackage(getApplicationContext());
    if ((this.packageName == null) || (this.packageName.isEmpty()))
    {
      int i = ThemeUtilities.get().getVersionCode(this, getPackageName());
      AppPref.get().setAppThemePackage(getPackageName(), i);
      ThemeManager.init(getApplicationContext());
    }
    Object localObject = (ImageView)findViewById(2131296305);
    ((RippleBackground)findViewById(2131296304)).startRippleAnimation();
    paramBundle = getAnimator((View)localObject, View.SCALE_X, true);
    localObject = getAnimator((View)localObject, View.SCALE_Y, true);
    AnimatorSet localAnimatorSet = new AnimatorSet();
    localAnimatorSet.playTogether(new Animator[] { paramBundle, localObject });
    localAnimatorSet.setDuration(800L);
    localAnimatorSet.start();
    paramBundle = (TextView)findViewById(2131296308);
    if (Build.VERSION.SDK_INT < 23) {
      ((Button)findViewById(2131296307)).setText(2131231081);
    }
    this.mPermission.onCreate(this);
    this.mPermission.setVisibility(8);
    localObject = new SpannableStringBuilder(getString(2131231069));
    ((SpannableStringBuilder)localObject).append(getString(2131231071));
    ((SpannableStringBuilder)localObject).setSpan(new ClickableSpan()
    {
      public void onClick(View paramAnonymousView)
      {
        SplashActivity.this.startActivity(new Intent(SplashActivity.this, BrowserActivity.class).putExtra("link", 1));
      }
      
      public void updateDrawState(TextPaint paramAnonymousTextPaint)
      {
        super.updateDrawState(paramAnonymousTextPaint);
        paramAnonymousTextPaint.setColor(-3158065);
      }
    }, ((SpannableStringBuilder)localObject).length() - getString(2131231071).length(), ((SpannableStringBuilder)localObject).length(), 0);
    ((SpannableStringBuilder)localObject).append(" and");
    ((SpannableStringBuilder)localObject).setSpan(new StyleSpan(3), 30, ((SpannableStringBuilder)localObject).length(), 0);
    ((SpannableStringBuilder)localObject).append(getString(2131231070));
    ((SpannableStringBuilder)localObject).setSpan(new ClickableSpan()
    {
      public void onClick(View paramAnonymousView)
      {
        SplashActivity.this.startActivity(new Intent(SplashActivity.this, BrowserActivity.class).putExtra("link", 0));
      }
      
      public void updateDrawState(TextPaint paramAnonymousTextPaint)
      {
        super.updateDrawState(paramAnonymousTextPaint);
        paramAnonymousTextPaint.setColor(-3158065);
      }
    }, ((SpannableStringBuilder)localObject).length() - getString(2131231070).length(), ((SpannableStringBuilder)localObject).length(), 0);
    paramBundle.setMovementMethod(LinkMovementMethod.getInstance());
    paramBundle.setText((CharSequence)localObject, TextView.BufferType.SPANNABLE);
  }
  
  protected void onDestroy()
  {
    this.mPermission.onDestroy();
    super.onDestroy();
  }
  
  public void onLaunch(View paramView)
  {
    if ((this.stepPermission) && (Build.VERSION.SDK_INT >= 23))
    {
      this.stepPermission = false;
      this.mPermission.setVisibility(0);
      ((Button)findViewById(2131296307)).setText(2131231081);
      return;
    }
    this.starting = true;
    int i = ThemeUtilities.get().getVersionCode(this, getPackageName());
    AppPref.get().setAppThemePackage(getPackageName(), i);
    if ((this.packageName == null) || (this.packageName.isEmpty())) {
      ThemeManager.init(getApplicationContext());
    }
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        SplashActivity.this.openActivity(SplashActivity.this.packageName);
      }
    }, 2500L);
    findViewById(2131296306).animate().alpha(0.0F).setDuration(300L).start();
  }
  
  public void onRequestPermissionsResult(int paramInt, String[] paramArrayOfString, int[] paramArrayOfInt)
  {
    this.mPermission.onRequestPermissionsResult(paramInt, paramArrayOfString, paramArrayOfInt);
  }
  
  public void openActivity(String paramString)
  {
    if ((paramString != null) && (!paramString.isEmpty()))
    {
      startActivity(new Intent(this, PluginLoadingActivity.class).setAction("indian.plusone.phone.launcher.intent.action.THEME_CHANGE").putExtra("package", paramString));
      finish();
      return;
    }
    paramString = readLwpPackage(getApplicationContext());
    if ((paramString != null) && (!paramString.isEmpty()))
    {
      startActivity(new Intent(this, PluginLoadingActivity.class).setAction("indian.plusone.phone.launcher.intent.action.LWP_CHANGE").putExtra("package", paramString));
      finish();
      return;
    }
    AppPref.get().setAppFirstLaunch(false);
    launch();
  }
}
