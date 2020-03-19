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

public class cf
{
  private static int a = 1;
  private static String b;
  private static int d;
  
  static
  {
    int j = d;
    int k = j & 0x73;
    int i = (j ^ 0x73 | k) << 1;
    j = -((j | 0x73) & (k ^ 0xFFFFFFFF));
    i = (i & j) + (j | i);
    a = i % 128;
  }
  
  public cf() {}
  
  private static String a(Context paramContext)
  {
    int i = a;
    i = ((i ^ 0x7B | i & 0x7B) << 1) - (-((i ^ 0xFFFFFFFF) & 0x7B | i & 0xFFFFFF84) ^ 0xFFFFFFFF) - 1;
    d = i % 128;
    if (i % 2 != 0) {
      i = 95;
    } else {
      i = 51;
    }
    if (i != 95) {
      return a(paramContext, "com.android.launcher.permission.READ_SETTINGS");
    }
    paramContext = a(paramContext, "com.android.launcher.permission.READ_SETTINGS");
    i = null.length;
    return paramContext;
  }
  
  private static String a(Context paramContext, String paramString)
  {
    int j = d;
    int i = j & 0xFFFFFFC2 | (j ^ 0xFFFFFFFF) & 0x3D;
    int k = 1;
    j = (j & 0x3D) << 1;
    i = (i & j) + (j | i);
    a = i % 128;
    boolean bool = TextUtils.isEmpty(paramString);
    j = 0;
    if (bool) {
      i = 1;
    } else {
      i = 0;
    }
    if (i != 0)
    {
      i = a;
      i = (i & 0x44) + (i | 0x44);
      i = ((i | 0xFFFFFFFF) << 1) - (i ^ 0xFFFFFFFF);
      d = i % 128;
      i = d;
      j = i ^ 0x3B;
      i = (i & 0x3B | j) << 1;
      j = -j;
      i = ((i | j) << 1) - (i ^ j);
      a = i % 128;
      return "";
    }
    for (;;)
    {
      try
      {
        paramContext = paramContext.getPackageManager().getInstalledPackages(8);
        if (paramContext == null) {
          i = 29;
        } else {
          i = 33;
        }
        if (i != 33)
        {
          k = a;
          i = k & 0xFFFFFFC4 | (k ^ 0xFFFFFFFF) & 0x3B;
          k = (k & 0x3B) << 1;
          i = (i ^ k) + ((k & i) << 1);
          d = i % 128;
          if (i % 2 != 0) {
            i = 62;
          } else {
            i = 78;
          }
          if (i == 62) {
            i = null.length;
          }
          k = a;
          i = k & 0x3F;
          k = --(k ^ 0x3F | i);
          i = (i ^ k) + ((k & i) << 1);
          d = i % 128;
          if (i % 2 != 0) {
            i = j;
          } else {
            i = 1;
          }
          if (i != 1) {
            throw new NullPointerException();
          }
          return "";
        }
        Iterator localIterator = paramContext.iterator();
        i = d;
        i = (i ^ 0x64) + ((i & 0x64) << 1);
        i = (i ^ 0xFFFFFFFF) + ((i & 0xFFFFFFFF) << 1);
        a = i % 128;
        if (i % 2 != 0) {}
        bool = localIterator.hasNext();
        if (bool) {
          i = 0;
        } else {
          i = 1;
        }
        if (i != 1)
        {
          i = d;
          i = (i & 0xFFFFFFD6 | (i ^ 0xFFFFFFFF) & 0x29) + ((i & 0x29) << 1);
          a = i % 128;
          if (i % 2 == 0) {
            i = 0;
          } else {
            i = 1;
          }
          if (i != 1)
          {
            paramContext = ((PackageInfo)localIterator.next()).providers;
            i = 17 / 0;
            if (paramContext == null) {
              break label1124;
            }
            i = 0;
            break label1126;
          }
          paramContext = ((PackageInfo)localIterator.next()).providers;
          if (paramContext == null) {
            break label1133;
          }
          i = 7;
          break label1135;
          int m = paramContext.length;
          i = d;
          i = (i & 0x53) + (i | 0x53);
          a = i % 128;
          if (i % 2 != 0) {}
          i = 0;
          if (i < m) {
            j = 0;
          } else {
            j = 1;
          }
          if (j != 1)
          {
            int n = a;
            j = n & 0xFFFFFFF2 | (n ^ 0xFFFFFFFF) & 0xD;
            n = --((n & 0xD) << 1);
            j = (j ^ n) + ((n & j) << 1);
            d = j % 128;
            if (j % 2 != 0) {
              j = 1;
            } else {
              j = 0;
            }
            if (j != 0)
            {
              paramContext = paramContext[i];
              paramString.equals(paramContext.readPermission);
              throw new NullPointerException();
            }
            Object localObject2 = paramContext[i];
            bool = paramString.equals(localObject2.readPermission);
            if (!bool) {
              j = 24;
            } else {
              j = 18;
            }
            Object localObject1 = localObject2;
            if (j != 18)
            {
              localObject1 = localObject2;
              j = a;
              j = ((j | 0x3D) << 1) - (j ^ 0x3D);
              d = j % 128;
              if (j % 2 != 0) {
                j = 0;
              } else {
                j = 1;
              }
              if (j != 0)
              {
                if (!paramString.equals(localObject1.writePermission)) {
                  break label1143;
                }
                j = 0;
                break label1145;
              }
              bool = paramString.equals(localObject1.writePermission);
              j = 74 / 0;
              if (!bool) {
                break label1152;
              }
              j = 0;
              break label1154;
            }
            bool = TextUtils.isEmpty(localObject1.authority);
            if (!bool) {
              j = 0;
            } else {
              j = 1;
            }
            if (j != 1)
            {
              n = a;
              j = n & 0x55;
              j += (n ^ 0x55 | j);
              d = j % 128;
              if (j % 2 != 0) {
                j = 67;
              } else {
                j = 40;
              }
              if (j != 67)
              {
                if (!localObject1.authority.contains(".launcher.settings")) {
                  break label1161;
                }
                j = 0;
                break label1163;
              }
              bool = localObject1.authority.contains(".launcher.settings");
              j = 61 / 0;
              if (!bool) {
                break label1171;
              }
              j = 61;
              break label1174;
              paramContext = localObject1.authority;
              i = d;
              i = (i ^ 0x73) + ((i & 0x73) << 1);
              a = i % 128;
              if (i % 2 == 0) {
                i = k;
              } else {
                i = 0;
              }
              if (i != 0)
              {
                i = 74 / 0;
                return paramContext;
              }
              return paramContext;
            }
            i += 2;
            i = (i | 0xFFFFFFFF) + (i & 0xFFFFFFFF);
            j = d;
            j = ((j | 0x65) << 1) - (j ^ 0x65);
            a = j % 128;
            if (j % 2 == 0) {
              continue;
            }
            continue;
          }
          j = d;
          i = (j | 0x33) << 1;
          j = -((j ^ 0xFFFFFFFF) & 0x33 | j & 0xFFFFFFCC);
          i = (i & j) + (j | i);
          a = i % 128;
          if (i % 2 == 0) {
            continue;
          }
          continue;
        }
        i = a;
        i = (i & 0x2F) + (i | 0x2F);
        d = i % 128;
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
      }
      j = a;
      i = (j ^ 0x3F | j & 0x3F) << 1;
      j = -((j ^ 0xFFFFFFFF) & 0x3F | j & 0xFFFFFFC0);
      i = (i ^ j) + ((j & i) << 1);
      d = i % 128;
      return "";
      label1124:
      i = 1;
      label1126:
      if (i != 0)
      {
        continue;
        label1133:
        i = 3;
        label1135:
        if (i != 3)
        {
          continue;
          label1143:
          j = 1;
          label1145:
          if (j != 0)
          {
            continue;
            label1152:
            j = 1;
            label1154:
            if (j != 0)
            {
              continue;
              label1161:
              j = 1;
              label1163:
              if (j != 1)
              {
                continue;
                label1171:
                j = 69;
                label1174:
                if (j == 61) {}
              }
            }
          }
        }
      }
    }
  }
  
