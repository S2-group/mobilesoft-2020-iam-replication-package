/*
    AFortune: show a fortune cookie

    Copyright (C) 2011  Goffredo Baroncelli <kreijack@gmail.com>

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package eu.kreijack.afortune;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.util.Log;

public class AFortuneDB {

	private static final String 	TAG = "eu.kreijack.afortune.AFortuneDB";
	private static final String	 	enabled_fortunes = "enabled_fortune";
	//private String[]				dbs_name;
	private Random					generator;
	private Context					context;
	private AssetManager 			assetManager;
	private SharedPreferences		settings;	
	private static AFortuneDB		aFortuneDB = null;
	private String					lastFortune="";
	
	class FortuneFile {
		public String	packageName;
		public String	fileName;
		public String	name;
	};
	
	private ArrayList<FortuneFile>	fortuneFiles;
	
	public static AFortuneDB getAFortuneDB(Context ctx){
		if(aFortuneDB == null)
			aFortuneDB = new AFortuneDB(ctx);
		return aFortuneDB;
	}
	
	private AFortuneDB(Context ctx){
		generator = new Random(System.currentTimeMillis());
		context = ctx;
	    settings = ctx.getSharedPreferences(Const.SHARED_PREFERENCES, 0);		
		assetManager = context.getResources().getAssets();
		fortuneFiles = new ArrayList<FortuneFile>();
		refresh();
	}
	
	public static String join(String x, String array[]){
		String r="";
		for(int i=0; i<array.length ; i++){
			if(i>0) r+=x;
			r += array[i];
		}
		return r;
	}
	
	public static String join(String x,Set<String> s){
    	Iterator<String> it = s.iterator();
		String res = "";
		while(it.hasNext()){
			if( res.length()>0) res += x;
			res += it.next();
		}
		return res;		
	}
	
	public static Set<String> array2set(String[] array){
		Set<String> s= new HashSet<String>();
		for(int i= 0 ; i < array.length ; i++)
			s.add(array[i]);
		return s;
	}
	
	void updateFortuneFiles(AssetManager am, String pn, String prefix){
		try {
			String dbs_name[] = am.list("");
			for(int i=0 ; i < dbs_name.length ; i++){
				
				if( !dbs_name[i].endsWith(".for")) continue;
				
				FortuneFile ff = new FortuneFile();
				ff.packageName = pn;
				ff.fileName = dbs_name[i];
				ff.name = prefix + dbs_name[i].substring(0, dbs_name[i].length()-4);
				
				fortuneFiles.add(ff);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.d(TAG,"Cannot access to AssetManager of package "+pn);
			//dbs_name = empty;
		}
		
	}
	
	void refresh(){
		
		fortuneFiles.clear();
		
		updateFortuneFiles(assetManager, "", "");
		
		PackageManager pm = context.getPackageManager();
		String prefix = context.getPackageName();
		
		List<PackageInfo> lpi = pm.getInstalledPackages(0);
		Iterator<PackageInfo> lpii = lpi.iterator();
		while(lpii.hasNext()){
			
			PackageInfo pi = lpii.next();
			if( !pi.packageName.startsWith(prefix+"_")) 
				continue;
				
			String prefix2 = "["+pi.packageName.substring(prefix.length()+1)+"] ";

			try {
				
				Context ctx = context.createPackageContext(pi.packageName,0);
				AssetManager am = ctx.getResources().getAssets();
				
				updateFortuneFiles(am, pi.packageName, prefix2);
			
			} catch (NameNotFoundException e) {
				Log.d(TAG, "NameNotFoundException: "+pi.packageName);
				//e.printStackTrace();
			}
			
		}
		
		String enabledFortunes = settings.getString(enabled_fortunes, "*");	
		if(enabledFortunes == "*" ){
			String[] names = new String[fortuneFiles.size()];
			for(int i=0;i<fortuneFiles.size();i++)
				names[i]=fortuneFiles.get(i).name;
	        setEnabledDBs(names);
		} else {
			setEnabledDBs(enabledFortunes.split("/"));
		}
	}
	
	public String[] getDBs(){
		String[] names = new String[fortuneFiles.size()];
		for(int i=0;i<fortuneFiles.size();i++)
			names[i]=fortuneFiles.get(i).name;	
		return names;
	}
	
	public String[] getEnabledDBs(){
		String enabledFortunes = settings.getString(enabled_fortunes, "*");
		return enabledFortunes.split("/");
	}
	
	public void setEnabledDBs(Set<String> s2){
		Set<String> s= array2set(getDBs());
		s2.retainAll(s);
		String enabled = join("/", s2);
		SharedPreferences.Editor editor = settings.edit();
    	editor.putString(enabled_fortunes, enabled); 
        editor.commit(); 
        
        Log.d(TAG,"Enabled DB="+enabled);
	}

	public void enabledDBs(boolean enabled, String fortune){
		Set<String> s= array2set(getEnabledDBs());
    	if( !enabled )
    		s.remove(fortune);
    	else
    		s.add(fortune);
    	setEnabledDBs(s);
	}
	
	public void setEnabledDBs(String ss[]){
		setEnabledDBs(array2set(ss));
	}
	

	public int[] getWeights(){
		int r[] = {0};
		return r;
	}
	public void setWeights(int [] v){
		// do nothing
	}
	private int random(){
		return Math.abs(generator.nextInt());
	}
	
	private String convertStreamToString(InputStream is) {
	/*
	 * To convert the InputStream to String we use the
	 * Reader.read(char[] buffer) method. We iterate until the
	 * Reader return -1 which means there's no more data to
	 * read. We use the StringWriter class to produce the string.
	 */
		if (is != null) {
			Writer writer = new StringWriter();
			
			char[] buffer = new char[1024];
			try {
				Reader reader = new BufferedReader(
						new InputStreamReader(is, "UTF-8"));
				int n;
				while ((n = reader.read(buffer)) != -1) {
					writer.write(buffer, 0, n);
				}
			} catch( IOException e){
				return "<IOException>";
			}
			return writer.toString();
		} else {       
			return "";
		}
	}
	
	private String getRandomText(String idb){
		InputStream ins = null;
		
		FortuneFile ff = null;
		
		for(int i=0 ; i < fortuneFiles.size(); i++){
			//Log.d(TAG,"fortunefiles:"+fortuneFiles.get(i).name+"; name: "+idb+";");
			if(fortuneFiles.get(i).name.equals(idb)){
				ff = fortuneFiles.get(i);
				break;
			}
		}
		
		if( ff == null){
			return "Internal error: fortune not found; idb="+idb;
		}
		
		Log.d(TAG,"name = "+ff.name);
		
		try {
			if(ff.packageName == ""){
				ins = assetManager.open(ff.fileName);
			}else{
				Context ctx = context.createPackageContext(ff.packageName,0);
				AssetManager am = ctx.getResources().getAssets();
				ins = am.open(ff.fileName);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String fortunes =  convertStreamToString(ins);
		
		if( fortunes.length() < 1000)
			return "FORTUNES TOO SHORT !";
		
		int i = random() % (fortunes.length() - 1);
		
		int j = fortunes.lastIndexOf("%\n", i);
		int k = fortunes.indexOf("%\n", j+1);
		
		//if(j<0) j=0;
		if(k<0) k= fortunes.length();
		
		Log.d(TAG, "len(fortunes)="+String.valueOf(fortunes.length())+
				"; i="+String.valueOf(i)+
				"; j="+String.valueOf(j)+
				"; k="+String.valueOf(k)
				);
		if( (k<0) || (k<j))
			return "<NO FORTUNE>";
		
		return fortunes.substring(j+2, k);
				
	}
	
	public String getRandomFortune(){
		refresh();
		
		String dbsname[] = getEnabledDBs();
		
		Log.d(TAG,"Select from db:"+join("/",dbsname));
		int idb = random() % dbsname.length;
		lastFortune = getRandomText(dbsname[idb]); 
		return lastFortune;
	}
	
	public String getLastFortune(){
		return lastFortune;
	}

}
