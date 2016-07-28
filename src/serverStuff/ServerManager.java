		package serverStuff;
		import java.io.BufferedReader;
		import java.io.FileWriter;
		import java.io.File;
		import java.io.IOException;
		import java.io.InputStreamReader;
		import java.io.PrintWriter;
		import java.sql.Connection;
		import java.sql.DriverManager;
		import java.sql.ResultSet;
		import java.sql.SQLException;
		import java.sql.Statement;
		import java.util.ArrayList;
		import java.util.Scanner;
		import java.awt.*;
		import javax.swing.*;
		
		class ServerManager
		{
			 static String path = "c:/temp/opentext.ini";
			static final int ARBITRARY_LIMIT=10;
		String powershell="powershell C:\\Users\\oduekea\\workspace\\shelltest.ps1";
		static String [] groupNames;
		 Server[] serverList;
		 static String [] serverNames;
		 boolean [][] associations;
		
		
		static Scanner in = new Scanner(System.in);
		
		 
		public static void main (String [] args) throws IOException, SQLException, InterruptedException
		{			
			ServerManager main= new ServerManager();
			//main.loadDefault();
			 String [] chosenServers = null;	
			 String [] changes;
		System.out.println("powerscript path listed as"+main.powershell);
		main.powershell=in.next();
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
				in.nextLine();
				String section;
				String variable;
				String value;
				//System.out.close();
				System.out.println("What is the path for the inichange file?");
				path=in.nextLine();
				for (int i=0; i<changes.length; i++){
					System.out.println("Input info:");
					
					Thread.sleep(3000);
					System.out.println("Which section?");
					section=in.nextLine();
					System.out.println("Which variable?");
					variable=in.nextLine();
					System.out.println("What value are you setting it too?");
					value=in.nextLine();
				changes[i]=section+","+variable+","+value+","+path;
				}
		 main.pushConfiguration(chosenServers, changes);
		 
		 //This triggers the powershell script so it can implement the changes made in the file
			 Runtime runtime = Runtime.getRuntime();
			 Process proc = (Process) runtime.exec(main.powershell);
		//Prints out the powershell output
			 BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			 System.out.println("Powershell:");
				 System.out.println(br.readLine());
	proc.getOutputStream().close();
			}
		
		//
		
		
		
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
			//Variables for sql connection
			Connection c = null;
		    Statement stmt = null;
		    //array list for srry of unknown size, regular arrays slow to append
		   ArrayList <String> names=new ArrayList<String>();
		   int counter=0;
		    try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:test.db");
		      stmt = c.createStatement();
		      // For each department and type combination there is a unique search
		      for (int i=0; i<departs.length; i++){
		    	  for (int j=0; j<type.length; j++){
		    		  
		    		  
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
		 			if( names.add(x))
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
		    //Examplearray for array format
		    String [] exampleArray = new String [counter];
			return (String[]) names.toArray(exampleArray);
		}

		// Combines the server info with changes than passes it on to push line by line 
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
					for (int j=0; j<change.length; j++)
					{
						System.out.println(servers[i]+","+change[j]);
						 //line[i*change.length+j]= 
						out.append(servers[i]+","+change[j]+"\r\n");
						System.out.println(servers[i]+","+change[j]);
						
					}
					
				}
			    
			    out.close();
			    }
			    catch(IOException e)
			    {
			        System.out.println("COULD NOT LOG!! :"+ e.getMessage());
			    }
		
		}
		//Pushes the lines to file
		// Dedicated method to make it easier to modify how individual lines are formatted
		public void pushConfiguration(String line,  PrintWriter out)
		{
						    out.println(line+"\r\n");
						  
		}
		}
		