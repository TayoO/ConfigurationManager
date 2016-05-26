package serverStuff;
import java.util.Map;

class ServerManager
{
String pathName;
 static GroupMap groups= new GroupMap();
 Server[] serverList;
public static void main (String [] args)
{
ServerGroup test= new ServerGroup("test");
groups.put("tayo",test);
System.out.println("Hello wlogegrld"+groups.get("tayo").name+test.name);
}
public void addGroup(String name)
{

}
public void deleteGroup(String name)
{

}

public void GrouptoggleServer(String name, String server)
{
if (GroupMap.get(name).serverMap.get(server)==null)
{
 GroupMap[name].addServer[server]
 serverList[
}
else 
GroupMap[name].deleteServer[server]

}