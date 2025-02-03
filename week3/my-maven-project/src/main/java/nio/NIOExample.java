package nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOExample {

    public static void main(String[] args){

        // Reading from a file
        // /* 
        try {
            FileInputStream fis = new FileInputStream("NioFile.txt");
            FileChannel readChannel = fis.getChannel();
            ByteBuffer readBuffer = ByteBuffer.allocate(1024);
            int bytesRead = readChannel.read(readBuffer);
            // int i = 0;
            while (bytesRead != -1) {
                readBuffer.flip();
                // System.out.println(i++);
                while (readBuffer.hasRemaining()) {
                    System.out.print((char) readBuffer.get());
                }
                readBuffer.clear();
                bytesRead = readChannel.read(readBuffer);
            }
            readChannel.close();
            fis.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
            e.printStackTrace();
        }
        // */

        // Writing to a file
        /*
        try {
            FileOutputStream fos = new FileOutputStream("NioFileWrite.txt");
            FileChannel writeChannel = fos.getChannel();
            ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
            String message = "Hello, this is a message from NIO";
            writeBuffer.put(message.getBytes());
            writeBuffer.flip();
            writeChannel.write(writeBuffer);
            writeChannel.close();
            fos.close();

            System.out.println("File written successfully");
        } catch (Exception e) {
            // TODO: handle exception

            System.out.println("Error: " + e);
            e.printStackTrace();
        }
        */
    }

}
