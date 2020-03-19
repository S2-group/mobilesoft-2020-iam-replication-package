package com.qsl.gojira.a.a.a;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import com.gimbal.logging.PrivateLogger;
import com.gimbal.logging.PrivateLoggerFactory;
import com.gimbal.logging.PublicLogger;
import com.gimbal.logging.PublicLoggerFactory;
import com.qsl.gojira.c.b;
import com.qualcomm.denali.contextEngineService.AndroidBrowserHistoryPluginImpl;
import com.qualcomm.denali.contextEngineService.AndroidBrowserHistoryPluginImpl.AndroidBrowserHistoryQueryService;
import com.qualcomm.denali.contextEngineService.ApplicationsQueryImpl;
import com.qualcomm.denali.contextEngineService.ApplicationsQueryImpl.ApplicationsQueryService;
import com.qualcomm.denali.contextEngineService.ContactsPluginImpl;
import com.qualcomm.denali.contextEngineService.ContactsPluginImpl.PhoneNumbersQueryService;
import com.qualcomm.denali.contextEngineService.DenaliContextEngineService;
import com.qualcomm.denali.contextEngineService.PointsOfInterestQueryImpl;
import com.qualcomm.denali.cxsinterface.AndroidBrowserHistoryQueryResult;
import com.qualcomm.denali.cxsinterface.AndroidBrowserHistoryQueryResult.AndroidBrowserHistoryQueryResultRecord;
import com.qualcomm.denali.cxsinterface.ApplicationsQueryResult;
import com.qualcomm.denali.cxsinterface.ApplicationsQueryResult.AppsQueryResultRecord;
import com.qualcomm.denali.cxsinterface.ContactsQueryResult;
import com.qualcomm.denali.cxsinterface.ContactsQueryResult.ContactsQueryResultRecord;
import com.qualcomm.denali.cxsinterface.DenaliContextEngineServiceConstants;
import com.qualcomm.denali.cxsinterface.DenaliLocation;
import com.qualcomm.denali.cxsinterface.DenaliTimeWindow;
import com.qualcomm.denali.cxsinterface.PointsOfInterestQueryResult;
import com.qualcomm.denali.cxsinterface.PointsOfInterestQueryResult.PointsOfInterestQueryResultRecord;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class a
  implements com.qsl.gojira.a.a.a
{
  private static PrivateLogger a = PrivateLoggerFactory.getLogger(a.class);
  private static PublicLogger b = PublicLoggerFactory.getLogger(a.class);
  private final DenaliContextEngineService c;
  private long d = 0L;
  private List<AndroidBrowserHistoryQueryResult.AndroidBrowserHistoryQueryResultRecord> e;
  private long f = 0L;
  private Map<String, ApplicationsQueryResult.AppsQueryResultRecord> g;
  
  public a(DenaliContextEngineService paramDenaliContextEngineService)
  {
    this.c = paramDenaliContextEngineService;
  }
  
  private Iterator<ContactsQueryResult.ContactsQueryResultRecord> a(int paramInt, DenaliLocation paramDenaliLocation, DenaliTimeWindow paramDenaliTimeWindow)
  {
    ContactsPluginImpl localContactsPluginImpl = (ContactsPluginImpl)this.c.getPlugin("com.qualcomm.denali.contextEngineService.ContactsPluginImpl");
    if (localContactsPluginImpl == null)
    {
      b.error("Failed to get ContactsPlugin plugin", new Object[0]);
      return new b();
    }
    if ((paramInt & 0x18) != 0) {}
    for (paramDenaliLocation = localContactsPluginImpl.get_phoneNumbersQueryService().GetTopPhoneNumbersByNumberOfMessages(paramInt, paramDenaliLocation, paramDenaliTimeWindow, 100);; paramDenaliLocation = localContactsPluginImpl.get_phoneNumbersQueryService().GetTopPhoneNumbersByNumberOfCalls(paramInt, paramDenaliLocation, paramDenaliTimeWindow, 100))
    {
      if ((paramDenaliLocation.nStatus != 0) || (paramDenaliLocation.nConfidence == 0)) {
        b.error("Failed to get Calls - status: {}   confidence: {}", new Object[] { Integer.valueOf(paramDenaliLocation.nStatus), Integer.valueOf(paramDenaliLocation.nConfidence) });
      }
      return Arrays.asList(paramDenaliLocation.results).iterator();
    }
  }
  
  public final Iterator<AndroidBrowserHistoryQueryResult.AndroidBrowserHistoryQueryResultRecord> a()
  {
    if ((this.e == null) || (this.d < System.currentTimeMillis() - 900000L))
    {
      Object localObject = (AndroidBrowserHistoryPluginImpl)this.c.getPlugin("com.qualcomm.denali.contextEngineService.AndroidBrowserHistoryPluginImpl");
      if (localObject == null)
      {
        b.error("Failed to get AndroidBrowserHistory plugin", new Object[0]);
        return new b();
      }
      localObject = ((AndroidBrowserHistoryPluginImpl)localObject).get_androidBrowserHistoryQueryService().GetTopURLsByTimesVisited(DenaliContextEngineServiceConstants.AnyPOI, DenaliContextEngineServiceConstants.AnyTimeWindow, 100);
      if ((((AndroidBrowserHistoryQueryResult)localObject).nStatus != 0) || (((AndroidBrowserHistoryQueryResult)localObject).nConfidence == 0)) {
        b.error("Failed to get URLs - status: {}   confidence: {}", new Object[] { Integer.valueOf(((AndroidBrowserHistoryQueryResult)localObject).nStatus), Integer.valueOf(((AndroidBrowserHistoryQueryResult)localObject).nConfidence) });
      }
      this.e = Arrays.asList(((AndroidBrowserHistoryQueryResult)localObject).results);
      this.d = System.currentTimeMillis();
    }
    return this.e.iterator();
  }
  
  public final Iterator<ApplicationsQueryResult.AppsQueryResultRecord> b()
  {
    Object localObject1;
    HashMap localHashMap;
    Object localObject2;
    if ((this.g == null) || (this.f < System.currentTimeMillis() - 900000L))
    {
      localObject1 = (ApplicationsQueryImpl)this.c.getPlugin("com.qualcomm.denali.contextEngineService.ApplicationsQueryImpl");
      if (localObject1 == null)
      {
        b.error("Failed to get ApplicationsQuery plugin", new Object[0]);
        return new b();
      }
      localObject1 = ((ApplicationsQueryImpl)localObject1).get_applicationsQueryService().GetTopApplicationsByTimeUsed(DenaliContextEngineServiceConstants.AnyPOI, DenaliContextEngineServiceConstants.AnyTimeWindow, 3, 100);
      if ((((ApplicationsQueryResult)localObject1).nStatus != 0) || (((ApplicationsQueryResult)localObject1).nConfidence == 0)) {
        b.error("Failed to get APPS - status: {}   confidence: {}", new Object[] { Integer.valueOf(((ApplicationsQueryResult)localObject1).nStatus), Integer.valueOf(((ApplicationsQueryResult)localObject1).nConfidence) });
      }
      localObject1 = ((ApplicationsQueryResult)localObject1).results;
      localHashMap = new HashMap();
      int j = localObject1.length;
      int i = 0;
      while (i < j)
      {
        localObject2 = localObject1[i];
        localHashMap.put(((ApplicationsQueryResult.AppsQueryResultRecord)localObject2).processName, localObject2);
        i += 1;
      }
      localObject1 = this.c.getContext();
      if ((localObject1 != null) && (((Context)localObject1).getPackageManager() != null)) {
        break label220;
      }
    }
    for (;;)
    {
      this.g = localHashMap;
      this.f = System.currentTimeMillis();
      return this.g.values().iterator();
      label220:
      Iterator localIterator = ((Context)localObject1).getPackageManager().getInstalledApplications(0).iterator();
      while (localIterator.hasNext())
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
        localObject2 = (ApplicationsQueryResult.AppsQueryResultRecord)localHashMap.get(localApplicationInfo.packageName);
        localObject1 = localObject2;
        if (localObject2 == null)
        {
          localObject1 = new ApplicationsQueryResult.AppsQueryResultRecord();
          ((ApplicationsQueryResult.AppsQueryResultRecord)localObject1).processName = localApplicationInfo.packageName;
          localHashMap.put(localApplicationInfo.packageName, localObject1);
        }
        ((ApplicationsQueryResult.AppsQueryResultRecord)localObject1).isCurrentlyInstalled = true;
      }
    }
  }
  
  public final double c()
  {
    Iterator localIterator = a(24, DenaliContextEngineServiceConstants.AnyPOI, DenaliContextEngineServiceConstants.AnyTimeWindow);
    for (double d1 = 0.0D; localIterator.hasNext(); d1 += ((ContactsQueryResult.ContactsQueryResultRecord)localIterator.next()).nNumInteractions) {}
    a.trace("Num SMSs:  {}", new Object[] { Double.valueOf(d1) });
    return d1;
  }
  
  public final double d()
  {
    Iterator localIterator = a(7, DenaliContextEngineServiceConstants.AnyPOI, DenaliContextEngineServiceConstants.AnyTimeWindow);
    for (double d1 = 0.0D; localIterator.hasNext(); d1 += ((ContactsQueryResult.ContactsQueryResultRecord)localIterator.next()).nNumInteractions) {}
    a.trace("Num Calls:  {}", new Object[] { Double.valueOf(d1) });
    return d1;
  }
  
  public final Iterator<PointsOfInterestQueryResult.PointsOfInterestQueryResultRecord> e()
  {
    Object localObject = (PointsOfInterestQueryImpl)this.c.getPlugin("com.qualcomm.denali.contextEngineService.PointsOfInterestQueryImpl");
    if (localObject == null)
    {
      b.error("Failed to get PointsOfInterestQuery plugin", new Object[0]);
      return new b();
    }
    localObject = ((PointsOfInterestQueryImpl)localObject).GetTopPointsOfInterest(DenaliContextEngineServiceConstants.AnyTimeWindow, 0, 3, 100);
    if ((((PointsOfInterestQueryResult)localObject).nStatus != 0) || (((PointsOfInterestQueryResult)localObject).nConfidence == 0)) {
      b.error("Failed to get RecurringEvents - status: {}   confidence: {}", new Object[] { Integer.valueOf(((PointsOfInterestQueryResult)localObject).nStatus), Integer.valueOf(((PointsOfInterestQueryResult)localObject).nConfidence) });
    }
    for (;;)
    {
      return Arrays.asList(((PointsOfInterestQueryResult)localObject).results).iterator();
      a.trace("Num RecurringEvents:  {}", new Object[] { Integer.valueOf(((PointsOfInterestQueryResult)localObject).results.length) });
    }
  }
}
