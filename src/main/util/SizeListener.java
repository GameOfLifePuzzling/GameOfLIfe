package main.util;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class SizeListener implements ChangeListener<Number>{
	
	private Board board;
	
	public SizeListener(Board board) {
		this.board = board;
	}

	@Override
	public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
		board.resize();
	}

}
