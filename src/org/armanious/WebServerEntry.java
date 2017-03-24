package org.armanious;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServerEntry extends Thread {

	public static void main(String...args) throws IOException{
		System.setOut(new PrintStream(new File("output-std.txt")));
		System.setErr(new PrintStream(new File("output-err.txt")));
		try(ServerSocket s = new ServerSocket(25025, 50)){
			while(true){
				new WebServerEntry(s.accept()).start();
			}
		}
	}

	private final Socket socket;

	public WebServerEntry(Socket s){
		this.socket = s;
	}

	
	public void run(){
		try{
			final BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			final String update = br.readLine();
			final String data = br.readLine();
			Gene[] genes;
			if(update.equals("relayout")){
				genes = Utilities.jsonToGeneArray(data);
			}else if(update.equals("initial")){
				genes = BioGRID.extractRelatedGenesFromBioGRID(data.split(","));
			}else if(update.equals("singlegene")){
				genes = BioGRID.singleGeneSearch(data);
			}else{
				genes = new Gene[0];
			}
			if(genes.length > 1){
				Utilities.layout(genes);
			}
			
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			try{
				out.write(Utilities.geneArrayToJson(genes));
				out.newLine();
				out.flush();
			}catch(Exception e){
				e.printStackTrace();
			}
			/*DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			final String toOutput = Utilities.geneArrayToJson(genes);
			try{ 
				dos.writeBytes(toOutput);
			}catch(Exception e){
				System.err.println("Failed writing once; trying again...");
				dos.writeBytes(toOutput);
			}
			dos.flush();*/
			
			socket.close();
			//writeOutput(Utilities.geneArrayToJson(genes), true);
		}catch(IOException e){
			e.printStackTrace();
		}
	}


	/*private String readPostInput() throws IOException {
		final InputStream in = socket.getInputStream();
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] data = new byte[4096];
		while(in.available() != 0){
			int read = in.read(data, 0, 4096);
			baos.write(data, 0, read);
		}
		final String[] lines = new String(baos.toByteArray()).split("\n");
		
		System.out.println(lines[0]);
		final String header = lines[0];
		final String[] parts = header.split(" ");
		if(parts[0].equals("POST") && parts[1].equals("/")){
			return lines[lines.length - 1];
			//String output = "<html><head></head><body>";
			//String postData = lines[lines.length - 1];
			//output += "<p>" + postData + "</p>";
			//output += "</body></html>";
			//writeOutput(output);
		}else{
			writeOutput("<html><head></head><body><form action=\"#\" method=\"post\"><input type=\"text\" name=\"update\" /><input type=\"text\" name=\"data\" /><br><input type=\"submit\" value=\"Submit\"></form></body></html>",
					false);
		}
		return null;
	}

	private void writeOutput(String output, boolean isJson) throws IOException {
		final DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

		dos.writeBytes("HTTP/1.1 200 OK" + "\r\n");
		dos.writeBytes("Server: Java HTTPServer" + "\r\n");
		dos.writeBytes("Content-Type: " + (isJson ? "application/json" : "text/html") + "\r\n");
		dos.writeBytes("Access-Control-Allow-Origin: *" + "\r\n");
		dos.writeBytes("Content-Length: " + output.length() + "\r\n");
		dos.writeBytes("Connection: close\r\n");
		dos.writeBytes("\r\n");

		dos.writeBytes(output);

		dos.flush();
	}*/
	
	/*
	public void run_legacy(){
		try {
			String postInput = readPostInput();
			if(postInput != null){
				String[] inputs = postInput.split("&");
				String data = URLDecoder.decode(inputs[1].substring(inputs[1].indexOf('=') + 1), "UTF-8");
				Gene[] genes;
				if(inputs[0].equals("update=true")){
					genes = Utilities.jsonToGeneArray(data);
				}else{
					genes = BioGRID.extractRelatedGenesFromBioGRID(data.split(","));
				}
				Utilities.layout(genes);
				
				writeOutput(Utilities.geneArrayToJson(genes), true);
			}
			
			socket.getOutputStream().close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/

}
