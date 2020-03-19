package com.kms.kmsshared;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.PendingIntent;
import android.app.admin.DevicePolicyManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.SoundPool;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.provider.Telephony.Sms;
import android.telephony.PhoneNumberUtils;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import com.kaspersky.components.ucp.UcpDeviceType;
import com.kaspersky.components.ucp.UcpGeneralClient;
import com.kaspersky.components.ucp.UcpServiceId;
import com.kaspersky.components.utils.HashUtils;
import com.kaspersky.kts.webfiltering.BrowsersIndexInfo;
import com.kaspersky.kts.webfiltering.BrowsersIndexInfo.難經本義;
import com.kavsdk.KavSdk;
import com.kavsdk.antivirus.Antivirus;
import com.kavsdk.antivirus.ThreatType;
import com.kavsdk.antivirus.VirusDbInfo;
import com.kavsdk.shared.SdkUtils;
import com.kavsdk.webfilter.SupportedBrowserInfo;
import com.kavsdk.webfilter.WebFilterSupportedBrowsers;
import com.kavsdk.webfilter.WebFilterSupportedBrowsers.WebFilterMode;
import com.kms.KisMainActivity;
import com.kms.activation.gui.LicenseBlockedActivity;
import com.kms.activation.gui.LicenseInfoActivity;
import com.kms.antivirus.AntivirusDatabasesInfoIssue;
import com.kms.antivirus.AntivirusLastScanIssue;
import com.kms.antivirus.AntivirusNewObjectsToScanIssue;
import com.kms.gui.ShareItActivity;
import com.kms.licensing.iapurchase.GplayGraceAlertActionReceiver;
import com.kms.locator.ExtraLocator;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Formatter;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;
import o.Ae;
import o.DA;
import o.DF;
import o.DG;
import o.DH;
import o.DN;
import o.DO;
import o.DP;
import o.DQ;
import o.EJ;
import o.EN;
import o.EO;
import o.EP;
import o.EV;
import o.EY;
import o.FK;
import o.FV;
import o.Fd;
import o.GK;
import o.kB;
import o.kk;
import o.lV;
import o.mI;
import o.mJ;
import o.mK;
import o.nv;
import o.tD;
import o.tF;
import o.tH;
import o.tH.難經本義;
import o.wY;
import o.xS;
import o.xb;
import o.xr;
import o.皮.難經本義;
import o.論人面獨能耐寒.一難;
import o.論人面獨能耐寒.難經本義卷上;
import org.json.JSONArray;
import org.json.JSONException;

public final class Utils
{
  public static final Uri 一難;
  private static final int[][] 七難;
  private static final String 三難;
  private static int 九難;
  public static DH 二難;
  private static final Object 五難;
  public static final Uri 僅輸入原文;
  private static String 八難;
  private static final List<String> 六難;
  private static int 四難;
  private static SoundPool 論太過不及等反常脈象;
  private static final byte[] 論寸口脈平而死者 = { 110, 60, 52, -112, -13, 1, 62, -52, -14, 9, -15, 2, 5, 4, 53, -64, -1, -7, -13, 1, 62, -58, 47, -47, -10, 4, -13, 1, 68, -58, 47, -62, 0, 64, 5, -7, 56, 2, 8, -13, 1, -11, 5, 13, -21, 14, -8, -3, 56, -3, 7, -75, 10, -7, -10, 9, 32, -31, -12, -1, 23, -18, -8, 6, -12, 7, -8, 0, -12, 88, -71, 4, -9, -4, 6, 0, -13, 0, -1, 8, 1, -18, 10, -14, 12, -12, -6, 1, -16, 42, -12, -18, -8, 6, -12, 7, -8, 0, -12, 80, -14, 9, -15, 2, 5, 4, 53, -60, -6, -7, 14, -10, -7, 69, -52, -3, -18, 10, -7, 0, 63, -23, -2, -8, 6, -16, 14, -77, 2, -3, 5, 68, -8, -28, -9, -4, 6, 0, 67, -85, 4, 78, -88, 4, 8, -12, 14, 68, -71, -4, -4, 6, -13, 1, 62, -52, -14, 9, -15, 2, 5, 4, 53, -60, -6, -7, 14, -14, 3, 12, -12, 61, -71, 14, -8, 6, -12, 7, -8, 0, -12, 74, -28, -12, -18, -8, 6, -12, 7, -8, 0, -12, -13, 1, 62, -73, 10, -5, 0, -7, 18, -11, -10, 14, 54, -52, -14, 9, -15, 2, 5, 4, -30, 7, 67, -81, 14, -3, -9, 9, -7, 1, 68, -79, 12, -13, 7, 68, -84, 10, -8, -7, 8, 7, 67, -79, -2, -6, 83, -67, -4, 68, -79, -8, 8, -1, -84, 7, 9, 53, -52, -14, 9, -15, 2, 5, 4, 53, -54, -19, 2, -2, 3, -4, 67, -55, -6, -10, 66, -53, 1, -12, -1, -8, -80, -4, 81, -3, -18, 10, -14, 12, -12, -6, -14, 9, -15, 2, 5, 4, 53, -60, -6, -7, 14, -10, -7, 69, -56, -20, 3, 1, 16, 50, -39, 14, -20, 3, 14, -3, -9, 9, -7, 1, -13, 1, 62, -62, -3, -7, -13, 1, 62, -62, -10, 0, 68, -57, -13, 12, -1, -9, 4, 10, -10, 2, -15, 13, -6, 58, 0, -58, 5, -21, 5, 68, -55, 2, -20, -13, 1, 62, -62, 9, -19, 2, 10, -14, -2, 7, -15, 74, -62, -10, 0, -13, 1, 62, -58, -9, -1, 7, -6, 6, 54, -52, -14, 9, -15, 2, 5, 4, 53, -52, -16, -1, -4, 68, -54, -6, -11, 2, 1, 7, 54, -32, -21, -9, -6, -13, 1, 62, -69, 16, -18, 5, 10, -2, 51, -69, 16, -18, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -13, 1, 62, -52, -14, 9, -15, 2, 5, 4, 53, -53, -17, 2, -9, 3, 13, -14, 67, -21, -49, 2, -9, 3, 13, -14, 48, -35, -18, 10, -14, 12, -12, -6, -13, 1, 62, -62, -3, -7, 68, -70, 17, -13, -7, -3, 6, 6, -10, -3, 2, 1, -3, -14, 9, -15, 2, 5, 4, 4, -11, 4, 6, -18, 16, -8, -3, -31, -14, 70, -78, 83, -72, 1, -10, 8, -14, 16, -20, 14, 68, -85, 11, 2, 68, -68, 1, -14, -2, 0, 4, 5, 1, -12, 75, -81, 14, -20, 11, 71, -71, -10, -4, 81, -13, 1, 62, -62, -3, -7, 68, -52, -19, -1, -3, 2, 16, -14, -7, -13, 1, 62, -58, -9, -1, 7, -6, 6, 54, -52, -14, 9, -15, 2, 5, 4, 53, -71, 18, -12, 0, -13, 1, 62, -52, -14, 9, -15, 2, 5, 4, 53, -65, 7, 2, 8, 14, -13, 1, 62, -62, -3, -7, 68, -57, -13, 12, -1, 54, -52, -3, -18, 10, -7, 0, 63, -58, -10, 3, 10, -25, 74, -58, -12, 16, -3, -3, 54, -52, -12, 6, -14, -3, 69, -54, -10, 2, 5, -9, 5, 0, 1, -16, 42, -12, -18, -8, 6, -12, 7, -8, 0, -12, -13, 0, -7, 14, -10, -7, 57, 10, -1, -69, 5, -7, -13, 1, 62, -65, 8, -20, 3, 14, -8, -8, 13, -3, -15, 2, 16, -14, 10, 1, -15, 0, 1, 2, -6, 8, -3, -7, 6, -7, 14, 0, 15, 1, -12, 6, -1, 2, -4, 0, 1, -7, 5, -4, -8, 5, 0, -14, 9, -15, 2, 5, 4, 53, -60, -6, -7, 14, -10, -7, 69, -52, -3, -18, 10, -7, 0, 63, -32, 11, -9, -6, 7, 3, -13, 1, -11, 5, 13, -21, 14, -8, -3, 56, -3, 7, -75, 10, -7, 13, 2, -4, -13, 1, 62, -52, -14, 9, -15, 2, 5, 4, 53, -54, -6, -11, 2, 1, 7, -14, 9, -15, 2, 5, 4, 53, -60, -6, -7, 14, -10, -7, 69, -54, 1, -20, 14, -3, -9, -4, -8, 74, -23, -2, -2, 4, -21, 8, -9, -14, 9, -15, 2, 5, 4, 53, -60, -6, -7, 14, -10, -7, 69, -52, -3, -18, 10, -7, 0, 63, -38, 13, -10, 9, -13, 1, 62, -52, -14, 9, -15, 2, 5, 4, 53, -53, -11, -10, 15, -16, 4, -1, -6, 11, 12, -9, -3, -8, 16, 33, -31, -12, -1, 29, -34, -6, 10, 2, -4, 5, 1, -20, 10, -7, 0, 77, -71, 4, -9, -4, 6, 0, -22, -19, -2, 83, -84, 15, 1, -14, 77, -85, 10, -5, 7, 68, -74, -11, 82, -72, -12, 12, 3, -20, 14, -14, 81, -85, 11, 6, -14, 77, -68, -19, 2, -1, 12, -10, -7, 83, -85, 10, -5, 7, -2, -18, 12, 5, -3, 5, -18, 0, 2, 2, 5, -23, -57, 16, -23, 71, -14, 9, -15, 2, 5, 4, 53, -60, -6, -7, 14, -10, -7, 69, -54, 1, -20, 14, -3, -9, -4, -8, 74, -21, -17, 2, -9, 3, 17, -2, -11, 6, -13, 1, 62, -58, -9, -1, 7, -6, 6, 54, -52, -14, 9, -15, 2, 5, 4, 53, -52, -16, -1, -4, 68, -55, -12, 11, -17, -9, 2, -64, 58, -12, -3, -39, -14, 9, -15, 2, 5, 4, 53, -60, -6, -7, 14, -10, -7, 69, -54, 1, -20, 14, -3, -9, -4, -8, 74, -27, -8, 1, 7, -5, 11, -7, 1, 53, -66, 1, 6, -29, -7, 2, 14, -3, 68, -84, -2, 1, 8, -6, 6, 70, -74, -11, 82, -77, -4, 0, 6, 1, -14, 81, -85, 11, 2, -10, 77, -72, -15, 11, 4, 67, -77, 6, -10, 6, -14, 11, 70, -12, -3, -71, -14, 9, -15, 2, 5, 4, 53, -67, 10, -14, 4, 3, -11, -1, 9, -7, 0, 63, -32, -3, 10, -6, 2, -20, -7, 14, 7, -8, 0, 8, -27, 11, -2, 18, -20, 14, -13, 1, 62, -58, -9, -1, 7, -6, 6, 54, -52, -14, 9, -15, 2, 5, 4, 53, -62, 5, -1, -12, -67, 1, 6, -13, -1, 3, 53, 10, -1, -70, 14, -15, -2, -14, 9, -15, 2, 5, 4, 53, -60, -6, -7, 14, -10, -7, 69, -56, -20, 3, 1, 16, 50, -28, -6, 4, -12, 10, 7, -12, -20, 21, -6, -7, 14, -10, -7, 0, -13, 1, 62, -62, -3, -7, 68, -57, -13, 12, -1, 54, -52, -3, -18, 10, -7, 0, 63, -58, -10, 3, 10, -25, 74, -58, -12, 16, -3, -3, 54, -52, -12, 6, -14, -3, 69, -54, 1, -14, 10, -3, -8, 6, 0, -18, -3, -9, 9, -7, 1, 68, -79, 12, -13, 7, 68, -84, 10, -8, -7, 8, 7, 67, -79, -2, -6, 83, -67, -4, 68, -79, -8, 8, -1, -1, -1, -1, 73, -31, -1, 29, -54, -1, 67, -41, -1, 13, -52, -1, 50, -58, -1, -13, 0, -7, 14, -10, -7, 57, 10, -1, -69, 5, -7, 67, -69, 13, -10, -7, -14, 9, -15, 2, 5, 4, 53, -60, -6, -7, 14, -10, -7, 69, -52, -3, -18, 10, -7, 0, 63, -41, 12, 3, -19, -45, -7, 8, 7, 67, -79, -2, -6, 83, -72, 1, -16, 83, -74, -5, 11, -7, 1, 68, -71, -4, -4, 6, -28, -9, -4, 6, 0, 67, -85, 4, 78, -77, 10, -21, 6, 10, -6, 71, -80, 2, 7, 67, -81, 14, -3, -9, 9, -7, 1, -3, -7, -14, 9, -15, 2, 5, 4, 53, -60, -6, -7, 14, -10, -7, 69, -56, -20, 3, 1, 16, 50, -38, -2, 1, 12, 3, -13, -13, 0, -7, 14, -10, -7, 57, 10, -1, -69, 5, -7, 67, -59, -6, 11, -14, -10, -13, 1, 62, -62, -3, -7, 68, -70, 17, -13, -7, -3, 6, 6, -1, -6, -4, 12, 0, -12, 7, -8, 0, 8, -13, 1, 62, -62, -3, -7, 68, -64, 7, -3, 5, -6, -10, 0, 1, -11, 5, 44, -18, -1, 2, -4, 0, 1, -7, 5, -4, -8, 5, 0, 14, -20, 3, 68, -58, -13, 6, 0, -2, 18, -20, -2, 1, -2, -18, 12, 5, -3, 5, -11, 4, 4, -8, -10, -4, 18, -14, 2, 5, -3, -10, -6, 9, -6, 6, -10, 9, 32, -31, -12, -1, -14, 9, -15, 2, 5, 4, 53, -60, -6, -7, 14, -10, -7, 69, -52, -3, -18, 10, -7, 0, 63, -21, 0, -20, -1, 14, -14, -8, -7, 27, -6, 6, -14, 6, 1, 0, 10, 6, 15, -14, 9, -15, 2, 5, 4, 53, -71, 14, -8, 6, -12, 7, -8, 0, -12, 74, -39, -18, -8, 6, -12, 7, -8, 0, -12, 43, -21, -14, 12, -7, 1, -14, 2, 5, -3, -10, -6, 13, 17, -35, 17, -1, -3, -15, -1, 12, -16, 8, -1, -14, 47, -38, -6, -2, 18, -12, -1, 6, 0, 7, 9, 53, -52, -14, 9, -15, 2, 5, 4, 54, -56, -6, -10, 66, -63, -1, -7, 69, -71, 5, -7, -22, 19, -32, -19, 13, -15, 82, -85, 10, -5, 7, 68, -74, -11, 82, -72, -12, 12, 3, -20, 14, -14, 81, -85, 11, 6, -14, 77, -68, -19, 2, -1, 12, -10, -7, 83, -85, 10, -5, 7, 1, -14, 10, -3, -8, 30, -29, -11, -1, 13, 0, 32, -31, -12, -1, -8, 36, -34, -6, 10, 2, -4, 5, 1, -20, 10, -7, 0, 45, -13, 0, -7, 14, -10, -7, 57, 10, -1, -59, 5, -1, 51, -51, -4, -11, 1, -11, 17, 19, -53, -14, 9, -15, 2, 5, 4 };
  public static Uri 論寸口脈與經經脈榮衛度數;
  public static final List<String> 論尺寸為脈之大要會;
  private static String 論王脈;
  private static final List<String> 論脈之陰陽虛實;
  private static final AtomicBoolean 論脈有輕重;
  private static final AtomicBoolean 論脈有陰陽之法;
  @SuppressLint({"SimpleDateFormat"})
  public static final DateFormat 難經本義;
  public static final Uri 難經本義卷上;
  
