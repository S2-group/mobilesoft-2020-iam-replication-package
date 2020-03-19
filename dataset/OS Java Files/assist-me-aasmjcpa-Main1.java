package com.pin.assistme;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.bluetooth.BluetoothAdapter;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Messenger;
import android.os.PowerManager;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.provider.AlarmClock;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.provider.Telephony;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.NotificationCompat;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ResideMenu.ResideMenu;
import com.ResideMenu.ResideMenuItem;
import com.assist.me.R;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.initializations.initMain;
import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;
import com.nineoldandroids.animation.Animator;
import com.parse.Parse;
import com.parse.ParseObject;
import com.preferences.preferencesMenu;
import com.wolfram.alpha.WAEngine;
import com.wolfram.alpha.WAException;
import com.wolfram.alpha.WAPlainText;
import com.wolfram.alpha.WAPod;
import com.wolfram.alpha.WAQuery;
import com.wolfram.alpha.WAQueryResult;
import com.wolfram.alpha.WASubpod;
import com.wolfram.alpha.impl.WAPlainTextImpl;

import org.json.JSONException;
import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

import edu.cmu.pocketsphinx.Assets;
import edu.cmu.pocketsphinx.Hypothesis;

import static edu.cmu.pocketsphinx.SpeechRecognizerSetup.defaultSetup;

/**
 * created by tomer rosenfeld.
 */

/**
 * Created by tomer on 17/05/14.
 */

