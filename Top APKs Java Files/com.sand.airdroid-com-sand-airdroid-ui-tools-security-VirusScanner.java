package com.sand.airdroid.ui.tools.security;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.TextUtils;
import com.sand.airdroid.base.OSHelper;
import com.sand.airdroid.database.AppMd5Cache;
import com.sand.airdroid.database.AppMd5CacheDao.Properties;
import com.trustlook.antivirus.utils.AppInfo;
import com.trustlook.cloudscan.Scanner;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.query.DeleteQuery;
import de.greenrobot.dao.query.QueryBuilder;
import de.greenrobot.dao.query.WhereCondition;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.inject.Inject;

public class VirusScanner
{
  private static final String d = "7tredj6d2fakyg06jpyf44wlmkmq8z3b";
  @Inject
  OSHelper a;
  @Inject
  SecurityAppDbOper b;
  private final String c = "VirusScanner";
  private final Scanner e = new Scanner("7tredj6d2fakyg06jpyf44wlmkmq8z3b");
  private Date f = null;
  private int g;
  
  @Inject
  public VirusScanner() {}
  
  @TargetApi(8)
  private AppInfo a(PackageInfo paramPackageInfo, PackageManager paramPackageManager)
  {
    Object localObject;
    if ((paramPackageInfo == null) || (paramPackageInfo.applicationInfo == null))
    {
      paramPackageManager = "unknown";
      localObject = (String)paramPackageManager;
      if (paramPackageInfo != null) {
        break label222;
      }
      paramPackageManager = "unknown";
      label26:
      paramPackageManager = new AppInfo((String)localObject, paramPackageManager);
      if ((paramPackageInfo != null) && (paramPackageInfo.applicationInfo != null)) {
        paramPackageManager.c(paramPackageInfo.applicationInfo.publicSourceDir);
      }
      if (paramPackageManager.i() != null)
      {
        localObject = new File(paramPackageManager.i());
        if (!this.b.c(paramPackageManager.k())) {
          break label230;
        }
        paramPackageManager.d(((AppMd5Cache)this.b.b(paramPackageManager.k()).get(0)).e());
        label118:
        paramPackageManager.a(((File)localObject).length());
      }
      paramPackageManager.b(paramPackageInfo.versionName);
      paramPackageManager.b(paramPackageInfo.lastUpdateTime);
      paramPackageManager.a(paramPackageInfo.requestedPermissions);
      if (((paramPackageInfo.applicationInfo.flags & 0x80) == 0) && ((paramPackageInfo.applicationInfo.flags & 0x1) == 0)) {
        break label261;
      }
      paramPackageManager.a(true);
    }
    for (;;)
    {
      if (!this.b.c(paramPackageManager.k())) {
        this.b.a(paramPackageManager, this.f);
      }
      return paramPackageManager;
      paramPackageManager = paramPackageInfo.applicationInfo.loadLabel(paramPackageManager);
      break;
      label222:
      paramPackageManager = paramPackageInfo.packageName;
      break label26;
      label230:
      String str = a((File)localObject, "MD5");
      if (TextUtils.isEmpty(str)) {
        break label118;
      }
      paramPackageManager.d(str.toUpperCase(Locale.US));
      break label118;
      label261:
      paramPackageManager.a(false);
    }
  }
  
  private static String a(File paramFile)
  {
    return a(paramFile, "MD5");
  }
  
