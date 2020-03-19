package com.iappw.parsers;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import com.iappw.R.string;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.ParserAdapter;

public class IApCoAnHaParser
  extends DefaultHandler
{
  Context context;
  private ArrayList<String> countries = new ArrayList();
  private String countryPom = "";
  StringBuffer currentValue = null;
  private ArrayList<String> hApps = new ArrayList();
  private ArrayList<String> pLs = new ArrayList();
  boolean parserStatus = false;
  SharedPreferences.Editor sharedEditor;
  SharedPreferences sharedPref;
  
  public IApCoAnHaParser(Context paramContext)
  {
    this.context = paramContext;
    this.sharedPref = paramContext.getSharedPreferences("iappch", 0);
    this.sharedEditor = this.sharedPref.edit();
  }
  
  private boolean checkCacheVal(Context paramContext, long paramLong)
  {
    if (this.sharedPref.getString("cacXl", null) == null) {}
    while (System.currentTimeMillis() - this.sharedPref.getLong("cacTime", 0L) >= paramLong) {
      return false;
    }
    return true;
  }
  
  private boolean checkIfCacheFileExist()
  {
    return this.sharedPref.getString("cacXl", null) != null;
  }
  
  public static boolean isOnline(Context paramContext)
  {
    boolean bool2 = false;
    NetworkInfo localNetworkInfo = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    boolean bool1 = bool2;
    if (localNetworkInfo != null)
    {
      bool1 = bool2;
      if (!localNetworkInfo.isConnected()) {}
    }
    try
    {
      paramContext = (HttpURLConnection)new URL(paramContext.getString(R.string.link)).openConnection();
      paramContext.setConnectTimeout(2000);
      paramContext.setReadTimeout(2000);
      paramContext.connect();
      int i = paramContext.getResponseCode();
      bool1 = bool2;
      if (i == 200) {
        bool1 = true;
      }
      return bool1;
    }
    catch (MalformedURLException paramContext)
    {
      return false;
    }
    catch (IOException paramContext)
    {
      return false;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  /* Error */
  private boolean parseFromAssets()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 45	com/iappw/parsers/IApCoAnHaParser:context	Landroid/content/Context;
    //   4: invokevirtual 154	android/content/Context:getAssets	()Landroid/content/res/AssetManager;
    //   7: astore_1
    //   8: invokestatic 160	javax/xml/parsers/SAXParserFactory:newInstance	()Ljavax/xml/parsers/SAXParserFactory;
    //   11: astore_2
    //   12: new 162	org/xml/sax/helpers/ParserAdapter
    //   15: dup
    //   16: aload_2
    //   17: invokevirtual 166	javax/xml/parsers/SAXParserFactory:newSAXParser	()Ljavax/xml/parsers/SAXParser;
    //   20: invokevirtual 172	javax/xml/parsers/SAXParser:getParser	()Lorg/xml/sax/Parser;
    //   23: invokespecial 175	org/xml/sax/helpers/ParserAdapter:<init>	(Lorg/xml/sax/Parser;)V
    //   26: astore_2
    //   27: aload_2
    //   28: aload_0
    //   29: invokevirtual 179	org/xml/sax/helpers/ParserAdapter:setContentHandler	(Lorg/xml/sax/ContentHandler;)V
    //   32: aload_2
    //   33: new 181	org/xml/sax/InputSource
    //   36: dup
    //   37: aload_1
    //   38: ldc -73
    //   40: invokevirtual 189	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   43: invokespecial 192	org/xml/sax/InputSource:<init>	(Ljava/io/InputStream;)V
    //   46: invokevirtual 196	org/xml/sax/helpers/ParserAdapter:parse	(Lorg/xml/sax/InputSource;)V
    //   49: iconst_0
    //   50: ireturn
    //   51: astore_1
    //   52: iconst_0
    //   53: ireturn
    //   54: astore_1
    //   55: iconst_0
    //   56: ireturn
    //   57: astore_1
    //   58: iconst_0
    //   59: ireturn
    //   60: astore_1
    //   61: iconst_0
    //   62: ireturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	63	0	this	IApCoAnHaParser
    //   7	31	1	localAssetManager	android.content.res.AssetManager
    //   51	1	1	localParserConfigurationException	ParserConfigurationException
    //   54	1	1	localSAXException1	SAXException
    //   57	1	1	localIOException	IOException
    //   60	1	1	localSAXException2	SAXException
    //   11	22	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   12	32	51	javax/xml/parsers/ParserConfigurationException
    //   12	32	54	org/xml/sax/SAXException
    //   32	49	57	java/io/IOException
    //   32	49	60	org/xml/sax/SAXException
  }
  
  /* Error */
  private boolean parseFromCache()
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_1
    //   2: invokestatic 160	javax/xml/parsers/SAXParserFactory:newInstance	()Ljavax/xml/parsers/SAXParserFactory;
    //   5: astore_2
    //   6: new 162	org/xml/sax/helpers/ParserAdapter
    //   9: dup
    //   10: aload_2
    //   11: invokevirtual 166	javax/xml/parsers/SAXParserFactory:newSAXParser	()Ljavax/xml/parsers/SAXParser;
    //   14: invokevirtual 172	javax/xml/parsers/SAXParser:getParser	()Lorg/xml/sax/Parser;
    //   17: invokespecial 175	org/xml/sax/helpers/ParserAdapter:<init>	(Lorg/xml/sax/Parser;)V
    //   20: astore_2
    //   21: aload_2
    //   22: aload_0
    //   23: invokevirtual 179	org/xml/sax/helpers/ParserAdapter:setContentHandler	(Lorg/xml/sax/ContentHandler;)V
    //   26: aload_0
    //   27: invokespecial 199	com/iappw/parsers/IApCoAnHaParser:checkIfCacheFileExist	()Z
    //   30: ifeq +39 -> 69
    //   33: aload_2
    //   34: new 181	org/xml/sax/InputSource
    //   37: dup
    //   38: new 201	java/io/StringReader
    //   41: dup
    //   42: aload_0
    //   43: getfield 55	com/iappw/parsers/IApCoAnHaParser:sharedPref	Landroid/content/SharedPreferences;
    //   46: ldc 68
    //   48: ldc 39
    //   50: invokeinterface 72 3 0
    //   55: invokespecial 202	java/io/StringReader:<init>	(Ljava/lang/String;)V
    //   58: invokespecial 205	org/xml/sax/InputSource:<init>	(Ljava/io/Reader;)V
    //   61: invokevirtual 196	org/xml/sax/helpers/ParserAdapter:parse	(Lorg/xml/sax/InputSource;)V
    //   64: aload_0
    //   65: getfield 43	com/iappw/parsers/IApCoAnHaParser:parserStatus	Z
    //   68: istore_1
    //   69: iload_1
    //   70: ireturn
    //   71: astore_2
    //   72: iconst_0
    //   73: ireturn
    //   74: astore_2
    //   75: iconst_0
    //   76: ireturn
    //   77: astore_2
    //   78: aload_0
    //   79: getfield 63	com/iappw/parsers/IApCoAnHaParser:sharedEditor	Landroid/content/SharedPreferences$Editor;
    //   82: ldc 68
    //   84: aconst_null
    //   85: invokeinterface 211 3 0
    //   90: pop
    //   91: aload_0
    //   92: getfield 63	com/iappw/parsers/IApCoAnHaParser:sharedEditor	Landroid/content/SharedPreferences$Editor;
    //   95: invokeinterface 214 1 0
    //   100: pop
    //   101: iconst_0
    //   102: ireturn
    //   103: astore_2
    //   104: aload_0
    //   105: getfield 63	com/iappw/parsers/IApCoAnHaParser:sharedEditor	Landroid/content/SharedPreferences$Editor;
    //   108: ldc 68
    //   110: aconst_null
    //   111: invokeinterface 211 3 0
    //   116: pop
    //   117: aload_0
    //   118: getfield 63	com/iappw/parsers/IApCoAnHaParser:sharedEditor	Landroid/content/SharedPreferences$Editor;
    //   121: invokeinterface 214 1 0
    //   126: pop
    //   127: iconst_0
    //   128: ireturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	129	0	this	IApCoAnHaParser
    //   1	69	1	bool	boolean
    //   5	29	2	localObject	Object
    //   71	1	2	localParserConfigurationException	ParserConfigurationException
    //   74	1	2	localSAXException1	SAXException
    //   77	1	2	localIOException	IOException
    //   103	1	2	localSAXException2	SAXException
    // Exception table:
    //   from	to	target	type
    //   6	26	71	javax/xml/parsers/ParserConfigurationException
    //   6	26	74	org/xml/sax/SAXException
    //   33	69	77	java/io/IOException
    //   33	69	103	org/xml/sax/SAXException
  }
  
  private boolean parseFromNet()
  {
    Object localObject1 = SAXParserFactory.newInstance();
    try
    {
      localObject1 = new ParserAdapter(((SAXParserFactory)localObject1).newSAXParser().getParser());
      ((ParserAdapter)localObject1).setContentHandler(this);
      ArrayList localArrayList = new ArrayList();
      Object localObject2 = new BasicHttpParams();
      HttpConnectionParams.setConnectionTimeout((HttpParams)localObject2, 3000);
      HttpConnectionParams.setSoTimeout((HttpParams)localObject2, 5000);
      localObject2 = new DefaultHttpClient((HttpParams)localObject2);
      HttpPost localHttpPost = new HttpPost(this.context.getString(R.string.link));
      boolean bool;
      return false;
    }
    catch (ParserConfigurationException localParserConfigurationException)
    {
      try
      {
        localHttpPost.setEntity(new UrlEncodedFormEntity(localArrayList, "utf-8"));
        localHttpPost.getURI();
        ((ParserAdapter)localObject1).parse(new InputSource(((DefaultHttpClient)localObject2).execute(localHttpPost).getEntity().getContent()));
        bool = this.parserStatus;
        return bool;
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
        localUnsupportedEncodingException.printStackTrace();
        return false;
      }
      catch (IOException localIOException)
      {
        localIOException.printStackTrace();
        return false;
      }
      catch (SAXException localSAXException2)
      {
        localSAXException2.printStackTrace();
      }
      localParserConfigurationException = localParserConfigurationException;
      localParserConfigurationException.printStackTrace();
      return false;
    }
    catch (SAXException localSAXException1)
    {
      localSAXException1.printStackTrace();
      return false;
    }
  }
  
  private boolean saveToCache()
  {
    Object localObject1 = new ArrayList();
    Object localObject2 = new BasicHttpParams();
    HttpConnectionParams.setConnectionTimeout((HttpParams)localObject2, 3000);
    HttpConnectionParams.setSoTimeout((HttpParams)localObject2, 5000);
    try
    {
      localObject2 = new DefaultHttpClient((HttpParams)localObject2);
      HttpPost localHttpPost = new HttpPost(this.context.getString(R.string.link));
      localHttpPost.setEntity(new UrlEncodedFormEntity((List)localObject1, "utf-8"));
      localHttpPost.getURI();
      localObject1 = ((DefaultHttpClient)localObject2).execute(localHttpPost);
      if (((HttpResponse)localObject1).getStatusLine().getStatusCode() == 200)
      {
        localObject1 = EntityUtils.toString(((HttpResponse)localObject1).getEntity());
        if ((localObject1 != null) && (((String)localObject1).length() > 0))
        {
          this.sharedEditor.putLong("cacTime", System.currentTimeMillis());
          this.sharedEditor.putString("cacXl", (String)localObject1);
          this.sharedEditor.commit();
          return true;
        }
      }
    }
    catch (IOException localIOException)
    {
      return false;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      for (;;) {}
    }
    catch (ClientProtocolException localClientProtocolException)
    {
      for (;;) {}
    }
  }
  
  public void characters(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws SAXException
  {
    this.currentValue.append(paramArrayOfChar, paramInt1, paramInt2);
  }
  
  public boolean check()
  {
    if (this.parserStatus)
    {
      if (this.countries.size() == 0) {
        return true;
      }
      return this.countries.contains(this.countryPom);
    }
    return false;
  }
  
  public boolean checkDevP(String paramString)
  {
    int i = 0;
    while (i < this.pLs.size())
    {
      if (paramString.contains((CharSequence)this.pLs.get(i))) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  public void endElement(String paramString1, String paramString2, String paramString3)
    throws SAXException
  {
    if (paramString2.equalsIgnoreCase("IApps")) {
      this.parserStatus = true;
    }
    do
    {
      return;
      if (paramString2.equalsIgnoreCase("plo"))
      {
        this.pLs.add(this.currentValue.toString());
        return;
      }
    } while (!paramString2.equalsIgnoreCase("countryCode"));
    this.countryPom = this.currentValue.toString().toLowerCase();
  }
  
  public boolean getCountry()
  {
    Object localObject;
    if ((this.countryPom != null) && (this.countryPom.length() == 0)) {
      localObject = SAXParserFactory.newInstance();
    }
    try
    {
      localObject = new ParserAdapter(((SAXParserFactory)localObject).newSAXParser().getParser());
      return false;
    }
    catch (ParserConfigurationException localParserConfigurationException1)
    {
      try
      {
        ((ParserAdapter)localObject).setContentHandler(this);
        ((ParserAdapter)localObject).parse(new InputSource(new URL("http://ip-api.com/xml/").openStream()));
        if ((this.countryPom != null) && (this.countryPom.length() == 0)) {
          this.countryPom = ((TelephonyManager)this.context.getSystemService("phone")).getSimCountryIso().toLowerCase();
        }
        if (this.countryPom == null) {
          this.countryPom = "";
        }
        return true;
      }
      catch (IOException localIOException2)
      {
        return false;
      }
      catch (MalformedURLException localMalformedURLException2)
      {
        return false;
      }
      catch (SAXException localSAXException2)
      {
        return false;
      }
      catch (ParserConfigurationException localParserConfigurationException2) {}
      localParserConfigurationException1 = localParserConfigurationException1;
      return false;
    }
    catch (SAXException localSAXException1)
    {
      return false;
    }
    catch (MalformedURLException localMalformedURLException1)
    {
      return false;
    }
    catch (IOException localIOException1)
    {
      return false;
    }
  }
  
  public String getpLs()
  {
    if ((this.pLs != null) && (this.pLs.size() > 0))
    {
      int i = new Random().nextInt(this.pLs.size());
      return (String)this.pLs.get(i);
    }
    return null;
  }
  
  public boolean isFU()
  {
    Iterator localIterator = this.context.getPackageManager().getInstalledApplications(128).iterator();
    while (localIterator.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
      if (this.hApps.contains(localApplicationInfo.packageName)) {
        return true;
      }
    }
    return false;
  }
  
  public int load(long paramLong)
    throws Exception
  {
    this.parserStatus = false;
    if (isOnline(this.context))
    {
      getCountry();
      int i = 0;
      if (!checkCacheVal(this.context, paramLong))
      {
        if (saveToCache()) {
          i = 1;
        }
        if (i == 0) {
          break label66;
        }
        parseFromCache();
        if (!this.parserStatus) {
          break label66;
        }
      }
      label66:
      do
      {
        do
        {
          return 0;
          i = 1;
          break;
          parseFromNet();
        } while (this.parserStatus);
        parseFromAssets();
      } while (this.parserStatus);
      return -2;
    }
    return -1;
  }
  
  public void startElement(String paramString1, String paramString2, String paramString3, Attributes paramAttributes)
    throws SAXException
  {
    this.currentValue = new StringBuffer();
    if (paramString2.equalsIgnoreCase("country")) {
      this.countries.add(paramAttributes.getValue("code"));
    }
    while (!paramString2.equalsIgnoreCase("app")) {
      return;
    }
    this.hApps.add(paramAttributes.getValue("id"));
  }
}
