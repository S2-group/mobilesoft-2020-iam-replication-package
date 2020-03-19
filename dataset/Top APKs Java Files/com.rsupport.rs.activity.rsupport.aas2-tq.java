import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import com.rsupport.rs.activity.edit.UserActionActivity;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public final class tq
{
  private static final int jdField_a_of_type_Int = 200;
  public static final String a = "chg";
  private static final int jdField_b_of_type_Int = 201;
  private static final String jdField_b_of_type_JavaLangString = "ApplicationInfo";
  private static final int jdField_c_of_type_Int = 202;
  private static String jdField_c_of_type_JavaLangString;
  private static final int jdField_d_of_type_Int = 203;
  private static String jdField_d_of_type_JavaLangString;
  private static final int jdField_e_of_type_Int = 204;
  private static final String jdField_e_of_type_JavaLangString = "filter_app_type";
  private static final int f = 205;
  private static final int g = 206;
  private static final int h = 207;
  private static final int i = 208;
  private static final int j = 0;
  private static final int k = 1;
  private static final int l = 2;
  private static final int m = 0;
  private Activity jdField_a_of_type_AndroidAppActivity;
  private ArrayList jdField_a_of_type_JavaUtilArrayList;
  private tr jdField_a_of_type_Tr;
  private tt jdField_a_of_type_Tt;
  private final int n = 1;
  private final int o = 2;
  private final int p = 3;
  private final int q = 4;
  private final int r = 5;
  private int s;
  
  static
  {
    c = "sort_order_type";
    d = "show_icon";
  }
  
  public tq(Activity paramActivity)
  {
    this.jdField_a_of_type_AndroidAppActivity = paramActivity;
    this.jdField_a_of_type_Tr = new tr();
    this.jdField_a_of_type_JavaUtilArrayList = new ArrayList();
  }
  
  private static int a(int paramInt)
  {
    if (paramInt == 0) {}
    do
    {
      return 1;
      if (paramInt == 1) {
        return 2;
      }
    } while (paramInt != 2);
    return 0;
  }
  
  public static int a(Activity paramActivity, String paramString, int paramInt)
  {
    return paramActivity.getPreferences(0).getInt(paramString, paramInt);
  }
  
  private String a(String paramString)
  {
    try
    {
      paramString = this.jdField_a_of_type_AndroidAppActivity.getPackageManager().getApplicationInfo(paramString, 0).loadLabel(this.jdField_a_of_type_AndroidAppActivity.getPackageManager()).toString();
      return paramString;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      paramString.printStackTrace();
    }
    return "";
  }
  
  private List a(List paramList, int paramInt)
  {
    if ((paramList == null) || (paramList.size() == 0)) {}
    ApplicationInfo localApplicationInfo;
    do
    {
      return paramList;
      paramInt = a(this.jdField_a_of_type_AndroidAppActivity, "filter_app_type", a(paramInt));
      if (paramInt == 1)
      {
        localArrayList = new ArrayList();
        i1 = paramList.size();
        paramInt = 0;
        for (;;)
        {
          if (paramInt >= i1) {
            return localArrayList;
          }
          localApplicationInfo = (ApplicationInfo)paramList.get(paramInt);
          if ((localApplicationInfo.flags & 0x1) != 0) {
            localArrayList.add(localApplicationInfo);
          }
          paramInt += 1;
        }
      }
    } while (paramInt != 2);
    ArrayList localArrayList = new ArrayList();
    int i1 = paramList.size();
    paramInt = 0;
    for (;;)
    {
      if (paramInt >= i1) {
        return localArrayList;
      }
      localApplicationInfo = (ApplicationInfo)paramList.get(paramInt);
      if ((localApplicationInfo.flags & 0x1) == 0) {
        localArrayList.add(localApplicationInfo);
      }
      paramInt += 1;
    }
  }
  
  private void a()
  {
    this.jdField_a_of_type_Tr.a(this.jdField_a_of_type_JavaUtilArrayList);
    tt localTt = new tt(this.jdField_a_of_type_AndroidAppActivity, this.jdField_a_of_type_Tr);
    this.jdField_a_of_type_Tt = localTt;
    localTt.start();
  }
  
  private static boolean a(ApplicationInfo paramApplicationInfo)
  {
    if ((paramApplicationInfo.flags & 0x80) != 0) {}
    for (int i1 = 1; (i1 == 0) && ((paramApplicationInfo.flags & 0x1) != 0); i1 = 0) {
      return false;
    }
    return true;
  }
  
  private byte[][] a()
  {
    int i1;
    byte[][] arrayOfByte;
    int i4;
    int i2;
    label60:
    int i5;
    int i3;
    if (vh.b())
    {
      i1 = 1;
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
      arrayOfByte = new byte[this.jdField_a_of_type_JavaUtilArrayList.size()][];
      try
      {
        localList = ((ActivityManager)this.jdField_a_of_type_AndroidAppActivity.getSystemService("activity")).getRunningAppProcesses();
        i4 = this.jdField_a_of_type_JavaUtilArrayList.size();
        i2 = 0;
      }
      catch (Exception localException)
      {
        List localList;
        ts localTs;
        Object localObject1;
        Object localObject3;
        Object localObject2;
        localException.printStackTrace();
        return arrayOfByte;
      }
      localTs = (ts)this.jdField_a_of_type_JavaUtilArrayList.get(i2);
      localObject3 = localObject1;
      if (localTs.jdField_a_of_type_AndroidGraphicsDrawableDrawable != null)
      {
        localObject3 = localObject1;
        if (i1 != 0)
        {
          localObject3 = localObject1;
          if (localTs.jdField_a_of_type_AndroidGraphicsDrawableDrawable.getClass().equals(BitmapDrawable.class))
          {
            ((BitmapDrawable)localTs.jdField_a_of_type_AndroidGraphicsDrawableDrawable).getBitmap().compress(Bitmap.CompressFormat.PNG, 100, localByteArrayOutputStream);
            localObject3 = localByteArrayOutputStream.toByteArray();
          }
        }
      }
      if (localTs.jdField_a_of_type_JavaLangCharSequence != null) {}
      for (localObject1 = localTs.jdField_a_of_type_JavaLangCharSequence + "&/";; localObject1 = localTs.jdField_a_of_type_AndroidContentPmApplicationInfo.packageName + "&/")
      {
        localObject2 = localObject1 + localTs.b + "&/";
        i5 = localList.size();
        i3 = 0;
        break label708;
        label219:
        localObject1 = localObject2 + (String)localObject1 + "&/";
        if (!localTs.d) {
          break;
        }
        localObject1 = localObject1 + "1";
        label277:
        localObject2 = localObject1;
        if (a(this.s) == 0)
        {
          if ((localTs.jdField_a_of_type_AndroidContentPmApplicationInfo.flags & 0x1) == 0) {
            break label625;
          }
          localObject2 = localObject1 + "&/1";
        }
        label327:
        localObject1 = (new StringBuilder(String.valueOf(localObject2)).append("&/").toString() + localTs.jdField_a_of_type_AndroidContentPmApplicationInfo.packageName).getBytes("UTF-16LE");
        localObject2 = new byte[localObject1.length + 2];
        System.arraycopy(localObject1, 0, localObject2, 0, localObject1.length);
        if (localObject3 == null) {
          break label651;
        }
        localObject1 = new byte[localObject2.length + 9 + localObject3.length];
        label419:
        System.arraycopy(new byte[] { ve.a(0)[0] }, 0, localObject1, 0, 1);
        System.arraycopy(ve.a(localObject2.length), 0, localObject1, 1, 4);
        if (localObject3 == null) {
          break label666;
        }
        System.arraycopy(ve.a(localObject3.length), 0, localObject1, 5, 4);
        label472:
        System.arraycopy(localObject2, 0, localObject1, 9, localObject2.length);
        i3 = localObject2.length;
        if (localObject3 != null) {
          System.arraycopy(localObject3, 0, localObject1, i3 + 9, localObject3.length);
        }
        arrayOfByte[i2] = localObject1;
        localByteArrayOutputStream.reset();
        localByteArrayOutputStream.close();
        i2 += 1;
        break label696;
      }
    }
    label625:
    label651:
    label666:
    label696:
    label708:
    label727:
    for (;;)
    {
      String str;
      if (localTs.jdField_a_of_type_AndroidContentPmApplicationInfo.processName.equals(((ActivityManager.RunningAppProcessInfo)localList.get(i3)).processName))
      {
        localObject1 = "ON";
        break label219;
        localObject1 = localObject1 + "0";
        break label277;
        localObject2 = localObject1 + "&/0";
        break label327;
        localObject1 = new byte[localObject2.length + 9 + 0];
        break label419;
        System.arraycopy(ve.a(0), 0, localObject1, 5, 4);
        break label472;
        i1 = 0;
        break;
        str = null;
        if (i2 < i4) {
          break label60;
        }
        return arrayOfByte;
      }
      for (;;)
      {
        if (i3 < i5) {
          break label727;
        }
        str = "OFF";
        break;
        i3 += 1;
      }
    }
  }
  
  private void b(String paramString)
  {
    Intent localIntent = new Intent(this.jdField_a_of_type_AndroidAppActivity, UserActionActivity.class);
    localIntent.setFlags(268435456);
    localIntent.putExtra("type", "runapp");
    localIntent.putExtra("packagename", paramString);
    this.jdField_a_of_type_AndroidAppActivity.startActivity(localIntent);
  }
  
  private static boolean b(Activity paramActivity, String paramString)
  {
    return paramActivity.getPreferences(0).getBoolean(paramString, true);
  }
  
  private byte[] b(String paramString)
  {
    Object localObject1 = this.jdField_a_of_type_AndroidAppActivity.getPackageManager();
    for (;;)
    {
      int i1;
      try
      {
        Object localObject2 = ((PackageManager)localObject1).getInstalledApplications(0);
        int i2 = ((List)localObject2).size();
        i1 = 0;
        if (i1 >= i2)
        {
          localObject1 = ((PackageManager)localObject1).getApplicationInfo(paramString, 0);
          localObject2 = new StringBuilder("ClassName&/");
          if (((ApplicationInfo)localObject1).className == null)
          {
            paramString = "";
            localObject2 = new StringBuilder(String.valueOf(paramString + "&&")).append("DataDir&/");
            if (((ApplicationInfo)localObject1).dataDir != null) {
              continue;
            }
            paramString = "";
            localObject2 = new StringBuilder(String.valueOf(paramString + "&&")).append("Name&/");
            if (((ApplicationInfo)localObject1).name != null) {
              continue;
            }
            paramString = "";
            localObject2 = new StringBuilder(String.valueOf(paramString + "&&")).append("PackageName&/");
            if (((ApplicationInfo)localObject1).packageName != null) {
              continue;
            }
            paramString = "";
            localObject2 = new StringBuilder(String.valueOf(paramString + "&&")).append("Permission&/");
            if (((ApplicationInfo)localObject1).permission != null) {
              continue;
            }
            paramString = "";
            localObject2 = new StringBuilder(String.valueOf(paramString + "&&")).append("SourceDir&/");
            if (((ApplicationInfo)localObject1).sourceDir != null) {
              continue;
            }
            paramString = "";
            paramString = (new StringBuilder(String.valueOf(((StringBuilder)localObject2).append(paramString).append("&&").toString())).append("TargetSDKVersion&/").append(((ApplicationInfo)localObject1).targetSdkVersion).append("&&").toString() + "UID&/" + ((ApplicationInfo)localObject1).uid + "&&").getBytes("UTF-16LE");
            localObject1 = new byte[paramString.length + 2];
            System.arraycopy(paramString, 0, localObject1, 0, paramString.length);
            return localObject1;
          }
        }
        else
        {
          if ((!((ApplicationInfo)((List)localObject2).get(i1)).packageName.equals(paramString)) && (!((ApplicationInfo)((List)localObject2).get(i1)).loadLabel((PackageManager)localObject1).equals(paramString))) {
            break label521;
          }
          paramString = ((ApplicationInfo)((List)localObject2).get(i1)).packageName;
          break label524;
        }
        paramString = ((ApplicationInfo)localObject1).className;
        continue;
        paramString = ((ApplicationInfo)localObject1).dataDir;
        continue;
        paramString = ((ApplicationInfo)localObject1).name;
        continue;
        paramString = ((ApplicationInfo)localObject1).packageName;
        continue;
        paramString = ((ApplicationInfo)localObject1).permission;
        continue;
        paramString = ((ApplicationInfo)localObject1).sourceDir;
        continue;
      }
      catch (Exception paramString)
      {
        vf.e("ApplicationInfo", paramString.getLocalizedMessage());
        return null;
      }
      label521:
      label524:
      i1 += 1;
    }
  }
  
  private static boolean c(Activity paramActivity, String paramString)
  {
    return paramActivity.getPreferences(0).getBoolean(paramString, true);
  }
  
  private boolean c(String paramString)
  {
    List localList1 = ((ActivityManager)this.jdField_a_of_type_AndroidAppActivity.getSystemService("activity")).getRunningAppProcesses();
    PackageManager localPackageManager = this.jdField_a_of_type_AndroidAppActivity.getPackageManager();
    List localList2 = localPackageManager.getInstalledApplications(0);
    int i4 = localList1.size();
    int i1 = 0;
    int i2;
    for (;;)
    {
      if (i1 >= i4)
      {
        vf.c("ApplicationInfo", "Running Fail");
        return false;
      }
      int i5 = ((ActivityManager.RunningAppProcessInfo)localList1.get(i1)).pkgList.length;
      i2 = 0;
      if (i2 < i5) {
        break;
      }
      i1 += 1;
    }
    if (paramString.equals(((ActivityManager.RunningAppProcessInfo)localList1.get(i1)).pkgList[i2]))
    {
      vf.c("ApplicationInfo", "Running Success");
      return true;
    }
    int i6 = localList2.size();
    int i3 = 0;
    for (;;)
    {
      if (i3 >= i6)
      {
        i2 += 1;
        break;
      }
      if ((((ApplicationInfo)localList2.get(i3)).packageName.equals(((ActivityManager.RunningAppProcessInfo)localList1.get(i1)).pkgList[i2])) && (paramString.equals(((ApplicationInfo)localList2.get(i3)).loadLabel(localPackageManager))))
      {
        vf.c("ApplicationInfo", "Running Success");
        return true;
      }
      i3 += 1;
    }
  }
  
  private boolean d(String paramString)
  {
    PackageManager localPackageManager = this.jdField_a_of_type_AndroidAppActivity.getPackageManager();
    List localList = localPackageManager.getInstalledApplications(0);
    int i2 = localList.size();
    int i1 = 0;
    for (;;)
    {
      if (i1 >= i2)
      {
        vf.c("ApplicationInfo", paramString + " is not Installed.");
        return false;
      }
      if (paramString.equals(((ApplicationInfo)localList.get(i1)).packageName))
      {
        vf.c("ApplicationInfo", paramString + " is Installed.");
        return true;
      }
      if (paramString.equals(((ApplicationInfo)localList.get(i1)).loadLabel(localPackageManager)))
      {
        vf.c("ApplicationInfo", paramString + " is Installed.");
        return true;
      }
      i1 += 1;
    }
  }
  
  public final Uri a(String paramString)
  {
    vf.c("ApplicationInfo", "getPackageUriFrom");
    int i2 = this.jdField_a_of_type_JavaUtilArrayList.size();
    int i1 = 0;
    for (;;)
    {
      if (i1 >= i2) {
        return null;
      }
      if (((ts)this.jdField_a_of_type_JavaUtilArrayList.get(i1)).jdField_a_of_type_JavaLangCharSequence != null) {}
      for (String str = ((ts)this.jdField_a_of_type_JavaUtilArrayList.get(i1)).jdField_a_of_type_JavaLangCharSequence; paramString.equals(str); str = ((ts)this.jdField_a_of_type_JavaUtilArrayList.get(i1)).jdField_a_of_type_AndroidContentPmApplicationInfo.packageName) {
        return Uri.parse("package:" + ((ts)this.jdField_a_of_type_JavaUtilArrayList.get(i1)).jdField_a_of_type_AndroidContentPmApplicationInfo.packageName);
      }
      if (paramString.equals(((ts)this.jdField_a_of_type_JavaUtilArrayList.get(i1)).jdField_a_of_type_AndroidContentPmApplicationInfo.packageName)) {
        return Uri.parse("package:" + ((ts)this.jdField_a_of_type_JavaUtilArrayList.get(i1)).jdField_a_of_type_AndroidContentPmApplicationInfo.packageName);
      }
      i1 += 1;
    }
  }
  
  public final void a(int paramInt)
  {
    this.s = paramInt;
    this.jdField_a_of_type_Tr.a();
    this.jdField_a_of_type_JavaUtilArrayList.clear();
    PackageManager localPackageManager = this.jdField_a_of_type_AndroidAppActivity.getPackageManager();
    Object localObject2 = localPackageManager.getInstalledApplications(0);
    Object localObject1;
    if ((localObject2 == null) || (((List)localObject2).size() == 0)) {
      localObject1 = localObject2;
    }
    for (;;)
    {
      int i2 = ((List)localObject1).size();
      paramInt = 0;
      if (paramInt >= i2)
      {
        this.jdField_a_of_type_Tr.a(this.jdField_a_of_type_JavaUtilArrayList);
        localObject1 = new tt(this.jdField_a_of_type_AndroidAppActivity, this.jdField_a_of_type_Tr);
        this.jdField_a_of_type_Tt = ((tt)localObject1);
        ((tt)localObject1).start();
      }
      try
      {
        if (this.jdField_a_of_type_Tt != null) {
          this.jdField_a_of_type_Tt.join();
        }
        qq.a(null).a.b = a();
        return;
        paramInt = a(this.jdField_a_of_type_AndroidAppActivity, "filter_app_type", a(paramInt));
        int i1;
        if (paramInt == 1)
        {
          localObject3 = new ArrayList();
          i1 = ((List)localObject2).size();
          paramInt = 0;
          for (;;)
          {
            localObject1 = localObject3;
            if (paramInt >= i1) {
              break;
            }
            localObject1 = (ApplicationInfo)((List)localObject2).get(paramInt);
            if ((((ApplicationInfo)localObject1).flags & 0x1) != 0) {
              ((List)localObject3).add(localObject1);
            }
            paramInt += 1;
          }
        }
        if (paramInt == 2)
        {
          localObject3 = new ArrayList();
          i1 = ((List)localObject2).size();
          paramInt = 0;
          for (;;)
          {
            localObject1 = localObject3;
            if (paramInt >= i1) {
              break;
            }
            localObject1 = (ApplicationInfo)((List)localObject2).get(paramInt);
            if ((((ApplicationInfo)localObject1).flags & 0x1) == 0) {
              ((List)localObject3).add(localObject1);
            }
            paramInt += 1;
          }
        }
        localObject1 = localObject2;
        continue;
        ApplicationInfo localApplicationInfo = (ApplicationInfo)((List)localObject1).get(paramInt);
        Object localObject3 = new ts();
        ((ts)localObject3).jdField_a_of_type_AndroidContentPmApplicationInfo = localApplicationInfo;
        ((ts)localObject3).c = localApplicationInfo.enabled;
        if ((localApplicationInfo.flags & 0x80) != 0)
        {
          i1 = 1;
          label358:
          if ((i1 == 0) && ((localApplicationInfo.flags & 0x1) != 0))
          {
            bool = false;
            ((ts)localObject3).d = bool;
          }
        }
        else
        {
          for (;;)
          {
            try
            {
              localPackageInfo = localPackageManager.getPackageInfo(localApplicationInfo.packageName, 0);
              if (localPackageInfo.versionName != null) {
                continue;
              }
              localObject2 = String.valueOf(localPackageInfo.versionCode);
              ((ts)localObject3).b = ((CharSequence)localObject2);
              ((ts)localObject3).jdField_a_of_type_Int = localPackageInfo.versionCode;
              if ((localApplicationInfo.sourceDir != null) && (localApplicationInfo.sourceDir.contains("/data/app-private"))) {
                ((ts)localObject3).jdField_a_of_type_Boolean = true;
              }
            }
            catch (PackageManager.NameNotFoundException localNameNotFoundException)
            {
              PackageInfo localPackageInfo;
              vf.e("ApplicationInfo", localNameNotFoundException.getLocalizedMessage());
              continue;
            }
            this.jdField_a_of_type_JavaUtilArrayList.add(localObject3);
            paramInt += 1;
            break;
            i1 = 0;
            break label358;
            localObject2 = localPackageInfo.versionName;
          }
        }
      }
      catch (InterruptedException localInterruptedException)
      {
        for (;;)
        {
          localInterruptedException.printStackTrace();
          continue;
          boolean bool = true;
        }
      }
    }
  }
  
  public final void a(String paramString)
  {
    vf.c("ApplicationInfo", "removeApp");
    int i2 = this.jdField_a_of_type_JavaUtilArrayList.size();
    int i1 = 0;
    for (;;)
    {
      if (i1 >= i2) {
        return;
      }
      if (((ts)this.jdField_a_of_type_JavaUtilArrayList.get(i1)).jdField_a_of_type_JavaLangCharSequence != null) {}
      for (String str = ((ts)this.jdField_a_of_type_JavaUtilArrayList.get(i1)).jdField_a_of_type_JavaLangCharSequence; paramString.equals(str); str = ((ts)this.jdField_a_of_type_JavaUtilArrayList.get(i1)).jdField_a_of_type_AndroidContentPmApplicationInfo.packageName)
      {
        paramString = new Intent("android.intent.action.DELETE", Uri.parse("package:" + ((ts)this.jdField_a_of_type_JavaUtilArrayList.get(i1)).jdField_a_of_type_AndroidContentPmApplicationInfo.packageName));
        this.jdField_a_of_type_AndroidAppActivity.startActivity(paramString);
        return;
      }
      i1 += 1;
    }
  }
  
  public final boolean a(String paramString)
  {
    int i2 = this.jdField_a_of_type_JavaUtilArrayList.size();
    int i1 = 0;
    for (;;)
    {
      if (i1 >= i2) {
        return false;
      }
      String str;
      if (((ts)this.jdField_a_of_type_JavaUtilArrayList.get(i1)).jdField_a_of_type_JavaLangCharSequence != null) {
        str = ((ts)this.jdField_a_of_type_JavaUtilArrayList.get(i1)).jdField_a_of_type_JavaLangCharSequence;
      }
      while (paramString.equals(str))
      {
        paramString = this.jdField_a_of_type_AndroidAppActivity.getPackageManager().getLaunchIntentForPackage(((ts)this.jdField_a_of_type_JavaUtilArrayList.get(i1)).jdField_a_of_type_AndroidContentPmApplicationInfo.packageName);
        if (paramString == null)
        {
          vf.c("ApplicationInfo", str + " does not start is null");
          return false;
          str = ((ts)this.jdField_a_of_type_JavaUtilArrayList.get(i1)).jdField_a_of_type_AndroidContentPmApplicationInfo.packageName;
        }
        else
        {
          if (paramString != null)
          {
            vf.c("ApplicationInfo", str + " start.");
            if (((ts)this.jdField_a_of_type_JavaUtilArrayList.get(i1)).jdField_a_of_type_AndroidContentPmApplicationInfo.packageName.contains("browser"))
            {
              vh.b(this.jdField_a_of_type_AndroidAppActivity, "www.google.com");
              vf.c("ApplicationInfo", str + " end.");
              return true;
            }
            try
            {
              UserActionActivity.a();
              paramString = ((ts)this.jdField_a_of_type_JavaUtilArrayList.get(i1)).jdField_a_of_type_AndroidContentPmApplicationInfo.packageName;
              Intent localIntent = new Intent(this.jdField_a_of_type_AndroidAppActivity, UserActionActivity.class);
              localIntent.setFlags(268435456);
              localIntent.putExtra("type", "runapp");
              localIntent.putExtra("packagename", paramString);
              this.jdField_a_of_type_AndroidAppActivity.startActivity(localIntent);
              vf.c("ApplicationInfo", str + " end.");
              return true;
            }
            catch (ActivityNotFoundException paramString)
            {
              for (;;)
              {
                vf.e("ApplicationInfo", paramString.getLocalizedMessage());
                if (((ts)this.jdField_a_of_type_JavaUtilArrayList.get(i1)).jdField_a_of_type_AndroidContentPmApplicationInfo.packageName.contains("contact")) {
                  try
                  {
                    this.jdField_a_of_type_AndroidAppActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("content://contacts/people")));
                  }
                  catch (ActivityNotFoundException paramString)
                  {
                    vf.e("ApplicationInfo", str + " does not start.");
                    return false;
                  }
                }
              }
            }
          }
          vf.c("ApplicationInfo", str + " does not start.");
          return false;
        }
      }
      i1 += 1;
    }
  }
  
  public final byte[] a(String paramString)
  {
    vf.c("ApplicationInfo", "getAppDetailInfoPacket");
    paramString = b(paramString);
    if (paramString == null) {
      return null;
    }
    byte[] arrayOfByte = new byte[paramString.length + 4];
    System.arraycopy(ve.a(paramString.length), 0, arrayOfByte, 0, 4);
    System.arraycopy(paramString, 0, arrayOfByte, 4, paramString.length);
    return arrayOfByte;
  }
  
  public final boolean b(String paramString)
  {
    List localList = ((ActivityManager)this.jdField_a_of_type_AndroidAppActivity.getSystemService("activity")).getRunningAppProcesses();
    int i3 = localList.size();
    int i1 = 0;
    if (i1 >= i3)
    {
      vf.c("ApplicationInfo", "Running Fail");
      return false;
    }
    int i4 = ((ActivityManager.RunningAppProcessInfo)localList.get(i1)).pkgList.length;
    int i2 = 0;
    for (;;)
    {
      if (i2 >= i4)
      {
        i1 += 1;
        break;
      }
      if (paramString.equals(a(((ActivityManager.RunningAppProcessInfo)localList.get(i1)).pkgList[i2])))
      {
        vf.c("ApplicationInfo", "Running Success");
        return true;
      }
      i2 += 1;
    }
  }
}
