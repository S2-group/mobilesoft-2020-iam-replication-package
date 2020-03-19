package com.inauth.root.utilities;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import com.abc.a1.RootTools;
import com.inauth.mme.InAuthManager;
import com.inauth.utilities.InAuthUtilities;
import com.inauth.utilities.ndk.InAuthNDK;
import com.inauth.utilities.ndk.NDKLog;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

public class RootHelper
{
  private static RootHelper instance;
  
  private RootHelper() {}
  
  private NDKLog decryptSigfile(String paramString1, String paramString2)
  {
    String str2 = "COMPROMISED";
    int i = 0;
    String str1 = str2;
    int j;
    if (paramString1 != null)
    {
      paramString1 = InAuthNDK.getInstance().inAuthroot(paramString1);
      j = InAuthNDK.getInstance().getRootReturnCodeNDK();
      str1 = str2;
      i = j;
      if (paramString1 != null)
      {
        if (!"GET_SIGFILE_VERSION".equals(paramString2)) {
          break label81;
        }
        str1 = parseSigfileVersion(paramString1);
        i = j;
      }
    }
    for (;;)
    {
      paramString1 = new NDKLog();
      paramString1.setDecryptedList(str1);
      paramString1.setReasonCode(i);
      return paramString1;
      label81:
      str1 = str2;
      i = j;
      if ("GET_ROOT_LIST".equals(paramString2))
      {
        str1 = paramString1;
        i = j;
      }
    }
  }
  
  private List<String> findRootApps(Application paramApplication, String[] paramArrayOfString)
  {
    paramApplication = paramApplication.getApplicationContext().getPackageManager().getInstalledPackages(0);
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (i < paramArrayOfString.length)
    {
      int j = 0;
      int k = paramApplication.size();
      while (j < k)
      {
        if (((PackageInfo)paramApplication.get(j)).packageName.equals(paramArrayOfString[i])) {
          localArrayList.add(paramArrayOfString[i]);
        }
        j += 1;
      }
      i += 1;
    }
    return localArrayList;
  }
  
  private boolean foundRootRelatedDirectories(String[] paramArrayOfString)
  {
    int i = 1;
    for (;;)
    {
      if (i < paramArrayOfString.length)
      {
        File localFile = new File(paramArrayOfString[i]);
        if ((!localFile.exists()) && ((localFile.list() == null) || (!localFile.canRead()) || (!localFile.canWrite()))) {}
      }
      else
      {
        return false;
      }
      i += 1;
    }
  }
  
  private static InputStream getInputStreamFromUrl(String paramString)
  {
    try
    {
      paramString = new DefaultHttpClient().execute(new HttpGet(paramString)).getEntity().getContent();
      return paramString;
    }
    catch (Exception paramString) {}
    return null;
  }
  
  public static RootHelper getInstance()
  {
    if (instance == null) {
      instance = new RootHelper();
    }
    return instance;
  }
  
