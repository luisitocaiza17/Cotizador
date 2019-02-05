package com.qbe.cotizador.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class ManejoFTP
{
  public static String servidorFTPInsis = "10.57.94.31";
  public static String usuario_ftpInsis = "frontinsis";
  public static String clave_ftpInsis = "everis0814*";
  public static String ruta_ftpInsis = "/home/frontinsis/inbox/";

  public static String servidorFTPQBE = "10.10.21.116";
  public static String usuario_ftpQBE = "insis";
  public static String clave_ftpQBE = "Passw0rd01";

  public static void subirXMLFTP(String xmlString, String numeroCotizacion)
  {      
  
  try {        
    	JSch sftp = new JSch();
        Session session = sftp.getSession(usuario_ftpInsis, servidorFTPInsis, 22);
        session.setPassword(clave_ftpInsis);
        Properties props = new Properties();
        props.put("StrictHostKeyChecking", "no");
        session.setConfig(props);  
		session.connect();
		System.out.println("Accedio a la session");
		// Guardamos la informacion dentro del archivo local
	    File archivoGenerado = new File(numeroCotizacion + "_REQ.xml");
	    FileWriter fw = new FileWriter(archivoGenerado);
	    fw.write(xmlString);
	    fw.close();
	  
	    ChannelSftp channelSftp = (ChannelSftp) session.openChannel("sftp");
	    channelSftp.connect();
	    System.out.println("Se conecto al canal sftp: "+ruta_ftpInsis); 	 	    
	    channelSftp.put(new FileInputStream(archivoGenerado), archivoGenerado.getName());
	    if (session.isConnected())
	      session.disconnect();
	      
	    if (channelSftp.isConnected())
	      channelSftp.disconnect();
	    
	} catch (JSchException | IOException | SftpException e) {
		e.printStackTrace();
	}
  }
  

  
  
  
  
//  public static void subirXMLFTP(String xmlString, String numeroCotizacion)
//  {
//    FTPClient client = new FTPClient();
//
//    try { client.connect("10.57.94.31");
//      System.out.println("paso");
//      boolean login = client.login(usuario_ftpInsis, clave_ftpInsis);
//      if (login)
//      {
//        System.out.println("Iniciando sesión ftp insis");
//        int replay = client.getReplyCode();
//        File archivoGenerado = null;
//
//        if (FTPReply.isPositiveCompletion(replay))
//        {
//          archivoGenerado = new File(numeroCotizacion + "_REQ.xml");
//
//          FileWriter fw = new FileWriter(archivoGenerado);
//          fw.write(xmlString);
//          fw.close();
//
//          FileInputStream input = new FileInputStream(archivoGenerado);
//          client.setFileType(2);
//          client.enterLocalPassiveMode();
//          client.changeWorkingDirectory(ruta_ftpInsis);
//
//          System.out.println("Subió satisfactoriamente el archivo");
//          if (!client.storeFile(archivoGenerado.getName(), input)) {
//            System.out.println("Subida fallida!");
//          }
//          input.close();
//        }
//
//        boolean logout = client.logout();
//
//        archivoGenerado.delete();
//
//        if (logout) {
//          System.out.println("Salio del servidor FTP");
//        } } else { System.out.println("Falló inciar sesión"); }
//    } catch (Exception e)
//    {
//      e.printStackTrace();
//      try
//      {
//        client.disconnect();
//      } catch (IOException ex) {
//        ex.printStackTrace();
//      }
//    }
//    finally
//    {
//      try
//      {
//        client.disconnect();
//      } catch (IOException e) {
//        e.printStackTrace();
//      }
//    }
//  }
}