import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Register")
public class Register extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // Retrieve form parameters
        String mail = request.getParameter("mail");
        String pwd = request.getParameter("pwd");
        String name = request.getParameter("name");

        // Database connection details
        String url = "jdbc:mysql://localhost:3306/tespo";
        String username = "root";
        String password = "Somya@0407"; // Replace with your MySQL password

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the database connection
            conn = DriverManager.getConnection(url, username, password);

            // Prepare SQL statement to insert user into database
           String sql = "INSERT INTO info (name, mail, pwd) VALUES (?, ?, ?)";
pstmt = conn.prepareStatement(sql);
pstmt.setString(1, name);
pstmt.setString(2, mail);
pstmt.setString(3, pwd);


            // Execute the insert statement
            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted > 0) {
                out.println("<html><body><h3>Registration successful!</h3></body></html>");
            } else {
                out.println("<html><body><h3>Registration failed. Please try again.</h3></body></html>");
            }
        } catch (ClassNotFoundException | SQLException e) {
            out.println("Error during registration: " + e.getMessage());
        } finally {
            // Close all database resources
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                out.println("Error closing database resources: " + e.getMessage());
            }
        }
    }
}
