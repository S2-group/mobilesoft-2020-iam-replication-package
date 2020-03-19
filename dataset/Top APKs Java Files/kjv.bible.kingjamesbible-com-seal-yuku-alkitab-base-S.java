package com.seal.yuku.alkitab.base;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.meevii.promotion.bean.AppModel;
import com.seal.base.App;
import com.seal.bibleread.model.Book;
import com.seal.bibleread.model.h;
import com.seal.utils.o;
import com.seal.yuku.alkitab.base.model.MVersion;
import com.seal.yuku.alkitab.base.model.MVersionDBP;
import com.seal.yuku.alkitab.base.model.MVersionDb;
import com.seal.yuku.alkitab.base.model.MVersionInternal;
import com.seal.yuku.alkitab.base.storage.Prefkey;
import com.seal.yuku.alkitab.base.storage.b;
import com.seal.yuku.alkitab.base.storage.c;
import com.seal.yuku.alkitab.base.storage.f;
import com.seal.yuku.alkitab.base.storage.g;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class S
  implements Serializable
{
  private static b a;
  public static int activeChapter;
  public static h activeVersion;
  public static String activeVersionId = ;
  private static f b;
  public static Book mActiveBook;
  
  public S() {}
  
  private static boolean a()
  {
    Iterator localIterator = App.b.getPackageManager().getInstalledPackages(0).iterator();
    while (localIterator.hasNext()) {
      if (((PackageInfo)localIterator.next()).packageName.equals("bibleverses.bibleverse.bible.biblia.verse.devotion")) {
        return true;
      }
    }
    return false;
  }
  
  public static void calculateAppliedValuesBasedOnPreferences()
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.copyTypes(TypeTransformer.java:311)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.fixTypes(TypeTransformer.java:226)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:207)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
  }
  
  public static List<MVersion> getAvailableVersions()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(getMVersionInternal());
    Iterator localIterator = getDb().b().iterator();
    Object localObject;
    while (localIterator.hasNext())
    {
      localObject = (MVersionDb)localIterator.next();
      if ((((MVersionDb)localObject).hasDataFile()) && (((MVersionDb)localObject).getActive())) {
        localArrayList.add(localObject);
      }
    }
    localIterator = getDb().c().iterator();
    while (localIterator.hasNext())
    {
      localObject = (MVersionDBP)localIterator.next();
      if (((MVersionDBP)localObject).getActive()) {
        localArrayList.add(localObject);
      }
    }
    Collections.sort(localArrayList, a.a);
    return localArrayList;
  }
  
  public static b getDb()
  {
    try
    {
      if (a == null) {
        a = new b(new c(App.b));
      }
      b localB = a;
      return localB;
    }
    finally {}
  }
  
  public static MVersionInternal getMVersionInternal()
  {
    com.seal.yuku.alkitab.base.b.a localA = com.seal.yuku.alkitab.base.b.a.a();
    MVersionInternal localMVersionInternal = new MVersionInternal();
    localMVersionInternal.locale = localA.e;
    localMVersionInternal.shortName = localA.f;
    localMVersionInternal.longName = localA.g;
    localMVersionInternal.description = null;
    localMVersionInternal.ordering = com.seal.storage.a.a(Prefkey.internal_version_ordering, 1);
    return localMVersionInternal;
  }
  
  public static f getSongDb()
  {
    try
    {
      if (b == null) {
        b = new f(new g());
      }
      f localF = b;
      return localF;
    }
    finally {}
  }
  
  public static String getVersionInitials(h paramH)
  {
    Object localObject = paramH.a();
    if (localObject != null) {
      return localObject;
    }
    paramH = paramH.b();
    if (paramH.length() <= 6) {
      return paramH.toUpperCase();
    }
    paramH = paramH.split("[^A-Za-z0-9]+");
    localObject = new char[paramH.length];
    int i = 0;
    int k;
    for (int j = 0; i < localObject.length; j = k)
    {
      k = j;
      if (paramH[i].length() > 0)
      {
        localObject[j] = Character.toUpperCase(paramH[i].charAt(0));
        k = j + 1;
      }
      i += 1;
    }
    return new String((char[])localObject, 0, j);
  }
  
  public static void goToPromoter(Activity paramActivity, AppModel paramAppModel, String paramString)
  {
    if (paramAppModel == null) {
      return;
    }
    o.a(paramActivity, paramAppModel.url, true);
    com.meevii.c.a.a.b("promoter_ad_click", paramString, paramAppModel.packageName);
  }
  
  public static void openVersionsDialog(Activity paramActivity, boolean paramBoolean, String paramString1, String paramString2, S.a paramA)
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.copyTypes(TypeTransformer.java:311)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.fixTypes(TypeTransformer.java:226)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:207)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
  }
}
