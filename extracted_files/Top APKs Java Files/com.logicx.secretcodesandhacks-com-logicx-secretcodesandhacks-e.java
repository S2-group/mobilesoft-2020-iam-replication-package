package com.logicx.secretcodesandhacks;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.content.res.XmlResourceParser;
import android.text.TextUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.xmlpull.v1.XmlPullParserException;

final class e
{
  private static String a(PackageInfo paramPackageInfo, PackageManager paramPackageManager, int paramInt1, int paramInt2, int paramInt3)
  {
    String str = paramPackageInfo.packageName;
    if (paramInt3 != 0) {
      paramInt1 = paramInt3;
    } else if (paramInt2 != 0) {
      paramInt1 = paramInt2;
    }
    return String.valueOf(paramPackageManager.getText(str, paramInt1, paramPackageInfo.applicationInfo));
  }
  
  public static List<b> a(d paramD)
  {
    Context localContext = paramD.i();
    PackageManager localPackageManager = paramD.o;
    HashSet localHashSet = new HashSet();
    ArrayList localArrayList1 = new ArrayList();
    Object localObject = localPackageManager.getInstalledPackages(32);
    if (localObject == null) {
      return localArrayList1;
    }
    long l1 = ((List)localObject).size();
    long l2 = 0L;
    Iterator localIterator = ((List)localObject).iterator();
    int i = 0;
    int m = 0;
    int j;
    if (localIterator.hasNext())
    {
      localObject = (PackageInfo)localIterator.next();
      if (paramD.g()) {
        return localArrayList1;
      }
      l2 += 1L;
      j = (int)(100L * l2 / l1);
      if (i == j) {
        j = i;
      }
    }
    for (;;)
    {
      int k;
      try
      {
        XmlResourceParser localXmlResourceParser = localContext.createPackageContext(((PackageInfo)localObject).packageName, 0).getAssets().openXmlResourceParser("AndroidManifest.xml");
        ArrayList localArrayList2 = new ArrayList();
        k = 0;
        int i1 = 0;
        try
        {
          i = localXmlResourceParser.next();
          if (i != 1) {
            try
            {
              if (paramD.g()) {
                return localArrayList1;
              }
              if ((localXmlResourceParser.getEventType() == 3) && (!localArrayList2.isEmpty())) {
                localArrayList2.remove(localArrayList2.size() - 1);
              }
              n = k;
              i = m;
              int i2 = i1;
              String str;
              if (localXmlResourceParser.getEventType() == 2)
              {
                str = localXmlResourceParser.getName();
                localArrayList2.add(str);
              }
              switch (str.hashCode())
              {
              case 1984153269: 
                if (!str.equals("service")) {
                  break;
                }
                i = 3;
                break;
              case 1554253136: 
                if (!str.equals("application")) {
                  break;
                }
                i = 0;
                break;
              case 790287890: 
                if (!str.equals("activity-alias")) {
                  break;
                }
                i = 2;
                break;
              case 3076010: 
                if (!str.equals("data")) {
                  break;
                }
                i = 6;
                break;
              case -808719889: 
                if (!str.equals("receiver")) {
                  break;
                }
                i = 4;
                break;
              case -1029793847: 
                if (!str.equals("intent-filter")) {
                  break;
                }
                i = 5;
                break;
              case -1655966961: 
                if (!str.equals("activity")) {
                  break;
                }
                i = 1;
                break label668;
                n = k;
                i = m;
                if ("android_secret_code".equals(localXmlResourceParser.getAttributeValue("http://schemas.android.com/apk/res/android", "scheme")))
                {
                  str = localXmlResourceParser.getAttributeValue("http://schemas.android.com/apk/res/android", "host");
                  n = k;
                  i = m;
                  if (!TextUtils.isEmpty(str))
                  {
                    n = k;
                    i = m;
                    if (!localHashSet.contains(str))
                    {
                      localHashSet.add(str);
                      localArrayList1.add(new b(str, a((PackageInfo)localObject, localPackageManager, i1, m, k)));
                      n = k;
                      i = m;
                      continue;
                      n = localXmlResourceParser.getAttributeResourceValue("http://schemas.android.com/apk/res/android", "label", 0);
                      i = m;
                    }
                  }
                }
                try
                {
                  i = localXmlResourceParser.getAttributeResourceValue("http://schemas.android.com/apk/res/android", "label", 0);
                  n = k;
                  i2 = i1;
                }
                catch (PackageManager.NameNotFoundException|IOException|XmlPullParserException localNameNotFoundException1)
                {
                  break label653;
                }
                i2 = localXmlResourceParser.getAttributeResourceValue("http://schemas.android.com/apk/res/android", "label", 0);
                n = k;
                i = m;
                k = n;
                m = i;
                i1 = i2;
              }
            }
            catch (PackageManager.NameNotFoundException|IOException|XmlPullParserException localNameNotFoundException2) {}
          }
        }
        catch (PackageManager.NameNotFoundException|IOException|XmlPullParserException localNameNotFoundException3) {}
        localNameNotFoundException4.printStackTrace();
      }
      catch (PackageManager.NameNotFoundException|IOException|XmlPullParserException localNameNotFoundException4) {}
      label653:
      i = j;
      break;
      return localArrayList1;
      i = -1;
      label668:
      switch (i)
      {
      }
      int n = k;
      i = m;
    }
  }
}
