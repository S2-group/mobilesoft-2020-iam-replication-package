package com.teamup.teamup.ui.eventdetail.sharing;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build.VERSION;
import android.provider.CalendarContract.Events;
import androidx.appcompat.app.AlertDialog.Builder;
import androidx.core.app.ShareCompat.IntentBuilder;
import androidx.lifecycle.Observer;
import ch.smoca.smoca_network.internetconnection.ConnectionManager;
import ch.smoca.uinavigation.FragmentActivity;
import ch.smoca.uinavigation.NavigationManager;
import ch.smoca.uinavigation.viewmodel.VMModelBase;
import com.teamup.teamup.model.dbo.EnabledEventSharingServices;
import com.teamup.teamup.model.dbo.Event;
import com.teamup.teamup.model.dbo.Subcalendar;
import com.teamup.teamup.model.dbo.User;
import com.teamup.teamup.model.livedata.EventSharePointerLiveData;
import com.teamup.teamup.model.realm.TeamupRealmManager;
import com.teamup.teamup.model.realm.TeamupRealmReader;
import com.teamup.teamup.net.repositories.MainRepository;
import com.teamup.teamup.ui.bottom_sheet.ListBottomSheet;
import com.teamup.teamup.ui.bottom_sheet.ListBottomSheet.ListBottomSheetListener;
import com.teamup.teamup.ui.bottom_sheet.ListBottomSheet.ListBottomSheetOption;
import io.realm.Realm;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import timber.log.Timber;

public class Sharer
{
  private MainRepository repository = new MainRepository();
  private VMModelBase viewModel;
  
  public Sharer(VMModelBase paramVMModelBase)
  {
    this.viewModel = paramVMModelBase;
  }
  
  private void appendTo(StringBuilder paramStringBuilder, String paramString1, String paramString2, String paramString3)
  {
    if ((paramString2 != null) && (!paramString2.isEmpty()))
    {
      paramStringBuilder.append(paramString1);
      paramStringBuilder.append(": ");
      paramStringBuilder.append(paramString2);
      paramStringBuilder.append(paramString3);
    }
  }
  
  private void executeShareAsPageInBrowser(EventSharePointerData paramEventSharePointerData)
  {
    paramEventSharePointerData = Uri.parse(paramEventSharePointerData.getFullURL());
    Resources localResources = NavigationManager.c().getResources();
    Intent localIntent = new Intent();
    localIntent.setAction("android.intent.action.VIEW");
    localIntent.setData(paramEventSharePointerData);
    NavigationManager.c().startActivity(Intent.createChooser(localIntent, localResources.getString(2131624140)));
  }
  
  private void executeShareAsPageInMessengers(EventSharePointerData paramEventSharePointerData)
  {
    Resources localResources = NavigationManager.c().getResources();
    Intent localIntent = new Intent();
    localIntent.setAction("android.intent.action.SEND");
    localIntent.putExtra("android.intent.extra.TEXT", paramEventSharePointerData.getFullURL());
    localIntent.setType("text/plain");
    NavigationManager.c().startActivity(Intent.createChooser(localIntent, localResources.getString(2131624140)));
  }
  
  private void executeShareTo(Event paramEvent, EventSharePointerData paramEventSharePointerData)
  {
    switch (4.$SwitchMap$com$teamup$teamup$ui$eventdetail$sharing$SHARE_ENDPOINT[paramEventSharePointerData.getEndpoint().ordinal()])
    {
    default: 
      return;
    case 7: 
      executeShareViaWhatsApp(paramEventSharePointerData);
      return;
    case 6: 
      executeShareViaEmail(paramEvent, paramEventSharePointerData);
      return;
    case 5: 
      executeShareViaTwitter(paramEventSharePointerData);
      return;
    case 4: 
      executeShareViaFacebook(paramEventSharePointerData);
      return;
    case 3: 
      executeShareAsPageInBrowser(paramEventSharePointerData);
      return;
    case 2: 
      executeShareAsPageInMessengers(paramEventSharePointerData);
      return;
    }
    executeShareViaOthers(paramEvent);
  }
  
