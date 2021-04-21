package mobile.asterisk.lesson;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private DataBaseHelper helper ;
    private ListView mainListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper = new DataBaseHelper(MainActivity.this);
        Button btnSave =  (Button) findViewById(R.id.btn_save);
        btnSave.setOnClickListener(MainActivity.this);
        mainListView = (ListView) findViewById(R.id.lv_list);
        mainListView.setOnClickListener(MainActivity.this);
        mainListView.setOnItemClickListener(MainActivity.this);

        getData();
    }

    private Cursor getData(){
        Cursor result = helper.Select("SELECT id, name FROM Test ", null);

        String[] lstName = new String[result.getCount()];
        int i =0;

        Bus[] busData = new Bus[result.getCount()];
        while (result.moveToNext()) {
            String id= result.getString(result.getColumnIndex("id"));
            String name =result.getString(result.getColumnIndex("name"));
            lstName[i] = name;
            Bus bus = new Bus();
            bus.setId(id);
            bus.setName(name);
            busData[i] = bus;
            i++;
        }

        CustomAdapter adapter = new CustomAdapter(this, R.layout.bus_row, busData);
        mainListView.setAdapter(adapter);
        return  result;
    }

    @Override
    public void onClick(View v) {
        try {
            if(v.getId() == R.id.lv_list){
                Toast.makeText(this, "amjilttai", Toast.LENGTH_LONG).show();
            }
            else {
                EditText etxId = (EditText) findViewById(R.id.etx_id);
                EditText etxName = (EditText) findViewById(R.id.et_name);
                TextView txvResult = (TextView) findViewById((R.id.txv_result));

                String id = etxId.getText().toString();
                String name = etxName.getText().toString();
                ContentValues values = new ContentValues();
                values.put("id", id);
                values.put("name", name);
                int result = helper.InsertRow(values);
                if (result > 0) {
                    Toast.makeText(this, "succss", Toast.LENGTH_LONG).show();
                    txvResult.setText("id bol +" + id + "  name bol + " + name);
                    getData();
                } else txvResult.setText("aldaaaa");
            }
        }
        catch (Exception ex) {

        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
