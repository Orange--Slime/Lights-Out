package main;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class Block extends Button{
	private boolean active;
	private final Color[] COLORS = {new Color(128d/255,128d/255,128d/255,255d/255), new Color(255d/255,255d/255,255d/255,255d/255), new Color(0d/255,128d/255,255d/255,255d/255), new Color(255d/255,128d/255,0d/255,255d/255)};
	private int[] coords = new int[2];
	private int[] dimensions = {50,50};

	public Block(){
		active = false;
		coords = null;
	}
	
	public Block(boolean bool){
		super();
		active = bool;
		coords = null;
		this.setMinHeight(dimensions[1]);
		this.setMinWidth(dimensions[0]);
		this.setBackground(new Background(new BackgroundFill(COLORS[(active)? 0: 1], new CornerRadii(0), new Insets(0))));
	}
	
	public Block(boolean bool, int x){
		super();
		active = bool;
		coords[0] = x;
		this.setMinHeight(dimensions[1]);
		this.setMinWidth(dimensions[0]);
		this.setBackground(new Background(new BackgroundFill(COLORS[(active)? 0: 1], new CornerRadii(0), new Insets(0))));
	}
	
	public Block(boolean bool, int x, int y){
		super();
		active = bool;
		coords[0] = x;
		coords[1] = y;
		this.setMinHeight(dimensions[1]);
		this.setMinWidth(dimensions[0]);
		this.setBackground(new Background(new BackgroundFill(COLORS[(active)? 0: 1], new CornerRadii(0), new Insets(0))));
	}
	
	public Block(boolean bool, int[] array){
		super();
		active = bool;
		coords = array.clone();
		this.setMinHeight(dimensions[1]);
		this.setMinWidth(dimensions[0]);
		this.setBackground(new Background(new BackgroundFill(COLORS[(active)? 0: 1], new CornerRadii(0), new Insets(0))));
	}
	
	public Block(int x){
		super();
		active = false;
		coords[0] = x;
		this.setMinHeight(dimensions[1]);
		this.setMinWidth(dimensions[0]);
		this.setBackground(new Background(new BackgroundFill(COLORS[(active)? 0: 1], new CornerRadii(0), new Insets(0))));
	}
	
	public Block(int x, int y){
		super();
		active = false;
		coords[0] = x;
		coords[1] = y;
		this.setMinHeight(dimensions[1]);
		this.setMinWidth(dimensions[0]);
		this.setBackground(new Background(new BackgroundFill(COLORS[(active)? 0: 1], new CornerRadii(0), new Insets(0))));
	}
	
	public Block(int[] array){
		super();
		active = false;
		coords = array.clone();
		this.setMinHeight(dimensions[1]);
		this.setMinWidth(dimensions[0]);
		this.setBackground(new Background(new BackgroundFill(COLORS[(active)? 0: 1], new CornerRadii(0), new Insets(0))));
	}

	public void changeColor(){
		this.setBackground(new Background(new BackgroundFill(COLORS[(active)? 0: 1], new CornerRadii(0), new Insets(0))));
	}
	
	public void changeState(){
		active = !active;
		changeColor();
	}
	
	public boolean getActive(){
		return active;
	}
	
	public int[] getCoords(){
		return coords;
	}
	
	public int getX(){
		return coords[0];
	}
	
	public int getY(){
		return coords[1];
	}
	
	public void onClick(Block[][] puzzlePieces){
		if(coords[0]-1 >= 0)
			puzzlePieces[coords[0]-1][coords[1]].changeState();
		if(coords[1]-1 >= 0)
			puzzlePieces[coords[0]][coords[1]-1].changeState();
		if(coords[0]+1 < puzzlePieces.length)
			puzzlePieces[coords[0]+1][coords[1]].changeState();
		if(coords[1]+1 < puzzlePieces.length)
			puzzlePieces[coords[0]][coords[1]+1].changeState();
		changeState();
	}
	
	public String toString(){
		return (active)? 0+"" : 1+"";
	}
}
