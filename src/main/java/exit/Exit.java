package exit;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class Exit extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		try
		{
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/barbershop?serverTimezone=Europe/Moscow&useSSL=false", "root", "password");
			PreparedStatement ps = connection.prepareStatement("update attendance set exitTime = ? where id_user = ?");
			java.util.Date dateNow = new java.util.Date();
			SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			ps.setString(1, formatForDateNow.format(dateNow));
			ps.setString(2, req.getSession().getAttribute("id_user").toString());
			ps.executeUpdate();
		}
		catch (SQLException exception)
		{
			exception.printStackTrace();
		}
		req.getSession().invalidate();
		for (Cookie c: req.getCookies())
			if (c.getName().equals("login") || c.getName().equals("password"))
			{
				c.setMaxAge(0);
				resp.addCookie(c);
			}
		resp.sendRedirect("/login");
	}
}
