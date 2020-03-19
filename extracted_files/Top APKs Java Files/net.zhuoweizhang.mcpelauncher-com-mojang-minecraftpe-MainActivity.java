package com.mojang.minecraftpe;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.NativeActivity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.os.Vibrator;
import android.provider.MediaStore.Images.Media;
import android.speech.tts.TextToSpeech;
import android.text.ClipboardManager;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.SparseArray;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;
import com.joshuahuelsman.patchtool.PTPatch;
import dalvik.system.BaseDexClassLoader;
import dalvik.system.DexFile;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;
import net.zhuoweizhang.mcpelauncher.AddonManager;
import net.zhuoweizhang.mcpelauncher.AddonOverrideTexturePack;
import net.zhuoweizhang.mcpelauncher.MaraudersMap;
import net.zhuoweizhang.mcpelauncher.MinecraftVersion;
import net.zhuoweizhang.mcpelauncher.PatchManager;
import net.zhuoweizhang.mcpelauncher.R.id;
import net.zhuoweizhang.mcpelauncher.R.integer;
import net.zhuoweizhang.mcpelauncher.R.layout;
import net.zhuoweizhang.mcpelauncher.R.string;
import net.zhuoweizhang.mcpelauncher.RealmsRedirectInfo;
import net.zhuoweizhang.mcpelauncher.RedirectPackageManager;
import net.zhuoweizhang.mcpelauncher.ScriptManager;
import net.zhuoweizhang.mcpelauncher.ScriptTextureDownloader;
import net.zhuoweizhang.mcpelauncher.TexturePack;
import net.zhuoweizhang.mcpelauncher.Utils;
import net.zhuoweizhang.mcpelauncher.ZipTexturePack;
import net.zhuoweizhang.mcpelauncher.api.modpe.ControllerManager;
import net.zhuoweizhang.mcpelauncher.patch.PatchUtils;
import net.zhuoweizhang.mcpelauncher.texture.AtlasProvider;
import net.zhuoweizhang.mcpelauncher.texture.ClientBlocksJsonProvider;
import net.zhuoweizhang.mcpelauncher.texture.TextureListProvider;
import net.zhuoweizhang.mcpelauncher.texture.TexturePackLoader;
import net.zhuoweizhang.mcpelauncher.ui.GetSubstrateActivity;
import net.zhuoweizhang.mcpelauncher.ui.HoverCar;
import net.zhuoweizhang.mcpelauncher.ui.MainMenuOptionsActivity;
import net.zhuoweizhang.mcpelauncher.ui.ManageScriptsActivity;
import net.zhuoweizhang.mcpelauncher.ui.NerdyStuffActivity;
import net.zhuoweizhang.mcpelauncher.ui.NoMinecraftActivity;
import net.zhuoweizhang.mcpelauncher.ui.TexturePacksActivity;
import org.mozilla.javascript.RhinoException;

