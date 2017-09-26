package sg.edu.nus.iss.phoenix.user.android.ui;/*
package sg.edu.nus.iss.phoenix.user.android.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import sg.edu.nus.iss.phoenix.R;
import sg.edu.nus.iss.phoenix.radioprogram.android.ui.ReviewSelectProgramScreen;
import sg.edu.nus.iss.phoenix.user.android.ui.UserAdapter;
import sg.edu.nus.iss.phoenix.user.entity.User;

//import sg.edu.nus.iss.phoenix.user.entity.Presenter;
//import sg.edu.nus.iss.phoenix.user.entity.Producer;

*/
/**
 * @author sujit ambore
 * @version 1.0
 *//*


public class SelectRoleScreen extends AppCompatActivity {

    private static final String TAG = ReviewSelectProgramScreen.class.getName();

  //  public Presenter m_Presenter;
  //  public Producer m_Producer;
  // public Producer m_System_Admin;
    // public Producer m_System_Manager;

    private UserAdapter mUSAdapter;
    // private ArrayAdapter<String> adapter = null;
    private ListView mListView;
    private User selectedRP = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        ArrayList<User> users = new ArrayList<User>();
        mUSAdapter = new UserAdapter(this, users);

        mListView = (ListView) findViewById(R.id.user_list);
        mListView.setAdapter(mUSAdapter);

        mListView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                // Log.v(TAG, "User at position " + position + " selected.");
                User us = (User) adapterView.getItemAtPosition(position);
                // Log.v(TAG, "User name is " + rp.getUserName());
                selectedRP = us;
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // your stuff
            }
        });
    }


}
*/
