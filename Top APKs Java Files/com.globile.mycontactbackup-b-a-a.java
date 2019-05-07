package b.a;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class a
{
  public static LinearLayout a(Context paramContext, View.OnClickListener paramOnClickListener)
  {
    Typeface localTypeface = Typeface.createFromAsset(paramContext.getAssets(), "fonts/noteworthy_bold.otf");
    Object localObject1 = paramContext.getPackageManager().getInstalledApplications(0).iterator();
    do
    {
      if (!((Iterator)localObject1).hasNext()) {
        break;
      }
    } while (!((ApplicationInfo)((Iterator)localObject1).next()).packageName.equals("com.amazon.mShop.android"));
    for (int i = 1;; i = 0)
    {
      localObject2 = paramContext.getResources().getStringArray(g.appNames);
      localObject3 = paramContext.getResources().getStringArray(g.appUrls);
      localObject4 = paramContext.getResources().getStringArray(g.appMarkets);
      localObject5 = paramContext.getResources().obtainTypedArray(g.appLogos);
      localObject1 = new ArrayList();
      int j = 0;
      while (j < localObject3.length)
      {
        if ((!paramContext.getPackageName().equalsIgnoreCase(localObject3[j])) && ((localObject4[j].equalsIgnoreCase("googleplay")) || ((localObject4[j].equalsIgnoreCase("amazon")) && (i != 0)))) {
          ((ArrayList)localObject1).add(new c(((TypedArray)localObject5).getResourceId(j, -1), localObject2[j], localObject3[j], localObject4[j]));
        }
        j += 1;
      }
    }
    Object localObject4 = LayoutInflater.from(paramContext);
    Object localObject2 = (LinearLayout)((LayoutInflater)localObject4).inflate(i.other_apps, null, false);
    Object localObject3 = (ListView)((LinearLayout)localObject2).findViewById(h.listViewOurOtherApps);
    localObject4 = (RelativeLayout)((LayoutInflater)localObject4).inflate(i.list_header_our_other_apps, null, false);
    Object localObject5 = (TextView)((RelativeLayout)localObject4).findViewById(h.textviewOtherAppsHeader);
    ((ImageButton)((RelativeLayout)localObject4).findViewById(h.imageButtonCloseHeader)).setOnClickListener(paramOnClickListener);
    ((TextView)localObject5).setTypeface(localTypeface);
    ((ListView)localObject3).addHeaderView((View)localObject4);
    ((ListView)localObject3).setAdapter(new d(paramContext, (ArrayList)localObject1));
    ((ListView)localObject3).setOnItemClickListener(new b((ArrayList)localObject1, paramContext));
    return localObject2;
  }
}
