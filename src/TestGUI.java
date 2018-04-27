import javafx.scene.control.Button;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.*;
public class TestGUI extends Application
{
	public static void main (String args[])
	{
		 launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception 
	{
		GridPane g1 = new GridPane();
		Button b = new Button("Testing");
		b.setOnAction(e -> System.out.println("pressed"));
		g1.setAlignment(Pos.CENTER);
		g1.add(b, 0, 0);
		Scene s = new Scene(g1, 300, 400);
		primaryStage.setScene(s);
		primaryStage.show();
	}
}
