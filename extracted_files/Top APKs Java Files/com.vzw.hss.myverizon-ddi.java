import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public class ddi
{
  private HashMap<String, List<String>> bdI = new HashMap();
  private List<String> bdJ = new ArrayList();
  
  public ddi()
  {
    Iu();
  }
  
  private ArrayList<String> A(List<String> paramList)
  {
    ArrayList localArrayList = new ArrayList();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      String str = (String)paramList.next();
      Iterator localIterator = this.bdJ.iterator();
      while (localIterator.hasNext())
      {
        String[] arrayOfString = ((String)localIterator.next()).split(":");
        if (arrayOfString[1].equalsIgnoreCase(str))
        {
          ddn.d("apppermission " + str);
          if (!localArrayList.contains(arrayOfString[0])) {
            localArrayList.add(arrayOfString[0]);
          }
        }
      }
    }
    return localArrayList;
  }
  
  private void Iu()
  {
    this.bdJ.add("CALENDAR:android.permission.READ_CALENDAR");
    this.bdJ.add("CALENDAR:android.permission.WRITE_CALENDAR");
    this.bdJ.add("PHONE:android.permission.READ_CALL_LOG");
    this.bdJ.add("PHONE:android.permission.READ_PHONE_STATE");
    this.bdJ.add("PHONE:android.permission.PROCESS_OUTGOING_CALLS");
    this.bdJ.add("PHONE:android.permission.WRITE_CALL_LOG");
    this.bdJ.add("PHONE:android.permission.CALL_PHONE");
    this.bdJ.add("PHONE:com.android.voicemail.permission.ADD_VOICEMAIL");
    this.bdJ.add("CONTACTS:android.permission.WRITE_CONTACTS");
    this.bdJ.add("CONTACTS:android.permission.GET_ACCOUNTS");
    this.bdJ.add("CONTACTS:android.permission.READ_CONTACTS");
    this.bdJ.add("STORAGE:android.permission.READ_EXTERNAL_STORAGE");
    this.bdJ.add("STORAGE:android.permission.WRITE_EXTERNAL_STORAGE");
    this.bdJ.add("SMS:android.permission.READ_SMS");
    this.bdJ.add("SMS:android.permission.RECEIVE_MMS");
    this.bdJ.add("SMS:android.permission.RECEIVE_SMS");
    this.bdJ.add("SMS:android.permission.RECEIVE_WAP_PUSH");
    this.bdJ.add("SMS:android.permission.SEND_SMS");
    this.bdJ.add("SMS:android.permission.USE_SIP");
    this.bdJ.add("MICROPHONE:android.permission.RECORD_AUDIO");
    this.bdJ.add("CAMERA:android.permission.CAMERA");
    this.bdJ.add("SENSORS:android.permission.BODY_SENSORS");
    this.bdJ.add("LOCATION:android.permission.ACCESS_FINE_LOCATION");
    this.bdJ.add("LOCATION:android.permission.ACCESS_COARSE_LOCATION");
  }
  
  private HashMap<String, List<String>> Iv()
  {
    HashMap localHashMap = new HashMap();
    ddn.d("compareWithDangerousPermissions 333 " + this.bdI.size());
    Iterator localIterator = this.bdI.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Object localObject = (Map.Entry)localIterator.next();
      String str = (String)((Map.Entry)localObject).getKey();
      ddn.d("keyAsPackageName " + str);
      localObject = (List)((Map.Entry)localObject).getValue();
      ArrayList localArrayList = A((List)localObject);
      if ((localObject != null) && (((List)localObject).size() > 0) && (localArrayList != null) && (localArrayList.size() > 0)) {
        localHashMap.put(str, localArrayList);
      }
    }
    return localHashMap;
  }
  
  private List<String> a(ApplicationInfo paramApplicationInfo, Context paramContext)
  {
    try
    {
      paramApplicationInfo = new ArrayList(Arrays.asList(paramContext.getPackageManager().getPackageInfo(paramApplicationInfo.processName, 4096).requestedPermissions));
      return paramApplicationInfo;
    }
    catch (Exception paramApplicationInfo) {}
    return null;
  }
  
  /* Error */
  private void bf(Context paramContext)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual 182	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   4: astore 4
    //   6: aload 4
    //   8: iconst_0
    //   9: invokevirtual 218	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   12: invokeinterface 36 1 0
    //   17: astore 5
    //   19: aload 5
    //   21: invokeinterface 42 1 0
    //   26: ifeq +118 -> 144
    //   29: new 220	org/json/JSONObject
    //   32: dup
    //   33: invokespecial 221	org/json/JSONObject:<init>	()V
    //   36: pop
    //   37: aload 5
    //   39: invokeinterface 46 1 0
    //   44: checkcast 196	android/content/pm/PackageInfo
    //   47: astore 6
    //   49: aload 6
    //   51: getfield 224	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   54: ifnull +83 -> 137
    //   57: aload 6
    //   59: getfield 224	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   62: astore_3
    //   63: aload_3
    //   64: aload_1
    //   65: invokestatic 230	ddu:c	(Ljava/lang/String;Landroid/content/Context;)Ljava/lang/String;
    //   68: astore 7
    //   70: aload 7
    //   72: ldc -24
    //   74: invokevirtual 235	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   77: ifne -58 -> 19
    //   80: aload 7
    //   82: ldc -19
    //   84: invokevirtual 235	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   87: istore_2
    //   88: iload_2
    //   89: ifne -70 -> 19
    //   92: aload 4
    //   94: aload_3
    //   95: iconst_0
    //   96: invokevirtual 241	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   99: ifnull -80 -> 19
    //   102: aload_0
    //   103: aload 6
    //   105: getfield 245	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   108: aload_1
    //   109: invokespecial 247	ddi:a	(Landroid/content/pm/ApplicationInfo;Landroid/content/Context;)Ljava/util/List;
    //   112: astore 6
    //   114: aload 6
    //   116: ifnull -97 -> 19
    //   119: aload_0
    //   120: getfield 19	ddi:bdI	Ljava/util/HashMap;
    //   123: aload_3
    //   124: aload 6
    //   126: invokevirtual 171	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   129: pop
    //   130: goto -111 -> 19
    //   133: astore_3
    //   134: goto -115 -> 19
    //   137: ldc -7
    //   139: astore_3
    //   140: goto -77 -> 63
    //   143: astore_1
    //   144: return
    //   145: astore_3
    //   146: goto -127 -> 19
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	149	0	this	ddi
    //   0	149	1	paramContext	Context
    //   87	2	2	bool	boolean
    //   62	62	3	str1	String
    //   133	1	3	localNameNotFoundException	PackageManager.NameNotFoundException
    //   139	1	3	str2	String
    //   145	1	3	localException	Exception
    //   4	89	4	localPackageManager	PackageManager
    //   17	21	5	localIterator	Iterator
    //   47	78	6	localObject	Object
    //   68	13	7	str3	String
    // Exception table:
    //   from	to	target	type
    //   92	114	133	android/content/pm/PackageManager$NameNotFoundException
    //   119	130	133	android/content/pm/PackageManager$NameNotFoundException
    //   0	19	143	java/lang/Exception
    //   19	63	143	java/lang/Exception
    //   63	88	143	java/lang/Exception
    //   92	114	145	java/lang/Exception
    //   119	130	145	java/lang/Exception
  }
  
  public ArrayList<ctm> bg(Context paramContext)
  {
    bf(paramContext);
    ArrayList localArrayList = new ArrayList();
    Object localObject = Iv();
    Map.Entry localEntry;
    if ((localObject != null) && (((HashMap)localObject).size() > 0))
    {
      Iterator localIterator = ((HashMap)localObject).entrySet().iterator();
      if (localIterator.hasNext()) {
        localEntry = (Map.Entry)localIterator.next();
      }
    }
    for (;;)
    {
      int i;
      try
      {
        localObject = paramContext.getPackageManager().getApplicationInfo((String)localEntry.getKey(), 0);
        if (localObject == null) {
          break;
        }
        ctm localCtm = new ctm();
        localCtm.ck((String)localEntry.getKey());
        localCtm.cm(paramContext.getPackageManager().getApplicationLabel((ApplicationInfo)localObject).toString());
        i = 0;
        localObject = "";
        if (i < ((List)localEntry.getValue()).size())
        {
          if (((String)localObject).length() == 0)
          {
            localObject = (String)localObject + (String)((List)localEntry.getValue()).get(i);
            break label291;
          }
          localObject = (String)localObject + ", " + (String)((List)localEntry.getValue()).get(i);
          break label291;
        }
        localCtm.N(((List)localEntry.getValue()).size());
        localCtm.cl((String)localObject);
        localArrayList.add(localCtm);
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException) {}
      break;
      return localArrayList;
      label291:
      i += 1;
    }
  }
}
