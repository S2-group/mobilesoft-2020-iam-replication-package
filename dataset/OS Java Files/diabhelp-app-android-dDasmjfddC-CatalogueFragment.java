package fr.diabhelp.diabhelp.Core;

/**
 * Created by naqued on 28/09/15.
 */
import android.app.ProgressDialog;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import fr.diabhelp.diabhelp.API.Asynctasks.CatalogueAPICallTask;
import fr.diabhelp.diabhelp.API.IApiCallTask;
import fr.diabhelp.diabhelp.API.ResponseModels.ResponseCatalogue;
import fr.diabhelp.diabhelp.Connexion_inscription.ConnexionActivity;
import fr.diabhelp.diabhelp.Utils.NetworkUtils;
import fr.diabhelp.diabhelp.Models.CatalogModule;
import fr.diabhelp.diabhelp.R;
import fr.diabhelp.diabhelp.Utils.MyToast;

public class CatalogueFragment extends Fragment implements IApiCallTask<ResponseCatalogue> {
    private RecyclerView                _recyclerView;
    private RecyclerView.Adapter        _recAdapter;
    private RecyclerView.LayoutManager  _recLayoutManager;
    private List<CatalogModule>         _modulesList = new ArrayList<>();
    private ProgressDialog              _progress;
    private static Integer launch = 0;


    //default constructor
    public CatalogueFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {

        return ;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.catalogue_fragment, container, false);
        _recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        _recyclerView.setHasFixedSize(false);
        _recLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        _recyclerView.setLayoutManager(_recLayoutManager);
        return v;
    }

    private void checkAvailability() {
        List<PackageInfo> packs = getActivity().getPackageManager().getInstalledPackages(0);
        for(int i = 0 ; i < packs.size() ; i++) {
            PackageInfo p = packs.get(i);
            if ((p.packageName.contains("diabhelp") && !p.packageName.contains("diabhelp.diabhelp"))) {
                for (int j = 0 ; j < _modulesList.size() ; j++) {
                    if (_modulesList.get(j).getName().equals(p.applicationInfo.loadLabel(getActivity().getPackageManager()).toString()));
                        //_modulesList.remove(j);
                }
            }
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && launch == 0) {
            if (NetworkUtils.getConnectivityStatus(getActivity()))
            {
                new CatalogueAPICallTask(getActivity(), this).execute();
                launch = 1;
                //TODO mettre en place le chargement en fonction du type d'utilisteur
                //new CatalogueAPICallTask(getActivity(), this).execute(ConnexionActivity._settings.getString(ConnexionActivity.TYPE_USER, ""));//
            }
        }
    }

    @Override
    public void onBackgroundTaskCompleted(String s, int type, String action) throws JSONException {}

    @Override
    public void onBackgroundTaskCompleted(ResponseCatalogue response, String action, ProgressDialog progress) {
        _progress = progress;
        CatalogueFragment.Error error = response.getError();
        Integer errorCode = error.getErrorCode();
        if (errorCode != null && errorCode != 0) {
            manageError(response.getError());
        }
        else if (action.equals("getModules")) {
            displayModules(response.getModules());
        }
    }

    private void displayModules(List<CatalogModule> modules) {
        System.out.println("nombre de modules = " + modules.size());
        _progress.dismiss();
        _modulesList = modules;
        //checkAvailability();
        _recAdapter = new CatalogRecyclerAdapter(_modulesList);
        _recyclerView.setAdapter(_recAdapter);
    }

    private void manageError(CatalogueFragment.Error error) {
        _progress.dismiss();
        switch (error) {
            case SERVER_ERROR:{
                MyToast.getInstance().displayWarningMessage(getString(R.string.error_server), Toast.LENGTH_LONG, getActivity());
                Log.e(getClass().getSimpleName(), "Problème survenu lors de la connexion au serveur");
                break;
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        launch = 0;
    }

    public enum  Error
    {
        NONE(0),
        SERVER_ERROR(1);

        private Integer errorCode;

        Error(Integer i) {
            this.errorCode = i;
        }

        public Integer getErrorCode() {
            return this.errorCode;
        }
    }
}
