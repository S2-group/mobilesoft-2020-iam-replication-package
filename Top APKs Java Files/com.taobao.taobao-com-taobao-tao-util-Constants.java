package com.taobao.tao.util;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.net.Uri;
import android.net.Uri.Builder;
import android.taobao.util.TaoLog;
import android.view.View;
import android.widget.Toast;
import com.taobao.tao.Globals;
import com.taobao.taobaocompat.R.string;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Deprecated
public class Constants
{
  public static final int ACTIVITY_ID_ALIPAY = 12;
  public static final int ACTIVITY_ID_BOUGHTLIST = 13;
  public static final int ACTIVITY_ID_BROWSER = 1;
  public static final int ACTIVITY_ID_CART = 22;
  public static final int ACTIVITY_ID_CATEGROY = 7;
  public static final int ACTIVITY_ID_CHANGEPASSWORD = 5;
  public static final int ACTIVITY_ID_CHARGE = 6;
  public static final int ACTIVITY_ID_COMPLAIN = 3;
  public static final int ACTIVITY_ID_COOLNAVIGATION = 14;
  public static final int ACTIVITY_ID_DETAIL = 10;
  public static final int ACTIVITY_ID_FAVORITE = 23;
  public static final int ACTIVITY_ID_JU = 19;
  public static final int ACTIVITY_ID_LOGIN = 4;
  public static final int ACTIVITY_ID_LOGISTICS = 24;
  public static final int ACTIVITY_ID_MAIN = 0;
  public static final int ACTIVITY_ID_MYTAOBAO = 25;
  public static final int ACTIVITY_ID_ORDER = 11;
  public static final int ACTIVITY_ID_RCGRID = 17;
  public static final int ACTIVITY_ID_RCTOP = 16;
  public static final int ACTIVITY_ID_RClIST = 18;
  public static final int ACTIVITY_ID_REDPACKET = 15;
  public static final int ACTIVITY_ID_SCANRESULT = 20;
  public static final int ACTIVITY_ID_SCANRESULTURL = 21;
  public static final int ACTIVITY_ID_SEARCHLIST = 9;
  public static final int ACTIVITY_ID_SHOWTEXT = 2;
  public static final String ALIPAYUPDATEPATH = "alipayupdatapath";
  public static final String ALIPAY_APK_NAME = "mobile_sp.apk";
  public static final String ALIPAY_PARNER = "";
  public static int ALIPAY_REQUEST_CODE = 0;
  public static final String ANDROID001 = "001";
  public static final String ANDROID001_SHARED = "android001_shared";
  public static final String ANDROIDTMALL = "tmall";
  public static final String ANDROIDTMALL_SHARED = "androidtmall_shared";
  public static final String APKNAME = "/tao.apk";
  public static final String APKNEWCHANGE = "tao.apk-change";
  public static final String APKSIZE = "tao.apk-size";
  public static final Uri APN_URI;
  public static String APP_DIR;
  public static final String ATLAS = "atlas";
  public static final String AUCTION_TAG1 = "20482";
  public static final String AUCTION_TAG2 = "20546";
  public static final String AVAILABLE_ENV_ERR = "ENV_ERR";
  public static final String AVAILABLE_ERROR = "ERR";
  public static final String BACKUPDATE = "立即更新";
  public static final String BANNER_COUNT_PER = "bannercount";
  public static final String BANNER_KEY = "banner_key";
  public static final String BANNER_VALUE = "banner_value";
  public static final String BIAOGE_FIRST_HAS_BIND_USER = "biaoge_first_has_bind_user";
  public static final String BIAOGE_FIRST_TO_BIAOGEMAIN = "biaoge_first_to_biaoge_main";
  public static final String BIAOGE_FIRST_TO_DETAIL = "biaoge_first_to_detail";
  public static final String BIAOGE_WELCOME = "biaoge_first_welcome";
  public static final String CATEGORCATID = "categorycatid";
  public static final String CATEGORNAME = "categoryname";
  public static final int CATEGOTY_TYPE_ALL = 1;
  public static final int CATEGOTY_TYPE_SEARCH = 0;
  public static final int CATEGOTY_TYPE_TMS = 2;
  public static final String CATEGROYALL_SHARED = "categroyall_shared";
  public static final String CATEGROYTYPE = "categroytype";
  public static final String COMMEND_ANDROID = "commend_android";
  public static final String COMMEND_TAG = "commend_tag";
  public static final String COMMEND_TMALL = "commend_tmall";
  public static final String COMPLAINFAILED = "抱歉，您的反馈发送失败，请重新发送！";
  public static final String COMPLAININVALIDSID = "抱歉，请重新登录！";
  public static final String COMPLAINSUCCESS = "您的反馈我们已收到，感谢您对淘宝网客户端的关注和支持！";
  public static final String COOLNAVIGATION_FLAG_SHARED = "coolnavigation_flag_shared";
  public static final String COOLNAVIGATION_SHARED = "coolnavigation_shared";
  public static final String COSHOP_SHARED = "coshop_shared";
  public static final String COUNT = "count";
  public static final Uri CURRENT_APN_URI;
  public static String CartlastTag = "";
  public static final String DEFAULT_CHARSET = "GBK";
  public static final int DETAILSWITCHRULE = 1;
  public static final String DUMP_RESOURCE = "taobaoandroiddumpresource";
  public static final String[] EMPTY_STRING_ARRAY;
  public static final int EVENT_ALLSPARK_ATTENTION_RESULT = 5003;
  public static final int EVENT_AVAILABLE = 4;
  public static final int EVENT_FROMLAIWANG_EVENT = 5004;
  public static final int EVENT_ID_EFFECT_TRACK = 23500;
  public static final int EVENT_ID_VIEW_ITEM_LBS = 20001;
  public static final int EVENT_YYZ_EVENT = 2101;
  public static final String EXITSHOWMESSAGE = "是否退出淘客户端？";
  public static int EventID_ATLAS_BLADE = 0;
  public static int EventID_ATLAS_DD_INSTALLERR = 0;
  public static int EventID_ATLAS_DD_LOADERR = 0;
  public static int EventID_ATLAS_DD_LOADTIME = 0;
  public static int EventID_ATLAS_PLUGIN_DEGRADE = 0;
  public static int EventID_ATLAS_PLUGIN_START = 0;
  public static int EventID_ATLAS_REDUCE_IN_RANK = 0;
  private static final int EventID_BASE_OFFSET_TWC = 21000;
  public static int EventID_BIZ_AVAILABLE = 0;
  public static int EventID_EVENT_TIME = 0;
  public static int EventID_EXTERN_URL = 0;
  public static int EventID_FEED_DETAIL_PERFORMACE = 0;
  public static int EventID_FRIST_LOGIN = 0;
  public static int EventID_GOOGLE_SERVER = 0;
  public static int EventID_H5_APPCACHE = 0;
  public static int EventID_H5_FREEMEMORY = 0;
  public static int EventID_H5_PERFORMACE = 0;
  public static int EventID_HOTPATCH_STATS = 0;
  public static int EventID_HTTPS_DEGRADE = 0;
  public static int EventID_LAUNCH_PERFORMACE = 0;
  public static int EventID_MAX_MEMORY = 0;
  public static int EventID_NAVIGATION = 0;
  public static int EventID_NAVI_PAGE = 0;
  public static int EventID_NETWORK_OBJECT = 0;
  public static int EventID_NETWORK_OBJECT_UNIFORM = 0;
  public static int EventID_PERSITCON = 0;
  public static int EventID_RD_TRACE = 0;
  public static int EventID_REFLECT_METHOD_FAILED_EVENT_ID = 0;
  public static int EventID_REFLECT_METHOD_SUCCEED_EVENT_ID = 0;
  public static int EventID_SO_INIT = 0;
  public static int EventID_SSO_EXCEPTION = 0;
  public static int EventID_SSO_HOST = 0;
  public static int EventID_STAT_1010 = 0;
  public static int EventID_SWITCH_PERFORMACE = 0;
  public static int EventID_TRAFFIC_STATS = 0;
  public static int EventID_VIP_EVENT = 0;
  public static int EventID_WINDVANE = 0;
  public static final int FALSESWITCHRULE = 2;
  public static final String FIELDLIST = "item_id,long_name,short_name,original_price,pic_url,activity_price,item_status,discount,sold_count,pic_url_from_ic,online_start_time,online_end_time";
  public static final int FILECHOOSER_REQ_CODE = 2688;
  public static int GAME = 0;
  public static final int HANDLER_APK_URL_HITTED = 136;
  public static final int HANDLER_APPLIFECYCLE = 141;
  public static final int HANDLER_AUTO_HIDE_SEARCHEDIT = 84;
  public static final int HANDLER_AUTO_HIDING_SEARCHEDIT = 85;
  public static final int HANDLER_COMPONENT_CLOSED = 135;
  public static final int HANDLER_COMPONENT_LOGIN_FILTER = 134;
  public static final int HANDLER_GGK = 142;
  public static final int HANDLER_REMINDER_URL_HITTED = 139;
  public static final int HANDLER_STOP_HIDING_SEARCHEDIT = 86;
  public static final int HANDLER_TBCALENDAR_URL_HITTED = 140;
  public static final int HANDLER_TOP_SHOP_URL_GET_SELLERID = 138;
  public static final int HANDLER_TOP_SHOP_URL_HITTED = 137;
  public static final int HANDLER_WHAR_BROWSER_CATEGROYF = 69;
  public static final int HANDLER_WHAR_BROWSER_CATEGROYS = 68;
  public static final int HANDLER_WHAR_MSG_CATEGORYFINSH = 73;
  public static final int HANDLER_WHAR_MSG_CATEGORYNEXTPAGE = 74;
  public static final int HANDLER_WHAR_MSG_ITMECLICK = 70;
  public static final int HANDLER_WHAR_MSG_ITMENEXTCLICK = 72;
  public static final int HANDLER_WHAR_MSG_ITMESELECTED = 71;
  public static final int HANDLER_WHAR_PICADD = 67;
  public static final int HANDLER_WHAT_ANIMATION_COMPLETE = 80;
  public static final int HANDLER_WHAT_ASSOCWORD_RECEIVE = 81;
  public static final int HANDLER_WHAT_AUTOTITLE = 55;
  public static final int HANDLER_WHAT_BINDED = 90;
  public static final int HANDLER_WHAT_BINDED_SYSTEM_ERR = 89;
  public static final int HANDLER_WHAT_BINDED_TIMEOUT = 91;
  public static final int HANDLER_WHAT_BINDING = 78;
  public static final int HANDLER_WHAT_BIND_AGREE = 76;
  public static final int HANDLER_WHAT_BIND_SUCCESS = 77;
  public static final int HANDLER_WHAT_BIND_TIMEOUT = 83;
  public static final int HANDLER_WHAT_CLOUD_HISTORY_SYNC = 143;
  public static final int HANDLER_WHAT_CONFIRM_ERROR = 79;
  public static final int HANDLER_WHAT_CONVENIENCE_CENTER_LOGINTITLE = 129;
  public static final int HANDLER_WHAT_FILTERURLSID = 59;
  public static final int HANDLER_WHAT_GAME_ADDR2_GET = 128;
  public static final int HANDLER_WHAT_GAME_CHARGE = 125;
  public static final int HANDLER_WHAT_GAME_CHARGE_RESULT = 126;
  public static final int HANDLER_WHAT_GAME_GETSECID = 127;
  public static final int HANDLER_WHAT_GETRUBRIC = 132;
  public static final int HANDLER_WHAT_JSBACK = 88;
  public static final int HANDLER_WHAT_LOGINSTATUSF = 57;
  public static final int HANDLER_WHAT_LOGINSTATUSS = 56;
  public static final int HANDLER_WHAT_NOSIM = 82;
  public static final int HANDLER_WHAT_ORDERFINISH = 60;
  public static final int HANDLER_WHAT_PHONECHARGE = 58;
  public static final int HANDLER_WHAT_PHONECHARGE_AGAIN = 93;
  public static final int HANDLER_WHAT_QQ_CHARGE = 122;
  public static final int HANDLER_WHAT_QQ_CHARGE_RESULT = 123;
  public static final int HANDLER_WHAT_SEARCHSUGGEST_HIDE = 131;
  public static final int HANDLER_WHAT_SEARCHSUGGEST_SHOW = 130;
  public static final int HANDLER_WHAT_UPDATE_GAME_CHARGE_CONTENT = 124;
  public static final int HANDLER_WHAT_UPDATE_QQ_CHARGE_CONTENT = 121;
  public static final String ID = "id";
  public static final String INFORMATION_FLAG_SHARED = "information_flag_shared";
  public static final String INFORMATION_SHARED = "information_shared";
  public static final int INVALCARTITEM_REQUEST_CODE = 101;
  public static final int ITEM_DELETE_ALL = 127;
  public static final int ITEM_DELETE_DAY1 = 124;
  public static final int ITEM_DELETE_DAY30 = 126;
  public static final int ITEM_DELETE_DAY5 = 125;
  public static final int ITEM_DELETE_THIS = 123;
  public static final String JAE_URL_PATTERN = "^(http|https)://([^/\\?#]+\\.)*((jae\\.(m|wapa|waptest)\\.taobao\\.com)|(jaeapp\\.com)|(aliapp-inc\\.com)|(amap\\.com)|(jaecdn\\.com))(/.*)?$";
  public static final String JSON_SHAREDPREFERENCES = "json_sharedpreferences";
  public static final String JUCITYGROUP_SHARED = "jucitygroup_shared";
  public static final String JULOCATIONCITY_SHARED = "julocationcity_shared";
  public static final String JU_GROUP_ACTIVITY_PRICE = "activity_price";
  public static final String JU_GROUP_BIG_PIC_URL = "big_pic_url";
  public static final String JU_GROUP_DISCOUNT = "discount";
  public static final String JU_GROUP_GROUP_ID = "group_id";
  public static final String JU_GROUP_ITEM_ID = "item_id";
  public static final String JU_GROUP_ITEM_STATUS = "item_status";
  public static final String JU_GROUP_LONG_NAME = "long_name";
  public static final String JU_GROUP_ONLINE_END_TIME = "online_end_time";
  public static final String JU_GROUP_ONLINE_START_TIME = "online_start_time";
  public static final String JU_GROUP_ORIGINAL_PRICE = "original_price";
  public static final String JU_GROUP_PIC_URL = "pic_url";
  public static final String JU_GROUP_PIC_URL_FROM_IC = "pic_url_from_ic";
  public static final String JU_GROUP_SHORT_NAME = "short_name";
  public static final String JU_GROUP_SOLD_COUNT = "sold_count";
  public static final String JU_GROUP_THUMBNAIL = "image_url";
  public static String JU_LIST_IMAGE_SIZE;
  public static final String JU_LIST_IMAGE_SIZE2 = "_250x250.jpg";
  public static final String KEY_AD_BANNER_URL = "ad_banner_url";
  public static final String KEY_AD_COOKIE = "ad_cookie";
  public static final String KEY_AD_WORD_SEARCH = "ad_word_search";
  public static final String KEY_AD_WORD_SHOW = "ad_word_show";
  public static final String KEY_COMPONENT_ID = "component_id";
  public static final String KEY_CONTENT_CHANGE_ITEM_DETAIL = "item_detail";
  public static final String KEY_CONTENT_CHANGE_SHOP_ITEM_LIST = "shop_itemlist";
  public static final String KEY_CONTENT_CHANGE_SHOP_ITEM_SORT = "shop_itemsort";
  public static final String KEY_FEED_ID = "feed_id";
  public static final String KEY_FROM_NOTIFICATION = "itent_from_notification";
  public static final String KEY_ITEM_ID = "item_id";
  public static final String KEY_MSG_PUSH_MSGS = "intent_msg_push_msgs";
  public static final String KEY_MSG_PUSH_TASK_ID = "intent_msg_push_task_id";
  public static final String KEY_MY_COMPONENT_TAB_ID = "tab";
  public static final String KEY_ORDER_ID = "order_id";
  public static final String KEY_PROMPTION_TITLE = "title";
  public static final String KEY_SEARCH_ACTION = "search_action";
  public static final String KEY_SEARCH_KEYWORD = "search_keyword";
  public static final String KEY_SELLER_ID = "seller_id";
  public static final String KEY_SHOP_ID = "shop_id";
  public static final String KEY_SNS_ID = "sns_id";
  public static final String KEY_USER_ID = "user_id";
  public static final String KEY_WIDGET_WEITAO_CLICK = "widget_weitao_click";
  public static final String KEY_WIDGET_WEITAO_LOGIN = "widget_weitao_login";
  public static final String LAST_VERSION = "lastversion";
  public static String LBSCurCity;
  public static final int LISTSWITCHRULE = 0;
  public static final String LOGCLOSE = "taobaoandroidlogclose";
  public static final int LOGIN_HANDLER_KEY_ADDSHOP = 14;
  public static final int LOGIN_HANDLER_KEY_ADVERTISE = 16;
  public static final int LOGIN_HANDLER_KEY_ALIPAY = 19;
  public static final int LOGIN_HANDLER_KEY_ALLSPARK_COMMENT = 60;
  public static final int LOGIN_HANDLER_KEY_ALLSPARK_DETAIL = 59;
  public static final int LOGIN_HANDLER_KEY_APPLYCOUPON = 45;
  public static final int LOGIN_HANDLER_KEY_BOUGHTLIST = 20;
  public static final int LOGIN_HANDLER_KEY_BROWSER = 73;
  public static final int LOGIN_HANDLER_KEY_BROWSERTITLE = 7;
  public static final int LOGIN_HANDLER_KEY_CAIPIAO_BETTING = 38;
  public static final int LOGIN_HANDLER_KEY_CAIPIAO_MATCHBETTING = 39;
  public static final int LOGIN_HANDLER_KEY_CAIPIAO_MYORDER = 36;
  public static final int LOGIN_HANDLER_KEY_CAIPIAO_ORDERDETAIL = 40;
  public static final int LOGIN_HANDLER_KEY_CAIPIAO_PURSUE = 37;
  public static final int LOGIN_HANDLER_KEY_CART = 28;
  public static final int LOGIN_HANDLER_KEY_CATEGROY = 13;
  public static final int LOGIN_HANDLER_KEY_CHANGEPASSWORD = 11;
  public static final int LOGIN_HANDLER_KEY_CHATHEAD = 78;
  public static final int LOGIN_HANDLER_KEY_COMPLAIN = 5;
  public static final int LOGIN_HANDLER_KEY_COUPON = 35;
  public static final int LOGIN_HANDLER_KEY_CUSTOM = 56;
  public static final int LOGIN_HANDLER_KEY_DELIVERYMANAGER = 42;
  public static final int LOGIN_HANDLER_KEY_DETAIL = 17;
  public static final int LOGIN_HANDLER_KEY_EBOOK = 55;
  public static final int LOGIN_HANDLER_KEY_FAVORITE = 2;
  public static final int LOGIN_HANDLER_KEY_FLIGHT_TICKET_CONFIRM_ORDER = 52;
  public static final int LOGIN_HANDLER_KEY_FLIGHT_TICKET_DETAIL = 48;
  public static final int LOGIN_HANDLER_KEY_FLIGHT_TICKET_FILL_ORDER = 51;
  public static final int LOGIN_HANDLER_KEY_FLIGHT_TICKET_ORDER_LIST = 49;
  public static final int LOGIN_HANDLER_KEY_FLIGHT_TICKET_PASSENGER_LIST = 53;
  public static final int LOGIN_HANDLER_KEY_FLIGHT_TICKET_RELATION_LIST = 50;
  public static final int LOGIN_HANDLER_KEY_GAME = 25;
  public static final int LOGIN_HANDLER_KEY_HISTORY = 10;
  public static final int LOGIN_HANDLER_KEY_HYBRIDACTIVITY = 74;
  public static final int LOGIN_HANDLER_KEY_INVALID_CART = 43;
  public static final int LOGIN_HANDLER_KEY_JU = 27;
  public static final int LOGIN_HANDLER_KEY_LAIWANG_NOTIFY = 79;
  public static final int LOGIN_HANDLER_KEY_LOGISTIC = 23;
  public static final int LOGIN_HANDLER_KEY_MAIN = 30;
  public static final int LOGIN_HANDLER_KEY_MENU = 3;
  public static final int LOGIN_HANDLER_KEY_MORE = 22;
  public static final int LOGIN_HANDLER_KEY_MSGCENTER_CATEGORY = 68;
  public static final int LOGIN_HANDLER_KEY_MSGCENTER_DETAIL = 69;
  public static final int LOGIN_HANDLER_KEY_MYHOME = 4;
  public static final int LOGIN_HANDLER_KEY_MYTAOBAO = 1;
  public static final int LOGIN_HANDLER_KEY_NULL = 0;
  public static final int LOGIN_HANDLER_KEY_ORDER = 18;
  public static final int LOGIN_HANDLER_KEY_ORDER_ASYNC = 70;
  public static final int LOGIN_HANDLER_KEY_PANELMANAGER_JUMP_PROXY = 34;
  public static final int LOGIN_HANDLER_KEY_PANELMANAGER_PROXY = 33;
  public static final int LOGIN_HANDLER_KEY_PHONECHARGE = 12;
  public static final int LOGIN_HANDLER_KEY_PROMOTION = 32;
  public static final int LOGIN_HANDLER_KEY_PURCHASE = 41;
  public static final int LOGIN_HANDLER_KEY_PUSHCENTER = 54;
  public static final int LOGIN_HANDLER_KEY_QQ = 24;
  public static final int LOGIN_HANDLER_KEY_REFOUND = 76;
  public static final int LOGIN_HANDLER_KEY_SCANURL = 31;
  public static final int LOGIN_HANDLER_KEY_SEARCHLOGISTIC = 15;
  public static final int LOGIN_HANDLER_KEY_SETTING = 57;
  public static final int LOGIN_HANDLER_KEY_SHOWMAINPAGE = 46;
  public static final int LOGIN_HANDLER_KEY_SHOWTEXT = 8;
  public static final int LOGIN_HANDLER_KEY_TABGROUP = 9;
  public static final int LOGIN_HANDLER_KEY_TC_BUY = 63;
  public static final int LOGIN_HANDLER_KEY_TC_CHANGE = 66;
  public static final int LOGIN_HANDLER_KEY_TC_MY = 64;
  public static final int LOGIN_HANDLER_KEY_TC_SCAN_PAY = 62;
  public static final int LOGIN_HANDLER_KEY_TC_USER_INFO = 65;
  public static final int LOGIN_HANDLER_KEY_TF_PUBACCOUNT_ADDFOLLOW = 61;
  public static final int LOGIN_HANDLER_KEY_TO_FACE_DETECT = 71;
  public static final int LOGIN_HANDLER_KEY_TO_GLOBALTOOLKIT = 72;
  public static final int LOGIN_HANDLER_KEY_TO_PUSHWEBVIEW = 67;
  public static final int LOGIN_HANDLER_KEY_TO_WANGXIN = 66;
  public static final int LOGIN_HANDLER_KEY_TUITUI = 29;
  public static final int LOGIN_HANDLER_KEY_WANGXIN = 47;
  public static final int LOGIN_HANDLER_KEY_WANGXINLIFE = 80;
  public static final int LOGIN_HANDLER_KEY_WEIBODETAIL = 26;
  public static final int LOGIN_HANDLER_KEY_WEITAO_FOLLOW = 77;
  public static final int LOGIN_HANDLER_KEY_WINDVANE_SERVER = 75;
  public static final int LOGIN_HANDLER_KEY_WWMESSAGE = 58;
  public static final int LOGIN_HANDLER_KEY_WWWAP = 21;
  public static final int LOGIN_HANDLER_KEY__APPCENTER = 44;
  public static final String LOGOPEN = "taobaoandroidlogopen";
  public static final String MYBROWSERHIDETITLE = "myBrowserHideTitle";
  public static final String MYBROWSERTITLE = "myBrowserTitle";
  public static final String MYBROWSERURL = "myBrowserUrl";
  public static final String NAME = "name";
  public static final String NETWORK_ERROR_CHARGE = "1";
  public static final int NOTCLEARHISTORYLISTSWITCHRULE = 4;
  public static final String NOTICE_CHANGE_PSD_FAIL = "5";
  public static final String NOTICE_CHANGE_PSD_SUCCESS = "2";
  public static final String NOTICE_CONNECTO_SERVER_ERROR = "1";
  public static final String NOTICE_ERRORTIP_CHGPSD = "4";
  public static final String NOTICE_LOGIN_INFO_INVALIDATE = "3";
  public static final String NO_ITEM_CHARGE = "2";
  public static final int ORDERLISTSWITCHRULE = 3;
  public static int PHONE = 0;
  public static final String PicSeparator = "|";
  public static int QQ = 0;
  public static final String REDIRECT_KEYWORD = "redirectURL";
  public static final String RUBRICCOMMEND = "cat-nav";
  public static final String RUBRICCOMMEND_SHARED = "rubriccommend_shared";
  public static String SAVE_FILE_ROOT_DIR;
  public static int SCREEN_HEIGHT_OFFSET = 0;
  public static long SD_MIN_LIMIT = 0L;
  public static final String SEARCH_FROM = "search_from";
  public static final String SEARCH_KEYWORD = "search";
  public static final String SEARCH_LOC = "search_loc";
  public static final String SEARCH_TYPE = "search_type";
  public static final String SETFOCUSED = "Focused";
  public static final String SHAREKEY = "?nativef2e";
  public static final String SHARETYPE = "text/plain";
  public static final String SHARETYPE_WITH_QRCODE = "image/png";
  public static final String SHOWTEXTSTR = "showTextStr";
  public static final String SID = "sid";
  public static final String SIDW = "sid=";
  public static final String SINAWEIBOAPPKEYDESKEY = "sinadesedekey";
  public static final String SPEN_DIALOG = "spen_dialog";
  public static final String SPEN_DIALOG_CLICK_BTN_AIR = "spen_dialog_click_btn_air";
  public static final String SPEN_DIALOG_CLICK_BTN_COMMENT = "spen_dialog_click_btn_comment";
  public static final String SPEN_DIALOG_CLICK_BTN_FAV = "spen_dialog_click_btn_fav";
  public static final String SPEN_DIALOG_CLICK_BTN_WANGWANG = "spen_dialog_click_btn_wangwang";
  public static final String SPEN_DIALOG_CLICK_IMAGE = "spen_dialog_click_image";
  public static final String SWITCHRULESTYLE = "switchrulestyle";
  public static final String TAOBAOUPDATEURL = "taobaoupdataurl";
  public static final String TAOBAO_IMAGE_CDN = "http://img.taobaocdn.com/bao/uploaded/";
  public static final String TAOBAO_IMAGE_DATA = "taobao_image_data";
  public static final String TBSettingsPrefernceKey = "tbsettings";
  public static final int TITLENUM = 10;
  public static final String TTIDKEY = "taobaoandroidttidkey";
  public static final String[] TTID_LEPHONE_T;
  public static final int TYPE_SEARCH_FROM_COUPON = 3;
  public static final String UPDATENOMEMORY = "抱歉，您的手机存储空间不够，请清理存储空间后重新下载！";
  public static final String UPDATE_TIP_SETTING = "tip_num";
  public static final String USERAGENTSTR = "anclient";
  public static final String USERINFO = "userinfo";
  public static final String UpdateKey = "tao_apk";
  public static final String VERSION = "*";
  public static final int WEBVIEW_GLOBAL = 0;
  public static final int WEBVIEW_JAE = 1;
  public static final int WEBVIEW_SAFE = 2;
  public static final String WEIBO_EMOTIONS_FLAG_SHARED = "weibo_emotions_flag_shared";
  public static final String WEIBO_EMOTIONS_SHARED = "weibo_emotions_shared";
  public static final String Xm = "X:";
  public static String appkey;
  private static String appsecret;
  private static String appsecretSigned;
  public static String areaAndCatName;
  public static String beforeWeiBoKey;
  public static boolean exitFlag = false;
  public static int half_screen_width = 0;
  public static boolean isRefreshWeiboUI = false;
  public static final int loginTitleMoveX = 20;
  public static final int loginTitleTime = 100;
  public static final int navigationbar_height = 52;
  public static boolean openShakeForLottery = false;
  public static String[] projection;
  public static float screen_density;
  public static int screen_height;
  public static int screen_width;
  public static int screen_widthmul2;
  public static View showInput;
  public static int statusBarHeight;
  public static String tempBrowserTitle;
  public static int titleHeight = -1;
  
