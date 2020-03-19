package com.art.artcamera.wecloudpush;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.art.artcamera.camera.q;
import com.art.artcamera.d.f;
import com.art.artcamera.d.g;
import com.art.artcamera.d.h;
import com.art.artcamera.filterstore.imageloade.KPNetworkImageView;
import com.art.artcamera.j.c;
import com.art.artcamera.utils.d;
import com.art.artcamera.vip.subscription.SVipActivity;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONObject;

public class b
{
  private static ExecutorService a = Executors.newFixedThreadPool(5);
  
  public static Intent a(Context paramContext, Intent paramIntent, String paramString)
  {
    int i = 0;
    Object localObject;
    String str1;
    for (;;)
    {
      try
      {
        localObject = paramString.split("\\?");
        if (localObject.length == 1)
        {
          str1 = paramString;
          if ("com.art.artcamera.filterstore.activity.FilterStoreActivity".equals(paramString)) {
            str1 = "com.art.artcamera.store.activity.StoreActivity";
          }
          paramIntent.setClassName(paramContext, str1);
          return paramIntent;
        }
        str1 = localObject[0];
        paramString = localObject[1];
        localObject = paramString.split("&");
        if (localObject.length != 1) {
          break;
        }
        localObject = paramString.split("=")[0];
        paramString = paramString.split("=")[1];
        if (!"type".equals(localObject)) {
          break label151;
        }
        if ("com.art.artcamera.filterstore.activity.FilterStoreActivity".equals(str1))
        {
          paramIntent.putExtra("extra_first_page", Integer.parseInt(paramString) + 1);
          paramString = str1;
          continue;
        }
        paramIntent.putExtra("extra_first_page", Integer.parseInt(paramString));
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
        return paramIntent;
      }
      paramString = str1;
      continue;
      label151:
      paramIntent.putExtra((String)localObject, paramString);
      paramString = str1;
    }
    for (;;)
    {
      paramString = str1;
      if (i >= localObject.length) {
        break;
      }
      paramString = localObject[i].split("=")[0];
      String str2 = localObject[i].split("=")[1];
      if ("type".equals(paramString))
      {
        if ("com.art.artcamera.filterstore.activity.FilterStoreActivity".equals(str1)) {
          paramIntent.putExtra("extra_first_page", Integer.parseInt(str2) + 1);
        } else {
          paramIntent.putExtra("extra_first_page", Integer.parseInt(str2));
        }
      }
      else {
        paramIntent.putExtra(paramString, str2);
      }
      i += 1;
    }
  }
  
