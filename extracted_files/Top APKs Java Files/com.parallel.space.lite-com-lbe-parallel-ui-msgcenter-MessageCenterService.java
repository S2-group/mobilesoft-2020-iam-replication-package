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
import com.lbe.parallel.DAApp;
import com.lbe.parallel.f.a;
import com.lbe.parallel.fq;
import com.lbe.parallel.ir;
import com.lbe.parallel.is;
import com.lbe.parallel.it;
import com.lbe.parallel.utility.ab;
import com.lbe.parallel.utility.af;
import com.lbe.parallel.utility.c.1;
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
  
  private static Intent a(ir paramIr)
  {
    String str = null;
    switch ((int)paramIr.c)
    {
    }
    for (;;)
    {
      paramIr = null;
      for (;;)
      {
        if (paramIr != null) {
          str = paramIr.toUri(1);
        }
        return paramIr;
        Intent localIntent = new Intent("android.intent.action.VIEW");
        localIntent.addFlags(268435456);
        localIntent.setData(Uri.parse(paramIr.g));
        paramIr = localIntent;
        continue;
        try
        {
          paramIr = Intent.parseUri(paramIr.h, 1);
        }
        catch (URISyntaxException paramIr)
        {
          paramIr.printStackTrace();
        }
      }
    }
  }
  
  public static void a(Context paramContext)
  {
    int i = ab.a().b("homepage_launch_count");
    long l = ab.a().e("latest_check_message_time");
    l = Math.max(System.currentTimeMillis() - l, 0L);
    if ((i > 2) && (l > 28800000L) && (af.e(paramContext.getApplicationContext()))) {
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
    c.1.c(this.b);
  }
  
  protected void onHandleIntent(final Intent paramIntent)
  {
    long l = ab.a().e("latest_check_message_time");
    if (Math.max(System.currentTimeMillis() - l, 0L) > 28800000L)
    {
      if (!af.e(getApplicationContext())) {
        break label283;
      }
      l = ab.a().e("latest_received_message_id");
      Object localObject1 = getApplicationContext();
      paramIntent = new is();
      paramIntent.b = f.a.o();
      paramIntent.c = f.a.p();
      paramIntent.e = l;
      Object localObject2 = new fq((Context)localObject1).getInstalledPackages(0);
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
        break label283;
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
            ir[] arrayOfIr = paramIntent;
            int j = arrayOfIr.length;
            int i = 0;
            long l = -1L;
            while (i < j)
            {
              ir localIr = arrayOfIr[i];
              MessageCenterService.a(MessageCenterService.this, localIr.f, new MessageCenterService.a(this, localIr, localAtomicInteger));
              l = Math.max(localIr.b, l);
              i += 1;
            }
            if (l != -1L) {
              ab.a().a("latest_received_message_id", l);
            }
          }
        });
        this.d.block();
      }
    }
    label283:
    for (int i = 1;; i = 0)
    {
      if (i != 0) {
        ab.a().a("latest_check_message_time", System.currentTimeMillis());
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
    a(MessageCenterService.1 param1, ir paramIr, AtomicInteger paramAtomicInteger) {}
    
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
