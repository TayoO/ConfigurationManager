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
import java.util.Collections;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.text.JTextComponent;



class ServerManager {
	static String path = "c:/temp/opentext.ini";
	static final int ARBITRARY_LIMIT = 10;
	String powershell = "powershell C:\\Users\\oduekea\\workspace\\shelltest.ps1";
	static String[] groupNames;
	Server[] serverList;
	static String[] serverNames;
	boolean[][] associations;
	GridBagConstraints globalConstraints = new GridBagConstraints();
//Outside of droplist applets all graphics go on this one frame.
	final BasicFrame frameManager = new BasicFrame();
	final JPanel introPan = new JPanel(new GridBagLayout());
	//static Scanner in = new Scanner(System.in);

	public static void main(String[] args) throws IOException, SQLException, InterruptedException {
		final ServerManager main = new ServerManager();
		
		main.openFrame();
}
	/*
	 * file code: numGroups numServers listOfGroupNames seperated by commas
	 * ListOfServerNames seperated by commas numGroups lines each with list of
	 * servers indexes corresponding to respective groups seperated by commas
	 * Example] 5 6 frontEnd, BackEnd, PWGSC, SSC, RCMP PWFE, PWBE, SSCFE,
	 * SSCBE, RCMPFE, RCMPBE 1,3,5 2,4,6 1,2h 5,6
	 */
	public void openFrame(){
		
		//Constraints
		globalConstraints.gridx = 100;
		globalConstraints.insets = new Insets(40, 40, 40, 40);
		globalConstraints.gridy = 40;
		
		
		
		final JTextArea title = new JTextArea("Content Server Configuration Manager");
		
		JButton config = new JButton("Push configurations");
		JButton serv  = new JButton("Manage ServerList");
		globalConstraints.gridx = 40;
		introPan.add(serv, globalConstraints);
		globalConstraints.gridx = 100;
        introPan.add(config, globalConstraints);

		//globalConstraints.gridy = 70;
		//final JTextArea powerShellInfoText = new JTextArea("powerscript path listed as" + this.powershell);JButton serv = new JButton("Manage ServerList");
		//introPan.add(powerShellInfoText, globalConstraints);
		globalConstraints.gridy = 20;
		globalConstraints.gridx = 40;
		introPan.add(title, globalConstraints);
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
				
				configurations();
			}
		});
		serv.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frameManager.getContentPane().removeAll();
				serverListManagement();
			}
		});
		System.out.println("hey");
		frameManager.getContentPane().removeAll();
		frameManager.add(introPan);
		System.out.println("intro pan");
		frameManager.revalidate();
		frameManager.pack();
		frameManager.setVisible(true);
	
	}
	public String[] SQLGroups(String[] departs, String[] type) {
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
					System.out.println("pre update");
					stmt.executeUpdate(sql);
					System.out.println("post update");

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
						System.out.println("pre type");
						System.out.println(rs.getString("type"));
						System.out.println("after type");
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

	public void addServerSQL(String id, String number, String type, String dep, String cif, String ip) throws ClassNotFoundException, SQLException{

	    
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
	}
public void modifyServerSQL(int id, String columnName, String change) {

	    
	    Connection c = null;
		Statement stmt = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
		    String sql="UPDATE SERVER SET "+columnName+" ='"+change+"' WHERE ID ="+id+" );"; 
			stmt = c.createStatement();
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
public void deleteServerSQL(int id){
	  Connection c = null;
			Statement stmt = null;
			
			try {
				Class.forName("org.sqlite.JDBC");
				c = DriverManager.getConnection("jdbc:sqlite:test.db");
			    String sql="DELETE FROM SERVER WHERE ID = "+id+";"; 
				stmt = c.createStatement();
				stmt.executeUpdate(sql);
			} catch (Exception e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
				System.exit(0);
			}
	
}
	public String [] getGroups(){
		{
			System.out.println("get groups");
			String [] departs;
			String [] types;
		String [] departmentList={"VAC","SSC","RCMP","PWGSC"};
		String [] typeList={"AD","AG","FE","ID"};
		DropDownListThread chooseDep=new DropDownListThread(departmentList);
		
		synchronized(chooseDep){
            try{
            	
                System.out.println("Waiting for departments to be choosen...");
                chooseDep.run( 200, 125);
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
		// Copy pasted code from previous lines.
		DropDownListThread chooseType=new DropDownListThread(typeList);
		 
		
		synchronized(chooseType){
            try{
            	
                System.out.println("Waiting for types to be choosen...");
                chooseType.run( 200, 125);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            types=chooseType.results;
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
		}
		// main.loadDefault();


		
		//main.ServerManagerFrame();
		//main.configFrame();
		System.out.println("powerscript path listed as" + this.powershell);
		
		



		}
		return null;
		
	}
// Choosing which server to push configuration to	
	public String [] chooseServer(){
		 final JPanel choosePan = new JPanel(new GridBagLayout());
		final ArrayList<String> chosenServers = new ArrayList<String>();
		

		JButton toGroups = new JButton("Push to Groups");
		globalConstraints.gridx=40;
		globalConstraints.gridy=40;
		JButton toServ   = new JButton("Push to individual Servers");
		toGroups.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Collections.addAll(chosenServers,getGroups());
			}
		});
		toServ.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Collections.addAll(chosenServers,getServs());
			}
		});
		choosePan.add(toGroups,globalConstraints);
		globalConstraints.gridx=80;
		choosePan.add(toServ,globalConstraints);
			
		

		
		String [] temp = {};
		return chosenServers.toArray(temp);
	}
	// Combines the server info with changes than passes it on to push line by
	// line
	
	protected String [] getServs() {
		final String[] serv=new String [1];
		final JPanel servPan = new JPanel(new GridBagLayout());
		final JTextArea editTextArea = new JTextArea("Enter servername here");

		JButton servButton = new JButton("Done");
		globalConstraints.gridx = 100;
		globalConstraints.gridy = 100;
		globalConstraints.insets = new Insets(40, 40, 40, 40);
		servPan.add(servButton, globalConstraints);

		globalConstraints.gridy = 40;
		servPan.add(editTextArea, globalConstraints);
		frameManager.getContentPane().removeAll();
frameManager.add(servPan);
System.out.println("serv pan");
frameManager.revalidate();
frameManager.pack();
frameManager.setVisible(true);
		servButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				serv [0]= editTextArea.getText();
				editTextArea.setText(" ");
				
			}
		});
		return null;
	}
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
	public void pushConfiguration(String line, PrintWriter out) {
		out.println(line + "\r\n");

	}
 public String []  autoPanel(final JPanel nextPan, String ... sects){
	 final boolean [] finished= new boolean [1];
	 finished[0]=false;
	 String [] output=new String [sects.length];
	 final JPanel autoPan= new JPanel(new GridBagLayout());
	 FlowLayout experimentLayout = new FlowLayout();
		autoPan.setLayout(experimentLayout);
		final JComponent [] comp= new JComponent[sects.length];
		for (int i=0; i<sects.length; i++){
	
			if (i%2==1){
				// Only used since final variables must be used for action performed.
				final int x=i;
				comp[i] = new JTextField(sects[i].substring(1,sects.length));
			( (JTextField) comp[i]).addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
                            ((JTextComponent) comp[x]).getText();
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
 
	public void serverListManagement(){
		System.out.println("heh");
		final JPanel listPan = new JPanel(new GridBagLayout());
		FlowLayout experimentLayout = new FlowLayout();
		listPan.setLayout(experimentLayout);
		JButton addingGroups = new JButton("Add Groups");
		JButton modifyGroups = new JButton("Modify Groups");
		JButton deleteGroups = new JButton("Delete Groups");
		globalConstraints.gridx = 100;
		globalConstraints.gridy = 100;
		globalConstraints.insets = new Insets(40, 40, 40, 40);
		JButton addingServers = new JButton("Add Servers");
		JButton modifyServers = new JButton("Modify Servers");
		JButton deleteServers = new JButton("Delete Servers");
listPan.add(addingGroups, globalConstraints);
listPan.add(modifyGroups, globalConstraints);
listPan.add(deleteGroups, globalConstraints);
listPan.add(addingServers, globalConstraints);
listPan.add(modifyServers, globalConstraints);
listPan.add(deleteServers, globalConstraints);
frameManager.getContentPane().removeAll();
		System.out.println("list pan");
		frameManager.getContentPane().add(listPan, BorderLayout.WEST);
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
		String [] results=autoPanel(introPan, "lid:","tEnter id here, must be a number","lnumber","tEnter the server number here (the e.g for GCDOCS-45393 type 45393)","ltype","tEnter type here 2 characters", "ldep:", "tEnter the department acronym here eg SSC",
			
				"lcif","tEnter cif here must be a number eg 1","lip:","tEnter ip here 1430-3212-2342");
		System.out.println(results[0]+results[1]+results[2]+results[3]+results[4]+results[5]+results[6]+results[7]);	
System.out.println("adding Serv");


	}
})	;		



modifyServers.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent e){
		
	}
})	;		



