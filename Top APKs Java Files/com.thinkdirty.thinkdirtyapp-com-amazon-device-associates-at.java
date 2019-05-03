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

final class at
  implements aw
{
  private static final String a = "at";
  private bb b;
  private final Set<PurchaseExperience> c = new HashSet();
  private boolean d = false;
  private boolean e = false;
  
  at()
  {
    this.c.add(PurchaseExperience.DIRECT_WITH_DETAIL);
    this.c.add(PurchaseExperience.DIRECT_WITH_PREVIEW);
  }
  
  private void a(RequestId paramRequestId, String paramString, JSONObject paramJSONObject)
    throws JSONException
  {
    Intent localIntent = new Intent(paramString);
    Context localContext = this.b.b();
    Bundle localBundle = new Bundle();
    paramJSONObject.put("sdkVersion", this.b.a());
    paramJSONObject.put("packageName", localContext.getPackageName());
    paramJSONObject.put("requestId", paramRequestId.toString());
    localBundle.putString("message", paramJSONObject.toString());
    paramRequestId = a;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("action: ");
    localStringBuilder.append(paramString);
    localStringBuilder.append(", message: ");
    localStringBuilder.append(paramJSONObject.toString(4));
    aa.b(paramRequestId, localStringBuilder.toString());
    localIntent.addFlags(268435456);
    localIntent.putExtras(localBundle);
    localContext.startService(localIntent);
  }
  
  private boolean a()
  {
    Object localObject = this.b.b().getPackageManager();
    boolean bool2 = false;
    localObject = ((PackageManager)localObject).getInstalledPackages(0).iterator();
    do
    {
      bool1 = bool2;
      if (!((Iterator)localObject).hasNext()) {
        break;
      }
    } while (!((PackageInfo)((Iterator)localObject).next()).packageName.equals("com.amazon.sdktestclient"));
    boolean bool1 = true;
    return bool1;
  }
  
  public void a(RequestId paramRequestId, PurchaseRequest paramPurchaseRequest, bb paramBb)
  {
    aa.b(a, "sendPurchaseRequest");
    this.b = paramBb;
    if ((a()) && ((paramPurchaseRequest.getPurchaseExperience() == null) || (paramPurchaseRequest.getPurchaseExperience() == PurchaseExperience.IN_APP))) {}
    try
    {
      paramBb = new JSONObject();
      paramBb.put("productType", "PHYSICAL");
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("[\"");
      localStringBuilder.append(paramPurchaseRequest.getProductId());
      localStringBuilder.append("\"]");
      paramBb.put("productIds", new JSONArray(localStringBuilder.toString()));
      paramBb.put("receiveReceipt", paramPurchaseRequest.getReceiveReceipt());
      a(paramRequestId, "com.amazon.device.iap.physical.physical_purchase", paramBb);
      return;
    }
    catch (JSONException paramRequestId)
    {
      for (;;) {}
    }
    aa.a(a, "Error in sendPurchaseRequest.");
    return;
    paramRequestId = new PurchaseResponse(paramRequestId, PurchaseResponse.Status.NOT_SUPPORTED);
    paramBb.a(bb.a.e, paramRequestId);
  }
  
  public void a(RequestId paramRequestId, ReceiptsRequest paramReceiptsRequest, bb paramBb)
  {
    aa.b(a, "sendGetReceiptsRequest");
    this.b = paramBb;
    if (!a())
    {
      paramRequestId = new ReceiptsResponse(paramRequestId, ReceiptsResponse.Status.NOT_SUPPORTED);
      paramBb.a(bb.a.b, paramRequestId);
      return;
    }
    try
    {
      paramBb = new JSONObject();
      paramBb.put("offset", paramReceiptsRequest.getOffset());
      a(paramRequestId, "com.amazon.device.iap.physical.physical_get_receipts", paramBb);
      return;
    }
    catch (JSONException paramRequestId)
    {
      for (;;) {}
    }
    aa.a(a, "Error in sendGetReceiptsRequest.");
  }
  
  public void a(RequestId paramRequestId, SearchByIdRequest paramSearchByIdRequest, bb paramBb)
  {
    aa.b(a, "sendSearchByIdRequest");
    this.b = paramBb;
    if (!a())
    {
      paramRequestId = new SearchByIdResponse(paramRequestId, SearchByIdResponse.Status.NOT_SUPPORTED);
      paramBb.a(bb.a.d, paramRequestId);
      return;
    }
    try
    {
      paramBb = new JSONObject();
      paramBb.put("productType", "PHYSICAL");
      paramBb.put("productIds", new JSONArray(paramSearchByIdRequest.getProductIds()));
      a(paramRequestId, "com.amazon.device.iap.physical.physical_searchById", paramBb);
      return;
    }
    catch (JSONException paramRequestId)
    {
      for (;;) {}
    }
    aa.a(a, "Error in sendSearchByIdRequest.");
  }
  
  public void a(RequestId paramRequestId, SearchRequest paramSearchRequest, bb paramBb)
  {
    aa.b(a, "sendSearchRequest");
    this.b = paramBb;
    if (!a())
    {
      paramRequestId = new SearchResponse(paramRequestId, SearchResponse.Status.NOT_SUPPORTED);
      paramBb.a(bb.a.c, paramRequestId);
      return;
    }
    try
    {
      paramBb = new JSONObject();
      paramBb.put("category", paramSearchRequest.getCategory().toString());
      paramBb.put("searchTerm", paramSearchRequest.getSearchTerm());
      Map localMap = paramSearchRequest.getFilters();
      JSONObject localJSONObject = new JSONObject();
      Iterator localIterator = localMap.keySet().iterator();
      while (localIterator.hasNext())
      {
        FilterType localFilterType = (FilterType)localIterator.next();
        localJSONObject.put(localFilterType.toString(), localMap.get(localFilterType));
      }
      paramBb.put("filters", localJSONObject);
      paramBb.put("sortType", paramSearchRequest.getSortType().toString());
      paramBb.put("page", paramSearchRequest.getPage());
      a(paramRequestId, "com.amazon.device.iap.physical.physical_search", paramBb);
      return;
    }
    catch (JSONException paramRequestId)
    {
      for (;;) {}
    }
    aa.a(a, "Error in sendSearchRequest.");
  }
  
  public void a(RequestId paramRequestId, bb paramBb)
  {
    aa.b(a, "sendGetServiceStatusRequest");
    this.b = paramBb;
    if (!a())
    {
      paramRequestId = new ServiceStatusResponse(paramRequestId, null, this.c, this.d, this.e);
      paramBb.a(bb.a.a, paramRequestId);
      return;
    }
    try
    {
      a(paramRequestId, "com.amazon.device.iap.physical.get_userData", new JSONObject());
      return;
    }
    catch (JSONException paramRequestId)
    {
      for (;;) {}
    }
    aa.a(a, "Error in sendGetServiceStatusRequest.");
  }
  
  public void b(RequestId paramRequestId, bb paramBb)
  {
    aa.b(a, "sendPurchaseResponseRequest");
    this.b = paramBb;
    try
    {
      a(paramRequestId, "com.amazon.device.iap.physical.physical_get_purchaseResult", new JSONObject());
      return;
    }
    catch (JSONException paramRequestId)
    {
      for (;;) {}
    }
    aa.a(a, "Error in sendPurchaseResponseRequest.");
  }
  
  public void c(RequestId paramRequestId, bb paramBb)
  {
    aa.b(a, "sendReceiptReceivedRequest");
    this.b = paramBb;
    try
    {
      a(paramRequestId, "com.amazon.device.iap.physical.physical_notify_receiptReceived", new JSONObject());
      return;
    }
    catch (JSONException paramRequestId)
    {
      for (;;) {}
    }
    aa.a(a, "Error in sendReceiptReceivedRequest.");
  }
}
