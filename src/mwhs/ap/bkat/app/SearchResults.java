package mwhs.ap.bkat.app;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class SearchResults extends ListActivity {
	 private String[] SCHOOLS;
	   /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      //Get the variables stored in the bundle passed from the main activity
      Bundle b = this.getIntent().getExtras();
      
      SCHOOLS = b.getStringArray("strings");
          
      setListAdapter(new ArrayAdapter<String>(this, mwhs.ap.doan.app.R.layout.results_list, SCHOOLS));
      
      ListView lv = getListView();
      lv.setTextFilterEnabled(true);
      ;
      lv.setOnItemClickListener(new OnItemClickListener() {
        public void onItemClick(AdapterView<?> parent, View view,
            int position, long id) {
          // When clicked, show a toast with the TextView text
          Toast.makeText(getApplicationContext(), ((TextView) view).getText(),
              Toast.LENGTH_SHORT).show();
        }
      });
    }

}
