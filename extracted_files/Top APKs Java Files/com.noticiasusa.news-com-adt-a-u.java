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
  private static int a;
  private static int b;
  private static final u c;
  
  static
  {
    int i = 0;
    a = 0;
    b = 1;
    c = new u();
    int j = a;
    j = ((--26 | j) << 1) - (j ^ --26) - (-1 ^ 0xFFFFFFFF) - 1;
    b = j % 128;
    if (j % 2 == 0) {}
    for (;;)
    {
      switch (i)
      {
      default: 
        return;
      }
      i = 57 / 0;
      return;
      i = 1;
    }
  }
  
  private u() {}
  
  private boolean a(Context paramContext, String paramString)
  {
    int i = b;
    i = ((i | 0x6D) << 1) - (i ^ 0x6D);
    a = i % 128;
    if (i % 2 != 0) {}
    paramContext = paramContext.getPackageManager().getInstalledPackages(0).iterator();
    i = a;
    i = ((i ^ 0xF | i & 0xF) << 1) - ((i ^ 0xFFFFFFFF) & 0xF | i & 0xFFFFFFF0);
    b = i % 128;
    if (i % 2 == 0)
    {
      i = 0;
      switch (i)
      {
      }
      if (paramContext.hasNext())
      {
        i = 86;
        switch (i)
        {
        default: 
          label120:
          i = a + 99;
          b = i % 128;
          if ((i % 2 == 0) && (!TextUtils.equals(paramString, ((PackageInfo)paramContext.next()).packageName))) {
            break;
          }
        }
      }
    }
    for (i = 67;; i = 90)
    {
      int j;
      int k;
      switch (i)
      {
      default: 
        i = b;
        j = --65;
        j = (j ^ 0xFFFFFFFF) & 0xFFFFFFFF | j & 0x0;
        k = -j;
        i = ((i & -j) << 1) + (k ^ i);
        j = -1;
        i = ((i & j) << 1) + (i ^ j);
        a = i % 128;
        if (i % 2 == 0) {
          break;
        }
      }
      for (i = 2;; i = 88) {
        switch (i)
        {
        default: 
          break;
        case 2: 
          i = -4 ^ 0x3;
          i = (-4 & 0x3) << 1;
          break;
          i = a;
          i = ((i | 0x3B) << 1) - (i ^ 0x3B);
          b = i % 128;
          if (i % 2 == 0) {}
          return false;
          i = b + 12 - 1;
          a = i % 128;
          if (i % 2 != 0) {}
          j = b;
          i = (j & 0x6B ^ 0xFFFFFFFF) & (j | 0x6B);
          j = -((j & 0x6B) << 1);
          k = -j;
          i = (-j | i) + (k & i);
          a = i % 128;
          if (i % 2 != 0) {}
          return true;
        }
      }
      i = 12;
      break label120;
      i = 1;
      break;
    }
  }
  
  public static u d()
  {
    int i = b;
    int k = -95;
    int j = -k;
    j &= i;
    k = -k;
    i = i & k | i ^ k;
    i = (i | j) + (j & i);
    a = i % 128;
    if (i % 2 != 0) {}
    u localU = c;
    i = b - (--53 ^ 0xFFFFFFFF) - 1;
    a = i % 128;
    if (i % 2 != 0) {}
    for (i = 69;; i = 70)
    {
      switch (i)
      {
      default: 
        i = null.length;
        return localU;
      }
      return localU;
    }
  }
  
  private String d(String paramString)
  {
    int j = 0;
    int k = a;
    int m = -101;
    int i = -m ^ k;
    m = -m;
    k = -((k & m) << 1);
    m = -k;
    i = (-k | i) + (m & i);
    b = i % 128;
    String str2;
    String str1;
    if (i % 2 == 0)
    {
      i = 1;
      switch (i)
      {
      default: 
        str2 = "";
        str1 = str2;
      }
    }
    for (;;)
    {
      boolean bool;
      try
      {
        Matcher localMatcher = Pattern.compile("referrer=([^&]*)", 2).matcher(paramString);
        str1 = str2;
        bool = localMatcher.find();
        if (!bool) {
          break label442;
        }
        i = j;
        paramString = str2;
        switch (i)
        {
        default: 
          paramString = localMatcher;
          str1 = "";
          i = b;
          i = (i & 0x75) - ((i | 0x75) ^ 0xFFFFFFFF) - 1;
          a = i % 128;
          if (i % 2 != 0) {}
          paramString = paramString.group(1);
          str1 = paramString;
          paramString = URLDecoder.decode(paramString);
          i = a;
          i = ((i | 0x4B) << 1) - (i ^ 0x4B);
          b = i % 128;
          if (i % 2 != 0) {
            break;
          }
        }
        i = b;
        j = -91;
        k = -j;
        i = (k & i) - ((i | -j) ^ 0xFFFFFFFF) - 1;
        a = i % 128;
        if (i % 2 == 0) {}
      }
      catch (Exception paramString)
      {
        w.b("get ref error", paramString);
        paramString = str1;
        continue;
        return paramString;
      }
      j = b;
      i = (j ^ 0x5B | j & 0x5B) << 1;
      j = -((j | 0x5B) & (j & 0x5B ^ 0xFFFFFFFF));
      i = (j | i) + (i & j);
      a = i % 128;
      if (i % 2 != 0)
      {
        i = 20;
        switch (i)
        {
        default: 
          label321:
          i = 24 / 0;
          return paramString;
        }
        str2 = "";
        str1 = str2;
        paramString = Pattern.compile("referrer=([^&]*)", 4).matcher(paramString);
        str1 = str2;
        bool = paramString.find();
        if (!bool) {
          break label431;
        }
      }
      label431:
      for (i = 32;; i = 84)
      {
        switch (i)
        {
        }
        paramString = str2;
        break;
        i = 3;
        break label321;
      }
      i = 0;
      break;
      label442:
      i = 1;
    }
  }
  
  private void e(Context paramContext, String paramString1, String paramString2)
  {
    w.c("save click:" + paramString1);
    paramContext = paramContext.getSharedPreferences("titles", 0).edit();
    paramContext.putBoolean(paramString1, true);
    paramContext.putString(paramString1 + "_clickRef", paramString2);
    paramContext.apply();
    int i = a;
    int k = -67;
    int j = -k;
    k = -k;
    i = ((i & k) << 1) + (j ^ i);
    b = i % 128;
    if (i % 2 == 0) {}
  }
  
  void c(final Context paramContext, String paramString)
  {
    int j = 0;
    int i = a + 81;
    b = i % 128;
    Object localObject;
    String str1;
    if (i % 2 == 0)
    {
      i = 0;
      switch (i)
      {
      default: 
        localObject = ec.e(paramContext);
        str1 = AdtAds.getAppKey(paramContext);
        if (TextUtils.isEmpty(str1))
        {
          i = 97;
          switch (i)
          {
          default: 
            i = a;
            i = ((i ^ 0x21 | i & 0x21) << 1) - ((i | 0x21) & (i & 0x21 ^ 0xFFFFFFFF));
            b = i % 128;
            if (i % 2 != 0) {}
            break;
          }
        }
        break;
      }
    }
    for (i = 50;; i = 71)
    {
      label157:
      label158:
      label161:
      String str2;
      switch (i)
      {
      default: 
        w.c("empty appKey");
        return;
        i = 12;
        switch (i)
        {
        }
        str2 = ((ec)localObject).c("gaid");
        if (TextUtils.isEmpty(str2))
        {
          i = 0;
          switch (i)
          {
          default: 
            label199:
            i = a;
            i = ((i | 0x4B) << 1) - (-((i ^ 0xFFFFFFFF) & 0x4B | i & 0xFFFFFFB4) ^ 0xFFFFFFFF) - 1;
            b = i % 128;
            if (i % 2 == 0) {}
            w.c("empty gaid");
            i = b;
            i = (i & 0x57) - (--(i & 0x57 | i ^ 0x57) ^ 0xFFFFFFFF) - 1;
            a = i % 128;
            if (i % 2 != 0) {}
            return;
          }
          localObject = AdConfigHelper.getHost(paramContext, ca.d);
          if (TextUtils.isEmpty((CharSequence)localObject))
          {
            int k = b;
            j = -83;
            i = -j;
            i = (k | -j) + (i & k);
            a = i % 128;
            if (i % 2 == 0) {
              break label881;
            }
          }
        }
        break;
      }
      label849:
      label860:
      label865:
      label881:
      for (i = 1;; i = 0)
      {
        switch (i)
        {
        default: 
          return;
          str1 = (String)localObject + "/pcheck?" + new ee().a("n", paramString).a("d", str2).a("v", "1").a("k", str1).a("mv", Integer.valueOf(130)).a("sdkv", "4.8.1").a("make", Build.MANUFACTURER).a("brand", Build.BRAND).a("model", Build.MODEL).a();
          w.c("url:" + str1);
          if (TextUtils.isEmpty(paramString))
          {
            i = b;
            i = (i ^ 0x53) - (--((i & 0x53) << 1) ^ 0xFFFFFFFF) - 1;
            a = i % 128;
            if (i % 2 == 0) {
              break label865;
            }
          }
          break;
        }
        for (i = 10;; i = 62)
        {
          switch (i)
          {
          default: 
            return;
          }
          for (;;)
          {
            try
            {
              paramString = NetworkInfo.buildGetInfo(str1, null, null);
              bool = ((String)localObject).endsWith(".adtiming.com");
              if (!bool) {
                break label860;
              }
              i = 33;
              switch (i)
              {
              default: 
                i = b + 3;
                a = i % 128;
                if (i % 2 == 0) {
                  break label849;
                }
                i = j;
                switch (i)
                {
                default: 
                  paramString.setCheckChain(true);
                  i = a + 21;
                  b = i % 128;
                  if (i % 2 != 0) {}
                  break;
                }
                break;
              }
              NetworkExecutor.getInstance().doNetRequest(paramContext, paramString, new NetCallback()
              {
                private static int d = 0;
                private static int e = 1;
                
                public void onFail(NetworkInfo paramAnonymousNetworkInfo, Throwable paramAnonymousThrowable)
                {
                  int i = e;
                  int j = --44;
                  i = (i | j) + (i & j) - (-1 ^ 0xFFFFFFFF) - 1;
                  d = i % 128;
                  if (i % 2 != 0) {}
                  for (i = 44;; i = 99)
                  {
                    switch (i)
                    {
                    default: 
                      return;
                    }
                    i = 41 / 0;
                    return;
                  }
                }
                
                public void onResp(NetworkInfo paramAnonymousNetworkInfo)
                {
                  int j = 0;
                  int k = 0;
                  int m = 0;
                  int n = d - (--4 ^ 0xFFFFFFFF) - 1;
                  int i = -1;
                  i = (n | i) + (n & i);
                  e = i % 128;
                  if ((i % 2 != 0) || (paramAnonymousNetworkInfo.getResponseCode() == 200))
                  {
                    paramAnonymousNetworkInfo = new String(paramAnonymousNetworkInfo.getRespBody(), Constants.UTF_8);
                    if (!TextUtils.isEmpty(paramAnonymousNetworkInfo)) {
                      break label859;
                    }
                    i = 0;
                    switch (i)
                    {
                    default: 
                      Object localObject4 = paramAnonymousNetworkInfo.trim().split("\t");
                      if (localObject4.length <= 6)
                      {
                        w.c("error bodyinfo:" + paramAnonymousNetworkInfo);
                        i = d + 91;
                        e = i % 128;
                        if (i % 2 == 0) {
                          i = 66;
                        }
                      }
                      else
                      {
                        switch (i)
                        {
                        default: 
                          label165:
                          throw new NullPointerException();
                          w.c("empty body");
                          label197:
                          i = d;
                          j = --71;
                          i = i - (-((j ^ 0xFFFFFFFF) & 0xFFFFFFFF | j & 0x0) ^ 0xFFFFFFFF) - 1 - 1;
                          e = i % 128;
                          if (i % 2 == 0)
                          {
                            i = m;
                            switch (i)
                            {
                            default: 
                              label242:
                              i = 57 / 0;
                              return;
                              Object localObject2 = localObject4[0];
                              paramAnonymousNetworkInfo = ec.e(paramContext).c("gaid");
                              Object localObject1 = localObject4[2];
                              Object localObject3 = localObject4[4];
                              final String str = localObject4[6].replace("\n", "").trim();
                              localObject4 = AdConfigHelper.getShellConfig(paramContext);
                              if (localObject4 != null)
                              {
                                i = 0;
                                label330:
                                switch (i)
                                {
                                }
                                while (u.e(u.this, paramContext, str))
                                {
                                  k = e;
                                  m = -49;
                                  i = -m;
                                  m = -m;
                                  i = ((i & k | k ^ i) << 1) - (-((k | m) & (k & m ^ 0xFFFFFFFF)) ^ 0xFFFFFFFF) - 1;
                                  d = i % 128;
                                  if (i % 2 == 0) {
                                    break label864;
                                  }
                                  i = j;
                                  switch (i)
                                  {
                                  default: 
                                    label430:
                                    throw new NullPointerException();
                                    i = d;
                                    m = --97;
                                    m = -((m | 0xFFFFFFFF) & (m & 0xFFFFFFFF ^ 0xFFFFFFFF));
                                    m = ((i | m) << 1) - (i ^ m);
                                    i = -1;
                                    i = ((m & i) << 1) + (m ^ i);
                                    e = i % 128;
                                    if ((i % 2 != 0) || (!bk.d(paramContext, str, ((cf)localObject4).n())))
                                    {
                                      j = e;
                                      k = -55;
                                      i = -k;
                                      i = (j | -k) + (i & j);
                                      d = i % 128;
                                      if (i % 2 == 0) {
                                        break label842;
                                      }
                                      i = 14;
                                      switch (i)
                                      {
                                      default: 
                                        label579:
                                        throw new NullPointerException();
                                      }
                                    }
                                    break;
                                  }
                                }
                                cu.e().a(paramContext, "task", new dv()
                                {
                                  private static int a = 1;
                                  private static int c = 0;
                                  
                                  public void a(Context paramAnonymous2Context, String paramAnonymous2String, Object... paramAnonymous2VarArgs)
                                  {
                                    int j = 4;
                                    int k = 0;
                                    int i = 1;
                                    int n = c;
                                    int i1 = -63;
                                    int m = -i1;
                                    i1 = -i1;
                                    m = ((n & i1) << 1) + ((m | n) & (n & m ^ 0xFFFFFFFF));
                                    a = m % 128;
                                    if ((m % 2 != 0) || (paramAnonymous2VarArgs.length < 3))
                                    {
                                      k = a;
                                      j = --29;
                                      j = k - ((j ^ 0xFFFFFFFF) & 0xFFFFFFFF | j & 0x0);
                                      k = -1;
                                      j = (j | -1) + (k & j);
                                      c = j % 128;
                                      if (j % 2 == 0) {
                                        break label694;
                                      }
                                      switch (i)
                                      {
                                      default: 
                                        label134:
                                        i = null.length;
                                        return;
                                      }
                                    }
                                    for (;;)
                                    {
                                      try
                                      {
                                        URI localURI = (URI)paramAnonymous2VarArgs[1];
                                        boolean bool = ((Boolean)paramAnonymous2VarArgs[0]).booleanValue();
                                        w.c("cast click ok:" + bool);
                                        if (!bool) {
                                          break label700;
                                        }
                                        i = 0;
                                        switch (i)
                                        {
                                        default: 
                                          m = a + 6;
                                          i = -1;
                                          i = (m | -1) + (i & m);
                                          c = i % 128;
                                          if (i % 2 != 0) {}
                                          paramAnonymous2String = AdConfigHelper.getShellConfig(paramAnonymous2Context);
                                          paramAnonymous2VarArgs = u.b(u.this, localURI.toASCIIString());
                                          u.b(u.this, paramAnonymous2Context, str, paramAnonymous2VarArgs);
                                          i = k;
                                          if (paramAnonymous2String == null) {
                                            break label706;
                                          }
                                          i = 1;
                                          break label706;
                                          w.c("cast_at switch is close");
                                          i = a;
                                          k = --23;
                                          k = i - ((k | 0xFFFFFFFF) & (k & 0xFFFFFFFF ^ 0xFFFFFFFF));
                                          i = -1;
                                          i = ((k & -1) << 1) + (i ^ k);
                                          c = i % 128;
                                          if (i % 2 == 0) {
                                            break;
                                          }
                                        }
                                        i = a - (--120 ^ 0xFFFFFFFF) - 1 - (-1 ^ 0xFFFFFFFF) - 1;
                                        c = i % 128;
                                        if (i % 2 == 0) {}
                                      }
                                      catch (Throwable paramAnonymous2Context)
                                      {
                                        w.b("cast error", paramAnonymous2Context);
                                        continue;
                                      }
                                      k = a;
                                      m = -13;
                                      i = -m;
                                      m = -m;
                                      k = (k & m | k ^ m) + (i & k);
                                      c = k % 128;
                                      i = j;
                                      if (k % 2 != 0) {
                                        i = 79;
                                      }
                                      switch (i)
                                      {
                                      default: 
                                        throw new NullPointerException();
                                        i = 4;
                                        switch (i)
                                        {
                                        default: 
                                          if (paramAnonymous2String.g() == 1)
                                          {
                                            w.c("do at cast");
                                            e.c().c(paramAnonymous2Context, "cast", new Object[] { new dv()
                                            {
                                              private static int c = 0;
                                              private static int d = 1;
                                              
                                              public void a(Context paramAnonymous3Context, String paramAnonymous3String, Object... paramAnonymous3VarArgs)
                                              {
                                                int i = c + 38 - (-1 ^ 0xFFFFFFFF) - 1;
                                                d = i % 128;
                                                if (i % 2 == 0) {}
                                                w.c("do cast at success");
                                                int j = d;
                                                i = j & 0x53;
                                                j |= 0x53;
                                                i = ((j & i) << 1) + (i ^ j);
                                                c = i % 128;
                                                if (i % 2 != 0) {}
                                              }
                                            }, str });
                                            i = a;
                                            i = (i & 0x4D) - ((i | 0x4D) ^ 0xFFFFFFFF) - 1;
                                            c = i % 128;
                                            if (i % 2 != 0)
                                            {
                                              continue;
                                              return;
                                            }
                                          }
                                          break;
                                        }
                                      case 4: 
                                        return;
                                        i = c - (--66 ^ 0xFFFFFFFF) - 1 - 1;
                                        a = i % 128;
                                        if (i % 2 == 0)
                                        {
                                          i = 25;
                                          continue;
                                          i = paramAnonymous2String.g();
                                          if (i == 1)
                                          {
                                            continue;
                                            label694:
                                            i = 0;
                                            break label134;
                                            label700:
                                            i = 1;
                                            continue;
                                            label706:
                                            switch (i)
                                            {
                                            }
                                          }
                                        }
                                        break;
                                      }
                                    }
                                  }
                                }, new Object[] { localObject2, paramAnonymousNetworkInfo, localObject1, localObject3 });
                                j = e;
                                i = -(--31 ^ 0xFFFFFFFF);
                                i = ((j | i) << 1) - (j ^ i) - (-1 ^ 0xFFFFFFFF) - 1;
                                d = i % 128;
                                if (i % 2 == 0) {
                                  break label874;
                                }
                              }
                              break;
                            }
                          }
                          break;
                        }
                      }
                      break;
                    }
                  }
                  label842:
                  label859:
                  label864:
                  label874:
                  for (i = k;; i = 1)
                  {
                    switch (i)
                    {
                    }
                    j = e;
                    k = -115;
                    i = -k;
                    k = -k;
                    i = ((i | j) & (j & i ^ 0xFFFFFFFF)) - (--((j & k) << 1) ^ 0xFFFFFFFF) - 1;
                    d = i % 128;
                    if (i % 2 != 0) {}
                    return;
                    return;
                    return;
                    return;
                    return;
                    i = e + 103;
                    d = i % 128;
                    if (i % 2 != 0) {}
                    for (i = 30;; i = 5)
                    {
                      switch (i)
                      {
                      }
                      w.c("empty body");
                      i = 77 / 0;
                      break label197;
                      i = 1;
                      break label330;
                      i = 59;
                      break label579;
                      i = 61;
                      break label165;
                      i = 1;
                      break label242;
                      i = 1;
                      break;
                      i = 1;
                      break label430;
                    }
                  }
                }
              });
              i = b;
              i = ((i | 0x17) << 1) - (i ^ 0x17);
              a = i % 128;
              if (i % 2 == 0) {}
            }
            catch (Throwable paramContext)
            {
              w.b("pcheck error", paramContext);
              continue;
            }
            i = a;
            i = ((i & 0x4B) << 1) + (i ^ 0x4B);
            b = i % 128;
            if (i % 2 == 0) {}
            return;
            w.c("empty appKey");
            i = null.length;
            break label157;
            throw new NullPointerException();
            localObject = ec.e(paramContext);
            str1 = AdtAds.getAppKey(paramContext);
            boolean bool = TextUtils.isEmpty(str1);
            i = 69 / 0;
            if (!bool) {
              break label158;
            }
            i = 95;
            break label161;
            paramString.setCheckChain(true);
            continue;
            i = null.length;
            return;
            i = 1;
            continue;
            i = 57;
            break;
            i = 3;
          }
        }
        i = 1;
        break label199;
        i = 1;
        break;
      }
    }
  }
}
