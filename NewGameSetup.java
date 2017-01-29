import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileSystemView;
import net.miginfocom.layout.AC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;

public class NewGameSetup {
	
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
	public static void main(Main mainGame) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewGameSetup window = new NewGameSetup(mainGame);
					window.frame.setVisible(true);
				} catch (Exception e) {
					mainGame.log(e.getMessage());
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NewGameSetup(Main mainGame) {
		frame = new JFrame();
		
		frame.setTitle("Farmers of Capitalism - New Game");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setWindowPosition(frame, 0);
		frame.getContentPane().setLayout(new MigLayout(new LC(), new AC().grow(), new AC().grow()));
		frame.setIconImage(((ImageIcon) image.getIcon()).getImage());
		
		JLabel question = new JLabel("How many players will embark on this adventure?");
		question.setHorizontalAlignment(SwingConstants.CENTER);
		question.setFont(defaultFont);
		frame.getContentPane().add(question, "span, growx, wrap");
		
		JRadioButton onePlayer= new JRadioButton("1 Player");
		JRadioButton twoPlayer = new JRadioButton("2 Players");
		JRadioButton fourPlayer = new JRadioButton("4 Players");
		onePlayer.setHorizontalAlignment(SwingConstants.CENTER);
		twoPlayer.setHorizontalAlignment(SwingConstants.CENTER);
		fourPlayer.setHorizontalAlignment(SwingConstants.CENTER);
		onePlayer.setFont(defaultFont);
		twoPlayer.setFont(defaultFont);
		fourPlayer.setFont(defaultFont);
		ButtonGroup amtPlayers = new ButtonGroup();
		amtPlayers.add(onePlayer);
		amtPlayers.add(twoPlayer);
		amtPlayers.add(fourPlayer);
		frame.getContentPane().add(onePlayer, "growx");
		frame.getContentPane().add(twoPlayer, "growx");
		frame.getContentPane().add(fourPlayer, "growx, wrap");
		
		JButton confirm = new JButton("Confirm");
		confirm.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean buttonIsSelected = onePlayer.isSelected() || twoPlayer.isSelected() || fourPlayer.isSelected();
				if(buttonIsSelected && onePlayer.isSelected()){
					mainGame.numPlayers = 1;
				}else if(buttonIsSelected && twoPlayer.isSelected()){
					mainGame.numPlayers = 2;
				}else if(buttonIsSelected && fourPlayer.isSelected()){
					mainGame.numPlayers = 4;
				}
				if(buttonIsSelected){
					//TODO
					mainGame.mode = "local";
					frame.dispose();
				}
			}
			
		});
		confirm.setFont(defaultFont);
		frame.add(confirm, "growx, span");
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
