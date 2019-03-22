import java.sql.*;
import java.io.*;

public class AgeTable {

   public static void main( String[] args ) {

      String ageTable = "AgeTable";

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
          String dropString = "DROP TABLE " + ageTable;
          stmt.executeUpdate(dropString);
          }
       catch (SQLException se) {/*do nothing*/} // table doesn't exist
      
      try {
         /***********************************************************************
         *  finally, display all the rows in the database...
         ***********************************************************************/
    	  System.out.print( "Building new " + ageTable + " table...\n\n" );
    	  String createString = 
    			  "CREATE TABLE " + ageTable + "(AgeNum Number PRIMARY KEY, "
    			  		+ "AgeFact VARCHAR2(30))";
    	  stmt.executeUpdate(createString);
    	  
          System.out.print( "Inserting rows in Age table...\n\n" );
          String insertString = 
        		  "INSERT INTO " + ageTable + " VALUES (01,'Under 18')";
          stmt.executeUpdate(insertString);
          
          insertString =
        		  "INSERT INTO " + ageTable + " VALUES (18,'1-24')";
          stmt.executeUpdate(insertString);
          
          insertString = 
        		  "INSERT INTO " + ageTable + " VALUES (25,'25-34')";
          stmt.executeUpdate(insertString);
          
          insertString = 
        		  "INSERT INTO " + ageTable + " VALUES (35,'35-44')";
          stmt.executeUpdate(insertString);
          
          insertString = 
        		  "INSERT INTO " + ageTable + " VALUES (45,'45-49')";
          stmt.executeUpdate(insertString);
          
          insertString = 
        		  "INSERT INTO " + ageTable + " VALUES (50,'50-55')";
          stmt.executeUpdate(insertString);
          
          insertString = 
        		  "INSERT INTO " + ageTable + " VALUES (56,'56+')";
          stmt.executeUpdate(insertString);
  
    	  System.out.print( "Display Age table...\n\n");
          ResultSet rset = stmt.executeQuery( " SELECT * FROM " + ageTable );
          while( rset.next() )
             System.out.println( rset.getString("AgeNum") + ": " +
                rset.getString("AgeFact"));
          
                   
          rset.close();
          stmt.close();
          conn.close();
         }
      catch (SQLException se) {
         System.out.println( "SQL ERROR: " + se );
         }
   } // end main

  }  // end class
