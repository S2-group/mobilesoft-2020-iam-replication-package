import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.text.TextUtils;
import com.verizon.mips.selfdiagnostic.dto.Data;
import com.verizon.mips.selfdiagnostic.dto.HighRiskJsonDto;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public class ddj
{
  private HighRiskJsonDto bdK = new HighRiskJsonDto();
  
  public ddj() {}
  
  private ArrayList<ctm> a(Context paramContext, List<ddm> paramList, List<Data> paramList1)
  {
    ArrayList localArrayList = new ArrayList();
    HashMap localHashMap = new HashMap();
    Object localObject2 = "";
    Object localObject1 = new ArrayList();
    Iterator localIterator = paramList1.iterator();
    paramList1 = (List<Data>)localObject2;
    while (localIterator.hasNext())
    {
      Data localData = (Data)localIterator.next();
      if ((localData != null) && (localData.getPackageName() != null))
      {
        localObject2 = paramList1;
        if (TextUtils.isEmpty(paramList1))
        {
          localObject2 = localData.getPackageName();
          localObject1 = new ArrayList();
        }
        if (!((String)localObject2).equalsIgnoreCase(localData.getPackageName()))
        {
          Collections.sort((List)localObject1, new ddk(this));
          localHashMap.put(localObject2, localObject1);
          localObject1 = new ArrayList();
          paramList1 = localData.getPackageName();
          ((ArrayList)localObject1).add(localData);
        }
        else
        {
          ((ArrayList)localObject1).add(localData);
          paramList1 = (List<Data>)localObject2;
        }
      }
    }
    if ((!TextUtils.isEmpty(paramList1)) && (localObject1 != null))
    {
      Collections.sort((List)localObject1, new ddl(this));
      localHashMap.put(paramList1, localObject1);
    }
    paramList = paramList.iterator();
    label311:
    label550:
    label1280:
    label1582:
    label1753:
    label1757:
    for (;;)
    {
      int i;
      if (paramList.hasNext())
      {
        localObject1 = (ddm)paramList.next();
        paramList1 = localHashMap.entrySet().iterator();
        if (!paramList1.hasNext()) {
          continue;
        }
        localObject2 = (Map.Entry)paramList1.next();
        if (!((String)((Map.Entry)localObject2).getKey()).equalsIgnoreCase(((ddm)localObject1).packageName)) {
          break;
        }
        paramList1 = (ArrayList)localHashMap.get(((Map.Entry)localObject2).getKey());
        i = 0;
        if (i >= paramList1.size()) {
          break label1753;
        }
        if ((((Data)paramList1.get(i)).getVersion() != null) && (((Data)paramList1.get(i)).getVersion().equalsIgnoreCase(((ddm)localObject1).versionName))) {
          if ((!((Data)paramList1.get(i)).getStatus().contains("Removed")) && (((Data)paramList1.get(i)).getName() != null) && (((Data)paramList1.get(i)).getDescription() != null) && (((Data)paramList1.get(i)).getRiskRating() != null))
          {
            localObject1 = new ctm();
            ((ctm)localObject1).ck(((Data)paramList1.get(i)).getPackageName());
            ((ctm)localObject1).cm(((Data)paramList1.get(i)).getName());
            ((ctm)localObject1).cl(((Data)paramList1.get(i)).getDescription());
            if ((((Data)paramList1.get(i)).getRiskRating().contains("1")) && (((Data)paramList1.get(i)).getRiskRating().contains("2")) && (((Data)paramList1.get(i)).getRiskRating().contains("3")))
            {
              ((ctm)localObject1).N(3.0F);
              localArrayList.add(localObject1);
            }
          }
          else
          {
            ddn.d("getStatus " + ((Data)paramList1.get(i)).getStatus());
            ddn.d("getRiskRating " + ((Data)paramList1.get(i)).getRiskRating());
            ddn.d("getDescription " + ((Data)paramList1.get(i)).getDescription());
            ddn.d("getPackageName " + ((Data)paramList1.get(i)).getPackageName());
            ddn.d("getName " + ((Data)paramList1.get(i)).getName());
            ddn.d("getVersion " + ((Data)paramList1.get(i)).getVersion());
            ddn.d("getTestTime " + ((Data)paramList1.get(i)).getTestTime());
          }
        }
      }
      for (boolean bool = true;; bool = false)
      {
        if (bool) {
          break label1757;
        }
        ddn.d("versionFound " + bool);
        ddn.d("getStatus " + ((Data)paramList1.get(0)).getStatus());
        ddn.d("getRiskRating " + ((Data)paramList1.get(0)).getRiskRating());
        ddn.d("getDescription " + ((Data)paramList1.get(0)).getDescription());
        ddn.d("getPackageName " + ((Data)paramList1.get(0)).getPackageName());
        ddn.d("getName " + ((Data)paramList1.get(0)).getName());
        ddn.d("getVersion " + ((Data)paramList1.get(0)).getVersion());
        ddn.d("getTestTime " + ((Data)paramList1.get(0)).getTestTime());
        try
        {
          long l = new File(paramContext.getPackageManager().getApplicationInfo(((Data)paramList1.get(0)).getPackageName(), 0).sourceDir).lastModified();
          if ((Long.parseLong(((Data)paramList1.get(0)).getTestTime()) >= l) || (((Data)paramList1.get(0)).getStatus().contains("Removed")) || (((Data)paramList1.get(0)).getName() == null) || (((Data)paramList1.get(0)).getDescription() == null) || (((Data)paramList1.get(0)).getRiskRating() == null)) {
            break;
          }
          localObject1 = new ctm();
          ((ctm)localObject1).ck(((Data)paramList1.get(0)).getPackageName());
          ((ctm)localObject1).cm(((Data)paramList1.get(0)).getName());
          ((ctm)localObject1).cl(((Data)paramList1.get(0)).getDescription());
          if ((!((Data)paramList1.get(0)).getRiskRating().contains("1")) || (!((Data)paramList1.get(0)).getRiskRating().contains("2")) || (!((Data)paramList1.get(0)).getRiskRating().contains("3"))) {
            break label1582;
          }
          ((ctm)localObject1).N(3.0F);
          localArrayList.add(localObject1);
        }
        catch (Exception localException) {}
        if ((((Data)paramList1.get(0)).getStatus().contains("Removed")) || (((Data)paramList1.get(0)).getName() == null) || (((Data)paramList1.get(0)).getDescription() == null) || (((Data)paramList1.get(0)).getRiskRating() == null)) {
          break;
        }
        ctm localCtm = new ctm();
        localCtm.ck(((Data)paramList1.get(0)).getPackageName());
        localCtm.cm(((Data)paramList1.get(0)).getName());
        localCtm.cl(((Data)paramList1.get(0)).getDescription());
        if ((((Data)paramList1.get(0)).getRiskRating().contains("1")) && (((Data)paramList1.get(0)).getRiskRating().contains("2")) && (((Data)paramList1.get(0)).getRiskRating().contains("3"))) {
          localCtm.N(3.0F);
        }
        for (;;)
        {
          localArrayList.add(localCtm);
          break;
          if ((((Data)paramList1.get(i)).getRiskRating().contains("1")) && (((Data)paramList1.get(i)).getRiskRating().contains("2")))
          {
            localCtm.N(2.0F);
            break label550;
          }
          if (((Data)paramList1.get(i)).getRiskRating().contains("1"))
          {
            localCtm.N(1.0F);
            break label550;
          }
          localCtm.N(0.0F);
          break label550;
          i += 1;
          break label311;
          if ((((Data)paramList1.get(0)).getRiskRating().contains("1")) && (((Data)paramList1.get(0)).getRiskRating().contains("2")))
          {
            localCtm.N(2.0F);
            break label1280;
          }
          if (((Data)paramList1.get(0)).getRiskRating().contains("1"))
          {
            localCtm.N(1.0F);
            break label1280;
          }
          localCtm.N(0.0F);
          break label1280;
          if ((((Data)paramList1.get(0)).getRiskRating().contains("1")) && (((Data)paramList1.get(0)).getRiskRating().contains("2"))) {
            localCtm.N(2.0F);
          } else if (((Data)paramList1.get(0)).getRiskRating().contains("1")) {
            localCtm.N(1.0F);
          } else {
            localCtm.N(0.0F);
          }
        }
        return localArrayList;
      }
    }
  }
  
  /* Error */
  private List<ddm> bi(Context paramContext)
  {
    // Byte code:
    //   0: new 22	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 23	java/util/ArrayList:<init>	()V
    //   7: astore 5
    //   9: aload_1
    //   10: invokevirtual 205	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   13: astore 6
    //   15: aload 6
    //   17: iconst_0
    //   18: invokevirtual 238	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   21: invokeinterface 34 1 0
    //   26: astore 7
    //   28: aload 7
    //   30: invokeinterface 40 1 0
    //   35: ifeq +147 -> 182
    //   38: new 86	ddm
    //   41: dup
    //   42: aload_0
    //   43: invokespecial 239	ddm:<init>	(Lddj;)V
    //   46: astore 8
    //   48: aload 7
    //   50: invokeinterface 44 1 0
    //   55: checkcast 241	android/content/pm/PackageInfo
    //   58: astore 4
    //   60: aload 4
    //   62: getfield 242	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   65: ifnull +103 -> 168
    //   68: aload 4
    //   70: getfield 242	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   73: astore_3
    //   74: aload_3
    //   75: ldc -12
    //   77: invokevirtual 62	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   80: ifne -52 -> 28
    //   83: aload_3
    //   84: aload_1
    //   85: invokestatic 250	ddu:c	(Ljava/lang/String;Landroid/content/Context;)Ljava/lang/String;
    //   88: astore 9
    //   90: aload 9
    //   92: ldc -4
    //   94: invokevirtual 255	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   97: ifne -69 -> 28
    //   100: aload 9
    //   102: ldc_w 257
    //   105: invokevirtual 255	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   108: istore_2
    //   109: iload_2
    //   110: ifne -82 -> 28
    //   113: aload 6
    //   115: aload_3
    //   116: iconst_0
    //   117: invokevirtual 211	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   120: ifnull -92 -> 28
    //   123: aload 4
    //   125: getfield 258	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   128: ifnull +46 -> 174
    //   131: aload 4
    //   133: getfield 258	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   136: astore 4
    //   138: aload 8
    //   140: aload_3
    //   141: putfield 102	ddm:packageName	Ljava/lang/String;
    //   144: aload 8
    //   146: aload 4
    //   148: putfield 119	ddm:versionName	Ljava/lang/String;
    //   151: aload 5
    //   153: aload 8
    //   155: invokeinterface 259 2 0
    //   160: pop
    //   161: goto -133 -> 28
    //   164: astore_3
    //   165: goto -137 -> 28
    //   168: ldc -12
    //   170: astore_3
    //   171: goto -97 -> 74
    //   174: ldc -12
    //   176: astore 4
    //   178: goto -40 -> 138
    //   181: astore_1
    //   182: aload 5
    //   184: areturn
    //   185: astore_3
    //   186: goto -158 -> 28
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	189	0	this	ddj
    //   0	189	1	paramContext	Context
    //   108	2	2	bool	boolean
    //   73	68	3	str1	String
    //   164	1	3	localNameNotFoundException	android.content.pm.PackageManager.NameNotFoundException
    //   170	1	3	str2	String
    //   185	1	3	localException	Exception
    //   58	119	4	localObject	Object
    //   7	176	5	localArrayList	ArrayList
    //   13	101	6	localPackageManager	PackageManager
    //   26	23	7	localIterator	Iterator
    //   46	108	8	localDdm	ddm
    //   88	13	9	str3	String
    // Exception table:
    //   from	to	target	type
    //   113	138	164	android/content/pm/PackageManager$NameNotFoundException
    //   138	161	164	android/content/pm/PackageManager$NameNotFoundException
    //   9	28	181	java/lang/Exception
    //   28	74	181	java/lang/Exception
    //   74	109	181	java/lang/Exception
    //   113	138	185	java/lang/Exception
    //   138	161	185	java/lang/Exception
  }
  
  private HighRiskJsonDto bj(Context paramContext)
  {
    try
    {
      String str2 = ddu.w(paramContext, "highriskjson.txt");
      String str1;
      if (str2 != null)
      {
        str1 = str2;
        if (str2.length() != 0) {}
      }
      else
      {
        str1 = d(paramContext.getResources().openRawResource(daf.highriskjson));
        ddn.d("highRiskJson == from raw " + str1);
      }
      ddn.d("highRiskJson " + str1);
      this.bdK = ((HighRiskJsonDto)ddu.a(str1, HighRiskJsonDto.class));
      ddn.d("highRiskJsonDto getStatusCode" + this.bdK.getStatusCode());
      ddn.d("highRiskJsonDto getData size " + this.bdK.getData().size());
    }
    catch (Exception localException)
    {
      for (;;)
      {
        try
        {
          this.bdK = new HighRiskJsonDto();
          paramContext = d(paramContext.getResources().openRawResource(daf.highriskjson));
          this.bdK = ((HighRiskJsonDto)ddu.a(paramContext, HighRiskJsonDto.class));
          ddn.d("highRiskJson exception == from raw " + paramContext);
        }
        catch (Exception paramContext)
        {
          this.bdK = null;
        }
      }
    }
    return this.bdK;
  }
  
  private String d(InputStream paramInputStream)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    byte[] arrayOfByte = new byte['Ѐ'];
    try
    {
      for (;;)
      {
        int i = paramInputStream.read(arrayOfByte);
        if (i == -1) {
          break;
        }
        localByteArrayOutputStream.write(arrayOfByte, 0, i);
      }
      return localByteArrayOutputStream.toString();
    }
    catch (IOException paramInputStream) {}
    for (;;)
    {
      localByteArrayOutputStream.close();
      paramInputStream.close();
    }
  }
  
  public ArrayList<ctm> bh(Context paramContext)
  {
    ArrayList localArrayList2 = new ArrayList();
    List localList = bi(paramContext);
    ArrayList localArrayList1 = localArrayList2;
    if (localList != null)
    {
      localArrayList1 = localArrayList2;
      if (localList.size() > 0)
      {
        HighRiskJsonDto localHighRiskJsonDto = bj(paramContext);
        localArrayList1 = localArrayList2;
        if (localHighRiskJsonDto != null)
        {
          localArrayList1 = localArrayList2;
          if (localHighRiskJsonDto.getData() != null)
          {
            localArrayList1 = localArrayList2;
            if (localHighRiskJsonDto.getData().size() > 0) {
              localArrayList1 = a(paramContext, localList, localHighRiskJsonDto.getData());
            }
          }
        }
      }
    }
    return localArrayList1;
  }
}
