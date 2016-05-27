package serverStuff;
class Server{
String name;
static SpecificMap sections= new SpecificMap<String, Section>();
String path;

public Server(String name, Section ... list)
{
this.name=name;
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
