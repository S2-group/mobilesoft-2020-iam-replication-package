package com.inveno.piflow.activity.ext;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.text.Html;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.inveno.piflow.R;
import com.inveno.piflow.activity.BaseFragmentActivity;
import com.inveno.piflow.biz.DayNightModeBiz;
import com.inveno.piflow.biz.NewsCommonMoreBiz;
import com.inveno.piflow.biz.NewsCommonMoreBiz.FontSizeChange;
import com.inveno.piflow.biz.NewsCommonMoreBiz.OnPopLightSetListener;
import com.inveno.piflow.biz.NewsCommonMoreBiz.OnPopTtsSeekBarChangeListener;
import com.inveno.piflow.biz.TtsBiz;
import com.inveno.piflow.biz.TtsBiz.OnSpeakCompletedListener;
import com.inveno.piflow.biz.upload.WFPvUploadBiz;
import com.inveno.piflow.download.downloadmanager.download.DownLoadCallback;
import com.inveno.piflow.download.downloadmanager.download.DownloadManager;
import com.inveno.piflow.download.downloadmanager.download.DownloadService;
import com.inveno.piflow.download.downloadmanager.download.DownloadService.DownloadServiceImpl;
import com.inveno.piflow.download.downloadmanager.download.IDownloadService;
import com.inveno.piflow.download.downloadmanager.netstate.NetWorkUtil;
import com.inveno.piflow.entity.model.showflow.ShowFlowAd;
import com.inveno.piflow.entity.model.showflow.ShowFlowHardAd;
import com.inveno.piflow.entity.model.showflow.ShowFlowNewinfo;
import com.inveno.piflow.entity.model.showflow.ShowFlowNews;
import com.inveno.piflow.entity.model.showflow.ShowFlowP;
import com.inveno.piflow.entity.model.upload.WFPvOperation;
import com.inveno.piflow.entity.view.TtsSettingFragment;
import com.inveno.piflow.entity.view.TtsSettingFragment.onPositiveListener;
import com.inveno.piflow.entity.view.news.WFAudioFragment;
import com.inveno.piflow.entity.view.news.WFNewsDetailFragment;
import com.inveno.piflow.tools.android.ToastTools;
import com.inveno.piflow.tools.commontools.Const;
import com.inveno.piflow.tools.commontools.DeviceConfig;
import com.inveno.piflow.tools.commontools.MkdirFileTools;
import com.inveno.piflow.tools.commontools.OperDialog;
import com.inveno.piflow.tools.commontools.StartModuleUtil;
import com.inveno.piflow.tools.commontools.StringTools;
import com.inveno.piflow.tools.commontools.Tools;
import com.tencent.mm.sdk.openapi.BaseReq;
import com.tencent.mm.sdk.openapi.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

/**
 * 瀑布流点击的资讯
 * 
 * @author hongchang.liu
 * 
 */
