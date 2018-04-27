import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import java.net.*;
import java.io.*;
public class MainGUI extends Application
{
    URL userUrl;
	Scene information;

	public String getMonth(String s)
    {
        return s.substring(0, s.indexOf(' '));
    }

    public String getDay(String s)
    {
        return s.substring(s.indexOf(' ') + 1, s.indexOf(' ') + 3);
    }
	public static void main (String args[])
	{
        System.setProperty("webdriver.chrome.driver", "/Users/arnavchandra/Downloads/ChromeDriver/chromedriver");
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception 
	{
	 primaryStage.setTitle("Citation Machine");
	 GridPane grid1 = new GridPane();
	 grid1.setAlignment(Pos.CENTER);
	 grid1.setHgap(10);
	 grid1.setVgap(10);
	 grid1.setPadding(new Insets(20, 0, 0, 0));
	 Label l = new Label("Welcome. Please enter your URL.");
	 grid1.add(l, 0, 0);
	 TextField urlField = new TextField();
	 grid1.add(urlField, 0, 1);
	 Button submit = new Button("OK");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 0, 0, 0));
        Text scenetitle = new Text("Welcome");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label titleLabel = new Label("Article Title:");
        grid.add(titleLabel, 0, 1);

        TextField title = new TextField();
        grid.add(title, 1, 1);

        Label authorLabel = new Label("Author:");
        grid.add(authorLabel, 0, 2);

        TextField author = new TextField();
        grid.add(author, 1, 2);

        Label webTitleLabel = new Label("Website Title:");
        grid.add(webTitleLabel, 0, 3 );

        TextField webTitle = new TextField();
        grid.add(webTitle, 1, 3);

        Label publisherLabel = new Label("Website Publisher:");
        grid.add(publisherLabel, 0, 4);

        TextField publisher = new TextField();
        grid.add(publisher, 1, 4);

        Label urlLabel = new Label("URL:");
        grid.add(urlLabel, 0, 5);

        TextField url = new TextField();
        grid.add(url, 1, 5);

        Label monthLabel = new Label("Month of Publication:");
        grid.add(monthLabel, 0, 6);

        TextField month = new TextField();
        grid.add(month, 1, 6);

        Label dayLabel = new Label("Day of Publication");
        grid.add(dayLabel, 0, 7);

        TextField day = new TextField();
        grid.add(day, 1, 7);

        Label yearLabel = new Label("Year of Publication:");
        grid.add(yearLabel, 0, 8);

        TextField year = new TextField();
        grid.add(year, 1, 8);

	 submit.setOnAction(e -> {
	                     boolean mal = false;
	                     try
                         {
                             userUrl = new URL(urlField.getText());
                             mal = false;
                         }
                         catch(MalformedURLException ex)
                         {
                             l.setText("Please enter a valid URL");
                             mal = true;
                         }
	                       if(!(urlField.getText().trim().equals("")) && !mal)
                           {
                               primaryStage.setScene(information);
                               url.setText(urlField.getText().trim());
                               URLTester urlT;
                               urlT = new URLTester(userUrl);
                               String date = urlT.getDate();
                               month.setText(getMonth(date));
                             //  day.setText(getDay(date));
                               title.setText(urlT.getTitle());

                           }
	                       else if(!mal)
                           {
                               l.setText("Please enter a URL");
                           }
	                     });
	 grid1.add(submit, 1, 1);


     Scene urlChecker = new Scene(grid1, 300, 150);
     information = new Scene(grid, 400, 500);
     primaryStage.setScene(urlChecker);
	 primaryStage.show();
	}

}
