package com.qsl.gojira.rulesengine.datasources.impl;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import com.qsl.faar.service.location.a.c;
import com.qsl.faar.service.location.sensors.impl.d;
import com.qsl.gojira.rulesengine.datasources.DenaliDataSource;
import com.qsl.gojira.util.EmptyIterator;
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
import jp.pp.android.obfuscated.a.a;
import jp.pp.android.obfuscated.a.b;

public class DenaliDataSourceImpl
  implements DenaliDataSource
{
  private static final int FIFTEEN_MIN_IN_MILLISECONDS = 900000;
  public static final int MAXRESULTSIZE = 100;
  public static final a privateLogger = d.a(DenaliDataSourceImpl.class.getName());
  public static final b publicLogger = c.a(DenaliDataSourceImpl.class.getName());
  private Map<String, ApplicationsQueryResult.AppsQueryResultRecord> appCache;
  private long appCacheTime = 0L;
  private final DenaliContextEngineService denaliService;
  private List<AndroidBrowserHistoryQueryResult.AndroidBrowserHistoryQueryResultRecord> urlCache;
  private long urlCacheTime = 0L;
  
  public DenaliDataSourceImpl(DenaliContextEngineService paramDenaliContextEngineService)
  {
    this.denaliService = paramDenaliContextEngineService;
  }
  
  private Map<String, ApplicationsQueryResult.AppsQueryResultRecord> addInstalledAppsToDenaliData(ApplicationsQueryResult.AppsQueryResultRecord[] paramArrayOfAppsQueryResultRecord)
  {
    HashMap localHashMap = new HashMap();
    int j = paramArrayOfAppsQueryResultRecord.length;
    int i = 0;
    ApplicationsQueryResult.AppsQueryResultRecord localAppsQueryResultRecord;
    while (i < j)
    {
      localAppsQueryResultRecord = paramArrayOfAppsQueryResultRecord[i];
      localHashMap.put(localAppsQueryResultRecord.processName, localAppsQueryResultRecord);
      i += 1;
    }
    paramArrayOfAppsQueryResultRecord = this.denaliService.getContext();
    if ((paramArrayOfAppsQueryResultRecord == null) || (paramArrayOfAppsQueryResultRecord.getPackageManager() == null)) {
      return localHashMap;
    }
    Iterator localIterator = paramArrayOfAppsQueryResultRecord.getPackageManager().getInstalledApplications(0).iterator();
    while (localIterator.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
      localAppsQueryResultRecord = (ApplicationsQueryResult.AppsQueryResultRecord)localHashMap.get(localApplicationInfo.packageName);
      paramArrayOfAppsQueryResultRecord = localAppsQueryResultRecord;
      if (localAppsQueryResultRecord == null)
      {
        paramArrayOfAppsQueryResultRecord = new ApplicationsQueryResult.AppsQueryResultRecord();
        paramArrayOfAppsQueryResultRecord.processName = localApplicationInfo.packageName;
        localHashMap.put(localApplicationInfo.packageName, paramArrayOfAppsQueryResultRecord);
      }
      paramArrayOfAppsQueryResultRecord.isCurrentlyInstalled = true;
    }
    return localHashMap;
  }
  
  private Iterator<ContactsQueryResult.ContactsQueryResultRecord> getPhoneRecord(int paramInt1, DenaliLocation paramDenaliLocation, DenaliTimeWindow paramDenaliTimeWindow, int paramInt2)
  {
    ContactsPluginImpl localContactsPluginImpl = (ContactsPluginImpl)this.denaliService.getPlugin("com.qualcomm.denali.contextEngineService.ContactsPluginImpl");
    if (localContactsPluginImpl == null)
    {
      publicLogger.c("Failed to get ContactsPlugin plugin", new Object[0]);
      return new EmptyIterator();
    }
    if ((paramInt1 & 0x18) != 0) {}
    for (paramDenaliLocation = localContactsPluginImpl.get_phoneNumbersQueryService().GetTopPhoneNumbersByNumberOfMessages(paramInt1, paramDenaliLocation, paramDenaliTimeWindow, paramInt2);; paramDenaliLocation = localContactsPluginImpl.get_phoneNumbersQueryService().GetTopPhoneNumbersByNumberOfCalls(paramInt1, paramDenaliLocation, paramDenaliTimeWindow, paramInt2))
    {
      if ((paramDenaliLocation.nStatus != 0) || (paramDenaliLocation.nConfidence == 0)) {
        publicLogger.c("Failed to get Calls - status: {}   confidence: {}", new Object[] { Integer.valueOf(paramDenaliLocation.nStatus), Integer.valueOf(paramDenaliLocation.nConfidence) });
      }
      return Arrays.asList(paramDenaliLocation.results).iterator();
    }
  }
  
  public Iterator<ApplicationsQueryResult.AppsQueryResultRecord> getApplications()
  {
    if ((this.appCache == null) || (this.appCacheTime < System.currentTimeMillis() - 900000L))
    {
      Object localObject = (ApplicationsQueryImpl)this.denaliService.getPlugin("com.qualcomm.denali.contextEngineService.ApplicationsQueryImpl");
      if (localObject == null)
      {
        publicLogger.c("Failed to get ApplicationsQuery plugin", new Object[0]);
        return new EmptyIterator();
      }
      localObject = ((ApplicationsQueryImpl)localObject).get_applicationsQueryService().GetTopApplicationsByTimeUsed(DenaliContextEngineServiceConstants.AnyPOI, DenaliContextEngineServiceConstants.AnyTimeWindow, 3, 100);
      if ((((ApplicationsQueryResult)localObject).nStatus != 0) || (((ApplicationsQueryResult)localObject).nConfidence == 0)) {
        publicLogger.c("Failed to get APPS - status: {}   confidence: {}", new Object[] { Integer.valueOf(((ApplicationsQueryResult)localObject).nStatus), Integer.valueOf(((ApplicationsQueryResult)localObject).nConfidence) });
      }
      this.appCache = addInstalledAppsToDenaliData(((ApplicationsQueryResult)localObject).results);
      this.appCacheTime = System.currentTimeMillis();
    }
    return this.appCache.values().iterator();
  }
  
  public double getCallRate()
  {
    Iterator localIterator = getPhoneRecord(7, DenaliContextEngineServiceConstants.AnyPOI, DenaliContextEngineServiceConstants.AnyTimeWindow, 100);
    for (double d = 0.0D; localIterator.hasNext(); d = ((ContactsQueryResult.ContactsQueryResultRecord)localIterator.next()).nNumInteractions + d) {}
    privateLogger.a("Num Calls:  {}", new Object[] { Double.valueOf(d) });
    return d;
  }
  
  public Iterator<ContactsQueryResult.ContactsQueryResultRecord> getCallRecord()
  {
    return getPhoneRecord(7, DenaliContextEngineServiceConstants.AnyPOI, DenaliContextEngineServiceConstants.AnyTimeWindow, 100);
  }
  
  public Iterator<PointsOfInterestQueryResult.PointsOfInterestQueryResultRecord> getPointsOfInterest()
  {
    Object localObject = (PointsOfInterestQueryImpl)this.denaliService.getPlugin("com.qualcomm.denali.contextEngineService.PointsOfInterestQueryImpl");
    if (localObject == null)
    {
      publicLogger.c("Failed to get PointsOfInterestQuery plugin", new Object[0]);
      return new EmptyIterator();
    }
    localObject = ((PointsOfInterestQueryImpl)localObject).GetTopPointsOfInterest(DenaliContextEngineServiceConstants.AnyTimeWindow, 0, 3, 100);
    if ((((PointsOfInterestQueryResult)localObject).nStatus != 0) || (((PointsOfInterestQueryResult)localObject).nConfidence == 0)) {
      publicLogger.c("Failed to get RecurringEvents - status: {}   confidence: {}", new Object[] { Integer.valueOf(((PointsOfInterestQueryResult)localObject).nStatus), Integer.valueOf(((PointsOfInterestQueryResult)localObject).nConfidence) });
    }
    for (;;)
    {
      return Arrays.asList(((PointsOfInterestQueryResult)localObject).results).iterator();
      privateLogger.a("Num RecurringEvents:  {}", new Object[] { Integer.valueOf(((PointsOfInterestQueryResult)localObject).results.length) });
    }
  }
  
  public double getSMSRate()
  {
    Iterator localIterator = getPhoneRecord(24, DenaliContextEngineServiceConstants.AnyPOI, DenaliContextEngineServiceConstants.AnyTimeWindow, 100);
    for (double d = 0.0D; localIterator.hasNext(); d = ((ContactsQueryResult.ContactsQueryResultRecord)localIterator.next()).nNumInteractions + d) {}
    privateLogger.a("Num SMSs:  {}", new Object[] { Double.valueOf(d) });
    return d;
  }
  
  public Iterator<ContactsQueryResult.ContactsQueryResultRecord> getSMSRecord()
  {
    return getPhoneRecord(24, DenaliContextEngineServiceConstants.AnyPOI, DenaliContextEngineServiceConstants.AnyTimeWindow, 100);
  }
  
  public Iterator<AndroidBrowserHistoryQueryResult.AndroidBrowserHistoryQueryResultRecord> getUrls()
  {
    if ((this.urlCache == null) || (this.urlCacheTime < System.currentTimeMillis() - 900000L))
    {
      Object localObject = (AndroidBrowserHistoryPluginImpl)this.denaliService.getPlugin("com.qualcomm.denali.contextEngineService.AndroidBrowserHistoryPluginImpl");
      if (localObject == null)
      {
        publicLogger.c("Failed to get AndroidBrowserHistory plugin", new Object[0]);
        return new EmptyIterator();
      }
      localObject = ((AndroidBrowserHistoryPluginImpl)localObject).get_androidBrowserHistoryQueryService().GetTopURLsByTimesVisited(DenaliContextEngineServiceConstants.AnyPOI, DenaliContextEngineServiceConstants.AnyTimeWindow, 100);
      if ((((AndroidBrowserHistoryQueryResult)localObject).nStatus != 0) || (((AndroidBrowserHistoryQueryResult)localObject).nConfidence == 0)) {
        publicLogger.c("Failed to get URLs - status: {}   confidence: {}", new Object[] { Integer.valueOf(((AndroidBrowserHistoryQueryResult)localObject).nStatus), Integer.valueOf(((AndroidBrowserHistoryQueryResult)localObject).nConfidence) });
      }
      this.urlCache = Arrays.asList(((AndroidBrowserHistoryQueryResult)localObject).results);
      this.urlCacheTime = System.currentTimeMillis();
    }
    return this.urlCache.iterator();
  }
}
