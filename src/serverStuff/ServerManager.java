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
public void changeLine(Server serv, String sect, Variable var
		){
	String fileName="changes.csv";

	  try {
          // Assume default encoding.
		  
          FileWriter fileWriter =
              new FileWriter(fileName);
          BufferedWriter bufferedWriter =
                  new BufferedWriter(fileWriter);
	String line= (serv.name+"' "+sect+", "+var.fieldName+", "+var.value+","+path);
	bufferedWriter.write(line);
	bufferedWriter.close();
	  }
	catch(IOException ex) {
        System.out.println(
            "Error writing to file '"
            + fileName + "'");
        // Or we could just do this:
        // ex.printStackTrace();
    }

}
}