package com.amazon.device.associates;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

final class ap
  implements ar
{
  private static final String a = ap.class.getSimpleName();
  private ay b;
  private final Set c = new HashSet();
  private boolean d = false;
  private boolean e = false;
  
  ap()
  {
    this.c.add(PurchaseExperience.DIRECT_WITH_DETAIL);
    this.c.add(PurchaseExperience.DIRECT_WITH_PREVIEW);
  }
  
  private void a(RequestId paramRequestId, String paramString, JSONObject paramJSONObject)
  {
    Intent localIntent = new Intent(paramString);
    Context localContext = this.b.b();
    Bundle localBundle = new Bundle();
    paramJSONObject.put("sdkVersion", this.b.a());
    paramJSONObject.put("packageName", localContext.getPackageName());
    paramJSONObject.put("requestId", paramRequestId.toString());
    localBundle.putString("message", paramJSONObject.toString());
    x.b(a, "action: " + paramString + ", message: " + paramJSONObject.toString(4));
    localIntent.addFlags(268435456);
    localIntent.putExtras(localBundle);
    localContext.startService(localIntent);
  }
  
  private boolean a()
  {
    Iterator localIterator = this.b.b().getPackageManager().getInstalledPackages(0).iterator();
    while (localIterator.hasNext()) {
      if (((PackageInfo)localIterator.next()).packageName.equals("com.amazon.sdktestclient")) {
        return true;
      }
    }
    return false;
  }
  
  public void a(RequestId paramRequestId, PurchaseRequest paramPurchaseRequest, ay paramAy)
  {
    x.b(a, "sendPurchaseRequest");
    this.b = paramAy;
    if ((!a()) || ((paramPurchaseRequest.getPurchaseExperience() != null) && (paramPurchaseRequest.getPurchaseExperience() != PurchaseExperience.IN_APP)))
    {
      paramRequestId = new PurchaseResponse(paramRequestId, PurchaseResponse.Status.NOT_SUPPORTED);
      paramAy.a(ay.a.e, paramRequestId);
      return;
    }
    try
    {
      paramAy = new JSONObject();
      paramAy.put("productType", "PHYSICAL");
      paramAy.put("productIds", new JSONArray("[\"" + paramPurchaseRequest.getProductId() + "\"]"));
      paramAy.put("receiveReceipt", paramPurchaseRequest.getReceiveReceipt());
      a(paramRequestId, "com.amazon.device.iap.physical.physical_purchase", paramAy);
      return;
    }
    catch (JSONException paramRequestId)
    {
      x.a(a, "Error in sendPurchaseRequest.");
    }
  }
  
  public void a(RequestId paramRequestId, ReceiptsRequest paramReceiptsRequest, ay paramAy)
  {
    x.b(a, "sendGetReceiptsRequest");
    this.b = paramAy;
    if (!a())
    {
      paramRequestId = new ReceiptsResponse(paramRequestId, ReceiptsResponse.Status.NOT_SUPPORTED);
      paramAy.a(ay.a.b, paramRequestId);
      return;
    }
    try
    {
      paramAy = new JSONObject();
      paramAy.put("offset", paramReceiptsRequest.getOffset());
      a(paramRequestId, "com.amazon.device.iap.physical.physical_get_receipts", paramAy);
      return;
    }
    catch (JSONException paramRequestId)
    {
      x.a(a, "Error in sendGetReceiptsRequest.");
    }
  }
  
  public void a(RequestId paramRequestId, SearchByIdRequest paramSearchByIdRequest, ay paramAy)
  {
    x.b(a, "sendSearchByIdRequest");
    this.b = paramAy;
    if (!a())
    {
      paramRequestId = new SearchByIdResponse(paramRequestId, SearchByIdResponse.Status.NOT_SUPPORTED);
      paramAy.a(ay.a.d, paramRequestId);
      return;
    }
    try
    {
      paramAy = new JSONObject();
      paramAy.put("productType", "PHYSICAL");
      paramAy.put("productIds", new JSONArray(paramSearchByIdRequest.getProductIds()));
      a(paramRequestId, "com.amazon.device.iap.physical.physical_searchById", paramAy);
      return;
    }
    catch (JSONException paramRequestId)
    {
      x.a(a, "Error in sendSearchByIdRequest.");
    }
  }
  
  public void a(RequestId paramRequestId, SearchRequest paramSearchRequest, ay paramAy)
  {
    x.b(a, "sendSearchRequest");
    this.b = paramAy;
    if (!a())
    {
      paramRequestId = new SearchResponse(paramRequestId, SearchResponse.Status.NOT_SUPPORTED);
      paramAy.a(ay.a.c, paramRequestId);
      return;
    }
    try
    {
      paramAy = new JSONObject();
      paramAy.put("category", paramSearchRequest.getCategory().toString());
      paramAy.put("searchTerm", paramSearchRequest.getSearchTerm());
      Map localMap = paramSearchRequest.getFilters();
      JSONObject localJSONObject = new JSONObject();
      Iterator localIterator = localMap.keySet().iterator();
      while (localIterator.hasNext())
      {
        FilterType localFilterType = (FilterType)localIterator.next();
        localJSONObject.put(localFilterType.toString(), localMap.get(localFilterType));
      }
      paramAy.put("filters", localJSONObject);
    }
    catch (JSONException paramRequestId)
    {
      x.a(a, "Error in sendSearchRequest.");
      return;
    }
    paramAy.put("sortType", paramSearchRequest.getSortType().toString());
    paramAy.put("page", paramSearchRequest.getPage());
    a(paramRequestId, "com.amazon.device.iap.physical.physical_search", paramAy);
  }
  
  public void a(RequestId paramRequestId, ay paramAy)
  {
    x.b(a, "sendGetServiceStatusRequest");
    this.b = paramAy;
    if (!a())
    {
      paramRequestId = new ServiceStatusResponse(paramRequestId, null, this.c, this.d, this.e);
      paramAy.a(ay.a.a, paramRequestId);
      return;
    }
    try
    {
      a(paramRequestId, "com.amazon.device.iap.physical.get_userData", new JSONObject());
      return;
    }
    catch (JSONException paramRequestId)
    {
      x.a(a, "Error in sendGetServiceStatusRequest.");
    }
  }
  
  public void b(RequestId paramRequestId, ay paramAy)
  {
    x.b(a, "sendPurchaseResponseRequest");
    this.b = paramAy;
    try
    {
      a(paramRequestId, "com.amazon.device.iap.physical.physical_get_purchaseResult", new JSONObject());
      return;
    }
    catch (JSONException paramRequestId)
    {
      x.a(a, "Error in sendPurchaseResponseRequest.");
    }
  }
  
  public void c(RequestId paramRequestId, ay paramAy)
  {
    x.b(a, "sendReceiptReceivedRequest");
    this.b = paramAy;
    try
    {
      a(paramRequestId, "com.amazon.device.iap.physical.physical_notify_receiptReceived", new JSONObject());
      return;
    }
    catch (JSONException paramRequestId)
    {
      x.a(a, "Error in sendReceiptReceivedRequest.");
    }
  }
}
