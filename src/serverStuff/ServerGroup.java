package serverStuff;


class ServerGroup{
private String name;
SpecificMap servers= new SpecificMap<String, Server>();
SpecificMap groupSections= new SpecificMap<String, Section>();
public ServerGroup (String name)
{
this.name=name;
}


public String getName()
{
	return name;
}
public void addServer(Server serv){
	servers.put(serv.name, serv);
}
public void setName(String name)
{
	this.name=name;
}
}
