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

public class w
{
  private static int b;
  private static final w c;
  private static int d;
  
  static
  {
    int i = 0;
    b = 0;
    d = 1;
    c = new w();
    int j = d;
    j = ((j & 0x57) << 1) + (j ^ 0x57);
    b = j % 128;
    if (j % 2 != 0) {}
    for (;;)
    {
      switch (i)
      {
      default: 
        i = 64 / 0;
        return;
      }
      return;
      i = 1;
    }
  }
  
  private w() {}
  
  private boolean b(Context paramContext, String paramString)
  {
    int i = d;
    int k = -27;
    int j = -k;
    k = -k;
    i = (i & k | i ^ k) + (j & i);
    b = i % 128;
    if (i % 2 != 0) {}
    paramContext = paramContext.getPackageManager().getInstalledPackages(0).iterator();
    i = d;
    k = -85;
    j = -k;
    k = -k;
    i = ((j | i) << 1) - ((i ^ 0xFFFFFFFF) & k | (k ^ 0xFFFFFFFF) & i);
    b = i % 128;
    if (i % 2 != 0) {}
    if (paramContext.hasNext()) {}
    for (i = 1;; i = 0)
    {
      switch (i)
      {
      default: 
        i = d;
        j = -9;
        k = -j;
        k = (k & i | i ^ k) << 1;
        i = -(i ^ -j);
        i = ((k | i) << 1) - (i ^ k);
        b = i % 128;
        if ((i % 2 == 0) || (TextUtils.equals(paramString, ((PackageInfo)paramContext.next()).packageName)))
        {
          i = 17;
          switch (i)
          {
          default: 
            label230:
            i = d;
            j = -117;
            k = -j;
            i = ((i & -j) << 1) + (k ^ i);
            b = i % 128;
            if (i % 2 == 0) {}
            break;
          }
        }
        break;
      }
      for (i = 0;; i = 1)
      {
        switch (i)
        {
        }
        break;
        for (i = 70;; i = 92)
        {
          switch (i)
          {
          }
          return true;
          j = b;
          k = -57;
          i = -k;
          i &= j;
          k = -k;
          j = -(j & k | j ^ k);
          k = -j;
          i = (-j | i) + (k & i);
          d = i % 128;
          if (i % 2 == 0) {}
          return false;
          i = b;
          j = -119;
          k = -j;
          i = (k & i) - (--(i | -j) ^ 0xFFFFFFFF) - 1;
          d = i % 128;
          if (i % 2 != 0) {
            break;
          }
        }
        i = 77;
        break label230;
      }
    }
  }
  
