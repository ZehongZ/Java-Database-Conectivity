import java.sql.*;
import java.io.*;

public class GenreTable {

   public static void main( String[] args ) {

      String genreTable = "GenreTable";

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
          String dropString = "DROP TABLE " + genreTable;
          stmt.executeUpdate(dropString);
          }
       catch (SQLException se) {/*do nothing*/} // table doesn't exist
      
      try {
         /***********************************************************************
         *  finally, display all the rows in the database...
         ***********************************************************************/
    	  System.out.print( "Building new " + genreTable + " table...\n\n" );
    	  String createString = 
    			  "CREATE TABLE " + genreTable + "(GenreID Number PRIMARY KEY, "
    			  		+ "Genre VARCHAR2(30))";
    	  stmt.executeUpdate(createString);
    	  
          System.out.print( "Inserting rows Genre table...\n\n" );
          String insertString = 
        		  "INSERT INTO " + genreTable + " VALUES (1,'Action')";
          stmt.executeUpdate(insertString);
          
          insertString =
        		  "INSERT INTO " + genreTable + " VALUES (2,'Adventure')";
          stmt.executeUpdate(insertString);
          
          insertString =
        		  "INSERT INTO " + genreTable + " VALUES (3,'Animation')";
          stmt.executeUpdate(insertString);
          
          insertString =
        		  "INSERT INTO " + genreTable + " VALUES (4,'Childrens')";
          stmt.executeUpdate(insertString);
          
          insertString =
        		  "INSERT INTO " + genreTable + " VALUES (5,'Comedy')";
          stmt.executeUpdate(insertString);
          
          insertString =
        		  "INSERT INTO " + genreTable + " VALUES (6,'Crime')";
          stmt.executeUpdate(insertString);

          insertString =
        		  "INSERT INTO " + genreTable + " VALUES (7,'Documentary')";
          stmt.executeUpdate(insertString);
          
          insertString =
        		  "INSERT INTO " + genreTable + " VALUES (8,'Drama')";
          stmt.executeUpdate(insertString);
          
          insertString =
        		  "INSERT INTO " + genreTable + " VALUES (9,'Fantasy')";
          stmt.executeUpdate(insertString);
          
          insertString =
        		  "INSERT INTO " + genreTable + " VALUES (10,'Film-Noir')";
          stmt.executeUpdate(insertString);
          
          insertString =
        		  "INSERT INTO " + genreTable + " VALUES (11,'Horror')";
          stmt.executeUpdate(insertString);
          
          insertString =
        		  "INSERT INTO " + genreTable + " VALUES (12,'Musical')";
          stmt.executeUpdate(insertString);
          
          insertString =
        		  "INSERT INTO " + genreTable + " VALUES (13,'Mystery')";
          stmt.executeUpdate(insertString);
          
          insertString =
        		  "INSERT INTO " + genreTable + " VALUES (14,'Romance')";
          stmt.executeUpdate(insertString);
          
          insertString =
        		  "INSERT INTO " + genreTable + " VALUES (15,'Sci-Fi')";
          stmt.executeUpdate(insertString);
          
          insertString =
        		  "INSERT INTO " + genreTable + " VALUES (16,'Thriller')";
          stmt.executeUpdate(insertString);
          
          insertString =
        		  "INSERT INTO " + genreTable + " VALUES (17,'War')";
          stmt.executeUpdate(insertString);
          
          insertString =
        		  "INSERT INTO " + genreTable + " VALUES (18,'Western')";
          stmt.executeUpdate(insertString);
          
    	  System.out.print( "Display Genre table...\n\n");
          ResultSet rset = stmt.executeQuery( " SELECT * FROM " + genreTable );
          while( rset.next() )
             System.out.println( rset.getString("GenreID") + ": " +
                rset.getString("Genre"));
          
                   
          rset.close();
          stmt.close();
          conn.close();
         }
      catch (SQLException se) {
         System.out.println( "SQL ERROR: " + se );
         }

   } // end main


  }  // end class
