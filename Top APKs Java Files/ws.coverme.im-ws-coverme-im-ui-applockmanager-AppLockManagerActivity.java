package ws.coverme.im.ui.applockmanager;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import ws.coverme.im.dll.SharedPreferencesManager;
import ws.coverme.im.model.KexinData;
import ws.coverme.im.model.applockmanager.LockAppData;
import ws.coverme.im.service.CMCoreService;
import ws.coverme.im.ui.graphical_psw.util.GraphicalUtil;
import ws.coverme.im.ui.others.advancedversion.AdvancedVersionActivity;
import ws.coverme.im.ui.others.advancedversion.util.PremiumUtil;
import ws.coverme.im.ui.others.vault.VaultAdvancedVersionActivity;
import ws.coverme.im.ui.view.BaseActivity;
import ws.coverme.im.ui.view.MyDialog;
import ws.coverme.im.ui.view.StretchListView;
import ws.coverme.im.util.AppInstalledUtil;
import ws.coverme.im.util.CMProgressDialog;
import ws.coverme.im.util.CMTracer;
import ws.coverme.im.util.ClickTimeSpanUtil;
import ws.coverme.im.util.OtherHelper;

public class AppLockManagerActivity
  extends BaseActivity
  implements View.OnClickListener, AdapterView.OnItemClickListener
{
  public static final int DIALOG_FIRSTTIME_MSG = 2;
  public static final int DIALOG_INVALID_MSG = 3;
  public static final int DIALOG_LEFTDAYS_MSG = 4;
  public static final int DIALOG_ROOT_MSG = 0;
  public static final int DIALOG_ROOT_TO_UNROOT_MSG = 1;
  public static final int VIEW_ALL = 2;
  public static final int VIEW_PART = 1;
  private int RootStatus = 0;
  private TextView actTip;
  private TextView actTitle;
  private TextView actTryTip;
  AppListAdapter appListAdapter;
  private StretchListView appListView;
  private RelativeLayout applock_tip_relativelayout;
  ArrayList<String> appnamesdata;
  ArrayList<HashMap<String, Object>> appsdata;
  private boolean canClickItem = false;
  private int curUserId = 0;
  public int displayHeight;
  public int displayWidth;
  private long firstUseTime = 0L;
  boolean isDestory = false;
  private boolean isExpired = false;
  private boolean isPayed = false;
  final Handler lockAppHandler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      switch (paramAnonymousMessage.what)
      {
      }
      for (;;)
      {
        super.handleMessage(paramAnonymousMessage);
        return;
        String str = (String)paramAnonymousMessage.obj;
        AppLockManagerActivity.this.RunApp(str, true);
        SharedPreferencesManager.setSharedPreferences("applock_openpackname", str, AppLockManagerActivity.this);
      }
    }
  };
  AppLockDataHandler lockhandler = null;
  private Handler mHandler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      if (AppLockManagerActivity.this.isDestory) {}
      do
      {
        return;
        AppLockManagerActivity.this.appListAdapter = new AppLockManagerActivity.AppListAdapter(AppLockManagerActivity.this, AppLockManagerActivity.this);
        AppLockManagerActivity.this.appListAdapter.resetChecked();
        AppLockManagerActivity.this.appListView.setAdapter(AppLockManagerActivity.this.appListAdapter);
      } while ((AppLockManagerActivity.this.prodialog == null) || (!AppLockManagerActivity.this.prodialog.isShowing()) || (AppLockManagerActivity.this.isDestory));
      AppLockManagerActivity.this.prodialog.dismiss();
    }
  };
  private String mypackName = "";
  private CMProgressDialog prodialog;
  private BroadcastReceiver receiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      if (("ws.coverme.im.model.constant.ACTION_PIN".equals(paramAnonymousIntent.getAction())) && (!paramAnonymousIntent.getBooleanExtra("input_correct", false))) {
        AppLockManagerActivity.this.finish();
      }
    }
  };
  
  public AppLockManagerActivity() {}
  
  private void RunApp(String paramString, boolean paramBoolean)
  {
    try
    {
      paramString = getPackageManager().getPackageInfo(paramString, 0);
      Object localObject = new Intent("android.intent.action.MAIN", null);
      ((Intent)localObject).addCategory("android.intent.category.LAUNCHER");
      ((Intent)localObject).setPackage(paramString.packageName);
      localObject = (ResolveInfo)getPackageManager().queryIntentActivities((Intent)localObject, 0).iterator().next();
      if (localObject != null)
      {
        paramString = ((ResolveInfo)localObject).activityInfo.packageName;
        localObject = ((ResolveInfo)localObject).activityInfo.name;
        Intent localIntent = new Intent("android.intent.action.MAIN");
        if (!paramBoolean)
        {
          localIntent.setFlags(268435456);
          localIntent.setComponent(new ComponentName(paramString, (String)localObject));
        }
        for (;;)
        {
          startActivity(localIntent);
          return;
          localIntent.setFlags(276824064);
          localIntent.setComponent(new ComponentName(paramString, (String)localObject));
        }
      }
      return;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      paramString.printStackTrace();
      return;
    }
    catch (Exception paramString) {}
  }
  
  private void checkSetBtn()
  {
    ArrayList localArrayList = this.lockhandler.getAppList(this.curUserId);
    if ((localArrayList != null) && (localArrayList.size() > 0))
    {
      ((Button)findViewById(2131231101)).setVisibility(0);
      return;
    }
    ((Button)findViewById(2131231101)).setVisibility(8);
  }
  
  private void gotoSetActivity(String paramString)
  {
    Intent localIntent = new Intent();
    localIntent.setClass(this, AppLockSettingActivity.class);
    localIntent.putExtra("packageName", paramString);
    localIntent.addFlags(67108864);
    startActivity(localIntent);
  }
  
  private void initAppListData()
  {
    checkExpiredandPayed();
    if ((!this.isPayed) && (this.isExpired == true) && (this.lockhandler.getLockAppNums() > 0))
    {
      if (this.RootStatus == 1) {
        AppLockRootHandler.ViewAllApp(this, this.lockhandler.getAppList(0));
      }
      this.lockhandler.deleteAllApp();
      CMTracer.i("AppLockTask", "AppLockManagerActivity: Expired! remove all lock datas");
    }
    getListData(2);
    this.mHandler.sendEmptyMessage(0);
  }
  
  private void initListener()
  {
    registerReceiver(this.receiver, new IntentFilter("ws.coverme.im.model.constant.ACTION_PIN"));
  }
  
  private void initView()
  {
    this.actTitle = ((TextView)findViewById(2131231100));
    this.actTip = ((TextView)findViewById(2131231104));
    this.actTryTip = ((TextView)findViewById(2131231103));
    this.applock_tip_relativelayout = ((RelativeLayout)findViewById(2131231102));
    Display localDisplay = getWindowManager().getDefaultDisplay();
    this.displayHeight = localDisplay.getHeight();
    this.displayWidth = localDisplay.getWidth();
    this.appListView = ((StretchListView)findViewById(2131231106));
    this.appListView.setDivider(getResources().getDrawable(2130838812));
    this.appListView.setOnItemClickListener(this);
    this.appsdata = new ArrayList();
    this.appnamesdata = new ArrayList();
    this.firstUseTime = AppLockDataHandler.getFirstTime(this);
    this.lockhandler = AppLockDataHandler.getInstance(this);
    this.curUserId = KexinData.getInstance().getCurrentAuthorityId();
  }
  
  public static void sendSecretaryTrial(Context paramContext)
  {
    boolean bool1 = PremiumUtil.isPremiumFeaturesPurchased();
    boolean bool2 = AppLockDataHandler.isExpired(paramContext);
    long l = AppLockDataHandler.getFirstTime(paramContext);
    if ((!bool1) && (!bool2) && (30 - (int)((System.currentTimeMillis() / 1000L - l) / 86400L) == 3) && (!AppLockDataHandler.getLockSecretaryTrialSend(paramContext))) {
      AppLockDataHandler.setLockSecretaryTrialSend(paramContext, true);
    }
  }
  
  public void addToList(HashMap<String, Object> paramHashMap)
  {
    this.appsdata.add(paramHashMap);
  }
  
  public void changeInfo()
  {
    ViewGroup.LayoutParams localLayoutParams;
    if (this.RootStatus == 1)
    {
      this.actTitle.setText(getResources().getString(2131166720));
      this.actTip.setText(getResources().getString(2131166722));
      if (!OtherHelper.isSimpleChineseLanguage(this))
      {
        localLayoutParams = this.applock_tip_relativelayout.getLayoutParams();
        this.applock_tip_relativelayout.getLayoutParams();
        localLayoutParams.height = getResources().getDimensionPixelSize(2131429139);
        this.applock_tip_relativelayout.setLayoutParams(localLayoutParams);
      }
    }
    for (;;)
    {
      changeTryInfo();
      new Handler().postDelayed(new Runnable()
      {
        public void run()
        {
          if (AppLockManagerActivity.this.RootStatus == 1) {
            if (!AppLockDataHandler.getRootTipDlg(AppLockManagerActivity.this))
            {
              AppLockDataHandler.setRootTipDlg(AppLockManagerActivity.this, true);
              AppLockManagerActivity.this.showTipDialog(0, null);
            }
          }
          for (;;)
          {
            AppLockManagerActivity.access$502(AppLockManagerActivity.this, true);
            return;
            if (AppLockManagerActivity.this.RootStatus == 3) {
              AppLockManagerActivity.this.showTipDialog(1, null);
            }
          }
        }
      }, 1000L);
      return;
      localLayoutParams = this.applock_tip_relativelayout.getLayoutParams();
      this.applock_tip_relativelayout.getLayoutParams();
      localLayoutParams.height = getResources().getDimensionPixelSize(2131429151);
      this.applock_tip_relativelayout.setLayoutParams(localLayoutParams);
      continue;
      this.actTitle.setText(getResources().getString(2131166719));
      this.actTip.setText(getResources().getString(2131166721));
      localLayoutParams = this.applock_tip_relativelayout.getLayoutParams();
      this.applock_tip_relativelayout.getLayoutParams();
      localLayoutParams.height = getResources().getDimensionPixelSize(2131429151);
      this.applock_tip_relativelayout.setLayoutParams(localLayoutParams);
    }
  }
  
  public void changeTryInfo()
  {
    if (this.isPayed == true)
    {
      this.actTryTip.setVisibility(8);
      localObject = (RelativeLayout.LayoutParams)this.actTip.getLayoutParams();
      ((RelativeLayout.LayoutParams)localObject).addRule(15, -1);
      this.actTip.setLayoutParams((ViewGroup.LayoutParams)localObject);
    }
    int j;
    do
    {
      do
      {
        return;
        this.actTryTip.setVisibility(0);
        if (this.isExpired == true)
        {
          localObject = "<font color=\"#ff3b30\">" + getResources().getString(2131166727) + "</font>";
          this.actTryTip.setText(Html.fromHtml((String)localObject));
        }
      } while (this.isExpired);
      int i = 30 - (int)((System.currentTimeMillis() / 1000L - this.firstUseTime) / 86400L);
      if (this.firstUseTime == 0L) {
        i = 30;
      }
      j = i;
      if (i > 30) {
        j = 30;
      }
    } while (j <= 0);
    if (j > 3) {}
    for (Object localObject = getResources().getString(2131166728, new Object[] { Integer.valueOf(j) });; localObject = "<font color=\"#ff3b30\">" + String.format(getResources().getString(2131166728), new Object[] { Integer.valueOf(j) }) + "</font>")
    {
      this.actTryTip.setText(Html.fromHtml((String)localObject));
      return;
    }
  }
  
  public void checkExpiredandPayed()
  {
    this.isPayed = PremiumUtil.isPremiumFeaturesPurchased();
    this.isExpired = AppLockDataHandler.isExpired(this);
  }
  
  public boolean checkPackItem(String paramString, boolean paramBoolean)
  {
    if ((this.firstUseTime == 0L) && (!this.isPayed))
    {
      long l = System.currentTimeMillis() / 1000L;
      AppLockDataHandler.setFirstTime(this, l);
      this.firstUseTime = l;
      CMTracer.i("AppLockTask", "AppLockManagerActivity: use lock app in the first time");
    }
    if (this.isExpired == true)
    {
      CMTracer.i("AppLockTask", "AppLockManagerActivity: Expired!");
      showTipDialog(3, null);
      return false;
    }
    Object localObject;
    if (paramBoolean == true)
    {
      CMTracer.i("AppLockTask", "AppLockManagerActivity: saveApp:" + paramString);
      localObject = (LockAppData)this.lockhandler.lockDataMap.get(paramString);
      if (localObject != null)
      {
        ((LockAppData)localObject).setIsLockOn(1);
        this.lockhandler.saveApp(this.curUserId, paramString, (LockAppData)localObject);
        gotoSetActivity(paramString);
      }
    }
    for (;;)
    {
      checkSetBtn();
      localObject = new Intent(this, CMCoreService.class);
      Bundle localBundle = new Bundle();
      localBundle.putString("startReason", "startOnAppLock");
      localBundle.putString("packageName", paramString);
      localBundle.putBoolean("isCheck", paramBoolean);
      ((Intent)localObject).putExtras(localBundle);
      startService((Intent)localObject);
      return true;
      localObject = new LockAppData();
      ((LockAppData)localObject).setPackageName(paramString);
      ((LockAppData)localObject).setAuthorId(this.curUserId);
      ((LockAppData)localObject).setIsLockOn(1);
      ((LockAppData)localObject).setIsShowPwdInput(0);
      ((LockAppData)localObject).setImageType(1);
      ((LockAppData)localObject).setAppName("");
      this.lockhandler.saveApp(this.curUserId, paramString, (LockAppData)localObject);
      break;
      CMTracer.i("AppLockTask", "AppLockManagerActivity: deleteApp:" + paramString);
      localObject = (LockAppData)this.lockhandler.lockDataMap.get(paramString);
      if (localObject != null)
      {
        ((LockAppData)localObject).setIsLockOn(0);
        this.lockhandler.saveApp(this.curUserId, paramString, (LockAppData)localObject);
      }
    }
  }
  
  public void getListData(int paramInt)
  {
    int i = 700;
    if (paramInt == 1) {
      i = 7;
    }
    int j = 0;
    this.mypackName = getPackageName();
    ArrayList localArrayList1 = this.lockhandler.getAppList(this.curUserId);
    ArrayList localArrayList2 = new ArrayList();
    ArrayList localArrayList3 = new ArrayList();
    PackageManager localPackageManager = getPackageManager();
    List localList = localPackageManager.getInstalledPackages(0);
    ((ActivityManager)getSystemService("activity")).getRunningServices(50);
    int i1 = localList.size();
    paramInt = 0;
    Object localObject1;
    String str;
    int n;
    if (paramInt < i1)
    {
      localObject1 = (PackageInfo)localList.get(paramInt);
      HashMap localHashMap = new HashMap();
      str = ((PackageInfo)localObject1).applicationInfo.packageName;
      if (str.equals(this.mypackName) == true) {
        n = j;
      }
    }
    for (;;)
    {
      paramInt += 1;
      j = n;
      break;
      n = j;
      if (str.equals("com.android.settings")) {
        continue;
      }
      n = j;
      if (AppLockDataHandler.isWhitePack(str) == true) {
        continue;
      }
      int m = 0;
      int k = m;
      if (localArrayList1 != null)
      {
        k = m;
        if (localArrayList1.contains(str) == true) {
          k = 1;
        }
      }
      n = 0;
      m = n;
      if (this.RootStatus == 1)
      {
        m = n;
        if (localPackageManager.getApplicationEnabledSetting(str) == 2) {
          m = 1;
        }
      }
      Object localObject2;
      Object localObject3;
      try
      {
        localObject2 = localPackageManager.getPackageInfo(str, 0);
        localObject3 = new Intent("android.intent.action.MAIN", null);
        ((Intent)localObject3).addCategory("android.intent.category.LAUNCHER");
        ((Intent)localObject3).setPackage(((PackageInfo)localObject2).packageName);
        localObject2 = localPackageManager.queryIntentActivities((Intent)localObject3, 0);
        if (localObject2 != null)
        {
          n = ((List)localObject2).size();
          if (n != 0) {}
        }
        else if (k == 0)
        {
          n = j;
          if (m == 0) {
            continue;
          }
        }
        n = j + 1;
        if (n > i)
        {
          paramInt = 0;
          while (paramInt < localArrayList2.size())
          {
            addToList((HashMap)localArrayList2.get(paramInt));
            paramInt += 1;
          }
        }
      }
      catch (Exception localException1)
      {
        n = j;
      }
      try
      {
        localException1.put("Itemlogo", ((PackageInfo)localObject1).applicationInfo.loadIcon(localPackageManager));
        localException1.put("Itemname", ((PackageInfo)localObject1).applicationInfo.loadLabel(localPackageManager));
        localException1.put("ItempackageName", str);
        if (k == 1)
        {
          if (this.isExpired == true)
          {
            localException1.put("Itemcheck", Boolean.valueOf(false));
            CMTracer.i("AppLockTask", "AppLockManagerActivity: expired deleteApp:" + str);
            localObject2 = (LockAppData)this.lockhandler.lockDataMap.get(str);
            localObject3 = new LockAppData();
            ((LockAppData)localObject3).setPackageName(str);
            ((LockAppData)localObject3).setAppName(String.valueOf(((PackageInfo)localObject1).applicationInfo.loadLabel(localPackageManager)));
            ((LockAppData)localObject3).setAuthorId(this.curUserId);
            ((LockAppData)localObject3).setIsLockOn(0);
            if (localObject2 != null)
            {
              ((LockAppData)localObject3).setImagePath(((LockAppData)localObject2).getImagePath());
              ((LockAppData)localObject3).setImageType(((LockAppData)localObject2).getImageType());
              ((LockAppData)localObject3).setIsShowPwdInput(((LockAppData)localObject2).getIsShowPwdInput());
            }
            this.lockhandler.saveApp(this.curUserId, str, (LockAppData)localObject3);
            localObject1 = new Intent(this, CMCoreService.class);
            localObject2 = new Bundle();
            ((Bundle)localObject2).putString("startReason", "startOnAppLock");
            ((Bundle)localObject2).putString("packageName", str);
            ((Bundle)localObject2).putBoolean("isCheck", false);
            ((Intent)localObject1).putExtras((Bundle)localObject2);
            startService((Intent)localObject1);
          }
          for (;;)
          {
            addToList(localException1);
            break;
            localException1.put("Itemcheck", Boolean.valueOf(true));
          }
        }
        localException1.put("Itemcheck", Boolean.valueOf(false));
        if (((((PackageInfo)localObject1).applicationInfo.flags & 0x1) == 0) && ((((PackageInfo)localObject1).applicationInfo.flags & 0x80) == 0))
        {
          localArrayList2.add(localException1);
          continue;
        }
        localArrayList3.add(localException1);
        continue;
        paramInt = 0;
        while (paramInt < localArrayList3.size())
        {
          addToList((HashMap)localArrayList3.get(paramInt));
          paramInt += 1;
        }
        return;
      }
      catch (Exception localException2)
      {
        for (;;) {}
      }
    }
  }
  
  public void onAttachedToWindow()
  {
    super.onAttachedToWindow();
  }
  
  public void onBackPressed()
  {
    super.onBackPressed();
  }
  
  public void onClick(View paramView)
  {
    if (ClickTimeSpanUtil.isFastDoubleClickForAlbum(700L)) {
      return;
    }
    switch (paramView.getId())
    {
    case 2131231100: 
    default: 
      return;
    case 2131231099: 
      finish();
      return;
    }
    paramView = new Intent();
    paramView.setClass(this, AppLockedManagerActivity.class);
    startActivity(paramView);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903072);
    this.prodialog = new CMProgressDialog(this);
    initView();
    this.prodialog.show();
    new GetDate().start();
    changeInfo();
    initListener();
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    this.isDestory = true;
    unregisterReceiver(this.receiver);
  }
  
  public void onItemClick(final AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    if (!this.canClickItem) {
      return;
    }
    paramAdapterView = (String)((HashMap)this.appsdata.get(paramInt)).get("ItempackageName");
    paramView = (String)((HashMap)this.appsdata.get(paramInt)).get("Itemname");
    final boolean bool = ((Boolean)this.appListAdapter.mChecked.get(paramInt)).booleanValue();
    final MyDialog localMyDialog = new MyDialog(this);
    localMyDialog.setTitle(2131165406);
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append(getResources().getString(2131166729, new Object[] { paramView }));
    localMyDialog.setMessage(localStringBuffer.toString());
    localMyDialog.setNegativeButton(2131166307, new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        localMyDialog.dismiss();
      }
    });
    localMyDialog.setPositiveButton(2131166306, new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramAnonymousView = new Intent(AppLockManagerActivity.this, CMCoreService.class);
        Bundle localBundle = new Bundle();
        localBundle.putString("startReason", "startOnAppLock");
        localBundle.putString("lastPackName", paramAdapterView + "_0");
        paramAnonymousView.putExtras(localBundle);
        AppLockManagerActivity.this.startService(paramAnonymousView);
        if ((bool == true) && (AppLockManagerActivity.this.RootStatus == 1)) {
          AppLockRootHandler.ViewApp(AppLockManagerActivity.this, paramAdapterView, AppLockManagerActivity.this.lockAppHandler);
        }
        for (;;)
        {
          localMyDialog.dismiss();
          return;
          AppLockManagerActivity.this.RunApp(paramAdapterView, false);
        }
      }
    });
    localMyDialog.show();
  }
  
  protected void onResume()
  {
    super.onResume();
    checkExpiredandPayed();
    changeTryInfo();
    checkSetBtn();
  }
  
  public void onWindowFocusChanged(boolean paramBoolean) {}
  
  protected void showTipDialog(int paramInt, final Bundle paramBundle)
  {
    paramBundle = new MyDialog(this);
    StringBuffer localStringBuffer;
    if (paramInt == 0)
    {
      paramBundle.setTitle(2131165406);
      localStringBuffer = new StringBuffer();
      localStringBuffer.append(getResources().getString(2131166724));
      paramBundle.setMessage(localStringBuffer.toString());
      paramBundle.setSinglePositiveButton(2131166306, null);
      paramBundle.setCancelable(false);
    }
    if (paramInt == 1)
    {
      paramBundle.setTitle(2131165406);
      localStringBuffer = new StringBuffer();
      localStringBuffer.append(getResources().getString(2131166725));
      paramBundle.setMessage(localStringBuffer.toString());
      paramBundle.setSinglePositiveButton(2131166306, null);
      paramBundle.setCancelable(false);
    }
    if (paramInt == 3) {
      if (isFinishing()) {}
    }
    while (this.isDestory)
    {
      return;
      paramBundle.setTitle(2131166718);
      localStringBuffer = new StringBuffer();
      localStringBuffer.append(getResources().getString(2131166726));
      paramBundle.setMessage(localStringBuffer.toString());
      paramBundle.setNegativeButton(2131166307, new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          paramBundle.dismiss();
        }
      });
      paramBundle.setPositiveButton(2131166730, new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          paramBundle.dismiss();
          paramAnonymousView = new Intent(AppLockManagerActivity.this, AdvancedVersionActivity.class);
          if (AppInstalledUtil.isVaultApp(AppLockManagerActivity.this)) {
            paramAnonymousView = new Intent(AppLockManagerActivity.this, VaultAdvancedVersionActivity.class);
          }
          AppLockManagerActivity.this.startActivity(paramAnonymousView);
        }
      });
    }
    paramBundle.show();
  }
  
  public class AppListAdapter
    extends BaseAdapter
  {
    Context _activity;
    private int appnums = 0;
    List<Boolean> mChecked = new ArrayList();
    private LayoutInflater mInflater;
    
    public AppListAdapter(Context paramContext)
    {
      this._activity = paramContext;
      this.mInflater = LayoutInflater.from(paramContext);
      this.appnums = AppLockManagerActivity.this.appsdata.size();
    }
    
    public int getCount()
    {
      return this.appnums;
    }
    
    public Object getItem(int paramInt)
    {
      return null;
    }
    
    public long getItemId(int paramInt)
    {
      return 0L;
    }
    
    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      int j;
      final int i;
      if (paramView == null)
      {
        paramViewGroup = new AppLockManagerActivity.ViewHolder(AppLockManagerActivity.this);
        paramView = this.mInflater.inflate(2130903071, null);
        paramViewGroup.Itemlogo = ((ImageView)paramView.findViewById(2131231095));
        paramViewGroup.Itemname = ((TextView)paramView.findViewById(2131231097));
        paramViewGroup.Itemcheck = ((CheckBox)paramView.findViewById(2131231096));
        j = AppLockManagerActivity.this.displayWidth * 14 / 720;
        if (j < 15)
        {
          i = 15;
          paramViewGroup.Itemname.setTextSize(i);
          paramView.setTag(paramViewGroup);
        }
      }
      for (;;)
      {
        j = AppLockManagerActivity.this.appsdata.size();
        i = paramInt;
        if (paramInt > j - 1) {
          i = j - 1;
        }
        Object localObject = (String)((HashMap)AppLockManagerActivity.this.appsdata.get(i)).get("ItempackageName");
        paramViewGroup.Itemlogo.setVisibility(0);
        paramViewGroup.Itemname.setVisibility(0);
        paramViewGroup.Itemcheck.setVisibility(0);
        paramView.setEnabled(true);
        localObject = (Drawable)((HashMap)AppLockManagerActivity.this.appsdata.get(i)).get("Itemlogo");
        if (localObject != null) {
          paramViewGroup.Itemlogo.setImageDrawable((Drawable)localObject);
        }
        paramViewGroup.Itemname.setText((String)((HashMap)AppLockManagerActivity.this.appsdata.get(i)).get("Itemname"));
        paramViewGroup.Itemcheck.setChecked(((Boolean)this.mChecked.get(i)).booleanValue());
        paramViewGroup.Itemcheck.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            AppLockManagerActivity localAppLockManagerActivity = (AppLockManagerActivity)AppLockManagerActivity.AppListAdapter.this._activity;
            String str = (String)((HashMap)AppLockManagerActivity.this.appsdata.get(i)).get("ItempackageName");
            paramAnonymousView = (CheckBox)paramAnonymousView;
            boolean bool = paramAnonymousView.isChecked();
            if (!localAppLockManagerActivity.checkPackItem(str, bool)) {
              if (bool) {
                break label97;
              }
            }
            label97:
            for (bool = true;; bool = false)
            {
              paramAnonymousView.setChecked(bool);
              AppLockManagerActivity.AppListAdapter.this.mChecked.set(i, Boolean.valueOf(paramAnonymousView.isChecked()));
              return;
            }
          }
        });
        paramView.setId(i);
        return paramView;
        if ((j >= 15) && (j < 19))
        {
          i = 18;
          break;
        }
        if ((j >= 19) && (j < 24))
        {
          i = 22;
          break;
        }
        i = j;
        if (j < 24) {
          break;
        }
        i = 24;
        break;
        paramViewGroup = (AppLockManagerActivity.ViewHolder)paramView.getTag();
      }
    }
    
    public void resetChecked()
    {
      this.mChecked.clear();
      this.appnums = AppLockManagerActivity.this.appsdata.size();
      int i = 0;
      if (i < this.appnums)
      {
        if (((Boolean)((HashMap)AppLockManagerActivity.this.appsdata.get(i)).get("Itemcheck")).booleanValue() == true) {
          this.mChecked.add(Boolean.valueOf(true));
        }
        for (;;)
        {
          i += 1;
          break;
          this.mChecked.add(Boolean.valueOf(false));
        }
      }
    }
  }
  
  class GetDate
    extends Thread
  {
    GetDate() {}
    
    public void run()
    {
      super.run();
      AppLockRootHandler.CheckRoot(AppLockManagerActivity.this);
      AppLockManagerActivity.access$202(AppLockManagerActivity.this, AppLockDataHandler.getRoot(AppLockManagerActivity.this));
      GraphicalUtil.checkDotLock(AppLockManagerActivity.this, 255, 64);
      AppLockManagerActivity.this.initAppListData();
    }
  }
  
  class ViewHolder
  {
    CheckBox Itemcheck;
    ImageView Itemlogo;
    TextView Itemname;
    Integer Itemtype;
    
    ViewHolder() {}
  }
}
