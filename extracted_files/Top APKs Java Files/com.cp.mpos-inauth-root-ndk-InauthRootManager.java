package inauth.root.ndk;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.stericson.RootTools.RootTools;
import com.stericson.RootTools.RootToolsException;
import inauth.root.ndk.network.SendTasks;
import inauth.root.ndk.utils.InAuthrootVars;
import inauth.root.ndk.utils.RootcheckerUtilities;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

@SuppressLint({"SimpleDateFormat"})
public class InauthRootManager
{
  public static final String LOG_URL = "/v0/devices/logs";
  public static final String REG_URL = "/register";
  private static String l;
  private static InauthrootNDK m;
  private int a;
  private int b = 0;
  private int c = 0;
  private boolean d = false;
  private String e;
  private String f = null;
  private String g = "-1.0";
  private String h = Rootchecker.getinAuthrootVersion();
  private String i = null;
  private int j = -1;
  private String k = "";
  
  public InauthRootManager()
  {
    m = new InauthrootNDK();
    this.a = 0;
  }
  
  public InauthRootManager(String paramString1, String paramString2)
  {
    m = new InauthrootNDK();
    this.i = paramString2;
    l = paramString1;
    this.a = 0;
  }
  
  private static List<String> a(String[] paramArrayOfString)
  {
    RootTools.log("runcmd ", "Runing commands ppp");
    try
    {
      paramArrayOfString = new Executer().a(paramArrayOfString);
      return paramArrayOfString;
    }
    catch (IOException paramArrayOfString)
    {
      paramArrayOfString.printStackTrace();
      return null;
    }
    catch (RootToolsException paramArrayOfString)
    {
      paramArrayOfString.printStackTrace();
      return null;
    }
    catch (TimeoutException paramArrayOfString)
    {
      paramArrayOfString.printStackTrace();
    }
    return null;
  }
  
  private static void a(String paramString)
  {
    SharedPreferences.Editor localEditor = Rootchecker.getinAuthRootchecker().getmPrefs().edit();
    localEditor.putString("SIG_CUR_VERSION", paramString);
    localEditor.commit();
  }
  
  private boolean a()
  {
    this.b = 0;
    if (RootTools.isAccessGiven())
    {
      RootTools.log(" This phone is Rooted and has permitted inAuth Rootchecker for root access(uid=0)(gid=0)");
      setrootReturnCode(1);
      return true;
    }
    RootTools.log("Starting Inauth algorithm " + this.b);
    try
    {
      d();
      RootTools.log("After running algorithm " + this.b);
      if (this.b >= this.c)
      {
        RootTools.log(" This phone is Rooted based on our algorithm Weightage - " + this.b);
        setrootReturnCode(2);
        return true;
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
        setrootReturnCode(6);
      }
      RootTools.log("This phone is NOT Rooted based on " + this.b + " return value");
      setrootReturnCode(0);
    }
    return false;
  }
  
  private static void b(String paramString)
  {
    SharedPreferences.Editor localEditor = Rootchecker.getinAuthRootchecker().getmPrefs().edit();
    localEditor.putString("SIG_MAX_VERSION", paramString);
    localEditor.commit();
  }
  
  private static boolean b()
  {
    for (;;)
    {
      try
      {
        Object localObject = Class.forName("android.os.SystemProperties");
        RootTools.log("isitzte()", "Entering Zte checks ");
        Method localMethod = ((Class)localObject).getMethod("get", new Class[] { String.class });
        Object[] arrayOfObject = new Object[3];
        arrayOfObject[0] = new String("ro.product.manufacturer");
        arrayOfObject[1] = new String("ro.build.version.release");
        arrayOfObject[2] = new String("ro.build.version.sdk");
        String str1 = (String)localMethod.invoke(localObject, new Object[] { arrayOfObject[0] });
        String str2 = (String)localMethod.invoke(localObject, new Object[] { arrayOfObject[1] });
        localObject = (String)localMethod.invoke(localObject, new Object[] { arrayOfObject[2] });
        RootTools.log("InAuthRoot", "Manufacturer  - " + str1 + "\n Android OS Version " + (String)localObject + "\n Release - " + str2);
        if (str1.equalsIgnoreCase("zte"))
        {
          bool = ((String)localObject).equalsIgnoreCase("10");
          if (bool)
          {
            bool = true;
            return bool;
          }
        }
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
        localClassNotFoundException.printStackTrace();
        return false;
      }
      catch (SecurityException localSecurityException)
      {
        localSecurityException.printStackTrace();
        return false;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        return false;
      }
      boolean bool = false;
    }
  }
  
