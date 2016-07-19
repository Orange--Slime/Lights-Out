package main;

import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Board extends Pane {
	private int height;
	private Block[][] puzzlePieces;
	private int width;
	
	public Board(){
		super();
		width = 7;
		height = 7;
		this.getChildren().add(createBoard());
	}
	
	public Board(int x, int y){
		super();
		width = x;
		height = y;
		this.getChildren().add(createBoard());
	}
	
	public void chaseTheLightsDown(){
		for(int i = 1; i < height; i++){
			for(int k = 0; k < width; k++){
				if(!puzzlePieces[i-1][k].getActive())
					puzzlePieces[i][k].onClick(puzzlePieces);
			}
		}
	}
	
	public void chaseTheLightsUp(){
		for(int i = width-2; i >= 0; i--){
			for(int k = 0; k < width; k++){
				if(!puzzlePieces[i+1][k].getActive())
					puzzlePieces[i][k].onClick(puzzlePieces);
			}
		}
	}
	
	public void chaseTheLightsRight(){
		for(int i = 1; i < height; i++){
			for(int k = 0; k < width; k++){
				if(!puzzlePieces[k][i-1].getActive())
					puzzlePieces[k][i].onClick(puzzlePieces);
			}
		}
	}
	
	public void chaseTheLightsLeft(){
		for(int i = width-2; i >= 0; i--){
			for(int k = 0; k < width; k++){
				if(!puzzlePieces[k][i+1].getActive())
					puzzlePieces[k][i].onClick(puzzlePieces);
			}
		}
	}
	
	private VBox createBoard(){
		puzzlePieces = new Block[width][height];
		VBox blocks = new VBox(1);
		for(int x = 0; x < width; x++){
			for(int k = 0; k < height; k++){
				puzzlePieces[x][k] = new Block(Math.random() < .5, x, k);
				Block temp = puzzlePieces[x][k];
				puzzlePieces[x][k].setOnAction(e -> {temp.onClick(puzzlePieces);});
			}
			HBox group = new HBox(1);
			group.getChildren().addAll(puzzlePieces[x]);
			blocks.getChildren().add(group);
		}
		blocks.setBackground(new Background(new BackgroundFill(new Color(0d,0d,0d,1d), new CornerRadii(0), new Insets(0))));
		return blocks;
	}
	
	public void createBoard(boolean[][] bools){
		for(int x = 0; x < width; x++){
			for(int k = 0; k < height; k++){
				if(puzzlePieces[x][k].getActive() != bools[x][k])
					puzzlePieces[x][k].changeState();
				else
					puzzlePieces[x][k].changeColor();
			}
		}
	}

	public boolean[][] getBoolArray(){
		boolean[][] temp = new boolean[width][height];
		for(int i = 0; i < width; i++){
			for(int k = 0; k < height; k++){
				temp[i][k] = puzzlePieces[i][k].getActive();
			}
		}
		return temp;
	}
	
	public Block[][] getPuzzlePieces() {
		return puzzlePieces;
	}
	
	public void load() {
		try {
			width = (int) readField("width");
			height = (int) readField("height");
			createBoard((boolean[][]) readField("puzzlePieces"));
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		refresh();
	}
	
	public void randomize(){
		int numWhite = 0;
		int numGray = 0;
		for(int x = 0; x < width; x++){
			for(int k = 0; k < height; k++){
				if((Math.random() < .5 && numWhite < width*height) || numGray > width*height){
						puzzlePieces[x][k].changeState(); 
						numWhite++;
				}
				else{
					puzzlePieces[x][k].changeColor();
					numGray++;
				}
			}
		}
	}
	
	private Object readField(String fieldName) throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream(new File("saveData/LightsOut-" + fieldName + ".dat"));
		ObjectInputStream ois = new ObjectInputStream(fis);
		Object value = ois.readObject();
		ois.close();
		return value;
	}
	
	private void refresh(){
		for(int x = 0; x < width; x++){
			for(int k = 0; k < height; k++){
				puzzlePieces[x][k].changeColor();
			}
		}
	}
	
	public void resize(int x, int y){
		
	}
	
	public void save() {
		try {
			saveField("width", width);
			saveField("height", height);
			saveField("puzzlePieces", getBoolArray());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void saveField(String fieldName, Object fieldValue) throws IOException {
		FileOutputStream fos = new FileOutputStream(new File("saveData\\LightsOut-" + fieldName + ".dat"));
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(fieldValue);
		oos.close();
	}
	
	@Override
	public String toString(){
		String temp = "";
		for(Block[] blocks: puzzlePieces){
			temp += "[";
			for(Block block: blocks){
				temp += block + ", ";
			}
			temp += "]\n";
		}
		return temp;
	}
	
//	public String getRow(int k){
//		String temp = "";
//		for(int i = 0; i < height; i++){
//			temp += puzzlePieces[k][i]+ " ";
//		}
//		return temp;
//	}
//	
//	public void solveGame() throws InterruptedException{
//		lightsOut();
//		try {
//			File file = new File("DropTable.txt");
//
//			if (!file.exists()) {
//				file.createNewFile();
//			}
//
//			FileWriter fw = new FileWriter(file.getAbsoluteFile());
//			BufferedWriter bw = new BufferedWriter(fw);
//			for(int a = 0; a <= 1; a++){
//				for(int b = 0; b <= 1; b++){
//					for(int c = 0; c <= 1; c++){
//						for(int d = 0; d <= 1; d++){
//							for(int e = 0; e <= 1; e++){
//								for(int f = 0; f <= 1; f++){
//									for(int g = 0; g <= 1; g++){
//										if(a == 1)
//											puzzlePieces[0][0].onClick(puzzlePieces);
//										if(b == 1)
//											puzzlePieces[0][1].onClick(puzzlePieces);
//										if(c == 1)
//											puzzlePieces[0][2].onClick(puzzlePieces);
//										if(d == 1)
//											puzzlePieces[0][3].onClick(puzzlePieces);
//										if(e == 1)
//											puzzlePieces[0][4].onClick(puzzlePieces);
//										if(f == 1)
//											puzzlePieces[0][5].onClick(puzzlePieces);
//										if(g == 1)
//											puzzlePieces[0][6].onClick(puzzlePieces);
//										bw.write(a + " " + b + " " + c + " " + d + " " + e + " " + f + " " + g + " | ");
//										chaseTheLightsDown();
//										bw.write(getRow(puzzlePieces.length-1)+"\n");
//										lightsOut();
//									}
//								}
//							}
//						}
//					}
//				}
//			}
//			bw.close();
//
//			System.out.println("Done");
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public void lightsOut(){
//		for(int i = 0; i < width; i++){
//			for(int k = 0; k < height; k++){
//				if(!puzzlePieces[i][k].getActive())
//					puzzlePieces[i][k].changeState();
//			}
//		}
//	}
}
