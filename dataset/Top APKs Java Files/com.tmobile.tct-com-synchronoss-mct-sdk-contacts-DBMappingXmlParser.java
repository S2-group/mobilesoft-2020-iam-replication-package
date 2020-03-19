package com.synchronoss.mct.sdk.contacts;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.os.Build;
import com.synchronoss.util.Log;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.xmlpull.v1.XmlPullParser;

public class DBMappingXmlParser
{
  static final String a = DBMappingXmlParser.class.getName();
  private static final String c = null;
  public Map<String, Object> b;
  private final Log d;
  private final Context e;
  private final String f = "read-account";
  private final String g = "write-account";
  private final String h = "type";
  private final String i = "id";
  private final String j = "limit";
  private final String k = "mime-type";
  private final String l = "value-type";
  
  public DBMappingXmlParser(Context paramContext, Log paramLog)
  {
    this.e = paramContext;
    this.d = paramLog;
  }
  
  private List<DBMappingXmlParser.Field> a(XmlPullParser paramXmlPullParser)
  {
    ArrayList localArrayList = new ArrayList();
    paramXmlPullParser.require(2, c, "fields");
    while (paramXmlPullParser.next() != 3) {
      if (paramXmlPullParser.getEventType() == 2) {
        if (paramXmlPullParser.getName().equals("field")) {
          localArrayList.add(b(paramXmlPullParser));
        } else {
          d(paramXmlPullParser);
        }
      }
    }
    return localArrayList;
  }
  
  private DBMappingXmlParser.Field b(XmlPullParser paramXmlPullParser)
  {
    paramXmlPullParser.require(2, c, "field");
    Object localObject2 = paramXmlPullParser.getName();
    int m = 0;
    int n = 0;
    Object localObject1 = "";
    String str1 = "";
    if (((String)localObject2).equals("field"))
    {
      m = Integer.parseInt(paramXmlPullParser.getAttributeValue(null, "id"));
      n = Integer.parseInt(paramXmlPullParser.getAttributeValue(null, "limit"));
      localObject1 = paramXmlPullParser.getAttributeValue(null, "mime-type");
      str1 = paramXmlPullParser.getAttributeValue(null, "value-type");
    }
    localObject2 = new DBMappingXmlParser.Field(m, n, (String)localObject1, str1, (byte)0);
    while (paramXmlPullParser.next() != 3) {
      if (paramXmlPullParser.getEventType() == 2) {
        if (paramXmlPullParser.getName().equals("field"))
        {
          paramXmlPullParser.require(2, c, "field");
          String str2 = paramXmlPullParser.getName();
          m = 0;
          n = 0;
          localObject1 = "";
          str1 = "";
          if (str2.equals("field"))
          {
            m = Integer.parseInt(paramXmlPullParser.getAttributeValue(null, "id"));
            n = Integer.parseInt(paramXmlPullParser.getAttributeValue(null, "limit"));
            localObject1 = paramXmlPullParser.getAttributeValue(null, "mime-type");
            str1 = paramXmlPullParser.getAttributeValue(null, "value-type");
            paramXmlPullParser.nextTag();
          }
          paramXmlPullParser.require(3, c, "field");
          localObject1 = new DBMappingXmlParser.Field(m, n, (String)localObject1, str1, (byte)0);
          ((DBMappingXmlParser.Field)localObject2).e.add(localObject1);
        }
        else
        {
          d(paramXmlPullParser);
        }
      }
    }
    return localObject2;
  }
  
