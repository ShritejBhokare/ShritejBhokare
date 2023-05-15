import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Pattern;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/SignUp")
public class SignUp extends GenericServlet{
 
	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String firstname=req.getParameter("fname");
		String lastname=req.getParameter("lname");
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		boolean flag=isValid(email);
		
 if(password.contains(firstname) || password.contains(lastname)){
	 
	    PrintWriter pw=res.getWriter();
		pw.print("Password Should not contain First Name or Last Name");
		
		RequestDispatcher rd=req.getRequestDispatcher("LogIn.html");
		rd.include(req, res);
		res.setContentType("text/html");
 }
 else if(flag==false){
	 
	    PrintWriter pw=res.getWriter();
		pw.print("Please Enter a Valid Email");
		
		RequestDispatcher rd=req.getRequestDispatcher("LogIn.html");
		rd.include(req, res);
		res.setContentType("text/html");
 }
 else {
	 
	 try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "Shritej@123");
			PreparedStatement ps=con.prepareStatement("INSERT INTO timepass.logindemo(Fname,Lname,Email,Password) VALUES(?,?,?,?)");
			
			ps.setString(1, firstname);
			ps.setString(2, lastname);
			ps.setString(3,email);
			ps.setString(4, password);
			
			ps.execute();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	 
  }
 
 }
	public static boolean isValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                            "[a-zA-Z0-9_+&*-]+)*@" +
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                            "A-Z]{2,7}$";
                              
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
  
}

