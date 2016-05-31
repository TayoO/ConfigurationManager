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
for (int i=0; i<6;i++){
	

addServer("num"+i, globalSections);
}
groups.get("testgroup").addServers();
main.groups.get("testgroup");
}
public void loadDefault(){
	
}
public static void addServer(String name, SpecificMap sect){

Server serv=new Server(name, sect);
}

public void addGroup(String name)
{
groups.put(name, new ServerGroup(name));
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
