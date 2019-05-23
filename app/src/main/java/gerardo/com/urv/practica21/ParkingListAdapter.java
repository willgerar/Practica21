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

public class ParkingListAdapter extends BaseAdapter {

    private final static String TAG = ParkingListAdapter.class.getSimpleName();
    Cursor mData;
    Context mContext;

    public ParkingListAdapter(Context context, Cursor data) {
        if(context == null) Log.e(TAG, "null");
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
        if(view == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.adapter_parking_list, null);
        }
        mData.moveToPosition(position);

        view.setTag(mData.getString(mData.getColumnIndexOrThrow(ModelContracts.ParkingModel.ID)));//seleccionar the floor that selection

        String name = mData.getString(mData.getColumnIndex(ModelContracts.ParkingModel.NAME));

        ((TextView) view.findViewById(R.id.mNamesParkings)).setText(name);

        return view;
    }
}
