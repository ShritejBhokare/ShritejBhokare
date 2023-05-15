import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/SignIn")
public class LogIn extends GenericServlet{

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email=req.getParameter("Email");
		String password=req.getParameter("Password");
		
		String email1="sumit@gmail.com";
		String pass1="sumit@123";
		
		if(email.equals(email1) && password.equals(pass1))
		{
			RequestDispatcher rd=req.getRequestDispatcher("welcome.html");
			rd.forward(req, res);
		}
		else 
		{
			PrintWriter pw=res.getWriter();
			pw.print("Invalid Credentials Please Enter Valid Credentials");
			
			RequestDispatcher rd=req.getRequestDispatcher("LogIn.html");
			rd.include(req, res);
			res.setContentType("text/html");
			//rd.forward(req, res);
		}
		
	}

	
}