  private JSONObject getValidation(String paramString1, String paramString2, String paramString3)
    throws IOException, JSONException
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (new File(paramString1).exists())
    {
      paramString1 = getSigfileVersion(paramString1);
      paramString2 = getInputStreamFromUrl(paramString3 + "/sigfile/status/android?account_guid=" + paramString2 + "&type=root&version=" + paramString1);
      localObject1 = localObject2;
      if (paramString2 != null)
      {
        paramString1 = null;
        try
        {
          paramString2 = new BufferedReader(new InputStreamReader(paramString2, "UTF-8"));
          paramString1 = paramString2;
        }
        catch (UnsupportedEncodingException paramString2)
        {
          for (;;)
          {
            paramString2.printStackTrace();
          }
        }
        paramString2 = new StringBuilder();
        for (;;)
        {
          paramString3 = paramString1.readLine();
          if (paramString3 == null) {
            break;
          }
          paramString2.append(paramString3);
        }
      }
    }
    try
    {
      localObject1 = new JSONObject(paramString2.toString());
      return localObject1;
    }
    catch (JSONException paramString1) {}
    return null;
  }
  
  private boolean isAppProcessRunning(Application paramApplication, List<String> paramList)
  {
    boolean bool1 = false;
    paramApplication = ((ActivityManager)paramApplication.getSystemService("activity")).getRunningAppProcesses();
    int i = 1;
    int k = paramList.size();
    for (;;)
    {
      boolean bool2 = bool1;
      String str;
      int j;
      if (i < k)
      {
        str = (String)paramList.get(i);
        j = 0;
      }
      for (;;)
      {
        bool2 = bool1;
        if (j < paramApplication.size())
        {
          if (((ActivityManager.RunningAppProcessInfo)paramApplication.get(j)).processName.equals(str)) {
            bool2 = true;
          }
        }
        else
        {
          if (!bool2) {
            break;
          }
          return bool2;
        }
        j += 1;
      }
      i += 1;
      bool1 = bool2;
    }
  }
  
  private boolean isDeviceRooted(Application paramApplication, List<String> paramList1, List<String> paramList2, List<String> paramList3, boolean paramBoolean, String paramString)
  {
    boolean bool = false;
    int j = 0;
    RootTools.debugMode = false;
    paramString = paramString.split("#####");
    Object localObject1 = paramString[2].split("(\\r|\\n)");
    Object localObject3 = paramString[3].split("(\\r|\\n)");
    Object localObject2 = paramString[4].split("(\\r|\\n)");
    paramString = paramString[5].split("(\\r|\\n)");
    localObject1 = localObject1[1];
    localObject3 = localObject3[1];
    localObject2 = localObject2[1];
    String str = paramString[1];
    int i;
    if (paramBoolean)
    {
      if (!RootTools.isRootAvailable())
      {
        paramBoolean = bool;
        if (!RootTools.isAccessGiven()) {}
      }
      else
      {
        paramBoolean = true;
      }
      if (System.getProperty("os.version").indexOf("cyanogen") != -1) {
        paramBoolean = true;
      }
      if (System.getProperty("os.version").indexOf("-CM-") != -1) {
        paramBoolean = true;
      }
      if (Build.PRODUCT.contains("cm_")) {
        paramBoolean = true;
      }
      if (System.getProperty("os.version").indexOf("purity") != -1) {
        paramBoolean = true;
      }
      if (Build.USER.indexOf("paranoid") != -1) {
        paramBoolean = true;
      }
      if (Build.PRODUCT.indexOf("omni") != -1) {
        paramBoolean = true;
      }
      if (Build.PRODUCT.indexOf("du_") != -1) {
        paramBoolean = true;
      }
      if (Build.HOST.contains("nychitman")) {
        paramBoolean = true;
      }
      PackageManager localPackageManager = InAuthManager.getInstance().getApp().getPackageManager();
      if (localPackageManager.hasSystemFeature("com.cyanogenmod.android")) {
        paramBoolean = true;
      }
      if (localPackageManager.hasSystemFeature("com.carbon.android")) {
        paramBoolean = true;
      }
      if (System.getProperty("os.version").indexOf("qemu") != -1) {
        paramBoolean = true;
      }
      bool = paramBoolean;
      if (!paramBoolean)
      {
        if (!paramList2.isEmpty())
        {
          i = 0 + Integer.parseInt((String)localObject1);
          j = i;
          if (isAppProcessRunning(paramApplication, paramList2)) {
            j = i + 3;
          }
        }
        if (j <= 5) {
          break label376;
        }
        bool = true;
      }
    }
    label376:
    do
    {
      return bool;
      paramBoolean = bool;
      if (!isZTEDevice()) {
        break;
      }
      paramBoolean = bool;
      break;
      i = j;
      if (!paramList1.isEmpty())
      {
        j += Integer.parseInt((String)localObject3);
        i = j;
        if (isAppProcessRunning(paramApplication, paramList1)) {
          i = j + 3;
        }
      }
      if (i > 5) {
        return true;
      }
      j = i;
      if (!paramList3.isEmpty())
      {
        i += Integer.parseInt((String)localObject2);
        j = i;
        if (isAppProcessRunning(paramApplication, paramList3)) {
          j = i + 2;
        }
      }
      if (j > 5) {
        return true;
      }
      if (j > 5) {
        return true;
      }
      i = j;
      if (foundRootRelatedDirectories(paramString)) {
        i = j + Integer.parseInt(str);
      }
      bool = paramBoolean;
    } while (i <= 5);
    return true;
  }
  
  private boolean isZTEDevice()
  {
    boolean bool2 = false;
    try
    {
      Object localObject = Class.forName("android.os.SystemProperties");
      Method localMethod = ((Class)localObject).getMethod("get", new Class[] { String.class });
      Object[] arrayOfObject = new Object[3];
      arrayOfObject[0] = new String("ro.product.manufacturer");
      arrayOfObject[1] = new String("ro.build.version.release");
      arrayOfObject[2] = new String("ro.build.version.sdk");
      String str = (String)localMethod.invoke(localObject, new Object[] { arrayOfObject[0] });
      localObject = (String)localMethod.invoke(localObject, new Object[] { arrayOfObject[2] });
      boolean bool1 = bool2;
      if (str.equalsIgnoreCase("zte"))
      {
        boolean bool3 = ((String)localObject).equalsIgnoreCase("10");
        bool1 = bool2;
        if (bool3) {
          bool1 = true;
        }
      }
      return bool1;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      return false;
    }
    catch (SecurityException localSecurityException)
    {
      return false;
    }
    catch (Exception localException) {}
    return false;
  }
  
  private String parseSigfileVersion(String paramString)
  {
    return paramString.split("#####")[0].split("(\\r|\\n)")[0];
  }
  
  public RootLog getRootLog(Application paramApplication, String paramString, boolean paramBoolean)
  {
    String str = "COMPROMISED";
    Object localObject3 = "MISSING_SIGFILE";
    ArrayList localArrayList = new ArrayList();
    Object localObject2 = localObject3;
    Object localObject1 = str;
    if (InAuthUtilities.doesFileExist(paramString))
    {
      NDKLog localNDKLog = decryptSigfile(paramString, "GET_ROOT_LIST");
      paramString = (String)localObject3;
      if (localNDKLog != null) {
        paramString = localNDKLog.getReasonCode();
      }
      localObject2 = paramString;
      localObject1 = str;
      if (localNDKLog.getDecryptedList() != null)
      {
        localObject2 = paramString;
        localObject1 = str;
        if (!"COMPROMISED".equals(localNDKLog.getDecryptedList()))
        {
          localObject2 = localNDKLog.getDecryptedList().split("#####");
          localObject1 = localObject2[2].split("(\\r|\\n)");
          localObject3 = localObject2[3].split("(\\r|\\n)");
          localObject2 = localObject2[4].split("(\\r|\\n)");
          localObject3 = findRootApps(paramApplication, (String[])localObject3);
          localObject1 = findRootApps(paramApplication, (String[])localObject1);
          localObject2 = findRootApps(paramApplication, (String[])localObject2);
          localArrayList.addAll((Collection)localObject2);
          localArrayList.addAll((Collection)localObject3);
          localArrayList.addAll((Collection)localObject1);
          if (!isDeviceRooted(paramApplication, (List)localObject3, (List)localObject1, (List)localObject2, paramBoolean, localNDKLog.getDecryptedList())) {
            break label249;
          }
          localObject1 = "DEVICE_ROOTED";
          localObject2 = paramString;
        }
      }
    }
    for (;;)
    {
      paramApplication = new RootLog();
      paramApplication.setRootStatus((String)localObject1);
      paramApplication.setRootReasonCode((String)localObject2);
      paramApplication.addRootList(localArrayList);
      return paramApplication;
      label249:
      if ((localArrayList != null) && (!localArrayList.isEmpty()))
      {
        localObject1 = "ROOT_APPLICATIONS_INSTALLED";
        localObject2 = paramString;
      }
      else
      {
        localObject1 = "DEVICE_NOT_ROOTED";
        localObject2 = paramString;
      }
    }
  }
  
  public String getSigfileVersion(String paramString)
  {
    String str2 = "MISSING_SIGFILE";
    String str1 = str2;
    if (InAuthUtilities.doesFileExist(paramString))
    {
      paramString = decryptSigfile(paramString, "GET_SIGFILE_VERSION");
      str1 = str2;
      if (paramString.getDecryptedList() != null) {
        str1 = paramString.getDecryptedList();
      }
    }
    return str1;
  }
  
  public String updateSignatureFile(String paramString1, String paramString2, String paramString3)
  {
    paramString1 = Executors.newFixedThreadPool(1).submit(new HttpCall(paramString1, paramString2, paramString3));
    try
    {
      paramString1 = (String)paramString1.get();
      return paramString1;
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
    return "INTERNAL_ERROR";
  }
  
  private class HttpCall
    implements Callable<String>
  {
    private final String accountGUID;
    private final String filePath;
    private final String serverURL;
    private String updateSignatureFileResponse = "INTERNAL_ERROR";
    
    HttpCall(String paramString1, String paramString2, String paramString3)
    {
      this.filePath = paramString1;
      this.accountGUID = paramString2;
      this.serverURL = paramString3;
    }
    
    /* Error */
    public String call()
    {
      // Byte code:
      //   0: aconst_null
      //   1: astore 6
      //   3: aconst_null
      //   4: astore 5
      //   6: aconst_null
      //   7: astore 4
      //   9: aload 5
      //   11: astore_2
      //   12: aload_0
      //   13: getfield 21	com/inauth/root/utilities/RootHelper$HttpCall:this$0	Lcom/inauth/root/utilities/RootHelper;
      //   16: aload_0
      //   17: getfield 30	com/inauth/root/utilities/RootHelper$HttpCall:filePath	Ljava/lang/String;
      //   20: aload_0
      //   21: getfield 32	com/inauth/root/utilities/RootHelper$HttpCall:accountGUID	Ljava/lang/String;
      //   24: aload_0
      //   25: getfield 34	com/inauth/root/utilities/RootHelper$HttpCall:serverURL	Ljava/lang/String;
      //   28: invokestatic 47	com/inauth/root/utilities/RootHelper:access$000	(Lcom/inauth/root/utilities/RootHelper;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lorg/json/JSONObject;
      //   31: astore 7
      //   33: aload 6
      //   35: astore_3
      //   36: aload 7
      //   38: ifnull +193 -> 231
      //   41: aload 5
      //   43: astore_2
      //   44: aload 7
      //   46: ldc 49
      //   48: invokevirtual 55	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   51: astore 8
      //   53: aload 5
      //   55: astore_2
      //   56: aload 8
      //   58: ldc 57
      //   60: invokevirtual 63	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   63: ifeq +12 -> 75
      //   66: aload 5
      //   68: astore_2
      //   69: aload_0
      //   70: ldc 65
      //   72: putfield 28	com/inauth/root/utilities/RootHelper$HttpCall:updateSignatureFileResponse	Ljava/lang/String;
      //   75: aload 6
      //   77: astore_3
      //   78: aload 5
      //   80: astore_2
      //   81: aload 8
      //   83: ldc 67
      //   85: invokevirtual 63	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   88: ifeq +143 -> 231
      //   91: aload 5
      //   93: astore_2
      //   94: aload 7
      //   96: ldc 69
      //   98: invokevirtual 55	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   101: invokestatic 73	com/inauth/root/utilities/RootHelper:access$100	(Ljava/lang/String;)Ljava/io/InputStream;
      //   104: astore_3
      //   105: aload_3
      //   106: ifnull +150 -> 256
      //   109: aload 5
      //   111: astore_2
      //   112: new 75	java/io/BufferedInputStream
      //   115: dup
      //   116: aload_3
      //   117: invokespecial 78	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
      //   120: astore_3
      //   121: aload 5
      //   123: astore_2
      //   124: new 80	org/apache/http/util/ByteArrayBuffer
      //   127: dup
      //   128: sipush 5000
      //   131: invokespecial 83	org/apache/http/util/ByteArrayBuffer:<init>	(I)V
      //   134: astore 6
      //   136: aload 5
      //   138: astore_2
      //   139: aload_3
      //   140: invokevirtual 87	java/io/BufferedInputStream:read	()I
      //   143: istore_1
      //   144: iload_1
      //   145: iconst_m1
      //   146: if_icmpeq +55 -> 201
      //   149: aload 5
      //   151: astore_2
      //   152: aload 6
      //   154: iload_1
      //   155: i2b
      //   156: invokevirtual 90	org/apache/http/util/ByteArrayBuffer:append	(I)V
      //   159: goto -23 -> 136
      //   162: astore_2
      //   163: aload 4
      //   165: astore_3
      //   166: aload_2
      //   167: astore 4
      //   169: aload_3
      //   170: astore_2
      //   171: aload_0
      //   172: ldc 26
      //   174: putfield 28	com/inauth/root/utilities/RootHelper$HttpCall:updateSignatureFileResponse	Ljava/lang/String;
      //   177: aload_3
      //   178: astore_2
      //   179: aload 4
      //   181: invokevirtual 93	java/lang/Exception:printStackTrace	()V
      //   184: aload_3
      //   185: ifnull +11 -> 196
      //   188: aload_3
      //   189: invokevirtual 98	java/io/FileOutputStream:flush	()V
      //   192: aload_3
      //   193: invokevirtual 101	java/io/FileOutputStream:close	()V
      //   196: aload_0
      //   197: getfield 28	com/inauth/root/utilities/RootHelper$HttpCall:updateSignatureFileResponse	Ljava/lang/String;
      //   200: areturn
      //   201: aload 5
      //   203: astore_2
      //   204: new 95	java/io/FileOutputStream
      //   207: dup
      //   208: aload_0
      //   209: getfield 30	com/inauth/root/utilities/RootHelper$HttpCall:filePath	Ljava/lang/String;
      //   212: invokespecial 104	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
      //   215: astore_3
      //   216: aload_3
      //   217: aload 6
      //   219: invokevirtual 108	org/apache/http/util/ByteArrayBuffer:toByteArray	()[B
      //   222: invokevirtual 112	java/io/FileOutputStream:write	([B)V
      //   225: aload_0
      //   226: ldc 114
      //   228: putfield 28	com/inauth/root/utilities/RootHelper$HttpCall:updateSignatureFileResponse	Ljava/lang/String;
      //   231: aload_3
      //   232: ifnull -36 -> 196
      //   235: aload_3
      //   236: invokevirtual 98	java/io/FileOutputStream:flush	()V
      //   239: aload_3
      //   240: invokevirtual 101	java/io/FileOutputStream:close	()V
      //   243: goto -47 -> 196
      //   246: astore_2
      //   247: aload_0
      //   248: ldc 26
      //   250: putfield 28	com/inauth/root/utilities/RootHelper$HttpCall:updateSignatureFileResponse	Ljava/lang/String;
      //   253: goto -57 -> 196
      //   256: aload 5
      //   258: astore_2
      //   259: aload_0
      //   260: ldc 26
      //   262: putfield 28	com/inauth/root/utilities/RootHelper$HttpCall:updateSignatureFileResponse	Ljava/lang/String;
      //   265: aload 6
      //   267: astore_3
      //   268: goto -37 -> 231
      //   271: astore_3
      //   272: aload_2
      //   273: ifnull +11 -> 284
      //   276: aload_2
      //   277: invokevirtual 98	java/io/FileOutputStream:flush	()V
      //   280: aload_2
      //   281: invokevirtual 101	java/io/FileOutputStream:close	()V
      //   284: aload_3
      //   285: athrow
      //   286: astore_2
      //   287: aload_0
      //   288: ldc 26
      //   290: putfield 28	com/inauth/root/utilities/RootHelper$HttpCall:updateSignatureFileResponse	Ljava/lang/String;
      //   293: goto -97 -> 196
      //   296: astore_2
      //   297: aload_0
      //   298: ldc 26
      //   300: putfield 28	com/inauth/root/utilities/RootHelper$HttpCall:updateSignatureFileResponse	Ljava/lang/String;
      //   303: goto -19 -> 284
      //   306: astore 4
      //   308: aload_3
      //   309: astore_2
      //   310: aload 4
      //   312: astore_3
      //   313: goto -41 -> 272
      //   316: astore 4
      //   318: goto -149 -> 169
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	321	0	this	HttpCall
      //   143	12	1	i	int
      //   11	141	2	localObject1	Object
      //   162	5	2	localException1	Exception
      //   170	34	2	localObject2	Object
      //   246	1	2	localException2	Exception
      //   258	23	2	localObject3	Object
      //   286	1	2	localException3	Exception
      //   296	1	2	localException4	Exception
      //   309	1	2	localObject4	Object
      //   35	233	3	localObject5	Object
      //   271	38	3	localObject6	Object
      //   312	1	3	localObject7	Object
      //   7	173	4	localObject8	Object
      //   306	5	4	localObject9	Object
      //   316	1	4	localException5	Exception
      //   4	253	5	localObject10	Object
      //   1	265	6	localByteArrayBuffer	org.apache.http.util.ByteArrayBuffer
      //   31	64	7	localJSONObject	JSONObject
      //   51	31	8	str	String
      // Exception table:
      //   from	to	target	type
      //   12	33	162	java/lang/Exception
      //   44	53	162	java/lang/Exception
      //   56	66	162	java/lang/Exception
      //   69	75	162	java/lang/Exception
      //   81	91	162	java/lang/Exception
      //   94	105	162	java/lang/Exception
      //   112	121	162	java/lang/Exception
      //   124	136	162	java/lang/Exception
      //   139	144	162	java/lang/Exception
      //   152	159	162	java/lang/Exception
      //   204	216	162	java/lang/Exception
      //   259	265	162	java/lang/Exception
      //   235	243	246	java/lang/Exception
      //   12	33	271	finally
      //   44	53	271	finally
      //   56	66	271	finally
      //   69	75	271	finally
      //   81	91	271	finally
      //   94	105	271	finally
      //   112	121	271	finally
      //   124	136	271	finally
      //   139	144	271	finally
      //   152	159	271	finally
      //   171	177	271	finally
      //   179	184	271	finally
      //   204	216	271	finally
      //   259	265	271	finally
      //   188	196	286	java/lang/Exception
      //   276	284	296	java/lang/Exception
      //   216	231	306	finally
      //   216	231	316	java/lang/Exception
    }
  }
}
