package Minesweeper.Minesweeper;

import java.awt.GridLayout;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Klasa reprezentująca pole minowe. Tworzy pojedyncze pola, a następnie zaminowuje losowe z nich.
 */
public class MineField extends JPanel{

	private static final long serialVersionUID = 1L;
	private int x;
	private int y;
	private int bombs;
	private double mineMultiplier = 0.15;
	private Tile tiles[][];
	private GridLayout layout;
	private int layoutGap = 2;
	private boolean won = false;
	
	/**
	 * Konstruktor tworzący nowe pole minowe.
	 * @param x Ilość pól w wymiarze X.
	 * @param y Ilość pól w wymiarze Y.
	 */
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
	
	/**
	 * Metoda rozstawiająca miny na polu.
	 */
	private void setMines(){
		int mapSize = x*y;
		bombs = (int) (mapSize * mineMultiplier);
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
	
	/**
	 * Metoda informująca pola o pobliskiej minie.
	 * @param mX Pozycja X miny.
	 * @param mY Pozycja Y miny.
	 */
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
	
	
	/**
	 * Metoda informująca pola o możliwości odkrycia się.
	 * @param mX Pozycja X pola informującego.
	 * @param mY Pozycja Y pola informującego.
	 */
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

	
	/**
	 * Metoda tworząca puste pole minowe.
	 */
	private void setField(){
		for(int i = 0; i < x; i++){
			for(int j = 0; j < y; j++){
				tiles[i][j] = new Tile(i, j, this);
				this.add(tiles[i][j]);
			}
		}
	}
	
	/**
	 * Metoda sprawdzająca warunek zwycięstwa.
	 */
	public void checkVictory(){
		if(!won){
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
				won = true;
				if(JOptionPane.showConfirmDialog(this, "You won. Start again?") == 0){
					App.newGame();
				}
			}
		}
		
	}
	
	/**
	 * Metoda kończąca grę porażką.
	 */
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


