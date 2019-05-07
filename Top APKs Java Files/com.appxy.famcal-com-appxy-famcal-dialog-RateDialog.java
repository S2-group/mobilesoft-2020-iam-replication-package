package com.appxy.famcal.dialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.appxy.famcal.activity.MyApplication;
import com.appxy.famcal.impletems.ActivityInterface;
import com.google.android.gms.common.GooglePlayServicesUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RateDialog
  extends DialogFragment
  implements ActivityInterface
{
  private static final String DEFAULT_PREFERENCE_CANCEL_COUNT = "cancel_count";
  private Activity context;
  private String info;
  private AlertDialog mDialog;
  private SharedPreferences mPreferences;
  
  public RateDialog() {}
  
  private boolean checkGooglePlayServicesAvailable()
  {
    return !GooglePlayServicesUtil.isUserRecoverableError(GooglePlayServicesUtil.isGooglePlayServicesAvailable(this.context));
  }
  
  @SuppressLint({"InflateParams"})
  private void showRateFeedback()
  {
    Object localObject = this.context.getLayoutInflater().inflate(2131493064, null);
    final EditText localEditText = (EditText)((View)localObject).findViewById(2131297086);
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this.context);
    localBuilder.setTitle("").setView((View)localObject).setPositiveButton(this.context.getResources().getString(2131624597), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.cancel();
        RateDialog.this.fillinfo();
        Object localObject = new Intent("android.intent.action.SEND");
        ((Intent)localObject).setType("plain/text");
        paramAnonymousDialogInterface = new ArrayList();
        localObject = RateDialog.this.context.getPackageManager().queryIntentActivities((Intent)localObject, 0);
        if (!((List)localObject).isEmpty())
        {
          localObject = ((List)localObject).iterator();
          while (((Iterator)localObject).hasNext())
          {
            ResolveInfo localResolveInfo = (ResolveInfo)((Iterator)localObject).next();
            Intent localIntent = new Intent("android.intent.action.SEND_MULTIPLE");
            localIntent.setType("plain/text");
            localIntent.putExtra("android.intent.extra.SUBJECT", "Famcal");
            if ((localResolveInfo.activityInfo.packageName.toLowerCase().contains("mail")) || (localResolveInfo.activityInfo.name.toLowerCase().contains("mail")) || (localResolveInfo.activityInfo.packageName.toLowerCase().contains("inbox")) || (localResolveInfo.activityInfo.packageName.toLowerCase().contains("com.fsck.k9")) || (localResolveInfo.activityInfo.name.toLowerCase().contains("blue")))
            {
              localIntent.putExtra("android.intent.extra.EMAIL", new String[] { "famcal.a@appxy.com" });
              localIntent.putExtra("android.intent.extra.SUBJECT", "FamCal Feedback");
              StringBuilder localStringBuilder = new StringBuilder();
              localStringBuilder.append(RateDialog.this.info);
              localStringBuilder.append(localEditText.getText().toString());
              localIntent.putExtra("android.intent.extra.TEXT", localStringBuilder.toString());
              localIntent.setPackage(localResolveInfo.activityInfo.packageName);
              paramAnonymousDialogInterface.add(localIntent);
            }
          }
          if (paramAnonymousDialogInterface.size() > 0)
          {
            localObject = Intent.createChooser((Intent)paramAnonymousDialogInterface.remove(0), RateDialog.this.context.getString(2131624642));
            ((Intent)localObject).putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[])paramAnonymousDialogInterface.toArray(new Parcelable[0]));
            RateDialog.this.context.startActivityForResult((Intent)localObject, 3);
            return;
          }
          Toast.makeText(RateDialog.this.context, "Can't find mail application", 0).show();
        }
      }
    }).setNegativeButton(this.context.getResources().getString(2131624094), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.cancel();
      }
    });
    localObject = localBuilder.create();
    ((AlertDialog)localObject).setCanceledOnTouchOutside(false);
    ((AlertDialog)localObject).show();
    ((AlertDialog)localObject).setOnKeyListener(new DialogInterface.OnKeyListener()
    {
      public boolean onKey(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt, KeyEvent paramAnonymousKeyEvent)
      {
        return (paramAnonymousInt == 4) && (paramAnonymousKeyEvent.getRepeatCount() == 0);
      }
    });
  }
  
  private void showThanksRate(Activity paramActivity)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(paramActivity);
    localBuilder.setTitle(paramActivity.getResources().getString(2131624617)).setMessage(paramActivity.getResources().getString(2131624502)).setPositiveButton(this.context.getResources().getString(2131624429), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.cancel();
        if (MyApplication.banben != 1) {
          return;
        }
        paramAnonymousDialogInterface = RateDialog.this.context.getPackageManager();
        paramAnonymousInt = 0;
        Object localObject = paramAnonymousDialogInterface.getInstalledApplications(0);
        int i = ((List)localObject).size();
        paramAnonymousDialogInterface = null;
        while (paramAnonymousInt < i)
        {
          if (((ApplicationInfo)((List)localObject).get(paramAnonymousInt)).packageName.equals("com.android.vending")) {
            paramAnonymousDialogInterface = (ApplicationInfo)((List)localObject).get(paramAnonymousInt);
          }
          paramAnonymousInt += 1;
        }
        if (paramAnonymousDialogInterface != null)
        {
          localObject = new Intent("android.intent.action.VIEW");
          ((Intent)localObject).setData(Uri.parse("market://details?id=com.appxy.famcal&hl=en"));
          ((Intent)localObject).setPackage(paramAnonymousDialogInterface.packageName);
          RateDialog.this.context.startActivity((Intent)localObject);
          return;
        }
        RateDialog.this.context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.appxy.famcal")));
      }
    }).setNegativeButton(paramActivity.getResources().getString(2131624418), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.cancel();
      }
    });
    paramActivity = localBuilder.create();
    paramActivity.setCanceledOnTouchOutside(false);
    paramActivity.show();
    paramActivity.setOnKeyListener(new DialogInterface.OnKeyListener()
    {
      public boolean onKey(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt, KeyEvent paramAnonymousKeyEvent)
      {
        return (paramAnonymousInt == 4) && (paramAnonymousKeyEvent.getRepeatCount() == 0);
      }
    });
  }
  
  public void fillinfo()
  {
    this.info = "";
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(this.info);
    localStringBuilder.append("Model : ");
    localStringBuilder.append(Build.MODEL);
    localStringBuilder.append("\n");
    this.info = localStringBuilder.toString();
    localStringBuilder = new StringBuilder();
    localStringBuilder.append(this.info);
    localStringBuilder.append("Release : ");
    localStringBuilder.append(Build.VERSION.RELEASE);
    localStringBuilder.append("\n");
    this.info = localStringBuilder.toString();
    localStringBuilder = new StringBuilder();
    localStringBuilder.append(this.info);
    localStringBuilder.append("App : ");
    localStringBuilder.append(getVersion());
    localStringBuilder.append("\n");
    this.info = localStringBuilder.toString();
  }
  
  public void fragmentrefresh(boolean paramBoolean) {}
  
  public String getVersion()
  {
    Object localObject1 = this.context.getPackageManager();
    Object localObject2;
    try
    {
      localObject1 = ((PackageManager)localObject1).getPackageInfo(this.context.getPackageName(), 0);
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      localNameNotFoundException.printStackTrace();
      localObject2 = null;
    }
    return localObject2.versionName;
  }
  
  public void onAttach(Activity paramActivity)
  {
    super.onAttach(paramActivity);
    this.context = paramActivity;
    this.mPreferences = paramActivity.getSharedPreferences("app_rater", 0);
  }
  
  public Dialog onCreateDialog(final Bundle paramBundle)
  {
    Object localObject1 = new AlertDialog.Builder(this.context);
    paramBundle = LayoutInflater.from(this.context).inflate(2131493085, null);
    ((AlertDialog.Builder)localObject1).setPositiveButton(this.context.getResources().getString(2131624505), null).setNegativeButton(this.context.getResources().getString(2131624418), null);
    this.mDialog = ((AlertDialog.Builder)localObject1).create();
    this.mDialog.setView(paramBundle);
    this.mDialog.show();
    this.mDialog.setCanceledOnTouchOutside(false);
    Object localObject2 = this.mDialog.getButton(-1);
    Object localObject3 = this.mDialog.getButton(-2);
    localObject1 = this.mPreferences.edit();
    this.mDialog.setOnKeyListener(new DialogInterface.OnKeyListener()
    {
      public boolean onKey(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt, KeyEvent paramAnonymousKeyEvent)
      {
        return (paramAnonymousInt == 4) && (paramAnonymousKeyEvent.getRepeatCount() == 0);
      }
    });
    ((Button)localObject2).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if ((RateDialog.this.mDialog != null) && (RateDialog.this.mDialog.isShowing())) {
          RateDialog.this.mDialog.dismiss();
        }
        this.val$e.putBoolean("flag_dont_show", false);
        this.val$e.putInt("cancel_count", 1);
        this.val$e.putLong("launch_count", 0L);
        this.val$e.putLong("first_launch_time", System.currentTimeMillis());
        this.val$e.commit();
      }
    });
    ((Button)localObject3).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        int i = RateDialog.this.mPreferences.getInt("cancel_count", 0);
        if (i < 1)
        {
          this.val$e.putBoolean("flag_dont_show", false);
          this.val$e.putInt("cancel_count", i + 1);
          this.val$e.putLong("launch_count", 0L);
          this.val$e.putLong("first_launch_time", System.currentTimeMillis());
        }
        else
        {
          this.val$e.putBoolean("flag_dont_show", true);
        }
        this.val$e.commit();
        if ((RateDialog.this.mDialog != null) && (RateDialog.this.mDialog.isShowing())) {
          RateDialog.this.mDialog.dismiss();
        }
      }
    });
    localObject2 = (ImageView)paramBundle.findViewById(2131296821);
    localObject3 = (ImageView)paramBundle.findViewById(2131296822);
    final ImageView localImageView1 = (ImageView)paramBundle.findViewById(2131296823);
    final ImageView localImageView2 = (ImageView)paramBundle.findViewById(2131296824);
    paramBundle = (ImageView)paramBundle.findViewById(2131296825);
    ((ImageView)localObject2).setOnTouchListener(new View.OnTouchListener()
    {
      @SuppressLint({"ClickableViewAccessibility"})
      public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
      {
        switch (paramAnonymousMotionEvent.getAction())
        {
        default: 
          return true;
        case 1: 
          RateDialog.this.mDialog.cancel();
          this.val$e.putBoolean("flag_dont_show", true);
          this.val$e.commit();
          RateDialog.this.showRateFeedback();
          return true;
        }
        this.val$imageview_rate1.setSelected(true);
        return true;
      }
    });
    ((ImageView)localObject3).setOnTouchListener(new View.OnTouchListener()
    {
      public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
      {
        switch (paramAnonymousMotionEvent.getAction())
        {
        default: 
          return true;
        case 1: 
          RateDialog.this.mDialog.cancel();
          this.val$imageview_rate1.setSelected(true);
          this.val$e.putBoolean("flag_dont_show", true);
          this.val$e.commit();
          RateDialog.this.showRateFeedback();
          return true;
        }
        this.val$imageview_rate1.setSelected(true);
        this.val$imageview_rate2.setSelected(true);
        return true;
      }
    });
    localImageView1.setOnTouchListener(new View.OnTouchListener()
    {
      public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
      {
        switch (paramAnonymousMotionEvent.getAction())
        {
        default: 
          return true;
        case 1: 
          RateDialog.this.mDialog.cancel();
          this.val$e.putBoolean("flag_dont_show", true);
          this.val$e.commit();
          this.val$imageview_rate1.setSelected(true);
          this.val$imageview_rate2.setSelected(true);
          RateDialog.this.showRateFeedback();
          return true;
        }
        this.val$imageview_rate1.setSelected(true);
        this.val$imageview_rate2.setSelected(true);
        localImageView1.setSelected(true);
        return true;
      }
    });
    localImageView2.setOnTouchListener(new View.OnTouchListener()
    {
      public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
      {
        switch (paramAnonymousMotionEvent.getAction())
        {
        default: 
          return true;
        case 1: 
          RateDialog.this.mDialog.cancel();
          this.val$imageview_rate1.setSelected(true);
          this.val$imageview_rate2.setSelected(true);
          localImageView1.setSelected(true);
          this.val$e.putBoolean("flag_dont_show", true);
          this.val$e.commit();
          RateDialog.this.showRateFeedback();
          return true;
        }
        this.val$imageview_rate1.setSelected(true);
        this.val$imageview_rate2.setSelected(true);
        localImageView1.setSelected(true);
        localImageView2.setSelected(true);
        return true;
      }
    });
    paramBundle.setOnTouchListener(new View.OnTouchListener()
    {
      public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
      {
        switch (paramAnonymousMotionEvent.getAction())
        {
        default: 
          return true;
        case 1: 
          RateDialog.this.mDialog.cancel();
          this.val$e.putBoolean("flag_dont_show", true);
          this.val$e.commit();
          this.val$imageview_rate1.setSelected(true);
          this.val$imageview_rate2.setSelected(true);
          localImageView1.setSelected(true);
          localImageView2.setSelected(true);
          RateDialog.this.showThanksRate(RateDialog.this.context);
          return true;
        }
        this.val$imageview_rate1.setSelected(true);
        this.val$imageview_rate2.setSelected(true);
        localImageView1.setSelected(true);
        localImageView2.setSelected(true);
        paramBundle.setSelected(true);
        return true;
      }
    });
    return this.mDialog;
  }
  
  public void onResume()
  {
    super.onResume();
  }
}
