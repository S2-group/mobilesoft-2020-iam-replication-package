package io.presage.common.profig;

import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.provider.Settings.Secure;
import d.c.a;
import d.g.b.e;
import do.Aveyronnais;
import io.presage.common.if.AbbayedeTimadeuc;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public final class CamembertauCalvados
{
  public static final CamembertauCalvados a = new CamembertauCalvados();
  
  public CamembertauCalvados() {}
  
  public static EcirdelAubrac a(Context paramContext)
  {
    boolean bool2 = false;
    boolean bool3 = true;
    try
    {
      localObject1 = CamembertdeNormandie.a;
      localObject2 = CamembertdeNormandie.a(paramContext);
      localObject1 = ((CamembertdeNormandie.CamembertauCalvados)localObject2).a();
      if (localObject1 != null)
      {
        bool1 = true ^ ((CamembertdeNormandie.CamembertauCalvados)localObject2).b();
        paramContext = (Context)localObject1;
      }
      else
      {
        throw new Exception("androidAdvertisingId is null");
      }
    }
    catch (Exception localException1)
    {
      Object localObject1;
      Object localObject2;
      label88:
      for (;;) {}
    }
    try
    {
      localObject2 = paramContext.getContentResolver();
      localObject1 = Settings.Secure.getString((ContentResolver)localObject2, "advertising_id");
      e.a(localObject1, "Settings.Secure.getStrin…solver, \"advertising_id\")");
      if (Settings.Secure.getInt((ContentResolver)localObject2, "limit_ad_tracking") != 0) {
        break label128;
      }
      bool1 = true;
    }
    catch (Exception localException2)
    {
      break label98;
      bool1 = false;
      if (localException2 == null) {
        break label88;
      }
      paramContext = localException2;
      break label107;
    }
    throw new Exception("aaid is null");
    label98:
    paramContext = b(paramContext);
    bool2 = true;
    bool1 = bool3;
    label107:
    return new EcirdelAubrac(paramContext, bool1, bool2);
  }
  
  /* Error */
  public static String a()
  {
    // Byte code:
    //   0: new 83	java/io/File
    //   3: dup
    //   4: ldc 85
    //   6: invokespecial 86	java/io/File:<init>	(Ljava/lang/String;)V
    //   9: astore_0
    //   10: getstatic 91	d/c/a:a	Ljava/nio/charset/Charset;
    //   13: astore_1
    //   14: new 93	java/util/ArrayList
    //   17: dup
    //   18: invokespecial 94	java/util/ArrayList:<init>	()V
    //   21: astore 4
    //   23: new 96	java/io/BufferedReader
    //   26: dup
    //   27: new 98	java/io/InputStreamReader
    //   30: dup
    //   31: new 100	java/io/FileInputStream
    //   34: dup
    //   35: aload_0
    //   36: invokespecial 103	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   39: aload_1
    //   40: invokespecial 106	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;Ljava/nio/charset/Charset;)V
    //   43: invokespecial 109	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   46: astore_0
    //   47: aload_0
    //   48: instanceof 96
    //   51: ifeq +6 -> 57
    //   54: goto +15 -> 69
    //   57: new 96	java/io/BufferedReader
    //   60: dup
    //   61: aload_0
    //   62: sipush 8192
    //   65: invokespecial 112	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   68: astore_0
    //   69: aconst_null
    //   70: astore_3
    //   71: aload_3
    //   72: astore_1
    //   73: new 114	d/e/d
    //   76: dup
    //   77: aload_0
    //   78: invokespecial 117	d/e/d:<init>	(Ljava/io/BufferedReader;)V
    //   81: astore_2
    //   82: aload_3
    //   83: astore_1
    //   84: aload_2
    //   85: instanceof 119
    //   88: ifeq +6 -> 94
    //   91: goto +14 -> 105
    //   94: aload_3
    //   95: astore_1
    //   96: new 119	d/b/a
    //   99: dup
    //   100: aload_2
    //   101: invokespecial 122	d/b/a:<init>	(Ld/b/b;)V
    //   104: astore_2
    //   105: aload_3
    //   106: astore_1
    //   107: aload_2
    //   108: invokeinterface 127 1 0
    //   113: astore_2
    //   114: aload_3
    //   115: astore_1
    //   116: aload_2
    //   117: invokeinterface 132 1 0
    //   122: ifeq +23 -> 145
    //   125: aload_3
    //   126: astore_1
    //   127: aload 4
    //   129: aload_2
    //   130: invokeinterface 136 1 0
    //   135: checkcast 138	java/lang/String
    //   138: invokevirtual 142	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   141: pop
    //   142: goto -28 -> 114
    //   145: aload_0
    //   146: aconst_null
    //   147: invokestatic 147	d/e/f:a	(Ljava/io/Closeable;Ljava/lang/Throwable;)V
    //   150: aload 4
    //   152: invokeinterface 152 1 0
    //   157: iconst_1
    //   158: ixor
    //   159: ifeq +15 -> 174
    //   162: aload 4
    //   164: iconst_0
    //   165: invokeinterface 158 2 0
    //   170: checkcast 138	java/lang/String
    //   173: areturn
    //   174: aconst_null
    //   175: areturn
    //   176: astore_2
    //   177: goto +8 -> 185
    //   180: astore_2
    //   181: aload_2
    //   182: astore_1
    //   183: aload_2
    //   184: athrow
    //   185: aload_0
    //   186: aload_1
    //   187: invokestatic 147	d/e/f:a	(Ljava/io/Closeable;Ljava/lang/Throwable;)V
    //   190: aload_2
    //   191: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   9	177	0	localObject1	Object
    //   13	174	1	localObject2	Object
    //   81	49	2	localObject3	Object
    //   176	1	2	localObject4	Object
    //   180	11	2	localThrowable	Throwable
    //   70	56	3	localObject5	Object
    //   21	142	4	localArrayList	ArrayList
    // Exception table:
    //   from	to	target	type
    //   73	82	176	finally
    //   84	91	176	finally
    //   96	105	176	finally
    //   107	114	176	finally
    //   116	125	176	finally
    //   127	142	176	finally
    //   183	185	176	finally
    //   73	82	180	java/lang/Throwable
    //   84	91	180	java/lang/Throwable
    //   96	105	180	java/lang/Throwable
    //   107	114	180	java/lang/Throwable
    //   116	125	180	java/lang/Throwable
    //   127	142	180	java/lang/Throwable
  }
  
  public static String b(Context paramContext)
  {
    try
    {
      localObject = a();
      if (localObject == null) {
        break label71;
      }
      if (((CharSequence)localObject).length() != 0) {
        break label201;
      }
      i = 1;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Object localObject;
        label71:
        continue;
        label201:
        int i = 0;
      }
    }
    if (i == 0)
    {
      localObject = ((String)localObject).getBytes(a.a);
      e.a(localObject, "(this as java.lang.String).getBytes(charset)");
      localObject = UUID.nameUUIDFromBytes((byte[])localObject).toString();
      e.a(localObject, "UUID.nameUUIDFromBytes(m…toByteArray()).toString()");
      return localObject;
      localObject = AbbayedeTimadeuc.a;
    }
    for (;;)
    {
      try
      {
        localObject = c(paramContext);
        if (localObject == null) {
          return "00000000-0000-0000-0000-000000000000";
        }
      }
      catch (Exception paramContext)
      {
        long l;
        continue;
      }
      try
      {
        l = paramContext.getPackageManager().getPackageInfo(((ApplicationInfo)localObject).packageName, 128).firstInstallTime;
        paramContext = new StringBuilder();
        paramContext.append(String.valueOf(l));
        paramContext = paramContext.toString();
        localObject = a.a;
        if (paramContext != null)
        {
          paramContext = paramContext.getBytes((Charset)localObject);
          e.a(paramContext, "(this as java.lang.String).getBytes(charset)");
          paramContext = UUID.nameUUIDFromBytes(paramContext).toString();
          e.a(paramContext, "UUID.nameUUIDFromBytes((…toByteArray()).toString()");
          return paramContext;
        }
        throw new Aveyronnais("null cannot be cast to non-null type java.lang.String");
      }
      catch (Exception paramContext) {}
    }
    paramContext = AbbayedeTimadeuc.a;
    return "00000000-0000-0000-0000-000000000000";
    paramContext = AbbayedeTimadeuc.a;
    return "00000000-0000-0000-0000-000000000000";
  }
  
  public static ApplicationInfo c(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    paramContext = paramContext.getPackageManager();
    if (paramContext == null) {
      return null;
    }
    Object localObject = paramContext.getInstalledApplications(128);
    paramContext = new ArrayList();
    if (((List)localObject).size() == 0) {
      return null;
    }
    e.a(localObject, "apps");
    int i = 0;
    while (i < ((List)localObject).size())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)((List)localObject).get(i);
      if (((0x1 & localApplicationInfo.flags) != 0) && (localApplicationInfo.packageName != null)) {
        paramContext.add(localApplicationInfo);
      }
      i += 1;
    }
    localObject = CamembertauCalvados.a;
    if (paramContext.size() > 1) {
      Collections.sort(paramContext, (Comparator)localObject);
    }
    return (ApplicationInfo)paramContext.get(0);
  }
  
  public static final class CamembertauCalvados<T>
    implements Comparator<ApplicationInfo>
  {
    public static final CamembertauCalvados a = new CamembertauCalvados();
    
    public CamembertauCalvados() {}
    
    public static int a(ApplicationInfo paramApplicationInfo1, ApplicationInfo paramApplicationInfo2)
    {
      paramApplicationInfo1 = paramApplicationInfo1.packageName;
      paramApplicationInfo2 = paramApplicationInfo2.packageName;
      e.a(paramApplicationInfo2, "rhs.packageName");
      return paramApplicationInfo1.compareTo(paramApplicationInfo2);
    }
  }
}
