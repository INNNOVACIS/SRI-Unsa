/*
Created		11/10/2016
Modified		24/10/2016
Project		
Model		
Company		
Author		
Version		
Database		mySQL 5 
*/


drop table IF EXISTS Estado;
drop table IF EXISTS ProcesoFlujoDestino;
drop table IF EXISTS TipoProduccion;
drop table IF EXISTS FondoConcursable;
drop table IF EXISTS TipoAsesoria;
drop table IF EXISTS EstructuraAreaInvestigacion;
drop table IF EXISTS Semestre;
drop table IF EXISTS TipoNivel;
drop table IF EXISTS EstructuraOrganizacion;
drop table IF EXISTS FlujoActor;
drop table IF EXISTS Privilegio;
drop table IF EXISTS Rol;
drop table IF EXISTS RolPrivilegio;
drop table IF EXISTS DetalleInvestigacionFlujo;
drop table IF EXISTS Archivo;
drop table IF EXISTS PlanificacionActividad;
drop table IF EXISTS ActividadInvetigacion;
drop table IF EXISTS ProcesoFlujo;
drop table IF EXISTS UsuarioFlujo;
drop table IF EXISTS UsuarioRol;
drop table IF EXISTS Usuario;
drop table IF EXISTS FlujoArista;
drop table IF EXISTS TipoActividadInvestigacion;


Create table TipoActividadInvestigacion (
	idtipoactividadinvestigacion Int NOT NULL,
	nombreinvestigacion Varchar(200),
	usercreacion Varchar(200),
	usermodificacion Varchar(200),
	fechacreacion Datetime,
	fechamodificacion Datetime,
	estado Char(1),
 Primary Key (idtipoactividadinvestigacion)) ENGINE = MyISAM;

Create table FlujoArista (
	idarista Int NOT NULL,
	idflujoactordestino Int NOT NULL,
	idflujoactororigen Int NOT NULL,
	idestado Int NOT NULL,
	usercreacion Varchar(200),
	usermodificacion Varchar(200),
	fechacreacion Datetime,
	fechamodificacion Datetime,
	estado Char(1),
 Primary Key (idarista)) ENGINE = MyISAM;

Create table Usuario (
	idusuario Int NOT NULL,
	usuariologin Varchar(200),
	usuariopassword Varchar(500),
	usercreacion Varchar(200),
	usermodificacion Varchar(200),
	fechacreacion Datetime,
	fechamodificacion Datetime,
	estado Char(1),
 Primary Key (idusuario)) ENGINE = MyISAM;

Create table UsuarioRol (
	idusuariorol Int NOT NULL,
	idusuario Int NOT NULL,
	idrol Int NOT NULL,
	usercreacion Varchar(200),
	usermodificacion Varchar(200),
	fechacreacion Datetime,
	fechamodificacion Datetime,
	estado Char(1),
 Primary Key (idusuariorol)) ENGINE = MyISAM;

Create table UsuarioFlujo (
	idusuarioflujo Int NOT NULL,
	idarista Int NOT NULL,
	idusuario Int NOT NULL,
	usercreacion Varchar(200),
	usermodificacion Varchar(200),
	fechacreacion Datetime,
	fechamodificacion Datetime,
	estado Char(1),
 Primary Key (idusuarioflujo)) ENGINE = MyISAM;

Create table ProcesoFlujo (
	idprocesoflujo Int NOT NULL,
	idusuarioflujo Int NOT NULL,
	flujo Char(20) NOT NULL,
	usercreacion Varchar(200),
	usermodificacion Varchar(200),
	fechacreacion Datetime,
	fechamodificacion Datetime,
	estado Char(1),
 Primary Key (idprocesoflujo)) ENGINE = MyISAM;

Create table ActividadInvetigacion (
	idactividadinvestigacion Int NOT NULL,
	idtipoactividadinvestigacion Int NOT NULL,
	horas Int,
	tipoproduccion Varchar(200),
	fondoconcursable Char(20),
	tipoasesoria Char(20),
	semestre Char(20),
	facultad Char(20),
	escuela Char(20),
	departamento Char(20),
	areainvestigacion Char(20),
	subareainvestigacion Char(20),
	disciplina Char(20),
	tipolabor Char(20),
	nombreactividadinvestigacion Char(20),
	descripcionactividad Char(20),
	usercrecion Varchar(200),
	usermodificacion Varchar(200),
	fechacreacion Datetime,
	fechamodificacion Datetime,
	estado Char(1),
 Primary Key (idactividadinvestigacion)) ENGINE = MyISAM;

