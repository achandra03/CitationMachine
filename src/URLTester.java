import java.net.*;
import java.io.*;
import java.util.ArrayList;
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
			arr.add(inputLine.toUpperCase());
		}

		in.close();
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
	    	  if(s.contains("UPDATED") || s.contains("PUBLISHED") || s.contains("DATE") && !foundDate)
	    	  {
		    	  date = findDate(s);
		    	  if((!date.equals("" + " " + "" + " " + "")) && (!foundDate))
		    	  {
		    		  foundDate = true;
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
	      if(content.contains("JANUARY"))
	      {
			  month = "January"; 
			  monthFound = true;
			  indexOfMonth = content.indexOf("JANUARY");
	      }
	      else if(content.contains("FEBRUARY"))
	      {
			  month = "February";
			  monthFound = true;
			  indexOfMonth = content.indexOf("FEBRUARY");
	      }
	      else if(content.contains("MARCH"))
	      {
			  month = "March";
			  monthFound = true;
			  indexOfMonth = content.indexOf("MARCH");
	      }
	      else if(content.contains("APRIL"))
	      {
			  month = "April";
			  monthFound = true;
			  indexOfMonth = content.indexOf("APRIL");
			  //System.out.println(content);
	      }
	      else if (content.contains("APR"))
		  {
		  	month = "April";
		  	monthFound = true;
		  	indexOfMonth = content.indexOf("APR");
		  }
	      else if(content.contains("MAY"))
	      {
			  month = "May";
			  monthFound = true;
			  indexOfMonth = content.indexOf("MAY");
	      }
	      else if(content.contains("JUNE"))
	      {
			  month = "June";
			  monthFound = true;
			  indexOfMonth = content.indexOf("JUNE");
	      }
	      else if(content.contains("JULY"))
	      {
			  month = "July";
			  monthFound = true;
			  indexOfMonth = content.indexOf("JULY");
	      }
	      else if(content.contains("AUGUST"))
	      {
			  month = "August";
			  monthFound = true;
			  indexOfMonth = content.indexOf("AUGUST");
	      }
	      else if(content.contains("SEPTEMBER"))
	      {
			  month = "September";
			  monthFound = true;
			  indexOfMonth = content.indexOf("SEPTEMBER");
	      }
	      else if(content.contains("OCTOBER"))
	      {
			  month = "October";
			  monthFound = true;
			  indexOfMonth = content.indexOf("OCTOBER");
	      }
	      else if(content.contains("NOVEMBER"))
	      {
			  month = "November";
			  monthFound = true;
			  indexOfMonth = content.indexOf("NOVEMBER");
	      }
	      else if(content.contains("DECEMBER"))
	      {
			  month = "December";
			  monthFound = true;
			  indexOfMonth = content.indexOf("DECEMBER");
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
		if(s.contains("NAME") && s.contains("AUTHOR") && s.contains("META"))
		{
			System.out.println(s);
			for(int i = s.indexOf("CONTENT") + 9; s.charAt(i) != '\"'; i++)
			{
				author += s.charAt(i);
			}
			break;
		}
	}
	return author;
  }

  public static ArrayList<String> format(ArrayList<String> arr)
  {
	     //Some formatting for sites with video content? Checked only on https://www.cnn.com/2018/03/21/us/austin-explosions/index.html
	      ArrayList<String> newArr = new ArrayList<String>();
	      String newContent = "";
	    	  for(int i = 0; i < arr.get(0).length(); i++)
	    	  {
	    		  if(arr.get(0).charAt(i) == '>')
	    		  {
	    			  newContent += '>';
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
		if(s.contains("<TITLE>"))
		{
			for(int i = s.indexOf("<TITLE>") + 7; s.charAt(i) != '<'; i++)
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
