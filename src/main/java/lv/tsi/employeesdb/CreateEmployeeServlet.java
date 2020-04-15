package lv.tsi.employeesdb;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.util.Objects;

@WebServlet(name = "CreateEmployeeServlet", urlPatterns = "/create")
public class CreateEmployeeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var id = Long.parseLong(request.getParameter("id"));
        var firstName = Objects.requireNonNull(request.getParameter("firstName"));
        var lastName = Objects.requireNonNull(request.getParameter("lastName"));
        var phoneNr = request.getParameter("phoneNr");
        var email = request.getParameter("email");
        var salary = BigDecimal.valueOf(Long.parseLong(request.getParameter("salary")));

        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (
                Connection conn = DriverManager.getConnection("jdbc:h2:~/employeedb;AUTO_SERVER=TRUE", "sa", "sa");
                PreparedStatement stmt = conn.prepareStatement("INSERT INTO EMPLOYEES (ID, FIRST_NAME, LAST_NAME, PHONE_NR, EMAIL, SALARY)" +
                        "VALUES (?,?,?,?,?,?)")
        ) {

            stmt.setLong(1, id);
            stmt.setString(2, firstName);
            stmt.setString(3, lastName);

            if (phoneNr == null) {
                stmt.setNull(4, Types.VARCHAR);
            } else {
                stmt.setString(4, phoneNr);
            }

            if (email == null) {
                stmt.setNull(5, Types.VARCHAR);
            } else {
                stmt.setString(5, email);
            }

            stmt.setBigDecimal(6, salary);

            stmt.executeUpdate();

            response.sendRedirect("/list");

        } catch (SQLException e) {
            response.sendError(500);
            e.printStackTrace();
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
