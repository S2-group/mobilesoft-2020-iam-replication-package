package com.appxy.tinyscanfree;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import com.appxy.adpter.PageSizeAdapter;
import com.appxy.adpter.ProcessAdapter;
import com.appxy.tools.IAPBuy;
import com.appxy.tools.Util;
import com.appxy.views.SwitchButton;
import com.flurry.android.FlurryAgent;
import io.milton.config.HttpManagerBuilder;
import io.milton.http.HttpManager;
import io.milton.http.ResourceFactory;
import io.milton.http.fs.FileSystemResourceFactory;
import io.milton.http.fs.NullSecurityManager;
import io.milton.http.fs.SimpleLockManager;
import io.milton.simpleton.SimpletonServer;
import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Activity_Setting
  extends BaseActivity
{
  private static final int RC_REQUEST = 10001;
  private Context context;
  private SharedPreferences.Editor editor;
  @SuppressLint({"HandlerLeak"})
  private Handler handler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      switch (paramAnonymousMessage.what)
      {
      default: 
        return;
      case 1: 
        Activity_Setting.this.mapp.setPort(Activity_Setting.this.mapp.getPort() + 1);
        Activity_Setting.this.scanport();
        return;
      case 2: 
        Activity_Setting.this.wifi_text.setVisibility(0);
        Activity_Setting.this.wifi_text.setText("http://" + Util.getIPAddress(true) + ":" + Activity_Setting.this.mapp.getPort());
        Object localObject = new FileSystemResourceFactory(new File(Environment.getExternalStorageDirectory() + "/MyTinyScan"), new NullSecurityManager(), "/");
        ((FileSystemResourceFactory)localObject).setAllowDirectoryBrowsing(true);
        ((FileSystemResourceFactory)localObject).setLockManager(new SimpleLockManager());
        paramAnonymousMessage = new HttpManagerBuilder();
        paramAnonymousMessage.setEnableFormAuth(false);
        paramAnonymousMessage.setResourceFactory((ResourceFactory)localObject);
        localObject = paramAnonymousMessage.buildHttpManager();
        Activity_Setting.this.mapp.setSs(new SimpletonServer((HttpManager)localObject, paramAnonymousMessage.getOuterWebdavResponseHandler(), 100, 10));
        Activity_Setting.this.mapp.getSs().setHttpPort(Integer.valueOf(Activity_Setting.this.mapp.getPort()));
        Activity_Setting.this.mapp.getSs().start();
        return;
      }
      Activity_Setting.this.mapp.setPort(Activity_Setting.this.mapp.getPort() + 1);
      Activity_Setting.this.scanport();
    }
  };
  private HashMap<String, Object> hm;
  private IAPBuy iapBuy;
  private String info = "";
  private boolean isResume = true;
  private Activity_Setting mActivity;
  private AlertDialog mDialog;
  private MyApplication mapp;
  private ArrayList<HashMap<String, Object>> mlist;
  private ArrayList<HashMap<String, Object>> mlist2;
  View.OnClickListener mlistener = new View.OnClickListener()
  {
    @SuppressLint({"DefaultLocale", "InflateParams"})
    public void onClick(View paramAnonymousView)
    {
      switch (paramAnonymousView.getId())
      {
      default: 
      case 2131755644: 
      case 2131755647: 
      case 2131755650: 
      case 2131755667: 
        do
        {
          return;
          Activity_Setting.this.editEmail();
          return;
          paramAnonymousView = Activity_Setting.this.getLayoutInflater().inflate(2130903144, null);
          if ((Activity_Setting.this.mDialog != null) && (Activity_Setting.this.mDialog.isShowing())) {
            Activity_Setting.this.mDialog.dismiss();
          }
          Activity_Setting.access$602(Activity_Setting.this, null);
          Activity_Setting.access$602(Activity_Setting.this, new AlertDialog.Builder(Activity_Setting.this.context).setTitle(Activity_Setting.this.getResources().getString(2131296380)).setView(paramAnonymousView).setNegativeButton(Activity_Setting.this.getResources().getString(2131296372), null).create());
          Activity_Setting.this.mDialog.show();
          Activity_Setting.access$702(Activity_Setting.this, new ArrayList());
          int i = 0;
          if (i < 6)
          {
            Activity_Setting.access$802(Activity_Setting.this, new HashMap());
            Activity_Setting.this.hm.put("image", Integer.valueOf(Activity_Setting.this.sizes[i]));
            Activity_Setting.this.hm.put("size", Activity_Setting.this.sizes2[i]);
            if (i == Activity_Setting.this.preferences.getInt("pagesize", 1)) {
              Activity_Setting.this.hm.put("selected", Boolean.valueOf(true));
            }
            for (;;)
            {
              Activity_Setting.this.mlist.add(Activity_Setting.this.hm);
              i += 1;
              break;
              Activity_Setting.this.hm.put("selected", Boolean.valueOf(false));
            }
          }
          paramAnonymousView = (ListView)paramAnonymousView.findViewById(2131755536);
          localObject = new PageSizeAdapter(Activity_Setting.this.context, Activity_Setting.this.mlist);
          paramAnonymousView.setAdapter((ListAdapter)localObject);
          paramAnonymousView.setOnItemClickListener(new AdapterView.OnItemClickListener()
          {
            public void onItemClick(AdapterView<?> paramAnonymous2AdapterView, View paramAnonymous2View, int paramAnonymous2Int, long paramAnonymous2Long)
            {
              Activity_Setting.this.size2.setText(Activity_Setting.this.sizes2[paramAnonymous2Int]);
              int i = 0;
              while (i < 6)
              {
                ((HashMap)Activity_Setting.this.mlist.get(i)).put("selected", Boolean.valueOf(false));
                i += 1;
              }
              ((HashMap)Activity_Setting.this.mlist.get(paramAnonymous2Int)).put("selected", Boolean.valueOf(true));
              Activity_Setting.access$1202(Activity_Setting.this, Activity_Setting.this.preferences.edit());
              Activity_Setting.this.editor.putInt("pagesize", paramAnonymous2Int);
              Activity_Setting.this.editor.commit();
              this.val$adapter.notifyDataSetChanged();
              Activity_Setting.this.mDialog.dismiss();
            }
          });
          return;
          paramAnonymousView = Activity_Setting.this.getLayoutInflater().inflate(2130903157, null, false);
          if ((Activity_Setting.this.mDialog != null) && (Activity_Setting.this.mDialog.isShowing())) {
            Activity_Setting.this.mDialog.dismiss();
          }
          Activity_Setting.access$602(Activity_Setting.this, null);
          Activity_Setting.access$602(Activity_Setting.this, new AlertDialog.Builder(Activity_Setting.this.context).setTitle(Activity_Setting.this.getResources().getString(2131296381)).setView(paramAnonymousView).setNegativeButton(Activity_Setting.this.getResources().getString(2131296372), null).create());
          Activity_Setting.this.mDialog.show();
          Activity_Setting.access$1302(Activity_Setting.this, new ArrayList());
          i = 0;
          if (i < 5)
          {
            Activity_Setting.access$802(Activity_Setting.this, new HashMap());
            Activity_Setting.this.hm.put("name", Activity_Setting.this.processName[i]);
            if (i == Activity_Setting.this.preferences.getInt("processid", 2)) {
              Activity_Setting.this.hm.put("selected", Boolean.valueOf(true));
            }
            for (;;)
            {
              Activity_Setting.this.mlist2.add(Activity_Setting.this.hm);
              i += 1;
              break;
              Activity_Setting.this.hm.put("selected", Boolean.valueOf(false));
            }
          }
          paramAnonymousView = (ListView)paramAnonymousView.findViewById(2131755608);
          localObject = new ProcessAdapter(Activity_Setting.this.context, Activity_Setting.this.mlist2);
          paramAnonymousView.setAdapter((ListAdapter)localObject);
          paramAnonymousView.setOnItemClickListener(new AdapterView.OnItemClickListener()
          {
            public void onItemClick(AdapterView<?> paramAnonymous2AdapterView, View paramAnonymous2View, int paramAnonymous2Int, long paramAnonymous2Long)
            {
              Activity_Setting.this.process2.setText(Activity_Setting.this.processName[paramAnonymous2Int]);
              int i = 0;
              while (i < 5)
              {
                ((HashMap)Activity_Setting.this.mlist2.get(i)).put("selected", Boolean.valueOf(false));
                i += 1;
              }
              ((HashMap)Activity_Setting.this.mlist2.get(paramAnonymous2Int)).put("selected", Boolean.valueOf(true));
              Activity_Setting.access$1202(Activity_Setting.this, Activity_Setting.this.preferences.edit());
              Activity_Setting.this.editor.putInt("processid", paramAnonymous2Int);
              Activity_Setting.this.editor.commit();
              this.val$adapter2.notifyDataSetChanged();
              Activity_Setting.this.mDialog.dismiss();
            }
          });
          return;
          Activity_Setting.this.fillinfo();
          localObject = new Intent("android.intent.action.SEND");
          ((Intent)localObject).setType("plain/text");
          if (Activity_Setting.this.getSharedPreferencesForCurrentUser().getBoolean("Amazon", false)) {}
          paramAnonymousView = new ArrayList();
          localObject = Activity_Setting.this.getPackageManager().queryIntentActivities((Intent)localObject, 0);
        } while (((List)localObject).isEmpty());
        Object localObject = ((List)localObject).iterator();
        while (((Iterator)localObject).hasNext())
        {
          ResolveInfo localResolveInfo = (ResolveInfo)((Iterator)localObject).next();
          Intent localIntent = new Intent("android.intent.action.SEND_MULTIPLE");
          localIntent.setType("plain/text");
          localIntent.putExtra("android.intent.extra.SUBJECT", "TinyScanner");
          if ((localResolveInfo.activityInfo.packageName.toLowerCase().contains("mail")) || (localResolveInfo.activityInfo.name.toLowerCase().contains("mail")) || (localResolveInfo.activityInfo.packageName.toLowerCase().contains("inbox")) || (localResolveInfo.activityInfo.packageName.toLowerCase().contains("com.fsck.k9")) || (localResolveInfo.activityInfo.name.toLowerCase().contains("blue")) || (localResolveInfo.activityInfo.packageName.toLowerCase().contains("outlook")))
          {
            localIntent.putExtra("android.intent.extra.EMAIL", new String[] { "tinyscan.a@appxy.com" });
            localIntent.putExtra("android.intent.extra.SUBJECT", "TinyScanner Feedback");
            localIntent.putExtra("android.intent.extra.TEXT", Activity_Setting.this.info);
            localIntent.setPackage(localResolveInfo.activityInfo.packageName);
            paramAnonymousView.add(localIntent);
          }
        }
        if (paramAnonymousView.size() > 0)
        {
          localObject = Intent.createChooser((Intent)paramAnonymousView.remove(0), Activity_Setting.this.getString(2131296491));
          ((Intent)localObject).putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[])paramAnonymousView.toArray(new Parcelable[0]));
          Activity_Setting.this.startActivityForResult((Intent)localObject, 3);
          return;
        }
        Toast.makeText(Activity_Setting.this.context, "Can't find mail application", 0).show();
        return;
      case 2131755666: 
        paramAnonymousView = new Intent(Activity_Setting.this.context, Activity_FaqTitle.class);
        Activity_Setting.this.startActivity(paramAnonymousView);
        return;
      case 2131755664: 
        if (Activity_Setting.this.mapp.isPad())
        {
          Activity_Setting.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://www.appxy.com/tinyscan-add-a-document-android-tablet/")));
          return;
        }
        Activity_Setting.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://www.appxy.com/tinyscan-add-a-document-for-android-phone/")));
        return;
      case 2131755662: 
        Activity_Setting.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://www.appxy.com/scannerprivacy/")));
        return;
      }
      paramAnonymousView = new Intent(Activity_Setting.this.context, Activity_WifiHelp.class);
      Activity_Setting.this.startActivity(paramAnonymousView);
    }
  };
  View.OnTouchListener mlistener2 = new View.OnTouchListener()
  {
    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
    {
      if (paramAnonymousMotionEvent.getAction() == 0) {
        paramAnonymousView.setBackgroundColor(Color.rgb(223, 223, 223));
      }
      do
      {
        return false;
        if (paramAnonymousMotionEvent.getAction() == 1)
        {
          paramAnonymousView.setBackgroundColor(0);
          return false;
        }
      } while (paramAnonymousMotionEvent.getAction() != 2);
      paramAnonymousView.setBackgroundColor(Color.rgb(223, 223, 223));
      return false;
    }
  };
  private TextView myemail;
  private SharedPreferences preferences;
  private TextView process2;
  private String[] processName;
  private RelativeLayout relativelayout_privacypolicy;
  private RelativeLayout rl0;
  private RelativeLayout rl1;
  private RelativeLayout rl2;
  private RelativeLayout rl3;
  private RelativeLayout rl4;
  private RelativeLayout rl6;
  private RelativeLayout rl7;
  private Switch s;
  private Switch s2;
  private SwitchButton sb;
  private SwitchButton sb2;
  private Toolbar setting_toolbar;
  private RelativeLayout settings_removeads_relativelayout;
  private TextView settings_removeads_text;
  private TextView size2;
  private int[] sizes;
  private String[] sizes2;
  private TextView wifi_text;
  
  public Activity_Setting() {}
  
  private SharedPreferences getSharedPreferencesForCurrentUser()
  {
    return getSharedPreferences(this.mapp.getCurrentUser(), 0);
  }
  
  @SuppressLint({"InflateParams"})
  private void showProIapBuyDialog()
  {
    Object localObject = LayoutInflater.from(this.context).inflate(2130903104, null);
    final AlertDialog localAlertDialog = new AlertDialog.Builder(this.context).setTitle(getString(2131296433)).setView((View)localObject).create();
    localAlertDialog.show();
    ImageView localImageView = (ImageView)((View)localObject).findViewById(2131755431);
    localObject = (ImageView)((View)localObject).findViewById(2131755433);
    localImageView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        localAlertDialog.dismiss();
        Activity_Setting.this.iapBuy.IAP_Buy();
      }
    });
    ((ImageView)localObject).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        localAlertDialog.dismiss();
        Object localObject = Activity_Setting.this.getPackageManager().getInstalledApplications(0);
        int j = ((List)localObject).size();
        paramAnonymousView = null;
        int i = 0;
        while (i < j)
        {
          if (((ApplicationInfo)((List)localObject).get(i)).packageName.equals("com.android.vending")) {
            paramAnonymousView = (ApplicationInfo)((List)localObject).get(i);
          }
          i += 1;
        }
        try
        {
          localObject = new Intent("android.intent.action.VIEW");
          ((Intent)localObject).setData(Uri.parse("market://details?id=com.appxy.tinyscan&hl=en"));
          ((Intent)localObject).setPackage(paramAnonymousView.packageName);
          Activity_Setting.this.startActivity((Intent)localObject);
          return;
        }
        catch (Exception paramAnonymousView)
        {
          Activity_Setting.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=com.appxy.tinyscan&hl=en")));
        }
      }
    });
  }
  
  private void thankBuy(String paramString)
  {
    new AlertDialog.Builder(this.mActivity).setMessage(paramString).setPositiveButton("OK", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.cancel();
      }
    }).create().show();
  }
  
  @SuppressLint({"InflateParams"})
  public void editEmail()
  {
    final View localView = LayoutInflater.from(this.context).inflate(2130903161, null);
    EditText localEditText = (EditText)localView.findViewById(2131755630);
    localEditText.setInputType(33);
    localEditText.setSelectAllOnFocus(true);
    localEditText.setText(this.preferences.getString("email", ""));
    new AlertDialog.Builder(this.context).setTitle(getString(2131296457)).setView(localView).setPositiveButton(getString(2131296486), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        EditText localEditText = (EditText)localView.findViewById(2131755630);
        String str = localEditText.getText().toString();
        if (Activity_Setting.this.isEmail(str))
        {
          Activity_Setting.access$1202(Activity_Setting.this, Activity_Setting.this.preferences.edit());
          Activity_Setting.this.editor.putString("email", localEditText.getText().toString());
          Activity_Setting.this.editor.commit();
          paramAnonymousDialogInterface.dismiss();
          Activity_Setting.this.myemail.setText(localEditText.getText().toString());
          return;
        }
        paramAnonymousDialogInterface.dismiss();
        new AlertDialog.Builder(Activity_Setting.this.context).setTitle(Activity_Setting.this.getString(2131296530)).setMessage(Activity_Setting.this.getString(2131296442)).setPositiveButton(Activity_Setting.this.getString(2131296464), null).create().show();
      }
    }).setNegativeButton(getString(2131296372), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.dismiss();
      }
    }).create().show();
  }
  
  public void fillinfo()
  {
    this.info = "";
    this.info = (this.info + "Model : " + Build.MODEL + "\n");
    this.info = (this.info + "Release : " + Build.VERSION.RELEASE + "\n");
    this.info = (this.info + "App : " + getVersion() + "\n");
  }
  
  public String getVersion()
  {
    Object localObject2 = getPackageManager();
    Object localObject1 = null;
    try
    {
      localObject2 = ((PackageManager)localObject2).getPackageInfo(getPackageName(), 0);
      localObject1 = localObject2;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;)
      {
        localNameNotFoundException.printStackTrace();
      }
    }
    return localObject1.versionName;
  }
  
  public boolean isEmail(String paramString)
  {
    return Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$").matcher(paramString).matches();
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    switch (paramInt1)
    {
    }
    do
    {
      return;
      if (paramInt2 == 1)
      {
        this.editor.putBoolean("isSetPass", true);
        this.editor.commit();
        return;
      }
      this.sb.setChecked(false);
      this.editor.putBoolean("isSetPass", false);
      this.editor.commit();
      return;
      if (paramInt2 == 1)
      {
        this.editor.putBoolean("isSetPass", true);
        this.editor.commit();
        return;
      }
      this.editor.putBoolean("isSetPass", false);
      this.editor.commit();
      return;
    } while ((paramInt2 != -1) || (paramInt1 != 10001));
    if (this.mapp.getAdvOrChargeOrNormal() == 1) {
      FlurryAgent.logEvent("7_UserAds_IAP");
    }
    for (;;)
    {
      paramIntent = getSharedPreferences("msp", 0).edit();
      paramIntent.putBoolean("GOOGLE_IAP", true);
      this.mapp.setIsBuyGoogleAds(true);
      this.mapp.setAdvOrChargeOrNormal(3);
      paramIntent.commit();
      paramIntent = new Message();
      paramIntent.what = 10020;
      this.handler.sendMessage(paramIntent);
      thankBuy("Thank you for upgrading to pro! ");
      return;
      if (this.mapp.getAdvOrChargeOrNormal() == 2) {
        FlurryAgent.logEvent("7_UserDoc_IAP");
      }
    }
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
  }
  
  @SuppressLint({"NewApi"})
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    this.mActivity = this;
    this.context = this;
    this.mapp = MyApplication.getApplication(this.context);
    if (this.mapp.isPad())
    {
      setContentView(2130903166);
      this.setting_toolbar = ((Toolbar)findViewById(2131755637));
      setSupportActionBar(this.setting_toolbar);
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      getSupportActionBar().setTitle(getResources().getString(2131296450));
      paramBundle = new DisplayMetrics();
      getWindowManager().getDefaultDisplay().getMetrics(paramBundle);
      this.sizes = new int[] { 2130838130, 2130838126, 2130838129, 2130838125, 2130838127, 2130838128 };
      this.sizes2 = new String[] { "Letter", "A4", "Legal", "A3", "A5", "Business Card" };
      this.preferences = getSharedPreferences("TinyScanPro", 0);
      this.editor = this.preferences.edit();
      this.processName = new String[] { getString(2131296376), getString(2131296472), getString(2131296369), getString(2131296417), getString(2131296443) };
      this.settings_removeads_relativelayout = ((RelativeLayout)findViewById(2131755641));
      this.settings_removeads_text = ((TextView)findViewById(2131755643));
      this.settings_removeads_text.setVisibility(8);
      if ((this.mapp.getAdvOrChargeOrNormal() != 1) || (!this.preferences.getBoolean("CountryIAP_ads_user_chayeads", false))) {
        break label894;
      }
      this.settings_removeads_relativelayout.setVisibility(0);
    }
    for (;;)
    {
      this.settings_removeads_relativelayout.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          Activity_Setting.this.showProIapBuyDialog();
        }
      });
      this.rl0 = ((RelativeLayout)findViewById(2131755644));
      this.rl0.setOnClickListener(this.mlistener);
      this.rl1 = ((RelativeLayout)findViewById(2131755647));
      this.rl1.setOnClickListener(this.mlistener);
      this.rl2 = ((RelativeLayout)findViewById(2131755650));
      this.rl2.setOnClickListener(this.mlistener);
      this.rl3 = ((RelativeLayout)findViewById(2131755667));
      this.rl3.setOnClickListener(this.mlistener);
      this.rl4 = ((RelativeLayout)findViewById(2131755666));
      this.rl4.setOnClickListener(this.mlistener);
      this.relativelayout_privacypolicy = ((RelativeLayout)findViewById(2131755662));
      this.relativelayout_privacypolicy.setOnClickListener(this.mlistener);
      this.rl6 = ((RelativeLayout)findViewById(2131755664));
      this.rl6.setOnClickListener(this.mlistener);
      this.rl7 = ((RelativeLayout)findViewById(2131755658));
      this.rl7.setOnClickListener(this.mlistener);
      this.myemail = ((TextView)findViewById(2131755646));
      this.myemail.setText(this.preferences.getString("email", ""));
      this.size2 = ((TextView)findViewById(2131755649));
      this.size2.setText(this.sizes2[this.preferences.getInt("pagesize", 1)] + "");
      this.process2 = ((TextView)findViewById(2131755652));
      this.process2.setText(this.processName[this.preferences.getInt("processid", 2)] + "");
      this.wifi_text = ((TextView)findViewById(2131755660));
      if (Build.VERSION.SDK_INT >= 21) {
        break label906;
      }
      this.sb = ((SwitchButton)findViewById(2131755654));
      this.sb.setClickable(true);
      this.sb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
      {
        public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
        {
          if (Activity_Setting.this.isResume) {
            return;
          }
          if (Activity_Setting.this.preferences.getBoolean("isSetPass", false))
          {
            paramAnonymousCompoundButton = new Intent(Activity_Setting.this.context, Activity_ChangePass.class);
            ((Activity)Activity_Setting.this.context).startActivity(paramAnonymousCompoundButton);
            return;
          }
          paramAnonymousCompoundButton = new Intent(Activity_Setting.this.context, Activity_SetPass.class);
          ((Activity)Activity_Setting.this.context).startActivityForResult(paramAnonymousCompoundButton, 2);
        }
      });
      this.sb2 = ((SwitchButton)findViewById(2131755657));
      if (this.mapp.getSs() != null)
      {
        this.sb2.setChecked(true);
        this.wifi_text.setVisibility(0);
        this.wifi_text.setText("http://" + Util.getIPAddress(true) + ":" + this.mapp.getPort());
      }
      this.sb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
      {
        public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
        {
          if (paramAnonymousCompoundButton.isChecked())
          {
            Activity_Setting.this.scanport();
            return;
          }
          Activity_Setting.this.wifi_text.setVisibility(4);
          if (Activity_Setting.this.mapp.getSs() != null) {
            Activity_Setting.this.mapp.getSs().stop();
          }
          Activity_Setting.this.mapp.setSs(null);
        }
      });
      return;
      setRequestedOrientation(1);
      break;
      label894:
      this.settings_removeads_relativelayout.setVisibility(8);
    }
    label906:
    this.s = ((Switch)findViewById(2131755669));
    this.s.setClickable(true);
    this.s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
    {
      public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
      {
        if (Activity_Setting.this.isResume) {
          return;
        }
        if (Activity_Setting.this.preferences.getBoolean("isSetPass", false))
        {
          paramAnonymousCompoundButton = new Intent(Activity_Setting.this.context, Activity_ChangePass.class);
          ((Activity)Activity_Setting.this.context).startActivity(paramAnonymousCompoundButton);
          return;
        }
        paramAnonymousCompoundButton = new Intent(Activity_Setting.this.context, Activity_SetPass.class);
        ((Activity)Activity_Setting.this.context).startActivityForResult(paramAnonymousCompoundButton, 2);
      }
    });
    this.s2 = ((Switch)findViewById(2131755671));
    if (this.mapp.getSs() != null)
    {
      this.s2.setChecked(true);
      this.wifi_text.setVisibility(0);
      this.wifi_text.setText("http://" + Util.getIPAddress(true) + ":" + this.mapp.getPort());
    }
    this.s2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
    {
      public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
      {
        if (paramAnonymousCompoundButton.isChecked())
        {
          Activity_Setting.this.scanport();
          return;
        }
        Activity_Setting.this.wifi_text.setVisibility(4);
        if (Activity_Setting.this.mapp.getSs() != null) {
          Activity_Setting.this.mapp.getSs().stop();
        }
        Activity_Setting.this.mapp.setSs(null);
      }
    });
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    return super.onCreateOptionsMenu(paramMenu);
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    this.sizes = null;
    this.sizes2 = null;
    this.processName = null;
    if (this.mlist != null) {
      this.mlist.clear();
    }
    this.mlist = null;
    if (this.mlist2 != null) {
      this.mlist2.clear();
    }
    this.mlist2 = null;
    if (this.hm != null) {
      this.hm.clear();
    }
    this.hm = null;
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4)
    {
      finish();
      return true;
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (paramMenuItem.getItemId() == 16908332) {
      finish();
    }
    return super.onOptionsItemSelected(paramMenuItem);
  }
  
  protected void onPause()
  {
    super.onPause();
    if ((this.mDialog != null) && (this.mDialog.isShowing())) {
      this.mDialog.dismiss();
    }
    this.mDialog = null;
  }
  
  @SuppressLint({"NewApi"})
  protected void onResume()
  {
    super.onResume();
    if ((this.mapp.getAdvOrChargeOrNormal() == 1) && (this.preferences.getBoolean("CountryIAP_ads_user_chayeads", false)))
    {
      this.iapBuy = new IAPBuy(this.mActivity, this.settings_removeads_text, this.settings_removeads_relativelayout, null);
      this.iapBuy.buyGoogleAdvPro();
    }
    this.isResume = true;
    if (Build.VERSION.SDK_INT < 21) {
      if (this.preferences.getBoolean("isSetPass", false)) {
        this.sb.setChecked(true);
      }
    }
    for (;;)
    {
      this.isResume = false;
      return;
      this.sb.setChecked(false);
      continue;
      if (this.preferences.getBoolean("isSetPass", false)) {
        this.s.setChecked(true);
      } else {
        this.s.setChecked(false);
      }
    }
  }
  
  public void scanport()
  {
    new Thread()
    {
      public void run()
      {
        try
        {
          new Socket().connect(new InetSocketAddress(Util.getIPAddress(true), Activity_Setting.this.mapp.getPort()), 2000);
          Activity_Setting.this.handler.sendEmptyMessage(1);
          Log.i("TAG", "1111===============");
          return;
        }
        catch (UnknownHostException localUnknownHostException)
        {
          Log.i("TAG", "222===============");
          Activity_Setting.this.handler.sendEmptyMessage(2);
          return;
        }
        catch (IOException localIOException)
        {
          Log.i("TAG", "3333===============");
          Activity_Setting.this.handler.sendEmptyMessage(2);
        }
      }
    }.start();
  }
}
