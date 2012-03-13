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

		String info = b.getString("schoolPicked");
		ArrayList<School> schools = b.getParcelableArrayList("schoolList");
		for (int i = 0; i < schools.size(); i++) {
			if (schools.get(i).getSchoolName().equals(info)) {
				s = schools.get(i);
			}
		}
		TextView currCollege = (TextView) findViewById(R.id.curr_college);
		currCollege.setText(s.getSchoolName());
		
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
			Intent i2 = new Intent(Intent.ACTION_SENDTO);
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
