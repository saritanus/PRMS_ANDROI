package sg.edu.nus.iss.phoenix.user.android.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import sg.edu.nus.iss.phoenix.R;
import sg.edu.nus.iss.phoenix.core.android.controller.ControlFactory;
import sg.edu.nus.iss.phoenix.radioprogram.android.ui.RadioProgramAdapter;
import sg.edu.nus.iss.phoenix.radioprogram.android.ui.ReviewSelectProgramScreen;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;
import sg.edu.nus.iss.phoenix.user.entity.Presenter;
import sg.edu.nus.iss.phoenix.user.entity.Producer;
import sg.edu.nus.iss.phoenix.user.entity.User;

/**
 * @author sujit ambore
 * @version 1.0
 */

public class SelectRoleScree extends AppCompatActivity {

    private static final String TAG = ReviewSelectProgramScreen.class.getName();

    public Presenter m_Presenter;
    public Producer m_Producer;
    public Producer m_System_Admin;
    public Producer m_System_Manager;

    private UserAdapter mRPAdapter;
    // private ArrayAdapter<String> adapter = null;
    private ListView mListView;
    private User selectedRP = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_role_scree);

        ArrayList<User> users = new ArrayList<User>();
        mRPAdapter = new UserAdapter(this, users);

        mListView = (ListView) findViewById(R.id.user_select_us_list);
        mListView.setAdapter(mRPAdapter);

        mListView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                // Log.v(TAG, "User at position " + position + " selected.");
                User rp = (User) adapterView.getItemAtPosition(position);
                // Log.v(TAG, "User name is " + rp.getUserName());
                selectedRP = rp;
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // your stuff
            }
        });
    }


    }