  private void c(final Context paramContext, String paramString, final int paramInt)
  {
    try
    {
      paramString = NetworkInfo.buildGetInfo(paramString, null, null);
      NetworkExecutor.getInstance().doNetRequest(paramContext, paramString, new NetCallback()
      {
        private static int a = 0;
        private static int c = 1;
        
        public void onFail(NetworkInfo paramAnonymousNetworkInfo, Exception paramAnonymousException)
        {
          int j = a;
          int i = (j ^ 0x55 | j & 0x55) << 1;
          j ^= 0x55;
          int k = -j;
          i = (-j | i) + (k & i);
          c = i % 128;
          if (i % 2 == 0) {}
        }
        
        public void onResp(NetworkInfo paramAnonymousNetworkInfo)
        {
          int j = 0;
          int k = 0;
          int m = a;
          int n = -71;
          int i = -n;
          i &= m;
          n = -(m | -n);
          m = -n;
          i = ((-n & i) << 1) + (m ^ i);
          c = i % 128;
          label169:
          Object localObject1;
          label265:
          Object localObject2;
          String str;
          Object localObject3;
          if ((i % 2 != 0) || (paramAnonymousNetworkInfo.getResponseCode() == 200))
          {
            paramAnonymousNetworkInfo = new String(paramAnonymousNetworkInfo.getRespBody(), Constants.UTF_8);
            if (!TextUtils.isEmpty(paramAnonymousNetworkInfo)) {
              break label755;
            }
            i = 20;
            switch (i)
            {
            default: 
              i = c + 55;
              a = i % 128;
              if (i % 2 != 0)
              {
                i = k;
                switch (i)
                {
                default: 
                  p.a("empty body");
                  return;
                }
              }
              break;
            case 18: 
              localObject1 = paramAnonymousNetworkInfo.trim().split("\t");
              if (localObject1.length <= 6)
              {
                p.a("error bodyinfo:" + paramAnonymousNetworkInfo);
                i = a;
                j = -11;
                k = -j;
                i = ((k | i) & (i & k ^ 0xFFFFFFFF)) - (--((i & -j) << 1) ^ 0xFFFFFFFF) - 1;
                c = i % 128;
                if (i % 2 == 0) {
                  i = 15;
                }
              }
              else
              {
                switch (i)
                {
                default: 
                  i = null.length;
                  return;
                  localObject2 = localObject1[0];
                  str = dj.e(paramContext).c("gaid");
                  paramAnonymousNetworkInfo = localObject1[2];
                  localObject3 = localObject1[4];
                  localObject1 = localObject1[6].replace("\n", "").trim();
                  if (!w.b(w.this, paramContext, (String)localObject1)) {}
                  break;
                }
              }
              break;
            }
          }
          for (i = 1;; i = 0)
          {
            switch (i)
            {
            default: 
              k = c;
              m = -77;
              i = (-m | k) << 1;
              k ^= -m;
              m = -k;
              i = ((-k & i) << 1) + (m ^ i);
              a = i % 128;
              if (i % 2 == 0) {
                break label738;
              }
              i = 75;
              switch (i)
              {
              default: 
                if (paramInt != 1) {
                  break;
                }
              case 76: 
                label434:
                do
                {
                  j = c;
                  i = (j ^ 0x3B | j & 0x3B) << 1;
                  j = (j | 0x3B) & (j & 0x3B ^ 0xFFFFFFFF);
                  i = ((-j | i) << 1) - (-j ^ i);
                  a = i % 128;
                  if (i % 2 != 0) {}
                  return;
                } while (paramInt == 1);
              }
              break;
            }
            ce.c().d(paramContext, "task", new di()
            {
              private static int c = 0;
              private static int d = 1;
              
              public void c(Context paramAnonymous2Context, String paramAnonymous2String, Object... paramAnonymous2VarArgs)
              {
                int k = 0;
                int j = 0;
                int i = c;
                i = ((i & 0x4B) << 1) + (i ^ 0x4B);
                d = i % 128;
                if (i % 2 == 0)
                {
                  i = 0;
                  switch (i)
                  {
                  default: 
                    if (paramAnonymous2VarArgs.length < 3)
                    {
                      label70:
                      i = d;
                      k = -69;
                      j = -k;
                      k = -k;
                      i = ((i & k) << 1) + (j & (i ^ 0xFFFFFFFF) | (j ^ 0xFFFFFFFF) & i);
                      c = i % 128;
                      if (i % 2 != 0) {}
                      return;
                    }
                    try
                    {
                      label132:
                      paramAnonymous2String = (URI)paramAnonymous2VarArgs[1];
                      boolean bool = ((Boolean)paramAnonymous2VarArgs[0]).booleanValue();
                      p.a("cast click ok:" + bool);
                      if (!bool) {
                        break label998;
                      }
                      i = 0;
                      switch (i)
                      {
                      default: 
                        label181:
                        label200:
                        j = c;
                        i = (j & 0x71 ^ 0xFFFFFFFF) & (j | 0x71);
                        j = -((j & 0x71) << 1);
                        k = -j;
                        i = (-j | i) + (k & i);
                        d = i % 128;
                        if (i % 2 != 0) {}
                        break;
                      }
                    }
                    catch (Throwable paramAnonymous2Context)
                    {
                      for (;;)
                      {
                        int m;
                        p.d("cast error", paramAnonymous2Context);
                      }
                    }
                    j = d;
                    k = -113;
                    i = -k & j;
                    k = -k;
                    j = -(j & k | j ^ k);
                    k = -j;
                    i = (-j | i) + (k & i);
                    c = i % 128;
                    if (i % 2 != 0) {}
                    return;
                    i = d;
                    m = --10;
                    i = (i | m) + (i & m);
                    m = -1;
                    i = ((i & m) << 1) + (i ^ m);
                    c = i % 128;
                    if (i % 2 != 0) {}
                    paramAnonymous2VarArgs = AdConfigHelper.getShellConfig(paramAnonymous2Context);
                    switch (w.3.this.d)
                    {
                    case 1: 
                      paramAnonymous2String = w.d(w.this, paramAnonymous2String.toASCIIString());
                      w.d(w.this, paramAnonymous2Context, this.e, paramAnonymous2String);
                      if (paramAnonymous2VarArgs != null) {
                        i = k;
                      }
                      break;
                    }
                    break;
                  }
                }
                for (;;)
                {
                  label474:
                  p.a("cast_at switch is close");
                  i = c;
                  j = -3;
                  k = -j;
                  i = ((i & -j) << 1) + (k ^ i);
                  d = i % 128;
                  if (i % 2 != 0) {
                    break label200;
                  }
                  break label200;
                  if (paramAnonymous2VarArgs != null)
                  {
                    i = j;
                    switch (i)
                    {
                    default: 
                      label536:
                      i = c;
                      i = ((i ^ 0x4D | i & 0x4D) << 1) - (i ^ 0x4D);
                      d = i % 128;
                      if (i % 2 == 0)
                      {
                        i = 30;
                        switch (i)
                        {
                        default: 
                          label602:
                          if (paramAnonymous2VarArgs.j() == 0)
                          {
                            label631:
                            p.a("do at cast");
                            b.e().a(paramAnonymous2Context, "cast", new Object[] { new di()
                            {
                              private static int b = 0;
                              private static int c = 1;
                              
                              public void c(Context paramAnonymous3Context, String paramAnonymous3String, Object... paramAnonymous3VarArgs)
                              {
                                int i = c;
                                int k = -91;
                                int j = -k;
                                k = -k;
                                i = (j & i) - ((i & k | i ^ k) ^ 0xFFFFFFFF) - 1;
                                b = i % 128;
                                if (i % 2 != 0) {}
                                for (i = 0;; i = 1)
                                {
                                  switch (i)
                                  {
                                  default: 
                                    p.a("do cast at success");
                                    i = null.length;
                                    return;
                                  }
                                  p.a("do cast at success");
                                  return;
                                }
                              }
                            }, this.e });
                            i = d;
                            j = -89;
                            k = -j;
                            i = (i | -j) + (k & i);
                            c = i % 128;
                            if (i % 2 == 0) {
                              break label991;
                            }
                          }
                          break;
                        }
                      }
                      break;
                    }
                  }
                  label991:
                  for (i = 66;; i = 45)
                  {
                    switch (i)
                    {
                    }
                    break label200;
                    if (paramAnonymous2VarArgs.j() == 1) {
                      break label631;
                    }
                    p.a("cast_at switch is close");
                    i = c;
                    i = (i | 0x13) + (i & 0x13);
                    d = i % 128;
                    if (i % 2 != 0) {
                      break label200;
                    }
                    break label200;
                    i = d;
                    j = -111;
                    k = -j;
                    i = (k & i) - (--(i | -j) ^ 0xFFFFFFFF) - 1;
                    c = i % 128;
                    if ((i % 2 != 0) && (paramAnonymous2VarArgs.j() != 1)) {
                      break label474;
                    }
                    p.a("do at cast");
                    b.e().a(paramAnonymous2Context, "cast", new Object[] { new di()
                    {
                      private static int c = 1;
                      private static int e = 0;
                      
                      public void c(Context paramAnonymous3Context, String paramAnonymous3String, Object... paramAnonymous3VarArgs)
                      {
                        int i = 1;
                        int j = e;
                        int k = --85;
                        j = j - ((k ^ 0xFFFFFFFF) & 0xFFFFFFFF | k & 0x0) - (-1 ^ 0xFFFFFFFF) - 1;
                        c = j % 128;
                        if (j % 2 == 0) {
                          i = 0;
                        }
                        switch (i)
                        {
                        default: 
                          p.a("do cast at success");
                          i = null.length;
                          j = c;
                          i = j ^ 0x37;
                          k = -((j & 0x37) << 1);
                          j = -k;
                          i = (-k | i) + (j & i);
                          e = i % 128;
                          if (i % 2 == 0) {
                            break;
                          }
                        }
                        for (i = 81;; i = 48) {
                          switch (i)
                          {
                          default: 
                            return;
                            p.a("do cast at success");
                            break;
                          case 81: 
                            throw new NullPointerException();
                          }
                        }
                      }
                    }, this.e });
                    j = c;
                    i = (j & 0x5B ^ 0xFFFFFFFF) & (j | 0x5B);
                    j = (j & 0x5B) << 1;
                    i = ((i | j) << 1) - (j ^ i);
                    d = i % 128;
                    if (i % 2 != 0) {
                      break label200;
                    }
                    break label200;
                    if (paramAnonymous2VarArgs.length >= 3) {
                      break label132;
                    }
                    break label70;
                    i = 42;
                    break label602;
                    i = 1;
                    break;
                    i = 1;
                    break label1007;
                    i = 1;
                    break label536;
                  }
                  label998:
                  i = 1;
                  break label181;
                  break label200;
                  label1007:
                  switch (i)
                  {
                  }
                }
              }
            }, new Object[] { localObject2, str, paramAnonymousNetworkInfo, localObject3 });
            k = c;
            m = -83;
            i = -m ^ k;
            m = -m;
            k = -((k & m) << 1);
            m = -k;
            i = ((-k & i) << 1) + (m ^ i);
            a = i % 128;
            if (i % 2 != 0) {}
            for (i = j;; i = 1)
            {
              switch (i)
              {
              }
              i = c;
              i = (i | 0x6D) + (i & 0x6D);
              a = i % 128;
              if (i % 2 != 0) {}
              for (i = 66;; i = 21)
              {
                switch (i)
                {
                default: 
                  return;
                  return;
                }
                throw new NullPointerException();
                p.a("empty body");
                i = 53 / 0;
                break label169;
                i = 1;
                break;
                i = 98;
                break label265;
                label738:
                i = 76;
                break label434;
              }
            }
            label755:
            i = 18;
            break;
          }
        }
      });
      paramInt = d + 85;
      b = paramInt % 128;
      if (paramInt % 2 == 0) {
        break label145;
      }
      paramInt = 76;
      switch (paramInt)
      {
      }
    }
    catch (Throwable paramContext)
    {
      for (;;)
      {
        int i;
        p.d("pcheck error", paramContext);
      }
      return;
    }
    paramInt = d;
    i = -31;
    paramInt = ((-i | paramInt) << 1) - (paramInt ^ -i);
    b = paramInt % 128;
    if (paramInt % 2 != 0) {}
    for (paramInt = 1;; paramInt = 0)
    {
      switch (paramInt)
      {
      default: 
        paramInt = 16 / 0;
        return;
      }
      label145:
      paramInt = 36;
      break;
    }
  }
  
