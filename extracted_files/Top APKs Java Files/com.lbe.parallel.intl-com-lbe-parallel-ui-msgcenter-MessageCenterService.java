package com.lbe.parallel.ui.msgcenter;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.ConditionVariable;
import android.os.Handler;
import android.os.Looper;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader.ImageContainer;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.lbe.doubleagent.utility.PackageManagerWrapper;
import com.lbe.parallel.DAApp;
import com.lbe.parallel.f.a;
import com.lbe.parallel.if;
import com.lbe.parallel.ig;
import com.lbe.parallel.ih;
import com.lbe.parallel.utility.a;
import com.lbe.parallel.utility.ad;
import com.lbe.parallel.utility.z;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MessageCenterService
  extends IntentService
{
  private AtomicInteger a = new AtomicInteger(10000);
  private List<ImageLoader.ImageContainer> b = new ArrayList();
  private Handler c = new Handler(Looper.getMainLooper());
  private ConditionVariable d;
  
  public MessageCenterService()
  {
    super("MessageCenter");
  }
  
  private static Intent a(if paramIf)
  {
    String str = null;
    switch ((int)paramIf.c)
    {
    }
    for (;;)
    {
      paramIf = null;
      for (;;)
      {
        if (paramIf != null) {
          str = paramIf.toUri(1);
        }
        return paramIf;
        Intent localIntent = new Intent("android.intent.action.VIEW");
        localIntent.addFlags(268435456);
        localIntent.setData(Uri.parse(paramIf.g));
        paramIf = localIntent;
        continue;
        try
        {
          paramIf = Intent.parseUri(paramIf.h, 1);
        }
        catch (URISyntaxException paramIf)
        {
          paramIf.printStackTrace();
        }
      }
    }
  }
  
  public static void a(Context paramContext)
  {
    int i = z.a().b("homepage_launch_count");
    long l = z.a().e("latest_check_message_time");
    l = Math.max(System.currentTimeMillis() - l, 0L);
    if ((i > 2) && (l > 28800000L) && (ad.d(paramContext.getApplicationContext()))) {
      paramContext.startService(new Intent(paramContext, MessageCenterService.class));
    }
  }
  
  public void onCreate()
  {
    super.onCreate();
  }
  
  public void onDestroy()
  {
    super.onDestroy();
    a.c(this.b);
  }
  
  protected void onHandleIntent(final Intent paramIntent)
  {
    long l = z.a().e("latest_check_message_time");
    if (Math.max(System.currentTimeMillis() - l, 0L) > 28800000L)
    {
      if (!ad.d(getApplicationContext())) {
        break label287;
      }
      l = z.a().e("latest_received_message_id");
      Object localObject1 = getApplicationContext();
      paramIntent = new ig();
      paramIntent.b = f.a.c((Context)localObject1);
      paramIntent.c = f.a.d((Context)localObject1);
      paramIntent.e = l;
      Object localObject2 = new PackageManagerWrapper((Context)localObject1).getInstalledPackages(0);
      localObject1 = new ArrayList(((List)localObject2).size());
      localObject2 = ((List)localObject2).iterator();
      while (((Iterator)localObject2).hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject2).next();
        if (!f.a.a(localPackageInfo.applicationInfo)) {
          ((List)localObject1).add(localPackageInfo.packageName);
        }
      }
      paramIntent.d = ((String[])((List)localObject1).toArray(new String[((List)localObject1).size()]));
      paramIntent = f.a.a(getApplicationContext(), paramIntent);
      if ((paramIntent == null) || (paramIntent.b != 1)) {
        break label287;
      }
      paramIntent = paramIntent.c;
      i = paramIntent.length;
      if ((paramIntent != null) && (paramIntent.length > 0))
      {
        this.d = new ConditionVariable();
        this.c.post(new Runnable()
        {
          public final void run()
          {
            AtomicInteger localAtomicInteger = new AtomicInteger(0);
            if[] arrayOfIf = paramIntent;
            int j = arrayOfIf.length;
            int i = 0;
            long l = -1L;
            while (i < j)
            {
              if localIf = arrayOfIf[i];
              MessageCenterService.a(MessageCenterService.this, localIf.f, new MessageCenterService.a(this, localIf, localAtomicInteger));
              l = Math.max(localIf.b, l);
              i += 1;
            }
            if (l != -1L) {
              z.a().a("latest_received_message_id", l);
            }
          }
        });
        this.d.block();
      }
    }
    label287:
    for (int i = 1;; i = 0)
    {
      if (i != 0) {
        z.a().a("latest_check_message_time", System.currentTimeMillis());
      }
      return;
    }
  }
  
  public void onStart(Intent paramIntent, int paramInt)
  {
    super.onStart(paramIntent, paramInt);
  }
  
  public static final class a
  {
    a(MessageCenterService.1 param1, if paramIf, AtomicInteger paramAtomicInteger) {}
    
    public final void a(Bitmap paramBitmap)
    {
      MessageCenterService.a(this.c.b, this.a, paramBitmap);
      if (this.b.incrementAndGet() >= this.c.a.length)
      {
        MessageCenterService.a(this.c.b).open();
        MessageCenterService.b(this.c.b);
      }
    }
  }
}
