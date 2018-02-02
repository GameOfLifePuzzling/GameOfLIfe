package main.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javafx.application.Platform;
import javafx.scene.layout.Pane;
import main.model.GameModel;

public class CellBoard extends Pane implements Board, Observer {

	private List<Cell> cells = new ArrayList<Cell>();


	public CellBoard() {
		this.setStyle("-fx-background-color: DARKGREY;");
		GameModel.getInstance().addObserver(this);
		init();
	}

	private void init() {
		cells.clear();
		this.getChildren().clear();
		double margin = 1;
		double cellWidth = (this.getWidth() / GameModel.getInstance().getCellCountWidth()) - margin;
		double cellHeight = (this.getHeight() / GameModel.getInstance().getCellCountHeight()) - margin;
		for (int i = 0; i < GameModel.getInstance().getCellCountHeight(); i++) {
			for (int j = 0; j < GameModel.getInstance().getCellCountWidth(); j++) {
				double posX = cellWidth * j + margin * j;
				double posY = cellHeight * i + margin * i;
				Cell cell = new Cell(posX, posY, cellWidth, cellHeight, i, j);
				cell.setAlive(false);
				cells.add(cell);
			}
		}
		for (Cell cell : cells) {
			this.getChildren().add(cell);
		}
	}

	@Override
	public void resize() {
		for (Cell cell : cells) {
			double margin = 1;
			double cellWidth = (this.getWidth() / GameModel.getInstance().getCellCountWidth()) - margin;
			double cellHeight = (this.getHeight() / GameModel.getInstance().getCellCountHeight()) - margin;
			double posX = cellWidth * cell.getPosJ() + margin * cell.getPosJ();
			double posY = cellHeight * cell.getPosI() + margin * cell.getPosI();
			cell.setWidth(cellWidth);
			cell.setHeight(cellHeight);
			cell.setX(posX);
			cell.setY(posY);
		}
	}
	
	public void applyUpdate(boolean[][] boardState) {
		for (Cell cell : cells) {
			cell.setAlive(boardState[cell.getPosI()][cell.getPosJ()]);
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		Platform.runLater(new Runnable() {
			public void run() {
				init();
				applyUpdate(GameModel.getInstance().getBoardState());
			}
		});
	}

}