  private static boolean b(String[] paramArrayOfString)
  {
    List localList = ((ActivityManager)Rootchecker.getApp().getSystemService("activity")).getRunningAppProcesses();
    Log.e("InAuthRoot", "Total number of running process  - " + localList.size());
    int n = 1;
    if (n >= paramArrayOfString.length) {
      return false;
    }
    String str = paramArrayOfString[n];
    int i1 = 0;
    for (;;)
    {
      if (i1 >= localList.size())
      {
        n += 1;
        break;
      }
      if (((ActivityManager.RunningAppProcessInfo)localList.get(i1)).processName.equals(str))
      {
        Log.e("InAuthRoot", "package name " + str + " found running ");
        return true;
      }
      i1 += 1;
    }
  }
  
  private void c(String[] paramArrayOfString)
  {
    Context localContext = Rootchecker.getApp().getApplicationContext();
    List localList = localContext.getPackageManager().getInstalledPackages(0);
    int i3 = 1;
    int n = 0;
    String str1;
    int i4;
    int i1;
    for (;;)
    {
      if (i3 >= paramArrayOfString.length) {
        return;
      }
      str1 = paramArrayOfString[i3];
      i4 = 0;
      i1 = 0;
      if (i4 < localList.size()) {
        break;
      }
      i3 += 1;
    }
    Object localObject = (PackageInfo)localList.get(i4);
    String str2 = ((PackageInfo)localObject).applicationInfo.packageName;
    int i2 = n;
    if (str2.equalsIgnoreCase(str1))
    {
      RootTools.log("InAuthRoot", " application " + str2 + " found \n");
      i2 = n + 2;
      i1 = 1;
    }
    localObject = ((PackageInfo)localObject).applicationInfo.loadLabel(localContext.getPackageManager()).toString();
    if (((String)localObject).equalsIgnoreCase(str1))
    {
      RootTools.log("InAuthRoot", " application " + (String)localObject + " found \n");
      i1 = i2 + 2;
      n = 1;
    }
    for (;;)
    {
      if ((n != 0) && (i1 >= 4))
      {
        this.b += i1;
        return;
      }
      i4 += 1;
      i2 = i1;
      i1 = n;
      n = i2;
      break;
      n = i1;
      i1 = i2;
    }
  }
  
  private static boolean c()
  {
    try
    {
      Object localObject = Class.forName("android.os.SystemProperties");
      localObject = (String)((Class)localObject).getMethod("get", new Class[] { String.class }).invoke(localObject, new Object[] { new String("ro.secure") });
      RootTools.log("ro Secure value -", (String)localObject);
      if ("0".equals(localObject))
      {
        RootTools.log("InAuthRoot", "Secured property is set to ROOTED ");
        return true;
      }
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      localClassNotFoundException.printStackTrace();
      return false;
    }
    catch (SecurityException localSecurityException)
    {
      localSecurityException.printStackTrace();
      return false;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      return false;
    }
    return false;
  }
  
  private boolean c(String paramString)
  {
    setrootReturnCode(2);
    try
    {
      this.e = m.a(paramString);
      setrootReturnCode(m.a());
      RootTools.log("InAuthRoot", "InauthroottNDk Reasoncode - " + getrootReturnCode());
      if (getrootReturnCode() != 0) {
        return false;
      }
      if (this.e == null)
      {
        RootTools.log("InAuthRoot", " NDK returning null");
        setrootReturnCode(getrootReturnCode());
        return false;
      }
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
      setrootReturnCode(5);
      return false;
    }
    return true;
  }
  
  private static String d(String paramString)
  {
    try
    {
      paramString = new RandomAccessFile(paramString, "r");
      byte[] arrayOfByte = new byte[(int)paramString.length()];
      paramString.readFully(arrayOfByte);
      paramString.close();
      paramString = new String(arrayOfByte, "UTF-8");
      return paramString;
    }
    catch (IOException paramString)
    {
      RootTools.log("readFileError", "File doesnt exist");
      return null;
    }
    catch (Exception paramString)
    {
      RootTools.log("RootChecker", "File doesnt exist");
    }
    return null;
  }
  
