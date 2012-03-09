package mwhs.ap.bkat.app;

import java.util.ArrayList;

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
	 private ArrayList<School> schools;
	 private ArrayList<School> matchedSchools = new ArrayList<School>();
	 private String[] matchedInfo;
	 private String[] info;
	   /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      //Get the variables stored in the bundle passed from the main activity
      Bundle b = this.getIntent().getExtras();
      
      info = b.getStringArray("strings");
      schools = b.getParcelableArrayList("schools");
      
      compareValues();
      matchedInfo = new String[matchedSchools.size()];
      for (int i = 0; i < matchedSchools.size(); i++) {
		matchedInfo[i] = matchedSchools.get(i).getSchoolName();
      }
      
      setListAdapter(new ArrayAdapter<String>(this, mwhs.ap.doan.app.R.layout.results_list,matchedInfo));
      
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
	private ArrayList<School> compareValues() {
		for (int i = 0; i < schools.size(); i++) {
				if(Integer.parseInt(info[2]) == schools.get(i).getTotalUndergrads() && 
				   info[0].equals(schools.get(i).getSchoolType())){
					matchedSchools.add(schools.get(i));
				}else{
					
				}
			
		}
		return matchedSchools;
		
	}

}
