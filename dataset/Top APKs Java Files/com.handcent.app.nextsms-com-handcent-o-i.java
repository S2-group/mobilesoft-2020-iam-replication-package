package com.handcent.o;

import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import com.google.android.mms.util.SqliteWrapper;
import com.handcent.common.ad;
import com.handcent.common.dd;
import com.handcent.common.em;
import com.handcent.common.en;
import com.handcent.n.ac;
import com.handcent.n.ae;
import com.handcent.nextsms.MmsApp;
import com.handcent.nextsms.views.hcautz;
import com.handcent.sms.i.bx;
import com.handcent.sms.model.HcSkin;
import com.handcent.sms.ui.aaf;
import java.io.File;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

public class i
{
  public static final String KEY_ACCOUNT_NAME = "hc_account_name";
  public static final String SUFFIX;
  public static int aPY = 0;
  public static int bBF = 0;
  public static int bBG = 0;
  public static final int bOz = -174846;
  public static final int bVA = 6001;
  public static final int bVB = 1114133;
  public static final int bVC = 60;
  public static final int bVD = 93;
  public static final int bVE = 8947848;
  public static final int bVF = 1;
  public static final int bVG = 2;
  public static final int bVH = 3;
  public static final int bVI = 4;
  public static final int bVJ = 0;
  public static final int bVK = 31;
  public static final int bVL = 16;
  public static final int bVM = 4;
  public static final int bVN = 19;
  public static final int bVO = 51;
  public static final int bVP = 46;
  public static final int bVQ = -17884;
  public static final int bVR = 67108863;
  public static final int bVS = 5242881;
  public static final int bVT = 5242882;
  public static final int bVU = 1711276033;
  public static final int bVV = 1711276034;
  public static final int bVW = 1711276035;
  public static final int bVX = 1711276036;
  public static final int bVY = 1711276037;
  public static final int bVZ = 1711276038;
  public static final int bVx = 1114129;
  public static final int bVy = 1114130;
  public static final int bVz = 1114131;
  public static final String bWA = "ff4331c2b97d4fe3a50546364c8d07ec";
  public static final String bWB = "92b03c7d449449c69d2245316dfccafa";
  public static final String bWC = "4028cb8b2ee7f47f012f000bee2f021b";
  public static final long bWD = 1437633761293485L;
  public static final long bWE = 1431974249830585L;
  public static final String bWF = "ca-app-pub-5850817660349412/9033973236";
  public static final int bWG = 1;
  public static final int bWH = 2;
  public static final int bWI = 3;
  public static final String bWJ = "d0c8a89b5af240018400b7ed5e0fc5d5";
  public static final String bWK = "pkey_split_thread";
  public static final String bWL = "pkey_sig_enable";
  public static final String bWM = "edit_large_key_editshow";
  private static final String bWN = "edit_large_key_always";
  private static final String bWO = "edit_large_key_none";
  public static boolean bWP = false;
  public static final String bWQ = hcautz.getInstance().a1("E0A7CE801B360745AB0EE990BF9CFEB1");
  public static final int bWR = Color.rgb(155, 158, 239);
  public static final int bWS = Color.rgb(155, 158, 239);
  public static final int bWT = Color.rgb(35, 206, 224);
  public static final int bWU = Color.rgb(255, 204, 153);
  public static final int bWV = Color.rgb(255, 204, 102);
  public static final int bWW = 10010;
  public static final int bWX = Color.rgb(0, 0, 0);
  public static final int bWY = Color.rgb(0, 0, 0);
  public static final int bWZ = Color.rgb(209, 255, 255);
  public static final int bWa = 4194305;
  public static final int bWb = 4194306;
  public static final int bWc = 4194307;
  public static final int bWd = 4194308;
  public static final int bWe = 4194309;
  public static final int bWf = 4194310;
  public static final int bWg = 4194311;
  public static final int bWh = 4194312;
  public static final int bWi = 4194313;
  public static final int bWj = 4194320;
  public static final int bWk = 4194321;
  public static final int bWl = 4194322;
  public static final int bWm = 4194323;
  public static final int bWn = 4194324;
  public static final int bWo = 4194325;
  public static final int bWp = 67108886;
  public static final int bWq = 67108887;
  public static final int bWr = 67108889;
  public static final int bWs = 67108896;
  public static final int bWt = 67108897;
  public static final int bWu = 67108898;
  public static final int bWv = 4194339;
  public static final int bWw = 67108900;
  public static final long bWx = 2145945600000L;
  public static final String bWy = "e145e6e77c5b47928c32e52a6a19a50c";
  public static final String bWz = "7495c86e234145d2ac30ae7059cff98d";
  public static final String bXA;
  public static final String bXB;
  public static final String bXC;
  public static final String bXD;
  public static final String bXE;
  public static final String bXF;
  public static final String bXG;
  public static final String bXH;
  public static final String bXI;
  public static final String bXJ;
  public static final String bXK;
  public static final String bXL;
  public static final String bXM;
  public static final String bXN;
  public static final String bXO;
  public static final String bXP;
  public static final String bXQ;
  public static final String bXR;
  public static final String bXS;
  public static final String bXT;
  public static final String bXU;
  public static final String bXV;
  public static final String bXW;
  public static final String bXX;
  public static final String bXY;
  public static final String bXZ;
  public static final int bXa = Color.rgb(111, 213, 68);
  public static final int bXb = Color.rgb(228, 228, 228);
  public static final int bXc = Color.rgb(174, 225, 83);
  public static final int bXd = Color.rgb(206, 247, 239);
  public static final int bXe = Color.rgb(255, 255, 255);
  public static final int bXf = Color.rgb(0, 0, 0);
  public static final int bXg = Color.rgb(0, 0, 0);
  public static final String bXh = "default";
  public static final String bXi = "full24";
  public static final String bXj = "full12";
  public static final int bXk = 0;
  public static final int bXl = 1;
  public static final int bXm = 2;
  public static final int bXn = 0;
  public static final int bXo = 1;
  public static final int bXp = 2;
  public static final int bXq = 3;
  public static final int bXr = 0;
  public static final int bXs = 1;
  public static final int bXt = 2;
  public static final int bXu = 3;
  public static final String bXv = Environment.getExternalStorageDirectory() + "/handcent/";
  public static final Uri bXw = Uri.parse(hcautz.getInstance().a1("BCD3CD4F2E015E1C96078D1705312A2D9F9FA4454E6EB0F542679BCB0273406D30E9900D16B38A1D"));
  public static final String bXx = hcautz.getInstance().a1("108CC56F4869C848D8EB1AF2E6A8A9ED");
  public static final String bXy = hcautz.getInstance().a1("F658B13329F8800D");
  public static final String bXz = hcautz.getInstance().a1("37E2C52F5BBD5319D8EB1AF2E61BA9ED");
  public static final String bYA;
  public static final String bYB;
  public static final String bYC;
  public static final String bYD;
  public static final String bYE;
  public static final String bYF;
  public static final String bYG;
  public static final String bYH;
  public static final String bYI;
  public static final String bYJ;
  public static final String bYK;
  public static final String bYL;
  public static final String bYM;
  public static final String bYN;
  public static final String bYO;
  public static final String bYP;
  public static final String bYQ;
  public static final String bYR;
  public static final String bYS;
  public static final String bYT;
  public static final String bYU;
  public static final String bYV;
  public static final String bYW;
  public static final String bYX;
  public static final String bYY;
  public static final String bYZ;
  public static final String bYa;
  public static final String bYb;
  public static final String bYc;
  public static final String bYd;
  public static final String bYe;
  public static final String bYf;
  public static final String bYg;
  public static final String bYh;
  public static final String bYi;
  public static final String bYj;
  public static final String bYk;
  public static final String bYl;
  public static final String bYm;
  public static final String bYn;
  public static final String bYo;
  public static final String bYp;
  public static final String bYq;
  public static final String bYr;
  public static final String bYs;
  public static final String bYt;
  public static final String bYu;
  public static final String bYv;
  public static final String bYw;
  public static final String bYx;
  public static final String bYy;
  public static final String bYz;
  public static final String bZA = "pkey_handcent_rec_bubble_color";
  public static final String bZB = "pkey_handcent_send_bubble_color";
  public static final String bZC = "pkey_rec_bubble_color";
  public static final String bZD = "pkey_send_bubble_color";
  public static final String bZE = "pkey_android_rec_background_color";
  public static final String bZF = "pkey_android_send_background_color";
  public static final String bZG = "pkey_android_rec_font_color";
  public static final String bZH = "pkey_android_send_font_color";
  public static final String bZI = "pkey_android_datetime_font_color";
  public static final String bZJ = "pkey_bubble_rec_usegrad";
  public static final String bZK = "pkey_bubble_send_usegrad";
  public static final String bZL = "pkey_rec_start_color";
  public static final String bZM = "pkey_rec_end_color";
  public static final String bZN = "pkey_send_start_color";
  public static final String bZO = "pkey_send_end_color";
  public static final String bZP = "pkey_rec_font_color";
  public static final String bZQ = "pkey_send_font_color";
  public static final String bZR = "pkey_datetime_font_color";
  public static final String bZS = "pref_key_usepic";
  public static final String bZT = "pref_key_use_contact_pic";
  public static final String bZU = "pref_key_use_themecolor";
  public static final String bZV = "pref_key_background_color";
  public static final String bZW = "pref_key_quicktext_list";
  public static final String bZX = "pkey_bubble_hyperlink";
  public static final String bZY = "pkey_bubble_showname";
  public static final String bZZ = "pkey_bubble_showdate";
  public static final String bZa;
  public static final String bZb;
  public static final String bZc;
  public static final String bZd;
  public static final String bZe;
  public static final String bZf;
  public static final String bZg;
  public static final int bZh = 54;
  public static final int bZi = 54;
  public static final int bZj = 32;
  public static final int bZk = 32;
  public static final int bZl;
  public static final int bZm;
  public static final int bZn;
  public static final int bZo;
  public static final String bZp = "제목없음";
  public static final String bZq;
  public static final int bZr = 0;
  public static final int bZs = 1;
  public static int bZt = 0;
  public static final int bZu = 6;
  public static final int bZv = 5;
  public static final int bZw = 16;
  public static final String bZx = "auto_convert_mms";
  public static final Boolean bZy;
  public static final String bZz = "pkey_theme_style";
  public static final String bcX = "hc_nickname";
  public static final String bda = "hc_signature";
  public static final int btb = 67108888;
  public static final String caA = "pref_blur_plus";
  public static final String caB = "pre_noti_shownum";
  public static final String caC = "pre_noti_show_text";
  public static final String caD = "show_as_flatslideshow1";
  public static final String caE = "pref_auto_reset_counter";
  public static final String caF = "pref_auto_reset_counter_history";
  public static final String caG = "pref_max_mms_size_new";
  public static final String caH = "pref_show_threadmessages_counter";
  public static final String caI = "pref_split160_ex";
  public static final String caJ = "pref_sent_notification";
  public static final String caK = "pref_sent_notif_ringtone";
  public static final String caL = "pref_fail_notif_ringtone";
  public static final String caM = "pref_sent_noti_vibrate_type";
  public static final String caN = "pref_sent_notif_vibrate_pattern";
  public static final String caO = "pref_sent_vibrate_pattern_custom";
  public static final String caP = "pref_blur_social_network_picture_source";
  public static final String caQ = "pref_social_network_show_contact";
  public static final String caR = "pref_save_directory";
  public static final String caS = "pref_popup_text_counter";
  public static final String caT = "pref_ext_font_search";
  public static final String caU = "pref_incoming_font";
  public static final String caV = "pref_outgoing_font";
  public static final String caW = "pref_contact_font";
  public static final String caX = "pref_editbox_font";
  public static final String caY = "pref_date_font";
  public static final String caZ = "pref_conversation_date_font";
  public static final String caa = "pkey_composemessage_keyboardon";
  public static final String cab = "pkey_font_pack";
  public static final String cac = "peky_font_file";
  public static final String cad = "pref_font_item";
  public static final String cae = "pref_font_value";
  public static final String caf = "pref_font_initsize";
  public static final String cag = "pref_font_size";
  public static final String cah = "pref_default_font";
  public static final String cai = "pref_client_delivery_report";
  public static final String caj = "pref_sms_counter";
  public static final String cak = "pref_mms_counter";
  public static final String cal = "enable_show_earier";
  public static final String cam = "bubble_transposition";
  public static final String can = "lite_space";
  public static final String cao = "pref_display_msg_counter";
  public static final String cap = "pref_only_show_mobile";
  public static final String caq = "pref_input_mode";
  public static final String car = "pref_mms_agent";
  public static final String cas = "pref_mms_agent_custom";
  public static final String cat = "pref_message_timestamp_source";
  public static final String cau = "pref_message_timestamp_adjust1";
  public static final String cav = "pref_receive_message_timestamp_adjust";
  public static final String caw = "pref_notif_sound_incall";
  public static final String cax = "pref_notif_vibrate_incall";
  public static final String cay = "pref_split_160";
  public static final String caz = "pref_popup_autorotate";
  public static final String cbA = "pref_emoji_send_type";
  public static final String cbB = "pref_emoji_default";
  public static final String cbC = "pref_sms_email_gateway";
  public static final String cbD = "pkey_editbox_font_color";
  public static final String cbE = "pkey_popup_keyboard_style";
  public static final String cbF = "pkey_show_full_editor";
  public static final String cbG = "pkey_show_full_editor_method";
  public static final String cbH = "pkey_full_editor_font";
  public static final String cbI = "pkey_full_editor_font_color";
  public static final String cbJ = "pkey_default_application";
  public static final String cbK = "pkey_led_plus";
  public static final String cbL = "pref_dealy_send";
  public static final String cbM = "pref_advance_mms_download";
  public static final String cbN = "pref_contact_show_image";
  public static final String cbO = "pref_contact_scroller_style";
  public static final String cbP = "pref_enable_scene";
  public static final String cbQ = "pref_active_scene";
  public static final String cbR = "pref_driving_safe";
  public static final String cbS = "pref_driving_audio_output";
  public static final String cbT = "pref_driving_amount_word";
  public static final String cbU = "pref_driving_auto_start";
  public static final String cbV = "pref_driving_launch_bluetooth";
  public static final String cbW = "pref_driving_run_docked";
  public static final String cbX = "pref_auto_reply_scene";
  public static final String cbY = "pref_auto_reply_text";
  public static final String cbZ = "pref_scene_play_message";
  public static final String cba = "pref_subject_font";
  public static final String cbb = "pref_app_language";
  public static final String cbc = "pkey_theme_type";
  public static final String cbd = "pref_greek_sms_mode";
  public static final String cbe = "pref_czech_sms_mode";
  public static final String cbf = "pref_slovak_sms_mode";
  public static final String cbg = "pref_frech_sms_mode_new";
  public static final String cbh = "pref_polish_sms_mode";
  public static final String cbi = "pref_spanish_sms_mode";
  public static final String cbj = "pref_korean_sms_mode";
  public static final String cbk = "pref_quick_attach_button";
  public static final String cbl = "pref_progtuguese_sms_mode";
  public static final String cbm = "pref_use_handcent_ringtone";
  public static final String cbn = "pref_auto_resize_picture";
  public static final String cbo = "pref_sent_show_notif";
  public static final String cbp = "pref_remind_different_sound";
  public static final String cbq = "pref_remind_sound";
  public static final String cbr = "pref_prevent_resend";
  public static final String cbs = "pkey_incoming_textlink_color";
  public static final String cbt = "pkey_outgoing_textlink_color";
  public static final String cbu = "pref_popup_custom_edit_font";
  public static final String cbv = "pref_send_confirm";
  public static final String cbw = "pref_smileyes";
  public static final String cbx = "pref_popup_smileys";
  public static final String cby = "pref_emoji_styles2";
  public static final String cbz = "pref_emoji_encoding";
  public static final String ccA = "pref_mms_subject_encoding";
  public static final String ccB = "pref_mms_enable_data_temp";
  public static final String ccC = "font_enable";
  public static final String ccD = "pref_wear_device_notification";
  public static final String ccE = "pref_wear_device_quick_suggestion";
  public static final String ccF = "pref_show_new_messages_counter";
  public static final String ccG = "pref_edit_large";
  public static final String ccH = "pref_mms_framework";
  public static final String ccI = "pref_dual_sim_send_placement";
  public static String ccJ;
  public static String ccK;
  public static Boolean ccL;
  public static Boolean ccM;
  public static Boolean ccN;
  public static Boolean ccO;
  public static Boolean ccP;
  public static Boolean ccQ;
  public static final boolean ccR = true;
  public static final boolean ccS = false;
  public static final boolean ccT = true;
  public static final String ccU = "smart";
  public static final String ccV = "";
  public static final String ccW = "";
  public static final String ccX = "80";
  public static final boolean ccY = true;
  public static final boolean ccZ = true;
  public static final String cca = "pref_scene_play_sender_name";
  public static final String ccb = "pref_scene_play_message_content";
  public static final String ccc = "pref_scene_reply_with_voice";
  public static final String ccd = "pref_autodelete_old_message";
  public static final String cce = "pref_autodelete_text";
  public static final String ccf = "pref_autodelete_multi";
  public static final String ccg = "pref_enable_mms_signature";
  public static final String cch = "pref_autoreply_opt_missed_call";
  public static final String cci = "pref_autoreply_opt_missed_message";
  public static final String ccj = "pref_autoreply_opt_known_contacts";
  public static final String cck = "pref_autoreply_opt_reply_once";
  public static final String ccl = "pref_group_mms";
  public static final String ccm = "pref_key_mms_auto_retrieval";
  public static final String ccn = "pref_key_send_sms_samsung";
  public static final String cco = "pref_notify_after_downloaded";
  public static final String ccp = "pref_key_show_close_dialog";
  public static final String ccq = "pref_mms_mmsc";
  public static final String ccr = "pref_mms_proxy_port";
  public static final String ccs = "pref_mms_proxy";
  public static final String cct = "pref_mms_carrier";
  public static final String ccu = "pref_recent_smile_tag";
  public static final String ccv = "pref_using_recent_smile";
  public static final String ccw = "pref_fb_use_profile_pic";
  public static final String ccx = "pref_fb_crop_pic";
  public static final String ccy = "pref_debug_mode";
  public static final String ccz = "pref_debug_mode_last";
  public static final String cdA = "pkey_full_editor_font";
  public static final String cdB = "click";
  public static final String cdC = "enable";
  public static final int cdD = -16777216;
  public static final boolean cdE = true;
  public static final String cdF = "default";
  public static final String cdG = "ask";
  public static final String cdH = "iphone";
  public static final boolean cdI = true;
  public static final String cdJ = "system";
  public static final boolean cdK = false;
  public static final boolean cdL = false;
  public static final int cdM = -14137089;
  public static final boolean cdN = false;
  public static final String cdO = "content://settings/system/notification_sound";
  public static final boolean cdP = false;
  public static final boolean cdQ = false;
  public static final boolean cdR = false;
  public static final boolean cdS = true;
  public static final String cdT = "disable";
  public static final String cdU = "default";
  public static final String cdV = "disable";
  public static final String cdW = "disable";
  public static final String cdX = "disable";
  public static final boolean cdY = true;
  public static final String cdZ = "default";
  public static final boolean cda = false;
  public static final boolean cdb = false;
  public static final boolean cdc = false;
  public static final boolean cdd = true;
  public static final int cde = 30;
  public static final int cdf = 200;
  public static final boolean cdg = false;
  public static final boolean cdh = false;
  public static final boolean cdi = false;
  public static final boolean cdj = false;
  public static final boolean cdk = false;
  public static final boolean cdl = true;
  public static final boolean cdm = false;
  public static final boolean cdn = false;
  public static final boolean cdo = true;
  public static final int cdp = 25;
  public static final String cdq = "auto";
  public static final boolean cdr = false;
  public static final String cds = "normal";
  public static final boolean cdt = true;
  public static final String cdu = "iphone";
  public static final boolean cdv = true;
  public static final String cdw = "default";
  public static final boolean cdx = false;
  public static final boolean cdy = false;
  public static final String cdz = "disable";
  public static final int ceA = -16777216;
  public static final Boolean ceB;
  public static final boolean ceC = false;
  public static final String ceD = "none";
  public static final boolean ceE = true;
  public static final String ceF = "18";
  public static final boolean ceG = true;
  public static final boolean ceH = false;
  public static final boolean ceI = true;
  public static final boolean ceJ = false;
  public static final String ceK = "normal";
  public static final String ceL = "Android-Mms/2.0";
  public static final Boolean ceM;
  public static final String ceN = "default";
  public static final String ceO = "default";
  public static final boolean ceP = false;
  public static final boolean ceQ = false;
  public static final boolean ceR = false;
  public static final boolean ceS = true;
  public static final boolean ceT = true;
  public static final Boolean ceU;
  public static final String ceV;
  public static final boolean ceW = true;
  public static final boolean ceX = true;
  public static final String ceY = "default";
  public static final String ceZ = "pkey_text_autocap";
  public static final String cea = "default";
  public static final boolean ceb = true;
  public static final String cec = "disable";
  public static final String ced = "bubble";
  public static final int cee;
  public static final int cef;
  public static final int ceg;
  public static final int ceh;
  public static final int cei;
  public static final int cej;
  public static final int cek;
  public static final int cel;
  public static final int cem;
  public static final int cen;
  public static final Boolean ceo;
  public static final Boolean cep;
  public static final int ceq;
  public static final int cer;
  public static final int ces;
  public static final int cet;
  public static final int ceu;
  public static final int cev;
  public static final int cew = -6648968;
  public static final Boolean cex;
  public static final Boolean cey;
  public static final Boolean cez;
  public static final String cfA = "android";
  public static final String cfB = "handcent";
  public static String[] cfC;
  public static final String cfD = "current_handcent_font_type";
  public static final String cfE = "1";
  public static final String cfF = "2";
  public static final String cfG = "3";
  public static final String cfH = "4";
  public static final String cfI = "0";
  public static final String cfJ = "0";
  public static final int cfK = 1;
  public static final int cfL = 2;
  public static final int cfM = 3;
  public static final int cfN = 4;
  public static final int cfO = 3;
  public static final int cfP = 4;
  public static final int cfQ = 4;
  public static final String cfR = "pref_emoji_version";
  public static int cfS = 0;
  public static final String cfT = "pref_plugin_notice";
  public static final boolean cfU = true;
  public static final String cfV = "pref_key_enable_notifications";
  public static final String cfW = "pref_key_testnotif";
  public static final String cfX = "pref_key_ringtone";
  public static final String cfY = "pref_key_notif_privacy";
  public static final String cfZ = "pref_key_vibrate";
  public static final String cfa = "pkey_text_autotext";
  public static final String cfb = "pkey_text_autotextlist";
  public static final Boolean cfc;
  public static final Boolean cfd;
  public static final Boolean cfe;
  public static final String cff = "default";
  public static final String cfg = "-1";
  public static final String cfh = "default";
  public static final String cfi = "0";
  public static final Boolean cfj;
  public static final Boolean cfk;
  public static final String cfl = "download";
  public static final String cfm;
  public static final String cfn;
  public static final String cfo;
  public static final String cfp;
  public static final String cfq = "light";
  public static final String cfr = "ar";
  public static final String cfs = "noLang";
  public static final int cft = 10;
  public static final int cfu = 5000;
  public static final String cfv = "need_login_result";
  public static final String cfw = "need_register_result";
  public static final String cfx = "bind_number_count";
  public static final int cfy = 5;
  public static final String cfz;
  public static final Boolean cgA;
  public static final String cgB = "Blue";
  public static final String cgC = "Blue";
  public static final String cgD = "Green";
  public static final String cgE = "0";
  public static final Boolean cgF;
  public static final String cgG = "2";
  public static final String cgH = "5";
  public static final Boolean cgI;
  public static final String cgJ = "1";
  public static final Boolean cgK;
  public static final String cgL = "custom";
  public static final String cgM = "custom";
  public static final String cgN;
  public static final String cgO = "0";
  public static final String cgP = "custom";
  public static final String cgQ;
  public static final String cgR = "5000";
  public static final Boolean cgS;
  public static final int cgT = 1112;
  public static final String cgU = "enabled";
  public static final String cgV = "timeout";
  public static final String cgW = "dimscreen";
  public static final String cgX = "privacy";
  public static final String cgY = "markread";
  public static final String cgZ = "blur";
  public static final String cga = "pref_key_vibrate_pattern";
  public static final String cgb = "pkey_blink_led";
  public static final String cgc = "pkey_led_color1";
  public static final String cgd = "pkey_led_color2";
  public static final String cge = "pkey_led_frequency";
  public static final String cgf = "notif_repeat";
  public static final String cgg = "notif_repeat_num";
  public static final String cgh = "notif_repeat_interval";
  public static final String cgi = "notif_repeat_screen_on";
  public static final String cgj = "vibrate_pattern_custom";
  public static final String cgk = "autoreset_pattern_custom";
  public static final String cgl = "vibrate_type";
  public static final String cgm = "flashled_pattern_custom";
  public static final String cgn = "pref_key_trackballl";
  public static final String cgo = "notification_screenon";
  public static final String cgp = "charm_notification";
  public static final String cgq = "pref_key_custom_led";
  public static final String cgr = "pref_notif_lock_screen";
  public static final Boolean cgs;
  public static final Boolean cgt;
  public static final Boolean cgu;
  public static final String cgv = "content://settings/system/notification_sound";
  public static final String cgw;
  public static final Boolean cgx;
  public static final Boolean cgy;
  public static final String cgz = "default";
  public static final Boolean chA;
  public static final Boolean chB;
  public static final Boolean chC;
  public static final Boolean chD;
  public static final Boolean chE;
  public static final Boolean chF;
  public static final String chG = "hc_convlist_background.png";
  public static final String chH;
  public static final String chI = "hc_convlist_background_land.png";
  public static final String chJ;
  public static final String chK = "hc_background.png";
  public static final String chL;
  public static final String chM = "hc_background_land.png";
  public static final String chN;
  public static final String chO = ".png";
  public static final String chP = "hc_background";
  public static final String chQ;
  public static final String chR = "hc_background_land";
  public static final String chS;
  public static final String chT = ".png";
  public static final String chU = "handcent_media";
  public static final String chV = "pkey_show_blacklist";
  public static final String chW = "pkey_blacklist";
  public static final String chX = "pkey_blacklist_threads";
  public static final Boolean chY;
  public static final String chZ = "";
  public static final String cha = "showonkeyguard";
  public static final String chb = "pkey_fast_reply";
  public static final String chc = "pkey_quick_reply_kb";
  public static final String chd = "pkey_popup_screen_on";
  public static final String che = "pkey_popup_window_mode";
  public static final String chf = "pkey_popup_autolink";
  public static final String chg = "popup_backkey";
  public static final String chh = "popup_confirm_deletion";
  public static final String chi = "popup_deletion_detail";
  public static final String chj = "widget_show_zero";
  public static final String chk = "popup_disable_keyguard";
  public static final String chl = "pref_always_backto_main";
  public static final String chm = "popup_over_lock_fix1";
  public static final Boolean chn;
  public static final Boolean cho;
  public static final String chp = "30";
  public static final Boolean chq;
  public static final Boolean chr;
  public static final Boolean chs;
  public static final Boolean cht;
  public static final Boolean chu;
  public static final Boolean chv;
  public static final Boolean chw;
  public static final Boolean chx;
  public static final String chy = "0";
  public static final Boolean chz;
  public static int ciA = 0;
  public static String ciB;
  public static String ciC;
  public static String ciD;
  public static boolean ciE = false;
  public static String ciF;
  public static boolean ciG = false;
  public static String ciH;
  public static boolean ciI = false;
  public static int[] ciJ;
  public static String[] ciK;
  public static String ciL;
  public static String ciM;
  public static String ciN;
  public static boolean ciO = false;
  private static String ciP;
  private static String ciQ;
  private static String ciR;
  private static String ciS;
  public static final String ciT = "ca-mb-app-pub-5850817660349412";
  public static final String ciU = "handcent";
  public static final String ciV;
  public static final String ciW;
  public static final String ciX = "local_channelid";
  public static final String ciY = "local_keywords";
  public static final String ciZ = "local_last_updatetime";
  public static final String cia = "";
  private static Animation cib;
  private static Animation cic;
  private static Animation cie;
  private static Animation cif;
  public static String cig;
  public static String cih;
  public static Boolean cii;
  public static String cij;
  public static String cik;
  public static String cil;
  public static String cim;
  public static String cin;
  public static String cio;
  public static String cip;
  public static String ciq;
  public static int cir = 0;
  public static int cis = 0;
  public static int cit = 0;
  public static int ciu = 0;
  public static int civ = 0;
  public static Boolean ciw;
  public static Boolean cix;
  public static int ciy = 0;
  public static int ciz = 0;
  public static String cjA;
  public static String cjB;
  public static String cjC;
  public static String cjD;
  public static Boolean cjE;
  public static Boolean cjF;
  public static String cjG;
  public static final boolean cjH = false;
  public static final boolean cjI = true;
  public static final boolean cjJ = true;
  public static final boolean cjK = false;
  public static final boolean cjL = false;
  public static final boolean cjM = false;
  public static final boolean cjN = true;
  public static final int cjO = 2;
  public static final int cjP = 0;
  public static final String cjQ = "3";
  public static final String cjR = "priv_move_return";
  public static final String cjS = "priv_sync_open";
  public static final String cjT = "priv_sync_notice";
  public static final String cjU = "priv_sync_init";
  public static final String cjV = "priv_data_upgrade";
  public static final String cjW = "priv_guide_completed";
  public static final String cjX = "priv_use_lock";
  public static final String cjY = "priv_lock_type";
  public static final String cjZ = "priv_num_pin";
  public static final String cja;
  public static final String cjb;
  public static final Long cjc;
  public static String cjd;
  public static Boolean cje;
  public static String cjf;
  public static int cjg = 0;
  public static String cjh;
  public static String cji;
  public static String cjj;
  public static String cjk;
  public static String cjl;
  public static String cjm;
  public static String cjn;
  public static String cjo;
  public static String cjp;
  public static String cjq;
  public static String cjr;
  public static String cjs;
  public static String cjt;
  public static Boolean cju;
  public static Boolean cjv;
  public static Boolean cjw;
  public static String cjx;
  public static String cjy;
  public static String cjz;
  public static final String ckA = "widget_show_mode";
  public static final String ckB = "1";
  public static final String ckC = "KEY_MY_INFO";
  public static final String ckD = "KEY_TIMER_SETTING";
  public static final String ckE = "hc_servicecancel";
  public static final String ckF = "hc_myfeatures";
  public static final String ckG = "hc_phone_authcode";
  public static final String ckH = "hc_phone_tmp";
  public static final String ckI = "hc_refresh_connect";
  public static final String ckJ = "hc_service_host";
  public static final String ckK = "hc_service_port";
  public static final String ckL = "hc_phonenum";
  public static final String ckM = "hc_allowquery";
  public static final String ckN = "hc_email";
  public static final String ckO = "hc_imactived";
  public static final String ckP = "hc_refreshtime";
  public static final String ckQ = "hc_password";
  public static final String ckR = "hc_mac";
  public static final String ckS = "hc_roomhistory";
  public static final String ckT = "hc_serviceshowmode";
  public static final String ckU = "hc_promotion_info";
  public static final String ckV = "hc_promotion_url";
  public static final String ckW = "hc_promotion_time";
  public static String ckX;
  public static String ckY;
  public static String ckZ;
  public static final String cka = "priv_lock_visible_pattern";
  public static final String ckb = "priv_lock_tactile_feedback";
  public static final String ckc = "priv_auto_backup_hour";
  public static final String ckd = "priv_auto_backup_minute";
  public static final String cke = "priv_auto_backup_valid_date";
  public static final String ckf = "priv_backup_ext_sdcard";
  public static final String ckg = "priv_lock_deadline";
  private static Animation ckh;
  private static Animation cki;
  public static String ckj;
  public static String ckk;
  public static String ckl;
  public static Boolean ckm;
  public static String ckn;
  public static int cko = 0;
  public static final String ckp = "Custom";
  public static String ckq;
  public static String ckr;
  public static final String cks = "hc_account_picture.png";
  public static final String ckt;
  public static final String cku = "widget_show_name";
  public static final Boolean ckv;
  public static final String ckw = "maximum_number_of_messages";
  public static final String ckx = "20";
  public static final String cky = "widget_auto_lock";
  public static final Boolean ckz;
  public static int clA = 0;
  public static int clB = 0;
  public static String clC;
  public static String clD;
  public static String clE;
  public static String clF;
  public static String clG;
  public static String clH;
  public static String clI;
  public static String clJ;
  public static String clK;
  public static String clL;
  public static String clM;
  public static String clN;
  public static String clO;
  public static String clP;
  public static String clQ;
  public static String clR;
  public static Boolean clS;
  public static Boolean clT;
  public static Boolean clU;
  public static Boolean clV;
  public static Boolean clW;
  public static final String clX = "368hqpm33";
  public static final Boolean clY;
  public static String clZ;
  public static long cla = 0L;
  public static String clb;
  public static String clc;
  public static String cld;
  public static String cle;
  public static String clf;
  public static String clg;
  public static String clh;
  public static String cli;
  public static final String clj = "skin_type";
  public static final String clk = "currentId";
  public static String cll;
  public static String clm;
  public static String cln;
  public static String clo;
  public static String clp;
  public static String clq;
  public static String clr;
  public static String cls;
  public static String clt;
  public static String clu;
  public static String clv;
  public static int clw = 0;
  public static String clx;
  public static int cly = 0;
  public static int clz = 0;
  public static Boolean cmA;
  public static String cmB;
  public static String cmC;
  public static String cmD;
  public static String cmE;
  public static Boolean cmF;
  public static int[] cmG;
  public static String[] cmH;
  public static String cmI;
  public static String cmJ;
  public static final String cmK = "pkey_fast_popup";
  public static final Boolean cmL;
  public static final String cmM;
  public static final String cmN = "pkey_show_moveto_pbox_toast";
  public static final Boolean cmO;
  public static final String cmP = "pkey_schedule_auto_delete";
  public static final Boolean cmQ;
  public static final String cmR = "pkey_hcmms_auto_download";
  public static final Boolean cmS;
  public static final String cmT = "pkey_hcmms_auto_convert";
  public static final Boolean cmU;
  private static String cmV;
  private static Boolean cmW;
  public static final String cmX = "pkey_message_counter_mode";
  public static final String cmY = "normal";
  public static final String cmZ = "pkey_text_counter_mode";
  public static final String cma = "content://settings/system/notification_sound";
  public static final String cmb = "1";
  public static final String cmc = "default";
  public static final Boolean cme;
  public static final String cmf = "Blue";
  public static final String cmg = "Blue";
  public static final String cmh = "0";
  public static final String cmi = "5000";
  public static final String cmj;
  public static final String cmk;
  public static int cml = 0;
  public static String cmm;
  public static String cmn;
  public static Boolean cmo;
  public static Boolean cmp;
  public static final String cmq = "pref_conversation_contactfont";
  public static final String cmr = "pref_conversation_contact_font_color";
  public static final String cms;
  public static final int cmt = -1;
  public static String cmu;
  public static String cmv;
  public static String cmw;
  public static String cmx;
  public static String cmy;
  public static String cmz;
  public static final String cnA;
  public static final String cnB;
  public static final String cnC;
  public static final String cnD;
  public static final int cnE = -1;
  public static final int cnF = -16777216;
  public static final int cnG = -14137089;
  public static final String cnH = "pref_order_type_new";
  public static final String cnI = "dt";
  public static String cnJ;
  public static Boolean cnK;
  public static String cnL;
  public static String cnM;
  public static String cnN;
  public static final String cnO = "hc_popup.png";
  public static final String cnP;
  public static final String cnQ = "hc_popup_land.png";
  public static final String cnR;
  public static final String cnS = ".png";
  public static final String cnT = "hc_popup";
  public static final String cnU;
  public static final String cnV = "hc_popup_land";
  public static final String cnW;
  public static final String cnX = "pref_key_popup_use_skin_bg";
  public static final String cnY = "pref_key_popup_background_color";
  public static final String cnZ = "pref_key_popup_usepic";
  public static final String cna = "yes";
  public static String cnb;
  public static int cnc = 0;
  public static int cnd = 0;
  public static final String cne = "pkey_schedule_run_notification";
  public static final Boolean cnf;
  public static String cng;
  public static String cnh;
  public static String cni;
  public static String cnj;
  public static String cnk;
  public static String cnl;
  public static Boolean cnm;
  public static final String cnn;
  public static final String cno = "pref_popup_contact_font";
  public static final String cnp = "pref_popup_indicator_font";
  public static final String cnq = "pref_popup_datetime_font";
  public static final String cnr = "pref_popup_content_font";
  public static final String cns = "pref_popup_reply_font";
  public static final String cnt = "pref_popup_contact_color";
  public static final String cnu = "pref_popup_indicator_color";
  public static final String cnv = "pref_popup_datetime_color";
  public static final String cnw = "pref_popup_content_color";
  public static final String cnx = "pref_popup_reply_color";
  public static final String cny = "pref_popup_textlink_color";
  public static final String cnz;
  public static final String coA = "pref_key_convlist_use_skin_bg";
  public static final String coB = "pref_key_conv_use_skin_bg";
  public static final String coC = "task_order_type";
  public static final String coD = "rtime";
  public static final String coE = "pref_use_picture_editor";
  public static final Boolean coF;
  public static final String coG = "show_file_panel";
  public static final boolean coH = true;
  public static final String coI = "run_save_old_skin_config_v60";
  public static final boolean coJ = false;
  public static final String coK = "use_new_skin_config_v60";
  public static final String coL = "com.handcent.ecard";
  public static final String coM = "com.handcent.ecard.launch.HcCardEntryActivity";
  public static String coN;
  public static String coO;
  public static final String coP = "pkey_hidden_keyboard_after_sending";
  public static final String coQ = "pkey_show_sending_animation";
  public static final boolean coR = false;
  public static final boolean coS = true;
  public static String coT;
  public static boolean coU = false;
  public static String coV;
  public static String coW;
  public static int[] coX;
  public static final String coY = "animation_between_windows";
  public static final Boolean coZ;
  public static final Boolean coa;
  public static final Boolean cob;
  public static String coc;
  public static int cod = 0;
  public static int coe = 0;
  public static int cof = 0;
  public static int cog = 0;
  public static int coh = 0;
  public static int coi = 0;
  public static int coj = 0;
  public static int cok = 0;
  public static Integer[] col;
  public static final String com = "pref_conversation_splitline_enabled";
  public static final boolean coo = true;
  public static final String cop = "pref_conversation_numbersfont";
  public static final String coq = "pref_conversation_numbers_font_color";
  public static final String cor;
  public static final int cos = -2496279;
  public static final String cot = "pref_hc_groups";
  public static final String cou = "local_last_notify_events_updatetime";
  public static final Long cov;
  public static String cow;
  public static byte[] cox;
  public static final String coy = "pref_swith_effects";
  public static final String coz = "3";
  public static Boolean cpA = Boolean.valueOf(false);
  public static Boolean cpB = Boolean.valueOf(false);
  public static Boolean cpC = Boolean.valueOf(false);
  public static final String cpD = "colorful_avatar";
  public static final Boolean cpE = Boolean.valueOf(true);
  public static String cpF = "unread_indicator_color";
  public static byte[] cpG = hcautz.getInstance().a1("9FA4BF81E4E452786ED6113283403CEC").getBytes();
  public static final String cpH = "convlist_custom_style_changed";
  public static final Boolean cpI = Boolean.valueOf(false);
  public static final String cpJ = "task24hour";
  private static boolean cpK = false;
  public static final String cpL = "avatar_shape";
  public static final String cpM = "circle";
  public static Boolean cpN = Boolean.valueOf(false);
  public static String cpO = "draft_font_color";
  public static final String cpP = "app_run_mode";
  public static final String cpQ = "normal";
  public static final String cpR = "background";
  private static final String cpS = "first_upload_diff";
  public static final String cpT = "pref_key_old_copy";
  private static final String cpU = "pref_key_old_uninstall";
  private static final String cpV = "pref_sp_key_data_tranf";
  private static final String cpW = "pref_key_score";
  public static long cpX = 0L;
  public static Map<Long, Integer> cpY = new HashMap();
  public static ConcurrentLinkedQueue<Integer> cpZ = new ConcurrentLinkedQueue();
  public static final String cpa = "pref_colorful_bubble_type";
  public static final String cpb = "withskin";
  public static final String cpc = "pref_contact_picture_in_title";
  public static final Boolean cpd;
  private static final String cpe = "com.handcent.sms.skin.";
  public static final String cpf = "author_name";
  public static final String cpg = "skin_display_name";
  public static final String cph = "preview";
  public static final String cpi = "pref_notification_id";
  public static final String cpj = "pref_event_id";
  public static final String cpk = "guide";
  public static final String cpl = "remote_sms";
  public static final String cpm = "remote_notice";
  public static final String cpn = "remote_tran";
  public static final String cpo = "open";
  public static final String cpp = "sync_recent";
  public static final String cpq = "device_name";
  public static final String cpr = "device_upload";
  public static final String cps = "main_guide_10650";
  private static final String cpt = "main_guide";
  public static final String cpu = "last_control";
  public static final String cpv = "last_control_type";
  public static final String cpw = "last_link_long";
  public static final String cpx = "link_on";
  public static Boolean cpy;
  public static Boolean cpz;
  public static ConcurrentLinkedQueue<Long> cqa = new ConcurrentLinkedQueue();
  public static final String cqb = "pref_key_failed_vibrate_type";
  public static final String cqc = "pref_restore_sync_time";
  public static final String cqd = "flag_v66tov67";
  public static final boolean cqe = false;
  public static final String cqf = "pkey_foreground_service";
  public static final Boolean cqg = Boolean.valueOf(true);
  public static String[] cqh = { "edit_large_key_editshow", "edit_large_key_always", "edit_large_key_none" };
  public static final String mKey;
  public static String mPackageName;
  
