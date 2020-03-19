package com.adt.a;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build.VERSION;
import android.text.TextUtils;
import java.util.Iterator;
import java.util.List;

public class ce
{
  private static int a;
  private static String c;
  private static int d = 0;
  
  static
  {
    a = 1;
    c = null;
    int i = d;
    int j = --2;
    i = ((i & --2) << 1) + (j ^ i);
    j = -1;
    i = (i | j) + (i & j);
    a = i % 128;
    if (i % 2 == 0) {}
    for (i = 89;; i = 48)
    {
      switch (i)
      {
      default: 
        return;
      }
      i = 26 / 0;
      return;
    }
  }
  
  public ce() {}
  
  private static String b(Context paramContext)
  {
    int k = 0;
    int j = 0;
    Object localObject = new Intent("android.intent.action.MAIN");
    ((Intent)localObject).addCategory("android.intent.category.HOME");
    paramContext = paramContext.getPackageManager().resolveActivity((Intent)localObject, 0);
    if (paramContext != null)
    {
      i = 2;
      switch (i)
      {
      default: 
        k = d;
        i = k & 0x17;
        int m = -(k | 0x17);
        k = -m;
        i = (-m | i) + (k & i);
        a = i % 128;
        if (i % 2 != 0) {
          break;
        }
      }
    }
    for (int i = j;; i = 1)
    {
      switch (i)
      {
      default: 
        i = 50 / 0;
        return "";
        label132:
        i = 1;
        switch (i)
        {
        default: 
          label134:
          if (paramContext.activityInfo != null) {}
          break;
        }
        break;
      }
      for (i = 72;; i = 68) {
        switch (i)
        {
        default: 
          break;
        case 68: 
          label183:
          if (paramContext.activityInfo.packageName.equals("android")) {}
          for (i = 3;; i = 47)
          {
            switch (i)
            {
            default: 
              i = a;
              i = (i & 0xFFFFFFAC | (i ^ 0xFFFFFFFF) & 0x53) - (--((i & 0x53) << 1) ^ 0xFFFFFFFF) - 1;
              d = i % 128;
              if (i % 2 == 0) {
                break;
              }
            }
            for (i = k;; i = 1)
            {
              switch (i)
              {
              default: 
                throw new NullPointerException();
                label292:
                j = d;
                k = -59;
                i = -k & j;
                k = -k;
                j = j & k | j ^ k;
                i = (j | i) + (i & j);
                a = i % 128;
                if (i % 2 != 0) {
                  break;
                }
              }
              for (i = 57;; i = 82) {
                switch (i)
                {
                default: 
                  return "";
                  break label292;
                  paramContext = paramContext.activityInfo.packageName;
                  j = d;
                  i = j & 0xFFFFFFAA | (j ^ 0xFFFFFFFF) & 0x55;
                  j = (j & 0x55) << 1;
                  i = ((i | j) << 1) - (j ^ i);
                  a = i % 128;
                  if (i % 2 == 0)
                  {
                    i = 93;
                    switch (i)
                    {
                    default: 
                      return paramContext;
                      return "";
                      i = a + 14 - 1;
                      d = i % 128;
                      if (i % 2 == 0) {
                        break label132;
                      }
                      i = 0;
                      break label134;
                      localObject = paramContext.activityInfo;
                      i = 93 / 0;
                      if (localObject != null) {}
                      break;
                    }
                  }
                case 57: 
                  label424:
                  for (i = 31;; i = 60)
                  {
                    switch (i)
                    {
                    }
                    break label183;
                    i = null.length;
                    return "";
                    throw new NullPointerException();
                    i = 20;
                    break;
                    i = 90;
                    break label424;
                  }
                }
              }
            }
          }
        }
      }
    }
  }
  
