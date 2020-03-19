import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import com.rsupport.common.log.a;
import com.rsupport.common.misc.g;
import com.rsupport.mvagent.dto.gson.NotifyCallGSon;
import com.rsupport.mvagent.h;
import java.io.UnsupportedEncodingException;
import java.util.List;

public final class so
  implements sd, st
{
  private Context Zc = null;
  protected sh aRm = null;
  protected sp aRn = null;
  protected sf aRo = null;
  private sc aRp = null;
  protected ji aRq = null;
  protected List<PackageInfo> aRr = null;
  
  public so(Context paramContext)
  {
    this.Zc = paramContext;
    this.aRr = paramContext.getPackageManager().getInstalledPackages(0);
    this.aRp = ((h)paramContext.getApplicationContext()).zT();
  }
  
  private ResolveInfo Gv()
  {
    if (this.Zc == null) {}
    PackageManager localPackageManager;
    List localList;
    do
    {
      return null;
      localObject = new Intent("android.intent.action.SENDTO", Uri.parse("sms:00000000"));
      localPackageManager = this.Zc.getPackageManager();
      localList = localPackageManager.queryIntentActivities((Intent)localObject, 65536);
      if (localList.size() == 1) {
        return (ResolveInfo)localList.get(0);
      }
    } while (localList.size() == 0);
    Object localObject = localPackageManager.resolveActivity((Intent)localObject, 65536);
    int j = localList.size();
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        return (ResolveInfo)localList.get(0);
      }
      if (((ResolveInfo)localList.get(i)).activityInfo.packageName.equals(((ResolveInfo)localObject).activityInfo.packageName)) {
        return (ResolveInfo)localList.get(i);
      }
      i += 1;
    }
  }
  
  private ResolveInfo Gw()
  {
    if (this.Zc == null) {
      return null;
    }
    Object localObject = new Intent("android.intent.action.CALL", Uri.parse("tel:00000000"));
    PackageManager localPackageManager = this.Zc.getPackageManager();
    List localList = localPackageManager.queryIntentActivities((Intent)localObject, 65536);
    if (localList.size() == 1) {
      return (ResolveInfo)localList.get(0);
    }
    localObject = localPackageManager.resolveActivity((Intent)localObject, 65536);
    int j = localList.size();
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        return (ResolveInfo)localList.get(0);
      }
      if (((ResolveInfo)localList.get(i)).activityInfo.packageName.equals(((ResolveInfo)localObject).activityInfo.packageName)) {
        return (ResolveInfo)localList.get(i);
      }
      i += 1;
    }
  }
  
  private void Gx()
  {
    Object localObject1 = this.Zc.getPackageName();
    Object localObject2;
    try
    {
      this.aRo.ay(((String)localObject1).getBytes("UTF-16LE"));
      if (this.Zc != null)
      {
        localObject3 = new Intent("android.intent.action.SENDTO", Uri.parse("sms:00000000"));
        localPackageManager = this.Zc.getPackageManager();
        localObject1 = localPackageManager.queryIntentActivities((Intent)localObject3, 65536);
        if (((List)localObject1).size() == 1)
        {
          localObject1 = (ResolveInfo)((List)localObject1).get(0);
          if (localObject1 != null) {
            break label204;
          }
          return;
        }
      }
    }
    catch (Exception localException1)
    {
      for (;;)
      {
        Object localObject3;
        PackageManager localPackageManager;
        a.ab(localException1.getLocalizedMessage());
        continue;
        if (localException1.size() != 0)
        {
          localObject3 = localPackageManager.resolveActivity((Intent)localObject3, 65536);
          int j = localException1.size();
          int i = 0;
          for (;;)
          {
            if (i >= j)
            {
              localObject2 = (ResolveInfo)localException1.get(0);
              break;
            }
            if (((ResolveInfo)((List)localObject2).get(i)).activityInfo.packageName.equals(((ResolveInfo)localObject3).activityInfo.packageName))
            {
              localObject2 = (ResolveInfo)((List)localObject2).get(i);
              break;
            }
            i += 1;
          }
        }
        localObject2 = null;
      }
      label204:
      localObject2 = ((ResolveInfo)localObject2).activityInfo.applicationInfo.packageName;
    }
    try
    {
      this.aRo.ay(((String)localObject2).getBytes("UTF-16LE"));
      localObject2 = Gw().activityInfo.applicationInfo.packageName;
      try
      {
        this.aRo.ay(((String)localObject2).getBytes("UTF-16LE"));
        return;
      }
      catch (Exception localException2)
      {
        a.ab(localException2.getLocalizedMessage());
        return;
      }
    }
    catch (Exception localException3)
    {
      for (;;)
      {
        a.ab(localException3.getLocalizedMessage());
      }
    }
  }
  
  private void aA(byte[] paramArrayOfByte)
  {
    if ((paramArrayOfByte != null) && (paramArrayOfByte.length > 0)) {
      switch (paramArrayOfByte[0])
      {
      }
    }
    do
    {
      do
      {
        return;
        a.cA("rpltNotifySMSCallDeny");
        if (this.aRm != null) {
          this.aRm.Gi();
        }
      } while (this.aRn == null);
      this.aRn.Gi();
      return;
      a.cA("rpltNotifySMSCallAllow");
      if (this.aRm != null) {
        this.aRm.Gh();
      }
    } while (this.aRn == null);
    this.aRn.Gh();
  }
  
  private void aB(byte[] paramArrayOfByte)
  {
    if (this.aRo != null) {
      this.aRo.ay(paramArrayOfByte);
    }
  }
  
  private static byte[] d(int paramInt, String paramString1, String paramString2)
  {
    NotifyCallGSon localNotifyCallGSon = new NotifyCallGSon();
    localNotifyCallGSon.state = paramInt;
    localNotifyCallGSon.number = paramString2;
    if ((paramString1 == null) || (paramString1.equals(""))) {}
    for (localNotifyCallGSon.name = paramString2;; localNotifyCallGSon.name = paramString1) {
      return localNotifyCallGSon.getJSONTextToBytes();
    }
  }
  
  private static byte[] f(String paramString1, String paramString2, String paramString3)
  {
    int n = 1;
    int i1 = fk(0);
    int j;
    byte[] arrayOfByte1;
    int k;
    if ((paramString1 == null) || (paramString1.length() <= 0))
    {
      j = 1;
      arrayOfByte1 = null;
      k = 3;
    }
    for (;;)
    {
      byte[] arrayOfByte2;
      byte[] arrayOfByte3;
      int m;
      label99:
      byte[] arrayOfByte4;
      if ((paramString2 == null) || (paramString2.length() <= 0))
      {
        j |= 0x2;
        arrayOfByte2 = null;
        k += 2;
        if ((paramString3 != null) && (paramString3.length() > 0)) {
          break label284;
        }
        arrayOfByte3 = null;
        k += 2;
        m = j | 0x4;
        j = k;
        if (m != 7) {
          break label316;
        }
        k = j - 6;
        arrayOfByte4 = new byte[k + 0];
        arrayOfByte4[0] = ((byte)i1);
        if (paramString1 == null) {}
      }
      try
      {
        if (paramString1.length() <= 0)
        {
          j = n;
          if (m != 7)
          {
            System.arraycopy(g.c((short)0), 0, arrayOfByte4, 1, 2);
            j = 3;
          }
          label151:
          if ((paramString2 != null) && (paramString2.length() > 0)) {
            break label370;
          }
          k = j;
          if (m != 7) {
            System.arraycopy(g.c((short)0), 0, arrayOfByte4, j, 2);
          }
        }
        label284:
        label316:
        int i;
        for (k = j + 2;; k = j + i)
        {
          if ((paramString3 != null) && (paramString3.length() > 0)) {
            break label414;
          }
          if ((m != 7) && (m != 3)) {
            System.arraycopy(g.c((short)0), 0, arrayOfByte4, k, 2);
          }
          return arrayOfByte4;
          try
          {
            arrayOfByte1 = paramString1.getBytes("UTF-16LE");
            j = arrayOfByte1.length;
            k = j + 3;
            j = 0;
          }
          catch (UnsupportedEncodingException paramString1)
          {
            return null;
          }
          try
          {
            arrayOfByte2 = paramString2.getBytes("UTF-16LE");
            k = k + 2 + arrayOfByte2.length;
          }
          catch (UnsupportedEncodingException paramString1)
          {
            return null;
          }
          try
          {
            arrayOfByte3 = paramString3.getBytes("UTF-16LE");
            k = k + 2 + arrayOfByte3.length;
            m = j;
            j = k;
          }
          catch (UnsupportedEncodingException paramString1)
          {
            return null;
          }
          k = j;
          if (m != 3) {
            break label99;
          }
          k = j - 4;
          break label99;
          i = (short)arrayOfByte1.length;
          System.arraycopy(g.c(i), 0, arrayOfByte4, 1, 2);
          System.arraycopy(arrayOfByte1, 0, arrayOfByte4, 3, i);
          j = i + 3;
          break label151;
          label370:
          i = (short)arrayOfByte2.length;
          System.arraycopy(g.c(i), 0, arrayOfByte4, j, 2);
          j += 2;
          System.arraycopy(arrayOfByte2, 0, arrayOfByte4, j, i);
        }
        label414:
        short s = (short)arrayOfByte3.length;
        System.arraycopy(g.c(s), 0, arrayOfByte4, k, 2);
        System.arraycopy(arrayOfByte3, 0, arrayOfByte4, k + 2, s);
        return arrayOfByte4;
      }
      catch (Exception paramString1)
      {
        a.d(paramString1);
      }
    }
    return null;
  }
  
  private static int fk(int paramInt)
  {
    switch (paramInt)
    {
    case 0: 
    default: 
      return 0;
    case 1: 
      return 1;
    }
    return 2;
  }
  
  private boolean h(int paramInt, byte[] paramArrayOfByte)
  {
    boolean bool = false;
    if (paramArrayOfByte == null) {}
    for (int i = 0;; i = paramArrayOfByte.length) {
      try
      {
        if (this.aRq != null) {
          bool = this.aRq.a(235, paramInt, paramArrayOfByte, i);
        }
        return bool;
      }
      finally {}
    }
  }
  
  public final boolean CU()
  {
    this.aRm = new sh(this.Zc);
    this.aRn = new sp(this.Zc);
    this.aRo = new sf(this.Zc);
    this.aRm.a(this);
    this.aRn.a(this);
    this.aRo.a(this);
    this.aRp.b(this);
    boolean bool = this.aRm.CU() & true & this.aRn.CU() & this.aRo.CU();
    Object localObject1 = this.Zc.getPackageName();
    try
    {
      this.aRo.ay(((String)localObject1).getBytes("UTF-16LE"));
      if (this.Zc != null)
      {
        localObject2 = new Intent("android.intent.action.SENDTO", Uri.parse("sms:00000000"));
        localPackageManager = this.Zc.getPackageManager();
        localObject1 = localPackageManager.queryIntentActivities((Intent)localObject2, 65536);
        if (((List)localObject1).size() == 1)
        {
          localObject1 = (ResolveInfo)((List)localObject1).get(0);
          if (localObject1 != null) {
            localObject1 = ((ResolveInfo)localObject1).activityInfo.applicationInfo.packageName;
          }
        }
      }
    }
    catch (Exception localException1)
    {
      try
      {
        Object localObject2;
        PackageManager localPackageManager;
        for (;;)
        {
          this.aRo.ay(((String)localObject1).getBytes("UTF-16LE"));
          localObject1 = Gw().activityInfo.applicationInfo.packageName;
          try
          {
            this.aRo.ay(((String)localObject1).getBytes("UTF-16LE"));
            return bool;
          }
          catch (Exception localException3)
          {
            int j;
            int i;
            ResolveInfo localResolveInfo;
            a.ab(localException3.getLocalizedMessage());
          }
          localException1 = localException1;
          a.ab(localException1.getLocalizedMessage());
        }
        if (localException1.size() != 0)
        {
          localObject2 = localPackageManager.resolveActivity((Intent)localObject2, 65536);
          j = localException1.size();
          i = 0;
          for (;;)
          {
            if (i >= j)
            {
              localResolveInfo = (ResolveInfo)localException1.get(0);
              break;
            }
            if (((ResolveInfo)localResolveInfo.get(i)).activityInfo.packageName.equals(((ResolveInfo)localObject2).activityInfo.packageName))
            {
              localResolveInfo = (ResolveInfo)localResolveInfo.get(i);
              break;
            }
            i += 1;
          }
        }
        localResolveInfo = null;
      }
      catch (Exception localException2)
      {
        for (;;)
        {
          a.ab(localException2.getLocalizedMessage());
        }
      }
    }
    return bool;
  }
  
  public final void Df()
  {
    if (this.aRm != null)
    {
      this.aRm.a(null);
      this.aRm.Df();
    }
    this.aRm = null;
    if (this.aRn != null)
    {
      this.aRn.a(null);
      this.aRn.Df();
      this.aRn = null;
    }
    if (this.aRo != null)
    {
      this.aRo.a(null);
      this.aRo.Df();
    }
    this.aRo = null;
    if (this.aRr != null) {
      this.aRr.clear();
    }
    this.aRp.c(this);
    this.aRp = null;
    this.aRq = null;
  }
  
  public final void a(int paramInt, String paramString, se paramSe)
  {
    NotifyCallGSon localNotifyCallGSon;
    if (kb.getUXStyle() == 0)
    {
      paramSe = paramSe.em(paramString);
      a.cA("STATE:" + paramInt + ", PhoneNumber:" + paramString + ", Name:" + paramSe);
      paramInt = fk(paramInt);
      localNotifyCallGSon = new NotifyCallGSon();
      localNotifyCallGSon.state = paramInt;
      localNotifyCallGSon.number = paramString;
      if ((paramSe != null) && (!paramSe.equals(""))) {
        break label108;
      }
      localNotifyCallGSon.name = paramString;
      paramString = localNotifyCallGSon.getJSONTextToBytes();
      if (paramString != null) {
        break label117;
      }
    }
    label108:
    label117:
    while (h(1, paramString))
    {
      return;
      localNotifyCallGSon.name = paramSe;
      break;
    }
    a.ab("onCallMessage() failed to delivery about incomingcall");
  }
  
  public final void a(String paramString1, String paramString2, se paramSe)
  {
    paramSe = paramSe.em(paramString1);
    a.S("PhoneNumber:" + paramString1 + ", message:" + paramString2 + ", Name:" + paramSe);
    paramString1 = f(paramString1, paramSe, paramString2);
    if (paramString1 == null) {}
    while (h(0, paramString1)) {
      return;
    }
    a.ab("onSmsMessage() failed to delivery about a received message");
  }
  
  public final void aC(byte[] paramArrayOfByte)
  {
    if (!h(6, paramArrayOfByte)) {
      a.ab("onAccessibilityMessage() failed to delivery about a received message");
    }
  }
  
  public final void c(int paramInt1, int paramInt2, byte[] paramArrayOfByte)
  {
    if (paramInt1 != 235) {}
    do
    {
      do
      {
        do
        {
          do
          {
            return;
            switch (paramInt2)
            {
            case 0: 
            case 1: 
            case 2: 
            case 3: 
            default: 
              return;
            }
          } while (this.aRo == null);
          this.aRo.ay(paramArrayOfByte);
          return;
        } while ((paramArrayOfByte == null) || (paramArrayOfByte.length <= 0));
        switch (paramArrayOfByte[0])
        {
        default: 
          return;
        case 0: 
          a.cA("rpltNotifySMSCallDeny");
          if (this.aRm != null) {
            this.aRm.Gi();
          }
          break;
        }
      } while (this.aRn == null);
      this.aRn.Gi();
      return;
      a.cA("rpltNotifySMSCallAllow");
      if (this.aRm != null) {
        this.aRm.Gh();
      }
    } while (this.aRn == null);
    this.aRn.Gh();
  }
  
  public final void c(ji paramJi)
  {
    this.aRq = paramJi;
  }
  
  public final void g(int paramInt, byte[] paramArrayOfByte)
  {
    h(paramInt, paramArrayOfByte);
  }
}
