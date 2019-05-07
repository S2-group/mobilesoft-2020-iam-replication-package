package com.ocf.droid.common.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Handler;
import android.provider.Settings.Secure;
import android.provider.Settings.SettingNotFoundException;
import android.speech.tts.TextToSpeech;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.telephony.PhoneNumberUtils;
import android.text.Editable;
import android.text.Html;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextUtils.SimpleStringSplitter;
import android.text.style.UnderlineSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareContent;
import com.facebook.share.widget.ShareDialog;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.ocf.droid.R.string;
import com.ocf.droid.R.style;
import com.ocf.droid.common.dialog.DardenDialog;
import com.ocf.droid.darden.config.Permissions;
import com.ocf.droid.darden.ui.model.AppUpdate;
import com.ocf.droid.darden.ui.model.BrandMessage;
import com.ocf.droid.darden.ui.model.BrandMessage.MainContent;
import com.ocf.droid.darden.ui.model.BuyGiftCards;
import com.ocf.droid.darden.ui.model.CreateOrders;
import com.ocf.droid.darden.ui.model.ErrorModel;
import com.ocf.droid.darden.ui.model.MenuOption;
import com.ocf.droid.darden.ui.model.MobilePayEnable;
import com.ocf.droid.darden.ui.model.OrderDetails;
import com.ocf.droid.darden.ui.model.OrderItemModel;
import com.ocf.droid.darden.ui.model.OrderMenuItem;
import com.ocf.droid.darden.ui.model.RestaurantAddress;
import com.ocf.droid.darden.ui.model.RestaurantDetail;
import com.ocf.droid.darden.ui.model.RestaurantHourInfo;
import com.ocf.droid.darden.ui.model.RestaurantHourInfo.DateTimeStamp;
import com.ocf.droid.darden.ui.model.SiteConfigModel;
import com.ocf.droid.darden.ui.model.StateModel;
import com.ocf.droid.darden.ui.model.UserInfoFavoriteMenu;
import com.ocf.droid.darden.ui.model.VersionInfo;
import com.ocf.droid.darden.utils.DardenAnalyticUtils;
import com.ocf.droid.modules.AndroidCustomCrypto;
import com.ocf.droid.modules.SimpleCrypto;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.RoundingMode;
import java.net.URI;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CommonUtils
{
  public static final String ACCESSIBILITY_SERVICE_NAME = "com.google.android.marvin.talkback/com.google.android.marvin.talkback.TalkBackService";
  public static final int COLLAPSE_VIEW = 1;
  public static final String DATE_FORMATTING = "MM/dd/yyyy";
  public static InputFilter EMOJI_FILTER = new CommonUtils.33();
  public static final int EXPAND_VIEW = 0;
  public static final int FADE_IN = 1;
  public static final int FADE_OUT = 0;
  public static final int MAX_ZIP_LENGTH = 6;
  private static final String PREFERENCE_NAME = "Darden";
  public static final String REGEX_ZIP_CA3 = "^(?!.*[DFIOQU])[A-VXY][0-9][A-Z]$";
  public static final String REGEX_ZIP_CA6 = "^(?!.*[DFIOQU])[A-VXY][0-9][A-Z] ?[0-9][A-Z][0-9]$";
  public static final String REGEX_ZIP_US = "^\\d{5}$";
  private static final String TAG = "CommonUtils";
  private static Date closeHour;
  private static Date currentHour;
  public static final String htmlEntity = "&[a-zA-Z][a-zA-Z0-9]+;";
  public static final Pattern htmlPattern = Pattern.compile("(\\<\\w+((\\s+\\w+(\\s*\\=\\s*(?:\".*?\"|'.*?'|[^'\"\\>\\s]+))?)+\\s*|\\s*)\\>.*\\</\\w+\\>)|(\\<\\w+((\\s+\\w+(\\s*\\=\\s*(?:\".*?\"|'.*?'|[^'\"\\>\\s]+))?)+\\s*|\\s*)/\\>)|(&[a-zA-Z][a-zA-Z0-9]+;)", 32);
  private static boolean isShowing = false;
  private static int locationMode = 0;
  private static AlertDialog noNetworkConnectivityAlert;
  private static Date openHour;
  public static final String tagEnd = "\\</\\w+\\>";
  public static final String tagSelfClosing = "\\<\\w+((\\s+\\w+(\\s*\\=\\s*(?:\".*?\"|'.*?'|[^'\"\\>\\s]+))?)+\\s*|\\s*)/\\>";
  public static final String tagStart = "\\<\\w+((\\s+\\w+(\\s*\\=\\s*(?:\".*?\"|'.*?'|[^'\"\\>\\s]+))?)+\\s*|\\s*)\\>";
  private static TextToSpeech textToSpeech;
  private static final String utf = "UTF-8";
  
  static
  {
    isShowing = false;
  }
  
  protected CommonUtils() {}
  
  public static OrderDetails addCatelogRefId(OrderDetails paramOrderDetails1, OrderDetails paramOrderDetails2)
  {
    paramOrderDetails1 = paramOrderDetails1.getItems();
    Object localObject1 = paramOrderDetails2.getItems();
    int i = 0;
    while (i < ((List)localObject1).size())
    {
      localObject2 = (OrderItemModel)((List)localObject1).get(i);
      ((OrderItemModel)localObject2).setImageURL(((OrderItemModel)paramOrderDetails1.get(i)).getLargeImage());
      ((OrderItemModel)localObject2).setLargeImage(((OrderItemModel)paramOrderDetails1.get(i)).getLargeImage());
      ((OrderItemModel)localObject2).setCatalogRefId(((OrderItemModel)paramOrderDetails1.get(i)).getCatalogRefId());
      i += 1;
    }
    paramOrderDetails1 = new ArrayList();
    localObject1 = new ArrayList();
    Object localObject2 = paramOrderDetails2.getItems();
    if (paramOrderDetails2.getItems() != null)
    {
      ((List)localObject1).clear();
      int j = 0;
      if (j < ((List)localObject2).size())
      {
        if (((OrderItemModel)((List)localObject2).get(j)).getQuantity() > 1)
        {
          double d = ((OrderItemModel)((List)localObject2).get(j)).getPrice().doubleValue() / ((OrderItemModel)((List)localObject2).get(j)).getQuantity();
          ((OrderItemModel)((List)localObject2).get(j)).setPrice(Double.valueOf(d));
        }
        OrderItemModel localOrderItemModel1 = (OrderItemModel)((List)localObject2).get(j);
        if (((List)localObject1).isEmpty())
        {
          ((List)localObject1).add(localOrderItemModel1);
          paramOrderDetails1.add(localOrderItemModel1.getId());
        }
        for (;;)
        {
          j += 1;
          break;
          if (paramOrderDetails1.contains(localOrderItemModel1.getId()))
          {
            OrderItemModel localOrderItemModel2 = (OrderItemModel)((List)localObject1).get(((List)localObject1).size() - 1);
            if ((localOrderItemModel2.getId().equals(localOrderItemModel1.getId())) && (localOrderItemModel2.getCatalogRefId().equals(localOrderItemModel1.getCatalogRefId())))
            {
              if ((localOrderItemModel2.getOptions().isEmpty()) && (localOrderItemModel1.getOptions().isEmpty()))
              {
                localOrderItemModel2.setQuantity(localOrderItemModel2.getQuantity() + 1);
              }
              else if (localOrderItemModel2.getOptions().size() == localOrderItemModel1.getOptions().size())
              {
                i = 0;
                int k = 0;
                if (k < localOrderItemModel2.getOptions().size())
                {
                  if (((MenuOption)localOrderItemModel2.getOptions().get(k)).getCatalogRefId().equals(((MenuOption)localOrderItemModel1.getOptions().get(k)).getCatalogRefId())) {}
                  for (i = 1;; i = 0)
                  {
                    k += 1;
                    break;
                  }
                }
                if (i != 0)
                {
                  localOrderItemModel2.setQuantity(localOrderItemModel2.getQuantity() + 1);
                }
                else
                {
                  ((List)localObject1).add(localOrderItemModel1);
                  paramOrderDetails1.add(localOrderItemModel1.getId());
                }
              }
              else
              {
                ((List)localObject1).add(localOrderItemModel1);
                paramOrderDetails1.add(localOrderItemModel1.getId());
              }
            }
            else
            {
              ((List)localObject1).add(localOrderItemModel1);
              paramOrderDetails1.add(localOrderItemModel1.getId());
            }
          }
          else
          {
            ((List)localObject1).add(localOrderItemModel1);
            paramOrderDetails1.add(localOrderItemModel1.getId());
          }
        }
      }
      paramOrderDetails2.setItems((List)localObject1);
    }
    return paramOrderDetails2;
  }
  
  public static boolean addLocationSearchItem(Activity paramActivity, String paramString)
  {
    Object localObject1 = getStringFromPreferences(paramActivity);
    if (localObject1 != null)
    {
      localObject1 = Arrays.asList(convertStringToArray((String)localObject1));
      Collections.reverse((List)localObject1);
      String[] arrayOfString = (String[])((List)localObject1).toArray();
      Object localObject2 = null;
      int j = arrayOfString.length;
      int i = 0;
      if (i < j)
      {
        String str = arrayOfString[i];
        localObject1 = localObject2;
        if (!str.equalsIgnoreCase(paramString)) {
          if (localObject2 == null) {
            break label117;
          }
        }
        label117:
        for (localObject1 = (String)localObject2 + "," + str;; localObject1 = str)
        {
          i += 1;
          localObject2 = localObject1;
          break;
        }
      }
      if (localObject2 != null) {
        paramString = (String)localObject2 + "," + paramString;
      }
    }
    for (;;)
    {
      return putStringInPreferences(paramActivity, paramString);
    }
  }
  
  public static boolean addLunchDinnerTime(String paramString1, String paramString2)
  {
    SimpleDateFormat localSimpleDateFormat1 = new SimpleDateFormat("h:mma", Locale.US);
    SimpleDateFormat localSimpleDateFormat2 = new SimpleDateFormat("h:mma", Locale.US);
    localSimpleDateFormat2.setTimeZone(TimeZone.getTimeZone(paramString2));
    try
    {
      paramString2 = localSimpleDateFormat1.parse(paramString1);
      paramString1 = Calendar.getInstance();
      paramString1.setTime(paramString2);
      paramString1.set(12, -30);
      paramString2 = Calendar.getInstance();
      paramString2.setTime(localSimpleDateFormat1.parse(localSimpleDateFormat2.format(paramString2.getTime())));
      if (paramString2.getTime().after(paramString1.getTime()))
      {
        System.out.println(true);
        return true;
      }
      System.out.println(false);
      return false;
    }
    catch (ParseException paramString1)
    {
      LOG.e("CommonUtils", "Error: ", paramString1);
    }
    return false;
  }
  
  public static OrderDetails addSelectedId(OrderDetails paramOrderDetails, CreateOrders paramCreateOrders)
  {
    List localList1 = paramOrderDetails.getItems();
    int i = 0;
    while (i < localList1.size())
    {
      OrderItemModel localOrderItemModel = (OrderItemModel)localList1.get(i);
      List localList2 = paramCreateOrders.getMenus();
      int j = 0;
      while (j < localList2.size())
      {
        Object localObject = (OrderMenuItem)localList2.get(j);
        if (localOrderItemModel.getCatalogRefId().equals(((OrderMenuItem)localObject).getCatalogRefId()))
        {
          localObject = localOrderItemModel.getOptions();
          int k = 0;
          if (k < ((List)localObject).size())
          {
            String str1 = ((MenuOption)((List)localObject).get(k)).getCatalogRefId();
            List localList3 = ((OrderMenuItem)localList2.get(j)).getOptions();
            int m = 0;
            for (;;)
            {
              if (m < localList3.size())
              {
                String str2 = (String)localList3.get(m);
                if (str2.contains(str1)) {
                  ((MenuOption)((List)localObject).get(k)).setSelectedId(str2);
                }
              }
              else
              {
                k += 1;
                break;
              }
              m += 1;
            }
          }
        }
        j += 1;
      }
      i += 1;
    }
    return paramOrderDetails;
  }
  
  public static int appUpdateStatus(Context paramContext)
  {
    return getUpdateVersionStatus(getBuildNumber(paramContext), (SiteConfigModel)convertJsonToClass(PreferenceManager.SITE_SETTINGS.getStringValue(paramContext), SiteConfigModel.class));
  }
  
  public static boolean calculateGuestUserWaitListTime(String paramString1, String paramString2, String paramString3, boolean paramBoolean)
  {
    SimpleDateFormat localSimpleDateFormat1 = new SimpleDateFormat("hh:mm:ss", Locale.US);
    SimpleDateFormat localSimpleDateFormat2 = new SimpleDateFormat("hh:mm:ss", Locale.US);
    localSimpleDateFormat2.setTimeZone(TimeZone.getTimeZone(paramString3));
    try
    {
      paramString3 = localSimpleDateFormat1.parse(paramString1);
      paramString1 = Calendar.getInstance();
      paramString1.setTime(paramString3);
      if (paramBoolean) {
        paramString1.set(12, -30);
      }
      paramString3 = localSimpleDateFormat1.parse(paramString2);
      paramString2 = Calendar.getInstance();
      paramString2.setTime(paramString3);
      if (paramBoolean) {
        paramString2.set(12, -30);
      }
      paramString3 = Calendar.getInstance();
      paramString3.setTime(localSimpleDateFormat1.parse(localSimpleDateFormat2.format(paramString3.getTime())));
      paramString3 = paramString3.getTime();
      if (((paramString3.compareTo(paramString1.getTime()) == 0) || (paramString3.after(paramString1.getTime()))) && (paramString3.before(paramString2.getTime())))
      {
        System.out.println(true);
        return true;
      }
      System.out.println(false);
      return false;
    }
    catch (ParseException paramString1)
    {
      LOG.e("CommonUtils", "Error: ", paramString1);
    }
    return false;
  }
  
  public static String calculateMilesDistance(double paramDouble)
  {
    return String.format("%.2f", new Object[] { Double.valueOf(paramDouble) });
  }
  
  public static void cardEditText(EditText paramEditText)
  {
    paramEditText.addTextChangedListener(new CommonUtils.24());
  }
  
  public static void cardEditTextHypen(EditText paramEditText)
  {
    paramEditText.addTextChangedListener(new CommonUtils.26());
  }
  
  public static void cardEditTextSpace(EditText paramEditText)
  {
    paramEditText.addTextChangedListener(new CommonUtils.25());
  }
  
  public static void cardExpEditTextSlash(EditText paramEditText)
  {
    paramEditText.addTextChangedListener(new CommonUtils.23());
  }
  
  public static void cardSpaceEditText(EditText paramEditText)
  {
    paramEditText.addTextChangedListener(new CommonUtils.27());
  }
  
  public static void checkBoxAccessibilityFocus(View paramView, CheckBox paramCheckBox, String paramString)
  {
    if (paramCheckBox.isChecked()) {}
    for (paramCheckBox = paramString + " checkbox checked";; paramCheckBox = paramString + " checkbox not checked")
    {
      paramView.setContentDescription(paramCheckBox);
      return;
    }
  }
  
  public static boolean checkEndTime(String paramString1, String paramString2)
  {
    SimpleDateFormat localSimpleDateFormat1 = new SimpleDateFormat("h:mma", Locale.US);
    SimpleDateFormat localSimpleDateFormat2 = new SimpleDateFormat("h:mma", Locale.US);
    localSimpleDateFormat2.setTimeZone(TimeZone.getTimeZone(paramString2));
    try
    {
      paramString2 = localSimpleDateFormat1.parse(paramString1);
      paramString1 = Calendar.getInstance();
      paramString1.setTime(paramString2);
      paramString2 = Calendar.getInstance();
      paramString2.setTime(localSimpleDateFormat1.parse(localSimpleDateFormat2.format(paramString2.getTime())));
      if (paramString2.getTime().after(paramString1.getTime()))
      {
        System.out.println(true);
        return true;
      }
      System.out.println(false);
      return false;
    }
    catch (ParseException paramString1)
    {
      LOG.e("CommonUtils", "Error: ", paramString1);
    }
    return false;
  }
  
  public static boolean checkGooglePlayServices(Activity paramActivity)
  {
    boolean bool = true;
    int i = GooglePlayServicesUtil.isGooglePlayServicesAvailable(paramActivity);
    if (i != 0)
    {
      Dialog localDialog = GooglePlayServicesUtil.getErrorDialog(i, paramActivity, 1);
      localDialog.setOnDismissListener(new CommonUtils.11(paramActivity));
      localDialog.show();
      bool = false;
    }
    return bool;
  }
  
  public static boolean checkStartTime(String paramString1, String paramString2)
  {
    SimpleDateFormat localSimpleDateFormat1 = new SimpleDateFormat("h:mma", Locale.US);
    SimpleDateFormat localSimpleDateFormat2 = new SimpleDateFormat("h:mma", Locale.US);
    localSimpleDateFormat2.setTimeZone(TimeZone.getTimeZone(paramString2));
    try
    {
      paramString2 = localSimpleDateFormat1.parse(paramString1);
      paramString1 = Calendar.getInstance();
      paramString1.setTime(paramString2);
      paramString2 = Calendar.getInstance();
      paramString2.setTime(localSimpleDateFormat1.parse(localSimpleDateFormat2.format(paramString2.getTime())));
      if (paramString2.getTime().before(paramString1.getTime()))
      {
        System.out.println(true);
        return true;
      }
      System.out.println(false);
      return false;
    }
    catch (ParseException paramString1)
    {
      LOG.e("CommonUtils", "Error: ", paramString1);
    }
    return false;
  }
  
  public static boolean checkWaitlistHourAgaintsTimezone(String paramString1, String paramString2, String paramString3)
    throws ParseException
  {
    paramString3 = TimeZone.getTimeZone(paramString3);
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTimeZone(paramString3);
    int i = localCalendar.get(1);
    int j = localCalendar.get(2) + 1;
    int k = localCalendar.get(5);
    int m = localCalendar.get(11);
    int n = localCalendar.get(12);
    paramString3 = new SimpleDateFormat("HH:mm:ss yyyy/MM/dd", Locale.US);
    currentHour = paramString3.parse(m + ":" + n + ":00" + " " + i + "/" + j + "/" + k);
    openHour = paramString3.parse(paramString1 + " " + i + "/" + j + "/" + k);
    closeHour = paramString3.parse(paramString2 + " " + i + "/" + j + "/" + k);
    return (currentHour.after(openHour)) && (currentHour.before(closeHour));
  }
  
  public static String checkWaitlistStatusAgaintsTimezone(String paramString1, String paramString2, String paramString3)
    throws ParseException
  {
    int i = 0;
    try
    {
      boolean bool = checkWaitlistHourAgaintsTimezone(paramString1, paramString2, paramString3);
      i = bool;
    }
    catch (ParseException paramString1)
    {
      for (;;)
      {
        LOG.e("CommonUtils", "Error: ", paramString1);
      }
      if (!currentHour.before(openHour)) {
        break label50;
      }
      return "Open at";
      label50:
      if (!currentHour.after(openHour)) {
        break label66;
      }
      return "Closed";
    }
    if (i != 0) {
      return "Open";
    }
    label66:
    return "";
  }
  
  public static boolean clearAndSaveLatestCookie(Context paramContext, Headers paramHeaders)
  {
    PreferenceManager.SAVED_APP_COOKIE.setStringValue(paramContext, "");
    return saveLatestCookie(paramContext, paramHeaders);
  }
  
  public static <T> List<T> cloneArray(List<T> paramList, Class<T> paramClass)
  {
    ArrayList localArrayList = new ArrayList();
    paramList = paramList.iterator();
    while (paramList.hasNext()) {
      localArrayList.add(cloneObject(paramList.next(), paramClass));
    }
    return localArrayList;
  }
  
  public static <T> T cloneObject(T paramT, Class<T> paramClass)
  {
    return convertJsonToClass(convertClassToJson(paramT), paramClass);
  }
  
  public static void collapse(View paramView)
  {
    CommonUtils.17 local17 = new CommonUtils.17(paramView, paramView.getMeasuredHeight());
    local17.setDuration(500L);
    paramView.startAnimation(local17);
  }
  
  public static boolean confirmPasswordValidation(String paramString1, String paramString2)
  {
    return (!TextUtils.isEmpty(paramString1)) && (paramString1.equals(paramString2));
  }
  
  public static <T> String convertClassToJson(T paramT)
  {
    String str = null;
    if (paramT != null) {
      str = new Gson().toJson(paramT);
    }
    return str;
  }
  
  public static String convertDashSeparatedStringToWord(String paramString)
  {
    return convertSeparatedStringToWord(paramString, "-");
  }
  
  public static String convertDateForOrder(String paramString, boolean paramBoolean)
  {
    Object localObject = new SimpleDateFormat("MMM dd, yyyy", Locale.US);
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("M/dd/yyyy", Locale.US);
    if (paramBoolean) {}
    try
    {
      return ((SimpleDateFormat)localObject).format(localSimpleDateFormat.parse(paramString));
    }
    catch (ParseException localParseException)
    {
      localParseException.printStackTrace();
    }
    localObject = localSimpleDateFormat.format(((SimpleDateFormat)localObject).parse(paramString));
    return localObject;
    return paramString;
  }
  
  public static int convertDpToPixels(Context paramContext, int paramInt)
  {
    return (int)TypedValue.applyDimension(1, paramInt, paramContext.getResources().getDisplayMetrics());
  }
  
  public static String convertFromAmPmTo24Hours(String paramString)
  {
    Object localObject = "";
    SimpleDateFormat localSimpleDateFormat;
    if (!isEmptyTextOrNull(paramString))
    {
      localObject = paramString.substring(paramString.length() - 2);
      paramString = paramString.replace((CharSequence)localObject, " " + (String)localObject);
      localObject = new SimpleDateFormat("HH:mm");
      localSimpleDateFormat = new SimpleDateFormat("hh:mm a");
    }
    try
    {
      localObject = ((SimpleDateFormat)localObject).format(localSimpleDateFormat.parse(paramString));
      return localObject;
    }
    catch (ParseException paramString)
    {
      paramString.printStackTrace();
    }
    return "";
  }
  
  public static <T> T convertJsonToClass(String paramString, Class<T> paramClass)
    throws JsonSyntaxException
  {
    Object localObject = null;
    if (!TextUtils.isEmpty(paramString)) {}
    try
    {
      localObject = new Gson().fromJson(paramString, paramClass);
      return localObject;
    }
    catch (Exception paramString)
    {
      LOG.e("CommonUtils", "Error: ", paramString);
    }
    return null;
  }
  
  public static int convertPixelsToDp(Context paramContext, int paramInt)
  {
    return (int)TypedValue.applyDimension(0, paramInt, paramContext.getResources().getDisplayMetrics());
  }
  
  public static <T> T convertResponseStringToJsonClass(String paramString, Class<T> paramClass)
  {
    try
    {
      paramString = new Gson().fromJson(paramString, paramClass);
      return paramString;
    }
    catch (Exception paramString)
    {
      LOG.e("CommonUtils", "Error: ", paramString);
    }
    return null;
  }
  
  private static String convertSeparatedStringToWord(String paramString1, String paramString2)
  {
    if ((paramString1 != null) && (!paramString1.equalsIgnoreCase("")) && (paramString1.contains(paramString2)))
    {
      StringBuilder localStringBuilder = new StringBuilder();
      paramString1 = paramString1.split(paramString2);
      int j = paramString1.length;
      int i = 0;
      while (i < j)
      {
        String str = paramString1[i];
        if ((str != null) && (!str.equalsIgnoreCase("")))
        {
          paramString2 = (str.charAt(0) + "").toUpperCase();
          str = str.substring(1) + " ";
          localStringBuilder.append(paramString2 + str);
        }
        i += 1;
      }
      return localStringBuilder.toString();
    }
    return "";
  }
  
  public static <T> T convertStreamToJsonClass(Context paramContext, String paramString, Class<T> paramClass)
  {
    try
    {
      paramContext = new BufferedReader(new InputStreamReader(paramContext.getAssets().open(paramString), "UTF-8"));
      paramContext = new Gson().fromJson(paramContext, paramClass);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      LOG.e("CommonUtils", "Error: ", paramContext);
    }
    return null;
  }
  
  private static String[] convertStringToArray(String paramString)
  {
    paramString = Arrays.asList(paramString.split(","));
    Collections.reverse(paramString);
    return (String[])paramString.toArray();
  }
  
  public static String convertTimeForOrder(String paramString, boolean paramBoolean)
  {
    Object localObject = new SimpleDateFormat("hh:mm a", Locale.US);
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("hh:mma", Locale.US);
    if (paramBoolean) {}
    try
    {
      return ((SimpleDateFormat)localObject).format(localSimpleDateFormat.parse(paramString));
    }
    catch (ParseException localParseException)
    {
      localParseException.printStackTrace();
    }
    localObject = localSimpleDateFormat.format(((SimpleDateFormat)localObject).parse(paramString));
    return localObject;
    return paramString;
  }
  
  public static HashMap<String, String> convertToMap(List<String> paramList)
  {
    HashMap localHashMap = new HashMap();
    int i = 0;
    while (i < paramList.size())
    {
      String[] arrayOfString = ((String)paramList.get(i)).replaceFirst("=", "|").split(Pattern.quote("|"));
      String str = "";
      if (arrayOfString.length > 1) {
        str = arrayOfString[1];
      }
      if (arrayOfString.length > 0) {
        localHashMap.put(arrayOfString[0], str);
      }
      i += 1;
    }
    return localHashMap;
  }
  
  public static String convertUnderscoreSeparatedStringToWord(String paramString)
  {
    return convertSeparatedStringToWord(paramString, "_");
  }
  
  public static void createGiftCardJson(Context paramContext, BuyGiftCards paramBuyGiftCards)
  {
    paramBuyGiftCards = convertClassToJson(paramBuyGiftCards);
    PreferenceManager.PREFERENCE_GIFTCARD.setStringValue(paramContext, paramBuyGiftCards);
  }
  
  public static ProgressDialog createProgressDialog(Activity paramActivity, String paramString1, String paramString2)
  {
    paramActivity = new ProgressDialog(paramActivity);
    paramActivity.setMessage(paramString1);
    paramActivity.setTitle(paramString2);
    return paramActivity;
  }
  
  public static String decryptMessage(String paramString, Context paramContext)
  {
    AndroidCustomCrypto localAndroidCustomCrypto = AndroidCustomCrypto.getInstance();
    try
    {
      localAndroidCustomCrypto.initialize(paramContext);
      paramString = localAndroidCustomCrypto.decrypt(paramString);
      return paramString;
    }
    catch (Exception paramString)
    {
      LOG.e("CommonUtils", "Error: ", paramString);
    }
    return null;
  }
  
  public static void deleteStringValue(Context paramContext, String paramString)
  {
    if (paramContext != null)
    {
      paramContext = paramContext.getSharedPreferences("Darden", 0).edit();
      paramContext.remove(paramString);
      paramContext.apply();
      paramContext.commit();
    }
  }
  
  public static String displayFormattedZipCode(String paramString)
  {
    String str = paramString;
    if (paramString.length() > 6) {
      str = paramString.substring(0, 5);
    }
    return str;
  }
  
  private static void drawRoundedRect(Canvas paramCanvas, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    Paint localPaint = new Paint();
    localPaint.setStyle(Paint.Style.FILL);
    localPaint.setColor(-7829368);
    paramCanvas.drawCircle(25.0F, 25.0F, 25.0F, localPaint);
    paramCanvas.drawCircle(paramFloat3 - 25.0F, 25.0F, 25.0F, localPaint);
    paramCanvas.drawRect(paramFloat1 + 25.0F, paramFloat2, paramFloat3 - 25.0F, paramFloat4, localPaint);
  }
  
  public static void editTextAccessibiltyFocus(Context paramContext, View paramView1, View paramView2)
  {
    if (isAccessibilityEnabled(paramContext))
    {
      paramView2.setImportantForAccessibility(1);
      paramView2.sendAccessibilityEvent(32768);
      paramView2.requestFocus();
      new Handler().postDelayed(new CommonUtils.1(paramView1), 2000L);
    }
  }
  
  public static String encryptMessage(String paramString, Context paramContext)
  {
    AndroidCustomCrypto localAndroidCustomCrypto = AndroidCustomCrypto.getInstance();
    try
    {
      localAndroidCustomCrypto.initialize(paramContext);
      paramString = localAndroidCustomCrypto.encrypt(paramString);
      return paramString;
    }
    catch (Exception paramString)
    {
      LOG.e("CommonUtils", "Error: ", paramString);
    }
    return null;
  }
  
  public static void errorMessages(Context paramContext, int paramInt)
  {
    switch (paramInt)
    {
    }
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(paramContext);
    localBuilder.setMessage(paramContext.getString(R.string.not_in_demo)).setCancelable(false).setPositiveButton("OK", new CommonUtils.28());
    localBuilder.create().show();
  }
  
  public static void expand(View paramView)
  {
    paramView.measure(-1, -2);
    int i = paramView.getMeasuredHeight();
    paramView.getLayoutParams().height = 1;
    paramView.setVisibility(0);
    CommonUtils.16 local16 = new CommonUtils.16(paramView, i);
    local16.setDuration(500L);
    paramView.startAnimation(local16);
  }
  
  public static void expandOrCollapse(View paramView, int paramInt1, int paramInt2, CommonUtils.AnimationListener paramAnimationListener)
  {
    paramView.measure(-1, -2);
    int i = paramView.getMeasuredHeight();
    int j = paramView.getMeasuredHeight();
    paramView.getLayoutParams().height = 1;
    paramView.setVisibility(0);
    paramAnimationListener = new CommonUtils.12(paramInt1, paramView, i, paramAnimationListener, j);
    paramAnimationListener.setDuration(paramInt2);
    paramView.startAnimation(paramAnimationListener);
  }
  
  public static String extractHostFromURI(String paramString)
  {
    try
    {
      String str = new URI(paramString).getHost();
      return str;
    }
    catch (Exception localException)
    {
      LOG.e("CommonUtils", "Error: ", localException);
    }
    return paramString;
  }
  
  public static void fadeOutIn(int paramInt, ImageView paramImageView, CommonUtils.AnimationListener paramAnimationListener)
  {
    float f1 = 0.0F;
    float f2 = 0.0F;
    switch (paramInt)
    {
    }
    for (;;)
    {
      AlphaAnimation localAlphaAnimation = new AlphaAnimation(f1, f2);
      localAlphaAnimation.setInterpolator(new AccelerateInterpolator());
      localAlphaAnimation.setDuration(200L);
      localAlphaAnimation.setAnimationListener(new CommonUtils.13(paramInt, paramImageView, paramAnimationListener));
      paramImageView.startAnimation(localAlphaAnimation);
      return;
      paramImageView.setVisibility(0);
      f1 = 0.0F;
      f2 = 1.0F;
      continue;
      f1 = 1.0F;
      f2 = 0.0F;
    }
  }
  
  public static String filterCookie(String paramString)
  {
    String str2 = "";
    String str1 = str2;
    if (!isEmptyTextOrNull(paramString))
    {
      if (!paramString.contains("; ")) {
        break label45;
      }
      paramString = paramString.split("; ");
      str1 = str2;
      if (paramString.length > 1) {
        str1 = paramString[0];
      }
    }
    label45:
    do
    {
      return str1;
      if (!paramString.contains(";")) {
        break;
      }
      paramString = paramString.split(";");
      str1 = str2;
    } while (paramString.length < 1);
    return paramString[0];
    return paramString + ";";
  }
  
  public static String formatDistanceAndShowAsMiles(double paramDouble)
  {
    DecimalFormat localDecimalFormat = new DecimalFormat("#0.0");
    if (paramDouble < 1.0D) {
      localDecimalFormat = new DecimalFormat("#0.00");
    }
    localDecimalFormat.setRoundingMode(RoundingMode.DOWN);
    return localDecimalFormat.format(paramDouble) + " mi";
  }
  
  public static String formatDistanceAndShowAsMilesFind(double paramDouble)
  {
    DecimalFormat localDecimalFormat = new DecimalFormat("#0.0");
    if (paramDouble < 1.0D) {
      localDecimalFormat = new DecimalFormat("#0.00");
    }
    localDecimalFormat.setRoundingMode(RoundingMode.DOWN);
    return localDecimalFormat.format(paramDouble) + " MILES AWAY";
  }
  
  @TargetApi(21)
  public static String formatPhoneNumber(String paramString)
  {
    Object localObject1;
    if (paramString == null) {
      localObject1 = "";
    }
    do
    {
      return localObject1;
      if ((Build.VERSION.SDK_INT > 19) && (!paramString.startsWith("1")) && (!paramString.startsWith("0"))) {
        return PhoneNumberUtils.formatNumber(paramString, "US");
      }
      localObject1 = new StringBuilder();
      if ((!paramString.startsWith("1")) && (!paramString.startsWith("0"))) {
        break;
      }
      localObject2 = new MessageFormat("({0}) {1}-{2}");
      localObject1 = paramString;
    } while (paramString.length() <= 9);
    return ((MessageFormat)localObject2).format(new String[] { paramString.substring(0, 3), paramString.substring(3, 6), paramString.substring(6) });
    Object localObject2 = new SpannableStringBuilder(paramString);
    PhoneNumberUtils.formatNanpNumber((Editable)localObject2);
    String[] arrayOfString = localObject2.toString().split("-");
    if (arrayOfString.length == 1) {
      ((StringBuilder)localObject1).append((CharSequence)localObject2);
    }
    for (;;)
    {
      return ((StringBuilder)localObject1).toString();
      if (arrayOfString.length == 3)
      {
        ((StringBuilder)localObject1).append("(");
        ((StringBuilder)localObject1).append(arrayOfString[0]);
        ((StringBuilder)localObject1).append(") ");
        ((StringBuilder)localObject1).append(arrayOfString[1]);
        ((StringBuilder)localObject1).append("-");
        ((StringBuilder)localObject1).append(arrayOfString[2]);
      }
      else
      {
        ((StringBuilder)localObject1).append(paramString);
      }
    }
  }
  
  public static String formatString(Context paramContext, int paramInt, String paramString)
  {
    return String.format(paramContext.getResources().getString(paramInt), new Object[] { paramString });
  }
  
  public static String formatTime(String paramString)
  {
    try
    {
      String[] arrayOfString = paramString.split(":");
      String str = paramString;
      if (arrayOfString.length > 1)
      {
        str = arrayOfString[1];
        str = arrayOfString[0] + str.substring(2);
      }
      return str;
    }
    catch (Exception localException)
    {
      LOG.e("CommonUtils", "Error: ", localException);
    }
    return paramString;
  }
  
  public static Spanned fromHtml(String paramString)
  {
    if (Build.VERSION.SDK_INT >= 24) {
      return Html.fromHtml(paramString, 0);
    }
    return Html.fromHtml(paramString);
  }
  
  public static Activity getActivity()
  {
    try
    {
      Object localObject2 = Class.forName("android.app.ActivityThread");
      Object localObject1 = ((Class)localObject2).getMethod("currentActivityThread", new Class[0]).invoke(null, new Object[0]);
      localObject2 = ((Class)localObject2).getDeclaredField("mActivities");
      ((Field)localObject2).setAccessible(true);
      localObject1 = (Map)((Field)localObject2).get(localObject1);
      if (localObject1 == null) {
        return null;
      }
      localObject2 = ((Map)localObject1).values().iterator();
      while (((Iterator)localObject2).hasNext())
      {
        localObject1 = ((Iterator)localObject2).next();
        Class localClass = localObject1.getClass();
        Field localField = localClass.getDeclaredField("paused");
        localField.setAccessible(true);
        if (!localField.getBoolean(localObject1))
        {
          localObject2 = localClass.getDeclaredField("activity");
          ((Field)localObject2).setAccessible(true);
          localObject1 = (Activity)((Field)localObject2).get(localObject1);
          return localObject1;
        }
      }
    }
    catch (Exception localException)
    {
      LOG.e("CommonUtils", "Error: ", localException);
    }
    return null;
  }
  
  public static String getAddDateExpired(String paramString, int paramInt)
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.set(12, getRoundUpMinute(localCalendar.get(12) + paramInt));
    return new SimpleDateFormat(paramString, Locale.getDefault()).format(localCalendar.getTime());
  }
  
  public static String getApplicationVersionCode(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    try
    {
      paramContext = localPackageManager.getPackageInfo(paramContext.getPackageName(), 0).versionName;
      return paramContext;
    }
    catch (Exception paramContext)
    {
      LOG.e("CommonUtils", "Error: ", paramContext);
    }
    return "1";
  }
  
  public static boolean getBooleanValue(Context paramContext, String paramString)
  {
    boolean bool = false;
    if (paramContext != null) {
      bool = paramContext.getSharedPreferences("Darden", 0).getBoolean(paramString.toLowerCase(Locale.ENGLISH), false);
    }
    return bool;
  }
  
  private static String getBuildNumber(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      LOG.e("Error: ", paramContext);
    }
    return "";
  }
  
  public static String getCompleteTheSurveyURL(Context paramContext, String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder("https://www.research.net/r/LHTGAndroidApp");
    localStringBuilder.append("?");
    localStringBuilder.append("x");
    localStringBuilder.append("=");
    localStringBuilder.append(paramString);
    localStringBuilder.append("&");
    localStringBuilder.append("i");
    localStringBuilder.append("=");
    localStringBuilder.append(paramContext.getPackageName());
    return localStringBuilder.toString();
  }
  
  public static String getCreditCardType(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return "";
    case 0: 
      return "VISA";
    case 1: 
      return "MASTERCARD";
    case 2: 
      return "DISCOVER";
    }
    return "AMEX";
  }
  
  public static LanguageType getCurrentLanguage(Context paramContext)
  {
    if ((paramContext != null) && (PreferenceManager.LANGUAGE.getIntegerValue(paramContext) == Permissions.LANGUAGE_SPANISH.getValue())) {
      return LanguageType.SPANISH;
    }
    return LanguageType.ENGLISH;
  }
  
  public static Locale getCurrentLocale(Context paramContext)
  {
    Locale localLocale = Locale.US;
    if (PreferenceManager.LANGUAGE.getIntegerValue(paramContext) == Permissions.LANGUAGE_SPANISH.getValue()) {
      localLocale = new Locale("es", "ES");
    }
    return localLocale;
  }
  
  public static HashMap<String, String> getCurrentWeekDate()
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.set(7, 2);
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("EEEE dd/MM/yyyy", Locale.US);
    HashMap localHashMap = new HashMap();
    int i = 0;
    while (i < 7)
    {
      localHashMap.put(localSimpleDateFormat.format(localCalendar.getTime()).split(" ")[1], localSimpleDateFormat.format(localCalendar.getTime()).split(" ")[0]);
      localCalendar.add(5, 1);
      i += 1;
    }
    return localHashMap;
  }
  
  public static String getDateForOrder(boolean paramBoolean)
  {
    if (paramBoolean) {
      return new SimpleDateFormat("M/dd/yyyy").format(new Date());
    }
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("M/dd/yyyy");
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTime(new Date());
    localCalendar.add(5, 1);
    return localSimpleDateFormat.format(localCalendar.getTime());
  }
  
  public static Calendar getDateFromPicker(int paramInt1, int paramInt2, int paramInt3)
  {
    Calendar localCalendar1 = Calendar.getInstance();
    localCalendar1.add(1, -13);
    Calendar localCalendar2 = Calendar.getInstance();
    localCalendar2.set(1, paramInt1);
    localCalendar2.set(2, paramInt2);
    localCalendar2.set(5, paramInt3);
    if (localCalendar2.after(localCalendar1)) {
      localCalendar2.set(localCalendar1.get(1), localCalendar1.get(2), localCalendar1.get(5));
    }
    return localCalendar2;
  }
  
  private static void getDeepChildOffset(ViewGroup paramViewGroup, ViewParent paramViewParent, View paramView, Point paramPoint)
  {
    paramViewParent = (ViewGroup)paramViewParent;
    paramPoint.x += paramView.getLeft();
    paramPoint.y += paramView.getTop();
    if (paramViewParent.equals(paramViewGroup)) {
      return;
    }
    getDeepChildOffset(paramViewGroup, paramViewParent.getParent(), paramViewParent, paramPoint);
  }
  
  public static EditText getDefaultHint(EditText paramEditText, String paramString)
  {
    paramEditText.setOnTouchListener(new CommonUtils.14(paramEditText));
    paramEditText.setOnFocusChangeListener(new CommonUtils.15(paramEditText, paramString));
    return paramEditText;
  }
  
  public static int getDeviceHeight(Activity paramActivity)
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    paramActivity.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics.heightPixels;
  }
  
  public static int getDeviceWidth(Activity paramActivity)
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    paramActivity.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics.widthPixels;
  }
  
  /* Error */
  public static Intent getDirectionsIntent(Context paramContext, double paramDouble1, double paramDouble2, String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 6
    //   3: aload_0
    //   4: invokestatic 1266	com/ocf/droid/common/utils/GPSUtils:getInstance	(Landroid/content/Context;)Lcom/ocf/droid/common/utils/GPSUtils;
    //   7: invokevirtual 1270	com/ocf/droid/common/utils/GPSUtils:getCurrentLocation	()Landroid/location/Location;
    //   10: astore 7
    //   12: aload 7
    //   14: ifnull +172 -> 186
    //   17: new 1272	android/content/Intent
    //   20: dup
    //   21: ldc_w 1274
    //   24: new 235	java/lang/StringBuilder
    //   27: dup
    //   28: invokespecial 236	java/lang/StringBuilder:<init>	()V
    //   31: ldc_w 1276
    //   34: invokevirtual 240	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   37: aload 7
    //   39: invokevirtual 1281	android/location/Location:getLatitude	()D
    //   42: invokevirtual 1284	java/lang/StringBuilder:append	(D)Ljava/lang/StringBuilder;
    //   45: ldc -14
    //   47: invokevirtual 240	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   50: aload 7
    //   52: invokevirtual 1287	android/location/Location:getLongitude	()D
    //   55: invokevirtual 1284	java/lang/StringBuilder:append	(D)Ljava/lang/StringBuilder;
    //   58: ldc_w 1289
    //   61: invokevirtual 240	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   64: dload_1
    //   65: invokevirtual 1284	java/lang/StringBuilder:append	(D)Ljava/lang/StringBuilder;
    //   68: ldc -14
    //   70: invokevirtual 240	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   73: dload_3
    //   74: invokevirtual 1284	java/lang/StringBuilder:append	(D)Ljava/lang/StringBuilder;
    //   77: invokevirtual 245	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   80: invokestatic 1294	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   83: invokespecial 1297	android/content/Intent:<init>	(Ljava/lang/String;Landroid/net/Uri;)V
    //   86: astore 5
    //   88: aload 5
    //   90: ldc_w 1299
    //   93: invokevirtual 1303	android/content/Intent:setPackage	(Ljava/lang/String;)Landroid/content/Intent;
    //   96: pop
    //   97: aload 5
    //   99: aload_0
    //   100: invokevirtual 1085	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   103: invokevirtual 1307	android/content/Intent:resolveActivity	(Landroid/content/pm/PackageManager;)Landroid/content/ComponentName;
    //   106: ifnonnull +212 -> 318
    //   109: aload 7
    //   111: ifnull +125 -> 236
    //   114: new 1272	android/content/Intent
    //   117: dup
    //   118: ldc_w 1274
    //   121: new 235	java/lang/StringBuilder
    //   124: dup
    //   125: invokespecial 236	java/lang/StringBuilder:<init>	()V
    //   128: ldc_w 1276
    //   131: invokevirtual 240	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   134: aload 7
    //   136: invokevirtual 1281	android/location/Location:getLatitude	()D
    //   139: invokevirtual 1284	java/lang/StringBuilder:append	(D)Ljava/lang/StringBuilder;
    //   142: ldc -14
    //   144: invokevirtual 240	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   147: aload 7
    //   149: invokevirtual 1287	android/location/Location:getLongitude	()D
    //   152: invokevirtual 1284	java/lang/StringBuilder:append	(D)Ljava/lang/StringBuilder;
    //   155: ldc_w 1289
    //   158: invokevirtual 240	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   161: dload_1
    //   162: invokevirtual 1284	java/lang/StringBuilder:append	(D)Ljava/lang/StringBuilder;
    //   165: ldc -14
    //   167: invokevirtual 240	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   170: dload_3
    //   171: invokevirtual 1284	java/lang/StringBuilder:append	(D)Ljava/lang/StringBuilder;
    //   174: invokevirtual 245	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   177: invokestatic 1294	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   180: invokespecial 1297	android/content/Intent:<init>	(Ljava/lang/String;Landroid/net/Uri;)V
    //   183: astore_0
    //   184: aload_0
    //   185: areturn
    //   186: new 1272	android/content/Intent
    //   189: dup
    //   190: ldc_w 1274
    //   193: getstatic 262	java/util/Locale:US	Ljava/util/Locale;
    //   196: ldc_w 1309
    //   199: iconst_3
    //   200: anewarray 4	java/lang/Object
    //   203: dup
    //   204: iconst_0
    //   205: dload_1
    //   206: invokestatic 165	java/lang/Double:valueOf	(D)Ljava/lang/Double;
    //   209: aastore
    //   210: dup
    //   211: iconst_1
    //   212: dload_3
    //   213: invokestatic 165	java/lang/Double:valueOf	(D)Ljava/lang/Double;
    //   216: aastore
    //   217: dup
    //   218: iconst_2
    //   219: aload 5
    //   221: aastore
    //   222: invokestatic 1312	java/lang/String:format	(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   225: invokestatic 1294	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   228: invokespecial 1297	android/content/Intent:<init>	(Ljava/lang/String;Landroid/net/Uri;)V
    //   231: astore 5
    //   233: goto -145 -> 88
    //   236: new 1272	android/content/Intent
    //   239: dup
    //   240: ldc_w 1274
    //   243: new 235	java/lang/StringBuilder
    //   246: dup
    //   247: invokespecial 236	java/lang/StringBuilder:<init>	()V
    //   250: ldc_w 1314
    //   253: invokevirtual 240	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   256: dload_1
    //   257: invokevirtual 1284	java/lang/StringBuilder:append	(D)Ljava/lang/StringBuilder;
    //   260: ldc -14
    //   262: invokevirtual 240	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   265: dload_3
    //   266: invokevirtual 1284	java/lang/StringBuilder:append	(D)Ljava/lang/StringBuilder;
    //   269: invokevirtual 245	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   272: invokestatic 1294	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   275: invokespecial 1297	android/content/Intent:<init>	(Ljava/lang/String;Landroid/net/Uri;)V
    //   278: astore_0
    //   279: aload_0
    //   280: areturn
    //   281: astore_0
    //   282: aload 6
    //   284: astore 5
    //   286: ldc 46
    //   288: new 235	java/lang/StringBuilder
    //   291: dup
    //   292: invokespecial 236	java/lang/StringBuilder:<init>	()V
    //   295: ldc_w 321
    //   298: invokevirtual 240	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   301: aload_0
    //   302: invokevirtual 1317	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   305: invokevirtual 245	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   308: invokestatic 1319	com/ocf/droid/common/utils/LOG:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   311: aload 5
    //   313: areturn
    //   314: astore_0
    //   315: goto -29 -> 286
    //   318: aload 5
    //   320: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	321	0	paramContext	Context
    //   0	321	1	paramDouble1	double
    //   0	321	3	paramDouble2	double
    //   0	321	5	paramString	String
    //   1	282	6	localObject	Object
    //   10	138	7	localLocation	Location
    // Exception table:
    //   from	to	target	type
    //   3	12	281	java/lang/Exception
    //   17	88	281	java/lang/Exception
    //   186	233	281	java/lang/Exception
    //   88	109	314	java/lang/Exception
    //   114	184	314	java/lang/Exception
    //   236	279	314	java/lang/Exception
  }
  
  public static double getDistance(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4)
  {
    Location localLocation1 = new Location("locationA");
    localLocation1.setLatitude(paramDouble1);
    localLocation1.setLongitude(paramDouble2);
    Location localLocation2 = new Location("locationA");
    localLocation2.setLatitude(paramDouble3);
    localLocation2.setLongitude(paramDouble4);
    return localLocation1.distanceTo(localLocation2) * 6.2137119E-4D;
  }
  
  public static String getDistance(Context paramContext, double paramDouble1, double paramDouble2)
  {
    paramContext = GPSUtils.getInstance(paramContext).getCurrentLocation();
    try
    {
      Location localLocation = new Location("RESTAURANT_LOCATION");
      localLocation.setLatitude(paramDouble1);
      localLocation.setLongitude(paramDouble2);
      paramContext = calculateMilesDistance(paramContext.distanceTo(localLocation) * 6.21371E-4D);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      Log.e("ERROR", "an exception was thrown", paramContext);
    }
    return "0.0";
  }
  
  public static String getEllipsesText(String paramString, int paramInt)
  {
    String str = "";
    if (!isEmptyTextOrNull(paramString))
    {
      if (paramString.length() > paramInt) {
        str = paramString.substring(0, paramInt) + "...";
      }
    }
    else {
      return str;
    }
    return paramString;
  }
  
  public static String getEmail(Context paramContext)
  {
    try
    {
      paramContext = SimpleCrypto.getInstance().decrypt(paramContext, "Welcome123", PreferenceManager.USER_EMAIL.getStringValue(paramContext));
      return paramContext;
    }
    catch (Exception paramContext)
    {
      LOG.e("CommonUtils", "Error: ", paramContext);
    }
    return null;
  }
  
  public static long getEpochTime()
  {
    return System.currentTimeMillis() / 1000L;
  }
  
  public static ErrorModel getErrorModel(ResponseBody paramResponseBody)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (paramResponseBody != null) {}
    try
    {
      Object localObject3 = paramResponseBody.source();
      localObject1 = localObject2;
      if (localObject3 != null)
      {
        ((BufferedSource)localObject3).request(Long.MAX_VALUE);
        localObject3 = ((BufferedSource)localObject3).buffer();
        localObject1 = Charset.forName("UTF8");
        MediaType localMediaType = paramResponseBody.contentType();
        paramResponseBody = (ResponseBody)localObject1;
        if (localMediaType != null) {
          paramResponseBody = localMediaType.charset(Charset.forName("UTF8"));
        }
        paramResponseBody = ((Buffer)localObject3).clone().readString(paramResponseBody);
        localObject1 = localObject2;
        if (!TextUtils.isEmpty(paramResponseBody)) {
          localObject1 = (ErrorModel)new GsonBuilder().create().fromJson(paramResponseBody, ErrorModel.class);
        }
      }
      return localObject1;
    }
    catch (Exception paramResponseBody)
    {
      LOG.e("CommonUtils", "Impossible to get ErrorModel stream", paramResponseBody);
    }
    return null;
  }
  
  public static String getFormattedZipcode(String paramString)
  {
    String str = paramString;
    if (paramString.matches(".*\\d+.*")) {
      str = "zipcode " + paramString;
    }
    return str;
  }
  
  public static List<Address> getGeoAddressList(Context paramContext, String paramString)
  {
    if (Geocoder.isPresent()) {
      try
      {
        Geocoder localGeocoder = new Geocoder(paramContext, Locale.US);
        paramContext = paramString;
        if (paramString.matches("\\d+")) {
          paramContext = "zipcode " + paramString;
        }
        paramContext = localGeocoder.getFromLocationName(paramContext, 5);
        return paramContext;
      }
      catch (Exception paramContext)
      {
        LOG.e("CommonUtils", "Error: ", paramContext);
      }
    }
    return null;
  }
  
  public static String getHierarchicalRestaurant(Context paramContext)
  {
    Object localObject = PreferenceManager.NEAREST_RESTAURANT.getStringValue(paramContext);
    String str1 = PreferenceManager.PREFERRED_RESTAURANT.getStringValue(paramContext);
    String str2 = PreferenceManager.CURRENT_RESTAURANT.getStringValue(paramContext);
    if (!isEmptyTextOrNull(str2)) {
      localObject = str2;
    }
    for (;;)
    {
      PreferenceManager.CURRENT_RESTAURANT.setStringValue(paramContext, (String)localObject);
      return localObject;
      if (!isEmptyTextOrNull(str1)) {
        localObject = str1;
      }
    }
  }
  
  private static String getHolidayMessage(RestaurantHourInfo paramRestaurantHourInfo)
  {
    if (paramRestaurantHourInfo.getBrandMessage() != null)
    {
      paramRestaurantHourInfo = paramRestaurantHourInfo.getBrandMessage().getMainContent();
      if ((paramRestaurantHourInfo != null) && (!paramRestaurantHourInfo.isEmpty()))
      {
        paramRestaurantHourInfo = paramRestaurantHourInfo.iterator();
        while (paramRestaurantHourInfo.hasNext())
        {
          String str = ((BrandMessage.MainContent)paramRestaurantHourInfo.next()).getContent();
          if (str != null)
          {
            str = Html.fromHtml(str).toString();
            if (str != null) {
              return str.replace("\n", "").replace("\t", " ").replaceAll("\\s+", " ").trim();
            }
          }
        }
      }
    }
    return null;
  }
  
  public static Drawable getImageFromAssets(Context paramContext, String paramString)
  {
    paramString = "www/default/" + paramString;
    try
    {
      paramContext = Drawable.createFromStream(paramContext.getAssets().open(paramString), null);
      return paramContext;
    }
    catch (IOException paramContext)
    {
      LOG.e("CommonUtils", "Error: ", paramContext);
    }
    return null;
  }
  
  public static Map<String, String> getLatLngMapFromAddress(Context paramContext, String paramString)
  {
    String str = getFormattedZipcode(paramString);
    paramString = new HashMap();
    paramContext = new Geocoder(paramContext, Locale.getDefault());
    try
    {
      paramContext = paramContext.getFromLocationName(str, 1);
      if ((paramContext != null) && (!paramContext.isEmpty()))
      {
        paramContext = (Address)paramContext.get(0);
        paramString.put("keys.KEY_LATITUDE", String.valueOf(paramContext.getLatitude()));
        paramString.put("keys.KEY_LONGITUDE", String.valueOf(paramContext.getLongitude()));
        paramString.put("keys.KEY_ZIPCODE", paramContext.getPostalCode());
        paramString.put("keys.KEY_CITYNAME", String.valueOf(paramContext.getLocality()));
        return paramString;
      }
      return null;
    }
    catch (IOException paramContext)
    {
      LOG.e("CommonUtils", "Error: ", paramContext);
    }
    return null;
  }
  
  public static List<LatLng> getLatLong(Context paramContext, String paramString)
  {
    Object localObject1 = null;
    localObject2 = null;
    if (Geocoder.isPresent()) {}
    try
    {
      paramString = new Geocoder(paramContext, Locale.getDefault()).getFromLocationName(paramString, 5);
      paramContext = new ArrayList(paramString.size());
      try
      {
        paramString = paramString.iterator();
        while (paramString.hasNext())
        {
          localObject1 = (Address)paramString.next();
          if ((((Address)localObject1).hasLatitude()) && (((Address)localObject1).hasLongitude())) {
            paramContext.add(new LatLng(((Address)localObject1).getLatitude(), ((Address)localObject1).getLongitude()));
          }
        }
        LOG.e("CommonUtils", "Error: ", paramString);
      }
      catch (IOException paramString) {}
    }
    catch (IOException paramString)
    {
      for (;;)
      {
        paramContext = localObject2;
      }
    }
    localObject1 = paramContext;
    return localObject1;
    return paramContext;
  }
  
  public static List<String> getListOfNewCookies(Headers paramHeaders)
  {
    ArrayList localArrayList = new ArrayList();
    paramHeaders = paramHeaders.values("Set-Cookie");
    int i = 0;
    while (i < paramHeaders.size())
    {
      localArrayList.add(i, filterCookie((String)paramHeaders.get(i)));
      i += 1;
    }
    return localArrayList;
  }
  
  public static String[] getLocationSearchList(Activity paramActivity)
  {
    String str = getStringFromPreferences(paramActivity);
    paramActivity = paramActivity.getString(R.string.curr_location);
    if (str == null) {
      return convertStringToArray(paramActivity);
    }
    return convertStringToArray(str + "," + paramActivity);
  }
  
  public static String getNextDay()
  {
    Calendar localCalendar = Calendar.getInstance();
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
    localCalendar.add(7, 1);
    return String.valueOf(localSimpleDateFormat.format(localCalendar.getTime()));
  }
  
  public static int getOrderTime(RestaurantDetail paramRestaurantDetail, String paramString1, String paramString2, String paramString3)
  {
    if ((paramRestaurantDetail != null) && (!isEmptyTextOrNull(paramRestaurantDetail.getTimeZoneInUTC())))
    {
      SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("HH:mm");
      localSimpleDateFormat.setTimeZone(TimeZone.getTimeZone(paramRestaurantDetail.getTimeZoneInUTC()));
      int i = Integer.parseInt(localSimpleDateFormat.format(Calendar.getInstance().getTime()).replace(":", ""));
      if (!isEmptyTextOrNull(paramString1))
      {
        int j = Integer.parseInt(convertFromAmPmTo24Hours(paramString2).replace(":", ""));
        if ((i < Integer.parseInt(convertFromAmPmTo24Hours(paramString3).replace(":", ""))) || (i >= j)) {}
      }
      else
      {
        return 1;
      }
      return 2;
    }
    return 2;
  }
  
  public static RequestBody getRequestBody(Context paramContext, Object paramObject)
  {
    if (PreferenceManager.CONFIG_MSG_ENCRYPTION_ENABLED.getBooleanValue(paramContext)) {
      return RequestBody.create(MediaType.parse("application/xml; charset=utf-8"), encryptMessage(convertClassToJson(paramObject), paramContext));
    }
    return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), convertClassToJson(paramObject));
  }
  
  public static RequestBody getRequestBodyResetPassword(Context paramContext, Object paramObject)
  {
    if (PreferenceManager.CONFIG_MSG_ENCRYPTION_ENABLED.getBooleanValue(paramContext))
    {
      Gson localGson = new GsonBuilder().disableHtmlEscaping().create();
      return RequestBody.create(MediaType.parse("application/xml; charset=utf-8"), encryptMessage(localGson.toJson(paramObject), paramContext));
    }
    return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), convertClassToJson(paramObject));
  }
  
  public static RequestBody getRequestBodyVerifyPassword(Context paramContext, Object paramObject)
  {
    LOG.d(CommonUtils.class.getSimpleName(), "REq: " + convertClassToJson(paramObject));
    return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), convertClassToJson(paramObject));
  }
  
  public static RequestBody getRequestBodyXML(Context paramContext, String paramString)
  {
    return RequestBody.create(MediaType.parse("application/xml; charset=utf-8"), paramString);
  }
  
  public static String getRestaurantAddress(RestaurantDetail paramRestaurantDetail)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if (paramRestaurantDetail != null)
    {
      localStringBuilder.append(paramRestaurantDetail.getName());
      localStringBuilder.append(System.lineSeparator());
      localStringBuilder.append(paramRestaurantDetail.getAddress().getLineOne());
      localStringBuilder.append(System.lineSeparator());
      localStringBuilder.append(paramRestaurantDetail.getAddress().getCity());
      localStringBuilder.append(", ");
      localStringBuilder.append(paramRestaurantDetail.getAddress().getState());
      localStringBuilder.append(" ");
      localStringBuilder.append(displayFormattedZipCode(paramRestaurantDetail.getAddress().getZipCode()));
    }
    return localStringBuilder.toString();
  }
  
  public static int getRoundUpMinute(int paramInt)
  {
    if (paramInt % 10 != 0) {
      return 10 - paramInt % 10 + paramInt;
    }
    return paramInt;
  }
  
  public static SiteConfigModel getSiteConfig(Context paramContext)
  {
    String str = PreferenceManager.SITE_SETTINGS.getStringValue(paramContext);
    if ((str != null) && (!str.isEmpty())) {
      return (SiteConfigModel)convertJsonToClass(str, SiteConfigModel.class);
    }
    return (SiteConfigModel)convertStreamToJsonClass(paramContext, "siteConfig.json", SiteConfigModel.class);
  }
  
  public static SpannableString getSpanText(String paramString, boolean paramBoolean)
  {
    SpannableString localSpannableString = new SpannableString(paramString);
    if ((paramBoolean) && (paramString != null)) {
      localSpannableString.setSpan(new UnderlineSpan(), 0, paramString.length(), 0);
    }
    return localSpannableString;
  }
  
  public static String getStateCode(String paramString, List<StateModel> paramList)
  {
    int i = 0;
    while (i < paramList.size())
    {
      if ((((StateModel)paramList.get(i)).getState().equalsIgnoreCase(paramString)) || (((StateModel)paramList.get(i)).getCode().equalsIgnoreCase(paramString))) {
        return ((StateModel)paramList.get(i)).getCode();
      }
      i += 1;
    }
    return "";
  }
  
  private static String getStringFromPreferences(Activity paramActivity)
  {
    return PreferenceManager.RECENT_SEARCH_RESTAURANT.getStringValue(paramActivity);
  }
  
  public static String getStringValue(Context paramContext, String paramString)
  {
    if (paramContext != null) {
      return paramContext.getSharedPreferences("Darden", 0).getString(paramString, "");
    }
    return "";
  }
  
  public static String getTimeForOrder(String paramString1, String paramString2, boolean paramBoolean)
  {
    SimpleDateFormat localSimpleDateFormat1 = new SimpleDateFormat("hh:mm a", Locale.US);
    if (paramBoolean)
    {
      localSimpleDateFormat1.setTimeZone(TimeZone.getTimeZone(paramString2));
      long l1 = System.currentTimeMillis();
      int i = (int)(l1 / 60000L % 60L);
      long l2 = 60000 * roundOffMin(i);
      long l3 = 60000 * i;
      paramString1 = new Date();
      paramString1.setTime(l1 - l3 + l2 + 2400000L);
      return localSimpleDateFormat1.format(paramString1);
    }
    SimpleDateFormat localSimpleDateFormat2 = new SimpleDateFormat("hh:mma", Locale.US);
    paramString2 = null;
    try
    {
      paramString1 = localSimpleDateFormat1.format(localSimpleDateFormat2.parse(paramString1));
      return paramString1;
    }
    catch (ParseException paramString1)
    {
      for (;;)
      {
        LOG.e("CommonUtils", "Error: ", paramString1);
        paramString1 = paramString2;
      }
    }
  }
  
  public static float getTotalPaymentForTip(float paramFloat1, float paramFloat2, float paramFloat3, boolean paramBoolean1, boolean paramBoolean2)
  {
    float f = paramFloat1;
    paramFloat1 = f;
    if (paramBoolean1) {
      paramFloat1 = f + paramFloat2;
    }
    paramFloat2 = paramFloat1;
    if (paramBoolean2)
    {
      paramFloat2 = paramFloat1;
      if (paramFloat3 != 0.0F) {
        paramFloat2 = paramFloat1 + paramFloat3;
      }
    }
    return paramFloat2;
  }
  
  public static Typeface getTypeface(Context paramContext, String paramString)
  {
    try
    {
      paramString = "fonts/" + paramString;
      paramContext = Typeface.createFromAsset(paramContext.getAssets(), paramString);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      LOG.e("CommonUtils", "Error: ", paramContext);
    }
    return Typeface.DEFAULT;
  }
  
  private static int getUpdateVersionLabel(String paramString1, String paramString2, int paramInt)
  {
    paramString1 = paramString1.split(",");
    int i = 0;
    while (i < paramString1.length)
    {
      if (paramString1[i].contains(Pattern.quote("*")))
      {
        if (!paramString2.contains(paramString1[i].replace("*", ""))) {}
      }
      else {
        while (paramString2.equalsIgnoreCase(paramString1[i])) {
          return paramInt;
        }
      }
      i += 1;
    }
    return 0;
  }
  
  private static int getUpdateVersionStatus(String paramString, SiteConfigModel paramSiteConfigModel)
  {
    String[] arrayOfString = null;
    int k = 0;
    int i = k;
    Object localObject;
    int j;
    if (paramSiteConfigModel != null)
    {
      i = k;
      if (paramSiteConfigModel.getAppUpdate() != null)
      {
        i = k;
        if (paramSiteConfigModel.getAppUpdate().getVersionInfo() != null)
        {
          i = 0;
          localObject = arrayOfString;
          if (i < paramSiteConfigModel.getAppUpdate().getVersionInfo().size())
          {
            if (!((VersionInfo)paramSiteConfigModel.getAppUpdate().getVersionInfo().get(i)).getOS().equalsIgnoreCase("Android")) {
              break label211;
            }
            localObject = (VersionInfo)paramSiteConfigModel.getAppUpdate().getVersionInfo().get(i);
          }
          i = k;
          if (localObject != null)
          {
            paramSiteConfigModel = ((VersionInfo)localObject).getMinVersion().split(Pattern.quote("."));
            arrayOfString = paramString.split(Pattern.quote("."));
            j = 0;
          }
        }
      }
    }
    for (;;)
    {
      i = k;
      if (j < arrayOfString.length)
      {
        if ((paramSiteConfigModel.length <= j) || (paramSiteConfigModel[j] == null) || (arrayOfString[j] == null) || (Integer.parseInt(arrayOfString[j]) >= Integer.parseInt(paramSiteConfigModel[j]))) {
          break label218;
        }
        j = getUpdateVersionLabel(((VersionInfo)localObject).getForceVersions(), paramString, 1);
        i = j;
        if (j != 1) {
          i = getUpdateVersionLabel(((VersionInfo)localObject).getNotifyVersions(), paramString, 2);
        }
      }
      label211:
      label218:
      do
      {
        do
        {
          return i;
          i += 1;
          break;
          if (paramSiteConfigModel.length <= j) {
            break label243;
          }
          i = k;
        } while (paramSiteConfigModel[j] == null);
        i = k;
      } while (arrayOfString[j] == null);
      label243:
      j += 1;
    }
  }
  
  public static String getZipCodeFromLatLng(Context paramContext, double paramDouble1, double paramDouble2)
  {
    Object localObject2 = null;
    try
    {
      boolean bool = Geocoder.isPresent();
      Object localObject1 = localObject2;
      if (bool) {}
      try
      {
        localObject1 = new Geocoder(paramContext, Locale.getDefault()).getFromLocation(paramDouble1, paramDouble2, 5);
        if (localObject1 != null)
        {
          int i = ((List)localObject1).size();
          if (i <= 0) {}
        }
      }
      catch (IOException paramContext)
      {
        for (;;)
        {
          try
          {
            paramContext = ((List)localObject1).iterator();
            if (!paramContext.hasNext()) {
              continue;
            }
            localObject1 = (Address)paramContext.next();
            if (((Address)localObject1).getPostalCode() == null) {
              continue;
            }
            paramContext = ((Address)localObject1).getPostalCode();
            return paramContext;
          }
          catch (Exception paramContext)
          {
            LOG.e("CommonUtils", "Error: ", paramContext);
            continue;
          }
          paramContext = paramContext;
          LOG.e("CommonUtils", "Error: ", paramContext);
          localObject1 = localObject2;
        }
      }
      return null;
    }
    catch (Exception paramContext)
    {
      LOG.e("CommonUtils", "Error: ", paramContext);
    }
  }
  
  public static List<Address> getZipCodeFromLatLng1(Context paramContext, double paramDouble1, double paramDouble2)
  {
    if (Geocoder.isPresent()) {
      try
      {
        paramContext = new Geocoder(paramContext, Locale.getDefault()).getFromLocation(paramDouble1, paramDouble2, 5);
        return paramContext;
      }
      catch (IOException paramContext)
      {
        LOG.e("CommonUtils", "Error: ", paramContext);
      }
    }
    return null;
  }
  
  public static void goToPlayStore()
  {
    String str = getActivity().getPackageName();
    try
    {
      getActivity().startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + str)));
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      getActivity().startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + str)));
    }
  }
  
  public static void hideSoftKeyboard(Context paramContext)
  {
    paramContext = (Activity)paramContext;
    if ((paramContext != null) && (paramContext.getCurrentFocus() != null)) {
      ((InputMethodManager)paramContext.getSystemService("input_method")).hideSoftInputFromWindow(paramContext.getCurrentFocus().getWindowToken(), 0);
    }
  }
  
  public static boolean isAccessibilityEnabled(Context paramContext)
  {
    int i = 0;
    boolean bool2 = false;
    try
    {
      int j = Settings.Secure.getInt(paramContext.getContentResolver(), "accessibility_enabled");
      i = j;
    }
    catch (Settings.SettingNotFoundException localSettingNotFoundException)
    {
      for (;;)
      {
        TextUtils.SimpleStringSplitter localSimpleStringSplitter;
        boolean bool1;
        Log.d("CommonUtils", "Error finding setting, default accessibility to not found: " + localSettingNotFoundException.getMessage());
      }
    }
    localSimpleStringSplitter = new TextUtils.SimpleStringSplitter(':');
    bool1 = bool2;
    if (i == 1)
    {
      paramContext = Settings.Secure.getString(paramContext.getContentResolver(), "enabled_accessibility_services");
      bool1 = bool2;
      if (paramContext != null)
      {
        localSimpleStringSplitter.setString(paramContext);
        do
        {
          bool1 = bool2;
          if (!localSimpleStringSplitter.hasNext()) {
            break;
          }
        } while (!localSimpleStringSplitter.next().equalsIgnoreCase("com.google.android.marvin.talkback/com.google.android.marvin.talkback.TalkBackService"));
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public static boolean isAvailableToOrders(int paramInt, String paramString1, String paramString2)
  {
    if ((!isEmptyTextOrNull(paramString1)) && (!isEmptyTextOrNull(paramString2))) {
      try
      {
        Object localObject = Calendar.getInstance();
        SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm a", Locale.getDefault());
        localObject = ((Calendar)localObject).getTime();
        long l1 = localSimpleDateFormat.parse(paramString1 + " " + paramString2).getTime();
        long l2 = ((Date)localObject).getTime();
        return l1 - l2 > paramInt * 60000L;
      }
      catch (ParseException paramString1)
      {
        LOG.e("CommonUtils", "Error: ", paramString1);
        return false;
      }
    }
    return false;
  }
  
  public static boolean isConnected(Context paramContext)
  {
    paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    return (paramContext != null) && (paramContext.getState() == NetworkInfo.State.CONNECTED);
  }
  
  public static boolean isEmailValid(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return false;
    }
    return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$", 2).matcher(paramString).matches();
  }
  
  public static boolean isEmptyTextOrNull(String paramString)
  {
    return (paramString == null) || (paramString.length() == 0) || (paramString.equalsIgnoreCase("null"));
  }
  
  public static boolean isFullAVSEnabled(Context paramContext)
  {
    paramContext = (SiteConfigModel)convertJsonToClass(PreferenceManager.SITE_SETTINGS.getStringValue(paramContext), SiteConfigModel.class);
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (paramContext != null)
    {
      bool1 = bool2;
      if (paramContext.getMobilePay() != null)
      {
        if (paramContext.getMobilePay().isEnableFullAVS() != true) {
          break label49;
        }
        bool1 = true;
      }
    }
    return bool1;
    label49:
    return false;
  }
  
  public static boolean isHTML(String paramString)
  {
    return htmlPattern.matcher(paramString).matches();
  }
  
  public static boolean isHaveFavoriteMenuForRestaurant(List<UserInfoFavoriteMenu> paramList, String paramString)
  {
    if ((paramList != null) && (paramList.size() > 0))
    {
      paramList = paramList.iterator();
      while (paramList.hasNext()) {
        if (((UserInfoFavoriteMenu)paramList.next()).getSiteId().toLowerCase().equalsIgnoreCase(paramString.toLowerCase())) {
          return true;
        }
      }
    }
    return false;
  }
  
  private static boolean isHolidayNow(RestaurantHourInfo paramRestaurantHourInfo)
  {
    long l1 = paramRestaurantHourInfo.getStartDate().getTime();
    long l2 = paramRestaurantHourInfo.getEndDate().getTime();
    paramRestaurantHourInfo = Calendar.getInstance();
    return RestaurantHourUtil.getInstance().validateWeeklyHourInterval(paramRestaurantHourInfo, l1, l2);
  }
  
  public static boolean isLocationEnabled(Context paramContext)
  {
    boolean bool = true;
    if (paramContext == null) {
      bool = false;
    }
    do
    {
      for (;;)
      {
        return bool;
        if (Build.VERSION.SDK_INT >= 19) {
          try
          {
            locationMode = Settings.Secure.getInt(paramContext.getContentResolver(), "location_mode");
            if ((ContextCompat.checkSelfPermission(paramContext, "android.permission.ACCESS_FINE_LOCATION") != 0) || (ContextCompat.checkSelfPermission(paramContext, "android.permission.ACCESS_COARSE_LOCATION") != 0)) {
              return false;
            }
          }
          catch (Settings.SettingNotFoundException localSettingNotFoundException)
          {
            for (;;)
            {
              LOG.e("CommonUtils", "Error: ", localSettingNotFoundException);
            }
          }
          catch (Exception localException)
          {
            for (;;)
            {
              LOG.e("CommonUtils", "Error: ", localException);
            }
          }
        }
      }
    } while (!TextUtils.isEmpty(Settings.Secure.getString(paramContext.getContentResolver(), "location_providers_allowed")));
    return false;
  }
  
  public static boolean isLocationPermissionGranted(Context paramContext)
  {
    return (Build.VERSION.SDK_INT < 23) || ((ContextCompat.checkSelfPermission(paramContext, "android.permission.ACCESS_FINE_LOCATION") == 0) && (ContextCompat.checkSelfPermission(paramContext, "android.permission.ACCESS_COARSE_LOCATION") == 0));
  }
  
  private static boolean isMessageEqualsUnableAndSpanish(String paramString)
  {
    return paramString.contains("unable");
  }
  
  public static boolean isNearOpenHour(int paramInt1, int paramInt2, int paramInt3)
  {
    Object localObject2 = Calendar.getInstance();
    Object localObject1 = Calendar.getInstance();
    ((Calendar)localObject1).set(11, paramInt2);
    ((Calendar)localObject1).set(12, paramInt3);
    ((Calendar)localObject1).add(12, -paramInt1);
    localObject2 = ((Calendar)localObject2).getTime();
    localObject1 = ((Calendar)localObject1).getTime();
    return ((Date)localObject2).getTime() >= ((Date)localObject1).getTime();
  }
  
  public static boolean isNetworkAvailable(Context paramContext)
  {
    try
    {
      paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
      if (paramContext != null)
      {
        boolean bool = paramContext.isConnected();
        if (bool) {
          return true;
        }
      }
      return false;
    }
    catch (Exception paramContext)
    {
      LOG.e("CommonUtils", "ERROR", paramContext);
    }
    return false;
  }
  
  public static boolean isNull(Object paramObject)
  {
    return paramObject == null;
  }
  
  public static boolean isPackageExisted(Context paramContext, String paramString)
  {
    boolean bool2 = false;
    paramContext = paramContext.getPackageManager().getInstalledApplications(0).iterator();
    do
    {
      bool1 = bool2;
      if (!paramContext.hasNext()) {
        break;
      }
    } while (!((ApplicationInfo)paramContext.next()).packageName.equals(paramString));
    boolean bool1 = true;
    return bool1;
  }
  
  public static boolean isPastDate(String paramString)
  {
    try
    {
      paramString = new SimpleDateFormat("MM/dd/yyyy hh:mm a", Locale.getDefault()).parse(paramString);
      long l1 = new Date().getTime();
      long l2 = paramString.getTime();
      return l1 > l2;
    }
    catch (ParseException paramString)
    {
      LOG.e("CommonUtils", "Error: ", paramString);
    }
    return true;
  }
  
  public static boolean isPickUpMoreThan30Day(String paramString)
  {
    try
    {
      paramString = new SimpleDateFormat("MM/dd/yyyy hh:mm a", Locale.getDefault()).parse(paramString);
      long l1 = new Date().getTime();
      long l2 = paramString.getTime();
      return l1 + 2592000000L < l2;
    }
    catch (ParseException paramString)
    {
      LOG.e("CommonUtils", "Error: ", paramString);
    }
    return true;
  }
  
  public static boolean isResponseError(String paramString)
  {
    boolean bool1 = false;
    try
    {
      boolean bool2 = new JSONObject(paramString).has("error");
      if (bool2) {
        bool1 = true;
      }
      return bool1;
    }
    catch (JSONException paramString) {}
    return true;
  }
  
  public static boolean isTablet(Context paramContext)
  {
    boolean bool = false;
    int i;
    if ((paramContext.getResources().getConfiguration().screenLayout & 0xF) == 4)
    {
      i = 1;
      if ((paramContext.getResources().getConfiguration().screenLayout & 0xF) != 3) {
        break label57;
      }
    }
    label57:
    for (int j = 1;; j = 0)
    {
      if ((i != 0) || (j != 0)) {
        bool = true;
      }
      return bool;
      i = 0;
      break;
    }
  }
  
  public static boolean isZipCodeValidBasedOnState(String paramString, List<StateModel> paramList)
  {
    if ((paramString == null) || (paramString.isEmpty())) {
      return false;
    }
    int i = 0;
    while (i < paramList.size())
    {
      if ((((StateModel)paramList.get(i)).getState().equalsIgnoreCase(paramString)) || (((StateModel)paramList.get(i)).getCode().equalsIgnoreCase(paramString))) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  public static ArrayList<String> listOfCreditCardPattern()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add("^4[0-9]$");
    localArrayList.add("^5[1-5]$");
    localArrayList.add("^6(?:011|5[0-9]{2})$");
    localArrayList.add("^3[47]$");
    return localArrayList;
  }
  
  public static void logFromMainThread(Context paramContext, String paramString1, String paramString2)
  {
    if (paramContext != null) {
      new Handler(paramContext.getMainLooper()).post(new CommonUtils.20(paramString2, paramString1));
    }
  }
  
  public static void longLog(String paramString1, String paramString2)
  {
    if ((paramString2 != null) && (paramString1 != null))
    {
      int j = paramString2.length();
      int i = 0;
      while (i < j)
      {
        if (i + 1024 < j) {}
        i += 1024;
      }
    }
  }
  
  public static void notPartOfDemo(Context paramContext)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(paramContext);
    localBuilder.setMessage(paramContext.getString(R.string.not_in_demo)).setCancelable(false).setPositiveButton("OK", new CommonUtils.21());
    localBuilder.create().show();
  }
  
  public static ErrorModel parseRetrofitError(Retrofit paramRetrofit, Response<?> paramResponse)
  {
    paramRetrofit = paramRetrofit.responseBodyConverter(ErrorModel.class, new Annotation[0]);
    try
    {
      paramRetrofit = (ErrorModel)paramRetrofit.convert(paramResponse.errorBody());
      return paramRetrofit;
    }
    catch (IOException paramRetrofit)
    {
      LOG.e("CommonUtils", "IO EXception: ", paramRetrofit);
    }
    return new ErrorModel();
  }
  
  public static void paymentCodeEditText(EditText paramEditText)
  {
    paramEditText.addTextChangedListener(new CommonUtils.22());
  }
  
  public static <T> void printStackTrace(Throwable paramThrowable, Class<T> paramClass) {}
  
  private static boolean putStringInPreferences(Activity paramActivity, String paramString)
  {
    PreferenceManager.RECENT_SEARCH_RESTAURANT.setStringValue(paramActivity, paramString);
    return true;
  }
  
  public static String replaceCookie(String paramString, List<String> paramList)
  {
    HashMap localHashMap = new HashMap();
    if (!isEmptyTextOrNull(paramString)) {
      localHashMap = convertToMap(Arrays.asList(paramString.split("; ")));
    }
    int i = 0;
    Object localObject;
    while (i < paramList.size())
    {
      localObject = ((String)paramList.get(i)).replaceFirst("=", "|").split(Pattern.quote("|"));
      paramString = "";
      if (localObject.length > 1) {
        paramString = localObject[1];
      }
      if (localObject.length > 0) {
        localHashMap.put(localObject[0], paramString);
      }
      i += 1;
    }
    paramString = new StringBuilder();
    paramList = localHashMap.keySet().iterator();
    while (paramList.hasNext())
    {
      localObject = (String)paramList.next();
      paramString.append((String)localObject + "=" + (String)localHashMap.get(localObject));
      paramString.append("; ");
    }
    return paramString.toString();
  }
  
  public static String replaceFirstLetterCaps(String paramString)
  {
    paramString = paramString.toCharArray();
    paramString[0] = Character.toUpperCase(paramString[0]);
    return new String(paramString);
  }
  
  public static void resetDataAfterLogout(Context paramContext)
  {
    PreferenceManager.IS_USER_LOGGED_IN.setBooleanValue(paramContext, false);
    PreferenceManager.PREF_USERID.setStringValue(paramContext, null);
    PreferenceManager.USER_PROFILE.setStringValue(paramContext, "");
    PreferenceManager.FORGOT_PASSWORD_EMAIL.setStringValue(paramContext, null);
    PreferenceManager.PREFERRED_CLOSET_DETAILS.setStringValue(paramContext, "");
    PreferenceManager.PREFERENCE_GIFTCARD.setStringValue(paramContext, "");
    PreferenceManager.PREFERENCE_ONLINE_OFFER_APPLIED.setBooleanValue(paramContext, false);
    PreferenceManager.PREFERENCE_DEFAULT_SHIPPING_ADDRESS_NAME.setStringValue(paramContext, "");
    PreferenceManager.PREFERENCE_DEFAULT_ADDRESS.setStringValue(paramContext, "");
    PreferenceManager.LOGIN_TYPE.setStringValue(paramContext, "");
    PreferenceManager.LOGIN_METHOD.setStringValue(paramContext, "");
    PreferenceManager.SHOPPING_CART_TYPE.setStringValue(paramContext, null);
    PreferenceManager.ORDER_ID.setStringValue(paramContext, null);
    PreferenceManager.ORDER_DETAIL.setStringValue(paramContext, null);
    PreferenceManager.PREFERENCE_PREVIOUS_ORDER.setBooleanValue(paramContext, false);
    PreferenceManager.SET_PREFERRED_RESTAURANT.setBooleanValue(paramContext, false);
    PreferenceManager.SAVED_APP_COOKIE.setStringValue(paramContext, "");
    PreferenceManager.ORDER_DATE.setStringValue(getActivity(), "");
    PreferenceManager.ORDER_TIME.setStringValue(getActivity(), "");
    PreferenceManager.PREFERENCE_PICK_UP_ORDER.setStringValue(paramContext, "");
    PreferenceManager.PREFERENCE_SPECIAL_INSTRUCTION.setStringValue(paramContext, "");
    PreferenceManager.PREFERRED_RESTAURANT_ID.setStringValue(paramContext, null);
    PreferenceManager.PREFERRED_RESTAURANT.setStringValue(paramContext, null);
    PreferenceManager.TEMPORARY_CREDITCARD.setStringValue(paramContext, null);
    PreferenceManager.TEMPORARY_GIFTCARD.setStringValue(paramContext, null);
    PreferenceManager.NEAREST_RESTAURANT.setStringValue(paramContext, null);
  }
  
  public static void resetOrderTime()
  {
    PreferenceManager.ORDER_DATE.setStringValue(getActivity(), "");
    PreferenceManager.ORDER_TIME.setStringValue(getActivity(), "");
  }
  
  public static double round(double paramDouble, int paramInt)
  {
    if (paramInt < 0) {
      throw new IllegalArgumentException();
    }
    long l = Math.pow(10.0D, paramInt);
    return Math.round(paramDouble * l) / l;
  }
  
  public static int roundOffMin(int paramInt)
  {
    return (int)(Math.rint(paramInt / 10.0D) * 10.0D);
  }
  
  public static boolean saveLatestCookie(Context paramContext, Headers paramHeaders)
  {
    return PreferenceManager.SAVED_APP_COOKIE.setStringValueAsCommit(paramContext, replaceCookie(PreferenceManager.SAVED_APP_COOKIE.getStringValue(paramContext), getListOfNewCookies(paramHeaders)));
  }
  
  public static void scrollToNestedView(NestedScrollView paramNestedScrollView, View paramView)
  {
    Point localPoint = new Point();
    getDeepChildOffset(paramNestedScrollView, paramView.getParent(), paramView, localPoint);
    paramNestedScrollView.smoothScrollTo(0, localPoint.y);
  }
  
  public static void scrollToView(ScrollView paramScrollView, View paramView)
  {
    Point localPoint = new Point();
    getDeepChildOffset(paramScrollView, paramView.getParent(), paramView, localPoint);
    paramScrollView.smoothScrollTo(0, localPoint.y);
  }
  
  public static String sentenceCaseText(String paramString)
  {
    paramString = paramString.toLowerCase().split("\\. ");
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    while (i < paramString.length)
    {
      Object localObject = paramString[i];
      localStringBuilder.append(String.valueOf(localObject.charAt(0)).toUpperCase());
      localStringBuilder.append(localObject.substring(1, localObject.length()));
      if (i < paramString.length - 1) {
        localStringBuilder.append(". ");
      }
      i += 1;
    }
    return localStringBuilder.toString();
  }
  
  public static void setBooleanValue(Context paramContext, String paramString, boolean paramBoolean)
  {
    if (paramContext != null)
    {
      paramContext = paramContext.getSharedPreferences("Darden", 0).edit();
      paramContext.putBoolean(paramString.toLowerCase(Locale.ENGLISH), paramBoolean);
      paramContext.apply();
    }
  }
  
  public static void setBrandHolidayPreferences(Context paramContext, RestaurantHourInfo paramRestaurantHourInfo)
  {
    PreferenceManager.BRAND_HOLIDAY_MESSAGE.setStringValue(paramContext, "");
    PreferenceManager.IS_GLOBAL_HOLIDAY.setBooleanValue(paramContext, false);
    if ((paramRestaurantHourInfo != null) && (isHolidayNow(paramRestaurantHourInfo)))
    {
      PreferenceManager.IS_GLOBAL_HOLIDAY.setBooleanValue(paramContext, true);
      paramRestaurantHourInfo = getHolidayMessage(paramRestaurantHourInfo);
      if ((paramRestaurantHourInfo != null) && (!paramRestaurantHourInfo.isEmpty())) {
        PreferenceManager.BRAND_HOLIDAY_MESSAGE.setStringValue(paramContext, paramRestaurantHourInfo);
      }
    }
    else
    {
      return;
    }
    PreferenceManager.IS_GLOBAL_HOLIDAY.setBooleanValue(paramContext, false);
  }
  
  public static String setCapitalizeString(String paramString)
  {
    paramString = paramString.split(" ");
    StringBuilder localStringBuilder = new StringBuilder();
    if (paramString[0].length() > 0)
    {
      localStringBuilder.append(Character.toUpperCase(paramString[0].charAt(0)) + paramString[0].subSequence(1, paramString[0].length()).toString().toLowerCase());
      int i = 1;
      while (i < paramString.length)
      {
        localStringBuilder.append(" ");
        localStringBuilder.append(Character.toUpperCase(paramString[i].charAt(0)) + paramString[i].subSequence(1, paramString[i].length()).toString().toLowerCase());
        i += 1;
      }
    }
    return localStringBuilder.toString();
  }
  
  public static void setListViewHeight(ExpandableListView paramExpandableListView, int paramInt)
  {
    ExpandableListAdapter localExpandableListAdapter = paramExpandableListView.getExpandableListAdapter();
    int j = 0;
    int n = View.MeasureSpec.makeMeasureSpec(paramExpandableListView.getWidth(), 1073741824);
    int k = 0;
    while (k < localExpandableListAdapter.getGroupCount())
    {
      localObject = localExpandableListAdapter.getGroupView(k, false, null, paramExpandableListView);
      i = j;
      if (localObject != null)
      {
        ((View)localObject).measure(n, 0);
        i = j + ((View)localObject).getMeasuredHeight();
      }
      if ((!paramExpandableListView.isGroupExpanded(k)) || (k == paramInt))
      {
        j = i;
        if (!paramExpandableListView.isGroupExpanded(k))
        {
          j = i;
          if (k != paramInt) {}
        }
      }
      else
      {
        int m = 0;
        for (;;)
        {
          j = i;
          if (m >= localExpandableListAdapter.getChildrenCount(k)) {
            break;
          }
          localObject = localExpandableListAdapter.getChildView(k, m, false, null, paramExpandableListView);
          j = i;
          if (localObject != null)
          {
            ((View)localObject).measure(n, 0);
            j = i + ((View)localObject).getMeasuredHeight();
          }
          m += 1;
          i = j;
        }
      }
      k += 1;
    }
    Object localObject = paramExpandableListView.getLayoutParams();
    int i = j + paramExpandableListView.getDividerHeight() * (localExpandableListAdapter.getGroupCount() - 1);
    paramInt = i;
    if (i < 10) {
      paramInt = 200;
    }
    ((ViewGroup.LayoutParams)localObject).height = paramInt;
    paramExpandableListView.setLayoutParams((ViewGroup.LayoutParams)localObject);
    paramExpandableListView.requestLayout();
  }
  
  public static void setListViewHeight(ListView paramListView)
  {
    ListAdapter localListAdapter = paramListView.getAdapter();
    int j = 0;
    int i = 0;
    while (i < localListAdapter.getCount())
    {
      localObject = localListAdapter.getView(i, null, paramListView);
      ((View)localObject).measure(0, 0);
      j += ((View)localObject).getMeasuredHeight();
      i += 1;
    }
    Object localObject = paramListView.getLayoutParams();
    ((ViewGroup.LayoutParams)localObject).height = (paramListView.getDividerHeight() * (localListAdapter.getCount() - 1) + j);
    paramListView.setLayoutParams((ViewGroup.LayoutParams)localObject);
    paramListView.requestLayout();
  }
  
  public static void setLocale(Context paramContext, String paramString)
  {
    paramString = new Locale(paramString);
    Resources localResources = paramContext.getResources();
    DisplayMetrics localDisplayMetrics = localResources.getDisplayMetrics();
    Configuration localConfiguration = localResources.getConfiguration();
    localConfiguration.locale = paramString;
    if (Build.VERSION.SDK_INT >= 17) {
      localConfiguration.setLocale(paramString);
    }
    while (Build.VERSION.SDK_INT >= 24)
    {
      paramContext.getApplicationContext().createConfigurationContext(localConfiguration);
      paramContext.getResources().updateConfiguration(localConfiguration, paramContext.getResources().getDisplayMetrics());
      return;
      localConfiguration.locale = paramString;
    }
    localResources.updateConfiguration(localConfiguration, localDisplayMetrics);
  }
  
  public static void setOrderTimeToPreferenceManager(String paramString1, String paramString2)
  {
    PreferenceManager.ORDER_DATE.setStringValue(getActivity(), paramString1);
    PreferenceManager.ORDER_TIME.setStringValue(getActivity(), paramString2);
  }
  
  public static void setPreferenceHoliday(Context paramContext, List<String> paramList)
  {
    String str = "";
    if (paramList != null)
    {
      Iterator localIterator = paramList.iterator();
      paramList = str;
      while (localIterator.hasNext())
      {
        str = (String)localIterator.next();
        if (paramList.isEmpty()) {
          paramList = str;
        } else {
          paramList = paramList + "," + str;
        }
      }
      PreferenceManager.GLOBAL_HOLIDAY_DATES.setStringValue(paramContext, paramList);
    }
  }
  
  public static void setStickyRestaurant(Context paramContext, RestaurantDetail paramRestaurantDetail)
  {
    if (paramRestaurantDetail != null)
    {
      PreferenceManager.CURRENT_RESTAURANT_ID.setStringValue(paramContext, paramRestaurantDetail.getId());
      paramRestaurantDetail = convertClassToJson(paramRestaurantDetail);
      PreferenceManager.CURRENT_RESTAURANT.setStringValue(paramContext, paramRestaurantDetail);
      PreferenceManager.IS_HAVE_RESTAURANT.setBooleanValue(paramContext, true);
    }
  }
  
  public static void setStickyRestaurant(Context paramContext, String paramString)
  {
    PreferenceManager.CURRENT_RESTAURANT.setStringValue(paramContext, paramString);
    PreferenceManager.IS_HAVE_RESTAURANT.setBooleanValue(paramContext, true);
    if (!isEmptyTextOrNull(paramString))
    {
      paramString = (RestaurantDetail)convertJsonToClass(paramString, RestaurantDetail.class);
      if (paramString != null) {
        PreferenceManager.CURRENT_RESTAURANT_ID.setStringValue(paramContext, paramString.getId());
      }
    }
  }
  
  public static void setStringValue(Context paramContext, String paramString1, String paramString2)
  {
    if (paramContext != null)
    {
      paramContext = paramContext.getSharedPreferences("Darden", 0).edit();
      paramContext.putString(paramString1, paramString2);
      paramContext.apply();
    }
  }
  
  public static void setupPaymentUI(Context paramContext, View paramView)
  {
    if (!(paramView instanceof EditText)) {
      paramView.setOnTouchListener(new CommonUtils.10(paramView, paramContext));
    }
    if ((paramView instanceof ViewGroup))
    {
      int i = 0;
      while (i < ((ViewGroup)paramView).getChildCount())
      {
        setupPaymentUI(paramContext, ((ViewGroup)paramView).getChildAt(i));
        i += 1;
      }
    }
  }
  
  public static void setupUI(Context paramContext, View paramView)
  {
    if (!(paramView instanceof EditText)) {
      paramView.setOnTouchListener(new CommonUtils.9(paramContext));
    }
    if ((paramView instanceof ViewGroup))
    {
      int i = 0;
      while (i < ((ViewGroup)paramView).getChildCount())
      {
        setupUI(paramContext, ((ViewGroup)paramView).getChildAt(i));
        i += 1;
      }
    }
  }
  
  public static void shareContentOnFB(Activity paramActivity, ShareContent<?, ?> paramShareContent)
  {
    FacebookSdk.sdkInitialize(paramActivity.getApplicationContext());
    ShareDialog.show(paramActivity, paramShareContent);
  }
  
  public static void showDatePicker(TextView paramTextView, Calendar paramCalendar, DatePickerDialog.OnDateSetListener paramOnDateSetListener)
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.add(1, -13);
    int i;
    int j;
    if (!isEmptyTextOrNull(paramTextView.getText().toString()))
    {
      i = paramCalendar.get(1);
      j = paramCalendar.get(2);
    }
    for (int k = paramCalendar.get(5);; k = localCalendar.get(5))
    {
      paramTextView = new DatePickerDialog(getActivity(), paramOnDateSetListener, i, j, k);
      paramTextView.getDatePicker().setMaxDate(localCalendar.getTimeInMillis());
      paramTextView.show();
      return;
      i = 1985;
      j = localCalendar.get(2);
    }
  }
  
  public static AlertDialog.Builder showDialog(Activity paramActivity, String paramString1, String paramString2, String paramString3, DialogInterface.OnClickListener paramOnClickListener1, DialogInterface.OnClickListener paramOnClickListener2)
  {
    paramActivity = new AlertDialog.Builder(paramActivity, R.style.dialogAccent);
    paramActivity.setMessage(fromHtml(paramString1));
    if (paramOnClickListener1 != null) {
      paramActivity.setPositiveButton(paramString2, paramOnClickListener1);
    }
    if (paramOnClickListener2 != null) {
      paramActivity.setNegativeButton(paramString3, paramOnClickListener2);
    }
    paramActivity.show();
    return paramActivity;
  }
  
  public static void showDialog(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, DialogInterface.OnClickListener paramOnClickListener1, DialogInterface.OnClickListener paramOnClickListener2)
  {
    paramContext = new AlertDialog.Builder(paramContext, R.style.dialogAccent);
    paramContext.setMessage(paramString2);
    paramContext.setCancelable(false);
    if (paramString1 != null) {
      paramContext.setTitle(paramString1);
    }
    if (paramOnClickListener1 != null) {
      paramContext.setPositiveButton(paramString3, paramOnClickListener1);
    }
    if (paramOnClickListener2 != null) {
      paramContext.setNegativeButton(paramString4, paramOnClickListener2);
    }
    paramContext.show();
  }
  
  public static void showForceLoginDialog(Context paramContext)
  {
    DardenDialog localDardenDialog = new DardenDialog(paramContext);
    localDardenDialog.createDialog(null, paramContext.getResources().getString(R.string.force_relogin_message), paramContext.getResources().getString(R.string.ok_btn), null);
    localDardenDialog.setCancelAble(false);
    localDardenDialog.setOnPositiveButtonClickListener(new CommonUtils.5(localDardenDialog));
    localDardenDialog.setOnBackButtonClickListener(new CommonUtils.6(localDardenDialog));
    if (!localDardenDialog.isShowing()) {
      localDardenDialog.show();
    }
  }
  
  public static void showForceUpdateDialog(String paramString)
  {
    Object localObject = new HashMap();
    ((HashMap)localObject).put("darden.og.modal.modalname", "Update Available");
    DardenAnalyticUtils.setActionAnalyticWithParam("Modal Tracking", (Map)localObject);
    localObject = new DardenDialog(getActivity());
    ((DardenDialog)localObject).createDialog("Update Available", paramString, "Upgrade", null);
    ((DardenDialog)localObject).setCanceledOnTouchOutside(false);
    ((DardenDialog)localObject).setCancelAble(false);
    ((DardenDialog)localObject).setOnBackButtonClickListener(new CommonUtils.29());
    ((DardenDialog)localObject).setOnPositiveButtonClickListener(new CommonUtils.30());
    ((DardenDialog)localObject).show();
  }
  
  public static void showNoNetworkConnectivityAlert(Context paramContext)
  {
    if ((noNetworkConnectivityAlert != null) && (noNetworkConnectivityAlert.isShowing())) {}
    while (paramContext == null) {
      return;
    }
    Object localObject = new HashMap();
    ((HashMap)localObject).put("darden.og.modal.modalname", "Offline Mode");
    DardenAnalyticUtils.setActionAnalyticWithParam("Modal Tracking", (Map)localObject);
    localObject = new AlertDialog.Builder(paramContext, R.style.dialogAccent).setTitle(R.string.offline_mode).setMessage(R.string.offline_msg).setCancelable(false);
    ((AlertDialog.Builder)localObject).setPositiveButton(R.string.settings, new CommonUtils.18(paramContext));
    ((AlertDialog.Builder)localObject).setNegativeButton(R.string.cancel_btn, new CommonUtils.19());
    noNetworkConnectivityAlert = ((AlertDialog.Builder)localObject).show();
  }
  
  public static void showNotifyUpdateDialog(String paramString)
  {
    Object localObject = new HashMap();
    ((HashMap)localObject).put("darden.og.modal.modalname", "Update Available");
    DardenAnalyticUtils.setActionAnalyticWithParam("Modal Tracking", (Map)localObject);
    localObject = new DardenDialog(getActivity());
    ((DardenDialog)localObject).createDialog("Update Available", paramString, "Upgrade", "Dismiss");
    ((DardenDialog)localObject).setCanceledOnTouchOutside(false);
    ((DardenDialog)localObject).setCancelAble(false);
    ((DardenDialog)localObject).setOnPositiveButtonClickListener(new CommonUtils.31());
    ((DardenDialog)localObject).setOnNegativeButtonClickListener(new CommonUtils.32((DardenDialog)localObject));
    ((DardenDialog)localObject).show();
  }
  
  public static ProgressDialog showPgDialog(Activity paramActivity, String paramString1, String paramString2)
  {
    paramActivity = new ProgressDialog(paramActivity);
    paramActivity.setTitle(paramString1);
    paramActivity.setMessage(paramString2);
    paramActivity.show();
    return paramActivity;
  }
  
  public static void showServiceFailureAlert(Context paramContext)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(paramContext, R.style.dialogAccent);
    localBuilder.setCancelable(false);
    localBuilder.setMessage(paramContext.getString(R.string.server_not_reachable));
    localBuilder.setPositiveButton(paramContext.getString(R.string.button_text_ok), new CommonUtils.7());
    localBuilder.setOnDismissListener(new CommonUtils.8());
    paramContext = localBuilder.create();
    if (!isShowing)
    {
      paramContext.show();
      isShowing = true;
    }
  }
  
  public static void showServiceOffDialog(Context paramContext)
  {
    showServiceOffDialog(paramContext, null, null, null, null, true);
  }
  
  public static void showServiceOffDialog(Context paramContext, String paramString)
  {
    showServiceOffDialog(paramContext, paramString, null, null, null, true);
  }
  
  public static void showServiceOffDialog(Context paramContext, String paramString1, String paramString2)
  {
    showServiceOffDialog(paramContext, paramString1, paramString2, null, null, true);
  }
  
  public static void showServiceOffDialog(Context paramContext, String paramString1, String paramString2, View.OnClickListener paramOnClickListener)
  {
    showServiceOffDialog(paramContext, paramString1, paramString2, paramOnClickListener, null, true);
  }
  
  public static void showServiceOffDialog(Context paramContext, String paramString1, String paramString2, View.OnClickListener paramOnClickListener, DialogInterface.OnKeyListener paramOnKeyListener, boolean paramBoolean)
  {
    DardenDialog localDardenDialog = new DardenDialog(paramContext);
    String str;
    if (PreferenceManager.LANGUAGE.getIntegerValue(paramContext) == Permissions.LANGUAGE_SPANISH.getValue())
    {
      str = "Lo sentimos, ha habido un problema al completar su solicitud. Por favor, inténtelo de nuevo más tarde.";
      if (paramString1 == null) {
        break label136;
      }
      if (!isMessageEqualsUnableAndSpanish(paramString1)) {
        break label131;
      }
      paramContext = str;
      label45:
      paramString1 = "OK";
    }
    for (;;)
    {
      localDardenDialog.createDialog(paramString2, paramContext, paramString1, null);
      localDardenDialog.setCancelAble(false);
      localDardenDialog.setOnPositiveButtonClickListener(new CommonUtils.3(paramOnClickListener, localDardenDialog));
      localDardenDialog.setOnBackButtonClickListener(new CommonUtils.4(paramOnKeyListener, paramBoolean, localDardenDialog));
      if (!localDardenDialog.isShowing()) {
        localDardenDialog.show();
      }
      if (!isEmptyTextOrNull(paramString2)) {
        DardenAnalyticUtils.setErrorAnalytic(paramString2, paramContext);
      }
      return;
      str = "Sorry, there's been a problem completing your request. Please try again later.";
      break;
      label131:
      paramContext = paramString1;
      break label45;
      label136:
      if (paramContext != null)
      {
        paramString1 = paramContext.getResources().getString(R.string.button_text_ok);
        paramContext = str;
      }
      else
      {
        paramContext = str;
        paramString1 = "OK";
      }
    }
  }
  
  public static void showServiceOffDialog(Context paramContext, String paramString1, String paramString2, View.OnClickListener paramOnClickListener, boolean paramBoolean)
  {
    showServiceOffDialog(paramContext, paramString1, paramString2, paramOnClickListener, null, paramBoolean);
  }
  
  public static void showToast(Activity paramActivity, String paramString)
  {
    if ((paramActivity != null) && (!TextUtils.isEmpty(paramString))) {
      Toast.makeText(paramActivity, paramString, 0).show();
    }
  }
  
  public static TextToSpeech textSpeech(Context paramContext)
  {
    textToSpeech = new TextToSpeech(paramContext, new CommonUtils.2());
    return textToSpeech;
  }
  
  public static List<Address> updateLocationsFromZipCode(String paramString)
  {
    paramString = getGeoAddressList(getActivity(), paramString);
    if ((paramString != null) && (paramString.size() > 0)) {
      try
      {
        ((Address)paramString.get(0)).getLocality();
        ((Address)paramString.get(0)).getAdminArea();
        ((Address)paramString.get(0)).getCountryName();
        return paramString;
      }
      catch (Exception paramString)
      {
        LOG.e("CommonUtils", "Error: ", paramString);
      }
    }
    return null;
  }
  
  public static boolean validateTextLength(String paramString, int paramInt)
  {
    return (!TextUtils.isEmpty(paramString)) && (paramString.length() == paramInt);
  }
  
  public static String waitUnits(String paramString1, String paramString2)
  {
    String str = "";
    int i = Integer.parseInt(paramString1);
    paramString1 = str;
    if (paramString2.equalsIgnoreCase("Minute"))
    {
      if ((i > 1) || (i == 0)) {
        paramString1 = "mins";
      }
    }
    else {
      return paramString1;
    }
    return "min";
  }
  
  public static Bitmap writeTextOnDrawable(Context paramContext, int paramInt, String paramString)
  {
    Context localContext = null;
    try
    {
      paramContext = BitmapFactory.decodeResource(paramContext.getResources(), paramInt).copy(Bitmap.Config.ARGB_8888, true);
      localContext = paramContext;
      Object localObject = Typeface.create("Helvetica", 1);
      localContext = paramContext;
      Paint localPaint = new Paint();
      localContext = paramContext;
      localPaint.setStyle(Paint.Style.FILL);
      localContext = paramContext;
      localPaint.setColor(-1);
      localContext = paramContext;
      localPaint.setTypeface((Typeface)localObject);
      localContext = paramContext;
      localPaint.setTextAlign(Paint.Align.CENTER);
      localContext = paramContext;
      localPaint.setTextSize(30.0F);
      localContext = paramContext;
      localObject = new Rect();
      localContext = paramContext;
      localPaint.getTextBounds(paramString, 0, paramString.length(), (Rect)localObject);
      localContext = paramContext;
      Canvas localCanvas = new Canvas(paramContext);
      localContext = paramContext;
      if (((Rect)localObject).width() >= localCanvas.getWidth() - 4)
      {
        localContext = paramContext;
        localPaint.setTextSize(30.0F);
      }
      localContext = paramContext;
      paramInt = localCanvas.getWidth() / 2;
      localContext = paramContext;
      int i = (int)(localCanvas.getHeight() / 2 - (localPaint.descent() + localPaint.ascent()) / 2.0F);
      localContext = paramContext;
      localCanvas.drawText(paramString, paramInt - 2, i, localPaint);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      LOG.e("CommonUtils", "Error: ", paramContext);
    }
    return localContext;
  }
  
  public static enum LanguageType
  {
    ENGLISH,  SPANISH;
    
    private LanguageType() {}
  }
}
