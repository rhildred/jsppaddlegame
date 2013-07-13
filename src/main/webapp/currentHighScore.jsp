<%@page import="java.util.*"%>
<%@page import="java.sql.*"%>
<%@page import="ca.on.conestogac.*"%>
<%

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
%>
<%= sResults %>

