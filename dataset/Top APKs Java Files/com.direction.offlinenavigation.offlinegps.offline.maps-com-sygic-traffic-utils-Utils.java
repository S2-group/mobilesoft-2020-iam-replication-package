package com.sygic.traffic.utils;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import android.supporo.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import com.sygic.traffic.appusage.collection.PackageChangeReceiver;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.geom.PrecisionModel;
import com.vividsolutions.jts.index.strtree.STRtree;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Utils
{
  public Utils() {}
  
  public static class Api
  {
    public Api() {}
    
    public static boolean isSatisfied()
    {
      return Build.VERSION.SDK_INT >= 9;
    }
  }
  
  public static class Conversion
  {
    private static final double METERS_PER_SECOND_TO_KILOMETERS_PER_HOUR_COEFFICIENT = 3.6D;
    private static final long SYGIC_BEGINNING_OF_TIME_IN_MILLISECONDS = 978307200000L;
    
    public Conversion() {}
    
    public static int geoCoordinateDoubeToInt(double paramDouble)
    {
      return (int)Math.round(100000.0D * paramDouble);
    }
    
    public static double mpsTokph(double paramDouble)
    {
      return 3.6D * paramDouble;
    }
    
    public static int networkType(int paramInt)
    {
      switch (paramInt)
      {
      default: 
        return 3;
      case -1: 
        return 0;
      case 0: 
        return 1;
      }
      return 2;
    }
    
    public static int unixTimeInMillisToSygicTimeInSeconds(long paramLong)
    {
      return (int)TimeUnit.MILLISECONDS.toSeconds(paramLong - 978307200000L + 500L);
    }
    
    @Retention(RetentionPolicy.SOURCE)
    public static @interface NetworkType
    {
      public static final int CELLULAR = 1;
      public static final int OTHER = 3;
      public static final int UNKNOWN = 0;
      public static final int WIFI = 2;
    }
  }
  
  public static class Device
  {
    private static final String EXTERNAL_STORAGE_ENV = "EXTERNAL_STORAGE";
    private static final String SECONDARY_STORAGE_ENV = "SECONDARY_STORAGE";
    
    public Device() {}
    
    public static void disablePackageChangeReceiverInDefaultState(Context paramContext)
    {
      ComponentName localComponentName = new ComponentName(paramContext, PackageChangeReceiver.class);
      if (paramContext.getPackageManager().getComponentEnabledSetting(localComponentName) == 0) {
        paramContext.getPackageManager().setComponentEnabledSetting(localComponentName, 2, 1);
      }
    }
    
    public static void enablePackageChangeReceiver(Context paramContext, boolean paramBoolean)
    {
      ComponentName localComponentName = new ComponentName(paramContext, PackageChangeReceiver.class);
      int i = paramContext.getPackageManager().getComponentEnabledSetting(localComponentName);
      paramContext = paramContext.getPackageManager();
      if ((i == 1) && (!paramBoolean)) {
        paramContext.setComponentEnabledSetting(localComponentName, 2, 1);
      }
      while ((i != 2) || (!paramBoolean)) {
        return;
      }
      paramContext.setComponentEnabledSetting(localComponentName, 1, 1);
    }
    
    private static List<File> findExternalStorageByForcedPath()
    {
      ArrayList localArrayList = new ArrayList();
      File[] arrayOfFile = new File("/storage/").listFiles();
      if (arrayOfFile != null)
      {
        int j = arrayOfFile.length;
        int i = 0;
        while (i < j)
        {
          File localFile = arrayOfFile[i];
          if ((!localFile.getAbsolutePath().equalsIgnoreCase(Environment.getExternalStorageDirectory().getAbsolutePath())) && (localFile.isDirectory()) && (localFile.canRead())) {
            localArrayList.add(localFile);
          }
          i += 1;
        }
      }
      return localArrayList;
    }
    
    public static int getBatteryChargerState(Context paramContext)
    {
      paramContext = paramContext.getApplicationContext().registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
      if (paramContext != null)
      {
        if (paramContext.getIntExtra("plugged", 0) == 0) {
          return 0;
        }
        return 1;
      }
      return -1;
    }
    
    public static float getBatteryLevel(Context paramContext)
    {
      paramContext = paramContext.getApplicationContext().registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
      if (paramContext != null)
      {
        int i = paramContext.getIntExtra("level", -1);
        int j = paramContext.getIntExtra("scale", -1);
        if ((i != -1) && (j != -1)) {
          return i / j * 100.0F;
        }
      }
      return -1.0F;
    }
    
    private static File getExternalStoragePath(Context paramContext)
    {
      Object localObject1 = null;
      if (paramContext == null) {
        paramContext = (Context)localObject1;
      }
      do
      {
        return paramContext;
        localObject1 = new ArrayList();
        if (Build.VERSION.SDK_INT >= 19)
        {
          paramContext = paramContext.getExternalFilesDirs(null);
          int j = paramContext.length;
          int i = 0;
          while (i < j)
          {
            Object localObject2 = paramContext[i];
            if (localObject2 != null) {
              ((List)localObject1).add(localObject2);
            }
            i += 1;
          }
        }
        ((List)localObject1).add(Environment.getExternalStorageDirectory());
        localObject1 = readRecords((List)localObject1);
        paramContext = (Context)localObject1;
      } while (localObject1 != null);
      return readRecords(findExternalStorageByForcedPath());
    }
    
    public static List<PackageInfo> getListOfInstalledApplications(Context paramContext)
    {
      return paramContext.getPackageManager().getInstalledPackages(0);
    }
    
    public static String getOsVersion()
    {
      return Build.VERSION.RELEASE;
    }
    
    private static File getRemovableStorage(String paramString)
    {
      paramString = System.getenv(paramString);
      if ((paramString != null) && (!paramString.isEmpty()))
      {
        paramString = paramString.split(":");
        int j = paramString.length;
        int i = 0;
        while (i < j)
        {
          File localFile = new File(paramString[i]);
          if (localFile.isDirectory()) {
            return localFile;
          }
          i += 1;
        }
      }
      return null;
    }
    
    public static long getTotalExternalStorage(Context paramContext)
    {
      paramContext = getExternalStoragePath(paramContext);
      if (paramContext != null) {
        return getTotalStorageSpace(paramContext);
      }
      return -1L;
    }
    
    public static long getTotalInternalStorage()
    {
      File localFile = Environment.getDataDirectory();
      long l = -1L;
      if ((Build.VERSION.SDK_INT >= 11) && (!Environment.isExternalStorageRemovable()) && (Environment.isExternalStorageEmulated())) {
        l = getTotalStorageSpace(localFile);
      }
      while (Environment.isExternalStorageRemovable()) {
        return l;
      }
      return getTotalStorageSpace(localFile);
    }
    
    private static long getTotalStorageSpace(File paramFile)
    {
      paramFile = new StatFs(paramFile.getPath());
      if (Build.VERSION.SDK_INT >= 18) {
        return paramFile.getBlockCountLong() * paramFile.getBlockSizeLong();
      }
      return paramFile.getBlockCount() * paramFile.getBlockSize();
    }
    
    public static long getUsedExternalStorage(Context paramContext)
    {
      paramContext = getExternalStoragePath(paramContext);
      if (paramContext != null) {
        return getUsedStorageSpace(paramContext);
      }
      return -1L;
    }
    
    public static long getUsedInternalStorage()
    {
      File localFile = Environment.getDataDirectory();
      long l = -1L;
      if ((Build.VERSION.SDK_INT >= 11) && (!Environment.isExternalStorageRemovable()) && (Environment.isExternalStorageEmulated())) {
        l = getUsedStorageSpace(localFile);
      }
      while (Environment.isExternalStorageRemovable()) {
        return l;
      }
      return getUsedStorageSpace(localFile);
    }
    
    private static long getUsedStorageSpace(File paramFile)
    {
      paramFile = new StatFs(paramFile.getPath());
      if (Build.VERSION.SDK_INT >= 18) {
        return paramFile.getBlockSizeLong() * (paramFile.getBlockCountLong() - paramFile.getAvailableBlocksLong());
      }
      return paramFile.getBlockSize() * (paramFile.getBlockCount() - paramFile.getAvailableBlocks());
    }
    
    private static boolean isFileOnRemovableStorage(File paramFile)
    {
      File localFile = getRemovableStorage("SECONDARY_STORAGE");
      if (localFile != null) {
        try
        {
          boolean bool = paramFile.getCanonicalPath().startsWith(localFile.getCanonicalPath());
          return bool;
        }
        catch (IOException localIOException) {}
      }
      return !isOnDeviceStorage(paramFile);
    }
    
    private static boolean isOnDeviceStorage(File paramFile)
    {
      String str = System.getenv("EXTERNAL_STORAGE");
      try
      {
        str = new File(str).getCanonicalPath();
        boolean bool = paramFile.getCanonicalPath().startsWith(str);
        return bool;
      }
      catch (IOException paramFile)
      {
        Log.e("DEVELOP", "isOnDeviceStorage: ", paramFile);
      }
      return true;
    }
    
    private static File readRecords(List<File> paramList)
    {
      paramList = paramList.iterator();
      while (paramList.hasNext())
      {
        File localFile = (File)paramList.next();
        if (isFileOnRemovableStorage(localFile))
        {
          Log.d("DEVELOP", "readRecords: " + localFile.getAbsolutePath() + " is removable storage " + String.valueOf(isFileOnRemovableStorage(localFile)));
          return localFile;
        }
      }
      return null;
    }
  }
  
  public static class Geometry
  {
    private final GeometryFactory mGeometryFactory;
    private STRtree mTree;
    
    private Geometry(int paramInt)
    {
      this.mGeometryFactory = new GeometryFactory(new PrecisionModel(), paramInt);
    }
    
    private String getCountryCode(Location paramLocation)
    {
      if (this.mTree == null) {}
      List localList;
      do
      {
        return null;
        paramLocation = this.mGeometryFactory.createPoint(new Coordinate(paramLocation.getLongitude(), paramLocation.getLatitude()));
        localList = this.mTree.query(paramLocation.getEnvelopeInternal());
      } while (localList.size() == 0);
      int i = 0;
      while (i < localList.size() - 1)
      {
        Utils.Geometry.CountryPolygon localCountryPolygon = (Utils.Geometry.CountryPolygon)localList.get(i);
        if (Utils.Geometry.CountryPolygon.access$300(localCountryPolygon).contains(paramLocation)) {
          return Utils.Geometry.CountryPolygon.access$400(localCountryPolygon);
        }
        i += 1;
      }
      return Utils.Geometry.CountryPolygon.access$400((Utils.Geometry.CountryPolygon)localList.get(i));
    }
    
    private void loadWorldPolygons(InputStream paramInputStream, OnGeometryReadyListener paramOnGeometryReadyListener)
    {
      if (paramInputStream == null) {
        paramOnGeometryReadyListener.onGeometryFailed();
      }
      paramOnGeometryReadyListener = new Utils.Geometry.1(this, paramOnGeometryReadyListener);
      if (Build.VERSION.SDK_INT >= 11)
      {
        paramOnGeometryReadyListener.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new InputStream[] { paramInputStream });
        return;
      }
      paramOnGeometryReadyListener.execute(new InputStream[] { paramInputStream });
    }
    
    public static void obtainAsync(AssetManager paramAssetManager, int paramInt, OnGeometryReadyListener paramOnGeometryReadyListener)
    {
      Geometry localGeometry = new Geometry(paramInt);
      try
      {
        paramAssetManager = paramAssetManager.open("fcd.dat");
        localGeometry.loadWorldPolygons(paramAssetManager, paramOnGeometryReadyListener);
        return;
      }
      catch (IOException paramAssetManager)
      {
        for (;;)
        {
          Log.w("TrafficService", "can't load geometry data file from assets");
          paramAssetManager = null;
        }
      }
    }
    
    public static void obtainAsync(InputStream paramInputStream, int paramInt, OnGeometryReadyListener paramOnGeometryReadyListener)
    {
      new Geometry(paramInt).loadWorldPolygons(paramInputStream, paramOnGeometryReadyListener);
    }
    
    public static abstract interface OnGeometryReadyListener
    {
      public abstract void onGeometryFailed();
      
      public abstract void onGeometryReady(Utils.Geometry paramGeometry);
    }
  }
  
  public static class Location
  {
    public Location() {}
    
    public static String getCountryCode(Utils.Geometry paramGeometry, Location paramLocation)
    {
      if (paramGeometry != null) {
        return paramGeometry.getCountryCode(paramLocation);
      }
      return null;
    }
    
    public static String getCountryCodeUsingGeocoder(Context paramContext, Location paramLocation)
    {
      if (Geocoder.isPresent()) {
        try
        {
          paramContext = new Geocoder(paramContext, Locale.ENGLISH).getFromLocation(paramLocation.getLatitude(), paramLocation.getLongitude(), 3);
          if (paramContext != null)
          {
            paramContext = paramContext.iterator();
            while (paramContext.hasNext())
            {
              paramLocation = ((Address)paramContext.next()).getCountryCode();
              boolean bool = TextUtils.isEmpty(paramLocation);
              if (!bool) {
                return paramLocation;
              }
            }
          }
        }
        catch (IOException paramContext)
        {
          Log.w("TrafficService", "Can't get results from Geocoder service: " + paramContext.getMessage());
        }
      }
      return null;
    }
    
    @TargetApi(18)
    public static boolean isMockLocation(Location paramLocation)
    {
      return (Build.VERSION.SDK_INT >= 18) && (paramLocation.isFromMockProvider());
    }
  }
  
  public static class Network
  {
    public Network() {}
    
    public static int getCellType(int paramInt)
    {
      switch (paramInt)
      {
      default: 
        return 0;
      case 1: 
      case 2: 
      case 4: 
      case 7: 
      case 11: 
        return 1;
      case 3: 
      case 5: 
      case 6: 
      case 8: 
      case 9: 
      case 10: 
      case 12: 
      case 14: 
      case 15: 
        return 2;
      }
      return 3;
    }
    
    public static int getCellType(TelephonyManager paramTelephonyManager)
    {
      return getCellType(paramTelephonyManager.getNetworkType());
    }
    
    public static float getValidatedThroughputDown(int paramInt, float paramFloat)
    {
      switch (paramInt)
      {
      default: 
        return normalizeThroughput(paramFloat, 5.0F);
      case 13: 
        return normalizeThroughput(paramFloat, 300.0F);
      }
      return normalizeThroughput(paramFloat, 50.0F);
    }
    
    public static float getValidatedThroughputUp(int paramInt, float paramFloat)
    {
      switch (paramInt)
      {
      default: 
        return normalizeThroughput(paramFloat, 2.0F);
      case 13: 
        return normalizeThroughput(paramFloat, 100.0F);
      }
      return normalizeThroughput(paramFloat, 25.0F);
    }
    
    public static boolean isMobileDataActive(ConnectivityManager paramConnectivityManager)
    {
      paramConnectivityManager = paramConnectivityManager.getActiveNetworkInfo();
      return (paramConnectivityManager != null) && (paramConnectivityManager.isConnected()) && (paramConnectivityManager.getType() != 1);
    }
    
    private static float normalizeThroughput(float paramFloat1, float paramFloat2)
    {
      if (paramFloat1 >= paramFloat2) {
        return paramFloat1;
      }
      return 0.0F;
    }
    
    @Retention(RetentionPolicy.SOURCE)
    public static @interface CellType
    {
      public static final int GSM = 1;
      public static final int LTE = 3;
      public static final int UNKNOWN = 0;
      public static final int WCDMA = 2;
    }
    
    @Retention(RetentionPolicy.SOURCE)
    public static @interface InfoType
    {
      public static final int ASU_LEVEL = 8;
      public static final int CDMA_DBM = 20;
      public static final int CDMA_ECIO = 21;
      public static final int CI = 5;
      public static final int CID = 0;
      public static final int EARFCN = 11;
      public static final int EVDO_DBM = 22;
      public static final int EVDO_ECIO = 23;
      public static final int EVDO_SNR = 24;
      public static final int GSM_BIT_ERROR_RATE = 25;
      public static final int GSM_SIGNAL_STRENGTH = 26;
      public static final int LAC = 1;
      public static final int LEVEL = 10;
      public static final int MCC = 2;
      public static final int MNC = 3;
      public static final int NETWORK_TYPE = 12;
      public static final int PCI = 6;
      public static final int PSC = 4;
      public static final int RSSI = 9;
      public static final int TAC = 7;
    }
  }
  
  public static class Permissions
  {
    public Permissions() {}
    
    public static boolean isPermissionGranted(Context paramContext, String paramString)
    {
      boolean bool = false;
      try
      {
        int i = ContextCompat.checkSelfPermission(paramContext, paramString);
        if (i == 0) {
          bool = true;
        }
        return bool;
      }
      catch (RuntimeException paramContext) {}
      return false;
    }
  }
  
  public static class Stream
  {
    private static final int COUNTRY_CODE_MAX_SIZE = 3;
    private static final char SPACE_CHAR = ' ';
    
    public Stream() {}
    
    public static void writeCountryCode(LittleEndianDataOutputStream paramLittleEndianDataOutputStream, String paramString)
      throws IOException
    {
      StringBuilder localStringBuilder = new StringBuilder(3);
      int i = 0;
      if (i < 3)
      {
        if ((paramString != null) && (i < paramString.length())) {
          localStringBuilder.append(paramString.charAt(i));
        }
        for (;;)
        {
          i += 1;
          break;
          localStringBuilder.append(' ');
        }
      }
      writeString(paramLittleEndianDataOutputStream, localStringBuilder.toString());
    }
    
    public static void writeLocation(LittleEndianDataOutputStream paramLittleEndianDataOutputStream, Location paramLocation)
      throws IOException
    {
      writeLocation(paramLittleEndianDataOutputStream, paramLocation, true);
    }
    
    public static void writeLocation(LittleEndianDataOutputStream paramLittleEndianDataOutputStream, Location paramLocation, boolean paramBoolean)
      throws IOException
    {
      int j = 0;
      float f;
      if (paramBoolean)
      {
        if (paramLocation != null)
        {
          f = paramLocation.getAccuracy();
          paramLittleEndianDataOutputStream.writeFloat(f);
        }
      }
      else {
        if (paramLocation == null) {
          break label69;
        }
      }
      label69:
      for (int i = Utils.Conversion.geoCoordinateDoubeToInt(paramLocation.getLatitude());; i = 0)
      {
        paramLittleEndianDataOutputStream.writeInt(i);
        i = j;
        if (paramLocation != null) {
          i = Utils.Conversion.geoCoordinateDoubeToInt(paramLocation.getLongitude());
        }
        paramLittleEndianDataOutputStream.writeInt(i);
        return;
        f = 0.0F;
        break;
      }
    }
    
    public static void writeString(LittleEndianDataOutputStream paramLittleEndianDataOutputStream, String paramString)
      throws IOException
    {
      if (paramString != null) {
        paramLittleEndianDataOutputStream.write(paramString.getBytes(Charset.forName("UTF-8")));
      }
      paramLittleEndianDataOutputStream.writeByte(0);
    }
  }
}