Create table PlanificacionActividad (
	idplanificacionactividad Int NOT NULL,
	idactividadinvestigacion Int NOT NULL,
	horaparciales Int,
	usercreacion Varchar(200),
	usermodificacion Varchar(200),
	fechacreacion Datetime,
	fechamodificacion Datetime,
	estado Char(1),
 Primary Key (idplanificacionactividad)) ENGINE = MyISAM;

Create table Archivo (
	idarchivo Char(20) NOT NULL,
	idplanificacionactividad Int NOT NULL,
	nombrearchivo Varchar(200),
	archivo Blob,
	usercreacion Varchar(200),
	usermodificacion Varchar(200),
	fechacreacion Datetime,
	fechamodificacion Datetime,
	estado Char(1),
 Primary Key (idarchivo)) ENGINE = MyISAM;

Create table DetalleInvestigacionFlujo (
	iddetalleinvestigacionflujo Int NOT NULL,
	idprocesoflujo Int NOT NULL,
	idactividadinvestigacion Int NOT NULL,
	usercreacion Varchar(200),
	usermodificacion Varchar(200),
	fechacreacion Datetime,
	fechamodificacion Datetime,
	estado Char(1),
 Primary Key (iddetalleinvestigacionflujo)) ENGINE = MyISAM;

Create table RolPrivilegio (
	idrolprivilegio Int NOT NULL,
	idrol Int NOT NULL,
	idprivilegio Int NOT NULL,
	usercreacion Varchar(200),
	usermodificacion Varchar(200),
	fechacreacion Datetime,
	fechamodificacion Datetime,
	estado Char(1),
 Primary Key (idrolprivilegio)) ENGINE = MyISAM;

Create table Rol (
	idrol Int NOT NULL,
	nombreRol Varchar(200),
	usercreacion Varchar(200),
	usermodificacion Varchar(200),
	fechacreacion Datetime,
	fechamodificacion Datetime,
	estado Char(1),
 Primary Key (idrol)) ENGINE = MyISAM;

Create table Privilegio (
	idprivilegio Int NOT NULL,
	nombrePrivilegio Varchar(200),
	usercreacion Varchar(200),
	usermodificacion Varchar(200),
	fechacreacion Datetime,
	fechamodificacion Datetime,
	estado Char(1),
 Primary Key (idprivilegio)) ENGINE = MyISAM;

Create table FlujoActor (
	idflujoactor Int NOT NULL,
	nombreactor Varchar(200),
	flujo Char(20) NOT NULL,
	usercreacion Varchar(200),
	usermodificacion Varchar(200),
	fechacreacion Datetime,
	fechamodificacion Datetime,
	estado Char(1),
 Primary Key (idflujoactor)) ENGINE = MyISAM;

Create table EstructuraOrganizacion (
	idestructuraorganizacion Int NOT NULL,
	idtiponivel Int NOT NULL,
	nombreestructuraorganizacion Varchar(200),
	idpadre Int NOT NULL,
	nivel Varchar(200) NOT NULL,
	usercreacion Varchar(200),
	usermodificacion Varchar(200),
	fechacreacion Datetime,
	fechamodificacion Datetime,
	estado Char(1),
 Primary Key (idestructuraorganizacion)) ENGINE = MyISAM;

Create table TipoNivel (
	idtiponivel Int NOT NULL,
	nombretiponivel Varchar(200),
	usercreacion Varchar(200),
	usermodificacion Varchar(200),
	fechacreacion Datetime,
	fechamodificacion Datetime,
	estado Char(1),
 Primary Key (idtiponivel)) ENGINE = MyISAM;

Create table Semestre (
	idsemestre Int NOT NULL,
	nombresemestre Varchar(200),
	fechacreacion Datetime,
	fechamodificacion Datetime,
	estado Char(1),
 Primary Key (idsemestre)) ENGINE = MyISAM;

Create table EstructuraAreaInvestigacion (
	idestructura Int NOT NULL,
	nombre Varchar(200),
	idpadre Int NOT NULL,
	nivel Varchar(200) NOT NULL,
	usercreacion Varchar(200),
	usermodificacion Varchar(200),
	fechacreacion Datetime,
	fechamodificacion Datetime,
	estado Char(1),
 Primary Key (idestructura)) ENGINE = MyISAM;

