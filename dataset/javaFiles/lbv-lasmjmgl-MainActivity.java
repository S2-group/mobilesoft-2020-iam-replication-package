package ms.gwillia.lbv;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;


public class MainActivity extends Activity {


    private TextView txtSpeechInput;
    private ImageButton btnSpeak;
    private HorizontalScrollView hsv;
    private LinearLayout hLL;
    private final int REQ_CODE_SPEECH = 12222;
    ArrayList<AppInfo> pkgAppsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        pkgAppsList = getInstalledApps(true);
        hsv = (HorizontalScrollView) findViewById(R.id.horizontalScrollView);
        hLL = (LinearLayout) findViewById(R.id.horizontalLL);
        txtSpeechInput = (TextView) findViewById(R.id.txtSpeechInput);
        btnSpeak = (ImageButton) findViewById(R.id.btnSpeak);

        // hide the action bar and ask for input on startup
        getActionBar().hide();

        promptSpeechInput();

        btnSpeak.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                hLL.removeAllViews();
                txtSpeechInput.setText("LaunchByVoice");
                promptSpeechInput();
            }
        });
    }

    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    /**
     * Receiving speech input
     * */
//        @TODO: let user select from apps when multiple matches are found
     @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String term = result.get(0).toLowerCase();

                    ArrayList<AppInfo> found = new ArrayList<AppInfo>();
                    for(int i = 0; i < pkgAppsList.size(); i++) {
                        AppInfo ai = pkgAppsList.get(i);
                        if((ai.getAppName().toLowerCase().contains(term) || ai.getPackageName().toLowerCase().contains(term) || ai.getAppName().toLowerCase().contains(term.replaceAll(" ", "")))) {
                            Log.i(getString(R.string.app_name), ai.getAppName() + "--->" + ai.getPackageName());
                            found.add(ai);
                        }
                    }
                    if(found.size() == 0) {
                        txtSpeechInput.setText("Sorry, '" + result.get(0) + "' not found");
                    }else {
                        if (found.size() == 1) {
                            launchIntent(found.get(0));
                        } else {
                            txtSpeechInput.setText(found.size() + " apps found for: " + term);
                            for (int i = 0; i < found.size(); i++) {
                                ImageButton ib = new ImageButton(this);
                                final AppInfo ai = found.get(i);
                                Bitmap bitmap = ((BitmapDrawable) ai.getIcon()).getBitmap();
                                Drawable d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 150, 150, true));
                                ib.setBackground(d);
                                ib.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        if(launchIntent(ai)) {
                                            hLL.removeAllViews();
                                            hsv.setVisibility(View.INVISIBLE);
                                        }
                                    }
                                });
                                hLL.addView(ib);
                                hsv.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                }
                break;
            }

        }
    }

    private boolean launchIntent(AppInfo ai) {
        Intent intent;
        PackageManager manager = getPackageManager();
        try {
            intent = manager.getLaunchIntentForPackage(ai.getPackageName());
            if (intent == null)
                throw new PackageManager.NameNotFoundException();
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            txtSpeechInput.setText("LaunchByVoice");
            startActivity(intent);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(getString(R.string.app_name), e.toString());
            txtSpeechInput.setText("Sorry, '" + ai.getAppName() + "' could not be launched");
            return false;
        }
    }

    private ArrayList<AppInfo> getInstalledApps(boolean getSysPackages) {
        ArrayList<AppInfo> res = new ArrayList<AppInfo>();
        List<PackageInfo> packs = getPackageManager().getInstalledPackages(0);
        for(int i=0;i<packs.size();i++) {
            PackageInfo p = packs.get(i);
            if ((!getSysPackages) && (p.versionName == null)) {
                continue ;
            }
            AppInfo ai = new AppInfo();
            ai.setPackageName(p.packageName);
            ai.setAppName(p.applicationInfo.loadLabel(getPackageManager()).toString());
            ai.setIcon(p.applicationInfo.loadIcon(getPackageManager()));
            res.add(ai);
        }
        return res;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
        return super.onOptionsItemSelected(item);
    }
}