  static
  {
    PHONE = 1;
    QQ = 2;
    GAME = 3;
    isRefreshWeiboUI = false;
    exitFlag = false;
    APN_URI = Uri.parse("content://telephony/carriers/current");
    CURRENT_APN_URI = Uri.parse("content://telephony/carriers/preferapn");
    projection = new String[] { "_id,name,apn,user,password,mmsc,mcc,mnc" };
    APP_DIR = "taobao";
    SAVE_FILE_ROOT_DIR = APP_DIR;
    areaAndCatName = null;
    tempBrowserTitle = null;
    beforeWeiBoKey = null;
    SD_MIN_LIMIT = 20971520L;
    LBSCurCity = "";
    EMPTY_STRING_ARRAY = new String[0];
    ALIPAY_REQUEST_CODE = 1;
    JU_LIST_IMAGE_SIZE = "_120x120.jpg";
    TTID_LEPHONE_T = new String[] { "10220a", "203200" };
    appsecret = null;
    appkey = BuiltConfig.getString(R.string.appkey);
    appsecretSigned = BuiltConfig.getString(R.string.appsecret);
    EventID_NAVI_PAGE = 21001;
    EventID_PERSITCON = 21002;
    EventID_RD_TRACE = 21003;
    EventID_ATLAS_REDUCE_IN_RANK = 21004;
    EventID_REFLECT_METHOD_FAILED_EVENT_ID = 21005;
    EventID_REFLECT_METHOD_SUCCEED_EVENT_ID = 21006;
    EventID_SSO_EXCEPTION = 21007;
    EventID_HTTPS_DEGRADE = 21008;
    EventID_ATLAS_DD_LOADERR = 21009;
    EventID_ATLAS_DD_LOADTIME = 21010;
    EventID_ATLAS_DD_INSTALLERR = 21011;
    EventID_MAX_MEMORY = 21012;
    EventID_ATLAS_PLUGIN_START = 21013;
    EventID_ATLAS_PLUGIN_DEGRADE = 21014;
    EventID_SSO_HOST = 21015;
    EventID_GOOGLE_SERVER = 21020;
    EventID_H5_PERFORMACE = 21025;
    EventID_LAUNCH_PERFORMACE = 21028;
    EventID_SWITCH_PERFORMACE = 21029;
    EventID_FEED_DETAIL_PERFORMACE = 21030;
    EventID_VIP_EVENT = 21031;
    EventID_NAVIGATION = 21032;
    EventID_SO_INIT = 21033;
    EventID_EVENT_TIME = 21034;
    EventID_EXTERN_URL = 21035;
    EventID_H5_APPCACHE = 21040;
    EventID_H5_FREEMEMORY = 21041;
    EventID_WINDVANE = 21042;
    EventID_STAT_1010 = 21050;
    EventID_FRIST_LOGIN = 21061;
    EventID_TRAFFIC_STATS = 21062;
    EventID_ATLAS_BLADE = 21063;
    EventID_HOTPATCH_STATS = 21064;
    EventID_BIZ_AVAILABLE = 30001;
    EventID_NETWORK_OBJECT_UNIFORM = 26690;
    EventID_NETWORK_OBJECT = 64301;
  }
  