deleteServers.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent e){
		
	}
});		


	}
	
	
	
	
    public void configurations() {
	
			
		final ArrayList<String> changes = new ArrayList<String>();
		
		// final int counter=0;
		


		

		final JPanel configPan = new JPanel(new GridBagLayout());
		final JTextArea editTextArea = new JTextArea("ExampleServer,ExampleSection,ExampleVariable,ExampleValue");

		JButton configButton = new JButton("New Configuration");
		
		globalConstraints.gridx = 100;
		globalConstraints.gridy = 100;
		globalConstraints.insets = new Insets(40, 40, 40, 40);
		configPan.add(configButton, globalConstraints);

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
		JButton back= new JButton("Back");
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				openFrame();
			}
		});
		globalConstraints.gridx = 60;
		globalConstraints.gridy = 100;
		configPan.add(back, globalConstraints);
		JButton push = new JButton("PushConfiguration");
		globalConstraints.gridx = 60;
		globalConstraints.gridy = 60;
		globalConstraints.insets = new Insets(40, 40, 40, 40);
		configPan.add(push, globalConstraints);
		JButton Done = new JButton("DonePushing");

		globalConstraints.gridy = 80;
		configPan.add(Done, globalConstraints);
		Done.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] test = new String[0];
				try {
					System.out.println("pushing changes"+changes.toArray(test)[0]);
					pushConfiguration(chooseServer(), changes.toArray(test));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	

		//2. Optional: What happens when the frame closes?
		frameManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//3. Create components and put them in the frame.
		//...create emptyLabel...
		frameManager.getContentPane().removeAll();
		//frameManager.getContentPane().add( BorderLayout.CENTER, Done);

		
		

		configButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				editTextArea.setText("ExampleServer,ExampleSection,ExampleVariable,ExampleValue");
			}
		});
		
		frameManager.add(configPan);
		push.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				(changes).add(editTextArea.getText());
				editTextArea.setText("ExampleServer,ExampleSection,ExampleVariable,ExampleValue");


			}
		});

		frameManager.revalidate();
	}

	


}
