package Minesweeper.Minesweeper;

import java.awt.GridLayout;

import javax.swing.JFrame;

/**
 * Hello world!
 *
 */
public class App
{
	private static int x = 800;
	private static int y = 600;
	private static int size = 20;
	private static String title = "Minesweeper";
	private static MineField mf;
	private static JFrame f;
	
	private App(int x, int y){
		f = new JFrame();
		f.setSize(x, y);
		f.setTitle(title);
		f.setLayout(new GridLayout(1,1));
		mf = new MineField(size,size);
		f.add(mf);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);	
		
		
	}
	
	
    public static void main( String[] args )
    {
        new App(x, y);
    }


	public static void newGame() {
		f.remove(mf);
		mf = new MineField(size,size);
		f.add(mf);
		
		f.revalidate();
		f.repaint();
		System.out.println("asd");
	}
}
