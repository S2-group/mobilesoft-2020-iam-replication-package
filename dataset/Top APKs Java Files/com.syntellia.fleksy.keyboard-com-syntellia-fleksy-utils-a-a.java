package com.syntellia.fleksy.utils.a;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import com.syntellia.fleksy.settings.activities.BadgesStatsActivity;
import com.syntellia.fleksy.settings.activities.LanguagesActivity;
import com.syntellia.fleksy.settings.activities.MainActivity;
import com.syntellia.fleksy.settings.activities.PersonalizeActivity;
import com.syntellia.fleksy.settings.activities.ThemeBuilderActivity;
import com.syntellia.fleksy.tutorial.activities.TutorActivity;
import java.util.Iterator;
import java.util.List;

public enum a
{
  private int am;
  private int an;
  private b.a ao;
  
  static
  {
    A = new a("MIDAS_TYPE", 26, 2131690450, 2131690397);
    B = new a("SWIPE_DASH", 27, 2131690655, 2131690419);
    C = new a("SWIPE_SPRINT", 28, 2131690663, 2131690421);
    D = new a("SWIPE_MARATHON", 29, 2131690659, 2131690420);
    E = new a("FLEKSY_MASTER", 30, 2131690042, 2131690379);
    F = new a("EVANGELIST", 31, 2131689922, 2131690372);
    G = new a("TWITTASTIC", 32, 2131690788, 2131690428);
    H = new a("EYES_FREE", 33, 2131689998, 2131690375);
    I = new a("BLURRED_LINES", 34, 2131689752, 2131690368);
    J = new a("FLUENT_IN_GIBBERISH", 35, 2131690047, 2131690380);
    K = new a("LOYALTY", 36, 2131690439, 2131690395);
    L = new a("POWER_USER", 37, 2131690505, 2131690403);
    M = new a("KING_OF_KEYBOARDS", 38, 2131690341, 2131690393);
    N = new a("GOAT", 39, 2131690087, 2131690387);
    O = new a("TASTE_THE_RAINBOW", 40, 2131690685, 2131690424);
    P = new a("TASTE_MORE_RAINBOW", 41, 2131690681, 2131690423);
    Q = new a("PRIVATE_CLUB", 42, 2131690515, 2131690404);
    R = new a("HIGH_ROLLER", 43, 2131690102, 2131690390);
    S = new a("CLOUD_LIFE", 44, 2131689766, 2131690370);
    T = new a("BRONZE_CARD", 45, 2131689756, 2131690369);
    U = new a("SILVER_CARD", 46, 2131690594, 2131690410);
    V = new a("GOLD_CARD", 47, 2131690090, 2131690388);
    W = new a("PLATINUM_CARD", 48, 2131690502, 2131690402);
    X = new a("ANNIVERSARY", 49, 2131689719, 2131690366);
    Y = new a("VALENTINES", 50, 2131690907, 2131690429);
    Z = new a("RANK1", 51, 2131690523, 2131690406);
    aa = new a("RANK2", 52, 2131690524, 2131690407);
    ab = new a("RANK3", 53, 2131690525, 2131690408);
    ac = new a("TEXTATHEME", 54, 2131690690, 2131690425);
    ad = new a("IGJTRT", 55, 2131690299, 2131690391);
    ae = new a("EVERYONEGETSATHEME", 56, 2131689926, 2131690373);
    af = new a("MOVIEMAKER", 57, 2131690461, 2131690399);
    ag = new a("GIFBROTHERS", 58, 2131690080, 2131690386);
    ah = new a("TWENTIETHTHCENTURYGIF", 59, 2131690786, 2131690427);
    ai = new a("STICKERLOVE", 60, 2131690639, 2131690417);
    aj = new a("STICKERSEVERYWHERE", 61, 2131690642, 2131690418);
    ak = new a("GOTGOTNEED", 62, 2131690093, 2131690389);
    al = new a("THEMEBUILDER", 63, 2131690695, 2131690426);
    ap = new a[] { a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z, aa, ab, ac, ad, ae, af, ag, ah, ai, aj, ak, al };
    Object localObject = new b.a(2131690038, 2131690037, 2131690130, 2131361955, 2131361946, 1, false, 2131690036)
    {
      public final Intent a(Context paramAnonymousContext)
      {
        return com.syntellia.fleksy.utils.notifications.a.a(paramAnonymousContext, TutorActivity.class);
      }
    };
    ((b.a)localObject).b(2130837711);
    c.ao = ((b.a)localObject);
    localObject = new b.a(2131690034, 2131690032, 2131690129, 2131361955, 2131361816, 1, false, 2131690033)
    {
      public final Intent a(Context paramAnonymousContext)
      {
        paramAnonymousContext = com.syntellia.fleksy.utils.notifications.a.a(paramAnonymousContext, "new_fleksy.jpg", paramAnonymousContext.getString(2131690033) + " " + paramAnonymousContext.getString(2131690377, new Object[] { paramAnonymousContext.getString(2131690409) }));
        paramAnonymousContext.putExtra("com.syntellia.fleksy.utils.notifications.achievement_share_fleksy_identifier", true);
        return paramAnonymousContext;
      }
    };
    ((b.a)localObject).b(2130837709);
    j.ao = ((b.a)localObject);
    localObject = new b.a(2131690319, 2131690320, 2131690134, 2131361955, 2131361831, 10, false, 2131690321);
    g.ao = ((b.a)localObject);
    localObject = new b.a(2131690912, 2131690911, 2131690134, 2131361955, 2131361866, 500, false, 2131690913);
    h.ao = ((b.a)localObject);
    localObject = new b.a(2131690076, 2131690075, 2131690134, 2131361955, 2131361824, 1000, false, 2131690077);
    i.ao = ((b.a)localObject);
    localObject = new b.a(2131689746, 2131689744, 2131690120, 2131361955, 2131361804, 1, false, 2131689745)
    {
      public final Intent a(Context paramAnonymousContext)
      {
        return com.syntellia.fleksy.utils.notifications.a.a(paramAnonymousContext, LanguagesActivity.class);
      }
    };
    ((b.a)localObject).b(2130837694);
    n.ao = ((b.a)localObject);
    localObject = new b.a(2131690521, 2131690519, 2131690144, 2131361955, 2131361840, 1, false, 2131690520);
    p.ao = ((b.a)localObject);
    localObject = new b.a(2131690065, 2131690063, 2131690133, 2131361955, 2131361820, 200, false, 2131690064);
    s.ao = ((b.a)localObject);
    localObject = new b.a(2131690069, 2131690067, 2131690133, 2131361955, 2131361821, 1000, false, 2131690068);
    t.ao = ((b.a)localObject);
    localObject = new b.a(2131690073, 2131690071, 2131690133, 2131361823, 2131361822, 10000, false, 2131690072);
    u.ao = ((b.a)localObject);
    localObject = new b.a(2131690625, 2131690623, 2131690130, 2131361955, 2131361845, 1, true, 2131690624);
    ((b.a)localObject).b(2130837710);
    v.ao = ((b.a)localObject);
    localObject = new b.a(2131690636, 2131690634, 2131690130, 2131361955, 2131361849, 1, true, 2131690635);
    ((b.a)localObject).b(2130837710);
    w.ao = ((b.a)localObject);
    localObject = new b.a(2131690629, 2131690627, 2131690130, 2131361955, 2131361846, 1, true, 2131690628);
    ((b.a)localObject).b(2130837710);
    x.ao = ((b.a)localObject);
    localObject = new b.a(2131690632, 2131690631, 2131690130, 2131361848, 2131361847, 1, true, 2131690628);
    ((b.a)localObject).b(2130837710);
    y.ao = ((b.a)localObject);
    localObject = new b.a(2131690610, 2131690609, 2131690149, 2131361955, 2131361844, 5, false, 2131689885) {};
    k.ao = ((b.a)localObject);
    localObject = new b.a(2131690453, 2131690452, 2131690124, 2131361808, 2131361807, 1, true, 2131689885) {};
    ((b.a)localObject).a(2131690451);
    A.ao = ((b.a)localObject);
    localObject = new b.a(2131690056, 2131690055, 2131690132, 2131361955, 2131361830, 1, true, 2131689885) {};
    b.ao = ((b.a)localObject);
    localObject = new b.a(2131689931, 2131689929, 2131690127, 2131361813, 2131361814, 1, true, 2131689885) {};
    a.ao = ((b.a)localObject);
    localObject = new b.a(2131690469, 2131690466, 2131690141, 2131361955, 2131361830, 1, false, 2131690468)
    {
      public final Intent a(Context paramAnonymousContext)
      {
        return com.syntellia.fleksy.utils.notifications.a.a(paramAnonymousContext, PersonalizeActivity.class);
      }
      
      public final void b() {}
      
      public final boolean c()
      {
        return false;
      }
    };
    ((b.a)localObject).b(2130837707);
    d.ao = ((b.a)localObject);
    localObject = new b.a(2131690473, 2131690470, 2131690141, 2131361955, 2131361856, 1, false, 2131690472)
    {
      public final Intent a(Context paramAnonymousContext)
      {
        return com.syntellia.fleksy.utils.notifications.a.a(paramAnonymousContext, PersonalizeActivity.class);
      }
      
      public final void b() {}
      
      public final boolean c()
      {
        return false;
      }
    };
    ((b.a)localObject).b(2130837707);
    e.ao = ((b.a)localObject);
    localObject = new b.a(2131690658, 2131690656, 2131690123, 2131361955, 2131361852, 3600, false, 2131690657);
    B.ao = ((b.a)localObject);
    localObject = new b.a(2131690666, 2131690664, 2131690123, 2131361955, 2131361854, 36000, false, 2131690665);
    C.ao = ((b.a)localObject);
    localObject = new b.a(2131690662, 2131690660, 2131690123, 2131361955, 2131361853, 63360, false, 2131690661);
    D.ao = ((b.a)localObject);
    localObject = new b.a(2131690044, 2131690041, 2131690131, 2131361817, 2131361818, 10, false, 2131690043)
    {
      public final boolean a()
      {
        return false;
      }
    };
    E.ao = ((b.a)localObject);
    localObject = new b.a(2131689924, 2131689921, 2131690126, 2131361810, 2131361811, 1, false, 2131689923)
    {
      public final boolean a()
      {
        return false;
      }
      
      public final void b() {}
      
      public final boolean c()
      {
        return false;
      }
    };
    F.ao = ((b.a)localObject);
    localObject = new b.a(2131690790, 2131690789, 2131690152, 2131361955, 2131361863, 1, false, 2131689885)
    {
      public final Intent a(Context paramAnonymousContext)
      {
        Object localObject2 = paramAnonymousContext.getPackageManager();
        Object localObject1 = paramAnonymousContext.getString(2131690571);
        localObject2 = ((PackageManager)localObject2).getInstalledPackages(0).iterator();
        do
        {
          if (!((Iterator)localObject2).hasNext()) {
            break;
          }
        } while (!((PackageInfo)((Iterator)localObject2).next()).packageName.equals(localObject1));
        for (int i = 1;; i = 0)
        {
          if (i != 0)
          {
            localObject1 = new Intent("android.intent.action.VIEW", Uri.parse("twitter://user?screen_name=fleksy"));
            ((Intent)localObject1).setPackage(paramAnonymousContext.getString(2131690571));
            return localObject1;
          }
          return new Intent("android.intent.action.VIEW", Uri.parse("https://twitter.com/intent/user?screen_name=fleksy"));
        }
      }
    };
    ((b.a)localObject).b(2130837678);
    G.ao = ((b.a)localObject);
    localObject = new b.a(2131690000, 2131689997, 2131690128, 2131361955, 2131361815, 200, false, 2131689999);
    H.ao = ((b.a)localObject);
    localObject = new b.a(2131689754, 2131689751, 2131690128, 2131361955, 2131361805, 500, false, 2131689753);
    I.ao = ((b.a)localObject);
    localObject = new b.a(2131690049, 2131690046, 2131690128, 2131361955, 2131361819, 1000, false, 2131690048);
    J.ao = ((b.a)localObject);
    localObject = new b.a(2131690441, 2131690438, 2131690138, 2131361955, 2131361816, 3, false, 2131690440);
    K.ao = ((b.a)localObject);
    localObject = new b.a(2131690507, 2131690504, 2131690138, 2131361955, 2131361838, 7, false, 2131690506);
    L.ao = ((b.a)localObject);
    localObject = new b.a(2131690343, 2131690340, 2131690138, 2131361955, 2131361832, 21, false, 2131690342);
    M.ao = ((b.a)localObject);
    localObject = new b.a(2131690088, 2131690086, 2131690135, 2131361826, 2131361827, 1, true, 2131689885);
    N.ao = ((b.a)localObject);
    localObject = new b.a(2131690962, 2131690958, 2131690154, 2131361867, 2131361868, 1, true, 2131690961);
    ((b.a)localObject).a(2131690960);
    ((b.a)localObject).b(2130837710);
    z.ao = ((b.a)localObject);
    localObject = new b.a(2131690459, 2131690457, 2131690140, 2131361955, 2131361835, 10, false, 2131689885);
    q.ao = ((b.a)localObject);
    localObject = new b.a(2131690607, 2131690605, 2131690148, 2131361955, 2131361843, 1, true, 2131689885)
    {
      public final Intent a(Context paramAnonymousContext)
      {
        return com.syntellia.fleksy.utils.notifications.a.a(paramAnonymousContext, BadgesStatsActivity.class);
      }
      
      public final Intent a(Context paramAnonymousContext, a paramAnonymousA)
      {
        return com.syntellia.fleksy.utils.notifications.a.a(paramAnonymousContext, "new_fleksy.jpg", b(paramAnonymousContext, paramAnonymousA));
      }
    };
    ((b.a)localObject).b(2130837707);
    f.ao = ((b.a)localObject);
    localObject = new b.a(2131690364, 2131690362, 2131690137, 2131361955, 2131361833, 1, false, 2131689885);
    o.ao = ((b.a)localObject);
    localObject = new b.a(2131689914, 2131689912, 2131690125, 2131361955, 2131361809, 100, false, 2131689885);
    m.ao = ((b.a)localObject);
    localObject = new b.a(2131690669, 2131690667, 2131690150, 2131361955, 2131361855, 1, false, 2131689885);
    r.ao = ((b.a)localObject);
    localObject = new b.a(2131690687, 2131690684, 2131690151, 2131361868, 2131361857, 1, false, 2131689885);
    ((b.a)localObject).a(2131690686);
    O.ao = ((b.a)localObject);
    localObject = new b.a(2131690683, 2131690680, 2131690151, 2131361853, 2131361830, 1, false, 2131689885);
    ((b.a)localObject).a(2131690682);
    P.ao = ((b.a)localObject);
    localObject = new b.a(2131690449, 2131690447, 2131690139, 2131361955, 2131361834, 8, false, 2131689885) {};
    l.ao = ((b.a)localObject);
    localObject = new b.a(2131690516, 2131690514, 2131690142, 2131361839, 2131361834, 1, false, 2131689885)
    {
      public final Intent a(Context paramAnonymousContext)
      {
        return com.syntellia.fleksy.utils.notifications.a.a(paramAnonymousContext, MainActivity.class);
      }
    };
    ((b.a)localObject).b(2130837696);
    Q.ao = ((b.a)localObject);
    localObject = new b.a(2131690103, 2131690101, 2131690136, 2131361839, 2131361838, 1, false, 2131689885)
    {
      public final Intent a(Context paramAnonymousContext)
      {
        return com.syntellia.fleksy.utils.notifications.a.a(paramAnonymousContext, MainActivity.class);
      }
    };
    ((b.a)localObject).b(2130837696);
    R.ao = ((b.a)localObject);
    localObject = new b.a(2131689767, 2131689765, 2131690122, 2131361955, 2131361806, 1, false, 2131689885);
    S.ao = ((b.a)localObject);
    localObject = new b.a(2131689757, 2131689755, 2131690121, 2131361955, 2131361845, 100, false, 2131689885);
    T.ao = ((b.a)localObject);
    localObject = new b.a(2131690595, 2131690592, 2131690121, 2131361955, 2131361849, 1000, false, 2131690596)
    {
      public final int b(Context paramAnonymousContext)
      {
        if ((b.b(paramAnonymousContext, a.b)) || (com.syntellia.b.a.a.a(paramAnonymousContext))) {
          return 2131690593;
        }
        return super.b(paramAnonymousContext);
      }
      
      public final String b(Context paramAnonymousContext, a paramAnonymousA)
      {
        return paramAnonymousContext.getString(paramAnonymousA.b().i()) + " " + paramAnonymousContext.getString(2131690377, new Object[] { paramAnonymousContext.getString(paramAnonymousA.d()) });
      }
    };
    U.ao = ((b.a)localObject);
    localObject = new b.a(2131690091, 2131690089, 2131690121, 2131361955, 2131361846, 10000, false, 2131689885);
    V.ao = ((b.a)localObject);
    localObject = new b.a(2131690503, 2131690501, 2131690121, 2131361847, 2131361848, 100000, false, 2131689885);
    W.ao = ((b.a)localObject);
    localObject = new b.a(2131689720, 2131689718, 2131690119, 2131361802, 2131361803, 1, false, 2131689885);
    X.ao = ((b.a)localObject);
    localObject = new b.a(2131690908, 2131690906, 2131690153, 2131361864, 2131361865, 1, true, 2131689885);
    Y.ao = ((b.a)localObject);
    localObject = new b.a(2131690952, 2131690951, 2131690145, 2131361841, 2131361842, 1, true, 2131689885);
    Z.ao = ((b.a)localObject);
    localObject = new b.a(2131690954, 2131690953, 2131690146, 2131361841, 2131361842, 1, true, 2131689885);
    aa.ao = ((b.a)localObject);
    localObject = new b.a(2131690956, 2131690955, 2131690147, 2131361841, 2131361842, 1, true, 2131689885);
    ab.ao = ((b.a)localObject);
    ac.ao = new b.a(2131690689, 2131690688, 2131690261, 2131361859, 2131361858, 1, false, 2131689885);
    ad.ao = new b.a(2131690300, 2131690298, 2131690262, 2131361829, 2131361858, 5, false, 2131689885);
    ae.ao = new b.a(2131689927, 2131689925, 2131690263, 2131361812, 2131361858, 10, false, 2131689885);
    af.ao = new b.a(2131690462, 2131690460, 2131690199, 2131361837, 2131361836, 1, false, 2131689885);
    ag.ao = new b.a(2131690081, 2131690079, 2131690199, 2131361825, 2131361836, 5, false, 2131689885);
    ah.ao = new b.a(2131690787, 2131690785, 2131690199, 2131361862, 2131361836, 100, false, 2131689885);
    ai.ao = new b.a(2131690640, 2131690638, 2131690289, 2131361850, 2131361836, 1, false, 2131689885);
    aj.ao = new b.a(2131690643, 2131690641, 2131690289, 2131361851, 2131361836, 5, false, 2131689885);
    ak.ao = new b.a(2131690094, 2131690092, 2131690289, 2131361828, 2131361836, 100, false, 2131689885);
    localObject = new b.a(2131690696, 2131690694, 2131690293, 2131361861, 2131361860, 1, false, 2131689885)
    {
      public final Intent a(Context paramAnonymousContext)
      {
        return com.syntellia.fleksy.utils.notifications.a.a(paramAnonymousContext, ThemeBuilderActivity.class);
      }
    };
    ((b.a)localObject).b(2130837696);
    al.ao = ((b.a)localObject);
  }
  
  private a(int paramInt1, int paramInt2)
  {
    this.am = paramInt1;
    this.an = paramInt2;
  }
  
  private void a(b.a paramA)
  {
    this.ao = paramA;
  }
  
  public final int a(Context paramContext)
  {
    return paramContext.getSharedPreferences("achievement_prefs", 0).getInt(paramContext.getString(this.am), 0);
  }
  
  public final boolean a()
  {
    return this.ao != null;
  }
  
  public final boolean a(Context paramContext, int paramInt)
  {
    SharedPreferences localSharedPreferences = paramContext.getSharedPreferences("achievement_prefs", 0);
    String str = paramContext.getString(this.am);
    int i1 = a(paramContext);
    if (i1 == this.ao.h()) {
      return false;
    }
    paramInt = Math.min(paramInt, this.ao.h() - i1);
    localSharedPreferences.edit().putInt(str, paramInt + i1).apply();
    return true;
  }
  
  public final b.a b()
  {
    return this.ao;
  }
  
  public final int c()
  {
    return this.am;
  }
  
  public final int d()
  {
    return this.an;
  }
  
  public final boolean e()
  {
    return this.an != -1;
  }
}
