import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileSystemView;
import net.miginfocom.layout.AC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;

public class MakePlayer {
	
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
	public static void main(int pNumber, Main mainActivity) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MakePlayer window = new MakePlayer(pNumber, mainActivity);
					window.frame.setVisible(true);
				} catch (Exception e) {
					mainActivity.log(e.getMessage());
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MakePlayer(int pNumber, Main mainActivity) {
		initialize(pNumber, mainActivity);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int pNumber, Main mainActivity) {
		frame = new JFrame();
		setWindowPosition(frame, 0);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout(new LC(), new AC().grow(), new AC().grow()));
		frame.setTitle("Farmers of Capitalism: Player Creation - P" + (pNumber + 1));
		frame.setIconImage(((ImageIcon) image.getIcon()).getImage());
		
		JLabel askName = new JLabel("Name for P" + (pNumber + 1));
		askName.setHorizontalAlignment(SwingConstants.CENTER);
		askName.setFont(defaultFont);
		frame.add(askName, "growx, span");
		
		JTextField getName = new JTextField("");
		getName.setHorizontalAlignment(SwingConstants.CENTER);
		getName.setFont(defaultFont);
		frame.add(getName, "growx, span, wrap");
		
		JButton submit = new JButton("Submit");
		submit.setHorizontalAlignment(SwingConstants.CENTER);
		submit.setFont(defaultFont);
		submit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				mainActivity.players[pNumber] = new Player(getName.getText(), 0, 0);
				frame.dispose();
			}
		});
		frame.add(submit, "span, growx");
		
		getName.addKeyListener(new KeyListener(){
			@Override
			public void keyPressed(KeyEvent e){
				if(e.getKeyCode() == KeyEvent.VK_ENTER && !getName.getText().equals("")){
					submit.getActionListeners()[0].actionPerformed(null);
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
			}
			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
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
