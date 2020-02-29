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




	@SuppressWarnings("unchecked")
	public void initialize(){
		showProcess=true;
		ss = new SudokuSolver();
		numbers= new TextField[9][9];
		Thread t = new Thread(new BoardUpdate());
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
				//l.setOnMouseClicked(new EditNumber(i,j,numbers[i][j]));
				gridPane.add(l, i, j);
			}
		t.start();
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
	new Thread(){
		public void run(){
			ss.Solve(ss.s.getBoard());
			}
		}.start();
	}

	private class EditNumber implements EventHandler<MouseEvent>{

		int x,y;
		Label l;

		public EditNumber(int x,int y,Label l)
		{
			this.x=x; this.y=y; this.l=l;
		}

		@Override
		public void handle(MouseEvent arg0) {
			l.setText("");
			TextField t = new TextField();
			t.setBackground(Background.EMPTY);
			t.setFont(new Font("Arial",20));
			Platform.runLater(new Runnable(){
				@Override
				public void run() {
					t.requestFocus();
				}

			});
			gridPane.add(t, x, y);
			while (t.isFocused());
			if (t.getText()!=""&&t.getText()!=null)
			{
				l.setText(t.getText());
				ss.s.put(x, y, Integer.parseInt(t.getText()));
			}

		}
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
				} catch(InterruptedException e) {}
			}

		}

	}

	@FXML
	public void showProc(){
		Platform.runLater(()->showProcess=showProcessBox.isSelected());

	}

}
