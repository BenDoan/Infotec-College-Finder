package mwhs.ap.bkat.app;

import mwhs.ap.doan.app.R;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class SchoolPage extends ListActivity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.college);
		School s = (School) savedInstanceState.get("school");
		Toast.makeText(getApplicationContext(), s.toString(), Toast.LENGTH_LONG);
		setListAdapter(new ArrayAdapter<String>(this,
				mwhs.ap.doan.app.R.id.list_majors, s.getMajors()));
		setListAdapter(new ArrayAdapter<String>(this,
				mwhs.ap.doan.app.R.id.list_sports, s.getSports()));
	}
}