@SuppressLint({"SdCardPath"})
public class MainActivity
  extends NativeActivity
{
  public static final int DIALOG_COPY_WORLD = 4;
  public static final int DIALOG_CRASH_SAFE_MODE = 4096;
  public static final int DIALOG_CREATE_WORLD = 1;
  public static final int DIALOG_FIRST_LAUNCH = 4099;
  public static final int DIALOG_INSERT_TEXT = 4103;
  public static final int DIALOG_INVALID_PATCHES = 4098;
  public static final int DIALOG_MULTIPLAYER_DISABLE_SCRIPTS = 4104;
  public static final int DIALOG_NOT_SUPPORTED = 4101;
  public static final int DIALOG_RUNTIME_OPTIONS = 4097;
  public static final int DIALOG_RUNTIME_OPTIONS_WITH_INSERT_TEXT = 4105;
  public static final int DIALOG_SELINUX_BROKE_EVERYTHING = 4106;
  public static final int DIALOG_SETTINGS = 3;
  public static final int DIALOG_UPDATE_TEXTURE_PACK = 4102;
  public static final int DIALOG_VERSION_MISMATCH_SAFE_MODE = 4100;
  public static final String[] GAME_MODES = { "creative", "survival" };
  public static final String HALF_SUPPORT_VERSION = "1.0";
  public static final String HEY_CAN_YOU_STOP_STEALING_BLOCKLAUNCHER_CODE = "please?";
  public static final int INPUT_STATUS_CANCELLED = 0;
  public static final int INPUT_STATUS_IN_PROGRESS = -1;
  public static final int INPUT_STATUS_OK = 1;
  private static final int MAX_FAILS = 2;
  private static String MC_NATIVE_LIBRARY_DIR = "/data/data/com.mojang.minecraftpe/lib/";
  private static String MC_NATIVE_LIBRARY_LOCATION = "/data/data/com.mojang.minecraftpe/lib/libminecraftpe.so";
  public static final String MOJANG_ACCOUNT_LOGIN_URL = "https://account.mojang.com/m/login?app=mcpe";
  public static final String PT_PATCHES_DIR = "ptpatches";
  private static final int REQUEST_MANAGE_SCRIPTS = 417;
  private static final int REQUEST_MANAGE_TEXTURES = 416;
  private static final int REQUEST_PICK_IMAGE = 415;
  public static final String SCRIPT_SUPPORT_VERSION = "0.16";
  public static final String TAG = "BlockLauncher/Main";
  public static WeakReference<MainActivity> currentMainActivity;
  public static List<String> failedPatches = new ArrayList();
  public static boolean hasPrePatched = false;
  public static boolean libLoaded = false;
  public static Set<String> loadedAddons;
  public static ByteBuffer minecraftLibBuffer;
  public static boolean tempSafeMode = false;
  public AddonOverrideTexturePack addonOverrideTexturePackInstance = null;
  private int commandHistoryIndex = 0;
  private List<String> commandHistoryList = new ArrayList();
  private View commandHistoryView;
  private PopupWindow commandHistoryWindow;
  private boolean controllerInit = false;
  protected DisplayMetrics displayMetrics;
  protected boolean fakePackage = false;
  public boolean forceFallback = false;
  protected boolean hasRecorder = false;
  private boolean hasResetSafeModeCounter = false;
  private boolean hiddenTextDismissAfterOneLine = false;
  private TextView hiddenTextView;
  private PopupWindow hiddenTextWindow;
  private HoverCar hoverCar = null;
  protected int inputStatus = -1;
  protected boolean isRecording = false;
  private Toast lastToast = null;
  private Dialog loginDialog;
  private WebView loginWebView;
  private final Handler mainHandler = new Handler()
  {
    public void dispatchMessage(Message paramAnonymousMessage)
    {
      MainActivity.this.toggleRecording();
    }
  };
  public ApplicationInfo mcAppInfo;
  private PackageInfo mcPkgInfo;
  private int mcpeArch = 0;
  protected Context minecraftApkContext;
  public boolean minecraftApkForwardLocked = false;
  private Typeface minecraftTypeface = null;
  protected MinecraftVersion minecraftVersion;
  private Button nextButton;
  private boolean overlyZealousSELinuxSafeMode = false;
  private long pickImageCallbackAddress = 0L;
  private Intent pickImageResult;
  private Button prevButton;
  public String refreshToken = "";
  private SparseArray<HurlRunner> requestMap = new SparseArray();
  public boolean requiresGuiBlocksPatch = false;
  public String session = "";
  public List<TexturePack> textureOverrides = new ArrayList();
  protected TexturePack texturePack;
  private boolean textureVerbose = false;
  private TextToSpeech tts;
  protected String[] userInputStrings = null;
  
  static
  {
    currentMainActivity = null;
    loadedAddons = new HashSet();
  }
  
  public MainActivity() {}
  
  private void addLibraryDirToPath(String paramString)
  {
    try
    {
      Object localObject2 = getClassLoader();
      Object localObject1 = Utils.getDeclaredFieldRecursive(localObject2.getClass(), "pathList");
      ((Field)localObject1).setAccessible(true);
      localObject1 = ((Field)localObject1).get(localObject2);
      Object localObject3 = localObject1.getClass();
      Object localObject4 = Utils.getDeclaredFieldRecursive((Class)localObject3, "nativeLibraryDirectories");
      ((Field)localObject4).setAccessible(true);
      Object localObject5 = ((Field)localObject4).get(localObject1);
      if ((localObject5 instanceof File[]))
      {
        localObject5 = (File[])localObject5;
        File[] arrayOfFile = addToFileList((File[])localObject5, new File(paramString));
        if (localObject5 != arrayOfFile) {
          ((Field)localObject4).set(localObject1, arrayOfFile);
        }
      }
      localObject3 = Utils.getDeclaredFieldRecursive((Class)localObject3, "nativeLibraryPathElements");
      if ((localObject3 != null) && ((localObject2 instanceof BaseDexClassLoader)) && (((BaseDexClassLoader)localObject2).findLibrary("minecraftpe") == null))
      {
        ((Field)localObject3).setAccessible(true);
        localObject2 = (Object[])((Field)localObject3).get(localObject1);
        localObject4 = localObject2.getClass().getComponentType().getConstructor(new Class[] { File.class, Boolean.TYPE, File.class, DexFile.class });
        ((Constructor)localObject4).setAccessible(true);
        paramString = ((Constructor)localObject4).newInstance(new Object[] { new File(paramString), Boolean.valueOf(true), null, null });
        localObject2 = Arrays.copyOf((Object[])localObject2, localObject2.length + 1);
        localObject2[(localObject2.length - 1)] = paramString;
        System.out.println(localObject2);
        ((Field)localObject3).set(localObject1, localObject2);
      }
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  private File[] addToFileList(File[] paramArrayOfFile, File paramFile)
  {
    int j = paramArrayOfFile.length;
    int i = 0;
    while (i < j)
    {
      if (paramArrayOfFile[i].equals(paramFile)) {
        return paramArrayOfFile;
      }
      i += 1;
    }
    File[] arrayOfFile = new File[paramArrayOfFile.length + 1];
    System.arraycopy(paramArrayOfFile, 0, arrayOfFile, 1, paramArrayOfFile.length);
    arrayOfFile[0] = paramFile;
    return arrayOfFile;
  }
  
  private boolean checkAddonArch(File paramFile)
  {
    try
    {
      int i = Utils.getElfArch(paramFile);
      int j = this.mcpeArch;
      return i == j;
    }
    catch (IOException paramFile) {}
    return true;
  }
  
  private void checkArch()
  {
    try
    {
      this.mcpeArch = Utils.getElfArch(new File(MC_NATIVE_LIBRARY_LOCATION));
      int i = Utils.getElfArch(new File(getApplicationInfo().nativeLibraryDir + "/libmcpelauncher.so"));
      if (this.mcpeArch != i)
      {
        Intent localIntent = new Intent(this, NoMinecraftActivity.class);
        localIntent.putExtra("message", getResources().getString(R.string.minecraft_wrong_arch).toString().replaceAll("ARCH", Utils.getArchName(i)));
        localIntent.putExtra("learnmore_uri", "https://github.com/zhuowei/MCPELauncher/issues/495");
        startActivity(localIntent);
        finish();
        try
        {
          Thread.sleep(1000L);
          System.exit(0);
          return;
        }
        catch (Exception localException)
        {
          for (;;)
          {
            localException.printStackTrace();
          }
        }
      }
      return;
    }
    catch (IOException localIOException) {}
  }
  
  private void checkForSubstrate()
  {
    if (!Build.CPU_ABI.equals("x86")) {
      return;
    }
    Object localObject1 = null;
    try
    {
      localObject2 = getPackageManager().getPackageInfo("com.saurik.substrate", 0);
      localObject1 = localObject2;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      Object localObject2;
      File localFile;
      for (;;) {}
    }
    if (localObject1 == null)
    {
      finish();
      startActivity(new Intent(this, GetSubstrateActivity.class));
      try
      {
        Thread.sleep(100L);
        Process.killProcess(Process.myPid());
        return;
      }
      catch (Throwable localThrowable)
      {
        return;
      }
    }
    localObject2 = getFileStreamPath("libmcpelauncher_tinysubstrate.so");
    if (!((File)localObject2).exists()) {
      localFile = new File(localThrowable.applicationInfo.nativeLibraryDir, "libsubstrate.so");
    }
    try
    {
      PatchUtils.copy(localFile, (File)localObject2);
      System.load(((File)localObject2).getAbsolutePath());
      return;
    }
    catch (IOException localIOException)
    {
      throw new RuntimeException(localIOException);
    }
  }
  
  private void clearRuntimeOptionsDialog()
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        MainActivity.this.removeDialog(4097);
        MainActivity.this.removeDialog(4105);
      }
    });
  }
  
  private File copyContentStoreToTempFile(Uri paramUri)
  {
    File localFile;
    FileOutputStream localFileOutputStream;
    try
    {
      localFile = new File(getExternalFilesDir(null), "skintemp.png");
      localFile.getParentFile().mkdirs();
      paramUri = getContentResolver().openInputStream(paramUri);
      localFileOutputStream = new FileOutputStream(localFile);
      byte[] arrayOfByte = new byte['က'];
      for (;;)
      {
        int i = paramUri.read(arrayOfByte);
        if (i == -1) {
          break;
        }
        localFileOutputStream.write(arrayOfByte, 0, i);
      }
      paramUri.close();
    }
    catch (IOException paramUri)
    {
      paramUri.printStackTrace();
      return new File("/sdcard/totally/fake");
    }
    localFileOutputStream.close();
    return localFile;
  }
  
  private void disableAllPatches()
  {
    PatchManager.getPatchManager(this).disableAllPatches();
  }
  
  private void disableTransparentSystemBar()
  {
    if (Build.VERSION.SDK_INT < 21) {
      return;
    }
    getWindow().clearFlags(Integer.MIN_VALUE);
  }
  
  private void enableSoftMenuKey()
  {
    if (Build.VERSION.SDK_INT >= 19) {}
    for (int i = 1073741824;; i = 134217728)
    {
      getWindow().addFlags(i);
      return;
    }
  }
  
  public static long findMinecraftLibLength()
    throws Exception
  {
    return new File(MC_NATIVE_LIBRARY_LOCATION).length();
  }
  
  private void fixMyEpicFail()
  {
    SharedPreferences localSharedPreferences = Utils.getPrefs(1);
    int k = localSharedPreferences.getInt("last_bl_version", 0);
    int i = 0;
    try
    {
      int j = getPackageManager().getPackageInfo(getPackageName(), 0).versionCode;
      i = j;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;) {}
    }
    if (k < 69) {
      Utils.getPrefs(0).edit().putBoolean("zz_load_native_addons", true).apply();
    }
    if (k != i) {
      localSharedPreferences.edit().putInt("last_bl_version", i).apply();
    }
  }
  
  private InputStream getRegularInputStream(String paramString)
  {
    try
    {
      paramString = new BufferedInputStream(new FileInputStream(new File(paramString)));
      return paramString;
    }
    catch (IOException paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }
  
  private void initAtlasMeta()
  {
    if (getMCPEVersion().startsWith("1.0")) {
      initAtlasMetaNew();
    }
    while (Utils.isSafeMode()) {
      return;
    }
    try
    {
      AtlasProvider localAtlasProvider1 = new AtlasProvider("resourcepacks/vanilla/client/textures/terrain_texture.json", "images/terrain-atlas/", "block.bl_modpkg.");
      AtlasProvider localAtlasProvider2 = new AtlasProvider("resourcepacks/vanilla/client/textures/item_texture.json", "images/items-opaque/", "item.bl_modpkg.");
      localAtlasProvider1.initAtlas(this);
      localAtlasProvider2.initAtlas(this);
      TextureListProvider localTextureListProvider = new TextureListProvider("resourcepacks/vanilla/client/textures.list");
      localTextureListProvider.init(this);
      ClientBlocksJsonProvider localClientBlocksJsonProvider = new ClientBlocksJsonProvider("resourcepacks/vanilla/client/blocks.json");
      localClientBlocksJsonProvider.init(this);
      this.textureOverrides.add(0, localAtlasProvider1);
      this.textureOverrides.add(1, localAtlasProvider2);
      this.textureOverrides.add(2, localTextureListProvider);
      this.textureOverrides.add(3, localClientBlocksJsonProvider);
      ScriptManager.terrainMeta = localAtlasProvider1;
      ScriptManager.itemsMeta = localAtlasProvider2;
      ScriptManager.blocksJson = localClientBlocksJsonProvider;
      ScriptManager.textureList = localTextureListProvider;
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      reportError(localException);
    }
  }
  
  private void initAtlasMetaNew()
  {
    if (Utils.isSafeMode()) {
      return;
    }
    try
    {
      AtlasProvider localAtlasProvider1 = new AtlasProvider("resource_packs/vanilla/textures/terrain_texture.json", "images/terrain-atlas/", "block.bl_modpkg.");
      AtlasProvider localAtlasProvider2 = new AtlasProvider("resource_packs/vanilla/textures/item_texture.json", "images/items-opaque/", "item.bl_modpkg.");
      localAtlasProvider1.initAtlas(this);
      localAtlasProvider2.initAtlas(this);
      TextureListProvider localTextureListProvider = new TextureListProvider("resource_packs/vanilla/textures.list");
      localTextureListProvider.init(this);
      ClientBlocksJsonProvider localClientBlocksJsonProvider = new ClientBlocksJsonProvider("resource_packs/vanilla/blocks.json");
      localClientBlocksJsonProvider.init(this);
      this.textureOverrides.add(0, localAtlasProvider1);
      this.textureOverrides.add(1, localAtlasProvider2);
      this.textureOverrides.add(2, localClientBlocksJsonProvider);
      ScriptManager.terrainMeta = localAtlasProvider1;
      ScriptManager.itemsMeta = localAtlasProvider2;
      ScriptManager.blocksJson = localClientBlocksJsonProvider;
      ScriptManager.textureList = localTextureListProvider;
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      reportError(localException);
    }
  }
  
  private boolean isAddonCompat(String paramString)
  {
    if (paramString == null) {}
    do
    {
      do
      {
        for (;;)
        {
          return false;
          if (paramString.equals(this.mcPkgInfo.versionName)) {
            return true;
          }
          if (this.mcPkgInfo.versionName.startsWith("0.14"))
          {
            String str = this.mcPkgInfo.versionName.substring(this.mcPkgInfo.versionName.lastIndexOf(".") + 1);
            try
            {
              if (Integer.parseInt(str) < 2)
              {
                if (paramString.startsWith("0.14.0")) {
                  return true;
                }
                if (paramString.equals("0.14.1")) {
                  return true;
                }
              }
              else
              {
                if (paramString.equals("0.14.2")) {
                  return true;
                }
                boolean bool = paramString.equals("0.14.3");
                if (bool) {
                  return true;
                }
              }
            }
            catch (Exception paramString)
            {
              paramString.printStackTrace();
              return true;
            }
          }
        }
      } while (!this.mcPkgInfo.versionName.startsWith("0.15"));
      if (paramString.startsWith("0.15.0")) {
        return true;
      }
    } while (!paramString.startsWith("0.15.1"));
    return true;
  }
  
  private boolean isCommandHistoryEnabled()
  {
    return Utils.getPrefs(0).getBoolean("zz_command_history", true);
  }
  
  private boolean isForcingController()
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (Build.VERSION.SDK_INT >= 12)
    {
      bool1 = bool2;
      if (Utils.hasExtrasPackage(this))
      {
        bool1 = bool2;
        if (Utils.getPrefs(0).getBoolean("zz_use_controller", false)) {
          bool1 = true;
        }
      }
    }
    return bool1;
  }
  
  public static native void nativeOnPickImageCanceled(long paramLong);
  
  public static native void nativeOnPickImageSuccess(long paramLong, String paramString);
  
  private void navigateCommandHistory(int paramInt)
  {
    int i = this.commandHistoryIndex + paramInt;
    paramInt = i;
    if (i < 0) {
      paramInt = 0;
    }
    i = paramInt;
    if (paramInt >= this.commandHistoryList.size()) {
      i = this.commandHistoryList.size() - 1;
    }
    setCommandHistoryIndex(i);
    String str = (String)this.commandHistoryList.get(i);
    this.hiddenTextView.setText(str);
    Selection.setSelection((Spannable)this.hiddenTextView.getText(), str.length());
  }
  
  private void prePatch()
    throws Exception
  {
    File localFile1 = getDir("patched", 0);
    Object localObject2 = new File(this.mcAppInfo.nativeLibraryDir + "/libminecraftpe.so");
    File localFile2 = new File(localFile1, "libminecraftpe.so");
    boolean bool = Utils.getPrefs(1).getBoolean("force_prepatch", true);
    if ((!hasPrePatched) && (Utils.getEnabledPatches().size() == 0))
    {
      hasPrePatched = true;
      if (localFile2.exists()) {
        localFile2.delete();
      }
      if (bool) {
        Utils.getPrefs(1).edit().putBoolean("force_prepatch", false).putInt("prepatch_version", this.mcPkgInfo.versionCode).apply();
      }
      return;
    }
    byte[] arrayOfByte;
    Object localObject1;
    int i;
    int j;
    if ((!hasPrePatched) && ((!localFile2.exists()) || (bool)))
    {
      System.out.println("Forcing new prepatch");
      arrayOfByte = new byte[(int)((File)localObject2).length()];
      localObject1 = ByteBuffer.wrap(arrayOfByte);
      localObject2 = new FileInputStream((File)localObject2);
      ((InputStream)localObject2).read(arrayOfByte);
      ((InputStream)localObject2).close();
      i = 0;
      j = getMaxNumPatches();
      localObject2 = Utils.getEnabledPatches().iterator();
    }
    for (;;)
    {
      if (((Iterator)localObject2).hasNext())
      {
        localObject3 = (String)((Iterator)localObject2).next();
        if ((j < 0) || (i < j)) {}
      }
      else
      {
        if (this.requiresGuiBlocksPatch)
        {
          System.out.println("Patching guiblocks");
          localObject2 = new PTPatch();
          if (this.minecraftVersion.guiBlocksPatch != null)
          {
            ((PTPatch)localObject2).loadPatch(this.minecraftVersion.guiBlocksPatch);
            PatchUtils.patch((ByteBuffer)localObject1, (PTPatch)localObject2);
          }
        }
        localObject1 = new FileOutputStream(localFile2);
        ((OutputStream)localObject1).write(arrayOfByte);
        ((OutputStream)localObject1).close();
        hasPrePatched = true;
        Utils.getPrefs(1).edit().putBoolean("force_prepatch", false).putInt("prepatch_version", this.mcPkgInfo.versionCode).apply();
        if (failedPatches.size() > 0) {
          showDialog(4098);
        }
        MC_NATIVE_LIBRARY_DIR = localFile1.getCanonicalPath();
        MC_NATIVE_LIBRARY_LOCATION = localFile2.getCanonicalPath();
        return;
      }
      Object localObject3 = new File((String)localObject3);
      if (((File)localObject3).exists())
      {
        try
        {
          PTPatch localPTPatch = new PTPatch();
          localPTPatch.loadPatch((File)localObject3);
          if (localPTPatch.checkMagic()) {
            break label505;
          }
          failedPatches.add(((File)localObject3).getName());
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
          failedPatches.add(((File)localObject3).getName());
        }
        continue;
        label505:
        PatchUtils.patch((ByteBuffer)localObject1, localException);
        i += 1;
      }
    }
  }
  
  private boolean requiresPatchingInSafeMode()
  {
    return getMCPEVersion().startsWith("1.0");
  }
  
  public static void saveScreenshot(String paramString, int paramInt1, int paramInt2, int[] paramArrayOfInt) {}
  
  private void setCommandHistoryIndex(int paramInt)
  {
    boolean bool2 = true;
    this.commandHistoryIndex = paramInt;
    Button localButton = this.prevButton;
    if (paramInt != 0)
    {
      bool1 = true;
      localButton.setEnabled(bool1);
      localButton = this.nextButton;
      if (paramInt == this.commandHistoryList.size() - 1) {
        break label60;
      }
    }
    label60:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      localButton.setEnabled(bool1);
      return;
      bool1 = false;
      break;
    }
  }
  
  private void setFakePackage(boolean paramBoolean)
  {
    this.fakePackage = paramBoolean;
  }
  
  @TargetApi(19)
  private void setImmersiveMode(boolean paramBoolean)
  {
    if (Build.VERSION.SDK_INT < 19) {
      return;
    }
    int i = getWindow().getDecorView().getSystemUiVisibility();
    if (paramBoolean) {
      i |= 0x1002;
    }
    for (;;)
    {
      getWindow().getDecorView().setSystemUiVisibility(i);
      return;
      i &= 0xEFFD;
    }
  }
  
  private static String stringFromInputStream(InputStream paramInputStream, int paramInt)
    throws IOException
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream(paramInt);
    try
    {
      byte[] arrayOfByte = new byte['Ѐ'];
      for (;;)
      {
        paramInt = paramInputStream.read(arrayOfByte);
        if (paramInt == -1) {
          break;
        }
        localByteArrayOutputStream.write(arrayOfByte, 0, paramInt);
      }
      paramInputStream = localByteArrayOutputStream.toString("UTF-8");
    }
    finally
    {
      localByteArrayOutputStream.close();
    }
    localByteArrayOutputStream.close();
    return paramInputStream;
  }
  
  private void touchImmersiveMode()
  {
    final boolean bool = Utils.getPrefs(0).getBoolean("zz_immersive_mode", false);
    if (!bool) {
      return;
    }
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        MainActivity.this.setImmersiveMode(bool);
      }
    });
  }
  
  private void turnOffSafeMode()
  {
    Utils.getPrefs(0).edit().putBoolean("zz_safe_mode", false).commit();
    Utils.getPrefs(1).edit().putBoolean("force_prepatch", true).commit();
    finish();
    NerdyStuffActivity.forceRestart(this);
  }
  
  private boolean useLegacyKeyboardInput()
  {
    return Utils.getPrefs(0).getBoolean("zz_legacy_keyboard_input", false);
  }
  
  public int abortWebRequest(int paramInt)
  {
    Log.i("BlockLauncher/Main", "Abort web request: " + paramInt);
    HurlRunner localHurlRunner = (HurlRunner)this.requestMap.get(paramInt);
    if (localHurlRunner != null) {
      HurlRunner.access$402(localHurlRunner, false);
    }
    return 0;
  }
  
  protected boolean allowScriptOverrideTextures()
  {
    return true;
  }
  
  protected void applyBuiltinPatches() {}
  
  public void buyGame() {}
  
  public long calculateAvailableDiskFreeSpace(String paramString)
  {
    System.out.println("Calculate disk free space: " + paramString);
    return 0L;
  }
  
  public int checkLicense()
  {
    return 0;
  }
  
  public void clearLoginInformation()
  {
    Log.i("BlockLauncher/Main", "Clear login info");
    Utils.getPrefs(0).edit().putString("accessToken", "").putString("clientId", "").putString("profileUuid", "").putString("profileName", "").apply();
  }
  
  public Intent createAndroidLaunchIntent()
  {
    System.out.println("create android launch intent");
    return getIntent();
  }
  
  protected Dialog createBackupsNotSupportedDialog()
  {
    new AlertDialog.Builder(this).setMessage("Backed up versions of BlockLauncher are not supported, as BlockLauncher depends on updates from the application store.  Please reinstall BlockLauncher. If you believe you received this message in error, contact zhuowei_applications@yahoo.com").setPositiveButton(17039370, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        MainActivity.this.finish();
      }
    }).setCancelable(false).create();
  }
  
  protected Dialog createCopyWorldDialog()
  {
    View localView = getLayoutInflater().inflate(R.layout.copy_world_dialog, null);
    new AlertDialog.Builder(this).setTitle(R.string.copy_world_title).setView(localView).setPositiveButton(17039370, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface = ((TextView)((AlertDialog)paramAnonymousDialogInterface).findViewById(R.id.world_name_entry)).getText().toString();
        MainActivity.this.userInputStrings = new String[] { paramAnonymousDialogInterface };
        MainActivity.this.inputStatus = 1;
      }
    }).setNegativeButton(17039360, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        MainActivity.this.inputStatus = 0;
      }
    }).setOnCancelListener(new DialogInterface.OnCancelListener()
    {
      public void onCancel(DialogInterface paramAnonymousDialogInterface)
      {
        MainActivity.this.inputStatus = 0;
      }
    }).create();
  }
  
  protected Dialog createCreateWorldDialog()
  {
    View localView = getLayoutInflater().inflate(R.layout.create_world_dialog, null);
    new AlertDialog.Builder(this).setTitle(R.string.world_create_title).setView(localView).setPositiveButton(17039370, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        Object localObject = (AlertDialog)paramAnonymousDialogInterface;
        paramAnonymousDialogInterface = ((TextView)((AlertDialog)localObject).findViewById(R.id.world_name_entry)).getText().toString();
        String str = ((TextView)((AlertDialog)localObject).findViewById(R.id.world_seed_entry)).getText().toString();
        localObject = MainActivity.GAME_MODES[((android.widget.Spinner)localObject.findViewById(R.id.world_gamemode_spinner)).getSelectedItemPosition()];
        MainActivity.this.userInputStrings = new String[] { paramAnonymousDialogInterface, str, localObject };
        MainActivity.this.inputStatus = 1;
      }
    }).setNegativeButton(17039360, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        MainActivity.this.inputStatus = 0;
      }
    }).setOnCancelListener(new DialogInterface.OnCancelListener()
    {
      public void onCancel(DialogInterface paramAnonymousDialogInterface)
      {
        MainActivity.this.inputStatus = 0;
      }
    }).create();
  }
  
  protected Dialog createFirstLaunchDialog()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(getResources().getString(R.string.firstlaunch_generic_intro)).append("\n\n");
    if (this.minecraftApkForwardLocked) {
      localStringBuilder.append(getResources().getString(R.string.firstlaunch_jelly_bean)).append("\n\n");
    }
    localStringBuilder.append(getResources().getString(R.string.firstlaunch_see_options)).append("\n\n");
    new AlertDialog.Builder(this).setTitle(R.string.firstlaunch_title).setMessage(localStringBuilder.toString()).setPositiveButton(17039370, null).setNeutralButton(R.string.firstlaunch_help, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface = new Intent("android.intent.action.VIEW");
        paramAnonymousDialogInterface.setData(Uri.parse("http://www.minecraftforum.net/topic/1675581-blocklauncher-an-android-app-that-patches-minecraft-pe-without-reinstall/"));
        MainActivity.this.startActivity(paramAnonymousDialogInterface);
      }
    }).create();
  }
  
  protected Dialog createInsertTextDialog()
  {
    final EditText localEditText = new EditText(this);
    localEditText.setSingleLine(false);
    LinearLayout localLinearLayout = new LinearLayout(this);
    localLinearLayout.setOrientation(0);
    localLinearLayout.addView(localEditText, -2, -2);
    Button localButton = new Button(this);
    localButton.setText(R.string.hovercar_insert_text_backspace);
    localButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        try
        {
          MainActivity.this.nativeTypeCharacter("\b");
          return;
        }
        catch (Exception paramAnonymousView)
        {
          MainActivity.this.showDialog(4101);
        }
      }
    });
    localLinearLayout.addView(localButton, -2, -2);
    new AlertDialog.Builder(this).setTitle(R.string.hovercar_insert_text).setView(localLinearLayout).setPositiveButton(17039370, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        try
        {
          paramAnonymousDialogInterface = localEditText.getText().toString().split("\n");
          paramAnonymousInt = 0;
          while (paramAnonymousInt < paramAnonymousDialogInterface.length)
          {
            if (paramAnonymousInt != 0) {
              MainActivity.this.nativeTypeCharacter("\n");
            }
            MainActivity.this.nativeTypeCharacter(paramAnonymousDialogInterface[paramAnonymousInt]);
            paramAnonymousInt += 1;
          }
          localEditText.setText("");
          return;
        }
        catch (UnsatisfiedLinkError paramAnonymousDialogInterface)
        {
          MainActivity.this.showDialog(4101);
        }
      }
    }).setNegativeButton(17039360, null).create();
  }
  
  protected Dialog createInvalidPatchesDialog()
  {
    return new AlertDialog.Builder(this).setMessage(getResources().getString(R.string.manage_patches_invalid_patches) + "\n" + PatchManager.join((String[])failedPatches.toArray(PatchManager.blankArray), "\n")).setPositiveButton(17039370, null).create();
  }
  
  protected Dialog createMultiplayerDisableScriptsDialog()
  {
    return new AlertDialog.Builder(this).setMessage(R.string.script_disabled_in_multiplayer).setPositiveButton(17039370, null).create();
  }
  
  protected Dialog createNotSupportedDialog()
  {
    return new AlertDialog.Builder(this).setMessage(R.string.feature_not_supported).setPositiveButton(17039370, null).create();
  }
  
  protected Dialog createRuntimeOptionsDialog(boolean paramBoolean)
  {
    Object localObject1 = getResources().getString(R.string.pref_texture_pack);
    final String str3 = getResources().getString(R.string.hovercar_options);
    final String str4 = getResources().getString(R.string.hovercar_insert_text);
    Object localObject2 = getResources().getString(R.string.pref_zz_manage_scripts);
    String str5 = getResources().getString(R.string.take_screenshot);
    final String str1 = getResources().getString(R.string.hovercar_start_recording);
    final String str2 = getResources().getString(R.string.hovercar_stop_recording);
    localObject2 = new ArrayList(Arrays.asList(new CharSequence[] { localObject1, localObject2, str5 }));
    if (this.hasRecorder)
    {
      this.isRecording = isKamcordRecording();
      if (this.isRecording)
      {
        localObject1 = str2;
        ((List)localObject2).add(localObject1);
      }
    }
    else
    {
      if (paramBoolean) {
        ((List)localObject2).add(str4);
      }
      ((List)localObject2).add(str3);
      localObject1 = new AlertDialog.Builder(this);
      if (!Utils.isSafeMode()) {
        break label263;
      }
    }
    label263:
    for (int i = R.string.pref_zz_safe_mode;; i = R.string.app_name)
    {
      localObject1 = ((AlertDialog.Builder)localObject1).setTitle(i).setItems((CharSequence[])((List)localObject2).toArray(new CharSequence[0]), new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          int i = 1;
          paramAnonymousDialogInterface = (CharSequence)this.val$options.get(paramAnonymousInt);
          if (paramAnonymousInt == 0)
          {
            paramAnonymousDialogInterface = new Intent(MainActivity.this, TexturePacksActivity.class);
            MainActivity.this.startActivityForResult(paramAnonymousDialogInterface, 416);
          }
          do
          {
            return;
            if (paramAnonymousInt == 1)
            {
              if (MainActivity.this.hasScriptSupport())
              {
                paramAnonymousDialogInterface = new Intent(MainActivity.this, ManageScriptsActivity.class);
                MainActivity.this.startActivityForResult(paramAnonymousDialogInterface, 417);
                return;
              }
              new AlertDialog.Builder(MainActivity.this).setMessage("Scripts are not supported yet in Minecraft PE " + MainActivity.this.mcPkgInfo.versionName).setPositiveButton(17039370, null).show();
              return;
            }
            if (paramAnonymousInt == 2)
            {
              if ((Utils.getPrefs(0).getBoolean("zz_script_enable", true)) && (!Utils.isSafeMode()) && (MainActivity.this.hasScriptSupport())) {}
              for (paramAnonymousInt = i; paramAnonymousInt != 0; paramAnonymousInt = 0)
              {
                ScriptManager.takeScreenshot("screenshot");
                return;
              }
              new AlertDialog.Builder(MainActivity.this).setMessage(R.string.take_screenshot_requires_modpe_script).setPositiveButton(17039370, null).show();
              return;
            }
            if (paramAnonymousDialogInterface.equals(str3))
            {
              MainActivity.this.startActivity(MainActivity.this.getOptionsActivityIntent());
              return;
            }
            if (paramAnonymousDialogInterface.equals(str4))
            {
              MainActivity.this.showDialog(4103);
              return;
            }
          } while ((!paramAnonymousDialogInterface.equals(str1)) && (!paramAnonymousDialogInterface.equals(str2)));
          MainActivity.this.mainHandler.sendEmptyMessageDelayed(327, 1000L);
        }
      });
      if (Build.VERSION.SDK_INT >= 19) {
        ((AlertDialog.Builder)localObject1).setOnDismissListener(new DialogInterface.OnDismissListener()
        {
          public void onDismiss(DialogInterface paramAnonymousDialogInterface)
          {
            MainActivity.this.touchImmersiveMode();
          }
        });
      }
      return ((AlertDialog.Builder)localObject1).create();
      localObject1 = str1;
      break;
    }
  }
  
  protected Dialog createSELinuxBrokeEverythingDialog()
  {
    return new AlertDialog.Builder(this).setMessage(R.string.selinux_broke_everything).setPositiveButton(17039370, null).create();
  }
  
  protected Dialog createSafeModeDialog(int paramInt)
  {
    new AlertDialog.Builder(this).setMessage(paramInt).setPositiveButton(R.string.safe_mode_exit, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        MainActivity.this.turnOffSafeMode();
      }
    }).setNegativeButton(R.string.safe_mode_continue, null).create();
  }
  
  public String createUUID()
  {
    System.out.println("Create UUID");
    return UUID.randomUUID().toString().replace("-", "");
  }
  
  protected Dialog createUpdateTexturePackDialog()
  {
    return new AlertDialog.Builder(this).setMessage(R.string.extract_textures_need_update).setPositiveButton(17039370, null).create();
  }
  
  public void dismissHiddenTextbox()
  {
    if (this.hiddenTextWindow == null) {
      return;
    }
    this.hiddenTextWindow.dismiss();
    hideKeyboardView();
  }
  
  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
  {
    if ((paramKeyEvent.getAction() == 2) && (paramKeyEvent.getKeyCode() == 0)) {
      try
      {
        nativeTypeCharacter(paramKeyEvent.getCharacters());
        return true;
      }
      catch (UnsatisfiedLinkError localUnsatisfiedLinkError) {}
    }
    return super.dispatchKeyEvent(paramKeyEvent);
  }
  
  public void displayDialog(int paramInt)
  {
    System.out.println("displayDialog: " + paramInt);
    this.inputStatus = 0;
    switch (paramInt)
    {
    case 2: 
    default: 
      return;
    case 1: 
      System.out.println("World creation");
      this.inputStatus = -1;
      runOnUiThread(new Runnable()
      {
        public void run()
        {
          MainActivity.this.showDialog(1);
        }
      });
      return;
    case 3: 
      System.out.println("Settings");
      this.inputStatus = -1;
      startActivityForResult(getOptionsActivityIntent(), 1234);
      return;
    }
    System.out.println("Copy world");
    this.inputStatus = -1;
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        MainActivity.this.showDialog(4);
      }
    });
  }
  
  public boolean doesRequireGuiBlocksPatch()
  {
    return false;
  }
  
  public boolean existsForPath(String paramString)
  {
    if (((paramString.startsWith("resource_packs/")) && (!paramString.startsWith("resource_packs/vanilla"))) || ((paramString.startsWith("resourcepacks")) && (!paramString.startsWith("resourcepacks/vanilla")))) {
      paramString = getLocalInputStreamForAsset(paramString);
    }
    for (;;)
    {
      if (paramString != null) {}
      try
      {
        paramString.close();
        if (paramString != null)
        {
          return true;
          paramString = getInputStreamForAsset(paramString);
          continue;
        }
        return false;
      }
      catch (IOException localIOException)
      {
        for (;;) {}
      }
    }
  }
  
  public void fakeTipMessageCallback(String paramString)
  {
    if (this.minecraftTypeface == null) {
      this.minecraftTypeface = Typeface.createFromAsset(getAssets(), "fonts/minecraft.ttf");
    }
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        TextView localTextView = new TextView(MainActivity.this);
        localTextView.setText(this.val$message);
        localTextView.setTypeface(MainActivity.this.minecraftTypeface);
        localTextView.setTextColor(-1);
        localTextView.setShadowLayer(0.1F, 8.0F, 8.0F, -16777216);
        localTextView.setTextSize(16.0F);
        if (MainActivity.this.lastToast != null) {
          MainActivity.this.lastToast.cancel();
        }
        Toast localToast = new Toast(MainActivity.this);
        localToast.setView(localTextView);
        MainActivity.access$1702(MainActivity.this, localToast);
        localToast.show();
      }
    });
  }
  
  protected String filterUrl(String paramString)
  {
    return paramString;
  }
  
  public void forceTextureReload() {}
  
  public String getAccessToken()
  {
    Log.i("BlockLauncher/Main", "Get access token");
    return Utils.getPrefs(0).getString("accessToken", "");
  }
  
  public int getAndroidVersion()
  {
    return Build.VERSION.SDK_INT;
  }
  
  public String[] getBroadcastAddresses()
  {
    Log.i("BlockLauncher/Main", "get broadcast addresses");
    return new String[] { "255.255.255.255" };
  }
  
  public String getClientId()
  {
    Log.i("BlockLauncher/Main", "Get client ID");
    return Utils.getPrefs(0).getString("clientId", "");
  }
  
  public int getCursorPosition()
  {
    if (this.hiddenTextView == null) {
      return 0;
    }
    return this.hiddenTextView.getSelectionStart();
  }
  
  public String getDateString(int paramInt)
  {
    System.out.println("getDateString: " + paramInt);
    return DateFormat.getDateInstance(3, Locale.US).format(new Date(paramInt * 1000L));
  }
  
  public String getDeviceId()
  {
    String str2 = Utils.getPrefs(0).getString("snooperId", null);
    String str1 = str2;
    if (str2 == null)
    {
      str1 = createUUID();
      Utils.getPrefs(0).edit().putString("snooperId", str1).apply();
    }
    System.out.println("Get device ID");
    return str1;
  }
  
  public String getDeviceModel()
  {
    return HardwareInformation.getDeviceModelName();
  }
  
  public String getExternalStoragePath()
  {
    return Environment.getExternalStorageDirectory().getAbsolutePath();
  }
  
  public int[] getFakeImageData(String paramString, boolean paramBoolean)
  {
    return new int[] { 1, 1, 0 };
  }
  
  public byte[] getFileDataBytes(String paramString)
  {
    byte[] arrayOfByte2 = getFileDataBytes(paramString, false);
    byte[] arrayOfByte1 = arrayOfByte2;
    if (paramString.endsWith(".meta"))
    {
      String str = new String(arrayOfByte2, Charset.forName("UTF-8"));
      arrayOfByte1 = arrayOfByte2;
      if (!str.contains("portal"))
      {
        arrayOfByte1 = arrayOfByte2;
        if (!str.contains("rabbit_foot")) {
          arrayOfByte1 = getFileDataBytes(paramString, true);
        }
      }
    }
    return arrayOfByte1;
  }
  
  public byte[] getFileDataBytes(String paramString, boolean paramBoolean)
  {
    label130:
    label132:
    for (;;)
    {
      try
      {
        if (paramString.charAt(0) == '/')
        {
          paramString = getRegularInputStream(paramString);
          if (paramString == null) {
            break label130;
          }
          if ("BlockLauncher/Main".hashCode() != -1771687045) {
            return null;
          }
        }
        else
        {
          if ((paramString.equals("behavior_packs/vanilla/entities/villager.json")) || (paramString.equals("resourcepacks/vanilla/server/entities/villager.json")))
          {
            paramString = openFallbackAsset(paramString);
            continue;
          }
          if (paramBoolean)
          {
            paramString = getLocalInputStreamForAsset(paramString);
            break label132;
          }
          paramString = getInputStreamForAsset(paramString);
          break label132;
        }
        ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
        byte[] arrayOfByte = new byte['Ѐ'];
        int i = paramString.read(arrayOfByte);
        if (i < 0) {
          return localByteArrayOutputStream.toByteArray();
        }
        localByteArrayOutputStream.write(arrayOfByte, 0, i);
        continue;
        return null;
      }
      catch (Exception paramString) {}
    }
  }
  
  public String[] getIPAddresses()
  {
    System.out.println("get IP addresses?");
    return new String[] { "127.0.0.1" };
  }
  
  public int[] getImageData(String paramString)
  {
    return getImageData(paramString, true);
  }
  
  public int[] getImageData(String paramString, boolean paramBoolean)
  {
    int i = 1;
    System.out.println("Get image data: " + paramString + " from assets? " + paramBoolean);
    if ((paramString.length() > 0) && (paramString.charAt(0) == '/')) {}
    for (;;)
    {
      if (i != 0) {}
      try
      {
        for (InputStream localInputStream = getRegularInputStream(paramString); localInputStream == null; localInputStream = getInputStreamForAsset(paramString)) {
          return getFakeImageData(paramString, paramBoolean);
        }
        paramString = BitmapFactory.decodeStream(localInputStream);
        int[] arrayOfInt = new int[paramString.getWidth() * paramString.getHeight() + 2];
        arrayOfInt[0] = paramString.getWidth();
        arrayOfInt[1] = paramString.getHeight();
        paramString.getPixels(arrayOfInt, 2, paramString.getWidth(), 0, 0, paramString.getWidth(), paramString.getHeight());
        localInputStream.close();
        paramString.recycle();
        return arrayOfInt;
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
        return null;
      }
      i = 0;
    }
  }
  
  public InputStream getInputStreamForAsset(String paramString)
  {
    return getInputStreamForAsset(paramString, null);
  }
  
  public InputStream getInputStreamForAsset(String paramString, long[] paramArrayOfLong)
  {
    int i = 0;
    try
    {
      for (;;)
      {
        int j = this.textureOverrides.size();
        if (i >= j) {
          break;
        }
        try
        {
          InputStream localInputStream1 = ((TexturePack)this.textureOverrides.get(i)).getInputStream(paramString);
          if (localInputStream1 != null)
          {
            if (paramArrayOfLong != null) {
              paramArrayOfLong[0] = ((TexturePack)this.textureOverrides.get(i)).getSize(paramString);
            }
            return localInputStream1;
          }
        }
        catch (IOException localIOException)
        {
          i += 1;
        }
      }
      if (this.texturePack == null) {
        return getLocalInputStreamForAsset(paramString, paramArrayOfLong);
      }
      System.out.println("Trying to load  " + paramString + "from tp");
      InputStream localInputStream2 = this.texturePack.getInputStream(paramString);
      if (localInputStream2 == null)
      {
        System.out.println("Can't load " + paramString + " from tp");
        paramString = getLocalInputStreamForAsset(paramString, paramArrayOfLong);
        return paramString;
      }
      return localInputStream2;
    }
    catch (Exception paramString)
    {
      System.err.println(paramString);
    }
    return null;
  }
  
  public int getKeyFromKeyCode(int paramInt1, int paramInt2, int paramInt3)
  {
    return KeyCharacterMap.load(paramInt3).get(paramInt1, paramInt2);
  }
  
  public float getKeyboardHeight()
  {
    Rect localRect = new Rect();
    getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
    if (localRect.bottom == 0) {
      return 0.0F;
    }
    return this.displayMetrics.heightPixels - localRect.bottom;
  }
  
  public Intent getLaunchIntent()
  {
    System.out.println("get launch intent");
    return getIntent();
  }
  
  protected InputStream getLocalInputStreamForAsset(String paramString)
  {
    return getLocalInputStreamForAsset(paramString, null);
  }
  
  protected InputStream getLocalInputStreamForAsset(String paramString, long[] paramArrayOfLong)
  {
    try
    {
      if (this.forceFallback)
      {
        paramString = openFallbackAsset(paramString);
        return paramString;
      }
      InputStream localInputStream3;
      try
      {
        InputStream localInputStream1 = this.minecraftApkContext.getAssets().open(paramString);
        localInputStream3 = localInputStream1;
        if (localInputStream1 == null)
        {
          if (this.textureVerbose) {
            System.out.println("Can't find it in the APK - attempting to load fallback");
          }
          localInputStream3 = openFallbackAsset(paramString);
        }
        if ((localInputStream3 != null) && (paramArrayOfLong != null)) {
          paramArrayOfLong[0] = localInputStream3.available();
        }
      }
      catch (Exception localException)
      {
        for (;;)
        {
          if (this.textureVerbose) {
            System.out.println("Attempting to load fallback");
          }
          InputStream localInputStream2 = openFallbackAsset(paramString);
        }
      }
      return localInputStream3;
    }
    catch (Exception paramString)
    {
      if (this.textureVerbose) {
        System.err.println(paramString);
      }
      return null;
    }
  }
  
  public String getLocale()
  {
    Locale localLocale = getResources().getConfiguration().locale;
    return localLocale.getLanguage() + "_" + localLocale.getCountry();
  }
  
  public String getMCPEVersion()
  {
    return this.mcPkgInfo.versionName;
  }
  
  public int getMaxNumPatches()
  {
    return getResources().getInteger(R.integer.max_num_patches);
  }
  
  public String[] getOptionStrings()
  {
    System.err.println("OptionStrings");
    SharedPreferences localSharedPreferences = Utils.getPrefs(0);
    Object localObject = localSharedPreferences.getAll().entrySet();
    ArrayList localArrayList = new ArrayList();
    localObject = ((Set)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      Map.Entry localEntry = (Map.Entry)((Iterator)localObject).next();
      String str = (String)localEntry.getKey();
      if (str.indexOf("zz_") != 0)
      {
        localArrayList.add(str);
        if (str.equals("ctrl_sensitivity")) {
          localArrayList.add(Double.toString(Integer.parseInt(localEntry.getValue().toString()) / 100.0D));
        } else {
          localArrayList.add(localEntry.getValue().toString());
        }
      }
    }
    localArrayList.add("game_difficulty");
    if (localSharedPreferences.getBoolean("game_difficultypeaceful", false)) {
      localArrayList.add("0");
    }
    for (;;)
    {
      System.out.println(localArrayList.toString());
      return (String[])localArrayList.toArray(new String[0]);
      localArrayList.add("2");
    }
  }
  
  protected Intent getOptionsActivityIntent()
  {
    return new Intent(this, MainMenuOptionsActivity.class);
  }
  
  public PackageManager getPackageManager()
  {
    if (this.fakePackage) {
      return new RedirectPackageManager(super.getPackageManager(), MC_NATIVE_LIBRARY_DIR);
    }
    return super.getPackageManager();
  }
  
  public float getPixelsPerMillimeter()
  {
    System.out.println("Pixels per mm");
    float f2 = this.displayMetrics.densityDpi / 25.4F;
    String str = Utils.getPrefs(0).getString("zz_custom_dpi", null);
    float f1 = f2;
    if (str != null)
    {
      f1 = f2;
      if (str.length() <= 0) {}
    }
    try
    {
      f1 = Float.parseFloat(str);
      f1 /= 25.4F;
      return f1;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return f2;
  }
  
  public String getPlatformStringVar(int paramInt)
  {
    System.out.println("getPlatformStringVar: " + paramInt);
    return "";
  }
  
  public String getProfileId()
  {
    Log.i("BlockLauncher/Main", "Get profile ID");
    return Utils.getPrefs(0).getString("profileUuid", "");
  }
  
  public String getProfileName()
  {
    Log.i("BlockLauncher/Main", "Get profile name");
    return Utils.getPrefs(0).getString("profileName", "");
  }
  
  public RealmsRedirectInfo getRealmsRedirectInfo()
  {
    return (RealmsRedirectInfo)RealmsRedirectInfo.targets.get("NONE");
  }
  
  public String getRefreshToken()
  {
    Log.i("BlockLauncher/Main", "Get Refresh token");
    return Utils.getPrefs(0).getString("refreshToken", "");
  }
  
  public int getScreenHeight()
  {
    System.out.println("height");
    return this.displayMetrics.heightPixels;
  }
  
  public int getScreenWidth()
  {
    System.out.println("width");
    return this.displayMetrics.widthPixels;
  }
  
  public String getSession()
  {
    Log.i("BlockLauncher/Main", "Get Session");
    return Utils.getPrefs(0).getString("sessionId", "");
  }
  
  public long getTotalMemory()
  {
    try
    {
      long l = Utils.parseMemInfo();
      Log.i("BlockLauncher/Main", "Get total memory: " + l);
      return l;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return 17179869184L;
  }
  
  public int getUserInputStatus()
  {
    System.out.println("User input status: " + this.inputStatus);
    return this.inputStatus;
  }
  
  public String[] getUserInputString()
  {
    System.out.println("User input string");
    return this.userInputStrings;
  }
  
  public String getWebRequestContent(int paramInt)
  {
    Log.i("BlockLauncher/Main", "Get web request content: " + paramInt);
    return "ThisIsSparta";
  }
  
  public int getWebRequestStatus(int paramInt)
  {
    Log.i("BlockLauncher/Main", "Get web request status: " + paramInt);
    return 0;
  }
  
  public boolean hasBuyButtonWhenInvalidLicense()
  {
    return false;
  }
  
  public boolean hasHardwareChanged()
  {
    return false;
  }
  
  protected boolean hasScriptSupport()
  {
    return true;
  }
  
  public void hideKeyboard()
  {
    if (useLegacyKeyboardInput())
    {
      hideKeyboardView();
      return;
    }
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        MainActivity.this.dismissHiddenTextbox();
      }
    });
  }
  
  public void hideKeyboardView()
  {
    ((InputMethodManager)getSystemService("input_method")).hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
    touchImmersiveMode();
  }
  
  protected void initKamcord() {}
  
  public void initPatching()
    throws Exception
  {
    System.loadLibrary("gnustl_shared");
    System.loadLibrary("mcpelauncher_tinysubstrate");
    System.out.println("MCPE Version is " + getMCPEVersion());
    if (getMCPEVersion().startsWith("1.0")) {
      System.loadLibrary("mcpelauncher_new");
    }
    for (;;)
    {
      if (!MaraudersMap.initPatching(this, findMinecraftLibLength()))
      {
        System.out.println("Well, that sucks!");
        tempSafeMode = true;
        this.overlyZealousSELinuxSafeMode = true;
      }
      return;
      System.loadLibrary("mcpelauncher");
    }
  }
  
  public void initiateUserInput(int paramInt)
  {
    System.out.println("initiateUserInput: " + paramInt);
  }
  
  public boolean isDemo()
  {
    Log.i("BlockLauncher/Main", "Is demo");
    return false;
  }
  
  public boolean isFirstSnooperStart()
  {
    boolean bool = false;
    System.out.println("Is first snooper start");
    if (Utils.getPrefs(0).getString("snooperId", null) == null) {
      bool = true;
    }
    return bool;
  }
  
  protected boolean isKamcordRecording()
  {
    return false;
  }
  
  public boolean isNetworkEnabled(boolean paramBoolean)
  {
    return true;
  }
  
  public boolean isRedirectingRealms()
  {
    return false;
  }
  
  public boolean isTablet()
  {
    if (Build.VERSION.SDK_INT < 13) {}
    while (getResources().getConfiguration().smallestScreenWidthDp < 600) {
      return false;
    }
    return true;
  }
  
  public boolean isTextToSpeechInProgress()
  {
    return false;
  }
  
  public boolean isTouchscreen()
  {
    return Utils.getPrefs(0).getBoolean("ctrl_usetouchscreen", true);
  }
  
  public void launchUri(String paramString)
  {
    paramString = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
    try
    {
      startActivity(paramString);
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public void leaveGameCallback()
  {
    System.out.println("Leave game");
    if (this.hasRecorder) {
      clearRuntimeOptionsDialog();
    }
  }
  
  public String[] listDirForPath(String paramString)
  {
    System.out.println("Listing dir for " + paramString);
    Object localObject1 = paramString + "/";
    HashSet localHashSet = new HashSet();
    Iterator localIterator = this.textureOverrides.iterator();
    for (;;)
    {
      if (localIterator.hasNext())
      {
        Object localObject2 = (TexturePack)localIterator.next();
        try
        {
          localObject2 = ((TexturePack)localObject2).listFiles();
          localObject2 = ((List)localObject2).iterator();
          while (((Iterator)localObject2).hasNext())
          {
            String str = (String)((Iterator)localObject2).next();
            if ((str.startsWith((String)localObject1)) && (str.indexOf("/", ((String)localObject1).length()) == -1)) {
              localHashSet.add(str.substring(str.lastIndexOf("/")));
            }
          }
        }
        catch (IOException localIOException2) {}
      }
    }
    localObject1 = paramString;
    if (getMCPEVersion().startsWith("1.0")) {
      localObject1 = "1007/" + paramString;
    }
    try
    {
      localObject1 = getAssets().list((String)localObject1);
      j = localObject1.length;
      i = 0;
      while (i < j)
      {
        localHashSet.add(localObject1[i]);
        i += 1;
      }
    }
    catch (IOException localIOException1)
    {
      int j;
      int i;
      for (;;)
      {
        String[] arrayOfString = new String[0];
      }
      try
      {
        paramString = this.minecraftApkContext.getAssets().list(paramString);
        j = paramString.length;
        i = 0;
        while (i < j)
        {
          localHashSet.add(paramString[i]);
          i += 1;
        }
      }
      catch (IOException paramString)
      {
        for (;;)
        {
          paramString = new String[0];
        }
        paramString = (String[])localHashSet.toArray(paramString);
        System.out.println(Arrays.toString(paramString));
      }
    }
    return paramString;
  }
  
  protected void loadNativeAddons()
  {
    if (!Utils.getPrefs(0).getBoolean("zz_load_native_addons", true)) {
      return;
    }
    PackageManager localPackageManager = getPackageManager();
    AddonManager localAddonManager = AddonManager.getAddonManager(this);
    Object localObject = localPackageManager.getInstalledApplications(128);
    StringBuilder localStringBuilder = new StringBuilder();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject).next();
      if (localApplicationInfo.metaData != null)
      {
        String str1 = localApplicationInfo.metaData.getString("net.zhuoweizhang.mcpelauncher.api.nativelibname");
        String str2 = localApplicationInfo.metaData.getString("net.zhuoweizhang.mcpelauncher.api.targetmcpeversion");
        if ((localPackageManager.checkPermission("net.zhuoweizhang.mcpelauncher.ADDON", localApplicationInfo.packageName) == 0) && (localAddonManager.isEnabled(localApplicationInfo.packageName)))
        {
          try
          {
            if (isAddonCompat(str2)) {
              break label241;
            }
            throw new Exception("The addon \"" + localPackageManager.getApplicationLabel(localApplicationInfo).toString() + "\" (" + localApplicationInfo.packageName + ")" + " is not compatible with Minecraft PE " + this.mcPkgInfo.versionName + ".");
          }
          catch (Throwable localThrowable)
          {
            reportError(localThrowable);
            localThrowable.printStackTrace();
          }
          continue;
          label241:
          if (str1 != null)
          {
            if (checkAddonArch(new File(localThrowable.nativeLibraryDir + "/lib" + str1 + ".so")))
            {
              System.load(localThrowable.nativeLibraryDir + "/lib" + str1 + ".so");
              loadedAddons.add(localThrowable.packageName);
            }
            else
            {
              localStringBuilder.append("\"").append(localPackageManager.getApplicationLabel(localThrowable).toString()).append("\" (").append(localThrowable.packageName).append(") ");
            }
          }
          else {
            loadedAddons.add(localThrowable.packageName);
          }
        }
      }
    }
    if (localStringBuilder.length() != 0) {
      reportError(new Exception(getResources().getString(R.string.addons_wrong_arch).toString().replaceAll("ARCH", Utils.getArchName(this.mcpeArch)).replaceAll("ADDONS", localStringBuilder.toString())));
    }
    if (getMCPEVersion().startsWith("0.16")) {}
    for (this.addonOverrideTexturePackInstance = new AddonOverrideTexturePack(this, "resourcepacks/vanilla/");; this.addonOverrideTexturePackInstance = new AddonOverrideTexturePack(this, "resource_packs/vanilla/"))
    {
      this.textureOverrides.add(this.addonOverrideTexturePackInstance);
      return;
    }
  }
  
  protected void loadTexturePack()
  {
    try
    {
      boolean bool = Utils.getPrefs(0).getBoolean("zz_texture_pack_enable", false);
      this.texturePack = null;
      if (bool)
      {
        ArrayList localArrayList = new ArrayList();
        List localList = TexturePackLoader.loadTexturePacks(this, localArrayList, getFileDataBytes("images/terrain.meta", true), getFileDataBytes("images/items.meta", true));
        if (localArrayList.size() != 0) {
          new AlertDialog.Builder(this).setMessage("Some of your texture packs are not compatible with Minecraft PE " + getMCPEVersion() + ". Please update " + Utils.join(localArrayList, ", ") + ".").setPositiveButton(17039370, null).show();
        }
        this.textureOverrides.addAll(localList);
      }
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      reportError(localException, R.string.texture_pack_unable_to_load, null);
    }
  }
  
  protected void loadTexturePackOld()
  {
    String str = null;
    Object localObject = str;
    try
    {
      boolean bool = Utils.getPrefs(0).getBoolean("zz_texture_pack_enable", false);
      localObject = str;
      str = Utils.getPrefs(1).getString("texturePack", null);
      if ((bool) && (str != null))
      {
        localObject = str;
        File localFile = new File(str);
        localObject = str;
        if (!localFile.exists())
        {
          localObject = str;
          this.texturePack = null;
          return;
        }
        localObject = str;
        this.texturePack = new ZipTexturePack(localFile);
        return;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      reportError(localException, R.string.texture_pack_unable_to_load, (String)localObject + ": size is " + new File((String)localObject).length());
      return;
    }
    localObject = localException;
    this.texturePack = null;
  }
  
  protected void loginLaunchCallback(Uri paramUri)
  {
    this.loginDialog.dismiss();
    if (paramUri.getQueryParameter("sessionId") == null) {
      return;
    }
    String str = paramUri.getQueryParameter("profileName");
    paramUri.getQueryParameter("identity");
    nativeLoginData(paramUri.getQueryParameter("accessToken"), paramUri.getQueryParameter("clientToken"), paramUri.getQueryParameter("profileUuid"), str);
  }
  
  protected void migrateToPatchManager()
  {
    int i = 1;
    for (;;)
    {
      try
      {
        if (Utils.getPrefs(1).getInt("patchManagerVersion", -1) <= 0) {
          break label81;
        }
      }
      catch (Exception localException)
      {
        File localFile;
        localException.printStackTrace();
        return;
      }
      showDialog(4099);
      localFile = getDir("ptpatches", 0);
      PatchManager.getPatchManager(this).setEnabled(localFile.listFiles(), true);
      System.out.println(Utils.getPrefs(1).getString("enabledPatches", "LOL"));
      return;
      while (i != 0)
      {
        return;
        label81:
        i = 0;
      }
    }
  }
  
  public native void nativeBackPressed();
  
  public native void nativeBackSpacePressed();
  
  public native void nativeKeyHandler(int paramInt, boolean paramBoolean);
  
  public native void nativeLoginData(String paramString1, String paramString2, String paramString3, String paramString4);
  
  public native void nativeProcessIntentUriQuery(String paramString1, String paramString2);
  
  public native void nativeRegisterThis();
  
  public native void nativeReturnKeyPressed();
  
  public native void nativeSetTextboxText(String paramString);
  
  public native void nativeStopThis();
  
  public native void nativeSuspend();
  
  public native void nativeTypeCharacter(String paramString);
  
  public native void nativeUnregisterThis();
  
  public native void nativeWebRequestCompleted(int paramInt1, long paramLong, int paramInt2, String paramString);
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramInt1 == 1234)
    {
      this.inputStatus = 1;
      System.out.println("Settings OK");
      if (!Utils.isSafeMode()) {
        applyBuiltinPatches();
      }
    }
    do
    {
      return;
      if (paramInt1 == 415)
      {
        if (paramInt2 == -1)
        {
          this.pickImageResult = paramIntent;
          paramIntent = copyContentStoreToTempFile(paramIntent.getData());
          nativeOnPickImageSuccess(this.pickImageCallbackAddress, paramIntent.getAbsolutePath());
          return;
        }
        nativeOnPickImageCanceled(this.pickImageCallbackAddress);
        return;
      }
    } while (((paramInt1 != 416) && (paramInt1 != 417)) || (paramInt2 != -1));
    finish();
    NerdyStuffActivity.forceRestart(this);
  }
  
  public void onBackPressed()
  {
    nativeBackPressed();
  }
  
  /* Error */
  public void onCreate(Bundle paramBundle)
  {
    // Byte code:
    //   0: new 2187	java/lang/ref/WeakReference
    //   3: dup
    //   4: aload_0
    //   5: invokespecial 2189	java/lang/ref/WeakReference:<init>	(Ljava/lang/Object;)V
    //   8: putstatic 259	com/mojang/minecraftpe/MainActivity:currentMainActivity	Ljava/lang/ref/WeakReference;
    //   11: iconst_2
    //   12: invokestatic 791	net/zhuoweizhang/mcpelauncher/Utils:getPrefs	(I)Landroid/content/SharedPreferences;
    //   15: ldc_w 2191
    //   18: iconst_0
    //   19: invokeinterface 799 3 0
    //   24: istore_3
    //   25: getstatic 513	java/lang/System:out	Ljava/io/PrintStream;
    //   28: new 539	java/lang/StringBuilder
    //   31: dup
    //   32: invokespecial 540	java/lang/StringBuilder:<init>	()V
    //   35: ldc_w 2193
    //   38: invokevirtual 553	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   41: iload_3
    //   42: invokevirtual 1143	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   45: invokevirtual 559	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   48: invokevirtual 1023	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   51: iload_3
    //   52: istore_2
    //   53: iload_3
    //   54: iconst_2
    //   55: if_icmpne +28 -> 83
    //   58: iconst_0
    //   59: invokestatic 791	net/zhuoweizhang/mcpelauncher/Utils:getPrefs	(I)Landroid/content/SharedPreferences;
    //   62: invokeinterface 809 1 0
    //   67: ldc_w 1124
    //   70: iconst_1
    //   71: invokeinterface 817 3 0
    //   76: invokeinterface 820 1 0
    //   81: iconst_0
    //   82: istore_2
    //   83: iconst_2
    //   84: invokestatic 791	net/zhuoweizhang/mcpelauncher/Utils:getPrefs	(I)Landroid/content/SharedPreferences;
    //   87: invokeinterface 809 1 0
    //   92: ldc_w 2191
    //   95: iload_2
    //   96: iconst_1
    //   97: iadd
    //   98: invokeinterface 824 3 0
    //   103: invokeinterface 1127 1 0
    //   108: pop
    //   109: aload_0
    //   110: new 451	java/io/File
    //   113: dup
    //   114: ldc_w 2195
    //   117: invokespecial 453	java/io/File:<init>	(Ljava/lang/String;)V
    //   120: invokevirtual 666	java/io/File:exists	()Z
    //   123: putfield 331	com/mojang/minecraftpe/MainActivity:textureVerbose	Z
    //   126: aload_0
    //   127: invokevirtual 2199	com/mojang/minecraftpe/MainActivity:getApplicationContext	()Landroid/content/Context;
    //   130: putstatic 2202	net/zhuoweizhang/mcpelauncher/MinecraftVersion:context	Landroid/content/Context;
    //   133: iconst_0
    //   134: istore_3
    //   135: aload_0
    //   136: aload_0
    //   137: invokevirtual 636	com/mojang/minecraftpe/MainActivity:getPackageManager	()Landroid/content/pm/PackageManager;
    //   140: ldc_w 2204
    //   143: iconst_0
    //   144: invokevirtual 644	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   147: putfield 343	com/mojang/minecraftpe/MainActivity:mcPkgInfo	Landroid/content/pm/PackageInfo;
    //   150: aload_0
    //   151: aload_0
    //   152: getfield 343	com/mojang/minecraftpe/MainActivity:mcPkgInfo	Landroid/content/pm/PackageInfo;
    //   155: getfield 671	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   158: putfield 1001	com/mojang/minecraftpe/MainActivity:mcAppInfo	Landroid/content/pm/ApplicationInfo;
    //   161: aload_0
    //   162: getfield 1001	com/mojang/minecraftpe/MainActivity:mcAppInfo	Landroid/content/pm/ApplicationInfo;
    //   165: getfield 549	android/content/pm/ApplicationInfo:nativeLibraryDir	Ljava/lang/String;
    //   168: putstatic 249	com/mojang/minecraftpe/MainActivity:MC_NATIVE_LIBRARY_DIR	Ljava/lang/String;
    //   171: new 539	java/lang/StringBuilder
    //   174: dup
    //   175: invokespecial 540	java/lang/StringBuilder:<init>	()V
    //   178: getstatic 249	com/mojang/minecraftpe/MainActivity:MC_NATIVE_LIBRARY_DIR	Ljava/lang/String;
    //   181: invokevirtual 553	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   184: ldc_w 1003
    //   187: invokevirtual 553	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   190: invokevirtual 559	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   193: putstatic 253	com/mojang/minecraftpe/MainActivity:MC_NATIVE_LIBRARY_LOCATION	Ljava/lang/String;
    //   196: getstatic 513	java/lang/System:out	Ljava/io/PrintStream;
    //   199: new 539	java/lang/StringBuilder
    //   202: dup
    //   203: invokespecial 540	java/lang/StringBuilder:<init>	()V
    //   206: ldc_w 2206
    //   209: invokevirtual 553	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   212: getstatic 253	com/mojang/minecraftpe/MainActivity:MC_NATIVE_LIBRARY_LOCATION	Ljava/lang/String;
    //   215: invokevirtual 553	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   218: invokevirtual 559	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   221: invokevirtual 1023	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   224: aload_0
    //   225: invokespecial 2208	com/mojang/minecraftpe/MainActivity:checkArch	()V
    //   228: aload_0
    //   229: getfield 1001	com/mojang/minecraftpe/MainActivity:mcAppInfo	Landroid/content/pm/ApplicationInfo;
    //   232: getfield 2211	android/content/pm/ApplicationInfo:sourceDir	Ljava/lang/String;
    //   235: aload_0
    //   236: getfield 1001	com/mojang/minecraftpe/MainActivity:mcAppInfo	Landroid/content/pm/ApplicationInfo;
    //   239: getfield 2214	android/content/pm/ApplicationInfo:publicSourceDir	Ljava/lang/String;
    //   242: invokevirtual 632	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   245: ifne +835 -> 1080
    //   248: iconst_1
    //   249: istore 5
    //   251: aload_0
    //   252: iload 5
    //   254: putfield 291	com/mojang/minecraftpe/MainActivity:minecraftApkForwardLocked	Z
    //   257: aload_0
    //   258: getfield 343	com/mojang/minecraftpe/MainActivity:mcPkgInfo	Landroid/content/pm/PackageInfo;
    //   261: getfield 805	android/content/pm/PackageInfo:versionCode	I
    //   264: istore 4
    //   266: aload_0
    //   267: iload 4
    //   269: invokestatic 2218	net/zhuoweizhang/mcpelauncher/MinecraftVersion:getRaw	(I)Lnet/zhuoweizhang/mcpelauncher/MinecraftVersion;
    //   272: putfield 1052	com/mojang/minecraftpe/MainActivity:minecraftVersion	Lnet/zhuoweizhang/mcpelauncher/MinecraftVersion;
    //   275: aload_0
    //   276: getfield 1052	com/mojang/minecraftpe/MainActivity:minecraftVersion	Lnet/zhuoweizhang/mcpelauncher/MinecraftVersion;
    //   279: ifnonnull +21 -> 300
    //   282: iconst_1
    //   283: putstatic 273	com/mojang/minecraftpe/MainActivity:tempSafeMode	Z
    //   286: aload_0
    //   287: sipush 4100
    //   290: invokevirtual 1071	com/mojang/minecraftpe/MainActivity:showDialog	(I)V
    //   293: aload_0
    //   294: invokestatic 2222	net/zhuoweizhang/mcpelauncher/MinecraftVersion:getDefault	()Lnet/zhuoweizhang/mcpelauncher/MinecraftVersion;
    //   297: putfield 1052	com/mojang/minecraftpe/MainActivity:minecraftVersion	Lnet/zhuoweizhang/mcpelauncher/MinecraftVersion;
    //   300: aload_0
    //   301: getfield 1052	com/mojang/minecraftpe/MainActivity:minecraftVersion	Lnet/zhuoweizhang/mcpelauncher/MinecraftVersion;
    //   304: getfield 2225	net/zhuoweizhang/mcpelauncher/MinecraftVersion:needsWarning	Z
    //   307: ifeq +12 -> 319
    //   310: ldc -101
    //   312: ldc_w 2227
    //   315: invokestatic 2230	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   318: pop
    //   319: aload_0
    //   320: getfield 1052	com/mojang/minecraftpe/MainActivity:minecraftVersion	Lnet/zhuoweizhang/mcpelauncher/MinecraftVersion;
    //   323: putstatic 2231	net/zhuoweizhang/mcpelauncher/patch/PatchUtils:minecraftVersion	Lnet/zhuoweizhang/mcpelauncher/MinecraftVersion;
    //   326: aload_0
    //   327: getfield 343	com/mojang/minecraftpe/MainActivity:mcPkgInfo	Landroid/content/pm/PackageInfo;
    //   330: getfield 920	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   333: ldc_w 938
    //   336: invokevirtual 842	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   339: ifne +909 -> 1248
    //   342: aload_0
    //   343: getfield 343	com/mojang/minecraftpe/MainActivity:mcPkgInfo	Landroid/content/pm/PackageInfo;
    //   346: getfield 920	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   349: ldc_w 940
    //   352: invokevirtual 632	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   355: ifeq +731 -> 1086
    //   358: goto +890 -> 1248
    //   361: aload_0
    //   362: getfield 343	com/mojang/minecraftpe/MainActivity:mcPkgInfo	Landroid/content/pm/PackageInfo;
    //   365: getfield 920	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   368: ldc -104
    //   370: invokevirtual 842	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   373: ifeq +7 -> 380
    //   376: iload_2
    //   377: ifeq +876 -> 1253
    //   380: aload_0
    //   381: getfield 343	com/mojang/minecraftpe/MainActivity:mcPkgInfo	Landroid/content/pm/PackageInfo;
    //   384: getfield 920	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   387: ldc 125
    //   389: invokevirtual 842	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   392: ifeq +699 -> 1091
    //   395: goto +858 -> 1253
    //   398: iload_2
    //   399: ifne +66 -> 465
    //   402: new 561	android/content/Intent
    //   405: dup
    //   406: aload_0
    //   407: ldc_w 2233
    //   410: invokespecial 566	android/content/Intent:<init>	(Landroid/content/Context;Ljava/lang/Class;)V
    //   413: astore 7
    //   415: aload 7
    //   417: ldc_w 2234
    //   420: aload_0
    //   421: getfield 343	com/mojang/minecraftpe/MainActivity:mcPkgInfo	Landroid/content/pm/PackageInfo;
    //   424: getfield 920	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   427: invokevirtual 597	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   430: pop
    //   431: aload 7
    //   433: ldc_w 2236
    //   436: ldc_w 2238
    //   439: invokevirtual 597	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   442: pop
    //   443: aload_0
    //   444: aload 7
    //   446: invokevirtual 605	com/mojang/minecraftpe/MainActivity:startActivity	(Landroid/content/Intent;)V
    //   449: aload_0
    //   450: invokevirtual 608	com/mojang/minecraftpe/MainActivity:finish	()V
    //   453: ldc2_w 609
    //   456: invokestatic 616	java/lang/Thread:sleep	(J)V
    //   459: invokestatic 654	android/os/Process:myPid	()I
    //   462: invokestatic 657	android/os/Process:killProcess	(I)V
    //   465: aload_0
    //   466: invokespecial 2240	com/mojang/minecraftpe/MainActivity:checkForSubstrate	()V
    //   469: aload_0
    //   470: invokespecial 2242	com/mojang/minecraftpe/MainActivity:fixMyEpicFail	()V
    //   473: aload_0
    //   474: invokevirtual 2244	com/mojang/minecraftpe/MainActivity:migrateToPatchManager	()V
    //   477: iconst_1
    //   478: invokestatic 791	net/zhuoweizhang/mcpelauncher/Utils:getPrefs	(I)Landroid/content/SharedPreferences;
    //   481: astore 7
    //   483: iload_3
    //   484: istore_2
    //   485: aload 7
    //   487: ldc_w 1019
    //   490: iconst_m1
    //   491: invokeinterface 799 3 0
    //   496: iload 4
    //   498: if_icmpeq +39 -> 537
    //   501: getstatic 513	java/lang/System:out	Ljava/io/PrintStream;
    //   504: ldc_w 2246
    //   507: invokevirtual 1023	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   510: aload 7
    //   512: invokeinterface 809 1 0
    //   517: ldc_w 1007
    //   520: iconst_1
    //   521: invokeinterface 817 3 0
    //   526: invokeinterface 820 1 0
    //   531: aload_0
    //   532: invokespecial 2247	com/mojang/minecraftpe/MainActivity:disableAllPatches	()V
    //   535: iconst_1
    //   536: istore_2
    //   537: aload 7
    //   539: ldc_w 2249
    //   542: iconst_m1
    //   543: invokeinterface 799 3 0
    //   548: iload 4
    //   550: if_icmpeq +77 -> 627
    //   553: iconst_0
    //   554: invokestatic 791	net/zhuoweizhang/mcpelauncher/Utils:getPrefs	(I)Landroid/content/SharedPreferences;
    //   557: invokeinterface 809 1 0
    //   562: ldc_w 2081
    //   565: iconst_0
    //   566: invokeinterface 817 3 0
    //   571: invokeinterface 820 1 0
    //   576: aload 7
    //   578: invokeinterface 809 1 0
    //   583: ldc_w 2249
    //   586: iload 4
    //   588: invokeinterface 824 3 0
    //   593: invokeinterface 820 1 0
    //   598: aload 7
    //   600: ldc_w 2251
    //   603: ldc_w 293
    //   606: invokeinterface 1521 3 0
    //   611: ldc_w 2253
    //   614: invokevirtual 1790	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   617: iflt +10 -> 627
    //   620: aload_0
    //   621: sipush 4102
    //   624: invokevirtual 1071	com/mojang/minecraftpe/MainActivity:showDialog	(I)V
    //   627: aload_0
    //   628: invokevirtual 802	com/mojang/minecraftpe/MainActivity:getPackageName	()Ljava/lang/String;
    //   631: ldc_w 2204
    //   634: invokevirtual 632	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   637: ifeq +498 -> 1135
    //   640: aload_0
    //   641: aload_0
    //   642: putfield 1727	com/mojang/minecraftpe/MainActivity:minecraftApkContext	Landroid/content/Context;
    //   645: invokestatic 2256	net/zhuoweizhang/mcpelauncher/Utils:setLanguageOverride	()V
    //   648: aload_0
    //   649: new 451	java/io/File
    //   652: dup
    //   653: ldc_w 2258
    //   656: invokespecial 453	java/io/File:<init>	(Ljava/lang/String;)V
    //   659: invokevirtual 666	java/io/File:exists	()Z
    //   662: putfield 283	com/mojang/minecraftpe/MainActivity:forceFallback	Z
    //   665: aload_0
    //   666: getfield 289	com/mojang/minecraftpe/MainActivity:textureOverrides	Ljava/util/List;
    //   669: invokeinterface 2261 1 0
    //   674: aload_0
    //   675: invokevirtual 2263	com/mojang/minecraftpe/MainActivity:loadTexturePack	()V
    //   678: aload_0
    //   679: invokevirtual 2265	com/mojang/minecraftpe/MainActivity:allowScriptOverrideTextures	()Z
    //   682: ifeq +21 -> 703
    //   685: aload_0
    //   686: getfield 289	com/mojang/minecraftpe/MainActivity:textureOverrides	Ljava/util/List;
    //   689: new 2267	net/zhuoweizhang/mcpelauncher/ScriptOverrideTexturePack
    //   692: dup
    //   693: aload_0
    //   694: invokespecial 2268	net/zhuoweizhang/mcpelauncher/ScriptOverrideTexturePack:<init>	(Landroid/content/Context;)V
    //   697: invokeinterface 1084 2 0
    //   702: pop
    //   703: aload_0
    //   704: invokestatic 2273	net/zhuoweizhang/mcpelauncher/ScriptTextureDownloader:attachCache	(Landroid/content/Context;)V
    //   707: aload_0
    //   708: aload_0
    //   709: invokevirtual 2275	com/mojang/minecraftpe/MainActivity:doesRequireGuiBlocksPatch	()Z
    //   712: putfield 285	com/mojang/minecraftpe/MainActivity:requiresGuiBlocksPatch	Z
    //   715: invokestatic 848	net/zhuoweizhang/mcpelauncher/Utils:isSafeMode	()Z
    //   718: ifne +19 -> 737
    //   721: iconst_0
    //   722: invokestatic 791	net/zhuoweizhang/mcpelauncher/Utils:getPrefs	(I)Landroid/content/SharedPreferences;
    //   725: ldc_w 2277
    //   728: iconst_1
    //   729: invokeinterface 956 3 0
    //   734: ifne +15 -> 749
    //   737: aload_0
    //   738: invokevirtual 838	com/mojang/minecraftpe/MainActivity:getMCPEVersion	()Ljava/lang/String;
    //   741: ldc 125
    //   743: invokevirtual 842	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   746: ifeq +7 -> 753
    //   749: aload_0
    //   750: invokespecial 2279	com/mojang/minecraftpe/MainActivity:prePatch	()V
    //   753: new 539	java/lang/StringBuilder
    //   756: dup
    //   757: invokespecial 540	java/lang/StringBuilder:<init>	()V
    //   760: aload_0
    //   761: getfield 1001	com/mojang/minecraftpe/MainActivity:mcAppInfo	Landroid/content/pm/ApplicationInfo;
    //   764: getfield 549	android/content/pm/ApplicationInfo:nativeLibraryDir	Ljava/lang/String;
    //   767: invokevirtual 553	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   770: ldc_w 2281
    //   773: invokevirtual 553	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   776: invokevirtual 559	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   779: invokestatic 688	java/lang/System:load	(Ljava/lang/String;)V
    //   782: getstatic 253	com/mojang/minecraftpe/MainActivity:MC_NATIVE_LIBRARY_LOCATION	Ljava/lang/String;
    //   785: invokestatic 688	java/lang/System:load	(Ljava/lang/String;)V
    //   788: aload_0
    //   789: invokestatic 2285	org/fmod/FMOD:init	(Landroid/content/Context;)V
    //   792: iconst_1
    //   793: putstatic 257	com/mojang/minecraftpe/MainActivity:libLoaded	Z
    //   796: invokestatic 848	net/zhuoweizhang/mcpelauncher/Utils:isSafeMode	()Z
    //   799: ifeq +10 -> 809
    //   802: aload_0
    //   803: invokespecial 2287	com/mojang/minecraftpe/MainActivity:requiresPatchingInSafeMode	()Z
    //   806: ifeq +83 -> 889
    //   809: aload_0
    //   810: invokevirtual 2289	com/mojang/minecraftpe/MainActivity:initPatching	()V
    //   813: getstatic 2291	com/mojang/minecraftpe/MainActivity:minecraftLibBuffer	Ljava/nio/ByteBuffer;
    //   816: ifnull +73 -> 889
    //   819: iconst_0
    //   820: invokestatic 791	net/zhuoweizhang/mcpelauncher/Utils:getPrefs	(I)Landroid/content/SharedPreferences;
    //   823: ldc_w 2293
    //   826: iconst_0
    //   827: invokeinterface 956 3 0
    //   832: istore 6
    //   834: aload_0
    //   835: invokevirtual 2295	com/mojang/minecraftpe/MainActivity:hasScriptSupport	()Z
    //   838: ifne +357 -> 1195
    //   841: iconst_1
    //   842: istore 5
    //   844: iload 6
    //   846: aload_0
    //   847: iload 5
    //   849: invokestatic 2299	net/zhuoweizhang/mcpelauncher/ScriptManager:nativePrePatch	(ZLcom/mojang/minecraftpe/MainActivity;Z)V
    //   852: aload_0
    //   853: invokevirtual 2295	com/mojang/minecraftpe/MainActivity:hasScriptSupport	()Z
    //   856: ifeq +23 -> 879
    //   859: iconst_0
    //   860: invokestatic 791	net/zhuoweizhang/mcpelauncher/Utils:getPrefs	(I)Landroid/content/SharedPreferences;
    //   863: ldc_w 2301
    //   866: iconst_0
    //   867: invokeinterface 956 3 0
    //   872: ifeq +7 -> 879
    //   875: iconst_1
    //   876: invokestatic 2304	net/zhuoweizhang/mcpelauncher/ScriptManager:nativeModPESetDesktopGui	(Z)V
    //   879: invokestatic 848	net/zhuoweizhang/mcpelauncher/Utils:isSafeMode	()Z
    //   882: ifne +7 -> 889
    //   885: aload_0
    //   886: invokevirtual 2306	com/mojang/minecraftpe/MainActivity:loadNativeAddons	()V
    //   889: aload_0
    //   890: invokevirtual 2295	com/mojang/minecraftpe/MainActivity:hasScriptSupport	()Z
    //   893: istore 5
    //   895: invokestatic 848	net/zhuoweizhang/mcpelauncher/Utils:isSafeMode	()Z
    //   898: ifne +51 -> 949
    //   901: getstatic 2291	com/mojang/minecraftpe/MainActivity:minecraftLibBuffer	Ljava/nio/ByteBuffer;
    //   904: ifnull +45 -> 949
    //   907: aload_0
    //   908: invokevirtual 2168	com/mojang/minecraftpe/MainActivity:applyBuiltinPatches	()V
    //   911: iload 5
    //   913: ifeq +36 -> 949
    //   916: iconst_0
    //   917: invokestatic 791	net/zhuoweizhang/mcpelauncher/Utils:getPrefs	(I)Landroid/content/SharedPreferences;
    //   920: ldc_w 2308
    //   923: iconst_1
    //   924: invokeinterface 956 3 0
    //   929: ifeq +20 -> 949
    //   932: aload_0
    //   933: invokestatic 2309	net/zhuoweizhang/mcpelauncher/ScriptManager:init	(Landroid/content/Context;)V
    //   936: aload_0
    //   937: getfield 289	com/mojang/minecraftpe/MainActivity:textureOverrides	Ljava/util/List;
    //   940: getstatic 2313	net/zhuoweizhang/mcpelauncher/ScriptManager:modPkgTexturePack	Lnet/zhuoweizhang/mcpelauncher/texture/ModPkgTexturePack;
    //   943: invokeinterface 1084 2 0
    //   948: pop
    //   949: invokestatic 848	net/zhuoweizhang/mcpelauncher/Utils:isSafeMode	()Z
    //   952: ifne +8 -> 960
    //   955: iload 5
    //   957: ifne +7 -> 964
    //   960: aload_0
    //   961: invokestatic 2316	net/zhuoweizhang/mcpelauncher/ScriptManager:loadEnabledScriptsNames	(Landroid/content/Context;)V
    //   964: iload_2
    //   965: ifeq +6 -> 971
    //   968: invokestatic 2319	net/zhuoweizhang/mcpelauncher/ScriptManager:clearTextureOverrides	()V
    //   971: aload_0
    //   972: invokespecial 2321	com/mojang/minecraftpe/MainActivity:initAtlasMeta	()V
    //   975: aload_0
    //   976: new 1719	android/util/DisplayMetrics
    //   979: dup
    //   980: invokespecial 2322	android/util/DisplayMetrics:<init>	()V
    //   983: putfield 1717	com/mojang/minecraftpe/MainActivity:displayMetrics	Landroid/util/DisplayMetrics;
    //   986: aload_0
    //   987: iconst_3
    //   988: invokevirtual 2325	com/mojang/minecraftpe/MainActivity:setVolumeControlStream	(I)V
    //   991: aload_0
    //   992: invokevirtual 2329	com/mojang/minecraftpe/MainActivity:getWindowManager	()Landroid/view/WindowManager;
    //   995: invokeinterface 2335 1 0
    //   1000: aload_0
    //   1001: getfield 1717	com/mojang/minecraftpe/MainActivity:displayMetrics	Landroid/util/DisplayMetrics;
    //   1004: invokevirtual 2341	android/view/Display:getMetrics	(Landroid/util/DisplayMetrics;)V
    //   1007: aload_0
    //   1008: getstatic 249	com/mojang/minecraftpe/MainActivity:MC_NATIVE_LIBRARY_DIR	Ljava/lang/String;
    //   1011: invokespecial 2343	com/mojang/minecraftpe/MainActivity:addLibraryDirToPath	(Ljava/lang/String;)V
    //   1014: aload_0
    //   1015: iconst_1
    //   1016: invokespecial 2345	com/mojang/minecraftpe/MainActivity:setFakePackage	(Z)V
    //   1019: aload_0
    //   1020: aload_1
    //   1021: invokespecial 2347	android/app/NativeActivity:onCreate	(Landroid/os/Bundle;)V
    //   1024: aload_0
    //   1025: invokevirtual 2349	com/mojang/minecraftpe/MainActivity:nativeRegisterThis	()V
    //   1028: aload_0
    //   1029: iconst_0
    //   1030: invokespecial 2345	com/mojang/minecraftpe/MainActivity:setFakePackage	(Z)V
    //   1033: aload_0
    //   1034: iconst_1
    //   1035: invokestatic 2353	net/zhuoweizhang/mcpelauncher/Utils:setupTheme	(Landroid/content/Context;Z)V
    //   1038: aload_0
    //   1039: invokespecial 2355	com/mojang/minecraftpe/MainActivity:disableTransparentSystemBar	()V
    //   1042: new 2357	java/net/CookieManager
    //   1045: dup
    //   1046: invokespecial 2358	java/net/CookieManager:<init>	()V
    //   1049: invokestatic 2364	java/net/CookieHandler:setDefault	(Ljava/net/CookieHandler;)V
    //   1052: invokestatic 848	net/zhuoweizhang/mcpelauncher/Utils:isSafeMode	()Z
    //   1055: ifeq +17 -> 1072
    //   1058: aload_0
    //   1059: getfield 304	com/mojang/minecraftpe/MainActivity:overlyZealousSELinuxSafeMode	Z
    //   1062: ifeq +171 -> 1233
    //   1065: aload_0
    //   1066: sipush 4106
    //   1069: invokevirtual 1071	com/mojang/minecraftpe/MainActivity:showDialog	(I)V
    //   1072: aload_0
    //   1073: invokevirtual 2366	com/mojang/minecraftpe/MainActivity:initKamcord	()V
    //   1076: invokestatic 2369	java/lang/System:gc	()V
    //   1079: return
    //   1080: iconst_0
    //   1081: istore 5
    //   1083: goto -832 -> 251
    //   1086: iconst_0
    //   1087: istore_2
    //   1088: goto -727 -> 361
    //   1091: iconst_0
    //   1092: istore_2
    //   1093: goto -695 -> 398
    //   1096: astore_1
    //   1097: aload_1
    //   1098: invokevirtual 2370	android/content/pm/PackageManager$NameNotFoundException:printStackTrace	()V
    //   1101: aload_0
    //   1102: invokevirtual 608	com/mojang/minecraftpe/MainActivity:finish	()V
    //   1105: aload_0
    //   1106: new 561	android/content/Intent
    //   1109: dup
    //   1110: aload_0
    //   1111: ldc_w 563
    //   1114: invokespecial 566	android/content/Intent:<init>	(Landroid/content/Context;Ljava/lang/Class;)V
    //   1117: invokevirtual 605	com/mojang/minecraftpe/MainActivity:startActivity	(Landroid/content/Intent;)V
    //   1120: ldc2_w 647
    //   1123: invokestatic 616	java/lang/Thread:sleep	(J)V
    //   1126: invokestatic 654	android/os/Process:myPid	()I
    //   1129: invokestatic 657	android/os/Process:killProcess	(I)V
    //   1132: return
    //   1133: astore_1
    //   1134: return
    //   1135: aload_0
    //   1136: aload_0
    //   1137: ldc_w 2204
    //   1140: iconst_2
    //   1141: invokevirtual 2374	com/mojang/minecraftpe/MainActivity:createPackageContext	(Ljava/lang/String;I)Landroid/content/Context;
    //   1144: putfield 1727	com/mojang/minecraftpe/MainActivity:minecraftApkContext	Landroid/content/Context;
    //   1147: goto -502 -> 645
    //   1150: astore 7
    //   1152: aload 7
    //   1154: invokevirtual 522	java/lang/Exception:printStackTrace	()V
    //   1157: aload_0
    //   1158: ldc_w 2376
    //   1161: iconst_1
    //   1162: invokestatic 2382	android/widget/Toast:makeText	(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
    //   1165: invokevirtual 2384	android/widget/Toast:show	()V
    //   1168: aload_0
    //   1169: invokevirtual 608	com/mojang/minecraftpe/MainActivity:finish	()V
    //   1172: goto -527 -> 645
    //   1175: astore 7
    //   1177: aload 7
    //   1179: invokevirtual 522	java/lang/Exception:printStackTrace	()V
    //   1182: goto -429 -> 753
    //   1185: astore_1
    //   1186: new 690	java/lang/RuntimeException
    //   1189: dup
    //   1190: aload_1
    //   1191: invokespecial 693	java/lang/RuntimeException:<init>	(Ljava/lang/Throwable;)V
    //   1194: athrow
    //   1195: iconst_0
    //   1196: istore 5
    //   1198: goto -354 -> 844
    //   1201: astore 7
    //   1203: aload 7
    //   1205: invokevirtual 522	java/lang/Exception:printStackTrace	()V
    //   1208: aload_0
    //   1209: aload 7
    //   1211: invokevirtual 908	com/mojang/minecraftpe/MainActivity:reportError	(Ljava/lang/Throwable;)V
    //   1214: goto -325 -> 889
    //   1217: astore 7
    //   1219: aload 7
    //   1221: invokevirtual 522	java/lang/Exception:printStackTrace	()V
    //   1224: aload_0
    //   1225: aload 7
    //   1227: invokevirtual 908	com/mojang/minecraftpe/MainActivity:reportError	(Ljava/lang/Throwable;)V
    //   1230: goto -266 -> 964
    //   1233: aload_0
    //   1234: sipush 4096
    //   1237: invokevirtual 1071	com/mojang/minecraftpe/MainActivity:showDialog	(I)V
    //   1240: goto -168 -> 1072
    //   1243: astore 7
    //   1245: goto -780 -> 465
    //   1248: iconst_1
    //   1249: istore_2
    //   1250: goto -889 -> 361
    //   1253: iconst_1
    //   1254: istore_2
    //   1255: goto -857 -> 398
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1258	0	this	MainActivity
    //   0	1258	1	paramBundle	Bundle
    //   52	1203	2	i	int
    //   24	460	3	j	int
    //   264	323	4	k	int
    //   249	948	5	bool1	boolean
    //   832	13	6	bool2	boolean
    //   413	186	7	localObject	Object
    //   1150	3	7	localException1	Exception
    //   1175	3	7	localException2	Exception
    //   1201	9	7	localException3	Exception
    //   1217	9	7	localException4	Exception
    //   1243	1	7	localThrowable	Throwable
    // Exception table:
    //   from	to	target	type
    //   135	248	1096	android/content/pm/PackageManager$NameNotFoundException
    //   251	300	1096	android/content/pm/PackageManager$NameNotFoundException
    //   300	319	1096	android/content/pm/PackageManager$NameNotFoundException
    //   319	358	1096	android/content/pm/PackageManager$NameNotFoundException
    //   361	376	1096	android/content/pm/PackageManager$NameNotFoundException
    //   380	395	1096	android/content/pm/PackageManager$NameNotFoundException
    //   402	453	1096	android/content/pm/PackageManager$NameNotFoundException
    //   453	465	1096	android/content/pm/PackageManager$NameNotFoundException
    //   465	483	1096	android/content/pm/PackageManager$NameNotFoundException
    //   485	535	1096	android/content/pm/PackageManager$NameNotFoundException
    //   537	627	1096	android/content/pm/PackageManager$NameNotFoundException
    //   1120	1132	1133	java/lang/Throwable
    //   627	645	1150	java/lang/Exception
    //   1135	1147	1150	java/lang/Exception
    //   715	737	1175	java/lang/Exception
    //   737	749	1175	java/lang/Exception
    //   749	753	1175	java/lang/Exception
    //   753	788	1185	java/lang/Exception
    //   796	809	1201	java/lang/Exception
    //   809	841	1201	java/lang/Exception
    //   844	879	1201	java/lang/Exception
    //   879	889	1201	java/lang/Exception
    //   889	911	1217	java/lang/Exception
    //   916	949	1217	java/lang/Exception
    //   949	955	1217	java/lang/Exception
    //   960	964	1217	java/lang/Exception
    //   453	465	1243	java/lang/Throwable
  }
  
  public Dialog onCreateDialog(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return super.onCreateDialog(paramInt);
    case 1: 
      return createCreateWorldDialog();
    case 4: 
      return createCopyWorldDialog();
    case 4096: 
      return createSafeModeDialog(R.string.manage_patches_crash_safe_mode);
    case 4097: 
      return createRuntimeOptionsDialog(false);
    case 4098: 
      return createInvalidPatchesDialog();
    case 4099: 
      return createFirstLaunchDialog();
    case 4100: 
      return createSafeModeDialog(R.string.version_mismatch_message);
    case 4101: 
      return createNotSupportedDialog();
    case 4102: 
      return createUpdateTexturePackDialog();
    case 4103: 
      return createInsertTextDialog();
    case 4104: 
      return createMultiplayerDisableScriptsDialog();
    case 4105: 
      return createRuntimeOptionsDialog(true);
    }
    return createSELinuxBrokeEverythingDialog();
  }
  
  public void onDestroy()
  {
    nativeUnregisterThis();
    super.onDestroy();
    File localFile = new File(getFilesDir(), "running.lock");
    if (localFile.exists()) {
      localFile.delete();
    }
    if (this.hoverCar != null)
    {
      this.hoverCar.dismiss();
      this.hoverCar = null;
    }
    ScriptManager.destroy();
    System.exit(0);
  }
  
  protected void onPause()
  {
    nativeSuspend();
    super.onPause();
    Utils.getPrefs(2).edit().putInt("safe_mode_counter", 0).commit();
    this.hasResetSafeModeCounter = true;
    hideKeyboardView();
  }
  
  protected void onResume()
  {
    super.onResume();
    if (this.hasResetSafeModeCounter)
    {
      int i = Utils.getPrefs(2).getInt("safe_mode_counter", 0);
      Utils.getPrefs(2).edit().putInt("safe_mode_counter", i + 1).commit();
    }
    if (this.hoverCar == null)
    {
      getWindow().getDecorView().post(new Runnable()
      {
        public void run()
        {
          try
          {
            MainActivity.this.setupHoverCar();
            return;
          }
          catch (Exception localException)
          {
            localException.printStackTrace();
          }
        }
      });
      setImmersiveMode(Utils.getPrefs(0).getBoolean("zz_immersive_mode", false));
      return;
    }
    HoverCar localHoverCar = this.hoverCar;
    if (!Utils.getPrefs(0).getBoolean("zz_hovercar_hide", false)) {}
    for (boolean bool = true;; bool = false)
    {
      localHoverCar.setVisible(bool);
      break;
    }
  }
  
  public void onStop()
  {
    nativeStopThis();
    super.onStop();
    ScriptTextureDownloader.flushCache();
    System.gc();
  }
  
  public void onWindowFocusChanged(boolean paramBoolean)
  {
    super.onWindowFocusChanged(paramBoolean);
    if (paramBoolean) {
      touchImmersiveMode();
    }
  }
  
  protected InputStream openFallbackAsset(String paramString)
    throws IOException
  {
    if (getMCPEVersion().startsWith("1.0")) {
      try
      {
        InputStream localInputStream = getAssets().open("1007/" + paramString);
        return localInputStream;
      }
      catch (IOException localIOException)
      {
        if (this.textureVerbose) {
          System.err.println(localIOException);
        }
      }
    }
    return getAssets().open(paramString);
  }
  
  public void openLoginWindow()
  {
    Log.i("BlockLauncher/Main", "Open login window");
    runOnUiThread(new Runnable()
    {
      @SuppressLint({"SetJavaScriptEnabled"})
      public void run()
      {
        MainActivity.access$502(MainActivity.this, new WebView(MainActivity.this));
        MainActivity.this.loginWebView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        MainActivity.this.loginWebView.setWebViewClient(new MainActivity.LoginWebViewClient(MainActivity.this, null));
        MainActivity.this.loginWebView.getSettings().setJavaScriptEnabled(true);
        MainActivity.access$702(MainActivity.this, new Dialog(MainActivity.this));
        MainActivity.this.loginDialog.setCancelable(true);
        MainActivity.this.loginDialog.requestWindowFeature(1);
        MainActivity.this.loginDialog.setContentView(MainActivity.this.loginWebView);
        MainActivity.this.loginDialog.getWindow().setLayout(-1, -1);
        MainActivity.this.loginDialog.show();
        MainActivity.this.loginWebView.loadUrl(MainActivity.this.getRealmsRedirectInfo().loginUrl);
      }
    });
  }
  
  public void pickImage(long paramLong)
  {
    this.pickImageCallbackAddress = paramLong;
    startActivityForResult(new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI), 415);
  }
  
  public void postScreenshotToFacebook(String paramString, int paramInt1, int paramInt2, int[] paramArrayOfInt) {}
  
  public void quit()
  {
    finish();
  }
  
  public void reportError(Throwable paramThrowable)
  {
    reportError(paramThrowable, R.string.report_error_title, null);
  }
  
  public void reportError(final Throwable paramThrowable, final int paramInt, final String paramString)
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        Object localObject = new StringWriter();
        PrintWriter localPrintWriter = new PrintWriter((Writer)localObject);
        paramThrowable.printStackTrace(localPrintWriter);
        if (paramString != null) {}
        for (localObject = paramString + "\n" + ((StringWriter)localObject).toString();; localObject = ((StringWriter)localObject).toString())
        {
          new AlertDialog.Builder(MainActivity.this).setTitle(paramInt).setMessage((CharSequence)localObject).setPositiveButton(17039370, null).setNeutralButton(17039361, new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
            {
              ((ClipboardManager)MainActivity.this.getSystemService("clipboard")).setText(this.val$msg);
            }
          }).show();
          return;
        }
      }
    });
  }
  
  public void reportReimported(final String paramString)
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        Toast.makeText(MainActivity.this, MainActivity.this.getResources().getString(R.string.manage_scripts_reimported_toast) + " " + paramString, 0).show();
      }
    });
  }
  
  protected void resetOrientation() {}
  
  public void screenshotCallback(final File paramFile)
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        Toast.makeText(MainActivity.this, MainActivity.this.getResources().getString(R.string.screenshot_saved_as) + " " + paramFile.getAbsolutePath(), 1).show();
        MediaScannerConnection.scanFile(MainActivity.this, new String[] { paramFile.getAbsolutePath() }, new String[] { "image/png" }, null);
      }
    });
  }
  
  public void scriptErrorCallback(final String paramString, final Throwable paramThrowable)
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        final StringWriter localStringWriter = new StringWriter();
        PrintWriter localPrintWriter = new PrintWriter(localStringWriter);
        localPrintWriter.println("Error occurred in script: " + paramString);
        if ((paramThrowable instanceof RhinoException))
        {
          String str = ((RhinoException)paramThrowable).lineSource();
          if (str != null) {
            localPrintWriter.println(str);
          }
        }
        paramThrowable.printStackTrace(localPrintWriter);
        new AlertDialog.Builder(MainActivity.this).setTitle(R.string.script_execution_error).setMessage(localStringWriter.toString()).setPositiveButton(17039370, null).setNeutralButton(17039361, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            ((ClipboardManager)MainActivity.this.getSystemService("clipboard")).setText(localStringWriter.toString());
          }
        }).show();
      }
    });
  }
  
  public void scriptOverrideTexture(String paramString1, String paramString2)
  {
    forceTextureReload();
  }
  
  public void scriptPrintCallback(final String paramString1, final String paramString2)
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        Toast.makeText(MainActivity.this, "Script " + paramString2 + ": " + paramString1, 0).show();
      }
    });
  }
  
  public void scriptResetImages()
  {
    forceTextureReload();
  }
  
  public void scriptTooManyErrorsCallback(final String paramString)
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        new AlertDialog.Builder(MainActivity.this).setTitle(R.string.script_execution_error).setMessage(paramString + " " + MainActivity.this.getResources().getString(R.string.script_too_many_errors)).setPositiveButton(17039370, null).show();
      }
    });
  }
  
  public void setClipboard(String paramString)
  {
    ((ClipboardManager)getSystemService("clipboard")).setText(paramString);
  }
  
  public void setFileDialogCallback(long paramLong) {}
  
  public void setIsPowerVR(boolean paramBoolean)
  {
    System.out.println("PowerVR: " + paramBoolean);
  }
  
  public void setLevelCallback(boolean paramBoolean)
  {
    System.out.println("Set level callback: " + paramBoolean);
    if ((paramBoolean) && (ScriptManager.scripts.size() > 0)) {
      runOnUiThread(new Runnable()
      {
        public void run()
        {
          MainActivity.this.showDialog(4104);
        }
      });
    }
    if (this.hasRecorder) {
      clearRuntimeOptionsDialog();
    }
  }
  
  public void setLoginInformation(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    Utils.getPrefs(0).edit().putString("accessToken", paramString1).putString("clientId", paramString2).putString("profileUuid", paramString3).putString("profileName", paramString4).apply();
  }
  
  public void setRefreshToken(String paramString)
  {
    Utils.getPrefs(0).edit().putString("refreshToken", paramString).apply();
  }
  
  public void setSession(String paramString)
  {
    Utils.getPrefs(0).edit().putString("sessionId", paramString).apply();
  }
  
  public void setTextToSpeechEnabled(boolean paramBoolean)
  {
    System.out.println("Text to speech?");
    if (paramBoolean) {
      if (this.tts == null) {
        this.tts = new TextToSpeech(this, null);
      }
    }
    while (this.tts == null) {
      return;
    }
    this.tts.shutdown();
    this.tts = null;
  }
  
  protected void setupHoverCar()
  {
    boolean bool = false;
    this.hoverCar = new HoverCar(this, Utils.isSafeMode());
    this.hoverCar.show(getWindow().getDecorView());
    HoverCar localHoverCar = this.hoverCar;
    if (!Utils.getPrefs(0).getBoolean("zz_hovercar_hide", false)) {
      bool = true;
    }
    localHoverCar.setVisible(bool);
    this.hoverCar.mainButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        boolean bool = Utils.getPrefs(0).getBoolean("zz_show_insert_text", false);
        paramAnonymousView = MainActivity.this;
        if (bool) {}
        for (int i = 4105;; i = 4097)
        {
          paramAnonymousView.showDialog(i);
          MainActivity.this.resetOrientation();
          return;
        }
      }
    });
  }
  
  public void showHiddenTextbox(String paramString, int paramInt, boolean paramBoolean)
  {
    boolean bool = isCommandHistoryEnabled();
    Object localObject;
    if (this.hiddenTextWindow == null)
    {
      if (!bool) {
        break label329;
      }
      this.commandHistoryView = getLayoutInflater().inflate(R.layout.chat_history_popup, null);
      this.hiddenTextView = ((TextView)this.commandHistoryView.findViewById(R.id.hidden_text_view));
      this.prevButton = ((Button)this.commandHistoryView.findViewById(R.id.command_history_previous));
      this.nextButton = ((Button)this.commandHistoryView.findViewById(R.id.command_history_next));
      localObject = new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (paramAnonymousView == MainActivity.this.prevButton) {
            MainActivity.this.navigateCommandHistory(-1);
          }
          while (paramAnonymousView != MainActivity.this.nextButton) {
            return;
          }
          MainActivity.this.navigateCommandHistory(1);
        }
      };
      this.prevButton.setOnClickListener((View.OnClickListener)localObject);
      this.nextButton.setOnClickListener((View.OnClickListener)localObject);
      localObject = new PopupTextWatcher(null);
      this.hiddenTextView.addTextChangedListener((TextWatcher)localObject);
      this.hiddenTextView.setOnEditorActionListener((TextView.OnEditorActionListener)localObject);
      this.hiddenTextView.setSingleLine(true);
      this.hiddenTextView.setImeOptions(301989893);
      this.hiddenTextView.setInputType(1);
      if (!bool) {
        break label344;
      }
    }
    for (this.hiddenTextWindow = new PopupWindow(this.commandHistoryView);; this.hiddenTextWindow = new PopupWindow((View)localObject))
    {
      this.hiddenTextWindow.setWindowLayoutMode(-2, -2);
      this.hiddenTextWindow.setFocusable(true);
      this.hiddenTextWindow.setInputMethodMode(1);
      this.hiddenTextWindow.setBackgroundDrawable(new ColorDrawable());
      this.hiddenTextWindow.setClippingEnabled(false);
      this.hiddenTextWindow.setTouchable(bool);
      this.hiddenTextWindow.setOutsideTouchable(true);
      this.hiddenTextWindow.setOnDismissListener(new PopupWindow.OnDismissListener()
      {
        public void onDismiss()
        {
          MainActivity.this.nativeBackPressed();
        }
      });
      if (!bool) {
        break label405;
      }
      paramInt = this.commandHistoryList.size() - 1;
      while (paramInt >= 0)
      {
        if (((String)this.commandHistoryList.get(paramInt)).equals("")) {
          this.commandHistoryList.remove(paramInt);
        }
        paramInt -= 1;
      }
      label329:
      this.hiddenTextView = new EditText(this);
      break;
      label344:
      localObject = new LinearLayout(this);
      ((LinearLayout)localObject).addView(this.hiddenTextView);
    }
    this.commandHistoryList.add(paramString);
    setCommandHistoryIndex(this.commandHistoryList.size() - 1);
    label405:
    this.hiddenTextView.setText(paramString);
    Selection.setSelection((Spannable)this.hiddenTextView.getText(), paramString.length());
    this.hiddenTextDismissAfterOneLine = paramBoolean;
    if (bool) {}
    for (paramInt = 0;; paramInt = 55536)
    {
      this.hiddenTextWindow.showAtLocation(getWindow().getDecorView(), 51, paramInt, 0);
      this.hiddenTextView.requestFocus();
      showKeyboardView();
      return;
    }
  }
  
  public void showKeyboard(String paramString, int paramInt, boolean paramBoolean)
  {
    showKeyboard(paramString, paramInt, paramBoolean, false);
  }
  
  public void showKeyboard(final String paramString, final int paramInt, final boolean paramBoolean1, boolean paramBoolean2)
  {
    if (useLegacyKeyboardInput())
    {
      showKeyboardView();
      return;
    }
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        MainActivity.this.showHiddenTextbox(paramString, paramInt, paramBoolean1);
      }
    });
  }
  
  public void showKeyboardView()
  {
    Log.i("BlockLauncher/Main", "Show keyboard view");
    ((InputMethodManager)getSystemService("input_method")).showSoftInput(getWindow().getDecorView(), 2);
  }
  
  public void showStoreNotWorkingDialog()
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        new AlertDialog.Builder(MainActivity.this).setTitle(R.string.store_not_supported_title).setMessage(R.string.store_not_supported_message).setPositiveButton(17039370, null).show();
      }
    });
  }
  
  public void startTextToSpeech(String paramString)
  {
    System.out.println("Text to speech: " + paramString);
    if (this.tts != null) {
      this.tts.speak(paramString, 1, null);
    }
  }
  
  public void statsTrackEvent(String paramString1, String paramString2)
  {
    Log.i("BlockLauncher/Main", "Stats track: " + paramString1 + ":" + paramString2);
  }
  
  public void statsUpdateUserData(String paramString1, String paramString2)
  {
    Log.i("BlockLauncher/Main", "Stats update user data: " + paramString1 + ":" + paramString2);
  }
  
  public void stopTextToSpeech()
  {
    System.out.println("Shutting up");
    if (this.tts != null) {
      this.tts.stop();
    }
  }
  
  public boolean supportsNonTouchscreen()
  {
    if (isForcingController()) {
      if ((!this.controllerInit) && (!Utils.isSafeMode()))
      {
        ControllerManager.init();
        this.controllerInit = true;
      }
    }
    int k;
    int j;
    do
    {
      return true;
      k = 0;
      j = 0;
      String[] arrayOfString = new String[3];
      arrayOfString[0] = Build.MODEL.toLowerCase(Locale.ENGLISH);
      arrayOfString[1] = Build.DEVICE.toLowerCase(Locale.ENGLISH);
      arrayOfString[2] = Build.PRODUCT.toLowerCase(Locale.ENGLISH);
      int m = arrayOfString.length;
      int i = 0;
      while (i < m)
      {
        String str = arrayOfString[i];
        if (str.indexOf("xperia") >= 0) {
          k = 1;
        }
        if (str.indexOf("play") >= 0) {
          j = 1;
        }
        i += 1;
      }
    } while ((k != 0) && (j != 0));
    return false;
  }
  
  public void tick() {}
  
  protected void toggleRecording() {}
  
  public void updateLocalization(String paramString1, String paramString2)
  {
    System.out.println("Update localization: " + paramString1 + ":" + paramString2);
  }
  
  public void updateTextboxText(final String paramString)
  {
    if (this.hiddenTextView == null) {
      return;
    }
    this.hiddenTextView.post(new Runnable()
    {
      public void run()
      {
        if (MainActivity.this.isCommandHistoryEnabled())
        {
          if ((MainActivity.this.commandHistoryList.size() < 1) || (((String)MainActivity.this.commandHistoryList.get(MainActivity.this.commandHistoryList.size() - 1)).length() > 0)) {
            break label128;
          }
          MainActivity.this.commandHistoryList.set(MainActivity.this.commandHistoryList.size() - 1, paramString);
        }
        for (;;)
        {
          MainActivity.this.setCommandHistoryIndex(MainActivity.this.commandHistoryList.size() - 1);
          MainActivity.this.hiddenTextView.setText(paramString);
          return;
          label128:
          MainActivity.this.commandHistoryList.add(paramString);
        }
      }
    });
  }
  
  public void vibrate(int paramInt)
  {
    int i = paramInt;
    if (Utils.getPrefs(0).getBoolean("zz_longvibration", false)) {
      i = paramInt * 5;
    }
    ((Vibrator)getSystemService("vibrator")).vibrate(i);
  }
  
  public void webRequest(int paramInt, long paramLong, String paramString1, String paramString2, String paramString3)
  {
    webRequest(paramInt, paramLong, paramString1, paramString2, paramString3, "");
  }
  
  public void webRequest(int paramInt, long paramLong, String paramString1, String paramString2, String paramString3, String paramString4)
  {
    new Thread(new HurlRunner(paramInt, paramLong, filterUrl(paramString1), paramString2, paramString3)).start();
  }
  
  private class HurlRunner
    implements Runnable
  {
    private HttpURLConnection conn;
    private String cookies;
    private boolean isValid = true;
    private String method;
    private int requestId;
    private String strurl;
    private long timestamp;
    private URL url;
    
    public HurlRunner(int paramInt, long paramLong, String arg5, String paramString2, String paramString3)
    {
      this.requestId = paramInt;
      this.timestamp = paramLong;
      this.strurl = ???;
      this.method = paramString2;
      this.cookies = paramString3;
      synchronized (MainActivity.this.requestMap)
      {
        MainActivity.this.requestMap.put(paramInt, this);
        return;
      }
    }
    
    /* Error */
    public void run()
    {
      // Byte code:
      //   0: aconst_null
      //   1: astore 9
      //   3: aconst_null
      //   4: astore 10
      //   6: aconst_null
      //   7: astore 4
      //   9: aconst_null
      //   10: astore 8
      //   12: aconst_null
      //   13: astore 7
      //   15: iconst_0
      //   16: istore_3
      //   17: iconst_0
      //   18: istore_2
      //   19: iload_3
      //   20: istore_1
      //   21: aload 9
      //   23: astore 6
      //   25: aload 10
      //   27: astore 5
      //   29: aload_0
      //   30: new 62	java/net/URL
      //   33: dup
      //   34: aload_0
      //   35: getfield 40	com/mojang/minecraftpe/MainActivity$HurlRunner:strurl	Ljava/lang/String;
      //   38: invokespecial 65	java/net/URL:<init>	(Ljava/lang/String;)V
      //   41: putfield 67	com/mojang/minecraftpe/MainActivity$HurlRunner:url	Ljava/net/URL;
      //   44: iload_3
      //   45: istore_1
      //   46: aload 9
      //   48: astore 6
      //   50: aload 10
      //   52: astore 5
      //   54: aload_0
      //   55: aload_0
      //   56: getfield 67	com/mojang/minecraftpe/MainActivity$HurlRunner:url	Ljava/net/URL;
      //   59: invokevirtual 71	java/net/URL:openConnection	()Ljava/net/URLConnection;
      //   62: checkcast 73	java/net/HttpURLConnection
      //   65: putfield 75	com/mojang/minecraftpe/MainActivity$HurlRunner:conn	Ljava/net/HttpURLConnection;
      //   68: iload_3
      //   69: istore_1
      //   70: aload 9
      //   72: astore 6
      //   74: aload 10
      //   76: astore 5
      //   78: aload_0
      //   79: getfield 75	com/mojang/minecraftpe/MainActivity$HurlRunner:conn	Ljava/net/HttpURLConnection;
      //   82: aload_0
      //   83: getfield 42	com/mojang/minecraftpe/MainActivity$HurlRunner:method	Ljava/lang/String;
      //   86: invokevirtual 78	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
      //   89: iload_3
      //   90: istore_1
      //   91: aload 9
      //   93: astore 6
      //   95: aload 10
      //   97: astore 5
      //   99: aload_0
      //   100: getfield 75	com/mojang/minecraftpe/MainActivity$HurlRunner:conn	Ljava/net/HttpURLConnection;
      //   103: ldc 80
      //   105: aload_0
      //   106: getfield 44	com/mojang/minecraftpe/MainActivity$HurlRunner:cookies	Ljava/lang/String;
      //   109: invokevirtual 84	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
      //   112: iload_3
      //   113: istore_1
      //   114: aload 9
      //   116: astore 6
      //   118: aload 10
      //   120: astore 5
      //   122: aload_0
      //   123: getfield 75	com/mojang/minecraftpe/MainActivity$HurlRunner:conn	Ljava/net/HttpURLConnection;
      //   126: ldc 86
      //   128: ldc 88
      //   130: invokevirtual 84	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
      //   133: iload_3
      //   134: istore_1
      //   135: aload 9
      //   137: astore 6
      //   139: aload 10
      //   141: astore 5
      //   143: aload_0
      //   144: getfield 75	com/mojang/minecraftpe/MainActivity$HurlRunner:conn	Ljava/net/HttpURLConnection;
      //   147: iconst_0
      //   148: invokevirtual 92	java/net/HttpURLConnection:setUseCaches	(Z)V
      //   151: iload_3
      //   152: istore_1
      //   153: aload 9
      //   155: astore 6
      //   157: aload 10
      //   159: astore 5
      //   161: aload_0
      //   162: getfield 75	com/mojang/minecraftpe/MainActivity$HurlRunner:conn	Ljava/net/HttpURLConnection;
      //   165: iconst_1
      //   166: invokevirtual 95	java/net/HttpURLConnection:setDoInput	(Z)V
      //   169: iload_3
      //   170: istore_1
      //   171: aload 9
      //   173: astore 6
      //   175: aload 10
      //   177: astore 5
      //   179: aload_0
      //   180: getfield 75	com/mojang/minecraftpe/MainActivity$HurlRunner:conn	Ljava/net/HttpURLConnection;
      //   183: invokevirtual 98	java/net/HttpURLConnection:connect	()V
      //   186: aload 10
      //   188: astore 5
      //   190: aload_0
      //   191: getfield 75	com/mojang/minecraftpe/MainActivity$HurlRunner:conn	Ljava/net/HttpURLConnection;
      //   194: invokevirtual 102	java/net/HttpURLConnection:getResponseCode	()I
      //   197: istore_1
      //   198: iload_1
      //   199: istore_2
      //   200: aload 10
      //   202: astore 5
      //   204: aload_0
      //   205: getfield 75	com/mojang/minecraftpe/MainActivity$HurlRunner:conn	Ljava/net/HttpURLConnection;
      //   208: invokevirtual 106	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
      //   211: astore 6
      //   213: aload 6
      //   215: astore 4
      //   217: iload_1
      //   218: istore_2
      //   219: aload 7
      //   221: astore 5
      //   223: aload 4
      //   225: ifnull +49 -> 274
      //   228: iload_2
      //   229: istore_1
      //   230: aload 4
      //   232: astore 6
      //   234: aload 4
      //   236: astore 5
      //   238: aload_0
      //   239: getfield 75	com/mojang/minecraftpe/MainActivity$HurlRunner:conn	Ljava/net/HttpURLConnection;
      //   242: invokevirtual 109	java/net/HttpURLConnection:getContentLength	()I
      //   245: ifge +207 -> 452
      //   248: sipush 1024
      //   251: istore_3
      //   252: iload_2
      //   253: istore_1
      //   254: aload 4
      //   256: astore 6
      //   258: aload 4
      //   260: astore 5
      //   262: aload 4
      //   264: iload_3
      //   265: invokestatic 113	com/mojang/minecraftpe/MainActivity:access$2100	(Ljava/io/InputStream;I)Ljava/lang/String;
      //   268: astore 7
      //   270: aload 7
      //   272: astore 5
      //   274: iload_2
      //   275: istore_3
      //   276: aload 5
      //   278: astore 7
      //   280: aload 4
      //   282: ifnull +14 -> 296
      //   285: aload 4
      //   287: invokevirtual 118	java/io/InputStream:close	()V
      //   290: aload 5
      //   292: astore 7
      //   294: iload_2
      //   295: istore_3
      //   296: aload 7
      //   298: ifnull +3 -> 301
      //   301: aload_0
      //   302: getfield 34	com/mojang/minecraftpe/MainActivity$HurlRunner:isValid	Z
      //   305: ifeq +21 -> 326
      //   308: aload_0
      //   309: getfield 29	com/mojang/minecraftpe/MainActivity$HurlRunner:this$0	Lcom/mojang/minecraftpe/MainActivity;
      //   312: aload_0
      //   313: getfield 36	com/mojang/minecraftpe/MainActivity$HurlRunner:requestId	I
      //   316: aload_0
      //   317: getfield 38	com/mojang/minecraftpe/MainActivity$HurlRunner:timestamp	J
      //   320: iload_3
      //   321: aload 7
      //   323: invokevirtual 122	com/mojang/minecraftpe/MainActivity:nativeWebRequestCompleted	(IJILjava/lang/String;)V
      //   326: aload_0
      //   327: getfield 29	com/mojang/minecraftpe/MainActivity$HurlRunner:this$0	Lcom/mojang/minecraftpe/MainActivity;
      //   330: invokestatic 48	com/mojang/minecraftpe/MainActivity:access$2000	(Lcom/mojang/minecraftpe/MainActivity;)Landroid/util/SparseArray;
      //   333: astore 4
      //   335: aload 4
      //   337: monitorenter
      //   338: aload_0
      //   339: getfield 29	com/mojang/minecraftpe/MainActivity$HurlRunner:this$0	Lcom/mojang/minecraftpe/MainActivity;
      //   342: invokestatic 48	com/mojang/minecraftpe/MainActivity:access$2000	(Lcom/mojang/minecraftpe/MainActivity;)Landroid/util/SparseArray;
      //   345: aload_0
      //   346: getfield 29	com/mojang/minecraftpe/MainActivity$HurlRunner:this$0	Lcom/mojang/minecraftpe/MainActivity;
      //   349: invokestatic 48	com/mojang/minecraftpe/MainActivity:access$2000	(Lcom/mojang/minecraftpe/MainActivity;)Landroid/util/SparseArray;
      //   352: aload_0
      //   353: invokevirtual 126	android/util/SparseArray:indexOfValue	(Ljava/lang/Object;)I
      //   356: invokevirtual 130	android/util/SparseArray:remove	(I)V
      //   359: aload 4
      //   361: monitorexit
      //   362: return
      //   363: astore 5
      //   365: aload 10
      //   367: astore 5
      //   369: aload_0
      //   370: getfield 75	com/mojang/minecraftpe/MainActivity$HurlRunner:conn	Ljava/net/HttpURLConnection;
      //   373: invokevirtual 133	java/net/HttpURLConnection:getErrorStream	()Ljava/io/InputStream;
      //   376: astore 6
      //   378: aload 6
      //   380: astore 4
      //   382: goto -163 -> 219
      //   385: astore 11
      //   387: iload_2
      //   388: istore_1
      //   389: aload 9
      //   391: astore 6
      //   393: aload 10
      //   395: astore 5
      //   397: aload 11
      //   399: invokevirtual 136	java/lang/Exception:printStackTrace	()V
      //   402: goto -183 -> 219
      //   405: astore 4
      //   407: aload 6
      //   409: astore 5
      //   411: aload 4
      //   413: invokevirtual 136	java/lang/Exception:printStackTrace	()V
      //   416: iload_1
      //   417: istore_3
      //   418: aload 8
      //   420: astore 7
      //   422: aload 6
      //   424: ifnull -128 -> 296
      //   427: aload 6
      //   429: invokevirtual 118	java/io/InputStream:close	()V
      //   432: iload_1
      //   433: istore_3
      //   434: aload 8
      //   436: astore 7
      //   438: goto -142 -> 296
      //   441: astore 4
      //   443: iload_1
      //   444: istore_3
      //   445: aload 8
      //   447: astore 7
      //   449: goto -153 -> 296
      //   452: iload_2
      //   453: istore_1
      //   454: aload 4
      //   456: astore 6
      //   458: aload 4
      //   460: astore 5
      //   462: aload_0
      //   463: getfield 75	com/mojang/minecraftpe/MainActivity$HurlRunner:conn	Ljava/net/HttpURLConnection;
      //   466: invokevirtual 109	java/net/HttpURLConnection:getContentLength	()I
      //   469: istore_3
      //   470: goto -218 -> 252
      //   473: astore 4
      //   475: aload 5
      //   477: ifnull +8 -> 485
      //   480: aload 5
      //   482: invokevirtual 118	java/io/InputStream:close	()V
      //   485: aload 4
      //   487: athrow
      //   488: astore 5
      //   490: aload 4
      //   492: monitorexit
      //   493: aload 5
      //   495: athrow
      //   496: astore 4
      //   498: iload_2
      //   499: istore_3
      //   500: aload 5
      //   502: astore 7
      //   504: goto -208 -> 296
      //   507: astore 5
      //   509: goto -24 -> 485
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	512	0	this	HurlRunner
      //   20	434	1	i	int
      //   18	481	2	j	int
      //   16	484	3	k	int
      //   405	7	4	localException1	Exception
      //   441	18	4	localException2	Exception
      //   473	18	4	localObject2	Object
      //   496	1	4	localException3	Exception
      //   27	264	5	localObject3	Object
      //   363	1	5	localException4	Exception
      //   367	114	5	localObject4	Object
      //   488	13	5	localObject5	Object
      //   507	1	5	localException5	Exception
      //   23	434	6	localObject6	Object
      //   13	490	7	localObject7	Object
      //   10	436	8	localObject8	Object
      //   1	389	9	localObject9	Object
      //   4	390	10	localObject10	Object
      //   385	13	11	localException6	Exception
      // Exception table:
      //   from	to	target	type
      //   190	198	363	java/lang/Exception
      //   204	213	363	java/lang/Exception
      //   369	378	385	java/lang/Exception
      //   29	44	405	java/lang/Exception
      //   54	68	405	java/lang/Exception
      //   78	89	405	java/lang/Exception
      //   99	112	405	java/lang/Exception
      //   122	133	405	java/lang/Exception
      //   143	151	405	java/lang/Exception
      //   161	169	405	java/lang/Exception
      //   179	186	405	java/lang/Exception
      //   238	248	405	java/lang/Exception
      //   262	270	405	java/lang/Exception
      //   397	402	405	java/lang/Exception
      //   462	470	405	java/lang/Exception
      //   427	432	441	java/lang/Exception
      //   29	44	473	finally
      //   54	68	473	finally
      //   78	89	473	finally
      //   99	112	473	finally
      //   122	133	473	finally
      //   143	151	473	finally
      //   161	169	473	finally
      //   179	186	473	finally
      //   190	198	473	finally
      //   204	213	473	finally
      //   238	248	473	finally
      //   262	270	473	finally
      //   369	378	473	finally
      //   397	402	473	finally
      //   411	416	473	finally
      //   462	470	473	finally
      //   338	362	488	finally
      //   490	493	488	finally
      //   285	290	496	java/lang/Exception
      //   480	485	507	java/lang/Exception
    }
  }
  
  private class LoginWebViewClient
    extends WebViewClient
  {
    boolean hasFiredLaunchEvent = false;
    
    private LoginWebViewClient() {}
    
    public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
    {
      paramBitmap = Uri.parse(paramString);
      paramString = MainActivity.this.getRealmsRedirectInfo().accountUrl;
      paramWebView = paramString;
      if (paramString == null) {
        paramWebView = "account.mojang.com";
      }
      if ((paramBitmap.getHost().equals(paramWebView)) && (paramBitmap.getPath().equals("/m/launch")) && (!this.hasFiredLaunchEvent))
      {
        MainActivity.this.loginLaunchCallback(paramBitmap);
        this.hasFiredLaunchEvent = true;
      }
    }
    
    public void onReceivedSslError(WebView paramWebView, SslErrorHandler paramSslErrorHandler, SslError paramSslError)
    {
      if (MainActivity.this.isRedirectingRealms())
      {
        paramSslErrorHandler.proceed();
        return;
      }
      super.onReceivedSslError(paramWebView, paramSslErrorHandler, paramSslError);
    }
    
    public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
    {
      Uri localUri = Uri.parse(paramString);
      String str2 = MainActivity.this.getRealmsRedirectInfo().accountUrl;
      String str1 = str2;
      if (str2 == null) {
        str1 = "account.mojang.com";
      }
      if (localUri.getHost().equals(str1))
      {
        if (localUri.getPath().equals("/m/launch"))
        {
          MainActivity.this.loginLaunchCallback(localUri);
          this.hasFiredLaunchEvent = true;
          return true;
        }
        paramWebView.loadUrl(paramString);
        return true;
      }
      return false;
    }
  }
  
  private class PopupTextWatcher
    implements TextWatcher, TextView.OnEditorActionListener
  {
    private PopupTextWatcher() {}
    
    public void afterTextChanged(Editable paramEditable)
    {
      MainActivity.this.nativeSetTextboxText(paramEditable.toString());
      if ((MainActivity.this.isCommandHistoryEnabled()) && (MainActivity.this.commandHistoryIndex >= 0) && (MainActivity.this.commandHistoryIndex < MainActivity.this.commandHistoryList.size())) {
        MainActivity.this.commandHistoryList.set(MainActivity.this.commandHistoryIndex, paramEditable.toString());
      }
    }
    
    public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
    
    public boolean onEditorAction(TextView paramTextView, int paramInt, KeyEvent paramKeyEvent)
    {
      MainActivity.this.nativeReturnKeyPressed();
      return true;
    }
    
    public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
  }
}
