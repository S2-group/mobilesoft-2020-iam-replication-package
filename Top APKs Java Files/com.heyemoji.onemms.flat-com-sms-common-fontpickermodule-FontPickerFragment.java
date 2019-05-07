package com.sms.common.fontpickermodule;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FontPickerFragment
  extends Fragment
  implements AdapterView.OnItemClickListener
{
  private static final String DEFAULT_FONT_NAME = "Default";
  private static final String DIRECTORY_FONT = "font";
  private static final String DIRECTORY_FONTS = "fonts";
  private static final String FLIP_FONT_PKG = "com.monotype.android.font";
  private static final int MSG_SCAN_ALL_FLIP_FONT = 101;
  private static final int MSG_UPDATE_UI = 201;
  private static final String NO_DIRECTORY = "";
  private static final String SAMPLE_STRING = "1234567890\nabcdefghij\nABCDEFGHIJ\b";
  private static final long SCAN_ALL_FLIP_FONT_DELAY = 200L;
  private static final String SUFFIX_OTF = ".otf";
  private static final String SUFFIX_TTF = ".ttf";
  private GridViewAdapter mAdapter;
  private Context mContext;
  private String mCurrentFontPath;
  private float mDefaultTextSize;
  private List<FontInfo> mFontList = new ArrayList();
  private FontPickerListener mFontPickerListener;
  private LayoutInflater mLayoutInflater;
  private GridView mListView;
  private PackageManager mPackageMgr;
  private Handler mScanFontHandler;
  private HandlerThread mScanFontThread;
  private String mSelfPkgName;
  private float mTextSize;
  private Handler mUIHandler;
  
  public FontPickerFragment() {}
  
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
        }
        FontPickerFragment.this.scanCurrentAppFont();
        FontPickerFragment.this.scanAllFlipFont();
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
        }
        FontPickerFragment.this.updateGrid();
        return true;
      }
    });
    this.mScanFontHandler.sendEmptyMessageDelayed(101, 200L);
  }
  
  private void initView(View paramView)
  {
    this.mListView = ((GridView)paramView.findViewById(R.id.font_picker_gridview));
    this.mAdapter = new GridViewAdapter();
    this.mListView.setAdapter(this.mAdapter);
    this.mListView.setOnItemClickListener(this);
    this.mFontList.add(new FontInfo(this.mSelfPkgName, "default"));
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
    scanFontFromPkg(this.mSelfPkgName, "");
    scanFontFromPkg(this.mSelfPkgName, "font");
    scanFontFromPkg(this.mSelfPkgName, "fonts");
  }
  
  private void scanFontFromPkg(String paramString1, String paramString2)
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
        if (((arrayOfString[i].endsWith(".ttf")) || (arrayOfString[i].endsWith(".otf"))) && (this.mFontPickerListener != null) && (!this.mFontPickerListener.isIgnoreFont(paramString1, arrayOfString[i])))
        {
          if (!TextUtils.isEmpty(paramString2))
          {
            localObject = paramString2 + File.separator + arrayOfString[i];
            this.mFontList.add(new FontInfo(paramString1, (String)localObject));
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
    }
  }
  
  private void showSelect(View paramView, boolean paramBoolean)
  {
    View localView = paramView.findViewById(R.id.font_preview_icon_select);
    if (localView != null)
    {
      paramView = paramView.findViewById(R.id.font_preview);
      if (paramBoolean)
      {
        localView.setVisibility(0);
        paramView.setBackgroundResource(R.drawable.font_picker_outline_selected);
      }
    }
    else
    {
      return;
    }
    paramView.setBackgroundResource(R.drawable.font_picker_outline_unselected);
    localView.setVisibility(8);
  }
  
  private void updateGrid()
  {
    if (this.mAdapter != null) {
      this.mAdapter.notifyDataSetChanged();
    }
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.mContext = getActivity().getApplicationContext();
    this.mLayoutInflater = getActivity().getLayoutInflater();
    this.mSelfPkgName = this.mContext.getPackageName();
    this.mPackageMgr = this.mContext.getPackageManager();
    paramBundle = this.mContext.getResources();
    this.mDefaultTextSize = paramBundle.getDimension(R.dimen.font_picker_default_preview_text_size);
    this.mTextSize = paramBundle.getDimension(R.dimen.font_picker_preview_text_size);
    this.mCurrentFontPath = FontUtils.assembleFontPath(this.mSelfPkgName, "default");
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    paramLayoutInflater = paramLayoutInflater.inflate(R.layout.font_picker_fragment, null, false);
    initView(paramLayoutInflater);
    initScanFont();
    return paramLayoutInflater;
  }
  
  public void onDestroy()
  {
    super.onDestroy();
    this.mFontList.clear();
    this.mAdapter.notifyDataSetChanged();
    this.mScanFontHandler.removeMessages(101);
    this.mUIHandler.removeMessages(201);
    this.mScanFontThread.quit();
    this.mFontPickerListener = null;
  }
  
  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    paramAdapterView = (FontInfo)this.mFontList.get(paramInt);
    if (this.mFontPickerListener != null) {
      this.mFontPickerListener.fontItemClick(paramAdapterView.getFormatPath());
    }
  }
  
  public void setCurrentFont(String paramString)
  {
    if (!TextUtils.isEmpty(paramString))
    {
      this.mCurrentFontPath = paramString;
      updateGrid();
    }
  }
  
  public void setFontPickerListener(FontPickerListener paramFontPickerListener)
  {
    this.mFontPickerListener = paramFontPickerListener;
  }
  
  public static abstract interface FontPickerListener
  {
    public abstract void fontItemClick(String paramString);
    
    public abstract boolean isIgnoreFont(String paramString1, String paramString2);
  }
  
  public class GridViewAdapter
    extends BaseAdapter
  {
    public GridViewAdapter() {}
    
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
      paramView = (TextView)paramViewGroup.findViewById(R.id.font_preview);
      TextView localTextView = (TextView)paramViewGroup.findViewById(R.id.font_picker_font_name_preview);
      FontInfo localFontInfo = (FontInfo)getItem(paramInt);
      if (localFontInfo != null)
      {
        if (!TextUtils.equals(FontPickerFragment.this.mCurrentFontPath, localFontInfo.getFormatPath())) {
          break label157;
        }
        FontPickerFragment.this.showSelect(paramViewGroup, true);
        paramView.setText("1234567890\nabcdefghij\nABCDEFGHIJ\b");
        paramView.setTypeface(FontUtils.getFont(FontPickerFragment.this.mContext, localFontInfo.getFormatPath()));
        if (!localFontInfo.isDefaultFont()) {
          break label169;
        }
        paramView.setText("Default");
        paramView.setTextSize(0, FontPickerFragment.this.mDefaultTextSize);
      }
      for (;;)
      {
        localTextView.setText(localFontInfo.getFontName());
        localTextView.setTextColor(-7829368);
        return paramViewGroup;
        label157:
        FontPickerFragment.this.showSelect(paramViewGroup, false);
        break;
        label169:
        paramView.setTextSize(0, FontPickerFragment.this.mTextSize);
      }
    }
  }
}
