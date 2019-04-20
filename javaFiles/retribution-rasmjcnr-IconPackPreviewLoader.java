/*
 * Copyright (c) 2015. Nathan A. Behary
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.nbehary.retribution;

import android.appwidget.AppWidgetProviderInfo;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;


class IconPackPreviewLoader {
    static final String TAG = "IconPackPreviewLoader";

    private int mPreviewBitmapWidth;
    private int mPreviewBitmapHeight;
    private String mSize;
    private final Context mContext;
    private final PackageManager mPackageManager;
    private PagedViewCellLayout mIconPackSpacingLayout;

    // Used for drawing shortcut previews
    private final BitmapCache mCachedShortcutPreviewBitmap = new BitmapCache();
    private final PaintCache mCachedShortcutPreviewPaint = new PaintCache();
    private final CanvasCache mCachedShortcutPreviewCanvas = new CanvasCache();

    // Used for drawing widget previews
    private final CanvasCache mCachedAppIconPackPreviewCanvas = new CanvasCache();
    private RectCache mCachedAppIconPackPreviewSrcRect = new RectCache();
    private RectCache mCachedAppIconPackPreviewDestRect = new RectCache();
    private PaintCache mCachedAppIconPackPreviewPaint = new PaintCache();
    private String mCachedSelectQuery;
    private final BitmapFactoryOptionsCache mCachedBitmapFactoryOptions = new BitmapFactoryOptionsCache();

    private final int mAppIconSize;
    private final IconCache mIconCache;

    private final float sPreviewIconPaddingPercentage = 0.25f;

    private final CacheDb mDb;

    private final HashMap<String, WeakReference<Bitmap>> mLoadedPreviews;
    private final ArrayList<SoftReference<Bitmap>> mUnusedBitmaps;
    private static final HashSet<String> sInvalidPackages;

    static {
        sInvalidPackages = new HashSet<String>();
    }

    public IconPackPreviewLoader(Context context) {
        LauncherAppState app = LauncherAppState.getInstance();
        DeviceProfile grid = app.getDynamicGrid().getDeviceProfile();

        mContext = context;
        mPackageManager = mContext.getPackageManager();
        mAppIconSize = grid.iconSizePx;
        mIconCache = app.getIconCache();
        mDb = app.getIconPackPreviewCacheDb();
        mLoadedPreviews = new HashMap<String, WeakReference<Bitmap>>();
        mUnusedBitmaps = new ArrayList<SoftReference<Bitmap>>();
    }

    public void setPreviewSize(int previewWidth, int previewHeight,
            PagedViewCellLayout widgetSpacingLayout) {
        mPreviewBitmapWidth = previewWidth;
        mPreviewBitmapHeight = previewHeight;
        mSize = previewWidth + "x" + previewHeight;
        mIconPackSpacingLayout = widgetSpacingLayout;
    }

    public Bitmap getPreview(final Object o) {
        final String name = getObjectName(o);
        final String packageName = getObjectPackage(o);
        // check if the package is valid
        boolean packageValid = true;
        synchronized(sInvalidPackages) {
            packageValid = !sInvalidPackages.contains(packageName);
        }
        if (!packageValid) {
            return null;
        }
        if (packageValid) {
            synchronized(mLoadedPreviews) {
                // check if it exists in our existing cache
                if (mLoadedPreviews.containsKey(name) && mLoadedPreviews.get(name).get() != null) {
                    return mLoadedPreviews.get(name).get();
                }
            }
        }

        Bitmap unusedBitmap = null;
        synchronized(mUnusedBitmaps) {
            // not in cache; we need to load it from the db
            while ((unusedBitmap == null || !unusedBitmap.isMutable() ||
                    unusedBitmap.getWidth() != mPreviewBitmapWidth ||
                    unusedBitmap.getHeight() != mPreviewBitmapHeight)
                    && mUnusedBitmaps.size() > 0) {
                unusedBitmap = mUnusedBitmaps.remove(0).get();
            }
            if (unusedBitmap != null) {
                final Canvas c = mCachedAppIconPackPreviewCanvas.get();
                c.setBitmap(unusedBitmap);
                c.drawColor(0, PorterDuff.Mode.CLEAR);
                c.setBitmap(null);
            }
        }

        if (unusedBitmap == null) {
            unusedBitmap = Bitmap.createBitmap(mPreviewBitmapWidth, mPreviewBitmapHeight,
                    Bitmap.Config.ARGB_8888);
        }

        Bitmap preview = null;

        if (packageValid) {
            preview = readFromDb(name, unusedBitmap);
        }

        if (preview != null) {
            synchronized(mLoadedPreviews) {
                mLoadedPreviews.put(name, new WeakReference<Bitmap>(preview));
            }
            return preview;
        } else {
            // it's not in the db... we need to generate it
            final Bitmap generatedPreview = generatePreview(o, unusedBitmap);
            preview = generatedPreview;
            if (preview != unusedBitmap) {
                throw new RuntimeException("generatePreview is not recycling the bitmap " + o);
            }

            synchronized(mLoadedPreviews) {
                mLoadedPreviews.put(name, new WeakReference<Bitmap>(preview));
            }

            // write to db on a thread pool... this can be done lazily and improves the performance
            // of the first time widget previews are loaded
            new AsyncTask<Void, Void, Void>() {
                public Void doInBackground(Void ... args) {
                    writeToDb(o, generatedPreview);
                    return null;
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Void) null);

            return preview;
        }
    }

    public void recycleBitmap(Object o, Bitmap bitmapToRecycle) {
        String name = getObjectName(o);
        synchronized (mLoadedPreviews) {
            if (mLoadedPreviews.containsKey(name)) {
                Bitmap b = mLoadedPreviews.get(name).get();
                if (b == bitmapToRecycle) {
                    mLoadedPreviews.remove(name);
                    if (bitmapToRecycle.isMutable()) {
                        synchronized (mUnusedBitmaps) {
                            mUnusedBitmaps.add(new SoftReference<Bitmap>(b));
                        }
                    }
                } else {
                    throw new RuntimeException("Bitmap passed in doesn't match up");
                }
            }
        }
    }

    static class CacheDb extends SQLiteOpenHelper {
        final static int DB_VERSION = 2;
        final static String DB_NAME = "widgetpreviews.db";
        final static String TABLE_NAME = "shortcut_and_widget_previews";
        final static String COLUMN_NAME = "name";
        final static String COLUMN_SIZE = "size";
        final static String COLUMN_PREVIEW_BITMAP = "preview_bitmap";
        final Context mContext;

        public CacheDb(Context context) {
            super(context, new File(context.getCacheDir(), DB_NAME).getPath(), null, DB_VERSION);
            // Store the context for later use
            mContext = context;
        }

        @Override
        public void onCreate(SQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                    COLUMN_NAME + " TEXT NOT NULL, " +
                    COLUMN_SIZE + " TEXT NOT NULL, " +
                    COLUMN_PREVIEW_BITMAP + " BLOB NOT NULL, " +
                    "PRIMARY KEY (" + COLUMN_NAME + ", " + COLUMN_SIZE + ") " +
                    ");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            if (oldVersion != newVersion) {
                // Delete all the records; they'll be repopulated as this is a cache
                db.execSQL("DELETE FROM " + TABLE_NAME);
            }
        }
    }

    private static final String WIDGET_PREFIX = "Widget:";
    private static final String SHORTCUT_PREFIX = "Shortcut:";
    private static final String ICONPACK_PREFIX = "Icon Pack:";

    private static String getObjectName(Object o) {
        // should cache the string builder
        StringBuilder sb = new StringBuilder();
        String output;
        if (o instanceof AppWidgetProviderInfo) {
            sb.append(WIDGET_PREFIX);
            sb.append(((AppWidgetProviderInfo) o).provider.flattenToString());
            output = sb.toString();
            sb.setLength(0);
        } else if (o instanceof ResolveInfo){
            sb.append(SHORTCUT_PREFIX);

            ResolveInfo info = (ResolveInfo) o;
            sb.append(new ComponentName(info.activityInfo.packageName,
                    info.activityInfo.name).flattenToString());
            output = sb.toString();
            sb.setLength(0);
        } else {
            sb.append(ICONPACK_PREFIX);
            String info = (String) o;
            sb.append(info);
            output = sb.toString();
            sb.setLength(0);
        }
        return output;
    }

    private String getObjectPackage(Object o) {
        if (o instanceof AppWidgetProviderInfo) {
            return ((AppWidgetProviderInfo) o).provider.getPackageName();
        } else if (o instanceof ResolveInfo){
            ResolveInfo info = (ResolveInfo) o;
            return info.activityInfo.packageName;
        } else {
            return (String) o;
        }
    }

    private void writeToDb(Object o, Bitmap preview) {
        String name = getObjectName(o);
        SQLiteDatabase db = mDb.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(CacheDb.COLUMN_NAME, name);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        preview.compress(Bitmap.CompressFormat.PNG, 100, stream);
        values.put(CacheDb.COLUMN_PREVIEW_BITMAP, stream.toByteArray());
        values.put(CacheDb.COLUMN_SIZE, mSize);
        db.insert(CacheDb.TABLE_NAME, null, values);
    }

    public static void removePackageFromDb(final CacheDb cacheDb, final String packageName) {
        synchronized(sInvalidPackages) {
            sInvalidPackages.add(packageName);
        }
        new AsyncTask<Void, Void, Void>() {
            public Void doInBackground(Void ... args) {
                SQLiteDatabase db = cacheDb.getWritableDatabase();
                db.delete(CacheDb.TABLE_NAME,
                        CacheDb.COLUMN_NAME + " LIKE ? OR " +
                        CacheDb.COLUMN_NAME + " LIKE ?", // SELECT query
                        new String[] {
                            WIDGET_PREFIX + packageName + "/%",
                            SHORTCUT_PREFIX + packageName + "/%"} // args to SELECT query
                            );
                synchronized(sInvalidPackages) {
                    sInvalidPackages.remove(packageName);
                }
                return null;
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Void) null);
    }

    private static void removeItemFromDb(final CacheDb cacheDb, final String objectName) {
        new AsyncTask<Void, Void, Void>() {
            public Void doInBackground(Void ... args) {
                SQLiteDatabase db = cacheDb.getWritableDatabase();
                db.delete(CacheDb.TABLE_NAME,
                        CacheDb.COLUMN_NAME + " = ? ", // SELECT query
                        new String[] { objectName }); // args to SELECT query
                return null;
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Void) null);
    }

    private Bitmap readFromDb(String name, Bitmap b) {
        if (mCachedSelectQuery == null) {
            mCachedSelectQuery = CacheDb.COLUMN_NAME + " = ? AND " +
                    CacheDb.COLUMN_SIZE + " = ?";
        }
        SQLiteDatabase db = mDb.getReadableDatabase();
        Cursor result = db.query(CacheDb.TABLE_NAME,
                new String[] { CacheDb.COLUMN_PREVIEW_BITMAP }, // cols to return
                mCachedSelectQuery, // select query
                new String[] { name, mSize }, // args to select query
                null,
                null,
                null,
                null);
        if (result.getCount() > 0) {
            result.moveToFirst();
            byte[] blob = result.getBlob(0);
            result.close();
            final BitmapFactory.Options opts = mCachedBitmapFactoryOptions.get();
            opts.inBitmap = b;
            opts.inSampleSize = 1;
            try {
                return BitmapFactory.decodeByteArray(blob, 0, blob.length, opts);
            } catch (IllegalArgumentException e) {
                removeItemFromDb(mDb, name);
                return null;
            }
        } else {
            result.close();
            return null;
        }
    }

    private Bitmap generatePreview(Object info, Bitmap preview) {
        if (preview != null &&
                (preview.getWidth() != mPreviewBitmapWidth ||
                        preview.getHeight() != mPreviewBitmapHeight)) {
            throw new RuntimeException("Improperly sized bitmap passed as argument");
        }
        return generateIconPackPreview(
                (String) info, mPreviewBitmapWidth, mPreviewBitmapHeight, preview);
     }






    private Bitmap generateIconPackPreview(
            String info, int maxWidth, int maxHeight, Bitmap preview) {
        Bitmap tempBitmap = mCachedShortcutPreviewBitmap.get();
        final Canvas c = mCachedShortcutPreviewCanvas.get();
        IconPackHelper iconPackHelper = new IconPackHelper(mContext);
        Drawable packIcon = mIconCache.getFullResIcon(info,0);
        Drawable icon = mIconCache.getFullResDefaultActivityIcon();
        PackageManager packageManager = mContext.getPackageManager();

        if (tempBitmap == null ||
                tempBitmap.getWidth() != maxWidth ||
                tempBitmap.getHeight() != maxHeight) {
            tempBitmap = Bitmap.createBitmap(maxWidth, maxHeight, Config.ARGB_8888);
            mCachedShortcutPreviewBitmap.set(tempBitmap);
        } else {
            c.setBitmap(tempBitmap);
            c.drawColor(0, PorterDuff.Mode.CLEAR);
            c.setBitmap(null);
        }

        // Don't! Render the icon.  Do other things.
        if (!info.equals("Default")) {
            iconPackHelper.loadIconPack(info);
        }




        int paddingTop = mContext.
                getResources().getDimensionPixelOffset(R.dimen.shortcut_preview_padding_top);
        int paddingLeft = mContext.
                getResources().getDimensionPixelOffset(R.dimen.shortcut_preview_padding_left);
        int paddingRight = mContext.
                getResources().getDimensionPixelOffset(R.dimen.shortcut_preview_padding_right);

        int scaledIconWidth = (maxWidth - paddingLeft - paddingRight);
        int iconRows = maxHeight/(mAppIconSize+25 );
        int iconCols = maxWidth/(mAppIconSize+25);



        int i = 0;
        int top = (preview.getHeight() - (mAppIconSize*iconRows + (25*(iconRows-1))  ))/2;
        int leftStart = (preview.getWidth() - (mAppIconSize*iconCols+(25*(iconCols-1))))/2;
        int left = leftStart;
        ArrayList<Integer> usedIds = new ArrayList<Integer>();
        if (!info.equals("Default")) {
            Map<ComponentName,String> ipr = iconPackHelper.mIconPackResources;
            for (Map.Entry<ComponentName, String> n : ipr.entrySet()) {

                int iconId;
                ComponentName test = n.getKey();
                iconId = iconPackHelper.getResourceIdForComponentNameIcon(test);
                if ((iconId==0) || usedIds.contains(iconId) )continue;
                usedIds.add(iconId);
                icon = mIconCache.getFullResIcon(iconPackHelper.getIconPackResources(),iconId);

                if ( (i!=0) && (i % iconCols)==0) {
                    top = top+mAppIconSize + 25;
                    left= leftStart;
                }
                renderDrawableToBitmap(
                        icon, preview,left,top,mAppIconSize,mAppIconSize);
                left = left + mAppIconSize +25;
                i++;
                if (i>((iconCols*iconRows)-1)) break;
            }
        } else {
            List<PackageInfo> apps = packageManager.getInstalledPackages(PackageManager.GET_META_DATA);
            apps =apps.subList(5,(apps.size()-1));  //do this to avoid the default icon in the first few "apps"
            for (PackageInfo a : apps) {
                icon = packageManager.getApplicationIcon(a.applicationInfo);
                if ( (i!=0) && (i % iconCols)==0) {
                    top = top+mAppIconSize + 25;
                    left= leftStart;
                }
                renderDrawableToBitmap(
                        icon, preview,left,top,mAppIconSize,mAppIconSize);
                left = left + mAppIconSize +25;
                i++;
                if (i>((iconCols*iconRows)-1)) break;
            }
        }

        renderDrawableToBitmap(
                packIcon, tempBitmap, paddingLeft, paddingTop, scaledIconWidth, scaledIconWidth);

        if (preview != null &&
                (preview.getWidth() != maxWidth || preview.getHeight() != maxHeight)) {
            throw new RuntimeException("Improperly sized bitmap passed as argument");
        } else if (preview == null) {
            preview = Bitmap.createBitmap(maxWidth, maxHeight, Config.ARGB_8888);
        }

        c.setBitmap(preview);
        // Draw a desaturated/scaled version of the icon in the background as a watermark
        Paint p = mCachedShortcutPreviewPaint.get();
        if (p == null) {
            p = new Paint();
            ColorMatrix colorMatrix = new ColorMatrix();
            colorMatrix.setSaturation(0);
            p.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
            p.setAlpha((int) (255 * 0.06f));
            mCachedShortcutPreviewPaint.set(p);
        }
        c.drawBitmap(tempBitmap, 0, 0, p);
        c.setBitmap(null);

//        renderDrawableToBitmap(packIcon, , 0, 0, mAppIconSize, mAppIconSize);

        return preview;
    }




    private static void renderDrawableToBitmap(
            Drawable d, Bitmap bitmap, int x, int y, int w, int h) {
        if (bitmap != null) {
            Canvas c = new Canvas(bitmap);
            c.scale(1f, 1f);
            Rect oldBounds = d.copyBounds();
            d.setBounds(x, y, x + w, y + h);
            d.draw(c);
            d.setBounds(oldBounds); // Restore the bounds
            c.setBitmap(null);
        }
    }

    private boolean isSystemPackage(PackageInfo pkgInfo) {
        return ((pkgInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0);
    }

}

