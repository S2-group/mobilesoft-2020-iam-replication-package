package ph.coreproc.android.philippineincometax;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ph.coreproc.android.philippineincometax.adapters.IncomeAdapter;
import ph.coreproc.android.philippineincometax.models.IncomeTaxCalculationModel;
import ph.coreproc.android.philippineincometax.models.NonTaxableIncomeModel;
import ph.coreproc.android.philippineincometax.models.TaxableIncomeModel;
import ph.coreproc.android.philippineincometax.objects.IncomeTaxCalculation;
import ph.coreproc.android.philippineincometax.objects.NonTaxableIncome;
import ph.coreproc.android.philippineincometax.objects.TaxableIncome;
import ph.coreproc.android.philippineincometax.util.NetworkHelper;

public class MainActivity extends AppCompatActivity {

    private static final String SCREEN_NAME = "Home Activity";

    private IncomeTaxCalculation mIncomeTaxCalculation;
    private IncomeTaxCalculationModel mIncomeTaxCalculationModel;
    private Context mContext;
    private CheckBox mSssCheckBox;
    private CheckBox mPhilhealthCheckBox;
    private CheckBox mPagibigCheckBox;
    private EditText mSalaryEditText;
    private TextView mNetIncomeTextView;
    private TextView mWithholdingTaxTextView;
    private LinearLayout mMoreOptionsLinearLayout;
    private TextView mMoreOptionsTextView;
    private TextView mDependentsTextView;
    private SeekBar mDependentsSeekBar;
    private Spinner mWithholdingTaxTypeSpinner;
    private TaxableIncomeModel mTaxableIncomeModel;
    private NonTaxableIncomeModel mNonTaxableIncomeModel;
    private LinearLayout mTaxableIncomeLinearLayout;
    private LinearLayout mNonTaxableIncomeLinearLayout;
    private ArrayList<HashMap<String, Object>> mCalculatorActivityItems;
    private PackageManager mPackageManager;
    private TextView mSssAmountTextView;
    private TextView mPhilhealthAmountTextView;
    private TextView mPagibigAmountTextView;

