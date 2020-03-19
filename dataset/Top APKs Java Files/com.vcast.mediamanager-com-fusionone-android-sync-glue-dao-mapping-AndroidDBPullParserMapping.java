package com.fusionone.android.sync.glue.dao.mapping;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.os.Build;
import com.fusionone.android.dsp.session.SessionManager;
import com.fusionone.android.dsp.session.SessionManagerImpl;
import com.fusionone.android.exceptions.SyncPlatformServiceException;
import com.fusionone.android.sync.glue.dao.AccountToSync;
import com.fusionone.dsp.framework.BundleActivator;
import com.fusionone.dsp.framework.BundleContext;
import com.fusionone.syncml.sdk.configurator.DeviceInformation;
import com.fusionone.syncml.sdk.database.DataBaseField;
import com.fusionone.syncml.sdk.database.DataBaseFieldImpl;
import com.synchronoss.android.util.Log;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class AndroidDBPullParserMapping
  implements AndroidDBMapping, DBMappingFields, BundleActivator
{
  private static final String DEFAULT_DBMAPPING_XML = "_default_dbmapping.xml";
  private static final int DEFAULT_INITIAL_CONTACT_VERSION = 2;
  private static final int DEFAULT_MAX_ITEM_SIZE = 10000;
  private static final String LOG_TAG = AndroidDBPullParserMapping.class.getSimpleName();
  private static final String TABLET_DEFAULT_DBMAPPING_XML = "tablet_default_dbmapping.xml";
  public static boolean ftabletDeviceType = false;
  Context androidContext;
  private BundleContext bundleContext;
  private Stack<AndroidDBMappingItem2> fCurrentDbMappingItems;
  private List<AndroidDBMappingItem2> fDbMappingItems;
  private Set<DataBaseField> fDefaultDataBaseFields;
  private int fInitialContactVersion;
  private int fMappingInfoVersion;
  private int fMaxItemSize;
  private Map<String, Boolean> fMimeTypesComplexity = new HashMap();
  private List<AccountToSync> fReadAccounts;
  private boolean fUtcDates;
  private List<AccountToSync> fWriteAccounts;
  private boolean isContactPictureDisabled;
  private Log log;
  SessionManager sessionManager;
  
  public AndroidDBPullParserMapping() {}
  
  private void appendDefaultField(XmlPullParser paramXmlPullParser)
  {
    int i = paramXmlPullParser.getAttributeCount() - 1;
    Object localObject1 = null;
    int j = -1;
    Object localObject3 = null;
    String str2;
    String str1;
    int k;
    Object localObject2;
    Object localObject4;
    if (i >= 0)
    {
      str2 = paramXmlPullParser.getAttributeName(i);
      str1 = paramXmlPullParser.getAttributeValue(i);
      if ("id".equals(str2))
      {
        k = Integer.valueOf(str1).intValue();
        localObject2 = localObject1;
        localObject4 = localObject3;
      }
    }
    for (;;)
    {
      i -= 1;
      localObject1 = localObject2;
      j = k;
      localObject3 = localObject4;
      break;
      k = j;
      localObject4 = localObject3;
      localObject2 = str1;
      if (!"value-type".equals(str2)) {
        if ("value".equals(str2))
        {
          localObject4 = str1;
          k = j;
          localObject2 = localObject1;
          continue;
          if ("String".equals(localObject1)) {
            paramXmlPullParser = new DataBaseFieldImpl(j, localObject3);
          }
          for (;;)
          {
            this.fDefaultDataBaseFields.add(paramXmlPullParser);
            return;
            if ("Boolean".equals(localObject1))
            {
              if (Boolean.TRUE.toString().equals(localObject3)) {}
              for (paramXmlPullParser = Boolean.TRUE;; paramXmlPullParser = Boolean.FALSE)
              {
                paramXmlPullParser = new DataBaseFieldImpl(j, paramXmlPullParser.toString().toUpperCase());
                break;
              }
            }
            if ("Integer".equals(localObject1))
            {
              paramXmlPullParser = new DataBaseFieldImpl(j, localObject3);
            }
            else
            {
              if (!"Float".equals(localObject1)) {
                break;
              }
              paramXmlPullParser = new DataBaseFieldImpl(j, localObject3);
            }
          }
          throw new XmlPullParserException("Unknown default field type: " + localObject1);
        }
        else
        {
          k = j;
          localObject4 = localObject3;
          localObject2 = localObject1;
        }
      }
    }
  }
  
  private void appendDeviceType(XmlPullParser paramXmlPullParser)
  {
    int i = paramXmlPullParser.getAttributeCount() - 1;
    while (i >= 0)
    {
      String str1 = paramXmlPullParser.getAttributeName(i);
      String str2 = paramXmlPullParser.getAttributeValue(i);
      if ("type".equals(str1))
      {
        ftabletDeviceType = Boolean.parseBoolean(str2);
        this.sessionManager.a("device_tablet_type", ftabletDeviceType);
        com.fusionone.android.sync.glue.configurator.ConfigurationService.DEVICE_TABLET_MODE = ftabletDeviceType;
        com.fusionone.android.sync.glue.configurator.ConfigurationService.DEVICE_TABLET_MODE_SET = true;
      }
      i -= 1;
    }
  }
  
  private void appendMappingRestriction(XmlPullParser paramXmlPullParser)
  {
    Object localObject2 = null;
    boolean bool = false;
    int i = paramXmlPullParser.getAttributeCount() - 1;
    Object localObject1 = null;
    String str;
    Object localObject3;
    if (i >= 0)
    {
      str = paramXmlPullParser.getAttributeName(i);
      localObject3 = paramXmlPullParser.getAttributeValue(i);
      if ("column-name".equals(str))
      {
        localObject1 = localObject2;
        localObject2 = localObject3;
      }
    }
    for (;;)
    {
      i -= 1;
      localObject3 = localObject2;
      localObject2 = localObject1;
      localObject1 = localObject3;
      break;
      if ("value".equals(str))
      {
        localObject2 = localObject1;
        localObject1 = localObject3;
      }
      else if ("access-data".equals(str))
      {
        bool = Boolean.TRUE.toString().equals(localObject3);
        localObject3 = localObject1;
        localObject1 = localObject2;
        localObject2 = localObject3;
        continue;
        if (this.fCurrentDbMappingItems.isEmpty()) {
          throw new XmlPullParserException("cannot append restriction. No field is active");
        }
        ((AndroidDBMappingItem2)this.fCurrentDbMappingItems.peek()).appendRestriction(new MappingRestriction(localObject1, localObject2, bool));
      }
      else
      {
        localObject3 = localObject1;
        localObject1 = localObject2;
        localObject2 = localObject3;
      }
    }
  }
  
  private void checkForConfigurationError(Context paramContext)
  {
    if (paramContext == null) {
      throw new SyncPlatformServiceException(7, "cannot get Android context from sync platform");
    }
  }
  
  private String confirmOEM(Context paramContext)
  {
    paramContext = paramContext.getPackageManager().getInstalledApplications(128).iterator();
    while (paramContext.hasNext()) {
      if (((ApplicationInfo)paramContext.next()).packageName.toLowerCase().startsWith("com.android.providers.htc")) {
        return "htc";
      }
    }
    return null;
  }
  
  private void ensureInitialized()
  {
    if (this.fDbMappingItems == null) {
      parseXmlDatabaseMapping();
    }
  }
  
  private int getFieldTypeValue(int paramInt, String paramString)
  {
    if ("Boolean".equals(paramString)) {
      paramInt = 2;
    }
    do
    {
      return paramInt;
      if ("Date_3339".equals(paramString)) {
        return 3;
      }
      if ("Date_samsung".equals(paramString)) {
        return 4;
      }
      if ("Date_long".equals(paramString)) {
        return 5;
      }
      if ("Date_yyyyMMdd".equals(paramString)) {
        return 6;
      }
      if ("Date_MMddyyyy".equals(paramString)) {
        return 8;
      }
    } while (!"ByteArray".equals(paramString));
    return 7;
  }
  
  @TargetApi(4)
  private String getManufacturer(Context paramContext)
  {
    String str2 = Build.MANUFACTURER;
    String str1;
    if (str2 != null)
    {
      str1 = str2;
      if (!str2.toLowerCase().contains("unknown")) {}
    }
    else
    {
      str1 = confirmOEM(paramContext);
    }
    return str1;
  }
  
  private AndroidDBMappingItem2 initDbMappingItem(XmlPullParser paramXmlPullParser)
  {
    int n = -1;
    int j = paramXmlPullParser.getAttributeCount() - 1;
    int i = 0;
    int k = 0;
    int m = 1;
    Object localObject3 = null;
    Object localObject2 = null;
    Object localObject4 = null;
    String str;
    Object localObject1;
    if (j >= 0)
    {
      str = paramXmlPullParser.getAttributeName(j);
      localObject1 = paramXmlPullParser.getAttributeValue(j);
      if ("id".equals(str)) {
        n = Integer.valueOf((String)localObject1).intValue();
      }
    }
    for (;;)
    {
      j -= 1;
      break;
      if ("table".equals(str))
      {
        localObject4 = localObject1;
      }
      else if ("mime-type".equals(str))
      {
        localObject2 = localObject1;
      }
      else if ("column-name".equals(str))
      {
        localObject3 = localObject1;
      }
      else if ("value-type".equals(str))
      {
        m = getFieldTypeValue(m, (String)localObject1);
      }
      else if ("limit".equals(str))
      {
        k = Integer.valueOf((String)localObject1).intValue();
      }
      else if ("priority".equals(str))
      {
        i = Integer.valueOf((String)localObject1).intValue();
        continue;
        if (this.fCurrentDbMappingItems.isEmpty()) {
          paramXmlPullParser = new AndroidDBMappingItem2(n, localObject4, localObject2, 1, localObject3, null, m, k, 0);
        }
        for (;;)
        {
          if (localObject2 != null)
          {
            localObject1 = (Boolean)this.fMimeTypesComplexity.get(paramXmlPullParser.getMimeType());
            if ((localObject1 == null) || (!((Boolean)localObject1).booleanValue())) {
              this.fMimeTypesComplexity.put(localObject2, Boolean.FALSE);
            }
          }
          if (i != 0) {
            paramXmlPullParser.setPriority(i);
          }
          return paramXmlPullParser;
          localObject1 = (AndroidDBMappingItem2)this.fCurrentDbMappingItems.peek();
          paramXmlPullParser = new AndroidDBMappingItem2(n, localObject4, localObject2, 3, localObject3, null, m, k, ((AndroidDBMappingItem2)localObject1).getId());
          ((AndroidDBMappingItem2)localObject1).setComplexityType(2);
          this.fMimeTypesComplexity.put(((AndroidDBMappingItem2)localObject1).getMimeType(), Boolean.TRUE);
        }
      }
    }
  }
  
  private void parseMappingInfo(XmlPullParser paramXmlPullParser)
  {
    int i = paramXmlPullParser.getAttributeCount() - 1;
    while (i >= 0)
    {
      String str1 = paramXmlPullParser.getAttributeName(i);
      String str2 = paramXmlPullParser.getAttributeValue(i);
      if ("version".equals(str1)) {
        this.fMappingInfoVersion = Integer.valueOf(str2).intValue();
      }
      i -= 1;
    }
  }
  
  private void parseXmlMapping(XmlPullParser paramXmlPullParser)
  {
    boolean bool = this.sessionManager.b("device_tablet_type", false).booleanValue();
    int i = paramXmlPullParser.getEventType();
    if (i != 1)
    {
      switch (i)
      {
      }
      for (;;)
      {
        i = paramXmlPullParser.next();
        break;
        Object localObject = paramXmlPullParser.getName();
        if ("field".equals(localObject))
        {
          localObject = initDbMappingItem(paramXmlPullParser);
          if ((this.isContactPictureDisabled) && ("vnd.android.cursor.item/photo".equals(((AndroidDBMappingItem2)localObject).getMimeType())))
          {
            this.log.d(LOG_TAG, " AndroidDBPullParserMapping  parseXmlMapping() skip photo", new Object[0]);
          }
          else if ((bool) && ("vnd.android.cursor.item/group_membership".equals(((AndroidDBMappingItem2)localObject).getMimeType())))
          {
            this.log.d(LOG_TAG, " <<<<Group not supported for TABLET>>>", new Object[0]);
          }
          else
          {
            this.fCurrentDbMappingItems.push(localObject);
            this.fDbMappingItems.add(localObject);
          }
        }
        else if ("restriction".equals(localObject))
        {
          appendMappingRestriction(paramXmlPullParser);
        }
        else if ("default-field".equals(localObject))
        {
          appendDefaultField(paramXmlPullParser);
        }
        else if ("restore-contacts".equals(localObject))
        {
          setRestoreContactsOptions(paramXmlPullParser);
        }
        else if ("mapping-info".equals(localObject))
        {
          parseMappingInfo(paramXmlPullParser);
        }
        else if ("device_tablet_type".equals(localObject))
        {
          appendDeviceType(paramXmlPullParser);
          continue;
          if (("field".equals(paramXmlPullParser.getName())) && (!this.fCurrentDbMappingItems.isEmpty())) {
            this.fCurrentDbMappingItems.pop();
          }
        }
      }
    }
  }
  
  private void setRestoreContactsOptions(XmlPullParser paramXmlPullParser)
  {
    int i = paramXmlPullParser.getAttributeCount() - 1;
    if (i >= 0)
    {
      String str1 = paramXmlPullParser.getAttributeName(i);
      String str2 = paramXmlPullParser.getAttributeValue(i);
      if ("initial-version".equals(str1)) {
        this.fInitialContactVersion = Integer.valueOf(str2).intValue();
      }
      for (;;)
      {
        i -= 1;
        break;
        if ("use-utc-dates".equals(str1))
        {
          this.fUtcDates = "true".equals(str2);
        }
        else if ("max-item-size".equals(str1))
        {
          this.fMaxItemSize = Integer.valueOf(str2).intValue();
        }
        else if ("max-msg-size".equals(str1))
        {
          int j = Integer.valueOf(str2).intValue();
          ((DeviceInformation)this.bundleContext.d(DeviceInformation.class.getName())).setMaxMsgSize(j);
        }
      }
    }
  }
  
  public void forceInitialized()
  {
    parseXmlDatabaseMapping();
  }
  
  @TargetApi(4)
  protected InputStreamReader getConfigByFileName(String paramString)
  {
    checkForConfigurationError(this.androidContext);
    try
    {
      paramString = new InputStreamReader(this.androidContext.openFileInput(paramString), "UTF-8");
      return paramString;
    }
    catch (IOException paramString)
    {
      paramString = getManufacturer(this.androidContext);
      Object localObject = getDBMappingFileName(paramString, Build.DEVICE);
      try
      {
        localObject = new InputStreamReader(this.androidContext.getAssets().open((String)localObject), "UTF-8");
        return localObject;
      }
      catch (IOException localIOException) {}
    }
    for (;;)
    {
      try
      {
        if (this.sessionManager.b("device_tablet_type", false).booleanValue())
        {
          paramString = "tablet_default_dbmapping.xml";
          paramString = new InputStreamReader(this.androidContext.getAssets().open(paramString), "UTF-8");
          return paramString;
        }
      }
      catch (IOException paramString)
      {
        throw new SyncPlatformServiceException(39, "Device ConfigFile not present");
      }
      paramString = getDefaultDbMappingFile(paramString);
    }
  }
  
  protected InputStreamReader getContentByFileName(String paramString)
  {
    checkForConfigurationError(this.androidContext);
    try
    {
      paramString = new InputStreamReader(this.androidContext.getAssets().open(paramString), "UTF-8");
      return paramString;
    }
    catch (IOException paramString)
    {
      throw new SyncPlatformServiceException(39, "Device ConfigFile not present");
    }
  }
  
  public String getDBMappingFileName(String paramString1, String paramString2)
  {
    paramString1 = paramString1.replace(" ", "").toLowerCase();
    paramString2 = paramString2.replace(" ", "").toLowerCase();
    return paramString1 + "_" + paramString2 + ".xml";
  }
  
  public Set<DataBaseField> getDefaultDatabaseFields()
  {
    ensureInitialized();
    return this.fDefaultDataBaseFields;
  }
  
  @TargetApi(4)
  public String getDefaultDbMappingFile(String paramString)
  {
    return paramString.toLowerCase() + "_default_dbmapping.xml";
  }
  
  public int getInitialContactVersion()
  {
    ensureInitialized();
    return this.fInitialContactVersion;
  }
  
  public int getMappingInfoVersion()
  {
    ensureInitialized();
    return this.fMappingInfoVersion;
  }
  
  public List<AndroidDBMappingItem2> getMappingItems()
  {
    ensureInitialized();
    return this.fDbMappingItems;
  }
  
  public int getMaxItemSize()
  {
    ensureInitialized();
    return this.fMaxItemSize;
  }
  
  public Map<String, Boolean> getMimeTypesComplexity()
  {
    ensureInitialized();
    return this.fMimeTypesComplexity;
  }
  
  public AccountToSync[] getReadAccounts()
  {
    ensureInitialized();
    AccountToSync[] arrayOfAccountToSync = new AccountToSync[this.fReadAccounts.size()];
    return (AccountToSync[])this.fReadAccounts.toArray(arrayOfAccountToSync);
  }
  
  public AccountToSync[] getWriteAccounts()
  {
    ensureInitialized();
    AccountToSync[] arrayOfAccountToSync = new AccountToSync[this.fWriteAccounts.size()];
    return (AccountToSync[])this.fWriteAccounts.toArray(arrayOfAccountToSync);
  }
  
  public boolean isUsingUtcDates()
  {
    ensureInitialized();
    return this.fUtcDates;
  }
  
  /* Error */
  public void parseXmlDatabaseMapping()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aload_0
    //   4: iconst_2
    //   5: putfield 433	com/fusionone/android/sync/glue/dao/mapping/AndroidDBPullParserMapping:fInitialContactVersion	I
    //   8: aload_0
    //   9: new 551	java/util/ArrayList
    //   12: dup
    //   13: invokespecial 552	java/util/ArrayList:<init>	()V
    //   16: putfield 84	com/fusionone/android/sync/glue/dao/mapping/AndroidDBPullParserMapping:fDbMappingItems	Ljava/util/List;
    //   19: aload_0
    //   20: new 554	java/util/HashSet
    //   23: dup
    //   24: invokespecial 555	java/util/HashSet:<init>	()V
    //   27: putfield 161	com/fusionone/android/sync/glue/dao/mapping/AndroidDBPullParserMapping:fDefaultDataBaseFields	Ljava/util/Set;
    //   30: aload_0
    //   31: new 71	java/util/HashMap
    //   34: dup
    //   35: invokespecial 72	java/util/HashMap:<init>	()V
    //   38: putfield 74	com/fusionone/android/sync/glue/dao/mapping/AndroidDBPullParserMapping:fMimeTypesComplexity	Ljava/util/Map;
    //   41: aload_0
    //   42: new 232	java/util/Stack
    //   45: dup
    //   46: invokespecial 556	java/util/Stack:<init>	()V
    //   49: putfield 106	com/fusionone/android/sync/glue/dao/mapping/AndroidDBPullParserMapping:fCurrentDbMappingItems	Ljava/util/Stack;
    //   52: aload_0
    //   53: new 551	java/util/ArrayList
    //   56: dup
    //   57: invokespecial 552	java/util/ArrayList:<init>	()V
    //   60: putfield 78	com/fusionone/android/sync/glue/dao/mapping/AndroidDBPullParserMapping:fReadAccounts	Ljava/util/List;
    //   63: aload_0
    //   64: new 551	java/util/ArrayList
    //   67: dup
    //   68: invokespecial 552	java/util/ArrayList:<init>	()V
    //   71: putfield 81	com/fusionone/android/sync/glue/dao/mapping/AndroidDBPullParserMapping:fWriteAccounts	Ljava/util/List;
    //   74: aload_0
    //   75: iconst_0
    //   76: putfield 439	com/fusionone/android/sync/glue/dao/mapping/AndroidDBPullParserMapping:fUtcDates	Z
    //   79: aload_0
    //   80: sipush 10000
    //   83: putfield 443	com/fusionone/android/sync/glue/dao/mapping/AndroidDBPullParserMapping:fMaxItemSize	I
    //   86: aload_0
    //   87: aload_0
    //   88: getfield 465	com/fusionone/android/sync/glue/dao/mapping/AndroidDBPullParserMapping:androidContext	Landroid/content/Context;
    //   91: invokestatic 561	com/fusionone/android/utils/Utils:a	(Landroid/content/Context;)Z
    //   94: putfield 116	com/fusionone/android/sync/glue/dao/mapping/AndroidDBPullParserMapping:isContactPictureDisabled	Z
    //   97: aload_0
    //   98: getfield 447	com/fusionone/android/sync/glue/dao/mapping/AndroidDBPullParserMapping:bundleContext	Lcom/fusionone/dsp/framework/BundleContext;
    //   101: getstatic 564	com/fusionone/android/sync/glue/dao/mapping/AndroidDBPullParserMapping:PROPERTIES_PID	Ljava/lang/String;
    //   104: invokeinterface 568 2 0
    //   109: astore_1
    //   110: aload_1
    //   111: ifnonnull +16 -> 127
    //   114: new 257	com/fusionone/android/exceptions/SyncPlatformServiceException
    //   117: dup
    //   118: bipush 7
    //   120: ldc_w 570
    //   123: invokespecial 260	com/fusionone/android/exceptions/SyncPlatformServiceException:<init>	(ILjava/lang/String;)V
    //   126: athrow
    //   127: aload_1
    //   128: ldc_w 572
    //   131: invokeinterface 576 2 0
    //   136: checkcast 135	java/lang/String
    //   139: astore_1
    //   140: new 578	java/io/BufferedReader
    //   143: dup
    //   144: aload_0
    //   145: aload_1
    //   146: invokevirtual 580	com/fusionone/android/sync/glue/dao/mapping/AndroidDBPullParserMapping:getContentByFileName	(Ljava/lang/String;)Ljava/io/InputStreamReader;
    //   149: invokespecial 583	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   152: astore_2
    //   153: invokestatic 589	org/xmlpull/v1/XmlPullParserFactory:newInstance	()Lorg/xmlpull/v1/XmlPullParserFactory;
    //   156: invokevirtual 593	org/xmlpull/v1/XmlPullParserFactory:newPullParser	()Lorg/xmlpull/v1/XmlPullParser;
    //   159: astore_3
    //   160: aload_3
    //   161: ifnonnull +98 -> 259
    //   164: new 257	com/fusionone/android/exceptions/SyncPlatformServiceException
    //   167: dup
    //   168: bipush 7
    //   170: new 191	java/lang/StringBuilder
    //   173: dup
    //   174: ldc_w 595
    //   177: invokespecial 196	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   180: aload_1
    //   181: invokevirtual 200	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   184: ldc_w 597
    //   187: invokevirtual 200	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   190: invokevirtual 201	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   193: invokespecial 260	com/fusionone/android/exceptions/SyncPlatformServiceException:<init>	(ILjava/lang/String;)V
    //   196: athrow
    //   197: astore 4
    //   199: aload_1
    //   200: astore_3
    //   201: aload 4
    //   203: astore_1
    //   204: aload_1
    //   205: invokevirtual 600	java/lang/Exception:printStackTrace	()V
    //   208: new 257	com/fusionone/android/exceptions/SyncPlatformServiceException
    //   211: dup
    //   212: bipush 7
    //   214: new 191	java/lang/StringBuilder
    //   217: dup
    //   218: ldc_w 602
    //   221: invokespecial 196	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   224: aload_3
    //   225: invokevirtual 200	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   228: ldc_w 604
    //   231: invokevirtual 200	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   234: aload_1
    //   235: invokevirtual 607	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   238: invokevirtual 200	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   241: invokevirtual 201	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   244: invokespecial 260	com/fusionone/android/exceptions/SyncPlatformServiceException:<init>	(ILjava/lang/String;)V
    //   247: athrow
    //   248: astore_1
    //   249: aload_2
    //   250: ifnull +7 -> 257
    //   253: aload_2
    //   254: invokevirtual 610	java/io/BufferedReader:close	()V
    //   257: aload_1
    //   258: athrow
    //   259: aload_3
    //   260: aload_2
    //   261: invokeinterface 613 2 0
    //   266: aload_0
    //   267: aload_3
    //   268: invokespecial 615	com/fusionone/android/sync/glue/dao/mapping/AndroidDBPullParserMapping:parseXmlMapping	(Lorg/xmlpull/v1/XmlPullParser;)V
    //   271: new 617	com/fusionone/android/sync/glue/dao/mapping/AndroidDBPullParserMapping$AndroidDeviceDBPullParserMapping
    //   274: dup
    //   275: aload_0
    //   276: aload_0
    //   277: getfield 400	com/fusionone/android/sync/glue/dao/mapping/AndroidDBPullParserMapping:log	Lcom/synchronoss/android/util/Log;
    //   280: ldc_w 619
    //   283: invokespecial 622	com/fusionone/android/sync/glue/dao/mapping/AndroidDBPullParserMapping$AndroidDeviceDBPullParserMapping:<init>	(Lcom/fusionone/android/sync/glue/dao/mapping/AndroidDBPullParserMapping;Lcom/synchronoss/android/util/Log;Ljava/lang/String;)V
    //   286: invokevirtual 624	com/fusionone/android/sync/glue/dao/mapping/AndroidDBPullParserMapping$AndroidDeviceDBPullParserMapping:a	()V
    //   289: aload_2
    //   290: invokevirtual 610	java/io/BufferedReader:close	()V
    //   293: return
    //   294: astore_1
    //   295: aload_1
    //   296: invokevirtual 625	java/io/IOException:printStackTrace	()V
    //   299: return
    //   300: astore_2
    //   301: aload_2
    //   302: invokevirtual 625	java/io/IOException:printStackTrace	()V
    //   305: goto -48 -> 257
    //   308: astore_1
    //   309: aconst_null
    //   310: astore_2
    //   311: goto -62 -> 249
    //   314: astore_1
    //   315: goto -66 -> 249
    //   318: astore_1
    //   319: aconst_null
    //   320: astore_3
    //   321: aload 4
    //   323: astore_2
    //   324: goto -120 -> 204
    //   327: astore_2
    //   328: aload_1
    //   329: astore_3
    //   330: aload_2
    //   331: astore_1
    //   332: aload 4
    //   334: astore_2
    //   335: goto -131 -> 204
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	338	0	this	AndroidDBPullParserMapping
    //   109	126	1	localObject1	Object
    //   248	10	1	localObject2	Object
    //   294	2	1	localIOException1	IOException
    //   308	1	1	localObject3	Object
    //   314	1	1	localObject4	Object
    //   318	11	1	localException1	Exception
    //   331	1	1	localObject5	Object
    //   152	138	2	localBufferedReader	java.io.BufferedReader
    //   300	2	2	localIOException2	IOException
    //   310	14	2	localException2	Exception
    //   327	4	2	localException3	Exception
    //   334	1	2	localException4	Exception
    //   159	171	3	localObject6	Object
    //   1	1	4	localObject7	Object
    //   197	136	4	localException5	Exception
    // Exception table:
    //   from	to	target	type
    //   153	160	197	java/lang/Exception
    //   164	197	197	java/lang/Exception
    //   259	289	197	java/lang/Exception
    //   204	248	248	finally
    //   289	293	294	java/io/IOException
    //   253	257	300	java/io/IOException
    //   127	140	308	finally
    //   140	153	308	finally
    //   153	160	314	finally
    //   164	197	314	finally
    //   259	289	314	finally
    //   127	140	318	java/lang/Exception
    //   140	153	327	java/lang/Exception
  }
  
  public void start(BundleContext paramBundleContext)
  {
    this.bundleContext = paramBundleContext;
    this.androidContext = ((Context)paramBundleContext.d(Context.class.getName()));
    this.sessionManager = new SessionManagerImpl(this.androidContext);
    this.log = ((Log)paramBundleContext.d(Log.class.getName()));
  }
  
  public void stop(BundleContext paramBundleContext) {}
}
