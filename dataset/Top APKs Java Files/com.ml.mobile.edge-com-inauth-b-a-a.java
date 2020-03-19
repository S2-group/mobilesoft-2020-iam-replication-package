package com.inauth.b.a;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.inauth.ndk.InAuthNDK;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class a
{
  private static a a;
  
  private a() {}
  
  public static a a()
  {
    if (a == null) {
      a = new a();
    }
    return a;
  }
  
  private com.inauth.ndk.a a(String paramString1, String paramString2)
  {
    String str2 = "COMPROMISED";
    int i = 0;
    String str1 = str2;
    if (paramString1 != null)
    {
      paramString1 = InAuthNDK.a().b(paramString1);
      int j = InAuthNDK.a().c();
      i = j;
      str1 = str2;
      if (paramString1 != null)
      {
        str1 = b(paramString1, paramString2);
        i = j;
      }
    }
    paramString1 = new com.inauth.ndk.a();
    paramString1.a(str1);
    paramString1.a(i);
    return paramString1;
  }
  
  private String a(byte[] paramArrayOfByte)
  {
    String str = "";
    int i = 0;
    while (i < paramArrayOfByte.length)
    {
      str = str + Integer.toString((paramArrayOfByte[i] & 0xFF) + 256, 16).substring(1);
      i += 1;
    }
    return str;
  }
  
  private String b(String paramString1, String paramString2)
  {
    String str = "";
    String[] arrayOfString1 = paramString1.split("#####");
    String[] arrayOfString2 = arrayOfString1[0].split("(\\r|\\n)");
    paramString1 = str;
    if ("GET_SIGFILE_VERSION".equals(paramString2)) {
      paramString1 = arrayOfString2[0];
    }
    if ("GET_MALWARE_LIST".equals(paramString2)) {
      paramString1 = arrayOfString1[1];
    }
    return paramString1;
  }
  
  private List<String> b(Application paramApplication, String paramString)
  {
    paramString = paramString.split("(\\r|\\n)");
    paramApplication = paramApplication.getApplicationContext().getPackageManager().getInstalledPackages(0);
    ArrayList localArrayList = new ArrayList();
    int i = 1;
    while (i < paramString.length)
    {
      String[] arrayOfString = paramString[i].split(";");
      int j = 0;
      if (j < paramApplication.size())
      {
        Object localObject = (PackageInfo)paramApplication.get(j);
        if (((PackageInfo)localObject).packageName.equals(arrayOfString[0]))
        {
          localObject = c(((PackageInfo)localObject).applicationInfo.sourceDir);
          if (localObject != null) {
            break label116;
          }
        }
        for (;;)
        {
          j += 1;
          break;
          label116:
          if (((String)localObject).equals(arrayOfString[1])) {
            localArrayList.add(paramString[i]);
          }
        }
      }
      i += 1;
    }
    return localArrayList;
  }
  
  private boolean b(String paramString)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (paramString != null)
    {
      paramString = new File(paramString);
      bool1 = bool2;
      if (paramString.exists())
      {
        bool1 = bool2;
        if (paramString.canRead()) {
          bool1 = true;
        }
      }
    }
    return bool1;
  }
  
  /* Error */
  private String c(String paramString)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_2
    //   2: new 155	java/io/FileInputStream
    //   5: dup
    //   6: aload_1
    //   7: invokespecial 156	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
    //   10: astore_1
    //   11: sipush 1024
    //   14: newarray byte
    //   16: astore 4
    //   18: ldc -98
    //   20: invokestatic 164	java/security/MessageDigest:getInstance	(Ljava/lang/String;)Ljava/security/MessageDigest;
    //   23: astore 5
    //   25: iload_2
    //   26: iconst_m1
    //   27: if_icmpeq +42 -> 69
    //   30: aload_1
    //   31: aload 4
    //   33: invokevirtual 170	java/io/InputStream:read	([B)I
    //   36: istore_3
    //   37: iload_3
    //   38: istore_2
    //   39: iload_3
    //   40: ifle -15 -> 25
    //   43: aload 5
    //   45: aload 4
    //   47: iconst_0
    //   48: iload_3
    //   49: invokevirtual 174	java/security/MessageDigest:update	([BII)V
    //   52: iload_3
    //   53: istore_2
    //   54: goto -29 -> 25
    //   57: astore 4
    //   59: aload_1
    //   60: ifnull +7 -> 67
    //   63: aload_1
    //   64: invokevirtual 177	java/io/InputStream:close	()V
    //   67: aconst_null
    //   68: areturn
    //   69: aload_0
    //   70: aload 5
    //   72: invokevirtual 181	java/security/MessageDigest:digest	()[B
    //   75: invokespecial 183	com/inauth/b/a/a:a	([B)Ljava/lang/String;
    //   78: astore 4
    //   80: aload_1
    //   81: ifnull +7 -> 88
    //   84: aload_1
    //   85: invokevirtual 177	java/io/InputStream:close	()V
    //   88: aload 4
    //   90: areturn
    //   91: astore 4
    //   93: aconst_null
    //   94: astore_1
    //   95: aload_1
    //   96: ifnull +7 -> 103
    //   99: aload_1
    //   100: invokevirtual 177	java/io/InputStream:close	()V
    //   103: aload 4
    //   105: athrow
    //   106: astore_1
    //   107: aconst_null
    //   108: areturn
    //   109: astore_1
    //   110: aconst_null
    //   111: areturn
    //   112: astore_1
    //   113: aconst_null
    //   114: areturn
    //   115: astore 4
    //   117: goto -22 -> 95
    //   120: astore_1
    //   121: aconst_null
    //   122: astore_1
    //   123: goto -64 -> 59
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	126	0	this	a
    //   0	126	1	paramString	String
    //   1	53	2	i	int
    //   36	17	3	j	int
    //   16	30	4	arrayOfByte	byte[]
    //   57	1	4	localException	Exception
    //   78	11	4	str	String
    //   91	13	4	localObject1	Object
    //   115	1	4	localObject2	Object
    //   23	48	5	localMessageDigest	java.security.MessageDigest
    // Exception table:
    //   from	to	target	type
    //   11	25	57	java/lang/Exception
    //   30	37	57	java/lang/Exception
    //   43	52	57	java/lang/Exception
    //   69	80	57	java/lang/Exception
    //   2	11	91	finally
    //   84	88	106	java/lang/Exception
    //   63	67	109	java/lang/Exception
    //   99	103	112	java/lang/Exception
    //   11	25	115	finally
    //   30	37	115	finally
    //   43	52	115	finally
    //   69	80	115	finally
    //   2	11	120	java/lang/Exception
  }
  
  public b a(Application paramApplication, String paramString)
  {
    String str4 = "COMPROMISED";
    String str3 = "MISSING_SIGFILE";
    ArrayList localArrayList = new ArrayList();
    String str2;
    Object localObject;
    String str1;
    if (paramApplication != null)
    {
      str2 = str3;
      localObject = localArrayList;
      str1 = str4;
      if (b(paramString))
      {
        com.inauth.ndk.a localA = a(paramString, "GET_MALWARE_LIST");
        paramString = str3;
        if (localA != null) {
          paramString = localA.b();
        }
        str2 = paramString;
        localObject = localArrayList;
        str1 = str4;
        if (localA.a() != null)
        {
          str2 = paramString;
          localObject = localArrayList;
          str1 = str4;
          if (!"COMPROMISED".equals(localA.a()))
          {
            localObject = b(paramApplication, localA.a());
            if ((((List)localObject).size() != 0) || ((!paramString.equals("MISSING_SIGFILE")) && (!paramString.equals("DECRYPTION_FAILURE")))) {
              break label177;
            }
            str1 = "COMPROMISED";
            str2 = paramString;
          }
        }
      }
    }
    for (;;)
    {
      paramApplication = new b();
      paramApplication.a(str1);
      paramApplication.b(str2);
      paramApplication.a((List)localObject);
      return paramApplication;
      label177:
      if (((List)localObject).size() == 0)
      {
        str1 = "NO_MALWARE_FOUND";
        str2 = paramString;
      }
      else
      {
        str1 = "MALWARE_FOUND";
        str2 = paramString;
        continue;
        str2 = "SDK_NOT_INITIALIZED";
        localObject = localArrayList;
        str1 = str4;
      }
    }
  }
  
  public String a(String paramString)
  {
    String str2 = "MISSING_SIGFILE";
    String str1 = str2;
    if (b(paramString))
    {
      paramString = a(paramString, "GET_SIGFILE_VERSION");
      str1 = str2;
      if ("MISSING_SIGFILE" != null) {
        str1 = paramString.a();
      }
    }
    return str1;
  }
}
