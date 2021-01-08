package db;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletDownload extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	{
		try
		{
			resp.sendRedirect("/start");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
	{
		try
		{
			resp.setContentType("text/plain");
			String name = (req.getParameter("date").equals(""))?"badDate":req.getParameter("date");
			resp.setHeader("Content-Disposition", "attachment;filename=" + name + ".txt");
			OutputStream os = resp.getOutputStream();

			ResultSet resultSet = (ResultSet) req.getAttribute("result");

			try
			{
				int cost = 0;
				while (resultSet.next())
				{
					String s = resultSet.getString("service") + " " + resultSet.getString("count") + " " + resultSet.getString("name") + " " + resultSet.getString("date") + " " + resultSet.getString("cost") + "\n";
					os.write(s.getBytes(), 0, s.length());
					cost += resultSet.getInt("cost") * resultSet.getInt("count") ;
				}
				String s = "----------------\nTotal cost: " + cost;
				os.write(s.getBytes(), 0, s.length());
				os.flush();
				os.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			resp.sendRedirect("/start");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}