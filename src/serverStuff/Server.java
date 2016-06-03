package serverStuff;
class Server{
String name;
static SpecificMap sections= new SpecificMap<String, Section>();
String path;

public Server(String name)
{
	
this.name=name;
}
public Server(String name, Section [] sects)
{
this.name=name;
for (int i=0;i<sects.length; i++)
{
	System.out.println("server created name="+name);
	sections.put(name, sects[i]);
	System.out.println("server  put name="+name);
}

}
public Server(String name, SpecificMap sect)
{
this.name=name;
sections.putAll( sect);
}

public void addSection(Section [] sections){
	for (int i=0; i<sections.length; i++)
	{
		this.sections.put(sections[i].name, sections[i]);
	}
}
public void addSection(SpecificMap sect){
sections.putAll( sect);
}
public String toString()
{
	 StringBuilder sb = new StringBuilder();
sb.append("["+name+"]");
	sb.append(sections.toString());
	return sb.toString();
}




}
