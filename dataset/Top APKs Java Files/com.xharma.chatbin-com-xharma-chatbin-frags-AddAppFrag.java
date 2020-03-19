package com.xharma.chatbin.frags;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AlertDialog.Builder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.xharma.chatbin.activity.AddAppActivity;
import com.xharma.chatbin.adapter.AddAppPopAdapter;
import com.xharma.chatbin.common.PrefManager;
import com.xharma.chatbin.entity.AppEntity;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class AddAppFrag
  extends DialogFragment
{
  private ListView appList;
  private AddAppPopAdapter arrayAdapter;
  private String base_url;
  private Calendar c;
  private final String chattingAppsListStr = "com.gbwhatsapp@com.whatsapp.w4b@com.facebook.mlite@com.instagram.android@com.imo.android.imoimbeta@com.facebook.orca@com.bsb.hike@com.imo.android.imoim@com.snapchat.android@";
  private final String chattingAppsListStrAll = "com.whatsapp.w4b@com.skype.raider@com.microsoft.office.lync15@com.facebook.mlite@com.zing.zalo@org.telegram.messenger@com.icq.mobile.client@com.viber.voip@com.talkray.client@com.jiochat.jiochatapp@jp.naver.line.android@com.mywickr.wickr2@com.tencent.mm@com.skype.m2@com.calea.echo@com.instagram.android@com.wire@kik.android@org.thoughtcrime.securesms@com.imo.android.imoimbeta@org.thunderdog.challegram@com.twitter.android@im.thebot.messenger@com.juphoon.justalk@com.rebelvox.voxer@com.linecorp.linelite@org.telegram.plus@ru.ok.messages@com.sgiggle.production@com.facebook.orca@com.bbm@live.hala@com.kakao.talk@@ru.mail@com.bsb.hike@com.imo.android.imoim@com.snapchat.android@";
  private SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
  private String from;
  private ArrayList<AppEntity> newList;
  private PrefManager prefManager;
  
  public AddAppFrag() {}
  
  private void alertNotify()
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(getActivity(), 2131689644);
    localBuilder.setTitle("Notify us");
    localBuilder.setMessage("There are plenty of apps out there, we might have missed your app. but we will try to provide service for your app as soon as possible ");
    localBuilder.setNegativeButton("cancel", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.dismiss();
      }
    });
    localBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface = new Bundle();
        Object localObject = AddAppFrag.this.getFragmentManager();
        AddAppFrag localAddAppFrag = new AddAppFrag();
        paramAnonymousDialogInterface.putString("from", "frag");
        localAddAppFrag.setArguments(paramAnonymousDialogInterface);
        localAddAppFrag.show((FragmentManager)localObject, "App Fragment");
        paramAnonymousDialogInterface = AddAppFrag.this.getActivity().getLayoutInflater().inflate(2131427373, (ViewGroup)AddAppFrag.this.getActivity().findViewById(2131296258));
        localObject = new Toast(AddAppFrag.this.getActivity());
        ((TextView)paramAnonymousDialogInterface.findViewById(2131296271)).setText("select your app");
        ((Toast)localObject).setDuration(1);
        ((Toast)localObject).setView(paramAnonymousDialogInterface);
        ((Toast)localObject).show();
      }
    });
    localBuilder.show();
  }
  
  private void sendAppDetails(final AppEntity paramAppEntity, final Dialog paramDialog)
  {
    final String str = paramAppEntity.getAppName();
    this.c = Calendar.getInstance();
    try
    {
      RequestQueue localRequestQueue = Volley.newRequestQueue(getActivity());
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append(this.base_url);
      ((StringBuilder)localObject).append("appRequest");
      localObject = ((StringBuilder)localObject).toString();
      JSONObject localJSONObject = new JSONObject();
      localJSONObject.put("device_id", this.prefManager.getUniqueId());
      localJSONObject.put("appName", str);
      localJSONObject.put("pkgName", paramAppEntity.getPkgName());
      localJSONObject.put("time", this.df.format(this.c.getTime()));
      paramAppEntity = localJSONObject.toString();
      localRequestQueue.add(new StringRequest(1, (String)localObject, new Response.Listener()new Response.ErrorListener
      {
        public void onResponse(String paramAnonymousString)
        {
          try
          {
            new JSONObject(paramAnonymousString);
            paramAnonymousString = AddAppFrag.this.getActivity().getLayoutInflater().inflate(2131427373, (ViewGroup)AddAppFrag.this.getActivity().findViewById(2131296258));
            Toast localToast = new Toast(AddAppFrag.this.getActivity());
            TextView localTextView = (TextView)paramAnonymousString.findViewById(2131296271);
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append("Request for ");
            localStringBuilder.append(str);
            localStringBuilder.append(" has been sent ");
            localTextView.setText(localStringBuilder.toString());
            localToast.setDuration(1);
            localToast.setView(paramAnonymousString);
            localToast.show();
            paramDialog.dismiss();
            return;
          }
          catch (JSONException paramAnonymousString)
          {
            Log.d("JSONException", String.valueOf(paramAnonymousString.getStackTrace()));
          }
        }
      }, new Response.ErrorListener()
      {
        public void onErrorResponse(VolleyError paramAnonymousVolleyError)
        {
          Log.d("JSONException", paramAnonymousVolleyError.toString());
        }
      })
      {
        public byte[] getBody()
          throws AuthFailureError
        {
          try
          {
            if (paramAppEntity == null) {
              return null;
            }
            byte[] arrayOfByte = paramAppEntity.getBytes("utf-8");
            return arrayOfByte;
          }
          catch (UnsupportedEncodingException localUnsupportedEncodingException)
          {
            for (;;) {}
          }
          VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", new Object[] { paramAppEntity, "utf-8" });
          return null;
        }
        
        public String getBodyContentType()
        {
          return "application/json; charset=utf-8";
        }
        
        protected Response<String> parseNetworkResponse(NetworkResponse paramAnonymousNetworkResponse)
        {
          String str = "";
          if (paramAnonymousNetworkResponse != null) {
            str = String.valueOf(paramAnonymousNetworkResponse.statusCode);
          }
          Response.success(str, HttpHeaderParser.parseCacheHeaders(paramAnonymousNetworkResponse));
          return super.parseNetworkResponse(paramAnonymousNetworkResponse);
        }
      });
      return;
    }
    catch (JSONException paramAppEntity)
    {
      paramAppEntity.printStackTrace();
    }
  }
  
  public View onCreateView(final LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    paramLayoutInflater = paramLayoutInflater.inflate(2131427396, paramViewGroup, false);
    this.base_url = getActivity().getResources().getString(2131623994);
    for (;;)
    {
      int i;
      try
      {
        if (getArguments() != null)
        {
          this.from = getArguments().getString("from");
          Object localObject3;
          if ("activity".equals(this.from))
          {
            this.prefManager = new PrefManager(getActivity());
            paramViewGroup = this.prefManager.getAdditionalPackages();
            this.appList = ((ListView)paramLayoutInflater.findViewById(2131296309));
            this.newList = new ArrayList();
            paramBundle = getActivity().getPackageManager().getInstalledPackages(0);
            i = 0;
            if (i < paramBundle.size())
            {
              localObject3 = (PackageInfo)paramBundle.get(i);
              if ((((PackageInfo)localObject3).applicationInfo.flags & 0x1) != 0) {
                break label856;
              }
              localObject1 = ((PackageInfo)localObject3).applicationInfo.loadLabel(getActivity().getPackageManager()).toString();
              localObject2 = ((PackageInfo)localObject3).applicationInfo.packageName;
              Object localObject4 = new StringBuilder();
              ((StringBuilder)localObject4).append((String)localObject2);
              ((StringBuilder)localObject4).append("@");
              if (paramViewGroup.contains(((StringBuilder)localObject4).toString())) {
                break label856;
              }
              localObject4 = new StringBuilder();
              ((StringBuilder)localObject4).append((String)localObject2);
              ((StringBuilder)localObject4).append("@");
              if (!"com.gbwhatsapp@com.whatsapp.w4b@com.facebook.mlite@com.instagram.android@com.imo.android.imoimbeta@com.facebook.orca@com.bsb.hike@com.imo.android.imoim@com.snapchat.android@".contains(((StringBuilder)localObject4).toString())) {
                break label856;
              }
              localObject3 = ((PackageInfo)localObject3).applicationInfo.loadIcon(getActivity().getPackageManager());
              localObject4 = new AppEntity();
              ((AppEntity)localObject4).setAppName((String)localObject1);
              ((AppEntity)localObject4).setPkgName((String)localObject2);
              ((AppEntity)localObject4).setAppIcon((Drawable)localObject3);
              this.newList.add(localObject4);
              break label856;
            }
            paramViewGroup = ResourcesCompat.getDrawable(getResources(), 2131230843, null);
            paramBundle = new AppEntity();
            paramBundle.setAppName("Your app is not listed?");
            paramBundle.setPkgName("Tap here to select the app");
            paramBundle.setAppIcon(paramViewGroup);
            this.newList.add(paramBundle);
            this.arrayAdapter = new AddAppPopAdapter(getActivity(), this.newList, this.from);
            this.appList.setAdapter(this.arrayAdapter);
            this.appList.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {
              public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
              {
                if (paramAnonymousInt != AddAppFrag.this.newList.size() - 1)
                {
                  AddAppFrag.this.prefManager.setAdditionalPackages(((AppEntity)paramAnonymousAdapterView.getItemAtPosition(paramAnonymousInt)).getPkgName());
                  ((AddAppActivity)AddAppFrag.this.getActivity()).loadAdditionalApps();
                  AddAppFrag.this.prefManager.setAdditionalPackageCountAdd();
                  AddAppFrag.this.getDialog().dismiss();
                  return;
                }
                AddAppFrag.this.alertNotify();
              }
            });
          }
          else if ("frag".equals(this.from))
          {
            this.prefManager = new PrefManager(getActivity());
            this.appList = ((ListView)paramLayoutInflater.findViewById(2131296309));
            this.newList = new ArrayList();
            paramViewGroup = getActivity().getPackageManager().getInstalledPackages(0);
            i = 0;
            if (i < paramViewGroup.size())
            {
              localObject2 = (PackageInfo)paramViewGroup.get(i);
              if ((((PackageInfo)localObject2).applicationInfo.flags & 0x1) != 0) {
                break label865;
              }
              paramBundle = ((PackageInfo)localObject2).applicationInfo.loadLabel(getActivity().getPackageManager()).toString();
              localObject1 = ((PackageInfo)localObject2).applicationInfo.packageName;
              if ("com.whatsapp".equals(localObject1)) {
                break label865;
              }
              localObject3 = new StringBuilder();
              ((StringBuilder)localObject3).append((String)localObject1);
              ((StringBuilder)localObject3).append("@");
              if ("com.gbwhatsapp@com.whatsapp.w4b@com.facebook.mlite@com.instagram.android@com.imo.android.imoimbeta@com.facebook.orca@com.bsb.hike@com.imo.android.imoim@com.snapchat.android@".contains(((StringBuilder)localObject3).toString())) {
                break label865;
              }
              localObject2 = ((PackageInfo)localObject2).applicationInfo.loadIcon(getActivity().getPackageManager());
              localObject3 = new AppEntity();
              ((AppEntity)localObject3).setAppName(paramBundle);
              ((AppEntity)localObject3).setPkgName((String)localObject1);
              ((AppEntity)localObject3).setAppIcon((Drawable)localObject2);
              this.newList.add(localObject3);
              break label865;
            }
            this.arrayAdapter = new AddAppPopAdapter(getActivity(), this.newList, this.from);
            this.appList.setAdapter(this.arrayAdapter);
            this.appList.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {
              public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
              {
                paramAnonymousView = ((ConnectivityManager)AddAppFrag.this.getActivity().getSystemService("connectivity")).getActiveNetworkInfo();
                if ((paramAnonymousView != null) && (paramAnonymousView.isConnectedOrConnecting()))
                {
                  AddAppFrag.this.sendAppDetails((AppEntity)paramAnonymousAdapterView.getItemAtPosition(paramAnonymousInt), AddAppFrag.this.getDialog());
                  return;
                }
                paramAnonymousAdapterView = Snackbar.make(paramLayoutInflater, "No internet connection", 0);
                paramAnonymousAdapterView.setActionTextColor(65280);
                ((TextView)paramAnonymousAdapterView.getView().findViewById(2131296532)).setTextColor(-1);
                paramAnonymousAdapterView.show();
              }
            });
          }
        }
        getDialog().setTitle("App");
        i = getResources().getDimensionPixelSize(2131165329);
        int j = getResources().getDimensionPixelSize(2131165328);
        getDialog().getWindow().setLayout(i, j);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));
        return paramLayoutInflater;
      }
      catch (Exception paramViewGroup)
      {
        paramBundle = Calendar.getInstance();
        Object localObject1 = System.out;
        Object localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append("issue => ");
        ((StringBuilder)localObject2).append(paramViewGroup.toString());
        ((StringBuilder)localObject2).append(" Current time => ");
        ((StringBuilder)localObject2).append(paramBundle.getTime());
        ((PrintStream)localObject1).println(((StringBuilder)localObject2).toString());
        return paramLayoutInflater;
      }
      label856:
      i += 1;
      continue;
      label865:
      i += 1;
    }
  }
}
