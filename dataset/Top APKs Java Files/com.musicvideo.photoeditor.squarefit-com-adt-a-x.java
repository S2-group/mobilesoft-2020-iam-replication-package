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

public class x
{
  private static int b;
  private static final x c;
  private static int e;
  
  static
  {
    int i = 1;
    b = 0;
    e = 1;
    c = new x();
    int j = b;
    j = ((j | 0x5B) << 1) - (-((j | 0x5B) & (j & 0x5B ^ 0xFFFFFFFF)) ^ 0xFFFFFFFF) - 1;
    e = j % 128;
    if (j % 2 == 0) {}
    for (;;)
    {
      switch (i)
      {
      default: 
        i = 18 / 0;
        return;
      }
      return;
      i = 0;
    }
  }
  
  private x() {}
  
  public static x a()
  {
    int j = e;
    int i = (j ^ 0x31 | j & 0x31) << 1;
    j ^= 0x31;
    int k = -j;
    i = (-j | i) + (k & i);
    b = i % 128;
    if (i % 2 != 0) {}
    for (i = 1;; i = 0)
    {
      switch (i)
      {
      default: 
        x localX = c;
        throw new NullPointerException();
      }
      return c;
    }
  }
  
  private void b(Context paramContext, String paramString1, String paramString2)
  {
    q.d("save click:" + paramString1);
    paramContext = paramContext.getSharedPreferences("titles", 0).edit();
    paramContext.putBoolean(paramString1, true);
    paramContext.putString(paramString1 + "_clickRef", paramString2);
    paramContext.apply();
    int i = b;
    i = ((i & 0xF) << 1) + (i ^ 0xF);
    e = i % 128;
    if (i % 2 == 0) {}
  }
  
  private String c(String paramString)
  {
    int j = 0;
    int i = b;
    i = (i & 0x1D) - ((i & 0x1D | i ^ 0x1D) ^ 0xFFFFFFFF) - 1;
    e = i % 128;
    if (i % 2 == 0) {}
    String str = "";
    for (;;)
    {
      try
      {
        paramString = Pattern.compile("referrer=([^&]*)", 2).matcher(paramString);
        boolean bool = paramString.find();
        if (!bool) {
          break label458;
        }
        i = 0;
        switch (i)
        {
        default: 
          paramString = str;
        }
      }
      catch (Exception localException1)
      {
        try
        {
          str = URLDecoder.decode(str);
          i = b;
          k = --41;
          k = i - (-((k | 0xFFFFFFFF) & (k & 0xFFFFFFFF ^ 0xFFFFFFFF)) ^ 0xFFFFFFFF) - 1;
          i = -1;
          i = ((k & -1) << 1) + (i ^ k);
          e = i % 128;
          if (i % 2 != 0) {
            break label438;
          }
          i = 0;
          label289:
          paramString = str;
          switch (i)
          {
          }
          paramString = str;
        }
        catch (Exception localException2)
        {
          int k;
          for (;;) {}
        }
        localException1 = localException1;
        paramString = str;
      }
      i = b + 108;
      i = ((-1 | i) << 1) - (i ^ -1);
      e = i % 128;
      if (i % 2 == 0)
      {
        i = j;
        switch (i)
        {
        default: 
          i = b;
          j = -9;
          i = ((-j | i) << 1) - (i ^ -j);
          e = i % 128;
          if (i % 2 != 0) {
            break;
          }
          i = 12;
          switch (i)
          {
          default: 
            throw new NullPointerException();
            paramString = paramString.group(1);
            str = paramString;
            paramString = str;
            q.a("get ref error", localException1);
            break;
          case 4: 
            return paramString;
          }
        case 0: 
          i = -6;
          continue;
          k = b;
          i = k ^ 0x67;
          k = -((k & 0x67) << 1);
          int m = -k;
          i = ((-k & i) << 1) + (m ^ i);
          e = i % 128;
          if (i % 2 == 0) {}
          for (i = 0;; i = 1)
          {
            switch (i)
            {
            }
            paramString = paramString.group(0);
            str = paramString;
            paramString = str;
            str = URLDecoder.decode(str);
            break;
            label438:
            i = 1;
            break label289;
          }
        }
      }
      else
      {
        i = 1;
        continue;
      }
      i = 4;
      continue;
      label458:
      i = 1;
    }
  }
  
