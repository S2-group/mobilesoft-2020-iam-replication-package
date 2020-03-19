package com.citrix.work.MAM.encryption;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import com.citrix.mdx.lib.PolicyParser;
import com.citrix.work.database.AndroidDatabaseHelper;
import com.citrix.work.database.Dao.Application;
import com.citrix.work.database.helpers.ApplicationHelper;
import com.citrix.work.database.helpers.MobileOfflineKeysHelper;
import com.citrix.work.deliveryservices.DSClientExecutionContext;
import com.citrix.work.deliveryservices.devicemanagement.DeviceManagementUtility;
import com.citrix.work.deliveryservices.keymanagementservice.KeyManagementServiceClient;
import com.citrix.work.deliveryservices.utilities.DeliveryServicesException;
import com.citrix.work.enums.AsyncTaskStatus;
import com.citrix.work.log.Logger;
import com.citrix.work.profile.ProfileData;
import com.citrix.work.profile.ProfileManager;
import com.zenprise.monitor.Monitor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class EncryptionKeyManager
{
  public static final int ENCRYPTION_VERSION1 = 1;
  public static final int ENCRYPTION_VERSION2 = 2;
  private static final String KEY_NEEDS_AUTHENTICATION = "NeedsAuthentication";
  private static final Logger m_logger = Logger.getLogger(EncryptionKeyManager.class);
  private AndroidDatabaseHelper m_db;
  private String m_deviceId;
  private EncryptionKeyStorage m_encKeyStorage;
  private int m_profileId;
  private List<String> m_vaultServerURLs = null;
  
  public EncryptionKeyManager(Context paramContext, int paramInt)
  {
    this.m_profileId = paramInt;
    this.m_db = AndroidDatabaseHelper.getHelper(paramContext);
    this.m_deviceId = DeviceManagementUtility.getDeviceToken(paramContext.getContentResolver());
    this.m_encKeyStorage = new EncryptionKeyStorageVault(paramContext);
  }
  
  private static byte[] decode64(String paramString)
  {
    String str = paramString;
    int i = paramString.indexOf('=');
    if (i > 0) {
      str = paramString.substring(0, i);
    }
    return Base64.decode(str, 0);
  }
  
  private void eraseStoredSecrets(String paramString1, String paramString2)
  {
    m_logger.i("Erasing stored secrets");
    if ((paramString1 != null) && (paramString1.equals(paramString2))) {
      this.m_encKeyStorage.eraseStoredSecrets(this.m_profileId, paramString1, paramString2);
    }
  }
  
  public static void fetchEncryptionSecretsForAllApps(int paramInt)
  {
    new EncryptionKeyfetchThread(paramInt).start();
  }
  
  public static void fetchEncryptionSecretsForAllInstalledApps(DSClientExecutionContext paramDSClientExecutionContext, int paramInt)
  {
    Iterator localIterator = ApplicationHelper.getInstalledApplicationsList(AndroidDatabaseHelper.getHelper(Monitor.getAppContext()), paramInt).iterator();
    while (localIterator.hasNext())
    {
      Application localApplication = (Application)localIterator.next();
      try
      {
        refetchEncryptionKeysOnlineAndSave(paramDSClientExecutionContext, Monitor.getAppContext(), localApplication);
      }
      catch (DeliveryServicesException localDeliveryServicesException)
      {
        localDeliveryServicesException.printStackTrace();
      }
    }
  }
  
  private void getEncryptionKey(DSClientExecutionContext paramDSClientExecutionContext, String paramString1, String paramString2, PolicyParser paramPolicyParser, Bundle paramBundle, int paramInt)
    throws DeliveryServicesException
  {
    m_logger.v("getKeys entry");
    paramDSClientExecutionContext = getEncryptionKey(paramDSClientExecutionContext, paramPolicyParser, paramString1, paramString2, paramInt);
    if (paramDSClientExecutionContext != null) {
      putKeyIntoBundle(paramBundle, paramDSClientExecutionContext, paramString1, paramString2, paramInt);
    }
    while (ProfileManager.getProfile(this.m_db, this.m_profileId).isLoggedIn()) {
      return;
    }
    paramBundle.putBoolean("NeedsAuthentication", true);
  }
  
  private byte[] getEncryptionKeyOffline(String paramString1, String paramString2, int paramInt)
  {
    m_logger.d("getting encryption keys offline");
    paramString1 = retrieveSecrets(paramString2);
    if ((paramString1 != null) && (paramString1.length == 2) && (paramString1[0] != null) && (paramString1[1] != null))
    {
      paramString1 = EncryptionSecrets.calculateKey(paramString1[0], paramString1[1], this.m_deviceId, paramInt);
      if (paramString1 != null)
      {
        m_logger.i("Returning saved offline key");
        return paramString1;
      }
      m_logger.i("Could not calculate offline key");
      return paramString1;
    }
    m_logger.i("No saved keys");
    return null;
  }
  
  private byte[] getEncryptionKeyOnline(DSClientExecutionContext paramDSClientExecutionContext, String paramString1, String paramString2, boolean paramBoolean, int paramInt)
    throws DeliveryServicesException
  {
    m_logger.d("getting encryption keys online");
    paramDSClientExecutionContext.throwIfCancelled();
    paramString1 = null;
    Object localObject1 = ProfileManager.getProfile(this.m_db, this.m_profileId);
    if (localObject1 != null)
    {
      m_logger.i("Calling retrieveKeys");
      Object localObject2 = retrieveKeys(paramDSClientExecutionContext, (ProfileData)localObject1, paramString2);
      m_logger.i("retrieveKeys returned");
      paramDSClientExecutionContext = paramString1;
      if (localObject2 != null)
      {
        paramDSClientExecutionContext = paramString1;
        if (((List)localObject2).size() == 2)
        {
          paramDSClientExecutionContext = paramString1;
          if (((List)localObject2).get(0) != null)
          {
            paramDSClientExecutionContext = paramString1;
            if (((List)localObject2).get(1) != null)
            {
              localObject1 = decode64((String)((List)localObject2).get(0));
              localObject2 = decode64((String)((List)localObject2).get(1));
              paramString1 = EncryptionSecrets.calculateKey((byte[])localObject1, (byte[])localObject2, this.m_deviceId, paramInt);
              paramDSClientExecutionContext = paramString1;
              if (paramString1 != null)
              {
                paramDSClientExecutionContext = paramString1;
                if (paramBoolean)
                {
                  storeSecrets(paramString2, (byte[])localObject1, (byte[])localObject2);
                  paramDSClientExecutionContext = paramString1;
                }
              }
            }
          }
        }
      }
      return paramDSClientExecutionContext;
    }
    m_logger.e("Invalid Profile");
    return null;
  }
  
  public static String getSecurityGroupVaultName(PolicyParser paramPolicyParser)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (paramPolicyParser != null)
    {
      paramPolicyParser = paramPolicyParser.getString("SecurityGroup");
      localObject1 = localObject2;
      if (paramPolicyParser != null) {
        localObject1 = "secgroup:" + paramPolicyParser.trim().toLowerCase(Locale.getDefault());
      }
    }
    return localObject1;
  }
  
  private List<String> getVaultServers(DSClientExecutionContext paramDSClientExecutionContext, ProfileData paramProfileData)
    throws DeliveryServicesException
  {
    try
    {
      if (this.m_vaultServerURLs == null)
      {
        this.m_vaultServerURLs = paramProfileData.getKeyManagementService().getVaultServerURLs(paramDSClientExecutionContext);
        if (this.m_vaultServerURLs == null) {
          m_logger.e("No key management service address");
        }
      }
      paramDSClientExecutionContext = this.m_vaultServerURLs;
      return paramDSClientExecutionContext;
    }
    finally {}
  }
  
  public static boolean isEncryptionEnabled(PolicyParser paramPolicyParser)
  {
    String str = paramPolicyParser.getString("PublicFileEncryptionEnum");
    paramPolicyParser = paramPolicyParser.getString("PrivateFileEncryptionEnum");
    if (("Disabled".equals(str)) && ("Disabled".equals(paramPolicyParser)))
    {
      m_logger.i("Encryption is disabled");
      return false;
    }
    m_logger.i("Encryption is enabled");
    return true;
  }
  
  public static boolean isOfflineKeysAllowed(PolicyParser paramPolicyParser)
  {
    return "Offline".equals(paramPolicyParser.getString("EncryptionKeys"));
  }
  
  public static void migrateStoredSecretsToVault(Context paramContext)
  {
    EncryptionKeyStorageVault localEncryptionKeyStorageVault = new EncryptionKeyStorageVault(paramContext);
    paramContext = AndroidDatabaseHelper.getHelper(paramContext);
    EncryptionSecrets[] arrayOfEncryptionSecrets = MobileOfflineKeysHelper.getAllSecretsFromOfflineKeyDB(paramContext);
    if ((arrayOfEncryptionSecrets != null) && (arrayOfEncryptionSecrets.length > 0))
    {
      m_logger.i("Migrating " + arrayOfEncryptionSecrets.length + " secrets...");
      int j = arrayOfEncryptionSecrets.length;
      int i = 0;
      if (i < j)
      {
        EncryptionSecrets localEncryptionSecrets = arrayOfEncryptionSecrets[i];
        byte[][] arrayOfByte = localEncryptionSecrets.retrieveDecryptedSecret();
        if ((arrayOfByte != null) && (arrayOfByte.length == 2)) {
          localEncryptionKeyStorageVault.storeSecrets(localEncryptionSecrets.getProfileId(), localEncryptionSecrets.getVaultName(), arrayOfByte[0], arrayOfByte[1]);
        }
        for (;;)
        {
          i += 1;
          break;
          m_logger.w("Could not decrypt secrets for " + localEncryptionSecrets.getVaultName());
        }
      }
      MobileOfflineKeysHelper.deleteAllOfflineKeys(paramContext);
      m_logger.i("Migration of secrets is complete.");
      return;
    }
    m_logger.i("No secrets to migrate");
  }
  
  private static void putKeyIntoBundle(Bundle paramBundle, byte[] paramArrayOfByte, String paramString1, String paramString2, int paramInt)
  {
    m_logger.v("Putting Key into Bundle, encryption Version = " + paramInt);
    String str = "Mam_Encryption_Key";
    if (paramInt == 2) {
      str = "Mam_Encryption_Key" + 2;
    }
    if ((paramString1 != null) && (paramString1.equals(paramString2)))
    {
      paramBundle.putByteArray(str, paramArrayOfByte);
      return;
    }
    paramBundle.putByteArray(str + "-vault-" + paramString2, paramArrayOfByte);
  }
  
  public static void refetchEncryptionKeysOnlineAndSave(DSClientExecutionContext paramDSClientExecutionContext, Context paramContext, Application paramApplication)
    throws DeliveryServicesException
  {
    Object localObject = paramApplication.m_mampolicies;
    if (!TextUtils.isEmpty((CharSequence)localObject))
    {
      localObject = new PolicyParser((String)localObject);
      boolean bool = isOfflineKeysAllowed((PolicyParser)localObject);
      if ((isEncryptionEnabled((PolicyParser)localObject)) && (bool))
      {
        paramContext = new EncryptionKeyManager(paramContext, paramApplication.m_profileId);
        if (paramContext.getEncryptionKeyOnline(paramDSClientExecutionContext, paramApplication.m_mamPackage, paramApplication.m_mamPackage, bool, 1) == null) {
          break label101;
        }
        localObject = getSecurityGroupVaultName((PolicyParser)localObject);
        if (localObject != null) {
          paramContext.getEncryptionKeyOnline(paramDSClientExecutionContext, paramApplication.m_mamPackage, (String)localObject, bool, 1);
        }
      }
      return;
      label101:
      throw new DeliveryServicesException(AsyncTaskStatus.StatusErrorEncryption);
    }
    m_logger.d("Failure getting either profileId or policy string");
  }
  
  private List<String> retrieveKeys(DSClientExecutionContext paramDSClientExecutionContext, ProfileData paramProfileData, String paramString)
    throws DeliveryServicesException
  {
    Iterator localIterator = null;
    ArrayList localArrayList = null;
    List localList = getVaultServers(paramDSClientExecutionContext, paramProfileData);
    Object localObject = localIterator;
    if (localList != null)
    {
      localObject = localIterator;
      if (!localList.isEmpty())
      {
        if (0 == 0) {
          localArrayList = new ArrayList();
        }
        localIterator = localList.iterator();
        for (;;)
        {
          localObject = localArrayList;
          if (!localIterator.hasNext()) {
            break;
          }
          localObject = (String)localIterator.next();
          localObject = new MamEncryptionKeyRetrieverThread(paramDSClientExecutionContext, paramProfileData, (String)localObject + "/retrieve_key", paramString);
          ((MamEncryptionKeyRetrieverThread)localObject).run();
          if (((MamEncryptionKeyRetrieverThread)localObject).m_result != AsyncTaskStatus.StatusSuccess) {
            throw new DeliveryServicesException(((MamEncryptionKeyRetrieverThread)localObject).m_result);
          }
          localArrayList.add(((MamEncryptionKeyRetrieverThread)localObject).m_resultDecodedKey);
          paramDSClientExecutionContext.throwIfCancelled();
        }
      }
    }
    return localObject;
  }
  
  private byte[][] retrieveSecrets(String paramString)
  {
    m_logger.w("Retrieving secrets");
    byte[][] arrayOfByte = this.m_encKeyStorage.retrieveSecrets(this.m_profileId, paramString);
    Logger localLogger = m_logger;
    StringBuilder localStringBuilder = new StringBuilder().append("Retrieved secrets: ");
    if (arrayOfByte == null) {}
    for (paramString = "null";; paramString = "not null")
    {
      localLogger.w(paramString);
      return arrayOfByte;
    }
  }
  
  private void storeSecrets(String paramString, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    m_logger.w("Storing secrets");
    this.m_encKeyStorage.storeSecrets(this.m_profileId, paramString, paramArrayOfByte1, paramArrayOfByte2);
    m_logger.w("Stored secrets");
  }
  
  public byte[] getEncryptionKey(DSClientExecutionContext paramDSClientExecutionContext, PolicyParser paramPolicyParser, String paramString1, String paramString2, int paramInt)
    throws DeliveryServicesException
  {
    Object localObject = null;
    m_logger.v("getKeys entry");
    if (!this.m_encKeyStorage.isReady())
    {
      m_logger.w("getEncryptionKey: storage isn't ready; returning null");
      return localObject;
    }
    if (this.m_profileId == -1)
    {
      m_logger.e("Can't get keys; bad profile id");
      return null;
    }
    localObject = null;
    boolean bool = isOfflineKeysAllowed(paramPolicyParser);
    if (bool) {}
    for (paramPolicyParser = getEncryptionKeyOffline(paramString1, paramString2, paramInt);; paramPolicyParser = (PolicyParser)localObject)
    {
      localObject = paramPolicyParser;
      if (paramPolicyParser != null) {
        break;
      }
      paramDSClientExecutionContext.throwIfCancelled();
      paramDSClientExecutionContext = getEncryptionKeyOnline(paramDSClientExecutionContext, paramString1, paramString2, bool, paramInt);
      if (paramDSClientExecutionContext == null) {
        break label130;
      }
      m_logger.i("Got encryption keys online");
      return paramDSClientExecutionContext;
      eraseStoredSecrets(paramString1, paramString2);
    }
    label130:
    m_logger.i("Could not get encryption keys online");
    return paramDSClientExecutionContext;
  }
  
  public void getEncryptionKeys(DSClientExecutionContext paramDSClientExecutionContext, String paramString1, String paramString2, PolicyParser paramPolicyParser, Bundle paramBundle)
    throws DeliveryServicesException
  {
    if (this.m_encKeyStorage.isReady())
    {
      getEncryptionKey(paramDSClientExecutionContext, paramString1, paramString2, paramPolicyParser, paramBundle, 1);
      getEncryptionKey(paramDSClientExecutionContext, paramString1, paramString2, paramPolicyParser, paramBundle, 2);
      return;
    }
    paramBundle.putBoolean("UE_Needed_For_Encryption", true);
  }
  
  public List<String> getVaults(DSClientExecutionContext paramDSClientExecutionContext, ProfileData paramProfileData)
    throws DeliveryServicesException
  {
    m_logger.v("getVaults entry");
    return paramProfileData.getKeyManagementService().getVaultServerURLs(paramDSClientExecutionContext);
  }
  
  private class MamEncryptionKeyRetrieverThread
    extends Thread
  {
    private DSClientExecutionContext m_context;
    private ProfileData m_profileData;
    public AsyncTaskStatus m_result;
    public String m_resultDecodedKey;
    private String m_vaultName;
    private String m_vaultserverAddress;
    
    public MamEncryptionKeyRetrieverThread(DSClientExecutionContext paramDSClientExecutionContext, ProfileData paramProfileData, String paramString1, String paramString2)
    {
      this.m_context = paramDSClientExecutionContext;
      this.m_vaultserverAddress = paramString1;
      this.m_vaultName = paramString2;
      this.m_result = null;
      this.m_profileData = paramProfileData;
    }
    
    public void run()
    {
      long l = System.currentTimeMillis();
      try
      {
        this.m_context.throwIfCancelled();
        this.m_resultDecodedKey = this.m_profileData.getKeyManagementService().retrieveKey(this.m_context, this.m_vaultserverAddress, this.m_vaultName);
        this.m_result = AsyncTaskStatus.StatusSuccess;
        EncryptionKeyManager.m_logger.d("MamEncryptionKeyRetrieverThread time = " + (System.currentTimeMillis() - l) + " ms");
        return;
      }
      catch (DeliveryServicesException localDeliveryServicesException)
      {
        for (;;)
        {
          this.m_result = localDeliveryServicesException.getErrorCode();
        }
      }
    }
  }
}
