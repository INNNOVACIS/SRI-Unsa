/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.util;

import com.innnovacis.unsa.model.SRIActividadInvestigacion;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
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

    public String GetEmail(SRIActividadInvestigacion data, String actor, String nombreCompleto) {
        String table = "";
        switch(actor){
            case "DOCE":
                    table = "<html>"
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
+"										Estimado " + nombreCompleto + " se realizo el registro de su Actividad con exito."
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
                break;
            case "DIUN":
                table = "<html>"
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
+"										Estimado " + nombreCompleto + " se aprobó la siguiente Actividad de Investigación, pendiente de su aprobación."
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
+"										Para revisar la Actividad de Investigacion: <a href='http://www.google.com.pe' style='color: #ffffff;'><font color='#ffffff'>Click aqui</font></a> <br/>"
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
                break;
            case "DECA":
                table = "<html>"
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
+"										Estimado " + nombreCompleto + " se aprobó la siguiente Actividad de Investigación."
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
+"										Para imprimir el reporte dar  <a href='http://104.131.8.31:8080/SRIUnsa-web/#/' style='color: #ffffff;'><font color='#ffffff'>Click aqui</font></a> <br/>"
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
                break;
            case "DIGE":
                table = "<html>"
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
+"										Estimado " + nombreCompleto + " se realizo el registro de su Actividad con exito, con Codigo Operacion SRI00005489."
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
                break;
        }
        
        
        return table;
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
    
    public String GetBodyEmailInforme(String nombreCompleto) {
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
+"										Le envio el informe de las actividades generadas para el semestre actual."
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
    
    public void initGmail(List<SRIUsuarioPersona> destinatarios, SRIActividadInvestigacion data, String actor) throws GeneralSecurityException, IOException{
        Email email = new Email();
        String from = email.readProperties("user");
        String pass = MD5.decrypt(email.readProperties("password"));
        String host = email.readProperties("host");
        String port = email.readProperties("port");
        String auth = email.readProperties("auth");
        String enable = email.readProperties("enable");
        
        for(SRIUsuarioPersona destinatario : destinatarios){
            List<String> to = new ArrayList<String>();
            to.add(destinatario.getSUsuarioEmail());
            String subject = "SRI UNSA - SISTEMA DE REGISTRO DE ACTIVIDADES DE INVESTIGACION";
            String nombreCompleto = destinatario.getSNombre() + " " + destinatario.getSApellido();
            String body = GetEmail(data, actor, nombreCompleto);
            sendFromGMail("true", "true", "465", "smtp.gmail.com", "innnovacisaqp", "innnovacis.", to, subject, body);
        }
        
        this.info = from + " : " + pass + " : " + host + " : " + pass + " : " + port + " : " + auth + " : " + enable;
    }
    
    public void enviarInforme(byte[] informe, List<String> destinatarios) throws GeneralSecurityException, IOException{
        Email email = new Email();
        String from = email.readProperties("user");
        String pass = MD5.decrypt(email.readProperties("password"));
        String host = email.readProperties("host");
        String port = email.readProperties("port");
        String auth = email.readProperties("auth");
        String enable = email.readProperties("enable");
        
        for(String destinatario : destinatarios){
            List<String> to = new ArrayList<String>();
            to.add(destinatario);
            String subject = "SRI-UNSA - SISTEMA DE REGISTRO DE ACTIVIDADES DE INVESTIGACION";
            String nombreCompleto = "";
            String body = GetBodyEmailInforme(nombreCompleto);
            sendFromGMailInforme("true", "true", "465", "smtp.gmail.com", "innnovacisaqp", "innnovacis.", to, subject, body, informe);
        }
    }
    
    public void enviarCodigoEmail(String codigo, List<String> destinatarios) throws GeneralSecurityException, IOException{
        Email email = new Email();
        String from = email.readProperties("user");
        String pass = MD5.decrypt(email.readProperties("password"));
        String host = email.readProperties("host");
        String port = email.readProperties("port");
        String auth = email.readProperties("auth");
        String enable = email.readProperties("enable");
        
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

    public void sendFromGMail(String enable, String auth, String port, String host, String from, String pass, List<String> to, String subject, String body) {
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
//        log.log(Level.INFO, "Email enable : {0}", enable);
//        log.log(Level.INFO, "Email host   : {0}", host);
//        log.log(Level.INFO, "Email user   : {0}", from);
//        log.log(Level.INFO, "Email password : {0}", pass);
//        log.log(Level.INFO, "Email port : {0}", port);
//        log.log(Level.INFO, "Email auth : {0}", auth);
        // first part (the html)
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
    
    public void sendFromGMailInforme(String enable, String auth, String port, String host, String from, String pass, List<String> to, String subject, String body, byte[] informe) {
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
            
            //ENVIAR INFORME BYTE
            DataSource dataSource = new ByteArrayDataSource(informe, "application/pdf");
            MimeBodyPart pdfBodyPart = new MimeBodyPart();
            pdfBodyPart.setDataHandler(new DataHandler(dataSource));
            pdfBodyPart.setFileName("Actividades Generadas.pdf");
            multipart.addBodyPart(pdfBodyPart);
            
            
            message.setContent(multipart);
            message.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[to.size()];
            
            
            
//            BufferedDataSource bds = new BufferedDataSource(informe, "Informe"); 
//            messageBodyPart.setDataHandler(new DataHandler(bds)); 
//            messageBodyPart.setFileName(bds.getName());
            
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


class BufferedDataSource implements DataSource { 

    private byte[] _data; 
    private java.lang.String _name; 

    public BufferedDataSource(byte[] data, String name) { 
    _data = data; 
    _name = name;
    } 

public String getContentType() { return "application/octet-stream";} 
public InputStream getInputStream() throws IOException { return new ByteArrayInputStream(_data);} 
public String getName() { return _name;} 

/** 
* Returns an OutputStream from the DataSource 
* @returns OutputStream Array of bytes converted into an OutputStream 
*/ 
public OutputStream getOutputStream() throws IOException { 
OutputStream out = new ByteArrayOutputStream(); 
out.write(_data); 
return out;
}
}