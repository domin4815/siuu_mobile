package siuu.siuuklient;

import android.database.MatrixCursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

/**
 * Created by robert on 6/22/16.
 */
public class AddEventActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_event);

        ListView preferencesList = (ListView) findViewById(R.id.inner_listview);
        preferencesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("Smiga!!!");
//                Intent intent = new Intent(PreferencesActivity.this, ShowNote.class);
//                intent.putExtra("id", id + "");
//                startActivity(intent);
            }
        });

        updateList();

    }

    private void updateList() {
        String[] columns = new String[] { "_id", "activity" };

        MatrixCursor matrixCursor= new MatrixCursor(columns);

        matrixCursor.addRow(new Object[] { 1, "Volleyball" });
        matrixCursor.addRow(new Object[] { 2, "Swimming" });

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.list_view,
                matrixCursor,
                columns,
                new int[] {0, R.id.preference_name},
                0
        );
        adapter.notifyDataSetChanged();
        ListView listView = (ListView) findViewById(R.id.inner_listview);
        listView.setAdapter(adapter);
    }
}
