package com.songkick.repository.source.contentresolver;

import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.support.v4.util.ArrayMap;
import com.songkick.model.Provider;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import rx.Observable;

public class TasteContentResolverClient
{
  private final List<TasteSource> activeSources;
  private final ContentResolver contentResolver;
  private final ArrayMap<String, TasteSource> supportedAuthorities = new ArrayMap();
  
  public TasteContentResolverClient(Context paramContext)
  {
    this.supportedAuthorities.put("com.android.providers.media", new AndroidMediaTasteSource());
    this.supportedAuthorities.put("com.google.android.music.MusicContent", new GoogleMusicTasteSource());
    this.activeSources = getActiveSources(paramContext);
    this.contentResolver = paramContext.getContentResolver();
  }
  
  private List<TasteSource> getActiveSources(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    paramContext = paramContext.getPackageManager().getInstalledPackages(8).iterator();
    while (paramContext.hasNext())
    {
      ProviderInfo[] arrayOfProviderInfo = ((PackageInfo)paramContext.next()).providers;
      if (arrayOfProviderInfo != null)
      {
        int j = arrayOfProviderInfo.length;
        int i = 0;
        while (i < j)
        {
          ProviderInfo localProviderInfo = arrayOfProviderInfo[i];
          if ((localProviderInfo != null) && (this.supportedAuthorities.containsKey(localProviderInfo.authority))) {
            localArrayList.add(this.supportedAuthorities.get(localProviderInfo.authority));
          }
          i += 1;
        }
      }
    }
    localArrayList.add(this.supportedAuthorities.get("com.android.providers.media"));
    return localArrayList;
  }
  
  private List<Provider> populateProviders()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.activeSources.iterator();
    while (localIterator.hasNext()) {
      localArrayList.add(((TasteSource)localIterator.next()).getProvider(this.contentResolver));
    }
    return localArrayList;
  }
  
  public Observable<List<Provider>> importTastes()
  {
    Observable.fromCallable(new Callable()
    {
      public List<Provider> call()
        throws Exception
      {
        return TasteContentResolverClient.this.populateProviders();
      }
    });
  }
}
