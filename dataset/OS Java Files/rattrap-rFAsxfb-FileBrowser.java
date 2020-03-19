package xiaowang.filebrowser.bean;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import android.os.Environment;

import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.tools.ant.taskdefs.Sleep;
import org.jason.lxcoff.lib.Configuration;
import org.jason.lxcoff.lib.ControlMessages;
import org.jason.lxcoff.lib.ExecutionController;

import xiaowang.filebrowser.adapter.FileSimpleAdapter;
import xiaowang.filebrowser.biz.FileHelper;
import xiaowang.filebrowser.biz.FileMD5;
import xiaowang.filebrowser.biz.FileOperator;
import xiaowang.filebrowser.biz.MIMEType;
import xiaowang.filebrowser.widget.MarqueeView;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.provider.Settings.Secure;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AbsListView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.Toast;

/*
 * �ļ��б���ͼ��Ĭ��SD����
 * ���±��� �����Ƿ�ΪĿ¼ �ж� 
 * 1.�����ļ�������ת
 * 2.�����ļ��У����ػ�Ŀ���ļ��е��ļ��б�
 * ע�⣺Ҫ�޸��ļ���ҪȨ�ޣ���AndroidManifest.xml�����
 * <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"></uses-permission>
 */

public class FileBrowser extends ListActivity implements
		OnSharedPreferenceChangeListener {
	private boolean firstSort = true;// �Ƿ������ļ��к����ļ�
	private List<File> fileList;
	private ListView listv;
	private File file, fileNow, fileParent;
	private int filePosition = 0;
	private boolean isFlag;// �Ƿ��·��ؼ��ı�ʶ
	private FileSimpleAdapter adapter;
	private int fsd;// �ļ�������ʽ
	private int fsf;// �ļ�����ʽ
	private SharedPreferences.Editor editor;
	private SharedPreferences sp;
	private List<Integer> fileSelecteds;
	private List<File> moves = new ArrayList<File>();
	private List<File> copys = new ArrayList<File>();
	private MarqueeView marqueeView;
	private ProgressBar progressBar;
	private Dialog menuDialog;
	private static int apkIndex = 0;

	private static String TAG = "FileBrowser";

	public static HashMap<String, String> fileSignature = new HashMap<String, String>();
	private final String DATABASE_PATH = ControlMessages.CONTAINER_APK_DIR
			+ "dictionary";
	private final String DATABASE_FILENAME = "dictionary.db2";
	private final String SCAN_FILE_PATH = ControlMessages.CONTAINER_APK_DIR
			+ "off-file/";
	SQLiteDatabase database;
	public static ArrayList<String> tempPath = new ArrayList<String>();
	PackageManager pm;
	public static List<ApplicationInfo> badApp_infos;

	Context context;
	private Configuration config;
	private ExecutionController executionController;
	private Socket dirServiceSocket = null;
	InputStream is = null;
	OutputStream os = null;
	ObjectOutputStream oos = null;
	ObjectInputStream ois = null;

	SharedPreferences settings;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// requestWindowFeature(Window.FEATURE_NO_TITLE);//ע��˳��
		// getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		// WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.listview);// ע��˳��

		// I wanna use network in main thread, so ...
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}

		settings = PreferenceManager.getDefaultSharedPreferences(this);
		settings.registerOnSharedPreferenceChangeListener(this);

		this.context = this;
		ExecutionController.myId = Secure.getString(
				context.getContentResolver(),
				android.provider.Settings.Secure.ANDROID_ID);
		createNotOffloadedFile();

		try {
			getInfoFromDirService();

		} catch (FileNotFoundException e) {
			Log.e(TAG, "Could not read the config file: "
					+ ControlMessages.PHONE_CONFIG_FILE);
			return;
		} catch (UnknownHostException e) {
			Log.e(TAG, "Could not connect: " + e.getMessage());
		} catch (IOException e) {
			Log.e(TAG, "IOException: " + e.getMessage());
			// return ;
		} catch (ClassNotFoundException e) {
			Log.e(TAG, "Could not find Clone class: " + e.getMessage());
			return;
		}
		// Create an execution controller
		this.executionController = new ExecutionController(
				this.dirServiceSocket, is, os, ois, oos,
				context.getPackageName(), context.getPackageManager(), context);

		readPrefs();

		// database = openDatabase();
		pm = getPackageManager();

		listv = getListView();
		sp = getSharedPreferences("setting", Activity.MODE_PRIVATE);
		editor = sp.edit();
		if (sp.contains("dir_sort")) {
			fsd = sp.getInt("dir_sort", -1);
			fsf = sp.getInt("file_sort", 0);
			firstSort = sp.getBoolean("first_sort", true);
		} else {
			fsd = FileOperator.NAME_ASC;
			fsf = FileOperator.NAME_ASC;
		}

		registerForContextMenu(listv);

		progressBar = (ProgressBar) findViewById(R.id.title_progressbar);
		marqueeView = (MarqueeView) findViewById(R.id.marquee_text);
		marqueeView.setText("/sdcard");

		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			File path = Environment.getExternalStorageDirectory();
			fileList(path);

		}

		listv.setOnScrollListener(new OnScrollListener() {

			public void onScrollStateChanged(AbsListView view, int scrollState) {
				switch (scrollState) {
				case SCROLL_STATE_FLING:
				case SCROLL_STATE_TOUCH_SCROLL:
					// adapter.setLoad(false);
					break;
				case SCROLL_STATE_IDLE:
					// adapter.setLoad(true);
					// adapter.notifyDataSetChanged();
					break;
				}
			}

			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {

			}
		});
		listv.setOnItemLongClickListener(new OnItemLongClickListener() {

			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				fileNow = fileList.get(position);
				filePosition = position;
				removeDialog(1);
				removeDialog(2);
				TableLayout tableLayout = (TableLayout) getLayoutInflater()
						.inflate(R.layout.menu_item, null);
				menuDialog = new AlertDialog.Builder(FileBrowser.this).setView(
						tableLayout).create();
				menuDialog.show();
				menuDialog.setCanceledOnTouchOutside(true);
				return true;
			}
		});
	}

	/**
	 * Create an empty file on the phone in order to let the method know where
	 * is being executed (on the phone or on the clone).
	 */
	private void createNotOffloadedFile() {
		try {
			File f = new File(ControlMessages.FILE_NOT_OFFLOADED);
			f.createNewFile();
		} catch (FileNotFoundException e) {
			Log.e(TAG, e.getMessage());
		} catch (IOException e) {
			Log.e(TAG, e.getMessage());
		}
	}

	/**
	 * Read the config file to get the IP and port for DirectoryService.
	 * 
	 * @throws IOException
	 * @throws UnknownHostException
	 * @throws ClassNotFoundException
	 */
	private void getInfoFromDirService() throws UnknownHostException,
			IOException, ClassNotFoundException {
		config = new Configuration(ControlMessages.PHONE_CONFIG_FILE);
		config.parseConfigFile(null, null);

		try {
			this.dirServiceSocket = new Socket();
			this.dirServiceSocket.connect(
					new InetSocketAddress(config.getDirServiceIp(), config
							.getDirServicePort()), 3000);
			this.os = this.dirServiceSocket.getOutputStream();
			this.is = this.dirServiceSocket.getInputStream();

			os.write(ControlMessages.PHONE_CONNECTION);

			oos = new ObjectOutputStream(os);
			ois = new ObjectInputStream(is);

			// Send the name and id to DirService
			os.write(ControlMessages.PHONE_AUTHENTICATION);
			oos.writeObject(ExecutionController.myId);
			oos.flush();

		} finally {

		}
	}

	// ��registerReceiver����ע��㲥������unregisterReceiver���������������ǳɶԳ��ֵġ�
	// �����onCreate����������ע��㲥������onDestroy�����������ͷš�
	// �����onResume����������ע��㲥������onPause�����������ͷš�
	private final BroadcastReceiver sdcardListener = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {

			String action = intent.getAction();

			if (Intent.ACTION_MEDIA_MOUNTED.equals(action)
					|| Intent.ACTION_MEDIA_SCANNER_FINISHED.equals(action)) {
				File path = Environment.getExternalStorageDirectory();
				fileList(path);
			}
		}
	};

	private void registerSDCardListener() {
		IntentFilter intentFilter = new IntentFilter(
				Intent.ACTION_MEDIA_MOUNTED);
		intentFilter.addAction(Intent.ACTION_MEDIA_SCANNER_STARTED);
		intentFilter.addAction(Intent.ACTION_MEDIA_SCANNER_FINISHED);
		intentFilter.addAction(Intent.ACTION_MEDIA_REMOVED);
		intentFilter.addAction(Intent.ACTION_MEDIA_UNMOUNTED);
		intentFilter.addAction(Intent.ACTION_MEDIA_BAD_REMOVAL);
		intentFilter.addDataScheme("file");
		registerReceiver(sdcardListener, intentFilter);
	}

	protected void onResume() {

		super.onResume();
		registerSDCardListener();

	}

	protected void onPause() {

		super.onPause();
		unregisterReceiver(sdcardListener);
	}

	// ��ȡ�ļ��б�,������listView
	public void fileList(final File path) {
		progressBar.setVisibility(View.VISIBLE);
		new AsyncTask<String, String, String>() {

			protected String doInBackground(String... params) {
				ArrayList<HashMap<String, Object>> data = FileOperator
						.fileList(FileBrowser.this, path, fsd, fsf, firstSort);
				fileList = FileOperator.fileList;
				adapter = new FileSimpleAdapter(FileBrowser.this, data,
						R.layout.listfiles, new String[] { "fileIcon",
								"fileName", "fileInfo", "checkBox" },
						new int[] { R.id.fileIcon, R.id.fileName,
								R.id.fileInfo, R.id.fileCheckBox });
				return null;
			}

			protected void onPostExecute(String result) {
				progressBar.setVisibility(View.GONE);
				// ����ListActivity����ķ���
				// ͬsetContentView��Activity�����е�����
				setListAdapter(adapter);
				// �б���¶���
				Animation anim = AnimationUtils.loadAnimation(FileBrowser.this,
						R.anim.zoomin);
				listv.startAnimation(anim);
				if (isFlag)
					listv.setSelection(filePosition);
				super.onPostExecute(result);
			}
		}.execute();

	}

	// �б������Ķ���
	// 1.������ļ��� �����û�ͼ �����ļ���
	// 2.������ļ� ������� ��ת��Ļ(Activity)
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		// positionΪ������ڵ�λ�� ����������ȡ�ö�Ӧ�ļ�
		file = fileList.get(position);
		filePosition = position;

		if (file.isDirectory() && file.canRead()) {

			File[] f = file.listFiles();
			if (f.length > 0) {
				fileParent = file;
				// marqueeView.setText(file.getAbsolutePath().substring(4));
				marqueeView.setText(file.getAbsolutePath());
				isFlag = false;
				fileList(file);
			} else {
				fileParent = file.getParentFile();
				Toast.makeText(this, "�ļ���Ϊ��", Toast.LENGTH_LONG).show();
			}

		} else {
			MIMEType.openFile(file, this);
		}
		overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
	}

	File moveFile;
	FileInputStream fis = null;
	String str;

	public void onClick(View item) {

		switch (item.getId()) {
		case R.id.btn_move:
			fileSelecteds = adapter.getSelecteds();
			if (fileSelecteds != null) {
				for (Integer i : fileSelecteds) {
					moveFile = fileList.get(i);
					moves.add(moveFile);
				}
			}

			break;
		case R.id.btn_moveto:
			if (!moves.isEmpty()) {
				for (File move : moves) {
					FileOperator.move(move, fileNow);
				}
				Toast.makeText(FileBrowser.this, "�ƶ����", Toast.LENGTH_LONG)
						.show();

				adapter.noneIsSelected();
				moves.removeAll(moves);
				fileSelecteds = null;
				isFlag = true;
				fileList(fileNow.getParentFile());
			} else {
				Toast.makeText(FileBrowser.this, "û���ļ����ƶ����빴ѡ�ļ����ƶ�",
						Toast.LENGTH_LONG).show();
			}

			break;
		case R.id.btn_delete:
			fileSelecteds = adapter.getSelecteds();

			showDialog(1);

			break;
		case R.id.btn_rename:

			showDialog(2);
			break;
		case R.id.btn_compress:

			new AsyncTask<Object, Integer, Void>() {

				protected void onPreExecute() {
					super.onPreExecute();
					showDialog(4);
				}

				protected Void doInBackground(Object... params) {

					try {
						FileOperator.CreateZipFile(fileNow.getAbsolutePath(),
								fileNow.getAbsolutePath() + ".zip");
					} catch (Exception e) {

						e.printStackTrace();
					}

					return null;
				}

				protected void onProgressUpdate(Integer... values) {
					super.onProgressUpdate(values);

				}

				protected void onPostExecute(Void result) {
					dismissDialog(4);
					Toast.makeText(FileBrowser.this, "ѹ�����", Toast.LENGTH_LONG)
							.show();
					fileList(fileNow.getParentFile());
					listv.setSelection(filePosition);
					super.onPostExecute(result);
				}

			}.execute();
			break;

		case R.id.btn_uncompress:
			new AsyncTask<String, Integer, Void>() {

				protected void onPreExecute() {
					super.onPreExecute();
					showDialog(4);
				}

				protected Void doInBackground(String... params) {

					try {
						FileOperator.deCompress(fileNow.getAbsolutePath(),
								fileNow.getParent());
					} catch (Exception e) {
						e.printStackTrace();
					}
					return null;
				}

				protected void onProgressUpdate(Integer... values) {

					super.onProgressUpdate(values);
				}

				protected void onPostExecute(Void result) {
					dismissDialog(4);
					Toast.makeText(FileBrowser.this, "��ѹ�����", Toast.LENGTH_LONG)
							.show();
					fileList(fileNow.getParentFile());
					super.onPostExecute(result);
				}

			}.execute();

			break;
		case R.id.btn_copy:
			fileSelecteds = adapter.getSelecteds();
			if (fileSelecteds != null) {
				for (Integer i : fileSelecteds) {
					moveFile = fileList.get(i);
					copys.add(moveFile);
				}
			}
			break;
		case R.id.btn_post:
			if (new File("system/xbin/cp").exists()) {
				new AsyncTask<Object, Integer, String>() {

					protected void onPreExecute() {
						showDialog(4);
					}

					protected String doInBackground(Object... params) {
						if (copys.isEmpty())
							return "failed";

						for (File copy : copys) {
							FileOperator.copy(copy, fileNow);
						}
						return "success";
					}

					protected void onPostExecute(String result) {
						removeDialog(4);
						if (result == "success") {
							Toast.makeText(FileBrowser.this, "�ļ���ճ��", 2000)
									.show();
							if (fileNow.isFile()) {
								fileList(fileNow.getParentFile());
							}
							adapter.noneIsSelected();
							copys.removeAll(copys);
							fileSelecteds = null;
						} else {
							Toast.makeText(FileBrowser.this,
									"δ���ļ������ƣ��빴ѡ�ļ�������", 2000).show();
						}
						super.onPostExecute(result);
					}
				}.execute();

			} else {
				new AsyncTask<Object, Integer, String>() {

					protected void onPreExecute() {

						super.onPreExecute();
						showDialog(4);
					}

					protected String doInBackground(Object... params) {
						if (copys.isEmpty())
							return "failed";

						for (File copy : copys) {
							if (fileNow.isDirectory()) {
								FileOperator.copy3(copy, fileNow);
							} else {
								FileOperator.copy3(copy,
										fileNow.getParentFile());
							}
						}
						return "success";
					}

					@SuppressLint("NewApi")
					protected void onPostExecute(String result) {

						super.onPostExecute(result);
						dismissDialog(4);
						if (result == "success") {
							Toast.makeText(FileBrowser.this, "ճ�������",
									Toast.LENGTH_LONG).show();
							fileList(fileNow.getParentFile());
							listv.setSelection(filePosition);
							adapter.noneIsSelected();
							copys.removeAll(copys);
							fileSelecteds = null;
						} else {
							Toast.makeText(FileBrowser.this,
									"δ���ļ������ƣ��빴ѡ�ļ�������", Toast.LENGTH_LONG)
									.show();
						}
					}
				}.execute();
			}

			break;
		case R.id.btn_newcreate:
			showDialog(0);
			break;
		default:
			break;
		}
		menuDialog.dismiss();
	}

	// ��д���·��ؼ��������¼�
	public void onBackPressed() {

		if (file == null) {
			super.onBackPressed();
		} else if (!fileParent.getName().equalsIgnoreCase("sdcard")) {
			isFlag = true;
			fileList(fileParent.getParentFile());

			fileParent = fileParent.getParentFile();
			marqueeView.setText(fileParent.getAbsolutePath());

		} else {
			finish();
		}
	}

	private ProgressDialog pd;
	private LinearLayout rename = null;
	private EditText etRename = null;

	protected Dialog onCreateDialog(int id) {
		rename = (LinearLayout) getLayoutInflater().inflate(R.layout.rename,
				null);
		etRename = (EditText) rename.findViewById(R.id.etRename);
		etRename.setText(fileNow.getName());
		LinearLayout mkDir = (LinearLayout) getLayoutInflater().inflate(
				R.layout.mkdir, null);
		final EditText etMkDir = (EditText) mkDir.findViewById(R.id.etMkDir);

		switch (id) {
		case 0:
			return new AlertDialog.Builder(this).setTitle("�������ļ���")
					.setView(mkDir)
					.setPositiveButton("ȷ��", new OnClickListener() {

						public void onClick(DialogInterface dialog, int which) {
							FileOperator.mkDir(fileNow, etMkDir.getText()
									.toString(), FileBrowser.this);
						}
					}).setNegativeButton("ȡ��", new OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {

						}
					}).create();

		case 1:

			return new AlertDialog.Builder(this)
					.setTitle("�Ƿ�ɾ���ļ���")
					.setPositiveButton("ȷ��",
							new DialogInterface.OnClickListener() {

								public void onClick(DialogInterface dialog,
										int which) {
									if (!fileSelecteds.isEmpty()) {
										for (Integer i : fileSelecteds) {
											moveFile = fileList.get(i);
											FileOperator.delete(moveFile,
													FileBrowser.this);
										}
										File[] f = moveFile.getParentFile()
												.listFiles();
										isFlag = true;
										if (f.length != 0) {
											fileList(moveFile.getParentFile());
										} else {
											marqueeView.setText(moveFile
													.getParentFile()
													.getParent());
											fileList(moveFile.getParentFile()
													.getParentFile());
										}
										if (fileList.size() > (Collections
												.min(fileSelecteds) + 1)
												&& Collections
														.min(fileSelecteds) - 1 >= 0) {
											listv.setSelection(Collections
													.min(fileSelecteds) - 1);
										} else {
											listv.setSelection(fileList.size() - 1);
										}
										adapter.noneIsSelected();
										fileSelecteds = null;
									} else {
										Toast.makeText(FileBrowser.this,
												"û���ļ���ɾ�����빴ѡ�ļ�",
												Toast.LENGTH_LONG).show();
									}
								}
							})
					.setNegativeButton("ȡ��",
							new DialogInterface.OnClickListener() {

								public void onClick(DialogInterface dialog,
										int which) {

								}
							}).create();
		case 2:
			return new AlertDialog.Builder(this)
					.setTitle("�ļ�������")
					.setView(rename)
					.setPositiveButton("ȷ��",
							new DialogInterface.OnClickListener() {

								public void onClick(DialogInterface dialog,
										int which) {

									fileNow.renameTo(new File(fileNow
											.getParentFile(), etRename
											.getText().toString()));

									fileList(fileNow.getParentFile());
									listv.setSelection(filePosition);
									Toast.makeText(FileBrowser.this, "�����������",
											1000).show();
								}
							})
					.setNegativeButton("ȡ��",
							new DialogInterface.OnClickListener() {

								public void onClick(DialogInterface dialog,
										int which) {

								}
							}).create();
		case 3:
			pd = new ProgressDialog(FileBrowser.this);
			pd.setCancelable(true);
			pd.setMax(100);
			pd.setMessage("���ڹ����У����Ժ򡣡���");
			pd.setIndeterminate(false);
			pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			pd.show();
			return pd;
		case 4:
			pd = new ProgressDialog(FileBrowser.this);
			pd.setCancelable(true);
			pd.setMax(100);
			pd.setMessage("���ڹ����У����Ժ򡣡���");
			pd.setIndeterminate(false);
			pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			pd.show();
			return pd;
		}
		return null;
	}

	public boolean onCreateOptionsMenu(Menu menu) {

		menu.add(1, 0, 0, "�ļ�����").setIcon(
				android.R.drawable.ic_menu_sort_alphabetically);
		menu.add(1, 1, 1, "�˳�").setIcon(android.R.drawable.ic_lock_power_off);
		menu.add(Menu.NONE, 100, 2, "Settings");
		menu.add(1, 3, 3, "����")
				.setIcon(android.R.drawable.ic_menu_info_details);
		menu.add(1, 4, 4, "����ɨ��").setIcon(android.R.drawable.ic_menu_search);
		menu.add(1, 5, 5, "Ȩ��ɨ��").setIcon(android.R.drawable.ic_menu_search);
		menu.add(1, 6, 6, "����").setIcon(android.R.drawable.ic_menu_search);
		menu.add(1, 7, 7, "����ɨ��").setIcon(android.R.drawable.ic_menu_search);
		menu.add(1, 2, 8, "�ļ�����").setIcon(android.R.drawable.ic_menu_search);
		return super.onCreateOptionsMenu(menu);
	}

	private String[] sorts = { "�����ļ��к����ļ�", "�����ļ������ļ���", "�ļ��а���ĸ��������",
			"�ļ��а���ĸ��������", "�ļ�����ĸ��������", "�ļ�����ĸ��������", "�ļ�����׺����������", "�ļ�����׺����������",
			"�ļ�����С��������", "�ļ�����С��������", "�ļ�/�ļ����������", "�ļ�/�ļ��н������" };
	private int index = 0;

	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case 0:
			new AlertDialog.Builder(this).setTitle("ѡ������ʽ")
					.setSingleChoiceItems(sorts, -1, new OnClickListener() {

						public void onClick(DialogInterface dialog, int which) {
							index = which;
						}
					}).setPositiveButton("ȷ��", new OnClickListener() {

						public void onClick(DialogInterface dialog, int which) {
							switch (index) {
							case 0:
								firstSort = true;
								break;
							case 1:
								firstSort = false;
								break;
							case 2:
								fsd = FileOperator.NAME_ASC;
								break;
							case 3:
								fsd = FileOperator.NAME_DESC;
								break;
							case 4:
								fsf = FileOperator.NAME_ASC;
								break;
							case 5:
								fsf = FileOperator.NAME_DESC;
								break;
							case 6:
								fsf = FileOperator.SUFFIX_ASC;
								break;
							case 7:
								fsf = FileOperator.SUFFIX_DESC;
								break;
							case 8:
								fsf = FileOperator.SIZE_ASC;
								break;
							case 9:
								fsf = FileOperator.SIZE_DESC;
								break;
							case 10:
								fsf = FileOperator.NAME_ASC;
								fsd = -1;
								break;
							case 11:
								fsf = FileOperator.NAME_DESC;
								fsd = -1;
								break;
							default:
								break;
							}
							editor.putInt("dir_sort", fsd);
							editor.putInt("file_sort", fsf);
							editor.putBoolean("first_sort", firstSort);
							editor.commit();
							if (file == null) {
								fileList(Environment
										.getExternalStorageDirectory());
								marqueeView.setText("/sdcard");
							} else if (file.isDirectory()) {
								fileList(file);
								marqueeView.setText(file.getAbsolutePath());
							} else {
								fileList(file.getParentFile());
								marqueeView.setText(file.getParent());
							}
						}
					}).create().show();
			break;
		case 1:
			FileHelper fileHelper = new FileHelper(FileBrowser.this);
			ArrayList<String> filePaths = fileHelper.getAllFiles(Environment
					.getExternalStorageDirectory());
			if (filePaths.size() == 0) {
				Log.d("jack", "filePathsΪ��");
			}
			for (String path : filePaths) {
				String deleteDirPath = path.substring(0, path.indexOf("/zip"));
				fileHelper.deleteSDFile(deleteDirPath);
			}
			FileHelper.zipList.clear();
			finish();
			break;
		case 2:
			resultList = new ArrayList<File>();
			LinearLayout layout = (LinearLayout) getLayoutInflater().inflate(
					R.layout.scan, null);
			editText = (EditText) layout.findViewById(R.id.edittext);
			new AlertDialog.Builder(this).setView(layout)
					.setPositiveButton("ȷ��", new OnClickListener() {

						public void onClick(DialogInterface dialog, int which) {
							new AsyncTask<Integer, Integer, String[]>() {

								private ProgressDialog dialog;

								// ǰ̨��ʾ
								protected void onPreExecute() {
									// ÿ��ɨ��ǰ��ǰһ�εĲ��ҽ�����
									resultList.clear();
									dialog = ProgressDialog.show(
											FileBrowser.this, "",
											"��������,���Ժ�....");
									super.onPreExecute();
								}

								// ��ִ̨��
								protected String[] doInBackground(
										Integer... params) {
									if (!android.os.Environment
											.getExternalStorageState()
											.equals(android.os.Environment.MEDIA_MOUNTED)) {

									} else {
										if (!editText.getText().toString()
												.equals("")) {
											GetFiles(new File(marqueeView
													.getText().toString()));
										}
									}
									return null;
								}

								// ִ�����
								protected void onPostExecute(String[] result) {
									dialog.dismiss();
									if (editText.getText().toString()
											.equals("")) {
										Toast.makeText(FileBrowser.this,
												"��������Ч��Ϣ", Toast.LENGTH_SHORT)
												.show();
									} else {
										Toast.makeText(FileBrowser.this,
												"ɨ�����", Toast.LENGTH_LONG)
												.show();
										Intent in = new Intent();
										in.setClass(FileBrowser.this,
												ScanResult.class);
										startActivity(in);
									}
									super.onPostExecute(result);
								}
							}.execute(0);
						}
					}).setNegativeButton("ȡ��", new OnClickListener() {

						public void onClick(DialogInterface dialog, int which) {

						}
					}).create().show();
			break;
		case 3:
			ScrollView sv = (ScrollView) getLayoutInflater().inflate(
					R.layout.textview, null);
			new AlertDialog.Builder(this).setTitle("��ӭʹ��AntiVirus").setView(sv)
					.setPositiveButton("�ر�", new OnClickListener() {

						public void onClick(DialogInterface dialog, int which) {

						}
					}).create().show();
			break;
		case 4:
			new AsyncTask<Integer, Integer, String[]>() {

				private ProgressDialog dialog;
				Bundle bundle = new Bundle();

				// ǰ̨��ʾ
				protected void onPreExecute() {
					dialog = ProgressDialog.show(FileBrowser.this, "",
							"���ڲ�ɱ,���Ժ�....");
					super.onPreExecute();
				}

				// ��ִ̨��
				protected String[] doInBackground(Integer... params) {
					OffScan scanner = new OffScan(executionController);
					
					for(int i = 0; i< 20; i++){
						long startTime = System.nanoTime();
						boolean scanResult = scanner.scan(SCAN_FILE_PATH + "virusTarget" + i + ".apk");

						ArrayList<String> virusName = new ArrayList<String>();
						ArrayList<String> virusPath = new ArrayList<String>();
						long dura = System.nanoTime() - startTime;

						bundle.putStringArrayList("virusName", virusName);
						bundle.putStringArrayList("virusPath", virusPath);
						bundle.putLong("execTime", dura / 1000000);
						bundle.putBoolean("result", scanResult);
						try {
							Thread.currentThread().sleep(2500);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
					}
					
					return null;
				}

				// ִ�����
				protected void onPostExecute(String[] result) {
					dialog.dismiss();
					long dura = bundle.getLong("execTime");
					boolean scanResult = bundle.getBoolean("result");
					Toast.makeText(
							FileBrowser.this,
							"Scan Result: " + scanResult + ", Cost Time: "
									+ dura + " ms.", Toast.LENGTH_LONG).show();
					Intent intent = new Intent();
					intent.setClass(FileBrowser.this, ScannResultActivity.class);
					intent.putExtras(bundle);
					startActivity(intent);
					super.onPostExecute(result);
				}
			}.execute();

			break;
		case 5:
			Intent intent2 = new Intent();
			intent2.setClass(FileBrowser.this, AppListActivity.class);
			startActivity(intent2);
			break;
		case 6:
			new AsyncTask<Integer, Integer, String[]>() {

				private ProgressDialog dialog;

				// ǰ̨��ʾ
				protected void onPreExecute() {
					dialog = ProgressDialog.show(FileBrowser.this, "",
							"������������,���Ժ�....");
					super.onPreExecute();
				}

				// ��ִ̨��
				protected String[] doInBackground(Integer... params) {
					try {
						Thread.sleep(4000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					return null;
				}

				// ִ�����
				protected void onPostExecute(String[] result) {
					dialog.dismiss();
					Toast toast = Toast.makeText(FileBrowser.this,
							"��ǰ�����������µģ��������", Toast.LENGTH_LONG);
					toast.setGravity(Gravity.CENTER, 0, 0);
					toast.show();
					super.onPostExecute(result);
				}
			}.execute();

			break;
		case 7:
			String sql = "select name,signature from virus";
			if (!database.isOpen()) {
				database = openDatabase();
			}
			Cursor cursor = database.rawQuery(sql, null);
			List<HashMap<String, String>> name_signature_list = new ArrayList<HashMap<String, String>>();
			HashMap<String, String> map;
			while (cursor.moveToNext()) {
				String name = cursor.getString(cursor.getColumnIndex("name"));
				String signature = cursor.getString(cursor
						.getColumnIndex("signature"));
				map = new HashMap<String, String>();
				map.put(name, signature);
				name_signature_list.add(map);
			}
			if (cursor != null) {
				cursor.close();
			}

			if (database.isOpen()) {
				database.close();
			}
			List<ApplicationInfo> infos = new ArrayList<ApplicationInfo>();
			badApp_infos = new ArrayList<ApplicationInfo>();
			infos = pm.getInstalledApplications(0);
			for (ApplicationInfo info : infos) {
				for (HashMap<String, String> name_signature : name_signature_list) {
					if (name_signature.containsKey(info.packageName)) {
						if (name_signature
								.get(info.packageName)
								.trim()
								.equals(getAppSignature(info.packageName)
										.trim())) {
							Log.e("test", info.packageName + "��һ������");
							badApp_infos.add(info);
						}
					}
				}
			}
			Intent i = new Intent(getApplicationContext(), BadAppActivity.class);
			startActivity(i);
			break;

		case 100:
			Intent mIntent = new Intent();
			mIntent.setClass(this, Preferences.class);
			startActivity(mIntent);
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	/*
	 * ��ȡ�����ǩ��
	 */
	public String getAppSignature(String packname) {
		try {
			PackageInfo packinfo = pm.getPackageInfo(packname,
					PackageManager.GET_SIGNATURES);
			// ��ȡ�����е�Ȩ��
			return packinfo.signatures[0].toCharsString();
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static List<File> resultList;// �ļ��б�
	EditText editText;

	public void GetFiles(File filePath) {

		File[] files = filePath.listFiles();

		if (files != null) {
			int len = files.length;
			for (int i = 0; i < len; i++) {
				if (files[i].isDirectory()) {
					GetFiles(files[i]);
				} else {
					if (files[i].getName().contains(editText.getText())) {
						resultList.add(files[i]);
					}
				}
			}
		}
	}

	public HashMap<String, String> getFileSignature(File[] files)
			throws IOException {

		for (int i = 0; i < files.length; i++) {
			File currentFile = files[i];
			Log.d("FileBrowser", currentFile.getAbsolutePath());
			if (currentFile.isDirectory() && currentFile.listFiles() != null) {
				getFileSignature(currentFile.listFiles());
			} else {
				String fileName = currentFile.getName();
				if (fileName.endsWith(".apk")) {
					FileMD5 md5 = new FileMD5(currentFile.getPath());
					if (currentFile.getPath().contains("zip")) {
						tempPath.add(currentFile.getPath());
						String key = currentFile.getPath().substring(0,
								currentFile.getPath().indexOf("/zip"))
								+ ".zip";
						fileSignature.put(key, md5.getMd5());
					} else {
						fileSignature.put(currentFile.getPath(), md5.getMd5());
					}
				} else if (fileName.endsWith(".zip")) {
					FileHelper.unZip(currentFile.getPath());
				}
			}
		}
		return fileSignature;
	}

	private SQLiteDatabase openDatabase() {
		try {
			// ���dictionary.db�ļ��ľ���·��
			String databaseFilename = DATABASE_PATH + "/" + DATABASE_FILENAME;
			File dir = new File(DATABASE_PATH);
			// ���/sdcard/dictionaryĿ¼�д��ڣ��������Ŀ¼
			if (!dir.exists())
				dir.mkdir();
			// �����/sdcard/dictionaryĿ¼�в�����
			// dictionary.db�ļ������res\rawĿ¼�и�������ļ���
			// SD����Ŀ¼��/sdcard/dictionary��
			if (!(new File(databaseFilename)).exists()) {
				// ��÷�װdictionary.db�ļ���InputStream����
				InputStream is = getResources().openRawResource(
						R.raw.mobi_virus);
				FileOutputStream fos = new FileOutputStream(databaseFilename);
				byte[] buffer = new byte[8192];
				int count = 0;
				// ��ʼ����dictionary.db�ļ�
				while ((count = is.read(buffer)) > 0) {
					fos.write(buffer, 0, count);
				}
				fos.close();
				is.close();
			}
			// ��/sdcard/dictionaryĿ¼�е�dictionary.db�ļ�
			SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(
					databaseFilename, null);
			return database;
		} catch (Exception e) {
		}
		return null;
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) {
		// TODO Auto-generated method stub
		readPrefs();
	}

	private void readPrefs() {
		boolean alwaysLocal = settings.getBoolean("alwaysLocal", false);
		Log.d(TAG, "alwaysLocal is " + alwaysLocal);
		if (alwaysLocal) {
			this.executionController
					.setUserChoice(ControlMessages.STATIC_LOCAL);
		} else {
			this.executionController
					.setUserChoice(ControlMessages.USER_CARES_ONLY_ENERGY);
		}
	}

}