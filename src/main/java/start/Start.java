package start;

import javax.servlet.http.*;
import java.io.IOException;

public class Start extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		req.getSession().setMaxInactiveInterval(-1);
		Cookie[] cookies = req.getCookies();
		for (Cookie c:cookies)
		if (c.getName().equals("login"))
		{
			resp.sendRedirect("/check");
			return;
		}
		resp.sendRedirect("/login");
	}
}