  /* Error */
  private static String a(File paramFile, String paramString)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic 173	java/security/MessageDigest:getInstance	(Ljava/lang/String;)Ljava/security/MessageDigest;
    //   4: astore_1
    //   5: new 175	java/io/FileInputStream
    //   8: dup
    //   9: aload_0
    //   10: invokespecial 178	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   13: astore_0
    //   14: sipush 8192
    //   17: newarray byte
    //   19: astore_3
    //   20: aload_0
    //   21: aload_3
    //   22: invokevirtual 182	java/io/FileInputStream:read	([B)I
    //   25: istore_2
    //   26: iload_2
    //   27: ifle +32 -> 59
    //   30: aload_1
    //   31: aload_3
    //   32: iconst_0
    //   33: iload_2
    //   34: invokevirtual 186	java/security/MessageDigest:update	([BII)V
    //   37: goto -17 -> 20
    //   40: astore_1
    //   41: new 188	java/lang/RuntimeException
    //   44: dup
    //   45: ldc -66
    //   47: aload_1
    //   48: invokespecial 193	java/lang/RuntimeException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   51: athrow
    //   52: astore_1
    //   53: aload_0
    //   54: invokevirtual 196	java/io/FileInputStream:close	()V
    //   57: aload_1
    //   58: athrow
    //   59: ldc -58
    //   61: iconst_1
    //   62: anewarray 4	java/lang/Object
    //   65: dup
    //   66: iconst_0
    //   67: new 200	java/math/BigInteger
    //   70: dup
    //   71: iconst_1
    //   72: aload_1
    //   73: invokevirtual 204	java/security/MessageDigest:digest	()[B
    //   76: invokespecial 207	java/math/BigInteger:<init>	(I[B)V
    //   79: bipush 16
    //   81: invokevirtual 211	java/math/BigInteger:toString	(I)Ljava/lang/String;
    //   84: aastore
    //   85: invokestatic 215	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   88: bipush 32
    //   90: bipush 48
    //   92: invokevirtual 219	java/lang/String:replace	(CC)Ljava/lang/String;
    //   95: getstatic 155	java/util/Locale:US	Ljava/util/Locale;
    //   98: invokevirtual 159	java/lang/String:toUpperCase	(Ljava/util/Locale;)Ljava/lang/String;
    //   101: astore_1
    //   102: aload_0
    //   103: invokevirtual 196	java/io/FileInputStream:close	()V
    //   106: aload_1
    //   107: areturn
    //   108: astore_0
    //   109: new 221	java/lang/StringBuilder
    //   112: dup
    //   113: ldc -33
    //   115: invokespecial 224	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   118: aload_0
    //   119: invokevirtual 228	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   122: pop
    //   123: aload_1
    //   124: areturn
    //   125: astore_0
    //   126: new 221	java/lang/StringBuilder
    //   129: dup
    //   130: ldc -33
    //   132: invokespecial 224	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   135: aload_0
    //   136: invokevirtual 228	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   139: pop
    //   140: goto -83 -> 57
    //   143: astore_0
    //   144: aconst_null
    //   145: areturn
    //   146: astore_0
    //   147: aconst_null
    //   148: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	149	0	paramFile	File
    //   0	149	1	paramString	String
    //   25	9	2	i	int
    //   19	13	3	arrayOfByte	byte[]
    // Exception table:
    //   from	to	target	type
    //   20	26	40	java/io/IOException
    //   30	37	40	java/io/IOException
    //   59	102	40	java/io/IOException
    //   20	26	52	finally
    //   30	37	52	finally
    //   41	52	52	finally
    //   59	102	52	finally
    //   102	106	108	java/io/IOException
    //   53	57	125	java/io/IOException
    //   0	5	143	java/security/NoSuchAlgorithmException
    //   5	14	146	java/io/FileNotFoundException
  }
  
  private void a(AppInfo paramAppInfo)
  {
    if (!this.b.c(paramAppInfo.k())) {
      this.b.a(paramAppInfo, this.f);
    }
  }
  
  private static String b(File paramFile)
  {
    return a(paramFile, "SHA1");
  }
  
  private List<AppInfo> b(Context paramContext, SecurityScanListener paramSecurityScanListener)
  {
    localArrayList = new ArrayList();
    try
    {
      PackageManager localPackageManager = paramContext.getPackageManager();
      Object localObject1 = localPackageManager.getInstalledPackages(0);
      this.f = new Date();
      this.g = (((List)localObject1).size() - 1);
      if ((localObject1 != null) && (this.g > 0))
      {
        localObject1 = ((List)localObject1).iterator();
        int i = 0;
        while (((Iterator)localObject1).hasNext())
        {
          Object localObject2 = (PackageInfo)((Iterator)localObject1).next();
          if (!((PackageInfo)localObject2).packageName.equals(paramContext.getPackageName()))
          {
            if (paramSecurityScanListener.b)
            {
              new StringBuilder("scanner isStop : i :").append(i).append(" count :").append(this.g);
              return null;
            }
            localObject2 = a((PackageInfo)localObject2, localPackageManager);
            i += 1;
            paramSecurityScanListener.a((AppInfo)localObject2, i, this.g);
            localArrayList.add(localObject2);
          }
        }
      }
      return localArrayList;
    }
    catch (RuntimeException paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  private List<AppInfo> b(Context paramContext, String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    try
    {
      paramContext = paramContext.getPackageManager();
      localArrayList.add(a(paramContext.getPackageInfo(paramString, 0), paramContext));
      return localArrayList;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return localArrayList;
  }
  
  public final List<AppInfo> a(Context paramContext, String paramString)
  {
    paramContext = b(paramContext, paramString);
    if (paramContext.size() > 0)
    {
      paramString = this.b;
      String str = ((AppInfo)paramContext.get(0)).k();
      QueryBuilder.a(paramString.d).a(AppMd5CacheDao.Properties.PackageName.a(str), new WhereCondition[0]).b().b();
      Scanner.a(paramContext, this.e.a(this.a.e(), paramContext));
      return paramContext;
    }
    return null;
  }
  
  public final void a(Context paramContext, SecurityScanListener paramSecurityScanListener)
  {
    paramContext = b(paramContext, paramSecurityScanListener);
    if (paramContext == null) {}
    while (!paramSecurityScanListener.a()) {
      return;
    }
    Scanner.a(paramContext, this.e.a(this.a.e(), paramContext));
    paramSecurityScanListener.a(paramContext);
  }
}