  private void executeShareViaEmail(Event paramEvent, EventSharePointerData paramEventSharePointerData)
  {
    Resources localResources = NavigationManager.c().getResources();
    ShareCompat.IntentBuilder localIntentBuilder = ShareCompat.IntentBuilder.a(NavigationManager.c()).a("message/rfc822");
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(localResources.getString(2131624127));
    localStringBuilder.append(": ");
    localStringBuilder.append(paramEvent.getTitle());
    localIntentBuilder.c(localStringBuilder.toString()).b(getEmailMessageForEvent(paramEvent, paramEventSharePointerData)).a(localResources.getString(2131624101)).c();
  }
  
  private void executeShareViaFacebook(EventSharePointerData paramEventSharePointerData)
  {
    String str = paramEventSharePointerData.getFullURL();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("https://www.facebook.com/sharer/sharer.php?u=");
    localStringBuilder.append(paramEventSharePointerData.getFullURL());
    share("com.facebook.katana", "com.facebook.composer.shareintent.ImplicitShareIntentHandlerDefaultAlias", str, localStringBuilder.toString());
  }
  
  private void executeShareViaOthers(Event paramEvent)
  {
    shareAsIntent(paramEvent);
  }
  
  private void executeShareViaTwitter(EventSharePointerData paramEventSharePointerData)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("http://www.twitter.com/intent/tweet?url=");
    localStringBuilder.append(paramEventSharePointerData.getFullURL());
    localStringBuilder.append("&text=");
    shareAsURL(localStringBuilder.toString());
  }
  
  private void executeShareViaWhatsApp(EventSharePointerData paramEventSharePointerData)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("whatsapp://send?text=");
    localStringBuilder.append(paramEventSharePointerData.getFullURL());
    shareAsURL(localStringBuilder.toString());
  }
  
  private String formatDateForSharing(Date paramDate)
  {
    return new SimpleDateFormat("EEE, d MMM yyyy HH:mm z", Locale.getDefault()).format(paramDate);
  }
  
  private static long getCalendarContractAllDayDateFrom(Date paramDate)
  {
    java.util.Calendar localCalendar = java.util.Calendar.getInstance();
    localCalendar.setTimeZone(TimeZone.getTimeZone("UTC"));
    localCalendar.setTime(paramDate);
    localCalendar.set(14, 0);
    localCalendar.set(13, 0);
    localCalendar.set(12, 0);
    localCalendar.set(11, 0);
    return localCalendar.getTime().getTime();
  }
  
  private String getEmailMessageForEvent(Event paramEvent, EventSharePointerData paramEventSharePointerData)
  {
    Object localObject2 = TeamupRealmManager.getUnclosedRealm();
    Object localObject1 = TeamupRealmManager.RealmReader().findUUIDSubcalendarOfEvent(paramEvent, (Realm)localObject2);
    if (localObject1 == null) {
      localObject1 = "";
    } else {
      localObject1 = ((Subcalendar)localObject1).getName();
    }
    TeamupRealmManager.closeUnclosedRealm((Realm)localObject2);
    localObject2 = new StringBuilder();
    Resources localResources = NavigationManager.c().getResources();
    appendTo((StringBuilder)localObject2, localResources.getString(2131624076), paramEvent.getTitle(), "<br>");
    appendTo((StringBuilder)localObject2, localResources.getString(2131624077), formatDateForSharing(paramEvent.getStart_dt()), "<br>");
    appendTo((StringBuilder)localObject2, localResources.getString(2131624072), paramEvent.getLocation(), "<br>");
    appendTo((StringBuilder)localObject2, localResources.getString(2131624071), paramEvent.getWho(), "<br>");
    appendTo((StringBuilder)localObject2, localResources.getString(2131624065), (String)localObject1, "<br>");
    paramEvent = new StringBuilder();
    paramEvent.append("<a href=\"");
    paramEvent.append(paramEventSharePointerData.getFullURL());
    paramEvent.append("\">");
    paramEvent.append(paramEventSharePointerData.getFullURL());
    paramEvent.append("</a>");
    paramEvent = paramEvent.toString();
    appendTo((StringBuilder)localObject2, localResources.getString(2131624070), paramEvent, "<br>");
    ((StringBuilder)localObject2).append("<br>");
    ((StringBuilder)localObject2).append("<br>");
    ((StringBuilder)localObject2).append("---");
    ((StringBuilder)localObject2).append("<br>");
    paramEvent = new StringBuilder();
    paramEvent.append(localResources.getString(2131624068));
    paramEvent.append(" <a href=\"");
    paramEvent.append(localResources.getString(2131624314));
    paramEvent.append("\">");
    paramEvent.append(localResources.getString(2131624143));
    paramEvent.append("</a>");
    ((StringBuilder)localObject2).append(paramEvent.toString());
    return ((StringBuilder)localObject2).toString();
  }
  
  private Intent getIntentForEvent(Event paramEvent)
  {
    Intent localIntent = new Intent("android.intent.action.INSERT").setData(CalendarContract.Events.CONTENT_URI).putExtra("title", paramEvent.getTitle()).putExtra("description", paramEvent.getNotesHTML()).putExtra("eventLocation", paramEvent.getLocation()).putExtra("allDay", paramEvent.isAll_day());
    if (paramEvent.isAllDay())
    {
      localIntent.putExtra("beginTime", getCalendarContractAllDayDateFrom(paramEvent.getStart_dt()));
      localIntent.putExtra("endTime", getCalendarContractAllDayDateFrom(paramEvent.getEnd_dt()));
      return localIntent;
    }
    localIntent.putExtra("beginTime", paramEvent.getStart_dt().getTime());
    localIntent.putExtra("endTime", paramEvent.getEnd_dt().getTime());
    return localIntent;
  }
  
  private void share(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    try
    {
      Intent localIntent = new Intent();
      localIntent.setClassName(paramString1, paramString2);
      localIntent.setAction("android.intent.action.SEND");
      localIntent.setType("text/plain");
      localIntent.putExtra("android.intent.extra.TEXT", paramString3);
      NavigationManager.c().startActivity(localIntent);
      return;
    }
    catch (Exception paramString1)
    {
      for (;;) {}
    }
    new Intent("android.intent.action.SEND");
    paramString1 = new Intent("android.intent.action.VIEW", Uri.parse(paramString4));
    NavigationManager.c().startActivity(paramString1);
  }
  
  private void shareAsURL(String paramString)
  {
    Object localObject = new Intent("android.intent.action.VIEW");
    ((Intent)localObject).setData(Uri.parse(paramString));
    try
    {
      NavigationManager.c().startActivity((Intent)localObject);
      return;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("Sharing: app for url not found for: ");
    ((StringBuilder)localObject).append(paramString);
    Timber.b(((StringBuilder)localObject).toString(), new Object[0]);
  }
  
  private void shareFailed()
  {
    Object localObject = NavigationManager.c();
    if (Build.VERSION.SDK_INT >= 21) {
      localObject = new AlertDialog.Builder((Context)localObject, 2131689676);
    } else {
      localObject = new AlertDialog.Builder((Context)localObject);
    }
    Resources localResources = NavigationManager.c().getResources();
    ((AlertDialog.Builder)localObject).a(localResources.getString(2131624137)).c(2131165351).b(localResources.getString(2131624136)).b(17039370, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
    }).c();
  }
  
  public boolean isWhatsAppInstalled()
  {
    Iterator localIterator = NavigationManager.c().getPackageManager().getInstalledPackages(0).iterator();
    while (localIterator.hasNext()) {
      if (((PackageInfo)localIterator.next()).packageName.contains("com.whatsapp")) {
        return true;
      }
    }
    return false;
  }
  
  public void shareAsIntent(Event paramEvent)
  {
    NavigationManager.c().startActivity(getIntentForEvent(paramEvent));
  }
  
  public void shareTo(final Event paramEvent, final SHARE_ENDPOINT paramSHARE_ENDPOINT)
  {
    if (4.$SwitchMap$com$teamup$teamup$ui$eventdetail$sharing$SHARE_ENDPOINT[paramSHARE_ENDPOINT.ordinal()] != 1)
    {
      if (ConnectionManager.a().c())
      {
        paramSHARE_ENDPOINT = this.repository.requestLinkForEvent(paramEvent, paramSHARE_ENDPOINT);
        paramSHARE_ENDPOINT.observe(this.viewModel, new Observer()
        {
          public void onChanged(EventSharePointerData paramAnonymousEventSharePointerData)
          {
            if ((paramAnonymousEventSharePointerData != null) && (paramAnonymousEventSharePointerData.isSuccess())) {
              Sharer.this.executeShareTo(paramEvent, paramAnonymousEventSharePointerData);
            } else {
              Sharer.this.shareFailed();
            }
            paramSHARE_ENDPOINT.removeObserver(this);
          }
        });
        return;
      }
      shareFailed();
      return;
    }
    executeShareViaOthers(paramEvent);
  }
  
  public void showShareBottomSheet(User paramUser, final Event paramEvent)
  {
    EnabledEventSharingServices localEnabledEventSharingServices = paramUser.getCurrentCalendar().getEnabledEventSharingServices();
    Resources localResources = NavigationManager.c().getResources();
    boolean bool = ConnectionManager.a().c();
    paramUser = null;
    Object localObject = null;
    ListBottomSheet.ListBottomSheetOption[] arrayOfListBottomSheetOption;
    if (bool)
    {
      arrayOfListBottomSheetOption = new ListBottomSheet.ListBottomSheetOption[7];
      if (localEnabledEventSharingServices.isOther()) {
        paramUser = new ListBottomSheet.ListBottomSheetOption(SHARE_ENDPOINT.Other.ordinal(), localResources.getString(2131624122), 2131165341);
      } else {
        paramUser = null;
      }
      arrayOfListBottomSheetOption[0] = paramUser;
      if (localEnabledEventSharingServices.isPage()) {
        paramUser = new ListBottomSheet.ListBottomSheetOption(SHARE_ENDPOINT.Page.ordinal(), localResources.getString(2131624135), 2131165318);
      } else {
        paramUser = null;
      }
      arrayOfListBottomSheetOption[1] = paramUser;
      if (localEnabledEventSharingServices.isPage()) {
        paramUser = new ListBottomSheet.ListBottomSheetOption(SHARE_ENDPOINT.Browser.ordinal(), localResources.getString(2131624120), 2131165386);
      } else {
        paramUser = null;
      }
      arrayOfListBottomSheetOption[2] = paramUser;
      if (localEnabledEventSharingServices.isEmail()) {
        paramUser = new ListBottomSheet.ListBottomSheetOption(SHARE_ENDPOINT.Email.ordinal(), localResources.getString(2131624101), 2131165371);
      } else {
        paramUser = null;
      }
      arrayOfListBottomSheetOption[3] = paramUser;
      if (localEnabledEventSharingServices.isFacebook()) {
        paramUser = new ListBottomSheet.ListBottomSheetOption(SHARE_ENDPOINT.Facebook.ordinal(), localResources.getString(2131624138), 2131165344);
      } else {
        paramUser = null;
      }
      arrayOfListBottomSheetOption[4] = paramUser;
      if (localEnabledEventSharingServices.isTwitter()) {
        paramUser = new ListBottomSheet.ListBottomSheetOption(SHARE_ENDPOINT.Twitter.ordinal(), localResources.getString(2131624141), 2131165378);
      } else {
        paramUser = null;
      }
      arrayOfListBottomSheetOption[5] = paramUser;
      paramUser = localObject;
      if (localEnabledEventSharingServices.isWhatsapp()) {
        paramUser = new ListBottomSheet.ListBottomSheetOption(SHARE_ENDPOINT.WhatsApp.ordinal(), localResources.getString(2131624142), 2131165381);
      }
      arrayOfListBottomSheetOption[6] = paramUser;
      paramUser = arrayOfListBottomSheetOption;
    }
    else
    {
      arrayOfListBottomSheetOption = new ListBottomSheet.ListBottomSheetOption[2];
      arrayOfListBottomSheetOption[0] = new ListBottomSheet.ListBottomSheetOption(-1, localResources.getString(2131624049), 2131165385);
      if (localEnabledEventSharingServices.isOther()) {
        paramUser = new ListBottomSheet.ListBottomSheetOption(SHARE_ENDPOINT.Other.ordinal(), localResources.getString(2131624122), 2131165341);
      }
      arrayOfListBottomSheetOption[1] = paramUser;
      paramUser = arrayOfListBottomSheetOption;
    }
    ListBottomSheet.showOptions(NavigationManager.c(), 2131624134, paramUser, new ListBottomSheet.ListBottomSheetListener()
    {
      public void onOptionClicked(int paramAnonymousInt)
      {
        if (paramAnonymousInt >= 0) {
          Sharer.this.shareTo(paramEvent, SHARE_ENDPOINT.values()[paramAnonymousInt]);
        }
      }
    });
  }
}
