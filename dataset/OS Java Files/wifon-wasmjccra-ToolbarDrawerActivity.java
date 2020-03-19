package com.croconaut.ratemebuddy.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.StringSignature;
import com.croconaut.cpt.data.Communication;
import com.croconaut.ratemebuddy.AppData;
import com.croconaut.ratemebuddy.ExternalUriContract;
import com.croconaut.ratemebuddy.R;
import com.croconaut.ratemebuddy.SharedFilesContract;
import com.croconaut.ratemebuddy.ui.views.transformation.CircleTransform;
import com.croconaut.ratemebuddy.utils.CommonUtils;
import com.croconaut.ratemebuddy.utils.ProfileUtils;
import com.croconaut.ratemebuddy.utils.ThemeManager;
import com.croconaut.ratemebuddy.utils.ThemeUtils;
import com.croconaut.ratemebuddy.utils.pojo.profiles.MyProfile;
import com.croconaut.tictactoe.ui.activities.MenuActivity;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import eu.inloop.viewmodel.base.ViewModelBaseEmptyActivity;


public class ToolbarDrawerActivity extends ViewModelBaseEmptyActivity implements CptProcessor {
    // default color value for application (our case == green)
    private static final String STATE_SELECTED_POSITION = "stateSelectedPosition";
    private static final String TAG = ToolbarDrawerActivity.class.getName();
    private static final int PENDING_INTENT_TXT_REQUEST_CODE = 55886;
    protected DrawerLayout drawerLayout;

    /**
     * Fonts
     */
    protected Typeface tLight;
    protected Typeface tRegular;
    protected Typeface tSemiBold;
    protected Typeface tBold;

