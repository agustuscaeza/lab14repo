package exercise2part2.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 * Lab Week 14 Part 2 Exercise 2
 * 
 * @author muhammadizzat
 */

public class UDPClientApp {

public static void main(String[] args) {
		
		System.out.println("\n\tUDP ClientApp: UDP Processing "
				+ "Client-Side Application.");
		
		try {
			
			// 1. Define server port number 4223 and address
			int portNo = 4223;
			InetAddress ip = InetAddress.getLocalHost();
			
			// 2. Prepare and transform data into bytes
			// Receive sentence input from client
			System.out.print("Enter text: ");
			Scanner sc = new Scanner(System.in);
			
			String text = sc.nextLine().trim();
			byte buf[] = text.getBytes();

			// 3. Wrap data in datagram packet (constructor no 5)
			DatagramPacket outPacket = 
					new DatagramPacket(buf, buf.length, ip, portNo);
			
			// 4. Create the socket object to transmit the data.
			DatagramSocket datagramSocket = new DatagramSocket();

			// 5. Send datagram packet
			datagramSocket.send(outPacket);
			System.out.println("\n\tSending '" + text + "' (" + text.length() 
				+ ") out on network.");
			
			// 6.New buffer to extract the received data
			byte[] inData = new byte[65535];
			byte[] inData1 = new byte[65535];
			byte[] inData2 = new byte[65535];
			
			// 7. Packet to receive data
			DatagramPacket inPacket = new DatagramPacket(inData, inData.length);
			DatagramPacket inPacket1 = new DatagramPacket(inData1, inData.length);
			DatagramPacket inPacket2 = new DatagramPacket(inData2, inData.length);
			
			// 8. Receive data
			datagramSocket.receive(inPacket);
			datagramSocket.receive(inPacket1);
			datagramSocket.receive(inPacket2);
			
			// 9. Extract data
			inData = inPacket.getData();
			inData1 = inPacket1.getData();
			inData2 = inPacket2.getData();
			
			// 10. Further processing
			String length = new String(inData, 0, inPacket.getLength());
			String length1 = new String(inData1, 0, inPacket.getLength());
			String length2 = new String(inData2, 0, inPacket.getLength());
			
			// Display the data received from the server
			System.out.println("\tNo of vowels from the server : " + length);
			System.out.println("\tNo of consonants from the server : " + length1);
			System.out.println("\tNo of punctuations from the server : " + length2);
			
			datagramSocket.close();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		System.out.println("\n\t ClientApp: End of program.");

	}
}

	

