package com.newbay.syncdrive.android.model.nab.Util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.os.Build;
import com.newbay.syncdrive.android.model.util.Log;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.xmlpull.v1.XmlPullParser;

@Singleton
public class DBMappingXmlParser
{
  public static final String ACCOUNTS = "accounts";
  public static final String FIELD = "field";
  public static final String FIELDS = "fields";
  static final String TAG = DBMappingXmlParser.class.getName();
  private static final String ns = null;
  private final String ID = "id";
  private final String LIMIT = "limit";
  private final String MIMETYPE = "mime-type";
  private final String READ_ACCOUNT = "read-account";
  private final String TYPE = "type";
  private final String VALUETYPE = "value-type";
  private final String WRITE_ACCOUNT = "write-account";
  private final Context context;
  public Map<String, Object> dbmappingvalues;
  private final Log mLog;
  
  @Inject
  public DBMappingXmlParser(Context paramContext, Log paramLog)
  {
    this.context = paramContext;
    this.mLog = paramLog;
  }
  
  private InputStreamReader getDefaultDBMappingFile()
  {
    Object localObject2 = Build.MANUFACTURER.replace(" ", "").toLowerCase();
    String str = Build.DEVICE.replace(" ", "").toLowerCase();
    Object localObject1 = localObject2;
    if (((String)localObject2).equalsIgnoreCase("unknown")) {
      localObject1 = htc_manufacturer_workaround();
    }
    localObject2 = (String)localObject1 + "_" + str + ".xml";
    this.mLog.a(TAG, "Dbmapping for this device: %s", new Object[] { localObject2 });
    try
    {
      localObject2 = new InputStreamReader(this.context.getAssets().open((String)localObject2), "UTF-8");
      return localObject2;
    }
    catch (IOException localIOException2)
    {
      localObject1 = (String)localObject1 + "_default_dbmapping.xml";
      try
      {
        localObject1 = new InputStreamReader(this.context.getAssets().open((String)localObject1), "UTF-8");
        return localObject1;
      }
      catch (IOException localIOException1)
      {
        throw new IOException("DbMapping file not found");
      }
    }
  }
  
  private String getFilePath()
  {
    return Build.MANUFACTURER.toLowerCase() + "_default_dbmapping.xml";
  }
  
  private String htc_manufacturer_workaround()
  {
    Iterator localIterator = this.context.getPackageManager().getInstalledApplications(128).iterator();
    while (localIterator.hasNext()) {
      if (((ApplicationInfo)localIterator.next()).packageName.toLowerCase().startsWith("com.android.providers.htc")) {
        return "htc";
      }
    }
    this.mLog.a(TAG, "Dbmapping workaround: ", new Object[0]);
    return "";
  }
  
  private List readAccounts(XmlPullParser paramXmlPullParser)
  {
    ArrayList localArrayList = new ArrayList();
    paramXmlPullParser.require(2, ns, "accounts");
    while (paramXmlPullParser.next() != 3) {
      if (paramXmlPullParser.getEventType() == 2)
      {
        String str = paramXmlPullParser.getName();
        if (str.equals("read-account")) {
          localArrayList.add(readReadAccount(paramXmlPullParser));
        } else if (str.equals("write-account")) {
          localArrayList.add(readWriteAccount(paramXmlPullParser));
        } else {
          skip(paramXmlPullParser);
        }
      }
    }
    return localArrayList;
  }
  
  private DBMappingXmlParser.Field readField(XmlPullParser paramXmlPullParser)
  {
    int j = 0;
    paramXmlPullParser.require(2, ns, "field");
    String str2 = paramXmlPullParser.getName();
    Object localObject = "";
    String str1 = "";
    int i;
    if (str2.equals("field"))
    {
      i = Integer.parseInt(paramXmlPullParser.getAttributeValue(null, "id"));
      j = Integer.parseInt(paramXmlPullParser.getAttributeValue(null, "limit"));
      localObject = paramXmlPullParser.getAttributeValue(null, "mime-type");
      str1 = paramXmlPullParser.getAttributeValue(null, "value-type");
    }
    for (;;)
    {
      localObject = new DBMappingXmlParser.Field(i, j, (String)localObject, str1, null);
      while (paramXmlPullParser.next() != 3) {
        if (paramXmlPullParser.getEventType() == 2) {
          if (paramXmlPullParser.getName().equals("field")) {
            ((DBMappingXmlParser.Field)localObject).addSubField(readSubField(paramXmlPullParser));
          } else {
            skip(paramXmlPullParser);
          }
        }
      }
      return localObject;
      i = 0;
    }
  }
  
