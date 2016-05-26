package serverStuff;
class Server{
String name;
Section [] sectionList;
String path;

public Server(String name, Section ... list)
{
this.name=name;
sectionList = new Section [list.length];
for (int i=0; i<list.length; i++)
sectionList[i]=list[i];
}
}