public class WFNewsDetailActivity extends BaseFragmentActivity implements
		NewsCommonVariable, FontSizeChange, OnPopLightSetListener,
		OnSpeakCompletedListener, OnPopTtsSeekBarChangeListener,
		IWXAPIEventHandler {

	private int backPage;
	private boolean hasNet;

	private NewsCommonMoreBiz newsCommonMoreBiz;

	public NewsCommonMoreBiz getNewsCommonMoreBiz() {
		return newsCommonMoreBiz;
	}

	private DayNightModeBiz dayNightModeBiz;

	private int mode;
	/** 字体调整popupWindow **/
	private PopupWindow textPopupWindow;
	/** 亮度调整的popupWindow */
	private PopupWindow lightPopupWindow;

	/** 数据保存sp */
	private SharedPreferences sp;

	private int fontSize;

	private DeviceConfig config;

	private RelativeLayout backgroundLayout;

	/** 线程池的线程数 **/
	private final int THREAD_SIZE = 3;

	/** 线程池 */
	private ExecutorService dataLoadExecutor;

	private HandlerThread handlerThread;
	private Handler sonHandler;

	public Handler getSonHandler() {
		return sonHandler;
	}

	public ExecutorService getDataLoadExecutor() {
		return dataLoadExecutor;
	}

	/************* 语音相关变量 *************/
	private boolean isSpeaking;

	public void setSpeaking(boolean isSpeaking) {
		this.isSpeaking = isSpeaking;
	}

	private PopupWindow ttsSetingPop;

	private AudioManager audioManager;

	private int maxVolume;

	/** 是否显示提示语音设置的对话框 **/
	private boolean noMoreAsk;

	private Dialog downSpeakEngineDialog;

	private boolean flipFromTts;

	// private boolean readWhat;

	private ImageButton speakingBtn;

	private MissPopRunnable missPopRunnable;

	private boolean clickStop;

	private TelephonyManager telephonyManager;

	// /** 应用安装包名 */
	// private String appPKName;

	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
		};
	};

	private WFNewsDetailFragment wfNewsDetailFragment;

	private ShowFlowNewinfo newsInfo;

	private PackageManager pkManager;

	/** 推广应用在该系统中的状态，默认0未下载， 1已经安装，2已下载完成，未安装 ***/
	private int adsAppState;
	public static final int NOT_DOWNLOADED = 0;
	public static final int HAS_INSTALL = 1;
	public static final int FINISH_DOWNLOAD = 2;

	private DownloadManager downloadManager;

	public DownloadManager getDownloadManager() {
		return downloadManager;
	}

	private DownLoadCallback callback;
	private ServiceConnection serviceConnection;

	private boolean binderServer;

	private boolean isStop;

	private ShowFlowAd showFlowAd;

	private ShowFlowHardAd flowHardAd;

	private long srartTime;
	private long endTime;

	private WFPvOperation pvOperation;

	private HashMap<String, ShowFlowNews> map;

	private String recoId;

	private ShowFlowNews showFlowNews;

	private TtsBiz ttsBiz;

	private ArrayList<String> contents;
	private ArrayList<String> imgs;
	//
	/** 当前朗读的内容段 **/
	private String currentContent;

	private String title;

	private int contentSize;

	private IWXAPI iwxapi;

	private boolean regBackCode;

	/** 注册微信 */
	private void regToWx() {
		iwxapi = WXAPIFactory.createWXAPI(mContext,
				Const.WEIXIN_FLYSHARE_APPID, true);
		regBackCode = iwxapi.registerApp(Const.WEIXIN_FLYSHARE_APPID);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.wpc_waterfall_newsdetail_container);

		if (findViewById(R.id.news_datail_backgroundLayout) != null) {

			if (savedInstanceState != null) {
				return;
			}

			regToWx();

			init();

			// "6"是视频
			if (flowHardAd != null && "6".equals(flowHardAd.getCtype())) {
				Fragment audioFragment = WFAudioFragment.getInstance(newsInfo);
				getSupportFragmentManager()
						.beginTransaction()
						.add(R.id.news_datail_fragment_container, audioFragment)
						.commit();

			} else {
				wfNewsDetailFragment = WFNewsDetailFragment.newInstance(
						newsInfo, fontSize, adsAppState, imgs, regBackCode);
				getSupportFragmentManager().beginTransaction()
						.add(R.id.news_datail_fragment_container,

						wfNewsDetailFragment).commit();
			}

		}

		// readWhat = Tools.getBooleanInformain(Tools.TTS_MODE, false,
		// mContext);
		// ttsBiz.setSpeakTitleOrContent(readWhat);
		initMembers();
		initViews();
		iwxapi.handleIntent(getIntent(), this);
	}

	@Override
	protected void onResume() {

		Tools.showLog("hzj", " newsCommonact   onResume mode" + mode);

		/** 改变模式 */
		// if (mode != dayNightModeBiz.getMode()) {
		//
		// mode = dayNightModeBiz.getMode();
		//
		// }

		if (isStop && wfNewsDetailFragment != null) {
			wfNewsDetailFragment
					.changeAppAdsBottomBar(checkAdsAppInstallState());

		}
		// wfNewsDetailFragment.refreshImg();
		isStop = false;
		super.onResume();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		// isSpeaking = false;
		// ttsBiz.stop();
		// resetMenuBtnImg(R.drawable.popup_btn_speak,
		// R.drawable.popup_btn_zhuanti, R.drawable.popup_btn_history,
		// R.drawable.popup_btn_set);
		super.onPause();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {

			if (ttsSetingPop != null && ttsSetingPop.isShowing()) {
				ttsSetingPop.dismiss();
			} else if (menuPopup != null && menuPopup.isShowing()) {
				menuPopup.dismiss();
			}

			else if (speakingBtn != null && speakingBtn.isShown()) {
				speakingBtn.setVisibility(View.INVISIBLE);
				isSpeaking = false;
				ttsBiz.destroy();
				if (mode == 1) {
					resetMenuBtnImg(R.drawable.popup_btn_speak,
							R.drawable.popup_btn_text,
							R.drawable.popup_btn_light, R.drawable.popup_btn_ri);
				} else {
					resetMenuBtnImg(R.drawable.popup_btn_speak,
							R.drawable.popup_btn_text,
							R.drawable.popup_btn_light, R.drawable.popup_btn_ye);
				}
				wfNewsDetailFragment.updateTitleTtsUi(false);
				wfNewsDetailFragment.updateTtsUi("", false);
				currentContent = title;

			} else if (ttsBiz != null) {

				ttsBiz.destroy();
				finish();
			} else {
				finish();
			}

			return false;
		}
		return super.onKeyDown(keyCode, event);
	}

	//
	// public static int id;

	protected void initViews() {

		speakingBtn = (ImageButton) findViewById(R.id.tts_speech_icon);
		backgroundLayout = (RelativeLayout) findViewById(R.id.news_datail_backgroundLayout);
		// changeBackgroud(dayNightModeBiz.getMode());
	}

	@Override
	protected void addAction(IntentFilter filter) {
		filter.addAction(NEED_FINISH);
	}

	@Override
	protected void changeUI(Context context, Intent intent) {

		String action = intent.getAction();
		if (NEED_FINISH.equals(action)) {
			finish();

		}

	}

	@Override
	protected void initData() {

		dayNightModeBiz = DayNightModeBiz.getInstance(this);
		mode = dayNightModeBiz.getMode();
		config = DeviceConfig.getInstance(mContext);

		sp = getSharedPreferences(Const.ALLSETTINGS, MODE_PRIVATE);
		fontSize = sp.getInt(Const.FLYSHARE_FONTSIZE, 18);
	}

	@Override
	protected void initMembers() {
		if (flowHardAd != null && "6".equals(flowHardAd.getCtype())) {

		} else {

			audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
			maxVolume = audioManager
					.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
			telephonyManager = (TelephonyManager) mContext
					.getSystemService(Context.TELEPHONY_SERVICE);
			telephonyManager.listen(new TtsPhoneListener(),
					PhoneStateListener.LISTEN_CALL_STATE);

			ttsBiz = TtsBiz.getInstance(mContext);
			ttsBiz.setOnSpeakCompletedListener(this);
			ttsBiz.setFromWhere(TtsBiz.FROM_NEWS_WATERFALL);
			ttsBiz.speakNewsWaterFall(contents);
			newsCommonMoreBiz = new NewsCommonMoreBiz(mContext);
			newsCommonMoreBiz.setFontSizeChange(this);
			newsCommonMoreBiz.setLightSetListener(this);
			newsCommonMoreBiz.setOnPopTtsSeekBarChangeListener(this);
			ttsSetingPop = newsCommonMoreBiz.createTtsSettingWindow(maxVolume,
					audioManager.getStreamVolume(AudioManager.STREAM_MUSIC),
					Tools.getInformain(Tools.TTS_SPEECH, 10, mContext),
					Tools.getBooleanInformain(Tools.TTS_MODE, false, mContext));
			initPopuwindow();
		}

		handlerThread = new HandlerThread("NewsCommonThread");
		handlerThread.start();
		sonHandler = new Handler(handlerThread.getLooper());
		dataLoadExecutor = Executors.newFixedThreadPool(THREAD_SIZE,
				new ThreadFactory() {

					@Override
					public Thread newThread(Runnable r) {
						Thread t = new Thread(r);
						// 设置线程的优先级别，让线程先后顺序执行（级别越高，抢到cpu执行的时间越多）
						t.setPriority(Thread.NORM_PRIORITY - 1);
						return t;
					}
				});

	}

	@Override
	protected void init() {
		map = myApp.getmShowFlowNewsMap();
		srartTime = System.currentTimeMillis();
		contents = new ArrayList<String>();
		imgs = new ArrayList<String>();
		pvOperation = new WFPvOperation();
		Intent intent = getIntent();
		newsInfo = (ShowFlowNewinfo) intent
				.getSerializableExtra(StartModuleUtil.WF_NEWS_BEAN);

		title = newsInfo.getTitle();
		currentContent = title;
		// 标题添加，用于tts阅读
		contents.add(currentContent);

		// 把图片和内容分开
		List<ShowFlowP> ps = newsInfo.getContent();
		if (ps != null) {
			int size = ps.size();
			for (int i = 0; i < size; i++) {
				ShowFlowP p = ps.get(i);
				if (p.isImg()) {
					imgs.add(p.getUrl());
				} else {
					String str = Html.fromHtml(p.getContent()).toString();
					Tools.showLog("hzj", i + "i str:" + str);
					if (StringTools.isNotEmpty(str)) {
						contents.add(str);
					}

				}

			}
		}
		contentSize = contents.size();
		recoId = newsInfo.getReco_id();
		pvOperation.setClick_timestamp("" + srartTime);
		pvOperation.setReco_id(recoId);
		pvOperation.setClick_item_id(newsInfo.getId());
		pvOperation.setClick_item_type(newsInfo.getType());
		showFlowNews = map.get(recoId);
		showFlowAd = newsInfo.getShowFlowAd();
		flowHardAd = newsInfo.getShowFlowHardAd();
		pkManager = mContext.getPackageManager();
		adsAppState = checkAdsAppInstallState();
		callback = getDownCallback();

		serviceConnection = getServiceConnection();
		binderServer = mContext.bindService(new Intent(mContext,
				DownloadService.class), serviceConnection,
				Context.BIND_AUTO_CREATE);
		initData();
	}

	/**
	 * 记录以及保存单条信息到手机数据库
	 */
	@Override
	protected void onDestroy() {
		if (ttsBiz != null)
			ttsBiz.destroy();

		WFPvUploadBiz.getInstance().post(mContext, pvOperation, srartTime,
				showFlowNews, newsInfo);
		// NewsHistoryActivity.mCanFinish = false;
		// bitmapManager.flushCache();
		// contents.clear();
		// imgs.clear();
		if (newsCommonMoreBiz != null) {
			newsCommonMoreBiz.removeAllFontSizeChange();
			newsCommonMoreBiz = null;
		}

		dataLoadExecutor.shutdown();
		handlerThread.quit();
		if (binderServer) {
			mContext.unbindService(serviceConnection);
			binderServer = false;
		}
		super.onDestroy();

	}

	/**
	 * 设置数值保留两位小数显示,四舍五入
	 * 
	 * @param num
	 * @param scale
	 * @param roundingMode
	 * @return
	 */
	private static String setSizetoDoble(long num) {

		BigDecimal mData = new BigDecimal(num).setScale(1,
				BigDecimal.ROUND_HALF_UP);

		return mData.toString();

	}

	/**
	 * 上传PV点击资讯
	 */
	private void uploadPv() {
		// 退出资讯详细,上传pv
		if (NetWorkUtil.isNetworkAvailable(mContext)) {
			pvOperation.setView_time(setSizetoDoble(System.currentTimeMillis()
					- srartTime));
			new Thread(new Runnable() {

				@Override
				public void run() {
					Tools.showLogA("开始上传：" + showFlowNews);
					if (showFlowNews != null) {
						for (int i = 0; i < showFlowNews.size(); i++) {
							ShowFlowNewinfo sf = showFlowNews.get(i);
							if (sf.getId().equals(newsInfo.getId())) {
								Tools.showLog("pvp",
										"删除当前点击的：" + newsInfo.getTitle());
								showFlowNews.remove(i);
							}
						}
						String code = WFPvUploadBiz.getInstance().postWFPvData(
								mContext, showFlowNews, pvOperation);
						Tools.showLog("pvp", "上传瀑布流pv返回码:" + code);
					}

				}
			}).start();
		}
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		// if (hasFocus) {
		// mNeedFinish = 0;
		// }
		super.onWindowFocusChanged(hasFocus);
	}

	@Override
	protected void onStop() {
		isStop = true;
		super.onStop();
	}

	private void changeBackgroud(int mode) {

		// 设置背景颜色
		if (mode == 1) {
			backgroundLayout.setBackgroundColor(dayNightModeBiz.getWhite());
		} else {
			backgroundLayout.setBackgroundColor(dayNightModeBiz.getBlackbg());
		}
	}

	@Override
	public boolean onMenuOpened(int featureId, Menu menu) {
		// TODO Auto-generated method stub

		super.onMenuOpened(featureId, menu);
		if (menuPopup != null) {
			if (menuPopup.isShowing()) {
				dismissSpeakIcon();

			} else {
				showSpeakIcon();
			}

			if (ttsSetingPop != null) {
				ttsSetingPop.dismiss();
			}
			dismissPopInMsg();
		}

		return false;
	}

	public void showSpeakIcon() {
		if (speakingBtn != null && isSpeaking) {
			speakingBtn.setVisibility(View.VISIBLE);
		}
	}

	public void dismissSpeakIcon() {
		if (speakingBtn != null) {
			speakingBtn.setVisibility(View.INVISIBLE);
		}
	}

	/** 延时取消显示menupop的消息 **/
	private class MissPopRunnable implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Tools.showLog("tts", "收到消息关闭menupop");

			boolean textPopShow = false;
			if (textPopupWindow != null) {

				if (textPopupWindow.isShowing()) {
					textPopShow = true;
				}

			}
			boolean lightPopShow = false;
			if (lightPopupWindow != null) {

				if (lightPopupWindow.isShowing()) {
					lightPopShow = true;
				}
			}
			boolean ttsPopShow = false;

			if (ttsSetingPop != null) {
				if (ttsSetingPop.isShowing()) {
					ttsPopShow = true;
				}
			}

			if (menuPopup != null && menuPopup.isShowing() && !textPopShow
					&& !lightPopShow && !ttsPopShow) {
				menuPopup.dismiss();
			}
		}

	}

	/** 延时取消显示menupop的消息 **/
	private void dismissPopInMsg() {
		if (missPopRunnable != null) {
			mHandler.removeCallbacks(missPopRunnable);
			missPopRunnable = null;
		}
		missPopRunnable = new MissPopRunnable();
		mHandler.postDelayed(missPopRunnable, 3000);
	}

	//
	private class TtsPhoneListener extends PhoneStateListener {

		@Override
		public void onCallStateChanged(int state, String incomingNumber) {
			// TODO Auto-generated method stub
			switch (state) {
			case TelephonyManager.CALL_STATE_IDLE: // 空闲
				if (isSpeaking && ttsBiz.isStop()) {
					ttsBiz.start();
					clickStop = false;
					resetMenuBtnImg(R.drawable.popup_btn_previous,
							R.drawable.popup_btn_stop,
							R.drawable.popup_btn_next, R.drawable.popup_btn_set);
				}
				break;
			case TelephonyManager.CALL_STATE_RINGING:
			case TelephonyManager.CALL_STATE_OFFHOOK: // 来电
				ttsBiz.stop();
				if (isSpeaking) {
					resetMenuBtnImg(R.drawable.popup_btn_previous,
							R.drawable.popup_btn_play,
							R.drawable.popup_btn_next, R.drawable.popup_btn_set);
					clickStop = true;
				}
				break;
			}
		}

	}

	/**
	 * 检查推广应用的状态
	 * 
	 * @return
	 */
	private int checkAdsAppInstallState() {

		boolean showAd = showFlowAd != null
				&& showFlowAd.getSecond() == ShowFlowAd.DOWN_APP;
		boolean showHardAd = flowHardAd != null
				&& flowHardAd.getSecond() == ShowFlowAd.DOWN_APP;

		if (showAd || showHardAd) {
			String appPKName = null;
			String cpApk = null;
			if (showAd) {
				appPKName = showFlowAd.getCpPackage();
				cpApk = showFlowAd.getCpApk();
			} else {
				String pkName = flowHardAd.getCppackage();
				appPKName = pkName.substring(0, pkName.indexOf("/"));
				cpApk = flowHardAd.getCpapk();
			}
			List<PackageInfo> packageInfos = pkManager.getInstalledPackages(0);
			for (PackageInfo packageInfo : packageInfos) {
				if (appPKName.equals(packageInfo.packageName)) {
					adsAppState = HAS_INSTALL;
					return adsAppState;

				}
			}

			String appName = StringTools.getFileNameFromUrl(cpApk);
			// 如果已经下载过直接提示安装
			String savePathOld = MkdirFileTools.APPS_PATH + File.separator
					+ appName;
			File file = new File(savePathOld);
			if (file.exists()) {
				adsAppState = FINISH_DOWNLOAD;
				return adsAppState;
			}
		}
		adsAppState = NOT_DOWNLOADED;

		Tools.showLog("wf", "adsappstate:" + adsAppState);
		return adsAppState;
	}

	private DownLoadCallback getDownCallback() {
		DownLoadCallback callback = new DownLoadCallback() {

			@Override
			public void onStart() {
				// TODO Auto-generated method stub
				super.onStart();

			}

			@Override
			public void onAdd(String url, Boolean isInterrupt) {
				// TODO Auto-generated method stub
				super.onAdd(url, isInterrupt);
			}

			@Override
			public void onLoading(String url, String speed, long download,
					int progress) {
				// TODO Auto-generated method stub
				super.onLoading(url, speed, download, progress);
				if (wfNewsDetailFragment != null) {
					wfNewsDetailFragment.updateAppProgress(progress);
					if (progress == 100) {
						adsAppState = FINISH_DOWNLOAD;
						wfNewsDetailFragment.changeAppAdsBottomBar(adsAppState);
						if (showFlowAd != null) {
							wfNewsDetailFragment.installApk(showFlowAd
									.getCpApk());
						} else if (flowHardAd != null) {
							wfNewsDetailFragment.installApk(flowHardAd
									.getCpapk());
						}

					}

				}
			}

			@Override
			public void onSuccess(String url) {
				// TODO Auto-generated method stub
				super.onSuccess(url);
				Tools.showLog("lhc", this + "onSuccess");

			}

			@Override
			public void onFailure(String url, String strMsg) {
				// TODO Auto-generated method stub
				super.onFailure(url, strMsg);
				Tools.showLog("lhc", this + "onFailure");

			}

			@Override
			public void onFinish(String url) {
				// TODO Auto-generated method stub
				super.onFinish(url);
			}

			@Override
			public void onStop() {
				// TODO Auto-generated method stub
				super.onStop();
			}

		};

		return callback;
	}

	private ServiceConnection getServiceConnection() {
		ServiceConnection serviceConnection = new ServiceConnection() {

			@Override
			public void onServiceDisconnected(ComponentName name) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onServiceConnected(ComponentName name, IBinder service) {
				// TODO Auto-generated method stub
				DownloadService.DownloadServiceImpl downloadServiceImpl = (DownloadServiceImpl) IDownloadService.Stub
						.asInterface(service);
				downloadManager = downloadServiceImpl.getDownloadManager();
				Tools.showLog("lhc", "onServiceConnected   downloadManager:"
						+ downloadManager);
				// downloadManager.setDownLoadCallback(callback);
			}
		};
		return serviceConnection;

	}

	public void setDownLoadMgCallback() {
		if (downloadManager != null && callback != null) {
			downloadManager.setDownLoadCallback(callback);
		}
	}

	@Override
	public void onTtsProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		// TODO Auto-generated method stub
		if (seekBar.getId() == R.id.tts_speech_sb) {
			Tools.setInformain(Tools.TTS_SPEECH, progress, mContext);
			ttsBiz.speechToSpeak(progress);
		} else if (seekBar.getId() == R.id.tts_volume_sb) {
			audioManager
					.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);

		}
	}

	@Override
	public void onSpeakCompleted(int i, String content, int size) {
		// TODO Auto-generated method stub

		Tools.showLog("tts", "瀑布流语音 onSpeakCompleted i:" + i + "   content:"
				+ content + "   size:" + size);
		if (StringTools.isNotEmpty(content)) {
			if (wfNewsDetailFragment != null) {
				if (i == 0) {
					wfNewsDetailFragment.updateTitleTtsUi(true);
				} else if (i == 1) {
					wfNewsDetailFragment.updateTitleTtsUi(false);
				} else {
					wfNewsDetailFragment.updateTtsUi(currentContent, false);
				}

				currentContent = content;
				wfNewsDetailFragment.updateTtsUi(content, true);
			}
		} else {
			isSpeaking = false;
			ttsBiz.stop();
			resetMenuBtnImg(R.drawable.popup_btn_speak,
					R.drawable.popup_btn_text, R.drawable.popup_btn_light,
					R.drawable.popup_btn_ye);
			wfNewsDetailFragment.updateTtsUi("", false);
			dismissSpeakIcon();
			return;
		}

	}

	@Override
	public void setLight(int progress) {
		// TODO Auto-generated method stub
		int brightness = progress + 30;
		lightController(brightness);
	}

	/**
	 * 亮度控制器
	 * 
	 * @param brightness
	 */
	private void lightController(int brightness) {
		Settings.System.putInt(getContentResolver(),
				Settings.System.SCREEN_BRIGHTNESS, brightness);
		brightness = Settings.System.getInt(getContentResolver(),
				Settings.System.SCREEN_BRIGHTNESS, -1);
		LayoutParams lp = getWindow().getAttributes();
		lp.screenBrightness = brightness / 255.0f;
		getWindow().setAttributes(lp);
	}

	@Override
	public void onFontSizeChangeListener(int fontSize) {
		// TODO Auto-generated method stub
		if (wfNewsDetailFragment != null) {
			wfNewsDetailFragment.fontSizeSet(fontSize);
		}
	}

	/** tts正在朗读的图标点击事件 **/
	public void speaking(View v) {
		if (speakingBtn != null) {
			speakingBtn.setVisibility(View.INVISIBLE);
		}

		if (menuPopup != null && !menuPopup.isShowing()) {
			menuPopup.showAtLocation(getWindow().getDecorView(),
					Gravity.BOTTOM, 0, 0);
		}
		dismissPopInMsg();

	}

	@Override
	public void onReq(BaseReq arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onResp(BaseResp arg0) {
		// TODO Auto-generated method stub
		int result = 0;

		switch (arg0.errCode) {
		case BaseResp.ErrCode.ERR_OK:
			result = R.string.weixin_errcode_success;
			break;
		case BaseResp.ErrCode.ERR_USER_CANCEL:
			result = R.string.weixin_errcode_cancel;
			break;
		case BaseResp.ErrCode.ERR_AUTH_DENIED:
			result = R.string.weixin_errcode_deny;
			break;
		default:
			result = R.string.weixin_errcode_unknown;
			break;
		}
		ToastTools.showToast(mContext, result);
	}

	/** 初始化menu键弹窗 */
	private void initPopuwindow() {

		if (mode == 1) {
			registMenuPopup2(
					new MenuPopupListener() {

						@Override
						public void clickBtn1() {

							if (!isSpeaking) {

								Tools.showLog("tts",
										"tts can use:" + ttsBiz.isCanbeUse());
								if (Build.VERSION.SDK_INT < 14) {
									toastTV.setText("系统版本过低。");
									toast.show();
									return;
								}
								if (ttsBiz.checkTtsEngine()) {
									//
									// String newsContent = m_aNewsInfoList.get(
									// m_iCurrentIndex).getNewsContent();
									// ttsBiz.speakNewsContent(newsContent,
									// m_iCurrentIndex, m_iMaxPage);

									noMoreAsk = Tools.getBooleanInformain(
											Tools.TTS_NOMORE_ASK, false,
											mContext);
									if (!noMoreAsk) {
										TtsSettingFragment ttsFragment = new TtsSettingFragment(
												2);
										ttsFragment
												.setPositiveListener(new onPositiveListener() {

													@Override
													public void onClickPositiveBtn() {
														// TODO Auto-generated
														// method stub

														// String content =
														// m_aNewsInfoList
														// .get(m_iCurrentIndex)
														// .getNewsContent();
														// if (StringTools
														// .isEmpty(content)) {
														// return;
														// }

														isSpeaking = true;
														resetMenuBtnImg(
																R.drawable.popup_btn_previous,
																R.drawable.popup_btn_stop,
																R.drawable.popup_btn_next,
																R.drawable.popup_btn_set);
														showSpeakIcon();
														ttsBiz.start();
														if (currentContent
																.equals(title)) {
															wfNewsDetailFragment
																	.updateTitleTtsUi(true);
														} else {
															wfNewsDetailFragment
																	.updateTtsUi(
																			currentContent,
																			true);
														}

													}
												});
										ttsFragment.show(
												getSupportFragmentManager(),
												"ttsFragment");

									} else {

										// 检测是否有语音功能，没有则弹出下载语音引擎对话框，有则查看pf是否记录有不在提示，无则弹出设置对话框，有则直接开始朗读。
										// 读内容且内容为空
										// if (StringTools.isEmpty(newsContent))
										// {
										// return;
										// }
										isSpeaking = true;
										Tools.showLog("tts", "点击了开始阅读："
												+ isSpeaking);
										resetMenuBtnImg(
												R.drawable.popup_btn_previous,
												R.drawable.popup_btn_stop,
												R.drawable.popup_btn_next,
												R.drawable.popup_btn_set);
										ttsBiz.start();
										wfNewsDetailFragment
												.updateTitleTtsUi(true);
										showSpeakIcon();
									}
								} else {
									if (downSpeakEngineDialog == null) {
										downSpeakEngineDialog = OperDialog
												.getOperDialog()
												.createDownSpeakEngineDialog(
														mContext);

									}
									downSpeakEngineDialog.show();
								}

							} else {
								if (ttsSetingPop != null) {
									ttsSetingPop.dismiss();

								}

								// if (readWhat) {
								// String content = m_aNewsInfoList.get(
								// m_iCurrentIndex).getNewsContent();
								if (StringTools.isEmpty(currentContent)) {
									isSpeaking = false;
									resetMenuBtnImg(R.drawable.popup_btn_speak,
											R.drawable.popup_btn_text,
											R.drawable.popup_btn_light,
											R.drawable.popup_btn_ye);
									return;
								}

								// }
								wfNewsDetailFragment.updateTtsUi(
										currentContent, false);
								ttsBiz.previous();
								Tools.showLog("tts", "点击了上一条");
								dismissPopInMsg();
							}

						}

						@Override
						public void clickBtn2() {

							if (!isSpeaking) {
								// 弹出调整字体大小的窗口
								if (null != lightPopupWindow
										&& lightPopupWindow.isShowing()) {
									lightPopupWindow.dismiss();
								}
								if (textPopupWindow == null) {
									textPopupWindow = newsCommonMoreBiz
											.createTextPoputWindow();
								}
								if (!textPopupWindow.isShowing()) {
									if (mode == 1) {
										textPopupWindow
												.getContentView()
												.setBackgroundColor(
														getResources()
																.getColor(
																		R.color.white));
										TextView tv = (TextView) textPopupWindow
												.getContentView().findViewById(
														R.id.tvSize);
										tv.setTextColor(getResources()
												.getColor(R.color.deep_black));
									} else {
										textPopupWindow
												.getContentView()
												.setBackgroundColor(
														getResources()
																.getColor(
																		R.color.popuSetTextView));
										TextView tv = (TextView) textPopupWindow
												.getContentView().findViewById(
														R.id.tvSize);
										tv.setTextColor(getResources()
												.getColor(R.color.popuNumColor));
									}
									textPopupWindow
											.showAtLocation(
													getWindow().getDecorView(),
													Gravity.BOTTOM,
													0,
													getResources()
															.getInteger(
																	R.integer.menu_popupwindow_height));
									int apiLevel = android.os.Build.VERSION.SDK_INT;
									if (apiLevel > 10) {
										// m_vViewPager.setAlpha(0.5f);
									}
								} else {
									textPopupWindow.dismiss();
									int apiLevel = android.os.Build.VERSION.SDK_INT;
									if (apiLevel > 10) {
										// m_vViewPager.setAlpha(1.0f);
									}
								}
							} else {

								if (ttsSetingPop != null) {
									ttsSetingPop.dismiss();

								}

								// m_aPagerAdapter.getItem(m_iCurrentIndex);
								// setTvSpeakColor("");
								if (ttsBiz.isStop()) {

									// String newsContent = m_aNewsInfoList.get(
									// m_iCurrentIndex).getNewsContent();
									if (StringTools.isNotEmpty(currentContent)) {
										// ttsBiz.speakNewsContent(newsContent,
										// m_iCurrentIndex, m_iMaxPage);
										resetMenuBtnImg(
												R.drawable.popup_btn_previous,
												R.drawable.popup_btn_stop,
												R.drawable.popup_btn_next,
												R.drawable.popup_btn_set);
										ttsBiz.start();
										clickStop = false;
									}

								} else {
									// wfNewsDetailFragment.updateTtsUi(
									// currentContent, false);
									ttsBiz.stop();
									resetMenuBtnImg(
											R.drawable.popup_btn_previous,
											R.drawable.popup_btn_play,
											R.drawable.popup_btn_next,
											R.drawable.popup_btn_set);
									clickStop = true;
								}
								dismissPopInMsg();
								Tools.showLog("tts", "点击了停止按钮clickStop:"
										+ clickStop);
							}

						}

						@Override
						public void clickBtn3() {

							if (!isSpeaking) {

								// 弹出改变过亮度的窗口
								if (null != textPopupWindow
										&& textPopupWindow.isShowing()) {
									textPopupWindow.dismiss();
								}
								int apiLevel = android.os.Build.VERSION.SDK_INT;
								if (apiLevel > 10) {
									// m_vViewPager.setAlpha(1.0f);
								}
								if (lightPopupWindow == null) {
									lightPopupWindow = newsCommonMoreBiz
											.createLightPoputWindow();
								}
								if (!lightPopupWindow.isShowing()) {
									if (mode == 1) {
										lightPopupWindow
												.getContentView()
												.setBackgroundColor(
														getResources()
																.getColor(
																		R.color.white));
										TextView tv = (TextView) lightPopupWindow
												.getContentView().findViewById(
														R.id.tvSize);
										tv.setTextColor(getResources()
												.getColor(R.color.deep_black));
									} else {
										lightPopupWindow
												.getContentView()
												.setBackgroundColor(
														getResources()
																.getColor(
																		R.color.popuSetTextView));
										TextView tv = (TextView) lightPopupWindow
												.getContentView().findViewById(
														R.id.tvSize);
										tv.setTextColor(getResources()
												.getColor(R.color.popuNumColor));
									}
									lightPopupWindow
											.showAtLocation(
													getWindow().getDecorView(),
													Gravity.BOTTOM,
													0,
													getResources()
															.getInteger(
																	R.integer.menu_popupwindow_height));
								} else {
									lightPopupWindow.dismiss();
								}
							} else {
								Tools.showLog("tts", "点击了下一条");
								if (ttsSetingPop != null) {
									ttsSetingPop.dismiss();

								}
								// if (readWhat) {
								// String content = m_aNewsInfoList.get(
								// m_iCurrentIndex).getNewsContent();
								if (StringTools.isEmpty(currentContent)) {
									isSpeaking = false;
									resetMenuBtnImg(R.drawable.popup_btn_speak,
											R.drawable.popup_btn_text,
											R.drawable.popup_btn_light,
											R.drawable.popup_btn_ye);
									return;
								}

								// }
								wfNewsDetailFragment.updateTtsUi(
										currentContent, false);
								ttsBiz.next();
								dismissPopInMsg();
							}

						}

						@Override
						public void clickBtn4() {
							ttsSetingPop = newsCommonMoreBiz
									.createTtsSettingWindow(
											maxVolume,
											audioManager
													.getStreamVolume(AudioManager.STREAM_MUSIC),
											Tools.getInformain(
													Tools.TTS_SPEECH, 10,
													mContext), Tools
													.getBooleanInformain(
															Tools.TTS_MODE,
															false, mContext));

							Tools.showLog("tts", "点击第四按钮时isSpeaking："
									+ isSpeaking);
							if (!isSpeaking) {
								Tools.showLog("zf", "点击了日夜模式：" + mode);
								if (mode == 1) {
									// 变换黑夜模式
									if (wfNewsDetailFragment != null) {
										wfNewsDetailFragment.styleChange(2);
									}
									if (null != textPopupWindow
											&& textPopupWindow.isShowing()) {
										textPopupWindow
												.getContentView()
												.setBackgroundColor(
														getResources()
																.getColor(
																		R.color.popuSetTextView));
										TextView tv = (TextView) textPopupWindow
												.getContentView().findViewById(
														R.id.tvSize);
										tv.setTextColor(getResources()
												.getColor(R.color.popuNumColor));
									}
									if (null != lightPopupWindow
											&& lightPopupWindow.isShowing()) {
										lightPopupWindow
												.getContentView()
												.setBackgroundColor(
														getResources()
																.getColor(
																		R.color.popuSetTextView));
										TextView tv = (TextView) lightPopupWindow
												.getContentView().findViewById(
														R.id.tvSize);
										tv.setTextColor(getResources()
												.getColor(R.color.popuNumColor));
									}
									dayNightModeBiz.setMode(2);
									mode = 2;

								} else {
									// 变换白天模式
									if (wfNewsDetailFragment != null) {
										wfNewsDetailFragment.styleChange(1);
									}
									if (null != textPopupWindow
											&& textPopupWindow.isShowing()) {
										textPopupWindow
												.getContentView()
												.setBackgroundColor(
														getResources()
																.getColor(
																		R.color.white));
										TextView tv = (TextView) textPopupWindow
												.getContentView().findViewById(
														R.id.tvSize);
										tv.setTextColor(getResources()
												.getColor(R.color.deep_black));
									}
									if (null != lightPopupWindow
											&& lightPopupWindow.isShowing()) {
										lightPopupWindow
												.getContentView()
												.setBackgroundColor(
														getResources()
																.getColor(
																		R.color.white));
										TextView tv = (TextView) lightPopupWindow
												.getContentView().findViewById(
														R.id.tvSize);
										tv.setTextColor(getResources()
												.getColor(R.color.deep_black));
									}
									dayNightModeBiz.setMode(1);
									mode = 1;

								}
								ImageButton button = (ImageButton) menuPopup
										.getContentView().findViewById(
												R.id.popup_menu_btn4);
								if (mode == 1) {
									button.setBackgroundResource(R.drawable.popup_btn_ye);
								} else {
									button.setBackgroundResource(R.drawable.popup_btn_ri);
								}
							} else {
								Tools.showLog("tts", "点击了设置语音");

								if (!ttsSetingPop.isShowing()) {
									ttsSetingPop
											.showAtLocation(
													getWindow().getDecorView(),
													Gravity.BOTTOM,
													0,
													getResources()
															.getInteger(
																	R.integer.menu_popupwindow_height));
								} else {
									ttsSetingPop.dismiss();
								}
								dismissPopInMsg();
							}

						}

					}, R.drawable.popup_btn_speak, R.drawable.popup_btn_text,
					R.drawable.popup_btn_light, R.drawable.popup_btn_ye);
			menuPopup.setOnDismissListener(new OnDismissListener() {

				@Override
				public void onDismiss() {
					if (null != lightPopupWindow
							&& lightPopupWindow.isShowing()) {
						lightPopupWindow.dismiss();
					}
					if (null != textPopupWindow && textPopupWindow.isShowing()) {
						textPopupWindow.dismiss();
					}
					showSpeakIcon();
					int apiLevel = android.os.Build.VERSION.SDK_INT;
					if (apiLevel > 10) {
						// m_vViewPager.setAlpha(1.0f);
					}
				}
			});
		} else {
			registMenuPopup2(
					new MenuPopupListener() {

						@Override
						public void clickBtn1() {
							// exitByFromWhere();
							// overridePendingTransition(R.anim.slide_down_in,
							// R.anim.slide_down_out);
							if (!isSpeaking) {

								Tools.showLog("tts",
										"tts can use:" + ttsBiz.isCanbeUse());
								if (Build.VERSION.SDK_INT < 14) {
									toastTV.setText("系统版本过低,请升级后再试.");
									toast.show();
									return;
								}

								if (ttsBiz.checkTtsEngine()) {
									// ttsBiz.speakNewsMain(newsInfos,
									// currentPage);
									// String newsContent = m_aNewsInfoList.get(
									// m_iCurrentIndex).getNewsContent();
									// ttsBiz.speakNewsContent(newsContent,
									// m_iCurrentIndex, m_iMaxPage);
									noMoreAsk = Tools.getBooleanInformain(
											Tools.TTS_NOMORE_ASK, false,
											mContext);
									if (!noMoreAsk) {
										TtsSettingFragment ttsFragment = new TtsSettingFragment(
												2);
										ttsFragment
												.setPositiveListener(new onPositiveListener() {

													@Override
													public void onClickPositiveBtn() {
														// if (readWhat) {
														// String content =
														// m_aNewsInfoList
														// .get(m_iCurrentIndex)
														// .getNewsContent();
														// if (StringTools
														// .isEmpty(content)) {
														// return;
														// }

														// }

														isSpeaking = true;
														resetMenuBtnImg(
																R.drawable.popup_btn_previous,
																R.drawable.popup_btn_stop,
																R.drawable.popup_btn_next,
																R.drawable.popup_btn_set);
														showSpeakIcon();
														ttsBiz.start();
														if (currentContent
																.equals(title)) {
															wfNewsDetailFragment
																	.updateTitleTtsUi(true);
														} else {
															wfNewsDetailFragment
																	.updateTtsUi(
																			currentContent,
																			true);
														}
													}
												});
										ttsFragment.show(
												getSupportFragmentManager(),
												"ttsFragment");

									} else {

										// 检测是否有语音功能，没有则弹出下载语音引擎对话框，有则查看pf是否记录有不在提示，无则弹出设置对话框，有则直接开始朗读。
										// if (StringTools.isEmpty(newsContent))
										// {
										// return;
										// }
										isSpeaking = true;
										Tools.showLog("tts", "点击了开始阅读："
												+ isSpeaking);
										resetMenuBtnImg(
												R.drawable.popup_btn_previous,
												R.drawable.popup_btn_stop,
												R.drawable.popup_btn_next,
												R.drawable.popup_btn_set);
										ttsBiz.start();
										// ((NewsComomFragment) m_aPagerAdapter
										// .getItem(0)).setTtsTitleTv();
										// setTvSpeakColor(null);
										// showSpeakIcon();
										wfNewsDetailFragment
												.updateTitleTtsUi(true);
									}
								} else {
									if (downSpeakEngineDialog == null) {
										downSpeakEngineDialog = OperDialog
												.getOperDialog()
												.createDownSpeakEngineDialog(
														mContext);

									}
									downSpeakEngineDialog.show();
								}

							} else {
								if (ttsSetingPop != null) {
									ttsSetingPop.dismiss();

								}
								// if (readWhat) {

								// String content = m_aNewsInfoList.get(
								// m_iCurrentIndex).getNewsContent();
								if (StringTools.isEmpty(currentContent)) {
									isSpeaking = false;
									resetMenuBtnImg(R.drawable.popup_btn_speak,
											R.drawable.popup_btn_text,
											R.drawable.popup_btn_light,
											R.drawable.popup_btn_ye);
									return;
								}

								// }
								wfNewsDetailFragment.updateTtsUi(
										currentContent, false);
								ttsBiz.previous();
								Tools.showLog("tts", "点击了上一条");
								dismissPopInMsg();
							}
						}

						@Override
						public void clickBtn2() {

							if (!isSpeaking) {
								if (null != lightPopupWindow
										&& lightPopupWindow.isShowing()) {
									lightPopupWindow.dismiss();
								}
								// 弹出调整字体大小的窗口
								if (textPopupWindow == null) {
									textPopupWindow = newsCommonMoreBiz
											.createTextPoputWindow();
								}
								if (!textPopupWindow.isShowing()) {
									if (mode == 1) {
										textPopupWindow
												.getContentView()
												.setBackgroundColor(
														getResources()
																.getColor(
																		R.color.white));
										TextView tv = (TextView) textPopupWindow
												.getContentView().findViewById(
														R.id.tvSize);
										tv.setTextColor(getResources()
												.getColor(R.color.deep_black));
									} else {
										textPopupWindow
												.getContentView()
												.setBackgroundColor(
														getResources()
																.getColor(
																		R.color.popuSetTextView));
										TextView tv = (TextView) textPopupWindow
												.getContentView().findViewById(
														R.id.tvSize);
										tv.setTextColor(getResources()
												.getColor(R.color.popuNumColor));
									}
									textPopupWindow
											.showAtLocation(
													getWindow().getDecorView(),
													Gravity.BOTTOM,
													0,
													getResources()
															.getInteger(
																	R.integer.menu_popupwindow_height));
									int apiLevel = android.os.Build.VERSION.SDK_INT;
									if (apiLevel > 10) {
										// m_vViewPager.setAlpha(0.5f);
									}
								} else {
									textPopupWindow.dismiss();
									int apiLevel = android.os.Build.VERSION.SDK_INT;
									if (apiLevel > 10) {
										// m_vViewPager.setAlpha(1.0f);
									}
								}
							} else {

								if (ttsSetingPop != null) {
									ttsSetingPop.dismiss();

								}
								if (ttsBiz.isStop()) {

									// ttsBiz.speakNewsContent(m_aNewsInfoList
									// .get(m_iCurrentIndex)
									// .getNewsContent(), m_iCurrentIndex,
									// m_iMaxPage);
									resetMenuBtnImg(
											R.drawable.popup_btn_previous,
											R.drawable.popup_btn_stop,
											R.drawable.popup_btn_next,
											R.drawable.popup_btn_set);
									ttsBiz.start();

									clickStop = false;
								} else {
									ttsBiz.stop();
									resetMenuBtnImg(
											R.drawable.popup_btn_previous,
											R.drawable.popup_btn_play,
											R.drawable.popup_btn_next,
											R.drawable.popup_btn_set);
									clickStop = true;
								}
								dismissPopInMsg();
								Tools.showLog("tts", "点击了停止按钮clickStop:"
										+ clickStop);
							}

						}

						@Override
						public void clickBtn3() {
							if (!isSpeaking) {

								if (null != textPopupWindow
										&& textPopupWindow.isShowing()) {
									textPopupWindow.dismiss();
								}
								int apiLevel = android.os.Build.VERSION.SDK_INT;
								if (apiLevel > 10) {
									// m_vViewPager.setAlpha(1.0f);
								}
								// 弹出改变过亮度的窗口
								if (lightPopupWindow == null) {
									lightPopupWindow = newsCommonMoreBiz
											.createLightPoputWindow();
								}
								if (!lightPopupWindow.isShowing()) {
									if (mode == 1) {
										lightPopupWindow
												.getContentView()
												.setBackgroundColor(
														getResources()
																.getColor(
																		R.color.white));
										TextView tv = (TextView) lightPopupWindow
												.getContentView().findViewById(
														R.id.tvSize);
										tv.setTextColor(getResources()
												.getColor(R.color.deep_black));
									} else {
										lightPopupWindow
												.getContentView()
												.setBackgroundColor(
														getResources()
																.getColor(
																		R.color.popuSetTextView));
										TextView tv = (TextView) lightPopupWindow
												.getContentView().findViewById(
														R.id.tvSize);
										tv.setTextColor(getResources()
												.getColor(R.color.popuNumColor));
									}
									lightPopupWindow
											.showAtLocation(
													getWindow().getDecorView(),
													Gravity.BOTTOM,
													0,
													getResources()
															.getInteger(
																	R.integer.menu_popupwindow_height));
								} else {
									lightPopupWindow.dismiss();
								}
							} else {
								Tools.showLog("tts", "点击了下一条");
								if (ttsSetingPop != null) {
									ttsSetingPop.dismiss();

								}
								// if (readWhat) {
								// String content = m_aNewsInfoList.get(
								// m_iCurrentIndex).getNewsContent();
								if (StringTools.isEmpty(currentContent)) {
									isSpeaking = false;
									resetMenuBtnImg(R.drawable.popup_btn_speak,
											R.drawable.popup_btn_text,
											R.drawable.popup_btn_light,
											R.drawable.popup_btn_ye);
									return;
								}

								// }
								wfNewsDetailFragment.updateTtsUi(
										currentContent, false);
								ttsBiz.next();
								dismissPopInMsg();
							}

						}

						@Override
						public void clickBtn4() {
							ttsSetingPop = newsCommonMoreBiz
									.createTtsSettingWindow(
											maxVolume,
											audioManager
													.getStreamVolume(AudioManager.STREAM_MUSIC),
											Tools.getInformain(
													Tools.TTS_SPEECH, 10,
													mContext), Tools
													.getBooleanInformain(
															Tools.TTS_MODE,
															false, mContext));
							Tools.showLog("tts", "点击第四按钮时isSpeaking："
									+ isSpeaking);
							if (!isSpeaking) {

								Tools.showLog("zf", "点击了日夜模式：" + mode);
								if (mode == 1) {
									// 变换黑夜模式
									if (wfNewsDetailFragment != null) {
										wfNewsDetailFragment.styleChange(2);
									}
									if (null != textPopupWindow
											&& textPopupWindow.isShowing()) {
										textPopupWindow
												.getContentView()
												.setBackgroundColor(
														getResources()
																.getColor(
																		R.color.popuSetTextView));
										TextView tv = (TextView) textPopupWindow
												.getContentView().findViewById(
														R.id.tvSize);
										tv.setTextColor(getResources()
												.getColor(R.color.popuNumColor));
									}
									if (null != lightPopupWindow
											&& lightPopupWindow.isShowing()) {
										lightPopupWindow
												.getContentView()
												.setBackgroundColor(
														getResources()
																.getColor(
																		R.color.popuSetTextView));
										TextView tv = (TextView) lightPopupWindow
												.getContentView().findViewById(
														R.id.tvSize);
										tv.setTextColor(getResources()
												.getColor(R.color.popuNumColor));
									}
									dayNightModeBiz.setMode(2);
									mode = 2;
									changeBackgroud(mode);
								} else {
									// 变换白天模式
									if (wfNewsDetailFragment != null) {
										wfNewsDetailFragment.styleChange(1);
									}
									if (null != textPopupWindow
											&& textPopupWindow.isShowing()) {
										textPopupWindow
												.getContentView()
												.setBackgroundColor(
														getResources()
																.getColor(
																		R.color.white));
										TextView tv = (TextView) textPopupWindow
												.getContentView().findViewById(
														R.id.tvSize);
										tv.setTextColor(getResources()
												.getColor(R.color.deep_black));
									}
									if (null != lightPopupWindow
											&& lightPopupWindow.isShowing()) {
										lightPopupWindow
												.getContentView()
												.setBackgroundColor(
														getResources()
																.getColor(
																		R.color.white));
										TextView tv = (TextView) lightPopupWindow
												.getContentView().findViewById(
														R.id.tvSize);
										tv.setTextColor(getResources()
												.getColor(R.color.deep_black));
									}
									dayNightModeBiz.setMode(1);
									mode = 1;
									changeBackgroud(mode);
								}
								ImageButton button = (ImageButton) menuPopup
										.getContentView().findViewById(
												R.id.popup_menu_btn4);
								if (mode == 1) {
									button.setBackgroundResource(R.drawable.popup_btn_ye);
								} else {
									button.setBackgroundResource(R.drawable.popup_btn_ri);
								}
							} else {
								Tools.showLog("tts", "点击了设置语音");

								if (!ttsSetingPop.isShowing()) {
									ttsSetingPop
											.showAtLocation(
													getWindow().getDecorView(),
													Gravity.BOTTOM,
													0,
													getResources()
															.getInteger(
																	R.integer.menu_popupwindow_height));
								} else {
									ttsSetingPop.dismiss();
								}
								dismissPopInMsg();
							}

						}

					}, R.drawable.popup_btn_speak, R.drawable.popup_btn_text,
					R.drawable.popup_btn_light, R.drawable.popup_btn_ri);
			menuPopup.setOnDismissListener(new OnDismissListener() {

				@Override
				public void onDismiss() {
					if (null != lightPopupWindow
							&& lightPopupWindow.isShowing()) {
						lightPopupWindow.dismiss();
					}
					if (null != textPopupWindow && textPopupWindow.isShowing()) {
						textPopupWindow.dismiss();
					}
					showSpeakIcon();

					int apiLevel = android.os.Build.VERSION.SDK_INT;
					if (apiLevel > 10) {
						// m_vViewPager.setAlpha(1.0f);
					}
				}
			});
		}

	}

}
