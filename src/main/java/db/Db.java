package db;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.Calendar;

public class Db extends HttpServlet{
	Connection connection;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	{
		try
		{
			char sw = req.getParameter("request").charAt(0);
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/barbershop?serverTimezone=Europe/Moscow&useSSL=false", "root", "password");
			switch (sw)
			{
				case '0' :
				{
					PreparedStatement preparedStatement = connection.prepareStatement("insert into users(name, passwordHashCode, access) values (?, ?, ?)");
					preparedStatement.setString(1, req.getParameter("name"));
					preparedStatement.setString(2, String.valueOf(req.getParameter("password").hashCode()));
					preparedStatement.setInt(3, Integer.parseInt(req.getParameter("access")));
					preparedStatement.executeUpdate();
					break;
				}
				case '1' :
				{
					PreparedStatement preparedStatement = connection.prepareStatement("delete from users where name = ?");
					preparedStatement.setString(1, req.getParameter("name"));
					preparedStatement.executeUpdate();
					break;
				}
				case '2' :
				case '3' :
				{
					Statement statement = connection.createStatement();
					String request = "select id_users, name, access from users " + ((req.getParameter("request").equals("2")) ? "" : ("where name = '" + req.getParameter("name") + "'"));
					statement.executeQuery(request);
					ResultSet resultSet = statement.getResultSet();
					StringBuilder stringBuilder = new StringBuilder()
							.append("<div class=\"row\"><div class=\"col l8 offset-l2\"><table id=\"table\" border=\"1\">")
							.append("<tr>")
							.append("<td>ID</td>")
							.append("<td>Имя</td>")
							.append("<td>Доступ</td>")
							.append("</tr>");
					while (resultSet.next())
						stringBuilder
								.append("<tr>")
								.append("<td>").append(resultSet.getString("id_users")).append("</td>")
								.append("<td>").append(resultSet.getString("name"  )).append("</td>")
								.append("<td>").append(resultSet.getString("access")).append("</td>")
								.append("</tr>");
					stringBuilder.append("</table></div></div>");
					req.getSession().setAttribute("table", stringBuilder.toString());
					try
					{
						resp.sendRedirect("/info");
						return;
					}
					catch (IOException e)
					{
						e.printStackTrace();
					}
					break;
				}
				case '4' :
				{
					PreparedStatement preparedStatement = connection.prepareStatement("select * from users where name = ?");
					preparedStatement.setString(1, req.getParameter("name"));
					ResultSet resultSet = preparedStatement.executeQuery();
					resultSet.next();
					String[] arr = {
							(req.getParameter("newName").equals(""))?resultSet.getString("name") : req.getParameter("newName"),
							(req.getParameter("newPassword").equals(""))?resultSet.getString("passwordHashCode") : req.getParameter("newPassword"),
							(req.getParameter("newAccess").equals(""))? String.valueOf(resultSet.getInt("access")) : req.getParameter("newAccess"),
					};

					preparedStatement = connection.prepareStatement("update users set name = ?, passwordHashCode = ?, access = ? where id_users = ?");
					preparedStatement.setString(1, arr[0]);
					preparedStatement.setString(2, String.valueOf(arr[1].hashCode()));
					preparedStatement.setInt(3, Integer.parseInt(arr[2]));
					preparedStatement.setInt(4, resultSet.getInt("id_users"));
					preparedStatement.executeUpdate();
					break;
				}
				case '5' :
				{
					PreparedStatement preparedStatement = connection.prepareStatement("insert into services(service, cost, description) values (?, ?, ?)");
					preparedStatement.setString(1, req.getParameter("service"));
					preparedStatement.setInt(2, Integer.parseInt(req.getParameter("cost")));
					preparedStatement.setString(3, req.getParameter("description"));
					preparedStatement.executeUpdate();
					break;
				}
				case '6' :
				{
					PreparedStatement preparedStatement = connection.prepareStatement("delete from services where service = ?");
					preparedStatement.setString(1, req.getParameter("service"));
					preparedStatement.executeUpdate();
					break;
				}
				case '7' :
				case '8' :
				{
					Statement statement = connection.createStatement();
					String sqlRequest = "select id_services, service, cost, description from services " + ((req.getParameter("request").equals("7")) ? "" : ("where service = '" + req.getParameter("service") + "'"));
					statement.executeQuery(sqlRequest);
					ResultSet resultSet = statement.getResultSet();
					StringBuilder stringBuilder = new StringBuilder()
							.append("<div class=\"row\"><div class=\"col l8 offset-l2\"><table id=\"table\" border=\"1\">")
							.append("<tr>")
							.append("<td>ID</td>")
							.append("<td>Имя</td>")
							.append("<td>Стоимость</td>")
							.append("<td>Описание</td>")
							.append("</tr>");
					while (resultSet.next())
						stringBuilder
								.append("<tr>")
								.append("<td>").append(resultSet.getString("id_services")).append("</td>")
								.append("<td>").append(resultSet.getString("service"  )).append("</td>")
								.append("<td>").append(resultSet.getString("cost")).append("</td>")
								.append("<td>").append(resultSet.getString("description")).append("</td>")
								.append("</tr>");
					stringBuilder.append("</table></div></div>");
					req.getSession().setAttribute("table", stringBuilder.toString());
					try
					{
						resp.sendRedirect("/info");
						return;
					}
					catch (IOException e)
					{
						e.printStackTrace();
					}
					break;
				}
				case '9' :
				{
					PreparedStatement preparedStatement = connection.prepareStatement("select * from services where service = ?");
					preparedStatement.setString(1, req.getParameter("service"));
					ResultSet resultSet = preparedStatement.executeQuery();
					resultSet.next();
					String[] arr = {
							(req.getParameter("newService").equals(""))?resultSet.getString("service") : req.getParameter("newService"),
							(req.getParameter("newCost").equals(""))?String.valueOf(resultSet.getInt("cost")) : req.getParameter("newCost"),
							(req.getParameter("newDescription").equals(""))? resultSet.getString("description") : req.getParameter("newDescription"),
					};

					preparedStatement = connection.prepareStatement("update services set service = ?, cost = ?, description = ? where id_services = ?");
					preparedStatement.setString(1, arr[0]);
					preparedStatement.setInt(2, Integer.parseInt(arr[1]));
					preparedStatement.setString(3, arr[2]);
					preparedStatement.setInt(4, resultSet.getInt("id_services"));
					preparedStatement.executeUpdate();
					break;
				}
				case 'a' :
				{
					PreparedStatement preparedStatement = connection.prepareStatement("select id_services from services where service = ?");
					preparedStatement.setString(1, req.getParameter("service"));
					int service = 0;
					ResultSet resultSet = preparedStatement.executeQuery();
					if (resultSet.next())
						service = resultSet.getInt("id_services");
					preparedStatement = connection.prepareStatement("select id_users from users where name = ?");
					preparedStatement.setString(1, req.getParameter("user"));
					int name = 0;
					resultSet = preparedStatement.executeQuery();
					if (resultSet.next())
						name = resultSet.getInt("id_users");
					preparedStatement = connection.prepareStatement("insert into orders(id_service, count, whoTakeOrder, date, name, number) values (?, ?, ?, ?, ?, ?)");
					preparedStatement.setInt(1, service);
					preparedStatement.setInt(2, Integer.parseInt(req.getParameter("count")));
					preparedStatement.setInt(3, name);
					preparedStatement.setString(4, req.getParameter("date"));
					preparedStatement.setString(5, req.getParameter("name"));
					preparedStatement.setString(6, req.getParameter("number"));
					preparedStatement.executeUpdate();
					break;
				}
				case 'b' :
				{
					PreparedStatement preparedStatement = connection.prepareStatement("select id_services from services where service = ?");
					preparedStatement.setString(1, req.getParameter("service"));
					ResultSet resultSet = preparedStatement.executeQuery();
					int a = 0;
					if (resultSet.next())
						a = resultSet.getInt("id_services");
					preparedStatement = connection.prepareStatement("delete from orders where id_service = ? and number = ? and date between ? and ?");
					preparedStatement.setInt(1, a);
					preparedStatement.setString(2, req.getParameter("number"));
					String date = req.getParameter("date");
					preparedStatement.setString(3, date.substring(0,10)+"T00:00:00");
					preparedStatement.setString(4, date.substring(0,10)+"T23:59:59");
					preparedStatement.executeUpdate();
					break;
				}
				case 'c' :
				case 'd' :
				{
					Statement statement = connection.createStatement();
					String request = "select id_orders, services.service, count, users.name, date, orders.name, number  from users join orders on users.id_users = orders.whoTakeOrder join services on orders.id_service = services.id_services " + ((req.getParameter("request").equals("c")) ? "" : ("where service = '" + req.getParameter("service") + "' and number = '"  + req.getParameter("number") + "'" )) + " order by id_orders";
					statement.executeQuery(request);
					ResultSet resultSet = statement.getResultSet();
					StringBuilder stringBuilder = new StringBuilder()
							.append("<div class=\"row\"><div class=\"col l8 offset-l2\"><table id=\"table\" border=\"1\">")
							.append("<tr>")
							.append("<td>ID</td>")
							.append("<td>Сервис</td>")
							.append("<td>Количество</td>")
							.append("<td>Работник</td>")
							.append("<td>Дата</td>")
							.append("<td>Имя</td>")
							.append("<td>Номер</td>")
							.append("</tr>");
					while (resultSet.next())
						stringBuilder
								.append("<tr>")
								.append("<td>").append(resultSet.getString("id_orders")).append("</td>")
								.append("<td>").append(resultSet.getString("services.service"  )).append("</td>")
								.append("<td>").append(resultSet.getString("count")).append("</td>")
								.append("<td>").append(resultSet.getString("users.name")).append("</td>")
								.append("<td>").append(resultSet.getString("date")).append("</td>")
								.append("<td>").append(resultSet.getString("orders.name")).append("</td>")
								.append("<td>").append(resultSet.getString("number")).append("</td>")
								.append("</tr>");
					stringBuilder.append("</table></div></div>");
					req.getSession().setAttribute("table", stringBuilder.toString());
					try
					{
						resp.sendRedirect("/info");
						return;
					}
					catch (IOException e)
					{
						e.printStackTrace();
					}
					break;
				}
				case 'e' :
				{
					PreparedStatement preparedStatement = connection.prepareStatement("select id_services from services where service = ?");
					preparedStatement.setString(1, req.getParameter("service"));
					int service1 = 0;
					ResultSet resultSet = preparedStatement.executeQuery();
					if (resultSet.next())
						service1 = resultSet.getInt("id_services");
					preparedStatement = connection.prepareStatement("select * from orders where id_service = ? and date between ? and ? and  number = ? and name = ?");
					preparedStatement.setInt(1, service1);
					preparedStatement.setString(2, req.getParameter("date").substring(0,10)+"T00:00:00");
					preparedStatement.setString(3, req.getParameter("date").substring(0,10)+"T23:59:59");
					preparedStatement.setString(4, req.getParameter("number"));
					preparedStatement.setString(5, req.getParameter("name"));
					resultSet = preparedStatement.executeQuery();
					resultSet.next();
					String[] arr1 = {
							(req.getParameter("newService").equals(""))?resultSet.getString("id_service") : req.getParameter("newService"),
							(req.getParameter("newCount").equals(""))?resultSet.getString("count") : req.getParameter("newCount"),
							(req.getParameter("newUser").equals(""))? resultSet.getString("whoTakeOrder") : req.getParameter("newUser"),
							(req.getParameter("newDate").equals(""))? resultSet.getString("date") : req.getParameter("newDate"),
					};

					preparedStatement = connection.prepareStatement("select id_services from services where service = ?");
					preparedStatement.setString(1, arr1[0]);
					resultSet = preparedStatement.executeQuery();
					if (resultSet.next())
						arr1[0] = resultSet.getString("id_services");
					preparedStatement = connection.prepareStatement("select id_users from users where name = ?");
					preparedStatement.setString(1, arr1[2]);
					resultSet = preparedStatement.executeQuery();
					if (resultSet.next())
						arr1[2] = resultSet.getString("id_users");

					preparedStatement = connection.prepareStatement("update orders set id_service = ?, count = ?, whoTakeOrder = ?, date = ? where number = ? and id_service = ? and name = ? and date between ? and ?");
					preparedStatement.setInt(1, Integer.parseInt(arr1[0]));
					preparedStatement.setInt(2, Integer.parseInt(arr1[1]));
					preparedStatement.setInt(3, Integer.parseInt(arr1[2]));
					preparedStatement.setString(4, arr1[3]);
					preparedStatement.setString(5, req.getParameter("number"));
					preparedStatement.setInt(6, service1);
					preparedStatement.setString(7, req.getParameter("name"));
					preparedStatement.setString(8, req.getParameter("date").substring(0,10)+"T00:00:00");
					preparedStatement.setString(9, req.getParameter("date").substring(0,10)+"T23:59:59");
					preparedStatement.executeUpdate();
					break;
				}
				case 'f' :
				{
					PreparedStatement preparedStatement = connection.prepareStatement("select services.service, users.name, count, date, cost from users join orders on users.id_users = orders.whoTakeOrder join services on orders.id_service = services.id_services where date between ? and ?");
					preparedStatement.setString(1, req.getParameter("date")+"T00:00:00");
					preparedStatement.setString(2, req.getParameter("date")+"T23:59:59");
					ResultSet resultSet = preparedStatement.executeQuery();

					try
					{
						req.setAttribute("result",  resultSet);
						getServletContext().getRequestDispatcher("/download").forward(req, resp);
						return;
					}
					catch (IOException | ServletException e)
					{
						e.printStackTrace();
					}
					break;
				}
				case 'g' :
				case 'h' :
				{
					StringBuilder st = new StringBuilder().append("google.charts.load('current', {packages:[\"calendar\"]});google.charts.setOnLoadCallback(drawChart);function drawChart(){var dataTable = new google.visualization.DataTable();dataTable.addColumn({ type: 'date', id: 'Date' });dataTable.addColumn({ type: 'number', id: 'Won/Loss' });dataTable.addRows([");

					String request = (sw == 'g') ? "select date from orders order by date" : "select date, cost, count from orders left join services on orders.id_service = services.id_services order by date";
					String s = (sw == 'g') ? "\"Количество заказов\"":"\"Прибыль\"";
							PreparedStatement preparedStatement = connection.prepareStatement(request);
					ResultSet resultSet = preparedStatement.executeQuery();
					resultSet.next();
					while (! resultSet.isAfterLast())
					{
						int i = (sw == 'g') ? 1 : resultSet.getInt("cost") * resultSet.getInt("count");
						Calendar cal = Calendar.getInstance();
						cal.setTime(resultSet.getDate("date"));
						if (!req.getParameter("date").equals(""))
							if (cal.get(Calendar.YEAR) != Integer.parseInt(req.getParameter("date").substring(0, 4)))
							{
								resultSet.next();
								continue;
							}
						while (resultSet.next())
						{
							if (dupl(resultSet, cal)) break;
							i += (sw == 'g') ? 1 : resultSet.getInt("cost") * resultSet.getInt("count");
						}
						st
								.append("[ new Date(")
								.append(cal.get(Calendar.YEAR))
								.append(", ")
								.append(cal.get(Calendar.MONTH))
								.append(", ")
								.append(cal.get(Calendar.DAY_OF_MONTH))
								.append("), ")
								.append(i)
								.append(" ],");
					}
					st
							.append("]);var chart = new google.visualization.Calendar(document.getElementById('chart'));")
							.append("var options={colorAxis:{colors:['#bfbfbf','magenta']},title:")
							.append(s)
							.append(",height: 700,calendar:{")
							.append("cellSize:20,cellColor:{stroke:'#ffffff00',strokeOpacity:0,strokeWidth:1,},")
							.append("focusedCellColor:{stroke:'magenta',strokeOpacity:0.4,strokeWidth:1,},")
							.append("yearLabel:{fontSize:32,color:'#555',},")
							.append("monthLabel:{fontSize:12,color:'#555',},")
							.append("monthOutlineColor:{stroke:'magenta',strokeOpacity:0.2,strokeWidth:1},")
							.append("unusedMonthOutlineColor:{stroke:'magenta',strokeOpacity:0.2,strokeWidth:1},")
							.append("underMonthSpace:16,},")
							.append("noDataPattern:{backgroundColor:'#555',color: '#555'},};")
							.append("chart.draw(dataTable, options);}");
					try
					{
						req.setAttribute("script", st.toString());
						getServletContext().getRequestDispatcher("/chart").forward(req, resp);
						return;
					}
					catch (IOException | ServletException e)
					{
						e.printStackTrace();
					}
					break;
				}
				case 'i' :
				case 'j' :
				{
					StringBuilder stringBuilder = new StringBuilder().append("google.charts.load('current',{'packages':['corechart']});google.charts.setOnLoadCallback(drawChart);function drawChart(){var data = new google.visualization.DataTable();data.addColumn('number', 'Day');data.addColumn('number', 'count');data.addRows([");
					String request = (sw == 'i') ? "select date from orders order by date" : "select date, cost, count from orders left join services on orders.id_service = services.id_services order by date";
					String s = (sw == 'i') ? "Количество заказов":"Прибыль";
					PreparedStatement preparedStatement = connection.prepareStatement(request);
					ResultSet resultSet = preparedStatement.executeQuery();
					resultSet.next();
					while (! resultSet.isAfterLast())
					{
						int i = (sw == 'i') ? 1 : resultSet.getInt("cost") * resultSet.getInt("count");
						Calendar cal = Calendar.getInstance();
						cal.setTime(resultSet.getDate("date"));
						if (cal.get(Calendar.YEAR) != Integer.parseInt(req.getParameter("date").substring(0, 4)))
						{
							resultSet.next();
							continue;
						}
						while (resultSet.next())
						{
							if (dupl(resultSet, cal)) break;
							i += (sw == 'i') ? 1 : resultSet.getInt("cost") * resultSet.getInt("count");
						}
						stringBuilder
								.append("[")
								.append(cal.get(Calendar.DAY_OF_YEAR))
								.append(", ")
								.append(i)
								.append("],");
					}

					stringBuilder
							.append("]);var options={title:'")
							.append(s)
							.append("',curveType:'function',legend:{position:'bottom'}};var chart=new google.visualization.LineChart(document.getElementById('chart'));chart.draw(data,options);}");
					try
					{
						req.setAttribute("script", stringBuilder.toString());
						getServletContext().getRequestDispatcher("/chart").forward(req, resp);
						return;
					}
					catch (IOException | ServletException e)
					{
						e.printStackTrace();
					}
					break;
				}
			}
		}
		catch (SQLException exception)
		{
			exception.printStackTrace();
		}
		try
		{
			resp.sendRedirect("/start");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private boolean dupl(ResultSet resultSet, Calendar cal) throws SQLException
	{
		Calendar buff = Calendar.getInstance();
		buff.setTime(resultSet.getDate("date"));
		return buff.get(Calendar.YEAR) != cal.get(Calendar.YEAR) || buff.get(Calendar.MONTH) != cal.get(Calendar.MONTH) || buff.get(Calendar.DAY_OF_MONTH) != cal.get(Calendar.DAY_OF_MONTH);
	}
}
