import java.sql.*;
import java.io.*;

public class MoviesGenreTable {

   public static void main( String[] args ) {

      String moviesGenreTable = "MoviesGenreTable";

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
          String dropString = "DROP TABLE " + moviesGenreTable;
          stmt.executeUpdate(dropString);
          }
       catch (SQLException se) {/*do nothing*/} // table doesn't exist
      
      try {
         /***********************************************************************
         *  finally, display all the rows in the database...
         ***********************************************************************/
    	  System.out.print( "Building new " + moviesGenreTable + " table...\n\n" );
    	  String createString = 
    			  "CREATE TABLE " + moviesGenreTable + "(MovieID INT PRIMARY KEY, "
    			  		+ "GenreID INT,"
    			  		+ "Genre VARCHAR2(100))";
    	  stmt.executeUpdate(createString);
    	  
          System.out.print( "Inserting rows in Genre table...\n\n" );
          String insertString=
        		  "INSERT INTO "+ moviesGenreTable+ "(MovieID,Genre) SELECT DISTINCT MOVIEID, trim(regexp_substr(Genres, '[^|]+', LEVEL)) FROM MOVIES CONNECT BY instr(regexp_substr(Genres, '[^|]+',LEVEL),'|', 1, level -1)>0";
          stmt.executeUpdate(insertString);
          
          String mergeString=
        		  "MERGE INTO MoviesGenreTable USING GenreTable ON (MoviesGenreTable.Genre=GenreTable.Genre) WHEN MATCHED THEN UPDATE SET MoviesGenreTable.GenreID=GenreTable.GenreID";
          stmt.executeUpdate(mergeString);
          
          String dropString=
        		  "ALTER TABLE moviesGenreTable DROP COLUMN Genre";
          stmt.executeUpdate(dropString);

          
    	  System.out.print( "Display Movies table...\n\n");
          ResultSet rset = stmt.executeQuery( " SELECT * FROM " + moviesGenreTable);
          while( rset.next() )
             System.out.println("MovieID: "+ rset.getString("MovieID") + " GenreID: " +
                rset.getString("GenreID"));
          
                   
          rset.close();
          stmt.close();
          conn.close();
         }
      catch (SQLException se) {
         System.out.println( "SQL ERROR: " + se );
         }

   } // end main


  }  // end class
