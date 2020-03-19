package com.oasisfeng.condom;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.os.Build.VERSION;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

class CondomPackageManager
  extends PackageManagerWrapper
{
  private static final CondomCore.Function<String, String> lI = new CondomCore.Function()
  {
    public String I(String paramAnonymousString)
    {
      return paramAnonymousString;
    }
  };
  private final CondomCore I;
  private final String l;
  
  CondomPackageManager(CondomCore paramCondomCore, PackageManager paramPackageManager, String paramString)
  {
    super(paramPackageManager);
    this.I = paramCondomCore;
    this.l = paramString;
  }
  
  public int checkPermission(final String paramString1, final String paramString2)
  {
    ((Integer)this.I.proceed(OutboundType.CHECK_PERMISSION, paramString2, Integer.valueOf(-1), new CondomCore.WrappedValueProcedure()
    {
      public Integer I()
      {
        return Integer.valueOf(CondomPackageManager.I(CondomPackageManager.this, paramString1, paramString2));
      }
    })).intValue();
  }
  
  public ApplicationInfo getApplicationInfo(final String paramString, final int paramInt)
    throws PackageManager.NameNotFoundException
  {
    (ApplicationInfo)this.I.proceed(OutboundType.GET_APPLICATION_INFO, paramString, null, new CondomCore.WrappedValueProcedureThrows()
    {
      public ApplicationInfo I()
        throws PackageManager.NameNotFoundException
      {
        return CondomPackageManager.I(CondomPackageManager.this, paramString, paramInt);
      }
    });
  }
  
  public List<ApplicationInfo> getInstalledApplications(int paramInt)
  {
    this.I.logConcern(this.l, "PackageManager.getInstalledApplications");
    return super.getInstalledApplications(paramInt);
  }
  
  public List<PackageInfo> getInstalledPackages(int paramInt)
  {
    this.I.logConcern(this.l, "PackageManager.getInstalledPackages");
    return super.getInstalledPackages(paramInt);
  }
  
  public PackageInfo getPackageInfo(final String paramString, final int paramInt)
    throws PackageManager.NameNotFoundException
  {
    PackageInfo localPackageInfo = (PackageInfo)this.I.proceed(OutboundType.GET_PACKAGE_INFO, paramString, null, new CondomCore.WrappedValueProcedureThrows()
    {
      public PackageInfo I()
        throws PackageManager.NameNotFoundException
      {
        return CondomPackageManager.l(CondomPackageManager.this, paramString, paramInt);
      }
    });
    if (localPackageInfo != null)
    {
      if (((paramInt & 0x1000) != 0) && (!this.I.getSpoofPermissions().isEmpty()) && (this.I.getPackageName().equals(paramString)))
      {
        if (localPackageInfo.requestedPermissions == null) {
          paramString = new ArrayList();
        } else {
          paramString = new ArrayList(Arrays.asList(localPackageInfo.requestedPermissions));
        }
        ArrayList localArrayList = new ArrayList(this.I.getSpoofPermissions());
        localArrayList.removeAll(paramString);
        if (!localArrayList.isEmpty())
        {
          paramString.addAll(localArrayList);
          localPackageInfo.requestedPermissions = ((String[])paramString.toArray(new String[paramString.size()]));
        }
        if (Build.VERSION.SDK_INT >= 16)
        {
          if (localPackageInfo.requestedPermissionsFlags == null) {
            paramString = new int[paramString.size()];
          } else {
            paramString = Arrays.copyOf(localPackageInfo.requestedPermissionsFlags, paramString.size());
          }
          paramInt = 0;
          while (paramInt < localPackageInfo.requestedPermissions.length)
          {
            if (this.I.shouldSpoofPermission(localPackageInfo.requestedPermissions[paramInt])) {
              paramString[paramInt] = 2;
            }
            paramInt += 1;
          }
          localPackageInfo.requestedPermissionsFlags = paramString;
        }
      }
      return localPackageInfo;
    }
    throw new PackageManager.NameNotFoundException(paramString);
  }
  
  public PackageInstaller getPackageInstaller()
  {
    throw new UnsupportedOperationException("PackageManager.getPackageInstaller() is not yet supported by Project Condom. If it causes trouble, please file an issue on GitHub.");
  }
  
  public String[] getPackagesForUid(final int paramInt)
  {
    Object localObject1 = this.I;
    Object localObject3 = OutboundType.QUERY_PACKAGES;
    CondomCore.WrappedValueProcedure local6 = new CondomCore.WrappedValueProcedure()
    {
      public List<String> I()
      {
        String[] arrayOfString = CondomPackageManager.I(CondomPackageManager.this, paramInt);
        if (arrayOfString != null) {
          return Arrays.asList(arrayOfString);
        }
        return null;
      }
    };
    CondomCore.Function localFunction = lI;
    Object localObject2 = null;
    localObject3 = ((CondomCore)localObject1).proceedQuery((OutboundType)localObject3, null, local6, localFunction);
    localObject1 = localObject2;
    if (localObject3 != null)
    {
      localObject1 = localObject2;
      if (!((List)localObject3).isEmpty()) {
        localObject1 = (String[])((List)localObject3).toArray(new String[0]);
      }
    }
    return localObject1;
  }
  
  public List<ResolveInfo> queryBroadcastReceivers(final Intent paramIntent, final int paramInt)
  {
    this.I.proceedQuery(OutboundType.QUERY_RECEIVERS, paramIntent, new CondomCore.WrappedValueProcedure()
    {
      public List<ResolveInfo> I()
      {
        return CondomPackageManager.I(CondomPackageManager.this, paramIntent, paramInt);
      }
    }, CondomCore.RECEIVER_PACKAGE_GETTER);
  }
  
  public List<ResolveInfo> queryIntentServices(final Intent paramIntent, final int paramInt)
  {
    final int i = paramIntent.getFlags();
    this.I.proceedQuery(OutboundType.QUERY_SERVICES, paramIntent, new CondomCore.WrappedValueProcedure()
    {
      public List<ResolveInfo> I()
      {
        List localList = CondomPackageManager.l(CondomPackageManager.this, paramIntent, paramInt);
        CondomPackageManager.l(CondomPackageManager.this).filterCandidates(OutboundType.QUERY_SERVICES, paramIntent.setFlags(i), localList, CondomPackageManager.I(CondomPackageManager.this), true);
        return localList;
      }
    }, CondomCore.SERVICE_PACKAGE_GETTER);
  }
  
  public ProviderInfo resolveContentProvider(String paramString, int paramInt)
  {
    paramString = super.resolveContentProvider(paramString, paramInt);
    if (!this.I.shouldAllowProvider(paramString)) {
      return null;
    }
    return paramString;
  }
  
  public ResolveInfo resolveService(final Intent paramIntent, final int paramInt)
  {
    final int i = paramIntent.getFlags();
    (ResolveInfo)this.I.proceed(OutboundType.QUERY_SERVICES, paramIntent, null, new CondomCore.WrappedValueProcedure()
    {
      public ResolveInfo I()
      {
        if ((!CondomPackageManager.l(CondomPackageManager.this).mExcludeBackgroundServices) && (CondomPackageManager.l(CondomPackageManager.this).mOutboundJudge == null)) {
          return CondomPackageManager.lI(CondomPackageManager.this, paramIntent, paramInt);
        }
        List localList = CondomPackageManager.ll(CondomPackageManager.this, paramIntent, paramInt);
        Intent localIntent = paramIntent.setFlags(i);
        return CondomPackageManager.l(CondomPackageManager.this).filterCandidates(OutboundType.QUERY_SERVICES, localIntent, localList, CondomPackageManager.I(CondomPackageManager.this), false);
      }
    });
  }
}
