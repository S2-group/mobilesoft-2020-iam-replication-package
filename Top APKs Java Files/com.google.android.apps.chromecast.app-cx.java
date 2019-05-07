import android.accounts.Account;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Application;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ServiceInfo;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.content.res.Resources.Theme;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.media.MediaDescription;
import android.media.MediaDescription.Builder;
import android.media.MediaMetadata;
import android.media.MediaRouter;
import android.media.MediaRouter.Callback;
import android.media.MediaRouter.RouteCategory;
import android.media.MediaRouter.RouteInfo;
import android.media.MediaRouter.UserRouteInfo;
import android.media.MediaRouter.VolumeCallback;
import android.media.Rating;
import android.media.session.MediaController;
import android.media.session.MediaController.Callback;
import android.media.session.MediaController.TransportControls;
import android.media.session.MediaSession.Token;
import android.media.session.PlaybackState;
import android.net.Uri;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Messenger;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.PowerManager.WakeLock;
import android.os.Process;
import android.os.SystemClock;
import android.provider.Settings.Secure;
import android.support.v7.app.MediaRouteVolumeSlider;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SubMenu;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.AccessibilityDelegate;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewParent;
import android.view.ViewPropertyAnimator;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction;
import android.view.accessibility.AccessibilityNodeInfo.CollectionInfo;
import android.view.accessibility.AccessibilityNodeInfo.CollectionItemInfo;
import android.view.accessibility.AccessibilityRecord;
import android.view.animation.Interpolator;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EdgeEffect;
import android.widget.OverScroller;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.apps.chromecast.app.HelpActivity;
import com.google.android.apps.chromecast.app.SetupApplication;
import com.google.android.apps.chromecast.app.setup.DeviceSetupActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Proxy.Type;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.security.auth.x500.X500Principal;
import org.apache.http.impl.cookie.DateParseException;
import org.apache.http.impl.cookie.DateUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;

