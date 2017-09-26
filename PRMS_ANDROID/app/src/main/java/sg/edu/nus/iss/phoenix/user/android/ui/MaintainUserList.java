package sg.edu.nus.iss.phoenix.user.android.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

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

	private ListView mListView;
	private UserAdapter mRPAdapter;
	private User selectedRP = null;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_list);

		ArrayList<User> users = new ArrayList<User>();
		mRPAdapter = new UserAdapter(this, users);

		// Setup FAB to open EditorActivity
		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				ControlFactory.getProgramController().selectCreateProgram();
			}
		});

		mListView = (ListView) findViewById(R.id.user_us_list);
		mListView.setAdapter(mRPAdapter);


		// Setup the item selection listener
		mListView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
				// Log.v(TAG, "Radio program at position " + position + " selected.");
				User rp = (User) adapterView.getItemAtPosition(position);
				// Log.v(TAG, "Radio program name is " + rp.getRadioProgramName());
				selectedRP = rp;
			}
			@Override
			public void onNothingSelected(AdapterView<?> adapterView) {
				// your stuff
			}
		});

	}

	@Override
	protected void onPostCreate(@Nullable Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		mListView.setSelection(0);

		ControlFactory.getUserController().onDisplayUserList(this);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu options from the res/menu/menu_editor.xml file.
		// This adds menu items to the app bar.
		getMenuInflater().inflate(R.menu.menu_editor, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// User clicked on a menu option in the app bar overflow menu
		switch (item.getItemId()) {
			// Respond to a click on the "View" menu option
			case R.id.action_view:
				if (selectedRP == null) {
					// Prompt for the selection of a radio program.
					Toast.makeText(this, "Select a user program first! Use arrow keys on emulator", Toast.LENGTH_SHORT).show();
					Log.v(TAG, "There is no selected radio program.");
				}
				else {
					Log.v(TAG, "Viewing user  program: " + selectedRP.getUserName() + "...");
					ControlFactory.getUserController().selectEditUser(selectedRP);
				}
		}

		return true;
	}

	@Override
	public void onBackPressed() {
		ControlFactory.getUserController().maintainedUser();
	}

	public void showUsers(List<User> users) {
		mRPAdapter.clear();
		for (int i = 0; i < users.size(); i++) {
			mRPAdapter.add(users.get(i));
		}
	}
	public void finalize() throws Throwable {}

}

//end MaintainUserList