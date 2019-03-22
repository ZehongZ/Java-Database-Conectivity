import java.sql.*;
import java.io.*;

public class OccupationTable {

   public static void main( String[] args ) {

      String occupationTable = "OccupationTable";

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
          String dropString = "DROP TABLE " + occupationTable;
          stmt.executeUpdate(dropString);
          }
       catch (SQLException se) {/*do nothing*/} // table doesn't exist
      
      try {
         /***********************************************************************
         *  finally, display all the rows in the database...
         ***********************************************************************/
    	  System.out.print( "Building new " + occupationTable + " table...\n\n" );
    	  String createString = 
    			  "CREATE TABLE " + occupationTable + "(OccupationID Number PRIMARY KEY, "
    			  		+ "JobDescription VARCHAR2(30))";
    	  stmt.executeUpdate(createString);
    	  
          System.out.print( "Inserting rows OccupationTable table...\n\n" );
          String insertString = 
        		  "INSERT INTO " + occupationTable + " VALUES (0,'other')";
          stmt.executeUpdate(insertString);
          
          insertString =
        		  "INSERT INTO " + occupationTable + " VALUES (1,'academic/educator')";
          stmt.executeUpdate(insertString);
          
          insertString = 
        		  "INSERT INTO " + occupationTable + " VALUES (2,'artist')";
          stmt.executeUpdate(insertString);
          
          insertString = 
        		  "INSERT INTO " + occupationTable + " VALUES (3,'clerical/admin')";
          stmt.executeUpdate(insertString);
          
          insertString = 
        		  "INSERT INTO " + occupationTable + " VALUES (4,'college/grad student')";
          stmt.executeUpdate(insertString);
          
          insertString = 
        		  "INSERT INTO " + occupationTable + " VALUES (5,'customer service')";
          stmt.executeUpdate(insertString);
          
          insertString = 
        		  "INSERT INTO " + occupationTable + " VALUES (6,'doctor/health care')";
          stmt.executeUpdate(insertString);
          
          insertString = 
        		  "INSERT INTO " + occupationTable + " VALUES (7,'executive/managerial')";
          stmt.executeUpdate(insertString);
          
          insertString = 
        		  "INSERT INTO " + occupationTable + " VALUES (8,'farmer')";
          stmt.executeUpdate(insertString);
          
          insertString = 
        		  "INSERT INTO " + occupationTable + " VALUES (9,'homemaker')";
          stmt.executeUpdate(insertString);
          
          insertString = 
        		  "INSERT INTO " + occupationTable + " VALUES (10,'K-12 Student')";
          stmt.executeUpdate(insertString);
          
          insertString = 
        		  "INSERT INTO " + occupationTable + " VALUES (11,'lawyer')";
          stmt.executeUpdate(insertString);
          
          insertString = 
        		  "INSERT INTO " + occupationTable + " VALUES (12,'programmer')";
          stmt.executeUpdate(insertString);
          
          insertString = 
        		  "INSERT INTO " + occupationTable + " VALUES (13,'retired')";
          stmt.executeUpdate(insertString);
          
          insertString = 
        		  "INSERT INTO " + occupationTable + " VALUES (14,'sales/marketing')";
          stmt.executeUpdate(insertString);
          
          insertString = 
        		  "INSERT INTO " + occupationTable + " VALUES (15,'scientist')";
          stmt.executeUpdate(insertString);
          
          insertString = 
        		  "INSERT INTO " + occupationTable + " VALUES (16,'self-employed')";
          stmt.executeUpdate(insertString);
          
          insertString = 
        		  "INSERT INTO " + occupationTable + " VALUES (17,'technician/engineer')";
          stmt.executeUpdate(insertString);
          
          insertString = 
        		  "INSERT INTO " + occupationTable + " VALUES (18,'tradesman/craftsman')";
          stmt.executeUpdate(insertString);
          
          insertString = 
        		  "INSERT INTO " + occupationTable + " VALUES (19,'unemployed')";
          stmt.executeUpdate(insertString);
          
          insertString = 
        		  "INSERT INTO " + occupationTable + " VALUES (20,'writer')";
          stmt.executeUpdate(insertString);
  
    	  System.out.print( "Display Occupation table...\n\n");
          ResultSet rset = stmt.executeQuery( " SELECT * FROM " + occupationTable );
          while( rset.next() )
             System.out.println( rset.getString("OccupationID") + ": " +
                rset.getString("JobDescription"));
          
                   
          rset.close();
          stmt.close();
          conn.close();
         }
      catch (SQLException se) {
         System.out.println( "SQL ERROR: " + se );
         }

   } // end main


  }  // end class
