package com.sms.common.fontpickermodule;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import com.keyboard.common.remotemodule.core.utils.SuggestInfoUtils;
import com.keyboard.common.remotemodule.core.zero.view.AdsDialog;
import com.keyboard.common.remotemodule.core.zero.view.AdsDialog.AdsDlgListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FontPickerFragment
  extends Fragment
  implements AdapterView.OnItemClickListener, AdsDialog.AdsDlgListener
{
  private static final String DEFAULT_FONT_NAME = "Default";
  private static final String DIRECTORY_FONT = "font";
  private static final String DIRECTORY_FONTS = "fonts";
  private static final String FLIP_FONT_PKG = "com.monotype.android.font";
  public static final String FRAGMENT_TAG = "FontPickerFragment";
  public static final String MORE_FONT_NAME = "More Fonts";
  public static final String MORE_FONT_PKG = "com.crazygame.keyboard.font";
  private static final int MSG_SCAN_ALL_FLIP_FONT = 101;
  private static final int MSG_SCAN_CUSTOM_FONT = 501;
  private static final int MSG_SCAN_EXTERNAL_FONT = 301;
  private static final int MSG_SHOW_DIALOG = 401;
  private static final int MSG_UPDATE_UI = 201;
  private static final String NO_DIRECTORY = "";
  private static final String SAMPLE_STRING = "1234567890\nabcdefghij\nABCDEFGHIJ\b";
  private static final long SCAN_ALL_FLIP_FONT_DELAY = 200L;
  private static final long SHOW_DIALOG_DELAY = 400L;
  private static final String SUFFIX_OTF = ".otf";
  private static final String SUFFIX_TTF = ".ttf";
  public static boolean mIsUseNewStyle = false;
  private Drawable mClickDrawable = null;
  private Activity mContext;
  private String mCurrentFontPath;
  private float mDefaultTextSize;
  private String mExternalFontApkPackName;
  private boolean mFilpFont = false;
  private List<FontInfo> mFontList = new ArrayList();
  private FontPickerListener mFontPickerListener;
  private GridViewAdapter mGridAdapter;
  private AdsDialog mGuideDownloadFontDialog;
  private InstallReceiver mInstallReceiver;
  private int mItemTitleSize;
  private LayoutInflater mLayoutInflater;
  private GridView mListView;
  private int mListViewWidth;
  private boolean mMessageFont = false;
  private NewGridViewAdapter mNewGridAdapter;
  private PackageManager mPackageMgr;
  private Handler mScanFontHandler;
  private HandlerThread mScanFontThread;
  private Drawable mSelectedDrawable = null;
  private int mSelectedTitleColor;
  private String mSelfPkgName;
  private String mSourceId = "";
  private int mSpacing;
  private float mTextSize;
  private float mThemeRatio = 1.45F;
  private Drawable mTitleDrawable = null;
  private Typeface mTypeface;
  private TypefaceListener mTypefaceListener;
  private Handler mUIHandler;
  private int mUnselectedTitleColor;
  
  public FontPickerFragment() {}
  
  private void addExternalFontApk()
  {
    if (!TextUtils.isEmpty(this.mExternalFontApkPackName))
    {
      this.mFontList.add(new FontInfo(this.mExternalFontApkPackName, "more fonts"));
      checkoutExternalFontIsInstall(true);
    }
  }
  
  private void addMoreFontItem()
  {
    if (this.mFontList == null) {}
    while ((this.mFontList.size() > 0) && (((FontInfo)this.mFontList.get(0)).getPkgName().equals("com.crazygame.keyboard.font"))) {
      return;
    }
    this.mFontList.add(0, new FontInfo("com.crazygame.keyboard.font", "More Fonts"));
    updateGrid();
  }
  
  private void checkNeedShowDialog()
  {
    if ((this.mGuideDownloadFontDialog == null) || (this.mContext == null)) {}
    while ((this.mContext.isFinishing()) || (isApkInstalled())) {
      return;
    }
    this.mGuideDownloadFontDialog.show();
  }
  
  private void checkoutExternalFontIsInstall(boolean paramBoolean)
  {
    String str = SuggestInfoUtils.getPkgNameFromInstallSource(this.mExternalFontApkPackName);
    if (!SuggestInfoUtils.isApkInstalled(this.mContext, str))
    {
      if (!paramBoolean) {
        SuggestInfoUtils.goToInstallApk(this.mContext, this.mExternalFontApkPackName, this.mContext.getPackageName(), this.mSourceId);
      }
      return;
    }
    this.mScanFontHandler.sendEmptyMessage(301);
  }
  
  private void checkoutFlipFontIsInstall()
  {
    Uri localUri = Uri.parse("https://play.google.com/store/search?q=FlipFont&c=apps&price=1");
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setData(localUri);
    localIntent.addFlags(268435456);
    startActivity(localIntent);
  }
  
  private List<String> getFlipFontPkg(Context paramContext)
  {
    if (paramContext == null)
    {
      paramContext = null;
      return paramContext;
    }
    ArrayList localArrayList = new ArrayList();
    List localList = paramContext.getPackageManager().getInstalledPackages(0);
    int i = 0;
    for (;;)
    {
      paramContext = localArrayList;
      if (i >= localList.size()) {
        break;
      }
      PackageInfo localPackageInfo = (PackageInfo)localList.get(i);
      String str = localPackageInfo.packageName;
      paramContext = str;
      if (TextUtils.isEmpty(str)) {
        paramContext = localPackageInfo.applicationInfo.packageName;
      }
      if (paramContext.startsWith("com.monotype.android.font")) {
        localArrayList.add(paramContext);
      }
      i += 1;
    }
  }
  
  private void initScanFont()
  {
    this.mScanFontThread = new HandlerThread(FontPickerFragment.class.getName() + hashCode());
    this.mScanFontThread.start();
    this.mScanFontHandler = new Handler(this.mScanFontThread.getLooper(), new Handler.Callback()
    {
      public boolean handleMessage(Message paramAnonymousMessage)
      {
        switch (paramAnonymousMessage.what)
        {
        default: 
          return false;
        case 101: 
          if (FontPickerFragment.this.mMessageFont)
          {
            FontPickerFragment.this.scanMessageFont();
            return true;
          }
          if (FontPickerFragment.this.mFilpFont)
          {
            FontPickerFragment.this.scanAllFlipFont();
            return true;
          }
          FontPickerFragment.this.scanCurrentAppFont();
          FontPickerFragment.this.scanAllFlipFont();
          return true;
        }
        FontPickerFragment.this.scanExternalFont();
        return true;
      }
    });
    this.mUIHandler = new Handler(Looper.getMainLooper(), new Handler.Callback()
    {
      public boolean handleMessage(Message paramAnonymousMessage)
      {
        switch (paramAnonymousMessage.what)
        {
        default: 
          return false;
        case 201: 
          FontPickerFragment.this.updateGrid();
          return true;
        }
        FontPickerFragment.this.checkNeedShowDialog();
        return true;
      }
    });
    this.mScanFontHandler.sendEmptyMessageDelayed(101, 200L);
  }
  
  private void initView(View paramView)
  {
    this.mListView = ((GridView)paramView.findViewById(R.id.font_picker_gridview));
    if (mIsUseNewStyle)
    {
      this.mNewGridAdapter = new NewGridViewAdapter(null);
      int i = (int)getResources().getDimension(R.dimen.new_font_picker_list_view_padding);
      this.mSpacing = i;
      this.mListView.setHorizontalSpacing(this.mSpacing);
      this.mListView.setVerticalSpacing(this.mSpacing);
      this.mListView.setDrawSelectorOnTop(true);
      this.mListView.setPadding(i, i, i, i);
      this.mListView.setAdapter(this.mNewGridAdapter);
      this.mListView.setOnItemClickListener(this);
      if (!this.mFilpFont) {
        break label177;
      }
      this.mFontList.add(new FontInfo(this.mSelfPkgName, "more filp fonts"));
    }
    for (;;)
    {
      addExternalFontApk();
      updateGrid();
      return;
      this.mGridAdapter = new GridViewAdapter(null);
      this.mListView.setAdapter(this.mGridAdapter);
      break;
      label177:
      this.mFontList.add(new FontInfo(this.mSelfPkgName, "default"));
    }
  }
  
  private boolean isApkInstalled()
  {
    if (TextUtils.isEmpty(this.mExternalFontApkPackName)) {
      return true;
    }
    String str = SuggestInfoUtils.getPkgNameFromInstallSource(this.mExternalFontApkPackName);
    return SuggestInfoUtils.isApkInstalled(this.mContext, str);
  }
  
  private void removeMoreFontIem()
  {
    if ((this.mFontList == null) || (this.mFontList.size() == 0)) {}
    while (!((FontInfo)this.mFontList.get(0)).getPkgName().equals("com.crazygame.keyboard.font")) {
      return;
    }
    this.mFontList.remove(0);
    updateGrid();
  }
  
  private void scanAllFlipFont()
  {
    Object localObject = getFlipFontPkg(this.mContext);
    if (localObject != null)
    {
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        String str = (String)((Iterator)localObject).next();
        scanFontFromPkg(str, "");
        scanFontFromPkg(str, "font");
        scanFontFromPkg(str, "fonts");
      }
    }
  }
  
  private void scanCurrentAppFont()
  {
    scanFontFromPkg(this.mSelfPkgName, "font");
  }
  
  private void scanExternalFont()
  {
    if (!TextUtils.isEmpty(this.mExternalFontApkPackName))
    {
      this.mFontList.remove(1);
      scanFontFromPkg(this.mExternalFontApkPackName, "fonts", true);
    }
  }
  
  private void scanFontFromPkg(String paramString1, String paramString2)
  {
    scanFontFromPkg(paramString1, paramString2, false);
  }
  
  private void scanFontFromPkg(String paramString1, String paramString2, boolean paramBoolean)
  {
    Object localObject;
    try
    {
      Resources localResources = this.mPackageMgr.getResourcesForApplication(paramString1);
      if (localResources == null) {
        return;
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
        localObject = null;
      }
      localObject = ((Resources)localObject).getAssets();
    }
    for (;;)
    {
      try
      {
        String[] arrayOfString = ((AssetManager)localObject).list(paramString2);
        if (arrayOfString == null) {
          break;
        }
        int i = 0;
        if (i >= arrayOfString.length) {
          break;
        }
        if (((arrayOfString[i].endsWith(".ttf")) || (arrayOfString[i].endsWith(".otf"))) && (((this.mFontPickerListener != null) && (!this.mFontPickerListener.isIgnoreFont(paramString1, arrayOfString[i]))) || (this.mFontPickerListener == null)))
        {
          if (!TextUtils.isEmpty(paramString2))
          {
            localObject = paramString2 + File.separator + arrayOfString[i];
            if (!paramBoolean) {
              break label215;
            }
            this.mFontList.add(1, new FontInfo(paramString1, (String)localObject));
            this.mUIHandler.sendEmptyMessage(201);
          }
        }
        else
        {
          i += 1;
          continue;
        }
        localObject = arrayOfString[i];
      }
      catch (Exception paramString1)
      {
        paramString1.printStackTrace();
        return;
      }
      continue;
      label215:
      this.mFontList.add(new FontInfo(paramString1, (String)localObject));
    }
  }
  
  private void scanMessageFont()
  {
    scanFontFromPkg(this.mSelfPkgName, "fonts");
  }
  
  private void setMoreFontAppInstallReceiver()
  {
    if (this.mInstallReceiver != null) {
      return;
    }
    this.mInstallReceiver = new InstallReceiver(null);
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("android.intent.action.PACKAGE_ADDED");
    localIntentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
    localIntentFilter.addDataScheme("package");
    try
    {
      this.mContext.registerReceiver(this.mInstallReceiver, localIntentFilter);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  private void updateGrid()
  {
    if (this.mGridAdapter != null) {
      this.mGridAdapter.notifyDataSetChanged();
    }
    if (this.mNewGridAdapter != null) {
      this.mNewGridAdapter.notifyDataSetChanged();
    }
  }
  
  public void adjustSize(View paramView, int paramInt1, int paramInt2)
  {
    Object localObject = paramView.getLayoutParams();
    if (localObject == null) {
      localObject = new AbsListView.LayoutParams(-1, paramInt2);
    }
    for (;;)
    {
      paramView.setLayoutParams((ViewGroup.LayoutParams)localObject);
      return;
      ((ViewGroup.LayoutParams)localObject).height = paramInt2;
    }
  }
  
  public void onAdsDlgBtnClick(AdsDialog paramAdsDialog, int paramInt)
  {
    if (paramInt == 0)
    {
      SuggestInfoUtils.goToInstallApk(this.mContext, this.mExternalFontApkPackName, this.mContext.getPackageName(), this.mSourceId);
      if (paramAdsDialog != null) {
        paramAdsDialog.dismiss();
      }
    }
    while ((4 != paramInt) || (paramAdsDialog == null) || (!paramAdsDialog.isShowing())) {
      return;
    }
    paramAdsDialog.dismiss();
  }
  
  public void onAdsDlgShow(AdsDialog paramAdsDialog) {}
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.mContext = getActivity();
    this.mLayoutInflater = getActivity().getLayoutInflater();
    this.mSelfPkgName = this.mContext.getPackageName();
    this.mPackageMgr = this.mContext.getPackageManager();
    paramBundle = this.mContext.getResources();
    this.mDefaultTextSize = paramBundle.getDimension(R.dimen.font_picker_default_preview_text_size);
    this.mTextSize = paramBundle.getDimension(R.dimen.font_picker_preview_text_size);
    if (TextUtils.isEmpty(this.mCurrentFontPath)) {
      this.mCurrentFontPath = FontUtils.assembleFontPath(this.mSelfPkgName, "default");
    }
    setMoreFontAppInstallReceiver();
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    paramLayoutInflater = paramLayoutInflater.inflate(R.layout.font_picker_fragment, null, false);
    initScanFont();
    initView(paramLayoutInflater);
    return paramLayoutInflater;
  }
  
  public void onDestroy()
  {
    super.onDestroy();
    this.mFontList.clear();
    if (this.mGridAdapter != null) {
      this.mGridAdapter.notifyDataSetChanged();
    }
    if (this.mNewGridAdapter != null) {
      this.mNewGridAdapter.notifyDataSetChanged();
    }
    this.mScanFontHandler.removeMessages(101);
    this.mScanFontHandler.removeMessages(301);
    this.mScanFontHandler.removeMessages(401);
    this.mUIHandler.removeMessages(201);
    this.mScanFontThread.quit();
    this.mFontPickerListener = null;
    if (this.mInstallReceiver != null)
    {
      this.mContext.unregisterReceiver(this.mInstallReceiver);
      this.mInstallReceiver = null;
    }
    if (this.mGuideDownloadFontDialog != null)
    {
      this.mGuideDownloadFontDialog.setListener(null);
      if (this.mGuideDownloadFontDialog.isShowing()) {
        this.mGuideDownloadFontDialog.dismiss();
      }
    }
    this.mGuideDownloadFontDialog = null;
  }
  
  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    paramAdapterView = (FontInfo)this.mFontList.get(paramInt);
    if (paramAdapterView.isExternalFont()) {
      checkoutExternalFontIsInstall(false);
    }
    do
    {
      return;
      if (paramAdapterView.isFilpFont())
      {
        checkoutFlipFontIsInstall();
        return;
      }
    } while (this.mFontPickerListener == null);
    this.mFontPickerListener.fontItemClick(paramAdapterView.getFormatPath());
  }
  
  public void onResume()
  {
    super.onResume();
    if (this.mFilpFont)
    {
      scanAllFlipFont();
      updateGrid();
    }
  }
  
  public void onStart()
  {
    super.onStart();
    if (this.mGuideDownloadFontDialog == null) {
      this.mGuideDownloadFontDialog = new AdsDialog(this.mContext, null, 0, 0, 0, 0, null, null, null);
    }
    this.mGuideDownloadFontDialog.setTitle(getResources().getString(R.string.title_font_setting));
    this.mGuideDownloadFontDialog.setMessage(getResources().getString(R.string.guide_download_font_dialog_message));
    this.mGuideDownloadFontDialog.setYesButton(getResources().getString(R.string.download), Integer.valueOf(0));
    this.mGuideDownloadFontDialog.setListener(this);
    this.mUIHandler.sendEmptyMessageDelayed(401, 400L);
  }
  
  public void scanCustomPkgFont(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    while (this.mScanFontHandler == null) {
      return;
    }
    this.mScanFontHandler.sendMessage(this.mScanFontHandler.obtainMessage(501, paramString));
  }
  
  public void setCurrentFont(String paramString)
  {
    if (!TextUtils.isEmpty(paramString))
    {
      this.mCurrentFontPath = paramString;
      updateGrid();
    }
  }
  
  public void setExternalFontApkPackName(String paramString)
  {
    this.mExternalFontApkPackName = paramString;
  }
  
  public void setFilpFont(boolean paramBoolean)
  {
    this.mFilpFont = paramBoolean;
  }
  
  public void setFontPickerListener(FontPickerListener paramFontPickerListener)
  {
    this.mFontPickerListener = paramFontPickerListener;
  }
  
  public void setMessageFont(boolean paramBoolean)
  {
    this.mMessageFont = paramBoolean;
  }
  
  public void setNeedMoreFontItem(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      addMoreFontItem();
      return;
    }
    removeMoreFontIem();
  }
  
  public void setSourceId(String paramString)
  {
    this.mSourceId = paramString;
  }
  
  public void setTitleColor(int paramInt1, int paramInt2)
  {
    this.mSelectedTitleColor = paramInt1;
    this.mUnselectedTitleColor = paramInt2;
  }
  
  public void setTypefaceListener(TypefaceListener paramTypefaceListener)
  {
    this.mTypefaceListener = paramTypefaceListener;
  }
  
  public void setViewBackground(Drawable paramDrawable1, Drawable paramDrawable2, Drawable paramDrawable3)
  {
    this.mClickDrawable = paramDrawable1;
    this.mSelectedDrawable = paramDrawable2;
    this.mTitleDrawable = paramDrawable3;
  }
  
  public static abstract interface FontPickerListener
  {
    public abstract void fontItemClick(String paramString);
    
    public abstract boolean isIgnoreFont(String paramString1, String paramString2);
  }
  
  private class GridViewAdapter
    extends BaseAdapter
  {
    private GridViewAdapter() {}
    
    public int getCount()
    {
      return FontPickerFragment.this.mFontList.size();
    }
    
    public Object getItem(int paramInt)
    {
      return FontPickerFragment.this.mFontList.get(paramInt);
    }
    
    public long getItemId(int paramInt)
    {
      return paramInt;
    }
    
    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      paramViewGroup = paramView;
      if (paramView == null) {
        paramViewGroup = FontPickerFragment.this.mLayoutInflater.inflate(R.layout.font_picker_griditem, null, false);
      }
      paramView = (FontInfo)getItem(paramInt);
      TextView localTextView = (TextView)paramViewGroup.findViewById(R.id.font_preview);
      ImageView localImageView = (ImageView)paramViewGroup.findViewById(R.id.more_fonts_view);
      localTextView.setTextColor(FontPickerFragment.this.getResources().getColor(R.color.font_picker_preview_text_color));
      Object localObject1;
      Object localObject2;
      if (paramView != null)
      {
        boolean bool = TextUtils.equals(FontPickerFragment.this.mCurrentFontPath, paramView.getFormatPath());
        localObject1 = paramViewGroup.findViewById(R.id.font_preview_icon_select);
        localObject2 = paramViewGroup.findViewById(R.id.font_preview);
        if (bool)
        {
          ((View)localObject1).setVisibility(0);
          ((View)localObject2).setBackgroundResource(R.drawable.font_picker_outline_selected);
        }
      }
      else
      {
        localObject1 = (TextView)paramViewGroup.findViewById(R.id.font_picker_font_name_preview);
        localObject2 = (ImageView)paramViewGroup.findViewById(R.id.font_icon_download);
        ((TextView)localObject1).setTextColor(-16777216);
        if (paramView != null)
        {
          localTextView.setText("1234567890\nabcdefghij\nABCDEFGHIJ\b");
          localTextView.setTypeface(FontUtils.getFont(FontPickerFragment.this.mContext, paramView.getFormatPath()));
          ((ImageView)localObject2).setVisibility(8);
          ((ImageView)localObject2).setAnimation(null);
          localImageView.setVisibility(8);
          if (!paramView.isDefaultFont()) {
            break label267;
          }
          localTextView.setText("Default");
          localTextView.setTextSize(0, FontPickerFragment.this.mDefaultTextSize);
        }
      }
      for (;;)
      {
        ((TextView)localObject1).setText(paramView.getFontName());
        return paramViewGroup;
        ((View)localObject1).setVisibility(8);
        ((View)localObject2).setBackgroundResource(R.drawable.font_picker_outline_unselected);
        break;
        label267:
        if (paramView.getFontName() == "More Fonts")
        {
          localImageView.setVisibility(0);
          localTextView.setTextSize(0, FontPickerFragment.this.mTextSize);
        }
        else if (paramView.isExternalFont())
        {
          localTextView.setText("");
          localTextView.setBackgroundDrawable(FontPickerFragment.this.getResources().getDrawable(R.drawable.font_preview));
          ((ImageView)localObject2).setVisibility(0);
          ((ImageView)localObject2).startAnimation(AnimationUtils.loadAnimation(FontPickerFragment.this.mContext, R.anim.guide_download));
        }
        else if (paramView.isFilpFont())
        {
          ((TextView)localObject1).setTextColor(FontPickerFragment.this.getResources().getColor(R.color.flip_font_text_color));
          localTextView.setTextColor(FontPickerFragment.this.getResources().getColor(R.color.flip_font_text_color));
        }
        else
        {
          localTextView.setTextSize(0, FontPickerFragment.this.mTextSize);
        }
      }
    }
  }
  
  private class InstallReceiver
    extends BroadcastReceiver
  {
    private InstallReceiver() {}
    
    public void onReceive(Context paramContext, Intent paramIntent)
    {
      paramContext = paramIntent.getData();
      if ((paramContext == null) || (FontPickerFragment.this.mListView == null)) {}
      do
      {
        do
        {
          return;
          paramContext = paramContext.getSchemeSpecificPart();
        } while ((TextUtils.isEmpty(paramContext)) || (!paramContext.equals("com.crazygame.keyboard.font")));
        paramContext = paramIntent.getAction();
        if ("android.intent.action.PACKAGE_ADDED".equals(paramContext))
        {
          FontPickerFragment.this.removeMoreFontIem();
          return;
        }
      } while (!"android.intent.action.PACKAGE_REMOVED".equals(paramContext));
      FontPickerFragment.this.addMoreFontItem();
    }
  }
  
  private class NewGridViewAdapter
    extends BaseAdapter
  {
    private NewGridViewAdapter() {}
    
    public int getCount()
    {
      return FontPickerFragment.this.mFontList.size();
    }
    
    public Object getItem(int paramInt)
    {
      return FontPickerFragment.this.mFontList.get(paramInt);
    }
    
    public long getItemId(int paramInt)
    {
      return paramInt;
    }
    
    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      paramViewGroup = paramView;
      if (paramView == null) {
        paramViewGroup = FontPickerFragment.this.mLayoutInflater.inflate(R.layout.new_font_picker_griditem, null, false);
      }
      paramView = (TextView)paramViewGroup.findViewById(R.id.font_picker_font_name_preview);
      TextView localTextView = (TextView)paramViewGroup.findViewById(R.id.font_preview);
      ImageView localImageView = (ImageView)paramViewGroup.findViewById(R.id.more_fonts_view);
      if (((FontPickerFragment.this.mTypeface == null) || (FontPickerFragment.this.mItemTitleSize <= 0)) && (FontPickerFragment.this.mTypefaceListener != null))
      {
        FontPickerFragment.access$1602(FontPickerFragment.this, FontPickerFragment.this.mTypefaceListener.getItemTitleTypeface());
        FontPickerFragment.access$1702(FontPickerFragment.this, FontPickerFragment.this.mTypefaceListener.getItemTitleSize());
      }
      if (FontPickerFragment.this.mTypeface != null) {
        paramView.setTypeface(FontPickerFragment.this.mTypeface);
      }
      if (FontPickerFragment.this.mItemTitleSize > 0) {
        paramView.setTextSize(FontPickerFragment.this.mItemTitleSize);
      }
      FontInfo localFontInfo = (FontInfo)getItem(paramInt);
      View localView1;
      View localView2;
      if (localFontInfo != null)
      {
        localView1 = paramViewGroup.findViewById(R.id.font_picker_item_container);
        boolean bool = TextUtils.equals(FontPickerFragment.this.mCurrentFontPath, localFontInfo.getFormatPath());
        localView2 = paramViewGroup.findViewById(R.id.font_preview_icon_select);
        localView2.setBackgroundDrawable(FontPickerFragment.this.mSelectedDrawable);
        if (!bool) {
          break label514;
        }
        localView1.setVisibility(8);
        localView2.setVisibility(0);
        if (FontPickerFragment.this.mSelectedTitleColor != 0) {
          paramView.setTextColor(FontPickerFragment.this.mSelectedTitleColor);
        }
      }
      if (FontPickerFragment.this.mListViewWidth <= 0) {
        FontPickerFragment.access$2302(FontPickerFragment.this, FontPickerFragment.this.mListView.getWidth() - FontPickerFragment.this.mListView.getPaddingLeft() - FontPickerFragment.this.mListView.getPaddingRight() - FontPickerFragment.this.mSpacing);
      }
      if (FontPickerFragment.this.mListViewWidth > 0)
      {
        paramInt = FontPickerFragment.this.mListViewWidth / 2;
        int i = (int)(paramInt / FontPickerFragment.this.mThemeRatio);
        int j = (int)FontPickerFragment.this.getResources().getDimension(R.dimen.new_font_picker_title_height);
        FontPickerFragment.this.adjustSize(paramViewGroup, paramInt, i + j);
      }
      if (localFontInfo != null)
      {
        localTextView.setText("1234567890\nabcdefghij\nABCDEFGHIJ\b");
        localTextView.setTypeface(FontUtils.getFont(FontPickerFragment.this.mContext, localFontInfo.getFormatPath()));
        localImageView.setVisibility(8);
        if (!localFontInfo.isDefaultFont()) {
          break label551;
        }
        localTextView.setText("Default");
        localTextView.setTextSize(0, FontPickerFragment.this.mDefaultTextSize);
      }
      for (;;)
      {
        paramView.setText(localFontInfo.getFontName());
        if (FontPickerFragment.this.mClickDrawable != null) {
          FontPickerFragment.this.mListView.setSelector(FontPickerFragment.this.mClickDrawable);
        }
        paramViewGroup.findViewById(R.id.title_container).setBackgroundDrawable(FontPickerFragment.this.mTitleDrawable);
        return paramViewGroup;
        label514:
        localView1.setVisibility(0);
        localView2.setVisibility(8);
        if (FontPickerFragment.this.mUnselectedTitleColor == 0) {
          break;
        }
        paramView.setTextColor(FontPickerFragment.this.mUnselectedTitleColor);
        break;
        label551:
        if (localFontInfo.getFontName() == "More Fonts")
        {
          localImageView.setVisibility(0);
          localTextView.setTextSize(0, FontPickerFragment.this.mTextSize);
        }
        else
        {
          localTextView.setTextSize(0, FontPickerFragment.this.mTextSize);
        }
      }
    }
  }
  
  public static abstract interface TypefaceListener
  {
    public abstract int getItemTitleSize();
    
    public abstract Typeface getItemTitleTypeface();
  }
}
