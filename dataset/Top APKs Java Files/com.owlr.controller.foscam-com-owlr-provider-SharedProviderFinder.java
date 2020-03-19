package com.owlr.provider;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SharedProviderFinder
  implements Types
{
  private static SharedProviderFinder sharedProviderFinder;
  private final Pattern authorityMatcherPattern;
  private final ContentResolver contentResolver;
  private final Context context;
  private final String sharedPermission;
  
  protected SharedProviderFinder(Context paramContext, Pattern paramPattern, String paramString, ContentResolver paramContentResolver)
  {
    this.context = paramContext;
    this.authorityMatcherPattern = paramPattern;
    this.sharedPermission = paramString;
    this.contentResolver = paramContentResolver;
  }
  
  public static SharedProviderFinder get(Context paramContext)
  {
    if (sharedProviderFinder == null) {
      sharedProviderFinder = initDefaultFinder(paramContext);
    }
    return sharedProviderFinder;
  }
  
  private static SharedProviderFinder initDefaultFinder(Context paramContext)
  {
    String str1 = MetaDataUtils.getSharedPermission(paramContext);
    String str2 = MetaDataUtils.getSharedAuthorityMatcher(paramContext);
    if (TextUtils.isEmpty(str2)) {
      throw new IllegalStateException("You need to define the \"app_authority_matcher\" meta-data in your ApplicationManifest.xml");
    }
    return new SharedProviderFinder(paramContext, Pattern.compile(str2), str1, paramContext.getContentResolver());
  }
  
  String delegateMaster(String paramString, boolean paramBoolean)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("master", Boolean.valueOf(paramBoolean));
    this.context.getContentResolver().insert(SharedSharedPreferences.getContentUri(paramString, "key", "type"), localContentValues);
    return paramString;
  }
  
  public String findMasterProvider()
  {
    return findMasterProvider(findProviders());
  }
  
  public String findMasterProvider(List<ProviderInfo> paramList)
  {
    if ((paramList == null) || (paramList.size() < 1)) {
      throw new IllegalStateException("There should be at least one Provider registered for this to work.");
    }
    Object localObject1 = null;
    int i = 0;
    int j = paramList.size();
    if (i < j)
    {
      String str = ((ProviderInfo)paramList.get(i)).authority;
      Object localObject2;
      if (TextUtils.isEmpty(str)) {
        localObject2 = localObject1;
      }
      for (;;)
      {
        i += 1;
        localObject1 = localObject2;
        break;
        boolean bool = isProviderMaster(getContentUri(str), this.contentResolver);
        Log.d("SharedProviders", "Auth " + str + " isMaster: " + bool);
        if ((bool) && (TextUtils.isEmpty(localObject1)))
        {
          localObject2 = str;
        }
        else
        {
          localObject2 = localObject1;
          if (bool)
          {
            localObject2 = localObject1;
            if (!TextUtils.isEmpty(localObject1))
            {
              Log.d("SharedProviders", "Un-Delegate Auth: " + str);
              delegateMaster(str, false);
              localObject2 = localObject1;
            }
          }
        }
      }
    }
    if (!TextUtils.isEmpty(localObject1)) {
      return localObject1;
    }
    paramList = ((ProviderInfo)paramList.get(0)).authority;
    if (TextUtils.isEmpty(paramList)) {
      throw new IllegalStateException("There are no valid providers to delegate. Are you sure you have your permissions and authorityMatcher set correctly");
    }
    return delegateMaster(paramList, true);
  }
  
  public List<ProviderInfo> findProviders()
  {
    Pattern localPattern = getAuthorityMatcherPattern();
    Log.i("SharedProviders", "Find Authorities using: " + localPattern.pattern() + " Permission: " + getSharedPermission());
    List localList = getInstalledProviders();
    int j = localList.size();
    ArrayList localArrayList = new ArrayList(j);
    String str = getSharedPermission();
    int i = 0;
    if (i < j)
    {
      ProviderInfo localProviderInfo = (ProviderInfo)localList.get(i);
      if (TextUtils.isEmpty(localProviderInfo.authority)) {}
      for (;;)
      {
        i += 1;
        break;
        Matcher localMatcher = localPattern.matcher(localProviderInfo.authority);
        if ((localMatcher.matches()) && (str.equalsIgnoreCase(localProviderInfo.writePermission)))
        {
          Log.d("SharedProviders", "provider: " + localProviderInfo.authority + " Matches: " + localMatcher.matches());
          localArrayList.add(localProviderInfo);
        }
      }
    }
    Log.d("SharedProviders", "Found " + localArrayList.size() + " providers.");
    return localArrayList;
  }
  
  Pattern getAuthorityMatcherPattern()
  {
    return this.authorityMatcherPattern;
  }
  
  Uri getContentUri(String paramString)
  {
    return SharedSharedPreferences.getContentUri(paramString, "master", "boolean");
  }
  
  List<ProviderInfo> getInstalledProviders()
  {
    List localList = this.context.getPackageManager().getInstalledPackages(8);
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (i < localList.size())
    {
      PackageInfo localPackageInfo = (PackageInfo)localList.get(i);
      if (localPackageInfo.providers != null) {
        Collections.addAll(localArrayList, localPackageInfo.providers);
      }
      i += 1;
    }
    return localArrayList;
  }
  
  String getSharedPermission()
  {
    return this.sharedPermission;
  }
  
  boolean isProviderMaster(Uri paramUri, ContentResolver paramContentResolver)
  {
    return SharedCursorUtils.getBooleanValue(paramContentResolver.query(paramUri, null, null, null, null), false);
  }
}
