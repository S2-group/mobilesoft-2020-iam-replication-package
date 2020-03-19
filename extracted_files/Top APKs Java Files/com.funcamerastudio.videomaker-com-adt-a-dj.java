package com.adt.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.aiming.mdt.sdk.AdtAds;
import com.aiming.mdt.sdk.common.network.NetCallback;
import com.aiming.mdt.sdk.common.network.NetworkExecutor;
import com.aiming.mdt.sdk.common.network.NetworkInfo;
import com.aiming.mdt.sdk.shell.AdConfigHelper;
import com.aiming.mdt.sdk.util.Constants;
import java.net.URI;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class dj
{
  private static final dj a = new dj();
  private static int c = 0;
  private static int d = 1;
  private String b = "";
  
  static
  {
    int i = c + 95 - 1;
    i = ((i | 0xFFFFFFFF) << 1) - (i ^ 0xFFFFFFFF);
    d = i % 128;
    if (i % 2 == 0) {
      i = 37;
    } else {
      i = 52;
    }
    if (i != 37) {
      return;
    }
    i = 63 / 0;
  }
  
  private dj() {}
  
  public static dj a()
  {
    int i = c;
    i = (i ^ 0x47) + ((i & 0x47) << 1);
    d = i % 128;
    if (i % 2 == 0) {
      i = 40;
    } else {
      i = 53;
    }
    if (i != 40) {
      return a;
    }
    dj localDj = a;
    i = 57 / 0;
    return localDj;
  }
  
  private String a(String paramString)
  {
    int j = d;
    int i = j & 0x3;
    int k = (j | 0x3) & (i ^ 0xFFFFFFFF);
    j = 1;
    i = --(i << 1);
    i = (k ^ i) + ((k & i) << 1);
    c = i % 128;
    if (i % 2 != 0) {
      i = 93;
    } else {
      i = 75;
    }
    if (i != 93)
    {
      String str1 = "";
      try
      {
        localMatcher = Pattern.compile("referrer=([^&]*)", 2).matcher(paramString);
        bool = localMatcher.find();
        if (bool) {
          i = 0;
        } else {
          i = 1;
        }
        paramString = str1;
        if (i == 1) {
          break label266;
        }
        paramString = localMatcher;
      }
      catch (Exception str1)
      {
        paramString = "";
        localException1 = ???;
        break label300;
      }
    }
    String str2 = "";
    Matcher localMatcher = Pattern.compile("referrer=([^&]*)", 2).matcher(paramString);
    boolean bool = localMatcher.find();
    if (bool) {
      i = 82;
    } else {
      i = 14;
    }
    paramString = str2;
    if (i != 14)
    {
      paramString = localMatcher;
      k = c;
      i = k & 0x7B;
      k = k ^ 0x7B | i;
      i = (i ^ k) + ((k & i) << 1);
      d = i % 128;
      paramString = paramString.group(1);
      try
      {
        str2 = URLDecoder.decode(paramString);
        i = c + 62 - 0 - 1;
        d = i % 128;
        if (i % 2 == 0) {
          paramString = str2;
        } else {
          paramString = str2;
        }
      }
      catch (Exception localException2)
      {
        break label300;
      }
    }
    label266:
    i = d;
    i = (i & 0xFFFFFFE0 | (i ^ 0xFFFFFFFF) & 0x1F) + ((i & 0x1F) << 1);
    c = i % 128;
    break label307;
    label300:
    df.a("get ref error", localException2);
    label307:
    k = d;
    i = k & 0xFFFFFFA8 | (k ^ 0xFFFFFFFF) & 0x57;
    k = (k & 0x57) << 1;
    i = ((i | k) << 1) - (k ^ i);
    c = i % 128;
    if (i % 2 != 0) {
      i = j;
    } else {
      i = 0;
    }
    if (i != 0) {
      i = null.length;
    }
    return paramString;
  }
  
  private void a(final Context paramContext, String paramString, final int paramInt)
  {
    int i = 1;
    try
    {
      paramString = NetworkInfo.buildGetInfo(paramString, null, null);
      NetworkExecutor.getInstance().doNetRequest(paramContext, paramString, new NetCallback()
      {
        private static int d = 0;
        private static int e = 1;
        
        public void onFail(NetworkInfo paramAnonymousNetworkInfo, Exception paramAnonymousException)
        {
          int k = e;
          int j = k & 0x41;
          int i = (k ^ 0x41 | j) << 1;
          j = -((k | 0x41) & (j ^ 0xFFFFFFFF));
          i = (i & j) + (j | i);
          d = i % 128;
          if (i % 2 != 0) {
            i = 50;
          } else {
            i = 76;
          }
          if (i != 50) {
            return;
          }
          throw new NullPointerException();
        }
        
        public void onResp(final NetworkInfo paramAnonymousNetworkInfo)
        {
          int i = e;
          i = (i & 0x39) + (i | 0x39);
          d = i % 128;
          if (paramAnonymousNetworkInfo.getResponseCode() == 200)
          {
            String str1 = new String(paramAnonymousNetworkInfo.getRespBody(), Constants.UTF_8);
            boolean bool = TextUtils.isEmpty(str1);
            j = 0;
            if (bool) {
              i = 0;
            } else {
              i = 1;
            }
            if (i != 0)
            {
              paramAnonymousNetworkInfo = str1.trim().split("\t");
              if (paramAnonymousNetworkInfo.length <= 6)
              {
                paramAnonymousNetworkInfo = new StringBuilder();
                paramAnonymousNetworkInfo.append("error bodyinfo:");
                paramAnonymousNetworkInfo.append(str1);
                df.a(paramAnonymousNetworkInfo.toString());
                i = e;
                i = (i ^ 0x61) + ((i & 0x61) << 1);
                d = i % 128;
                return;
              }
              str1 = paramAnonymousNetworkInfo[0];
              String str2 = cp.a(paramContext).a("gaid");
              Object localObject1 = paramAnonymousNetworkInfo[2];
              Object localObject2 = paramAnonymousNetworkInfo[4];
              paramAnonymousNetworkInfo = paramAnonymousNetworkInfo[6].replace("\n", "").trim();
              if (dj.a(dj.this, paramContext, paramAnonymousNetworkInfo)) {
                i = 65;
              } else {
                i = 45;
              }
              if (i == 65)
              {
                i = e;
                i = (i ^ 0x67) + ((i & 0x67) << 1);
                d = i % 128;
                if (paramInt == 1)
                {
                  i = d;
                  i = (i ^ 0x67) + ((i & 0x67) << 1);
                  e = i % 128;
                  if (i % 2 == 0) {
                    i = 51;
                  } else {
                    i = 12;
                  }
                  if (i != 12) {
                    i = 20 / 0;
                  }
                  return;
                }
              }
              bk.a().a(paramContext, "task", new cn()
              {
                private static int c = 0;
                private static int d = 1;
                
                public void a(Context paramAnonymous2Context, String paramAnonymous2String, Object... paramAnonymous2VarArgs)
                {
                  int i = d;
                  i = ((i | 0x53) << 1) - (i ^ 0x53);
                  c = i % 128;
                  int j = paramAnonymous2VarArgs.length;
                  i = 0;
                  if (j < 3)
                  {
                    j = d;
                    j = ((j ^ 0x3 | j & 0x3) << 1) - (-((j ^ 0xFFFFFFFF) & 0x3 | j & 0xFFFFFFFC) ^ 0xFFFFFFFF) - 1;
                    c = j % 128;
                    if (j % 2 != 0) {
                      i = 17;
                    }
                    if (i != 17) {
                      return;
                    }
                    i = null.length;
                    return;
                  }
                  for (;;)
                  {
                    try
                    {
                      paramAnonymous2String = (URI)paramAnonymous2VarArgs[1];
                      boolean bool = ((Boolean)paramAnonymous2VarArgs[0]).booleanValue();
                      paramAnonymous2VarArgs = new StringBuilder();
                      paramAnonymous2VarArgs.append("cast click ok:");
                      paramAnonymous2VarArgs.append(bool);
                      df.a(paramAnonymous2VarArgs.toString());
                      if (bool) {
                        i = 94;
                      } else {
                        i = 71;
                      }
                      if (i == 94)
                      {
                        i = c;
                        j = i & 0x11;
                        i = j + (i ^ 0x11 | j);
                        d = i % 128;
                        if (i % 2 == 0) {
                          i = 1;
                        } else {
                          i = 0;
                        }
                        if (i != 0)
                        {
                          i = dj.1.this.b;
                          j = 54 / 0;
                        }
                      }
                      switch (i)
                      {
                      case 2: 
                        switch (dj.1.this.b)
                        {
                        case 2: 
                          df.a("do at cast");
                          j = c;
                          i = (j ^ 0x59 | j & 0x59) << 1;
                          j = -((j ^ 0xFFFFFFFF) & 0x59 | j & 0xFFFFFFA6);
                          i = (i & j) + (j | i);
                          d = i % 128;
                        }
                        break;
                      case 1: 
                        paramAnonymous2String = dj.a(dj.this, paramAnonymous2String.toASCIIString());
                        dj.a(dj.this, paramAnonymous2Context, paramAnonymousNetworkInfo, paramAnonymous2String);
                        i = c;
                        i = (i & 0xFFFFFFD0 | (i ^ 0xFFFFFFFF) & 0x2F) - ((i & 0x2F) << 1 ^ 0xFFFFFFFF) - 1;
                        d = i % 128;
                        if (i % 2 == 0) {}
                        i = d;
                        i = (i & 0x3D) - ((i | 0x3D) ^ 0xFFFFFFFF) - 1;
                        c = i % 128;
                      }
                    }
                    catch (Throwable paramAnonymous2Context)
                    {
                      df.a("cast error", paramAnonymous2Context);
                    }
                    i = c + 27 - 1 - 1;
                    d = i % 128;
                    return;
                  }
                }
              }, new Object[] { str1, str2, localObject1, localObject2 });
              j = e;
              i = j & 0x2B;
              j = --(j ^ 0x2B | i);
              i = ((i | j) << 1) - (j ^ i);
              d = i % 128;
              if (i % 2 == 0) {}
            }
            else
            {
              i = d + 71;
              e = i % 128;
              df.a("empty body");
              i = d;
              int k = ((i | 0x69) << 1) - (i ^ 0x69);
              e = k % 128;
              i = j;
              if (k % 2 == 0) {
                i = 1;
              }
              if (i != 0) {
                i = null.length;
              }
              return;
            }
          }
          int j = e;
          i = j & 0x4F;
          j |= 0x4F;
          i = (i & j) + (j | i);
          d = i % 128;
        }
      });
      paramInt = d;
      paramInt = (paramInt ^ 0x59) + ((paramInt & 0x59) << 1);
      c = paramInt % 128;
    }
    catch (Throwable paramContext)
    {
      df.a("pcheck error", paramContext);
    }
    paramInt = c;
    paramInt = (paramInt & 0x14) + (paramInt | 0x14);
    int j = ((paramInt | 0xFFFFFFFF) << 1) - (paramInt ^ 0xFFFFFFFF);
    d = j % 128;
    paramInt = i;
    if (j % 2 == 0) {
      paramInt = 0;
    }
    if (paramInt != 0) {
      return;
    }
    throw new NullPointerException();
  }
  
  private void a(Context paramContext, String paramString1, String paramString2)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("save click:");
    localStringBuilder.append(paramString1);
    df.a(localStringBuilder.toString());
    paramContext = paramContext.getSharedPreferences("titles", 0).edit();
    paramContext.putBoolean(paramString1, true);
    localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString1);
    localStringBuilder.append("_clickRef");
    paramContext.putString(localStringBuilder.toString(), paramString2);
    paramContext.apply();
    int j = d;
    int i = 67;
    j += 67;
    c = j % 128;
    if (j % 2 != 0) {
      i = 32;
    }
    if (i != 32) {
      return;
    }
    i = null.length;
  }
  
  private boolean c(Context paramContext, String paramString)
  {
    int j = c;
    int i = (j | 0x53) << 1;
    j = -(j ^ 0x53);
    i = (i ^ j) + ((j & i) << 1);
    d = i % 128;
    boolean bool = paramContext.getSharedPreferences("titles", 0).getBoolean(paramString, false);
    i = c;
    i = ((i ^ 0x53 | i & 0x53) << 1) - (-((i ^ 0xFFFFFFFF) & 0x53 | i & 0xFFFFFFAC) ^ 0xFFFFFFFF) - 1;
    d = i % 128;
    return bool;
  }
  
  private boolean d(Context paramContext, String paramString)
  {
    int i = c;
    i = (i & 0x6) + (i | 0x6);
    i = (i & 0xFFFFFFFF) + (i | 0xFFFFFFFF);
    d = i % 128;
    if (i % 2 == 0) {
      i = 34;
    } else {
      i = 57;
    }
    boolean bool = false;
    if (i != 57) {}
    for (paramContext = paramContext.getPackageManager().getInstalledPackages(1);; paramContext = paramContext.getPackageManager().getInstalledPackages(0))
    {
      paramContext = paramContext.iterator();
      break;
    }
    i = d;
    i = ((i ^ 0x3D | i & 0x3D) << 1) - (-((i ^ 0xFFFFFFFF) & 0x3D | i & 0xFFFFFFC2) ^ 0xFFFFFFFF) - 1;
    c = i % 128;
    for (;;)
    {
      if (paramContext.hasNext()) {
        i = 0;
      } else {
        i = 1;
      }
      if (i != 0)
      {
        i = d + 62 - 1;
        c = i % 128;
        return false;
      }
      j = d;
      i = j & 0xFFFFFF82 | (j ^ 0xFFFFFFFF) & 0x7D;
      j = --((j & 0x7D) << 1);
      i = (i & j) + (j | i);
      c = i % 128;
      if (TextUtils.equals(paramString, ((PackageInfo)paramContext.next()).packageName)) {
        i = 1;
      } else {
        i = 0;
      }
      if (i == 1) {
        break;
      }
      i = c + 47;
      d = i % 128;
      if (i % 2 == 0) {}
    }
    int j = c;
    int k = j | 0x2B;
    i = k << 1;
    j = -((j & 0x2B ^ 0xFFFFFFFF) & k);
    i = (i & j) + (j | i);
    d = i % 128;
    if (i % 2 == 0) {
      i = 78;
    } else {
      i = 16;
    }
    if (i != 78) {
      bool = true;
    }
    return bool;
  }
  
  void a(Context paramContext, String paramString)
  {
    int i = c;
    i = (i & 0x69) + (i | 0x69);
    d = i % 128;
    if (i % 2 == 0) {
      i = 30;
    } else {
      i = 68;
    }
    int k = 0;
    int m = 1;
    int j = 1;
    if (i != 68)
    {
      this.b = paramString;
      localObject2 = cp.a(paramContext);
      localObject1 = AdtAds.getAppKey(paramContext);
      boolean bool = TextUtils.isEmpty((CharSequence)localObject1);
      i = null.length;
      if (bool) {
        i = 11;
      } else {
        i = 85;
      }
      if (i == 11) {
        break label541;
      }
    }
    else
    {
      this.b = paramString;
      localObject2 = cp.a(paramContext);
      localObject1 = AdtAds.getAppKey(paramContext);
      if (TextUtils.isEmpty((CharSequence)localObject1)) {
        i = 1;
      } else {
        i = 0;
      }
      if (i == 1) {
        break label541;
      }
    }
    Object localObject2 = ((cp)localObject2).a("gaid");
    if (TextUtils.isEmpty((CharSequence)localObject2)) {
      i = 1;
    } else {
      i = 0;
    }
    if (i != 0)
    {
      k = c;
      m = k & 0x35;
      i = (k ^ 0x35 | m) << 1;
      k = -((k | 0x35) & (m ^ 0xFFFFFFFF));
      i = (i & k) + (k | i);
      d = i % 128;
      df.a("empty gaid");
      i = c;
      k = (i & 0x73) + (i | 0x73);
      d = k % 128;
      i = j;
      if (k % 2 == 0) {
        i = 0;
      }
      if (i != 0) {
        return;
      }
      i = 76 / 0;
      return;
    }
    String str = AdConfigHelper.getHost(paramContext, ar.b);
    if (TextUtils.isEmpty(str))
    {
      i = d;
      i = (i ^ 0x1E) + ((i & 0x1E) << 1) - 0 - 1;
      c = i % 128;
      if (i % 2 != 0) {
        i = m;
      } else {
        i = 0;
      }
      if (i != 0)
      {
        i = 0 / 0;
        return;
      }
      return;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(str);
    localStringBuilder.append("/pcheck?");
    localStringBuilder.append(new co().a("n", paramString).a("d", localObject2).a("v", "1").a("k", localObject1).a("mv", Integer.valueOf(130)).a("sdkv", "4.4.3").a());
    paramString = localStringBuilder.toString();
    Object localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append("url:");
    ((StringBuilder)localObject1).append(paramString);
    df.a(((StringBuilder)localObject1).toString());
    a(paramContext, paramString, 1);
    j = c;
    i = j ^ 0x7D;
    j = --((j & 0x7D) << 1);
    i = ((i | j) << 1) - (j ^ i);
    d = i % 128;
    if (i % 2 == 0) {
      i = k;
    } else {
      i = 1;
    }
    if (i != 1)
    {
      i = null.length;
      return;
    }
    return;
    label541:
    df.a("empty appKey");
    i = d;
    i = (i & 0xD) - (--(i | 0xD) ^ 0xFFFFFFFF) - 1;
    c = i % 128;
  }
  
  public void b(Context paramContext, String paramString)
  {
    Object localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append("checkByPackageName:");
    ((StringBuilder)localObject1).append(paramString);
    df.a(((StringBuilder)localObject1).toString());
    if (c(paramContext, paramString))
    {
      j = d;
      i = j & 0x6B;
      j = --(j ^ 0x6B | i);
      i = (i & j) + (j | i);
      c = i % 128;
      return;
    }
    String str = AdtAds.getAppKey(paramContext);
    if (TextUtils.isEmpty(str)) {
      i = 23;
    } else {
      i = 49;
    }
    int j = 0;
    if (i != 23)
    {
      Object localObject2 = cp.a(paramContext).a("gaid");
      if (TextUtils.isEmpty((CharSequence)localObject2)) {
        i = 1;
      } else {
        i = 0;
      }
      if (i != 1)
      {
        localObject1 = AdConfigHelper.getHost(paramContext, ar.b);
        if (TextUtils.isEmpty((CharSequence)localObject1))
        {
          i = c + 53;
          d = i % 128;
          return;
        }
        localObject2 = new co().a("p", paramString).a("d", localObject2).a("k", str).a("v", "1").a("mv", Integer.valueOf(130)).a("sdkv", "4.4.3");
        if (!TextUtils.isEmpty(this.b)) {
          i = 85;
        } else {
          i = 36;
        }
        if (i != 36)
        {
          i = d;
          i = (i & 0xFFFFFFC2 | (i ^ 0xFFFFFFFF) & 0x3D) + ((i & 0x3D) << 1);
          c = i % 128;
          ((co)localObject2).a("n", this.b);
          this.b = "";
          j = d;
          i = j & 0x21;
          j |= 0x21;
          i = (i ^ j) + ((j & i) << 1);
          c = i % 128;
        }
        paramString = new StringBuilder();
        paramString.append((String)localObject1);
        paramString.append("/pcheck?");
        paramString.append(((co)localObject2).a());
        localObject1 = paramString.toString();
        paramString = new StringBuilder();
        paramString.append("url:");
        paramString.append((String)localObject1);
        df.a(paramString.toString());
        a(paramContext, (String)localObject1, 2);
        i = d;
        i = (i ^ 0x16) + ((i & 0x16) << 1);
        i = (i & 0xFFFFFFFF) + (i | 0xFFFFFFFF);
        c = i % 128;
        if (i % 2 != 0) {
          i = 0;
        } else {
          i = 1;
        }
        if (i != 1)
        {
          i = 34 / 0;
          return;
        }
        return;
      }
      i = c + 70 - 1;
      d = i % 128;
      if (i % 2 == 0) {
        i = 28;
      } else {
        i = 79;
      }
      if (i != 79)
      {
        df.a("empty gaid");
        i = 45 / 0;
      }
      else
      {
        df.a("empty gaid");
      }
      j = c;
      i = j & 0xFFFFFFA8 | (j ^ 0xFFFFFFFF) & 0x57;
      j = (j & 0x57) << 1;
      i = (i & j) + (j | i);
      d = i % 128;
      return;
    }
    int k = d;
    int i = k & 0xF;
    k = (k | 0xF) & (i ^ 0xFFFFFFFF);
    i = --(i << 1);
    k = (k & i) + (k | i);
    c = k % 128;
    i = j;
    if (k % 2 != 0) {
      i = 1;
    }
    if (i != 0)
    {
      df.a("empty appKey");
      throw new NullPointerException();
    }
    df.a("empty appKey");
    j = d;
    i = j & 0x5F;
    j = j ^ 0x5F | i;
    i = ((i | j) << 1) - (j ^ i);
    c = i % 128;
  }
}
