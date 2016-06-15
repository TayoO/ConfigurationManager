package serverStuff;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
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
 String [] groupNames;
 Server[] serverList;
 String [] serverNames;
 boolean [][] associations;

static Scanner in = new Scanner(System.in);
 
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
		System.out.println("How many groups?");
		groupIndex = new int [in.nextInt()];
		for (int i=0; i<groupIndex.length; i++){
			groupIndex[i]=in.nextInt();
		}
		main.pushConfiguration(groupIndex, changes);
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
	File Data=new File("U:\\test\\ServerList.csv");

    // The name of the file to open.
    String fileName = "changes.csv";

    // This will reference one line at a time
    String line = null;

    try {
        // FileReader reads text files in the default encoding.
        FileReader fileReader = 
            new FileReader(fileName);

        // Always wrap FileReader in BufferedReader.
        BufferedReader br = 
            new BufferedReader(fileReader);

        while((line = br.readLine()) != null) {
        	groupNames=new String [Integer.getInteger(br.readLine())];
        	System.out.println(groupNames.length);
        	serverNames=new String [Integer.getInteger(br.readLine())];
        	System.out.println(serverNames.length);
        	associations= new boolean [groupNames.length] [serverNames.length];
        	groupNames=br.readLine().split(",");
        	for (int i=0; i<groupNames.length; i++) {
        		String [] paringIndex=(br.readLine().split(","));
        		for (int j=0; j<paringIndex.length;j++){
        		associations[i] [Integer.getInteger(paringIndex[j])]=true;
        		}
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
public void pushConfiguration(int [] sg, String [] c ) throws IOException{
	int counter;
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
			uniqueServer.add(serverNames[j]);
		}
		
	}
	System.out.println("push sg server");




		 


	pushConfiguration((String [])uniqueServer.toArray(), c) ;
}
public void pushConfiguration( String [] servers, String []  change)throws IOException{
	 File log = new File("U:\\test\\changes.csv");
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
	        System.out.println("COULD NOT LOG!!");
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