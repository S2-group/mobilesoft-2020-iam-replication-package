package com.rappi.base.b;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.appsee.Appsee;
import com.google.gson.Gson;
import com.newrelic.agent.android.NewRelic;
import com.rappi.base.basket.BasketStoreDetail;
import com.rappi.base.basket.datamodel.BasketProduct;
import com.rappi.base.basket.datamodel.ProductSell;
import com.rappi.base.interfaces.Taggable;
import com.rappi.base.models.Address;
import com.rappi.base.models.City;
import com.rappi.base.models.LastScheduledTime;
import com.rappi.base.models.PlaceSorted;
import com.rappi.base.models.Product;
import com.rappi.base.models.Topping;
import com.rappi.base.models.Zone;
import com.rappi.base.models.store.StoreDetail;
import com.rappi.base.models.store.StoreTypeModelV2;
import com.rappi.base.models.z;
import com.rappi.base.others.r;
import com.rappi.base.utils.g;
import io.reactivex.y;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.Callable;
import kotlin.a.ab;
import kotlin.e.a.m;
import kotlin.h.h;
import kotlin.k;
import kotlin.o;
import org.json.JSONObject;
import siftscience.android.Sift;

public final class c
{
  public static final c b = new c();
  private static final kotlin.d c = kotlin.e.a((kotlin.e.a.a)c.d.a);
  
  private c() {}
  
  private final int a(String paramString, int paramInt)
  {
    int j = paramString.hashCode();
    int i = 1;
    if (j != -956638206)
    {
      if ((j == 1271142950) && (paramString.equals("ORDER_WHIM"))) {
        return 1;
      }
    }
    else if (paramString.equals("CART_ADDED_UNIT")) {
      return 1;
    }
    if (paramInt > 0) {
      i = paramInt;
    }
    return i;
  }
  
  private final void a(String paramString, Map<String, String> paramMap, double paramDouble, boolean paramBoolean)
  {
    if (paramMap == null) {
      paramMap = (Map)new LinkedHashMap();
    }
    b.d(paramMap);
    b.e(paramMap);
    if (!paramBoolean)
    {
      Appsee.addEvent(paramString, (Map)new HashMap(paramMap));
      com.rappi.base.b.a.a.a(paramString, b.b(paramMap));
    }
    try
    {
      com.crashlytics.android.a.a(4, paramString, ab.b(paramMap).toString());
      NewRelic.recordBreadcrumb(paramString, ab.b(paramMap));
      o localO1 = o.a;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      o localO2 = o.a;
    }
    com.rappi.base.b.a.d.a.a(paramString, paramMap);
    b.a(new com.rappi.base.b.b.c(paramString, paramDouble, b.c(paramMap)));
    com.rappi.base.b.a.e.a.a(paramString, b.b(paramMap));
  }
  
  private final Bundle b(BasketProduct paramBasketProduct)
  {
    Bundle localBundle = new Bundle(4);
    localBundle.putString("fb_content_id", Product.getCorrectProductId(paramBasketProduct.b()));
    localBundle.putString("fb_content_type", com.rappi.base.b.a.c.a);
    localBundle.putString("fb_currency", b.a());
    localBundle.putString("fb_description", paramBasketProduct.c());
    return localBundle;
  }
  