  private static String b(Context paramContext)
  {
    int i = a;
    i = (i ^ 0x79) + ((i & 0x79) << 1);
    d = i % 128;
    String str = a(paramContext);
    if (str != null) {
      i = 89;
    } else {
      i = 77;
    }
    int k = 45;
    if (i != 77)
    {
      i = a;
      i = ((i ^ 0x2D | i & 0x2D) << 1) - ((i ^ 0xFFFFFFFF) & 0x2D | i & 0xFFFFFFD2);
      d = i % 128;
      localObject = str;
      if (!str.trim().equals("")) {}
    }
    else
    {
      str = d(paramContext);
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(str);
      ((StringBuilder)localObject).append(".permission.READ_SETTINGS");
      localObject = a(paramContext, ((StringBuilder)localObject).toString());
      i = d;
      j = i & 0x53;
      i = (i | 0x53) & (j ^ 0xFFFFFFFF);
      j = --(j << 1);
      i = ((i | j) << 1) - (i ^ j);
      a = i % 128;
    }
    if (TextUtils.isEmpty((CharSequence)localObject)) {
      i = 41;
    } else {
      i = 38;
    }
    if (i != 41)
    {
      paramContext = (Context)localObject;
    }
    else
    {
      i = a + 30;
      i = (i & 0xFFFFFFFF) + (i | 0xFFFFFFFF);
      d = i % 128;
      if (i % 2 != 0) {
        i = 92;
      } else {
        i = 95;
      }
      int m = 0;
      if (i != 95)
      {
        i = Build.VERSION.SDK_INT;
        if (i < 31) {
          j = 94;
        } else {
          j = 54;
        }
        if (j == 54) {
          break label362;
        }
      }
      else
      {
        i = Build.VERSION.SDK_INT;
        if (i < 8) {
          j = 64;
        } else {
          j = 32;
        }
        if (j == 32) {
          break label362;
        }
      }
      paramContext = "com.android.launcher.settings";
      i = a;
      i = ((i | 0x8) << 1) - (i ^ 0x8) - 0 - 1;
      d = i % 128;
      if (i % 2 == 0)
      {
        break label466;
        label362:
        if (i < 19) {
          i = 44;
        } else {
          i = 34;
        }
        if (i != 44)
        {
          paramContext = "com.android.launcher3.settings";
          i = d;
          i = (i ^ 0x4F) + ((i & 0x4F) << 1);
          a = i % 128;
        }
        else
        {
          j = d;
          i = j & 0x5D;
          j = i + (j ^ 0x5D | i);
          a = j % 128;
          i = m;
          if (j % 2 == 0) {
            i = 1;
          }
          if (i != 0) {
            i = null.length;
          }
          for (;;)
          {
            paramContext = "com.android.launcher2.settings";
            break;
          }
        }
      }
    }
    label466:
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("content://");
    ((StringBuilder)localObject).append(paramContext);
    ((StringBuilder)localObject).append("/favorites?notify=true");
    paramContext = ((StringBuilder)localObject).toString();
    int j = d + 56 - 1;
    a = j % 128;
    i = k;
    if (j % 2 == 0) {
      i = 14;
    }
    if (i != 14) {
      return paramContext;
    }
    throw new NullPointerException();
  }
  
