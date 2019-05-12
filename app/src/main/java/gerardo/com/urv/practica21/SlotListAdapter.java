package gerardo.com.urv.practica21;

import android.accessibilityservice.GestureDescription;
import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import cat.tomasgis.app.providers.parkingprovider.contracts.ModelContracts;

public class SlotListAdapter extends BaseAdapter{

    private Context mContext;
    private static final String TAG = SlotListAdapter.class.getSimpleName();
    Cursor mData;

    public SlotListAdapter(Context context, Cursor data) {
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
            view = LayoutInflater.from(mContext).inflate(R.layout.adapter_slot_list, null);
        }
        mData.moveToPosition(position);

        String name = mData.getString(mData.getColumnIndex(ModelContracts.SlotContract.NAME));
        ((TextView) view.findViewById(R.id.slotName)).setText(name);

        return view;
    }
}
