package sg.edu.nus.iss.phoenix.user.android.controller;

import android.content.Intent;
import android.util.Log;

import java.util.List;
import sg.edu.nus.iss.phoenix.core.android.controller.ControlFactory;
import sg.edu.nus.iss.phoenix.core.android.controller.MainController;
import sg.edu.nus.iss.phoenix.user.android.delegate.CreateUserDelegate;
import sg.edu.nus.iss.phoenix.user.android.delegate.DeleteUserDelegate;
import sg.edu.nus.iss.phoenix.user.android.delegate.RetrieveUsersDelegate;
import sg.edu.nus.iss.phoenix.user.android.delegate.UpdateUserDelegate;
import sg.edu.nus.iss.phoenix.user.android.ui.MaintainUserList;
import sg.edu.nus.iss.phoenix.user.android.ui.MaintainUserScreen;
import sg.edu.nus.iss.phoenix.user.entity.User;


/**
 * @author sujit  ambore
 * @version 1.0
 */

public class UserController {
    private static final String TAG = UserController.class.getName();

    private MaintainUserList maintainUserList;
    private MaintainUserScreen maintainUserScreen;
    private User us2edit = null;

    public void startUseCase() {
        us2edit = null;
        Intent intent = new Intent(MainController.getApp(), MaintainUserScreen.class);
        MainController.displayScreen(intent);
    }

    public void onDisplayUserList(MaintainUserList maintainUserList) {
        this.maintainUserList = maintainUserList;
        new  RetrieveUsersDelegate(this).execute("all");
    }

    public void userRetrieved(List<User> users) {
        maintainUserScreen.showUsers(users);
    }

    public void selectCreateUser(User rp) {
        us2edit = null;
        new CreateUserDelegate(this).execute(rp);
        Intent intent = new Intent(MainController.getApp(), MaintainUserScreen.class);
        MainController.displayScreen(intent);

    }

    public void selectEditUser(User users) {
        us2edit = users;
        Log.v(TAG, "Editing user name: " + users.getUserName() + "...");

        Intent intent = new Intent(MainController.getApp(), MaintainUserScreen.class);
        MainController.displayScreen(intent);
    }

    public void onDisplayUser(MaintainUserScreen maintainUserScreen) {
        this.maintainUserScreen = maintainUserScreen;
        if (us2edit == null)
            maintainUserScreen.createUser();
        else
            maintainUserScreen.editUser(us2edit);
    }

    public void selectUpdateUser(User us) {
        new UpdateUserDelegate(this).execute(us);
    }

    public void selectDeleteUser(User us) {
        new DeleteUserDelegate(this).execute(us.getUserName());
    }

    public void userDeleted(boolean success) {
        // Go back to UserList screen with refreshed users.
        startUseCase();
    }

    public void userUpdated(boolean success) {
        // Go back to UserList screen with refreshed users.
        startUseCase();
    }

    public void selectCreateUsers(User us) {
        new CreateUserDelegate(this).execute(us);
    }

    public void userCreated(boolean success) {
        // Go back to UserList screen with refreshed users.
        startUseCase();
    }

    public void selectCancelCreateEditUser() {
        // Go back to UserList screen with refreshed users.
        startUseCase();
    }

    public void maintainedUser() {
        ControlFactory.getMainController().maintainedUser();
    }

}
