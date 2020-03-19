package com.nativex.monetization.smartoffers;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.HandlerThread;
import com.nativex.monetization.communication.ServerRequestManager;
import com.nativex.monetization.manager.MonetizationSharedDataManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class SmartOffersManager
{
  private static final boolean enabled = true;
  private static HandlerThread handlerThread;
  private static SmartOffersManager instance;
  private static List<SmartOffer> mSmartOffers;
  private static SmartOffersCheckRunnable runnable;
  private static Handler threadHandler;
  
  private SmartOffersManager()
  {
    handlerThread = new HandlerThread("SmartOffers-Thread");
    handlerThread.start();
    threadHandler = new Handler(handlerThread.getLooper());
    runnable = new SmartOffersCheckRunnable(null);
  }
  
  private static final void checkForInstalledApps(List<SmartOffer> paramList)
  {
    Object localObject1 = MonetizationSharedDataManager.getContext();
    if (localObject1 == null) {}
    ArrayList localArrayList;
    do
    {
      return;
      Object localObject2 = ((Context)localObject1).getPackageManager();
      localObject1 = new HashMap();
      localArrayList = new ArrayList();
      Object localObject3;
      try
      {
        localObject2 = ((PackageManager)localObject2).getInstalledPackages(8193).iterator();
        while (((Iterator)localObject2).hasNext())
        {
          localObject3 = (PackageInfo)((Iterator)localObject2).next();
          if (((PackageInfo)localObject3).packageName != null) {
            ((HashMap)localObject1).put(((PackageInfo)localObject3).packageName, localObject3);
          }
        }
        paramList = paramList.iterator();
      }
      catch (Exception paramList)
      {
        paramList.printStackTrace();
        return;
      }
      while (paramList.hasNext())
      {
        localObject3 = (SmartOffer)paramList.next();
        localObject2 = new SmartOfferResult();
        ((SmartOfferResult)localObject2).setId(((SmartOffer)localObject3).getId());
        if (((HashMap)localObject1).containsKey(((SmartOffer)localObject3).getValue()))
        {
          localObject3 = (PackageInfo)((HashMap)localObject1).get(((SmartOffer)localObject3).getValue());
          if (localObject3 != null)
          {
            ((SmartOfferResult)localObject2).setValue(Boolean.valueOf(true));
            ((SmartOfferResult)localObject2).setFirstTimeUTC(Long.toString(((PackageInfo)localObject3).firstInstallTime));
          }
          for (;;)
          {
            localArrayList.add(localObject2);
            break;
            ((SmartOfferResult)localObject2).setValue(Boolean.valueOf(false));
          }
        }
      }
    } while ((localArrayList == null) || (localArrayList.size() <= 0));
    ServerRequestManager.getInstance().commitSmartOffers(localArrayList);
  }
  
  public static SmartOffersManager getInstance()
  {
    if (instance == null) {
      instance = new SmartOffersManager();
    }
    return instance;
  }
  
  public static void release()
  {
    if (handlerThread != null) {
      handlerThread.quit();
    }
    handlerThread = null;
    threadHandler = null;
    runnable = null;
    mSmartOffers = null;
    instance = null;
  }
  
  public final void configureSmartOffers(List<SmartOffer> paramList)
  {
    if ((paramList != null) && (paramList.size() > 0))
    {
      mSmartOffers = paramList;
      threadHandler.post(runnable);
    }
  }
  
  public final void doSmartOffersCheck()
  {
    ServerRequestManager.getInstance().getSmartOfferRules();
  }
  
  private static class SmartOffersCheckRunnable
    implements Runnable
  {
    private SmartOffersCheckRunnable() {}
    
    public void run()
    {
      SmartOffersManager.checkForInstalledApps(SmartOffersManager.mSmartOffers);
      SmartOffersManager.access$102(null);
    }
  }
}
