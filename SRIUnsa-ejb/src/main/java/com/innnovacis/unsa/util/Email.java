/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.util;

import com.innnovacis.unsa.model.SRIActividadInvestigacion;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.inject.Inject;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

/**
 *
 * @author Gen-Tya-TIConsul1
 */
public class Email {

        
    @Inject
    private Logger log;
    
    private static String RECIPIENT = "ali.arapa@gmail.com";
    public String info;

    public String GetEmailRegistro(String titulo, String nombreDestinatario, String cuerpo, 
            String nombreRemitente, SRIActividadInvestigacion actividadInvestigacion) {
        
        Calendar calendar = new GregorianCalendar();
        String anio = Integer.toString(calendar.get(Calendar.YEAR));
        String urlActividad = "http://localhost:8080/SRIUnsa-web/#/actividad/Generadas/" + actividadInvestigacion.getNIdActividadInvestigacion();
        String plantillaTabla = GetPlantillaTabla(actividadInvestigacion);
        String plantilla = "<html>"
+"<head>"
+"</head>"
+"<body style='margin: 0; padding: 0;'>"
+"  <table border='0' cellpadding='0' cellspacing='0' width='100%'>	"
+"      <tr>"
+"          <td style='padding: 10px 0 30px 0;'>"
+"              <table align='center' border='0' cellpadding='0' cellspacing='0' width='600' style='border: 1px solid #cccccc; border-collapse: collapse;'>"
+"                  <tr>"
+"			<td align='center' bgcolor='#fff' style='padding: 20px 0 20px 0; color: #153643; font-size: 28px; font-weight: bold; font-family: Arial, sans-serif;'>"
+"                          <img src=\"cid:LogoUnsa\" alt='Logo UNSA' width='400' height='160' style='display: block;' />"
+"			</td>"
+"                  </tr>"
+"                  <tr>"
+"			<td bgcolor='#ffffff' style='padding: 40px 30px 40px 30px;'>"
+"                          <table border='0' cellpadding='0' cellspacing='0' width='100%'>"
+"				<tr>"
+"                                  <td align='center' 	style='color: #153643; font-family: Arial, sans-serif; font-size: 24px;'>"
+"					<b>" + titulo + "</b>"
+"                                  </td>"
+"				</tr>"
+"				<tr>"
+"                                  <td style='padding: 20px 0 30px 0; color: #153643; font-family: Arial, sans-serif; font-size: 16px; line-height: 20px;'>"
+"					Estimado " + nombreRemitente + ":"
+"                                  </td>"              
+"				</tr>"
+"				<tr>"
+"                                  <td style='padding: 20px 0 30px 0; color: #153643; font-family: Arial, sans-serif; font-size: 16px; line-height: 20px;'>"
+                                       cuerpo + " " + nombreDestinatario + "."
+"                                  </td>"              
+"                              </tr>"
+                               plantillaTabla
+"                          </table>"
+"                      </td>"
+"                  </tr>"
+"                  <tr>"
+"			<td bgcolor='#951f1f' style='padding: 30px 30px 30px 30px;'>"
+"                          <table border='0' cellpadding='0' cellspacing='0' width='100%'>"
+"				<tr>"
+"                                  <td style='color: #ffffff; font-family: Arial, sans-serif; font-size: 14px;' width='75%'>"
+"					Para acceder al registro de la Actividad de Ivestigación. <a href='" + urlActividad + "' style='color: #ffffff;'><font color='#ffffff'>Click aqui</font></a> <br/>"
+"					&reg;Innnovacis,  SIRI-UNSA " + anio
+"                                  </td>"
+"                              </tr>"
+"                          </table>"
+"			</td>"
+"                  </tr>"
+"		</table>"
+"          </td>"
+"	</tr>"
+"  </table>"
+"</body>"
+"</html>";
        
        return plantilla;
    }

