package sk.martinflorek.wear.feelthewear.model.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import java.lang.ref.WeakReference;
import java.text.Collator;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import sk.martinflorek.wear.feelthewear.al;
import sk.martinflorek.wear.feelthewear.am;
import sk.martinflorek.wear.feelthewear.model.dtos.AppVibratePattern;

public final class j
  implements i
{
  final WeakReference a;
  final c b;
  
  public j(Context paramContext, c paramC)
  {
    this.a = new WeakReference(paramContext);
    this.b = paramC;
  }
  
  public final List a(boolean paramBoolean)
  {
    LinkedList localLinkedList = new LinkedList();
    Context localContext = (Context)this.a.get();
    if (localContext == null) {
      return localLinkedList;
    }
    Object localObject1 = localContext.getPackageManager();
    Object localObject2 = ((PackageManager)localObject1).getInstalledApplications(128);
    SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(localContext);
    localObject2 = ((List)localObject2).iterator();
    while (((Iterator)localObject2).hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject2).next();
      try
      {
        AppVibratePattern localAppVibratePattern = new AppVibratePattern();
        localAppVibratePattern.appName = localApplicationInfo.loadLabel((PackageManager)localObject1).toString();
        localAppVibratePattern.packageName = localApplicationInfo.packageName;
        localAppVibratePattern.id = al.a(localAppVibratePattern.packageName);
        if (!localAppVibratePattern.packageName.equals(localAppVibratePattern.appName))
        {
          this.b.a(localAppVibratePattern);
          localLinkedList.add(localAppVibratePattern);
        }
      }
      catch (Throwable localThrowable) {}
    }
    Collections.sort(localLinkedList, new k(this, Collator.getInstance(new Locale("pt"))));
    localObject1 = new AppVibratePattern();
    ((AppVibratePattern)localObject1).id = 2L;
    ((AppVibratePattern)localObject1).appName = localContext.getResources().getString(2131230987);
    ((AppVibratePattern)localObject1).packageName = "sk.martinflorek.ftw.all";
    ((AppVibratePattern)localObject1).vibratePatternName = localSharedPreferences.getString("pattern_name:" + ((AppVibratePattern)localObject1).packageName, null);
    ((AppVibratePattern)localObject1).vibratePatternNameSpannable = am.a(((AppVibratePattern)localObject1).vibratePatternName);
    ((AppVibratePattern)localObject1).vibratePatternString = localSharedPreferences.getString("pattern:" + ((AppVibratePattern)localObject1).packageName, null);
    ((AppVibratePattern)localObject1).vibratePattern = am.b(((AppVibratePattern)localObject1).vibratePatternString);
    localLinkedList.add(0, localObject1);
    localObject1 = new AppVibratePattern();
    ((AppVibratePattern)localObject1).id = 3L;
    ((AppVibratePattern)localObject1).appName = localContext.getResources().getString(2131230985);
    ((AppVibratePattern)localObject1).packageName = "sk.martinflorek.ftw.phonecall";
    ((AppVibratePattern)localObject1).vibratePatternName = localSharedPreferences.getString("pattern_name:" + ((AppVibratePattern)localObject1).packageName, null);
    ((AppVibratePattern)localObject1).vibratePatternNameSpannable = am.a(((AppVibratePattern)localObject1).vibratePatternName);
    ((AppVibratePattern)localObject1).vibratePatternString = localSharedPreferences.getString("pattern:" + ((AppVibratePattern)localObject1).packageName, null);
    ((AppVibratePattern)localObject1).vibratePattern = am.b(((AppVibratePattern)localObject1).vibratePatternString);
    localLinkedList.add(1, localObject1);
    if (paramBoolean)
    {
      localObject1 = new AppVibratePattern();
      ((AppVibratePattern)localObject1).id = 4L;
      ((AppVibratePattern)localObject1).appName = localContext.getResources().getString(2131230984);
      ((AppVibratePattern)localObject1).packageName = "sk.martinflorek.ftw.calendar.reminders";
      ((AppVibratePattern)localObject1).vibratePatternName = localSharedPreferences.getString("pattern_name:" + ((AppVibratePattern)localObject1).packageName, null);
      ((AppVibratePattern)localObject1).vibratePatternNameSpannable = am.a(((AppVibratePattern)localObject1).vibratePatternName);
      ((AppVibratePattern)localObject1).vibratePatternString = localSharedPreferences.getString("pattern:" + ((AppVibratePattern)localObject1).packageName, null);
      ((AppVibratePattern)localObject1).vibratePattern = am.b(((AppVibratePattern)localObject1).vibratePatternString);
      localLinkedList.add(2, localObject1);
    }
    return localLinkedList;
  }
}
