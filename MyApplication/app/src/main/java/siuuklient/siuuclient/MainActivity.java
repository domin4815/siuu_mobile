package siuuklient.siuuclient;

import android.database.Cursor;
import android.database.MatrixCursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        updateList();
    }

    private void updateList() {
        String[] columns = new String[] { "_id", "activity" };

        MatrixCursor matrixCursor= new MatrixCursor(columns);

        matrixCursor.addRow(new Object[] { 1, "Volleyball" });
        matrixCursor.addRow(new Object[] { 2, "Swimming" });

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.list_element,
                matrixCursor,
                columns,
                new int[] {0, R.id.listTextView},
                0
        );
        adapter.notifyDataSetChanged();
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }
}
