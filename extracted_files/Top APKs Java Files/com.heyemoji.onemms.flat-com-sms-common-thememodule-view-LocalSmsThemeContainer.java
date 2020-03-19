package com.sms.common.thememodule.view;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.keyboard.common.remotemodule.core.network.ImageLoaderWrapper;
import com.keyboard.common.remotemodule.core.utils.LocalResUtils;
import com.keyboard.common.utilsmodule.AccessResUtils;
import com.sms.common.thememodule.R.drawable;
import com.sms.common.thememodule.R.id;
import com.sms.common.thememodule.R.integer;
import com.sms.common.thememodule.data.SmsThemeInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LocalSmsThemeContainer
  extends TabContainer
  implements SmsThemeGridView.ItemClickListener
{
  public static final int MSG_SCAN_INSTALLED_THEME = 101;
  public static final int MSG_UPDATE_UI = 201;
  private static final String TAG = OnlineSmsThemeContainer.class.getSimpleName();
  private int mColumnsNum = 0;
  private Context mContext = null;
  private ArrayList<SmsThemeInfo> mLocalBuildInThemeList;
  private int mPageItemNum = 0;
  private String mPkgPrefix = null;
  private Handler mScanThemeHandler;
  private HandlerThread mScanThemeThread;
  private SmsThemeGridView mSmsGridView = null;
  private ArrayList<SmsThemeInfo> mSmsThemeBuffer;
  private ImageView mSmsThemeNoData = null;
  private String mThemeName = null;
  private String mThemeResName = null;
  private Handler mUIHandler;
  
  public LocalSmsThemeContainer(Context paramContext)
  {
    super(paramContext);
    init(paramContext);
  }
  
  public LocalSmsThemeContainer(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext);
  }
  
  public LocalSmsThemeContainer(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramContext);
  }
  
  private void init(Context paramContext)
  {
    this.mContext = paramContext;
    this.mColumnsNum = this.mContext.getResources().getInteger(R.integer.sms_theme_gridview_colnum);
  }
  
  private void initData()
  {
    this.mSmsThemeBuffer = new ArrayList();
    Object localObject = getResources();
    Drawable localDrawable = ((Resources)localObject).getDrawable(R.drawable.sms_theme_ic_loading);
    localObject = ((Resources)localObject).getDrawable(R.drawable.sms_theme_ic_load_error);
    this.mSmsGridView = ((SmsThemeGridView)findViewById(R.id.sms_local_theme_gridview));
    this.mSmsGridView.setNumColumns(this.mColumnsNum);
    this.mSmsGridView.setLoadingRes(localDrawable, (Drawable)localObject);
    this.mSmsGridView.setItemClickListener(this);
    this.mSmsThemeNoData = ((ImageView)findViewById(R.id.sms_theme_no_data));
    this.mScanThemeHandler.sendEmptyMessageDelayed(101, 200L);
  }
  
  private void initHandler()
  {
    this.mScanThemeThread = new HandlerThread("scan installed theme");
    this.mScanThemeThread.start();
    this.mScanThemeHandler = new Handler(this.mScanThemeThread.getLooper(), new Handler.Callback()
    {
      public boolean handleMessage(Message paramAnonymousMessage)
      {
        if (paramAnonymousMessage.what == 101)
        {
          LocalSmsThemeContainer.this.assembleInstalledTheme();
          return true;
        }
        return false;
      }
    });
    this.mUIHandler = new Handler(Looper.getMainLooper(), new Handler.Callback()
    {
      public boolean handleMessage(Message paramAnonymousMessage)
      {
        if (paramAnonymousMessage.what == 201)
        {
          LocalSmsThemeContainer.this.mSmsGridView.setThemeInfo(LocalSmsThemeContainer.this.mSmsThemeBuffer);
          return true;
        }
        return false;
      }
    });
  }
  
  private ArrayList<String> scanAllAppPkg(Context paramContext)
  {
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    List localList = paramContext.getPackageManager().getInstalledPackages(0);
    int i = 0;
    while (i < localList.size())
    {
      PackageInfo localPackageInfo = (PackageInfo)localList.get(i);
      String str = localPackageInfo.packageName;
      paramContext = str;
      if (TextUtils.isEmpty(str)) {
        paramContext = localPackageInfo.applicationInfo.packageName;
      }
      localArrayList2.add(paramContext);
      i += 1;
    }
    i = 0;
    while (i < localArrayList2.size())
    {
      if (((String)localArrayList2.get(i)).contains(this.mPkgPrefix)) {
        localArrayList1.add(localArrayList2.get(i));
      }
      i += 1;
    }
    return localArrayList1;
  }
  
  public void assembleInstalledTheme()
  {
    Random localRandom = new Random(System.currentTimeMillis());
    ArrayList localArrayList = scanAllAppPkg(this.mContext);
    int i = 0;
    while (i < localArrayList.size())
    {
      int j = localRandom.nextInt(10000);
      SmsThemeInfo localSmsThemeInfo = new SmsThemeInfo(AccessResUtils.getString(this.mContext, this.mThemeName, (String)localArrayList.get(i)), LocalResUtils.assembleInstalledResUrl(this.mContext, this.mThemeResName, "drawable", (String)localArrayList.get(i)), (String)localArrayList.get(i), Integer.toString(j + 1 + 10000), "5");
      localSmsThemeInfo.mInstall = true;
      this.mSmsThemeBuffer.add(localSmsThemeInfo);
      this.mUIHandler.sendEmptyMessage(201);
      i += 1;
    }
    localArrayList.clear();
  }
  
  public void onCreate()
  {
    initHandler();
    initData();
  }
  
  public void onDestroy()
  {
    if (this.mSmsGridView != null) {
      this.mSmsGridView.destroy();
    }
    this.mScanThemeThread.quit();
    this.mScanThemeHandler.removeMessages(101);
    this.mUIHandler.removeMessages(201);
  }
  
  public void onItemClick(SmsThemeInfo paramSmsThemeInfo)
  {
    setCurrentTheme(paramSmsThemeInfo.mPkg);
  }
  
  public void onPause() {}
  
  public void onResume() {}
  
  public String setAppId(String paramString)
  {
    return null;
  }
  
  public void setCurrentTheme(String paramString)
  {
    this.mSmsGridView.setCurrentTheme(paramString);
    this.mSmsGridView.updateView();
  }
  
  protected void setItemClickListener(SmsThemeGridView.ItemClickListener paramItemClickListener)
  {
    this.mSmsGridView.setItemClickListener(paramItemClickListener);
  }
  
  protected void setLocalThemeList(ArrayList<SmsThemeInfo> paramArrayList)
  {
    if (paramArrayList != null)
    {
      this.mLocalBuildInThemeList = paramArrayList;
      this.mSmsThemeBuffer.addAll(0, this.mLocalBuildInThemeList);
      this.mSmsGridView.setThemeInfo(this.mSmsThemeBuffer);
    }
  }
  
  protected int setPageItemNum(int paramInt)
  {
    if (paramInt > 0) {
      this.mPageItemNum = paramInt;
    }
    return this.mPageItemNum;
  }
  
  public void setResData(String paramString1, String paramString2, String paramString3)
  {
    if (!TextUtils.isEmpty(paramString1)) {
      this.mThemeName = paramString1;
    }
    if (!TextUtils.isEmpty(paramString2)) {
      this.mPkgPrefix = paramString2;
    }
    if (!TextUtils.isEmpty(paramString3)) {
      this.mThemeResName = paramString3;
    }
  }
  
  public void themeInstalled(String paramString)
  {
    if (this.mSmsGridView != null) {
      themeInstalled(this.mSmsGridView, paramString, this.mThemeName, this.mThemeResName, true);
    }
  }
  
  public void themeUninstall(String paramString)
  {
    if (this.mSmsGridView != null) {
      themeUninstall(this.mSmsGridView, paramString, true);
    }
  }
}
