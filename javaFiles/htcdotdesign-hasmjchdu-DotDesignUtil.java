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
package com.htc.dotdesign.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.htc.dotdesign.ColorArray;
import com.htc.dotdesign.DotDesignConstants;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

public class DotDesignUtil {
    private static final String LOG_PREFIX = "[DotDropUtil] ";
	private static int msIsFWVGA = -1; // 0: false, 1: true
	public static Bitmap lessResolution(String filePath, int width, int height) {
		int reqHeight = width;
		int reqWidth = height;
		BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
		// First decode with inJustDecodeBounds=true to check dimensions
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(filePath, options);

		// Calculate inSampleSize
		options.inSampleSize = calculateInSampleSize(options, reqWidth,
				reqHeight);

		// Decode bitmap with inSampleSize set
		options.inJustDecodeBounds = false;

		return BitmapFactory.decodeFile(filePath, options);
	}
	
	private static int calculateInSampleSize(BitmapFactory.Options options,
			int reqWidth, int reqHeight) {

		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {

			// Calculate ratios of height and width to requested height and
			// width
			final int heightRatio = Math.round((float) height
					/ (float) reqHeight);
			final int widthRatio = Math.round((float) width / (float) reqWidth);

			// Choose the smallest ratio as inSampleSize value, this will
			// guarantee
			// a final image with both dimensions larger than or equal to the
			// requested height and width.
			inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
		}
		return inSampleSize;
	}
	
	public static void getTempFileName(Context context) {
		if(context == null) {
			return;
		}
		String mTempFileName;
        deleteTempFiles(context);
        File f = new File(Environment.getExternalStorageDirectory(), ".dotmatrix");
        if (!f.exists()) {
            f.mkdir();
        }

        // Make sure the hidden folder is readable/writable
        if (!f.canWrite()) {
            f.setWritable(true);
        }
        if (!f.canRead()) {
            f.setReadable(true);
        }

        mTempFileName = "/.dotmatrix/image_tmp_" + System.currentTimeMillis() + ".jpg";
        Log.d(DotDesignConstants.LOG_TAG, "mTempFileName:" + mTempFileName);
    }
	
	private static void deleteTempFiles(Context context) {
        Log.d(DotDesignConstants.LOG_TAG, "deleteTempFiles()...");
        File f = new File(Environment.getExternalStorageDirectory(), ".dotmatrix");
        File[] fArray = f.listFiles();
        if (f.isDirectory() && (fArray != null)) {
            // for(File f1: f.listFiles()) {
            for (File f1 : fArray) {
                String filename = f1.getName();
                if ((filename != null) && (filename.contains("image_tmp_"))) {
                    Log.d(DotDesignConstants.LOG_TAG,"delete file:" + filename);
                    f1.delete();
                    Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                    intent.setData(Uri.fromFile(f1));
                    context.sendBroadcast(intent);
                }
            }
        }
    }
	