@cyd
public class cx
  implements b
{
  public static final int A = 2130772252;
  public static final int B = 2130772250;
  public static final int C = 2130772254;
  public static final int D = 2130772207;
  public static final int E = 2130771973;
  public static final int F = 2130772239;
  public static final int G = 2130772269;
  public static final int H = 2130772213;
  public static final int I = 2130772229;
  public static final int J = 2130772240;
  public static final int K = 2130772245;
  public static final int L = 2130772225;
  public static final int M = 2130772270;
  public static final int N = 2130772271;
  public static final int O = 2130772233;
  public static final int P = 2130772272;
  public static final int Q = 2130772273;
  public static final int R = 2130772274;
  public static final int S = 2130772034;
  public static final int T = 2130772232;
  public static final int U = 2130772224;
  public static final int V = 2130772223;
  public static final int W = 2131427328;
  public static final int X = 2131427329;
  public static final int Y = 2131427333;
  public static final int Z = 2131427335;
  public static Field a;
  public static final int aA = 2130837535;
  public static final int aB = 2130837536;
  public static final int aC = 2130837537;
  public static final int aD = 2130837540;
  public static final int aE = 2130837551;
  public static final int aF = 2130837552;
  public static final int aG = 2130837553;
  public static final int aH = 2130837559;
  public static final int aI = 2130837560;
  public static final int aJ = 2130837561;
  public static final int aK = 2130837562;
  public static final int aL = 2130837563;
  public static final int aM = 2130837564;
  public static final int aN = 2130837565;
  public static final int aO = 2130837567;
  public static final int aP = 2130837568;
  public static final int aQ = 2130837569;
  public static final int aR = 2130837570;
  public static final int aS = 2130837571;
  public static final int aT = 2130837572;
  public static final int aU = 2131624050;
  public static final int aV = 2131623936;
  public static final int aW = 2131624049;
  public static final int aX = 2131624019;
  public static final int aY = 2131624018;
  public static final int aZ = 2131624051;
  public static final int aa = 2131492864;
  public static final int ab = 2131558408;
  public static final int ac = 2131558409;
  public static final int ad = 2131558420;
  public static final int ae = 2131558435;
  public static final int af = 2131558436;
  public static final int ag = 2131558444;
  public static final int ah = 2130837504;
  public static final int ai = 2130837506;
  public static final int aj = 2130837507;
  public static final int ak = 2130837510;
  public static final int al = 2130837511;
  public static final int am = 2130837512;
  public static final int an = 2130837519;
  public static final int ao = 2130837520;
  public static final int ap = 2130837521;
  public static final int aq = 2130837525;
  public static final int ar = 2130837526;
  public static final int as = 2130837527;
  public static final int at = 2130837528;
  public static final int au = 2130837529;
  public static final int av = 2130837530;
  public static final int aw = 2130837531;
  public static final int ax = 2130837532;
  public static final int ay = 2130837533;
  public static final int az = 2130837534;
  public static boolean b = false;
  public static final int bA = 2131624063;
  public static final int bB = 2131624043;
  public static final int bC = 2131624029;
  public static final int bD = 2131623946;
  public static final int bE = 2131624061;
  public static final int bF = 2131624037;
  public static final int bG = 2131624027;
  public static final int bH = 2131624032;
  public static final int bI = 2131624031;
  public static final int bJ = 2131689474;
  public static final int bK = 2130968576;
  public static final int bL = 2130968579;
  public static final int bM = 2130968580;
  public static final int bN = 2130968582;
  public static final int bO = 2130968583;
  public static final int bP = 2130968584;
  public static final int bQ = 2130968587;
  public static final int bR = 2130968588;
  public static final int bS = 2130968589;
  public static final int bT = 2130968590;
  public static final int bU = 2130968591;
  public static final int bV = 2130968592;
  public static final int bW = 2130968593;
  public static final int bX = 2130968595;
  public static final int bY = 2130968596;
  public static final int bZ = 2130968597;
  public static final int ba = 2131624046;
  public static final int bb = 2131624020;
  public static final int bc = 2131624021;
  public static final int bd = 2131624033;
  public static final int be = 2131624028;
  public static final int bf = 2131624034;
  public static final int bg = 2131624040;
  public static final int bh = 2131624039;
  public static final int bi = 2131624048;
  public static final int bj = 2131624024;
  public static final int bk = 2131624052;
  public static final int bl = 2131624022;
  public static final int bm = 2131624026;
  public static final int bn = 2131624023;
  public static final int bo = 2131624025;
  public static final int bp = 2131624030;
  public static final int bq = 2131624038;
  public static final int br = 2131624035;
  public static final int bs = 2131624036;
  public static final int bt = 2131624055;
  public static final int bu = 2131624060;
  public static final int bv = 2131624056;
  public static final int bw = 2131624062;
  public static final int bx = 2131624057;
  public static final int by = 2131624058;
  public static final int bz = 2131624059;
  public static Field c;
  public static final int cA = 2131624377;
  public static final int cB = 2131624383;
  public static final int cC = 2131624389;
  public static final int cD = 2131624394;
  public static final int cE = 2131624396;
  public static final int cF = 2131624395;
  public static final int cG = 2131624384;
  public static final int cH = 2131624385;
  public static final int cI = 2131624380;
  public static final int cJ = 2131624379;
  public static final int cK = 2131624397;
  public static final int cL = 2131624387;
  public static final int cM = 2131624382;
  public static final int cN = 2131624388;
  public static final int cO = 2131624390;
  public static final int cP = 2131624391;
  public static final int cQ = 2131624392;
  public static final int cR = 2131624393;
  public static final int cS = 2131689493;
  public static final int cT = 2131165184;
  public static final int cU = 2131165185;
  public static final int cV = 2130968711;
  public static final int cW = 2130968712;
  public static final int cX = 2130968713;
  public static final int cY = 2130968714;
  public static final int cZ = 2131820906;
  public static final int ca = 2130968598;
  public static final int cb = 2130968599;
  public static final int cc = 2131820547;
  public static final int cd = 2131820550;
  public static final int ce = 2131820551;
  public static final int cf = 2131820560;
  public static final int cg = 2131820561;
  public static final int ch = 2131886208;
  public static final int ci = 2131886415;
  public static final int cj = 2131886420;
  public static final int ck = 2131492897;
  public static final int cl = 2131492898;
  public static final int cm = 2131558491;
  public static final int cn = 2131886257;
  public static final int co = 2131558565;
  public static final int cp = 2131558566;
  public static final int cq = 2131558567;
  public static final int cr = 2131558568;
  public static final int cs = 2131558569;
  public static final int ct = 2131558570;
  public static final int cu = 2130837723;
  public static final int cv = 2130837743;
  public static final int cw = 2131624386;
  public static final int cx = 2131624375;
  public static final int cy = 2131624378;
  public static final int cz = 2131624376;
  public static boolean d = false;
  public static final int dA = 2130837740;
  public static final int dB = 2131624182;
  public static final int dC = 2131624179;
  public static final int dD = 2131624184;
  public static final int dE = 2131624183;
  public static final int dF = 2131624181;
  public static final int dG = 2131624180;
  public static final int dH = 2131689480;
  public static final int dI = 2130968641;
  public static final int dJ = 2130968642;
  public static final int dK = 2130968710;
  public static final int dL = 2131820564;
  public static final int dM = 2131820565;
  public static final int dN = 2131820602;
  public static final int dO = 2131820686;
  public static final int dP = 2131820687;
  public static final int dQ = 2131820828;
  public static final int dR = 2130837610;
  public static final int dS = 2131624185;
  public static final int dT = 2131624186;
  public static final int dU = 2130968643;
  public static final int dV = 2130968644;
  public static final int dW = 2131820694;
  public static final int dX = 2131820695;
  public static final int dY = 2131820698;
  public static volatile String dZ;
  public static final int da = 2131820907;
  public static final int db = 2131820909;
  public static final int dc = 2131820910;
  public static final int dd = 2131820911;
  public static final int de = 2131820912;
  public static final int df = 2131820913;
  public static final int dg = 2131820914;
  public static final int dh = 2131820915;
  public static final int di = 2131820916;
  public static final int dj = 2131820917;
  public static final int dk = 2131820918;
  public static final int dl = 2131886429;
  public static final int dm = 2131886430;
  public static final int dn = 2131886432;
  public static final int jdField_do = 2131886431;
  public static final int dp = 2131492901;
  public static final int dq = 2131492902;
  public static final int dr = 2131492908;
  public static final int ds = 2131558496;
  public static final int dt = 2131558499;
  public static final int du = 2131558500;
  public static final int dv = 2131558501;
  public static final int dw = 2131558502;
  public static final int dx = 2131558505;
  public static final int dy = 2131558556;
  public static final int dz = 2130837700;
  public static Method e;
  public static final int eA = 2131558474;
  public static final int eB = 2131558485;
  public static final int eC = 2131558486;
  public static final int eD = 2131558487;
  public static final int eE = 2131558489;
  public static final int eF = 2131558507;
  public static final int eG = 2131558527;
  public static final int eH = 2131558528;
  public static final int eI = 2131558530;
  public static final int eJ = 2131558532;
  public static final int eK = 2131558542;
  public static final int eL = 2131558546;
  public static final int eM = 2131558547;
  public static final int eN = 2131558548;
  public static final int eO = 2131558558;
  public static final int eP = 2131558561;
  public static final int eQ = 2131558562;
  public static final int eR = 2131558564;
  public static final int eS = 2131558576;
  public static final int eT = 2131558577;
  public static final int eU = 2131558578;
  public static final int eV = 2131558581;
  public static final int eW = 2131558587;
  public static final int eX = 2131558590;
  public static final int eY = 2131558591;
  public static final int eZ = 2131558592;
  public static final int ea = 2131034112;
  public static final int eb = 2131034113;
  public static final int ec = 2131034118;
  public static final int ed = 2131361792;
  public static final int ee = 2131361793;
  public static final int ef = 2130772248;
  public static final int eg = 2131492899;
  public static final int eh = 2131492900;
  public static final int ei = 2131492954;
  public static final int ej = 2131492980;
  public static final int ek = 2131492983;
  public static final int el = 2131492992;
  public static final int em = 2131492999;
  public static final int en = 2131493000;
  public static final int eo = 2131493003;
  public static final int ep = 2131493011;
  public static final int eq = 2131493026;
  public static final int er = 2131493028;
  public static final int es = 2131493034;
  public static final int et = 2131493035;
  public static final int eu = 2131493036;
  public static final int ev = 2131493037;
  public static final int ew = 2131558468;
  public static final int ex = 2131558469;
  public static final int ey = 2131558470;
  public static final int ez = 2131558471;
  public static final int f = 2130772182;
  public static final int fA = 2130837660;
  public static final int fB = 2130837661;
  public static final int fC = 2130837667;
  public static final int fD = 2130837673;
  public static final int fE = 2130837675;
  public static final int fF = 2130837676;
  public static final int fG = 2130837679;
  public static final int fH = 2130837680;
  public static final int fI = 2130837684;
  public static final int fJ = 2130837685;
  public static final int fK = 2130837686;
  public static final int fL = 2130837718;
  public static final int fM = 2130837722;
  public static final int fN = 2130837774;
  public static final int fO = 2130837775;
  public static final int fP = 2130837777;
  public static final int fQ = 2130837780;
  public static final int fR = 2130837781;
  public static final int fS = 2130837782;
  public static final int fT = 2130837791;
  public static final int fU = 2130837795;
  public static final int fV = 2130837797;
  public static final int fW = 2130837798;
  public static final int fX = 2130837801;
  public static final int fY = 2130837805;
  public static final int fZ = 2130837806;
  public static final int fa = 2131558596;
  public static final int fb = 2131558597;
  public static final int fc = 2131558598;
  public static final int fd = 2131558605;
  public static final int fe = 2131558612;
  public static final int ff = 2131558613;
  public static final int fg = 2131558614;
  public static final int fh = 2131558622;
  public static final int fi = 2131558623;
  public static final int fj = 2131558624;
  public static final int fk = 2131558630;
  public static final int fl = 2130837575;
  public static final int fm = 2130837576;
  public static final int fn = 2130837577;
  public static final int fo = 2130837597;
  public static final int fp = 2130837598;
  public static final int fq = 2130837599;
  public static final int fr = 2130837600;
  public static final int fs = 2130837601;
  public static final int ft = 2130837602;
  public static final int fu = 2130837650;
  public static final int fv = 2130837651;
  public static final int fw = 2130837652;
  public static final int fx = 2130837653;
  public static final int fy = 2130837654;
  public static final int fz = 2130837659;
  public static final int g = 2130772187;
  public static final int gA = 2131689488;
  public static final int gB = 2131689489;
  public static final int gC = 2131689490;
  public static final int gD = 2131689491;
  public static final int gE = 2131689497;
  public static final int gF = 2131689499;
  public static final int gG = 2131689502;
  public static final int gH = 2130968603;
  public static final int gI = 2130968606;
  public static final int gJ = 2130968607;
  public static final int gK = 2130968608;
  public static final int gL = 2130968610;
  public static final int gM = 2130968612;
  public static final int gN = 2130968613;
  public static final int gO = 2130968615;
  public static final int gP = 2130968616;
  public static final int gQ = 2130968618;
  public static final int gR = 2130968619;
  public static final int gS = 2130968621;
  public static final int gT = 2130968623;
  public static final int gU = 2130968624;
  public static final int gV = 2130968625;
  public static final int gW = 2130968626;
  public static final int gX = 2130968627;
  public static final int gY = 2130968628;
  public static final int gZ = 2130968629;
  public static final int ga = 2130837807;
  public static final int gb = 2130837808;
  public static final int gc = 2130837809;
  public static final int gd = 2130837810;
  public static final int ge = 2130837811;
  public static final int gf = 2130837812;
  public static final int gg = 2130837813;
  public static final int gh = 2130837814;
  public static final int gi = 2130837815;
  public static final int gj = 2130837816;
  public static final int gk = 2130837817;
  public static final int gl = 2130837818;
  public static final int gm = 2130837819;
  public static final int gn = 2130837820;
  public static final int go = 2130837821;
  public static final int gp = 2130837822;
  public static final int gq = 2130837823;
  public static final int gr = 2130837852;
  public static final int gs = 2130837853;
  public static final int gt = 2130837867;
  public static final int gu = 2130837880;
  public static final int gv = 2131689483;
  public static final int gw = 2131689484;
  public static final int gx = 2131689485;
  public static final int gy = 2131689486;
  public static final int gz = 2131689487;
  public static final int h = 2130772183;
  public static final int hA = 2130968678;
  public static final int hB = 2130968679;
  public static final int hC = 2130968683;
  public static final int hD = 2130968684;
  public static final int hE = 2130968685;
  public static final int hF = 2130968686;
  public static final int hG = 2130968687;
  public static final int hH = 2130968688;
  public static final int hI = 2130968689;
  public static final int hJ = 2130968690;
  public static final int hK = 2130968691;
  public static final int hL = 2130968693;
  public static final int hM = 2130968697;
  public static final int hN = 2130968698;
  public static final int hO = 2130968699;
  public static final int hP = 2130968707;
  public static final int hQ = 2130968717;
  public static final int hR = 2130968719;
  public static final int hS = 2130968721;
  public static final int hT = 2130968722;
  public static final int hU = 2130968723;
  public static final int hV = 2130968734;
  public static final int hW = 2130968735;
  public static final int hX = 2130968736;
  public static final int hY = 2130968737;
  public static final int hZ = 2130968741;
  public static final int ha = 2130968630;
  public static final int hb = 2130968631;
  public static final int hc = 2130968636;
  public static final int hd = 2130968639;
  public static final int he = 2130968640;
  public static final int hf = 2130968647;
  public static final int hg = 2130968649;
  public static final int hh = 2130968650;
  public static final int hi = 2130968652;
  public static final int hj = 2130968655;
  public static final int hk = 2130968656;
  public static final int hl = 2130968657;
  public static final int hm = 2130968658;
  public static final int hn = 2130968659;
  public static final int ho = 2130968660;
  public static final int hp = 2130968661;
  public static final int hq = 2130968662;
  public static final int hr = 2130968667;
  public static final int hs = 2130968668;
  public static final int ht = 2130968669;
  public static final int hu = 2130968670;
  public static final int hv = 2130968671;
  public static final int hw = 2130968672;
  public static final int hx = 2130968673;
  public static final int hy = 2130968674;
  public static final int hz = 2130968675;
  public static final int i = 2130772177;
  public static final int iA = 2130968786;
  public static final int iB = 2130968788;
  public static final int iC = 2130968790;
  public static final int iD = 2130968792;
  public static final int iE = 2130968793;
  public static final int iF = 2130968796;
  public static final int iG = 2130968797;
  public static final int iH = 2130968798;
  public static final int iI = 2130968806;
  public static final int iJ = 2130968807;
  public static final int iK = 2130968808;
  public static final int iL = 2131951617;
  public static final int iM = 2131951618;
  public static final int iN = 2131951619;
  public static final int iO = 2131951620;
  public static final int iP = 2131951621;
  public static final int iQ = 2131951622;
  public static final int iR = 2131951623;
  public static final int iS = 2131951624;
  public static final int iT = 2131951625;
  public static final int iU = 2131755008;
  public static final int iV = 2131755009;
  public static final int iW = 2131296256;
  public static final int iX = 2131296257;
  public static final int iY = 2131296258;
  public static final int iZ = 2131296262;
  public static final int ia = 2130968742;
  public static final int ib = 2130968743;
  public static final int ic = 2130968745;
  public static final int id = 2130968748;
  public static final int ie = 2130968749;
  public static final int jdField_if = 2130968750;
  public static final int ig = 2130968751;
  public static final int ih = 2130968754;
  public static final int ii = 2130968755;
  public static final int ij = 2130968756;
  public static final int ik = 2130968758;
  public static final int il = 2130968759;
  public static final int im = 2130968760;
  public static final int in = 2130968761;
  public static final int io = 2130968764;
  public static final int ip = 2130968766;
  public static final int iq = 2130968767;
  public static final int ir = 2130968774;
  public static final int is = 2130968775;
  public static final int it = 2130968776;
  public static final int iu = 2130968778;
  public static final int iv = 2130968780;
  public static final int iw = 2130968782;
  public static final int ix = 2130968783;
  public static final int iy = 2130968784;
  public static final int iz = 2130968785;
  public static final int j = 2130772179;
  public static final int jA = 2131820563;
  public static final int jB = 2131820755;
  public static final int jC = 2131820756;
  public static final int jD = 2131820764;
  public static final int jE = 2131821103;
  public static final int jF = 2131821104;
  public static final int jG = 2130771969;
  public static final int jH = 2131492933;
  public static final int jI = 2131558472;
  public static final int jJ = 2131558600;
  public static final int jK = 2131558601;
  public static final int jL = 2130837573;
  public static final int jM = 2130837578;
  public static final int jN = 2131624066;
  public static final int jO = 2131624505;
  public static final int jP = 2131624509;
  public static final int jQ = 2131624503;
  public static final int jR = 2131624499;
  public static final int jS = 2131624504;
  public static final int jT = 2131624065;
  public static final int jU = 2131623940;
  public static final int jV = 2131624102;
  public static final int jW = 2131624104;
  public static final int jX = 2131624105;
  public static final int jY = 2131624501;
  public static final int jZ = 2131624101;
  public static final int ja = 2131886291;
  public static GoogleApiClient jb;
  public static final int jc = 2131820710;
  public static final int jd = 2131820711;
  public static final int je = 2131820712;
  public static final int jf = 2131820713;
  public static final int jg = 2131820714;
  public static final int jh = 2131820715;
  public static final int ji = 2131820717;
  public static final int jj = 2131820718;
  public static final int jk = 2131820719;
  public static final int jl = 2131820720;
  public static final int jm = 2131820721;
  public static final int jn = 2131820722;
  public static final int jo = 2131820724;
  public static final int jp = 2131820725;
  public static final int jq = 2131820728;
  public static final int jr = 2131820729;
  public static final int js = 2131820730;
  public static final int jt = 2131820731;
  public static final int ju = 2131820732;
  public static final int jv = 2131820733;
  public static final int jw = 2131820734;
  public static final int jx = 2131820735;
  public static final int jy = 2131820736;
  public static final int jz = 2131820737;
  public static final int k = 2130772185;
  public static final int ka = 2131624103;
  public static final int kb = 2131624508;
  public static final int kc = 2131624507;
  public static final int kd = 2131624100;
  public static final int ke = 2131624500;
  public static final int kf = 2131624506;
  public static final int kg = 2131624502;
  public static final int kh = 2131689481;
  public static final int ki = 2131689482;
  public static final int kj = 2131820599;
  public static final int kk = 2131820861;
  public static final int kl = 2131821026;
  public static final int km = 2131821093;
  public static volatile boolean kn = false;
  public static final int ko = 2131624339;
  public static final int kp = 2131624357;
  public static final int kq = 2131624358;
  public static final int kr = 2131624359;
  public static final int ks = 2131624360;
  public static final int kt = 2130968694;
  public static final int ku = 2130968695;
  public static final int kv = 2130968696;
  public static final int l = 2130772186;
  public static final int m = 2130772210;
  public static final int n = 2130772204;
  public static final int o = 2130772201;
  public static final int p = 2130772192;
  public static final int q = 2130772180;
  public static final int r = 2130772181;
  public static final int s = 2130772256;
  public static final int t = 2130772259;
  public static final int u = 2130772264;
  public static final int v = 2130772265;
  public static final int w = 2130772267;
  public static final int x = 2130772249;
  public static final int y = 2130772253;
  public static final int z = 2130772251;
  
  public cx() {}
  
  public static long A(Object paramObject)
  {
    return ((PlaybackState)paramObject).getActions();
  }
  
  public static String A(String paramString)
  {
    String str = paramString;
    if (!paramString.startsWith("http"))
    {
      str = paramString;
      if (paramString.length() > 200) {
        str = String.valueOf(paramString.substring(0, 200)).concat("[...]");
      }
    }
    return B(str);
  }
  
  public static CharSequence B(Object paramObject)
  {
    return ((PlaybackState)paramObject).getErrorMessage();
  }
  
  public static String B(String paramString)
  {
    int i2 = paramString.length();
    StringBuilder localStringBuilder = new StringBuilder(i2);
    int i1 = 0;
    if (i1 < i2)
    {
      char c1 = paramString.charAt(i1);
      if ((c1 >= ' ') && (c1 <= '~') && (c1 != '"') && (c1 != '\'')) {
        localStringBuilder.append(c1);
      }
      for (;;)
      {
        i1 += 1;
        break;
        localStringBuilder.append(String.format("\\u%04x", new Object[] { Integer.valueOf(c1) }));
      }
    }
    return localStringBuilder.toString();
  }
  
  public static long C(Object paramObject)
  {
    return ((PlaybackState)paramObject).getLastPositionUpdateTime();
  }
  
  public static boolean C(String paramString)
  {
    return (paramString.equals("POST")) || (paramString.equals("PATCH")) || (paramString.equals("PUT")) || (paramString.equals("DELETE")) || (paramString.equals("MOVE"));
  }
  
  public static List D(Object paramObject)
  {
    return ((PlaybackState)paramObject).getCustomActions();
  }
  
  public static boolean D(String paramString)
  {
    return (paramString.equals("POST")) || (paramString.equals("PUT")) || (paramString.equals("PATCH")) || (paramString.equals("PROPPATCH")) || (paramString.equals("REPORT"));
  }
  
  public static long E(Object paramObject)
  {
    return ((PlaybackState)paramObject).getActiveQueueItemId();
  }
  
  public static boolean E(String paramString)
  {
    return (D(paramString)) || (paramString.equals("OPTIONS")) || (paramString.equals("DELETE")) || (paramString.equals("PROPFIND")) || (paramString.equals("MKCOL")) || (paramString.equals("LOCK"));
  }
  
  public static Object F(Object paramObject)
  {
    return AccessibilityNodeInfo.obtain((AccessibilityNodeInfo)paramObject);
  }
  
  public static boolean F(String paramString)
  {
    return !paramString.equals("PROPFIND");
  }
  
  public static int G(Object paramObject)
  {
    return ((AccessibilityNodeInfo)paramObject).getActions();
  }
  
  public static CharSequence H(Object paramObject)
  {
    return ((AccessibilityNodeInfo)paramObject).getClassName();
  }
  
  public static CharSequence I(Object paramObject)
  {
    return ((AccessibilityNodeInfo)paramObject).getContentDescription();
  }
  
  public static CharSequence J(Object paramObject)
  {
    return ((AccessibilityNodeInfo)paramObject).getPackageName();
  }
  
  public static CharSequence K(Object paramObject)
  {
    return ((AccessibilityNodeInfo)paramObject).getText();
  }
  
  public static boolean L(Object paramObject)
  {
    return ((AccessibilityNodeInfo)paramObject).isCheckable();
  }
  
  public static boolean M(Object paramObject)
  {
    return ((AccessibilityNodeInfo)paramObject).isChecked();
  }
  
  public static boolean N(Object paramObject)
  {
    return ((AccessibilityNodeInfo)paramObject).isClickable();
  }
  
  public static boolean O(Object paramObject)
  {
    return ((AccessibilityNodeInfo)paramObject).isEnabled();
  }
  
  public static boolean P(Object paramObject)
  {
    return ((AccessibilityNodeInfo)paramObject).isFocusable();
  }
  
  public static boolean Q(Object paramObject)
  {
    return ((AccessibilityNodeInfo)paramObject).isFocused();
  }
  
  public static boolean R(Object paramObject)
  {
    return ((AccessibilityNodeInfo)paramObject).isLongClickable();
  }
  
  public static boolean S(Object paramObject)
  {
    return ((AccessibilityNodeInfo)paramObject).isPassword();
  }
  
  public static boolean T(Object paramObject)
  {
    return ((AccessibilityNodeInfo)paramObject).isScrollable();
  }
  
  public static boolean U(Object paramObject)
  {
    return ((AccessibilityNodeInfo)paramObject).isSelected();
  }
  
  public static void V(Object paramObject)
  {
    ((AccessibilityNodeInfo)paramObject).recycle();
  }
  
  public static boolean W(Object paramObject)
  {
    return ((AccessibilityNodeInfo)paramObject).isVisibleToUser();
  }
  
  public static boolean X(Object paramObject)
  {
    return ((AccessibilityNodeInfo)paramObject).isAccessibilityFocused();
  }
  
  public static String Y(Object paramObject)
  {
    return ((AccessibilityNodeInfo)paramObject).getViewIdResourceName();
  }
  
  public static boolean Z(Object paramObject)
  {
    return ((EdgeEffect)paramObject).isFinished();
  }
  
  public static char a(long paramLong)
  {
    int i2 = (int)(paramLong % 24L);
    int i1 = i2;
    if (i2 >= 8) {
      i1 = i2 + 1;
    }
    i2 = i1;
    if (i1 >= 14) {
      i2 = i1 + 1;
    }
    return Character.toChars(i2 + 65)[0];
  }
  
  public static double a(int paramInt)
  {
    double d1 = Color.red(paramInt) / 255.0D;
    double d2;
    label48:
    double d3;
    if (d1 < 0.03928D)
    {
      d1 /= 12.92D;
      d2 = Color.green(paramInt) / 255.0D;
      if (d2 >= 0.03928D) {
        break label114;
      }
      d2 /= 12.92D;
      d3 = Color.blue(paramInt) / 255.0D;
      if (d3 >= 0.03928D) {
        break label133;
      }
    }
    label114:
    label133:
    for (d3 /= 12.92D;; d3 = Math.pow((d3 + 0.055D) / 1.055D, 2.4D))
    {
      return d1 * 0.2126D + d2 * 0.7152D + 0.0722D * d3;
      d1 = Math.pow((d1 + 0.055D) / 1.055D, 2.4D);
      break;
      d2 = Math.pow((d2 + 0.055D) / 1.055D, 2.4D);
      break label48;
    }
  }
  
  public static float a(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    if (paramFloat1 < 0.0F) {
      paramFloat2 = 0.0F;
    }
    do
    {
      return paramFloat2;
      paramFloat2 = paramFloat3;
    } while (paramFloat1 > paramFloat3);
    return paramFloat1;
  }
  
  public static float a(VelocityTracker paramVelocityTracker, int paramInt)
  {
    return paramVelocityTracker.getXVelocity(paramInt);
  }
  
  public static int a(int paramInt1, int paramInt2)
  {
    int i1 = Color.alpha(paramInt2);
    int i2 = Color.alpha(paramInt1);
    int i3 = b(i2, i1);
    return Color.argb(i3, a(Color.red(paramInt1), i2, Color.red(paramInt2), i1, i3), a(Color.green(paramInt1), i2, Color.green(paramInt2), i1, i3), a(Color.blue(paramInt1), i2, Color.blue(paramInt2), i1, i3));
  }
  
  public static int a(int paramInt1, int paramInt2, float paramFloat)
  {
    int i2 = 0;
    int i1 = 255;
    if (Color.alpha(paramInt2) != 255) {
      throw new IllegalArgumentException("background can not be translucent: #" + Integer.toHexString(paramInt2));
    }
    if (c(d(paramInt1, 255), paramInt2) < paramFloat) {
      i4 = -1;
    }
    int i3;
    do
    {
      do
      {
        return i4;
        i3 = 0;
        i4 = i1;
      } while (i3 > 10);
      i4 = i1;
    } while (i1 - i2 <= 1);
    int i4 = (i2 + i1) / 2;
    if (c(d(paramInt1, i4), paramInt2) < paramFloat) {
      i2 = i4;
    }
    for (;;)
    {
      i3 += 1;
      break;
      i1 = i4;
    }
  }
  
  public static int a(int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramInt1 < 0) {
      paramInt2 = 0;
    }
    do
    {
      return paramInt2;
      paramInt2 = paramInt1;
    } while (paramInt1 <= 255);
    return 255;
  }
  
  public static int a(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    if (paramInt5 == 0) {
      return 0;
    }
    return (paramInt1 * 255 * paramInt2 + paramInt3 * paramInt4 * (255 - paramInt2)) / (paramInt5 * 255);
  }
  
  public static int a(aah paramAah, zj paramZj, View paramView1, View paramView2, zy paramZy, boolean paramBoolean)
  {
    if ((paramZy.m() == 0) || (paramAah.a() == 0) || (paramView1 == null) || (paramView2 == null)) {
      return 0;
    }
    if (!paramBoolean) {
      return Math.abs(paramZy.a(paramView1) - paramZy.a(paramView2)) + 1;
    }
    int i1 = paramZj.b(paramView2);
    int i2 = paramZj.a(paramView1);
    return Math.min(paramZj.e(), i1 - i2);
  }
  
  public static int a(aah paramAah, zj paramZj, View paramView1, View paramView2, zy paramZy, boolean paramBoolean1, boolean paramBoolean2)
  {
    int i1 = 0;
    int i2 = i1;
    if (paramZy.m() != 0)
    {
      i2 = i1;
      if (paramAah.a() != 0)
      {
        i2 = i1;
        if (paramView1 != null)
        {
          if (paramView2 != null) {
            break label45;
          }
          i2 = i1;
        }
      }
    }
    return i2;
    label45:
    i1 = Math.min(paramZy.a(paramView1), paramZy.a(paramView2));
    i2 = Math.max(paramZy.a(paramView1), paramZy.a(paramView2));
    if (paramBoolean2) {}
    for (i1 = Math.max(0, paramAah.a() - i2 - 1);; i1 = Math.max(0, i1))
    {
      i2 = i1;
      if (!paramBoolean1) {
        break;
      }
      i2 = Math.abs(paramZj.b(paramView2) - paramZj.a(paramView1));
      int i3 = Math.abs(paramZy.a(paramView1) - paramZy.a(paramView2));
      float f1 = i2 / (i3 + 1);
      return Math.round(i1 * f1 + (paramZj.b() - paramZj.a(paramView1)));
    }
  }
  
  public static int a(Parcel paramParcel, int paramInt)
  {
    if ((paramInt & 0xFFFF0000) != -65536) {
      return paramInt >> 16 & 0xFFFF;
    }
    return paramParcel.readInt();
  }
  
  public static int a(MotionEvent paramMotionEvent)
  {
    return paramMotionEvent.getPointerCount();
  }
  
  public static int a(MotionEvent paramMotionEvent, int paramInt)
  {
    return paramMotionEvent.findPointerIndex(paramInt);
  }
  
  public static int a(ViewConfiguration paramViewConfiguration)
  {
    return paramViewConfiguration.getScaledPagingTouchSlop();
  }
  
  public static int a(ViewGroup.MarginLayoutParams paramMarginLayoutParams)
  {
    return paramMarginLayoutParams.getMarginStart();
  }
  
  public static int a(AccessibilityEvent paramAccessibilityEvent)
  {
    return paramAccessibilityEvent.getContentChangeTypes();
  }
  
  public static int a(bfj paramBfj)
  {
    switch (bpw.a[paramBfj.ordinal()])
    {
    default: 
      return fW;
    }
    return fT;
  }
  
  public static int a(String paramString1, int paramInt, String paramString2)
  {
    while ((paramInt < paramString1.length()) && (paramString2.indexOf(paramString1.charAt(paramInt)) == -1)) {
      paramInt += 1;
    }
    return paramInt;
  }
  
  public static int a(Locale paramLocale)
  {
    return TextUtils.getLayoutDirectionFromLocale(paramLocale);
  }
  
  public static int a(JSONObject paramJSONObject, int paramInt)
  {
    if (paramJSONObject.isNull("preview_channel_state")) {
      return 0;
    }
    paramInt = paramJSONObject.getInt("preview_channel_state");
    switch (paramInt)
    {
    default: 
      return 0;
    }
    return paramInt;
  }
  
  public static int a(float[] paramArrayOfFloat)
  {
    float f1 = paramArrayOfFloat[0];
    float f2 = paramArrayOfFloat[1];
    float f3 = paramArrayOfFloat[2];
    f2 = (1.0F - Math.abs(f3 * 2.0F - 1.0F)) * f2;
    f3 -= 0.5F * f2;
    float f4 = f2 * (1.0F - Math.abs(f1 / 60.0F % 2.0F - 1.0F));
    int i1;
    int i2;
    int i3;
    switch ((int)f1 / 60)
    {
    default: 
      i1 = 0;
      i2 = 0;
      i3 = 0;
    }
    for (;;)
    {
      return Color.rgb(a(i3, 0, 255), a(i2, 0, 255), a(i1, 0, 255));
      i3 = Math.round((f2 + f3) * 255.0F);
      i2 = Math.round((f4 + f3) * 255.0F);
      i1 = Math.round(255.0F * f3);
      continue;
      i3 = Math.round((f4 + f3) * 255.0F);
      i2 = Math.round((f2 + f3) * 255.0F);
      i1 = Math.round(255.0F * f3);
      continue;
      i3 = Math.round(255.0F * f3);
      i2 = Math.round((f2 + f3) * 255.0F);
      i1 = Math.round((f4 + f3) * 255.0F);
      continue;
      i3 = Math.round(255.0F * f3);
      i2 = Math.round((f4 + f3) * 255.0F);
      i1 = Math.round((f2 + f3) * 255.0F);
      continue;
      i3 = Math.round((f4 + f3) * 255.0F);
      i2 = Math.round(255.0F * f3);
      i1 = Math.round((f2 + f3) * 255.0F);
      continue;
      i3 = Math.round((f2 + f3) * 255.0F);
      i2 = Math.round(255.0F * f3);
      i1 = Math.round((f4 + f3) * 255.0F);
    }
  }
  
  public static int a(Object... paramVarArgs)
  {
    return Arrays.hashCode(paramVarArgs);
  }
  
  public static long a(byte[] paramArrayOfByte, int paramInt)
  {
    long l1 = 0L;
    int i1 = 0;
    while (i1 < 4)
    {
      l1 = (l1 << 8) + (paramArrayOfByte[(paramInt + i1)] & 0xFF);
      i1 += 1;
    }
    return l1;
  }
  
  public static aee a(ael paramAel)
  {
    long l6 = System.currentTimeMillis();
    Map localMap = paramAel.c;
    long l3 = 0L;
    long l2 = 0L;
    long l1 = 0L;
    Object localObject1 = (String)localMap.get("Date");
    if (localObject1 != null) {
      l3 = b((String)localObject1);
    }
    localObject1 = (String)localMap.get("Cache-Control");
    int i1;
    Object localObject2;
    if (localObject1 != null)
    {
      localObject1 = ((String)localObject1).split(",");
      i2 = 0;
      i1 = 0;
      l1 = 0L;
      l2 = 0L;
      if (i2 < localObject1.length)
      {
        localObject2 = localObject1[i2].trim();
        if ((((String)localObject2).equals("no-cache")) || (((String)localObject2).equals("no-store"))) {
          return null;
        }
        if (((String)localObject2).startsWith("max-age=")) {}
        for (;;)
        {
          try
          {
            l5 = Long.parseLong(((String)localObject2).substring(8));
            l4 = l1;
          }
          catch (Exception localException2)
          {
            long l4 = l1;
            long l5 = l2;
            continue;
            l1 = 0L;
            l2 = 0L;
            continue;
            l4 = 0L;
            continue;
            l5 = 0L;
            continue;
          }
          i2 += 1;
          l1 = l4;
          l2 = l5;
          break;
          if (((String)localObject2).startsWith("stale-while-revalidate=")) {}
          try
          {
            l4 = Long.parseLong(((String)localObject2).substring(23));
            l5 = l2;
          }
          catch (Exception localException1)
          {
            l4 = l1;
            l5 = l2;
          }
          if (!((String)localObject2).equals("must-revalidate"))
          {
            l4 = l1;
            l5 = l2;
            if (!((String)localObject2).equals("proxy-revalidate")) {}
          }
          else
          {
            i1 = 1;
            l4 = l1;
            l5 = l2;
          }
        }
      }
    }
    for (int i2 = 1;; i2 = 0)
    {
      localObject1 = (String)localMap.get("Expires");
      if (localObject1 != null)
      {
        l5 = b((String)localObject1);
        localObject1 = (String)localMap.get("Last-Modified");
        if (localObject1 != null)
        {
          l4 = b((String)localObject1);
          localObject1 = (String)localMap.get("ETag");
          if (i2 != 0)
          {
            l2 = l6 + 1000L * l2;
            if (i1 != 0) {
              l1 = l2;
            }
          }
          for (;;)
          {
            localObject2 = new aee();
            ((aee)localObject2).a = paramAel.b;
            ((aee)localObject2).b = ((String)localObject1);
            ((aee)localObject2).f = l2;
            ((aee)localObject2).e = l1;
            ((aee)localObject2).c = l3;
            ((aee)localObject2).d = l4;
            ((aee)localObject2).g = localMap;
            return localObject2;
            l1 = 1000L * l1 + l2;
            continue;
            if ((l3 <= 0L) || (l5 < l3)) {
              break;
            }
            l1 = l5 - l3 + l6;
            l2 = l1;
          }
          break;
        }
      }
      i1 = 0;
    }
  }
  
  public static Intent a(Account paramAccount, ArrayList paramArrayList, String[] paramArrayOfString1, boolean paramBoolean, String paramString1, String paramString2, String[] paramArrayOfString2, Bundle paramBundle)
  {
    return a(null, null, paramArrayOfString1, false, null, null, null, null, false);
  }
  
  public static Intent a(Account paramAccount, ArrayList paramArrayList, String[] paramArrayOfString1, boolean paramBoolean1, String paramString1, String paramString2, String[] paramArrayOfString2, Bundle paramBundle, boolean paramBoolean2)
  {
    return a(paramAccount, paramArrayList, paramArrayOfString1, paramBoolean1, paramString1, paramString2, paramArrayOfString2, paramBundle, false, 0, 0);
  }
  
  public static Intent a(Account paramAccount, ArrayList paramArrayList, String[] paramArrayOfString1, boolean paramBoolean1, String paramString1, String paramString2, String[] paramArrayOfString2, Bundle paramBundle, boolean paramBoolean2, int paramInt1, int paramInt2)
  {
    return a(paramAccount, paramArrayList, paramArrayOfString1, paramBoolean1, paramString1, paramString2, paramArrayOfString2, paramBundle, paramBoolean2, 0, 0, false);
  }
  
  public static Intent a(Account paramAccount, ArrayList paramArrayList, String[] paramArrayOfString1, boolean paramBoolean1, String paramString1, String paramString2, String[] paramArrayOfString2, Bundle paramBundle, boolean paramBoolean2, int paramInt1, int paramInt2, boolean paramBoolean3)
  {
    Intent localIntent = new Intent();
    localIntent.setAction("com.google.android.gms.common.account.CHOOSE_ACCOUNT");
    localIntent.setPackage("com.google.android.gms");
    localIntent.putExtra("allowableAccounts", paramArrayList);
    localIntent.putExtra("allowableAccountTypes", paramArrayOfString1);
    localIntent.putExtra("addAccountOptions", paramBundle);
    localIntent.putExtra("selectedAccount", paramAccount);
    localIntent.putExtra("alwaysPromptForAccount", paramBoolean1);
    localIntent.putExtra("descriptionTextOverride", paramString1);
    localIntent.putExtra("authTokenType", paramString2);
    localIntent.putExtra("addAccountRequiredFeatures", paramArrayOfString2);
    localIntent.putExtra("setGmsCoreAccount", paramBoolean2);
    localIntent.putExtra("overrideTheme", paramInt1);
    localIntent.putExtra("overrideCustomTheme", paramInt2);
    return localIntent;
  }
  
  public static Intent a(Activity paramActivity, String paramString1, String paramString2, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (paramString2 == null) {
      throw new NullPointerException("The videoId cannot be null");
    }
    return a(new Intent("com.google.android.youtube.api.StandalonePlayerActivity.START").putExtra("video_id", paramString2), paramActivity, paramString1, 0, true, false);
  }
  
  public static Intent a(ComponentName paramComponentName)
  {
    return Intent.makeMainActivity(paramComponentName);
  }
  
  public static Intent a(Intent paramIntent, Activity paramActivity, String paramString, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
  {
    paramString = a(paramIntent, paramActivity).putExtra("developer_key", paramString).putExtra("autoplay", paramBoolean1).putExtra("lightbox_mode", paramBoolean2).putExtra("start_time_millis", paramInt);
    if ((paramActivity.getWindow().getAttributes().flags & 0x400) == 0) {}
    for (paramBoolean1 = true;; paramBoolean1 = false)
    {
      paramString.putExtra("window_has_status_bar", paramBoolean1);
      return paramIntent;
    }
  }
  
  public static Intent a(Intent paramIntent, Context paramContext)
  {
    paramIntent.putExtra("app_package", paramContext.getPackageName()).putExtra("app_version", dtg.a(paramContext)).putExtra("client_library_version", dtg.a());
    return paramIntent;
  }
  
  public static Bitmap a(Bitmap paramBitmap)
  {
    int i2 = paramBitmap.getWidth();
    int i1 = paramBitmap.getHeight();
    while (i2 * i1 > 360000)
    {
      i2 /= 2;
      i1 /= 2;
    }
    Bitmap localBitmap = paramBitmap;
    if (i2 != paramBitmap.getWidth()) {
      localBitmap = Bitmap.createScaledBitmap(paramBitmap, i2, i1, true);
    }
    return localBitmap;
  }
  
  public static PorterDuff.Mode a(int paramInt, PorterDuff.Mode paramMode)
  {
    switch (paramInt)
    {
    }
    do
    {
      return null;
      return PorterDuff.Mode.SRC_OVER;
      return PorterDuff.Mode.SRC_IN;
      return PorterDuff.Mode.SRC_ATOP;
      return PorterDuff.Mode.MULTIPLY;
      return PorterDuff.Mode.SCREEN;
    } while (Build.VERSION.SDK_INT < 11);
    return PorterDuff.Mode.valueOf("ADD");
  }
  
  public static Drawable a(Context paramContext, int paramInt)
  {
    return paramContext.getDrawable(paramInt);
  }
  
  public static Drawable a(Drawable paramDrawable)
  {
    return paramDrawable;
  }
  
  public static Drawable a(CompoundButton paramCompoundButton)
  {
    return paramCompoundButton.getButtonDrawable();
  }
  
  public static AsyncTask a(AsyncTask paramAsyncTask, Object... paramVarArgs)
  {
    if (Build.VERSION.SDK_INT >= 11)
    {
      b(paramAsyncTask, paramVarArgs);
      return paramAsyncTask;
    }
    paramAsyncTask.execute(paramVarArgs);
    return paramAsyncTask;
  }
  
  public static Parcelable.Creator a(dm paramDm)
  {
    if (Build.VERSION.SDK_INT >= 13) {
      return b(paramDm);
    }
    return new dl(paramDm);
  }
  
  public static Parcelable a(Parcel paramParcel, int paramInt, Parcelable.Creator paramCreator)
  {
    paramInt = a(paramParcel, paramInt);
    int i1 = paramParcel.dataPosition();
    if (paramInt == 0) {
      return null;
    }
    paramCreator = (Parcelable)paramCreator.createFromParcel(paramParcel);
    paramParcel.setDataPosition(paramInt + i1);
    return paramCreator;
  }
  
  public static Menu a(Context paramContext, bx paramBx)
  {
    if (Build.VERSION.SDK_INT >= 14) {
      return new rp(paramContext, paramBx);
    }
    throw new UnsupportedOperationException();
  }
  
  public static MenuItem a(Context paramContext, by paramBy)
  {
    if (Build.VERSION.SDK_INT >= 16) {
      return new rh(paramContext, paramBy);
    }
    if (Build.VERSION.SDK_INT >= 14) {
      return new rc(paramContext, paramBy);
    }
    throw new UnsupportedOperationException();
  }
  
  public static MenuItem a(MenuItem paramMenuItem, View paramView)
  {
    return paramMenuItem.setActionView(paramView);
  }
  
  public static SubMenu a(Context paramContext, bz paramBz)
  {
    if (Build.VERSION.SDK_INT >= 14) {
      return new rr(paramContext, paramBz);
    }
    throw new UnsupportedOperationException();
  }
  
  public static View a(Context paramContext, int paramInt, String paramString, String... paramVarArgs)
  {
    paramContext = ((LayoutInflater)paramContext.getSystemService("layout_inflater")).inflate(paramInt, null);
    TextView localTextView = (TextView)paramContext.findViewById(bva.dn);
    localTextView.setText(paramString);
    a(localTextView, paramVarArgs);
    return paramContext;
  }
  
  public static View a(Context paramContext, String paramString, String... paramVarArgs)
  {
    return a(paramContext, gI, paramString, paramVarArgs);
  }
  
  public static View a(MenuItem paramMenuItem)
  {
    return paramMenuItem.getActionView();
  }
  
  public static bff a(bfg paramBfg, X509Certificate paramX509Certificate)
  {
    Object localObject2 = null;
    bff localBff = new bff();
    bew localBew = paramBfg.aa;
    if (localBew == null)
    {
      localBff.b = "No certificate is present";
      return localBff;
    }
    if (TextUtils.isEmpty(paramBfg.u))
    {
      localBff.b = "No public key in device configuration";
      return localBff;
    }
    Object localObject1 = String.format("%s,%s,%s,%s,%s", new Object[] { paramBfg.b, paramBfg.q, paramBfg.t, paramBfg.u, localBew.b });
    try
    {
      localObject1 = ((String)localObject1).getBytes("UTF-8");
      localPublicKey = paramX509Certificate.getPublicKey();
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      for (;;)
      {
        try
        {
          PublicKey localPublicKey;
          localBff.c = a(localPublicKey.getEncoded());
          Signature localSignature = Signature.getInstance("SHA1withRSA");
          localSignature.initVerify(localPublicKey);
          localSignature.update((byte[])localObject1);
          if (localSignature.verify(localBew.c)) {
            continue;
          }
          localBff.b = "Signed data failed to verify";
          return localBff;
        }
        catch (IllegalArgumentException paramBfg)
        {
          paramBfg = String.valueOf(paramBfg.toString());
          if (paramBfg.length() == 0) {
            continue;
          }
          paramBfg = "Verification exception: ".concat(paramBfg);
          localBff.b = paramBfg;
          return localBff;
          paramBfg = new String("Verification exception: ");
          continue;
        }
        catch (NoSuchAlgorithmException paramBfg)
        {
          paramBfg = String.valueOf(paramBfg.toString());
          if (paramBfg.length() == 0) {
            continue;
          }
          paramBfg = "Verification exception: ".concat(paramBfg);
          localBff.b = paramBfg;
          return localBff;
          paramBfg = new String("Verification exception: ");
          continue;
        }
        catch (InvalidKeyException paramBfg)
        {
          paramBfg = String.valueOf(paramBfg.toString());
          if (paramBfg.length() == 0) {
            continue;
          }
          paramBfg = "Verification exception: ".concat(paramBfg);
          localBff.b = paramBfg;
          return localBff;
          paramBfg = new String("Verification exception: ");
          continue;
        }
        catch (SignatureException paramBfg)
        {
          paramBfg = String.valueOf(paramBfg.toString());
          if (paramBfg.length() == 0) {
            continue;
          }
          paramBfg = "Verification exception: ".concat(paramBfg);
          localBff.b = paramBfg;
          return localBff;
          paramBfg = new String("Verification exception: ");
          continue;
          String[] arrayOfString = paramX509Certificate.getSubjectX500Principal().getName().split(",");
          int i2 = arrayOfString.length;
          int i1 = 0;
          paramX509Certificate = localObject2;
          if (i1 >= i2) {
            continue;
          }
          paramX509Certificate = arrayOfString[i1].trim();
          if (!paramX509Certificate.startsWith("CN=")) {
            continue;
          }
          paramX509Certificate = paramX509Certificate.substring(3);
          if (paramX509Certificate != null) {
            continue;
          }
          localBff.b = "No CN found!";
          return localBff;
          i1 += 1;
          continue;
          paramX509Certificate = new StringTokenizer(paramX509Certificate);
          if (paramX509Certificate.countTokens() == 2) {
            continue;
          }
          localBff.b = "Malformed CN; expected two whitespace-separated tokens";
          return localBff;
          paramX509Certificate.nextToken();
          paramX509Certificate = paramX509Certificate.nextToken();
          paramBfg = paramBfg.t.replace(":", "");
          if (paramX509Certificate.equals(paramBfg)) {
            continue;
          }
          localBff.b = String.format(Locale.US, "CN '%s' does not match hotspot BSSID '%s'", new Object[] { paramBfg, paramX509Certificate });
          return localBff;
          localBff.a = true;
        }
        localUnsupportedEncodingException = localUnsupportedEncodingException;
        arrayOfString = null;
      }
    }
    return localBff;
  }
  
  public static ctx a(int paramInt, cts paramCts, ctx[] paramArrayOfCtx, Set paramSet)
  {
    int i3 = 0;
    int i4 = 0;
    int i2 = 0;
    if (paramSet.contains(Integer.valueOf(paramInt))) {
      t("Value cycle detected.  Current value reference: " + paramInt + "." + "  Previous value references: " + paramSet + ".");
    }
    ctx localCtx1 = (ctx)a(paramCts.d, paramInt, "values");
    if (paramArrayOfCtx[paramInt] != null) {
      return paramArrayOfCtx[paramInt];
    }
    Object localObject = null;
    paramSet.add(Integer.valueOf(paramInt));
    switch (localCtx1.c)
    {
    }
    for (;;)
    {
      if (localObject == null) {
        t("Invalid value: " + localCtx1);
      }
      paramArrayOfCtx[paramInt] = localObject;
      paramSet.remove(Integer.valueOf(paramInt));
      return localObject;
      localObject = b(localCtx1);
      ctx localCtx2 = a(localCtx1);
      localCtx2.e = new ctx[((ctu)localObject).d.length];
      int[] arrayOfInt = ((ctu)localObject).d;
      i3 = arrayOfInt.length;
      int i1 = 0;
      for (;;)
      {
        localObject = localCtx2;
        if (i2 >= i3) {
          break;
        }
        i4 = arrayOfInt[i2];
        localCtx2.e[i1] = a(i4, paramCts, paramArrayOfCtx, paramSet);
        i2 += 1;
        i1 += 1;
      }
      localCtx2 = a(localCtx1);
      localObject = b(localCtx1);
      if (((ctu)localObject).e.length != ((ctu)localObject).f.length) {
        t("Uneven map keys (" + ((ctu)localObject).e.length + ") and map values (" + ((ctu)localObject).f.length + ")");
      }
      localCtx2.f = new ctx[((ctu)localObject).e.length];
      localCtx2.g = new ctx[((ctu)localObject).e.length];
      arrayOfInt = ((ctu)localObject).e;
      i4 = arrayOfInt.length;
      i2 = 0;
      i1 = 0;
      while (i2 < i4)
      {
        int i5 = arrayOfInt[i2];
        localCtx2.f[i1] = a(i5, paramCts, paramArrayOfCtx, paramSet);
        i2 += 1;
        i1 += 1;
      }
      arrayOfInt = ((ctu)localObject).f;
      i4 = arrayOfInt.length;
      i1 = 0;
      i2 = i3;
      for (;;)
      {
        localObject = localCtx2;
        if (i2 >= i4) {
          break;
        }
        i3 = arrayOfInt[i2];
        localCtx2.g[i1] = a(i3, paramCts, paramArrayOfCtx, paramSet);
        i2 += 1;
        i1 += 1;
      }
      localObject = a(localCtx1);
      ((ctx)localObject).h = dra.a(a(b(localCtx1).h, paramCts, paramArrayOfCtx, paramSet));
      continue;
      localCtx2 = a(localCtx1);
      localObject = b(localCtx1);
      localCtx2.l = new ctx[((ctu)localObject).g.length];
      arrayOfInt = ((ctu)localObject).g;
      i3 = arrayOfInt.length;
      i1 = 0;
      i2 = i4;
      for (;;)
      {
        localObject = localCtx2;
        if (i2 >= i3) {
          break;
        }
        i4 = arrayOfInt[i2];
        localCtx2.l[i1] = a(i4, paramCts, paramArrayOfCtx, paramSet);
        i2 += 1;
        i1 += 1;
      }
      localObject = localCtx1;
    }
  }
  
  public static ctx a(ctx paramCtx)
  {
    ctx localCtx = new ctx();
    localCtx.c = paramCtx.c;
    localCtx.m = ((int[])paramCtx.m.clone());
    if (paramCtx.n) {
      localCtx.n = paramCtx.n;
    }
    return localCtx;
  }
  
  public static cue a(cyh paramCyh)
  {
    long l6 = System.currentTimeMillis();
    Map localMap = paramCyh.b;
    long l3 = 0L;
    long l2 = 0L;
    long l1 = 0L;
    Object localObject1 = (String)localMap.get("Date");
    if (localObject1 != null) {
      l3 = s((String)localObject1);
    }
    localObject1 = (String)localMap.get("Cache-Control");
    int i1;
    Object localObject2;
    if (localObject1 != null)
    {
      localObject1 = ((String)localObject1).split(",");
      i2 = 0;
      i1 = 0;
      l1 = 0L;
      l2 = 0L;
      if (i2 < localObject1.length)
      {
        localObject2 = localObject1[i2].trim();
        if ((((String)localObject2).equals("no-cache")) || (((String)localObject2).equals("no-store"))) {
          return null;
        }
        if (((String)localObject2).startsWith("max-age=")) {}
        for (;;)
        {
          try
          {
            l5 = Long.parseLong(((String)localObject2).substring(8));
            l4 = l1;
          }
          catch (Exception localException2)
          {
            long l4 = l1;
            long l5 = l2;
            continue;
            l1 = 0L;
            l2 = 0L;
            continue;
            l4 = 0L;
            continue;
            l5 = 0L;
            continue;
          }
          i2 += 1;
          l1 = l4;
          l2 = l5;
          break;
          if (((String)localObject2).startsWith("stale-while-revalidate=")) {}
          try
          {
            l4 = Long.parseLong(((String)localObject2).substring(23));
            l5 = l2;
          }
          catch (Exception localException1)
          {
            l4 = l1;
            l5 = l2;
          }
          if (!((String)localObject2).equals("must-revalidate"))
          {
            l4 = l1;
            l5 = l2;
            if (!((String)localObject2).equals("proxy-revalidate")) {}
          }
          else
          {
            i1 = 1;
            l4 = l1;
            l5 = l2;
          }
        }
      }
    }
    for (int i2 = 1;; i2 = 0)
    {
      localObject1 = (String)localMap.get("Expires");
      if (localObject1 != null)
      {
        l5 = s((String)localObject1);
        localObject1 = (String)localMap.get("Last-Modified");
        if (localObject1 != null)
        {
          l4 = s((String)localObject1);
          localObject1 = (String)localMap.get("ETag");
          if (i2 != 0)
          {
            l2 = l6 + 1000L * l2;
            if (i1 != 0) {
              l1 = l2;
            }
          }
          for (;;)
          {
            localObject2 = new cue();
            ((cue)localObject2).a = paramCyh.a;
            ((cue)localObject2).b = ((String)localObject1);
            ((cue)localObject2).f = l2;
            ((cue)localObject2).e = l1;
            ((cue)localObject2).c = l3;
            ((cue)localObject2).d = l4;
            ((cue)localObject2).g = localMap;
            return localObject2;
            l1 = 1000L * l1 + l2;
            continue;
            if ((l3 <= 0L) || (l5 < l3)) {
              break;
            }
            l1 = l5 - l3 + l6;
            l2 = l1;
          }
          break;
        }
      }
      i1 = 0;
    }
  }
  
  public static cus a(cuu paramCuu)
  {
    if (paramCuu == null) {
      return null;
    }
    return paramCuu.a(bvd.a().g.b());
  }
  
  public static cus a(cuu paramCuu, long paramLong)
  {
    if (paramCuu == null) {
      return null;
    }
    return paramCuu.a(paramLong);
  }
  
  public static dal a(Context paramContext, dga paramDga)
  {
    File localFile = new File(paramContext.getCacheDir(), "volley");
    paramDga = "volley/0";
    try
    {
      String str = paramContext.getPackageName();
      paramContext = paramContext.getPackageManager().getPackageInfo(str, 0);
      paramContext = str + "/" + paramContext.versionCode;
      if (Build.VERSION.SDK_INT >= 9) {}
      for (paramContext = new dgb();; paramContext = new dfd(AndroidHttpClient.newInstance(paramContext)))
      {
        paramContext = new der(paramContext);
        paramContext = new dal(new dfa(localFile), paramContext);
        paramContext.a();
        return paramContext;
      }
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;)
      {
        paramContext = paramDga;
      }
    }
  }
  
  public static dfo a(cto paramCto, cts paramCts, ctx[] paramArrayOfCtx)
  {
    dfp localDfp = dfo.a();
    paramCto = paramCto.c;
    int i2 = paramCto.length;
    int i1 = 0;
    if (i1 < i2)
    {
      int i3 = paramCto[i1];
      Object localObject = (ctr)a(paramCts.e, Integer.valueOf(i3).intValue(), "properties");
      String str = (String)a(paramCts.c, ((ctr)localObject).c, "keys");
      localObject = (ctx)a(paramArrayOfCtx, ((ctr)localObject).d, "values");
      if (ctm.F.toString().equals(str)) {
        localDfp.a = ((ctx)localObject);
      }
      for (;;)
      {
        i1 += 1;
        break;
        localDfp.a(str, (ctx)localObject);
      }
    }
    return localDfp.a();
  }
  
  public static dfq a(cts paramCts)
  {
    int i2 = 0;
    Object localObject1 = new ctx[paramCts.d.length];
    int i1 = 0;
    while (i1 < paramCts.d.length)
    {
      a(i1, paramCts, (ctx[])localObject1, new HashSet(0));
      i1 += 1;
    }
    dfr localDfr = dfq.a();
    ArrayList localArrayList1 = new ArrayList();
    i1 = 0;
    while (i1 < paramCts.g.length)
    {
      localArrayList1.add(a(paramCts.g[i1], paramCts, (ctx[])localObject1));
      i1 += 1;
    }
    ArrayList localArrayList2 = new ArrayList();
    i1 = 0;
    while (i1 < paramCts.h.length)
    {
      localArrayList2.add(a(paramCts.h[i1], paramCts, (ctx[])localObject1));
      i1 += 1;
    }
    ArrayList localArrayList3 = new ArrayList();
    i1 = 0;
    Object localObject2;
    while (i1 < paramCts.f.length)
    {
      localObject2 = a(paramCts.f[i1], paramCts, (ctx[])localObject1);
      localDfr.a((dfo)localObject2);
      localArrayList3.add(localObject2);
      i1 += 1;
    }
    localObject1 = paramCts.i;
    int i3 = localObject1.length;
    i1 = i2;
    while (i1 < i3)
    {
      localObject2 = a(localObject1[i1], localArrayList1, localArrayList3, localArrayList2, paramCts);
      localDfr.a.add(localObject2);
      i1 += 1;
    }
    localDfr.b = paramCts.j;
    localDfr.c = paramCts.k;
    return localDfr.a();
  }
  
  public static dfs a(ctt paramCtt, List paramList1, List paramList2, List paramList3, cts paramCts)
  {
    dft localDft = new dft();
    Object localObject = paramCtt.c;
    int i2 = localObject.length;
    int i1 = 0;
    dfo localDfo;
    while (i1 < i2)
    {
      localDfo = (dfo)paramList3.get(Integer.valueOf(localObject[i1]).intValue());
      localDft.a.add(localDfo);
      i1 += 1;
    }
    localObject = paramCtt.d;
    i2 = localObject.length;
    i1 = 0;
    while (i1 < i2)
    {
      localDfo = (dfo)paramList3.get(Integer.valueOf(localObject[i1]).intValue());
      localDft.b.add(localDfo);
      i1 += 1;
    }
    paramList3 = paramCtt.e;
    i2 = paramList3.length;
    i1 = 0;
    while (i1 < i2)
    {
      localObject = (dfo)paramList1.get(Integer.valueOf(paramList3[i1]).intValue());
      localDft.c.add(localObject);
      i1 += 1;
    }
    paramList3 = paramCtt.g;
    i2 = paramList3.length;
    i1 = 0;
    int i3;
    while (i1 < i2)
    {
      i3 = paramList3[i1];
      localObject = paramCts.d[Integer.valueOf(i3).intValue()].d;
      localDft.i.add(localObject);
      i1 += 1;
    }
    paramList3 = paramCtt.f;
    i2 = paramList3.length;
    i1 = 0;
    while (i1 < i2)
    {
      localObject = (dfo)paramList1.get(Integer.valueOf(paramList3[i1]).intValue());
      localDft.d.add(localObject);
      i1 += 1;
    }
    paramList1 = paramCtt.h;
    i2 = paramList1.length;
    i1 = 0;
    while (i1 < i2)
    {
      i3 = paramList1[i1];
      paramList3 = paramCts.d[Integer.valueOf(i3).intValue()].d;
      localDft.j.add(paramList3);
      i1 += 1;
    }
    paramList1 = paramCtt.i;
    i2 = paramList1.length;
    i1 = 0;
    while (i1 < i2)
    {
      paramList3 = (dfo)paramList2.get(Integer.valueOf(paramList1[i1]).intValue());
      localDft.e.add(paramList3);
      i1 += 1;
    }
    paramList1 = paramCtt.k;
    i2 = paramList1.length;
    i1 = 0;
    while (i1 < i2)
    {
      i3 = paramList1[i1];
      paramList3 = paramCts.d[Integer.valueOf(i3).intValue()].d;
      localDft.g.add(paramList3);
      i1 += 1;
    }
    paramList1 = paramCtt.j;
    i2 = paramList1.length;
    i1 = 0;
    while (i1 < i2)
    {
      paramList3 = (dfo)paramList2.get(Integer.valueOf(paramList1[i1]).intValue());
      localDft.f.add(paramList3);
      i1 += 1;
    }
    paramCtt = paramCtt.l;
    i2 = paramCtt.length;
    i1 = 0;
    while (i1 < i2)
    {
      i3 = paramCtt[i1];
      paramList1 = paramCts.d[Integer.valueOf(i3).intValue()].d;
      localDft.h.add(paramList1);
      i1 += 1;
    }
    return new dfs(localDft.a, localDft.b, localDft.c, localDft.d, localDft.e, localDft.f, localDft.g, localDft.h, localDft.i, localDft.j);
  }
  
  public static dpj a(dpj paramDpj)
  {
    try
    {
      dpj localDpj = new dpj(dra.a(w(dra.a((ctx)paramDpj.a))), paramDpj.b);
      return localDpj;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      dnm.a("Escape URI: unsupported encoding", localUnsupportedEncodingException);
    }
    return paramDpj;
  }
  
  public static dpj a(dpj paramDpj, int paramInt)
  {
    if (!d((ctx)paramDpj.a))
    {
      dnm.a("Escaping can only be applied to strings.");
      return paramDpj;
    }
    switch (paramInt)
    {
    default: 
      dnm.a("Unsupported Value Escaping: " + paramInt);
      return paramDpj;
    }
    return a(paramDpj);
  }
  
  public static dpj a(dpj paramDpj, int... paramVarArgs)
  {
    int i2 = paramVarArgs.length;
    int i1 = 0;
    while (i1 < i2)
    {
      paramDpj = a(paramDpj, paramVarArgs[i1]);
      i1 += 1;
    }
    return paramDpj;
  }
  
  public static dtv a(String[] paramArrayOfString)
  {
    dtv localDtv = new dtv();
    localDtv.d = bpc.c();
    localDtv.a = Integer.valueOf(1);
    localDtv.c = 1;
    localDtv.b = paramArrayOfString;
    return localDtv;
  }
  
  public static CharSequence a(Object paramObject, Context paramContext)
  {
    return ((MediaRouter.RouteInfo)paramObject).getName(paramContext);
  }
  
  public static Object a()
  {
    return new MediaDescription.Builder();
  }
  
  public static Object a(int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean)
  {
    return AccessibilityNodeInfo.CollectionItemInfo.obtain(paramInt1, paramInt2, paramInt3, paramInt4, paramBoolean);
  }
  
  public static Object a(int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean1, boolean paramBoolean2)
  {
    return AccessibilityNodeInfo.CollectionItemInfo.obtain(paramInt1, paramInt2, paramInt3, paramInt4, paramBoolean1, paramBoolean2);
  }
  
  public static Object a(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    return AccessibilityNodeInfo.CollectionInfo.obtain(paramInt1, paramInt2, paramBoolean);
  }
  
  public static Object a(int paramInt1, int paramInt2, boolean paramBoolean, int paramInt3)
  {
    return AccessibilityNodeInfo.CollectionInfo.obtain(paramInt1, paramInt2, paramBoolean, paramInt3);
  }
  
  public static Object a(int paramInt, CharSequence paramCharSequence)
  {
    return new AccessibilityNodeInfo.AccessibilityAction(paramInt, paramCharSequence);
  }
  
  public static Object a(Context paramContext)
  {
    return paramContext.getSystemService("display");
  }
  
  public static Object a(Context paramContext, Object paramObject)
  {
    return new MediaController(paramContext, (MediaSession.Token)paramObject);
  }
  
  public static Object a(Parcel paramParcel)
  {
    return MediaDescription.CREATOR.createFromParcel(paramParcel);
  }
  
  public static Object a(cv paramCv)
  {
    return new cw(paramCv);
  }
  
  public static Object a(et paramEt)
  {
    return new es(paramEt);
  }
  
  public static Object a(ev paramEv)
  {
    return new eu(paramEv);
  }
  
  public static Object a(Object paramObject)
  {
    Object localObject = paramObject;
    if (paramObject != null) {
      localObject = ((Transition)paramObject).clone();
    }
    return localObject;
  }
  
  public static Object a(Object paramObject, View paramView1, ArrayList paramArrayList, Map paramMap, View paramView2)
  {
    Object localObject = paramObject;
    if (paramObject != null)
    {
      a(paramArrayList, paramView1);
      if (paramMap != null) {
        paramArrayList.removeAll(paramMap.values());
      }
      if (paramArrayList.isEmpty()) {
        localObject = null;
      }
    }
    else
    {
      return localObject;
    }
    paramArrayList.add(paramView2);
    b((Transition)paramObject, paramArrayList);
    return paramObject;
  }
  
  public static Object a(Object paramObject1, Object paramObject2, Object paramObject3, boolean paramBoolean)
  {
    Transition localTransition = (Transition)paramObject1;
    paramObject1 = (Transition)paramObject2;
    paramObject3 = (Transition)paramObject3;
    if ((localTransition != null) && (paramObject1 != null)) {}
    for (;;)
    {
      if (paramBoolean)
      {
        paramObject2 = new TransitionSet();
        if (localTransition != null) {
          paramObject2.addTransition(localTransition);
        }
        if (paramObject1 != null) {
          paramObject2.addTransition(paramObject1);
        }
        if (paramObject3 != null) {
          paramObject2.addTransition(paramObject3);
        }
        return paramObject2;
      }
      paramObject2 = null;
      if ((paramObject1 != null) && (localTransition != null)) {
        paramObject1 = new TransitionSet().addTransition(paramObject1).addTransition(localTransition).setOrdering(1);
      }
      while (paramObject3 != null)
      {
        paramObject2 = new TransitionSet();
        if (paramObject1 != null) {
          paramObject2.addTransition(paramObject1);
        }
        paramObject2.addTransition(paramObject3);
        return paramObject2;
        if (paramObject1 == null)
        {
          paramObject1 = paramObject2;
          if (localTransition != null) {
            paramObject1 = localTransition;
          }
        }
      }
      return paramObject1;
      paramBoolean = true;
    }
  }
  
  public static Object a(Object paramObject, String paramString, boolean paramBoolean)
  {
    return ((MediaRouter)paramObject).createRouteCategory(paramString, false);
  }
  
  public static Object a(ji paramJi)
  {
    return new jh(paramJi);
  }
  
  public static Object a(jk paramJk)
  {
    return new jj(paramJk);
  }
  
  public static Object a(un paramUn)
  {
    return new uo(paramUn);
  }
  
  public static Object a(ur paramUr)
  {
    return new us(paramUr);
  }
  
  public static Object a(uu paramUu)
  {
    return new uv(paramUu);
  }
  
  public static Object a(Object[] paramArrayOfObject, int paramInt, String paramString)
  {
    if ((paramInt < 0) || (paramInt >= paramArrayOfObject.length)) {
      t("Index out of bounds detected: " + paramInt + " in " + paramString);
    }
    return paramArrayOfObject[paramInt];
  }
  
  public static String a(Context paramContext, int paramInt, String paramString)
  {
    paramContext = paramContext.getResources();
    switch (paramInt)
    {
    default: 
      return paramContext.getString(js);
    case 1: 
      if (a(paramContext)) {
        return paramContext.getString(jk, new Object[] { paramString });
      }
      return paramContext.getString(jj, new Object[] { paramString });
    case 3: 
      return paramContext.getString(jg, new Object[] { paramString });
    case 18: 
      return paramContext.getString(jy, new Object[] { paramString });
    case 2: 
      return paramContext.getString(jw, new Object[] { paramString });
    case 42: 
      return paramContext.getString(jc, new Object[] { paramString });
    case 9: 
      return paramContext.getString(jt, new Object[] { paramString });
    case 7: 
      return paramContext.getString(jo);
    case 5: 
      return paramContext.getString(jm);
    case 16: 
      return paramContext.getString(je, new Object[] { paramString });
    }
    return paramContext.getString(jq);
  }
  
  public static String a(Context paramContext, bfj paramBfj)
  {
    switch (bpw.a[paramBfj.ordinal()])
    {
    default: 
      return paramContext.getString(cci.bh);
    case 1: 
      return paramContext.getString(cci.bi);
    case 2: 
      return paramContext.getString(cci.bl);
    case 3: 
      return paramContext.getString(cci.bn);
    }
    return paramContext.getString(cci.bf);
  }
  
  /* Error */
  public static String a(Context paramContext, dsl paramDsl)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 2693	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   4: invokevirtual 2637	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   7: astore_0
    //   8: aload_0
    //   9: aload_0
    //   10: ldc_w 2695
    //   13: ldc_w 2697
    //   16: aload_0
    //   17: getstatic 2699	cx:ko	I
    //   20: invokevirtual 2702	android/content/res/Resources:getResourcePackageName	(I)Ljava/lang/String;
    //   23: invokevirtual 2706	android/content/res/Resources:getIdentifier	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I
    //   26: invokevirtual 2710	android/content/res/Resources:openRawResource	(I)Ljava/io/InputStream;
    //   29: astore_0
    //   30: aload_1
    //   31: getfield 2713	dsl:c	I
    //   34: newarray byte
    //   36: astore 4
    //   38: aload_0
    //   39: aload_1
    //   40: getfield 2715	dsl:b	J
    //   43: invokevirtual 2721	java/io/InputStream:skip	(J)J
    //   46: pop2
    //   47: iconst_0
    //   48: istore_2
    //   49: iload_2
    //   50: aload 4
    //   52: arraylength
    //   53: if_icmpge +27 -> 80
    //   56: aload_0
    //   57: aload 4
    //   59: iload_2
    //   60: aload 4
    //   62: arraylength
    //   63: iload_2
    //   64: isub
    //   65: invokevirtual 2725	java/io/InputStream:read	([BII)I
    //   68: istore_3
    //   69: iload_3
    //   70: ifle +10 -> 80
    //   73: iload_2
    //   74: iload_3
    //   75: iadd
    //   76: istore_2
    //   77: goto -28 -> 49
    //   80: aload_0
    //   81: invokevirtual 2728	java/io/InputStream:close	()V
    //   84: new 1207	java/lang/String
    //   87: dup
    //   88: aload 4
    //   90: ldc_w 2730
    //   93: invokespecial 2733	java/lang/String:<init>	([BLjava/lang/String;)V
    //   96: astore_0
    //   97: aload_0
    //   98: areturn
    //   99: astore_0
    //   100: aload_1
    //   101: invokestatic 1223	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   104: astore_1
    //   105: new 2735	java/lang/RuntimeException
    //   108: dup
    //   109: new 1237	java/lang/StringBuilder
    //   112: dup
    //   113: aload_1
    //   114: invokestatic 1223	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   117: invokevirtual 1215	java/lang/String:length	()I
    //   120: bipush 32
    //   122: iadd
    //   123: invokespecial 1240	java/lang/StringBuilder:<init>	(I)V
    //   126: ldc_w 2737
    //   129: invokevirtual 1262	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   132: aload_1
    //   133: invokevirtual 1262	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   136: invokevirtual 1266	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   139: aload_0
    //   140: invokespecial 2739	java/lang/RuntimeException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   143: athrow
    //   144: astore_0
    //   145: new 2735	java/lang/RuntimeException
    //   148: dup
    //   149: ldc_w 2741
    //   152: aload_0
    //   153: invokespecial 2739	java/lang/RuntimeException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   156: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	157	0	paramContext	Context
    //   0	157	1	paramDsl	dsl
    //   48	29	2	i1	int
    //   68	8	3	i2	int
    //   36	53	4	arrayOfByte	byte[]
    // Exception table:
    //   from	to	target	type
    //   38	47	99	java/io/IOException
    //   49	69	99	java/io/IOException
    //   80	84	99	java/io/IOException
    //   84	97	144	java/io/UnsupportedEncodingException
  }
  
  public static String a(Context paramContext, boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (int i1 = cci.i;; i1 = cci.r) {
      return paramContext.getString(i1);
    }
  }
  
  public static String a(ActivityInfo paramActivityInfo)
  {
    return paramActivityInfo.parentActivityName;
  }
  
  public static String a(PowerManager.WakeLock paramWakeLock, String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder().append(String.valueOf(Process.myPid() << 32 | System.identityHashCode(paramWakeLock)));
    paramWakeLock = paramString;
    if (TextUtils.isEmpty(paramString)) {
      paramWakeLock = "";
    }
    return paramWakeLock;
  }
  
  public static String a(ctd paramCtd)
  {
    if (paramCtd == null) {
      return "";
    }
    StringBuffer localStringBuffer = new StringBuffer();
    try
    {
      a(null, paramCtd, new StringBuffer(), localStringBuffer);
      return localStringBuffer.toString();
    }
    catch (IllegalAccessException paramCtd)
    {
      return "Error printing proto: " + paramCtd.getMessage();
    }
    catch (InvocationTargetException paramCtd) {}
    return "Error printing proto: " + paramCtd.getMessage();
  }
  
  public static String a(dwg paramDwg)
  {
    return a(new dxq(paramDwg));
  }
  
  public static String a(dxf paramDxf, String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("# ").append(paramString);
    a(paramDxf, localStringBuilder, 0);
    return localStringBuilder.toString();
  }
  
  public static String a(dxq paramDxq)
  {
    StringBuilder localStringBuilder = new StringBuilder(paramDxq.a());
    int i1 = 0;
    if (i1 < paramDxq.a())
    {
      int i2 = paramDxq.a(i1);
      switch (i2)
      {
      default: 
        if ((i2 >= 32) && (i2 <= 126)) {
          localStringBuilder.append((char)i2);
        }
        break;
      }
      for (;;)
      {
        i1 += 1;
        break;
        localStringBuilder.append("\\a");
        continue;
        localStringBuilder.append("\\b");
        continue;
        localStringBuilder.append("\\f");
        continue;
        localStringBuilder.append("\\n");
        continue;
        localStringBuilder.append("\\r");
        continue;
        localStringBuilder.append("\\t");
        continue;
        localStringBuilder.append("\\v");
        continue;
        localStringBuilder.append("\\\\");
        continue;
        localStringBuilder.append("\\'");
        continue;
        localStringBuilder.append("\\\"");
        continue;
        localStringBuilder.append('\\');
        localStringBuilder.append((char)((i2 >>> 6 & 0x3) + 48));
        localStringBuilder.append((char)((i2 >>> 3 & 0x7) + 48));
        localStringBuilder.append((char)((i2 & 0x7) + 48));
      }
    }
    return localStringBuilder.toString();
  }
  
  public static String a(dyp paramDyp)
  {
    if (paramDyp == null) {
      return "";
    }
    StringBuffer localStringBuffer = new StringBuffer();
    try
    {
      b(null, paramDyp, new StringBuffer(), localStringBuffer);
      return localStringBuffer.toString();
    }
    catch (IllegalAccessException paramDyp)
    {
      paramDyp = String.valueOf(paramDyp.getMessage());
      if (paramDyp.length() != 0) {
        return "Error printing proto: ".concat(paramDyp);
      }
      return new String("Error printing proto: ");
    }
    catch (InvocationTargetException paramDyp)
    {
      paramDyp = String.valueOf(paramDyp.getMessage());
      if (paramDyp.length() != 0) {
        return "Error printing proto: ".concat(paramDyp);
      }
    }
    return new String("Error printing proto: ");
  }
  
  public static String a(dzq paramDzq)
  {
    int i1 = paramDzq.e.indexOf('/', paramDzq.a.length() + 3);
    int i2 = dzq.a(paramDzq.e, i1, paramDzq.e.length(), "?#");
    String str1 = paramDzq.e.substring(i1, i2);
    String str2 = paramDzq.f();
    paramDzq = str1;
    if (str2 != null) {
      paramDzq = str1 + '?' + str2;
    }
    return paramDzq;
  }
  
  public static String a(eaa paramEaa, Proxy.Type paramType)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramEaa.b);
    localStringBuilder.append(' ');
    if (b(paramEaa, paramType)) {
      localStringBuilder.append(paramEaa.a);
    }
    for (;;)
    {
      localStringBuilder.append(" HTTP/1.1");
      return localStringBuilder.toString();
      localStringBuilder.append(a(paramEaa.a));
    }
  }
  
  public static String a(String paramString, int paramInt)
  {
    if (paramInt <= 0)
    {
      byj.a("index out of range for prefix", paramString);
      return "";
    }
    return paramString + paramInt;
  }
  
  public static String a(String paramString, Object paramObject)
  {
    if (TextUtils.isEmpty(paramString)) {
      throw new IllegalArgumentException(String.valueOf(paramObject));
    }
    return paramString;
  }
  
  public static String a(String paramString1, String paramString2, Collection paramCollection)
  {
    paramString1 = new StringBuilder(paramString1);
    if (paramString2 != null)
    {
      paramCollection = paramString2.toUpperCase();
      if (!paramCollection.matches("[A-F0-9]+")) {
        throw new IllegalArgumentException("Invalid application ID: " + paramString2);
      }
      paramString1.append("/").append(paramCollection);
    }
    return paramString1.toString();
  }
  
  public static String a(Map paramMap)
  {
    return a(paramMap, "ISO-8859-1");
  }
  
  public static String a(Map paramMap, String paramString)
  {
    Object localObject = (String)paramMap.get("Content-Type");
    paramMap = paramString;
    int i1;
    if (localObject != null)
    {
      localObject = ((String)localObject).split(";");
      i1 = 1;
    }
    for (;;)
    {
      paramMap = paramString;
      if (i1 < localObject.length)
      {
        paramMap = localObject[i1].trim().split("=");
        if ((paramMap.length == 2) && (paramMap[0].equals("charset"))) {
          paramMap = paramMap[1];
        }
      }
      else
      {
        return paramMap;
      }
      i1 += 1;
    }
  }
  
  public static String a(Set paramSet, String paramString)
  {
    String str = paramString;
    if (paramSet.contains(paramString))
    {
      int i1 = 2;
      while (paramSet.contains(String.valueOf(paramString).length() + 12 + paramString + " " + i1)) {
        i1 += 1;
      }
      str = String.valueOf(paramString).length() + 12 + paramString + " " + i1;
    }
    return str;
  }
  
  public static String a(byte[] paramArrayOfByte)
  {
    try
    {
      paramArrayOfByte = MessageDigest.getInstance("SHA1").digest(paramArrayOfByte);
      StringBuilder localStringBuilder = new StringBuilder();
      long l1 = a(paramArrayOfByte, 16);
      localStringBuilder.append(a(a(paramArrayOfByte, 0) ^ l1));
      localStringBuilder.append(b(a(paramArrayOfByte, 4) ^ l1));
      localStringBuilder.append(a(a(paramArrayOfByte, 8) ^ l1));
      localStringBuilder.append(b(l1 ^ a(paramArrayOfByte, 12)));
      paramArrayOfByte = localStringBuilder.toString();
      return paramArrayOfByte;
    }
    catch (NoSuchAlgorithmException paramArrayOfByte)
    {
      throw new IllegalArgumentException("Failed to calculate setup PIN code", paramArrayOfByte);
    }
  }
  
  public static String a(StackTraceElement[] paramArrayOfStackTraceElement, int paramInt)
  {
    if (paramInt + 4 >= paramArrayOfStackTraceElement.length) {
      return "<bottom of call stack>";
    }
    paramArrayOfStackTraceElement = paramArrayOfStackTraceElement[(paramInt + 4)];
    return paramArrayOfStackTraceElement.getClassName() + "." + paramArrayOfStackTraceElement.getMethodName() + ":" + paramArrayOfStackTraceElement.getLineNumber();
  }
  
  public static Set a(Context paramContext, dtx paramDtx)
  {
    Object localObject1 = paramContext.getPackageManager().getInstalledApplications(128);
    paramContext = new HashSet(((List)localObject1).size());
    localObject1 = ((List)localObject1).iterator();
    while (((Iterator)localObject1).hasNext()) {
      paramContext.add(((ApplicationInfo)((Iterator)localObject1).next()).packageName.toLowerCase(Locale.US));
    }
    localObject1 = new HashSet();
    paramDtx = paramDtx.f;
    int i2 = paramDtx.length;
    int i1 = 0;
    while (i1 < i2)
    {
      Object localObject2 = paramDtx[i1];
      if (paramContext.contains(localObject2.b.toLowerCase(Locale.US))) {
        ((Set)localObject1).add(localObject2.a);
      }
      i1 += 1;
    }
    return localObject1;
  }
  
  public static void a(int paramInt1, int paramInt2, int paramInt3, float[] paramArrayOfFloat)
  {
    float f1 = paramInt1 / 255.0F;
    float f3 = paramInt2 / 255.0F;
    float f5 = paramInt3 / 255.0F;
    float f6 = Math.max(f1, Math.max(f3, f5));
    float f7 = Math.min(f1, Math.min(f3, f5));
    float f2 = f6 - f7;
    float f4 = (f6 + f7) / 2.0F;
    if (f6 == f7)
    {
      f1 = 0.0F;
      f2 = 0.0F;
      f3 = f2 * 60.0F % 360.0F;
      f2 = f3;
      if (f3 < 0.0F) {
        f2 = f3 + 360.0F;
      }
      paramArrayOfFloat[0] = a(f2, 0.0F, 360.0F);
      paramArrayOfFloat[1] = a(f1, 0.0F, 1.0F);
      paramArrayOfFloat[2] = a(f4, 0.0F, 1.0F);
      return;
    }
    if (f6 == f1) {
      f1 = (f3 - f5) / f2 % 6.0F;
    }
    for (;;)
    {
      f3 = f2 / (1.0F - Math.abs(f4 * 2.0F - 1.0F));
      f2 = f1;
      f1 = f3;
      break;
      if (f6 == f3) {
        f1 = (f5 - f1) / f2 + 2.0F;
      } else {
        f1 = (f1 - f3) / f2 + 4.0F;
      }
    }
  }
  
  public static void a(int paramInt, float[] paramArrayOfFloat)
  {
    a(Color.red(paramInt), Color.green(paramInt), Color.blue(paramInt), paramArrayOfFloat);
  }
  
  public static void a(Activity paramActivity)
  {
    paramActivity.finishAfterTransition();
  }
  
  public static void a(Activity paramActivity, DialogInterface.OnClickListener paramOnClickListener)
  {
    if (paramActivity.isFinishing()) {
      return;
    }
    bpr localBpr = new bpr(paramOnClickListener);
    Object localObject;
    if (Build.VERSION.SDK_INT < boy.Q()) {
      localObject = new bps(paramActivity);
    }
    for (paramActivity = new AlertDialog.Builder(paramActivity).setMessage(cci.cF).setCancelable(true).setNegativeButton(cci.bt, (DialogInterface.OnClickListener)localObject).setPositiveButton(cci.F, paramOnClickListener).create();; paramActivity = new AlertDialog.Builder(paramActivity).setMessage(cci.eJ).setTitle(cci.eK).setCancelable(true).setNegativeButton(cci.eI, paramOnClickListener).setPositiveButton(cci.eH, (DialogInterface.OnClickListener)localObject).create())
    {
      paramActivity.setOnDismissListener(localBpr);
      paramActivity.show();
      return;
      localObject = new bpt(paramActivity);
    }
  }
  
  public static void a(Activity paramActivity, Bundle paramBundle)
  {
    if (jb == null) {
      jb = new ccf(SetupApplication.a).a(csd.b).a();
    }
    if ((!jb.e()) && (!jb.f())) {
      jb.c();
    }
    Bitmap localBitmap = null;
    Object localObject = paramActivity.getIntent().getExtras();
    if (localObject != null) {
      localBitmap = (Bitmap)((Bundle)localObject).getParcelable("screenShot");
    }
    localObject = localBitmap;
    if (localBitmap == null)
    {
      localObject = localBitmap;
      if (e(paramActivity)) {
        localObject = f(paramActivity);
      }
    }
    csd.a(jb, (Bitmap)localObject, paramBundle);
  }
  
  public static void a(Activity paramActivity, bbp paramBbp)
  {
    Intent localIntent2 = paramBbp.e();
    Intent localIntent1 = localIntent2;
    if (localIntent2 == null) {
      localIntent1 = HelpActivity.a(paramActivity, paramActivity.getString(cci.es));
    }
    if (e(paramActivity)) {
      localIntent1.putExtra("screenShot", f(paramActivity));
    }
    localIntent1.putExtra("feedbackSupported", true);
    localIntent1.putParcelableArrayListExtra("feedbackDevices", paramBbp.c_());
    paramActivity.startActivity(localIntent1);
  }
  
  public static void a(Activity paramActivity, List paramList)
  {
    Bundle localBundle = new Bundle();
    boy.a(localBundle, paramActivity);
    Object localObject1 = SetupApplication.a;
    if (((SetupApplication)localObject1).l()) {
      localBundle.putString("last-setup-failure", String.format("%d seconds ago: %s", new Object[] { Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(SystemClock.elapsedRealtime() - ((SetupApplication)localObject1).k)), ((SetupApplication)localObject1).l }));
    }
    a(localBundle, paramActivity);
    if ((paramList != null) && (!paramList.isEmpty()))
    {
      localObject1 = UUID.randomUUID().toString();
      localBundle.putString("feedback-id", String.format(Locale.US, boy.B(), new Object[] { localObject1 }));
      long l1 = SystemClock.elapsedRealtime();
      ArrayList localArrayList1 = new ArrayList();
      ArrayList localArrayList2 = new ArrayList();
      paramList = paramList.iterator();
      label206:
      label305:
      label315:
      while (paramList.hasNext())
      {
        Object localObject2 = (boj)paramList.next();
        Object localObject3;
        if (!TextUtils.isEmpty(((boj)localObject2).a))
        {
          localArrayList1.add(((boj)localObject2).a);
          localObject3 = ((boj)localObject2).a;
          if (TextUtils.isEmpty(((boj)localObject2).c)) {
            break label305;
          }
          localArrayList2.add(((boj)localObject2).c);
        }
        for (;;)
        {
          if (((boj)localObject2).b == null) {
            break label315;
          }
          localObject2 = new bhq(((boj)localObject2).b, (String)localObject1);
          localObject3 = new bhg(new bhe[] { localObject2 });
          ((bhg)localObject3).a = new bol((bhq)localObject2, l1);
          ((bhg)localObject3).a();
          break;
          localArrayList1.add("???");
          break label206;
          localArrayList2.add("?");
        }
      }
      localBundle.putString("build-Chromecast", TextUtils.join(",", localArrayList1));
      localBundle.putString("type-Chromecast", TextUtils.join(",", localArrayList2));
    }
    a(paramActivity, localBundle);
  }
  
  public static void a(Activity paramActivity, String[] paramArrayOfString, int paramInt)
  {
    if ((paramActivity instanceof f)) {
      ((f)paramActivity).b_(paramInt);
    }
    paramActivity.requestPermissions(paramArrayOfString, paramInt);
  }
  
  public static void a(Context paramContext, MediaRouteVolumeSlider paramMediaRouteVolumeSlider, View paramView)
  {
    int i1 = f(paramContext);
    if (Color.alpha(i1) != 255) {
      i1 = a(i1, ((Integer)paramView.getTag()).intValue());
    }
    for (;;)
    {
      if (paramMediaRouteVolumeSlider.a != i1)
      {
        if (Color.alpha(i1) != 255) {
          Log.e("MediaRouteVolumeSlider", "Volume slider color cannot be translucent: #" + Integer.toHexString(i1));
        }
        paramMediaRouteVolumeSlider.a = i1;
      }
      return;
    }
  }
  
  public static void a(Context paramContext, View paramView1, View paramView2, boolean paramBoolean)
  {
    int i1 = d(paramContext, bva.r);
    int i2 = d(paramContext, bva.s);
    int i3;
    if ((paramBoolean) && (c(-1, i1) < 3.0D))
    {
      i3 = -1;
      i2 = i1;
    }
    for (;;)
    {
      paramView1.setBackgroundColor(i3);
      paramView2.setBackgroundColor(i2);
      paramView1.setTag(Integer.valueOf(i3));
      paramView2.setTag(Integer.valueOf(i2));
      return;
      i3 = i1;
    }
  }
  
  public static void a(Context paramContext, String paramString)
  {
    a(paramContext, paramString, null, null);
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2, Bundle paramBundle)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    try
    {
      Intent localIntent3 = Intent.parseUri(paramString1, 1);
      localIntent1 = localIntent3;
      if ("android.intent.action.VIEW".equals(localIntent3.getAction()))
      {
        localIntent3.setAction("android.intent.action.CAST");
        localIntent1 = localIntent3;
      }
    }
    catch (URISyntaxException localURISyntaxException)
    {
      Intent localIntent2;
      do
      {
        for (;;)
        {
          Intent localIntent1;
          localIntent2 = new Intent("android.intent.action.CAST", Uri.parse(paramString1));
        }
      } while (a(paramContext, localIntent2.setAction("android.intent.action.VIEW"), paramString2));
      paramString1 = localPackageManager.getLaunchIntentForPackage(paramString1);
      if (paramString1 == null) {
        break label137;
      }
      paramString1.addFlags(268435456);
      paramContext.startActivity(paramString1);
      return;
      Toast.makeText(paramContext, cci.bX, 0).show();
    }
    if (paramBundle != null) {
      localIntent1.putExtras(paramBundle);
    }
    localIntent1.addFlags(268435456);
    if (a(paramContext, localIntent1, paramString2)) {
      return;
    }
    label137:
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    paramContext = paramContext.getSharedPreferences(paramString1, 0).edit();
    paramContext.putString(paramString2, paramString3);
    a(paramContext);
  }
  
  public static void a(Context paramContext, Intent[] paramArrayOfIntent)
  {
    paramContext.startActivities(paramArrayOfIntent);
  }
  
  public static void a(Context paramContext, Intent[] paramArrayOfIntent, Bundle paramBundle)
  {
    paramContext.startActivities(paramArrayOfIntent, paramBundle);
  }
  
  public static void a(SharedPreferences.Editor paramEditor)
  {
    if (Build.VERSION.SDK_INT >= 9)
    {
      paramEditor.apply();
      return;
    }
    new Thread(new dqr(paramEditor)).start();
  }
  
  public static void a(Drawable paramDrawable, int paramInt)
  {
    paramDrawable.setLayoutDirection(paramInt);
  }
  
  public static void a(Bundle paramBundle, Activity paramActivity)
  {
    a(paramBundle, "gms-core-version", b(paramActivity, "com.google.android.gms"));
    a(paramBundle, "app-version-youtube", b(paramActivity, "com.google.android.youtube"));
    a(paramBundle, "app-version-play-movies", b(paramActivity, "com.google.android.videos"));
    a(paramBundle, "app-version-play-music", b(paramActivity, "com.google.android.music"));
    a(paramBundle, "app-version-netflix", b(paramActivity, "com.netflix.mediaclient"));
  }
  
  public static void a(Bundle paramBundle, String paramString1, String paramString2)
  {
    if (paramString2 != null) {
      paramBundle.putString(paramString1, paramString2);
    }
  }
  
  public static void a(Parcel paramParcel, int paramInt, double paramDouble)
  {
    c(paramParcel, paramInt, 8);
    paramParcel.writeDouble(paramDouble);
  }
  
  public static void a(Parcel paramParcel, int paramInt, float paramFloat)
  {
    c(paramParcel, paramInt, 4);
    paramParcel.writeFloat(paramFloat);
  }
  
  public static void a(Parcel paramParcel, int paramInt1, int paramInt2)
  {
    paramInt1 = a(paramParcel, paramInt1);
    if (paramInt1 != paramInt2) {
      throw new q("Expected size " + paramInt2 + " got " + paramInt1 + " (0x" + Integer.toHexString(paramInt1) + ")", paramParcel);
    }
  }
  
  public static void a(Parcel paramParcel, int paramInt, long paramLong)
  {
    c(paramParcel, paramInt, 8);
    paramParcel.writeLong(paramLong);
  }
  
  public static void a(Parcel paramParcel, int paramInt, Bundle paramBundle, boolean paramBoolean)
  {
    if (paramBundle == null) {
      return;
    }
    paramInt = r(paramParcel, paramInt);
    paramParcel.writeBundle(paramBundle);
    s(paramParcel, paramInt);
  }
  
  public static void a(Parcel paramParcel, int paramInt, IBinder paramIBinder, boolean paramBoolean)
  {
    if (paramIBinder == null) {
      return;
    }
    paramInt = r(paramParcel, paramInt);
    paramParcel.writeStrongBinder(paramIBinder);
    s(paramParcel, paramInt);
  }
  
  public static void a(Parcel paramParcel, int paramInt1, Parcelable paramParcelable, int paramInt2, boolean paramBoolean)
  {
    if (paramParcelable == null) {
      return;
    }
    paramInt1 = r(paramParcel, paramInt1);
    paramParcelable.writeToParcel(paramParcel, paramInt2);
    s(paramParcel, paramInt1);
  }
  
  public static void a(Parcel paramParcel, int paramInt, Long paramLong, boolean paramBoolean)
  {
    if (paramLong == null) {
      return;
    }
    c(paramParcel, 3, 8);
    paramParcel.writeLong(paramLong.longValue());
  }
  
  public static void a(Parcel paramParcel, int paramInt, String paramString, boolean paramBoolean)
  {
    if (paramString == null) {
      return;
    }
    paramInt = r(paramParcel, paramInt);
    paramParcel.writeString(paramString);
    s(paramParcel, paramInt);
  }
  
  public static void a(Parcel paramParcel, int paramInt, List paramList, boolean paramBoolean)
  {
    if (paramList == null) {
      return;
    }
    int i1 = r(paramParcel, 1);
    int i2 = paramList.size();
    paramParcel.writeInt(i2);
    paramInt = 0;
    while (paramInt < i2)
    {
      paramParcel.writeInt(((Integer)paramList.get(paramInt)).intValue());
      paramInt += 1;
    }
    s(paramParcel, i1);
  }
  
  public static void a(Parcel paramParcel, int paramInt, short paramShort)
  {
    c(paramParcel, 3, 4);
    paramParcel.writeInt(paramShort);
  }
  
  public static void a(Parcel paramParcel, int paramInt, boolean paramBoolean)
  {
    c(paramParcel, paramInt, 4);
    if (paramBoolean) {}
    for (paramInt = 1;; paramInt = 0)
    {
      paramParcel.writeInt(paramInt);
      return;
    }
  }
  
  public static void a(Parcel paramParcel, int paramInt, byte[] paramArrayOfByte, boolean paramBoolean)
  {
    if (paramArrayOfByte == null) {
      return;
    }
    paramInt = r(paramParcel, paramInt);
    paramParcel.writeByteArray(paramArrayOfByte);
    s(paramParcel, paramInt);
  }
  
  public static void a(Parcel paramParcel, int paramInt, int[] paramArrayOfInt, boolean paramBoolean)
  {
    if (paramArrayOfInt == null) {
      return;
    }
    paramInt = r(paramParcel, 4);
    paramParcel.writeIntArray(paramArrayOfInt);
    s(paramParcel, paramInt);
  }
  
  public static void a(Parcel paramParcel, int paramInt1, Parcelable[] paramArrayOfParcelable, int paramInt2, boolean paramBoolean)
  {
    if (paramArrayOfParcelable == null) {
      return;
    }
    int i1 = r(paramParcel, paramInt1);
    int i2 = paramArrayOfParcelable.length;
    paramParcel.writeInt(i2);
    paramInt1 = 0;
    if (paramInt1 < i2)
    {
      Parcelable localParcelable = paramArrayOfParcelable[paramInt1];
      if (localParcelable == null) {
        paramParcel.writeInt(0);
      }
      for (;;)
      {
        paramInt1 += 1;
        break;
        a(paramParcel, localParcelable, paramInt2);
      }
    }
    s(paramParcel, i1);
  }
  
  public static void a(Parcel paramParcel, int paramInt, String[] paramArrayOfString, boolean paramBoolean)
  {
    if (paramArrayOfString == null) {
      return;
    }
    paramInt = r(paramParcel, paramInt);
    paramParcel.writeStringArray(paramArrayOfString);
    s(paramParcel, paramInt);
  }
  
  public static void a(Parcel paramParcel, Parcelable paramParcelable, int paramInt)
  {
    int i1 = paramParcel.dataPosition();
    paramParcel.writeInt(1);
    int i2 = paramParcel.dataPosition();
    paramParcelable.writeToParcel(paramParcel, paramInt);
    paramInt = paramParcel.dataPosition();
    paramParcel.setDataPosition(i1);
    paramParcel.writeInt(paramInt - i2);
    paramParcel.setDataPosition(paramInt);
  }
  
  public static void a(ParcelFileDescriptor paramParcelFileDescriptor)
  {
    if (paramParcelFileDescriptor != null) {}
    try
    {
      paramParcelFileDescriptor.close();
      return;
    }
    catch (IOException paramParcelFileDescriptor) {}
  }
  
  public static void a(Transition paramTransition, btg paramBtg)
  {
    if (paramTransition != null) {
      paramTransition.setEpicenterCallback(new aq(paramBtg));
    }
  }
  
  public static void a(KeyEvent paramKeyEvent)
  {
    paramKeyEvent.startTracking();
  }
  
  public static void a(LayoutInflater paramLayoutInflater, fn paramFn)
  {
    if (paramFn != null) {}
    for (paramFn = new fl(paramFn);; paramFn = null)
    {
      paramLayoutInflater.setFactory(paramFn);
      return;
    }
  }
  
  public static void a(MenuItem paramMenuItem, int paramInt)
  {
    paramMenuItem.setShowAsAction(paramInt);
  }
  
  public static void a(View paramView, float paramFloat)
  {
    paramView.setTranslationX(paramFloat);
  }
  
  public static void a(View paramView, int paramInt1, int paramInt2)
  {
    paramView.setScrollIndicators(paramInt1, paramInt2);
  }
  
  public static void a(View paramView, int paramInt, Paint paramPaint)
  {
    paramView.setLayerType(paramInt, paramPaint);
  }
  
  public static void a(View paramView, long paramLong)
  {
    paramView.animate().setDuration(paramLong);
  }
  
  public static void a(View paramView, ColorStateList paramColorStateList)
  {
    if ((paramView instanceof gk)) {
      ((gk)paramView).a(paramColorStateList);
    }
  }
  
  public static void a(View paramView, PorterDuff.Mode paramMode)
  {
    if ((paramView instanceof gk)) {
      ((gk)paramView).a(paramMode);
    }
  }
  
  public static void a(View paramView1, View paramView2, Object paramObject1, ArrayList paramArrayList1, Object paramObject2, ArrayList paramArrayList2, Object paramObject3, ArrayList paramArrayList3, Object paramObject4, ArrayList paramArrayList4, Map paramMap)
  {
    paramObject1 = (Transition)paramObject1;
    paramObject2 = (Transition)paramObject2;
    paramObject3 = (Transition)paramObject3;
    paramObject4 = (Transition)paramObject4;
    if (paramObject4 != null) {
      paramView1.getViewTreeObserver().addOnPreDrawListener(new ar(paramView1, paramObject1, paramArrayList1, paramObject2, paramArrayList2, paramObject3, paramArrayList3, paramMap, paramArrayList4, paramObject4, paramView2));
    }
  }
  
  public static void a(View paramView, Interpolator paramInterpolator)
  {
    paramView.animate().setInterpolator(paramInterpolator);
  }
  
  public static void a(View paramView, il paramIl)
  {
    paramView.animate().setListener(new ii(paramIl, paramView));
  }
  
  public static void a(View paramView, in paramIn)
  {
    ik localIk = null;
    if (paramIn != null) {
      localIk = new ik(paramIn, paramView);
    }
    paramView.animate().setUpdateListener(localIk);
  }
  
  public static void a(View paramView, CharSequence paramCharSequence)
  {
    if (paramView == null) {
      return;
    }
    if (TextUtils.isEmpty(paramCharSequence))
    {
      paramView.setVisibility(8);
      return;
    }
    ((TextView)paramView).setText(paramCharSequence);
    paramView.setVisibility(0);
  }
  
  public static void a(View paramView, Object paramObject)
  {
    paramView.setAccessibilityDelegate((View.AccessibilityDelegate)paramObject);
  }
  
  public static void a(View paramView, boolean paramBoolean)
  {
    paramView.setSaveFromParentEnabled(paramBoolean);
  }
  
  public static void a(ViewGroup.MarginLayoutParams paramMarginLayoutParams, int paramInt)
  {
    paramMarginLayoutParams.setMarginStart(paramInt);
  }
  
  public static void a(ViewGroup paramViewGroup, Object paramObject)
  {
    TransitionManager.beginDelayedTransition(paramViewGroup, (Transition)paramObject);
  }
  
  public static void a(ViewGroup paramViewGroup, boolean paramBoolean)
  {
    if (e == null) {}
    try
    {
      e = ViewGroup.class.getDeclaredMethod("setChildrenDrawingOrderEnabled", new Class[] { Boolean.TYPE });
      e.setAccessible(true);
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      for (;;)
      {
        try
        {
          e.invoke(paramViewGroup, new Object[] { Boolean.valueOf(paramBoolean) });
          return;
        }
        catch (IllegalAccessException paramViewGroup)
        {
          Log.e("ViewCompat", "Unable to invoke childrenDrawingOrderEnabled", paramViewGroup);
          return;
        }
        catch (IllegalArgumentException paramViewGroup)
        {
          Log.e("ViewCompat", "Unable to invoke childrenDrawingOrderEnabled", paramViewGroup);
          return;
        }
        catch (InvocationTargetException paramViewGroup)
        {
          Log.e("ViewCompat", "Unable to invoke childrenDrawingOrderEnabled", paramViewGroup);
        }
        localNoSuchMethodException = localNoSuchMethodException;
        Log.e("ViewCompat", "Unable to find childrenDrawingOrderEnabled", localNoSuchMethodException);
      }
    }
  }
  
  public static void a(ViewParent paramViewParent, View paramView)
  {
    try
    {
      paramViewParent.onStopNestedScroll(paramView);
      return;
    }
    catch (AbstractMethodError paramView)
    {
      Log.e("ViewParentCompat", "ViewParent " + paramViewParent + " does not implement interface " + "method onStopNestedScroll", paramView);
    }
  }
  
  public static void a(ViewParent paramViewParent, View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    try
    {
      paramViewParent.onNestedScroll(paramView, paramInt1, paramInt2, paramInt3, paramInt4);
      return;
    }
    catch (AbstractMethodError paramView)
    {
      Log.e("ViewParentCompat", "ViewParent " + paramViewParent + " does not implement interface " + "method onNestedScroll", paramView);
    }
  }
  
  public static void a(ViewParent paramViewParent, View paramView, int paramInt1, int paramInt2, int[] paramArrayOfInt)
  {
    try
    {
      paramViewParent.onNestedPreScroll(paramView, paramInt1, paramInt2, paramArrayOfInt);
      return;
    }
    catch (AbstractMethodError paramView)
    {
      Log.e("ViewParentCompat", "ViewParent " + paramViewParent + " does not implement interface " + "method onNestedPreScroll", paramView);
    }
  }
  
  public static void a(AccessibilityEvent paramAccessibilityEvent, int paramInt)
  {
    paramAccessibilityEvent.setContentChangeTypes(paramInt);
  }
  
  public static void a(CompoundButton paramCompoundButton, ColorStateList paramColorStateList)
  {
    paramCompoundButton.setButtonTintList(paramColorStateList);
  }
  
  public static void a(CompoundButton paramCompoundButton, PorterDuff.Mode paramMode)
  {
    paramCompoundButton.setButtonTintMode(paramMode);
  }
  
  public static void a(PopupWindow paramPopupWindow, int paramInt)
  {
    paramPopupWindow.setWindowLayoutType(paramInt);
  }
  
  public static void a(PopupWindow paramPopupWindow, View paramView, int paramInt1, int paramInt2, int paramInt3)
  {
    paramPopupWindow.showAsDropDown(paramView, paramInt1, paramInt2, paramInt3);
  }
  
  public static void a(PopupWindow paramPopupWindow, boolean paramBoolean)
  {
    paramPopupWindow.setOverlapAnchor(paramBoolean);
  }
  
  public static void a(TextView paramTextView, String paramString1, String paramString2, Intent paramIntent)
  {
    paramTextView.setLinksClickable(true);
    paramTextView.setMovementMethod(LinkMovementMethod.getInstance());
    paramTextView = SpannableString.valueOf(paramTextView.getText());
    paramString1 = c(paramString1).matcher(paramTextView);
    while (paramString1.find())
    {
      int i1 = paramString1.start();
      int i2 = paramString1.end();
      paramTextView.setSpan(new bpq(paramString2, null), i1, i2, 33);
    }
  }
  
  public static void a(TextView paramTextView, String paramString1, String paramString2, apq paramApq)
  {
    a(paramTextView, paramString1, paramString2, null, null, paramApq);
  }
  
  public static void a(TextView paramTextView, String paramString1, String paramString2, String paramString3, Bundle paramBundle, apq paramApq)
  {
    if ((TextUtils.isEmpty(paramString2)) || (TextUtils.isEmpty(paramString1)))
    {
      paramTextView.setVisibility(8);
      return;
    }
    paramTextView.setVisibility(0);
    paramTextView.setText(paramString1);
    paramTextView.setOnClickListener(new bpu(paramString2, paramString3, paramBundle, paramApq));
  }
  
  public static void a(TextView paramTextView, String... paramVarArgs)
  {
    int i1 = 0;
    while (i1 < paramVarArgs.length - 1)
    {
      a(paramTextView, paramVarArgs[i1], paramVarArgs[(i1 + 1)], null);
      i1 += 2;
    }
    paramTextView.setAutoLinkMask(1);
    paramTextView.setMovementMethod(LinkMovementMethod.getInstance());
  }
  
  public static void a(SafeParcelable paramSafeParcelable, Intent paramIntent, String paramString)
  {
    paramIntent.putExtra(paramString, a(paramSafeParcelable));
  }
  
  public static void a(dnb paramDnb, ctq paramCtq)
  {
    paramCtq = paramCtq.d;
    int i2 = paramCtq.length;
    int i1 = 0;
    while (i1 < i2)
    {
      paramDnb.a(dra.a(paramCtq[i1]));
      i1 += 1;
    }
  }
  
  public static void a(dnb paramDnb, ctv paramCtv)
  {
    if (paramCtv.d == null)
    {
      dnm.b("supplemental missing experimentSupplemental");
      return;
    }
    a(paramDnb, paramCtv.d);
    b(paramDnb, paramCtv.d);
    c(paramDnb, paramCtv.d);
  }
  
  public static void a(dxf paramDxf, StringBuilder paramStringBuilder, int paramInt)
  {
    Object localObject2 = new HashMap();
    Object localObject1 = paramDxf.getClass().getDeclaredMethods();
    int i2 = localObject1.length;
    int i1 = 0;
    while (i1 < i2)
    {
      localObject3 = localObject1[i1];
      if (((Method)localObject3).getParameterTypes().length == 0) {
        ((Map)localObject2).put(((Method)localObject3).getName(), localObject3);
      }
      i1 += 1;
    }
    Object localObject3 = paramDxf.getClass().getDeclaredFields();
    i2 = localObject3.length;
    i1 = 0;
    if (i1 < i2)
    {
      localObject1 = localObject3[i1].getName();
      String str1;
      String str2;
      if (((String)localObject1).endsWith("_FIELD_NUMBER"))
      {
        str1 = ((String)localObject1).substring(0, ((String)localObject1).length() - 13);
        str2 = x(str1);
        localObject1 = String.valueOf(str2);
        if (((String)localObject1).length() == 0) {
          break label267;
        }
        localObject1 = "get".concat((String)localObject1);
        label158:
        Method localMethod = (Method)((Map)localObject2).get(localObject1);
        localObject1 = String.valueOf(str2);
        if (((String)localObject1).length() == 0) {
          break label282;
        }
        localObject1 = "has".concat((String)localObject1);
        label197:
        localObject1 = (Method)((Map)localObject2).get(localObject1);
        if ((localMethod == null) || (localObject1 == null)) {
          break label297;
        }
        if (((Boolean)dwr.a((Method)localObject1, paramDxf, new Object[0])).booleanValue()) {
          a(paramStringBuilder, paramInt, str1.toLowerCase(), dwr.a(localMethod, paramDxf, new Object[0]));
        }
      }
      for (;;)
      {
        i1 += 1;
        break;
        label267:
        localObject1 = new String("get");
        break label158;
        label282:
        localObject1 = new String("has");
        break label197;
        label297:
        localObject1 = (Method)((Map)localObject2).get(String.valueOf(str2).length() + 7 + "get" + str2 + "List");
        if (localObject1 != null) {
          a(paramStringBuilder, paramInt, str1.toLowerCase(), dwr.a((Method)localObject1, paramDxf, new Object[0]));
        }
      }
    }
    if ((paramDxf instanceof dwt))
    {
      localObject1 = ((dwt)paramDxf).a;
      if (((dwo)localObject1).b) {
        localObject1 = new dxd(((dwo)localObject1).a.entrySet().iterator());
      }
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (Map.Entry)((Iterator)localObject1).next();
        i1 = ((dwq)((Map.Entry)localObject2).getKey()).c();
        a(paramStringBuilder, paramInt, 13 + "[" + i1 + "]", ((Map.Entry)localObject2).getValue());
        continue;
        localObject1 = ((dwo)localObject1).a.entrySet().iterator();
      }
    }
    if (((dwr)paramDxf).n != null) {
      ((dwr)paramDxf).n.a(paramStringBuilder, paramInt);
    }
  }
  
  public static void a(Closeable paramCloseable)
  {
    try
    {
      paramCloseable.close();
      return;
    }
    catch (IOException paramCloseable) {}
  }
  
  public static void a(InputStream paramInputStream, OutputStream paramOutputStream)
  {
    byte[] arrayOfByte = new byte['Ѐ'];
    for (;;)
    {
      int i1 = paramInputStream.read(arrayOfByte);
      if (i1 == -1) {
        return;
      }
      paramOutputStream.write(arrayOfByte, 0, i1);
    }
  }
  
  public static void a(Object paramObject, int paramInt)
  {
    ((AccessibilityNodeInfo)paramObject).addAction(paramInt);
  }
  
  public static void a(Object paramObject, int paramInt1, int paramInt2)
  {
    ((EdgeEffect)paramObject).setSize(paramInt1, paramInt2);
  }
  
  public static void a(Object paramObject1, int paramInt, Object paramObject2)
  {
    ((MediaRouter)paramObject1).selectRoute(8388611, (MediaRouter.RouteInfo)paramObject2);
  }
  
  public static void a(Object paramObject1, int paramInt1, Object paramObject2, int paramInt2)
  {
    ((MediaRouter)paramObject1).addCallback(paramInt1, (MediaRouter.Callback)paramObject2, paramInt2);
  }
  
  public static void a(Object paramObject, Bitmap paramBitmap)
  {
    ((MediaDescription.Builder)paramObject).setIconBitmap(paramBitmap);
  }
  
  public static void a(Object paramObject, Rect paramRect)
  {
    ((AccessibilityNodeInfo)paramObject).getBoundsInParent(paramRect);
  }
  
  public static void a(Object paramObject, Uri paramUri)
  {
    ((MediaDescription.Builder)paramObject).setIconUri(paramUri);
  }
  
  public static void a(Object paramObject, Bundle paramBundle)
  {
    ((MediaDescription.Builder)paramObject).setExtras(paramBundle);
  }
  
  public static void a(Object paramObject, Parcel paramParcel, int paramInt)
  {
    ((MediaDescription)paramObject).writeToParcel(paramParcel, paramInt);
  }
  
  public static void a(Object paramObject, View paramView)
  {
    ((Transition)paramObject).setEpicenterCallback(new ao(c(paramView)));
  }
  
  public static void a(Object paramObject, View paramView, int paramInt)
  {
    ((View.AccessibilityDelegate)paramObject).sendAccessibilityEvent(paramView, paramInt);
  }
  
  public static void a(Object paramObject1, View paramView, Object paramObject2)
  {
    ((View.AccessibilityDelegate)paramObject1).onInitializeAccessibilityNodeInfo(paramView, (AccessibilityNodeInfo)paramObject2);
  }
  
  public static void a(Object paramObject, View paramView, Map paramMap, ArrayList paramArrayList)
  {
    paramObject = (TransitionSet)paramObject;
    paramArrayList.clear();
    paramArrayList.addAll(paramMap.values());
    paramMap = paramObject.getTargets();
    paramMap.clear();
    int i2 = paramArrayList.size();
    int i1 = 0;
    while (i1 < i2)
    {
      a(paramMap, (View)paramArrayList.get(i1));
      i1 += 1;
    }
    paramArrayList.add(paramView);
    b(paramObject, paramArrayList);
  }
  
  public static void a(Object paramObject, View paramView, boolean paramBoolean)
  {
    ((Transition)paramObject).excludeTarget(paramView, paramBoolean);
  }
  
  public static void a(Object paramObject, CharSequence paramCharSequence)
  {
    ((MediaDescription.Builder)paramObject).setTitle(paramCharSequence);
  }
  
  public static void a(Object paramObject1, Object paramObject2)
  {
    ((MediaController)paramObject1).unregisterCallback((MediaController.Callback)paramObject2);
  }
  
  public static void a(Object paramObject1, Object paramObject2, Handler paramHandler)
  {
    ((MediaController)paramObject1).registerCallback((MediaController.Callback)paramObject2, paramHandler);
  }
  
  public static void a(Object paramObject1, Object paramObject2, View paramView1, buo paramBuo, View paramView2, btg paramBtg, Map paramMap1, ArrayList paramArrayList1, Map paramMap2, Map paramMap3, ArrayList paramArrayList2)
  {
    if ((paramObject1 != null) || (paramObject2 != null))
    {
      paramObject1 = (Transition)paramObject1;
      if (paramObject1 != null) {
        paramObject1.addTarget(paramView2);
      }
      if (paramObject2 != null) {
        a(paramObject2, paramView2, paramMap2, paramArrayList2);
      }
      paramView1.getViewTreeObserver().addOnPreDrawListener(new ap(paramView1, paramObject1, paramView2, paramBuo, paramMap1, paramMap3, paramArrayList1));
      a(paramObject1, paramBtg);
    }
  }
  
  public static void a(Object paramObject, String paramString)
  {
    ((MediaDescription.Builder)paramObject).setMediaId(paramString);
  }
  
  public static void a(Object paramObject, StringBuilder paramStringBuilder)
  {
    if (paramObject == null)
    {
      paramStringBuilder.append("null");
      return;
    }
    String str2 = paramObject.getClass().getSimpleName();
    String str1;
    if (str2 != null)
    {
      str1 = str2;
      if (str2.length() > 0) {}
    }
    else
    {
      str2 = paramObject.getClass().getName();
      int i1 = str2.lastIndexOf('.');
      str1 = str2;
      if (i1 > 0) {
        str1 = str2.substring(i1 + 1);
      }
    }
    paramStringBuilder.append(str1);
    paramStringBuilder.append('{');
    paramStringBuilder.append(Integer.toHexString(System.identityHashCode(paramObject)));
  }
  
  public static void a(Object paramObject, ArrayList paramArrayList)
  {
    paramObject = (Transition)paramObject;
    int i1;
    if ((paramObject instanceof TransitionSet))
    {
      paramObject = (TransitionSet)paramObject;
      int i2 = paramObject.getTransitionCount();
      i1 = 0;
      while (i1 < i2)
      {
        a(paramObject.getTransitionAt(i1), paramArrayList);
        i1 += 1;
      }
    }
    if (!a(paramObject))
    {
      List localList = paramObject.getTargets();
      if ((localList != null) && (localList.size() == paramArrayList.size()) && (localList.containsAll(paramArrayList)))
      {
        i1 = paramArrayList.size() - 1;
        while (i1 >= 0)
        {
          paramObject.removeTarget((View)paramArrayList.get(i1));
          i1 -= 1;
        }
      }
    }
  }
  
  public static void a(Object paramObject, boolean paramBoolean)
  {
    ((AccessibilityNodeInfo)paramObject).setClickable(paramBoolean);
  }
  
  public static void a(String paramString)
  {
    if (Build.VERSION.SDK_INT >= 18) {
      bva.a(paramString);
    }
  }
  
  public static void a(String paramString, Object paramObject, StringBuffer paramStringBuffer1, StringBuffer paramStringBuffer2)
  {
    if (paramObject != null)
    {
      if (!(paramObject instanceof ctd)) {
        break label440;
      }
      int i4 = paramStringBuffer1.length();
      if (paramString != null)
      {
        paramStringBuffer2.append(paramStringBuffer1).append(p(paramString)).append(" <\n");
        paramStringBuffer1.append("  ");
      }
      Class localClass = paramObject.getClass();
      Object localObject1 = localClass.getFields();
      int i5 = localObject1.length;
      int i1 = 0;
      String str;
      Object localObject2;
      if (i1 < i5)
      {
        Object localObject3 = localObject1[i1];
        i2 = localObject3.getModifiers();
        str = localObject3.getName();
        if ((!"cachedSize".equals(str)) && ((i2 & 0x1) == 1) && ((i2 & 0x8) != 8) && (!str.startsWith("_")) && (!str.endsWith("_")))
        {
          localObject2 = localObject3.getType();
          localObject3 = localObject3.get(paramObject);
          if (!((Class)localObject2).isArray()) {
            break label251;
          }
          if (((Class)localObject2).getComponentType() != Byte.TYPE) {
            break label200;
          }
          a(str, localObject3, paramStringBuffer1, paramStringBuffer2);
        }
        for (;;)
        {
          i1 += 1;
          break;
          label200:
          if (localObject3 == null) {}
          for (i2 = 0;; i2 = Array.getLength(localObject3))
          {
            int i3 = 0;
            while (i3 < i2)
            {
              a(str, Array.get(localObject3, i3), paramStringBuffer1, paramStringBuffer2);
              i3 += 1;
            }
            break;
          }
          label251:
          a(str, localObject3, paramStringBuffer1, paramStringBuffer2);
        }
      }
      localObject1 = localClass.getMethods();
      int i2 = localObject1.length;
      i1 = 0;
      while (i1 < i2)
      {
        str = localObject1[i1].getName();
        if (str.startsWith("set")) {
          str = str.substring(3);
        }
        for (;;)
        {
          try
          {
            localObject2 = localClass.getMethod("has" + str, new Class[0]);
            if (!((Boolean)((Method)localObject2).invoke(paramObject, new Object[0])).booleanValue()) {}
          }
          catch (NoSuchMethodException localNoSuchMethodException2)
          {
            continue;
          }
          try
          {
            localObject2 = localClass.getMethod("get" + str, new Class[0]);
            a(str, ((Method)localObject2).invoke(paramObject, new Object[0]), paramStringBuffer1, paramStringBuffer2);
          }
          catch (NoSuchMethodException localNoSuchMethodException1) {}
        }
        i1 += 1;
      }
      if (paramString != null)
      {
        paramStringBuffer1.setLength(i4);
        paramStringBuffer2.append(paramStringBuffer1).append(">\n");
      }
    }
    return;
    label440:
    paramString = p(paramString);
    paramStringBuffer2.append(paramStringBuffer1).append(paramString).append(": ");
    if ((paramObject instanceof String))
    {
      paramString = q((String)paramObject);
      paramStringBuffer2.append("\"").append(paramString).append("\"");
    }
    for (;;)
    {
      paramStringBuffer2.append("\n");
      return;
      if ((paramObject instanceof byte[])) {
        a((byte[])paramObject, paramStringBuffer2);
      } else {
        paramStringBuffer2.append(paramObject);
      }
    }
  }
  
  public static void a(String paramString1, String paramString2)
  {
    t(3);
  }
  
  public static void a(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (t(5)) {
      Log.w(paramString1, paramString2, paramThrowable);
    }
  }
  
  public static void a(String paramString, Throwable paramThrowable)
  {
    e(3);
  }
  
  public static void a(String paramString, JSONObject paramJSONObject, bfg paramBfg)
  {
    int i1 = 0;
    String str = null;
    if (paramString == null)
    {
      paramBfg.L = null;
      return;
    }
    if (paramJSONObject != null) {}
    for (paramJSONObject = paramJSONObject.optJSONObject("timezone");; paramJSONObject = null)
    {
      if (paramJSONObject != null)
      {
        str = paramJSONObject.optString("display_string", null);
        i1 = paramJSONObject.optInt("offset", 0);
      }
      paramBfg.L = new bfc(paramString, str, i1);
      return;
    }
  }
  
  public static void a(String paramString, Object... paramVarArgs)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString);
    localStringBuilder.append("(");
    int i2 = paramVarArgs.length;
    paramString = "";
    int i1 = 0;
    if (i1 < i2)
    {
      Object localObject = paramVarArgs[i1];
      localStringBuilder.append(paramString);
      if ((localObject instanceof Bundle)) {
        localStringBuilder.append(dlj.a((Bundle)localObject));
      }
      for (;;)
      {
        paramString = ", ";
        i1 += 1;
        break;
        localStringBuilder.append(localObject);
      }
    }
    localStringBuilder.append(")");
    if (Log.isLoggable("PeopleClientCall", 2)) {
      new Throwable("STACK TRACE (It's not crash!)");
    }
  }
  
  public static void a(StringBuilder paramStringBuilder, int paramInt, String paramString, Object paramObject)
  {
    int i2 = 0;
    if ((paramObject instanceof List))
    {
      paramObject = ((List)paramObject).iterator();
      while (paramObject.hasNext()) {
        a(paramStringBuilder, paramInt, paramString, paramObject.next());
      }
    }
    paramStringBuilder.append('\n');
    int i1 = 0;
    while (i1 < paramInt)
    {
      paramStringBuilder.append(' ');
      i1 += 1;
    }
    paramStringBuilder.append(paramString);
    if ((paramObject instanceof String))
    {
      paramStringBuilder.append(": \"").append(y((String)paramObject)).append('"');
      return;
    }
    if ((paramObject instanceof dwg))
    {
      paramStringBuilder.append(": \"").append(a((dwg)paramObject)).append('"');
      return;
    }
    if ((paramObject instanceof dwr))
    {
      paramStringBuilder.append(" {");
      a((dwr)paramObject, paramStringBuilder, paramInt + 2);
      paramStringBuilder.append("\n");
      i1 = i2;
      while (i1 < paramInt)
      {
        paramStringBuilder.append(' ');
        i1 += 1;
      }
      paramStringBuilder.append("}");
      return;
    }
    paramStringBuilder.append(": ").append(paramObject.toString());
  }
  
  public static void a(ArrayList paramArrayList, View paramView)
  {
    if (paramView.getVisibility() == 0)
    {
      if (!(paramView instanceof ViewGroup)) {
        break label61;
      }
      paramView = (ViewGroup)paramView;
      if (!paramView.isTransitionGroup()) {
        break label33;
      }
      paramArrayList.add(paramView);
    }
    for (;;)
    {
      return;
      label33:
      int i2 = paramView.getChildCount();
      int i1 = 0;
      while (i1 < i2)
      {
        a(paramArrayList, paramView.getChildAt(i1));
        i1 += 1;
      }
    }
    label61:
    paramArrayList.add(paramView);
  }
  
  public static void a(List paramList, View paramView)
  {
    int i3 = paramList.size();
    if (a(paramList, paramView, i3)) {}
    for (;;)
    {
      return;
      paramList.add(paramView);
      int i1 = i3;
      while (i1 < paramList.size())
      {
        paramView = (View)paramList.get(i1);
        if ((paramView instanceof ViewGroup))
        {
          paramView = (ViewGroup)paramView;
          int i4 = paramView.getChildCount();
          int i2 = 0;
          while (i2 < i4)
          {
            View localView = paramView.getChildAt(i2);
            if (!a(paramList, localView, i3)) {
              paramList.add(localView);
            }
            i2 += 1;
          }
        }
        i1 += 1;
      }
    }
  }
  
  public static void a(Map paramMap, View paramView)
  {
    if (paramView.getVisibility() == 0)
    {
      String str = paramView.getTransitionName();
      if (str != null) {
        paramMap.put(str, paramView);
      }
      if ((paramView instanceof ViewGroup))
      {
        paramView = (ViewGroup)paramView;
        int i2 = paramView.getChildCount();
        int i1 = 0;
        while (i1 < i2)
        {
          a(paramMap, paramView.getChildAt(i1));
          i1 += 1;
        }
      }
    }
  }
  
  public static void a(JSONObject paramJSONObject, bfg paramBfg)
  {
    int i1 = paramJSONObject.optInt("version", 0);
    paramBfg.a = i1;
    paramBfg.b = paramJSONObject.optString("name", null);
    if (i1 < 7)
    {
      b(paramJSONObject, paramBfg);
      return;
    }
    c(paramJSONObject, paramBfg);
  }
  
  public static void a(XmlPullParser paramXmlPullParser)
  {
    if (paramXmlPullParser.getEventType() != 2) {
      throw new IllegalStateException();
    }
    int i1 = 1;
    while (i1 != 0) {
      switch (paramXmlPullParser.next())
      {
      default: 
        break;
      case 2: 
        i1 += 1;
        break;
      case 3: 
        i1 -= 1;
      }
    }
  }
  
  public static void a(r paramR, int paramInt, bpm paramBpm)
  {
    paramBpm = new bpl(paramBpm);
    try
    {
      Dialog localDialog = cbu.a(paramInt, paramR, 9001);
      if (localDialog != null)
      {
        localDialog.setOnDismissListener(paramBpm);
        localDialog.show();
        return;
      }
    }
    catch (Resources.NotFoundException localNotFoundException)
    {
      bpi.a("PlayServicesUtil", "Could not show Google Play services error dialog for result code %d", new Object[] { Integer.valueOf(paramInt) });
      paramR = new AlertDialog.Builder(paramR).setMessage(cci.dp).setPositiveButton(cci.F, null).create();
      paramR.setOnDismissListener(paramBpm);
      paramR.show();
    }
  }
  
  public static void a(r paramR, bpm paramBpm)
  {
    int i1 = cbu.a(paramR);
    if (i1 == 0)
    {
      paramBpm.a(true);
      return;
    }
    bpi.a("PlayServicesUtil", "Google Play services not available", new Object[0]);
    a(paramR, i1, paramBpm);
  }
  
  public static void a(r paramR, ConnectionResult paramConnectionResult, bpm paramBpm)
  {
    if (paramConnectionResult.a()) {
      try
      {
        paramConnectionResult.a(paramR, 9001);
        return;
      }
      catch (IntentSender.SendIntentException paramR)
      {
        bpi.a("PlayServicesUtil", paramR, "Failed to resolve Google Play Services connection failure", new Object[0]);
        return;
      }
    }
    a(paramR, paramConnectionResult.c, paramBpm);
  }
  
  public static void a(boolean paramBoolean)
  {
    if (!paramBoolean) {
      throw new IllegalStateException();
    }
  }
  
  public static void a(boolean paramBoolean, Object paramObject)
  {
    if (!paramBoolean) {
      throw new IllegalStateException(String.valueOf(paramObject));
    }
  }
  
  public static void a(byte[] paramArrayOfByte, StringBuffer paramStringBuffer)
  {
    if (paramArrayOfByte == null)
    {
      paramStringBuffer.append("\"\"");
      return;
    }
    paramStringBuffer.append('"');
    int i1 = 0;
    if (i1 < paramArrayOfByte.length)
    {
      int i2 = paramArrayOfByte[i1] & 0xFF;
      if ((i2 == 92) || (i2 == 34)) {
        paramStringBuffer.append('\\').append((char)i2);
      }
      for (;;)
      {
        i1 += 1;
        break;
        if ((i2 >= 32) && (i2 < 127)) {
          paramStringBuffer.append((char)i2);
        } else {
          paramStringBuffer.append(String.format("\\%03o", new Object[] { Integer.valueOf(i2) }));
        }
      }
    }
    paramStringBuffer.append('"');
  }
  
  public static boolean a(int paramInt, r paramR)
  {
    if ((paramInt == 82) && ("LGE".equalsIgnoreCase(Build.MANUFACTURER)) && (Build.VERSION.SDK_INT == 16))
    {
      paramR.openOptionsMenu();
      return true;
    }
    return false;
  }
  
  public static boolean a(Activity paramActivity, Intent paramIntent)
  {
    return paramActivity.shouldUpRecreateTask(paramIntent);
  }
  
  public static boolean a(Activity paramActivity, String paramString)
  {
    return paramActivity.shouldShowRequestPermissionRationale(paramString);
  }
  
  public static boolean a(ActivityManager paramActivityManager)
  {
    if (Build.VERSION.SDK_INT >= 19) {
      return mv.a(paramActivityManager);
    }
    return false;
  }
  
  public static boolean a(Context paramContext, Intent paramIntent, String paramString)
  {
    if ((bov.k()) && (paramIntent.getDataString().contains("market://")) && (b(paramContext, paramIntent.getDataString()))) {
      return true;
    }
    PackageManager localPackageManager = paramContext.getPackageManager();
    if (!TextUtils.isEmpty(paramString))
    {
      paramIntent.setPackage(paramString);
      if (localPackageManager.resolveActivity(paramIntent, 65536) != null)
      {
        paramContext.startActivity(paramIntent);
        return true;
      }
    }
    paramIntent.setPackage(null);
    if (localPackageManager.resolveActivity(paramIntent, 65536) != null)
    {
      paramContext.startActivity(paramIntent);
      return true;
    }
    return false;
  }
  
  public static boolean a(Resources paramResources)
  {
    if (paramResources == null) {}
    for (;;)
    {
      return false;
      if ((paramResources.getConfiguration().screenLayout & 0xF) > 3) {}
      for (int i1 = 1; ((bva.j()) && (i1 != 0)) || (b(paramResources)); i1 = 0) {
        return true;
      }
    }
  }
  
  public static boolean a(Messenger paramMessenger)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (paramMessenger != null) {}
    try
    {
      paramMessenger = paramMessenger.getBinder();
      bool1 = bool2;
      if (paramMessenger != null) {
        bool1 = true;
      }
      return bool1;
    }
    catch (NullPointerException paramMessenger) {}
    return false;
  }
  
  public static boolean a(Transition paramTransition)
  {
    return (!a(paramTransition.getTargetIds())) || (!a(paramTransition.getTargetNames())) || (!a(paramTransition.getTargetTypes()));
  }
  
  public static boolean a(View paramView, int paramInt)
  {
    return paramView.canScrollHorizontally(paramInt);
  }
  
  public static boolean a(ViewParent paramViewParent, View paramView, float paramFloat1, float paramFloat2)
  {
    try
    {
      boolean bool = paramViewParent.onNestedPreFling(paramView, paramFloat1, paramFloat2);
      return bool;
    }
    catch (AbstractMethodError paramView)
    {
      Log.e("ViewParentCompat", "ViewParent " + paramViewParent + " does not implement interface " + "method onNestedPreFling", paramView);
    }
    return false;
  }
  
  public static boolean a(ViewParent paramViewParent, View paramView, float paramFloat1, float paramFloat2, boolean paramBoolean)
  {
    try
    {
      paramBoolean = paramViewParent.onNestedFling(paramView, paramFloat1, paramFloat2, paramBoolean);
      return paramBoolean;
    }
    catch (AbstractMethodError paramView)
    {
      Log.e("ViewParentCompat", "ViewParent " + paramViewParent + " does not implement interface " + "method onNestedFling", paramView);
    }
    return false;
  }
  
  public static boolean a(ViewParent paramViewParent, View paramView1, View paramView2, int paramInt)
  {
    try
    {
      boolean bool = paramViewParent.onStartNestedScroll(paramView1, paramView2, paramInt);
      return bool;
    }
    catch (AbstractMethodError paramView1)
    {
      Log.e("ViewParentCompat", "ViewParent " + paramViewParent + " does not implement interface " + "method onStartNestedScroll", paramView1);
    }
    return false;
  }
  
  public static boolean a(cuu paramCuu, cus paramCus, long paramLong, String... paramVarArgs)
  {
    if ((paramCuu == null) || (paramCus == null)) {
      return false;
    }
    return paramCuu.a(paramCus, paramLong, paramVarArgs);
  }
  
  public static boolean a(cuu paramCuu, cus paramCus, String... paramVarArgs)
  {
    if ((paramCuu == null) || (paramCus == null)) {}
    while ((!paramCuu.a) || (paramCus == null)) {
      return false;
    }
    return paramCuu.a(paramCus, bvd.a().g.b(), paramVarArgs);
  }
  
  public static boolean a(dlr paramDlr)
  {
    boolean bool1;
    if (paramDlr == null) {
      bool1 = false;
    }
    boolean bool2;
    do
    {
      return bool1;
      if (!(paramDlr instanceof cdc)) {
        break;
      }
      bool2 = ((cdc)paramDlr).a();
      bool1 = bool2;
    } while (bool2);
    Log.w("Utils", "Owner is freezable and isDataValid returned false -- invalid Owner!");
    return bool2;
    return true;
  }
  
  public static boolean a(Object paramObject, float paramFloat)
  {
    ((EdgeEffect)paramObject).onPull(paramFloat);
    return true;
  }
  
  public static boolean a(Object paramObject, float paramFloat1, float paramFloat2)
  {
    ((EdgeEffect)paramObject).onPull(paramFloat1, paramFloat2);
    return true;
  }
  
  public static boolean a(Object paramObject, Canvas paramCanvas)
  {
    return ((EdgeEffect)paramObject).draw(paramCanvas);
  }
  
  public static boolean a(Object paramObject, View paramView, int paramInt, Bundle paramBundle)
  {
    return ((View.AccessibilityDelegate)paramObject).performAccessibilityAction(paramView, paramInt, paramBundle);
  }
  
  public static boolean a(Object paramObject, View paramView, AccessibilityEvent paramAccessibilityEvent)
  {
    return ((View.AccessibilityDelegate)paramObject).dispatchPopulateAccessibilityEvent(paramView, paramAccessibilityEvent);
  }
  
  public static boolean a(Object paramObject, ViewGroup paramViewGroup, View paramView, AccessibilityEvent paramAccessibilityEvent)
  {
    return ((View.AccessibilityDelegate)paramObject).onRequestSendAccessibilityEvent(paramViewGroup, paramView, paramAccessibilityEvent);
  }
  
  public static boolean a(List paramList)
  {
    return (paramList == null) || (paramList.isEmpty());
  }
  
  public static boolean a(List paramList, View paramView, int paramInt)
  {
    boolean bool2 = false;
    int i1 = 0;
    for (;;)
    {
      boolean bool1 = bool2;
      if (i1 < paramInt)
      {
        if (paramList.get(i1) == paramView) {
          bool1 = true;
        }
      }
      else {
        return bool1;
      }
      i1 += 1;
    }
  }
  
  public static byte[] a(SafeParcelable paramSafeParcelable)
  {
    Parcel localParcel = Parcel.obtain();
    paramSafeParcelable.writeToParcel(localParcel, 0);
    paramSafeParcelable = localParcel.marshall();
    localParcel.recycle();
    return paramSafeParcelable;
  }
  
  public static void aa(Object paramObject)
  {
    ((EdgeEffect)paramObject).finish();
  }
  
  public static boolean ab(Object paramObject)
  {
    paramObject = (EdgeEffect)paramObject;
    paramObject.onRelease();
    return paramObject.isFinished();
  }
  
  public static float ac(Object paramObject)
  {
    return ((OverScroller)paramObject).getCurrVelocity();
  }
  
  public static List ad(Object paramObject)
  {
    paramObject = (MediaRouter)paramObject;
    int i2 = paramObject.getRouteCount();
    ArrayList localArrayList = new ArrayList(i2);
    int i1 = 0;
    while (i1 < i2)
    {
      localArrayList.add(paramObject.getRouteAt(i1));
      i1 += 1;
    }
    return localArrayList;
  }
  
  public static int ae(Object paramObject)
  {
    return ((MediaRouter.RouteInfo)paramObject).getSupportedTypes();
  }
  
  public static int af(Object paramObject)
  {
    return ((MediaRouter.RouteInfo)paramObject).getPlaybackType();
  }
  
  public static int ag(Object paramObject)
  {
    return ((MediaRouter.RouteInfo)paramObject).getPlaybackStream();
  }
  
  public static int ah(Object paramObject)
  {
    return ((MediaRouter.RouteInfo)paramObject).getVolume();
  }
  
  public static int ai(Object paramObject)
  {
    return ((MediaRouter.RouteInfo)paramObject).getVolumeMax();
  }
  
  public static int aj(Object paramObject)
  {
    return ((MediaRouter.RouteInfo)paramObject).getVolumeHandling();
  }
  
  public static Object ak(Object paramObject)
  {
    return ((MediaRouter.RouteInfo)paramObject).getTag();
  }
  
  public static Object al(Object paramObject)
  {
    return ((MediaRouter)paramObject).getDefaultRoute();
  }
  
  public static void am(Object paramObject)
  {
    if (paramObject == null) {
      throw new IllegalArgumentException("null reference");
    }
  }
  
  public static cfl an(Object paramObject)
  {
    return new cfl(paramObject);
  }
  
  public static Object ao(Object paramObject)
  {
    if (paramObject == null) {
      throw new NullPointerException("null reference");
    }
    return paramObject;
  }
  
  public static ctx ap(Object paramObject)
  {
    return dra.a(aq(paramObject));
  }
  
  public static Object aq(Object paramObject)
  {
    if ((paramObject instanceof JSONArray)) {
      throw new RuntimeException("JSONArrays are not supported");
    }
    if (JSONObject.NULL.equals(paramObject)) {
      throw new RuntimeException("JSON nulls are not supported");
    }
    Object localObject = paramObject;
    if ((paramObject instanceof JSONObject))
    {
      paramObject = (JSONObject)paramObject;
      localObject = new HashMap();
      Iterator localIterator = paramObject.keys();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        ((Map)localObject).put(str, aq(paramObject.get(str)));
      }
    }
    return localObject;
  }
  
  public static char b(long paramLong)
  {
    return Character.toChars((int)(paramLong % 8L) + 50)[0];
  }
  
  public static float b(VelocityTracker paramVelocityTracker, int paramInt)
  {
    return paramVelocityTracker.getYVelocity(paramInt);
  }
  
  public static int b(int paramInt)
  {
    return KeyEvent.normalizeMetaState(paramInt);
  }
  
  public static int b(int paramInt1, int paramInt2)
  {
    return 255 - (255 - paramInt2) * (255 - paramInt1) / 255;
  }
  
  public static int b(int paramInt1, int paramInt2, int paramInt3)
  {
    return View.resolveSizeAndState(paramInt1, paramInt2, paramInt3);
  }
  
  public static int b(aah paramAah, zj paramZj, View paramView1, View paramView2, zy paramZy, boolean paramBoolean)
  {
    if ((paramZy.m() == 0) || (paramAah.a() == 0) || (paramView1 == null) || (paramView2 == null)) {
      return 0;
    }
    if (!paramBoolean) {
      return paramAah.a();
    }
    int i1 = paramZj.b(paramView2);
    int i2 = paramZj.a(paramView1);
    int i3 = Math.abs(paramZy.a(paramView1) - paramZy.a(paramView2));
    return (int)((i1 - i2) / (i3 + 1) * paramAah.a());
  }
  
  public static int b(Context paramContext, int paramInt)
  {
    return paramContext.getColor(paramInt);
  }
  
  public static int b(Drawable paramDrawable)
  {
    return paramDrawable.getLayoutDirection();
  }
  
  public static int b(Parcel paramParcel)
  {
    return paramParcel.readInt();
  }
  
  public static int b(MotionEvent paramMotionEvent)
  {
    return paramMotionEvent.getSource();
  }
  
  public static int b(MotionEvent paramMotionEvent, int paramInt)
  {
    return paramMotionEvent.getPointerId(paramInt);
  }
  
  public static int b(ViewGroup.MarginLayoutParams paramMarginLayoutParams)
  {
    return paramMarginLayoutParams.getMarginEnd();
  }
  
  public static int b(String paramString, int paramInt)
  {
    while (paramInt < paramString.length())
    {
      int i1 = paramString.charAt(paramInt);
      if ((i1 != 32) && (i1 != 9)) {
        break;
      }
      paramInt += 1;
    }
    return paramInt;
  }
  
  public static long b(String paramString)
  {
    try
    {
      long l1 = DateUtils.parseDate(paramString).getTime();
      return l1;
    }
    catch (DateParseException paramString) {}
    return 0L;
  }
  
  public static Bitmap b(Bitmap paramBitmap)
  {
    int i3 = 0;
    if (paramBitmap == null) {
      return null;
    }
    int i4 = paramBitmap.getWidth();
    int i1 = paramBitmap.getHeight();
    int i2;
    if (i4 >= i1)
    {
      i3 = i4 / 2 - i1 / 2;
      i2 = 0;
    }
    for (;;)
    {
      Bitmap localBitmap = Bitmap.createBitmap(i1, i1, Bitmap.Config.ARGB_8888);
      Canvas localCanvas = new Canvas(localBitmap);
      Paint localPaint = new Paint(1);
      localPaint.setColor(-16777216);
      localCanvas.drawCircle(i1 / 2, i1 / 2, i1 / 2, localPaint);
      localPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
      localCanvas.drawBitmap(paramBitmap, i3, i2, localPaint);
      return localBitmap;
      i2 = i1 / 2 - i4 / 2;
      i1 = i4;
    }
  }
  
  public static Bitmap b(ParcelFileDescriptor paramParcelFileDescriptor)
  {
    if (paramParcelFileDescriptor == null) {
      return null;
    }
    paramParcelFileDescriptor = new FileInputStream(paramParcelFileDescriptor.getFileDescriptor());
    try
    {
      Bitmap localBitmap = BitmapFactory.decodeStream(paramParcelFileDescriptor);
      return localBitmap;
    }
    finally
    {
      a(paramParcelFileDescriptor);
    }
  }
  
  public static Bitmap b(Object paramObject, String paramString)
  {
    return ((MediaMetadata)paramObject).getBitmap(paramString);
  }
  
  public static Parcelable.Creator b(dm paramDm)
  {
    return new dn(paramDm);
  }
  
  public static MenuItem b(MenuItem paramMenuItem, int paramInt)
  {
    return paramMenuItem.setActionView(paramInt);
  }
  
  public static ctu b(ctx paramCtx)
  {
    if ((ctu)paramCtx.a(ctu.c) == null) {
      t("Expected a ServingValue and didn't get one. Value is: " + paramCtx);
    }
    return (ctu)paramCtx.a(ctu.c);
  }
  
  public static Object b(Context paramContext)
  {
    return new EdgeEffect(paramContext);
  }
  
  public static Object b(Object paramObject)
  {
    if (paramObject == null) {}
    do
    {
      return null;
      paramObject = (Transition)paramObject;
    } while (paramObject == null);
    TransitionSet localTransitionSet = new TransitionSet();
    localTransitionSet.addTransition(paramObject);
    return localTransitionSet;
  }
  
  public static Object b(Object paramObject, View paramView)
  {
    return ((View.AccessibilityDelegate)paramObject).getAccessibilityNodeProvider(paramView);
  }
  
  public static String b(Activity paramActivity, String paramString)
  {
    Object localObject = null;
    try
    {
      paramString = paramActivity.getPackageManager().getPackageInfo(paramString, 0);
      paramActivity = localObject;
      if (paramString != null) {
        paramActivity = paramString.versionName;
      }
      return paramActivity;
    }
    catch (PackageManager.NameNotFoundException paramActivity) {}
    return null;
  }
  
  public static String b(Context paramContext, bfj paramBfj)
  {
    switch (bpw.a[paramBfj.ordinal()])
    {
    default: 
      return paramContext.getString(cci.bk);
    case 1: 
      return paramContext.getString(cci.bj);
    case 2: 
      return paramContext.getString(cci.bm);
    case 3: 
      return paramContext.getString(cci.bo);
    }
    return paramContext.getString(cci.bg);
  }
  
  public static String b(View paramView)
  {
    return paramView.getTransitionName();
  }
  
  public static String b(XmlPullParser paramXmlPullParser)
  {
    String str = paramXmlPullParser.nextText();
    if (paramXmlPullParser.getEventType() != 3) {
      paramXmlPullParser.nextTag();
    }
    return str;
  }
  
  public static String b(byte[] paramArrayOfByte)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i2 = paramArrayOfByte.length;
    int i1 = 0;
    while (i1 < i2)
    {
      int i3 = paramArrayOfByte[i1];
      if ((i3 & 0xF0) == 0) {
        localStringBuilder.append("0");
      }
      localStringBuilder.append(Integer.toHexString(i3 & 0xFF));
      i1 += 1;
    }
    return localStringBuilder.toString().toUpperCase();
  }
  
  public static String b(Object... paramVarArgs)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    b(true);
    String str = "";
    int i1 = 0;
    while (i1 < 4)
    {
      localStringBuilder.append(str);
      localStringBuilder.append(paramVarArgs[i1]);
      localStringBuilder.append("=");
      localStringBuilder.append(paramVarArgs[(i1 + 1)]);
      str = ", ";
      i1 += 2;
    }
    return localStringBuilder.toString();
  }
  
  public static void b()
  {
    if (Build.VERSION.SDK_INT >= 18) {
      bva.h();
    }
  }
  
  public static void b(Activity paramActivity)
  {
    paramActivity.invalidateOptionsMenu();
  }
  
  public static void b(Activity paramActivity, Intent paramIntent)
  {
    paramActivity.navigateUpTo(paramIntent);
  }
  
  public static void b(AsyncTask paramAsyncTask, Object... paramVarArgs)
  {
    paramAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, paramVarArgs);
  }
  
  public static void b(Parcel paramParcel, int paramInt)
  {
    paramParcel.setDataPosition(a(paramParcel, paramInt) + paramParcel.dataPosition());
  }
  
  public static void b(Parcel paramParcel, int paramInt1, int paramInt2)
  {
    if (paramInt1 != 8) {
      throw new q("Expected size " + 8 + " got " + paramInt1 + " (0x" + Integer.toHexString(paramInt1) + ")", paramParcel);
    }
  }
  
  public static void b(Parcel paramParcel, int paramInt, List paramList, boolean paramBoolean)
  {
    if (paramList == null) {
      return;
    }
    paramInt = r(paramParcel, paramInt);
    paramParcel.writeStringList(paramList);
    s(paramParcel, paramInt);
  }
  
  public static void b(View paramView, float paramFloat)
  {
    paramView.setTranslationY(paramFloat);
  }
  
  public static void b(View paramView, long paramLong)
  {
    paramView.animate().setStartDelay(paramLong);
  }
  
  public static void b(View paramView, CharSequence paramCharSequence)
  {
    if ((Build.VERSION.SDK_INT >= 16) && (paramView != null) && (!TextUtils.isEmpty(paramCharSequence))) {
      new Handler(Looper.getMainLooper()).post(new bpv(paramView, paramCharSequence));
    }
  }
  
  public static void b(View paramView, boolean paramBoolean)
  {
    paramView.setActivated(paramBoolean);
  }
  
  public static void b(ViewGroup paramViewGroup, boolean paramBoolean)
  {
    paramViewGroup.setMotionEventSplittingEnabled(paramBoolean);
  }
  
  public static void b(ViewParent paramViewParent, View paramView1, View paramView2, int paramInt)
  {
    try
    {
      paramViewParent.onNestedScrollAccepted(paramView1, paramView2, paramInt);
      return;
    }
    catch (AbstractMethodError paramView1)
    {
      Log.e("ViewParentCompat", "ViewParent " + paramViewParent + " does not implement interface " + "method onNestedScrollAccepted", paramView1);
    }
  }
  
  public static void b(dnb paramDnb, ctq paramCtq)
  {
    paramCtq = paramCtq.c;
    int i2 = paramCtq.length;
    int i1 = 0;
    while (i1 < i2)
    {
      Map localMap = c(paramCtq[i1]);
      if (localMap != null) {
        paramDnb.a(localMap);
      }
      i1 += 1;
    }
  }
  
  public static void b(Object paramObject, int paramInt)
  {
    ((AccessibilityRecord)paramObject).setMaxScrollX(paramInt);
  }
  
  public static void b(Object paramObject1, int paramInt, Object paramObject2)
  {
    ((MediaRouter)paramObject1).addCallback(paramInt, (MediaRouter.Callback)paramObject2);
  }
  
  public static void b(Object paramObject, Rect paramRect)
  {
    ((AccessibilityNodeInfo)paramObject).getBoundsInScreen(paramRect);
  }
  
  public static void b(Object paramObject, View paramView, AccessibilityEvent paramAccessibilityEvent)
  {
    ((View.AccessibilityDelegate)paramObject).onInitializeAccessibilityEvent(paramView, paramAccessibilityEvent);
  }
  
  public static void b(Object paramObject, CharSequence paramCharSequence)
  {
    ((MediaDescription.Builder)paramObject).setSubtitle(paramCharSequence);
  }
  
  public static void b(Object paramObject, ArrayList paramArrayList)
  {
    int i1 = 0;
    paramObject = (Transition)paramObject;
    int i2;
    if ((paramObject instanceof TransitionSet))
    {
      paramObject = (TransitionSet)paramObject;
      i2 = paramObject.getTransitionCount();
      while (i1 < i2)
      {
        b(paramObject.getTransitionAt(i1), paramArrayList);
        i1 += 1;
      }
    }
    if ((!a(paramObject)) && (a(paramObject.getTargets())))
    {
      i2 = paramArrayList.size();
      i1 = 0;
      while (i1 < i2)
      {
        paramObject.addTarget((View)paramArrayList.get(i1));
        i1 += 1;
      }
    }
  }
  
  public static void b(Object paramObject, boolean paramBoolean)
  {
    ((AccessibilityNodeInfo)paramObject).setEnabled(paramBoolean);
  }
  
  public static void b(String paramString, Object paramObject, StringBuffer paramStringBuffer1, StringBuffer paramStringBuffer2)
  {
    if (paramObject != null)
    {
      if (!(paramObject instanceof dyp)) {
        break label498;
      }
      int i4 = paramStringBuffer1.length();
      if (paramString != null)
      {
        paramStringBuffer2.append(paramStringBuffer1).append(z(paramString)).append(" <\n");
        paramStringBuffer1.append("  ");
      }
      Class localClass = paramObject.getClass();
      Object localObject1 = localClass.getFields();
      int i5 = localObject1.length;
      int i1 = 0;
      Object localObject3;
      if (i1 < i5)
      {
        Object localObject4 = localObject1[i1];
        i2 = localObject4.getModifiers();
        localObject2 = localObject4.getName();
        if ((!"cachedSize".equals(localObject2)) && ((i2 & 0x1) == 1) && ((i2 & 0x8) != 8) && (!((String)localObject2).startsWith("_")) && (!((String)localObject2).endsWith("_")))
        {
          localObject3 = localObject4.getType();
          localObject4 = localObject4.get(paramObject);
          if (!((Class)localObject3).isArray()) {
            break label251;
          }
          if (((Class)localObject3).getComponentType() != Byte.TYPE) {
            break label200;
          }
          b((String)localObject2, localObject4, paramStringBuffer1, paramStringBuffer2);
        }
        for (;;)
        {
          i1 += 1;
          break;
          label200:
          if (localObject4 == null) {}
          for (i2 = 0;; i2 = Array.getLength(localObject4))
          {
            int i3 = 0;
            while (i3 < i2)
            {
              b((String)localObject2, Array.get(localObject4, i3), paramStringBuffer1, paramStringBuffer2);
              i3 += 1;
            }
            break;
          }
          label251:
          b((String)localObject2, localObject4, paramStringBuffer1, paramStringBuffer2);
        }
      }
      Object localObject2 = localClass.getMethods();
      int i2 = localObject2.length;
      i1 = 0;
      if (i1 < i2)
      {
        localObject1 = localObject2[i1].getName();
        if (((String)localObject1).startsWith("set")) {
          localObject3 = ((String)localObject1).substring(3);
        }
        for (;;)
        {
          try
          {
            localObject1 = String.valueOf(localObject3);
            if (((String)localObject1).length() != 0)
            {
              localObject1 = "has".concat((String)localObject1);
              localObject1 = localClass.getMethod((String)localObject1, new Class[0]);
              if (!((Boolean)((Method)localObject1).invoke(paramObject, new Object[0])).booleanValue()) {}
            }
          }
          catch (NoSuchMethodException localNoSuchMethodException1)
          {
            continue;
            String str = new String("get");
            continue;
          }
          try
          {
            localObject1 = String.valueOf(localObject3);
            if (((String)localObject1).length() == 0) {
              continue;
            }
            localObject1 = "get".concat((String)localObject1);
            localObject1 = localClass.getMethod((String)localObject1, new Class[0]);
            b((String)localObject3, ((Method)localObject1).invoke(paramObject, new Object[0]), paramStringBuffer1, paramStringBuffer2);
          }
          catch (NoSuchMethodException localNoSuchMethodException2)
          {
            continue;
          }
          i1 += 1;
          break;
          localObject1 = new String("has");
        }
      }
      if (paramString != null)
      {
        paramStringBuffer1.setLength(i4);
        paramStringBuffer2.append(paramStringBuffer1).append(">\n");
      }
    }
    return;
    label498:
    paramString = z(paramString);
    paramStringBuffer2.append(paramStringBuffer1).append(paramString).append(": ");
    if ((paramObject instanceof String))
    {
      paramString = A((String)paramObject);
      paramStringBuffer2.append("\"").append(paramString).append("\"");
    }
    for (;;)
    {
      paramStringBuffer2.append("\n");
      return;
      if ((paramObject instanceof byte[])) {
        b((byte[])paramObject, paramStringBuffer2);
      } else {
        paramStringBuffer2.append(paramObject);
      }
    }
  }
  
  public static void b(String paramString1, String paramString2)
  {
    if (t(5)) {
      Log.w(paramString1, paramString2);
    }
  }
  
  public static void b(String paramString, Throwable paramThrowable)
  {
    if (e(6)) {
      Log.e("Ads", paramString, paramThrowable);
    }
  }
  
  public static void b(String paramString, JSONObject paramJSONObject, bfg paramBfg)
  {
    Object localObject2 = null;
    if (paramString == null)
    {
      paramBfg.N = null;
      return;
    }
    Object localObject1 = localObject2;
    if (paramJSONObject != null)
    {
      paramJSONObject = paramJSONObject.optJSONObject("locale");
      localObject1 = localObject2;
      if (paramJSONObject != null) {
        localObject1 = paramJSONObject.optString("display_string", null);
      }
    }
    paramBfg.N = new bfa(paramString, (String)localObject1);
  }
  
  public static void b(JSONObject paramJSONObject, bfg paramBfg)
  {
    paramBfg.q = paramJSONObject.optString("ssdp_udn", null);
    paramBfg.c = paramJSONObject.optString("build_version", null);
    paramBfg.d = paramJSONObject.optString("build_version", null);
    paramBfg.p = ((paramJSONObject.optDouble("uptime", 0.0D) * 1000.0D));
    paramBfg.v = paramJSONObject.optBoolean("has_update", false);
    paramBfg.x = paramJSONObject.optString("ip_address");
    paramBfg.s = paramJSONObject.getString("mac_address");
    paramBfg.y = paramJSONObject.optBoolean("connected", false);
    paramBfg.z = paramJSONObject.optBoolean("ethernet_connected", false);
    paramBfg.H = paramJSONObject.optBoolean("tos_accepted", false);
    paramBfg.A = paramJSONObject.optString("bssid");
    paramBfg.B = paramJSONObject.optString("ssid");
    paramBfg.a(paramJSONObject.optString("hotspot_bssid"));
    paramBfg.C = bfm.a(paramJSONObject.optInt("wpa_state", bfm.a.b));
    paramBfg.J = bfk.a(paramJSONObject.optInt("setup_state", bfk.a.r));
    paramBfg.D = paramJSONObject.optBoolean("wpa_configured", false);
    paramBfg.E = paramJSONObject.optInt("wpa_id", 0);
    paramBfg.F = paramJSONObject.optInt("signal_level", 0);
    paramBfg.G = paramJSONObject.optInt("noise_level", 0);
    paramBfg.u = paramJSONObject.optString("public_key", null);
    paramBfg.M = bfl.a(paramJSONObject.optInt("time_format", bfl.a.c));
    paramBfg.X = paramJSONObject.optString("opencast_pin_code", null);
    JSONObject localJSONObject = paramJSONObject.optJSONObject("detail");
    String str = paramJSONObject.optString("locale", null);
    a(paramJSONObject.optString("timezone", null), localJSONObject, paramBfg);
    b(str, localJSONObject, paramBfg);
    if ((localJSONObject != null) && (localJSONObject.has("model_name"))) {
      paramBfg.g = localJSONObject.getString("model_name");
    }
    if (paramJSONObject.has("location")) {
      paramBfg.O = paramJSONObject.getJSONObject("location").optString("country_code");
    }
    e(paramJSONObject, paramBfg);
    boolean bool;
    if (paramJSONObject.has("opt_in"))
    {
      localJSONObject = paramJSONObject.getJSONObject("opt_in");
      if ((!localJSONObject.optBoolean("stats", false)) && (!localJSONObject.optBoolean("crash", false))) {
        break label495;
      }
      bool = true;
      paramBfg.R = bool;
      paramBfg.U = localJSONObject.optBoolean("device_id", false);
      if (!localJSONObject.has("opencast")) {
        break label500;
      }
    }
    label495:
    label500:
    for (paramBfg.S = Boolean.valueOf(localJSONObject.optBoolean("opencast", false));; paramBfg.S = null)
    {
      if (paramJSONObject.has("setup_stats")) {
        paramBfg.K = paramJSONObject.getJSONObject("setup_stats").optInt("num_initial_eureka_info");
      }
      return;
      bool = false;
      break;
    }
  }
  
  public static void b(boolean paramBoolean)
  {
    if (!paramBoolean) {
      throw new IllegalArgumentException();
    }
  }
  
  public static void b(boolean paramBoolean, Object paramObject)
  {
    if (!paramBoolean) {
      throw new IllegalArgumentException(String.valueOf(paramObject));
    }
  }
  
  public static void b(byte[] paramArrayOfByte, StringBuffer paramStringBuffer)
  {
    if (paramArrayOfByte == null)
    {
      paramStringBuffer.append("\"\"");
      return;
    }
    paramStringBuffer.append('"');
    int i1 = 0;
    if (i1 < paramArrayOfByte.length)
    {
      int i2 = paramArrayOfByte[i1] & 0xFF;
      if ((i2 == 92) || (i2 == 34)) {
        paramStringBuffer.append('\\').append((char)i2);
      }
      for (;;)
      {
        i1 += 1;
        break;
        if ((i2 >= 32) && (i2 < 127)) {
          paramStringBuffer.append((char)i2);
        } else {
          paramStringBuffer.append(String.format("\\%03o", new Object[] { Integer.valueOf(i2) }));
        }
      }
    }
    paramStringBuffer.append('"');
  }
  
  public static boolean b(Context paramContext, String paramString)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    paramString = Uri.parse(paramString).getQueryParameter("id");
    Intent localIntent = new Intent("com.android.finsky.APP_DETAILS_DIALOG");
    localIntent.putExtra("docid", paramString);
    localIntent.putExtra("referrer", "utm_source=chromecast");
    localIntent.addCategory("android.intent.category.DEFAULT");
    localIntent.setPackage("com.android.vending");
    if (localPackageManager.resolveActivity(localIntent, 65536) != null)
    {
      ((Activity)paramContext).startActivityForResult(localIntent, 3);
      return true;
    }
    return false;
  }
  
  public static boolean b(Resources paramResources)
  {
    boolean bool2 = false;
    paramResources = paramResources.getConfiguration();
    boolean bool1 = bool2;
    if (bva.k())
    {
      bool1 = bool2;
      if ((paramResources.screenLayout & 0xF) <= 3)
      {
        bool1 = bool2;
        if (paramResources.smallestScreenWidthDp >= 600) {
          bool1 = true;
        }
      }
    }
    return bool1;
  }
  
  public static boolean b(MenuItem paramMenuItem)
  {
    return paramMenuItem.expandActionView();
  }
  
  public static boolean b(View paramView, int paramInt)
  {
    return paramView.canScrollVertically(paramInt);
  }
  
  public static boolean b(ViewConfiguration paramViewConfiguration)
  {
    return paramViewConfiguration.hasPermanentMenuKey();
  }
  
  public static boolean b(eaa paramEaa, Proxy.Type paramType)
  {
    return (!paramEaa.a.a.equals("https")) && (paramType == Proxy.Type.HTTP);
  }
  
  public static boolean b(Object paramObject1, Object paramObject2)
  {
    return ((AccessibilityNodeInfo)paramObject1).removeAction((AccessibilityNodeInfo.AccessibilityAction)paramObject2);
  }
  
  public static Object[] b(Parcel paramParcel, int paramInt, Parcelable.Creator paramCreator)
  {
    paramInt = a(paramParcel, paramInt);
    int i1 = paramParcel.dataPosition();
    if (paramInt == 0) {
      return null;
    }
    paramCreator = paramParcel.createTypedArray(paramCreator);
    paramParcel.setDataPosition(paramInt + i1);
    return paramCreator;
  }
  
  public static double c(int paramInt1, int paramInt2)
  {
    if (Color.alpha(paramInt2) != 255) {
      throw new IllegalArgumentException("background can not be translucent: #" + Integer.toHexString(paramInt2));
    }
    int i1 = paramInt1;
    if (Color.alpha(paramInt1) < 255) {
      i1 = a(paramInt1, paramInt2);
    }
    double d1 = a(i1) + 0.05D;
    double d2 = a(paramInt2) + 0.05D;
    return Math.max(d1, d2) / Math.min(d1, d2);
  }
  
  public static float c(MotionEvent paramMotionEvent, int paramInt)
  {
    return paramMotionEvent.getX(paramInt);
  }
  
  public static int c(Context paramContext)
  {
    DisplayMetrics localDisplayMetrics = paramContext.getResources().getDisplayMetrics();
    TypedValue localTypedValue;
    if (localDisplayMetrics.widthPixels < localDisplayMetrics.heightPixels)
    {
      i1 = 1;
      localTypedValue = new TypedValue();
      paramContext = paramContext.getResources();
      if (i1 == 0) {
        break label69;
      }
    }
    label69:
    for (int i1 = ct;; i1 = cs)
    {
      paramContext.getValue(i1, localTypedValue, true);
      if (localTypedValue.type != 5) {
        break label76;
      }
      return (int)localTypedValue.getDimension(localDisplayMetrics);
      i1 = 0;
      break;
    }
    label76:
    if (localTypedValue.type == 6) {
      return (int)localTypedValue.getFraction(localDisplayMetrics.widthPixels, localDisplayMetrics.widthPixels);
    }
    return -2;
  }
  
  public static int c(Context paramContext, int paramInt)
  {
    TypedValue localTypedValue = new TypedValue();
    if (paramContext.getTheme().resolveAttribute(paramInt, localTypedValue, true)) {
      return localTypedValue.resourceId;
    }
    return 0;
  }
  
  public static int c(Parcel paramParcel)
  {
    int i2 = b(paramParcel);
    int i3 = a(paramParcel, i2);
    int i1 = paramParcel.dataPosition();
    if (r(i2) != 20293) {
      throw new q("Expected object header. Got 0x" + Integer.toHexString(i2), paramParcel);
    }
    i2 = i1 + i3;
    if ((i2 < i1) || (i2 > paramParcel.dataSize())) {
      throw new q("Size read is invalid start=" + i1 + " end=" + i2, paramParcel);
    }
    return i2;
  }
  
  public static int c(String paramString, int paramInt)
  {
    try
    {
      long l1 = Long.parseLong(paramString);
      if (l1 > 2147483647L) {
        return Integer.MAX_VALUE;
      }
      if (l1 < 0L) {
        return 0;
      }
      return (int)l1;
    }
    catch (NumberFormatException paramString) {}
    return paramInt;
  }
  
  public static long c(Object paramObject, String paramString)
  {
    return ((MediaMetadata)paramObject).getLong(paramString);
  }
  
  public static Rect c(View paramView)
  {
    Rect localRect = new Rect();
    int[] arrayOfInt = new int[2];
    paramView.getLocationOnScreen(arrayOfInt);
    localRect.set(arrayOfInt[0], arrayOfInt[1], arrayOfInt[0] + paramView.getWidth(), arrayOfInt[1] + paramView.getHeight());
    return localRect;
  }
  
  public static Object c()
  {
    return new View.AccessibilityDelegate();
  }
  
  public static String c(Object paramObject)
  {
    return ((MediaDescription)paramObject).getMediaId();
  }
  
  public static String c(String paramString1, String paramString2)
  {
    try
    {
      paramString1 = edz.a(eef.a((paramString1 + ":" + paramString2).getBytes("ISO-8859-1")).b);
      paramString1 = "Basic " + paramString1;
      return paramString1;
    }
    catch (UnsupportedEncodingException paramString1)
    {
      throw new AssertionError();
    }
  }
  
  public static ArrayList c(Parcel paramParcel, int paramInt, Parcelable.Creator paramCreator)
  {
    paramInt = a(paramParcel, paramInt);
    int i1 = paramParcel.dataPosition();
    if (paramInt == 0) {
      return null;
    }
    paramCreator = paramParcel.createTypedArrayList(paramCreator);
    paramParcel.setDataPosition(paramInt + i1);
    return paramCreator;
  }
  
  public static Map c(ctx paramCtx)
  {
    paramCtx = dra.e(paramCtx);
    if (!(paramCtx instanceof Map))
    {
      dnm.b("value: " + paramCtx + " is not a map value, ignored.");
      return null;
    }
    return (Map)paramCtx;
  }
  
  public static Pattern c(String paramString)
  {
    int i1 = 0;
    while ((i1 < paramString.length()) && (Character.getType(paramString.charAt(0)) == 16)) {
      i1 += 1;
    }
    String str = paramString;
    if (i1 > 0) {
      str = paramString.substring(i1);
    }
    return Pattern.compile(str, 2);
  }
  
  public static void c(Activity paramActivity)
  {
    paramActivity.finishAffinity();
  }
  
  public static void c(Activity paramActivity, String paramString)
  {
    PackageManager localPackageManager = paramActivity.getApplication().getPackageManager();
    Intent localIntent2 = d(paramString);
    Intent localIntent1 = localIntent2;
    if (localIntent2.resolveActivity(localPackageManager) == null)
    {
      localIntent1 = new Intent("android.intent.action.VIEW");
      localIntent1.setData(Uri.parse(paramString));
    }
    paramActivity.startActivity(localIntent1);
  }
  
  public static void c(Parcel paramParcel, int paramInt1, int paramInt2)
  {
    if (paramInt2 >= 65535)
    {
      paramParcel.writeInt(0xFFFF0000 | paramInt1);
      paramParcel.writeInt(paramInt2);
      return;
    }
    paramParcel.writeInt(paramInt2 << 16 | paramInt1);
  }
  
  public static void c(Parcel paramParcel, int paramInt, List paramList, boolean paramBoolean)
  {
    if (paramList == null) {
      return;
    }
    int i1 = r(paramParcel, paramInt);
    int i2 = paramList.size();
    paramParcel.writeInt(i2);
    paramInt = 0;
    if (paramInt < i2)
    {
      Parcelable localParcelable = (Parcelable)paramList.get(paramInt);
      if (localParcelable == null) {
        paramParcel.writeInt(0);
      }
      for (;;)
      {
        paramInt += 1;
        break;
        a(paramParcel, localParcelable, 0);
      }
    }
    s(paramParcel, i1);
  }
  
  public static void c(View paramView, float paramFloat)
  {
    paramView.setAlpha(paramFloat);
  }
  
  public static void c(dnb paramDnb, ctq paramCtq)
  {
    ctp[] arrayOfCtp = paramCtq.e;
    int i2 = arrayOfCtp.length;
    int i1 = 0;
    while (i1 < i2)
    {
      ctp localCtp = arrayOfCtp[i1];
      if (localCtp.c == null)
      {
        dnm.b("GaExperimentRandom: No key");
        i1 += 1;
      }
      else
      {
        Object localObject = paramDnb.b(localCtp.c);
        if (!(localObject instanceof Number))
        {
          paramCtq = null;
          label65:
          long l1 = localCtp.d;
          long l2 = localCtp.e;
          if ((!localCtp.f) || (paramCtq == null) || (paramCtq.longValue() < l1) || (paramCtq.longValue() > l2))
          {
            if (l1 > l2) {
              break label240;
            }
            localObject = Long.valueOf(Math.round(Math.random() * (l2 - l1) + l1));
          }
          paramDnb.a(localCtp.c);
          paramCtq = dnb.b(localCtp.c, localObject);
          if (localCtp.g > 0L)
          {
            if (paramCtq.containsKey("gtm")) {
              break label249;
            }
            paramCtq.put("gtm", dnb.a(new Object[] { "lifetime", Long.valueOf(localCtp.g) }));
          }
        }
        for (;;)
        {
          paramDnb.a(paramCtq);
          break;
          paramCtq = Long.valueOf(((Number)localObject).longValue());
          break label65;
          label240:
          dnm.b("GaExperimentRandom: random range invalid");
          break;
          label249:
          localObject = paramCtq.get("gtm");
          if ((localObject instanceof Map)) {
            ((Map)localObject).put("lifetime", Long.valueOf(localCtp.g));
          } else {
            dnm.b("GaExperimentRandom: gtm not a map");
          }
        }
      }
    }
  }
  
  public static void c(Object paramObject, int paramInt)
  {
    ((AccessibilityRecord)paramObject).setMaxScrollY(paramInt);
  }
  
  public static void c(Object paramObject, Rect paramRect)
  {
    ((AccessibilityNodeInfo)paramObject).setBoundsInParent(paramRect);
  }
  
  public static void c(Object paramObject, View paramView)
  {
    ((AccessibilityNodeInfo)paramObject).addChild(paramView);
  }
  
  public static void c(Object paramObject, View paramView, AccessibilityEvent paramAccessibilityEvent)
  {
    ((View.AccessibilityDelegate)paramObject).onPopulateAccessibilityEvent(paramView, paramAccessibilityEvent);
  }
  
  public static void c(Object paramObject, CharSequence paramCharSequence)
  {
    ((MediaDescription.Builder)paramObject).setDescription(paramCharSequence);
  }
  
  public static void c(Object paramObject1, Object paramObject2)
  {
    ((AccessibilityNodeInfo)paramObject1).setCollectionInfo((AccessibilityNodeInfo.CollectionInfo)paramObject2);
  }
  
  public static void c(Object paramObject, boolean paramBoolean)
  {
    ((AccessibilityNodeInfo)paramObject).setFocusable(paramBoolean);
  }
  
  public static void c(String paramString, Throwable paramThrowable)
  {
    if (e(5)) {
      Log.w("Ads", paramString, paramThrowable);
    }
  }
  
  public static void c(JSONObject paramJSONObject, bfg paramBfg)
  {
    JSONObject localJSONObject1 = paramJSONObject.optJSONObject("build_info");
    if (localJSONObject1 != null)
    {
      paramBfg.c = localJSONObject1.optString("cast_build_revision", null);
      paramBfg.d = localJSONObject1.optString("system_build_number", null);
      paramBfg.e = localJSONObject1.optString("release_track", null);
      paramBfg.V = a(localJSONObject1, 0);
    }
    localJSONObject1 = paramJSONObject.optJSONObject("device_info");
    if (localJSONObject1 != null)
    {
      paramBfg.f = localJSONObject1.optString("manufacturer", null);
      paramBfg.g = localJSONObject1.optString("model_name", null);
      paramBfg.h = localJSONObject1.optString("factory_country_code", null);
      localJSONObject2 = localJSONObject1.optJSONObject("capabilities");
      if (localJSONObject2 != null)
      {
        paramBfg.ae = true;
        paramBfg.i = localJSONObject2.optBoolean("display_supported", true);
        paramBfg.j = localJSONObject2.optBoolean("wifi_supported", true);
        paramBfg.k = localJSONObject2.optBoolean("wifi_regulatory_domain_locked", false);
        paramBfg.l = localJSONObject2.optBoolean("hotspot_supported", true);
        paramBfg.m = localJSONObject2.optBoolean("ble_supported", false);
        paramBfg.n = localJSONObject2.optBoolean("multizone_supported", false);
        paramBfg.o = localJSONObject2.optBoolean("stats_supported", true);
      }
      paramBfg.p = ((localJSONObject1.optDouble("uptime", 0.0D) * 1000.0D));
      paramBfg.q = localJSONObject1.optString("ssdp_udn", null);
      paramBfg.r = localJSONObject1.optString("uma_client_id", null);
      paramBfg.s = localJSONObject1.getString("mac_address");
      paramBfg.a(localJSONObject1.optString("hotspot_bssid"));
      paramBfg.u = localJSONObject1.optString("public_key", null);
    }
    localJSONObject1 = paramJSONObject.optJSONObject("ota_status");
    if (localJSONObject1 != null)
    {
      paramBfg.v = localJSONObject1.optBoolean("ready_to_apply", false);
      paramBfg.w = localJSONObject1.optInt("download_progress", 0);
    }
    localJSONObject1 = paramJSONObject.optJSONObject("net");
    if (localJSONObject1 != null)
    {
      paramBfg.x = localJSONObject1.optString("ip_address");
      paramBfg.y = localJSONObject1.optBoolean("online", false);
      paramBfg.z = localJSONObject1.optBoolean("ethernet_connected", false);
    }
    localJSONObject1 = paramJSONObject.optJSONObject("wifi");
    if (localJSONObject1 != null)
    {
      paramBfg.A = localJSONObject1.optString("bssid");
      paramBfg.B = localJSONObject1.optString("ssid");
      paramBfg.C = bfm.a(localJSONObject1.optInt("wpa_state", bfm.a.b));
      paramBfg.D = localJSONObject1.optBoolean("wpa_configured", false);
      paramBfg.E = localJSONObject1.optInt("wpa_id", 0);
      paramBfg.F = localJSONObject1.optInt("signal_level", 0);
      paramBfg.G = localJSONObject1.optInt("noise_level", 0);
    }
    localJSONObject1 = paramJSONObject.optJSONObject("setup");
    if (localJSONObject1 != null)
    {
      paramBfg.H = localJSONObject1.optBoolean("tos_accepted", false);
      paramBfg.I = localJSONObject1.optString("ssid_suffix");
      paramBfg.J = bfk.a(localJSONObject1.optInt("setup_state", bfk.a.r));
      if (localJSONObject1.has("stats")) {
        paramBfg.K = localJSONObject1.getJSONObject("stats").optInt("num_initial_eureka_info");
      }
    }
    localJSONObject1 = paramJSONObject.optJSONObject("detail");
    JSONObject localJSONObject2 = paramJSONObject.optJSONObject("settings");
    if (localJSONObject2 != null)
    {
      a(localJSONObject2.optString("timezone", null), localJSONObject1, paramBfg);
      paramBfg.M = bfl.a(localJSONObject2.optInt("time_format", bfl.a.c));
      b(localJSONObject2.optString("locale", null), localJSONObject1, paramBfg);
      paramBfg.O = localJSONObject2.optString("country_code", null);
      paramBfg.P = localJSONObject2.optBoolean("system_sound_effects", false);
    }
    localJSONObject1 = paramJSONObject.optJSONObject("opt_in");
    if (localJSONObject1 != null)
    {
      if (localJSONObject1.has("hdmi_prefer_50hz")) {
        paramBfg.T = Boolean.valueOf(localJSONObject1.optBoolean("hdmi_prefer_50hz", false));
      }
      paramBfg.Q = localJSONObject1.optBoolean("audio_hdr", false);
      paramBfg.R = localJSONObject1.optBoolean("stats", false);
      if (!localJSONObject1.has("opencast")) {
        break label784;
      }
    }
    label784:
    for (paramBfg.S = Boolean.valueOf(localJSONObject1.optBoolean("opencast", false));; paramBfg.S = null)
    {
      if (localJSONObject1.has("preview_channel")) {
        paramBfg.W = Boolean.valueOf(localJSONObject1.optBoolean("preview_channel", false));
      }
      localJSONObject1 = paramJSONObject.optJSONObject("opencast");
      if (localJSONObject1 != null) {
        paramBfg.X = localJSONObject1.optString("pin_code", null);
      }
      d(paramJSONObject, paramBfg);
      e(paramJSONObject, paramBfg);
      return;
    }
  }
  
  public static void c(boolean paramBoolean, Object paramObject)
  {
    if (!paramBoolean) {
      throw new IllegalStateException(String.valueOf(paramObject));
    }
  }
  
  public static boolean c(int paramInt)
  {
    return KeyEvent.metaStateHasNoModifiers(paramInt);
  }
  
  public static boolean c(Context paramContext, String paramString)
  {
    boolean bool = false;
    paramContext = paramContext.getPackageManager();
    try
    {
      int i1 = paramContext.getApplicationInfo(paramString, 0).flags;
      if ((i1 & 0x200000) != 0) {
        bool = true;
      }
      return bool;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  public static boolean c(Parcel paramParcel, int paramInt)
  {
    a(paramParcel, paramInt, 4);
    return paramParcel.readInt() != 0;
  }
  
  public static boolean c(MenuItem paramMenuItem)
  {
    return paramMenuItem.isActionViewExpanded();
  }
  
  public static float d(MotionEvent paramMotionEvent, int paramInt)
  {
    return paramMotionEvent.getY(paramInt);
  }
  
  public static int d(int paramInt1, int paramInt2)
  {
    if ((paramInt2 < 0) || (paramInt2 > 255)) {
      throw new IllegalArgumentException("alpha must be between 0 and 255.");
    }
    return 0xFFFFFF & paramInt1 | paramInt2 << 24;
  }
  
  public static int d(Context paramContext, int paramInt)
  {
    TypedValue localTypedValue = new TypedValue();
    paramContext.getTheme().resolveAttribute(paramInt, localTypedValue, true);
    if (localTypedValue.resourceId != 0) {
      return paramContext.getResources().getColor(localTypedValue.resourceId);
    }
    return localTypedValue.data;
  }
  
  public static int d(Parcel paramParcel)
  {
    return r(paramParcel, 20293);
  }
  
  public static long d()
  {
    return ValueAnimator.getFrameDelay();
  }
  
  public static Context d(Context paramContext)
  {
    int i1;
    if (h(paramContext)) {
      if (f(paramContext) == -570425344) {
        i1 = dm;
      }
    }
    for (;;)
    {
      return new ContextThemeWrapper(paramContext, i1);
      i1 = do;
      continue;
      if (f(paramContext) == -570425344) {
        i1 = dn;
      } else {
        i1 = dl;
      }
    }
  }
  
  public static Intent d(Activity paramActivity)
  {
    return paramActivity.getParentActivityIntent();
  }
  
  public static Intent d(String paramString)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setData(Uri.parse(paramString));
    localIntent.setPackage("com.android.vending");
    return localIntent;
  }
  
  public static CharSequence d(Object paramObject)
  {
    return ((MediaDescription)paramObject).getTitle();
  }
  
  public static Object d(Object paramObject, String paramString)
  {
    return ((MediaMetadata)paramObject).getRating(paramString);
  }
  
  public static short d(Parcel paramParcel, int paramInt)
  {
    a(paramParcel, paramInt, 4);
    return (short)paramParcel.readInt();
  }
  
  public static void d(Parcel paramParcel, int paramInt1, int paramInt2)
  {
    c(paramParcel, paramInt1, 4);
    paramParcel.writeInt(paramInt2);
  }
  
  public static void d(View paramView, float paramFloat)
  {
    paramView.setScaleX(paramFloat);
  }
  
  public static void d(Object paramObject, Rect paramRect)
  {
    ((AccessibilityNodeInfo)paramObject).setBoundsInScreen(paramRect);
  }
  
  public static void d(Object paramObject, View paramView)
  {
    ((AccessibilityNodeInfo)paramObject).setParent(paramView);
  }
  
  public static void d(Object paramObject, View paramView, AccessibilityEvent paramAccessibilityEvent)
  {
    ((View.AccessibilityDelegate)paramObject).sendAccessibilityEventUnchecked(paramView, paramAccessibilityEvent);
  }
  
  public static void d(Object paramObject, CharSequence paramCharSequence)
  {
    ((AccessibilityNodeInfo)paramObject).setClassName(paramCharSequence);
  }
  
  public static void d(Object paramObject1, Object paramObject2)
  {
    ((AccessibilityNodeInfo)paramObject1).setCollectionItemInfo((AccessibilityNodeInfo.CollectionItemInfo)paramObject2);
  }
  
  public static void d(Object paramObject, boolean paramBoolean)
  {
    ((AccessibilityNodeInfo)paramObject).setFocused(paramBoolean);
  }
  
  public static void d(JSONObject paramJSONObject, bfg paramBfg)
  {
    paramJSONObject = paramJSONObject.optJSONObject("multizone");
    if (paramJSONObject == null) {}
    do
    {
      return;
      paramBfg.Y = ((int)TimeUnit.MICROSECONDS.toMillis(paramJSONObject.optInt("audio_output_delay")));
      paramJSONObject = paramJSONObject.optJSONArray("groups");
    } while ((paramJSONObject == null) || (paramJSONObject.length() <= 0));
    ArrayList localArrayList = new ArrayList();
    int i1 = 0;
    while (i1 < paramJSONObject.length())
    {
      JSONObject localJSONObject = paramJSONObject.optJSONObject(i1);
      localArrayList.add(new bey(localJSONObject.optString("uuid", null), localJSONObject.optString("name", null), localJSONObject.optString("leader", null)));
      i1 += 1;
    }
    paramBfg.Z = localArrayList;
  }
  
  public static boolean d(int paramInt)
  {
    return (paramInt == 82) && ("LGE".equalsIgnoreCase(Build.MANUFACTURER)) && (Build.VERSION.SDK_INT == 16);
  }
  
  public static boolean d(ctx paramCtx)
  {
    return dra.e(paramCtx) instanceof String;
  }
  
  public static boolean d(Object paramObject, int paramInt)
  {
    ((EdgeEffect)paramObject).onAbsorb(paramInt);
    return true;
  }
  
  public static float e(Context paramContext)
  {
    TypedValue localTypedValue = new TypedValue();
    if (paramContext.getTheme().resolveAttribute(16842803, localTypedValue, true)) {
      return localTypedValue.getFloat();
    }
    return 0.5F;
  }
  
  public static float e(MotionEvent paramMotionEvent, int paramInt)
  {
    return paramMotionEvent.getAxisValue(paramInt);
  }
  
  public static int e(int paramInt1, int paramInt2)
  {
    return Gravity.getAbsoluteGravity(paramInt1, paramInt2);
  }
  
  public static int e(Parcel paramParcel, int paramInt)
  {
    a(paramParcel, paramInt, 4);
    return paramParcel.readInt();
  }
  
  public static CharSequence e(Object paramObject)
  {
    return ((MediaDescription)paramObject).getSubtitle();
  }
  
  public static CharSequence e(Object paramObject, String paramString)
  {
    return ((MediaMetadata)paramObject).getText(paramString);
  }
  
  public static Object e(Object paramObject, int paramInt)
  {
    return ((MediaRouter)paramObject).getSelectedRoute(8388611);
  }
  
  public static String e(Context paramContext, int paramInt)
  {
    paramContext = paramContext.getResources();
    switch (paramInt)
    {
    default: 
      Log.e("GoogleApiAvailability", "Unexpected error code " + paramInt);
    case 4: 
    case 6: 
      return null;
    case 1: 
      return paramContext.getString(jl);
    case 3: 
      return paramContext.getString(jh);
    case 18: 
      return paramContext.getString(jz);
    case 2: 
      return paramContext.getString(jx);
    case 42: 
      return paramContext.getString(jd);
    case 9: 
      Log.e("GoogleApiAvailability", "Google Play services is invalid. Cannot recover.");
      return paramContext.getString(ju);
    case 7: 
      Log.e("GoogleApiAvailability", "Network error occurred. Please retry request later.");
      return paramContext.getString(jp);
    case 8: 
      Log.e("GoogleApiAvailability", "Internal error occurred. Please see logs for detailed information");
      return null;
    case 10: 
      Log.e("GoogleApiAvailability", "Developer error occurred. Please see logs for detailed information");
      return null;
    case 5: 
      Log.e("GoogleApiAvailability", "An invalid account was specified when connecting. Please provide a valid account.");
      return paramContext.getString(jn);
    case 11: 
      Log.e("GoogleApiAvailability", "The application is not licensed to the user.");
      return null;
    case 16: 
      Log.e("GoogleApiAvailability", "One of the API components you attempted to connect to is not available.");
      return null;
    }
    Log.e("GoogleApiAvailability", "The specified account could not be signed in.");
    return paramContext.getString(jr);
  }
  
  public static String e(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return paramString;
    }
    if (paramString.length() == 1) {
      return Character.toString(Character.toTitleCase(paramString.charAt(0)));
    }
    char c1 = Character.toTitleCase(paramString.charAt(0));
    paramString = String.valueOf(paramString.substring(1));
    return String.valueOf(paramString).length() + 1 + c1 + paramString;
  }
  
  public static void e(View paramView, float paramFloat)
  {
    paramView.setScaleY(paramFloat);
  }
  
  public static void e(Object paramObject, View paramView)
  {
    ((AccessibilityNodeInfo)paramObject).setSource(paramView);
  }
  
  public static void e(Object paramObject, CharSequence paramCharSequence)
  {
    ((AccessibilityNodeInfo)paramObject).setContentDescription(paramCharSequence);
  }
  
  public static void e(Object paramObject1, Object paramObject2)
  {
    ((MediaRouter)paramObject1).removeCallback((MediaRouter.Callback)paramObject2);
  }
  
  public static void e(Object paramObject, boolean paramBoolean)
  {
    ((AccessibilityNodeInfo)paramObject).setLongClickable(paramBoolean);
  }
  
  public static void e(JSONObject paramJSONObject, bfg paramBfg)
  {
    int i1 = 0;
    Object localObject = paramJSONObject.optJSONObject("sign");
    if (localObject == null) {}
    do
    {
      String str1;
      String str2;
      do
      {
        return;
        paramJSONObject = ((JSONObject)localObject).optString("certificate", null);
        str1 = ((JSONObject)localObject).optString("nonce", null);
        str2 = ((JSONObject)localObject).optString("signed_data", null);
      } while ((paramJSONObject == null) || (str1 == null) || (str2 == null));
      localObject = ((JSONObject)localObject).optJSONArray("intermediate_certs");
      paramBfg.aa = new bew(paramJSONObject, str1, Base64.decode(str2.getBytes(), 0));
    } while ((localObject == null) || (((JSONArray)localObject).length() <= 0));
    paramJSONObject = new String[((JSONArray)localObject).length()];
    while (i1 < ((JSONArray)localObject).length())
    {
      paramJSONObject[i1] = ((JSONArray)localObject).optString(i1, null);
      i1 += 1;
    }
    paramBfg.ab = paramJSONObject;
  }
  
  public static boolean e()
  {
    return Build.VERSION.SDK_INT >= 21;
  }
  
  public static boolean e(int paramInt)
  {
    return ((paramInt >= 5) || (Log.isLoggable("Ads", paramInt))) && ((paramInt != 2) || (m()));
  }
  
  public static boolean e(Activity paramActivity)
  {
    if ((paramActivity instanceof DeviceSetupActivity))
    {
      paramActivity = ((DeviceSetupActivity)paramActivity).b.a().a(bva.aP);
      if ((paramActivity instanceof bkf)) {
        if (((bkf)paramActivity).a.f.isChecked()) {
          break label55;
        }
      }
      label55:
      for (int i1 = 1; i1 != 0; i1 = 0) {
        return true;
      }
      return false;
    }
    return true;
  }
  
  public static boolean e(View paramView)
  {
    return (paramView.getWidth() > 0) && (paramView.getHeight() > 0);
  }
  
  public static int f(Context paramContext)
  {
    if (c(-1, d(paramContext, bva.r)) >= 3.0D) {
      return -1;
    }
    return -570425344;
  }
  
  public static int f(View paramView)
  {
    if (!b) {}
    try
    {
      a = View.class.getDeclaredField("mMinWidth");
      a.setAccessible(true);
      b = true;
      if (a != null) {
        try
        {
          int i1 = ((Integer)a.get(paramView)).intValue();
          return i1;
        }
        catch (Exception paramView) {}
      }
      return 0;
    }
    catch (NoSuchFieldException localNoSuchFieldException)
    {
      for (;;) {}
    }
  }
  
  public static long f(Parcel paramParcel, int paramInt)
  {
    a(paramParcel, paramInt, 8);
    return paramParcel.readLong();
  }
  
  public static age f()
  {
    return new ajc();
  }
  
  public static Bitmap f(Activity paramActivity)
  {
    try
    {
      paramActivity = paramActivity.getWindow().getDecorView().getRootView();
      boolean bool = paramActivity.isDrawingCacheEnabled();
      paramActivity.setDrawingCacheEnabled(true);
      Bitmap localBitmap = paramActivity.getDrawingCache();
      if (localBitmap != null)
      {
        localBitmap = a(localBitmap).copy(Bitmap.Config.RGB_565, false);
        if (!bool)
        {
          paramActivity.setDrawingCacheEnabled(false);
          paramActivity.destroyDrawingCache();
        }
        return localBitmap;
      }
      return null;
    }
    catch (OutOfMemoryError paramActivity)
    {
      paramActivity.toString();
    }
    return null;
  }
  
  public static CharSequence f(Object paramObject)
  {
    return ((MediaDescription)paramObject).getDescription();
  }
  
  public static Object f(Object paramObject1, Object paramObject2)
  {
    return ((MediaRouter)paramObject1).createUserRoute((MediaRouter.RouteCategory)paramObject2);
  }
  
  public static String f(int paramInt)
  {
    return a("cd", paramInt);
  }
  
  public static String f(Context paramContext, int paramInt)
  {
    paramContext = paramContext.getResources();
    switch (paramInt)
    {
    default: 
      return paramContext.getString(17039370);
    case 1: 
      return paramContext.getString(ji);
    case 3: 
      return paramContext.getString(jf);
    }
    return paramContext.getString(jv);
  }
  
  public static void f(View paramView, float paramFloat)
  {
    paramView.animate().alpha(paramFloat);
  }
  
  public static void f(Object paramObject, int paramInt)
  {
    ((MediaRouter.RouteInfo)paramObject).requestSetVolume(paramInt);
  }
  
  public static void f(Object paramObject, CharSequence paramCharSequence)
  {
    ((AccessibilityNodeInfo)paramObject).setPackageName(paramCharSequence);
  }
  
  public static void f(Object paramObject, boolean paramBoolean)
  {
    ((AccessibilityNodeInfo)paramObject).setScrollable(paramBoolean);
  }
  
  public static void f(String paramString)
  {
    e(3);
  }
  
  public static boolean f(int paramInt1, int paramInt2)
  {
    return KeyEvent.metaStateHasModifiers(paramInt1, paramInt2);
  }
  
  public static int g(Activity paramActivity)
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    paramActivity.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics.widthPixels;
  }
  
  public static int g(Context paramContext)
  {
    int i2 = d(paramContext, bva.r);
    int i1 = i2;
    if (c(i2, d(paramContext, 16842801)) < 3.0D) {
      i1 = d(paramContext, bva.q);
    }
    return i1;
  }
  
  public static int g(View paramView)
  {
    if (!d) {}
    try
    {
      c = View.class.getDeclaredField("mMinHeight");
      c.setAccessible(true);
      d = true;
      if (c != null) {
        try
        {
          int i1 = ((Integer)c.get(paramView)).intValue();
          return i1;
        }
        catch (Exception paramView) {}
      }
      return 0;
    }
    catch (NoSuchFieldException localNoSuchFieldException)
    {
      for (;;) {}
    }
  }
  
  public static Bitmap g(Object paramObject)
  {
    return ((MediaDescription)paramObject).getIconBitmap();
  }
  
  public static Long g(Parcel paramParcel, int paramInt)
  {
    paramInt = a(paramParcel, paramInt);
    if (paramInt == 0) {
      return null;
    }
    b(paramParcel, paramInt, 8);
    return Long.valueOf(paramParcel.readLong());
  }
  
  public static String g()
  {
    return dZ;
  }
  
  public static String g(int paramInt)
  {
    return a("cm", paramInt);
  }
  
  public static String g(int paramInt1, int paramInt2)
  {
    StackTraceElement[] arrayOfStackTraceElement = Thread.currentThread().getStackTrace();
    StringBuffer localStringBuffer = new StringBuffer();
    paramInt1 = 3;
    while (paramInt1 < paramInt2 + 3)
    {
      localStringBuffer.append(a(arrayOfStackTraceElement, paramInt1)).append(" ");
      paramInt1 += 1;
    }
    return localStringBuffer.toString();
  }
  
  public static String g(Context paramContext, int paramInt)
  {
    paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses();
    if (paramContext != null)
    {
      paramContext = paramContext.iterator();
      while (paramContext.hasNext())
      {
        ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)paramContext.next();
        if (localRunningAppProcessInfo.pid == paramInt) {
          return localRunningAppProcessInfo.processName;
        }
      }
    }
    return null;
  }
  
  public static void g(View paramView, float paramFloat)
  {
    paramView.animate().translationX(paramFloat);
  }
  
  public static void g(Object paramObject, int paramInt)
  {
    ((MediaRouter.RouteInfo)paramObject).requestUpdateVolume(paramInt);
  }
  
  public static void g(Object paramObject, CharSequence paramCharSequence)
  {
    ((MediaRouter.UserRouteInfo)paramObject).setName(paramCharSequence);
  }
  
  public static void g(Object paramObject1, Object paramObject2)
  {
    ((MediaRouter)paramObject1).addUserRoute((MediaRouter.UserRouteInfo)paramObject2);
  }
  
  public static void g(Object paramObject, boolean paramBoolean)
  {
    ((AccessibilityNodeInfo)paramObject).setSelected(paramBoolean);
  }
  
  public static void g(String paramString)
  {
    if (e(6)) {
      Log.e("Ads", paramString);
    }
  }
  
  public static float h(Parcel paramParcel, int paramInt)
  {
    a(paramParcel, paramInt, 4);
    return paramParcel.readFloat();
  }
  
  public static int h(Activity paramActivity)
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    paramActivity.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    return Math.min(localDisplayMetrics.widthPixels, localDisplayMetrics.heightPixels);
  }
  
  public static Uri h(Object paramObject)
  {
    return ((MediaDescription)paramObject).getIconUri();
  }
  
  public static String h(int paramInt)
  {
    return a("&pr", paramInt);
  }
  
  public static void h()
  {
    if (Looper.myLooper() != Looper.getMainLooper()) {
      throw new IllegalStateException("This method may only be called on the application's main thread.");
    }
  }
  
  public static void h(View paramView, float paramFloat)
  {
    paramView.animate().translationY(paramFloat);
  }
  
  public static void h(Object paramObject, int paramInt)
  {
    ((MediaRouter.UserRouteInfo)paramObject).setPlaybackType(paramInt);
  }
  
  public static void h(Object paramObject1, Object paramObject2)
  {
    ((MediaRouter)paramObject1).removeUserRoute((MediaRouter.UserRouteInfo)paramObject2);
  }
  
  public static void h(Object paramObject, boolean paramBoolean)
  {
    ((AccessibilityNodeInfo)paramObject).setVisibleToUser(paramBoolean);
  }
  
  public static void h(String paramString)
  {
    e(4);
  }
  
  public static boolean h(Context paramContext)
  {
    TypedValue localTypedValue = new TypedValue();
    return (paramContext.getTheme().resolveAttribute(bva.t, localTypedValue, true)) && (localTypedValue.data != 0);
  }
  
  public static boolean h(View paramView)
  {
    return paramView.getWindowToken() != null;
  }
  
  public static double i(Parcel paramParcel, int paramInt)
  {
    a(paramParcel, paramInt, 8);
    return paramParcel.readDouble();
  }
  
  public static int i(Activity paramActivity)
  {
    int i1 = paramActivity.getWindowManager().getDefaultDisplay().getRotation();
    int i2 = paramActivity.getResources().getConfiguration().orientation;
    if (i2 == 1)
    {
      if ((i1 == 0) || (i1 == 3)) {
        return 1;
      }
      return 9;
    }
    if (i2 == 2)
    {
      if ((i1 == 0) || (i1 == 1)) {
        return 0;
      }
      return 8;
    }
    return -1;
  }
  
  public static int i(View paramView)
  {
    return paramView.getOverScrollMode();
  }
  
  public static Bundle i()
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("source", "googlecast_search");
    return localBundle;
  }
  
  public static Bundle i(Object paramObject)
  {
    return ((MediaDescription)paramObject).getExtras();
  }
  
  public static Object i(Context paramContext)
  {
    return paramContext.getSystemService("media_router");
  }
  
  public static String i(int paramInt)
  {
    return a("pr", paramInt);
  }
  
  public static void i(Object paramObject, int paramInt)
  {
    ((MediaRouter.UserRouteInfo)paramObject).setPlaybackStream(paramInt);
  }
  
  public static void i(Object paramObject1, Object paramObject2)
  {
    ((MediaRouter.RouteInfo)paramObject1).setTag(paramObject2);
  }
  
  public static void i(Object paramObject, boolean paramBoolean)
  {
    ((AccessibilityNodeInfo)paramObject).setAccessibilityFocused(paramBoolean);
  }
  
  public static void i(String paramString)
  {
    e(2);
  }
  
  public static float j(View paramView)
  {
    return paramView.getAlpha();
  }
  
  public static Bundle j()
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("source", "googlecast_recommend");
    return localBundle;
  }
  
  public static Object j(Object paramObject)
  {
    return ((MediaDescription.Builder)paramObject).build();
  }
  
  public static String j(int paramInt)
  {
    return a("&promo", paramInt);
  }
  
  public static String j(Parcel paramParcel, int paramInt)
  {
    paramInt = a(paramParcel, paramInt);
    int i1 = paramParcel.dataPosition();
    if (paramInt == 0) {
      return null;
    }
    String str = paramParcel.readString();
    paramParcel.setDataPosition(paramInt + i1);
    return str;
  }
  
  public static void j(Activity paramActivity)
  {
    aps.a().a(15, Integer.valueOf(2));
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.addFlags(268435456);
    localIntent.setData(Uri.parse(boy.c()));
    paramActivity.startActivity(localIntent);
  }
  
  public static void j(Object paramObject, int paramInt)
  {
    ((MediaRouter.UserRouteInfo)paramObject).setVolume(paramInt);
  }
  
  public static void j(Object paramObject1, Object paramObject2)
  {
    ((MediaRouter.UserRouteInfo)paramObject1).setVolumeCallback((MediaRouter.VolumeCallback)paramObject2);
  }
  
  public static void j(String paramString)
  {
    if (e(5)) {
      Log.w("Ads", paramString);
    }
  }
  
  public static boolean j(Context paramContext)
  {
    return cbu.a(paramContext.getPackageManager(), paramContext.getPackageName());
  }
  
  public static int k(View paramView)
  {
    return paramView.getLayerType();
  }
  
  public static Bundle k()
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("source", "googlecast_recommendlogo");
    return localBundle;
  }
  
  public static IBinder k(Parcel paramParcel, int paramInt)
  {
    paramInt = a(paramParcel, paramInt);
    int i1 = paramParcel.dataPosition();
    if (paramInt == 0) {
      return null;
    }
    IBinder localIBinder = paramParcel.readStrongBinder();
    paramParcel.setDataPosition(paramInt + i1);
    return localIBinder;
  }
  
  public static String k(int paramInt)
  {
    return a("promo", paramInt);
  }
  
  public static String k(Context paramContext)
  {
    return paramContext.getString(cci.dw, new Object[] { bpc.b() });
  }
  
  public static Set k(Object paramObject)
  {
    return ((MediaMetadata)paramObject).keySet();
  }
  
  public static void k(Activity paramActivity)
  {
    c(paramActivity, paramActivity.getString(cci.aw));
  }
  
  public static void k(Object paramObject, int paramInt)
  {
    ((MediaRouter.UserRouteInfo)paramObject).setVolumeMax(paramInt);
  }
  
  public static boolean k(Object paramObject1, Object paramObject2)
  {
    return (paramObject1 == paramObject2) || ((paramObject1 != null) && (paramObject1.equals(paramObject2)));
  }
  
  public static boolean k(String paramString)
  {
    if (n() < 9) {
      return false;
    }
    paramString = new File(paramString);
    paramString.setReadable(false, false);
    paramString.setWritable(false, false);
    paramString.setReadable(true, true);
    paramString.setWritable(true, true);
    return true;
  }
  
  public static int l(View paramView)
  {
    return paramView.getMeasuredWidthAndState();
  }
  
  public static Bundle l(Parcel paramParcel, int paramInt)
  {
    paramInt = a(paramParcel, paramInt);
    int i1 = paramParcel.dataPosition();
    if (paramInt == 0) {
      return null;
    }
    Bundle localBundle = paramParcel.readBundle();
    paramParcel.setDataPosition(paramInt + i1);
    return localBundle;
  }
  
  public static Object l(Object paramObject1, Object paramObject2)
  {
    if (paramObject1 == null) {
      throw new NullPointerException(String.valueOf(paramObject2));
    }
    return paramObject1;
  }
  
  public static String l(int paramInt)
  {
    return a("pi", paramInt);
  }
  
  public static String l(Context paramContext)
  {
    return paramContext.getString(cci.ey, new Object[] { bpc.b() });
  }
  
  public static String l(String paramString)
  {
    if (paramString == null) {
      throw new IllegalArgumentException("applicationId cannot be null");
    }
    return a("com.google.android.gms.cast.CATEGORY_CAST", paramString, null);
  }
  
  public static void l(Activity paramActivity)
  {
    aps.a().a(15, Integer.valueOf(0));
    c(paramActivity, boy.U());
  }
  
  public static void l(Object paramObject, int paramInt)
  {
    ((MediaRouter.UserRouteInfo)paramObject).setVolumeHandling(paramInt);
  }
  
  public static boolean l()
  {
    return Build.VERSION.SDK_INT >= 23;
  }
  
  public static boolean l(Object paramObject)
  {
    return ((Rating)paramObject).isRated();
  }
  
  public static int m(View paramView)
  {
    return paramView.getMeasuredState();
  }
  
  public static int m(Object paramObject)
  {
    return ((Rating)paramObject).getRatingStyle();
  }
  
  public static String m(int paramInt)
  {
    return a("&il", paramInt);
  }
  
  public static String m(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      throw new IllegalArgumentException("Given String is empty or null");
    }
    return paramString;
  }
  
  public static boolean m()
  {
    bth localBth = btm.h;
    return ((Boolean)bvd.a().i.a(localBth)).booleanValue();
  }
  
  public static boolean m(Context paramContext)
  {
    return (bov.e()) && (paramContext.getPackageManager().hasSystemFeature("android.hardware.bluetooth_le"));
  }
  
  public static byte[] m(Parcel paramParcel, int paramInt)
  {
    paramInt = a(paramParcel, paramInt);
    int i1 = paramParcel.dataPosition();
    if (paramInt == 0) {
      return null;
    }
    byte[] arrayOfByte = paramParcel.createByteArray();
    paramParcel.setDataPosition(paramInt + i1);
    return arrayOfByte;
  }
  
  public static float n(View paramView)
  {
    return paramView.getTranslationX();
  }
  
  public static int n()
  {
    try
    {
      int i1 = Integer.parseInt(Build.VERSION.SDK);
      return i1;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      byj.a("Invalid version number", Build.VERSION.SDK);
    }
    return 0;
  }
  
  public static String n(int paramInt)
  {
    return a("il", paramInt);
  }
  
  public static void n(String paramString)
  {
    if (Looper.myLooper() != Looper.getMainLooper()) {
      throw new IllegalStateException(paramString);
    }
  }
  
  public static boolean n(Context paramContext)
  {
    return (l()) && (p(paramContext));
  }
  
  public static boolean n(Object paramObject)
  {
    return ((Rating)paramObject).hasHeart();
  }
  
  public static int[] n(Parcel paramParcel, int paramInt)
  {
    paramInt = a(paramParcel, paramInt);
    int i1 = paramParcel.dataPosition();
    if (paramInt == 0) {
      return null;
    }
    int[] arrayOfInt = paramParcel.createIntArray();
    paramParcel.setDataPosition(paramInt + i1);
    return arrayOfInt;
  }
  
  public static float o(View paramView)
  {
    return paramView.getTranslationY();
  }
  
  public static String o(int paramInt)
  {
    return a("cd", paramInt);
  }
  
  public static void o(String paramString)
  {
    if (Looper.myLooper() == Looper.getMainLooper()) {
      throw new IllegalStateException(paramString);
    }
  }
  
  public static boolean o()
  {
    return (cdl.a) && (cct.b()) && (cct.a() == Process.myUid());
  }
  
  public static boolean o(Context paramContext)
  {
    return bva.a(paramContext.getApplicationContext(), "android.permission.ACCESS_COARSE_LOCATION") == -1;
  }
  
  public static boolean o(Object paramObject)
  {
    return ((Rating)paramObject).isThumbUp();
  }
  
  public static String[] o(Parcel paramParcel, int paramInt)
  {
    paramInt = a(paramParcel, paramInt);
    int i1 = paramParcel.dataPosition();
    if (paramInt == 0) {
      return null;
    }
    String[] arrayOfString = paramParcel.createStringArray();
    paramParcel.setDataPosition(paramInt + i1);
    return arrayOfString;
  }
  
  public static float p(Object paramObject)
  {
    return ((Rating)paramObject).getStarRating();
  }
  
  public static String p(int paramInt)
  {
    return a("cm", paramInt);
  }
  
  public static String p(String paramString)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    int i1 = 0;
    if (i1 < paramString.length())
    {
      char c1 = paramString.charAt(i1);
      if (i1 == 0) {
        localStringBuffer.append(Character.toLowerCase(c1));
      }
      for (;;)
      {
        i1 += 1;
        break;
        if (Character.isUpperCase(c1)) {
          localStringBuffer.append('_').append(Character.toLowerCase(c1));
        } else {
          localStringBuffer.append(c1);
        }
      }
    }
    return localStringBuffer.toString();
  }
  
  public static ArrayList p(Parcel paramParcel, int paramInt)
  {
    int i1 = a(paramParcel, paramInt);
    int i2 = paramParcel.dataPosition();
    if (i1 == 0) {
      return null;
    }
    ArrayList localArrayList = new ArrayList();
    int i3 = paramParcel.readInt();
    paramInt = 0;
    while (paramInt < i3)
    {
      localArrayList.add(Integer.valueOf(paramParcel.readInt()));
      paramInt += 1;
    }
    paramParcel.setDataPosition(i2 + i1);
    return localArrayList;
  }
  
  public static void p(View paramView)
  {
    paramView.jumpDrawablesToCurrentState();
  }
  
  public static boolean p()
  {
    return Log.isLoggable("PeopleClientCall", 3);
  }
  
  public static boolean p(Context paramContext)
  {
    boolean bool = false;
    if (Settings.Secure.getInt(paramContext.getContentResolver(), "location_mode", 0) == 0) {
      bool = true;
    }
    return bool;
  }
  
  public static float q(Object paramObject)
  {
    return ((Rating)paramObject).getPercentRating();
  }
  
  public static String q(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return "unknown status code: " + paramInt;
    case -1: 
      return "SUCCESS_CACHE";
    case 0: 
      return "SUCCESS";
    case 1: 
      return "SERVICE_MISSING";
    case 2: 
      return "SERVICE_VERSION_UPDATE_REQUIRED";
    case 3: 
      return "SERVICE_DISABLED";
    case 4: 
      return "SIGN_IN_REQUIRED";
    case 5: 
      return "INVALID_ACCOUNT";
    case 6: 
      return "RESOLUTION_REQUIRED";
    case 7: 
      return "NETWORK_ERROR";
    case 8: 
      return "INTERNAL_ERROR";
    case 9: 
      return "SERVICE_INVALID";
    case 10: 
      return "DEVELOPER_ERROR";
    case 11: 
      return "LICENSE_CHECK_FAILED";
    case 13: 
      return "ERROR";
    case 14: 
      return "INTERRUPTED";
    case 15: 
      return "TIMEOUT";
    case 16: 
      return "CANCELED";
    case 17: 
      return "API_NOT_CONNECTED";
    case 3000: 
      return "AUTH_API_INVALID_CREDENTIALS";
    case 3001: 
      return "AUTH_API_ACCESS_FORBIDDEN";
    case 3002: 
      return "AUTH_API_CLIENT_ERROR";
    case 3003: 
      return "AUTH_API_SERVER_ERROR";
    case 3004: 
      return "AUTH_TOKEN_ERROR";
    }
    return "AUTH_URL_RESOLUTION";
  }
  
  public static String q(Context paramContext)
  {
    return g(paramContext, Binder.getCallingPid());
  }
  
  public static String q(String paramString)
  {
    String str = paramString;
    if (!paramString.startsWith("http"))
    {
      str = paramString;
      if (paramString.length() > 200) {
        str = paramString.substring(0, 200) + "[...]";
      }
    }
    return r(str);
  }
  
  public static ArrayList q(Parcel paramParcel, int paramInt)
  {
    paramInt = a(paramParcel, paramInt);
    int i1 = paramParcel.dataPosition();
    if (paramInt == 0) {
      return null;
    }
    ArrayList localArrayList = paramParcel.createStringArrayList();
    paramParcel.setDataPosition(paramInt + i1);
    return localArrayList;
  }
  
  public static boolean q()
  {
    return t(3);
  }
  
  public static boolean q(View paramView)
  {
    return paramView.hasOnClickListeners();
  }
  
  public static int r(int paramInt)
  {
    return 0xFFFF & paramInt;
  }
  
  public static int r(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeInt(0xFFFF0000 | paramInt);
    paramParcel.writeInt(0);
    return paramParcel.dataPosition();
  }
  
  public static int r(View paramView)
  {
    return paramView.getLayoutDirection();
  }
  
  public static dal r(Context paramContext)
  {
    return a(paramContext, null);
  }
  
  public static Object r(Object paramObject)
  {
    return ((MediaController)paramObject).getTransportControls();
  }
  
  public static String r()
  {
    return "okhttp/2.7.2";
  }
  
  public static String r(String paramString)
  {
    int i2 = paramString.length();
    StringBuilder localStringBuilder = new StringBuilder(i2);
    int i1 = 0;
    if (i1 < i2)
    {
      char c1 = paramString.charAt(i1);
      if ((c1 >= ' ') && (c1 <= '~') && (c1 != '"') && (c1 != '\'')) {
        localStringBuilder.append(c1);
      }
      for (;;)
      {
        i1 += 1;
        break;
        localStringBuilder.append(String.format("\\u%04x", new Object[] { Integer.valueOf(c1) }));
      }
    }
    return localStringBuilder.toString();
  }
  
  public static int s(int paramInt)
  {
    if (paramInt == 0) {
      throw new IllegalArgumentException("Given Integer is zero");
    }
    return paramInt;
  }
  
  public static int s(View paramView)
  {
    return paramView.getWindowSystemUiVisibility();
  }
  
  public static long s(String paramString)
  {
    try
    {
      long l1 = DateUtils.parseDate(paramString).getTime();
      return l1;
    }
    catch (DateParseException paramString) {}
    return 0L;
  }
  
  public static Object s(Object paramObject)
  {
    return ((MediaController)paramObject).getPlaybackState();
  }
  
  public static ArrayList s(Context paramContext)
  {
    Object localObject = paramContext.getApplicationContext();
    paramContext = ((Context)localObject).getPackageName();
    ArrayList localArrayList;
    for (;;)
    {
      try
      {
        localObject = ((Context)localObject).getPackageManager().getServiceInfo(new ComponentName((Context)localObject, "com.google.android.libraries.social.licenses.MetadataHolder"), 640);
        if (((ServiceInfo)localObject).metaData != null)
        {
          paramContext = ((ServiceInfo)localObject).metaData;
          localArrayList = new ArrayList();
          Iterator localIterator = paramContext.keySet().iterator();
          if (!localIterator.hasNext()) {
            break;
          }
          String str2 = (String)localIterator.next();
          if (!str2.startsWith("com.google.android.libraries.social.licenses.LICENSE.")) {
            continue;
          }
          String[] arrayOfString = paramContext.getString(str2).split(":");
          if (arrayOfString.length != 2) {
            break label232;
          }
          bool = true;
          localObject = String.valueOf(str2);
          if (((String)localObject).length() == 0) {
            break label237;
          }
          localObject = "Invalid license meta-data value for element: ".concat((String)localObject);
          c(bool, localObject);
          long l1 = Long.parseLong(arrayOfString[0]);
          int i1 = Integer.parseInt(arrayOfString[1]);
          localArrayList.add(new dsl(str2.substring(53), l1, i1));
          continue;
        }
        paramContext = Bundle.EMPTY;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        throw new RuntimeException(String.format("Could not find application info for package: %s", new Object[] { paramContext }), localNameNotFoundException);
      }
      continue;
      label232:
      boolean bool = false;
      continue;
      label237:
      String str1 = new String("Invalid license meta-data value for element: ");
    }
    Collections.sort(localArrayList);
    return localArrayList;
  }
  
  public static void s(Parcel paramParcel, int paramInt)
  {
    int i1 = paramParcel.dataPosition();
    paramParcel.setDataPosition(paramInt - 4);
    paramParcel.writeInt(i1 - paramInt);
    paramParcel.setDataPosition(i1);
  }
  
  public static long t(View paramView)
  {
    return paramView.animate().getDuration();
  }
  
  public static Object t(Object paramObject)
  {
    return ((MediaController)paramObject).getMetadata();
  }
  
  public static void t(Parcel paramParcel, int paramInt)
  {
    s(paramParcel, paramInt);
  }
  
  public static void t(String paramString)
  {
    dnm.a(paramString);
    throw new dfu(paramString);
  }
  
  public static boolean t(int paramInt)
  {
    boolean bool = kn;
    return Log.isLoggable("PeopleService", paramInt);
  }
  
  public static void u(View paramView)
  {
    paramView.animate().cancel();
  }
  
  public static void u(Object paramObject)
  {
    ((MediaController.TransportControls)paramObject).play();
  }
  
  public static byte[] u(String paramString)
  {
    int i2 = paramString.length();
    if (i2 % 2 != 0) {
      throw new IllegalArgumentException("purported base16 string has odd number of characters");
    }
    byte[] arrayOfByte = new byte[i2 / 2];
    int i1 = 0;
    while (i1 < i2)
    {
      int i3 = Character.digit(paramString.charAt(i1), 16);
      int i4 = Character.digit(paramString.charAt(i1 + 1), 16);
      if ((i3 == -1) || (i4 == -1)) {
        throw new IllegalArgumentException("purported base16 string has illegal char");
      }
      arrayOfByte[(i1 / 2)] = ((byte)((i3 << 4) + i4));
      i1 += 2;
    }
    return arrayOfByte;
  }
  
  public static dfq v(String paramString)
  {
    paramString = ap(new JSONObject(paramString));
    dfr localDfr = dfq.a();
    int i1 = 0;
    while (i1 < paramString.f.length)
    {
      localDfr.a(dfo.a().a(ctm.u.toString(), paramString.f[i1]).a(ctm.p.toString(), dra.a(dro.b)).a(dro.c, paramString.g[i1]).a());
      i1 += 1;
    }
    return localDfr.a();
  }
  
  public static void v(View paramView)
  {
    paramView.animate().start();
  }
  
  public static void v(Object paramObject)
  {
    ((MediaController.TransportControls)paramObject).pause();
  }
  
  public static int w(Object paramObject)
  {
    return ((PlaybackState)paramObject).getState();
  }
  
  public static String w(String paramString)
  {
    return URLEncoder.encode(paramString, "UTF-8").replaceAll("\\+", "%20");
  }
  
  public static long x(Object paramObject)
  {
    return ((PlaybackState)paramObject).getPosition();
  }
  
  public static String x(String paramString)
  {
    String str = "";
    int i2 = 0;
    int i1 = 1;
    if (i2 < paramString.length())
    {
      char c1 = paramString.charAt(i2);
      if (c1 == '_') {
        i1 = 1;
      }
      for (;;)
      {
        i2 += 1;
        break;
        if (i1 != 0)
        {
          str = String.valueOf(str);
          c1 = Character.toUpperCase(c1);
          str = String.valueOf(str).length() + 1 + str + c1;
          i1 = 0;
        }
        else
        {
          str = String.valueOf(str);
          c1 = Character.toLowerCase(c1);
          str = String.valueOf(str).length() + 1 + str + c1;
        }
      }
    }
    return str;
  }
  
  public static long y(Object paramObject)
  {
    return ((PlaybackState)paramObject).getBufferedPosition();
  }
  
  public static String y(String paramString)
  {
    return a(dwg.a(paramString));
  }
  
  public static float z(Object paramObject)
  {
    return ((PlaybackState)paramObject).getPlaybackSpeed();
  }
  
  public static String z(String paramString)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    int i1 = 0;
    if (i1 < paramString.length())
    {
      char c1 = paramString.charAt(i1);
      if (i1 == 0) {
        localStringBuffer.append(Character.toLowerCase(c1));
      }
      for (;;)
      {
        i1 += 1;
        break;
        if (Character.isUpperCase(c1)) {
          localStringBuffer.append('_').append(Character.toLowerCase(c1));
        } else {
          localStringBuffer.append(c1);
        }
      }
    }
    return localStringBuffer.toString();
  }
  
  public void a(View paramView) {}
}
