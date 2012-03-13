package mwhs.ap.bkat.app;

import java.util.ArrayList;

import mwhs.ap.doan.app.R;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SchoolPage extends Activity implements OnClickListener {
	private School s;
	private Button email;

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
		college.setText(curr.getSchoolName());
		
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

//		Bundle b = this.getIntent().getExtras();
//
//		String info = b.getString("schoolPicked");
//		ArrayList<School> schools = b.getParcelableArrayList("schoolList");
//		for (int i = 0; i < schools.size(); i++) {
//			if (schools.get(i).getSchoolName().equals(info)) {
//				s = schools.get(i);
//			}
//		}
//		TextView currCollege = (TextView) findViewById(R.id.curr_college);
//		currCollege.setText(s.getSchoolName());
		
		email = (Button) findViewById(R.id.toggleButton1);
		email.setOnClickListener(this);
		// Toast.makeText(getApplicationContext(), s.toString(),
		// Toast.LENGTH_LONG);
		// setListAdapter(new ArrayAdapter<String>(this,
		// mwhs.ap.doan.app.R.id.list_majors, s.getMajors()));
		// setListAdapter(new ArrayAdapter<String>(this,
		// mwhs.ap.doan.app.R.id.list_sports, s.getSports()));
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.toggleButton1:
			Intent i2 = new Intent(Intent.ACTION_SEND);
			i2.setType("text/plain");
			i2.putExtra(Intent.EXTRA_EMAIL,
					new String[] { "recipient@example.com" });
			i2.putExtra(Intent.EXTRA_SUBJECT, "subject of email");
			i2.putExtra(Intent.EXTRA_TEXT, "body of email");
			try {
				startActivity(Intent.createChooser(i2, "Send mail..."));
			} catch (android.content.ActivityNotFoundException ex) {
				Toast.makeText(SchoolPage.this,
						"There are no email clients installed.",
						Toast.LENGTH_SHORT).show();
			}

		}
	}
}
