/*
 * Copyright (C) 2015 Ouadban Youssef(tafayor.dev@gmail.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */



package com.tafayor.selfcamerashot.taflib.helpers;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Build;


import com.tafayor.selfcamerashot.taflib.types.StrException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;


public class PackageHelper
{
    private static String TAG = "PackageHelper";

    public static String BIN_FOLDER = "bin";
    public static String TMP_FOLDER = "tmp";
    public static String FILES_FOLDER = "files";
    public static String COPIED_NATIVE_LIBS_FOLDER = "libs-";



    private static String[] sNativeLibs;





    //----------------------------------------------------------------------------------------------
    // getAppsByIntent
    //----------------------------------------------------------------------------------------------
    public static List<String> getAppsByIntent(Context ctx, String action)
    {
        final PackageManager packageManager = ctx.getPackageManager();
        final Intent intent = new Intent(action);
        List<ResolveInfo> list = packageManager.queryIntentActivities(intent,0);

        List<String> apps = new ArrayList<>();
        for(ResolveInfo info : list)
        {
            apps.add(info.activityInfo.packageName);
        }
        return apps;
    }



    //----------------------------------------------------------------------------------------------
    // getAppLabel
    //----------------------------------------------------------------------------------------------
    public static String getAppLabel(Context ctx, String pkg)
    {
        String label = "";
        final PackageManager pm = ctx.getPackageManager();
        ApplicationInfo ai;
        try
        {
            ai = pm.getApplicationInfo(pkg, 0);
            if(ai!=null) label = pm.getApplicationLabel(ai).toString();
        }
        catch (Exception e)
        {
            LogHelper.logx(e);
        }


        return label;
    }



    //----------------------------------------------------------------------------------------------
    // getAppLabel
    //----------------------------------------------------------------------------------------------
    public static String getAppLabel(Context ctx)
    {
        int stringId = ctx.getApplicationInfo().labelRes;
        return ctx.getString(stringId);

    }


    //----------------------------------------------------------------------------------------------
    // isUserApp
    //----------------------------------------------------------------------------------------------
    public static boolean isUserApp(Context ctx, String pkg)
    {
        List<String> userApps = getUserApps(ctx, false);
        return (userApps.contains(pkg));
    }


    //----------------------------------------------------------------------------------------------
    // getUserApps
    //----------------------------------------------------------------------------------------------
    public static List<String>  getUserApps(Context ctx, boolean includeLaunchers)
    {
        PackageManager pm = ctx.getPackageManager();
        List<PackageInfo> allApps = pm.getInstalledPackages(0);
        List<String> userApps = new ArrayList<>();
        List<String> launchers = null ;
        if(!includeLaunchers) launchers = getLauncherApps(ctx);

        try
        {
            for(PackageInfo pi : allApps)
            {
                ApplicationInfo ai = pm.getApplicationInfo(pi.packageName, 0);
                if ((ai.flags & ApplicationInfo.FLAG_SYSTEM) == 0)
                {
                    if(!includeLaunchers && launchers.contains(pi.packageName)) continue;
                    userApps.add(pi.packageName);
                }
            }
        }
        catch(Exception ex)
        {
            LogHelper.logx(ex);
        }

        return userApps;
    }


    //----------------------------------------------------------------------------------------------
    // getUserApps
    //----------------------------------------------------------------------------------------------
    public static List<String>  getSystemApps(Context ctx)
    {
        PackageManager pm = ctx.getPackageManager();
        List<PackageInfo> allApps = pm.getInstalledPackages(0);
        List<String> systemApps = new ArrayList<>();


        try
        {
            for(PackageInfo pi : allApps)
            {
                ApplicationInfo ai = pm.getApplicationInfo(pi.packageName, 0);
                if ((ai.flags & ApplicationInfo.FLAG_SYSTEM) != 0)
                {
                    systemApps.add(pi.packageName);
                }
            }
        }
        catch(Exception ex)
        {
            LogHelper.logx(ex);
        }

        return systemApps;
    }


    //----------------------------------------------------------------------------------------------
    // isLauncher
    //----------------------------------------------------------------------------------------------
    public static boolean isLauncher(Context ctx, String pkg)
    {
        List<String> launchers = PackageHelper.getLauncherApps(ctx);
        return (launchers.contains(pkg));
    }



