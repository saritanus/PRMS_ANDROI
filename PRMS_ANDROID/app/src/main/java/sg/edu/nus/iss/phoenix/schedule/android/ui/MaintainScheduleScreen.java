package sg.edu.nus.iss.phoenix.schedule.android.ui;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

import sg.edu.nus.iss.phoenix.R;

import static sg.edu.nus.iss.phoenix.R.id.fab;

/**
 * @author The Administrator
 * @version 1.0
 * @created 20-Sep-2017 1:01:59 AM
 */
public class MaintainScheduleScreen extends Activity implements View.OnClickListener {

	private EditText scheduleStartDate, scheduleStartTime;
	private int fday, fmonth, fyear,hour, minute;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_schedule);
		scheduleStartDate = (EditText) findViewById(R.id.schedule_from_date);
		scheduleStartTime = (EditText) findViewById(R.id.schedule_to_date);
		scheduleStartDate.setOnClickListener(this);
		scheduleStartTime.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v == scheduleStartDate) {
			final Calendar c = Calendar.getInstance();
			fday = c.get(Calendar.DAY_OF_MONTH);
			fmonth = c.get(Calendar.MONTH);
			fyear = c.get(Calendar.YEAR);

			DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
				@Override
				public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
					String strDate = dayOfMonth + "-" + (month + 1) + "-" + year;
					scheduleStartDate.setText(strDate);
				}
			}
					, fday, fmonth, fyear);
			datePickerDialog.updateDate(fyear, fmonth, fday);
			datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
			datePickerDialog.show();

		}

		if (v == scheduleStartTime) {
			final Calendar c = Calendar.getInstance();
			hour = c.get(Calendar.HOUR_OF_DAY);
			minute = c.get(Calendar.MINUTE);
			TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
				@Override
				public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

					scheduleStartTime.setText(hourOfDay + ":" + minute);
				}
			}
					, hour, minute, false);
			timePickerDialog.show();
		}



	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_card_demo, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_copy) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

}