  public static MessageBO a(String paramString)
  {
    MessageBO localMessageBO = new MessageBO();
    try
    {
      paramString = new JSONObject(paramString);
      localMessageBO.setMessageId(paramString.optLong("message_id"));
      localMessageBO.setTitle(paramString.optString("title"));
      localMessageBO.setContent(paramString.optString("content"));
      localMessageBO.setActionType(paramString.optString("action_type"));
      localMessageBO.setActionParam(paramString.optString("action_param"));
      localMessageBO.setIcon(paramString.optString("icon"));
      localMessageBO.setBanner(paramString.optString("banner"));
      localMessageBO.setPackageName(paramString.optString("black_list"));
      localMessageBO.setType(paramString.optString("type"));
      localMessageBO.setdType(paramString.optString("d_type"));
      localMessageBO.setPosition(paramString.optString("position"));
      localMessageBO.setVip(paramString.optString("is_vip"));
      localMessageBO.setSaleStartDate(paramString.optString("sale_start_date"));
      localMessageBO.setSaleEndDate(paramString.optString("sale_end_date"));
      localMessageBO.setButtonLeft(paramString.optString("button_left"));
      localMessageBO.setButtonRight(paramString.optString("button_right"));
      localMessageBO.setActionTypeRight(paramString.optString("action_type_right"));
      localMessageBO.setActionParamRight(paramString.optString("action_param_right"));
      return localMessageBO;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return localMessageBO;
  }
  
  public static MessageBO a(String paramString1, String paramString2)
  {
    MessageBO localMessageBO = new MessageBO();
    try
    {
      paramString1 = new JSONObject(paramString1);
      JSONObject localJSONObject = paramString1.optJSONObject("extra");
      localMessageBO.setMessageId(paramString1.optLong("promotionId"));
      localMessageBO.setdType(paramString1.optString("d_type"));
      localMessageBO.setActionType(paramString2);
      localMessageBO.setLabel(localJSONObject.optInt("label"));
      localMessageBO.setContent(localJSONObject.optString("reason"));
      localMessageBO.setFileType(localJSONObject.optInt("fileType"));
      localMessageBO.setImageUrl(localJSONObject.optString("imgUrl"));
      localMessageBO.setVideoUrl(localJSONObject.optString("vedioUrl"));
      localMessageBO.setReward(localJSONObject.optString("reward"));
      localMessageBO.setContactus(localJSONObject.optString("contactus"));
      localMessageBO.setGuideline(localJSONObject.optString("guideline"));
      return localMessageBO;
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
    return localMessageBO;
  }
  
  public static void a(Activity paramActivity, String paramString)
  {
    Object localObject1 = "";
    for (;;)
    {
      try
      {
        if ("1".equals(paramString))
        {
          localObject1 = c.a("wecloud_push_main");
          c.a("wecloud_push_main", "");
          if (!TextUtils.isEmpty((CharSequence)localObject1)) {
            break;
          }
          return;
        }
        if ("2".equals(paramString))
        {
          localObject1 = c.a("wecloud_push_filter_store");
          c.a("wecloud_push_filter_store", "");
          continue;
        }
        if (!"3".equals(paramString)) {
          continue;
        }
      }
      catch (Throwable paramActivity)
      {
        paramActivity.printStackTrace();
        return;
      }
      localObject1 = c.a("wecloud_push_gallery");
      c.a("wecloud_push_gallery", "");
    }
    Object localObject2 = c.a("wecloud_push_message_id");
    com.art.artcamera.background.a.b.d((String)localObject2 + "wc_d_show_mid");
    paramString = a((String)localObject1);
    Object localObject3 = paramString.getButtonLeft();
    String str = paramString.getButtonRight();
    localObject1 = new AlertDialog.Builder(paramActivity);
    ((AlertDialog.Builder)localObject1).setNegativeButton((CharSequence)localObject3, new b.2());
    ((AlertDialog.Builder)localObject1).setPositiveButton(str, new b.3((String)localObject2, paramString, paramActivity));
    if (!TextUtils.isEmpty(paramString.getBanner()))
    {
      localObject3 = LayoutInflater.from(paramActivity).inflate(d.h.wecloud_push_message_dialog, null, false);
      ((AlertDialog.Builder)localObject1).setView((View)localObject3);
      paramActivity = (TextView)((View)localObject3).findViewById(d.g.wecloud_push_title);
      localObject2 = (KPNetworkImageView)((View)localObject3).findViewById(d.g.wecloud_push_item);
      localObject3 = (TextView)((View)localObject3).findViewById(d.g.wecloud_push_content);
      paramActivity.setText(paramString.getTitle());
      ((TextView)localObject3).setText(paramString.getContent());
      ((KPNetworkImageView)localObject2).setDefaultImageResId(d.f.filter_store_details_default);
      ((KPNetworkImageView)localObject2).setErrorImageResId(d.f.filter_store_details_default);
      ((KPNetworkImageView)localObject2).setImageUrl(paramString.getBanner());
    }
    for (;;)
    {
      paramActivity = ((AlertDialog.Builder)localObject1).create();
      paramActivity.setCancelable(true);
      paramActivity.setCanceledOnTouchOutside(false);
      paramActivity.show();
      return;
      ((AlertDialog.Builder)localObject1).setTitle(paramString.getTitle());
      ((AlertDialog.Builder)localObject1).setMessage(paramString.getContent());
    }
  }
  
  public static void a(Context paramContext)
  {
    d.f(paramContext);
  }
  
  public static void a(Context paramContext, MessageBO paramMessageBO)
  {
    if ((!TextUtils.isEmpty(paramMessageBO.getSaleStartDate())) && (!TextUtils.isEmpty(paramMessageBO.getSaleEndDate())))
    {
      q.e(paramMessageBO.getSaleStartDate());
      q.f(paramMessageBO.getSaleEndDate());
    }
    SVipActivity.startSVipActivity(paramContext, 7, true);
  }
  
  public static void a(MessageBO paramMessageBO, a paramA)
  {
    if ((paramMessageBO == null) || (paramA == null)) {
      return;
    }
    a.submit(new b.1(paramMessageBO, paramA));
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    for (;;)
    {
      return false;
      paramContext = paramContext.getPackageManager().getInstalledPackages(0);
      paramString = paramString.split(",");
      int k = paramString.length;
      int i = 0;
      while (i < k)
      {
        Object localObject = paramString[i];
        int j = 0;
        while (j < paramContext.size())
        {
          PackageInfo localPackageInfo = (PackageInfo)paramContext.get(j);
          if (((localPackageInfo.applicationInfo.flags & 0x1) == 0) && (localObject.equals(localPackageInfo.packageName))) {
            return true;
          }
          j += 1;
        }
        i += 1;
      }
    }
  }
  
  public static void b(Context paramContext) {}
}