    public String GetBodyEmail(String nombreCompleto, String codigo) {
        String mensaje = "<html>"
+"<head>"
+"</head>"
+"<body style='margin: 0; padding: 0;'>"
+"	<table border='0' cellpadding='0' cellspacing='0' width='100%'>	"
+"		<tr>"
+"			<td style='padding: 10px 0 30px 0;'>"
+"				<table align='center' border='0' cellpadding='0' cellspacing='0' width='600' style='border: 1px solid #cccccc; border-collapse: collapse;'>"
+"					<tr>"
+"						<td align='center' bgcolor='#fff' style='padding: 20px 0 20px 0; color: #153643; font-size: 28px; font-weight: bold; font-family: Arial, sans-serif;'>"
+"							<img src=\"cid:LogoUnsa\" alt='Creating Email Magic' width='400' height='160' style='display: block;' />"
+"						</td>"
+"					</tr>"
+"					<tr>"
+"						<td bgcolor='#ffffff' style='padding: 40px 30px 40px 30px;'>"
+"							<table border='0' cellpadding='0' cellspacing='0' width='100%'>"
+"								<tr>"
+"									<td align='center' 	style='color: #153643; font-family: Arial, sans-serif; font-size: 24px;'>"
+"										<b>SRI - Confirmacion de Operacion!</b>"
+"									</td>"
+"								</tr>"
+"								<tr>"
+"									<td style='padding: 20px 0 30px 0; color: #153643; font-family: Arial, sans-serif; font-size: 16px; line-height: 20px;'>"
+"										Estimado " + nombreCompleto + ":"
+"									</td>"              
+"								</tr>"
+"								<tr>"
+"									<td style='padding: 20px 0 30px 0; color: #153643; font-family: Arial, sans-serif; font-size: 16px; line-height: 20px;'>"
+"										Alguien solicit&oacute; recientemente el cambio de su contrase&ntilde;a."
+"									</td>"              
+"								</tr>"
+"								<tr>"
+"									<td style='padding: 20px 0 30px 0; color: #153643; font-family: Arial, sans-serif; font-size: 16px; line-height: 20px;'>"
+"										Ingrese este c&oacute;digo para cambiar la contraseña."
+"									</td>"              
+"								</tr>"
+"                                                              <tr>"
+"                                                                      <td style='font-size:11px;font-family:LucidaGrande,tahoma,verdana,arial,sans-serif;padding:10px;background-color:#f2f2f2;border-left:1px solid #ccc;border-right:1px solid #ccc;border-top:1px solid #ccc;border-bottom:1px solid #ccc'>"
+"                                                                          <span style='font-family:Helvetica Neue,Helvetica,Lucida Grande,tahoma,verdana,arial,sans-serif;font-size:16px;line-height:21px;color:#141823'>"
+                                                                               codigo
+"                                                                          </span>"
+"                                                                      </td>"
+"                                                              </tr>"
+"							</table>"
+"						</td>"
+"					</tr>"
+"					<tr>"
+"						<td bgcolor='#951f1f' style='padding: 30px 30px 30px 30px;'>"
+"							<table border='0' cellpadding='0' cellspacing='0' width='100%'>"
+"								<tr>"
+"									<td style='color: #ffffff; font-family: Arial, sans-serif; font-size: 14px;' width='75%'>"
+"										Para acceder al Registro de su Actividad de Investigacion <a href='http://www.google.com.pe' style='color: #ffffff;'><font color='#ffffff'>Click aqui</font></a> <br/>"
+"										&reg;Innnovacis,  SRIUnsa 2016"
+"									</td>"
+"								</tr>"
+"							</table>"
+"						</td>"
+"					</tr>"
+"				</table>"
+"			</td>"
+"		</tr>"
+"	</table>"
+"</body>"
+"</html>";
            
        return mensaje;
    }
    
