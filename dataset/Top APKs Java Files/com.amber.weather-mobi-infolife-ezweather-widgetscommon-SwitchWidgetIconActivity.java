package mobi.infolife.ezweather.widgetscommon;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import mobi.infolife.ezweather.e.d;

public class SwitchWidgetIconActivity
  extends Activity
{
  Context context;
  ListView iconSetListView;
  ArrayList<String> iconsetNameList = new ArrayList();
  List<String> iconsetPkgNameList = new ArrayList();
  boolean isIconsetAllowedChange = false;
  IconSetListAdapter mAdapter;
  private View.OnClickListener mClickListener = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      if (paramAnonymousView.getId() == 2131689757)
      {
        SwitchWidgetIconActivity.this.finish();
        SwitchWidgetIconActivity.this.overridePendingTransition(0, 0);
      }
    }
  };
  LinearLayout moreLayout;
  LinearLayout okLayout;
  String widgetPkgName;
  
  public SwitchWidgetIconActivity() {}
  
  private boolean getBooleanConfig(String paramString)
  {
    boolean bool = false;
    Context localContext = WeatherUtilsLibrary.getPluginAppContext(this.context, paramString);
    if (localContext != null) {}
    try
    {
      bool = localContext.getResources().getBoolean(d.a(this.context, "isAllowChange", "bool", paramString));
      return bool;
    }
    catch (Exception paramString) {}
    return false;
  }
  
  public static List<PackageInfo> getInstalledAppList(Context paramContext, int paramInt)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext = paramContext.getInstalledPackages(paramInt);
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  private void getInstalledIconSet(Context paramContext)
  {
    this.iconsetNameList.add(getResources().getString(2131231428));
    this.iconsetPkgNameList.add(this.widgetPkgName);
    PackageManager localPackageManager = paramContext.getPackageManager();
    Iterator localIterator = getInstalledAppList(paramContext, 8192).iterator();
    paramContext = null;
    label189:
    for (;;)
    {
      if (localIterator.hasNext())
      {
        String str2 = ((PackageInfo)localIterator.next()).packageName;
        try
        {
          ApplicationInfo localApplicationInfo = localPackageManager.getApplicationInfo(str2, 128);
          paramContext = localApplicationInfo;
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          for (;;)
          {
            localNameNotFoundException.printStackTrace();
          }
          str1 = paramContext.metaData.getString("EZWEATHER_PLUGIN");
          if (str1 == null) {
            break label189;
          }
        }
        if (paramContext != null)
        {
          String str1;
          if ((paramContext.metaData != null) && (!"".equals(str1))) {
            if ((str1.equals("mobi.infolife.ezweather.plugin.iconset")) && (getBooleanConfig(str2)))
            {
              this.iconsetNameList.add(localPackageManager.getApplicationLabel(paramContext).toString());
              this.iconsetPkgNameList.add(str2);
            }
          }
        }
      }
      else
      {
        return;
      }
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903312);
    this.context = this;
    paramBundle = ViewUtilsLibrary.createTypeface(this.context, "Roboto Regular.ttf");
    this.iconSetListView = ((ListView)findViewById(2131689755));
    ((TextView)findViewById(2131689622)).setTypeface(paramBundle);
    this.moreLayout = ((LinearLayout)findViewById(2131689756));
    this.okLayout = ((LinearLayout)findViewById(2131689757));
    this.moreLayout.setOnClickListener(this.mClickListener);
    this.okLayout.setOnClickListener(this.mClickListener);
    this.iconSetListView.setDivider(null);
    this.widgetPkgName = getIntent().getStringExtra("widget_pkg_name");
    getInstalledIconSet(this.context);
    this.mAdapter = new IconSetListAdapter(this.context);
    this.iconSetListView.setAdapter(this.mAdapter);
    int i = PreferencesLibrary.getSelectedPosition(this.context, this.widgetPkgName);
    paramBundle = PreferencesLibrary.getIconsetName(this.context, this.widgetPkgName);
    if ((i < this.iconsetNameList.size()) && (((String)this.iconsetNameList.get(i)).equals(paramBundle))) {}
    for (;;)
    {
      this.iconSetListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
      {
        public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
        {
          PreferencesLibrary.setWidgetIconPkgNameByPkgName(SwitchWidgetIconActivity.this.context, SwitchWidgetIconActivity.this.widgetPkgName, (String)SwitchWidgetIconActivity.this.iconsetPkgNameList.get(paramAnonymousInt));
          SwitchWidgetIconActivity.this.mAdapter.setSelectedPostion(paramAnonymousInt);
          PreferencesLibrary.setIconsetName(SwitchWidgetIconActivity.this.context, SwitchWidgetIconActivity.this.widgetPkgName, (String)SwitchWidgetIconActivity.this.iconsetNameList.get(paramAnonymousInt));
          Log.d("----------", "------icon------" + (String)SwitchWidgetIconActivity.this.iconsetNameList.get(paramAnonymousInt));
          ViewUtilsLibrary.startUpdateViewService(SwitchWidgetIconActivity.this.context);
        }
      });
      return;
      PreferencesLibrary.setWidgetIconPkgNameByPkgName(this.context, this.widgetPkgName, this.widgetPkgName);
      this.mAdapter.setSelectedPostion(0);
      PreferencesLibrary.setIconsetName(this.context, this.widgetPkgName, "Default");
      ViewUtilsLibrary.startUpdateViewService(this.context);
    }
  }
  
  class IconSetListAdapter
    extends BaseAdapter
  {
    private LayoutInflater mInflater;
    Object object = new Object();
    
    public IconSetListAdapter(Context paramContext)
    {
      this.mInflater = LayoutInflater.from(paramContext);
    }
    
    public int getCount()
    {
      return SwitchWidgetIconActivity.this.iconsetNameList.size();
    }
    
    public Object getItem(int paramInt)
    {
      return SwitchWidgetIconActivity.this.iconsetNameList.get(paramInt);
    }
    
    public long getItemId(int paramInt)
    {
      return paramInt;
    }
    
    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      Typeface localTypeface = Typeface.create("sans-serif-light", 0);
      if (paramView == null)
      {
        paramView = this.mInflater.inflate(2130903187, null);
        paramViewGroup = new SwitchWidgetIconActivity.ViewHolder(SwitchWidgetIconActivity.this);
        paramViewGroup.iconsetItemLayout = ((LinearLayout)SwitchWidgetIconActivity.this.findViewById(2131690181));
        paramViewGroup.title = ((TextView)paramView.findViewById(2131690183));
        paramViewGroup.icon = ((ImageView)paramView.findViewById(2131690182));
        paramView.setTag(paramViewGroup);
      }
      for (;;)
      {
        paramViewGroup.title.setText((CharSequence)SwitchWidgetIconActivity.this.iconsetNameList.get(paramInt));
        paramViewGroup.title.setTypeface(localTypeface);
        if (paramInt != PreferencesLibrary.getSelectedPosition(SwitchWidgetIconActivity.this.context, SwitchWidgetIconActivity.this.widgetPkgName)) {
          break;
        }
        paramViewGroup.icon.setImageResource(2130838012);
        return paramView;
        paramViewGroup = (SwitchWidgetIconActivity.ViewHolder)paramView.getTag();
      }
      paramViewGroup.icon.setImageResource(2130838011);
      return paramView;
    }
    
    public void setSelectedPostion(int paramInt)
    {
      PreferencesLibrary.setSelectedPosition(SwitchWidgetIconActivity.this.context, SwitchWidgetIconActivity.this.widgetPkgName, paramInt);
      notifyDataSetChanged();
    }
  }
  
  public class ViewHolder
  {
    public ImageView icon;
    public LinearLayout iconsetItemLayout;
    public TextView title;
    
    public ViewHolder() {}
  }
}