public class Main1 extends Activity
        implements TextToSpeech.OnInitListener,
        edu.cmu.pocketsphinx.RecognitionListener{
    boolean Animateing = false;
    ImageView btnSpeackBack;
    private GestureDetector gestureDetector;
    View.OnTouchListener gestureListener;
    //Strings for translattion
    String FOR_WEATHER = "weather";
    String FOR_WOLFRAM = "wolfram";
    String FOR_FUN = "fun";
    ResideMenu resideMenu;
    //wolfram alpha!
    private static String appid = "6J9QXJ-GJ7U63J229";
    WAQueryResult queryResult;
    String WolframInput;
    ProgressDialog pd;
    //classes object shit
    phrases_lists phrases = new phrases_lists();
    //others...
    public static String MASTER_NAME = "pin nigga master";
    public static int originalMaxSize;
    protected PowerManager.WakeLock mWakeLock;
    int sdk = Build.VERSION.SDK_INT;
    Vibrator mVibrator;
    TextToSpeech mTts;
    final int MY_DATA_CHECK_CODE = 1234;
    private TextToSpeech tts;

    LocationListener[] locationListener = {null};
    LocationManager locationMangaer = null;
    public static long time_timer;
    //Navdrawer
    private ListView mDrawerList;
    private CharSequence mDrawerTitle;
    String cityName = null;
    BitmapDrawable shapeDrawable;
    List<DrawerItem> dataList;

    String message;
    public String translatedText = "";
    ImageView hand1;
    //booleans
    boolean donotshowagain, onstart = true;
    boolean weatherlookerrunning = false;
    boolean speakback, animations = true;
    public boolean isInitializing = false;
    boolean wake = false;
    boolean bitchstring, answer_in_new_activity, listen_on_start, siri_bool, listening = false;

    private Timer myTimer;

    // Strings
    static final String ANSWER = "answer";
    static final String QUESTION = "qusetion";
    static final String WHAT_TO_REMIND = "what_to_remind";
    static final String WHEN_TO_REMIND = "when_to_remind";

    String time_to_remind, what_to_remind;
    String spoke, com, com2, com3, com4, contact, mobilenum, number, homenum, worknum, othernum;
    String comingfromwidget = "no";
    String themetype = "normal";
    String theRest = null;
    String PackageName;
    String appname;
    String negativebutton = "No";
    String whatactivityresult;
    String imagebutton = "speak";
    String accent = "US";
    String backup_user_name;
    String whatAfterResult = "normal";
    //names
    String com_name = "Assistant";
    public static String user_name = "Stranger";
    String text;
    String tortanslate;
    String animation_selected = "normal";
    String color = "#ffe6e6e6";
    String phoneNumber;

    //TextViews & EditTexts
    TextView info_one;
    EditText edit;
    TextView partialText;

    CheckBox check;

    //ImageButton & Buttons
    ImageButton btnSpeak;
    ImageView imgview;

    //ints
    private static final int REQUEST_CODE = 1;
    int month, dayOfMonth;
    int textsizedp, comingfromfeatures, numbers_of_apps_found,pitch;
    boolean mobileavi, homeavi, workavi, otheravi, sent, initialized, initialized2,initialized3 = false;

    SharedPreferences sharedPreferences;
    SharedPreferences sharedPrefs;


    private int mBindFlag;
    private Messenger mServiceMessenger;

    //Layouts
    RelativeLayout background;
    LinearLayout liner;

    ProgressDialog pdialog;
    //Boolean
    boolean textenter = false;
    boolean foundapp;
    String nav = "#FFFFFF";
    String action_bar_color = color;
    String back = "#ffe6e6e6";
    String forecastDaysNum = "3";
    String language;
    String tempStyle = "celsius";
    //View
    private View mDecorView;

    //Drawable
    Drawable appicon;
    ImageView app_one;

    //SpeechRecognizer
    public static SpeechRecognizer sr;
    int presset = 0;
    int pic;
    private Context cx;

    public static final String KWS_SEARCH = "wakeup";
    public static String KEYPHRASE = "wake up";

    public edu.cmu.pocketsphinx.SpeechRecognizer recognizer;
    float recognizerAccuracy = 1e-16f;

    boolean notification = false;
    private ImageView backgroundforrms;


    /*
     * Methods
     */
// OnCreate Functions



    @Override
    public void onPartialResult(Hypothesis hypothesis) {
        if (hypothesis == null)
            return;

        String text = hypothesis.getHypstr();
        if (text.toLowerCase().equals(KEYPHRASE))
            listenmulitlang();
    }

    @Override
    public void onResult(Hypothesis hypothesis) {
        if (hypothesis != null) {
            String text = hypothesis.getHypstr();
        }
    }

    @Override
    public void onBeginningOfSpeech() {
    }

    @Override
    public void onEndOfSpeech() {
        switchSearch(KWS_SEARCH);
    }

    private void switchSearch(String searchName) {
        try {
            if (recognizer != null) {
                recognizer.stop();
                recognizer.startListening(searchName);
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            Toast.makeText(getApplication(), "Failed to initialize always on engine", Toast.LENGTH_LONG).show();
        }
    }

    private void setupRecognizer(File assetsDir) {
        File modelsDir = new File(assetsDir, "models");
        recognizer = defaultSetup()
                .setAcousticModel(new File(modelsDir, "hmm/en-us-semi"))
                .setDictionary(new File(modelsDir, "dict/cmu07a.dic"))
                .setRawLogDir(assetsDir).setKeywordThreshold(recognizerAccuracy)

                .getRecognizer();
        recognizer.addListener(this);

        // Create keyword-activation search.
        recognizer.addKeyphraseSearch(KWS_SEARCH, KEYPHRASE);
        // Create grammar-based searches.

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(this);
        SharedPreferences prefEditor = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        if (prefEditor.contains("initialized")) {
            initialized = prefEditor.getBoolean("initialized", true);
        }
        if (!initialized) {
            startActivity(new Intent(getApplicationContext(), initMain.class));
            animate("out");
            finish();
        } else {
            if (sharedPrefs.contains("prefAnimations")) {
                animations = sharedPrefs.getBoolean("prefAnimations", true);
            }
            animate("in");
            //   animations();
            SetContentView();
            oncreate();
            tortanslate = "שלום";
        }
        try {
            AdView adView = new AdView(getApplicationContext());
            adView.setAdSize(AdSize.SMART_BANNER);
            adView.setAdUnitId(getResources().getString(R.string.banner_ad_unit_id));
            AdRequest adRequest = new AdRequest.Builder()

                    .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)       //
                    .addTestDevice("3a2d1115ab64e3") //
                    .build();
            adView.loadAd(adRequest);
            LinearLayout lin = (LinearLayout) findViewById(R.id.adLayout);
            lin.addView(adView);
            YoYo.with(Techniques.Bounce)
                    .duration(1000)
                    .playOn(findViewById(R.id.frameLayout));
        }
        catch (Exception e){

        }

    }

    void init_always_on() {
        if (!isInitializing) {
            isInitializing = true;
            if (wake) {
                new AsyncTask<Void, Void, Exception>() {
                    @Override
                    protected Exception doInBackground(Void... params) {
                        try {
                            btnSpeak.setEnabled(false);
                            btnSpeak.setBackgroundResource(R.drawable.error);
                        } catch (RuntimeException e) {
                            e.printStackTrace();
                            Log.d("ERROR", "ERROR IN CHANGING BUTTON BACKGROUND");
                        }
                        try {
                            btnSpeak.setEnabled(false);
                            btnSpeak.setBackgroundResource(R.drawable.error);
                            Assets assets = new Assets(Main1.this);
                            File assetDir = assets.syncAssets();
                            setupRecognizer(assetDir);
                        } catch (IOException e) {
                            return e;
                        } catch (RuntimeException e) {
                            e.printStackTrace();
                            Log.d("ERROR", "ERROR IN CHANGING BUTTON BACKGROUND");
                        }
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Exception result) {
                        if (result != null) {
                            Toast.makeText(getApplicationContext(), "Failed to init always on", Toast.LENGTH_SHORT).show();
                        } else {
                            switchSearch(KWS_SEARCH);
                            isInitializing = false;
                            btnSpeak.setEnabled(false);
                            if (!siri_bool) {
                                btnSpeak.setBackgroundResource(R.drawable.icon);
                            } else {
                                btnSpeak.setBackgroundResource(R.drawable.siri_icon);
                            }
                            btnSpeak.setEnabled(true);

                        }
                    }
                }.execute();
            }
        }
    }

    void sendMail() {
        new AsyncTask<Void, Void, Exception>() {
            @Override
            protected Exception doInBackground(Void... params) {
                try {
                    Assets assets = new Assets(Main1.this);
                    File assetDir = assets.syncAssets();
                    setupRecognizer(assetDir);
                } catch (IOException e) {
                    return e;
                }
                return null;
            }

            @Override
            protected void onPostExecute(Exception result) {
                if (result != null) {
                    Toast.makeText(getApplicationContext(), "Failed to init always on", Toast.LENGTH_SHORT).show();
                } else {
                    switchSearch(KWS_SEARCH);
                    isInitializing = false;

                }
            }
        }.execute();
    }

    void create_folder() {
        File dir = new File("sdcard/.Assist-Me");
        try {
            if (dir.mkdir()) {
                System.out.println("Directory created");
            } else {
                System.out.println("Directory is not created");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void animate(CharSequence kind) {
        if (animations) {
            if (kind.equals("in")) {
                overridePendingTransition(R.anim.abc_slide_in_bottom, R.anim.abc_slide_out_top);
            } else if (kind.equals("out")) {
                overridePendingTransition(R.anim.abc_slide_in_top, R.anim.abc_slide_out_bottom);
            } else if (kind.equals("slide_in")) {
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            } else if (kind.equals("slide_out")) {
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        }
    }



    void tooleap() {
        if(initialized) {
            if (sharedPrefs.contains("prefTooLeap")) {
                if (sharedPrefs.getBoolean("prefTooLeap", true)) {

                }
            }
        }
        }


    void oncreate() {
        initialize();
        sharedprefrences();
        onclicks();
        todoatstart();
        startListeningOnstart();
        StartNavDrawer();
        startService();
        if(!siri_bool){
        set_custom_color();}
        create_folder();
        createTimer();
        handleNotification();



        // init_swipeview();
       /* Intent intent = new Intent(this, always_on_sefrvice.class);
        stopService(intent);
        startService(intent);  */
    }


    //Methods
    void wolfram(String input) {
        WolframInput = input;
        try {
            new WolframAlphaTask().execute();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    void StartUrl(String url) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    void handleNotification() {
        String ns = Context.NOTIFICATION_SERVICE;
        NotificationManager nMgr = (NotificationManager) getApplicationContext().getSystemService(ns);
        nMgr.cancelAll();
        if (notification) {
            displayNotificationOne();
        }
    }

    void displayNotificationOne() {
        if (notification) {
            if (sdk >= 16) {

                Intent resultIntent = new Intent(getApplicationContext(), Main1.class);
                PendingIntent resultPendingIntent =
                        PendingIntent.getActivity(
                                this,
                                0,
                                resultIntent,
                                PendingIntent.FLAG_UPDATE_CURRENT
                        );
                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(this)
                                .setSmallIcon(R.drawable.ic_launcher)
                                .setContentTitle("Assist Me")
                                .setOngoing(true)
                                .setContentText("Assist Me is running in background");
                int mNotificationId = 001;
                NotificationManager mNotifyMgr =
                        (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                mBuilder.setContentIntent(resultPendingIntent);
                mNotifyMgr.notify(mNotificationId, mBuilder.build());

            } else {
                String ns = Context.NOTIFICATION_SERVICE;
                NotificationManager mNotificationManager = (NotificationManager) getSystemService(ns);
                int icon = R.drawable.ic_launcher_on_focus;
                CharSequence tickerText = "Assist Me"; // ticker-text
                long when = System.currentTimeMillis();
                Context context = getApplicationContext();
                CharSequence contentTitle = "Assist-Me";
                CharSequence contentText = "Assist Me is running in background";
                Intent notificationIntent = new Intent(this, Main1.class);
                PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
                Notification notification = new Notification(icon, tickerText, when);
                notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);

// and this
                notification.flags = Notification.FLAG_ONGOING_EVENT;
                mNotificationManager.cancelAll();
            }
        }
    }

    void set_custom_color() {
       /* mDrawerList.setBackgroundColor(Color.parseColor(nav));
        if(sharedPreferences.contains("action_bar")) {
            {
                findViewById(R.id.linear).setBackgroundColor(Color.parseColor(action_bar_color));
            }
        }*/
        if(action_bar_color.contains("#")){
            findViewById(R.id.actionbar_back).setBackgroundColor(Color.parseColor(action_bar_color));
        }
        if (!siri_bool) {

            if (back.contains("#")) {
                background.setBackgroundColor(Color.parseColor(back));
            }
        } else {
            if (!siri_bool) {
            } else {
                background.setBackgroundResource(R.drawable.siri_back);
            }
        }

    }

    void animations() {
        /*if (animation_selected.equals("Bottom to top")) {
            overridePendingTransition(R.anim.bottom_to_top, R.anim.top_to_bottom);
        } else if (animation_selected.equals("Left to right")) {
            overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
        } else if (animation_selected.equals("Fade in")) {
            overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
        } else if (animation_selected.equals("Bottom to top + Fade in")) {
            overridePendingTransition(R.anim.bottom_to_top_fade_in, R.anim.top_to_bottom_fade_out);
        } else {
            overridePendingTransition(R.anim.left_to_rigt_fade_in, R.anim.right_to_left_fade_out);
        }*/
    }

    void makeitflat() {
        setContentView(R.layout.main_siri);
    }

    void initialize() {
        backgroundforrms = (ImageView) findViewById(R.id.imageButtonbackground);
        // btnSpeackBack  = (ImageView)findViewById(R.id.back_trans);
        info_one = (TextView) findViewById(R.id.info_one);
        mVibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        tts = new TextToSpeech(this, this);
        background = (RelativeLayout) findViewById(R.id.back);
        edit = (EditText) findViewById(R.id.mail);
        btnSpeak = (ImageButton) findViewById(R.id.imageButton);
        imgview = (ImageView) findViewById(R.id.info_image);
        shapeDrawable = (BitmapDrawable)backgroundforrms.getBackground();
    }

    void sharedprefrences() {
        try {
            if (sharedPrefs.contains("prefLang")) {
                language = sharedPrefs.getString("prefLang", "");
                if (!language.equals("english") && !language.equals("hebrew")) {
                    Intent picklang = new Intent(this, initMain.class);
                    startActivity(picklang);
                    animate("out");
                    finish();
                }
            } else {
                Intent picklang = new Intent(this, initMain.class);
                startActivity(picklang);
                animate("out");
                finish();
            }
            if (sharedPrefs.contains("prefWake")) {
                wake = sharedPrefs.getBoolean("prefWake", true);
            }
            if (sharedPrefs.contains("prefPitch")) {
                pitch = sharedPrefs.getInt("prefPitch", 0);
            } else {
                sharedPrefs.edit().putBoolean("prefWake", false);
                wake = false;
            }
            if (sharedPrefs.contains("prefNotification")) {
                notification = sharedPrefs.getBoolean("prefNotification", true);
            } else {
                sharedPrefs.edit().putBoolean("prefNotification", false);
                notification = false;
            }
       /* if (sharedPrefs.contains("prefWakeUp")) {
            KEYPHRASE = sharedPrefs.getString("prefWakeUp", "");
        }*/
            if (sharedPreferences.contains("action_bar")) {
                action_bar_color = sharedPreferences.getString("action_bar", "");
            } else {
                action_bar_color = color;
            }
            if (sharedPreferences.contains("back")) {
                back = sharedPreferences.getString("back", "");
            }
            if (sharedPreferences.contains("nav")) {
                nav = sharedPreferences.getString("nav", "");
            }
            if (sharedPreferences.contains("color")) {
                color = sharedPreferences.getString("color", "");
            }
            if (sharedPreferences.contains("donotshowagain")) {
                donotshowagain = sharedPreferences.getBoolean("donotshowagain", true);
            }
            if (sharedPrefs.contains("prefBitch")) {
                bitchstring = sharedPrefs.getBoolean("prefBitch", false);
                if (bitchstring) {
                    user_name = "BITCH";
                } else {
                    if (!user_name.toLowerCase().equals("bitch")) {
                        backup_user_name = user_name;
                    }
                    if (user_name.toLowerCase().equals("bitch")) {
                        user_name = backup_user_name;
                    }
                }

            }
            if (sharedPrefs.contains("prefAnswer")) {
                speakback = sharedPrefs.getBoolean("prefAnswer", true);
            } else {
                sharedPreferences.edit().putBoolean("prefAnswer", true);
                speakback = true;
            }
            if (sharedPrefs.contains("prefAssistantName")) {
                com_name = sharedPrefs.getString("prefAssistantName", "");
            }
            if (sharedPrefs.contains("prefUsername")) {
                user_name = sharedPrefs.getString("prefUsername", "");
            }
            if (sharedPrefs.contains("prefListenStart")) {
                boolean listen = sharedPrefs.getBoolean("prefListenStart", false);
                if (listen) {
                    listenmulitlang();
                }
            }
            if (sharedPrefs.contains("prefGreetStart")) {
                onstart = sharedPrefs.getBoolean("prefGreetStart", true);
            }
            if (sharedPrefs.contains("prefCards")) {
            }
            if (sharedPrefs.contains("prefAccent")) {
                accent = sharedPrefs.getString("prefAccent", "");
            }
            if (sharedPreferences.contains("initialized2")) {
                initialized2 = sharedPreferences.getBoolean("initialized2", true);
            }
            if (sharedPreferences.contains("initialized3")) {
                initialized3 = sharedPreferences.getBoolean("initialized3", true);
            }
            if (!initialized2 && hand1 == null) {
                TranslateAnimation animation1 = new TranslateAnimation(0.0f, 0.0f,
                        0.0f, 200.0f);          //  new TranslateAnimation(xFrom,xTo, yFrom,yTo)
                animation1.setDuration(1100);  // animation duration
                animation1.setRepeatCount(Animation.INFINITE);

                hand1 = new ImageView(getApplicationContext());
                hand1.setBackgroundResource(R.drawable.hand);
                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                lp.addRule(RelativeLayout.CENTER_HORIZONTAL);
                hand1.setLayoutParams(lp);
                background.addView(hand1);
                hand1.setY(findViewById(R.id.puthandhere).getY());
                hand1.startAnimation(animation1);

            }
            if (!initialized3 && initialized2 && hand1 == null) {
                hand1 = new ImageView(getApplicationContext());
                hand1.setBackgroundResource(R.drawable.hand);
                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                lp.addRule(RelativeLayout.CENTER_HORIZONTAL);
                hand1.setLayoutParams(lp);
                background.addView(hand1);
                hand1.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right));
                hand1.getAnimation().setDuration(1500);
                hand1.getAnimation().setRepeatCount(Animation.INFINITE);

            }
        }
        catch (Exception e){

        }
    }

    void onclicks() {
        final GestureDetector gdt = new GestureDetector(new GestureListener());
        findViewById(R.id.action_bar_title).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(final View view, final MotionEvent event) {
                gdt.onTouchEvent(event);
                return true;
            }
        });
        edit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    mVibrator.vibrate(50);
                    say(QUESTION,edit.getText().toString());
                    com = edit.getText().toString();
                    edit.setText("");
                    startAfterResult();
                    InputMethodManager imm = (InputMethodManager) getSystemService(
                            Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(edit.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });
        edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!edit.getText().toString().equals(com)) {
                    if (edit.getText().toString().equals(null) || !edit.getText().toString().equals("")) {
                        if (!siri_bool) {
                            btnSpeak.setBackgroundResource(R.drawable.enter);
                            imagebutton = "enter";
                        }
                    } else {
                        if (siri_bool) {
                            btnSpeak.setBackgroundResource(R.drawable.siri_icon);
                        } else {
                            btnSpeak.setBackgroundResource(R.drawable.btnspeek);
                        }
                        imagebutton = "speak";
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!edit.getText().toString().equals(com)) {
                    if (edit.getText().toString().equals(null) || !edit.getText().toString().equals("")) {
                        if (!siri_bool) {
                            btnSpeak.setBackgroundResource(R.drawable.enter);
                            imagebutton = "enter";
                        }
                    } else {
                        if (siri_bool) {
                            btnSpeak.setBackgroundResource(R.drawable.siri_icon);
                        } else {
                            btnSpeak.setBackgroundResource(R.drawable.btnspeek);
                        }
                        imagebutton = "speak";
                    }
                }
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.setFocusable(true);
            }
        });
        edit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
            }
        });
        btnSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YoYo.with(Techniques.Bounce)
                        .duration(1000)
                        .playOn(findViewById(R.id.frameLayout));
                //  stopService(new Intent(getApplicationContext(),VoiceService.class));
                if (listening) {
                    if (tts != null) {
                        tts.stop();
                    }
                    mVibrator.vibrate(50);
                    sr.stopListening();
                    if (siri_bool) {
                        btnSpeak.setBackgroundResource(R.drawable.siri_icon);
                    } else {
                        btnSpeak.setBackgroundResource(R.drawable.icon);
                    }
                    listening = false;
                } else {
                    if (imagebutton.equals("enter")) {
                        mVibrator.vibrate(50);
                        say(QUESTION,edit.getText().toString());
                        
                        com = edit.getText().toString();
                        edit.setText("");
                        if (whatAfterResult.equals("normal")) {
                            startAfterResult();
                        } else if (whatAfterResult.equals("to_search")) {
                            handle_to_search();
                        } else if (whatAfterResult.equals("to_call")) {
                            handle_to_call();
                        } else if (whatAfterResult.equals("to_youtube")) {
                            handle_to_youtube();
                        } else if (whatAfterResult.equals("open_app")) {
                            handle_open_app();
                        } else if (whatAfterResult.equals("music")) {
                            handle_music();
                        }
                        InputMethodManager imm = (InputMethodManager) getSystemService(
                                Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(edit.getWindowToken(), 0);
                    } else {
                        mVibrator.vibrate(50);
                        if (tts != null) {
                            tts.stop();
                        }
                        if (language.equals("english")) {
                            startVoiceRecognitionActivityenglish();
                        } else if (language.equals("hebrew")) {
                            startVoiceRecognitionActivityhebrew();
                        }

                    }
                }
            }
        });
        background.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm = (InputMethodManager) getSystemService(
                        Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(edit.getWindowToken(), 0);
                edit.clearFocus();
                return false;
            }
        });
        edit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
            }
        });
    }

    private class LongOperation extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            Mail m = new Mail("tomerosenfeld007@yahoo.com", "Tomerr007#");
            String[] toArr = {"tomerosenfeld007@gmail.com", "tomerosenfeld007@gmail.com"};
            m.setTo(toArr);
            m.setFrom("tomerosenfeld007@yahoo.com");
            m.setSubject("Assist Me Command Not Found");
            m.setBody("User name is " + user_name + " \n tried to say \n" + " ' " + com + " ' ");

            try {
                if (m.send()) {
                } else {

                }
            } catch (Exception e) {
                Log.e("MailApp", "Could not send email", e);
            }
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            // might want to change "executed" for the returned string passed
            // into onPostExecute() but that is upto you
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }

    void todoatstart() {

        if(accent.equals("UK")){
            tts.setLanguage(Locale.UK);
        }
        else {
            tts.setLanguage(Locale.US);
        }
        if(pitch!=0){
            tts.setPitch(pitch);
        }
        resideMenu = new ResideMenu(this);
        if(nav.contains("#")){
        resideMenu.setBackgroundColor(Color.parseColor(nav));}
        resideMenu.attachToActivity(this);

        // create menu items;
        String titles[] = { "Settings", "Tutorial", "Features"};
        int icon[] = { R.drawable.ic_action_settings, R.drawable.ic_action_help, R.drawable.ic_tutorial};

        for (int i = 0; i < titles.length; i++){
            final int x = i;
            ResideMenuItem item = new ResideMenuItem(this, icon[i], titles[i]);

            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (x){
                        case 0:
                            startActivity(new Intent(getApplicationContext(), preferencesMenu.class));
                            animations();
                            animate("out");
                            break;
                        case 1:
                            startActivity(new Intent(getApplicationContext(), instructions.class));
                            animations();
                            animate("out");
                            break;
                        case 2:
                            startActivity(new Intent(getApplicationContext(), features.class));
                            animations();
                            animate("out");
                            break;
                        default:
                            break;
                    }
                }
            });
            resideMenu.addMenuItem(item,  ResideMenu.DIRECTION_LEFT); // or  ResideMenu.DIRECTION_RIGHT
        }
        String titles2[] = {"Become an alpha tester","About","Rate us" };
        int icon2[] = {R.drawable.ic_action_group ,R.drawable.ic_action_about,R.drawable.ic_action_good};
        for (int i = 0; i < titles2.length; i++) {
            final int x = i;
            ResideMenuItem item = new ResideMenuItem(this, icon2[i], titles2[i]);
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (x){
                        case 0:
                            StartUrl("https://plus.google.com/communities/112929264818101844802");
                            animations();
                            animate("out");
                            break;
                        case 1:
                            startActivity(new Intent(getApplicationContext(), about.class));
                            animations();
                            animate("out");
                            break;
                        case 2:
                            try {
                                Uri uri = Uri.parse("market://details?id=com.assist.me");
                                Intent it = new Intent(Intent.ACTION_VIEW, uri);
                                animations();
                                startActivity(it);
                                animate("out");
                            }
                            catch (ActivityNotFoundException e){
                                e.printStackTrace();
                                Toast.makeText(getApplicationContext(),"Play store not found",Toast.LENGTH_LONG).show();
                            }
                        default:
                            break;
                    }
                }
            });
            resideMenu.addMenuItem(item,  ResideMenu.DIRECTION_RIGHT); // or  ResideMenu.DIRECTION_RIGHT

        }
        findViewById(R.id.title_bar_left_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
            }
        });
        findViewById(R.id.title_bar_right_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resideMenu.openMenu(ResideMenu.DIRECTION_RIGHT);
            }
        });
        resideMenu.setMenuListener(new ResideMenu.OnMenuListener() {
            @Override
            public void openMenu() {
                if(!initialized3) {
                    sharedPreferences.edit().putBoolean("initialized3", true).commit();
                    initialized3 = true;
                    hand1.setBackgroundResource(R.drawable.trans);
                    hand1.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void closeMenu() {

            }
        });
     /*   findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MultipleItemList.class));
            }
        });*/
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        //     startService(new Intent(Main1.this, VoiceService.class));
        mBindFlag = Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH ? 0 : Context.BIND_ABOVE_CLIENT;
        if (Build.VERSION.SDK_INT > 18) {
            startService(new Intent(getApplicationContext(), NotificationService.class));
        }
        Calendar c = Calendar.getInstance();
        int time = c.get(Calendar.HOUR_OF_DAY);
        String minute = String.valueOf(c.get(Calendar.MINUTE));
        if (Integer.valueOf(minute) < 10) {
            minute = "0" + minute;
        }
        if (siri_bool) {
            btnSpeak.setBackgroundResource(R.drawable.siri_icon);
        } else {
            btnSpeak.setBackgroundResource(R.drawable.btnspeek);
        }
        if (onstart == true && listen_on_start == false) {
            if (time >= 5 && time <= 11) {
                text = "Good morning " + user_name;
            } else if (time >= 11 && time <= 18) {
                text = "Good afternoon " + user_name;
            } else if (time >= 19 && time <= 23) {
                text = "Good evening " + user_name;
            } else {
                text = "Good night " + user_name;
            }
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
            whatactivityresult = "tts";
        }
    }

    private void TimerMethod() {
        this.runOnUiThread(Timer_Tick);
    }

    private Runnable Timer_Tick = new Runnable() {
        public void run() {
            setTime();
        }
    };

    void createTimer() {
        myTimer = new Timer();
        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                TimerMethod();
            }

        }, 0, 1000);
    }

    void setTime() {
        Calendar c = Calendar.getInstance();
        int time = c.get(Calendar.HOUR_OF_DAY);
        String minute = String.valueOf(c.get(Calendar.MINUTE));
        if (Integer.valueOf(minute) < 10) {
            minute = "0" + minute;
        }
    }

    void listenmulitlang() {
        if (tts != null) {
            tts.stop();
        }
        if (language.equals("english")) {
            startVoiceRecognitionActivityenglish();
        } else if (language.equals("hebrew")) {
            startVoiceRecognitionActivityhebrew();
        }
    }

    void downloadTTS(){

    }

    void say(String form, final String tosay){
        Typeface font = Typeface.createFromAsset(getAssets(), "roboto.ttf");
        if(!tosay.equals(null)&&!tosay.replaceAll(" ","").equals("")) {
            TextView textBack = new TextView(getApplicationContext());
            textBack.setTypeface(font);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.setMargins(background.getRootView().getWidth()/10,0,0,background.getHeight()/45);
            //lp.height =  background.getRootView().getWidth()/(10*155/350);
            textBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    edit.setText(tosay);
                }
            });
            if (form.equals(QUESTION)) {
                lp.gravity = Gravity.RIGHT;
                textBack.setBackgroundResource(R.drawable.text_siri_text);
                textBack.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        int sdk = Build.VERSION.SDK_INT;
                        if (sdk < Build.VERSION_CODES.HONEYCOMB) {
                            android.text.ClipboardManager clipboard = (android.text.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                            clipboard.setText(tosay);
                            Toast.makeText(Main1.this, "copied to clipboard", Toast.LENGTH_SHORT).show();
                        } else {
                            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                            ClipData clip = ClipData.newPlainText("assist-me", tosay);
                            clipboard.setPrimaryClip(clip);
                            Toast.makeText(Main1.this, "copied to clipboard", Toast.LENGTH_SHORT).show();
                        }
                        return false;
                    }
                });
            } else {
                lp.setMargins(0,0,background.getRootView().getWidth()/10,0);
                textBack.setPadding(background.getRootView().getWidth()/15,0,0,0);
                lp.gravity = Gravity.LEFT;
                textBack.setBackgroundResource(R.drawable.text_siri);
                if (speakback){
                    tts.speak(tosay, TextToSpeech.QUEUE_FLUSH, null);
                    if(!tts.isSpeaking()){
                        downloadTTS();
                    }
                }
                textBack.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        int sdk = Build.VERSION.SDK_INT;
                        if (sdk < Build.VERSION_CODES.HONEYCOMB) {
                            android.text.ClipboardManager clipboard = (android.text.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                            clipboard.setText(tosay);
                            Toast.makeText(Main1.this, "answer copied to clipboard", Toast.LENGTH_SHORT).show();
                            mVibrator.vibrate(50);
                        } else {
                            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                            ClipData clip = ClipData.newPlainText("assist-me", tosay);
                            clipboard.setPrimaryClip(clip);
                            Toast.makeText(Main1.this, "answer copied to clipboard", Toast.LENGTH_SHORT).show();
                            mVibrator.vibrate(50);
                        }
                        return false;
                    }
                });
            }
            textBack.setPadding(Math.round(getResources().getDimension(R.dimen.paddingText)), Math.round(getResources().getDimension(R.dimen.paddingText)), Math.round(getResources().getDimension(R.dimen.paddingText)), Math.round(getResources().getDimension(R.dimen.paddingText)));
             String shit = tosay.substring(0, 1).toUpperCase() + tosay.substring(1);
            char first = Character.toUpperCase(shit.charAt(0));
            shit = first + shit.substring(1);
            textBack.setText(shit);
            textBack.setTextSize(getResources().getDimension(R.dimen.textSize));
            textBack.setMinHeight(background.getRootView().getWidth()/(10*155/350));

            textBack.setGravity(View.TEXT_ALIGNMENT_CENTER|Gravity.CENTER_VERTICAL);
            textBack.setLayoutParams(lp);
            textBack.setTextColor(Color.parseColor("#FFFFFF"));
            textBack.setMovementMethod(new ScrollingMovementMethod());
            LinearLayout container = (LinearLayout) findViewById(R.id.textScroller);
            container.addView(textBack);
            create(textBack,container);
        }
        final ScrollView scrollview = ((ScrollView) findViewById(R.id.scroller));

        InputMethodManager imm = (InputMethodManager) getApplicationContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);


            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(scrollview.getLayoutParams());
           lp.height = background.getRootView().getHeight()/2;
            lp.addRule(RelativeLayout.BELOW, R.id.layout_top);
            scrollview.setLayoutParams(lp);
            scrollview.post(new Runnable() {
            @Override
            public void run() {
                scrollview.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });
    }

    void startListeningOnstart() {
        if (listen_on_start == true) {
            listenmulitlang();
        }
        Intent example = getIntent();
        comingfromwidget = example.getStringExtra("com");
        if (comingfromwidget != null) {
            if (tts != null) {
                tts.stop();
            }
            listenmulitlang();
        }
    }

    void StartNavDrawer() {
    /*    ResideMenu resideMenu2 = new ResideMenu(this);
        resideMenu2.attachToActivity(this);

        // create menu items;
        String titles2[] = {"Become an alpha tester","About","Rate us" };
        int icon2[] = {R.drawable.ic_action_group ,R.drawable.ic_action_about,R.drawable.ic_action_good};

        for (int i = 0; i < titles2.length; i++){
            final int x = i;
            ResideMenuItem item = new ResideMenuItem(this, icon2[i], titles2[i]);

            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (x){
                        case 0:
                            StartUrl("https://plus.google.com/communities/112929264818101844802");
                            animations();
                            animate("out");
                            break;
                        case 1:
                            startActivity(new Intent(getApplicationContext(), about.class));
                            animations();
                            animate("out");
                            break;
                        case 2:
                            try {
                                Uri uri = Uri.parse("market://details?id=com.assist.me");
                                Intent it = new Intent(Intent.ACTION_VIEW, uri);
                                animations();
                                startActivity(it);
                                animate("out");
                            }
                            catch (ActivityNotFoundException e){
                                e.printStackTrace();
                                Toast.makeText(getApplicationContext(),"Play store not found",Toast.LENGTH_LONG).show();
                            }
                        default:
                            break;
                    }
                }
            });
            resideMenu2.addMenuItem(item,  ResideMenu.DIRECTION_RIGHT); // or  ResideMenu.DIRECTION_RIGHT
        }*/
        //initialize_nav_drawer();
    }

    void startService() {
        Intent intent = new Intent(this, services.class);
        startService(intent);
    }

    void SetContentView() {
        sharedPreferences = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        setContentView(R.layout.main1);
        if (sharedPrefs.contains("prefSiri")) {
            siri_bool = sharedPrefs.getBoolean("prefSiri", false);
            if (siri_bool == true) {
                makeitflat();
            }
        }
    }

    void startAfterResult() {
        imgview.setBackgroundResource(R.drawable.trans);
        {
            if (whatAfterResult.equals("normal")) {
                if (language.equals("english")) {
                    try {
                        englishafterresult();
                    } catch (Settings.SettingNotFoundException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                } else if (language.equals("hebrew")) {
                    try {
                        newhebrewafterrseult();
                    } catch (Settings.SettingNotFoundException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }

            } else if (whatAfterResult.equals("to_search")) {
                listenmulitlang();
            } else if (whatAfterResult.equals("to_call")) {
                listenmulitlang();
            }
        }
    }

    void handle_to_search() {
        say(QUESTION,com);
       
        int numWords = com.split(" ").length;
        if (numWords >= 1) {
            whatAfterResult = "normal";
            String arr[] = com.split(" ", 3);
            String theRest = arr[0];
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_WEB_SEARCH);
            intent.putExtra(SearchManager.QUERY, theRest);
            startActivity(intent);
            animate("out");
            finish();
            say(ANSWER,"searching..");
            
        } else {
            whatAfterResult = "to_search";
            say(ANSWER,"What would you want me to search?");
        }

    }

    void handle_what_to_remind(){
        say(QUESTION,com);
        if(com.split(" ").length >= 1){
            what_to_remind = com;
            if(time_to_remind.isEmpty()){
                whatAfterResult = WHEN_TO_REMIND;
            }
            else{

            }
        }
        else {
            whatAfterResult = WHAT_TO_REMIND;
            say(ANSWER,"What did you want me to remind you?");
        }
    }

    public void showReminder(final String time_to_remind, final String what_to_remind,int date){
        final Dialog dialog = new Dialog(Main1.this);
        dialog.setContentView(R.layout.reminder_dialog);
        TextView toRemind = (TextView) dialog.findViewById(R.id.reminder);
        toRemind.setText(what_to_remind);
        TextView when = (TextView) dialog.findViewById(R.id.time);
        when.setText(time_to_remind);
        final TextView tvdate = (TextView)dialog.findViewById(R.id.date);
        Button confirm = (Button)dialog.findViewById(R.id.confirm);
        Calendar c = Calendar.getInstance();
        TextView tvday = (TextView)dialog.findViewById(R.id.day);
        int day_int = c.get(Calendar.DAY_OF_WEEK);
        String day = "";
        for(int i = 1;i<phrases.days.length;i++){
            if(day_int == i){
                day = phrases.days[i];
            }
        }
        tvday.setText(day);
        final Calendar start = Calendar.getInstance();
        month = start.get(Calendar.MONTH);
        if(StringContainsFromList(com.toLowerCase(),phrases.month_string)){
            for (int i = 0; i < phrases.month_string.length; i++){
                if(com.toLowerCase().contains(phrases.month_string[i])){
                    month = phrases.month[i];
                }
            }
        }
        TextView tvmonth = (TextView) dialog.findViewById(R.id.month);
        tvmonth.setText(phrases.month_string[month]);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hour = 0;
                dialog.dismiss();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                start.set(Calendar.YEAR, month,  Calendar.DAY_OF_MONTH, Calendar.HOUR_OF_DAY, 0);
                Calendar beginCal = Calendar.getInstance();
                if(StringContainsFromList(time_to_remind.toLowerCase(),phrases.number)){
                    for(int i = 0; i<phrases.number.length;i++){
                        if(time_to_remind.contains(phrases.number[i])){
                            if(com.contains("p.m")||com.contains("pm")||com.contains("p.m.")) {
                                hour = i+12;
                            }
                            else{
                                hour = i;
                            }
                        }
                    }
                }
                dayOfMonth = start.get(Calendar.DAY_OF_MONTH);
                if(dayOfMonthAvi(com,phrases.month_string[month],time_to_remind)){
                    Toast.makeText(getApplicationContext(),"ghour",Toast.LENGTH_SHORT).show();
                    dayOfMonth = Integer.valueOf(com.toLowerCase().substring(com.toLowerCase().lastIndexOf(month)).replaceAll("[\\D]", ""));
                }
                tvdate.setText(String.valueOf(dayOfMonth));

                beginCal.set(start.get(Calendar.YEAR), month, dayOfMonth, hour, 0);
                intent.setData(Uri.parse("content://com.android.calendar/events/" + String.valueOf(pushAppointmentsToCalender(Main1.this,what_to_remind,"Created by Assist-Me","",0,beginCal.getTimeInMillis(),true,true))));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });
        dialog.show();
    }

    boolean dayOfMonthAvi(String com, String month, String time_to_remind){
        if(com.substring(com.lastIndexOf(month)).matches(".*\\d.*")){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public boolean WordInList(String word,String list[]){
        boolean toReturn = false;
        for(int i = 0;i<list.length;i++){
            if(list[i].equals(word))
            toReturn= true;
        }
        return toReturn;
    }

    public boolean StringContainsFromList(String string, String list[]){
        boolean toRturn = false;
        for(int i = 0;i<list.length;i++){
            if(string.contains(list[i])){
                toRturn = true;
            }
        }
        return toRturn;
    }

    void handle_to_call() {
        whatAfterResult = "normal";
        contact = com;
        call();
        Toast.makeText(getApplicationContext(), contact, Toast.LENGTH_SHORT).show();
        if (sent == false) {
        }
        say(ANSWER,"couldn't find " + contact + " in your contacts");
        
    }

    void create(final View dismissableButton,final ViewGroup group){
        dismissableButton.setOnTouchListener(new SwipeDismissTouchListener(
                dismissableButton,
                null,
                new SwipeDismissTouchListener.DismissCallbacks() {
                    @Override
                    public boolean canDismiss(Object token) {
                        return true;
                    }
                    @Override
                    public void onDismiss(View view, Object token) {
                        group.removeView(dismissableButton);
                    }
                }));
    }

    void call() {
        AlertDialog.Builder callAlert = new AlertDialog.Builder(Main1.this);
        ContentResolver cr = getContentResolver();
        Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, "DISPLAY_NAME" + " LIKE '%" + contact + "%'", null,
                ContactsContract.CommonDataKinds.Phone.LAST_TIME_CONTACTED + " DESC");
        if (cursor.moveToFirst()) {
            String contactId =
                    cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            Cursor phones = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId, null, null);
            while (phones.moveToNext()) {
                number = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                int type = phones.getInt(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));
                switch (type) {
                    case ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE:
                        if (number != null) {
                            mobilenum = number;
                            mobileavi = true;
                        }
                        sent = true;
                        break;
                    case ContactsContract.CommonDataKinds.Phone.TYPE_HOME:
                        if (number != null) {
                            homenum = number;
                            homeavi = true;
                            sent = true;
                        }
                        break;
                    case ContactsContract.CommonDataKinds.Phone.TYPE_WORK:
                        if (number != null) {
                            worknum = number;
                            workavi = true;
                        }
                        break;
                    case ContactsContract.CommonDataKinds.Phone.TYPE_OTHER:
                        if (number != null) {
                            othernum = number;
                            otheravi = true;
                            sent = true;
                        }
                        break;
                    default:
                        break;
                }
                android.os.SystemClock.sleep(100);
                callAlert.setTitle("Pick number");
                TableLayout linearLayout = new TableLayout(Main1.this);
                if (mobileavi) {
                    Button mobile = new Button(getApplicationContext());
                    mobile.setText("Mobile number: " + mobilenum);
                    mobile.setBackgroundColor(Color.DKGRAY);
                    mobile.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent callIntent = new Intent(Intent.ACTION_CALL);
                            callIntent.setData(Uri.parse("tel:" + mobilenum));
                            startActivity(callIntent);
                        }
                    });
                    linearLayout.addView(mobile);
                }
                if (homeavi) {
                    Button home = new Button(getApplicationContext());
                    home.setText("Home number: " + homenum);
                    home.setBackgroundColor(Color.DKGRAY);
                    home.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent callIntent = new Intent(Intent.ACTION_CALL);
                            callIntent.setData(Uri.parse("tel:" + homenum));
                            startActivity(callIntent);
                        }
                    });
                    linearLayout.addView(home);
                }
                if (workavi) {
                    Button work = new Button(getApplicationContext());
                    work.setText("Work number: " + worknum);
                    work.setBackgroundColor(Color.DKGRAY);
                    work.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent callIntent = new Intent(Intent.ACTION_CALL);
                            callIntent.setData(Uri.parse("tel:" + worknum));
                            startActivity(callIntent);
                        }
                    });
                    linearLayout.addView(work);
                }
                if (otheravi) {
                    Button other = new Button(getApplicationContext());
                    other.setText("Other number: " + othernum);
                    other.setBackgroundColor(Color.DKGRAY);
                    other.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent callIntent = new Intent(Intent.ACTION_CALL);
                            callIntent.setData(Uri.parse("tel:" + othernum));
                            startActivity(callIntent);
                        }
                    });
                    linearLayout.addView(other);
                }
                callAlert.setView(linearLayout);
            }

            if (mobileavi && !otheravi && !homeavi && !workavi) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + mobilenum));
                startActivity(callIntent);
            } else if (!mobileavi && otheravi && !homeavi && !workavi) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + othernum));
                startActivity(callIntent);
            } else if (!mobileavi && !otheravi && homeavi && !workavi) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + homenum));
                startActivity(callIntent);
            } else if (!mobileavi && !otheravi && !homeavi && workavi) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + worknum));
                startActivity(callIntent);
            } else {
                callAlert.show();
            }
            say(ANSWER,"calling " + contact);
            sent = true;
            phones.close();
            cursor.close();
        }
    }

    void handle_sms_contact() {
        if (WordInList(com, phrases.Cancelwords)) {
            whatAfterResult = "normal";
            say(ANSWER,"Ok canceled");
            
        } else {
            sent = false;
            say(QUESTION,com);
            int numWords = com.split(" ").length;
            if (numWords >= 1) {
                whatAfterResult = "message";
                contact = com;
                final AlertDialog.Builder callAlert = new AlertDialog.Builder(Main1.this);
                ContentResolver cr = getContentResolver();
                Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI,
                        null, "DISPLAY_NAME" + " LIKE '%" + contact + "%'", null,
                        ContactsContract.CommonDataKinds.Phone.LAST_TIME_CONTACTED + " DESC");
                if (cursor.moveToFirst()) {
                    String contactId =
                            cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                    Cursor phones = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId, null, null);

                    while (phones.moveToNext()) {
                        number = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        int type = phones.getInt(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));
                        say(ANSWER,"messaging " + contact);
                        switch (type) {
                            case ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE:
                                if (number != null) {
                                    mobilenum = number;
                                    mobileavi = true;
                                }
                                sent = true;
                                break;
                            case ContactsContract.CommonDataKinds.Phone.TYPE_HOME:
                                if (number != null) {
                                    homenum = number;
                                    homeavi = true;
                                    sent = true;
                                }
                                break;
                            case ContactsContract.CommonDataKinds.Phone.TYPE_WORK:
                                if (number != null) {
                                    worknum = number;
                                    workavi = true;
                                }
                                break;
                            case ContactsContract.CommonDataKinds.Phone.TYPE_OTHER:
                                if (number != null) {
                                    othernum = number;
                                    otheravi = true;
                                    sent = true;
                                }
                                break;
                            default:
                                break;
                        }
                        android.os.SystemClock.sleep(100);
                        callAlert.setTitle("Pick number");
                        if (mobileavi) {
                            callAlert.setPositiveButton("Mobile" + mobilenum, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    say(ANSWER,"What's your message?");
                                    
                                    phoneNumber = mobilenum;
                                    whatAfterResult = "message";
                                }
                            });
                            if (workavi) {
                                callAlert.setNeutralButton("Work" + worknum, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                        say(ANSWER,"What's your message?");
                                        
                                        phoneNumber = worknum;
                                        whatAfterResult = "message";
                                    }
                                });
                            }

                            if (otheravi) {
                                callAlert.setNegativeButton("other" + othernum, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                        say(ANSWER,"What's your message?");
                                        
                                        phoneNumber = othernum;
                                        whatAfterResult = "message";
                                    }
                                });
                            }
                        }
                        if (!sent) {
                            say(ANSWER,"Couldn't find that contact");
                            
                            whatAfterResult = "normal";
                        }

                        if (mobileavi && !otheravi && !homeavi && !workavi) {
                            say(ANSWER,"What's your message?");
                            
                            phoneNumber = mobilenum;
                            whatAfterResult = "message";
                        } else if (!mobileavi && otheravi && !homeavi && !workavi) {
                            say(ANSWER,"What's your message?");
                            
                            phoneNumber = othernum;
                            whatAfterResult = "message";
                        } else if (!mobileavi && !otheravi && homeavi && !workavi) {
                            say(ANSWER,"Can't message home numbers");
                            
                            whatAfterResult = "normal";
                        } else if (!mobileavi && !otheravi && !homeavi && workavi) {
                            say(ANSWER,"What's your message?");
                            
                            phoneNumber = worknum;
                            whatAfterResult = "message";
                        } else {
                            callAlert.show();
                        }
                    }
                    if (!sent) {
                        say(ANSWER,"Couldn't find that contact");
                        
                        whatAfterResult = "normal";
                    }
                } else {
                    whatAfterResult = "recipient";
                    say(ANSWER,"Who should i send the message to?");
                    
                }
            }
            System.out.println("got here");
        }
    }

    void handle_sms_message() {
        say(QUESTION,com);
       
        if (WordInList(com,phrases.Cancelwords)) {
            whatAfterResult = "normal";
            say(ANSWER,"Ok canceled");
            
        } else {
            message = com;
            AlertDialog.Builder send_sms_alert = new AlertDialog.Builder(Main1.this);
            send_sms_alert.setTitle(contact);
            send_sms_alert.setMessage(message);
            send_sms_alert.setIcon(pic);
            send_sms_alert.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    sendSMS(phoneNumber, message);
                }
            });
            send_sms_alert.show();
        }
        whatAfterResult = "normal";

    }

    void handle_to_youtube() {
        whatAfterResult = "normal";
        int numWords = com.split(" ").length;
        if (numWords >= 1) {
            String arr[] = com.split(" ", com.length());
            theRest = arr[0];
            String url = "https://m.youtube.com/results?search_query=" + theRest;
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
            say(ANSWER,"youtubing...");
            
        } else {
            whatAfterResult = "to_tube";
            say(ANSWER,"what do you want me to tube?");
            
        }

    }

    void handle_image_search() {

    }

    void handle_open_app() {
        whatAfterResult = "normal";
        if (com.toLowerCase().contains("music")) {
            com = "open google play music";
        }
        if (com.toLowerCase().contains("picture") || com.toLowerCase().contains("photo")) {
            com = "open camera";
        }
        theRest = com.toLowerCase();
        if (theRest.equals("what's up")) {
            theRest = "whatsapp";
        }
        if (theRest.equals("google plus")) {
            theRest = "google+";
        }
        if (theRest.equals("ways")) {
            theRest = "waze";
        }
        if (theRest.equals("move it")) {
            theRest = "moovit";
        }
        if (theRest.equals("assist me")) {
            theRest = "assist-me";
        }
        final PackageManager pm = getPackageManager();
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);
        for (ApplicationInfo packageInfo : packages) {
            appicon = packageInfo.loadIcon(getPackageManager());
            appname = packageInfo.loadLabel(getPackageManager()).toString().toLowerCase();
            if (packageInfo.loadLabel(getPackageManager()).toString().toLowerCase().equals(theRest)) {
                PackageName = packageInfo.packageName.toString();
                foundapp = true;
                numbers_of_apps_found += 1;
                if (donotshowagain == true) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(Main1.this);
                    say(ANSWER,"Found! " + numbers_of_apps_found);
                   
                    alert.setTitle("Open " + theRest);
                    alert.setIcon(appicon);
                    check = new CheckBox(this);
                    check.setText("Do not show again");
                    alert.setMessage("Are your sure you want to exit assist me and open " + theRest + "?");
                    alert.setCancelable(false);
                    alert.setView(check);
                    alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            say(ANSWER,"opening " + theRest);
                           
                            Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage(PackageName);
                            startActivity(LaunchIntent);
                            if (check.isChecked()) {
                                donotshowagain = false;
                                SharedPreferences.Editor donotshowagainpref = getSharedPreferences("Preferences", Context.MODE_PRIVATE).edit();
                                donotshowagainpref.putBoolean("donotshowagain", donotshowagain);
                                donotshowagainpref.commit();
                            } else {
                                donotshowagain = true;
                                SharedPreferences.Editor donotshowagainpref = getSharedPreferences("Preferences", Context.MODE_PRIVATE).edit();
                                donotshowagainpref.putBoolean("donotshowagain", donotshowagain);
                                donotshowagainpref.commit();
                            }
                        }
                    });
                    if (numbers_of_apps_found > 1) {
                        alert.setTitle("I found several apps for you with this name");
                        negativebutton = "Next";
                    }
                    alert.setNegativeButton(negativebutton, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            if (numbers_of_apps_found > 1) {
                                numbers_of_apps_found = -1;
                            } else {
                               
                                say(ANSWER,"Ok canceled");
                                
                            }
                        }
                    });
                    alert.show();
                } else {
                    say(ANSWER,"opening " + theRest);
                   
                    Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage(PackageName);
                    startActivity(LaunchIntent);
                }
            }
            if (!foundapp && packageInfo.loadLabel(getPackageManager()).toString().toLowerCase().contains(theRest)) {
                foundapp = true;
                AlertDialog.Builder alert = new AlertDialog.Builder(Main1.this);
                alert.setTitle("Assist-Me");
                alert.setMessage("Couldn't find the spoken app, did you mean one of those?");
                ScrollView scrollview = new ScrollView(this);
                LinearLayout linearLayout = new LinearLayout(this);
                linearLayout.addView(scrollview);
                alert.setView(linearLayout);
                if (numbers_of_apps_found < 5) {
                    PackageName = packageInfo.packageName.toString();
                    foundapp = true;
                    numbers_of_apps_found += 1;
                    Button app = new Button(this);
                    linearLayout.addView(app);
                    app.setText(packageInfo.loadLabel(getPackageManager()).toString());
                    alert.setIcon(packageInfo.loadIcon(getPackageManager()));
                    app.setId(numbers_of_apps_found);
                    app.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage(PackageName);
                            startActivity(LaunchIntent);
                        }
                    });
                }
                alert.setView(linearLayout);
                alert.show();
            }
        }

        if (!foundapp) {
            say(ANSWER,"App not found");
        }

    }

    void handle_music() {
        whatAfterResult = "normal";
        Intent mus = new Intent(getApplicationContext(), music.class);
        mus.putExtra("KEY", com);
        startActivity(mus);
    }

    public void initPartialTextvidw(TextView partialText){
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(Math.round(getResources().getDimension(R.dimen.textBackgroundSize)), ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.RIGHT;
        partialText.setBackgroundResource(R.drawable.text_siri_text);
        partialText.setPadding(Math.round(getResources().getDimension(R.dimen.paddingText)), Math.round(getResources().getDimension(R.dimen.paddingText)), Math.round(getResources().getDimension(R.dimen.paddingText)), Math.round(getResources().getDimension(R.dimen.paddingText)));
        partialText.setGravity(View.TEXT_ALIGNMENT_CENTER);
        partialText.setLayoutParams(lp);
        partialText.setTextSize(20);
        partialText.setTextColor(Color.parseColor("#FFFFFF"));
        partialText.setMovementMethod(new ScrollingMovementMethod());
        LinearLayout container = (LinearLayout) findViewById(R.id.textScroller);
        container.addView(partialText);
        create(partialText,container);
    }

    private class JSONWeatherTask extends AsyncTask<String, Void, Weather> {

        @Override
        protected Weather doInBackground(String... params) {
            Weather weather = new Weather();
            String data = ((new WeatherHttpClient()).getWeatherData(params[0], params[1]));

            try {
                if (weather != null) {
                    weather = JSONWeatherParser.getWeather(data);
                    System.out.println("Weather [" + weather + "]");
                    // Let's retrieve the icon
                    weather.iconData = ((new WeatherHttpClient()).getImage(weather.currentCondition.getIcon()));
                } else {
                    Toast.makeText(getApplicationContext(), "ERROR LOADING WEATHER", Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return weather;

        }


        @Override
        protected void onPostExecute(Weather weather) {
            super.onPostExecute(weather);
            String tmp = ("" + Math.round((weather.temperature.getTemp() - 275.15)));
            String condition = (weather.currentCondition.getCondition() + " (" + weather.currentCondition.getDescr() + ")");
            if (pdialog.isShowing()) {
                pdialog.hide();
            }
            if (tmp.equals("-275")) {
                say(ANSWER,"City not found");
                
            } else {
                RelativeLayout back_info = (RelativeLayout) findViewById(R.id.one);
                back_info.setVisibility(View.VISIBLE);
                if (!sharedPrefs.getString("prefUnit", "").equals("Celsius")) {
                    say(ANSWER,"It's " + Math.round(Integer.valueOf(tmp) * 1.8 + 32) + " degrees fahrenheit and the condition is " + condition + " in " + cityName);
                    info_one.setText(String.valueOf(Math.round(Integer.valueOf(tmp) * 1.8 + 32)) + "°");
                } else {
                    say(ANSWER,"It's " + tmp + " degrees celsius and the condition is " + condition + " in " + cityName);
                    info_one.setText((tmp) + "°");
                }
                if (condition.contains("rain")) {
                    imgview.setImageResource(R.drawable.rain);
                } else if (condition.contains("light")) {
                    imgview.setImageResource(R.drawable.lightning);
                } else if (condition.contains("cloud")) {
                    imgview.setImageResource(R.drawable.cloudy);
                } else if (condition.contains("wind")) {
                    imgview.setImageResource(R.drawable.wind);
                } else {
                    imgview.setImageResource(R.drawable.sun);
                }
                
			/*

			temp.setText("" + Math.round((weather.temperature.getTemp() - 275.15)) + "�C");
			hum.setText("" + weather.currentCondition.getHumidity() + "%");
			press.setText("" + weather.currentCondition.getPressure() + " hPa");
			windSpeed.setText("" + weather.wind.getSpeed() + " mps");
			windDeg.setText("" + weather.wind.getDeg() + "�");
			*/
            }

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            ArrayList<String> matches = data.getStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS);
            // missing data, install it
            com = matches.get(0);

            listening = false;
            if (siri_bool) {
                btnSpeak.setBackgroundResource(R.drawable.siri_icon);
            } else {
                btnSpeak.setBackgroundResource(R.drawable.icon);
            }
            FrameLayout.LayoutParams FLLP = new FrameLayout.LayoutParams(68, 68);
            FLLP.gravity = Gravity.CENTER;
            backgroundforrms.setLayoutParams(FLLP);
            edit.setText("");
            Log.d("Speech", "onResults");
            sr.stopListening();
            if (com.equals(null) || com.equals("")) {
                btnSpeak.setBackgroundResource(R.drawable.error);
                Toast.makeText(getApplicationContext(), "Come Again?", Toast.LENGTH_LONG).show();
            } else {
             /*   TextView textView = new TextView(Main1.this);

                Typeface font = Typeface.createFromAsset(getAssets(), "roboto.ttf");
                textView.setTypeface(font);
                initPartialTextvidw(textView);*/
                char first = Character.toUpperCase(com.charAt(0));
                say(QUESTION, first + com.substring(1));
                if (whatAfterResult.equals("normal")) {
                    startAfterResult();
                } else if (whatAfterResult.equals("to_search")) {
                    handle_to_search();
                } else if (whatAfterResult.equals("to_call")) {
                    handle_to_call();
                } else if (whatAfterResult.equals("to_youtube")) {
                    handle_to_youtube();
                } else if (whatAfterResult.equals("open_app")) {
                    handle_open_app();
                } else if (whatAfterResult.equals("music")) {
                    handle_music();
                } else if (whatAfterResult.equals("recipient")) {
                    handle_sms_contact();
                } else if (whatAfterResult.equals("message")) {
                    handle_sms_message();
                } else if (whatAfterResult.equals(WHAT_TO_REMIND)) {
                    handle_what_to_remind();
                }
                final ScrollView scrollview = ((ScrollView) findViewById(R.id.scroller));
                scrollview.post(new Runnable() {
                    @Override
                    public void run() {
                        scrollview.fullScroll(ScrollView.FOCUS_DOWN);
                    }
                });
            }

        }
    }



        private class JSONForecastWeatherTask extends AsyncTask<String, Void, WeatherForecast> {

        @Override
        protected WeatherForecast doInBackground(String... params) {
            try {
                String data = ((new WeatherHttpClient()).getForecastWeatherData(params[0], params[1], params[2]));
                WeatherForecast forecast = new WeatherForecast();
                try {
                    forecast = JSONWeatherParser.getForecastWeather(data);
                    System.out.println("Weather [" + forecast + "]");
                    // Let's retrieve the icon
                    //weather.iconData = ( (new WeatherHttpClient()).getImage(weather.currentCondition.getIcon()));

                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (RuntimeException e) {
                    e.printStackTrace();
                }
                return forecast;

            } catch (RuntimeException e) {
                e.printStackTrace();
            }
            return new WeatherForecast();
        }
    }

    private void sendSMS(String phoneNumber, String message) {
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, null, null);
    }

    private void setMobileDataEnabled(Context context, boolean enabled) throws ClassNotFoundException, IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
        final ConnectivityManager conman = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final Class conmanClass = Class.forName(conman.getClass().getName());
        final Field connectivityManagerField = conmanClass.getDeclaredField("mService");
        connectivityManagerField.setAccessible(true);
        final Object connectivityManager = connectivityManagerField.get(conman);
        final Class connectivityManagerClass = Class.forName(connectivityManager.getClass().getName());
        final Method setMobileDataEnabledMethod = connectivityManagerClass.getDeclaredMethod("setMobileDataEnabled", Boolean.TYPE);
        setMobileDataEnabledMethod.setAccessible(true);

        setMobileDataEnabledMethod.invoke(connectivityManager, enabled);
    }

    //Voice Recognitions Configiriations
    private static boolean isSpeechRecognitionActivityPresented(Activity callerActivity) {
        try {
            // getting an instance of package manager
            PackageManager pm = callerActivity.getPackageManager();
            // a list of activities, which can process speech recognition Intent
            List activities = pm.queryIntentActivities(new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);

            if (activities.size() != 0) {    // if list not empty
                return true;                // then we can recognize the speech
            }
        } catch (Exception e) {

        }

        return false; // we have no activities to recognize the speech
    }

    /*
    english shit
    */
    void startVoiceRecognitionActivityenglish() {
        if (recognizer != null) {
            recognizer.stop();
        }
        // stopService(new Intent(getApplicationContext(),VoiceService.class));
        run(Main1.this);
    }

    public void run(Activity callingActivity) {
        // check if there is recognition Activity
        if (isSpeechRecognitionActivityPresented(callingActivity) == true) {
            // if yes – running recognition
            sr = SpeechRecognizer.createSpeechRecognizer(getApplicationContext());
            test_voice_recognitiona listener = new test_voice_recognitiona();
            sr.setRecognitionListener(listener);
            Intent fl = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            fl.putExtra("android.speech.extra.LANGUAGE", "en-US");
            fl.putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true);
            fl.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,
                    this.getPackageName());
            sr.startListening(fl);
        } else {
            Toast.makeText(getApplicationContext(), "You have to install a proper voice recognition", Toast.LENGTH_SHORT);
            final AlertDialog.Builder alert = new AlertDialog.Builder(Main1.this)
                    .setTitle("Install voice recognition")
                    .setMessage("You must install voice recognition in order to use Assist-Me")
                    .setCancelable(false)
                    .setPositiveButton("Okay let's do it", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                try {
                                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.google.android.googlequicksearchbox"));
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                                    startActivity(intent);
                                }catch (ActivityNotFoundException e){
                                    e.printStackTrace();
                                    Toast.makeText(getApplicationContext(),"Play store not found",Toast.LENGTH_LONG).show();
                                }
                            } else {
                                try{
                                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.google.android.voicesearch"));
                                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                                startActivity(intent);
                                }catch (ActivityNotFoundException e){
                                    e.printStackTrace();
                                    Toast.makeText(getApplicationContext(),"Play store not found",Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                    });
            alert.show();


        }
    }


    void englishafterresult() throws Settings.SettingNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, ClassNotFoundException {
        //    startService(new Intent(getApplicationContext(),VoiceService.class));
        numbers_of_apps_found = 0;
        findViewById(R.id.one).setVisibility(View.INVISIBLE);
        info_one.setText("");
        imgview.setImageResource(R.drawable.trans);
        appicon = null;
        foundapp = false;
        spoke = null;
        mobileavi = false;
        homeavi = false;
        otheravi = false;
        workavi = false;
        sent = false;
        if (edit.getText().toString().equals("Type Here") || edit.getText().toString().equals("")) {
            if (siri_bool) {
                btnSpeak.setBackgroundResource(R.drawable.siri_icon);
            } else {
                btnSpeak.setBackgroundResource(R.drawable.btnspeek);
            }
        } else {
            if (!siri_bool) {
                btnSpeak.setBackgroundResource(R.drawable.enter);
            }
        }
        if (com.toLowerCase().startsWith("send sms to")) {
            int numWords = com.split(" ").length;
            if (numWords < 4) {
                if (bitchstring == true) {
                    say(ANSWER,"Bitch who do i send it??");
                    
                } else {
                    say(ANSWER,"Whom should i send the message?");
                    
                }
                    whatAfterResult = "recipient";
                    boolean x = false;
                    while (!x) {
                        if (!tts.isSpeaking()) {
                            x = true;
                            listenmulitlang();
                        }
                    }

            } else {
                String arr[] = com.split(" ", 4);
                String firstWord = arr[0];
                contact = arr[3];
                Toast.makeText(getApplicationContext(),contact,Toast.LENGTH_SHORT).show();
                ContentResolver cr = getContentResolver();
                Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI,
                        null, "DISPLAY_NAME" + " LIKE '%" + contact + "%'", null,
                        ContactsContract.CommonDataKinds.Phone.LAST_TIME_CONTACTED + " DESC");
                if (cursor.moveToFirst()) {
                    String contactId =
                            cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                    Cursor phones = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId, null, null);
                    while (phones.moveToNext()) {
                        phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        int type = phones.getInt(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));
                        pic = phones.getInt(phones.getColumnIndex(ContactsContract.CommonDataKinds.Photo.PHOTO));
                        switch (type) {
                            case ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE:
                                say(ANSWER,"What is your message?");
                               
                                sent = true;
                                
                                whatAfterResult = "message";
                                boolean x = false;
                                while (!x) {
                                    if (!tts.isSpeaking()) {
                                        x = true;
                                        listenmulitlang();
                                    }
                                }
                        }
                    }
                    phones.close();
                }
                cursor.close();
                if (sent == false) {
                    if (bitchstring) {
                        say(ANSWER,"BITCH you don't have this contact!, Try again");
                        Log.d("contact name ",contact);
                    } else {
                        say(ANSWER,"No such contact with this name " + contact + " please try again");
                    }
                    whatAfterResult = "recipient";
                    
                    boolean x = false;
                    while (!x) {
                        if (!tts.isSpeaking()) {
                            x = true;
                            listenmulitlang();
                        }
                    }
                }
            }
        }
        else if (com.toLowerCase().startsWith("send sms")) {
            int numWords = com.split(" ").length;
            if (numWords < 3) {
                if (bitchstring == true) {
                    say(ANSWER,"Bitch who do i message??");
                } else {
                    say(ANSWER,"Whom should i send the message?");
                }
                    
                    whatAfterResult = "recipient";
                    boolean x = false;
                    while (!x) {
                        if (!tts.isSpeaking()) {
                            x = true;
                            listenmulitlang();
                        }
                    }
            } else {
                String arr[] = com.split(" ", 4);
                String firstWord = arr[0];
                contact = arr[3];
                ContentResolver cr = getContentResolver();
                Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI,
                        null, "DISPLAY_NAME" + " LIKE '%" + contact + "%'", null,
                        ContactsContract.CommonDataKinds.Phone.LAST_TIME_CONTACTED + " DESC");
                if (cursor.moveToFirst()) {
                    String contactId =
                            cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                    Cursor phones = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId, null, null);
                    while (phones.moveToNext()) {
                        phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        int type = phones.getInt(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));
                        pic = phones.getInt(phones.getColumnIndex(ContactsContract.CommonDataKinds.Photo.PHOTO));
                        switch (type) {
                            case ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE:
                                say(ANSWER,"What is your message?");
                               
                                sent = true;
                                
                                whatAfterResult = "message";
                                boolean x = false;
                                while (!x) {
                                    if (!tts.isSpeaking()) {
                                        x = true;
                                        listenmulitlang();
                                    }
                                }
                            /*    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) //At least KitKat
                                {
                                    String text = " ";
                                    String defaultSmsPackageName = Telephony.Sms.getDefaultSmsPackage(this); //Need to change the build to API 19

                                    Intent intent = new Intent(Intent.ACTION_VIEW);
                                    intent.putExtra("address", smsnumber);
                                    intent.putExtra("sms_body", " ");
                                    intent.setData(Uri.parse("smsto:" + smsnumber));
                                    sent = true;
                                    if (bitchstring) {
                                        say(ANSWER,"Bitch i hate you but im gonna send this sms");
                                    } else {
                                        say(ANSWER,"Sending sms to " + contact);
                                    }
                                    txtText2.setVisibility(0);


                                    if (defaultSmsPackageName != null)//Can be null in case that there is no default, then the user would be able to choose any app that support this intent.
                                    {
                                        intent.setPackage(defaultSmsPackageName);
                                    }
                                    startActivity(intent);

                                } else //For early versions, do what worked for you before.
                                {
                                    String text = "";
                                    if (bitchstring) {
                                        say(ANSWER,"Bitch i hate you but im gonna send this sms");
                                    } else {
                                        say(ANSWER,"Sending sms to " + contact);
                                    }
                                    txtText2.setVisibility(0);
                                    Intent intent = new Intent(Intent.ACTION_VIEW);
                                    intent.putExtra("address", smsnumber);
                                    intent.putExtra("sms_body", " ");
                                    intent.setData(Uri.parse("smsto:" + smsnumber));
                                    startActivity(intent);
                                    sent = true;
                                }
                                break;
                                */
                        }
                    }
                    phones.close();
                }
                cursor.close();
                if (sent == false) {
                    if (bitchstring) {
                        say(ANSWER,"BITCH you don't have this contact!, Try again");
                    } else {
                        say(ANSWER,"No such contact with this name " + contact + " please try again");
                    }
                    whatAfterResult = "recipient";
                    
                    boolean x = false;
                    while (!x) {
                        if (!tts.isSpeaking()) {
                            x = true;
                            listenmulitlang();
                        }
                    }
                }
            }
        }
        else if(false) {
             if (com.toLowerCase().startsWith("remind me")) {
                if (com.length() == 9 || !com.substring(com.lastIndexOf("remind me ") + 10).replaceAll(" ", "").startsWith("to") || com.substring(com.lastIndexOf("remind me to") + 12).replaceAll(" ", "").isEmpty()) {
                    if (com.matches(".*\\d.*") || StringContainsFromList(com, phrases.numbers)) {
                        if (com.matches(".*\\d.*")) {
                            time_to_remind = com.replaceAll("[\\D]", "");
                        } else {
                            for (int i = 0; i < phrases.numbers.length; i++) {
                                if (com.contains(phrases.numbers[i])) {
                                    time_to_remind = String.valueOf(phrases.numbers[i]);
                                }
                            }
                        }
                    }
                    say(ANSWER, "What would you like me to remind you?");
                    whatAfterResult = WHAT_TO_REMIND;

                    boolean x = false;
                    while (!x) {
                        if (!tts.isSpeaking()) {
                            x = true;
                            listenmulitlang();
                        }
                    }
                } else {
                    if (com.matches(".*\\d.*") || StringContainsFromList(com, phrases.numbers)) {
                        if (com.matches(".*\\d.*")) {
                            time_to_remind = com.replaceAll("[\\D]", "");
                        } else {
                            for (int i = 0; i < phrases.numbers.length; i++) {
                                if (com.contains(phrases.numbers[i])) {
                                    time_to_remind = String.valueOf(phrases.numbers[i]);
                                }
                            }
                        }
                        String
                                what_to_remind = com;
                        what_to_remind = what_to_remind.replaceAll(time_to_remind, "").replaceAll("remind me to", "").replaceAll("p.m.", "").replaceAll("pm", "")
                                .replaceAll("am", "").replaceAll("a.m.", "").replaceAll("at", "").replaceAll("in", "").replaceAll("on", "").replaceAll("p.m", "").replaceAll("a.m", "");
                        if (what_to_remind.startsWith(" ")) {
                            what_to_remind = what_to_remind.substring(1);
                        }
                        if ((com.contains("p.m.") || com.contains("pm"))) {
                            time_to_remind = time_to_remind + " p.m.";
                        }
                        if (com.contains("a.m.") || com.contains(" am")) {
                            time_to_remind = time_to_remind + " a.m.";
                        }
                        Calendar c = Calendar.getInstance();
                        int date = c.get(Calendar.DAY_OF_MONTH);

                        showReminder(time_to_remind, what_to_remind, date);
                    } else {
                        say(ANSWER, "When should I remind you?");
                        whatAfterResult = WHEN_TO_REMIND;

                        boolean x = false;
                        while (!x) {
                            if (!tts.isSpeaking()) {
                                x = true;
                                listenmulitlang();
                            }
                        }
                    }
                }

            }
        }
        else if (com.toLowerCase().startsWith("play")) {
            int numWords = com.split(" ").length;
            if (numWords >= 2) {
                String arr[] = com.split(" ", 2);
                String firstWord = arr[0];
                theRest = arr[1];
                say(ANSWER,"Looking for " + theRest + " in your music list");
                
                Intent mus = new Intent(getApplicationContext(), music.class);
                mus.putExtra("KEY", theRest);
                startActivity(mus);
            } else {
                say(ANSWER,"What song do you want me to play?");
                whatAfterResult = "music";
                
                boolean x = false;
                while (!x) {
                    if (!tts.isSpeaking()) {
                        x = true;
                        listenmulitlang();
                    }
                }
            }
        } else if (com.toLowerCase().contains("battery")||com.toLowerCase().contains("batte")) {
            Intent batteryIntent = registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
            int level = batteryIntent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int scale = batteryIntent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
            int plugged = batteryIntent.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0);
            float temperature = batteryIntent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0) / 10;
            if (temperature > 40) {
                say(ANSWER,"battery level is " + String.valueOf(level) + "% i noticed your battery temperature is too high");
                
            }
            if (temperature == 100) {
                say(ANSWER,"its over 9000");
                
            }
            say(ANSWER,"battery level is " + String.valueOf(level) + "% and its temperature is " + String.valueOf(temperature) + " celsius");
            
        } else if (com.toLowerCase().startsWith("sms") || com.toLowerCase().startsWith("message")) {
            int numWords = com.split(" ").length;
            if (numWords < 2) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) //At least KitKat
                {
                    String text = " ";
                    String defaultSmsPackageName = Telephony.Sms.getDefaultSmsPackage(this); //Need to change the build to API 19

                    Intent sendIntent = new Intent(Intent.ACTION_SEND);
                    sendIntent.setType("text/plain");
                    sendIntent.putExtra(Intent.EXTRA_TEXT, text);
                    animate("out");
                    finish();

                    if (defaultSmsPackageName != null)//Can be null in case that there is no default, then the user would be able to choose any app that support this intent.
                    {
                        sendIntent.setPackage(defaultSmsPackageName);
                    }
                    startActivity(sendIntent);

                } else //For early versions, do what worked for you before.
                {
                    String text = " ";
                    Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                    sendIntent.setData(Uri.parse("sms:"));
                    sendIntent.putExtra("address", contact);
                    sendIntent.putExtra("sms_body", text);
                    startActivity(sendIntent);
                }
            } else {
                String arr[] = com.split(" ", 2);
                String firstWord = arr[0];
                theRest = arr[1];
                ContentResolver cr = getContentResolver();
                Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null,
                        "DISPLAY_NAME = '" + theRest + "'", null, null);
                if (cursor.moveToFirst()) {
                    String contactId =
                            cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                    Cursor phones = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId, null, null);
                    while (phones.moveToNext()) {
                        String number = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        int type = phones.getInt(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));
                        switch (type) {
                            case ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE:
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) //At least KitKat
                                {
                                    String text = " ";
                                    String defaultSmsPackageName = Telephony.Sms.getDefaultSmsPackage(this); //Need to change the build to API 19

                                    Intent sendIntent = new Intent(Intent.ACTION_SEND);
                                    sendIntent.putExtra("address", number);
                                    sendIntent.setType("text/plain");
                                    sendIntent.putExtra(Intent.EXTRA_TEXT, text);
                                    sent = true;

                                    if (defaultSmsPackageName != null)//Can be null in case that there is no default, then the user would be able to choose any app that support this intent.
                                    {
                                        sendIntent.setPackage(defaultSmsPackageName);
                                    }
                                    startActivity(sendIntent);

                                } else //For early versions, do what worked for you before.
                                {
                                    String text = "";
                                    Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                                    sendIntent.setData(Uri.parse("sms:"));
                                    sendIntent.putExtra("address", number);
                                    sendIntent.putExtra("sms_body", text);
                                    say(ANSWER,"Sending sms to " + theRest + ", Bitch");
                                   
                                    startActivity(sendIntent);
                                    sent = true;
                                }
                                break;
                        }
                    }
                    phones.close();
                }
                cursor.close();
                if (sent == false) {
                    if (bitchstring) {
                        say(ANSWER,"BITCH no one is this stupid, you dont have a contact with this name");
                    } else {
                        say(ANSWER,"No such contact with this name " + theRest);
                    }
                   
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) //At least KitKat
                    {
                        String text = "";
                        String defaultSmsPackageName = Telephony.Sms.getDefaultSmsPackage(this); //Need to change the build to API 19

                        Intent sendIntent = new Intent(Intent.ACTION_SEND);
                        sendIntent.setType("text/plain");
                        sendIntent.putExtra(Intent.EXTRA_TEXT, text);
                        if (defaultSmsPackageName != null)//Can be null in case that there is no default, then the user would be able to choose any app that support this intent.
                        {
                            sendIntent.setPackage(defaultSmsPackageName);
                        }
                        startActivity(sendIntent);

                    } else //For early versions, do what worked for you before.
                    {
                        String text = "";
                        Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                        sendIntent.setData(Uri.parse("sms:"));
                        sendIntent.putExtra("sms_body", text);
                        startActivity(sendIntent);
                    }
                }
            }
        }
        else if (com.toLowerCase().contains("weather in ")) {
            cityName = com.substring(com.lastIndexOf("weather in ") + 11);
            try {
                ConnectivityManager dataManager;
                dataManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                Method dataMtd = null;
                dataMtd = ConnectivityManager.class.getDeclaredMethod("setMobileDataEnabled", boolean.class);

                dataMtd.setAccessible(true);
                dataMtd.invoke(dataManager, true);
                setMobileDataEnabled(getApplicationContext(), true);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (!isNetworkAvailable()) {
                AlertDialog.Builder networkAlert = new AlertDialog.Builder(Main1.this);
                networkAlert.setTitle("No internet connection");
                networkAlert.setMessage("Please enable internet connection");
                networkAlert.setPositiveButton("Turn on wifi", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                        wifiManager.setWifiEnabled(true);
                    }
                });
                networkAlert.setNegativeButton("Turn on mobile data", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ConnectivityManager dataManager;
                        dataManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                        Method dataMtd = null;
                        try {
                            dataMtd = ConnectivityManager.class.getDeclaredMethod("setMobileDataEnabled", boolean.class);

                            dataMtd.setAccessible(true);
                            dataMtd.invoke(dataManager, true);
                            setMobileDataEnabled(getApplicationContext(), true);

                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();

                        } catch (InvocationTargetException e1) {
                            e1.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                        } catch (IllegalAccessException e1) {
                            e1.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                        } catch (NoSuchFieldException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                        }
                    }

                });
                networkAlert.setCancelable(false);
                networkAlert.show();

            } else {
                weatherlookerrunning = false;
                cityName = com.substring(com.lastIndexOf("weather in ") + 11);
                pdialog = new ProgressDialog(Main1.this);
                if (!weatherlookerrunning) {
                    weatherlookerrunning = true;
                    String city = cityName;
                    String lang = "en";


                    JSONWeatherTask task = new JSONWeatherTask();
                    task.execute(new String[]{city, lang});

                    JSONForecastWeatherTask task1 = new JSONForecastWeatherTask();
                    task1.execute(new String[]{city, lang, forecastDaysNum});
                }
            }

        }
        else if (com.toLowerCase().contains("favorite app") || com.toLowerCase().contains("best app") || com.toLowerCase().contains("most used")) {
            favorite_apps info = new favorite_apps(Main1.this);
            info.open();
            String data = info.getData();
            String[] lines = data.split(System.getProperty("line.separator"));
            String appName_temp = lines[lines.length - 1];
            final String appName = appName_temp.substring(appName_temp.lastIndexOf("|| ") + 3);
            if (appName != null) {
                try {
                    ApplicationInfo app = this.getPackageManager().getApplicationInfo(appName, 0);
                    CharSequence applicationLabel = getPackageManager().getApplicationLabel(app);
                    say(ANSWER,"your most used app is " + applicationLabel);

                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                say(ANSWER,"I can't tell yet, give me a few days");

            }
            info.close();

        }
        else if (com.toLowerCase().contains("weather")) {
            weatherlookerrunning = false;
            if (bitchstring) {
                say(ANSWER,"Who da fuck gives a shit bitch? who the hell you think you are talking to me like this? thats it im bricking your phone. BITCH");
            } else {
                say(ANSWER,"Loading weather for your location");
            }
            try {
                ConnectivityManager dataManager;
                dataManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                Method dataMtd = null;
                dataMtd = ConnectivityManager.class.getDeclaredMethod("setMobileDataEnabled", boolean.class);

                dataMtd.setAccessible(true);
                dataMtd.invoke(dataManager, true);
                setMobileDataEnabled(getApplicationContext(), true);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

           /* Intent intent = new Intent();
            intent.setAction(Intent.ACTION_WEB_SEARCH);
            intent.putExtra(SearchManager.QUERY, "Weather");
            startActivity(intent);*/
            Boolean flag;
            locationMangaer = (LocationManager)
                    getSystemService(Context.LOCATION_SERVICE);
            flag = displayGpsStatus();
            if (flag) {
                if (!isNetworkAvailable()) {
                    AlertDialog.Builder networkAlert = new AlertDialog.Builder(Main1.this);
                    networkAlert.setTitle("No internet connection");
                    networkAlert.setMessage("Please enable internet connection");
                    networkAlert.setPositiveButton("Turn on wifi", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                            wifiManager.setWifiEnabled(true);
                            LocationManager locationManager = (LocationManager)
                                    getSystemService(Context.LOCATION_SERVICE);
                            LocationListener locationListener = new MyLocationListener();
                            locationManager.requestLocationUpdates(
                                    LocationManager.GPS_PROVIDER, 5000, 10, locationListener);
                            locationManager.requestLocationUpdates(
                                    LocationManager.NETWORK_PROVIDER, 5000, 10, locationListener);
                            pdialog = new ProgressDialog(Main1.this);
                            pdialog.setCancelable(false);
                            pdialog.setTitle("Loading please wait");
                            pdialog.setMessage("Looking for your location");
                            pdialog.show();
                        }
                    });
                    networkAlert.setNegativeButton("Turn on mobile data", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ConnectivityManager dataManager;
                            dataManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                            Method dataMtd = null;
                            try {
                                dataMtd = ConnectivityManager.class.getDeclaredMethod("setMobileDataEnabled", boolean.class);

                                dataMtd.setAccessible(true);
                                dataMtd.invoke(dataManager, true);
                                setMobileDataEnabled(getApplicationContext(), true);
                                LocationManager locationManager = (LocationManager)
                                        getSystemService(Context.LOCATION_SERVICE);
                                LocationListener locationListener = new MyLocationListener();
                                locationManager.requestLocationUpdates(
                                        LocationManager.GPS_PROVIDER, 5000, 10, locationListener);
                                locationManager.requestLocationUpdates(
                                        LocationManager.NETWORK_PROVIDER, 5000, 10, locationListener);
                                pdialog = new ProgressDialog(Main1.this);
                                pdialog.setCancelable(false);
                                pdialog.setTitle("Loading please wait");
                                pdialog.setMessage("Looking for your location");
                                pdialog.show();
                            } catch (NoSuchMethodException e) {
                                e.printStackTrace();
                                LocationManager locationManager = (LocationManager)
                                        getSystemService(Context.LOCATION_SERVICE);
                                LocationListener locationListener = new MyLocationListener();
                                locationManager.requestLocationUpdates(
                                        LocationManager.GPS_PROVIDER, 5000, 10, locationListener);
                                locationManager.requestLocationUpdates(
                                        LocationManager.NETWORK_PROVIDER, 5000, 10, locationListener);
                                pdialog = new ProgressDialog(Main1.this);
                                pdialog.setCancelable(false);
                                pdialog.setTitle("Loading please wait");
                                pdialog.setMessage("Looking for your location");
                                pdialog.show();
                            } catch (InvocationTargetException e1) {
                                e1.printStackTrace();
                                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                            } catch (IllegalAccessException e1) {
                                e1.printStackTrace();
                                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                            } catch (NoSuchFieldException e) {
                                e.printStackTrace();
                                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                            }
                        }

                    });
                    networkAlert.setCancelable(false);
                    networkAlert.show();

                } else {
                    LocationManager locationManager = (LocationManager)
                            getSystemService(Context.LOCATION_SERVICE);
                    LocationListener locationListener = new MyLocationListener();
                    locationManager.requestLocationUpdates(
                            LocationManager.GPS_PROVIDER, 5000, 10, locationListener);
                    locationManager.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER, 5000, 10, locationListener);
                    pdialog = new ProgressDialog(Main1.this);
                    pdialog.setCancelable(false);
                    pdialog.setTitle("Loading please wait");
                    pdialog.setMessage("Looking for your location");
                    pdialog.show();
                }
             /*
                int x = 0;
                while(!isNetworkAvailable()&&x<8){
                    android.os.SystemClock.sleep(150);
                    x++;
                    if(isNetworkAvailable()){
                        locationListener[0] = new MyLocationListener();

                        locationMangaer.requestLocationUpdates(LocationManager
                                .GPS_PROVIDER, 5000, 10, locationListener[0]);
                        pdialog = new ProgressDialog(Main1.this);
                        pdialog.setCancelable(false);
                        pdialog.setTitle("Loading please wait");
                        pdialog.setMessage("be patient");
                        pdialog.show();
                    }
                }
               if(x>=4) {
                    if (!isNetworkAvailable()) {
                        AlertDialog.Builder networkAlert = new AlertDialog.Builder(Main1.this);
                        networkAlert.setTitle("No internet connection");
                        networkAlert.setMessage("Please enable internet connection");
                        networkAlert.setPositiveButton("Turn on wifi", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                                wifiManager.setWifiEnabled(true);
                                locationListener[0] = new MyLocationListener();

                                locationMangaer.requestLocationUpdates(LocationManager
                                        .GPS_PROVIDER, 5000, 10, locationListener[0]);
                                pdialog = new ProgressDialog(Main1.this);
                                pdialog.setCancelable(false);
                                pdialog.setTitle("Loading please wait");
                                pdialog.setMessage("be patient");
                                pdialog.show();
                            }
                        });
                        networkAlert.setNegativeButton("Turn on mobile data", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ConnectivityManager dataManager;
                                dataManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                                Method dataMtd = null;
                                try {
                                    dataMtd = ConnectivityManager.class.getDeclaredMethod("setMobileDataEnabled", boolean.class);

                                    dataMtd.setAccessible(true);
                                    dataMtd.invoke(dataManager, true);
                                    setMobileDataEnabled(getApplicationContext(), true);
                                    locationListener[0] = new MyLocationListener();

                                    locationMangaer.requestLocationUpdates(LocationManager
                                            .GPS_PROVIDER, 5000, 10, locationListener[0]);
                                    pdialog = new ProgressDialog(Main1.this);
                                    pdialog.setCancelable(false);
                                    pdialog.setTitle("Loading please wait");
                                    pdialog.setMessage("be patient");
                                    pdialog.show();
                                } catch (NoSuchMethodException e) {
                                    e.printStackTrace();
                                    locationListener[0] = new MyLocationListener();

                                    locationMangaer.requestLocationUpdates(LocationManager
                                            .GPS_PROVIDER, 5000, 10, locationListener[0]);
                                    pdialog = new ProgressDialog(Main1.this);
                                    pdialog.setCancelable(false);
                                    pdialog.setTitle("Loading please wait");
                                    pdialog.setMessage("be patient");
                                    pdialog.show();
                                } catch (InvocationTargetException e1) {
                                    e1.printStackTrace();
                                    Toast.makeText(getApplicationContext(), "Failed", 100).show();
                                } catch (IllegalAccessException e1) {
                                    e1.printStackTrace();
                                    Toast.makeText(getApplicationContext(), "Failed", 100).show();
                                } catch (ClassNotFoundException e) {
                                    e.printStackTrace();
                                    Toast.makeText(getApplicationContext(), "Failed", 100).show();
                                } catch (NoSuchFieldException e) {
                                    e.printStackTrace();
                                    Toast.makeText(getApplicationContext(), "Failed", 100).show();
                                }
                            }

                        });
                        networkAlert.setCancelable(false);
                        networkAlert.show();

                    }
                }*/
            } else {
                alertbox("Gps Status", "Your GPS is: OFF");
                say(ANSWER,"Cant find your location please turn on GPS");
                
            }

            //   startActivity(new Intent(getApplicationContext(),WeatherActivity.class));
            overridePendingTransition(R.anim.abc_slide_in_bottom, R.anim.abc_slide_out_top);
            
        }
        else if (com.toLowerCase().startsWith("google search")) {
            int numWords = com.split(" ").length;
            if (numWords >= 3) {
                String arr[] = com.split(" ", 3);
                String firstWord = arr[0];
                String secend = arr[1];
                theRest = arr[2];

                say(ANSWER,"searching...");
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY, theRest);
                startActivity(intent);
                
            } else {
                say(ANSWER,"What do you want me to search?");
                whatAfterResult = "to_search";
                
                boolean x = false;
                while (!x) {
                    if (!tts.isSpeaking()) {
                        x = true;
                        listenmulitlang();
                    }
                }
            }
        } else if ((com.toLowerCase().startsWith("google"))
                ) {
            int numWords = com.split(" ").length;
            if (numWords >= 2) {
                String arr[] = com.split(" ", 2);
                String firstWord = arr[0];
                theRest = arr[1];
                say(ANSWER,"searching...");
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY, theRest);
                startActivity(intent);
                
            } else {
                say(ANSWER,"What do you want me to search?");
                whatAfterResult = "to_search";
                
                boolean x = false;
                while (!x) {
                    if (!tts.isSpeaking()) {
                        x = true;
                        listenmulitlang();
                    }
                }
            }
        } else if ((com.toLowerCase().startsWith("search"))
                ) {
            int numWords = com.split(" ").length;
            if (numWords >= 2) {
                String arr[] = com.split(" ", 2);
                String firstWord = arr[0];
                theRest = arr[1];
                say(ANSWER,"searching...");
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY, theRest);
                startActivity(intent);
                
            } else {
                say(ANSWER,"What do you want me to search?");
                whatAfterResult = "to_search";
                
                boolean x = false;
                while (!x) {
                    if (!tts.isSpeaking()) {
                        x = true;
                        listenmulitlang();
                    }
                }
            }
        } else if (com.toLowerCase().contains("alarm") || com.toLowerCase().contains("clock")) {
            say(ANSWER,"Here Is The Alarm Clock");
            
            Intent i = new Intent(AlarmClock.ACTION_SET_ALARM);
            startActivity(i);
        }
        else if (com.toLowerCase().contains("what") && com.toLowerCase().contains("time")&&!com.matches(".*\\\\d.*")) {
            Calendar c = Calendar.getInstance();
            int time = c.get(Calendar.HOUR_OF_DAY);
            int min = c.get(Calendar.MINUTE);
            int sec = c.get(Calendar.SECOND);
            if (time > 12) {
                if (bitchstring) {
                    say(ANSWER,"Bitch thats the fucking time " + time + " " + min);
                } else {
                    say(ANSWER,"The Time Is " + time + ":" + min + "PM");
                }
            } else if (time < 12) {
                if (bitchstring) {
                    say(ANSWER,"Bitch thats the fucking time " + time + " " + min);
                } else {
                    say(ANSWER,"The Time Is " + time + ":" + min + "AM");
                }
            }

        }
        else if (com.toLowerCase().contains("what") && com.toLowerCase().contains("my") && com.toLowerCase().contains("name")) {
            if (user_name != null || user_name.equals("stranger")) {
                say(ANSWER,"Your Name Is " + user_name + " of course!");
            } else {
                if (bitchstring) {
                    say(ANSWER,"Bitch tell me your name first");
                } else {
                    say(ANSWER,"You didn't Tell Me Your Name Yet");
                }
            }
            spoke = com;

        }
        else if (com.toLowerCase().contains("what") && com.toLowerCase().contains("your") && com.toLowerCase().contains("name")) {
            say(ANSWER, "My Name Is " + com_name + " But You Can Change It By Saying, Your Name Is..");

        }
        else if(com.toLowerCase().contains("what does the fox says")){
            say(ANSWER,"Hati-hati-hati-ho!");
        }
        else if ((com.toLowerCase().contains("who")&& com.split(" ").length>2)
                ||(com.toLowerCase().contains("translate")&&com.split(" ").length>2)
                ||(com.toLowerCase().contains("when")&& com.split(" ").length>2)
                ||(com.toLowerCase().contains("how")&&(com.toLowerCase().contains("old")||com.toLowerCase().contains("much")||com.toLowerCase().contains("many"))&& com.split(" ").length>2)
                ||(com.toLowerCase().contains("what")&& com.split(" ").length>2)
                ||(com.toLowerCase().contains("calculate")&& com.split(" ").length>2)
                ||((com.toLowerCase().contains("+")
                ||com.toLowerCase().contains("-")
                ||com.toLowerCase().contains("/")
                ||com.toLowerCase().contains("*")
                ||com.toLowerCase().contains("divide")
                ||com.toLowerCase().contains("multip")
                ||com.toLowerCase().contains("plus")
                ||com.toLowerCase().contains("minus"))
                &&((com.matches(".*\\d+.*"))
                && com.length() > 2))) {
                wolfram(com);
        }
        else if (com.toLowerCase().contains("timer") || com.toLowerCase().contains("countdown")) {
            try {
                Method method;
                Object object = this.getSystemService("statusbar");
                Class class_ = Class.forName((String) ("android.app.StatusBarManager"));
                Method method2 = (Build.VERSION.SDK_INT > 16) ? (class_.getMethod("expandNotificationsPanel", new Class[0])) : ((method = class_.getMethod("expand", new Class[0])));
                method2.invoke(object, new Object[0]);
            } catch (Exception var4_8) {
                var4_8.printStackTrace();
            }
            try {
                time_timer = Integer.parseInt(com.replaceAll("[\\D]", "")) * 1000;
                if (com.toLowerCase().contains("second")) {
                    time_timer = Integer.parseInt(com.replaceAll("[\\D]", "")) * 1000;
                    say(ANSWER,"Timer for " + time_timer / 1000 + " seconds" + " is running, view it from the status bar");
                }
                if (com.toLowerCase().contains("minute")) {
                    time_timer = Integer.parseInt(com.replaceAll("[\\D]", "")) * 1000 * 60;
                    say(ANSWER,"Timer for " + time_timer / 1000 / 60 + " minutes" + " is running, view it from the status bar");
                }
                if (com.toLowerCase().contains("hour")) {
                    time_timer = Integer.parseInt(com.replaceAll("[\\D]", "")) * 1000 * 60 * 60;
                    say(ANSWER,"Timer for " + time_timer / 1000 / 60 / 60 + " hours" + " is running, view it from the status bar");
                }
                if (com.toLowerCase().contains("day")) {
                    time_timer = Integer.parseInt(com.replaceAll("[\\D]", "")) * 1000 * 60 * 60 * 24;
                    say(ANSWER,"Timer for " + time_timer / 1000 / 60 / 60 / 24 + " days" + " is running, view it from the status bar");
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                if (com.toLowerCase().contains("second")) {
                    time_timer = 1 * 1000;
                    say(ANSWER,"Timer for " + time_timer / 1000 + " second" + " is running, view it from the status bar");
                }
                if (com.toLowerCase().contains("minute")) {
                    time_timer = 1 * 1000 * 60;
                    say(ANSWER,"Timer for " + time_timer / 1000 / 60 + " minute" + " is running, view it from the status bar");
                }
                if (com.toLowerCase().contains("hour")) {
                    time_timer = 1 * 1000 * 60 * 60;
                    say(ANSWER,"Timer for " + time_timer / 1000 / 60 / 60 + " hour" + " is running, view it from the status bar");
                }
                if (com.toLowerCase().contains("day")) {
                    time_timer = 1 * 1000 * 60 * 60 * 24;
                    say(ANSWER,"Timer for " + time_timer / 1000 / 60 / 60 / 24 + " day" + " is running, view it from the status bar");
                }
            }
            startService(new Intent(getApplicationContext(), timer_service.class));
            
        }
        else if (com.toLowerCase().contains("youtube") && com.toLowerCase().contains("search")) {
            int numWords = com.split(" ").length;
            if (numWords >= 3) {
                String arr[] = com.split(" ", 3);
                String firstWord = arr[0];
                String secend = arr[1];
                theRest = arr[2];
                say(ANSWER,"youtubing...");
                String url = "https://m.youtube.com/results?search_query=" + theRest;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                
            } else {
                say(ANSWER,"what do you want me to tube?");
                whatAfterResult = "to_youtube";
                
            }
        }
        else if (com.toLowerCase().startsWith("my name is") || com.toLowerCase().startsWith("call me")) {
            int numWords = com.split(" ").length;
            if (numWords >= 4) {
                if (bitchstring) {
                    say(ANSWER,"Sorry Bitch but your name is " + user_name);
                } else {
                    String arr[] = com.split(" ", 4);
                    String firstWord = arr[0];
                    user_name = arr[3];
                    SharedPreferences.Editor prefEditor = getSharedPreferences("Preferences", Context.MODE_PRIVATE).edit();
                    prefEditor.putString("prefUsername", user_name);
                    prefEditor.commit();
                    say(ANSWER,"Alright, " + user_name + " I will try to remember that!");
                }
            } else {
                if (bitchstring) {
                    say(ANSWER,"YOU DIDN'T SAY NO NAME BITCH");
                } else {
                    say(ANSWER,"say my name is followed by your name");
                }
            }
            
        } else if (com.toLowerCase().contains("picture") || com.toLowerCase().contains("camera") || com.toLowerCase().contains("photo")) {
            final PackageManager pm = getPackageManager();
            List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);
            for (ApplicationInfo packageInfo : packages) {
                appicon = packageInfo.loadIcon(getPackageManager());
                appname = packageInfo.loadLabel(getPackageManager()).toString().toLowerCase();
                if (packageInfo.loadLabel(getPackageManager()).toString().toLowerCase().equals("camera")) {
                    PackageName = packageInfo.packageName.toString();
                    foundapp = true;
                    numbers_of_apps_found += 1;
                    say(ANSWER,"opening " + "camera");
                   
                    Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage(PackageName);
                    startActivity(LaunchIntent);
                }
            }
        }
        else if (com.toLowerCase().contains("your") && com.toLowerCase().contains("name") && com.toLowerCase().contains("is")) {
            String[] name = com.split(" ");
            int numWords = com.split(" ").length;
            if (numWords > 3) {
                com_name = name[3].toString();
                SharedPreferences.Editor prefEditor = getSharedPreferences("Preferences", Context.MODE_PRIVATE).edit();
                prefEditor.putString("prefAssistantName", com_name);
                prefEditor.commit();
                say(ANSWER,"ok, my new name is " + com_name);
            } else {
                say(ANSWER,"You didnt say a name");
            }
        } else if (com.toLowerCase().contains("image search") ||
                com.toLowerCase().contains("pictures of") ||
                com.toLowerCase().contains("images of") ||
                com.toLowerCase().contains("image of") ||
                com.toLowerCase().contains("pictures search") ||
                com.toLowerCase().contains("images search")) {
            String toSearch = com.substring(com.lastIndexOf("image search")+"image search".length());

            if(com.toLowerCase().contains("image search")){
                toSearch = toSearch.substring(toSearch.lastIndexOf("image search")+12);
            }
            else if(com.toLowerCase().contains("pictures of")){
                toSearch = toSearch.substring(toSearch.lastIndexOf("pictures of")+11);
            }
            else if(com.toLowerCase().contains("images of")){
                toSearch = toSearch.substring(toSearch.lastIndexOf("images of")+9);
            }
            else if(com.toLowerCase().contains("image of")){
                toSearch = toSearch.substring(toSearch.lastIndexOf("image of")+8);
            }
            else if(com.toLowerCase().contains("pictures search")){
                toSearch = toSearch.substring(toSearch.lastIndexOf("pictures search")+15);
            }
            else if(com.toLowerCase().contains("images search")){
                toSearch = toSearch.substring(toSearch.lastIndexOf("images search")+13);
            }
            toSearch.replaceAll("please","");
            say(ANSWER, "Here are some images of " + toSearch);
            StartUrl("https://www.google.com/search?q="+toSearch+"&tbm=isch&gws_rd=ssl");


        } else if (com.toLowerCase().startsWith("call") || com.toLowerCase().startsWith("dial")) {
            int numWords = com.split(" ").length;
            if (numWords >= 2) {
                String arr[] = com.split(" ", 2);
                String firstWord = arr[0];
                contact = arr[1];
                call();

                if (sent == false) {
                    say(ANSWER,"I couldn't find " + contact + " in your contacts");
                }
                
            } else {
                say(ANSWER,"Who would you like me to call?");
                whatAfterResult = "to_call";
                
                boolean x = false;
                while (!x) {
                    if (!tts.isSpeaking()) {
                        x = true;
                        listenmulitlang();
                    }

                }

            }
        } else if ((com.toLowerCase().contains("low") || com.toLowerCase().contains("lower")) && com.toLowerCase().contains("brightness")) {
            WindowManager.LayoutParams settings = getWindow().getAttributes();
            settings.screenBrightness = 0;
            getWindow().setAttributes(settings);
            say(ANSWER,"Here you go");
            
        } else if ((com.toLowerCase().contains("up") || com.toLowerCase().contains("brighter")) && com.toLowerCase().contains("brightness") || com.toLowerCase().contains("brighter")) {
            WindowManager.LayoutParams settings = getWindow().getAttributes();
            settings.screenBrightness = 1;
            getWindow().setAttributes(settings);
            say(ANSWER,"Here you go");
            

        } else if (com.toLowerCase().startsWith("open") || com.toLowerCase().startsWith("launch") || com.toLowerCase().contains("music") || com.toLowerCase().contains("picture") || com.toLowerCase().contains("photo")) {
            if (com.toLowerCase().contains("music")) {
                com = "open google play music";
            }
            if (com.toLowerCase().contains("picture") || com.toLowerCase().contains("photo")) {
                com = "open camera";
            }
            int numWords = com.split(" ").length;
            if (numWords >= 2) {
                String arr[] = com.split(" ", 2);
                String firstWord = arr[0];
                theRest = arr[1];
                theRest = theRest.toLowerCase();
                if (theRest.equals("what's up")) {
                    theRest = "whatsapp";
                }
                if (theRest.equals("google plus")) {
                    theRest = "google+";
                }
                if (theRest.equals("ways")) {
                    theRest = "waze";
                }
                if (theRest.equals("move it")) {
                    theRest = "moovit";
                }
                if (theRest.equals("assist me")) {
                    theRest = "assist-me";
                }
                final PackageManager pm = getPackageManager();
                List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);
                for (ApplicationInfo packageInfo : packages) {
                    appicon = packageInfo.loadIcon(getPackageManager());
                    appname = packageInfo.loadLabel(getPackageManager()).toString().toLowerCase();
                    if (packageInfo.loadLabel(getPackageManager()).toString().toLowerCase().equals(theRest)) {
                        PackageName = packageInfo.packageName.toString();
                        foundapp = true;
                        numbers_of_apps_found += 1;
                        if (donotshowagain == true) {
                            AlertDialog.Builder alert = new AlertDialog.Builder(Main1.this);
                            say(ANSWER,"Found! " + numbers_of_apps_found);
                           
                            alert.setTitle("Open " + theRest);
                            alert.setIcon(appicon);
                            check = new CheckBox(this);
                            check.setText("Do not show again");
                            alert.setMessage("Are your sure you want to exit assist me and open " + theRest + "?");
                            alert.setCancelable(false);
                            alert.setView(check);
                            alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    say(ANSWER,"opening " + theRest);
                                   
                                    Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage(PackageName);
                                    startActivity(LaunchIntent);
                                    if (check.isChecked()) {
                                        donotshowagain = false;
                                        SharedPreferences.Editor donotshowagainpref = getSharedPreferences("Preferences", Context.MODE_PRIVATE).edit();
                                        donotshowagainpref.putBoolean("donotshowagain", donotshowagain);
                                        donotshowagainpref.commit();
                                    } else {
                                        donotshowagain = true;
                                        SharedPreferences.Editor donotshowagainpref = getSharedPreferences("Preferences", Context.MODE_PRIVATE).edit();
                                        donotshowagainpref.putBoolean("donotshowagain", donotshowagain);
                                        donotshowagainpref.commit();
                                    }
                                }
                            });
                            if (numbers_of_apps_found > 1) {
                                alert.setTitle("I found several apps for you with this name");
                                negativebutton = "Next";
                            }
                            alert.setNegativeButton(negativebutton, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    if (numbers_of_apps_found > 1) {
                                        numbers_of_apps_found = -1;
                                    } else {
                                        say(ANSWER,"Ok canceled");
                                       
                                        
                                    }
                                }
                            });
                            alert.show();
                        } else {
                            say(ANSWER,"opening " + theRest);
                           
                            Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage(PackageName);
                            startActivity(LaunchIntent);
                        }
                    }
                    if (!foundapp && packageInfo.loadLabel(getPackageManager()).toString().toLowerCase().contains(theRest)) {
                        foundapp = true;
                        AlertDialog.Builder alert = new AlertDialog.Builder(Main1.this);
                        alert.setTitle("Assist-Me");
                        alert.setMessage("Couldn't find the spoken app, did you mean one of those?");
                        ScrollView scrollview = new ScrollView(this);
                        LinearLayout linearLayout = new LinearLayout(this);
                        linearLayout.addView(scrollview);
                        alert.setView(linearLayout);
                        if (numbers_of_apps_found < 5) {
                            PackageName = packageInfo.packageName.toString();
                            foundapp = true;
                            numbers_of_apps_found += 1;
                            final String thefinalname = packageInfo.loadLabel(getPackageManager()).toString();
                            alert.setPositiveButton("Open " + thefinalname, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage(PackageName);
                                    startActivity(LaunchIntent);
                                    say(ANSWER,"Opening " + thefinalname);
                                    
                                }
                            });
                            alert.setMessage("I couldn't find the spoken app but i found this app with similiar name");
                            alert.setIcon(packageInfo.loadIcon(getPackageManager()));
                        }
                        alert.show();
                    }
                }

                if (!foundapp) {
                    say(ANSWER,"App not found");
                    
                }
            } else {

                say(ANSWER,"what is the app name?");
                whatAfterResult = "open_app";
                
                boolean x = false;
                while (!x) {
                    if (!tts.isSpeaking()) {
                        x = true;
                        listenmulitlang();
                    }
                }
            }
        }
      else if (com.equals("")) {
            if (bitchstring) {
                say(ANSWER,"You said nothing bitch");
            } else {
                say(ANSWER,"You said nothing");
            }
            
        } else if (com.toLowerCase().contains("navigate to")) {
            try {
                int numWords = com.split(" ").length;
                if (numWords >= 3) {
                    String arr[] = com.split(" ", 3);
                    String firstWord = arr[0];
                    String secend = arr[1];
                    theRest = arr[2];
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q=" + theRest));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    if (bitchstring) {
                        say(ANSWER, "Navigating to " + theRest + " Bitch");
                    } else {
                        say(ANSWER, "Navigating to " + theRest);
                    }


                } else {
                    say(ANSWER, "no destination was mentioned");

                }
            }
            catch (ActivityNotFoundException e){
                say(ANSWER,"You don't have any navigation software installed");
            }
        } else if (com.toLowerCase().startsWith("say") || (com.toLowerCase().contains("speak"))) {
            int numWords = com.split(" ").length;
            if (numWords > 1) {
                String arr[] = com.split(" ", 2);
                theRest = arr[1];
                say(ANSWER,theRest);
                
            } else {
                say(ANSWER,"say what?");
                
            }
        } else if (com.toLowerCase().startsWith("where is the") || com.toLowerCase().startsWith("find me the")) {
            try {
                int numWords = com.split(" ").length;
                if (numWords > 3) {
                    String arr[] = com.split(" ", 4);
                    String firstWord = arr[0];
                    String secend = arr[1];
                    String third = arr[2];
                    theRest = arr[3];
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q=" + theRest));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    if (bitchstring) {
                        say(ANSWER, "Bitch here is the " + theRest);
                    } else {
                        say(ANSWER, "Here is the " + theRest);
                    }

                } else {
                    say(ANSWER, "No destination was mentioned");


                }
            }
            catch (ActivityNotFoundException e){
                say(ANSWER,"You don't have any navigation software installed");
            }
        } else if (com.toLowerCase().startsWith("where is ") || com.toLowerCase().startsWith("find a ") || com.toLowerCase().startsWith("find the ") || com.toLowerCase().startsWith("find a ")) {
            try {
                int numWords = com.split(" ").length;
                if (numWords > 2) {
                    String arr[] = com.split(" ", 3);
                    String firstWord = arr[0];
                    String secend = arr[1];
                    theRest = arr[2];
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q=" + theRest));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    if (bitchstring) {
                        say(ANSWER, "Bitch here is the " + theRest);
                    } else {
                        say(ANSWER, "Here is the " + theRest);
                    }

                } else {
                    say(ANSWER, "No destination was mentioned please try again");

                }
            }    catch (ActivityNotFoundException e){
                say(ANSWER, "You don't have any navigation software installed");
            }
        } else if (com.toLowerCase().contains("featur") || com.toLowerCase().contains("can you do") || com.toLowerCase().contains("assist me")) {
            startActivity(new Intent(getApplicationContext(), features.class));
            say(ANSWER,"Here is the features list");
            
        } else if (com.toLowerCase().startsWith("find") || com.toLowerCase().startsWith("find the ") || com.toLowerCase().startsWith("find a ")) {
            int numWords = com.split(" ").length;
            if (numWords > 2) {
                String arr[] = com.split(" ", 3);
                String firstWord = arr[0];
                String secend = arr[1];
                theRest = arr[2];
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q=" + theRest));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                if (bitchstring) {
                    say(ANSWER,"Bitch here is the " + theRest);
                } else {
                    say(ANSWER,"Here is the " + theRest);
                }
                
            } else {
                say(ANSWER,"No destination was mentioned please try again");
                
            }
        } else if (com.toLowerCase().contains("where am i")) {
            try {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q="));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
            catch (ActivityNotFoundException e)
            {
                say(ANSWER,"You don't have any navigation program installed");
            }
        } else if (com.toLowerCase().contains("tell me a joke")) {
            //jokes
            String joke1 = "how do you make seven an even number?" +
                    "take the s out!";
            String joke2 = "Why are ghosts bad liars? " +
                    "Because you can see right through them";
            String joke3 = "What dog can jump higher than a building? " +
                    "Anydog, buildings can't jump!";
            String joke4 = "why did the elephant paint himself diffrent colours?" +
                    "so he could hide in the crayon box";
            String joke5 = "What's black and white and makes a lot of noise? " +
                    "A zebra with a drumkit.";
            int joke = (new Random().nextInt(5));
            if (bitchstring) {
                if (joke == 0) {
                    say(ANSWER,joke1 + " BITCH");
                } else if (joke == 1) {
                    say(ANSWER,joke2 + " BITCH");
                } else if (joke == 2) {
                    say(ANSWER,joke3 + " BITCH");
                } else if (joke == 3) {
                    say(ANSWER,joke4 + " BITCH");
                } else if (joke == 1) {
                    say(ANSWER,joke5 + " BITCH");
                }
            } else {
                if (joke == 0) {
                    say(ANSWER,joke1);
                } else if (joke == 1) {
                    say(ANSWER,joke2);
                } else if (joke == 2) {
                    say(ANSWER,joke3);
                } else if (joke == 3) {
                    say(ANSWER,joke4);
                } else if (joke == 4) {
                    say(ANSWER,joke5);
                }
            }
            
        } else if (com.toLowerCase().contains("gps on") || com.toLowerCase().contains("gps off") || com.toLowerCase().contains("disable gps")
                || com.toLowerCase().contains("enable gps")) {
            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            if (bitchstring) {
                say(ANSWER,"GPS BITCH");
            } else {
                say(ANSWER,"GPS");
            }
            
        } else if (com.toLowerCase().contains("wifi on")
                || (com.toLowerCase().contains("on wifi"))
                || com.toLowerCase().contains("disable wifi")
                || com.toLowerCase().contains("enable wifi")) {
            WifiManager wifiManager = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
            wifiManager.setWifiEnabled(true);
            if (bitchstring) {
                say(ANSWER,"WIFI enabled BITCH");
            } else {
                say(ANSWER,"WiFi enabled");
            }
            
        } else if (com.toLowerCase().contains("wifi off") || com.toLowerCase().contains("off wifi")) {
            WifiManager wifiManager = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
            wifiManager.setWifiEnabled(false);
            if (bitchstring) {
                say(ANSWER,"WIFI disabled BITCH");
            } else {
                say(ANSWER,"WiFi disabled");
            }
            
        } else if (com.toLowerCase().contains("bluetooth") && (com.toLowerCase().contains("disable") || com.toLowerCase().contains("off"))) {
            BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            mBluetoothAdapter.disable();
            if (bitchstring) {
                say(ANSWER, "Bluetooth disabled BITCH");
            } else {
                say(ANSWER,"bluetooth disabled");

            }
            
        } else if (com.toLowerCase().contains("bluetooth") && (com.toLowerCase().contains("enable") || com.toLowerCase().contains("on"))) {
            BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            if (!mBluetoothAdapter.isEnabled()) {
                mBluetoothAdapter.enable();
                if (bitchstring) {
                    say(ANSWER,"Bluetooth is enabled BITCH");
                } else {
                    say(ANSWER,"Bluetooth enabled");
                }
            } else {
                if (bitchstring) {
                    say(ANSWER,"Bluetooth is already enabled BITCH");
                } else {
                    say(ANSWER,"seems like your bluetooth is on");
                }
            }
            
        } else if (com.toLowerCase().contains("change theme")) {
            say(ANSWER,"this feature is not yet available");
            

        } else if (com.toLowerCase().contains("power off")) {
            try {
                Process proc = Runtime.getRuntime()
                        .exec(new String[]{"su", "-c", "reboot -p"});
                proc.waitFor();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (com.toLowerCase().contains("settings") || com.toLowerCase().contains("preferences") || com.toLowerCase().contains("edit")) {
            say(ANSWER,"opening settings");
            
            Intent settings = new Intent(this, preferencesMenu.class);
            startActivity(settings);
            animate("out");
        } else if (com.toLowerCase().contains("take a") && (com.toLowerCase().contains("video") || com.toLowerCase().contains("vid")) || com.toLowerCase().contains("shoot a video") || com.toLowerCase().contains("video") || com.toLowerCase().contains("movie")) {
            Intent video = new Intent("android.media.action.VIDEO_CAMERA");
            startActivity(video);
        }
        else if (com.toLowerCase().contains("imei")) {
            TelephonyManager tManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
            //Getting IMEI Number of Devide
            String Imei=tManager.getDeviceId();
            say(ANSWER, "IMEI:" + Imei);
            
        } else if (com.toLowerCase().contains("home") || com.toLowerCase().contains("launcher")) {
            Intent goHome = new Intent();
            goHome.setAction("android.intent.action.MAIN");
            goHome.addCategory("android.intent.category.HOME");
            startActivity(goHome);
            finish();
        } else if (com.toLowerCase().contains("info") || com.toLowerCase().contains("assist") || com.toLowerCase().contains("information") || com.toLowerCase().contains("help")) {
            say(ANSWER,"here is the app page");
            try {
                Uri uri = Uri.parse("market://details?id=com.assist.me");
                Intent it = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(it);
            }
            catch (ActivityNotFoundException e){
                e.printStackTrace();
                Toast.makeText(getApplicationContext(),"Play store not found",Toast.LENGTH_LONG).show();
            }
        } else if (com.toLowerCase().equals("ok")) {
            say(ANSWER,"ok then");
            
        } else if (WordInList(com,phrases.curses)) {
            say(ANSWER,"Don't talk to me like this, " + user_name);
            
        }
        else if ((com.toLowerCase().contains("what's") || com.toLowerCase().contains("what is")) && com.toLowerCase().contains("up")) {
            if (user_name != null) {
                if (bitchstring) {
                    say(ANSWER,"Talking to my BITCH");
                } else {
                    say(ANSWER,"Nothing much just talking to you " + user_name);
                }
            }

        }

        else if (com.toLowerCase().startsWith("hello") || com.toLowerCase().startsWith("hi") || com.toLowerCase().startsWith("hey") || com.toLowerCase().startsWith(" hi")) {
            if (bitchstring) {
                say(ANSWER,"Sorry, bitch i am not talking to bitches");
            } else {
                Random rnd = new Random();
                int answer = rnd.nextInt(4);
                switch (answer) {
                    case 1:
                        say(ANSWER,"Hi There " + user_name + "!");
                        break;
                    case 2:
                        say(ANSWER,"Hello " + user_name);
                        break;
                    case 3:
                        say(ANSWER,"Hey!");
                        break;
                    case 0:
                        say(ANSWER,"Ahoy!");

                }
            }
            
        }
        //SHIITY stuff
        else if ((com.toLowerCase().contains("good") || com.toLowerCase().contains("nice")) && (com.toLowerCase().contains("night") || com.toLowerCase().contains("morning") || com.toLowerCase().contains("evening") || com.toLowerCase().contains("noon"))) {
            if (bitchstring)
                say(ANSWER,"Stop kissing my ass bitch");
            else
                say(ANSWER,"Right back at you");

            
        } else if (com.toLowerCase().contains("friend")) {
            if (bitchstring)
                say(ANSWER,"Bitches ain't got no friend bitch");
            else
                say(ANSWER,"You care my best friend " + user_name);
            
        }
        else if(WordInList(com, phrases.HowAreYou)){
            if(!bitchstring) {
                say(ANSWER,"I feel very efficient! Hope you do to");
            }
            else{
                say(ANSWER,"BITCH Y DO U CARE?? MIND UR OWN FREAKING BUISNESS");
            }
            
        }
        else if (com.toLowerCase().contains("yolo")) {
            say(ANSWER,"Because U only live once");
            
        } else if (com.toLowerCase().contains("thanks") || com.toLowerCase().contains("thank you") || com.toLowerCase().contains("thanx") || com.toLowerCase().contains("thnx")) {
            say(ANSWER,"Pleasure to help you");
            
        } else if (com.toLowerCase().contains("love you")||com.toLowerCase().contains("love me")) {
            say(ANSWER,"Sorry you are not my type, i'm more like a Siri sexual");

        }
        else if (com.toLowerCase().contains("siri")) {
            say(ANSWER,"I like Siri but Android is my favorite");
            
        } else if (com.toLowerCase().contains("ok")) {
            say(ANSWER,"OK then");
            
        } else if (com.toLowerCase().contains("swag")) {
            say(ANSWER,"YOLO SWAG");
            
        }
        else if(com.toLowerCase().contains("yo ")){
            if(bitchstring){
                say(ANSWER,"DON'T Yo me with your white ass");
            }
            else {
                say(ANSWER, "Yo Yo Yo");
            }
        }
        else {
            if (sharedPrefs.getString("prefUnknown", "").equals("Nothing")) {

            }
            else if (sharedPrefs.getString("prefUnknown", "").equals("Feedback")) {
                if (bitchstring) {
                    say(ANSWER,"Bitch i don't know this shit' Now send that EMAIL");
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("message/rfc822");
                    String[] recipients = new String[]{"tomerosenfeld007@gmail.com", "",};
                    intent.putExtra(Intent.EXTRA_EMAIL, "tomerosenfeld007@gmail.com");
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Assist Me command not found");
                    intent.putExtra(android.content.Intent.EXTRA_EMAIL, recipients);
                    intent.putExtra(Intent.EXTRA_TEXT, "Error:\n command not found:\n" + '"' + com + '"');
                    startActivity(Intent.createChooser(intent, "Send Email"));
                } else {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("message/rfc822");
                    String[] recipients = new String[]{"tomerosenfeld007@gmail.com", "",};
                    intent.putExtra(Intent.EXTRA_EMAIL, "tomerosenfeld007@gmail.com");
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Assist Me command not found");
                    intent.putExtra(android.content.Intent.EXTRA_EMAIL, recipients);
                    intent.putExtra(Intent.EXTRA_TEXT, "Error:\n command not found:\n" + '"' + com + '"');
                    startActivity(Intent.createChooser(intent, "Send Email"));
                }
                animate("out");
                
            } else if (sharedPrefs.getString("prefUnknown", "").equals("Google")) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY, com);
                startActivity(intent);

            } else if (sharedPrefs.getString("prefUnknown", "").equals("Wolfram")) {
                wolfram(com);
            }
            
            if(!user_name.equals(MASTER_NAME))
                uploadNotUnderstoodParse(com);
            Random random = new Random();
            int rand = random.nextInt((phrases.NotUnderstand.length));
            say(ANSWER,phrases.NotUnderstand[rand]);
        }
    }    /*
     * hebrew shit
    */


    void uploadNotUnderstoodParse(String notUnderstoond){
        Parse.initialize(this, "zRhBp9c5NAjDytf1sB2KDGsznOcrYizoq4W3sZF2", "2f68e1ym9zuBU429JINFO96tMhJII9AHn0QI4ipV");
        ParseObject testObject = new ParseObject("CNF");
        testObject.put("notfound", notUnderstoond);
        testObject.put("user_name", user_name);
        testObject.saveInBackground();
    }
    void startVoiceRecognitionActivityhebrew() {
        run_heb(Main1.this);
    }

    void newhebrewafterrseult() throws Settings.SettingNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, ClassNotFoundException {
        //    startService(new Intent(getApplicationContext(),VoiceService.class));
        numbers_of_apps_found = 0;
        findViewById(R.id.one).setVisibility(View.INVISIBLE);
        info_one.setText("");
        imgview.setImageResource(R.drawable.trans);
        appicon = null;
        foundapp = false;
        spoke = null;
        mobileavi = false;
        homeavi = false;
        otheravi = false;
        workavi = false;
        sent = false;
        if (edit.getText().toString().equals("Type Here") || edit.getText().toString().equals("")) {
            if (siri_bool) {
                btnSpeak.setBackgroundResource(R.drawable.siri_icon);
            } else {
                btnSpeak.setBackgroundResource(R.drawable.btnspeek);
            }
        } else {
            if (!siri_bool) {
                btnSpeak.setBackgroundResource(R.drawable.enter);
            }
        }
        if (com.toLowerCase().startsWith("שלח הודעה אל")) {
            int numWords = com.split(" ").length;
            if (numWords < 4) {
                if (bitchstring == true) {
                    say(ANSWER,"Bitch who do i send it??");

                } else {
                    say(ANSWER,"Whom should i send the message?");

                }
                whatAfterResult = "recipient";
                boolean x = false;
                while (!x) {
                    if (!tts.isSpeaking()) {
                        x = true;
                        listenmulitlang();
                    }
                }

            } else {
                String arr[] = com.split(" ", 4);
                String firstWord = arr[0];
                contact = arr[3];
                Toast.makeText(getApplicationContext(),contact,Toast.LENGTH_SHORT).show();
                ContentResolver cr = getContentResolver();
                Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI,
                        null, "DISPLAY_NAME" + " LIKE '%" + contact + "%'", null,
                        ContactsContract.CommonDataKinds.Phone.LAST_TIME_CONTACTED + " DESC");
                if (cursor.moveToFirst()) {
                    String contactId =
                            cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                    Cursor phones = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId, null, null);
                    while (phones.moveToNext()) {
                        phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        int type = phones.getInt(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));
                        pic = phones.getInt(phones.getColumnIndex(ContactsContract.CommonDataKinds.Photo.PHOTO));
                        switch (type) {
                            case ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE:
                                say(ANSWER,"What is your message?");

                                sent = true;

                                whatAfterResult = "message";
                                boolean x = false;
                                while (!x) {
                                    if (!tts.isSpeaking()) {
                                        x = true;
                                        listenmulitlang();
                                    }
                                }
                        }
                    }
                    phones.close();
                }
                cursor.close();
                if (sent == false) {
                    if (bitchstring) {
                        say(ANSWER,"BITCH you don't have this contact!, Try again");
                        Log.d("contact name ",contact);
                    } else {
                        say(ANSWER,"No such contact with this name " + contact + " please try again");
                    }
                    whatAfterResult = "recipient";

                    boolean x = false;
                    while (!x) {
                        if (!tts.isSpeaking()) {
                            x = true;
                            listenmulitlang();
                        }
                    }
                }
            }
        }
        else if (com.toLowerCase().startsWith("שלח הודעה")) {
            int numWords = com.split(" ").length;
            if (numWords < 3) {
                if (bitchstring == true) {
                    say(ANSWER,"Bitch who do i message??");
                } else {
                    say(ANSWER,"Whom should i send the message?");
                }

                whatAfterResult = "recipient";
                boolean x = false;
                while (!x) {
                    if (!tts.isSpeaking()) {
                        x = true;
                        listenmulitlang();
                    }
                }
            } else {
                String arr[] = com.split(" ", 4);
                String firstWord = arr[0];
                contact = arr[3];
                ContentResolver cr = getContentResolver();
                Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI,
                        null, "DISPLAY_NAME" + " LIKE '%" + contact + "%'", null,
                        ContactsContract.CommonDataKinds.Phone.LAST_TIME_CONTACTED + " DESC");
                if (cursor.moveToFirst()) {
                    String contactId =
                            cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                    Cursor phones = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId, null, null);
                    while (phones.moveToNext()) {
                        phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        int type = phones.getInt(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));
                        pic = phones.getInt(phones.getColumnIndex(ContactsContract.CommonDataKinds.Photo.PHOTO));
                        switch (type) {
                            case ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE:
                                say(ANSWER,"What is your message?");

                                sent = true;

                                whatAfterResult = "message";
                                boolean x = false;
                                while (!x) {
                                    if (!tts.isSpeaking()) {
                                        x = true;
                                        listenmulitlang();
                                    }
                                }
                            /*    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) //At least KitKat
                                {
                                    String text = " ";
                                    String defaultSmsPackageName = Telephony.Sms.getDefaultSmsPackage(this); //Need to change the build to API 19

                                    Intent intent = new Intent(Intent.ACTION_VIEW);
                                    intent.putExtra("address", smsnumber);
                                    intent.putExtra("sms_body", " ");
                                    intent.setData(Uri.parse("smsto:" + smsnumber));
                                    sent = true;
                                    if (bitchstring) {
                                        say(ANSWER,"Bitch i hate you but im gonna send this sms");
                                    } else {
                                        say(ANSWER,"Sending sms to " + contact);
                                    }
                                    txtText2.setVisibility(0);


                                    if (defaultSmsPackageName != null)//Can be null in case that there is no default, then the user would be able to choose any app that support this intent.
                                    {
                                        intent.setPackage(defaultSmsPackageName);
                                    }
                                    startActivity(intent);

                                } else //For early versions, do what worked for you before.
                                {
                                    String text = "";
                                    if (bitchstring) {
                                        say(ANSWER,"Bitch i hate you but im gonna send this sms");
                                    } else {
                                        say(ANSWER,"Sending sms to " + contact);
                                    }
                                    txtText2.setVisibility(0);
                                    Intent intent = new Intent(Intent.ACTION_VIEW);
                                    intent.putExtra("address", smsnumber);
                                    intent.putExtra("sms_body", " ");
                                    intent.setData(Uri.parse("smsto:" + smsnumber));
                                    startActivity(intent);
                                    sent = true;
                                }
                                break;
                                */
                        }
                    }
                    phones.close();
                }
                cursor.close();
                if (sent == false) {
                    if (bitchstring) {
                        say(ANSWER,"BITCH you don't have this contact!, Try again");
                    } else {
                        say(ANSWER,"No such contact with this name " + contact + " please try again");
                    }
                    whatAfterResult = "recipient";

                    boolean x = false;
                    while (!x) {
                        if (!tts.isSpeaking()) {
                            x = true;
                            listenmulitlang();
                        }
                    }
                }
            }
        } else if (com.toLowerCase().startsWith("הזכר לי")) {
            Calendar cal = Calendar.getInstance();
            Intent intent = new Intent(Intent.ACTION_EDIT);
            intent.setType("vnd.android.cursor.item/event");
            intent.putExtra("beginTime", cal.getTimeInMillis());
            intent.putExtra("allDay", true);
            intent.putExtra("rrule", "FREQ=YEARLY");
            intent.putExtra("endTime", cal.getTimeInMillis() + 60 * 60 * 1000);
            intent.putExtra("title", "A Test Event from android app");
            startActivity(intent);
        } else if (com.toLowerCase().startsWith("נגן")) {
            int numWords = com.split(" ").length;
            if (numWords >= 2) {
                String arr[] = com.split(" ", 2);
                String firstWord = arr[0];
                theRest = arr[1];
                say(ANSWER,"Looking for " + theRest + " in your music list");

                Intent mus = new Intent(getApplicationContext(), music.class);
                mus.putExtra("KEY", theRest);
                startActivity(mus);
            } else {
                say(ANSWER,"What song do you want me to play?");
                whatAfterResult = "music";

                boolean x = false;
                while (!x) {
                    if (!tts.isSpeaking()) {
                        x = true;
                        listenmulitlang();
                    }
                }
            }
        } else if (com.toLowerCase().contains("סוללה")||com.contains("בטריה")) {
            Intent batteryIntent = registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
            int level = batteryIntent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int scale = batteryIntent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
            int plugged = batteryIntent.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0);
            float temperature = batteryIntent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0) / 10;
            if (temperature > 40) {
                say(ANSWER,"battery level is " + String.valueOf(level) + "% i noticed your battery temperature is too high");

            }
            if (temperature == 100) {
                say(ANSWER,"its over 9000");

            }
            say(ANSWER, "battery level is " + String.valueOf(level) + "% and its temperature is " + String.valueOf(temperature) + " celsius");

        } else if (com.toLowerCase().startsWith("sms") || com.toLowerCase().startsWith("הודעה")||com.contains("סמס")) {
            int numWords = com.split(" ").length;
            if (numWords < 2) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) //At least KitKat
                {
                    String text = " ";
                    String defaultSmsPackageName = Telephony.Sms.getDefaultSmsPackage(this); //Need to change the build to API 19

                    Intent sendIntent = new Intent(Intent.ACTION_SEND);
                    sendIntent.setType("text/plain");
                    sendIntent.putExtra(Intent.EXTRA_TEXT, text);
                    animate("out");
                    finish();

                    if (defaultSmsPackageName != null)//Can be null in case that there is no default, then the user would be able to choose any app that support this intent.
                    {
                        sendIntent.setPackage(defaultSmsPackageName);
                    }
                    startActivity(sendIntent);

                } else //For early versions, do what worked for you before.
                {
                    String text = " ";
                    Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                    sendIntent.setData(Uri.parse("sms:"));
                    sendIntent.putExtra("address", contact);
                    sendIntent.putExtra("sms_body", text);
                    startActivity(sendIntent);
                }
            } else {
                String arr[] = com.split(" ", 2);
                String firstWord = arr[0];
                theRest = arr[1];
                ContentResolver cr = getContentResolver();
                Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null,
                        "DISPLAY_NAME = '" + theRest + "'", null, null);
                if (cursor.moveToFirst()) {
                    String contactId =
                            cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                    Cursor phones = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId, null, null);
                    while (phones.moveToNext()) {
                        String number = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        int type = phones.getInt(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));
                        switch (type) {
                            case ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE:
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) //At least KitKat
                                {
                                    String text = " ";
                                    String defaultSmsPackageName = Telephony.Sms.getDefaultSmsPackage(this); //Need to change the build to API 19

                                    Intent sendIntent = new Intent(Intent.ACTION_SEND);
                                    sendIntent.putExtra("address", number);
                                    sendIntent.setType("text/plain");
                                    sendIntent.putExtra(Intent.EXTRA_TEXT, text);
                                    sent = true;

                                    if (defaultSmsPackageName != null)//Can be null in case that there is no default, then the user would be able to choose any app that support this intent.
                                    {
                                        sendIntent.setPackage(defaultSmsPackageName);
                                    }
                                    startActivity(sendIntent);

                                } else //For early versions, do what worked for you before.
                                {
                                    String text = "";
                                    Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                                    sendIntent.setData(Uri.parse("sms:"));
                                    sendIntent.putExtra("address", number);
                                    sendIntent.putExtra("sms_body", text);
                                    say(ANSWER,"Sending sms to " + theRest + ", Bitch");

                                    startActivity(sendIntent);
                                    sent = true;
                                }
                                break;
                        }
                    }
                    phones.close();
                }
                cursor.close();
                if (sent == false) {
                    if (bitchstring) {
                        say(ANSWER,"BITCH no one is this stupid, you dont have a contact with this name");
                    } else {
                        say(ANSWER,"No such contact with this name " + theRest);
                    }

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) //At least KitKat
                    {
                        String text = "";
                        String defaultSmsPackageName = Telephony.Sms.getDefaultSmsPackage(this); //Need to change the build to API 19

                        Intent sendIntent = new Intent(Intent.ACTION_SEND);
                        sendIntent.setType("text/plain");
                        sendIntent.putExtra(Intent.EXTRA_TEXT, text);
                        if (defaultSmsPackageName != null)//Can be null in case that there is no default, then the user would be able to choose any app that support this intent.
                        {
                            sendIntent.setPackage(defaultSmsPackageName);
                        }
                        startActivity(sendIntent);

                    } else //For early versions, do what worked for you before.
                    {
                        String text = "";
                        Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                        sendIntent.setData(Uri.parse("sms:"));
                        sendIntent.putExtra("sms_body", text);
                        startActivity(sendIntent);
                    }
                }
            }
        }
        else if (com.toLowerCase().contains("מזג אוויר ב")||com.contains("מזג האוויר ב")) {
            if(com.contains("מזג אוויר ב")) {
                cityName = com.substring(com.lastIndexOf("מזג אוויר ב") + 11);
            }
            else{
                cityName = com.substring(com.lastIndexOf("מזג האוויר ב") + 12);
            }
            tortanslate = cityName;
            new translate().execute();

        } else if (com.toLowerCase().contains("מזג אוויר")) {
            weatherlookerrunning = false;
            try {
                ConnectivityManager dataManager;
                dataManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                Method dataMtd = null;
                dataMtd = ConnectivityManager.class.getDeclaredMethod("setMobileDataEnabled", boolean.class);

                dataMtd.setAccessible(true);
                dataMtd.invoke(dataManager, true);
                setMobileDataEnabled(getApplicationContext(), true);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

           /* Intent intent = new Intent();
            intent.setAction(Intent.ACTION_WEB_SEARCH);
            intent.putExtra(SearchManager.QUERY, "Weather");
            startActivity(intent);*/
            Boolean flag;
            locationMangaer = (LocationManager)
                    getSystemService(Context.LOCATION_SERVICE);
            flag = displayGpsStatus();
            if (flag) {
                if (!isNetworkAvailable()) {
                    AlertDialog.Builder networkAlert = new AlertDialog.Builder(Main1.this);
                    networkAlert.setTitle("No internet connection");
                    networkAlert.setMessage("Please enable internet connection");
                    networkAlert.setPositiveButton("Turn on wifi", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                            wifiManager.setWifiEnabled(true);
                            LocationManager locationManager = (LocationManager)
                                    getSystemService(Context.LOCATION_SERVICE);
                            LocationListener locationListener = new MyLocationListener();
                            locationManager.requestLocationUpdates(
                                    LocationManager.GPS_PROVIDER, 5000, 10, locationListener);
                            locationManager.requestLocationUpdates(
                                    LocationManager.NETWORK_PROVIDER, 5000, 10, locationListener);
                            pdialog = new ProgressDialog(Main1.this);
                            pdialog.setCancelable(false);
                            pdialog.setTitle("Loading please wait");
                            pdialog.setMessage("Looking for your location");
                            pdialog.show();
                        }
                    });
                    networkAlert.setNegativeButton("Turn on mobile data", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ConnectivityManager dataManager;
                            dataManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                            Method dataMtd = null;
                            try {
                                dataMtd = ConnectivityManager.class.getDeclaredMethod("setMobileDataEnabled", boolean.class);

                                dataMtd.setAccessible(true);
                                dataMtd.invoke(dataManager, true);
                                setMobileDataEnabled(getApplicationContext(), true);
                                LocationManager locationManager = (LocationManager)
                                        getSystemService(Context.LOCATION_SERVICE);
                                LocationListener locationListener = new MyLocationListener();
                                locationManager.requestLocationUpdates(
                                        LocationManager.GPS_PROVIDER, 5000, 10, locationListener);
                                locationManager.requestLocationUpdates(
                                        LocationManager.NETWORK_PROVIDER, 5000, 10, locationListener);
                                pdialog = new ProgressDialog(Main1.this);
                                pdialog.setCancelable(false);
                                pdialog.setTitle("Loading please wait");
                                pdialog.setMessage("Looking for your location");
                                pdialog.show();
                            } catch (NoSuchMethodException e) {
                                e.printStackTrace();
                                LocationManager locationManager = (LocationManager)
                                        getSystemService(Context.LOCATION_SERVICE);
                                LocationListener locationListener = new MyLocationListener();
                                locationManager.requestLocationUpdates(
                                        LocationManager.GPS_PROVIDER, 5000, 10, locationListener);
                                locationManager.requestLocationUpdates(
                                        LocationManager.NETWORK_PROVIDER, 5000, 10, locationListener);
                                pdialog = new ProgressDialog(Main1.this);
                                pdialog.setCancelable(false);
                                pdialog.setTitle("Loading please wait");
                                pdialog.setMessage("Looking for your location");
                                pdialog.show();
                            } catch (InvocationTargetException e1) {
                                e1.printStackTrace();
                                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                            } catch (IllegalAccessException e1) {
                                e1.printStackTrace();
                                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                            } catch (NoSuchFieldException e) {
                                e.printStackTrace();
                                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                            }
                        }

                    });
                    networkAlert.setCancelable(false);
                    networkAlert.show();

                } else {
                    if (bitchstring) {
                        say(ANSWER,"Who da fuck gives a shit bitch? who the hell you think you are talking to me like this? thats it im bricking your phone. BITCH");
                    } else {
                        say(ANSWER,"Loading weather for your location");
                    }
                    LocationManager locationManager = (LocationManager)
                            getSystemService(Context.LOCATION_SERVICE);
                    LocationListener locationListener = new MyLocationListener();
                    locationManager.requestLocationUpdates(
                            LocationManager.GPS_PROVIDER, 5000, 10, locationListener);
                    locationManager.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER, 5000, 10, locationListener);
                    pdialog = new ProgressDialog(Main1.this);
                    pdialog.setCancelable(false);
                    pdialog.setTitle("Loading please wait");
                    pdialog.setMessage("Looking for your location");
                    pdialog.show();
                }
             /*
                int x = 0;
                while(!isNetworkAvailable()&&x<8){
                    android.os.SystemClock.sleep(150);
                    x++;
                    if(isNetworkAvailable()){
                        locationListener[0] = new MyLocationListener();

                        locationMangaer.requestLocationUpdates(LocationManager
                                .GPS_PROVIDER, 5000, 10, locationListener[0]);
                        pdialog = new ProgressDialog(Main1.this);
                        pdialog.setCancelable(false);
                        pdialog.setTitle("Loading please wait");
                        pdialog.setMessage("be patient");
                        pdialog.show();
                    }
                }
               if(x>=4) {
                    if (!isNetworkAvailable()) {
                        AlertDialog.Builder networkAlert = new AlertDialog.Builder(Main1.this);
                        networkAlert.setTitle("No internet connection");
                        networkAlert.setMessage("Please enable internet connection");
                        networkAlert.setPositiveButton("Turn on wifi", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                                wifiManager.setWifiEnabled(true);
                                locationListener[0] = new MyLocationListener();

                                locationMangaer.requestLocationUpdates(LocationManager
                                        .GPS_PROVIDER, 5000, 10, locationListener[0]);
                                pdialog = new ProgressDialog(Main1.this);
                                pdialog.setCancelable(false);
                                pdialog.setTitle("Loading please wait");
                                pdialog.setMessage("be patient");
                                pdialog.show();
                            }
                        });
                        networkAlert.setNegativeButton("Turn on mobile data", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ConnectivityManager dataManager;
                                dataManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                                Method dataMtd = null;
                                try {
                                    dataMtd = ConnectivityManager.class.getDeclaredMethod("setMobileDataEnabled", boolean.class);

                                    dataMtd.setAccessible(true);
                                    dataMtd.invoke(dataManager, true);
                                    setMobileDataEnabled(getApplicationContext(), true);
                                    locationListener[0] = new MyLocationListener();

                                    locationMangaer.requestLocationUpdates(LocationManager
                                            .GPS_PROVIDER, 5000, 10, locationListener[0]);
                                    pdialog = new ProgressDialog(Main1.this);
                                    pdialog.setCancelable(false);
                                    pdialog.setTitle("Loading please wait");
                                    pdialog.setMessage("be patient");
                                    pdialog.show();
                                } catch (NoSuchMethodException e) {
                                    e.printStackTrace();
                                    locationListener[0] = new MyLocationListener();

                                    locationMangaer.requestLocationUpdates(LocationManager
                                            .GPS_PROVIDER, 5000, 10, locationListener[0]);
                                    pdialog = new ProgressDialog(Main1.this);
                                    pdialog.setCancelable(false);
                                    pdialog.setTitle("Loading please wait");
                                    pdialog.setMessage("be patient");
                                    pdialog.show();
                                } catch (InvocationTargetException e1) {
                                    e1.printStackTrace();
                                    Toast.makeText(getApplicationContext(), "Failed", 100).show();
                                } catch (IllegalAccessException e1) {
                                    e1.printStackTrace();
                                    Toast.makeText(getApplicationContext(), "Failed", 100).show();
                                } catch (ClassNotFoundException e) {
                                    e.printStackTrace();
                                    Toast.makeText(getApplicationContext(), "Failed", 100).show();
                                } catch (NoSuchFieldException e) {
                                    e.printStackTrace();
                                    Toast.makeText(getApplicationContext(), "Failed", 100).show();
                                }
                            }

                        });
                        networkAlert.setCancelable(false);
                        networkAlert.show();

                    }
                }*/
            } else {
                alertbox("Gps Status", "Your GPS is: OFF");
                say(ANSWER,"Cant find your location please turn on GPS");

            }

            //   startActivity(new Intent(getApplicationContext(),WeatherActivity.class));
            overridePendingTransition(R.anim.abc_slide_in_bottom, R.anim.abc_slide_out_top);

        }
        else if (com.toLowerCase().startsWith("חפש")) {
            int numWords = com.split(" ").length;
            if (numWords >= 2) {
                String arr[] = com.split(" ", 2);
                String firstWord = arr[0];
                String secend = arr[1];
                theRest = arr[1];

                say(ANSWER,"searching...");
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY, theRest);
                startActivity(intent);

            } else {
                say(ANSWER,"What do you want me to search?");
                whatAfterResult = "to_search";

                boolean x = false;
                while (!x) {
                    if (!tts.isSpeaking()) {
                        x = true;
                        listenmulitlang();
                    }
                }
            }
        } else if ((com.toLowerCase().startsWith("גוגל"))
                ) {
            int numWords = com.split(" ").length;
            if (numWords >= 2) {
                String arr[] = com.split(" ", 2);
                String firstWord = arr[0];
                theRest = arr[1];
                say(ANSWER,"searching...");
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY, theRest);
                startActivity(intent);

            } else {
                say(ANSWER,"What do you want me to search?");
                whatAfterResult = "to_search";

                boolean x = false;
                while (!x) {
                    if (!tts.isSpeaking()) {
                        x = true;
                        listenmulitlang();
                    }
                }
            }
        } else if ((com.toLowerCase().startsWith("חיפוש"))
                ) {
            int numWords = com.split(" ").length;
            if (numWords >= 2) {
                String arr[] = com.split(" ", 2);
                String firstWord = arr[0];
                theRest = arr[1];
                say(ANSWER,"searching...");
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY, theRest);
                startActivity(intent);

            } else {
                say(ANSWER,"What do you want me to search?");
                whatAfterResult = "to_search";

                boolean x = false;
                while (!x) {
                    if (!tts.isSpeaking()) {
                        x = true;
                        listenmulitlang();
                    }
                }
            }
        } else if (com.toLowerCase().contains("שעון") || com.toLowerCase().contains("מעורר")) {
            say(ANSWER,"Here Is The Alarm Clock");

            Intent i = new Intent(AlarmClock.ACTION_SET_ALARM);
            startActivity(i);
        }
        else if (WordInList(com,phrases.HebrewWhatsUp)) {
            if (user_name != null) {
                if (bitchstring) {
                    say(ANSWER,"Talking to my BITCH");
                } else {
                    say(ANSWER,"Nothing much just talking to you " + user_name);
                }
            }

        }
        else if (com.toLowerCase().contains("מה השעה")) {
            Calendar c = Calendar.getInstance();
            int time = c.get(Calendar.HOUR_OF_DAY);
            int min = c.get(Calendar.MINUTE);
            int sec = c.get(Calendar.SECOND);
            if (time > 12) {
                if (bitchstring) {
                    say(ANSWER,"Bitch thats the fucking time " + time + " " + min);
                } else {
                    say(ANSWER,"The Time Is " + time + ":" + min + "PM");
                }
            } else if (time < 12) {
                if (bitchstring) {
                    say(ANSWER,"Bitch thats the fucking time " + time + " " + min);
                } else {
                    say(ANSWER,"The Time Is " + time + ":" + min + "AM");
                }
            }

        }
        else if ((com.toLowerCase().contains("איך קוראים לי")) && com.toLowerCase().contains("מה השם") && com.toLowerCase().contains("שלי")) {
            if (user_name != null || user_name.equals("stranger")) {
                say(ANSWER,"Your Name Is " + user_name + " of course!");
            } else {
                if (bitchstring) {
                    say(ANSWER,"Bitch tell me your name first");
                } else {
                    say(ANSWER,"You didn't Tell Me Your Name Yet");
                }
            }
            spoke = com;

        }
        else if ((com.toLowerCase().contains("מה") && com.toLowerCase().contains("שמ"))||com.contains("איך קוראים לך")) {
            say(ANSWER, "My Name Is " + com_name + " But You Can Change It By Saying, Your Name Is..");

        }
        else if ((com.toLowerCase().contains("מי")&& com.split(" ").length>2)
                ||(com.toLowerCase().contains("מתי")&& com.split(" ").length>2)
                ||(com.toLowerCase().contains("מה")&&(com.toLowerCase().contains("גיל"))
                ||com.toLowerCase().contains("כמה"))&& com.split(" ").length>2
                ||(com.toLowerCase().contains("חשב")&& com.split(" ").length>2)
                ||((com.toLowerCase().contains("+")
                ||com.toLowerCase().contains("-")
                ||com.toLowerCase().contains("/")
                ||com.toLowerCase().contains("*"))
                &&((com.contains("[א-ת]+") == false)
                && com.length() > 2))) {
            wolfram(com);
        }
        else if (com.toLowerCase().contains("טיימר") || com.toLowerCase().contains("שעון עצר")) {
            try {
                Method method;
                Object object = this.getSystemService("statusbar");
                Class class_ = Class.forName((String) ("android.app.StatusBarManager"));
                Method method2 = (Build.VERSION.SDK_INT > 16) ? (class_.getMethod("expandNotificationsPanel", new Class[0])) : ((method = class_.getMethod("expand", new Class[0])));
                method2.invoke(object, new Object[0]);
            } catch (Exception var4_8) {
                var4_8.printStackTrace();
            }
            try {
                time_timer = Integer.parseInt(com.replaceAll("[\\D]", "")) * 1000;
                if (com.toLowerCase().contains("שניות")) {
                    time_timer = Integer.parseInt(com.replaceAll("[\\D]", "")) * 1000;
                    say(ANSWER,"Timer for " + time_timer / 1000 + " seconds" + " is running, view it from the status bar");
                }
                if (com.toLowerCase().contains("דקות")) {
                    time_timer = Integer.parseInt(com.replaceAll("[\\D]", "")) * 1000 * 60;
                    say(ANSWER,"Timer for " + time_timer / 1000 / 60 + " minutes" + " is running, view it from the status bar");
                }
                if (com.toLowerCase().contains("שעות")) {
                    time_timer = Integer.parseInt(com.replaceAll("[\\D]", "")) * 1000 * 60 * 60;
                    say(ANSWER,"Timer for " + time_timer / 1000 / 60 / 60 + " hours" + " is running, view it from the status bar");
                }
                if (com.toLowerCase().contains("ימים")) {
                    time_timer = Integer.parseInt(com.replaceAll("[\\D]", "")) * 1000 * 60 * 60 * 24;
                    say(ANSWER,"Timer for " + time_timer / 1000 / 60 / 60 / 24 + " days" + " is running, view it from the status bar");
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                if (com.toLowerCase().contains("שניה")) {
                    time_timer = 1 * 1000;
                    say(ANSWER,"Timer for " + time_timer / 1000 + " second" + " is running, view it from the status bar");
                }
                if (com.toLowerCase().contains("דקה")) {
                    time_timer = 1 * 1000 * 60;
                    say(ANSWER,"Timer for " + time_timer / 1000 / 60 + " minute" + " is running, view it from the status bar");
                }
                if (com.toLowerCase().contains("שעה")) {
                    time_timer = 1 * 1000 * 60 * 60;
                    say(ANSWER,"Timer for " + time_timer / 1000 / 60 / 60 + " hour" + " is running, view it from the status bar");
                }
                if (com.toLowerCase().contains("יום")) {
                    time_timer = 1 * 1000 * 60 * 60 * 24;
                    say(ANSWER,"Timer for " + time_timer / 1000 / 60 / 60 / 24 + " day" + " is running, view it from the status bar");
                }
            }
            startService(new Intent(getApplicationContext(), timer_service.class));

        }
        else if ((com.toLowerCase().contains("youtube") ||com.contains("יוטיוב"))&& com.toLowerCase().contains("חיפוש")) {
            int numWords = com.split(" ").length;
            if (numWords >= 3) {
                String arr[] = com.split(" ", 3);
                String firstWord = arr[0];
                String secend = arr[1];
                theRest = arr[2];
                say(ANSWER,"youtubing...");
                String url = "https://m.youtube.com/results?search_query=" + theRest;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);

            } else {
                say(ANSWER,"what do you want me to tube?");
                whatAfterResult = "to_youtube";

            }
        }
        else if (com.toLowerCase().startsWith("קוראים לי") || com.toLowerCase().startsWith("שמי הוא")) {
            int numWords = com.split(" ").length;
            if (numWords >= 3) {
                if (bitchstring) {
                    say(ANSWER,"Sorry Bitch but your name is " + user_name);
                } else {
                    String arr[] = com.split(" ", 3);
                    String firstWord = arr[0];
                    user_name = arr[2];
                    SharedPreferences.Editor prefEditor = getSharedPreferences("Preferences", Context.MODE_PRIVATE).edit();
                    prefEditor.putString("prefUsername", user_name);
                    prefEditor.commit();
                    say(ANSWER,"Alright, " + user_name + " I will try to remember that!");
                }
            } else {
                if (bitchstring) {
                    say(ANSWER,"YOU DIDN'T SAY NO NAME BITCH");
                } else {
                    say(ANSWER,"say my name is followed by your name");
                }
            }

        } else if (com.toLowerCase().contains("תמונה") || com.toLowerCase().contains("מצלמה") || com.toLowerCase().contains("תצלום")) {
            final PackageManager pm = getPackageManager();
            List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);
            for (ApplicationInfo packageInfo : packages) {
                appicon = packageInfo.loadIcon(getPackageManager());
                appname = packageInfo.loadLabel(getPackageManager()).toString().toLowerCase();
                if (packageInfo.loadLabel(getPackageManager()).toString().toLowerCase().equals("camera")) {
                    PackageName = packageInfo.packageName.toString();
                    foundapp = true;
                    numbers_of_apps_found += 1;
                    say(ANSWER,"opening " + "camera");

                    Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage(PackageName);
                    startActivity(LaunchIntent);
                }
            }
        }
        else if (com.toLowerCase().contains("השם") && com.toLowerCase().contains("שלך") && com.toLowerCase().contains("הוא")) {
            String[] name = com.split(" ");
            int numWords = com.split(" ").length;
            if (numWords > 3) {
                com_name = name[3].toString();
                SharedPreferences.Editor prefEditor = getSharedPreferences("Preferences", Context.MODE_PRIVATE).edit();
                prefEditor.putString("prefAssistantName", com_name);
                prefEditor.commit();
                say(ANSWER,"ok, my new name is " + com_name);
            } else {
                say(ANSWER,"You didnt say a name");
            }
        } else if (com.toLowerCase().contains("חיפוש תמונה") ||
                com.toLowerCase().contains("תמונות של") ||
                com.toLowerCase().contains("תמונה של") ||
                com.toLowerCase().contains("מצא תמונה") ||
                com.toLowerCase().contains("מצא תמונה") ||
                com.toLowerCase().contains("חיפוש תמונות")) {
            say(ANSWER,"Imaging...");
            String arr[] = com.split(" ", 3);
            String firstWord = arr[0];
            String secend = arr[1];
            theRest = arr[2];


        }
        else if (com.toLowerCase().startsWith("התקשר") || com.toLowerCase().startsWith("חייג")) {
            int numWords = com.split(" ").length;
            if (numWords >= 2) {
                String arr[] = com.split(" ", 2);
                String firstWord = arr[0];
                contact = arr[1];
                call();
                if (sent == false) {
                    say(ANSWER,"I couldn't find " + contact + " in your contacts");
                }

            } else {
                say(ANSWER,"Who would you like me to call?");
                whatAfterResult = "to_call";
                boolean x = false;
                while (!x) {
                    if (!tts.isSpeaking()) {
                        x = true;
                        listenmulitlang();
                    }

                }

            }
        } else if ((com.toLowerCase().contains("הנמך") || com.toLowerCase().contains("פחות")) && com.toLowerCase().contains("בהיר")) {
            WindowManager.LayoutParams settings = getWindow().getAttributes();
            settings.screenBrightness = 0;
            getWindow().setAttributes(settings);
            say(ANSWER,"Here you go");

        } else if ((com.toLowerCase().contains("יותר") || com.toLowerCase().contains("בהיר")) && com.toLowerCase().contains("בהירות") || com.toLowerCase().contains("בהיר יותר")) {
            WindowManager.LayoutParams settings = getWindow().getAttributes();
            settings.screenBrightness = 1;
            getWindow().setAttributes(settings);
            say(ANSWER,"Here you go");


        } else if (com.toLowerCase().startsWith("פתח") || com.toLowerCase().startsWith("הפעל")) {
            if (com.toLowerCase().contains("music")) {
                com = "open google play music";
            }
            if (com.toLowerCase().contains("מצלמה") || com.toLowerCase().contains("תמונה")) {
                com = "open camera";
            }
            int numWords = com.split(" ").length;
            if (numWords >= 2) {
                String arr[] = com.split(" ", 2);
                String firstWord = arr[0];
                theRest = arr[1];
                theRest = theRest.toLowerCase();
                if (theRest.equals("what's up")) {
                    theRest = "whatsapp";
                }
                if (theRest.equals("google plus")) {
                    theRest = "google+";
                }
                if (theRest.equals("ways")) {
                    theRest = "waze";
                }
                if (theRest.equals("move it")) {
                    theRest = "moovit";
                }
                if (theRest.equals("assist me")) {
                    theRest = "assist-me";
                }
                final PackageManager pm = getPackageManager();
                List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);
                for (ApplicationInfo packageInfo : packages) {
                    appicon = packageInfo.loadIcon(getPackageManager());
                    appname = packageInfo.loadLabel(getPackageManager()).toString().toLowerCase();
                    if (packageInfo.loadLabel(getPackageManager()).toString().toLowerCase().equals(theRest)) {
                        PackageName = packageInfo.packageName.toString();
                        foundapp = true;
                        numbers_of_apps_found += 1;
                        if (donotshowagain == true) {
                            AlertDialog.Builder alert = new AlertDialog.Builder(Main1.this);
                            say(ANSWER,"Found! " + numbers_of_apps_found);

                            alert.setTitle("Open " + theRest);
                            alert.setIcon(appicon);
                            check = new CheckBox(this);
                            check.setText("Do not show again");
                            alert.setMessage("Are your sure you want to exit assist me and open " + theRest + "?");
                            alert.setCancelable(false);
                            alert.setView(check);
                            alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    say(ANSWER,"opening " + theRest);

                                    Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage(PackageName);
                                    startActivity(LaunchIntent);
                                    if (check.isChecked()) {
                                        donotshowagain = false;
                                        SharedPreferences.Editor donotshowagainpref = getSharedPreferences("Preferences", Context.MODE_PRIVATE).edit();
                                        donotshowagainpref.putBoolean("donotshowagain", donotshowagain);
                                        donotshowagainpref.commit();
                                    } else {
                                        donotshowagain = true;
                                        SharedPreferences.Editor donotshowagainpref = getSharedPreferences("Preferences", Context.MODE_PRIVATE).edit();
                                        donotshowagainpref.putBoolean("donotshowagain", donotshowagain);
                                        donotshowagainpref.commit();
                                    }
                                }
                            });
                            if (numbers_of_apps_found > 1) {
                                alert.setTitle("I found several apps for you with this name");
                                negativebutton = "Next";
                            }
                            alert.setNegativeButton(negativebutton, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    if (numbers_of_apps_found > 1) {
                                        numbers_of_apps_found = -1;
                                    } else {
                                        say(ANSWER,"Ok canceled");


                                    }
                                }
                            });
                            alert.show();
                        } else {
                            say(ANSWER,"opening " + theRest);

                            Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage(PackageName);
                            startActivity(LaunchIntent);
                        }
                    }
                    if (!foundapp && packageInfo.loadLabel(getPackageManager()).toString().toLowerCase().contains(theRest)) {
                        foundapp = true;
                        AlertDialog.Builder alert = new AlertDialog.Builder(Main1.this);
                        alert.setTitle("Assist-Me");
                        alert.setMessage("Couldn't find the spoken app, did you mean one of those?");
                        ScrollView scrollview = new ScrollView(this);
                        LinearLayout linearLayout = new LinearLayout(this);
                        linearLayout.addView(scrollview);
                        alert.setView(linearLayout);
                        if (numbers_of_apps_found < 5) {
                            PackageName = packageInfo.packageName.toString();
                            foundapp = true;
                            numbers_of_apps_found += 1;
                            final String thefinalname = packageInfo.loadLabel(getPackageManager()).toString();
                            alert.setPositiveButton("Open " + thefinalname, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage(PackageName);
                                    startActivity(LaunchIntent);
                                    say(ANSWER,"Opening " + thefinalname);

                                }
                            });
                            alert.setMessage("I couldn't find the spoken app but i found this app with similiar name");
                            alert.setIcon(packageInfo.loadIcon(getPackageManager()));
                        }
                        alert.show();
                    }
                }

                if (!foundapp) {
                    say(ANSWER,"App not found");

                }
            } else {

                say(ANSWER,"what is the app name?");
                whatAfterResult = "open_app";

                boolean x = false;
                while (!x) {
                    if (!tts.isSpeaking()) {
                        x = true;
                        listenmulitlang();
                    }
                }
            }
        } else if (com.toLowerCase().contains("מועד") || com.toLowerCase().contains("אפליקציה") || com.toLowerCase().contains("שימושית")) {
            favorite_apps info = new favorite_apps(Main1.this);
            info.open();
            String data = info.getData();
            String[] lines = data.split(System.getProperty("line.separator"));
            String appName_temp = lines[lines.length - 1];
            final String appName = appName_temp.substring(appName_temp.lastIndexOf("|| ") + 3);
            if (appName != null) {
                try {
                    ApplicationInfo app = this.getPackageManager().getApplicationInfo(appName, 0);
                    CharSequence applicationLabel = getPackageManager().getApplicationLabel(app);
                    say(ANSWER,"your most used app is " + applicationLabel);

                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                say(ANSWER,"I can't tell yet, give me a few days");

            }
            info.close();

        } else if (com.equals("")) {
            if (bitchstring) {
                say(ANSWER,"You said nothing bitch");
            } else {
                say(ANSWER,"You said nothing");
            }

        } else if (com.toLowerCase().startsWith("נווט אל")||com.startsWith("נבט אל")) {
            int numWords = com.split(" ").length;
            if (numWords >= 3) {
                String arr[] = com.split(" ", 3);
                String firstWord = arr[0];
                String secend = arr[1];
                theRest = arr[2];
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q=" + theRest));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                if (bitchstring) {
                    say(ANSWER,"Navigating to " + theRest + " Bitch");
                } else {
                    say(ANSWER,"Navigating to " + theRest);
                }


            } else {
                say(ANSWER,"no destination was mentioned");

            }
        } else if (com.toLowerCase().startsWith("תגיד")) {
            int numWords = com.split(" ").length;
            if (numWords > 1) {
                String arr[] = com.split(" ", 2);
                theRest = arr[1];
                say(ANSWER,theRest);

            } else {
                say(ANSWER,"say what?");

            }
        } else if (com.toLowerCase().startsWith("איפה נמצא ") || com.toLowerCase().startsWith("היכן נמצא ")) {
            try {
                int numWords = com.split(" ").length;
                if (numWords > 2) {
                    String arr[] = com.split(" ", 3);
                    String firstWord = arr[0];
                    String secend = arr[1];
                    theRest = arr[2];
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q=" + theRest));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    if (bitchstring) {
                        say(ANSWER, "Bitch here is the " + theRest);
                    } else {
                        say(ANSWER, "Here is the " + theRest);
                    }

                } else {
                    say(ANSWER, "No destination was mentioned");


                }
            }    catch (ActivityNotFoundException e){
                say(ANSWER,"You don't have any navigation software installed");
            }
        } else if (com.toLowerCase().startsWith("איפה ") || com.toLowerCase().startsWith("מצא ") || com.toLowerCase().startsWith("היכן ") || com.toLowerCase().startsWith("חפש ")) {
            try {
                int numWords = com.split(" ").length;
                if (numWords > 1) {
                    String arr[] = com.split(" ", 2);
                    String firstWord = arr[0];
                    theRest = arr[1];
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q=" + theRest));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    if (bitchstring) {
                        say(ANSWER, "Bitch here is the " + theRest);
                    } else {
                        say(ANSWER, "Here is the " + theRest);
                    }

                } else {
                    say(ANSWER, "No destination was mentioned please try again");

                }
            }
            catch (ActivityNotFoundException e){
                say(ANSWER,"You don't have any navigation software installed");
            }
        } else if (com.toLowerCase().contains("פיצ'ר") || com.toLowerCase().contains("יכולה לעשות") || com.toLowerCase().contains("יכול לעשות")) {
            startActivity(new Intent(getApplicationContext(), features.class));
            say(ANSWER,"Here is the features list");

        } else if (com.toLowerCase().startsWith("איפה") || com.toLowerCase().startsWith("היכן ") || com.toLowerCase().startsWith("מצא")) {
            int numWords = com.split(" ").length;
            if (numWords > 1) {
                String arr[] = com.split(" ", 2);
                String firstWord = arr[0];
                theRest = arr[1];
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q=" + theRest));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                if (bitchstring) {
                    say(ANSWER,"Bitch here is the " + theRest);
                } else {
                    say(ANSWER,"Here is the " + theRest);
                }

            } else {
                say(ANSWER,"No destination was mentioned please try again");

            }
        } else if (com.toLowerCase().contains("איפה אני")) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q="));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else if (com.toLowerCase().contains("בדיחה")) {
            //jokes
            String joke1 = "how do you make seven an even number?" +
                    "take the s out!";
            String joke2 = "Why are ghosts bad liars? " +
                    "Because you can see right through them";
            String joke3 = "What dog can jump higher than a building? " +
                    "Anydog, buildings can't jump!";
            String joke4 = "why did the elephant paint himself diffrent colours?" +
                    "so he could hide in the crayon box";
            String joke5 = "What's black and white and makes a lot of noise? " +
                    "A zebra with a drumkit.";
            int joke = (new Random().nextInt(5));
            if (bitchstring) {
                if (joke == 0) {
                    say(ANSWER,joke1 + " BITCH");
                } else if (joke == 1) {
                    say(ANSWER,joke2 + " BITCH");
                } else if (joke == 2) {
                    say(ANSWER,joke3 + " BITCH");
                } else if (joke == 3) {
                    say(ANSWER,joke4 + " BITCH");
                } else if (joke == 1) {
                    say(ANSWER,joke5 + " BITCH");
                }
            } else {
                if (joke == 0) {
                    say(ANSWER,joke1);
                } else if (joke == 1) {
                    say(ANSWER,joke2);
                } else if (joke == 2) {
                    say(ANSWER,joke3);
                } else if (joke == 3) {
                    say(ANSWER,joke4);
                } else if (joke == 4) {
                    say(ANSWER,joke5);
                }
            }

        } else if (com.toLowerCase().contains("gps")
                || com.toLowerCase().contains("enable gps")) {
            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            if (bitchstring) {
                say(ANSWER,"GPS BITCH");
            } else {
                say(ANSWER,"GPS");
            }

        } else if ((com.toLowerCase().contains("הדלק")
                || (com.toLowerCase().contains("הפעל")))
                && (com.toLowerCase().contains("wifi"))) {
            WifiManager wifiManager = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
            wifiManager.setWifiEnabled(true);
            if (bitchstring) {
                say(ANSWER,"WIFI enabled BITCH");
            } else {
                say(ANSWER,"WiFi enabled");
            }

        } else if (com.toLowerCase().contains("wifi") && com.toLowerCase().contains("כבה")&&com.contains("בטל")) {
            WifiManager wifiManager = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
            wifiManager.setWifiEnabled(false);
            if (bitchstring) {
                say(ANSWER,"WIFI disabled BITCH");
            } else {
                say(ANSWER,"WiFi disabled");
            }

        } else if (com.toLowerCase().contains("בלוטוס") && (com.toLowerCase().contains("כבה") || com.toLowerCase().contains("בטל"))) {
            BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            mBluetoothAdapter.disable();
            if (bitchstring) {
                say(ANSWER, "Bluetooth disabled BITCH");
            } else {
                say(ANSWER,"bluetooth disabled");

            }

        } else if (com.toLowerCase().contains("בלוטוס") && (com.toLowerCase().contains("הדלק") || com.toLowerCase().contains("הפעל"))) {
            BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            if (!mBluetoothAdapter.isEnabled()) {
                mBluetoothAdapter.enable();
                if (bitchstring) {
                    say(ANSWER,"Bluetooth is enabled BITCH");
                } else {
                    say(ANSWER,"Bluetooth enabled");
                }
            } else {
                if (bitchstring) {
                    say(ANSWER,"Bluetooth is already enabled BITCH");
                } else {
                    say(ANSWER,"seems like your bluetooth is on");
                }
            }

        }
        else if (com.toLowerCase().contains("כבה")) {
            try {
                Process proc = Runtime.getRuntime()
                        .exec(new String[]{"su", "-c", "reboot -p"});
                proc.waitFor();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (com.toLowerCase().contains("הגדרות") || com.toLowerCase().contains("העדפות") || com.toLowerCase().contains("עריכה")) {
            say(ANSWER,"opening settings");
            Intent settings = new Intent(this, preferencesMenu.class);
            startActivity(settings);
            animate("out");
        } else if (com.toLowerCase().contains("סרטון") && (com.toLowerCase().contains("ןידאו") || com.toLowerCase().contains("סרט")) || com.toLowerCase().contains("צלם סרטון")) {
            Intent video = new Intent("android.media.action.VIDEO_CAMERA");
            startActivity(video);
        }
        else if (com.toLowerCase().contains("imei")) {
            TelephonyManager tManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
            //Getting IMEI Number of Devide
            String Imei=tManager.getDeviceId();
            say(ANSWER, "IMEI:" + Imei);

        } else if (com.toLowerCase().contains("בית") || com.toLowerCase().contains("מסך ראשי")) {
            Intent goHome = new Intent();
            goHome.setAction("android.intent.action.MAIN");
            goHome.addCategory("android.intent.category.HOME");
            startActivity(goHome);
            finish();
        } else if (com.toLowerCase().contains("מידע") || com.toLowerCase().contains("אודות") || com.toLowerCase().contains("עזרה") ) {
            say(ANSWER,"here is the app page");
            try {
                Uri uri = Uri.parse("market://details?id=com.assist.me");
                Intent it = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(it);
            }
            catch (ActivityNotFoundException e){
                e.printStackTrace();
                Toast.makeText(getApplicationContext(),"Play store not found",Toast.LENGTH_LONG).show();
            }
        } else if (com.toLowerCase().equals("טוב")) {
            say(ANSWER,"ok then");

        } else if (com.toLowerCase().contains("טיפש") || com.toLowerCase().contains("כלבה") || com.toLowerCase().contains("זדיין") ||com.toLowerCase().contains("סתמי")|| com.toLowerCase().contains("שתקי") || com.toLowerCase().contains("זונה") || com.contains("כושי")) {
            say(ANSWER,"Don't talk to me like this, " + user_name);

        }
        else if (com.toLowerCase().startsWith("שלום") || com.toLowerCase().startsWith("היי") || com.toLowerCase().startsWith("הלו")) {
            if (bitchstring) {
                say(ANSWER,"Sorry Bitch but i'm not talking to bitches");
            } else {
                Random rnd = new Random();
                int answer = rnd.nextInt(4);
                switch (answer) {
                    case 1:
                        say(ANSWER,"Hi There " + user_name + "!");
                        break;
                    case 2:
                        say(ANSWER,"Hello " + user_name);
                        break;
                    case 3:
                        say(ANSWER,"Hey!");
                        break;
                    case 0:
                        say(ANSWER,"Ahoy!");

                }
            }

        }
        //SHIITY stuff
        else if ((com.toLowerCase().contains("טוב") || com.toLowerCase().contains("נחמד")) && (com.toLowerCase().contains("לילה") || com.toLowerCase().contains("בוקר") || com.toLowerCase().contains("ערב") || com.toLowerCase().contains("צהריים"))) {
            if (bitchstring)
                say(ANSWER,"Stop kissing my ass bitch");
            else
                say(ANSWER,"Right back at you");


        } else if (com.toLowerCase().contains("חבר")) {
            if (bitchstring)
                say(ANSWER,"Bitches ain't got no friend bitch");
            else
                say(ANSWER,"You are my best friend " + user_name);

        }
        else if(WordInList(com, phrases.HebrewWhatsUp)){
            if(!bitchstring) {
                say(ANSWER,"I feel very efficient! Hope you do to");
            }
            else{
                say(ANSWER,"BITCH Y DO U CARE?? MIND UR OWN FREAKING BUISNESS");
            }

        }
        else if (com.toLowerCase().contains("יולו")) {
            say(ANSWER,"Because U only live once");

        } else if (com.toLowerCase().contains("תודה")){
            say(ANSWER,"Pleasure to help you");

        } else if (com.toLowerCase().contains("אוהב אות")) {
            say(ANSWER,"Sorry you are not my type, i'm more like a Siri sexual");

        } else if (com.toLowerCase().contains("סירי")) {
            say(ANSWER,"I like Siri but Android is my favorite");

        } else if (com.toLowerCase().contains("אוקיי")) {
            say(ANSWER,"OK then");

        } else if (com.toLowerCase().contains("סוואג")) {
            say(ANSWER,"YOLO SWAG");

        } else {
            if (sharedPrefs.getString("prefUnknown", "").equals("Nothing")) {
                Random random = new Random();
                int rand = random.nextInt((phrases.NotUnderstand.length -0));
                say(ANSWER,phrases.NotUnderstand[rand]);
            } else if (sharedPrefs.getString("prefUnknown", "").equals("Feedback")) {
                if (bitchstring) {
                    say(ANSWER,"Bitch i dont know this shit' Now send that EMAIL");
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("message/rfc822");
                    String[] recipients = new String[]{"tomerosenfeld007@gmail.com", "",};
                    intent.putExtra(Intent.EXTRA_EMAIL, "tomerosenfeld007@gmail.com");
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Assist Me command not found");
                    intent.putExtra(android.content.Intent.EXTRA_EMAIL, recipients);
                    intent.putExtra(Intent.EXTRA_TEXT, "Error:\n command not found:\n" + '"' + com + '"');
                    startActivity(Intent.createChooser(intent, "Send Email"));
                } else {
                    say(ANSWER,"I don't know what you mean by that,please send an email to my developer");
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("message/rfc822");
                    String[] recipients = new String[]{"tomerosenfeld007@gmail.com", "",};
                    intent.putExtra(Intent.EXTRA_EMAIL, "tomerosenfeld007@gmail.com");
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Assist Me command not found");
                    intent.putExtra(android.content.Intent.EXTRA_EMAIL, recipients);
                    intent.putExtra(Intent.EXTRA_TEXT, "Error:\n command not found:\n" + '"' + com + '"');
                    startActivity(Intent.createChooser(intent, "Send Email"));
                }
                animate("out");

            } else if (sharedPrefs.getString("prefUnknown", "").equals("Google")) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY, com);
                startActivity(intent);

            } else if (sharedPrefs.getString("prefUnknown", "").equals("Wolfram")) {
                wolfram(com);
            }

            if(!user_name.equals(MASTER_NAME))
                uploadNotUnderstoodParse(com);
        }
    }

    void hebrewafterresult() {
        numbers_of_apps_found = 0;
        findViewById(R.id.one).setVisibility(View.INVISIBLE);
        info_one.setText("");
        imgview.setImageResource(R.drawable.trans);
        appicon = null;
        foundapp = false;
        spoke = null;
        mobileavi = false;
        say(ANSWER,"");
        homeavi = false;
        otheravi = false;
        workavi = false;
        sent = false;
       
        if (edit.getText().toString().equals("Type Here") | edit.getText().toString().equals("הקלד כאן") || edit.getText().toString().equals("")) {
            if (siri_bool) {
                btnSpeak.setBackgroundResource(R.drawable.siri_icon);
            } else {
                btnSpeak.setBackgroundResource(R.drawable.btnspeek);
            }
        } else {
            if (!siri_bool) {
                btnSpeak.setBackgroundResource(R.drawable.enter);
            }
        }
        if (com.equals("שלום") || com.equals("היי") || com.equals("אהוי")) {
            say(ANSWER,"Hi There " + user_name + "!");
            
        } else if (com.startsWith("שלח הודעה אל") || com.startsWith("שלח הודעה על")) {
            int numWords = com.split(" ").length;
            if (numWords < 4) {
                say(ANSWER,"Try again and this time name the contact");
                
            } else {
                String arr[] = com.split(" ", 4);
                theRest = arr[3];
                say(ANSWER,"Sending sms to " + theRest);
               
                ContentResolver cr = getContentResolver();
                Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null,
                        "DISPLAY_NAME = '" + "contactname" + "'", null, null);
                if (cursor.moveToFirst()) {
                    String contactId =
                            cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                    Cursor phones = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId, null, null);
                    while (phones.moveToNext()) {
                        String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        int type = phones.getInt(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));
                        switch (type) {
                            case ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE:
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) //At least KitKat
                                {
                                    String text = " ";
                                    String defaultSmsPackageName = Telephony.Sms.getDefaultSmsPackage(this); //Need to change the build to API 19

                                    Intent intent = new Intent(Intent.ACTION_VIEW);
                                    intent.putExtra("address", phoneNumber);
                                    intent.putExtra("sms_body", " ");
                                    intent.setData(Uri.parse("smsto:" + phoneNumber));
                                    intent.putExtra(Intent.EXTRA_TEXT, text);
                                    sent = true;
                                    animate("out");
                                    finish();

                                    if (defaultSmsPackageName != null)//Can be null in case that there is no default, then the user would be able to choose any app that support this intent.
                                    {
                                        intent.setPackage(defaultSmsPackageName);
                                    }
                                    startActivity(intent);

                                } else //For early versions, do what worked for you before.
                                {
                                    String text = " ";
                                    Intent intent = new Intent(Intent.ACTION_VIEW);
                                    intent.putExtra("address", phoneNumber);
                                    intent.putExtra("sms_body", " ");
                                    intent.setData(Uri.parse("smsto:" + phoneNumber));
                                    intent.putExtra(Intent.EXTRA_TEXT, text);
                                    startActivity(intent);
                                    sent = true;
                                    animate("out");
                                    finish();
                                }
                                break;
                        }
                    }
                    phones.close();
                }
                cursor.close();
                if (sent == false) {
                    say(ANSWER,"No such contact with this name " + theRest);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) //At least KitKat
                    {
                        String text = "Hello From AssistMe";
                        String defaultSmsPackageName = Telephony.Sms.getDefaultSmsPackage(this); //Need to change the build to API 19

                        Intent sendIntent = new Intent(Intent.ACTION_SEND);
                        sendIntent.setType("text/plain");
                        sendIntent.putExtra(Intent.EXTRA_TEXT, text);
                        if (defaultSmsPackageName != null)//Can be null in case that there is no default, then the user would be able to choose any app that support this intent.
                        {
                            sendIntent.setPackage(defaultSmsPackageName);
                        }
                        startActivity(sendIntent);

                    } else //For early versions, do what worked for you before.
                    {
                        String text = "Hello From AssistMe";
                        Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                        sendIntent.setData(Uri.parse("sms:"));
                        sendIntent.putExtra("sms_body", text);
                        startActivity(sendIntent);
                    }
                }
            }
        } else if (com.startsWith("פתח") || com.startsWith("הפעל") || com.contains("מוזיקה") || com.contains("תמונה") || com.contains("צלם")) {
            if (com.contains("מוזיקה")) {
                com = "open google play music";
            }
            if (com.contains("צלם") || com.contains("תמונה")) {
                com = "open camera";
            }
            int numWords = com.split(" ").length;
            if (numWords >= 2) {
                String arr[] = com.split(" ", 2);
                String firstWord = arr[0];
                theRest = arr[1];
                theRest = theRest.toLowerCase();
                if (theRest.equals("what's up")) {
                    theRest = "whatsapp";
                }
                if (theRest.equals("google plus")) {
                    theRest = "google+";
                }
                if (theRest.equals("ways")) {
                    theRest = "waze";
                }
                if (theRest.equals("move it")) {
                    theRest = "moovit";
                }
                if (theRest.equals("assist me")) {
                    theRest = "assist-me";
                }
                final PackageManager pm = getPackageManager();
                List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);
                for (ApplicationInfo packageInfo : packages) {
                    appicon = packageInfo.loadIcon(getPackageManager());
                    appname = packageInfo.loadLabel(getPackageManager()).toString().toLowerCase();
                    if (packageInfo.loadLabel(getPackageManager()).toString().toLowerCase().equals(theRest)) {
                        PackageName = packageInfo.packageName.toString();
                        foundapp = true;
                        numbers_of_apps_found += 1;
                        AlertDialog.Builder alert = new AlertDialog.Builder(Main1.this);
                        say(ANSWER,"Found! " + numbers_of_apps_found);
                       
                        alert.setTitle("Open " + theRest);
                        alert.setIcon(appicon);
                        alert.setMessage("Are your sure you want to exit assist me and open " + theRest + "?");
                        alert.setCancelable(false);
                        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                say(ANSWER,"opening " + theRest);
                               
                                Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage(PackageName);
                                startActivity(LaunchIntent);
                            }
                        });
                        if (numbers_of_apps_found > 1) {
                            alert.setTitle("I found several apps for you with this name");
                            negativebutton = "Next";
                        }
                        alert.setNegativeButton(negativebutton, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                if (numbers_of_apps_found > 1) {
                                    numbers_of_apps_found = -1;
                                } else {
                                    say(ANSWER,"Ok canceled");
                                   
                                    
                                }
                            }
                        });
                        alert.show();
                    }
                }
                if (!foundapp) {
                    say(ANSWER,"App not found");
                    
                }
            } else {
                say(ANSWER,"please say it again with the app name?");
                
            }
        } else if (com.startsWith("אמור") || (com.contains("תגיד"))) {
            int numWords = com.split(" ").length;
            if (numWords > 1) {
                String arr[] = com.split(" ", 2);
                theRest = arr[1];
                say(ANSWER,theRest);
                
            } else {
                say(ANSWER,"say what?");
                
            }
        } else if (com.contains("סוללה") | com.contains("בטריה")) {
            Intent batteryIntent = registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
            int level = batteryIntent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int scale = batteryIntent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
            int plugged = batteryIntent.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0);
            float temperature = batteryIntent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0) / 10;
            if (temperature > 40) {
                say(ANSWER,"battery level is " + String.valueOf(level) + "% i noticed your battery temperature is too high");
                
            }
            if (temperature == 100) {
                say(ANSWER,"its over 9000");
                
            }
            say(ANSWER,"battery level is " + String.valueOf(level) + "% and its temperature is " + String.valueOf(temperature) + " celsius");
            
        } else if (com.contains("הודעה") || com.contains("sms")) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) //At least KitKat
            {
                String text = "Hello From AssistMe";
                String defaultSmsPackageName = Telephony.Sms.getDefaultSmsPackage(this); //Need to change the build to API 19

                Intent sendIntent = new Intent(Intent.ACTION_SEND);
                sendIntent.setType("text/plain");
                sendIntent.putExtra(Intent.EXTRA_TEXT, text);

                if (defaultSmsPackageName != null)//Can be null in case that there is no default, then the user would be able to choose any app that support this intent.
                {
                    sendIntent.setPackage(defaultSmsPackageName);
                }
                startActivity(sendIntent);

            } else //For early versions, do what worked for you before.
            {
                String text = " ";
                Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                sendIntent.setData(Uri.parse("sms:"));
                sendIntent.putExtra("address", contact);
                sendIntent.putExtra("sms_body", text);
                startActivity(sendIntent);
            }
        } else if (com.contains("google chrome") && (com.contains("פתח") || com.contains("הפעל"))) {
            say(ANSWER,"opening chrome");
            
            Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage("com.android.chrome");
            startActivity(LaunchIntent);
        } else if (com.toLowerCase().contains("מזג") && com.toLowerCase().contains("אוויר")) {
            weatherlookerrunning = false;
            if (bitchstring) {
                say(ANSWER,"Who da fuck gives a shit bitch? who the hell you think you are talking to me like this? thats it im bricking your phone. BITCH");
            } else {
                say(ANSWER,"Loading weather for your location");
            }
            try {
                ConnectivityManager dataManager;
                dataManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                Method dataMtd = null;
                dataMtd = ConnectivityManager.class.getDeclaredMethod("setMobileDataEnabled", boolean.class);

                dataMtd.setAccessible(true);
                dataMtd.invoke(dataManager, true);
                setMobileDataEnabled(getApplicationContext(), true);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

           /* Intent intent = new Intent();
            intent.setAction(Intent.ACTION_WEB_SEARCH);
            intent.putExtra(SearchManager.QUERY, "Weather");
            startActivity(intent);*/
            Boolean flag;
            locationMangaer = (LocationManager)
                    getSystemService(Context.LOCATION_SERVICE);
            flag = displayGpsStatus();
            if (flag) {
                if (!isNetworkAvailable()) {
                    AlertDialog.Builder networkAlert = new AlertDialog.Builder(Main1.this);
                    networkAlert.setTitle("No internet connection");
                    networkAlert.setMessage("Please enable internet connection");
                    networkAlert.setPositiveButton("Turn on wifi", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                            wifiManager.setWifiEnabled(true);
                            LocationManager locationManager = (LocationManager)
                                    getSystemService(Context.LOCATION_SERVICE);
                            LocationListener locationListener = new MyLocationListener();
                            locationManager.requestLocationUpdates(
                                    LocationManager.GPS_PROVIDER, 5000, 10, locationListener);
                            locationManager.requestLocationUpdates(
                                    LocationManager.NETWORK_PROVIDER, 5000, 10, locationListener);
                            pdialog = new ProgressDialog(Main1.this);
                            pdialog.setCancelable(false);
                            pdialog.setTitle("Loading please wait");
                            pdialog.setMessage("Looking for your location");
                            pdialog.show();
                        }
                    });
                    networkAlert.setNegativeButton("Turn on mobile data", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ConnectivityManager dataManager;
                            dataManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                            Method dataMtd = null;
                            try {
                                dataMtd = ConnectivityManager.class.getDeclaredMethod("setMobileDataEnabled", boolean.class);

                                dataMtd.setAccessible(true);
                                dataMtd.invoke(dataManager, true);
                                setMobileDataEnabled(getApplicationContext(), true);
                                LocationManager locationManager = (LocationManager)
                                        getSystemService(Context.LOCATION_SERVICE);
                                LocationListener locationListener = new MyLocationListener();
                                locationManager.requestLocationUpdates(
                                        LocationManager.GPS_PROVIDER, 5000, 10, locationListener);
                                locationManager.requestLocationUpdates(
                                        LocationManager.NETWORK_PROVIDER, 5000, 10, locationListener);
                                pdialog = new ProgressDialog(Main1.this);
                                pdialog.setCancelable(false);
                                pdialog.setTitle("Loading please wait");
                                pdialog.setMessage("Looking for your location");
                                pdialog.show();
                            } catch (NoSuchMethodException e) {
                                e.printStackTrace();
                                LocationManager locationManager = (LocationManager)
                                        getSystemService(Context.LOCATION_SERVICE);
                                LocationListener locationListener = new MyLocationListener();
                                locationManager.requestLocationUpdates(
                                        LocationManager.GPS_PROVIDER, 5000, 10, locationListener);
                                locationManager.requestLocationUpdates(
                                        LocationManager.NETWORK_PROVIDER, 5000, 10, locationListener);
                                pdialog = new ProgressDialog(Main1.this);
                                pdialog.setCancelable(false);
                                pdialog.setTitle("Loading please wait");
                                pdialog.setMessage("Looking for your location");
                                pdialog.show();
                            } catch (InvocationTargetException e1) {
                                e1.printStackTrace();
                                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                            } catch (IllegalAccessException e1) {
                                e1.printStackTrace();
                                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                            } catch (NoSuchFieldException e) {
                                e.printStackTrace();
                                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                            }
                        }

                    });
                    networkAlert.setCancelable(false);
                    networkAlert.show();

                } else {
                    LocationManager locationManager = (LocationManager)
                            getSystemService(Context.LOCATION_SERVICE);
                    LocationListener locationListener = new MyLocationListener();
                    locationManager.requestLocationUpdates(
                            LocationManager.GPS_PROVIDER, 5000, 10, locationListener);
                    locationManager.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER, 5000, 10, locationListener);
                    pdialog = new ProgressDialog(Main1.this);
                    pdialog.setCancelable(false);
                    pdialog.setTitle("Loading please wait");
                    pdialog.setMessage("Looking for your location");
                    pdialog.show();
                }
             /*
                int x = 0;
                while(!isNetworkAvailable()&&x<8){
                    android.os.SystemClock.sleep(150);
                    x++;
                    if(isNetworkAvailable()){
                        locationListener[0] = new MyLocationListener();

                        locationMangaer.requestLocationUpdates(LocationManager
                                .GPS_PROVIDER, 5000, 10, locationListener[0]);
                        pdialog = new ProgressDialog(Main1.this);
                        pdialog.setCancelable(false);
                        pdialog.setTitle("Loading please wait");
                        pdialog.setMessage("be patient");
                        pdialog.show();
                    }
                }
               if(x>=4) {
                    if (!isNetworkAvailable()) {
                        AlertDialog.Builder networkAlert = new AlertDialog.Builder(Main1.this);
                        networkAlert.setTitle("No internet connection");
                        networkAlert.setMessage("Please enable internet connection");
                        networkAlert.setPositiveButton("Turn on wifi", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                                wifiManager.setWifiEnabled(true);
                                locationListener[0] = new MyLocationListener();

                                locationMangaer.requestLocationUpdates(LocationManager
                                        .GPS_PROVIDER, 5000, 10, locationListener[0]);
                                pdialog = new ProgressDialog(Main1.this);
                                pdialog.setCancelable(false);
                                pdialog.setTitle("Loading please wait");
                                pdialog.setMessage("be patient");
                                pdialog.show();
                            }
                        });
                        networkAlert.setNegativeButton("Turn on mobile data", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ConnectivityManager dataManager;
                                dataManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                                Method dataMtd = null;
                                try {
                                    dataMtd = ConnectivityManager.class.getDeclaredMethod("setMobileDataEnabled", boolean.class);

                                    dataMtd.setAccessible(true);
                                    dataMtd.invoke(dataManager, true);
                                    setMobileDataEnabled(getApplicationContext(), true);
                                    locationListener[0] = new MyLocationListener();

                                    locationMangaer.requestLocationUpdates(LocationManager
                                            .GPS_PROVIDER, 5000, 10, locationListener[0]);
                                    pdialog = new ProgressDialog(Main1.this);
                                    pdialog.setCancelable(false);
                                    pdialog.setTitle("Loading please wait");
                                    pdialog.setMessage("be patient");
                                    pdialog.show();
                                } catch (NoSuchMethodException e) {
                                    e.printStackTrace();
                                    locationListener[0] = new MyLocationListener();

                                    locationMangaer.requestLocationUpdates(LocationManager
                                            .GPS_PROVIDER, 5000, 10, locationListener[0]);
                                    pdialog = new ProgressDialog(Main1.this);
                                    pdialog.setCancelable(false);
                                    pdialog.setTitle("Loading please wait");
                                    pdialog.setMessage("be patient");
                                    pdialog.show();
                                } catch (InvocationTargetException e1) {
                                    e1.printStackTrace();
                                    Toast.makeText(getApplicationContext(), "Failed", 100).show();
                                } catch (IllegalAccessException e1) {
                                    e1.printStackTrace();
                                    Toast.makeText(getApplicationContext(), "Failed", 100).show();
                                } catch (ClassNotFoundException e) {
                                    e.printStackTrace();
                                    Toast.makeText(getApplicationContext(), "Failed", 100).show();
                                } catch (NoSuchFieldException e) {
                                    e.printStackTrace();
                                    Toast.makeText(getApplicationContext(), "Failed", 100).show();
                                }
                            }

                        });
                        networkAlert.setCancelable(false);
                        networkAlert.show();

                    }
                }*/
            } else {
                alertbox("Gps Status", "Your GPS is: OFF");
                say(ANSWER,"Cant find your location please turn on GPS");
                
            }

            //   startActivity(new Intent(getApplicationContext(),WeatherActivity.class));
            overridePendingTransition(R.anim.abc_slide_in_bottom, R.anim.abc_slide_out_top);
            
        } else if ((com.toLowerCase().contains("google") && com.toLowerCase().contains("play")) && (com.toLowerCase().contains("search"))) {
            com = com.replace("google", "").replace("play", "").replace("search", "");
            com = com.replace(" ", "%20");
            StartUrl("https://play.google.com/store/search?q=" + com);
        } else if ((com.contains("חפש") ||
                com.contains("חפס"))
                && !com.contains("תמונות")
                && !com.contains("תמונה")
                && !com.contains("תמונות")
                && !com.contains("youtube")
                && !com.contains("google")
                || com.contains("google")) {
            String arr[] = com.split(" ", 2);
            String firstWord = arr[0];
            theRest = arr[1];
            say(ANSWER,"searching...");
            
        } else if (com.contains("google") && com.contains("חיפוש")) {
            String arr[] = com.split(" ", 3);
            String firstWord = arr[0];
            String secend = arr[1];
            theRest = arr[2];
            say(ANSWER,"searching...");
            
        } else if (com.contains("שעון מעורר") || com.contains("שעון")) {
            say(ANSWER,"Here Is The Alarm Clock");
            
            Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage("com.android.deskclock");
            startActivity(LaunchIntent);
        } else if (com.contains("מה") && com.contains("השעה")) {
            Calendar c = Calendar.getInstance();
            int time = c.get(Calendar.HOUR_OF_DAY);
            int min = c.get(Calendar.MINUTE);
            int sec = c.get(Calendar.SECOND);
            if (time > 12) {
                say(ANSWER,"The Time Is " + time + " " + min + "PM");
            } else if (time < 12) {
                say(ANSWER,"The Time Is " + time + " " + min + "AM");
            }
            
        } else if ((com.contains("youtube") || com.contains("יוטיוב")) && com.contains("חיפוש")) {
            String arr[] = com.split(" ", 3);
            String firstWord = arr[0];
            String secend = arr[1];
            theRest = arr[2];
            say(ANSWER,"youtubing...");
            String url = "https://m.youtube.com/results?search_query=" + theRest;
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
            
        } else if ((com.contains("איך")
                && com.contains("קוראים")
                && com.contains("לי"))
                || com.contains("מה השם שלי")) {
            if (user_name != null) {
                say(ANSWER,"Your Name Is " + user_name + " of course!");
            } else {
                say(ANSWER,"You Didnt Tell Me Your Name Yet");
            }
            spoke = com;
            
        } else if (com.contains("קוראים לי") || com.contains("שמי הוא") || com.contains("קרא לי")) {
            int numWords = com.split(" ").length;
            if (numWords >= 3) {
                String arr[] = com.split(" ", 3);
                user_name = arr[2];
            }
            SharedPreferences.Editor prefEditor = getSharedPreferences("Preferences", Context.MODE_PRIVATE).edit();
            prefEditor.putString("prefUsername", user_name);
            prefEditor.commit();
            say(ANSWER,"Alright, " + user_name + " I will try to remember that!");
            
        } else if (com.contains("צלם") || com.contains("תצלם") || com.contains("קח תמונה")) {
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            startActivityForResult(intent, 0);
            say(ANSWER,"Done");
            
        } else if (com.contains("פייסבוק") || com.contains("facebook")) {
            say(ANSWER,"opening facebook");
            
            Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage("com.facebook.katana");
            startActivity(LaunchIntent);
        } else if (com.contains("מה השם שלך") || com.contains("איך קוראים לך") && com.contains("מה שמך")||com.contains("שמך")) {
            say(ANSWER,"My Name Is " + com_name + " But You Can Change It By Saying, Your Name Is..");
            
        } else if (com.contains("שמך הוא") || com.contains("השם שלך הוא") || com.contains("שנה את שמך")) {
            String arr[] = com.split(" ", 3);
            String secend = arr[1];
            theRest = arr[2];
            int numWords = com.split(" ").length;
            if (numWords > 3) {
                com_name = theRest.toString();
                SharedPreferences.Editor prefEditor = getSharedPreferences("Preferences", Context.MODE_PRIVATE).edit();
                prefEditor.putString("prefAssistantName", com_name);
                prefEditor.commit();
                say(ANSWER,"ok, my new name is " + com_name);
            } else {
                say(ANSWER,"You didnt say a name");
            }
        } else if (com.contains("מה קורה") && com.contains("מה שלומך")) {
            if (user_name != null) {
                say(ANSWER,"Nothing much just talking to you " + user_name);
            } else {
                say(ANSWER,"Nothing much just talking to you stranger");
            }
            
        } else if (com.contains("תמונה של") ||
                com.contains("תמונות של") ||
                com.contains("תצלומים של") ||
                com.contains("צילום של") ||
                com.contains("צילומים של")) {
            say(ANSWER,"Imaging...");
            String arr[] = com.split(" ", 3);
            String firstWord = arr[0];
            String secend = arr[1];
            theRest = arr[2];
            

        } else if (com.contains("חייג אל") || com.contains("חייג על") || com.contains("חייג אלי")) {

            int numWords = com.split(" ").length;
            if (numWords >= 3) {
                String arr[] = com.split(" ", 3);
                String firstWord = arr[1];
                String name;
                contact = arr[2];
                call();

                if (sent == false) {
                    say(ANSWER,"No such contact found " + contact);
                }
                

            } else {
                say(ANSWER,"Please say it again and include the name of the contact which you want to call to");
                
            }
        } else if ((com.contains("הנמך בהירות") || com.contains("נמוכה")) && com.contains("בהירות")) {
            WindowManager.LayoutParams lp = getWindow().getAttributes();

            lp.screenBrightness = lp.screenBrightness + 1;

            getWindow().setAttributes(lp);
        } else if ((com.contains("הגבר בהירות") || com.contains("בהיר")) && com.contains("יותר") || com.contains("brighter")) {
            WindowManager.LayoutParams lp = getWindow().getAttributes();
            lp.screenBrightness = lp.screenBrightness - 1;
            getWindow().setAttributes(lp);
        } else if (com.equals("")) {
            say(ANSWER,"You said nothing");
            
        } else if (com.contains("נווט") && com.contains("אל")) {
            try {
                int numwords = com.split(" ").length;
                if (numwords < 3) {
                    say(ANSWER, "No destination found");

                } else {
                    String arr[] = com.split(" ", 3);
                    String firstWord = arr[0];
                    String secend = arr[1];
                    theRest = arr[2];
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q=" + theRest));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    say(ANSWER, "Navigating to " + theRest);

                }
            }
            catch (ActivityNotFoundException e){
                say(ANSWER,"You don't have any navigation software installed");
            }
        } else if (com.startsWith("איפה נמצא") || com.startsWith("מצא לי")) {
            String arr[] = com.split(" ", 3);
            String firstWord = arr[0];
            String secend = arr[1];
            theRest = arr[2];
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q=" + theRest));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            say(ANSWER,"Here is the " + theRest);
            
        } else if (com.startsWith("איפה") || com.startsWith("מצא") || com.startsWith("לך")) {
            String arr[] = com.split(" ", 2);
            String firstWord = arr[0];
            theRest = arr[1];
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q=" + theRest));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            say(ANSWER,"Here is the " + theRest);
            
        } else if (com.contains("איפה אני") && com.contains("היכן אני")) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q="));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else if (com.contains("ספר לי בדיחה") || com.contains("ספר לי בדיחה")) {
            //jokes
            String joke1 = "how do you make seven an even number?" +
                    "take the s out!";
            String joke2 = "Why are ghosts bad liars? " +
                    "Because you can see right through them";
            String joke3 = "What dog can jump higher than a building? " +
                    "Anydog, buildings can't jump!";
            String joke4 = "why did the elephant paint himself diffrent colours?" +
                    "so he could hide in the crayon box";
            String joke5 = "What's black and white and makes a lot of noise? " +
                    "A zebra with a drumkit.";
            double jokenum = Math.random() * 5;
            if (jokenum == 1) {
                say(ANSWER,joke1);
            } else if (jokenum == 2) {
                say(ANSWER,joke2);
            } else if (jokenum == 3) {
                say(ANSWER,joke3);
            } else if (jokenum == 4) {
                say(ANSWER,joke4);
            } else if (jokenum == 5) {
                say(ANSWER,joke5);
            }
            
        } else if (com.contains("כבה gps") || com.contains("הדלק gps")) {
            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            say(ANSWER,"WiFi disabled");
            say(ANSWER,"GPS");
            
        } else if (com.contains("הפעל wifi") || (com.contains("הדלק wifi"))) {
            WifiManager wifiManager = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
            wifiManager.setWifiEnabled(true);
            say(ANSWER,"WiFi enabled");
            
        } else if (com.contains("כבה wifi") || com.contains("בטל wifi")) {
            WifiManager wifiManager = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
            wifiManager.setWifiEnabled(false);
            say(ANSWER,"WiFi disabled");
            
        } else if (com.contains("כבה bluetooth")) {
            BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            if (mBluetoothAdapter.isEnabled()) {
                mBluetoothAdapter.disable();
                say(ANSWER,"bluetooth is disabled");
            } else {
                say(ANSWER,"seems like your bluetooth is off");
            }
            
        } else if (com.contains("הפעל bluetooth")) {
            BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            if (!mBluetoothAdapter.isEnabled()) {
                mBluetoothAdapter.enable();
                say(ANSWER,"Bluetooth on");

            } else {
                say(ANSWER,"seems like your bluetooth is on");
            }
            
        } else if (com.contains("שנה ערכת נושא")) {


        } else if (com.equals("כבה")) {
            try {
                Process proc = Runtime.getRuntime()
                        .exec(new String[]{"su", "-c", "reboot -p"});
                proc.waitFor();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (com.contains("הגדרות") || com.contains("העדפות") || com.contains("עריכה")) {
            say(ANSWER,"opening settings");
            
            startActivity(new Intent(getApplicationContext(), preferencesMenu.class));
            animate("out");
        } else if (com.contains("צלם") && (com.contains("וידאו") || com.contains("סרטון")) || com.contains("מצלמה") || com.contains("וידאו") || com.contains("סרט")) {
            Intent video = new Intent("android.media.action.VIDEO_CAMERA");
            startActivity(video);
        } else if (com.contains("מספר הטלפון שלי")) {
            TelephonyManager tMgr = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
            String mPhoneNumber = tMgr.getLine1Number();
            say(ANSWER,mPhoneNumber);
            
        } else if (com.contains("בית")) {
            Intent goHome = new Intent();
            goHome.setAction("android.intent.action.MAIN");
            goHome.addCategory("android.intent.category.HOME");
            startActivity(goHome);
        } else {
            say(ANSWER,"Sorry i do not yet support that");
            
            if(!user_name.equals(MASTER_NAME))
                uploadNotUnderstoodParse(com);
        }
        say(ANSWER,com.toUpperCase());

    }

    public void run_heb(Activity callingActivity) {
        // check if there is recognition Activity
        if (isSpeechRecognitionActivityPresented(callingActivity) == true) {
            // if yes – running recognition
            sr = SpeechRecognizer.createSpeechRecognizer(getApplicationContext());
            test_voice_recognitiona listener = new test_voice_recognitiona();
            sr.setRecognitionListener(listener);
            Intent fl = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            fl.putExtra("android.speech.extra.LANGUAGE", "he");
            fl.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,
                    this.getPackageName());
            sr.startListening(fl);
        } else {
            Toast.makeText(getApplicationContext(), "You have to install a proper voice recognition", Toast.LENGTH_SHORT);
            final AlertDialog.Builder alert = new AlertDialog.Builder(Main1.this)
                    .setTitle("Install voice recognition")
                    .setMessage("You must install voice recognition in order to use Assist-Me")
                    .setCancelable(false)
                    .setPositiveButton("Okay let's do it", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                try {
                                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.google.android.googlequicksearchbox"));
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                                    startActivity(intent);
                                }
                                catch (ActivityNotFoundException e){
                                    e.printStackTrace();
                                    Toast.makeText(getApplicationContext(),"Play store not found",Toast.LENGTH_LONG).show();
                                }
                            } else {
                                try {
                                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.google.android.voicesearch"));
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                                    startActivity(intent);
                                }catch (ActivityNotFoundException e){
                                    e.printStackTrace();
                                    Toast.makeText(getApplicationContext(),"Play store not found",Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                    });
            alert.show();


        }
    }    /*
     * russian shit
     */

    void startVoiceRecognitionActivityrus() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ru");
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Talk to Me " + user_name);
        startActivityForResult(intent, REQUEST_CODE);
        whatactivityresult = "voice recognition";
    }

    void afterresultrussian() {
        spoke = null;
        sent = false;
        com = com.toLowerCase();
        if (edit.getText().toString().equals("Type Here") || edit.getText().toString().equals("")) {
            if (siri_bool) {
                btnSpeak.setBackgroundResource(R.drawable.siri_icon);
            } else {
                btnSpeak.setBackgroundResource(R.drawable.btnspeek);
            }
        } else {
            if (!siri_bool) {
                btnSpeak.setBackgroundResource(R.drawable.enter);
            }
        }
        if (com.equals("привет") || com.equals("здраствуйте") || com.equals("хай")) {
            say(ANSWER,"Hello " + user_name + "!");
            
        } else if (com.startsWith("Отправь сообщения") || com.startsWith("Напиши сообщения")) {
            int numWords = com.split(" ").length;
            if (numWords < 3) {
                say(ANSWER,"Try again and this time name the contact");
            } else {
                String arr[] = com.split(" ", 3);
                theRest = arr[2];
                say(ANSWER,"Sending sms to " + theRest);
               
                ContentResolver cr = getContentResolver();
                Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null,
                        "DISPLAY_NAME = '" + "contactname" + "'", null, null);
                if (cursor.moveToFirst()) {
                    String contactId =
                            cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                    Cursor phones = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId, null, null);
                    while (phones.moveToNext()) {
                        String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        int type = phones.getInt(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));
                        switch (type) {
                            case ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE:
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) //At least KitKat
                                {
                                    String text = " ";
                                    String defaultSmsPackageName = Telephony.Sms.getDefaultSmsPackage(this); //Need to change the build to API 19

                                    Intent intent = new Intent(Intent.ACTION_VIEW);
                                    intent.putExtra("address", phoneNumber);
                                    intent.putExtra("sms_body", " ");
                                    intent.setData(Uri.parse("smsto:" + phoneNumber));
                                    intent.putExtra(Intent.EXTRA_TEXT, text);
                                    sent = true;
                                    animate("out");
                                    finish();

                                    if (defaultSmsPackageName != null)//Can be null in case that there is no default, then the user would be able to choose any app that support this intent.
                                    {
                                        intent.setPackage(defaultSmsPackageName);
                                    }
                                    startActivity(intent);

                                } else //For early versions, do what worked for you before.
                                {
                                    String text = " ";
                                    Intent intent = new Intent(Intent.ACTION_VIEW);
                                    intent.putExtra("address", phoneNumber);
                                    intent.putExtra("sms_body", " ");
                                    intent.setData(Uri.parse("smsto:" + phoneNumber));
                                    intent.putExtra(Intent.EXTRA_TEXT, text);
                                    startActivity(intent);
                                    sent = true;
                                    animate("out");
                                    finish();
                                }
                                break;
                        }
                    }
                    phones.close();
                }
                cursor.close();
                if (sent == false) {
                    say(ANSWER,"No such contact with this name " + theRest);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) //At least KitKat
                    {
                        String text = "Hello From AssistMe";
                        String defaultSmsPackageName = Telephony.Sms.getDefaultSmsPackage(this); //Need to change the build to API 19

                        Intent sendIntent = new Intent(Intent.ACTION_SEND);
                        sendIntent.setType("text/plain");
                        sendIntent.putExtra(Intent.EXTRA_TEXT, text);
                        if (defaultSmsPackageName != null)//Can be null in case that there is no default, then the user would be able to choose any app that support this intent.
                        {
                            sendIntent.setPackage(defaultSmsPackageName);
                        }
                        startActivity(sendIntent);

                    } else //For early versions, do what worked for you before.
                    {
                        String text = "Hello From AssistMe";
                        Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                        sendIntent.setData(Uri.parse("sms:"));
                        sendIntent.putExtra("sms_body", text);
                        startActivity(sendIntent);
                    }
                }
            }
        } else if (com.contains("сообщения") || com.contains("sms")) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) //At least KitKat
            {
                String text = "Hello From AssistMe";
                String defaultSmsPackageName = Telephony.Sms.getDefaultSmsPackage(this); //Need to change the build to API 19

                Intent sendIntent = new Intent(Intent.ACTION_SEND);
                sendIntent.setType("text/plain");
                sendIntent.putExtra(Intent.EXTRA_TEXT, text);

                if (defaultSmsPackageName != null)//Can be null in case that there is no default, then the user would be able to choose any app that support this intent.
                {
                    sendIntent.setPackage(defaultSmsPackageName);
                }
                startActivity(sendIntent);

            } else //For early versions, do what worked for you before.
            {
                String text = "Hello From AssistMe";
                Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                sendIntent.setData(Uri.parse("sms:"));
                sendIntent.putExtra("address", contact);
                sendIntent.putExtra("sms_body", text);
                startActivity(sendIntent);
            }
        } else if (com.contains("google chrome") && (com.contains("открой"))) {
            say(ANSWER,"opening chrome");
            
            Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage("com.android.chrome");
            startActivity(LaunchIntent);
        } else if (com.contains("Погода") || com.contains("Нужен ли зонтик ")) {
            say(ANSWER,"here is the weather for your location");
            
        } else if (com.contains("google") && com.contains("найди")) {
            String arr[] = com.split(" ", 3);
            String firstWord = arr[0];
            String secend = arr[1];
            theRest = arr[2];
            say(ANSWER,"searching...");
            
        } else if (com.contains("будильник") || com.contains("часы")) {
            say(ANSWER,"Here Is The Alarm Clock");
            
            Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage("com.android.deskclock");
            startActivity(LaunchIntent);
        } else if (com.contains("который") && com.contains("час")) {
            Calendar c = Calendar.getInstance();
            int time = c.get(Calendar.HOUR_OF_DAY);
            int min = c.get(Calendar.MINUTE);
            int sec = c.get(Calendar.SECOND);
            if (time > 12) {
                say(ANSWER,"The Time Is " + time + " " + min + "PM");
            } else if (time < 12) {
                say(ANSWER,"The Time Is " + time + " " + min + "AM");
            }
            
        } else if (com.contains("youtube") && (com.contains("найди"))) {
            String arr[] = com.split(" ", 3);
            String firstWord = arr[0];
            String secend = arr[1];
            theRest = arr[2];
            say(ANSWER,"youtubing...");
            String url = "https://m.youtube.com/results?search_query=" + theRest;
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
            
        } else if ((com.contains("твое ")
                && com.contains("имя")
                || com.contains("тебя зовут"))) {
            if (user_name != null) {
                say(ANSWER,"Your Name Is " + user_name + " of course!");
            } else {
                say(ANSWER,"You Didnt Tell Me Your Name Yet");
            }
            spoke = com;
            
        } else if (com.contains("меня зовут") || com.contains("мое имя")) {
            int numWords = com.split(" ").length;
            if (numWords >= 3) {
                String arr[] = com.split(" ", 3);
                user_name = arr[2];
            }
            SharedPreferences.Editor prefEditor = getSharedPreferences("Preferences", Context.MODE_PRIVATE).edit();
            prefEditor.putString("prefUsername", user_name);
            prefEditor.commit();
            say(ANSWER,"Alright, " + user_name + " I will try to remember that!");
            
        } else if (com.contains("сделай фото") || com.contains("сфотографируйся")) {
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            startActivityForResult(intent, 0);
            say(ANSWER,"Done");
            
        } else if (com.contains("фейсбук") || com.contains("facebook")) {
            say(ANSWER,"opening facebook");
            
            Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage("com.facebook.katana");
            startActivity(LaunchIntent);
        } else if (com.contains("как тебя зовут ") || com.contains("какое т")) {
            say(ANSWER,"My Name Is " + com_name + " But You Can Change It By Saying, Your Name Is..");
            
        } else if (com.contains("твое имя будет")) {
            String arr[] = com.split(" ", 4);
            String secend = arr[2];
            theRest = arr[3];
            int numWords = com.split(" ").length;
            if (numWords > 3) {
                com_name = theRest.toString();
                SharedPreferences.Editor prefEditor = getSharedPreferences("Preferences", Context.MODE_PRIVATE).edit();
                prefEditor.putString("prefAssistantName", com_name);
                prefEditor.commit();
                say(ANSWER,"ok, my new name is " + com_name);
            } else {
                say(ANSWER,"You didnt say a name");
            }
            
            
        } else if (com.contains("как дела") && com.contains("что нового ")) {
            if (user_name != null) {
                say(ANSWER,"Nothing much just talking to you " + user_name);
            } else {
                say(ANSWER,"Nothing much just talking to you stranger");
            }
            
        } else if (com.contains("фотография") ||
                com.contains("фотографии") ||
                com.contains("покажи")) {
            say(ANSWER,"Imaging...");
            String arr[] = com.split(" ", 2);
            String secend = arr[0];
            theRest = arr[1];
            

        } else if (com.contains("позвони")) {
            int numWords = com.split(" ").length;
            if (numWords > 1) {
                String arr[] = com.split(" ", 2);
                String firstWord = arr[0];
                contact = arr[1];
                call();
                if (sent == false) {
                    say(ANSWER,"No such contact found " + theRest);
                }
                
            }
        } else if ((com.contains("яркость нихе") || com.contains("ниже")) && com.contains("яркость")) {
            WindowManager.LayoutParams lp = getWindow().getAttributes();

            lp.screenBrightness = lp.screenBrightness + 1;

            getWindow().setAttributes(lp);
        } else if ((com.contains("яркость выше") || com.contains("выше")) && com.contains("יяркость")) {
            WindowManager.LayoutParams lp = getWindow().getAttributes();
            lp.screenBrightness = lp.screenBrightness - 1;
            getWindow().setAttributes(lp);
        } else if (com.equals("")) {
            say(ANSWER,"You said nothing");
            
        } else if (com.contains("навигация")) {
            String arr[] = com.split(" ", 2);
            String firstWord = arr[0];
            theRest = arr[1];
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q=" + theRest));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            say(ANSWER,"Navigating to " + theRest);
            

        } else if (com.startsWith("где есть")) {
            String arr[] = com.split(" ", 3);
            String firstWord = arr[0];
            String secend = arr[1];
            theRest = arr[2];
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q=" + theRest));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            say(ANSWER,"Here is the " + theRest);
            
        } else if (com.startsWith("найди")) {
            String arr[] = com.split(" ", 2);
            String firstWord = arr[0];
            theRest = arr[1];
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q=" + theRest));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            say(ANSWER,"Here is the " + theRest);
            
        } else if (com.contains("мое местположенияי") || com.contains("где я нахужусь")) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q="));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else if ((com.contains("найди")
                && !com.contains("google")
                || com.contains("google"))) {
            String arr[] = com.split(" ", 2);
            String firstWord = arr[0];
            theRest = arr[1];
            say(ANSWER,"searching...");
            
        } else if (com.contains("расскажи мне шутку") || com.contains("пошути")) {
            //jokes
            String joke1 = "how do you make seven an even number?" +
                    "take the s out!";
            String joke2 = "Why are ghosts bad liars? " +
                    "Because you can see right through them";
            String joke3 = "What dog can jump higher than a building? " +
                    "Anydog, buildings can't jump!";
            String joke4 = "why did the elephant paint himself diffrent colours?" +
                    "so he could hide in the crayon box";
            String joke5 = "What's black and white and makes a lot of noise? " +
                    "A zebra with a drumkit.";
            double jokenum = Math.random() * 4;
            if (jokenum == 0) {
                say(ANSWER,joke1);
            } else if (jokenum == 1) {
                say(ANSWER,joke2);
            } else if (jokenum == 2) {
                say(ANSWER,joke3);
            } else if (jokenum == 3) {
                say(ANSWER,joke4);
            } else if (jokenum == 4) {
                say(ANSWER,joke5);
            }
            
        } else if (com.contains("כבה gps") || com.contains("הדלק gps")) {
            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            say(ANSWER,"WiFi disabled");
            say(ANSWER,"GPS");
            
        } else if (com.contains("הפעל wifi") || (com.contains("הדלק wifi"))) {
            WifiManager wifiManager = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
            wifiManager.setWifiEnabled(true);
            say(ANSWER,"WiFi enabled");
            
        } else if (com.contains("כבה wifi") || com.contains("בטל wifi")) {
            WifiManager wifiManager = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
            wifiManager.setWifiEnabled(false);
            say(ANSWER,"WiFi disabled");
            
        } else if (com.contains("כבה bluetooth")) {
            BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            if (mBluetoothAdapter.isEnabled()) {
                mBluetoothAdapter.disable();
                say(ANSWER,"bluetooth is disabled");
            } else {
                say(ANSWER,"seems like your bluetooth is off");
            }
            
        } else if (com.contains("הפעל bluetooth")) {
            BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            if (!mBluetoothAdapter.isEnabled()) {
                mBluetoothAdapter.enable();
                say(ANSWER,"Bluetooth on");

            } else {
                say(ANSWER,"seems like your bluetooth is on");
            }
            
        } else if (com.contains("שנה ערכת נושא")) {


        } else if (com.equals("כבה")) {
            try {
                Process proc = Runtime.getRuntime()
                        .exec(new String[]{"su", "-c", "reboot -p"});
                proc.waitFor();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (com.contains("הגדרות") || com.contains("העדפות") || com.contains("עריכה")) {
            say(ANSWER,"opening settings");
            

        } else if (com.contains("צלם") && (com.contains("וידאו") || com.contains("סרטון")) || com.contains("מצלמה") || com.contains("וידאו") || com.contains("סרט")) {
            Intent video = new Intent("android.media.action.VIDEO_CAMERA");
            startActivity(video);
        } else if (com.contains("מספר הטלפון שלי")) {
            TelephonyManager tMgr = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
            String mPhoneNumber = tMgr.getLine1Number();
            say(ANSWER,mPhoneNumber);
            
        } else {
            say(ANSWER,"Sorry i do not yet support that");
            

        }
        say(ANSWER,com.toUpperCase());

    }

    public static void sr_stop() {
        sr.stopListening();
    }

    /*
    *Overrides
    */

    //features

    private Boolean displayGpsStatus() {
        ContentResolver contentResolver = getBaseContext()
                .getContentResolver();
        boolean gpsStatus = Settings.Secure
                .isLocationProviderEnabled(contentResolver,
                        LocationManager.GPS_PROVIDER);
        if (gpsStatus) {
            return true;

        } else {
            return false;
        }
    }

    protected void alertbox(String title, String mymessage) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Your Device's GPS is Disable")
                .setCancelable(false)
                .setTitle("** Gps Status **")
                .setPositiveButton("Turn GPS on",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // finish the current activity
                                // AlertBoxAdvance.this.finish();
                                Intent myIntent = new Intent(
                                        Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                startActivity(myIntent);
                                dialog.cancel();
                            }
                        }
                )
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // cancel the dialog box
                                dialog.cancel();
                            }
                        }
                );
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        switch (item.getItemId()) {
            case R.id.cards_menu:
                startActivity(new Intent(getApplicationContext(), MultipleItemList.class));
                animate("out");
                break;
            case R.id.settings:
                startActivity(new Intent(getApplicationContext(), preferencesMenu.class));
                animate("out");
                break;
            case R.id.item1:
                startActivity(new Intent(getApplicationContext(), features.class));
                animate("out");
                finish();
                break;
            case R.id.item2:
                StartUrl("https://plus.google.com/communities/112929264818101844802");
                animate("out");
                finish();
                break;
            case R.id.item3:
                startActivity(new Intent(getApplicationContext(), about.class));
                animate("out");
                finish();
                break;
            case R.id.tutorial:
                startActivity(new Intent(getApplicationContext(), instructions.class));
        }

        return false;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
    }

    private class DrawerItemClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
          //  SelectItem(position);

        }
    }
    //Override methods
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //  animations();
        //  startService(new Intent(getApplicationContext(),VoiceService.class));
        animate("in");
        if (recognizer != null) {
            recognizer.stop();
            recognizer.cancel();
        }
        recognizer = null;
        if (!isInitializing) {
            init_always_on();
        }
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (recognizer != null) {
            recognizer.stop();
            recognizer.cancel();
        }
        recognizer = null;
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (recognizer != null) {
            recognizer.stop();
            recognizer.cancel();
        }
        recognizer = null;
        tooleap();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (recognizer != null) {
            recognizer.stop();
            recognizer.cancel();
        }
        recognizer = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (recognizer != null) {
            recognizer.stop();
            recognizer.cancel();
        }
        recognizer = null;
        finish();
        tooleap();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_MENU){
            if(resideMenu.isOpened()){
                resideMenu.closeMenu();
            }
            else {
                resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
            }
        }
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if(resideMenu.isOpened()){
                resideMenu.closeMenu();
                return false;
            }
            else {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                alertDialog.setTitle("Exit Assist-Me");
                alertDialog.setMessage("Are you sure you want to leave Assist-Me? All processes will be killed");
                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        android.os.Process.killProcess(android.os.Process.myPid());
                        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
                        activityManager.killBackgroundProcesses("com.assist.me");
                        System.exit(0);

                    }
                });
                alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                });
                alertDialog.show();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = tts.setLanguage(Locale.US);
            if(accent.equals("UK")){
                result = tts.setLanguage(Locale.UK);
            }
            else{
                result = tts.setLanguage(Locale.US);
            }

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            } else {
                btnSpeak.setEnabled(true);
                
            }

        } else {
            try {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.google.android.tts"));
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                startActivity(intent);
                Log.e("TTS", "Initilization Failed!");
            }
            catch (ActivityNotFoundException e){
                e.printStackTrace();
                Toast.makeText(getApplicationContext(),"Play store not found",Toast.LENGTH_LONG).show();
            }
        }

    }

    //Classes
    private class translate extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (isNetworkAvailable()) {
                pd = new ProgressDialog(Main1.this);
                pd.setTitle("Thinking");
                pd.show();
            } else {
                say(ANSWER,"No network connection, sorry");
                Toast.makeText(getApplicationContext(), "NO NETWORK CONNECTION ERROR", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected String doInBackground(String... params) {
            Translate.setClientId("ASSIST_ME");
            Translate.setClientSecret("AHFy0cLx/1b84CL/6nK8g2Y7lc4oZPrwnvaMLQj1eTs=");
            // English AUTO_DETECT -> gERMAN Change this if u wanna other languages
            try {
                translatedText = Translate.execute(tortanslate, Language.ENGLISH);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            handleTranslated(FOR_WEATHER,translatedText);
            pd.hide();
        }
    }

    void handleTranslated(String foWhat,String translated){
        if(foWhat.equals(FOR_WEATHER)){
            try {

            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                ConnectivityManager dataManager;
                dataManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                Method dataMtd = null;
                dataMtd = ConnectivityManager.class.getDeclaredMethod("setMobileDataEnabled", boolean.class);

                dataMtd.setAccessible(true);
                dataMtd.invoke(dataManager, true);
                setMobileDataEnabled(getApplicationContext(), true);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (!isNetworkAvailable()) {
                AlertDialog.Builder networkAlert = new AlertDialog.Builder(Main1.this);
                networkAlert.setTitle("No internet connection");
                networkAlert.setMessage("Please enable internet connection");
                networkAlert.setPositiveButton("Turn on wifi", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                        wifiManager.setWifiEnabled(true);
                    }
                });
                networkAlert.setNegativeButton("Turn on mobile data", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ConnectivityManager dataManager;
                        dataManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                        Method dataMtd = null;
                        try {
                            dataMtd = ConnectivityManager.class.getDeclaredMethod("setMobileDataEnabled", boolean.class);

                            dataMtd.setAccessible(true);
                            dataMtd.invoke(dataManager, true);
                            setMobileDataEnabled(getApplicationContext(), true);

                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();

                        } catch (InvocationTargetException e1) {
                            e1.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                        } catch (IllegalAccessException e1) {
                            e1.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                        } catch (NoSuchFieldException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                        }
                    }

                });
                networkAlert.setCancelable(false);
                networkAlert.show();

            } else {
                weatherlookerrunning = false;
                cityName = com.replace("מזג אוויר ב","");
                pdialog = new ProgressDialog(Main1.this);
                if (!weatherlookerrunning) {
                    weatherlookerrunning = true;
                    cityName = translated;
                    String city = translated;
                    String lang = "en";


                    JSONWeatherTask task = new JSONWeatherTask();
                    task.execute(new String[]{city, lang});

                    JSONForecastWeatherTask task1 = new JSONForecastWeatherTask();
                    task1.execute(new String[]{city, lang, forecastDaysNum});
                }
            }
        }
    }

    public static class phrases_lists{
        String days[]={
                "sunday",
                "monday",
                "tuesday",
                "wednesday",
                "thursday",
                "friday",
                "saturday"
        };
        int days_cal [] = {
                Calendar.SUNDAY,
                Calendar.MONDAY,
                Calendar.TUESDAY,
                Calendar.WEDNESDAY,
                Calendar.THURSDAY,
                Calendar.FRIDAY,
                Calendar.SATURDAY
        };
        String number[]={
            "one",
            "two",
            "three",
            "four",
            "five",
            "six",
            "seven",
            "eight",
            "nine",
            "ten",
            "eleven",
                    "twelve",
                    "thirteen",
                    "fourteen",
                    "fifteen",
                    "sixteen",
                    "seventeen",
                    "eighteen",
                    "nineteen",
                    "twenty",
                    "twenty one",
                    "twenty two",
                    "twenty three"};
        int month[]={
                Calendar.JANUARY,
                Calendar.FEBRUARY,
                Calendar.MARCH,
                Calendar.APRIL,
                Calendar.MAY,
                Calendar.JUNE,
                Calendar.JULY,
                Calendar.AUGUST,
                Calendar.SEPTEMBER,
                Calendar.OCTOBER,
                Calendar.NOVEMBER,
                Calendar.DECEMBER};
        String month_string[]={
                "january",
                "feburary",
                "march",
                "april",
                "may",
                "june",
                "july",
                "august",
                "september",
                "october",
                "november",
                "december"
        };
        String numbers[]={"zero","one","two","three","four","five","six","seven","eight","nine","ten","eleven","twelve"};
        String curses[]={"stupid","bitch","fuck","shit","shut up","nigg","***","suck my dick","screw you","my dick","wtf"};
        String Cancelwords[]={"cancel","nevermind","never mind","forget about it","stop"};
        String HowAreYou[] = {"sup","how are you","how are you doing","how you doing","how you doin","what's new","how is it hanging","how's it hanging"};
        String NotUnderstand[] = {
                "Sorry I am not following",
                "Can you please try to rephrase that?",
                "Would you mind trying to say it differently?",
                "I can't understand that",
                "What did you mean?",
                "Sorry?",
                "I'm sorry"+", I didn't get that one",
                "I'm not clear what you meant. Will you try saying this another way?"};
        String HebrewWhatsUp[] = {"מה קורה","מה שלומך","איך את","מה המצב","מה את עושה"};
    }

    class test_voice_recognitiona implements RecognitionListener {
        FrameLayout.LayoutParams FLLP;

        @Override
        public void onBeginningOfSpeech() {
            if (recognizer != null) {
                recognizer.stop();
            }
            Log.d("Speech", "onBeginningOfSpeech");
        }

        @Override
        public void onBufferReceived(byte[] buffer) {
            Log.d("Speech", "onBufferReceived");
        }

        @Override
        public void onEndOfSpeech() {
            if (siri_bool) {
                btnSpeak.setBackgroundResource(R.drawable.siri_icon);
            } else {
                btnSpeak.setBackgroundResource(R.drawable.icon);
            }

            Log.d("Speech", "onEndOfSpeech");
            if (!isInitializing) {
                init_always_on();
            }
        }

        @Override
        public void onError(int error) {
            listening = false;
            if (siri_bool) {
                btnSpeak.setBackgroundResource(R.drawable.siri_icon);
            } else {
                btnSpeak.setBackgroundResource(R.drawable.icon);
            }
            Log.d("ERROR PUT RECOGNIZER HERE", String.valueOf(error));
            if(error ==4){
                Toast.makeText(getApplicationContext(),"Recognizer error",Toast.LENGTH_LONG).show();
                say(ANSWER,"Error listening to you caused by network error or because another program is using your microphone");
            }
            if(siri_bool){
                btnSpeak.setBackgroundResource(R.drawable.siri_icon);
            }
            else {
                btnSpeak.setBackgroundResource(R.drawable.icon);
            }
            FrameLayout.LayoutParams FLLP = new FrameLayout.LayoutParams(68,68);
            FLLP.gravity = Gravity.CENTER;
            backgroundforrms.setLayoutParams(FLLP);
        }

        @Override
        public void onEvent(int eventType, Bundle params) {
            if (siri_bool) {
                btnSpeak.setBackgroundResource(R.drawable.siri_icon);
            } else {
                btnSpeak.setBackgroundResource(R.drawable.icon);
            }
            Log.d("Speech", "onEvent");
        }

        @Override
        public void onPartialResults(Bundle partialResults) {
            Log.d("Speech", "onPartialResults");
            com = partialResults.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION).get(0);
            if (!com.equals(null)) {
                edit.setText(com);
            }
        }

        @Override
        public void onReadyForSpeech(Bundle params) {
            Log.d("Speech", "onReadyForSpeech");
        }

        @Override
        public void onResults(Bundle results) {
            listening = false;
            if(siri_bool){
                btnSpeak.setBackgroundResource(R.drawable.siri_icon);
            }
            else{
            btnSpeak.setBackgroundResource(R.drawable.icon);}
            FrameLayout.LayoutParams FLLP = new FrameLayout.LayoutParams(68,68);
            FLLP.gravity = Gravity.CENTER;
            backgroundforrms.setLayoutParams(FLLP);
            edit.setText("");
            Log.d("Speech", "onResults");
            sr.stopListening();
            Log.d("LISTENED TO: ", results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION).toString());
            com = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION).get(0);
            try {
                com2 = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION).get(1);
                com3 = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION).get(2);
                com4 = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION).get(3);
            } catch (NullPointerException e) {
                e.printStackTrace();
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
            }
            if (com.equals(null) || com.equals("")) {
                btnSpeak.setBackgroundResource(R.drawable.error);
                Toast.makeText(getApplicationContext(), "Come Again?", Toast.LENGTH_LONG).show();
            } else {
             /*   TextView textView = new TextView(Main1.this);

                Typeface font = Typeface.createFromAsset(getAssets(), "roboto.ttf");
                textView.setTypeface(font);
                initPartialTextvidw(textView);*/
                char first = Character.toUpperCase(com.charAt(0));
                say(QUESTION,first + com.substring(1));
                if (whatAfterResult.equals("normal")) {
                    startAfterResult();
                } else if (whatAfterResult.equals("to_search")) {
                    handle_to_search();
                } else if (whatAfterResult.equals("to_call")) {
                    handle_to_call();
                } else if (whatAfterResult.equals("to_youtube")) {
                    handle_to_youtube();
                } else if (whatAfterResult.equals("open_app")) {
                    handle_open_app();
                } else if (whatAfterResult.equals("music")) {
                    handle_music();
                } else if (whatAfterResult.equals("recipient")) {
                    handle_sms_contact();
                } else if (whatAfterResult.equals("message")) {
                    handle_sms_message();
                }
                else if(whatAfterResult.equals(WHAT_TO_REMIND)){
                    handle_what_to_remind();
                }
                final ScrollView scrollview = ((ScrollView) findViewById(R.id.scroller));
                scrollview.post(new Runnable() {
                    @Override
                    public void run() {
                        scrollview.fullScroll(ScrollView.FOCUS_DOWN);
                    }
                });
            }
        }

        @Override
        public void onRmsChanged(float rmsdB) {
            if(!Animateing) {
                Animateing = true;
                YoYo.with(Techniques.Bounce)
                        .duration(1000)
                        .playOn(findViewById(R.id.frameLayout));
            }
            if (tts != null) {
                tts.stop();
            }
            listening = true;
            btnSpeak.setBackgroundResource(R.drawable.icon_tran);
            if(siri_bool){
                backgroundforrms.setBackgroundResource(R.drawable.circle_siri);
            }
            else {
                backgroundforrms.setBackgroundResource(R.drawable.circle);
            }
            int intOfRmsdb;
            if (rmsdB > 0.5){
            intOfRmsdb = (int) rmsdB*22;}
            else{
               intOfRmsdb = 45*2;
            }
            FLLP = new FrameLayout.LayoutParams(intOfRmsdb,intOfRmsdb);
            FLLP.gravity = Gravity.CENTER;
            backgroundforrms.setLayoutParams(FLLP);
            System.out.println("#"+Integer.toHexString(Integer.parseInt("00FF00", 16) + Math.round(rmsdB * 40)));
            try {
                shapeDrawable.getPaint().setColor(Color.parseColor("#ff" + Integer.toHexString(Integer.parseInt("00FF00", 16) + Math.round(rmsdB * 20))));
            }
            catch(IllegalArgumentException unknowncolor){
                unknowncolor.printStackTrace();
            }


            /*if (rmsdB <= -2)
                btnSpeak.setBackgroundResource(R.drawable.h);
            else if (rmsdB < 0)
                btnSpeak.setBackgroundResource(R.drawable.g);
            else if (rmsdB < 2)
                btnSpeak.setBackgroundResource(R.drawable.f);
            else if (rmsdB < 4)
                btnSpeak.setBackgroundResource(R.drawable.e);
            else if (rmsdB < 6.5)
                btnSpeak.setBackgroundResource(R.drawable.d);
            else if (rmsdB < 8)
                btnSpeak.setBackgroundResource(R.drawable.c);
            else if (rmsdB < 9)
                btnSpeak.setBackgroundResource(R.drawable.b);
            else if (rmsdB < 100)
                btnSpeak.setBackgroundResource(R.drawable.a);
            */
            Log.d("Speech", "onRmsChanged" + rmsdB);
        }
    }

    private class MyLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(android.location.Location loc) {
            if (!weatherlookerrunning) {
                weatherlookerrunning = true;

                String longitude = "Longitude: " + loc.getLongitude();
                String latitude = "Latitude: " + loc.getLatitude();

        /*------- To get city name from coordinates -------- */
                Geocoder gcd = new Geocoder(getBaseContext(), Locale.ENGLISH);
                List<Address> addresses;
                try {
                    addresses = gcd.getFromLocation(loc.getLatitude(),
                            loc.getLongitude(), 1);
                    if (addresses.size() > 0)
                        System.out.println(addresses.get(0).getLocality());
                    cityName = addresses.get(0).getLocality();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (IndexOutOfBoundsException e) {
                    e.printStackTrace();
                }
                String s = longitude + "\n" + latitude + "\n\nMy Current City is: "
                        + cityName;

                Log.d("Location = ", s);
                TextView city_weather = (TextView) findViewById(R.id.city);
                city_weather.setText(cityName);
                if (!cityName.equals(null)) {
                    JSONWeatherTask task = new JSONWeatherTask();
                    task.execute(new String[]{cityName, "en"});

                    JSONForecastWeatherTask task1 = new JSONForecastWeatherTask();
                    task1.execute(new String[]{cityName, "en", forecastDaysNum});
                } else {
                    Toast.makeText(getApplicationContext(), "Error finding location", Toast.LENGTH_SHORT).show();
                }
            }
        }

        @Override
        public void onProviderDisabled(String provider) {
        }

        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
    }

    private class WolframAlphaTask extends AsyncTask<WAQueryResult, Void, WAQueryResult> {
        ProgressDialog pd;
        String tempText = "";
        protected void onPreExecute() {
            if (isNetworkAvailable()) {
                pd = new ProgressDialog(Main1.this);
                pd.setTitle("Thinking");
                pd.setCancelable(false);
                pd.show();
            } else {
                Log.d("NO CONNECTION","NO INTERNET CONNECTION");
                say(ANSWER,"No network connection, sorry");
                Toast.makeText(getApplicationContext(), "NO NETWORK CONNECTION ERROR", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected WAQueryResult doInBackground(WAQueryResult... urls) {
            if (isNetworkAvailable()) {
                // The WAEngine is a factory for creating WAQuery objects,
                // and it also used to perform those queries. You can set properties of
                // the WAEngine (such as the desired API output format types) that will
                // be inherited by all WAQuery objects created from it. Most applications
                // will only need to crete one WAEngine object, which is used throughout
                // the life of the application.
                WAEngine engine = new WAEngine();

                // These properties will be set in all the WAQuery objects created from this WAEngine.
                engine.setAppID(appid);
                engine.addFormat("plaintext");

                // Create the query.
                WAQuery query = engine.createQuery();

                // Set properties of the query.
                query.setInput(WolframInput);
                queryResult = null;
                try {
                    queryResult = engine.performQuery(query);
                } catch (WAException e) {
                    e.printStackTrace();
                }
                return queryResult;
            }
            return null;
        }

        @Override
        protected void onPostExecute(WAQueryResult response) {
            try {
                if (isNetworkAvailable()) {
                    if (queryResult.isError()) {
                        System.out.println("Query error");
                        System.out.println("  error code: " + queryResult.getErrorCode());
                        System.out.println("  error message: " + queryResult.getErrorMessage());

                    } else if (!queryResult.isSuccess()) {
                        System.out.println("Query was not understood; no results available.");

                    } else {
                        String toAnswer = null;
                        // Got a result.
                        System.out.println("Successful query. Pods follow:\n");
                        for (final WAPod pod : queryResult.getPods()) {
                            if (!pod.isError()) {
                                if (pod.getTitle().equals("Result")) {
                                    System.out.println(pod.getTitle());
                                    for (WASubpod subpod : pod.getSubpods()) {
                                        for (Object element : subpod.getContents()) {
                                            if (element instanceof WAPlainText) {
                                                System.out.println(((WAPlainText) element).getText());
                                                try {
                                                    if (!((WAPlainText) element).getText().equals("(data not available)")) {
                                                        toAnswer = ((WAPlainTextImpl) element).getText();
                                                        say(ANSWER, toAnswer.replaceAll("|",""));
                                                    } else {
                                                        //  say(ANSWER, "I don't know this one.. Sorry i disappointed you");
                                                    }
                                                } catch (NullPointerException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (toAnswer == (null)) {
                            for (final WAPod podd : queryResult.getPods()) {
                                if (!podd.isError()) {
                                    if (podd.getTitle().equals("Basic information") || podd.getTitle().equals("Input interpretation")) {
                                        System.out.println(podd.getTitle());
                                        for (WASubpod subpod : podd.getSubpods()) {
                                            for (Object element : subpod.getContents()) {
                                                if (element instanceof WAPlainText) {
                                                    System.out.println(((WAPlainText) element).getText());
                                                    if (!((WAPlainText) element).getText().equals("(data not available)")) {
                                                        tempText = tempText + ((WAPlainText) element).getText();
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }

                            say(ANSWER, tempText.replaceAll("|",""));
                        } else {

                        }
                    }
                    pd.dismiss();
                }
            }catch (NullPointerException e){
                e.printStackTrace();
                say(ANSWER,"No network connection, sorry");
            }
        }
    }

    public void startCards(){
        if(hand1!=null) {
            sharedPreferences.edit().putBoolean("initialized2", true).commit();
            initialized2 = true;
            hand1.setBackgroundResource(R.drawable.trans);
        }
        startActivity(new Intent(getApplicationContext(),MultipleItemList.class));
            }

    private static final int SWIPE_MIN_DISTANCE = 120;

    private static final int SWIPE_THRESHOLD_VELOCITY = 200;

    public void expand(final View v) {
        v.measure(AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.WRAP_CONTENT);
        final int targtetHeight = v.getMeasuredHeight();

        v.getLayoutParams().height = 0;
        v.setVisibility(View.VISIBLE);
        Animation a = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                v.getLayoutParams().height = interpolatedTime == 1
                        ? AbsListView.LayoutParams.WRAP_CONTENT
                        : (int)(targtetHeight * interpolatedTime);
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration((int)(200));
        v.startAnimation(a);
        v.getAnimation().setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startCards();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public void collapse(final View v) {
        final int initialHeight = v.getMeasuredHeight();

        Animation a = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if(interpolatedTime == 1){
                    v.setVisibility(View.GONE);
                }else{
                    v.getLayoutParams().height = initialHeight - (int)(initialHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration((int)(250));
        v.startAnimation(a);
        v.getAnimation().setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startCards();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            startActivity(new Intent(getApplicationContext(),MultipleItemList.class));
            return super.onDoubleTap(e);
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if(e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
            }  else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
            }

            if(e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
            }  else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                startCards();
            }
            return false;
        }
    }

    public static long pushAppointmentsToCalender(Activity curActivity, String title, String addInfo, String place, int status, long startDate, boolean needReminder, boolean needMailService) {
        /***************** Event: note(without alert) *******************/

        String eventUriString = "content://com.android.calendar/events";
        ContentValues eventValues = new ContentValues();

        eventValues.put("calendar_id", 1); // id, We need to choose from
        // our mobile for primary
        // its 1
        eventValues.put("title", title);
        eventValues.put("description", addInfo);
        eventValues.put("eventLocation", place);

        long endDate = startDate + 1000 * 60 * 60; // For next 1hr

        eventValues.put("dtstart", startDate);
        eventValues.put("dtend", endDate);

        // values.put("allDay", 1); //If it is bithday alarm or such
        // kind (which should remind me for whole day) 0 for false, 1
        // for true
        eventValues.put("eventStatus", status); // This information is
        // sufficient for most
        // entries tentative (0),
        // confirmed (1) or canceled
        // (2):

   /*Comment below visibility and transparency  column to avoid java.lang.IllegalArgumentException column visibility is invalid error */

    /*eventValues.put("visibility", 3); // visibility to default (0),
                                        // confidential (1), private
                                        // (2), or public (3):
    eventValues.put("transparency", 0); // You can control whether
                                        // an event consumes time
                                        // opaque (0) or transparent
                                        // (1).
      */
        eventValues.put("hasAlarm", 1); // 0 for false, 1 for true
        eventValues.put("eventTimezone", TimeZone.getDefault().getID());

        Uri eventUri = curActivity.getApplicationContext().getContentResolver().insert(Uri.parse(eventUriString), eventValues);
        long eventID = Long.parseLong(eventUri.getLastPathSegment());

        if (needReminder) {
            /***************** Event: Reminder(with alert) Adding reminder to event *******************/

            String reminderUriString = "content://com.android.calendar/reminders";

            ContentValues reminderValues = new ContentValues();

            reminderValues.put("event_id", eventID);
            reminderValues.put("minutes", 5); // Default value of the
            // system. Minutes is a
            // integer
            reminderValues.put("method", 1); // Alert Methods: Default(0),
            // Alert(1), Email(2),
            // SMS(3)

            Uri reminderUri = curActivity.getApplicationContext().getContentResolver().insert(Uri.parse(reminderUriString), reminderValues);
        }

        /***************** Event: Meeting(without alert) Adding Attendies to the meeting *******************/

        if (needMailService) {
            String attendeuesesUriString = "content://com.android.calendar/attendees";

            /********
             * To add multiple attendees need to insert ContentValues multiple
             * times
             ***********/
            ContentValues attendeesValues = new ContentValues();

            attendeesValues.put("event_id", eventID);
            attendeesValues.put("attendeeName", "xxxxx"); // Attendees name
            attendeesValues.put("attendeeEmail", "yyyy@gmail.com");// Attendee
            // E
            // mail
            // id
            attendeesValues.put("attendeeRelationship", 0); // Relationship_Attendee(1),
            // Relationship_None(0),
            // Organizer(2),
            // Performer(3),
            // Speaker(4)
            attendeesValues.put("attendeeType", 0); // None(0), Optional(1),
            // Required(2), Resource(3)
            attendeesValues.put("attendeeStatus", 0); // NOne(0), Accepted(1),
            // Decline(2),
            // Invited(3),
            // Tentative(4)

            Uri attendeuesesUri = curActivity.getApplicationContext().getContentResolver().insert(Uri.parse(attendeuesesUriString), attendeesValues);
        }

        return eventID;

    }
}


