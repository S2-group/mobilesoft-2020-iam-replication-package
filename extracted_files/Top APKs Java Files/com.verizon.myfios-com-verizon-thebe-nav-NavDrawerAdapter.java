package com.verizon.thebe.nav;

import android.app.Activity;
import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.content.Loader.ForceLoadContentObserver;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.verizon.activeandroid.content.ContentProvider;
import com.verizon.activeandroid.query.From;
import com.verizon.activeandroid.query.Select;
import com.verizon.android.analytics.Track;
import com.verizon.persona.model.DashBoard.TileInfo;
import com.verizon.persona.model.NavGroup;
import com.verizon.persona.model.NavGroup.NavItem;
import com.verizon.persona.model.Persona;
import com.verizon.sso.SsoClient;
import com.verizon.thebe.config.AppConfig;
import com.verizon.thebe.util.AppUtil;
import com.verizon.thebe.util.AsyncHelper.AsyncSetText;
import com.verizon.thebe.util.AsyncHelper.TaskHandler;
import com.verizon.unity.AppBaseActivity;
import com.verizon.util.BitmapManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NavDrawerAdapter
  extends BaseExpandableListAdapter
  implements LoaderManager.LoaderCallbacks<Cursor>
{
  private static final String WELCOME_WIDGET_TYPE = "Welcome";
  private static List<NavGroup> sNavGroups = null;
  private static HashMap<String, Integer> sPkgVersions = null;
  BitmapManager bitmapManager = null;
  LayoutInflater inflater = null;
  private Context mActivity;
  TextView notificationText = null;
  
  public NavDrawerAdapter(Activity paramActivity)
  {
    this.mActivity = paramActivity;
    this.inflater = LayoutInflater.from(paramActivity);
    this.bitmapManager = BitmapManager.getInstance(paramActivity, 1);
    ((AppBaseActivity)paramActivity).getSupportLoaderManager().initLoader(hashCode(), null, this);
  }
  
  private View getHeaderView(final View paramView)
  {
    View localView;
    if (paramView != null)
    {
      localView = paramView;
      if (paramView.getTag() != null) {}
    }
    else
    {
      localView = this.inflater.inflate(2130903300, null);
      paramView = new ViewHolder();
      paramView.option1 = ((TextView)localView.findViewById(2131559292));
      paramView.option2 = ((TextView)localView.findViewById(2131559293));
      paramView.option3 = ((TextView)localView.findViewById(2131559294));
      paramView.profileName = ((TextView)localView.findViewById(2131559287));
      paramView.profileId = ((TextView)localView.findViewById(2131559288));
      paramView.optionsIndicator = ((ImageView)localView.findViewById(2131559289));
      paramView.accountView = ((RelativeLayout)localView.findViewById(2131559301));
      paramView.optionsList = ((LinearLayout)localView.findViewById(2131559290));
      paramView.divider = localView.findViewById(2131559291);
      localView.setTag(paramView);
    }
    paramView = (ViewHolder)localView.getTag();
    if ((getNavItem() != null) && (!"B".equalsIgnoreCase(Persona.getCustomerType())))
    {
      paramView.option1.setVisibility(0);
      paramView.divider.setVisibility(0);
      paramView.option1.setText(2131099912);
    }
    paramView.option2.setText(2131099761);
    paramView.option3.setText(2131099762);
    if (SsoClient.isLoggedIn(this.mActivity))
    {
      new AsyncHelper.AsyncSetText(paramView.profileName, new AsyncHelper.TaskHandler()
      {
        public String performTask(String... paramAnonymousVarArgs)
        {
          paramAnonymousVarArgs = (DashBoard.TileInfo)new Select().from(DashBoard.TileInfo.class).where("widgetType = 'Welcome'").executeSingle();
          if (paramAnonymousVarArgs != null) {
            return paramAnonymousVarArgs.body;
          }
          return "";
        }
      }).execute(new String[0]);
      paramView.profileId.setText("(" + SsoClient.getUserId(this.mActivity) + ")");
    }
    paramView.accountView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        try
        {
          if (paramView.optionsList.getVisibility() == 8)
          {
            paramView.optionsList.setVisibility(0);
            paramAnonymousView = ResourcesCompat.getDrawable(NavDrawerAdapter.this.mActivity.getResources(), 2130838156, null);
            paramView.optionsIndicator.setImageDrawable(paramAnonymousView);
          }
          for (;;)
          {
            DrawableCompat.setTint(DrawableCompat.wrap(paramView.optionsIndicator.getDrawable()).mutate(), NavDrawerAdapter.this.mActivity.getResources().getColor(2131492913));
            return;
            paramView.optionsList.setVisibility(8);
            paramAnonymousView = ResourcesCompat.getDrawable(NavDrawerAdapter.this.mActivity.getResources(), 2130837776, null);
            paramView.optionsIndicator.setImageDrawable(paramAnonymousView);
          }
          return;
        }
        catch (Exception paramAnonymousView)
        {
          if (AppConfig.DEBUG) {
            paramAnonymousView.printStackTrace();
          }
        }
      }
    });
    paramView.option1.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        try
        {
          NavDrawerAdapter.this.getNavItem().getIntent(NavDrawerAdapter.this.mActivity).send();
          NavDrawerAdapter.this.closeDrawer();
          return;
        }
        catch (Exception paramAnonymousView)
        {
          for (;;)
          {
            if (AppConfig.DEBUG) {
              paramAnonymousView.printStackTrace();
            }
          }
        }
      }
    });
    paramView.option2.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse("app_internal://com.verizon.thebe/settings"));
        paramAnonymousView.getContext().startActivity(localIntent);
        NavDrawerAdapter.this.closeDrawer();
      }
    });
    paramView.option3.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        AppUtil.signOut(paramAnonymousView.getContext());
        NavDrawerAdapter.this.closeDrawer();
      }
    });
    return localView;
  }
  
  private HashMap<String, Integer> getInstalledVersions()
  {
    Object localObject = this.mActivity.getPackageManager();
    HashMap localHashMap = new HashMap(40);
    localObject = ((PackageManager)localObject).getInstalledPackages(0);
    int i = 0;
    while (i < ((List)localObject).size())
    {
      PackageInfo localPackageInfo = (PackageInfo)((List)localObject).get(i);
      localHashMap.put(localPackageInfo.packageName, Integer.valueOf(localPackageInfo.versionCode));
      i += 1;
    }
    return localHashMap;
  }
  
  private NavGroup.NavItem getNavItem()
  {
    return (NavGroup.NavItem)new Select().from(NavGroup.NavItem.class).where("title = 'Profile'").executeSingle();
  }
  
  protected void closeDrawer() {}
  
  protected View getAppItemView(View paramView, NavGroup paramNavGroup, final NavGroup.NavItem paramNavItem)
  {
    if (sPkgVersions == null) {
      sPkgVersions = getInstalledVersions();
    }
    View localView;
    if (paramView != null)
    {
      localView = paramView;
      if ("appitem".equals((String)paramView.getTag())) {}
    }
    else
    {
      localView = this.inflater.inflate(2130903297, null);
    }
    paramView = (ImageView)localView.findViewById(2131559295);
    TextView localTextView1 = (TextView)localView.findViewById(2131559297);
    TextView localTextView2 = (TextView)localView.findViewById(2131559298);
    ImageView localImageView = (ImageView)localView.findViewById(2131559296);
    if ((paramNavGroup.imagebaseUri != null) && (paramNavGroup.imagebaseUri.length() > 0)) {
      this.bitmapManager.setBitmap(paramView, AppUtil.BITMAP_LOADER, paramNavGroup.imagebaseUri + "/" + paramNavItem.icon);
    }
    localTextView1.setText(paramNavItem.title);
    if (paramNavItem.isInstalled(this.mActivity, sPkgVersions)) {
      if (paramNavItem.isUpdateAvailable(this.mActivity, sPkgVersions))
      {
        localImageView.setImageResource(2130837678);
        localImageView.setVisibility(0);
        localTextView2.setText("Update Available");
      }
    }
    for (;;)
    {
      localView.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          try
          {
            NavDrawerAdapter.access$302(null);
            Track.event("Apps", "AppSwitchClick", paramNavItem.title);
            paramNavItem.getIntent(NavDrawerAdapter.this.mActivity).send();
            NavDrawerAdapter.this.closeDrawer();
            return;
          }
          catch (PendingIntent.CanceledException paramAnonymousView)
          {
            paramAnonymousView.printStackTrace();
          }
        }
      });
      localView.setTag("appitem");
      return localView;
      localTextView2.setText("Installed");
      localImageView.setVisibility(8);
      continue;
      localImageView.setImageResource(2130837677);
      localImageView.setVisibility(8);
      if ((paramNavItem.description != null) && (!paramNavItem.description.equals(""))) {
        localTextView2.setText(paramNavItem.description);
      } else {
        localTextView2.setVisibility(8);
      }
    }
  }
  
  public Object getChild(int paramInt1, int paramInt2)
  {
    return null;
  }
  
  public long getChildId(int paramInt1, int paramInt2)
  {
    return 0L;
  }
  
  public View getChildView(int paramInt1, int paramInt2, boolean paramBoolean, View paramView, ViewGroup paramViewGroup)
  {
    if (((NavGroup.NavItem)((NavGroup)sNavGroups.get(paramInt1)).ni.get(paramInt2)).isAppItem()) {
      return getAppItemView(paramView, (NavGroup)sNavGroups.get(paramInt1), (NavGroup.NavItem)((NavGroup)sNavGroups.get(paramInt1)).ni.get(paramInt2));
    }
    return getNavItemView(paramView, (NavGroup)sNavGroups.get(paramInt1), (NavGroup.NavItem)((NavGroup)sNavGroups.get(paramInt1)).ni.get(paramInt2));
  }
  
  public int getChildrenCount(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= sNavGroups.size())) {}
    while (sNavGroups.get(paramInt) == null) {
      return 0;
    }
    paramInt = ((NavGroup)sNavGroups.get(paramInt)).ni.size();
    if (sNavGroups == null) {
      paramInt = 0;
    }
    return paramInt;
  }
  
  public Object getGroup(int paramInt)
  {
    return null;
  }
  
  public int getGroupCount()
  {
    if (sNavGroups == null) {
      return 0;
    }
    return sNavGroups.size();
  }
  
  public long getGroupId(int paramInt)
  {
    return 0L;
  }
  
  public View getGroupView(int paramInt, boolean paramBoolean, View paramView, ViewGroup paramViewGroup)
  {
    ((ExpandableListView)paramViewGroup).expandGroup(paramInt);
    if (paramInt < 0) {
      return getNavGroupView(paramView, (NavGroup)sNavGroups.get(0));
    }
    if (paramInt >= sNavGroups.size()) {
      return getNavGroupView(paramView, (NavGroup)sNavGroups.get(sNavGroups.size() - 1));
    }
    return getNavGroupView(paramView, (NavGroup)sNavGroups.get(paramInt));
  }
  
  protected View getNavGroupView(View paramView, NavGroup paramNavGroup)
  {
    View localView;
    if ((paramNavGroup == null) || ((paramView != null) && ((paramView.getTag() instanceof ViewHolder)))) {
      localView = getHeaderView(paramView);
    }
    do
    {
      return localView;
      if (paramView != null)
      {
        localView = paramView;
        if (!(paramView instanceof FrameLayout)) {}
      }
      else
      {
        localView = this.inflater.inflate(2130903298, null);
      }
      ((TextView)localView.findViewById(2131559299)).setText(paramNavGroup.title);
    } while (!TextUtils.isEmpty(paramNavGroup.title));
    return new FrameLayout(this.mActivity);
  }
  
  protected View getNavItemView(View paramView, NavGroup paramNavGroup, final NavGroup.NavItem paramNavItem)
  {
    View localView;
    if (paramView != null)
    {
      localView = paramView;
      if ("navitem".equals((String)paramView.getTag())) {}
    }
    else
    {
      localView = this.inflater.inflate(2130903299, null);
    }
    paramView = (ImageView)localView.findViewById(2131559295);
    TextView localTextView = (TextView)localView.findViewById(2131559297);
    if ((paramNavGroup.imagebaseUri == null) || (paramNavGroup.imagebaseUri.length() == 0)) {
      paramView.setImageResource(AppUtil.getImageResourceId(this.mActivity, paramNavItem.imageName.replace(".png", "")));
    }
    for (;;)
    {
      if (paramNavItem.title.equals("Notifications")) {
        this.notificationText = localTextView;
      }
      localTextView.setText(paramNavItem.title);
      localView.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          try
          {
            Track.event("Nav", "NavClick", paramNavItem.clickStream);
            paramNavItem.getIntent(NavDrawerAdapter.this.mActivity).send();
            NavDrawerAdapter.this.closeDrawer();
            return;
          }
          catch (Exception paramAnonymousView)
          {
            paramAnonymousView.printStackTrace();
          }
        }
      });
      localView.setTag("navitem");
      return localView;
      this.bitmapManager.setBitmap(paramView, AppUtil.BITMAP_LOADER, paramNavGroup.imagebaseUri + "/" + paramNavItem.imageName);
    }
  }
  
  public boolean hasStableIds()
  {
    return false;
  }
  
  public boolean isChildSelectable(int paramInt1, int paramInt2)
  {
    return false;
  }
  
  public Loader<Cursor> onCreateLoader(int paramInt, Bundle paramBundle)
  {
    paramBundle = ContentProvider.createUri(NavGroup.class);
    new CursorLoader(this.mActivity, paramBundle, null, null, null, "groupIndex")
    {
      private final Loader<Cursor>.ForceLoadContentObserver mObserver = new Loader.ForceLoadContentObserver(this);
      
      public Cursor loadInBackground()
      {
        ArrayList localArrayList = new ArrayList(10);
        localArrayList.add(0, null);
        Cursor localCursor = super.loadInBackground();
        while (localCursor.moveToNext())
        {
          NavGroup localNavGroup = new NavGroup();
          localNavGroup.loadFromCursor(localCursor, false);
          localNavGroup.ni = new Select().from(NavGroup.NavItem.class).where("_pid =" + localNavGroup.getId()).execute();
          localArrayList.add(localNavGroup);
        }
        if (localArrayList.size() > 0) {
          NavDrawerAdapter.access$202(localArrayList);
        }
        localCursor.registerContentObserver(this.mObserver);
        localCursor.setNotificationUri(NavDrawerAdapter.this.mActivity.getContentResolver(), getUri());
        return localCursor;
      }
    };
  }
  
  public void onLoadFinished(Loader<Cursor> paramLoader, Cursor paramCursor)
  {
    notifyDataSetChanged();
  }
  
  public void onLoaderReset(Loader<Cursor> paramLoader) {}
  
  class ViewHolder
  {
    RelativeLayout accountView;
    public View divider;
    TextView option1;
    TextView option2;
    TextView option3;
    ImageView optionsIndicator;
    LinearLayout optionsList;
    TextView profileId;
    TextView profileName;
    
    ViewHolder() {}
  }
}
