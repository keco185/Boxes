package us.kdms.boxes;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

class Border extends JComponent {
	private static final long serialVersionUID = 1L;
	int current = 0; //current player 1 - 5
	int currentmoveydot = 0;
	int[][] multD; //lines
	int[][] multP; //boxes
	int[] multC; //score for players 0 - 4
	int players; //number of players
	boolean fullScreen = false;
	String[] names;
	public Border(int current, int[][] multP, int[][] multD, int[] multC, int players, boolean fullScreen, String[] names) {
		this.current = current;
		this.multP = multP;
		this.multD = multD;
		this.multC = multC;
		this.players = players;
		this.fullScreen = fullScreen;
		this.names = names;
	}
	public void paint (Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, 575, 575);
		g2.setColor(Color.DARK_GRAY);
		int i = 0;
		int k = 0;
		int p = 0;
		while (k < 10) {
			while (i < 19) {
				if (multD[k][i] > 0) {
					p = multD[k][i];
					if (p == 1) {
						g2.setColor(Color.RED);
					} else if (p == 2) {
						g2.setColor(Color.BLUE);
					} else if (p == 3) {
						g2.setColor(Color.GREEN);
					} else if (p == 4) {
						g2.setColor(new Color(255,141,0));
					} else {
						g2.setColor(Color.getHSBColor(Float.parseFloat("0.764"), Float.parseFloat("0.8"), Float.parseFloat("0.75")));
					}
					if ((i % 2 == 0)) {
						g2.drawLine((k + 1) * 50 + 2, (i + 2) * 25 + 2, (k + 2) * 50 + 2, (i + 2) * 25 + 2);
						g2.drawLine((k + 1) * 50 + 3, (i + 2) * 25 + 3, (k + 2) * 50 + 3, (i + 2) * 25 + 3);
					} else {
						g2.drawLine((k + 1) * 50 + 2, (i + 1) * 25 + 2, (k + 1) * 50 + 2, (i + 3) * 25 + 2);
						g2.drawLine((k + 1) * 50 + 3, (i + 1) * 25 + 3, (k + 1) * 50 + 3, (i + 3) * 25 + 3);
					}
				}
				i++;
			}
			i = 0;
			k++;
		}
		k = 0;
		while (k < 9) {
			while (i < 9) {
				if (multP[k][i] > 0) {
					p = multP[k][i];
					if (p == 1) {
						g2.setColor(Color.RED);
					} else if (p == 2) {
						g2.setColor(Color.BLUE);
					} else if (p == 3) {
						g2.setColor(Color.GREEN);
					} else if (p == 4) {
						g2.setColor(new Color(255,141,0));
					} else {
						g2.setColor(Color.getHSBColor(Float.parseFloat("0.764"), Float.parseFloat("0.8"), Float.parseFloat("0.75")));
					}
					g2.fillRect(k * 50 + 60, i * 50 + 60, 35, 35);
				}
				i++;
			}
			i = 0;
			k++;
		}
		k = 0;
		while (k < players) {
			if (k == 0) {
				g2.setColor(Color.RED);
				g2.drawString(names[k] + ": " + multC[k], k * 100 + 50, 535);
			} else if (k == 1) {
				g2.setColor(Color.BLUE);
				g2.drawString(names[k] + ": " + multC[k], k * 100 + 50, 535);
			} else if (k == 2) {
				g2.setColor(Color.GREEN);
				g2.drawString(names[k] + ": " + multC[k], k * 100 + 50, 535);
			} else if (k == 3) {
				g2.setColor(new Color(255,141,0));
				g2.drawString(names[k] + ": " + multC[k], k * 100 + 50, 535);
			} else if (k == 4){
				g2.setColor(Color.getHSBColor(Float.parseFloat("0.764"), Float.parseFloat("0.8"), Float.parseFloat("0.75")));
				g2.drawString(names[k] + ": " + multC[k], k * 100 + 50, 535);
			}
			k++;
		}
		k = 0;
		if (current == 1) {
			g2.setColor(Color.RED);
		} else if (current == 2) {
			g2.setColor(Color.BLUE);
		} else if (current == 3) {
			g2.setColor(Color.GREEN);
		} else if (current == 4) {
			g2.setColor(new Color(255,141,0));
		} else {
			g2.setColor(Color.getHSBColor(Float.parseFloat("0.764"), Float.parseFloat("0.8"), Float.parseFloat("0.75")));
		}
		g2.fillOval(187, 10, 200, 20);
		g2.setColor(Color.BLACK);
		while (k < 10) {
			while (i < 10) {
				g2.fillOval(i * 50 + 50, k * 50 + 50, 5, 5);
				i++;
			}
			i = 0;
			k++;
		}
		k = 0;
		if (fullScreen) {
			g2.setColor(Color.LIGHT_GRAY);
			g2.fillRect(0, 585, 585, getHeight() - 585);
			g2.setColor(Color.BLACK);
			g2.fillRect(575, 0, 10, 585);
			g2.fillRect(0, 575, 585, 10);
			g2.fillRect(10, 595, 277, 10);
			g2.fillRect(10, 595, 10, (getHeight() - 605));
			g2.fillRect(10, (getHeight() - 20), 277, 10);
			g2.fillRect(277, 595, 10, (getHeight() - 605));
			g2.fillRect(297, 595, 277, 10);
			g2.fillRect(297, 595, 10, (getHeight() - 605));
			g2.fillRect(297, (getHeight() - 20), 277, 10);
			g2.fillRect(564, 595, 10, (getHeight() - 605));
			try {
				ImageIcon windowButton;
				windowButton = new ImageIcon(Class.forName("us.kdms.boxes.Boxes").getResource("window.png"));
				Image buttonWindow = windowButton.getImage();
				g2.drawImage(buttonWindow, 20, 605, 257, (getHeight() - 605), null);
				ImageIcon quitButton;
				quitButton = new ImageIcon(Class.forName("us.kdms.boxes.Boxes").getResource("quit.png"));
				Image buttonQuit = quitButton.getImage();
				g2.drawImage(buttonQuit, 307, 605, 257, (getHeight() - 605), null);
				ImageIcon skipButton;
				skipButton = new ImageIcon(Class.forName("us.kdms.boxes.Boxes").getResource("skip.png"));
				Image buttonSkip = skipButton.getImage();
				g2.drawImage(buttonSkip, 595, 10, (getWidth() - 585) / 2 - 20, 200, null);
				ImageIcon resetButton;
				resetButton = new ImageIcon(Class.forName("us.kdms.boxes.Boxes").getResource("reset.png"));
				Image buttonReset = resetButton.getImage();
				g2.drawImage(buttonReset, 595 + ((getWidth() - 585) / 2 - 10), 10, (getWidth() - 585) / 2 - 20, 200, null);
				ImageIcon classicButton;
				classicButton = new ImageIcon(Class.forName("us.kdms.boxes.Boxes").getResource("classicMode.png"));
				Image buttonClassic = classicButton.getImage();
				g2.drawImage(buttonClassic, 595 + ((getWidth() - 585) / 2 - 10), 220, (getWidth() - 585) / 4 - 20, 100, null);
				ImageIcon avoidanceButton;
				avoidanceButton = new ImageIcon(Class.forName("us.kdms.boxes.Boxes").getResource("avoidanceMode.png"));
				Image buttonAvoidance = avoidanceButton.getImage();
				g2.drawImage(buttonAvoidance, 595 + ((getWidth() - 585) / 2 - 10), 330, (getWidth() - 585) / 4 - 20, 100, null);
				ImageIcon attachmentButton;
				attachmentButton = new ImageIcon(Class.forName("us.kdms.boxes.Boxes").getResource("attachmentMode.png"));
				Image buttonAttachment = attachmentButton.getImage();
				g2.drawImage(buttonAttachment, 595 + ((getWidth() - 585) / 2 - 10), 440, (getWidth() - 585) / 4 - 20, 100, null);
			} catch (Exception e) {
			}
			g2.scale(3.0, 3.0);
			while (k < players) {
				if (k == 0) {
					g2.setColor(Color.RED);
					g2.drawString(names[k] + ": " + multC[k], 200, 90 + (k * 30));
				} else if (k == 1) {
					g2.setColor(Color.BLUE);
					g2.drawString(names[k] + ": " + multC[k], 200, 90 + (k * 30));
				} else if (k == 2) {
					g2.setColor(Color.GREEN);
					g2.drawString(names[k] + ": " + multC[k], 200, 90 + (k * 30));
				} else if (k == 3) {
					g2.setColor(new Color(255,141,0));
					g2.drawString(names[k] + ": " + multC[k], 200, 90 + (k * 30));
				} else if (k == 4){
					g2.setColor(Color.getHSBColor(Float.parseFloat("0.764"), Float.parseFloat("0.8"), Float.parseFloat("0.75")));
					g2.drawString(names[k] + ": " + multC[k], 200, 90 + (k * 30));
				}
				k++;
			}
			k = 0;
		}
	}
}
public class Boxes {
	public static boolean isPressed = false;
	public static boolean reset = false;
	public static boolean newGame = false;
	public static int newGameMode = -1;
	public static String fileLocation = "savefile.boxes";
	public static boolean loadGame = false;
	public static boolean skipCurrent = false;
	public static boolean fullScreen = false;
	public static boolean setFullScreen = false;
	public static boolean skipCurrentFS = false;
	public static String[] names = new String[5];
	public static int online = 0;
	public static int onlineNumber = -1;
	public static boolean goSaveFile = true;
	static GraphicsDevice device = GraphicsEnvironment
			.getLocalGraphicsEnvironment().getScreenDevices()[0];
	public static void main(String[] args) {
		int[][] multD = new int[10][19];
		int[][] multP = new int[9][9];
		int current = 1;
		int players = 2;
		int[] multC = new int[5];
		int currentmovex = 0;
		int currentmovey = 0;
		int currentmovexdot = -1;
		int currentmoveydot = -1;
		int i = 0;
		int k = 0;
		
		@SuppressWarnings("unused")
		int gameNumber;
		boolean m1m1 = false;
		boolean m1p0 = false;
		boolean m1p1 = false;
		boolean p0m1 = false;
		boolean p0p1 = false;
		boolean p1m1 = false;
		boolean p1p0 = false;
		boolean p1p1 = false;
		boolean p0p2 = false;
		boolean p0m2 = false;
		boolean firstRound = true;
		boolean fullScreen = false;
		int counter = 31;
		int gameMode = 0;
		int onlinePos = 0;
		URL url;
		String[] gameModes = new String[3];
		gameModes[0] = "Classic";
		gameModes[1] = "Avoidance";
		gameModes[2] = "Attachment";
		boolean boxComplete = false;
		boolean game = true;
		String[] options = new String[2];
		options[0] = "Yes";
		options[1] = "No";
		String[] playerOpt = new String[6];
		playerOpt[4] = "1 Player";
		playerOpt[3] = "2 Players";
		playerOpt[2] = "3 Players";
		playerOpt[1] = "4 Players";
		playerOpt[0] = "5 Players";
		playerOpt[5] = "Online";
		ImageIcon image1 = null;
		JFrame window = new JFrame();
		MouseListener ml = new MouseListener()
		{
			@Override
			public void mouseClicked(MouseEvent e) {				
			}
			@Override
			public void mouseEntered(MouseEvent e) {				
			}
			@Override
			public void mouseExited(MouseEvent e) {				
			}
			@Override
			public void mousePressed(MouseEvent e) {
				isPressed = true;
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				isPressed = false;
			}
		}
		;
		window.addMouseListener (ml);
		window.revalidate ();
		MenuBar menuBar = new MenuBar();
		Menu menuFile = new Menu("File");
		Menu menuModes = new Menu("Game Modes");
		Menu menuDisplay = new Menu("Display");
		menuBar.add(menuFile);
		menuBar.add(menuModes);
		menuBar.add(menuDisplay);
		MenuItem menuItemReset = new MenuItem("Reset");
		menuFile.add(menuItemReset);
		MenuItem menuItemLoad = new MenuItem("Load Game");
		menuFile.add(menuItemLoad);
		MenuItem menuItemNew = new MenuItem("New Game");
		menuFile.add(menuItemNew);
		MenuItem menuItemSkip = new MenuItem("Skip");
		menuFile.add(menuItemSkip);
		MenuItem menuItemOnline = new MenuItem("Online");
		menuFile.add(menuItemOnline);
		MenuItem menuItemClassic = new MenuItem("Classic Mode");
		menuModes.add(menuItemClassic);
		MenuItem menuItemAvoidance = new MenuItem("Avoidance Mode");
		menuModes.add(menuItemAvoidance);
		MenuItem menuItemAttachment = new MenuItem("Attachment Mode");
		menuModes.add(menuItemAttachment);
		MenuItem menuItemFullScreen = new MenuItem("Full Screen");
		menuDisplay.add(menuItemFullScreen);
		window.setMenuBar(menuBar);
		menuItemLoad.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				final JFileChooser fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					System.out.println("Opening: " + file.getPath() + ".");
					fileLocation = file.getPath();
					loadGame = true;
				} else {
					System.out.println("Open command cancelled by user.");
				}
			}
		});
		menuItemSkip.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				skipCurrent = true;
			}
		});
		menuItemOnline.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				online = 1;
				onlineNumber = 1;
				goSaveFile = true;
			}
		});
		menuItemReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				reset = true;
			}
		});
		menuItemAttachment.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				newGameMode = 2;
			}
		});
		menuItemAvoidance.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				newGameMode = 1;
			}
		});
		menuItemClassic.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				newGameMode = 0;
			}
		});
		menuItemNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				newGame = true;
				reset = true;
			}
		});
		menuItemFullScreen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setFullScreen = true;
			}

		});
		try {
			image1 = new ImageIcon(Class.forName("us.kdms.boxes.Boxes").getResource("icon.png"));
			Image regImage = image1.getImage();
			window.setIconImage(regImage);
			Cursor cursor = new Cursor(0);
			window.setCursor(cursor);
		} catch (ClassNotFoundException e2) {
			e2.printStackTrace();
		}
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Cursor redCursorF = null;
		Cursor blueCursorF = null;
		Cursor greenCursorF = null;
		Cursor yellowCursorF = null;
		Cursor orangeCursorF = null;
		Cursor redCursorFDown = null;
		Cursor blueCursorFDown = null;
		Cursor greenCursorFDown = null;
		Cursor yellowCursorFDown = null;
		Cursor orangeCursorFDown = null;
		try {
			ImageIcon cursorRed = new ImageIcon(Class.forName("us.kdms.boxes.Boxes").getResource("red.png"));
			Image redCursor = cursorRed.getImage();
			ImageIcon cursorBlue = new ImageIcon(Class.forName("us.kdms.boxes.Boxes").getResource("blue.png"));
			Image blueCursor = cursorBlue.getImage();
			ImageIcon cursorGreen = new ImageIcon(Class.forName("us.kdms.boxes.Boxes").getResource("green.png"));
			Image greenCursor = cursorGreen.getImage();
			ImageIcon cursorYellow = new ImageIcon(Class.forName("us.kdms.boxes.Boxes").getResource("yellow.png"));
			Image yellowCursor = cursorYellow.getImage();
			ImageIcon cursorOrange = new ImageIcon(Class.forName("us.kdms.boxes.Boxes").getResource("orange.png"));
			Image orangeCursor = cursorOrange.getImage();
			ImageIcon cursorRedDown = new ImageIcon(Class.forName("us.kdms.boxes.Boxes").getResource("redDown.png"));
			Image redCursorDown = cursorRedDown.getImage();
			ImageIcon cursorBlueDown = new ImageIcon(Class.forName("us.kdms.boxes.Boxes").getResource("blueDown.png"));
			Image blueCursorDown = cursorBlueDown.getImage();
			ImageIcon cursorGreenDown = new ImageIcon(Class.forName("us.kdms.boxes.Boxes").getResource("greenDown.png"));
			Image greenCursorDown = cursorGreenDown.getImage();
			ImageIcon cursorYellowDown = new ImageIcon(Class.forName("us.kdms.boxes.Boxes").getResource("yellowDown.png"));
			Image yellowCursorDown = cursorYellowDown.getImage();
			ImageIcon cursorOrangeDown = new ImageIcon(Class.forName("us.kdms.boxes.Boxes").getResource("orangeDown.png"));
			Image orangeCursorDown = cursorOrangeDown.getImage();
			Point cursorHotSpot = new Point(8,8);
			redCursorF = toolkit.createCustomCursor(redCursor, cursorHotSpot, "Cursor");
			blueCursorF = toolkit.createCustomCursor(blueCursor, cursorHotSpot, "Cursor");
			greenCursorF = toolkit.createCustomCursor(greenCursor, cursorHotSpot, "Cursor");
			yellowCursorF = toolkit.createCustomCursor(yellowCursor, cursorHotSpot, "Cursor");
			orangeCursorF = toolkit.createCustomCursor(orangeCursor, cursorHotSpot, "Cursor");
			redCursorFDown = toolkit.createCustomCursor(redCursorDown, cursorHotSpot, "Cursor");
			blueCursorFDown = toolkit.createCustomCursor(blueCursorDown, cursorHotSpot, "Cursor");
			greenCursorFDown = toolkit.createCustomCursor(greenCursorDown, cursorHotSpot, "Cursor");
			yellowCursorFDown = toolkit.createCustomCursor(yellowCursorDown, cursorHotSpot, "Cursor");
			orangeCursorFDown = toolkit.createCustomCursor(orangeCursorDown, cursorHotSpot, "Cursor");
		} catch (ClassNotFoundException e2) {
			e2.printStackTrace();
		}
		int continueFromSave = 0;
		try {
			int[][][] file = Save.OpenFile(fileLocation, onlineNumber);
			if (file[0][0][0] == -1) {
				players = 5 - JOptionPane.showOptionDialog(null, "How many players?", "New Game", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, image1, playerOpt, 0);
				if (players == -1) {
					players = 2;
				}
				if (players == 0) {
					online = 1;
					onlineNumber = -1;
					while (onlineNumber < 0) {
						try {
							onlineNumber = Integer.parseInt(JOptionPane.showInputDialog("Enter your online number"));
							gameNumber = Lobby.mainLobby(onlineNumber);
						} catch (Exception e) {

						}
					}
				} else {
					online = 0;
					onlineNumber = -1;
					gameMode = JOptionPane.showOptionDialog(null, "Select a Game Mode: ", "New Game", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, image1, gameModes, 0);
				}
				int v = 0;
				while (v < players) {
					names[v] = JOptionPane.showInputDialog(window, "Name for player " + (v+1) + "?", "Set Player Name", JOptionPane.QUESTION_MESSAGE);
					v++;
				}
			} else {
				continueFromSave = JOptionPane.showOptionDialog(null, "Continue from save?", "Found Save File", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, image1, options, 0);
				if (continueFromSave == 0) {
					players = file[0][0][0];
					current = file[0][0][1];
					gameMode = file[0][0][2];
					online = file[0][0][3];
					onlineNumber = file[0][0][4];
					while (k < 10) {
						while (i < 19) {
							multD[k][i] = file[1][k][i];
							i++;
						}
						i = 0;
						k++;
					}
					k = 0;
					while (k < 9) {
						while (i < 9) {
							multP[k][i] = file[2][k][i];
							i++;
						}
						i = 0;
						k++;
					}
					k = 0;
					while (k < players) {
						multC[k] = file[3][0][k];
						k++;
					}
					k = 0;
					int v = 0;
					while (v < players) {
						names[v] = JOptionPane.showInputDialog(window, "Name for player " + (v+1) + "?", "Set Player Name", JOptionPane.QUESTION_MESSAGE);
						v++;
					}
				} else {
					players = 5 - JOptionPane.showOptionDialog(null, "How many players?", "New Game", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, image1, playerOpt, 0);
					if (players == -1) {
						players = 2;
					}
					if (players == 0) {
						online = 1;
						onlineNumber = -1;
						while (onlineNumber < 0) {
							try {
								onlineNumber = Integer.parseInt(JOptionPane.showInputDialog("Enter your online number"));
								gameNumber = Lobby.mainLobby(onlineNumber);
							} catch (Exception e) {

							}
						}
					} else {
						online = 0;
						onlineNumber = -1;
						gameMode = JOptionPane.showOptionDialog(null, "Select a Game Mode: ", "New Game", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, image1, gameModes, 0);
					}
					int v = 0;
					while (v < players) {
						names[v] = JOptionPane.showInputDialog(window, "Name for player " + (v+1) + "?", "Set Player Name", JOptionPane.QUESTION_MESSAGE);
						v++;
					}	
				}
			}
		} catch (IOException e1) {
			players = 5 - JOptionPane.showOptionDialog(null, "How many players?", "New Game", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, image1, playerOpt, 0);
			if (players == 0) {
				online = 1;
				onlineNumber = -1;
				while (onlineNumber < 0) {
					try {
						onlineNumber = Integer.parseInt(JOptionPane.showInputDialog("Enter your online number"));
						gameNumber = Lobby.mainLobby(onlineNumber);
					} catch (Exception e) {

					}
				}
			} else {
				online = 0;
				onlineNumber = -1;
				gameMode = JOptionPane.showOptionDialog(null, "Select a Game Mode: ", "New Game", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, image1, gameModes, 0);
			}
			int v = 0;
			while (v < players) {
				names[v] = JOptionPane.showInputDialog(window, "Name for player " + (v+1) + "?", "Set Player Name", JOptionPane.QUESTION_MESSAGE);
				v++;
			}
		}
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setTitle("Boxes");
		window.setResizable(false);
		window.setName("Boxes");
		window.pack();
		window.setBounds(0, 0, 575, 575);
		window.getContentPane().removeAll();
		window.getContentPane().add(new Border(current, multP, multD, multC, players, fullScreen, names));
		window.setVisible(true);
		int windowStartY = window.getLocation().y;
		int windowStartX = window.getLocation().x;
		double calcX = 0;
		double calcX2 = 0;
		double calcY = 0;
		double calcY2 = 0;
		try {
			url = new URL("http://kdms.us/boxes/index.php?i=0," + onlineNumber);
			URLConnection conn = url.openConnection();
			BufferedReader br = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			onlinePos = Integer.parseInt(br.readLine());
		} catch (Exception e) {
		}
		while (game) {
			while (currentmovexdot == -1 || currentmoveydot == -1) {
				if (KeyboardListener.isExit()) {
					System.exit(0);
				}
				if (setFullScreen) {
					fullScreen = true;
					window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
					window.setExtendedState(JFrame.MAXIMIZED_BOTH);
					device.setFullScreenWindow(window);
					setFullScreen = false;
					window.getContentPane().removeAll();
					window.getContentPane().add(new Border(current, multP, multD, multC, players, fullScreen,names));
					window.setVisible(true);

				}
				if (!isPressed) {
					skipCurrentFS = false;
				}
				if (loadGame) {
					try {
						int[][][] file = Save.OpenFile(fileLocation, onlineNumber);
						if (file[0][0][0] == -1) {
							JOptionPane.showMessageDialog(window, "Could not load file","Error",JOptionPane.ERROR_MESSAGE, image1);
						} else {
							players = file[0][0][0];
							current = file[0][0][1];
							gameMode = file[0][0][2];
							online = file[0][0][3];
							onlineNumber = file[0][0][4];
							while (k < 10) {
								while (i < 19) {
									multD[k][i] = file[1][k][i];
									i++;
								}
								i = 0;
								k++;
							}
							k = 0;
							while (k < 9) {
								while (i < 9) {
									multP[k][i] = file[2][k][i];
									i++;
								}
								i = 0;
								k++;
							}
							k = 0;
							while (k < players) {
								multC[k] = file[3][0][k];
								k++;
							}
							k = 0;
						}
						int v = 0;
						while (v < players) {
							names[v] = JOptionPane.showInputDialog(window, "Name for player " + (v+1) + "?", "Set Player Name", JOptionPane.QUESTION_MESSAGE);
							v++;
						}
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(window, "Could not load file","Error",JOptionPane.ERROR_MESSAGE, image1);
					}
					window.getContentPane().removeAll();
					window.getContentPane().add(new Border(current, multP, multD, multC, players, fullScreen,names));
					window.setVisible(true);
					loadGame = false;
				}
				try {
					Thread.sleep(33);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (online == 1){
					if (goSaveFile) {
						try {
							Save.SaveFile(players, current, online, onlineNumber, multD, multP, multC, gameMode, fileLocation);
							goSaveFile = false;
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					if (counter > 30) {
						counter = 0;
						try {
							int[][][] file = Save.OpenFile(fileLocation, onlineNumber);
							players = file[0][0][0];
							current = file[0][0][1];
							gameMode = file[0][0][2];
							online = file[0][0][3];
							onlineNumber = file[0][0][4];
							while (k < 10) {
								while (i < 19) {
									multD[k][i] = file[1][k][i];
									i++;
								}
								i = 0;
								k++;
							}
							k = 0;
							while (k < 9) {
								while (i < 9) {
									multP[k][i] = file[2][k][i];
									i++;
								}
								i = 0;
								k++;
							}
							k = 0;
							while (k < players) {
								multC[k] = file[3][0][k];
								k++;
							}
							k = 0;
						} catch (IOException e1) {
						}
						window.getContentPane().removeAll();
						window.getContentPane().add(new Border(current, multP, multD, multC, players, fullScreen,names));
						window.setVisible(true);
					} else {
						counter++;
					}
				}
				if (skipCurrent) {
					if (current < players) {
						current++;
					} else {
						current = 1;
					}
					window.getContentPane().removeAll();
					window.getContentPane().add(new Border(current, multP, multD, multC, players, fullScreen,names));
					window.setVisible(true);
					skipCurrent = false;
				}
				if (reset) {
					if (newGame) {
						players = 5 - JOptionPane.showOptionDialog(null, "How many players?", "New Game", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, image1, playerOpt, 0);
						if (players == -1) {
							players = 2;
						}
						if (players == 0) {
							online = 1;
							onlineNumber = -1;
							while (onlineNumber < 0) {
								try {
									onlineNumber = Integer.parseInt(JOptionPane.showInputDialog("Enter your online number"));
									gameNumber = Lobby.mainLobby(onlineNumber);
								} catch (Exception e) {

								}
							}
							int v = 0;
							while (v < players) {
								names[v] = JOptionPane.showInputDialog(window, "Name for player " + (v+1) + "?", "Set Player Name", JOptionPane.QUESTION_MESSAGE);
								v++;
							}
						} else {
							online = 0;
							onlineNumber = -1;
							gameMode = JOptionPane.showOptionDialog(null, "Select a Game Mode: ", "New Game", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, image1, gameModes, 0);
							int v = 0;
							while (v < players) {
								names[v] = JOptionPane.showInputDialog(window, "Name for player " + (v+1) + "?", "Set Player Name", JOptionPane.QUESTION_MESSAGE);
								v++;
							}
						}
						try {
							url = new URL("http://kdms.us/boxes/index.php?i=0," + onlineNumber);
							URLConnection conn = url.openConnection();
							BufferedReader br = new BufferedReader(
									new InputStreamReader(conn.getInputStream()));
							onlinePos = Integer.parseInt(br.readLine());
						} catch (Exception e) {
						}
					}
					current = 1;
					Arrays.fill(multC, 0);
					for (int[] row: multD)
						Arrays.fill(row, 0);
					for (int[] row: multP)
						Arrays.fill(row, 0);
					window.getContentPane().removeAll();
					window.getContentPane().add(new Border(current, multP, multD, multC, players, fullScreen,names));
					window.setVisible(true);
					reset = false;
					newGame = false;
				}
				if (!(newGameMode == -1)) {
					gameMode = newGameMode;
					newGameMode = -1;
				}
				window.getContentPane().removeAll();
				window.getContentPane().add(new Border(current, multP, multD, multC, players, fullScreen,names));
				window.setVisible(true);
				if (!isPressed) {
					if (online == 0) {
					if (current == 1) {
						window.setCursor(redCursorF);
					} else if (current == 2) {
						window.setCursor(blueCursorF);
					} else if (current == 3) {
						window.setCursor(greenCursorF);
					} else if (current == 4) {
						window.setCursor(orangeCursorF);
					} else if (current == 5){
						window.setCursor(yellowCursorF);
					}
					} else {
						if (onlinePos == 1) {
							window.setCursor(redCursorF);
						} else if (onlinePos == 2) {
							window.setCursor(blueCursorF);
						} else if (onlinePos == 3) {
							window.setCursor(greenCursorF);
						} else if (onlinePos == 4) {
							window.setCursor(orangeCursorF);
						} else if (onlinePos == 5){
							window.setCursor(yellowCursorF);
						}
					}
				} else {
					if (online == 0) {
					if (current == 1) {
						window.setCursor(redCursorFDown);
					} else if (current == 2) {
						window.setCursor(blueCursorFDown);
					} else if (current == 3) {
						window.setCursor(greenCursorFDown);
					} else if (current == 4) {
						window.setCursor(orangeCursorFDown);
					} else {
						window.setCursor(yellowCursorFDown);
					}
					} else {
						if (onlinePos == 1) {
							window.setCursor(redCursorFDown);
						} else if (onlinePos == 2) {
							window.setCursor(blueCursorFDown);
						} else if (onlinePos == 3) {
							window.setCursor(greenCursorFDown);
						} else if (onlinePos == 4) {
							window.setCursor(orangeCursorFDown);
						} else {
							window.setCursor(yellowCursorFDown);
						}
					}
					currentmovex = (int) MouseInfo.getPointerInfo().getLocation().getX();
					currentmovey = (int) MouseInfo.getPointerInfo().getLocation().getY();
					if (fullScreen) {
						currentmovex = currentmovex - window.getLocation().x + (windowStartX * 2);
						currentmovey = currentmovey - window.getLocation().y + (windowStartY * 2);
					} else {
						currentmovex = currentmovex - window.getLocation().x + windowStartX;
						currentmovey = currentmovey - window.getLocation().y + windowStartY;
					}
					if (fullScreen) {
						if (MouseInfo.getPointerInfo().getLocation().getY() > 595) {
							if (MouseInfo.getPointerInfo().getLocation().getX() < 277) {
								device.setFullScreenWindow(null);
								window.getContentPane().removeAll();
								window.getContentPane().add(new Border(current, multP, multD, multC, players, false,names));
								window.setVisible(true);
								isPressed = false;
								fullScreen = false;
							} else if (MouseInfo.getPointerInfo().getLocation().getX() < 585){
								System.exit(0);
							}
						}
						if (MouseInfo.getPointerInfo().getLocation().getX() > 595 + ((window.getWidth() - 585) / 2 - 10) && MouseInfo.getPointerInfo().getLocation().getY() > 220 && MouseInfo.getPointerInfo().getLocation().getX() < (595 + ((window.getWidth() - 585) / 2 - 10)) + ((window.getWidth() - 585) / 4 - 20) && MouseInfo.getPointerInfo().getLocation().getY() < 320) {
							gameMode = 0;
						}
						if (MouseInfo.getPointerInfo().getLocation().getX() > 595 + ((window.getWidth() - 585) / 2 - 10) && MouseInfo.getPointerInfo().getLocation().getY() > 330 && MouseInfo.getPointerInfo().getLocation().getX() < (595 + ((window.getWidth() - 585) / 2 - 10)) + ((window.getWidth() - 585) / 4 - 20) && MouseInfo.getPointerInfo().getLocation().getY() < 430) {
							gameMode = 1;
						}
						if (MouseInfo.getPointerInfo().getLocation().getX() > 595 + ((window.getWidth() - 585) / 2 - 10) && MouseInfo.getPointerInfo().getLocation().getY() > 440 && MouseInfo.getPointerInfo().getLocation().getX() < (595 + ((window.getWidth() - 585) / 2 - 10)) + ((window.getWidth() - 585) / 4 - 20) && MouseInfo.getPointerInfo().getLocation().getY() < 540) {
							gameMode = 2;
						}
						if (MouseInfo.getPointerInfo().getLocation().getX() > 595 + ((window.getWidth() - 585) / 2 - 10) && MouseInfo.getPointerInfo().getLocation().getY() > 10 && MouseInfo.getPointerInfo().getLocation().getY() < 200 && MouseInfo.getPointerInfo().getLocation().getX() < window.getWidth() - 10) {
							reset = true;
						}
						if (MouseInfo.getPointerInfo().getLocation().getX() > 595 && MouseInfo.getPointerInfo().getLocation().getY() > 10 && MouseInfo.getPointerInfo().getLocation().getY() < 200 && MouseInfo.getPointerInfo().getLocation().getX() < 595 + ((window.getWidth() - 585) / 2 - 20)) {
							if (!skipCurrentFS) {
								if (current < players) {
									current++;
								} else {
									current = 1;
								}
							}
							skipCurrentFS = true;
							window.getContentPane().removeAll();
							window.getContentPane().add(new Border(current, multP, multD, multC, players, fullScreen,names));
							window.setVisible(true);
						}
					}
					calcX = (((double)(currentmovex - 50) / 50) + 0.25);
					calcX2 = (((double)(currentmovex - 50) / 50) - 0.25);
					if (Math.floor(calcX) == Math.floor(calcX2)) {
						calcY = (((double)(currentmovey - 50) / 50) - 0.25);
						calcY2 = (((double)(currentmovey - 50) / 50) - 0.75);
						if (Math.floor(calcY) == Math.floor(calcY2)) {
							currentmovexdot = (int) Math.floor(calcX);
							currentmoveydot = (int) (Math.floor(calcY) * 2);
							if (currentmovexdot > 8 || currentmoveydot > 18 || currentmovexdot < 0 || currentmoveydot < 0 || (multD[currentmovexdot][currentmoveydot] > 0)) {
								currentmovexdot = -1;
								currentmoveydot = -1;
							} else if (gameMode == 1) {
								try {
									m1p0 = (multD[currentmovexdot - 1][currentmoveydot] == current);
								} catch (Exception e) {
									m1p0 = false;
								}
								try {
									p1p0 = (multD[currentmovexdot + 1][currentmoveydot] == current);
								} catch (Exception e) {
									p1p0 = false;
								}
								try {
									p0m1 = (multD[currentmovexdot][currentmoveydot - 1] == current);
								} catch (Exception e) {
									p0m1 = false;
								}
								try {
									p0p1 = (multD[currentmovexdot][currentmoveydot + 1] == current);
								} catch (Exception e) {
									p0p1 = false;
								}
								try {
									p1m1 = (multD[currentmovexdot + 1][currentmoveydot - 1] == current);
								} catch (Exception e) {
									p1m1 = false;
								}
								try {
									p1p1 = (multD[currentmovexdot + 1][currentmoveydot + 1] == current);
								} catch (Exception e) {
									p1p1 = false;
								}
								if (m1p0 || p1p0 || p0m1 || p0p1  || p1m1 || p1p1) {
									currentmovexdot = -1;
									currentmoveydot = -1;
								}
							} else if (gameMode == 2 && !firstRound) {
								if (current == 1){
									try {
										m1p0 = (multD[currentmovexdot - 1][currentmoveydot] == players);
									} catch (Exception e) {
										m1p0 = false;
									}
									try {
										p1p0 = (multD[currentmovexdot + 1][currentmoveydot] == players);
									} catch (Exception e) {
										p1p0 = false;
									}
									try {
										p0m1 = (multD[currentmovexdot][currentmoveydot - 1] == players);
									} catch (Exception e) {
										p0m1 = false;
									}
									try {
										p0p1 = (multD[currentmovexdot][currentmoveydot + 1] == players);
									} catch (Exception e) {
										p0p1 = false;
									}
									try {
										p1m1 = (multD[currentmovexdot + 1][currentmoveydot - 1] == players);
									} catch (Exception e) {
										p1m1 = false;
									}
									try {
										p1p1 = (multD[currentmovexdot + 1][currentmoveydot + 1] == players);
									} catch (Exception e) {
										p1p1 = false;
									}
									if (!(m1p0 || p1p0 || p0m1 || p0p1  || p1m1 || p1p1)) {
										currentmovexdot = -1;
										currentmoveydot = -1;
									}
								} else {
									try {
										m1p0 = (multD[currentmovexdot - 1][currentmoveydot] == current - 1);
									} catch (Exception e) {
										m1p0 = false;
									}
									try {
										p1p0 = (multD[currentmovexdot + 1][currentmoveydot] == current - 1);
									} catch (Exception e) {
										p1p0 = false;
									}
									try {
										p0m1 = (multD[currentmovexdot][currentmoveydot - 1] == current - 1);
									} catch (Exception e) {
										p0m1 = false;
									}
									try {
										p0p1 = (multD[currentmovexdot][currentmoveydot + 1] == current - 1);
									} catch (Exception e) {
										p0p1 = false;
									}
									try {
										p1m1 = (multD[currentmovexdot + 1][currentmoveydot - 1] == current - 1);
									} catch (Exception e) {
										p1m1 = false;
									}
									try {
										p1p1 = (multD[currentmovexdot + 1][currentmoveydot + 1] == current - 1);
									} catch (Exception e) {
										p1p1 = false;
									}
									if (!(m1p0 || p1p0 || p0m1 || p0p1  || p1m1 || p1p1)) {
										currentmovexdot = -1;
										currentmoveydot = -1;
									}
								}
							}
						}
					} else {
						calcY = (((double)(currentmovey - 50) / 50) + 0.25);
						calcY2 = (((double)(currentmovey - 50) / 50) - 0.25);
						if (Math.floor(calcY) == Math.floor(calcY2)) {
							currentmovexdot = (int) Math.floor(calcX);
							currentmoveydot = (int) ((Math.floor(calcY)) * 2 - 1);
							if (currentmovexdot > 9 || currentmoveydot > 17 || currentmovexdot < 0 || currentmoveydot < 0 || (multD[currentmovexdot][currentmoveydot] > 0)) {
								currentmovexdot = -1;
								currentmoveydot = -1;
							} else if (gameMode == 1 ) {
								try {
									p0m2 = (multD[currentmovexdot][currentmoveydot - 2] == current);
								} catch (Exception e) {
									p0m2 = false;
								}
								try {
									p0m1 = (multD[currentmovexdot][currentmoveydot - 1] == current);
								} catch (Exception e) {
									p0m1 = false;
								}try {
									p0p2 = (multD[currentmovexdot][currentmoveydot + 2] == current);
								} catch (Exception e) {
									p0p2 = false;
								}
								try {
									p0p1 = (multD[currentmovexdot][currentmoveydot + 1] == current);
								} catch (Exception e) {
									p0p1 = false;
								}
								try {
									m1m1 = (multD[currentmovexdot - 1][currentmoveydot - 1] == current);
								} catch (Exception e) {
									m1m1 = false;
								}
								try {
									m1p1 = (multD[currentmovexdot - 1][currentmoveydot + 1] == current);
								} catch (Exception e) {
									m1p1 = false;
								}
								if(p0m2 || p0p2 || p0m1 || p0p1 || m1m1 || m1p1) {
									currentmovexdot = -1;
									currentmoveydot = -1;
								}
							} else if (gameMode == 2 && !firstRound) {
								if (current == 1){
									try {
										p0m2 = (multD[currentmovexdot][currentmoveydot - 2] == players);
									} catch (Exception e) {
										p0m2 = false;
									}
									try {
										p0m1 = (multD[currentmovexdot][currentmoveydot - 1] == players);
									} catch (Exception e) {
										p0m1 = false;
									}try {
										p0p2 = (multD[currentmovexdot][currentmoveydot + 2] == players);
									} catch (Exception e) {
										p0p2 = false;
									}
									try {
										p0p1 = (multD[currentmovexdot][currentmoveydot + 1] == players);
									} catch (Exception e) {
										p0p1 = false;
									}
									try {
										m1m1 = (multD[currentmovexdot - 1][currentmoveydot - 1] == players);
									} catch (Exception e) {
										m1m1 = false;
									}
									try {
										m1p1 = (multD[currentmovexdot - 1][currentmoveydot + 1] == players);
									} catch (Exception e) {
										m1p1 = false;
									}
									if(!(p0m2 || p0p2 || p0m1 || p0p1 || m1m1 || m1p1)) {
										currentmovexdot = -1;
										currentmoveydot = -1;
									}
								} else {
									try {
										p0m2 = (multD[currentmovexdot][currentmoveydot - 2] == current - 1);
									} catch (Exception e) {
										p0m2 = false;
									}
									try {
										p0m1 = (multD[currentmovexdot][currentmoveydot - 1] == current - 1);
									} catch (Exception e) {
										p0m1 = false;
									}try {
										p0p2 = (multD[currentmovexdot][currentmoveydot + 2] == current - 1);
									} catch (Exception e) {
										p0p2 = false;
									}
									try {
										p0p1 = (multD[currentmovexdot][currentmoveydot + 1] == current - 1);
									} catch (Exception e) {
										p0p1 = false;
									}
									try {
										m1m1 = (multD[currentmovexdot - 1][currentmoveydot - 1] == current - 1);
									} catch (Exception e) {
										m1m1 = false;
									}
									try {
										m1p1 = (multD[currentmovexdot - 1][currentmoveydot + 1] == current - 1);
									} catch (Exception e) {
										m1p1 = false;
									}
									if(!(p0m2 || p0p2 || p0m1 || p0p1 || m1m1 || m1p1)) {
										currentmovexdot = -1;
										currentmoveydot = -1;
									}
								}
							}
						}
					}

				}				
			}
			if (currentmovexdot < 0 || currentmoveydot < 0 || currentmovexdot > 10 || currentmoveydot > 18) {
			} else {
				multD[currentmovexdot][currentmoveydot] = current;
				firstRound = false;
				if (currentmoveydot % 2 == 0) {
					try  {
						if (multD[currentmovexdot][currentmoveydot - 1] == 0 || multD[currentmovexdot][currentmoveydot - 2] == 0 || multD[currentmovexdot + 1][currentmoveydot - 1] == 0) {

						} else {
							multP[currentmovexdot][currentmoveydot / 2 - 1] = current;
							boxComplete = true;
							multC[current - 1]++;
						}
					} catch (Exception e) {
					}
					try {
						if (multD[currentmovexdot][currentmoveydot + 1] == 0 || multD[currentmovexdot][currentmoveydot + 2] == 0 || multD[currentmovexdot + 1][currentmoveydot + 1] == 0) {

						} else {
							multP[currentmovexdot][currentmoveydot / 2] = current;
							boxComplete = true;
							multC[current - 1]++;
						}
					} catch (Exception e) {
					}
				} else {
					try {
						if (multD[currentmovexdot - 1][currentmoveydot] == 0 || multD[currentmovexdot - 1][currentmoveydot - 1] == 0 || multD[currentmovexdot - 1][currentmoveydot + 1] == 0) {

						} else {
							multP[currentmovexdot - 1][(currentmoveydot - 1) / 2] = current;
							boxComplete = true;
							multC[current - 1]++;
						}
					} catch (Exception e) {
					}
					try {
						if (multD[currentmovexdot + 1][currentmoveydot] == 0 || multD[currentmovexdot][currentmoveydot - 1] == 0 || multD[currentmovexdot][currentmoveydot + 1] == 0) {

						} else {
							multP[currentmovexdot][(currentmoveydot - 1) / 2] = current;
							boxComplete = true;
							multC[current - 1]++;
						}
					} catch (Exception e) {
					}
				}
				if (!boxComplete) {
					if (players > current) {
						current++;
					} else {
						current = 1;
					}
				}
				boxComplete = false;
/*				window.getContentPane().removeAll();
				window.getContentPane().add(new Border(current, multP, multD, multC, players, fullScreen));
				window.setVisible(true);*/
			}
			currentmovex = 0;
			currentmovey = 0;
			currentmovexdot = -1;
			currentmoveydot = -1;
			try {
				Save.SaveFile(players, current, online, onlineNumber, multD, multP, multC, gameMode, fileLocation);
			} catch (IOException e) {
				e.printStackTrace();
			}
			counter = 31;
		}
	}
	
}
class AI {
	public int getMoves(int current, int gameMode, int[] multD) {
		current--;
		return 1;
	}
}