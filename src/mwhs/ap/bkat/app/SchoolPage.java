package mwhs.ap.bkat.app;

import mwhs.ap.doan.app.R;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SchoolPage extends Activity{

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.college);
		
		Bundle b = this.getIntent().getExtras();
		School s = (School) b.get("school");

		setTextViews(s);
		
		String[] majors = new String[2];
		majors[0]= "Computer Science";
		majors[1]= "Math";		
		s.setMajors(majors);
		
		String[] sports = new String[2];
		sports[0]= "Hockey";
		sports[1]= "Football";		
		s.setSports(sports);
		
		
		ListView m_listview = (ListView) findViewById(R.id.list_majors);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, s.getMajors());
		m_listview.setAdapter(adapter);

		ListView s_listview2 = (ListView) findViewById(R.id.list_sports);
		ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, s.getSports());
		s_listview2.setAdapter(adapter2);
	}
	
	private void setTextViews(School curr){
		TextView college = (TextView) findViewById(R.id.curr_college);
		college.setText(curr.toString());
		
		TextView setting = (TextView) findViewById(R.id.curr_location);
		setting.setText(curr.getSetting());
		
		TextView inTuition = (TextView) findViewById(R.id.curr_in_tuition);
		inTuition.setText("" + curr.getTuitionInState());
		
		TextView outTuition = (TextView) findViewById(R.id.curr_out_tuition);
		outTuition.setText("" + curr.getTuitionOutOfState());
		
		TextView size = (TextView) findViewById(R.id.curr_size);
		size.setText("" + curr.getTotalUndergrads());
		
		TextView housing = (TextView) findViewById(R.id.curr_housing);
		housing.setText("" + curr.getRoomAndBoardCost());
	}
}
