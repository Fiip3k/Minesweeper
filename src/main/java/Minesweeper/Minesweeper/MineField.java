package Minesweeper.Minesweeper;

import java.awt.GridLayout;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MineField extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int x;
	private int y;
	private int bombs;
	private double mineMultiplier = 0.15;
	private Tile tiles[][];
	private GridLayout layout;
	private int layoutGap = 2;
	
	public MineField(int x, int y){
		this.x = x;
		this.y = y;
		tiles = new Tile[x][y];
		layout = new GridLayout(x,y);
		layout.setHgap(layoutGap);
		layout.setVgap(layoutGap);
		this.setLayout(layout);
		
		setField();
		setMines();
	}
	
	private void setMines(){
		int mapSize = x*y;
		bombs = (int) (mapSize * mineMultiplier);
		bombs = 2;
		Random r = new Random();
		for(int i = 0; i < bombs; i++){
			int mX;
			int mY;
			do{
				mX = r.nextInt()%x;
				if(mX < 0) mX *= -1;
				
				mY = r.nextInt()%y;;
				if(mY < 0) mY *= -1;
			}while(tiles[mX][mY].isMine());
			tiles[mX][mY].setMine();
			
			informSurroundingsAboutBombs(mX, mY);
		}
	}
	
	private void informSurroundingsAboutBombs(int mX, int mY) {
		for(int i = -1; i < 2; i++){
			for(int j = -1; j < 2; j++){
				int tempX = mX+i;
				int tempY = mY+j;
				if(tempX >= 0 && tempX < x){
					if(tempY >= 0 && tempY < y){
						if(tempX == mX && tempY == mY){
							continue;
						}
							tiles[tempX][tempY].addSurroundings();;
					}
				}
			}
		}		
	}
	
	public void informSurroundingsAboutBlank(int mX, int mY) {
		for(int i = -1; i < 2; i++){
			for(int j = -1; j < 2; j++){
				int tempX = mX+i;
				int tempY = mY+j;
				if(tempX >= 0 && tempX < x){
					if(tempY >= 0 && tempY < y){
						if(tempX == mX && tempY == mY){
							continue;
						}
							tiles[tempX][tempY].check();
					}
				}
			}
		}		
	}

	private void setField(){
		for(int i = 0; i < x; i++){
			for(int j = 0; j < y; j++){
				tiles[i][j] = new Tile(i, j, this);
				this.add(tiles[i][j]);
			}
		}
	}

	public void checkVictory(){
		int successCount = 0;
		for(int i = 0; i < x; i++){
			for(int j = 0; j < y; j++){
				if(tiles[i][j].isMine() && tiles[i][j].isMarked()){
					successCount++;
				}
				if(!tiles[i][j].isMine() && tiles[i][j].isMarked()){
					successCount--;
				}
			}
		}
		if(successCount == bombs){
			if(JOptionPane.showConfirmDialog(this, "You won. Start again?") == 0){
				App.newGame();
			}
		}
	}
	
	public void endGame() {
		for(int i = 0; i < x; i++){
			for(int j = 0; j < y; j++){
				tiles[i][j].removeMouseListener(tiles[i][j].getMouseListeners()[0]);
			}
		}

		if(JOptionPane.showConfirmDialog(this, "Game over. Start again?") == 0){
			App.newGame();	
		}
	}

}