  public Constants() {}
  
  public static String addBoughtListParam(String paramString)
  {
    String str = "&client_orderlist=androidtaobao";
    if (paramString.indexOf("&client_orderlist=androidtaobao") < 0) {
      str = paramString + "&client_orderlist=androidtaobao";
    }
    return str;
  }
  
  public static void addJsonSharepreferences(String paramString1, String paramString2)
  {
    if (paramString1 == null) {
      return;
    }
    SharedPreferences.Editor localEditor = Globals.getApplication().getSharedPreferences("json_sharedpreferences", 0).edit();
    if (paramString2 == null) {
      localEditor.remove(paramString1);
    }
    for (;;)
    {
      localEditor.commit();
      return;
      localEditor.putString(paramString1, paramString2);
    }
  }
  
  public static final String changeUrlSid(String paramString1, String paramString2)
  {
    if ((paramString1 == null) || (paramString2 == null) || (paramString2.length() == 0)) {
      return paramString1;
    }
    return replaceParam(paramString1, "sid", paramString2);
  }
  
  public static void chmod(String paramString1, String paramString2)
  {
    try
    {
      paramString1 = "chmod " + paramString1 + " " + paramString2;
      Runtime.getRuntime().exec(paramString1);
      return;
    }
    catch (IOException paramString1)
    {
      paramString1.printStackTrace();
    }
  }
  