    /**
     * Utils.
     */
    protected ThemeManager theme;
    protected SharedPreferences prefs;
    protected AppData appData;
    protected Context mContext;
    protected Resources mRes;
    protected Toolbar toolbar;
    protected ProfileUtils profileUtils;
    protected CommonUtils commonUtils;
    protected NavigationView navigationView;
    private int mCurrentSelectedPosition;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "On DESTROY CALLED");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initiliazeViewsAndResources();
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initilizeAppBackground();
        if (savedInstanceState != null) {
            mCurrentSelectedPosition = savedInstanceState.getInt(STATE_SELECTED_POSITION);
        }

        if (getIntent().getExtras() != null) {
            mCurrentSelectedPosition = getIntent().getExtras().getInt(STATE_SELECTED_POSITION);
        }

        setUpNavigationDrawer();
        getWindow().getDecorView().setBackgroundColor(ContextCompat.getColor(mContext,R.color.material_white));
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout != null && drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else {
            super.onBackPressed();
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initializeHeader();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_SELECTED_POSITION, mCurrentSelectedPosition);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mCurrentSelectedPosition = savedInstanceState.getInt(STATE_SELECTED_POSITION, 0);
        if (navigationView != null) {
            Menu menu = navigationView.getMenu();
            if (mCurrentSelectedPosition != 0)
                menu.getItem(mCurrentSelectedPosition - 1).setChecked(true);
        }
    }

    protected void setUpNavigationDrawer() {
        if (toolbar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationIcon(R.drawable.ic_action_drawer);
            drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
            drawerLayout.setBackgroundColor(ContextCompat.getColor(mContext,R.color.material_white));

            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            });

            ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name) {
                @Override
                public void onDrawerClosed(View drawerView) {
                    super.onDrawerClosed(drawerView);
                    InputMethodManager inputMethodManager = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                }

                @Override
                public void onDrawerOpened(View drawerView) {
                    super.onDrawerOpened(drawerView);
                    InputMethodManager inputMethodManager = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                }
            };

            drawerLayout.setDrawerListener(actionBarDrawerToggle);

        }
    }

    @Override
    public boolean process(Intent cptIntent) throws IOException, ClassNotFoundException {
        initializeHeader();
        return false;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU) {

            if (drawerLayout == null)
                return true;

            if (!drawerLayout.isDrawerOpen(Gravity.LEFT)) {
                drawerLayout.openDrawer(Gravity.LEFT);
            } else if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
                drawerLayout.closeDrawer(Gravity.LEFT);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    protected void setUpNavigationView() {
        navigationView = (NavigationView) findViewById(R.id.navigationView);
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(MenuItem menuItem) {


                    Bundle bundle = new Bundle();
                    switch (menuItem.getItemId()) {
                        case R.id.timeline:
                            mCurrentSelectedPosition = 1;
                            bundle.putInt(STATE_SELECTED_POSITION, mCurrentSelectedPosition);
                            startActivity(new Intent(ToolbarDrawerActivity.this, TimelineActivity.class).putExtras(bundle));
                            return true;
                        case R.id.people:
                            mCurrentSelectedPosition = 2;
                            bundle.putInt(STATE_SELECTED_POSITION, mCurrentSelectedPosition);
                            startActivity(new Intent(mContext, PeopleParentActivityBB.class).putExtras(bundle));
                            return true;
                        case R.id.settings:
                            mCurrentSelectedPosition = 3;
                            bundle.putInt(STATE_SELECTED_POSITION, mCurrentSelectedPosition);
                            startActivity(new Intent(mContext, SettingsActivity.class).putExtras(bundle));
                            return true;
                        case R.id.share:
                            mCurrentSelectedPosition = 4;
                            bundle.putInt(STATE_SELECTED_POSITION, mCurrentSelectedPosition);
                            File apkFile = readApk();
                            if (apkFile == null) {
                                Toast.makeText(mContext, mRes.getString(R.string.toast_share_problem), Toast.LENGTH_LONG).show();
                                return true;
                            }

                            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                            sharingIntent.setType(MimeTypeMap.getSingleton().getMimeTypeFromExtension("apk"));
                            sharingIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);  // needed because android:exported = false for our CP
                            //sharingIntent.setComponent(new ComponentName("com.android.bluetooth", "com.android.bluetooth.opp.BluetoothOppLauncherActivity"));
                            sharingIntent.putExtra(Intent.EXTRA_STREAM, Uri.withAppendedPath(SharedFilesContract.getRootUri(ToolbarDrawerActivity.this), apkFile.getPath()));
                            // ForResult is mandatory even if not used!
                            startActivityForResult(Intent.createChooser(sharingIntent, mRes.getString(R.string.action_share)), -1);
                            return true;
                        case R.id.inviteFriend:
                            mCurrentSelectedPosition = 5;

                            String name = MyProfile.getInstance(getApplicationContext()).getName();
                            String myProfileId = MyProfile.getInstance(getApplicationContext()).getProfileId();

                            Uri baseUri = ExternalUriContract.PROFILE_URI.buildUpon()
                                    .appendQueryParameter(ExternalUriContract.PARAM_PROFILE_NAME, name)
                                    .build();

                            String subject = String.format(
                                    getResources().getString(R.string.invite_profile_subject),
                                    getResources().getString(R.string.app_name),
                                    name
                            );

                            String text = String.format(
                                    getResources().getString(R.string.invite_profile_text),
                                    getResources().getString(R.string.app_name),
                                    "%1$s",   // will be replaced by the complete uri
                                    name.replace("%", "%%") // in case name contains '%'
                            );

                            String textHtml = String.format(
                                    getResources().getString(R.string.invite_profile_text_hml),
                                    getResources().getString(R.string.app_name),
                                    "%1$s",   // will be replaced by the complete uri
                                    name.replace("%", "%%") // in case name contains '%'
                            );

                            Communication.inviteFriend(mContext,
                                    mRes.getString(R.string.action_invite_friend),
                                    subject,
                                    text,
                                    textHtml,
                                    baseUri,
                                    ExternalUriContract.PARAM_PROFILE_CROCO_ID
                            );
                            return true;
                        case R.id.about:
                            mCurrentSelectedPosition = 6;
                            bundle.putInt(STATE_SELECTED_POSITION, mCurrentSelectedPosition);
                            startActivity(new Intent(mContext, AboutActivity.class).putExtras(bundle));
                            return true;
                        case R.id.writeToCeo:
                            Intent ceoIntent = new Intent(mContext, CommunicationActivity.class);
                            ceoIntent.putExtra(CommunicationActivity.EXTRA_TARGET_CROCO_ID, CommonUtils.CEO_CROCO_ID);
                            startActivity(ceoIntent);
                            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                            return true;
                        case R.id.reportBug:
                            Communication.sendCptLogs(mContext);
                            return true;
                        case R.id.gameTicTacToe:
                            startActivity(MenuActivity.newStartIntent(getApplicationContext()));
                            return true;
                        default:
                            return true;
                    }
                }
            });

            View header = navigationView.getHeaderView(0);
            header.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(mContext, EditProfileActivity.class));
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
            });
        }

        initializeHeader();
    }

    private File readApk() {
        PackageManager pm = getPackageManager();
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);
        for (ApplicationInfo packageInfo : packages) {
            if (packageInfo.sourceDir.contains(getApplicationContext().getPackageName())) {
                return new File(packageInfo.sourceDir);
            }
        }
        return null;
    }

    private void initilizeAppBackground() {
        getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(mContext,R.color.white_background)));
    }

    private void initiliazeViewsAndResources() {
        theme = new ThemeManager(this);
        tLight = Typeface.createFromAsset(getAssets(), "fonts/light.ttf");
        tRegular = Typeface.createFromAsset(getAssets(), "fonts/regular.ttf");
        tSemiBold = Typeface.createFromAsset(getAssets(), "fonts/semibold.ttf");
        tBold = Typeface.createFromAsset(getAssets(), "fonts/bold.ttf");
        mRes = getResources();
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        commonUtils = new CommonUtils();
        appData = (AppData) getApplication();
        profileUtils = new ProfileUtils(appData);
        mContext = this;
    }

    protected void initializeHeader() {
        if (navigationView == null) return;

        View header = navigationView.getHeaderView(0);
        ImageView ivCover = (ImageView) header.findViewById(R.id.ivCoverDrawer);
        final ImageView ivPhoto = (ImageView) header.findViewById(R.id.ivPhotoDrawer);
        TextView tvName = (TextView) header.findViewById(R.id.tvNameDrawer);
        TextView tvStatus = (TextView) header.findViewById(R.id.tvStatusDrawer);
        TextView tvLikes = (TextView) header.findViewById(R.id.tvLikesDrawer);
        TextView tvComments = (TextView) header.findViewById(R.id.tvCommentsDrawer);

        MyProfile myProfile = MyProfile.getInstance(mContext);

        if (myProfile == null) {
            Log.e(TAG, "My profile is null, returning!");
            return;
        }

        Glide.with(this)
                .load(ThemeUtils.getBgCoverResId(prefs))
                .asBitmap()
                .thumbnail(0.2f)
                .into(ivCover);

        Glide.with(this)
                .load(myProfile.getThumbUri())
                .asBitmap()
                .signature(new StringSignature(
                        String.valueOf(MyProfile.getInstance(this).getTimeStamp())))
                .thumbnail(0.2f)
                .transform(new CircleTransform(mContext))
                .into(ivPhoto);

        tvName.setText(myProfile.getName());
        tvName.setTypeface(tSemiBold);
        tvName.setShadowLayer(3, 0, 1, Color.BLACK);
        tvStatus.setShadowLayer(5, 0, 1, Color.BLACK);

        RelativeLayout statusStats = (RelativeLayout) header.findViewById(R.id.rlStatusStatsDrawer);
        if (myProfile.getStatus() != null && myProfile.getStatus().getContent() != null) {
            tvStatus.setText(myProfile.getStatus().getContent());
            tvLikes.setText(String.valueOf(myProfile.getStatus().getVotes().size()));
            tvComments.setText(String.valueOf(myProfile.getStatus().getComments().size()));

            if (myProfile.getStatus().getContent().isEmpty()) {
                statusStats.setVisibility(View.GONE);
                tvStatus.setVisibility(View.GONE);
            } else {
                statusStats.setVisibility(View.VISIBLE);
                tvStatus.setVisibility(View.VISIBLE);

                statusStats.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ToolbarDrawerActivity.this, CommentActivity.class);
                        intent.putExtra(CommentActivity.EXTRA_CROCO_ID, MyProfile.getInstance(mContext).getIdent());
                        startActivity(intent);
                    }
                });
            }
        } else

        {
            tvStatus.setVisibility(View.GONE);
            statusStats.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Force overflow button to show on devices with hardware menu button.
     */
    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (featureId == Window.FEATURE_ACTION_BAR && menu != null) {
            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
                try {
                    Method m = menu.getClass().getDeclaredMethod(
                            "setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (NoSuchMethodException e) {
                    Log.e(TAG, "onMenuOpened", e);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }

    public void setToolbarTitle(@NonNull final String header){
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle(header);
    }

    public void initializeHeaderWithDrawer(String header, boolean showNavIcon) {
        toolbar = (Toolbar) findViewById(R.id.idToolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle(header);
        toolbar.setNavigationIcon(R.drawable.ic_action_drawer);
        setSupportActionBar(toolbar);
        setUpNavigationDrawer();
        setUpNavigationView();

        //if we dont want to show nav icon
        if (!showNavIcon) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_back);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }

        Menu menu = navigationView.getMenu();
        MenuItem checkedItem = null;
        if (mCurrentSelectedPosition != 0)
            checkedItem = menu.getItem(mCurrentSelectedPosition - 1).setChecked(true);

        for (int i = 0; i < menu.size(); i++) {
            MenuItem item = menu.getItem(i);
            if (checkedItem == null)
                item.setChecked(false);
            else if (item.getItemId() != checkedItem.getItemId()) item.setChecked(false);
        }

    }


}
