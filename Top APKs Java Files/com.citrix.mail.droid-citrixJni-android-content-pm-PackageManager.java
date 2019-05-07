package citrixJni.android.content.pm;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.ChangedPackages;
import android.content.pm.FeatureInfo;
import android.content.pm.InstrumentationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.pm.SharedLibraryInfo;
import android.content.pm.VersionedPackage;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.UserHandle;
import citrix.InterceptMethod;
import com.citrix.APIContainment.a.d;
import com.citrix.APIContainment.a.e;
import com.citrix.mdx.e.b.a;
import com.citrix.util.JniStubHelper;
import java.util.List;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.JoinPoint.StaticPart;
import org.aspectj.runtime.internal.AroundClosure;
import org.aspectj.runtime.internal.Conversions;
import org.aspectj.runtime.reflect.Factory;

public class PackageManager
{
  public static final int COMPONENT_ENABLED_STATE_DEFAULT = 0;
  public static final int COMPONENT_ENABLED_STATE_DISABLED = 2;
  public static final int COMPONENT_ENABLED_STATE_DISABLED_UNTIL_USED = 4;
  public static final int COMPONENT_ENABLED_STATE_DISABLED_USER = 3;
  public static final int COMPONENT_ENABLED_STATE_ENABLED = 1;
  public static final int DONT_KILL_APP = 1;
  public static final String EXTRA_VERIFICATION_ID = "android.content.pm.extra.VERIFICATION_ID";
  public static final String EXTRA_VERIFICATION_RESULT = "android.content.pm.extra.VERIFICATION_RESULT";
  public static final String FEATURE_ACTIVITIES_ON_SECONDARY_DISPLAYS = "android.software.activities_on_secondary_displays";
  public static final String FEATURE_APP_WIDGETS = "android.software.app_widgets";
  public static final String FEATURE_AUDIO_LOW_LATENCY = "android.hardware.audio.low_latency";
  public static final String FEATURE_AUDIO_OUTPUT = "android.hardware.audio.output";
  public static final String FEATURE_AUDIO_PRO = "android.hardware.audio.pro";
  public static final String FEATURE_AUTOFILL = "android.software.autofill";
  public static final String FEATURE_AUTOMOTIVE = "android.hardware.type.automotive";
  public static final String FEATURE_BACKUP = "android.software.backup";
  public static final String FEATURE_BLUETOOTH = "android.hardware.bluetooth";
  public static final String FEATURE_BLUETOOTH_LE = "android.hardware.bluetooth_le";
  public static final String FEATURE_CAMERA = "android.hardware.camera";
  public static final String FEATURE_CAMERA_ANY = "android.hardware.camera.any";
  public static final String FEATURE_CAMERA_AUTOFOCUS = "android.hardware.camera.autofocus";
  public static final String FEATURE_CAMERA_CAPABILITY_MANUAL_POST_PROCESSING = "android.hardware.camera.capability.manual_post_processing";
  public static final String FEATURE_CAMERA_CAPABILITY_MANUAL_SENSOR = "android.hardware.camera.capability.manual_sensor";
  public static final String FEATURE_CAMERA_CAPABILITY_RAW = "android.hardware.camera.capability.raw";
  public static final String FEATURE_CAMERA_EXTERNAL = "android.hardware.camera.external";
  public static final String FEATURE_CAMERA_FLASH = "android.hardware.camera.flash";
  public static final String FEATURE_CAMERA_FRONT = "android.hardware.camera.front";
  public static final String FEATURE_CAMERA_LEVEL_FULL = "android.hardware.camera.level.full";
  public static final String FEATURE_COMPANION_DEVICE_SETUP = "android.software.companion_device_setup";
  public static final String FEATURE_CONNECTION_SERVICE = "android.software.connectionservice";
  public static final String FEATURE_CONSUMER_IR = "android.hardware.consumerir";
  public static final String FEATURE_DEVICE_ADMIN = "android.software.device_admin";
  public static final String FEATURE_EMBEDDED = "android.hardware.type.embedded";
  public static final String FEATURE_ETHERNET = "android.hardware.ethernet";
  public static final String FEATURE_FAKETOUCH = "android.hardware.faketouch";
  public static final String FEATURE_FAKETOUCH_MULTITOUCH_DISTINCT = "android.hardware.faketouch.multitouch.distinct";
  public static final String FEATURE_FAKETOUCH_MULTITOUCH_JAZZHAND = "android.hardware.faketouch.multitouch.jazzhand";
  public static final String FEATURE_FINGERPRINT = "android.hardware.fingerprint";
  public static final String FEATURE_FREEFORM_WINDOW_MANAGEMENT = "android.software.freeform_window_management";
  public static final String FEATURE_GAMEPAD = "android.hardware.gamepad";
  public static final String FEATURE_HIFI_SENSORS = "android.hardware.sensor.hifi_sensors";
  public static final String FEATURE_HOME_SCREEN = "android.software.home_screen";
  public static final String FEATURE_INPUT_METHODS = "android.software.input_methods";
  public static final String FEATURE_LEANBACK = "android.software.leanback";
  public static final String FEATURE_LEANBACK_ONLY = "android.software.leanback_only";
  public static final String FEATURE_LIVE_TV = "android.software.live_tv";
  public static final String FEATURE_LIVE_WALLPAPER = "android.software.live_wallpaper";
  public static final String FEATURE_LOCATION = "android.hardware.location";
  public static final String FEATURE_LOCATION_GPS = "android.hardware.location.gps";
  public static final String FEATURE_LOCATION_NETWORK = "android.hardware.location.network";
  public static final String FEATURE_MANAGED_USERS = "android.software.managed_users";
  public static final String FEATURE_MICROPHONE = "android.hardware.microphone";
  public static final String FEATURE_MIDI = "android.software.midi";
  public static final String FEATURE_NFC = "android.hardware.nfc";
  public static final String FEATURE_NFC_HOST_CARD_EMULATION = "android.hardware.nfc.hce";
  public static final String FEATURE_NFC_HOST_CARD_EMULATION_NFCF = "android.hardware.nfc.hcef";
  public static final String FEATURE_OPENGLES_EXTENSION_PACK = "android.hardware.opengles.aep";
  public static final String FEATURE_PC = "android.hardware.type.pc";
  public static final String FEATURE_PICTURE_IN_PICTURE = "android.software.picture_in_picture";
  public static final String FEATURE_PRINTING = "android.software.print";
  public static final String FEATURE_RAM_LOW = "android.hardware.ram.low";
  public static final String FEATURE_RAM_NORMAL = "android.hardware.ram.normal";
  public static final String FEATURE_SCREEN_LANDSCAPE = "android.hardware.screen.landscape";
  public static final String FEATURE_SCREEN_PORTRAIT = "android.hardware.screen.portrait";
  public static final String FEATURE_SECURELY_REMOVES_USERS = "android.software.securely_removes_users";
  public static final String FEATURE_SENSOR_ACCELEROMETER = "android.hardware.sensor.accelerometer";
  public static final String FEATURE_SENSOR_AMBIENT_TEMPERATURE = "android.hardware.sensor.ambient_temperature";
  public static final String FEATURE_SENSOR_BAROMETER = "android.hardware.sensor.barometer";
  public static final String FEATURE_SENSOR_COMPASS = "android.hardware.sensor.compass";
  public static final String FEATURE_SENSOR_GYROSCOPE = "android.hardware.sensor.gyroscope";
  public static final String FEATURE_SENSOR_HEART_RATE = "android.hardware.sensor.heartrate";
  public static final String FEATURE_SENSOR_HEART_RATE_ECG = "android.hardware.sensor.heartrate.ecg";
  public static final String FEATURE_SENSOR_LIGHT = "android.hardware.sensor.light";
  public static final String FEATURE_SENSOR_PROXIMITY = "android.hardware.sensor.proximity";
  public static final String FEATURE_SENSOR_RELATIVE_HUMIDITY = "android.hardware.sensor.relative_humidity";
  public static final String FEATURE_SENSOR_STEP_COUNTER = "android.hardware.sensor.stepcounter";
  public static final String FEATURE_SENSOR_STEP_DETECTOR = "android.hardware.sensor.stepdetector";
  public static final String FEATURE_SIP = "android.software.sip";
  public static final String FEATURE_SIP_VOIP = "android.software.sip.voip";
  public static final String FEATURE_TELEPHONY = "android.hardware.telephony";
  public static final String FEATURE_TELEPHONY_CDMA = "android.hardware.telephony.cdma";
  public static final String FEATURE_TELEPHONY_GSM = "android.hardware.telephony.gsm";
  @Deprecated
  public static final String FEATURE_TELEVISION = "android.hardware.type.television";
  public static final String FEATURE_TOUCHSCREEN = "android.hardware.touchscreen";
  public static final String FEATURE_TOUCHSCREEN_MULTITOUCH = "android.hardware.touchscreen.multitouch";
  public static final String FEATURE_TOUCHSCREEN_MULTITOUCH_DISTINCT = "android.hardware.touchscreen.multitouch.distinct";
  public static final String FEATURE_TOUCHSCREEN_MULTITOUCH_JAZZHAND = "android.hardware.touchscreen.multitouch.jazzhand";
  public static final String FEATURE_USB_ACCESSORY = "android.hardware.usb.accessory";
  public static final String FEATURE_USB_HOST = "android.hardware.usb.host";
  public static final String FEATURE_VERIFIED_BOOT = "android.software.verified_boot";
  public static final String FEATURE_VR_HEADTRACKING = "android.hardware.vr.headtracking";
  public static final String FEATURE_VR_MODE = "android.software.vr.mode";
  public static final String FEATURE_VR_MODE_HIGH_PERFORMANCE = "android.hardware.vr.high_performance";
  public static final String FEATURE_VULKAN_HARDWARE_COMPUTE = "android.hardware.vulkan.compute";
  public static final String FEATURE_VULKAN_HARDWARE_LEVEL = "android.hardware.vulkan.level";
  public static final String FEATURE_VULKAN_HARDWARE_VERSION = "android.hardware.vulkan.version";
  public static final String FEATURE_WATCH = "android.hardware.type.watch";
  public static final String FEATURE_WEBVIEW = "android.software.webview";
  public static final String FEATURE_WIFI = "android.hardware.wifi";
  public static final String FEATURE_WIFI_AWARE = "android.hardware.wifi.aware";
  public static final String FEATURE_WIFI_DIRECT = "android.hardware.wifi.direct";
  public static final String FEATURE_WIFI_PASSPOINT = "android.hardware.wifi.passpoint";
  public static final int GET_ACTIVITIES = 1;
  public static final int GET_CONFIGURATIONS = 16384;
  @Deprecated
  public static final int GET_DISABLED_COMPONENTS = 512;
  @Deprecated
  public static final int GET_DISABLED_UNTIL_USED_COMPONENTS = 32768;
  public static final int GET_GIDS = 256;
  public static final int GET_INSTRUMENTATION = 16;
  public static final int GET_INTENT_FILTERS = 32;
  public static final int GET_META_DATA = 128;
  public static final int GET_PERMISSIONS = 4096;
  public static final int GET_PROVIDERS = 8;
  public static final int GET_RECEIVERS = 2;
  public static final int GET_RESOLVED_FILTER = 64;
  public static final int GET_SERVICES = 4;
  public static final int GET_SHARED_LIBRARY_FILES = 1024;
  public static final int GET_SIGNATURES = 64;
  @Deprecated
  public static final int GET_UNINSTALLED_PACKAGES = 8192;
  public static final int GET_URI_PERMISSION_PATTERNS = 2048;
  public static final int INSTALL_REASON_DEVICE_RESTORE = 2;
  public static final int INSTALL_REASON_DEVICE_SETUP = 3;
  public static final int INSTALL_REASON_POLICY = 1;
  public static final int INSTALL_REASON_UNKNOWN = 0;
  public static final int INSTALL_REASON_USER = 4;
  public static final int MATCH_ALL = 131072;
  public static final int MATCH_DEFAULT_ONLY = 65536;
  public static final int MATCH_DIRECT_BOOT_AWARE = 524288;
  public static final int MATCH_DIRECT_BOOT_UNAWARE = 262144;
  public static final int MATCH_DISABLED_COMPONENTS = 512;
  public static final int MATCH_DISABLED_UNTIL_USED_COMPONENTS = 32768;
  public static final int MATCH_SYSTEM_ONLY = 1048576;
  public static final int MATCH_UNINSTALLED_PACKAGES = 8192;
  public static final long MAXIMUM_VERIFICATION_TIMEOUT = 3600000L;
  public static final int PERMISSION_DENIED = -1;
  public static final int PERMISSION_GRANTED = 0;
  public static final int SIGNATURE_FIRST_NOT_SIGNED = -1;
  public static final int SIGNATURE_MATCH = 0;
  public static final int SIGNATURE_NEITHER_SIGNED = 1;
  public static final int SIGNATURE_NO_MATCH = -3;
  public static final int SIGNATURE_SECOND_NOT_SIGNED = -2;
  public static final int SIGNATURE_UNKNOWN_PACKAGE = -4;
  public static final int VERIFICATION_ALLOW = 1;
  public static final int VERIFICATION_REJECT = -1;
  public static final int VERSION_CODE_HIGHEST = -1;
  private static final JoinPoint.StaticPart ajc$tjp_0;
  