  private static String c(Context paramContext)
  {
    int i = a + 114;
    int j = -1;
    i = ((i & j) << 1) + (i ^ j);
    d = i % 128;
    if (i % 2 != 0) {}
    paramContext = c(paramContext, "com.android.launcher.permission.READ_SETTINGS");
    i = d;
    j = -85;
    int k = -j;
    k = (k | i) & (i & k ^ 0xFFFFFFFF);
    i = (i & -j) << 1;
    i = (i | k) + (k & i);
    a = i % 128;
    if (i % 2 == 0) {}
    return paramContext;
  }
  
  private static String c(Context paramContext, String paramString)
  {
    int k = 17;
    int m = 1;
    int j = 1;
    int n = d;
    int i = n & 0x73;
    int i1 = -(n | 0x73);
    n = -i1;
    i = (-i1 | i) + (n & i);
    a = i % 128;
    label89:
    label108:
    label124:
    label177:
    label196:
    label239:
    label310:
    Iterator localIterator;
    label407:
    boolean bool;
    label444:
    label558:
    label560:
    label603:
    label673:
    label692:
    label717:
    label754:
    label756:
    Object localObject2;
    label798:
    Object localObject1;
    if (i % 2 == 0)
    {
      i = 64;
      switch (i)
      {
      default: 
        if (TextUtils.isEmpty(paramString))
        {
          i = 0;
          switch (i)
          {
          }
          for (;;)
          {
            try
            {
              paramContext = paramContext.getPackageManager().getInstalledPackages(8);
              if (paramContext != null) {
                break label1500;
              }
              i = 0;
              switch (i)
              {
              default: 
                i = d;
                i = ((i | 0xB) << 1) - (i ^ 0xB);
                a = i % 128;
                if (i % 2 != 0) {
                  break label1484;
                }
                i = k;
                switch (i)
                {
                default: 
                  m = a;
                  i = -29;
                  k = -i;
                  i = ((m & -i) << 1) + (k ^ m);
                  d = i % 128;
                  if (i % 2 == 0) {
                    break label1435;
                  }
                  i = j;
                  switch (i)
                  {
                  default: 
                    return "";
                    j = a;
                    i = --82;
                    j = ((j | i) << 1) - (j ^ i);
                    i = -1;
                    i = (j | -1) + (i & j);
                    d = i % 128;
                    if (i % 2 == 0) {
                      break label1430;
                    }
                    i = m;
                    switch (i)
                    {
                    default: 
                      return "";
                    }
                    break;
                  }
                  break;
                }
              case 1: 
                localIterator = paramContext.iterator();
                j = d;
                k = -95;
                i = (-k | j) << 1;
                k = -k;
                j = (j ^ 0xFFFFFFFF) & k | (k ^ 0xFFFFFFFF) & j;
                k = -j;
                i = ((-j & i) << 1) + (k ^ i);
                a = i % 128;
                if (i % 2 != 0) {
                  break label1456;
                }
                i = 1;
                switch (i)
                {
                }
                bool = localIterator.hasNext();
                if (!bool) {
                  break label1440;
                }
                i = 1;
                switch (i)
                {
                default: 
                  i = d;
                  k = -87;
                  j = -k;
                  k = -k;
                  i = ((j & i | i ^ j) << 1) - ((i | k) & (i & k ^ 0xFFFFFFFF));
                  a = i % 128;
                  if (i % 2 != 0) {}
                  break;
                }
                break;
              }
            }
            catch (Exception paramContext)
            {
              paramContext.printStackTrace();
              continue;
              return paramContext;
            }
            i = a;
            k = -39;
            j = -k;
            i = (i | -k) + (j & i);
            d = i % 128;
            if (i % 2 != 0) {}
            return "";
            i = 1;
            switch (i)
            {
            default: 
              paramContext = ((PackageInfo)localIterator.next()).providers;
              if (paramContext == null) {
                break label1445;
              }
              i = 0;
              break label1510;
              k = paramContext.length;
              j = a;
              m = -13;
              i = -m;
              m = -m;
              i = (i & (j ^ 0xFFFFFFFF) | (i ^ 0xFFFFFFFF) & j) - ((j & m) << 1 ^ 0xFFFFFFFF) - 1;
              d = i % 128;
              if (i % 2 != 0) {}
              i = 0;
              if (i >= k) {
                break label1412;
              }
              j = 23;
              switch (j)
              {
              default: 
                i = d + 92 - 1;
                a = i % 128;
                if (i % 2 != 0) {
                  break label1467;
                }
                i = 0;
                switch (i)
                {
                }
                i = --4;
                i = -((i ^ 0xFFFFFFFF) & 0xFFFFFFFF | i & 0x0);
              }
              break;
            }
          }
          j = 0;
          switch (j)
          {
          default: 
            localObject2 = paramContext[i];
            if (!paramString.equals(localObject2.readPermission))
            {
              j = 1;
              break label1531;
              if (paramString.equals(localObject1.writePermission)) {
                j = 31;
              }
            }
            break;
          }
        }
        break;
      }
    }
    for (;;)
    {
      bool = TextUtils.isEmpty(localObject1.authority);
      if (!bool)
      {
        j = 33;
        switch (j)
        {
        default: 
          i = ((i | 0x1) << 1) - (i ^ 0x1);
          j = d;
          j = ((j & 0x7D) << 1) + ((j & 0x7D ^ 0xFFFFFFFF) & (j | 0x7D));
          a = j % 128;
          if (j % 2 == 0)
          {
            j = 43;
            switch (j)
            {
            }
          }
          break;
        case 33: 
          label834:
          label902:
          j = a;
          n = -109;
          m = -n;
          j = (j | -n) + (m & j);
          d = j % 128;
          if (j % 2 != 0) {}
          bool = localObject1.authority.contains(".launcher.settings");
          if (bool)
          {
            j = 79;
            label982:
            switch (j)
            {
            }
            k = a;
            j = -103;
            i = -j;
            i = ((i | k) & (k & i ^ 0xFFFFFFFF)) - ((k & -j) << 1 ^ 0xFFFFFFFF) - 1;
            d = i % 128;
            if (i % 2 != 0) {}
            paramContext = localObject1.authority;
            i = a;
            j = --79;
            k = (j ^ 0xFFFFFFFF) & 0xFFFFFFFF | j & 0x0;
            j = -k;
            i = ((i & -k) << 1) + (j ^ i);
            j = -1;
            i = ((i | j) << 1) - (j ^ i);
            d = i % 128;
            if (i % 2 == 0) {
              break label1472;
            }
            i = 82;
            switch (i)
            {
            default: 
              label1122:
              throw new NullPointerException();
            }
            break;
            throw new NullPointerException();
            j = a;
            k = -31;
            i = -k;
            i &= j;
            k = -k;
            j = -(j & k | j ^ k);
            k = -j;
            i = (-j | i) + (k & i);
            d = i % 128;
            if (i % 2 == 0) {
              break label558;
            }
            i = 0;
            break label560;
            paramContext = ((PackageInfo)localIterator.next()).providers;
            i = 12 / 0;
            if (paramContext == null) {
              break label1505;
            }
          }
          break;
        }
      }
      label1412:
      label1430:
      label1435:
      label1440:
      label1445:
      label1456:
      label1467:
      label1472:
      label1484:
      label1500:
      label1505:
      for (i = 0;; i = 1)
      {
        switch (i)
        {
        }
        break label692;
        i = null.length;
        return "";
        bool = TextUtils.isEmpty(paramString);
        i = null.length;
        if (bool) {}
        for (i = 1;; i = 0)
        {
          switch (i)
          {
          }
          break label108;
          j = a;
          j = ((--18 | j) << 1) - (j ^ --18);
          m = -1;
          j = ((j & m) << 1) + (j ^ m);
          d = j % 128;
          if (j % 2 == 0) {
            break label754;
          }
          j = 1;
          break label756;
          paramContext = paramContext[i];
          paramString.equals(paramContext.readPermission);
          throw new NullPointerException();
          i = null.length;
          break label196;
          i = 1;
          break label89;
          j = 38;
          break label673;
          j = 64;
          break label1563;
          j = 59;
          break label982;
          i = 0;
          break label310;
          i = 0;
          break label239;
          i = 0;
          break label444;
          i = 1;
          break label1510;
          i = 49;
          break;
          i = 0;
          break label407;
          j = 91;
          break label834;
          i = 1;
          break label717;
          i = 87;
          break label1122;
          j = 17;
          break label902;
          i = 43;
          break label177;
          j = 0;
          break label1531;
        }
        i = 1;
        break label124;
      }
      label1510:
      switch (i)
      {
      }
      break label603;
      label1531:
      localObject1 = localObject2;
      switch (j)
      {
      }
      localObject1 = localObject2;
      break label798;
      label1563:
      switch (j)
      {
      }
    }
  }
  
