import java.sql.*;
import java.io.*;

public class MoviesTable {

   public static void main( String[] args ) {

      String moviesTable = "MoviesTable";

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
          String dropString = "DROP TABLE " + moviesTable;
          stmt.executeUpdate(dropString);
          }
       catch (SQLException se) {/*do nothing*/} // table doesn't exist
      
      try {
         /***********************************************************************
         *  finally, display all the rows in the database...
         ***********************************************************************/
    	  System.out.print( "Building new " + moviesTable + " table...\n\n" );
    	  String createString = 
    			  "CREATE TABLE " + moviesTable + "(MovieID Number PRIMARY KEY, "
    			  		+ "Title VARCHAR2(500),"
    			  		+ "Years VARCHAR2(10))";
    	  stmt.executeUpdate(createString);
    	  
          System.out.print( "Inserting rows in Movies table...\n\n" );
          String insertString=
        		  "INSERT INTO MoviesTable (MovieID, Title, Years)"
        		  + "SELECT DISTINCT MOVIEID, SUBSTR(TITLE,0, LENGTH(TITLE)-6), SUBSTR(TITLE,-6) "
        		  + "FROM MOVIES";
          stmt.executeUpdate(insertString);
          

          
    	  System.out.print( "Display Movies table...\n\n");
          ResultSet rset = stmt.executeQuery( " SELECT * FROM " + moviesTable );
          while( rset.next() )
             System.out.println("MovieID: "+ rset.getString("MovieID") + " Title: " +
                rset.getString("Title") + " Years: "+ rset.getString("Years"));
          
                   
          rset.close();
          stmt.close();
          conn.close();
         }
      catch (SQLException se) {
         System.out.println( "SQL ERROR: " + se );
         }

   } // end main


  }  // end class
