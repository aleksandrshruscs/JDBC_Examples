package lv.tsi.employeesdb;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;

@WebServlet(name = "ListEmployeesServlet", urlPatterns = "/list")
public class ListEmployeesServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String searchRequest;

        if ((searchRequest = request.getParameter("search")) != null) {
            var searchList = new ArrayList<Employee>();
            try (
                    Connection conn = DriverManager.getConnection("jdbc:h2:~/employeedb;AUTO_SERVER=TRUE", "sa", "sa");
                    PreparedStatement stmt = conn.prepareStatement("SELECT * FROM EMPLOYEES WHERE " +
                            "(LAST_NAME, FIRST_NAME, ID, EMAIL, SALARY, PHONE_NR) LIKE ?;")
            ) {
                searchRequest = "%" + searchRequest + "%";
                stmt.setString(1, searchRequest);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    long id = rs.getLong("id");
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    String phoneNumber = rs.getString("phone_nr");
                    BigDecimal salary = rs.getBigDecimal("salary");
                    String email = rs.getString("email");

                    var emp = new Employee();
                    emp.setId(id);
                    emp.setFirstName(firstName);
                    emp.setLastName(lastName);
                    emp.setPhoneNr(phoneNumber);
                    emp.setSalary(salary);
                    emp.setEmail(email);

                    searchList.add(emp);
                }
                if (searchList.size() > 0) {
                    request.setAttribute("searchList", searchList);
                } else {
                    request.setAttribute("notFound", "Sorry, I found nothing");
                }

            } catch (SQLException e) {
                response.sendError(500);
                e.printStackTrace();
            }
        }

        try (
                Connection conn = DriverManager.getConnection("jdbc:h2:~/employeedb;AUTO_SERVER=TRUE", "sa", "sa");
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM employees")
        ) {
            var list = new ArrayList<Employee>();
            while (rs.next()) {
                long id = rs.getLong("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String phoneNumber = rs.getString("phone_nr");
                BigDecimal salary = rs.getBigDecimal("salary");
                String email = rs.getString("email");

                var emp = new Employee();
                emp.setId(id);
                emp.setFirstName(firstName);
                emp.setLastName(lastName);
                emp.setPhoneNr(phoneNumber);
                emp.setSalary(salary);
                emp.setEmail(email);

                list.add(emp);
            }

            request.setAttribute("list", list);
            request.getRequestDispatcher("WEB-INF/list.jsp").include(request, response);

        } catch (SQLException e) {
            response.sendError(500);
            e.printStackTrace();
        }
    }
}