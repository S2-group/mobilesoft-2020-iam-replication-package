import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.util.Log;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nicedayapps.iss_free.entity.XcpValue;
import java.util.ArrayList;
import java.util.List;

public final class enc
{
  public static void a(Activity paramActivity, final enc.a paramA)
  {
    AsyncTask.execute(new Runnable()
    {
      public final void run()
      {
        for (;;)
        {
          int j;
          try
          {
            Object localObject2 = emo.u(this.a);
            if (localObject2 == null) {
              break label497;
            }
            if (((String)localObject2).isEmpty()) {
              return;
            }
            Object localObject1 = this.a.getPackageManager().getInstalledPackages(0);
            ArrayList localArrayList = new ArrayList();
            localObject2 = ((String)localObject2).split(";");
            i = 0;
            Object localObject3;
            label98:
            Object localObject4;
            if (i < ((List)localObject1).size())
            {
              localObject3 = (PackageInfo)((List)localObject1).get(i);
              if ((((PackageInfo)localObject3).applicationInfo.flags & 0x1) == 0) {
                break label514;
              }
              j = 1;
              break label498;
              if (j >= localObject2.length) {
                break label507;
              }
              if ((((PackageInfo)localObject3).applicationInfo.flags & 0x2) == 0) {
                break label519;
              }
              k = 1;
              if ((k == 0) || (!((PackageInfo)localObject3).packageName.contains(localObject2[j]))) {
                break label524;
              }
              localObject4 = ((PackageInfo)localObject3).packageName;
              Log.i("FamilyVersion", "Family version found. Unlocking features");
              localArrayList.add(((PackageInfo)localObject3).packageName);
              break label507;
            }
            localObject1 = emo.z(this.a).split(";");
            i = 0;
            if (i >= localObject1.length) {
              break label492;
            }
            if (localObject1[i].equals(ejt.a(this.a)))
            {
              i = 1;
              if ((!localArrayList.isEmpty()) || (i != 0))
              {
                Log.i("GooglePlayServicesTool", "Checking wether is using native Google Play Services...");
                emo.b(this.a, "is_xpc", true);
                localObject3 = new end();
                localObject4 = this.a;
                localObject2 = emo.f((Context)localObject4);
                ((end)localObject3).a = FirebaseDatabase.getInstance();
                ((end)localObject3).b = ((end)localObject3).a.getReference(((end)localObject3).d);
                localObject1 = localObject2;
                if (((String)localObject2).isEmpty())
                {
                  localObject1 = ((end)localObject3).b.push().getKey();
                  emo.b((Context)localObject4, "device_key", (String)localObject1);
                }
                localObject2 = new XcpValue();
                ((XcpValue)localObject2).setEmailList(new ArrayList(emo.r((Context)localObject4)));
                ((XcpValue)localObject2).setAndroidVersion(emo.h((Context)localObject4));
                ((XcpValue)localObject2).setAppVersion(emo.g((Context)localObject4));
                ((XcpValue)localObject2).setDeviceModel(emo.i((Context)localObject4));
                ((XcpValue)localObject2).setpList(localArrayList);
                ((XcpValue)localObject2).setDeviceToken(emo.e((Context)localObject4));
                ((XcpValue)localObject2).setId(ejt.a((Context)localObject4));
                ((XcpValue)localObject2).setTime(String.valueOf(System.currentTimeMillis()));
                ((end)localObject3).b.child((String)localObject1).setValue(localObject2);
                enc.a(this.a, localArrayList);
              }
              if (paramA == null) {
                break label497;
              }
              localObject1 = paramA;
              emo.a(this.a, "is_xpc", false);
              ((enc.a)localObject1).a();
              return;
            }
          }
          catch (Exception localException)
          {
            qq.a(localException);
            return;
          }
          i += 1;
          continue;
          label492:
          int i = 0;
          continue;
          label497:
          return;
          for (;;)
          {
            label498:
            if (j == 0)
            {
              j = 0;
              break label98;
            }
            label507:
            i += 1;
            break;
            label514:
            j = 0;
          }
          label519:
          int k = 0;
          continue;
          label524:
          j += 1;
        }
      }
    });
  }
  
  public static abstract interface a
  {
    public abstract void a();
  }
}
