package us.kdms.boxes;


public class LinePos {
	static int[][] multD = new int[5][9];
	public int GetStatus(int pos1, int pos2) {
		return multD[pos1][pos2];
	}
	public void SetStatus(int pos1, int pos2, int value) {
		multD[pos1][pos2] = value;
	}
}
