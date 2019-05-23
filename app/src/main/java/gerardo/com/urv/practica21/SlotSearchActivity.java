package gerardo.com.urv.practica21;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.List;

import cat.tomasgis.app.providers.parkingprovider.contracts.ModelContracts;

public class SlotSearchActivity extends AppCompatActivity{
    private RadioButton normal, electrico, discapacitados,bicicletas, motos, all;
    private ListView mListView;
    private List<String> mList = new ArrayList<>();
    private ArrayAdapter<String> mArrayAdapter;

    Context mContext;
    Cursor cursor;
    SimpleCursorAdapter cursorAdapter;

    String fields[] = {ModelContracts.SlotModel.SLOT_STATE,ModelContracts.SlotModel.SLOT_COLOR,ModelContracts.SlotModel.SLOT_TYPE};
    int xmlFields[] = {R.id.mSlot_State,R.id.mSlot_Color, R.id.mSlot_Type};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slot_search);

        normal = findViewById(R.id.common);
        electrico = findViewById(R.id.electric);
        discapacitados = findViewById(R.id.disabled);
        bicicletas = findViewById(R.id.bike);
        motos = findViewById(R.id.motorbike);
        all = findViewById(R.id.all);

        mListView = findViewById(R.id.listSlotSearch);

        String selection = null;
        String selectionArgs[]=null;

        this.cursor = this.getContentResolver().query(ModelContracts.SlotModel.buildContentUri(),
                ModelContracts.SlotModel.DEFAULT_PROJECTIONS,selection,selectionArgs,ModelContracts.SlotModel.DEFAULT_SORT);

        String fields[] = {ModelContracts.SlotModel.SLOT_STATE,ModelContracts.SlotModel.NAME,ModelContracts.SlotModel.SLOT_TYPE};
        int xmlFields[] = {R.id.mSlot_State,R.id.mSlot_Color, R.id.mSlot_Type};

        cursorAdapter = new SimpleCursorAdapter(getApplicationContext(), R.layout.item_selection_slot_search,
                cursor, fields, xmlFields, 0);

        mListView.setAdapter(cursorAdapter);

    }
    public void onRadioButtonClicked(View view){

        if(normal.isChecked() == true){

            String selection = ModelContracts.SlotModel.buildFloorStateTypeSelection();
            String[] selectionArgs = ModelContracts.SlotModel.buildFloorStateTypeSelectionArgs("2","FREE", "COMMON");

            this.cursor = this.getContentResolver().query(ModelContracts.SlotModel.buildContentUri(),
                    ModelContracts.SlotModel.DEFAULT_PROJECTIONS,selection,selectionArgs,ModelContracts.SlotModel.DEFAULT_SORT);

            this.cursorAdapter.swapCursor(this.cursor);
            this.cursorAdapter.notifyDataSetChanged();

            //this.cursor.moveToFirst();
            /*for (int i=0;i<this.cursor.getCount();i++) {
                String state = this.cursor.getString(this.cursor.getColumnIndex(ModelContracts.SlotModel.SLOT_STATE));
                String type = this.cursor.getString(this.cursor.getColumnIndex(ModelContracts.SlotModel.SLOT_TYPE));
                String idFloor = this.cursor.getString(this.cursor.getColumnIndex(ModelContracts.SlotModel.FLOOR_ID));
                this.cursor.moveToNext();
                String a = state;
            }
            this.cursorAdapter.swapCursor(this.cursor);
            this.cursorAdapter.notifyDataSetChanged();*/

        }else if(electrico.isChecked() == true){

            String selection = ModelContracts.SlotModel.buildFloorStateTypeSelection();
            String[] selectionArgs = ModelContracts.SlotModel.buildFloorStateTypeSelectionArgs("2","FREE", "ELECTRIC");

            cursor = this.getContentResolver().query(ModelContracts.SlotModel.buildContentUri(),
                    ModelContracts.SlotModel.DEFAULT_PROJECTIONS,selection,selectionArgs, ModelContracts.SlotModel.DEFAULT_SORT);

            this.cursorAdapter.swapCursor(this.cursor);
            this.cursorAdapter.notifyDataSetChanged();

        }else if(discapacitados.isChecked() == true){

            String selection = ModelContracts.SlotModel.buildFloorStateTypeSelection();
            String[] selectionArgs = ModelContracts.SlotModel.buildFloorStateTypeSelectionArgs("2","FREE","DISABLED");

            cursor = this.getContentResolver().query(ModelContracts.SlotModel.buildContentUri(),
                    ModelContracts.SlotModel.DEFAULT_PROJECTIONS,selection,selectionArgs,ModelContracts.SlotModel.DEFAULT_SORT);

            this.cursorAdapter.swapCursor(this.cursor);
            this.cursorAdapter.notifyDataSetChanged();

        }else if(bicicletas.isChecked() == true){

            String selection = ModelContracts.SlotModel.buildFloorStateTypeSelection();
            String[] selectionArgs = ModelContracts.SlotModel.buildFloorStateTypeSelectionArgs("2","FREE","BIKE");

            cursor = this.getContentResolver().query(ModelContracts.SlotModel.buildContentUri(),
                    ModelContracts.SlotModel.DEFAULT_PROJECTIONS,selection,selectionArgs,ModelContracts.SlotModel.DEFAULT_SORT);

            this.cursorAdapter.swapCursor(this.cursor);
            this.cursorAdapter.notifyDataSetChanged();

        }else if(motos.isChecked() == true){

            String selection = ModelContracts.SlotModel.buildFloorStateTypeSelection();
            String[] selectionArgs = ModelContracts.SlotModel.buildFloorStateTypeSelectionArgs("2","FREE","MOTORBIKE");

            cursor = this.getContentResolver().query(ModelContracts.SlotModel.buildContentUri(),
                    ModelContracts.SlotModel.DEFAULT_PROJECTIONS,selection,selectionArgs,ModelContracts.SlotModel.DEFAULT_SORT);

            this.cursorAdapter.swapCursor(this.cursor);
            this.cursorAdapter.notifyDataSetChanged();

        }else if(all.isChecked() == true){

            String selection = ModelContracts.SlotModel.buildFloorStateTypeSelection();
            String[] selectionArgs = ModelContracts.SlotModel.buildFloorStateTypeSelectionArgs("2","FREE","MOTORBIKE");

            cursor = this.getContentResolver().query(ModelContracts.SlotModel.buildContentUri(),
                    ModelContracts.SlotModel.DEFAULT_PROJECTIONS,selection,selectionArgs,ModelContracts.SlotModel.DEFAULT_SORT);

            this.cursorAdapter.swapCursor(this.cursor);
            this.cursorAdapter.notifyDataSetChanged();

        }
    }

}












