  private void d()
  {
    Object localObject1 = this.e.replaceAll("\\r|\\n", "").split("==========");
    RootTools.log("InAuthRoot", " RootInfo Contents  \n" + localObject1[0] + localObject1[1] + localObject1[2] + localObject1[3] + localObject1[4]);
    if (c())
    {
      this.b += Integer.valueOf(this.k).intValue();
      RootTools.log("ADB running in unsecured mode - " + this.b);
    }
    if (RootTools.isRootAvailable())
    {
      this.b += Integer.valueOf(this.k).intValue();
      RootTools.log("Su binary found - " + this.b);
    }
    Log.e("InAuthRoot", "After Prioritycheck 1 - " + this.b);
    label207:
    Object localObject2;
    Object localObject3;
    int n;
    Object localObject4;
    if ((!this.d) && (this.b >= this.c))
    {
      return;
    }
    else
    {
      do
      {
        do
        {
          do
          {
            localObject2 = localObject1[1].split(",");
            localObject3 = localObject2[0];
            if (d((String[])localObject2))
            {
              n = this.b;
              this.b = (Integer.valueOf((String)localObject3).intValue() + n);
            }
            if ((this.b < this.c) && (b((String[])localObject2))) {
              this.b += Integer.valueOf(this.k).intValue();
            }
            Log.e("InAuthRoot", "After Prioritycheck 2 - " + this.b);
          } while ((!this.d) && (this.b >= this.c));
          localObject3 = localObject1[2].split(",");
          localObject4 = localObject2[0];
          if (d((String[])localObject3))
          {
            this.b += Integer.valueOf((String)localObject4).intValue();
            RootTools.log("InAuthRoot", "Root weightage - " + this.b);
          }
          if ((this.b < this.c) && (b((String[])localObject2))) {
            this.b += Integer.valueOf((String)localObject4).intValue();
          }
          Log.e("InAuthRoot", "After Prioritycheck 3 - " + this.b);
        } while ((!this.d) && (this.b >= this.c));
        localObject2 = localObject1[3].split(",");
        localObject3 = localObject2[0];
        c((String[])localObject2);
        if ((this.b < this.c) && (b((String[])localObject2))) {
          this.b += Integer.valueOf((String)localObject3).intValue();
        }
        Log.e("InAuthRoot", "After Prioritycheck 4 - " + this.b);
      } while ((!this.d) && (this.b >= this.c));
      localObject2 = localObject1[4].split(",");
      n = 1;
    }
    for (;;)
    {
      if (n >= localObject2.length) {}
      for (n = 0;; n = 2)
      {
        if (n > 0) {
          this.b += Integer.valueOf(localObject2[0]).intValue();
        }
        Log.e("InAuthRoot", "After Prioritycheck 5 - " + this.b);
        localObject1 = localObject1[5].split(",");
        RootTools.log("InAuthRoot", "Executing sh Commands : " + localObject1[1] + localObject1[2]);
        if (!b()) {
          break label207;
        }
        localObject1 = a(new String[] { localObject1[1], localObject1[2] });
        if (localObject1 == null) {
          break label207;
        }
        RootTools.log("InAuthRoot", "Command execution results : ");
        localObject1 = ((List)localObject1).iterator();
        if (!((Iterator)localObject1).hasNext()) {
          break label207;
        }
        localObject2 = (String)((Iterator)localObject1).next();
        RootTools.log("InAuthRoot", (String)localObject2);
        if (!((String)localObject2).contains("uid=0")) {
          break;
        }
        this.b += this.c;
        RootTools.log("InAuthRoot", "UID set to root - " + this.b);
        return;
        localObject3 = localObject2[n];
        localObject4 = new File((String)localObject3);
        if ((!((File)localObject4).exists()) && ((((File)localObject4).list() == null) || (!((File)localObject4).canRead()) || (!((File)localObject4).canWrite()))) {
          break label955;
        }
        RootTools.log("InAuthRoot", "Directory " + (String)localObject3 + " accessible - " + this.b + " " + ((File)localObject4).canRead() + " " + ((File)localObject4).canWrite());
        RootTools.log("InAuthRoot", "Directory " + (String)localObject3 + " has the following files - " + ((File)localObject4).list());
      }
      label955:
      RootTools.log("InAuthRoot", "Directory " + (String)localObject3 + " not found");
      n += 1;
    }
  }
  
