import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.RemoteException;
import com.sds.emm.sdk.core.apis.common.EMMException;
import com.sds.emm.sdk.core.apis.sso.EMMSSO;
import com.sds.emm.sdk.core.internal.security.NativeSecurityForJni;
import com.sds.emm.sdk.core.local.clientservice.SeedData;
import com.sds.emm.sdk.core.local.utils.Utils;
import java.util.List;

public class EMMSDK4_lk
  implements EMMSDK4_xx
{
  private static final String d = "EMMPIMSKeyHandler";
  private String g = null;
  private Context k;
  private String u = null;
  
  public EMMSDK4_lk() {}
  
  private final boolean a()
  {
    try
    {
      List localList = this.k.getPackageManager().getInstalledPackages(0);
      int i = 0;
      boolean bool1 = false;
      while (i < localList.size())
      {
        String str = ((PackageInfo)localList.get(i)).packageName;
        boolean bool2 = Utils.isEMMClientInstalled(this.k);
        if (bool2 == true) {
          bool1 = true;
        }
        i += 1;
      }
      return bool1;
    }
    catch (EMMSDK4_kf localEMMSDK4_kf) {}
    return false;
  }
  
  private final String e()
  {
    try
    {
      String str = new NativeSecurityForJni().doCreateEMMClientKey(this.k.getPackageName(), EMMSSO.insert(");,4\0210,2,5.''\003\017", 93));
      return str;
    }
    catch (EMMSDK4_kf localEMMSDK4_kf)
    {
      for (;;) {}
    }
    return null;
  }
  
  private final String w()
  {
    try
    {
      String str = new NativeSecurityForJni().doCreateEMMFamilyKey(EMMSSO.insert("ew``EdxnpirssW[", 17), this.k.getPackageName());
      return str;
    }
    catch (EMMSDK4_kf localEMMSDK4_kf)
    {
      for (;;) {}
    }
    return null;
  }
  
  public String c()
  {
    return this.g;
  }
  
  public byte[] m()
  {
    return null;
  }
  
  public String o(byte[] paramArrayOfByte)
  {
    return null;
  }
  
  public boolean p(Context paramContext)
  {
    this.k = paramContext;
    int j;
    long l;
    short[] arrayOfShort;
    int i;
    if (true == a())
    {
      paramContext = new NativeSecurityForJni();
      j = 0;
      try
      {
        Object localObject1 = EMMSDK4_k.j();
        l = paramContext.doGetCurrentTime();
        localObject1 = ((SeedData)localObject1).getBytes();
        arrayOfShort = new short['¡'];
        i = 0;
      }
      catch (EMMException localEMMException)
      {
        localEMMException.printStackTrace();
        break label85;
      }
      catch (RemoteException localRemoteException)
      {
        localRemoteException.printStackTrace();
      }
      this.u = paramContext.doDecodeSeedToKey(arrayOfShort, l);
    }
    for (;;)
    {
      label85:
      Object localObject2;
      try
      {
        localObject2 = EMMSDK4_k.r(this.k.getPackageName());
        l = paramContext.doGetCurrentTime();
        localObject2 = ((SeedData)localObject2).getBytes();
        arrayOfShort = new short['¡'];
        i = j;
      }
      catch (EMMException paramContext)
      {
        paramContext.printStackTrace();
        return true;
      }
      catch (RemoteException paramContext)
      {
        paramContext.printStackTrace();
        return true;
      }
      this.g = paramContext.doDecodeSeedToKey(arrayOfShort, l);
      return true;
      this.g = e();
      this.u = w();
      return true;
      while (i < 161)
      {
        if (localObject2[i] == 0) {
          arrayOfShort[i] = 300;
        } else {
          arrayOfShort[i] = ((short)localObject2[i]);
        }
        i += 1;
      }
      break;
      while (i < 161)
      {
        if (localObject2[i] == 0) {
          arrayOfShort[i] = 300;
        } else {
          arrayOfShort[i] = ((short)localObject2[i]);
        }
        i += 1;
      }
    }
  }
  
  public byte[] r(String paramString)
  {
    return null;
  }
  
  public boolean t()
  {
    return false;
  }
  
  public String z()
  {
    return this.u;
  }
}
