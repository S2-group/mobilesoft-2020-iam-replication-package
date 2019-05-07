package com.adt.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
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

public class u
{
  private static int a = 0;
  private static int c = 1;
  private static final u e = new u();
  private String b = "";
  
  static
  {
    int i = c;
    i = (i & 0x35) + (i | 0x35);
    a = i % 128;
  }
  
  private u() {}
  
  private boolean b(Context paramContext, String paramString)
  {
    int i = a;
    int j = i ^ 0x6B;
    i = ((i & 0x6B | j) << 1) - j;
    c = i % 128;
    if (i % 2 == 0) {
      i = 18;
    } else {
      i = 85;
    }
    if (i != 85) {
      return paramContext.getSharedPreferences("titles", 0).getBoolean(paramString, true);
    }
    return paramContext.getSharedPreferences("titles", 0).getBoolean(paramString, false);
  }
  
  public static u c()
  {
    int i = c;
    i = (i ^ 0x36) + ((i & 0x36) << 1) - 1;
    a = i % 128;
    if (i % 2 != 0) {
      i = 9;
    } else {
      i = 94;
    }
    u localU;
    if (i != 94)
    {
      localU = e;
      i = null.length;
    }
    else
    {
      localU = e;
    }
    i = a;
    i = (i & 0xB) + (i | 0xB);
    c = i % 128;
    return localU;
  }
  
  private String c(String paramString)
  {
    int j = c;
    int i = j ^ 0x5D;
    j = (j & 0x5D | i) << 1;
    i = -i;
    i = (j ^ i) + ((j & i) << 1);
    a = i % 128;
    String str = "";
    try
    {
      Object localObject = Pattern.compile("referrer=([^&]*)", 2).matcher(paramString);
      boolean bool = ((Matcher)localObject).find();
      j = 0;
      if (bool) {
        i = 0;
      } else {
        i = 1;
      }
      paramString = str;
      if (i != 1)
      {
        int k = a;
        i = (k ^ 0x29 | k & 0x29) << 1;
        k = -((k ^ 0xFFFFFFFF) & 0x29 | k & 0xFFFFFFD6);
        k = (i & k) + (k | i);
        c = k % 128;
        i = j;
        if (k % 2 == 0) {
          i = 1;
        }
        if (i != 1) {}
        for (paramString = ((Matcher)localObject).group(1);; paramString = ((Matcher)localObject).group(1)) {
          try
          {
            str = URLDecoder.decode(paramString);
            paramString = str;
          }
          catch (Exception localException1)
          {
            localObject = localException1;
            break label235;
          }
        }
      }
      i = c;
      i = (i ^ 0x26) + ((i & 0x26) << 1);
      i = ((i | 0xFFFFFFFF) << 1) - (i ^ 0xFFFFFFFF);
      a = i % 128;
    }
    catch (Exception localException2)
    {
      paramString = localException1;
      label235:
      t.c("get ref error", localException2);
    }
    i = c;
    i = ((i | 0x75) << 1) - (i ^ 0x75);
    a = i % 128;
    return paramString;
  }
  
