package sg.edu.nus.iss.phoenix.schedule.android.controller;

import android.content.Intent;
import android.util.Log;

import java.util.List;


import sg.edu.nus.iss.phoenix.core.android.controller.MainController;
import sg.edu.nus.iss.phoenix.schedule.android.delegate.CreateScheduleDelegate;
import sg.edu.nus.iss.phoenix.schedule.android.delegate.RetrieveSchedulesDelegate;
import sg.edu.nus.iss.phoenix.schedule.android.ui.MaintainScheduleScreen;
import sg.edu.nus.iss.phoenix.schedule.android.ui.ScheduleListScreen;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;

public class ScheduleController {
	private static final String TAG = ScheduleController.class.getName();

	private ScheduleListScreen scheduleListScreen;
	private MaintainScheduleScreen maintainScheduleScreen;

	private ProgramSlot ps2edit = null;
	public void startUseCase() {
		ps2edit = null;
		Intent intent = new Intent(MainController.getApp(), ScheduleListScreen.class);
		MainController.displayScreen(intent);

	}

	public void onDisplayScheduleList(ScheduleListScreen scheduleListScreen) {
		this.scheduleListScreen = scheduleListScreen;
		new RetrieveSchedulesDelegate(this).execute("all");
	}

	public void scheduleRetrieved(List<ProgramSlot> programSlots) {
		scheduleListScreen.showSchedulePrograms(programSlots);
	}



	public void selectCreateSchedule() {
		ps2edit = null;
		Intent intent = new Intent(MainController.getApp(), MaintainScheduleScreen.class);
		MainController.displayScreen(intent);

	}

	public void selectCreateSchedule(ProgramSlot programSlot) {
		new CreateScheduleDelegate(this).execute(programSlot);
	}
	public void onDisplaySchedule(MaintainScheduleScreen maintainScheduleScreen) {
		this.maintainScheduleScreen = maintainScheduleScreen;
		if (ps2edit == null)
			maintainScheduleScreen.createSchedule();
		else
			maintainScheduleScreen.editSchedule(ps2edit);
	}

	public void scheduleCreated(boolean success) {
		// Go back to ProgramList screen with refreshed programs.
		startUseCase();
	}
}
