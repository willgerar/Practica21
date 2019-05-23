package gerardo.com.urv.practica21;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import cat.tomasgis.app.providers.parkingprovider.contracts.ModelContracts;

public class FloorListActivity extends AppCompatActivity {
    private static  final String TAG = FloorListActivity.class.getSimpleName();

    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floor_list);

        ((ListView)this.findViewById(R.id.listFloorP)).setAdapter(null);

        mContext = this.getApplicationContext();

        String idParking = getIntent().getExtras().getString(ModelContracts.FloorModel.PARKING_ID);

        String selection = ModelContracts.FloorModel.PARKING_ID+"=?";
        String [] selectionArgs = {idParking};

        Cursor cursor = this.getContentResolver().query(ModelContracts.FloorModel.buildContentUri(),
                ModelContracts.FloorModel.DEFAULT_PROJECTIONS,
                selection, selectionArgs, ModelContracts.FloorModel.DEFAULT_SORT);

        FloorListAdapter mAdapter = new FloorListAdapter(mContext,cursor);
        ListView listFloors = findViewById(R.id.listFloorP);
        listFloors.setAdapter(mAdapter);
        listFloors.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String idFloor = (String) view.getTag();//con esta línea elegir el id del parking
                Intent mIntent = new Intent(FloorListActivity.this, DetailFloorActivity.class);
                mIntent.putExtra(ModelContracts.SlotModel.FLOOR_ID, idFloor);//pasa a la activity el id del parking
                FloorListActivity.this.startActivity(mIntent);//FloorListActivity.this. <- se puede llamar al intent poniendo esto delante
            }
        });

    }
}


        /*Bundle params = getIntent().getExtras();
        int idParking= Integer.parseInt(params.getString(ModelContracts.ParkingModel.ID));*/


