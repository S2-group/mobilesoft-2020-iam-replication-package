package com.tajchert.hours.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.tajchert.hours.R;
import com.tajchert.hours.Tools;
import com.tajchert.hours.widgets.Widget;
import com.tajchert.hours.widgets.WidgetInstance;
import com.tajchert.hours.widgets.WidgetListManager;
import com.tajchert.hours.WidgetUpdateService;

import java.util.ArrayList;

public class ActivityWidgetSettings extends Activity {
	private WidgetInstance widget;
	private SharedPreferences prefs;
	
	private int position;
	
	private CheckBox checkFullDay;
	private CheckBox checkEventColors;
	private CheckBox checkNotGoing;
	private SeekBar seekbarGradientTransparency;
	private SeekBar SeekBarPieIntensity;
	private SeekBar SeekBarOutIntensity;
	
	private Button buttonSelect;
	private Button buttonSelectOuter;
	
	
	private ImageButton stylePrev;
	private ImageButton styleNext;
	
	//Preview
	private ImageView viewUnder;
	private ImageView viewOver;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_widget_settings);
		setViewElements();
		Bundle b = getIntent().getExtras();
		String widgetId = b.getString("widgetID");
		prefs = getSharedPreferences("com.tajchert.hours", Context.MODE_PRIVATE);
		widget = WidgetListManager.getWidgetInstance(prefs, widgetId);
		position = widget.style;
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            try {
                getActionBar().setDisplayHomeAsUpEnabled(true);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
	}
	@Override
	protected void onResume() {
	    super.onResume();
	    if(widget != null){
	    	new SetWidgetData().execute("");
	    	//setState();
	    }else{
	    	this.finish();
	    }
	}
	
	@Override
	protected void onPause(){
		super.onPause();
		new SaveWidgetData().execute("");
	}
	
	private class SetWidgetData extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
        	//update values
        	widget = WidgetListManager.getWidgetInstance(prefs, widget.id+"");
            return "Executed";
        }
        @Override
        protected void onPostExecute(String result) {
        	setState();
        }
        @Override
        protected void onPreExecute() {}
    }
	
	
	private class SaveWidgetData extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
    		Intent serviceIntent = new Intent(ActivityWidgetSettings.this, WidgetUpdateService.class);
    		serviceIntent.putExtra("widgetID", widget.id+"");
    		ActivityWidgetSettings.this.startService(serviceIntent);
            return "Executed";
        }
        @Override
        protected void onPostExecute(String result) {
        	ActivityWidgetSettings.this.finish();
        }
        @Override
        protected void onPreExecute() {
        	if(widget!=null){
        		WidgetListManager.updateWidget(widget.id, prefs, widget);
    		}
        }
    }

	private void setViewElements(){
		seekbarGradientTransparency = (SeekBar) findViewById(R.id.seekbarGradientTransparency);
		SeekBarPieIntensity = (SeekBar) findViewById(R.id.SeekBarPieIntensity);
		SeekBarOutIntensity = (SeekBar) findViewById(R.id.SeekBarOut);
		buttonSelect = (Button) findViewById(R.id.buttonProgram);
		buttonSelectOuter = (Button) findViewById(R.id.buttonProgramOut);
		
		//Preview
		viewUnder = (ImageView) findViewById(R.id.imageViewClockUnderflow);
		viewOver = (ImageView) findViewById(R.id.imageViewClockOverflow);
		stylePrev = (ImageButton) findViewById(R.id.imageButtonPrev);
		styleNext = (ImageButton) findViewById(R.id.imageButtonNext);
		
		checkFullDay = (CheckBox) findViewById(R.id.checkBoxCalendarFullDay);
		checkEventColors = (CheckBox) findViewById(R.id.checkBoxCalendarColors);
		checkNotGoing = (CheckBox) findViewById(R.id.checkBoxCalendarGoing);
	}
	private void setState(){
		// Set underOverflow
		updatePreview();
		
		seekbarGradientTransparency.setProgress(widget.transparencyCenter);
		seekbarGradientTransparency.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
					int tmp = 0;
					public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
						tmp = progress;
					}
					public void onStartTrackingTouch(SeekBar seekBar) {}
					public void onStopTrackingTouch(SeekBar seekBar) {
						widget.transparencyCenter = tmp;
						updatePreview();
					}
		});
		SeekBarPieIntensity.setProgress(widget.transparencyInner);
		SeekBarPieIntensity.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
					int tmp = 0;
					public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
						tmp = progress;
					}
					public void onStartTrackingTouch(SeekBar seekBar) {}
					public void onStopTrackingTouch(SeekBar seekBar) {
						widget.transparencyInner = tmp;
						updatePreview();
					}
		});
		SeekBarOutIntensity.setProgress(widget.transparencyOuter);
		SeekBarOutIntensity.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
					int tmp = 0;
					public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
						tmp = progress;
					}
					public void onStartTrackingTouch(SeekBar seekBar) {}
					public void onStopTrackingTouch(SeekBar seekBar) {
						widget.transparencyOuter = tmp;
						updatePreview();
					}
		});
		if(widget.showFullDay){
			checkFullDay.setChecked(true);
		}else{
			checkFullDay.setChecked(false);
		}
		if(widget.showNotGoing){
			checkNotGoing.setChecked(true);
		}else{
			checkNotGoing.setChecked(false);
		}
		if(widget.useCalendarColor){
			checkEventColors.setChecked(true);
		}else{
			checkEventColors.setChecked(false);
		}
		checkFullDay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
		       @Override
		       public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
		    	   widget.showFullDay = isChecked;
		    	   updatePreview();
		       }
		   });
		checkNotGoing.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
		       @Override
		       public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
		    	   widget.showNotGoing = isChecked;
		    	   updatePreview();
		       }
		   }); 
		checkEventColors.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
		       @Override
		       public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
		    	   widget.useCalendarColor = isChecked;
		    	   updatePreview();
		       }
		   }); 
		
		buttonSelect.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				new AppPicker().execute("1");
			}
		});
		buttonSelectOuter.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				new AppPicker().execute("2");
			}
		}); 
		stylePrev.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(position>0){
					position--;
					updatePreview();
				}
			}
		});
		styleNext.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(position<(Tools.clock_layouts.length-1)){
					position++;
					updatePreview();
				}
			}
		});
		
	}
	
	private void updatePreview(){
		Bitmap clockBackground = null;
		widget.style = position;
		if(position==0){
			stylePrev.setVisibility(View.GONE);
		}else{
			stylePrev.setVisibility(View.VISIBLE);
		}
		if(position==(Tools.clock_layouts.length-1)){
			styleNext.setVisibility(View.GONE);
		}else{
			styleNext.setVisibility(View.VISIBLE);
		}
		if(widget.style == 0){
			clockBackground = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.hand_dial), 150, 150, false);
			widget.setDimensions(Tools.clock_layouts_dimensions_small[0]);
		}else if(widget.style == 1){
			clockBackground = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.hand_nodigits_dial), 150, 150, false);
			widget.setDimensions(Tools.clock_layouts_dimensions_small[1]);
		}else if(widget.style == 2){
			clockBackground = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.kit_kat_hand_dial), 150, 150, false);
			widget.setDimensions(Tools.clock_layouts_dimensions_small[2]);
		}else if(widget.style == 3){
			clockBackground = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.hand_whitefull_dial), 150, 150, false);
			widget.setDimensions(Tools.clock_layouts_dimensions_small[3]);
		}
		viewOver.setImageBitmap(Widget.addStaticWidget(ActivityWidgetSettings.this, widget));
		viewUnder.setImageBitmap(clockBackground);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_color_managment, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if( id == R.id.action_done){
			new SaveWidgetData().execute("");
		}
		if(id == android.R.id.home){
			new SaveWidgetData().execute("");
		}
		return super.onOptionsItemSelected(item);
	}
	private class AppPicker extends AsyncTask<String, Void, String> {
		private AlertDialog.Builder builderSingle;
		private ProgressDialog progressSelect;
        @Override
        protected String doInBackground(final String... params) {
        	final PackageManager packageManager = ActivityWidgetSettings.this.getPackageManager();
            final AppArrayAdapter adapter = new AppArrayAdapter(ActivityWidgetSettings.this, packageManager);
    		builderSingle.setNegativeButton(
    				getResources().getString(R.string.cancel),
    				new DialogInterface.OnClickListener() {
    					@Override
    					public void onClick(DialogInterface dialog, int which) {
    						dialog.dismiss();
    					}
    				});
    		builderSingle.setAdapter(adapter, new DialogInterface.OnClickListener() {
    					String packageName = "";

    					@Override
    					public void onClick(DialogInterface dialog,final int which) {
    						String strName = adapter.getItem(which);
    						AlertDialog.Builder builderInner = new AlertDialog.Builder(ActivityWidgetSettings.this);
    						builderInner.setMessage("" + strName);
    						builderInner.setTitle(getResources().getString(R.string.tab_extras_select_one_title_selected));
    						ArrayList<ApplicationInfo> appInstalled = new ArrayList<ApplicationInfo>();
    						appInstalled = (ArrayList<ApplicationInfo>) packageManager.getInstalledApplications(PackageManager.GET_META_DATA);

    						for (ApplicationInfo app : appInstalled) {
    							String name = (String) packageManager.getApplicationLabel(app);
    							if ((name != null && name.length() > 0)
    									&& name.equals(strName)) {
    								packageName = app.packageName;
    							}
    						}
    						builderInner.setPositiveButton("Ok",
    								new DialogInterface.OnClickListener() {
    									@Override
    									public void onClick(DialogInterface dialog, int pos) {
    										if(Integer.parseInt(params[0])==2){
    											widget.actionSecond = packageName;
    										}else{
    											widget.action = packageName;
    										}
    										
    										WidgetListManager.updateWidget(widget.id, prefs, widget);
    										dialog.dismiss();
    									}
    								});
    						builderInner.show();
    					}
    				});
    		return "Executed";
        }
        @Override
        protected void onPostExecute(String result) {
        	if(builderSingle != null){
        		builderSingle.show();
        	}
        	try {
				progressSelect.dismiss();
			} catch (Exception e) {
			}
        	
        }
        @Override
        protected void onPreExecute() {
        	progressSelect = new ProgressDialog(ActivityWidgetSettings.this);
        	progressSelect.setTitle("Loading");
        	progressSelect.setMessage("Wait while loading...");
        	progressSelect.show();
        	builderSingle = new AlertDialog.Builder(ActivityWidgetSettings.this);
    		builderSingle.setIcon(R.drawable.ic_launcher);
    		builderSingle.setTitle(getResources().getString(R.string.tab_extras_select_one_title));
        }
    }
}
