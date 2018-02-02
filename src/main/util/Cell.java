package main.util;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import main.model.GameModel;

public class Cell extends Rectangle {
	
	private boolean alive;
	private int posI;
	private int posJ;

	public Cell(double posX, double posY, double width, double height, int posI, int posJ) {
		super(posX, posY, width, height);
		this.posI = posI;
		this.posJ = posJ;
		this.setOnMouseMoved(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.isControlDown()) {
					setAlive(true);
					GameModel.getInstance().getBoardState()[posI][posJ] = true;
				} else if (event.isAltDown()) {
					setAlive(false);
					GameModel.getInstance().getBoardState()[posI][posJ] = false;
				}
			}
			
		});
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
		this.setFill(alive ? Color.BLACK : Color.WHITE);
	}

	public int getPosI() {
		return posI;
	}

	public void setPosI(int posI) {
		this.posI = posI;
	}

	public int getPosJ() {
		return posJ;
	}

	public void setPosJ(int posJ) {
		this.posJ = posJ;
	}
	
	
}
