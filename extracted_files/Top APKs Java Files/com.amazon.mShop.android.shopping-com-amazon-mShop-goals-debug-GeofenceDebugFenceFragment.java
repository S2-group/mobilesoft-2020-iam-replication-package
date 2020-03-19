package com.amazon.mShop.goals.debug;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.amazon.mShop.goals.GoalsShopKitModule;
import com.amazon.mShop.goals.GoalsShopKitSubcomponent;
import com.amazon.mShop.goals.R.id;
import com.amazon.mShop.goals.R.layout;
import com.amazon.mShop.goals.model.GoalsRegion;
import com.amazon.mShop.goals.model.Location;
import com.amazon.mShop.goals.region.GoalsRequestHandler;
import com.amazon.mShop.goals.region.RegionsRepository;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.inject.Inject;

public class GeofenceDebugFenceFragment
  extends Fragment
  implements Observer
{
  private DebugFenceAdapter debugFenceAdapter;
  private GeofenceDebugRecyclerWrapper geofenceDebugRecyclerWrapper;
  @Inject
  GoalsRequestHandler goalsRequestHandler;
  private GeofenceDebugFenceFragment.GetRegionConfigAsync regionConfigAsync;
  @Inject
  RegionsRepository regionsRepository;
  
  public GeofenceDebugFenceFragment() {}
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    GoalsShopKitModule.getSubcomponent().inject(this);
    paramLayoutInflater = paramLayoutInflater.inflate(R.layout.geofence_debug_fence, paramViewGroup, false);
    this.geofenceDebugRecyclerWrapper = ((GeofenceDebugRecyclerWrapper)paramLayoutInflater.findViewById(R.id.geofence_debug_recycler_fence));
    this.geofenceDebugRecyclerWrapper.setEmptyView(paramLayoutInflater.findViewById(R.id.geofence_config_empty));
    this.geofenceDebugRecyclerWrapper.setHasFixedSize(true);
    this.geofenceDebugRecyclerWrapper.addItemDecoration(new GeofenceDividerItemDecoration(getContext()));
    this.geofenceDebugRecyclerWrapper.setLayoutManager(new LinearLayoutManager(getActivity()));
    boolean bool2 = false;
    paramViewGroup = getActivity().getPackageManager().getInstalledApplications(0).iterator();
    do
    {
      bool1 = bool2;
      if (!paramViewGroup.hasNext()) {
        break;
      }
    } while (!((ApplicationInfo)paramViewGroup.next()).packageName.equals("com.google.android.apps.maps"));
    boolean bool1 = true;
    this.debugFenceAdapter = new DebugFenceAdapter(this, bool1);
    this.geofenceDebugRecyclerWrapper.setAdapter(this.debugFenceAdapter);
    this.geofenceDebugRecyclerWrapper.hideEmptyView();
    return paramLayoutInflater;
  }
  
  public void onPause()
  {
    super.onPause();
    this.regionsRepository.deleteObserver(this);
    if (this.regionConfigAsync != null)
    {
      this.regionConfigAsync.cancel(true);
      this.regionConfigAsync = null;
    }
  }
  
  public void onResume()
  {
    super.onResume();
    this.regionsRepository.addObserver(this);
    if (this.regionConfigAsync == null)
    {
      this.regionConfigAsync = new GeofenceDebugFenceFragment.GetRegionConfigAsync(this, null);
      this.regionConfigAsync.execute(new Void[0]);
    }
  }
  
  public void triggerFence(GoalsRegion paramGoalsRegion)
  {
    CharSequence[] arrayOfCharSequence = new CharSequence[paramGoalsRegion.getEvents().size()];
    int i = 0;
    while (i < paramGoalsRegion.getEvents().size())
    {
      arrayOfCharSequence[i] = ((CharSequence)paramGoalsRegion.getEvents().get(i));
      i += 1;
    }
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(getActivity());
    localBuilder.setTitle("Trigger Event").setItems(arrayOfCharSequence, new GeofenceDebugFenceFragment.1(this, arrayOfCharSequence, paramGoalsRegion));
    localBuilder.show();
  }
  
  public void update(Observable paramObservable, Object paramObject)
  {
    if (this.regionConfigAsync == null)
    {
      this.regionConfigAsync = new GeofenceDebugFenceFragment.GetRegionConfigAsync(this, null);
      this.regionConfigAsync.execute(new Void[0]);
    }
  }
  
  public void viewOnMap(Location paramLocation)
  {
    paramLocation = new Intent("android.intent.action.VIEW", Uri.parse("geo:" + paramLocation.getLatitude() + "," + paramLocation.getLongitude() + "?z=19"));
    paramLocation.setPackage("com.google.android.apps.maps");
    startActivity(paramLocation);
  }
}
