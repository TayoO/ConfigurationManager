package serverStuff;


class ServerGroup{
private String name;
SpecificMap servers= new SpecificMap<String, Server>();
SpecificMap groupSections= new SpecificMap<String, Section>();
public ServerGroup (String name)
{
this.name=name;
}

public ServerGroup (String name, Server [] servers)
{
this.name=name;
for (int i=0; i<servers.length; i++)
{

	this.servers.put(servers[i].name,servers [i]);
}
}
public ServerGroup (String name, String [] serverNames)
{
this.name=name;
for (int i=0; i<serverNames.length; i++)
{
	servers.put (serverNames[i], new Server(serverNames[i]));

}
}

public void addServers( Server[]serv)
{
	for (int i=0; i<servers.length; i++)
	{
	servers.put(serv[i].name, serv);
	}
}
public void addServers( SpecificMap m)
{
	for (int i=0; i<servers.length; i++)
	{
	servers.putAll(m);
	}
}
public String getName()
{
	return name;
}
public void addServer(Server serv){
	System.out.println("hey");
	servers.put(serv.name, serv);
}
public void setName(String name)
{
	this.name=name;
}

public Server [] getServers(){
return (Server []) servers.entrySet().toArray();
}

public String [] getServerNames(){
return (String []) servers.keySet().toArray();
}

public String toString()
{System.out.println("toString"+name);
	 StringBuilder sb = new StringBuilder();
sb.append("["+name+"]");
System.out.println("toString"+name);
String prints;

prints=servers.toString();


	sb.append("\r\n snfs"+prints);


	return sb.toString();

}

}
