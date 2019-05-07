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
  private static final String a = at.class.getSimpleName();
  private bd b;
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
    aa.b(a, "action: " + paramString + ", message: " + paramJSONObject.toString(4));
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
  
  public void a(RequestId paramRequestId, PurchaseRequest paramPurchaseRequest, bd paramBd)
  {
    aa.b(a, "sendPurchaseRequest");
    this.b = paramBd;
    if ((!a()) || ((paramPurchaseRequest.getPurchaseExperience() != null) && (paramPurchaseRequest.getPurchaseExperience() != PurchaseExperience.IN_APP)))
    {
      paramRequestId = new PurchaseResponse(paramRequestId, PurchaseResponse.Status.NOT_SUPPORTED);
      paramBd.a(bd.a.e, paramRequestId);
      return;
    }
    try
    {
      paramBd = new JSONObject();
      paramBd.put("productType", "PHYSICAL");
      paramBd.put("productIds", new JSONArray("[\"" + paramPurchaseRequest.getProductId() + "\"]"));
      paramBd.put("receiveReceipt", paramPurchaseRequest.getReceiveReceipt());
      a(paramRequestId, "com.amazon.device.iap.physical.physical_purchase", paramBd);
      return;
    }
    catch (JSONException paramRequestId)
    {
      aa.a(a, "Error in sendPurchaseRequest.");
    }
  }
  
  public void a(RequestId paramRequestId, ReceiptsRequest paramReceiptsRequest, bd paramBd)
  {
    aa.b(a, "sendGetReceiptsRequest");
    this.b = paramBd;
    if (!a())
    {
      paramRequestId = new ReceiptsResponse(paramRequestId, ReceiptsResponse.Status.NOT_SUPPORTED);
      paramBd.a(bd.a.b, paramRequestId);
      return;
    }
    try
    {
      paramBd = new JSONObject();
      paramBd.put("offset", paramReceiptsRequest.getOffset());
      a(paramRequestId, "com.amazon.device.iap.physical.physical_get_receipts", paramBd);
      return;
    }
    catch (JSONException paramRequestId)
    {
      aa.a(a, "Error in sendGetReceiptsRequest.");
    }
  }
  
  public void a(RequestId paramRequestId, SearchByIdRequest paramSearchByIdRequest, bd paramBd)
  {
    aa.b(a, "sendSearchByIdRequest");
    this.b = paramBd;
    if (!a())
    {
      paramRequestId = new SearchByIdResponse(paramRequestId, SearchByIdResponse.Status.NOT_SUPPORTED);
      paramBd.a(bd.a.d, paramRequestId);
      return;
    }
    try
    {
      paramBd = new JSONObject();
      paramBd.put("productType", "PHYSICAL");
      paramBd.put("productIds", new JSONArray(paramSearchByIdRequest.getProductIds()));
      a(paramRequestId, "com.amazon.device.iap.physical.physical_searchById", paramBd);
      return;
    }
    catch (JSONException paramRequestId)
    {
      aa.a(a, "Error in sendSearchByIdRequest.");
    }
  }
  
  public void a(RequestId paramRequestId, SearchRequest paramSearchRequest, bd paramBd)
  {
    aa.b(a, "sendSearchRequest");
    this.b = paramBd;
    if (!a())
    {
      paramRequestId = new SearchResponse(paramRequestId, SearchResponse.Status.NOT_SUPPORTED);
      paramBd.a(bd.a.c, paramRequestId);
      return;
    }
    try
    {
      paramBd = new JSONObject();
      paramBd.put("category", paramSearchRequest.getCategory().toString());
      paramBd.put("searchTerm", paramSearchRequest.getSearchTerm());
      Map localMap = paramSearchRequest.getFilters();
      JSONObject localJSONObject = new JSONObject();
      Iterator localIterator = localMap.keySet().iterator();
      while (localIterator.hasNext())
      {
        FilterType localFilterType = (FilterType)localIterator.next();
        localJSONObject.put(localFilterType.toString(), localMap.get(localFilterType));
      }
      paramBd.put("filters", localJSONObject);
    }
    catch (JSONException paramRequestId)
    {
      aa.a(a, "Error in sendSearchRequest.");
      return;
    }
    paramBd.put("sortType", paramSearchRequest.getSortType().toString());
    paramBd.put("page", paramSearchRequest.getPage());
    a(paramRequestId, "com.amazon.device.iap.physical.physical_search", paramBd);
  }
  
  public void a(RequestId paramRequestId, bd paramBd)
  {
    aa.b(a, "sendGetServiceStatusRequest");
    this.b = paramBd;
    if (!a())
    {
      paramRequestId = new ServiceStatusResponse(paramRequestId, null, this.c, this.d, this.e);
      paramBd.a(bd.a.a, paramRequestId);
      return;
    }
    try
    {
      a(paramRequestId, "com.amazon.device.iap.physical.get_userData", new JSONObject());
      return;
    }
    catch (JSONException paramRequestId)
    {
      aa.a(a, "Error in sendGetServiceStatusRequest.");
    }
  }
  
  public void b(RequestId paramRequestId, bd paramBd)
  {
    aa.b(a, "sendPurchaseResponseRequest");
    this.b = paramBd;
    try
    {
      a(paramRequestId, "com.amazon.device.iap.physical.physical_get_purchaseResult", new JSONObject());
      return;
    }
    catch (JSONException paramRequestId)
    {
      aa.a(a, "Error in sendPurchaseResponseRequest.");
    }
  }
  
  public void c(RequestId paramRequestId, bd paramBd)
  {
    aa.b(a, "sendReceiptReceivedRequest");
    this.b = paramBd;
    try
    {
      a(paramRequestId, "com.amazon.device.iap.physical.physical_notify_receiptReceived", new JSONObject());
      return;
    }
    catch (JSONException paramRequestId)
    {
      aa.a(a, "Error in sendReceiptReceivedRequest.");
    }
  }
}
