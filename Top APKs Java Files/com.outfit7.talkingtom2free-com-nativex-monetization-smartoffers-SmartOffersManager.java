package com.nativex.monetization.smartoffers;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.nativex.monetization.communication.ServerRequestManager;
import com.nativex.monetization.manager.MonetizationSharedDataManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class SmartOffersManager
{
  private static final boolean enabled = true;
  
  private SmartOffersManager() {}
  
  private static final void checkForInstalledApps(final List<SmartOffer> paramList)
  {
    Context localContext = MonetizationSharedDataManager.getContext();
    if (localContext == null) {
      return;
    }
    new Thread(new Runnable()
    {
      public final void run()
      {
        Object localObject1 = this.val$packageManager.getInstalledPackages(8193);
        HashMap localHashMap = new HashMap();
        localObject1 = ((List)localObject1).iterator();
        while (((Iterator)localObject1).hasNext())
        {
          localObject2 = (PackageInfo)((Iterator)localObject1).next();
          if (((PackageInfo)localObject2).packageName != null) {
            localHashMap.put(((PackageInfo)localObject2).packageName, localObject2);
          }
        }
        localObject1 = new ArrayList();
        Object localObject2 = paramList.iterator();
        while (((Iterator)localObject2).hasNext())
        {
          Object localObject3 = (SmartOffer)((Iterator)localObject2).next();
          SmartOfferResult localSmartOfferResult = new SmartOfferResult();
          localSmartOfferResult.setId(((SmartOffer)localObject3).getId());
          if (localHashMap.containsKey(((SmartOffer)localObject3).getValue()))
          {
            localObject3 = (PackageInfo)localHashMap.get(((SmartOffer)localObject3).getValue());
            if (localObject3 != null)
            {
              localSmartOfferResult.setValue(Boolean.valueOf(true));
              localSmartOfferResult.setFirstTimeUTC(Long.toString(((PackageInfo)localObject3).firstInstallTime));
            }
            for (;;)
            {
              ((List)localObject1).add(localSmartOfferResult);
              break;
              localSmartOfferResult.setValue(Boolean.valueOf(false));
            }
          }
        }
        if (((List)localObject1).size() > 0) {
          ServerRequestManager.getInstance().commitSmartOffers((List)localObject1);
        }
      }
    }).start();
  }
  
  public static final void configureSmartOffers(List<SmartOffer> paramList)
  {
    if ((paramList != null) && (paramList.size() > 0) && (paramList != null) && (paramList.size() > 0)) {
      checkForInstalledApps(paramList);
    }
  }
  
  public static final void doSmartOffersCheck()
  {
    ServerRequestManager.getInstance().getSmartOfferRules();
  }
}
