package Pages;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.DosFileAttributes;
import java.util.jar.Attributes;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;

public class ActivationKeyPage extends JFrame {

	private JPanel contentPane;
	private JTextField txtactkey;
	private static String OS = System.getProperty("os.name").toLowerCase();
	public static String key = "1234";
	public JLabel lblWrong;
	InetAddress ip;

	InputStream inStream = null;
	OutputStream outStream = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ActivationKeyPage frame = new ActivationKeyPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static boolean isWindows() {
		return (OS.indexOf("win") >= 0);
		
	}

	public static boolean isMac() {
		return (OS.indexOf("mac") >= 0);
	}

	public static boolean isUnix() {
		return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0);
	}

	public static boolean isSolaris() {
		return (OS.indexOf("sunos") >= 0);
	}

	public static String getOS() {
		if (isWindows()) {
			return "win";
		} else if (isMac()) {
			return "osx";
		} else if (isUnix()) {
			return "uni";
		} else if (isSolaris()) {
			return "sol";
		} else {
			return "err";
		}
		// String key=
		// if()
	}

	/**
	 * Create the frame.
	 */
	public ActivationKeyPage() {
		setFont(new Font("Arial", Font.PLAIN, 11));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 591, 382);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Activation Key");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblNewLabel.setBounds(198, 103, 174, 34);
		contentPane.add(lblNewLabel);
		
		txtactkey = new JTextField();
		txtactkey.setBounds(160, 147, 259, 39);
		contentPane.add(txtactkey);
		txtactkey.setColumns(10);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 try {
			            String OS1 = null;
			            if (isWindows()) {
			               // System.out.println("This is Windows =" + OS);
			                OS1 = OS;
			            } else if (isMac()) {
			                OS1 = OS;
			            } else if (isUnix()) {
			                OS1 = OS;
			            } else if (isSolaris()) {
			                OS1 = OS;
			            } else {
			                System.out.println("Your OS is not support!!");
			            }

			            String key1 = txtactkey.getText();

			            String hostname = InetAddress.getLocalHost().getHostName();

			            Process p = Runtime.getRuntime().exec("getmac /fo csv /nh");
			            java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(p.getInputStream()));
			            String line;
			            line = in.readLine();
			            String[] result = line.split(",");
			            String macid = result[0].replace('"', ' ').trim();
			            
			        	ip = InetAddress.getLocalHost();
			    		System.out.println("Current IP address : " + ip.getHostAddress());

			    		NetworkInterface network = NetworkInterface.getByInetAddress(ip);

			    		byte[] mac = network.getHardwareAddress();

			    		System.out.print("Current MAC address : ");

			    		StringBuilder sb = new StringBuilder();
			    		for (int i = 0; i < mac.length; i++) {
			    			sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
			    		}
			    		System.out.println(sb.toString());
			            
			           
			            String ip1 = Inet4Address.getLocalHost().getHostAddress();
			            
			            File file =new File("F:\\Key2.txt");
			            if(key.equals(txtactkey.getText()))
			            {
			            	
			            	 if (!file.exists()) {
			            		 file.createNewFile();	
			            		 
			            	 }
			             }
			            else
			            {
			            lblWrong.setText("Plz enter product key.");
			            }
			          
			            FileWriter fw = new FileWriter(file.getAbsoluteFile());
			            
			            BufferedWriter bw = new BufferedWriter(fw);
			            bw.write("Key="+txtactkey.getText());
			            bw.newLine();
			            bw.write("MacAddress="+sb.toString());
			            bw.newLine();
			            
			            bw.append("OsType="+OS);
			            bw.newLine();
			            bw.append("HostName="+hostname);

		            	 Process p1;
							//p1 = Runtime.getRuntime().exec("attrib +h " + file.getPath());
							//p1.waitFor();
							file.setReadOnly();
							file.setExecutable(false);
		            		 file.setReadable(false);
		            		 file.setWritable(false);
						
			            bw.close();
			           JOptionPane.showMessageDialog(null,"Activation Ok");
			           dispose();
			            
			        } catch (IOException e1) {
			            e1.printStackTrace();
			        } 
			}
		});
		btnNewButton.setBounds(201, 200, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {

	public void actionPerformed(ActionEvent e) {
		dispose();
	}
});btnExit.setBounds(299,200,89,23);contentPane.add(btnExit);
 lblWrong = new JLabel("");
lblWrong.setHorizontalAlignment(SwingConstants.CENTER);
lblWrong.setForeground(Color.RED);
lblWrong.setFont(new Font("Tahoma", Font.PLAIN, 24));
lblWrong.setBounds(169, 261, 250, 29);
contentPane.add(lblWrong);}

}
