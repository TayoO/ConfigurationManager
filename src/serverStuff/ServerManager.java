package serverStuff;

import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.text.JTextComponent;



class ServerManager {
	//static String path = "c:/temp/opentext.ini";
	
	//This is the path to the powershell code
	String powershell = "powershell C:\\Users\\oduekea\\workspace\\shelltest.ps1";

	// Instead of creating unique gridbag constraints each time, I just use one and modify it for each situation
	//Another possibility would be to use unique constraints for back and done buttons to keep it consistent across screens
	static GridBagConstraints globalConstraints = new GridBagConstraints();

	
	
//Outside of droplist applets all graphics go on this one frame.
	final BasicFrame frameManager = new BasicFrame();

	// Intro panel is the first panel that appears when 
	final JPanel introPan = new JPanel(new GridBagLayout());
	//static Scanner in = new Scanner(System.in);

// An array is used because final objects can't be set to new values, but changing values inside objects doesn't count as a change

	
	 


		
	public static void main(String[] args) throws IOException, SQLException, InterruptedException {
		//initiating an new configuration object
		
		ServerManager main = new ServerManager();
		//running the configuration object
		SQLShowServs();
		main.startManager();
}
	/*
	 * file code: numGroups numServers listOfGroupNames seperated by commas
	 * ListOfServerNames seperated by commas numGroups lines each with list of
	 * servers indexes corresponding to respective groups seperated by commas
	 * Example] 5 6 frontEnd, BackEnd, PWGSC, SSC, RCMP PWFE, PWBE, SSCFE,
	 * SSCBE, RCMPFE, RCMPBE 1,3,5 2,4,6 1,2h 5,6
	 */
	
	public void serverManager(){
		//Insets means no matter what else, all buttons will maintain are certain distance from the edges of the panel/frame. The larger the integer values passed, the further from the edges it goes.
		//globalConstraints.insets = new Insets(40, 40, 40, 40);
	}
	
	
	//startManager runs the introPan. The intro pan is the first panel that the user sees. 
	//From here the user can choose to manage the list of servers and go to serverManagerList, or try to push configurations, which means they must first go to chooseServers to pick which servers they push too.
	public void startManager(){
		globalConstraints.insets = new Insets(40, 40, 40, 40);
		//Constraints

		globalConstraints.gridx = 100;
		globalConstraints.gridy = 40;
		
		
		// Displays the tile of the application
		final JLabel title = new JLabel("Content Server Configuration Manager");
		globalConstraints.gridy = 0;
		globalConstraints.gridx = 40;
		introPan.add(title, globalConstraints);

		//Button to manage the list of Servers
		JButton serv  = new JButton("Manage ServerList");
		serv.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frameManager.getContentPane().removeAll();
				serverListManagement();
			}
		});
		globalConstraints.gridy = 40;
		introPan.add(serv, globalConstraints);
		
		
		//Button to push configurations
		JButton config = new JButton("Push configurations");
		globalConstraints.gridx = 100;
        introPan.add(config, globalConstraints);

		//globalConstraints.gridy = 70;
		//final JTextArea powerShellInfoText = new JTextArea("powerscript path listed as" + this.powershell);JButton serv = new JButton("Manage ServerList");
		//introPan.add(powerShellInfoText, globalConstraints);
		