  static
  {
    bXA = hcautz.getInstance().a1("0D3C88AF0FB68239");
    bXB = hcautz.getInstance().a1("2BF7A85728CE1A98");
    bXC = hcautz.getInstance().a1("A411D904BBFF1A75");
    bXD = hcautz.getInstance().a1("3B88943FF23CFA57");
    bXE = hcautz.getInstance().a1("4F0CBA11F2DED1E00CCEDF97EF8523B4");
    bXF = hcautz.getInstance().a1("BA68FEDECAFD0D5F");
    bXG = hcautz.getInstance().a1("82B698B22440E1AE");
    bXH = hcautz.getInstance().a1("476A98BD2CCFA87E");
    bXI = hcautz.getInstance().a1("31AFB12FEE42A538");
    bXJ = hcautz.getInstance().a1("AA4FAB05CDCCFEB4");
    bXK = hcautz.getInstance().a1("FDF601637D2EA8D33A3F954B9C41F9E97758566A7AFD4318");
    bXL = hcautz.getInstance().a1("0B30DCD3A9B540D5460A74D0110D8C34");
    bXM = hcautz.getInstance().a1("FA9974CC66E3D380");
    bXN = hcautz.getInstance().a1("69875CEC520F14A01B81FB3BC456F7BA");
    bXO = hcautz.getInstance().a1("ACAEDA58A04AE72A");
    bXP = hcautz.getInstance().a1("FF2EB225DE887204BA33DC5807F803B8");
    bXQ = hcautz.getInstance().a1("062FDF4293813490");
    bXR = hcautz.getInstance().a1("6278112D0EAC57DD");
    bXS = hcautz.getInstance().a1("B4D5F0681FF3A80D");
    bXT = hcautz.getInstance().a1("54927CB861CEE5A0");
    bXU = hcautz.getInstance().a1("A2366507211612278BE6E36C845EE8DA");
    bXV = hcautz.getInstance().a1("1325F60A74F588B569ED6EBAE430D1CF");
    bXW = hcautz.getInstance().a1("B50E1B63B6B81352");
    bXX = hcautz.getInstance().a1("FDF6C5637D2EA8D33A3F954B9C41F9E94445CEBEC90C010CD8EB1AF2E6FDA9ED");
    bXY = hcautz.getInstance().a1("8867D0693CC59996");
    bXZ = hcautz.getInstance().a1("D2A68C3C6911773A");
    bYa = hcautz.getInstance().a1("7A257EF560B03E93");
    bYb = hcautz.getInstance().a1("8DD61EA8ED5887548A11299FC1FC7B7B");
    bYc = hcautz.getInstance().a1("B849DA2774E17C29");
    bYd = hcautz.getInstance().a1("1CA739AD26CDD7FA");
    bYe = hcautz.getInstance().a1("07A8FA3A78BE56C6");
    bYf = hcautz.getInstance().a1("DAB8673D10313292");
    bYg = hcautz.getInstance().a1("AD129457E8F279CA");
    bYh = hcautz.getInstance().a1("747735389132A345");
    bYi = hcautz.getInstance().a1("7E430DFF9C52BB2042393B66430DA1F7");
    bYj = hcautz.getInstance().a1("CBDD2898F84458DA87106A73560F16A50ED3C75613E7BE3F");
    bYk = hcautz.getInstance().a1("BD9DA951DA2B82CF");
    bYl = hcautz.getInstance().a1("28DE71EA698CF1592F757BF19B6CB2DB");
    bYm = hcautz.getInstance().a1("9C91457D95C373FC");
    bYn = hcautz.getInstance().a1("D1B901739D2EBDE9");
    bYo = hcautz.getInstance().a1("2ECC1BC9A6D7E7EE");
    bYp = hcautz.getInstance().a1("C900AC36EF7193F7");
    bYq = hcautz.getInstance().a1("E451D1C383D75FAE");
    bYr = hcautz.getInstance().a1("C731B98BFA21E61F");
    bYs = hcautz.getInstance().a1("D6504007D17C0BF7");
    bYt = hcautz.getInstance().a1("336E44B973B24F69");
    bYu = hcautz.getInstance().a1("00FE041D84522BC45E95B5F9702C3FD9");
    bYv = hcautz.getInstance().a1("08467DE8D5695AF7");
    bYw = hcautz.getInstance().a1("64BF33EAD134D6C7");
    bYx = hcautz.getInstance().a1("DC4136FDF1E6235A");
    bYy = hcautz.getInstance().a1("933A9141FE0ECD85");
    bYz = hcautz.getInstance().a1("DE47C2BC583D9FB3");
    bYA = hcautz.getInstance().a1("6646240917535375");
    bYB = hcautz.getInstance().a1("194E51068B2E830594B2774B85EEB81A");
    bYC = hcautz.getInstance().a1("34BE6A38FD2F1B86");
    bYD = hcautz.getInstance().a1("332B6B2F10CD9ED6");
    bYE = hcautz.getInstance().a1("FDF62E637DFDA8D3");
    bYF = hcautz.getInstance().a1("4880ACE31B6D3AB5");
    bYG = hcautz.getInstance().a1("AB883D976C9A1F071CDA4A2B24FAFBDE");
    bYH = hcautz.getInstance().a1("275F0961AACEB511");
    bYI = hcautz.getInstance().a1("8CE1CD0D022907BF15AB4C59694F2382");
    bYJ = hcautz.getInstance().a1("2BF38E57DDE3163C");
    bYK = hcautz.getInstance().a1("894A1B5779B789BD");
    bYL = hcautz.getInstance().a1("1D21FCECF48F8F46");
    bYM = hcautz.getInstance().a1("1F7F7AD1453EEB25EB617CF2E33065E3");
    bYN = hcautz.getInstance().a1("40A642D588CA6B643E2A10732A1406C0");
    bYO = hcautz.getInstance().a1("793DD5F143DA3C7556541932F32AE64D");
    bYP = hcautz.getInstance().a1("F7159DCA5E26C6ED");
    bYQ = hcautz.getInstance().a1("8E02B031C95E645658638EE169DCC65F");
    bYR = hcautz.getInstance().a1("5065A43910344AA0");
    bYS = hcautz.getInstance().a1("2127D1CE50F99D0A");
    bYT = hcautz.getInstance().a1("D55EF0CEE02192015420F8B9C5DD76ACCE92C0BD24EA8A24");
    bYU = hcautz.getInstance().a1("1D961B8A1F463748");
    bYV = hcautz.getInstance().a1("287C0EA4250AA2CD");
    bYW = hcautz.getInstance().a1("61926D318C87ACED");
    bYX = hcautz.getInstance().a1("BE7478BEF4DAAAAA");
    bYY = hcautz.getInstance().a1("2E4E283481D92F2D57B9282DF7EE3D03");
    bYZ = hcautz.getInstance().a1("32B1F94F789D23D11648B4E0E3679D03");
    bZa = hcautz.getInstance().a1("6443F25A48F2D62D808A9E206729FB6C");
    bZb = hcautz.getInstance().a1("53D369EFDCC20F02");
    bZc = hcautz.getInstance().a1("157D3DAACF0C97F31CDA4A2B2429FBDE");
    bZd = hcautz.getInstance().a1("7B91CCCE1F1256C3E9ADAA4CC3D9A987");
    bZe = hcautz.getInstance().a1("7F010CAD63280F4E");
    bZf = hcautz.getInstance().a1("5CAD08AAE0CB942B");
    bZg = hcautz.getInstance().a1("746250FB759E8111663C30AD4F4DF041");
    bZl = Integer.valueOf(hcautz.getInstance().a1("3B1DC76D17552720")).intValue();
    bZm = Integer.valueOf(hcautz.getInstance().a1("E2FB8501812203E9")).intValue();
    bZn = Integer.valueOf(hcautz.getInstance().a1("E617F290B5B6A809")).intValue();
    bZo = Integer.valueOf(hcautz.getInstance().a1("894A1B5779B789BD")).intValue();
    bZq = hcautz.getInstance().a1("C1128787ABE99E9B3324843F114EAC216A3425C65D8B63FF3832541372B9C109C90D49182DB622E366E78FA865FCA1B8");
    bZt = 0;
    mKey = hcautz.getInstance().a1("0E8E4D518D4E2835B6213B8F653282E7");
    SUFFIX = hcautz.getInstance().a1("745758DA35F2E83A");
    bZy = Boolean.valueOf(true);
    ccJ = "both";
    ccK = "smart";
    ccL = Boolean.valueOf(true);
    ccM = Boolean.valueOf(true);
    ccN = Boolean.valueOf(false);
    ccO = Boolean.valueOf(false);
    ccP = Boolean.valueOf(false);
    ccQ = Boolean.valueOf(false);
    cee = Color.rgb(209, 255, 255);
    cef = Color.rgb(111, 213, 68);
    ceg = Color.rgb(228, 228, 228);
    ceh = Color.rgb(0, 255, 0);
    cei = Color.rgb(206, 247, 239);
    cej = Color.rgb(255, 255, 255);
    cek = Color.rgb(0, 0, 0);
    cel = Color.rgb(0, 0, 0);
    cem = Color.rgb(0, 0, 0);
    cen = Color.rgb(0, 0, 0);
    ceo = Boolean.valueOf(true);
    cep = Boolean.valueOf(true);
    ceq = Color.rgb(155, 158, 239);
    cer = Color.rgb(35, 206, 224);
    ces = Color.rgb(255, 204, 153);
    cet = Color.rgb(255, 204, 102);
    ceu = Color.rgb(0, 0, 0);
    cev = Color.rgb(0, 0, 0);
    cex = Boolean.valueOf(false);
    cey = Boolean.valueOf(false);
    cez = Boolean.valueOf(true);
    ceB = Boolean.valueOf(false);
    ceM = Boolean.valueOf(true);
    ceU = Boolean.valueOf(false);
    ceV = null;
    cfc = Boolean.valueOf(true);
    cfd = Boolean.valueOf(true);
    cfe = Boolean.valueOf(true);
    cfj = Boolean.valueOf(false);
    cfk = Boolean.valueOf(true);
    cfm = m.gp("*system*,NORMAL,Normal,18");
    cfn = m.gp("*system*,NORMAL,Normal,16");
    cfo = m.gp("*system*,NORMAL,Bold,14");
    cfp = m.gp("*system*,NORMAL,Normal,14");
    cfz = hcautz.getInstance().a1("189625F0E778E54B");
    cfC = null;
    aPY = -1;
    cfS = -1;
    cgs = Boolean.valueOf(true);
    cgt = Boolean.valueOf(true);
    cgu = Boolean.valueOf(true);
    cgw = null;
    cgx = Boolean.valueOf(false);
    cgy = Boolean.valueOf(true);
    cgA = Boolean.valueOf(true);
    cgF = Boolean.valueOf(false);
    cgI = Boolean.valueOf(false);
    cgK = Boolean.valueOf(true);
    cgN = hcautz.getInstance().a1("A98FDD2CCEAB6CCA");
    cgQ = hcautz.getInstance().a1("9A15C7F17A7F2DCD3B1D556D17A62720");
    cgS = Boolean.valueOf(false);
    chn = Boolean.valueOf(true);
    cho = Boolean.valueOf(true);
    chq = Boolean.valueOf(false);
    chr = Boolean.valueOf(false);
    chs = Boolean.valueOf(true);
    cht = Boolean.valueOf(true);
    chu = Boolean.valueOf(false);
    chv = Boolean.valueOf(true);
    chw = Boolean.valueOf(true);
    chx = Boolean.valueOf(false);
    chz = Boolean.valueOf(false);
    chA = Boolean.valueOf(false);
    chB = Boolean.valueOf(false);
    chC = Boolean.valueOf(true);
    chD = Boolean.valueOf(false);
    chE = Boolean.valueOf(true);
    chF = Boolean.valueOf(true);
    chH = a.db(MmsApp.getContext()) + "/" + "hc_convlist_background.png";
    chJ = a.db(MmsApp.getContext()) + "/" + "hc_convlist_background_land.png";
    chL = a.db(MmsApp.getContext()) + "/" + "hc_background.png";
    chN = a.db(MmsApp.getContext()) + "/" + "hc_background_land.png";
    chQ = a.db(MmsApp.getContext()) + "/" + "hc_background";
    chS = a.db(MmsApp.getContext()) + "/" + "hc_background_land";
    chY = Boolean.valueOf(false);
    cib = null;
    cic = null;
    cie = null;
    cif = null;
    cig = "pkey_sig_enable";
    cih = "pkey_sig_text";
    cii = Boolean.valueOf(false);
    cij = "";
    cik = "enable_conv_custom";
    cil = "contact_font_color";
    cim = "text_font_color";
    cin = "date_font_color";
    cio = "divider_color";
    cip = "conv_background_color";
    ciq = "conv_use_pic";
    cir = -16777216;
    cis = -5662081;
    cit = -14266186;
    ciu = -1;
    civ = -1;
    ciw = Boolean.valueOf(false);
    cix = Boolean.valueOf(false);
    ciy = -5662081;
    ciz = -1;
    ciA = -16777216;
    ciB = hcautz.getInstance().a1("3483518B078AD11C5AB8C2B768D8999D489892C9E26573B322D45477056A56205AB8C2B768D8999D94B2774B8567B81A");
    ciC = hcautz.getInstance().a1("4DB3B90C2D628F565306661529C7FC909A0D4075EA559B6F78252B0F118C4E8E3B1438489A37668A292A16A4D9133178");
    ciD = "tts_type";
    ciE = true;
    ciF = "enabled_quick_compose";
    ciG = false;
    ciH = "pkey_use_camera_button";
    ciI = false;
    ciJ = new int[] { 2130839210, 2130839214, 2130839227, 2130839229, 2130839241, 2130839243, 2130839209, 2130839205, 2130839211, 2130839215, 2130839216, 2130839228, 2130839230, 2130839242, 2130839244, 2130839249, 2130839250, 2130839642, 2130839645, 2130839647, 2130839646, 2130839648, 2130839643, 2130839644, 2130838662, 2130839824, 2130839825, 2130839826, 2130839827, 2130839828, 2130839830, 2130839829, 2130839831, 2130839832, 2130839833, 2130839834, 2130839835, 2130839218, 2130839219, 2130839220, 2130839221, 2130839222, 2130839223, 2130839224, 2130838663, 2130838664, 2130838665, 2130838666, 2130838667, 2130839212, 2130839248, 2130839225, 2130839226, 2130839217, 2130839213, 2130839207, 2130839208, 2130839245 };
    ciK = new String[] { "default", "green", "orange", "pink", "purple", "red", "black", "aque_envelope_icon", "blue_envelope_icon", "green_envelope_icon", "orange_envelope_icon", "pink_envelope_icon", "purple_envelope_icon", "red_envelope_icon", "white_envelope_icon", "yellow_envelope_icon", "ycz20_black", "ycz20_orange", "ycz20_purple", "ycz20_pink", "ycz20_red", "ycz20_blue", "ycz20_green" };
    ciL = "notif_icon_ind";
    ciM = "0";
    ciN = "recv_message_self_check";
    ciO = true;
    ciP = String.valueOf(m.getVersionCode());
    ciQ = "release_notes";
    ciR = "hc_password";
    ciS = "";
    ciV = a.Rp();
    ciW = hcautz.getInstance().a1("8EBA85359878CCAAE68401B85FFA4F67");
    cja = ciW;
    cjb = hcautz.getInstance().a1("A38A6D13F5FC96C126F7993FB46DEBA4601228AB6D31EABFF43BAEF236CA32936BB476982A46B06B");
    cjc = Long.valueOf(0L);
    cjd = "send_editor_initial_lines";
    cje = Boolean.valueOf(false);
    cjf = "ad_type";
    cjg = 1;
    cjh = "ad_bg_color";
    cji = "000000";
    cjj = "security_lock_type";
    cjk = "lock_visible_pattern";
    cjl = "lock_tactile_feedback";
    cjm = "lock_level";
    cjn = "num_pin";
    cjo = "2";
    cjp = "1";
    cjq = "0";
    cjr = "1";
    cjs = "2";
    cjt = "3";
    cju = Boolean.valueOf(true);
    cjv = Boolean.valueOf(false);
    cjw = Boolean.valueOf(true);
    cjx = "pref_filter_unknown";
    cjy = "pref_filter_keywords";
    cjz = "pref_filter_options";
    cjA = "pref_filter_prefix";
    cjB = "pref_fliter_tag_as_read";
    cjC = "disable";
    cjD = "";
    cjE = Boolean.valueOf(false);
    cjF = Boolean.valueOf(true);
    cjG = "";
    ckh = null;
    cki = null;
    ckj = "pkey_disp_pic";
    ckk = "large";
    ckl = "pref_key_enable_smiley";
    ckm = Boolean.valueOf(true);
    ckn = "pref_custom_led_color";
    cko = -16776961;
    ckq = "disp_pic_for_conv";
    ckr = "large";
    ckt = a.db(MmsApp.getContext()) + "/" + "hc_account_picture.png";
    ckv = Boolean.valueOf(true);
    ckz = Boolean.valueOf(true);
    ckX = "06745aeee5314163ae61e0c3f2cae0d7";
    ckY = "conversationlist_background_changed";
    ckZ = "conversation_background_changed";
    cla = 3000L;
    clb = m.Tj() + hcautz.getInstance().a1("71FF1C9CEF97FC60AA3D7822DCD26B58B6CF3979255328D3B58D46D3357A893A5A452066D7638F33");
    clc = m.Tj() + hcautz.getInstance().a1("71FF2F9CEF97FC60AA3D7822DCD26B58B6CF3979255328D38C298E5F2C6CDF27EC7F560B3C4DC88A9D7A912423638B94");
    cld = m.Tj() + hcautz.getInstance().a1("71FF339CEF97FC60AA3D7822DCD26B58332FD092B0159ABD747AA99AA81AB854935C822AFE63C8A8");
    cle = m.Tj() + hcautz.getInstance().a1("71FFED9CEF97FC60AA3D7822DCD26B58332FD092B0159ABDD60DB4434063EDF6");
    clf = hcautz.getInstance().a1("B22DE1E1CEAD6BA4");
    clg = hcautz.getInstance().a1("AA4D50CCC554254B");
    clh = hcautz.getInstance().a1("7D0C1632C51815F0");
    cli = hcautz.getInstance().a1("646FCAFEEAF0B0AC");
    cll = null;
    mPackageName = null;
    clm = "pref_skin_package_name";
    cln = "custom";
    clo = hcautz.getInstance().a1("8E0FC5ED3BC19FD25FF630393992D08E221F8DB767FE5D21D8EB1AF2E6E7A9ED");
    clp = "pref_remember_password";
    clq = "pref_screen_display_mode";
    clr = "pref_screen_night_brightness";
    cls = "pref_screen_night_starthour";
    clt = "pref_screen_night_endhour";
    clu = "pref_screen_night_startminute";
    clv = "pref_screen_night_endminute";
    clw = 50;
    clx = "normal";
    cly = 18;
    clz = 0;
    clA = 8;
    clB = 0;
    clC = "pref_hctalk_mode";
    clD = "pref_hctalk_starttime";
    clE = "pref_hctalk_endtime";
    clF = "open";
    clG = "close";
    clH = "smart";
    clI = "00:30";
    clJ = "08:30";
    clK = "pkey_privacy_hidden_content";
    clL = "pkey_privacy_notification_title";
    clM = "pkey_privacy_notification_message";
    clN = "pkey_privacy_enable_popup";
    clO = "pkey_privacy_function_enable";
    clP = "pkey_privacy_auto_backup";
    clQ = "pkey_privacy_auto_move";
    clR = "pkey_privacy_charm_mode";
    clS = Boolean.valueOf(true);
    clT = Boolean.valueOf(false);
    clU = Boolean.valueOf(true);
    clV = Boolean.valueOf(true);
    clW = Boolean.valueOf(true);
    clY = Boolean.valueOf(true);
    clZ = "24";
    cme = Boolean.valueOf(true);
    cmj = hcautz.getInstance().a1("A98FDD2CCEAB6CCA");
    cmk = hcautz.getInstance().a1("9A15C7F17A7F2DCD3B1D556D17A62720");
    cml = -16776961;
    cmm = "pkey_privacy_mms_auto_retrieval";
    cmn = "pkey_privacy_mms_retrieval_during_roaming";
    cmo = Boolean.valueOf(true);
    cmp = Boolean.valueOf(false);
    cms = m.gp("*system*,NORMAL,NORMAL,16");
    cmu = "pref_use_chinese_speech";
    cmv = "pkey_chinese_sampling_rate";
    cmw = "pkey_chinese_tts_role";
    cmx = "pkey_chinese_speed";
    cmy = "pkey_chinese_volume";
    cmz = "pref_use_background_music";
    cmA = Boolean.valueOf(false);
    cmB = "16000";
    cmC = "xiaoyan";
    cmD = "medium";
    cmE = "medium";
    cmF = Boolean.valueOf(false);
    cmG = new int[] { 10999, 2130839678, 2130839679, 2130839680, 2130839681, 2130839682, 2130839683, 2130839684 };
    cmH = new String[] { "Default", "Blue", "Black", "Green", "Orange", "Pink", "Purple", "Red" };
    cmI = "progress_icon_ind";
    cmJ = "0";
    cmL = Boolean.valueOf(false);
    cmM = hcautz.getInstance().a1("677FB967FB4A5F12392A546A51AEC488");
    cmO = Boolean.valueOf(true);
    cmQ = Boolean.valueOf(false);
    cmS = Boolean.valueOf(false);
    cmU = Boolean.valueOf(false);
    cmV = "pkey_dont_show_dialog";
    cmW = Boolean.valueOf(false);
    cnb = "faeed3fd2aad4f63a545f427cf244a8f";
    cnc = 150;
    cnd = 143;
    cnf = Boolean.valueOf(true);
    cng = "pref_hc_talk_auto_retrieve";
    cnh = "pref_hc_talk_send_thumb";
    cni = "pref_hc_talk_compress_picture";
    cnj = "pref_hc_talk_international_number";
    cnk = "pref_hc_talk_use_contacts";
    cnl = "pref_hc_talk_show_block_buddy";
    cnm = Boolean.valueOf(false);
    cnn = hcautz.getInstance().a1("83A026AD1F1A6F8FF76014C354A05751");
    cnz = m.gp("*system*,NORMAL,Bold,18");
    cnA = m.gp("*system*,NORMAL,Normal,14");
    cnB = m.gp("*system*,NORMAL,Bold,12");
    cnC = m.gp("*system*,NORMAL,Normal,16");
    cnD = m.gp("*system*,NORMAL,Normal,18");
    cnJ = "pref_key_enable_numbers";
    cnK = Boolean.valueOf(true);
    cnL = "both";
    cnM = "close";
    cnN = "cancel";
    cnP = a.db(MmsApp.getContext()) + "/" + "hc_popup.png";
    cnR = a.db(MmsApp.getContext()) + "/" + "hc_popup_land.png";
    cnU = a.db(MmsApp.getContext()) + "/" + "hc_popup";
    cnW = a.db(MmsApp.getContext()) + "/" + "hc_popup_land";
    coa = Boolean.valueOf(false);
    cob = Boolean.valueOf(true);
    coc = "popup_background_changed";
    bBF = (int)(6.0F * m.SA());
    bBG = (int)(6.0F * m.SA());
    cod = 6;
    cog = 127;
    coh = 10;
    coi = 117;
    col = new Integer[] { Integer.valueOf(2130839715), Integer.valueOf(2130839720), Integer.valueOf(2130839716), Integer.valueOf(2130839717), Integer.valueOf(2130839719) };
    cor = m.gp("*system*,NORMAL,Normal,14");
    cov = Long.valueOf(0L);
    cow = "pref_dataupdate_when_appupdate";
    cox = hcautz.getInstance().a1("AD95EE28A34A42C1E9F344A85FB54564").getBytes();
    coF = Boolean.valueOf(true);
    coN = "unread_background_color";
    coO = hcautz.getInstance().a1("B1D6FAAA464D8F36EA32CD1D38298B08CE0A5BBAF073F53CDD8938A3C45C37899EC8277F25E8314D");
    coT = "pkey_use_qc_in_notification";
    coU = true;
    coV = "pkey_qc_in_notification_icon_ind";
    coW = "0";
    coX = new int[] { 2130838754, 2130838755, 2130838756, 2130838753, 2130838762, 2130838763, 2130838764, 2130838757, 2130838758, 2130838759, 2130838760, 2130838761 };
    coZ = Boolean.valueOf(true);
    cpd = Boolean.valueOf(false);
    cpy = Boolean.valueOf(false);
    cpz = Boolean.valueOf(false);
  }
  
