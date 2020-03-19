package fishnoodle._engine30;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.location.Location;
import android.location.LocationManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Environment;
import android.provider.MediaStore.Audio.Media;
import android.text.TextUtils;
import android.view.Display;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class Utility
{
  static float a;
  static int b;
  static float c;
  static float d;
  static boolean e;
  private static final int f;
  
  static
  {
    int i = 0;
    a = 0.0F;
    b = 0;
    c = 1.0F;
    d = 0.0F;
    e = false;
    StackTraceElement[] arrayOfStackTraceElement = Thread.currentThread().getStackTrace();
    int m = arrayOfStackTraceElement.length;
    int j = 0;
    for (;;)
    {
      int k = i;
      if (j < m)
      {
        StackTraceElement localStackTraceElement = arrayOfStackTraceElement[j];
        i += 1;
        if (localStackTraceElement.getClassName().equals(Utility.class.getName())) {
          k = i;
        }
      }
      else
      {
        f = k;
        return;
      }
      j += 1;
    }
  }
  
  public Utility() {}
  
  static int a(float paramFloat)
  {
    if (paramFloat % 1.0F < 0.5F) {
      return (int)paramFloat;
    }
    return (int)(paramFloat + 0.5F);
  }
  
  public static void adjustScreenPosForDepth(Vector3 paramVector3, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6)
  {
    float f1 = paramFloat2 / paramFloat3;
    paramFloat2 = paramFloat4 / paramFloat2;
    paramFloat3 = paramFloat5 / paramFloat3;
    paramVector3.x = (f1 * paramFloat1 * 0.011111111F * ((paramFloat2 - 0.5F) * 2.0F * paramFloat6));
    paramVector3.y = paramFloat6;
    paramVector3.z = ((1.0F - paramFloat3 - 0.5F) * 2.0F * paramFloat6 * (paramFloat1 * 0.011111111F));
  }
  
  public static String baseFilenameFromPath(String paramString)
  {
    int i = paramString.lastIndexOf('.');
    String str = paramString;
    if (i > -1) {
      str = paramString.substring(0, i);
    }
    i = str.lastIndexOf('/');
    paramString = str;
    if (i > -1) {
      paramString = str.substring(i + 1);
    }
    return paramString;
  }
  
  public static double clamp(double paramDouble1, double paramDouble2, double paramDouble3)
  {
    if (paramDouble1 > paramDouble2) {}
    while (paramDouble1 < paramDouble3)
    {
      return paramDouble1;
      paramDouble1 = paramDouble2;
    }
    return paramDouble3;
  }
  
  public static float clamp(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    if (paramFloat1 > paramFloat2) {}
    while (paramFloat1 < paramFloat3)
    {
      return paramFloat1;
      paramFloat1 = paramFloat2;
    }
    return paramFloat3;
  }
  
  public static int clamp(int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramInt1 > paramInt2) {}
    while (paramInt1 < paramInt3)
    {
      return paramInt1;
      paramInt1 = paramInt2;
    }
    return paramInt3;
  }
  
  public static long clamp(long paramLong1, long paramLong2, long paramLong3)
  {
    if (paramLong1 > paramLong2) {}
    while (paramLong1 < paramLong3)
    {
      return paramLong1;
      paramLong1 = paramLong2;
    }
    return paramLong3;
  }
  
  public static final int closestPowerOfTwo(int paramInt)
  {
    int j = 0;
    int i = paramInt;
    paramInt = j;
    while (i > 1)
    {
      i /= 2;
      paramInt += 1;
    }
    return paramInt;
  }
  
  public static void combineModelViewProj(Matrix4 paramMatrix41, Matrix4 paramMatrix42, Matrix4 paramMatrix43, Matrix4 paramMatrix44)
  {
    paramMatrix41.set(paramMatrix43);
    Matrix4.matrixMultiply(paramMatrix41, paramMatrix41, paramMatrix42);
    Matrix4.matrixMultiply(paramMatrix41, paramMatrix44, paramMatrix41);
  }
  
  public static boolean compareTriangleOnAxis(Mesh.Data paramData, int paramInt1, int paramInt2, int paramInt3)
  {
    int j = paramInt2 * 3;
    int i = paramInt3 * 3;
    paramInt2 = paramData.indices[(j + 0)];
    paramInt3 = paramData.indices[(j + 1)];
    j = paramData.indices[(j + 2)];
    int k = paramData.indices[(i + 0)];
    int m = paramData.indices[(i + 1)];
    i = paramData.indices[(i + 2)];
    float f1 = paramData.positions[(paramInt2 * 3 + paramInt1)];
    float f2 = paramData.positions[(paramInt3 * 3 + paramInt1)];
    float f3 = paramData.positions[(j * 3 + paramInt1)];
    float f4 = paramData.positions[(k * 3 + paramInt1)];
    float f5 = paramData.positions[(m * 3 + paramInt1)];
    float f6 = paramData.positions[(i * 3 + paramInt1)];
    return (f3 + (f1 + f2)) / 3.0F > (f6 + (f4 + f5)) / 3.0F;
  }
  
  public static final short convertFloat32toFloat16(float paramFloat)
  {
    int i = 8388607;
    int m = Float.floatToRawIntBits(paramFloat);
    int j = m >> 31;
    int k = m & 0x7FFFFF;
    m &= 0x7F800000;
    if (m >= 1199570944)
    {
      if ((k != 0) && (m == 2139095040)) {}
      for (;;)
      {
        return (short)(i >> 13 | j << 15 | 0x7C00);
        i = 0;
      }
    }
    if (m <= 939524096) {
      return (short)(k >> (939524096 - m >> 23) + 14 | j << 15);
    }
    return (short)(j << 15 | m - 939524096 >> 13 | k >> 13);
  }
  
  public static void convertFloat32toFloat16(float[] paramArrayOfFloat, short[] paramArrayOfShort)
  {
    int j = paramArrayOfShort.length;
    int i = 0;
    while (i < j)
    {
      paramArrayOfShort[i] = convertFloat32toFloat16(paramArrayOfFloat[i]);
      i += 1;
    }
  }
  
  public static void createHomeScreenShortcut(Intent paramIntent, int paramInt1, int paramInt2)
  {
    Context localContext = AppContext.getContext();
    String str = AppContext.getResourceString(paramInt1);
    try
    {
      Intent localIntent = new Intent();
      localIntent.putExtra("android.intent.extra.shortcut.INTENT", paramIntent);
      localIntent.putExtra("android.intent.extra.shortcut.NAME", str);
      localIntent.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(localContext, paramInt2));
      localIntent.putExtra("duplicate", false);
      localIntent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
      localContext.sendBroadcast(localIntent);
      paramIntent = AppContext.getResourceString(R.string.creating_shortcuts_message);
      paramIntent = Toast.makeText(localContext, paramIntent + " " + str, 0);
      paramIntent.setGravity(17, paramIntent.getXOffset() / 2, 200);
      paramIntent.show();
      return;
    }
    catch (Exception paramIntent)
    {
      for (;;)
      {
        paramIntent = AppContext.getResourceString(R.string.creating_shortcuts_error);
        paramIntent = Toast.makeText(localContext, paramIntent + " " + str, 1);
        paramIntent.setGravity(17, paramIntent.getXOffset() / 2, 200);
        paramIntent.show();
      }
    }
  }
  
  public static float floatFromGraph(float paramFloat, float[] paramArrayOfFloat)
  {
    return floatFromGraph(paramFloat, paramArrayOfFloat, false);
  }
  
  public static float floatFromGraph(float paramFloat, float[] paramArrayOfFloat, boolean paramBoolean)
  {
    if (paramArrayOfFloat.length == 0) {
      return 0.0F;
    }
    if (paramArrayOfFloat.length < 2) {
      return paramArrayOfFloat[0];
    }
    int i;
    int k;
    int j;
    if (paramBoolean)
    {
      paramFloat = clamp(paramFloat, 0.0F, 1.0F);
      i = paramArrayOfFloat.length - 1;
      paramFloat *= i;
      int m = (int)paramFloat;
      k = (int)paramFloat + 1;
      j = m;
      if (m >= i) {
        j = i;
      }
      if (k < i) {
        break label106;
      }
      if (!paramBoolean) {
        break label101;
      }
    }
    for (;;)
    {
      paramFloat -= j;
      return paramArrayOfFloat[i] * paramFloat + (1.0F - paramFloat) * paramArrayOfFloat[j];
      paramFloat = wrap(paramFloat, 1.0F);
      break;
      label101:
      i = 0;
      continue;
      label106:
      i = k;
    }
  }
  
  public static float fovH2VDeg(double paramDouble1, double paramDouble2)
  {
    return (float)Math.toDegrees(Math.atan(Math.tan(Math.toRadians(paramDouble1 / 2.0D)) / paramDouble2)) * 2.0F;
  }
  
  public static void fpsTrack(String paramString, float paramFloat)
  {
    if (e) {}
    do
    {
      return;
      if (isDebuggable())
      {
        e = true;
        return;
      }
      a += paramFloat;
      b += 1;
      if (c > paramFloat) {
        c = paramFloat;
      }
      if (d < paramFloat) {
        d = paramFloat;
      }
    } while (b <= 100);
    paramFloat = a / b;
    SysLog.writeV(paramString + " FPS: " + 1.0F / paramFloat + "  Min: " + c + "  Max: " + d);
    b = 0;
    a = 0.0F;
    c = 1.0F;
    d = 0.0F;
  }
  
  public static double gaussianWeight(double paramDouble1, double paramDouble2)
  {
    paramDouble2 *= paramDouble2;
    paramDouble1 = -(paramDouble1 * paramDouble1 / (2.0D * paramDouble2));
    return 1.0D / Math.sqrt(paramDouble2 * 6.283185307179586D) * Math.pow(2.718281828459045D, paramDouble1);
  }
  
  public static void gaussianWeights5Tap(Vector3 paramVector3, double paramDouble)
  {
    double d1 = gaussianWeight(0.0D, paramDouble);
    double d2 = gaussianWeight(1.0D, paramDouble);
    paramDouble = gaussianWeight(2.0D, paramDouble);
    double d3 = paramDouble + d2 + d1 + d2 + paramDouble;
    paramVector3.x = ((float)(d1 / d3));
    paramVector3.y = ((float)(d2 / d3));
    paramVector3.z = ((float)(paramDouble / d3));
  }
  
  public static Intent getAlarmClockLaunchIntent(Context paramContext)
  {
    paramContext = paramContext.getPackageManager();
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.addCategory("android.intent.category.LAUNCHER");
    String[][] arrayOfString; = new String[5][];
    arrayOfString;[0] = { "HTC Alarm Clock", "com.htc.android.worldclock", "com.htc.android.worldclock.WorldClockTabControl" };
    arrayOfString;[1] = { "Standard Alarm Clock", "com.android.deskclock", "com.android.deskclock.AlarmClock" };
    arrayOfString;[2] = { "Froyo Nexus Alarm Clock", "com.google.android.deskclock", "com.android.deskclock.DeskClock" };
    arrayOfString;[3] = { "Moto Blur Alarm Clock", "com.motorola.blur.alarmclock", "com.motorola.blur.alarmclock.AlarmClock" };
    arrayOfString;[4] = { "Samsung Galaxy Clock", "com.sec.android.app.clockpackage", "com.sec.android.app.clockpackage.ClockPackage" };
    int j = 0;
    int i = 0;
    for (;;)
    {
      int k = i;
      Object localObject;
      String str;
      if (j < arrayOfString;.length)
      {
        localObject = arrayOfString;[j][0];
        localObject = arrayOfString;[j][1];
        str = arrayOfString;[j][2];
      }
      try
      {
        localObject = new ComponentName((String)localObject, str);
        paramContext.getActivityInfo((ComponentName)localObject, 128);
        localIntent.setComponent((ComponentName)localObject);
        i = 1;
      }
      catch (Exception localException)
      {
        for (;;) {}
      }
      if (i != 0)
      {
        k = i;
        if (k == 0) {
          break;
        }
        return localIntent;
      }
      j += 1;
    }
    return null;
  }
  
  public static boolean getBoolean(SharedPreferences paramSharedPreferences, String paramString1, String paramString2)
  {
    return paramSharedPreferences.getBoolean(paramString1, Boolean.parseBoolean(AppContext.getResourceString(paramString2)));
  }
  
  public static int getDisplayRotation(Display paramDisplay)
  {
    if (Build.VERSION.SDK_INT < 8) {
      return paramDisplay.getOrientation();
    }
    return paramDisplay.getRotation();
  }
  
  public static float getFloat(SharedPreferences paramSharedPreferences, String paramString1, String paramString2)
  {
    return Float.parseFloat(paramSharedPreferences.getString(paramString1, AppContext.getResourceString(paramString2)));
  }
  
  public static int getInt(SharedPreferences paramSharedPreferences, String paramString1, String paramString2)
  {
    return Integer.parseInt(paramSharedPreferences.getString(paramString1, AppContext.getResourceString(paramString2)));
  }
  
  public static double[] getLastGPS(Context paramContext)
  {
    Object localObject = (LocationManager)paramContext.getSystemService("location");
    List localList = ((LocationManager)localObject).getProviders(true);
    int i = localList.size();
    paramContext = null;
    i -= 1;
    if (i >= 0)
    {
      paramContext = ((LocationManager)localObject).getLastKnownLocation((String)localList.get(i));
      if (paramContext == null) {}
    }
    for (;;)
    {
      localObject = new double[2];
      if (paramContext != null)
      {
        localObject[0] = paramContext.getLatitude();
        localObject[1] = paramContext.getLongitude();
        return localObject;
        i -= 1;
        break;
      }
      SysLog.writeV("getLastGPS couldn't find cached positional data");
      return localObject;
    }
  }
  
  public static StackTraceElement[] getStackTrace()
  {
    StackTraceElement[] arrayOfStackTraceElement1 = Thread.currentThread().getStackTrace();
    StackTraceElement[] arrayOfStackTraceElement2 = new StackTraceElement[arrayOfStackTraceElement1.length - f];
    int i = 0;
    while (i < arrayOfStackTraceElement2.length)
    {
      arrayOfStackTraceElement2[i] = arrayOfStackTraceElement1[(f + i)];
      i += 1;
    }
    return arrayOfStackTraceElement2;
  }
  
  public static String getString(SharedPreferences paramSharedPreferences, String paramString1, String paramString2)
  {
    return paramSharedPreferences.getString(paramString1, AppContext.getResourceString(paramString2));
  }
  
  public static Bitmap glScreenshot(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i = paramInt3 * paramInt4;
    Object localObject1 = ByteBuffer.allocateDirect(4);
    ((ByteBuffer)localObject1).order(ByteOrder.nativeOrder());
    localObject1 = ((ByteBuffer)localObject1).asIntBuffer();
    GL20.gl.glGetIntegerv(3333, (IntBuffer)localObject1);
    ((IntBuffer)localObject1).position(0);
    GL20.gl.glPixelStorei(3333, 1);
    Object localObject2 = ByteBuffer.allocateDirect(i * 4);
    ((ByteBuffer)localObject2).order(ByteOrder.nativeOrder());
    ((ByteBuffer)localObject2).position(0);
    GL20.gl.glReadPixels(paramInt1, paramInt2, paramInt3, paramInt4, 6408, 5121, (Buffer)localObject2);
    ((ByteBuffer)localObject2).position(0);
    int[] arrayOfInt = new int[i];
    ((ByteBuffer)localObject2).asIntBuffer().get(arrayOfInt);
    paramInt1 = 0;
    while (paramInt1 < arrayOfInt.length)
    {
      arrayOfInt[paramInt1] = (arrayOfInt[paramInt1] & 0xFF00FF00 | (arrayOfInt[paramInt1] & 0xFF) << 16 | (arrayOfInt[paramInt1] & 0xFF0000) >> 16);
      paramInt1 += 1;
    }
    localObject2 = Bitmap.createBitmap(paramInt3, paramInt4, Bitmap.Config.ARGB_8888);
    ((Bitmap)localObject2).setPixels(arrayOfInt, i - paramInt3, -paramInt3, 0, 0, paramInt3, paramInt4);
    GL20.gl.glPixelStorei(3333, ((IntBuffer)localObject1).get());
    return localObject2;
  }
  
  public static int indexFromStringArray(String[] paramArrayOfString, String paramString)
  {
    int j;
    if (paramArrayOfString == null)
    {
      j = -1;
      return j;
    }
    int i = 0;
    for (;;)
    {
      if (i >= paramArrayOfString.length) {
        break label35;
      }
      j = i;
      if (paramArrayOfString[i].contentEquals(paramString)) {
        break;
      }
      i += 1;
    }
    label35:
    return -1;
  }
  
  public static boolean installAndSetRingtone(Context paramContext, String paramString, int paramInt1, int paramInt2, int paramInt3)
  {
    String str1 = paramContext.getResources().getString(paramInt2);
    String str2 = paramContext.getResources().getString(paramInt3);
    return installAndSetRingtone(paramContext, paramString, paramContext.getResources().openRawResource(paramInt1), str1, str2, paramContext.getString(R.string.ringtone_default_artist));
  }
  
  public static boolean installAndSetRingtone(Context paramContext, String paramString1, InputStream paramInputStream, String paramString2, String paramString3)
  {
    return installAndSetRingtone(paramContext, paramString1, paramInputStream, paramString2, paramString3, paramContext.getString(R.string.ringtone_default_artist));
  }
  
  public static boolean installAndSetRingtone(Context paramContext, String paramString1, InputStream paramInputStream, String paramString2, String paramString3, String paramString4)
  {
    return setRingtone(paramContext, installRingtone(paramContext, paramString1, paramInputStream, paramString2, paramString4), paramString3, paramContext.getString(R.string.ringtone_default_set_error_message));
  }
  
  public static Uri installRingtone(Context paramContext, String paramString, int paramInt1, int paramInt2)
  {
    String str = paramContext.getResources().getString(paramInt2);
    return installRingtone(paramContext, paramString, paramContext.getResources().openRawResource(paramInt1), str, paramContext.getString(R.string.ringtone_default_artist));
  }
  
  public static Uri installRingtone(Context paramContext, String paramString1, InputStream paramInputStream, String paramString2)
  {
    return installRingtone(paramContext, paramString1, paramInputStream, paramString2, paramContext.getString(R.string.ringtone_default_artist));
  }
  
  public static Uri installRingtone(Context paramContext, String paramString1, InputStream paramInputStream, String paramString2, String paramString3)
  {
    try
    {
      Object localObject = new byte[paramInputStream.available()];
      paramInputStream.read((byte[])localObject);
      paramInputStream.close();
      if (Build.VERSION.SDK_INT < 8) {}
      for (paramInputStream = Environment.getExternalStorageDirectory();; paramInputStream = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_RINGTONES.toLowerCase(Locale.ENGLISH)))
      {
        paramInputStream.mkdirs();
        paramString1 = new File(paramInputStream, paramString1);
        paramInputStream = new FileOutputStream(paramString1);
        paramInputStream.write((byte[])localObject);
        paramInputStream.close();
        paramInputStream = MediaStore.Audio.Media.getContentUriForPath(paramString1.getAbsolutePath());
        paramContext.getContentResolver().delete(paramInputStream, "_data=\"" + paramString1.getAbsolutePath() + "\"", null);
        localObject = new ContentValues();
        ((ContentValues)localObject).put("_data", paramString1.getAbsolutePath());
        ((ContentValues)localObject).put("title", paramString2);
        ((ContentValues)localObject).put("_size", Long.valueOf(paramString1.length()));
        ((ContentValues)localObject).put("artist", paramString3);
        ((ContentValues)localObject).put("is_ringtone", Boolean.valueOf(true));
        ((ContentValues)localObject).put("is_notification", Boolean.valueOf(true));
        ((ContentValues)localObject).put("is_alarm", Boolean.valueOf(true));
        ((ContentValues)localObject).put("is_music", Boolean.valueOf(false));
        return paramContext.getContentResolver().insert(paramInputStream, (ContentValues)localObject);
      }
      return null;
    }
    catch (IOException paramString1)
    {
      paramString1.printStackTrace();
      SysLog.writeD("FAILED! " + paramString1.getMessage());
      try
      {
        paramContext = Toast.makeText(paramContext, R.string.ringtone_default_set_error_message, 0);
        paramContext.setGravity(17, paramContext.getXOffset() / 2, 200);
        paramContext.show();
        return null;
      }
      catch (Exception paramContext) {}
    }
  }
  
  public static boolean isDebuggable()
  {
    return (AppContext.getContext().getApplicationInfo().flags & 0x2) != 0;
  }
  
  public static boolean isPackageInstalled(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(128).iterator();
    while (paramContext.hasNext()) {
      if (TextUtils.equals(((PackageInfo)paramContext.next()).packageName, paramString)) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean isStringEqual(String paramString1, String paramString2)
  {
    return ((paramString1 == null) && (paramString2 == null)) || ((paramString1 != null) && (paramString2 != null) && (paramString1.contentEquals(paramString2)));
  }
  
  public static boolean isStringNullOrEmpty(String paramString)
  {
    return (paramString == null) || (paramString.contentEquals(""));
  }
  
  public static float lerp(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    return paramFloat1 * paramFloat3 + (1.0F - paramFloat3) * paramFloat2;
  }
  
  public static float lerpSmooth(float paramFloat)
  {
    return paramFloat * paramFloat * (3.0F - 2.0F * paramFloat);
  }
  
  public static float lerpTo(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    paramFloat3 = paramFloat4 / paramFloat3;
    return (1.0F - paramFloat3) * paramFloat2 + paramFloat1 * paramFloat3;
  }
  
  public static void logD(String paramString) {}
  
  public static ByteBuffer newByteBuffer(int paramInt)
  {
    ByteBuffer localByteBuffer = ByteBuffer.allocateDirect(paramInt);
    localByteBuffer.order(ByteOrder.nativeOrder());
    return localByteBuffer;
  }
  
  public static FloatBuffer newFloatBuffer(int paramInt)
  {
    return newByteBuffer(paramInt * 4).asFloatBuffer();
  }
  
  public static IntBuffer newIntBuffer(int paramInt)
  {
    return newByteBuffer(paramInt * 4).asIntBuffer();
  }
  
  public static ShortBuffer newShortBuffer(int paramInt)
  {
    return newByteBuffer(paramInt * 2).asShortBuffer();
  }
  
  public static float normalizeTouchX(float paramFloat1, float paramFloat2)
  {
    return paramFloat1 / paramFloat2 * 2.0F - 1.0F;
  }
  
  public static float normalizeTouchY(float paramFloat1, float paramFloat2)
  {
    return -(paramFloat1 / paramFloat2 * 2.0F - 1.0F);
  }
  
  public static void obfuscatePassword(String paramString, List<Byte> paramList1, List<Byte> paramList2)
  {
    paramList1.clear();
    paramList2.clear();
    int i = 0;
    while (i < paramString.length())
    {
      byte b2 = (byte)paramString.charAt(i);
      byte b1 = (byte)GlobalRand.intRange(-128, 128);
      paramList1.add(Byte.valueOf(b1));
      paramList2.add(Byte.valueOf((byte)(b2 ^ b1)));
      i += 1;
    }
  }
  
  public static float perspectiveFrustumSize(float paramFloat1, float paramFloat2)
  {
    return (float)Math.tan(Math.toRadians(paramFloat2 / 2.0F)) * 2.0F * paramFloat1;
  }
  
  public static void printCharacterStats(Typeface paramTypeface)
  {
    printCharacterStats(paramTypeface, "");
  }
  
  public static void printCharacterStats(Typeface paramTypeface, String paramString)
  {
    String[] arrayOfString1 = new String[5];
    arrayOfString1[0] = "Letter";
    arrayOfString1[1] = "Letter (lower)";
    arrayOfString1[2] = "Number";
    arrayOfString1[3] = "Letter Accent";
    arrayOfString1[4] = "Letter Accent (lower)";
    int i = 0;
    int j = 0;
    while (j < arrayOfString1.length)
    {
      int k = i;
      if (arrayOfString1[j].length() > i) {
        k = arrayOfString1[j].length();
      }
      j += 1;
      i = k;
    }
    String str2 = "%-" + i + "s";
    String str1 = "";
    j = 0;
    while (j < i)
    {
      str1 = str1 + "-";
      j += 1;
    }
    int[] arrayOfInt1 = new int[5];
    int[] arrayOfInt2 = new int[5];
    int[] arrayOfInt3 = new int[5];
    String[] arrayOfString2 = new String[5];
    String[] arrayOfString3 = new String[5];
    String[] arrayOfString4 = new String[5];
    i = 0;
    while (i < 5)
    {
      arrayOfInt1[i] = 0;
      arrayOfInt2[i] = 0;
      arrayOfInt3[i] = 0;
      arrayOfString2[i] = "";
      arrayOfString3[i] = "";
      arrayOfString4[i] = "";
      i += 1;
    }
    Paint localPaint = new Paint();
    localPaint.setTypeface(paramTypeface);
    localPaint.setTextSize(256.0F);
    paramTypeface = new Rect();
    i = 0;
    while (i < 5)
    {
      String str3 = new String[] { "ABCDEFGHIJKLMNOPQRSTUVWXYZ", "abcdefghijklmnopqrstuvwxyz", "0123456789", "АБВГДЕЖЗИЙКЛМНОПРСТУФХЦЩЪЫЬЭ", "абвгдежзийклмнопрстуфхцщъыьэя" }[i];
      j = 0;
      while (j < str3.length())
      {
        String str4 = String.valueOf(str3.charAt(j));
        localPaint.getTextBounds(str4, 0, str4.length(), paramTypeface);
        if ((TextUtils.isEmpty(arrayOfString2[i])) || (paramTypeface.right - paramTypeface.left > arrayOfInt1[i]))
        {
          arrayOfInt1[i] = (paramTypeface.right - paramTypeface.left);
          arrayOfString2[i] = str4;
        }
        if ((TextUtils.isEmpty(arrayOfString3[i])) || (paramTypeface.top < arrayOfInt2[i]))
        {
          arrayOfInt2[i] = paramTypeface.top;
          arrayOfString3[i] = str4;
        }
        if ((TextUtils.isEmpty(arrayOfString4[i])) || (paramTypeface.bottom > arrayOfInt3[i]))
        {
          arrayOfInt3[i] = paramTypeface.bottom;
          arrayOfString4[i] = str4;
        }
        j += 1;
      }
      i += 1;
    }
    if (!TextUtils.isEmpty(paramString)) {
      SysLog.writeD("Font: %s", new Object[] { paramString });
    }
    SysLog.writeD(str2 + " | Width |      | Top |      | Bottom |", new Object[] { "Max" });
    SysLog.writeD(str1 + "-|-------|------|-----|------|--------|------");
    i = 0;
    while (i < 5)
    {
      SysLog.writeD(str2 + " |   %s   | %4s |  %s  | %4s |    %s   | %4s", new Object[] { arrayOfString1[i], arrayOfString2[i], String.valueOf(arrayOfInt1[i]), arrayOfString3[i], String.valueOf(arrayOfInt2[i]), arrayOfString4[i], String.valueOf(arrayOfInt3[i]) });
      i += 1;
    }
  }
  
  @Deprecated
  public static void putArrayToBuffer(float[] paramArrayOfFloat, int paramInt1, FloatBuffer paramFloatBuffer, int paramInt2)
  {
    paramFloatBuffer.put(paramArrayOfFloat, paramInt1, paramInt2);
  }
  
  @Deprecated
  public static void putArrayToBuffer(short[] paramArrayOfShort, int paramInt1, ShortBuffer paramShortBuffer, int paramInt2)
  {
    paramShortBuffer.put(paramArrayOfShort, paramInt1, paramInt2);
  }
  
  public static void rotateDeviceSensorVector(int paramInt, Vector3 paramVector31, Vector3 paramVector32)
  {
    if (3 == paramInt)
    {
      paramVector32.set(-paramVector31.y, paramVector31.x, -paramVector31.z);
      return;
    }
    if (2 == paramInt)
    {
      paramVector32.set(paramVector31.x, paramVector31.y, -paramVector31.z);
      return;
    }
    if (1 == paramInt)
    {
      paramVector32.set(paramVector31.y, -paramVector31.x, -paramVector31.z);
      return;
    }
    paramVector32.set(-paramVector31.x, -paramVector31.y, -paramVector31.z);
  }
  
  public static float rotateOrientationSensorAngle(Display paramDisplay, float paramFloat)
  {
    int i = getDisplayRotation(paramDisplay);
    float f1;
    if (3 == i) {
      f1 = paramFloat - 90.0F;
    }
    do
    {
      return f1;
      if (2 == i) {
        return paramFloat - 180.0F;
      }
      f1 = paramFloat;
    } while (1 != i);
    return paramFloat - 270.0F;
  }
  
  public static boolean setRingtone(Context paramContext, Uri paramUri, String paramString1, String paramString2)
  {
    boolean bool = true;
    if (paramUri == null) {
      try
      {
        throw new Exception();
      }
      catch (Throwable paramUri)
      {
        SysLog.writeD("ERROR: Couldn't set ringtone!");
        if (paramString2 != null) {}
      }
    }
    for (paramUri = paramContext.getString(R.string.ringtone_default_set_error_message);; paramUri = paramString2)
    {
      if (paramString2 != null)
      {
        paramContext = Toast.makeText(paramContext, paramUri, 0);
        paramContext.setGravity(17, paramContext.getXOffset() / 2, 200);
        paramContext.show();
      }
      bool = false;
      do
      {
        return bool;
        RingtoneManager.setActualDefaultRingtoneUri(paramContext, 1, paramUri);
      } while (paramString1 == null);
      paramUri = Toast.makeText(paramContext, paramString1, 1);
      paramUri.setGravity(17, paramUri.getXOffset() / 2, 200);
      paramUri.show();
      return true;
    }
  }
  
  public static boolean setRingtone(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    Object localObject2 = null;
    Object localObject1;
    Uri localUri;
    Cursor localCursor;
    if (Build.VERSION.SDK_INT < 8)
    {
      localObject1 = Environment.getExternalStorageDirectory();
      ((File)localObject1).mkdirs();
      localUri = MediaStore.Audio.Media.getContentUriForPath(new File((File)localObject1, paramString1).getAbsolutePath());
      localCursor = paramContext.getContentResolver().query(localUri, new String[] { "_id", "_data" }, "is_ringtone = 1", null, null);
      localCursor.moveToFirst();
    }
    for (;;)
    {
      localObject1 = localObject2;
      if (!localCursor.isAfterLast())
      {
        if (localCursor.getString(localCursor.getColumnIndex("_data")).toLowerCase(Locale.US).contains(paramString1.toLowerCase(Locale.US))) {
          localObject1 = Uri.withAppendedPath(localUri, String.valueOf(localCursor.getInt(localCursor.getColumnIndex("_id"))));
        }
      }
      else
      {
        return setRingtone(paramContext, (Uri)localObject1, paramString2, paramString3);
        localObject1 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_RINGTONES.toLowerCase(Locale.ENGLISH));
        break;
      }
      localCursor.moveToNext();
    }
  }
  
  public static void vec3FromGraph(Vector3 paramVector3, float paramFloat, Vector3[] paramArrayOfVector3, boolean paramBoolean)
  {
    if ((paramArrayOfVector3 == null) || (paramArrayOfVector3.length == 0))
    {
      paramVector3.set(0.0F, 0.0F, 0.0F);
      return;
    }
    if (paramArrayOfVector3.length < 2)
    {
      paramVector3.set(paramArrayOfVector3[0]);
      return;
    }
    int i;
    int k;
    int j;
    if (paramBoolean)
    {
      paramFloat = clamp(paramFloat, 0.0F, 1.0F);
      i = paramArrayOfVector3.length - 1;
      paramFloat *= i;
      int m = (int)paramFloat;
      k = (int)paramFloat + 1;
      j = m;
      if (m >= i) {
        j = i;
      }
      if (k < i) {
        break label127;
      }
      if (!paramBoolean) {
        break label121;
      }
    }
    for (;;)
    {
      float f1 = j;
      Vector3.mix(paramVector3, paramArrayOfVector3[j], paramArrayOfVector3[i], paramFloat - f1);
      return;
      paramFloat = wrap(paramFloat, 1.0F);
      break;
      label121:
      i = 0;
      continue;
      label127:
      i = k;
    }
  }
  
  public static void vec4FromGraph(Vector4 paramVector4, float paramFloat, Vector4[] paramArrayOfVector4, boolean paramBoolean)
  {
    if ((paramArrayOfVector4 == null) || (paramArrayOfVector4.length == 0))
    {
      paramVector4.set(0.0F, 0.0F, 0.0F, 0.0F);
      return;
    }
    if (paramArrayOfVector4.length < 2)
    {
      paramVector4.set(paramArrayOfVector4[0]);
      return;
    }
    int i;
    int k;
    int j;
    if (paramBoolean)
    {
      paramFloat = clamp(paramFloat, 0.0F, 1.0F);
      i = paramArrayOfVector4.length - 1;
      paramFloat *= i;
      int m = (int)paramFloat;
      k = (int)paramFloat + 1;
      j = m;
      if (m >= i) {
        j = i;
      }
      if (k < i) {
        break label128;
      }
      if (!paramBoolean) {
        break label122;
      }
    }
    for (;;)
    {
      float f1 = j;
      Vector4.mix(paramVector4, paramArrayOfVector4[j], paramArrayOfVector4[i], paramFloat - f1);
      return;
      paramFloat = wrap(paramFloat, 1.0F);
      break;
      label122:
      i = 0;
      continue;
      label128:
      i = k;
    }
  }
  
  public static double wrap(double paramDouble1, double paramDouble2)
  {
    double d1 = paramDouble1 % paramDouble2;
    paramDouble1 = d1;
    if (d1 < 0.0D) {
      paramDouble1 = d1 + paramDouble2;
    }
    return paramDouble1;
  }
  
  public static float wrap(float paramFloat1, float paramFloat2)
  {
    float f1 = paramFloat1 % paramFloat2;
    paramFloat1 = f1;
    if (f1 < 0.0F) {
      paramFloat1 = f1 + paramFloat2;
    }
    return paramFloat1;
  }
  
  public static int wrap(int paramInt1, int paramInt2)
  {
    int i = paramInt1 % paramInt2;
    paramInt1 = i;
    if (i < 0) {
      paramInt1 = i + paramInt2;
    }
    return paramInt1;
  }
  
  public static long wrap(long paramLong1, long paramLong2)
  {
    long l = paramLong1 % paramLong2;
    paramLong1 = l;
    if (l < 0L) {
      paramLong1 = l + paramLong2;
    }
    return paramLong1;
  }
}
