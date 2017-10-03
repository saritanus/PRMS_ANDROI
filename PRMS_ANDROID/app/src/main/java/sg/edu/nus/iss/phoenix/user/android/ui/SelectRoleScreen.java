
package sg.edu.nus.iss.phoenix.user.android.ui;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import java.util.ArrayList;

import sg.edu.nus.iss.phoenix.R;
import sg.edu.nus.iss.phoenix.core.android.controller.ControlFactory;

/**
 * Created by vimal on 30-Sep-17.
 */

public class SelectRoleScreen extends AppCompatActivity implements View.OnClickListener {

private static final String TAG = SelectRoleScreen.class.getName();
public Button btnCreateRole;
public CheckBox systemAdminCheckbox;
public CheckBox presenterCheckbox;
public CheckBox producerCheckbox;
public CheckBox stationManagerCheckbox;
public ArrayList<Integer> listRoles = new ArrayList();
public int systemadmin, presenter, producer, stationmanager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_role);

        Button btnCreateRole = (Button) findViewById(R.id.btn_Create_Role);
        systemAdminCheckbox = (CheckBox) findViewById(R.id.system_admin_checkbox);
        presenterCheckbox = (CheckBox) findViewById(R.id.presenter_checkbox);
        producerCheckbox = (CheckBox) findViewById(R.id.producer_checkbox);
        stationManagerCheckbox = (CheckBox) findViewById(R.id.station_manager_checkbox);
        btnCreateRole.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == btnCreateRole)
            if (systemAdminCheckbox.isChecked()) {
                listRoles.add(1);
                Log.v(TAG, "Selecting the list of roles admin: " + listRoles + "...");
            }
        if (presenterCheckbox.isChecked()) {
            listRoles.add(3);
        }
        if (producerCheckbox.isChecked()) {
            listRoles.add(4);
        }
        if (stationManagerCheckbox.isChecked()) {
            listRoles.add(2);
        }
        Log.v(TAG, "Selecting the list of roles: " + listRoles + "...");
        ControlFactory.getUserController().rolesSelected(listRoles);

    }
}