  static {}
  
  @InterceptMethod
  public static void addPackageToPreferred(Object paramObject, String paramString)
  {
    JniStubHelper.a(new Object[] { paramString }, new boolean[0], new byte[0], new char[0], new short[0], new int[0], new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static boolean addPermission(Object paramObject, PermissionInfo paramPermissionInfo)
  {
    return JniStubHelper.c(new Object[] { paramPermissionInfo }, new boolean[0], new byte[0], new char[0], new short[0], new int[0], new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static boolean addPermissionAsync(Object paramObject, PermissionInfo paramPermissionInfo)
  {
    return JniStubHelper.c(new Object[] { paramPermissionInfo }, new boolean[0], new byte[0], new char[0], new short[0], new int[0], new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static void addPreferredActivity(Object paramObject, IntentFilter paramIntentFilter, int paramInt, ComponentName[] paramArrayOfComponentName, ComponentName paramComponentName)
  {
    JniStubHelper.a(new Object[] { paramIntentFilter, paramArrayOfComponentName, paramComponentName }, new boolean[0], new byte[0], new char[0], new short[0], new int[] { paramInt }, new long[0], new float[0], new double[0]);
  }
  
  private static void ajc$preClinit()
  {
    Factory localFactory = new Factory("PackageManager.java", PackageManager.class);
    ajc$tjp_0 = localFactory.makeSJP("method-execution", localFactory.makeMethodSig("9", "hasSystemFeature", "citrixJni.android.content.pm.PackageManager", "java.lang.Object:java.lang.String", "arg0:arg1", "", "boolean"), 0);
  }
  
  @InterceptMethod
  public static boolean canRequestPackageInstalls(Object paramObject)
  {
    return JniStubHelper.c(new Object[0], new boolean[0], new byte[0], new char[0], new short[0], new int[0], new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static String[] canonicalToCurrentPackageNames(Object paramObject, String[] paramArrayOfString)
  {
    return (String[])JniStubHelper.b(new Object[] { paramArrayOfString }, new boolean[0], new byte[0], new char[0], new short[0], new int[0], new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static int checkPermission(Object paramObject, String paramString1, String paramString2)
  {
    return JniStubHelper.g(new Object[] { paramString1, paramString2 }, new boolean[0], new byte[0], new char[0], new short[0], new int[0], new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static int checkSignatures(Object paramObject, int paramInt1, int paramInt2)
  {
    return JniStubHelper.g(new Object[0], new boolean[0], new byte[0], new char[0], new short[0], new int[] { paramInt1, paramInt2 }, new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static int checkSignatures(Object paramObject, String paramString1, String paramString2)
  {
    return JniStubHelper.g(new Object[] { paramString1, paramString2 }, new boolean[0], new byte[0], new char[0], new short[0], new int[0], new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static void clearInstantAppCookie(Object paramObject)
  {
    JniStubHelper.a(new Object[0], new boolean[0], new byte[0], new char[0], new short[0], new int[0], new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static void clearPackagePreferredActivities(Object paramObject, String paramString)
  {
    JniStubHelper.a(new Object[] { paramString }, new boolean[0], new byte[0], new char[0], new short[0], new int[0], new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static String[] currentToCanonicalPackageNames(Object paramObject, String[] paramArrayOfString)
  {
    return (String[])JniStubHelper.b(new Object[] { paramArrayOfString }, new boolean[0], new byte[0], new char[0], new short[0], new int[0], new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static void extendVerificationTimeout(Object paramObject, int paramInt1, int paramInt2, long paramLong)
  {
    JniStubHelper.a(new Object[0], new boolean[0], new byte[0], new char[0], new short[0], new int[] { paramInt1, paramInt2 }, new long[] { paramLong }, new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static Drawable getActivityBanner(Object paramObject, ComponentName paramComponentName)
    throws PackageManager.NameNotFoundException
  {
    return (Drawable)JniStubHelper.b(new Object[] { paramComponentName }, new boolean[0], new byte[0], new char[0], new short[0], new int[0], new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static Drawable getActivityBanner(Object paramObject, Intent paramIntent)
    throws PackageManager.NameNotFoundException
  {
    return (Drawable)JniStubHelper.b(new Object[] { paramIntent }, new boolean[0], new byte[0], new char[0], new short[0], new int[0], new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static Drawable getActivityIcon(Object paramObject, ComponentName paramComponentName)
    throws PackageManager.NameNotFoundException
  {
    return (Drawable)JniStubHelper.b(new Object[] { paramComponentName }, new boolean[0], new byte[0], new char[0], new short[0], new int[0], new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static Drawable getActivityIcon(Object paramObject, Intent paramIntent)
    throws PackageManager.NameNotFoundException
  {
    return (Drawable)JniStubHelper.b(new Object[] { paramIntent }, new boolean[0], new byte[0], new char[0], new short[0], new int[0], new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static ActivityInfo getActivityInfo(Object paramObject, ComponentName paramComponentName, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return (ActivityInfo)JniStubHelper.b(new Object[] { paramComponentName }, new boolean[0], new byte[0], new char[0], new short[0], new int[] { paramInt }, new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static Drawable getActivityLogo(Object paramObject, ComponentName paramComponentName)
    throws PackageManager.NameNotFoundException
  {
    return (Drawable)JniStubHelper.b(new Object[] { paramComponentName }, new boolean[0], new byte[0], new char[0], new short[0], new int[0], new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static Drawable getActivityLogo(Object paramObject, Intent paramIntent)
    throws PackageManager.NameNotFoundException
  {
    return (Drawable)JniStubHelper.b(new Object[] { paramIntent }, new boolean[0], new byte[0], new char[0], new short[0], new int[0], new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static List<PermissionGroupInfo> getAllPermissionGroups(Object paramObject, int paramInt)
  {
    return (List)JniStubHelper.b(new Object[0], new boolean[0], new byte[0], new char[0], new short[0], new int[] { paramInt }, new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static Drawable getApplicationBanner(Object paramObject, ApplicationInfo paramApplicationInfo)
  {
    return (Drawable)JniStubHelper.b(new Object[] { paramApplicationInfo }, new boolean[0], new byte[0], new char[0], new short[0], new int[0], new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static Drawable getApplicationBanner(Object paramObject, String paramString)
    throws PackageManager.NameNotFoundException
  {
    return (Drawable)JniStubHelper.b(new Object[] { paramString }, new boolean[0], new byte[0], new char[0], new short[0], new int[0], new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static int getApplicationEnabledSetting(Object paramObject, String paramString)
  {
    return JniStubHelper.g(new Object[] { paramString }, new boolean[0], new byte[0], new char[0], new short[0], new int[0], new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static Drawable getApplicationIcon(Object paramObject, ApplicationInfo paramApplicationInfo)
  {
    return (Drawable)JniStubHelper.b(new Object[] { paramApplicationInfo }, new boolean[0], new byte[0], new char[0], new short[0], new int[0], new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static Drawable getApplicationIcon(Object paramObject, String paramString)
    throws PackageManager.NameNotFoundException
  {
    return (Drawable)JniStubHelper.b(new Object[] { paramString }, new boolean[0], new byte[0], new char[0], new short[0], new int[0], new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static ApplicationInfo getApplicationInfo(Object paramObject, String paramString, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return (ApplicationInfo)JniStubHelper.b(new Object[] { paramString }, new boolean[0], new byte[0], new char[0], new short[0], new int[] { paramInt }, new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static CharSequence getApplicationLabel(Object paramObject, ApplicationInfo paramApplicationInfo)
  {
    return (CharSequence)JniStubHelper.b(new Object[] { paramApplicationInfo }, new boolean[0], new byte[0], new char[0], new short[0], new int[0], new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static Drawable getApplicationLogo(Object paramObject, ApplicationInfo paramApplicationInfo)
  {
    return (Drawable)JniStubHelper.b(new Object[] { paramApplicationInfo }, new boolean[0], new byte[0], new char[0], new short[0], new int[0], new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static Drawable getApplicationLogo(Object paramObject, String paramString)
    throws PackageManager.NameNotFoundException
  {
    return (Drawable)JniStubHelper.b(new Object[] { paramString }, new boolean[0], new byte[0], new char[0], new short[0], new int[0], new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static ChangedPackages getChangedPackages(Object paramObject, int paramInt)
  {
    return (ChangedPackages)JniStubHelper.b(new Object[0], new boolean[0], new byte[0], new char[0], new short[0], new int[] { paramInt }, new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static int getComponentEnabledSetting(Object paramObject, ComponentName paramComponentName)
  {
    return JniStubHelper.g(new Object[] { paramComponentName }, new boolean[0], new byte[0], new char[0], new short[0], new int[0], new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static Drawable getDefaultActivityIcon(Object paramObject)
  {
    return (Drawable)JniStubHelper.b(new Object[0], new boolean[0], new byte[0], new char[0], new short[0], new int[0], new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static Drawable getDrawable(Object paramObject, String paramString, int paramInt, ApplicationInfo paramApplicationInfo)
  {
    return (Drawable)JniStubHelper.b(new Object[] { paramString, paramApplicationInfo }, new boolean[0], new byte[0], new char[0], new short[0], new int[] { paramInt }, new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static List<ApplicationInfo> getInstalledApplications(Object paramObject, int paramInt)
  {
    return (List)JniStubHelper.b(new Object[0], new boolean[0], new byte[0], new char[0], new short[0], new int[] { paramInt }, new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static List<PackageInfo> getInstalledPackages(Object paramObject, int paramInt)
  {
    return (List)JniStubHelper.b(new Object[0], new boolean[0], new byte[0], new char[0], new short[0], new int[] { paramInt }, new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static String getInstallerPackageName(Object paramObject, String paramString)
  {
    return (String)JniStubHelper.b(new Object[] { paramString }, new boolean[0], new byte[0], new char[0], new short[0], new int[0], new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static byte[] getInstantAppCookie(Object paramObject)
  {
    return (byte[])JniStubHelper.b(new Object[0], new boolean[0], new byte[0], new char[0], new short[0], new int[0], new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static int getInstantAppCookieMaxBytes(Object paramObject)
  {
    return JniStubHelper.g(new Object[0], new boolean[0], new byte[0], new char[0], new short[0], new int[0], new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static InstrumentationInfo getInstrumentationInfo(Object paramObject, ComponentName paramComponentName, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return (InstrumentationInfo)JniStubHelper.b(new Object[] { paramComponentName }, new boolean[0], new byte[0], new char[0], new short[0], new int[] { paramInt }, new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static Intent getLaunchIntentForPackage(Object paramObject, String paramString)
  {
    return (Intent)JniStubHelper.b(new Object[] { paramString }, new boolean[0], new byte[0], new char[0], new short[0], new int[0], new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static Intent getLeanbackLaunchIntentForPackage(Object paramObject, String paramString)
  {
    return (Intent)JniStubHelper.b(new Object[] { paramString }, new boolean[0], new byte[0], new char[0], new short[0], new int[0], new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static String getNameForUid(Object paramObject, int paramInt)
  {
    return (String)JniStubHelper.b(new Object[0], new boolean[0], new byte[0], new char[0], new short[0], new int[] { paramInt }, new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static PackageInfo getPackageArchiveInfo(Object paramObject, String paramString, int paramInt)
  {
    return (PackageInfo)JniStubHelper.b(new Object[] { paramString }, new boolean[0], new byte[0], new char[0], new short[0], new int[] { paramInt }, new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static int[] getPackageGids(Object paramObject, String paramString)
    throws PackageManager.NameNotFoundException
  {
    return (int[])JniStubHelper.b(new Object[] { paramString }, new boolean[0], new byte[0], new char[0], new short[0], new int[0], new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static int[] getPackageGids(Object paramObject, String paramString, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return (int[])JniStubHelper.b(new Object[] { paramString }, new boolean[0], new byte[0], new char[0], new short[0], new int[] { paramInt }, new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static PackageInfo getPackageInfo(Object paramObject, VersionedPackage paramVersionedPackage, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return (PackageInfo)JniStubHelper.b(new Object[] { paramVersionedPackage }, new boolean[0], new byte[0], new char[0], new short[0], new int[] { paramInt }, new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static PackageInfo getPackageInfo(Object paramObject, String paramString, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return (PackageInfo)JniStubHelper.b(new Object[] { paramString }, new boolean[0], new byte[0], new char[0], new short[0], new int[] { paramInt }, new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static PackageInstaller getPackageInstaller(Object paramObject)
  {
    return (PackageInstaller)JniStubHelper.b(new Object[0], new boolean[0], new byte[0], new char[0], new short[0], new int[0], new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static int getPackageUid(Object paramObject, String paramString, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return JniStubHelper.g(new Object[] { paramString }, new boolean[0], new byte[0], new char[0], new short[0], new int[] { paramInt }, new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static String[] getPackagesForUid(Object paramObject, int paramInt)
  {
    return (String[])JniStubHelper.b(new Object[0], new boolean[0], new byte[0], new char[0], new short[0], new int[] { paramInt }, new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static List<PackageInfo> getPackagesHoldingPermissions(Object paramObject, String[] paramArrayOfString, int paramInt)
  {
    return (List)JniStubHelper.b(new Object[] { paramArrayOfString }, new boolean[0], new byte[0], new char[0], new short[0], new int[] { paramInt }, new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static PermissionGroupInfo getPermissionGroupInfo(Object paramObject, String paramString, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return (PermissionGroupInfo)JniStubHelper.b(new Object[] { paramString }, new boolean[0], new byte[0], new char[0], new short[0], new int[] { paramInt }, new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static PermissionInfo getPermissionInfo(Object paramObject, String paramString, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return (PermissionInfo)JniStubHelper.b(new Object[] { paramString }, new boolean[0], new byte[0], new char[0], new short[0], new int[] { paramInt }, new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static int getPreferredActivities(Object paramObject, List<IntentFilter> paramList, List<ComponentName> paramList1, String paramString)
  {
    return JniStubHelper.g(new Object[] { paramList, paramList1, paramString }, new boolean[0], new byte[0], new char[0], new short[0], new int[0], new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static List<PackageInfo> getPreferredPackages(Object paramObject, int paramInt)
  {
    return (List)JniStubHelper.b(new Object[0], new boolean[0], new byte[0], new char[0], new short[0], new int[] { paramInt }, new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static ProviderInfo getProviderInfo(Object paramObject, ComponentName paramComponentName, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return (ProviderInfo)JniStubHelper.b(new Object[] { paramComponentName }, new boolean[0], new byte[0], new char[0], new short[0], new int[] { paramInt }, new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static ActivityInfo getReceiverInfo(Object paramObject, ComponentName paramComponentName, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return (ActivityInfo)JniStubHelper.b(new Object[] { paramComponentName }, new boolean[0], new byte[0], new char[0], new short[0], new int[] { paramInt }, new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static Resources getResourcesForActivity(Object paramObject, ComponentName paramComponentName)
    throws PackageManager.NameNotFoundException
  {
    return (Resources)JniStubHelper.b(new Object[] { paramComponentName }, new boolean[0], new byte[0], new char[0], new short[0], new int[0], new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static Resources getResourcesForApplication(Object paramObject, ApplicationInfo paramApplicationInfo)
    throws PackageManager.NameNotFoundException
  {
    return (Resources)JniStubHelper.b(new Object[] { paramApplicationInfo }, new boolean[0], new byte[0], new char[0], new short[0], new int[0], new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static Resources getResourcesForApplication(Object paramObject, String paramString)
    throws PackageManager.NameNotFoundException
  {
    return (Resources)JniStubHelper.b(new Object[] { paramString }, new boolean[0], new byte[0], new char[0], new short[0], new int[0], new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static ServiceInfo getServiceInfo(Object paramObject, ComponentName paramComponentName, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return (ServiceInfo)JniStubHelper.b(new Object[] { paramComponentName }, new boolean[0], new byte[0], new char[0], new short[0], new int[] { paramInt }, new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static List<SharedLibraryInfo> getSharedLibraries(Object paramObject, int paramInt)
  {
    return (List)JniStubHelper.b(new Object[0], new boolean[0], new byte[0], new char[0], new short[0], new int[] { paramInt }, new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static FeatureInfo[] getSystemAvailableFeatures(Object paramObject)
  {
    return (FeatureInfo[])JniStubHelper.b(new Object[0], new boolean[0], new byte[0], new char[0], new short[0], new int[0], new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static String[] getSystemSharedLibraryNames(Object paramObject)
  {
    return (String[])JniStubHelper.b(new Object[0], new boolean[0], new byte[0], new char[0], new short[0], new int[0], new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static CharSequence getText(Object paramObject, String paramString, int paramInt, ApplicationInfo paramApplicationInfo)
  {
    return (CharSequence)JniStubHelper.b(new Object[] { paramString, paramApplicationInfo }, new boolean[0], new byte[0], new char[0], new short[0], new int[] { paramInt }, new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static Drawable getUserBadgedDrawableForDensity(Object paramObject, Drawable paramDrawable, UserHandle paramUserHandle, Rect paramRect, int paramInt)
  {
    return (Drawable)JniStubHelper.b(new Object[] { paramDrawable, paramUserHandle, paramRect }, new boolean[0], new byte[0], new char[0], new short[0], new int[] { paramInt }, new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static Drawable getUserBadgedIcon(Object paramObject, Drawable paramDrawable, UserHandle paramUserHandle)
  {
    return (Drawable)JniStubHelper.b(new Object[] { paramDrawable, paramUserHandle }, new boolean[0], new byte[0], new char[0], new short[0], new int[0], new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static CharSequence getUserBadgedLabel(Object paramObject, CharSequence paramCharSequence, UserHandle paramUserHandle)
  {
    return (CharSequence)JniStubHelper.b(new Object[] { paramCharSequence, paramUserHandle }, new boolean[0], new byte[0], new char[0], new short[0], new int[0], new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static XmlResourceParser getXml(Object paramObject, String paramString, int paramInt, ApplicationInfo paramApplicationInfo)
  {
    return (XmlResourceParser)JniStubHelper.b(new Object[] { paramString, paramApplicationInfo }, new boolean[0], new byte[0], new char[0], new short[0], new int[] { paramInt }, new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static boolean hasSystemFeature(Object paramObject, String paramString)
  {
    JoinPoint localJoinPoint = Factory.makeJP(ajc$tjp_0, null, null, paramObject, paramString);
    return Conversions.booleanValue(hasSystemFeature_aroundBody1$advice(paramObject, paramString, localJoinPoint, com.citrix.APIContainment.a.c.a(), paramString, null, localJoinPoint));
  }
  
  @InterceptMethod
  public static boolean hasSystemFeature(Object paramObject, String paramString, int paramInt)
  {
    return JniStubHelper.c(new Object[] { paramString }, new boolean[0], new byte[0], new char[0], new short[0], new int[] { paramInt }, new long[0], new float[0], new double[0]);
  }
  
  private static final boolean hasSystemFeature_aroundBody0(Object paramObject, String paramString, JoinPoint paramJoinPoint)
  {
    return JniStubHelper.c(new Object[] { paramString }, new boolean[0], new byte[0], new char[0], new short[0], new int[0], new long[0], new float[0], new double[0]);
  }
  
  private static final Object hasSystemFeature_aroundBody1$advice(Object paramObject, String paramString1, JoinPoint paramJoinPoint1, com.citrix.APIContainment.a.c paramC, String paramString2, AroundClosure paramAroundClosure, JoinPoint paramJoinPoint2)
  {
    paramString1 = null;
    e.d("MDX-HALAspect", "hasSystemFeature");
    if (com.citrix.APIContainment.a.c.b() != null) {
      paramString1 = com.citrix.APIContainment.a.c.b().a(null, paramJoinPoint2.getArgs());
    }
    if ((paramString1 == null) || (paramString1.a)) {
      return Conversions.booleanObject(hasSystemFeature_aroundBody0(paramObject, paramString2, paramJoinPoint1));
    }
    return Boolean.valueOf(paramString1.a);
  }
  
  @InterceptMethod
  public static boolean isInstantApp(Object paramObject)
  {
    return JniStubHelper.c(new Object[0], new boolean[0], new byte[0], new char[0], new short[0], new int[0], new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static boolean isInstantApp(Object paramObject, String paramString)
  {
    return JniStubHelper.c(new Object[] { paramString }, new boolean[0], new byte[0], new char[0], new short[0], new int[0], new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static boolean isPermissionRevokedByPolicy(Object paramObject, String paramString1, String paramString2)
  {
    return JniStubHelper.c(new Object[] { paramString1, paramString2 }, new boolean[0], new byte[0], new char[0], new short[0], new int[0], new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static boolean isSafeMode(Object paramObject)
  {
    return JniStubHelper.c(new Object[0], new boolean[0], new byte[0], new char[0], new short[0], new int[0], new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static List<ResolveInfo> queryBroadcastReceivers(Object paramObject, Intent paramIntent, int paramInt)
  {
    return (List)JniStubHelper.b(new Object[] { paramIntent }, new boolean[0], new byte[0], new char[0], new short[0], new int[] { paramInt }, new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static List<ProviderInfo> queryContentProviders(Object paramObject, String paramString, int paramInt1, int paramInt2)
  {
    return (List)JniStubHelper.b(new Object[] { paramString }, new boolean[0], new byte[0], new char[0], new short[0], new int[] { paramInt1, paramInt2 }, new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static List<InstrumentationInfo> queryInstrumentation(Object paramObject, String paramString, int paramInt)
  {
    return (List)JniStubHelper.b(new Object[] { paramString }, new boolean[0], new byte[0], new char[0], new short[0], new int[] { paramInt }, new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static List<ResolveInfo> queryIntentActivities(Object paramObject, Intent paramIntent, int paramInt)
  {
    return (List)queryIntentActivities_aroundBody3$advice(paramObject, paramIntent, paramInt, d.a(), paramIntent, paramInt, null);
  }
  
  private static final List queryIntentActivities_aroundBody2(Object paramObject, Intent paramIntent, int paramInt)
  {
    return (List)JniStubHelper.b(new Object[] { paramIntent }, new boolean[0], new byte[0], new char[0], new short[0], new int[] { paramInt }, new long[0], new float[0], new double[0]);
  }
  
  /* Error */
  private static final Object queryIntentActivities_aroundBody3$advice(Object paramObject, Intent paramIntent1, int paramInt1, d paramD, Intent paramIntent2, int paramInt2, AroundClosure paramAroundClosure)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: getstatic 688	com/citrix/APIContainment/a/d:b	Lorg/aspectj/runtime/internal/CFlowCounter;
    //   5: invokevirtual 693	org/aspectj/runtime/internal/CFlowCounter:inc	()V
    //   8: getstatic 688	com/citrix/APIContainment/a/d:b	Lorg/aspectj/runtime/internal/CFlowCounter;
    //   11: invokevirtual 693	org/aspectj/runtime/internal/CFlowCounter:inc	()V
    //   14: invokestatic 696	com/citrix/APIContainment/a/d:b	()Lcom/citrix/mdx/e/d;
    //   17: astore_3
    //   18: getstatic 688	com/citrix/APIContainment/a/d:b	Lorg/aspectj/runtime/internal/CFlowCounter;
    //   21: invokevirtual 699	org/aspectj/runtime/internal/CFlowCounter:dec	()V
    //   24: aload_3
    //   25: ifnull +46 -> 71
    //   28: getstatic 688	com/citrix/APIContainment/a/d:b	Lorg/aspectj/runtime/internal/CFlowCounter;
    //   31: invokevirtual 693	org/aspectj/runtime/internal/CFlowCounter:inc	()V
    //   34: invokestatic 696	com/citrix/APIContainment/a/d:b	()Lcom/citrix/mdx/e/d;
    //   37: astore_1
    //   38: getstatic 688	com/citrix/APIContainment/a/d:b	Lorg/aspectj/runtime/internal/CFlowCounter;
    //   41: invokevirtual 699	org/aspectj/runtime/internal/CFlowCounter:dec	()V
    //   44: getstatic 688	com/citrix/APIContainment/a/d:b	Lorg/aspectj/runtime/internal/CFlowCounter;
    //   47: invokevirtual 693	org/aspectj/runtime/internal/CFlowCounter:inc	()V
    //   50: aload_1
    //   51: aload 4
    //   53: iload 5
    //   55: aconst_null
    //   56: invokevirtual 704	com/citrix/mdx/e/d:a	(Ljava/lang/Object;ILjava/lang/Object;)Ljava/lang/Object;
    //   59: astore_1
    //   60: getstatic 688	com/citrix/APIContainment/a/d:b	Lorg/aspectj/runtime/internal/CFlowCounter;
    //   63: invokevirtual 699	org/aspectj/runtime/internal/CFlowCounter:dec	()V
    //   66: aload_1
    //   67: checkcast 644	com/citrix/mdx/e/b$a
    //   70: astore_1
    //   71: aload_1
    //   72: ifnull +26 -> 98
    //   75: getstatic 688	com/citrix/APIContainment/a/d:b	Lorg/aspectj/runtime/internal/CFlowCounter;
    //   78: invokevirtual 693	org/aspectj/runtime/internal/CFlowCounter:inc	()V
    //   81: aload_1
    //   82: getfield 647	com/citrix/mdx/e/b$a:a	Z
    //   85: istore 7
    //   87: getstatic 688	com/citrix/APIContainment/a/d:b	Lorg/aspectj/runtime/internal/CFlowCounter;
    //   90: invokevirtual 699	org/aspectj/runtime/internal/CFlowCounter:dec	()V
    //   93: iload 7
    //   95: ifeq +65 -> 160
    //   98: aload_0
    //   99: aload 4
    //   101: iload 5
    //   103: invokestatic 706	citrixJni/android/content/pm/PackageManager:queryIntentActivities_aroundBody2	(Ljava/lang/Object;Landroid/content/Intent;I)Ljava/util/List;
    //   106: astore_0
    //   107: getstatic 688	com/citrix/APIContainment/a/d:b	Lorg/aspectj/runtime/internal/CFlowCounter;
    //   110: invokevirtual 699	org/aspectj/runtime/internal/CFlowCounter:dec	()V
    //   113: aload_0
    //   114: areturn
    //   115: astore_0
    //   116: getstatic 688	com/citrix/APIContainment/a/d:b	Lorg/aspectj/runtime/internal/CFlowCounter;
    //   119: invokevirtual 699	org/aspectj/runtime/internal/CFlowCounter:dec	()V
    //   122: aload_0
    //   123: athrow
    //   124: astore_0
    //   125: getstatic 688	com/citrix/APIContainment/a/d:b	Lorg/aspectj/runtime/internal/CFlowCounter;
    //   128: invokevirtual 699	org/aspectj/runtime/internal/CFlowCounter:dec	()V
    //   131: aload_0
    //   132: athrow
    //   133: astore_0
    //   134: getstatic 688	com/citrix/APIContainment/a/d:b	Lorg/aspectj/runtime/internal/CFlowCounter;
    //   137: invokevirtual 699	org/aspectj/runtime/internal/CFlowCounter:dec	()V
    //   140: aload_0
    //   141: athrow
    //   142: astore_0
    //   143: getstatic 688	com/citrix/APIContainment/a/d:b	Lorg/aspectj/runtime/internal/CFlowCounter;
    //   146: invokevirtual 699	org/aspectj/runtime/internal/CFlowCounter:dec	()V
    //   149: aload_0
    //   150: athrow
    //   151: astore_0
    //   152: getstatic 688	com/citrix/APIContainment/a/d:b	Lorg/aspectj/runtime/internal/CFlowCounter;
    //   155: invokevirtual 699	org/aspectj/runtime/internal/CFlowCounter:dec	()V
    //   158: aload_0
    //   159: athrow
    //   160: getstatic 688	com/citrix/APIContainment/a/d:b	Lorg/aspectj/runtime/internal/CFlowCounter;
    //   163: invokevirtual 693	org/aspectj/runtime/internal/CFlowCounter:inc	()V
    //   166: aload_1
    //   167: getfield 709	com/citrix/mdx/e/b$a:c	Ljava/lang/Object;
    //   170: astore_0
    //   171: getstatic 688	com/citrix/APIContainment/a/d:b	Lorg/aspectj/runtime/internal/CFlowCounter;
    //   174: invokevirtual 699	org/aspectj/runtime/internal/CFlowCounter:dec	()V
    //   177: goto -70 -> 107
    //   180: astore_0
    //   181: getstatic 688	com/citrix/APIContainment/a/d:b	Lorg/aspectj/runtime/internal/CFlowCounter;
    //   184: invokevirtual 699	org/aspectj/runtime/internal/CFlowCounter:dec	()V
    //   187: aload_0
    //   188: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	189	0	paramObject	Object
    //   0	189	1	paramIntent1	Intent
    //   0	189	2	paramInt1	int
    //   0	189	3	paramD	d
    //   0	189	4	paramIntent2	Intent
    //   0	189	5	paramInt2	int
    //   0	189	6	paramAroundClosure	AroundClosure
    //   85	9	7	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   14	18	115	java/lang/Throwable
    //   8	14	124	java/lang/Throwable
    //   18	24	124	java/lang/Throwable
    //   28	34	124	java/lang/Throwable
    //   38	50	124	java/lang/Throwable
    //   60	71	124	java/lang/Throwable
    //   75	81	124	java/lang/Throwable
    //   87	93	124	java/lang/Throwable
    //   98	107	124	java/lang/Throwable
    //   116	124	124	java/lang/Throwable
    //   134	142	124	java/lang/Throwable
    //   143	151	124	java/lang/Throwable
    //   152	160	124	java/lang/Throwable
    //   160	166	124	java/lang/Throwable
    //   171	177	124	java/lang/Throwable
    //   181	189	124	java/lang/Throwable
    //   34	38	133	java/lang/Throwable
    //   50	60	142	java/lang/Throwable
    //   81	87	151	java/lang/Throwable
    //   166	171	180	java/lang/Throwable
  }
  
  @InterceptMethod
  public static List<ResolveInfo> queryIntentActivityOptions(Object paramObject, ComponentName paramComponentName, Intent[] paramArrayOfIntent, Intent paramIntent, int paramInt)
  {
    return (List)JniStubHelper.b(new Object[] { paramComponentName, paramArrayOfIntent, paramIntent }, new boolean[0], new byte[0], new char[0], new short[0], new int[] { paramInt }, new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static List<ResolveInfo> queryIntentContentProviders(Object paramObject, Intent paramIntent, int paramInt)
  {
    return (List)queryIntentContentProviders_aroundBody7$advice(paramObject, paramIntent, paramInt, d.a(), paramIntent, paramInt, null);
  }
  
  private static final List queryIntentContentProviders_aroundBody6(Object paramObject, Intent paramIntent, int paramInt)
  {
    return (List)JniStubHelper.b(new Object[] { paramIntent }, new boolean[0], new byte[0], new char[0], new short[0], new int[] { paramInt }, new long[0], new float[0], new double[0]);
  }
  
  /* Error */
  private static final Object queryIntentContentProviders_aroundBody7$advice(Object paramObject, Intent paramIntent1, int paramInt1, d paramD, Intent paramIntent2, int paramInt2, AroundClosure paramAroundClosure)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: getstatic 688	com/citrix/APIContainment/a/d:b	Lorg/aspectj/runtime/internal/CFlowCounter;
    //   5: invokevirtual 693	org/aspectj/runtime/internal/CFlowCounter:inc	()V
    //   8: getstatic 688	com/citrix/APIContainment/a/d:b	Lorg/aspectj/runtime/internal/CFlowCounter;
    //   11: invokevirtual 693	org/aspectj/runtime/internal/CFlowCounter:inc	()V
    //   14: invokestatic 696	com/citrix/APIContainment/a/d:b	()Lcom/citrix/mdx/e/d;
    //   17: astore_3
    //   18: getstatic 688	com/citrix/APIContainment/a/d:b	Lorg/aspectj/runtime/internal/CFlowCounter;
    //   21: invokevirtual 699	org/aspectj/runtime/internal/CFlowCounter:dec	()V
    //   24: aload_3
    //   25: ifnull +46 -> 71
    //   28: getstatic 688	com/citrix/APIContainment/a/d:b	Lorg/aspectj/runtime/internal/CFlowCounter;
    //   31: invokevirtual 693	org/aspectj/runtime/internal/CFlowCounter:inc	()V
    //   34: invokestatic 696	com/citrix/APIContainment/a/d:b	()Lcom/citrix/mdx/e/d;
    //   37: astore_1
    //   38: getstatic 688	com/citrix/APIContainment/a/d:b	Lorg/aspectj/runtime/internal/CFlowCounter;
    //   41: invokevirtual 699	org/aspectj/runtime/internal/CFlowCounter:dec	()V
    //   44: getstatic 688	com/citrix/APIContainment/a/d:b	Lorg/aspectj/runtime/internal/CFlowCounter;
    //   47: invokevirtual 693	org/aspectj/runtime/internal/CFlowCounter:inc	()V
    //   50: aload_1
    //   51: aload 4
    //   53: iload 5
    //   55: aconst_null
    //   56: invokevirtual 719	com/citrix/mdx/e/d:c	(Ljava/lang/Object;ILjava/lang/Object;)Ljava/lang/Object;
    //   59: astore_1
    //   60: getstatic 688	com/citrix/APIContainment/a/d:b	Lorg/aspectj/runtime/internal/CFlowCounter;
    //   63: invokevirtual 699	org/aspectj/runtime/internal/CFlowCounter:dec	()V
    //   66: aload_1
    //   67: checkcast 644	com/citrix/mdx/e/b$a
    //   70: astore_1
    //   71: aload_1
    //   72: ifnull +26 -> 98
    //   75: getstatic 688	com/citrix/APIContainment/a/d:b	Lorg/aspectj/runtime/internal/CFlowCounter;
    //   78: invokevirtual 693	org/aspectj/runtime/internal/CFlowCounter:inc	()V
    //   81: aload_1
    //   82: getfield 647	com/citrix/mdx/e/b$a:a	Z
    //   85: istore 7
    //   87: getstatic 688	com/citrix/APIContainment/a/d:b	Lorg/aspectj/runtime/internal/CFlowCounter;
    //   90: invokevirtual 699	org/aspectj/runtime/internal/CFlowCounter:dec	()V
    //   93: iload 7
    //   95: ifeq +65 -> 160
    //   98: aload_0
    //   99: aload 4
    //   101: iload 5
    //   103: invokestatic 721	citrixJni/android/content/pm/PackageManager:queryIntentContentProviders_aroundBody6	(Ljava/lang/Object;Landroid/content/Intent;I)Ljava/util/List;
    //   106: astore_0
    //   107: getstatic 688	com/citrix/APIContainment/a/d:b	Lorg/aspectj/runtime/internal/CFlowCounter;
    //   110: invokevirtual 699	org/aspectj/runtime/internal/CFlowCounter:dec	()V
    //   113: aload_0
    //   114: areturn
    //   115: astore_0
    //   116: getstatic 688	com/citrix/APIContainment/a/d:b	Lorg/aspectj/runtime/internal/CFlowCounter;
    //   119: invokevirtual 699	org/aspectj/runtime/internal/CFlowCounter:dec	()V
    //   122: aload_0
    //   123: athrow
    //   124: astore_0
    //   125: getstatic 688	com/citrix/APIContainment/a/d:b	Lorg/aspectj/runtime/internal/CFlowCounter;
    //   128: invokevirtual 699	org/aspectj/runtime/internal/CFlowCounter:dec	()V
    //   131: aload_0
    //   132: athrow
    //   133: astore_0
    //   134: getstatic 688	com/citrix/APIContainment/a/d:b	Lorg/aspectj/runtime/internal/CFlowCounter;
    //   137: invokevirtual 699	org/aspectj/runtime/internal/CFlowCounter:dec	()V
    //   140: aload_0
    //   141: athrow
    //   142: astore_0
    //   143: getstatic 688	com/citrix/APIContainment/a/d:b	Lorg/aspectj/runtime/internal/CFlowCounter;
    //   146: invokevirtual 699	org/aspectj/runtime/internal/CFlowCounter:dec	()V
    //   149: aload_0
    //   150: athrow
    //   151: astore_0
    //   152: getstatic 688	com/citrix/APIContainment/a/d:b	Lorg/aspectj/runtime/internal/CFlowCounter;
    //   155: invokevirtual 699	org/aspectj/runtime/internal/CFlowCounter:dec	()V
    //   158: aload_0
    //   159: athrow
    //   160: getstatic 688	com/citrix/APIContainment/a/d:b	Lorg/aspectj/runtime/internal/CFlowCounter;
    //   163: invokevirtual 693	org/aspectj/runtime/internal/CFlowCounter:inc	()V
    //   166: aload_1
    //   167: getfield 709	com/citrix/mdx/e/b$a:c	Ljava/lang/Object;
    //   170: astore_0
    //   171: getstatic 688	com/citrix/APIContainment/a/d:b	Lorg/aspectj/runtime/internal/CFlowCounter;
    //   174: invokevirtual 699	org/aspectj/runtime/internal/CFlowCounter:dec	()V
    //   177: goto -70 -> 107
    //   180: astore_0
    //   181: getstatic 688	com/citrix/APIContainment/a/d:b	Lorg/aspectj/runtime/internal/CFlowCounter;
    //   184: invokevirtual 699	org/aspectj/runtime/internal/CFlowCounter:dec	()V
    //   187: aload_0
    //   188: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	189	0	paramObject	Object
    //   0	189	1	paramIntent1	Intent
    //   0	189	2	paramInt1	int
    //   0	189	3	paramD	d
    //   0	189	4	paramIntent2	Intent
    //   0	189	5	paramInt2	int
    //   0	189	6	paramAroundClosure	AroundClosure
    //   85	9	7	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   14	18	115	java/lang/Throwable
    //   8	14	124	java/lang/Throwable
    //   18	24	124	java/lang/Throwable
    //   28	34	124	java/lang/Throwable
    //   38	50	124	java/lang/Throwable
    //   60	71	124	java/lang/Throwable
    //   75	81	124	java/lang/Throwable
    //   87	93	124	java/lang/Throwable
    //   98	107	124	java/lang/Throwable
    //   116	124	124	java/lang/Throwable
    //   134	142	124	java/lang/Throwable
    //   143	151	124	java/lang/Throwable
    //   152	160	124	java/lang/Throwable
    //   160	166	124	java/lang/Throwable
    //   171	177	124	java/lang/Throwable
    //   181	189	124	java/lang/Throwable
    //   34	38	133	java/lang/Throwable
    //   50	60	142	java/lang/Throwable
    //   81	87	151	java/lang/Throwable
    //   166	171	180	java/lang/Throwable
  }
  
  @InterceptMethod
  public static List<ResolveInfo> queryIntentServices(Object paramObject, Intent paramIntent, int paramInt)
  {
    return (List)queryIntentServices_aroundBody5$advice(paramObject, paramIntent, paramInt, d.a(), paramIntent, paramInt, null);
  }
  
  private static final List queryIntentServices_aroundBody4(Object paramObject, Intent paramIntent, int paramInt)
  {
    return (List)JniStubHelper.b(new Object[] { paramIntent }, new boolean[0], new byte[0], new char[0], new short[0], new int[] { paramInt }, new long[0], new float[0], new double[0]);
  }
  
  /* Error */
  private static final Object queryIntentServices_aroundBody5$advice(Object paramObject, Intent paramIntent1, int paramInt1, d paramD, Intent paramIntent2, int paramInt2, AroundClosure paramAroundClosure)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: getstatic 688	com/citrix/APIContainment/a/d:b	Lorg/aspectj/runtime/internal/CFlowCounter;
    //   5: invokevirtual 693	org/aspectj/runtime/internal/CFlowCounter:inc	()V
    //   8: getstatic 688	com/citrix/APIContainment/a/d:b	Lorg/aspectj/runtime/internal/CFlowCounter;
    //   11: invokevirtual 693	org/aspectj/runtime/internal/CFlowCounter:inc	()V
    //   14: invokestatic 696	com/citrix/APIContainment/a/d:b	()Lcom/citrix/mdx/e/d;
    //   17: astore_3
    //   18: getstatic 688	com/citrix/APIContainment/a/d:b	Lorg/aspectj/runtime/internal/CFlowCounter;
    //   21: invokevirtual 699	org/aspectj/runtime/internal/CFlowCounter:dec	()V
    //   24: aload_3
    //   25: ifnull +46 -> 71
    //   28: getstatic 688	com/citrix/APIContainment/a/d:b	Lorg/aspectj/runtime/internal/CFlowCounter;
    //   31: invokevirtual 693	org/aspectj/runtime/internal/CFlowCounter:inc	()V
    //   34: invokestatic 696	com/citrix/APIContainment/a/d:b	()Lcom/citrix/mdx/e/d;
    //   37: astore_1
    //   38: getstatic 688	com/citrix/APIContainment/a/d:b	Lorg/aspectj/runtime/internal/CFlowCounter;
    //   41: invokevirtual 699	org/aspectj/runtime/internal/CFlowCounter:dec	()V
    //   44: getstatic 688	com/citrix/APIContainment/a/d:b	Lorg/aspectj/runtime/internal/CFlowCounter;
    //   47: invokevirtual 693	org/aspectj/runtime/internal/CFlowCounter:inc	()V
    //   50: aload_1
    //   51: aload 4
    //   53: iload 5
    //   55: aconst_null
    //   56: invokevirtual 728	com/citrix/mdx/e/d:b	(Ljava/lang/Object;ILjava/lang/Object;)Ljava/lang/Object;
    //   59: astore_1
    //   60: getstatic 688	com/citrix/APIContainment/a/d:b	Lorg/aspectj/runtime/internal/CFlowCounter;
    //   63: invokevirtual 699	org/aspectj/runtime/internal/CFlowCounter:dec	()V
    //   66: aload_1
    //   67: checkcast 644	com/citrix/mdx/e/b$a
    //   70: astore_1
    //   71: aload_1
    //   72: ifnull +26 -> 98
    //   75: getstatic 688	com/citrix/APIContainment/a/d:b	Lorg/aspectj/runtime/internal/CFlowCounter;
    //   78: invokevirtual 693	org/aspectj/runtime/internal/CFlowCounter:inc	()V
    //   81: aload_1
    //   82: getfield 647	com/citrix/mdx/e/b$a:a	Z
    //   85: istore 7
    //   87: getstatic 688	com/citrix/APIContainment/a/d:b	Lorg/aspectj/runtime/internal/CFlowCounter;
    //   90: invokevirtual 699	org/aspectj/runtime/internal/CFlowCounter:dec	()V
    //   93: iload 7
    //   95: ifeq +65 -> 160
    //   98: aload_0
    //   99: aload 4
    //   101: iload 5
    //   103: invokestatic 730	citrixJni/android/content/pm/PackageManager:queryIntentServices_aroundBody4	(Ljava/lang/Object;Landroid/content/Intent;I)Ljava/util/List;
    //   106: astore_0
    //   107: getstatic 688	com/citrix/APIContainment/a/d:b	Lorg/aspectj/runtime/internal/CFlowCounter;
    //   110: invokevirtual 699	org/aspectj/runtime/internal/CFlowCounter:dec	()V
    //   113: aload_0
    //   114: areturn
    //   115: astore_0
    //   116: getstatic 688	com/citrix/APIContainment/a/d:b	Lorg/aspectj/runtime/internal/CFlowCounter;
    //   119: invokevirtual 699	org/aspectj/runtime/internal/CFlowCounter:dec	()V
    //   122: aload_0
    //   123: athrow
    //   124: astore_0
    //   125: getstatic 688	com/citrix/APIContainment/a/d:b	Lorg/aspectj/runtime/internal/CFlowCounter;
    //   128: invokevirtual 699	org/aspectj/runtime/internal/CFlowCounter:dec	()V
    //   131: aload_0
    //   132: athrow
    //   133: astore_0
    //   134: getstatic 688	com/citrix/APIContainment/a/d:b	Lorg/aspectj/runtime/internal/CFlowCounter;
    //   137: invokevirtual 699	org/aspectj/runtime/internal/CFlowCounter:dec	()V
    //   140: aload_0
    //   141: athrow
    //   142: astore_0
    //   143: getstatic 688	com/citrix/APIContainment/a/d:b	Lorg/aspectj/runtime/internal/CFlowCounter;
    //   146: invokevirtual 699	org/aspectj/runtime/internal/CFlowCounter:dec	()V
    //   149: aload_0
    //   150: athrow
    //   151: astore_0
    //   152: getstatic 688	com/citrix/APIContainment/a/d:b	Lorg/aspectj/runtime/internal/CFlowCounter;
    //   155: invokevirtual 699	org/aspectj/runtime/internal/CFlowCounter:dec	()V
    //   158: aload_0
    //   159: athrow
    //   160: getstatic 688	com/citrix/APIContainment/a/d:b	Lorg/aspectj/runtime/internal/CFlowCounter;
    //   163: invokevirtual 693	org/aspectj/runtime/internal/CFlowCounter:inc	()V
    //   166: aload_1
    //   167: getfield 709	com/citrix/mdx/e/b$a:c	Ljava/lang/Object;
    //   170: astore_0
    //   171: getstatic 688	com/citrix/APIContainment/a/d:b	Lorg/aspectj/runtime/internal/CFlowCounter;
    //   174: invokevirtual 699	org/aspectj/runtime/internal/CFlowCounter:dec	()V
    //   177: goto -70 -> 107
    //   180: astore_0
    //   181: getstatic 688	com/citrix/APIContainment/a/d:b	Lorg/aspectj/runtime/internal/CFlowCounter;
    //   184: invokevirtual 699	org/aspectj/runtime/internal/CFlowCounter:dec	()V
    //   187: aload_0
    //   188: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	189	0	paramObject	Object
    //   0	189	1	paramIntent1	Intent
    //   0	189	2	paramInt1	int
    //   0	189	3	paramD	d
    //   0	189	4	paramIntent2	Intent
    //   0	189	5	paramInt2	int
    //   0	189	6	paramAroundClosure	AroundClosure
    //   85	9	7	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   14	18	115	java/lang/Throwable
    //   8	14	124	java/lang/Throwable
    //   18	24	124	java/lang/Throwable
    //   28	34	124	java/lang/Throwable
    //   38	50	124	java/lang/Throwable
    //   60	71	124	java/lang/Throwable
    //   75	81	124	java/lang/Throwable
    //   87	93	124	java/lang/Throwable
    //   98	107	124	java/lang/Throwable
    //   116	124	124	java/lang/Throwable
    //   134	142	124	java/lang/Throwable
    //   143	151	124	java/lang/Throwable
    //   152	160	124	java/lang/Throwable
    //   160	166	124	java/lang/Throwable
    //   171	177	124	java/lang/Throwable
    //   181	189	124	java/lang/Throwable
    //   34	38	133	java/lang/Throwable
    //   50	60	142	java/lang/Throwable
    //   81	87	151	java/lang/Throwable
    //   166	171	180	java/lang/Throwable
  }
  
  @InterceptMethod
  public static List<PermissionInfo> queryPermissionsByGroup(Object paramObject, String paramString, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return (List)JniStubHelper.b(new Object[] { paramString }, new boolean[0], new byte[0], new char[0], new short[0], new int[] { paramInt }, new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static void removePackageFromPreferred(Object paramObject, String paramString)
  {
    JniStubHelper.a(new Object[] { paramString }, new boolean[0], new byte[0], new char[0], new short[0], new int[0], new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static void removePermission(Object paramObject, String paramString)
  {
    JniStubHelper.a(new Object[] { paramString }, new boolean[0], new byte[0], new char[0], new short[0], new int[0], new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static ResolveInfo resolveActivity(Object paramObject, Intent paramIntent, int paramInt)
  {
    return (ResolveInfo)JniStubHelper.b(new Object[] { paramIntent }, new boolean[0], new byte[0], new char[0], new short[0], new int[] { paramInt }, new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static ProviderInfo resolveContentProvider(Object paramObject, String paramString, int paramInt)
  {
    return (ProviderInfo)JniStubHelper.b(new Object[] { paramString }, new boolean[0], new byte[0], new char[0], new short[0], new int[] { paramInt }, new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static ResolveInfo resolveService(Object paramObject, Intent paramIntent, int paramInt)
  {
    return (ResolveInfo)JniStubHelper.b(new Object[] { paramIntent }, new boolean[0], new byte[0], new char[0], new short[0], new int[] { paramInt }, new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static void setApplicationCategoryHint(Object paramObject, String paramString, int paramInt)
  {
    JniStubHelper.a(new Object[] { paramString }, new boolean[0], new byte[0], new char[0], new short[0], new int[] { paramInt }, new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static void setApplicationEnabledSetting(Object paramObject, String paramString, int paramInt1, int paramInt2)
  {
    JniStubHelper.a(new Object[] { paramString }, new boolean[0], new byte[0], new char[0], new short[0], new int[] { paramInt1, paramInt2 }, new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static void setComponentEnabledSetting(Object paramObject, ComponentName paramComponentName, int paramInt1, int paramInt2)
  {
    JniStubHelper.a(new Object[] { paramComponentName }, new boolean[0], new byte[0], new char[0], new short[0], new int[] { paramInt1, paramInt2 }, new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static void setInstallerPackageName(Object paramObject, String paramString1, String paramString2)
  {
    JniStubHelper.a(new Object[] { paramString1, paramString2 }, new boolean[0], new byte[0], new char[0], new short[0], new int[0], new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static void updateInstantAppCookie(Object paramObject, byte[] paramArrayOfByte)
  {
    JniStubHelper.a(new Object[] { paramArrayOfByte }, new boolean[0], new byte[0], new char[0], new short[0], new int[0], new long[0], new float[0], new double[0]);
  }
  
  @InterceptMethod
  public static void verifyPendingInstall(Object paramObject, int paramInt1, int paramInt2)
  {
    JniStubHelper.a(new Object[0], new boolean[0], new byte[0], new char[0], new short[0], new int[] { paramInt1, paramInt2 }, new long[0], new float[0], new double[0]);
  }
}