  private void c(final Context paramContext, String paramString, final int paramInt)
  {
    try
    {
      paramString = NetworkInfo.buildGetInfo(paramString, null, null);
      NetworkExecutor.getInstance().doNetRequest(paramContext, paramString, new NetCallback()
      {
        private static int a = 0;
        private static int e = 1;
        
        public void onFail(NetworkInfo paramAnonymousNetworkInfo, Exception paramAnonymousException)
        {
          int i = e;
          int j = i & 0x17;
          i = j + (i ^ 0x17 | j);
          a = i % 128;
          if (i % 2 != 0) {
            i = 94;
          } else {
            i = 17;
          }
          if (i != 94) {
            return;
          }
          i = null.length;
        }
        
        public void onResp(final NetworkInfo paramAnonymousNetworkInfo)
        {
          int i = e;
          i = (i & 0x49) + (i | 0x49);
          a = i % 128;
          if (paramAnonymousNetworkInfo.getResponseCode() == 200)
          {
            String str1 = new String(paramAnonymousNetworkInfo.getRespBody(), Constants.UTF_8);
            if (TextUtils.isEmpty(str1)) {
              i = 1;
            } else {
              i = 0;
            }
            if (i != 1)
            {
              paramAnonymousNetworkInfo = str1.trim().split("\t");
              if (paramAnonymousNetworkInfo.length <= 6)
              {
                paramAnonymousNetworkInfo = new StringBuilder();
                paramAnonymousNetworkInfo.append("error bodyinfo:");
                paramAnonymousNetworkInfo.append(str1);
                t.d(paramAnonymousNetworkInfo.toString());
                j = e;
                i = j ^ 0x77;
                j = --((j & 0x77) << 1);
                i = ((i | j) << 1) - (j ^ i);
                a = i % 128;
                if (i % 2 != 0) {
                  i = 1;
                } else {
                  i = 0;
                }
                if (i != 1) {
                  return;
                }
              }
              String str2;
              Object localObject1;
              Object localObject2;
              for (i = 71;; i = 34)
              {
                i /= 0;
                return;
                str1 = paramAnonymousNetworkInfo[0];
                str2 = dp.d(paramContext).d("gaid");
                localObject1 = paramAnonymousNetworkInfo[2];
                localObject2 = paramAnonymousNetworkInfo[4];
                paramAnonymousNetworkInfo = paramAnonymousNetworkInfo[6].replace("\n", "").trim();
                if (u.a(u.this, paramContext, paramAnonymousNetworkInfo)) {
                  i = 43;
                } else {
                  i = 50;
                }
                if (i != 43) {
                  break;
                }
                i = a;
                j = i & 0x35;
                i = ((i | 0x35) & (j ^ 0xFFFFFFFF)) + (j << 1);
                e = i % 128;
                if (i % 2 == 0) {
                  i = 43;
                } else {
                  i = 44;
                }
                if (i != 43 ? paramInt != 1 : paramInt != 0) {
                  break;
                }
                i = a + 10;
                i = (i & 0xFFFFFFFF) + (i | 0xFFFFFFFF);
                e = i % 128;
                if (i % 2 == 0) {
                  i = 51;
                } else {
                  i = 38;
                }
                if (i != 51) {
                  return;
                }
              }
              cg.e().c(paramContext, "task", new dj()
              {
                private static int a = 0;
                private static int e = 1;
                
                public void c(Context paramAnonymous2Context, String paramAnonymous2String, Object... paramAnonymous2VarArgs)
                {
                  int i = e;
                  i = (i & 0x19) + (i | 0x19);
                  a = i % 128;
                  if (i % 2 != 0) {
                    i = 36;
                  } else {
                    i = 15;
                  }
                  if (i != 15 ? paramAnonymous2VarArgs.length < 5 : paramAnonymous2VarArgs.length < 3)
                  {
                    i = e;
                    i = ((i ^ 0x5B | i & 0x5B) << 1) - (-((i ^ 0xFFFFFFFF) & 0x5B | i & 0xFFFFFFA4) ^ 0xFFFFFFFF) - 1;
                    a = i % 128;
                    return;
                  }
                  int j = 0;
                  for (;;)
                  {
                    try
                    {
                      paramAnonymous2String = (URI)paramAnonymous2VarArgs[1];
                      boolean bool = ((Boolean)paramAnonymous2VarArgs[0]).booleanValue();
                      paramAnonymous2VarArgs = new StringBuilder();
                      paramAnonymous2VarArgs.append("cast click ok:");
                      paramAnonymous2VarArgs.append(bool);
                      t.d(paramAnonymous2VarArgs.toString());
                      if (bool) {
                        i = 78;
                      } else {
                        i = 73;
                      }
                      if (i != 73)
                      {
                        i = a;
                        i = ((i | 0x33) << 1) - (i ^ 0x33);
                        e = i % 128;
                      }
                      switch (u.2.this.d)
                      {
                      case 2: 
                        t.d("do at cast");
                        i = a;
                        i = (i & 0x1) - ((i | 0x1) ^ 0xFFFFFFFF) - 1;
                        e = i % 128;
                        break;
                      case 1: 
                        paramAnonymous2String = u.e(u.this, paramAnonymous2String.toASCIIString());
                        u.d(u.this, paramAnonymous2Context, paramAnonymousNetworkInfo, paramAnonymous2String);
                        i = e;
                        i = (i & 0x44) + (i | 0x44);
                        i = ((i | 0xFFFFFFFF) << 1) - (i ^ 0xFFFFFFFF);
                        a = i % 128;
                        if (i % 2 == 0) {}
                        k = a;
                        i = k & 0x13;
                        k = k ^ 0x13 | i;
                        i = (i ^ k) + ((k & i) << 1);
                        e = i % 128;
                      }
                    }
                    catch (Throwable paramAnonymous2Context)
                    {
                      t.c("cast error", paramAnonymous2Context);
                    }
                    int k = a;
                    i = (k ^ 0x6F | k & 0x6F) << 1;
                    k = -((k ^ 0xFFFFFFFF) & 0x6F | k & 0xFFFFFF90);
                    k = ((i | k) << 1) - (k ^ i);
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
              }, new Object[] { str1, str2, localObject1, localObject2 });
              i = e;
              i = ((i | 0x62) << 1) - (i ^ 0x62);
              i = (i ^ 0xFFFFFFFF) + ((i & 0xFFFFFFFF) << 1);
              a = i % 128;
            }
            else
            {
              j = e;
              i = j & 0x57;
              j = (j | 0x57) & (i ^ 0xFFFFFFFF);
              i <<= 1;
              i = (j ^ i) + ((j & i) << 1);
              a = i % 128;
              if (i % 2 != 0) {
                i = 82;
              } else {
                i = 66;
              }
              if (i != 82)
              {
                t.d("empty body");
                return;
              }
              t.d("empty body");
              i = null.length;
              return;
            }
          }
          int j = e;
          i = j & 0xFFFFFFB0 | (j ^ 0xFFFFFFFF) & 0x4F;
          j = --((j & 0x4F) << 1);
          i = (i ^ j) + ((j & i) << 1);
          a = i % 128;
        }
      });
      paramInt = c + 8;
      paramInt = ((paramInt | 0xFFFFFFFF) << 1) - (paramInt ^ 0xFFFFFFFF);
      a = paramInt % 128;
    }
    catch (Throwable paramContext)
    {
      t.c("pcheck error", paramContext);
    }
    paramInt = a + 107;
    c = paramInt % 128;
  }
  
  private void c(Context paramContext, String paramString1, String paramString2)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("save click:");
    localStringBuilder.append(paramString1);
    t.d(localStringBuilder.toString());
    paramContext = paramContext.getSharedPreferences("titles", 0).edit();
    paramContext.putBoolean(paramString1, true);
    localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString1);
    localStringBuilder.append("_clickRef");
    paramContext.putString(localStringBuilder.toString(), paramString2);
    paramContext.apply();
    int j = a;
    int i = j & 0xFFFFFF9E | (j ^ 0xFFFFFFFF) & 0x61;
    j = (j & 0x61) << 1;
    i = (i & j) + (j | i);
    c = i % 128;
  }
  
