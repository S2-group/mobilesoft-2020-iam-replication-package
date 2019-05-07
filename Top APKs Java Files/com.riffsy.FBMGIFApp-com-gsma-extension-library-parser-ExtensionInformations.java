package com.gsma.extension.library.parser;

import android.annotation.SuppressLint;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.gsma.extension.library.utils.ExtensionEndorsement;
import com.gsma.extension.library.utils.ExtensionEndorser;
import com.gsma.extension.library.utils.HexUtils;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public final class ExtensionInformations
{
  private static final String ANDROID_NS = "http://schemas.android.com/apk/res/android";
  private static final String ENDORSEMENT_METADATA = "gsma-extension-endorsement";
  private static final String EXTENSION_DESCRIPTOR_METADATA = "gsma-extension-descriptor";
  private static final String EXTENSION_DESCRIPTOR_METADATA_PREFIX = "gsma-extension-descriptor:";
  private static final String LOGTAG = ExtensionInformations.class.getName();
  private static final boolean PARSING_LOGS = false;
  private static final String STETEL_NS = "http://schemas.gsma.com/extensions";
  private boolean mAlwaysAnonymize;
  private final boolean mEndorsementEnabled;
  private final Collection<ExtensionEndorser> mEndorsers;
  private final XmlPullParserFactory mParserFactory;
  private final boolean mSignatureCheckEnabled;
  
  public ExtensionInformations(boolean paramBoolean1, boolean paramBoolean2, Collection<ExtensionEndorser> paramCollection)
  {
    this.mSignatureCheckEnabled = paramBoolean1;
    this.mEndorsementEnabled = paramBoolean2;
    if (paramCollection == null) {
      this.mEndorsers = null;
    }
    for (;;)
    {
      try
      {
        this.mParserFactory = XmlPullParserFactory.newInstance();
        this.mParserFactory.setNamespaceAware(true);
        this.mAlwaysAnonymize = false;
        return;
      }
      catch (XmlPullParserException paramCollection)
      {
        throw new RuntimeException(paramCollection);
      }
      this.mEndorsers = new LinkedList();
      this.mEndorsers.addAll(paramCollection);
    }
  }
  
  private final ExtensionInfo getExtensionInfo(PackageManager paramPackageManager, ApplicationInfo paramApplicationInfo)
  {
    ExtensionInfo localExtensionInfo = null;
    Bundle localBundle = paramApplicationInfo.metaData;
    if (localBundle != null)
    {
      int j = localBundle.getInt("gsma-extension-descriptor", 0);
      int i = 0;
      if (j != 0) {
        i = localBundle.getInt("gsma-extension-endorsement", 0);
      }
      localExtensionInfo = getExtensionInfoFromResId(paramPackageManager, paramApplicationInfo, j, i);
    }
    return localExtensionInfo;
  }
  
  private ExtensionInfo getExtensionInfoFromResId(PackageManager paramPackageManager, ApplicationInfo paramApplicationInfo, int paramInt1, int paramInt2)
  {
    XmlResourceParser localXmlResourceParser = null;
    Object localObject2 = null;
    Object localObject3 = null;
    Resources localResources;
    if (paramInt1 != 0)
    {
      localObject1 = localXmlResourceParser;
      try
      {
        localResources = paramPackageManager.getResourcesForApplication(paramApplicationInfo);
        localObject1 = localXmlResourceParser;
        localXmlResourceParser = localResources.getXml(paramInt1);
        paramPackageManager = localObject3;
        for (;;)
        {
          label39:
          localObject1 = paramPackageManager;
          if (localXmlResourceParser.next() == 1) {
            break;
          }
          localObject1 = paramPackageManager;
          switch (localXmlResourceParser.getEventType())
          {
          case 2: 
            localObject1 = paramPackageManager;
            if ("extension-provider".equals(localXmlResourceParser.getName()))
            {
              localObject2 = paramPackageManager;
              for (;;)
              {
                paramPackageManager = (PackageManager)localObject2;
                localObject1 = localObject2;
                if (localXmlResourceParser.next() == 3) {
                  break;
                }
                localObject1 = localObject2;
                if (localXmlResourceParser.getEventType() == 2)
                {
                  localObject1 = localObject2;
                  if ("extension".equals(localXmlResourceParser.getName()))
                  {
                    localObject1 = localObject2;
                    localObject2 = parseExtension(localXmlResourceParser, paramApplicationInfo.packageName, localResources);
                  }
                }
              }
            }
            break;
          case 3: 
            localObject1 = paramPackageManager;
            Log.d(LOGTAG, "End TAG " + localXmlResourceParser.getName());
          }
        }
        return localObject2;
      }
      catch (Throwable paramPackageManager)
      {
        Log.d(LOGTAG, "Error retrieving extension infos", paramPackageManager);
        localObject2 = localObject1;
      }
    }
    label231:
    do
    {
      localObject2 = paramPackageManager;
    } while (paramPackageManager == null);
    Object localObject1 = paramPackageManager;
    paramPackageManager.endorsementResource = paramInt2;
    localObject1 = paramPackageManager;
    paramInt1 = paramPackageManager.endorsementResource;
    if (paramInt1 != 0) {
      localObject1 = paramPackageManager;
    }
    for (;;)
    {
      try
      {
        paramApplicationInfo = this.mParserFactory.newPullParser();
        localObject1 = paramPackageManager;
        paramApplicationInfo.setInput(new InputStreamReader(localResources.openRawResource(paramPackageManager.endorsementResource)));
      }
      catch (XmlPullParserException paramApplicationInfo)
      {
        localObject1 = paramPackageManager;
        Log.d(LOGTAG, "Reading endorsement for extension " + paramPackageManager.label, paramApplicationInfo);
        return paramPackageManager;
        localObject1 = paramPackageManager;
        Log.d(LOGTAG, "End TAG " + paramApplicationInfo.getName());
        continue;
      }
      catch (IOException paramApplicationInfo)
      {
        localObject1 = paramPackageManager;
        Log.d(LOGTAG, "Reading endorsement for extension " + paramPackageManager.label, paramApplicationInfo);
        return paramPackageManager;
      }
      localObject1 = paramPackageManager;
      if (paramApplicationInfo.next() != 1) {
        localObject1 = paramPackageManager;
      }
      switch (paramApplicationInfo.getEventType())
      {
      case 2: 
        localObject1 = paramPackageManager;
        if ("extension-publisher".equals(paramApplicationInfo.getName()))
        {
          localObject1 = paramPackageManager;
          paramPackageManager.endorsementDocument = parseEndorsementDocument(paramApplicationInfo);
          localObject1 = paramPackageManager;
          parseToEnd(paramApplicationInfo);
        }
        break;
      case 3: 
        localObject1 = paramPackageManager;
        localObject2 = paramPackageManager;
        if (paramPackageManager.endorsementDocument != null) {
          break label231;
        }
        localObject1 = paramPackageManager;
        Log.d(LOGTAG, "No valid endorsement document found " + paramPackageManager.label);
        return paramPackageManager;
        localObject1 = paramPackageManager;
        Log.d(LOGTAG, "Extension endorsement not declared in manifest for extension " + paramPackageManager.label);
        return paramPackageManager;
        break label39;
      }
    }
  }
  
  private final int getInt(String paramString, Resources paramResources, int paramInt)
  {
    int i;
    if ((paramString != null) && (paramString.startsWith("@"))) {
      try
      {
        i = Integer.parseInt(paramString.substring(1));
        return i;
      }
      catch (NumberFormatException paramResources)
      {
        Log.d(LOGTAG, "Parsing integer " + paramString, paramResources);
        return paramInt;
      }
    }
    try
    {
      i = Integer.parseInt(getString(paramString, paramResources, null));
      return i;
    }
    catch (NumberFormatException paramString) {}
    return paramInt;
  }
  
  public static final int getInt(XmlPullParser paramXmlPullParser, int paramInt)
    throws XmlPullParserException, IOException
  {
    try
    {
      int i = Integer.parseInt(getText(paramXmlPullParser, null));
      return i;
    }
    catch (Throwable paramXmlPullParser) {}
    return paramInt;
  }
  
  private final String getString(String paramString1, Resources paramResources, String paramString2)
  {
    String str1 = paramString1;
    String str2 = str1;
    if (paramString1 != null)
    {
      str2 = str1;
      if (paramString1.startsWith("@"))
      {
        if (!paramString1.startsWith("@string/")) {
          break label75;
        }
        str2 = paramResources.getString(paramResources.getIdentifier(paramString2 + ":" + paramString1.substring(1), null, null));
      }
    }
    return str2;
    try
    {
      label75:
      paramString1 = paramResources.getString(Integer.parseInt(paramString1.substring(1)));
      return paramString1;
    }
    catch (NumberFormatException paramString1) {}
    return str1;
  }
  
  public static final String getText(XmlPullParser paramXmlPullParser, String paramString)
    throws XmlPullParserException, IOException
  {
    Object localObject2 = null;
    if (paramXmlPullParser.getEventType() == 2)
    {
      localObject2 = paramXmlPullParser.nextText();
      Object localObject1 = localObject2;
      if (localObject2 != null) {
        localObject1 = ((String)localObject2).replace("\n", "").trim();
      }
      localObject2 = localObject1;
      if (paramXmlPullParser.getEventType() != 3)
      {
        paramXmlPullParser.next();
        localObject2 = localObject1;
      }
    }
    if (localObject2 == null) {
      return paramString;
    }
    return localObject2;
  }
  
  private final void parseActions(XmlPullParser paramXmlPullParser, ExtensionInfo paramExtensionInfo, Resources paramResources)
    throws XmlPullParserException, IOException
  {
    String str2 = paramExtensionInfo.packageName;
    while (paramXmlPullParser.next() != 3) {
      if ((paramXmlPullParser.getEventType() == 2) && ("action".equals(paramXmlPullParser.getName())))
      {
        Action localAction = new Action(getString(paramXmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", "name"), paramResources, str2), getString(paramXmlPullParser.getAttributeValue("http://schemas.gsma.com/extensions", "type"), paramResources, str2), getString(paramXmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", "label"), paramResources, str2), getInt(paramXmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", "icon"), paramResources, 0), null, null);
        LinkedList localLinkedList1 = new LinkedList();
        LinkedList localLinkedList2 = new LinkedList();
        String str1 = null;
        while (paramXmlPullParser.next() != 3) {
          if (paramXmlPullParser.getEventType() == 2)
          {
            String str3;
            if ("mimetype".equals(paramXmlPullParser.getName()))
            {
              str3 = getString(paramXmlPullParser.getAttributeValue("http://schemas.gsma.com/extensions", "mimetype"), paramResources, str2);
              if (!TextUtils.isEmpty(str3)) {
                localLinkedList1.add(str3);
              }
              parseToEnd(paramXmlPullParser);
            }
            else if ("context".equals(paramXmlPullParser.getName()))
            {
              str3 = getString(paramXmlPullParser.getAttributeValue("http://schemas.gsma.com/extensions", "name"), paramResources, str2);
              LinkedList localLinkedList3 = new LinkedList();
              int i = 0;
              while (paramXmlPullParser.next() == 2)
              {
                i = 1;
                if ("sub-context".equals(paramXmlPullParser.getName()))
                {
                  String str4 = getString(paramXmlPullParser.getAttributeValue("http://schemas.gsma.com/extensions", "name"), paramResources, str2);
                  if (!TextUtils.isEmpty(str4)) {
                    localLinkedList3.add(str4);
                  }
                }
                parseToEnd(paramXmlPullParser);
              }
              if (!TextUtils.isEmpty(str3))
              {
                if (!localLinkedList3.isEmpty()) {
                  break label397;
                }
                localLinkedList2.add(new Context(str3, null));
              }
              for (;;)
              {
                if ((i != 0) || (paramXmlPullParser.getEventType() == 3)) {
                  break label425;
                }
                parseToEnd(paramXmlPullParser);
                break;
                label397:
                localLinkedList2.add(new Context(str3, (String[])localLinkedList3.toArray(new String[0])));
              }
            }
            else
            {
              label425:
              if ("text-regexp".equals(paramXmlPullParser.getName()))
              {
                str1 = getString(paramXmlPullParser.getAttributeValue("http://schemas.gsma.com/extensions", "name"), paramResources, str2);
                parseToEnd(paramXmlPullParser);
              }
            }
          }
        }
        if (!localAction.isEmpty())
        {
          if (!localLinkedList1.isEmpty()) {
            localAction.mimetypes = ((String[])localLinkedList1.toArray(new String[0]));
          }
          if (!localLinkedList2.isEmpty()) {
            localAction.contexts = ((Context[])localLinkedList2.toArray(new Context[0]));
          }
          if (!TextUtils.isEmpty(str1)) {
            localAction.textRegexp = str1;
          }
          paramExtensionInfo.actions.add(localAction);
        }
      }
    }
  }
  
  private final EndorsementDocument parseEndorsementDocument(XmlPullParser paramXmlPullParser)
    throws XmlPullParserException, IOException
  {
    EndorsementDocument localEndorsementDocument = new EndorsementDocument();
    while (paramXmlPullParser.next() != 3) {
      if (paramXmlPullParser.getEventType() == 2) {
        if ("entity".equals(paramXmlPullParser.getName()))
        {
          localEndorsementDocument.entity = getText(paramXmlPullParser, null);
        }
        else if ("application".equals(paramXmlPullParser.getName()))
        {
          localEndorsementDocument.application = getText(paramXmlPullParser, null);
        }
        else if ("packagename".equals(paramXmlPullParser.getName()))
        {
          localEndorsementDocument.packageName = getText(paramXmlPullParser, null);
        }
        else if ("versioncode".equals(paramXmlPullParser.getName()))
        {
          localEndorsementDocument.versionCode = getInt(paramXmlPullParser, 0);
        }
        else if ("maxversioncode".equals(paramXmlPullParser.getName()))
        {
          localEndorsementDocument.maxVersionCode = getInt(paramXmlPullParser, 0);
        }
        else if ("publisherfingerprint".equals(paramXmlPullParser.getName()))
        {
          localEndorsementDocument.hash = paramXmlPullParser.getAttributeValue(null, "hash");
          Object localObject = getText(paramXmlPullParser, null);
          if (TextUtils.isEmpty((CharSequence)localObject))
          {
            Log.d(LOGTAG, "No fingerprint found in extension document");
          }
          else
          {
            String[] arrayOfString = ((String)localObject).split(":");
            if ((arrayOfString != null) && (arrayOfString.length > 0))
            {
              localObject = new byte[arrayOfString.length];
              int i = 0;
              while (i < localObject.length)
              {
                localObject[i] = ((byte)(Integer.parseInt(arrayOfString[i], 16) & 0xFF));
                i += 1;
              }
              localEndorsementDocument.publisherFingerprint = ((byte[])localObject);
            }
            else
            {
              Log.d(LOGTAG, "Wrong fingerprint format: " + (String)localObject);
            }
          }
        }
        else if ("privacy".equals(paramXmlPullParser.getName()))
        {
          localEndorsementDocument.privacyAddress = paramXmlPullParser.getAttributeValue(null, "addresses");
        }
        else
        {
          parseToEnd(paramXmlPullParser);
        }
      }
    }
    return localEndorsementDocument;
  }
  
  private final void parseEndorsements(XmlPullParser paramXmlPullParser, ExtensionInfo paramExtensionInfo, Resources paramResources)
    throws XmlPullParserException, IOException
  {
    String str1 = paramExtensionInfo.packageName;
    while (paramXmlPullParser.next() != 3) {
      if ((paramXmlPullParser.getEventType() == 2) && ("endorser".equals(paramXmlPullParser.getName())))
      {
        String str2 = getString(paramXmlPullParser.getAttributeValue("http://schemas.gsma.com/extensions", "name"), paramResources, str1);
        String str3 = getString(paramXmlPullParser.getAttributeValue("http://schemas.gsma.com/extensions", "hash"), paramResources, str1);
        Object localObject = getText(paramXmlPullParser, null);
        if (localObject != null) {
          try
          {
            localObject = new Endorser(str2, str3, HexUtils.hexStringToByteArray((String)localObject));
            if (!((Endorser)localObject).isEmpty()) {
              paramExtensionInfo.endorsement.add(localObject);
            }
          }
          catch (NumberFormatException localNumberFormatException)
          {
            Log.d(LOGTAG, "Invalid endorser value for " + str2 + " " + str3, localNumberFormatException);
          }
        }
      }
    }
  }
  
  private final void parseEvents(XmlPullParser paramXmlPullParser, ExtensionInfo paramExtensionInfo, Resources paramResources)
    throws XmlPullParserException, IOException
  {
    String str = paramExtensionInfo.packageName;
    while (paramXmlPullParser.next() != 3) {
      if ((paramXmlPullParser.getEventType() == 2) && ("event".equals(paramXmlPullParser.getName())))
      {
        Event localEvent = new Event(getString(paramXmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", "name"), paramResources, str), getString(paramXmlPullParser.getAttributeValue("http://schemas.gsma.com/extensions", "type"), paramResources, str));
        if (!localEvent.isEmpty()) {
          paramExtensionInfo.events.add(localEvent);
        }
        while (paramXmlPullParser.next() != 3) {}
      }
    }
  }
  
  private final ExtensionInfo parseExtension(XmlPullParser paramXmlPullParser, String paramString, Resources paramResources)
    throws XmlPullParserException, IOException
  {
    ExtensionInfo localExtensionInfo = new ExtensionInfo(paramString);
    localExtensionInfo.packageName = paramString;
    localExtensionInfo.name = getString(paramXmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", "name"), paramResources, paramString);
    localExtensionInfo.label = getString(paramXmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", "label"), paramResources, paramString);
    localExtensionInfo.versionCode = getInt(paramXmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", "versionCode"), paramResources, 0);
    localExtensionInfo.versionName = getString(paramXmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", "versionName"), paramResources, paramString);
    localExtensionInfo.mimetype = getString(paramXmlPullParser.getAttributeValue("http://schemas.gsma.com/extensions", "mimetype"), paramResources, paramString);
    localExtensionInfo.type = getString(paramXmlPullParser.getAttributeValue("http://schemas.gsma.com/extensions", "type"), paramResources, paramString);
    localExtensionInfo.description = getString(paramXmlPullParser.getAttributeValue("http://schemas.gsma.com/extensions", "description"), paramResources, paramString);
    localExtensionInfo.disclaimer = getString(paramXmlPullParser.getAttributeValue("http://schemas.gsma.com/extensions", "disclaimer"), paramResources, paramString);
    while (paramXmlPullParser.next() != 3) {
      if (paramXmlPullParser.getEventType() == 2) {
        if ("actions".equals(paramXmlPullParser.getName())) {
          parseActions(paramXmlPullParser, localExtensionInfo, paramResources);
        } else if ("events".equals(paramXmlPullParser.getName())) {
          parseEvents(paramXmlPullParser, localExtensionInfo, paramResources);
        } else if ("providers".equals(paramXmlPullParser.getName())) {
          parseProviders(paramXmlPullParser, localExtensionInfo, paramResources);
        } else if ("endorsements".equals(paramXmlPullParser.getName())) {
          parseEndorsements(paramXmlPullParser, localExtensionInfo, paramResources);
        } else {
          parseToEnd(paramXmlPullParser);
        }
      }
    }
    return localExtensionInfo;
  }
  
  private final void parseProviders(XmlPullParser paramXmlPullParser, ExtensionInfo paramExtensionInfo, Resources paramResources)
    throws XmlPullParserException, IOException
  {
    String str = paramExtensionInfo.packageName;
    while (paramXmlPullParser.next() != 3) {
      if ((paramXmlPullParser.getEventType() == 2) && ("provider".equals(paramXmlPullParser.getName())))
      {
        Provider localProvider = new Provider(getString(paramXmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", "authorities"), paramResources, str));
        if (!localProvider.isEmpty()) {
          paramExtensionInfo.providers.add(localProvider);
        }
        while (paramXmlPullParser.next() != 3) {}
      }
    }
  }
  
  private final void parseToEnd(XmlPullParser paramXmlPullParser)
    throws IOException, XmlPullParserException
  {
    int i = 1;
    while (i > 0) {
      switch (paramXmlPullParser.next())
      {
      default: 
        break;
      case 1: 
        i = 0;
        break;
      case 2: 
        i += 1;
        break;
      case 3: 
        i -= 1;
      }
    }
  }
  
  public static final void printParsingStatus(XmlPullParser paramXmlPullParser, String paramString)
  {
    try
    {
      Log.v(LOGTAG, "Starting endorsements parsing (" + paramString + "), event = " + paramXmlPullParser.getEventType() + ", name " + paramXmlPullParser.getName());
      return;
    }
    catch (XmlPullParserException paramXmlPullParser)
    {
      Log.d(LOGTAG, "Xml parsing exception", paramXmlPullParser);
    }
  }
  
  @Deprecated
  public ExtensionInfo getExtensionInfo(android.content.Context paramContext, String paramString)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (paramString != null)
    {
      paramContext = getExtensionsInfo(paramContext).iterator();
      do
      {
        localObject1 = localObject2;
        if (!paramContext.hasNext()) {
          break;
        }
        localObject1 = (ExtensionInfo)paramContext.next();
      } while (!paramString.equals(((ExtensionInfo)localObject1).packageName));
    }
    return localObject1;
  }
  
  @SuppressLint({"WrongConstant"})
  public List<ExtensionInfo> getExtensionsInfo(android.content.Context paramContext)
  {
    Object localObject1 = null;
    PackageManager localPackageManager = paramContext.getPackageManager();
    List localList = getInternalExtensionsInfo(paramContext);
    Object localObject2 = localPackageManager.getInstalledApplications(192);
    if (!((List)localObject2).isEmpty())
    {
      LinkedList localLinkedList = new LinkedList();
      localObject2 = ((List)localObject2).iterator();
      for (;;)
      {
        localObject1 = localLinkedList;
        if (!((Iterator)localObject2).hasNext()) {
          break;
        }
        localObject1 = (ApplicationInfo)((Iterator)localObject2).next();
        ExtensionInfo localExtensionInfo = getExtensionInfo(localPackageManager, (ApplicationInfo)localObject1);
        if ((localExtensionInfo != null) && (!localExtensionInfo.isEmpty()))
        {
          if (TextUtils.isEmpty(localExtensionInfo.mimetype))
          {
            Log.e(LOGTAG, "Error parsing extension with packagename " + localExtensionInfo.packageName);
          }
          else
          {
            boolean bool1;
            label158:
            boolean bool2;
            label168:
            boolean bool3;
            label197:
            label258:
            Object localObject3;
            StringBuilder localStringBuilder;
            if (!this.mSignatureCheckEnabled)
            {
              bool1 = true;
              if (this.mEndorsementEnabled) {
                break label618;
              }
              bool2 = true;
              bool3 = bool1;
              if (this.mSignatureCheckEnabled)
              {
                if (!paramContext.getPackageName().equals(((ApplicationInfo)localObject1).packageName)) {
                  break label624;
                }
                bool1 = true;
                Log.v(LOGTAG, "Signature verification for extension " + localExtensionInfo.label + " = " + bool1);
                bool3 = bool1;
              }
              if (!bool3) {
                break label647;
              }
              localExtensionInfo.setClearAddresses(true);
              bool1 = bool2;
              if ((bool1) && (localExtensionInfo.endorsementDocument != null) && ("clear".equalsIgnoreCase(localExtensionInfo.endorsementDocument.privacyAddress))) {
                localExtensionInfo.setClearAddresses(true);
              }
              if (this.mAlwaysAnonymize) {
                localExtensionInfo.setClearAddresses(false);
              }
              localObject3 = LOGTAG;
              localStringBuilder = new StringBuilder().append("Extension ").append(localExtensionInfo.label).append(" signature check ");
              if (!this.mSignatureCheckEnabled) {
                break label689;
              }
              localObject1 = "enabled";
              label353:
              localStringBuilder = localStringBuilder.append((String)localObject1).append(" result = ").append(bool3).append(" endorsement check = ");
              if (!this.mEndorsementEnabled) {
                break label697;
              }
              localObject1 = "enabled";
              label391:
              localStringBuilder = localStringBuilder.append((String)localObject1).append(" result = ").append(bool1).append(" clear addresses = ");
              if (!localExtensionInfo.isClearAddresses()) {
                break label705;
              }
            }
            label618:
            label624:
            label647:
            label689:
            label697:
            label705:
            for (localObject1 = "enabled";; localObject1 = "disabled")
            {
              Log.v((String)localObject3, (String)localObject1);
              if ((this.mSignatureCheckEnabled) && (!bool3) && (!bool1)) {
                break label713;
              }
              int j = 0;
              int i = j;
              if (localList != null)
              {
                localObject1 = localList.iterator();
                do
                {
                  i = j;
                  if (!((Iterator)localObject1).hasNext()) {
                    break;
                  }
                  localObject3 = (ExtensionInfo)((Iterator)localObject1).next();
                } while ((localObject3 == null) || (((ExtensionInfo)localObject3).mimetype == null) || (!((ExtensionInfo)localObject3).mimetype.equals(localExtensionInfo.mimetype)));
                Log.v(LOGTAG, "Skipping extension " + localExtensionInfo.label + " because the same mimetype has been found internally in " + ((ExtensionInfo)localObject3).label + " - " + ((ExtensionInfo)localObject3).name);
                i = 1;
              }
              if (i != 0) {
                break;
              }
              localLinkedList.add(localExtensionInfo);
              break;
              bool1 = false;
              break label158;
              bool2 = false;
              break label168;
              if (localPackageManager.checkSignatures(paramContext.getPackageName(), ((ApplicationInfo)localObject1).packageName) < 0) {
                break label197;
              }
              bool1 = true;
              break label197;
              bool1 = bool2;
              if (bool3) {
                break label258;
              }
              bool1 = bool2;
              if (!this.mEndorsementEnabled) {
                break label258;
              }
              bool1 = ExtensionEndorsement.checkExtensionEndorsement(localPackageManager, ((ApplicationInfo)localObject1).packageName, localExtensionInfo, this.mEndorsers, false);
              break label258;
              localObject1 = "disabled";
              break label353;
              localObject1 = "disabled";
              break label391;
            }
            label713:
            Log.w(LOGTAG, "Invalid security for extension " + localExtensionInfo.label);
          }
        }
        else {
          Log.v(LOGTAG, "No extension info in app with package " + ((ApplicationInfo)localObject1).packageName);
        }
      }
    }
    paramContext = (android.content.Context)localObject1;
    if (localList != null)
    {
      if (localObject1 != null) {
        localList.addAll((Collection)localObject1);
      }
      paramContext = localList;
    }
    return paramContext;
  }
  
  public List<ExtensionInfo> getInternalExtensionsInfo(android.content.Context paramContext)
  {
    ExtensionInfo localExtensionInfo = null;
    Iterator localIterator = null;
    PackageManager localPackageManager = paramContext.getPackageManager();
    Object localObject = localIterator;
    for (;;)
    {
      try
      {
        localPackageInfo = localPackageManager.getPackageInfo(paramContext.getPackageName(), 128);
        paramContext = localExtensionInfo;
        if (localPackageInfo != null)
        {
          localObject = localIterator;
          paramContext = localExtensionInfo;
          if (localPackageInfo.applicationInfo != null)
          {
            localObject = localIterator;
            localBundle = localPackageInfo.applicationInfo.metaData;
            paramContext = localExtensionInfo;
            if (localBundle != null)
            {
              localObject = localIterator;
              localIterator = localBundle.keySet().iterator();
              paramContext = null;
            }
          }
        }
      }
      catch (PackageManager.NameNotFoundException paramContext)
      {
        PackageInfo localPackageInfo;
        Bundle localBundle;
        int i;
        paramContext.printStackTrace();
        paramContext = (android.content.Context)localObject;
      }
      try
      {
        if (!localIterator.hasNext()) {
          return paramContext;
        }
        localObject = (String)localIterator.next();
        if ((localObject == null) || (!((String)localObject).startsWith("gsma-extension-descriptor"))) {
          break label213;
        }
        i = localBundle.getInt((String)localObject, 0);
        if (i == 0) {
          break label213;
        }
        localExtensionInfo = getExtensionInfoFromResId(localPackageManager, localPackageInfo.applicationInfo, i, 0);
        if (localExtensionInfo == null) {
          break label213;
        }
        if (paramContext != null) {
          break label210;
        }
        localObject = new LinkedList();
        paramContext = (android.content.Context)localObject;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        localObject = paramContext;
        paramContext = localNameNotFoundException;
        continue;
        continue;
        continue;
      }
      localObject = paramContext;
      localExtensionInfo.setClearAddresses(true);
      localObject = paramContext;
      paramContext.add(localExtensionInfo);
    }
    return paramContext;
    label210:
    label213:
    return paramContext;
  }
  
  /* Error */
  public final List<ExtensionInfo> getMyExtensionInfo(android.content.Context paramContext)
  {
    // Byte code:
    //   0: new 2	com/gsma/extension/library/parser/ExtensionInformations
    //   3: dup
    //   4: iconst_0
    //   5: iconst_0
    //   6: aconst_null
    //   7: invokespecial 628	com/gsma/extension/library/parser/ExtensionInformations:<init>	(ZZLjava/util/Collection;)V
    //   10: astore_3
    //   11: aconst_null
    //   12: astore 4
    //   14: aconst_null
    //   15: astore 6
    //   17: aload_1
    //   18: invokevirtual 527	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   21: astore 7
    //   23: aload 7
    //   25: aload_1
    //   26: invokevirtual 546	android/content/Context:getPackageName	()Ljava/lang/String;
    //   29: sipush 128
    //   32: invokevirtual 632	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   35: astore 5
    //   37: aload_3
    //   38: aload 7
    //   40: aload 5
    //   42: invokespecial 537	com/gsma/extension/library/parser/ExtensionInformations:getExtensionInfo	(Landroid/content/pm/PackageManager;Landroid/content/pm/ApplicationInfo;)Lcom/gsma/extension/library/parser/ExtensionInfo;
    //   45: astore 8
    //   47: aload 5
    //   49: astore_3
    //   50: aload 6
    //   52: astore_1
    //   53: aload 8
    //   55: ifnull +23 -> 78
    //   58: new 71	java/util/LinkedList
    //   61: dup
    //   62: invokespecial 72	java/util/LinkedList:<init>	()V
    //   65: astore_1
    //   66: aload_1
    //   67: aload 8
    //   69: invokeinterface 348 2 0
    //   74: pop
    //   75: aload 5
    //   77: astore_3
    //   78: aload_1
    //   79: astore 4
    //   81: aload_3
    //   82: ifnull +169 -> 251
    //   85: aload_1
    //   86: astore 4
    //   88: aload_1
    //   89: ifnonnull +162 -> 251
    //   92: aload_1
    //   93: astore 4
    //   95: aload_3
    //   96: getfield 93	android/content/pm/ApplicationInfo:metaData	Landroid/os/Bundle;
    //   99: ifnull +152 -> 251
    //   102: aload_3
    //   103: getfield 93	android/content/pm/ApplicationInfo:metaData	Landroid/os/Bundle;
    //   106: invokevirtual 619	android/os/Bundle:keySet	()Ljava/util/Set;
    //   109: invokeinterface 622 1 0
    //   114: astore 5
    //   116: aload 5
    //   118: invokeinterface 514 1 0
    //   123: ifeq +125 -> 248
    //   126: aload 5
    //   128: invokeinterface 517 1 0
    //   133: checkcast 133	java/lang/String
    //   136: astore 6
    //   138: aload 6
    //   140: ldc 17
    //   142: invokevirtual 229	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   145: ifeq +118 -> 263
    //   148: aload_3
    //   149: getfield 93	android/content/pm/ApplicationInfo:metaData	Landroid/os/Bundle;
    //   152: aload 6
    //   154: invokevirtual 634	android/os/Bundle:getInt	(Ljava/lang/String;)I
    //   157: istore_2
    //   158: iload_2
    //   159: ifeq +104 -> 263
    //   162: aload_0
    //   163: aload 7
    //   165: aload_3
    //   166: iload_2
    //   167: iconst_0
    //   168: invokespecial 103	com/gsma/extension/library/parser/ExtensionInformations:getExtensionInfoFromResId	(Landroid/content/pm/PackageManager;Landroid/content/pm/ApplicationInfo;II)Lcom/gsma/extension/library/parser/ExtensionInfo;
    //   171: astore 8
    //   173: aload 8
    //   175: ifnull +88 -> 263
    //   178: aload_1
    //   179: ifnonnull +87 -> 266
    //   182: new 71	java/util/LinkedList
    //   185: dup
    //   186: invokespecial 72	java/util/LinkedList:<init>	()V
    //   189: astore 4
    //   191: aload 4
    //   193: astore_1
    //   194: aload_1
    //   195: aload 8
    //   197: invokeinterface 348 2 0
    //   202: pop
    //   203: goto -87 -> 116
    //   206: astore_1
    //   207: aload 4
    //   209: astore_1
    //   210: aconst_null
    //   211: astore_3
    //   212: goto -134 -> 78
    //   215: astore 4
    //   217: getstatic 42	com/gsma/extension/library/parser/ExtensionInformations:LOGTAG	Ljava/lang/String;
    //   220: new 148	java/lang/StringBuilder
    //   223: dup
    //   224: invokespecial 149	java/lang/StringBuilder:<init>	()V
    //   227: ldc_w 636
    //   230: invokevirtual 155	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   233: aload 6
    //   235: invokevirtual 155	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   238: invokevirtual 158	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   241: invokestatic 164	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   244: pop
    //   245: goto -42 -> 203
    //   248: aload_1
    //   249: astore 4
    //   251: aload 4
    //   253: areturn
    //   254: astore 4
    //   256: goto -39 -> 217
    //   259: astore_3
    //   260: goto -50 -> 210
    //   263: goto -60 -> 203
    //   266: goto -72 -> 194
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	269	0	this	ExtensionInformations
    //   0	269	1	paramContext	android.content.Context
    //   157	10	2	i	int
    //   10	202	3	localObject1	Object
    //   259	1	3	localNameNotFoundException	PackageManager.NameNotFoundException
    //   12	196	4	localObject2	Object
    //   215	1	4	localThrowable1	Throwable
    //   249	3	4	localContext	android.content.Context
    //   254	1	4	localThrowable2	Throwable
    //   35	92	5	localObject3	Object
    //   15	219	6	str	String
    //   21	143	7	localPackageManager	PackageManager
    //   45	151	8	localExtensionInfo	ExtensionInfo
    // Exception table:
    //   from	to	target	type
    //   23	47	206	android/content/pm/PackageManager$NameNotFoundException
    //   58	66	206	android/content/pm/PackageManager$NameNotFoundException
    //   138	158	215	java/lang/Throwable
    //   162	173	215	java/lang/Throwable
    //   182	191	215	java/lang/Throwable
    //   194	203	254	java/lang/Throwable
    //   66	75	259	android/content/pm/PackageManager$NameNotFoundException
  }
  
  public boolean isAlwaysAnonymize()
  {
    return this.mAlwaysAnonymize;
  }
  
  public final ExtensionInfo searchExtensionForType(List<ExtensionInfo> paramList, String paramString)
  {
    if ((paramList != null) && (paramString != null))
    {
      paramList = paramList.iterator();
      while (paramList.hasNext())
      {
        ExtensionInfo localExtensionInfo = (ExtensionInfo)paramList.next();
        if ((localExtensionInfo != null) && (paramString.equals(localExtensionInfo.mimetype))) {
          return localExtensionInfo;
        }
      }
    }
    return null;
  }
  
  public void setAlwaysAnonymize(boolean paramBoolean)
  {
    this.mAlwaysAnonymize = paramBoolean;
  }
}
