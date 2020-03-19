package com.ijinshan.safe;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemProperties;
import android.text.TextUtils;
import com.ijinshan.a.f;
import com.ijinshan.a.g;
import com.ijinshan.browser.utils.y;
import com.ijinshan.safe.ui.FlashDangerDialog;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class a
{
  private static a a = null;
  private static Handler b;
  private static final Signature[] d = { new Signature("308204c5308203ada003020102020900d7cb412f75f4887e300d06092a864886f70d010105050030819d310b3009060355040613025553311330110603550408130a43616c69666f726e69613111300f0603550407130853616e204a6f736531233021060355040a131a41646f62652053797374656d7320496e636f72706f7261746564311c301a060355040b1313496e666f726d6174696f6e2053797374656d73312330210603550403131a41646f62652053797374656d7320496e636f72706f7261746564301e170d3039313030313030323331345a170d3337303231363030323331345a30819d310b3009060355040613025553311330110603550408130a43616c69666f726e69613111300f0603550407130853616e204a6f736531233021060355040a131a41646f62652053797374656d7320496e636f72706f7261746564311c301a060355040b1313496e666f726d6174696f6e2053797374656d73312330210603550403131a41646f62652053797374656d7320496e636f72706f726174656430820120300d06092a864886f70d01010105000382010d0030820108028201010099724f3e05bbd78843794f357776e04b340e13cb1c9ccb3044865180d7d8fec8166c5bbd876da8b80aa71eb6ba3d4d3455c9a8de162d24a25c4c1cd04c9523affd06a279fc8f0d018f242486bdbb2dbfbf6fcb21ed567879091928b876f7ccebc7bccef157366ebe74e33ae1d7e9373091adab8327482154afc0693a549522f8c796dd84d16e24bb221f5dbb809ca56dd2b6e799c5fa06b6d9c5c09ada54ea4c5db1523a9794ed22a3889e5e05b29f8ee0a8d61efe07ae28f65dece2ff7edc5b1416d7c7aad7f0d35e8f4a4b964dbf50ae9aa6d620157770d974131b3e7e3abd6d163d65758e2f0822db9c88598b9db6263d963d13942c91fc5efe34fc1e06e3020103a382010630820102301d0603551d0e041604145af418e419a639e1657db960996364a37ef20d403081d20603551d230481ca3081c780145af418e419a639e1657db960996364a37ef20d40a181a3a481a030819d310b3009060355040613025553311330110603550408130a43616c69666f726e69613111300f0603550407130853616e204a6f736531233021060355040a131a41646f62652053797374656d7320496e636f72706f7261746564311c301a060355040b1313496e666f726d6174696f6e2053797374656d73312330210603550403131a41646f62652053797374656d7320496e636f72706f7261746564820900d7cb412f75f4887e300c0603551d13040530030101ff300d06092a864886f70d0101050500038201010076c2a11fe303359689c2ebc7b2c398eff8c3f9ad545cdbac75df63bf7b5395b6988d1842d6aa1556d595b5692e08224d667a4c9c438f05e74906c53dd8016dde7004068866f01846365efd146e9bfaa48c9ecf657f87b97c757da11f225c4a24177bf2d7188e6cce2a70a1e8a841a14471eb51457398b8a0addd8b6c8c1538ca8f1e40b4d8b960009ea22c188d28924813d2c0b4a4d334b7cf05507e1fcf0a06fe946c7ffc435e173af6fc3e3400643710acc806f830a14788291d46f2feed9fb5c70423ca747ed1572d752894ac1f19f93989766308579393fabb43649aa8806a313b1ab9a50922a44c2467b9062037f2da0d484d9ffd8fe628eeea629ba637") };
  private final Context c;
  
  private a(Context paramContext)
  {
    this.c = paramContext;
    b = new a(Looper.getMainLooper());
  }
  
  public static a a(Context paramContext)
  {
    try
    {
      if (a != null) {
        break label43;
      }
      if (paramContext == null) {
        throw new IllegalStateException("First call to PluginManager need a valid context.");
      }
    }
    finally {}
    a = new a(paramContext.getApplicationContext());
    label43:
    paramContext = a;
    return paramContext;
  }
  
  public static boolean a(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return false;
    }
    return paramString.equalsIgnoreCase("Android.Exploit.FakeID.a");
  }
  
  private static boolean b(PackageInfo paramPackageInfo)
  {
    boolean bool = true;
    String[] arrayOfString = paramPackageInfo.requestedPermissions;
    if (arrayOfString == null) {}
    label22:
    label71:
    label84:
    label147:
    label153:
    for (;;)
    {
      return false;
      int j = arrayOfString.length;
      int i = 0;
      if (i < j) {
        if (!"android.webkit.permission.PLUGIN".equals(arrayOfString[i])) {}
      }
      for (i = 1;; i = 0)
      {
        if (i == 0) {
          break label153;
        }
        paramPackageInfo = paramPackageInfo.signatures;
        if (paramPackageInfo == null) {
          break;
        }
        int k;
        if (SystemProperties.getBoolean("ro.secure", false))
        {
          int m = paramPackageInfo.length;
          j = 0;
          i = 0;
          if (j < m)
          {
            arrayOfString = paramPackageInfo[j];
            k = 0;
            if (k >= d.length) {
              break label147;
            }
            if (d[k].equals(arrayOfString)) {
              i = 1;
            }
          }
        }
        for (;;)
        {
          j += 1;
          break label71;
          i += 1;
          break label22;
          k += 1;
          break label84;
          if (i == 0) {
            break;
          }
          if (paramPackageInfo.length > 1) {}
          for (;;)
          {
            return bool;
            bool = false;
          }
        }
      }
    }
  }
  
  public void a()
  {
    new Thread(new Runnable()
    {
      private void a()
      {
        Object localObject1 = a.a(a.this).getPackageManager();
        Bundle localBundle = new Bundle();
        Object localObject2 = ((PackageManager)localObject1).getInstalledPackages(4160);
        localObject1 = new ArrayList();
        ArrayList localArrayList = new ArrayList();
        localObject2 = ((List)localObject2).iterator();
        while (((Iterator)localObject2).hasNext())
        {
          PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject2).next();
          if ((localPackageInfo != null) && (a.a(localPackageInfo)))
          {
            String str = localPackageInfo.packageName;
            if (!TextUtils.isEmpty(str)) {
              localArrayList.add(str);
            }
            y.b("PluginManager", "Malware Package Name: " + localPackageInfo.packageName);
            y.b("PluginManager", "Malware APK Path: " + localPackageInfo.applicationInfo.sourceDir);
            str = f.a(localPackageInfo.applicationInfo.sourceDir);
            com.ijinshan.a.a localA = f.a(localPackageInfo.applicationInfo.packageName, str, a.a(a.this));
            if (localA != null)
            {
              y.b("PluginManager", "Malware MD5: " + str);
              y.b("PluginManager", "Malware IsVirus: " + localA.a());
              y.b("PluginManager", "Malware Name: " + localA.a.e);
              if (a.a(localA.a.e)) {
                ((ArrayList)localObject1).add(localPackageInfo);
              }
            }
          }
        }
        if (!((ArrayList)localObject1).isEmpty())
        {
          localBundle.putSerializable("malwareList", (Serializable)localObject1);
          localObject1 = a.b().obtainMessage(1);
          ((Message)localObject1).setData(localBundle);
          ((Message)localObject1).sendToTarget();
        }
      }
      
      public void run()
      {
        try
        {
          a();
          return;
        }
        catch (Exception localException)
        {
          y.b("PluginManager", "detectMaliciousPlugin() exception", localException);
          return;
        }
        catch (OutOfMemoryError localOutOfMemoryError)
        {
          y.b("PluginManager", "detectMaliciousPlugin() OutOfMemoryError", localOutOfMemoryError);
        }
      }
    }, "detectMaliciousPlugin").run();
  }
  
  private class a
    extends Handler
  {
    public a(Looper paramLooper)
    {
      super();
    }
    
    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      default: 
        return;
      }
      paramMessage = paramMessage.getData();
      Intent localIntent = new Intent(a.a(a.this), FlashDangerDialog.class);
      localIntent.addFlags(268435456);
      localIntent.putExtra("bundle", paramMessage);
      a.a(a.this).startActivity(localIntent);
    }
  }
}
