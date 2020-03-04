package application;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import solver.SudokuSolver;

public class SudokuController {

	SudokuSolver ss;

	@FXML
	private Pane pane;

	@FXML
	private GridPane gridPane;

	public static TextField[][] numbers;

	@FXML
	private CheckBox showProcessBox;

	public static boolean showProcess;


	private Thread t;




	@SuppressWarnings("unchecked")
	public void initialize(){
		showProcess=true;
		ss = new SudokuSolver();
		t = new Thread(new BoardUpdate());
		numbers= new TextField[9][9];
		for (int i=0;i<9;i++)
			for (int j=0;j<9;j++)
			{
				TextField l = new TextField(""+ss.s.get(i, j));
				l.setBackground(Background.EMPTY);
				Font f = new Font("Arial",20);
				l.setAlignment(Pos.CENTER);
				l.setFont(f);
				numbers[i][j]=l;
				l.setPadding(new Insets(20));
				gridPane.add(l, i, j);
			}
	}

	private void update(){
		for (int i=0;i<9;i++)
			for (int j=0;j<9;j++)
				numbers[i][j].setText(""+ss.s.get(i, j));
	}

	@FXML
	public void SolveSud(){
		solve();
		update();
	}


	public void solve(){
		t=new Thread(new BoardUpdate());
		for (int i=0;i<9;i++)
			for (int j=0;j<9;j++)
				ss.s.put(i, j, Integer.parseInt(numbers[i][j].getText()));
	new Thread(){
		public void run(){
			ss.Solve(ss.s.getBoard());
			}
		}.start();
		t.start();
	}

	private class BoardUpdate implements Runnable{

		@Override
		public void run() {
			while (true)
			{
			Platform.runLater(new Runnable(){

				@Override
				public void run() {
					for (int i=0;i<9;i++)
						for (int j=0;j<9;j++)
							numbers[i][j].setText(""+ss.s.get(i, j));
				}

			});

				try {
					Thread.sleep(100);
				} catch(InterruptedException e) {
					return;
				}
			}

		}

	}


	@FXML
	public void showProc(){
		Platform.runLater(()->showProcess=showProcessBox.isSelected());

	}

	@FXML
	public void reset(){
		t.interrupt();
		ss = new SudokuSolver();
		for (int i=0;i<9;i++)
			for (int j=0;j<9;j++)
				numbers[i][j].setText(""+ss.s.get(i, j));
		for (int i=0;i<9;i++)
			for (int j=0;j<9;j++)
				numbers[i][j].setStyle("-fx-backgound-color: grey;");

	}

	@FXML
	public void clear(){
		t.interrupt();
		ss = new SudokuSolver();
		for (int i=0;i<9;i++)
			for (int j=0;j<9;j++)
				ss.s.put(i, j, 0);
		for (int i=0;i<9;i++)
			for (int j=0;j<9;j++)
				numbers[i][j].setText(""+ss.s.get(i, j));
		for (int i=0;i<9;i++)
			for (int j=0;j<9;j++)
				numbers[i][j].setStyle("-fx-backgound-color: grey;");
	}

}
