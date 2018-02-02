package main.logic;

import java.util.Timer;
import java.util.TimerTask;

import main.model.GameModel;

public class GameLoop {

	private int STEP_TIME = 300;
	private Timer timer;
	private TimerTask task;
	
	public GameLoop() {
		this.timer = new Timer();
	}
	
	public void runGame() {
		this.task = new TimerTask() {
			@Override
			public void run() {
				GameModel.getInstance().updateBoardState();
			}
		};
		timer.schedule(task, STEP_TIME, STEP_TIME);
	}
	
	public void pauseGame() {
		if (task != null) {
			task.cancel();
		}
	}
	
	public void nextGeneration() {
		GameModel.getInstance().updateBoardState();
	}
}
