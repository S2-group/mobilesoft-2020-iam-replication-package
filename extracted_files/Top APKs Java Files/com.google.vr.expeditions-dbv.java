import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

class dbv
  extends Handler
{
  static final String a = dbv.class.getSimpleName();
  long b;
  private final Context c;
  private File d;
  
  public dbv(Context paramContext)
  {
    super(localHandlerThread.getLooper());
    this.c = paramContext;
  }
  
  private final dqw a(String paramString)
  {
    Object localObject1;
    Object localObject2;
    if (this.d == null)
    {
      localObject1 = this.c.getPackageManager().getInstalledApplications(128).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (ApplicationInfo)((Iterator)localObject1).next();
        if ((((ApplicationInfo)localObject2).className != null) && (((ApplicationInfo)localObject2).className.contains("vr.expeditions")))
        {
          String str1 = String.valueOf(((ApplicationInfo)localObject2).className);
          String str2 = String.valueOf(((ApplicationInfo)localObject2).publicSourceDir);
          String str3 = String.valueOf(((ApplicationInfo)localObject2).permission);
          new StringBuilder(String.valueOf(str1).length() + 66 + String.valueOf(str2).length() + String.valueOf(str3).length()).append("Found requested source of the application: ").append(str1).append("in: ").append(str2).append(" with permissions: ").append(str3);
          this.d = new File(((ApplicationInfo)localObject2).sourceDir);
          this.b = this.d.length();
        }
      }
    }
    paramString = cts.a(this.c, paramString);
    if (this.d != null)
    {
      localObject1 = new dsk();
      localObject2 = new dsu("application", this.d);
      if (((dsk)localObject1).h == null) {
        ((dsk)localObject1).h = new ArrayList();
      }
      ((dsk)localObject1).h.add(localObject2);
      paramString.e = ((dsg)localObject1);
    }
    return paramString;
  }
  
  public void handleMessage(Message paramMessage)
  {
    int i = paramMessage.what;
    new StringBuilder(37).append("running upgrade of type = ").append(i);
    paramMessage = (cyn)paramMessage.obj;
    String str1 = String.format("http://%s:%d%s", new Object[] { paramMessage.a, Integer.valueOf(paramMessage.g), "/upgrade" });
    dqw localDqw = a(str1);
    cat localCat = cat.a();
    paramMessage.h = 1;
    dwr localDwr = dwr.a();
    String str2 = paramMessage.a();
    localCat.a(TimeUnit.MILLISECONDS);
    long l = this.b;
    localDwr.b(new cym(str2, "", 1));
    try
    {
      dpy.a().a(localDqw, new dbw(this, localCat, paramMessage, str1)).get();
      return;
    }
    catch (ExecutionException paramMessage)
    {
      Log.e(a, "Cannot execute upgrade request.", paramMessage);
      return;
    }
    catch (InterruptedException paramMessage)
    {
      Log.e(a, "Upgrade request interrupted.", paramMessage);
    }
  }
}