    public String GetEstructuraEmail(String titulo, String nombreDestino, String cuerpo, String nombreRemitente) {
        Calendar calendar = new GregorianCalendar();
        String anio = Integer.toString(calendar.get(Calendar.YEAR));
        String mensaje = "<html>"
+"<head>"
+"</head>"
+"<body style='margin: 0; padding: 0;'>"
+"  <table border='0' cellpadding='0' cellspacing='0' width='100%'>	"
+"      <tr>"
+"          <td style='padding: 10px 0 30px 0;'>"
+"              <table align='center' border='0' cellpadding='0' cellspacing='0' width='600' style='border: 1px solid #cccccc; border-collapse: collapse;'>"
+"                  <tr>"
+"			<td align='center' bgcolor='#fff' style='padding: 20px 0 20px 0; color: #153643; font-size: 28px; font-weight: bold; font-family: Arial, sans-serif;'>"
+"                          <img src=\"cid:LogoUnsa\" alt='Logo UNSA' width='400' height='160' style='display: block;' />"
+"			</td>"
+"                  </tr>"
+"                  <tr>"
+"			<td bgcolor='#ffffff' style='padding: 40px 30px 40px 30px;'>"
+"                          <table border='0' cellpadding='0' cellspacing='0' width='100%'>"
+"				<tr>"
+"                                  <td align='center' 	style='color: #153643; font-family: Arial, sans-serif; font-size: 24px;'>"
+"					<b>" + titulo + "</b>"
+"                                  </td>"
+"				</tr>"
+"				<tr>"
+"                                  <td style='padding: 20px 0 30px 0; color: #153643; font-family: Arial, sans-serif; font-size: 16px; line-height: 20px;'>"
+"					Estimado " + nombreDestino + ":"
+"                                  </td>"              
+"				</tr>"
+"				<tr>"
+"                                  <td style='padding: 20px 0 30px 0; color: #153643; font-family: Arial, sans-serif; font-size: 16px; line-height: 20px;'>"
+                                       cuerpo
+"                                  </td>"              
+"                              </tr>"
+"				<tr>"
+"                                  <td style='padding: 20px 0 30px 0; color: #153643; font-family: Arial, sans-serif; font-size: 16px; line-height: 20px;'>"
+"                                      Atentamente. <br>"
+                                       nombreRemitente                
+"                                  </td>"              
+"				</tr>"
+"                          </table>"
+"                      </td>"
+"                  </tr>"
+"                  <tr>"
+"			<td bgcolor='#951f1f' style='padding: 30px 30px 30px 30px;'>"
+"                          <table border='0' cellpadding='0' cellspacing='0' width='100%'>"
+"				<tr>"
+"                                  <td style='color: #ffffff; font-family: Arial, sans-serif; font-size: 14px;' width='75%'>"
+"					Sistema de Registro de Investigación Docente. <a href='http://104.131.8.31:8080/SRIUnsa-web' style='color: #ffffff;'><font color='#ffffff'>Click aqui</font></a> <br/>"
+"					&reg;Innnovacis,  SIRI-UNSA " + anio
+"                                  </td>"
+"                              </tr>"
+"                          </table>"
+"			</td>"
+"                  </tr>"
+"		</table>"
+"          </td>"
+"	</tr>"
+"  </table>"
+"</body>"
+"</html>";
            
        return mensaje;
    }
    
    public String readProperties(String value) throws IOException {
        String respuesta = "";
        URL url = this.getClass().getProtectionDomain().getCodeSource().getLocation();
        String path = url.getPath();
        String nombrearchivo = "config.properties";
        
        Properties prop = new Properties();

        File temp = new File(path, nombrearchivo);
        try {
            FileInputStream in = new FileInputStream(temp);
            prop.load(in);
            respuesta = prop.getProperty(value);
        } catch (Exception ex) {
        }
        return respuesta;
    }
    
    public void enviarRegistro(List<String> nombreDestinatarios, String nombreRemitente, String cuerpo, List<String> emailDestinatarios, 
            String emailRemitente, String titulo, String asunto, byte[] adjunto, SRIActividadInvestigacion actividadInvestigacion) throws GeneralSecurityException, IOException{
        Email email = new Email();
        String from = email.readProperties("user");
        String pass = MD5.decrypt(email.readProperties("password"));
        String host = email.readProperties("host");
        String port = email.readProperties("port");
        String auth = email.readProperties("auth");
        String enable = email.readProperties("enable");
        
       
        List<String> to = new ArrayList<String>();
        to.add(emailRemitente);
        String subject = asunto;
        String body = GetEmailRegistro(titulo, nombreDestinatarios.get(0), cuerpo, nombreRemitente, actividadInvestigacion);
        sendFromGMailInforme("true", "true", "465", "smtp.gmail.com", "innnovacisaqp", "innnovacis.", 
                            to, subject, emailDestinatarios.get(0), body, adjunto);
        
        this.info = from + " : " + pass + " : " + host + " : " + pass + " : " + port + " : " + auth + " : " + enable;
    }
    
