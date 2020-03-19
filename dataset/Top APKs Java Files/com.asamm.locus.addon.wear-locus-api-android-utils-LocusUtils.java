package locus.api.android.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.text.TextUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import locus.api.android.ActionTools;
import locus.api.android.utils.exceptions.RequiredVersionMissingException;
import locus.api.objects.Storable;
import locus.api.objects.extra.Track;
import locus.api.objects.extra.Waypoint;
import locus.api.utils.DataReaderBigEndian;
import locus.api.utils.DataWriterBigEndian;
import locus.api.utils.Logger;
import locus.api.utils.Utils;

public class LocusUtils
{
  private static final String TAG = "LocusUtils";
  
  public LocusUtils() {}
  
  public static void addWaypointToIntent(Intent paramIntent, Waypoint paramWaypoint)
  {
    paramIntent.putExtra("INTENT_EXTRA_POINT", paramWaypoint.getAsBytes());
  }
  
  public static void callInstallLocus(Context paramContext)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://market.android.com/details?id=menion.android.locus"));
    localIntent.addFlags(268435456);
    paramContext.startActivity(localIntent);
  }
  
  public static void callStartLocusMap(Context paramContext)
  {
    Intent localIntent = new Intent("com.asamm.locus.map.START_APP");
    localIntent.addFlags(268435456);
    paramContext.startActivity(localIntent);
  }
  
  public static android.location.Location convertToA(locus.api.objects.extra.Location paramLocation)
  {
    android.location.Location localLocation = new android.location.Location(paramLocation.getProvider());
    localLocation.setLongitude(paramLocation.getLongitude());
    localLocation.setLatitude(paramLocation.getLatitude());
    localLocation.setTime(paramLocation.getTime());
    if (paramLocation.hasAccuracy()) {
      localLocation.setAccuracy(paramLocation.getAccuracy());
    }
    if (paramLocation.hasAltitude()) {
      localLocation.setAltitude(paramLocation.getAltitude());
    }
    if (paramLocation.hasBearing()) {
      localLocation.setBearing(paramLocation.getBearing());
    }
    if (paramLocation.hasSpeed()) {
      localLocation.setSpeed(paramLocation.getSpeed());
    }
    return localLocation;
  }
  
  public static locus.api.objects.extra.Location convertToL(android.location.Location paramLocation)
  {
    locus.api.objects.extra.Location localLocation = new locus.api.objects.extra.Location(paramLocation.getProvider());
    localLocation.setLongitude(paramLocation.getLongitude());
    localLocation.setLatitude(paramLocation.getLatitude());
    localLocation.setTime(paramLocation.getTime());
    if (paramLocation.hasAccuracy()) {
      localLocation.setAccuracy(paramLocation.getAccuracy());
    }
    if (paramLocation.hasAltitude()) {
      localLocation.setAltitude(paramLocation.getAltitude());
    }
    if (paramLocation.hasBearing()) {
      localLocation.setBearing(paramLocation.getBearing());
    }
    if (paramLocation.hasSpeed()) {
      localLocation.setSpeed(paramLocation.getSpeed());
    }
    return localLocation;
  }
  
  @Deprecated
  public static LocusVersion createLocusVersion(Context paramContext)
  {
    if (paramContext == null)
    {
      paramContext = null;
      return paramContext;
    }
    Logger.logW("LocusUtils", "getLocusVersion(" + paramContext + "), Warning: old version of Locus: Correct package name is not known!");
    List localList = getAvailableVersions(paramContext);
    int i = 0;
    int j = localList.size();
    for (;;)
    {
      if (i >= j) {
        break label95;
      }
      LocusVersion localLocusVersion = (LocusVersion)localList.get(i);
      paramContext = localLocusVersion;
      if (localLocusVersion.isVersionFree()) {
        break;
      }
      paramContext = localLocusVersion;
      if (localLocusVersion.isVersionPro()) {
        break;
      }
      i += 1;
    }
    label95:
    return null;
  }
  
  public static LocusVersion createLocusVersion(Context paramContext, Intent paramIntent)
  {
    if ((paramContext == null) || (paramIntent == null)) {
      return null;
    }
    paramIntent = paramIntent.getStringExtra("INTENT_EXTRA_PACKAGE_NAME");
    if ((paramIntent != null) && (paramIntent.length() > 0)) {
      return createLocusVersion(paramContext, paramIntent);
    }
    return createLocusVersion(paramContext);
  }
  
  public static LocusVersion createLocusVersion(Context paramContext, String paramString)
  {
    if (paramString != null) {
      try
      {
        if (paramString.length() != 0)
        {
          if (!paramString.startsWith("menion.android.locus")) {
            return null;
          }
          Object localObject = paramContext.getPackageManager().getPackageInfo(paramString, 0);
          if (localObject != null)
          {
            localObject = new LocusVersion(paramString, ((PackageInfo)localObject).versionName, ((PackageInfo)localObject).versionCode, null);
            return localObject;
          }
        }
      }
      catch (Exception localException)
      {
        Logger.logE("LocusUtils", "getLocusVersion(" + paramContext + ", " + paramString + ")", localException);
      }
    }
    return null;
  }
  
  public static LocusVersion getActiveVersion(Context paramContext)
  {
    return getActiveVersion(paramContext, VersionCode.UPDATE_01);
  }
  
  public static LocusVersion getActiveVersion(Context paramContext, int paramInt)
  {
    return getActiveVersion(paramContext, paramInt, paramInt, paramInt);
  }
  
  private static LocusVersion getActiveVersion(Context paramContext, int paramInt1, int paramInt2, int paramInt3)
  {
    List localList = getAvailableVersions(paramContext);
    if (localList.size() == 0) {
      return null;
    }
    Object localObject2 = null;
    long l1 = 0L;
    int i = 0;
    int j = localList.size();
    for (;;)
    {
      long l2;
      Object localObject4;
      if (i < j)
      {
        long l3 = l1;
        Object localObject1 = localObject2;
        try
        {
          LocusVersion localLocusVersion = (LocusVersion)localList.get(i);
          l3 = l1;
          localObject1 = localObject2;
          if (localLocusVersion.isVersionFree())
          {
            l2 = l1;
            localObject4 = localObject2;
            if (paramInt1 <= 0) {
              break label384;
            }
            l3 = l1;
            localObject1 = localObject2;
            if (localLocusVersion.getVersionCode() < paramInt1)
            {
              l2 = l1;
              localObject4 = localObject2;
              break label384;
            }
          }
          else
          {
            l3 = l1;
            localObject1 = localObject2;
            if (!localLocusVersion.isVersionPro()) {
              break label273;
            }
            l2 = l1;
            localObject4 = localObject2;
            if (paramInt2 <= 0) {
              break label384;
            }
            l3 = l1;
            localObject1 = localObject2;
            l2 = l1;
            localObject4 = localObject2;
            if (localLocusVersion.getVersionCode() < paramInt2) {
              break label384;
            }
          }
          label273:
          int k;
          do
          {
            l3 = l1;
            localObject1 = localObject2;
            LocusInfo localLocusInfo = ActionTools.getLocusInfo(paramContext, localLocusVersion);
            l2 = l1;
            localObject4 = localObject2;
            if (localLocusInfo == null) {
              break;
            }
            if (localObject2 != null)
            {
              l2 = l1;
              localObject4 = localObject2;
              l3 = l1;
              localObject1 = localObject2;
              if (localLocusInfo.getLastActive() < l1) {}
            }
            else
            {
              localObject4 = localLocusVersion;
              l3 = l1;
              localObject1 = localObject4;
              l2 = localLocusInfo.getLastActive();
            }
            l3 = l2;
            localObject1 = localObject4;
            if (!localLocusInfo.isRunning()) {
              break;
            }
            return localLocusVersion;
            l3 = l1;
            localObject1 = localObject2;
            l2 = l1;
            localObject4 = localObject2;
            if (!localLocusVersion.isVersionGis()) {
              break;
            }
            l2 = l1;
            localObject4 = localObject2;
            if (paramInt3 <= 0) {
              break;
            }
            l3 = l1;
            localObject1 = localObject2;
            k = localLocusVersion.getVersionCode();
          } while (k >= paramInt3);
          l2 = l1;
          localObject4 = localObject2;
        }
        catch (RequiredVersionMissingException localRequiredVersionMissingException)
        {
          Logger.logE("LocusUtils", "prepareActiveLocus()", localRequiredVersionMissingException);
          l2 = l3;
          localObject4 = localObject1;
        }
      }
      if (localRequiredVersionMissingException != null) {
        return localRequiredVersionMissingException;
      }
      return (LocusVersion)localList.get(0);
      label384:
      i += 1;
      l1 = l2;
      Object localObject3 = localObject4;
    }
  }
  
  public static LocusVersion getActiveVersion(Context paramContext, VersionCode paramVersionCode)
  {
    return getActiveVersion(paramContext, paramVersionCode.vcFree, paramVersionCode.vcPro, paramVersionCode.vcGis);
  }
  
  public static List<LocusVersion> getAvailableVersions(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    List localList = paramContext.getPackageManager().getInstalledApplications(0);
    int i = 0;
    int j = localList.size();
    while (i < j)
    {
      Object localObject = (ApplicationInfo)localList.get(i);
      if (isPackageNameLocus(((ApplicationInfo)localObject).packageName))
      {
        localObject = createLocusVersion(paramContext, ((ApplicationInfo)localObject).packageName);
        if (localObject != null) {
          localArrayList.add(localObject);
        }
      }
      i += 1;
    }
    return localArrayList;
  }
  
  public static locus.api.objects.extra.Location getLocationFromIntent(Intent paramIntent, String paramString)
  {
    try
    {
      if (!paramIntent.hasExtra(paramString)) {
        return null;
      }
      paramString = new locus.api.objects.extra.Location(paramIntent.getByteArrayExtra(paramString));
      return paramString;
    }
    catch (Exception paramString)
    {
      Logger.logE("LocusUtils", "getLocationFromIntent(" + paramIntent + ")", paramString);
    }
    return null;
  }
  
  public static Waypoint getWaypointFromIntent(Intent paramIntent)
  {
    try
    {
      Waypoint localWaypoint = new Waypoint(paramIntent.getByteArrayExtra("INTENT_EXTRA_POINT"));
      return localWaypoint;
    }
    catch (Exception localException)
    {
      Logger.logE("LocusUtils", "getWaypointFromIntent(" + paramIntent + ")", localException);
    }
    return null;
  }
  
  public static void handleIntentGetLocation(Context paramContext, Intent paramIntent, OnIntentGetLocation paramOnIntentGetLocation)
    throws NullPointerException
  {
    if (paramIntent == null) {
      throw new NullPointerException("Intent cannot be null");
    }
    if (!isIntentGetLocation(paramIntent))
    {
      paramOnIntentGetLocation.onFailed();
      return;
    }
    paramOnIntentGetLocation.onReceived(getLocationFromIntent(paramIntent, "INTENT_EXTRA_LOCATION_GPS"), getLocationFromIntent(paramIntent, "INTENT_EXTRA_LOCATION_MAP_CENTER"));
  }
  
  public static void handleIntentMainFunction(Context paramContext, Intent paramIntent, OnIntentMainFunction paramOnIntentMainFunction)
    throws NullPointerException
  {
    handleIntentMenuItem(paramContext, paramIntent, paramOnIntentMainFunction, "locus.api.android.INTENT_ITEM_MAIN_FUNCTION");
  }
  
  public static void handleIntentMainFunctionGc(Context paramContext, Intent paramIntent, OnIntentMainFunction paramOnIntentMainFunction)
    throws NullPointerException
  {
    handleIntentMenuItem(paramContext, paramIntent, paramOnIntentMainFunction, "locus.api.android.INTENT_ITEM_MAIN_FUNCTION_GC");
  }
  
  private static void handleIntentMenuItem(Context paramContext, Intent paramIntent, OnIntentMainFunction paramOnIntentMainFunction, String paramString)
    throws NullPointerException
  {
    if (paramIntent == null) {
      throw new NullPointerException("Intent cannot be null");
    }
    if (paramOnIntentMainFunction == null) {
      throw new NullPointerException("Handler cannot be null");
    }
    if (!isRequiredAction(paramIntent, paramString))
    {
      paramOnIntentMainFunction.onFailed();
      return;
    }
    paramOnIntentMainFunction.onReceived(createLocusVersion(paramContext, paramIntent), getLocationFromIntent(paramIntent, "INTENT_EXTRA_LOCATION_GPS"), getLocationFromIntent(paramIntent, "INTENT_EXTRA_LOCATION_MAP_CENTER"));
  }
  
  public static Waypoint handleIntentPointTools(Context paramContext, Intent paramIntent)
    throws RequiredVersionMissingException
  {
    long l = paramIntent.getLongExtra("INTENT_EXTRA_ITEM_ID", -1L);
    if (l < 0L) {
      return null;
    }
    return ActionTools.getLocusWaypoint(paramContext, createLocusVersion(paramContext, paramIntent), l);
  }
  
  public static long[] handleIntentPointsScreenTools(Intent paramIntent)
  {
    long[] arrayOfLong = null;
    if (paramIntent.hasExtra("INTENT_EXTRA_ITEMS_ID")) {
      arrayOfLong = paramIntent.getLongArrayExtra("INTENT_EXTRA_ITEMS_ID");
    }
    return arrayOfLong;
  }
  
  public static void handleIntentSearchList(Context paramContext, Intent paramIntent, OnIntentMainFunction paramOnIntentMainFunction)
    throws NullPointerException
  {
    handleIntentMenuItem(paramContext, paramIntent, paramOnIntentMainFunction, "locus.api.android.INTENT_ITEM_SEARCH_LIST");
  }
  
  public static Track handleIntentTrackTools(Context paramContext, Intent paramIntent)
    throws RequiredVersionMissingException
  {
    long l = paramIntent.getLongExtra("INTENT_EXTRA_ITEM_ID", -1L);
    if (l < 0L) {
      return null;
    }
    return ActionTools.getLocusTrack(paramContext, createLocusVersion(paramContext, paramIntent), l);
  }
  
  public static boolean isAppAvailable(Context paramContext, String paramString, int paramInt)
  {
    boolean bool2 = false;
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramString, 0);
      boolean bool1 = bool2;
      if (paramContext != null)
      {
        int i = paramContext.versionCode;
        bool1 = bool2;
        if (i >= paramInt) {
          bool1 = true;
        }
      }
      return bool1;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  public static boolean isIntentGetLocation(Intent paramIntent)
  {
    return isRequiredAction(paramIntent, "locus.api.android.INTENT_ITEM_GET_LOCATION");
  }
  
  public static boolean isIntentMainFunction(Intent paramIntent)
  {
    return isRequiredAction(paramIntent, "locus.api.android.INTENT_ITEM_MAIN_FUNCTION");
  }
  
  public static boolean isIntentMainFunctionGc(Intent paramIntent)
  {
    return isRequiredAction(paramIntent, "locus.api.android.INTENT_ITEM_MAIN_FUNCTION_GC");
  }
  
  public static boolean isIntentPointTools(Intent paramIntent)
  {
    return isRequiredAction(paramIntent, "locus.api.android.INTENT_ITEM_POINT_TOOLS");
  }
  
  public static boolean isIntentPointsScreenTools(Intent paramIntent)
  {
    return isRequiredAction(paramIntent, "locus.api.android.INTENT_ITEM_POINTS_SCREEN_TOOLS");
  }
  
  public static boolean isIntentReceiveLocation(Intent paramIntent)
  {
    return isRequiredAction(paramIntent, "locus.api.android.ACTION_RECEIVE_LOCATION");
  }
  
  public static boolean isIntentSearchList(Intent paramIntent)
  {
    return isRequiredAction(paramIntent, "locus.api.android.INTENT_ITEM_SEARCH_LIST");
  }
  
  public static boolean isIntentTrackTools(Intent paramIntent)
  {
    return isRequiredAction(paramIntent, "locus.api.android.INTENT_ITEM_TRACK_TOOLS");
  }
  
  public static boolean isLocusAvailable(Context paramContext)
  {
    return isLocusAvailable(paramContext, VersionCode.UPDATE_01);
  }
  
  public static boolean isLocusAvailable(Context paramContext, int paramInt1, int paramInt2, int paramInt3)
  {
    paramContext = getAvailableVersions(paramContext);
    int i = 0;
    int j = paramContext.size();
    while (i < j)
    {
      LocusVersion localLocusVersion = (LocusVersion)paramContext.get(i);
      if ((localLocusVersion.isVersionFree()) && (paramInt1 > 0) && (localLocusVersion.getVersionCode() >= paramInt1)) {}
      while (((localLocusVersion.isVersionPro()) && (paramInt2 > 0) && (localLocusVersion.getVersionCode() >= paramInt2)) || ((localLocusVersion.isVersionGis()) && (paramInt3 > 0) && (localLocusVersion.getVersionCode() >= paramInt3))) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  public static boolean isLocusAvailable(Context paramContext, VersionCode paramVersionCode)
  {
    return isLocusAvailable(paramContext, paramVersionCode.vcFree, paramVersionCode.vcPro, paramVersionCode.vcGis);
  }
  
  public static boolean isLocusFreePro(LocusVersion paramLocusVersion, int paramInt)
  {
    boolean bool2 = true;
    boolean bool1;
    if (paramLocusVersion == null) {
      bool1 = false;
    }
    do
    {
      do
      {
        do
        {
          return bool1;
          if (!paramLocusVersion.isVersionFree()) {
            break;
          }
          bool1 = bool2;
        } while (paramLocusVersion.getVersionCode() >= paramInt);
        bool1 = bool2;
      } while (!paramLocusVersion.isVersionPro());
      bool1 = bool2;
    } while (paramLocusVersion.getVersionCode() < paramInt);
    return true;
  }
  
  private static boolean isPackageNameLocus(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0)) {}
    while ((!paramString.startsWith("menion")) || ((!paramString.equals("menion.android.locus")) && (!paramString.startsWith("menion.android.locus.free")) && (!paramString.startsWith("menion.android.locus.pro")))) {
      return false;
    }
    return true;
  }
  
  private static boolean isRequiredAction(Intent paramIntent, String paramString)
  {
    return (paramIntent != null) && (paramIntent.getAction() != null) && (paramIntent.getAction().equals(paramString));
  }
  
  public static Intent prepareResultExtraOnDisplayIntent(Waypoint paramWaypoint, boolean paramBoolean)
  {
    Intent localIntent = new Intent();
    addWaypointToIntent(localIntent, paramWaypoint);
    localIntent.putExtra("INTENT_EXTRA_POINT_OVERWRITE", paramBoolean);
    return localIntent;
  }
  
  public static void sendBroadcast(Context paramContext, Intent paramIntent, LocusVersion paramLocusVersion)
  {
    paramIntent.setPackage(paramLocusVersion.getPackageName());
    paramContext.sendBroadcast(paramIntent);
  }
  
  public static boolean sendGetLocationData(Activity paramActivity, String paramString, locus.api.objects.extra.Location paramLocation)
  {
    if (paramLocation == null) {
      return false;
    }
    Intent localIntent = new Intent();
    if (!TextUtils.isEmpty(paramString)) {
      localIntent.putExtra("INTENT_EXTRA_NAME", paramString);
    }
    localIntent.putExtra("INTENT_EXTRA_LOCATION", paramLocation.getAsBytes());
    paramActivity.setResult(-1, localIntent);
    paramActivity.finish();
    return true;
  }
  
  public static class LocusVersion
    extends Storable
  {
    private String mPackageName;
    private int mVersionCode;
    private String mVersionName;
    
    public LocusVersion() {}
    
    private LocusVersion(String paramString1, String paramString2, int paramInt)
    {
      String str = paramString1;
      if (paramString1 == null) {
        str = "";
      }
      this.mPackageName = str;
      paramString1 = paramString2;
      if (paramString2 == null) {
        paramString1 = "";
      }
      this.mVersionName = paramString1;
      int i = paramInt;
      if (paramInt < 0) {
        i = 0;
      }
      this.mVersionCode = i;
    }
    
    public String getPackageName()
    {
      return this.mPackageName;
    }
    
    protected int getVersion()
    {
      return 0;
    }
    
    public int getVersionCode()
    {
      return this.mVersionCode;
    }
    
    public String getVersionName()
    {
      return this.mVersionName;
    }
    
    public boolean isVersionFree()
    {
      return (!isVersionPro()) && (!isVersionGis());
    }
    
    public boolean isVersionGis()
    {
      return this.mPackageName.contains(".gis");
    }
    
    public boolean isVersionPro()
    {
      return this.mPackageName.contains(".pro");
    }
    
    public boolean isVersionValid(LocusUtils.VersionCode paramVersionCode)
    {
      if (isVersionFree()) {
        if ((paramVersionCode.vcFree == 0) || (this.mVersionCode < paramVersionCode.vcFree)) {}
      }
      do
      {
        do
        {
          return true;
          return false;
          if (!isVersionPro()) {
            break;
          }
        } while ((paramVersionCode.vcPro != 0) && (this.mVersionCode >= paramVersionCode.vcPro));
        return false;
        if (!isVersionGis()) {
          break;
        }
      } while ((paramVersionCode.vcGis != 0) && (this.mVersionCode >= paramVersionCode.vcGis));
      return false;
      return false;
    }
    
    protected void readObject(int paramInt, DataReaderBigEndian paramDataReaderBigEndian)
      throws IOException
    {
      this.mPackageName = paramDataReaderBigEndian.readString();
      this.mVersionName = paramDataReaderBigEndian.readString();
      this.mVersionCode = paramDataReaderBigEndian.readInt();
    }
    
    public void reset()
    {
      this.mPackageName = "";
      this.mVersionName = "";
      this.mVersionCode = 0;
    }
    
    public String toString()
    {
      return Utils.toString(this);
    }
    
    protected void writeObject(DataWriterBigEndian paramDataWriterBigEndian)
      throws IOException
    {
      paramDataWriterBigEndian.writeString(this.mPackageName);
      paramDataWriterBigEndian.writeString(this.mVersionName);
      paramDataWriterBigEndian.writeInt(this.mVersionCode);
    }
  }
  
  public static abstract interface OnIntentGetLocation
  {
    public abstract void onFailed();
    
    public abstract void onReceived(locus.api.objects.extra.Location paramLocation1, locus.api.objects.extra.Location paramLocation2);
  }
  
  public static abstract interface OnIntentMainFunction
  {
    public abstract void onFailed();
    
    public abstract void onReceived(LocusUtils.LocusVersion paramLocusVersion, locus.api.objects.extra.Location paramLocation1, locus.api.objects.extra.Location paramLocation2);
  }
  
  public static enum VersionCode
  {
    UPDATE_01(235, 235, 0),  UPDATE_02(242, 242, 0),  UPDATE_03(269, 269, 0),  UPDATE_04(278, 278, 0),  UPDATE_05(296, 296, 0),  UPDATE_06(311, 311, 5),  UPDATE_07(317, 317, 0),  UPDATE_08(343, 343, 0),  UPDATE_09(357, 357, 0),  UPDATE_10(370, 370, 0),  UPDATE_11(380, 380, 0),  UPDATE_12(421, 421, 0),  UPDATE_13(652, 652, 0),  UPDATE_14(684, 684, 0);
    
    public final int vcFree;
    public final int vcGis;
    public final int vcPro;
    
    private VersionCode(int paramInt1, int paramInt2, int paramInt3)
    {
      this.vcFree = paramInt1;
      this.vcPro = paramInt2;
      this.vcGis = paramInt3;
    }
  }
}
