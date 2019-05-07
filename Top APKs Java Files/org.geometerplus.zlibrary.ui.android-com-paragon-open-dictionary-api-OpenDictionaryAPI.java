package com.paragon.open.dictionary.api;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public final class OpenDictionaryAPI
{
  private static final Direction DIRECTION_ANY_ANY = new Direction(Language.ANY, Language.ANY);
  static final String LOG_ERROR_TAG = "Open Dictionary API";
  private static final String META_OPEN_DICTIONARY_API_MIN_VERSION_CODE = "open.dictionary.api.minVersionCode";
  private static final String META_OPEN_DICTIONARY_API_VERSION_CODE = "open.dictionary.api.versionCode";
  private static final String META_OPEN_DICTIONARY_API_VERSION_NAME = "open.dictionary.api.versionName";
  private static final int MIN_VERSION_CODE = 1;
  static final String OPEN_DICTIONARY_API = "Open Dictionary API";
  public static final String VENDOR = "Paragon Software Group";
  public static final int VERSION_CODE = 2;
  public static final String VERSION_NAME = "1.2.1";
  private Context ctx;
  private HashMap<Direction, HashSet<Dictionary>> dictsMap;
  HashMap<Language, HashSet<Morphology>> morphosMap;
  
  public OpenDictionaryAPI(Context paramContext)
  {
    if (paramContext == null) {
      throw new IllegalArgumentException("\"ctx\" param must not be null");
    }
    this.ctx = paramContext;
    this.dictsMap = new HashMap();
    this.morphosMap = new HashMap();
    prepare();
  }
  
  private void prepare()
  {
    Iterator localIterator = this.ctx.getPackageManager().getInstalledPackages(64).iterator();
    if (localIterator.hasNext())
    {
      Object localObject1 = (PackageInfo)localIterator.next();
      for (;;)
      {
        Object localObject5;
        try
        {
          Object localObject2 = this.ctx.getPackageManager().getApplicationInfo(((PackageInfo)localObject1).packageName, 128);
          if ((localObject2 == null) || (((ApplicationInfo)localObject2).metaData == null) || (!((ApplicationInfo)localObject2).metaData.containsKey("open.dictionary.api.minVersionCode")) || (2 < ((ApplicationInfo)localObject2).metaData.getInt("open.dictionary.api.minVersionCode")) || (((ApplicationInfo)localObject2).metaData.getInt("open.dictionary.api.versionCode") < 1)) {
            break;
          }
          localObject1 = new Dictionary(this.ctx, this, (ApplicationInfo)localObject2);
          if (((Dictionary)localObject1).hasPairDictionary())
          {
            i = 2;
            Dictionary[] arrayOfDictionary = new Dictionary[i];
            arrayOfDictionary[0] = localObject1;
            if (((Dictionary)localObject1).hasPairDictionary()) {
              arrayOfDictionary[1] = ((Dictionary)localObject1).getPairDictionary();
            }
            int j = arrayOfDictionary.length;
            i = 0;
            if (i >= j) {
              break;
            }
            localObject4 = arrayOfDictionary[i];
            localObject2 = (HashSet)this.dictsMap.get(((Dictionary)localObject4).getDirection());
            localObject1 = localObject2;
            if (localObject2 == null)
            {
              localObject2 = this.dictsMap;
              localObject5 = ((Dictionary)localObject4).getDirection();
              localObject1 = new HashSet();
              ((HashMap)localObject2).put(localObject5, localObject1);
            }
            ((HashSet)localObject1).add(localObject4);
            if (((Dictionary)localObject4).hasMorphology()) {
              break label322;
            }
            i += 1;
            continue;
          }
        }
        catch (Exception localException)
        {
          Log.e("Open Dictionary API", "[" + this.ctx.getPackageName() + "] " + "Can't get application info of \"" + ((PackageInfo)localObject1).packageName + "\"", localException);
        }
        int i = 1;
        continue;
        label322:
        Object localObject4 = ((Dictionary)localObject4).getMorphology();
        Object localObject3 = (HashSet)this.morphosMap.get(((Morphology)localObject4).getLanguage());
        localObject1 = localObject3;
        if (localObject3 == null)
        {
          localObject3 = this.morphosMap;
          localObject5 = ((Morphology)localObject4).getLanguage();
          localObject1 = new HashSet();
          ((HashMap)localObject3).put(localObject5, localObject1);
        }
        ((HashSet)localObject1).add(localObject4);
      }
    }
  }
  
  public HashSet<Dictionary> getDictionaries()
  {
    return getDictionaries(DIRECTION_ANY_ANY);
  }
  
  public HashSet<Dictionary> getDictionaries(Direction paramDirection)
  {
    if (paramDirection == null) {
      throw new IllegalArgumentException("\"dir\" param must not be null");
    }
    HashSet localHashSet = new HashSet();
    if ((Language.ANY.equals(paramDirection.from)) || (Language.ANY.equals(paramDirection.to))) {
      if ((Language.ANY.equals(paramDirection.from)) && (Language.ANY.equals(paramDirection.to))) {
        paramDirection = this.dictsMap.values().iterator();
      }
    }
    while (paramDirection.hasNext())
    {
      localHashSet.addAll((HashSet)paramDirection.next());
      continue;
      Map.Entry localEntry;
      if (Language.ANY.equals(paramDirection.from))
      {
        localIterator = this.dictsMap.entrySet().iterator();
        while (localIterator.hasNext())
        {
          localEntry = (Map.Entry)localIterator.next();
          if (((Direction)localEntry.getKey()).to.equals(paramDirection.to)) {
            localHashSet.addAll((Collection)localEntry.getValue());
          }
        }
      }
      Iterator localIterator = this.dictsMap.entrySet().iterator();
      while (localIterator.hasNext())
      {
        localEntry = (Map.Entry)localIterator.next();
        if (((Direction)localEntry.getKey()).from.equals(paramDirection.from))
        {
          localHashSet.addAll((Collection)localEntry.getValue());
          continue;
          paramDirection = (HashSet)this.dictsMap.get(paramDirection);
          if (paramDirection != null) {
            localHashSet.addAll(paramDirection);
          }
        }
      }
    }
    return localHashSet;
  }
  
  public HashSet<Morphology> getMorphologies()
  {
    HashSet localHashSet = new HashSet();
    Iterator localIterator = this.morphosMap.values().iterator();
    while (localIterator.hasNext()) {
      localHashSet.add(((HashSet)localIterator.next()).iterator().next());
    }
    return localHashSet;
  }
  
  public Morphology getMorphology(Language paramLanguage)
  {
    if (paramLanguage == null) {
      throw new IllegalArgumentException("\"lang\" param must not be null");
    }
    if (this.morphosMap.containsKey(paramLanguage)) {
      return (Morphology)((HashSet)this.morphosMap.get(paramLanguage)).iterator().next();
    }
    return null;
  }
  
  public boolean hasDictionary(Direction paramDirection)
  {
    if (paramDirection == null) {
      throw new IllegalArgumentException("\"dir\" param must not be null");
    }
    return !getDictionaries(paramDirection).isEmpty();
  }
  
  public boolean hasMorphology(Language paramLanguage)
  {
    if (paramLanguage == null) {
      throw new IllegalArgumentException("\"lang\" param must not be null");
    }
    return getMorphology(paramLanguage) != null;
  }
}
