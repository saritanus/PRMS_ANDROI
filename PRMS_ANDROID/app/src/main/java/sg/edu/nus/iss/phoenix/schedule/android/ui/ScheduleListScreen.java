package sg.edu.nus.iss.phoenix.schedule.android.ui;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

import sg.edu.nus.iss.phoenix.R;
import sg.edu.nus.iss.phoenix.core.android.controller.ControlFactory;

import static sg.edu.nus.iss.phoenix.R.id.fab;

/**
 * @author The Administrator
 * @version 1.0
 * @created 20-Sep-2017 1:01:59 AM
 */
public class ScheduleListScreen extends AppCompatActivity implements View.OnClickListener {

	private EditText scheduleFromDate, scheduleToDate;
	private int fday, fmonth, fyear, tday, tmonth, tyear;
	RecyclerView recyclerView;
	RecyclerView.LayoutManager layoutManager;
	RecyclerView.Adapter adapter;
	private FloatingActionButton fabbtn;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_schedule_list);
		// Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		// setSupportActionBar(toolbar);
		scheduleFromDate = (EditText) findViewById(R.id.schedule_from_date);
		scheduleToDate = (EditText) findViewById(R.id.schedule_to_date);
		fabbtn = (FloatingActionButton) findViewById(fab);
		scheduleFromDate.setOnClickListener(this);
		scheduleToDate.setOnClickListener(this);
		fabbtn.setOnClickListener(this);

		recyclerView =
				(RecyclerView) findViewById(R.id.recycler_view);

		layoutManager = new LinearLayoutManager(this);
		recyclerView.setLayoutManager(layoutManager);

		adapter = new ScheduleAdapter();
		recyclerView.setAdapter(adapter);
	}






	public void finalize() throws Throwable {
		super.finalize();
	}
	public void ScheduleListScreen(){

	}

	public void Activity(){

	}

	protected void onCreate(){

	}

	public void onDisplayScheduleList(){

	}

	protected void onPostCreate(){

	}

	public void selectViewProgramSlot(){

	}

	public void showProgramSlots(){

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
					String strDate = year + "-" + (month + 1) + "-" + dayOfMonth;
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
					String strDate = year + "-" + (month + 1) + "-" + dayOfMonth;
					scheduleToDate.setText(strDate);
				}
			}
					, tday, tmonth, tyear);
			datePickerDialog.updateDate(tyear, tmonth, tday);
			datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
			datePickerDialog.show();

		}
		if (v ==fabbtn ) {
			//ControlFactory.getScheduleController().startCreateScheduleUseCase(this);
		}
	}
}//end ScheduleListScreen