package FileOperation;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MyPanel extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ImageIcon icon;
	
	public MyPanel(String imagePath)
	{
		super();
		//URL url = getClass().getResource("D:\\java_2\\my_xiangmu\\image\\functionPanelImage.jpg");
		//setSize(getParent().getWidth(), getParent().getHeight());
		icon = new ImageIcon(imagePath);
		setSize(icon.getIconWidth(),icon.getIconHeight());
		setOpaque(false);
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Image img = icon.getImage();
		//g.drawImage(img, 0, 0, getParent());
		g.drawImage(img , 0 , 0 , this.getWidth() , this.getHeight() , this) ;
	}

}
