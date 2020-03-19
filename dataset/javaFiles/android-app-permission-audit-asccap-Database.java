/*
 * Projet 	: Permission Explorer
 * Auteur 	: Carlo Criniti
 * Date   	: 2011.06.10
 * 
 * Classe Database
 * Permet de gérer la base de données SQLite
 */

package com.carlocriniti.android.permission_explorer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

public class Database {
	// Contexte de l'application
	private Context context;
	
	// Barre de progression pour les opérations longues
	private ProgressDialog progressDialog;
	
	// Base de donnnées
	public SQLiteDatabase database;
	
	/*
	 * Database
	 * Récupération du contexte du programme et création
	 * ou ouverture de la base de données.
	 */
	public Database (Context context)
	{
		this.context = context;
		database = new DatabaseOpenHelper(this.context).getWritableDatabase();
	}
	
	/*
	 * isUpToDate
	 * Vérifie si les données de la base sont à jour
	 */
	public void isUpToDate()
	{
		// Configuration d'une barre de progression
		progressDialog = new ProgressDialog(context);
		progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		progressDialog.setMessage(context.getString(R.string.dialog_uptodate_text));
		progressDialog.setCancelable(false);
		
		// Récupération du nombre d'applications
		PackageManager pm = context.getPackageManager();
		List<PackageInfo> packages = pm.getInstalledPackages(PackageManager.GET_META_DATA);
		progressDialog.setMax(packages.size());
		
		// Affichage de la barre de progression
    	progressDialog.show();
    	
    	// Exécution de la tâche parallèle
    	new IsUpToDateTask().execute();
	}
	
	/*
	 * Classe IsUpToDateTask
	 * Fournit une tâche dans un thread séparé pour un
	 * travail long.
	 */
	private class IsUpToDateTask extends AsyncTask<Void, Void, Boolean> {
		/*
		 * doInBackground
		 * Méthode de travail dans le thread séparé
		 */
		protected Boolean doInBackground(Void... params) {
			// Récupération d'un fournisseur de liste d'applications
			PackageManager pm = context.getPackageManager();
			List<PackageInfo> packages = pm.getInstalledPackages(PackageManager.GET_META_DATA);
			
			String packageName;			// Nom du package
	    	String packageVersionCode;	// Code de version du package
	    	
	    	boolean isUpToDate = true;	// La base est, par défaut, à jour
	    	Cursor packageChange;		// Accès à la base de données
	    	
	    	// Selection du nombre d'applications dans la base de donnees
	    	packageChange = database.rawQuery("SELECT Count(*) FROM application;", null);
	    	packageChange.moveToFirst();
	    	
	    	// Si ce n'est pas nul ou ou si ce ne correspond pas au nombre du système
	    	if (packageChange.getInt(0) != packages.size() || packageChange.getInt(0) == 0) {
	    		// On ferme l'accès et la base n'est pas à jour
	    		packageChange.close();
	    		return false;
	    	}
	    	
	    	// On parcourt chaque application
	    	for (PackageInfo pi : packages) {
	    		// On incrémente la progressbar
	    		progressDialog.incrementProgressBy(1);
	    		
	    		// Récupère les informations sur l'application installée
	    		packageName = pi.packageName;
	    		packageVersionCode = Integer.toString(pi.versionCode);
	    		
	    		// Récupère le nombre d'applications dans la base de données correspondant à ces informations
	    		packageChange = database.query("application", new String[]{"id"}, "name = ? AND version_code = ?", new String[]{packageName, packageVersionCode}, null, null, null);
	    		if (packageChange.getCount() == 0) {
	    			// Aucune application dans la base de données ==> pas à jour
	    			isUpToDate = false;
	    			packageChange.close();
	    			break;
	    		}
	    		packageChange.close();
	    	}
	    	
	    	// Envoi du résultat
	    	return isUpToDate;	    				
		}
		
		/*
		 * onPostExecute
		 * Réception du résultat dans le thread principal 
		 */
		protected void onPostExecute(Boolean result) {
			// Fermeture de la barre de progression et appel de la fonction de fin
			progressDialog.dismiss();
			((Main)context).isUpToDateResult(result);
	     }
	}
	
	/*
	 * updateDatabase
	 * Méthode de mise à jour de la base de données
	 */
	public void updateDatabase(Activity activity)
	{
		// Configuration d'une barre de progression
		progressDialog = new ProgressDialog(context);
		progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		progressDialog.setMessage(context.getString(R.string.dialog_update_text));
		progressDialog.setCancelable(false);
		
		// Récupération du nombre d'applications sur le système
		PackageManager pm = context.getPackageManager();
		List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);
		progressDialog.setMax(packages.size());
		
		// Affichage de la barre de progression
    	progressDialog.show();
		
    	// Exécution de la tâche dans un thread parallèle
		new DatabaseUpdateTask(activity, database, progressDialog).execute();
	}
}