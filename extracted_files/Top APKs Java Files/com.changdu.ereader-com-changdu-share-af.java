package com.changdu.share;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.util.SparseArray;
import android.view.ViewGroup;
import android.widget.Toast;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class af
  implements k
{
  private static final String a = "UmengShareApi";
  private static SparseArray<String> e = new SparseArray();
  private static final int f = 1;
  private static final int g = 0;
  private UMShareAPI b;
  private Context c;
  private x d = new y();
  private int h = 0;
  
  static
  {
    e.put(j.j.d, "分享内容为空");
    e.put(j.j.g, "未知错误");
    e.put(j.j.a, "授权失败");
    e.put(j.j.f, "分享失败");
    e.put(j.j.e, "分享内容不合法");
    e.put(j.j.c, "获取用户资料失败");
    e.put(j.j.b, "没有安装应用");
  }
  
  public af() {}
  
  private UMImage a(String paramString)
  {
    boolean bool = b(paramString);
    Object localObject1 = null;
    if (!bool) {}
    try
    {
      try
      {
        localObject4 = BitmapFactory.decodeFile(new URI(paramString).getPath());
      }
      catch (URISyntaxException localURISyntaxException)
      {
        localURISyntaxException.printStackTrace();
      }
    }
    catch (Throwable localThrowable2)
    {
      try
      {
        localObject2 = BitmapFactory.decodeFile(paramString);
        localObject4 = localObject2;
        if (localObject2 != null) {
          break label93;
        }
      }
      catch (Throwable localThrowable2)
      {
        try
        {
          Object localObject4;
          for (;;)
          {
            int i = Integer.valueOf(paramString).intValue();
            localObject4 = ((BitmapDrawable)this.c.getResources().getDrawable(i)).getBitmap();
            if (localObject4 != null)
            {
              paramString = new UMImage(this.c, (Bitmap)localObject4);
              paramString.setThumb(paramString);
              return paramString;
            }
            Object localObject2 = new UMImage(this.c, paramString);
            return localObject2;
            localThrowable1 = localThrowable1;
          }
          localThrowable2 = localThrowable2;
          localObject3 = localObject4;
        }
        catch (Throwable localThrowable3)
        {
          for (;;)
          {
            Object localObject3;
            Object localObject5 = localObject3;
          }
        }
      }
    }
    localObject4 = null;
    localObject2 = localObject4;
    if (localObject4 != null) {}
  }
  
  private Throwable a(Throwable paramThrowable)
  {
    Object localObject = paramThrowable.getMessage();
    int j = e.size();
    int i = 0;
    while (i < j)
    {
      if (((String)localObject).contains((CharSequence)e.valueAt(i)))
      {
        i = e.keyAt(i);
        str = this.c.getResources().getString(i);
        break label72;
      }
      i += 1;
    }
    String str = null;
    label72:
    if (str == null) {
      return paramThrowable;
    }
    i = ((String)localObject).lastIndexOf("---");
    paramThrowable = "";
    if (i > -1) {
      paramThrowable = ((String)localObject).substring(i);
    }
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append(str);
    ((StringBuilder)localObject).append(paramThrowable);
    return new Throwable(((StringBuilder)localObject).toString());
  }
  
  private void a(Activity paramActivity, ShareAction paramShareAction, int paramInt, r paramR)
  {
    SHARE_MEDIA localSHARE_MEDIA = a(paramInt);
    if (this.b.isAuthorize(paramActivity, localSHARE_MEDIA)) {
      this.b.deleteOauth(paramActivity, localSHARE_MEDIA, null);
    }
    paramActivity = new ag(this, paramR);
    paramShareAction.setPlatform(localSHARE_MEDIA);
    paramShareAction.setCallback(paramActivity);
    paramShareAction.share();
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    int i = 0;
    paramContext = paramContext.getInstalledPackages(0);
    ArrayList localArrayList = new ArrayList();
    if (paramContext != null) {
      while (i < paramContext.size())
      {
        localArrayList.add(((PackageInfo)paramContext.get(i)).packageName);
        i += 1;
      }
    }
    return localArrayList.contains(paramString);
  }
  
  private void b()
  {
    PlatformConfig.setQQZone(n.c, n.d);
    PlatformConfig.setSinaWeibo(n.f, n.g, n.h);
    PlatformConfig.setWeixin(n.a, n.b);
    PlatformConfig.setDing(n.e);
    PlatformConfig.setTwitter(this.c.getString(j.j.aB), this.c.getString(j.j.aC));
    com.umeng.socialize.Config.DEBUG = false;
    com.umeng.socialize.Config.isUmengSina = Boolean.valueOf(true);
  }
  
  private boolean b(String paramString)
  {
    return (paramString == null) || ("".equalsIgnoreCase(paramString.trim()));
  }
  
  private String c(Activity paramActivity, int paramInt)
  {
    if ((paramInt == 1) || (paramInt == 11))
    {
      int i;
      if ((!b(paramActivity, 1)) && (!b(paramActivity, 11))) {
        i = 0;
      } else {
        i = 1;
      }
      if (i == 0)
      {
        localObject = "未安装QQ应用";
        break label55;
      }
    }
    Object localObject = null;
    label55:
    boolean bool = b(paramActivity, paramInt);
    if (paramInt != 31)
    {
      paramActivity = (Activity)localObject;
      if (paramInt != 3) {}
    }
    else
    {
      paramActivity = (Activity)localObject;
      if ((bool ^ true)) {
        paramActivity = "未安装微信应用";
      }
    }
    localObject = paramActivity;
    if (paramInt == 4)
    {
      localObject = paramActivity;
      if (!a(this.c, "com.alibaba.android.rimet")) {
        localObject = "未安装钉钉应用";
      }
    }
    return localObject;
  }
  
  private void c(Activity paramActivity, String paramString1, String paramString2, String paramString3, String paramString4, int paramInt, r paramR)
  {
    String str = c(paramActivity, paramInt);
    if (!b(str))
    {
      paramR.a(paramInt, new Exception(str));
      return;
    }
    if ((b(paramString4)) && (!b(paramString1)))
    {
      if (b(paramString2)) {
        paramString2 = paramString3;
      }
      a(paramActivity, paramString1, paramString2, paramInt, paramR);
      return;
    }
    if (!b(paramString4))
    {
      if (paramInt == 903)
      {
        paramString2 = new StringBuilder();
        paramString2.append(paramString3);
        paramString2.append(" ");
        paramString2.append(paramString4);
        paramString2 = paramString2.toString();
        if ((paramInt == 903) && (paramString3.length() + paramString4.length() / 2 >= 140))
        {
          int i = 140 - paramString4.length() / 2;
          paramString2 = paramString4;
          if (i > 2)
          {
            paramString2 = new StringBuilder();
            paramString2.append(paramString3.substring(0, i - 2));
            paramString2.append("... ");
            paramString2.append(paramString4);
            paramString2 = paramString2.toString();
          }
        }
        a(paramActivity, paramString1, paramString2, paramInt, paramR);
        return;
      }
      b(paramActivity, paramString4, paramString2, paramString1, paramString3, paramInt, paramR);
      return;
    }
    Toast.makeText(paramActivity, j.j.az, 1).show();
  }
  
  public int a(SHARE_MEDIA paramSHARE_MEDIA)
  {
    switch (aj.a[paramSHARE_MEDIA.ordinal()])
    {
    default: 
      return 0;
    case 12: 
      return 907;
    case 11: 
      return 905;
    case 10: 
      return 906;
    case 9: 
      return 903;
    case 8: 
      return 902;
    case 7: 
      return 901;
    case 6: 
      return 4;
    case 5: 
      return 31;
    case 4: 
      return 3;
    case 3: 
      return 11;
    case 2: 
      return 2;
    }
    return 1;
  }
  
  public SHARE_MEDIA a(int paramInt)
  {
    if (paramInt != 11)
    {
      if (paramInt != 31)
      {
        if (paramInt != 111)
        {
          switch (paramInt)
          {
          default: 
            switch (paramInt)
            {
            default: 
              switch (paramInt)
              {
              default: 
                StringBuilder localStringBuilder = new StringBuilder();
                localStringBuilder.append("undefined platform id:");
                localStringBuilder.append(paramInt);
                throw new RuntimeException(localStringBuilder.toString());
              case 907: 
                return SHARE_MEDIA.WHATSAPP;
              case 906: 
                return SHARE_MEDIA.FACEBOOK_MESSAGER;
              }
              return SHARE_MEDIA.GOOGLEPLUS;
            case 903: 
              return SHARE_MEDIA.TWITTER;
            case 902: 
              return SHARE_MEDIA.LINE;
            }
            return SHARE_MEDIA.FACEBOOK;
          case 4: 
            return SHARE_MEDIA.DINGTALK;
          case 3: 
            return SHARE_MEDIA.WEIXIN;
          case 2: 
            return SHARE_MEDIA.SINA;
          }
          return SHARE_MEDIA.QQ;
        }
        return SHARE_MEDIA.TENCENT;
      }
      return SHARE_MEDIA.WEIXIN_CIRCLE;
    }
    return SHARE_MEDIA.QZONE;
  }
  
  public void a()
  {
    this.b.release();
  }
  
  public void a(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if ((this.h == 0) && (l.a(paramInt1, paramInt2, paramIntent))) {
      return;
    }
    if ((this.h == 1) && (h.a(paramInt1, paramInt2, paramIntent))) {
      return;
    }
    try
    {
      this.b.onActivityResult(paramInt1, paramInt2, paramIntent);
      return;
    }
    catch (Throwable paramIntent)
    {
      Log.e("UmengShareApi", paramIntent.getMessage());
    }
  }
  
  public void a(Activity paramActivity, int paramInt, b paramB)
  {
    SHARE_MEDIA localSHARE_MEDIA = a(paramInt);
    if (this.b.isAuthorize(paramActivity, localSHARE_MEDIA)) {
      this.b.deleteOauth(paramActivity, localSHARE_MEDIA, null);
    }
    paramB = new ah(this, paramB);
    this.b.doOauthVerify(paramActivity, localSHARE_MEDIA, paramB);
  }
  
  public void a(Activity paramActivity, String paramString1, String paramString2, int paramInt, r paramR)
  {
    if (l.a(paramActivity, paramString1, paramString2, paramInt, paramR)) {
      return;
    }
    paramString1 = a(paramString1);
    ShareAction localShareAction = new ShareAction(paramActivity);
    localShareAction.withText(paramString2);
    localShareAction.withMedia(paramString1);
    a(paramActivity, localShareAction, paramInt, paramR);
  }
  
  public void a(Activity paramActivity, String paramString1, String paramString2, String paramString3, String paramString4, int paramInt, r paramR)
  {
    this.h = 0;
    try
    {
      c(paramActivity, paramString1, paramString2, paramString3, paramString4, paramInt, paramR);
      return;
    }
    catch (Throwable paramActivity)
    {
      Log.e("UmengShareApi", paramActivity.getMessage());
      paramR.a(paramInt, paramActivity);
    }
  }
  
  public void a(Context paramContext)
  {
    this.c = paramContext;
    b();
    this.b = UMShareAPI.get(paramContext);
  }
  
  public void a(ViewGroup paramViewGroup, i paramI)
  {
    if (this.d != null) {
      this.d.a(paramViewGroup, paramI);
    }
  }
  
  public void a(ViewGroup paramViewGroup, int[] paramArrayOfInt, i paramI)
  {
    if (this.d != null) {
      this.d.a(paramViewGroup, paramArrayOfInt, paramI, 0);
    }
  }
  
  public void a(ViewGroup paramViewGroup, int[] paramArrayOfInt, i paramI, int paramInt)
  {
    if (this.d != null) {
      this.d.a(paramViewGroup, paramArrayOfInt, paramI, paramInt);
    }
  }
  
  public void a(boolean paramBoolean)
  {
    UMShareConfig localUMShareConfig = new UMShareConfig();
    localUMShareConfig.isNeedAuthOnGetUserInfo(paramBoolean);
    this.b.setShareConfig(localUMShareConfig);
  }
  
  public boolean a(Activity paramActivity, int paramInt)
  {
    return this.b.isAuthorize(paramActivity, a(paramInt));
  }
  
  public void b(Activity paramActivity, int paramInt, b paramB)
  {
    Object localObject = c(paramActivity, paramInt);
    if (!b((String)localObject))
    {
      paramB.a(paramInt, 0, new Exception((String)localObject));
      return;
    }
    this.h = 1;
    Toast.makeText(paramActivity, paramActivity.getString(j.j.A), 0).show();
    if (h.a(paramActivity, paramInt, paramB)) {
      return;
    }
    if (paramInt == 1)
    {
      a(paramActivity, paramInt, paramB);
      return;
    }
    localObject = a(paramInt);
    if (this.b.isAuthorize(paramActivity, (SHARE_MEDIA)localObject)) {
      this.b.deleteOauth(paramActivity, (SHARE_MEDIA)localObject, null);
    }
    paramB = new ai(this, paramB);
    this.b.getPlatformInfo(paramActivity, (SHARE_MEDIA)localObject, paramB);
  }
  
  public void b(Activity paramActivity, String paramString1, String paramString2, String paramString3, String paramString4, int paramInt, r paramR)
  {
    Log.e("UmengShareApi", paramString1);
    if (l.a(paramActivity, paramString1, paramString2, paramString3, paramString4, paramInt, paramR)) {
      return;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString1);
    localStringBuilder.append(" ");
    paramString1 = new UMWeb(localStringBuilder.toString());
    paramString1.setTitle(paramString2);
    paramString1.setDescription(paramString4);
    paramString1.setThumb(a(paramString3));
    paramString2 = new ShareAction(paramActivity);
    paramString2.withMedia(paramString1);
    a(paramActivity, paramString2, paramInt, paramR);
  }
  
  public boolean b(Activity paramActivity, int paramInt)
  {
    return this.b.isInstall(paramActivity, a(paramInt));
  }
}
