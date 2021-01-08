package start;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;

public class Check extends HttpServlet
{
	private Connection connection;



	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	{
		Cookie[] cookie1 = req.getCookies();
		String[] s = new String[2];
		for (Cookie c : cookie1)
			if (c.getName().equals("login")) s[0] = c.getValue();
			else if (c.getName().equals("password")) s[1] = c.getValue();
		if (s[0] != null && s[1] != null) try
			{
				duplicate(req, resp, s);
				return;
			}
			catch (SQLException | IOException exception)
			{
				exception.printStackTrace();
			}
		for (Cookie c:cookie1)
		{
			c.setMaxAge(0);
			resp.addCookie(c);
		}
		try
		{
			resp.sendRedirect("/login");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private void duplicate(HttpServletRequest req, HttpServletResponse resp, String[] s) throws SQLException, IOException
	{
		try
		{
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/barbershop?serverTimezone=Europe/Moscow&useSSL=false", "root", "password");
		}
		catch (SQLException exception)
		{
			exception.printStackTrace();
		}
		PreparedStatement ps = connection.prepareStatement("select * from users where name = ? and passwordHashCode = ?");
		ps.setString(1, s[0]);
		ps.setString(2, String.valueOf(s[1].hashCode()));
		ResultSet resultSet;
		resultSet = ps.executeQuery();
		if (resultSet.next()) {
			Cookie[] cookies = {
					new Cookie("login", s[0]),
					new Cookie("password", s[1]),
			};
			for (Cookie cookie:cookies)
			{
				cookie.setMaxAge(31536000);
				resp.addCookie(cookie);
			}

			ps = connection.prepareStatement("insert into attendance(id_user, time) value (?,?)");
			ps.setInt(1, resultSet.getInt("id_users"));
			java.util.Date dateNow = new java.util.Date();
			SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			ps.setString(2, formatForDateNow.format(dateNow));
			ps.executeUpdate();
			req.getSession().setAttribute("id_user", resultSet.getInt("id_users"));
			req.getSession().setAttribute("access", resultSet.getInt("access"));
			resp.sendRedirect("/start");
			return;
		}
		else
		{
			for (Cookie c: req.getCookies())
				if (c.getName().equals("login") || c.getName().equals("password"))
				{
					c.setMaxAge(0);
					resp.addCookie(c);
				}
		}
		resp.sendRedirect("/login");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	{
		try
		{
			duplicate(req, resp, new String[] { req.getParameter("login"), req.getParameter("password") });
		}
		catch (SQLException | IOException exception)
		{
			exception.printStackTrace();
		}
	}
}
