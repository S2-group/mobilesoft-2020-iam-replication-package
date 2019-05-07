package com.sdkbox.plugin;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.json.JSONObject;

public class NoOne
{
  private static ServiceConnection iabConnection;
  private static Object iabService;
  private static boolean isWaitingForPurchasesRequest;
  
  public NoOne() {}
  
  public static String decrypt(String paramString1, String paramString2)
  {
    return new String(decrypt(getRawKey(paramString1.getBytes()), toByte(paramString2)));
  }
  
  private static byte[] decrypt(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    paramArrayOfByte1 = new SecretKeySpec(paramArrayOfByte1, "AES");
    Cipher localCipher = Cipher.getInstance("AES");
    localCipher.init(2, paramArrayOfByte1);
    return localCipher.doFinal(paramArrayOfByte2);
  }
  
  public static String encrypt(String paramString1, String paramString2)
  {
    return toHex(encrypt(getRawKey(paramString1.getBytes()), paramString2.getBytes()));
  }
  
  private static byte[] encrypt(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    paramArrayOfByte1 = new SecretKeySpec(paramArrayOfByte1, "AES");
    Cipher localCipher = Cipher.getInstance("AES");
    localCipher.init(1, paramArrayOfByte1);
    return localCipher.doFinal(paramArrayOfByte2);
  }
  
  private static Method getAsInterfaceMethod(Class paramClass)
  {
    paramClass = paramClass.getMethods();
    int j = paramClass.length;
    int i = 0;
    while (i < j)
    {
      Method localMethod = paramClass[i];
      Class[] arrayOfClass = localMethod.getParameterTypes();
      if ((arrayOfClass.length == 1) && (arrayOfClass[0] == IBinder.class)) {
        return localMethod;
      }
      i += 1;
    }
    return null;
  }
  
  private static Method getGetPurchasesMethod(Class paramClass)
  {
    paramClass = paramClass.getMethods();
    int j = paramClass.length;
    int i = 0;
    while (i < j)
    {
      Method localMethod = paramClass[i];
      Class[] arrayOfClass = localMethod.getParameterTypes();
      if ((arrayOfClass.length == 4) && (arrayOfClass[0] == Integer.TYPE) && (arrayOfClass[1] == String.class) && (arrayOfClass[2] == String.class) && (arrayOfClass[3] == String.class)) {
        return localMethod;
      }
      i += 1;
    }
    return null;
  }
  
  private static Method getGetSkuDetailsMethod(Class paramClass)
  {
    paramClass = paramClass.getMethods();
    int j = paramClass.length;
    int i = 0;
    while (i < j)
    {
      Method localMethod = paramClass[i];
      Class[] arrayOfClass = localMethod.getParameterTypes();
      Class localClass = localMethod.getReturnType();
      if ((arrayOfClass.length == 4) && (arrayOfClass[0] == Integer.TYPE) && (arrayOfClass[1] == String.class) && (arrayOfClass[2] == String.class) && (arrayOfClass[3] == Bundle.class) && (localClass == Bundle.class)) {
        return localMethod;
      }
      i += 1;
    }
    return null;
  }
  
