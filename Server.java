import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.io.IOException;
import java.net.DatagramPacket;

public class Server {
    public static void main(String[] args){
        try{
        DatagramSocket server = new DatagramSocket(2000);
        byte [] receiveData = new byte[1024];
        byte [] sendData = new byte[1024];

        while(true){
            try{
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            server.receive(receivePacket);
            String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();
            String capitalizaedMessage = message.toUpperCase();
            sendData = capitalizaedMessage.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
            server.send(sendPacket);
            }
            catch(IOException e){
                System.err.println(e);
            }
        }
        }
        catch(SocketException e){
            System.err.println(e);
        }
    }
}
