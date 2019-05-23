package gerardo.com.urv.practica21;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import cat.tomasgis.app.providers.parkingprovider.contracts.ModelContracts;

public class DetailFloorActivity extends AppCompatActivity {
    private static final String TAG = gerardo.com.urv.practica21.DetailFloorActivity.class.getSimpleName();
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_floor);

        mContext = this.getApplicationContext();

        String idFloor = getIntent().getExtras().getString(ModelContracts.SlotModel.FLOOR_ID);//rewiev lo que me estan pasando

        String selection = ModelContracts.SlotModel.FLOOR_ID + "=?";
        String []selectionArgs = {idFloor};

        Cursor cursor = this.getContentResolver().query(ModelContracts.SlotModel.buildContentUri(),
                ModelContracts.SlotModel.DEFAULT_PROJECTIONS, selection, selectionArgs,
                ModelContracts.SlotModel.DEFAULT_SORT);

        SlotListAdapter mAdapter = new SlotListAdapter (mContext, cursor);
        ((ListView)this.findViewById(R.id.listDetail)).setAdapter((mAdapter));

    }
}
