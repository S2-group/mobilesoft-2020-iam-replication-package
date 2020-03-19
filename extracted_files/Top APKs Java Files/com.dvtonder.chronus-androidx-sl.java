package androidx;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.TypeCastException;

public final class sl
{
  public static final b aCA = new b(null);
  private ax aCv;
  private final PackageManager aCw;
  private int aCx;
  private final FragmentActivity aCy;
  private final c aCz;
  
  public sl(FragmentActivity paramFragmentActivity, c paramC)
  {
    this.aCy = paramFragmentActivity;
    this.aCz = paramC;
    paramFragmentActivity = this.aCy.getPackageManager();
    ebu.g(paramFragmentActivity, "parentActivity.packageManager");
    this.aCw = paramFragmentActivity;
  }
  
  private final String a(Intent paramIntent, boolean paramBoolean)
  {
    Object localObject = paramIntent.resolveActivityInfo(this.aCw, 1);
    String str = (String)null;
    if (localObject != null)
    {
      str = ((ActivityInfo)localObject).loadLabel(this.aCw).toString();
      if (!paramBoolean) {
        str = ((ActivityInfo)localObject).name;
      }
    }
    localObject = str;
    if (str == null)
    {
      if (paramBoolean) {
        return str;
      }
      localObject = paramIntent.toUri(0);
    }
    return localObject;
  }
  
  private final void a(Intent paramIntent, int paramInt)
  {
    try
    {
      if (this.aCx == 0)
      {
        this.aCy.startActivityForResult(paramIntent, paramInt);
        return;
      }
      Fragment localFragment = this.aCy.getSupportFragmentManager().findFragmentById(this.aCx);
      if (localFragment != null)
      {
        this.aCy.startActivityFromFragment(localFragment, paramIntent, paramInt);
        return;
      }
    }
    catch (SecurityException paramIntent)
    {
      Log.w("ShortcutPickHelper", "Security Exception attempting to start activity", (Throwable)paramIntent);
    }
  }
  
  private final void a(Intent paramIntent, int paramInt1, int paramInt2)
  {
    Object localObject = this.aCy.getString(2131952414);
    String str1 = this.aCy.getString(2131952413);
    String str2 = paramIntent.getStringExtra("android.intent.extra.shortcut.NAME");
    if (ebu.V(localObject, str2))
    {
      paramIntent = new Intent("android.intent.action.MAIN", null);
      paramIntent.addCategory("android.intent.category.LAUNCHER");
      localObject = new Intent("android.intent.action.PICK_ACTIVITY");
      ((Intent)localObject).putExtra("android.intent.extra.INTENT", (Parcelable)paramIntent);
      a((Intent)localObject, paramInt1);
      return;
    }
    if (ebu.V(str1, str2))
    {
      localObject = this.aCw.getInstalledPackages(1);
      paramIntent = new ExpandableListView((Context)this.aCy);
      ebu.g(localObject, "pInfos");
      paramIntent.setAdapter((ExpandableListAdapter)new a((List)localObject, (Context)this.aCy));
      paramIntent.setOnChildClickListener((ExpandableListView.OnChildClickListener)new d(this));
      localObject = new ax.a((Context)this.aCy);
      ((ax.a)localObject).e((View)paramIntent);
      this.aCv = ((ax.a)localObject).bH();
      paramIntent = this.aCv;
      if (paramIntent == null) {
        ebu.alO();
      }
      paramIntent.setTitle((CharSequence)this.aCy.getString(2131952416));
      paramIntent = this.aCv;
      if (paramIntent == null) {
        ebu.alO();
      }
      paramIntent.show();
      paramIntent = this.aCv;
      if (paramIntent == null) {
        ebu.alO();
      }
      paramIntent.setOnCancelListener((DialogInterface.OnCancelListener)new e(this));
      return;
    }
    a(paramIntent, paramInt2);
  }
  
  private final void k(Intent paramIntent)
  {
    this.aCz.a(paramIntent.toUri(0), a(paramIntent, false), true);
  }
  
  private final void l(Intent paramIntent)
  {
    Intent localIntent = (Intent)paramIntent.getParcelableExtra("android.intent.extra.shortcut.INTENT");
    if (localIntent != null)
    {
      localIntent.putExtra("android.intent.extra.shortcut.NAME", paramIntent.getStringExtra("android.intent.extra.shortcut.NAME"));
      paramIntent = localIntent.toUri(0);
      ebu.g(paramIntent, "appUri");
      paramIntent = (CharSequence)paramIntent;
      paramIntent = new ecy("com.android.contacts.action.QUICK_CONTACT").a(paramIntent, "android.intent.action.VIEW");
      this.aCz.a(paramIntent, m(localIntent), false);
    }
  }
  
  private final String m(Intent paramIntent)
  {
    String str1 = a(paramIntent, true);
    String str2 = paramIntent.getStringExtra("android.intent.extra.shortcut.NAME");
    if ((str1 != null) && (str2 != null))
    {
      paramIntent = new StringBuilder();
      paramIntent.append(str1);
      paramIntent.append(": ");
      paramIntent.append(str2);
      return paramIntent.toString();
    }
    if (str2 != null) {
      return str2;
    }
    paramIntent = paramIntent.toUri(0);
    ebu.g(paramIntent, "intent.toUri(0)");
    return paramIntent;
  }
  
