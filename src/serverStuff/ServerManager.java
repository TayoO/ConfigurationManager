package serverStuff;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
class ServerManager
{
	 static String path = "c:/temp/opentext.ini";
	 
 static SpecificMap groups= new SpecificMap<String, ServerGroup>();
 static SpecificMap servers= new SpecificMap<String, Server>();
 static SpecificMap globalSections= new SpecificMap<String, Section>();
 
 Server[] serverList;
public static void main (String [] args)
{

	String [] pushServers= new String [] {"hello","second","third", "fourth", "fifth"};
	String [] changes=new String []  {"data,linkedList,double","seconddata,linkedList,double","thirddata,linkedList,double", "fourthdata,linkedList,double", "fifthdata,linkedList,double"};
	
	globalSections.put("default", new Section ("title"));
	ServerManager main= new ServerManager();
	main.pushConfiguration(pushServers, changes);
ServerGroup test= new ServerGroup("test");
groups.put("tayo",test);
main.addGroup("testgroup");
Server [] testServ= new Server [6];
for (int i=0; i<6;i++){
	testServ[i]=addServer("num"+i, globalSections);
	System.out.println("line26 "+testServ[i].toString());
	testServ[i]=new Server("num"+i, globalSections);
	System.out.println("line28 "+testServ[i].toString());
	
test.addServers(testServ);
System.out.println(test.toString());
}

//main.groups.get("testgroup");
System.out.println(test.toString());
test.servers.put("hey", new Server("hey"));
System.out.println(test.servers.get("hey"). toString());
}
public void loadDefault(){
	
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
public void pushConfiguration(ServerGroup [] sg, Server [] server, String [] c ){
	
	SpecificMap allServ=new SpecificMap <String, Server>();
	// Put and put all take into account duplicates for length

	for (int i=0; i<sg.length; i++){
		allServ.putAll(sg[i].servers);
	}
	for (int i=0; i<server.length;i++){
		allServ.put(server[i].name, server[i]);
	}
			
 String [] uniqueServers=new String[allServ.length];
		 
	for (int i=0; i<allServ.length;i++)
	uniqueServers [i]=(String)allServ.keySet().iterator().next();
	pushConfiguration(uniqueServers, c);
}
public void pushConfiguration( String [] servers, String []  change)
{
	//sg= servergroup s= server
	for (int i=0; i<servers.length; i++)
	{

	pushConfiguration(servers[i]+","+change);


	}
}
public void pushConfiguration(String line)
{
	 try {
			File file = new File("U:\\test\\changes.csv");
		
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
			BufferedReader br = new BufferedReader(new FileReader("U:\\test\\changes.csv"));
			try {
			    StringBuilder sb = new StringBuilder();
			    line += br.readLine();
			    int counter=0;
			    while (br.readLine() != null) {
			    	counter++;
			    	System.out.println("line num "+counter);
			        sb.append(line);
			        sb.append(System.lineSeparator());
			        line += br.readLine();
			    }
			    String everything = sb.toString();
			} finally {
			    br.close();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);

		
			bw.append(line);
			bw.close();
		    
			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		}
}

public void pushConfiguration(String line, boolean modify)
{
	//find a use for the boolean later
	  Scanner in = new Scanner(System.in);
		System.out.println("enter a server name, section variable and value");
		 String server= in.nextLine();
		 String section= in.nextLine();
		 String variable= in.nextLine();
		 String value= in.nextLine();
		

				String content = server+","+section+","+variable+","+value+","+path;

				pushConfiguration(content);
        
}
}