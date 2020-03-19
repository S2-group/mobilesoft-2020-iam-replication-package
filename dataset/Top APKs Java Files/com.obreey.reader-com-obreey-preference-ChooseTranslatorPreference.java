package com.obreey.preference;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.TypedArray;
import android.net.Uri;
import android.net.Uri.Builder;
import android.preference.DialogPreference;
import android.text.Html;
import android.util.AttributeSet;
import com.obreey.books.GlobalUtils;
import com.obreey.books.Log;
import com.obreey.bookviewer.OptionsActivity;
import com.obreey.bookviewer.ReaderContext;
import com.obreey.bookviewer.lib.JniWrapper;
import com.obreey.bookviewer.lib.ReaderProperty;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class ChooseTranslatorPreference
  extends DialogPreference
  implements ReaderPreference
{
  private static final String TAG = "PBRD PROPS";
  private String dfltValue = "";
  private String pValue = "";
  ReaderProperty rprop;
  private String summary_format;
  private boolean summary_is_html;
  
  public ChooseTranslatorPreference(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(ReaderContext.getJniWrapper().getProperty(getKey()));
  }
  
  public ChooseTranslatorPreference(Context paramContext, ReaderProperty paramReaderProperty)
  {
    super(paramContext, null);
    init(paramReaderProperty);
  }
  
  private void appendPredefinedItem(String paramString1, String paramString2, PickAppsAdapter paramPickAppsAdapter, List<ApplicationInfo> paramList, boolean paramBoolean)
  {
    if (isInstalled(paramString1, paramList))
    {
      if (paramString1 == "com.abbyy.mobile.lingvo.market")
      {
        paramList = new Intent("com.abbyy.mobile.lingvo.intent.action.TRANSLATE");
        paramList.setPackage(paramString1);
        paramPickAppsAdapter.appendItem(paramList, paramString1, paramString2);
        return;
      }
      paramString2 = new Intent("android.intent.action.SEND");
      paramString2.setType("text/plain");
      paramString2.setPackage(paramString1);
      if (GlobalUtils.isAppAvailable(paramString2))
      {
        paramPickAppsAdapter.appendItem(paramString2, paramString1);
        return;
      }
      paramString2 = new Intent("android.intent.action.SEARCH");
      paramString2.setPackage(paramString1);
      if (GlobalUtils.isAppAvailable(paramString2)) {
        paramPickAppsAdapter.appendItem(paramString2, paramString1);
      }
    }
    else
    {
      Intent localIntent = new Intent("android.intent.action.VIEW");
      if (paramBoolean) {
        paramList = "market://details";
      } else {
        paramList = "http://play.google.com/store/apps/details";
      }
      localIntent.setData(Uri.parse(paramList).buildUpon().appendQueryParameter("id", paramString1).build());
      paramList = new StringBuilder();
      paramList.append(paramString2);
      paramList.append(" (install)");
      paramPickAppsAdapter.appendItem(localIntent, paramString1, paramList.toString());
    }
  }
  
  private int findIndexOfValue(String paramString, PickAppsAdapter paramPickAppsAdapter)
  {
    int i = 0;
    while (i < paramPickAppsAdapter.getCount())
    {
      if (paramPickAppsAdapter.getItem(i).intent.equals(paramString)) {
        return i;
      }
      i += 1;
    }
    return -1;
  }
  
  private CharSequence formatSummary()
  {
    Object localObject2;
    if ((this.summary_format != null) && (this.pValue != null) && (this.pValue.length() != 0)) {
      localObject2 = null;
    }
    try
    {
      localObject1 = Intent.parseUri(this.pValue, 1);
      localObject2 = localObject1;
    }
    catch (URISyntaxException localURISyntaxException)
    {
      Object localObject1;
      Object localObject3;
      for (;;) {}
    }
    if (localObject2 == null) {
      return "";
    }
    localObject1 = getContext().getPackageManager();
    localObject3 = ((PackageManager)localObject1).queryIntentActivities((Intent)localObject2, 0);
    if (((List)localObject3).size() > 0)
    {
      localObject3 = (ResolveInfo)((List)localObject3).get(0);
      localObject2 = ((ResolveInfo)localObject3).loadLabel((PackageManager)localObject1);
      localObject1 = localObject2;
      if (localObject2 == null)
      {
        localObject1 = localObject2;
        if (((ResolveInfo)localObject3).activityInfo != null) {
          localObject1 = ((ResolveInfo)localObject3).activityInfo.name;
        }
      }
    }
    else
    {
      localObject1 = ((Intent)localObject2).getAction();
      localObject3 = new StringBuilder();
      ((StringBuilder)localObject3).append("action '");
      ((StringBuilder)localObject3).append((String)localObject1);
      ((StringBuilder)localObject3).append("' is not available");
      localObject1 = ((StringBuilder)localObject3).toString();
      if (((Intent)localObject2).getComponent() != null)
      {
        localObject1 = ((Intent)localObject2).getComponent().getPackageName();
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append("Application '");
        ((StringBuilder)localObject2).append((String)localObject1);
        ((StringBuilder)localObject2).append("' is not installed");
        localObject1 = ((StringBuilder)localObject2).toString();
      }
    }
    if (this.summary_is_html) {
      return Html.fromHtml(String.format(Locale.getDefault(), this.summary_format, new Object[] { GlobalUtils.escapeHtml((CharSequence)localObject1), GlobalUtils.escapeHtml(this.dfltValue) }));
    }
    return String.format(Locale.getDefault(), this.summary_format, new Object[] { localObject1, this.dfltValue });
    return "";
  }
  
  private void init(ReaderProperty paramReaderProperty)
  {
    this.rprop = paramReaderProperty;
    if (paramReaderProperty == null) {
      return;
    }
    if ((paramReaderProperty.isAdvanced()) && (!((OptionsActivity)getContext()).isAdvancedEnabled())) {
      return;
    }
    setPositiveButtonText(17039370);
    setNegativeButtonText(17039360);
    setKey(paramReaderProperty.name());
    setTitle(GlobalUtils.getTranslation(paramReaderProperty.name()));
    updateValue();
    paramReaderProperty = new StringBuilder();
    paramReaderProperty.append(getKey());
    paramReaderProperty.append("!summary-html");
    paramReaderProperty = GlobalUtils.getTranslationRaw(paramReaderProperty.toString());
    if (paramReaderProperty != null)
    {
      this.summary_is_html = true;
      this.summary_format = paramReaderProperty;
    }
    else
    {
      paramReaderProperty = new StringBuilder();
      paramReaderProperty.append(getKey());
      paramReaderProperty.append("!summary");
      this.summary_format = GlobalUtils.getTranslationRaw(paramReaderProperty.toString());
    }
    if (this.summary_format != null) {
      setSummary(formatSummary());
    }
  }
  
  private boolean isInstalled(String paramString, List<ApplicationInfo> paramList)
  {
    paramList = paramList.iterator();
    while (paramList.hasNext()) {
      if (((ApplicationInfo)paramList.next()).packageName.equals(paramString)) {
        return true;
      }
    }
    return false;
  }
  
  private void updateInfoView()
  {
    if (this.summary_format != null) {
      setSummary(formatSummary());
    }
  }
  
  public ReaderProperty getReaderProperty()
  {
    return this.rprop;
  }
  
  public void notifyChanged()
  {
    super.notifyChanged();
    if (this.rprop != null) {
      ((OptionsActivity)getContext()).updateEnabled(this.rprop.name());
    }
  }
  
  protected void onDialogClosed(boolean paramBoolean)
  {
    if (!paramBoolean) {
      return;
    }
    if (this.pValue != null) {
      persistString(this.pValue);
    } else {
      persistString(null);
    }
    updateInfoView();
  }
  
  protected Object onGetDefaultValue(TypedArray paramTypedArray, int paramInt)
  {
    return this.dfltValue;
  }
  
  protected void onPrepareDialogBuilder(AlertDialog.Builder paramBuilder)
  {
    super.onPrepareDialogBuilder(paramBuilder);
    final PickAppsAdapter localPickAppsAdapter = new PickAppsAdapter((Activity)getContext());
    Object localObject = getContext().getPackageManager().getInstalledApplications(0);
    boolean bool = isInstalled("com.android.vending", (List)localObject);
    appendPredefinedItem("com.abbyy.mobile.lingvo.market", "Abbyy Lingvo", localPickAppsAdapter, (List)localObject, bool);
    localPickAppsAdapter.appendItem(new Intent("goldendict.intent.action.SEARCH"), null);
    appendPredefinedItem("mobi.goldendict.android.free", "GoldenDict", localPickAppsAdapter, (List)localObject, bool);
    appendPredefinedItem("com.ngc.fora", "Fora Dictionary", localPickAppsAdapter, (List)localObject, bool);
    appendPredefinedItem("com.google.android.apps.translate", "Google translate", localPickAppsAdapter, (List)localObject, bool);
    appendPredefinedItem("tool.offline.translation.dictionary", "Off-line Dictionaries", localPickAppsAdapter, (List)localObject, bool);
    localObject = new Intent("android.intent.action.SEND");
    ((Intent)localObject).setType("text/plain");
    localPickAppsAdapter.pickItems((Intent)localObject);
    localPickAppsAdapter.pickItems(new Intent("android.intent.action.SEARCH"));
    paramBuilder.setSingleChoiceItems(localPickAppsAdapter, findIndexOfValue(this.pValue, localPickAppsAdapter), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        if ((paramAnonymousInt >= 0) && (paramAnonymousInt < localPickAppsAdapter.getCount()))
        {
          PickAppsAdapter.Item localItem = localPickAppsAdapter.getItem(paramAnonymousInt);
          try
          {
            Intent localIntent = Intent.parseUri(localItem.intent, 1);
            if (localIntent.getData() != null)
            {
              ChooseTranslatorPreference.this.getContext().startActivity(localIntent);
              ChooseTranslatorPreference.this.onClick(paramAnonymousDialogInterface, -2);
            }
            else
            {
              ChooseTranslatorPreference.access$002(ChooseTranslatorPreference.this, localItem.intent);
              ChooseTranslatorPreference.this.onClick(paramAnonymousDialogInterface, -1);
            }
          }
          catch (Exception localException)
          {
            Log.e("translation", localException, "Cannot parse translation intent '%s'", new Object[] { localItem.intent });
          }
        }
        ChooseTranslatorPreference.this.onClick(paramAnonymousDialogInterface, -2);
        paramAnonymousDialogInterface.dismiss();
      }
    });
    paramBuilder.setPositiveButton(null, null);
  }
  
  protected void onSetInitialValue(boolean paramBoolean, Object paramObject)
  {
    if (paramBoolean)
    {
      this.pValue = getPersistedString(this.dfltValue);
      return;
    }
    this.pValue = this.dfltValue;
  }
  
  public void updateValue()
  {
    String str = ReaderContext.getJniWrapper().getPropertyValue(getKey());
    if ((str != null) && (str.length() != 0))
    {
      this.pValue = str;
      return;
    }
    this.pValue = this.dfltValue;
  }
}
