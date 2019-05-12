package gerardo.com.urv.practica21;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import cat.tomasgis.app.providers.parkingprovider.contracts.ModelContracts;
import cat.tomasgis.module.communication.CommManager;
import cat.tomasgis.module.communication.base.AppURL;
import cat.tomasgis.module.communication.listeners.IDataReceiver;
import cat.tomasgis.module.communication.listeners.StringResponseListener;
import gerardo.com.urv.practica21.Models.Floor;
import gerardo.com.urv.practica21.Models.Parking;
import gerardo.com.urv.practica21.Models.Slot;

public class ParkingListActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, IDataReceiver {

    private static final String TAG = gerardo.com.urv.practica21.ParkingListActivity.class.getSimpleName();
    StringResponseListener responseListener = new StringResponseListener(this);

    private ListView mListView;// declarar mi lista
    private List<String> mList = new ArrayList<>();//declarar e instanciar array de strings
    private ArrayAdapter<String> mAdapter; // declarar un array de listas

    private Parking[] parking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mListView = findViewById(R.id.listparkings);

        CommManager.initializeQueu(this);;
        if(!CommManager.callRequest(AppURL.PARKING_URL, responseListener)){
            Toast.makeText(this, "error conexión ", Toast.LENGTH_SHORT).show();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_search_parking_places) {
            Intent intent = new Intent(ParkingListActivity.this, SlotSearchActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_access_parking) {
            Intent intent = new Intent(ParkingListActivity.this, FloorListActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_map_parking) {
            Intent intent = new Intent(ParkingListActivity.this, MapParkingActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_validate_entry) {
            Intent intent = new Intent(ParkingListActivity.this, ValidateActivity.class);
            startActivity(intent);
        }

        /*else if (id == R.id.nav_motorcycle) {
            Intent intent = new Intent(ParkingListActivity.this, MotorcycleActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_bicycle) {
            Intent intent = new Intent(ParkingListActivity.this, BicycleActivity.class);
            startActivity(intent);
        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * Método recibe datos del servidor y muestra los parkings que están añadidos
     * @param data datos del servidor
     */
    @Override
    public void onReceiveData(String data) {
        /*Bundle params = getIntent().getExtras();
        int*/
        if(data != null){
            if(data.length() > 0){
                Toast.makeText(this, "Recibiendo...", Toast.LENGTH_SHORT).show();

                Gson gson = new Gson();
                parking = gson.fromJson(data, Parking[].class);//objeto que nos dscargamos aquí es el parkings
                for(int i=0; i<parking.length; i++){
                    String name = parking[i].getName();
                    mList.add(name);
                    mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mList);
                    mListView.setAdapter(mAdapter);
                    clearAllData();
                    createBaseData();
                }
            }

        }
    }

    protected void clearAllData(){
        int numberElementsDeleted;
        ContentResolver contentResolver = this.getContentResolver();

        //Slots
        numberElementsDeleted = getContentResolver().delete(ModelContracts.SlotModel.buildContentUri(), null, null);
        Log.d(TAG, String.format("erasing slots %d", numberElementsDeleted));

        //Floors
        numberElementsDeleted = getContentResolver().delete(ModelContracts.FloorModel.buildContentUri(), null , null);
        Log.d(TAG, String.format("erasing floors %d", numberElementsDeleted ));

        //Location
        numberElementsDeleted = getContentResolver().delete(ModelContracts.LocationModel.buildContentUri(), null, null);
        Log.d(TAG, String.format("erasing locations %d", numberElementsDeleted));

        //Parking
        numberElementsDeleted = getContentResolver().delete(ModelContracts.ParkingModel.buildContentUri(), null, null);
        Log.d(TAG, String.format("erasing parking %d", numberElementsDeleted));
    }

    protected  void createBaseData() {
        int i = 0;
        ContentResolver contentResolver = this.getContentResolver();
        ContentValues contentValuesParking;
        ContentValues contentValuesFloor;
        ContentValues contentValuesSlot;
        ContentValues contentValuesLocation;

        while(i<3){
            contentValuesParking = ContentValuesUtils.modelToContentValuesP(parking[i]);
            Uri insertUri;
            for(Floor floor: parking[i].getFloors()){
                contentValuesFloor = ContentValuesUtils.modelToContentValuesF(floor);
                //insertFloor
                insertUri = contentResolver.insert(ModelContracts.FloorModel.buildContentUri(), contentValuesFloor);
                Log.d(TAG, String.format("insert floor %s", insertUri.toString()));

                for(Slot slot: floor.getSlots()){
                    contentValuesSlot = ContentValuesUtils.modelToContentValuesS(slot, floor.getCompany_number());
                    //insert Slots
                    insertUri = contentResolver.insert(ModelContracts.SlotModel.buildContentUri(), contentValuesSlot);
                    Log.d(TAG, String.format("insert slot %s", insertUri.toString()));
                }

                contentValuesLocation = ContentValuesUtils.modelToContentValuesL(parking[i].getLocation(), parking[i].getName());
                //insert location
                insertUri = contentResolver.insert(ModelContracts.LocationModel.buildContentUri(), contentValuesLocation);
                Log.d(TAG, String.format("insert location %s", insertUri.toString()));
            }

            //insert parking
            insertUri = contentResolver.insert(ModelContracts.ParkingModel.buildContentUri(), contentValuesParking);
            Log.d(TAG, String.format("insert parking %s", insertUri.toString()));
            i++;
        }
    }


}
