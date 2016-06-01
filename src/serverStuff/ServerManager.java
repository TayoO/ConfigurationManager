package serverStuff;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

class ServerManager
{
	 static String path;
 static SpecificMap groups= new SpecificMap<String, ServerGroup>();
 static SpecificMap servers= new SpecificMap<String, Server>();
 static SpecificMap globalSections= new SpecificMap<String, Section>();
 
 Server[] serverList;
public static void main (String [] args)
{
	ServerManager main= new ServerManager();
ServerGroup test= new ServerGroup("test");
groups.put("tayo",test);
System.out.println("Hello "+((ServerGroup) (groups.get((String)"tayo"))).getName());
main.addGroup("testgroup");
Server [] testServ= new Server [6];
for (int i=0; i<6;i++){
	testServ[i]=addServer("num"+i, globalSections);


test.addServers(testServ);
}

main.groups.get("testgroup");
System.out.println(test.toString());
System.out.println(test.servers.get("num1"). toString());
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



}
