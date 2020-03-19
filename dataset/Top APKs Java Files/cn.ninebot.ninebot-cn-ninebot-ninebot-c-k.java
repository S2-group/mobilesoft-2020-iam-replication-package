package cn.ninebot.ninebot.c;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import cn.ninebot.libraries.h.p;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;
import com.mob.MobSDK;
import java.io.File;
import java.util.HashMap;
import java.util.List;

public class k
{
  private static k b;
  PlatformActionListener a = new PlatformActionListener()
  {
    public void onCancel(Platform paramAnonymousPlatform, int paramAnonymousInt)
    {
      if (paramAnonymousPlatform.getName().equals(QQ.NAME)) {}
    }
    
    public void onComplete(Platform paramAnonymousPlatform, int paramAnonymousInt, HashMap<String, Object> paramAnonymousHashMap)
    {
      p.a(k.a(k.this), k.a(k.this).getString(2131756405));
    }
    
    public void onError(Platform paramAnonymousPlatform, int paramAnonymousInt, Throwable paramAnonymousThrowable) {}
  };
  private Context c;
  
  public k(Context paramContext)
  {
    this.c = paramContext;
    b = this;
    MobSDK.init(paramContext, "9621d6b0abfc", "76ac2773e228a0b365337ab070ca2838");
  }
  
  public static k a(Context paramContext)
  {
    if (b == null) {
      try
      {
        if (b == null) {
          b = new k(paramContext);
        }
      }
      finally {}
    }
    return b;
  }
  
  public void a(final Context paramContext, final String paramString1, final String paramString2, final PlatformActionListener paramPlatformActionListener)
  {
    if (!b(paramContext))
    {
      p.a(paramContext, paramContext.getString(2131756387));
      return;
    }
    new Thread(new Runnable()
    {
      public void run()
      {
        MobSDK.init(paramContext);
        Platform.ShareParams localShareParams = new Platform.ShareParams();
        localShareParams.setTitle(paramContext.getString(2131756396));
        localShareParams.setText(paramString1);
        localShareParams.setShareType(2);
        if (paramString2 != null)
        {
          localObject = new File(paramString2);
          if ((((File)localObject).exists()) && (((File)localObject).isFile())) {
            localShareParams.setImagePath(paramString2);
          }
        }
        Platform localPlatform = ShareSDK.getPlatform(Wechat.NAME);
        if (paramPlatformActionListener != null) {}
        for (Object localObject = paramPlatformActionListener;; localObject = k.this.a)
        {
          localPlatform.setPlatformActionListener((PlatformActionListener)localObject);
          break;
        }
        localPlatform.share(localShareParams);
      }
    }).start();
  }
  
  public void a(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, PlatformActionListener paramPlatformActionListener)
  {
    OnekeyShare localOnekeyShare = new OnekeyShare();
    localOnekeyShare.disableSSOWhenAuthorize();
    if (paramString1 != null) {
      localOnekeyShare.setPlatform(paramString1);
    }
    localOnekeyShare.setTitle(paramString2);
    localOnekeyShare.setTitleUrl(paramString6);
    localOnekeyShare.setText(paramString3);
    localOnekeyShare.setImagePath(paramString4);
    localOnekeyShare.setUrl(paramString6);
    localOnekeyShare.setImageUrl(paramString5);
    localOnekeyShare.setCallback(paramPlatformActionListener);
    localOnekeyShare.show(paramContext);
  }
  
  public void a(final String paramString1, final String paramString2, final String paramString3, final int paramInt, final String paramString4)
  {
    if (!b(this.c))
    {
      p.a(this.c, this.c.getString(2131756387));
      return;
    }
    new Thread(new Runnable()
    {
      public void run()
      {
        MobSDK.init(k.a(k.this));
        Platform.ShareParams localShareParams = new Platform.ShareParams();
        localShareParams.setTitle(paramString2);
        localShareParams.setText(paramString3);
        localShareParams.setShareType(8);
        localShareParams.setImageData(BitmapFactory.decodeResource(k.a(k.this).getResources(), paramInt));
        if (paramString4 != null)
        {
          localObject = new File(paramString4);
          if ((((File)localObject).exists()) && (((File)localObject).isFile())) {
            localShareParams.setFilePath(paramString4);
          }
        }
        Object localObject = ShareSDK.getPlatform(paramString1);
        ((Platform)localObject).setPlatformActionListener(k.this.a);
        ((Platform)localObject).share(localShareParams);
      }
    }).start();
  }
  