  private static String d(Context paramContext)
  {
    Object localObject = new Intent("android.intent.action.MAIN");
    ((Intent)localObject).addCategory("android.intent.category.HOME");
    paramContext = paramContext.getPackageManager().resolveActivity((Intent)localObject, 0);
    if (paramContext != null) {
      i = 0;
    } else {
      i = 1;
    }
    int j;
    if (i == 0)
    {
      i = d;
      j = i ^ 0x1;
      i = ((i & 0x1 | j) << 1) - j;
      a = i % 128;
      if (paramContext.activityInfo == null) {
        i = 91;
      } else {
        i = 37;
      }
      if (i != 91)
      {
        if (paramContext.activityInfo.packageName.equals("android")) {
          i = 57;
        } else {
          i = 6;
        }
        if (i != 6)
        {
          i = a;
          i = (i & 0x4D) - ((i | 0x4D) ^ 0xFFFFFFFF) - 1;
          d = i % 128;
          i = a + 57 - 1;
          i = (i & 0xFFFFFFFF) + (i | 0xFFFFFFFF);
          d = i % 128;
          return "";
        }
        paramContext = paramContext.activityInfo.packageName;
        i = a + 80 - 1;
        d = i % 128;
        if (i % 2 != 0) {
          i = 0;
        } else {
          i = 1;
        }
        localObject = paramContext;
        if (i == 1) {}
      }
    }
    for (int i = 41;; i = 96)
    {
      i /= 0;
      localObject = paramContext;
      return localObject;
      paramContext = "";
      j = a;
      i = j & 0xFFFFFFB6 | (j ^ 0xFFFFFFFF) & 0x49;
      j = --((j & 0x49) << 1);
      i = (i ^ j) + ((j & i) << 1);
      d = i % 128;
      if (i % 2 != 0) {
        i = 0;
      } else {
        i = 1;
      }
      if (i == 1) {
        break;
      }
    }
    return "";
  }
  
