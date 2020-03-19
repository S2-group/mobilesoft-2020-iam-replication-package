import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.drivergenius.screenrecorder.RecorderApplication;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class qo
{
  public static int a(Context paramContext)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      return i;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return 0;
  }
  
  public static String a()
  {
    try
    {
      String str = RecorderApplication.a().getPackageName();
      return str;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return "";
  }
  
  /* Error */
  public static String a(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 14	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   4: aload_0
    //   5: invokevirtual 18	android/content/Context:getPackageName	()Ljava/lang/String;
    //   8: iconst_0
    //   9: invokevirtual 24	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   12: getfield 48	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   15: astore_0
    //   16: aload_0
    //   17: ifnull +12 -> 29
    //   20: aload_0
    //   21: astore_1
    //   22: aload_0
    //   23: invokevirtual 54	java/lang/String:length	()I
    //   26: ifgt +6 -> 32
    //   29: ldc 43
    //   31: astore_1
    //   32: aload_1
    //   33: areturn
    //   34: astore_1
    //   35: ldc 43
    //   37: astore_0
    //   38: aload_1
    //   39: invokevirtual 34	java/lang/Exception:printStackTrace	()V
    //   42: aload_0
    //   43: areturn
    //   44: astore_1
    //   45: goto -7 -> 38
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	48	0	paramContext	Context
    //   21	12	1	localObject	Object
    //   34	5	1	localException1	Exception
    //   44	1	1	localException2	Exception
    // Exception table:
    //   from	to	target	type
    //   0	16	34	java/lang/Exception
    //   22	29	44	java/lang/Exception
  }
  
  public static List<ApplicationInfo> a(PackageManager paramPackageManager, int paramInt)
  {
    Object localObject = new ArrayList();
    if (paramPackageManager != null) {}
    for (paramPackageManager = new ArrayList(paramPackageManager.getInstalledApplications(paramInt));; paramPackageManager = (PackageManager)localObject)
    {
      localObject = paramPackageManager.iterator();
      while (((Iterator)localObject).hasNext()) {
        if ("com.drivergenius.screenrecorder".equals(((ApplicationInfo)((Iterator)localObject).next()).packageName)) {
          ((Iterator)localObject).remove();
        }
      }
      return paramPackageManager;
    }
  }
  
  public static int b(Context paramContext)
  {
    String str2 = nm.a(paramContext);
    String str1 = str2;
    try
    {
      if (TextUtils.isEmpty(str2))
      {
        str1 = b(paramContext);
        if (!TextUtils.isEmpty(str1)) {
          break label61;
        }
        str2 = rv.a(paramContext, "cn");
        str1 = str2;
        if (!TextUtils.isEmpty(str2))
        {
          nm.a(paramContext, str2);
          str1 = str2;
        }
      }
      while (!TextUtils.isEmpty(str1))
      {
        return Integer.parseInt(str1);
        label61:
        nm.a(paramContext, str1);
      }
      return 0;
    }
    catch (NumberFormatException paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  /* Error */
  private static String b(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 137	android/content/Context:getApplicationInfo	()Landroid/content/pm/ApplicationInfo;
    //   4: getfield 140	android/content/pm/ApplicationInfo:sourceDir	Ljava/lang/String;
    //   7: astore_0
    //   8: new 142	java/util/zip/ZipFile
    //   11: dup
    //   12: aload_0
    //   13: invokespecial 145	java/util/zip/ZipFile:<init>	(Ljava/lang/String;)V
    //   16: astore_3
    //   17: aload_3
    //   18: astore_0
    //   19: aload_3
    //   20: invokevirtual 149	java/util/zip/ZipFile:entries	()Ljava/util/Enumeration;
    //   23: astore 4
    //   25: aload_3
    //   26: astore_0
    //   27: aload 4
    //   29: invokeinterface 154 1 0
    //   34: ifeq +161 -> 195
    //   37: aload_3
    //   38: astore_0
    //   39: aload 4
    //   41: invokeinterface 157 1 0
    //   46: checkcast 159	java/util/zip/ZipEntry
    //   49: invokevirtual 162	java/util/zip/ZipEntry:getName	()Ljava/lang/String;
    //   52: astore_2
    //   53: aload_3
    //   54: astore_0
    //   55: aload_2
    //   56: ldc -92
    //   58: invokevirtual 168	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   61: istore_1
    //   62: iload_1
    //   63: ifeq -38 -> 25
    //   66: aload_2
    //   67: astore_0
    //   68: aload_3
    //   69: ifnull +9 -> 78
    //   72: aload_3
    //   73: invokevirtual 171	java/util/zip/ZipFile:close	()V
    //   76: aload_2
    //   77: astore_0
    //   78: aload_0
    //   79: ldc -83
    //   81: invokevirtual 177	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   84: astore_3
    //   85: ldc 43
    //   87: astore_2
    //   88: aload_2
    //   89: astore_0
    //   90: aload_3
    //   91: ifnull +18 -> 109
    //   94: aload_2
    //   95: astore_0
    //   96: aload_3
    //   97: arraylength
    //   98: iconst_3
    //   99: if_icmplt +10 -> 109
    //   102: aload_3
    //   103: iconst_2
    //   104: aaload
    //   105: invokevirtual 180	java/lang/String:toString	()Ljava/lang/String;
    //   108: astore_0
    //   109: aload_0
    //   110: areturn
    //   111: astore_0
    //   112: aload_0
    //   113: invokevirtual 181	java/io/IOException:printStackTrace	()V
    //   116: aload_2
    //   117: astore_0
    //   118: goto -40 -> 78
    //   121: astore 4
    //   123: aconst_null
    //   124: astore_2
    //   125: aload_2
    //   126: astore_0
    //   127: aload 4
    //   129: invokevirtual 181	java/io/IOException:printStackTrace	()V
    //   132: aload_2
    //   133: ifnull +56 -> 189
    //   136: aload_2
    //   137: invokevirtual 171	java/util/zip/ZipFile:close	()V
    //   140: ldc 43
    //   142: astore_0
    //   143: goto -65 -> 78
    //   146: astore_0
    //   147: aload_0
    //   148: invokevirtual 181	java/io/IOException:printStackTrace	()V
    //   151: ldc 43
    //   153: astore_0
    //   154: goto -76 -> 78
    //   157: astore_2
    //   158: aconst_null
    //   159: astore_0
    //   160: aload_0
    //   161: ifnull +7 -> 168
    //   164: aload_0
    //   165: invokevirtual 171	java/util/zip/ZipFile:close	()V
    //   168: aload_2
    //   169: athrow
    //   170: astore_0
    //   171: aload_0
    //   172: invokevirtual 181	java/io/IOException:printStackTrace	()V
    //   175: goto -7 -> 168
    //   178: astore_2
    //   179: goto -19 -> 160
    //   182: astore 4
    //   184: aload_3
    //   185: astore_2
    //   186: goto -61 -> 125
    //   189: ldc 43
    //   191: astore_0
    //   192: goto -114 -> 78
    //   195: ldc 43
    //   197: astore_2
    //   198: goto -132 -> 66
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	201	0	paramContext	Context
    //   61	2	1	bool	boolean
    //   52	85	2	str	String
    //   157	12	2	localObject1	Object
    //   178	1	2	localObject2	Object
    //   185	13	2	localObject3	Object
    //   16	169	3	localObject4	Object
    //   23	17	4	localEnumeration	java.util.Enumeration
    //   121	7	4	localIOException1	java.io.IOException
    //   182	1	4	localIOException2	java.io.IOException
    // Exception table:
    //   from	to	target	type
    //   72	76	111	java/io/IOException
    //   8	17	121	java/io/IOException
    //   136	140	146	java/io/IOException
    //   8	17	157	finally
    //   164	168	170	java/io/IOException
    //   19	25	178	finally
    //   27	37	178	finally
    //   39	53	178	finally
    //   55	62	178	finally
    //   127	132	178	finally
    //   19	25	182	java/io/IOException
    //   27	37	182	java/io/IOException
    //   39	53	182	java/io/IOException
    //   55	62	182	java/io/IOException
  }
  
  public static List<ApplicationInfo> b(PackageManager paramPackageManager, int paramInt)
  {
    paramPackageManager = a(paramPackageManager, paramInt);
    Iterator localIterator = paramPackageManager.iterator();
    while (localIterator.hasNext()) {
      if ((((ApplicationInfo)localIterator.next()).flags & 0x1) == 1) {
        localIterator.remove();
      }
    }
    return paramPackageManager;
  }
}
