package main.logic;

import main.model.GameModel;

public class GameLogic {

	public static boolean[][] nextBoardState(boolean[][] prevBoardState) {
		int width = GameModel.getInstance().getCellCountWidth();
		int height = GameModel.getInstance().getCellCountHeight();
		boolean[][] nextBoardState = new boolean[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				int neighbours = countNeighbours(prevBoardState, i, j);
				if (neighbours < 2) {
					nextBoardState[i][j] = false;
				} else if ((neighbours == 3 || neighbours == 2) && prevBoardState[i][j] == true) {
					nextBoardState[i][j] = true;
				} else if (neighbours == 3 && prevBoardState[i][j] == false) {
					nextBoardState[i][j] = true;
				} else if (neighbours > 3) {
					nextBoardState[i][j] = false;
				} else {
					nextBoardState[i][j] = false;
				}
			}
		}
		return nextBoardState;
	}
	
	private static int countNeighbours(boolean[][] boardState, int i, int j) {
		int count = 0;
		int defAdjI = -1;
		int defAdjJ = -1;
		int defLimI = 1;
		int defLimJ = 1;
		if (i < 1) {
			defAdjI = 0;
		}
		if (j < 1) {
			defAdjJ = 0;
		}
		if (i >= GameModel.getInstance().getCellCountHeight() - 1) {
			defLimI = 0;
		}
		if (j >= GameModel.getInstance().getCellCountWidth() - 1) {
			defLimJ = 0;
		}
		for (int adjI = defAdjI; adjI <= defLimI; adjI++) {
			for (int adjJ = defAdjJ; adjJ <= defLimJ; adjJ++) {
				if (boardState[i + adjI][j + adjJ] == true) {
					count++;
				}
			}
		}
		if (boardState[i][j] == true) {
			count--;
		}
		return count;
	}
}