    private AdView mAdView;
    private NetworkHelper mNetworkHelper;
    private Tracker mTracker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide keyboard
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setIcon(R.drawable.ic_launcher);
        }

        // Let's look for a caclulator application
        mCalculatorActivityItems = new ArrayList<HashMap<String, Object>>();
        mPackageManager = getPackageManager();
        List<PackageInfo> packs = mPackageManager.getInstalledPackages(0);
        for (PackageInfo pi : packs) {
            if (pi.packageName.toLowerCase().contains("calcul")) {
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("appName", pi.applicationInfo.loadLabel(mPackageManager));
                map.put("packageName", pi.packageName);
                mCalculatorActivityItems.add(map);
            }
        }

        mContext = this;

        mIncomeTaxCalculation = new IncomeTaxCalculation(0);
        mIncomeTaxCalculationModel = new IncomeTaxCalculationModel(mContext);
        mIncomeTaxCalculationModel.create(mIncomeTaxCalculation);

        mTaxableIncomeModel = new TaxableIncomeModel(mContext);
        mNonTaxableIncomeModel = new NonTaxableIncomeModel(mContext);

        mTaxableIncomeLinearLayout = (LinearLayout) findViewById(R.id.taxableIncomeLinearLayout);
        mNonTaxableIncomeLinearLayout = (LinearLayout) findViewById(R.id.nonTaxableIncomeLinearLayout);

        mSssCheckBox = (CheckBox) findViewById(R.id.sssCheckBox);
        mSssCheckBox.setChecked(mIncomeTaxCalculation.isSssActive());
        mSssCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compute();
            }
        });
        mPhilhealthCheckBox = (CheckBox) findViewById(R.id.philhealthCheckBox);
        mPhilhealthCheckBox.setChecked(mIncomeTaxCalculation
                .isPhilhealthActive());
        mPhilhealthCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compute();
            }
        });
        mPagibigCheckBox = (CheckBox) findViewById(R.id.pagibigCheckBox);
        mPagibigCheckBox.setChecked(mIncomeTaxCalculation.isPagibigActive());
        mPagibigCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compute();
            }
        });

        // Now we get the textview amounts
        mSssAmountTextView = (TextView) findViewById(R.id.sssAmountTextView);
        mPhilhealthAmountTextView = (TextView) findViewById(R.id.philhealthAmountTextView);
        mPagibigAmountTextView = (TextView) findViewById(R.id.pagibigAmountTextView);

        mSalaryEditText = (EditText) findViewById(R.id.salaryEditText);
        if (mIncomeTaxCalculation.getSalaryRate() > 0) {
            mSalaryEditText.setText(mIncomeTaxCalculation.getSalaryRate() + "");
        }
        mSalaryEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                compute();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        mNetIncomeTextView = (TextView) findViewById(R.id.netIncomeTextView);
        mWithholdingTaxTextView = (TextView) findViewById(R.id.withholdingTaxTextView);
        mMoreOptionsLinearLayout = (LinearLayout) findViewById(R.id.moreOptionsLinearLayout);

        mMoreOptionsTextView = (TextView) findViewById(R.id.moreOptionsTextView);
        mMoreOptionsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                toggleMoreOptions();
            }
        });

        mDependentsTextView = (TextView) findViewById(R.id.dependentsTextView);
        mDependentsSeekBar = (SeekBar) findViewById(R.id.dependentsSeekBar);
        mDependentsSeekBar.setMax(4);
        mDependentsSeekBar.incrementProgressBy(1);
        mDependentsSeekBar.setProgress(mIncomeTaxCalculation.getDependents());
        mDependentsSeekBar
                .setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                    @Override
                    public void onStopTrackingTouch(SeekBar arg0) {
                        // Nothing here
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar arg0) {
                        // Nothing here
                    }

                    @Override
                    public void onProgressChanged(SeekBar arg0, int arg1,
                                                  boolean arg2) {
                        if (arg1 == arg0.getMax()) {
                            mDependentsTextView.setText(arg1 + " or more");
                        } else {
                            mDependentsTextView.setText(arg1 + "");
                        }
                        compute();
                    }
                });

        mWithholdingTaxTypeSpinner = (Spinner) findViewById(R.id.withholdingTaxTypeSpinner);
        mWithholdingTaxTypeSpinner.setSelection(mIncomeTaxCalculation
                .getWithholdingTaxTypeId() - 1);
        mWithholdingTaxTypeSpinner
                .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int arg2, long arg3) {
                        if (arg2 == 0) {
                            mDependentsSeekBar.setEnabled(false);
                            mDependentsSeekBar.setProgress(0);
                        } else {
                            mDependentsSeekBar.setEnabled(true);
                        }
                        compute();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {
                        // Nothing here
                    }
                });

        TextView addTaxableIncomeTextView = (TextView) findViewById(R.id.addTaxableIncomeTextView);
        addTaxableIncomeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                final View view = LayoutInflater.from(mContext).inflate(
                        R.layout.dialog_add_income, null);

                final AlertDialog alertDialog = new AlertDialog.Builder(
                        mContext).create();
                alertDialog.setTitle("Add A Taxable Income");
                alertDialog.setView(view);
                alertDialog.setCancelable(false);
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                            }
                        }
                );
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                dialog.dismiss();
                            }
                        }
                );
                alertDialog.show();
                Button alertDialogButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                if (alertDialogButton != null) {
                    alertDialogButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View arg0) {
                            assert view != null;
                            EditText incomeNameEditText = (EditText) view
                                    .findViewById(R.id.incomeNameEditText);
                            EditText incomeAmountEditText = (EditText) view
                                    .findViewById(R.id.incomeAmountEditText);
                            Editable incomeNameEditTextText = incomeNameEditText.getText();
                            String incomeName = "";
                            if (incomeNameEditTextText != null) {
                                incomeName = incomeNameEditTextText.toString().trim();
                            }
                            Editable incomeAmountEditTextText = incomeAmountEditText
                                    .getText();
                            String incomeAmountString = "";
                            if (incomeAmountEditTextText != null) {
                                incomeAmountString = incomeAmountEditTextText.toString().trim();
                            }

                            if (incomeAmountString.length() <= 0) {
                                incomeAmountEditText
                                        .setError("Amount needs to be greater than 0");
                                return;
                            }
                            double incomeAmount = Double
                                    .parseDouble(incomeAmountString);
                            if (incomeAmount <= 0) {
                                incomeAmountEditText
                                        .setError("Amount needs to be greater than 0");
                                return;
                            }

                            // Save the income
                            TaxableIncome ti = new TaxableIncome(
                                    mIncomeTaxCalculation, incomeName,
                                    incomeAmount);
                            mTaxableIncomeModel.create(ti);
                            IncomeAdapter tia = new IncomeAdapter(ti);
                            mTaxableIncomeLinearLayout.addView(tia
                                    .getView(mContext));
                            compute();
                            alertDialog.dismiss();
                        }
                    });
                }
            }
        });

        TextView addNonTaxableIncomeTextView = (TextView) findViewById(R.id.addNonTaxableIncomeTextView);
        addNonTaxableIncomeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                final View view = LayoutInflater.from(mContext).inflate(
                        R.layout.dialog_add_income, null);

                final AlertDialog alertDialog = new AlertDialog.Builder(
                        mContext).create();
                alertDialog.setTitle("Add A Non-Taxable Income");
                alertDialog.setView(view);
                alertDialog.setCancelable(false);
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                            }
                        }
                );
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                dialog.dismiss();
                            }
                        }
                );
                alertDialog.show();
                Button alertDialogButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);

                if (alertDialogButton != null) {
                    alertDialogButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View arg0) {
                            assert view != null;
                            EditText incomeNameEditText = (EditText) view
                                    .findViewById(R.id.incomeNameEditText);
                            EditText incomeAmountEditText = (EditText) view
                                    .findViewById(R.id.incomeAmountEditText);
                            Editable incomeNameEditTextText = incomeNameEditText.getText();
                            String incomeName = "";
                            if (incomeNameEditTextText != null) {
                                incomeName = incomeNameEditTextText.toString().trim();
                            }
                            Editable incomeAmountEditTextText = incomeAmountEditText.getText();
                            String incomeAmountString = "";
                            if (incomeAmountEditTextText != null) {
                                incomeAmountString = incomeAmountEditTextText.toString().trim();
                            }
                            if (incomeAmountString.length() <= 0) {
                                incomeAmountEditText
                                        .setError("Amount needs to be greater than 0");
                                return;
                            }
                            double incomeAmount = Double
                                    .parseDouble(incomeAmountString);
                            if (incomeAmount <= 0) {
                                incomeAmountEditText
                                        .setError("Amount needs to be greater than 0");
                                return;
                            }

                            // Save the income
                            NonTaxableIncome ti = new NonTaxableIncome(
                                    mIncomeTaxCalculation, incomeName,
                                    incomeAmount);
                            mNonTaxableIncomeModel.create(ti);
                            IncomeAdapter ia = new IncomeAdapter(ti);
                            mNonTaxableIncomeLinearLayout.addView(ia
                                    .getView(mContext));
                            compute();
                            alertDialog.dismiss();
                        }
                    });
                }
            }
        });

        mAdView = (AdView) findViewById(R.id.adView);

        initAds();
        initGA();
    }

    public void toggleMoreOptions() {
        if (mMoreOptionsLinearLayout.getVisibility() == View.VISIBLE) {
            mMoreOptionsLinearLayout.setVisibility(View.GONE);
            mMoreOptionsTextView.setText("More Income Tax Options");
        } else {
            mMoreOptionsLinearLayout.setVisibility(View.VISIBLE);
            mMoreOptionsTextView.setText("Less Income Tax Options");
        }
    }

    public void compute() {
        Editable salaryEditable = mSalaryEditText.getText();
        String salaryStringValue = "";
        if (salaryEditable != null) {
            salaryStringValue = salaryEditable.toString().trim();
        }
        double salary = 0;
        if (salaryStringValue.length() > 0) {
            salary = Double.parseDouble(mSalaryEditText.getText().toString());
            if (salary >= 10000000) {
                mSalaryEditText.setError("Number is too large");
                return;
            }
        }

        mIncomeTaxCalculation.setSssActive(mSssCheckBox.isChecked());
        mIncomeTaxCalculation.setPhilhealthActive(mPhilhealthCheckBox
                .isChecked());
        mIncomeTaxCalculation.setPagibigActive(mPagibigCheckBox.isChecked());

        mIncomeTaxCalculation.setSalaryFrequency(1);
        mIncomeTaxCalculation
                .setWithholdingTaxTypeId(mWithholdingTaxTypeSpinner
                        .getSelectedItemPosition() + 1);
        mIncomeTaxCalculation.setDependents(mDependentsSeekBar.getProgress());

        mIncomeTaxCalculation.setSalaryRate(salary, mContext);

        double netIncome = mIncomeTaxCalculation.getNetIncome(mContext);
        mNetIncomeTextView.setText("P"
                + customFormat("###,###,###,###.##", netIncome));

        mWithholdingTaxTextView.setText("P"
                + customFormat("###,###,###,###.##",
                mIncomeTaxCalculation.getWithholdingTax(mContext).getWithholdingTaxAmount()));

        if (mIncomeTaxCalculation.getSss() != null) {
            mSssAmountTextView.setText("P" + customFormat("###.##", mIncomeTaxCalculation.getSss().getEmployeeContribution()));
        } else {
            mSssAmountTextView.setText("P0.00");
        }

        if (mIncomeTaxCalculation.getPhilhealth() != null) {
            mPhilhealthAmountTextView.setText("P" + customFormat("###.##", mIncomeTaxCalculation.getPhilhealth().getEmployeeContribution()));
        } else {
            mPhilhealthAmountTextView.setText("P0.00");
        }

        if (mIncomeTaxCalculation.getPagibig() != null) {
            mPagibigAmountTextView.setText("P" + customFormat("###.##", mIncomeTaxCalculation.getPagibig().getEmployeeContribution()));
        } else {
            mPagibigAmountTextView.setText("P0.00");
        }

        mIncomeTaxCalculationModel.update(mIncomeTaxCalculation);
    }

    private String customFormat(String pattern, double value) {
        DecimalFormat myFormatter = new DecimalFormat(pattern);
        return myFormatter.format(value);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_calculator:
                if (mCalculatorActivityItems.size() >= 1) {
                    String packageName = (String) mCalculatorActivityItems.get(0).get("packageName");
                    Intent i = mPackageManager.getLaunchIntentForPackage(packageName);
                    if (i != null)
                        startActivityForResult(i, 1);
                } else {
                    Toast.makeText(mContext, "You do not have any caculator applications", Toast.LENGTH_SHORT).show();
                }
                return true;
            case R.id.action_reset:
                Intent i = new Intent(MainActivity.this, MainActivity.class);
                MainActivity.this.startActivity(i);
                MainActivity.this.finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Ads
     */
    private void initAds() {
        loadAds();

        mNetworkHelper = new NetworkHelper(mContext) {
            @Override
            public void onNetworkAvailabilityChanged(boolean isNetworkAvailable) {
                if (isNetworkAvailable) {
                    mAdView.setVisibility(View.VISIBLE);
                    loadAds();
                } else {
                    mAdView.setVisibility(View.GONE);
                }
            }
        };
        mNetworkHelper.setTimeout(1000);

        mAdView.setVisibility(mNetworkHelper.isNetworkAvailable() ?
                        View.VISIBLE : View.GONE
        );

        mNetworkHelper.startNetworkAvailabilityChecking();
    }

    private void loadAds() {
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }


    /**
     * GA
     */
    public void initGA() {
        App application = (App) getApplication();
        mTracker = application.getDefaultTracker();
    }


    /**
     * Called when returning to the activity
     */
    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
        if (mTracker != null) {
            mTracker.setScreenName(SCREEN_NAME);
            mTracker.send(new HitBuilders.ScreenViewBuilder().build());
        }
    }

    /**
     * Called before the activity is destroyed
     */
    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        mNetworkHelper.stopNetworkAvailabilityChecking();
        super.onDestroy();
    }

}