package com.amazon.identity.auth.accounts;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import com.amazon.identity.auth.device.framework.MAPApplicationInformationQueryer;
import com.amazon.identity.auth.device.framework.TrustedPackageManager;
import com.amazon.identity.auth.device.utils.MAPLog;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class SubAuthenticatorRegistry
{
  private static final String TAG = SubAuthenticatorRegistry.class.getName();
  private final Context mContext;
  private final SubAuthenticatorDescriptionParser mDescriptionParser;
  private final Object mGuard = new Object[0];
  private List<SubAuthenticatorDescription> mSubAuthenticators;
  private int mSubAuthenticatorsVersion = 0;
  private final TrustedPackageManager mTrustedPackageManager;
  
  public SubAuthenticatorRegistry(Context paramContext)
  {
    this(paramContext, new SubAuthenticatorDescriptionParser());
  }
  
  public SubAuthenticatorRegistry(Context paramContext, SubAuthenticatorDescriptionParser paramSubAuthenticatorDescriptionParser)
  {
    if ((paramContext == null) || (paramSubAuthenticatorDescriptionParser == null)) {
      throw new IllegalArgumentException("One or more arguments are null");
    }
    this.mContext = paramContext.getApplicationContext();
    this.mTrustedPackageManager = new TrustedPackageManager(paramContext);
    this.mDescriptionParser = paramSubAuthenticatorDescriptionParser;
  }
  
  private List<SubAuthenticatorDescription> dedupeSubAuthenticators(List<SubAuthenticatorDescription> paramList1, List<SubAuthenticatorDescription> paramList2)
  {
    ArrayList localArrayList = new ArrayList();
    HashSet localHashSet = new HashSet();
    paramList1 = paramList1.iterator();
    while (paramList1.hasNext())
    {
      SubAuthenticatorDescription localSubAuthenticatorDescription = (SubAuthenticatorDescription)paramList1.next();
      localHashSet.add(localSubAuthenticatorDescription.packageName);
      localArrayList.add(localSubAuthenticatorDescription);
    }
    paramList1 = paramList2.iterator();
    while (paramList1.hasNext())
    {
      paramList2 = (SubAuthenticatorDescription)paramList1.next();
      if (!localHashSet.contains(paramList2.packageName)) {
        localArrayList.add(paramList2);
      } else {
        MAPLog.d(TAG, "Deduped sub-authenticator" + paramList2.packageName);
      }
    }
    return localArrayList;
  }
  
  private List<SubAuthenticatorDescription> getAppsWithSubAuthenticator()
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject1 = new Intent("com.amazon.dcp.sso.AccountSubAuthenticator");
    localObject1 = this.mTrustedPackageManager.queryIntentServices((Intent)localObject1, 128).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      ResolveInfo localResolveInfo = (ResolveInfo)((Iterator)localObject1).next();
      Object localObject3;
      try
      {
        ServiceInfo localServiceInfo = localResolveInfo.serviceInfo;
        localObject3 = this.mTrustedPackageManager.getParserForPackage(localServiceInfo, "com.amazon.dcp.sso.AccountSubAuthenticator");
        if (localObject3 != null) {
          break label141;
        }
        throw new InvalidSubAuthenticatorDefinitionException(String.format("%s does not have a valid sub authenticator metadata file", new Object[] { localServiceInfo.packageName }));
      }
      catch (InvalidSubAuthenticatorDefinitionException localInvalidSubAuthenticatorDefinitionException)
      {
        MAPLog.e(TAG, String.format("Ignored invalid sub authenticator from package %s: %s", new Object[] { localResolveInfo.serviceInfo.packageName, localInvalidSubAuthenticatorDefinitionException.toString() }));
      }
      continue;
      label141:
      Object localObject2 = this.mDescriptionParser.parse(localInvalidSubAuthenticatorDefinitionException.packageName, localInvalidSubAuthenticatorDefinitionException.name, (XmlResourceParser)localObject3);
      localArrayList.add(localObject2);
      MAPLog.d(TAG, String.format("Detected sub-authenticator: %s/%s", new Object[] { ((SubAuthenticatorDescription)localObject2).packageName, ((SubAuthenticatorDescription)localObject2).className }));
      MAPLog.d(TAG, String.format(" Supports token types:", new Object[0]));
      localObject2 = ((SubAuthenticatorDescription)localObject2).tokenTypes.iterator();
      while (((Iterator)localObject2).hasNext())
      {
        localObject3 = (String)((Iterator)localObject2).next();
        MAPLog.d(TAG, String.format("  %s", new Object[] { localObject3 }));
      }
    }
    return localArrayList;
  }
  
  private List<SubAuthenticatorDescription> getDMSApps()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = getDmsPackageInfos().iterator();
    for (;;)
    {
      if (localIterator.hasNext())
      {
        Object localObject = (DMSPackageInfo)localIterator.next();
        String str1 = ((DMSPackageInfo)localObject).getPackageName();
        localObject = ((DMSPackageInfo)localObject).getParser();
        try
        {
          localObject = this.mDescriptionParser.parse(str1, null, (XmlResourceParser)localObject);
          localArrayList.add(localObject);
          MAPLog.d(TAG, String.format("Detected DMS sub-authenticator: %s/%s", new Object[] { ((SubAuthenticatorDescription)localObject).packageName, ((SubAuthenticatorDescription)localObject).className }));
          MAPLog.d(TAG, String.format(" Supports token types:", new Object[0]));
          localObject = ((SubAuthenticatorDescription)localObject).tokenTypes.iterator();
          while (((Iterator)localObject).hasNext())
          {
            String str2 = (String)((Iterator)localObject).next();
            MAPLog.d(TAG, String.format("  %s", new Object[] { str2 }));
          }
        }
        catch (InvalidSubAuthenticatorDefinitionException localInvalidSubAuthenticatorDefinitionException)
        {
          MAPLog.e(TAG, String.format("Ignored invalid sub authenticator from package %s: %s", new Object[] { str1, localInvalidSubAuthenticatorDefinitionException.toString() }));
        }
      }
    }
    return localArrayList;
  }
  
  private List<DMSPackageInfo> getDmsPackageInfos()
  {
    Object localObject1 = this.mTrustedPackageManager.getInstalledPackages();
    ArrayList localArrayList = new ArrayList();
    localObject1 = ((List)localObject1).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject1).next();
      try
      {
        Object localObject2 = this.mTrustedPackageManager.getResourcesForApplication(localPackageInfo.packageName);
        if (localObject2 != null)
        {
          int i = ((Resources)localObject2).getIdentifier("dms_sub_authenticator", "xml", localPackageInfo.packageName);
          if (i != 0)
          {
            localObject2 = ((Resources)localObject2).getXml(i);
            localArrayList.add(new DMSPackageInfo(localPackageInfo.packageName, (XmlResourceParser)localObject2));
          }
        }
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        MAPLog.d(TAG, "Cannot get resources for applicatoin. " + localPackageInfo.packageName + " not found");
      }
    }
    return localArrayList;
  }
  
  private List<SubAuthenticatorDescription> getSubAuthenticatorList()
  {
    synchronized (this.mGuard)
    {
      int i = this.mSubAuthenticatorsVersion;
      if (this.mSubAuthenticators != null)
      {
        localList = this.mSubAuthenticators;
        return localList;
      }
      List localList = Collections.unmodifiableList(removeMAPR5Applications(dedupeSubAuthenticators(getAppsWithSubAuthenticator(), getDMSApps())));
      synchronized (this.mGuard)
      {
        if (i == this.mSubAuthenticatorsVersion) {
          this.mSubAuthenticators = localList;
        }
        return localList;
      }
    }
  }
  
  private List<SubAuthenticatorDescription> removeMAPR5Applications(List<SubAuthenticatorDescription> paramList)
  {
    ArrayList localArrayList = new ArrayList();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      SubAuthenticatorDescription localSubAuthenticatorDescription = (SubAuthenticatorDescription)paramList.next();
      String str = localSubAuthenticatorDescription.packageName;
      if (MAPApplicationInformationQueryer.getInstance(this.mContext).getAppInfo(str) == null)
      {
        MAPLog.d(TAG, "Package %s is not a MAP R5 app, so using it's Sub Auth.", new String[] { str });
        localArrayList.add(localSubAuthenticatorDescription);
      }
      else
      {
        MAPLog.d(TAG, "Package %s is a MAP R5 app, so not using it's Sub Auth.", new String[] { str });
      }
    }
    return localArrayList;
  }
  
  public void checkSignature(SubAuthenticatorDescription paramSubAuthenticatorDescription)
    throws InvalidSubAuthenticatorDefinitionException
  {
    if (!this.mTrustedPackageManager.isTrustedPackage(paramSubAuthenticatorDescription.packageName)) {
      throw new InvalidSubAuthenticatorDefinitionException(String.format("Package %s has a different signature from the authenticator.", new Object[] { paramSubAuthenticatorDescription.packageName }));
    }
  }
  
  public SubAuthenticatorDescription getSubAuthenticatorForTokenType(String paramString)
  {
    SubAuthenticatorDescription localSubAuthenticatorDescription;
    Iterator localIterator2;
    do
    {
      Iterator localIterator1 = getSubAuthenticatorList().iterator();
      while (!localIterator2.hasNext())
      {
        if (!localIterator1.hasNext()) {
          break;
        }
        localSubAuthenticatorDescription = (SubAuthenticatorDescription)localIterator1.next();
        localIterator2 = localSubAuthenticatorDescription.tokenTypes.iterator();
      }
    } while (!((String)localIterator2.next()).equals(paramString));
    return localSubAuthenticatorDescription;
    MAPLog.e(TAG, "No SubAuthenticatorDescription found for the desired token type: " + paramString);
    return null;
  }
  
  public List<SubAuthenticatorDescription> getSubAuthenticators()
  {
    return getSubAuthenticatorList();
  }
  
  public void onRescanRequired()
  {
    synchronized (this.mGuard)
    {
      this.mSubAuthenticatorsVersion += 1;
      this.mSubAuthenticators = null;
      return;
    }
  }
  
  private static class DMSPackageInfo
  {
    private String mPackageName;
    private XmlResourceParser mParser;
    
    public DMSPackageInfo(String paramString, XmlResourceParser paramXmlResourceParser)
    {
      this.mPackageName = paramString;
      this.mParser = paramXmlResourceParser;
    }
    
    public String getPackageName()
    {
      return this.mPackageName;
    }
    
    public XmlResourceParser getParser()
    {
      return this.mParser;
    }
  }
}
