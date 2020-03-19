package com.wondershare.drfone.ui.activity;

import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.wondershare.drfone.utils.l;
import com.wondershare.drfone.utils.n;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DeepRecoveryScanInfoActivity
  extends BaseToolbarActivity
{
  private String A;
  a b;
  l c;
  List<b> f = new ArrayList();
  c g;
  LinkedBlockingQueue<Runnable> h = new LinkedBlockingQueue();
  ExecutorService i = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, this.h);
  private Handler j;
  private ListView k;
  private View l;
  private Button m;
  private String n;
  private ImageView o;
  private TextView p;
  private ImageView q;
  private String r;
  private String s;
  private String t;
  private String u;
  private String v;
  private String w;
  private String x;
  private String y;
  private String z;
  
  public DeepRecoveryScanInfoActivity() {}
  
  private void b(String paramString)
  {
    if (TextUtils.isEmpty(paramString))
    {
      setTitle(getString(2131034185));
      return;
    }
    int i1 = -1;
    switch (paramString.hashCode())
    {
    }
    for (;;)
    {
      switch (i1)
      {
      default: 
        setTitle(getString(2131034185));
        return;
        if (paramString.equals("recovery"))
        {
          i1 = 0;
          continue;
          if (paramString.equals("clone"))
          {
            i1 = 1;
            continue;
            if (paramString.equals("backup"))
            {
              i1 = 2;
              continue;
              if (paramString.equals("transfer"))
              {
                i1 = 3;
                continue;
                if (paramString.equals("mirror"))
                {
                  i1 = 4;
                  continue;
                  if (paramString.equals("root"))
                  {
                    i1 = 5;
                    continue;
                    if (paramString.equals("eraser")) {
                      i1 = 6;
                    }
                  }
                }
              }
            }
          }
        }
        break;
      }
    }
    setTitle(getString(2131034256));
    return;
    setTitle(getString(2131034432));
    this.p.setText(2131034269);
    return;
    setTitle(getString(2131034436));
    this.p.setText(2131034269);
    return;
    setTitle(getString(2131034433));
    return;
    setTitle(getString(2131034276));
    return;
    setTitle(getString(2131034278));
    return;
    setTitle("Eraser");
  }
  
  private int d()
  {
    if ((this.n != null) && (this.n.equals("recovery"))) {
      return 5;
    }
    return 0;
  }
  
  protected void a()
  {
    n.a("DeepRecovery", "DR_Persion", "DR_Count", "DR_Scanning");
    setContentView(2130903068);
    this.r = getString(2131034191);
    this.s = getString(2131034193);
    this.t = getString(2131034194);
    this.u = getString(2131034195);
    this.v = getString(2131034188);
    this.w = getString(2131034192);
    this.x = getString(2131034189);
    this.y = getString(2131034190);
    this.z = getString(2131034187);
    this.A = getString(2131034196);
  }
  
  @TargetApi(11)
  protected void b()
  {
    this.k = ((ListView)findViewById(2131427467));
    this.l = View.inflate(this, 2130903113, null);
    this.o = ((ImageView)this.l.findViewById(2131427679));
    this.o.setBackgroundResource(2130837751);
    this.q = ((ImageView)this.l.findViewById(2131427678));
    this.p = ((TextView)this.l.findViewById(2131427680));
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        ObjectAnimator localObjectAnimator = ObjectAnimator.ofFloat(DeepRecoveryScanInfoActivity.a(DeepRecoveryScanInfoActivity.this), "rotation", new float[] { 0.0F, 359.0F });
        localObjectAnimator.setInterpolator(new LinearInterpolator());
        localObjectAnimator.setDuration(2000L);
        localObjectAnimator.setRepeatCount(-1);
        localObjectAnimator.start();
      }
    }, 1L);
    this.k.addHeaderView(this.l);
    this.m = ((Button)findViewById(2131427468));
    this.m.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if ((DeepRecoveryScanInfoActivity.b(DeepRecoveryScanInfoActivity.this) != null) && (DeepRecoveryScanInfoActivity.b(DeepRecoveryScanInfoActivity.this).equals("backup"))) {
          n.a("Backup", "B_Persion", "B_Count", "B_Checking");
        }
        if ((DeepRecoveryScanInfoActivity.b(DeepRecoveryScanInfoActivity.this) != null) && (DeepRecoveryScanInfoActivity.b(DeepRecoveryScanInfoActivity.this).equals("clone"))) {
          n.a("Clone", "C_Persion", "C_Count", "C_Checking");
        }
        n.a("DeepRecovery", "DR_Persion", "DR_Count", "DR_Next");
        paramAnonymousView = new Intent(DeepRecoveryScanInfoActivity.this, WebViewActivity.class);
        paramAnonymousView.putExtra("type", DeepRecoveryScanInfoActivity.b(DeepRecoveryScanInfoActivity.this));
        DeepRecoveryScanInfoActivity.this.startActivity(paramAnonymousView);
      }
    });
  }
  
  @TargetApi(11)
  protected void c()
  {
    try
    {
      this.n = getIntent().getStringExtra("type");
      b(this.n);
      if ((this.n != null) && (this.n.equals("backup"))) {
        n.a("Backup", "B_Persion", "B_Count", "B_Checking");
      }
      if ((this.n != null) && (this.n.equals("clone"))) {
        n.a("Clone", "C_Persion", "C_Count", "C_Checking");
      }
      this.g = new c(this, this.k, this.f);
      this.k.setAdapter(this.g);
      this.j = new Handler(new Handler.Callback()
      {
        public boolean handleMessage(Message paramAnonymousMessage)
        {
          switch (paramAnonymousMessage.arg1)
          {
          }
          do
          {
            do
            {
              do
              {
                do
                {
                  do
                  {
                    return false;
                    localList = DeepRecoveryScanInfoActivity.this.f;
                    localDeepRecoveryScanInfoActivity = DeepRecoveryScanInfoActivity.this;
                    str = DeepRecoveryScanInfoActivity.c(DeepRecoveryScanInfoActivity.this);
                    if (paramAnonymousMessage.arg2 == 0) {}
                    for (i = 0;; i = paramAnonymousMessage.arg2 + DeepRecoveryScanInfoActivity.d(DeepRecoveryScanInfoActivity.this))
                    {
                      localList.add(new DeepRecoveryScanInfoActivity.b(localDeepRecoveryScanInfoActivity, str, String.valueOf(i)));
                      DeepRecoveryScanInfoActivity.this.g.a(DeepRecoveryScanInfoActivity.this.f);
                      return false;
                    }
                    localList = DeepRecoveryScanInfoActivity.this.f;
                    localDeepRecoveryScanInfoActivity = DeepRecoveryScanInfoActivity.this;
                    str = DeepRecoveryScanInfoActivity.e(DeepRecoveryScanInfoActivity.this);
                    if (paramAnonymousMessage.arg2 == 0) {}
                    for (i = 0;; i = paramAnonymousMessage.arg2 + DeepRecoveryScanInfoActivity.d(DeepRecoveryScanInfoActivity.this))
                    {
                      localList.add(new DeepRecoveryScanInfoActivity.b(localDeepRecoveryScanInfoActivity, str, String.valueOf(i)));
                      DeepRecoveryScanInfoActivity.this.g.a(DeepRecoveryScanInfoActivity.this.f);
                      return false;
                    }
                    localList = DeepRecoveryScanInfoActivity.this.f;
                    localDeepRecoveryScanInfoActivity = DeepRecoveryScanInfoActivity.this;
                    str = DeepRecoveryScanInfoActivity.f(DeepRecoveryScanInfoActivity.this);
                    if (paramAnonymousMessage.arg2 == 0) {}
                    for (i = 0;; i = paramAnonymousMessage.arg2 + DeepRecoveryScanInfoActivity.d(DeepRecoveryScanInfoActivity.this))
                    {
                      localList.add(new DeepRecoveryScanInfoActivity.b(localDeepRecoveryScanInfoActivity, str, String.valueOf(i)));
                      DeepRecoveryScanInfoActivity.this.g.a(DeepRecoveryScanInfoActivity.this.f);
                      return false;
                    }
                  } while ((DeepRecoveryScanInfoActivity.b(DeepRecoveryScanInfoActivity.this) != null) && ((DeepRecoveryScanInfoActivity.b(DeepRecoveryScanInfoActivity.this).equals("clone")) || (DeepRecoveryScanInfoActivity.b(DeepRecoveryScanInfoActivity.this).equals("backup"))));
                  DeepRecoveryScanInfoActivity.this.f.add(new DeepRecoveryScanInfoActivity.b(DeepRecoveryScanInfoActivity.this, DeepRecoveryScanInfoActivity.g(DeepRecoveryScanInfoActivity.this), "***"));
                  DeepRecoveryScanInfoActivity.this.g.a(DeepRecoveryScanInfoActivity.this.f);
                  return false;
                  localList = DeepRecoveryScanInfoActivity.this.f;
                  localDeepRecoveryScanInfoActivity = DeepRecoveryScanInfoActivity.this;
                  str = DeepRecoveryScanInfoActivity.h(DeepRecoveryScanInfoActivity.this);
                  if (paramAnonymousMessage.arg2 == 0) {}
                  for (i = 0;; i = paramAnonymousMessage.arg2 + DeepRecoveryScanInfoActivity.d(DeepRecoveryScanInfoActivity.this))
                  {
                    localList.add(new DeepRecoveryScanInfoActivity.b(localDeepRecoveryScanInfoActivity, str, String.valueOf(i)));
                    DeepRecoveryScanInfoActivity.this.g.a(DeepRecoveryScanInfoActivity.this.f);
                    return false;
                  }
                  localList = DeepRecoveryScanInfoActivity.this.f;
                  localDeepRecoveryScanInfoActivity = DeepRecoveryScanInfoActivity.this;
                  str = DeepRecoveryScanInfoActivity.i(DeepRecoveryScanInfoActivity.this);
                  if (paramAnonymousMessage.arg2 == 0) {}
                  for (i = 0;; i = paramAnonymousMessage.arg2 + DeepRecoveryScanInfoActivity.d(DeepRecoveryScanInfoActivity.this))
                  {
                    localList.add(new DeepRecoveryScanInfoActivity.b(localDeepRecoveryScanInfoActivity, str, String.valueOf(i)));
                    DeepRecoveryScanInfoActivity.this.g.a(DeepRecoveryScanInfoActivity.this.f);
                    return false;
                  }
                } while ((DeepRecoveryScanInfoActivity.b(DeepRecoveryScanInfoActivity.this) != null) && ((DeepRecoveryScanInfoActivity.b(DeepRecoveryScanInfoActivity.this).equals("clone")) || (DeepRecoveryScanInfoActivity.b(DeepRecoveryScanInfoActivity.this).equals("backup"))));
                DeepRecoveryScanInfoActivity.this.f.add(new DeepRecoveryScanInfoActivity.b(DeepRecoveryScanInfoActivity.this, DeepRecoveryScanInfoActivity.j(DeepRecoveryScanInfoActivity.this), "***"));
                DeepRecoveryScanInfoActivity.this.g.a(DeepRecoveryScanInfoActivity.this.f);
                return false;
              } while ((DeepRecoveryScanInfoActivity.b(DeepRecoveryScanInfoActivity.this) == null) || ((!DeepRecoveryScanInfoActivity.b(DeepRecoveryScanInfoActivity.this).equals("clone")) && (!DeepRecoveryScanInfoActivity.b(DeepRecoveryScanInfoActivity.this).equals("backup"))));
              localList = DeepRecoveryScanInfoActivity.this.f;
              localDeepRecoveryScanInfoActivity = DeepRecoveryScanInfoActivity.this;
              str = DeepRecoveryScanInfoActivity.k(DeepRecoveryScanInfoActivity.this);
              if (paramAnonymousMessage.arg2 == 0) {}
              for (i = 0;; i = paramAnonymousMessage.arg2 + DeepRecoveryScanInfoActivity.d(DeepRecoveryScanInfoActivity.this))
              {
                localList.add(new DeepRecoveryScanInfoActivity.b(localDeepRecoveryScanInfoActivity, str, String.valueOf(i)));
                DeepRecoveryScanInfoActivity.this.g.a(DeepRecoveryScanInfoActivity.this.f);
                return false;
              }
            } while ((DeepRecoveryScanInfoActivity.b(DeepRecoveryScanInfoActivity.this) == null) || ((!DeepRecoveryScanInfoActivity.b(DeepRecoveryScanInfoActivity.this).equals("clone")) && (!DeepRecoveryScanInfoActivity.b(DeepRecoveryScanInfoActivity.this).equals("backup"))));
            localList = DeepRecoveryScanInfoActivity.this.f;
            localDeepRecoveryScanInfoActivity = DeepRecoveryScanInfoActivity.this;
            str = DeepRecoveryScanInfoActivity.l(DeepRecoveryScanInfoActivity.this);
            if (paramAnonymousMessage.arg2 == 0) {}
            for (i = 0;; i = paramAnonymousMessage.arg2 + DeepRecoveryScanInfoActivity.d(DeepRecoveryScanInfoActivity.this))
            {
              localList.add(new DeepRecoveryScanInfoActivity.b(localDeepRecoveryScanInfoActivity, str, String.valueOf(i)));
              DeepRecoveryScanInfoActivity.this.g.a(DeepRecoveryScanInfoActivity.this.f);
              return false;
            }
          } while ((DeepRecoveryScanInfoActivity.b(DeepRecoveryScanInfoActivity.this) == null) || ((!DeepRecoveryScanInfoActivity.b(DeepRecoveryScanInfoActivity.this).equals("clone")) && (!DeepRecoveryScanInfoActivity.b(DeepRecoveryScanInfoActivity.this).equals("backup"))));
          List localList = DeepRecoveryScanInfoActivity.this.f;
          DeepRecoveryScanInfoActivity localDeepRecoveryScanInfoActivity = DeepRecoveryScanInfoActivity.this;
          String str = DeepRecoveryScanInfoActivity.m(DeepRecoveryScanInfoActivity.this);
          if (paramAnonymousMessage.arg2 == 0) {}
          for (int i = 0;; i = paramAnonymousMessage.arg2 + DeepRecoveryScanInfoActivity.d(DeepRecoveryScanInfoActivity.this))
          {
            localList.add(new DeepRecoveryScanInfoActivity.b(localDeepRecoveryScanInfoActivity, str, String.valueOf(i)));
            DeepRecoveryScanInfoActivity.this.g.a(DeepRecoveryScanInfoActivity.this.f);
            return false;
          }
        }
      });
      Log.d(a, "initializeData: ");
      this.b = new a(this.j);
      if (Build.VERSION.SDK_INT < 11)
      {
        this.b.execute(new Object[0]);
        return;
      }
      this.b.executeOnExecutor(this.i, new Object[0]);
      return;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
  }
  
  protected void onDestroy()
  {
    if (this.c != null) {
      this.c.b();
    }
    if (this.h != null) {
      this.h.clear();
    }
    if (this.b != null) {
      this.b.cancel(true);
    }
    Log.d(a, "onDestroy: ");
    super.onDestroy();
  }
  
  protected void onPause()
  {
    Log.d(a, "onPause: ");
    if (this.b != null) {
      this.b.a();
    }
    if (this.c != null) {
      this.c.b();
    }
    super.onPause();
  }
  
  protected void onResume()
  {
    Log.d(a, "onResume: ");
    if (this.b != null) {
      this.b.b();
    }
    if (this.c != null) {
      this.c.a();
    }
    super.onResume();
  }
  
  private class a
    extends AsyncTask
  {
    int a = 0;
    int b = 1;
    int c = 2;
    int d = 3;
    int e = 4;
    boolean f = false;
    private final Handler h;
    private int i = 8;
    private int j = 7;
    private int k = 9;
    
    public a(Handler paramHandler)
    {
      this.h = paramHandler;
    }
    
    private int a(Uri paramUri)
    {
      paramUri = DeepRecoveryScanInfoActivity.this.getContentResolver().query(paramUri, null, null, null, null);
      if (paramUri == null)
      {
        paramUri.close();
        return 0;
      }
      try
      {
        int m = paramUri.getCount();
        paramUri.close();
        return m;
      }
      catch (Exception localException)
      {
        localException = localException;
        paramUri.close();
        return 0;
      }
      finally
      {
        localObject = finally;
        paramUri.close();
        throw localObject;
      }
    }
    
    private void c()
    {
      try
      {
        Thread.sleep(1000L);
        return;
      }
      catch (InterruptedException localInterruptedException)
      {
        localInterruptedException.printStackTrace();
      }
    }
    
    private void d()
    {
      while (true == this.f) {
        try
        {
          Thread.sleep(500L);
        }
        catch (InterruptedException localInterruptedException)
        {
          localInterruptedException.printStackTrace();
        }
      }
    }
    
    public void a()
    {
      this.f = true;
    }
    
    public void b()
    {
      this.f = false;
    }
    
    /* Error */
    @TargetApi(19)
    protected Object doInBackground(Object[] paramArrayOfObject)
    {
      // Byte code:
      //   0: aload_0
      //   1: aload_0
      //   2: getstatic 97	android/provider/ContactsContract$Contacts:CONTENT_URI	Landroid/net/Uri;
      //   5: invokespecial 99	com/wondershare/drfone/ui/activity/DeepRecoveryScanInfoActivity$a:a	(Landroid/net/Uri;)I
      //   8: putfield 30	com/wondershare/drfone/ui/activity/DeepRecoveryScanInfoActivity$a:a	I
      //   11: aload_0
      //   12: getfield 48	com/wondershare/drfone/ui/activity/DeepRecoveryScanInfoActivity$a:h	Landroid/os/Handler;
      //   15: iconst_0
      //   16: iconst_0
      //   17: aload_0
      //   18: getfield 30	com/wondershare/drfone/ui/activity/DeepRecoveryScanInfoActivity$a:a	I
      //   21: invokestatic 105	android/os/Message:obtain	(Landroid/os/Handler;III)Landroid/os/Message;
      //   24: invokevirtual 108	android/os/Message:sendToTarget	()V
      //   27: aload_0
      //   28: invokespecial 110	com/wondershare/drfone/ui/activity/DeepRecoveryScanInfoActivity$a:c	()V
      //   31: aload_0
      //   32: invokespecial 112	com/wondershare/drfone/ui/activity/DeepRecoveryScanInfoActivity$a:d	()V
      //   35: aload_0
      //   36: invokevirtual 116	com/wondershare/drfone/ui/activity/DeepRecoveryScanInfoActivity$a:isCancelled	()Z
      //   39: istore_2
      //   40: iload_2
      //   41: ifeq +5 -> 46
      //   44: aconst_null
      //   45: areturn
      //   46: aload_0
      //   47: aload_0
      //   48: ldc 118
      //   50: invokestatic 124	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
      //   53: invokespecial 99	com/wondershare/drfone/ui/activity/DeepRecoveryScanInfoActivity$a:a	(Landroid/net/Uri;)I
      //   56: putfield 32	com/wondershare/drfone/ui/activity/DeepRecoveryScanInfoActivity$a:b	I
      //   59: aload_0
      //   60: getfield 48	com/wondershare/drfone/ui/activity/DeepRecoveryScanInfoActivity$a:h	Landroid/os/Handler;
      //   63: iconst_0
      //   64: iconst_1
      //   65: aload_0
      //   66: getfield 32	com/wondershare/drfone/ui/activity/DeepRecoveryScanInfoActivity$a:b	I
      //   69: invokestatic 105	android/os/Message:obtain	(Landroid/os/Handler;III)Landroid/os/Message;
      //   72: invokevirtual 108	android/os/Message:sendToTarget	()V
      //   75: aload_0
      //   76: invokespecial 110	com/wondershare/drfone/ui/activity/DeepRecoveryScanInfoActivity$a:c	()V
      //   79: aload_0
      //   80: invokevirtual 116	com/wondershare/drfone/ui/activity/DeepRecoveryScanInfoActivity$a:isCancelled	()Z
      //   83: istore_2
      //   84: iload_2
      //   85: ifne -41 -> 44
      //   88: aload_0
      //   89: aload_0
      //   90: getstatic 129	android/provider/MediaStore$Images$Media:EXTERNAL_CONTENT_URI	Landroid/net/Uri;
      //   93: invokespecial 99	com/wondershare/drfone/ui/activity/DeepRecoveryScanInfoActivity$a:a	(Landroid/net/Uri;)I
      //   96: putfield 34	com/wondershare/drfone/ui/activity/DeepRecoveryScanInfoActivity$a:c	I
      //   99: aload_0
      //   100: getfield 48	com/wondershare/drfone/ui/activity/DeepRecoveryScanInfoActivity$a:h	Landroid/os/Handler;
      //   103: iconst_0
      //   104: iconst_2
      //   105: aload_0
      //   106: getfield 34	com/wondershare/drfone/ui/activity/DeepRecoveryScanInfoActivity$a:c	I
      //   109: invokestatic 105	android/os/Message:obtain	(Landroid/os/Handler;III)Landroid/os/Message;
      //   112: invokevirtual 108	android/os/Message:sendToTarget	()V
      //   115: aload_0
      //   116: invokespecial 110	com/wondershare/drfone/ui/activity/DeepRecoveryScanInfoActivity$a:c	()V
      //   119: aload_0
      //   120: invokespecial 112	com/wondershare/drfone/ui/activity/DeepRecoveryScanInfoActivity$a:d	()V
      //   123: aload_0
      //   124: invokevirtual 116	com/wondershare/drfone/ui/activity/DeepRecoveryScanInfoActivity$a:isCancelled	()Z
      //   127: istore_2
      //   128: iload_2
      //   129: ifne -85 -> 44
      //   132: aload_0
      //   133: aload_0
      //   134: getstatic 132	android/provider/MediaStore$Video$Media:EXTERNAL_CONTENT_URI	Landroid/net/Uri;
      //   137: invokespecial 99	com/wondershare/drfone/ui/activity/DeepRecoveryScanInfoActivity$a:a	(Landroid/net/Uri;)I
      //   140: putfield 36	com/wondershare/drfone/ui/activity/DeepRecoveryScanInfoActivity$a:d	I
      //   143: aload_0
      //   144: getfield 48	com/wondershare/drfone/ui/activity/DeepRecoveryScanInfoActivity$a:h	Landroid/os/Handler;
      //   147: iconst_0
      //   148: iconst_3
      //   149: aload_0
      //   150: getfield 36	com/wondershare/drfone/ui/activity/DeepRecoveryScanInfoActivity$a:d	I
      //   153: invokestatic 105	android/os/Message:obtain	(Landroid/os/Handler;III)Landroid/os/Message;
      //   156: invokevirtual 108	android/os/Message:sendToTarget	()V
      //   159: aload_0
      //   160: invokespecial 110	com/wondershare/drfone/ui/activity/DeepRecoveryScanInfoActivity$a:c	()V
      //   163: aload_0
      //   164: invokespecial 112	com/wondershare/drfone/ui/activity/DeepRecoveryScanInfoActivity$a:d	()V
      //   167: aload_0
      //   168: invokevirtual 116	com/wondershare/drfone/ui/activity/DeepRecoveryScanInfoActivity$a:isCancelled	()Z
      //   171: istore_2
      //   172: iload_2
      //   173: ifne -129 -> 44
      //   176: aload_0
      //   177: aload_0
      //   178: getstatic 135	android/provider/MediaStore$Audio$Media:EXTERNAL_CONTENT_URI	Landroid/net/Uri;
      //   181: invokespecial 99	com/wondershare/drfone/ui/activity/DeepRecoveryScanInfoActivity$a:a	(Landroid/net/Uri;)I
      //   184: putfield 38	com/wondershare/drfone/ui/activity/DeepRecoveryScanInfoActivity$a:e	I
      //   187: aload_0
      //   188: getfield 48	com/wondershare/drfone/ui/activity/DeepRecoveryScanInfoActivity$a:h	Landroid/os/Handler;
      //   191: iconst_0
      //   192: iconst_4
      //   193: aload_0
      //   194: getfield 38	com/wondershare/drfone/ui/activity/DeepRecoveryScanInfoActivity$a:e	I
      //   197: invokestatic 105	android/os/Message:obtain	(Landroid/os/Handler;III)Landroid/os/Message;
      //   200: invokevirtual 108	android/os/Message:sendToTarget	()V
      //   203: aload_0
      //   204: invokespecial 110	com/wondershare/drfone/ui/activity/DeepRecoveryScanInfoActivity$a:c	()V
      //   207: aload_0
      //   208: invokespecial 112	com/wondershare/drfone/ui/activity/DeepRecoveryScanInfoActivity$a:d	()V
      //   211: aload_0
      //   212: invokevirtual 116	com/wondershare/drfone/ui/activity/DeepRecoveryScanInfoActivity$a:isCancelled	()Z
      //   215: ifne -171 -> 44
      //   218: aload_0
      //   219: getfield 48	com/wondershare/drfone/ui/activity/DeepRecoveryScanInfoActivity$a:h	Landroid/os/Handler;
      //   222: iconst_0
      //   223: iconst_5
      //   224: iconst_0
      //   225: invokestatic 105	android/os/Message:obtain	(Landroid/os/Handler;III)Landroid/os/Message;
      //   228: invokevirtual 108	android/os/Message:sendToTarget	()V
      //   231: aload_0
      //   232: invokespecial 110	com/wondershare/drfone/ui/activity/DeepRecoveryScanInfoActivity$a:c	()V
      //   235: aload_0
      //   236: invokespecial 112	com/wondershare/drfone/ui/activity/DeepRecoveryScanInfoActivity$a:d	()V
      //   239: aload_0
      //   240: invokevirtual 116	com/wondershare/drfone/ui/activity/DeepRecoveryScanInfoActivity$a:isCancelled	()Z
      //   243: ifne -199 -> 44
      //   246: aload_0
      //   247: getfield 48	com/wondershare/drfone/ui/activity/DeepRecoveryScanInfoActivity$a:h	Landroid/os/Handler;
      //   250: iconst_0
      //   251: bipush 6
      //   253: iconst_0
      //   254: invokestatic 105	android/os/Message:obtain	(Landroid/os/Handler;III)Landroid/os/Message;
      //   257: invokevirtual 108	android/os/Message:sendToTarget	()V
      //   260: aload_0
      //   261: invokespecial 110	com/wondershare/drfone/ui/activity/DeepRecoveryScanInfoActivity$a:c	()V
      //   264: aload_0
      //   265: invokespecial 112	com/wondershare/drfone/ui/activity/DeepRecoveryScanInfoActivity$a:d	()V
      //   268: aload_0
      //   269: invokevirtual 116	com/wondershare/drfone/ui/activity/DeepRecoveryScanInfoActivity$a:isCancelled	()Z
      //   272: istore_2
      //   273: iload_2
      //   274: ifne -230 -> 44
      //   277: new 137	java/lang/StringBuilder
      //   280: dup
      //   281: invokespecial 138	java/lang/StringBuilder:<init>	()V
      //   284: ldc -116
      //   286: invokevirtual 144	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   289: astore_3
      //   290: getstatic 149	android/os/Build$VERSION:SDK_INT	I
      //   293: bipush 8
      //   295: if_icmplt +175 -> 470
      //   298: ldc -105
      //   300: astore_1
      //   301: aload_0
      //   302: aload_0
      //   303: aload_3
      //   304: aload_1
      //   305: invokevirtual 144	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   308: ldc -103
      //   310: invokevirtual 144	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   313: invokevirtual 157	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   316: invokestatic 124	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
      //   319: invokespecial 99	com/wondershare/drfone/ui/activity/DeepRecoveryScanInfoActivity$a:a	(Landroid/net/Uri;)I
      //   322: putfield 42	com/wondershare/drfone/ui/activity/DeepRecoveryScanInfoActivity$a:i	I
      //   325: aload_0
      //   326: getfield 48	com/wondershare/drfone/ui/activity/DeepRecoveryScanInfoActivity$a:h	Landroid/os/Handler;
      //   329: iconst_0
      //   330: bipush 8
      //   332: aload_0
      //   333: getfield 42	com/wondershare/drfone/ui/activity/DeepRecoveryScanInfoActivity$a:i	I
      //   336: invokestatic 105	android/os/Message:obtain	(Landroid/os/Handler;III)Landroid/os/Message;
      //   339: invokevirtual 108	android/os/Message:sendToTarget	()V
      //   342: aload_0
      //   343: invokespecial 110	com/wondershare/drfone/ui/activity/DeepRecoveryScanInfoActivity$a:c	()V
      //   346: aload_0
      //   347: invokespecial 112	com/wondershare/drfone/ui/activity/DeepRecoveryScanInfoActivity$a:d	()V
      //   350: aload_0
      //   351: invokevirtual 116	com/wondershare/drfone/ui/activity/DeepRecoveryScanInfoActivity$a:isCancelled	()Z
      //   354: istore_2
      //   355: iload_2
      //   356: ifne -312 -> 44
      //   359: aload_0
      //   360: aload_0
      //   361: getstatic 160	android/provider/CallLog$Calls:CONTENT_URI	Landroid/net/Uri;
      //   364: invokespecial 99	com/wondershare/drfone/ui/activity/DeepRecoveryScanInfoActivity$a:a	(Landroid/net/Uri;)I
      //   367: putfield 46	com/wondershare/drfone/ui/activity/DeepRecoveryScanInfoActivity$a:k	I
      //   370: aload_0
      //   371: getfield 48	com/wondershare/drfone/ui/activity/DeepRecoveryScanInfoActivity$a:h	Landroid/os/Handler;
      //   374: iconst_0
      //   375: bipush 9
      //   377: aload_0
      //   378: getfield 46	com/wondershare/drfone/ui/activity/DeepRecoveryScanInfoActivity$a:k	I
      //   381: invokestatic 105	android/os/Message:obtain	(Landroid/os/Handler;III)Landroid/os/Message;
      //   384: invokevirtual 108	android/os/Message:sendToTarget	()V
      //   387: aload_0
      //   388: invokespecial 110	com/wondershare/drfone/ui/activity/DeepRecoveryScanInfoActivity$a:c	()V
      //   391: aload_0
      //   392: invokespecial 112	com/wondershare/drfone/ui/activity/DeepRecoveryScanInfoActivity$a:d	()V
      //   395: aload_0
      //   396: invokevirtual 116	com/wondershare/drfone/ui/activity/DeepRecoveryScanInfoActivity$a:isCancelled	()Z
      //   399: istore_2
      //   400: iload_2
      //   401: ifne -357 -> 44
      //   404: aload_0
      //   405: getfield 25	com/wondershare/drfone/ui/activity/DeepRecoveryScanInfoActivity$a:g	Lcom/wondershare/drfone/ui/activity/DeepRecoveryScanInfoActivity;
      //   408: invokevirtual 164	com/wondershare/drfone/ui/activity/DeepRecoveryScanInfoActivity:getPackageManager	()Landroid/content/pm/PackageManager;
      //   411: astore_1
      //   412: new 166	java/util/ArrayList
      //   415: dup
      //   416: invokespecial 167	java/util/ArrayList:<init>	()V
      //   419: pop
      //   420: aload_0
      //   421: aload_1
      //   422: iconst_0
      //   423: invokevirtual 173	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
      //   426: invokeinterface 178 1 0
      //   431: putfield 44	com/wondershare/drfone/ui/activity/DeepRecoveryScanInfoActivity$a:j	I
      //   434: aload_0
      //   435: getfield 48	com/wondershare/drfone/ui/activity/DeepRecoveryScanInfoActivity$a:h	Landroid/os/Handler;
      //   438: iconst_0
      //   439: bipush 7
      //   441: aload_0
      //   442: getfield 44	com/wondershare/drfone/ui/activity/DeepRecoveryScanInfoActivity$a:j	I
      //   445: invokestatic 105	android/os/Message:obtain	(Landroid/os/Handler;III)Landroid/os/Message;
      //   448: invokevirtual 108	android/os/Message:sendToTarget	()V
      //   451: aload_0
      //   452: invokespecial 110	com/wondershare/drfone/ui/activity/DeepRecoveryScanInfoActivity$a:c	()V
      //   455: aload_0
      //   456: invokespecial 112	com/wondershare/drfone/ui/activity/DeepRecoveryScanInfoActivity$a:d	()V
      //   459: aload_0
      //   460: invokevirtual 116	com/wondershare/drfone/ui/activity/DeepRecoveryScanInfoActivity$a:isCancelled	()Z
      //   463: istore_2
      //   464: iload_2
      //   465: ifeq -421 -> 44
      //   468: aconst_null
      //   469: areturn
      //   470: ldc -76
      //   472: astore_1
      //   473: goto -172 -> 301
      //   476: astore_1
      //   477: ldc -74
      //   479: aload_1
      //   480: invokevirtual 183	java/lang/Exception:toString	()Ljava/lang/String;
      //   483: invokestatic 188	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
      //   486: pop
      //   487: aconst_null
      //   488: areturn
      //   489: astore_1
      //   490: goto -56 -> 434
      //   493: astore_1
      //   494: goto -124 -> 370
      //   497: astore_1
      //   498: goto -173 -> 325
      //   501: astore_1
      //   502: goto -315 -> 187
      //   505: astore_1
      //   506: goto -363 -> 143
      //   509: astore_1
      //   510: goto -411 -> 99
      //   513: astore_1
      //   514: goto -455 -> 59
      //   517: astore_1
      //   518: goto -507 -> 11
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	521	0	this	a
      //   0	521	1	paramArrayOfObject	Object[]
      //   39	426	2	bool	boolean
      //   289	15	3	localStringBuilder	StringBuilder
      // Exception table:
      //   from	to	target	type
      //   11	40	476	java/lang/Exception
      //   59	84	476	java/lang/Exception
      //   99	128	476	java/lang/Exception
      //   143	172	476	java/lang/Exception
      //   187	273	476	java/lang/Exception
      //   325	355	476	java/lang/Exception
      //   370	400	476	java/lang/Exception
      //   434	464	476	java/lang/Exception
      //   404	434	489	java/lang/Exception
      //   359	370	493	java/lang/Exception
      //   277	298	497	java/lang/Exception
      //   301	325	497	java/lang/Exception
      //   176	187	501	java/lang/Exception
      //   132	143	505	java/lang/Exception
      //   88	99	509	java/lang/Exception
      //   46	59	513	java/lang/Exception
      //   0	11	517	java/lang/Exception
    }
    
    @TargetApi(12)
    protected void onPostExecute(Object paramObject)
    {
      if (isCancelled()) {
        return;
      }
      if (DeepRecoveryScanInfoActivity.this.c != null) {
        DeepRecoveryScanInfoActivity.this.c.b();
      }
      DeepRecoveryScanInfoActivity.this.g.a(DeepRecoveryScanInfoActivity.n(DeepRecoveryScanInfoActivity.this), 0);
    }
  }
  
  private class b
  {
    public String a;
    public String b;
    
    public b(String paramString1, String paramString2)
    {
      this.a = paramString1;
      this.b = paramString2;
    }
  }
  
  public class c
    extends BaseAdapter
  {
    private List<DeepRecoveryScanInfoActivity.b> b = new ArrayList();
    private LayoutInflater c;
    private Context d;
    private ListView e;
    private boolean f = false;
    
    public c(ListView paramListView, List<DeepRecoveryScanInfoActivity.b> paramList)
    {
      Collection localCollection;
      this.b.addAll(localCollection);
      this.c = LayoutInflater.from(paramListView);
      this.d = paramListView;
      this.e = paramList;
    }
    
    private Drawable a(String paramString)
    {
      if (TextUtils.isEmpty(paramString)) {
        return DeepRecoveryScanInfoActivity.this.getResources().getDrawable(2130837648);
      }
      if (paramString.equals(DeepRecoveryScanInfoActivity.c(DeepRecoveryScanInfoActivity.this))) {
        return DeepRecoveryScanInfoActivity.this.getResources().getDrawable(2130837648);
      }
      if (paramString.equals(DeepRecoveryScanInfoActivity.e(DeepRecoveryScanInfoActivity.this))) {
        return DeepRecoveryScanInfoActivity.this.getResources().getDrawable(2130837735);
      }
      if (paramString.equals(DeepRecoveryScanInfoActivity.h(DeepRecoveryScanInfoActivity.this))) {
        return DeepRecoveryScanInfoActivity.this.getResources().getDrawable(2130837745);
      }
      if (paramString.equals(DeepRecoveryScanInfoActivity.i(DeepRecoveryScanInfoActivity.this))) {
        return DeepRecoveryScanInfoActivity.this.getResources().getDrawable(2130837765);
      }
      if (paramString.equals(DeepRecoveryScanInfoActivity.f(DeepRecoveryScanInfoActivity.this))) {
        return DeepRecoveryScanInfoActivity.this.getResources().getDrawable(2130837579);
      }
      if (paramString.equals(DeepRecoveryScanInfoActivity.g(DeepRecoveryScanInfoActivity.this))) {
        return DeepRecoveryScanInfoActivity.this.getResources().getDrawable(2130837660);
      }
      if (paramString.equals(DeepRecoveryScanInfoActivity.j(DeepRecoveryScanInfoActivity.this))) {
        return DeepRecoveryScanInfoActivity.this.getResources().getDrawable(2130837768);
      }
      if (paramString.equals(DeepRecoveryScanInfoActivity.k(DeepRecoveryScanInfoActivity.this))) {
        return DeepRecoveryScanInfoActivity.this.getResources().getDrawable(2130837576);
      }
      if (paramString.equals(DeepRecoveryScanInfoActivity.m(DeepRecoveryScanInfoActivity.this))) {
        return DeepRecoveryScanInfoActivity.this.getResources().getDrawable(2130837593);
      }
      if (paramString.equals(DeepRecoveryScanInfoActivity.l(DeepRecoveryScanInfoActivity.this))) {
        return DeepRecoveryScanInfoActivity.this.getResources().getDrawable(2130837667);
      }
      return DeepRecoveryScanInfoActivity.this.getResources().getDrawable(2130837648);
    }
    
    private void a(final View paramView, Animation.AnimationListener paramAnimationListener)
    {
      Animation local2 = new Animation()
      {
        protected void applyTransformation(float paramAnonymousFloat, Transformation paramAnonymousTransformation)
        {
          if (paramAnonymousFloat == 1.0F)
          {
            paramView.setVisibility(8);
            return;
          }
          paramView.getLayoutParams().height = (this.b - (int)(this.b * paramAnonymousFloat));
          paramView.requestLayout();
        }
        
        public boolean willChangeBounds()
        {
          return true;
        }
      };
      if (paramAnimationListener != null) {
        local2.setAnimationListener(paramAnimationListener);
      }
      local2.setDuration(500L);
      paramView.startAnimation(local2);
    }
    
    public void a(View paramView, int paramInt)
    {
      a(paramView, new Animation.AnimationListener()
      {
        public void onAnimationEnd(Animation paramAnonymousAnimation)
        {
          DeepRecoveryScanInfoActivity.this.runOnUiThread(new Runnable()
          {
            public void run()
            {
              DeepRecoveryScanInfoActivity.c.this.notifyDataSetChanged();
              DeepRecoveryScanInfoActivity.o(DeepRecoveryScanInfoActivity.this).setVisibility(0);
            }
          });
        }
        
        public void onAnimationRepeat(Animation paramAnonymousAnimation) {}
        
        public void onAnimationStart(Animation paramAnonymousAnimation) {}
      });
    }
    
    public void a(List<DeepRecoveryScanInfoActivity.b> paramList)
    {
      this.b = paramList;
      notifyDataSetChanged();
    }
    
    public int getCount()
    {
      if (this.f) {
        return this.b.size() + 1;
      }
      return this.b.size();
    }
    
    public Object getItem(int paramInt)
    {
      List localList = this.b;
      if (this.f) {}
      for (int i = this.b.size() + 1;; i = this.b.size()) {
        return localList.get(paramInt % i);
      }
    }
    
    public long getItemId(int paramInt)
    {
      return paramInt;
    }
    
    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      paramViewGroup = this.b;
      int i;
      DeepRecoveryScanInfoActivity.b localB;
      if (this.f)
      {
        i = this.b.size() + 1;
        localB = (DeepRecoveryScanInfoActivity.b)paramViewGroup.get(paramInt % i);
        if (paramView != null) {
          break label243;
        }
        paramViewGroup = new a();
        paramView = this.c.inflate(2130903120, null);
        paramViewGroup.a = ((ImageView)paramView.findViewById(2131427703));
        paramViewGroup.b = ((TextView)paramView.findViewById(2131427705));
        paramViewGroup.c = ((TextView)paramView.findViewById(2131427706));
        if ((localB.a.equals(DeepRecoveryScanInfoActivity.j(DeepRecoveryScanInfoActivity.this))) || (localB.a.equals(DeepRecoveryScanInfoActivity.g(DeepRecoveryScanInfoActivity.this)))) {
          paramViewGroup.c.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymousView)
            {
              Toast.makeText(DeepRecoveryScanInfoActivity.c.a(DeepRecoveryScanInfoActivity.c.this), "To preview the deleted data, please use the desktop version to scan your device.", 0).show();
            }
          });
        }
        Animation localAnimation = AnimationUtils.loadAnimation(this.d, 2130968587);
        localAnimation.setDuration(500L);
        paramView.startAnimation(localAnimation);
      }
      for (;;)
      {
        paramView.setTag(paramViewGroup);
        try
        {
          paramViewGroup.a.setImageDrawable(a(localB.a));
          paramViewGroup.b.setText(localB.a);
          paramViewGroup.c.setText(String.valueOf(localB.b));
          return paramView;
        }
        catch (Exception paramViewGroup) {}
        i = this.b.size();
        break;
        label243:
        paramViewGroup = (a)paramView.getTag();
      }
      return paramView;
    }
    
    public class a
    {
      public ImageView a;
      public TextView b;
      public TextView c;
      
      public a() {}
    }
  }
}
