package com.sportsoutclass.outclassdl;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Sachinda on 1/22/2016.
 * This class connects the about page recycler view with its activities
 */
final class AboutPageRVAdapter extends RecyclerView.Adapter<AboutPageRVAdapter.aboutPageViewHolder> {

    private String[] t_Data_set;
    private String[] st_Data_set;
    private Context ctx;
    private static final String GooglePlayStorePackageNameOld = "com.google.market";
    private static final String GooglePlayStorePackageNameNew = "com.android.vending";

    AboutPageRVAdapter(Context context, String[] titleDataset, String[] subTitleDataset) {

        this.t_Data_set = titleDataset;
        this.st_Data_set = subTitleDataset;
        ctx = context;
    }


    class aboutPageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView about_Title;
        TextView about_subTitle;
        aboutPageViewHolder(View v) {
            super(v);
            ctx.getApplicationContext();
            v.setOnClickListener(this);
            about_Title = (TextView) v.findViewById(R.id.row_mainLine);
            about_subTitle = (TextView) v.findViewById(R.id.row_subLine);
        }

        @Override
        public void onClick(View v) {
            /*
             * If position 3, goes to email intent for user feedback
             * If position 4, goes to Play Store or Amazon App store for rating
             */
            int position = getAdapterPosition();
            if (position == 2) {
                Intent sendEmail = new Intent(Intent.ACTION_SEND);
                sendEmail.setType("message/rfc822");
                sendEmail.putExtra(Intent.EXTRA_EMAIL, new String[]{"dlcalculatorapp@gmail.com"});
                sendEmail.putExtra(Intent.EXTRA_SUBJECT, "Sending Feedback");
                sendEmail.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                try {
                    ctx.startActivity(sendEmail);
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(ctx, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
            } else if (position == 3) {
                boolean googlePlay = checkWhichStore();
                Log.v("googlePlay: ", String.valueOf(googlePlay));
                final String appPackageName = ctx.getPackageName(); // getPackageName() from Context or Activity object
                Uri uri = Uri.parse((googlePlay ? "market://details?id=" : "amzn://apps/android?p=") + appPackageName);
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                //Checking if there are activities that can handle going to market
                //required for crash safety
                PackageManager packageManager = ctx.getPackageManager();
                List activities = packageManager.queryIntentActivities(goToMarket,
                        PackageManager.MATCH_DEFAULT_ONLY);
                boolean isIntentSafe = activities.size() > 0;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                            Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                            Intent.FLAG_ACTIVITY_MULTIPLE_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                }else{
                    goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                            Intent.FLAG_ACTIVITY_MULTIPLE_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                }
                if(isIntentSafe){
                    try {
                        ctx.startActivity(goToMarket);
                    } catch (android.content.ActivityNotFoundException a) {
                        try {
                            ctx.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse((googlePlay ? "https://play.google.com/store/apps/details?id=" : "http://www.amazon.com/gp/mas/dl/android?p=") + appPackageName)));
                        } catch (ActivityNotFoundException e2) {
                            Toast.makeText(ctx, "You don't have any app that can open this link", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

            } else if(position == 4){
                TeamSelection teamSelect = (TeamSelection) v.getContext();
                Fragment licences = new LicencesFrag();
                teamSelect.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, licences, "licences").addToBackStack(null).commit();
            }

        }

        /**
         * Checks if Google play is installed or not in the device
         *
         * @return true if installed
         */
        boolean checkWhichStore() {
            PackageManager packageManager = ctx.getPackageManager();
            boolean googlePlayStoreInstalled = false;
            List<PackageInfo> packages = packageManager.getInstalledPackages(0);
            for (PackageInfo packageInfo : packages) {
                if (packageInfo.packageName.equals(GooglePlayStorePackageNameOld) ||
                        packageInfo.packageName.equals(GooglePlayStorePackageNameNew)) {
                    googlePlayStoreInstalled = true;
                    break;
                }
            }
            return googlePlayStoreInstalled;
        }
    }

    @Override
    public AboutPageRVAdapter.aboutPageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row1, parent, false);
        return new AboutPageRVAdapter.aboutPageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(aboutPageViewHolder holder, int position) {
        holder.about_Title.setText(t_Data_set[position]);
        holder.about_subTitle.setText(st_Data_set[position]);
    }

    @Override
    public int getItemCount() {
        return t_Data_set.length;
    }



}