  public final void a(String[] paramArrayOfString, Intent.ShortcutIconResource[] paramArrayOfShortcutIconResource, int paramInt)
  {
    Bundle localBundle = new Bundle();
    if (ro.sk())
    {
      Toast.makeText((Context)this.aCy, 2131952676, 1).show();
      Log.e("ShortcutPickHelper", "The shortcut picker is not supported on Huawei/EMUI devices");
      return;
    }
    ArrayList localArrayList = new ArrayList();
    if (paramArrayOfString != null) {
      Collections.addAll((Collection)localArrayList, (String[])Arrays.copyOf(paramArrayOfString, paramArrayOfString.length));
    }
    localArrayList.add(this.aCy.getString(2131952414));
    localArrayList.add(this.aCy.getString(2131952413));
    localBundle.putStringArrayList("android.intent.extra.shortcut.NAME", localArrayList);
    paramArrayOfString = new ArrayList();
    if (paramArrayOfShortcutIconResource != null) {
      Collections.addAll((Collection)paramArrayOfString, (Intent.ShortcutIconResource[])Arrays.copyOf(paramArrayOfShortcutIconResource, paramArrayOfShortcutIconResource.length));
    }
    paramArrayOfString.add(Intent.ShortcutIconResource.fromContext((Context)this.aCy, 17301651));
    paramArrayOfString.add(Intent.ShortcutIconResource.fromContext((Context)this.aCy, 2131230814));
    localBundle.putParcelableArrayList("android.intent.extra.shortcut.ICON_RESOURCE", paramArrayOfString);
    paramArrayOfString = new Intent("android.intent.action.PICK_ACTIVITY");
    paramArrayOfString.putExtra("android.intent.extra.INTENT", (Parcelable)new Intent("android.intent.action.CREATE_SHORTCUT"));
    paramArrayOfString.putExtra("android.intent.extra.TITLE", this.aCy.getText(2131952417));
    paramArrayOfString.putExtras(localBundle);
    this.aCx = paramInt;
    try
    {
      if (ro.sh())
      {
        paramArrayOfString = Intent.createChooser(paramArrayOfString, this.aCy.getText(2131952417));
        ebu.g(paramArrayOfString, "Intent.createChooser(pic…select_custom_app_title))");
        a(paramArrayOfString, 100);
        return;
      }
      a(paramArrayOfString, 100);
      return;
    }
    catch (ActivityNotFoundException paramArrayOfString)
    {
      Log.e("ShortcutPickHelper", "Could not find the shortcut picker activity:", (Throwable)paramArrayOfString);
      return;
    }
    catch (SecurityException paramArrayOfString)
    {
      Log.e("ShortcutPickHelper", "Security exception opening the shortcut picker:", (Throwable)paramArrayOfString);
    }
  }
  
  public final String bl(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    try
    {
      Object localObject = Intent.parseUri(paramString, 0);
      ebu.g(localObject, "intent");
      if (ebu.V("android.intent.action.MAIN", ((Intent)localObject).getAction())) {
        return a((Intent)localObject, false);
      }
      localObject = m((Intent)localObject);
      return localObject;
    }
    catch (URISyntaxException localURISyntaxException) {}
    return paramString;
  }
  