  private String d(String paramString)
  {
    int j = 1;
    int k = b;
    int i = k & 0x4F;
    k = -(k & 0x4F | k ^ 0x4F);
    int m = -k;
    i = ((-k & i) << 1) + (m ^ i);
    d = i % 128;
    if (i % 2 == 0) {}
    for (i = 0;; i = 1)
    {
      String str2;
      String str1;
      switch (i)
      {
      default: 
        str2 = "";
        str1 = str2;
      }
      for (;;)
      {
        try
        {
          paramString = Pattern.compile("referrer=([^&]*)", 4).matcher(paramString);
          str1 = str2;
          bool = paramString.find();
          if (!bool) {
            break label517;
          }
          i = 99;
          switch (i)
          {
          default: 
            paramString = "";
            i = b;
            m = -9;
            k = -m;
            m = -m;
            i = (i & m | i ^ m) + (k & i);
            d = i % 128;
            if (i % 2 != 0) {
              break label495;
            }
            i = 0;
            str1 = paramString;
            switch (i)
            {
            default: 
              str1 = paramString;
            }
            break;
          }
        }
        catch (Exception paramString)
        {
          boolean bool;
          p.d("get ref error", paramString);
          continue;
          return str1;
        }
        k = b;
        m = -61;
        i = -m;
        i = (i & k | k ^ i) << 1;
        k ^= -m;
        m = -k;
        i = ((-k & i) << 1) + (m ^ i);
        d = i % 128;
        if (i % 2 == 0)
        {
          i = j;
          switch (i)
          {
          default: 
            i = 8 / 0;
            return str1;
            str2 = "";
            str1 = str2;
            paramString = Pattern.compile("referrer=([^&]*)", 2).matcher(paramString);
            str1 = str2;
            bool = paramString.find();
            if (bool)
            {
              i = 44;
              switch (i)
              {
              }
              str1 = "";
              i = b;
              i = (i | 0x67) + (i & 0x67);
              d = i % 128;
              if (i % 2 == 0) {}
              paramString = paramString.group(1);
              str1 = paramString;
              paramString = URLDecoder.decode(paramString);
              i = b;
              k = -75;
              m = -k;
              i = (i | -k) + (m & i);
              d = i % 128;
              if (i % 2 == 0)
              {
                i = 25;
                switch (i)
                {
                }
              }
            }
            break;
          case 0: 
            continue;
            label495:
            i = 1;
            continue;
            i = 82;
            continue;
            i = 23;
            break;
          }
        }
        else
        {
          i = 0;
          continue;
          label517:
          i = 16;
        }
      }
    }
  }
  