  public void b(final Context paramContext, final String paramString1, final String paramString2, final PlatformActionListener paramPlatformActionListener)
  {
    if (!b(paramContext))
    {
      p.a(paramContext, paramContext.getString(2131756387));
      return;
    }
    new Thread(new Runnable()
    {
      public void run()
      {
        MobSDK.init(paramContext);
        Platform.ShareParams localShareParams = new Platform.ShareParams();
        localShareParams.setTitle(paramContext.getString(2131756396));
        localShareParams.setText(paramString1);
        localShareParams.setShareType(2);
        if (paramString2 != null)
        {
          localObject = new File(paramString2);
          if ((((File)localObject).exists()) && (((File)localObject).isFile())) {
            localShareParams.setImagePath(paramString2);
          }
        }
        Platform localPlatform = ShareSDK.getPlatform(WechatMoments.NAME);
        if (paramPlatformActionListener != null) {}
        for (Object localObject = paramPlatformActionListener;; localObject = k.this.a)
        {
          localPlatform.setPlatformActionListener((PlatformActionListener)localObject);
          break;
        }
        localPlatform.share(localShareParams);
      }
    }).start();
  }
  
  public boolean b(Context paramContext)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    if (paramContext != null)
    {
      int i = 0;
      while (i < paramContext.size())
      {
        if (((PackageInfo)paramContext.get(i)).packageName.equals("com.tencent.mm")) {
          return true;
        }
        i += 1;
      }
    }
    return false;
  }
  
  public void c(final Context paramContext, String paramString1, final String paramString2, final PlatformActionListener paramPlatformActionListener)
  {
    if (!c(paramContext))
    {
      p.a(paramContext, paramContext.getString(2131755378));
      return;
    }
    new Thread(new Runnable()
    {
      public void run()
      {
        MobSDK.init(paramContext);
        Platform.ShareParams localShareParams = new Platform.ShareParams();
        localShareParams.setShareType(2);
        if (paramString2 != null)
        {
          localObject = new File(paramString2);
          if ((((File)localObject).exists()) && (((File)localObject).isFile())) {
            localShareParams.setImagePath(paramString2);
          }
        }
        Platform localPlatform = ShareSDK.getPlatform(QQ.NAME);
        if (paramPlatformActionListener != null) {}
        for (Object localObject = paramPlatformActionListener;; localObject = k.this.a)
        {
          localPlatform.setPlatformActionListener((PlatformActionListener)localObject);
          break;
        }
        localPlatform.share(localShareParams);
      }
    }).start();
  }
  
  public boolean c(Context paramContext)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    if (paramContext != null)
    {
      int i = 0;
      while (i < paramContext.size())
      {
        if (((PackageInfo)paramContext.get(i)).packageName.equals("com.tencent.mobileqq")) {
          return true;
        }
        i += 1;
      }
    }
    return false;
  }
  
  public void d(final Context paramContext, final String paramString1, final String paramString2, final PlatformActionListener paramPlatformActionListener)
  {
    if (!d(paramContext))
    {
      p.a(paramContext, paramContext.getString(2131756387));
      return;
    }
    new Thread(new Runnable()
    {
      public void run()
      {
        MobSDK.init(paramContext);
        Platform.ShareParams localShareParams = new Platform.ShareParams();
        localShareParams.setText(paramString1);
        if (paramString2 != null)
        {
          localObject = new File(paramString2);
          if ((((File)localObject).exists()) && (((File)localObject).isFile())) {
            localShareParams.setImagePath(paramString2);
          }
        }
        Platform localPlatform = ShareSDK.getPlatform(SinaWeibo.NAME);
        if (paramPlatformActionListener != null) {}
        for (Object localObject = paramPlatformActionListener;; localObject = k.this.a)
        {
          localPlatform.setPlatformActionListener((PlatformActionListener)localObject);
          break;
        }
        localPlatform.share(localShareParams);
      }
    }).start();
  }
  
  public boolean d(Context paramContext)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    if (paramContext != null)
    {
      int i = 0;
      while (i < paramContext.size())
      {
        if (((PackageInfo)paramContext.get(i)).packageName.equals("com.sina.weibo")) {
          return true;
        }
        i += 1;
      }
    }
    return false;
  }
}
