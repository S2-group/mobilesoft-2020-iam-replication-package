package cn.jpush.android.util;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import cn.jpush.android.api.InstrumentedActivity;
import cn.jpush.android.api.InstrumentedListActivity;
import cn.jpush.android.data.d;
import cn.jpush.android.service.AlarmReceiver;
import cn.jpush.android.service.DaemonService;
import cn.jpush.android.service.DownloadService;
import cn.jpush.android.service.PushReceiver;
import cn.jpush.android.service.PushService;
import cn.jpush.android.service.ServiceInterface;
import cn.jpush.android.service.s;
import cn.jpush.android.service.t;
import cn.jpush.android.ui.PushActivity;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.security.MessageDigest;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.security.auth.x500.X500Principal;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class a
{
  public static int a;
  private static List<String> b;
  private static final X500Principal c;
  private static final String d;
  private static final String e;
  private static final String f;
  private static final ArrayList<String> g;
  private static final ArrayList<String> h;
  private static final ArrayList<String> i;
  private static PushReceiver j;
  private static final String[] z;
  
  static
  {
    Object localObject1 = new String[''];
    int m = 0;
    String str = "\"P\f\025&$T";
    int k = -1;
    Object localObject2;
    for (;;)
    {
      localObject2 = localObject1;
      str = z(z(str));
      switch (k)
      {
      default: 
        localObject2[m] = str;
        str = "#Y\036\036* V\t";
        m = 1;
        k = 0;
        break;
      case 0: 
        localObject2[m] = str;
        str = "4J\001";
        m = 2;
        k = 1;
        break;
      case 1: 
        localObject2[m] = str;
        str = "%]\033\022+$";
        m = 3;
        k = 2;
        break;
      case 2: 
        localObject2[m] = str;
        m = 4;
        str = " V\t\t'(\\>\037#\027]\037\b!.V";
        k = 3;
        break;
      case 3: 
        localObject2[m] = str;
        m = 5;
        str = ",W\t\036$";
        k = 4;
        break;
      case 4: 
        localObject2[m] = str;
        m = 6;
        str = "&K\000U>$J\036\022'/\026\017\032;$Z\f\025,";
        k = 5;
        break;
      case 5: 
        localObject2[m] = str;
        m = 7;
        str = "/]\031\f'3S";
        k = 6;
        break;
      case 6: 
        localObject2[m] = str;
        m = 8;
        str = "\013h\030\b \036|\b\r!\"]$\037";
        k = 7;
        break;
      case 7: 
        localObject2[m] = str;
        m = 9;
        str = " V\t\t'(\\C\013-3U\004\b;(W\003U\037\023q9>\027\022}9/\001\017>";
        k = 8;
        break;
      case 8: 
        localObject2[m] = str;
        m = 10;
        str = " V\t\t'(\\C\013-3U\004\b;(W\003U\032\004y)$\030\tw#>\027\022l,/\r";
        k = 9;
        break;
      case 9: 
        localObject2[m] = str;
        m = 11;
        str = "1P\002\025-";
        k = 10;
        break;
      case 10: 
        localObject2[m] = str;
        m = 12;
        str = "\"VC\02184K\005U)/\\\037\024!%\026 (\017\036q)";
        k = 11;
        break;
      case 11: 
        localObject2[m] = str;
        m = 13;
        str = "\"VC\02184K\005U)/\\\037\024!%\026,+\030\n}4";
        k = 12;
        break;
      case 12: 
        localObject2[m] = str;
        m = 14;
        str = "\"VC\02184K\005U)/\\\037\024!%\026+2\004\004g=:\034\t";
        k = 13;
        break;
      case 13: 
        localObject2[m] = str;
        m = 15;
        str = "\022]\003\037h#J\002\032,\"Y\036\017h5WM\03281\002M";
        k = 14;
        break;
      case 14: 
        localObject2[m] = str;
        m = 16;
        str = "\"VC\02184K\005U)/\\\037\024!%\02692\034\r}";
        k = 15;
        break;
      case 15: 
        localObject2[m] = str;
        m = 17;
        str = "\"VC\02184K\005U)/\\\037\024!%\026(#\034\023y";
        k = 16;
        break;
      case 16: 
        localObject2[m] = str;
        m = 18;
        str = "\"VC\02184K\005U)/\\\037\024!%\026\004\025<$V\031U\005\004k>:\017\004g?>\013\004q;>\f";
        k = 17;
        break;
      case 17: 
        localObject2[m] = str;
        m = 19;
        str = "dKC\013-3U\004\b;(W\003U\002\021m>3\027\f}>(\t\006}";
        k = 18;
        break;
      case 18: 
        localObject2[m] = str;
        m = 20;
        str = "\"VC\02184K\005U)/\\\037\024!%\026 >\033\022y*>";
        k = 19;
        break;
      case 19: 
        localObject2[m] = str;
        m = 21;
        str = "\"VC\02184K\005U)/\\\037\024!%\026.4\006\025}#/\027\025a=>";
        k = 20;
        break;
      case 20: 
        localObject2[m] = str;
        m = 22;
        str = "x\017ZO,t\016\tMps]XOq\"";
        k = 21;
        break;
      case 21: 
        localObject2[m] = str;
        m = 23;
        str = "/W\031\022.([\f\017!.V";
        k = 22;
        break;
      case 22: 
        localObject2[m] = str;
        m = 24;
        str = "5W\f\b<\025]\025\017";
        k = 23;
        break;
      case 23: 
        localObject2[m] = str;
        m = 25;
        str = "讶刈M+'3L\f\027h之莏去惓盌卄吵員:81s\b\002帾暵斈,\025,3W\004\037\005 V\004\035-2L皕廯嬟毴";
        k = 24;
        break;
      case 24: 
        localObject2[m] = str;
        m = 26;
        str = "\"VC\02184K\005U)/\\\037\024!%\026\004\025<$V\031U\006\016l$=\001\002y92\007\017g\"+\r\017})$\030\023w5\"";
        k = 25;
        break;
      case 25: 
        localObject2[m] = str;
        m = 27;
        str = "\013h\030\b 掑礂ｷ卾呅响y\035\013\003$A习卂鄅";
        k = 26;
        break;
      case 26: 
        localObject2[m] = str;
        m = 28;
        str = "%]\017\016/\036V\002\017!'Q\016\032<(W\003";
        k = 27;
        break;
      case 27: 
        localObject2[m] = str;
        m = 29;
        str = "%Y\031\032";
        k = 28;
        break;
      case 28: 
        localObject2[m] = str;
        m = 30;
        str = "5A\035\036";
        k = 29;
        break;
      case 29: 
        localObject2[m] = str;
        m = 31;
        str = "(L\004\026-";
        k = 30;
        break;
      case 30: 
        localObject2[m] = str;
        m = 32;
        str = " V\t\t'(\\C\022&5]\003\017f [\031\022'/\026;2\r\026";
        k = 31;
        break;
      case 31: 
        localObject2[m] = str;
        m = 33;
        str = " H\035\027!\"Y\031\022'/\027\033\025,oY\003\037:.Q\tU8 [\006\032/$\025\f\t+)Q\033\036";
        k = 32;
        break;
      case 32: 
        localObject2[m] = str;
        m = 34;
        str = "1T\030\034/$\\";
        k = 33;
        break;
      case 33: 
        localObject2[m] = str;
        m = 35;
        str = "2L\f\017=2";
        k = 34;
        break;
      case 34: 
        localObject2[m] = str;
        m = 36;
        str = " V\t\t'(\\C\022&5]\003\017f [\031\022'/\026/:\034\025}?\"\027\002p,5\017\004|";
        k = 35;
        break;
      case 35: 
        localObject2[m] = str;
        m = 37;
        str = " V\t\t'(\\2\022,";
        k = 36;
        break;
      case 36: 
        localObject2[m] = str;
        m = 38;
        str = "$U\035\0171aH\f\t),K";
        k = 37;
        break;
      case 37: 
        localObject2[m] = str;
        m = 39;
        str = "\fy.[)%\\\037[!/^\002Vel\025M";
        k = 38;
        break;
      case 38: 
        localObject2[m] = str;
        m = 40;
        str = " V\t\t'(\\C\013-3U\004\b;(W\003U\t\002{((\033\036o$=\001\036k9:\034\004";
        k = 39;
        break;
      case 39: 
        localObject2[m] = str;
        m = 41;
        str = "6Q\013\022";
        k = 40;
        break;
      case 40: 
        localObject2[m] = str;
        m = 42;
        str = "\"W\003\025-\"L\004\r!5A";
        k = 41;
        break;
      case 41: 
        localObject2[m] = str;
        m = 43;
        str = "\002M\037\t-/LM\013#aQ\003\b< T\001\036,aH\f\017 {\030";
        k = 42;
        break;
      case 42: 
        localObject2[m] = str;
        m = 44;
        str = "e\034";
        k = 43;
        break;
      case 43: 
        localObject2[m] = str;
        m = 45;
        str = "n\\\f\017)nY\035\013g";
        k = 44;
        break;
      case 44: 
        localObject2[m] = str;
        m = 46;
        str = "nK\024\b<$UB\03281\027";
        k = 45;
        break;
      case 45: 
        localObject2[m] = str;
        m = 47;
        str = "\013h\030\b 掑礂ｷ罁屙纞讙于硺";
        k = 46;
        break;
      case 46: 
        localObject2[m] = str;
        m = 48;
        str = " [\031\022>(L\024";
        k = 47;
        break;
      case 47: 
        localObject2[m] = str;
        m = 49;
        str = "梁浳剝隽托ak)0h杫淃勍纤诩亢砹は烂决枤眳讋悾";
        k = 48;
        break;
      case 48: 
        localObject2[m] = str;
        m = 50;
        str = "(U\b\022";
        k = 49;
        break;
      case 49: 
        localObject2[m] = str;
        m = 51;
        str = "\f|X";
        k = 50;
        break;
      case 50: 
        localObject2[m] = str;
        m = 52;
        str = "$V\t\017\005(V\036";
        k = 51;
        break;
      case 51: 
        localObject2[m] = str;
        m = 53;
        str = "2L\f\t<\fQ\003\b";
        k = 52;
        break;
      case 52: 
        localObject2[m] = str;
        m = 54;
        str = "2L\f\t<\tW\030\t";
        k = 53;
        break;
      case 53: 
        localObject2[m] = str;
        m = 55;
        str = "$V\t3'4J";
        k = 54;
        break;
      case 54: 
        localObject2[m] = str;
        m = 56;
        str = "2Q\001\036&\"]=\016;)l\004\026-";
        k = 55;
        break;
      case 55: 
        localObject2[m] = str;
        m = 57;
        str = "\002Y\003[&.LM\034-5\030\035\032+*Y\n\036h/Y\000\036f";
        k = 56;
        break;
      case 56: 
        localObject2[m] = str;
        m = 58;
        str = "\000V\t\t'(\\8\017!-";
        k = 57;
        break;
      case 57: 
        localObject2[m] = str;
        m = 59;
        str = "\"VC\02184K\005U)/\\\037\024!%\026\030\022f\021M\036\023\t\"L\004\r!5A";
        k = 58;
        break;
      case 58: 
        localObject2[m] = str;
        m = 60;
        str = "#W\t\002";
        k = 59;
        break;
      case 59: 
        localObject2[m] = str;
        m = 61;
        str = "(K8\013, L\b--3K\004\024&";
        k = 60;
        break;
      case 60: 
        localObject2[m] = str;
        m = 62;
        str = "\035f";
        k = 61;
        break;
      case 61: 
        localObject2[m] = str;
        m = 63;
        str = "1M\036\023h5Q\000\036h(KM｡";
        k = 62;
        break;
      case 62: 
        localObject2[m] = str;
        m = 64;
        str = "\"T\002\b-1M\036\023";
        k = 63;
        break;
      case 63: 
        localObject2[m] = str;
        m = 65;
        str = "1M\036\023<(U\b";
        k = 64;
        break;
      case 64: 
        localObject2[m] = str;
        m = 66;
        str = ",W\030\025<$\\";
        k = 65;
        break;
      case 65: 
        localObject2[m] = str;
        m = 67;
        str = "\017WM\017)3_\b\017h3]\016\036!7]\037[i";
        k = 66;
        break;
      case 66: 
        localObject2[m] = str;
        m = 68;
        str = "q\022]";
        k = 67;
        break;
      case 67: 
        localObject2[m] = str;
        m = 69;
        str = "u_";
        k = 68;
        break;
      case 68: 
        localObject2[m] = str;
        m = 70;
        str = "s_";
        k = 69;
        break;
      case 69: 
        localObject2[m] = str;
        m = 71;
        str = "r_";
        k = 70;
        break;
      case 70: 
        localObject2[m] = str;
        m = 72;
        str = "4V\006\025'6V";
        k = 71;
        break;
      case 71: 
        localObject2[m] = str;
        m = 73;
        str = " V\t\t'(\\C\022&5]\003\017f [\031\022'/\026/4\007\025g.4\005\021t(/\r\005";
        k = 72;
        break;
      case 72: 
        localObject2[m] = str;
        m = 74;
        str = "\025P\b[8$J\000\022;2Q\002\025h2P\002\016$%\030\017\036h%]\013\022&$\\MVh";
        k = 73;
        break;
      case 73: 
        localObject2[m] = str;
        m = 75;
        str = "\"VC\02184K\005U)/\\\037\024!%\026\004\025<$V\031U\f ]\000\024&\022]\037\r!\"]";
        k = 74;
        break;
      case 74: 
        localObject2[m] = str;
        m = 76;
        str = "\030W\030[;)W\030\027,aU\f\020-aU\f\022&aY\016\017!7Q\031\002h$@\031\036&%KM2&2L\037\016%$V\031\036,\000[\031\022>(L\024[`\013h\030\b h\024M\024<)]\037\f!2]M\002'4\030\032\022$-\030\003\024<aK\b\036h4K\b\th\"T\004\030#aY\003\037h4K\b\th [\031\022>$\030\031\022%$\030\036\017)3LM\024&aJ\b\013'3LM\022&ah\002\t< TC[";
        k = 75;
        break;
      case 75: 
        localObject2[m] = str;
        m = 77;
        str = "m\030\002\017 $J\032\022;$\030\024\024=a[\f\025h/W\031[$.[\f\017-aL\005\036h%]\033\022+$KC";
        k = 76;
        break;
      case 76: 
        localObject2[m] = str;
        m = 78;
        str = "\000V\t\t'(\\ \032&(^\b\b<o@\000\027h,Q\036\b!/_M\t-0M\004\t-%\030\004\025<$V\031[.(T\031\036:a^\002\th\021M\036\023\033$J\033\022+$\002M\030&oR\035\016;)\026\f\025,3W\004\037f(V\031\036&5\026?>\017\bk9>\032";
        k = 77;
        break;
      case 77: 
        localObject2[m] = str;
        m = 79;
        str = "\000V\t\t'(\\ \032&(^\b\b<o@\000\027h,Q\036\b!/_M\t-0M\004\t-%\030\037\036+$Q\033\036:{\030";
        k = 78;
        break;
      case 78: 
        localObject2[m] = str;
        m = 80;
        str = "\"VC\02184K\005U)/\\\037\024!%\026\004\025<$V\031U\006\016l$=\001\002y92\007\017g?>\013\004q;>\f\036h?4\020\030";
        k = 79;
        break;
      case 79: 
        localObject2[m] = str;
        m = 81;
        str = "\025P\b[8$J\000\022;2W\004\025h(KM\t-0M\004\t-%\030@[";
        k = 80;
        break;
      case 80: 
        localObject2[m] = str;
        m = 82;
        str = "\000V\t\t'(\\ \032&(^\b\b<o@\000\027h,Q\036\b!/_M\t-0M\004\t-%\030\036\036:7Q\016\036ra";
        k = 81;
        break;
      case 81: 
        localObject2[m] = str;
        m = 83;
        str = "\000V\t\t'(\\ \032&(^\b\b<o@\000\027h,Q\036\b!/_M\t-0M\004\t-%\030\004\025<$V\031[.(T\031\036:a^\002\th\021M\036\023\t\"L\004\r!5AW[+/\026\007\013=2PC\032&%J\002\022,oM\004U\0304K\005:+5Q\033\022<8";
        k = 82;
        break;
      case 82: 
        localObject2[m] = str;
        m = 84;
        str = "\000V\t\t'(\\ \032&(^\b\b<o@\000\027h,Q\036\b!/_M\022&5]\003\017h'Q\001\017-3\030\013\024:a|\f\036%.V>\036:7Q\016\036ra[\003U\"1M\036\023f V\t\t'(\\C\022&5]\003\017f\005Y\b\026'/k\b\t>([\b";
        k = 83;
        break;
      case 83: 
        localObject2[m] = str;
        m = 85;
        str = "\005]\033\036$.H\b\th2P\002\016$%\030\036\036<ay\035\013\003$AM\022&ay\003\037:.Q\t6)/Q\013\036;5\026\025\026$";
        k = 84;
        break;
      case 84: 
        localObject2[m] = str;
        m = 86;
        str = "\000V\t\t'(\\ \032&(^\b\b<o@\000\027h,Q\036\b!/_M\t-0M\004\t-%\030\004\025<$V\031[.(T\031\036:a^\002\th\021M\036\023\032$[\b\022>$JW[+/\026\007\013=2PC\032&%J\002\022,oQ\003\017-/LC5\007\025q+2\013\000l$4\006\036j(8\r\bn(?\027\021j\"#\021";
        k = 85;
        break;
      case 85: 
        localObject2[m] = str;
        m = 87;
        str = "\026]M\t-\"W\000\026-/\\M\002'4\030\f\037,aL\005\036h1]\037\026!2K\004\024&a\025M";
        k = 86;
        break;
      case 86: 
        localObject2[m] = str;
        m = 88;
        str = " [\031\022'/\002\016\023-\"S;\032$(\\ \032&(^\b\b<";
        k = 87;
        break;
      case 87: 
        localObject2[m] = str;
        m = 89;
        str = "\"VC\02184K\005U)/\\\037\024!%\026\004\025<$V\031U\032\004h\")\034";
        k = 88;
        break;
      case 88: 
        localObject2[m] = str;
        m = 90;
        str = "\017WM\017)3_\b\017h [\031\022>(L\024[i";
        k = 89;
        break;
      case 89: 
        localObject2[m] = str;
        m = 91;
        str = "\000V\t\t'(\\ \032&(^\b\b<o@\000\027h,Q\036\b!/_M\t-0M\004\t-%\030\004\025<$V\031[.(T\031\036:a\002M\030&oR\035\016;)\026\f\025,3W\004\037f(V\031\036&5\026?>\030\016j9";
        k = 90;
        break;
      case 90: 
        localObject2[m] = str;
        m = 92;
        str = "\000V\t\t'(\\ \032&(^\b\b<o@\000\027h,Q\036\b!/_M\t-0M\004\t-%\030\f\030<(N\004\0171{\030";
        k = 91;
        break;
      case 91: 
        localObject2[m] = str;
        m = 93;
        str = "\021M\036\023\032$[\b\022>$JM\b .M\001\037h/W\031[  N\b[!/L\b\025<a^\004\027<$JMVeaY\003\037:.Q\tU!/L\b\025<oY\016\017!.VC9\007\016l28\007\fh!>\034\004|A[\030-]\f\b-aJ\b\026'7]M\017 $\030\004\025<$V\031[.(T\031\036:aQ\003[\t/\\\037\024!%u\f\025!']\036\017f9U\001";
        k = 92;
        break;
      case 92: 
        localObject2[m] = str;
        m = 94;
        str = "oH\b\t%(K\036\022'/\026'+\035\022p26\r\022k,<\r";
        k = 93;
        break;
      case 93: 
        localObject2[m] = str;
        m = 95;
        str = "\"VC\02184K\005U)/\\\037\024!%\026\004\025<$V\031U\032\004$(\034\004j";
        k = 94;
        break;
      case 94: 
        localObject2[m] = str;
        m = 96;
        str = "\025P\b[8$J\000\022;2W\004\025h(KM\t-0M\004\t-%\030@[)/\\\037\024!%\026\035\036:,Q\036\b!.VC,\032\bl($\033\004l92\006\006k";
        k = 95;
        break;
      case 95: 
        localObject2[m] = str;
        m = 97;
        str = "\bu(2";
        k = 96;
        break;
      case 96: 
        localObject2[m] = str;
        m = 98;
        str = "\021J\002\030-2K\002\t";
        k = 97;
        break;
      case 97: 
        localObject2[m] = str;
        m = 99;
        str = "nH\037\024+n[\035\016!/^\002";
        k = 98;
        break;
      case 98: 
        localObject2[m] = str;
        m = 100;
        str = "\031\026XKq";
        k = 99;
        break;
      case 99: 
        localObject2[m] = str;
        m = 101;
        str = " V\t\t'(\\C\013-3U\004\b;(W\003U\037\023q9>\027\004`9>\032\017y!$\033\025w?:\017\004";
        k = 100;
        break;
      case 100: 
        localObject2[m] = str;
        m = 102;
        str = "\002W\000\013'/]\003\017\001/^\002\000";
        k = 101;
        break;
      case 101: 
        localObject2[m] = str;
        m = 103;
        str = "\032\b0Q";
        k = 102;
        break;
      case 102: 
        localObject2[m] = str;
        m = 104;
        str = "%M\035\027!\"Y\031\036";
        k = 103;
        break;
      case 103: 
        localObject2[m] = str;
        m = 105;
        str = " V\t\t'(\\C\022&5]\003\017f$@\031\t)oK\005\024:5[\030\017f\bv9>\006\025";
        k = 104;
        break;
      case 104: 
        localObject2[m] = str;
        m = 106;
        str = "\"W\000U)/\\\037\024!%\026\001\032=/[\005\036:oY\016\017!.VC2\006\022l,7\004\036k%4\032\025{8/";
        k = 105;
        break;
      case 105: 
        localObject2[m] = str;
        m = 107;
        str = "\024V\b\0038$[\031\036,{\030\004\025> T\004\037h4J\001[ea";
        k = 106;
        break;
      case 106: 
        localObject2[m] = str;
        m = 108;
        str = " V\t\t'(\\C\022&5]\003\017f$@\031\t)oK\005\024:5[\030\017f\b{\"5\027\023}>4\035\023{(";
        k = 107;
        break;
      case 107: 
        localObject2[m] = str;
        m = 109;
        str = " V\t\t'(\\C\022&5]\003\017f$@\031\t)oK\005\024:5[\030\017f\017y >";
        k = 108;
        break;
      case 108: 
        localObject2[m] = str;
        m = 110;
        str = "\017WM\017)3_\b\017h2]\037\r!\"]MZ";
        k = 109;
        break;
      case 109: 
        localObject2[m] = str;
        m = 111;
        str = "3]\n\022;5]\037$,$N\004\030-\036U\f\030";
        k = 110;
        break;
      case 110: 
        localObject2[m] = str;
        m = 112;
        str = "3]\n\022;5]\037$,$N\004\030-\036Q\000\036!";
        k = 111;
        break;
      case 111: 
        localObject2[m] = str;
        m = 113;
        str = "3]\n\022;5]\037$,$N\004\030-\036Y\003\037:.Q\t$!%";
        k = 112;
        break;
      case 112: 
        localObject2[m] = str;
        m = 114;
        str = "-]\033\036$l\tZNr7]\037\b!.VM\0168&J\f\037-";
        k = 113;
        break;
      case 113: 
        localObject2[m] = str;
        m = 115;
        str = "\025P\004\bh(KM\032h/]\032[,$N\004\030-a^\002\th3]\n\022;5]\037Z";
        k = 114;
        break;
      case 114: 
        localObject2[m] = str;
        m = 116;
        str = " V\t\t'(\\C\025-5\026\016\024&/\026.4\006\017}./\001\027q9\"\027\002p,5\017\004";
        k = 115;
        break;
      case 115: 
        localObject2[m] = str;
        m = 117;
        str = " V\t\t'(\\C\022&5]\003\017f [\031\022'/\026=:\013\ny*>\027\023} 4\036\004|";
        k = 116;
        break;
      case 116: 
        localObject2[m] = str;
        m = 118;
        str = " V\t\t'(\\C\022&5]\003\017f [\031\022'/\026=:\013\ny*>\027\000|)>\f";
        k = 117;
        break;
      case 117: 
        localObject2[m] = str;
        m = 119;
        str = "1Y\016\020)&]";
        k = 118;
        break;
      case 118: 
        localObject2[m] = str;
        m = 120;
        str = " V\t\t'(\\C\022&5]\003\017f [\031\022'/\0268(\r\023g=)\r\022}#/";
        k = 119;
        break;
      case 119: 
        localObject2[m] = str;
        m = 121;
        str = "3]\n\022;5J\f\017!.V2\022,";
        k = 120;
        break;
      case 120: 
        localObject2[m] = str;
        m = 122;
        str = " V\t\t'(\\C\022&5]\003\017f\"Y\031\036/.J\024U\004\000m#8\000\004j";
        k = 121;
        break;
      case 121: 
        localObject2[m] = str;
        m = 123;
        str = "\017m!7h\"W\003\017-9L";
        k = 122;
        break;
      case 122: 
        localObject2[m] = str;
        m = 124;
        str = "\006Q\033\036h4HM\017'aK\031\032:5\030\f\0138a^\002\th(V\033\032$(\\M\013)3Y\000\bhl\030\035\032+*Y\n\036\006 U\bA";
        k = 123;
        break;
      case 123: 
        localObject2[m] = str;
        m = 125;
        str = "\024V\006\025'6V";
        k = 124;
        break;
      case 124: 
        localObject2[m] = str;
        m = 126;
        str = "\006W\031[;%[\f\t,a^\004\027-aK\f\r-%\030\t\036>([\b2,a\025M";
        k = 125;
        break;
      case 125: 
        localObject2[m] = str;
        m = 127;
        str = " V\t\t'(\\C\022&5]\003\017f [\031\022'/\026 :\001\017";
        k = 126;
        break;
      case 126: 
        localObject2[m] = str;
        m = 128;
        str = "\006W\031[;%[\f\t,a^\004\027-aK\f\r-%\030\030\037!%\030@[";
        k = 127;
        break;
      case 127: 
        localObject2[m] = str;
        m = 129;
        str = "\021J\b\035;\007Q\001\036";
        k = 128;
        break;
      case 128: 
        localObject2[m] = str;
        m = 130;
        str = "1M\036\023\0274\\\004\037";
        k = 129;
        break;
      case 129: 
        localObject2[m] = str;
        m = 131;
        str = "*]\024";
        k = 130;
        break;
      case 130: 
        localObject2[m] = str;
        m = 132;
        str = "\021M\036\023";
        k = 131;
        break;
      case 131: 
        localObject2[m] = str;
        m = 133;
        str = "1W\032\036:";
        k = 132;
        break;
      case 132: 
        localObject2[m] = str;
        m = 134;
        str = "\fY\004\025h\"T\f\b;aQ\036[";
        k = 133;
        break;
      case 133: 
        localObject2[m] = str;
        m = 135;
        str = "/Y\000\036";
        k = 134;
        break;
      case 134: 
        localObject2[m] = str;
        m = 136;
        str = "7]\037$& U\b";
        k = 135;
        break;
      case 135: 
        localObject2[m] = str;
        m = 137;
        str = "7]\037$+.\\\b";
        k = 136;
        break;
      case 136: 
        localObject2[m] = str;
        m = 138;
        str = "1S\n";
        k = 137;
        break;
      case 137: 
        localObject2[m] = str;
        m = 139;
        str = "\006]\003\036: L\b[\f$N\004\030-\b\\M\b=\"[\b\b;a";
        k = 138;
        break;
      case 138: 
        localObject2[m] = str;
        m = 140;
        str = "\013h8(\000\036y=+\003\004a";
        k = 139;
        break;
      case 139: 
        localObject2[m] = str;
        m = 141;
        str = "\023]\001\036)2]M\f)*]M\027'\"SM\032.5]\037[}q\b]\026;{";
        k = 140;
        break;
      case 140: 
        localObject2[m] = str;
        m = 142;
        str = "4L\013Vp";
        k = 141;
        break;
      case 141: 
        localObject2[m] = str;
        m = 143;
        str = "\002vP:&%J\002\022,a|\b\031=&\024\"F\t/\\\037\024!%\024.F\035\022";
        k = 142;
      }
    }
    localObject2[m] = str;
    z = (String[])localObject1;
    localObject1 = new ArrayList();
    b = (List)localObject1;
    str = "r\rUMr\b\\Hx\rUB}";
    k = -1;
    for (;;)
    {
      str = z(z(str));
      switch (k)
      {
      default: 
        ((List)localObject1).add(str);
        localObject1 = b;
        str = "q\bYBqx\b\\K~u\b]Kx";
        for (k = -1;; k = 0)
        {
          str = z(z(str));
          switch (k)
          {
          default: 
            ((List)localObject1).add(str);
            localObject1 = b;
            str = "q\b]Kxq\b]Kxq\b]K";
          }
        }
        ((List)localObject1).add(str);
        c = new X500Principal(z['']);
        localObject1 = new StringBuilder().append(Environment.getExternalStorageDirectory().getPath());
        str = "n\\\f\017)n";
        k = -1;
        for (;;)
        {
          str = z(z(str));
          switch (k)
          {
          default: 
            d = str;
            localObject1 = new StringBuilder().append(Environment.getExternalStorageDirectory().getPath());
            str = "n\\\f\017)n\026\035\016;)g\030\037!%";
            k = 0;
            continue;
            ((List)localObject1).add(str);
            localObject1 = b;
            str = "q\b]Kxq\b]Kxq\b]Kx";
            k = 1;
            break;
          case 0: 
            e = str;
            localObject1 = new StringBuilder().append(Environment.getExternalStorageDirectory().getPath());
            str = "n\\\f\017)n\026\035\016;)g\t\036>([\b\022,";
            k = 1;
          }
        }
        f = str;
        a = 1;
        g = new ArrayList();
        h = new ArrayList();
        localObject1 = g;
        str = " V\t\t'(\\C\013-3U\004\b;(W\003U\001\017l()\006\004l";
        k = 0;
        break;
      case 0: 
        ((ArrayList)localObject1).add(str);
        localObject1 = g;
        str = " V\t\t'(\\C\013-3U\004\b;(W\003U\037\000s($\004\016{&";
        k = 1;
        break;
      case 1: 
        ((ArrayList)localObject1).add(str);
        g.add(z[101]);
        localObject1 = g;
        str = " V\t\t'(\\C\013-3U\004\b;(W\003U\t\002{((\033\036v(/\037\016j&$\033\025y9>";
        k = 2;
        break;
      case 2: 
        ((ArrayList)localObject1).add(str);
        localObject1 = h;
        str = " V\t\t'(\\C\013-3U\004\b;(W\003U\036\bz?:\034\004";
        k = 3;
        break;
      case 3: 
        ((ArrayList)localObject1).add(str);
        localObject1 = h;
        str = " V\t\t'(\\C\013-3U\004\b;(W\003U\013\ty#<\r\036o$=\001\036k9:\034\004";
        k = 4;
        break;
      case 4: 
        ((ArrayList)localObject1).add(str);
        localObject1 = new ArrayList();
        i = (ArrayList)localObject1;
        str = " V\t\t'(\\C\013-3U\004\b;(W\003U\t\002{((\033\036~$5\r\036t\"8\t\025q\"5";
        k = 5;
        break;
      case 5: 
        ((ArrayList)localObject1).add(str);
        localObject1 = i;
        str = " V\t\t'(\\C\013-3U\004\b;(W\003U\t\002{((\033\036{\":\032\022}27\007\002y92\007\017";
        k = 6;
        break;
      case 6: 
        ((ArrayList)localObject1).add(str);
        localObject1 = i;
        str = " V\t\t'(\\C\013-3U\004\b;(W\003U\t\002{((\033\036t\"8\t\025q\"5\027\004`9)\t\036{\"6\005\000v)(";
        k = 7;
      }
    }
    ((ArrayList)localObject1).add(str);
    i.add(z[40]);
  }
  
  /* Error */
  private static String A(Context paramContext)
  {
    // Byte code:
    //   0: invokestatic 422	cn/jpush/android/util/x:c	()V
    //   3: aload_0
    //   4: getstatic 324	cn/jpush/android/util/a:z	[Ljava/lang/String;
    //   7: sipush 129
    //   10: aaload
    //   11: iconst_0
    //   12: invokevirtual 428	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   15: getstatic 324	cn/jpush/android/util/a:z	[Ljava/lang/String;
    //   18: sipush 131
    //   21: aaload
    //   22: aconst_null
    //   23: invokeinterface 434 3 0
    //   28: astore_1
    //   29: aload_1
    //   30: invokestatic 439	cn/jpush/android/util/ai:a	(Ljava/lang/String;)Z
    //   33: ifne +5 -> 38
    //   36: aload_1
    //   37: areturn
    //   38: invokestatic 442	cn/jpush/android/util/a:a	()Z
    //   41: ifne +75 -> 116
    //   44: aload_0
    //   45: getstatic 324	cn/jpush/android/util/a:z	[Ljava/lang/String;
    //   48: sipush 129
    //   51: aaload
    //   52: iconst_0
    //   53: invokevirtual 428	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   56: astore_2
    //   57: aload_2
    //   58: getstatic 324	cn/jpush/android/util/a:z	[Ljava/lang/String;
    //   61: sipush 131
    //   64: aaload
    //   65: aconst_null
    //   66: invokeinterface 434 3 0
    //   71: astore_0
    //   72: aload_0
    //   73: astore_1
    //   74: aload_0
    //   75: ifnonnull -39 -> 36
    //   78: invokestatic 448	java/util/UUID:randomUUID	()Ljava/util/UUID;
    //   81: invokevirtual 449	java/util/UUID:toString	()Ljava/lang/String;
    //   84: astore_0
    //   85: aload_2
    //   86: invokeinterface 453 1 0
    //   91: astore_1
    //   92: aload_1
    //   93: getstatic 324	cn/jpush/android/util/a:z	[Ljava/lang/String;
    //   96: sipush 131
    //   99: aaload
    //   100: aload_0
    //   101: invokeinterface 459 3 0
    //   106: pop
    //   107: aload_1
    //   108: invokeinterface 462 1 0
    //   113: pop
    //   114: aload_0
    //   115: areturn
    //   116: aload_0
    //   117: getstatic 324	cn/jpush/android/util/a:z	[Ljava/lang/String;
    //   120: sipush 130
    //   123: aaload
    //   124: aconst_null
    //   125: invokestatic 467	cn/jpush/android/util/ae:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   128: astore_2
    //   129: aload_2
    //   130: astore_1
    //   131: aload_2
    //   132: ifnonnull -96 -> 36
    //   135: new 361	java/io/File
    //   138: dup
    //   139: getstatic 382	cn/jpush/android/util/a:e	Ljava/lang/String;
    //   142: invokespecial 468	java/io/File:<init>	(Ljava/lang/String;)V
    //   145: astore_1
    //   146: aload_1
    //   147: invokevirtual 471	java/io/File:exists	()Z
    //   150: ifeq +71 -> 221
    //   153: new 473	java/io/FileInputStream
    //   156: dup
    //   157: aload_1
    //   158: invokespecial 476	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   161: invokestatic 481	cn/jpush/android/util/m:a	(Ljava/io/InputStream;)Ljava/util/ArrayList;
    //   164: astore_2
    //   165: aload_2
    //   166: invokevirtual 485	java/util/ArrayList:size	()I
    //   169: ifle +52 -> 221
    //   172: aload_2
    //   173: iconst_0
    //   174: invokevirtual 489	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   177: checkcast 28	java/lang/String
    //   180: astore_2
    //   181: aload_0
    //   182: getstatic 324	cn/jpush/android/util/a:z	[Ljava/lang/String;
    //   185: sipush 130
    //   188: aaload
    //   189: aload_2
    //   190: invokestatic 492	cn/jpush/android/util/ae:b	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)V
    //   193: new 352	java/lang/StringBuilder
    //   196: dup
    //   197: getstatic 324	cn/jpush/android/util/a:z	[Ljava/lang/String;
    //   200: sipush 128
    //   203: aaload
    //   204: invokespecial 493	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   207: aload_2
    //   208: invokevirtual 369	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   211: invokevirtual 374	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   214: pop
    //   215: invokestatic 495	cn/jpush/android/util/x:d	()V
    //   218: aload_2
    //   219: areturn
    //   220: astore_2
    //   221: new 352	java/lang/StringBuilder
    //   224: dup
    //   225: invokespecial 353	java/lang/StringBuilder:<init>	()V
    //   228: invokestatic 501	java/lang/System:currentTimeMillis	()J
    //   231: invokevirtual 504	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   234: invokevirtual 374	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   237: invokevirtual 508	java/lang/String:getBytes	()[B
    //   240: invokestatic 512	java/util/UUID:nameUUIDFromBytes	([B)Ljava/util/UUID;
    //   243: invokevirtual 449	java/util/UUID:toString	()Ljava/lang/String;
    //   246: invokestatic 515	cn/jpush/android/util/ai:b	(Ljava/lang/String;)Ljava/lang/String;
    //   249: astore_3
    //   250: aload_0
    //   251: getstatic 324	cn/jpush/android/util/a:z	[Ljava/lang/String;
    //   254: sipush 130
    //   257: aaload
    //   258: aload_3
    //   259: invokestatic 492	cn/jpush/android/util/ae:b	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)V
    //   262: aload_1
    //   263: invokevirtual 518	java/io/File:createNewFile	()Z
    //   266: pop
    //   267: new 520	java/io/FileOutputStream
    //   270: dup
    //   271: aload_1
    //   272: invokespecial 521	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   275: astore_2
    //   276: aload_2
    //   277: astore_0
    //   278: aload_2
    //   279: aload_3
    //   280: invokevirtual 508	java/lang/String:getBytes	()[B
    //   283: invokevirtual 525	java/io/FileOutputStream:write	([B)V
    //   286: aload_2
    //   287: astore_0
    //   288: aload_2
    //   289: invokevirtual 528	java/io/FileOutputStream:flush	()V
    //   292: aload_2
    //   293: astore_0
    //   294: invokestatic 495	cn/jpush/android/util/x:d	()V
    //   297: aload_3
    //   298: astore_1
    //   299: aload_2
    //   300: ifnull -264 -> 36
    //   303: aload_2
    //   304: invokevirtual 531	java/io/FileOutputStream:close	()V
    //   307: aload_3
    //   308: areturn
    //   309: astore_0
    //   310: aload_3
    //   311: areturn
    //   312: astore_0
    //   313: invokestatic 533	cn/jpush/android/util/x:j	()V
    //   316: aload_3
    //   317: areturn
    //   318: astore_0
    //   319: aconst_null
    //   320: astore_2
    //   321: aload_2
    //   322: astore_0
    //   323: invokestatic 533	cn/jpush/android/util/x:j	()V
    //   326: aload_3
    //   327: astore_1
    //   328: aload_2
    //   329: ifnull -293 -> 36
    //   332: aload_2
    //   333: invokevirtual 531	java/io/FileOutputStream:close	()V
    //   336: aload_3
    //   337: areturn
    //   338: astore_0
    //   339: aload_3
    //   340: areturn
    //   341: astore_1
    //   342: aconst_null
    //   343: astore_0
    //   344: aload_0
    //   345: ifnull +7 -> 352
    //   348: aload_0
    //   349: invokevirtual 531	java/io/FileOutputStream:close	()V
    //   352: aload_1
    //   353: athrow
    //   354: astore_0
    //   355: goto -3 -> 352
    //   358: astore_1
    //   359: goto -15 -> 344
    //   362: astore_0
    //   363: goto -42 -> 321
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	366	0	paramContext	Context
    //   28	300	1	localObject1	Object
    //   341	12	1	localObject2	Object
    //   358	1	1	localObject3	Object
    //   56	163	2	localObject4	Object
    //   220	1	2	localFileNotFoundException	FileNotFoundException
    //   275	58	2	localFileOutputStream	java.io.FileOutputStream
    //   249	91	3	str	String
    // Exception table:
    //   from	to	target	type
    //   153	218	220	java/io/FileNotFoundException
    //   303	307	309	java/io/IOException
    //   262	267	312	java/io/IOException
    //   267	276	318	java/io/IOException
    //   332	336	338	java/io/IOException
    //   267	276	341	finally
    //   348	352	354	java/io/IOException
    //   278	286	358	finally
    //   288	292	358	finally
    //   294	297	358	finally
    //   323	326	358	finally
    //   278	286	362	java/io/IOException
    //   288	292	362	java/io/IOException
    //   294	297	362	java/io/IOException
  }
  
  private static String B(Context paramContext)
  {
    if ((!a()) || (!c(paramContext, z[101])))
    {
      x.f();
      return null;
    }
    paramContext = new File(f);
    if (paramContext.exists()) {
      try
      {
        paramContext = m.a(new FileInputStream(paramContext));
        if (paramContext.size() > 0)
        {
          paramContext = (String)paramContext.get(0);
          new StringBuilder(z[126]).append(paramContext).toString();
          x.d();
          return paramContext;
        }
      }
      catch (FileNotFoundException paramContext)
      {
        return null;
      }
    }
    return null;
  }
  
  private static String C(Context paramContext)
  {
    Object localObject = j(paramContext);
    if ((!ai.a((String)localObject)) && (d((String)localObject))) {
      return localObject;
    }
    if (Build.VERSION.SDK_INT >= 9) {}
    for (String str = Build.SERIAL;; str = null)
    {
      if (!ai.a(str))
      {
        localObject = str;
        if (d(str)) {
          break;
        }
      }
      paramContext = i(paramContext, " ");
      if (!ai.a(paramContext))
      {
        localObject = paramContext;
        if (d(paramContext)) {
          break;
        }
      }
      return UUID.randomUUID().toString();
    }
  }
  
  private static boolean D(Context paramContext)
  {
    try
    {
      String str2 = Settings.System.getString(paramContext.getContentResolver(), z[97]);
      String str1 = str2;
      if (TextUtils.isEmpty(str2)) {
        str1 = i(paramContext, "");
      }
      Settings.System.putString(paramContext.getContentResolver(), z[97], str1);
      x.d();
      return true;
    }
    catch (Exception paramContext)
    {
      x.f();
    }
    return false;
  }
  
  private static boolean E(Context paramContext)
  {
    try
    {
      Intent localIntent = new Intent(z[127]);
      localIntent.setPackage(paramContext.getPackageName());
      localIntent.addCategory(z[122]);
      paramContext = paramContext.getPackageManager().resolveActivity(localIntent, 0).activityInfo;
      if (paramContext != null)
      {
        paramContext = Class.forName(paramContext.name);
        if (paramContext == null) {
          return false;
        }
        if (!InstrumentedActivity.class.isAssignableFrom(paramContext))
        {
          boolean bool = InstrumentedListActivity.class.isAssignableFrom(paramContext);
          if (!bool) {}
        }
        else
        {
          return true;
        }
      }
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  private static void F(Context paramContext)
  {
    
    if (j == null) {
      j = new PushReceiver();
    }
    paramContext.registerReceiver(j, new IntentFilter(z[120]));
    paramContext.registerReceiver(j, new IntentFilter(z[116]));
    try
    {
      IntentFilter localIntentFilter1 = new IntentFilter(z[118]);
      localIntentFilter1.addDataScheme(z[119]);
      IntentFilter localIntentFilter2 = new IntentFilter(z[117]);
      localIntentFilter2.addDataScheme(z[119]);
      IntentFilter localIntentFilter3 = new IntentFilter(z[80]);
      localIntentFilter3.setPriority(1000);
      localIntentFilter3.addCategory(paramContext.getPackageName());
      paramContext.registerReceiver(j, localIntentFilter1);
      paramContext.registerReceiver(j, localIntentFilter2);
      paramContext.registerReceiver(j, localIntentFilter3);
      return;
    }
    catch (Exception paramContext) {}
  }
  
  public static int a(byte paramByte)
  {
    return paramByte & 0xFF;
  }
  
  public static int a(Context paramContext, float paramFloat)
  {
    return (int)(paramContext.getResources().getDisplayMetrics().density * paramFloat);
  }
  
  public static Intent a(Context paramContext, d paramD, boolean paramBoolean)
  {
    Intent localIntent = new Intent();
    localIntent.putExtra(z[61], paramBoolean);
    localIntent.putExtra(z[60], paramD);
    localIntent.setAction(z[59]);
    localIntent.addCategory(paramContext.getPackageName());
    localIntent.addFlags(335544320);
    return localIntent;
  }
  
  public static String a(Context paramContext)
  {
    paramContext = paramContext.getResources().getDisplayMetrics();
    if (paramContext == null) {
      return z[68];
    }
    int k = paramContext.widthPixels;
    int m = paramContext.heightPixels;
    return k + "*" + m;
  }
  
  public static String a(Context paramContext, String paramString)
  {
    String str2 = Build.VERSION.RELEASE + "," + Integer.toString(Build.VERSION.SDK_INT);
    String str3 = Build.MODEL;
    String str4 = s.a(paramContext, z[6], z[1]);
    String str5 = Build.DEVICE;
    String str1;
    if (ai.a(cn.jpush.android.a.e))
    {
      str1 = " ";
      paramString = new StringBuilder().append(str2).append(z[44]).append(str3).append(z[44]).append(str4).append(z[44]).append(str5).append(z[44]).append(str1).append(z[44]).append(paramString).append(z[44]);
      str1 = paramContext.getApplicationInfo().sourceDir;
      if (!ai.a(str1)) {
        break label221;
      }
      x.f();
      k = 0;
      label183:
      if (k == 0) {
        break label289;
      }
    }
    label221:
    label289:
    for (int k = 1;; k = 0)
    {
      return k + z[44] + a(paramContext);
      str1 = cn.jpush.android.a.e;
      break;
      new StringBuilder(z[43]).append(str1).toString();
      x.c();
      if (str1.startsWith(z[46]))
      {
        k = 1;
        break label183;
      }
      if (str1.startsWith(z[45]))
      {
        k = 0;
        break label183;
      }
      x.d();
      k = 0;
      break label183;
    }
  }
  
  public static String a(String paramString)
  {
    int m = 0;
    try
    {
      Object localObject = MessageDigest.getInstance(z[51]);
      paramString = paramString.toCharArray();
      byte[] arrayOfByte = new byte[paramString.length];
      int k = 0;
      while (k < paramString.length)
      {
        arrayOfByte[k] = ((byte)paramString[k]);
        k += 1;
      }
      paramString = ((MessageDigest)localObject).digest(arrayOfByte);
      localObject = new StringBuffer();
      k = m;
      while (k < paramString.length)
      {
        m = paramString[k] & 0xFF;
        if (m < 16) {
          ((StringBuffer)localObject).append("0");
        }
        ((StringBuffer)localObject).append(Integer.toHexString(m));
        k += 1;
      }
      paramString = ((StringBuffer)localObject).toString();
      return paramString;
    }
    catch (Exception paramString)
    {
      x.c();
    }
    return "";
  }
  
  public static String a(byte[] paramArrayOfByte)
  {
    try
    {
      paramArrayOfByte = MessageDigest.getInstance(z[51]).digest(paramArrayOfByte);
      StringBuffer localStringBuffer = new StringBuffer();
      int k = 0;
      while (k < paramArrayOfByte.length)
      {
        int m = paramArrayOfByte[k] & 0xFF;
        if (m < 16) {
          localStringBuffer.append("0");
        }
        localStringBuffer.append(Integer.toHexString(m));
        k += 1;
      }
      paramArrayOfByte = localStringBuffer.toString();
      return paramArrayOfByte;
    }
    catch (Exception paramArrayOfByte)
    {
      x.c();
    }
    return "";
  }
  
  public static JSONObject a(String paramString, JSONArray paramJSONArray)
  {
    if ((paramJSONArray == null) || (paramJSONArray.length() == 0)) {
      return null;
    }
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put(z[29], paramJSONArray);
      localJSONObject.put(z[30], paramString);
      localJSONObject.put(z[31], PushService.m + Math.abs(System.currentTimeMillis() / 1000L - PushService.n));
      return localJSONObject;
    }
    catch (Exception paramString)
    {
      paramString.getMessage();
      x.f();
    }
    return null;
  }
  
  public static void a(Context paramContext, d paramD)
  {
    try
    {
      Intent localIntent = new Intent(z[18]);
      localIntent.putExtra(z[13], paramD.n);
      localIntent.putExtra(z[20], paramD.i);
      localIntent.putExtra(z[21], paramD.j);
      localIntent.putExtra(z[16], paramD.k);
      localIntent.putExtra(z[17], paramD.l);
      localIntent.putExtra(z[12], paramD.c);
      if (paramD.f()) {
        localIntent.putExtra(z[14], paramD.U);
      }
      localIntent.addCategory(paramD.m);
      paramContext.sendBroadcast(localIntent, String.format(z[19], new Object[] { paramD.m }));
      new StringBuilder(z[15]).append(String.format(z[19], new Object[] { paramD.m })).toString();
      x.d();
      ServiceInterface.a(paramD.c, 1018, paramContext);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.getMessage();
      x.f();
    }
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2, int paramInt)
  {
    Object localObject = Uri.parse(paramString2);
    if (localObject == null)
    {
      new StringBuilder(z[107]).append(paramString2).toString();
      x.c();
      return;
    }
    paramString2 = new Intent(z[32], (Uri)localObject);
    paramString2.setFlags(335544320);
    localObject = Intent.ShortcutIconResource.fromContext(paramContext, paramInt);
    Intent localIntent = new Intent(z[106]);
    localIntent.putExtra(z[104], false);
    localIntent.putExtra(z[109], paramString1);
    localIntent.putExtra(z[105], paramString2);
    localIntent.putExtra(z[108], (Parcelable)localObject);
    paramContext.sendBroadcast(localIntent);
  }
  
  public static boolean a()
  {
    boolean bool = Environment.getExternalStorageState().equals(z[66]);
    if (!bool) {
      x.c();
    }
    return bool;
  }
  
  private static boolean a(Context paramContext, Class<?> paramClass)
  {
    if (paramContext.getPackageManager().queryIntentServices(new Intent(paramContext, paramClass), 0).isEmpty())
    {
      x.e(z[58], z[110]);
      return false;
    }
    return true;
  }
  
  public static boolean a(Context paramContext, String paramString1, String paramString2)
  {
    if (paramContext == null) {
      throw new IllegalArgumentException(z[123]);
    }
    if (TextUtils.isEmpty(paramString1))
    {
      new StringBuilder(z[124]).append(paramString1).toString();
      x.e();
      return false;
    }
    Intent localIntent2 = l(paramContext, paramString1);
    Intent localIntent1 = localIntent2;
    if (localIntent2 == null)
    {
      try
      {
        if (TextUtils.isEmpty(paramString2))
        {
          x.c();
          return false;
        }
      }
      catch (Exception paramContext)
      {
        x.h();
        return false;
      }
      localIntent1 = new Intent();
      localIntent1.setClassName(paramString1, paramString2);
      localIntent1.addCategory(z[122]);
    }
    localIntent1.setFlags(268435456);
    paramContext.startActivity(localIntent1);
    return true;
  }
  
  public static boolean a(Context paramContext, String paramString, boolean paramBoolean)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    paramString = new Intent(paramString);
    paramString.addCategory(paramContext.getPackageName());
    return !localPackageManager.queryBroadcastReceivers(paramString, 0).isEmpty();
  }
  
  public static int b(byte[] paramArrayOfByte)
  {
    if ((paramArrayOfByte == null) || (paramArrayOfByte.length < 20)) {
      return -1;
    }
    return paramArrayOfByte[3] & 0xFF;
  }
  
  public static String b(Context paramContext, String paramString)
  {
    String str2 = Build.VERSION.RELEASE + "," + Integer.toString(Build.VERSION.SDK_INT);
    String str3 = Build.MODEL;
    String str4 = s.a(paramContext, z[6], z[1]);
    String str5 = Build.DEVICE;
    String str1;
    if (ai.a(cn.jpush.android.a.e)) {
      str1 = " ";
    }
    for (;;)
    {
      String str6 = d(paramContext);
      paramContext = new JSONObject();
      try
      {
        paramContext.put(z[4], str2);
        paramContext.put(z[5], str3);
        paramContext.put(z[1], str4);
        paramContext.put(z[3], str5);
        paramContext.put(z[0], str1);
        paramContext.put(z[7], str6);
        paramContext.put(z[2], paramString);
        return paramContext.toString();
        str1 = cn.jpush.android.a.e;
      }
      catch (JSONException paramString)
      {
        for (;;) {}
      }
    }
  }
  
  public static String b(String paramString)
  {
    try
    {
      paramString = MessageDigest.getInstance(z[51]).digest(paramString.getBytes(z['']));
      StringBuffer localStringBuffer = new StringBuffer();
      int k = 0;
      while (k < paramString.length)
      {
        int m = paramString[k] & 0xFF;
        if (m < 16) {
          localStringBuffer.append("0");
        }
        localStringBuffer.append(Integer.toHexString(m));
        k += 1;
      }
      paramString = localStringBuffer.toString();
      return paramString;
    }
    catch (Exception paramString)
    {
      x.c();
    }
    return "";
  }
  
  public static void b()
  {
    PowerManager.WakeLock localWakeLock = t.a().b();
    if ((localWakeLock == null) || (localWakeLock.isHeld())) {}
    try
    {
      localWakeLock.release();
      x.c();
      return;
    }
    catch (RuntimeException localRuntimeException)
    {
      for (;;)
      {
        new StringBuilder(z['']).append(localRuntimeException).toString();
        x.c();
      }
    }
  }
  
  public static void b(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      paramString1 = new Intent(paramString1);
      paramString1.putExtra(z[17], paramString2);
      paramString2 = paramContext.getPackageName();
      if (ai.a(paramString2)) {
        x.e(z[58], z[57]);
      }
      paramString1.addCategory(paramString2);
      paramContext.sendBroadcast(paramString1, String.format(z[19], new Object[] { paramString2 }));
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.getMessage();
      x.f();
    }
  }
  
  public static void b(Context paramContext, String paramString1, String paramString2, int paramInt)
  {
    Object localObject = new Intent(paramContext, PushReceiver.class);
    ((Intent)localObject).setAction(z[26]);
    ((Intent)localObject).putExtra(z[28], true);
    ((Intent)localObject).putExtra(z[48], paramString2);
    ((Intent)localObject).putExtra(z[30], paramInt);
    paramString2 = PendingIntent.getBroadcast(paramContext, 0, (Intent)localObject, 134217728);
    localObject = (NotificationManager)paramContext.getSystemService(z[23]);
    paramInt = -1;
    try
    {
      k = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 256).applicationInfo.icon;
      paramInt = k;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      int k;
      String str1;
      String str2;
      Notification localNotification;
      for (;;) {}
    }
    k = paramInt;
    if (paramInt < 0) {
      k = 17301586;
    }
    str1 = z[47];
    str2 = z[49];
    localNotification = new Notification(k, paramString1, System.currentTimeMillis());
    localNotification.flags = 34;
    localNotification.setLatestEventInfo(paramContext, str1, str2, paramString2);
    ((NotificationManager)localObject).notify(paramString1.hashCode(), localNotification);
  }
  
  public static boolean b(Context paramContext)
  {
    paramContext = ((ConnectivityManager)paramContext.getSystemService(z[42])).getActiveNetworkInfo();
    return (paramContext != null) && (paramContext.isConnected());
  }
  
  private static boolean b(Context paramContext, Class<?> paramClass)
  {
    if (paramContext.getPackageManager().queryBroadcastReceivers(new Intent(paramContext, paramClass), 0).isEmpty())
    {
      x.e(z[58], z[67]);
      return false;
    }
    return true;
  }
  
  private static boolean b(Context paramContext, String paramString, boolean paramBoolean)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    paramString = new Intent(paramString);
    if (paramBoolean) {
      paramString.addCategory(paramContext.getPackageName());
    }
    return !localPackageManager.queryIntentServices(paramString, 0).isEmpty();
  }
  
  public static int c(byte[] paramArrayOfByte)
  {
    int k = 0;
    int n = 0;
    int m = n;
    if (paramArrayOfByte != null)
    {
      if (paramArrayOfByte.length < 6) {
        m = n;
      }
    }
    else {
      return m;
    }
    n = 0;
    for (;;)
    {
      m = k;
      if (n >= 2) {
        break;
      }
      m = paramArrayOfByte[n];
      n += 1;
      k = (m & 0xFF) + (k << 8);
    }
  }
  
  public static String c()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    if (new File(z[99]).exists()) {
      try
      {
        BufferedReader localBufferedReader = new BufferedReader(new FileReader(new File(z[99])));
        for (;;)
        {
          String str = localBufferedReader.readLine();
          if (str == null) {
            break;
          }
          if (str.contains(z[98]))
          {
            int k = str.indexOf(":");
            if ((k >= 0) && (k < str.length() - 1)) {
              localStringBuffer.append(str.substring(k + 1).trim());
            }
          }
        }
        return localStringBuffer.toString();
      }
      catch (IOException localIOException) {}
    }
    for (;;)
    {
      localIOException.close();
    }
  }
  
  public static short c(Context paramContext)
  {
    return ae.b(paramContext);
  }
  
  public static void c(Context paramContext, String paramString1, String paramString2)
  {
    if (!h(paramContext)) {
      return;
    }
    x.c();
    Object localObject = new Intent(paramContext, PushReceiver.class);
    ((Intent)localObject).setAction(z[26]);
    ((Intent)localObject).putExtra(z[28], true);
    ((Intent)localObject).putExtra(z[24], paramString2);
    paramString2 = PendingIntent.getBroadcast(paramContext, 0, (Intent)localObject, 134217728);
    localObject = (NotificationManager)paramContext.getSystemService(z[23]);
    int k = -1;
    try
    {
      m = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 256).applicationInfo.icon;
      k = m;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      int m;
      String str1;
      String str2;
      Notification localNotification;
      for (;;) {}
    }
    m = k;
    if (k < 0) {
      m = 17301586;
    }
    str1 = z[27];
    str2 = z[25];
    localNotification = new Notification(m, paramString1, System.currentTimeMillis());
    localNotification.flags = 34;
    localNotification.setLatestEventInfo(paramContext, str1, str2, paramString2);
    ((NotificationManager)localObject).notify(paramString1.hashCode(), localNotification);
  }
  
  public static void c(String paramString)
  {
    b.add(paramString);
  }
  
  public static boolean c(Context paramContext, String paramString)
  {
    if ((paramContext == null) || (TextUtils.isEmpty(paramString))) {
      throw new IllegalArgumentException(z[38]);
    }
    return paramContext.getPackageManager().checkPermission(paramString, paramContext.getPackageName()) == 0;
  }
  
  public static String d()
  {
    try
    {
      InetAddress localInetAddress;
      do
      {
        localObject = NetworkInterface.getNetworkInterfaces();
        Enumeration localEnumeration;
        while (!localEnumeration.hasMoreElements())
        {
          if (!((Enumeration)localObject).hasMoreElements()) {
            break;
          }
          localEnumeration = ((NetworkInterface)((Enumeration)localObject).nextElement()).getInetAddresses();
        }
        localInetAddress = (InetAddress)localEnumeration.nextElement();
      } while ((localInetAddress.isLoopbackAddress()) || (!(localInetAddress instanceof Inet4Address)));
      Object localObject = localInetAddress.getHostAddress().toString();
      return localObject;
    }
    catch (Exception localException)
    {
      x.e();
      localException.printStackTrace();
    }
    return "";
  }
  
  public static String d(Context paramContext)
  {
    paramContext = ((ConnectivityManager)paramContext.getSystemService(z[42])).getActiveNetworkInfo();
    if (paramContext == null) {
      paramContext = z[125];
    }
    String str1;
    String str2;
    do
    {
      return paramContext;
      str1 = paramContext.getTypeName();
      str2 = paramContext.getSubtypeName();
      if (str1 == null) {
        return z[125];
      }
      paramContext = str1;
    } while (str2 == null);
    return str1 + "," + str2;
  }
  
  public static boolean d(Context paramContext, String paramString)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    paramContext = new ComponentName(paramContext.getPackageName(), paramString);
    try
    {
      localPackageManager.getReceiverInfo(paramContext, 128);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  private static boolean d(Context paramContext, String paramString1, String paramString2)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    paramString2 = new Intent(paramString2);
    paramString2.setPackage(paramContext.getPackageName());
    paramContext = localPackageManager.queryBroadcastReceivers(paramString2, 0).iterator();
    while (paramContext.hasNext())
    {
      paramString2 = ((ResolveInfo)paramContext.next()).activityInfo;
      if ((paramString2 != null) && (paramString2.name.equals(paramString1))) {
        return true;
      }
    }
    return false;
  }
  
  private static boolean d(String paramString)
  {
    if (ai.a(paramString)) {}
    while (paramString.length() < 10) {
      return false;
    }
    int k = 0;
    for (;;)
    {
      if (k >= b.size()) {
        break label74;
      }
      if ((paramString.equals(b.get(k))) || (paramString.startsWith((String)b.get(k)))) {
        break;
      }
      k += 1;
    }
    label74:
    return true;
  }
  
  public static String e(Context paramContext)
  {
    paramContext = ((ConnectivityManager)paramContext.getSystemService(z[42])).getActiveNetworkInfo();
    if (paramContext == null) {
      return "";
    }
    return paramContext.getTypeName().toUpperCase(Locale.getDefault());
  }
  
  public static void e(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent();
    localIntent.addFlags(268435456);
    localIntent.setAction(z[32]);
    localIntent.setDataAndType(Uri.fromFile(new File(paramString)), z[33]);
    paramContext.startActivity(localIntent);
  }
  
  private static boolean e(String paramString)
  {
    if (ai.a(paramString))
    {
      x.c();
      return true;
    }
    if (Pattern.compile(z[103]).matcher(paramString).matches())
    {
      x.c();
      return true;
    }
    return false;
  }
  
  public static void f(Context paramContext)
  {
    h(paramContext, null);
  }
  
  public static boolean f(Context paramContext, String paramString)
  {
    try
    {
      paramContext.getPackageManager().getApplicationInfo(paramString, 0);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  public static int g(Context paramContext)
  {
    paramContext = paramContext.registerReceiver(null, new IntentFilter(z[36]));
    int k = paramContext.getIntExtra(z[35], -1);
    if ((k == 2) || (k == 5)) {}
    for (k = 1; k == 0; k = 0) {
      return -1;
    }
    return paramContext.getIntExtra(z[34], -1);
  }
  
  public static String g(Context paramContext, String paramString)
  {
    if (!c(paramContext, z[40])) {}
    for (;;)
    {
      return paramString;
      try
      {
        paramContext = ((WifiManager)paramContext.getSystemService(z[41])).getConnectionInfo().getMacAddress();
        boolean bool = ai.a(paramContext);
        if (!bool) {
          return paramContext;
        }
      }
      catch (Exception paramContext)
      {
        x.j();
      }
    }
    return paramString;
  }
  
  public static void h(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent(z[127]);
    String str = paramContext.getPackageName();
    localIntent.setPackage(str);
    if (!ai.a(paramString)) {
      localIntent.putExtra(z[17], paramString);
    }
    localIntent.addCategory(z[122]);
    paramString = paramContext.getPackageManager().resolveActivity(localIntent, 0);
    new StringBuilder(z['']).append(paramString.activityInfo.name).toString();
    x.d();
    localIntent.setClassName(str, paramString.activityInfo.name);
    localIntent.setFlags(268435456);
    paramContext.startActivity(localIntent);
  }
  
  public static boolean h(Context paramContext)
  {
    boolean bool4 = false;
    boolean bool5 = false;
    bool1 = false;
    boolean bool2 = bool4;
    boolean bool3 = bool5;
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 64).signatures;
      bool2 = bool4;
      bool3 = bool5;
      CertificateFactory localCertificateFactory = CertificateFactory.getInstance(z[100]);
      int k = 0;
      for (;;)
      {
        bool2 = bool1;
        bool3 = bool1;
        if (k >= paramContext.length) {
          return bool1;
        }
        bool2 = bool1;
        bool3 = bool1;
        bool1 = ((X509Certificate)localCertificateFactory.generateCertificate(new ByteArrayInputStream(paramContext[k].toByteArray()))).getSubjectX500Principal().equals(c);
        bool2 = bool1;
        if (bool1) {
          break;
        }
        k += 1;
      }
      return bool1;
    }
    catch (Exception paramContext)
    {
      return bool2;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      return bool3;
    }
  }
  
  public static String i(Context paramContext)
  {
    String str = ae.a(paramContext, z[50], "");
    if ((!ai.a(str)) && (d(str))) {
      return str;
    }
    str = z(paramContext);
    ae.b(paramContext, z[50], str);
    return str;
  }
  
  public static String i(Context paramContext, String paramString)
  {
    if (c(paramContext, z[10])) {
      paramString = ((TelephonyManager)paramContext.getSystemService(z[11])).getDeviceId();
    }
    return paramString;
  }
  
  public static String j(Context paramContext)
  {
    return Settings.Secure.getString(paramContext.getContentResolver(), z[37]);
  }
  
  public static void j(Context paramContext, String paramString)
  {
    if (!ai.a(paramString))
    {
      o(paramContext, paramString);
      m(paramContext, paramString);
      ae.b(paramContext, z[8], paramString);
    }
  }
  
  public static String k(Context paramContext)
  {
    boolean bool1 = false;
    Object localObject = ae.a(paramContext, z[8], null);
    if (!ai.a((String)localObject))
    {
      a = b.d.ordinal();
      return localObject;
    }
    localObject = n(paramContext, (String)localObject);
    if (!ai.a((String)localObject))
    {
      a = b.b.ordinal();
      m(paramContext, (String)localObject);
      ae.b(paramContext, z[8], (String)localObject);
      return localObject;
    }
    localObject = B(paramContext);
    if (!ai.a((String)localObject))
    {
      a = b.c.ordinal();
      o(paramContext, (String)localObject);
      ae.b(paramContext, z[8], (String)localObject);
      return localObject;
    }
    boolean bool2 = c(paramContext, z[9]);
    int k;
    if ((c(paramContext, z[101])) && (a()))
    {
      k = 1;
      if ((bool2) || (k != 0)) {
        break label190;
      }
    }
    for (;;)
    {
      if (bool1) {
        break label211;
      }
      a = b.e.ordinal();
      return C(paramContext);
      k = 0;
      break;
      label190:
      if ((k == 0) && (bool2)) {
        bool1 = D(paramContext);
      } else {
        bool1 = true;
      }
    }
    label211:
    String str1 = i(paramContext, (String)localObject);
    String str2 = j(paramContext);
    String str3 = g(paramContext, "");
    localObject = UUID.randomUUID().toString();
    str1 = a(str1 + str2 + str3 + (String)localObject);
    if (ai.a(str1)) {}
    for (;;)
    {
      str1 = o(paramContext, (String)localObject);
      if ((!ai.a(m(paramContext, (String)localObject))) || (!ai.a(str1)))
      {
        new StringBuilder(z['']).append((String)localObject).toString();
        x.c();
        ae.b(paramContext, z[8], (String)localObject);
        a = b.a.ordinal();
        return localObject;
      }
      a = b.e.ordinal();
      x.c();
      return C(paramContext);
      localObject = str1;
    }
  }
  
  private static boolean k(Context paramContext, String paramString)
  {
    if ((paramContext == null) || (TextUtils.isEmpty(paramString))) {
      throw new IllegalArgumentException(z[38]);
    }
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext.getPermissionInfo(paramString, 128);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  private static Intent l(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getPackageManager();
      if (paramContext.getPackageInfo(paramString, 256) != null)
      {
        paramContext = paramContext.getLaunchIntentForPackage(paramString);
        return paramContext;
      }
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return null;
  }
  
  public static void l(Context paramContext)
  {
    paramContext = ((PowerManager)paramContext.getSystemService(z[''])).newWakeLock(1, z['']);
    paramContext.setReferenceCounted(false);
    t.a().a(paramContext);
    if (!t.a().b().isHeld()) {
      t.a().b().acquire();
    }
    x.c();
  }
  
  /* Error */
  private static String m(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: invokestatic 442	cn/jpush/android/util/a:a	()Z
    //   3: ifeq +16 -> 19
    //   6: aload_0
    //   7: getstatic 324	cn/jpush/android/util/a:z	[Ljava/lang/String;
    //   10: bipush 101
    //   12: aaload
    //   13: invokestatic 537	cn/jpush/android/util/a:c	(Landroid/content/Context;Ljava/lang/String;)Z
    //   16: ifne +7 -> 23
    //   19: aconst_null
    //   20: astore_0
    //   21: aload_0
    //   22: areturn
    //   23: new 361	java/io/File
    //   26: dup
    //   27: getstatic 376	cn/jpush/android/util/a:d	Ljava/lang/String;
    //   30: invokespecial 468	java/io/File:<init>	(Ljava/lang/String;)V
    //   33: astore_0
    //   34: aload_0
    //   35: invokevirtual 471	java/io/File:exists	()Z
    //   38: ifne +8 -> 46
    //   41: aload_0
    //   42: invokevirtual 1251	java/io/File:mkdir	()Z
    //   45: pop
    //   46: new 361	java/io/File
    //   49: dup
    //   50: getstatic 386	cn/jpush/android/util/a:f	Ljava/lang/String;
    //   53: invokespecial 468	java/io/File:<init>	(Ljava/lang/String;)V
    //   56: astore_0
    //   57: aload_0
    //   58: invokevirtual 471	java/io/File:exists	()Z
    //   61: ifeq +8 -> 69
    //   64: aload_0
    //   65: invokevirtual 1254	java/io/File:delete	()Z
    //   68: pop
    //   69: aload_0
    //   70: invokevirtual 518	java/io/File:createNewFile	()Z
    //   73: pop
    //   74: new 520	java/io/FileOutputStream
    //   77: dup
    //   78: aload_0
    //   79: invokespecial 521	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   82: astore_2
    //   83: aload_2
    //   84: astore_0
    //   85: aload_2
    //   86: aload_1
    //   87: invokevirtual 508	java/lang/String:getBytes	()[B
    //   90: invokevirtual 525	java/io/FileOutputStream:write	([B)V
    //   93: aload_2
    //   94: astore_0
    //   95: aload_2
    //   96: invokevirtual 528	java/io/FileOutputStream:flush	()V
    //   99: aload_2
    //   100: astore_0
    //   101: invokestatic 495	cn/jpush/android/util/x:d	()V
    //   104: aload_1
    //   105: astore_0
    //   106: aload_2
    //   107: ifnull -86 -> 21
    //   110: aload_2
    //   111: invokevirtual 531	java/io/FileOutputStream:close	()V
    //   114: aload_1
    //   115: areturn
    //   116: astore_0
    //   117: aload_1
    //   118: areturn
    //   119: astore_0
    //   120: invokestatic 533	cn/jpush/android/util/x:j	()V
    //   123: goto -77 -> 46
    //   126: astore_0
    //   127: aconst_null
    //   128: areturn
    //   129: astore_0
    //   130: invokestatic 533	cn/jpush/android/util/x:j	()V
    //   133: aconst_null
    //   134: areturn
    //   135: astore_0
    //   136: aconst_null
    //   137: astore_1
    //   138: aload_1
    //   139: astore_0
    //   140: invokestatic 533	cn/jpush/android/util/x:j	()V
    //   143: aload_1
    //   144: ifnull +7 -> 151
    //   147: aload_1
    //   148: invokevirtual 531	java/io/FileOutputStream:close	()V
    //   151: aconst_null
    //   152: areturn
    //   153: astore_1
    //   154: aconst_null
    //   155: astore_0
    //   156: aload_0
    //   157: ifnull +7 -> 164
    //   160: aload_0
    //   161: invokevirtual 531	java/io/FileOutputStream:close	()V
    //   164: aload_1
    //   165: athrow
    //   166: astore_0
    //   167: goto -16 -> 151
    //   170: astore_0
    //   171: goto -7 -> 164
    //   174: astore_1
    //   175: goto -19 -> 156
    //   178: astore_0
    //   179: aload_2
    //   180: astore_1
    //   181: goto -43 -> 138
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	184	0	paramContext	Context
    //   0	184	1	paramString	String
    //   82	98	2	localFileOutputStream	java.io.FileOutputStream
    // Exception table:
    //   from	to	target	type
    //   110	114	116	java/io/IOException
    //   41	46	119	java/lang/Exception
    //   64	69	126	java/lang/SecurityException
    //   69	74	129	java/io/IOException
    //   74	83	135	java/io/IOException
    //   74	83	153	finally
    //   147	151	166	java/io/IOException
    //   160	164	170	java/io/IOException
    //   85	93	174	finally
    //   95	99	174	finally
    //   101	104	174	finally
    //   140	143	174	finally
    //   85	93	178	java/io/IOException
    //   95	99	178	java/io/IOException
    //   101	104	178	java/io/IOException
  }
  
  public static JSONArray m(Context paramContext)
  {
    Object localObject1 = new ArrayList();
    Object localObject2 = paramContext.getPackageManager().getInstalledPackages(0);
    int k = 0;
    Object localObject3;
    while (k < ((List)localObject2).size())
    {
      localObject3 = (PackageInfo)((List)localObject2).get(k);
      y localY = new y();
      localY.a = ((PackageInfo)localObject3).applicationInfo.loadLabel(paramContext.getPackageManager()).toString();
      localY.b = ((PackageInfo)localObject3).packageName;
      localY.c = ((PackageInfo)localObject3).versionName;
      localY.d = ((PackageInfo)localObject3).versionCode;
      ((ArrayList)localObject1).add(localY);
      k += 1;
    }
    paramContext = new JSONArray();
    try
    {
      localObject1 = ((ArrayList)localObject1).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (y)((Iterator)localObject1).next();
        localObject3 = new JSONObject();
        ((JSONObject)localObject3).put(z[''], ((y)localObject2).a);
        ((JSONObject)localObject3).put(z[''], ((y)localObject2).b);
        ((JSONObject)localObject3).put(z[''], ((y)localObject2).c);
        ((JSONObject)localObject3).put(z[''], ((y)localObject2).d);
        paramContext.put(localObject3);
      }
      return paramContext;
    }
    catch (JSONException localJSONException) {}
  }
  
  private static String n(Context paramContext, String paramString)
  {
    String str = paramString;
    if (c(paramContext, z[9])) {}
    try
    {
      str = Settings.System.getString(paramContext.getContentResolver(), z[8]);
      return str;
    }
    catch (Exception paramContext)
    {
      x.f();
    }
    return paramString;
  }
  
  public static boolean n(Context paramContext)
  {
    boolean bool2 = false;
    try
    {
      if (ae.a(paramContext, z[64], "0").equals("1"))
      {
        x.d();
        return false;
      }
      paramContext = ae.a(paramContext, z[65], "");
      if (ai.a(paramContext))
      {
        x.d();
        return true;
      }
      new StringBuilder(z[63]).append(paramContext).toString();
      x.d();
      Object localObject = paramContext.split("_");
      paramContext = localObject[0];
      localObject = localObject[1];
      paramContext = paramContext.toCharArray();
      localObject = ((String)localObject).split(z[62]);
      Calendar localCalendar = Calendar.getInstance();
      int m = localCalendar.get(7);
      int n = localCalendar.get(11);
      int i1 = paramContext.length;
      int k = 0;
      boolean bool1;
      for (;;)
      {
        bool1 = bool2;
        if (k >= i1) {
          break;
        }
        if (m == Integer.valueOf(String.valueOf(paramContext[k])).intValue() + 1)
        {
          int i2 = Integer.valueOf(localObject[0]).intValue();
          int i3 = Integer.valueOf(localObject[1]).intValue();
          if ((n >= i2) && (n <= i3)) {
            return true;
          }
        }
        k += 1;
      }
      return bool1;
    }
    catch (Exception paramContext)
    {
      bool1 = true;
    }
  }
  
  private static String o(Context paramContext, String paramString)
  {
    if (c(paramContext, z[9])) {
      try
      {
        boolean bool = Settings.System.putString(paramContext.getContentResolver(), z[8], paramString);
        if (bool) {
          return paramString;
        }
      }
      catch (Exception paramContext)
      {
        x.f();
      }
    }
    return null;
  }
  
  public static boolean o(Context paramContext)
  {
    paramContext = ae.a(paramContext, z[56], "");
    if (TextUtils.isEmpty(paramContext)) {}
    for (;;)
    {
      return false;
      try
      {
        paramContext = new JSONObject(paramContext);
        int k = paramContext.optInt(z[54], -1);
        int m = paramContext.optInt(z[53], -1);
        int n = paramContext.optInt(z[55], -1);
        int i1 = paramContext.optInt(z[52], -1);
        if ((k >= 0) && (m >= 0) && (n >= 0) && (i1 >= 0) && (m <= 59) && (i1 <= 59) && (n <= 23) && (k <= 23))
        {
          paramContext = Calendar.getInstance();
          int i2 = paramContext.get(11);
          int i3 = paramContext.get(12);
          if (k < n)
          {
            if (((i2 > k) && (i2 < n)) || ((i2 == k) && (i3 >= m)) || ((i2 == n) && (i3 <= i1))) {
              return true;
            }
          }
          else if (k == n)
          {
            if ((i2 == k) && (i3 >= m) && (i3 <= i1)) {
              return true;
            }
          }
          else if ((k > n) && (((i2 > k) && (i2 <= 23)) || ((i2 >= 0) && (i2 < n)) || ((i2 == k) && (i3 >= m)) || ((i2 == n) && (i3 <= i1)))) {
            return true;
          }
        }
      }
      catch (JSONException paramContext) {}
    }
    return false;
  }
  
  public static boolean p(Context paramContext)
  {
    x.b(z[58], z[88]);
    int k;
    if (!a(paramContext, PushService.class))
    {
      x.e(z[58], z[82] + PushService.class.getCanonicalName());
      k = 0;
      if (ai.a(cn.jpush.android.a.f)) {
        break label1007;
      }
    }
    for (int m = 1;; m = 0)
    {
      if ((!cn.jpush.android.a.i) && (!E(paramContext))) {
        x.d(z[58], z[76]);
      }
      if ((m == 0) || (k == 0)) {
        break label1027;
      }
      return true;
      if (!a(paramContext, DaemonService.class))
      {
        x.d(z[58], z[82] + DaemonService.class.getCanonicalName());
        cn.jpush.android.a.k = false;
      }
      for (;;)
      {
        if (b(paramContext, z[95], false)) {
          break label235;
        }
        x.e(z[58], z[78]);
        k = 0;
        break;
        if (!b(paramContext, z[75], true))
        {
          x.d(z[58], z[84]);
          cn.jpush.android.a.k = false;
        }
        else
        {
          cn.jpush.android.a.k = true;
        }
      }
      label235:
      if (!b(paramContext, z[89], false))
      {
        x.e(z[58], z[91]);
        k = 0;
        break;
      }
      if (!a(paramContext, DownloadService.class))
      {
        x.e(z[58], z[82] + DownloadService.class.getCanonicalName());
        k = 0;
        break;
      }
      if (!b(paramContext, PushReceiver.class))
      {
        x.e(z[58], z[79] + PushReceiver.class.getCanonicalName());
        F(paramContext);
      }
      for (;;)
      {
        k = 1;
        break;
        if (d(paramContext, PushReceiver.class.getCanonicalName(), z[73])) {
          x.d(z[58], z[93]);
        }
        if (!a(paramContext, z[80], true))
        {
          x.e(z[58], z[86]);
          k = 0;
          break;
        }
        if (!b(paramContext, AlarmReceiver.class))
        {
          x.e(z[58], z[79] + AlarmReceiver.class.getCanonicalName());
          k = 0;
          break;
        }
        if (paramContext.getPackageManager().queryIntentActivities(new Intent(paramContext, PushActivity.class), 0).isEmpty()) {
          x.e(z[58], z[90]);
        }
        for (k = 0;; k = 1)
        {
          if (k != 0) {
            break label580;
          }
          x.e(z[58], z[92] + PushActivity.class.getCanonicalName());
          k = 0;
          break;
        }
        label580:
        Object localObject2 = z[59];
        Object localObject1 = paramContext.getPackageManager();
        localObject2 = new Intent((String)localObject2);
        ((Intent)localObject2).addCategory(paramContext.getPackageName());
        if (((PackageManager)localObject1).queryIntentActivities((Intent)localObject2, 0).isEmpty()) {}
        for (k = 0;; k = 1)
        {
          if (k != 0) {
            break label660;
          }
          x.e(z[58], z[83]);
          k = 0;
          break;
        }
        label660:
        localObject1 = paramContext.getPackageName() + z[94];
        if (!k(paramContext, (String)localObject1))
        {
          x.e(z[58], z[74] + (String)localObject1);
          k = 0;
          break;
        }
        g.add(localObject1);
        localObject1 = g.iterator();
        for (;;)
        {
          if (((Iterator)localObject1).hasNext())
          {
            localObject2 = (String)((Iterator)localObject1).next();
            if (!c(paramContext.getApplicationContext(), (String)localObject2))
            {
              x.e(z[58], z[81] + (String)localObject2);
              k = 0;
              break;
            }
          }
        }
        if ((h(paramContext)) && (!c(paramContext.getApplicationContext(), z[9])))
        {
          x.e(z[58], z[96]);
          k = 0;
          break;
        }
        localObject1 = h.iterator();
        while (((Iterator)localObject1).hasNext())
        {
          localObject2 = (String)((Iterator)localObject1).next();
          if (!c(paramContext.getApplicationContext(), (String)localObject2)) {
            x.d(z[58], z[87] + (String)localObject2);
          }
        }
        localObject1 = i.iterator();
        while (((Iterator)localObject1).hasNext())
        {
          localObject2 = (String)((Iterator)localObject1).next();
          if (!c(paramContext.getApplicationContext(), (String)localObject2)) {
            x.d(z[58], z[87] + (String)localObject2 + z[77]);
          }
        }
      }
      label1007:
      x.d(z[58], z[85]);
    }
    label1027:
    return false;
  }
  
  public static void q(Context paramContext)
  {
    if ((j != null) && (!d(paramContext, PushReceiver.class.getCanonicalName()))) {}
    try
    {
      paramContext.unregisterReceiver(j);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.getMessage();
      x.f();
    }
  }
  
  public static long r(Context paramContext)
  {
    return PushService.a(paramContext);
  }
  
  public static String s(Context paramContext)
  {
    return PushService.b(paramContext);
  }
  
  public static String t(Context paramContext)
  {
    return ae.a(paramContext, z[121], "");
  }
  
  public static String u(Context paramContext)
  {
    String str = cn.jpush.android.a.f;
    Object localObject = str;
    if (ai.a(str)) {
      localObject = paramContext.getPackageManager();
    }
    try
    {
      paramContext = ((PackageManager)localObject).getApplicationInfo(paramContext.getPackageName(), 128);
      localObject = str;
      if (paramContext != null)
      {
        localObject = str;
        if (paramContext.metaData != null) {
          localObject = paramContext.metaData.getString(z['']);
        }
      }
      return localObject;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return str;
  }
  
  public static String v(Context paramContext)
  {
    paramContext = ((ConnectivityManager)paramContext.getSystemService(z[42])).getActiveNetworkInfo();
    if (paramContext == null) {
      return z[72];
    }
    if (paramContext.getType() == 1) {
      return z[41];
    }
    if (paramContext.getType() == 0)
    {
      int k = paramContext.getSubtype();
      if ((k == 4) || (k == 1) || (k == 2)) {
        return z[70];
      }
      if ((k == 3) || (k == 8) || (k == 6) || (k == 5) || (k == 12)) {
        return z[71];
      }
      if (k == 13) {
        return z[69];
      }
    }
    return "";
  }
  
  public static boolean w(Context paramContext)
  {
    String str3 = i(paramContext, "");
    String str2 = j(paramContext);
    String str1 = str2;
    if (ai.a(str2)) {
      str1 = " ";
    }
    str2 = ae.a(paramContext, z[112], z[114]);
    String str4 = ae.a(paramContext, z[113], z[114]);
    if ((z[114].equals(str2)) && (z[114].equals(str4)))
    {
      x.c();
      return false;
    }
    if ((e(str3)) || (e(str2)))
    {
      str3 = g(paramContext, " ");
      str2 = str3;
      if (ai.a(str3)) {
        str2 = " ";
      }
      str3 = ae.a(paramContext, z[111], z[114]);
      if ((str1.equals(str4)) && (str2.equals(str3))) {
        return false;
      }
      x.c();
    }
    for (;;)
    {
      x.b(z[58], z[115]);
      ae.b(paramContext, z[8], "");
      m(paramContext, "");
      o(paramContext, "");
      return true;
      if ((str3.equals(str2)) && (str1.equals(str4))) {
        return false;
      }
      x.c();
    }
  }
  
  public static List<ComponentName> x(Context paramContext)
  {
    x.c();
    ArrayList localArrayList = new ArrayList();
    paramContext = paramContext.getPackageManager();
    Object localObject = new Intent();
    ((Intent)localObject).setAction(z[75]);
    paramContext = paramContext.queryIntentServices((Intent)localObject, 0);
    if ((paramContext == null) || (paramContext.size() == 0)) {
      return null;
    }
    int k = 0;
    while (k < paramContext.size())
    {
      localObject = ((ResolveInfo)paramContext.get(k)).serviceInfo;
      String str1 = ((ServiceInfo)localObject).name;
      String str2 = ((ServiceInfo)localObject).packageName;
      if (((ServiceInfo)localObject).exported)
      {
        new StringBuilder(z[102]).append(str2).append("/").append(str1).append("}").toString();
        x.c();
        localArrayList.add(new ComponentName(str2, str1));
      }
      k += 1;
    }
    return localArrayList;
  }
  
  private static String y(Context paramContext)
  {
    if (!c(paramContext, z[40])) {
      return null;
    }
    try
    {
      paramContext = ((WifiManager)paramContext.getSystemService(z[41])).getConnectionInfo().getMacAddress();
      if ((paramContext != null) && (!paramContext.equals("")))
      {
        new StringBuilder(z[39]).append(paramContext).toString();
        x.d();
        paramContext = a(paramContext + Build.MODEL);
        return paramContext;
      }
    }
    catch (Exception paramContext)
    {
      x.j();
      return null;
    }
    return null;
  }
  
  private static String z(Context paramContext)
  {
    try
    {
      Object localObject = i(paramContext, "");
      if (d((String)localObject)) {
        return localObject;
      }
      String str2 = j(paramContext);
      if ((!ai.a(str2)) && (d(str2)))
      {
        localObject = str2;
        if (!z[22].equals(str2.toLowerCase(Locale.getDefault()))) {}
      }
      else
      {
        str2 = y(paramContext);
        if (!ai.a(str2))
        {
          localObject = str2;
          if (d(str2)) {}
        }
        else
        {
          str2 = A(paramContext);
          localObject = str2;
          if (str2 == null) {
            return " ";
          }
        }
      }
    }
    catch (Exception localException)
    {
      x.j();
      String str1 = A(paramContext);
      return str1;
    }
  }
  
  private static String z(char[] paramArrayOfChar)
  {
    int i1 = paramArrayOfChar.length;
    int m = 0;
    int k = 0;
    char[] arrayOfChar = paramArrayOfChar;
    if (i1 <= 1) {}
    label120:
    do
    {
      int n = k;
      arrayOfChar = paramArrayOfChar;
      m = k;
      int i2 = arrayOfChar[m];
      switch (n % 5)
      {
      default: 
        k = 72;
      }
      for (;;)
      {
        arrayOfChar[m] = ((char)(k ^ i2));
        m = n + 1;
        if (i1 != 0) {
          break label120;
        }
        arrayOfChar = paramArrayOfChar;
        n = m;
        m = i1;
        break;
        k = 65;
        continue;
        k = 56;
        continue;
        k = 109;
        continue;
        k = 123;
      }
      arrayOfChar = paramArrayOfChar;
      k = m;
      paramArrayOfChar = arrayOfChar;
    } while (i1 > m);
    return new String(arrayOfChar).intern();
  }
  
  private static char[] z(String paramString)
  {
    paramString = paramString.toCharArray();
    String str = paramString;
    for (;;)
    {
      int k = paramString.length;
      if (k < 2)
      {
        if (k != 0) {
          str[0] = ((char)(str[0] ^ 0x48));
        }
      }
      else {
        return str;
      }
    }
  }
}
