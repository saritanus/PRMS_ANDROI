package sg.edu.nus.iss.phoenix.user.android.delegate;

import android.os.AsyncTask;

import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;
import sg.edu.nus.iss.phoenix.user.android.controller.UserController;
import sg.edu.nus.iss.phoenix.user.entity.User;

/**
 * Created by User on 24/9/2017.
 */

public class UpdateUserDelegate extends AsyncTask<RadioProgram, Void, Boolean> {

    private static final String TAG = CreateUserDelegate.class.getName();

    private final UserController userController;

    public UpdateUserDelegate(UserController userController) {
        this.userController = userController;
    }
    @Override
    protected Boolean doInBackground(RadioProgram... params) {
        return null;
    }

    public void execute(User us) {
    }
}
