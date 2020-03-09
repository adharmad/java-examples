package com.example.javaexamples.date;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDateFormat {
    
    
    public static void main(String[] args)
    {
        String dateFormat1 = "YYYY-MM-dd";
        String dateFormat2 = "yyyy-MM-dd";
        
        String date1 = "1234-44-77";
        String date2 = "1234-444-77";
        String date3 = "12344-44-77";
        String date4 = "1234-44-776";
        String date5 = "123a-44-77";
        String date6 = "1234-4b-77";
        String date7 = "1234-44-7c";
        String date8 = "asdasdas";
        String date9 = " ";
        String date10 = "44-77-1234";
        String date11 = "abcd-ef-ghij";
        String date12 = "1234-44-77 ";
        String date13 = " 1980-01-01";
        
        String[] dateArray = {date1, date2, date3, date4, date5, date6, date7, date8, date9, date10, date11,
                date12, date13};
        
        
        for (int i=0 ; i < dateArray.length ; i++)
        {
            System.out.println(dateArray[i] + " --> " + isDateValid(dateArray[i], dateFormat1));
        }
        
        
        //tryDate(date5, dateFormat2);
        
        
    }
    
    public static void tryDate(String strValue, String dateFormat)
    {
        try
        {
            SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
            Date d = sdf.parse(strValue);
            System.out.println(d);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
	public static boolean isDateValid(String strValue1, String dateFormat1)
	{
	    String strValue = strValue1.trim();
	    String dateFormat = dateFormat1.trim();
	    
	    if (strValue.length() != dateFormat.length())
	    {
	        return false;
	    }
	    
	    int len = dateFormat.length();
	    int idx = 0;
	    
	    while (idx < len) 
	    {
	        if (dateFormat.charAt(idx) == 'y' || dateFormat.charAt(idx) == 'Y' || 
	                dateFormat.charAt(idx) == 'm' || dateFormat.charAt(idx) == 'M' ||
	                dateFormat.charAt(idx) == 'd' || dateFormat.charAt(idx) == 'D') 
	        {
	            char ch = strValue.charAt(idx);
	            
	            if (!Character.isDigit(ch)) {
	                return false;
	            }
	            
	        }
	        idx++;
	    }

	    return true;
	}

}
