package banking;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Scanner;

public class BankingSystemMenu {
	Scanner sc = new Scanner(System.in);
	 void menu(Connection con, Statement stmt, String temp) throws Exception {
		 try {
				Thread.sleep(3000);
			}
			catch(Exception e1 ){
				throw new Exception("devil's exception");
				
			}
		 Bankk b = new Bankk();
		System.out.println("welcome sir/mam /20s " + new Date());
		System.out.println("|| ENTER 1 FOR MAKE DEPOSITE \n" + "|| ENTER 2 FOR WITHDRAWL \n"
				+ "|| ENTER 3 TO SET NEW PASSWORD \n" + "|| ENTER 4 TO SEE PERSONAL DETAILS \n"
				+ "|| ENTER 5 TO SEE CURRENT BALANCE \n" + "|| ENTER 6 TO EXIT \n");
		int choice1 = sc.nextInt();
		int choice;
		if (choice1 > 7 && choice1 < 0) {
			System.out.println("invalid input");
			System.exit(0);
		}
		choice = choice1;

		try {
			switch (choice) {
			case 1:
				System.out.println("|| enter AMOUT TO DEPOSITE \n ");
				double newAmount = sc.nextDouble();
				String sql4 = "update cred set balance =  balance + " + newAmount + " where userid = '" + temp + "'";
				int rs = stmt.executeUpdate(sql4);
				if (rs > 0) {
					System.out.println("insertion complete");
					menu(con, stmt, temp);
				} else {
					System.out.println("some error occour");
					menu(con, stmt, temp);
					
				}
				System.out.println(new Date());
				break;

			case 2:
				System.out.println("enter AMOUNT THAT YOU WANT TO WITHDRAWL || ");
				Double Wamount = sc.nextDouble();
				String sql6 = "select balance from cred where userid ='" + temp + "'";
				ResultSet rs1 = stmt.executeQuery(sql6);
				if (rs1.next()) {
					double currentBalance = rs1.getDouble("balance");
					if (currentBalance >= Wamount) {
						double updateBalance = currentBalance - Wamount;
						String sql7 = "update cred set balance = " + updateBalance + " where userid ='" + temp + "'";
						int rset = stmt.executeUpdate(sql7);
						if (rset > 0) {
							System.out.println("update complete ");
							menu(con, stmt, temp);
							
						} else {
							System.out.println("something went wrong");
							menu(con, stmt, temp);
						}
					} else {
						System.out.println("insuffeciant balance");
						menu(con, stmt, temp);
					}

					break;
				}
 
			case 3:
				System.out.println("enter new password you want to set");
				String newPass = sc.next();
				String sql10 =  "update cred set password = '"+newPass+"' where userid = '"+temp+"'";
				int resu = stmt.executeUpdate(sql10);
				if(resu>0) {
					System.out.println("password updated");
					System.out.println("relogin please");
					b.login1(con, stmt);
					
				}
				else 
				System.out.println("some went wrong");
				menu(con, stmt, temp);
				break;
			case 4:
				System.out.println("your details");
				String sql8 = "select * from cred where userid = '"+temp+"'";
				ResultSet rss = stmt.executeQuery(sql8);
				if (rss.next()) {
					String useridd = rss.getString("userid");
					String pass = rss.getString("password");
					String cname = rss.getString("coustmerName");
					double balance1 = rss.getDouble("balance");
					int mobile = rss.getInt("mobile");
					String email = rss.getString("email");
					System.out.println("  userID: " + useridd + ";\n  Name: " + cname + ";\n  Balance Available: "
							+ balance1 + ";\n  Mobile: " + mobile + ";\n  Email: " + email);
					menu(con, stmt, temp);
				}
				break;
			case 5:
				String sql9 = "select balance from cred where userid ='" + temp + "'";
				ResultSet rs2 = stmt.executeQuery(sql9);
				if (rs2.next()) {
					double currentBalance = rs2.getDouble("balance");
					System.out.println(currentBalance);
					menu(con, stmt, temp);
				}
			case 6:
				System.out.println("Be back soone");
//				System.exit(0);
				Thread.sleep(800);
				b.login1(con, stmt);
				
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

}