/*
		introButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String str = powerShellInfoText.getText();
				powerShellInfoText.setText(" test");
			}
		});
		*/
		config.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frameManager.getContentPane().removeAll();
				
				try {
					chooseServers();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		frameManager.getContentPane().removeAll();
		frameManager.add(introPan);
		System.out.println("intro pan");
		frameManager.revalidate();
		frameManager.pack();
		frameManager.setVisible(true);
	
	}
	
	//This class directly access the SQL code to get the servers that have any combination of the given department or type options
	protected static void SQLShowServs() {
		Connection c = null;
		Statement stmt = null;
		ArrayList<String> names = new ArrayList<String>();
		int counter=0;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
			String sql="select * from sqlite_master";
			stmt = c.createStatement();
			ResultSet rs =stmt.executeQuery(sql);
			System.out.print(" Databasename1: "+rs.getString("name"));
			System.out.print("   "+rs.next()+" Databasename2: "+rs.getString("name"));
			System.out.print("   "+rs.next()+" Databasename3: "+rs.getString("name"));
			System.out.print("   "+rs.next());
		stmt = c.createStatement();
		sql = "Select * from Server;";
		stmt.executeUpdate(sql);
		rs = stmt.executeQuery(sql);
		System.out.println("Results:");
		while (rs.next()) {

			System.out.print(" id: " +rs.getInt("id"));
			String x = rs.getString("name");
			System.out.print(" name: " +x);
			if (names.add(x))
				counter++;
			System.out.print(" type: " +rs.getString("type"));
			System.out.print(" dep: " +rs.getString("Department"));
			System.out.print(" CIF: " +rs.getString("CIF"));
			System.out.println(" IP: " +rs.getString("IP"));
		}
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		 try { 
		        stmt.close();
		    } 
		    catch (Exception ex) {
		    }

		    try { 
		        c.close();
		    } 
		    catch (Exception ex) {
		        System.out.println ("Error closing connections");
		    }
		String[] exampleArray = new String[counter];
		
	}
	
	public static String[] SQLGroups(String[] departs, String[] type) {
		// Variables for sql connection
		Connection c = null;
		Statement stmt = null;
		// array list for srry of unknown size, regular arrays slow to append
		ArrayList<String> names = new ArrayList<String>();
		int counter = 0;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
			stmt = c.createStatement();
			// For each department and type combination there is a unique search
			for (int i = 0; i < departs.length; i++) {
				for (int j = 0; j < type.length; j++) {

					String sql = "Select * from Server where Department =\'" + departs[i] + "\'and Type=\'" + type[j]
							+ "\';";
					// sql="Select * from Server ;";
					stmt.executeUpdate(sql);
					ResultSet rs = stmt.executeQuery(sql);
					System.out.println("Results:" + rs.next());
					while (rs.next()) {
						System.out.println("Results:");
						System.out.println(rs.getInt("id"));
						System.out.println("after id");
						String x = rs.getString("name");
						System.out.println(x);
						if (names.add(x))
							counter++;
						System.out.println(rs.getString("type"));
					}
				}
			}
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		// Examplearray for array format
		String[] exampleArray = new String[counter];
		return (String[]) names.toArray(exampleArray);
	}

	
	//This class adds a server with the given values
	
	public static void addServerSQL(String id, String number, String type, String dep, String cif, String ip) throws ClassNotFoundException, SQLException{

	    
	    Connection c = null;
		Statement stmt = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
		    String sql="INSERT INTO SERVER (ID,NAME,Type,Department ,CIF, IP) " +
		            "VALUES ('"+id+"', '"+dep+"-"+number+"', '"+type+"', '"+dep+"', '"+cif+"', '"+ip+"' );"; 
			stmt = c.createStatement();
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		 try { 
		        stmt.close();
		    } 
		    catch (Exception ex) {
		    }

		    try { 
		        c.close();
		    } 
		    catch (Exception ex) {
		        System.out.println ("Error closing connections");
		    }
	}
	
//This method modifies a column given by the column in the server, given by the id,  to the value given in the change
	public static void modifyServerSQL(int id, String columnName, String change) {

	    
	    Connection c = null;
		Statement stmt = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
		    String sql="UPDATE SERVER SET "+columnName+" ='"+change+"' WHERE ID ="+id+";"; 
			stmt = c.createStatement();
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		 try { 
		        stmt.close();
		    } 
		    catch (Exception ex) {
		    }

		    try { 
		        c.close();
		    } 
		    catch (Exception ex) {
		        System.out.println ("Error closing connections");
		    }
	}
