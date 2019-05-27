package ActiveKey;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import Pages.*;

public class ActiveKey {

	public static void main(String[] args) {
		try {
			InputStream inStream = null;
			OutputStream outStream = null;
			int d, check = 0;
			String[] macArray;
			BufferedReader br = null;
			File file = new File("F:\\Key2.txt");
			file.setExecutable(true);
		      file.setReadable(true);
		      file.setWritable(true);
			String sCurrentLine;
			String macaddress = null;
			if (!file.exists()) {
				ActivationKeyPage aw = new ActivationKeyPage();
				aw.setVisible(true);
			}
			
			else{
			
			br = new BufferedReader(new FileReader(file));

				while ((sCurrentLine = br.readLine()) != null) {
					if (sCurrentLine.contains("MacAddress")) {
						macaddress = sCurrentLine.substring(11, 28);
						System.out.println(macaddress);
					}
				}
				macArray = new String[1000];
				
				macArray[0] = macaddress;
				// MAC CHECKING
				InetAddress ip;
				ip = InetAddress.getLocalHost();
					// System.out.println("Current IP address : " +
					// ip.getHostAddress());

					NetworkInterface network = NetworkInterface.getByInetAddress(ip);
					

					byte[] mac = network.getHardwareAddress();
					
					
					
					 System.out.print("Current MAC address:"+mac);
					StringBuilder sb = new StringBuilder();
					for (int i = 0; i < mac.length; i++) {
						sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
					}
					 System.out.println(sb.toString());
					String a = (sb.toString());
					// String b="74-E5-43-23-F1-B4";

					for (d = 0; d < macArray.length; d++) {
						if (a.equals(macArray[d])) {
							check = 1;
							break;
						}
					}
					
					if (check == 1) {
						if (!file.exists()) {
							ActivationKeyPage aw = new ActivationKeyPage();
							aw.setVisible(true);
						}
						else
						{
							LoginPage lg=new LoginPage();
							lg.setVisible(true);
							
						}
					}
					if (check != 1) {
						JOptionPane.showMessageDialog(null, "Registration Required Plz Contact Hiray Softtech Pvt Ltd.");
						
					} 
				}
					
		} catch (Exception re) {
			System.out.println(re.getMessage());
		}
	}
}