  private boolean d(Context paramContext, String paramString)
  {
    int i = a;
    int j = i & 0x67;
    i = j + (i ^ 0x67 | j);
    c = i % 128;
    paramContext = paramContext.getPackageManager().getInstalledPackages(0).iterator();
    j = c;
    i = j & 0x1;
    j = j ^ 0x1 | i;
    i = (i & j) + (j | i);
    a = i % 128;
    for (;;)
    {
      if (paramContext.hasNext()) {
        i = 1;
      } else {
        i = 0;
      }
      if (i != 1)
      {
        i = a;
        i = (i ^ 0x58) + ((i & 0x58) << 1) - 0 - 1;
        c = i % 128;
        return false;
      }
      i = a + 68;
      i = ((i | 0xFFFFFFFF) << 1) - (i ^ 0xFFFFFFFF);
      c = i % 128;
      if (i % 2 == 0) {
        i = 56;
      } else {
        i = 15;
      }
      if (i != 56)
      {
        if (TextUtils.equals(paramString, ((PackageInfo)paramContext.next()).packageName)) {
          i = 42;
        } else {
          i = 0;
        }
        if (i == 42) {
          break;
        }
      }
      else
      {
        boolean bool = TextUtils.equals(paramString, ((PackageInfo)paramContext.next()).packageName);
        i = 55 / 0;
        if (bool) {
          i = 1;
        } else {
          i = 0;
        }
        if (i == 1) {
          break;
        }
      }
      j = c;
      i = j & 0xFFFFFFCA | (j ^ 0xFFFFFFFF) & 0x35;
      j = (j & 0x35) << 1;
      i = (i & j) + (j | i);
      a = i % 128;
      if (i % 2 != 0) {}
    }
    i = c;
    j = i & 0x6D;
    i = ((i | 0x6D) & (j ^ 0xFFFFFFFF)) + (j << 1);
    a = i % 128;
    return i % 2 != 0;
  }
  