//This methods deletes the server given by the id
	
	public static void deleteServerSQL(int id){
	  Connection c = null;
			Statement stmt = null;
			
			try {
				Class.forName("org.sqlite.JDBC");
				c = DriverManager.getConnection("jdbc:sqlite:test.db");
				stmt = c.createStatement();
				
			    String sql="SELECT NAME FROM SERVER WHERE ID ="+id+";";
			    ResultSet rs = stmt.executeQuery(sql);
			    if (rs.next())
			    {
			    System.out.println("Results:" +rs.getString("name"));
			    		sql="DELETE * FROM SERVER WHERE ID = "+id+";"; 
			    		 sql="SELECT NAME FROM SERVER WHERE ID ="+id+";";
					     rs = stmt.executeQuery(sql);
					    if (rs.next())
					    {
					    System.out.println("Result not deleted:" +rs.getString("name"));
		
					    }
					    else
					    {
					    	System.out.println("id deleted found");
					    }
				stmt.executeUpdate(sql);
			    }
			    else
			    {
			    	System.out.println("id not found");
			    }
			    
			} catch (Exception e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
				System.exit(0);
			}
	
}
	//This method, when working properly, gets the list of departments and the list of types that are to be pushed to from dropDownListThread objects. 
	public void getGroups() throws InterruptedException{

		String [] departs={"SSC","RCMP"};
		
	
	
       
		String [] types={"AC","FE"};
		String [] typeList={"AD","AG","FE","ID"};
		/*
	System.out.println("get groups");
	

		
		
		synchronized(chooseDep){
            try{
            	
                
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            departs=chooseDep.results;
 if (departs.length==0){
	 System.out.println("no departments chosen");
 }
 else if (departs.length==1){
	 System.out.println("The department is "+departs[0]);
 }
 else{
	 System.out.println("The departments are:");
	 for (int i=0;i<departs.length; i++)
	 {
		 System.out.println(departs[i]);
	 }
 }
           
        }
        */
		// Copy pasted code from previous lines.
		/*
		
		 
		
		synchronized(chooseType){
            try{
            	
                
                
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            
		DropDownListThread chooseType=new DropDownListThread(typeList);
		chooseType.run( 200, 125);
		System.out.println("Waiting for types to be choosen...");
            types=chooseType.results;
            */
		
            
 if (types.length==0){
	 System.out.println("no types chosen");
 }
 else if (types.length==1){
	 System.out.println("The type is "+types[0]);
 }
 else{
	 System.out.println("The types are:");
	 for (int i=0;i<types.length; i++)
	 {
		 System.out.println(types[i]);
	 }
 }
		
		// main.loadDefault();


		
		//main.ServerManagerFrame();
		//main.configFrame();
		System.out.println("powerscript path listed as" + this.powershell);

		configurations(SQLGroups(departs, types));
		
		
	}
// Choosing which server to push configuration to	

	// Allows the user to choose Servers, either by individual servers or by groups
	public void chooseServers() throws InterruptedException{

		 final JPanel choosePan = new JPanel(new GridBagLayout());
		

		JButton toGroups = new JButton("Push to Groups");
		globalConstraints.gridx=40;
		globalConstraints.gridy=40;
		choosePan.add(toGroups,globalConstraints);
		toGroups.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					getGroups();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		JButton toServ   = new JButton("Push to individual Servers");
		globalConstraints.gridx=80;
		toServ.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				getServs();
			}
		});
		choosePan.add(toServ,globalConstraints);
		
		JButton back = new JButton ("Back");
		globalConstraints.gridx=40;
		globalConstraints.gridy=80;
		choosePan.add(back, globalConstraints);
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				startManager();
			}
		});
		
		JButton done = new JButton ("Done");
		globalConstraints.gridx=40;
		globalConstraints.gridy=80;
		choosePan.add(done, globalConstraints);
		done.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				startManager();
			}
		});
		
		
		frameManager.getContentPane().removeAll();
		frameManager.add(choosePan);
		System.out.println("choose pan");
		frameManager.revalidate();
		frameManager.pack();
		frameManager.setVisible(true);
		
		
		
	}
	// Combines the server info with changes than passes it on to push line by
	// line
	// Chooses servers by directly entering the server names

	protected void getServs() {
		final ArrayList<String> servs = new ArrayList<String>();
		
		final JPanel servPan = new JPanel(new GridBagLayout());
		final JTextArea editTextArea = new JTextArea("Enter servername here");

		JButton servButton = new JButton("addServer");
		globalConstraints.gridx = 100;
		globalConstraints.gridy = 100;
		servPan.add(servButton, globalConstraints);

		globalConstraints.gridy = 40;
		servPan.add(editTextArea, globalConstraints);
		globalConstraints.gridx = 40;
		JButton done = new JButton("Done");
		servPan.add(done,globalConstraints);
		done.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String [] temp = {};
				configurations((servs).toArray(temp));
				
			}
		});
		frameManager.getContentPane().removeAll();
