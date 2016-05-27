package serverStuff;
class Section{
String name;
static SpecificMap variables= new SpecificMap<String, Variable>();
public Section (String name)
{
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
public String toString()
{
	 StringBuilder sb = new StringBuilder();
 sb.append("["+name+"]");
	while(variables.values().iterator().hasNext()){
	sb.append("\r\n "+variables.values().iterator().next());
	}
	return sb.toString();
}

}