  private static String d(Context paramContext)
  {
    int n = 62;
    int m = 0;
    int k = 0;
    int j = 1;
    int i = d;
    i = (i & 0x4B | i ^ 0x4B) + (i & 0x4B);
    a = i % 128;
    if (i % 2 == 0) {}
    String str = c(paramContext);
    Object localObject;
    if (str != null)
    {
      i = 62;
      switch (i)
      {
      default: 
        for (;;)
        {
          localObject = b(paramContext);
          paramContext = c(paramContext, (String)localObject + ".permission.READ_SETTINGS");
          i = a;
          i = ((i | 0x65) << 1) - (i ^ 0x65);
          d = i % 128;
          if (i % 2 == 0) {
            break;
          }
          i = n;
          label140:
          localObject = paramContext;
          switch (i)
          {
          default: 
            localObject = paramContext;
          }
          label178:
          label274:
          label276:
          do
          {
            if (!TextUtils.isEmpty((CharSequence)localObject)) {
              break;
            }
            i = 98;
            switch (i)
            {
            default: 
              paramContext = (Context)localObject;
              paramContext = "content://" + paramContext + "/favorites?notify=true";
              j = a;
              i = (j | 0x65) << 1;
              j = -((j ^ 0xFFFFFFFF) & 0x65 | j & 0xFFFFFF9A);
              i = ((j & i) << 1) + (i ^ j);
              d = i % 128;
              if (i % 2 != 0) {}
              return paramContext;
              i = 1;
              switch (i)
              {
              default: 
                localObject = str;
              }
              break;
            }
          } while (!str.trim().equals(""));
        }
        n = d;
        int i1 = -121;
        i = -i1;
        i &= n;
        i1 = -i1;
        n = -(n & i1 | n ^ i1);
        i = ((-n | i) << 1) - (-n ^ i);
        a = i % 128;
        if (i % 2 == 0) {}
        n = Build.VERSION.SDK_INT;
        if (n >= 8) {
          break;
        }
      }
    }
    for (i = 0;; i = 1)
    {
      switch (i)
      {
      default: 
        if (n < 19)
        {
          i = 1;
          switch (i)
          {
          default: 
            label425:
            i = d;
            k = --59;
            m = (k | 0xFFFFFFFF) & (k & 0xFFFFFFFF ^ 0xFFFFFFFF);
            k = -m;
            i = (i | -m) + (k & i) - (-1 ^ 0xFFFFFFFF) - 1;
            a = i % 128;
            if (i % 2 != 0) {}
            break;
          }
        }
        break;
      }
      for (i = j;; i = 0) {
        switch (i)
        {
        default: 
          paramContext = "com.android.launcher2.settings";
          i = null.length;
          break;
          localObject = "com.android.launcher.settings";
          j = d;
          i = --106;
          i = ((j & --106) << 1) + (i ^ j) - (-1 ^ 0xFFFFFFFF) - 1;
          a = i % 128;
          paramContext = (Context)localObject;
          if (i % 2 != 0) {
            break;
          }
          paramContext = (Context)localObject;
          break;
        case 0: 
          label529:
          paramContext = "com.android.launcher2.settings";
          break;
          localObject = "com.android.launcher3.settings";
          j = d;
          i = (j & 0x33 ^ 0xFFFFFFFF) & (j | 0x33);
          j = (j & 0x33) << 1;
          i = ((j & i) << 1) + (i ^ j);
          a = i % 128;
          if (i % 2 == 0)
          {
            i = k;
            label642:
            paramContext = (Context)localObject;
            switch (i)
            {
            }
            paramContext = (Context)localObject;
            break;
            i = a;
            i = ((i & 0x4D ^ 0xFFFFFFFF) & (i | 0x4D)) - (--((i & 0x4D) << 1) ^ 0xFFFFFFFF) - 1;
            d = i % 128;
            if (i % 2 == 0) {
              break label274;
            }
            i = 0;
            break label276;
            str.trim().equals("");
            throw new NullPointerException();
            i = a - (--105 ^ 0xFFFFFFFF) - 1;
            d = i % 128;
            if (i % 2 == 0) {
              break label824;
            }
          }
          label824:
          for (i = m;; i = 1)
          {
            switch (i)
            {
            }
            i = 32 / 0;
            break label529;
            i = 41;
            break label140;
            i = 1;
            break label642;
            i = 0;
            break label425;
            i = 33;
            break label178;
            i = 28;
            break;
          }
        }
      }
    }
  }
  
