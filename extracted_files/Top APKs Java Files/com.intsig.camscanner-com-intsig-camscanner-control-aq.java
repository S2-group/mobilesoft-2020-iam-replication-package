package com.intsig.camscanner.control;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.os.Build;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.intsig.camscanner.RecommendToFriendsQRCodeActivity;
import com.intsig.nativelib.QREngine;
import com.intsig.q.c;
import com.intsig.q.e;
import com.intsig.tsapp.sync.av;
import com.intsig.util.r;
import com.intsig.v.b;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class aq
  extends al
{
  private boolean c;
  private String d;
  
  public aq(Activity paramActivity, String paramString, boolean paramBoolean)
  {
    super(paramActivity);
    this.c = paramBoolean;
    this.d = paramString;
  }
  
  private void b(ActivityInfo paramActivityInfo)
  {
    String str = paramActivityInfo.packageName;
    paramActivityInfo = paramActivityInfo.name;
    int i = str.hashCode();
    if (i != -973170826)
    {
      if (i != -951532658)
      {
        if (i != 361910168)
        {
          if ((i == 1536737232) && (str.equals("com.sina.weibo")))
          {
            i = 1;
            break label100;
          }
        }
        else if (str.equals("com.tencent.mobileqq"))
        {
          i = 2;
          break label100;
        }
      }
      else if (str.equals("qrcode"))
      {
        i = 3;
        break label100;
      }
    }
    else if (str.equals("com.tencent.mm"))
    {
      i = 0;
      break label100;
    }
    i = -1;
    switch (i)
    {
    default: 
      c.b("CSReferearn", "invitenewchina_more");
      return;
    case 3: 
      c.b("CSReferearn", "invitenewchina_qrcode");
      return;
    case 2: 
      c.b("CSReferearn", "invitenewchina_qq");
      return;
    case 1: 
      label100:
      c.b("CSReferearn", "invitenewchina_weibo");
      return;
    }
    if (TextUtils.equals("com.tencent.mm.ui.tools.ShareImgUI", paramActivityInfo))
    {
      c.b("CSReferearn", "invitenewchina_wechat");
      return;
    }
    if (TextUtils.equals("com.tencent.mm.ui.tools.ShareToTimeLineUI", paramActivityInfo)) {
      c.b("CSReferearn", "invitenewchina_moment");
    }
  }
  
  private void b(String paramString)
  {
    ax.a(this.a, paramString);
  }
  
  private ResolveInfo f()
  {
    ResolveInfo localResolveInfo = new ResolveInfo();
    ActivityInfo localActivityInfo = new ActivityInfo();
    localActivityInfo.packageName = "qrcode";
    localActivityInfo.name = "null";
    localActivityInfo.icon = 2131231389;
    localActivityInfo.labelRes = 2131690445;
    localResolveInfo.activityInfo = localActivityInfo;
    return localResolveInfo;
  }
  
  private ResolveInfo g()
  {
    ResolveInfo localResolveInfo = new ResolveInfo();
    ActivityInfo localActivityInfo = new ActivityInfo();
    localActivityInfo.packageName = "galley";
    localActivityInfo.name = "null";
    localActivityInfo.icon = 2131230967;
    localActivityInfo.labelRes = 2131690436;
    localResolveInfo.activityInfo = localActivityInfo;
    return localResolveInfo;
  }
  
  private void h()
  {
    Intent localIntent = new Intent(this.a, RecommendToFriendsQRCodeActivity.class);
    localIntent.putExtra("extra_url", this.d);
    this.a.startActivity(localIntent);
  }
  
  public String a(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString);
    localStringBuilder.append(av.U(this.a));
    localStringBuilder.append("_cn.jpg");
    return localStringBuilder.toString();
  }
  
  public void a(int paramInt, List<ResolveInfo> paramList)
  {
    if (!this.c)
    {
      paramList.add(paramInt, f());
      return;
    }
    paramList.add(paramInt, g());
  }
  
  public void a(ActivityInfo paramActivityInfo)
  {
    if (paramActivityInfo != null)
    {
      String str1 = paramActivityInfo.packageName;
      String str2 = paramActivityInfo.name;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("go2Share packageName>>>");
      localStringBuilder.append(str1);
      localStringBuilder.append(",className>>>");
      localStringBuilder.append(str2);
      e.b("RecommendToFriendsControlCN", localStringBuilder.toString());
      b(paramActivityInfo);
      if ((TextUtils.equals("com.tencent.mm", str1)) && (TextUtils.equals("com.tencent.mm.ui.tools.ShareToTimeLineUI", str2)))
      {
        a(new ar(this, str1, str2));
        e();
        return;
      }
      if ((TextUtils.equals("com.sina.weibo", str1)) && (TextUtils.equals("com.sina.weibo.composerinde.ComposerDispatchActivity", str2)))
      {
        a(new as(this, str1, str2));
        e();
        return;
      }
      if (TextUtils.equals("qrcode", str1))
      {
        h();
        return;
      }
      if (TextUtils.equals("galley", str1))
      {
        a(new at(this));
        e();
        return;
      }
      paramActivityInfo = r.ax();
      if (TextUtils.equals("com.tencent.mobileqq", str1)) {
        paramActivityInfo = r.az();
      }
      a(str1, str2, paramActivityInfo);
    }
  }
  
  public void a(List<ResolveInfo> paramList)
  {
    b.a(this.a, paramList);
    PackageManager localPackageManager = this.a.getPackageManager();
    List localList = localPackageManager.getInstalledPackages(0);
    if ((localList != null) && (localList.size() > 0))
    {
      int i = 0;
      while (i < localList.size())
      {
        String str = ((PackageInfo)localList.get(i)).packageName;
        if ("com.sina.weibo".equals(str))
        {
          ResolveInfo localResolveInfo = new ResolveInfo();
          ActivityInfo localActivityInfo = new ActivityInfo();
          localActivityInfo.packageName = str;
          localActivityInfo.name = "com.sina.weibo.composerinde.ComposerDispatchActivity";
          try
          {
            if ("xiaomi".equals(Build.BRAND.toLowerCase()))
            {
              localActivityInfo.icon = 2131231408;
              localActivityInfo.labelRes = 2131689624;
            }
            else
            {
              localActivityInfo.icon = localPackageManager.getApplicationInfo(str, 0).icon;
              localActivityInfo.labelRes = localPackageManager.getApplicationInfo(str, 0).labelRes;
            }
          }
          catch (PackageManager.NameNotFoundException localNameNotFoundException)
          {
            e.b("RecommendToFriendsControlCN", localNameNotFoundException);
            localActivityInfo.icon = 2131231408;
            localActivityInfo.labelRes = 2131689624;
          }
          localResolveInfo.activityInfo = localActivityInfo;
          paramList.add(localResolveInfo);
        }
        i += 1;
      }
    }
  }
  
  public String[] a()
  {
    return new String[] { "com.tencent.mm,com.tencent.mm.ui.tools.ShareImgUI", "com.tencent.mm,com.tencent.mm.ui.tools.ShareToTimeLineUI", "com.tencent.mobileqq,com.tencent.mobileqq.activity.JumpActivity", "com.sina.weibo,com.sina.weibo.composerinde.ComposerDispatchActivity" };
  }
  
  public View b()
  {
    View localView = LayoutInflater.from(this.a).inflate(2131427380, null);
    ((ImageView)localView.findViewById(2131296839)).setImageBitmap(QREngine.encodeToBitmap(this.d));
    TextView localTextView = (TextView)localView.findViewById(2131297541);
    Object localObject = localTextView.getText();
    Matcher localMatcher = Pattern.compile("(\\d+)").matcher((CharSequence)localObject);
    StringBuilder localStringBuilder = new StringBuilder();
    if (localMatcher.find()) {
      localStringBuilder.append(localMatcher.group(1));
    }
    if (!TextUtils.isEmpty(localStringBuilder))
    {
      int i = TextUtils.indexOf((CharSequence)localObject, localStringBuilder);
      if (i != -1)
      {
        localObject = new SpannableString((CharSequence)localObject);
        ((SpannableString)localObject).setSpan(new ForegroundColorSpan(Color.parseColor("#FF19BC9C")), i, localStringBuilder.length() + i, 33);
        localTextView.setText((CharSequence)localObject);
      }
    }
    return localView;
  }
  
  public boolean c()
  {
    if ((!TextUtils.isEmpty(this.d)) && (TextUtils.equals(this.d, r.ak()))) {
      return false;
    }
    r.B(this.d);
    return true;
  }
}