  public final void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if ((paramInt2 == -1) && (paramIntent != null))
    {
      switch (paramInt1)
      {
      default: 
        return;
      case 102: 
        l(paramIntent);
        return;
      case 101: 
        k(paramIntent);
        return;
      }
      a(paramIntent, 101, 102);
    }
  }
  
  public final class a
    extends BaseExpandableListAdapter
  {
    private final ArrayList<a> aCB = new ArrayList();
    private final int aCC;
    
    public a(Context paramContext)
    {
      paramContext = paramContext.iterator();
      while (paramContext.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
        this.aCB.add(new a(localPackageInfo.applicationInfo.loadLabel(sl.b(sl.this)).toString(), localPackageInfo));
      }
      Collections.sort((List)this.aCB, (Comparator)new b());
      this.aCC = localObject.getResources().getDimensionPixelSize(2131165627);
    }
    
    public String bg(int paramInt1, int paramInt2)
    {
      String str = ((a)this.aCB.get(paramInt1)).wN().activities[paramInt2].name;
      ebu.g(str, "allList[groupPosition].i…ities[childPosition].name");
      return str;
    }
    
    public a fD(int paramInt)
    {
      Object localObject = this.aCB.get(paramInt);
      ebu.g(localObject, "allList[groupPosition]");
      return (a)localObject;
    }
    
    public long getChildId(int paramInt1, int paramInt2)
    {
      return paramInt2;
    }
    
    public View getChildView(int paramInt1, int paramInt2, boolean paramBoolean, View paramView, ViewGroup paramViewGroup)
    {
      ebu.h(paramViewGroup, "parent");
      paramViewGroup = paramView;
      if (paramView == null)
      {
        paramViewGroup = View.inflate((Context)sl.a(sl.this), 17367043, null);
        if (paramViewGroup == null) {
          ebu.alO();
        }
        paramViewGroup.setPadding(this.aCC, 0, 0, 0);
      }
      paramView = (TextView)paramViewGroup.findViewById(16908308);
      ebu.g(paramView, "textView");
      CharSequence localCharSequence = (CharSequence)bg(paramInt1, paramInt2);
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(((a)this.aCB.get(paramInt1)).wN().packageName);
      localStringBuilder.append(".");
      paramView.setText((CharSequence)new ecy(localStringBuilder.toString()).b(localCharSequence, ""));
      return paramViewGroup;
    }
    
    public int getChildrenCount(int paramInt)
    {
      if (((a)this.aCB.get(paramInt)).wN().activities != null) {
        return ((a)this.aCB.get(paramInt)).wN().activities.length;
      }
      return 0;
    }
    
    public int getGroupCount()
    {
      return this.aCB.size();
    }
    
    public long getGroupId(int paramInt)
    {
      return paramInt;
    }
    
    public View getGroupView(int paramInt, boolean paramBoolean, View paramView, ViewGroup paramViewGroup)
    {
      ebu.h(paramViewGroup, "parent");
      paramViewGroup = paramView;
      if (paramView == null)
      {
        paramViewGroup = View.inflate((Context)sl.a(sl.this), 17367043, null);
        if (paramViewGroup == null) {
          ebu.alO();
        }
        paramViewGroup.setPadding(70, 0, 0, 0);
      }
      paramView = (TextView)paramViewGroup.findViewById(16908308);
      ebu.g(paramView, "textView");
      paramView.setText((CharSequence)fD(paramInt).getLabel());
      return paramViewGroup;
    }
    
    public boolean hasStableIds()
    {
      return true;
    }
    
    public boolean isChildSelectable(int paramInt1, int paramInt2)
    {
      return true;
    }
    
    public final class a
    {
      private final PackageInfo aCE;
      private final String label;
      
      public a(PackageInfo paramPackageInfo)
      {
        this.label = paramPackageInfo;
        this.aCE = localObject;
      }
      
      public final String getLabel()
      {
        return this.label;
      }
      
      public final PackageInfo wN()
      {
        return this.aCE;
      }
    }
    
    public final class b
      implements Comparator<sl.a.a>
    {
      public b() {}
      
      public int a(sl.a.a paramA1, sl.a.a paramA2)
      {
        ebu.h(paramA1, "item1");
        ebu.h(paramA2, "item2");
        paramA1 = paramA1.getLabel();
        Locale localLocale = Locale.getDefault();
        ebu.g(localLocale, "Locale.getDefault()");
        if (paramA1 != null)
        {
          paramA1 = paramA1.toLowerCase(localLocale);
          ebu.g(paramA1, "(this as java.lang.String).toLowerCase(locale)");
          paramA2 = paramA2.getLabel();
          localLocale = Locale.getDefault();
          ebu.g(localLocale, "Locale.getDefault()");
          if (paramA2 != null)
          {
            paramA2 = paramA2.toLowerCase(localLocale);
            ebu.g(paramA2, "(this as java.lang.String).toLowerCase(locale)");
            return Integer.compare(paramA1.compareTo(paramA2), 0);
          }
          throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
      }
    }
  }
  
  public static final class b
  {
    private b() {}
  }
  
  public static abstract interface c
  {
    public abstract void a(String paramString1, String paramString2, boolean paramBoolean);
  }
  
  static final class d
    implements ExpandableListView.OnChildClickListener
  {
    d(sl paramSl) {}
    
    public final boolean onChildClick(ExpandableListView paramExpandableListView, View paramView, int paramInt1, int paramInt2, long paramLong)
    {
      paramView = new Intent("android.intent.action.MAIN");
      ebu.g(paramExpandableListView, "parent");
      Object localObject = paramExpandableListView.getExpandableListAdapter().getGroup(paramInt1);
      if (localObject != null)
      {
        localObject = ((sl.a.a)localObject).wN().packageName;
        paramExpandableListView = paramExpandableListView.getExpandableListAdapter().getGroup(paramInt1);
        if (paramExpandableListView != null)
        {
          paramView.setClassName((String)localObject, ((sl.a.a)paramExpandableListView).wN().activities[paramInt2].name);
          sl.a(this.aCD, paramView);
          paramExpandableListView = sl.c(this.aCD);
          if (paramExpandableListView == null) {
            ebu.alO();
          }
          paramExpandableListView.dismiss();
          return true;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.dvtonder.chronus.preference.ShortcutPickHelper.AppExpandableAdapter.GroupInfo");
      }
      throw new TypeCastException("null cannot be cast to non-null type com.dvtonder.chronus.preference.ShortcutPickHelper.AppExpandableAdapter.GroupInfo");
    }
  }
  
  static final class e
    implements DialogInterface.OnCancelListener
  {
    e(sl paramSl) {}
    
    public final void onCancel(DialogInterface paramDialogInterface)
    {
      sl.d(this.aCD).a(null, null, false);
    }
  }
}
