investigacionApp.factory('SharedService', function($location) {

    return {
        usuarioLogin : sessvars.usuarioLogin,
        idUsuarioRegistrar : sessvars.idUsuarioRegistrar,
        usuarioHome : sessvars.usuarioHome,
        
        userAutenticado : sessvars.autenticado,
        nombreRol : sessvars.nombreRol,
        nombreUsuario : sessvars.nombreUsuario,
        idRol : sessvars.idRol,
        idUsuario : sessvars.idUsuario,
        idDocente : sessvars.idDocente,
        idPersona : sessvars.idPersona,
        privilegios : sessvars.privilegios,
        htmlMenu : sessvars.htmlMenu,
        stringMenu : sessvars.stringMenu,
        htmlMenuVertical : sessvars.htmlMenuVertical,
        stringMenuVertical : sessvars.stringMenuVertical,
        locationHome : sessvars.locationHome,
        crearActividadHome : sessvars.crearActividadHome,
        tipoInvestigacion : sessvars.tipoInvestigacion,
        usuario : sessvars.usuario,
        docente : sessvars.docente, //almacena el objeto docente del que se registrara las actividad.
        dateToString: function(date){
            var nDate = Number(date); 
            var dDate = new Date(nDate);
            var sDate = ("0" + dDate.getDate()).slice(-2) + "/" + ("0"+(dDate.getMonth()+1)).slice(-2) + "/" + dDate.getFullYear();
            return sDate;
        },
        scrollTop : function(){
                $('html,body').animate({
                scrollTop: $("#page-wrapper").offset().top - 100
            }, 800);
        },
        popUp : {
            open:false,
            close:true,
            titulo:'',
            mensaje:'',
            url:'',
            op1:{open:false, txt:'', fun:function(){}},
            clearPopUp : function(){
                    this.open = false;
                    this.close = true;
                    this.titulo = '';
                    this.mensaje='';
                    this.op1.open= false;                  
                    this.op1.txt = '';
            },
            irPopUp : function(){
                this.clearPopUp();
                $location.path(this.url);
            },
            showPopUp : function(_titulo, _mensaje, _url, _op1){
                    this.open = true;
                    this.close = false;     
                    this.titulo = _titulo;
                    this.mensaje = _mensaje;
                    this.url = _url;
                    if(_op1){     
                        this.op1 = _op1;
                    }
            }
        },
        isAutenticado : function(){
            return this.nombreUsuario === "" || this.nombreUsuario === undefined ? false : true;
        },
        isPermitido : function(url){
            var permitido = false;
            url = url.substring(1);
            angular.forEach(this.privilegios, function(valor, key){
                if(valor.surlPrivilegio !== null){
                    var privilegio = valor.surlPrivilegio.substring(1);
                    if(url.indexOf(privilegio) !== -1) {
                        permitido = true;
                    }
                }
            });
            return permitido;
        },
        // Show Menu Vertical
        vicerector : sessvars.vicerector,
        homeDocente : sessvars.homeDoce,
        homeDirector : sessvars.homeDirector,
        actividadInvestigacion : sessvars.actividadInvestigacion,
        actividadDocente : sessvars.actividadDocente,
        actividades : sessvars.actividades,
        reportes : sessvars.reportes,
        configuracion : sessvars.configuracion,
        permisos : sessvars.permisos,
        generadas : sessvars.generadas,
        pendientes : sessvars.pendientes,
        revisadas : sessvars.revisadas,
        revisadasMasivas : sessvars.revisadasMasivas,
        relacionDocentes : sessvars.relacionDocentes,
        archivos : sessvars.archivos,
        showTipoInvestigacion : sessvars.showTipoInvestigacion,
        tipoNivel : sessvars.tipoNivel,
        tipoInvestigador : sessvars.tipoInvestigador,
        tipoProduccion : sessvars.tipoProduccion,
        tipoAsesoria : sessvars.tipoAsesoria,
        semestres : sessvars.semestres,
        estructuraOrganizacion : sessvars.estructuraOrganizacion,
        areaInvestigacion : sessvars.areaInvestigacion,
        flujoArista : sessvars.flujoArista,
        generarCampos : sessvars.generarCampos,
        fuenteFinanciamiento : sessvars.fuenteFinanciamiento,
        usuarios : sessvars.usuarios,
        roles : sessvars.roles,
        rolUsuario : sessvars.rolUsuario,
        privilegio : sessvars.privilegio,
        actores : sessvars.actores,
        usuarioActores : sessvars.usuarioActores,    
    };
});

investigacionApp.factory('SeguridadEnrutamiento', function($q, SharedService) {

    return {
        verificarPropiedades: function(){
            return "";
        }
    };
});