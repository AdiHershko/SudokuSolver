package game;

public class Sudoku {

	private Board board;
	private boolean gameOver;
	private int[] horizontalCounter;
	private int[] verticalCounter;
	private int[] squareCounter;


	public Sudoku(){
		board = new Board();
		gameOver=false;
		horizontalCounter = new int[9];
		verticalCounter = new int[9];
		squareCounter = new int[9];
	}

	public Sudoku(int[][] board)
	{
		this.board = new Board(board);
		gameOver=false;
		horizontalCounter = new int[9];
		verticalCounter = new int[9];
		squareCounter = new int[9];
	}

	public Sudoku(Board board)
	{
		this.board = board;
		gameOver=false;
		horizontalCounter = new int[9];
		verticalCounter = new int[9];
		squareCounter = new int[9];
	}


	public boolean isGameOver(){
		if (!checkRows()) return false;
		if (!checkColumns()) return false;
		if (!checkSquares()) return false;
		return true;
	}


	private boolean checkRows(){
		for (int i=0;i<9;i++)
		{
			for (int k=0;k<9;k++) horizontalCounter[k]=0; //reset counter
			for (int j=0;j<9;j++)
			{
				if (board.get(i, j)==0) return false;
				horizontalCounter[board.get(i, j)-1]++;
			}
			for (int k=0;k<9;k++)
				if (horizontalCounter[k]!=1) return false;
		}
		return true;

	}

	private boolean checkColumns(){
		for (int i=0;i<9;i++)
		{
			for (int k=0;k<9;k++) verticalCounter[k]=0; //reset counter
			for (int j=0;j<9;j++)
			{
				if (board.get(i, j)==0) return false;
				verticalCounter[board.get(j, i)-1]++;
			}
			for (int k=0;k<9;k++)
				if (verticalCounter[k]!=1) return false;
		}
		return true;
	}

	private boolean checkSquares(){
		for (int z=0;z<=6;z+=3) //divide to small 3x3 squares
		{
			for (int w=0;w<=6;w+=3)
			{
				for (int k=0;k<9;k++) squareCounter[k]=0; //reset counter
				for (int i = z;i<z+3;i++)
					for (int j=w;j<w+3;j++)
					{
						if (board.get(i, j)==0) return false;
						squareCounter[board.get(i, j)-1]++;
					}
				for (int k=0;k<9;k++)
					if (squareCounter[k]!=1) return false;

			}
		}
		return true;
	}


	public boolean checkSingleRow(int row){
		for (int k=0;k<9;k++) horizontalCounter[k]=0; //reset counter
		for (int i=0;i<9;i++)
		{
			if (!(board.get(row, i)==0))
				horizontalCounter[board.get(row, i)-1]++;
		}
		for (int i=0;i<9;i++)
			if (horizontalCounter[i]!=1) return false;
		return true;

	}

	public boolean checkSingleColumn(int col){
		for (int k=0;k<9;k++) verticalCounter[k]=0; //reset counter
		for (int i=0;i<9;i++)
		{
			if (!(board.get(i, col)==0))
				verticalCounter[board.get(i, col)-1]++;
		}
		for (int i=0;i<9;i++)
			if (verticalCounter[i]!=1) return false;
		return true;
	}


	public boolean checkSingleSquare(int row,int col){
		for (int k=0;k<9;k++) squareCounter[k]=0; //reset counter
		for (int i = row;i<row+3;i++)
			for (int j=col;j<col+3;j++)
			{
				if (board.get(i, j)==0) return false;
				squareCounter[board.get(i, j)-1]++;
			}
		for (int k=0;k<9;k++)
			if (squareCounter[k]!=1) return false;
		return true;
	}

	public boolean numberExistsInRow(int row,int num)
	{
		for (int i=0;i<9;i++)
			if (board.get(row, i)==num) return true;
		return false;
	}

	public boolean numberExistsInColumn(int col,int num)
	{
		for (int i=0;i<9;i++)
			if (board.get(i, col)==num) return true;
		return false;
	}

	public boolean numberExistsInSquare(int row,int col,int num)
	{
		for (int i = row;i<row+3;i++)
			for (int j=col;j<col+3;j++)
			{
				if (board.get(i, j)==num) return true;
			}
		return false;
	}

	public void put(int x,int y,int num)
	{
		board.put(x, y, num);
	}

	public int get(int x,int y)
	{
		return board.get(x, y);
	}

	public Board getBoard()
	{
		return board;
	}

	public void  setBoard(Board board)
	{
		this.board=board;
	}

}