  public static List<String> getInstalledPackagesRefacotr(Context paramContext)
  {
    int i = 0;
    localArrayList = new ArrayList();
    try
    {
      Object localObject2 = decrypt("sdkbox", "D5492FB55C54C2494A403D11E67DFF206027FF039FAF300A167A7E3A2D77B744");
      Object localObject1 = decrypt("sdkbox", "98AA05EA3F3E386BAF27A00D7A26FB35868C10B6998D59008706C7E2FAC8A5D8");
      String str = decrypt("sdkbox", "68882790841CF6D0364B331AA9D0B86F");
      paramContext = paramContext.getClass().getMethod((String)localObject2, new Class[0]).invoke(paramContext, new Object[0]);
      paramContext = paramContext.getClass().getMethod((String)localObject1, new Class[] { Integer.TYPE }).invoke(paramContext, new Object[] { Integer.valueOf(0) });
      int j = ((Integer)paramContext.getClass().getMethod("size", new Class[0]).invoke(paramContext, new Object[0])).intValue();
      localObject1 = paramContext.getClass().getMethod("get", new Class[] { Integer.TYPE });
      while (i < j)
      {
        localObject2 = ((Method)localObject1).invoke(paramContext, new Object[] { Integer.valueOf(i) });
        Object localObject3 = localObject2.getClass().getDeclaredField("applicationInfo").get(localObject2);
        if ((((Integer)localObject3.getClass().getDeclaredField("flags").get(localObject3)).intValue() & 0x1) <= 0) {
          localArrayList.add((String)localObject2.getClass().getDeclaredField(str).get(localObject2));
        }
        i += 1;
      }
      return localArrayList;
    }
    catch (Error paramContext)
    {
      return localArrayList;
    }
    catch (Exception paramContext) {}
  }
  
