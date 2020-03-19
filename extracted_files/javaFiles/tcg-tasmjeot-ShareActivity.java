package ec.orangephi.tcg;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookSdk;
import com.facebook.share.Sharer;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnItemClickListener;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.tweetcomposer.TweetComposer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import ec.orangephi.tcg.adapters.ShareOptionAdapter;
import ec.orangephi.tcg.models.ShareOption;
import io.fabric.sdk.android.Fabric;

/**
 * Created by gesuwall on 11/24/15.
 */
public class ShareActivity extends AppCompatActivity {
    protected CallbackManager callbackManager;
    protected ShareDialog shareDialog;
    private AsyncTask<Bitmap, Void, Uri> pendingTask;
    private static String tempIMG = "/temporalImage.png";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);
        // this part is optional
        //shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() { ... });

        TwitterAuthConfig authConfig =  new TwitterAuthConfig("consumerKey", "consumerSecret");
        Fabric.with(this, new TwitterCore(authConfig), new TweetComposer());
    }


    /**
     * Comparte un bitmap con Facebook.
     * @param image Bitmap a compartir
     */
    protected void sharePhotoFB(Bitmap image){
        if(shareDialog.canShow(SharePhotoContent.class)) {
            SharePhoto photo = new SharePhoto.Builder()
                    .setBitmap(image)
                    .build();
            SharePhotoContent content = new SharePhotoContent.Builder()
                    .addPhoto(photo)
                    .build();
            shareDialog.show(this, content);
        } else {
            Log.d("ShareActivity", "Cant share photo");
            Toast.makeText(this, getResources().getText(R.string.installFB), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Consulta todas las aplicaciones instaladas en el dispositivo para saber si hay apps oficiales
     * con las cuales se puede compartir mejor las fotos.
     * @return Una lista de enteros que representan a los elementos del enum ShareOptions para
     * saber que apps estan instaladas.
     */
    protected ArrayList<Integer> getAvailableShareApps(){
        final Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        boolean hasFB = false, hasTW = false;
        ArrayList<Integer> list = new ArrayList<>();
        PackageManager pm = getPackageManager();
        List<ApplicationInfo> availableApps = pm.getInstalledApplications(PackageManager.GET_META_DATA);
        if (availableApps != null) {
            for (ApplicationInfo targetApp : availableApps) {
                String packageName = targetApp.packageName;
                Log.d("Share activity", "package " + packageName);
                if (packageName.equals("com.facebook.katana")) {
                    hasFB = true;
                } else if(packageName.equals("com.twitter.android")) {
                    hasTW = true;
                }
            }
        }

        if(hasFB)
            list.add(ShareOption.Facebook.ordinal());
        if(hasTW)
            list.add(ShareOption.Twitter.ordinal());
        list.add(ShareOption.Other.ordinal());
        return list;
    }

    protected void showShareDialog(final ArrayList<Integer> options, final Bitmap shareBMP,
                                   final String caption){
        ShareOptionAdapter adapter = new ShareOptionAdapter(options);
        DialogPlus dialog = DialogPlus.newDialog(this)
                .setAdapter(adapter)
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(DialogPlus dialog, Object item, View view, int position) {
                        dialog.dismiss();
                        switch (ShareOption.values()[options.get(position-1)]){
                            case Facebook:
                                sharePhotoFB(shareBMP);
                                break;
                            case Twitter:
                                sharePhotoTW(shareBMP, caption);
                                break;
                            case Other:
                                sharePhotoOther(shareBMP, caption);
                        }
                    }
                })
                .setGravity(Gravity.CENTER)
                .setCancelable(true)
                .setHeader(R.layout.share_header)
                .create();
        dialog.show();
    }
    /**
     * Comprime un bitmap en background y lo comparte enviando un Intent a cualquier app instalada
     * que pueda recibir una imagen
     * @param image El bitmap a compartir
     * @param caption text opcional a compartir con la imagen
     */
    protected void sharePhotoOther(Bitmap image, final String caption){
        pendingTask = new AsyncTask<Bitmap, Void, Uri>() {
            @Override
            protected Uri doInBackground(Bitmap... params) {
                return compressBitmapToUri(params[0]);
            }

            @Override
            protected void onPostExecute(Uri uri) {
                pendingTask = null;
                if(uri != null) {
                    Intent share = new Intent(Intent.ACTION_SEND);
                    share.setType("image/jpeg");
                    share.putExtra(Intent.EXTRA_STREAM, uri);
                    share.putExtra(Intent.EXTRA_TEXT, caption);
                    startActivity(Intent.createChooser(share, getResources().getString(R.string.header_title)));
                }
            }
        };
        pendingTask.execute(image);

    }
    /**
     * Comprime un bitmap en background y lo comparte con Twitter.
     * @param bmp El bitmap a compartir
     * @param caption text opcional a compartir con la imagen
     */
    protected void sharePhotoTW(Bitmap bmp, final String caption){
        /*
        Uri uri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                "://" + getResources().getResourcePackageName(drawable)
                + '/' + getResources().getResourceTypeName(drawable) + '/' + getResources().getResourceEntryName(drawable));

        Log.d("ShareActivity", uri.toString());*/
        pendingTask = new AsyncTask<Bitmap, Void, Uri>() {
            @Override
            protected Uri doInBackground(Bitmap... params) {
                return compressBitmapToUri(params[0]);
            }

            @Override
            protected void onPostExecute(Uri uri) {
                pendingTask = null;
                if(uri != null) {
                    Log.d("ShareActivity", uri.toString());
                    TweetComposer.Builder builder = new TweetComposer.Builder(ShareActivity.this)
                            .text(caption)
                            .image(uri);
                    builder.show();
                }
            }
        };
        pendingTask.execute(bmp);

    }

    /**
     * Comprime un bitmap a un archivo temporar y devuelve el URI del archivo. Esta operacion
     * es muy pesada por lo cual debe de llamarse en un background thread.
     * @param bmp Bitmap a comprimir y compartir
     * @return Uri del Bitmap en el archivo temporal listo para ser compartido.
     */
    private Uri compressBitmapToUri(Bitmap bmp){
        try {
            OutputStream out = new FileOutputStream(Environment.getExternalStorageDirectory() + tempIMG);
            bmp.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();
            return Uri.parse(Environment.getExternalStorageDirectory() + tempIMG);
        } catch (IOException ex){
            ex.printStackTrace();
            return null;
        }
}
    @Override
    protected void onStop() {
        if(pendingTask != null)
            pendingTask.cancel(true);
        super.onStop();
    }
}
