package main.model;

import java.util.Observable;

import main.logic.GameLogic;

public class GameModel extends Observable{

	private int cellCountWidth = 100;
	private int cellCountHeight = 100;
	private boolean[][] boardState = new boolean[cellCountWidth][cellCountHeight];

	private static GameModel instance;

	private GameModel() {initBoard();}

	public static synchronized GameModel getInstance() {
		if (GameModel.instance == null) {
			GameModel.instance = new GameModel();
		}
		return GameModel.instance;
	}
	
	public void initBoard() {
		boardState= new boolean[cellCountHeight][cellCountWidth];
		for (int i = 0; i < cellCountHeight; i++) {
			for (int j = 0; j < cellCountWidth; j++) {
				boardState[i][j] = false;
			}
		}
		setChanged();
		notifyObservers();
	}
	
	public void updateBoardState() {
		boardState = GameLogic.nextBoardState(boardState);
		setChanged();
		notifyObservers();
	}

	public boolean[][] getBoardState() {
		return boardState;
	}

	public void setBoardState(boolean[][] boardState) {
		this.boardState = boardState;
		setChanged();
		notifyObservers();
	}

	public int getCellCountWidth() {
		return cellCountWidth;
	}

	public void setCellCountWidth(int cellCountWidth) {
		this.cellCountWidth = cellCountWidth;
		initBoard();
	}

	public int getCellCountHeight() {
		return cellCountHeight;
	}

	public void setCellCountHeight(int cellCountHeight) {
		this.cellCountHeight = cellCountHeight;
		initBoard();
	}
	
	

}
