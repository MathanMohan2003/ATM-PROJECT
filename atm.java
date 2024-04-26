package atm;
import java.sql.*;
import java.util.Scanner;

import com.mysql.jdbc.Driver;
public class atm {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm","root","");
			Statement stmt=con.createStatement();
			Scanner sc = new Scanner(System.in);
			System.out.println("WELCOME TO SBI");
			System.out.println("ENTER YOUR PIN NUMBER");
			int pin = sc.nextInt();
			ResultSet rs = stmt.executeQuery("SELECT * FROM atm WHERE ac_no=" + pin);
			String Fname=null;
			String Lname=null;
			int bal=0;
			int count=0;
			while(rs.next())
			{
				Fname=rs.getString(2);
				Lname=rs.getString(3);
				bal= rs.getInt(4);
				count++;
			}
			
			int choice;
			int amount=0;
			int Wamount=0;
			if(count>0) {
				System.out.println("Hello "+Fname+" "+Lname);
				 while (true)
				 {
					 System.out.println();
					 System.out.println("Enter 1 to check balance");
					 System.out.println("Enter 2 to Add Amount");
					 System.out.println("Enter 3 to withdraw");
					 System.out.println("Enter 4 to recipet");
					 System.out.println("Enter 5 to Exit/Quit");
					 System.out.println();
					 System.out.println("ENTER YOUR CHOICE");
					 choice  = sc.nextInt();
					 
					 switch(choice)
					 {
					 case 1:
						 System.out.print("your balance is "+bal);
						 System.out.println();
						 break;
						 
					 case 2:
						 System.out.println("Please enter the amount you want to add");
						  amount = sc.nextInt();
						 bal = bal+amount;
						 int bal1 = stmt.executeUpdate("UPDATE atm SET bal = " + bal + " WHERE ac_no = " + pin);
						 System.out.println("amount deposited succesfully! your current balance is "+bal);
						 System.out.println();
						 break;
					 case 3:
						 System.out.println("please enter amount you want to withdraw");
						 Wamount = sc.nextInt();
						 if (Wamount > bal) {
							 System.out.println("Insufficient balance");
						 }
						 else {
						 bal = bal-Wamount;
						 int sub = stmt.executeUpdate("UPDATE atm SET bal = " + bal + " WHERE ac_no = " + pin);
						 System.out.println(Wamount +" withdrawn successfully your current balance is "+bal);
						 System.out.println();
						 }
						 break;
						 
					 case 4:
						 System.out.println("Thank you for coming !!!!");
						 System.out.println();
						 System.out.println("your current balance is : "+bal );
						 System.out.println("Amount deposited :"+amount);
						 System.out.println("Amount withdrawn :"+Wamount);
						 break;
						 }
					 
					 if (choice==5)
					 {
						 System.out.println("THANK YOU FOR BANKING ACCOUNT");
						 break;
					 }
				 }
			}
			else
			{
				System.out.print("WRONG PIN NUMBER!");
			}
		}
		catch(Exception e) {
         System.out.print(e);
		}

	}

}
