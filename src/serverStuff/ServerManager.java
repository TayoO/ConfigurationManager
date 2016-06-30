package serverStuff;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

class ServerManager
{
	 static String path = "c:/temp/opentext.ini";

static String [] groupNames;
 static String [] serverNames;

//Which servers are in which groups
 boolean [][] associations;
 String [] groupInfo;
//Allows input directly to Java
static Scanner in = new Scanner(System.in);

static SpecificMap groups= new SpecificMap<String, ServerGroup>();
static SpecificMap servers= new SpecificMap<String, Server>();
static SpecificMap globalSections= new SpecificMap<String, Section>();
 
public static void main (String [] args) throws IOException
{
	
	int [] groupIndex;
	
			String [] pushServers= new String [] {"epsilon","second","third", "fourth", "fifth"};
	String [] changes=new String []  {"data,linkedList,double,"+path,"seconddata,linkedList,double,"+path,"thirddata,linkedList,double,"+path, "fourthdata,linkedList,double,"+path, "fifthdata,linkedList,double,"+path};
	//	globalSections.put("default", new Section ("title"));
	ServerManager main= new ServerManager();
	main.loadDefault();
	main.pushConfiguration(pushServers, changes);
	System.out.println("Do you want to push to groups?");
	if(in.nextBoolean()==true){
		for (int i=0; i<groupNames.length; i++)
		{
			System.out.println(i+ " "+groupNames[i]);
		}
		System.out.println("How many groups do you want to push to?");

		groupIndex = new int [in.nextInt()];
		System.out.println("enter the indexes of the groups");
		for (int i=0; i<groupIndex.length; i++){
			groupIndex[i]=in.nextInt();
		}
		main.pushConfiguration(changes, groupIndex);
	}
	else 
	{
		main.pushConfiguration(pushServers,changes);
	}


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
1,2
3,4
5,6



*/
public void loadDefault(	) throws IOException, IOException{		


    // The name of the file to open.
    String fileName = "U:\\test\\ServerList.csv";

    // This will reference one line at a time
    String line = null;

    try {
        // FileReader reads text files in the default encoding.
        FileReader fileReader = 
            new FileReader(fileName);

        // Always wrap FileReader in BufferedReader.
        BufferedReader br = 
            new BufferedReader(fileReader);

       //unused Line
        line =br.readLine();
        
        	line =br.readLine();
        	System.out.println(line);
        	groupNames=line.split(",");
        	
        	//unsed Lines
        	line =br.readLine();
        	line =br.readLine();
        	
        	line =br.readLine();
        	serverNames=line.split(",");
        	System.out.println(line);
        	
        	//unsed Lines
        	line =br.readLine();
        	line =br.readLine();
        	
        			 // when group index i includes server index j, association i,j is true
        	
        	for (int i=0; i<groupNames.length; i++) {
        		//
        		//System.out.println(groupNames[i]);
        		line =br.readLine();
        		System.out.println("pushing"+line);
        		groups.put(groupNames[i], new ServerGroup (groupNames[i],(line.split(","))));
        		System.out.println(groups.get(groupNames[i]));
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

    }


	    
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
	PriorityQueue<String> uniqueServer= new PriorityQueue<String>();
	StringBuilder sb;

	boolean []  actualList=new boolean [serverNames.length];
//Server [] 
		for (int i=0; i<sg.length; i++)
			
		{
			groups.toString();
			//groups.put(sg[i],new ServerGroup(sg[i]));
			;System.out.println( "sg[i]"+sg[i]+"grw"+groups.get(serverNames[sg[i]]));
			groups.put("erge", new ServerGroup("fwe"));
			
			ServerGroup g=((ServerGroup) groups.get("erge"));
			
					System.out.println(g.toString());
					
			String [] unfiltered=( g.getServerNames());
			for (int j=0; j<unfiltered.length;i++ )
			uniqueServer.add(unfiltered[j]);

			
		}
		
		
	
	System.out.println("push sg server");

	 String [] array = uniqueServer.toArray(new String [counter]);


		 


	pushConfiguration(array, c) ;
	System.out.println("push sg server done");
}
public void pushConfiguration( String [] servers, String []  change)throws IOException{
	 File log = new File("U:\\test\\changes.csv");
	    try{
	    if(log.exists()==false){
	            System.out.println("We had to make a new file.");
	            log.createNewFile();
	    }
	    PrintWriter out = new PrintWriter(new FileWriter(log, true));
	    ArrayList x= new ArrayList<String>(servers.length*change.length);
	    for (int i=0; i<servers.length; i++)
		{
			for (int j=0; j<change.length; j++){
				System.out.println(servers[i]+","+change[j]);
				 //line[i*change.length+j]= 
				x.add(servers[i]+","+change[j]);
				System.out.println(servers[i]+","+change[j]);
				
			}
			
			Files.write(Paths.get("U:\\test\\changes.csv"),x,Charset.forName("UTF-8"));
		
		}
	    
	    out.close();
	    }catch(IOException e)
	    {
	        System.out.println("--COULD NOT LOG!!");
	    }

}
public void pushConfiguration(String []line,  PrintWriter out)
{
	for (int i=0; i<line.length; i++){
		

				    out.println(line[i]+"\r\n");
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