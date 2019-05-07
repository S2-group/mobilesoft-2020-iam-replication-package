package com.umeng.qq.handler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import com.ali.mobisecenhance.Init;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig.Platform;
import com.umeng.socialize.ShareContent;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareConfig;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.handler.UMSSOHandler;
import com.wecut.lolicam.cws;
import com.wecut.lolicam.cwu;
import com.wecut.lolicam.cwv;
import com.wecut.lolicam.cxb;
import com.wecut.lolicam.cxc;
import com.wecut.lolicam.cxd;
import com.wecut.lolicam.cxe;
import com.wecut.lolicam.cxf;
import com.wecut.lolicam.cxj;
import com.wecut.lolicam.cxn;
import com.wecut.lolicam.cxs;
import com.wecut.lolicam.cxu;
import com.wecut.lolicam.cya;
import com.wecut.lolicam.cyb;
import com.wecut.lolicam.cye;
import com.wecut.lolicam.cyg;
import com.wecut.lolicam.cyh;
import com.wecut.lolicam.cyi;
import com.wecut.lolicam.cyy;
import com.wecut.lolicam.czc;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;
import z.z.z.z2;

public class UmengQQHandler
  extends b
{
  private cxb ˈ;
  private s ˉ;
  private final String י = "https://graph.qq.com/oauth2.0/me?access_token=";
  private final String ـ = "&unionid=1";
  
  static
  {
    Init.doFixC(UmengQQHandler.class, 570941891);
    if (Build.VERSION.SDK_INT < 0) {
      z2.class.toString();
    }
  }
  
  public UmengQQHandler() {}
  
  private static String ʻ(InputStream paramInputStream)
  {
    BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(paramInputStream));
    StringBuilder localStringBuilder = new StringBuilder();
    try
    {
      for (;;)
      {
        String str = localBufferedReader.readLine();
        if (str == null) {
          break;
        }
        localStringBuilder.append(str + "/n");
      }
      try
      {
        paramInputStream.close();
        throw localObject;
      }
      catch (IOException paramInputStream)
      {
        for (;;)
        {
          paramInputStream.printStackTrace();
        }
      }
    }
    catch (IOException localIOException)
    {
      localIOException = localIOException;
      localIOException.printStackTrace();
      try
      {
        paramInputStream.close();
        for (;;)
        {
          return localStringBuilder.toString();
          try
          {
            paramInputStream.close();
          }
          catch (IOException paramInputStream)
          {
            paramInputStream.printStackTrace();
          }
        }
      }
      catch (IOException paramInputStream)
      {
        for (;;)
        {
          paramInputStream.printStackTrace();
        }
      }
    }
    finally {}
  }
  
  private static String ʼ(String paramString)
  {
    try
    {
      paramString = new URL(paramString).openConnection();
      if (paramString == null) {
        return "";
      }
      paramString.connect();
      paramString = paramString.getInputStream();
      if (paramString != null)
      {
        paramString = ʻ(paramString);
        return paramString;
      }
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return "";
  }
  
  private static String ʽ(String paramString)
  {
    try
    {
      paramString = new URL(paramString).openConnection();
      if (paramString == null) {
        return "";
      }
      paramString.connect();
      paramString = paramString.getInputStream();
      if (paramString != null)
      {
        paramString = ʻ(paramString);
        return paramString;
      }
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return "";
  }
  
  private cxb ʿ(UMAuthListener paramUMAuthListener)
  {
    return new i(this, paramUMAuthListener);
  }
  
  private void ˆ(UMAuthListener paramUMAuthListener)
  {
    cxu.ʻ(new n(this, paramUMAuthListener), false);
  }
  
  private String ˉ()
  {
    if (this.ˉ != null) {
      return this.ˉ.ʻ;
    }
    return "";
  }
  
  private String ˊ()
  {
    if (this.ˉ != null) {
      return this.ˉ.ʽ;
    }
    return "";
  }
  
  private static String ˋ()
  {
    try
    {
      String str = URLEncoder.encode(Build.MODEL.replace(" ", "+"), "UTF-8");
      return str;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      localUnsupportedEncodingException.printStackTrace();
    }
    return "sm801";
  }
  
  public final native boolean j_();
  
  protected final native String k_();
  
  public final void ʻ(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramInt1 == 10103) {
      cws.ʻ(paramInt1, paramInt2, paramIntent, this.ˈ);
    }
    if (paramInt1 == 11101) {
      cws.ʻ(paramInt1, paramInt2, paramIntent, ʿ(this.ʾ));
    }
  }
  
  public final void ʻ(Context paramContext, PlatformConfig.Platform paramPlatform)
  {
    super.ʻ(paramContext, paramPlatform);
    if (paramContext != null) {
      this.ˉ = new s(this.ˊ, cxs.ˈ.toString());
    }
  }
  
  public final void ʻ(UMAuthListener paramUMAuthListener)
  {
    this.ʾ = paramUMAuthListener;
  }
  
  public final void ʻ(JSONObject paramJSONObject)
  {
    try
    {
      String str1 = paramJSONObject.getString("access_token");
      String str2 = paramJSONObject.getString("expires_in");
      paramJSONObject = paramJSONObject.getString("openid");
      if ((!TextUtils.isEmpty(str1)) && (!TextUtils.isEmpty(str2)) && (!TextUtils.isEmpty(paramJSONObject)))
      {
        this.ʿ.ʼ.ʼ.ʻ(str1, str2);
        this.ʿ.ʼ.ʼ.ʽ = paramJSONObject;
      }
      return;
    }
    catch (Exception paramJSONObject) {}
  }
  
  public final boolean ʻ(ShareContent paramShareContent, UMShareListener paramUMShareListener)
  {
    Object localObject4 = new t(paramShareContent);
    if (this.ʿ == null)
    {
      cxu.ʻ(new c(this, paramUMShareListener));
      return false;
    }
    this.ˈ = new g(this, paramUMShareListener);
    if (this.ˈ == null) {
      cyy.ʻ();
    }
    if (!ʾ())
    {
      if (Config.isJumptoAppStore)
      {
        paramShareContent = new Intent("android.intent.action.VIEW");
        paramShareContent.setData(Uri.parse("http://log.umsns.com/link/qq/download/"));
        ((Activity)this.ˏ.get()).startActivity(paramShareContent);
      }
      cxu.ʻ(new e(this, paramUMShareListener));
    }
    boolean bool = ˎ().isHideQzoneOnQQFriendList();
    String str = ˎ().getAppName();
    Object localObject1;
    Object localObject2;
    if ((((cyb)localObject4).ˋ == 2) || (((cyb)localObject4).ˋ == 3))
    {
      localObject1 = null;
      paramShareContent = null;
      localObject2 = new Bundle();
      if ((((cyb)localObject4).ʼ != null) && (((cyb)localObject4).ʼ.ˊ() != null))
      {
        if (cxn.ʻ(((cyb)localObject4).ʼ) <= 0) {
          paramShareContent = czc.ˋ;
        }
        localObject1 = ((cyb)localObject4).ʼ.ˊ().toString();
        ((Bundle)localObject2).putString("summary", ((cyb)localObject4).ʽ);
        ((Bundle)localObject2).putString("imageLocalUrl", (String)localObject1);
        ((Bundle)localObject2).putInt("req_type", 5);
        if (!TextUtils.isEmpty(paramShareContent)) {
          ((Bundle)localObject2).putString("error", paramShareContent);
        }
        paramShareContent = (ShareContent)localObject2;
        label262:
        if (!bool) {
          break label1009;
        }
        paramShareContent.putInt("cflag", 2);
      }
    }
    for (;;)
    {
      if (!TextUtils.isEmpty(str)) {
        paramShareContent.putString("appName", str);
      }
      localObject1 = paramShareContent.getString("error");
      if (TextUtils.isEmpty((CharSequence)localObject1)) {
        break label1020;
      }
      cxu.ʻ(new f(this, paramUMShareListener, (String)localObject1));
      return false;
      paramShareContent = czc.ʼʼ;
      break;
      Object localObject3;
      if (((cyb)localObject4).ˋ == 4)
      {
        localObject2 = null;
        paramShareContent = null;
        localObject3 = null;
        localObject4 = ((cyb)localObject4).ˆ;
        localObject1 = localObject2;
        if (((cyi)localObject4).ʾ() != null)
        {
          if (((cyi)localObject4).ʾ().ˊ() == null) {
            break label531;
          }
          paramShareContent = localObject3;
          if (cxn.ʻ(((cyi)localObject4).ʾ()) <= 0) {
            paramShareContent = czc.ˊ;
          }
        }
        for (localObject1 = ((cyi)localObject4).ʾ().ˊ().toString();; localObject1 = localObject2)
        {
          localObject2 = new Bundle();
          ((Bundle)localObject2).putString("title", t.ʻ(t.ʻ((cya)localObject4), 45));
          ((Bundle)localObject2).putString("summary", t.ʻ(t.ʼ((cya)localObject4), 60));
          ((Bundle)localObject2).putString("imageUrl", "");
          ((Bundle)localObject2).putString("imageLocalUrl", (String)localObject1);
          ((Bundle)localObject2).putInt("req_type", 2);
          ((Bundle)localObject2).putString("targetUrl", ((cyi)localObject4).ˏ);
          ((Bundle)localObject2).putString("audio_url", ((cyi)localObject4).ʽ());
          if (!TextUtils.isEmpty(paramShareContent)) {
            ((Bundle)localObject2).putString("error", paramShareContent);
          }
          paramShareContent = (ShareContent)localObject2;
          break;
          label531:
          paramShareContent = czc.ʼʼ;
        }
      }
      if (((cyb)localObject4).ˋ == 16)
      {
        localObject2 = null;
        paramShareContent = null;
        localObject3 = null;
        cyh localCyh = ((cyb)localObject4).ˉ;
        localObject1 = localObject2;
        if (localCyh.ʾ() != null)
        {
          if (localCyh.ʾ().ˊ() == null) {
            break label755;
          }
          paramShareContent = localObject3;
          if (cxn.ʻ(localCyh.ʾ()) <= 0) {
            paramShareContent = czc.ˊ;
          }
        }
        for (localObject1 = localCyh.ʾ().ˊ().toString();; localObject1 = localObject2)
        {
          localObject2 = new Bundle();
          ((Bundle)localObject2).putString("title", t.ʻ(t.ʻ(localCyh), 45));
          ((Bundle)localObject2).putString("summary", t.ʻ(t.ʼ(localCyh), 60));
          ((Bundle)localObject2).putString("imageUrl", "");
          ((Bundle)localObject2).putString("imageLocalUrl", (String)localObject1);
          ((Bundle)localObject2).putInt("req_type", 1);
          ((Bundle)localObject2).putString("targetUrl", localCyh.ʽ());
          if (TextUtils.isEmpty(((cyb)localObject4).ˉ.ʽ())) {
            ((Bundle)localObject2).putString("error", czc.ʾʾ);
          }
          if (!TextUtils.isEmpty(paramShareContent)) {
            ((Bundle)localObject2).putString("error", paramShareContent);
          }
          paramShareContent = (ShareContent)localObject2;
          break;
          label755:
          paramShareContent = czc.ʼʼ;
        }
      }
      if (((cyb)localObject4).ˋ == 8)
      {
        localObject2 = null;
        paramShareContent = null;
        localObject3 = null;
        localObject4 = ((cyb)localObject4).ʾ;
        localObject1 = localObject2;
        if (((cyg)localObject4).ʾ() != null)
        {
          if (((cyg)localObject4).ʾ().ˊ() == null) {
            break label954;
          }
          paramShareContent = localObject3;
          if (cxn.ʻ(((cyg)localObject4).ʾ()) <= 0) {
            paramShareContent = czc.ˊ;
          }
        }
        for (localObject1 = ((cyg)localObject4).ʾ().ˊ().toString();; localObject1 = localObject2)
        {
          localObject2 = new Bundle();
          ((Bundle)localObject2).putString("title", t.ʻ(t.ʻ((cya)localObject4), 45));
          ((Bundle)localObject2).putString("summary", t.ʻ(t.ʼ((cya)localObject4), 60));
          ((Bundle)localObject2).putString("imageUrl", "");
          ((Bundle)localObject2).putString("imageLocalUrl", (String)localObject1);
          ((Bundle)localObject2).putInt("req_type", 1);
          ((Bundle)localObject2).putString("targetUrl", ((cyg)localObject4).ʽ());
          if (!TextUtils.isEmpty(paramShareContent)) {
            ((Bundle)localObject2).putString("error", paramShareContent);
          }
          paramShareContent = (ShareContent)localObject2;
          break;
          label954:
          paramShareContent = czc.ʼʼ;
        }
      }
      paramShareContent = new Bundle();
      paramShareContent.putString("summary", ((cyb)localObject4).ʽ);
      paramShareContent.putInt("req_type", 1);
      paramShareContent.putString("error", czc.ʼ("text"));
      break label262;
      label1009:
      paramShareContent.putInt("cflag", 1);
    }
    label1020:
    if ((this.ˏ.get() != null) && (!((Activity)this.ˏ.get()).isFinishing()))
    {
      localObject2 = this.ʿ;
      paramUMShareListener = (Activity)this.ˏ.get();
      localObject1 = this.ˈ;
      localObject2 = new cxe(((cws)localObject2).ʼ.ʼ);
      if (!cxd.ʻ(paramUMShareListener, "4.5.0")) {
        break label1118;
      }
      ((cxb)localObject1).ʻ(new cxj(-6, "低版本手Q不支持该项功能!", null));
    }
    for (;;)
    {
      return true;
      label1118:
      if (cxd.ʻ(paramUMShareListener)) {
        if (!TextUtils.isEmpty(paramShareContent.getString("imageUrl"))) {
          ((cxb)localObject1).ʻ(new cxj(-1, "分享类型解析出问题，不能有url图片，请联系技术人员", ""));
        } else {
          ((cxe)localObject2).ʻ(paramUMShareListener, paramShareContent, (cxb)localObject1);
        }
      }
    }
  }
  
  public final void ʼ(UMAuthListener paramUMAuthListener)
  {
    this.ʾ = paramUMAuthListener;
    if (this.ʿ == null) {
      cxu.ʻ(new l(this, paramUMAuthListener));
    }
    if (ʾ())
    {
      if ((this.ˏ.get() != null) && (!((Activity)this.ˏ.get()).isFinishing()))
      {
        Object localObject = this.ʿ;
        paramUMAuthListener = (Activity)this.ˏ.get();
        cxb localCxb = ʿ(this.ʾ);
        localObject = ((cws)localObject).ʼ;
        String str = paramUMAuthListener.getApplicationContext().getPackageName();
        Iterator localIterator = paramUMAuthListener.getPackageManager().getInstalledApplications(128).iterator();
        while ((localIterator.hasNext()) && (!str.equals(((ApplicationInfo)localIterator.next()).packageName))) {}
        com.wecut.lolicam.cxa.ˊ = false;
        localObject = ((cxc)localObject).ʻ;
        ((cwu)localObject).ʻ = "all";
        ((cwu)localObject).ʼ = new WeakReference(paramUMAuthListener);
        ((cwu)localObject).ʽ = localCxb;
        if (!((cwu)localObject).ʻ(paramUMAuthListener))
        {
          ((cwu)localObject).ʽ = new cwv((cwu)localObject, ((cwu)localObject).ʽ);
          cwu.ʻ();
        }
      }
      return;
    }
    if (Config.isJumptoAppStore)
    {
      paramUMAuthListener = new Intent("android.intent.action.VIEW");
      paramUMAuthListener.setData(Uri.parse("http://log.umsns.com/link/qq/download/"));
      ((Activity)this.ˏ.get()).startActivity(paramUMAuthListener);
    }
    cxu.ʻ(new m(this));
  }
  
  public final void ʽ(UMAuthListener paramUMAuthListener)
  {
    if ((this.ˉ.ʻ()) && (!ˎ().isNeedAuthOnGetUserInfo()))
    {
      ˆ(paramUMAuthListener);
      return;
    }
    ʼ(new r(this, paramUMAuthListener));
  }
  
  public final boolean ʽ()
  {
    return this.ʾ != null;
  }
  
  public final void ʾ(UMAuthListener paramUMAuthListener)
  {
    this.ʿ.ʻ();
    if (this.ˉ != null)
    {
      s localS = this.ˉ;
      localS.ˆ.edit().clear().commit();
      localS.ʻ = null;
      s.ʼ = 0L;
      localS.ʽ = null;
    }
    cxu.ʻ(new d(this, paramUMAuthListener));
  }
  
  public final boolean ʾ()
  {
    return (this.ʿ != null) && (cws.ʻ((Activity)this.ˏ.get()));
  }
  
  public final boolean ʿ()
  {
    return true;
  }
  
  public final int ˆ()
  {
    return 10103;
  }
  
  public final void ˈ()
  {
    if (this.ʿ != null)
    {
      cws localCws = this.ʿ;
      cws.ʻ = null;
      localCws.ʼ = null;
    }
    this.ʿ = null;
    this.ʾ = null;
  }
}
