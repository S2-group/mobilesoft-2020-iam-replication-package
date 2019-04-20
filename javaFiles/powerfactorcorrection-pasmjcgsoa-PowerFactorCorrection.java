package com.gmail.simpson.o.alexis;


import com.appbrain.AdService;
import com.appbrain.AppBrain;
import com.startapp.android.publish.StartAppAd;
import com.startapp.android.publish.StartAppSDK;
//import com.startapp.android.publish.StartAppAd;
//import com.startapp.android.publish.StartAppSDK;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;
import java.util.Random;
//import java.util.Random;

public class PowerFactorCorrection extends Activity {
	
	private double power,voltage,frequency, capacitance;
	private double existing_pf;
	private double required_pf;
	private Button calculateBtn;
	private Button clearBtn;
	private EditText power_et;
	private EditText voltage_et;
	private EditText frequency_et;
	private EditText existing_pf_et;
	private EditText required_pf_et;
	private EditText capacitance_et;
	
	private final String STARTAPPDeveloper_ID = "102835337";
	private final String STARTAPP_App_ID = "203253004";
	private StartAppAd startAppAd;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        StartAppSDK.init(this, STARTAPPDeveloper_ID, STARTAPP_App_ID, false);
        AppBrain.init(this);
		setContentView(R.layout.activity_power_factor_correction);
       // StartAppAd.showSlider(this);
		startAppAd = new StartAppAd(this);


		power_et = (EditText)findViewById(R.id.power_ET2);
		voltage_et =(EditText)findViewById(R.id.Voltage_Et_2);
		frequency_et=(EditText)findViewById(R.id.Frequency_Et_2);
		existing_pf_et =(EditText)findViewById(R.id.Existing_pf_Et);
		required_pf_et = (EditText)findViewById(R.id.Required_pf_Et);
		capacitance_et = (EditText)findViewById(R.id.capacitance_et_2);
		calculateBtn = (Button)findViewById(R.id.Calculate_below_unity_btn);
		clearBtn = (Button)findViewById(R.id.clear_below_unity_btn);
		
		calculateBtn.setOnClickListener(new View.OnClickListener() {
			
			@SuppressLint("DefaultLocale")
			@Override
			public void onClick(View v) {
				
				if (power_et.getText().toString().length()== 0|| voltage_et.getText().toString().length()== 0||
						frequency_et.getText().toString().length()== 0||existing_pf_et.getText().toString().length()== 0||
						required_pf_et.getText().toString().length()== 0){
					
					//show dialog or toast 
					Toast.makeText(PowerFactorCorrection.this, R.string.dialog_message, Toast.LENGTH_SHORT).show();
					
				}else{
					//parse editTexts and initialize with there values
					power= Double.parseDouble(power_et.getText().toString());
					voltage= Double.parseDouble(voltage_et.getText().toString());
					frequency= Double.parseDouble(frequency_et.getText().toString());
					existing_pf= Double.parseDouble(existing_pf_et.getText().toString());
					required_pf= Double.parseDouble(required_pf_et.getText().toString());
					
					if (existing_pf > required_pf){
												
						AlertDialog.Builder builder = new AlertDialog.Builder(PowerFactorCorrection.this);

						// 2. Chain together various setter methods to set the dialog characteristics
						builder.setMessage(R.string.Dialog_message_2)
						       .setTitle(R.string.dialog_title);
						//create a dialog ok button
						builder.setPositiveButton(R.string.dialog_ok_btn, new DialogInterface.OnClickListener() {
					           public void onClick(DialogInterface dialog, int id) {
					               // User clicked OK button
					           }
					       });				

						// 3. Get the AlertDialog from create()
						AlertDialog dialog = builder.create();
						//show dialog
						dialog.show();
						
					}else if (existing_pf > 1 || required_pf > 1){
						Toast.makeText(PowerFactorCorrection.this, 
								R.string.Messsage_powerFactor_greater_1, Toast.LENGTH_SHORT).show();
						
					}else{
						
						capacitance = Calculation.calculateCapcitance(power, voltage, frequency, existing_pf, required_pf);
						//display in textbox
						String result =(String.format("%,.3f",capacitance));
						capacitance_et.setText(result);
					}
				}				
				
			}
		});
		
		clearBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			    power_et.getText().clear();
			    voltage_et.getText().clear();
			    frequency_et.getText().clear();
			    existing_pf_et.getText().clear();
				required_pf_et.getText().clear();
				capacitance_et.getText().clear();
				
			}
		});
		
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		startAppAd.onResume();

	}
	
	@Override
	public void onBackPressed() {

        Random random = new Random();
        int chance = random.nextInt(2);
        if (chance == 1) {
            startAppAd.onBackPressed();
        }else {
            AppBrain.getAds().showOfferWall(this);
        }
		super.onBackPressed();
	}
	
	@Override
	public void onPause() {
		super.onPause();
		startAppAd.onPause();
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_2, menu);
        AdService ads = AppBrain.getAds();
        MenuItem item = menu.add(ads.getOfferWallButtonLabel(this));
        ads.setOfferWallMenuItemClickListener(this, item);
        return super.onCreateOptionsMenu(menu);
        //return true;
    }
    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item){
        switch(item.getItemId()) {

            case R.id.rate:
                /*Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/details?id=com.gmail.simpson.o.alexis&hl=en"));
                startActivity(browserIntent);
                //Toast.makeText(this, "Thank you very much", Toast.LENGTH_LONG).show();*/

                if(isApplicationIstalledByPackageName ("com.slideme.sam.manager")){
                    // sam is installed, search SAM for bundleid;
                    // Create your market link intent to launch
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    //replace with your SlideME username
                    intent.setData(Uri.parse("sam://details?bundleId= 4671960a-6642-11e4-b79f-a97036bc0e10"));
                    startActivity(intent);
                   // 1119eff0-e2c3-11e3-815d-a97036bc0e10
                } else {
                    //sam is not installed, go to application details on slideme.org
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    //replace below url with the link to the application on slideme.org
                    intent.setData(Uri.parse("http://slideme.org/app/com.gmail.simpson.o.alexis"));
                    startActivity(intent);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public boolean isApplicationIstalledByPackageName(String packageName) {
        List<PackageInfo> packages = getPackageManager().getInstalledPackages(0);
        if (packages != null && packageName != null) {
            for (PackageInfo packageInfo : packages) {
                if (packageName.equals(packageInfo.packageName)) {
                    return true;
                }
            }
        }
        return false;
    }

	
}
