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

public class bi
{
  private static String a;
  private static int b = 0;
  private static int c = 1;
  
  static
  {
    int i = b + 123;
    c = i % 128;
  }
  
  private static String a(Context paramContext)
  {
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.addCategory("android.intent.category.HOME");
    paramContext = paramContext.getPackageManager().resolveActivity(localIntent, 0);
    int j = 32;
    if (paramContext != null) {
      i = 0;
    } else {
      i = 32;
    }
    if (i == 0)
    {
      int k = c;
      i = k & 0x9;
      k = --(k ^ 0x9 | i);
      i = (i & k) + (k | i);
      b = i % 128;
      if (paramContext.activityInfo == null) {
        i = 95;
      } else {
        i = 61;
      }
      if (i == 61) {}
    }
    else
    {
      j = c;
      i = j & 0x69;
      j = (j | 0x69) & (i ^ 0xFFFFFFFF);
      i <<= 1;
      i = (j & i) + (j | i);
      b = i % 128;
      if (i % 2 != 0) {
        i = 57;
      } else {
        i = 42;
      }
      if (i != 42) {
        throw new NullPointerException();
      }
      return "";
    }
    if (paramContext.activityInfo.packageName.equals("android")) {
      i = 79;
    } else {
      i = 47;
    }
    if (i != 47)
    {
      i = b + 15 - 1 - 0 - 1;
      c = i % 128;
      i = b;
      i = ((i ^ 0x33 | i & 0x33) << 1) - (-((i ^ 0xFFFFFFFF) & 0x33 | i & 0xFFFFFFCC) ^ 0xFFFFFFFF) - 1;
      c = i % 128;
      if (i % 2 == 0) {
        i = j;
      } else {
        i = 30;
      }
      if (i != 30) {
        throw new NullPointerException();
      }
      return "";
    }
    paramContext = paramContext.activityInfo.packageName;
    int i = b;
    j = i & 0x43;
    i = ((i | 0x43) & (j ^ 0xFFFFFFFF)) - (j << 1 ^ 0xFFFFFFFF) - 1;
    c = i % 128;
    return paramContext;
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    i = b;
    i = (i & 0xFFFFFFF4 | (i ^ 0xFFFFFFFF) & 0xB) + ((i & 0xB) << 1);
    c = i % 128;
    if (paramContext != null) {
      i = 39;
    } else {
      i = 83;
    }
    bool2 = false;
    bool1 = false;
    int m = 0;
    int k;
    int j;
    int n;
    if (i == 39)
    {
      i = b;
      k = 80;
      i = (i & 0x50) + (i | 0x50) - 1;
      c = i % 128;
      if (i % 2 == 0) {
        i = 0;
      } else {
        i = 1;
      }
      j = 84;
      if (i != 0)
      {
        if (TextUtils.isEmpty(paramString)) {
          i = 84;
        } else {
          i = 70;
        }
        if (i != 70) {
          break label900;
        }
      }
      else
      {
        boolean bool3 = TextUtils.isEmpty(paramString);
        i = null.length;
        if (bool3) {
          i = 30;
        } else {
          i = 1;
        }
        if (i == 30) {
          break label900;
        }
      }
      if (TextUtils.isEmpty(a)) {
        i = 1;
      } else {
        i = 0;
      }
      if (i == 1)
      {
        i = c;
        i = (i & 0x3D) + (i | 0x3D);
        b = i % 128;
        a = c(paramContext);
        i = c;
        n = i ^ 0x5;
        i = ((i & 0x5 | n) << 1) - (-n ^ 0xFFFFFFFF) - 1;
        b = i % 128;
      }
      paramContext = paramContext.getContentResolver();
      if (!TextUtils.isEmpty(a)) {
        i = 56;
      } else {
        i = 51;
      }
      if (i != 51)
      {
        i = c;
        n = i & 0x13;
        i = ((i ^ 0x13 | n) << 1) - (-((i | 0x13) & (n ^ 0xFFFFFFFF)) ^ 0xFFFFFFFF) - 1;
        b = i % 128;
        if (i % 2 != 0) {
          i = 90;
        } else {
          i = 11;
        }
        if (i == 11) {}
      }
    }
    try
    {
      Uri localUri = Uri.parse(a);
      String[] arrayOfString = new String[3];
      arrayOfString[0] = "title";
      arrayOfString[1] = "iconResource";
      paramString = paramContext.query(localUri, arrayOfString, "title=?", new String[] { paramString }, null);
      if (paramString == null) {
        break label956;
      }
      i = 48;
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        bool1 = bool2;
        continue;
        i = 1;
        paramString = paramContext;
        if (i == 1) {}
      }
    }
    paramContext = paramContext.query(Uri.parse(a), new String[] { "title", "iconResource" }, "title=?", new String[] { paramString }, null);
    if (paramContext != null) {
      i = 1;
    } else {
      i = 0;
    }
    paramString = paramContext;
    if (i != 0)
    {
      n = c;
      i = n & 0xFFFFFF86 | (n ^ 0xFFFFFFFF) & 0x79;
      n = (n & 0x79) << 1;
      i = ((i | n) << 1) - (n ^ i);
      b = i % 128;
      if (i % 2 != 0) {
        i = 19;
      } else {
        i = 25;
      }
      if (i != 25)
      {
        i = paramContext.getCount();
        n = null.length;
        if (i <= 0) {
          break label983;
        }
        i = 0;
        break label985;
      }
      i = paramContext.getCount();
      if (i > 0) {
        i = 3;
      } else {
        i = 85;
      }
      paramString = paramContext;
      if (i != 85)
      {
        n = b;
        i = n & 0x3B;
        n = (n | 0x3B) & (i ^ 0xFFFFFFFF);
        i <<= 1;
        i = (n & i) + (n | i);
        c = i % 128;
        bool1 = true;
      }
    }
    for (;;)
    {
      i = m;
      if (paramContext != null) {
        i = 1;
      }
      if (i != 0)
      {
        i = c;
        i = (i & 0x47) + (i | 0x47);
        b = i % 128;
        try
        {
          bool2 = paramContext.isClosed();
          i = k;
          if (!bool2) {
            i = 11;
          }
          if (i == 11)
          {
            i = c;
            i = ((i | 0x1) << 1) - (i ^ 0x1);
            b = i % 128;
            paramContext.close();
            k = c;
            i = k & 0xFFFFFF98 | (k ^ 0xFFFFFFFF) & 0x67;
            k = (k & 0x67) << 1;
            i = (i & k) + (k | i);
            b = i % 128;
            if (i % 2 != 0) {
              break label768;
            }
          }
        }
        catch (Exception paramContext)
        {
          break label813;
        }
      }
      label768:
      k = b;
      i = k & 0x6D;
      k = --(k ^ 0x6D | i);
      i = (i ^ k) + ((k & i) << 1);
      c = i % 128;
      break label844;
      label813:
      paramString = new StringBuilder();
      paramString.append("isShortCutExist:");
      paramString.append(paramContext.getMessage());
      cg.a(paramString.toString());
      label844:
      i = b + 81 - 1;
      k = (i ^ 0xFFFFFFFF) + ((i & 0xFFFFFFFF) << 1);
      c = k % 128;
      i = j;
      if (k % 2 == 0) {
        i = 30;
      }
      if (i != 30) {
        return bool1;
      }
      i = null.length;
      return bool1;
      label900:
      i = c;
      j = i & 0x71;
      i = j + (i ^ 0x71 | j);
      b = i % 128;
      if (i % 2 != 0) {
        i = 14;
      } else {
        i = 41;
      }
      if (i != 14) {
        return false;
      }
      i = 72 / 0;
      return false;
      label956:
      i = 81;
      paramContext = paramString;
      if (i == 48) {
        break;
      }
      bool1 = false;
      paramContext = paramString;
    }
  }
  
  private static String b(Context paramContext)
  {
    int j = b;
    int i = 1;
    j = j + 31 - 1 - 1;
    c = j % 128;
    paramContext = b(paramContext, "com.android.launcher.permission.READ_SETTINGS");
    j = c;
    j = (j ^ 0x31) + ((j & 0x31) << 1);
    b = j % 128;
    if (j % 2 != 0) {
      i = 0;
    }
    if (i != 0) {
      return paramContext;
    }
    i = 78 / 0;
    return paramContext;
  }
  
  private static String b(Context paramContext, String paramString)
  {
    int i = c;
    i = ((i | 0x59) << 1) - (i ^ 0x59);
    b = i % 128;
    if (i % 2 != 0) {
      i = 1;
    } else {
      i = 0;
    }
    int j = 96;
    boolean bool;
    if (i != 1)
    {
      if (TextUtils.isEmpty(paramString)) {
        i = 0;
      } else {
        i = 1;
      }
      if (i == 0) {
        break label937;
      }
    }
    else
    {
      bool = TextUtils.isEmpty(paramString);
      i = null.length;
      if (bool) {
        i = 96;
      } else {
        i = 36;
      }
      if (i == 96) {
        break label937;
      }
    }
    for (;;)
    {
      ProviderInfo localProviderInfo;
      try
      {
        paramContext = paramContext.getPackageManager().getInstalledPackages(8);
        if (paramContext == null) {
          i = j;
        } else {
          i = 67;
        }
        if (i != 67)
        {
          j = c;
          i = (j | 0x2B) << 1;
          j = -((j ^ 0xFFFFFFFF) & 0x2B | j & 0xFFFFFFD4);
          i = (i ^ j) + ((j & i) << 1);
          b = i % 128;
          i = c + 80 - 1;
          b = i % 128;
          return "";
        }
        Iterator localIterator = paramContext.iterator();
        j = b;
        i = j ^ 0x13;
        j = (j & 0x13) << 1;
        i = (i ^ j) + ((j & i) << 1);
        c = i % 128;
        bool = localIterator.hasNext();
        if (bool) {
          i = 18;
        } else {
          i = 41;
        }
        if (i != 18)
        {
          j = b;
          i = j & 0x11;
          j = j ^ 0x11 | i;
          i = ((i | j) << 1) - (j ^ i);
          c = i % 128;
        }
        else
        {
          i = c + 90 - 1;
          b = i % 128;
          ProviderInfo[] arrayOfProviderInfo = ((PackageInfo)localIterator.next()).providers;
          if (arrayOfProviderInfo != null) {
            i = 0;
          } else {
            i = 1;
          }
          if (i != 1)
          {
            i = b + 49;
            c = i % 128;
            if (i % 2 != 0) {}
            int k = arrayOfProviderInfo.length;
            int m = b;
            j = m & 0x41;
            i = (m ^ 0x41 | j) << 1;
            j = -((m | 0x41) & (j ^ 0xFFFFFFFF));
            i = (i & j) + (j | i);
            c = i % 128;
            i = 0;
            if (i < k) {
              j = 1;
            } else {
              j = 0;
            }
            if (j != 0)
            {
              j = c + 41;
              b = j % 128;
              if (j % 2 != 0) {
                j = 0;
              } else {
                j = 1;
              }
              if (j != 0)
              {
                localProviderInfo = arrayOfProviderInfo[i];
                if (paramString.equals(localProviderInfo.readPermission)) {
                  break label983;
                }
                j = 52;
                break label986;
              }
              localProviderInfo = arrayOfProviderInfo[i];
              bool = paramString.equals(localProviderInfo.readPermission);
              j = 95 / 0;
              if (bool) {
                break label1001;
              }
              j = 44;
              break label1004;
              if (!paramString.equals(paramContext.writePermission)) {
                break label1019;
              }
              j = 1;
              break label1021;
              bool = TextUtils.isEmpty(paramContext.authority);
              if (!bool) {
                j = 0;
              } else {
                j = 1;
              }
              if (j != 1)
              {
                j = c + 47 - 1;
                j = (j & 0xFFFFFFFF) + (j | 0xFFFFFFFF);
                b = j % 128;
                if (j % 2 != 0) {
                  j = 1;
                } else {
                  j = 0;
                }
                if (j != 1)
                {
                  if (!paramContext.authority.contains(".launcher.settings")) {
                    break label1029;
                  }
                  j = 0;
                  break label1031;
                }
                bool = paramContext.authority.contains(".launcher.settings");
                j = null.length;
                if (bool) {
                  j = 88;
                } else {
                  j = 75;
                }
                if (j == 88)
                {
                  j = c;
                  i = j & 0x53;
                  j = (j | 0x53) & (i ^ 0xFFFFFFFF);
                  i = --(i << 1);
                  i = ((j | i) << 1) - (j ^ i);
                  b = i % 128;
                  paramContext = paramContext.authority;
                  i = c;
                  i = (i ^ 0x4B) + ((i & 0x4B) << 1);
                  b = i % 128;
                  if (i % 2 != 0) {
                    i = 10;
                  } else {
                    i = 97;
                  }
                  if (i != 10) {
                    return paramContext;
                  }
                  throw new NullPointerException();
                }
              }
              j = (i & 0xFFFFFFE5 | (i ^ 0xFFFFFFFF) & 0x1A) + ((i & 0x1A) << 1);
              i = j & 0xFFFFFFE7;
              j |= 0xFFFFFFE7;
              i = ((i & j) << 1) + (i ^ j);
              j = c;
              j = (j & 0x33) + (j | 0x33);
              b = j % 128;
              continue;
            }
          }
          i = c;
          i = ((i | 0x20) << 1) - (i ^ 0x20);
          i = ((i | 0xFFFFFFFF) << 1) - (i ^ 0xFFFFFFFF);
          b = i % 128;
          if (i % 2 != 0) {
            continue;
          }
          continue;
        }
        i = b;
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
        i = b + 117 - 1;
        i = ((i | 0xFFFFFFFF) << 1) - (i ^ 0xFFFFFFFF);
        c = i % 128;
        if (i % 2 == 0) {
          i = 66;
        } else {
          i = 6;
        }
        if (i != 6) {
          i = null.length;
        }
        return "";
      }
      label937:
      i = ((i | 0x6B) << 1) - (i ^ 0x6B);
      c = i % 128;
      i = c + 12 - 0 - 1;
      b = i % 128;
      return "";
      label983:
      j = 62;
      label986:
      paramContext = localProviderInfo;
      if (j != 62)
      {
        paramContext = localProviderInfo;
        continue;
        label1001:
        j = 53;
        label1004:
        paramContext = localProviderInfo;
        if (j != 53)
        {
          paramContext = localProviderInfo;
          continue;
          label1019:
          j = 0;
          label1021:
          if (j != 1)
          {
            continue;
            label1029:
            j = 1;
            label1031:
            if (j == 1) {}
          }
        }
      }
    }
  }
  
  private static String c(Context paramContext)
  {
    int j = b;
    int i = j & 0x7;
    j = j ^ 0x7 | i;
    i = (i & j) + (j | i);
    c = i % 128;
    if (i % 2 == 0) {
      i = 33;
    } else {
      i = 78;
    }
    j = 0;
    if (i != 33)
    {
      localObject1 = b(paramContext);
      if (localObject1 != null) {
        i = 53;
      } else {
        i = 79;
      }
      if (i != 53) {
        break label131;
      }
    }
    else
    {
      localObject1 = b(paramContext);
      i = 84 / 0;
      if (localObject1 != null) {
        i = 0;
      } else {
        i = 1;
      }
      if (i != 0) {
        break label131;
      }
    }
    Object localObject2 = localObject1;
    if (((String)localObject1).trim().equals(""))
    {
      label131:
      localObject2 = a(paramContext);
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append((String)localObject2);
      ((StringBuilder)localObject1).append(".permission.READ_SETTINGS");
      localObject2 = b(paramContext, ((StringBuilder)localObject1).toString());
      k = c;
      i = k & 0x29;
      k = --(k | 0x29);
      i = (i & k) + (k | i);
      b = i % 128;
    }
    if (TextUtils.isEmpty((CharSequence)localObject2)) {
      i = 1;
    } else {
      i = 0;
    }
    if (i != 1)
    {
      paramContext = (Context)localObject2;
    }
    else
    {
      i = c;
      i = ((i ^ 0x63 | i & 0x63) << 1) - ((i ^ 0xFFFFFFFF) & 0x63 | i & 0xFFFFFF9C);
      b = i % 128;
      k = Build.VERSION.SDK_INT;
      if (k < 8) {
        i = 76;
      } else {
        i = 48;
      }
      if (i != 76)
      {
        if (k < 19) {
          i = 1;
        } else {
          i = 0;
        }
        if (i != 0)
        {
          k = b + 38 - 1;
          c = k % 128;
          i = j;
          if (k % 2 == 0) {
            i = 1;
          }
          if (i != 0) {
            throw new NullPointerException();
          }
          for (;;)
          {
            paramContext = "com.android.launcher2.settings";
            break;
          }
          j = b;
          i = j & 0x5B;
          i = i - ((j ^ 0x5B | i) ^ 0xFFFFFFFF) - 1;
          c = i % 128;
          if (i % 2 == 0) {}
        }
        else
        {
          paramContext = "com.android.launcher3.settings";
          i = b;
          i = (i ^ 0x55) - ((i & 0x55) << 1 ^ 0xFFFFFFFF) - 1;
          c = i % 128;
          if (i % 2 == 0) {}
        }
      }
      else
      {
        i = b;
        i = (i & 0x56) + (i | 0x56);
        i = (i & 0xFFFFFFFF) + (i | 0xFFFFFFFF);
        c = i % 128;
        paramContext = "com.android.launcher.settings";
        i = b;
        i = (i ^ 0x73) - ((i & 0x73) << 1 ^ 0xFFFFFFFF) - 1;
        c = i % 128;
      }
    }
    Object localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append("content://");
    ((StringBuilder)localObject1).append(paramContext);
    ((StringBuilder)localObject1).append("/favorites?notify=true");
    paramContext = ((StringBuilder)localObject1).toString();
    j = c;
    int k = j | 0x53;
    i = k << 1;
    j = -((j & 0x53 ^ 0xFFFFFFFF) & k);
    i = (i & j) + (j | i);
    b = i % 128;
    if (i % 2 != 0) {
      i = 89;
    } else {
      i = 93;
    }
    if (i != 93) {
      throw new NullPointerException();
    }
    return paramContext;
  }
}