  static
  {
    九難 = 36;
    三難 = Utils.class.getSimpleName();
    難經本義 = new SimpleDateFormat(難經本義(457, 論寸口脈平而死者[32], 論寸口脈平而死者['È']));
    僅輸入原文 = Uri.parse(難經本義(九難 | 0x11B, -論寸口脈平而死者['͝'], 論寸口脈平而死者['È']));
    難經本義卷上 = Uri.parse(難經本義(九難 | 0x193, -論寸口脈平而死者['͝'], 論寸口脈平而死者['ǔ']));
    一難 = Uri.parse(難經本義(九難 | 0x402, -論寸口脈平而死者['͝'], 論寸口脈平而死者[84]));
    二難 = null;
    論尺寸為脈之大要會 = Arrays.asList(new String[] { AntivirusNewObjectsToScanIssue.難經本義, AntivirusLastScanIssue.僅輸入原文, AntivirusDatabasesInfoIssue.難經本義卷上, AntivirusDatabasesInfoIssue.僅輸入原文 });
    論太過不及等反常脈象 = null;
    四難 = 0;
    論脈有陰陽之法 = new AtomicBoolean();
    五難 = new Object();
    論脈有輕重 = new AtomicBoolean();
    Object localObject1 = 難經本義(888, -論寸口脈平而死者['͝'], -論寸口脈平而死者['ī']);
    int i = -論寸口脈平而死者['͝'];
    六難 = Arrays.asList(new String[] { localObject1, 難經本義(602, i, i), 難經本義(九難 | 0x2CA, -論寸口脈平而死者['͝'], 論寸口脈平而死者['״']), 難經本義(1302, -論寸口脈平而死者['͝'], 論寸口脈平而死者[43]), 難經本義(九難 | 0x44A, -論寸口脈平而死者['͝'], 論寸口脈平而死者[45]), 難經本義(九難 | 0x5C1, -論寸口脈平而死者['͝'], -論寸口脈平而死者['ī']) });
    localObject1 = 難經本義(1387, -論寸口脈平而死者['͝'], 論寸口脈平而死者[63]);
    Object localObject2 = 難經本義(1352, -論寸口脈平而死者['͝'], 論寸口脈平而死者['Į']);
    Object localObject3 = 難經本義(九難 | 0x541, -論寸口脈平而死者['͝'], 論寸口脈平而死者['Ǽ']);
    String str = 難經本義(277, -論寸口脈平而死者['͝'], 論寸口脈平而死者[45]);
    i = -論寸口脈平而死者['͝'];
    論脈之陰陽虛實 = Arrays.asList(new String[] { localObject1, localObject2, localObject3, str, 難經本義(1050, i, i + 2), 難經本義(1171, -論寸口脈平而死者['͝'], 論寸口脈平而死者['ʩ']), 難經本義(九難 | 0x109, -論寸口脈平而死者['͝'], -論寸口脈平而死者['ī']), 難經本義(1241, -論寸口脈平而死者['͝'], 論寸口脈平而死者['ّ']), 難經本義(1683, -論寸口脈平而死者['͝'], 論寸口脈平而死者['Į']) });
    localObject1 = new int[] { 2131625030, 2131624846, 2131624871 };
    localObject2 = new int[] { 2131625029, 2131624845, 2131624874 };
    localObject3 = new int[] { 2131625028, 2131624844, 2131624873 };
    七難 = new int[][] { { 2131625027, 2131624843, 2131624870 }, { 2131625032, 2131624848, 2131624872 }, localObject1, localObject2, localObject3, { 2131625031, 2131624847, 2131624875 } };
  }
  
  public Utils() {}
  
  public static native void freeNative();
  
  public static native String getPasswordHash(String paramString);
  
  public static native void initNative();
  
  public static ResolveInfo 一難(Context paramContext)
  {
    Intent localIntent = new Intent(難經本義(九難 | 0x3C9, 論寸口脈平而死者[60] + 1, -論寸口脈平而死者['ɞ']));
    localIntent.addCategory(難經本義(716, 論寸口脈平而死者[60] + 1, 論寸口脈平而死者['״']));
    localIntent.addCategory(難經本義(九難 | 0x38B, 論寸口脈平而死者[60] + 1, 論寸口脈平而死者['ٽ']));
    return paramContext.getPackageManager().resolveActivity(localIntent, 0);
  }
  
  public static String 一難(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    paramString = PhoneNumberUtils.extractNetworkPortion(paramString);
    StringBuilder localStringBuilder = new StringBuilder();
    int i = paramString.length() - 1;
    while (i >= 0)
    {
      char c = paramString.charAt(i);
      if (PhoneNumberUtils.isISODigit(c))
      {
        localStringBuilder.insert(0, c);
        if (localStringBuilder.length() == 10) {
          break;
        }
      }
      i -= 1;
    }
    return localStringBuilder.toString();
  }
  
  public static void 一難()
  {
    TelephonyManager localTelephonyManager = (TelephonyManager)KMSApplication.難經本義.getSystemService(難經本義(281, 論寸口脈平而死者[9], 論寸口脈平而死者[13]));
    Method[] arrayOfMethod1 = null;
    for (;;)
    {
      int i;
      try
      {
        Method[] arrayOfMethod2 = Class.forName(難經本義(九難 | 0x83, 論寸口脈平而死者[60] + 1, 論寸口脈平而死者['͆'])).getDeclaredMethods();
        int j = arrayOfMethod2.length;
        i = 0;
        Object localObject = arrayOfMethod1;
        if (i < j)
        {
          localObject = arrayOfMethod2[i];
          if (!((Method)localObject).toString().contains(難經本義(1614, 論寸口脈平而死者['È'], 論寸口脈平而死者[43]))) {
            break label278;
          }
          ((Method)localObject).setAccessible(true);
          localObject = ((Method)localObject).invoke(localTelephonyManager, new Object[0]);
        }
        arrayOfMethod1 = Class.forName(難經本義(1549, -論寸口脈平而死者['͝'], 九難 + 4)).getMethods();
        j = arrayOfMethod1.length;
        i = 0;
        if (i < j)
        {
          localTelephonyManager = arrayOfMethod1[i];
          if (localTelephonyManager.toString().contains(難經本義(212, -論寸口脈平而死者['ī'], 論寸口脈平而死者[63])))
          {
            localTelephonyManager.invoke(localObject, new Object[0]);
          }
          else
          {
            i += 1;
            continue;
          }
        }
        return;
      }
      catch (Exception localException)
      {
        Log.e(難經本義(347, 九難 | 0xA, 論寸口脈平而死者[11]), 難經本義(九難 | 0x64B, -論寸口脈平而死者['ī'], -論寸口脈平而死者['͝']));
        localException.printStackTrace();
        return;
      }
      label278:
      i += 1;
    }
  }
  