    public void enviarInforme(List<String> nombreDestinatarios, String nombreRemitente,
                              String cuerpo, List<String> emailDestinatarios, String emailRemitente, String titulo, String asunto, byte[] adjunto) throws GeneralSecurityException, IOException{
        
        for(int i = 0; i < emailDestinatarios.size(); i++){
            List<String> to = new ArrayList<String>();
            to.add(emailDestinatarios.get(i));
            String subject = asunto;
            String body = GetEstructuraEmail(titulo, nombreDestinatarios.get(i), cuerpo, nombreRemitente);
            sendFromGMailInforme("true", "true", "465", "smtp.gmail.com", "innnovacisaqp", "innnovacis.", to, subject, emailRemitente, body, adjunto);
        }
        
        if(emailDestinatarios.isEmpty()){
            List<String> to = new ArrayList<String>();
            String subject = asunto;
            String body = GetEstructuraEmail(titulo, "", cuerpo, nombreRemitente);
            sendFromGMailInforme("true", "true", "465", "smtp.gmail.com", "innnovacisaqp", "innnovacis.", to, subject, emailRemitente, body, adjunto);
        }
    }
    
    public void enviarCodigoEmail(String codigo, List<String> destinatarios) throws GeneralSecurityException, IOException{
        Email email = new Email();
        
        for(String destinatario : destinatarios){
            List<String> to = new ArrayList<String>();
            to.add(destinatario);
            String subject = "SRI-UNSA - SISTEMA DE REGISTRO DE ACTIVIDADES DE INVESTIGACION";
            String nombreCompleto = "";
            String body = GetBodyEmail(nombreCompleto, codigo);
            sendFromGMail("true", "true", "465", "smtp.gmail.com", "innnovacisaqp", "innnovacis.", to, subject, body);
        }
    }
    
    public String recuperar() throws IOException, GeneralSecurityException{
        Email email = new Email();
        String fileName = System.getProperty("jboss.server.config.dir");
        URL url = this.getClass().getProtectionDomain().getCodeSource().getLocation();
        String path = url.getPath();
        String from = email.readProperties("user");
        String pass = MD5.decrypt(email.readProperties("password"));
        String host = email.readProperties("host");
        String port = email.readProperties("port");
        String auth = email.readProperties("auth");
        String enable = email.readProperties("enable");
        this.info = "PATH : " + path + " DIR_Wildfly: " + fileName + " : " + from + " : " + pass + " : " + host + " : " + pass + " : " + port + " : " + auth + " : " + enable;
       return this.info;
    }

