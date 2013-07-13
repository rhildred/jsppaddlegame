package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;
import java.sql.*;
import ca.on.conestogac.*;

public final class currentHighScore_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write('\n');
      out.write('\n');
      out.write('\n');


String sResults = "";
Connection connection = null;
try
{
	connection = OpenShiftDataSource.getConnection("jdbc:mysql://localhost/jsppaddlegame?user=root");
	if(request.getMethod().equalsIgnoreCase("post"))
	{
		PreparedStatement updateStmt = connection.prepareStatement("UPDATE highscores SET name = ?, score = ?");
		String sName = request.getParameter("name");
		updateStmt.setString(1, sName);
		int n1 = Integer.parseInt(request.getParameter("score"));
		updateStmt.setInt(2, n1);

		int nRows = updateStmt.executeUpdate();
		if(nRows <= 0){
			throw new Exception(nRows + " updated expected 1");
		}

	}
	PreparedStatement stmt = connection.prepareStatement("SELECT name, score FROM highscores");
	ResultSet rs = stmt.executeQuery();

	rs.next();

	sResults = "{\"name\":\"" + rs.getString("name") + "\"," 
	+ "\"score\":\"" + rs.getString("score") + "\"}";
}
catch(Exception e)
{
	sResults = e.toString();
}
finally
{
	if(connection != null)
	{
		connection.close();
	}
}

      out.write('\n');
      out.print( sResults );
      out.write('\n');
      out.write('\n');
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