  private void d(final Context paramContext, String paramString, final int paramInt)
  {
    try
    {
      paramString = NetworkInfo.buildGetInfo(paramString, null, null);
      NetworkExecutor.getInstance().doNetRequest(paramContext, paramString, new NetCallback()
      {
        private static int c = 0;
        private static int e = 1;
        
        public void onFail(NetworkInfo paramAnonymousNetworkInfo, Exception paramAnonymousException)
        {
          int j = e;
          int k = -37;
          int i = -k;
          k = -k;
          i = (j & k | j ^ k) + (i & j);
          c = i % 128;
          if (i % 2 != 0) {}
          for (i = 0;; i = 1)
          {
            switch (i)
            {
            default: 
              return;
            }
            throw new NullPointerException();
          }
        }
        
        public void onResp(NetworkInfo paramAnonymousNetworkInfo)
        {
          int j = 0;
          int i = c;
          int k = --78;
          i = (i | k) + (i & k) - 1;
          e = i % 128;
          if ((i % 2 != 0) || (paramAnonymousNetworkInfo.getResponseCode() == 200))
          {
            paramAnonymousNetworkInfo = new String(paramAnonymousNetworkInfo.getRespBody(), Constants.UTF_8);
            if (!TextUtils.isEmpty(paramAnonymousNetworkInfo)) {
              break label861;
            }
            i = 86;
            switch (i)
            {
            default: 
              k = c;
              int m = -73;
              i = -m;
              i = (i | k) & (k & i ^ 0xFFFFFFFF);
              m = -m;
              k = (k & m) << 1;
              i = (k | i) + (i & k);
              e = i % 128;
              if (i % 2 == 0)
              {
                i = 34;
                switch (i)
                {
                default: 
                  label159:
                  q.d("empty body");
                  i = 16 / 0;
                  i = c;
                  i = ((i & 0x6D) << 1) + (i ^ 0x6D);
                  e = i % 128;
                  if (i % 2 != 0) {}
                  break;
                }
              }
              break;
            }
          }
          for (i = j;; i = 1)
          {
            Object localObject3;
            label314:
            Object localObject2;
            String str;
            Object localObject1;
            bw localBw;
            switch (i)
            {
            default: 
              return;
              q.d("empty body");
              break;
              localObject3 = paramAnonymousNetworkInfo.trim().split("\t");
              if (localObject3.length <= 6)
              {
                q.d("error bodyinfo:" + paramAnonymousNetworkInfo);
                i = e + 29;
                c = i % 128;
                if (i % 2 != 0) {
                  i = 60;
                }
              }
              else
              {
                switch (i)
                {
                default: 
                  return;
                  localObject2 = localObject3[0];
                  str = dp.c(paramContext).c("gaid");
                  paramAnonymousNetworkInfo = localObject3[2];
                  localObject1 = localObject3[4];
                  localObject3 = localObject3[6].replace("\n", "").trim();
                  localBw = AdConfigHelper.getShellConfig(paramContext);
                  if (localBw != null)
                  {
                    i = 1;
                    switch (i)
                    {
                    default: 
                      i = e;
                      i = ((i ^ 0x27 | i & 0x27) << 1) - (i ^ 0x27);
                      c = i % 128;
                      if (i % 2 == 0) {}
                      break;
                    }
                  }
                  break;
                }
              }
            case 0: 
              label397:
              for (i = 27;; i = 90)
              {
                switch (i)
                {
                default: 
                  if (!bh.d(paramContext, (String)localObject3, localBw.l()))
                  {
                    j = e;
                    k = -99;
                    i = -k;
                    i = (i & j | j ^ i) << 1;
                    k = -k;
                    j = (j ^ 0xFFFFFFFF) & k | (k ^ 0xFFFFFFFF) & j;
                    k = -j;
                    i = ((-j & i) << 1) + (k ^ i);
                    c = i % 128;
                    if (i % 2 != 0) {}
                    return;
                  }
                  label562:
                  if (!x.a(x.this, paramContext, (String)localObject3)) {
                    break;
                  }
                }
                for (i = 0;; i = 1)
                {
                  switch (i)
                  {
                  default: 
                    label600:
                    label702:
                    label705:
                    do
                    {
                      ch.c().e(paramContext, "task", new do()
                      {
                        private static int d = 1;
                        private static int e = 0;
                        
                        public void b(Context paramAnonymous2Context, String paramAnonymous2String, Object... paramAnonymous2VarArgs)
                        {
                          int j = 0;
                          int k = e;
                          int i = (k ^ 0x39 | k & 0x39) << 1;
                          k = (k ^ 0xFFFFFFFF) & 0x39 | k & 0xFFFFFFC6;
                          i = ((-k | i) << 1) - (-k ^ i);
                          d = i % 128;
                          if ((i % 2 != 0) || (paramAnonymous2VarArgs.length < 3))
                          {
                            i = d;
                            j = --63;
                            j = i - ((j ^ 0xFFFFFFFF) & 0xFFFFFFFF | j & 0x0);
                            i = -1;
                            i = ((j & -1) << 1) + (i ^ j);
                            e = i % 128;
                            if (i % 2 == 0) {
                              break label1125;
                            }
                          }
                          label344:
                          label451:
                          label485:
                          label791:
                          label1092:
                          label1119:
                          label1125:
                          for (i = 1;; i = 0)
                          {
                            switch (i)
                            {
                            default: 
                              return;
                              try
                              {
                                localURI = (URI)paramAnonymous2VarArgs[1];
                                boolean bool = ((Boolean)paramAnonymous2VarArgs[0]).booleanValue();
                                q.d("cast click ok:" + bool);
                                if (!bool) {
                                  break label1092;
                                }
                                i = 2;
                                switch (i)
                                {
                                default: 
                                  k = d;
                                  m = -25;
                                  i = (-m | k) << 1;
                                  m = -m;
                                  k = -((k | m) & (k & m ^ 0xFFFFFFFF));
                                  i = (k | i) + (i & k);
                                  e = i % 128;
                                  if (i % 2 != 0) {}
                                  paramAnonymous2String = AdConfigHelper.getShellConfig(paramAnonymous2Context);
                                  i = x.4.this.a;
                                  switch (i)
                                  {
                                  }
                                  break;
                                }
                                i = d;
                                k = -3;
                                i = (-k ^ i) - ((i & -k) << 1 ^ 0xFFFFFFFF) - 1;
                                e = i % 128;
                                if (i % 2 == 0) {}
                              }
                              catch (Throwable paramAnonymous2Context)
                              {
                                for (;;)
                                {
                                  URI localURI;
                                  int m;
                                  q.a("cast error", paramAnonymous2Context);
                                }
                                return;
                              }
                              k = d;
                              i = (k ^ 0x2F | k & 0x2F) << 1;
                              k = -(k ^ 0x2F);
                              i = (k | i) + (i & k);
                              e = i % 128;
                              if (i % 2 != 0)
                              {
                                i = j;
                                switch (i)
                                {
                                default: 
                                  i = null.length;
                                  return;
                                  if (paramAnonymous2String != null)
                                  {
                                    i = 65;
                                    switch (i)
                                    {
                                    default: 
                                      i = e;
                                      i = ((i & 0x63) << 1) + (i & 0xFFFFFF9C | (i ^ 0xFFFFFFFF) & 0x63);
                                      d = i % 128;
                                      if ((i % 2 != 0) || (paramAnonymous2String.o() == 1))
                                      {
                                        q.d("do at cast");
                                        d.c().b(paramAnonymous2Context, "cast", new Object[] { new do()
                                        {
                                          private static int b = 0;
                                          private static int c = 1;
                                          
                                          public void b(Context paramAnonymous3Context, String paramAnonymous3String, Object... paramAnonymous3VarArgs)
                                          {
                                            int i = c;
                                            i = ((--36 | i) << 1) - (i ^ --36);
                                            int j = -1;
                                            i = (i | -1) + (j & i);
                                            b = i % 128;
                                            if (i % 2 != 0) {}
                                            q.d("do cast at success");
                                            i = b - (--55 ^ 0xFFFFFFFF) - 1;
                                            c = i % 128;
                                            if (i % 2 == 0) {}
                                          }
                                        }, this.a });
                                        i = e;
                                        m = -23;
                                        k = -m;
                                        i = ((k & i | i ^ k) << 1) - (-(i ^ -m) ^ 0xFFFFFFFF) - 1;
                                        d = i % 128;
                                        if (i % 2 != 0) {
                                          break label1119;
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
                            for (i = 0;; i = 1)
                            {
                              switch (i)
                              {
                              }
                              break label344;
                              q.d("cast_at switch is close");
                              i = d;
                              i = (i & 0x45) - ((i & 0x45 | i ^ 0x45) ^ 0xFFFFFFFF) - 1;
                              e = i % 128;
                              if (i % 2 != 0) {}
                              for (i = 75;; i = 58)
                              {
                                switch (i)
                                {
                                }
                                break label344;
                                paramAnonymous2VarArgs = x.b(x.this, localURI.toASCIIString());
                                x.e(x.this, paramAnonymous2Context, this.a, paramAnonymous2VarArgs);
                                if (paramAnonymous2String != null)
                                {
                                  i = 90;
                                  switch (i)
                                  {
                                  default: 
                                    k = e;
                                    m = -39;
                                    i = -m;
                                    i = (i & k | k ^ i) << 1;
                                    m = -m;
                                    k = -((k ^ 0xFFFFFFFF) & m | (m ^ 0xFFFFFFFF) & k);
                                    i = (k | i) + (i & k);
                                    d = i % 128;
                                    if ((i % 2 != 0) || (paramAnonymous2String.o() == 1))
                                    {
                                      q.d("do at cast");
                                      d.c().b(paramAnonymous2Context, "cast", new Object[] { new do()
                                      {
                                        private static int d = 1;
                                        private static int e = 0;
                                        
                                        public void b(Context paramAnonymous3Context, String paramAnonymous3String, Object... paramAnonymous3VarArgs)
                                        {
                                          int i = e;
                                          i = ((i & 0x45) << 1) + ((i & 0x45 ^ 0xFFFFFFFF) & (i | 0x45));
                                          d = i % 128;
                                          if (i % 2 == 0) {}
                                          for (i = 29;; i = 36)
                                          {
                                            switch (i)
                                            {
                                            default: 
                                              q.d("do cast at success");
                                              return;
                                            }
                                            q.d("do cast at success");
                                            i = 6 / 0;
                                            return;
                                          }
                                        }
                                      }, this.a });
                                      k = e;
                                      m = -47;
                                      i = -m;
                                      m = -m;
                                      i = (i & k) - (--(k & m | k ^ m) ^ 0xFFFFFFFF) - 1;
                                      d = i % 128;
                                      if (i % 2 != 0) {
                                        break;
                                      }
                                    }
                                    break;
                                  case 45: 
                                    q.d("cast_at switch is close");
                                    i = d + 85;
                                    e = i % 128;
                                    if (i % 2 == 0) {}
                                    break;
                                  }
                                }
                                for (i = 49;; i = 53)
                                {
                                  switch (i)
                                  {
                                  }
                                  break label344;
                                  throw new NullPointerException();
                                  i = 84;
                                  break label485;
                                  i = 45;
                                  break label791;
                                  i = 8;
                                  break;
                                }
                              }
                              i = 1;
                              break label451;
                            }
                          }
                        }
                      }, new Object[] { localObject2, str, paramAnonymousNetworkInfo, localObject1 });
                      i = e;
                      i = (i | 0x63) + (i & 0x63);
                      c = i % 128;
                      if (i % 2 != 0) {}
                      i = c;
                      i = (i | 0x7B) + (i & 0x7B);
                      e = i % 128;
                      if (i % 2 == 0) {}
                      return;
                      i = 45;
                      switch (i)
                      {
                      }
                    } while (paramInt != 1);
                  }
                  for (;;)
                  {
                    i = e;
                    i = ((i | 0x2F) << 1) - (i ^ 0x2F);
                    c = i % 128;
                    if (i % 2 != 0) {}
                    return;
                    i = null.length;
                    return;
                    boolean bool = bh.d(paramContext, (String)localObject3, localBw.l());
                    i = 65 / 0;
                    if (bool) {
                      break label562;
                    }
                    break;
                    j = e;
                    i = -65;
                    i = ((-i | j) << 1) - (j ^ -i);
                    c = i % 128;
                    if (i % 2 == 0) {
                      break label702;
                    }
                    i = 62;
                    break label705;
                    if (paramInt != 1) {
                      break label600;
                    }
                  }
                  throw new NullPointerException();
                  i = 84;
                  break label314;
                  label861:
                  i = 1;
                  break;
                  i = 0;
                  break label397;
                }
                i = 49;
                break label159;
              }
            }
          }
        }
      });
      paramInt = b;
      int j = -27;
      i = -j;
      i &= paramInt;
      j = -j;
      paramInt = paramInt & j | paramInt ^ j;
      paramInt = ((paramInt & i) << 1) + (i ^ paramInt);
      e = paramInt % 128;
      if (paramInt % 2 != 0) {}
    }
    catch (Throwable paramContext)
    {
      for (;;)
      {
        int i;
        q.a("pcheck error", paramContext);
      }
      paramInt = null.length;
      return;
    }
    paramInt = e;
    i = --37;
    i = -((i ^ 0xFFFFFFFF) & 0xFFFFFFFF | i & 0x0);
    paramInt = (paramInt | i) + (paramInt & i) - 1;
    b = paramInt % 128;
    if (paramInt % 2 != 0) {}
    for (paramInt = 1;; paramInt = 0) {
      switch (paramInt)
      {
      default: 
        return;
      }
    }
  }
  
  private boolean d(Context paramContext, String paramString)
  {
    int i = e;
    int k = -45;
    int j = -k;
    j = (j & i | i ^ j) << 1;
    k = -k;
    i = (i | k) & (i & k ^ 0xFFFFFFFF);
    i = ((-i | j) << 1) - (-i ^ j);
    b = i % 128;
    if (i % 2 != 0) {}
    boolean bool = paramContext.getSharedPreferences("titles", 0).getBoolean(paramString, false);
    i = e;
    i = (i & 0x19 | i ^ 0x19) + (i & 0x19);
    b = i % 128;
    if (i % 2 != 0) {}
    return bool;
  }
  
  private boolean e(Context paramContext, String paramString)
  {
    int i = b;
    int k = -5;
    int j = -k;
    k = -k;
    i = (i & k | i ^ k) + (j & i);
    e = i % 128;
    if (i % 2 == 0) {}
    paramContext = paramContext.getPackageManager().getInstalledPackages(0).iterator();
    j = e;
    i = j & 0x41;
    j = -(j | 0x41);
    k = -j;
    i = (-j | i) + (k & i);
    b = i % 128;
    if (i % 2 != 0) {}
    if (paramContext.hasNext()) {}
    for (i = 1;; i = 0)
    {
      switch (i)
      {
      default: 
        i = e;
        k = -85;
        j = -k;
        j = (j | i) & (i & j ^ 0xFFFFFFFF);
        k = -k;
        i = (i & k) << 1;
        i = ((j | i) << 1) - (i ^ j);
        b = i % 128;
        if ((i % 2 != 0) && (!TextUtils.equals(paramString, ((PackageInfo)paramContext.next()).packageName))) {
          break;
        }
      }
      for (i = 64;; i = 73)
      {
        switch (i)
        {
        default: 
          j = e;
          i = j ^ 0x39;
          j = (j & 0x39) << 1;
          i = (j | i) + (i & j);
          b = i % 128;
          if (i % 2 != 0) {}
          i = b;
          i = (i & 0x5F | i ^ 0x5F) + (i & 0x5F);
          e = i % 128;
          if (i % 2 == 0) {}
          return true;
          i = b;
          j = -125;
          k = -j;
          i = ((i & -j) << 1) + (k ^ i);
          e = i % 128;
          if (i % 2 == 0) {}
          return false;
        }
        i = b;
        j = -121;
        i = (-j & i) - (--(i | -j) ^ 0xFFFFFFFF) - 1;
        e = i % 128;
        if (i % 2 != 0) {
          break;
        }
        break;
      }
    }
  }
  
  void a(Context paramContext, String paramString)
  {
    int j = 0;
    int k = 1;
    int i = e;
    int m = --1;
    m = -((m | 0xFFFFFFFF) & (m & 0xFFFFFFFF ^ 0xFFFFFFFF));
    i = ((i & m) << 1) + (i ^ m) - 1;
    b = i % 128;
    if (i % 2 != 0) {}
    Object localObject = dp.c(paramContext);
    String str1 = AdtAds.getAppKey(paramContext);
    if (TextUtils.isEmpty(str1))
    {
      i = 27;
      switch (i)
      {
      default: 
        i = e;
        m = -11;
        k = -m;
        i = (i | -m) + (k & i);
        b = i % 128;
        if (i % 2 != 0)
        {
          i = 56;
          switch (i)
          {
          default: 
            label142:
            q.d("empty appKey");
            i = e - (--41 ^ 0xFFFFFFFF) - 1;
            b = i % 128;
            if (i % 2 == 0) {}
            break;
          }
        }
        break;
      }
    }
    for (i = j;; i = 1)
    {
      String str2;
      switch (i)
      {
      default: 
        i = null.length;
        return;
        str2 = ((dp)localObject).c("gaid");
        if (TextUtils.isEmpty(str2))
        {
          i = 0;
          switch (i)
          {
          default: 
            label239:
            localObject = AdConfigHelper.getHost(paramContext, bv.c);
            if (TextUtils.isEmpty((CharSequence)localObject))
            {
              j = b;
              k = -75;
              i = -k;
              i &= j;
              k = -k;
              j = -(j & k | j ^ k);
              k = -j;
              i = ((-j & i) << 1) + (k ^ i);
              e = i % 128;
              if (i % 2 != 0) {
                break label687;
              }
            }
            break;
          }
        }
        break;
      }
      label687:
      for (i = 19;; i = 17)
      {
        switch (i)
        {
        default: 
          return;
          i = b;
          j = --77;
          i = i - (-((j ^ 0xFFFFFFFF) & 0xFFFFFFFF | j & 0x0) ^ 0xFFFFFFFF) - 1 - 1;
          e = i % 128;
          if (i % 2 != 0) {
            break;
          }
        }
        for (i = k;; i = 0)
        {
          switch (i)
          {
          default: 
            q.d("empty gaid");
            return;
            paramString = (String)localObject + "/pcheck?" + new dn().d("n", paramString).d("d", str2).d("v", "1").d("k", str1).d("mv", Integer.valueOf(130)).d("sdkv", "4.6.2").d("make", Build.MANUFACTURER).d("brand", Build.BRAND).d("model", Build.MODEL).d();
            q.d("url:" + paramString);
            d(paramContext, paramString, 1);
            j = b;
            i = (j & 0x23 ^ 0xFFFFFFFF) & (j | 0x23);
            j = -((j & 0x23) << 1);
            i = ((-j | i) << 1) - (-j ^ i);
            e = i % 128;
            if (i % 2 == 0) {}
            return;
            return;
          }
          q.d("empty gaid");
          i = 80 / 0;
          return;
          q.d("empty appKey");
          i = 40 / 0;
          break;
          throw new NullPointerException();
        }
        i = 89;
        break;
        i = 1;
        break label239;
      }
      i = 96;
      break label142;
    }
  }
  
  public void b(Context paramContext, String paramString)
  {
    int j = 1;
    int k = 0;
    q.d("checkByPackageName:" + paramString);
    Object localObject = AdConfigHelper.getShellConfig(paramContext);
    if (localObject != null)
    {
      i = 64;
      switch (i)
      {
      default: 
        i = e;
        i = (i | 0xB) + (i & 0xB);
        b = i % 128;
        if ((i % 2 == 0) || (!bh.d(paramContext, paramString, ((bw)localObject).l())))
        {
          i = b;
          i = ((i & 0x21) << 1) + (i & 0xFFFFFFDE | (i ^ 0xFFFFFFFF) & 0x21);
          e = i % 128;
          if (i % 2 == 0) {}
          return;
        }
        break;
      }
      if (d(paramContext, paramString))
      {
        i = b;
        k = -109;
        i = ((-k | i) << 1) - (i ^ -k);
        e = i % 128;
        if (i % 2 != 0) {
          break label798;
        }
      }
    }
    label798:
    for (int i = j;; i = 0)
    {
      String str1;
      switch (i)
      {
      default: 
        return;
        str1 = AdtAds.getAppKey(paramContext);
        if (!TextUtils.isEmpty(str1)) {
          break;
        }
      }
      for (i = 57;; i = 41)
      {
        String str2;
        switch (i)
        {
        default: 
          j = e;
          k = -3;
          i = -k;
          i &= j;
          k = -k;
          j = -(j & k | j ^ k);
          k = -j;
          i = (-j | i) + (k & i);
          b = i % 128;
          if (i % 2 != 0)
          {
            i = 37;
            switch (i)
            {
            default: 
              q.d("empty appKey");
              i = 58 / 0;
              return;
            }
            q.d("empty appKey");
            return;
          }
          break;
        case 41: 
          str2 = dp.c(paramContext).c("gaid");
          if (!TextUtils.isEmpty(str2)) {}
          break;
        }
        for (i = k;; i = 1)
        {
          switch (i)
          {
          default: 
            i = e;
            i = (i & 0xB | i ^ 0xB) + (i & 0xB);
            b = i % 128;
            if (i % 2 != 0) {}
            q.d("empty gaid");
            j = e;
            i = j & 0xFFFFFFE2 | (j ^ 0xFFFFFFFF) & 0x1D;
            j = (j & 0x1D) << 1;
            i = ((i | j) << 1) - (j ^ i);
            b = i % 128;
            if (i % 2 != 0) {}
            return;
          }
          localObject = AdConfigHelper.getHost(paramContext, bv.c);
          if (TextUtils.isEmpty((CharSequence)localObject))
          {
            i = e;
            k = -31;
            j = -k;
            k = -k;
            i = ((i & k) << 1) + ((j | i) & (i & j ^ 0xFFFFFFFF));
            b = i % 128;
            if (i % 2 != 0) {}
            return;
          }
          paramString = new dn().d("p", paramString).d("d", str2).d("k", str1).d("v", "1").d("mv", Integer.valueOf(130)).d("make", Build.MANUFACTURER).d("brand", Build.BRAND).d("model", Build.MODEL).d("sdkv", "4.6.2");
          paramString = (String)localObject + "/pcheck?" + paramString.d();
          q.d("url:" + paramString);
          d(paramContext, paramString, 2);
          j = b;
          k = -43;
          i = -k;
          i &= j;
          k = -k;
          j = -(j & k | j ^ k);
          k = -j;
          i = (-j | i) + (k & i);
          e = i % 128;
          if (i % 2 == 0) {}
          return;
          i = 85 / 0;
          return;
          i = 11;
          break;
        }
        i = 31;
        break;
      }
    }
  }
}