  private List<DBMappingXmlParser.Field> readFields(XmlPullParser paramXmlPullParser)
  {
    ArrayList localArrayList = new ArrayList();
    paramXmlPullParser.require(2, ns, "fields");
    while (paramXmlPullParser.next() != 3) {
      if (paramXmlPullParser.getEventType() == 2) {
        if (paramXmlPullParser.getName().equals("field")) {
          localArrayList.add(readField(paramXmlPullParser));
        } else {
          skip(paramXmlPullParser);
        }
      }
    }
    return localArrayList;
  }
  
  private Map<String, Object> readMapping(XmlPullParser paramXmlPullParser)
  {
    this.dbmappingvalues = new HashMap();
    paramXmlPullParser.require(2, ns, "db-mapping");
    while (paramXmlPullParser.next() != 3) {
      if (paramXmlPullParser.getEventType() == 2)
      {
        String str = paramXmlPullParser.getName();
        if (str.equals("accounts")) {
          this.dbmappingvalues.put("accounts", readAccounts(paramXmlPullParser));
        } else if (str.equals("fields")) {
          this.dbmappingvalues.put("fields", readFields(paramXmlPullParser));
        } else {
          skip(paramXmlPullParser);
        }
      }
    }
    return this.dbmappingvalues;
  }
  
  private DBMappingXmlParser.ReadAccount readReadAccount(XmlPullParser paramXmlPullParser)
  {
    paramXmlPullParser.require(2, ns, "read-account");
    String str2;
    String str1;
    if (paramXmlPullParser.getName().equals("read-account"))
    {
      str2 = paramXmlPullParser.getAttributeValue(null, "type");
      str1 = paramXmlPullParser.getAttributeValue(null, "clear-deleted-contacts");
      paramXmlPullParser.nextTag();
    }
    for (;;)
    {
      paramXmlPullParser.require(3, ns, "read-account");
      if ((str1 != null) && (Boolean.parseBoolean(str1))) {}
      for (boolean bool = true;; bool = false) {
        return new DBMappingXmlParser.ReadAccount(str2, bool, null);
      }
      str2 = "";
      str1 = null;
    }
  }
  
  private DBMappingXmlParser.Field readSubField(XmlPullParser paramXmlPullParser)
  {
    int j = 0;
    paramXmlPullParser.require(2, ns, "field");
    String str3 = paramXmlPullParser.getName();
    String str1 = "";
    String str2 = "";
    int i;
    if (str3.equals("field"))
    {
      i = Integer.parseInt(paramXmlPullParser.getAttributeValue(null, "id"));
      j = Integer.parseInt(paramXmlPullParser.getAttributeValue(null, "limit"));
      str1 = paramXmlPullParser.getAttributeValue(null, "mime-type");
      str2 = paramXmlPullParser.getAttributeValue(null, "value-type");
      paramXmlPullParser.nextTag();
    }
    for (;;)
    {
      paramXmlPullParser.require(3, ns, "field");
      return new DBMappingXmlParser.Field(i, j, str1, str2, null);
      i = 0;
    }
  }
  
  private DBMappingXmlParser.WriteAccount readWriteAccount(XmlPullParser paramXmlPullParser)
  {
    paramXmlPullParser.require(2, ns, "write-account");
    String str3;
    String str2;
    String str1;
    if (paramXmlPullParser.getName().equals("write-account"))
    {
      str3 = paramXmlPullParser.getAttributeValue(null, "type");
      str2 = paramXmlPullParser.getAttributeValue(null, "name");
      str1 = paramXmlPullParser.getAttributeValue(null, "preverified");
      paramXmlPullParser.nextTag();
    }
    for (;;)
    {
      paramXmlPullParser.require(3, ns, "write-account");
      if ((str1 != null) && (Boolean.parseBoolean(str1))) {}
      for (boolean bool = true;; bool = false) {
        return new DBMappingXmlParser.WriteAccount(str2, str3, bool, null);
      }
      str3 = "";
      str2 = "";
      str1 = null;
    }
  }
  
  private void skip(XmlPullParser paramXmlPullParser)
  {
    if (paramXmlPullParser.getEventType() != 2) {
      throw new IllegalStateException();
    }
    int i = 1;
    while (i != 0) {
      switch (paramXmlPullParser.next())
      {
      default: 
        break;
      case 2: 
        i += 1;
        break;
      case 3: 
        i -= 1;
      }
    }
  }
  
  public Map<String, Object> getDbmappingvalues()
  {
    if ((this.dbmappingvalues != null) && (!this.dbmappingvalues.isEmpty())) {
      return this.dbmappingvalues;
    }
    return parseDBMapping();
  }
  