  public i() {}
  
  public static Boolean A(Context paramContext, boolean paramBoolean)
  {
    Object localObject = m.ip(paramContext);
    String str = ((SharedPreferences)localObject).getString(ciQ, "V233");
    if (!str.equalsIgnoreCase(ciP))
    {
      if (paramBoolean)
      {
        localObject = ((SharedPreferences)localObject).edit();
        ((SharedPreferences.Editor)localObject).putString(ciQ, ciP);
        ((SharedPreferences.Editor)localObject).commit();
        if (!"V233".equalsIgnoreCase(str)) {
          U(paramContext, true);
        }
      }
      return Boolean.valueOf(true);
    }
    return Boolean.valueOf(false);
  }
  
  public static void A(Context paramContext, int paramInt)
  {
    paramContext = m.ip(paramContext).edit();
    paramContext.putInt(clt, paramInt);
    paramContext.commit();
  }
  
  public static void B(Context paramContext, int paramInt)
  {
    paramContext = m.ip(paramContext).edit();
    paramContext.putInt(clu, paramInt);
    paramContext.commit();
  }
  
  public static void B(Context paramContext, boolean paramBoolean)
  {
    paramContext = m.dd(paramContext, "privacy_" + eY(paramContext)).edit();
    paramContext.putBoolean("priv_move_return", paramBoolean);
    paramContext.commit();
  }
  
  public static void C(Context paramContext, int paramInt)
  {
    paramContext = m.ip(paramContext).edit();
    paramContext.putInt(clv, paramInt);
    paramContext.commit();
  }
  
  public static void C(Context paramContext, boolean paramBoolean)
  {
    paramContext = m.dd(paramContext, "privacy_" + eY(paramContext)).edit();
    paramContext.putBoolean("priv_sync_open", paramBoolean);
    paramContext.commit();
  }
  
  public static void D(Context paramContext, int paramInt)
  {
    paramContext = m.ip(paramContext).edit();
    paramContext.putInt("pref_notification_id", 0);
    paramContext.commit();
  }
  
  public static void D(Context paramContext, boolean paramBoolean)
  {
    paramContext = m.dd(paramContext, "privacy_" + eY(paramContext)).edit();
    paramContext.putBoolean("priv_sync_notice", paramBoolean);
    paramContext.commit();
  }
  
  public static void E(Context paramContext, int paramInt)
  {
    paramContext = m.ip(paramContext).edit();
    paramContext.putInt("pref_event_id", 0);
    paramContext.commit();
  }
  
  public static void E(Context paramContext, boolean paramBoolean)
  {
    paramContext = m.dd(paramContext, "privacy_" + eY(paramContext)).edit();
    paramContext.putBoolean("priv_sync_init", paramBoolean);
    paramContext.commit();
  }
  
  public static void F(Context paramContext, int paramInt)
  {
    paramContext = m.dd(paramContext, "remote_sms_" + eY(paramContext)).edit();
    paramContext.putInt("last_control_type", paramInt);
    paramContext.commit();
  }
  
  public static void F(Context paramContext, boolean paramBoolean)
  {
    paramContext = m.dd(paramContext, "privacy_" + eY(paramContext)).edit();
    paramContext.putBoolean("priv_data_upgrade", paramBoolean);
    paramContext.commit();
  }
  
  public static void G(Context paramContext, boolean paramBoolean)
  {
    paramContext = m.dd(paramContext, "privacy_" + eY(paramContext)).edit();
    paramContext.putBoolean("priv_guide_completed", paramBoolean);
    paramContext.commit();
  }
  
  public static void H(Context paramContext, boolean paramBoolean)
  {
    paramContext = m.dd(paramContext, "privacy_" + eY(paramContext)).edit();
    paramContext.putBoolean("priv_use_lock", paramBoolean);
    paramContext.commit();
  }
  
  public static void I(Context paramContext, boolean paramBoolean)
  {
    paramContext = m.dd(paramContext, "privacy_" + eY(paramContext)).edit();
    paramContext.putBoolean("priv_backup_ext_sdcard", paramBoolean);
    paramContext.commit();
  }
  
  public static String J(Context paramContext, boolean paramBoolean)
  {
    if ("".equals(eZ(paramContext))) {
      return "";
    }
    Object localObject2 = hcautz.getInstance().getUserLoginInfo2(eX(paramContext), m.jm(paramContext), m.jn(paramContext));
    Object localObject1 = localObject2;
    if (TextUtils.isEmpty((CharSequence)localObject2))
    {
      dd.d("", "try again to get account name,try to fix get account name null bug...");
      localObject1 = hcautz.getInstance().getUserLoginInfo2(eX(paramContext), m.jm(paramContext), m.jn(paramContext));
    }
    localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append(";");
    ((StringBuilder)localObject2).append((String)localObject1).append(";");
    ((StringBuilder)localObject2).append(m.jn(paramContext)).append(";");
    ((StringBuilder)localObject2).append(m.jm(paramContext)).append(";");
    ((StringBuilder)localObject2).append(m.jI(paramContext)).append(";");
    ((StringBuilder)localObject2).append("" + m.getVersionCode()).append(";");
    if (paramBoolean) {}
    for (paramContext = "1";; paramContext = "0")
    {
      ((StringBuilder)localObject2).append(paramContext).append(";");
      return ((StringBuilder)localObject2).toString();
    }
  }
  
  public static void K(Context paramContext, boolean paramBoolean)
  {
    paramContext = m.ip(paramContext).edit();
    paramContext.putBoolean(clp, paramBoolean);
    paramContext.commit();
  }
  
  public static void L(Context paramContext, boolean paramBoolean)
  {
    paramContext = m.dd(paramContext, "privacy_" + eY(paramContext)).edit();
    paramContext.putBoolean("pref_key_enable_notifications", paramBoolean);
    paramContext.commit();
  }
  
  public static void M(Context paramContext, boolean paramBoolean)
  {
    m.dd(paramContext, "privacy_" + eY(paramContext)).edit().putBoolean(clK, paramBoolean).commit();
  }
  
  public static void N(Context paramContext, boolean paramBoolean)
  {
    m.dd(paramContext, "privacy_" + eY(paramContext)).edit().putBoolean(clN, paramBoolean).commit();
  }
  
  public static void O(Context paramContext, boolean paramBoolean)
  {
    m.dd(paramContext, "privacy_" + eY(paramContext)).edit().putBoolean("notification_screenon", paramBoolean).commit();
  }
  
  public static void P(Context paramContext, boolean paramBoolean)
  {
    paramContext = m.dd(paramContext, "privacy_" + eY(paramContext)).edit();
    paramContext.putBoolean(clP, paramBoolean);
    paramContext.commit();
  }
  
  public static void Q(Context paramContext, boolean paramBoolean)
  {
    paramContext = m.ip(paramContext).edit();
    paramContext.putBoolean(cmV, paramBoolean);
    paramContext.commit();
  }
  
  public static void R(Context paramContext, boolean paramBoolean)
  {
    paramContext = m.ip(paramContext).edit();
    paramContext.putBoolean(cnk, paramBoolean);
    paramContext.commit();
  }
  
  public static final Animation RA()
  {
    if (cic == null)
    {
      cic = new TranslateAnimation(0, 0.0F, 2, -100.0F, 0, 0.0F, 0, 0.0F);
      cic.setDuration(400L);
    }
    return cic;
  }
  
  public static final Animation RB()
  {
    if (ckh == null)
    {
      ckh = new TranslateAnimation(0, 0.0F, 0, 0.0F, 2, 1.0F, 0, 0.0F);
      ckh.setDuration(200L);
      ckh.setInterpolator(new LinearInterpolator());
    }
    return ckh;
  }
  
  public static final Animation RC()
  {
    if (cki == null)
    {
      cki = new TranslateAnimation(0, 0.0F, 0, 0.0F, 0, 0.0F, 2, 1.0F);
      cki.setDuration(200L);
      ckh.setInterpolator(new LinearInterpolator());
    }
    return cki;
  }
  
  public static boolean RD()
  {
    return true;
  }
  
  public static String RE()
  {
    String str = "0_android_" + m.jI(MmsApp.getContext());
    dd.i("", "xmpp resource:" + str);
    return str;
  }
  
  public static boolean RF()
  {
    return true;
  }
  
  public static boolean RG()
  {
    return "android40".equalsIgnoreCase(m.gR("conversation_list_skin_style"));
  }
  
  public static boolean RH()
  {
    return "android40".equalsIgnoreCase(m.gR("conversation_skin_style"));
  }
  
  public static boolean RI()
  {
    if (cpK)
    {
      cpK = false;
      return true;
    }
    return cpK;
  }
  
  public static int RJ()
  {
    return bZt;
  }
  
  public static void RK()
  {
    Object localObject;
    try
    {
      dd.d("", "dump top threads info:");
      if (cpY != null)
      {
        Iterator localIterator1 = cpY.keySet().iterator();
        while (localIterator1.hasNext())
        {
          localObject = (Long)localIterator1.next();
          dd.d("", "key:" + localObject + " value:" + cpY.get(localObject));
        }
      }
      if (cpZ == null) {
        break label162;
      }
    }
    catch (Exception localException)
    {
      m.g(localException);
      return;
    }
    Iterator localIterator2 = cpZ.iterator();
    while (localIterator2.hasNext())
    {
      localObject = (Integer)localIterator2.next();
      dd.d("", "pos:" + localObject);
    }
    label162:
    if (cqa != null)
    {
      localIterator2 = cqa.iterator();
      while (localIterator2.hasNext())
      {
        localObject = (Long)localIterator2.next();
        dd.d("", "tid:" + localObject);
      }
    }
    dd.d("", "dump top threads info end");
  }
  
