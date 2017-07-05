package Minesweeper.Minesweeper;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;

/**
 * Klasa reprezentująca jedno pole - zaminowane lub nie.
 */
public class Tile extends JComponent {
	private MineField mf;
	private static final long serialVersionUID = 1L;
	private int x,y;
	private int tileSize = 50;
	private int surroundings = 0;
	private boolean isSelected = false;
	private boolean isMine = false;
	private boolean isMarked = false;
	private boolean checked = false;

	/**
	 * Konstruktor tworzący pole.
	 * @param x Miejsce w wymiarze X tabeli pola minowego.
	 * @param y Miejsce w wymiarze Y tabeli pola minowego.
	 * @param mf Referencja pola minowego, na którym leży nasze pojedyncze pole.
	 */
	public Tile(int x, int y, MineField mf) {
		this.mf = mf;
		this.x = x;
		this.y = y;
		
		addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {


			}

			@Override
			public void mouseReleased(MouseEvent e) {
					if(SwingUtilities.isRightMouseButton(e)){
						mark();
					}else if(SwingUtilities.isLeftMouseButton(e)){
						check();
					}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				select();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				select();
			}

		});
	}
	
	
	/**
	 * Metoda oznaczająca dane pole
	 */
	private void mark() {
		if (!isMarked)
			isMarked = true;
		else
			isMarked = false;
		
		repaint();	
		mf.checkVictory();
	}

	/**
	 * Metoda podświetlająca pole, na którym znajduje się kursor
	 */
	private void select() {
		if (!isSelected)
			isSelected = true;
		else
			isSelected = false;
		
		repaint();
	}
	
	/**
	 * Metoda odsłaniająca pole
	 */
	public void check(){
		if(!checked){
			checked = true;
			isMarked = false;
			if(!isMine){
				if(surroundings == 0){
					mf.informSurroundingsAboutBlank(x, y);
				}
			}else{
				mf.endGame();
			}
			repaint();
			mf.checkVictory();
		}	
	}
	
	/**
	 * Setter
	 */
	public void setMine(){
		isMine = true;
	}
	
	/**
	 * Getter
	 */
	public boolean isMine(){
		return isMine;
	}
	
	/**
	 * Getter
	 */
	public boolean isMarked(){
		return isMarked;
	}
	
	/**
	 * Metoda inkrementująca ilość min w sąsiedztwie
	 */
	public void addSurroundings(){
		this.surroundings++;
	}

	/**
	 * Override metody z klasy JComponent rysującej komponent
	 */
	@Override
	public void paintComponent(Graphics g) {
		if (!checked) {
			if(isMarked){
				g.setColor(Color.ORANGE);
				g.fillRect(0, 0, tileSize, tileSize);
			}else if (isSelected) {
				g.setColor(Color.BLACK);
				g.fillRect(0, 0, tileSize, tileSize);
			} else {
				g.setColor(Color.GRAY);
				g.fillRect(0, 0, tileSize, tileSize);
			}
			
		} else {
			if(!isMine){
				g.setColor(Color.WHITE);
				g.fillRect(0, 0, tileSize, tileSize);
				if(surroundings > 0){
					g.setColor(Color.BLACK);
					g.drawString("" + surroundings, (int) (getWidth()/2 - getWidth()*0.1 + 1) , (int) (getHeight()/2 + getHeight()*0.1 + 1));
				}
					
			}else{
				g.setColor(Color.RED);
				g.fillRect(0, 0, tileSize, tileSize);
			}
		}
	}

}
