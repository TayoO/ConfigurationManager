package serverStuff;
class Server{
String name;
static SpecificMap sections= new SpecificMap<String, Section>();
String path;

public Server(String name)
{
	System.out.println("name");
this.name=name;
}
public Server(String name, Section [] sects)
{
this.name=name;
for (int i=0;i<sects.length; i++)
{
	sections.put(name, sects[i]);
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
	while(sections.values().iterator().hasNext()){
	sb.append("\r\n "+sections.values().iterator().next());
	}
	return sb.toString();
}




}
