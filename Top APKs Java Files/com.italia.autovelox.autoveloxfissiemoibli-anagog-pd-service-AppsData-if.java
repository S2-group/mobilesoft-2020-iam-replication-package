package anagog.pd.service.AppsData;

import anagog.pd.service.MobilityService;
import anagog.pd.service.NotificationLogManager;
import anagog.pd.service.ServiceLoggingSettings.LogType;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class if
{
  private static final String ʻ;
  private static final String ʽ;
  private static final String ˊ;
  private static final String ˋ;
  private static int ˋॱ;
  private static final String ˎ;
  private static final String ˏ;
  private static int ˏॱ = 1;
  private static final String ॱ;
  private static final byte[] ॱˊ = { 31, -58, 93, -59, 30, 13, 0, 1, 5, -84, 67, 15, -13, -4, 19, -15, -69, 42, 9, -4, -1, -46, 79, -13, 8, -5, -2, 17, -84, 70, 12, -3, -2, -77, 40, 25, 18, -11, -21, 18, 15, -84, 70, 9, 3, -82, 85, -2, -14, 13, -82, 65, 15, 0, 3, -83, 68, -3, 19, -19, 30, 13, 0, 1, 5, -84, 67, 15, -13, -4, 19, -15, -69, 42, 9, -4, -1, -46, 79, -13, 8, -5, -2, 17, -84, 70, 9, 3, -82, 85, -2, -14, 13, -82, 65, 15, 0, 3, -83, 68, -3, 19, -19, 17, 2, 8, -10, 6, -2, -23, 19, 12, -8, 33, -5, 1, 0, -11, -1, 35, 9, 1, 1, -43, 37, 5, 1, -19, 11, 0, -24, 21, 4, -8, 21, 18, 1, -31, 27, -12, -3, 19, -15, -17, 21, 4, -8, 37, 5, 1, -19, 11, 0, -7, -1, 35, -7, 0, -5, 5, -7, 9, 5, 51, 38, -6, 1, -15, 8, 19, 12, -8, 30, 13, 0, 1, 5, -84, 67, 15, -13, -4, 19, -15, -69, 42, 9, -4, -1, -46, 79, -13, 8, -5, -2, 17, -84, 70, 9, 3, -82, 65, 13, -78, 73, 5, 5, 1, -19, 11, 0, -7, -1, -68, 65, 15, 0 };
  private static int ॱˋ;
  private static final String ᐝ;
  private Context ʼ;
  private final PackageManager ˊॱ;
  
  static
  {
    ˋॱ = 139;
    ॱˋ = 0;
  }
  
  public if(Context paramContext)
  {
    this.ʼ = paramContext.getApplicationContext();
    this.ˊॱ = this.ʼ.getPackageManager();
  }
  
  private static String ˊ(byte paramByte, short paramShort1, short paramShort2)
  {
    byte[] arrayOfByte2 = ॱˊ;
    paramShort1 += 4;
    int j = 57 - paramByte;
    byte[] arrayOfByte1 = new byte[j];
    int i;
    short s1;
    if (arrayOfByte2 == null)
    {
      paramShort2 = 0;
      paramByte = paramShort1;
      i = j;
      s1 = paramShort1;
    }
    for (;;)
    {
      paramByte += 1;
      short s2;
      for (paramShort1 = i + s1;; paramShort1 = s1)
      {
        s2 = paramShort2 + 1;
        arrayOfByte1[paramShort2] = ((byte)paramShort1);
        if (s2 != j) {
          break;
        }
        return new String(arrayOfByte1, 0).intern();
        paramByte = paramShort1;
        paramShort1 = 0;
        s1 = 83 - paramShort2;
        paramShort2 = paramShort1;
      }
      s1 = arrayOfByte2[paramByte];
      i = paramShort1;
      paramShort2 = s2;
    }
  }
  
  private JSONObject ˎ(PackageInfo paramPackageInfo)
  {
    JSONObject localJSONObject = new JSONObject();
    int j = Build.VERSION.SDK_INT;
    int i;
    boolean bool;
    if (j >= 12)
    {
      for (;;)
      {
        i = 87;
        switch (i)
        {
        }
      }
      bool = false;
    }
    for (;;)
    {
      try
      {
        localJSONObject.put(ˊ((byte)(ॱˊ['£'] - 1), (short)109, (byte)ॱˊ[6]), bool);
        if ((paramPackageInfo.applicationInfo.flags & 0x1) == 0) {
          break label797;
        }
      }
      catch (JSONException paramPackageInfo)
      {
        NotificationLogManager.getInstance().log(ServiceLoggingSettings.LogType.GENERAL, new Object[] { ˊ((byte)ॱˊ[''], (short)'¨', (byte)(ॱˊ[27] - 1)) });
        return localJSONObject;
      }
      localJSONObject.put(ˊ((byte)ॱˊ['£'], (short)' ', (byte)ॱˊ[6]), bool);
      if (j < 9) {
        break label880;
      }
      i = 47;
      break label853;
      i = paramPackageInfo.applicationInfo.flags;
      if ((i & 0x200000) != 0) {
        i = 0;
      }
      switch (i)
      {
      case 1: 
      default: 
        i = 1;
        continue;
        bool = true;
        continue;
        i = ॱˋ + 5;
        ˏॱ = i % 128;
        if (i % 2 == 0) {
          i = 65;
        }
        switch (i)
        {
        default: 
          i = 15;
          continue;
          localJSONObject.put(ˊ((byte)(ॱˊ[17] - 1), (short)115, (byte)ॱˊ[5]), String.format(Locale.US, ˊ((byte)53, (short)'', (byte)-ॱˊ[21]), new Object[] { Double.valueOf(paramPackageInfo.firstInstallTime / 1000.0D) }));
          localJSONObject.put(ˊ((byte)-ॱˊ[123], (short)(ˋॱ & 0x3E6), (byte)-ॱˊ['']), String.format(Locale.US, ˊ((byte)53, (short)'', (byte)-ॱˊ[21]), new Object[] { Double.valueOf(paramPackageInfo.lastUpdateTime / 1000.0D) }));
          i = ˏॱ + 99;
          ॱˋ = i % 128;
          if (i % 2 != 0) {}
          localJSONObject.put(ˊ((byte)-ॱˊ[21], (short)99, (byte)ॱˊ[44]), paramPackageInfo.packageName);
          localJSONObject.put(ˊ((byte)53, (short)'¥', (byte)ॱˊ[8]), paramPackageInfo.applicationInfo.loadLabel(this.ˊॱ).toString());
          i = ˏॱ + 25;
          ॱˋ = i % 128;
          if (i % 2 != 0) {}
          return localJSONObject;
          i = ॱˋ + 17;
          ˏॱ = i % 128;
          if (i % 2 == 0)
          {
            for (;;)
            {
              i = 1;
              switch (i)
              {
              }
            }
            i = paramPackageInfo.applicationInfo.flags;
            if ((i & 0x200000) != 0) {}
            for (i = 80;; i = 97) {
              switch (i)
              {
              }
            }
          }
          break;
        }
      case 0: 
        label580:
        i = ॱˋ + 57;
        ˏॱ = i % 128;
        if (i % 2 == 0) {}
        for (i = 30;; i = 33) {
          switch (i)
          {
          }
        }
        bool = true;
        continue;
        localJSONObject.put(ˊ((byte)ॱˊ['£'], (short)' ', (byte)ॱˊ[6]), true);
        if (j >= 9) {}
        label759:
        for (i = 28;; i = 54)
        {
          switch (i)
          {
          }
          break label759;
          i = 79;
          break;
          label797:
          i = 0;
          break label821;
          i = 0;
          break label580;
        }
        bool = true;
        continue;
        for (;;)
        {
          i = 1;
          label821:
          switch (i)
          {
          }
        }
        bool = false;
      }
    }
    for (;;)
    {
      label853:
      switch (i)
      {
      }
      label880:
      i = 92;
    }
  }
  
  private List<PackageInfo> ˏ()
  {
    return this.ˊॱ.getInstalledPackages(0);
  }
  
  private JSONArray ॱ()
  {
    return ॱ(MobilityService.getListOfRunningApps());
  }
  
  private JSONArray ॱ(HashSet<String> paramHashSet)
  {
    JSONArray localJSONArray = new JSONArray();
    paramHashSet = paramHashSet.iterator();
    for (;;)
    {
      if (paramHashSet.hasNext()) {}
      for (int i = 0;; i = 1) {
        switch (i)
        {
        }
      }
      i = ˏॱ + 7;
      ॱˋ = i % 128;
      if (i % 2 != 0) {}
      String str = (String)paramHashSet.next();
      try
      {
        localObject = new JSONObject();
        ((JSONObject)localObject).put(ˊ((byte)-ॱˊ[21], (short)99, (byte)ॱˊ[44]), str);
        localJSONArray.put(localObject);
      }
      catch (JSONException localJSONException)
      {
        NotificationLogManager localNotificationLogManager = NotificationLogManager.getInstance();
        Object localObject = ServiceLoggingSettings.LogType.GENERAL;
        byte b = (byte)ॱˊ[6];
        short s = (short)b;
        localNotificationLogManager.log((ServiceLoggingSettings.LogType)localObject, new Object[] { ˊ(b, s, (byte)(s | 0x10)) });
      }
    }
    return localJSONArray;
  }
  
  public JSONObject ˋ()
  {
    JSONObject localJSONObject = new JSONObject();
    JSONArray localJSONArray = new JSONArray();
    Object localObject2 = ˏ();
    Object localObject1 = ॱ();
    if (localObject2 != null)
    {
      for (;;)
      {
        i = 83;
        label38:
        switch (i)
        {
        }
      }
      localObject2 = ((List)localObject2).iterator();
      if (!((Iterator)localObject2).hasNext()) {
        break label401;
      }
      for (;;)
      {
        i = 38;
        label89:
        switch (i)
        {
        }
      }
      i = ॱˋ + 103;
      ˏॱ = i % 128;
      if (i % 2 == 0) {}
      localJSONArray.put(ˎ((PackageInfo)((Iterator)localObject2).next()));
      i = ॱˋ + 31;
      ˏॱ = i % 128;
      if (i % 2 != 0) {
        break label407;
      }
    }
    label401:
    label407:
    for (int i = 0;; i = 1) {
      switch (i)
      {
      case 1: 
      default: 
        break;
      case 0: 
        if (((Iterator)localObject2).hasNext()) {}
        for (i = 43;; i = 77) {
          switch (i)
          {
          }
        }
        byte b = (byte)48;
        try
        {
          localJSONObject.put(ˊ(b, (short)(ˋॱ + 4), (byte)-ॱˊ[106]), localJSONArray);
          localJSONObject.put(ˊ((byte)(ॱˊ['£'] - 1), (short)'', (byte)ॱˊ[7]), localObject1);
          i = ॱˋ + 105;
          ˏॱ = i % 128;
          if (i % 2 == 0) {}
          return localJSONObject;
        }
        catch (JSONException localJSONException)
        {
          NotificationLogManager localNotificationLogManager = NotificationLogManager.getInstance();
          localObject1 = ServiceLoggingSettings.LogType.GENERAL;
          b = (byte)ॱˊ[5];
          short s = (short)56;
          localNotificationLogManager.log((ServiceLoggingSettings.LogType)localObject1, new Object[] { ˊ(b, s, (byte)(s & 0x54)) });
          return localJSONObject;
        }
        i = 90;
        break label38;
        i = 87;
        break label89;
      }
    }
  }
}
