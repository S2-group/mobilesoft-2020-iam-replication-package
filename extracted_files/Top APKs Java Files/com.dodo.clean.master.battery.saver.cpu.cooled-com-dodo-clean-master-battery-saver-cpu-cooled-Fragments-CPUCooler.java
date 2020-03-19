package com.dodo.clean.master.battery.saver.cpu.cooled.Fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemAnimator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.dodo.clean.master.battery.saver.cpu.cooled.Application;
import com.dodo.clean.master.battery.saver.cpu.cooled.Classes.Apps;
import com.dodo.clean.master.battery.saver.cpu.cooled.Cpu_Scanner;
import com.dodo.clean.master.battery.saver.cpu.cooled.MainActivity;
import com.dodo.clean.master.battery.saver.cpu.cooled.RecyclerAdapter;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

public class CPUCooler
  extends Fragment
{
  public static List<Apps> apps;
  List<Apps> apps2;
  BroadcastReceiver batteryReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      try
      {
        paramAnonymousIntent.getIntExtra("level", 0);
        CPUCooler.this.temp = (paramAnonymousIntent.getIntExtra("temperature", 0) / 10.0F);
        paramAnonymousIntent = CPUCooler.this.batterytemp;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(CPUCooler.this.temp);
        localStringBuilder.append("°C");
        paramAnonymousIntent.setText(localStringBuilder.toString());
        if (CPUCooler.this.temp >= 30.0D)
        {
          CPUCooler.apps = new ArrayList();
          CPUCooler.this.apps2 = new ArrayList();
          CPUCooler.this.tempimg.setImageResource(2131165366);
          CPUCooler.this.showmain.setText(paramAnonymousContext.getResources().getString(2131558454));
          CPUCooler.this.showmain.setTextColor(Color.parseColor("#F22938"));
          CPUCooler.this.showsec.setText(paramAnonymousContext.getResources().getString(2131558404));
          CPUCooler.this.nooverheating.setText("");
          CPUCooler.this.coolbutton.setImageResource(2131165317);
          CPUCooler.this.coolbutton.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymous2View)
            {
              paramAnonymous2View = new Intent(CPUCooler.this.getActivity(), Cpu_Scanner.class);
              CPUCooler.this.startActivity(paramAnonymous2View);
              new Handler().postDelayed(new Runnable()
              {
                public void run()
                {
                  CPUCooler.this.nooverheating.setText(Application.getInstance().getResources().getString(2131558423));
                  CPUCooler.this.showmain.setText(Application.getInstance().getResources().getString(2131558452));
                  CPUCooler.this.showmain.setTextColor(CPUCooler.this.getResources().getColor(2131034152));
                  CPUCooler.this.showsec.setText(Application.getInstance().getResources().getString(2131558410));
                  CPUCooler.this.showsec.setTextColor(CPUCooler.this.getResources().getColor(2131034152));
                  CPUCooler.this.coolbutton.setImageResource(2131165319);
                  CPUCooler.this.tempimg.setImageResource(2131165282);
                  CPUCooler.this.batterytemp.setText("25.3°C");
                  CPUCooler.this.recyclerView.setAdapter(null);
                }
              }, 2000L);
              CPUCooler.this.coolbutton.setOnClickListener(new View.OnClickListener()
              {
                public void onClick(View paramAnonymous3View)
                {
                  paramAnonymous3View = CPUCooler.this.getLayoutInflater(CPUCooler.this.getArguments()).inflate(2131361841, null);
                  Object localObject = (ImageView)paramAnonymous3View.findViewById(2131230883);
                  ((TextView)paramAnonymous3View.findViewById(2131231020)).setText(Application.getInstance().getResources().getString(2131558409));
                  localObject = new Toast(CPUCooler.this.getActivity());
                  ((Toast)localObject).setGravity(16, 0, 70);
                  ((Toast)localObject).setDuration(1);
                  ((Toast)localObject).setView(paramAnonymous3View);
                  ((Toast)localObject).show();
                }
              });
            }
          });
          if (Build.VERSION.SDK_INT < 23)
          {
            CPUCooler.this.showsec.setTextAppearance(paramAnonymousContext, 16973892);
            CPUCooler.this.showsec.setTextColor(Color.parseColor("#F22938"));
          }
          else
          {
            CPUCooler.this.showsec.setTextAppearance(16973894);
            CPUCooler.this.showsec.setTextColor(Color.parseColor("#F22938"));
          }
          CPUCooler.this.recyclerView.setItemAnimator(new SlideInLeftAnimator());
          CPUCooler.this.recyclerView.getItemAnimator().setAddDuration(10000L);
          CPUCooler.this.mAdapter = new RecyclerAdapter(CPUCooler.apps);
          paramAnonymousContext = new LinearLayoutManager(CPUCooler.this.getActivity().getApplicationContext(), 0, false);
          CPUCooler.this.recyclerView.setLayoutManager(paramAnonymousContext);
          CPUCooler.this.recyclerView.setItemAnimator(new SlideInUpAnimator(new OvershootInterpolator(1.0F)));
          CPUCooler.this.recyclerView.computeHorizontalScrollExtent();
          CPUCooler.this.recyclerView.setAdapter(CPUCooler.this.mAdapter);
          CPUCooler.this.getAllICONS();
        }
        return;
      }
      catch (Exception paramAnonymousContext) {}
    }
  };
  TextView batterytemp;
  int check = 0;
  ImageView coolbutton;
  RecyclerAdapter mAdapter;
  TextView nooverheating;
  RecyclerView recyclerView;
  TextView showmain;
  TextView showsec;
  float temp;
  ImageView tempimg;
  View view;
  
  public CPUCooler() {}
  
  public void getAllICONS()
  {
    PackageManager localPackageManager = getActivity().getPackageManager();
    List localList = localPackageManager.getInstalledApplications(128);
    if (localList != null)
    {
      int i = 0;
      while (i < localList.size())
      {
        String str = ((ApplicationInfo)localList.get(i)).packageName;
        Object localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("");
        ((StringBuilder)localObject1).append(str);
        Log.e("packageName-->", ((StringBuilder)localObject1).toString());
        if (!str.equals(Application.getInstance().getResources().getString(2131558540))) {
          try
          {
            localObject1 = (String)localPackageManager.getApplicationLabel(localPackageManager.getApplicationInfo(str, 128));
            localObject1 = new Apps();
            long l = new File(localPackageManager.getApplicationInfo(str, 128).publicSourceDir).length();
            localObject2 = new StringBuilder();
            ((StringBuilder)localObject2).append(l / 1000000L);
            ((StringBuilder)localObject2).append("");
            Log.e("SIZE", ((StringBuilder)localObject2).toString());
            localObject2 = new StringBuilder();
            ((StringBuilder)localObject2).append(l / 1000000L + 20L);
            ((StringBuilder)localObject2).append("MB");
            ((Apps)localObject1).setSize(((StringBuilder)localObject2).toString());
            localObject2 = localPackageManager.getApplicationInfo(str, 128);
            Drawable localDrawable = getActivity().getPackageManager().getApplicationIcon(((ApplicationInfo)localList.get(i)).packageName);
            ((Apps)localObject1).setImage(localDrawable);
            getActivity().getPackageManager();
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append("");
            localStringBuilder.append(localDrawable);
            Log.e("ico-->", localStringBuilder.toString());
            if ((((ApplicationInfo)localObject2).flags & 0x1) == 0) {
              if (this.check <= 5)
              {
                this.check += 1;
                apps.add(localObject1);
              }
              else
              {
                getActivity().unregisterReceiver(this.batteryReceiver);
                break;
              }
            }
            this.mAdapter.notifyDataSetChanged();
          }
          catch (PackageManager.NameNotFoundException localNameNotFoundException)
          {
            Object localObject2 = new StringBuilder();
            ((StringBuilder)localObject2).append("Unable to find icon for package '");
            ((StringBuilder)localObject2).append(str);
            ((StringBuilder)localObject2).append("': ");
            ((StringBuilder)localObject2).append(localNameNotFoundException.getMessage());
            Log.e("ERROR", ((StringBuilder)localObject2).toString());
          }
        }
        i += 1;
      }
    }
    if (apps.size() > 1)
    {
      this.mAdapter = new RecyclerAdapter(apps);
      this.mAdapter.notifyDataSetChanged();
    }
  }
  
  public boolean getUserVisibleHint()
  {
    MainActivity.name.setText(Application.getInstance().getResources().getString(2131558408));
    return getUserVisibleHint();
  }
  
  @Nullable
  public View onCreateView(LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, @Nullable Bundle paramBundle)
  {
    this.view = paramLayoutInflater.inflate(2131361822, paramViewGroup, false);
    try
    {
      getActivity().registerReceiver(this.batteryReceiver, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
      this.recyclerView = ((RecyclerView)this.view.findViewById(2131230944));
      this.tempimg = ((ImageView)this.view.findViewById(2131231014));
      this.showmain = ((TextView)this.view.findViewById(2131230987));
      this.showsec = ((TextView)this.view.findViewById(2131230988));
      this.coolbutton = ((ImageView)this.view.findViewById(2131230823));
      this.nooverheating = ((TextView)this.view.findViewById(2131230919));
      this.showmain.setText(Application.getInstance().getResources().getString(2131558452));
      this.showsec.setText(Application.getInstance().getResources().getString(2131558410));
      this.nooverheating.setText(Application.getInstance().getResources().getString(2131558423));
      this.coolbutton.setImageResource(2131165319);
      this.coolbutton.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          paramAnonymousView = CPUCooler.this;
          paramAnonymousView = paramAnonymousView.getLayoutInflater(paramAnonymousView.getArguments()).inflate(2131361841, null);
          Object localObject = (ImageView)paramAnonymousView.findViewById(2131230883);
          ((TextView)paramAnonymousView.findViewById(2131231020)).setText(Application.getInstance().getResources().getString(2131558409));
          localObject = new Toast(CPUCooler.this.getActivity());
          ((Toast)localObject).setGravity(16, 0, 70);
          ((Toast)localObject).setDuration(1);
          ((Toast)localObject).setView(paramAnonymousView);
          ((Toast)localObject).show();
        }
      });
      this.tempimg.setImageResource(2131165282);
      this.batterytemp = ((TextView)this.view.findViewById(2131230795));
      paramLayoutInflater = new StringBuilder();
      paramLayoutInflater.append(this.temp);
      paramLayoutInflater.append("");
      Log.e("Temperrature", paramLayoutInflater.toString());
      return this.view;
    }
    catch (Exception paramLayoutInflater)
    {
      for (;;) {}
    }
  }
  
  public void onDestroy()
  {
    super.onDestroy();
    try
    {
      getActivity().unregisterReceiver(this.batteryReceiver);
      return;
    }
    catch (Exception localException) {}
  }
  
  public void setUserVisibleHint(boolean paramBoolean)
  {
    super.setUserVisibleHint(paramBoolean);
    if (paramBoolean) {
      MainActivity.name.setText(MainActivity.context.getResources().getString(2131558408));
    }
  }
}