    public void sendFromGMail(String enable, String auth, String port, String host, String from, String pass, 
                              List<String> to, String subject, String body) {
        Properties props = System.getProperties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.user", "innnovacisaqp");
        props.put("mail.smtp.password", "innnovacis.");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

//        Session session = Session.getDefaultInstance(props);
        Session session = Session.getInstance(props, new GMailAuthenticator(from, pass));
        MimeMessage message = new MimeMessage(session);

        MimeMultipart multipart = new MimeMultipart("related");

        BodyPart messageBodyPart = new MimeBodyPart();
        String htmlText = body;
        try {
            messageBodyPart.setContent(htmlText, "text/html; charset=utf-8");
            multipart.addBodyPart(messageBodyPart);
            messageBodyPart = new MimeBodyPart();
            DataSource fds = new FileDataSource("/home/logo/logo-unsa-2.jpg");
            messageBodyPart.setDataHandler(new DataHandler(fds));
            messageBodyPart.setHeader("Content-ID", "<LogoUnsa>");
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);
            message.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[to.size()];

            // To get the array of addresses
            for (int i = 0; i < to.size(); i++) {
                toAddress[i] = new InternetAddress(to.get(i));
            }

            for (int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }

            message.setSubject(subject);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

        } catch (MessagingException ex) {
            Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void sendFromGMailInforme(String enable, String auth, String port, String host, String from, String pass, 
                                    List<String> to, String subject, String emailRemitente, String body, byte[] informe) {
        Properties props = System.getProperties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.user", "innnovacisaqp");//innnovacisaqp");
        props.put("mail.smtp.password", "innnovacis.");//innnovacis.");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(props, new GMailAuthenticator(from, pass));
        MimeMessage message = new MimeMessage(session);

        MimeMultipart multipart = new MimeMultipart("related");
        BodyPart messageBodyPart = new MimeBodyPart();
        String htmlText = body;
        try {
            messageBodyPart.setContent(htmlText, "text/html; charset=utf-8");
            multipart.addBodyPart(messageBodyPart);
            messageBodyPart = new MimeBodyPart();
            
            //ADJUNTAR IMAGE
            DataSource fds = new FileDataSource("/home/logo/logo-unsa-2.jpg");
            messageBodyPart.setDataHandler(new DataHandler(fds));
            messageBodyPart.setHeader("Content-ID", "<LogoUnsa>");
            multipart.addBodyPart(messageBodyPart);
            
            //ADJUNTAR INFORME BYTE

            DataSource dataSource = new ByteArrayDataSource(informe, "application/pdf");
            MimeBodyPart pdfBodyPart = new MimeBodyPart();
            pdfBodyPart.setDataHandler(new DataHandler(dataSource));
            pdfBodyPart.setFileName("Actividades Generadas.pdf");

            multipart.addBodyPart(pdfBodyPart);

            
            message.setContent(multipart);
            message.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[to.size()];
            
            for (int i = 0; i < to.size(); i++) {
                toAddress[i] = new InternetAddress(to.get(i));
            }

            for (int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }
            

            message.addRecipient(Message.RecipientType.CC, new InternetAddress(emailRemitente));
            message.addRecipient(Message.RecipientType.BCC, new InternetAddress("ali.arapa@gmail.com"));
//            message.addRecipient(Message.RecipientType.BCC, new InternetAddress("wilfredoqi@gmail.com"));

            message.setSubject(subject);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

        } catch (MessagingException ex) {
            Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public String GetPlantillaTabla (SRIActividadInvestigacion actividadInvestigacion){
        String respuesta = "";
        
        switch(actividadInvestigacion.getNIdTipoActividadInvestigacion()){
            case 1 : 
                respuesta = "<tr>"
                            +"    <td style='font-size: 0.9em;'>"
                            +"        <table border='1' cellpadding='3' cellspacing='0' width='100%'>"
                            +"            <tr >"
                            +"                <th align='right' width='40%'>Departamento : </th>"
                            +"                <td>" + actividadInvestigacion.getSDepartamento() + "</td>"
                            +"            </tr>"
                            +"            <tr >"
                            +"                <th align='right' width='40%'>Tipo de Actividad : </th>"
                            +"                <td> INVESTIGACIÓN FORMATIVA </td>"
                            +"            </tr>"
                            +"            <tr >"
                            +"                <th align='right' width='40%'>Nombre del curso o Asignatura : </th>"
                            +"                <td>" + actividadInvestigacion.getSNombreActividadInvestigacion() + "</td>"
                            +"            </tr>"
                            +"            <tr >"
                            +"                <th align='right' width='40%'>Descripción de la Actividad Formativa : </th>"
                            +"                <td>" + actividadInvestigacion.getNIdTipoActividadInvestigacion() + "</td>"
                            +"            </tr>"
                            +"        </table>"
                            +"    </td>"
                            +"</tr>";
                break;
            case 11 :
                respuesta = "<tr>"
                            +"    <td style='font-size: 0.9em;'>"
                            +"        <table border='1' cellpadding='3' cellspacing='0' width='100%'>"
                            +"            <tr >"
                            +"                <th align='right' width='40%'>Departamento : </th>"
                            +"                <td>" + actividadInvestigacion.getSDepartamento() + "</td>"
                            +"            </tr>"
                            +"            <tr >"
                            +"                <th align='right' width='40%'>Tipo de Actividad : </th>"
                            +"                <td> ASESORÍA DE TESIS </td>"
                            +"            </tr>"
                            +"            <tr >"
                            +"                <th align='right' width='40%'>Título de Tesis : </th>"
                            +"                <td>" + actividadInvestigacion.getSNombreActividadInvestigacion() + "</td>"
                            +"            </tr>"
                            +"            <tr >"
                            +"                <th align='right' width='40%'>Grado: </th>"
                            +"                <td>" + actividadInvestigacion.getSTipoAsesoria() + "</td>"
                            +"            </tr>"
                            +"            <tr >"
                            +"                <th align='right' width='40%'>Nombre(s) del Asesorado(s): </th>"
                            +"                <td>" + actividadInvestigacion.getSNombreAsesorado() + "</td>"
                            +"            </tr>"
                            +"            <tr >"
                            +"                <th align='right' width='40%'>N° de Resolución: </th>"
                            +"                <td>" + actividadInvestigacion.getSNumeroContrato() + "</td>"
                            +"            </tr>"
                            +"        </table>"
                            +"    </td>"
                            +"</tr>";
                break;
            case 12 :
                respuesta = "<tr>"
                            +"    <td style='font-size: 0.9em;'>"
                            +"        <table border='1' cellpadding='3' cellspacing='0' width='100%'>"
                            +"            <tr >"
                            +"                <th align='right' width='40%'>Departamento : </th>"
                            +"                <td>" + actividadInvestigacion.getSDepartamento() + "</td>"
                            +"            </tr>"
                            +"            <tr >"
                            +"                <th align='right' width='40%'>Tipo de Actividad : </th>"
                            +"                <td> INVESTIGACIONES BÁSICAS Y APLICADAS </td>"
                            +"            </tr>"
                            +"            <tr >"
                            +"                <th align='right' width='40%'>Área : </th>"
                            +"                <td>" + actividadInvestigacion.getSAreaInvestigacion() + "</td>"
                            +"            </tr>"
                            +"            <tr >"
                            +"                <th align='right' width='40%'>Sub área : </th>"
                            +"                <td>" + actividadInvestigacion.getSSubAreaInvestigacion() + "</td>"
                            +"            </tr>"
                            +"            <tr >"
                            +"                <th align='right' width='40%'>Disciplina : </th>"
                            +"                <td>" + actividadInvestigacion.getSDisciplina() + "</td>"
                            +"            </tr>"
                            +"            <tr >"
                            +"                <th align='right' width='40%'>Título del Proyecto de Investigación : </th>"
                            +"                <td>" + actividadInvestigacion.getSNombreActividadInvestigacion() + "</td>"
                            +"            </tr>"
                            +"            <tr >"
                            +"                <th align='right' width='40%'>Rol: </th>"
                            +"                <td>" + actividadInvestigacion.getSTipoLabor() + "</td>"
                            +"            </tr>"
                            +"            <tr >"
                            +"                <th align='right' width='40%'>Número de contrato o Resolución de seleccionados: </th>"
                            +"                <td>" + actividadInvestigacion.getSNumeroContrato() + "</td>"
                            +"            </tr>"
                            +"            <tr >"
                            +"                <th align='right' width='40%'>Duración del Proyecto : </th>"
                            +"                <td>" + actividadInvestigacion.getSPlazoEjecucion() + "</td>"
                            +"            </tr>"
                            +"        </table>"
                            +"    </td>"
                            +"</tr>";
                break;
            case 13 :
                respuesta = "<tr>"
                            +"    <td style='font-size: 0.9em;'>"
                            +"        <table border='1' cellpadding='3' cellspacing='0' width='100%'>"
                            +"            <tr >"
                            +"                <th align='right' width='40%'>Departamento : </th>"
                            +"                <td>" + actividadInvestigacion.getSDepartamento() + "</td>"
                            +"            </tr>"
                            +"            <tr >"
                            +"                <th align='right' width='40%'>Tipo de Actividad : </th>"
                            +"                <td> PRODUCCIÓN INTELECTUAL </td>"
                            +"            </tr>"
                            +"            <tr >"
                            +"                <th align='right' width='40%'>Tipo de Producción: </th>"
                            +"                <td>" + actividadInvestigacion.getSRITipoProduccion() + "</td>"
                            +"            </tr>"
                            +"            <tr >"
                            +"                <th align='right' width='40%'>Estado: </th>"
                            +"                <td>" + actividadInvestigacion.getSEstadoProduccion() + "</td>"
                            +"            </tr>"
                            +"            <tr >"
                            +"                <th align='right' width='40%'>Título : </th>"
                            +"                <td>" + actividadInvestigacion.getSNombreActividadInvestigacion() + "</td>"
                            +"            </tr>"
                            +"            <tr >"
                            +"                <th align='right' width='40%'>Fecha de Publicación: </th>"
                            +"                <td>" + actividadInvestigacion.getDFechaAceptacion() + "</td>"
                            +"            </tr>"
                            +"        </table>"
                            +"    </td>"
                            +"</tr>";
                break;
            default :
                respuesta = "";
                break;
        }
        
        return respuesta;
    }
    
}

class GMailAuthenticator extends Authenticator {
    String user;
    String pw;
    public GMailAuthenticator (String username, String password)
    {
       super();
       this.user = username;
       this.pw = password;
    }
    public PasswordAuthentication getPasswordAuthentication()
    {
       return new PasswordAuthentication(user, pw);
    }
}
