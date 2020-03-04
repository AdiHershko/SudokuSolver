package solver;

import application.SudokuController;
import game.Board;
import game.Sudoku;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

public class SudokuSolver {


	public static Sudoku s;
	public static boolean finished=false;

	public SudokuSolver(){
		s = new Sudoku(new Board(new int[][] {
			{0, 5, 3, 0, 0, 0, 7, 9, 0},
            {0, 0, 9, 7, 8, 2, 6, 0, 0},
            {0, 0, 0, 5, 0, 3, 0, 0, 0},
            {0, 0, 0, 4, 0, 6, 0, 0, 0},
            {0, 4, 0, 0, 0, 0, 0, 6, 0},
            {8, 0, 5, 1, 0, 9, 3, 0, 2},
            {0, 0, 8, 9, 3, 1, 4, 0, 0},
            {9, 0, 0, 0, 0, 0, 0, 0, 6},
            {0, 0, 4, 0, 0, 0, 8, 0, 0} }));
	}



	public boolean canPutNumber(Board board,int row,int col,int num){
		 if (!(s.numberExistsInRow(row, num)||s.numberExistsInColumn(col, num)||s.numberExistsInSquare(row-row%3, col-col%3,num))) return true;
		 return false;
	}


	public boolean Solve(Board board)
	{
		int row=-1;
		int col=-1;
		boolean brk=false;
		for (int i=0;i<9;i++)
		{
			for (int j=0;j<9;j++)
				if (s.getBoard().get(i, j)==0) {
					row=i;
					col=j;
					brk=true;
					break;
				}
			if (brk) break;
		}
		   for (int num = 1; num <= 9; num++)
		    {
		        if (canPutNumber(board, row, col, num))
		        {
		        	if (SudokuController.showProcess)
		        		SudokuController.numbers[row][col].setStyle("-fx-background-color: yellow;");
		            s.getBoard().put(row, col, num);
		            if (SudokuController.showProcess)
		            {
		            try{
		            	Thread.sleep(1);
		            }catch(InterruptedException e) { }
		            }
		            if (s.isGameOver()){ finished=true;
		            if (SudokuController.showProcess)
		            	SudokuController.numbers[row][col].setStyle("-fx-background-color: #00CC00;");
		            	return true;
		            }
		            if (Solve(s.getBoard()))
		            {
		            	if (SudokuController.showProcess)
		            		SudokuController.numbers[row][col].setStyle("-fx-background-color: #00CC00;");
		                return true;
		            }
		            else
		            {
		                s.getBoard().put(row, col, 0);
		            }
		        }
		    }
		    return false;
	}


	public static void main(String[] args)
	{
		SudokuSolver s = new SudokuSolver();
		s.Solve(s.s.getBoard());
		System.out.println(s.s.getBoard());
	}


}