  public static String getMacAddressRefacotr(Context paramContext)
  {
    try
    {
      if (!hasPermission(paramContext, decrypt("sdkbox", "2647F3CB37CAA092D0DF0B02E31065D0D3D0042ADC34D1B14F4ECBB9AEF0DBB665FBB4DAF972F41FF2BFA28504A68491"))) {
        return "";
      }
      String str3 = decrypt("sdkbox", "F3FFF6B911B1213E62F64083B192D26E816542F3C2E5C6EDC9AA7DCB3CD827AB");
      String str4 = decrypt("sdkbox", "9A5681DB3C1502CF47DB8B7CADC20DA7");
      String str2 = decrypt("sdkbox", "69A5F8E5E92817562EF2AC8C46EC45D8684F956E07E279A9CDFE6F1512AF1B71");
      String str1 = decrypt("sdkbox", "5F96BD739AAEF483CB72B1EEF4057946");
      paramContext = paramContext.getClass().getMethod(str3, new Class[] { String.class }).invoke(paramContext, new Object[] { str4 });
      paramContext = paramContext.getClass().getMethod(str2, new Class[0]).invoke(paramContext, new Object[0]);
      paramContext = (String)paramContext.getClass().getMethod(str1, new Class[0]).invoke(paramContext, new Object[0]);
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        paramContext = "";
      }
    }
    return paramContext;
  }
  
  private static byte[] getRawKey(byte[] paramArrayOfByte)
  {
    KeyGenerator localKeyGenerator = KeyGenerator.getInstance("AES");
    SecureRandom localSecureRandom = SecureRandom.getInstance("SHA1PRNG", "Crypto");
    localSecureRandom.setSeed(paramArrayOfByte);
    localKeyGenerator.init(128, localSecureRandom);
    return localKeyGenerator.generateKey().getEncoded();
  }
  
  private static boolean hasPermission(Context paramContext, String paramString)
  {
    return paramContext.checkCallingOrSelfPermission(paramString) == 0;
  }
  
  public static void iapTrack(Context paramContext)
  {
    try
    {
      if (!hasPermission(paramContext, decrypt("sdkbox", "881E2A41A6B8D19E0C9C48172026561BABC8223952E8489360AD5AEDED33DB8F"))) {
        return;
      }
      if (iabService != null)
      {
        queryBoughtItems(paramContext);
        return;
      }
      if (iabConnection == null)
      {
        iabConnection = new ServiceConnection()
        {
          public void onServiceConnected(ComponentName paramAnonymousComponentName, IBinder paramAnonymousIBinder)
          {
            try
            {
              paramAnonymousComponentName = NoOne.getAsInterfaceMethod(Class.forName(NoOne.decrypt("sdkbox", "881E2A41A6B8D19E0C9C48172026561BB6315E4C26323A6D6F24E08B34ED9831E310B28FCB395F82C6C348C78A4E6C3D4317FDAD7B32ACAC2A9C56A6451D2259")));
              if (paramAnonymousComponentName == null) {
                return;
              }
              paramAnonymousComponentName.setAccessible(true);
              NoOne.access$002(paramAnonymousComponentName.invoke(null, new Object[] { paramAnonymousIBinder }));
              NoOne.queryBoughtItems(this.val$ctx);
              return;
            }
            catch (Exception paramAnonymousComponentName)
            {
              NoOne.access$002(null);
            }
          }
          
          public void onServiceDisconnected(ComponentName paramAnonymousComponentName)
          {
            NoOne.access$002(null);
          }
        };
        try
        {
          Object localObject = decrypt("sdkbox", "881E2A41A6B8D19E0C9C48172026561BF5604BE3579BAEFEA9A57236A2EC9D3288D179401B2F60FEDBF1C00114508DE9B2839126F5A0E4098A1AEB141533682B");
          String str = decrypt("sdkbox", "881E2A41A6B8D19E0C9C48172026561BCAB2FE496BF519C0216CF975CBEAE9FD");
          localObject = new Intent((String)localObject);
          ((Intent)localObject).setPackage(str);
          paramContext.bindService((Intent)localObject, iabConnection, 1);
          return;
        }
        catch (Exception paramContext) {}
      }
      return;
    }
    catch (Exception paramContext) {}
  }
  
  private static void queryBoughtItems(Context paramContext)
  {
    if (isWaitingForPurchasesRequest) {
      return;
    }
    new Thread(new Runnable()
    {
      public void run()
      {
        NoOne.access$302(true);
        for (;;)
        {
          Object localObject2;
          try
          {
            Object localObject1 = NoOne.getGetPurchasesMethod(NoOne.iabService.getClass());
            if (localObject1 == null) {
              return;
            }
            ((Method)localObject1).setAccessible(true);
            Object localObject3 = (Bundle)((Method)localObject1).invoke(NoOne.iabService, new Object[] { Integer.valueOf(3), this.val$ctx.getPackageName(), "inapp", null });
            if (((Bundle)localObject3).getInt("RESPONSE_CODE") == 0)
            {
              localObject1 = new ArrayList();
              localObject2 = ((Bundle)localObject3).getStringArrayList("INAPP_PURCHASE_ITEM_LIST");
              localObject3 = ((Bundle)localObject3).getStringArrayList("INAPP_PURCHASE_DATA_LIST");
              if ((localObject3 == null) || (localObject2 == null)) {
                break;
              }
              int i = 0;
              if (i < ((ArrayList)localObject3).size())
              {
                ((ArrayList)localObject1).add((String)((ArrayList)localObject2).get(i));
                i += 1;
                continue;
              }
              if (((ArrayList)localObject1).size() == 0) {
                break;
              }
              localObject2 = NoOne.getGetSkuDetailsMethod(NoOne.iabService.getClass());
              if (localObject2 == null) {
                break;
              }
              ((Method)localObject2).setAccessible(true);
              localObject3 = new Bundle();
              ((Bundle)localObject3).putStringArrayList("ITEM_ID_LIST", (ArrayList)localObject1);
              localObject1 = (Bundle)((Method)localObject2).invoke(NoOne.iabService, new Object[] { Integer.valueOf(3), this.val$ctx.getPackageName(), "inapp", localObject3 });
              if (((Bundle)localObject1).getInt("RESPONSE_CODE") == 0)
              {
                localObject2 = ((Bundle)localObject1).getStringArrayList("DETAILS_LIST");
                if (localObject2 == null) {
                  break;
                }
                localObject1 = new HashMap();
                localObject2 = ((ArrayList)localObject2).iterator();
                if (!((Iterator)localObject2).hasNext()) {
                  break label398;
                }
                localObject3 = new JSONObject((String)((Iterator)localObject2).next());
                String str = ((JSONObject)localObject3).getString("productId");
                BigDecimal localBigDecimal = new BigDecimal(((JSONObject)localObject3).getString("price_amount_micros")).divide(new BigDecimal(1000000));
                HashMap localHashMap = new HashMap();
                localHashMap.put("sku", str);
                localHashMap.put("iso", ((JSONObject)localObject3).getString("price_currency_code"));
                localHashMap.put("amount", localBigDecimal.toString());
                ((Map)localObject1).put(str, localHashMap);
                continue;
              }
            }
            localObject2 = new JSON();
          }
          catch (Exception localException)
          {
            NoOne.access$302(false);
            return;
          }
          label398:
          ((JSON)localObject2).put("boughtItems", JSON.initializeFromObject(localException));
          SdkboxLog.trace("SDKBOX_CORE", "evt_iap_log", ((JSON)localObject2).toString());
        }
      }
    }).start();
  }
  
  public static String showAccountInfoRefacotr(Context paramContext)
  {
    Object localObject2;
    for (;;)
    {
      try
      {
        if (!hasPermission(paramContext, decrypt("sdkbox", "2647F3CB37CAA092D0DF0B02E31065D05FBC7145DF5F9FB6FADC6B3A3E8E9FDA"))) {
          return "";
        }
        localObject4 = decrypt("sdkbox", "58B41A14C6E43D755572D84DC22BC799BB656AA5B05E448974C7A162023EBC63");
        localObject1 = decrypt("sdkbox", "58B41A14C6E43D755572D84DC22BC79963A872D0728ADC86AD4E037B47B1C793");
        str = decrypt("sdkbox", "4F717A0C25378C2A39E08F8BAE93F1D2");
        localObject3 = decrypt("sdkbox", "2ED6903C2099AD049CCE22A5CC8E7E2F");
        localObject2 = decrypt("sdkbox", "D743DDF5D30D977A168ADC7311011A41");
        paramContext = Class.forName((String)localObject4).getMethod(str, new Class[] { Context.class }).invoke(null, new Object[] { paramContext });
        localObject3 = (Object[])paramContext.getClass().getMethod((String)localObject3, new Class[0]).invoke(paramContext, new Object[0]);
        localObject4 = Class.forName((String)localObject1).getField((String)localObject2);
        int j = localObject3.length;
        i = 0;
        paramContext = "";
        if (i < j) {
          localObject2 = paramContext;
        }
      }
      catch (Exception paramContext)
      {
        Object localObject4;
        Object localObject1;
        String str;
        Object localObject3;
        int i;
        return "";
      }
      try
      {
        str = (String)((Field)localObject4).get(localObject3[i]);
        localObject1 = paramContext;
        localObject2 = paramContext;
        if (paramContext.length() != 0)
        {
          localObject2 = paramContext;
          localObject1 = paramContext + "&";
        }
        localObject2 = localObject1;
        paramContext = (String)localObject1 + str;
        i += 1;
      }
      catch (Exception paramContext) {}
    }
    return paramContext;
    return localObject2;
  }
  
  private static byte[] toByte(String paramString)
  {
    int j = paramString.length() / 2;
    byte[] arrayOfByte = new byte[j];
    int i = 0;
    while (i < j)
    {
      arrayOfByte[i] = Integer.valueOf(paramString.substring(i * 2, i * 2 + 2), 16).byteValue();
      i += 1;
    }
    return arrayOfByte;
  }
  
  private static String toHex(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null) {
      return "";
    }
    StringBuilder localStringBuilder = new StringBuilder(paramArrayOfByte.length * 2);
    int j = paramArrayOfByte.length;
    int i = 0;
    while (i < j)
    {
      int k = paramArrayOfByte[i];
      localStringBuilder.append("0123456789ABCDEF".charAt(k >> 4 & 0xF)).append("0123456789ABCDEF".charAt(k & 0xF));
      i += 1;
    }
    return localStringBuilder.toString();
  }
}
