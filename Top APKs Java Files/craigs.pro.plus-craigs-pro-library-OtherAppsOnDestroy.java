package craigs.pro.library;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class OtherAppsOnDestroy
  extends MainActivity
{
  List<String> appIds = Arrays.asList(new String[] { getPackageName() + "::cPRO Craigslist::icn_cpro", "imagepro.hdtattoodesignspro::Tattoo Designs::icn_tattoos", "imagepro.hdwallpapers::Wallpapers::icn_wallpapers", "imagepro.hddogscatalog::Dog Breeds::icn_dogs", "imagepro.hdanimalcatalogpro::Animal Catalog::icn_animals", "imagepro.hdcatscatalog::Cats::icn_cats", "imagepro.hdflowerscatalog::Flowers::icn_flowers", "imagepro.hdbirdscatalog::Bird Guide::icn_birds", "imagepro.hdfishcatalog::Sharks and Fish::icn_fish", "imagepro.hdbutterflycatalog::Butterflies::icn_butterflies" });
  public ArrayList<String> appLinks = new ArrayList();
  ArrayList<String> installedApps = new ArrayList();
  OtherAppsAdapter otherAppsAdapter;
  
  public OtherAppsOnDestroy() {}
  
  private String[] getAppInfo(int paramInt)
  {
    int k = -1;
    Object localObject1 = " :: :: ";
    int i = 0;
    while (i < this.appIds.size())
    {
      String[] arrayOfString = ((String)this.appIds.get(i)).split("::");
      int j = k;
      Object localObject2 = localObject1;
      if (arrayOfString.length == 3)
      {
        j = k;
        localObject2 = localObject1;
        if (!getPackageName().equals(arrayOfString[0]))
        {
          j = k;
          localObject2 = localObject1;
          if (!alreadyInstalled(arrayOfString[0]))
          {
            k += 1;
            j = k;
            localObject2 = localObject1;
            if (k == paramInt)
            {
              localObject2 = (String)this.appIds.get(i);
              j = k;
            }
          }
        }
      }
      i += 1;
      k = j;
      localObject1 = localObject2;
    }
    return ((String)localObject1).split("::");
  }
  
  private int numberOfNotInstalledApps()
  {
    int j = 0;
    int i = 0;
    while (i < this.appIds.size())
    {
      int k = j;
      if (getAppInfo(i)[0].contains(".")) {
        k = j + 1;
      }
      i += 1;
      j = k;
    }
    return j;
  }
  
  public boolean alreadyInstalled(String paramString)
  {
    boolean bool = false;
    Iterator localIterator = this.installedApps.iterator();
    while (localIterator.hasNext()) {
      if (((String)localIterator.next()).equalsIgnoreCase(paramString)) {
        bool = true;
      }
    }
    return bool;
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.other_apps_on_destroy);
    findViewById(R.id.notNowButton).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        OtherAppsOnDestroy.this.finish();
      }
    });
    retrieveInstalledAppIds();
    paramBundle = (GridView)findViewById(R.id.appGrid);
    this.otherAppsAdapter = new OtherAppsAdapter();
    paramBundle.setAdapter(this.otherAppsAdapter);
    if (numberOfNotInstalledApps() == 0) {
      finish();
    }
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    return super.onKeyDown(paramInt, paramKeyEvent);
  }
  
  public void openApp(int paramInt)
  {
    Object localObject = getAppInfo(paramInt);
    if ((localObject.length == 3) && (localObject[0].contains("."))) {
      localObject = localObject[0];
    }
    try
    {
      startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + (String)localObject)));
      finish();
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      for (;;)
      {
        startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=" + (String)localObject)));
      }
    }
  }
  
  public void retrieveInstalledAppIds()
  {
    try
    {
      Iterator localIterator = getPackageManager().getInstalledPackages(8192).iterator();
      while (localIterator.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
        this.installedApps.add(localPackageInfo.packageName);
      }
      return;
    }
    catch (Exception localException) {}
  }
  
  public class OtherAppsAdapter
    extends BaseAdapter
  {
    public OtherAppsAdapter() {}
    
    private View newView(int paramInt, ViewGroup paramViewGroup)
    {
      return OtherAppsOnDestroy.this.getLayoutInflater().inflate(R.layout.grid_app, paramViewGroup, false);
    }
    
    public int getCount()
    {
      return OtherAppsOnDestroy.this.numberOfNotInstalledApps();
    }
    
    public Object getItem(int paramInt)
    {
      return null;
    }
    
    public long getItemId(int paramInt)
    {
      return 0L;
    }
    
    public View getView(final int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      if (paramView == null)
      {
        paramView = newView(paramInt, paramViewGroup);
        paramViewGroup = new ViewHolder();
        paramViewGroup.image = ((ImageView)paramView.findViewById(R.id.image));
        paramViewGroup.imageLayout = ((RelativeLayout)paramView.findViewById(R.id.imageLayout));
        paramViewGroup.titleContainer = ((RelativeLayout)paramView.findViewById(R.id.titleContainer));
        paramViewGroup.semiTransparentBg = ((RelativeLayout)paramView.findViewById(R.id.semiTransparentBg));
        paramViewGroup.title = ((TextView)paramView.findViewById(R.id.title));
        paramView.setTag(paramViewGroup);
      }
      for (;;)
      {
        Object localObject = OtherAppsOnDestroy.this.getAppInfo(paramInt);
        if (localObject.length == 3)
        {
          paramViewGroup.title.setText(localObject[1]);
          localObject = "drawable/" + localObject[2];
          int i = OtherAppsOnDestroy.this.getResources().getIdentifier((String)localObject, null, OtherAppsOnDestroy.this.getPackageName());
          localObject = OtherAppsOnDestroy.this.getResources().getDrawable(i);
          paramViewGroup.image.setImageDrawable((Drawable)localObject);
        }
        paramViewGroup.semiTransparentBg.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            OtherAppsOnDestroy.this.openApp(paramInt);
          }
        });
        return paramView;
        paramViewGroup = (ViewHolder)paramView.getTag();
      }
    }
    
    class ViewHolder
    {
      ImageView image;
      RelativeLayout imageLayout;
      RelativeLayout semiTransparentBg;
      TextView title;
      RelativeLayout titleContainer;
      
      ViewHolder() {}
    }
  }
}
