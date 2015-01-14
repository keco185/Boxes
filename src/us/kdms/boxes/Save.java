package us.kdms.boxes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Save {
	@SuppressWarnings({ "resource" })
	public static String serverAddress = "127.0.0.1";
	public static int[][][] OpenFile(String fileLocation, int onlineNumber) throws IOException {
		List<String> savefound = new ArrayList<String>();
		int[][][] file = new int[4][10][19];
		if (onlineNumber > 0) {
			Socket s = new Socket(serverAddress, 9090);
			PrintWriter out = new PrintWriter(s.getOutputStream(), true);
	        out.println("1");
			BufferedReader input =
		            new BufferedReader(new InputStreamReader(s.getInputStream()));
			//URL url;
			//url = new URL("http://kdms.us/boxes/index.php");
			//URLConnection conn = url.openConnection();
			//BufferedReader br = new BufferedReader(
			//		new InputStreamReader(conn.getInputStream()));
			String page = input.readLine();
			savefound = Arrays.asList(page.split(","));
		} else {
			Scanner check = new Scanner(new File(fileLocation));
			while (check.hasNextLine()) {
				savefound.add(check.nextLine());
			}
		}
		int i = 0;
		if (savefound.size() > 1) {
			file[0][0][0] = Integer.valueOf(savefound.get(0));
			file[0][0][2] = Integer.valueOf(savefound.get(2));
			if (onlineNumber > 0) {
				file[0][0][1] = Integer.valueOf(savefound.get(1));
				URL url;
				url = new URL("http://kdms.us/boxes/index.php?i=" + file[0][0][1]);
				URLConnection conn = url.openConnection();
				BufferedReader br = new BufferedReader(
						new InputStreamReader(conn.getInputStream()));
				String currentPlayer = br.readLine();
				file[0][0][1] = Integer.parseInt(currentPlayer);
				file[0][0][3] = 1;
				file[0][0][4] = onlineNumber;
				i = 3;
			} else {
				file[0][0][1] = Integer.valueOf(savefound.get(1));
				file[0][0][3] = Integer.valueOf(savefound.get(3));
				file[0][0][4] = Integer.valueOf(savefound.get(4));
				i = 5;
			}
			int k = 0;
			int j = 0;
			while (k < 10) {
				while (j < 19) {
					file[1][k][j] = Integer.valueOf(savefound.get(i));
					j++;
					i++;
				}
				j = 0;
				k++;
			}
			k = 0;
			while (k < 9) {
				while (j < 9) {
					file[2][k][j] = Integer.valueOf(savefound.get(i));
					j++;
					i++;
				}
				j = 0;
				k++;
			}
			k = 0;
			while (k < file[0][0][0]) {
				file[3][0][k] = Integer.valueOf(savefound.get(i - 0));
				i++;
				k++;
			}
			k = 0;
		} else {
			file[0][0][0] = -1;
		}
		return file;
	}
	public static void SaveFile(int players, int current, int online, int onlineNumber, int[][] multD, int[][] multP, int[] multC, int gameMode, String fileLocation) throws IOException {
		int i = 0;
		int k = 0;
		if (online == 1) {
			String sendSave = "";
			sendSave+=players;
			sendSave+= ",";
			sendSave+=onlineNumber;
			sendSave+= ",";
			sendSave+=gameMode;
			sendSave+= ",";
			while (k < 10) {
				while (i < 19) {
					sendSave+=multD[k][i];
					sendSave+= ",";
					i++;
				}
				i = 0;
				k++;
			}
			k = 0;
			while (k < 9) {
				while (i < 9) {
					sendSave+=multP[k][i];
					sendSave+= ",";
					i++;
				}
				i = 0;
				k++;
			}
			k = 0;
			while (k < players) {
				sendSave+=multC[k];
				sendSave+= ",";
				k++;
			}
			k = 0;
			Socket s = new Socket(serverAddress, 9090);
			PrintWriter out = new PrintWriter(s.getOutputStream(), true);
			out.println(sendSave);
			//URL url;
			//url = new URL("http://kdms.us/boxes/index.php?i=" + sendSave);
			//URLConnection conn = url.openConnection();
			//BufferedReader br = new BufferedReader(
			//		new InputStreamReader(conn.getInputStream()));
			//System.out.println(String.valueOf(multC[3]));
			//System.out.println("Sending: http://kdms.us/boxes/index.php?i=" + sendSave);
		} else {
			Writer output;
			output = new BufferedWriter(new FileWriter(fileLocation, false));
			output.append(players + "\n");
			output.append(current + "\n");
			output.append(gameMode + "\n");
			output.append(online + "\n");
			output.append(onlineNumber + "\n");
			while (k < 10) {
				while (i < 19) {
					output.append(multD[k][i] + "\n");
					i++;
				}
				i = 0;
				k++;
			}
			k = 0;
			while (k < 9) {
				while (i < 9) {
					output.append(multP[k][i] + "\n");
					i++;
				}
				i = 0;
				k++;
			}
			k = 0;
			while (k < players) {
				output.append(multC[k] + "\n");
				k++;
			}
			k = 0;
			output.close();
		}
	}
}