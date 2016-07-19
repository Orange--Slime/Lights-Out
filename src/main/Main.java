package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
	Board board = new Board();
	boolean gameOver = false;
	Stage mainStage;
	
	public static void main(String[] args){
		launch(args);
	}
	
	public boolean isGameOver(){
		for(Block[] blocks: board.getPuzzlePieces()){
			for(Block block: blocks){
				if(!block.getActive())
					return false;
			}
		}
		return true;
	}

	public void start(Stage arg0) throws Exception {
		mainStage = arg0;
		mainStage.setTitle("Lights Out!");
		Scene scene = new Scene(new VBox(new BoardMenuBar(board, mainStage), board));
		mainStage.setScene(scene);
		mainStage.show();
	}
}
