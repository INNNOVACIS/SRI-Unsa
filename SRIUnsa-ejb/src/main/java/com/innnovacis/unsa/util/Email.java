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
import java.security.GeneralSecurityException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Gen-Tya-TIConsul1
 */
public class Email {

    private static String RECIPIENT = "ali.arapa@gmail.com";

    public String GetEmail(SRIActividadInvestigacion data) {
        String table = "<html>                                                                                                                                         "
                + "<head>                                                                                                                                                        "
                + "<style>                                                                                                                                                       "
                + "body {font-size:100%}                                                                                                                                         "
                + "</style>                                                                                                                                                      "
                + "</head>                                                                                                                                                       "
                + "<body>                                                                                                                                                        "
                + "	<table>                                                                                                                                                    "
                + "		<tr style = 'width:100%;'>                                                                                                                                                   "
                + "	<td>                                                                                                                                                       "
                + "			<table style='border-collapse: collapse;' style = 'width:100%;'>                                                                                                                                            "
                + "				<thead style='background: #C5C5C5 ;'>                                                                "
                + "				<tr style='color:white;	background:#00008B'>                                                                                                   "
                + "				                                                                                                                                               "
                + "					<th>                                                                                                                                       "
                + "						  <img src=\"cid:LogoUnsa\" width='100px'/>                                                                             "
                + "					</th>                                                                                                                                      "
                + "			                                                                                                                                                   "
                + "						<th colspan='8' style='display:table-row; float:left; padding-top:30px; width: 500px; font-family:'Comic Sans'; font-size:12pt;'>      "
                + "							<center>                                                                                                                           "
                + "								<b>SRI - CONFIRMACION DE OPERACIÓN</b><br/>                                                                                                "
                + "							</center>                                                                                                                          "
                + "						</th>                                                                                                                                  "
                + "						<th>                                                                                                                                   "
                + "							  <img src=\"cid:LogoUnsa\" width='100px' />                                                                          "
                + "						</th>                                                                                                                                  "
                + "				                                                                                                                                               "
                + "				</tr>                                                                                                                                          "
                + "				</thead>                                                                                                                                       "
                + "			</table>                                                                                                                                           "
                + "		</td>                                                                                                                                                  "
                + "	</tr>                                                                                                                                                      "
                + "		<tr style = 'width:100%;'>                                                                                                                                                   "
                + "		<td>                                                                                                                                                   "
                + "<table style = 'width:100%; background-color: white; border: 0;overflow: hidden;font: 9pt Tahoma;color: black;border-collapse: separate;border: Solid 1px #CCCCCC;'>"
                + "			<tbody width='100%' >                                                                                                                               "
                + "				<tr style=' border: 1px #ccc solid;  color:black; height:30px'>                                                           "
                + "			      <td>   <br />                                                                                                                                        "
                + "						Estimado  " + "Ali Arapa" + " se realizo el registro de su Actividad   con exito.<br/><br/> <hr />                                                                        "
                + "						Su codigo de Operacion es  " + "Codigo Operacion SRI00005489 <br/>  <br/>                                                                        "
                + "				</td>                                                                                                                                             "
                + "				</tr>                                                                                                                                          "
                + "				<tr style=' border: 1px #ccc solid;  color:black; height:30px'>                                                           "
+ "			      <td>                                                                                                                                           "
                + "<table style = 'width:100%; background-color: white; border: 0;overflow: hidden;font: 9pt Tahoma;color: black;border-collapse: separate;border: Solid 1px #CCCCCC;'>"
                +"<tr>"
                +"<td>Nombre</td><td> " + data.getSNombreActividadInvestigacion() + " </td></tr>"
                +"<tr><td>Facultad </td><td>" + data.getSFacultad() + " </td></tr>"
                +"<tr><td>Departamento </td><td>" + data.getSDepartamento() + "</td></tr>"
                +"<tr><td>Escuela </td><td> " + data.getSEscuela() + " </td></tr>"
                +"<tr><td><Duración de la Actividad</td><td> " + String.valueOf(data.getNHoras()) + " </td></tr>"
                +"<tr><td>Fondo Concursable </td><td> " + data.getSFondoConcursable() + " </td></tr>"
                +"<tr><td>Tipo de Producción</td><td>" + data.getSRITipoProduccion() + "</td></tr>"
                +"<tr><td>Colaboradores </td><td>" + "ninguno" + "</td></tr>"
                +"<tr><td>Area Investigación</td><td>" + data.getSAreaInvestigacion() + "</td></tr>"
                +"<tr><td>Sub Area Investigación</td><td> " + data.getSSubAreaInvestigacion() + " </td></tr>"
                +"<tr><td>Disciplina</td><td> " + data.getSDisciplina() + " </td></tr>"
                +"<tr><td>Descripcion de la Actividad </td><td> " + data.getSDescripcionActividad() + "</td></tr>"
                +"</table>"
                + "				</td>                                                                                                                                             "
                +"</tr>"
                +"<tr>"
                +"<tr>"
                +"<td><br /></td>"
                +"</tr>"
                + "			      <td>                                                                                                                                           "
                + "								Para acceder al Registro de su Actividad de Investigacion  .<a href='www.google.com.pe'>Click Aqui</a><br/>   <br/>                                                                      "
                + "				</td>                                                                                                                                             "
                + "				</tr>                                                                                                                                          "
                + "				                                                                                                                                               "
                + "				<tr style=' border: 1px #ccc solid;  color:black; height:30px'>                                                           "
                + "			      <td>                                                                                                                                           "
                + "							Atte. Sistema de Información UNSA <br/>                                                                        "
                + "				</td>                                                                                                                                             "
                + "				</tr>                                                                                                                                          "
                + "			</tbody>                                                                                                                                           "
                + "		</table>                                                                                                                                               "
                + "		</td>                                                                                                                                                  "
                + "		</tr>                                                                                                                                                  "
                + "		                                                                                                                                                       "
                + "     </table>                                                                                                                                                 "
                + "	                                                                                                                                                           "
                + "</body>                                                                                                                                                       "
                + "</html>                                                                                                                                                       ";
        return table;
    }

    public String readProperties(String value) {
        String respuesta = "";
        String path = System.getProperty("/home/will");
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
    
    public void initGmail(String recipient, String destino, SRIActividadInvestigacion data) throws GeneralSecurityException, IOException{
        Email email = new Email();
        String from = email.readProperties("user");
        String pass = MD5.decrypt(email.readProperties("password"));
        String[] to = {recipient, destino}; 
        String subject = "SRI UNSA - SISTEMA DE REGISTRO DE ACTIVIDADES DE INVESTIGACION";
        String body = GetEmail(data);

        sendFromGMail(from, pass, to, subject, body);
    }

    private void sendFromGMail(String from, String pass, String[] to, String subject, String body) {
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        MimeMultipart multipart = new MimeMultipart("related");

        // first part (the html)
        BodyPart messageBodyPart = new MimeBodyPart();
        String htmlText = body;
        try {
            messageBodyPart.setContent(htmlText, "text/html; charset=utf-8");
            multipart.addBodyPart(messageBodyPart);
            messageBodyPart = new MimeBodyPart();
            DataSource fds = new FileDataSource("/home/will/logounsa.png");
            messageBodyPart.setDataHandler(new DataHandler(fds));
            messageBodyPart.setHeader("Content-ID", "<LogoUnsa>");
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);
            message.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[to.length];

            // To get the array of addresses
            for (int i = 0; i < to.length; i++) {
                toAddress[i] = new InternetAddress(to[i]);
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
}