  public void a(Context paramContext, String paramString)
  {
    Object localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append("checkByPackageName:");
    ((StringBuilder)localObject1).append(paramString);
    t.d(((StringBuilder)localObject1).toString());
    boolean bool = b(paramContext, paramString);
    int j = 0;
    if (bool)
    {
      i = a;
      j = i ^ 0x5D;
      i = ((i & 0x5D | j) << 1) - j;
      c = i % 128;
      if (i % 2 == 0) {
        i = 1;
      } else {
        i = 0;
      }
      if (i != 1) {
        return;
      }
      i = 79 / 0;
      return;
    }
    String str = AdtAds.getAppKey(paramContext);
    if (TextUtils.isEmpty(str)) {
      i = 1;
    } else {
      i = 0;
    }
    if (i != 0)
    {
      i = a;
      i = (i & 0x2D) + (i | 0x2D);
      c = i % 128;
      t.d("empty appKey");
      i = a;
      int k = i & 0x67;
      k += (i ^ 0x67 | k);
      c = k % 128;
      i = j;
      if (k % 2 == 0) {
        i = 1;
      }
      if (i != 1) {
        return;
      }
      throw new NullPointerException();
    }
    Object localObject2 = dp.d(paramContext).d("gaid");
    if (TextUtils.isEmpty((CharSequence)localObject2)) {
      i = 51;
    } else {
      i = 13;
    }
    if (i != 13)
    {
      i = c;
      j = i & 0x5B;
      i = j + (i ^ 0x5B | j);
      a = i % 128;
      if (i % 2 != 0) {
        i = 76;
      } else {
        i = 88;
      }
      if (i != 76)
      {
        t.d("empty gaid");
        return;
      }
      t.d("empty gaid");
      i = null.length;
      return;
    }
    localObject1 = AdConfigHelper.getHost(paramContext, bw.c);
    if (TextUtils.isEmpty((CharSequence)localObject1))
    {
      i = a;
      i = ((i | 0xA) << 1) - (i ^ 0xA);
      i = ((i | 0xFFFFFFFF) << 1) - (i ^ 0xFFFFFFFF);
      c = i % 128;
      if (i % 2 == 0) {
        i = 42;
      } else {
        i = 12;
      }
      if (i != 12)
      {
        i = null.length;
        return;
      }
      return;
    }
    localObject2 = new do().a("p", paramString).a("d", localObject2).a("k", str).a("v", "1").a("mv", Integer.valueOf(130)).a("make", Build.MANUFACTURER).a("brand", Build.BRAND).a("model", Build.MODEL).a("sdkv", "4.5.1");
    if (!TextUtils.isEmpty(this.b)) {
      i = 94;
    } else {
      i = 60;
    }
    if (i != 60)
    {
      i = c;
      i = (i ^ 0x42) + ((i & 0x42) << 1);
      i = (i ^ 0xFFFFFFFF) + ((i & 0xFFFFFFFF) << 1);
      a = i % 128;
      ((do)localObject2).a("n", this.b);
      this.b = "";
      i = a;
      j = i & 0x79;
      i = j + (i ^ 0x79 | j);
      c = i % 128;
    }
    paramString = new StringBuilder();
    paramString.append((String)localObject1);
    paramString.append("/pcheck?");
    paramString.append(((do)localObject2).e());
    paramString = paramString.toString();
    localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append("url:");
    ((StringBuilder)localObject1).append(paramString);
    t.d(((StringBuilder)localObject1).toString());
    c(paramContext, paramString, 2);
    int i = a;
    j = i & 0x79;
    i = ((i | 0x79) & (j ^ 0xFFFFFFFF)) - (--(j << 1) ^ 0xFFFFFFFF) - 1;
    c = i % 128;
  }
  