	public static void SaveImagetoInternal(Context context,Bitmap finalBitmap,String filename) {
		if(context == null || finalBitmap == null) {
			return;
		}
		FileOutputStream out = null;
        try {
            out = context.openFileOutput(filename, Context.MODE_PRIVATE);
            finalBitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
	}
	
	public static Bitmap ReadImgfromInternal(String filepath) {
	    BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        return BitmapFactory.decodeFile(filepath, options);
	}
	
	public static Bitmap readImgfromResource(Context context,int id) {
	    if(context == null) {
	        return null;
	    }
	    BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
	    return BitmapFactory.decodeResource(context.getResources(), id, options);
	}
	
	public static Bitmap ReadImgfromInternal(Context context,String filename) {
	    if(context == null) {
	        return null;
	    }
		Bitmap bitmap = null;
		FileInputStream fis = null;
		File orignfile = context.getFilesDir();
        String dirPath = orignfile.getAbsolutePath();
		try {
			BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.RGB_565;
			bitmap = BitmapFactory.decodeFile(dirPath + filename,options);
		} catch(Exception e) {
			e.printStackTrace();
		} finally{
			if(fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return bitmap;
	}
	
	public static void SaveImagetoExternal(Bitmap finalBitmap,Context context) {
		FileOutputStream out = null;
		String fname = DotDesignConstants.shareName;
		File file = new File(context.getExternalFilesDir(null), fname);
		try {
			out = new FileOutputStream(file);
			finalBitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public static String getFileName() {
		return System.currentTimeMillis() + "_";
	}
	
	public static byte[] converBitmapToByte(Bitmap bitmap) {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
		byte[] byteArray = stream.toByteArray();
		return byteArray;
	}
	
	public static Bitmap BytesToBimap(byte[] array) {
		if (array != null && array.length != 0) {
			return BitmapFactory.decodeByteArray(array, 0, array.length);
		} else {
			return null;
		}
	}
	
	public static ArrayList<File> getListFiles(File parentDir) {
		ArrayList<File> inFiles = new ArrayList<File>();
		File[] files = parentDir.listFiles();
		if (files == null) {
			return inFiles;
		}
		//sort file by date
		Arrays.sort(files, new Comparator<File>() {
			public int compare(File f1, File f2) {
				return Long.valueOf(f1.lastModified()).compareTo(
						f2.lastModified());
			}
		});
		for (File file : files) {
			if (file.isDirectory()) {
				// inFiles.addAll(getListFiles(file));
				// do nothing
			} else {
				if (file.getName().endsWith("thumb.png")) {
					inFiles.add(file);
				}
			}
		}
		return inFiles;
	}
	
	public static ColorArray getColorArray(String filename,Context context) {
		if(context == null) {
			return null;
		}
		FileInputStream fis = null;
		ColorArray colorarray = null;
		try {
			fis = context.openFileInput(filename);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        ObjectInputStream is = null;
		try {
			is = new ObjectInputStream(fis);
			if (is != null) {
				colorarray = (ColorArray) is.readObject();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
        return colorarray;
	}
	
	public static boolean isFWVGA(Context context) {
        if (context == null) {
            return false;
        }
        if (msIsFWVGA == -1) {
            msIsFWVGA = 0;
            WindowManager windowMgr = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            if (windowMgr != null) {
                Display display = windowMgr.getDefaultDisplay();
                Point size = new Point();
                display.getSize(size);
                int width = size.x;
                int height = size.y;

                // PS. 854 - 72(navigation bar height) = 782
                if (width == 782 && height == 480) {
                    msIsFWVGA = 1;
                } else if (width == 480 && height == 782) {
                    msIsFWVGA = 1;
                }
            } else {
 
            }
        }
        return (msIsFWVGA == 1);
    }
    
    public static void selectTemplateFromCamera(Activity activity) {
        Log.d(DotDesignConstants.LOG_TAG, LOG_PREFIX + "selectTemplateFromCamera");
        if (activity != null) {
            File f = new File(android.os.Environment.getExternalStorageDirectory(),
                    DotDesignConstants.TEMP_FILE_NAME);
            Intent cameraIntent = new Intent(
                    android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
            activity.startActivityForResult(cameraIntent,
                    DotDesignConstants.REQUEST_GET_PHOTO_FROM_CAMERA);
        }
    }
    
    public static void selectTemplateFromGallery(Activity activity) {
        Log.d(DotDesignConstants.LOG_TAG, LOG_PREFIX + "selectTemplateFromGallery");
        if (activity != null) {
            try {
                Intent intent_gallery = new Intent(Intent.ACTION_PICK);
                intent_gallery.setType("image/*");
                activity.startActivityForResult(intent_gallery,
                        DotDesignConstants.REQUEST_GET_PHOTO_FROM_GALLERY);
            } catch (ActivityNotFoundException e) {
                e.printStackTrace();
            } catch (SecurityException se) {
                se.printStackTrace();
            }
        }
    }
    
    public static Point getResolution(Activity activity) {
        Point size = null;
        if (activity != null) {
            Display display = activity.getWindowManager().getDefaultDisplay();
            size = new Point();
            display.getRealSize(size);
        }
        return size;
    }
    
    public static boolean IsPackageExist(Context context, String packagename) {
        if (context != null) {
            PackageManager mPackageManager = context.getPackageManager();
            List<PackageInfo> packageList = mPackageManager
                    .getInstalledPackages(PackageManager.GET_PERMISSIONS);
            for (PackageInfo packageInfo : packageList) {
                if (packageInfo != null) {
                    if (packageInfo.packageName.toLowerCase().contains(packagename)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public static Intent getCropImageIntent(int cropWidth, int cropHeight, String filePath) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        File tmpFile = new File(filePath);
        intent.setDataAndType(Uri.fromFile(tmpFile), "image/*");
        // intent.putExtra("data", data);
        intent.putExtra("srcFilePath", tmpFile.getAbsolutePath());
        intent.putExtra("sourceUri", Uri.fromFile(tmpFile));
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", cropWidth);
        intent.putExtra("aspectY", cropHeight);
        intent.putExtra("outputX", cropWidth); // aspect crop width. divide by 2
                                               // to make smaller crop frame
                                               // size
        intent.putExtra("outputY", cropHeight); // aspect crop height. divide by
                                                // 2 to make smaller crop frame
                                                // size

        intent.putExtra("noFaceDetection", true); // true: disable face
                                                  // detection
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("return-data", false);
        File tmpCropFile = new File(Environment.getExternalStorageDirectory(),
                DotDesignConstants.TEMP_FILE_NAME);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tmpCropFile));

        return intent;
    }

    public static Intent getCropImageIntentFromCam(Context context,int cropWidth, int cropHeight) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        File tmpFile = new File(Environment.getExternalStorageDirectory(), DotDesignConstants.TEMP_FILE_NAME);
        intent.setDataAndType(Uri.fromFile(tmpFile), "image/*");
        // intent.putExtra("data", data);

        intent.putExtra("srcFilePath", tmpFile.getAbsolutePath());
        intent.putExtra("sourceUri", Uri.fromFile(tmpFile));
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", cropWidth);
        intent.putExtra("aspectY", cropHeight);
        intent.putExtra("outputX", cropWidth); // aspect crop width. divide by 2
                                               // to make smaller crop frame
                                               // size
        intent.putExtra("outputY", cropHeight); // aspect crop height. divide by
                                                // 2 to make smaller crop frame
                                                // size

        intent.putExtra("noFaceDetection", true); // true: disable face
                                                  // detection
        intent.putExtra("outputFormat", Bitmap.CompressFormat.PNG.toString());
        intent.putExtra("return-data", false);
        File tmpCropFile = new File(Environment.getExternalStorageDirectory(),DotDesignConstants.TEMP_FILE_NAME);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tmpCropFile));

        return intent;
    }
    
    public static void getCropPhotoByGallery(Activity activity, Intent data) {
        if (data == null || activity == null) {
            return;
        }
        Uri selectedImage = data.getData();
        String[] filePathColumn = {
                MediaStore.Images.Media.DATA
        };
        Cursor cursor = null;
        try {
            cursor = activity.getContentResolver().query(
                    selectedImage, filePathColumn, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                int columnIndex = cursor
                        .getColumnIndex(filePathColumn[0]);
                String filePath = cursor.getString(columnIndex);
                Point resSize = DotDesignUtil.getResolution(activity);
                if (resSize != null && filePath != null) {
                    Intent stockUI_intent = DotDesignUtil.getCropImageIntent(
                            resSize.x, resSize.y, filePath);
                    activity.startActivityForResult(stockUI_intent,
                            DotDesignConstants.REQUEST_CROP_IMG_IN_GALLERY);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
                cursor = null;
            }
        }
    }
    
    public static void getCropPhotoByCamera(Activity activity, Intent data) {
        if (activity != null) {
            Point size = DotDesignUtil.getResolution(activity);
            Intent intent = DotDesignUtil.getCropImageIntentFromCam(activity, size.x, size.y);
            PackageManager pm = activity.getPackageManager();
            List<ResolveInfo> list = pm.queryIntentActivities(intent,
                    PackageManager.GET_ACTIVITIES);
            if (list.size() > 0) {
                try {
                    activity.startActivityForResult(intent,
                            DotDesignConstants.REQUEST_CROP_IMG_IN_GALLERY_FROM_CAMERA);
                } catch (ActivityNotFoundException e) {
                    e.printStackTrace();
                } catch (SecurityException se)
                {
                    se.printStackTrace();
                }
            } else {

            }
        }
    }
    
    public static Bitmap getBitmapFromAsset(AssetManager assetMgr, String filePath) {
        if (assetMgr == null || TextUtils.isEmpty(filePath)) {
            Log.w(DotDesignConstants.LOG_TAG, LOG_PREFIX + "getBitmapFromAsset, assetMgr is null or fileName is empty!!");
            return null;
        }

        InputStream istr = null;
        Bitmap bitmap = null;
        try {
            istr = assetMgr.open(filePath);
            bitmap = BitmapFactory.decodeStream(istr);
        } catch (IOException e) {
            Log.w(DotDesignConstants.LOG_TAG, LOG_PREFIX + "getBitmapFromAsset, exception!!!");
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
            Log.w(DotDesignConstants.LOG_TAG, LOG_PREFIX + "getBitmapFromAsset, bitmap is null!!");
        }

        return bitmap;
    }
}
