/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author VICTUS
 */
public class LoginCheck extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
try{
       
            String mail = request.getParameter("mail");
            String pwd = request.getParameter("pwd");
            
            
            out.println("value="+mail);
            out.println("value="+pwd);
            //Load the driver class
            Class.forName("com.mysql.cj.jdbc.Driver");

            //Make the statement object
             //Create the connection
                    Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tespo", "root", "Somya@0407");
                //Make the statement object
                Statement smt =  cn.createStatement();
                
                //execute query
                // ResultSet rs=smt.executeQuery("select * from INFO where mail='"+mail+"' and password='"+pwd+"'");
                ResultSet rs=smt.executeQuery("select * from infoo where mail='"+mail+"' and password='"+pwd+"'");
              
out.println(rs.next());
                if(rs.next())
                {
                    
                    RequestDispatcher rd=request.getRequestDispatcher("Home");
                    
                    
                    //HttpSession hs=request.getSession(true);
                   //hs.setAttribute("mail",mail);
                   // hs.setAttribute("pwd", pwd);
                    //RequestDispatcher rd=request.getRequestDispatcher("Home");
                    //rd.forward(request,response);
                }
                
                else
                {
                    out.println("<h2>Invalid User ID or password</h2>");
                    RequestDispatcher rd=request.getRequestDispatcher("index.html");
                    rd.include(request, response);  
                }  
         
            
        }

        
       catch(Exception e) {
    // You can also log the exception details to a log file for better debugging.
    // Log.error("Exception occurred in LoginCheck servlet", e);
    out.println("An error occurred. Please check the server logs for more details.");
}

    }


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
