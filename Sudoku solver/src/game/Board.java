package game;

public class Board {

	private int[][] board;


	public Board()
	{
		board = new int[9][9];
	}

	public Board(int[][] board)
	{
		this.board=board;
	}


	public int[][] getFullBoard(){
		return board;
	}

	public int get(int x,int y)
	{
		return x>9||y>9||x<0||y<0 ? -1 : board[x][y];
	}

	public void put(int x,int y,int num)
	{
		if (!(x>9||y>9||x<0||y<0)) board[x][y] = num;
	}

	public String toString(){
		String ret = "";
		for (int i=0;i<9;i++)
		{
			for (int j=0;j<9;j++)
				ret+=board[i][j]+" ";
			ret+="\n";
		}
		return ret;
	}

}
