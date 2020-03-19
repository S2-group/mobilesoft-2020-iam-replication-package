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
  implements cf
{
  private static final String a = ap.class.getSimpleName();
  private ay b;
  private final Set<PurchaseExperience> c = new HashSet();
  private boolean d = false;
  private boolean e = false;
  
  ap()
  {
    this.c.add(PurchaseExperience.b);
    this.c.add(PurchaseExperience.c);
  }
  
  private void a(RequestId paramRequestId, String paramString, JSONObject paramJSONObject)
  {
    Intent localIntent = new Intent(paramString);
    Context localContext = this.b.d();
    Bundle localBundle = new Bundle();
    paramJSONObject.put("sdkVersion", this.b.b());
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
    Iterator localIterator = this.b.d().getPackageManager().getInstalledPackages(0).iterator();
    while (localIterator.hasNext()) {
      if (((PackageInfo)localIterator.next()).packageName.equals("com.amazon.sdktestclient")) {
        return true;
      }
    }
    return false;
  }
  
  public final void a(RequestId paramRequestId, ay paramAy)
  {
    x.b(a, "sendGetServiceStatusRequest");
    this.b = paramAy;
    if (!a())
    {
      paramRequestId = new by(paramRequestId, null, this.c, this.d, this.e);
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
  
  public final void a(RequestId paramRequestId, bm paramBm, ay paramAy)
  {
    x.b(a, "sendPurchaseRequest");
    this.b = paramAy;
    if ((!a()) || ((paramBm.d() != null) && (paramBm.d() != PurchaseExperience.a)))
    {
      paramRequestId = new PurchaseResponse(paramRequestId, PurchaseResponse.Status.e);
      paramAy.a(ay.a.e, paramRequestId);
      return;
    }
    try
    {
      paramAy = new JSONObject();
      paramAy.put("productType", "PHYSICAL");
      paramAy.put("productIds", new JSONArray("[\"" + paramBm.a() + "\"]"));
      paramAy.put("receiveReceipt", paramBm.b());
      a(paramRequestId, "com.amazon.device.iap.physical.physical_purchase", paramAy);
      return;
    }
    catch (JSONException paramRequestId)
    {
      x.a(a, "Error in sendPurchaseRequest.");
    }
  }
  
  public final void a(RequestId paramRequestId, br paramBr, ay paramAy)
  {
    x.b(a, "sendGetReceiptsRequest");
    this.b = paramAy;
    if (!a())
    {
      paramRequestId = new ReceiptsResponse(paramRequestId, ReceiptsResponse.Status.d);
      paramAy.a(ay.a.b, paramRequestId);
      return;
    }
    try
    {
      paramAy = new JSONObject();
      paramAy.put("offset", paramBr.a());
      a(paramRequestId, "com.amazon.device.iap.physical.physical_get_receipts", paramAy);
      return;
    }
    catch (JSONException paramRequestId)
    {
      x.a(a, "Error in sendGetReceiptsRequest.");
    }
  }
  
  public final void a(RequestId paramRequestId, bt paramBt, ay paramAy)
  {
    x.b(a, "sendSearchByIdRequest");
    this.b = paramAy;
    if (!a())
    {
      paramRequestId = new SearchByIdResponse(paramRequestId, SearchByIdResponse.Status.d);
      paramAy.a(ay.a.d, paramRequestId);
      return;
    }
    try
    {
      paramAy = new JSONObject();
      paramAy.put("productType", "PHYSICAL");
      paramAy.put("productIds", new JSONArray(paramBt.a()));
      a(paramRequestId, "com.amazon.device.iap.physical.physical_searchById", paramAy);
      return;
    }
    catch (JSONException paramRequestId)
    {
      x.a(a, "Error in sendSearchByIdRequest.");
    }
  }
  
  public final void a(RequestId paramRequestId, bx paramBx, ay paramAy)
  {
    x.b(a, "sendSearchRequest");
    this.b = paramAy;
    if (!a())
    {
      paramRequestId = new SearchResponse(paramRequestId, SearchResponse.Status.d);
      paramAy.a(ay.a.c, paramRequestId);
      return;
    }
    try
    {
      paramAy = new JSONObject();
      paramAy.put("category", paramBx.a().toString());
      paramAy.put("searchTerm", paramBx.b());
      Map localMap = paramBx.c();
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
    paramAy.put("sortType", paramBx.d().toString());
    paramAy.put("page", paramBx.e());
    a(paramRequestId, "com.amazon.device.iap.physical.physical_search", paramAy);
  }
  
  public final void b(RequestId paramRequestId, ay paramAy)
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
  
  public final void c(RequestId paramRequestId, ay paramAy)
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
