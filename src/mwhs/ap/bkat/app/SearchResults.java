package mwhs.ap.bkat.app;

import java.util.ArrayList;

import mwhs.ap.doan.app.R;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SearchResults extends ListActivity {
	 private ArrayList<School> schools;
	 private ArrayList<School> matchedSchools = new ArrayList<School>();
	 private String[] matchedInfo;
	 private String[] info;
	 private String major = " ";
	 private String sport  = " ";
	 private int population = 0;
	 private String cost = " ";
	 private String housing  = " ";
	 private String region = " ";
	 private int minCost;
	 private int maxCost;
	   /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      //Get the variables stored in the bundle passed from the main activity
      Bundle b = this.getIntent().getExtras();
      
      info = b.getStringArray("strings");
      schools = b.getParcelableArrayList("schools");
      setVariables();
      
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
//          Toast.makeText(getApplicationContext(), ((TextView) view).getText(),
//              Toast.LENGTH_SHORT).show();
          
        	Bundle b = new Bundle();
			b.putString("schoolPicked", matchedInfo[position]);
			Bundle b2 = new Bundle();
			b2.putParcelableArrayList("schoolList", schools);
			Intent i = new Intent();
			i.putExtras(b);
			i.putExtras(b2);
			i.setClass(getApplicationContext(), SchoolPage.class);
			startActivity(i);
          

        }
      });
    }
	private void setVariables() {
		if (!info[0].equals("")) {
			major = info[0];
		}
		if (!info[1].equals("")) {
			sport = info[1];
		}
		if (!info[2].equals("")) {
			population = Integer.parseInt(info[2]);
		}
		String a = info[3];
		if(a.equals("$1,000-$10,000" )){
			minCost = 1000;
			maxCost = 10000;
		} else if(a.equals("$10,001-$20,000" )){
			minCost = 10001;
			maxCost = 20000;			
		}else if(a.equals("$20,001-$30,000" )){
			minCost = 20001;
			maxCost = 30000;
		}else if(a.equals("$30,001-$40,000" )){
			minCost = 30001;
			maxCost = 40000;
		}else if(a.equals("40,001-$50,000+" )){
			minCost = 40001;
			maxCost = Integer.MAX_VALUE;
		}
		if (!info[4].equals("")) {
			 housing = info[4];
		}
		if (!info[5].equals("")) {
			 region = info[5];
		}  
	}
	
	private ArrayList<School> compareValues() {
		for (int i = 0; i < schools.size(); i++) {
			int a = ((schools.get(i).getTuitionInState() + schools.get(i).getTuitionOutOfState())/2) + (schools.get(i).getRoomAndBoardCost()/2);
				if(population == schools.get(i).getTotalUndergrads() || 
				   major.equalsIgnoreCase(schools.get(i).getSchoolType()) || 
				   minCost <= a && maxCost >= a){
					matchedSchools.add(schools.get(i));
				}			
		}
		return matchedSchools;
		
	}

}
