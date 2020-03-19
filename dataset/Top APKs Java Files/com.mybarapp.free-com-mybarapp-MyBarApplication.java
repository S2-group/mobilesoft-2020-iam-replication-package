package com.mybarapp;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;
import android.widget.ImageView;
import com.crashlytics.android.c.l.a;
import com.google.android.gms.internal.ads.zzzc;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuth.a;
import com.google.firebase.auth.y;
import com.mybarapp.android_util.g.a;
import com.mybarapp.c.a.ab;
import com.mybarapp.c.a.ae;
import com.mybarapp.c.a.ag;
import com.mybarapp.c.a.c;
import com.mybarapp.c.a.c.a;
import com.mybarapp.c.a.h;
import com.mybarapp.c.a.j;
import com.mybarapp.c.a.p;
import com.mybarapp.c.a.r;
import com.mybarapp.c.a.z;
import com.mybarapp.d.b.a;
import com.mybarapp.d.b.d;
import com.mybarapp.d.b.f;
import com.mybarapp.model.c.a;
import com.mybarapp.model.p;
import com.mybarapp.model.s;
import com.mybarapp.model.s.a;
import com.mybarapp.model.w;
import com.mybarapp.util.m;
import com.squareup.picasso.t;
import com.squareup.picasso.x;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class MyBarApplication
  extends Application
{
  public static volatile boolean a = false;
  @SuppressLint({"StaticFieldLeak"})
  private static volatile Context b;
  private static h c;
  private static String d;
  
  public MyBarApplication() {}
  
  public static h a()
  {
    try
    {
      if (c == null)
      {
        com.mybarapp.util.o localO1 = com.mybarapp.util.j.a("app_create_mybar");
        g.a.a().a.containsKey("");
        localObject1 = FirebaseAuth.getInstance();
        Object localObject3 = -..Lambda.MyBarApplication.KMfWPBp28C8-sn5whyKuyBzFiyo.INSTANCE;
        ((FirebaseAuth)localObject1).b.add(localObject3);
        ((FirebaseAuth)localObject1).d.execute(new y((FirebaseAuth)localObject1, (FirebaseAuth.a)localObject3));
        com.mybarapp.storage.b localB = a(b);
        localObject3 = new com.mybarapp.d.c(new com.mybarapp.storage.a(localB));
        localObject1 = new p(new com.mybarapp.android_util.d(b.getResources()));
        Object localObject4 = new com.mybarapp.android_util.c(b.getResources());
        com.mybarapp.util.o localO2 = com.mybarapp.util.j.a("parse");
        Object localObject6 = new com.mybarapp.model.c((w)localObject4, (p)localObject1);
        localObject4 = new c.a();
        Object localObject5 = com.mybarapp.util.j.a("parse_proto_all");
        localObject1 = ((com.mybarapp.model.c)localObject6).a.a();
        Object localObject9 = ((com.mybarapp.model.c)localObject6).a.b();
        Object localObject8 = ((com.mybarapp.model.c)localObject6).a.c();
        Object localObject7 = ((com.mybarapp.model.c)localObject6).a.d();
        ((com.mybarapp.util.o)localObject5).a("MyBar.BarParserPb", new String[] { "Parsed all protobufs" });
        localObject5 = com.mybarapp.util.j.a("parse_model_create_total");
        Object localObject10 = com.mybarapp.util.j.a("parse_model_create_glasses");
        ((c.a)localObject4).a = new ArrayList(((a.r)localObject1).b());
        ((c.a)localObject4).b = new HashMap();
        localObject1 = ((a.r)localObject1).a.iterator();
        Object localObject11;
        while (((Iterator)localObject1).hasNext())
        {
          localObject11 = (a.p)((Iterator)localObject1).next();
          String str = ((a.p)localObject11).a;
          com.mybarapp.model.k localK = p.a("extra_".concat(String.valueOf(str)));
          Object localObject12 = new StringBuilder("extra_");
          ((StringBuilder)localObject12).append(str);
          ((StringBuilder)localObject12).append("_icon");
          localObject12 = p.a(((StringBuilder)localObject12).toString());
          localObject11 = new com.mybarapp.model.i(str, ((a.p)localObject11).b, ((a.p)localObject11).c, localK, (com.mybarapp.model.k)localObject12);
          ((c.a)localObject4).a.add(localObject11);
          ((c.a)localObject4).b.put(((com.mybarapp.model.i)localObject11).a, localObject11);
        }
        ((com.mybarapp.util.o)localObject10).a("MyBar.BarParserPb", new String[] { "Glasses model create" });
        localObject1 = com.mybarapp.util.j.a("parse_model_create_units");
        ((c.a)localObject4).c = new ArrayList(((a.ag)localObject9).b());
        ((c.a)localObject4).d = new HashMap();
        localObject9 = ((a.ag)localObject9).a.iterator();
        while (((Iterator)localObject9).hasNext())
        {
          localObject11 = (a.ae)((Iterator)localObject9).next();
          localObject10 = ((a.ae)localObject11).a;
          localObject11 = p.a((a.ae)localObject11, ((c.a)localObject4).d);
          ((c.a)localObject4).d.put(localObject10, localObject11);
          ((c.a)localObject4).c.add(localObject11);
        }
        ((com.mybarapp.util.o)localObject1).a("MyBar.BarParserPb", new String[] { "Units model create" });
        localObject1 = com.mybarapp.util.j.a("parse_model_create_baritems");
        ((c.a)localObject4).e = new ArrayList(((a.j)localObject8).b());
        ((c.a)localObject4).f = new HashMap();
        localObject8 = ((a.j)localObject8).a.iterator();
        while (((Iterator)localObject8).hasNext())
        {
          localObject9 = (a.h)((Iterator)localObject8).next();
          localObject9 = ((com.mybarapp.model.c)localObject6).b.a((a.h)localObject9);
          ((c.a)localObject4).e.add(localObject9);
          ((c.a)localObject4).f.put(((com.mybarapp.model.a)localObject9).b, localObject9);
        }
        ((com.mybarapp.util.o)localObject1).a("MyBar.BarParserPb", new String[] { "BarItems model create" });
        localObject1 = com.mybarapp.util.j.a("parse_model_create_recipes");
        ((c.a)localObject4).g = new ArrayList(((a.ab)localObject7).b());
        ((c.a)localObject4).h = new HashMap();
        localObject7 = ((a.ab)localObject7).a.iterator();
        while (((Iterator)localObject7).hasNext())
        {
          localObject8 = (a.z)((Iterator)localObject7).next();
          localObject8 = ((com.mybarapp.model.c)localObject6).b.a((a.z)localObject8, m.a(((c.a)localObject4).d), m.a(((c.a)localObject4).b), m.a(((c.a)localObject4).f));
          if (localObject8 == null)
          {
            com.mybarapp.util.j.b("parse_recipe");
          }
          else
          {
            ((c.a)localObject4).g.add(localObject8);
            ((c.a)localObject4).h.put(((s)localObject8).b, localObject8);
          }
        }
        ((com.mybarapp.util.o)localObject1).a("MyBar.BarParserPb", new String[] { "Recipes model create" });
        if (((c.a)localObject4).e.size() != ((c.a)localObject4).f.size())
        {
          localObject1 = new StringBuilder("Wrong mapping, bar items size (");
          ((StringBuilder)localObject1).append(((c.a)localObject4).e.size());
          ((StringBuilder)localObject1).append(") is different from bar items by ID size (");
          ((StringBuilder)localObject1).append(((c.a)localObject4).f.size());
          ((StringBuilder)localObject1).append(")");
          com.mybarapp.util.j.a("parse_mapping_all_bar_items", ((StringBuilder)localObject1).toString());
        }
        if (((c.a)localObject4).g.size() != ((c.a)localObject4).h.size())
        {
          localObject1 = new StringBuilder("Wrong mapping, recipes size (");
          ((StringBuilder)localObject1).append(((c.a)localObject4).g.size());
          ((StringBuilder)localObject1).append(") is different from recipes by ID size (");
          ((StringBuilder)localObject1).append(((c.a)localObject4).h.size());
          ((StringBuilder)localObject1).append(")");
          com.mybarapp.util.j.a("parse_mapping_all_recipes", ((StringBuilder)localObject1).toString());
        }
        localObject1 = new StringBuilder("Loaded ");
        ((StringBuilder)localObject1).append(((c.a)localObject4).e.size());
        ((StringBuilder)localObject1).append(" bar items, ");
        ((StringBuilder)localObject1).append(((c.a)localObject4).g.size());
        ((StringBuilder)localObject1).append(" recipes");
        com.mybarapp.util.h.c("MyBar.BarParserPb", ((StringBuilder)localObject1).toString());
        ((com.mybarapp.util.o)localObject5).a("MyBar.BarParserPb", new String[] { "Model create" });
        localO2.a("MyBar.BarParserPb", new String[] { "Parsing time" });
        localObject1 = b;
        localO2 = com.mybarapp.util.j.a("app_upgrade_app_data");
        localObject5 = new com.mybarapp.storage.j(PreferenceManager.getDefaultSharedPreferences((Context)localObject1));
        a(localB, (com.mybarapp.storage.j)localObject5, (Context)localObject1);
        if (!((com.mybarapp.d.c)localObject3).a(com.mybarapp.d.c.h))
        {
          ((com.mybarapp.d.c)localObject3).a(com.mybarapp.d.c.m, a.c.b().a(com.mybarapp.auth.a.a).e());
          localObject6 = (TelephonyManager)b.getSystemService("phone");
          if (localObject6 != null)
          {
            boolean bool = "US".equalsIgnoreCase(((TelephonyManager)localObject6).getNetworkCountryIso()) ^ true;
            ((com.mybarapp.d.c)localObject3).a(com.mybarapp.d.c.b, bool);
            com.mybarapp.util.h.c("MyBarApplication", "Set use metric system to: ".concat(String.valueOf(bool)));
          }
          ((com.mybarapp.d.c)localObject3).a(com.mybarapp.d.c.h, true);
          com.mybarapp.util.h.c("MyBarApplication", "Initialized local settings.");
        }
        a((c.a)localObject4, (com.mybarapp.storage.j)localObject5, localB, (Context)localObject1);
        localO2.a("MyBarApplication", new String[] { "Upgrade app data" });
        localObject1 = b((com.mybarapp.d.c)localObject3, (c.a)localObject4, com.mybarapp.auth.a.a((com.mybarapp.d.c)localObject3), b);
        if ((!e.a) && (!((com.mybarapp.d.c)localObject3).a(com.mybarapp.d.c.g)) && (c()))
        {
          a = true;
          com.mybarapp.util.j.c("app_appturbo_unlock");
          ((com.mybarapp.d.c)localObject3).a(com.mybarapp.d.c.g, true);
        }
        localO1.a("MyBarApplication", new String[] { "Initialized MyBar" });
        c = new h((com.mybarapp.d.c)localObject3, new c()
        {
          public final b a(com.mybarapp.c.a.a paramAnonymousA)
          {
            return MyBarApplication.a(this.a, this.b, paramAnonymousA, MyBarApplication.b());
          }
          
          public final void a(com.mybarapp.c.a.a paramAnonymousA1, com.mybarapp.c.a.a paramAnonymousA2)
          {
            MyBarApplication.a(paramAnonymousA1, paramAnonymousA2);
          }
          
          public final void b(com.mybarapp.c.a.a paramAnonymousA)
          {
            MyBarApplication.a(paramAnonymousA, MyBarApplication.b()).b();
          }
        }, (b)localObject1);
      }
      Object localObject1 = c;
      return localObject1;
    }
    finally {}
  }
  
  private static com.mybarapp.storage.b a(Context paramContext)
  {
    return com.mybarapp.storage.c.a(new com.mybarapp.storage.o("local", f.a(), paramContext));
  }
  
  private static <T extends com.mybarapp.model.l> Map<String, String> a(Iterable<T> paramIterable, com.mybarapp.storage.a paramA)
  {
    HashSet localHashSet = new HashSet();
    paramIterable = paramIterable.iterator();
    while (paramIterable.hasNext())
    {
      Object localObject1 = (com.mybarapp.model.l)paramIterable.next();
      Object localObject2 = ((com.mybarapp.model.l)localObject1).e();
      if (localObject2 != null)
      {
        localObject2 = com.mybarapp.model.k.b((com.mybarapp.model.k)localObject2);
        if (localObject2 != null) {
          localHashSet.add(new File((String)localObject2));
        }
      }
      localObject1 = ((com.mybarapp.model.l)localObject1).d();
      if (localObject1 != null)
      {
        localObject1 = com.mybarapp.model.k.b((com.mybarapp.model.k)localObject1);
        if (localObject1 != null) {
          localHashSet.add(new File((String)localObject1));
        }
      }
    }
    return g.a(paramA, com.google.common.collect.u.a(localHashSet));
  }
  
  private void a(Configuration paramConfiguration)
  {
    if ("uk".equals(b(paramConfiguration)))
    {
      paramConfiguration = new Configuration(paramConfiguration);
      Locale localLocale = new Locale("ru");
      paramConfiguration.locale = localLocale;
      Locale.setDefault(localLocale);
      getBaseContext().getResources().updateConfiguration(paramConfiguration, getBaseContext().getResources().getDisplayMetrics());
      com.mybarapp.util.h.c("MyBarApplication", "Changed Ukrainian language to Russian.");
    }
  }
  
  private static void a(c.a paramA, com.mybarapp.storage.j paramJ, com.mybarapp.storage.b paramB, Context paramContext)
  {
    if (paramJ.b(com.mybarapp.storage.j.c.d(), ((Boolean)com.mybarapp.storage.j.c.a).booleanValue()))
    {
      com.mybarapp.util.h.c("MyBarApplication", "No need to convert from prefs storage, already converted");
      com.mybarapp.util.j.c("prefs_storage_already_converted");
      return;
    }
    if (!a(paramJ))
    {
      com.mybarapp.util.h.c("MyBarApplication", "Prefs storage doesn't have any My Bar data");
      com.mybarapp.util.j.c("prefs_storage_empty");
      return;
    }
    com.mybarapp.util.o localO = com.mybarapp.util.j.a("app_prefs_to_basic");
    com.mybarapp.d.c localC = new com.mybarapp.d.c(paramJ);
    com.mybarapp.storage.q.a(localC, paramB);
    paramB = b(com.mybarapp.auth.a.a, paramContext);
    paramContext = new com.mybarapp.storage.a(paramB);
    Object localObject3 = new ArrayList(paramA.e);
    Object localObject1 = com.mybarapp.model.d.a(paramJ.b(com.mybarapp.storage.j.a.d(), (String)com.mybarapp.storage.j.a.a));
    ((List)localObject3).addAll((Collection)localObject1);
    Object localObject2 = new HashMap();
    localObject3 = ((List)localObject3).iterator();
    while (((Iterator)localObject3).hasNext())
    {
      localObject4 = (com.mybarapp.model.a)((Iterator)localObject3).next();
      ((Map)localObject2).put(((com.mybarapp.model.a)localObject4).b, localObject4);
    }
    localObject3 = a((Iterable)localObject1, paramContext);
    g.a((Iterable)localObject1, (Map)localObject3);
    Object localObject4 = m.a(paramA.d);
    paramA = m.a(paramA.b);
    localObject2 = m.a((Map)localObject2);
    paramA = com.mybarapp.model.d.a(paramJ.b(com.mybarapp.storage.j.b.d(), (String)com.mybarapp.storage.j.b.a), (m)localObject2, paramA, (m)localObject4);
    ((Map)localObject3).putAll(a(paramA, paramContext));
    g.b(paramA, (Map)localObject3);
    paramContext.a((Iterable)localObject1, p.a);
    localObject2 = new StringBuilder("Saved ");
    ((StringBuilder)localObject2).append(((List)localObject1).size());
    ((StringBuilder)localObject2).append(" custom bar items to new storage.");
    com.mybarapp.util.h.c("MyBarApplication", ((StringBuilder)localObject2).toString());
    paramContext.b(paramA, p.b);
    localObject1 = new StringBuilder("Saved ");
    ((StringBuilder)localObject1).append(paramA.size());
    ((StringBuilder)localObject1).append(" recipes to new storage.");
    com.mybarapp.util.h.c("MyBarApplication", ((StringBuilder)localObject1).toString());
    com.mybarapp.storage.q.b(localC, paramB);
    new com.mybarapp.d.a(paramContext).a(com.mybarapp.d.a.a, true);
    paramJ.a(com.mybarapp.storage.j.c.d(), true);
    com.mybarapp.util.j.c("prefs_storage_converted");
    localO.a("MyBarApplication", new String[] { "Converted prefs storage to new basic storage" });
  }
  
  private static void a(com.mybarapp.storage.b paramB, com.mybarapp.storage.j paramJ, Context paramContext)
  {
    if (e.a) {
      if (!paramB.d()) {
        return;
      }
    }
    try
    {
      Context localContext = paramContext.createPackageContext("com.mybarapp.free", 0);
      Object localObject1 = a(localContext);
      if (!((com.mybarapp.storage.b)localObject1).d())
      {
        paramJ = com.mybarapp.util.j.a("app_copy_storage_free_pro");
        com.mybarapp.storage.q.a((com.mybarapp.storage.b)localObject1, paramB, false);
        com.mybarapp.util.h.c("MyBarApplication", "Copied local storage from Free to Pro");
        paramB = new com.mybarapp.d.c(new com.mybarapp.storage.a(paramB)).a().iterator();
        while (paramB.hasNext())
        {
          localObject1 = (com.mybarapp.c.a.a)paramB.next();
          Object localObject2 = b((com.mybarapp.c.a.a)localObject1, localContext);
          com.mybarapp.storage.b localB = b((com.mybarapp.c.a.a)localObject1, paramContext);
          if ((!((com.mybarapp.storage.b)localObject2).d()) && (localB.d()))
          {
            com.mybarapp.storage.q.a((com.mybarapp.storage.b)localObject2, localB, false);
            localObject2 = new StringBuilder("Copied basic storage from Fre to Pro for account ");
            ((StringBuilder)localObject2).append(((com.mybarapp.c.a.a)localObject1).a);
            com.mybarapp.util.h.c("MyBarApplication", ((StringBuilder)localObject2).toString());
          }
        }
        paramJ.a("MyBarApplication", new String[] { "Data copy from Free to Pro (new)" });
        return;
      }
      paramB = new com.mybarapp.storage.j(localContext.getSharedPreferences("com.mybarapp.free_preferences", 0));
      if ((!a(paramJ)) && (a(paramB)))
      {
        paramJ.a(paramB);
        com.mybarapp.util.j.c("app_copy_old_prefs_storage_free_to_pro");
      }
      return;
    }
    catch (PackageManager.NameNotFoundException paramB) {}
    return;
  }
  
  private static void a(Map<String, com.mybarapp.model.a> paramMap, com.mybarapp.d.a paramA)
  {
    ArrayList localArrayList = new ArrayList();
    String[] arrayOfString = com.mybarapp.model.f.a;
    int j = arrayOfString.length;
    int i = 0;
    while (i < j)
    {
      String str = arrayOfString[i];
      Object localObject = (com.mybarapp.model.a)paramMap.get(str);
      if (localObject != null)
      {
        localArrayList.add(localObject);
      }
      else
      {
        localObject = new StringBuilder("Default bar item with ID ");
        ((StringBuilder)localObject).append(str);
        ((StringBuilder)localObject).append(" not found!");
        com.mybarapp.util.h.d("MyBarApplication", ((StringBuilder)localObject).toString());
      }
      i += 1;
    }
    paramA.a(localArrayList);
    com.mybarapp.util.h.c("MyBarApplication", "Added default bar items");
  }
  
  private static boolean a(com.mybarapp.storage.j paramJ)
  {
    return !new com.mybarapp.d.a(paramJ).a(com.mybarapp.d.a.c).isEmpty();
  }
  
  private static b b(com.mybarapp.d.c paramC, c.a paramA, com.mybarapp.c.a.a paramA1, Context paramContext)
  {
    Object localObject2 = com.mybarapp.util.j.a("parse_result_copy");
    Object localObject1 = new c.a();
    ((c.a)localObject1).a = paramA.a;
    ((c.a)localObject1).b = paramA.b;
    ((c.a)localObject1).c = paramA.c;
    ((c.a)localObject1).d = paramA.d;
    ((c.a)localObject1).e = paramA.e;
    ((c.a)localObject1).f = paramA.f;
    ((c.a)localObject1).g = new ArrayList(paramA.g.size());
    ((c.a)localObject1).h = new HashMap(paramA.g.size());
    paramA = paramA.g.iterator();
    while (paramA.hasNext())
    {
      localObject3 = (s)paramA.next();
      localObject4 = new s.a((s)localObject3);
      localObject4 = new s(((s)localObject3).b, (s.a)localObject4);
      ((s)localObject4).k = ((s)localObject3).k;
      ((c.a)localObject1).g.add(localObject4);
      ((c.a)localObject1).h.put(((s)localObject4).b, localObject4);
    }
    ((com.mybarapp.util.o)localObject2).a("MyBar.BarParserPb", new String[] { "Copy time" });
    paramA = com.mybarapp.util.j.a("app_create_bar_data");
    localObject2 = new com.mybarapp.storage.a(b(paramA1, paramContext));
    Object localObject3 = new com.mybarapp.d.a((com.mybarapp.storage.k)localObject2);
    if (((com.mybarapp.d.a)localObject3).a(com.mybarapp.d.a.a))
    {
      com.mybarapp.util.h.b("MyBarApplication", "Account already initialized.");
    }
    else
    {
      a(((c.a)localObject1).f, (com.mybarapp.d.a)localObject3);
      ((com.mybarapp.d.a)localObject3).c(Collections.emptyList());
      ((com.mybarapp.d.a)localObject3).b(Collections.emptyList());
      ((com.mybarapp.d.a)localObject3).a(Collections.emptySet());
      ((com.mybarapp.d.a)localObject3).a(com.mybarapp.d.a.a, true);
      com.mybarapp.util.h.c("MyBarApplication", "Initialized account settings.");
    }
    localObject3 = new com.mybarapp.d.a((com.mybarapp.storage.k)localObject2);
    Object localObject5 = com.mybarapp.util.j.a("app_storage_load");
    Object localObject4 = new p(new com.mybarapp.android_util.d(paramContext.getResources()));
    Object localObject6 = new HashMap();
    Object localObject7 = ((c.a)localObject1).e.iterator();
    while (((Iterator)localObject7).hasNext())
    {
      localObject8 = (com.mybarapp.model.a)((Iterator)localObject7).next();
      ((Map)localObject6).put(((com.mybarapp.model.a)localObject8).b, localObject8);
    }
    localObject7 = ((com.mybarapp.storage.a)localObject2).a("BarItem", ((p)localObject4).a(m.a((Map)localObject6))).iterator();
    while (((Iterator)localObject7).hasNext())
    {
      localObject8 = (com.mybarapp.model.a)((Iterator)localObject7).next();
      ((Map)localObject6).put(((com.mybarapp.model.a)localObject8).b, localObject8);
    }
    localObject7 = new HashMap();
    Object localObject8 = ((com.mybarapp.d.a)localObject3).a();
    Object localObject9 = ((c.a)localObject1).g.iterator();
    while (((Iterator)localObject9).hasNext())
    {
      localObject10 = (s)((Iterator)localObject9).next();
      if (((Set)localObject8).contains(((s)localObject10).b))
      {
        StringBuilder localStringBuilder = new StringBuilder("Initial recipe ");
        localStringBuilder.append(((s)localObject10).b);
        localStringBuilder.append(" deleted");
        com.mybarapp.util.h.b("MyBarApplication", localStringBuilder.toString());
      }
      else
      {
        ((Map)localObject7).put(((s)localObject10).b, localObject10);
      }
    }
    localObject8 = ((com.mybarapp.storage.a)localObject2).a("Recipe", ((p)localObject4).a(m.a(((c.a)localObject1).d), m.a(((c.a)localObject1).b), m.a((Map)localObject6), m.a(((c.a)localObject1).h))).iterator();
    while (((Iterator)localObject8).hasNext())
    {
      localObject9 = (s)((Iterator)localObject8).next();
      ((Map)localObject7).put(((s)localObject9).b, localObject9);
    }
    ((com.mybarapp.util.o)localObject5).a("MyBarApplication", new String[] { "Storage load time" });
    ((com.mybarapp.storage.a)localObject2).a(((Map)localObject6).values(), ((Map)localObject7).values());
    localObject7 = new com.mybarapp.model.f(((c.a)localObject1).a, ((c.a)localObject1).c, ((Map)localObject6).values(), ((Map)localObject7).values());
    Object localObject10 = new m() {};
    localObject5 = ((p)localObject4).a((m)localObject10);
    localObject8 = new m() {};
    localObject9 = ((p)localObject4).a(m.a(((c.a)localObject1).d), m.a(((c.a)localObject1).b), (m)localObject10, (m)localObject8);
    localObject4 = new com.mybarapp.model.e();
    ((com.mybarapp.model.e)localObject4).addAll(((com.mybarapp.d.a)localObject3).a((m)localObject10));
    localObject6 = new com.mybarapp.model.u();
    ((com.mybarapp.model.u)localObject6).addAll(((com.mybarapp.d.a)localObject3).b((m)localObject10));
    localObject10 = new com.mybarapp.model.h();
    ((com.mybarapp.model.h)localObject10).addAll(((com.mybarapp.d.a)localObject3).c((m)localObject8));
    paramC = new com.mybarapp.model.q((com.mybarapp.model.f)localObject7, (com.mybarapp.model.e)localObject4, (com.mybarapp.model.u)localObject6, new -..Lambda.MyBarApplication.56ztVDRcUDeWa9wkhPqURKy-anQ(paramC));
    localObject1 = new com.mybarapp.a.c((c.a)localObject1, (com.mybarapp.d.a)localObject3, (com.mybarapp.storage.a)localObject2, paramC, (com.mybarapp.model.e)localObject4, (com.mybarapp.model.u)localObject6, (com.mybarapp.model.h)localObject10, (com.google.common.base.g)localObject5, (com.google.common.base.g)localObject9);
    ((com.mybarapp.a.c)localObject1).b();
    paramA.a("MyBarApplication", new String[] { "Initialized Bar Data" });
    return new b(paramA1, (com.mybarapp.model.e)localObject4, (com.mybarapp.model.u)localObject6, (com.mybarapp.model.h)localObject10, (com.mybarapp.d.a)localObject3, paramC, (com.mybarapp.storage.a)localObject2, (com.mybarapp.a.c)localObject1, new com.mybarapp.android_util.a(paramContext.getResources(), (com.mybarapp.storage.h)localObject2));
  }
  
  private static com.mybarapp.storage.b b(com.mybarapp.c.a.a paramA, Context paramContext)
  {
    StringBuilder localStringBuilder = new StringBuilder("account_");
    localStringBuilder.append(paramA.a);
    return new com.mybarapp.storage.o(localStringBuilder.toString(), f.a(), paramContext);
  }
  
  private static String b(Configuration paramConfiguration)
  {
    if (paramConfiguration.locale != null) {
      return paramConfiguration.locale.getLanguage();
    }
    return "";
  }
  
  private static boolean c()
  {
    Object localObject = b.getPackageManager().getInstalledPackages(0).iterator();
    while (((Iterator)localObject).hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
      if ((localPackageInfo.packageName.equalsIgnoreCase("com.appturbo.appturboCA2015")) || (localPackageInfo.packageName.equalsIgnoreCase("com.appturbo.appoftheday2015")) || (localPackageInfo.packageName.equalsIgnoreCase("com.appturbo.appofthenight"))) {
        return true;
      }
    }
    localObject = new Intent("android.intent.action.VIEW");
    ((Intent)localObject).setFlags(268435456);
    ((Intent)localObject).setData(Uri.parse("appturbo://check"));
    return b.getPackageManager().queryIntentActivities((Intent)localObject, 65536).size() > 0;
  }
  
  protected void attachBaseContext(Context paramContext)
  {
    super.attachBaseContext(paramContext);
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    com.mybarapp.util.h.c("MyBarApplication", "onConfigurationChanged");
    a(paramConfiguration);
    paramConfiguration = b(getResources().getConfiguration());
    StringBuilder localStringBuilder = new StringBuilder("Current language: ");
    localStringBuilder.append(paramConfiguration);
    localStringBuilder.append(", last language: ");
    localStringBuilder.append(d);
    com.mybarapp.util.h.c("MyBarApplication", localStringBuilder.toString());
    if (!paramConfiguration.equals(d))
    {
      c = null;
      com.mybarapp.e.i.a();
      com.mybarapp.e.b.a();
    }
    d = paramConfiguration;
  }
  
  public void onCreate()
  {
    super.onCreate();
    com.mybarapp.util.c.a().a(new com.mybarapp.android_util.b());
    Object localObject1 = getApplicationContext();
    Object localObject2 = f.d();
    if ("firebase".equals(localObject2))
    {
      localObject1 = new com.mybarapp.android_util.e((Context)localObject1);
      com.mybarapp.util.h.b("MyBarApplication", "Firebase monitoring provider");
    }
    else if ("flurry".equals(localObject2))
    {
      localObject1 = new com.mybarapp.android_util.f((Context)localObject1);
      com.mybarapp.util.h.b("MyBarApplication", "Flurry monitoring provider");
    }
    else
    {
      localObject1 = new com.mybarapp.util.l();
      com.mybarapp.util.h.b("MyBarApplication", "Null (default) monitoring provider");
    }
    com.mybarapp.util.c.a().a((com.mybarapp.util.k)localObject1);
    com.mybarapp.util.j.a("app_build_code", "code", "free");
    localObject1 = new com.crashlytics.android.a.a();
    localObject2 = new l.a();
    ((l.a)localObject2).a = false;
    localObject2 = ((l.a)localObject2).a();
    boolean bool;
    if (((com.crashlytics.android.a.a)localObject1).c == null)
    {
      ((com.crashlytics.android.a.a)localObject1).c = ((com.crashlytics.android.c.l)localObject2);
      if (((com.crashlytics.android.a.a)localObject1).d != null) {
        if (((com.crashlytics.android.a.a)localObject1).c == null) {
          ((com.crashlytics.android.a.a)localObject1).c = ((com.crashlytics.android.a.a)localObject1).d.a();
        } else {
          throw new IllegalStateException("Must not use Deprecated methods delay(), disabled(), listener(), pinningInfoProvider() with core()");
        }
      }
      if (((com.crashlytics.android.a.a)localObject1).a == null) {
        ((com.crashlytics.android.a.a)localObject1).a = new com.crashlytics.android.a.b();
      }
      if (((com.crashlytics.android.a.a)localObject1).b == null) {
        ((com.crashlytics.android.a.a)localObject1).b = new com.crashlytics.android.b.a();
      }
      if (((com.crashlytics.android.a.a)localObject1).c == null) {
        ((com.crashlytics.android.a.a)localObject1).c = new com.crashlytics.android.c.l();
      }
      io.fabric.sdk.android.c.a(this, new io.fabric.sdk.android.h[] { new com.crashlytics.android.a(((com.crashlytics.android.a.a)localObject1).a, ((com.crashlytics.android.a.a)localObject1).b, ((com.crashlytics.android.a.a)localObject1).c) });
      localObject1 = com.google.firebase.perf.a.a();
      bool = f.c();
    }
    try
    {
      FirebaseApp.getInstance();
      localObject2 = FirebaseApp.getInstance().a().getSharedPreferences("FirebasePerfSharedPrefs", 0);
      if (!((com.google.firebase.perf.a)localObject1).c())
      {
        ((SharedPreferences)localObject2).edit().putBoolean("isEnabled", bool).apply();
        ((com.google.firebase.perf.a)localObject1).c = Boolean.valueOf(bool);
      }
    }
    catch (IllegalStateException localIllegalStateException)
    {
      for (;;) {}
    }
    localObject1 = com.mybarapp.util.j.a("app_create");
    a(getBaseContext().getResources().getConfiguration());
    android.support.v7.app.g.l();
    com.mikepenz.materialdrawer.d.b.a(new com.mikepenz.materialdrawer.d.a()
    {
      public final void a(ImageView paramAnonymousImageView)
      {
        t.a().a(paramAnonymousImageView);
      }
      
      public final void a(ImageView paramAnonymousImageView, Uri paramAnonymousUri, Drawable paramAnonymousDrawable)
      {
        paramAnonymousUri = new x(t.a(), paramAnonymousUri);
        if (paramAnonymousUri.c)
        {
          if (paramAnonymousUri.d == 0)
          {
            paramAnonymousUri.e = paramAnonymousDrawable;
            paramAnonymousUri.a(paramAnonymousImageView, null);
            return;
          }
          throw new IllegalStateException("Placeholder image already set.");
        }
        throw new IllegalStateException("Already explicitly declared as no placeholder.");
      }
    });
    zzzc.zzqq().zza(this, "ca-app-pub-1460316045966691~7452642761", null);
    d = b(getResources().getConfiguration());
    b = getApplicationContext();
    ((com.mybarapp.util.o)localObject1).a("MyBarApplication", new String[] { "onCreate finished" });
    return;
    throw new IllegalStateException("CrashlyticsCore Kit already set.");
  }
}
