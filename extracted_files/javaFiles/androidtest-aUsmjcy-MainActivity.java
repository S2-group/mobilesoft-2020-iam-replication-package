package com.y;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.y.module.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zf on 2015/5/14.
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        log(UsefulCode.getScreenPhysicalSize(this));
        log(UsefulCode.isTablet(MainActivity.this));

        this.findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UsefulCode.startApkActivity(MainActivity.this, "com.dgcy.lty.android.sdfc");
            }
        });

        log(UsefulCode.GetTextWidth("ab世界12", 12));

        for (String s : UsefulCode.getActivities(MainActivity.this)) {
            log(s);
        }

        log(UsefulCode.checkChinese("a你好"));
        log(UsefulCode.checkChinese("abc"));

        log(UsefulCode.checkNickname("a你好++*"));

        final ImageView iv = (ImageView) findViewById(R.id.iv);


        this.findViewById(R.id.change
        ).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap lBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.a);
                UsefulCode.setImageBitmap(iv, lBitmap, MainActivity.this);
            }
        });

        for (ApplicationInfo app
                : UsefulCode.getInstalledApplications(MainActivity.this)) {
            log("package: " + app.packageName + ", sourceDir: " + app.sourceDir);
        }

        List<User> lUserList = new ArrayList<User>();
        for (int i = 0; i < 10; i++) {
            lUserList.add(new User(i + ""));
        }

        User[] lUsers = UsefulCode.toArray(User.class, (ArrayList<User>) lUserList);
        for (User lUser : lUsers) {
            log(lUser);
        }

        log(UsefulCode.getIp(MainActivity.this));

        this.findViewById(R.id.notify).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UsefulCode.sendNotification(MainActivity.this, "标题 " + System.currentTimeMillis(), "内容 " + System.currentTimeMillis(), null);
            }
        });


    }

    private void log(Object str) {
        Log.d("MainActivity", str.toString());
    }


}
