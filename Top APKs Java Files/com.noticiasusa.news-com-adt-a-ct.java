package com.adt.a;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ct
{
  private static byte[][] a;
  private static byte[] b;
  private static byte[] c;
  private static byte[] d;
  private static byte[] e;
  private static byte[] f;
  private static byte[] g;
  private static byte[] h;
  private static byte[] i;
  private static byte[] j;
  private static byte[] k;
  private static byte[] l;
  private static byte[] m;
  private static byte[] n;
  private static byte[] o;
  private static byte[] p;
  private static int q;
  private static byte[] r;
  private static int s;
  private static byte[] t;
  
  static
  {
    int i1 = 1;
    q = 0;
    s = 1;
    byte[] arrayOfByte1 = { 47, 115, 121, 115, 116, 101, 109, 47, 98, 105, 110, 47 };
    byte[] arrayOfByte2 = { 47, 115, 121, 115, 116, 101, 109, 47, 120, 98, 105, 110, 47 };
    byte[] arrayOfByte3 = { 47, 118, 101, 110, 100, 111, 114, 47, 98, 105, 110, 47 };
    a = new byte[][] { arrayOfByte1, arrayOfByte2, { 47, 115, 121, 115, 116, 101, 109, 47, 115, 98, 105, 110, 47 }, { 47, 115, 98, 105, 110, 47 }, arrayOfByte3 };
    e = new byte[] { 115, 117 };
    d = new byte[] { 98, 117, 115, 121, 98, 111, 120 };
    c = new byte[] { 119, 104, 105, 99, 104, 32, 115, 117 };
    b = new byte[] { 119, 104, 105, 99, 104, 32, 98, 117, 115, 121, 98, 111, 120 };
    h = new byte[] { 120, 112, 111, 115, 101, 100 };
    f = new byte[] { 104, 111, 111, 107 };
    i = new byte[] { 100, 101, 46, 114, 111, 98, 118, 46, 97, 110, 100, 114, 111, 105, 100, 46, 120, 112, 111, 115, 101, 100, 46, 88, 112, 111, 115, 101, 100, 66, 114, 105, 100, 103, 101 };
    j = new byte[] { 100, 105, 115, 97, 98, 108, 101, 72, 111, 111, 107, 115 };
    g = new byte[] { 100, 101, 46, 114, 111, 98, 118, 46, 97, 110, 100, 114, 111, 105, 100, 46, 120, 112, 111, 115, 101, 100, 46, 105, 110, 115, 116, 97, 108, 108, 101, 114 };
    k = new byte[] { 103, 101, 116, 112, 114, 111, 112, 32, 114, 111, 46, 100, 101, 98, 117, 103, 103, 97, 98, 108, 101 };
    m = new byte[] { 47, 112, 114, 111, 99, 47, 48, 47, 115, 116, 97, 116, 117, 115 };
    o = new byte[] { 116, 114, 97, 99, 101, 114, 112, 105, 100 };
    n = new byte[] { 106, 97, 118, 97, 46, 108, 97, 110, 103, 46, 83, 121, 115, 116, 101, 109 };
    l = new byte[] { 103, 101, 116, 80, 114, 111, 112, 101, 114, 116, 121 };
    t = new byte[] { 104, 116, 116, 112, 46, 112, 114, 111, 120, 121, 72, 111, 115, 116 };
    r = new byte[] { 104, 116, 116, 112, 46, 112, 114, 111, 120, 121, 80, 111, 114, 116 };
    p = new byte[] { 110, 117, 108, 108 };
    int i2 = q + 108 - 1;
    s = i2 % 128;
    if (i2 % 2 == 0) {}
    for (;;)
    {
      switch (i1)
      {
      default: 
        throw new NullPointerException();
      }
      return;
      i1 = 0;
    }
  }
  
  public ct() {}
  
  private static boolean a()
  {
    boolean bool2 = false;
    int i1 = s + 95;
    q = i1 % 128;
    if (i1 % 2 != 0) {}
    for (i1 = 1;; i1 = 0)
    {
      byte[][] arrayOfByte;
      int i2;
      boolean bool1;
      switch (i1)
      {
      default: 
        arrayOfByte = a;
        i2 = arrayOfByte.length;
        i1 = 1;
        bool1 = bool2;
        if (i1 < i2)
        {
          byte[] arrayOfByte1 = arrayOfByte[i1];
          if (!b(new String(arrayOfByte1) + new String(d))) {
            break label240;
          }
        }
        break;
      }
      label240:
      for (int i3 = 1;; i3 = 0) {
        switch (i3)
        {
        default: 
          i1 = ((i1 | 0xFFFFFFD4) << 1) - (i1 ^ 0xFFFFFFD4) - (--45 ^ 0xFFFFFFFF) - 1;
          break;
          arrayOfByte = a;
          i2 = arrayOfByte.length;
          i1 = 0;
          break;
        case 1: 
          for (i1 = 73;; i1 = 61)
          {
            bool1 = bool2;
            switch (i1)
            {
            default: 
              bool1 = true;
            }
            return bool1;
            i1 = q;
            i2 = -113;
            i3 = -i2;
            i1 = (i1 | -i2) + (i3 & i1);
            s = i1 % 128;
            if (i1 % 2 != 0) {
              break;
            }
          }
        }
      }
    }
  }
  
  private static boolean a(Context paramContext)
  {
    int i2 = 1;
    i1 = s;
    i3 = -75;
    i4 = -i3;
    i1 = ((i1 & -i3) << 1) + (i4 ^ i1);
    q = i1 % 128;
    if (i1 % 2 != 0) {}
    try
    {
      i1 = paramContext.getApplicationInfo().flags;
      if ((i1 & 0x2) == 0) {
        break label260;
      }
      i1 = 1;
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        boolean bool1;
        boolean bool2 = false;
        continue;
        i1 = q;
        i3 = -19;
        i4 = -i3;
        i1 = (i1 | -i3) + (i4 & i1);
        s = i1 % 128;
        if (i1 % 2 == 0) {}
        for (i1 = 89;; i1 = 65)
        {
          switch (i1)
          {
          }
          bool1 = true;
          break;
        }
        i1 = 0;
        continue;
        i1 = 0;
      }
    }
    switch (i1)
    {
    default: 
      for (bool1 = false;; bool1 = true)
      {
        i1 = s;
        i3 = -69;
        i4 = -i3;
        i1 = (i1 | -i3) + (i4 & i1);
        q = i1 % 128;
        if (i1 % 2 == 0) {
          break;
        }
        i1 = i2;
        bool2 = bool1;
        switch (i1)
        {
        default: 
          bool2 = bool1;
        }
        i1 = q;
        i1 = ((i1 & 0x1B) << 1) + (i1 ^ 0x1B);
        s = i1 % 128;
        if (i1 % 2 == 0) {}
        return bool2;
      }
    }
  }
  
  private static boolean a(Context paramContext, String paramString)
  {
    int i1 = s + 30 - 1;
    q = i1 % 128;
    if (i1 % 2 != 0) {}
    for (;;)
    {
      try
      {
        paramContext = paramContext.getPackageManager().getInstalledPackages(0).iterator();
        i1 = q;
        int i2 = -99;
        i1 = ((-i2 | i1) << 1) - (i1 ^ -i2);
        s = i1 % 128;
        if (i1 % 2 == 0) {}
        bool = paramContext.hasNext();
        if (!bool) {
          break label261;
        }
        i1 = 70;
        switch (i1)
        {
        default: 
          i1 = q + 39;
          s = i1 % 128;
          if (i1 % 2 != 0) {
            break label267;
          }
          i1 = 74;
          switch (i1)
          {
          default: 
            bool = ((PackageInfo)paramContext.next()).packageName.equals(paramString);
            if (!bool) {
              break label273;
            }
            i1 = 0;
            switch (i1)
            {
            default: 
              break;
            case 0: 
              bool = true;
              i1 = q;
              i1 = (i1 | 0x47) + (i1 & 0x47);
              s = i1 % 128;
              if (i1 % 2 == 0) {}
              return bool;
            }
            break;
          }
          break;
        }
      }
      catch (Exception paramContext)
      {
        return false;
      }
      ((PackageInfo)paramContext.next()).packageName.equals(paramString);
      throw new NullPointerException();
      label261:
      i1 = 17;
      continue;
      label267:
      i1 = 17;
      continue;
      label273:
      i1 = 1;
      continue;
      boolean bool = false;
    }
  }
  
  private static boolean b()
  {
    int i2 = 1;
    int i1 = s;
    i1 = ((i1 | 0x4F) << 1) - (i1 ^ 0x4F);
    q = i1 % 128;
    if (i1 % 2 != 0)
    {
      i1 = 1;
      switch (i1)
      {
      default: 
        label72:
        String str;
        try
        {
          Integer.valueOf(Arrays.toString(new byte[] { 83, 66 }));
          i1 = q;
          int i3 = -27;
          int i4 = -i3;
          i3 = ((i1 & -i3) << 1) + (i4 ^ i1);
          s = i3 % 128;
          i1 = i2;
          if (i3 % 2 == 0) {
            i1 = 0;
          }
          switch (i1)
          {
          }
          return false;
        }
        catch (Exception localException)
        {
          str = e(localException).toLowerCase();
          if (str.contains(new String(f))) {
            break;
          }
        }
        if (!str.contains(new String(h))) {}
        break;
      }
    }
    for (i1 = 74;; i1 = 79)
    {
      switch (i1)
      {
      }
      return true;
      Integer.valueOf(Arrays.toString(new byte[] { 83, 66 }));
      break label72;
      i1 = 0;
      break;
    }
  }
  
  private static boolean b(String paramString)
  {
    int i2 = 1;
    for (;;)
    {
      boolean bool1;
      try
      {
        bool1 = new File(paramString).exists();
        if (!bool1) {
          break label230;
        }
        i1 = 1;
        switch (i1)
        {
        default: 
          i1 = q;
          i1 = ((i1 & 0x71) << 1) + (i1 ^ 0x71);
          s = i1 % 128;
          if (i1 % 2 != 0) {
            break label225;
          }
          i1 = 1;
          switch (i1)
          {
          default: 
            bool1 = true;
            i1 = q;
            i1 = ((i1 | 0x53) << 1) - (i1 ^ 0x53);
            s = i1 % 128;
            if (i1 % 2 != 0) {
              break label220;
            }
            i1 = 1;
            bool2 = bool1;
            switch (i1)
            {
            default: 
              bool2 = bool1;
            }
            break;
          }
          break;
        }
      }
      catch (Exception paramString)
      {
        boolean bool2 = false;
        continue;
        return bool2;
      }
      int i1 = s + 25;
      q = i1 % 128;
      if (i1 % 2 != 0)
      {
        i1 = i2;
        switch (i1)
        {
        default: 
          i1 = 95 / 0;
          return bool2;
        }
        bool1 = false;
      }
      else
      {
        i1 = 0;
        continue;
        label220:
        i1 = 0;
        continue;
        label225:
        i1 = 0;
        continue;
        label230:
        i1 = 0;
        continue;
        bool1 = false;
      }
    }
  }
  
  private static String c(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    for (;;)
    {
      try
      {
        localProcess = Runtime.getRuntime().exec(paramString);
        localBufferedReader = new BufferedReader(new InputStreamReader(localProcess.getInputStream()));
        paramString = localBufferedReader.readLine();
        if (paramString == null) {
          break label212;
        }
        i1 = 0;
      }
      catch (Exception paramString)
      {
        Process localProcess;
        BufferedReader localBufferedReader;
        continue;
        i3 = s;
        i2 = -23;
        i1 = -i2;
        i1 = (i3 | -i2) + (i1 & i3);
        q = i1 % 128;
        if (i1 % 2 == 0) {
          continue;
        }
        i1 = 1;
        continue;
        localStringBuilder.append(paramString);
        throw new NullPointerException();
      }
      localBufferedReader.close();
      localProcess.destroy();
      int i1 = q + 111;
      s = i1 % 128;
      if (i1 % 2 == 0) {}
      paramString = localStringBuilder.toString();
      i1 = s;
      i1 = ((i1 & 0xD) << 1) + (i1 ^ 0xD);
      q = i1 % 128;
      if (i1 % 2 != 0) {}
      return paramString;
      i1 = 0;
      switch (i1)
      {
      default: 
        localStringBuilder.append(paramString);
        break;
      case 1: 
        int i3;
        int i2;
        label212:
        i1 = 1;
        switch (i1)
        {
        }
        break;
      }
    }
  }
  
  private static boolean c()
  {
    boolean bool = true;
    try
    {
      Field localField = ClassLoader.getSystemClassLoader().loadClass(new String(i)).getDeclaredField(new String(j));
      localField.setAccessible(true);
      localField.get(null);
      int i1 = q - (--57 ^ 0xFFFFFFFF) - 1;
      s = i1 % 128;
      if (i1 % 2 == 0) {}
      return bool;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        bool = false;
      }
    }
  }
  
  public static boolean c(Context paramContext)
  {
    int i2 = 21;
    int i3 = 0;
    int i1 = 0;
    int i4 = q;
    int i5 = -1;
    i4 = ((-i5 | i4) << 1) - (i4 ^ -i5);
    s = i4 % 128;
    if (i4 % 2 == 0)
    {
      bool = g();
      i4 = 15 / 0;
      if (!bool) {}
    }
    else
    {
      while (g())
      {
        boolean bool;
        i2 = s + 113;
        q = i2 % 128;
        if (i2 % 2 != 0) {
          i1 = 1;
        }
        switch (i1)
        {
        default: 
          return true;
        }
      }
    }
    if (d(paramContext))
    {
      i1 = 5;
      switch (i1)
      {
      default: 
        label125:
        if (!a(paramContext)) {
          break;
        }
      }
    }
    for (i1 = 0;; i1 = 1) {
      switch (i1)
      {
      default: 
        if (e())
        {
          i1 = 1;
          switch (i1)
          {
          default: 
            i1 = q + 14 - 1;
            s = i1 % 128;
            if (i1 % 2 != 0) {
              break;
            }
            return true;
          case 0: 
            if (j())
            {
              i1 = 74;
              switch (i1)
              {
              default: 
                if (d())
                {
                  i2 = q;
                  i1 = -107;
                  i1 = ((-i1 | i2) << 1) - (i2 ^ -i1);
                  s = i1 % 128;
                  if (i1 % 2 == 0)
                  {
                    i1 = 26;
                    switch (i1)
                    {
                    }
                    return true;
                  }
                }
                else
                {
                  if (!a()) {
                    break label751;
                  }
                  i1 = 1;
                  switch (i1)
                  {
                  default: 
                    if (!TextUtils.isEmpty(c(new String(c))))
                    {
                      i1 = 1;
                      switch (i1)
                      {
                      default: 
                        if (!TextUtils.isEmpty(c(new String(b)))) {
                          break;
                        }
                        if (a(paramContext, new String(g)))
                        {
                          i1 = q;
                          i3 = ((i1 & 0x3B) << 1) + (i1 ^ 0x3B);
                          s = i3 % 128;
                          i1 = i2;
                          if (i3 % 2 == 0) {
                            i1 = 85;
                          }
                          switch (i1)
                          {
                          default: 
                            return true;
                          }
                          return false;
                        }
                        if (b())
                        {
                          i1 = q;
                          i2 = (i1 | 0x3) + (i1 & 0x3);
                          s = i2 % 128;
                          i1 = i3;
                          if (i2 % 2 == 0) {
                            i1 = 1;
                          }
                        }
                        switch (i1)
                        {
                        default: 
                          return true;
                          if (c())
                          {
                            i3 = s;
                            i1 = -39;
                            i2 = -i1;
                            i1 = ((i3 & -i1) << 1) + (i2 ^ i3);
                            q = i1 % 128;
                            if (i1 % 2 != 0) {}
                            return true;
                          }
                          return false;
                        }
                      case 1: 
                        return true;
                        throw new NullPointerException();
                        i1 = null.length;
                        return true;
                      }
                    }
                    break;
                  }
                }
                break;
              case 74: 
                i1 = q + 27;
                s = i1 % 128;
                if (i1 % 2 != 0) {}
                break;
              }
            }
            break;
          }
        }
      case 0: 
        label180:
        label234:
        label295:
        label326:
        label365:
        for (i1 = 36;; i1 = 21)
        {
          switch (i1)
          {
          }
          return true;
          i1 = s - (--99 ^ 0xFFFFFFFF) - 1;
          q = i1 % 128;
          if (i1 % 2 == 0) {
            break;
          }
          return true;
          i2 = q;
          i1 = -113;
          i1 = ((-i1 | i2) << 1) - (i2 ^ -i1);
          s = i1 % 128;
          if (i1 % 2 != 0) {
            break;
          }
          return true;
          i1 = s + 47;
          q = i1 % 128;
          if (i1 % 2 == 0) {
            break;
          }
          return false;
          i1 = 76;
          break label295;
          i1 = 42;
          break label234;
          i1 = 0;
          break label180;
          label751:
          i1 = 0;
          break label326;
          i1 = 3;
          break label125;
          i1 = 0;
          break label365;
        }
      }
    }
  }
  
  private static boolean d()
  {
    boolean bool2 = true;
    int i1 = s;
    i1 = (i1 | 0x9) + (i1 & 0x9);
    q = i1 % 128;
    byte[][] arrayOfByte;
    int i2;
    boolean bool1;
    label66:
    int i3;
    if (i1 % 2 != 0)
    {
      i1 = 0;
      switch (i1)
      {
      default: 
        arrayOfByte = a;
        i2 = arrayOfByte.length;
        i1 = 0;
        bool1 = false;
        while (i1 < i2)
        {
          byte[] arrayOfByte1 = arrayOfByte[i1];
          if (!b(new String(arrayOfByte1) + new String(e))) {
            break label341;
          }
          i3 = 0;
          switch (i3)
          {
          default: 
            label120:
            i1 = i1 - 35 - 1;
            i3 = --37;
            int i4 = --37;
            int i5 = s;
            i5 = ((i5 & 0x19) << 1) + (i5 ^ 0x19);
            q = i5 % 128;
            if (i5 % 2 != 0) {}
            i1 = ((i3 | i1) << 1) - (i1 ^ i4);
          }
        }
        label204:
        i1 = s + 23;
        q = i1 % 128;
        if (i1 % 2 == 0) {
          break;
        }
      }
    }
    for (i1 = 82;; i1 = 12)
    {
      switch (i1)
      {
      default: 
        i1 = 74 / 0;
        return bool1;
      }
      return bool1;
      arrayOfByte = a;
      i2 = arrayOfByte.length;
      i1 = 0;
      bool1 = true;
      break label66;
      i1 = s + 9;
      q = i1 % 128;
      if (i1 % 2 != 0) {}
      i1 = s;
      i2 = -1;
      i1 = ((-i2 | i1) << 1) - (i1 ^ -i2);
      q = i1 % 128;
      bool1 = bool2;
      if (i1 % 2 == 0) {
        break label204;
      }
      bool1 = bool2;
      break label204;
      label341:
      i3 = 1;
      break label120;
      i1 = 1;
      break;
    }
  }
  
  private static boolean d(Context paramContext)
  {
    int i1 = q;
    i1 = (i1 | 0x15) + (i1 & 0x15);
    s = i1 % 128;
    label66:
    Object localObject1;
    Object localObject2;
    boolean bool;
    label128:
    label174:
    int i2;
    if (i1 % 2 == 0)
    {
      i1 = 0;
      switch (i1)
      {
      default: 
        throw new NullPointerException();
      }
      for (;;)
      {
        if (Build.VERSION.SDK_INT < 23) {
          break label616;
        }
        i1 = 1;
        switch (i1)
        {
        default: 
          if (Build.VERSION.SDK_INT >= 16) {
            localObject1 = new ArrayList();
          }
          break;
        }
        try
        {
          localObject2 = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
          for (;;)
          {
            bool = ((Iterator)localObject2).hasNext();
            if (!bool) {
              break label621;
            }
            i1 = 1;
            switch (i1)
            {
            default: 
              i1 = s + 22 - 1;
              q = i1 % 128;
              if (i1 % 2 == 0) {
                break label610;
              }
              i1 = 84;
              switch (i1)
              {
              default: 
                paramContext = (NetworkInterface)((Iterator)localObject2).next();
                if (paramContext.isUp())
                {
                  ((List)localObject1).add(paramContext.getName());
                  i1 = s;
                  i3 = -85;
                  i2 = -i3;
                  i1 = ((i1 & -i3) << 1) + (i2 ^ i1);
                  q = i1 % 128;
                  if (i1 % 2 == 0) {}
                }
                break;
              }
              break;
            }
          }
          if (paramContext != null) {}
        }
        catch (Throwable paramContext)
        {
          for (;;)
          {
            int i3;
            label265:
            dz.a("Failed collecting ivc data", paramContext);
          }
        }
      }
      return false;
    }
    for (;;)
    {
      try
      {
        localObject1 = (ConnectivityManager)paramContext.getSystemService("connectivity");
        localObject2 = ((ConnectivityManager)localObject1).getAllNetworks();
        i3 = localObject2.length;
        i1 = s;
        i2 = -27;
        i1 = ((-i2 | i1) << 1) - (i1 ^ -i2);
        q = i1 % 128;
        if (i1 % 2 != 0) {}
        i1 = 0;
        if (i1 >= i3) {
          break label475;
        }
        i2 = s;
        i2 = (i2 | 0x65) + (i2 & 0x65);
        q = i2 % 128;
        if (i2 % 2 == 0) {
          break label626;
        }
        i2 = 55;
        switch (i2)
        {
        default: 
          paramContext = ((ConnectivityManager)localObject1).getNetworkCapabilities(localObject2[i1]);
          if (!paramContext.hasTransport(4)) {
            break label605;
          }
          i2 = 0;
        }
      }
      catch (Throwable paramContext)
      {
        dz.a("Failed collecting ivc data", paramContext);
      }
      bool = paramContext.hasCapability(15);
      if (!bool) {
        i2 = 0;
      }
      switch (i2)
      {
      default: 
        return true;
        break label265;
        bool = ((List)localObject1).contains("tun0");
        return bool;
        return false;
        paramContext = ((ConnectivityManager)localObject1).getNetworkCapabilities(localObject2[i1]);
        bool = paramContext.hasTransport(3);
        if (bool)
        {
          i2 = q - (--123 ^ 0xFFFFFFFF) - 1;
          s = i2 % 128;
          if (i2 % 2 != 0) {
            continue;
          }
          bool = paramContext.hasCapability(34);
          if (!bool)
          {
            continue;
            paramContext = (NetworkInterface)((Iterator)localObject2).next();
            bool = paramContext.isUp();
            i1 = 19 / 0;
            if (bool) {}
            for (i1 = 0;; i1 = 1)
            {
              switch (i1)
              {
              }
              break;
            }
            i2 = 1;
            continue;
            i2 = 1;
            break label637;
            i1 = 17;
            break label174;
            i1 = 0;
            break label66;
            i1 = 0;
            break label128;
            i2 = 16;
            continue;
            i1 = 1;
            break;
            switch (i2)
            {
            }
          }
        }
      case 1: 
        label475:
        label605:
        label610:
        label616:
        label621:
        label626:
        label637:
        i1 = i1 + 29 - 1;
        i2 = -27;
        i1 = ((i1 & -27) << 1) + (i2 ^ i1);
      }
    }
  }
  
  private static String e(Throwable paramThrowable)
  {
    int i3 = q;
    int i2 = -7;
    int i1 = -i2;
    i1 = ((i3 & -i2) << 1) + (i1 ^ i3);
    s = i1 % 128;
    label66:
    Object localObject1;
    label115:
    label123:
    label180:
    label210:
    label228:
    Object localObject2;
    if (i1 % 2 == 0)
    {
      i1 = 66;
      switch (i1)
      {
      default: 
        if (paramThrowable == null)
        {
          i1 = 0;
          switch (i1)
          {
          default: 
            i1 = s;
            i1 = (i1 | 0x3B) + (i1 & 0x3B);
            q = i1 % 128;
            if (i1 % 2 != 0)
            {
              localObject1 = paramThrowable;
              if (localObject1 == null) {
                break label503;
              }
              i1 = 11;
              switch (i1)
              {
              default: 
                i2 = s;
                i1 = -97;
                i3 = -i1;
                i1 = (i2 | -i1) + (i3 & i2);
                q = i1 % 128;
                if (i1 % 2 != 0)
                {
                  i1 = 0;
                  switch (i1)
                  {
                  default: 
                    if ((localObject1 instanceof UnknownHostException))
                    {
                      i1 = 0;
                      switch (i1)
                      {
                      default: 
                        localObject2 = ((Throwable)localObject1).getCause();
                        i1 = q;
                        i1 = ((i1 & 0x6B) << 1) + (i1 ^ 0x6B);
                        s = i1 % 128;
                        if (i1 % 2 != 0) {}
                        break;
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
        break;
      }
    }
    for (i1 = 36;; i1 = 92)
    {
      localObject1 = localObject2;
      switch (i1)
      {
      }
      localObject1 = localObject2;
      break label115;
      throw new NullPointerException();
      i1 = q;
      i1 = ((i1 & 0x1D) << 1) + (i1 ^ 0x1D);
      s = i1 % 128;
      if (i1 % 2 == 0) {}
      return "";
      do
      {
        return "";
        localObject2 = new StringWriter();
        localObject1 = new PrintWriter((Writer)localObject2);
        ThrowableExtension.a(paramThrowable, (PrintWriter)localObject1);
        ((PrintWriter)localObject1).flush();
        paramThrowable = ((StringWriter)localObject2).toString();
        i1 = s;
        i2 = -59;
        i1 = ((-i2 | i1) << 1) - (i1 ^ -i2);
        q = i1 % 128;
        if (i1 % 2 != 0)
        {
          i1 = 21 / 0;
          return paramThrowable;
        }
        return paramThrowable;
        localObject1 = paramThrowable;
        break;
        boolean bool = localObject1 instanceof UnknownHostException;
        i1 = null.length;
        if (!bool) {
          break label228;
        }
        i1 = s - (--85 ^ 0xFFFFFFFF) - 1;
        q = i1 % 128;
      } while (i1 % 2 == 0);
      i1 = 37 / 0;
      return "";
      i1 = 1;
      break label66;
      i1 = 1;
      break label210;
      i1 = 6;
      break;
      i1 = 1;
      break label180;
      label503:
      i1 = 73;
      break label123;
    }
  }
  
  private static boolean e()
  {
    i2 = 0;
    bool2 = true;
    try
    {
      i1 = Integer.valueOf(c(new String(k))).intValue();
      if (i1 != 1) {
        break label254;
      }
      i1 = 0;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        bool2 = false;
        continue;
        int i1 = q;
        int i3 = -9;
        i1 = ((-i3 | i1) << 1) - (i1 ^ -i3);
        s = i1 % 128;
        if (i1 % 2 == 0) {}
        i3 = q + 78 - 1;
        s = i3 % 128;
        i1 = i2;
        if (i3 % 2 == 0) {
          i1 = 1;
        }
        boolean bool1 = bool2;
        switch (i1)
        {
        }
        i1 = --5;
        bool1 = bool2;
        continue;
        i1 = 1;
        continue;
        i1 = 99;
      }
    }
    switch (i1)
    {
    default: 
      bool1 = false;
      i1 = q;
      i2 = -69;
      i1 = ((-i2 | i1) << 1) - (i1 ^ -i2);
      s = i1 % 128;
      if (i1 % 2 == 0)
      {
        i1 = 68;
        bool2 = bool1;
        switch (i1)
        {
        default: 
          bool2 = bool1;
        }
        i1 = q;
        i2 = -23;
        i1 = ((-i2 | i1) << 1) - (i1 ^ -i2);
        s = i1 % 128;
        if (i1 % 2 == 0) {}
        return bool2;
      }
      break;
    }
  }
  
  private static boolean g()
  {
    int i2 = 0;
    for (;;)
    {
      try
      {
        localObject = ClassLoader.getSystemClassLoader().loadClass(new String(n)).getMethod(new String(l), new Class[] { String.class });
        str = String.valueOf(((Method)localObject).invoke(null, new Object[] { new String(t) }));
        localObject = String.valueOf(((Method)localObject).invoke(null, new Object[] { new String(r) }));
        if (TextUtils.isEmpty(str)) {
          continue;
        }
        bool1 = str.equals(new String(p));
        if (!bool1) {
          break label347;
        }
        i1 = 79;
        switch (i1)
        {
        default: 
          i1 = q + 41;
          s = i1 % 128;
          if (i1 % 2 == 0) {}
          bool1 = true;
          i1 = q + 9;
          s = i1 % 128;
          if (i1 % 2 != 0) {
            break label358;
          }
          i1 = 1;
          bool2 = bool1;
          switch (i1)
          {
          default: 
            bool2 = bool1;
          }
          break;
        }
      }
      catch (Exception localException)
      {
        Object localObject;
        String str;
        boolean bool1;
        boolean bool2 = false;
        continue;
        return bool2;
      }
      int i1 = q + 106 - 1;
      s = i1 % 128;
      if (i1 % 2 == 0)
      {
        i1 = i2;
        switch (i1)
        {
        default: 
          i1 = null.length;
          return bool2;
          if (!TextUtils.isEmpty((CharSequence)localObject))
          {
            bool1 = str.equals(new String(p));
            if (!bool1)
            {
              i1 = 1;
              switch (i1)
              {
              default: 
                break;
              case 0: 
                bool1 = false;
                break;
              }
            }
          }
          else
          {
            bool1 = false;
          }
          break;
        case 1: 
          i1 = 0;
          continue;
          label347:
          i1 = 35;
          break;
        }
      }
      else
      {
        i1 = 1;
        continue;
        label358:
        i1 = 0;
      }
    }
  }
  
  private static boolean j()
  {
    bool2 = false;
    try
    {
      i1 = android.os.Process.myPid();
      localInputStreamReader = new InputStreamReader(new FileInputStream(new File(new String(m).replaceAll(String.valueOf(0), String.valueOf(i1)))));
      localBufferedReader = new BufferedReader(localInputStreamReader);
      localObject = new String(o);
      i1 = q;
      int i3 = -45;
      i2 = -i3;
      i1 = (i1 | -i3) + (i2 & i1);
      s = i1 % 128;
      if (i1 % 2 == 0) {}
      str2 = localBufferedReader.readLine();
      if (str2 == null) {
        break label491;
      }
      i1 = 1;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        int i1;
        InputStreamReader localInputStreamReader;
        BufferedReader localBufferedReader;
        Object localObject;
        int i2;
        String str2;
        label125:
        boolean bool1 = bool2;
        continue;
        switch (i1)
        {
        }
        String str1 = "";
      }
    }
    localInputStreamReader.close();
    localBufferedReader.close();
    bool1 = TextUtils.isEmpty((CharSequence)localObject);
    if (!bool1)
    {
      i1 = 49;
      bool1 = bool2;
      switch (i1)
      {
      default: 
        i1 = q + 73;
        s = i1 % 128;
        if (i1 % 2 == 0) {}
        localObject = Pattern.compile("\\d+").matcher((CharSequence)localObject);
        bool1 = ((Matcher)localObject).find();
        if (!bool1) {
          break;
        }
      }
    }
    for (i1 = 59;; i1 = 2)
    {
      switch (i1)
      {
      default: 
        bool1 = bool2;
      }
      for (;;)
      {
        i1 = q - (--1 ^ 0xFFFFFFFF) - 1;
        s = i1 % 128;
        if (i1 % 2 == 0) {}
        return bool1;
        bool1 = str2.toLowerCase().contains((CharSequence)localObject);
        if (!bool1) {
          break;
        }
        i1 = q + 77;
        s = i1 % 128;
        localObject = str2;
        if (i1 % 2 != 0) {
          break label125;
        }
        localObject = str2;
        break label125;
        i1 = q + 64 - 1;
        s = i1 % 128;
        if (i1 % 2 == 0) {}
        i1 = Integer.valueOf(((Matcher)localObject).group(0)).intValue();
        bool1 = bool2;
        if (i1 != 0)
        {
          i1 = q;
          i2 = -101;
          i1 = ((-i2 | i1) << 1) - (i1 ^ -i2);
          s = i1 % 128;
          if (i1 % 2 == 0) {}
          i1 = s;
          i1 = (i1 | 0x63) + (i1 & 0x63);
          q = i1 % 128;
          if (i1 % 2 != 0) {}
          bool1 = true;
        }
      }
      i1 = q + 123;
      s = i1 % 128;
      if (i1 % 2 == 0) {}
      for (i1 = 1;; i1 = 0)
      {
        switch (i1)
        {
        }
        str2.toLowerCase().contains((CharSequence)localObject);
        throw new NullPointerException();
        label491:
        i1 = 0;
        break label520;
        i1 = 26;
        break;
      }
    }
  }
}
