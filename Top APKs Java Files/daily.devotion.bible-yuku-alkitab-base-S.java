package yuku.alkitab.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.seal.base.App;
import com.seal.bibleread.model.Version;
import com.seal.storage.Preferences;
import com.seal.utils.Utils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import yuku.alkitab.base.config.AppConfig;
import yuku.alkitab.base.model.MVersion;
import yuku.alkitab.base.model.MVersionDBP;
import yuku.alkitab.base.model.MVersionDb;
import yuku.alkitab.base.model.MVersionInternal;
import yuku.alkitab.base.storage.InternalDb;
import yuku.alkitab.base.storage.InternalDbHelper;
import yuku.alkitab.base.storage.Prefkey;

public class S
{
  private static InternalDb db;
  
  public static void calculateAppliedValuesBasedOnPreferences()
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.copyTypes(TypeTransformer.java:311)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.fixTypes(TypeTransformer.java:226)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:207)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
  }
  
  private static boolean checkOnlineVersion(String paramString)
  {
    Iterator localIterator = App.sContext.getPackageManager().getInstalledPackages(0).iterator();
    while (localIterator.hasNext()) {
      if (((PackageInfo)localIterator.next()).packageName.equals(paramString)) {
        return true;
      }
    }
    return false;
  }
  
  public static List<MVersion> getAvailableVersions()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(getMVersionInternal());
    Iterator localIterator = getDb().listAllVersions().iterator();
    Object localObject;
    while (localIterator.hasNext())
    {
      localObject = (MVersionDb)localIterator.next();
      if ((((MVersionDb)localObject).hasDataFile()) && (((MVersionDb)localObject).getActive())) {
        localArrayList.add(localObject);
      }
    }
    localIterator = getDb().listVersionsFromDBP().iterator();
    while (localIterator.hasNext())
    {
      localObject = (MVersionDBP)localIterator.next();
      if (((MVersionDBP)localObject).getActive()) {
        localArrayList.add(localObject);
      }
    }
    Collections.sort(localArrayList, S..Lambda.0.$instance);
    return localArrayList;
  }
  
  public static InternalDb getDb()
  {
    try
    {
      if (db == null) {
        db = new InternalDb(new InternalDbHelper(App.sContext));
      }
      InternalDb localInternalDb = db;
      return localInternalDb;
    }
    finally {}
  }
  
  public static MVersionInternal getMVersionInternal()
  {
    AppConfig localAppConfig = AppConfig.get();
    MVersionInternal localMVersionInternal = new MVersionInternal();
    localMVersionInternal.locale = localAppConfig.internalLocale;
    localMVersionInternal.shortName = localAppConfig.internalShortName;
    localMVersionInternal.longName = localAppConfig.internalLongName;
    localMVersionInternal.description = null;
    localMVersionInternal.ordering = Preferences.getInt(Prefkey.internal_version_ordering, 1);
    return localMVersionInternal;
  }
  
  public static String getVersionInitials(Version paramVersion)
  {
    Object localObject = paramVersion.getShortName();
    if (localObject != null) {
      return localObject;
    }
    paramVersion = paramVersion.getLongName();
    if (paramVersion.length() <= 6) {
      return paramVersion.toUpperCase();
    }
    paramVersion = paramVersion.split("[^A-Za-z0-9]+");
    localObject = new char[paramVersion.length];
    int i = 0;
    int k;
    for (int j = 0; i < localObject.length; j = k)
    {
      k = j;
      if (paramVersion[i].length() > 0)
      {
        localObject[j] = Character.toUpperCase(paramVersion[i].charAt(0));
        k = j + 1;
      }
      i += 1;
    }
    return new String((char[])localObject, 0, j);
  }
  
  public static void goOtherVersion(Activity paramActivity, String paramString1, String paramString2)
  {
    if (TextUtils.isEmpty(paramString1)) {
      return;
    }
    if (!checkOnlineVersion(paramString1))
    {
      Utils.searchMarket(paramActivity, paramString2, true);
      return;
    }
    paramString1 = paramActivity.getPackageManager().getLaunchIntentForPackage(paramString1);
    paramString1.setAction("android.intent.action.VIEW");
    paramString1.setFlags(268435456);
    paramActivity.startActivity(paramString1);
  }
  
  public static void openVersionsDialog(Activity paramActivity, boolean paramBoolean, String paramString, S.VersionDialogListener paramVersionDialogListener)
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.copyTypes(TypeTransformer.java:311)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.fixTypes(TypeTransformer.java:226)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:207)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
  }
}