  private static boolean d(String[] paramArrayOfString)
  {
    Context localContext = Rootchecker.getApp().getApplicationContext();
    List localList = localContext.getPackageManager().getInstalledPackages(0);
    int n = 1;
    boolean bool2;
    if (n >= paramArrayOfString.length)
    {
      bool2 = false;
      label30:
      return bool2;
    }
    String str1 = paramArrayOfString[n];
    int i1 = 0;
    boolean bool1 = false;
    for (;;)
    {
      if (i1 >= localList.size())
      {
        n += 1;
        break;
      }
      Object localObject = (PackageInfo)localList.get(i1);
      String str2 = ((PackageInfo)localObject).applicationInfo.packageName;
      if (str2.equalsIgnoreCase(str1))
      {
        RootTools.log("InAuthRoot", " application " + str2 + " found \n");
        bool1 = true;
      }
      localObject = ((PackageInfo)localObject).applicationInfo.loadLabel(localContext.getPackageManager()).toString();
      if (((String)localObject).equalsIgnoreCase(str1))
      {
        RootTools.log("InAuthRoot", " application " + (String)localObject + " found \n");
        bool1 = true;
      }
      bool2 = bool1;
      if (bool1) {
        break label30;
      }
      i1 += 1;
    }
  }
  
  public static void placeFileintoInternalmemory()
  {
    String str1 = d("/sdcard/root" + InAuthrootVars.filename);
    String str2 = Rootchecker.getApp().getFilesDir() + "/sdcard/root";
    Object localObject = new File(str2);
    if (!((File)localObject).exists())
    {
      ((File)localObject).mkdirs();
      RootTools.log("Internalfilecreator", "Creating Internal directory structure");
    }
    str2 = str2 + InAuthrootVars.filename;
    try
    {
      localObject = new BufferedOutputStream(new FileOutputStream(str2, false));
      ((OutputStream)localObject).write(str1.getBytes());
      ((OutputStream)localObject).flush();
      ((OutputStream)localObject).close();
      RootTools.log("InAuthRoot", "Encryption File has been created inside internal memory at " + str2);
      return;
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      RootTools.log("InAuthRoot", "File not found Exception");
      localFileNotFoundException.printStackTrace();
      return;
    }
    catch (Exception localException)
    {
      RootTools.log("InAuthRoot", "Exception inside Writing Internal file");
      localException.printStackTrace();
    }
  }
  
  public static String readFilefromInternalMemory(String paramString)
  {
    try
    {
      paramString = new BufferedReader(new InputStreamReader(Rootchecker.getApp().openFileInput(paramString)));
      StringBuffer localStringBuffer = new StringBuffer();
      for (;;)
      {
        String str = paramString.readLine();
        if (str == null)
        {
          RootTools.log("InAuthRoot", "Internal Memory File contents: \n" + localStringBuffer.toString());
          return localStringBuffer.toString();
        }
        localStringBuffer.append(str);
      }
      return null;
    }
    catch (FileNotFoundException paramString)
    {
      RootTools.log("InAuthRoot", "File not found");
      paramString.printStackTrace();
      return null;
    }
    catch (IOException paramString)
    {
      RootTools.log("Internal Writing IO ERROR", "File not found");
      paramString.printStackTrace();
      return null;
    }
    catch (Exception paramString)
    {
      RootTools.log("IO ERROR", "Internal File writing error");
      paramString.printStackTrace();
    }
  }
  