  public static boolean d(Context paramContext, String paramString)
  {
    int j = a;
    int i = (j ^ 0x21 | j & 0x21) << 1;
    j = -((j ^ 0xFFFFFFFF) & 0x21 | j & 0xFFFFFFDE);
    i = ((i | j) << 1) - (j ^ i);
    d = i % 128;
    if (i % 2 != 0) {
      i = 53;
    } else {
      i = 45;
    }
    boolean bool1;
    if (i != 45)
    {
      if (paramContext != null) {
        i = 1;
      } else {
        i = 0;
      }
      if (i != 0)
      {
        bool1 = true;
      }
      else
      {
        bool2 = true;
        break label803;
      }
    }
    else
    {
      if (paramContext != null) {
        i = 36;
      } else {
        i = 5;
      }
      if (i == 5) {
        break label800;
      }
      bool1 = false;
    }
    if (TextUtils.isEmpty(paramString)) {
      i = 0;
    } else {
      i = 1;
    }
    boolean bool2 = bool1;
    boolean bool3;
    if (i != 0)
    {
      if (TextUtils.isEmpty(b)) {
        i = 1;
      } else {
        i = 0;
      }
      if (i == 1)
      {
        i = a;
        i = (i ^ 0x1) + ((i & 0x1) << 1);
        d = i % 128;
        b = b(paramContext);
        j = d;
        i = (j ^ 0x23 | j & 0x23) << 1;
        j = -((j ^ 0xFFFFFFFF) & 0x23 | j & 0xFFFFFFDC);
        i = ((i | j) << 1) - (j ^ i);
        a = i % 128;
        if (i % 2 == 0) {}
      }
      paramContext = paramContext.getContentResolver();
      if (!TextUtils.isEmpty(b)) {
        i = 98;
      } else {
        i = 4;
      }
      bool2 = bool1;
      if (i != 4)
      {
        i = d;
        j = i ^ 0x47;
        i = ((i & 0x47 | j) << 1) - j;
        a = i % 128;
        bool3 = bool1;
      }
    }
    for (;;)
    {
      try
      {
        paramContext = paramContext.query(Uri.parse(b), new String[] { "title", "iconResource" }, "title=?", new String[] { paramString }, null);
        if (paramContext != null) {
          i = 0;
        } else {
          i = 1;
        }
        bool2 = bool1;
        if (i != 1)
        {
          i = d;
          j = i & 0x69;
          i = ((i | 0x69) & (j ^ 0xFFFFFFFF)) + (j << 1);
          a = i % 128;
          bool3 = bool1;
          i = paramContext.getCount();
          if (i > 0) {
            i = 1;
          } else {
            i = 0;
          }
          if (i != 1)
          {
            bool2 = bool1;
          }
          else
          {
            i = a;
            i = (i & 0x76) + (i | 0x76);
            i = (i ^ 0xFFFFFFFF) + ((i & 0xFFFFFFFF) << 1);
            d = i % 128;
            i = a + 75 - 1 - 0 - 1;
            d = i % 128;
            bool2 = true;
          }
        }
        if (paramContext != null) {
          i = 0;
        } else {
          i = 1;
        }
        if (i != 1)
        {
          i = a;
          i = (i & 0x3D) + (i | 0x3D);
          d = i % 128;
          if (i % 2 != 0) {
            i = 86;
          } else {
            i = 25;
          }
          if (i != 86)
          {
            bool3 = bool2;
            if (paramContext.isClosed()) {
              break label876;
            }
            i = 74;
            break label879;
          }
          bool3 = bool2;
          bool1 = paramContext.isClosed();
          bool3 = bool2;
          i = 27 / 0;
          if (!bool1) {
            i = 1;
          } else {
            i = 0;
          }
          if (i != 0)
          {
            j = a;
            int k = j | 0x25;
            i = k << 1;
            j = -((j & 0x25 ^ 0xFFFFFFFF) & k);
            i = ((i | j) << 1) - (j ^ i);
            d = i % 128;
            bool3 = bool2;
            paramContext.close();
            i = a + 34 - 0 - 1;
            d = i % 128;
            if (i % 2 != 0) {}
          }
        }
        j = d;
        i = j & 0xD;
        j = j ^ 0xD | i;
        i = (i & j) + (j | i);
        a = i % 128;
      }
      catch (Exception paramContext)
      {
        paramString = new StringBuilder();
        paramString.append("isShortCutExist:");
        paramString.append(paramContext.getMessage());
        di.e(paramString.toString());
        bool2 = bool3;
      }
      i = a + 9;
      d = i % 128;
      if (i % 2 != 0) {
        i = 91;
      } else {
        i = 72;
      }
      if (i != 91) {
        return bool2;
      }
      i = 6 / 0;
      return bool2;
      label800:
      bool2 = false;
      label803:
      j = a;
      i = j & 0xFFFFFFF2 | (j ^ 0xFFFFFFFF) & 0xD;
      j = --((j & 0xD) << 1);
      i = (i ^ j) + ((j & i) << 1);
      d = i % 128;
      if (i % 2 != 0) {
        i = 84;
      } else {
        i = 29;
      }
      if (i != 84) {
        return bool2;
      }
      i = null.length;
      return bool2;
      label876:
      i = 48;
      label879:
      if (i == 74) {}
    }
  }
}
