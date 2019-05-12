package gerardo.com.urv.practica21;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import cat.tomasgis.app.providers.parkingprovider.contracts.ModelContracts;

public class FloorListAdapter extends BaseAdapter {

    private Context mContext;
    private static final String TAG = FloorListAdapter.class.getSimpleName();
    Cursor mData;

    public FloorListAdapter (Context context, Cursor data){
        if (context == null) Log.e(TAG, "null");
            mContext = context;
            mData = data;
    }

    @Override
    public int getCount() {
        if(mData == null) return 0;

        return mData.getCount();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if(view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.adapter_floor_list, null);
        }

        mData.moveToPosition(position);

        String name = mData.getString(mData.getColumnIndex(ModelContracts.FloorContract.NAME));

        ((TextView) view.findViewById(R.id.floorName)).setText(name);


        Cursor cursor = mContext.getContentResolver().query(ModelContracts.SlotModel.buildContentUri(),
                ModelContracts.SlotModel.DEFAULT_PROJECTIONS,"floor_id=?", new String[]{"1"},
                ModelContracts.SlotModel.DEFAULT_SORT);
        ((TextView) view.findViewById(R.id.numberSlotsFloor)).setText(String.valueOf(cursor.getCount()));

        cursor.close();
        return view;
    }
}
