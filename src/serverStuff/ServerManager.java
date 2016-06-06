package serverStuff;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
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

	
	globalSections.put("default", new Section ("title"));
	ServerManager main= new ServerManager();
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

public void pushConfiguration(String [] sg, String [] s, String change)
{
	//sg= servergroup s= server
	for (int i=0; i<sg.length; i++)
	{
		for (int j=0; j<s.length; j++)
{
	pushConfiguration(sg[i]+","+s[j]+","+change);
}

	}
}
public void pushConfiguration(String line)
{
	  Scanner in = new Scanner(System.in);
		System.out.println("enter a server name, section variable and value");
		 String server= in.nextLine();
		 String section= in.nextLine();
		 String variable= in.nextLine();
		 String value= in.nextLine();
		 
		 try {

				String content = server+","+section+","+variable+","+value+","+path;

				File file = new File("U:\\test\\changes.csv");
				// if file doesnt exists, then create it
				if (!file.exists()) {
					file.createNewFile();
				}

				FileWriter fw = new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
				bw.append(content);
				bw.close();

				System.out.println("Done");

			} catch (IOException e) {
				e.printStackTrace();
			}
}
}
