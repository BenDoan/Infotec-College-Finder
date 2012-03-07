package mwhs.ap.bkat.app;


import android.app.Activity;
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
	   /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);

      setListAdapter(new ArrayAdapter<String>(this, mwhs.ap.doan.app.R.layout.results_list, SCHOOLS));

      ListView lv = getListView();
      lv.setTextFilterEnabled(true);

      lv.setOnItemClickListener(new OnItemClickListener() {
        public void onItemClick(AdapterView<?> parent, View view,
            int position, long id) {
          // When clicked, show a toast with the TextView text
          Toast.makeText(getApplicationContext(), ((TextView) view).getText(),
              Toast.LENGTH_SHORT).show();
        }
      });
    }
    static final String[] SCHOOLS = new String[] {
    	"Nebraska", "Iowa", "Stuff"
      };
}