  /* Error */
  public static void RL()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: getstatic 3592	com/handcent/o/i:cpY	Ljava/util/Map;
    //   5: invokeinterface 3818 1 0
    //   10: getstatic 3597	com/handcent/o/i:cpZ	Ljava/util/concurrent/ConcurrentLinkedQueue;
    //   13: invokevirtual 3819	java/util/concurrent/ConcurrentLinkedQueue:clear	()V
    //   16: getstatic 3599	com/handcent/o/i:cqa	Ljava/util/concurrent/ConcurrentLinkedQueue;
    //   19: invokevirtual 3819	java/util/concurrent/ConcurrentLinkedQueue:clear	()V
    //   22: invokestatic 2693	com/handcent/nextsms/MmsApp:getContext	()Landroid/content/Context;
    //   25: astore_3
    //   26: invokestatic 2693	com/handcent/nextsms/MmsApp:getContext	()Landroid/content/Context;
    //   29: invokevirtual 3825	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   32: astore 4
    //   34: getstatic 3830	com/handcent/n/ae:bTb	Landroid/net/Uri;
    //   37: astore 5
    //   39: getstatic 3835	com/handcent/n/ac:bQZ	Ljava/lang/String;
    //   42: astore 6
    //   44: new 2083	java/lang/StringBuilder
    //   47: dup
    //   48: invokespecial 2086	java/lang/StringBuilder:<init>	()V
    //   51: getstatic 3838	com/handcent/n/ac:TIMESTAMP	Ljava/lang/String;
    //   54: invokevirtual 2101	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   57: ldc_w 3840
    //   60: invokevirtual 2101	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   63: invokevirtual 2105	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   66: astore 7
    //   68: aload_3
    //   69: aload 4
    //   71: aload 5
    //   73: iconst_1
    //   74: anewarray 2871	java/lang/String
    //   77: dup
    //   78: iconst_0
    //   79: aload 6
    //   81: aastore
    //   82: ldc_w 770
    //   85: aconst_null
    //   86: aload 7
    //   88: invokestatic 3846	com/google/android/mms/util/SqliteWrapper:query	(Landroid/content/Context;Landroid/content/ContentResolver;Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   91: astore_3
    //   92: aload_3
    //   93: astore_2
    //   94: aload_2
    //   95: invokeinterface 3851 1 0
    //   100: ifeq +64 -> 164
    //   103: iconst_0
    //   104: istore_0
    //   105: aload_2
    //   106: iconst_0
    //   107: invokeinterface 3855 2 0
    //   112: invokestatic 2965	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   115: astore_3
    //   116: getstatic 3592	com/handcent/o/i:cpY	Ljava/util/Map;
    //   119: aload_3
    //   120: iload_0
    //   121: invokestatic 3493	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   124: invokeinterface 3859 3 0
    //   129: pop
    //   130: getstatic 3597	com/handcent/o/i:cpZ	Ljava/util/concurrent/ConcurrentLinkedQueue;
    //   133: iload_0
    //   134: invokestatic 3493	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   137: invokevirtual 3862	java/util/concurrent/ConcurrentLinkedQueue:add	(Ljava/lang/Object;)Z
    //   140: pop
    //   141: getstatic 3599	com/handcent/o/i:cqa	Ljava/util/concurrent/ConcurrentLinkedQueue;
    //   144: aload_3
    //   145: invokevirtual 3862	java/util/concurrent/ConcurrentLinkedQueue:add	(Ljava/lang/Object;)Z
    //   148: pop
    //   149: iload_0
    //   150: iconst_1
    //   151: iadd
    //   152: istore_0
    //   153: aload_2
    //   154: invokeinterface 3865 1 0
    //   159: istore_1
    //   160: iload_1
    //   161: ifne -56 -> 105
    //   164: aload_2
    //   165: ifnull +9 -> 174
    //   168: aload_2
    //   169: invokeinterface 3867 1 0
    //   174: invokestatic 3869	com/handcent/o/i:RK	()V
    //   177: return
    //   178: astore_2
    //   179: aconst_null
    //   180: astore_2
    //   181: aload_2
    //   182: ifnull -8 -> 174
    //   185: aload_2
    //   186: invokeinterface 3867 1 0
    //   191: goto -17 -> 174
    //   194: astore_3
    //   195: aload_2
    //   196: ifnull +9 -> 205
    //   199: aload_2
    //   200: invokeinterface 3867 1 0
    //   205: aload_3
    //   206: athrow
    //   207: astore_3
    //   208: goto -13 -> 195
    //   211: astore_3
    //   212: goto -31 -> 181
    // Local variable table:
    //   start	length	slot	name	signature
    //   104	49	0	i	int
    //   159	2	1	bool	boolean
    //   1	168	2	localObject1	Object
    //   178	1	2	localException1	Exception
    //   180	20	2	localObject2	Object
    //   25	120	3	localObject3	Object
    //   194	12	3	localObject4	Object
    //   207	1	3	localObject5	Object
    //   211	1	3	localException2	Exception
    //   32	38	4	localContentResolver	android.content.ContentResolver
    //   37	35	5	localUri	Uri
    //   42	38	6	str1	String
    //   66	21	7	str2	String
    // Exception table:
    //   from	to	target	type
    //   22	92	178	java/lang/Exception
    //   22	92	194	finally
    //   94	103	207	finally
    //   105	149	207	finally
    //   153	160	207	finally
    //   94	103	211	java/lang/Exception
    //   105	149	211	java/lang/Exception
    //   153	160	211	java/lang/Exception
  }
  
  public static boolean RM()
  {
    return m.dd(MmsApp.getContext(), eY(MmsApp.getContext())).getBoolean("first_upload_diff", true);
  }
  
  public static void RN()
  {
    boolean bool = RM();
    SharedPreferences.Editor localEditor;
    if (bool)
    {
      localEditor = m.dd(MmsApp.getContext(), eY(MmsApp.getContext())).edit();
      if (bool) {
        break label49;
      }
    }
    label49:
    for (bool = true;; bool = false)
    {
      localEditor.putBoolean("first_upload_diff", bool).commit();
      return;
    }
  }
  
  public static long RO()
  {
    return m.ko(MmsApp.getContext()).getLong("pref_restore_sync_time", 0L);
  }
  
  public static void RP()
  {
    SharedPreferences localSharedPreferences = m.ip(MmsApp.getContext());
    localSharedPreferences.edit().putBoolean("flag_v66tov67", true);
    localSharedPreferences.edit().commit();
  }
  
  public static boolean RQ()
  {
    return m.ip(MmsApp.getContext()).getBoolean("flag_v66tov67", false);
  }
  
  public static void RR()
  {
    SharedPreferences localSharedPreferences = m.ip(MmsApp.getContext());
    localSharedPreferences.edit().remove("flag_v66tov67");
    localSharedPreferences.edit().commit();
  }
  
  public static boolean RS()
  {
    if (!RT()) {}
    String str;
    do
    {
      return false;
      str = m.ip(m.Tx()).getString("pref_mms_framework", ccK);
      if (str.equalsIgnoreCase("system")) {
        return true;
      }
    } while ((str.equalsIgnoreCase("handcent")) || (!str.equalsIgnoreCase("smart")) || (!RV()));
    return true;
  }
  
  public static boolean RT()
  {
    if (cfS < 0) {
      if ((!m.Sr()) || (!RU())) {
        break label30;
      }
    }
    label30:
    for (cfS = 1; cfS > 0; cfS = 0) {
      return true;
    }
    return false;
  }
  
  public static boolean RU()
  {
    Method localMethod1 = m.a(SmsManager.class, hcautz.getInstance().a1("935E1469A5581DA71C84B127310976DB8E1D1BC367D2D3AB"), new Class[] { Context.class, Uri.class, String.class, Bundle.class, PendingIntent.class });
    Method localMethod2 = m.a(SmsManager.class, hcautz.getInstance().a1("DFBAC5A93A0A7EA9F8A00A405CEC7090C8730A71E9505BE5D8EB1AF2E61DA9ED"), new Class[] { Context.class, String.class, Uri.class, Bundle.class, PendingIntent.class });
    return (localMethod1 != null) && (localMethod2 != null);
  }
  
  public static boolean RV()
  {
    Object localObject = (TelephonyManager)m.Tx().getSystemService("phone");
    if (localObject != null) {}
    for (localObject = ((TelephonyManager)localObject).getNetworkOperatorName().trim();; localObject = "") {
      return (bYw.equalsIgnoreCase(m.iU(m.Tx()))) && ((bZg.equalsIgnoreCase((String)localObject)) || (bYE.equalsIgnoreCase((String)localObject)) || (bYW.equalsIgnoreCase((String)localObject)));
    }
  }
  
  public static boolean RW()
  {
    return !"both".equalsIgnoreCase(m.ip(m.Tx()).getString("pref_dual_sim_send_placement", ccJ));
  }
  
  public static int RX()
  {
    String str = m.ip(m.Tx()).getString("pref_dual_sim_send_placement", ccJ);
    if ("sim1".equalsIgnoreCase(str)) {
      return bZm;
    }
    if ("sim2".equalsIgnoreCase(str)) {
      return bZn;
    }
    return bZl;
  }
  
  public static boolean Rv()
  {
    return "constant".equalsIgnoreCase(m.gR("conversation_item_background_mode"));
  }
  
  public static String Rw()
  {
    if (m.Sx()) {
      return "mms";
    }
    return "all";
  }
  
  public static final Animation Rx()
  {
    if (cib == null)
    {
      cib = new TranslateAnimation(2, -100.0F, 0, 0.0F, 0, 0.0F, 0, 0.0F);
      cib.setDuration(400L);
    }
    return cib;
  }
  
  public static final Animation Ry()
  {
    if (cic == null)
    {
      cic = new TranslateAnimation(0, 0.0F, 2, -100.0F, 0, 0.0F, 0, 0.0F);
      cic.setDuration(400L);
    }
    return cic;
  }
  
  public static final Animation Rz()
  {
    if (cic == null)
    {
      cic = new TranslateAnimation(0, 0.0F, 2, 100.0F, 0, 0.0F, 0, 0.0F);
      cic.setDuration(400L);
    }
    return cic;
  }
  
  public static void S(Context paramContext, boolean paramBoolean)
  {
    paramContext = m.ip(paramContext).edit();
    paramContext.putBoolean(cnl, paramBoolean);
    paramContext.commit();
  }
  
  public static void T(Context paramContext, boolean paramBoolean)
  {
    paramContext = m.ip(paramContext).edit();
    paramContext.putBoolean("pref_conversation_splitline_enabled", paramBoolean);
    paramContext.commit();
  }
  
  public static void U(Context paramContext, boolean paramBoolean)
  {
    paramContext = m.ip(paramContext).edit();
    paramContext.putBoolean(cow, paramBoolean);
    paramContext.commit();
  }
  
  public static boolean V(Context paramContext, boolean paramBoolean)
  {
    paramContext = m.ip(paramContext).edit();
    paramContext.putBoolean("show_file_panel", paramBoolean);
    return paramContext.commit();
  }
  
  public static boolean W(Context paramContext, boolean paramBoolean)
  {
    paramContext = m.ip(paramContext).edit();
    paramContext.putBoolean("run_save_old_skin_config_v60", paramBoolean);
    return paramContext.commit();
  }
  
  public static boolean X(Context paramContext, boolean paramBoolean)
  {
    paramContext = m.ip(paramContext).edit();
    paramContext.putBoolean("use_new_skin_config_v60", paramBoolean);
    return paramContext.commit();
  }
  
  public static void Y(Context paramContext, boolean paramBoolean)
  {
    paramContext = m.dd(paramContext, "remote_sms_" + eY(paramContext)).edit();
    paramContext.putBoolean("guide", paramBoolean);
    paramContext.commit();
  }
  
  public static void Z(Context paramContext, boolean paramBoolean)
  {
    paramContext = m.dd(paramContext, "main_guide").edit();
    paramContext.putBoolean("main_guide_10650", paramBoolean);
    paramContext.commit();
  }
  
  public static Boolean a(Context paramContext, l paramL)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    switch (j.bUW[paramL.ordinal()])
    {
    default: 
      if (localSharedPreferences.getString(cjj, cjq).equalsIgnoreCase(cjr)) {
        return Boolean.valueOf(true);
      }
      break;
    case 1: 
      if (m.dd(paramContext, "privacy_" + eY(paramContext)).getString("priv_lock_type", cjq).equalsIgnoreCase(cjr)) {
        return Boolean.valueOf(true);
      }
      return Boolean.valueOf(false);
    }
    return Boolean.valueOf(false);
  }
  
  public static void a(Context paramContext, boolean paramBoolean, l paramL)
  {
    SharedPreferences.Editor localEditor = m.ip(paramContext).edit();
    switch (j.bUW[paramL.ordinal()])
    {
    default: 
      localEditor.putBoolean(cjk, paramBoolean);
      paramContext = localEditor;
    }
    for (;;)
    {
      paramContext.commit();
      return;
      paramContext = m.dd(paramContext, "privacy_" + eY(paramContext)).edit();
      paramContext.putBoolean("priv_lock_visible_pattern", paramBoolean);
    }
  }
  
  public static boolean a(Context paramContext, String paramString, l paramL)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    switch (j.bUW[paramL.ordinal()])
    {
    default: 
      paramContext = localSharedPreferences;
    }
    while (paramContext.getString(cjn, "").equals(m.gB(hcautz.getInstance().a1("7E191A18D811C587") + paramString)))
    {
      return true;
      paramL = m.dd(paramContext, "privacy_" + eY(paramContext));
      paramContext = paramL;
      if (paramL.getString("priv_num_pin", "").equals(m.gB(hcautz.getInstance().a1("7E191A18D811C587") + paramString))) {
        return true;
      }
    }
    return false;
  }
  
  public static int aA(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return jdMethod_do(paramContext);
    }
    paramString = m.C(paramContext, "pref_split160_ex", paramString);
    return Integer.valueOf(localSharedPreferences.getString("pref_split160_ex_" + paramString, String.valueOf(jdMethod_do(paramContext)))).intValue();
  }
  
  public static String aB(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences.getString("pref_smileyes", "system");
    }
    paramContext = m.C(paramContext, "pref_smileyes", paramString);
    return localSharedPreferences.getString("pref_smileyes_" + paramContext, localSharedPreferences.getString("pref_smileyes", "system"));
  }
  
  public static String aC(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences.getString("pref_dealy_send", "disable");
    }
    paramContext = m.C(paramContext, "pref_dealy_send", paramString);
    return localSharedPreferences.getString("pref_dealy_send_" + paramContext, localSharedPreferences.getString("pref_dealy_send", "disable"));
  }
  
  public static String aD(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences.getString("pref_emoji_encoding", "iphone");
    }
    paramContext = m.C(paramContext, "pref_emoji_encoding", paramString);
    return localSharedPreferences.getString("pref_emoji_encoding_" + paramContext, localSharedPreferences.getString("pref_emoji_encoding", "iphone"));
  }
  
  public static String aE(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences.getString("show_as_flatslideshow1", "default");
    }
    paramContext = m.C(paramContext, "show_as_flatslideshow1", paramString);
    return localSharedPreferences.getString("show_as_flatslideshow1_" + paramContext, localSharedPreferences.getString("show_as_flatslideshow1", "default"));
  }
  
  public static String aF(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences.getString("pkey_font_pack", "none");
    }
    paramContext = m.C(paramContext, "pkey_font_pack", paramString);
    return localSharedPreferences.getString("pkey_font_pack_" + paramContext, localSharedPreferences.getString("pkey_font_pack", "none"));
  }
  
  public static String aG(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences.getString("peky_font_file", ceV);
    }
    paramContext = m.C(paramContext, "peky_font_file", paramString);
    return localSharedPreferences.getString("peky_font_file_" + paramContext, localSharedPreferences.getString("peky_font_file", ceV));
  }
  
  public static String aH(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences.getString("pref_font_size", "18");
    }
    paramContext = m.C(paramContext, "pref_font_size", paramString);
    return localSharedPreferences.getString("pref_font_size_" + paramContext, localSharedPreferences.getString("pref_font_size", "18"));
  }
  
  public static String aI(Context paramContext, String paramString)
  {
    String str = m.gR("conversation_style");
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences.getString("pkey_theme_style", str);
    }
    paramContext = m.C(paramContext, "pkey_theme_style", paramString);
    return localSharedPreferences.getString("pkey_theme_style_" + paramContext, localSharedPreferences.getString("pkey_theme_style", str));
  }
  
  public static String aJ(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences.getString("pkey_show_full_editor", "enable");
    }
    paramContext = m.C(paramContext, "pkey_show_full_editor", paramString);
    return localSharedPreferences.getString("pkey_show_full_editor_" + paramContext, localSharedPreferences.getString("pkey_show_full_editor", "enable"));
  }
  
  public static Boolean aK(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return Boolean.valueOf(localSharedPreferences.getBoolean("charm_notification", cgK.booleanValue()));
    }
    paramContext = m.C(paramContext, "pkey_show_full_editor", paramString);
    return Boolean.valueOf(localSharedPreferences.getBoolean("charm_notification_" + paramContext, localSharedPreferences.getBoolean("charm_notification", cgK.booleanValue())));
  }
  
  public static String aL(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences.getString("pkey_show_full_editor_method", "click");
    }
    paramContext = m.C(paramContext, "pkey_show_full_editor_method", paramString);
    return localSharedPreferences.getString("pkey_show_full_editor_method_" + paramContext, localSharedPreferences.getString("pkey_show_full_editor_method", "click"));
  }
  
  public static int aM(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences.getInt("pkey_handcent_rec_bubble_color", cee);
    }
    paramContext = m.C(paramContext, "pkey_handcent_rec_bubble_color", paramString);
    return localSharedPreferences.getInt("pkey_handcent_rec_bubble_color_" + paramContext, localSharedPreferences.getInt("pkey_handcent_rec_bubble_color", cee));
  }
  
  public static int aN(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences.getInt("pkey_handcent_send_bubble_color", cef);
    }
    paramContext = m.C(paramContext, "pkey_handcent_send_bubble_color", paramString);
    return localSharedPreferences.getInt("pkey_handcent_send_bubble_color_" + paramContext, localSharedPreferences.getInt("pkey_handcent_send_bubble_color", cef));
  }
  
  public static int aO(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    int i = m.gS("conversation_incoming_bubble_color");
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences.getInt("pkey_rec_bubble_color", i);
    }
    paramContext = m.C(paramContext, "pkey_rec_bubble_color", paramString);
    return localSharedPreferences.getInt("pkey_rec_bubble_color_" + paramContext, localSharedPreferences.getInt("pkey_rec_bubble_color", i));
  }
  
  public static int aP(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    int i = m.gS("conversation_outgoing_bubble_color");
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences.getInt("pkey_send_bubble_color", i);
    }
    paramContext = m.C(paramContext, "pkey_send_bubble_color", paramString);
    return localSharedPreferences.getInt("pkey_send_bubble_color_" + paramContext, localSharedPreferences.getInt("pkey_send_bubble_color", i));
  }
  
  public static int aQ(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences.getInt("pkey_android_rec_background_color", cei);
    }
    paramContext = m.C(paramContext, "pkey_android_rec_background_color", paramString);
    return localSharedPreferences.getInt("pkey_android_rec_background_color_" + paramContext, localSharedPreferences.getInt("pkey_android_rec_background_color", cei));
  }
  
  public static int aR(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences.getInt("pkey_android_send_background_color", cej);
    }
    paramContext = m.C(paramContext, "pkey_android_send_background_color", paramString);
    return localSharedPreferences.getInt("pkey_android_send_background_color_" + paramContext, localSharedPreferences.getInt("pkey_android_send_background_color", cej));
  }
  
  public static int aS(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences.getInt("pkey_android_rec_font_color", cek);
    }
    paramContext = m.C(paramContext, "pkey_android_rec_font_color", paramString);
    return localSharedPreferences.getInt("pkey_android_rec_font_color_" + paramContext, localSharedPreferences.getInt("pkey_android_rec_font_color", cek));
  }
  
  public static int aT(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    int i = m.gS("full_editor_color");
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences.getInt("pkey_full_editor_font_color", i);
    }
    paramContext = m.C(paramContext, "pkey_full_editor_font_color", paramString);
    return localSharedPreferences.getInt("pkey_full_editor_font_color_" + paramContext, localSharedPreferences.getInt("pkey_full_editor_font_color", i));
  }
  
  public static int aU(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences.getInt("pkey_android_send_font_color", cel);
    }
    paramContext = m.C(paramContext, "pkey_android_send_font_color", paramString);
    return localSharedPreferences.getInt("pkey_android_send_font_color_" + paramContext, localSharedPreferences.getInt("pkey_android_send_font_color", cel));
  }
  
  public static int aV(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences.getInt("pkey_android_datetime_font_color", cem);
    }
    paramContext = m.C(paramContext, "pkey_android_datetime_font_color", paramString);
    return localSharedPreferences.getInt("pkey_android_datetime_font_color_" + paramContext, localSharedPreferences.getInt("pkey_android_datetime_font_color", cel));
  }
  
  public static boolean aW(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences.getBoolean("pkey_bubble_rec_usegrad", ceo.booleanValue());
    }
    paramContext = m.C(paramContext, "pkey_bubble_rec_usegrad", paramString);
    return localSharedPreferences.getBoolean("pkey_bubble_rec_usegrad_" + paramContext, localSharedPreferences.getBoolean("pkey_bubble_rec_usegrad", ceo.booleanValue()));
  }
  
  public static boolean aX(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences.getBoolean("pkey_bubble_send_usegrad", cep.booleanValue());
    }
    paramContext = m.C(paramContext, "pkey_bubble_send_usegrad", paramString);
    return localSharedPreferences.getBoolean("pkey_bubble_send_usegrad_" + paramContext, localSharedPreferences.getBoolean("pkey_bubble_send_usegrad", cep.booleanValue()));
  }
  
  public static String aY(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences.getString("pref_incoming_font", cfm);
    }
    paramContext = m.C(paramContext, "pref_incoming_font", paramString);
    return localSharedPreferences.getString("pref_incoming_font_" + paramContext, localSharedPreferences.getString("pref_incoming_font", cfm));
  }
  
  public static String aZ(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences.getString("pkey_full_editor_font", cfm);
    }
    paramContext = m.C(paramContext, "pkey_full_editor_font", paramString);
    return localSharedPreferences.getString("pkey_full_editor_font_" + paramContext, localSharedPreferences.getString("pkey_full_editor_font", cfm));
  }
  
  public static void aa(Context paramContext, boolean paramBoolean)
  {
    paramContext = m.dd(paramContext, "remote_notice_" + eY(paramContext)).edit();
    paramContext.putBoolean("open", paramBoolean);
    paramContext.commit();
  }
  
  public static void ab(Context paramContext, boolean paramBoolean)
  {
    paramContext = m.dd(paramContext, "remote_tran").edit();
    paramContext.putBoolean("remote_tran", paramBoolean);
    paramContext.commit();
  }
  
  public static void ac(Context paramContext, boolean paramBoolean)
  {
    paramContext = m.dd(paramContext, "remote_sms_" + eY(paramContext)).edit();
    paramContext.putBoolean("open", paramBoolean);
    paramContext.commit();
  }
  
  public static void ad(Context paramContext, boolean paramBoolean)
  {
    paramContext = m.dd(paramContext, "remote_sms_" + eY(paramContext)).edit();
    paramContext.putBoolean("sync_recent", paramBoolean);
    paramContext.commit();
  }
  
  public static void ae(long paramLong)
  {
    dd.d("", "remove top threadid:" + paramLong);
    SqliteWrapper.delete(MmsApp.getContext(), MmsApp.getContext().getContentResolver(), ae.bTb, ac.bQZ + "=" + paramLong, null);
  }
  
  public static void ae(Context paramContext, boolean paramBoolean)
  {
    paramContext = m.dd(paramContext, "remote_sms_" + eY(paramContext)).edit();
    paramContext.putBoolean("device_upload", paramBoolean);
    paramContext.commit();
  }
  
  public static void af(Context paramContext, boolean paramBoolean)
  {
    paramContext = m.dd(paramContext, "remote_sms_" + eY(paramContext)).edit();
    paramContext.putBoolean("link_on", paramBoolean);
    paramContext.commit();
  }
  
  public static boolean af(long paramLong)
  {
    return cpY.containsKey(Long.valueOf(paramLong));
  }
  
  public static int ag(long paramLong)
  {
    dd.d("", "get top threadPostion by id:" + paramLong);
    int i = ((Integer)cpY.get(Long.valueOf(paramLong))).intValue();
    cpZ.remove(Integer.valueOf(i));
    cqa.remove(Long.valueOf(paramLong));
    return i;
  }
  
  public static void ag(Context paramContext, boolean paramBoolean)
  {
    paramContext = m.ip(paramContext).edit();
    paramContext.putBoolean("pref_debug_mode", paramBoolean);
    if (paramBoolean) {
      paramContext.putLong("pref_debug_mode_last", System.currentTimeMillis());
    }
    paramContext.commit();
  }
  
  public static void ah(long paramLong)
  {
    if (paramLong > RO()) {
      m.ko(MmsApp.getContext()).edit().putLong("pref_restore_sync_time", paramLong).commit();
    }
  }
  
  public static void ah(Context paramContext, boolean paramBoolean)
  {
    m.ip(paramContext).edit().putBoolean("pref_key_old_copy", paramBoolean).commit();
  }
  
  public static boolean ai(Context paramContext, boolean paramBoolean)
  {
    return m.ip(paramContext).edit().putBoolean("pref_key_score", paramBoolean).commit();
  }
  
  public static int aw(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    int i = m.gS("conversation_reply_editor_color");
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences.getInt("pkey_editbox_font_color", i);
    }
    paramContext = m.C(paramContext, "pkey_editbox_font_color", paramString);
    return localSharedPreferences.getInt("pkey_editbox_font_color_" + paramContext, localSharedPreferences.getInt("pkey_editbox_font_color", i));
  }
  
  public static int ax(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences.getInt("pkey_incoming_textlink_color", -14137089);
    }
    paramContext = m.C(paramContext, "pkey_incoming_textlink_color", paramString);
    return localSharedPreferences.getInt("pkey_incoming_textlink_color_" + paramContext, localSharedPreferences.getInt("pkey_incoming_textlink_color", -14137089));
  }
  
  public static int ay(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences.getInt("pkey_outgoing_textlink_color", -14137089);
    }
    paramContext = m.C(paramContext, "pkey_outgoing_textlink_color", paramString);
    return localSharedPreferences.getInt("pkey_outgoing_textlink_color_" + paramContext, localSharedPreferences.getInt("pkey_outgoing_textlink_color", -14137089));
  }
  
  public static Boolean az(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return Boolean.valueOf(localSharedPreferences.getBoolean("lite_space", cfj.booleanValue()));
    }
    paramContext = m.C(paramContext, "lite_space", paramString);
    return Boolean.valueOf(localSharedPreferences.getBoolean("lite_space_" + paramContext, localSharedPreferences.getBoolean("lite_space", cfj.booleanValue())));
  }
  
  public static int b(Context paramContext, String paramString, int paramInt)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences.getInt("pkey_rec_start_color", paramInt);
    }
    paramContext = m.C(paramContext, "pkey_rec_start_color", paramString);
    return localSharedPreferences.getInt("pkey_rec_start_color_" + paramContext, localSharedPreferences.getInt("pkey_rec_start_color", paramInt));
  }
  
  public static Boolean b(Context paramContext, l paramL)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    switch (j.bUW[paramL.ordinal()])
    {
    default: 
      return Boolean.valueOf(localSharedPreferences.getBoolean(cjk, cju.booleanValue()));
    }
    return Boolean.valueOf(m.dd(paramContext, "privacy_" + eY(paramContext)).getBoolean("priv_lock_visible_pattern", cju.booleanValue()));
  }
  
  public static void b(Context paramContext, int paramInt1, int paramInt2)
  {
    paramContext = m.ip(paramContext).edit();
    StringBuilder localStringBuilder = new StringBuilder();
    if (paramInt1 < 10) {
      localStringBuilder.append("0");
    }
    localStringBuilder.append(paramInt1);
    localStringBuilder.append(":");
    if (paramInt2 < 10) {
      localStringBuilder.append("0");
    }
    localStringBuilder.append(paramInt2);
    paramContext.putString(clD, localStringBuilder.toString());
    paramContext.commit();
  }
  
  public static void b(Context paramContext, boolean paramBoolean, l paramL)
  {
    SharedPreferences.Editor localEditor = m.ip(paramContext).edit();
    switch (j.bUW[paramL.ordinal()])
    {
    default: 
      localEditor.putBoolean(cjl, paramBoolean);
      paramContext = localEditor;
    }
    for (;;)
    {
      paramContext.commit();
      return;
      paramContext = m.dd(paramContext, "privacy_" + eY(paramContext)).edit();
      paramContext.putBoolean("priv_lock_tactile_feedback", paramBoolean);
    }
  }
  
  public static String bA(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences.getString("flashled_pattern_custom", cgQ);
    }
    paramContext = m.C(paramContext, "flashled_pattern_custom", paramString);
    return localSharedPreferences.getString("flashled_pattern_custom_" + paramContext, localSharedPreferences.getString("flashled_pattern_custom", cgQ));
  }
  
  public static String bB(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences.getString("pref_key_trackballl", "5000");
    }
    paramContext = m.C(paramContext, "pref_key_trackballl", paramString);
    return localSharedPreferences.getString("pref_key_trackballl_" + paramContext, localSharedPreferences.getString("pref_key_trackballl", "5000"));
  }
  
  public static void bC(Context paramContext, String paramString)
  {
    paramContext = m.ip(paramContext).edit();
    paramContext.putString("pkey_blacklist", paramString);
    paramContext.commit();
  }
  
  public static void bD(Context paramContext, String paramString)
  {
    paramContext = m.ip(paramContext).edit();
    paramContext.putString("pkey_blacklist_threads", paramString);
    paramContext.commit();
  }
  
  public static Boolean bE(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return Boolean.valueOf(localSharedPreferences.getBoolean(cig, cii.booleanValue()));
    }
    paramContext = m.C(paramContext, cig, paramString);
    return Boolean.valueOf(localSharedPreferences.getBoolean(cig + "_" + paramContext, localSharedPreferences.getBoolean(cig, cii.booleanValue())));
  }
  
  public static Boolean bF(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return Boolean.valueOf(localSharedPreferences.getBoolean("pref_enable_mms_signature", true));
    }
    paramContext = m.C(paramContext, cig, paramString);
    return Boolean.valueOf(localSharedPreferences.getBoolean("pref_enable_mms_signature_" + paramContext, localSharedPreferences.getBoolean("pref_enable_mms_signature", true)));
  }
  
  public static String bG(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences.getString(cih, cij);
    }
    paramContext = m.C(paramContext, cih, paramString);
    return localSharedPreferences.getString(cih + "_" + paramContext, localSharedPreferences.getString(cih, cij));
  }
  
  public static String bH(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences.getString(ciL, ciM);
    }
    if (paramString.equals("368hqpm33")) {
      return m.dd(paramContext, "privacy_" + eY(paramContext)).getString(ciL, clZ);
    }
    paramContext = m.C(paramContext, ciL, paramString);
    return localSharedPreferences.getString(ciL + "_" + paramContext, localSharedPreferences.getString(ciL, ciM));
  }
  
  public static int bI(Context paramContext, String paramString)
  {
    return Integer.parseInt(bH(paramContext, paramString));
  }
  
  public static void bJ(Context paramContext, String paramString)
  {
    paramContext = m.ip(paramContext).edit();
    paramContext.putString("local_channelid", paramString);
    paramContext.commit();
  }
  
  public static void bK(Context paramContext, String paramString)
  {
    paramContext = m.ip(paramContext).edit();
    paramContext.putString("local_keywords", paramString);
    paramContext.commit();
  }
  
  public static void bL(Context paramContext, String paramString)
  {
    String str;
    if (paramString != null)
    {
      str = paramString;
      if (!"".equals(paramString)) {}
    }
    else
    {
      str = cji;
    }
    paramContext = m.ip(paramContext).edit();
    paramContext.putString(cjh, str);
    paramContext.commit();
  }
  
  public static int bM(Context paramContext)
  {
    paramContext = m.ip(paramContext).getString("pref_key_show_close_dialog", cnN);
    if (cnL.equalsIgnoreCase(paramContext)) {
      return 0;
    }
    if (cnN.equalsIgnoreCase(paramContext)) {
      return 1;
    }
    return 2;
  }
  
  public static void bM(Context paramContext, String paramString)
  {
    paramContext = m.ip(paramContext).edit();
    paramContext.putString(cjn, m.gB("897" + paramString));
    paramContext.commit();
  }
  
  public static void bN(Context paramContext, String paramString)
  {
    paramContext = m.ip(paramContext).edit();
    paramContext.putString(cjj, paramString);
    paramContext.commit();
  }
  
  public static void bO(Context paramContext, String paramString)
  {
    SharedPreferences.Editor localEditor = m.dd(paramContext, "privacy_" + eY(paramContext)).edit();
    localEditor.putString("priv_num_pin", m.gB("897" + paramString));
    if (!eA(paramContext)) {
      localEditor.putBoolean("priv_use_lock", true);
    }
    localEditor.commit();
  }
  
  public static void bP(Context paramContext, String paramString)
  {
    paramContext = m.dd(paramContext, "privacy_" + eY(paramContext)).edit();
    paramContext.putString("priv_lock_type", paramString);
    paramContext.commit();
  }
  
  public static void bQ(Context paramContext, String paramString)
  {
    paramContext = m.dd(paramContext, "privacy_" + eY(paramContext)).edit();
    paramContext.putString("priv_auto_backup_valid_date", paramString);
    paramContext.commit();
  }
  
  public static int bR(Context paramContext, String paramString)
  {
    String[] arrayOfString = paramContext.getResources().getStringArray(2131493041);
    paramContext = aI(paramContext, paramString);
    int i = 0;
    while (i < arrayOfString.length)
    {
      if (paramContext.equals(arrayOfString[i])) {
        return i;
      }
      i += 1;
    }
    return -1;
  }
  
  public static int bS(Context paramContext, String paramString)
  {
    String[] arrayOfString = paramContext.getResources().getStringArray(2131493057);
    paramContext = aJ(paramContext, paramString);
    int i = 0;
    while (i < arrayOfString.length)
    {
      if (paramContext.equals(arrayOfString[i])) {
        return i;
      }
      i += 1;
    }
    return -1;
  }
  
  public static int bT(Context paramContext, String paramString)
  {
    String[] arrayOfString = paramContext.getResources().getStringArray(2131492950);
    paramContext = aL(paramContext, paramString);
    int i = 0;
    while (i < arrayOfString.length)
    {
      if (paramContext.equals(arrayOfString[i])) {
        return i;
      }
      i += 1;
    }
    return -1;
  }
  
  public static Boolean bU(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return Boolean.valueOf(localSharedPreferences.getBoolean(ckl, ckm.booleanValue()));
    }
    paramContext = m.C(paramContext, ckl, paramString);
    return Boolean.valueOf(localSharedPreferences.getBoolean(ckl + "_" + paramContext, localSharedPreferences.getBoolean(ckl, ckm.booleanValue())));
  }
  
  public static int bV(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences.getInt(ckn, cko);
    }
    paramContext = m.C(paramContext, ckn, paramString);
    return localSharedPreferences.getInt(ckn + "_" + paramContext, localSharedPreferences.getInt(ckn, cko));
  }
  
  public static String bW(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    Object localObject = ckr;
    try
    {
      String str = m.gR("contact_picture_in_message_item");
      localObject = str;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences.getString(ckq, (String)localObject);
    }
    paramContext = m.C(paramContext, ckq, paramString);
    return localSharedPreferences.getString(ckq + "_" + paramContext, localSharedPreferences.getString(ckq, (String)localObject));
  }
  
  public static int bX(Context paramContext, String paramString)
  {
    String[] arrayOfString = paramContext.getResources().getStringArray(2131492987);
    paramContext = bW(paramContext, paramString);
    int i = 0;
    while (i < arrayOfString.length)
    {
      if (paramContext.equals(arrayOfString[i])) {
        return i;
      }
      i += 1;
    }
    return -1;
  }
  
  public static String bY(Context paramContext, String paramString)
  {
    String[] arrayOfString1 = paramContext.getResources().getStringArray(2131492912);
    String[] arrayOfString2 = paramContext.getResources().getStringArray(2131493045);
    String str = paramString;
    if (paramString == null) {
      str = eU(paramContext);
    }
    int i = 0;
    while (i < arrayOfString2.length)
    {
      if (str.equals(arrayOfString2[i])) {
        return arrayOfString1[i];
      }
      i += 1;
    }
    return "";
  }
  
  public static void bZ(Context paramContext, String paramString)
  {
    paramContext = m.ip(paramContext).edit();
    paramContext.putString("hc_account_name", paramString);
    paramContext.commit();
  }
  
  public static String ba(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences.getString("pref_editbox_font", cfn);
    }
    paramContext = m.C(paramContext, "pref_editbox_font", paramString);
    return localSharedPreferences.getString("pref_editbox_font_" + paramContext, localSharedPreferences.getString("pref_editbox_font", cfn));
  }
  
  public static String bb(Context paramContext, String paramString)
  {
    paramContext = m.ip(paramContext);
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return paramContext.getString("pref_contact_font", cfm);
    }
    return paramContext.getString("pref_contact_font_" + paramString, paramContext.getString("pref_contact_font", cfm));
  }
  
  public static String bc(Context paramContext, String paramString)
  {
    paramContext = m.ip(paramContext);
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return paramContext.getString("pref_subject_font", cfp);
    }
    return paramContext.getString("pref_subject_font_" + paramString, paramContext.getString("pref_subject_font", cfp));
  }
  
  public static String bd(Context paramContext, String paramString)
  {
    paramContext = m.ip(paramContext);
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return paramContext.getString("pref_date_font", cfp);
    }
    return paramContext.getString("pref_date_font_" + paramString, paramContext.getString("pref_date_font", cfp));
  }
  
  public static String be(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences.getString("pref_conversation_date_font", cfo);
    }
    paramContext = m.C(paramContext, "pref_conversation_date_font", paramString);
    return localSharedPreferences.getString("pref_conversation_date_font_" + paramContext, localSharedPreferences.getString("pref_conversation_date_font", cfo));
  }
  
  public static String bf(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences.getString("pref_outgoing_font", cfm);
    }
    paramContext = m.C(paramContext, "pref_outgoing_font", paramString);
    return localSharedPreferences.getString("pref_outgoing_font_" + paramContext, localSharedPreferences.getString("pref_outgoing_font", cfm));
  }
  
  public static int bg(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    int i = m.gS("conversation_date_text_color");
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences.getInt("pkey_datetime_font_color", i);
    }
    paramContext = m.C(paramContext, "pkey_datetime_font_color", paramString);
    return localSharedPreferences.getInt("pkey_datetime_font_color_" + paramContext, localSharedPreferences.getInt("pkey_datetime_font_color", i));
  }
  
  public static boolean bh(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences.getBoolean("pref_key_usepic", cex.booleanValue());
    }
    paramContext = m.C(paramContext, "pref_key_usepic", paramString);
    return localSharedPreferences.getBoolean("pref_key_usepic_" + paramContext, localSharedPreferences.getBoolean("pref_key_usepic", cex.booleanValue()));
  }
  
  public static boolean bi(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences.getBoolean("pref_key_use_contact_pic", cey.booleanValue());
    }
    paramContext = m.C(paramContext, "pref_key_use_contact_pic", paramString);
    return localSharedPreferences.getBoolean("pref_key_use_contact_pic_" + paramContext, localSharedPreferences.getBoolean("pref_key_use_contact_pic", cey.booleanValue()));
  }
  
  public static boolean bj(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences.getBoolean("pref_key_use_themecolor", cez.booleanValue());
    }
    paramContext = m.C(paramContext, "pref_key_use_themecolor", paramString);
    return localSharedPreferences.getBoolean("pref_key_use_themecolor_" + paramContext, localSharedPreferences.getBoolean("pref_key_use_themecolor", cez.booleanValue()));
  }
  
  public static int bk(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences.getInt("pref_key_background_color", -16777216);
    }
    paramContext = m.C(paramContext, "pref_key_background_color", paramString);
    return localSharedPreferences.getInt("pref_key_background_color_" + paramContext, localSharedPreferences.getInt("pref_key_background_color", -16777216));
  }
  
  public static boolean bl(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences.getBoolean("pkey_bubble_showdate", ceB.booleanValue());
    }
    paramContext = m.C(paramContext, "pkey_bubble_showdate", paramString);
    return localSharedPreferences.getBoolean("pkey_bubble_showdate_" + paramContext, localSharedPreferences.getBoolean("pkey_bubble_showdate", ceB.booleanValue()));
  }
  
  public static boolean bm(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences.getBoolean("pkey_bubble_hyperlink", true);
    }
    paramContext = m.C(paramContext, "pkey_bubble_hyperlink", paramString);
    return localSharedPreferences.getBoolean("pkey_bubble_hyperlink_" + paramContext, localSharedPreferences.getBoolean("pkey_bubble_hyperlink", true));
  }
  
  public static boolean bn(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences.getBoolean("pkey_bubble_showname", ceU.booleanValue());
    }
    paramContext = m.C(paramContext, "pkey_bubble_showname", paramString);
    return localSharedPreferences.getBoolean("pkey_bubble_showname_" + paramContext, localSharedPreferences.getBoolean("pkey_bubble_showname", ceU.booleanValue()));
  }
  
  public static boolean bo(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences.getBoolean("pre_noti_show_text", true);
    }
    paramContext = m.C(paramContext, "pre_noti_show_text", paramString);
    return localSharedPreferences.getBoolean("pre_noti_show_text_" + paramContext, localSharedPreferences.getBoolean("pre_noti_show_text", true));
  }
  
  public static String bp(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences.getString("vibrate_type", "1");
    }
    paramContext = m.C(paramContext, "vibrate_type", paramString);
    return localSharedPreferences.getString("vibrate_type_" + paramContext, localSharedPreferences.getString("vibrate_type", "1"));
  }
  
  public static boolean bq(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences.getBoolean("pref_key_enable_notifications", cgu.booleanValue());
    }
    paramContext = m.C(paramContext, "pref_key_enable_notifications", paramString);
    return localSharedPreferences.getBoolean("pref_key_enable_notifications_" + paramContext, localSharedPreferences.getBoolean("pref_key_enable_notifications", cgu.booleanValue()));
  }
  
  public static boolean br(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences.getBoolean("pref_key_notif_privacy", cgx.booleanValue());
    }
    paramContext = m.C(paramContext, "pref_key_notif_privacy", paramString);
    return localSharedPreferences.getBoolean("pref_key_notif_privacy_" + paramContext, localSharedPreferences.getBoolean("pref_key_notif_privacy", cgx.booleanValue()));
  }
  
  public static boolean bs(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences.getBoolean("pref_key_vibrate", cgy.booleanValue());
    }
    paramContext = m.C(paramContext, "pref_key_vibrate", paramString);
    return localSharedPreferences.getBoolean("pref_key_vibrate_" + paramContext, localSharedPreferences.getBoolean("pref_key_vibrate", cgy.booleanValue()));
  }
  
  public static String bt(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences.getString("pref_key_vibrate_pattern", "default");
    }
    paramContext = m.C(paramContext, "pref_key_vibrate_pattern", paramString);
    return localSharedPreferences.getString("pref_key_vibrate_pattern_" + paramContext, localSharedPreferences.getString("pref_key_vibrate_pattern", "default"));
  }
  
  public static String bu(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences.getString("vibrate_pattern_custom", cgN);
    }
    paramContext = m.C(paramContext, "vibrate_pattern_custom", paramString);
    return localSharedPreferences.getString("vibrate_pattern_custom_" + paramContext, localSharedPreferences.getString("vibrate_pattern_custom", cgN));
  }
  
  public static String bv(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences.getString("pref_key_ringtone", "content://settings/system/notification_sound");
    }
    paramContext = m.C(paramContext, "pref_key_ringtone", paramString);
    return localSharedPreferences.getString("pref_key_ringtone_" + paramContext, localSharedPreferences.getString("pref_key_ringtone", "content://settings/system/notification_sound"));
  }
  
  public static boolean bw(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences.getBoolean("pkey_blink_led", cgA.booleanValue());
    }
    paramContext = m.C(paramContext, "pkey_blink_led", paramString);
    return localSharedPreferences.getBoolean("pkey_blink_led_" + paramContext, localSharedPreferences.getBoolean("pkey_blink_led", cgA.booleanValue()));
  }
  
  public static String bx(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences.getString("pkey_led_color1", "Blue");
    }
    paramContext = m.C(paramContext, "pkey_led_color1", paramString);
    return localSharedPreferences.getString("pkey_led_color1_" + paramContext, localSharedPreferences.getString("pkey_led_color1", "Blue"));
  }
  
  public static String by(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences.getString("pkey_led_color2", "Blue");
    }
    paramContext = m.C(paramContext, "pkey_led_color2", paramString);
    return localSharedPreferences.getString("pkey_led_color2_" + paramContext, localSharedPreferences.getString("pkey_led_color2", "Blue"));
  }
  
  public static String bz(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences.getString("pkey_led_frequency", "0");
    }
    paramContext = m.C(paramContext, "pkey_led_frequency", paramString);
    return localSharedPreferences.getString("pkey_led_frequency_" + paramContext, localSharedPreferences.getString("pkey_led_frequency", "0"));
  }
  
  public static int c(Context paramContext, String paramString, int paramInt)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences.getInt("pkey_rec_end_color", paramInt);
    }
    paramContext = m.C(paramContext, "pkey_rec_end_color", paramString);
    return localSharedPreferences.getInt("pkey_rec_end_color_" + paramContext, localSharedPreferences.getInt("pkey_rec_end_color", paramInt));
  }
  
  public static Boolean c(Context paramContext, l paramL)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    switch (j.bUW[paramL.ordinal()])
    {
    default: 
      return Boolean.valueOf(localSharedPreferences.getBoolean(cjl, cjv.booleanValue()));
    }
    return Boolean.valueOf(m.dd(paramContext, "privacy_" + eY(paramContext)).getBoolean("priv_lock_tactile_feedback", cjv.booleanValue()));
  }
  
  public static void c(Context paramContext, int paramInt1, int paramInt2)
  {
    paramContext = m.ip(paramContext).edit();
    StringBuilder localStringBuilder = new StringBuilder();
    if (paramInt1 < 10) {
      localStringBuilder.append("0");
    }
    localStringBuilder.append(paramInt1);
    localStringBuilder.append(":");
    if (paramInt2 < 10) {
      localStringBuilder.append("0");
    }
    localStringBuilder.append(paramInt2);
    paramContext.putString(clE, localStringBuilder.toString());
    paramContext.commit();
  }
  
  public static String cA(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences1 = m.dd(paramContext, "privacy_" + eY(paramContext));
    if (TextUtils.isEmpty(paramString)) {
      return m.go(localSharedPreferences1.getString(clL, fz(paramContext)));
    }
    SharedPreferences localSharedPreferences2 = m.ip(paramContext);
    paramString = m.C(paramContext, clL, paramString);
    return m.go(localSharedPreferences2.getString(clL + "_" + paramString, localSharedPreferences1.getString(clL, fz(paramContext))));
  }
  
  public static void cB(Context paramContext, String paramString)
  {
    m.dd(paramContext, "privacy_" + eY(paramContext)).edit().putString(clM, m.gp(paramString)).commit();
  }
  
  public static String cC(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences1 = m.dd(paramContext, "privacy_" + eY(paramContext));
    if (TextUtils.isEmpty(paramString)) {
      return m.go(localSharedPreferences1.getString(clM, fA(paramContext)));
    }
    SharedPreferences localSharedPreferences2 = m.ip(paramContext);
    paramString = m.C(paramContext, clM, paramString);
    return m.go(localSharedPreferences2.getString(clM + "_" + paramString, localSharedPreferences1.getString(clM, fA(paramContext))));
  }
  
  public static Boolean cD(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences1 = m.dd(paramContext, "privacy_" + eY(paramContext));
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return Boolean.valueOf(localSharedPreferences1.getBoolean(clR, cgK.booleanValue()));
    }
    SharedPreferences localSharedPreferences2 = m.ip(paramContext);
    paramContext = m.C(paramContext, "pkey_show_full_editor", paramString);
    return Boolean.valueOf(localSharedPreferences2.getBoolean(clR + "_" + paramContext, localSharedPreferences1.getBoolean(clR, cgK.booleanValue())));
  }
  
  public static boolean cE(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences1 = m.dd(paramContext, "privacy_" + eY(paramContext));
    if (TextUtils.isEmpty(paramString)) {
      return localSharedPreferences1.getBoolean(clN, clT.booleanValue());
    }
    SharedPreferences localSharedPreferences2 = m.ip(paramContext);
    paramContext = m.C(paramContext, clN, paramString);
    return localSharedPreferences2.getBoolean(clN + "_" + paramContext, localSharedPreferences1.getBoolean(clN, clT.booleanValue()));
  }
  
  public static String cF(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences.getString("pref_conversation_contactfont", cms);
    }
    paramContext = m.C(paramContext, "pref_conversation_contactfont", paramString);
    return localSharedPreferences.getString("pref_conversation_contactfont_" + paramContext, localSharedPreferences.getString("pref_conversation_contactfont", cms));
  }
  
  public static int cG(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    int i = m.gS("conversation_contact_title_text_color");
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences.getInt("pref_conversation_contact_font_color", i);
    }
    paramContext = m.C(paramContext, "pref_conversation_contact_font_color", paramString);
    return localSharedPreferences.getInt("pref_conversation_contact_font_color_" + paramContext, localSharedPreferences.getInt("pref_conversation_contact_font_color", i));
  }
  
  public static String cH(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences.getString(cmI, cmJ);
    }
    paramContext = m.C(paramContext, cmI, paramString);
    return localSharedPreferences.getString(cmI + "_" + paramContext, localSharedPreferences.getString(cmI, cmJ));
  }
  
  public static int cI(Context paramContext, String paramString)
  {
    return Integer.parseInt(cH(paramContext, paramString));
  }
  
  public static int cJ(Context paramContext, String paramString)
  {
    String[] arrayOfString = paramContext.getResources().getStringArray(2131493017);
    paramContext = cL(paramContext, paramString);
    int i = 0;
    while (i < arrayOfString.length)
    {
      if (paramContext.equals(arrayOfString[i])) {
        return i;
      }
      i += 1;
    }
    return -1;
  }
  
  public static int cK(Context paramContext, String paramString)
  {
    String[] arrayOfString = paramContext.getResources().getStringArray(2131492991);
    paramContext = cM(paramContext, paramString);
    int i = 0;
    while (i < arrayOfString.length)
    {
      if (paramContext.equals(arrayOfString[i])) {
        return i;
      }
      i += 1;
    }
    return -1;
  }
  
  public static String cL(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    if (!localSharedPreferences.getBoolean("pref_display_msg_counter", true))
    {
      SharedPreferences.Editor localEditor = localSharedPreferences.edit();
      localEditor.putString("pkey_message_counter_mode", "disable");
      localEditor.remove("pref_display_msg_counter");
      localEditor.commit();
    }
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences.getString("pkey_message_counter_mode", "normal");
    }
    paramContext = m.C(paramContext, "pkey_message_counter_mode", paramString);
    return localSharedPreferences.getString("pkey_message_counter_mode_" + paramContext, localSharedPreferences.getString("pkey_message_counter_mode", "normal"));
  }
  
  public static String cM(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences.getString("pkey_text_counter_mode", "yes");
    }
    paramContext = m.C(paramContext, "pkey_text_counter_mode", paramString);
    return localSharedPreferences.getString("pkey_text_counter_mode_" + paramContext, localSharedPreferences.getString("pkey_text_counter_mode", "yes"));
  }
  
  public static Boolean cN(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return Boolean.valueOf(localSharedPreferences.getBoolean(cnJ, cnK.booleanValue()));
    }
    paramContext = m.C(paramContext, cnJ, paramString);
    return Boolean.valueOf(localSharedPreferences.getBoolean(cnJ + "_" + paramContext, localSharedPreferences.getBoolean(cnJ, cnK.booleanValue())));
  }
  
  public static boolean cO(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences.getBoolean("pref_key_popup_usepic", coa.booleanValue());
    }
    paramContext = m.C(paramContext, "pref_key_popup_usepic", paramString);
    return localSharedPreferences.getBoolean("pref_key_popup_usepic_" + paramContext, localSharedPreferences.getBoolean("pref_key_popup_usepic", coa.booleanValue()));
  }
  
  public static boolean cP(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences.getBoolean("pref_key_popup_use_skin_bg", cob.booleanValue());
    }
    paramContext = m.C(paramContext, "pref_key_popup_use_skin_bg", paramString);
    return localSharedPreferences.getBoolean("pref_key_popup_use_skin_bg_" + paramContext, localSharedPreferences.getBoolean("pref_key_popup_use_skin_bg", cob.booleanValue()));
  }
  
  public static int cQ(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences.getInt("pref_key_popup_background_color", -16777216);
    }
    paramContext = m.C(paramContext, "pref_key_popup_background_color", paramString);
    return localSharedPreferences.getInt("pref_key_popup_background_color_" + paramContext, localSharedPreferences.getInt("pref_key_popup_background_color", -16777216));
  }
  
  public static String cR(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences.getString("pref_conversation_numbersfont", cor);
    }
    paramContext = m.C(paramContext, "pref_conversation_numbersfont", paramString);
    return localSharedPreferences.getString("pref_conversation_numbersfont_" + paramContext, localSharedPreferences.getString("pref_conversation_numbersfont", cor));
  }
  
  public static int cS(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    int i = m.gS("conversation_contact_number_text_color");
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences.getInt("pref_conversation_numbers_font_color", i);
    }
    paramContext = m.C(paramContext, "pref_conversation_numbers_font_color", paramString);
    return localSharedPreferences.getInt("pref_conversation_numbers_font_color_" + paramContext, localSharedPreferences.getInt("pref_conversation_numbers_font_color", i));
  }
  
  public static void cT(Context paramContext, String paramString)
  {
    paramContext = m.ip(paramContext).edit();
    paramContext.putString("pref_hc_groups", paramString);
    paramContext.commit();
  }
  
  public static boolean cU(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences.getBoolean("pref_key_conv_use_skin_bg", cob.booleanValue());
    }
    paramContext = m.C(paramContext, "pref_key_conv_use_skin_bg", paramString);
    return localSharedPreferences.getBoolean("pref_key_conv_use_skin_bg_" + paramContext, localSharedPreferences.getBoolean("pref_key_conv_use_skin_bg", cob.booleanValue()));
  }
  
  public static String cV(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences.getString("pref_colorful_bubble_type", "withskin");
    }
    paramContext = m.C(paramContext, "pref_colorful_bubble_type", paramString);
    return localSharedPreferences.getString("pref_colorful_bubble_type_" + paramContext, localSharedPreferences.getString("pref_colorful_bubble_type", "withskin"));
  }
  
  public static int cW(Context paramContext, String paramString)
  {
    int i = 0;
    String[] arrayOfString = new String[6];
    arrayOfString[0] = "withskin";
    arrayOfString[1] = "cbt_default";
    arrayOfString[2] = "cbt_hcclassic";
    arrayOfString[3] = "cbt_iphone";
    arrayOfString[4] = "cbt_test1";
    arrayOfString[5] = "cbt_test2";
    paramContext = cV(paramContext, paramString);
    while (i < arrayOfString.length)
    {
      if (paramContext.equals(arrayOfString[i])) {
        return i;
      }
      i += 1;
    }
    return -1;
  }
  
  public static boolean cX(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    String str = m.gR("contact_picture_in_conversation_title");
    boolean bool1 = cpd.booleanValue();
    try
    {
      boolean bool2 = Boolean.parseBoolean(str);
      bool1 = bool2;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences.getBoolean("pref_contact_picture_in_title", bool1);
    }
    paramContext = m.C(paramContext, "pref_contact_picture_in_title", paramString);
    return localSharedPreferences.getBoolean("pref_contact_picture_in_title_" + paramContext, localSharedPreferences.getBoolean("pref_contact_picture_in_title", bool1));
  }
  
  public static void cY(Context paramContext, String paramString)
  {
    paramContext = m.dd(paramContext, "remote_sms_" + eY(paramContext)).edit();
    paramContext.putString("device_name", paramString);
    paramContext.commit();
  }
  
  public static void cZ(Context paramContext, String paramString)
  {
    paramContext = m.dd(paramContext, "remote_sms_" + eY(paramContext)).edit();
    paramContext.putString("last_control", paramString);
    paramContext.commit();
  }
  
  public static void ca(Context paramContext, String paramString)
  {
    paramContext = m.ip(paramContext).edit();
    paramContext.putString("hc_mac", paramString);
    paramContext.commit();
  }
  
  public static void cb(Context paramContext, String paramString)
  {
    paramContext = m.ip(paramContext).edit();
    paramContext.putString("hc_promotion_info", paramString);
    paramContext.commit();
  }
  
  public static void cc(Context paramContext, String paramString)
  {
    paramContext = m.ip(paramContext).edit();
    paramContext.putString("hc_promotion_url", paramString);
    paramContext.commit();
  }
  
  public static void cd(Context paramContext, String paramString)
  {
    long l2 = Long.parseLong(paramString);
    long l1;
    if (l2 >= 0L)
    {
      l1 = l2;
      if (l2 <= 10000L) {}
    }
    else
    {
      l1 = cla;
    }
    paramContext = m.ip(paramContext).edit();
    paramContext.putLong("hc_promotion_time", l1);
    paramContext.commit();
  }
  
  public static void ce(Context paramContext, String paramString)
  {
    paramContext = m.ip(paramContext).edit();
    paramContext.putString("skin_type", paramString);
    if (paramContext.commit()) {
      cll = paramString;
    }
  }
  
  public static void cf(Context paramContext, String paramString)
  {
    paramContext = m.ip(paramContext).edit();
    paramContext.putString(clm, paramString);
    if (paramContext.commit()) {
      mPackageName = paramString;
    }
  }
  
  public static void cg(Context paramContext, String paramString)
  {
    w(paramContext, paramString, null);
  }
  
  public static void ch(Context paramContext, String paramString)
  {
    paramContext = m.ip(paramContext).edit();
    paramContext.putString(clo, paramString);
    paramContext.commit();
  }
  
  public static String ci(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    if (paramString != null) {
      return localSharedPreferences.getString(clo + "_" + m.di(paramContext, paramString), localSharedPreferences.getString(clo, null));
    }
    return localSharedPreferences.getString(clo, null);
  }
  
  public static void cj(Context paramContext, String paramString)
  {
    paramContext = m.ip(paramContext).edit();
    paramContext.putString(clq, paramString);
    paramContext.commit();
    m.csE = paramString;
  }
  
  public static void ck(Context paramContext, String paramString)
  {
    paramContext = m.ip(paramContext).edit();
    paramContext.putString(clC, paramString);
    paramContext.commit();
  }
  
  public static boolean cl(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences1 = m.dd(paramContext, "privacy_" + eY(paramContext));
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences1.getBoolean("pref_key_enable_notifications", clY.booleanValue());
    }
    SharedPreferences localSharedPreferences2 = m.ip(paramContext);
    paramContext = m.C(paramContext, "pref_key_enable_notifications", paramString);
    return localSharedPreferences2.getBoolean("pref_key_enable_notifications_" + paramContext, localSharedPreferences1.getBoolean("pref_key_enable_notifications", clY.booleanValue()));
  }
  
  public static String cm(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences1 = m.dd(paramContext, "privacy_" + eY(paramContext));
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences1.getString("pref_key_ringtone", "content://settings/system/notification_sound");
    }
    SharedPreferences localSharedPreferences2 = m.ip(paramContext);
    paramContext = m.C(paramContext, "pref_key_ringtone", paramString);
    return localSharedPreferences2.getString("pref_key_ringtone_" + paramContext, localSharedPreferences1.getString("pref_key_ringtone", "content://settings/system/notification_sound"));
  }
  
  public static String cn(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences1 = m.dd(paramContext, "privacy_" + eY(paramContext));
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences1.getString(ciL, clZ);
    }
    SharedPreferences localSharedPreferences2 = m.ip(paramContext);
    paramContext = m.C(paramContext, ciL, paramString);
    return localSharedPreferences2.getString(ciL + "_" + paramContext, localSharedPreferences1.getString(ciL, clZ));
  }
  
  public static String co(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences1 = m.dd(paramContext, "privacy_" + eY(paramContext));
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences1.getString("vibrate_type", "1");
    }
    SharedPreferences localSharedPreferences2 = m.ip(paramContext);
    paramContext = m.C(paramContext, "vibrate_type", paramString);
    return localSharedPreferences2.getString("vibrate_type_" + paramContext, localSharedPreferences1.getString("vibrate_type", "1"));
  }
  
  public static String cp(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences1 = m.dd(paramContext, "privacy_" + eY(paramContext));
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences1.getString("pref_key_vibrate_pattern", "default");
    }
    SharedPreferences localSharedPreferences2 = m.ip(paramContext);
    paramContext = m.C(paramContext, "pref_key_vibrate_pattern", paramString);
    return localSharedPreferences2.getString("pref_key_vibrate_pattern_" + paramContext, localSharedPreferences1.getString("pref_key_vibrate_pattern", "default"));
  }
  
  public static boolean cq(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences1 = m.dd(paramContext, "privacy_" + eY(paramContext));
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences1.getBoolean("pkey_blink_led", cme.booleanValue());
    }
    SharedPreferences localSharedPreferences2 = m.ip(paramContext);
    paramContext = m.C(paramContext, "pkey_blink_led", paramString);
    return localSharedPreferences2.getBoolean("pkey_blink_led_" + paramContext, localSharedPreferences1.getBoolean("pkey_blink_led", cme.booleanValue()));
  }
  
  public static String cr(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences1 = m.dd(paramContext, "privacy_" + eY(paramContext));
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences1.getString("pkey_led_color1", "Blue");
    }
    SharedPreferences localSharedPreferences2 = m.ip(paramContext);
    paramContext = m.C(paramContext, "pkey_led_color1", paramString);
    return localSharedPreferences2.getString("pkey_led_color1_" + paramContext, localSharedPreferences1.getString("pkey_led_color1", "Blue"));
  }
  
  public static String cs(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences1 = m.dd(paramContext, "privacy_" + eY(paramContext));
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences1.getString("pkey_led_color2", "Blue");
    }
    SharedPreferences localSharedPreferences2 = m.ip(paramContext);
    paramContext = m.C(paramContext, "pkey_led_color2", paramString);
    return localSharedPreferences2.getString("pkey_led_color2_" + paramContext, localSharedPreferences1.getString("pkey_led_color2", "Blue"));
  }
  
  public static String ct(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences1 = m.dd(paramContext, "privacy_" + eY(paramContext));
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences1.getString("pkey_led_frequency", "0");
    }
    SharedPreferences localSharedPreferences2 = m.ip(paramContext);
    paramContext = m.C(paramContext, "pkey_led_frequency", paramString);
    return localSharedPreferences2.getString("pkey_led_frequency_" + paramContext, localSharedPreferences1.getString("pkey_led_frequency", "0"));
  }
  
  public static String cu(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences1 = m.dd(paramContext, "privacy_" + eY(paramContext));
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences1.getString("pref_key_trackballl", "5000");
    }
    SharedPreferences localSharedPreferences2 = m.ip(paramContext);
    paramContext = m.C(paramContext, "pref_key_trackballl", paramString);
    return localSharedPreferences2.getString("pref_key_trackballl_" + paramContext, localSharedPreferences1.getString("pref_key_trackballl", "5000"));
  }
  
  public static String cv(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences1 = m.dd(paramContext, "privacy_" + eY(paramContext));
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences1.getString("vibrate_pattern_custom", cmj);
    }
    SharedPreferences localSharedPreferences2 = m.ip(paramContext);
    paramContext = m.C(paramContext, "vibrate_pattern_custom", paramString);
    return localSharedPreferences2.getString("vibrate_pattern_custom_" + paramContext, localSharedPreferences1.getString("vibrate_pattern_custom", cmj));
  }
  
  public static void cv(boolean paramBoolean)
  {
    cpK = paramBoolean;
  }
  
  public static String cw(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences1 = m.dd(paramContext, "privacy_" + eY(paramContext));
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences1.getString("flashled_pattern_custom", cmk);
    }
    SharedPreferences localSharedPreferences2 = m.ip(paramContext);
    paramContext = m.C(paramContext, "flashled_pattern_custom", paramString);
    return localSharedPreferences2.getString("flashled_pattern_custom_" + paramContext, localSharedPreferences1.getString("flashled_pattern_custom", cmk));
  }
  
  public static int cx(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences1 = m.dd(paramContext, "privacy_" + eY(paramContext));
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences1.getInt(ckn, cml);
    }
    SharedPreferences localSharedPreferences2 = m.ip(paramContext);
    paramContext = m.C(paramContext, ckn, paramString);
    return localSharedPreferences2.getInt(ckn + "_" + paramContext, localSharedPreferences1.getInt(ckn, cml));
  }
  
  public static boolean cy(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences1 = m.dd(paramContext, "privacy_" + eY(paramContext));
    if (TextUtils.isEmpty(paramString)) {
      return localSharedPreferences1.getBoolean(clK, clS.booleanValue());
    }
    SharedPreferences localSharedPreferences2 = m.ip(paramContext);
    paramContext = m.C(paramContext, clK, paramString);
    return localSharedPreferences2.getBoolean(clK + "_" + paramContext, localSharedPreferences1.getBoolean(clK, clS.booleanValue()));
  }
  
  public static void cz(Context paramContext, String paramString)
  {
    m.dd(paramContext, "privacy_" + eY(paramContext)).edit().putString(clL, m.gp(paramString)).commit();
  }
  
  public static int d(Context paramContext, String paramString, int paramInt)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences.getInt("pkey_send_start_color", paramInt);
    }
    paramContext = m.C(paramContext, "pkey_send_start_color", paramString);
    return localSharedPreferences.getInt("pkey_send_start_color_" + paramContext, localSharedPreferences.getInt("pkey_send_start_color", paramInt));
  }
  
  public static void d(Context paramContext, l paramL)
  {
    new aaf(paramContext, paramL).aqe();
    paramContext = m.dd(paramContext, "privacy_" + eY(paramContext)).edit();
    switch (j.bUW[paramL.ordinal()])
    {
    }
    for (;;)
    {
      paramContext.commit();
      return;
      paramContext.remove("priv_use_lock");
      paramContext.remove("priv_num_pin");
      paramContext.putString("priv_lock_type", "0");
    }
  }
  
  public static Boolean dA(Context paramContext)
  {
    return Boolean.valueOf(m.ip(paramContext).getBoolean("popup_confirm_deletion", chC.booleanValue()));
  }
  
  public static Boolean dB(Context paramContext)
  {
    return Boolean.valueOf(m.ip(paramContext).getBoolean("popup_backkey", chB.booleanValue()));
  }
  
  public static Boolean dC(Context paramContext)
  {
    return Boolean.valueOf(m.ip(paramContext).getBoolean("pkey_popup_autolink", chz.booleanValue()));
  }
  
  public static Boolean dD(Context paramContext)
  {
    if (go(paramContext)) {
      return Boolean.valueOf(m.ip(paramContext).getBoolean("pref_group_mms", dE(paramContext)));
    }
    return Boolean.valueOf(false);
  }
  
  public static boolean dE(Context paramContext)
  {
    return "US".equalsIgnoreCase(m.iP(paramContext));
  }
  
  public static String dF(Context paramContext)
  {
    return "handcent";
  }
  
  public static Boolean dG(Context paramContext)
  {
    return Boolean.valueOf(m.ip(paramContext).getBoolean("pkey_show_blacklist", chY.booleanValue()));
  }
  
  public static String dH(Context paramContext)
  {
    return m.ip(paramContext).getString("pkey_blacklist", "");
  }
  
  public static String dI(Context paramContext)
  {
    return m.ip(paramContext).getString("pkey_blacklist_threads", "");
  }
  
  public static String dJ(Context paramContext)
  {
    return m.ip(paramContext).getString("pkey_popup_window_mode", "0");
  }
  
  public static Boolean dK(Context paramContext)
  {
    return Boolean.valueOf(m.ip(paramContext).getBoolean("widget_show_zero", chA.booleanValue()));
  }
  
  public static String dL(Context paramContext)
  {
    Object localObject = paramContext.getResources().getStringArray(2131492916);
    paramContext = new ArrayList();
    int i = 0;
    while (i < localObject.length)
    {
      paramContext.add(new em("1", localObject[i]));
      i += 1;
    }
    localObject = new en(paramContext);
    String str = ((en)localObject).toString();
    paramContext.clear();
    ((en)localObject).clear();
    return str;
  }
  
  public static String[] dM(Context paramContext)
  {
    try
    {
      if (cfC == null)
      {
        paramContext = new en(m.go(m.ip(paramContext).getString("pref_wear_device_quick_suggestion", dN(paramContext))));
        if (paramContext.getList().size() > 0)
        {
          cfC = new String[paramContext.getList().size()];
          int i = 0;
          while (i < paramContext.getList().size())
          {
            cfC[i] = ((em)paramContext.getList().get(i)).getValue();
            i += 1;
          }
        }
      }
      return cfC;
    }
    catch (Exception paramContext)
    {
      cfC = null;
    }
  }
  
  public static String dN(Context paramContext)
  {
    Object localObject = paramContext.getResources().getStringArray(2131493067);
    paramContext = new ArrayList();
    int i = 0;
    while (i < localObject.length)
    {
      paramContext.add(new em("1", localObject[i]));
      i += 1;
    }
    localObject = new en(paramContext);
    String str = ((en)localObject).toString();
    paramContext.clear();
    ((en)localObject).clear();
    return str;
  }
  
  public static boolean dO(Context paramContext)
  {
    return m.ip(paramContext).getBoolean("pref_wear_device_notification", ccM.booleanValue());
  }
  
  public static int dP(Context paramContext)
  {
    return m.ip(paramContext).getInt(cil, m.gS("conversation_list_contact_text_color"));
  }
  
  public static int dQ(Context paramContext)
  {
    return m.ip(paramContext).getInt(cim, m.gS("conversation_list_subject_text_color"));
  }
  
  public static int dR(Context paramContext)
  {
    return m.ip(paramContext).getInt(cin, m.gS("conversation_list_date_text_color"));
  }
  
  public static int dS(Context paramContext)
  {
    return m.ip(paramContext).getInt(cio, ciu);
  }
  
  public static int dT(Context paramContext)
  {
    return m.ip(paramContext).getInt(cip, civ);
  }
  
  public static Boolean dU(Context paramContext)
  {
    return Boolean.valueOf(m.ip(paramContext).getBoolean(ciq, ciw.booleanValue()));
  }
  
  public static Boolean dV(Context paramContext)
  {
    return Boolean.valueOf(m.ip(paramContext).getBoolean(cik, cix.booleanValue()));
  }
  
  public static Boolean dW(Context paramContext)
  {
    return Boolean.valueOf(m.ip(paramContext).getBoolean(ciD, ciE));
  }
  
  public static Boolean dX(Context paramContext)
  {
    return Boolean.valueOf(m.ip(paramContext).getBoolean(ciF, ciG));
  }
  
  public static String dY(Context paramContext)
  {
    return m.ip(paramContext).getString(ciL, ciM);
  }
  
  public static int dZ(Context paramContext)
  {
    return Integer.parseInt(m.ip(paramContext).getString(ciL, ciM));
  }
  
  public static boolean da(Context paramContext, String paramString)
  {
    return false;
  }
  
  public static String db(Context paramContext, String paramString)
  {
    String[] arrayOfString1 = paramContext.getResources().getStringArray(2131492883);
    String[] arrayOfString2 = paramContext.getResources().getStringArray(2131492975);
    String str = paramString;
    if (paramString == null) {
      str = hF(paramContext);
    }
    int i = 0;
    while (i < arrayOfString2.length)
    {
      if (str.equals(arrayOfString2[i])) {
        return arrayOfString1[i];
      }
      i += 1;
    }
    return "";
  }
  
  public static String dc(Context paramContext, String paramString)
  {
    String[] arrayOfString1 = paramContext.getResources().getStringArray(2131492881);
    String[] arrayOfString2 = paramContext.getResources().getStringArray(2131492973);
    String str = paramString;
    if (paramString == null) {
      str = hJ(paramContext);
    }
    int i = 0;
    while (i < arrayOfString2.length)
    {
      if (str.equals(arrayOfString2[i])) {
        return arrayOfString1[i];
      }
      i += 1;
    }
    return "";
  }
  
  public static boolean dg(Context paramContext)
  {
    return m.ip(paramContext).getBoolean("auto_convert_mms", bZy.booleanValue());
  }
  
  public static boolean dh(Context paramContext)
  {
    return "sent".equalsIgnoreCase(m.ip(paramContext).getString("pref_sent_notification", "disable"));
  }
  
  public static boolean di(Context paramContext)
  {
    return "received".equalsIgnoreCase(m.ip(paramContext).getString("pref_sent_notification", "disable"));
  }
  
  public static boolean dj(Context paramContext)
  {
    return m.ip(paramContext).getBoolean("pref_plugin_notice", true);
  }
  
  public static String dk(Context paramContext)
  {
    return m.ip(paramContext).getString("pref_sent_notif_ringtone", "content://settings/system/notification_sound");
  }
  
  public static String dl(Context paramContext)
  {
    return m.ip(paramContext).getString("pref_sent_noti_vibrate_type", "1");
  }
  
  public static String dm(Context paramContext)
  {
    return m.ip(paramContext).getString("pref_sent_vibrate_pattern_custom", cgN);
  }
  
  public static String dn(Context paramContext)
  {
    return m.ip(paramContext).getString("pref_sent_notif_vibrate_pattern", "default");
  }
  
  public static int jdMethod_do(Context paramContext)
  {
    paramContext = m.ip(paramContext);
    SharedPreferences.Editor localEditor;
    if (paramContext.contains("pref_split_160"))
    {
      boolean bool = paramContext.getBoolean("pref_split_160", false);
      localEditor = paramContext.edit();
      if (!bool) {
        break label88;
      }
      localEditor.putString("pref_split160_ex", "1");
    }
    for (;;)
    {
      localEditor.remove("pref_split_160");
      localEditor.commit();
      return Integer.valueOf(paramContext.getString("pref_split160_ex", "0")).intValue();
      label88:
      localEditor.putString("pref_split160_ex", "0");
    }
  }
  
  public static boolean dp(Context paramContext)
  {
    return m.m(bYH, paramContext);
  }
  
  public static boolean dq(Context paramContext)
  {
    return (m.Sy()) || (m.iC(paramContext));
  }
  
  public static boolean dr(Context paramContext)
  {
    return "us".equalsIgnoreCase(m.iP(paramContext));
  }
  
  public static String ds(Context paramContext)
  {
    if (m.js(paramContext)) {
      return "id";
    }
    return "dt";
  }
  
  public static boolean dt(Context paramContext)
  {
    return m.ip(paramContext).getBoolean("enable_show_earier", true);
  }
  
  public static boolean du(Context paramContext)
  {
    return m.ip(paramContext).getBoolean("bubble_transposition", false);
  }
  
  public static Boolean dv(Context paramContext)
  {
    return Boolean.valueOf(m.ip(paramContext).getBoolean("notification_screenon", cgS.booleanValue()));
  }
  
  public static int dw(Context paramContext)
  {
    return Integer.valueOf(m.ip(paramContext).getString("autoreset_pattern_custom", "0")).intValue();
  }
  
  public static Boolean dx(Context paramContext)
  {
    return Boolean.valueOf(m.ip(paramContext).getBoolean("enabled", cho.booleanValue()));
  }
  
  public static Boolean dy(Context paramContext)
  {
    return Boolean.valueOf(m.ip(paramContext).getBoolean("popup_disable_keyguard", chE.booleanValue()));
  }
  
  public static Boolean dz(Context paramContext)
  {
    return Boolean.valueOf(false);
  }
  
  public static int e(Context paramContext, String paramString, int paramInt)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences.getInt("pkey_send_end_color", paramInt);
    }
    paramContext = m.C(paramContext, "pkey_send_end_color", paramString);
    return localSharedPreferences.getInt("pkey_send_end_color_" + paramContext, localSharedPreferences.getInt("pkey_send_end_color", paramInt));
  }
  
  public static void e(long paramLong, String paramString)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put(ac.bQZ, Long.valueOf(paramLong));
    localContentValues.put(ac.bRa, paramString);
    localContentValues.put(ac.TIMESTAMP, Long.valueOf(System.currentTimeMillis()));
    SqliteWrapper.insert(MmsApp.getContext(), MmsApp.getContext().getContentResolver(), ae.bTb, localContentValues);
  }
  
  public static void e(Context paramContext, String paramString, boolean paramBoolean)
  {
    SharedPreferences.Editor localEditor = m.ip(paramContext).edit();
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      localEditor.putBoolean(ckl, paramBoolean);
    }
    for (;;)
    {
      localEditor.commit();
      return;
      paramContext = m.C(paramContext, ckl, paramString);
      localEditor.putBoolean(ckl + "_" + paramContext, paramBoolean);
    }
  }
  
  public static boolean eA(Context paramContext)
  {
    return m.dd(paramContext, "privacy_" + eY(paramContext)).getBoolean("priv_use_lock", false);
  }
  
  public static String eB(Context paramContext)
  {
    return m.dd(paramContext, "privacy_" + eY(paramContext)).getString("priv_num_pin", "");
  }
  
  public static void eC(Context paramContext)
  {
    paramContext = m.dd(paramContext, "privacy_" + eY(paramContext)).edit();
    paramContext.remove("priv_num_pin");
    paramContext.commit();
  }
  
  public static boolean eD(Context paramContext)
  {
    paramContext = m.dd(paramContext, "privacy_" + eY(paramContext)).getString("priv_num_pin", null);
    if (paramContext == null) {}
    while ("".equals(paramContext)) {
      return false;
    }
    return true;
  }
  
  public static int eE(Context paramContext)
  {
    paramContext = m.dd(paramContext, "privacy_" + eY(paramContext)).getString("priv_lock_type", cjq);
    if (paramContext.equalsIgnoreCase(cjq)) {}
    do
    {
      return 0;
      if (paramContext.equalsIgnoreCase(cjr)) {
        return 1;
      }
      if (paramContext.equalsIgnoreCase(cjs)) {
        return 2;
      }
    } while (!paramContext.equalsIgnoreCase(cjt));
    return 3;
  }
  
  public static String eF(Context paramContext)
  {
    String str = m.dd(paramContext, "privacy_" + eY(paramContext)).getString("priv_lock_type", cjq);
    paramContext = paramContext.getResources().getStringArray(2131493028);
    if (str.equalsIgnoreCase(cjq)) {
      return paramContext[0];
    }
    if (str.equalsIgnoreCase(cjr)) {
      return paramContext[1];
    }
    if (str.equalsIgnoreCase(cjs)) {
      return paramContext[2];
    }
    if (str.equalsIgnoreCase(cjt)) {
      return paramContext[3];
    }
    return paramContext[0];
  }
  
  public static int eG(Context paramContext)
  {
    return m.dd(paramContext, "privacy_" + eY(paramContext)).getInt("priv_auto_backup_hour", 2);
  }
  
  public static int eH(Context paramContext)
  {
    return m.dd(paramContext, "privacy_" + eY(paramContext)).getInt("priv_auto_backup_minute", 0);
  }
  
  public static String eI(Context paramContext)
  {
    Date localDate = new Date();
    localDate.setHours(eG(paramContext));
    localDate.setMinutes(eH(paramContext));
    return new SimpleDateFormat("HH:mm").format(localDate);
  }
  
  public static boolean eJ(Context paramContext)
  {
    return m.dd(paramContext, "privacy_" + eY(paramContext)).getBoolean("priv_backup_ext_sdcard", true);
  }
  
  public static String eK(Context paramContext)
  {
    String str = m.dd(paramContext, "privacy_" + eY(paramContext)).getString("priv_auto_backup_valid_date", "3");
    if (str.equals("1")) {
      return str + " " + paramContext.getString(2131165885);
    }
    return str + " " + paramContext.getString(2131165886);
  }
  
  public static String eL(Context paramContext)
  {
    return m.dd(paramContext, "privacy_" + eY(paramContext)).getString("priv_auto_backup_valid_date", "3");
  }
  
  public static long eM(Context paramContext)
  {
    return m.dd(paramContext, "privacy_" + eY(paramContext)).getLong("priv_lock_deadline", 0L);
  }
  
  public static List<com.handcent.sms.ui.c.a> eN(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    String str = eY(paramContext);
    if (TextUtils.isEmpty(str)) {
      return null;
    }
    File[] arrayOfFile = m.an(m.Tj() + hcautz.getInstance().a1("71FFA09CEF97FC60B44D839E31BF62031C665E98AE63BE13"), "hcprivacy-" + str + "-");
    int i;
    int j;
    long l;
    if ((arrayOfFile != null) && (arrayOfFile.length > 0))
    {
      localArrayList.add(new com.handcent.sms.ui.c.a(paramContext.getString(2131167618), false, false));
      Arrays.sort(arrayOfFile, new k());
      i = 0;
      while (i < arrayOfFile.length) {
        if (arrayOfFile[i].isDirectory())
        {
          i += 1;
        }
        else
        {
          localObject = arrayOfFile[i].getName().replaceAll("hcprivacy-" + str + "-", "");
          j = ((String)localObject).lastIndexOf(".");
          if (j > 0) {
            localObject = ((String)localObject).substring(0, j);
          }
          for (;;)
          {
            l = arrayOfFile[i].lastModified();
            localObject = new com.handcent.sms.ui.c.a((String)localObject, true, false);
            ((com.handcent.sms.ui.c.a)localObject).setTimestamp(l);
            localArrayList.add(localObject);
            break;
            dd.q("", "pbox backup file have no ext name'");
          }
        }
      }
    }
    Object localObject = m.an(m.Tk() + hcautz.getInstance().a1("71FFA09CEF97FC60B44D839E31BF62031C665E98AE63BE13"), "hcprivacy-" + str + "-");
    if ((localObject != null) && (localObject.length > 0))
    {
      localArrayList.add(new com.handcent.sms.ui.c.a(paramContext.getString(2131165722), false, true));
      Arrays.sort((Object[])localObject, new k());
      i = 0;
      while (i < localObject.length) {
        if (localObject[i].isDirectory())
        {
          i += 1;
        }
        else
        {
          paramContext = localObject[i].getName().replaceAll("hcprivacy-" + str + "-", "");
          j = paramContext.lastIndexOf(".");
          if (j > 0) {
            paramContext = paramContext.substring(0, j);
          }
          for (;;)
          {
            l = localObject[i].lastModified();
            paramContext = new com.handcent.sms.ui.c.a(paramContext, true, true);
            paramContext.setTimestamp(l);
            localArrayList.add(paramContext);
            break;
            dd.q("", "pbox backup file have no ext name'");
          }
        }
      }
    }
    return localArrayList;
  }
  
  public static Boolean eO(Context paramContext)
  {
    return Boolean.valueOf(m.ip(paramContext).getBoolean("pref_show_threadmessages_counter", true));
  }
  
  public static String eP(Context paramContext)
  {
    return m.ip(paramContext).getString(ckj, ckk);
  }
  
  public static int eQ(Context paramContext)
  {
    String[] arrayOfString = paramContext.getResources().getStringArray(2131492987);
    paramContext = eP(paramContext);
    int i = 0;
    while (i < arrayOfString.length)
    {
      if (paramContext.equals(arrayOfString[i])) {
        return i;
      }
      i += 1;
    }
    return -1;
  }
  
  public static Boolean eR(Context paramContext)
  {
    return Boolean.valueOf(m.ip(paramContext).getBoolean("widget_show_name", ckv.booleanValue()));
  }
  
  public static int eS(Context paramContext)
  {
    return Integer.parseInt(m.ip(paramContext).getString("maximum_number_of_messages", "20"));
  }
  
  public static boolean eT(Context paramContext)
  {
    return m.ip(paramContext).getBoolean("widget_auto_lock", ckz.booleanValue());
  }
  
  public static String eU(Context paramContext)
  {
    return m.ip(paramContext).getString("widget_show_mode", "1");
  }
  
  public static String eV(Context paramContext)
  {
    return J(paramContext, true);
  }
  
  public static String eW(Context paramContext)
  {
    return hcautz.getInstance().getEncryptPassword(m.jm(paramContext), m.jn(paramContext));
  }
  
  public static String eX(Context paramContext)
  {
    return m.ip(paramContext).getString("hc_account_name", "");
  }
  
  public static String eY(Context paramContext)
  {
    return m.ip(paramContext).getString("hc_account_name", "");
  }
  
  public static String eZ(Context paramContext)
  {
    return m.ip(paramContext).getString("hc_mac", "");
  }
  
  public static Boolean ea(Context paramContext)
  {
    return Boolean.valueOf(m.ip(paramContext).getBoolean(ciN, ciO));
  }
  
  public static String eb(Context paramContext)
  {
    return m.ip(paramContext).getString(ciR, "");
  }
  
  public static boolean ec(Context paramContext)
  {
    return !ciS.equalsIgnoreCase(eb(paramContext));
  }
  
  public static void ed(Context paramContext)
  {
    paramContext = m.ip(paramContext).edit();
    paramContext.remove(ciR);
    paramContext.commit();
  }
  
  public static String ee(Context paramContext)
  {
    return m.ip(paramContext).getString("local_channelid", cja);
  }
  
  public static String ef(Context paramContext)
  {
    return m.ip(paramContext).getString("local_keywords", cjb);
  }
  
  public static Long eg(Context paramContext)
  {
    return Long.valueOf(m.ip(paramContext).getLong("local_last_updatetime", cjc.longValue()));
  }
  
  public static Boolean eh(Context paramContext)
  {
    return Boolean.valueOf(m.ip(paramContext).getBoolean(cjd, cje.booleanValue()));
  }
  
  public static int ei(Context paramContext)
  {
    return m.ip(paramContext).getInt(cjf, cjg);
  }
  
  public static String ej(Context paramContext)
  {
    return m.ip(paramContext).getString(cjh, cji);
  }
  
  public static boolean ek(Context paramContext)
  {
    return m.ip(paramContext).getBoolean("pref_social_network_show_contact", cfk.booleanValue());
  }
  
  public static String el(Context paramContext)
  {
    paramContext = m.ip(paramContext).getString("pref_save_directory", "download");
    return bXv + paramContext + "/";
  }
  
  public static void em(Context paramContext)
  {
    paramContext = m.ip(paramContext).edit();
    paramContext.remove(cjn);
    paramContext.commit();
  }
  
  public static boolean en(Context paramContext)
  {
    paramContext = m.ip(paramContext).getString(cjn, null);
    if (paramContext == null) {}
    while ("".equals(paramContext)) {
      return false;
    }
    return true;
  }
  
  public static int eo(Context paramContext)
  {
    paramContext = m.ip(paramContext).getString(cjj, cjq);
    if (paramContext.equalsIgnoreCase(cjq)) {}
    do
    {
      return 0;
      if (paramContext.equalsIgnoreCase(cjr)) {
        return 1;
      }
    } while (!paramContext.equalsIgnoreCase(cjs));
    return 2;
  }
  
  public static boolean ep(Context paramContext)
  {
    int i = eo(paramContext);
    return (i == 1) || (i == 2);
  }
  
  public static String eq(Context paramContext)
  {
    String str = m.ip(paramContext).getString(cjj, cjq);
    paramContext = paramContext.getResources().getStringArray(2131493028);
    if (str.equalsIgnoreCase(cjq)) {
      return paramContext[0];
    }
    if (str.equalsIgnoreCase(cjr)) {
      return paramContext[1];
    }
    if (str.equalsIgnoreCase(cjs)) {
      return paramContext[2];
    }
    if (str.equalsIgnoreCase(cjt)) {
      return paramContext[3];
    }
    return paramContext[0];
  }
  
  public static String er(Context paramContext)
  {
    return m.ip(paramContext).getString(cjm, cjo);
  }
  
  public static boolean es(Context paramContext)
  {
    return m.dd(paramContext, "privacy_" + eY(paramContext)).getBoolean("priv_move_return", false);
  }
  
  public static void et(Context paramContext)
  {
    m.dd(paramContext, "privacy_" + eY(paramContext)).edit().remove("priv_move_return").commit();
  }
  
  public static boolean eu(Context paramContext)
  {
    return false;
  }
  
  public static boolean ev(Context paramContext)
  {
    return false;
  }
  
  public static boolean ew(Context paramContext)
  {
    return (eu(paramContext)) && (ex(paramContext));
  }
  
  public static boolean ex(Context paramContext)
  {
    return m.dd(paramContext, "privacy_" + eY(paramContext)).getBoolean("priv_sync_init", true);
  }
  
  public static boolean ey(Context paramContext)
  {
    return m.dd(paramContext, "privacy_" + eY(paramContext)).getBoolean("priv_data_upgrade", false);
  }
  
  public static boolean ez(Context paramContext)
  {
    return m.dd(paramContext, "privacy_" + eY(paramContext)).getBoolean("priv_guide_completed", false);
  }
  
  public static int f(Context paramContext, String paramString, int paramInt)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences.getInt("pkey_rec_font_color", paramInt);
    }
    paramContext = m.C(paramContext, "pkey_rec_font_color", paramString);
    return localSharedPreferences.getInt("pkey_rec_font_color_" + paramContext, localSharedPreferences.getInt("pkey_rec_font_color", paramInt));
  }
  
  public static void f(Context paramContext, String paramString, boolean paramBoolean)
  {
    SharedPreferences.Editor localEditor = m.ip(paramContext).edit();
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      localEditor.putBoolean(cnJ, paramBoolean);
    }
    for (;;)
    {
      localEditor.commit();
      return;
      paramContext = m.C(paramContext, cnJ, paramString);
      localEditor.putBoolean(cnJ + "_" + paramContext, paramBoolean);
    }
  }
  
  public static String fA(Context paramContext)
  {
    return m.gp(paramContext.getString(2131167031));
  }
  
  public static boolean fB(Context paramContext)
  {
    return m.dd(paramContext, "privacy_" + eY(paramContext)).getBoolean(clP, clV.booleanValue());
  }
  
  public static boolean fC(Context paramContext)
  {
    return m.dd(paramContext, "privacy_" + eY(paramContext)).getBoolean(clQ, clW.booleanValue());
  }
  
  public static boolean fD(Context paramContext)
  {
    return m.dd(paramContext, "privacy_" + eY(paramContext)).getBoolean("notif_repeat", cgF.booleanValue());
  }
  
  public static int fE(Context paramContext)
  {
    return Integer.parseInt(m.dd(paramContext, "privacy_" + eY(paramContext)).getString("notif_repeat_interval", "5"));
  }
  
  public static int fF(Context paramContext)
  {
    return Integer.parseInt(m.dd(paramContext, "privacy_" + eY(paramContext)).getString("notif_repeat_num", "2"));
  }
  
  public static boolean fG(Context paramContext)
  {
    return m.dd(paramContext, "privacy_" + eY(paramContext)).getBoolean("notif_repeat_screen_on", cgI.booleanValue());
  }
  
  public static boolean fH(Context paramContext)
  {
    return m.dd(paramContext, "privacy_" + eY(paramContext)).getBoolean("pref_remind_different_sound", false);
  }
  
  public static String fI(Context paramContext)
  {
    return m.dd(paramContext, "privacy_" + eY(paramContext)).getString("pref_sent_notification", "disable");
  }
  
  public static String fJ(Context paramContext)
  {
    return m.dd(paramContext, "privacy_" + eY(paramContext)).getString("pref_sent_notif_vibrate_pattern", "default");
  }
  
  public static boolean fK(Context paramContext)
  {
    return "sent".equalsIgnoreCase(m.dd(paramContext, "privacy_" + eY(paramContext)).getString("pref_sent_notification", "disable"));
  }
  
  public static boolean fL(Context paramContext)
  {
    return "received".equalsIgnoreCase(m.dd(paramContext, "privacy_" + eY(paramContext)).getString("pref_sent_notification", "disable"));
  }
  
  public static String fM(Context paramContext)
  {
    return m.dd(paramContext, "privacy_" + eY(paramContext)).getString("pref_sent_notif_ringtone", "content://settings/system/notification_sound");
  }
  
  public static String fN(Context paramContext)
  {
    return m.dd(paramContext, "privacy_" + eY(paramContext)).getString("pref_sent_noti_vibrate_type", "1");
  }
  
  public static String fO(Context paramContext)
  {
    return m.dd(paramContext, "privacy_" + eY(paramContext)).getString("pref_sent_vibrate_pattern_custom", cgN);
  }
  
  public static boolean fP(Context paramContext)
  {
    return m.dd(paramContext, "privacy_" + eY(paramContext)).getBoolean("pref_sent_show_notif", false);
  }
  
  public static String fQ(Context paramContext)
  {
    return m.dd(paramContext, "privacy_" + eY(paramContext)).getString("pref_remind_sound", "content://settings/system/notification_sound");
  }
  
  public static boolean fR(Context paramContext)
  {
    return m.dd(paramContext, "privacy_" + eY(paramContext)).getBoolean(cmm, cmo.booleanValue());
  }
  
  public static boolean fS(Context paramContext)
  {
    return m.ip(paramContext).getBoolean("popup_over_lock_fix1", chn.booleanValue());
  }
  
  public static boolean fT(Context paramContext)
  {
    return m.dd(paramContext, "privacy_" + eY(paramContext)).getBoolean(cmn, cmp.booleanValue());
  }
  
  public static boolean fU(Context paramContext)
  {
    return m.ip(paramContext).getBoolean(cmu, cmA.booleanValue());
  }
  
  public static String fV(Context paramContext)
  {
    return m.ip(paramContext).getString(cmv, cmB);
  }
  
  public static String fW(Context paramContext)
  {
    return m.ip(paramContext).getString(cmw, cmC);
  }
  
  public static String fX(Context paramContext)
  {
    return m.ip(paramContext).getString(cmx, cmD);
  }
  
  public static String fY(Context paramContext)
  {
    return m.ip(paramContext).getString(cmy, cmE);
  }
  
  public static boolean fZ(Context paramContext)
  {
    return m.ip(paramContext).getBoolean(cmz, cmF.booleanValue());
  }
  
  public static String fa(Context paramContext)
  {
    String[] arrayOfString = m.ip(paramContext).getString("hc_promotion_info", paramContext.getString(2131167574)).split("\\^");
    if ((arrayOfString == null) || (arrayOfString.length == 0)) {
      return paramContext.getString(2131167574);
    }
    if (arrayOfString.length == 1) {
      return arrayOfString[0];
    }
    dd.d("", "arr length:" + arrayOfString.length);
    int i = new Random().nextInt(arrayOfString.length - 1);
    dd.d("", "random num:" + i);
    dd.d("", "urlinfo:" + arrayOfString[i]);
    return arrayOfString[i];
  }
  
  public static String fb(Context paramContext)
  {
    return m.ip(paramContext).getString("hc_promotion_url", coO);
  }
  
  public static long fc(Context paramContext)
  {
    return m.ip(paramContext).getLong("hc_promotion_time", cla);
  }
  
  public static String fd(Context paramContext)
  {
    if (cll == null) {
      cll = m.ip(paramContext).getString("skin_type", clh);
    }
    return cll;
  }
  
  public static String fe(Context paramContext)
  {
    if (mPackageName == null) {
      mPackageName = m.ip(paramContext).getString(clm, clh);
    }
    return mPackageName;
  }
  
  public static String ff(Context paramContext)
  {
    return ci(paramContext, null);
  }
  
  public static boolean fg(Context paramContext)
  {
    return m.ip(paramContext).getBoolean(clp, false);
  }
  
  public static String fh(Context paramContext)
  {
    return m.ip(paramContext).getString(clq, clx);
  }
  
  public static int fi(Context paramContext)
  {
    String str = fh(paramContext);
    paramContext = paramContext.getResources().getStringArray(2131493051);
    if ((paramContext != null) && (paramContext.length > 0))
    {
      int i = 0;
      while (i < paramContext.length)
      {
        if (str.equalsIgnoreCase(paramContext[i])) {
          return i;
        }
        i += 1;
      }
    }
    return 0;
  }
  
  public static boolean fj(Context paramContext)
  {
    Object localObject = fh(paramContext);
    if ("night".equalsIgnoreCase((String)localObject)) {}
    Date localDate;
    do
    {
      return true;
      if (!"smart".equalsIgnoreCase((String)localObject)) {
        break;
      }
      localObject = new Date();
      int i = fl(paramContext);
      int j = fn(paramContext);
      int k = fm(paramContext);
      int m = fp(paramContext);
      paramContext = new Date();
      paramContext.setHours(i);
      paramContext.setMinutes(j);
      paramContext.setSeconds(0);
      localDate = new Date();
      localDate.setHours(k);
      localDate.setMinutes(m);
      localDate.setSeconds(0);
      if (localDate.before(paramContext)) {
        localDate.setDate(localDate.getDate() + 1);
      }
    } while ((((Date)localObject).after(paramContext)) && (((Date)localObject).before(localDate)));
    return false;
  }
  
  public static int fk(Context paramContext)
  {
    return m.ip(paramContext).getInt(clr, clw);
  }
  
  public static int fl(Context paramContext)
  {
    return m.ip(paramContext).getInt(cls, cly);
  }
  
  public static int fm(Context paramContext)
  {
    return m.ip(paramContext).getInt(clt, clA);
  }
  
  public static int fn(Context paramContext)
  {
    return m.ip(paramContext).getInt(clu, clz);
  }
  
  public static String fo(Context paramContext)
  {
    Date localDate = new Date();
    localDate.setHours(fl(paramContext));
    localDate.setMinutes(fn(paramContext));
    return new SimpleDateFormat("HH:mm").format(localDate);
  }
  
  public static int fp(Context paramContext)
  {
    return m.ip(paramContext).getInt(clv, clB);
  }
  
  public static String fq(Context paramContext)
  {
    Date localDate = new Date();
    localDate.setHours(fm(paramContext));
    localDate.setMinutes(fp(paramContext));
    return new SimpleDateFormat("HH:mm").format(localDate);
  }
  
  public static Calendar fr(Context paramContext)
  {
    return gf(m.ip(paramContext).getString(clD, clI));
  }
  
  public static String fs(Context paramContext)
  {
    return m.ip(paramContext).getString(clD, clI);
  }
  
  public static Calendar ft(Context paramContext)
  {
    return gf(m.ip(paramContext).getString(clE, clJ));
  }
  
  public static String fu(Context paramContext)
  {
    return m.ip(paramContext).getString(clE, clJ);
  }
  
  public static String fv(Context paramContext)
  {
    return m.ip(paramContext).getString(clC, clF);
  }
  
  public static boolean fw(Context paramContext)
  {
    paramContext = fv(paramContext);
    return !clG.equals(paramContext);
  }
  
  public static Boolean fx(Context paramContext)
  {
    return Boolean.valueOf(m.dd(paramContext, "privacy_" + eY(paramContext)).getBoolean("notification_screenon", cgS.booleanValue()));
  }
  
  public static boolean fy(Context paramContext)
  {
    return m.dd(paramContext, "privacy_" + eY(paramContext)).getBoolean(clO, clU.booleanValue());
  }
  
  public static String fz(Context paramContext)
  {
    return m.gp(paramContext.getString(2131167032));
  }
  
  public static int g(Context paramContext, String paramString, int paramInt)
  {
    SharedPreferences localSharedPreferences = m.ip(paramContext);
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      return localSharedPreferences.getInt("pkey_send_font_color", paramInt);
    }
    paramContext = m.C(paramContext, "pkey_send_font_color", paramString);
    return localSharedPreferences.getInt("pkey_send_font_color_" + paramContext, localSharedPreferences.getInt("pkey_send_font_color", paramInt));
  }
  
  public static void g(Context paramContext, String paramString, boolean paramBoolean)
  {
    SharedPreferences.Editor localEditor = m.ip(paramContext).edit();
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      localEditor.putBoolean("pref_contact_picture_in_title", paramBoolean);
    }
    for (;;)
    {
      localEditor.commit();
      return;
      paramContext = m.C(paramContext, "pref_contact_picture_in_title", paramString);
      localEditor.putBoolean("pref_contact_picture_in_title_" + paramContext, paramBoolean);
    }
  }
  
  public static String gA(Context paramContext)
  {
    return m.ip(paramContext).getString("pref_popup_contact_font", cnz);
  }
  
  public static String gB(Context paramContext)
  {
    return m.ip(paramContext).getString("pref_popup_indicator_font", cnB);
  }
  
  public static String gC(Context paramContext)
  {
    return m.ip(paramContext).getString("pref_popup_datetime_font", cnA);
  }
  
  public static String gD(Context paramContext)
  {
    return m.ip(paramContext).getString("pref_popup_content_font", cnC);
  }
  
  public static String gE(Context paramContext)
  {
    return m.ip(paramContext).getString("pref_popup_reply_font", cnD);
  }
  
  public static int gF(Context paramContext)
  {
    return m.ip(paramContext).getInt("pref_popup_contact_color", m.gS("popup_contact_text_color"));
  }
  
  public static int gG(Context paramContext)
  {
    return m.ip(paramContext).getInt("pref_popup_indicator_color", m.gS("popup_indicator_text_color"));
  }
  
  public static int gH(Context paramContext)
  {
    return m.ip(paramContext).getInt("pref_popup_datetime_color", m.gS("popup_date_text_color"));
  }
  
  public static int gI(Context paramContext)
  {
    return m.ip(paramContext).getInt("pref_popup_content_color", m.gS("popup_body_text_color"));
  }
  
  public static int gJ(Context paramContext)
  {
    return m.ip(paramContext).getInt("pref_popup_reply_color", m.gS("popup_reply_editor_text_color"));
  }
  
  public static boolean gK(Context paramContext)
  {
    return "dt".equals(m.ip(paramContext).getString("pref_order_type_new", ds(paramContext)));
  }
  
  public static boolean gL(Context paramContext)
  {
    return (m.Sd()) && (m.Tt());
  }
  
  public static boolean gM(Context paramContext)
  {
    return m.ip(paramContext).getBoolean("pref_conversation_splitline_enabled", true);
  }
  
  public static boolean gN(Context paramContext)
  {
    return true;
  }
  
  public static void gO(Context paramContext)
  {
    paramContext = m.ip(paramContext).edit();
    paramContext.remove("pref_hc_groups");
    paramContext.commit();
  }
  
  public static Long gP(Context paramContext)
  {
    return Long.valueOf(m.ip(paramContext).getLong("local_last_notify_events_updatetime", cov.longValue()));
  }
  
  public static boolean gQ(Context paramContext)
  {
    return m.ip(paramContext).getBoolean(cow, false);
  }
  
  public static String gR(Context paramContext)
  {
    return m.ip(paramContext).getString("pref_swith_effects", "3");
  }
  
  public static boolean gS(Context paramContext)
  {
    return m.ip(paramContext).getBoolean("pref_key_convlist_use_skin_bg", cob.booleanValue());
  }
  
  public static void gT(Context paramContext)
  {
    paramContext = m.ip(paramContext).edit();
    paramContext.remove(cil);
    paramContext.remove(cim);
    paramContext.remove(cin);
    paramContext.remove(cio);
    paramContext.remove("pref_show_threadmessages_counter");
    paramContext.remove(ciq);
    paramContext.remove(cip);
    paramContext.remove(ckj);
    paramContext.remove("pref_convlistbkg_mode");
    paramContext.remove("pkey_theme_style");
    paramContext.remove("pkey_rec_bubble_color");
    paramContext.remove("pkey_send_bubble_color");
    paramContext.remove("pkey_full_editor_font_color");
    paramContext.remove("pkey_rec_font_color");
    paramContext.remove("pkey_send_font_color");
    paramContext.remove("pkey_datetime_font_color");
    paramContext.remove("pref_key_usepic");
    paramContext.remove("pref_key_use_contact_pic");
    paramContext.remove("pref_key_use_themecolor");
    paramContext.remove("pref_key_background_color");
    paramContext.remove(ckq);
    paramContext.remove("pkey_editbox_font_color");
    paramContext.remove("pref_conversation_contact_font_color");
    paramContext.remove("pref_conversation_numbers_font_color");
    paramContext.remove(cmI);
    paramContext.remove("pref_composebkg_mode");
    paramContext.remove("pref_colorful_bubble_type");
    paramContext.remove("pref_contact_picture_in_title");
    paramContext.remove("pref_popup_contact_color");
    paramContext.remove("pref_popup_indicator_color");
    paramContext.remove("pref_popup_datetime_color");
    paramContext.remove("pref_popup_content_color");
    paramContext.remove("pref_popup_reply_color");
    paramContext.remove("pref_key_popup_usepic");
    paramContext.remove("pref_key_popup_use_skin_bg");
    paramContext.remove("pref_key_popup_background_color");
    paramContext.remove("pref_popup_textlink_color");
    paramContext.commit();
  }
  
  public static boolean gU(Context paramContext)
  {
    return "rtime".equalsIgnoreCase(m.ip(paramContext).getString("task_order_type", "rtime"));
  }
  
  public static Boolean gV(Context paramContext)
  {
    return Boolean.valueOf(false);
  }
  
  public static boolean gW(Context paramContext)
  {
    return m.ip(paramContext).getBoolean("pref_key_mms_auto_retrieval", RF());
  }
  
  public static boolean gX(Context paramContext)
  {
    return true;
  }
  
  public static boolean gY(Context paramContext)
  {
    return m.ip(paramContext).getBoolean("run_save_old_skin_config_v60", false);
  }
  
  public static boolean gZ(Context paramContext)
  {
    return true;
  }
  
  public static String ga(Context paramContext)
  {
    return m.ip(paramContext).getString(cmI, cmJ);
  }
  
  public static int gb(Context paramContext)
  {
    return Integer.parseInt(m.ip(paramContext).getString(cmI, cmJ));
  }
  
  public static Boolean gc(Context paramContext)
  {
    return Boolean.valueOf(false);
  }
  
  public static int gd(String paramString)
  {
    int i = Integer.parseInt(paramString);
    return ciJ[i];
  }
  
  public static Boolean gd(Context paramContext)
  {
    return Boolean.valueOf(m.dd(paramContext, "privacy_" + eY(paramContext)).getBoolean("pkey_show_moveto_pbox_toast", cmO.booleanValue()));
  }
  
  public static Boolean ge(Context paramContext)
  {
    return Boolean.valueOf(m.ip(paramContext).getBoolean("pkey_schedule_auto_delete", cmQ.booleanValue()));
  }
  
  public static Boolean ge(String paramString)
  {
    return Boolean.valueOf("themecolor".equalsIgnoreCase(paramString));
  }
  
  public static Boolean gf(Context paramContext)
  {
    return Boolean.valueOf(m.ip(paramContext).getBoolean("pkey_hcmms_auto_download", cmS.booleanValue()));
  }
  
  public static Calendar gf(String paramString)
  {
    paramString = paramString.split(":");
    int i = Integer.parseInt(paramString[0]);
    int j = Integer.parseInt(paramString[1]);
    paramString = Calendar.getInstance();
    paramString.set(11, i);
    paramString.set(12, j);
    paramString.set(13, 0);
    return paramString;
  }
  
  public static int gg(String paramString)
  {
    int i = Integer.parseInt(paramString);
    return cmG[i];
  }
  
  public static Boolean gg(Context paramContext)
  {
    return Boolean.valueOf(false);
  }
  
  public static int gh(String paramString)
  {
    int i = Integer.parseInt(paramString);
    return coX[i];
  }
  
  public static Boolean gh(Context paramContext)
  {
    return Boolean.valueOf(m.ip(paramContext).getBoolean(cmV, cmW.booleanValue()));
  }
  
  public static boolean gi(Context paramContext)
  {
    if (m.Sx()) {
      return false;
    }
    paramContext = m.ip(paramContext).getString("pkey_default_application", Rw());
    if (m.Sq()) {
      paramContext = "all";
    }
    return (paramContext.equalsIgnoreCase("sms")) || (paramContext.equalsIgnoreCase("all"));
  }
  
  public static boolean gj(Context paramContext)
  {
    boolean bool = false;
    if ("smart".equalsIgnoreCase(m.go(m.ip(paramContext).getString("pref_mms_carrier", "smart")).split(",,,")[0])) {
      bool = true;
    }
    return bool;
  }
  
  public static boolean gk(Context paramContext)
  {
    boolean bool = false;
    if ("list".equalsIgnoreCase(m.go(m.ip(paramContext).getString("pref_mms_carrier", "smart")).split(",,,")[0])) {
      bool = true;
    }
    return bool;
  }
  
  public static boolean gl(Context paramContext)
  {
    boolean bool = false;
    if ("manual".equalsIgnoreCase(m.go(m.ip(paramContext).getString("pref_mms_carrier", "smart")).split(",,,")[0])) {
      bool = true;
    }
    return bool;
  }
  
  public static com.handcent.sms.model.a gm(Context paramContext)
  {
    for (;;)
    {
      try
      {
        Object localObject = m.ip(paramContext);
        paramContext = ((SharedPreferences)localObject).getString("pref_mms_mmsc", "");
        String str = ((SharedPreferences)localObject).getString("pref_mms_proxy", "");
        localObject = ((SharedPreferences)localObject).getString("pref_mms_proxy_port", "80");
        if (!bx.ps((String)localObject))
        {
          i = Integer.valueOf((String)localObject).intValue();
          paramContext = new com.handcent.sms.model.a(paramContext, str, i);
          return paramContext;
        }
      }
      catch (Exception paramContext)
      {
        return null;
      }
      int i = -1;
    }
  }
  
  public static com.handcent.sms.model.a gn(Context paramContext)
  {
    try
    {
      paramContext = m.go(m.ip(paramContext).getString("pref_mms_carrier", "smart")).split(",,,");
      if (paramContext.length > 1)
      {
        paramContext = new com.handcent.sms.model.a(paramContext[1]);
        return paramContext;
      }
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  public static boolean go(Context paramContext)
  {
    paramContext = m.ip(paramContext).getString("pkey_default_application", Rw());
    if (m.Sq()) {
      paramContext = "all";
    }
    return (paramContext.equalsIgnoreCase("mms")) || (paramContext.equalsIgnoreCase("all"));
  }
  
  public static boolean gp(Context paramContext)
  {
    return (!go(paramContext)) || (!gW(paramContext));
  }
  
  public static boolean gq(Context paramContext)
  {
    return m.ip(paramContext).getBoolean("pref_notify_after_downloaded", true);
  }
  
  public static boolean gr(Context paramContext)
  {
    return true;
  }
  
  public static Boolean gs(Context paramContext)
  {
    return Boolean.valueOf(m.ip(paramContext).getBoolean("pkey_schedule_run_notification", cnf.booleanValue()));
  }
  
  public static Boolean gt(Context paramContext)
  {
    return Boolean.valueOf(m.ip(paramContext).getBoolean(cng, true));
  }
  
  public static Boolean gu(Context paramContext)
  {
    return Boolean.valueOf(m.ip(paramContext).getBoolean(cnh, true));
  }
  
  public static Boolean gv(Context paramContext)
  {
    return Boolean.valueOf(m.ip(paramContext).getBoolean(cni, true));
  }
  
  public static Boolean gw(Context paramContext)
  {
    return Boolean.valueOf(m.ip(paramContext).getBoolean(cnj, false));
  }
  
  public static Boolean gx(Context paramContext)
  {
    return Boolean.valueOf(m.ip(paramContext).getBoolean(cnk, false));
  }
  
  public static Boolean gy(Context paramContext)
  {
    return Boolean.valueOf(m.ip(paramContext).getBoolean(cnl, cnm.booleanValue()));
  }
  
  public static int gz(Context paramContext)
  {
    return m.ip(paramContext).getInt("pref_popup_textlink_color", m.gS("popup_text_link_color"));
  }
  
  public static void h(Context paramContext, String paramString, int paramInt)
  {
    String[] arrayOfString = paramContext.getResources().getStringArray(2131493041);
    SharedPreferences.Editor localEditor = m.ip(paramContext).edit();
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      localEditor.putString("pkey_theme_style", arrayOfString[paramInt]);
    }
    for (;;)
    {
      localEditor.commit();
      return;
      paramContext = m.C(paramContext, "pkey_theme_style", paramString);
      localEditor.putString("pkey_theme_style_" + paramContext, arrayOfString[paramInt]);
    }
  }
  
  public static boolean hA(Context paramContext)
  {
    return da(paramContext, null);
  }
  
  public static boolean hB(Context paramContext)
  {
    return m.ip(paramContext).getBoolean("pref_debug_mode", ccP.booleanValue());
  }
  
  public static boolean hC(Context paramContext)
  {
    return m.ip(paramContext).getBoolean("font_enable", true);
  }
  
  public static boolean hD(Context paramContext)
  {
    return m.ip(paramContext).getBoolean("colorful_avatar", cpE.booleanValue());
  }
  
  public static int hE(Context paramContext)
  {
    return m.ip(paramContext).getInt(cpF, m.gS("conversation_list_unread_indicator_color"));
  }
  
  public static String hF(Context paramContext)
  {
    return m.ip(paramContext).getString("avatar_shape", "circle");
  }
  
  public static boolean hG(Context paramContext)
  {
    return "circle".equalsIgnoreCase(hF(paramContext));
  }
  
  public static boolean hH(Context paramContext)
  {
    if (!m.hI("avatar_shape_support")) {
      return cpN.booleanValue();
    }
    m.ip(paramContext);
    paramContext = m.gR("avatar_shape_support");
    boolean bool1 = cpN.booleanValue();
    try
    {
      boolean bool2 = Boolean.parseBoolean(paramContext);
      return bool2;
    }
    catch (Exception paramContext) {}
    return bool1;
  }
  
  public static int hI(Context paramContext)
  {
    return m.ip(paramContext).getInt(cpO, m.jc(2131167916));
  }
  
  public static String hJ(Context paramContext)
  {
    return m.ip(paramContext).getString("app_run_mode", "background");
  }
  
  public static boolean hK(Context paramContext)
  {
    return "background".equalsIgnoreCase(hJ(paramContext));
  }
  
  public static boolean hL(Context paramContext)
  {
    return m.ip(paramContext).getBoolean("pref_always_backto_main", chF.booleanValue());
  }
  
  public static boolean hM(Context paramContext)
  {
    return m.ip(paramContext).getBoolean("pref_key_old_copy", false);
  }
  
  public static void hN(Context paramContext)
  {
    m.ip(paramContext).edit().putLong("pref_key_old_uninstall", System.currentTimeMillis()).commit();
  }
  
  public static boolean hO(Context paramContext)
  {
    return System.currentTimeMillis() - hP(paramContext) > 604800000L;
  }
  
  private static long hP(Context paramContext)
  {
    return m.ip(paramContext).getLong("pref_key_old_uninstall", 0L);
  }
  
  public static SharedPreferences hQ(Context paramContext)
  {
    return m.dd(paramContext, "pref_sp_key_data_tranf");
  }
  
  public static boolean hR(Context paramContext)
  {
    return m.ip(paramContext).getBoolean("pref_key_score", false);
  }
  
  public static String hS(Context paramContext)
  {
    return m.ip(paramContext).getString("pref_key_failed_vibrate_type", "1");
  }
  
  public static Boolean hT(Context paramContext)
  {
    return Boolean.valueOf(false);
  }
  
  public static int hU(Context paramContext)
  {
    k = 3;
    if (aPY < 0) {}
    for (;;)
    {
      try
      {
        str = m.ip(paramContext).getString("pref_emoji_version", "0");
        if (!"0".equalsIgnoreCase(str)) {
          continue;
        }
        i = 4;
      }
      catch (Exception paramContext)
      {
        String str;
        int j;
        boolean bool;
        continue;
        int i = j;
        continue;
        i = 4;
        continue;
        if (j > 3) {
          continue;
        }
        i = k;
        continue;
      }
      str = ad.as(paramContext);
      j = i;
      if (paramContext.getApplicationContext().getPackageManager().getPackageInfo(str, 0).versionCode >= 200) {
        continue;
      }
      j = i;
      if (i < 4) {
        continue;
      }
      j = 3;
      continue;
      aPY = i;
      return aPY;
      if ("4".equalsIgnoreCase(str))
      {
        i = 4;
      }
      else if ("3".equalsIgnoreCase(str))
      {
        i = 3;
      }
      else if ("2".equalsIgnoreCase(str))
      {
        i = 2;
      }
      else
      {
        bool = "1".equalsIgnoreCase(str);
        if (!bool) {
          continue;
        }
        i = 1;
      }
    }
  }
  
  public static String hV(Context paramContext)
  {
    return m.ip(paramContext).getString("pref_edit_large", "edit_large_key_editshow");
  }
  
  public static boolean hW(Context paramContext)
  {
    return TextUtils.equals(hV(paramContext), "edit_large_key_editshow");
  }
  
  public static boolean hX(Context paramContext)
  {
    return TextUtils.equals(hV(paramContext), "edit_large_key_always");
  }
  
  public static boolean hY(Context paramContext)
  {
    return TextUtils.equals(hV(paramContext), "edit_large_key_none");
  }
  
  public static Boolean ha(Context paramContext)
  {
    return Boolean.valueOf(m.ip(paramContext).getString(ciQ, "ISNEWINS").equalsIgnoreCase("ISNEWINS"));
  }
  
  public static boolean hb(Context paramContext)
  {
    int i = eE(paramContext);
    return (i == 1) || (i == 2) || (i == 3);
  }
  
  public static int hc(Context paramContext)
  {
    return m.ip(paramContext).getInt(coN, m.gS("conversation_list_unread_background_color"));
  }
  
  public static boolean hd(Context paramContext)
  {
    return m.ip(paramContext).getBoolean("pkey_hidden_keyboard_after_sending", false);
  }
  
  public static boolean he(Context paramContext)
  {
    return m.ip(paramContext).getBoolean("pkey_show_sending_animation", true);
  }
  
  public static boolean hf(Context paramContext)
  {
    return m.ip(paramContext).getBoolean(coT, coU);
  }
  
  public static String hg(Context paramContext)
  {
    return m.ip(paramContext).getString(coV, coW);
  }
  
  public static boolean hh(Context paramContext)
  {
    return m.ip(paramContext).getBoolean("animation_between_windows", coZ.booleanValue());
  }
  
  public static boolean hi(Context paramContext)
  {
    return m.ip(paramContext).getBoolean("pref_using_recent_smile", true);
  }
  
  public static List<HcSkin> hj(Context paramContext)
  {
    localArrayList = new ArrayList();
    Object localObject1 = m.getContext().getPackageManager().getInstalledPackages(0);
    if ((localObject1 != null) && (((List)localObject1).size() > 0)) {
      try
      {
        Iterator localIterator = ((List)localObject1).iterator();
        while (localIterator.hasNext())
        {
          localObject1 = (PackageInfo)localIterator.next();
          Object localObject2 = ((PackageInfo)localObject1).packageName;
          if (((String)localObject2).startsWith("com.handcent.sms.skin."))
          {
            HcSkin localHcSkin = new HcSkin();
            localHcSkin.ir("");
            localHcSkin.kn("" + ((PackageInfo)localObject1).versionName);
            localHcSkin.kl("custom");
            localHcSkin.ko((String)localObject2 + "_xxx.apk");
            localHcSkin.kp((String)localObject2);
            localObject1 = "";
            String str = "";
            Resources localResources = paramContext.getPackageManager().getResourcesForApplication((String)localObject2);
            int i = localResources.getIdentifier("author_name", "string", (String)localObject2);
            if (i != 0) {
              localObject1 = localResources.getString(i);
            }
            i = localResources.getIdentifier("skin_display_name", "string", (String)localObject2);
            if (i != 0) {
              str = localResources.getString(i);
            }
            i = localResources.getIdentifier("preview", "drawable", (String)localObject2);
            if (i > 0)
            {
              localObject2 = BitmapFactory.decodeResource(localResources, i);
              if (localObject2 != null) {
                localHcSkin.ad((Bitmap)localObject2);
              }
            }
            localHcSkin.setName(str);
            localHcSkin.iu((String)localObject1);
            localArrayList.add(localHcSkin);
          }
        }
        return localArrayList;
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
        dd.d("CommonConfig", "getInstalledSkins error");
      }
    }
  }
  
  public static int hk(Context paramContext)
  {
    return m.ip(paramContext).getInt("pref_notification_id", 0);
  }
  
  public static int hl(Context paramContext)
  {
    return m.ip(paramContext).getInt("pref_event_id", 0);
  }
  
  public static boolean hm(Context paramContext)
  {
    return m.dd(paramContext, "remote_sms_" + eY(paramContext)).getBoolean("guide", cpA.booleanValue());
  }
  
  public static boolean hn(Context paramContext)
  {
    return m.ip(paramContext).getBoolean("pref_show_new_messages_counter", ccL.booleanValue());
  }
  
  public static boolean ho(Context paramContext)
  {
    return m.dd(paramContext, "main_guide").getBoolean("main_guide_10650", true);
  }
  
  public static boolean hp(Context paramContext)
  {
    return m.dd(paramContext, "remote_notice_" + eY(paramContext)).getBoolean("open", ccQ.booleanValue());
  }
  
  public static boolean hq(Context paramContext)
  {
    return m.dd(paramContext, "remote_tran").getBoolean("remote_tran", true);
  }
  
  public static boolean hr(Context paramContext)
  {
    return m.dd(paramContext, "remote_sms_" + eY(paramContext)).getBoolean("open", ccQ.booleanValue());
  }
  
  public static boolean hs(Context paramContext)
  {
    return m.dd(paramContext, "remote_sms_" + eY(paramContext)).getBoolean("sync_recent", cpy.booleanValue());
  }
  
  public static String ht(Context paramContext)
  {
    return m.dd(paramContext, "remote_sms_" + eY(paramContext)).getString("device_name", "");
  }
  
  public static boolean hu(Context paramContext)
  {
    return m.dd(paramContext, "remote_sms_" + eY(paramContext)).getBoolean("device_upload", cpz.booleanValue());
  }
  
  public static int hv(Context paramContext)
  {
    return m.dd(paramContext, "remote_sms_" + eY(paramContext)).getInt("last_control_type", -1);
  }
  
  public static String hw(Context paramContext)
  {
    return m.dd(paramContext, "remote_sms_" + eY(paramContext)).getString("last_control", "");
  }
  
  public static long hx(Context paramContext)
  {
    return m.dd(paramContext, "remote_sms_" + eY(paramContext)).getLong("last_link_long", 0L);
  }
  
  public static boolean hy(Context paramContext)
  {
    return m.dd(paramContext, "remote_sms_" + eY(paramContext)).getBoolean("link_on", cpB.booleanValue());
  }
  
  public static boolean hz(Context paramContext)
  {
    if (!m.hI("conversation_list_colorful_heads")) {
      return cpC.booleanValue();
    }
    m.ip(paramContext);
    paramContext = m.gR("conversation_list_colorful_heads");
    boolean bool1 = cpC.booleanValue();
    try
    {
      boolean bool2 = Boolean.parseBoolean(paramContext);
      return bool2;
    }
    catch (Exception paramContext) {}
    return bool1;
  }
  
  public static void i(Context paramContext, String paramString, int paramInt)
  {
    String[] arrayOfString = paramContext.getResources().getStringArray(2131493057);
    SharedPreferences.Editor localEditor = m.ip(paramContext).edit();
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      localEditor.putString("pkey_show_full_editor", arrayOfString[paramInt]);
    }
    for (;;)
    {
      localEditor.commit();
      return;
      paramContext = m.C(paramContext, "pkey_show_full_editor", paramString);
      localEditor.putString("pkey_show_full_editor_" + paramContext, arrayOfString[paramInt]);
    }
  }
  
  public static boolean iA(int paramInt)
  {
    if (Rv()) {}
    while (paramInt == m.gS("conversation_outgoing_bubble_color")) {
      return true;
    }
    return false;
  }
  
  public static int iB(int paramInt)
  {
    return ciJ[paramInt];
  }
  
  public static String iC(int paramInt)
  {
    return ciK[paramInt];
  }
  
  public static int iD(int paramInt)
  {
    if (paramInt == 1) {
      return bZm;
    }
    if (paramInt == 0) {
      return bZn;
    }
    return 0;
  }
  
  public static int iE(int paramInt)
  {
    if (paramInt == 1) {
      return bZn;
    }
    if (paramInt == 2) {
      return bZm;
    }
    return 0;
  }
  
  public static int iF(int paramInt)
  {
    if (paramInt == bZm) {
      return 1;
    }
    if (paramInt == bZn) {
      return 0;
    }
    return -1;
  }
  
  public static int iG(int paramInt)
  {
    if (paramInt == 1) {
      return bZn;
    }
    return bZm;
  }
  
  public static int iH(int paramInt)
  {
    if (paramInt == bZn) {
      return 1;
    }
    return 0;
  }
  
  public static int iI(int paramInt)
  {
    if (paramInt == bZn) {
      return 2;
    }
    return 1;
  }
  
  public static int iJ(int paramInt)
  {
    if (paramInt == 2) {
      return bZn;
    }
    return bZm;
  }
  
  public static int iK(int paramInt)
  {
    if (paramInt == bZm) {
      return 2;
    }
    if (paramInt == bZn) {
      return 1;
    }
    return 0;
  }
  
  public static int iL(int paramInt)
  {
    return cmG[paramInt];
  }
  
  public static String iM(int paramInt)
  {
    return cmH[paramInt];
  }
  
  public static int iN(int paramInt)
  {
    if (paramInt == 0) {
      return bZm;
    }
    if (paramInt == 1) {
      return bZn;
    }
    return bZm;
  }
  
  public static int iO(int paramInt)
  {
    if (paramInt == bZm) {
      return 0;
    }
    if (paramInt == bZn) {
      return 1;
    }
    return -1;
  }
  
  public static boolean iz(int paramInt)
  {
    if (Rv()) {}
    while (paramInt == m.gS("conversation_incoming_bubble_color")) {
      return true;
    }
    return false;
  }
  
  public static void j(Context paramContext, String paramString, int paramInt)
  {
    String[] arrayOfString = paramContext.getResources().getStringArray(2131492950);
    SharedPreferences.Editor localEditor = m.ip(paramContext).edit();
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      localEditor.putString("pkey_show_full_editor_method", arrayOfString[paramInt]);
    }
    for (;;)
    {
      localEditor.commit();
      return;
      paramContext = m.C(paramContext, "pkey_show_full_editor_method", paramString);
      localEditor.putString("pkey_show_full_editor_method_" + paramContext, arrayOfString[paramInt]);
    }
  }
  
  public static void k(Context paramContext, String paramString, int paramInt)
  {
    String[] arrayOfString = paramContext.getResources().getStringArray(2131492987);
    SharedPreferences.Editor localEditor = m.ip(paramContext).edit();
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      localEditor.putString(ckq, arrayOfString[paramInt]);
    }
    for (;;)
    {
      localEditor.commit();
      return;
      paramContext = m.C(paramContext, ckq, paramString);
      localEditor.putString(ckq + "_" + paramContext, arrayOfString[paramInt]);
    }
  }
  
  public static void l(Context paramContext, String paramString, int paramInt)
  {
    paramContext = m.ip(paramContext).edit();
    if (TextUtils.isEmpty(paramString)) {
      paramContext.putString(cmI, Integer.toString(paramInt));
    }
    for (;;)
    {
      paramContext.commit();
      return;
      paramContext.putString(cmI + "_" + paramString, Integer.toString(paramInt));
    }
  }
  
  public static void m(Context paramContext, String paramString, int paramInt)
  {
    String[] arrayOfString = paramContext.getResources().getStringArray(2131493017);
    SharedPreferences.Editor localEditor = m.ip(paramContext).edit();
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      localEditor.putString("pkey_message_counter_mode", arrayOfString[paramInt]);
    }
    for (;;)
    {
      localEditor.commit();
      return;
      paramContext = m.C(paramContext, "pkey_message_counter_mode", paramString);
      localEditor.putString("pkey_message_counter_mode_" + paramContext, arrayOfString[paramInt]);
    }
  }
  
  public static void n(Context paramContext, String paramString, int paramInt)
  {
    String[] arrayOfString = paramContext.getResources().getStringArray(2131492991);
    SharedPreferences.Editor localEditor = m.ip(paramContext).edit();
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      localEditor.putString("pkey_text_counter_mode", arrayOfString[paramInt]);
    }
    for (;;)
    {
      localEditor.commit();
      return;
      paramContext = m.C(paramContext, "pkey_text_counter_mode", paramString);
      localEditor.putString("pkey_text_counter_mode_" + paramContext, arrayOfString[paramInt]);
    }
  }
  
  public static void o(Context paramContext, String paramString, int paramInt)
  {
    String[] arrayOfString = new String[6];
    arrayOfString[0] = "withskin";
    arrayOfString[1] = "cbt_default";
    arrayOfString[2] = "cbt_hcclassic";
    arrayOfString[3] = "cbt_iphone";
    arrayOfString[4] = "cbt_test1";
    arrayOfString[5] = "cbt_test2";
    SharedPreferences.Editor localEditor = m.ip(paramContext).edit();
    if ((paramString == null) || ("".equalsIgnoreCase(paramString))) {
      localEditor.putString("pref_colorful_bubble_type", arrayOfString[paramInt]);
    }
    for (;;)
    {
      localEditor.commit();
      return;
      paramContext = m.C(paramContext, "pref_colorful_bubble_type", paramString);
      localEditor.putString("pref_colorful_bubble_type_" + paramContext, arrayOfString[paramInt]);
    }
  }
  
  public static void q(Context paramContext, long paramLong)
  {
    paramContext = m.ip(paramContext).edit();
    paramContext.putLong("local_last_updatetime", paramLong);
    paramContext.commit();
  }
  
  public static void r(Context paramContext, long paramLong)
  {
    paramContext = m.dd(paramContext, "privacy_" + eY(paramContext)).edit();
    paramContext.putLong("priv_lock_deadline", paramLong);
    paramContext.commit();
  }
  
  public static void s(Context paramContext, long paramLong)
  {
    paramContext = m.ip(paramContext).edit();
    paramContext.putLong("local_last_notify_events_updatetime", paramLong);
    paramContext.commit();
  }
  
  public static void t(Context paramContext, int paramInt)
  {
    paramContext = m.ip(paramContext).edit();
    paramContext.putString("autoreset_pattern_custom", String.valueOf(paramInt));
    paramContext.commit();
  }
  
  public static void t(Context paramContext, long paramLong)
  {
    paramContext = m.dd(paramContext, "remote_sms_" + eY(paramContext)).edit();
    paramContext.putLong("last_link_long", paramLong);
    paramContext.commit();
  }
  
  public static void u(Context paramContext, int paramInt)
  {
    paramContext = m.ip(paramContext).edit();
    paramContext.putInt(cjf, paramInt);
    paramContext.commit();
  }
  
  public static void v(Context paramContext, int paramInt)
  {
    paramContext = m.dd(paramContext, "privacy_" + eY(paramContext)).edit();
    paramContext.putInt("priv_auto_backup_hour", paramInt);
    paramContext.commit();
  }
  
  public static void v(Context paramContext, String paramString1, String paramString2)
  {
    SharedPreferences.Editor localEditor = m.ip(paramContext).edit();
    if ((paramString1 == null) || ("".equalsIgnoreCase(paramString1)))
    {
      localEditor.putString("pkey_led_color1", paramString2);
      paramContext = localEditor;
    }
    for (;;)
    {
      paramContext.commit();
      return;
      if (paramString1.equals("368hqpm33"))
      {
        paramContext = m.dd(paramContext, "privacy_" + eY(paramContext)).edit();
        paramContext.putString("pkey_led_color1", paramString2);
      }
      else
      {
        paramContext = m.C(paramContext, "pkey_led_color1", paramString1);
        localEditor.putString("pkey_led_color1_" + paramContext, paramString2);
        paramContext = localEditor;
      }
    }
  }
  
  public static void w(Context paramContext, int paramInt)
  {
    paramContext = m.dd(paramContext, "privacy_" + eY(paramContext)).edit();
    paramContext.putInt("priv_auto_backup_minute", paramInt);
    paramContext.commit();
  }
  
  public static void w(Context paramContext, String paramString1, String paramString2)
  {
    SharedPreferences.Editor localEditor = m.ip(paramContext).edit();
    if (paramString2 != null) {
      localEditor.putString("pref_smileyes_" + m.di(paramContext, paramString2), paramString1);
    }
    for (;;)
    {
      localEditor.commit();
      return;
      localEditor.putString("pref_smileyes", paramString1);
    }
  }
  
  public static void x(Context paramContext, int paramInt)
  {
    String[] arrayOfString = paramContext.getResources().getStringArray(2131492987);
    paramContext = m.ip(paramContext).edit();
    paramContext.putString(ckj, arrayOfString[paramInt]);
    paramContext.commit();
  }
  
  public static void x(Context paramContext, String paramString1, String paramString2)
  {
    SharedPreferences.Editor localEditor = m.ip(paramContext).edit();
    if (paramString2 != null) {
      localEditor.putString(clo + "_" + m.di(paramContext, paramString2), paramString1);
    }
    for (;;)
    {
      localEditor.commit();
      return;
      localEditor.putString(clo, paramString1);
    }
  }
  
  public static void y(Context paramContext, int paramInt)
  {
    paramContext = m.ip(paramContext).edit();
    paramContext.putInt(clr, paramInt);
    paramContext.commit();
  }
  
  public static void y(Context paramContext, String paramString1, String paramString2)
  {
    SharedPreferences.Editor localEditor = m.ip(paramContext).edit();
    if ((paramString1 == null) || ("".equalsIgnoreCase(paramString1))) {
      localEditor.putString("pref_colorful_bubble_type", paramString2);
    }
    for (;;)
    {
      localEditor.commit();
      return;
      paramContext = m.C(paramContext, "pref_colorful_bubble_type", paramString1);
      localEditor.putString("pref_colorful_bubble_type_" + paramContext, paramString2);
    }
  }
  
  public static void z(Context paramContext, int paramInt)
  {
    paramContext = m.ip(paramContext).edit();
    paramContext.putInt(cls, paramInt);
    paramContext.commit();
  }
  
  public static void z(Context paramContext, boolean paramBoolean)
  {
    paramContext = m.ip(paramContext).edit();
    paramContext.putBoolean("pref_plugin_notice", paramBoolean);
    paramContext.commit();
  }
}