Create table TipoAsesoria (
	idtipoasesoria Int NOT NULL,
	nombretipoasesoria Varchar(200),
	usercreacion Varchar(200),
	usermodificacion Varchar(200),
	fechacreacion Datetime,
	fechamodificacion Datetime,
	estado Char(1),
 Primary Key (idtipoasesoria)) ENGINE = MyISAM;

Create table FondoConcursable (
	idfondoconcursable Int NOT NULL,
	nombrefondoconcursable Varchar(200),
	usercreacion Varchar(200),
	usermodificacion Varchar(200),
	fechacreacion Datetime,
	fechamodificacion Datetime,
	estado Char(1),
 Primary Key (idfondoconcursable)) ENGINE = MyISAM;

Create table TipoProduccion (
	idtipoproduccion Int NOT NULL,
	nombretipoproduccion Varchar(200),
	usercreacion Varchar(200),
	usermodificacion Varchar(200),
	fechacreacion Datetime,
	fechamodificacion Varchar(200),
	estado Char(1),
 Primary Key (idtipoproduccion)) ENGINE = MyISAM;

Create table ProcesoFlujoDestino (
	idprocesoflujodestino Int NOT NULL,
	idprocesoflujo Int NOT NULL,
	idusuarioflujo Int NOT NULL,
	estadoenvio Char(1),
	usercreacion Varchar(200),
	usermodificacion Varchar(200),
	fechacreacion Datetime,
	fechamodificacion Datetime,
	estado Char(1),
 Primary Key (idprocesoflujodestino)) ENGINE = MyISAM;

Create table Estado (
	idestado Int NOT NULL,
	nombreestado Varchar(200),
	usercreacion Varchar(200),
	usermodificacion Varchar(200),
	fechacreacion Datetime,
	fechamodificacion Datetime,
	estado Char(1),
 Primary Key (idestado)) ENGINE = MyISAM;


Alter table ActividadInvetigacion add Foreign Key (idtipoactividadinvestigacion) references TipoActividadInvestigacion (idtipoactividadinvestigacion) on delete  restrict on update  restrict;
Alter table UsuarioFlujo add Foreign Key (idarista) references FlujoArista (idarista) on delete  restrict on update  restrict;
Alter table UsuarioRol add Foreign Key (idusuario) references Usuario (idusuario) on delete  restrict on update  restrict;
Alter table UsuarioFlujo add Foreign Key (idusuario) references Usuario (idusuario) on delete  restrict on update  restrict;
Alter table ProcesoFlujo add Foreign Key (idusuarioflujo) references UsuarioFlujo (idusuarioflujo) on delete  restrict on update  restrict;
Alter table ProcesoFlujoDestino add Foreign Key (idusuarioflujo) references UsuarioFlujo (idusuarioflujo) on delete  restrict on update  restrict;
Alter table DetalleInvestigacionFlujo add Foreign Key (idprocesoflujo) references ProcesoFlujo (idprocesoflujo) on delete  restrict on update  restrict;
Alter table ProcesoFlujoDestino add Foreign Key (idprocesoflujo) references ProcesoFlujo (idprocesoflujo) on delete  restrict on update  restrict;
Alter table PlanificacionActividad add Foreign Key (idactividadinvestigacion) references ActividadInvetigacion (idactividadinvestigacion) on delete  restrict on update  restrict;
Alter table DetalleInvestigacionFlujo add Foreign Key (idactividadinvestigacion) references ActividadInvetigacion (idactividadinvestigacion) on delete  restrict on update  restrict;
Alter table Archivo add Foreign Key (idplanificacionactividad) references PlanificacionActividad (idplanificacionactividad) on delete  restrict on update  restrict;
Alter table RolPrivilegio add Foreign Key (idrol) references Rol (idrol) on delete  restrict on update  restrict;
Alter table UsuarioRol add Foreign Key (idrol) references Rol (idrol) on delete  restrict on update  restrict;
Alter table RolPrivilegio add Foreign Key (idprivilegio) references Privilegio (idprivilegio) on delete  restrict on update  restrict;
Alter table FlujoArista add Foreign Key (idflujoactororigen) references FlujoActor (idflujoactor) on delete  restrict on update  restrict;
Alter table FlujoArista add Foreign Key (idflujoactordestino) references FlujoActor (idflujoactor) on delete  restrict on update  restrict;
Alter table EstructuraOrganizacion add Foreign Key (idtiponivel) references TipoNivel (idtiponivel) on delete  restrict on update  restrict;
Alter table FlujoArista add Foreign Key (idestado) references Estado (idestado) on delete  restrict on update  restrict;


/* Users permissions */