  private InputStreamReader b()
  {
    Object localObject2 = Build.MANUFACTURER.replace(" ", "").toLowerCase();
    String str = Build.DEVICE.replace(" ", "").toLowerCase();
    localObject1 = localObject2;
    if (((String)localObject2).equalsIgnoreCase("unknown"))
    {
      localObject1 = this.e.getPackageManager().getInstalledApplications(128).iterator();
      do
      {
        if (!((Iterator)localObject1).hasNext()) {
          break;
        }
      } while (!((ApplicationInfo)((Iterator)localObject1).next()).packageName.toLowerCase().startsWith("com.htc.sense"));
    }
    for (localObject1 = "htc";; localObject1 = "htc")
    {
      localObject2 = (String)localObject1 + "_" + str + ".xml";
      this.d.a(a, "Dbmapping for this device: %s", new Object[] { localObject2 });
      try
      {
        localObject2 = new InputStreamReader(this.e.getAssets().open((String)localObject2), "UTF-8");
        return localObject2;
      }
      catch (IOException localIOException2)
      {
        localObject1 = (String)localObject1 + "_default_dbmapping.xml";
        this.d.a(a, "Default DBmapping issue Got Exception...File name is " + (String)localObject1, new Object[0]);
        try
        {
          localObject1 = new InputStreamReader(this.e.getAssets().open((String)localObject1), "UTF-8");
          return localObject1;
        }
        catch (IOException localIOException1)
        {
          throw new IOException("DbMapping file not found");
        }
      }
      this.d.a(a, "Dbmapping workaround: ", new Object[0]);
    }
  }
  
  private List c(XmlPullParser paramXmlPullParser)
  {
    ArrayList localArrayList = new ArrayList();
    paramXmlPullParser.require(2, c, "accounts");
    String str1;
    String str2;
    for (;;)
    {
      if (paramXmlPullParser.next() != 3) {
        if (paramXmlPullParser.getEventType() == 2)
        {
          str1 = paramXmlPullParser.getName();
          if (str1.equals("read-account"))
          {
            paramXmlPullParser.require(2, c, "read-account");
            if (!paramXmlPullParser.getName().equals("read-account")) {
              break label316;
            }
            str2 = paramXmlPullParser.getAttributeValue(null, "type");
            str1 = paramXmlPullParser.getAttributeValue(null, "clear-deleted-contacts");
            paramXmlPullParser.nextTag();
          }
        }
      }
    }
    for (;;)
    {
      paramXmlPullParser.require(3, c, "read-account");
      if ((str1 != null) && (Boolean.parseBoolean(str1))) {}
      for (boolean bool = true;; bool = false)
      {
        localArrayList.add(new DBMappingXmlParser.ReadAccount(str2, bool, (byte)0));
        break;
      }
      String str3;
      if (str1.equals("write-account"))
      {
        paramXmlPullParser.require(2, c, "write-account");
        if (!paramXmlPullParser.getName().equals("write-account")) {
          break label303;
        }
        str3 = paramXmlPullParser.getAttributeValue(null, "type");
        str2 = paramXmlPullParser.getAttributeValue(null, "name");
        str1 = paramXmlPullParser.getAttributeValue(null, "preverified");
        paramXmlPullParser.nextTag();
      }
      for (;;)
      {
        paramXmlPullParser.require(3, c, "write-account");
        if ((str1 != null) && (Boolean.parseBoolean(str1))) {}
        for (bool = true;; bool = false)
        {
          localArrayList.add(new DBMappingXmlParser.WriteAccount(str2, str3, bool, (byte)0));
          break;
        }
        d(paramXmlPullParser);
        break;
        return localArrayList;
        label303:
        str3 = "";
        str2 = "";
        str1 = null;
      }
      label316:
      str2 = "";
      str1 = null;
    }
  }
  
  private static void d(XmlPullParser paramXmlPullParser)
  {
    if (paramXmlPullParser.getEventType() != 2) {
      throw new IllegalStateException();
    }
    int m = 1;
    while (m != 0) {
      switch (paramXmlPullParser.next())
      {
      default: 
        break;
      case 2: 
        m += 1;
        break;
      case 3: 
        m -= 1;
      }
    }
  }
  
