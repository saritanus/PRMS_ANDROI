package sg.edu.nus.iss.phoenix.user.android.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.method.KeyListener;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import sg.edu.nus.iss.phoenix.R;
import sg.edu.nus.iss.phoenix.core.android.controller.ControlFactory;
import sg.edu.nus.iss.phoenix.radioprogram.android.ui.MaintainProgramScreen;
import sg.edu.nus.iss.phoenix.user.entity.User;

/**
 * @author sujit  ambore
 * @version 1.0
 * @created 24-Sep-2017 2:09:53 AM
 */
public class MaintainUserList extends AppCompatActivity{

	private static final String TAG = MaintainProgramScreen.class.getName();
	private EditText mRPNameEditText;
	private EditText mRPDescEditText;

	private User us2edit = null;
	KeyListener mRPNameEditTextKeyListener = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_list);

		// Find all relevant views that we will need to read user input from
		mRPNameEditText = (EditText) findViewById(R.id.maintain_user_name_text_view);
		mRPDescEditText = (EditText) findViewById(R.id.maintain_program_desc_text_view);
		// Keep the KeyListener for name EditText so as to enable editing after disabling it.
		mRPNameEditTextKeyListener = mRPNameEditText.getKeyListener();
	}
	@Override
	protected void onPostCreate(@Nullable Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		ControlFactory.getUserController().onDisplayProgram(this);
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
		// If this is a new radioprogram, hide the "Delete" menu item.
		if (us2edit == null) {
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
				if (us2edit == null) { // Newly created.
					Log.v(TAG, "Saving user " + mRPNameEditText.getText().toString() + "...");
					User rp = new User(mRPNameEditText.getText().toString(),
							mRPDescEditText.getText().toString());
					ControlFactory.getUserController().selectCreateUser(rp);
				}
				else { // Edited.
					Log.v(TAG, "Saving user " + us2edit.getUserName() + "...");
					us2edit.setUserName(mRPNameEditText.getText().toString());
					ControlFactory.getUserController().selectUpdateUser(us2edit);
				}
				return true;
			// Respond to a click on the "Delete" menu option
			case R.id.action_delete:
				Log.v(TAG, "Deleting user " + us2edit.getUserName() + "...");
				ControlFactory.getUserController().selectDeleteUser(us2edit);
				return true;
			// Respond to a click on the "Cancel" menu option
			case R.id.action_cancel:
				Log.v(TAG, "Canceling creating/editing radio program...");
				ControlFactory.getUserController().selectCancelCreateEditUser();
				return true;
		}

		return true;
	}


	public void finalize() throws Throwable {}

}//end MaintainUserList