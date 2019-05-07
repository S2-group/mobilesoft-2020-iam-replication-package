import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import com.vaultmicro.camerafi.vl;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class bsf
{
  public static String d;
  public String a;
  public String b;
  public String c;
  
  public bsf() {}
  
  public bsf(String paramString1, String paramString2, String paramString3)
  {
    this.a = paramString1;
    this.b = paramString2;
    this.c = paramString3;
  }
  
  public static String a(Context paramContext)
  {
    if (d != null) {
      return d;
    }
    List localList = Arrays.asList(g.a);
    paramContext = paramContext.getPackageManager().getInstalledApplications(128).iterator();
    ApplicationInfo localApplicationInfo;
    do
    {
      if (!paramContext.hasNext()) {
        return null;
      }
      localApplicationInfo = (ApplicationInfo)paramContext.next();
    } while ((!localApplicationInfo.enabled) || (!localList.contains(localApplicationInfo.packageName)));
    d = localApplicationInfo.packageName;
    return localApplicationInfo.packageName;
  }
  
  private static void a(Activity paramActivity, String paramString)
  {
    paramString = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
    paramString.setPackage(a(paramActivity));
    paramString.addFlags(268435456);
    paramActivity.startActivity(paramString);
  }
  
  public static void a(Activity paramActivity, String paramString, boolean paramBoolean)
  {
    paramString = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
    paramString.setPackage(a(paramActivity));
    paramString.addFlags(268435456);
    paramActivity.startActivity(paramString);
  }
  
  private void a(String paramString)
  {
    this.a = paramString;
  }
  
  private boolean a(int paramInt1, int paramInt2, String paramString)
  {
    return a(String.format("%04x", new Object[] { Integer.valueOf(paramInt1) }), String.format("%04x", new Object[] { Integer.valueOf(paramInt2) }), paramString);
  }
  
  private void b(String paramString)
  {
    this.b = paramString;
  }
  
  private static boolean b(Context paramContext)
  {
    return a(paramContext) != null;
  }
  
  private void c(String paramString)
  {
    this.c = paramString;
  }
  
  public final String a()
  {
    return this.a;
  }
  
  public final boolean a(String paramString1, String paramString2, String paramString3)
  {
    int i;
    if ((paramString1 == null) || (paramString1.isEmpty()) || (paramString1.equalsIgnoreCase("0"))) {
      i = 1;
    }
    for (;;)
    {
      int j;
      if ((paramString2 == null) || (paramString2.isEmpty()) || (paramString2.equalsIgnoreCase("0"))) {
        j = 1;
      }
      for (;;)
      {
        label46:
        int k;
        if ((paramString3 == null) || (paramString3.isEmpty()) || (paramString3.equalsIgnoreCase("unknown"))) {
          k = 1;
        }
        for (;;)
        {
          label69:
          vl.l(vl.getMethodName(), "device-filter matches", new Object[0]);
          String str2 = vl.getMethodName();
          String str1;
          if (i != 0)
          {
            str1 = "o";
            label95:
            vl.l(str2, "      +-- vid         : (%s) %s <--> %s", new Object[] { str1, paramString1, this.a });
            str1 = vl.getMethodName();
            if (j == 0) {
              break label294;
            }
            paramString1 = "o";
            label135:
            vl.l(str1, "      +-- pid         : (%s) %s <--> %s", new Object[] { paramString1, paramString2, this.b });
            paramString2 = vl.getMethodName();
            if (k == 0) {
              break label300;
            }
          }
          label294:
          label300:
          for (paramString1 = "o";; paramString1 = "x")
          {
            vl.l(paramString2, "      +-- productName : (%s) %s <--> %s", new Object[] { paramString1, paramString3, this.c });
            if ((i == 0) || (j == 0) || (k == 0)) {
              break label306;
            }
            return true;
            if (!Pattern.compile(this.a, 2).matcher(paramString1).matches()) {
              break label320;
            }
            i = 1;
            break;
            if (!Pattern.compile(this.b, 2).matcher(paramString2).matches()) {
              break label314;
            }
            j = 1;
            break label46;
            if (!Pattern.compile(this.c, 2).matcher(paramString3).matches()) {
              break label308;
            }
            k = 1;
            break label69;
            str1 = "x";
            break label95;
            paramString1 = "x";
            break label135;
          }
          label306:
          return false;
          label308:
          k = 0;
        }
        label314:
        j = 0;
      }
      label320:
      i = 0;
    }
  }
  
  public final String b()
  {
    return this.b;
  }
  
  public final String c()
  {
    return this.c;
  }
}
