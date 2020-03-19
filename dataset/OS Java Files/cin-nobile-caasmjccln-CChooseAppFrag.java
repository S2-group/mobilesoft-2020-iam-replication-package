package com.cin.linyuehlii.nobile;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;


public class CChooseAppFrag extends Fragment {
    private List<Member_isi> memberList;
    private PackageManager packageManager = null;
    private List<ApplicationInfo> applist = null;
    private CApplicationAdapter listadaptor = null;
    private ViewPager vpMember;
    private FragmentManager manager = getFragmentManager();
    private ImageButton Fbt, Lbt;
    private ListView lvMember;
    private String t = "";
    private ArrayList<String> appChoosedList;
    ArrayList<Member> Charile;
    private int appID = 0;
    private Button button;
    byte[] CharileBytes = {};
    private static int save = -1;
    public View row;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = (LinearLayout) inflater.inflate(R.layout.a_fragment_applist, container, false);
        lvMember = (ListView) view.findViewById(R.id.applist);

        Fbt = (ImageButton) getActivity().findViewById(R.id.Fbt);
        Lbt = (ImageButton) getActivity().findViewById(R.id.Lbt);
        Fbt.setVisibility(ImageButton.VISIBLE);
        Fbt.setBackgroundResource(R.mipmap.bt_back);
        Lbt.setVisibility(ImageButton.VISIBLE);
        Lbt.setBackgroundResource(R.mipmap.bt_nextstep);
        button = (Button) getActivity().findViewById(R.id.button123);
        button.setVisibility(Button.GONE);
        appChoosedList = new ArrayList<>();
        Charile = new ArrayList<Member>();

        packageManager = getActivity().getPackageManager();

        new LoadApplications().execute();
        manager = getActivity().getSupportFragmentManager();


        /*************************從檔案讀取選好的APP**************************/

        FileInputStream fileIn = null;
        ObjectInputStream objectIn = null;


        try {

            fileIn = getActivity().openFileInput("t.tmp");

            objectIn = new ObjectInputStream(fileIn);
            Charile = (ArrayList<Member>) (objectIn.readObject());
            appID = Charile.get(Charile.size() - 1).getId() + 1;
            objectIn.close();

            Log.d("file", "fileIn");

        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
            Log.d("file", "fileIn FNFE");

        } catch (IOException ioe) {
            ioe.printStackTrace();
            Log.d("file", "fileIn IOE");

        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
            Log.d("file", "fileIn CNFE");
        }

        /*************************************從檔案讀取選好的APP********************************************/


        /**************item click 抓取到的applist 監聽點擊動作**************/
        lvMember.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ApplicationInfo app = applist.get(position);
                app.loadIcon(packageManager);  //取得AppICON
                app.loadLabel(packageManager); //取得APP名字
                Log.d("id", "pos = " + position);
                Log.d("id","id = " + id);

                String appName = String.valueOf(app.loadLabel(packageManager));
                String appPackageName = app.packageName;
                boolean isContain = false;

                for (int i = 0; i < Charile.size(); i++) {
                    Log.d("Charile", "\nCharile.get(" + i + ").getName = " + Charile.get(i).getName());
                    Log.d("Charile", "insert appName = " + appName);
                    if (!(Charile.get(i).getName().equals(appName)))
                        isContain = false;
                    else {
                        isContain = true;
                        break;
                    }
                }

                Log.d("isContain", "isContain = " + isContain);
                if (isContain)
                    Toast.makeText(getActivity(), "請不要重複點擊 " + appName + "> <", Toast.LENGTH_SHORT).show();
                else {
                    Member member = new Member(appID, 0, appName, "1 小時 00 分鐘", appPackageName);
                    //(appID, image, appName, time, packageName)
                    Toast.makeText(getActivity(), "您已新增  " + appName +
                            " ! 選取完所有APP後，請按「下一步」設定時間~", Toast.LENGTH_SHORT).show();
                    Charile.add(member);
                    appID++;

                }
                /************連結網站*************/
            }
        });

        Fbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), StartActivity.class);
                startActivity(intent);
                getActivity().finish();

            }
        });
        Lbt.setOnClickListener(new View.OnClickListener() {//選完app下一頁

            @Override
            public void onClick(View v) {
                FragmentManager manager;
                manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();


                /*************************************把選好APP的存成檔案*****************************************/
                //ByteArrayOutputStream bos = new ByteArrayOutputStream();
                //ObjectOutput out = null;
                //FileOutputStream fileOut = null;

                /*

                try {


                    FileOutputStream fileOut = getActivity().openFileOutput("t.tmp", Context.MODE_PRIVATE);
                    ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

                    //out = new ObjectOutputStream(bos);
                    objectOut.writeObject(Charile);
                    //CharileBytes = bos.toByteArray();
                    //fileOut.write(CharileBytes);
                    objectOut.close();
                    Log.d("Charile","存入成功");

                } catch (IOException ex) {
                    ex.printStackTrace();
                    Log.d("Charile","fileOut ioe");
                } /*finally {
                    try {
                        if (out != null) {
                            out.close();
                        }
                    } catch (IOException ex) {
                        // ignore close exception
                    }
                    try {
                        bos.close();
                    } catch (IOException ex) {
                        // ignore close exception
                    }
                }*/

                /*************************************把選好APP的存成檔案*****************************************/

                /****************傳送資料*******************/
                Fragment f2 = new CSetAppTimeFrag();
                transaction.replace(R.id.frag, f2);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("appChoosedList", Charile);
                f2.setArguments(bundle);
                transaction.commit();


            }
        });

        return view;

    }

    /***********
     * 其他方法
     ***************/
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        boolean result = true;

        switch (item.getItemId()) {
            case R.id.menu_about: {
                displayAboutDialog();

                break;
            }
            default: {
                result = super.onOptionsItemSelected(item);

                break;
            }
        }

        return result;
    }


    private void displayAboutDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //builder.setTitle(getString(R.string.about_title));
        //  builder.setMessage(getString(R.string.about_desc));


        builder.setPositiveButton("Know More", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://javatechig.com"));
                startActivity(browserIntent);
                dialog.cancel();
            }
        });
        builder.setNegativeButton("No Thanks!", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });

        builder.show();
    }


    private List<ApplicationInfo> checkForLaunchIntent(List<ApplicationInfo> list) {
        ArrayList<ApplicationInfo> applist = new ArrayList<ApplicationInfo>();
        for (ApplicationInfo info : list) {
            try {
                if (null != packageManager.getLaunchIntentForPackage(info.packageName)) {
                    applist.add(info);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return applist;
    }


    private class LoadApplications extends AsyncTask<Void, Void, Void> {
        private ProgressDialog progress = null;

        @Override
        protected Void doInBackground(Void... params) {
            applist = checkForLaunchIntent(packageManager.getInstalledApplications(PackageManager.GET_META_DATA));
            listadaptor = new CApplicationAdapter(getActivity(),
                                                  R.layout.c_appitem, applist);

            return null;
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected void onPostExecute(Void result) {
            lvMember.setAdapter(listadaptor);
            progress.dismiss();
            super.onPostExecute(result);
        }

        @Override
        protected void onPreExecute() {
            progress = ProgressDialog.show(getActivity(), null,
                                           "Loading application info...");
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }

}