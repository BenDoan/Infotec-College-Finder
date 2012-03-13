package mwhs.ap.bkat.app;

import java.util.ArrayList;

import mwhs.ap.doan.app.R;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SchoolPage extends Activity {
	private School s;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.college);
		Bundle b = this.getIntent().getExtras();
		
		String info = b.getString("schoolPicked");
		ArrayList<School> schools = b.getParcelableArrayList("schoolList");
		for (int i = 0; i < schools.size(); i++) {
			if(schools.get(i).getSchoolName().equals(info)){
				s = schools.get(i);
			}
		}
		TextView currCollege = (TextView) findViewById(R.id.curr_college);
		currCollege.setText(s.getSchoolName());

		//Toast.makeText(getApplicationContext(), s.toString(), Toast.LENGTH_LONG);
//		setListAdapter(new ArrayAdapter<String>(this,
//				mwhs.ap.doan.app.R.id.list_majors, s.getMajors()));
//		setListAdapter(new ArrayAdapter<String>(this,
//				mwhs.ap.doan.app.R.id.list_sports, s.getSports()));
	}
}