frameManager.add(servPan);
System.out.println("serv pan");
frameManager.revalidate();
frameManager.pack();
frameManager.setVisible(true);
		servButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				(servs).add( editTextArea.getText());
				editTextArea.setText(" ");
				
			}
		});
	
	}
	
	// This methods gets a list of servers to push to and a list of changes to push and pushes all the changes to each server
public void pushConfiguration(String[] servers, String[] change) throws IOException {
		File log = new File("U:\\test\\changes2.csv");
		try {
			if (log.exists() == false) {
				System.out.println("We had to make a new file.");
				log.createNewFile();
			}
			PrintWriter out = new PrintWriter(new FileWriter(log, true));
			for (int i = 0; i < servers.length; i++) {
				for (int j = 0; j < change.length; j++) {
					
					// line[i*change.length+j]=
					out.append(servers[i] + "," + change[j] + "\r\n");
					System.out.println(servers[i] + "," + change[j]);

				}

			}

			out.close();
		} catch (IOException e) {
			System.out.println("COULD NOT LOG!! :" + e.getMessage());
		}

	}

	// Pushes the lines to file
	// Dedicated method to make it easier to modify how individual lines are
	// formatted
	
	
	
	
	
	//Autopanel is the only panel that is softcoded. In this context what that means is that the contents of the panel can vary based of what values are passed to the method. For this reason, flowlayouts are used instead of hardcoded gridConstraints.
 public String []  autoPanel(final JPanel nextPan, String ... sects){
		//FlowLayout automatically formats components inside  in a horizzontal layout based on the order they are added. This removes the need for gridConstraints. It is used when the formatting need to be done automatically and scale for various numbers of buttons instead of hard coded
		
		FlowLayout autoLayout = new FlowLayout();

		
		 final JPanel autoPan= new JPanel(autoLayout);
		 
	 final boolean [] finished= new boolean [1];
	 finished[0]=false;
		JButton back= new JButton("Back");
		autoPan.add(back);
	 back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				startManager();
			}
		});
		
		String [] output=new String [sects.length];
		
		final JComponent [] comp= new JComponent[sects.length];
		for (int i=0; i<sects.length; i++){
	
			if (i%2==1){
				// Only used since final variables must be used for action performed.
				
				// only final objects can be referenced in the actionListener
				final int unchangeableInt=i;
				
				comp[i] = new JTextField(sects[i]);
			( (JTextField) comp[i]).addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
                            ((JTextComponent) comp[unchangeableInt]).getText();
					}
				});
			}
			else{
				comp[i] = new JLabel(sects[i].substring(1,sects[i].length()));
			}
			autoPan.add(comp[i]);
			
		}
	 JButton done = new JButton("Done");
	 

	done.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				frameManager.getContentPane().removeAll();
				frameManager.add(nextPan);
				finished[1]=true;
				System.out.println("next pan");
				frameManager.getContentPane().add(nextPan, BorderLayout.WEST);
				frameManager.revalidate();
						frameManager.pack();
						frameManager.setVisible(true);
			 }
		});
	
	 autoPan.add(done);
	 System.out.println("removeAll");
	 frameManager.getContentPane().removeAll();
		frameManager.add(autoPan);
		System.out.println("auto pan");
		frameManager.getContentPane().add(autoPan, BorderLayout.WEST);
		frameManager.revalidate();
				frameManager.pack();
				frameManager.setVisible(true);
				while(!finished[0])
				{
					
				}
	return output;	
 }
 
 // This frame gives options on how to modify the server list. Currently only addServer works, deleteServer has also been attempted but buggy
	public void serverListManagement(){
		FlowLayout serverLayout = new FlowLayout();
		 JPanel listPan = new JPanel(serverLayout);
		
		//listPan.setLayout(serverLayout);
		JButton addingGroups = new JButton("Add Groups");
		JButton modifyGroups = new JButton("Modify Groups");
		JButton deleteGroups = new JButton("Delete Groups");
		globalConstraints.gridx = 100;
		globalConstraints.gridy = 100;
		JButton addingServers = new JButton("Add Servers");
		JButton modifyServers = new JButton("Modify Servers");
		JButton deleteServers = new JButton("Delete Servers");
listPan.add(addingGroups, globalConstraints);
listPan.add(modifyGroups, globalConstraints);
listPan.add(deleteGroups, globalConstraints);
listPan.add(addingServers, globalConstraints);
listPan.add(modifyServers, globalConstraints);
listPan.add(deleteServers, globalConstraints);

//final array of 1 used so that the value of the JPanel object can be modified without changing the way it is refferenced, so it can be used inside an actionListener
final JPanel [] list= new JPanel[1];
list[0]=listPan;
frameManager.getContentPane().removeAll();
		System.out.println("list pan");
		frameManager.getContentPane().add(listPan);
		frameManager.revalidate();
				frameManager.pack();
				frameManager.setVisible(true);
				

	addingGroups.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		}
	})	;		


	
	modifyGroups.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			
		}
	})	;		


