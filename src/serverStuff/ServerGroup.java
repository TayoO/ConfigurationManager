package serverStuff;


class ServerGroup{
private String name;
Server [] groupMembers;

public ServerGroup (String name)
{
System.out.println("tayo rules");
this.name=name;
}
public String getName()
{
	return name;
}
public void setName(String name)
{
	this.name=name;
}
}
