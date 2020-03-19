package com.android.inputmethod.latin.kkuirearch.fragments;

import android.animation.AnimatorSet;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.h;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.inputmethod.latin.d.r;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;
import com.myandroid.billing.a;
import com.umeng.analytics.MobclickAgent;
import emoji.keyboard.emoticonkeyboard.theme.b;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

public class LocalThemeFragment
  extends BaseFragment
{
  private static final String TAG = "LocalThemeFragment";
  static final String TAG_GO_KEYBOARD_THEME = "theme";
  int[] THEMES_IDS;
  String[] THEMES_NAME;
  final int[] THEMES_PREVIEW = { 2130838530, 2130838533, 2130838529, 2130838289, 2130838531, 2130838532 };
  private Activity mActivity;
  private AnimatorSet mAnimatorSetLocal;
  private final BroadcastReceiver mKaKaThemeReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      if ("emoji.keyboard.emoticonkeyboard.KAKA_THEME_CHANGED".equals(paramAnonymousIntent.getAction()))
      {
        paramAnonymousIntent = paramAnonymousIntent.getExtras().getString("theme_pkg");
        if (!LocalThemeFragment.this.isInstalled(paramAnonymousContext, paramAnonymousIntent)) {
          break label126;
        }
        paramAnonymousContext = new LocalThemeFragment.Theme(LocalThemeFragment.this);
        paramAnonymousContext.id = 17;
        paramAnonymousContext.name = com.android.inputmethod.latin.kkuirearch.utils.d.c(LocalThemeFragment.this.mActivity, paramAnonymousIntent);
        paramAnonymousContext.pkg = paramAnonymousIntent;
        paramAnonymousContext.isPrime = false;
        paramAnonymousContext.preview = 0;
        if (!LocalThemeFragment.this.mThemes.containsKey(paramAnonymousContext.pkg)) {
          LocalThemeFragment.this.mThemes.put(paramAnonymousContext.pkg, paramAnonymousContext);
        }
      }
      for (;;)
      {
        LocalThemeFragment.this.mLocalThemeAdapter.notifyDataSetChanged();
        return;
        label126:
        if (LocalThemeFragment.this.mThemes.containsKey(paramAnonymousIntent)) {
          LocalThemeFragment.this.mThemes.remove(paramAnonymousIntent);
        }
      }
    }
  };
  private ThemeAdapter mLocalThemeAdapter;
  LocalThemeUpdateAsync mLocalThemeTask;
  private View mMainView;
  private String mSelectedThemeName;
  GridView mThemeGrid;
  private final LinkedHashMap<String, Theme> mThemes = new LinkedHashMap();
  
  public LocalThemeFragment() {}
  
  private boolean isInstalled(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext.getPackageInfo(paramString, 0);
      return true;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  private void loadKaKaKeyboardThemes()
  {
    this.mThemes.clear();
    initThemes();
    SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.mActivity);
    Object localObject1;
    if (!localSharedPreferences.getBoolean("keyboard_theme_scanned", false))
    {
      localSharedPreferences.edit().putString("keyboard_theme_pkgs_installed", "").apply();
      localObject1 = this.mActivity.getPackageManager();
    }
    label399:
    for (;;)
    {
      try
      {
        localObject2 = ((PackageManager)localObject1).getInstalledPackages(0);
        localObject1 = "";
        localObject2 = ((List)localObject2).iterator();
        if (((Iterator)localObject2).hasNext())
        {
          localObject3 = (PackageInfo)((Iterator)localObject2).next();
          if ((!b.a(((PackageInfo)localObject3).packageName)) || (b.a(this.mActivity, ((PackageInfo)localObject3).packageName, "preview_img") == null)) {
            break label399;
          }
          localObject1 = (String)localObject1 + ((PackageInfo)localObject3).packageName + ",";
          break label399;
        }
        localSharedPreferences.edit().putString("keyboard_theme_pkgs_installed", (String)localObject1).apply();
      }
      catch (NullPointerException localNullPointerException)
      {
        Object localObject2;
        Object localObject3;
        int j;
        int i;
        localNullPointerException.printStackTrace();
        continue;
      }
      localSharedPreferences.edit().putBoolean("keyboard_theme_scanned", true).apply();
      localObject1 = localSharedPreferences.getString("keyboard_theme_pkgs_installed", "");
      if ((localObject1 != null) && (!((String)localObject1).isEmpty()))
      {
        localObject1 = ((String)localObject1).split(",", -1);
        j = localObject1.length;
        i = 0;
        if (i < j)
        {
          localSharedPreferences = localObject1[i];
          if (b.a(localSharedPreferences))
          {
            localObject2 = new Theme();
            ((Theme)localObject2).id = 17;
            ((Theme)localObject2).name = com.android.inputmethod.latin.kkuirearch.utils.d.c(this.mActivity, localSharedPreferences);
            localObject3 = localSharedPreferences.split("\\.");
            if ((localObject3.length > 0) && (this.mSelectedThemeName.equals(localObject3[(localObject3.length - 1)]))) {
              PreferenceManager.getDefaultSharedPreferences(this.mActivity).edit().putString("keyboard_theme_name", ((Theme)localObject2).name).apply();
            }
            ((Theme)localObject2).pkg = localSharedPreferences;
            ((Theme)localObject2).preview = 0;
            ((Theme)localObject2).isPrime = false;
            this.mThemes.put(((Theme)localObject2).pkg, localObject2);
          }
          i += 1;
          continue;
        }
      }
      return;
    }
  }
  
  private void loadSupportedGOThemees()
  {
    XmlResourceParser localXmlResourceParser = getResources().getXml(2131099655);
    for (;;)
    {
      String str2;
      try
      {
        if (localXmlResourceParser.getEventType() != 1)
        {
          if ((localXmlResourceParser.getEventType() == 2) && ("theme".equals(localXmlResourceParser.getName())))
          {
            String str1 = localXmlResourceParser.getAttributeValue(0);
            str2 = localXmlResourceParser.getAttributeValue(1);
            a.a();
            if ((isInstalled(this.mActivity, str2)) && (!this.mThemes.containsKey(str2)))
            {
              String str3 = localXmlResourceParser.getAttributeValue(2);
              String str4 = localXmlResourceParser.getAttributeValue(3);
              Theme localTheme = new Theme();
              localTheme.id = Integer.parseInt(str1);
              localTheme.pkg = str2;
              localTheme.name = str3;
              localTheme.preview = getResources().getIdentifier(str4, "drawable", this.mActivity.getPackageName());
              this.mThemes.put(str2, localTheme);
            }
          }
          else
          {
            localXmlResourceParser.next();
          }
        }
        else {
          return;
        }
      }
      catch (Exception localException)
      {
        r.a(Log.getStackTraceString(localException), true);
      }
      a.a();
      if ((!isInstalled(this.mActivity, str2)) && (this.mThemes.containsKey(str2))) {
        this.mThemes.remove(str2);
      }
    }
  }
  
  private int revertToDisplayPosition(int paramInt1, int paramInt2)
  {
    if (emoji.keyboard.emoticonkeyboard.extras.d.e(this.mActivity)) {
      if (paramInt2 >= 5) {}
    }
    while (paramInt2 < 6)
    {
      return paramInt2;
      return paramInt1 - (paramInt2 + 1 - 5);
    }
    return paramInt1 - (paramInt2 + 1 - 6);
  }
  
  void initThemes()
  {
    this.THEMES_IDS = getResources().getIntArray(2131361852);
    this.THEMES_NAME = getResources().getStringArray(2131361793);
    Theme localTheme1 = new Theme();
    localTheme1.id = this.THEMES_IDS[0];
    localTheme1.name = this.THEMES_NAME[0];
    localTheme1.preview = this.THEMES_PREVIEW[0];
    Theme localTheme2 = new Theme();
    localTheme2.id = this.THEMES_IDS[1];
    localTheme2.name = this.THEMES_NAME[1];
    localTheme2.preview = this.THEMES_PREVIEW[1];
    localTheme2.isPrime = true;
    Theme localTheme3 = new Theme();
    localTheme3.id = this.THEMES_IDS[2];
    localTheme3.name = this.THEMES_NAME[2];
    localTheme3.preview = this.THEMES_PREVIEW[2];
    Theme localTheme4 = new Theme();
    localTheme4.id = this.THEMES_IDS[3];
    localTheme4.name = this.THEMES_NAME[3];
    localTheme4.preview = this.THEMES_PREVIEW[3];
    localTheme4.isPrime = true;
    Theme localTheme5 = new Theme();
    localTheme5.id = this.THEMES_IDS[4];
    localTheme5.name = this.THEMES_NAME[4];
    localTheme5.preview = this.THEMES_PREVIEW[4];
    Theme localTheme6 = new Theme();
    localTheme6.id = this.THEMES_IDS[5];
    if (!emoji.keyboard.emoticonkeyboard.extras.d.e(this.mActivity)) {}
    for (localTheme6.name = this.THEMES_NAME[5];; localTheme6.name = this.THEMES_NAME[0])
    {
      localTheme6.preview = this.THEMES_PREVIEW[5];
      if (!emoji.keyboard.emoticonkeyboard.extras.d.e(this.mActivity)) {
        break;
      }
      this.mThemes.put(localTheme6.name, localTheme6);
      this.mThemes.put(localTheme5.name, localTheme5);
      this.mThemes.put(localTheme3.name, localTheme3);
      this.mThemes.put(localTheme2.name, localTheme2);
      this.mThemes.put(localTheme4.name, localTheme4);
      return;
    }
    this.mThemes.put(localTheme1.name, localTheme1);
    this.mThemes.put(localTheme5.name, localTheme5);
    this.mThemes.put(localTheme3.name, localTheme3);
    this.mThemes.put(localTheme2.name, localTheme2);
    this.mThemes.put(localTheme4.name, localTheme4);
    this.mThemes.put(localTheme6.name, localTheme6);
  }
  
  public void onAttach(Activity paramActivity)
  {
    super.onAttach(paramActivity);
    this.mActivity = paramActivity;
    this.mLocalThemeAdapter = new ThemeAdapter(this.mThemes);
  }
  
  public boolean onContextItemSelected(MenuItem paramMenuItem)
  {
    super.onContextItemSelected(paramMenuItem);
    Object localObject = (AdapterView.AdapterContextMenuInfo)paramMenuItem.getMenuInfo();
    localObject = (Theme)this.mLocalThemeAdapter.getItem(((AdapterView.AdapterContextMenuInfo)localObject).position);
    switch (paramMenuItem.getItemId())
    {
    default: 
      return super.onContextItemSelected(paramMenuItem);
    }
    paramMenuItem = new Intent();
    paramMenuItem.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
    paramMenuItem.setData(Uri.fromParts("package", ((Theme)localObject).pkg, null));
    paramMenuItem.setFlags(268435456);
    this.mActivity.startActivity(paramMenuItem);
    return true;
  }
  
  public void onCreateContextMenu(ContextMenu paramContextMenu, View paramView, ContextMenu.ContextMenuInfo paramContextMenuInfo)
  {
    super.onCreateContextMenu(paramContextMenu, paramView, paramContextMenuInfo);
    paramView = (AdapterView.AdapterContextMenuInfo)paramContextMenuInfo;
    paramContextMenuInfo = (Theme)this.mLocalThemeAdapter.getItem(paramView.position);
    if (emoji.keyboard.emoticonkeyboard.extras.d.e(this.mActivity)) {
      if ((paramView.position >= 5) && (!paramContextMenuInfo.name.equals(this.mSelectedThemeName))) {
        this.mActivity.getMenuInflater().inflate(2131820548, paramContextMenu);
      }
    }
    while ((paramView.position < 6) || (paramContextMenuInfo.name.equals(this.mSelectedThemeName))) {
      return;
    }
    this.mActivity.getMenuInflater().inflate(2131820548, paramContextMenu);
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    paramLayoutInflater = paramLayoutInflater.inflate(2130968666, paramViewGroup, false);
    this.mMainView = paramLayoutInflater;
    this.mThemeGrid = ((GridView)paramLayoutInflater.findViewById(2131755458));
    this.mThemeGrid.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        PreferenceManager.getDefaultSharedPreferences(LocalThemeFragment.this.mActivity).edit().putString("keyboard_theme_id", String.valueOf(((LocalThemeFragment.Theme)LocalThemeFragment.this.mLocalThemeAdapter.getItem(paramAnonymousInt)).id)).commit();
        PreferenceManager.getDefaultSharedPreferences(LocalThemeFragment.this.mActivity).edit().putString("keyboard_theme_pkg", ((LocalThemeFragment.Theme)LocalThemeFragment.this.mLocalThemeAdapter.getItem(paramAnonymousInt)).pkg).commit();
        PreferenceManager.getDefaultSharedPreferences(LocalThemeFragment.this.mActivity).edit().putString("keyboard_theme_name", ((LocalThemeFragment.Theme)LocalThemeFragment.this.mLocalThemeAdapter.getItem(paramAnonymousInt)).name).commit();
        PreferenceManager.getDefaultSharedPreferences(LocalThemeFragment.this.mActivity).edit().remove("main_keyboard_bg").commit();
        PreferenceManager.getDefaultSharedPreferences(LocalThemeFragment.this.mActivity).edit().remove("suggest_strip_bg").commit();
        LocalThemeFragment.access$202(LocalThemeFragment.this, ((LocalThemeFragment.Theme)LocalThemeFragment.this.mLocalThemeAdapter.getItem(paramAnonymousInt)).name);
        LocalThemeFragment.this.mLocalThemeAdapter.notifyDataSetChanged();
        Toast.makeText(LocalThemeFragment.this.mActivity, ((LocalThemeFragment.Theme)LocalThemeFragment.this.mLocalThemeAdapter.getItem(paramAnonymousInt)).name + " " + LocalThemeFragment.this.getString(2131296869), 0).show();
        if (((emoji.keyboard.emoticonkeyboard.extras.d.e(LocalThemeFragment.this.mActivity)) && (paramAnonymousInt == 0)) || ((!emoji.keyboard.emoticonkeyboard.extras.d.e(LocalThemeFragment.this.mActivity)) && (paramAnonymousInt == 5))) {
          emoji.keyboard.emoticonkeyboard.extras.d.a(LocalThemeFragment.this.getActivity().getApplication(), "LocalTheme_New_Default");
        }
        if (!emoji.keyboard.emoticonkeyboard.extras.d.e(LocalThemeFragment.this.mActivity)) {
          if (paramAnonymousInt > 5) {
            LocalThemeFragment.this.mLogger.logEvent("THEME_SET");
          }
        }
        while (paramAnonymousInt <= 4) {
          return;
        }
        LocalThemeFragment.this.mLogger.logEvent("THEME_SET");
      }
    });
    registerForContextMenu(this.mThemeGrid);
    this.mThemeGrid.setAdapter(this.mLocalThemeAdapter);
    this.mSelectedThemeName = PreferenceManager.getDefaultSharedPreferences(this.mActivity).getString("keyboard_theme_name", "Default");
    this.mLocalThemeAdapter.notifyDataSetChanged();
    return paramLayoutInflater;
  }
  
  public void onDestroy()
  {
    this.mThemes.clear();
    if (this.mThemeGrid != null) {
      this.mThemeGrid.setAdapter(null);
    }
    System.gc();
    super.onDestroy();
  }
  
  public void onPause()
  {
    super.onPause();
    this.mActivity.unregisterReceiver(this.mKaKaThemeReceiver);
    this.mLocalThemeTask.cancel(true);
    MobclickAgent.onPageEnd(getString(2131296793));
  }
  
  public void onResume()
  {
    super.onResume();
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("emoji.keyboard.emoticonkeyboard.KAKA_THEME_CHANGED");
    this.mActivity.registerReceiver(this.mKaKaThemeReceiver, localIntentFilter);
    this.mLocalThemeTask = new LocalThemeUpdateAsync();
    this.mLocalThemeTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    MobclickAgent.onPageStart(getString(2131296793));
  }
  
  public void onStart()
  {
    super.onStart();
  }
  
  public void onStop()
  {
    super.onStop();
  }
  
  class LocalThemeUpdateAsync
    extends AsyncTask<Void, Void, Void>
  {
    LocalThemeUpdateAsync() {}
    
    protected Void doInBackground(Void... paramVarArgs)
    {
      LocalThemeFragment.this.loadKaKaKeyboardThemes();
      return null;
    }
    
    protected void onPostExecute(Void paramVoid)
    {
      LocalThemeFragment.access$202(LocalThemeFragment.this, PreferenceManager.getDefaultSharedPreferences(LocalThemeFragment.this.mActivity).getString("keyboard_theme_name", "Default"));
      LocalThemeFragment.this.mLocalThemeAdapter.notifyDataSetChanged();
    }
  }
  
  class Theme
  {
    int id;
    boolean isPrime = false;
    String name;
    String pkg = "";
    int preview;
    
    Theme() {}
  }
  
  final class ThemeAdapter
    extends BaseAdapter
  {
    private LinkedHashMap<String, LocalThemeFragment.Theme> mThemesPreview;
    
    public ThemeAdapter()
    {
      Object localObject;
      this.mThemesPreview = localObject;
    }
    
    public final int getCount()
    {
      return this.mThemesPreview.size();
    }
    
    public final Object getItem(int paramInt)
    {
      return ((LocalThemeFragment.Theme[])this.mThemesPreview.values().toArray(new LocalThemeFragment.Theme[0]))[LocalThemeFragment.this.revertToDisplayPosition(this.mThemesPreview.size(), paramInt)];
    }
    
    public final long getItemId(int paramInt)
    {
      return 0L;
    }
    
    public final View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      LocalThemeFragment.Theme localTheme;
      GenericDraweeHierarchy localGenericDraweeHierarchy;
      if (paramView == null)
      {
        paramView = LocalThemeFragment.this.mActivity.getLayoutInflater().inflate(2130968828, paramViewGroup, false);
        paramViewGroup = new LocalThemeFragment.ViewHolder(null);
        paramViewGroup.textView = ((TextView)paramView.findViewById(2131755390));
        paramViewGroup.previewImage = ((SimpleDraweeView)paramView.findViewById(2131755388));
        paramViewGroup.selectedImage = ((ImageView)paramView.findViewById(2131755822));
        paramView.setTag(paramViewGroup);
        localTheme = (LocalThemeFragment.Theme)getItem(paramInt);
        paramViewGroup.textView.setText(localTheme.name);
        localGenericDraweeHierarchy = (GenericDraweeHierarchy)paramViewGroup.previewImage.getHierarchy();
        if (localTheme.preview == 0) {
          break label175;
        }
        localGenericDraweeHierarchy.setPlaceholderImage(localTheme.preview);
        paramViewGroup.previewImage.setHierarchy(localGenericDraweeHierarchy);
      }
      for (;;)
      {
        if (!LocalThemeFragment.this.mSelectedThemeName.equals(localTheme.name)) {
          break label209;
        }
        paramViewGroup.selectedImage.setVisibility(0);
        return paramView;
        paramViewGroup = (LocalThemeFragment.ViewHolder)paramView.getTag();
        break;
        label175:
        localGenericDraweeHierarchy.setPlaceholderImage(b.a(LocalThemeFragment.this.mActivity, localTheme.pkg, "preview_img"));
        paramViewGroup.previewImage.setHierarchy(localGenericDraweeHierarchy);
      }
      label209:
      paramViewGroup.selectedImage.setVisibility(8);
      return paramView;
    }
  }
  
  private static class ViewHolder
  {
    SimpleDraweeView previewImage;
    ImageView selectedImage;
    TextView textView;
    
    private ViewHolder() {}
  }
}
