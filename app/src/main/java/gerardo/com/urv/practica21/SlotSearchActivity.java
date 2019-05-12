package gerardo.com.urv.practica21;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import cat.tomasgis.module.communication.listeners.IDataReceiver;
import gerardo.com.urv.practica21.Models.Slot;

public class SlotSearchActivity extends AppCompatActivity implements IDataReceiver {
    private RadioButton normal, electrico, discapacitados,bicicletas, motos;
    private ListView mListView;
    private List<String> mList = new ArrayList<>();
    private ArrayAdapter<String> mArrayAdapter;

    private Slot[] slot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slot_search);

        normal = findViewById(R.id.common);
        electrico = findViewById(R.id.electric);
        discapacitados = findViewById(R.id.disabled);
        bicicletas = findViewById(R.id.bike);
        motos = findViewById(R.id.motorbike);

    }
    public void onRadioButtonClicked(View view){


    }

    @Override
    public void onReceiveData(String data) {
        if(data != null){
            if(data.length()>0){
                Gson gson = new Gson();
                slot = gson.fromJson(data, Slot[].class);


            }
        }
    }
}
