package gerardo.com.urv.practica21;

import android.database.Cursor;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import cat.tomasgis.app.providers.parkingprovider.contracts.ModelContracts;

public class MapParkingActivity extends FragmentActivity implements OnMapReadyCallback {

    private ListView mListView;// declarar mi lista

    private List<String> mList = new ArrayList<>();//declarar e instanciar array de strings
    private ArrayAdapter<String> mAdapter; // declarar un array de listas


    private GoogleMap mMap;

    SimpleCursorAdapter cursorAdapter;

    String fields[] = {ModelContracts.LocationModel.NAME, ModelContracts.LocationModel.LATITUDE, ModelContracts.LocationModel.LONGITUDE};
    //int xmlFields[] = {android.R.id.mSlot_State,R.id.mSlot_Color, R.id.mSlot_Type};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_parking);



        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);// inserta el cuadro de zoom con el + y el -
        uiSettings.setCompassEnabled(true);// muestra el cuadro de acceso a google mapas y el cuadro de accceso a la ubicación
        uiSettings.setMapToolbarEnabled(true);// oculta el cuadro de la barra de maps osea donde se muestra la línea anterior
        uiSettings.setTiltGesturesEnabled(true);// matiene activo el titulo sobre el globo de ubicación

        double latitude;
        double longitude;
        String location;
        String address;

        String ids [] = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};

        for(int i = 0; i<9; i++){
            String selection = ModelContracts.LocationModel.ID+ "=?";
            String [] selectionArgs = {ids[i]};

            //Realizo la consulta de todas las latitude y longitudes de ubicación de los parkings
            Cursor cursor = this.getContentResolver().query(ModelContracts.LocationModel.buildContentUri(),
                    ModelContracts.LocationModel.DEFAULT_PROJECTIONS, selection, selectionArgs,
                    ModelContracts.LocationModel.DEFAULT_SORT);

            if(cursor.getCount() > 0){
                cursor.moveToFirst();

                latitude = cursor.getDouble(cursor.getColumnIndex(ModelContracts.LocationContract.LATITUDE));
                longitude = cursor.getDouble(cursor.getColumnIndex(ModelContracts.LocationContract.LONGITUDE));
                location = cursor.getString(cursor.getColumnIndex(ModelContracts.LocationContract.NAME));
                address = cursor.getString(cursor.getColumnIndex(ModelContracts.LocationContract.STREET_ADDRESS));

                // Add a marker in Sydney and move the camera
                LatLng locationParkings = new LatLng(latitude, longitude);
                mMap.addMarker(new MarkerOptions().position(locationParkings).title(location +" , "+ address).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(locationParkings, 11f));

            }
        }
    }
}
