package sg.edu.nus.iss.phoenix.schedule.android.ui;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

import sg.edu.nus.iss.phoenix.R;
import sg.edu.nus.iss.phoenix.core.android.controller.ControlFactory;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.user.entity.User;

import static sg.edu.nus.iss.phoenix.R.id.fab;

/**
 * @author The Administrator
 * @version 1.0
 * @created 20-Sep-2017 1:01:59 AM
 */
public class MaintainScheduleScreen extends AppCompatActivity implements View.OnClickListener {

	private EditText scheduleStartDate, scheduleDuration, schedulePresenter,scheduleProducer,scheduleProgram;
	private int fday, fmonth, fyear,hour, minute;
	private ProgramSlot ps2edit = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_schedule);
		schedulePresenter = (EditText) findViewById(R.id.edittext_presenter);
		scheduleProgram = (EditText) findViewById(R.id.edittext_radioprogram);
		scheduleProducer = (EditText) findViewById(R.id.edittext_producer);
		scheduleStartDate = (EditText) findViewById(R.id.schedule_start_date);
		scheduleDuration = (EditText) findViewById(R.id.schedule_duration);
		scheduleStartDate.setOnClickListener(this);
		scheduleDuration.setOnClickListener(this);


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

		if (v == scheduleDuration) {
			final Calendar c = Calendar.getInstance();
			hour = c.get(Calendar.HOUR_OF_DAY);
			minute = c.get(Calendar.MINUTE);
			TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
				@Override
				public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

					scheduleDuration.setText(hourOfDay + ":" + minute);
				}
			}
					, hour, minute, false);
			timePickerDialog.show();
		}



	}
	@Override
	protected void onPostCreate(@Nullable Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		ControlFactory.getScheduleController().onDisplaySchdule(this);
	}




	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu options from the res/menu/menu_editor.xml file.
		// This adds menu items to the app bar.
		getMenuInflater().inflate(R.menu.menu_editor, menu);
		return true;
	}

	/**
	 * This method is called after invalidateOptionsMenu(), so that the
	 * menu can be updated (some menu items can be hidden or made visible).
	 */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		super.onPrepareOptionsMenu(menu);
		// If this is a new radioprogram, hide the "Delete" menu item.
		if (ps2edit == null) {
			MenuItem menuItem = menu.findItem(R.id.action_delete);
			menuItem.setVisible(false);
		}
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// User clicked on a menu option in the app bar overflow menu
		switch (item.getItemId()) {
			// Respond to a click on the "Save" menu option
			case R.id.action_save:
				// Save radio program.

				if (ps2edit == null) { // Newly created.
				//	Log.v(TAG, "Saving Schedule Program " + schedulePresenter.getText().toString() + "...");
					ProgramSlot ps = new ProgramSlot();
					RadioProgram rp = new RadioProgram();
					rp.setRadioProgramId(12);
					ps.setRadioProgram(rp);
					User presenter = new User();
					presenter.setUserId(1);
					User producer = new User();
					presenter.setUserId(2);
					ps.setPresenter(presenter);
					ps.setProducer(producer);
					ps.setDuration(scheduleDuration.getText().toString());
					ps.setStartTime(scheduleStartDate.getText().toString());
					ControlFactory.getScheduleController().selectCreateSchedule();
				}
				else { // Edited.
				//	Log.v(TAG, "Saving radio program " + rp2edit.getRadioProgramId() + "...");
				//	rp2edit.setRadioProgramDescription(mRPDescEditText.getText().toString());
				//	rp2edit.setRadioProgramDuration(mDurationEditText.getText().toString());
				//	ControlFactory.getProgramController().selectUpdateProgram(rp2edit);
				}
				return true;
			// Respond to a click on the "Delete" menu option
			case R.id.action_delete:
			//	Log.v(TAG, "Deleting radio program " + ps2edit.getRadioProgramName() + "...");
			//	ControlFactory.getProgramController().selectDeleteProgram(ps2edit);
				return true;
			// Respond to a click on the "Cancel" menu option
			case R.id.action_cancel:
			//	Log.v(TAG, "Canceling creating/editing radio program...");
			//	ControlFactory.getProgramController().selectCancelCreateEditProgram();
				return true;
		}

		return true;
	}

	@Override
	public void onBackPressed() {
	//	Log.v(TAG, "Canceling creating/editing radio program...");
		ControlFactory.getProgramController().selectCancelCreateEditProgram();
	}

	public void createProgram() {
//		this.rp2edit = null;
//		mRPNameEditText.setText("", TextView.BufferType.EDITABLE);
//		mRPDescEditText.setText("", TextView.BufferType.EDITABLE);
//		mDurationEditText.setText("", TextView.BufferType.EDITABLE);
//		mRPNameEditText.setKeyListener(mRPNameEditTextKeyListener);
	}

	public void editProgram(RadioProgram rp2edit) {
		//this.rp2edit = rp2edit;
		if (rp2edit != null) {
//			mRPNameEditText.setText(rp2edit.getRadioProgramName(), TextView.BufferType.NORMAL);
//			mRPDescEditText.setText(rp2edit.getRadioProgramDescription(), TextView.BufferType.EDITABLE);
//			mDurationEditText.setText(rp2edit.getRadioProgramDuration(), TextView.BufferType.EDITABLE);
//			mRPNameEditText.setKeyListener(null);
		}
	}
}