package com.docusign.ink;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.FileProvider;
import android.widget.Toast;
import com.docusign.bizobj.Account;
import com.docusign.bizobj.BillingPlan;
import com.docusign.bizobj.BillingPlan.PaymentMethod;
import com.docusign.bizobj.Document;
import com.docusign.bizobj.User;
import com.docusign.common.DSActivity;
import com.docusign.common.DSAnalyticsUtil;
import com.docusign.common.DSApplication;
import com.docusign.ink.utils.DSLog;
import com.docusign.persistence.IAllowServerTemplates;
import com.docusign.persistence.IRestrictions;
import com.docusign.persistence.ObjectPersistenceFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class GrabDocDialogFragment
  extends SourceCellDialogFragment<IGrabDoc>
{
  private static final String DISTRIBUTOR_CODE1 = "DSX2013WEBPACKAGING";
  private static final String DISTRIBUTOR_CODE2 = "DocuSignIt";
  private static final String DISTRIBUTOR_CODE3 = "Google";
  private static final String DISTRIBUTOR_CODE4 = "Platform QA";
  public static final String DRIVE_PACKAGE_NAME = "com.google.android.apps.docs";
  private static final String EVERNOTE_PICKER = "com.evernote.action.NOTE_PICKER";
  private static final String FREE_PLAN_CLASSIFICATION = "free";
  private static final String PARAM_EXCLUDED = TAG + ".excludedClasses";
  private static final String PLAN_NAME = "Prime Edition";
  public static final String TAG = GrabDocDialogFragment.class.getSimpleName();
  
  public GrabDocDialogFragment()
  {
    super(IGrabDoc.class);
  }
  
  public static GrabDocDialogFragment newInstance()
  {
    return newInstance(new ComponentName[0]);
  }
  
  public static GrabDocDialogFragment newInstance(ComponentName[] paramArrayOfComponentName)
  {
    GrabDocDialogFragment localGrabDocDialogFragment = new GrabDocDialogFragment();
    Bundle localBundle = new Bundle();
    localBundle.putParcelableArray(PARAM_EXCLUDED, paramArrayOfComponentName);
    localGrabDocDialogFragment.setArguments(localBundle);
    return localGrabDocDialogFragment;
  }
  
  private boolean showTemplatesFeatureWall()
  {
    if (!Feature.IN_APP_UPGRADE.on()) {
      return false;
    }
    Object localObject2 = getActivity();
    Object localObject1 = null;
    if (localObject2 != null) {
      localObject1 = ((DSApplication)((Activity)localObject2).getApplication()).getCurrentUser();
    }
    if ((localObject1 != null) && (!((User)localObject1).isAwaitingActivation())) {
      try
      {
        Object localObject3 = ((DSApplication)getActivity().getApplication()).getBillingPlan();
        localObject2 = ((DSApplication)getActivity().getApplication()).getAccount();
        if ((localObject3 != null) && (localObject2 != null))
        {
          localObject1 = ((BillingPlan)localObject3).getPaymentMethod();
          localObject3 = ((BillingPlan)localObject3).getName();
          String str = ((Account)localObject2).getDistributorCode();
          localObject2 = ((Account)localObject2).getPlanClassification();
          if (localObject1 != BillingPlan.PaymentMethod.FREEMIUM) {
            return false;
          }
          if (((localObject2 == null) || (!((String)localObject2).equalsIgnoreCase("free"))) && ((!str.equalsIgnoreCase("DSX2013WEBPACKAGING")) || (((String)localObject3).contains("Prime Edition"))) && (!str.equalsIgnoreCase("DocuSignIt")) && (!str.contains("Google")))
          {
            boolean bool = str.equalsIgnoreCase("Platform QA");
            if (!bool) {}
          }
          else
          {
            return true;
          }
        }
      }
      catch (Exception localException)
      {
        DSLog.e(TAG, localException.getMessage());
      }
    }
    return false;
  }
  
  public List<Intent> getData()
  {
    ArrayList localArrayList1 = new ArrayList();
    Object localObject1 = getArguments();
    if ((localObject1 != null) && (((Bundle)localObject1).getParcelableArray(PARAM_EXCLUDED) != null) && ((((Bundle)localObject1).getParcelableArray(PARAM_EXCLUDED) instanceof ComponentName[])))
    {
      localObject1 = (ComponentName[])((Bundle)localObject1).getParcelableArray(PARAM_EXCLUDED);
      if (localObject1 != null) {
        localArrayList1.addAll(Arrays.asList((Object[])localObject1));
      }
    }
    if (!DSApplication.isDev()) {
      localArrayList1.add(new ComponentName(getActivity(), PickerActivity.class));
    }
    localObject1 = getActivity();
    PackageManager localPackageManager = ((Activity)localObject1).getPackageManager();
    ArrayList localArrayList2 = new ArrayList();
    if (localPackageManager.hasSystemFeature("android.hardware.camera")) {}
    Object localObject4;
    Object localObject3;
    try
    {
      Object localObject2 = new Intent("android.media.action.IMAGE_CAPTURE");
      localObject4 = ((DSApplication)((Activity)localObject1).getApplication()).createTempFile("DSI", ".jpg");
      localObject4 = FileProvider.getUriForFile(getActivity(), "com.docusign.ink.fileprovider", (File)localObject4);
      ((Intent)localObject2).putExtra("output", (Parcelable)localObject4).addFlags(1);
      getActivity().grantUriPermission(((DSActivity)getActivity()).getCameraPackageName(), (Uri)localObject4, 3);
      if (((Intent)localObject2).resolveActivity(getActivity().getPackageManager()) == null) {
        Toast.makeText(getActivity(), getString(2131100581), 1).show();
      }
      for (;;)
      {
        localArrayList2.add(new Intent(getActivity(), LibraryListActivity.class));
        if (ObjectPersistenceFactory.buildIAllowServerTemplates(getActivity().getApplicationContext()).getAllowServerTemplates().booleanValue())
        {
          localObject1 = new Intent(getActivity(), ManageTemplatesActivity.class);
          ((Intent)localObject1).putExtra(".paramFeatureWall", showTemplatesFeatureWall());
          if (showTemplatesFeatureWall()) {
            DSAnalyticsUtil.getTrackerInstance(getActivity()).sendEvent("feature_walls_template", "lock_displayed", "display_count", null);
          }
          localArrayList2.add(localObject1);
        }
        localObject1 = new HashMap();
        localObject2 = Document.SUPPORTED_MIME_TYPES.iterator();
        while (((Iterator)localObject2).hasNext())
        {
          localObject4 = (String)((Iterator)localObject2).next();
          localObject4 = localPackageManager.queryIntentActivities(new Intent("android.intent.action.GET_CONTENT").addCategory("android.intent.category.OPENABLE").setType((String)localObject4), 0).iterator();
          while (((Iterator)localObject4).hasNext())
          {
            localObject5 = (ResolveInfo)((Iterator)localObject4).next();
            ((HashMap)localObject1).put(((ResolveInfo)localObject5).activityInfo.name, localObject5);
          }
        }
        localArrayList2.add(localObject2);
      }
    }
    catch (IOException localIOException)
    {
      Object localObject5;
      for (;;)
      {
        ((DSActivity)localObject1).showErrorDialog(getString(2131100265), localIOException.getMessage());
      }
      localObject1 = ((HashMap)localObject1).values().iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject3 = (ResolveInfo)((Iterator)localObject1).next();
        localArrayList2.add(new Intent().setClassName(((ResolveInfo)localObject3).activityInfo.packageName, ((ResolveInfo)localObject3).activityInfo.name).setAction("android.intent.action.GET_CONTENT").addCategory("android.intent.category.OPENABLE").setType("*/*"));
      }
      localObject1 = localPackageManager.queryIntentActivities(new Intent("com.evernote.action.NOTE_PICKER"), 0).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject3 = (ResolveInfo)((Iterator)localObject1).next();
        localArrayList2.add(new Intent().setClassName(((ResolveInfo)localObject3).activityInfo.packageName, ((ResolveInfo)localObject3).activityInfo.name).setAction("com.evernote.action.NOTE_PICKER"));
      }
      localObject1 = null;
      localObject3 = localPackageManager.queryIntentActivities(new Intent("android.intent.action.OPEN_DOCUMENT").addCategory("android.intent.category.OPENABLE").setType("*/*"), 0);
      String[] arrayOfString;
      Iterator localIterator;
      int k;
      if (!((List)localObject3).isEmpty())
      {
        localObject3 = (ResolveInfo)((List)localObject3).get(0);
        localObject5 = localPackageManager.getInstalledPackages(1).iterator();
        do
        {
          localObject4 = localObject1;
          if (!((Iterator)localObject5).hasNext()) {
            break;
          }
          localObject4 = (PackageInfo)((Iterator)localObject5).next();
        } while (!((PackageInfo)localObject4).packageName.equals("com.google.android.apps.docs"));
        arrayOfString = ObjectPersistenceFactory.buildIRestrictions(getActivity()).getDocumentImportRestrictions();
        if (localArrayList2.isEmpty()) {
          break label1473;
        }
        localObject1 = null;
        localIterator = localArrayList2.iterator();
        k = 0;
      }
      for (;;)
      {
        if (!localIterator.hasNext()) {
          break label1311;
        }
        int i1 = 0;
        Intent localIntent = (Intent)localIterator.next();
        int j = 1;
        int i = i1;
        int i2;
        int m;
        if (arrayOfString != null)
        {
          i = i1;
          if (arrayOfString.length > 0)
          {
            i2 = arrayOfString.length;
            m = 0;
          }
        }
        int n;
        for (i = j;; i = n)
        {
          j = i;
          if (m < i2)
          {
            localObject5 = arrayOfString[m];
            n = i;
            if (((String)localObject5).isEmpty()) {
              break label1128;
            }
            if ((!((String)localObject5).equalsIgnoreCase("Drive")) || (localIntent.getComponent() == null) || (!localIntent.getComponent().getPackageName().contains("com.google.android.apps.docs"))) {
              break label1006;
            }
            j = 0;
          }
          for (;;)
          {
            i = i1;
            if (j != 0) {
              i = 1;
            }
            localObject5 = localArrayList1.iterator();
            while (((Iterator)localObject5).hasNext())
            {
              ComponentName localComponentName = (ComponentName)((Iterator)localObject5).next();
              if ((localIntent.getComponent() != null) && (localIntent.getComponent().equals(localComponentName))) {
                i = 1;
              }
            }
            localObject3 = null;
            break;
            label1006:
            if ((localIntent.getComponent() != null) && (localIntent.getComponent().getPackageName().equalsIgnoreCase(getActivity().getPackageName()))) {
              j = 0;
            }
            do
            {
              do
              {
                n = j;
                if (localIntent.getComponent() == null) {
                  break label1128;
                }
                n = j;
                if (!localIntent.getComponent().getPackageName().contains("ForwardIntentToUserOwner")) {
                  break label1128;
                }
                j = 1;
                break;
                j = i;
              } while (localIntent.getComponent() == null);
              j = i;
            } while (!localIntent.getComponent().getPackageName().contains("." + ((String)localObject5).toLowerCase()));
            j = 0;
          }
          label1128:
          m += 1;
        }
        localObject5 = localObject1;
        j = i;
        if (localObject1 == null)
        {
          localObject5 = localObject1;
          j = i;
          if (localObject3 != null)
          {
            localObject5 = localObject1;
            j = i;
            if (localIntent.getComponent() != null)
            {
              localObject5 = localObject1;
              j = i;
              if (localIntent.getComponent().getPackageName() != null)
              {
                localObject5 = localObject1;
                j = i;
                if (localIntent.getComponent().getPackageName().equals(((ResolveInfo)localObject3).activityInfo.packageName))
                {
                  if ((arrayOfString == null) || (arrayOfString.length <= 0)) {
                    localObject1 = localIntent;
                  }
                  j = 1;
                  localObject5 = localObject1;
                }
              }
            }
          }
        }
        i = k;
        if (localIntent.getComponent() != null)
        {
          i = k;
          if (localIntent.getComponent().getPackageName() != null)
          {
            i = k;
            if (localIntent.getComponent().getPackageName().equals("com.google.android.apps.docs")) {
              i = 1;
            }
          }
        }
        k = i;
        localObject1 = localObject5;
        if (j != 0)
        {
          localIterator.remove();
          k = i;
          localObject1 = localObject5;
        }
      }
      label1311:
      if (localObject1 != null) {
        localArrayList2.add(localObject1);
      }
      if (k != 0) {
        break label1473;
      }
    }
    if ((localObject1 == null) && (localObject4 != null))
    {
      if ((localObject3 == null) || (Build.VERSION.SDK_INT < 19)) {
        break label1476;
      }
      localArrayList2.add(new Intent().setClassName(((ResolveInfo)localObject3).activityInfo.packageName, ((ResolveInfo)localObject3).activityInfo.name).setAction("android.intent.action.GET_CONTENT").addCategory("android.intent.category.OPENABLE").setType("*/*"));
    }
    for (;;)
    {
      DSAnalyticsUtil.getTrackerInstance(getActivity().getApplicationContext()).sendEvent("document_source", "more_missing_drive_doc_source_present", "Manufacturer: " + Build.MANUFACTURER + "| Brand: " + Build.BRAND + "| Product: " + Build.PRODUCT, Long.valueOf(Build.VERSION.SDK_INT));
      label1473:
      return localArrayList2;
      label1476:
      localArrayList2.add(localPackageManager.getLaunchIntentForPackage(((PackageInfo)localObject4).packageName).setAction("android.intent.action.GET_CONTENT").addCategory("android.intent.category.OPENABLE"));
    }
  }
  
  public static abstract interface IGrabDoc
    extends SourceCellDialogFragment.ISourceCell<GrabDocDialogFragment>
  {}
}