deleteGroups.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent e){
		
	}
})	;		



addingServers.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent e){
		FlowLayout addLayout = new FlowLayout();
		 final JPanel addPan= new JPanel(addLayout);

		
	JLabel id = new JLabel ("Enter id here, must be a number");
	final TextField idInput= new TextField ("");
	JLabel number = new JLabel ("Enter the server number here (the e.g for GCDOCS-45393 type 45393");
	final TextField numberInput= new TextField ("");
	final JLabel type = new JLabel ("Enter type here 2 characters");
	final TextField typeInput= new TextField ("");
	JLabel dep = new JLabel ("Enter the department acronym here eg SSC");
	final TextField depInput= new TextField ("");
	JLabel cif = new JLabel ("Enter cif here must be a number eg 1");
	final TextField cifInput= new TextField ("");
	final JLabel ip = new JLabel ("Enter ip here 1430-3212-2342");
	final TextField ipInput= new TextField ("");
	addPan.add(id);
	addPan.add(idInput);
	addPan.add(number);
	addPan.add(numberInput);
	addPan.add(type);
	addPan.add(typeInput);
	addPan.add(dep);
	addPan.add(depInput);
	addPan.add(cif);
	addPan.add(cifInput);
	addPan.add(ip);
	addPan.add(ipInput);
	JButton done=new JButton("Done");
	addPan.add(done);
	done.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			try{
			addServerSQL(idInput.getText(), numberInput.getText(), typeInput.getText(), depInput.getText(), cifInput.getText(), ipInput.getText());
			}
			catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	});
	
	JButton back= new JButton("Back");
	addPan.add(back);
	back.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			startManager();
		}
	});
	
	
		frameManager.getContentPane().removeAll();
		
		System.out.println("add pan");
		//frameManager.getContentPane().add(list[0]);
		frameManager.add(addPan);
		frameManager.revalidate();
				frameManager.pack();
				frameManager.setVisible(true);
		//String [] results=addPan();//autoPanel(introPan, "lid:","tEnter id here, must be a number","lnumber","tEnter the server number here (the e.g for GCDOCS-45393 type 45393)","ltype","tEnter type here 2 characters", "ldep:", "tEnter the department acronym here eg SSC","lcif","tEnter cif here must be a number eg 1","lip:","tEnter ip here 1430-3212-2342");
		//System.out.println(results[0]+results[1]+results[2]+results[3]+results[4]+results[5]+results[6]+results[7]);	
System.out.println("adding Serv");


	}
})	;		



