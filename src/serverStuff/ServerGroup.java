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
	this.name=name;
	servers[i].addSection(groupSections);
	this.servers.put(servers[i].name,servers [i]);
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
public String toString()
{
	 StringBuilder sb = new StringBuilder();
sb.append("["+name+"]");
	while(servers.values().iterator().hasNext()){
	sb.append("\r\n "+servers.values().iterator().next());
	}
	return sb.toString();
}
}
