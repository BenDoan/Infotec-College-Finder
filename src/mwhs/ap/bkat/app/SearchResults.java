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
	 private String population = " ";
	 private String cost = " ";
	 private String housing  = " ";
	 private String region = " ";
	 private String setting = " ";
	 private int minCost;
	 private int maxCost;
	 private int minPop;
	 private int maxPop;
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
			b2.putParcelable("schoolList", schools.get(position));
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
		String b = info[2];
		if (b.equals("1-1,000")) {
			minPop = 0;
			maxPop = 1000;
		} else if(b.equals("1,001-5,000")){
			minPop = 1001;
			maxPop = 5000;
		} else if(b.equals("5,001-10,000")){
			minPop = 5001;
			maxPop = 10000;
		} else if(b.equals("10,001-15,000")){
			minPop = 10001;
			maxPop = 15000;
		} else if(b.equals("15,001-20,000")){
			minPop = 15001;
			maxPop = 20000;
		} else if(b.equals("20,001-25,000")){
			minPop = 20001;
			maxPop = 25000;
		} else if(b.equals("25,001-30,000")){
			minPop = 25001;
			maxPop = 30000;
		} else if(b.equals("30,001-35,000")){
			minPop = 30001;
			maxPop = 35000;
		} else if(b.equals("35,001-40,000")){
			minPop = 35001;
			maxPop = 40000;
		} else if(b.equals("40,001-45,000")){
			minPop = 40001;
			maxPop = 45000;
		} else if(b.equals("45,001-50,000+")){
			minPop = 45001;
			maxPop = Integer.MAX_VALUE;
		} else{
			minPop = 0;
			maxPop = Integer.MAX_VALUE;
		}
		String a = info[3];
		if(a.equals("$1,000-$10,000" )){
			minCost = 0;
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
		if (!info[6].equals("")) {
			setting = info[6];
		}
	}
	
	private ArrayList<School> compareValues() {
		for (int i = 0; i < schools.size(); i++) {
			int a = ((schools.get(i).getTuitionInState() + schools.get(i).getTuitionOutOfState())/2) + (schools.get(i).getRoomAndBoardCost()/2);
				if(minPop <= schools.get(i).getTotalUndergrads() && maxPop >= schools.get(i).getTotalUndergrads() || 
				   major.equalsIgnoreCase(schools.get(i).getSchoolType()) || 
				   minCost <= a && maxCost >= a){
					matchedSchools.add(schools.get(i));
				}			
		}
		return matchedSchools;
		
	}

}
