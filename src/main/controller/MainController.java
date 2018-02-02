package main.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import main.logic.GameLoop;
import main.model.GameModel;
import main.util.CellBoard;
import main.util.SizeListener;

public class MainController implements Initializable {

	@FXML
	private BorderPane gamePane;
	@FXML
	private Button runButton;
	@FXML
	private Button nextButton;
	@FXML
	private Button pauseButton;
	@FXML
	private Button clearButton;
	@FXML
	private Button exitButton;
	@FXML
	private Button sampleButton;
	@FXML
	private Button saveButton;
	@FXML
	private Button loadButton;
	@FXML
	private Button gliderGunButton;
	@FXML
	private TextField widthField;
	@FXML
	private TextField heightField;

	private CellBoard cellBoard;
	private GameLoop loop;
	private int count = 0;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		loop = new GameLoop();
		cellBoard = new CellBoard();
		gamePane.setCenter(cellBoard);
		cellBoard.widthProperty().addListener(new SizeListener(cellBoard));
		cellBoard.heightProperty().addListener(new SizeListener(cellBoard));
		runButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				loop.runGame();
			}
		});
		pauseButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				loop.pauseGame();
			}
		});
		clearButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				loop.pauseGame();
				GameModel.getInstance().initBoard();
			}
		});
		exitButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				System.exit(0);
			}
		});
		widthField.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				GameModel.getInstance().setCellCountWidth(Integer.parseInt(widthField.getText()));
			}
		});
		heightField.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				GameModel.getInstance().setCellCountHeight(Integer.parseInt(heightField.getText()));
			}
		});
		nextButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				loop.nextGeneration();
			}
		});
		sampleButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				load("basePatterns.txt");
			}
		});
		gliderGunButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				load("gliderGun.txt");
			}
		});
		saveButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				PrintWriter writer;
				try {
					writer = new PrintWriter("file.txt", "UTF-8");
					writer.println(GameModel.getInstance().getCellCountWidth() + ":"
							+ GameModel.getInstance().getCellCountHeight());
					boolean[][] board = GameModel.getInstance().getBoardState();
					for (int i = 0; i < GameModel.getInstance().getCellCountHeight(); i++) {
						for (int j = 0; j < GameModel.getInstance().getCellCountWidth(); j++) {
							writer.print(board[i][j] + " ");
						}
						writer.print("\n");
					}
					writer.close();
				} catch (FileNotFoundException | UnsupportedEncodingException e) {
					e.printStackTrace();
				}

			}
		});
		loadButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				BufferedReader reader;
				try {
					reader = new BufferedReader(new FileReader("file.txt"));
					String line = reader.readLine();
					String[] size = line.split(":");
					int width = Integer.parseInt(size[0]);
					int height = Integer.parseInt(size[1]);
					boolean[][] board = new boolean[height][width];
					int row = 0;
					while ((line = reader.readLine()) != null) {
						String[] cols = line.split(" ");
						int col = 0;
						for (String c : cols) {
							if (Boolean.getBoolean(c)) {
								System.out.println("true");
							}
							board[row][col] = Boolean.parseBoolean(c);
							col++;
						}
						row++;
					}
					reader.close();
					GameModel.getInstance().setCellCountHeight(height);
					GameModel.getInstance().setCellCountWidth(width);
					GameModel.getInstance().setBoardState(board);
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		});
	}

	private void load(String fileName) {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(fileName));
			String line = reader.readLine();
			String[] size = line.split(":");
			int width = Integer.parseInt(size[0]);
			int height = Integer.parseInt(size[1]);
			boolean[][] board = new boolean[height][width];
			int row = 0;
			while ((line = reader.readLine()) != null) {
				String[] cols = line.split(" ");
				int col = 0;
				for (String c : cols) {
					if (Boolean.getBoolean(c)) {
						System.out.println("true");
					}
					board[row][col] = Boolean.parseBoolean(c);
					col++;
				}
				row++;
			}
			reader.close();
			GameModel.getInstance().setCellCountHeight(height);
			GameModel.getInstance().setCellCountWidth(width);
			GameModel.getInstance().setBoardState(board);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
