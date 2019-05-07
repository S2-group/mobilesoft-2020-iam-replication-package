package de.blinkt.openvpn.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.ApplicationInfo.DisplayNameComparator;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Filter;
import android.widget.Filter.FilterResults;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnCloseListener;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.Switch;
import android.widget.TextView;
import de.blinkt.openvpn.R.id;
import de.blinkt.openvpn.R.layout;
import de.blinkt.openvpn.R.menu;
import de.blinkt.openvpn.R.string;
import de.blinkt.openvpn.VpnProfile;
import de.blinkt.openvpn.core.ProfileManager;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

public class Settings_Allowed_Apps
  extends Fragment
  implements AdapterView.OnItemClickListener, CompoundButton.OnCheckedChangeListener, View.OnClickListener
{
  private TextView mDefaultAllowTextView;
  private PackageAdapter mListAdapter;
  private ListView mListView;
  private VpnProfile mProfile;
  
  public Settings_Allowed_Apps() {}
  
  private void changeDisallowText(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.mDefaultAllowTextView.setText(R.string.vpn_disallow_radio);
      return;
    }
    this.mDefaultAllowTextView.setText(R.string.vpn_allow_radio);
  }
  
  public void onCheckedChanged(CompoundButton paramCompoundButton, boolean paramBoolean)
  {
    paramCompoundButton = (String)paramCompoundButton.getTag();
    if (paramBoolean)
    {
      Log.d("openvpn", "adding to allowed apps" + paramCompoundButton);
      this.mProfile.mAllowedAppsVpn.add(paramCompoundButton);
      return;
    }
    Log.d("openvpn", "removing from allowed apps" + paramCompoundButton);
    this.mProfile.mAllowedAppsVpn.remove(paramCompoundButton);
  }
  
  public void onClick(View paramView) {}
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    paramBundle = getArguments().getString(getActivity().getPackageName() + ".profileUUID");
    this.mProfile = ProfileManager.get(getActivity(), paramBundle);
    getActivity().setTitle(getString(R.string.edit_profile_title, new Object[] { this.mProfile.getName() }));
    setHasOptionsMenu(true);
  }
  
  public void onCreateOptionsMenu(Menu paramMenu, MenuInflater paramMenuInflater)
  {
    paramMenuInflater.inflate(R.menu.allowed_apps, paramMenu);
    SearchView localSearchView = (SearchView)paramMenu.findItem(R.id.app_search_widget).getActionView();
    localSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
    {
      public boolean onQueryTextChange(String paramAnonymousString)
      {
        Settings_Allowed_Apps.this.mListView.setFilterText(paramAnonymousString);
        if (TextUtils.isEmpty(paramAnonymousString))
        {
          Settings_Allowed_Apps.this.mListView.setTextFilterEnabled(false);
          return true;
        }
        Settings_Allowed_Apps.this.mListView.setTextFilterEnabled(true);
        return true;
      }
      
      public boolean onQueryTextSubmit(String paramAnonymousString)
      {
        Settings_Allowed_Apps.this.mListView.setFilterText(paramAnonymousString);
        Settings_Allowed_Apps.this.mListView.setTextFilterEnabled(true);
        return true;
      }
    });
    localSearchView.setOnCloseListener(new SearchView.OnCloseListener()
    {
      public boolean onClose()
      {
        Settings_Allowed_Apps.this.mListView.clearTextFilter();
        Settings_Allowed_Apps.this.mListAdapter.getFilter().filter("");
        return false;
      }
    });
    super.onCreateOptionsMenu(paramMenu, paramMenuInflater);
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    paramLayoutInflater = paramLayoutInflater.inflate(R.layout.allowed_vpn_apps, paramViewGroup, false);
    this.mDefaultAllowTextView = ((TextView)paramLayoutInflater.findViewById(R.id.default_allow_text));
    paramViewGroup = (Switch)paramLayoutInflater.findViewById(R.id.default_allow);
    paramViewGroup.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
    {
      public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
      {
        Settings_Allowed_Apps.this.changeDisallowText(paramAnonymousBoolean);
        Settings_Allowed_Apps.this.mProfile.mAllowedAppsVpnAreDisallowed = paramAnonymousBoolean;
      }
    });
    paramViewGroup.setChecked(this.mProfile.mAllowedAppsVpnAreDisallowed);
    this.mListView = ((ListView)paramLayoutInflater.findViewById(16908298));
    this.mListAdapter = new PackageAdapter(getActivity(), this.mProfile);
    this.mListView.setAdapter(this.mListAdapter);
    this.mListView.setOnItemClickListener(this);
    this.mListView.setEmptyView(paramLayoutInflater.findViewById(R.id.loading_container));
    new Thread(new Runnable()
    {
      public void run()
      {
        Settings_Allowed_Apps.PackageAdapter.access$800(Settings_Allowed_Apps.this.mListAdapter, Settings_Allowed_Apps.this.getActivity());
      }
    }).start();
    return paramLayoutInflater;
  }
  
  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    ((AppViewHolder)paramView.getTag()).checkBox.toggle();
  }
  
  public void onResume()
  {
    super.onResume();
    changeDisallowText(this.mProfile.mAllowedAppsVpnAreDisallowed);
  }
  
  static class AppViewHolder
  {
    public ImageView appIcon;
    public TextView appName;
    public CompoundButton checkBox;
    public ApplicationInfo mInfo;
    public View rootView;
    
    AppViewHolder() {}
    
    public static AppViewHolder createOrRecycle(LayoutInflater paramLayoutInflater, View paramView, ViewGroup paramViewGroup)
    {
      if (paramView == null)
      {
        paramLayoutInflater = paramLayoutInflater.inflate(R.layout.allowed_application_layout, paramViewGroup, false);
        paramView = new AppViewHolder();
        paramView.rootView = paramLayoutInflater;
        paramView.appName = ((TextView)paramLayoutInflater.findViewById(R.id.app_name));
        paramView.appIcon = ((ImageView)paramLayoutInflater.findViewById(R.id.app_icon));
        paramView.checkBox = ((CompoundButton)paramLayoutInflater.findViewById(R.id.app_selected));
        paramLayoutInflater.setTag(paramView);
        return paramView;
      }
      return (AppViewHolder)paramView.getTag();
    }
  }
  
  class PackageAdapter
    extends BaseAdapter
    implements Filterable
  {
    private ItemFilter mFilter = new ItemFilter(null);
    private Vector<ApplicationInfo> mFilteredData;
    private final LayoutInflater mInflater;
    private Vector<ApplicationInfo> mPackages;
    private final PackageManager mPm;
    
    PackageAdapter(Context paramContext, VpnProfile paramVpnProfile)
    {
      this.mPm = paramContext.getPackageManager();
      Settings_Allowed_Apps.access$402(Settings_Allowed_Apps.this, paramVpnProfile);
      this.mInflater = LayoutInflater.from(paramContext);
      this.mPackages = new Vector();
      this.mFilteredData = this.mPackages;
    }
    
    private void populateList(Activity paramActivity)
    {
      Object localObject = this.mPm.getInstalledApplications(128);
      int j = 0;
      Vector localVector = new Vector();
      int i = j;
      try
      {
        localApplicationInfo = this.mPm.getApplicationInfo("android", 128);
        i = j;
        j = localApplicationInfo.uid;
        i = j;
        localVector.add(localApplicationInfo);
        i = j;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        ApplicationInfo localApplicationInfo;
        for (;;) {}
      }
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        localApplicationInfo = (ApplicationInfo)((Iterator)localObject).next();
        if ((this.mPm.checkPermission("android.permission.INTERNET", localApplicationInfo.packageName) == 0) && (localApplicationInfo.uid != i)) {
          localVector.add(localApplicationInfo);
        }
      }
      Collections.sort(localVector, new ApplicationInfo.DisplayNameComparator(this.mPm));
      this.mPackages = localVector;
      this.mFilteredData = localVector;
      paramActivity.runOnUiThread(new Runnable()
      {
        public void run()
        {
          Settings_Allowed_Apps.PackageAdapter.this.notifyDataSetChanged();
        }
      });
    }
    
    public int getCount()
    {
      return this.mFilteredData.size();
    }
    
    public Filter getFilter()
    {
      return this.mFilter;
    }
    
    public Object getItem(int paramInt)
    {
      return this.mFilteredData.get(paramInt);
    }
    
    public long getItemId(int paramInt)
    {
      return ((ApplicationInfo)this.mFilteredData.get(paramInt)).packageName.hashCode();
    }
    
    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      Settings_Allowed_Apps.AppViewHolder localAppViewHolder = Settings_Allowed_Apps.AppViewHolder.createOrRecycle(this.mInflater, paramView, paramViewGroup);
      paramView = localAppViewHolder.rootView;
      localAppViewHolder.mInfo = ((ApplicationInfo)this.mFilteredData.get(paramInt));
      ApplicationInfo localApplicationInfo = (ApplicationInfo)this.mFilteredData.get(paramInt);
      paramViewGroup = localApplicationInfo.loadLabel(this.mPm);
      paramView = paramViewGroup;
      if (TextUtils.isEmpty(paramViewGroup)) {
        paramView = localApplicationInfo.packageName;
      }
      localAppViewHolder.appName.setText(paramView);
      localAppViewHolder.appIcon.setImageDrawable(localApplicationInfo.loadIcon(this.mPm));
      localAppViewHolder.checkBox.setTag(localApplicationInfo.packageName);
      localAppViewHolder.checkBox.setOnCheckedChangeListener(Settings_Allowed_Apps.this);
      localAppViewHolder.checkBox.setChecked(Settings_Allowed_Apps.this.mProfile.mAllowedAppsVpn.contains(localApplicationInfo.packageName));
      return localAppViewHolder.rootView;
    }
    
    private class ItemFilter
      extends Filter
    {
      private ItemFilter() {}
      
      protected Filter.FilterResults performFiltering(CharSequence paramCharSequence)
      {
        String str = paramCharSequence.toString().toLowerCase(Locale.getDefault());
        Filter.FilterResults localFilterResults = new Filter.FilterResults();
        int j = Settings_Allowed_Apps.PackageAdapter.this.mPackages.size();
        Vector localVector = new Vector(j);
        int i = 0;
        if (i < j)
        {
          ApplicationInfo localApplicationInfo = (ApplicationInfo)Settings_Allowed_Apps.PackageAdapter.this.mPackages.get(i);
          CharSequence localCharSequence = localApplicationInfo.loadLabel(Settings_Allowed_Apps.PackageAdapter.this.mPm);
          paramCharSequence = localCharSequence;
          if (TextUtils.isEmpty(localCharSequence)) {
            paramCharSequence = localApplicationInfo.packageName;
          }
          if ((paramCharSequence instanceof String)) {
            if (((String)paramCharSequence).toLowerCase(Locale.getDefault()).contains(str)) {
              localVector.add(localApplicationInfo);
            }
          }
          for (;;)
          {
            i += 1;
            break;
            if (paramCharSequence.toString().toLowerCase(Locale.getDefault()).contains(str)) {
              localVector.add(localApplicationInfo);
            }
          }
        }
        localFilterResults.values = localVector;
        localFilterResults.count = localVector.size();
        return localFilterResults;
      }
      
      protected void publishResults(CharSequence paramCharSequence, Filter.FilterResults paramFilterResults)
      {
        Settings_Allowed_Apps.PackageAdapter.access$302(Settings_Allowed_Apps.PackageAdapter.this, (Vector)paramFilterResults.values);
        Settings_Allowed_Apps.PackageAdapter.this.notifyDataSetChanged();
      }
    }
  }
}
