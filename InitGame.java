import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileSystemView;
import net.miginfocom.layout.AC;
import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;

public class InitGame {
	
	@SuppressWarnings("serial")
	public class LayerController extends JLayeredPane{
		
		public LayerController(){
			super();
		}
		
		@Override
		public boolean isOptimizedDrawingEnabled(){
			return false;
		}
	}
	
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
	
	public static String gameAction = "none";
	public static String[] logs;
	public static InitGame window;

	private JFrame frame;
	private Font defaultFont;
	private ImageContainer image = new ImageContainer(FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + "\\My Games\\Farmers of Capitalism\\Images\\splash.jpg", "splash image - hills");

	/**
	 * Launch the application.
	 */
	public static String[] main(String[] args) throws InterruptedException{
		String[] toLog = new String[args.length + 2];
		for(int i = 0; i < args.length; i++){
			toLog[i] = args[i];
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					toLog[args.length] = "Initializing Splash Screen";
					window = new InitGame(toLog);
					window.frame.setVisible(true);
				} catch (Exception e) {
					toLog[args.length] = e.getMessage();
				}
			}
		});
		return toLog;
	}

	/**
	 * Create the application.
	 */
	public InitGame(String[] args) throws InterruptedException{
		initialize(args);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String[] args) throws InterruptedException{
		frame = new JFrame();
		setWindowPosition(frame, 0);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout(new LC(), new AC().grow(), new AC().grow()));
		frame.setTitle("Farmers Of Capitalism");
		frame.setIconImage(((ImageIcon) image.getIcon()).getImage());
		
		JPanel main = new JPanel(new MigLayout(new LC(), new AC().grow(), new AC().grow()));
		JPanel buttons = new JPanel(new MigLayout(new LC(), new AC().grow(), new AC().grow()));
		LayerController layer = new LayerController();
		layer.setLayout(new MigLayout());
		layer.add(main, new CC().width("100%").height("100%"), new Integer(1));
		layer.add(buttons, new CC().pos("80%", "80%"), new Integer(0));
		frame.add(layer, "grow");
		
		image.setFont(defaultFont);
		image.setHorizontalAlignment(SwingConstants.CENTER);
		main.add(image, "grow");
		
		JButton newGame = new JButton("New Game");
		newGame.setHorizontalAlignment(SwingConstants.CENTER);
		newGame.setFont(defaultFont);
		newGame.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				args[args.length - 1] = "User selected new game";
				logs = args;
				gameAction = "new game";
			}
		});
		buttons.add(newGame, "growx, wrap");
		
		JButton cont = new JButton("Continue");
		cont.setHorizontalAlignment(SwingConstants.CENTER);
		cont.setFont(defaultFont);
		cont.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				//TODO user selects game save
				args[args.length - 1] = "User selected to continue game at "; //TODO + directoryName;
				logs = args;
				gameAction = ""; // TODO make it directory
			}
		});
		buttons.add(cont, "growx, wrap");
		
		/**
		 *
		frame.getRootPane().setDefaultButton(newGame);
		newGame.requestFocus();
		Thread.sleep(30);
		 * 
		 */
		frame.getRootPane().setDefaultButton(cont);
		cont.requestFocusInWindow();
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
	
	public void die(){
		frame.dispose();
	}

}