  public static int 七難()
  {
    long l = 論脈有輕重();
    l = System.currentTimeMillis() - l;
    if (l < 0L)
    {
      String str = 難經本義(347, 九難 | 0xA, 論寸口脈平而死者[11]);
      int i = -論寸口脈平而死者[''];
      int j = 論寸口脈平而死者[36];
      Log.e(str, 難經本義(i, j, j & 0xEE));
    }
    return (int)((259200000L - l - 1L) / 60000L) + 1;
  }
  
  private static String 七難(Context paramContext)
  {
    Iterator localIterator1 = 論脈之陰陽虛實.iterator();
    while (localIterator1.hasNext())
    {
      String str = (String)localIterator1.next();
      Iterator localIterator2 = SdkUtils.getInstalledApplications(paramContext, 0).iterator();
      while (localIterator2.hasNext())
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator2.next();
        if ((localApplicationInfo.packageName.equalsIgnoreCase(str)) && (!論脈不滿五十動而一止(paramContext.getApplicationInfo().manageSpaceActivityName).equals(論脈不滿五十動而一止(localApplicationInfo.manageSpaceActivityName)))) {
          return str;
        }
      }
    }
    return null;
  }
  
  public static void 七難(String paramString)
  {
    if (paramString == null) {
      throw new IllegalArgumentException(難經本義(九難 | 0x1C3, -論寸口脈平而死者['Ӧ'], 論寸口脈平而死者['ٽ']));
    }
    Context localContext = KMSApplication.難經本義;
    paramString = localContext.getPackageManager().getLaunchIntentForPackage(paramString);
    try
    {
      localContext.startActivity(paramString);
      return;
    }
    catch (Exception paramString)
    {
      Log.e(難經本義(347, 九難 | 0xA, 論寸口脈平而死者[11]), 難經本義(九難 | 0x152, 論寸口脈平而死者['Ƙ'], 論寸口脈平而死者['״']), paramString);
    }
  }
  
  public static String 三難(String paramString)
  {
    return new File(paramString).getParentFile().getPath();
  }
  
  public static void 三難()
  {
    DA.難經本義(9);
  }
  
  public static boolean 三難(Context paramContext)
  {
    return 七難(paramContext) != null;
  }
  
  public static String 九難()
  {
    return KMSApplication.難經本義.getString(2131624988);
  }
  
  public static String 九難(String paramString)
  {
    if (mK.難經本義(paramString)) {
      return paramString;
    }
    String str = paramString.replace(難經本義(1697, 論寸口脈平而死者['͖'] - 1, 論寸口脈平而死者[32]), "");
    if (str.length() == 20)
    {
      paramString = new StringBuilder();
      int i = 0;
      while (i < 4)
      {
        paramString.append(str.substring(i * 5, (i + 1) * 5));
        if (i < 3) {
          paramString.append('-');
        }
        i += 1;
      }
      return paramString.toString();
    }
    return paramString;
  }
  
  public static Drawable 二難(String paramString)
  {
    try
    {
      paramString = KMSApplication.難經本義.getPackageManager().getApplicationIcon(paramString);
      return paramString;
    }
    catch (PackageManager.NameNotFoundException paramString) {}
    return null;
  }
  
  private static Uri 二難(Context paramContext, int paramInt)
  {
    Object localObject3 = null;
    try
    {
      try
      {
        Object localObject1 = paramContext.getExternalCacheDir();
        Object localObject2 = paramContext.getResources().getResourceEntryName(paramInt);
        File localFile = new File((File)localObject1, mK.難經本義(HashUtils.難經本義((String)localObject2, 難經本義(1120, 論寸口脈平而死者['֡'], 論寸口脈平而死者[11]))) + 難經本義(580, 論寸口脈平而死者['Ȉ'], 論寸口脈平而死者['¨']));
        if (!localFile.exists())
        {
          Bitmap localBitmap = BitmapFactory.decodeResource(paramContext.getResources(), paramInt);
          localObject1 = null;
          paramContext = null;
          try
          {
            localObject2 = new FileOutputStream(localFile);
            paramContext = (Context)localObject2;
            localObject1 = localObject2;
            localBitmap.compress(Bitmap.CompressFormat.PNG, 0, (OutputStream)localObject2);
          }
          catch (IOException localIOException)
          {
            localObject1 = paramContext;
            Log.e(三難, 難經本義(1568, 論寸口脈平而死者['Ƙ'], 論寸口脈平而死者['ّ']), localIOException);
          }
          finally
          {
            localBitmap.recycle();
            難經本義((Closeable)localObject1);
          }
        }
        paramContext = Uri.fromFile(localFile);
      }
      catch (Exception paramContext)
      {
        Log.e(三難, 難經本義(397, 論寸口脈平而死者['Ì'], 論寸口脈平而死者[60]), paramContext);
        paramContext = localObject3;
      }
      return paramContext;
    }
    finally {}
  }
  
  public static void 二難(Context paramContext)
  {
    String str = 七難(paramContext);
    if (str != null)
    {
      if (難經本義卷上(paramContext, str))
      {
        七難(str);
        return;
      }
      五難(str);
    }
  }
  
  public static boolean 二難()
  {
    return EV.四難().難經本義卷上() != 0;
  }
  
  public static void 五難(Context paramContext)
  {
    Object localObject = new Intent(paramContext, KisMainActivity.class);
    ((Intent)localObject).addFlags(268435456);
    localObject = PendingIntent.getActivity(paramContext, 0, (Intent)localObject, 0);
    String str1 = paramContext.getString(2131624792);
    String str2 = paramContext.getString(2131624772);
    paramContext = new 皮.難經本義(paramContext);
    paramContext.難經本義(str1).僅輸入原文(str2).難經本義(2130837717).難經本義((PendingIntent)localObject).僅輸入原文(true);
    paramContext.難經本義(new 論人面獨能耐寒.難經本義卷上().僅輸入原文(str2).難經本義(str1));
    DA.難經本義(11, paramContext);
  }
  
  public static void 五難(String paramString)
  {
    if (paramString == null) {
      return;
    }
    Context localContext = KMSApplication.難經本義;
    Intent localIntent = new Intent(難經本義(1601, 論寸口脈平而死者[60] + 1, 論寸口脈平而死者['״']));
    localIntent.setData(Uri.fromParts(難經本義(1393, 論寸口脈平而死者[9], 論寸口脈平而死者[63]), paramString, null));
    localIntent.addFlags(268435456);
    localContext.startActivity(localIntent);
  }
  
  public static boolean 五難()
  {
    return 難經本義(KMSApplication.論脈不滿五十動而一止().getVirusDbInfo());
  }
  
  public static long 僅輸入原文(VirusDbInfo paramVirusDbInfo)
  {
    return 難經本義(paramVirusDbInfo.mYear, paramVirusDbInfo.mMonth, paramVirusDbInfo.mDay, paramVirusDbInfo.mHour, paramVirusDbInfo.mMinute, paramVirusDbInfo.mSecond);
  }
  
  public static String 僅輸入原文(DataInputStream paramDataInputStream)
  {
    if (paramDataInputStream.readByte() == 0) {
      return null;
    }
    return paramDataInputStream.readUTF();
  }
  
  public static String 僅輸入原文(InputStream paramInputStream)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    byte[] arrayOfByte = new byte['ࠀ'];
    for (;;)
    {
      int i = paramInputStream.read(arrayOfByte);
      if (i == -1) {
        break;
      }
      localByteArrayOutputStream.write(arrayOfByte, 0, i);
    }
    localByteArrayOutputStream.close();
    paramInputStream.close();
    return localByteArrayOutputStream.toString();
  }
  
  public static String 僅輸入原文(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    paramString = PhoneNumberUtils.extractNetworkPortion(paramString);
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    while (i < paramString.length())
    {
      char c = paramString.charAt(i);
      if (c == '+') {
        localStringBuilder.append(c);
      } else if ((c >= '0') && (c <= '9')) {
        localStringBuilder.append(c);
      }
      i += 1;
    }
    return localStringBuilder.toString();
  }
  
  public static String 僅輸入原文(String paramString, Context paramContext)
  {
    try
    {
      paramString = 僅輸入原文(paramContext.getAssets().open(paramString));
      return paramString;
    }
    catch (Exception paramString) {}
    return null;
  }
  
  public static String 僅輸入原文(byte[] paramArrayOfByte)
  {
    return 難經本義(paramArrayOfByte, 難經本義(170, -論寸口脈平而死者['̩'], 論寸口脈平而死者['¨']));
  }
  
  public static void 僅輸入原文()
  {
    DA.難經本義(5);
    Object localObject = KMSApplication.難經本義;
    int i = 九難 | 0x25A;
    if (((Context)localObject).checkCallingOrSelfPermission(難經本義(i, i & 0x18, 九難)) == 0) {
      i = 1;
    } else {
      i = 0;
    }
    if (i != 0) {}
    for (;;)
    {
      try
      {
        TelephonyManager localTelephonyManager = (TelephonyManager)KMSApplication.難經本義.getSystemService(難經本義(281, 論寸口脈平而死者[9], 論寸口脈平而死者[13]));
        localObject = null;
        Method[] arrayOfMethod = Class.forName(難經本義(九難 | 0x83, 論寸口脈平而死者[60] + 1, 論寸口脈平而死者['͆'])).getDeclaredMethods();
        int j = arrayOfMethod.length;
        i = 0;
        if (i < j)
        {
          Method localMethod = arrayOfMethod[i];
          if (!localMethod.toString().contains(難經本義(1074, 論寸口脈平而死者['È'], 論寸口脈平而死者[84]))) {
            break label274;
          }
          localMethod.setAccessible(true);
          localObject = localMethod.invoke(localTelephonyManager, new Object[0]);
          break label274;
        }
        if (localObject != null) {
          localObject.getClass().getDeclaredMethod(難經本義(論寸口脈平而死者['ڔ'], -論寸口脈平而死者['͝'], -論寸口脈平而死者['']), new Class[0]).invoke(localObject, new Object[0]);
        }
        return;
      }
      catch (Exception localException)
      {
        Log.e(難經本義(347, 九難 | 0xA, 論寸口脈平而死者[11]), 難經本義(九難 | 0x340, 論寸口脈平而死者[50], -論寸口脈平而死者['']));
      }
      return;
      label274:
      i += 1;
    }
  }
  
  public static void 僅輸入原文(Activity paramActivity)
  {
    難經本義(paramActivity, true);
  }
  
  public static void 僅輸入原文(Context paramContext)
  {
    Object localObject = new Intent(paramContext, LicenseInfoActivity.class);
    ((Intent)localObject).putExtra(難經本義(134, 論寸口脈平而死者[43], 論寸口脈平而死者['״']), true);
    localObject = PendingIntent.getActivity(paramContext, 0, (Intent)localObject, 0);
    String str = paramContext.getString(2131625257);
    paramContext = new 皮.難經本義(paramContext);
    paramContext.難經本義(2130837718).難經本義卷上(str).難經本義(0L).難經本義(str).難經本義((PendingIntent)localObject).僅輸入原文(true);
    DA.難經本義(8, paramContext);
  }
  
  public static void 僅輸入原文(Context paramContext, int paramInt)
  {
    難經本義(paramContext, String.format(paramContext.getString(2131625064), new Object[] { Integer.valueOf(paramInt) }), 2131625034, 2130837735, 6, 論四時之脈以胃氣為本());
  }
  
  public static boolean 僅輸入原文(Context paramContext, String paramString)
  {
    return TextUtils.equals(paramContext.getPackageManager().getInstallerPackageName(paramContext.getPackageName()), paramString);
  }
  
  private static boolean 僅輸入原文(String paramString, int paramInt)
  {
    PackageManager localPackageManager = KMSApplication.難經本義.getPackageManager();
    try
    {
      paramString = localPackageManager.getApplicationInfo(paramString, 0);
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      return false;
    }
    return (paramString.flags & paramInt) != 0;
  }
  
  public static String 八難()
  {
    if (論王脈 != null) {
      return 論王脈;
    }
    Object localObject = KMSApplication.難經本義;
    String str = null;
    if (localObject != null) {}
    try
    {
      str = ((Context)localObject).getPackageManager().getPackageInfo(((Context)localObject).getPackageName(), 0).versionName;
      if (mK.難經本義(str))
      {
        論王脈 = 難經本義(1016, -論寸口脈平而死者['ǲ'], 論寸口脈平而死者['Ǽ']);
      }
      else
      {
        localObject = new StringBuilder(str.length() + 難經本義(1209, 論寸口脈平而死者['Ȉ'], 論寸口脈平而死者[5]).length());
        ((StringBuilder)localObject).append(str);
        if (mK.一難(str, 難經本義(1697, 論寸口脈平而死者['Ȉ'], 論寸口脈平而死者[32])) < 3) {
          ((StringBuilder)localObject).append(難經本義(1209, 論寸口脈平而死者['Ȉ'], 論寸口脈平而死者[5]));
        }
        論王脈 = ((StringBuilder)localObject).toString();
      }
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      localNameNotFoundException.printStackTrace();
      throw new RuntimeException(localNameNotFoundException);
    }
    return 論王脈;
  }
  
  public static String 八難(String paramString)
  {
    int j = 32 - paramString.length();
    StringBuilder localStringBuilder = new StringBuilder(36);
    if (j < 0)
    {
      i = -論寸口脈平而死者['̩'];
      throw new Exception(難經本義(681, i, i + 2));
    }
    int i = 0;
    while (i < j)
    {
      localStringBuilder.append('0');
      i += 1;
    }
    localStringBuilder.append(paramString);
    localStringBuilder.insert(8, '-');
    localStringBuilder.insert(13, '-');
    localStringBuilder.insert(18, '-');
    localStringBuilder.insert(23, '-');
    return localStringBuilder.toString();
  }
  
  public static AntivirusDatabasesStatus 六難()
  {
    if (KMSApplication.論脈不滿五十動而一止().isInitialized())
    {
      long l = 論脈有輕重();
      if (l != 0L)
      {
        l = new GregorianCalendar().getTimeInMillis() - l;
        if (l < 259200000L) {
          return AntivirusDatabasesStatus.Actual;
        }
        if (l < 604800000L) {
          return AntivirusDatabasesStatus.Old;
        }
        return AntivirusDatabasesStatus.VeryOld;
      }
    }
    return AntivirusDatabasesStatus.VeryOld;
  }
  
  public static boolean 六難(String paramString)
  {
    return getPasswordHash(paramString).compareTo(EV.三難().二難()) == 0;
  }
  
  public static String 十一難(String paramString)
  {
    try
    {
      String str = new File(paramString).getCanonicalPath();
      return str;
    }
    catch (IOException localIOException)
    {
      Log.e(難經本義(347, 九難 | 0xA, 論寸口脈平而死者[11]), 難經本義(1208, 論寸口脈平而死者['Ì'], 九難 + 1) + paramString, localIOException);
    }
    return null;
  }
  
  public static boolean 十一難()
  {
    boolean bool1 = EV.論太過不及等反常脈象().僅輸入原文();
    boolean bool2 = 論別知臟腑之病();
    boolean bool3 = 十難();
    return (!bool2) || (!bool1) || ((!bool3) && (!論一脈十變()));
  }
  
  public static void 十三難()
  {
    tH.難經本義(難經本義(263, 九難 | 0xA, 論寸口脈平而死者['Į']));
    tH.難經本義(難經本義(980, 論寸口脈平而死者[45], 論寸口脈平而死者['ǔ']), 難經本義(1664, 論寸口脈平而死者[45], 論寸口脈平而死者['ǔ']), 難經本義(1370, 論寸口脈平而死者[45], 論寸口脈平而死者['È']));
    tH.難經本義(new tH.難經本義(FV.僅輸入原文(), FV.難經本義(), 難經本義(1016, -論寸口脈平而死者['ǲ'], 論寸口脈平而死者['Ǽ'])));
  }
  
  public static void 十二難() {}
  
  private static String 十六難()
  {
    String str = null;
    if (wY.論脈之陰陽虛實().難經本義(難經本義(234, 論寸口脈平而死者['Ҋ'], -論寸口脈平而死者['͝']))) {
      str = wY.論脈之陰陽虛實().難經本義卷上(難經本義(234, 論寸口脈平而死者['Ҋ'], -論寸口脈平而死者['͝']));
    }
    return str;
  }
  
  public static ArrayList<String> 十四難()
  {
    return kB.難經本義(Integer.MAX_VALUE);
  }
  
  public static boolean 十難()
  {
    return EV.三難().論王脈() == xS.僅輸入原文(KMSApplication.十二難());
  }
  
  public static boolean 十難(String paramString)
  {
    return 僅輸入原文(paramString, 2097152);
  }
  
  public static PackageInfo 四難(String paramString)
  {
    return KMSApplication.難經本義.getPackageManager().getPackageArchiveInfo(paramString, 0);
  }
  
  public static String 四難()
  {
    Object localObject = 十六難();
    if (!mK.僅輸入原文((String)localObject)) {
      return mK.難經本義(HashUtils.難經本義((String)localObject, 難經本義(1120, 論寸口脈平而死者['֡'], 論寸口脈平而死者[11])));
    }
    Context localContext = KMSApplication.難經本義;
    EO localEO = EV.論尺寸為脈之大要會();
    String str = (String)localEO.二難(13);
    localObject = str;
    if (str.equals(難經本義(1289, 論寸口脈平而死者['ӟ'], 論寸口脈平而死者[45])))
    {
      if (論五臟病之內外證()) {
        localObject = 論寸口脈與經經脈榮衛度數(localContext);
      } else {
        localObject = 論脈之陰陽虛實(localContext);
      }
      localEO.難經本義(13, localObject);
      localEO.y_();
    }
    return localObject;
  }
  
  public static boolean 四難(Context paramContext)
  {
    return paramContext.registerReceiver(null, new IntentFilter(難經本義(206, 論寸口脈平而死者[60] + 1, 九難))).getIntExtra(難經本義(九難 | 0xCB, 論寸口脈平而死者[63], 論寸口脈平而死者[12]), 1) == 2;
  }
  
  public static String 論一脈十變(String paramString)
  {
    Object localObject = KMSApplication.難經本義.getAssets();
    StringBuilder localStringBuilder = new StringBuilder();
    String str2 = null;
    String str1 = null;
    try
    {
      paramString = new BufferedReader(new InputStreamReader(((AssetManager)localObject).open(paramString)));
      for (;;)
      {
        str1 = paramString;
        str2 = paramString;
        localObject = paramString.readLine();
        if (localObject == null) {
          break;
        }
        str1 = paramString;
        str2 = paramString;
        localStringBuilder.append((String)localObject);
      }
    }
    catch (IOException paramString) {}finally
    {
      nv.難經本義(str2);
    }
    return localStringBuilder.toString();
  }
  
  public static boolean 論一脈十變()
  {
    return EV.三難().十二難();
  }
  
  private static boolean 論五臟病之內外證()
  {
    int i = KMSApplication.十二難().十一難().難經本義().難經本義卷上();
    return (i != 0) && (i != 8);
  }
  
  public static boolean 論別知臟腑之病()
  {
    return EV.三難().七難() == xS.難經本義(KMSApplication.十二難());
  }
  
  public static boolean 論別知臟腑之病(String paramString)
  {
    return 僅輸入原文(paramString, 1);
  }
  
  private static Intent 論四時之脈以胃氣為本()
  {
    Intent localIntent = null;
    if (DQ.難經本義())
    {
      Object localObject2 = KMSApplication.十二難();
      Object localObject1 = ((Context)localObject2).getPackageManager();
      localObject2 = Telephony.Sms.getDefaultSmsPackage((Context)localObject2);
      if (!TextUtils.isEmpty((CharSequence)localObject2)) {
        localIntent = ((PackageManager)localObject1).getLaunchIntentForPackage((String)localObject2);
      }
      localObject1 = localIntent;
      if (localIntent == null)
      {
        localObject1 = new Intent(難經本義(九難 | 0x182, 論寸口脈平而死者[60] + 1, -論寸口脈平而死者['ɞ']));
        ((Intent)localObject1).setData(Uri.parse(難經本義(1667, 論寸口脈平而死者[63], 論寸口脈平而死者['¨'])));
      }
      return localObject1;
    }
    localIntent = new Intent(難經本義(九難 | 0x3C9, 論寸口脈平而死者[60] + 1, -論寸口脈平而死者['ɞ']));
    localIntent.addCategory(難經本義(九難 | 0x38B, 論寸口脈平而死者[60] + 1, 論寸口脈平而死者['ٽ']));
    localIntent.setType(難經本義(107, 論寸口脈平而死者['¨'], -論寸口脈平而死者['͝']));
    return localIntent;
  }
  
  public static int 論太過不及等反常脈象(Context paramContext)
  {
    return 難經本義(paramContext.registerReceiver(null, new IntentFilter(難經本義(206, 論寸口脈平而死者[60] + 1, 九難))));
  }
  
  public static String 論太過不及等反常脈象(String paramString)
  {
    Iterator localIterator = SdkUtils.getInstalledApplications(KMSApplication.難經本義, 0).iterator();
    while (localIterator.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
      if (localApplicationInfo.sourceDir.equals(paramString)) {
        return localApplicationInfo.packageName;
      }
    }
    return null;
  }
  
  public static void 論太過不及等反常脈象()
  {
    if (!EV.三難().論寸口脈與經經脈榮衛度數()) {
      return;
    }
    if (!論脈有陰陽之法.compareAndSet(false, true)) {
      return;
    }
    synchronized (五難)
    {
      僅輸入原文 local僅輸入原文 = new 僅輸入原文(null);
      new Timer().schedule(local僅輸入原文, 500L);
    }
    論太過不及等反常脈象.play(四難, 1.0F, 1.0F, 1, 0, 1.3F);
  }
  
  public static String 論實實虛虛之誤()
  {
    return 難經本義(Locale.getDefault());
  }
  
  public static String 論寸口脈平而死者()
  {
    if (mK.難經本義(八難))
    {
      StringBuilder localStringBuilder = new StringBuilder();
      String[] arrayOfString = 八難().split(難經本義(論寸口脈平而死者['ǔ'], 論寸口脈平而死者['͊'], 論寸口脈平而死者[5]));
      if (arrayOfString.length > 2)
      {
        localStringBuilder.append(arrayOfString[0]).append(難經本義(1697, 論寸口脈平而死者['Ȉ'], 論寸口脈平而死者[32])).append(arrayOfString[1]).append(難經本義(1697, 論寸口脈平而死者['Ȉ'], 論寸口脈平而死者[32])).append(arrayOfString[2]);
        八難 = localStringBuilder.toString();
      }
    }
    return 八難;
  }
  
  public static boolean 論寸口脈平而死者(String paramString)
  {
    return (!mK.難經本義(paramString)) && (EV.三難().三難().equals(paramString));
  }
  
  private static Uri 論寸口脈與經經脈榮衛度數(Context paramContext, int paramInt)
  {
    if (!Environment.getExternalStorageState().equals(難經本義(1026, 論寸口脈平而死者[84], 論寸口脈平而死者[63]))) {
      return null;
    }
    return 二難(paramContext, paramInt);
  }
  
  public static CharSequence 論寸口脈與經經脈榮衛度數(String paramString)
  {
    PackageManager localPackageManager = KMSApplication.難經本義.getPackageManager();
    try
    {
      paramString = localPackageManager.getApplicationInfo(paramString, 0);
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      paramString = null;
    }
    if (paramString != null) {
      return localPackageManager.getApplicationLabel(paramString);
    }
    return KMSApplication.難經本義.getString(2131625410);
  }
  
  public static String 論寸口脈與經經脈榮衛度數(Context paramContext)
  {
    String str1 = 難經本義(1289, 論寸口脈平而死者['ӟ'], 論寸口脈平而死者[45]);
    String str2 = ((TelephonyManager)paramContext.getSystemService(難經本義(281, 論寸口脈平而死者[9], 論寸口脈平而死者[13]))).getDeviceId();
    Object localObject;
    if (str2 != null)
    {
      localObject = str2;
      if (!str2.equals(難經本義(1289, 論寸口脈平而死者['ӟ'], 論寸口脈平而死者[45]))) {}
    }
    else
    {
      str2 = Settings.System.getString(paramContext.getContentResolver(), 難經本義(1222, 論寸口脈平而死者[60] + 1, 論寸口脈平而死者[9]));
      if (str2 != null)
      {
        localObject = str2;
        if (!str2.equals(難經本義(1289, 論寸口脈平而死者['ӟ'], 論寸口脈平而死者[45]))) {}
      }
      else
      {
        str2 = Settings.Secure.getString(paramContext.getContentResolver(), 難經本義(1222, 論寸口脈平而死者[60] + 1, 論寸口脈平而死者[9]));
        if (str2 != null)
        {
          localObject = str2;
          if (!str2.equals(難經本義(1289, 論寸口脈平而死者['ӟ'], 論寸口脈平而死者[45]))) {}
        }
        else
        {
          paramContext = ((WifiManager)paramContext.getSystemService(難經本義(963, 論寸口脈平而死者[11], 論寸口脈平而死者['¨']))).getConnectionInfo().getMacAddress();
          localObject = paramContext;
          if (paramContext != null) {
            localObject = paramContext.replaceAll(難經本義(論寸口脈平而死者['ǔ'], 論寸口脈平而死者['͊'], 論寸口脈平而死者[5]), "");
          }
        }
      }
    }
    paramContext = str1;
    if (localObject != null)
    {
      paramContext = str1;
      if (((String)localObject).length() > 0) {
        paramContext = (Context)localObject;
      }
    }
    return paramContext;
  }
  
  public static void 論寸口脈與經經脈榮衛度數()
  {
    DA.難經本義(2);
  }
  
  public static int 論尺寸為脈之大要會()
  {
    return EV.四難().難經本義卷上();
  }
  
  public static String 論尺寸為脈之大要會(String paramString)
  {
    return new File(paramString).getName();
  }
  
  public static boolean 論尺寸為脈之大要會(Context paramContext)
  {
    String str = 七難(paramContext);
    return (str != null) && (難經本義卷上(paramContext, str));
  }
  
  private static void 論王脈(Context paramContext)
  {
    label266:
    for (;;)
    {
      try
      {
        Object localObject1 = new ArrayList();
        Object localObject2 = new Intent(難經本義(913, 論寸口脈平而死者[60] + 1, -論寸口脈平而死者['ɞ']));
        ((Intent)localObject2).setType(難經本義(689, 論寸口脈平而死者['Į'], 論寸口脈平而死者[38]));
        ((Intent)localObject2).putExtra(難經本義(345, 24, 26), Uri.parse(""));
        int i = 論寸口脈平而死者[60] + 1;
        ((Intent)localObject2).putExtra(難經本義(1417, i, i), "");
        paramContext = paramContext.getPackageManager().queryIntentActivities((Intent)localObject2, 0);
        if (paramContext != null)
        {
          paramContext = paramContext.iterator();
          if (paramContext.hasNext())
          {
            localObject2 = ((ResolveInfo)paramContext.next()).activityInfo;
            if (localObject2 == null) {
              continue;
            }
            String str = ((ActivityInfo)localObject2).packageName;
            if (六難.contains(str)) {
              break label266;
            }
            ((List)localObject1).add(str + 難經本義(1667, 論寸口脈平而死者['ʅ'], 論寸口脈平而死者[32]) + ((ActivityInfo)localObject2).name);
            break label266;
          }
        }
        paramContext = new JSONArray((Collection)localObject1);
        localObject1 = EV.論脈之陰陽虛實();
        ((EY)localObject1).難經本義(paramContext);
        ((EY)localObject1).y_();
        return;
      }
      finally {}
    }
  }
  
  public static boolean 論王脈()
  {
    return Environment.isExternalStorageRemovable();
  }
  
  public static boolean 論王脈(String paramString)
  {
    int i;
    try
    {
      i = Integer.valueOf(paramString).intValue();
    }
    catch (NumberFormatException paramString)
    {
      i = 0;
    }
    return i < 0;
  }
  
  private static String 論脈不滿五十動而一止(String paramString)
  {
    if (paramString == null) {
      return "";
    }
    int i = paramString.lastIndexOf(難經本義(1697, 論寸口脈平而死者['Ȉ'], 論寸口脈平而死者[32]));
    if (i == -1) {
      return paramString;
    }
    if (i == paramString.length() - 1) {
      return "";
    }
    return paramString.substring(i);
  }
  
  public static boolean 論脈不滿五十動而一止()
  {
    NetworkInfo localNetworkInfo = ((ConnectivityManager)KMSApplication.難經本義.getSystemService(難經本義(1625, -論寸口脈平而死者['͝'], 論寸口脈平而死者['Ǽ']))).getActiveNetworkInfo();
    return (localNetworkInfo != null) && (localNetworkInfo.isConnected());
  }
  
  public static int 論脈之陰陽虛實()
  {
    long l = System.currentTimeMillis() - EV.僅輸入原文().僅輸入原文();
    if (l < 0L) {
      Log.e(難經本義(347, 九難 | 0xA, 論寸口脈平而死者[11]), 難經本義(840, 論寸口脈平而死者['ڔ'], 論寸口脈平而死者[89]));
    }
    return (int)((1209600000L - l) / 60000L);
  }
  
  private static String 論脈之陰陽虛實(Context paramContext)
  {
    return mJ.僅輸入原文(paramContext, 難經本義(1120, 論寸口脈平而死者['֡'], 論寸口脈平而死者[11]));
  }
  
  public static boolean 論脈之陰陽虛實(String paramString)
  {
    Object localObject = EV.三難().二難();
    if (mK.難經本義((String)localObject)) {
      return false;
    }
    String str = 難經本義(Base64.decode((String)localObject, 0));
    localObject = str;
    if (str.length() > 32) {
      localObject = str.substring(0, 32);
    }
    return paramString.equalsIgnoreCase((String)localObject);
  }
  
  public static long 論脈有輕重()
  {
    return 僅輸入原文(KMSApplication.論脈不滿五十動而一止().getVirusDbInfo());
  }
  
  public static void 論脈有輕重(Context paramContext)
  {
    Object localObject1 = new Intent(paramContext, GplayGraceAlertActionReceiver.class);
    ((Intent)localObject1).setAction(難經本義(1118, -論寸口脈平而死者['͝'], 論寸口脈平而死者['֡']));
    localObject1 = PendingIntent.getBroadcast(paramContext, 0, (Intent)localObject1, 134217728);
    Object localObject2 = new Intent(paramContext, GplayGraceAlertActionReceiver.class);
    ((Intent)localObject2).setAction(難經本義(532, -論寸口脈平而死者['͝'], 論寸口脈平而死者['ڔ']));
    localObject2 = PendingIntent.getBroadcast(paramContext, 0, (Intent)localObject2, 134217728);
    String str1 = paramContext.getString(2131623962);
    String str2 = paramContext.getString(2131624090);
    paramContext = new 皮.難經本義(paramContext);
    paramContext.難經本義(2130837741).難經本義(str1).僅輸入原文(str2).難經本義((PendingIntent)localObject1).僅輸入原文((PendingIntent)localObject2).僅輸入原文(true);
    DA.難經本義(13, paramContext);
  }
  
  public static boolean 論脈有輕重(String paramString)
  {
    String str = getPasswordHash(paramString);
    if (str.length() == 0) {
      return false;
    }
    EN localEN = EV.三難();
    if (localEN == null) {
      return false;
    }
    try
    {
      localEN.僅輸入原文(str);
      localEN.難經本義卷上(paramString);
      localEN.y_();
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
    return true;
  }
  
  public static String 論脈有陰陽之法()
  {
    EN localEN = EV.三難();
    String str = localEN.十三難();
    if (mK.難經本義(str))
    {
      str = mJ.僅輸入原文(KMSApplication.難經本義);
      try
      {
        localEN.論尺寸為脈之大要會(str);
        localEN.y_();
        return str;
      }
      finally
      {
        localObject = finally;
        throw localObject;
      }
    }
    return str;
  }
  
  public static String 論脈有陰陽之法(String paramString)
  {
    PackageInfo localPackageInfo = 四難(paramString);
    if (localPackageInfo != null) {
      return 難經本義(localPackageInfo, paramString);
    }
    return null;
  }
  
  public static void 論脈有陰陽之法(Context paramContext)
  {
    Object localObject1 = new Intent(paramContext, LicenseInfoActivity.class);
    ((Intent)localObject1).putExtra(難經本義(134, 論寸口脈平而死者[43], 論寸口脈平而死者['״']), true);
    Object localObject2 = new Intent(paramContext, KisMainActivity.class);
    ((Intent)localObject2).addFlags(268435456);
    localObject1 = PendingIntent.getActivities(paramContext, 0, new Intent[] { localObject2, localObject1 }, 0);
    localObject2 = paramContext.getString(2131623962);
    String str = paramContext.getString(2131624292);
    paramContext = new 皮.難經本義(paramContext);
    paramContext.難經本義(2130837718).難經本義卷上((CharSequence)localObject2).難經本義(0L).難經本義((CharSequence)localObject2).僅輸入原文(str).難經本義((PendingIntent)localObject1).僅輸入原文(true);
    DA.難經本義(10, paramContext);
  }
  
  public static boolean 論色脈皮膚之相應()
  {
    KMSApplication localKMSApplication = KMSApplication.十二難();
    String str = mI.難經本義(localKMSApplication);
    return (str != null) && (str.equals(localKMSApplication.getPackageName()));
  }
  
  public static int 難經本義(Activity paramActivity)
  {
    paramActivity = paramActivity.getWindowManager().getDefaultDisplay();
    if (paramActivity.getWidth() == paramActivity.getHeight()) {
      return 3;
    }
    if (paramActivity.getWidth() < paramActivity.getHeight()) {
      return 1;
    }
    return 2;
  }
  
  public static int 難經本義(Intent paramIntent)
  {
    int i = paramIntent.getIntExtra(難經本義(九難 | 0x499, 論寸口脈平而死者[43], 論寸口脈平而死者[13]), -1);
    int j = paramIntent.getIntExtra(難經本義(九難 | 0x3D8, 論寸口脈平而死者[63], 論寸口脈平而死者[13]), -1);
    return (int)(i / j * 100.0F);
  }
  
  public static int 難經本義(String paramString, Context paramContext)
  {
    String str = paramContext.getPackageName();
    return paramContext.getResources().getIdentifier(str + 難經本義(786, 論寸口脈平而死者[120], 論寸口脈平而死者[13]) + paramString, null, null);
  }
  
  public static int 難經本義(List<Locale> paramList, String paramString)
  {
    int i = 0;
    while (i < paramList.size())
    {
      if (((Locale)paramList.get(i)).getCountry().equals(paramString)) {
        return i;
      }
      i += 1;
    }
    return -1;
  }
  
  private static long 難經本義(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
  {
    return new GregorianCalendar(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6).getTimeInMillis();
  }
  
  private static Intent 難經本義(Context paramContext, Uri paramUri, String paramString)
  {
    paramContext = paramContext.getString(2131624133);
    Object localObject = 難經本義(paramUri, paramString);
    if (!((List)localObject).isEmpty())
    {
      paramUri = (Intent)((List)localObject).remove(((List)localObject).size() - 1);
      if (mK.難經本義(paramContext)) {
        paramContext = null;
      }
      paramContext = Intent.createChooser(paramUri, paramContext);
      if (!((List)localObject).isEmpty()) {
        paramContext.putExtra(難經本義(九難 | 0x213, 論寸口脈平而死者[60] + 1, -論寸口脈平而死者['ǅ']), (Parcelable[])((List)localObject).toArray(new Parcelable[0]));
      }
      return paramContext;
    }
    localObject = new Intent(難經本義(913, 論寸口脈平而死者[60] + 1, -論寸口脈平而死者['ɞ']));
    ((Intent)localObject).setType(難經本義(689, 論寸口脈平而死者['Į'], 論寸口脈平而死者[38]));
    ((Intent)localObject).putExtra(難經本義(345, 24, 26), paramUri);
    int i = 論寸口脈平而死者[60] + 1;
    ((Intent)localObject).putExtra(難經本義(1417, i, i), paramString);
    if (mK.難經本義(paramContext)) {
      paramContext = null;
    }
    return Intent.createChooser((Intent)localObject, paramContext);
  }
  
  public static String 難經本義(int paramInt)
  {
    Random localRandom = new Random(System.currentTimeMillis());
    StringBuilder localStringBuilder = new StringBuilder(paramInt);
    int i = 0;
    while (i < paramInt)
    {
      int j = localRandom.nextInt(16);
      localStringBuilder.append(String.format(難經本義(九難 | 0x58B, -論寸口脈平而死者['ä'], 論寸口脈平而死者[5]), new Object[] { Integer.valueOf(j) }));
      i += 1;
    }
    return localStringBuilder.toString();
  }
  
  private static String 難經本義(int paramInt1, int paramInt2, int paramInt3)
  {
    int j = paramInt3 + 1;
    byte[] arrayOfByte1 = 論寸口脈平而死者;
    int i = 0;
    int k = 0;
    paramInt3 = 121 - paramInt2;
    paramInt2 = 1701 - paramInt1;
    byte[] arrayOfByte2 = new byte[j];
    j -= 1;
    paramInt1 = paramInt2;
    if (arrayOfByte1 == null)
    {
      paramInt3 = j;
      i = paramInt2;
      paramInt1 = paramInt2;
      paramInt2 = k;
    }
    for (;;)
    {
      paramInt3 = paramInt3 + -i - 1;
      paramInt1 += 1;
      i = paramInt2;
      arrayOfByte2[i] = ((byte)paramInt3);
      paramInt2 = i + 1;
      if (i == j) {
        return new String(arrayOfByte2, 0).intern();
      }
      i = arrayOfByte1[paramInt1];
    }
  }
  
  public static String 難經本義(int paramInt, Context paramContext)
  {
    try
    {
      paramContext = paramContext.getResources().openRawResource(paramInt);
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
      byte[] arrayOfByte = new byte['ࠀ'];
      for (;;)
      {
        paramInt = paramContext.read(arrayOfByte);
        if (paramInt == -1) {
          break;
        }
        localByteArrayOutputStream.write(arrayOfByte, 0, paramInt);
      }
      localByteArrayOutputStream.close();
      paramContext.close();
      paramContext = localByteArrayOutputStream.toString();
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  public static String 難經本義(long paramLong)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramLong / 3600000L % 60L);
    localStringBuilder.append(難經本義(1667, 論寸口脈平而死者[120], 論寸口脈平而死者[32]));
    int i = (int)(paramLong / 60000L) % 60;
    if (i < 10) {
      localStringBuilder.append(難經本義(1683, 論寸口脈平而死者['ӟ'], 論寸口脈平而死者[32]));
    }
    localStringBuilder.append(i);
    localStringBuilder.append(難經本義(1667, 論寸口脈平而死者[120], 論寸口脈平而死者[32]));
    i = (int)(paramLong / 1000L % 60L);
    if (i < 10) {
      localStringBuilder.append(難經本義(1683, 論寸口脈平而死者['ӟ'], 論寸口脈平而死者[32]));
    }
    localStringBuilder.append(i);
    return localStringBuilder.toString();
  }
  
  public static String 難經本義(PackageInfo paramPackageInfo, String paramString)
  {
    paramPackageInfo.applicationInfo.sourceDir = paramString;
    paramPackageInfo.applicationInfo.publicSourceDir = paramString;
    paramString = KMSApplication.難經本義.getPackageManager();
    return (String)paramPackageInfo.applicationInfo.loadLabel(paramString);
  }
  
  public static String 難經本義(Locale paramLocale)
  {
    paramLocale = Locale.getDefault();
    return paramLocale.getLanguage() + 難經本義(1697, 論寸口脈平而死者['͖'] - 1, 論寸口脈平而死者[32]) + paramLocale.getCountry();
  }
  
  public static String 難經本義(byte[] paramArrayOfByte)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int j = paramArrayOfByte.length;
    int i = 0;
    while (i < j)
    {
      byte b = paramArrayOfByte[i];
      localStringBuilder.append(String.format(難經本義(641, -論寸口脈平而死者['ä'], 論寸口脈平而死者['¨']), new Object[] { Byte.valueOf(b) }));
      i += 1;
    }
    return localStringBuilder.toString();
  }
  
  private static String 難經本義(byte[] paramArrayOfByte, String paramString)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    try
    {
      paramString = MessageDigest.getInstance(paramString);
      localObject1 = localObject2;
      paramString.update(paramArrayOfByte);
      localObject1 = localObject2;
      paramArrayOfByte = paramString.digest();
      localObject1 = localObject2;
      paramString = new Formatter();
      localObject1 = localObject2;
      int j = paramArrayOfByte.length;
      int i = 0;
      while (i < j)
      {
        byte b = paramArrayOfByte[i];
        localObject1 = localObject2;
        paramString.format(難經本義(719, -論寸口脈平而死者['ä'], 論寸口脈平而死者['¨']), new Object[] { Byte.valueOf(b) });
        i += 1;
      }
      localObject1 = localObject2;
      paramArrayOfByte = paramString.toString();
      localObject1 = paramArrayOfByte;
      paramString.close();
      return paramArrayOfByte;
    }
    catch (Exception paramArrayOfByte)
    {
      paramArrayOfByte.printStackTrace();
    }
    return localObject1;
  }
  
  public static StringBuffer 難經本義(String paramString, Set<String> paramSet)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    paramSet = paramSet.iterator();
    while (paramSet.hasNext())
    {
      localStringBuffer.append(paramString);
      localStringBuffer.append(難經本義(九難 | 0x602, 論寸口脈平而死者[69] + 1, 論寸口脈平而死者[63]));
      String str = (String)paramSet.next();
      if (難經本義(str)) {
        localStringBuffer.append(難經本義卷上(str));
      } else {
        localStringBuffer.append(str);
      }
      localStringBuffer.append(難經本義(1697, 論寸口脈平而死者['ͭ'], 論寸口脈平而死者[32]));
      if (paramSet.hasNext()) {
        localStringBuffer.append(難經本義(1427, 論寸口脈平而死者[69] + 1, 論寸口脈平而死者['¨']));
      }
    }
    return localStringBuffer;
  }
  
  public static List<ResolveInfo> 難經本義(PackageManager paramPackageManager)
  {
    Intent localIntent = new Intent(難經本義(九難 | 0x182, 論寸口脈平而死者[60] + 1, -論寸口脈平而死者['ɞ']), Uri.parse(難經本義(577, 論寸口脈平而死者['ǔ'], 論寸口脈平而死者[52])));
    localIntent.addCategory(難經本義(782, 論寸口脈平而死者[60] + 1, 論寸口脈平而死者[56]));
    int i;
    if (DQ.僅輸入原文()) {
      i = 131072;
    } else {
      i = 0;
    }
    return paramPackageManager.queryIntentActivities(localIntent, i);
  }
  
  private static List<Intent> 難經本義(Uri paramUri, String paramString)
  {
    try
    {
      Object localObject = EV.論脈之陰陽虛實();
      ArrayList localArrayList = new ArrayList();
      try
      {
        localObject = ((EY)localObject).一難();
        int i = 0;
        while (i < ((JSONArray)localObject).length())
        {
          String[] arrayOfString = ((JSONArray)localObject).getString(i).split(難經本義(1667, 論寸口脈平而死者['ʅ'], 論寸口脈平而死者[32]));
          if (arrayOfString.length == 2)
          {
            Intent localIntent = new Intent(難經本義(913, 論寸口脈平而死者[60] + 1, -論寸口脈平而死者['ɞ']));
            localIntent.setType(難經本義(689, 論寸口脈平而死者['Į'], 論寸口脈平而死者[38]));
            localIntent.putExtra(難經本義(345, 24, 26), paramUri);
            int j = 論寸口脈平而死者[60] + 1;
            localIntent.putExtra(難經本義(1417, j, j), paramString);
            localIntent.setPackage(arrayOfString[0]);
            localIntent.setClassName(arrayOfString[0], arrayOfString[1]);
            localArrayList.add(localIntent);
          }
          i += 1;
        }
      }
      catch (JSONException paramUri) {}
      return localArrayList;
    }
    finally {}
  }
  
  public static List<Locale> 難經本義(List<String> paramList)
  {
    ArrayList localArrayList = new ArrayList();
    String[] arrayOfString = Locale.getISOCountries();
    int j = arrayOfString.length;
    Locale[] arrayOfLocale = new Locale[j];
    int i = 0;
    while (i < j)
    {
      arrayOfLocale[i] = new Locale("", arrayOfString[i]);
      i += 1;
    }
    Arrays.sort(arrayOfLocale, new DN());
    j = arrayOfLocale.length;
    i = 0;
    while (i < j)
    {
      arrayOfString = arrayOfLocale[i];
      String str = arrayOfString.getDisplayCountry();
      if ((str.trim().length() > 0) && (!paramList.contains(str)))
      {
        localArrayList.add(arrayOfString);
        paramList.add(str);
      }
      i += 1;
    }
    return localArrayList;
  }
  
  public static <T> List<T> 難經本義(List<T> paramList1, List<T> paramList2)
  {
    ArrayList localArrayList = new ArrayList(paramList1.size() + paramList2.size());
    localArrayList.addAll(paramList1);
    localArrayList.addAll(paramList2);
    return localArrayList;
  }
  
  public static Set<String> 難經本義(Set<String> paramSet)
  {
    HashSet localHashSet = new HashSet();
    paramSet = paramSet.iterator();
    while (paramSet.hasNext())
    {
      String str = (String)paramSet.next();
      if (難經本義(str)) {
        localHashSet.add(一難(str));
      } else {
        localHashSet.add(str);
      }
    }
    return localHashSet;
  }
  
  public static void 難經本義() {}
  
  public static void 難經本義(Activity paramActivity, int paramInt)
  {
    難經本義(paramActivity, paramInt, null);
  }
  
  public static void 難經本義(Activity paramActivity, int paramInt, String paramString)
  {
    String str = "";
    switch (paramInt)
    {
    default: 
      break;
    case 1: 
      paramString = paramActivity.getString(2131624701);
      str = paramActivity.getString(2131624699);
      break;
    case 0: 
      paramString = paramActivity.getString(2131624706);
      str = paramActivity.getString(2131624704);
      break;
    case 2: 
      paramString = paramActivity.getString(2131624709);
      str = paramActivity.getString(2131624707);
      break;
    case 4: 
      break;
    case 5: 
      paramString = paramActivity.getString(2131624879);
      break;
    case 3: 
      paramString = paramActivity.getString(2131624703);
      str = paramActivity.getString(2131624702);
      break;
    }
    paramString = paramActivity.getString(2131624136);
    str = paramActivity.getString(2131624135);
    paramString = paramActivity.getString(2131624134, new Object[] { paramString, str, paramActivity.getString(2131624137) });
    paramActivity.startActivity(難經本義(paramActivity, 論寸口脈與經經脈榮衛度數(paramActivity, 2130837947), paramString));
  }
  
  public static void 難經本義(Activity paramActivity, int paramInt, boolean paramBoolean)
  {
    if (EV.論脈之陰陽虛實().難經本義卷上()) {
      paramActivity.startActivity(ShareItActivity.難經本義(paramActivity, paramInt, paramBoolean));
    }
  }
  
  public static void 難經本義(Activity paramActivity, Class<? extends Activity> paramClass, Bundle paramBundle)
  {
    paramClass = new Intent(paramActivity, paramClass);
    if (paramBundle != null) {
      paramClass.putExtras(paramBundle);
    }
    paramClass.addFlags(8388608);
    paramClass.addFlags(67108864);
    paramClass.addFlags(65536);
    paramActivity.startActivity(paramClass);
  }
  
  public static void 難經本義(Activity paramActivity, boolean paramBoolean)
  {
    Intent localIntent = new Intent(難經本義(九難 | 0x3C9, 論寸口脈平而死者[60] + 1, -論寸口脈平而死者['ɞ']));
    localIntent.addCategory(難經本義(716, 論寸口脈平而死者[60] + 1, 論寸口脈平而死者['״']));
    Object localObject = paramActivity.getPackageManager().queryIntentActivities(localIntent, 0);
    if ((((List)localObject).size() == 1) || (一難(paramActivity) != null))
    {
      paramActivity.startActivity(localIntent);
      if (paramBoolean) {
        paramActivity.finish();
      }
      return;
    }
    if (((List)localObject).size() > 0)
    {
      localObject = (ResolveInfo)((List)localObject).get(0);
      localIntent.setClassName(((ResolveInfo)localObject).activityInfo.packageName, ((ResolveInfo)localObject).activityInfo.name);
      if (paramBoolean) {
        paramActivity.finish();
      }
      paramActivity.startActivity(localIntent);
    }
  }
  
  public static void 難經本義(ActivityManager paramActivityManager, String paramString)
  {
    paramActivityManager.restartPackage(paramString);
  }
  
  public static void 難經本義(Context paramContext)
  {
    if (Build.VERSION.SDK_INT < 19)
    {
      paramContext = (ActivityManager)paramContext.getSystemService(難經本義(1424, 論寸口脈平而死者[60] + 1, 論寸口脈平而死者[50]));
      int i = -論寸口脈平而死者['͝'];
      難經本義(paramContext, 難經本義(1156, i, i));
    }
  }
  
  public static void 難經本義(Context paramContext, int paramInt)
  {
    String str = String.format(paramContext.getString(2131625063), new Object[] { Integer.valueOf(paramInt) });
    Intent localIntent = new Intent();
    localIntent.setAction(難經本義(九難 | 0x182, 論寸口脈平而死者[60] + 1, -論寸口脈平而死者['ɞ']));
    localIntent.setType(難經本義(九難 | 0x58A, 論寸口脈平而死者['¨'], 論寸口脈平而死者['״']));
    難經本義(paramContext, str, 2131625033, 2130837734, 5, localIntent);
  }
  
  public static void 難經本義(Context paramContext, Uri paramUri)
  {
    Intent localIntent = new Intent();
    localIntent.setAction(難經本義(九難 | 0x182, 論寸口脈平而死者[60] + 1, -論寸口脈平而死者['ɞ']));
    localIntent.addFlags(268435456);
    localIntent.addFlags(67108864);
    localIntent.addFlags(8388608);
    if (paramUri != null) {
      localIntent.setDataAndType(paramUri, 難經本義(九難 | 0xD3, 論寸口脈平而死者[12], 論寸口脈平而死者[38]));
    } else {
      localIntent.setType(難經本義(九難 | 0xD3, 論寸口脈平而死者[12], 論寸口脈平而死者[38]));
    }
    paramUri = BrowsersIndexInfo.難經本義(paramContext);
    if (paramUri.難經本義())
    {
      paramUri = paramUri.難經本義卷上.難經本義.activityInfo;
      if (難經本義(paramContext, localIntent, paramUri.packageName, paramUri.name)) {
        return;
      }
    }
    paramUri = ((List)WebFilterSupportedBrowsers.getSupportedBrowsers().get(WebFilterSupportedBrowsers.WebFilterMode.Bookmarks)).iterator();
    while (paramUri.hasNext()) {
      if (難經本義(paramContext, localIntent, ((SupportedBrowserInfo)paramUri.next()).getPackageName(), 難經本義(1275, -論寸口脈平而死者['͝'], -論寸口脈平而死者['͋']))) {
        return;
      }
    }
    if ((Build.VERSION.SDK_INT >= 17) && (難經本義(paramContext, localIntent, 難經本義(960, -論寸口脈平而死者['͝'], 論寸口脈平而死者['ǔ']), 難經本義(1336, -論寸口脈平而死者['͝'], -論寸口脈平而死者['͋'])))) {
      return;
    }
    try
    {
      localIntent.setComponent(null);
      paramContext.startActivity(localIntent);
      return;
    }
    catch (Exception paramContext) {}
  }
  
  public static void 難經本義(Context paramContext, Class<?> paramClass, int paramInt, String paramString)
  {
    paramClass = PendingIntent.getActivity(paramContext, 0, new Intent(paramContext, paramClass), 0);
    皮.難經本義 local難經本義 = new 皮.難經本義(paramContext);
    local難經本義.難經本義(2130837718).難經本義卷上(paramString).難經本義(0L).難經本義(paramContext.getString(2131623963)).僅輸入原文(paramString).難經本義(paramClass).僅輸入原文(true);
    DA.難經本義(paramInt, local難經本義);
    try
    {
      Thread.sleep(1000L);
    }
    catch (InterruptedException paramContext)
    {
      paramContext.printStackTrace();
    }
    DA.難經本義(paramInt);
  }
  
  public static void 難經本義(Context paramContext, String paramString)
  {
    int i = 論寸口脈平而死者[32];
    tD.僅輸入原文(難經本義(i, i | 0x2E, 論寸口脈平而死者['Ǽ']));
    tD.難經本義(八難());
    File localFile = Ae.難經本義(paramContext);
    String str = 論實實虛虛之誤();
    kk localKk = wY.論脈之陰陽虛實();
    UcpServiceId localUcpServiceId = UcpServiceId.KasperskyMobileSecurity;
    if (xr.難經本義()) {
      localObject = UcpDeviceType.Tablet;
    } else {
      localObject = UcpDeviceType.Mobile;
    }
    Object localObject = new GK(str, localKk, localUcpServiceId, (UcpDeviceType)localObject, UcpGeneralClient.難經本義().getAbsolutePath());
    tF.難經本義().難經本義(new ExtraLocator((GK)localObject));
    try
    {
      KavSdk.initSafe(paramContext, localFile, new xb(paramContext, 4), 2131099657, paramString);
    }
    catch (RuntimeException paramContext)
    {
      paramContext.printStackTrace();
      throw paramContext;
    }
    initNative();
    二難 = DH.難經本義();
    論太過不及等反常脈象 = new SoundPool(20, 2, 0);
    四難 = 論太過不及等反常脈象.load(paramContext, 2131099674, 1);
    論寸口脈與經經脈榮衛度數 = Uri.parse(難經本義(論寸口脈平而死者['Į'], -論寸口脈平而死者['͝'], 論寸口脈平而死者['Į']));
  }
  
  private static void 難經本義(Context paramContext, String paramString, int paramInt1, int paramInt2, int paramInt3, Intent paramIntent)
  {
    paramIntent = PendingIntent.getActivity(paramContext, 0, paramIntent, 0);
    String str = paramContext.getString(paramInt1);
    paramContext = new 皮.難經本義(paramContext);
    paramContext.難經本義(paramInt2).難經本義卷上(str).難經本義(0L).難經本義(paramString).僅輸入原文(str).難經本義(paramIntent).僅輸入原文(true);
    DA.難經本義(paramInt3, paramContext);
  }
  
  public static void 難經本義(Context paramContext, String paramString1, String paramString2, long paramLong, ThreatType paramThreatType)
  {
    if ((paramLong > 0L) && (!論脈有輕重.compareAndSet(false, true))) {
      return;
    }
    if (paramString2 != null)
    {
      switch (1.難經本義[paramThreatType.ordinal()])
      {
      default: 
        break;
      case 1: 
        i = 2131624605;
        break;
      case 2: 
        i = 2131624604;
        break;
      }
      int i = 2131624603;
      paramString1 = String.format(paramContext.getString(i), new Object[] { paramString2, paramString1 });
    }
    else
    {
      paramString1 = String.format(paramContext.getString(2131624606), new Object[] { paramString1 });
    }
    paramString2 = PendingIntent.getActivity(paramContext, 0, new Intent(paramContext, KisMainActivity.class), 0);
    paramThreatType = new 皮.難經本義(paramContext);
    paramThreatType.難經本義(2130837718).難經本義卷上(paramString1).難經本義(0L).難經本義(paramContext.getString(2131623963)).僅輸入原文(paramString1).難經本義(paramString2);
    DA.難經本義(2, paramThreatType);
    if (paramLong > 0L)
    {
      paramContext = new 難經本義(paramContext);
      new Timer().schedule(paramContext, paramLong);
    }
  }
  
  public static void 難經本義(Context paramContext, boolean paramBoolean)
  {
    new Thread(new DO(paramContext, paramBoolean)).start();
  }
  
  public static void 難經本義(DataOutput paramDataOutput, String paramString)
  {
    if (paramString == null)
    {
      paramDataOutput.writeByte(0);
      return;
    }
    paramDataOutput.writeByte(1);
    paramDataOutput.writeUTF(paramString);
  }
  
  public static void 難經本義(DataOutput paramDataOutput, byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null)
    {
      paramDataOutput.writeInt(0);
      return;
    }
    paramDataOutput.writeInt(paramArrayOfByte.length);
    paramDataOutput.write(paramArrayOfByte);
  }
  
  public static void 難經本義(String paramString1, String paramString2, DG paramDG)
  {
    try
    {
      二難.難經本義(paramString1, paramString2, paramDG);
      return;
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
  }
  
  public static void 難經本義(String paramString1, String paramString2, boolean paramBoolean)
  {
    if (mK.難經本義(paramString1)) {
      return;
    }
    DF localDF = null;
    if (paramBoolean) {
      localDF = new DF(paramString1, paramString2);
    }
    try
    {
      二難.難經本義(paramString1, paramString2, localDF);
      return;
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
  }
  
  public static void 難經本義(byte[] paramArrayOfByte, boolean paramBoolean)
  {
    int m = paramArrayOfByte.length;
    int j = 0;
    int i = 0;
    while (i < m)
    {
      int k = j;
      if (paramBoolean)
      {
        paramArrayOfByte[i] = ((byte)((byte)(paramArrayOfByte[i] + (byte)(256 - j * 5 - 7)) % 256));
        k = paramArrayOfByte[i];
      }
      i += 1;
      j = k;
    }
  }
  
  public static boolean 難經本義(BroadcastReceiver paramBroadcastReceiver)
  {
    return paramBroadcastReceiver.isOrderedBroadcast();
  }
  
  public static boolean 難經本義(Context paramContext, Intent paramIntent)
  {
    return paramContext.getPackageManager().queryIntentActivities(paramIntent, 65536).size() > 0;
  }
  
  private static boolean 難經本義(Context paramContext, Intent paramIntent, String paramString1, String paramString2)
  {
    paramIntent.setClassName(paramString1, paramString2);
    try
    {
      paramContext.startActivity(paramIntent);
    }
    catch (Exception paramContext)
    {
      return false;
    }
    return true;
  }
  
  public static boolean 難經本義(VirusDbInfo paramVirusDbInfo)
  {
    return paramVirusDbInfo.mRecords > 0;
  }
  
  public static boolean 難經本義(Closeable paramCloseable)
  {
    if (paramCloseable != null) {}
    try
    {
      paramCloseable.close();
    }
    catch (IOException paramCloseable)
    {
      return false;
    }
    return true;
  }
  
  @Deprecated
  public static boolean 難經本義(InputStream paramInputStream)
  {
    return 難經本義(paramInputStream);
  }
  
  @Deprecated
  public static boolean 難經本義(OutputStream paramOutputStream)
  {
    return 難經本義(paramOutputStream);
  }
  
  public static boolean 難經本義(String paramString)
  {
    paramString = PhoneNumberUtils.stripSeparators(paramString);
    return (paramString != null) && (paramString.length() > 0);
  }
  
  public static byte[] 難經本義(DataInputStream paramDataInputStream)
  {
    int i = paramDataInputStream.readInt();
    if (i == 0) {
      return null;
    }
    byte[] arrayOfByte = new byte[i];
    paramDataInputStream.read(arrayOfByte);
    return arrayOfByte;
  }
  
  public static int[] 難經本義(String paramString, int paramInt)
  {
    int[] arrayOfInt = new int[paramInt];
    if (mK.難經本義(paramString)) {
      return arrayOfInt;
    }
    paramString = paramString.split(難經本義(論寸口脈平而死者['ǔ'], 論寸口脈平而死者['͊'], 論寸口脈平而死者[5]));
    int i = Math.min(paramString.length, paramInt);
    paramInt = 0;
    while (paramInt < i)
    {
      try
      {
        arrayOfInt[paramInt] = Integer.parseInt(paramString[paramInt]);
      }
      catch (NumberFormatException localNumberFormatException) {}
      paramInt += 1;
    }
    return arrayOfInt;
  }
  
  public static String 難經本義卷上(String paramString)
  {
    paramString = new StringBuilder(paramString);
    paramString.insert(0, 難經本義(1697, -論寸口脈平而死者['ä'], 論寸口脈平而死者[32]));
    if (paramString.length() == 11)
    {
      paramString.insert(4, 難經本義(1697, -論寸口脈平而死者['ä'], 論寸口脈平而死者[32]));
      paramString.insert(8, 難經本義(1697, -論寸口脈平而死者['ä'], 論寸口脈平而死者[32]));
      paramString.insert(11, 難經本義(1697, -論寸口脈平而死者['ä'], 論寸口脈平而死者[32]));
    }
    return paramString.toString();
  }
  
  public static void 難經本義卷上()
  {
    DA.難經本義(6);
    DP.難經本義((ActivityManager)KMSApplication.難經本義.getSystemService(難經本義(1424, 論寸口脈平而死者[60] + 1, 論寸口脈平而死者[50])), 難經本義(1697, -論寸口脈平而死者['͝'], 論寸口脈平而死者[45]));
  }
  
  public static void 難經本義卷上(Activity paramActivity)
  {
    難經本義(paramActivity, -1);
  }
  
  public static void 難經本義卷上(Context paramContext)
  {
    PendingIntent localPendingIntent = PendingIntent.getActivity(paramContext, 0, new Intent(paramContext, LicenseBlockedActivity.class), 0);
    String str1 = paramContext.getString(2131623963);
    String str2 = paramContext.getString(2131624888);
    paramContext = new 皮.難經本義(paramContext);
    paramContext.難經本義(2130837741).難經本義(str1).僅輸入原文(str2).難經本義(localPendingIntent).僅輸入原文(false);
    DA.難經本義(9, paramContext);
  }
  
  public static void 難經本義卷上(Context paramContext, int paramInt)
  {
    Object localObject = new Intent(paramContext, KisMainActivity.class);
    ((Intent)localObject).addFlags(268435456);
    PendingIntent localPendingIntent = PendingIntent.getActivity(paramContext, 0, (Intent)localObject, 0);
    localObject = paramContext.getString(2131624267);
    皮.難經本義 local難經本義 = new 皮.難經本義(paramContext);
    local難經本義.難經本義((CharSequence)localObject).難經本義(2130837717).難經本義(localPendingIntent).僅輸入原文(true);
    switch (paramInt)
    {
    default: 
      break;
    case 5: 
      i = 2131624267;
      break;
    case 57005: 
      i = 2131624269;
      break;
    case 622: 
    case 624: 
      i = 2131624277;
      break;
    case 627: 
    case 634: 
      i = 2131624275;
      break;
    case 601: 
      i = 2131624268;
      break;
    case 629: 
      i = 2131624294;
      break;
    case 636: 
      i = 2131624293;
      break;
    case 3: 
      i = 2131624279;
      break;
    case 602: 
      i = 2131624285;
      break;
    case 603: 
      i = 2131624273;
      break;
    case 1007: 
      i = 2131624270;
      break;
    case 637: 
      i = 2131624271;
      break;
    case 638: 
      i = 2131624272;
      break;
    }
    int i = 2131624267;
    paramContext = String.format(paramContext.getString(i) + 難經本義(723, 論寸口脈平而死者[69] + 1, 論寸口脈平而死者[13]), new Object[] { Integer.valueOf(paramInt) });
    local難經本義.僅輸入原文(paramContext);
    local難經本義.難經本義(new 論人面獨能耐寒.難經本義卷上().僅輸入原文(paramContext).難經本義((CharSequence)localObject));
    DA.難經本義(10, local難經本義);
  }
  
  private static boolean 難經本義卷上(Context paramContext, String paramString)
  {
    if (paramString == null) {
      throw new IllegalArgumentException(難經本義(1489, 論寸口脈平而死者[89], -論寸口脈平而死者['͋']));
    }
    try
    {
      paramContext = ((DevicePolicyManager)paramContext.getSystemService(難經本義(798, 論寸口脈平而死者['Ҋ'], 論寸口脈平而死者[84]))).getActiveAdmins();
      if (paramContext != null)
      {
        paramContext = paramContext.iterator();
        while (paramContext.hasNext())
        {
          boolean bool = ((ComponentName)paramContext.next()).getPackageName().equals(paramString);
          if (bool) {
            return true;
          }
        }
      }
    }
    catch (Exception paramContext)
    {
      return true;
    }
    return false;
  }
  
  static class 僅輸入原文
    extends TimerTask
  {
    private 僅輸入原文() {}
    
    public void run()
    {
      Utils.論損至脈之病證及其治法().getAndSet(false);
    }
  }
  
  static class 難經本義
    extends TimerTask
  {
    private Context 難經本義;
    
    public 難經本義(Context paramContext)
    {
      this.難經本義 = paramContext;
    }
    
    public void run()
    {
      Utils.論寸口脈與經經脈榮衛度數();
      Utils.十五難().getAndSet(false);
    }
  }
}
