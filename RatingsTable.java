import java.util.List;
import java.sql.*;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.io.FileNotFoundException;


public class RatingsTable {

   public static void main( String[] args ) throws 
   FileNotFoundException, NumberFormatException, IOException {

      String tableName = "Ratings";

      Connection conn = null;
      Statement stmt = null;
      /***********************************************************************
      *  determine if the JDBC driver exists and load it...
      ***********************************************************************/
      System.out.print( "\nLoading JDBC driver...\n\n" );
      try {
         Class.forName("oracle.jdbc.OracleDriver");
         }
      catch(ClassNotFoundException e) {
         System.out.println(e);
         System.exit(1);
         }

      /***********************************************************************
      *  establish a connection to the database...
      ***********************************************************************/
      try {
         System.out.print( "Connecting to ACADPRD0 database...\n\n" );
         //String url = dataSource + dbName;

         conn = DriverManager.getConnection(
			"jdbc:oracle:thin:@acadoradbprd01.dpu.depaul.edu:1521:ACADPRD0",
            "ZZHUANG", "cdm1379457");


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

      /***********************************************************************
      *  in the event that this table already exists, we want to delete it
      *  and build a new table from scratch... if the table doesn't exist,
      *  an SQLException would be thrown when the DROP TABLE stmt below is
      *  executed. We catch that exception, but we don't need to do anything
      *  because we expect the error to occur if the table doesn't exist...
      ***********************************************************************/
      try {
         String dropString = "DROP TABLE " + tableName;
         stmt.executeUpdate(dropString);
         }
      catch (SQLException se) {/*do nothing*/} // table doesn't exist

      try {
         /***********************************************************************
         *  create the new table...
         ***********************************************************************/
         System.out.print( "Building new " + tableName + " table...\n\n" );
         String createString =
            "CREATE TABLE " + tableName +
            "  (UserID INT NOT NULL,"
            + "MovieID INT NOT NULL,"
            + "Ratings INT NOT NULL,"
            + "TimeStamp TIMESTAMP NOT NULL,"
            + "PRIMARY KEY (UserID, MovieID))";
         stmt.executeUpdate(createString);

         /***********************************************************************
         *  now populate the table...
         ***********************************************************************/
         System.out.print( "Inserting rows in Ratings table...\n\n" );

		 PreparedStatement updateRating =
		 conn.prepareStatement( "INSERT INTO " + tableName + " VALUES (?, ?,?,? )");

		 conn.setAutoCommit(false);
		 
		 File file = new File("/Users/zehongzhuang/Desktop/ratings.dat");
		 FileInputStream fstream = new FileInputStream(file);
		 BufferedReader br = new BufferedReader (new InputStreamReader(fstream));
		 
		 String content;
		 
		 int i = 0;
		 
		 while ((content = br.readLine()) !=null && i<=200000){
			 List<String> ratingData = Arrays.asList(content.split("::"));
			 updateRating.setInt(1, Integer.parseInt(ratingData.get(0)));
			 updateRating.setInt(2, Integer.parseInt(ratingData.get(1)));
			 updateRating.setInt(3, Integer.parseInt(ratingData.get(2)));
			 updateRating.setTimestamp(4, new Timestamp(Long.parseLong(ratingData.get(3))));
			 updateRating.executeUpdate();
			 i++;	 
		 }
         conn.commit();

         /***********************************************************************
         *  finally, display all the rows in the database...
         ***********************************************************************/
         ResultSet rset = stmt.executeQuery( "SELECT * FROM " + tableName);
         while( rset.next() )
            System.out.println("UserID: "+ rset.getString("UserID") + " MovieID:" +
               rset.getString("MovieID")+" Ratings:"+rset.getString("Ratings")+" Timestamp:"+rset.getString("TimeStamp"));
         br.close();
         rset.close();
         stmt.close();
         conn.close();
         }
      catch (SQLException se) {
         System.out.println( "SQL ERROR: " + se );
         }

   } // end main


  }  // end class
