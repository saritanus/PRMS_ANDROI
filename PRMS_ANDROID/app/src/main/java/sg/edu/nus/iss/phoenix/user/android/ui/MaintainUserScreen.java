package sg.edu.nus.iss.phoenix.user.android.ui;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.method.KeyListener;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

import sg.edu.nus.iss.phoenix.R;
import sg.edu.nus.iss.phoenix.core.android.controller.ControlFactory;
import sg.edu.nus.iss.phoenix.user.entity.User;

public class MaintainUserScreen extends AppCompatActivity implements View.OnClickListener {

	// Tag for logging
	private static final String TAG = MaintainUserScreen.class.getName();

	private EditText mUserNameEditText;
	private EditText mUserEmailEditText;
	private EditText mUserJoiningDateText;
	private User userEdit = null;
	private UserAdapter mRPAdapter;
	KeyListener mUserNameEditTextKeyListener = null;
	KeyListener mUserJoiningDateEditTextKeyListener = null;
	private int day,month,year;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_user);

		// Find all relevant views that we will need to read user input from
		mUserNameEditText = (EditText) findViewById(R.id.user_name);
		mUserEmailEditText = (EditText) findViewById(R.id.user_email_id);
		mUserJoiningDateText = (EditText) findViewById(R.id.user_joining_date);

		// Keep the KeyListener for name EditText so as to enable editing after disabling it.
		mUserNameEditTextKeyListener = mUserNameEditText.getKeyListener();
		mUserJoiningDateEditTextKeyListener = mUserJoiningDateText.getKeyListener();
		mUserJoiningDateText.setOnClickListener(this);
	}

	@Override
	protected void onPostCreate(@Nullable Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		ControlFactory.getUserController().onDisplayUser(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu options from the res/menu/menu_editor.xml file.
		// This adds menu items to the app bar.

		getMenuInflater().inflate(R.menu.menu_editor, menu);
		return true;
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		super.onPrepareOptionsMenu(menu);
		// If this is a new user, hide the "Delete" menu item.
		if (userEdit == null) {
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

				//sujit code
				// Save user.
//				if (userEdit == null) { // Newly created.
//					Log.v(TAG, "Saving user " + mUserNameEditText.getText().toString() + "...");
//					User user = new User();
//					user.setName(mUserNameEditText.getText().toString());
//					user.setEmailID(mUserEmailEditText.getText().toString());
//					user.setJoiningDate(mUserJoiningDateText.getText().toString());
//					user.setPassword("password");

				if (userEdit == null) {
					// Newly created.
					User u = new User(0,mUserNameEditText.getText().toString(),
							mUserEmailEditText.getText().toString(),
							mUserJoiningDateText.getText().toString());
							ControlFactory.getUserController().selectCreateUser(u);


					//old code
					//ControlFactory.getUserController().selectCreateUser(user);

				} else { // Edited.
					Log.v(TAG, "Saving user program " + userEdit.getName() + "...");
					userEdit.setUserId(userEdit.getUserId());
					userEdit.setName(mUserNameEditText.getText().toString());
					userEdit.setEmailID(mUserEmailEditText.getText().toString());
					userEdit.setJoiningDate(mUserJoiningDateText.getText().toString());

					ControlFactory.getUserController().selectUpdateUser(userEdit);
				}
				return true;
			// Respond to a click on the "Delete" menu option
			case R.id.action_delete:
				Log.v(TAG, "Deleting user " + userEdit.getName() + "...");
				ControlFactory.getUserController().selectDeleteUser(userEdit);
				return true;
			// Respond to a click on the "Cancel" menu option
			case R.id.action_cancel:
				Log.v(TAG, "Canceling creating/editing user...");
				ControlFactory.getUserController().selectCancelCreateEditUser();
				return true;
		}
		return true;
	}
	void dummy(){
		Log.v(TAG, "Saving user " + mUserNameEditText.getText().toString() + "...");
		User user = new User();
		user.setName(mUserNameEditText.getText().toString());
		user.setEmailID(mUserEmailEditText.getText().toString());
		user.setJoiningDate(mUserJoiningDateText.getText().toString());
		user.setPassword("password");
		ControlFactory.getUserController().selectCreateUser(user);
	}


	@Override
	public void onBackPressed() {
		Log.v(TAG, "Canceling creating/editing user ...");
		ControlFactory.getUserController().selectCancelCreateEditUser();
	}

	public void createUser() {
		this.userEdit = null;
		mUserNameEditText.setText("", TextView.BufferType.EDITABLE);
		mUserEmailEditText.setText("", TextView.BufferType.EDITABLE);
		mUserJoiningDateText.setText("",TextView.BufferType.EDITABLE);
		mUserNameEditText.setKeyListener(mUserNameEditTextKeyListener);
		mUserJoiningDateText.setKeyListener(mUserJoiningDateEditTextKeyListener);}


	public void editUser(User us2edit) {
		this.userEdit = us2edit;
		if (us2edit != null) {
			/* edit user*/
			mUserNameEditText.setText(userEdit.getName(), TextView.BufferType.NORMAL);
			mUserEmailEditText.setText(userEdit.getEmailID(), TextView.BufferType.EDITABLE);
			mUserJoiningDateText.setText(userEdit.getJoiningDate(),TextView.BufferType.EDITABLE);
			mUserNameEditText.setKeyListener(null);
		}
	}

	public void showUsers(List<User> users) {
		mRPAdapter.clear();
		for (int i = 0; i < users.size(); i++) {
			mRPAdapter.add(users.get(i));
		}

	}

	/* Date-picker to select user joining date*/
	@Override
	public void onClick(View v) {
		if (v == mUserJoiningDateText) {
			final Calendar c = Calendar.getInstance();
			day = c.get(Calendar.DAY_OF_MONTH);
			month = c.get(Calendar.MONTH);
			year = c.get(Calendar.YEAR);

			DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
				@Override
				public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
					String strDate = year + "-" + (month + 1) + "-" + dayOfMonth;
					mUserJoiningDateText.setText(strDate);
				}
			}
					, day, month, year);
			datePickerDialog.updateDate(year, month, day);
			datePickerDialog.show();


		}

	}
}



