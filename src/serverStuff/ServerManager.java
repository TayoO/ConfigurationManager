package serverStuff;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

class ServerManager
{
	 static String path = "c:/temp/opentext.ini";
	 static final int ARBITRARY_LIMIT=10;
 static SpecificMap groups= new SpecificMap<String, ServerGroup>();
 static SpecificMap servers= new SpecificMap<String, Server>();
 static SpecificMap globalSections= new SpecificMap<String, Section>();
static String [] groupNames;
 Server[] serverList;
 static String [] serverNames;
 boolean [][] associations;


static Scanner in = new Scanner(System.in);
private ArrayList <String> names;
 
public static void main (String [] args) throws IOException, SQLException
{
	
	int [] groupIndex;
	
			String [] pushServers= new String [] {"epsilon","second","third", "fourth", "fifth"};
	String [] changes=new String []  {"data,linkedList,double,"+path,"seconddata,linkedList,double,"+path,"thirddata,linkedList,double,"+path, "fourthdata,linkedList,double,"+path, "fifthdata,linkedList,double,"+path};
	//	globalSections.put("default", new Section ("title"));
	ServerManager main= new ServerManager();
	main.loadDefault();
	 String [] chosenServers = null;	

	System.out.println("Do you want to push to groups?");
	if(in.nextBoolean()==true){
		
		System.out.println("How many departments?");
	String [] departs=new String [in.nextInt()];
	
	
		for (int i=0; i<departs.length; i++){
			
		System.out.println("enter the next department name");
		departs[i]= in.next();
		}
		System.out.println("how many server types?");
		String [] types=new String [in.nextInt()];
		for (int i=0; i<types.length; i++){
			
		System.out.println("enter the  type names");
		types[i]= in.next();
		
		}
		chosenServers=main.getGroups(departs, types);
	     
  		
	}
	
		//System.out.println("What server names do you want to push to");
	

		
		System.out.println("how many changes do you want to push");
		changes= new String[in.nextInt()];
		String section;
		String variable;
		String value;
		System.out.close();
		 
		for (int i=0; i<changes.length; i++){
			System.out.println("Which section?");
			section=in.nextLine();
			System.out.println("Which variable?");
			variable=in.nextLine();
			System.out.println("What value are you setting it too?");
			value=in.nextLine();
		changes[i]=section+","+variable+","+value+","+path;
		}
 main.pushConfiguration(chosenServers, changes);
	}
	



/*
file code:
numGroups
numServers
listOfGroupNames seperated by commas
ListOfServerNames seperated by commas
numGroups lines each with list of servers indexes corresponding to respective groups seperated by commas
Example]
5
6
frontEnd, BackEnd, PWGSC, SSC, RCMP
PWFE, PWBE, SSCFE, SSCBE, RCMPFE, RCMPBE
1,3,5
2,4,6
1,2h
5,6
*/
public String [] getGroups(String [] departs, String [] type){
	Connection c = null;
    Statement stmt = null;
   ArrayList <String> names=new ArrayList<String>();
   int counter=0;
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:test.db");
      stmt = c.createStatement();
      for (int i=0; i<departs.length; i++){
    	  for (int j=0; j<departs.length; j++){
    		  
    		  
      String sql="Select * from Server where Department =\'"+departs[i]+"\'and Type=\'"+type[j]+"\';";		
      //sql="Select * from Server ;";
      System.out.println("pre update");
      stmt.executeUpdate(sql);
      System.out.println("post update");
     
 
 		ResultSet rs=stmt.executeQuery(sql);
 		 System.out.println("Results:"+rs.next());
 		while (rs.next())
 		{
 			System.out.println("Results:");
 			System.out.println(rs.getInt("id"));
 			System.out.println("after id");
 			String x=rs.getString("name");
 			System.out.println(x);
 			if( names.add(x));
 			counter++;
 			System.out.println("pre type");
 			System.out.println(rs.getString("type"));
 			System.out.println("after type");
 		}      }
    }
    }catch ( Exception e ) {
        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        System.exit(0);
      }
    String [] exampleArray = new String [counter];
	return (String[]) names.toArray(exampleArray);
}
public void loadDefault(	) throws IOException, SQLException {
	  
    
    Connection c = null;
    Statement stmt = null;
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:test.db");
      System.out.println("Opened database successfully");

      stmt = c.createStatement();
      String sql;		
      		sql="CREATE TABLE Server " +
                   "(ID INT PRIMARY KEY     NOT NULL," +
                   " NAME           char(32)    NOT NULL, " + 
                   " Type           char(3)     NOT NULL, " + 
                   " Department      char(10), " + 
                   " CIF       INT, " + 
                   " IP        CHAR(30)        )"; 
      //stmt.executeUpdate(sql);
     int index;
      int name=629;
      int offset=1;
      String [] type={"DB","FE","BE","AG","AD"};
      String [] group={"GCDOCS","PWGSC", "VAC","SSC","RCMP"};
      
      StringBuilder sb=new StringBuilder();
      for (index=1; index<200; index++)
    	  
      {
    	  String depart=group[(int) Math.floor(Math.random()*5)];
    	 String serverType=type[(int) Math.floor(Math.random()*5)];
    	
      String line=("insert or replace INTO SERVER (ID,NAME,Type,Department ,CIF, IP) VALUES ("+index+",'"+depart +(100+index)+"','"+serverType+"','"+depart+"','" +"null"+"','"+"100.121.194."+index+"');");
    	System.out.println(line);
      sb.append(line);
      }
      //sb.append(");");
      sql = sb.toString();
      System.out.println(sql);
    		  /*"INSERT INTO SERVER (ID,NAME,Type,Department ,CIF, IP) " +
              "VALUES (13, 'GCDOCS-5000629', 'DB', 'GCDOCS', 1, 100-121-194-254 );"+
      "INSERT INTO SERVER (ID,NAME,Type,Department ,CIF, IP) " +
      "VALUES (13, 'GCDOCS-5000629', 'DB', 'GCDOCS', 1, 100-121-194-254 );"+ 
      "INSERT INTO SERVER (ID,NAME,Type,Department ,CIF, IP) " +
      "VALUES (13, 'GCDOCS-5000629', 'DB', 'GCDOCS', 1, 100-121-194-254 );"+ 
      "INSERT INTO SERVER (ID,NAME,Type,Department ,CIF, IP) " +
      "VALUES (13, 'GCDOCS-5000629', 'DB', 'GCDOCS', 1, 100-121-194-254 );"+
      "INSERT INTO SERVER (ID,NAME,Type,Department ,CIF, IP) " +
      "VALUES (13, 'GCDOCS-5000629', 'DB', 'GCDOCS', 1, 100-121-194-254 );"; 
      */
      stmt.executeUpdate(sql);
      System.out.println("Results:");
      sql ="Select * from Server where type =\'FE\';";
     
      		ResultSet rs=stmt.executeQuery(sql);
      		 System.out.println("Results:"+rs.next());
      		while (rs.next())
      		{
      			System.out.println("Results:");
      			System.out.println(rs.getInt("id"));
      			System.out.println(rs.getString("name"));
      			System.out.println(rs.getString("type"));
      		}
      stmt.close();
      c.close();
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
    }
    System.out.println("Table created successfully");
}
/*IOException{		


    // The name of the file to open.
    String fileName = "U:\\test\\ServerList2.csv";

    // This will reference one line at a time
    String line = null;

    try {
        // FileReader reads text files in the default encoding.
        FileReader fileReader = 
            new FileReader(fileName);

        // Always wrap FileReader in BufferedReader.
        BufferedReader br = 
            new BufferedReader(fileReader);
          
        	line =br.readLine(); 
        	groupNames=line.split(",");
        	System.out.println("er" +groupNames.length);
        	line =br.readLine();
        	serverNames=line.split(",");
        	System.out.println(line+"er" +serverNames.length);
        	associations= new boolean [groupNames.length] [serverNames.length];
        	for (int i=0; i<groupNames.length; i++) {
        		System.out.println(groupNames[i]);
        		line =br.readLine();
        		String [] paringIndex=(line.split(","));
        		for (int j=0; j<paringIndex.length;j++){
        			System.out.println(paringIndex[j]);
        		associations[i] [Integer.parseInt(paringIndex[j])-1]=true;
        		}
        	}

        

        // Always close files.
        br.close();         
    }
    catch(FileNotFoundException ex) {
        System.out.println(
            "Unable to open file '" + 
            fileName + "'");                
    }
    catch(IOException ex) {
        System.out.println(
            "Error reading file '" 
            + fileName + "'");                  
        // Or we could just do this: 
        // ex.printStackTrace();
    }


	    
	}*/