  public static int getABS(int paramInt)
  {
    int i = paramInt >> 31;
    return i ^ paramInt + i;
  }
  
  public static String getAppsecret()
  {
    if ((appsecret == null) || ("".equals(appsecret)))
    {
      str = new String();
      int i = 0;
      while (i < appsecretSigned.length())
      {
        char c = (char)(appsecretSigned.charAt(i) - i % 5);
        str = str + c;
        i += 1;
      }
      appsecret = str;
    }
    String str = appsecret;
    return appsecret;
  }
  
  public static String getData()
  {
    Object localObject = new Date();
    localObject = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Date)localObject);
    return (String)localObject + " ";
  }
  
  public static final String getItemParam(Object paramObject, String paramString)
  {
    return (String)((HashMap)paramObject).get(paramString);
  }
  
  public static String getJsonSharepreferences(String paramString)
  {
    return Globals.getApplication().getSharedPreferences("json_sharedpreferences", 0).getString(paramString, null);
  }
  
  public static final String getMidStr(String paramString1, String paramString2, String paramString3)
  {
    try
    {
      paramString2 = paramString1.substring(paramString1.indexOf(paramString2) + paramString2.length());
      paramString2 = paramString2.substring(0, paramString2.indexOf(paramString3));
      return paramString2;
    }
    catch (Exception paramString2) {}
    return paramString1;
  }
  
  public static String getResourceStr(int paramInt)
  {
    return Globals.getApplication().getString(paramInt);
  }
  
  public static final String getUrlSid(String paramString)
  {
    if (paramString == null) {}
    do
    {
      return "";
      i = paramString.indexOf("sid=");
    } while (i == -1);
    paramString = paramString.substring(i);
    int i = paramString.indexOf("&");
    if (i != -1) {
      return paramString.substring("sid=".length(), i);
    }
    return paramString.substring("sid=".length());
  }
  
  public static final String isAjaxPortParameter(String paramString1, String paramString2)
  {
    String str = paramString1;
    if (paramString1.indexOf("ajaxport=") == -1) {
      if (paramString1.indexOf("?") == -1) {
        break label66;
      }
    }
    label66:
    for (paramString2 = "&" + paramString2;; paramString2 = "?" + paramString2)
    {
      str = paramString1 + paramString2;
      return str;
    }
  }
  
  public static boolean isEmpty(Object paramObject)
  {
    if (paramObject == null) {}
    while (((paramObject instanceof String)) && (((String)paramObject).equals(""))) {
      return true;
    }
    return false;
  }
  
  public static boolean isEmpty(ArrayList<Object> paramArrayList)
  {
    return (paramArrayList == null) || (paramArrayList.size() == 0);
  }
  
  public static boolean isExistAlipayApkInAssests(Context paramContext)
  {
    boolean bool2 = false;
    paramContext = paramContext.getAssets();
    try
    {
      paramContext = paramContext.list("");
      int i = 0;
      for (;;)
      {
        boolean bool1 = bool2;
        if (i < paramContext.length)
        {
          bool1 = paramContext[i].equals("mobile_sp.apk");
          if (bool1) {
            bool1 = true;
          }
        }
        else
        {
          return bool1;
        }
        i += 1;
      }
      return false;
    }
    catch (IOException paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static final boolean isIndex(String paramString, String[] paramArrayOfString)
  {
    if ((paramString == null) || (paramArrayOfString == null)) {
      return false;
    }
    int j = paramArrayOfString.length;
    int i = 0;
    label15:
    if (i < j) {
      if (paramArrayOfString[i] != null) {
        break label33;
      }
    }
    label33:
    while (paramString.indexOf(paramArrayOfString[i]) == -1)
    {
      i += 1;
      break label15;
      break;
    }
    return true;
  }
  
  public static boolean isIntentAvailable(Context paramContext, String paramString)
  {
    return paramContext.getPackageManager().queryIntentActivities(new Intent(paramString), 65536).size() > 0;
  }
  
  public static boolean isLephone()
  {
    return false;
  }
  
  public static boolean isMobile_spExist(Context paramContext, String paramString)
  {
    boolean bool2 = false;
    paramContext = paramContext.getPackageManager();
    boolean bool1 = bool2;
    int i;
    if (paramContext != null)
    {
      paramContext = paramContext.getInstalledPackages(0);
      i = 0;
    }
    for (;;)
    {
      bool1 = bool2;
      if (i < paramContext.size())
      {
        if (((PackageInfo)paramContext.get(i)).packageName.equalsIgnoreCase(paramString)) {
          bool1 = true;
        }
      }
      else {
        return bool1;
      }
      i += 1;
    }
  }
  
  public static boolean isNotEmpty(String paramString)
  {
    return (paramString != null) && (!"".equals(paramString));
  }
  
  public static boolean isNotEmpty(ArrayList<Object> paramArrayList)
  {
    return (paramArrayList != null) && (paramArrayList.size() != 0);
  }
  
  public static final String isParameter(String paramString1, String paramString2)
  {
    String str = paramString1;
    if (paramString1.indexOf("ttid=") == -1) {
      if (paramString1.indexOf("?") == -1) {
        break label66;
      }
    }
    label66:
    for (paramString2 = "&" + paramString2;; paramString2 = "?" + paramString2)
    {
      str = paramString1 + paramString2;
      return str;
    }
  }
  
  public static final boolean isRegularIndex(String paramString, String[] paramArrayOfString)
  {
    if ((paramString == null) || (paramArrayOfString == null)) {
      return false;
    }
    int j = paramArrayOfString.length;
    int i = 0;
    label15:
    if (i < j) {
      if (paramArrayOfString[i] != null) {
        break label33;
      }
    }
    label33:
    while (!paramString.matches(paramArrayOfString[i]))
    {
      i += 1;
      break label15;
      break;
    }
    return true;
  }
  
  public static final String isXnParameter(String paramString1, String paramString2)
  {
    String str = paramString1;
    if (paramString1.indexOf("xn=") == -1) {
      if (paramString1.indexOf("?") == -1) {
        break label66;
      }
    }
    label66:
    for (paramString2 = "&" + paramString2;; paramString2 = "?" + paramString2)
    {
      str = paramString1 + paramString2;
      return str;
    }
  }
  
  public static void myLog(String paramString1, String paramString2)
  {
    new StringBuilder().append(paramString1).append("").toString();
    new StringBuilder().append(paramString2).append("").toString();
  }
  
  public static void onAllActivityResume(Activity paramActivity) {}
  
  public static void onDestroyJsonSharepreferences()
  {
    addJsonSharepreferences("categroyall_shared", null);
    addJsonSharepreferences("information_flag_shared", "false");
    addJsonSharepreferences("coolnavigation_flag_shared", "false");
    addJsonSharepreferences("weibo_emotions_flag_shared", "false");
  }
  
  public static void openFile(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.addFlags(268435456);
    localIntent.setDataAndType(Uri.parse("file://" + paramString), "application/vnd.android.package-archive");
    paramContext.startActivity(localIntent);
  }
  
  public static int percent(float paramFloat1, float paramFloat2)
  {
    return (int)(paramFloat1 / paramFloat2 * 100.0F);
  }
  
  public static int percent(int paramInt1, int paramInt2)
  {
    return paramInt1 * 100 / paramInt2;
  }
  
  public static String replaceParam(String paramString1, String paramString2, String paramString3)
  {
    try
    {
      Object localObject1 = Uri.parse(paramString1);
      Object localObject2 = ((Uri)localObject1).getQueryParameter(paramString2);
      if (localObject2 == null)
      {
        localObject2 = ((Uri)localObject1).buildUpon();
        localObject1 = ((Uri)localObject1).getPath();
        if ((localObject1 == null) || (((String)localObject1).length() == 0)) {
          ((Uri.Builder)localObject2).appendPath("");
        }
        return ((Uri.Builder)localObject2).appendQueryParameter(paramString2, paramString3).toString();
      }
      if ("".equals(localObject2)) {
        return paramString1.replace(paramString2 + "=", paramString2 + "=" + paramString3);
      }
      paramString2 = paramString1.replace((CharSequence)localObject2, paramString3);
      return paramString2;
    }
    catch (Exception paramString2)
    {
      TaoLog.Loge("Taobao", "" + paramString2.getMessage());
    }
    return paramString1;
  }
  
  public static boolean retrieveApkFromAssets(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      paramContext = paramContext.getAssets().open(paramString1);
      paramString1 = new File(paramString2);
      if (!paramString1.getParentFile().exists()) {
        paramString1.getParentFile().mkdirs();
      }
      if (paramString1.exists()) {
        paramString1.delete();
      }
      paramString1.createNewFile();
      paramString1 = new FileOutputStream(paramString1);
      paramString2 = new byte['Ѐ'];
      for (;;)
      {
        int i = paramContext.read(paramString2);
        if (i <= 0) {
          break;
        }
        paramString1.write(paramString2, 0, i);
      }
      paramString1.close();
    }
    catch (IOException paramContext)
    {
      paramContext.printStackTrace();
      return false;
    }
    paramContext.close();
    return true;
  }
  
  public static void shareByIntent(String paramString, Activity paramActivity)
  {
    try
    {
      Intent localIntent = new Intent("android.intent.action.SEND");
      localIntent.setType("text/plain");
      localIntent.putExtra("android.intent.extra.TEXT", paramString);
      paramActivity.startActivity(Intent.createChooser(localIntent, "分享"));
      return;
    }
    catch (Exception paramString)
    {
      paramActivity = Toast.makeText(Globals.getApplication(), "无分享内容", 5000);
      paramActivity.setGravity(17, 0, 0);
      paramActivity.show();
      paramString.printStackTrace();
    }
  }
  
  public static final void showToast(int paramInt)
  {
    showToast(Globals.getApplication().getString(paramInt));
  }
  
  public static final void showToast(int paramInt1, int paramInt2)
  {
    String str = Globals.getApplication().getString(paramInt1);
    Toast localToast = null;
    if (0 == 0) {
      localToast = Toast.makeText(Globals.getApplication(), "", paramInt2);
    }
    localToast.setText(str);
    localToast.show();
  }
  
  public static final void showToast(Context paramContext, String paramString)
  {
    paramContext = null;
    if (0 == 0) {
      paramContext = Toast.makeText(Globals.getApplication(), "", 3000);
    }
    paramContext.setText(paramString);
    paramContext.show();
  }
  
  public static final void showToast(Context paramContext, String paramString, int paramInt)
  {
    paramContext = null;
    if (0 == 0) {
      paramContext = Toast.makeText(Globals.getApplication(), "", paramInt);
    }
    paramContext.setText(paramString);
    paramContext.show();
  }
  
  public static final void showToast(String paramString)
  {
    showToast(null, paramString);
  }
  
  public static int touchLen(int paramInt1, int paramInt2)
  {
    return getABS(paramInt1 - paramInt2);
  }
  
  /* Error */
  public static void write(String paramString1, String paramString2, int paramInt)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aload_0
    //   3: ifnull +13 -> 16
    //   6: aload_0
    //   7: invokevirtual 1030	java/lang/String:length	()I
    //   10: istore_2
    //   11: iload_2
    //   12: iconst_1
    //   13: if_icmpge +22 -> 35
    //   16: iconst_0
    //   17: ifeq +11 -> 28
    //   20: new 1358	java/lang/NullPointerException
    //   23: dup
    //   24: invokespecial 1359	java/lang/NullPointerException:<init>	()V
    //   27: athrow
    //   28: return
    //   29: astore_0
    //   30: aload_0
    //   31: invokevirtual 1340	java/lang/Exception:printStackTrace	()V
    //   34: return
    //   35: new 1361	java/io/FileWriter
    //   38: dup
    //   39: new 981	java/lang/StringBuilder
    //   42: dup
    //   43: invokespecial 982	java/lang/StringBuilder:<init>	()V
    //   46: invokestatic 1366	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   49: invokevirtual 1367	java/io/File:getPath	()Ljava/lang/String;
    //   52: invokevirtual 986	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   55: ldc_w 1369
    //   58: invokevirtual 986	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   61: aload_1
    //   62: invokevirtual 986	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   65: invokevirtual 990	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   68: iconst_1
    //   69: invokespecial 1372	java/io/FileWriter:<init>	(Ljava/lang/String;Z)V
    //   72: astore_1
    //   73: aload_1
    //   74: ifnonnull +18 -> 92
    //   77: aload_1
    //   78: ifnull -50 -> 28
    //   81: aload_1
    //   82: invokevirtual 1373	java/io/FileWriter:close	()V
    //   85: return
    //   86: astore_0
    //   87: aload_0
    //   88: invokevirtual 1340	java/lang/Exception:printStackTrace	()V
    //   91: return
    //   92: aload_1
    //   93: aload_0
    //   94: invokevirtual 1375	java/io/FileWriter:write	(Ljava/lang/String;)V
    //   97: aload_1
    //   98: ifnull -70 -> 28
    //   101: aload_1
    //   102: invokevirtual 1373	java/io/FileWriter:close	()V
    //   105: return
    //   106: astore_0
    //   107: aload_0
    //   108: invokevirtual 1340	java/lang/Exception:printStackTrace	()V
    //   111: return
    //   112: astore_0
    //   113: aconst_null
    //   114: astore_1
    //   115: aload_1
    //   116: ifnull -88 -> 28
    //   119: aload_1
    //   120: invokevirtual 1373	java/io/FileWriter:close	()V
    //   123: return
    //   124: astore_0
    //   125: aload_0
    //   126: invokevirtual 1340	java/lang/Exception:printStackTrace	()V
    //   129: return
    //   130: astore_0
    //   131: aload_3
    //   132: astore_1
    //   133: aload_1
    //   134: ifnull +7 -> 141
    //   137: aload_1
    //   138: invokevirtual 1373	java/io/FileWriter:close	()V
    //   141: aload_0
    //   142: athrow
    //   143: astore_1
    //   144: aload_1
    //   145: invokevirtual 1340	java/lang/Exception:printStackTrace	()V
    //   148: goto -7 -> 141
    //   151: astore_0
    //   152: goto -19 -> 133
    //   155: astore_0
    //   156: goto -41 -> 115
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	159	0	paramString1	String
    //   0	159	1	paramString2	String
    //   0	159	2	paramInt	int
    //   1	131	3	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   20	28	29	java/lang/Exception
    //   81	85	86	java/lang/Exception
    //   101	105	106	java/lang/Exception
    //   6	11	112	java/lang/Exception
    //   35	73	112	java/lang/Exception
    //   119	123	124	java/lang/Exception
    //   6	11	130	finally
    //   35	73	130	finally
    //   137	141	143	java/lang/Exception
    //   92	97	151	finally
    //   92	97	155	java/lang/Exception
  }
}