  void e(Context paramContext, String paramString)
  {
    int i = c;
    i = (i & 0x55) + (i | 0x55);
    a = i % 128;
    int j = 0;
    if (i % 2 != 0) {
      i = 0;
    } else {
      i = 1;
    }
    if (i != 0)
    {
      this.b = paramString;
      localObject2 = dp.d(paramContext);
      localObject1 = AdtAds.getAppKey(paramContext);
      if (TextUtils.isEmpty((CharSequence)localObject1)) {
        i = 19;
      } else {
        i = 71;
      }
      if (i != 19) {
        break label203;
      }
    }
    else
    {
      this.b = paramString;
      localObject2 = dp.d(paramContext);
      localObject1 = AdtAds.getAppKey(paramContext);
      boolean bool = TextUtils.isEmpty((CharSequence)localObject1);
      i = 2 / 0;
      if (bool) {
        i = 29;
      } else {
        i = 13;
      }
      if (i == 13) {
        break label203;
      }
    }
    j = c;
    i = j & 0x31;
    j = j ^ 0x31 | i;
    i = (i ^ j) + ((j & i) << 1);
    a = i % 128;
    t.d("empty appKey");
    i = a;
    i = (i ^ 0x27) + ((i & 0x27) << 1);
    c = i % 128;
    return;
    label203:
    String str2 = ((dp)localObject2).d("gaid");
    if (TextUtils.isEmpty(str2)) {
      i = 58;
    } else {
      i = 41;
    }
    if (i != 41)
    {
      j = c;
      i = j & 0x33;
      j = --(j ^ 0x33 | i);
      i = (i & j) + (j | i);
      a = i % 128;
      t.d("empty gaid");
      i = a;
      i = ((i | 0x5E) << 1) - (i ^ 0x5E);
      i = (i & 0xFFFFFFFF) + (i | 0xFFFFFFFF);
      c = i % 128;
      return;
    }
    String str1 = AdConfigHelper.getHost(paramContext, bw.c);
    if (TextUtils.isEmpty(str1))
    {
      int k = c;
      i = k & 0x43;
      k = --(k ^ 0x43 | i);
      i = (i & k) + (k | i);
      a = i % 128;
      if (i % 2 != 0) {
        i = j;
      } else {
        i = 1;
      }
      if (i != 0) {
        return;
      }
      i = null.length;
      return;
    }
    Object localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append(str1);
    ((StringBuilder)localObject2).append("/pcheck?");
    ((StringBuilder)localObject2).append(new do().a("n", paramString).a("d", str2).a("v", "1").a("k", localObject1).a("mv", Integer.valueOf(130)).a("sdkv", "4.5.1").a("make", Build.MANUFACTURER).a("brand", Build.BRAND).a("model", Build.MODEL).e());
    paramString = ((StringBuilder)localObject2).toString();
    Object localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append("url:");
    ((StringBuilder)localObject1).append(paramString);
    t.d(((StringBuilder)localObject1).toString());
    c(paramContext, paramString, 1);
    i = c + 8;
    i = (i ^ 0xFFFFFFFF) + ((i & 0xFFFFFFFF) << 1);
    a = i % 128;
    if (i % 2 != 0) {
      i = 45;
    } else {
      i = 50;
    }
    if (i != 50) {
      throw new NullPointerException();
    }
  }
}
