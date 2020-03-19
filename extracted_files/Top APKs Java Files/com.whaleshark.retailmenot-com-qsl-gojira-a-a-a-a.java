package com.qsl.gojira.a.a.a;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
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
import org.b.c;

public class a
  implements com.qsl.gojira.a.a.a
{
  private static org.b.b a = c.a(a.class);
  private final DenaliContextEngineService b;
  private long c = 0L;
  private List<AndroidBrowserHistoryQueryResult.AndroidBrowserHistoryQueryResultRecord> d;
  private long e = 0L;
  private Map<String, ApplicationsQueryResult.AppsQueryResultRecord> f;
  
  public a(DenaliContextEngineService paramDenaliContextEngineService)
  {
    this.b = paramDenaliContextEngineService;
  }
  
  private Iterator<ContactsQueryResult.ContactsQueryResultRecord> a(int paramInt, DenaliLocation paramDenaliLocation, DenaliTimeWindow paramDenaliTimeWindow)
  {
    ContactsPluginImpl localContactsPluginImpl = (ContactsPluginImpl)this.b.getPlugin("com.qualcomm.denali.contextEngineService.ContactsPluginImpl");
    if (localContactsPluginImpl == null)
    {
      a.e("Failed to get ContactsPlugin plugin");
      return new com.qsl.gojira.c.b();
    }
    if ((paramInt & 0x18) != 0) {}
    for (paramDenaliLocation = localContactsPluginImpl.get_phoneNumbersQueryService().GetTopPhoneNumbersByNumberOfMessages(paramInt, paramDenaliLocation, paramDenaliTimeWindow, 100);; paramDenaliLocation = localContactsPluginImpl.get_phoneNumbersQueryService().GetTopPhoneNumbersByNumberOfCalls(paramInt, paramDenaliLocation, paramDenaliTimeWindow, 100))
    {
      if ((paramDenaliLocation.nStatus != 0) || (paramDenaliLocation.nConfidence == 0)) {
        a.e("Failed to get Calls - status: {}   confidence: {}", Integer.valueOf(paramDenaliLocation.nStatus), Integer.valueOf(paramDenaliLocation.nConfidence));
      }
      return Arrays.asList(paramDenaliLocation.results).iterator();
    }
  }
  
  public final Iterator<AndroidBrowserHistoryQueryResult.AndroidBrowserHistoryQueryResultRecord> a()
  {
    if ((this.d == null) || (this.c < System.currentTimeMillis() - 900000L))
    {
      Object localObject = (AndroidBrowserHistoryPluginImpl)this.b.getPlugin("com.qualcomm.denali.contextEngineService.AndroidBrowserHistoryPluginImpl");
      if (localObject == null)
      {
        a.e("Failed to get AndroidBrowserHistory plugin");
        return new com.qsl.gojira.c.b();
      }
      localObject = ((AndroidBrowserHistoryPluginImpl)localObject).get_androidBrowserHistoryQueryService().GetTopURLsByTimesVisited(DenaliContextEngineServiceConstants.AnyPOI, DenaliContextEngineServiceConstants.AnyTimeWindow, 100);
      if ((((AndroidBrowserHistoryQueryResult)localObject).nStatus != 0) || (((AndroidBrowserHistoryQueryResult)localObject).nConfidence == 0)) {
        a.e("Failed to get URLs - status: {}   confidence: {}", Integer.valueOf(((AndroidBrowserHistoryQueryResult)localObject).nStatus), Integer.valueOf(((AndroidBrowserHistoryQueryResult)localObject).nConfidence));
      }
      this.d = Arrays.asList(((AndroidBrowserHistoryQueryResult)localObject).results);
      this.c = System.currentTimeMillis();
    }
    return this.d.iterator();
  }
  
  public final Iterator<ApplicationsQueryResult.AppsQueryResultRecord> b()
  {
    Object localObject1;
    HashMap localHashMap;
    Object localObject2;
    if ((this.f == null) || (this.e < System.currentTimeMillis() - 900000L))
    {
      localObject1 = (ApplicationsQueryImpl)this.b.getPlugin("com.qualcomm.denali.contextEngineService.ApplicationsQueryImpl");
      if (localObject1 == null)
      {
        a.e("Failed to get ApplicationsQuery plugin");
        return new com.qsl.gojira.c.b();
      }
      localObject1 = ((ApplicationsQueryImpl)localObject1).get_applicationsQueryService().GetTopApplicationsByTimeUsed(DenaliContextEngineServiceConstants.AnyPOI, DenaliContextEngineServiceConstants.AnyTimeWindow, 3, 100);
      if ((((ApplicationsQueryResult)localObject1).nStatus != 0) || (((ApplicationsQueryResult)localObject1).nConfidence == 0)) {
        a.e("Failed to get APPS - status: {}   confidence: {}", Integer.valueOf(((ApplicationsQueryResult)localObject1).nStatus), Integer.valueOf(((ApplicationsQueryResult)localObject1).nConfidence));
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
      localObject1 = this.b.getContext();
      if ((localObject1 != null) && (((Context)localObject1).getPackageManager() != null)) {
        break label210;
      }
    }
    for (;;)
    {
      this.f = localHashMap;
      this.e = System.currentTimeMillis();
      return this.f.values().iterator();
      label210:
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
    a.a("Num SMSs:  {}", Double.valueOf(d1));
    return d1;
  }
  
  public final double d()
  {
    Iterator localIterator = a(7, DenaliContextEngineServiceConstants.AnyPOI, DenaliContextEngineServiceConstants.AnyTimeWindow);
    for (double d1 = 0.0D; localIterator.hasNext(); d1 += ((ContactsQueryResult.ContactsQueryResultRecord)localIterator.next()).nNumInteractions) {}
    a.a("Num Calls:  {}", Double.valueOf(d1));
    return d1;
  }
  
  public final Iterator<PointsOfInterestQueryResult.PointsOfInterestQueryResultRecord> e()
  {
    Object localObject = (PointsOfInterestQueryImpl)this.b.getPlugin("com.qualcomm.denali.contextEngineService.PointsOfInterestQueryImpl");
    if (localObject == null)
    {
      a.e("Failed to get PointsOfInterestQuery plugin");
      return new com.qsl.gojira.c.b();
    }
    localObject = ((PointsOfInterestQueryImpl)localObject).GetTopPointsOfInterest(DenaliContextEngineServiceConstants.AnyTimeWindow, 0, 3, 100);
    if ((((PointsOfInterestQueryResult)localObject).nStatus != 0) || (((PointsOfInterestQueryResult)localObject).nConfidence == 0)) {
      a.e("Failed to get RecurringEvents - status: {}   confidence: {}", Integer.valueOf(((PointsOfInterestQueryResult)localObject).nStatus), Integer.valueOf(((PointsOfInterestQueryResult)localObject).nConfidence));
    }
    for (;;)
    {
      return Arrays.asList(((PointsOfInterestQueryResult)localObject).results).iterator();
      a.a("Num RecurringEvents:  {}", Integer.valueOf(((PointsOfInterestQueryResult)localObject).results.length));
    }
  }
}