    //----------------------------------------------------------------------------------------------
    // getLauncherPackages
    //----------------------------------------------------------------------------------------------
    public static List<String> getLauncherApps(Context ctx)
    {
        List<String> pkgList = new ArrayList<>();
        PackageManager pm = ctx.getPackageManager();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        List<ResolveInfo> infoList = pm.queryIntentActivities(intent, 0);
       if(!infoList.isEmpty())
       {
           for(ResolveInfo info : infoList)
           {
               pkgList.add(info.activityInfo.packageName);
           }
       }
        return pkgList;
    }



    //----------------------------------------------------------------------------------------------
    // isLauncher
    //----------------------------------------------------------------------------------------------
    public static boolean isLaunchable(Context ctx, String pkg)
    {
        Intent LaunchIntent = ctx.getPackageManager().getLaunchIntentForPackage(pkg);
       return (LaunchIntent!=null);
        //List<String> apps = PackageHelper.getLaunchableApps(ctx);
        //return (apps.contains(pkg));
    }


    //----------------------------------------------------------------------------------------------
    // getInstalledApps
    //----------------------------------------------------------------------------------------------
    public static List<String> getLaunchableApps(Context context)
    {
        List<ApplicationInfo> installedApps = context.getPackageManager().
                getInstalledApplications(PackageManager.PERMISSION_GRANTED);
        List<String> launchableInstalledApps = new ArrayList<String>();
        for(int i =0; i<installedApps.size(); i++)
        {
            if(context.getPackageManager().getLaunchIntentForPackage(installedApps.get(i).packageName) != null)
            {
                //If you're here, then this is a launch-able app
                launchableInstalledApps.add(installedApps.get(i).packageName);
            }
        }
        return launchableInstalledApps;
    }



    //----------------------------------------------------------------------------------------------
    // getInstalledApps
    //----------------------------------------------------------------------------------------------
    public static List<String>  getInstalledApps(Context ctx, boolean includeLaunchers)
    {
        PackageManager pm = ctx.getPackageManager();
        List<PackageInfo> allApps = pm.getInstalledPackages(0);
        List<String> apps = new ArrayList<>();
        List<String> launchers = null ;
        if(!includeLaunchers) launchers = getLauncherApps(ctx);

        try
        {
            for(PackageInfo pi : allApps)
            {
                if(!includeLaunchers && launchers.contains(pi.packageName)) continue;
                apps.add(pi.packageName);
            }
        }
        catch(Exception ex)
        {
            LogHelper.logx(ex);
        }

        return apps;
    }




    public static String getApkPath(Context ctx)
    {
        ApplicationInfo appInfo = ctx.getApplicationInfo();
        return appInfo.sourceDir;
    }


    public static void setNativeLibraries(String[] libs)
    {
        sNativeLibs = Arrays.copyOf(libs, libs.length);
    }



    public  static boolean unpackNativeLibraries(Context ctx)
    {

        boolean result = true;

        File apkFile = null;
        String desDir;
        File desDirFile = null;
        ZipFile zipFile = null;
        InputStream is = null;
        OutputStream os = null;


        String desDirname = PackageHelper.getCopiedNativeLibsDirname(ctx);
        int index = 1;
        if(desDirname != null)
        {

            desDirFile = new File(getCopiedNativeLibsParentDir(ctx) + desDirname);
            IOHelper.deletePathRecursive(desDirFile);

            index = Integer.parseInt("0" + desDirname.split("-")[1]);
            index++;

        }

        desDirname = COPIED_NATIVE_LIBS_FOLDER + index;
        desDir = getCopiedNativeLibsParentDir(ctx) + desDirname + "/";
        desDirFile = new File(desDir);



        ApplicationInfo appInfo = ctx.getApplicationInfo();
        apkFile = new File(appInfo.sourceDir);

        try
        {
            if(!desDirFile.mkdirs()) throw new Exception("Failed to create libs folder" );
            zipFile =  new ZipFile(apkFile, ZipFile.OPEN_READ);
            for(String lib : sNativeLibs)
            {
                String libFilename = System.mapLibraryName(lib);
                String libLocalPath = "lib/" + Build.CPU_ABI + "/" + libFilename;
                File libDesFile = new File(desDir  + libFilename);

                ZipEntry entry = zipFile.getEntry(libLocalPath);
                if(entry == null)
                {
                    zipFile.close();
                    throw new Exception("Zip entry not found : " + libLocalPath);

                }


                is = zipFile.getInputStream(entry);
                os = new FileOutputStream(libDesFile)  ;

                IOHelper.copyStream(is, os);

                libDesFile.setReadable(true);
                libDesFile.setWritable(true);
                libDesFile.setExecutable(true);

            }

        }
        catch(Exception e)
        {
            LogHelper.logx(TAG, "unpackNativeLibraries", e);
            IOHelper.deletePathRecursive(desDirFile);
            result = false;
        }
        finally
        {
            try{if(is != null) is.close();}catch (Exception e){}
            try{if(is != null) os.close();}catch (Exception e){}
            try{zipFile.close();}catch (Exception e){}

        }


        return result;
    }



