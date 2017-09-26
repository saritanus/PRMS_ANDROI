package sg.edu.nus.iss.phoenix.user.android.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.method.KeyListener;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import sg.edu.nus.iss.phoenix.R;
import sg.edu.nus.iss.phoenix.core.android.controller.ControlFactory;
import sg.edu.nus.iss.phoenix.user.entity.User;

public class MaintainUserScreen extends AppCompatActivity {

    // Tag for logging
    private static final String TAG = MaintainUserScreen.class.getName();

    private EditText mRPNameEditText;
    private EditText mRPDescEditText;
    //private User selectedRP = null;
    private User rp2edit = null;
    private UserAdapter mRPAdapter;
    KeyListener mRPNameEditTextKeyListener = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        // Find all relevant views that we will need to read user input from
        mRPNameEditText = (EditText) findViewById(R.id.maintain_program_name_text_view);
        mRPDescEditText = (EditText) findViewById(R.id.maintain_program_desc_text_view);
        // Keep the KeyListener for name EditText so as to enable editing after disabling it.
        mRPNameEditTextKeyListener = mRPNameEditText.getKeyListener();
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
        // If this is a new radioprogram, hide the "Delete" menu item.
        if (rp2edit == null) {
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
                if (rp2edit == null) { // Newly created.
                    Log.v(TAG, "Saving user " + mRPNameEditText.getText().toString() + "...");
                    User rp = new User(mRPNameEditText.getText().toString(),
                            mRPDescEditText.getText().toString());
                    ControlFactory.getUserController().selectCreateUser(rp);
                } else { // Edited.
                    Log.v(TAG, "Saving user program " + rp2edit.getUserName() + "...");
                    rp2edit.setUserName(mRPDescEditText.getText().toString());
                    rp2edit.setUserRoleDescription(mRPDescEditText.getText().toString());
                    ControlFactory.getUserController().selectUpdateUser(rp2edit);
                }
                return true;
            // Respond to a click on the "Delete" menu option
            case R.id.action_delete:
                Log.v(TAG, "Deleting user " + rp2edit.getUserName() + "...");
                ControlFactory.getUserController().selectDeleteUser(rp2edit);
                return true;
            // Respond to a click on the "Cancel" menu option
            case R.id.action_cancel:
                Log.v(TAG, "Canceling creating/editing user...");
                ControlFactory.getUserController().selectCancelCreateEditUser();
                return true;
        }

        return true;
    }


    @Override
    public void onBackPressed() {
        Log.v(TAG, "Canceling creating/editing user ...");
        ControlFactory.getUserController().selectCancelCreateEditUser();
    }

    public void createUser() {
        this.rp2edit = null;
        mRPNameEditText.setText("", TextView.BufferType.EDITABLE);
        mRPDescEditText.setText("", TextView.BufferType.EDITABLE);
        mRPNameEditText.setKeyListener(mRPNameEditTextKeyListener);
    }

    public void editUser(User us2edit) {
        this.rp2edit = us2edit;
        if (us2edit != null) {
            mRPNameEditText.setText(rp2edit.getUserName(), TextView.BufferType.NORMAL);
            mRPDescEditText.setText(rp2edit.getUserRoleDescription(), TextView.BufferType.EDITABLE);
            mRPNameEditText.setKeyListener(null);
        }
    }

        public void showUsers(List<User> users) {
            mRPAdapter.clear();
            for (int i = 0; i < users.size(); i++) {
                mRPAdapter.add(users.get(i));
            }

        }

        //private UserAdapter mUSAdapter;
//public void finalize() throws Throwable {
//		super.finalize();
//}
        //public void MaintainUserScreen(){}
        //public void onCreate(){}
        //public void onPostCreate(){}
        //public void selectCreateProgram(){}
        //public void selectCreateUser(){}
        //public void selectDeleteUser(){}
        //public void selectModifyUser(){}
        //public void selelctViewProgram(){}
        //public void showUsers(List<User> user) {
        //  mUSAdapter.clear();
        // for (int i = 0; i < user.size(); i++) {
        //    mUSAdapter.add(user.get(i)); } } }


    }



