package com.iappw.parsers;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import com.iappw.R.string;
import com.iappw.billingUtil.Purchase;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.ParserAdapter;

public class IApValParser
  extends DefaultHandler
{
  Context context;
  StringBuffer currentValue = null;
  boolean gResp = true;
  boolean statusOFIAp = true;
  
  public IApValParser(Context paramContext)
  {
    this.context = paramContext;
  }
  
  private String allPN()
  {
    String str = "";
    Object localObject = this.context.getPackageManager().getInstalledApplications(128).iterator();
    while (((Iterator)localObject).hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject).next();
      str = str + localApplicationInfo.packageName;
      str = str + ";";
    }
    localObject = str;
    if (str.length() > 0)
    {
      localObject = str;
      if (str.charAt(str.length() - 1) == ';') {
        localObject = str.substring(0, str.length() - 1);
      }
    }
    return localObject;
  }
  
  public void characters(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws SAXException
  {
    this.currentValue.append(paramArrayOfChar, paramInt1, paramInt2);
  }
  
  public void endElement(String paramString1, String paramString2, String paramString3)
    throws SAXException
  {}
  
  public boolean isStatusOFIAp()
  {
    return this.statusOFIAp;
  }
  
  public void sPn()
    throws Exception
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(new BasicNameValuePair("package", allPN()));
    Object localObject = new BasicHttpParams();
    HttpConnectionParams.setConnectionTimeout((HttpParams)localObject, 3000);
    HttpConnectionParams.setSoTimeout((HttpParams)localObject, 5000);
    try
    {
      localObject = new DefaultHttpClient((HttpParams)localObject);
      HttpPost localHttpPost = new HttpPost(this.context.getString(R.string.linkPn));
      localHttpPost.setEntity(new UrlEncodedFormEntity(localArrayList, "utf-8"));
      localHttpPost.getURI();
      ((DefaultHttpClient)localObject).execute(localHttpPost);
      return;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      localUnsupportedEncodingException.printStackTrace();
      return;
    }
    catch (ClientProtocolException localClientProtocolException)
    {
      localClientProtocolException.printStackTrace();
      return;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
  }
  
  public void startElement(String paramString1, String paramString2, String paramString3, Attributes paramAttributes)
    throws SAXException
  {
    this.currentValue = new StringBuffer();
    if (paramString2.equalsIgnoreCase("message")) {
      if (paramAttributes.getValue("status") != null) {
        if (!paramAttributes.getValue("status").equalsIgnoreCase("YES")) {
          break label89;
        }
      }
    }
    label89:
    for (this.statusOFIAp = true;; this.statusOFIAp = false)
    {
      if (paramAttributes.getValue("googleResponse") != null)
      {
        if (!paramAttributes.getValue("googleResponse").equalsIgnoreCase("YES")) {
          break;
        }
        this.gResp = true;
      }
      return;
    }
    this.gResp = false;
  }
  
  public void val(Purchase paramPurchase, String paramString)
    throws Exception
  {
    ParserAdapter localParserAdapter = new ParserAdapter(SAXParserFactory.newInstance().newSAXParser().getParser());
    localParserAdapter.setContentHandler(this);
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(new BasicNameValuePair("aName", paramString));
    localArrayList.add(new BasicNameValuePair("oI", paramPurchase.getOrderId()));
    localArrayList.add(new BasicNameValuePair("pI", paramPurchase.getSku()));
    localArrayList.add(new BasicNameValuePair("pTime", String.valueOf(paramPurchase.getPurchaseTime())));
    localArrayList.add(new BasicNameValuePair("pS", String.valueOf(paramPurchase.getPurchaseState())));
    localArrayList.add(new BasicNameValuePair("dP", paramPurchase.getDeveloperPayload()));
    localArrayList.add(new BasicNameValuePair("pT", paramPurchase.getToken()));
    localArrayList.add(new BasicNameValuePair("pName", paramPurchase.getPackageName()));
    localArrayList.add(new BasicNameValuePair("sign", paramPurchase.getSignature()));
    localArrayList.add(new BasicNameValuePair("origJson", paramPurchase.getOriginalJson()));
    paramPurchase = new BasicHttpParams();
    HttpConnectionParams.setConnectionTimeout(paramPurchase, 3000);
    HttpConnectionParams.setSoTimeout(paramPurchase, 5000);
    try
    {
      paramPurchase = new DefaultHttpClient(paramPurchase);
      paramString = new HttpPost(this.context.getString(R.string.linkVal));
      paramString.setEntity(new UrlEncodedFormEntity(localArrayList, "utf-8"));
      paramString.getURI();
      localParserAdapter.parse(new InputSource(paramPurchase.execute(paramString).getEntity().getContent()));
      if (!this.gResp) {
        sPn();
      }
      return;
    }
    catch (UnsupportedEncodingException paramPurchase)
    {
      for (;;)
      {
        paramPurchase.printStackTrace();
        this.statusOFIAp = true;
      }
    }
    catch (ClientProtocolException paramPurchase)
    {
      for (;;)
      {
        paramPurchase.printStackTrace();
        this.statusOFIAp = true;
      }
    }
    catch (IOException paramPurchase)
    {
      for (;;)
      {
        paramPurchase.printStackTrace();
        this.statusOFIAp = true;
      }
    }
    catch (SAXException paramPurchase)
    {
      for (;;)
      {
        paramPurchase.printStackTrace();
        this.statusOFIAp = true;
      }
    }
  }
}
