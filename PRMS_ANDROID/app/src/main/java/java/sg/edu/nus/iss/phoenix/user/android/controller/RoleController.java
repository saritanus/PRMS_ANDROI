package sg.edu.nus.iss.phoenix.user.android.controller;

import java.util.List;

import sg.edu.nus.iss.phoenix.radioprogram.android.ui.ReviewSelectProgramScreen;
import sg.edu.nus.iss.phoenix.user.entity.User;

public class RoleController {

    // Tag for logging.
    private static final String TAG = RoleController.class.getName();

    private ReviewSelectProgramScreen reviewSelectProgramScreen;
    private User rpSelected = null;

    public void rolesRetrieved(List<User> users) {
    }

    public RoleController() {
    }
}