  /* Error */
  public Map<String, Object> parseDBMapping()
  {
    // Byte code:
    //   0: invokestatic 335	android/util/Xml:newPullParser	()Lorg/xmlpull/v1/XmlPullParser;
    //   3: astore_3
    //   4: aload_3
    //   5: ldc_w 337
    //   8: iconst_0
    //   9: invokeinterface 341 3 0
    //   14: aload_0
    //   15: invokespecial 343	com/newbay/syncdrive/android/model/nab/Util/DBMappingXmlParser:getDefaultDBMappingFile	()Ljava/io/InputStreamReader;
    //   18: astore_2
    //   19: aload_2
    //   20: astore_1
    //   21: aload_3
    //   22: aload_2
    //   23: invokeinterface 347 2 0
    //   28: aload_2
    //   29: astore_1
    //   30: aload_3
    //   31: invokeinterface 296 1 0
    //   36: pop
    //   37: aload_2
    //   38: astore_1
    //   39: aload_0
    //   40: aload_3
    //   41: invokespecial 349	com/newbay/syncdrive/android/model/nab/Util/DBMappingXmlParser:readMapping	(Lorg/xmlpull/v1/XmlPullParser;)Ljava/util/Map;
    //   44: astore_3
    //   45: aload_2
    //   46: invokestatic 353	org/apache/commons/io/IOUtils:a	(Ljava/io/Reader;)V
    //   49: aload_3
    //   50: areturn
    //   51: astore_3
    //   52: aconst_null
    //   53: astore_2
    //   54: aload_2
    //   55: astore_1
    //   56: aload_0
    //   57: getfield 81	com/newbay/syncdrive/android/model/nab/Util/DBMappingXmlParser:mLog	Lcom/newbay/syncdrive/android/model/util/Log;
    //   60: getstatic 41	com/newbay/syncdrive/android/model/nab/Util/DBMappingXmlParser:TAG	Ljava/lang/String;
    //   63: ldc_w 355
    //   66: aload_3
    //   67: iconst_0
    //   68: anewarray 4	java/lang/Object
    //   71: invokeinterface 358 5 0
    //   76: pop
    //   77: aload_2
    //   78: invokestatic 353	org/apache/commons/io/IOUtils:a	(Ljava/io/Reader;)V
    //   81: aconst_null
    //   82: areturn
    //   83: astore_3
    //   84: aconst_null
    //   85: astore_2
    //   86: aload_2
    //   87: astore_1
    //   88: aload_0
    //   89: getfield 81	com/newbay/syncdrive/android/model/nab/Util/DBMappingXmlParser:mLog	Lcom/newbay/syncdrive/android/model/util/Log;
    //   92: getstatic 41	com/newbay/syncdrive/android/model/nab/Util/DBMappingXmlParser:TAG	Ljava/lang/String;
    //   95: ldc_w 360
    //   98: aload_3
    //   99: iconst_0
    //   100: anewarray 4	java/lang/Object
    //   103: invokeinterface 358 5 0
    //   108: pop
    //   109: aload_2
    //   110: invokestatic 353	org/apache/commons/io/IOUtils:a	(Ljava/io/Reader;)V
    //   113: aconst_null
    //   114: areturn
    //   115: astore_1
    //   116: aconst_null
    //   117: astore_3
    //   118: aload_1
    //   119: astore_2
    //   120: aload_3
    //   121: invokestatic 353	org/apache/commons/io/IOUtils:a	(Ljava/io/Reader;)V
    //   124: aload_2
    //   125: athrow
    //   126: astore_2
    //   127: aload_1
    //   128: astore_3
    //   129: goto -9 -> 120
    //   132: astore_3
    //   133: goto -47 -> 86
    //   136: astore_3
    //   137: goto -83 -> 54
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	140	0	this	DBMappingXmlParser
    //   20	68	1	localObject1	Object
    //   115	13	1	localObject2	Object
    //   18	107	2	localObject3	Object
    //   126	1	2	localObject4	Object
    //   3	47	3	localObject5	Object
    //   51	16	3	localXmlPullParserException1	org.xmlpull.v1.XmlPullParserException
    //   83	16	3	localIOException1	IOException
    //   117	12	3	localObject6	Object
    //   132	1	3	localIOException2	IOException
    //   136	1	3	localXmlPullParserException2	org.xmlpull.v1.XmlPullParserException
    // Exception table:
    //   from	to	target	type
    //   0	19	51	org/xmlpull/v1/XmlPullParserException
    //   0	19	83	java/io/IOException
    //   0	19	115	finally
    //   21	28	126	finally
    //   30	37	126	finally
    //   39	45	126	finally
    //   56	77	126	finally
    //   88	109	126	finally
    //   21	28	132	java/io/IOException
    //   30	37	132	java/io/IOException
    //   39	45	132	java/io/IOException
    //   21	28	136	org/xmlpull/v1/XmlPullParserException
    //   30	37	136	org/xmlpull/v1/XmlPullParserException
    //   39	45	136	org/xmlpull/v1/XmlPullParserException
  }
}
