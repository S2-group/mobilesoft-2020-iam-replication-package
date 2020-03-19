import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import com.rsupport.common.log.a;
import java.util.Iterator;
import java.util.List;

public final class ky
  implements kr
{
  private Context Zc = null;
  private kz aHi = null;
  private ks aHj = null;
  private boolean aHk = false;
  private String aHl = null;
  
  public ky(Context paramContext)
  {
    this.Zc = paramContext;
    this.aHi = new kz(this);
  }
  
  private void CG()
  {
    IntentFilter localIntentFilter = new IntentFilter("android.intent.action.PACKAGE_ADDED");
    localIntentFilter.addAction("android.intent.action.PACKAGE_CHANGED");
    localIntentFilter.addDataScheme("package");
    if (this.Zc != null)
    {
      this.aHk = true;
      this.Zc.registerReceiver(this.aHi, localIntentFilter);
    }
  }
  
  private void CH()
  {
    if ((this.Zc != null) && (this.aHk))
    {
      this.aHk = false;
      this.Zc.unregisterReceiver(this.aHi);
    }
  }
  
  private boolean dJ(String paramString)
  {
    Object localObject;
    if (paramString != null)
    {
      localObject = this.Zc.getPackageManager().getInstalledPackages(4);
      if (localObject != null) {
        localObject = ((List)localObject).iterator();
      }
    }
    do
    {
      if (!((Iterator)localObject).hasNext()) {
        return false;
      }
    } while (!((PackageInfo)((Iterator)localObject).next()).packageName.equals(paramString));
    return true;
  }
  
  private void dK(String paramString)
  {
    paramString = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + paramString));
    paramString.setFlags(1073741824);
    paramString.addFlags(268435456);
    this.Zc.startActivity(paramString);
  }
  
  public final String CC()
  {
    this.aHl = new kt(this.Zc).CE();
    if ((this.aHl != null) && (!this.aHl.equals(""))) {
      return this.aHl;
    }
    return null;
  }
  
  public final String CD()
  {
    this.aHl = new kt(this.Zc).CE();
    String str = this.aHl;
    Object localObject;
    if (str != null)
    {
      localObject = this.Zc.getPackageManager().getInstalledPackages(4);
      if (localObject != null)
      {
        localObject = ((List)localObject).iterator();
        if (((Iterator)localObject).hasNext()) {
          break label70;
        }
      }
    }
    for (int i = 0;; i = 1)
    {
      if (i == 0) {
        break label94;
      }
      return this.aHl;
      label70:
      if (!((PackageInfo)((Iterator)localObject).next()).packageName.equals(str)) {
        break;
      }
    }
    label94:
    return null;
  }
  
  public final String Cs()
  {
    this.aHl = new kt(this.Zc).CE();
    return this.aHl;
  }
  
  public final void a(ks paramKs)
  {
    this.aHj = paramKs;
    this.aHl = new kt(this.Zc).CE();
    a.cA("installMarket targetRspermPackage : " + this.aHl);
    if ((this.aHl != null) && (!this.aHl.equals("")))
    {
      Object localObject = new IntentFilter("android.intent.action.PACKAGE_ADDED");
      ((IntentFilter)localObject).addAction("android.intent.action.PACKAGE_CHANGED");
      ((IntentFilter)localObject).addDataScheme("package");
      if (this.Zc != null)
      {
        this.aHk = true;
        this.Zc.registerReceiver(this.aHi, (IntentFilter)localObject);
      }
      localObject = this.aHl;
      localObject = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + (String)localObject));
      ((Intent)localObject).setFlags(1073741824);
      ((Intent)localObject).addFlags(268435456);
      this.Zc.startActivity((Intent)localObject);
      if (paramKs != null) {
        paramKs.el(1);
      }
    }
    while (paramKs == null) {
      return;
    }
    paramKs.el(404);
  }
  
  public final void close()
  {
    if ((this.Zc != null) && (this.aHk))
    {
      this.aHk = false;
      this.Zc.unregisterReceiver(this.aHi);
    }
    this.Zc = null;
  }
}
