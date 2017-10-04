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

	private static final String TAG =MaintainScheduleScreen.class.getName(); ;
	private EditText scheduleStartDate,scheduleStartTime, scheduleDuration;
	private EditText schedulePresenter,scheduleProducer,scheduleProgram;
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
		scheduleStartTime = (EditText) findViewById(R.id.schedule_startTime);
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
					String temp = "0";
					if(dayOfMonth < 10){temp = temp+String.valueOf(dayOfMonth);}
					else
						temp = String.valueOf(dayOfMonth);
					String strDate = temp + "-" + (month + 1) + "-" + year;
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

					scheduleStartTime.setText(hourOfDay + ":" + minute+":00");
				}
			}
					, hour, minute, false);
			timePickerDialog.show();
		}



	}
	@Override
	protected void onPostCreate(@Nullable Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		ControlFactory.getScheduleController().onDisplaySchedule(this);
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
					Log.v(TAG, "Saving Schedule Program " + scheduleProgram.getText().toString() + "...");
					ProgramSlot ps2edit = new ProgramSlot();
					RadioProgram rp = new RadioProgram();
					rp.setRadioProgramName(scheduleProgram.getText().toString());
					ps2edit.setRadioProgram(rp);
					User presenter = new User();
					presenter.setName(schedulePresenter.getText().toString());
					ps2edit.setPresenter(presenter);
					User producer = new User();
					producer.setName(scheduleProducer.getText().toString());
					ps2edit.setProducer(producer);
					ps2edit.setDuration(scheduleDuration.getText().toString());
					ps2edit.setStartTime(scheduleStartDate.getText().toString()+" "+scheduleStartTime.getText().toString());
					ControlFactory.getScheduleController().selectCreateSchedule(ps2edit);
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

	public void createSchedule() {
		this.ps2edit = null;

		schedulePresenter.setText("", TextView.BufferType.EDITABLE);
		scheduleProgram.setText("", TextView.BufferType.EDITABLE);
		scheduleProducer.setText("", TextView.BufferType.EDITABLE);
		scheduleStartDate.setOnClickListener(this);
		scheduleDuration.setOnClickListener(this);
		scheduleStartTime.setText("", TextView.BufferType.EDITABLE);

	}

	public void editSchedule(ProgramSlot ps2edit) {
		this.ps2edit = ps2edit;
		if (ps2edit != null) {
			schedulePresenter.setText(ps2edit.getPresenter().getName(), TextView.BufferType.EDITABLE);
			scheduleProgram.setText(ps2edit.getRadioProgram().getRadioProgramName(), TextView.BufferType.EDITABLE);
			scheduleProducer.setText(ps2edit.getProducer().getName(), TextView.BufferType.EDITABLE);
			scheduleStartDate.setText(ps2edit.getStartTime(),TextView.BufferType.EDITABLE);
			scheduleStartTime.setText(ps2edit.getStartTime(), TextView.BufferType.EDITABLE);
			scheduleStartDate.setOnClickListener(this);
			scheduleDuration.setOnClickListener(this);

//			mRPNameEditText.setKeyListener(null);
		}
	}
}