public void request()
{
	//
}

public static Server addServer(String name, SpecificMap sect){

Server serv=new Server(name, sect);
return serv;
}
public void addGroup(String name)
{
groups.put(name, new ServerGroup(name));
}
public void addGroup(ServerGroup group)
{
groups.put(group.getName(), group);
}
public void deleteGroup(String name)
{
groups.remove(name);
}
public void addSection(String name){
globalSections.put(name, new Section(name));	
}
public void addGroupSections(String [] names, String []ServerGroups)
{
	
}
public void addServer(String serverName, Section [] sections, String [] servergroups){
	
	Server added=new Server(serverName, sections);
	servers.put(serverName, added);
	for (int i= 0; i<servergroups.length; i++)
	{
		 ServerGroup  test=(ServerGroup) (groups.get(servergroups[i]));
		 test.addServer(added);

	}
}
public void deleteServer(String serverName)
{
	groups.remove(serverName);
}
public void pushConfiguration(String [] c, int ...sg ) throws IOException{
	int counter=0;
	LinkedList<String> uniqueServer= new LinkedList<String>();
	boolean []  actualList=new boolean [serverNames.length];
	for (int j=0; j<serverNames.length;j++){
		for (int i=0; i<sg.length; i++)
		{
			if (associations[sg[i]][j])
			{
			actualList[j]=true;

			}
		}
		if(actualList[j])
		{
			counter++;
			uniqueServer.add(serverNames[j]);
		}
		
	}
	System.out.println("push sg server");

	 String [] array = uniqueServer.toArray(new String [counter]);


		 


	pushConfiguration(array, c) ;
}
public void pushConfiguration( String [] servers, String []  change)throws IOException{
	 File log = new File("U:\\test\\changes2.csv");
	    try{
	    if(log.exists()==false){
	            System.out.println("We had to make a new file.");
	            log.createNewFile();
	    }
	    PrintWriter out = new PrintWriter(new FileWriter(log, true));
	    for (int i=0; i<servers.length; i++)
		{
			for (int j=0; j<change.length; j++){
				System.out.println(servers[i]+","+change[j]);
				 //line[i*change.length+j]= 
				out.append(servers[i]+","+change[j]+"\r\n");
				System.out.println(servers[i]+","+change[j]);
				
			}
			
		}
	    
	    out.close();
	    }catch(IOException e)
	    {
	        System.out.println("COULD NOT LOG!! :"+ e.getMessage());
	    }

}
public void pushConfiguration(String []line,  PrintWriter out)
{
	for (int i=0; i<line.length; i++){
		

				    out.println(line[i]+"\r\n");
				    out.append("sup");
	} 
				    //more code
				    
				    //more code
} 
public void pushConfiguration(String line,  PrintWriter out)
{
	
		

				    out.println(line+"\r\n");
				    out.append("sup");
	
				    //more code
				    
				    //more code
}
public void pushConfiguration( boolean modify, PrintWriter out)
{
	//find a use for the boolean later
		System.out.println("enter a server name, section variable and value");
		 String server= in.nextLine();
		 String section= in.nextLine();
		 String variable= in.nextLine();
		 String value= in.nextLine();
		

				String content = server+","+section+","+variable+","+value+","+path;

				pushConfiguration(content, out);
        
}
}