modifyServers.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent e){
		FlowLayout addLayout = new FlowLayout();
		 final JPanel changePan= new JPanel(addLayout);

		
	JLabel id = new JLabel ("Enter id here, must be a number");
	final TextField idInput= new TextField ("");
	JLabel column = new JLabel ("Enter the column name here, either number, type, department, CIF, IP");
	final TextField columnInput= new TextField ("");
	final JLabel change = new JLabel ("Enter what you want to change the value to");
	final TextField changeInput= new TextField ("");
	changePan.add(id);
	changePan.add(idInput);
	changePan.add(column);
	changePan.add(columnInput);
	changePan.add(change);
	changePan.add(changeInput);

	JButton done=new JButton("Done");
	changePan.add(done);
	done.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			try{
			modifyServerSQL(Integer.parseInt(idInput.getText()), columnInput.getText(), changeInput.getText());
			}
			catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	});
	
	JButton back= new JButton("Back");
	changePan.add(back);
	back.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			startManager();
		}
	});
	
	
		frameManager.getContentPane().removeAll();
		
		System.out.println("change pan");
		//frameManager.getContentPane().add(list[0]);
		frameManager.add(changePan);
		frameManager.revalidate();
				frameManager.pack();
				frameManager.setVisible(true);
		//String [] results=addPan();//autoPanel(introPan, "lid:","tEnter id here, must be a number","lnumber","tEnter the server number here (the e.g for GCDOCS-45393 type 45393)","ltype","tEnter type here 2 characters", "ldep:", "tEnter the department acronym here eg SSC","lcif","tEnter cif here must be a number eg 1","lip:","tEnter ip here 1430-3212-2342");
		//System.out.println(results[0]+results[1]+results[2]+results[3]+results[4]+results[5]+results[6]+results[7]);	
System.out.println("changing Serv");


	}
})	;		



deleteServers.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent e){
		FlowLayout delLayout = new FlowLayout();
		 final JPanel delPan= new JPanel(delLayout);
		 JLabel id = new JLabel ("id");
			final TextField idInput= new TextField ("");
			JButton done=new JButton("Done");
			delPan.add(done);
			delPan.add(id);
			delPan.add(idInput);
			done.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					try{
					deleteServerSQL(Integer.parseInt(idInput.getText()));
					//System.out.println("deleting Server"+idInput.getText());
					}
					catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			});
			
			JButton back= new JButton("Back");
			delPan.add(back);
			back.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					startManager();
				}
			});
			frameManager.getContentPane().removeAll();
			
			System.out.println("del pan");
			//frameManager.getContentPane().add(list[0]);
			frameManager.add(delPan);
			frameManager.revalidate();
					frameManager.pack();
					frameManager.setVisible(true);
	}
});		


	}
	// This method gets the changes that are going to be pushed and calls the method to push them passing the servers that they are being pushed to
    public void configurations(final String [] serversToPushTo) {
		final ArrayList<String> changes = new ArrayList<String>();
		
		// final int counter=0;
		


		

		final JPanel configPan = new JPanel(new GridBagLayout());
		
		
		
		JButton configButton = new JButton("New Configuration");
		JButton back= new JButton("Back");
		JButton push = new JButton("PushConfiguration");
		JButton Done = new JButton("DonePushing");
		final JTextArea editTextArea = new JTextArea("ExampleServer,ExampleSection,ExampleVariable,ExampleValue");
		
		globalConstraints.gridx = 100;
		globalConstraints.gridy = 100;
		configPan.add(configButton, globalConstraints);
		
		
		
		globalConstraints.gridx = 80;
		configPan.add(back, globalConstraints);
		
		globalConstraints.gridx = 60;
		
		configPan.add(push, globalConstraints);
		
		globalConstraints.gridy = 60;
		configPan.add(back, globalConstraints);
		
		globalConstraints.gridy = 80;
		configPan.add(Done, globalConstraints);
		
		globalConstraints.gridy = 40;
		configPan.add(editTextArea, globalConstraints);
		
		configButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String str = editTextArea.getText();
				editTextArea.setText(" ");
				changes.add(str);
			}
		});
		
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				startManager();
			}
		});
		
		
		
		
		
		
		Done.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] test = new String[0];
				try {
					System.out.println("pushing changes"+changes.toArray(test)[0]);
					pushConfiguration(serversToPushTo, changes.toArray(test));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	
		frameManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		

		configButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				editTextArea.setText("ExampleServer,ExampleSection,ExampleVariable,ExampleValue");
			}
		});
		
		push.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				(changes).add(editTextArea.getText());
				editTextArea.setText("ExampleServer,ExampleSection,ExampleVariable,ExampleValue");


			}
		});

		frameManager.getContentPane().removeAll();
		frameManager.add(configPan);
		System.out.println("config Pan");
		frameManager.revalidate();
		frameManager.pack();
		frameManager.setVisible(true);
	}

}
