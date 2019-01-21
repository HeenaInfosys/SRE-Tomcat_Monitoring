package com.psl.main;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InventoryDAO {
	
	public static boolean addDtls1 = false;
	public static boolean addDtls2 = false;
	public static String price 	   = null;
	public static String manufacturer = null;
	public static String color = null;
	public static String carName = null;
	
	public static ArrayList DispList1(){
		
		InventoryBean l_ref = new InventoryBean();
		ArrayList list1 = new ArrayList();
		

		l_ref.setManufacturer("Tata");
		l_ref.setCarName("Nano");
		l_ref.setColorsAvailable("Red, White");
		l_ref.setPrice("6000");

		list1.add(0, l_ref);

		InventoryBean l_ref_1 = new InventoryBean();

		l_ref_1.setManufacturer("Maruti Suzuki");
		l_ref_1.setCarName("Alto");
		l_ref_1.setColorsAvailable("Red, White");
		l_ref_1.setPrice("8000");

		list1.add(1, l_ref_1);

		InventoryBean l_ref_2 = new InventoryBean();

		l_ref_2.setManufacturer("Hyundai");
		l_ref_2.setCarName("i10");
		l_ref_2.setColorsAvailable("Red, White");
		l_ref_2.setPrice("9000");

		list1.add(2, l_ref_2);
		
		if(addDtls1){
			InventoryBean l_ref_5 = new InventoryBean();

			l_ref_5.setManufacturer(manufacturer);
			l_ref_5.setCarName(carName);
			l_ref_5.setColorsAvailable(color);
			l_ref_5.setPrice(price);

			list1.add(3, l_ref_5);
		}

		return list1;
	}
	
	public static ArrayList DispList2(){
		
		ArrayList list2 = new ArrayList();
		InventoryBean l_ref_3 = new InventoryBean();

		l_ref_3.setManufacturer("Ford");
		l_ref_3.setCarName("Figo");
		l_ref_3.setColorsAvailable("Red, White");
		l_ref_3.setPrice("40000");

		list2.add(0, l_ref_3);

		InventoryBean l_ref_4 = new InventoryBean();

		l_ref_4.setManufacturer("Volkswagen");
		l_ref_4.setCarName("Polo");
		l_ref_4.setColorsAvailable("Red, White");
		l_ref_4.setPrice("42000");

		list2.add(1, l_ref_4);
		
		if(addDtls2){
			InventoryBean l_ref_6 = new InventoryBean();

			l_ref_6.setManufacturer(manufacturer);
			l_ref_6.setCarName(carName);
			l_ref_6.setColorsAvailable(color);
			l_ref_6.setPrice(price);

			list2.add(2, l_ref_6);
		}

		return list2;
	}

	public static void InvLogic() {

		DispList1();
		DispList2();
	
	}
	
	public static void showModifiedDtls (
			String p_manufacturer		,
			String p_carName			,
			String p_color				,
			String p_price            	) {

		price 		 = p_price ;
		manufacturer = p_manufacturer ;
		color  		 = p_color ;
		carName  	 = p_carName ;
		
		int l_price = Integer.parseInt(p_price);
		 
		if(l_price < 10000){
			addDtls1 = true;
		}else{
			addDtls2 = true;
		}
		DispList1();
		DispList2();
	}
    
	public static boolean validateFields(String field){
		
		String regexpChars = "^[a-zA-Z0-9]{1,}$";
		Matcher matcher;
		
		if(field.length()<1 || field.equals("")){
			return false;
		}
		
		// to be uncommented for Special Character handling
		Pattern pattern = Pattern.compile(regexpChars);
		matcher = pattern.matcher(field);
		if(!matcher.matches())
		{
		   return false;
		}
		
		return true;
	}
	
    public static boolean validatePrice(String price){
		
		String regexpChars = "((\\d{1,6})(((\\.)(\\d{0,2})){0,1}))";
		Matcher matcher;
		
		if(price.length()<1 || price.equals("")){
			return false;
		}
		
		Pattern pattern = Pattern.compile(regexpChars);
		matcher = pattern.matcher(price);
		if(!matcher.matches())
		{
		   return false;
		}
		
		// to be uncommented for price range check
		int val = Integer.parseInt(price);
		
		if(val <= 0){
			return false;
		}
		
		return true;
	}
    
    public static void main(String args[]){
	InventoryDAO obj = new InventoryDAO();
	boolean result = obj.validatePrice("0");
	System.out.println("validation is :"+result);
}
    
}