  private void d(Context paramContext, String paramString1, String paramString2)
  {
    p.a("save click:" + paramString1);
    paramContext = paramContext.getSharedPreferences("titles", 0).edit();
    paramContext.putBoolean(paramString1, true);
    paramContext.putString(paramString1 + "_clickRef", paramString2);
    paramContext.apply();
    int i = b;
    int j = --51;
    i -= ((j | 0xFFFFFFFF) & (j & 0xFFFFFFFF ^ 0xFFFFFFFF));
    j = -1;
    i = ((i | j) << 1) - (i ^ j);
    d = i % 128;
    if (i % 2 == 0) {}
  }
  
  public static w e()
  {
    int i = b;
    i = (i | 0x2D) + (i & 0x2D);
    d = i % 128;
    w localW;
    if (i % 2 == 0)
    {
      i = 96;
      switch (i)
      {
      default: 
        localW = c;
        label56:
        i = b;
        int j = -(--99 ^ 0xFFFFFFFF);
        i = ((i & j) << 1) + (i ^ j);
        i = ((-1 | i) << 1) - (i ^ -1);
        d = i % 128;
        if (i % 2 != 0) {
          break;
        }
      }
    }
    for (i = 4;; i = 25)
    {
      switch (i)
      {
      default: 
        return localW;
      }
      i = null.length;
      return localW;
      localW = c;
      i = 30 / 0;
      break label56;
      i = 91;
      break;
    }
  }
  
