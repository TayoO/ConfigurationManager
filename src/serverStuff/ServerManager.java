package serverStuff;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
class ServerManager
{
	 static String path = "c:/temp/opentext.ini";
	 
 static SpecificMap groups= new SpecificMap<String, ServerGroup>();
 static SpecificMap servers= new SpecificMap<String, Server>();
 static SpecificMap globalSections= new SpecificMap<String, Section>();
 
 Server[] serverList;
public static void main (String [] args) throws IOException
{

	String [] pushServers= new String [] {"epsilon","second","third", "fourth", "fifth"};
	String [] changes=new String []  {"data,linkedList,double","seconddata,linkedList,double","thirddata,linkedList,double", "fourthdata,linkedList,double", "fifthdata,linkedList,double"};
	
//	globalSections.put("default", new Section ("title"));
	ServerManager main= new ServerManager();
	main.pushConfiguration(pushServers, changes);


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
public void pushConfiguration(ServerGroup [] sg, Server [] server, String [] c ) throws IOException{
	System.out.println("push sg server");
	
	SpecificMap allServ=new SpecificMap <String, Server>();
	// Put and put all take into account duplicates for length

	for (int i=0; i<sg.length-2; i++){
		allServ.putAll(sg[i].servers);
	}
	for (int i=0; i<server.length-2;i++){
		allServ.put(server[i].name, server[i]);
	}
			
 String [] uniqueServers=new String[allServ.length];
		 
	for (int i=0; i<allServ.length;i++)
	uniqueServers [i]=(String)allServ.keySet().iterator().next();
	pushConfiguration(uniqueServers, c) ;
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
{

		
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
			/*
			BufferedReader br = new BufferedReader(new FileReader("U:\\test\\changes.csv"));
		int counter=0;
		 LinkedList<String>  sb= new LinkedList<String>();
		while(br.!=null)
		{
		counter++;
			   
			    System.out.println("line num "+counter);
			    sb.add(br.readLine()+System.lineSeparator());
			    System.out.println("sb: "+sb.getFirst());
			    line += br.readLine();

		}
			        		        
			    

		
			    br.close();
			

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);

		
			bw.append(sb.removeFirst());
			bw.close();
		    
			System.out.println("Done");*/

		
public void pushConfiguration(String line,  PrintWriter out)
{
	
		

				    out.println(line+"\r\n");
				    out.append("sup");
	
				    //more code
				    
				    //more code
}
public void pushConfiguration(String line, boolean modify, PrintWriter out)
{
	//find a use for the boolean later
	  Scanner in = new Scanner(System.in);
		System.out.println("enter a server name, section variable and value");
		 String server= in.nextLine();
		 String section= in.nextLine();
		 String variable= in.nextLine();
		 String value= in.nextLine();
		

				String content = server+","+section+","+variable+","+value+","+path;

				pushConfiguration(content, out);
        
}
}