package Minesweeper.Minesweeper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
/**
 * Klasa reprezentująca menu gry.
 */
public class Menu extends JMenuBar{
	private static final long serialVersionUID = 1L;
	private JMenu game;
	private JMenuItem newGame;
	private JMenu settings;
	private JMenuItem easy;
	private JMenuItem medium;
	private JMenuItem hard;
	
	public Menu(){
		
		game = new JMenu("File");
		newGame = new JMenuItem("New Game");
		newGame.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				App.newGame();
			}
		});
		settings = new JMenu("Settings");
		easy = new JMenuItem("Easy 9x9");
		easy.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				App.newGame(9);
			}
		});
		medium = new JMenuItem("Medium 20x20");
		medium.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				App.newGame(20);
			}
		});
		hard = new JMenuItem("Hard 30x30");
		hard.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				App.newGame(30);
			}
		});
		game.add(newGame);
		settings.add(easy);
		settings.add(medium);
		settings.add(hard);
		
		this.add(game);
		this.add(settings);
	}

}
