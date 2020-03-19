/*
 * Copyright (C) 2015 HTC Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.htc.dotbreaker.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.htc.dotbreaker.R;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;

public class DotBreakerUtils {
    public static final String LOG_TAG = "DotBreaker";
    public static final String LOG_PREFIX = "[DotBreakerUtils] ";
    
    private static int mInnerFrameWidth = 0;
    private static int mInnerFrameHeight = 0;
    private static int mInnerFrameMarginTop = 0;
    private static int mInnerFrameMarginLeft = 0;
    
    public static int getDotViewInnerFrameWidth(Resources res) {
        if (res != null) {
            if (mInnerFrameWidth == 0) {
                mInnerFrameWidth = res.getDimensionPixelSize(R.dimen.inner_frame_width);
            }
        }
        return mInnerFrameWidth;
    }
    
    public static int getDotViewInnerFrameHeight(Resources res) {
        if (res != null) {
            if (mInnerFrameHeight == 0) {
                mInnerFrameHeight = res.getDimensionPixelSize(R.dimen.inner_frame_height);
            }
        }
        return mInnerFrameHeight;
    }
    
    public static int getDotViewInnerFrameMarginTop(Resources res) {
        if (res != null) {
            if (mInnerFrameMarginTop == 0) {
                mInnerFrameMarginTop = res.getDimensionPixelSize(R.dimen.inner_frame_margin_top);
            }
        }
        return mInnerFrameMarginTop;
    }
    
    public static int getDotViewInnerFrameMarginLeft(Resources res) {
        if (res != null) {
            if (mInnerFrameMarginLeft == 0) {
                mInnerFrameMarginLeft = res.getDimensionPixelSize(R.dimen.inner_frame_margin_left);
            }
        }
        return mInnerFrameMarginLeft;
    }
    
    /**
     * @param color the color(ARGB) which want to change the alpha value
     * @param factor set the opacity that range is from 0.0 to 1.0
     * @return newColor
     */
    public static int adjustColorAlpha(int color, float factor) {
        int newAlpha = Math.round(Color.alpha(color) * factor);
        return Color.argb(newAlpha, Color.red(color), Color.green(color), Color.blue(color));
    }
    
    public static ArrayList<int[][]> readPNGtoAnimation(Context context, boolean isInAssetFolder, String folderName,
            int frameSize, int rowSize, int colSize) throws IOException {
        Log.d(DotBreakerUtils.LOG_TAG, LOG_PREFIX + "readPNGtoAnimation start, frameSize = "
                + frameSize + ", rowSize = " + rowSize + ", colSize = " + colSize);
        if (context == null) {
            Log.w(DotBreakerUtils.LOG_TAG, LOG_PREFIX + "readPNGtoAnimation, mContext is null!!");
            return null;
        }
        if (TextUtils.isEmpty(folderName)) {
            Log.w(DotBreakerUtils.LOG_TAG, LOG_PREFIX + "readPNGtoAnimation, folderName is empty or null!!");
            return null;
        }
        if (frameSize <= 0 || rowSize <= 0 || colSize <= 0) {
            Log.w(DotBreakerUtils.LOG_TAG, LOG_PREFIX + "readPNGtoAnimation, wrong format of frameSize, rowSize, colSize!!");
            return null;
        }
        
        Log.d(DotBreakerUtils.LOG_TAG, LOG_PREFIX + "readPNGtoAnimation folderName = " + folderName);
        
        ArrayList<int[][]> imgDotMatrixList = new ArrayList<int[][]>();
        int[][] frameMatrix = null;
        int frameIdx = 0;

        
        String filePath = "";
        InputStream is = null;
        
        while (frameIdx < frameSize) {
            try {
                // 2: string length must be 2, 0: means to add '0' if lack
                filePath = folderName + File.separator + String.format(Locale.US, "%02d", frameIdx) + ".png"; 
                if (isInAssetFolder) {
                    is = context.getAssets().open(filePath);
                } else {
                    filePath = context.getFilesDir().getPath() + File.separator + filePath;
                    File file = new File(filePath);
                    is = new FileInputStream(file);
                }

                Bitmap bitmap = null;
                bitmap = BitmapFactory.decodeStream(is);
                
                if (bitmap != null) {
                    frameMatrix = new int[rowSize][colSize];
                    for (int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
                        for (int colIdx = 0; colIdx < colSize; colIdx++) {
                            frameMatrix[rowIdx][colIdx] = bitmap.getPixel(colIdx, rowIdx);
                        }
                    }
                    imgDotMatrixList.add(frameMatrix);
                }
                
                frameIdx++;
            } catch (Exception e) {
                Log.w(DotBreakerUtils.LOG_TAG, LOG_PREFIX + "readPNGtoAnimation, exception object: " + e);
                return null;
            } finally {
                if (is != null) {
                    is.close();
                }
            }
        }

        Log.d(DotBreakerUtils.LOG_TAG, LOG_PREFIX + "readPNGtoAnimation end");
        return imgDotMatrixList;
    }
    
    public static Bitmap getBitmapFromAsset(AssetManager assetMgr, String filePath) {
        if (assetMgr == null || TextUtils.isEmpty(filePath)) {
            Log.w(DotBreakerUtils.LOG_TAG, LOG_PREFIX + "getBitmapFromAsset, assetMgr is null or fileName is empty!!");
            return null;
        }

        InputStream istr = null;
        Bitmap bitmap = null;
        try {
            istr = assetMgr.open(filePath);
            bitmap = BitmapFactory.decodeStream(istr);
        } catch (IOException e) {
            Log.w(DotBreakerUtils.LOG_TAG, LOG_PREFIX + "getBitmapFromAsset, exception!!!");
            e.printStackTrace();
        } finally {
            if (istr != null) {
                try {
                    istr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if (bitmap == null) {
            Log.w(DotBreakerUtils.LOG_TAG, LOG_PREFIX + "getBitmapFromAsset, bitmap is null!!");
        }

        return bitmap;
    }
    
    public static boolean isPackageExisted(String targetPackage,Context context) {
        if (context == null || TextUtils.isEmpty(targetPackage)) {
            return false;
        }
        List<ApplicationInfo> packages;
        PackageManager packagemanager = context.getPackageManager();
        if (packagemanager != null) {
            packages = packagemanager.getInstalledApplications(0);
            for (ApplicationInfo packageInfo : packages) {
                if (TextUtils.equals(packageInfo.packageName, targetPackage)) {
                    return true;
                }
            }
        }
        return false;
    }
}