  public static String toHex(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null) {
      return "";
    }
    StringBuffer localStringBuffer = new StringBuffer(paramArrayOfByte.length * 2);
    int n = 0;
    for (;;)
    {
      if (n >= paramArrayOfByte.length) {
        return localStringBuffer.toString();
      }
      int i1 = paramArrayOfByte[n];
      localStringBuffer.append("0123456789ABCDEF".charAt(i1 >> 4 & 0xF)).append("0123456789ABCDEF".charAt(i1 & 0xF));
      n += 1;
    }
  }
  
  public String AnonymousReport()
  {
    if (Rootchecker.getinAuthRootchecker().getmPrefs().getBoolean("ANONYMOUS_REPORT", false)) {
      localObject = "DUPLICATE_SEND";
    }
    String str;
    do
    {
      do
      {
        return localObject;
        str = new SendTasks().send("anonymous");
        new StringBuilder("Anonymous Report() : ").append(str).toString();
        localObject = str;
      } while (str == null);
      localObject = str;
    } while (!str.equalsIgnoreCase("success"));
    Object localObject = Rootchecker.getinAuthRootchecker().getmPrefs().edit();
    ((SharedPreferences.Editor)localObject).putBoolean("ANONYMOUS_REPORT", true);
    ((SharedPreferences.Editor)localObject).commit();
    return str;
  }
  
  public String DetailedReport()
  {
    String str = new SendTasks().send("detailed");
    new StringBuilder("Detailed Report() : ").append(str).toString();
    return str;
  }
  
  public String getCustomerId()
  {
    return this.i;
  }
  
  public String getDeviceInfo()
  {
    return this.f;
  }
  
  public String getDevicePersistentId()
  {
    Context localContext = Rootchecker.getApp().getApplicationContext();
    Object localObject = (TelephonyManager)localContext.getSystemService("phone");
    String str = ((TelephonyManager)localObject).getDeviceId();
    localObject = ((TelephonyManager)localObject).getSimSerialNumber();
    return new UUID(Settings.Secure.getString(localContext.getContentResolver(), "android_id").hashCode(), str.hashCode() << 32 | ((String)localObject).hashCode()).toString();
  }
  
  public String getFullUrl(String paramString)
  {
    return l + paramString;
  }
  
  public String getHost()
  {
    return l;
  }
  
  public int getInauthrootValue()
  {
    return this.j;
  }
  
  public String getLogUrl()
  {
    return "/v0/devices/logs";
  }
  
  public String getSigfileVersion()
  {
    return this.g;
  }
  
  public String getinAuthrootVersion()
  {
    return this.h;
  }
  
  public int getrootReturnCode()
  {
    return this.a;
  }
  
  public int inAuthroot(String paramString)
  {
    this.a = 0;
    this.e = null;
    if (paramString == null)
    {
      RootTools.log("InAuthRoot", "This device is compromised, file name is null");
      setrootReturnCode(1);
      this.j = 2;
      return 2;
    }
    if (!c(paramString))
    {
      RootTools.log("InAuthRoot", "This device is compromised");
      this.j = 2;
      return 2;
    }
    paramString = this.e.replaceAll("\\r|\\n", "").split("==========")[0].split(",");
    this.k = paramString[2];
    this.c = Integer.valueOf(paramString[3]).intValue();
    if (paramString[4].equals("true")) {
      this.d = true;
    }
    this.g = paramString[0];
    this.h = paramString[1];
    RootTools.log("InAuthRoot", "Sig File Version - " + getSigfileVersion());
    RootTools.log("InAuthRoot", "InAuthroot Version - " + getinAuthrootVersion());
    paramString = getSigfileVersion();
    double d1 = Double.valueOf(paramString).doubleValue();
    double d2;
    double d3;
    int n;
    if (Rootchecker.getinAuthRootchecker().getmPrefs().getBoolean("LIMIT_VERSION", false))
    {
      d2 = Double.valueOf(Rootchecker.getinAuthRootchecker().getmPrefs().getString("MAX_ALLOWED_VERSION", "-1.0")).doubleValue();
      d3 = Double.valueOf(Rootchecker.getinAuthRootchecker().getmPrefs().getString("MIN_ALLOWED_VERSION", "-1.0")).doubleValue();
      if ((d1 <= d2) && (d1 >= d3)) {
        n = 1;
      }
    }
    while (n == 0)
    {
      this.j = 7;
      return 2;
      d2 = Double.valueOf(Rootchecker.getinAuthRootchecker().getmPrefs().getString("SIG_CUR_VERSION", "-1.0")).doubleValue();
      d3 = Double.valueOf(Rootchecker.getinAuthRootchecker().getmPrefs().getString("SIG_MAX_VERSION", "-1.0")).doubleValue();
      RootTools.log("InAuthRoot", "returning sig_stored_version  " + d2);
      if (d2 == -1.0D)
      {
        a(paramString);
        b(paramString);
        RootTools.log("InAuthRoot", "first time ");
        n = 1;
      }
      else
      {
        if (d1 > d3)
        {
          RootTools.log("InAuthRoot", "Highest Version device seen  " + d3);
          b(paramString);
        }
        if ((d1 - 0.1D == d2) || (d1 == d2) || (d1 + 0.1D == d3))
        {
          a(paramString);
          RootTools.log("InAuthRoot", "Dot one comparision successful.returning true  ");
          n = 1;
        }
        else
        {
          RootTools.log("InAuthRoot", "returning false ");
          this.j = 7;
          n = 0;
        }
      }
    }
    if (!a())
    {
      RootTools.log("InAuthRoot", "This device is not Rooted");
      this.j = 0;
      return 0;
    }
    this.e = null;
    this.b = 0;
    this.j = 1;
    return 1;
  }
  
  public boolean register(String paramString)
  {
    l = paramString;
    long l1 = RootcheckerUtilities.getValidateCode4UploadFile();
    paramString = RootcheckerUtilities.registerWithServer(getDeviceInfo(), l1);
    for (;;)
    {
      try
      {
        paramString = paramString.split(",");
        try
        {
          int n = Integer.parseInt(paramString[0]);
          switch (n)
          {
          default: 
            return false;
          }
        }
        catch (Exception paramString)
        {
          RootTools.log("InAuthRoot", " Null response code");
          return false;
        }
        localEditor = Rootchecker.getinAuthRootchecker().getmPrefs().edit();
      }
      catch (Exception paramString)
      {
        SharedPreferences.Editor localEditor;
        paramString.printStackTrace();
        RootTools.log("InAuthRoot", "Unable to connect to http server.");
        return false;
      }
      try
      {
        localEditor.putInt("REG_ID", Integer.parseInt(paramString[1]));
        if (Integer.parseInt(paramString[0]) == 0)
        {
          localEditor.putString("DEVICE_ID_TIME", new SimpleDateFormat("M/dd/yyyy hh:mm a").format(new Date()));
          localEditor.putLong("KEY_FILE_UPLOAD_CODE", l1);
        }
        if (Integer.parseInt(paramString[0]) == 1)
        {
          localEditor.putLong("KEY_FILE_UPLOAD_CODE", l1);
          localEditor.putString("DEVICE_ID_TIME", paramString[2]);
          localEditor.putString("DEVICE_ID_TM_DEVICE", paramString[3]);
          localEditor.putString("DEVICE_ID_TM_SIM", paramString[4]);
          localEditor.putString("DEVICE_ID_ANDROID_ID", paramString[5]);
          localEditor.putString("DEVICE_ID", paramString[6]);
          localEditor.putString("DEVICE_MODEL", paramString[7]);
          this.f = (paramString[2] + "," + paramString[3] + "," + paramString[4] + "," + paramString[5] + "," + paramString[6] + "," + paramString[7]);
          RootTools.log("InAuthRoot", "saved Registration Info successfully");
        }
        localEditor.commit();
      }
      catch (NumberFormatException paramString)
      {
        RootTools.log("InAuthRoot", "Invalid device id.");
        paramString.printStackTrace();
        continue;
      }
      RootTools.log("InAuthRoot", "Successfully got the response code(0|1).");
      return true;
      RootTools.log("InAuthRoot", "Bad Response code during registration" + paramString[1]);
    }
  }
  
  public void setLowerLimitSigVersion(String paramString)
  {
    SharedPreferences.Editor localEditor = Rootchecker.getinAuthRootchecker().getmPrefs().edit();
    localEditor.putString("MIN_ALLOWED_VERSION", paramString);
    localEditor.commit();
  }
  
  public void setUpperLimitSigVersion(String paramString)
  {
    SharedPreferences.Editor localEditor = Rootchecker.getinAuthRootchecker().getmPrefs().edit();
    localEditor.putString("MAX_ALLOWED_VERSION", paramString);
    localEditor.commit();
  }
  
  public void setrootReturnCode(int paramInt)
  {
    this.a = paramInt;
  }
}
