import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.remote.DesiredCapabilities;
//import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.*;
public class HTMLUnitTester 
{
 private String userURL;
 public HTMLUnitTester(String u)
 {
 	userURL = u;
 }
 public static String getOrganization(String s)
 {
	 if(s.contains("-"))
	 {
		 return s.substring(s.indexOf("-") + 1);
	 }
	 if(s.contains("—"))
	 {
		 return s.substring(s.indexOf("—") + 1);
	 }
	return null;
 }

 public String getTitle()
 {
 	WebDriver d = new ChromeDriver();
 	d.get(userURL);
	 d.close();
 	return d.getTitle();
 }
 public static void main (String args[]) throws Exception
 {

 }
}
