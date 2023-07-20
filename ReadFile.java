package com.java.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadFile {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	System.out.println("Enter choice :\n1. Read Data \n2. update data \n3. Insert data \n4. Delete data");
    	int choice =sc.nextInt();
    	System.out.println();
    	switch(choice) {
    	case 1:
    		readdata();
    		break;
    	case 2:
    		updatedata();
    		break;
    	case 3:
    		insertdata();
    		break;
    	case 4:
    		deletedata();
    		break;
    	default:
    		System.out.println("Wrong input.......");
    	}
    	System.out.println();
    	System.out.println("Program terminated.......");
    	
    	
    }
    
    
public static void readdata() {
        try {
        	
        	ArrayList<students> arList = new ArrayList<>();
        	
            String url = "jdbc:postgresql://localhost/postgres";
            String userName = "database username";
            String pass = "database password";

            Connection con = DriverManager.getConnection(url, userName, pass);
            Statement st = con.createStatement();
            String query = "SELECT * FROM public.students";
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                students stu = new students();
                
				String name = rs.getString(1);
				int age = rs.getInt(2);
				String admNo = rs.getString(3);
				String standard= rs.getString(4);
				
				
				stu.setName(name);
				stu.setAge(age);
				stu.setAdmissionno(admNo);
				stu.setStandard(standard);
				
				arList.add(stu);
            }
			System.out.println("Name | Age | Admission Number | Standard");
			System.out.println("----------------------------------------");
			 for (students stu : arList) {
			 	System.out.println(stu.getName()+ " | "+stu.getAge()+ " | "+stu.getAdmissionno()+ " | "+ stu.getStandard());
        }

            st.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        
    }



public static void updatedata() {
    try {
    	
        String url = "jdbc:postgresql://localhost/postgres";
        String userName = "postgres";
        String pass = "Harish@532";

        Connection con = DriverManager.getConnection(url, userName, pass);
        Statement st = con.createStatement();
        String query = "update public.students set age = 15 where name='Surendra Reddy'";
        ResultSet rs = st.executeQuery(query);

        st.close();
        con.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    
}




public static void insertdata() {
		  try {
		    	
		        String url = "jdbc:postgresql://localhost/postgres";
		        String userName = "postgres";
		        String pass = "Harish@532";

		        Connection con = DriverManager.getConnection(url, userName, pass);
		        PreparedStatement pst = con.prepareStatement("insert into public.students (name, age,admissionno, standard) values (?,?,?,?)");
		       
		        Scanner sc = new Scanner(System.in);
		        
		       
		        int i=0;
		        while(i<2) {
		        	
		        	System.out.println("Enter name :");
		        	String name=sc.nextLine();
		        	System.out.println("Enter age :");
			        int age=sc.nextInt();
			        sc.nextLine();
			        System.out.println("Enter admission number :");
			        String admno = sc.nextLine();
			        
			        System.out.println("Enter standard :");
			        String standard = sc.nextLine();
		        	
		        		pst.setString(1, name);
				        pst.setInt(2, age);
				        pst.setString(3, admno);
				        pst.setString(4, standard);
				        boolean rs = pst.execute();
				        System.out.println("Inserted ...");
				        i++;
		        }
		        
		        pst.close();
		        con.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
	}

public static void deletedata() {
	  try {
	    	
	        String url = "jdbc:postgresql://localhost/postgres";
	        String userName = "postgres";
	        String pass = "Harish@532";

	        Connection con = DriverManager.getConnection(url, userName, pass);
	        PreparedStatement pst = con.prepareStatement("delete from public.students where admissionno = ?");
	       
	        Scanner sc = new Scanner(System.in);
	        
	       
	        int i=0;
	        while(i<2) {
		        System.out.println("Enter admission number :");
		        String admno = sc.nextLine();
			        pst.setString(1,admno);
			        boolean rs = pst.execute();
			        System.out.println("Deleted.....");
			        i++;
	        }
	        
	        pst.close();
	        con.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
}


}


class students{
    
    private String name;
    private int age;
    private String admissionno;
    private String standard;

    public students() {
    }

    public students( String name, int age, String admissionno, String standard) {
        
        this.name = name;
        this.age = age;
        this.admissionno = admissionno;
        this.standard = standard;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAdmissionno() {
        return admissionno;
    }

    public void setAdmissionno(String admissionno) {
        this.admissionno = admissionno;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }
}