  void d(Context paramContext, String paramString)
  {
    int j = 0;
    int i = d;
    int m = -15;
    int k = -m;
    m = -m;
    i = (k & i) - ((i & m | i ^ m) ^ 0xFFFFFFFF) - 1;
    b = i % 128;
    Object localObject;
    String str1;
    if (i % 2 != 0)
    {
      i = 80;
      switch (i)
      {
      default: 
        localObject = dj.e(paramContext);
        str1 = AdtAds.getAppKey(paramContext);
        if (!TextUtils.isEmpty(str1)) {
          break;
        }
      }
    }
    for (i = 0;; i = 1)
    {
      switch (i)
      {
      default: 
        p.a("empty appKey");
        i = d;
        j = --37;
        j = -((j | 0xFFFFFFFF) & (j & 0xFFFFFFFF ^ 0xFFFFFFFF));
        i = ((i & j) << 1) + (i ^ j);
        j = -1;
        i = (i | -1) + (j & i);
        b = i % 128;
        if (i % 2 == 0) {
          break;
        }
      }
      for (i = 90;; i = 93)
      {
        switch (i)
        {
        default: 
          i = null.length;
          return;
          i = 1;
          label214:
          switch (i)
          {
          }
          localObject = ((dj)localObject).c("gaid");
          if (!TextUtils.isEmpty((CharSequence)localObject)) {
            break;
          }
        }
        for (i = j;; i = 1)
        {
          String str2;
          switch (i)
          {
          default: 
            str2 = AdConfigHelper.getHost(paramContext, br.b);
            if (TextUtils.isEmpty(str2))
            {
              i = b - (--47 ^ 0xFFFFFFFF) - 1;
              d = i % 128;
              if (i % 2 == 0) {}
              return;
            }
            break;
          case 0: 
            i = d;
            j = --75;
            i -= ((j ^ 0xFFFFFFFF) & 0xFFFFFFFF | j & 0x0);
            j = -1;
            i = ((i & j) << 1) + (i ^ j);
            b = i % 128;
            if (i % 2 != 0) {}
            p.a("empty gaid");
            i = d;
            i = (i | 0x7B) + (i & 0x7B);
            b = i % 128;
            if (i % 2 != 0) {}
            return;
          }
          paramString = str2 + "/pcheck?" + new dk().d("n", paramString).d("d", localObject).d("v", "1").d("k", str1).d("mv", Integer.valueOf(130)).d("sdkv", "4.5.8").d("make", Build.MANUFACTURER).d("brand", Build.BRAND).d("model", Build.MODEL).d();
          p.a("url:" + paramString);
          c(paramContext, paramString, 1);
          i = d;
          k = -91;
          j = -k;
          j = (j & i | i ^ j) << 1;
          k = -k;
          i = -((i ^ 0xFFFFFFFF) & k | (k ^ 0xFFFFFFFF) & i);
          i = (i | j) + (j & i);
          b = i % 128;
          if (i % 2 != 0) {}
          return;
          return;
          localObject = dj.e(paramContext);
          str1 = AdtAds.getAppKey(paramContext);
          boolean bool = TextUtils.isEmpty(str1);
          i = 24 / 0;
          if (!bool) {
            break;
          }
          i = 0;
          break label214;
        }
      }
      i = 81;
      break;
    }
  }
}