    public static boolean areNativeLibsCopied(Context ctx)
    {
        String libsDir = getCopiedNativeLibsDir(ctx);
        if(libsDir == null) return false;
        for(String lib : sNativeLibs)
        {
            String libFilename = System.mapLibraryName(lib);
            File libFile = new File(libsDir + libFilename);
            if(!libFile.exists()) return false;
        }

        return true;
    }




    public static boolean isAppInstalledOnExternalStorage(Context ctx)
    {
        boolean isInstalled = false;

        PackageManager pm = ctx.getPackageManager();
        try
        {
            PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(), 0);
            ApplicationInfo ai = pi.applicationInfo;

            isInstalled = ((ai.flags & ApplicationInfo.FLAG_EXTERNAL_STORAGE) == ApplicationInfo.FLAG_EXTERNAL_STORAGE);

        } catch (Exception e)
        {
            LogHelper.logx(e);
        }

        return isInstalled;
    }




    public static boolean copyBinaryFromLibDir(Context ctx, String binaryName, String desDir)
    {
        return copyBinaryFromLibDir(ctx, binaryName, desDir, binaryName);
    }



    public static boolean copyBinaryFromLibDir(Context ctx, String binaryName, String desDir, String newBinaryName)
    {
        boolean result;
        boolean ret;
        String binaryTempName = System.mapLibraryName(binaryName);
        String srcPath = getLibDir(ctx)+ binaryTempName;
        String desPath = desDir + newBinaryName;

        LogHelper.log("copyBinaryFromLibDir");
        LogHelper.log("srcPath : " + srcPath);
        LogHelper.log("desPath : " + desPath);
        try
        {
            ret = IOHelper.copyFile(srcPath, desPath);
            if(!ret) throw new StrException("Failed to copy binary file from lib dir");
            result = true;
        }
        catch(StrException e)
        {
            LogHelper.logx(e);
            result = false;
        }


        return result;
    }




    public static  String getPackageName(Context ctx) { return ctx.getPackageName();}


   

   







    public static Drawable getAppIcon(Context ctx, String uri)
    {
        Drawable icon = null;
        try
        {
            icon = ctx.getPackageManager().getApplicationIcon(uri);
        }
        catch (PackageManager.NameNotFoundException e) {
            LogHelper.logx(e);
        }

        if(icon == null)
        {
            ctx.getResources().getDrawable(android.R.drawable.sym_def_app_icon);
        }

        return icon;
    }



    public static boolean isPackageInstalled(Context ctx, String uri)
    {
        PackageManager pm = ctx.getPackageManager();
        boolean app_installed = false;
        try
        {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES); //pm.getPackageInfo(targetPackage,PackageManager.GET_META_DATA)
            app_installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }


    public static void launchPackage(Context ctx, String uri)
    {
        Intent LaunchIntent = ctx.getPackageManager().getLaunchIntentForPackage("com.example.appName");
        ctx.startActivity(LaunchIntent);
    }

    public static boolean tmpDirExists(Context ctx)
    {
        File tmpDir = new File(getTmpDir(ctx));
        return tmpDir.exists();
    }

    public static boolean binDirExists(Context ctx)
    {
        File binDir = new File(getBinDir(ctx));
        return binDir.exists();
    }

    public static boolean filesDirExists(Context ctx)
    {
        File filesDir = new File(getFilesDir(ctx));
        return filesDir.exists();
    }


    public static String getFilesDir(Context ctx)
    {
        return  getDataDir(ctx) + FILES_FOLDER + "/";
    }

    public static String getTmpDir(Context ctx)
    {
        return  getFilesDir(ctx) + TMP_FOLDER + "/";
    }

    public static String getBinDir(Context ctx)
    {
        return  getFilesDir(ctx) + BIN_FOLDER + "/";
    }

    public static String getCopiedNativeLibsParentDir(Context ctx)
    {
        return getFilesDir(ctx);
    }


    public static String getCopiedNativeLibsDirname(Context ctx)
    {

        File dir = new File(getCopiedNativeLibsParentDir(ctx));
        File[] foundFiles = dir.listFiles(new FilenameFilter()
        {
            @Override
            public boolean accept(File file, String name)
            {
                return name.startsWith(COPIED_NATIVE_LIBS_FOLDER);
            }
        });

        if(foundFiles == null || foundFiles.length == 0) return null;

        return  foundFiles[0].getName();
    }


    public static  String getCopiedNativeLibsDir(Context ctx)
    {
        String dirname = getCopiedNativeLibsDirname(ctx) ;
        if(dirname == null) return null;
        return getCopiedNativeLibsParentDir(ctx) + dirname + "/";
    }


    public static String getDataDir(Context ctx)
    {
        String dir="";
        PackageManager m = ctx.getPackageManager();
        String packageName = ctx.getPackageName();

        try
        {
            PackageInfo packageInfo = m.getPackageInfo(packageName, 0);
            dir = packageInfo.applicationInfo.dataDir + "/";
        }
        catch (PackageManager.NameNotFoundException e)
        {
            LogHelper.log("Error Package name not found ," + e.getMessage());
        }

        return dir;
    }



    public static String getLibDir(Context ctx)
    {
        if(areNativeLibsCopied(ctx))
        {
            return getCopiedNativeLibsDir(ctx);
        }
        else
        {
            return getDefaultLibDir(ctx);
        }
    }



    public static String getDefaultLibDir(Context ctx)
    {
        String libDir="";
        PackageManager m = ctx.getPackageManager();
        String packageName = ctx.getPackageName();
        PackageInfo packageInfo ;

        try
        {
            packageInfo = m.getPackageInfo(packageName, 0);
            if(Build.VERSION.SDK_INT < Build.VERSION_CODES.GINGERBREAD)
            {
                libDir = packageInfo.applicationInfo.dataDir + "/lib";
            }
            else
            {
                ApplicationInfo appInfo = ctx.getApplicationInfo();
                libDir = appInfo.nativeLibraryDir;
            }

            libDir = libDir  + "/" ;

        }

        catch (Exception e)
        {
            LogHelper.log("getLibDir :" + e.getMessage());
        }

        return libDir;
    }







    public static boolean copyFileFromAsset(Context ctx, String srcFileName, String desPath)
    {
        AssetManager assetManager = ctx.getAssets();

        InputStream in = null;
        OutputStream out = null;
        File outFile;
        String path="";
        boolean result;

        try
        {
            in = assetManager.open(srcFileName);
            outFile = new File(desPath, srcFileName);
            if(outFile.exists()) throw new StrException("Destination exists already");
            out = new FileOutputStream(outFile);

            copyStream(in, out);

            path = outFile.getAbsolutePath();
            result = true;
        }
        catch(Exception e)
        {
            LogHelper.log(TAG, "copyFileFromAsset", e.getMessage());
            result = false;
        }
        finally
        {
            try
            {
                in.close();
                in = null;
                out.flush();
                out.close();
                out = null;
            }catch(Exception e){}
        }


        return result;
    }


    private static void copyStream(InputStream in, OutputStream out) throws IOException
    {
        byte[] buffer = new byte[1024];
        int read;
        while((read = in.read(buffer)) != -1)
        {
            out.write(buffer, 0, read);
        }
    }


    public static void copyBFileFromAsset(Context ctx, String srcFileName, String desPath)
    {
        AssetManager assetManager = ctx.getAssets();


        InputStream in = null;
        OutputStream out = null;
        File outFile;

        try
        {
            in = assetManager.open(srcFileName);
            outFile = new File(desPath, srcFileName);
            out = new FileOutputStream(outFile);

            copyStream(in, out);
            in.close();
            in = null;
            out.flush();
            out.close();
            out = null;
        }
        catch(IOException e)
        {
            LogHelper.log("Failed to copy asset file: " + srcFileName + " , " + e.getMessage());
        }
    }















}