  /* Error */
  public final Map<String, Object> a()
  {
    // Byte code:
    //   0: invokestatic 282	android/util/Xml:newPullParser	()Lorg/xmlpull/v1/XmlPullParser;
    //   3: astore_3
    //   4: aload_3
    //   5: ldc_w 284
    //   8: iconst_0
    //   9: invokeinterface 288 3 0
    //   14: aload_0
    //   15: invokespecial 290	com/synchronoss/mct/sdk/contacts/DBMappingXmlParser:b	()Ljava/io/InputStreamReader;
    //   18: astore_2
    //   19: aload_2
    //   20: astore_1
    //   21: aload_3
    //   22: aload_2
    //   23: invokeinterface 294 2 0
    //   28: aload_2
    //   29: astore_1
    //   30: aload_3
    //   31: invokeinterface 131 1 0
    //   36: pop
    //   37: aload_2
    //   38: astore_1
    //   39: aload_0
    //   40: new 296	java/util/HashMap
    //   43: dup
    //   44: invokespecial 297	java/util/HashMap:<init>	()V
    //   47: putfield 299	com/synchronoss/mct/sdk/contacts/DBMappingXmlParser:b	Ljava/util/Map;
    //   50: aload_2
    //   51: astore_1
    //   52: aload_3
    //   53: iconst_2
    //   54: getstatic 33	com/synchronoss/mct/sdk/contacts/DBMappingXmlParser:c	Ljava/lang/String;
    //   57: ldc_w 301
    //   60: invokeinterface 82 4 0
    //   65: aload_2
    //   66: astore_1
    //   67: aload_3
    //   68: invokeinterface 86 1 0
    //   73: iconst_3
    //   74: if_icmpeq +173 -> 247
    //   77: aload_2
    //   78: astore_1
    //   79: aload_3
    //   80: invokeinterface 89 1 0
    //   85: iconst_2
    //   86: if_icmpne -21 -> 65
    //   89: aload_2
    //   90: astore_1
    //   91: aload_3
    //   92: invokeinterface 90 1 0
    //   97: astore 4
    //   99: aload_2
    //   100: astore_1
    //   101: aload 4
    //   103: ldc -7
    //   105: invokevirtual 98	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   108: ifeq +55 -> 163
    //   111: aload_2
    //   112: astore_1
    //   113: aload_0
    //   114: getfield 299	com/synchronoss/mct/sdk/contacts/DBMappingXmlParser:b	Ljava/util/Map;
    //   117: ldc -7
    //   119: aload_0
    //   120: aload_3
    //   121: invokespecial 303	com/synchronoss/mct/sdk/contacts/DBMappingXmlParser:c	(Lorg/xmlpull/v1/XmlPullParser;)Ljava/util/List;
    //   124: invokeinterface 309 3 0
    //   129: pop
    //   130: goto -65 -> 65
    //   133: astore_3
    //   134: aload_2
    //   135: astore_1
    //   136: aload_0
    //   137: getfield 70	com/synchronoss/mct/sdk/contacts/DBMappingXmlParser:d	Lcom/synchronoss/util/Log;
    //   140: getstatic 31	com/synchronoss/mct/sdk/contacts/DBMappingXmlParser:a	Ljava/lang/String;
    //   143: ldc_w 311
    //   146: aload_3
    //   147: iconst_0
    //   148: anewarray 4	java/lang/Object
    //   151: invokeinterface 314 5 0
    //   156: pop
    //   157: aload_2
    //   158: invokestatic 319	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/Reader;)V
    //   161: aconst_null
    //   162: areturn
    //   163: aload_2
    //   164: astore_1
    //   165: aload 4
    //   167: ldc 76
    //   169: invokevirtual 98	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   172: ifeq +55 -> 227
    //   175: aload_2
    //   176: astore_1
    //   177: aload_0
    //   178: getfield 299	com/synchronoss/mct/sdk/contacts/DBMappingXmlParser:b	Ljava/util/Map;
    //   181: ldc 76
    //   183: aload_0
    //   184: aload_3
    //   185: invokespecial 321	com/synchronoss/mct/sdk/contacts/DBMappingXmlParser:a	(Lorg/xmlpull/v1/XmlPullParser;)Ljava/util/List;
    //   188: invokeinterface 309 3 0
    //   193: pop
    //   194: goto -129 -> 65
    //   197: astore_3
    //   198: aload_2
    //   199: astore_1
    //   200: aload_0
    //   201: getfield 70	com/synchronoss/mct/sdk/contacts/DBMappingXmlParser:d	Lcom/synchronoss/util/Log;
    //   204: getstatic 31	com/synchronoss/mct/sdk/contacts/DBMappingXmlParser:a	Ljava/lang/String;
    //   207: ldc_w 323
    //   210: aload_3
    //   211: iconst_0
    //   212: anewarray 4	java/lang/Object
    //   215: invokeinterface 314 5 0
    //   220: pop
    //   221: aload_2
    //   222: invokestatic 319	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/Reader;)V
    //   225: aconst_null
    //   226: areturn
    //   227: aload_2
    //   228: astore_1
    //   229: aload_3
    //   230: invokestatic 109	com/synchronoss/mct/sdk/contacts/DBMappingXmlParser:d	(Lorg/xmlpull/v1/XmlPullParser;)V
    //   233: goto -168 -> 65
    //   236: astore_3
    //   237: aload_1
    //   238: astore_2
    //   239: aload_3
    //   240: astore_1
    //   241: aload_2
    //   242: invokestatic 319	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/Reader;)V
    //   245: aload_1
    //   246: athrow
    //   247: aload_2
    //   248: astore_1
    //   249: aload_0
    //   250: getfield 299	com/synchronoss/mct/sdk/contacts/DBMappingXmlParser:b	Ljava/util/Map;
    //   253: astore_3
    //   254: aload_2
    //   255: invokestatic 319	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/Reader;)V
    //   258: aload_3
    //   259: areturn
    //   260: astore_1
    //   261: aconst_null
    //   262: astore_2
    //   263: goto -22 -> 241
    //   266: astore_3
    //   267: aconst_null
    //   268: astore_2
    //   269: goto -71 -> 198
    //   272: astore_3
    //   273: aconst_null
    //   274: astore_2
    //   275: goto -141 -> 134
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	278	0	this	DBMappingXmlParser
    //   20	229	1	localObject1	Object
    //   260	1	1	localObject2	Object
    //   18	257	2	localObject3	Object
    //   3	118	3	localXmlPullParser	XmlPullParser
    //   133	52	3	localXmlPullParserException1	org.xmlpull.v1.XmlPullParserException
    //   197	33	3	localIOException1	IOException
    //   236	4	3	localObject4	Object
    //   253	6	3	localMap	Map
    //   266	1	3	localIOException2	IOException
    //   272	1	3	localXmlPullParserException2	org.xmlpull.v1.XmlPullParserException
    //   97	69	4	str	String
    // Exception table:
    //   from	to	target	type
    //   21	28	133	org/xmlpull/v1/XmlPullParserException
    //   30	37	133	org/xmlpull/v1/XmlPullParserException
    //   39	50	133	org/xmlpull/v1/XmlPullParserException
    //   52	65	133	org/xmlpull/v1/XmlPullParserException
    //   67	77	133	org/xmlpull/v1/XmlPullParserException
    //   79	89	133	org/xmlpull/v1/XmlPullParserException
    //   91	99	133	org/xmlpull/v1/XmlPullParserException
    //   101	111	133	org/xmlpull/v1/XmlPullParserException
    //   113	130	133	org/xmlpull/v1/XmlPullParserException
    //   165	175	133	org/xmlpull/v1/XmlPullParserException
    //   177	194	133	org/xmlpull/v1/XmlPullParserException
    //   229	233	133	org/xmlpull/v1/XmlPullParserException
    //   249	254	133	org/xmlpull/v1/XmlPullParserException
    //   21	28	197	java/io/IOException
    //   30	37	197	java/io/IOException
    //   39	50	197	java/io/IOException
    //   52	65	197	java/io/IOException
    //   67	77	197	java/io/IOException
    //   79	89	197	java/io/IOException
    //   91	99	197	java/io/IOException
    //   101	111	197	java/io/IOException
    //   113	130	197	java/io/IOException
    //   165	175	197	java/io/IOException
    //   177	194	197	java/io/IOException
    //   229	233	197	java/io/IOException
    //   249	254	197	java/io/IOException
    //   21	28	236	finally
    //   30	37	236	finally
    //   39	50	236	finally
    //   52	65	236	finally
    //   67	77	236	finally
    //   79	89	236	finally
    //   91	99	236	finally
    //   101	111	236	finally
    //   113	130	236	finally
    //   136	157	236	finally
    //   165	175	236	finally
    //   177	194	236	finally
    //   200	221	236	finally
    //   229	233	236	finally
    //   249	254	236	finally
    //   0	19	260	finally
    //   0	19	266	java/io/IOException
    //   0	19	272	org/xmlpull/v1/XmlPullParserException
  }
}
