import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.ims.rcsservice.ims.ImsRegistrationState;
import java.util.Iterator;
import java.util.List;

public final class cnk
{
  public cnm a;
  public int b = 0;
  public final Context c;
  
  public cnk(int paramInt, Context paramContext)
  {
    this.b = paramInt;
    this.c = paramContext;
    this.a = new cnm(paramContext);
  }
  
  public final boolean a()
  {
    switch (this.b)
    {
    case 3: 
    case 7: 
    case 10: 
    case 13: 
    case 16: 
    case 17: 
    default: 
      return false;
    }
    return true;
  }
  
  public final boolean b()
  {
    return !a();
  }
  
  final boolean c()
  {
    boolean bool = false;
    cme.a();
    int i;
    if (!this.c.getSharedPreferences("provisioning_engine_state_cache_common.pref", 0).getBoolean("provisioning_engine_bugle_has_permission_key", false)) {
      i = 15;
    }
    for (;;)
    {
      if (i != this.b)
      {
        localObject1 = new Bundle();
        ((Bundle)localObject1).putInt("com.google.android.ims.provisioning.rcs.availability.update.key", this.b);
        ((Bundle)localObject1).putString("com.google.android.ims.provisioning.sim.id.key", cti.a.i());
        cnj.a(this.c, 12, (Bundle)localObject1);
        this.b = i;
        bool = true;
      }
      return bool;
      Object localObject1 = this.a;
      cme.a();
      if (((cnm)localObject1).a.getSharedPreferences("provisioning_engine_state_cache_common.pref", 0).getLong("provisioning_engine_bugle_version_key", 0L) >= ((Integer)bpd.a().q().a()).intValue())
      {
        i = 1;
        label144:
        if (i != 0)
        {
          localObject1 = this.a;
          long l = dbz.b(((cnm)localObject1).a);
          cme.a();
          if (l < ((cnm)localObject1).a.getSharedPreferences("provisioning_engine_state_cache_common.pref", 0).getLong("provisioning_engine_bugle_supported_min_cs_apk_key", 0L)) {
            break label208;
          }
        }
      }
      label208:
      for (i = 1;; i = 0)
      {
        if (i != 0) {
          break label213;
        }
        i = 8;
        break;
        i = 0;
        break label144;
      }
      label213:
      localObject1 = this.a;
      cti.a.a(((cnm)localObject1).a, "ABSENT");
      localObject1 = this.a;
      if (cti.b(((cnm)localObject1).a) != 1)
      {
        cme.a();
        if (!((cnm)localObject1).a.getSharedPreferences("provisioning_engine_state_cache_common.pref", 0).getBoolean("provisioning_engine_bugle_enabled_rcs_for_multi_sim", false))
        {
          ddb.c("Rcs multi sim is disabled because ENABLE_RCS_MULTISIM_V0 disabled", new Object[0]);
          i = 0;
        }
      }
      for (;;)
      {
        if (i != 0) {
          break label336;
        }
        i = 11;
        break;
        if (cti.d(((cnm)localObject1).a) != cti.c(((cnm)localObject1).a))
        {
          ddb.c("Rcs multi sim is disabled defaultSub mismatch defaultDataSub", new Object[0]);
          i = 0;
        }
        else
        {
          i = 1;
        }
      }
      label336:
      cme.a();
      if (!this.c.getSharedPreferences("provisioning_engine_state_cache_common.pref", 0).getBoolean("provisioning_engine_bugle_enabled_rcs_from_preference_key", false))
      {
        i = 4;
      }
      else
      {
        cme.a();
        if (!this.c.getSharedPreferences("provisioning_engine_state_cache_common.pref", 0).getBoolean("provisioning_engine_bugle_is_default_sms_app_key", false))
        {
          i = 5;
        }
        else
        {
          localObject1 = this.a;
          if (!dbv.c())
          {
            String str = ((cnm)localObject1).a.getPackageName();
            Object localObject2 = ((cnm)localObject1).a.getPackageManager().getInstalledApplications(128);
            if (localObject2 != null)
            {
              localObject2 = ((List)localObject2).iterator();
              ApplicationInfo localApplicationInfo;
              do
              {
                if (!((Iterator)localObject2).hasNext()) {
                  break;
                }
                localApplicationInfo = (ApplicationInfo)((Iterator)localObject2).next();
              } while ((str.equals(localApplicationInfo.packageName)) || (!((cnm)localObject1).a(localApplicationInfo)));
            }
          }
          for (i = 1;; i = 0)
          {
            if (i == 0) {
              break label510;
            }
            i = 14;
            break;
          }
          label510:
          localObject1 = this.a;
          cme.a();
          if (!TextUtils.isEmpty(((cnm)localObject1).a.getSharedPreferences("provisioning_engine_state_cache_common.pref", 0).getString("provisioning_engine_bugle_overridden_acs_url", "")))
          {
            ddb.c("config server url available - manually overridden", new Object[0]);
            i = 1;
          }
          for (;;)
          {
            if (i != 0) {
              break label671;
            }
            i = 2;
            break;
            if (((cnm)localObject1).a())
            {
              i = 1;
            }
            else
            {
              if (!((Boolean)bpj.g.a()).booleanValue()) {
                i = 0;
              }
              for (;;)
              {
                if (i == 0) {
                  break label657;
                }
                ddb.c("config server url available - dogfood enabled", new Object[0]);
                i = 1;
                break;
                if (!TextUtils.isEmpty((String)bpd.a().e().a())) {}
                for (i = 1;; i = 0)
                {
                  if (i != 0) {
                    break label652;
                  }
                  i = 0;
                  break;
                }
                label652:
                i = 1;
              }
              label657:
              ddb.c("config server url unavailable - Carrier is not RCS enabled", new Object[0]);
              i = 0;
            }
          }
          label671:
          if (!this.a.b())
          {
            ddb.f("Engine temporarily not connected state not set", new Object[0]);
            i = 0;
          }
          else
          {
            localObject1 = this.a;
            if (((cnm)localObject1).b == null)
            {
              i = 0;
              if (i != 0) {
                break label813;
              }
              localObject1 = this.a;
              if ((((cnm)localObject1).b == null) || (((cnm)localObject1).b.getState() != 30054)) {
                break label802;
              }
            }
            label802:
            for (i = 1;; i = 0)
            {
              if (i == 0) {
                break label807;
              }
              i = 18;
              break;
              switch (((cnm)localObject1).b.getState())
              {
              case 30055: 
              default: 
                i = 1;
                break;
              case 30053: 
              case 30054: 
              case 30056: 
                i = 0;
                break;
              }
            }
            label807:
            i = 6;
            continue;
            label813:
            if (cme.a().a(this.c, cti.a.i()).getBoolean("provisioning_engine_has_term_of_service_key", false))
            {
              if (this.a.a()) {
                i = 16;
              } else {
                i = 17;
              }
            }
            else if ((!cnm.c()) && (this.a.a())) {
              i = 10;
            } else if (cnm.c()) {
              i = 7;
            } else {
              i = 3;
            }
          }
        }
      }
    }
  }
}
