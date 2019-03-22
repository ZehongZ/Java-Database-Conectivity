import java.sql.*;
import java.io.*;

public class Query {

   public static void main( String[] args ) {

      Connection conn = null;
      Statement stmt = null;

      /***********************************************************************
      *  determine if the JDBC driver exists and load it...
      ***********************************************************************/

      /***********************************************************************
      *  establish a connection to the database...
      ***********************************************************************/
      try {
         System.out.print( "Connecting to ACADPRD0 database...\n\n" );
         //String url = dataSource + dbName;

         conn = DriverManager.getConnection("jdbc:oracle:thin:@acadoradbprd01.dpu.depaul.edu:1521:ACADPRD0", "ZZHUANG", "cdm1379457");


         /*conn = dbms.equals("localAccess") ? DriverManager.getConnection(url)
            : DriverManager.getConnection(url, userName, password );*/
         System.out.println( "Connected to database ACADPRD0..." );

         /***********************************************************************
         *  create an object by which we will pass SQL stmts to the database...
         ***********************************************************************/
         stmt = conn.createStatement();
         }
      catch (SQLException se) {
         System.out.println(se);
         System.exit(1);
         }
      
      try {
         /***********************************************************************
         *  finally, display all the rows in the database...
         ***********************************************************************/
    	  String query="SELECT count(gender),gender FROM UsersTable group by gender";
    	  System.out.print( "Display query question...\n\n");
          ResultSet rset = stmt.executeQuery(query);
          while( rset.next() )
              System.out.println("Number: "+ rset.getInt(1) + " Gender: " +
                 rset.getString(2));
                   
          rset.close();
          stmt.close();
          conn.close();
         }
      catch (SQLException se) {
         System.out.println( "SQL ERROR: " + se );
         }

   } // end main


  }  // end class