  public static boolean d(Context paramContext, String paramString)
  {
    int k = 24;
    int j = 1;
    int i = a;
    int n = -39;
    int m = -n;
    n = -n;
    i = ((m & i | i ^ m) << 1) - ((i ^ 0xFFFFFFFF) & n | (n ^ 0xFFFFFFFF) & i);
    d = i % 128;
    if ((i % 2 == 0) || (paramContext != null))
    {
      i = 84;
      switch (i)
      {
      default: 
        label72:
        m = d;
        i = (m ^ 0x1D | m & 0x1D) << 1;
        m = (m ^ 0xFFFFFFFF) & 0x1D | m & 0xFFFFFFE2;
        n = -m;
        i = (-m | i) + (n & i);
        a = i % 128;
        if ((i % 2 == 0) && (!TextUtils.isEmpty(paramString))) {
          break;
        }
      }
    }
    for (i = 0;; i = 1)
    {
      switch (i)
      {
      default: 
        if (TextUtils.isEmpty(c))
        {
          i = 1;
          switch (i)
          {
          default: 
            label216:
            paramContext = paramContext.getContentResolver();
            if (TextUtils.isEmpty(c)) {}
            break;
          }
        }
        break;
      }
      for (i = k;; i = 76)
      {
        boolean bool2;
        switch (i)
        {
        default: 
          bool2 = false;
          i = a;
          j = -105;
          k = -j;
          i = ((i & -j) << 1) + (k & (i ^ 0xFFFFFFFF) | (k ^ 0xFFFFFFFF) & i);
          d = i % 128;
          if (i % 2 != 0) {}
          return bool2;
          c = d(paramContext);
          break;
          i = 1;
          switch (i)
          {
          }
        case 24: 
          for (;;)
          {
            try
            {
              label255:
              label315:
              label317:
              paramString = paramContext.query(Uri.parse(c), new String[] { "title", "iconResource" }, "title=?", new String[] { paramString }, null);
              if (paramString == null) {
                continue;
              }
              i = 0;
              paramContext = paramString;
              switch (i)
              {
              default: 
                paramContext = paramString;
                bool1 = false;
                if (paramContext == null) {
                  continue;
                }
                i = 0;
                switch (i)
                {
                default: 
                  i = d + 105;
                  a = i % 128;
                  if (i % 2 != 0) {
                    continue;
                  }
                  i = 93;
                  bool2 = bool1;
                  switch (i)
                  {
                  }
                  bool2 = bool1;
                  break label255;
                  i = 35;
                  paramString = paramContext;
                  switch (i)
                  {
                  }
                  break;
                }
              case 0: 
                i = paramContext.getCount();
                if (i <= 0) {
                  continue;
                }
                i = 0;
                paramString = paramContext;
                switch (i)
                {
                }
                i = a;
                k = --77;
                i -= ((k ^ 0xFFFFFFFF) & 0xFFFFFFFF | k & 0x0);
                k = -1;
                i = ((i & k) << 1) + (i ^ k);
                d = i % 128;
                if (i % 2 == 0) {
                  continue;
                }
                i = 57;
                switch (i)
                {
                }
                i = a;
                m = -109;
                k = -m;
                m = -m;
                i = (k & i) - ((i & m | i ^ m) ^ 0xFFFFFFFF) - 1;
                d = i % 128;
                if (i % 2 == 0) {
                  continue;
                }
                i = 58;
                switch (i)
                {
                default: 
                  bool1 = true;
                  continue;
                  k = a;
                  i = k & 0x5B;
                  k = -(k | 0x5B);
                  m = -k;
                  i = ((-k & i) << 1) + (m ^ i);
                  d = i % 128;
                  if (i % 2 != 0) {}
                  try
                  {
                    bool2 = paramContext.isClosed();
                    if (bool2) {
                      continue;
                    }
                    i = j;
                    switch (i)
                    {
                    default: 
                      break;
                    case 1: 
                      j = d;
                      k = -73;
                      i = -k;
                      i = (i & j | j ^ i) << 1;
                      k = -k;
                      j = (j ^ 0xFFFFFFFF) & k | (k ^ 0xFFFFFFFF) & j;
                      k = -j;
                      i = (-j | i) + (k & i);
                      a = i % 128;
                      if (i % 2 != 0) {
                        continue;
                      }
                      i = 73;
                      switch (i)
                      {
                      default: 
                        paramContext.close();
                        i = 24 / 0;
                        break;
                      case 57: 
                        paramContext.close();
                      }
                      break;
                    }
                  }
                  catch (Exception paramContext) {}
                }
                break;
              }
            }
            catch (Exception paramContext)
            {
              Uri localUri;
              String[] arrayOfString;
              boolean bool1 = false;
              continue;
            }
            db.d("isShortCutExist:" + paramContext.getMessage());
            bool2 = bool1;
            break label255;
            i = a;
            k = -75;
            j = -k;
            k = -k;
            i = ((j & i | i ^ j) << 1) - ((i | k) & (i & k ^ 0xFFFFFFFF));
            d = i % 128;
            if (i % 2 != 0) {}
            return false;
            bool1 = true;
            continue;
            i = d;
            m = -21;
            n = -m;
            i = (i | -m) + (n & i);
            a = i % 128;
            if (i % 2 == 0)
            {
              i = 1;
              switch (i)
              {
              }
              c = d(paramContext);
              i = 1 / 0;
              break label216;
              i = d;
              k = --6;
              i = (i | --6) + (k & i);
              k = -1;
              i = ((i & k) << 1) + (i ^ k);
              a = i % 128;
              if (i % 2 != 0) {
                break label315;
              }
              i = 0;
              break label317;
              localUri = Uri.parse(c);
              arrayOfString = new String[2];
              arrayOfString[0] = "title";
              arrayOfString[0] = "iconResource";
              paramContext = paramContext.query(localUri, arrayOfString, "title=?", new String[] { paramString }, null);
              if (paramContext == null) {
                continue;
              }
              i = 88;
              continue;
              i = 1;
              continue;
              i = 1;
              continue;
            }
            i = 0;
            continue;
            i = 1;
            continue;
            i = 13;
            continue;
            i = 1;
            continue;
            i = 0;
            break;
            i = 0;
            continue;
            i = 85;
            continue;
            i = 57;
          }
          i = 99;
          break label72;
        }
      }
    }
  }
}
