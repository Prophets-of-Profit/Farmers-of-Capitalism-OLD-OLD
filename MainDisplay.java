import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.filechooser.FileSystemView;
import net.miginfocom.layout.AC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;

public class MainDisplay {

	@SuppressWarnings("serial")
	public class ImageContainer extends JLabel{
		
		public ImageContainer(String fileDirectory, String description){
			this.setIcon(new ImageIcon(fileDirectory, description));
		}
		
		@Override
	    public void paintComponent(Graphics g)
	    {
	        super.paintComponent(g);
	        g.drawImage(((ImageIcon) this.getIcon()).getImage(), 0, 0, getWidth(), getHeight(), this);
	    }
		
	}

	private JFrame frame;
	private Font defaultFont;
	private ImageContainer image = new ImageContainer(FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + "\\My Games\\Farmers of Capitalism\\Images\\splash.jpg", "splash image - hills");


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainDisplay window = new MainDisplay();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainDisplay() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		setWindowPosition(frame, 0);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout(new LC(), new AC().grow(), new AC().grow()));
		frame.setTitle("Farmers of Capitalism");
		frame.setIconImage(((ImageIcon) image.getIcon()).getImage());
		
		JLabel hello = new JLabel("hello");
		hello.setFont(defaultFont);
		frame.add(hello);
	}
	
	private void setWindowPosition(JFrame window, int screen){        
	    GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    GraphicsDevice[] allDevices = env.getScreenDevices();
	    int topLeftX, topLeftY, screenX, screenY, windowPosX, windowPosY;
	    
	    screen = (screen < allDevices.length && screen > -1) ? screen : 0;

	    topLeftX = allDevices[screen].getDefaultConfiguration().getBounds().x;
	    topLeftY = allDevices[screen].getDefaultConfiguration().getBounds().y;
	    
        screenX  = allDevices[screen].getDefaultConfiguration().getBounds().width;
        screenY  = allDevices[screen].getDefaultConfiguration().getBounds().height;
	        
        window.setBounds(0, 0, allDevices[screen].getDefaultConfiguration().getBounds().width / 2, allDevices[screen].getDefaultConfiguration().getBounds().height / 2);
	    
	    windowPosX = ((screenX - window.getWidth())  / 2) + topLeftX;
	    windowPosY = ((screenY - window.getHeight()) / 2) + topLeftY;
	    
	    defaultFont = new Font("Roman Baseline", Font.ROMAN_BASELINE, screenX / 75);

	    window.setLocation(windowPosX, windowPosY);
	}

}
