package com.liveyap.timehut.models;

import android.app.Activity;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.View;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.liveyap.timehut.Global;
import com.liveyap.timehut.helper.DateHelper;
import com.liveyap.timehut.helper.ImageHelper;
import com.liveyap.timehut.imageLoader.ImageLoaderHelper;
import com.liveyap.timehut.server.model.BabyAvatarModel;
import com.liveyap.timehut.server.model.BabyBackgroundModel;
import com.liveyap.timehut.server.model.BabyVideoQuotaModel;
import com.liveyap.timehut.server.model.VideoQuota;
import com.liveyap.timehut.views.LoadingActivity;
import com.liveyap.timehut.widgets.THToast;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import me.acen.foundation.helper.Util;
import org.json.JSONArray;
import org.json.JSONObject;
import rx.Scheduler;
import rx.Scheduler.Worker;
import rx.schedulers.Schedulers;

@DatabaseTable(tableName="babies")
public class Baby
  extends Model
  implements Serializable
{
  @Deprecated
  public static final String CROWN_BRONZE = "bronze";
  public static final String CROWN_GOLD = "gold";
  public static final String CROWN_SILVER = "silver";
  public static final int FOLLOWER_VIDEO_MINUTE = 120;
  public static final String GENDER_BOY = "boy";
  public static final String GENDER_GIRL = "girl";
  public static final String GENDER_UNKNOWN = "unknown";
  private static final String INVITATIONS_RESOURCE_PATH = "invitations";
  public static final int PARENT_VIDEO_MINUTE = 360;
  private static final String READ_SETTINGS_PERMISSION = "com.android.launcher.permission.READ_SETTINGS";
  public static final String RELATIONSHIP = "relation";
  static final int REMAIN_NO_SPACE = 104857600;
  static final int REMAIN_VIP_EXPIRE = 864000;
  public static final String RESOURCE_PATH = "babies";
  private static final String SHORTCUT_ADD_ACTION = "com.android.launcher.action.INSTALL_SHORTCUT";
  private static final String SHORTCUT_DEL_ACTION = "com.android.launcher.action.UNINSTALL_SHORTCUT";
  public static final String STATE_ADDABLE = "addable";
  public static final String STATE_ADDED = "added";
  public static final String STATE_FAMILY = "family";
  public static final String STATE_FOLLOWER = "follower";
  public static final String STATE_REQUESTED = "requested";
  @DatabaseField
  public String access_question;
  @DatabaseField
  public String address;
  public int age = -1;
  @DatabaseField
  public boolean allow_guest;
  public BabyAvatarModel avatar;
  @DatabaseField
  public String avatar_filePath;
  @DatabaseField
  public String avatar_original;
  @DatabaseField
  public String avatar_rounded;
  public BabyBackgroundModel background;
  @DatabaseField
  public String background_filePath;
  @DatabaseField
  public String background_original;
  @DatabaseField
  public String birthday;
  private Date birthdayDate;
  @DatabaseField
  public String crown;
  @DatabaseField
  public String first_name;
  @DatabaseField
  public String gender;
  @DatabaseField(id=true)
  public long id;
  @DatabaseField
  public String identifier;
  @DatabaseField
  public String last_name;
  private int months = -1;
  @DatabaseField
  public String name;
  @DatabaseField
  public String question_answer;
  @DatabaseField
  public long quota_used;
  @DatabaseField
  public String reason;
  @DatabaseField
  public String relation;
  @DatabaseField
  public String relation_type;
  @DatabaseField
  public boolean show;
  public String state;
  @DatabaseField
  public long total_video_space;
  public int unread;
  @DatabaseField
  public long user_id;
  @DatabaseField
  public long utc_offset;
  @SerializedName("video_quota")
  @DatabaseField
  public long video_quota_all;
  @DatabaseField
  public long video_quota_basic;
  public long video_quota_extra;
  @DatabaseField
  public long video_quota_vip;
  @DatabaseField
  public long video_space_used;
  @DatabaseField
  public long vip_expiration;
  
  public Baby() {}
  
  public Baby(JSONObject paramJSONObject) {}
  
  public static void addShortCut(Baby paramBaby, Bitmap paramBitmap, Context paramContext)
  {
    addShortCut(paramBaby, paramBitmap, paramContext, true);
  }
  
  private static void addShortCut(Baby paramBaby, Bitmap paramBitmap, Context paramContext, boolean paramBoolean)
  {
    addShortCut(paramBaby.getDisplayName(), paramBaby.getId(), paramBitmap, paramContext, paramBoolean);
  }
  
  public static void addShortCut(String paramString, long paramLong, Bitmap paramBitmap, Context paramContext, boolean paramBoolean)
  {
    Intent localIntent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
    localIntent.putExtra("android.intent.extra.shortcut.NAME", paramString);
    localIntent.putExtra("duplicate", false);
    paramString = new Intent("android.intent.action.VIEW");
    paramString.setComponent(new ComponentName(Global.packageName, LoadingActivity.class.getName()));
    paramString.putExtra("id", paramLong);
    paramString.putExtra("email", Global.getUser().email);
    paramString.addFlags(32768);
    localIntent.putExtra("android.intent.extra.shortcut.INTENT", paramString);
    paramString = paramBitmap;
    if (paramBoolean) {
      paramString = ImageHelper.makeBitmapForShortCut(paramBitmap);
    }
    localIntent.putExtra("android.intent.extra.shortcut.ICON", paramString);
    paramContext.sendBroadcast(localIntent);
  }
  
  public static void addShortCutNow(Activity paramActivity, View paramView, Baby paramBaby)
  {
    if (paramBaby == null) {
      return;
    }
    if (TextUtils.isEmpty(paramBaby.getAvatar()))
    {
      addShortCut(paramBaby, null, paramActivity);
      THToast.show(Global.getString(2131230864, new Object[] { paramBaby.getDisplayName() }));
      return;
    }
    if (paramView != null) {
      paramView.setEnabled(false);
    }
    THToast.show(Global.getString(2131230862, new Object[] { paramBaby.getDisplayName() }));
    ImageLoaderHelper.get(paramBaby.getAvatar(), new Baby.2(paramBaby, paramActivity));
  }
  
  public static void addShortCutNow(Activity paramActivity, Baby paramBaby)
  {
    addShortCutNow(paramActivity, null, paramBaby);
  }
  
  public static void addShutCutByString(Baby paramBaby, String paramString, Context paramContext)
  {
    if (TextUtils.isEmpty(paramString))
    {
      addShortCut(paramBaby, ImageHelper.readBitmap(2130838395), paramContext, false);
      return;
    }
    Schedulers.io().createWorker().schedule(new Baby.1(paramBaby, paramString, paramContext));
  }
  
  public static void delShortcutFromDesktop(Baby paramBaby, Context paramContext)
  {
    Intent localIntent1 = new Intent("com.android.launcher.action.UNINSTALL_SHORTCUT");
    localIntent1.putExtra("android.intent.extra.shortcut.NAME", paramBaby.getDisplayName());
    Intent localIntent2 = new Intent("android.intent.action.VIEW");
    localIntent2.setComponent(new ComponentName(Global.packageName, LoadingActivity.class.getName()));
    localIntent2.putExtra("id", paramBaby.id);
    localIntent2.putExtra("email", Global.getUser().email);
    localIntent2.addFlags(32768);
    localIntent1.putExtra("android.intent.extra.shortcut.INTENT", localIntent2);
    localIntent1.putExtra("duplicate", false);
    paramContext.sendBroadcast(localIntent1);
  }
  
  private static String getAuthorityFromPermission(Context paramContext, String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      break label30;
    }
    label7:
    do
    {
      return null;
      paramContext = paramContext.getPackageManager().getInstalledPackages(8);
    } while (paramContext == null);
    paramContext = paramContext.iterator();
    for (;;)
    {
      label30:
      if (!paramContext.hasNext()) {
        break label7;
      }
      ProviderInfo[] arrayOfProviderInfo = ((PackageInfo)paramContext.next()).providers;
      if (arrayOfProviderInfo == null) {
        break;
      }
      int j = arrayOfProviderInfo.length;
      int i = 0;
      while (i < j)
      {
        ProviderInfo localProviderInfo = arrayOfProviderInfo[i];
        if ((paramString.equals(localProviderInfo.readPermission)) || (paramString.equals(localProviderInfo.writePermission))) {
          return localProviderInfo.authority;
        }
        i += 1;
      }
    }
  }
  
  public static boolean hasShortcut(Baby paramBaby, Context paramContext)
  {
    Object localObject = getAuthorityFromPermission(paramContext, "com.android.launcher.permission.READ_SETTINGS");
    if (localObject == null) {
      return false;
    }
    localObject = Uri.parse("content://" + (String)localObject + "/favorites?notify=true");
    paramContext = paramContext.getContentResolver();
    paramBaby = paramBaby.getDisplayName();
    paramBaby = paramContext.query((Uri)localObject, new String[] { "title" }, "title=?", new String[] { paramBaby }, null);
    if ((paramBaby != null) && (paramBaby.getCount() > 0))
    {
      paramBaby.moveToFirst();
      return true;
    }
    return false;
  }
  
  public static List<Baby> safelyGetListFromObj(Object paramObject)
  {
    System.currentTimeMillis();
    ArrayList localArrayList = new ArrayList();
    if ((paramObject instanceof JSONArray))
    {
      paramObject = (JSONArray)paramObject;
      int j = paramObject.length();
      int i = 0;
      while (i < j)
      {
        JSONObject localJSONObject = paramObject.optJSONObject(i);
        if (localJSONObject != null) {
          localArrayList.add(new Baby(localJSONObject));
        }
        i += 1;
      }
    }
    return localArrayList;
  }
  
  public String boyOrGirl()
  {
    if (isBoy()) {
      return Global.getString(2131230876);
    }
    if (isGirl()) {
      return Global.getString(2131230877);
    }
    return Global.getString(2131230878);
  }
  
  public String boyOrGirl(boolean paramBoolean)
  {
    if (isBoy()) {
      return Global.getString(2131230876);
    }
    if (isGirl()) {
      return Global.getString(2131230877);
    }
    if (paramBoolean) {
      return Global.getString(2131230878);
    }
    return "";
  }
  
  public boolean equals(Object paramObject)
  {
    return ((paramObject instanceof Baby)) && ((this == paramObject) || (this.id == ((Baby)paramObject).id));
  }
  
  public String getAddress()
  {
    return this.address;
  }
  
  public int getAge()
  {
    if (this.age < 0) {
      this.age = DateHelper.getYears(new Date(), this.birthdayDate);
    }
    return this.age;
  }
  
  public String getAvatar()
  {
    return this.avatar_rounded;
  }
  
  public String getBackground()
  {
    return this.background_original;
  }
  
  public Date getBirthday()
  {
    SimpleDateFormat localSimpleDateFormat;
    if (this.birthdayDate == null)
    {
      if (this.birthday == null) {
        break label50;
      }
      localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }
    for (;;)
    {
      try
      {
        this.birthdayDate = localSimpleDateFormat.parse(this.birthday);
        return this.birthdayDate;
      }
      catch (ParseException localParseException)
      {
        localParseException.printStackTrace();
        continue;
      }
      label50:
      this.birthdayDate = new Date();
    }
  }
  
  public Calendar getBirthdayCalendar()
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTime(getBirthday());
    return localCalendar;
  }
  
  public String getBirthdayString()
  {
    return this.birthday;
  }
  
  public String getDisplayName()
  {
    return getDisplayName(false);
  }
  
  public String getDisplayName(boolean paramBoolean)
  {
    if (TextUtils.isEmpty(this.name)) {
      return this.last_name + " " + this.first_name;
    }
    return this.name;
  }
  
  public String getDisplayRelation()
  {
    if ("mom".equalsIgnoreCase(this.relation)) {
      return Global.getString(2131230893);
    }
    if ("dad".equalsIgnoreCase(this.relation)) {
      return Global.getString(2131230892);
    }
    return com.liveyap.timehut.helper.StringHelper.getRelationVisibleByKey(this.relation);
  }
  
  public String getFirstName()
  {
    return this.first_name;
  }
  
  public String getFullAddress()
  {
    String str = getAddress();
    if (!Util.isNullOrEmpty(str)) {
      return str;
    }
    return "";
  }
  
  public String getFullName()
  {
    if (TextUtils.isEmpty(this.first_name)) {
      this.first_name = "";
    }
    if (TextUtils.isEmpty(this.last_name)) {
      this.last_name = "";
    }
    if (me.acen.foundation.helper.StringHelper.hasChinese(this.first_name + this.name + this.last_name)) {
      return this.last_name + this.first_name;
    }
    if ((TextUtils.isEmpty(this.first_name)) || (TextUtils.isEmpty(this.last_name))) {
      return this.first_name + this.last_name;
    }
    return this.first_name + " " + this.last_name;
  }
  
  public String getGender()
  {
    return this.gender;
  }
  
  public long getId()
  {
    return this.id;
  }
  
  public String getLastName()
  {
    return this.last_name;
  }
  
  public String getLocalAvatar()
  {
    return this.avatar_filePath;
  }
  
  public int getMonths()
  {
    if (this.months < 0) {
      this.months = DateHelper.getMD(this.birthdayDate, new Date())[0];
    }
    return this.months;
  }
  
  public String getNickname()
  {
    return this.name;
  }
  
  public String getRelation()
  {
    return this.relation;
  }
  
  public String getRelationButMe()
  {
    if ("mom".equalsIgnoreCase(this.relation)) {
      return Global.getString(2131230892);
    }
    if ("dad".equalsIgnoreCase(this.relation)) {
      return Global.getString(2131230893);
    }
    return Global.getString(2131230892) + Global.getString(2131230760) + Global.getString(2131230893);
  }
  
  public int getUnread()
  {
    return this.unread;
  }
  
  public long getUserId()
  {
    return this.user_id;
  }
  
  public long getVideoQuota()
  {
    return this.video_quota_all;
  }
  
  public long getVideoQuotaRest()
  {
    return getVideoQuota() - getVideoQuotaUsed();
  }
  
  public long getVideoQuotaUsed()
  {
    long l = 0L;
    if (this.quota_used >= 0L) {
      l = this.quota_used;
    }
    return l;
  }
  
  public long getVideoSpace()
  {
    return this.total_video_space;
  }
  
  public long getVideoSpaceRest()
  {
    return getVideoSpace() - getVideoSpaceUsed();
  }
  
  public long getVideoSpaceUsed()
  {
    return this.video_space_used;
  }
  
  public boolean hasVideoQuotaUsed()
  {
    return this.quota_used != Long.MAX_VALUE;
  }
  
  public boolean hasVideoSpaceUsed()
  {
    return this.total_video_space != 0L;
  }
  
  public String heOrShe()
  {
    if (isBoy()) {
      return Global.getString(2131230847);
    }
    if (isGirl()) {
      return Global.getString(2131230852);
    }
    return Global.getString(2131230850);
  }
  
  public String himOrHer()
  {
    if (isGirl()) {
      return Global.getString(2131230764);
    }
    return Global.getString(2131230765);
  }
  
  public String hisOrHer(boolean paramBoolean)
  {
    if (isBoy())
    {
      if (paramBoolean) {}
      for (i = 2131230849;; i = 2131230927) {
        return Global.getString(i);
      }
    }
    if (isGirl())
    {
      if (paramBoolean) {}
      for (i = 2131230848;; i = 2131230914) {
        return Global.getString(i);
      }
    }
    if (paramBoolean) {}
    for (int i = 2131230851;; i = 2131231103) {
      return Global.getString(i);
    }
  }
  
  public void init()
  {
    if (this.avatar != null)
    {
      this.avatar_original = this.avatar.original;
      this.avatar_rounded = this.avatar.rounded;
    }
    if (this.background != null) {
      this.background_original = this.background.original;
    }
    SimpleDateFormat localSimpleDateFormat;
    if (!TextUtils.isEmpty(this.birthday)) {
      localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }
    try
    {
      this.birthdayDate = localSimpleDateFormat.parse(this.birthday);
      return;
    }
    catch (ParseException localParseException)
    {
      localParseException.printStackTrace();
    }
  }
  
  public boolean isAddable()
  {
    return (TextUtils.isEmpty(this.state)) || ("addable".equalsIgnoreCase(this.state));
  }
  
  public boolean isAdded()
  {
    return (!TextUtils.isEmpty(this.state)) && ("added".equalsIgnoreCase(this.state));
  }
  
  public boolean isBoy()
  {
    return "boy".equalsIgnoreCase(getGender());
  }
  
  public boolean isBuddy()
  {
    return !"family".equals(this.relation_type);
  }
  
  public boolean isDadOrMom()
  {
    return ("mom".equalsIgnoreCase(this.relation)) || ("dad".equalsIgnoreCase(this.relation));
  }
  
  public boolean isFamily()
  {
    return (!TextUtils.isEmpty(this.state)) && ("family".equalsIgnoreCase(this.state));
  }
  
  public boolean isFollower()
  {
    return (!TextUtils.isEmpty(this.state)) && ("follower".equalsIgnoreCase(this.state));
  }
  
  public boolean isGirl()
  {
    return "girl".equalsIgnoreCase(getGender());
  }
  
  public boolean isMum()
  {
    return "mom".equalsIgnoreCase(this.relation);
  }
  
  public boolean isRequested()
  {
    return (!TextUtils.isEmpty(this.state)) && ("requested".equalsIgnoreCase(this.state));
  }
  
  public boolean isVipAccount()
  {
    return this.vip_expiration * 1000L > System.currentTimeMillis();
  }
  
  public String maleOrFemale()
  {
    if (isBoy()) {
      return Global.getString(2131231687);
    }
    if (isGirl()) {
      return Global.getString(2131231483);
    }
    return Global.getString(2131230878);
  }
  
  public void setAddress(String paramString)
  {
    this.address = paramString;
  }
  
  public void setBirthday(Date paramDate)
  {
    this.birthdayDate = paramDate;
    this.birthday = DateFormat.format("yyyy-MM-dd", paramDate).toString();
  }
  
  public void setFirstName(String paramString)
  {
    this.first_name = paramString;
  }
  
  public void setGender(String paramString)
  {
    if (!Util.isNullOrEmpty(paramString)) {
      this.gender = paramString.toLowerCase();
    }
  }
  
  public void setLastName(String paramString)
  {
    this.last_name = paramString;
  }
  
  public void setLocalAvatar(String paramString)
  {
    this.avatar_filePath = paramString;
  }
  
  public void setNickname(String paramString)
  {
    this.name = paramString;
  }
  
  public void setRelation(String paramString)
  {
    this.relation = paramString;
  }
  
  public void setUnread(int paramInt)
  {
    this.unread = paramInt;
  }
  
  public void setVideoQuotaTime(BabyVideoQuotaModel paramBabyVideoQuotaModel)
  {
    if (paramBabyVideoQuotaModel == null) {}
    do
    {
      return;
      this.quota_used = paramBabyVideoQuotaModel.quota_used;
      this.vip_expiration = paramBabyVideoQuotaModel.vip_expiration;
      this.total_video_space = paramBabyVideoQuotaModel.total_video_space;
      this.video_space_used = paramBabyVideoQuotaModel.video_space_used;
    } while (paramBabyVideoQuotaModel.video_quota == null);
    this.video_quota_basic = paramBabyVideoQuotaModel.video_quota.basic;
    this.video_quota_vip = paramBabyVideoQuotaModel.video_quota.vip;
    this.video_quota_extra = paramBabyVideoQuotaModel.video_quota.extra;
  }
  
  public boolean vipWillExpire()
  {
    long l = System.currentTimeMillis() / 1000L;
    return (this.vip_expiration > l) && (this.vip_expiration < 864000L + l);
  }
  
  public boolean vipWillOver()
  {
    return (this.vip_expiration * 1000L < System.currentTimeMillis()) && (getVideoSpaceRest() < 104857600L);
  }
}
