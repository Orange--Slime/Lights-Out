package main;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

public class BoardMenuBar extends MenuBar {
	private Board board;
	private Menu file;
	private Menu help;
	private Menu view;
	Stage window;

	public BoardMenuBar() {
		super();
		createMenuBar();
		super.getMenus().addAll(file, view, help);
	}
	
	public BoardMenuBar(Board board) {
		super();
		createMenuBar();
		super.getMenus().addAll(file, view, help);
		this.board = board;
	}
	
	public BoardMenuBar(Board board, Stage stage) {
		super();
		createMenuBar();
		super.getMenus().addAll(file, view, help);
		this.board = board;
		window = stage;
	}
	
	private Menu createDebug() {
		Menu temp = new Menu("Debug");
		
		MenuItem allVariables = new MenuItem("All Variables");
		allVariables.setOnAction(e -> AlertBox.display("All Variables", board+""));
		
		temp.getItems().addAll(allVariables);
		return temp;
	}

	private Menu createFile() {
		Menu temp = new Menu("File");
		
		MenuItem save = new MenuItem("Save");
		save.setOnAction(e -> board.save());
		save.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));
		
		MenuItem load = new MenuItem("Load");
		load.setOnAction(e -> board.load());
		load.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN));
		
		MenuItem quickClose = new MenuItem("Quick Close");
		quickClose.setOnAction(e -> window.close());
		quickClose.setAccelerator(new KeyCodeCombination(KeyCode.W, KeyCombination.CONTROL_DOWN));
		quickClose.setVisible(false);
		
		MenuItem chaseDown = new MenuItem("Chase Down");
		chaseDown.setOnAction(e -> board.chaseTheLightsDown());
		chaseDown.setAccelerator(new KeyCodeCombination(KeyCode.DOWN, KeyCombination.CONTROL_DOWN));
		chaseDown.setVisible(false);
		
		MenuItem chaseUp = new MenuItem("Chase Up");
		chaseUp.setOnAction(e -> board.chaseTheLightsUp());
		chaseUp.setAccelerator(new KeyCodeCombination(KeyCode.UP, KeyCombination.CONTROL_DOWN));
		chaseUp.setVisible(false);
		
		MenuItem chaseRight = new MenuItem("Chase Down");
		chaseRight.setOnAction(e -> board.chaseTheLightsRight());
		chaseRight.setAccelerator(new KeyCodeCombination(KeyCode.RIGHT, KeyCombination.CONTROL_DOWN));
		chaseRight.setVisible(false);
		
		MenuItem chaseLeft = new MenuItem("Chase Up");
		chaseLeft.setOnAction(e -> board.chaseTheLightsLeft());
		chaseLeft.setAccelerator(new KeyCodeCombination(KeyCode.LEFT, KeyCombination.CONTROL_DOWN));
		chaseLeft.setVisible(false);
		
		temp.getItems().addAll(save, load, quickClose, chaseDown, chaseUp, chaseRight, chaseLeft);
		return temp;
	}
	
	private Menu createHelp() {
		Menu temp = new Menu("Help");
		
		MenuItem helpBox = new MenuItem("Help");
		helpBox.setOnAction(e -> AlertBox.display("Help", "This is \"Lights Out!\" A game where the your goal is to turn all the lights off.\n Clicking any box will change both that box and the ones surrounding it to the opposite state."));
		
		Menu debug = createDebug();
		
		
		temp.getItems().addAll(helpBox, debug);
		return temp;
	}

	private void createMenuBar() {
		file = createFile();
		view = createView();
		help = createHelp();
	}
	
	private Menu createSizes(){
		Menu size = new Menu("Sizes");
		
		MenuItem threexthree = new MenuItem("3x3");
		threexthree.setOnAction(e -> board = new Board(3,3));
		
		MenuItem threexfour = new MenuItem("3x4");
		threexthree.setOnAction(e -> board = new Board(3,3));
		
		MenuItem fourxfour = new MenuItem("4x4");
		threexthree.setOnAction(e -> board = new Board(3,3));

		MenuItem fourxfive = new MenuItem("4x5");
		threexthree.setOnAction(e -> board = new Board(3,3));

		MenuItem fivexfive = new MenuItem("5x5");
		threexthree.setOnAction(e -> board = new Board(3,3));

		MenuItem fivexsix = new MenuItem("5x6");
		threexthree.setOnAction(e -> board = new Board(3,3));

		MenuItem sixxsix = new MenuItem("6x6");
		threexthree.setOnAction(e -> board = new Board(3,3));
		
		MenuItem sixxseven = new MenuItem("6x7");
		threexthree.setOnAction(e -> board = new Board(3,3));

		MenuItem sevenxseven = new MenuItem("7x7");
		threexthree.setOnAction(e -> board = new Board(3,3));
		
		size.getItems().addAll(threexthree, threexfour, fourxfour, fourxfive, fivexfive, fivexsix, sixxsix, sixxseven, sevenxseven);
		return size;
	}
	
	private Menu createView() {
		Menu temp = new Menu("View");

		MenuItem reset = new MenuItem("Reset");
		reset.setOnAction(e -> board.randomize());
		reset.setAccelerator(new KeyCodeCombination(KeyCode.R, KeyCombination.CONTROL_DOWN));
		
		MenuItem fullScreen = new MenuItem("Fullscreen");
		fullScreen.setOnAction(e -> window.setFullScreen(!window.isFullScreen()));
		fullScreen.setAccelerator(new KeyCodeCombination(KeyCode.F11));
		fullScreen.setVisible(false);
		
		//Menu sizes = createSizes();
		
		temp.getItems().addAll(reset, fullScreen);
		return temp;
	}
}
