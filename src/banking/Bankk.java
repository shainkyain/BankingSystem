package banking;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.sql.Connection;
import java.util.Date;
import java.util.Scanner;

public class Bankk {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		try {
			String url = "jdbc:mysql://localhost:3306/bank1";
			
			Connection con = DriverManager.getConnection(url, "2810", "2810");
			Statement stmt = con.createStatement();
			BankingSystemMenu bkm = new BankingSystemMenu();
			Bankk b = new Bankk();
			FirstView fv = new FirstView();
			fv.firstView();
			b.login1(con, stmt);
			
			
		}
		catch(Exception e) {
		System.out.println(e);
		}
	}
		void login1(Connection con, Statement stmt) throws SQLIntegrityConstraintViolationException {
			try{
				System.out.println("enter 1  to login and 2  to signup");
			BankingSystemMenu bkm = new BankingSystemMenu();
			int a = sc.nextInt();
			String temp;
			if (a == 1) {
				System.out.println("enter userid:");
				String userid1 = sc.next();
				temp = userid1;
				System.out.println("enter password:");
				String password1 = sc.next();
				String sql = "select * from cred where userid='" + userid1 + "'and password='" + password1 + "'";
				ResultSet rs = stmt.executeQuery(sql);
				if (rs.next()) {
					System.out.println("login sucessful");
					rs.close();
					bkm.menu(con, stmt, temp);

				} else {
					System.out.println("incorrect credentials");
					login1(con, stmt);
				}

			} else if (a == 2) {
				System.out.println("please provide us your some information");
				System.out.println("userid ");
				String userid1 = sc.next();
				temp = userid1;
				System.out.println("enter password");
				String password = sc.next();
				System.out.println("enter name");
				String name = sc.next();
				System.out.println("enter mobile no.");
				int mobile = sc.nextInt();
				System.out.println("enter email");
				String email = sc.next();
//		String sql1 ="insert into cred values('"+userid2+"','"+password"')";

				String sql1 = "insert into cred values ('" + userid1 + "','" + password + "','" + name + "',0.00,"
						+ mobile + ",'" + email + "')";

				int rowsAffected = stmt.executeUpdate(sql1);
				if (rowsAffected > 0) {
					System.out.println("Insertion successful. " + rowsAffected + " row(s) affected.");
					System.out.println("sighup complete please login now");
				login1(con, stmt);
				} else {
					System.out.println("Insertion failed.");
					login1(con, stmt);
				}

			} else {
				System.out.println("invalid input");
				System.exit(0);
			}
		}
			catch(SQLIntegrityConstraintViolationException e1) {
			throw new SQLIntegrityConstraintViolationException("please select another username");
		}
			catch (Exception e) {
			System.out.println(e);
		}
			finally {
				login1(con, stmt);
			}
		}
		}
	
	


