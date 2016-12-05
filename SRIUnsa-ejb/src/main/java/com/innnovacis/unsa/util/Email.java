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
import java.util.List;
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
        String table = "<html>"
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
+"										Estimado Ali Arapa se realizo el registro de su Actividad con exito, con Codigo Operacion SRI00005489."
+"									</td>"
+"								</tr>"
+"								<tr>"
+"									<td style='font-size: 0.9em;'>"
+"										<table border='1' cellpadding='3' cellspacing='0' width='100%'>"
+"											<tr >"
+"											    <th align='right' width='40%'>Nombre de la Actividad : </th>"
+"											    <td>" + data.getSNombreActividadInvestigacion() + "</td>"
+"											</tr>"
+"											<tr >"
+"											    <th align='right' width='40%'>Tipo Actividad de Investigación : </th>"
+"											    <td>" + data.getNIdTipoActividadInvestigacion() + "</td>"
+"											</tr>"
+"											<tr >"
+"											    <th align='right' width='40%'>Facultad : </th>"
+"											    <td>" + data.getSFacultad() + "</td>"
+"											</tr>"
+"											<tr >"
+"											    <th align='right' width='40%'>Departamento : </th>"
+"											    <td>" + data.getSDepartamento() + "</td>"
+"											</tr>"
+"											<tr >"
+"											    <th align='right' width='40%'>Escuela : </th>"
+"											    <td>" + data.getSEscuela() + "</td>"
+"											</tr>"
+"											<tr >"
+"											    <th align='right' width='40%'>Fondo Concursable : </th>"
+"											    <td>" + data.getSFondoConcursable() + "</td>"
+"											</tr>"
+"											<tr >"
+"											    <th align='right' width='40%'>Area Investigación : </th>"
+"											    <td>" + data.getSAreaInvestigacion() + "</td>"
+"											</tr>"
+"											<tr >"
+"											    <th align='right' width='40%'>SubArea Investigación : </th>"
+"											    <td>" + data.getSSubAreaInvestigacion() + "</td>"
+"											</tr>"
+"											<tr >"
+"											    <th align='right' width='40%'>Disciplina : </th>"
+"											    <td>" + data.getSDisciplina() + "</td>"
+"											</tr>"
+"											<tr >"
+"											    <th align='right' width='40%'>Numero de horas : </th>"
+"											    <td>" + String.valueOf(data.getNHoras()) + "</td>"
+"											</tr>"
+"											<tr >"
+"											    <th align='right' width='40%'>Descripción : </th>"
+"											    <td>" + data.getSDescripcionActividad() + "</td>"
+"											</tr>"
+"										</table>"
+"									</td>"
+"								</tr>"
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
+"									<td align='right' width='20%'>"
+"										<table border='0' cellpadding='0' cellspacing='0'>"
+"											<tr>"
+"												<td style='font-family: Arial, sans-serif; font-size: 12px; font-weight: bold;'>"
+"													<a href='http://www.twitter.com/' style='color: #ffffff;'>"
+"														<img src='images/tw.gif' alt='Twitter' width='38' height='38' style='display: block;' border='0' />"
+"													</a>"
+"												</td>"
+"												<td style='font-size: 0; line-height: 0;' width='20'>&nbsp;</td>"
+"												<td style='font-family: Arial, sans-serif; font-size: 12px; font-weight: bold;'>"
+"													<a href='http://www.twitter.com/' style='color: #ffffff;'>"
+"														<img src='images/fb.gif' alt='Facebook' width='38' height='38' style='display: block;' border='0' />"
+"													</a>"
+"												</td>"
+"											</tr>"
+"										</table>"
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
        return table;
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
    
    public void initGmail(List<String> destinatarios, SRIActividadInvestigacion data) throws GeneralSecurityException, IOException{
        Email email = new Email();
        String from = email.readProperties("user");
        String pass = MD5.decrypt(email.readProperties("password"));
        String host = email.readProperties("host");
        String port = email.readProperties("port");
        String auth = email.readProperties("auth");
        String enable = email.readProperties("enable");
        List<String> to = destinatarios;
        String subject = "SRI UNSA - SISTEMA DE REGISTRO DE ACTIVIDADES DE INVESTIGACION";
        String body = GetEmail(data);

        sendFromGMail(enable, auth, port, host, from, pass, to, subject, body);
    }

    private void sendFromGMail(String enable, String auth, String port, String host, String from, String pass, List<String> to, String subject, String body) {
        Properties props = System.getProperties();
        props.put("mail.smtp.starttls.enable", enable);
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", auth);

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
            URL url = this.getClass().getProtectionDomain().getCodeSource().getLocation();
            String path = url.getPath();
            DataSource fds = new FileDataSource(path + "/logo-unsa-2.jpg");
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
}