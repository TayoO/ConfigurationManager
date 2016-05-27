package serverStuff;
import java.util.Map;

class ServerManager
{
String pathName;
 static SpecificMap groups= new SpecificMap<String, ServerGroup>();
 static SpecificMap servers= new SpecificMap<String, Server>();
 static SpecificMap globalSections= new SpecificMap<String, Section>();
 Server[] serverList;
public static void main (String [] args)
{
ServerGroup test= new ServerGroup("test");
groups.put("tayo",test);
System.out.println("Hello "+((ServerGroup) (groups.get((String)"tayo"))).getName());
}
public void loadDefault(){
	
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
public void addServer(String serverName, String [] sections, String [] servergroups){
	servers.put(serverName, new Server(serverName, sections));
	for (int i= 0; i<servergroups.length; i++)
	{
		groups.get(servergroups[i]).addServer(severName);
	}
}
public void deleteServer(String serverName)
{
	groups.remove(serverName);
}
/*
public void GrouptoggleServer(String name, String server)
{
if (groups.get(name).serverMap.get(server)==null)
{
 GroupMap[name].addServer[server]
 serverList[
}
else 
GroupMap[name].deleteServer[server]
*/
public void pushConfiguration(){
	
}
}