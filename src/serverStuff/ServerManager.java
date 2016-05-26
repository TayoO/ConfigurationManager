package serverStuff;
import java.util.Map;

class ServerManager
{
String pathName;
 static SpecificMap groups= new SpecificMap<String, ServerGroup>();
 static SpecificMap servers= new SpecificMap<String, Server>();
 SpecificMap globalSections= new SpecificMap<String, Section>();
 Server[] serverList;
public static void main (String [] args)
{
ServerGroup test= new ServerGroup("test");
groups.put("tayo",test);
System.out.println("Hello "+((ServerGroup) (groups.get((String)"tayo"))).getName());
}
public void addGroup(String name)
{

}
public void deleteGroup(String name)
{

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
}