  private final HashMap<String, String> b(String paramString1, String paramString2, Address paramAddress)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("STORE_TYPE", paramString1);
    localHashMap.put("SOURCE", paramString2);
    localHashMap.put("PLACE", paramString2);
    localHashMap.put("IS_PRIME", String.valueOf(com.rappi.base.rappiPrime.b.a.d()));
    localHashMap.putAll(b.a(paramAddress));
    localHashMap.putAll(b.a(com.rappi.base.p.b.b.a.h()));
    return localHashMap;
  }
  
  private final JSONObject b(Map<String, String> paramMap)
  {
    JSONObject localJSONObject = new JSONObject();
    if (paramMap != null) {}
    try
    {
      paramMap = paramMap.entrySet().iterator();
      while (paramMap.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)paramMap.next();
        localJSONObject.put((String)localEntry.getKey(), (String)localEntry.getValue());
      }
      paramMap = o.a;
      return localJSONObject;
    }
    catch (Exception paramMap)
    {
      for (;;) {}
    }
    paramMap.printStackTrace();
    paramMap = o.a;
    return localJSONObject;
  }
  
  private final Bundle c(List<BasketProduct> paramList)
  {
    paramList = y.fromCallable((Callable)new c.b(paramList)).subscribeOn(io.reactivex.k.a.a()).blockingSingle();
    kotlin.e.b.l.a(paramList, "Observable.fromCallable …ation()).blockingSingle()");
    return (Bundle)paramList;
  }
  
  private final Bundle c(Map<String, String> paramMap)
  {
    int i;
    if (paramMap != null) {
      i = paramMap.size();
    } else {
      i = 1;
    }
    Bundle localBundle = new Bundle(i);
    if (paramMap != null)
    {
      paramMap = paramMap.entrySet().iterator();
      while (paramMap.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)paramMap.next();
        localBundle.putString((String)localEntry.getKey(), (String)localEntry.getValue());
      }
    }
    return localBundle;
  }
  
  private final void d(Map<String, String> paramMap)
  {
    if (com.rappi.base.p.b.b.a.a().a()) {
      paramMap.putAll(a(com.rappi.base.p.b.b.a.h()));
    }
  }
  
  private final void e(Map<String, String> paramMap)
  {
    if (!paramMap.containsKey("ADDRESS_ID"))
    {
      Address localAddress = com.rappi.base.p.b.b.a.i();
      if (localAddress != null) {
        paramMap.putAll(b.a(localAddress));
      }
    }
  }
  
  public final String a()
  {
    return g.a.e();
  }
  
  public final Map<String, String> a(BasketStoreDetail paramBasketStoreDetail)
  {
    kotlin.e.b.l.b(paramBasketStoreDetail, "storeModel");
    HashMap localHashMap = new HashMap(5);
    localHashMap.put("STORE_ID", paramBasketStoreDetail.a());
    localHashMap.put("STORE_NAME", paramBasketStoreDetail.b());
    paramBasketStoreDetail = (String)localHashMap.put(com.rappi.base.b.b.a.k, String.valueOf(paramBasketStoreDetail.c()));
    return (Map)localHashMap;
  }
  
  public final Map<String, String> a(BasketProduct paramBasketProduct)
  {
    HashMap localHashMap = new HashMap();
    if (paramBasketProduct != null)
    {
      localHashMap.put("PRODUCT_ID", Product.getCorrectProductId(paramBasketProduct.b()));
      localHashMap.put("PRODUCT_NAME", paramBasketProduct.c());
      localHashMap.put("PRODUCT_DESCRIPTION", paramBasketProduct.d());
      localHashMap.put("PRODUCT_TYPE", paramBasketProduct.getSaleType());
      localHashMap.put("QUANTITY", String.valueOf(paramBasketProduct.getQuantity()));
      localHashMap.put("STORE_ID", paramBasketProduct.f());
      localHashMap.put("CURRENCY", b.a());
      paramBasketProduct = com.rappi.base.utils.n.a();
      if (paramBasketProduct != null)
      {
        localHashMap.put("DEVICE_WIDTH", String.valueOf(paramBasketProduct.b()));
        paramBasketProduct = (String)localHashMap.put("DEVICE_HEIGHT", String.valueOf(paramBasketProduct.c()));
      }
    }
    return (Map)localHashMap;
  }
  
  public final Map<String, String> a(Taggable paramTaggable, Address paramAddress)
  {
    kotlin.e.b.l.b(paramAddress, "address");
    HashMap localHashMap = new HashMap();
    if (paramTaggable != null)
    {
      localHashMap.put("TAG_ID", String.valueOf(paramTaggable.getId()));
      localHashMap.put("TAG_NAME", paramTaggable.getName());
      localHashMap.put("POSITION", String.valueOf(paramTaggable.getIndex()));
      localHashMap.putAll(b.a(paramAddress));
    }
    return (Map)localHashMap;
  }
  
  public final Map<String, String> a(Address paramAddress)
  {
    HashMap localHashMap = new HashMap();
    if (paramAddress != null)
    {
      localHashMap.put("ADDRESS_ID", String.valueOf(Integer.valueOf(paramAddress.getId())));
      localHashMap.put("ADDRESS", paramAddress.getAddress());
      localHashMap.put("ADDRESS_DESCRIPTION", paramAddress.getDescription());
      Object localObject1 = paramAddress.getTag();
      if (localObject1 == null) {
        localObject1 = "";
      }
      localHashMap.put("ADDRESS_TAG", localObject1);
      localHashMap.put("LAT", String.valueOf(Double.valueOf(paramAddress.getLatitude())));
      localHashMap.put("LNG", String.valueOf(Double.valueOf(paramAddress.getLongitude())));
      localObject1 = paramAddress.getZone();
      Object localObject2 = null;
      if (localObject1 != null) {
        localObject1 = ((Zone)localObject1).getId();
      } else {
        localObject1 = null;
      }
      localHashMap.put("MICROZONE_ID", String.valueOf(localObject1));
      localObject1 = paramAddress.getZone();
      if (localObject1 != null) {
        localObject1 = ((Zone)localObject1).getName();
      } else {
        localObject1 = null;
      }
      localHashMap.put("MICROZONE_NAME", String.valueOf(localObject1));
      localHashMap.put("COUNTRY", g.a.a((com.rappi.base.others.i)r.a));
      localObject1 = paramAddress.getCity();
      paramAddress = localObject2;
      if (localObject1 != null) {
        paramAddress = ((City)localObject1).getCity();
      }
      localHashMap.put("CITY", String.valueOf(paramAddress));
    }
    return (Map)localHashMap;
  }
  
  public final Map<String, String> a(StoreDetail paramStoreDetail)
  {
    HashMap localHashMap = new HashMap(5);
    if (paramStoreDetail != null)
    {
      localHashMap.put("STORE_ID", paramStoreDetail.getId());
      localHashMap.put("STORE_TYPE", paramStoreDetail.getStoreType());
      localHashMap.put("STORE_NAME", paramStoreDetail.getName());
      localHashMap.put("BRAND_NAME", paramStoreDetail.getBrandName());
      paramStoreDetail = (String)localHashMap.put(com.rappi.base.b.b.a.k, String.valueOf(paramStoreDetail.getDeliveryPrice()));
    }
    return (Map)localHashMap;
  }
  
  public final Map<String, String> a(z paramZ)
  {
    HashMap localHashMap = new HashMap();
    if (paramZ != null)
    {
      localHashMap.put("USER_EMAIL", paramZ.getEmail());
      localHashMap.put("USER_FULLNAME", paramZ.getFullName());
      localHashMap.put("USER_ID", paramZ.getId().toString());
      paramZ = (String)localHashMap.put("IS_PRIME", String.valueOf(com.rappi.base.rappiPrime.b.a.d()));
    }
    return (Map)localHashMap;
  }
  
  public final Map<String, String> a(String paramString1, String paramString2)
  {
    kotlin.e.b.l.b(paramString1, "key");
    kotlin.e.b.l.b(paramString2, "value");
    HashMap localHashMap = new HashMap(1);
    localHashMap.put(paramString1, paramString2);
    return (Map)localHashMap;
  }
  
  public final Map<String, String> a(List<BasketProduct> paramList)
  {
    paramList = y.fromCallable((Callable)new c.a(paramList)).subscribeOn(io.reactivex.k.a.a()).blockingSingle();
    kotlin.e.b.l.a(paramList, "Observable.fromCallable<…ation()).blockingSingle()");
    return (Map)paramList;
  }
  
  public final void a(Application paramApplication)
  {
    kotlin.e.b.l.b(paramApplication, "application");
    com.rappi.base.b.a.c.a(paramApplication);
    com.rappi.base.b.a.b.b.a(paramApplication);
    com.rappi.base.b.a.a.a(paramApplication);
    com.rappi.base.b.a.e.a.a(paramApplication);
  }
  
  public final void a(Application paramApplication, z paramZ)
  {
    kotlin.e.b.l.b(paramApplication, "application");
    kotlin.e.b.l.b(paramZ, "user");
    com.rappi.base.b.a.d localD = com.rappi.base.b.a.d.a;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(g.a.d());
    localStringBuilder.append("_");
    localStringBuilder.append(paramZ.getId());
    localD.a(paramApplication, localStringBuilder.toString());
  }
  
  public final void a(Context paramContext, String paramString)
  {
    kotlin.e.b.l.b(paramContext, "application");
    kotlin.e.b.l.b(paramString, "filter");
    try
    {
      Object localObject = paramContext.getPackageManager().getInstalledApplications(128);
      if ((localObject != null) && ((((Collection)localObject).isEmpty() ^ true)))
      {
        paramContext = new ArrayList();
        paramString = kotlin.j.n.b((CharSequence)paramString, new String[] { "," }, false, 0, 6, null);
        localObject = ((Iterable)localObject).iterator();
        while (((Iterator)localObject).hasNext())
        {
          ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject).next();
          if ((localApplicationInfo.packageName != null) && (paramString.contains(localApplicationInfo.packageName))) {
            paramContext.add(localApplicationInfo.packageName);
          }
        }
        if ((((Collection)paramContext).isEmpty() ^ true))
        {
          paramString = b;
          localObject = new HashMap();
          ((HashMap)localObject).put("apps", kotlin.a.l.a((Iterable)paramContext, null, null, null, 0, null, null, 63, null));
          paramString.a("installed_apps", (Map)localObject);
        }
      }
      paramContext = o.a;
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      paramContext = o.a;
    }
  }
  
  public final void a(com.rappi.base.b.b.b paramB)
  {
    kotlin.e.b.l.b(paramB, "appsFlyerEvent");
    com.rappi.base.b.a.b.b.a(paramB);
  }
  
  public final void a(com.rappi.base.b.b.c paramC)
  {
    kotlin.e.b.l.b(paramC, "facebookEvent");
    com.rappi.base.b.a.c.a(paramC);
  }
  
  public final void a(StoreTypeModelV2 paramStoreTypeModelV2, BasketProduct paramBasketProduct, List<Topping> paramList, String paramString, b paramB)
  {
    kotlin.e.b.l.b(paramStoreTypeModelV2, "source");
    kotlin.e.b.l.b(paramBasketProduct, "product");
    kotlin.e.b.l.b(paramString, "comment");
    kotlin.e.b.l.b(paramB, "store");
    BasketProduct localBasketProduct = BasketProduct.a(paramBasketProduct, null, new ProductSell(b.a(paramStoreTypeModelV2.getStoreType(), paramBasketProduct.getQuantity()), 0.0D, 0.0D, 0.0D, 14, null), null, null, null, null, 61, null);
    Map localMap = b.a(localBasketProduct);
    String str = paramStoreTypeModelV2.getStoreType();
    if (str == null) {
      throw new kotlin.l("null cannot be cast to non-null type java.lang.String");
    }
    str = str.toUpperCase();
    kotlin.e.b.l.a(str, "(this as java.lang.String).toUpperCase()");
    localMap.put("SOURCE", str);
    localMap.put("PRODUCT_COMMENT", paramString);
    localMap.put("IS_PRIME", String.valueOf(com.rappi.base.rappiPrime.b.a.d()));
    localMap.put("STORE_ID", paramBasketProduct.f());
    localMap.put("PRODUCT_ID", paramBasketProduct.b());
    paramStoreTypeModelV2 = paramStoreTypeModelV2.getStoreType();
    if (paramStoreTypeModelV2 == null) {
      throw new kotlin.l("null cannot be cast to non-null type java.lang.String");
    }
    paramStoreTypeModelV2 = paramStoreTypeModelV2.toUpperCase();
    kotlin.e.b.l.a(paramStoreTypeModelV2, "(this as java.lang.String).toUpperCase()");
    localMap.put("STORE_TYPE", paramStoreTypeModelV2);
    localMap.putAll(b.a(paramBasketProduct.e()));
    localMap.putAll(b.a(com.rappi.base.p.b.b.a.h()));
    localMap.putAll(b.b(paramList));
    localMap.putAll((Map)d.a(paramB));
    b.a(new com.rappi.base.b.b.b("ADD_TO_CART", localMap));
    b.b("ADD_TO_CART", localMap, localBasketProduct.a());
    paramStoreTypeModelV2 = new com.rappi.base.b.b.c("fb_mobile_add_to_cart", paramBasketProduct.getPrice(), b.b(paramBasketProduct));
    int i = 0;
    int j = localBasketProduct.getQuantity();
    while (i < j)
    {
      com.rappi.base.b.a.c.a(paramStoreTypeModelV2);
      i += 1;
    }
  }
  
  public final void a(z paramZ, Application paramApplication)
  {
    kotlin.e.b.l.b(paramZ, "user");
    kotlin.e.b.l.b(paramApplication, "application");
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append(g.a.d());
    ((StringBuilder)localObject).append("_");
    ((StringBuilder)localObject).append(paramZ.getId());
    localObject = ((StringBuilder)localObject).toString();
    com.rappi.base.b.a.c.a((String)localObject);
    com.rappi.base.b.a.a.a((String)localObject);
    com.rappi.base.b.a.b.b.b((String)localObject);
    com.rappi.base.b.a.e.a.a((String)localObject);
    NewRelic.setUserId((String)localObject);
    if (Sift.get() != null)
    {
      localObject = Sift.get();
      if (localObject == null) {
        kotlin.e.b.l.a();
      }
      kotlin.e.b.l.a(localObject, "Sift.get()!!");
      ((Sift)localObject).setUserId(paramZ.getId().toString());
    }
    b.a(paramApplication, paramZ);
  }
  
  public final void a(String paramString)
  {
    kotlin.e.b.l.b(paramString, "event");
    a(paramString, (Map)new HashMap());
  }
  
  public final void a(String paramString1, BasketProduct paramBasketProduct, List<Topping> paramList, String paramString2, b paramB, PlaceSorted paramPlaceSorted)
  {
    kotlin.e.b.l.b(paramString1, "source");
    kotlin.e.b.l.b(paramBasketProduct, "product");
    kotlin.e.b.l.b(paramString2, "comment");
    kotlin.e.b.l.b(paramB, "store");
    BasketProduct localBasketProduct = BasketProduct.a(paramBasketProduct, null, new ProductSell(b.a(paramString1, paramBasketProduct.getQuantity()), 0.0D, 0.0D, 0.0D, 14, null), null, null, null, null, 61, null);
    Map localMap = b.a(localBasketProduct);
    localMap.put("SOURCE", paramString1);
    localMap.put("PRODUCT_COMMENT", paramString2);
    localMap.put("IS_PRIME", String.valueOf(com.rappi.base.rappiPrime.b.a.d()));
    localMap.put("STORE_ID", paramBasketProduct.f());
    localMap.put("STORE_NAME", paramBasketProduct.e().b());
    localMap.putAll(b.a(com.rappi.base.p.b.b.a.h()));
    localMap.putAll(b.b(paramList));
    localMap.putAll((Map)d.a(paramB));
    if ((kotlin.e.b.l.a(paramString1, "WHIM")) || (kotlin.j.n.a(paramB.b(), "WHIM", true)))
    {
      if (paramPlaceSorted != null)
      {
        paramString1 = paramPlaceSorted.getSecondaryText();
        if (paramString1 == null) {
          paramString1 = "";
        }
        localMap.put("WHIM_ADDRESS", paramString1);
        paramString1 = paramPlaceSorted.getPrimaryText();
        if (paramString1 == null) {
          paramString1 = "";
        }
        paramString1 = (String)localMap.put("WHIM_ADDRESS_DESCRIPTION", paramString1);
      }
      localMap.put("WHIM_DESCRIPTION", paramBasketProduct.c());
    }
    b.a(new com.rappi.base.b.b.b("ADD_TO_CART", localMap));
    b.b("ADD_TO_CART", localMap, localBasketProduct.a());
    paramString1 = new com.rappi.base.b.b.c("fb_mobile_add_to_cart", paramBasketProduct.getPrice(), b.b(paramBasketProduct));
    int i = 0;
    int j = localBasketProduct.getQuantity();
    while (i < j)
    {
      com.rappi.base.b.a.c.a(paramString1);
      i += 1;
    }
  }
  
  public final void a(String paramString1, Address paramAddress, String paramString2)
  {
    kotlin.e.b.l.b(paramString1, "source");
    kotlin.e.b.l.b(paramAddress, "address");
    kotlin.e.b.l.b(paramString2, "storeType");
    Map localMap = (Map)new LinkedHashMap();
    localMap.put("SOURCE", paramString1);
    localMap.put("STORE_TYPE", paramString2);
    localMap.put("IS_PRIME", String.valueOf(com.rappi.base.rappiPrime.b.a.d()));
    localMap.putAll(b.a(paramAddress));
    localMap.putAll(b.a(com.rappi.base.p.b.b.a.h()));
    b.a("SELECT_LOCAL_SEARCH", localMap);
  }
  
  public final void a(String paramString1, Address paramAddress, String paramString2, boolean paramBoolean)
  {
    kotlin.e.b.l.b(paramString1, "storeType");
    kotlin.e.b.l.b(paramAddress, "address");
    paramString1 = ab.b(new kotlin.i[] { new kotlin.i("STORE_TYPE", paramString1) });
    paramString1.put("IS_PRIME", String.valueOf(com.rappi.base.rappiPrime.b.a.d()));
    paramString1.put("PRODUCTS_IN_CART", String.valueOf(paramBoolean));
    if (paramString2 != null) {
      paramString2 = (String)paramString1.put("SOURCE", paramString2);
    }
    paramString1.putAll(b.a(com.rappi.base.p.b.b.a.h()));
    paramString1.putAll(b.a(paramAddress));
    b.a("VIEW_STORE_TYPE", paramString1);
  }
  
  public final void a(String paramString1, String paramString2, Address paramAddress)
  {
    kotlin.e.b.l.b(paramString1, "storeType");
    kotlin.e.b.l.b(paramString2, "place");
    kotlin.e.b.l.b(paramAddress, "address");
    paramString1 = (Map)b(paramString1, paramString2, paramAddress);
    paramString1.put("NEW_HOME", String.valueOf(com.rappi.base.others.a.m()));
    b.a("SELECT_STORE_TYPE", paramString1);
    b.a(new com.rappi.base.b.b.b("SELECT_STORE_TYPE", paramString1));
  }
  
  public final void a(String paramString, Map<String, String> paramMap)
  {
    kotlin.e.b.l.b(paramString, "event");
    kotlin.e.b.l.b(paramMap, "params");
    a(paramString, paramMap, 0.0D, false);
  }
  
  public final void a(String paramString, Map<String, String> paramMap, double paramDouble)
  {
    kotlin.e.b.l.b(paramString, "event");
    kotlin.e.b.l.b(paramMap, "params");
    a(paramString, paramMap, paramDouble, true);
  }
  
  public final void a(List<BasketProduct> paramList, b paramB, Address paramAddress, LastScheduledTime paramLastScheduledTime, String paramString, PlaceSorted paramPlaceSorted)
  {
    kotlin.e.b.l.b(paramB, "store");
    kotlin.e.b.l.b(paramAddress, "address");
    if ((paramList != null) && (!paramList.isEmpty()))
    {
      Object localObject = (Iterable)paramList;
      Iterator localIterator = ((Iterable)localObject).iterator();
      double d2 = 0.0D;
      BasketProduct localBasketProduct;
      for (double d1 = 0.0D; localIterator.hasNext(); d1 += localBasketProduct.getQuantity() * localBasketProduct.getPrice()) {
        localBasketProduct = (BasketProduct)localIterator.next();
      }
      localIterator = ((Iterable)localObject).iterator();
      while (localIterator.hasNext()) {
        d2 += ((BasketProduct)localIterator.next()).e().c();
      }
      int i = 0;
      localObject = ((Iterable)localObject).iterator();
      while (((Iterator)localObject).hasNext()) {
        i += ((BasketProduct)((Iterator)localObject).next()).getQuantity();
      }
      localObject = b.a(paramList);
      ((Map)localObject).putAll((Map)d.a(paramB));
      paramB = com.rappi.base.b.b.a.k;
      kotlin.e.b.l.a(paramB, "SHIPPING_COST");
      ((Map)localObject).put(paramB, String.valueOf(d2));
      ((Map)localObject).put("TOTAL_PRICE", String.valueOf(d1));
      ((Map)localObject).put("QUANTITY", String.valueOf(i));
      ((Map)localObject).put("IS_PRIME", String.valueOf(com.rappi.base.rappiPrime.b.a.d()));
      if (paramString != null)
      {
        ((Map)localObject).put("SOURCE", paramString);
        if ((kotlin.e.b.l.a(paramString, "WHIM")) && (paramPlaceSorted != null))
        {
          paramB = paramPlaceSorted.getSecondaryText();
          if (paramB == null) {
            paramB = "";
          }
          ((Map)localObject).put("WHIM_ADDRESS", paramB);
          paramB = paramPlaceSorted.getPrimaryText();
          if (paramB == null) {
            paramB = "";
          }
          ((Map)localObject).put("WHIM_ADDRESS_DESCRIPTION", paramB);
        }
      }
      ((Map)localObject).putAll(a(paramAddress));
      paramAddress = null;
      if (paramLastScheduledTime != null) {
        paramB = paramLastScheduledTime.getOpenDate();
      } else {
        paramB = null;
      }
      if (paramLastScheduledTime != null) {
        paramAddress = paramLastScheduledTime.getCloseDate();
      }
      com.rappi.base.m.i.a(k.a(paramB, paramAddress), (m)new c.e((Map)localObject));
      ((Map)localObject).putAll(a(com.rappi.base.p.b.b.a.h()));
      b.b("PROCEED_TO_CHECKOUT", (Map)localObject, d1);
      b.a(new com.rappi.base.b.b.b("PROCEED_TO_CHECKOUT", (Map)localObject));
      com.rappi.base.b.a.c.a(new com.rappi.base.b.b.c("fb_mobile_initiated_checkout", d1 + com.rappi.base.p.b.b.a.C().a(), b.c(paramList)));
    }
  }
  
  public final void a(Map<String, String> paramMap)
  {
    kotlin.e.b.l.b(paramMap, "errorMap");
    b.d(paramMap);
    b.e(paramMap);
    com.rappi.base.b.a.a.a(com.rappi.base.b.b.a.m, b.b(paramMap));
    try
    {
      com.crashlytics.android.a.a(6, com.rappi.base.b.b.a.m, ab.b(paramMap).toString());
      paramMap = o.a;
      return;
    }
    catch (Exception paramMap)
    {
      paramMap.printStackTrace();
      paramMap = o.a;
    }
  }
  
  public final void a(boolean paramBoolean)
  {
    b.a("ALLOW_LOCATION", b.a("LOCATION_ALLOWED", String.valueOf(paramBoolean)));
  }
  
  public final Gson b()
  {
    kotlin.d localD = c;
    h localH = a[0];
    return (Gson)localD.a();
  }
  
  public final Map<String, String> b(List<Topping> paramList)
  {
    paramList = y.fromCallable((Callable)new c.c(paramList)).subscribeOn(io.reactivex.k.a.a()).blockingSingle();
    kotlin.e.b.l.a(paramList, "Observable.fromCallable<…ation()).blockingSingle()");
    return (Map)paramList;
  }
  
  public final void b(Application paramApplication)
  {
    kotlin.e.b.l.b(paramApplication, "application");
    z localZ = com.rappi.base.p.b.b.a.h();
    if (localZ != null) {
      b.a(localZ, paramApplication);
    }
  }
  
  public final void b(String paramString)
  {
    kotlin.e.b.l.b(paramString, "storeType");
    Map localMap = (Map)new LinkedHashMap();
    localMap.put("STORE_TYPE", paramString);
    a("VIEW_MINIMUM_BANNER", localMap);
  }
  
  public final void b(String paramString, Map<String, String> paramMap, double paramDouble)
  {
    kotlin.e.b.l.b(paramString, "event");
    kotlin.e.b.l.b(paramMap, "params");
    a(paramString, paramMap, paramDouble, false);
  }
  
  public final void c()
  {
    b.a("ALLOW_LOCATION");
  }
  
  public final void c(String paramString)
  {
    kotlin.e.b.l.b(paramString, "source");
    b.a("VIEW_HOME", b.a("SOURCE", paramString));
  }
  
  public final void d()
  {
    c localC = b;
    String str = com.rappi.base.b.b.a.j;
    kotlin.e.b.l.a(str, "VIEW_BLOCK_ORDERS");
    localC.a(str);
  }
  
  public final void d(String paramString)
  {
    kotlin.e.b.l.b(paramString, "source");
    Map localMap = (Map)new LinkedHashMap();
    localMap.put("SOURCE", paramString);
    b.a("PREVIOUS_ORDER", localMap);
  }
}
