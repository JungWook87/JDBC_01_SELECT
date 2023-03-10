package edu.kh.jdbc1;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TEST{
	public static void main(String[] args){
    	Scanner sc = new Scanner(System.in);
        
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try{
        	Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@localhost:1521:XE";
            String user = "kh";
            String pw = "kh1234";
            
            conn = DriverManager.getConnection(url, user, pw);
            
            stmt = conn.createStatement();
            
            System.out.println("< 입력 받은 급여보다 많이 받는(초과) 직원만 조회 >");
            System.out.print("급여 입력 : ");
            int input = sc.nextInt();
            sc.nextLine();
            
            String sql = "SELECT EMP_ID, EMP_NAME, SALARY FROM EMPLOYEE"
            		+ " WHERE SALARY > " + input;
                       
			rs = stmt.executeQuery(sql);
            
            while(rs.next()){
            	String empId = rs.getString("EMP_ID");
                String empName = rs.getString("EMP_NAME");
                int salary = rs.getInt("SALARY");
                
                System.out.printf("사번 : %s / 이름 : %s / 급여 : %d\n", 
                				  empId, empName, salary);
            }
        }    
        catch(Exception e){
        	e.printStackTrace();
        } finally {
        	try{
            	if(rs != null) rs.close();
                if(stmt != null) stmt.close();
                if(conn != null) conn.close();
            } catch(SQLException e) {
            	e.printStackTrace();
            }    
        }    
    }
}  
