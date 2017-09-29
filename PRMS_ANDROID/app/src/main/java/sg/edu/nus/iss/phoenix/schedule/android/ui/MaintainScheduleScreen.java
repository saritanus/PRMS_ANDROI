package sg.edu.nus.iss.phoenix.schedule.android.ui;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.app.Activity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

import sg.edu.nus.iss.phoenix.R;

/**
 * @author The Administrator
 * @version 1.0
 * @created 20-Sep-2017 1:01:59 AM
 */
public class MaintainScheduleScreen extends Activity implements View.OnClickListener {

	private EditText scheduleFromDate, scheduleToDate;
	private FloatingActionButton fabbtn;
	private int fday, fmonth, fyear, tday, tmonth, tyear;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_schedule_list);
		fabbtn = (FloatingActionButton) findViewById(R.id.fab);
		scheduleFromDate = (EditText) findViewById(R.id.schedule_from_date);
		scheduleToDate = (EditText) findViewById(R.id.schedule_to_date);
		scheduleFromDate.setOnClickListener(this);
		scheduleToDate.setOnClickListener(this);
		fabbtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v == scheduleFromDate) {
			final Calendar c = Calendar.getInstance();
			fday = c.get(Calendar.DAY_OF_MONTH);
			fmonth = c.get(Calendar.MONTH);
			fyear = c.get(Calendar.YEAR);

			DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
				@Override
				public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
					String strDate = dayOfMonth + "-" + (month + 1) + "-" + year;
					scheduleFromDate.setText(strDate);
				}
			}
					, fday, fmonth, fyear);
			datePickerDialog.updateDate(fyear, fmonth, fday);
			datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
			datePickerDialog.show();

		}

		if (v == scheduleToDate) {
			final Calendar c = Calendar.getInstance();
			tday = c.get(Calendar.DAY_OF_MONTH);
			tmonth = c.get(Calendar.MONTH);
			tyear = c.get(Calendar.YEAR);

			DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
				@Override
				public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
					String strDate = dayOfMonth + "-" + (month + 1) + "-" + year;
					scheduleToDate.setText(strDate);
				}
			}
					, tday, tmonth, tyear);
			datePickerDialog.updateDate(tyear, tmonth, tday);
			datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
			datePickerDialog.show();

		}
		}

}


