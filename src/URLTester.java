import java.net.*;
import java.io.*;
import java.util.ArrayList;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
public class URLTester 
{
  private static URL url;
  private static ArrayList<String> arr;
  public URLTester(URL u)
  {
  	try
	{
		url = u;
		BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
		String inputLine;
		arr = new ArrayList<String>();
		boolean foundAuthor = false;
		while ((inputLine = in.readLine()) != null)
		{
			//System.out.println(inputLine.toUpperCase());
			if(inputLine.contains("author") && !foundAuthor)
			{
				foundAuthor = true;
			}
			arr.add(inputLine);
		}

		in.close();
		System.out.println(arr.size());
		if(arr.size() < 30)
			arr = format(arr);
	}
	catch(IOException e)
	{
		e.printStackTrace();
	}
  }

  public static String getDate()
  {
	     String date = null;
	     boolean foundDate = false;
	     //Making a first pass, prioritizing specific words
	     for(String s : arr)
	     {
	     	// System.out.println(s);
	    	  if(s.contains("updated") || s.contains("publish") || s.contains("date") && !foundDate)
	    	  {
		    	  date = findDate(s);
		    	  if((!date.equals("" + " " + "" + " " + "")) && (!foundDate))
		    	  {
		    		  foundDate = true;
		    		  break;
		    	  }
	    	  }	  
	     }
	     
	     if(!foundDate)
	     {
		     for(String s : arr)
		     {
		    	  if(s.contains("1") || s.contains("2") || s.contains("3") || s.contains("4") || s.contains("5") || s.contains("6") || s.contains("7") || s.contains("8") || s.contains("9") || s.contains("0"))
		    	  {
			    	  date = findDate(s);
			    	  if((!date.equals("" + " " + "" + " " + "")) && (!foundDate))
			    	  {
	                     break;
			    	  }
		    	  }	  
		     }
	     }
	     return date;
  }
  public static String findDate(String content)
  {
	  String month = "";
	  String day = "";
	  String year = "";
	  boolean monthFound = false;
	  int indexOfMonth = 0;
	      if(content.contains("January"))
	      {
			  month = "January"; 
			  monthFound = true;
			  indexOfMonth = content.indexOf("January");
	      }
	      else if(content.contains("February"))
	      {
			  month = "February";
			  monthFound = true;
			  indexOfMonth = content.indexOf("February");
	      }
	      else if(content.contains("March"))
	      {
			  month = "March";
			  monthFound = true;
			  indexOfMonth = content.indexOf("March");
	      }
	      else if(content.contains("April"))
	      {
			  month = "April";
			  monthFound = true;
			  indexOfMonth = content.indexOf("April");
			  //System.out.println(content);
	      }
	      else if (content.contains("Apr"))
		  {
		  	month = "April";
		  	monthFound = true;
		  	indexOfMonth = content.indexOf("Apr");
		  }
	      else if(content.contains("May"))
	      {
			  month = "May";
			  monthFound = true;
			  indexOfMonth = content.indexOf("May");
			 // System.out.println(content);
	      }
	      else if(content.contains("June"))
	      {
			  month = "June";
			  monthFound = true;
			  indexOfMonth = content.indexOf("June");
	      }
	      else if(content.contains("July"))
	      {
			  month = "July";
			  monthFound = true;
			  indexOfMonth = content.indexOf("July");
	      }
	      else if(content.contains("August"))
	      {
			  month = "August";
			  monthFound = true;
			  indexOfMonth = content.indexOf("August");
	      }
	      else if(content.contains("September"))
	      {
			  month = "September";
			  monthFound = true;
			  indexOfMonth = content.indexOf("September");
	      }
	      else if(content.contains("October"))
	      {
			  month = "October";
			  monthFound = true;
			  indexOfMonth = content.indexOf("October");
	      }
	      else if(content.contains("November"))
	      {
			  month = "November";
			  monthFound = true;
			  indexOfMonth = content.indexOf("November");
	      }
	      else if(content.contains("December"))
	      {
			  month = "December";
			  monthFound = true;
			  indexOfMonth = content.indexOf("December");
	      }
	  int lastIndexOfDay = 0;
      for(int i = 0; i < content.length(); i++)
	  {
    	      //First part: checking if character is number; second part: checking that there is a month in the string; third part: checking that the two parts are within 7 characters of each other
		  if(content.charAt(i) <= 57 && content.charAt(i) >= 48 && monthFound && !(day.length() >= 2) && Math.abs(i - indexOfMonth) <= 7)
		  {
		   day += content.charAt(i);
		   lastIndexOfDay = i;  
		  }
	  }
      for(int j = lastIndexOfDay + 1; j < content.length(); j++)
      {
		  if(content.charAt(j) <= 57 && content.charAt(j) >= 48 && monthFound && !(year.length() >= 4))
			  year += content.charAt(j);
      }
	  return month + " " + day + " " + year;
  }
  
  public static String findAuthor()
  {
  	String author = "";
    for(String s : arr)
	{
		if(s.contains("author") && s.contains("meta") && s.contains("content"))
		{
			//System.out.println(s);
			for(int i = s.indexOf("content") + 9; s.charAt(i) != '\"'; i++)
			{
				author += s.charAt(i);
			}
			break;
		}
	}
	if(author.contains(" ") && author.length() < 15)
	 return author;
    return "";
  }

  public static ArrayList<String> format(ArrayList<String> arr)
  {
	     //Some formatting for sites with video content? Checked only on https://www.cnn.com/2018/03/21/us/austin-explosions/index.html
	      ArrayList<String> newArr = new ArrayList<String>();
	      String newContent = "";
	    	  for(int i = 0; i < arr.get(0).length(); i++)
	    	  {
	    		  if(arr.get(0).charAt(i) == '/')
	    		  {
	    			  newContent += "/>";
	    			  newArr.add(newContent);
	    			  newContent = "";
	    		  }
	    		  else
	    			  newContent += arr.get(0).charAt(i);
	    	  }
	    	  return newArr;
	     
  }

  public static String getTitle()
  {
  	String title = "";
    for(String s: arr)
	{
		if(s.contains("<title>"))
		{
			//System.out.println(s);
			for(int i = s.indexOf("<title>") + 7; s.charAt(i) != '<'; i++)
			{
				//if((s.charAt(i) >= 65 && s.charAt(i) <= 90) || (s.charAt(i) >= 97 && s.charAt(i) <= 122) || s.charAt(i) == 32 || s.charAt(i) == 45)
				 title += s.charAt(i);
			}
			break;
		}
	}
	return title;
  }
  
 public static void main (String args[]) throws Exception
 {

 }
}
