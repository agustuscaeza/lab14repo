package exercise2part2.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import exercise2part2.controller.SentenceCounter;

/**
 * Lab Week 14 Part 2 Exercise 2
 * 
 * 
 * 
 * @author muhammadizzat
 * 
 */
public class UDPServerApp {

public static void main(String[] args) {
		
		System.out.println("UDP ServerApp: UDP Processing "
				+ "Server-Side Application.");
		
		
		// Permissible port for this application
		int portNo = 4223;
        
        try {
        	
    		// 1. Bind a DatagramSocket's object to a port number
            DatagramSocket datagramSocket = new DatagramSocket(portNo);
        	
            // Continually listen for request
        	while (true) {
                
                // 2. Variable to received data from the port
        		// 65535 is the maximum size for UDP packet
                byte[] receivedData = new byte[65535];
          
                // 3. Object represents packet from client
                DatagramPacket receivedPacket = 
                		new DatagramPacket(receivedData, receivedData.length);
                
                // 4. Receive packet
				datagramSocket.receive(receivedPacket);
				
				// 5. Extract data from packet
				receivedData = receivedPacket.getData();
				
				// 6. Further processing
				SentenceCounter processor = 
						new SentenceCounter(receivedData);
				String sentence = processor.getSentence();
				System.out.println("\nMessage received: " + sentence + ".\n");
				
				// This is not used because it give a misleading result
	            // int length = sentence.length();
	            
	            // More processing
	            int totalVowel = processor.CountVowel();
	            int totalConso = processor.CountConso();
	            int totalPunct = processor.CountPunct();
	            
	            byte[] outData = processor.convertToByteArray(totalVowel);
	            byte[] outData1 = processor.convertToByteArray(totalConso);
	            byte[] outData2 = processor.convertToByteArray(totalPunct);
	            
	            // 7. Get the client information
	            InetAddress clientAddress =  receivedPacket.getAddress();
	            int clientPort = receivedPacket.getPort();
	            int sizeOutData = outData.length;
	            int sizeOutData1 = outData1.length;
	            int sizeOutData2 = outData2.length;
	            
	            // 8. Wrap data into datagram packet
	            DatagramPacket outPacket = new DatagramPacket(outData, 
	            		sizeOutData, clientAddress, clientPort);

	            DatagramPacket outPacket1 = new DatagramPacket(outData1, 
	            		sizeOutData1, clientAddress, clientPort);

	            DatagramPacket outPacket2 = new DatagramPacket(outData2, 
	            		sizeOutData2, clientAddress, clientPort);
	            
	            // 9. Reply to client
	            datagramSocket.send(outPacket);
	            System.out.println("Message sent (total Vowel) : " 
	            		+ totalVowel  + ".\n");
	            
	            datagramSocket.send(outPacket1);
	            System.out.println("Message sent (total Consonant) : " 
	            		+ totalConso  + ".\n");
	            
	            datagramSocket.send(outPacket2);
	            System.out.println("Message sent (total Punctuation) : " 
	            		+ totalPunct  + ".\n");
	            
        	}
				
		} catch (IOException e) {
				
			e.printStackTrace();
        }
        
        System.out.println("UDP ServerApp: End of program.");
    }
}
