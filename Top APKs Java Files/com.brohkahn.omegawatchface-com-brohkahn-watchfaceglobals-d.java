package com.brohkahn.watchfaceglobals;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Paint.Align;
import android.graphics.Typeface;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class d
{
  public static Typeface a(a paramA, AssetManager paramAssetManager)
  {
    switch (1.a[paramA.ordinal()])
    {
    default: 
      paramA = "fonts/" + paramA.toString().toLowerCase() + ".ttf";
    }
    try
    {
      paramAssetManager = Typeface.createFromAsset(paramAssetManager, paramA);
      return paramAssetManager;
    }
    catch (Exception paramAssetManager)
    {
      Log.d("WatchFaceLibrary", "Unable to load typeface " + paramA + " from assets.");
    }
    return Typeface.create(Typeface.DEFAULT, 0);
    return Typeface.create(Typeface.DEFAULT, 2);
    return Typeface.create(Typeface.DEFAULT, 1);
    return Typeface.create(Typeface.DEFAULT, 1);
    return Typeface.create(Typeface.DEFAULT, 1);
  }
  
  public static List<String> a(Resources paramResources)
  {
    ArrayList localArrayList = new ArrayList();
    if (paramResources == null)
    {
      localArrayList.add("showFitness");
      localArrayList.add("weatherHighLowVisible");
      localArrayList.add("weatherSunriseSunsetVisible");
      localArrayList.add("weatherWindVisible");
      localArrayList.add("weatherDescriptionVisible");
      localArrayList.add("allowTapEvents");
      return localArrayList;
    }
    localArrayList.add(paramResources.getString(l.b.show_fitness_key));
    localArrayList.add(paramResources.getString(l.b.high_low_element_key));
    localArrayList.add(paramResources.getString(l.b.sunrise_sunset_element_key));
    localArrayList.add(paramResources.getString(l.b.wind_element_key));
    localArrayList.add(paramResources.getString(l.b.description_element_key));
    localArrayList.add(paramResources.getString(l.b.allow_tap_events_key));
    return localArrayList;
  }
  
  public static boolean a(Context paramContext)
  {
    try
    {
      String str = paramContext.getPackageName().replace("watchface", "");
      paramContext = paramContext.getPackageManager().getInstalledApplications(0).iterator();
      while (paramContext.hasNext())
      {
        boolean bool = ((ApplicationInfo)paramContext.next()).packageName.equals(str);
        if (bool) {
          return true;
        }
      }
    }
    catch (Exception paramContext)
    {
      Log.d("Helpers", paramContext.getMessage());
    }
    return false;
  }
  
  public static boolean a(n paramN)
  {
    switch (1.b[paramN.ordinal()])
    {
    default: 
      return true;
    }
    return false;
  }
  
  public static float b(n paramN)
  {
    float f = 36.0F;
    switch (1.b[paramN.ordinal()])
    {
    default: 
      f = 0.0F;
    case 6: 
    case 18: 
      return f;
    case 7: 
      return 24.0F;
    case 8: 
      return 18.0F;
    case 9: 
    case 10: 
    case 11: 
      return 12.0F;
    case 12: 
      return 18.0F;
    case 1: 
    case 2: 
    case 3: 
    case 4: 
      return 12.0F;
    case 13: 
    case 14: 
      return 12.0F;
    case 15: 
    case 16: 
    case 17: 
      return 12.0F;
    case 5: 
      return 15.0F;
    }
    return 14.0F;
  }
  
  public static List<String> b(Resources paramResources)
  {
    List localList = a(paramResources);
    if (paramResources == null)
    {
      localList.add("weatherPoll");
      localList.add("weatherProvider");
    }
    for (;;)
    {
      paramResources = n.values();
      int j = paramResources.length;
      int i = 0;
      while (i < j)
      {
        Object localObject = paramResources[i];
        localList.add(localObject.toString() + "YOffset");
        localList.add(localObject.toString() + "XOffset");
        localList.add(localObject.toString() + "TextAlignment");
        localList.add(localObject.toString() + "IconSide");
        i += 1;
      }
      localList.add(paramResources.getString(l.b.weather_poll_interval_key));
      localList.add(paramResources.getString(l.b.weather_provider_key));
    }
    return localList;
  }
  
  public static Paint.Align c(n paramN)
  {
    return Paint.Align.CENTER;
  }
  
  public static float d(n paramN)
  {
    return 50.0F;
  }
  
  public static float e(n paramN)
  {
    float f = 52.0F;
    switch (1.b[paramN.ordinal()])
    {
    default: 
      f = 0.0F;
    case 6: 
    case 7: 
    case 19: 
      return f;
    case 8: 
      return 35.0F;
    case 5: 
      return 60.0F;
    case 9: 
      return 62.0F;
    case 10: 
      return 74.0F;
    case 11: 
      return 68.0F;
    case 12: 
      return 25.0F;
    case 1: 
    case 2: 
    case 3: 
    case 4: 
      return 15.0F;
    case 13: 
    case 14: 
      return 82.0F;
    case 15: 
    case 16: 
    case 17: 
      return 90.0F;
    }
    return 90.0F;
  }
  
  public static boolean f(n paramN)
  {
    switch (1.b[paramN.ordinal()])
    {
    case 8: 
    default: 
      return true;
    }
    return false;
  }
  
  public static int g(n paramN)
  {
    return 0;
  }
  
  public static int h(n paramN)
  {
    switch (1.b[paramN.ordinal()])
    {
    default: 
      return 0;
    case 6: 
      return l.a.ic_schedule_black;
    case 7: 
      return l.a.ic_hourglass_empty_black;
    case 8: 
      return l.a.ic_event_black;
    case 5: 
      return l.a.ic_public_black;
    case 9: 
      return l.a.ic_event_note_black;
    case 10: 
      return l.a.ic_schedule_black;
    case 11: 
      return l.a.ic_place_black;
    case 12: 
      return l.a.ic_ac_unit_black;
    case 1: 
      return l.a.ic_wb_sunny_black;
    case 4: 
      return l.a.wind_day_black;
    case 2: 
      return l.a.ic_swap_vert_black;
    case 3: 
      return l.a.ic_sunrise_sunset_black;
    case 13: 
      return l.a.icon_phone_black;
    case 14: 
      return l.a.icon_watch_black;
    case 16: 
      return l.a.ic_steps_black;
    case 15: 
      return l.a.ic_whatshot_black;
    case 17: 
      return l.a.ic_favorite_black;
    case 19: 
      return l.a.ic_logo_developer;
    }
    return l.a.ic_logo_app;
  }
  
  public static m i(n paramN)
  {
    switch (1.b[paramN.ordinal()])
    {
    default: 
      return m.a;
    case 5: 
    case 6: 
    case 7: 
    case 8: 
      return m.c;
    case 9: 
    case 10: 
    case 11: 
      return m.d;
    case 1: 
    case 2: 
    case 3: 
    case 4: 
    case 12: 
      return m.b;
    case 16: 
      return m.f;
    case 15: 
      return m.e;
    case 17: 
      return m.g;
    case 14: 
      return m.i;
    }
    return m.h;
  }
}
