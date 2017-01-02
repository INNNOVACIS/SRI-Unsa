investigacionApp.controller('LoginController', function($scope, $location, $log, $sce, SharedService, 
    PrivilegioService, LoginService, UsuariosService) {
    
    $scope.sharedService = SharedService;
    $scope.loader = false;
    
    var loginServiceSuccess = function(response){
        $log.debug("loginService - Success");
        console.log("Respuesta :: ", response);
        if(response !== "") {
            sessvars.nombreUsuario = response.nombreUsuario;
            sessvars.nombreRol = response.nombreRol;
            sessvars.idUsuario = response.idUsuario;
            sessvars.idRol = response.idRol;
            sessvars.autenticado = true;
            sessvars.idPersona = response.idPersona;
            
            $scope.sharedService.nombreUsuario = response.nombreUsuario;
            $scope.sharedService.nombreRol = response.nombreRol;
            $scope.sharedService.idUsuario = response.idUsuario;
            $scope.sharedService.idRol = response.idRol;
            $scope.sharedService.userAutenticado = sessvars.autenticado;
            $scope.getPrivilegios(response.idUsuario);                
        } else {
            alert("Usuario no registrado");
            $scope.loader = false;
        }
    };

    var loginServiceError = function(response){
        $log.debug("loginService - Error");
        console.log("Respuesta :: ", response);
        alert("Usuario no registrado");
        $scope.loader = false;
    };
    
    var getPrivilegiosByIdRolSuccess = function(response){
        $log.debug("getPrivilegiosByIdRol - Success");
        console.log("Respuesta :: ", response);
        sessvars.privilegios = response;
        $scope.sharedService.privilegios = sessvars.privilegios;
        
        //Crea el Menu Horizontal
        sessvars.stringMenu = CrearMenu($scope.sharedService.privilegios);
        sessvars.htmlMenu = $sce.trustAsHtml(sessvars.stringMenu);
        $scope.sharedService.htmlMenu = sessvars.htmlMenu;
        
        sessvars.stringMenuVertical = CrearMenuVertical($scope.sharedService.privilegios);
        sessvars.htmlMenuVertical = $sce.trustAsHtml(sessvars.stringMenuVertical);
        
        $scope.ShowMenuVertical($scope.sharedService.privilegios);
        $scope.sharedService.htmlMenuVertical = sessvars.htmlMenuVertical;
        
        $scope.GetActoresByIdUsuario($scope.sharedService.idUsuario);
        $scope.GetUsuariosByIdUsuario($scope.sharedService.idUsuario);
//        $location.path("/homeDirectorUnidad");
    };
    var getPrivilegiosByIdRolError = function(response){
        $log.debug("getPrivilegiosByIdRol - Error");
        console.log("Respuesta :: ", response);
        $scope.loader = false;
    };
    
    var GetActoresByIdUsuarioSuccess = function(response){
        $log.debug("GetActoresByIdUsuario - Success");
        console.log("Respuesta :: ", response);        
        sessvars.locationHome = seleccionarHome(response.body);
        sessvars.crearActividadHome = sessvars.locationHome.replace("/", "#");
        $scope.sharedService.locationHome = sessvars.locationHome;
        $scope.sharedService.crearActividadHome = sessvars.crearActividadHome;
        $location.path($scope.sharedService.locationHome);
    };
    var GetActoresByIdUsuarioError = function(response){
        $log.debug("GetActoresByIdUsuario - Error");
        console.log("Respuesta :: ", response);
    };
    
    var GetUsuariosByIdUsuarioSuccess = function(response){
        $log.debug("GetUsuariosByIdUsuario - Success");
        console.log("Respuesta :: ", response);
        sessvars.usuario = response.body;
        $scope.sharedService.usuario = response.body;
    };
    var GetUsuariosByIdUsuarioError = function(response){
        $log.debug("GetUsuariosByIdUsuario - Error");
        console.log("Respuesta :: ", response);
    };

    $scope.login = function(){
        $scope.loader = true;
        LoginService.Autenticar($scope.usuario).then(loginServiceSuccess, loginServiceError);
    };
    
    $scope.getPrivilegios = function(id){
        PrivilegioService.getPrivilegiosByIdRol(id).then(getPrivilegiosByIdRolSuccess, getPrivilegiosByIdRolError);
    };
    
    $scope.GetActoresByIdUsuario = function(idUsuario){
        UsuariosService.GetActoresByIdUsuario(idUsuario).then(GetActoresByIdUsuarioSuccess, GetActoresByIdUsuarioError);
    };
    $scope.GetUsuariosByIdUsuario = function(idUsuario){
        UsuariosService.GetByIdUsuario(idUsuario).then(GetUsuariosByIdUsuarioSuccess, GetUsuariosByIdUsuarioError);
    };
    
    var seleccionarHome = function(actores){
        var rango = 0;
        var location = "";
        angular.forEach(actores, function(value, key){
            if(value.scodigo === "DOCE" && rango === 0){
                location = "/homedocente";
            }
            if(value.scodigo === "DIUN" && rango === 0){
                rango = 1;
                location = "/homeDirectorUnidad";
            }
            if(value.scodigo === "VICE"){
                rango = 2;
                location = "/homeVicerector";
            }
        });
        return location;
    };
    
    var CrearMenu = function(privilegios){
        var menuHorizontal = "";
        
        angular.forEach(privilegios, function(value, key){
            var item = "";
            if(value.nidPadre === 0 && value.surlPrivilegio !== null && value.surlPrivilegio === '#homeDirectorUnidad'){
                item = GetItemHtml(value.surlPrivilegio, value.sNombrePrivilegio);
                menuHorizontal = menuHorizontal + item;
            }
        });
        
        
        angular.forEach(privilegios, function(value,key){
            var item = "";
            var subItem = "";
            // Item padre sin DropDown
            if(value.nidPadre === 0 && value.surlPrivilegio !== null && value.surlPrivilegio !== '#homeDirectorUnidad'){ //or ""
                item = GetItemHtml(value.surlPrivilegio, value.sNombrePrivilegio);
            }
            // Item padre con DropDown
            if(value.nidPadre === 0 && value.surlPrivilegio === null && value.sNombrePrivilegio !== "Permisos"){
                item = '<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-file-o fa-fw"></i> ' + value.sNombrePrivilegio + ' <i class="fa fa-caret-down"></i></a><ul class="dropdown-menu dropdown-user">';
                angular.forEach(privilegios, function(subValue, key){
                    if(subValue.nidPadre !== 0 &&  subValue.nidPadre === value.nidPrivilegio){
                        subItem = subItem + GetItemHtml(subValue.surlPrivilegio, subValue.sNombrePrivilegio);
                        subItem = subItem + '<li class="divider"></li>';
                    }
                });
                item = item + subItem + '</ul></li>';
            }
            menuHorizontal = menuHorizontal + item;
        });
        menuHorizontal = menuHorizontal + '<li class="dropdown"> <a class="dropdown-toggle" data-toggle="dropdown"   > <i class="fa fa-user fa-fw"></i>  <i class="fa fa-caret-down"></i></a><ul class="dropdown-menu dropdown-user"><li><a ng-click="logout()"><i class="fa fa-sign-out fa-fw"></i> Salir</a></li> </ul> </li>';
        return menuHorizontal;
    };
    
    var CrearMenuVertical = function(privilegios){
        var menuVertical = '<li class="sidebar-search"><a href="#home"><button class="btn btn-primary" type="button"><i class="fa fa-edit fa-fw"></i>  Crear Actividad</button></a></li>';
        /* Un primer for para mantener el orden*/
        angular.forEach(privilegios, function(value, key){
            var item = "";
            var subItem = "";
            //Item padre sin Dropdown
            if(value.sNombrePrivilegio === "Actividades Generadas" || value.sNombrePrivilegio === "Actividades Pendientes" || value.sNombrePrivilegio === "Actividades Revisadas"){
                item = GetItemHtml(value.surlPrivilegio, value.sNombrePrivilegio);
                menuVertical = menuVertical + item;
            }
        });
        angular.forEach(privilegios, function(value, key){
            var item = "";
            var subItem = "";
            // Item padre con DropDown
            if(value.nidPadre === 0 && value.surlPrivilegio === null && value.sNombrePrivilegio !== "Actividades"){
                item = '<li> <a ><i class="fa fa-files-o fa-fw"></i> ' + value.sNombrePrivilegio + '<span class="fa arrow"></span></a><ul class="nav nav-second-level">';
                angular.forEach(privilegios, function(subValue, key){
                    if(subValue.nidPadre !== 0 &&  subValue.nidPadre === value.nidPrivilegio){
                        subItem = subItem + GetItemHtml(subValue.surlPrivilegio, subValue.sNombrePrivilegio);
                    }
                });
                item = item + subItem + '</ul></li>';
            }
            menuVertical = menuVertical + item;
        });
        return menuVertical;
    };
    
    var GetItemHtml = function(url, nombre){
        return '<li><a href="' + url + '">' + nombre + '</a></li>';
    };
   
    $scope.ShowMenuVertical = function(privilegios){
        angular.forEach(privilegios, function(value, key){
            switch(value.snombrePrivilegio.toUpperCase()) {
                case "VICERECTOR":
                    sessvars.vicerector = true;
                    $scope.sharedService.vicerector = sessvars.vicerector;
                    break;
                case "HOME DOCENTE":
                    sessvars.homeDocente = true;
                    $scope.sharedService.homeDocente = sessvars.homeDocente;
                    break;
                case "HOME DIRECTOR UNIDAD":
                    sessvars.homeDirector = true;
                    $scope.sharedService.homeDirector = sessvars.homeDirector;
                    break;
                case "ACTIVIDAD DE INVESTIGACION":
                    sessvars.actividadInvestigacion = true;
                    $scope.sharedService.actividadInvestigacion = sessvars.actividadInvestigacion;
                    break;
                case "ACTIVIDAD DOCENTE":
                    sessvars.actividadDocente = true;
                    $scope.sharedService.actividadDocente = sessvars.actividadDocente;
                    break;
                case "ACTIVIDADES":
                    sessvars.actividades = true;
                    $scope.sharedService.actividades = sessvars.actividades;
                    break;
                case "REPORTES":
                    sessvars.reportes = true;
                    $scope.sharedService.reportes = sessvars.reportes;
                    break;
                case "CONFIGURACION":
                    sessvars.configuracion = true;
                    $scope.sharedService.configuracion = sessvars.configuracion;
                    break;
                case "PERMISOS":
                    sessvars.permisos = true;
                    $scope.sharedService.permisos = sessvars.permisos;
                    break;
                case "GENERADAS":
                    sessvars.generadas = true;
                    $scope.sharedService.generadas = sessvars.generadas;
                    break;
                case "PENDIENTES":
                    sessvars.pendientes = true;
                    $scope.sharedService.pendientes = sessvars.pendientes;
                    break;
                case "REVISADAS":
                    sessvars.revisadas = true;
                    $scope.sharedService.revisadas = sessvars.revisadas;
                    break;
                case "REVISADAS MASIVAS":
                    sessvars.revisadasMasivas = true;
                    $scope.sharedService.revisadasMasivas = sessvars.revisadasMasivas;
                    break;
                case "RELACION DOCENTES":
                    sessvars.relacionDocentes = true;
                    $scope.sharedService.relacionDocentes = sessvars.relacionDocentes;
                    break;
                case "ARCHIVOS":
                    sessvars.archivos = true;
                    $scope.sharedService.archivos = sessvars.archivos;
                    break;
                case "TIPO INVESTIGACION":
                    sessvars.showTipoInvestigacion = true;
                    $scope.sharedService.showTipoInvestigacion = sessvars.showTipoInvestigacion;
                    break;
                case "TIPO NIVEL":
                    sessvars.tipoNivel = true;
                    $scope.sharedService.tipoNivel = sessvars.tipoNivel;
                    break;
                case "TIPO INVESTIGADOR":
                    sessvars.tipoInvestigador = true;
                    $scope.sharedService.tipoInvestigador = sessvars.tipoInvestigador;
                    break;
                case "TIPO DE PRODUCCION":
                    sessvars.tipoProduccion = true;
                    $scope.sharedService.tipoProduccion = sessvars.tipoProduccion;
                    break;
                case "TIPO DE ASESORIA":
                    sessvars.tipoAsesoria = true;
                    $scope.sharedService.tipoAsesoria = sessvars.tipoAsesoria;
                    break;
                case "SEMESTRES ":
                    sessvars.semestres = true;
                    $scope.sharedService.semestres = sessvars.semestres;
                    break;
                case "ESTRUCTURA DE LA ORGANIZACION":
                    sessvars.estructuraOrganizacion = true;
                    $scope.sharedService.estructuraOrganizacion = sessvars.estructuraOrganizacion;
                    break;
                case "AREA DE INVESTIGACION":
                    sessvars.areaInvestigacion = true;
                    $scope.sharedService.areaInvestigacion = sessvars.areaInvestigacion;
                    break;
                case "FLUJO ARISTA":
                    sessvars.flujoArista = true;
                    $scope.sharedService.flujoArista = sessvars.flujoArista;
                    break;
                case "GENERAR CAMPOS":
                    sessvars.generarCampos = true;
                    $scope.sharedService.generarCampos = sessvars.generarCampos;
                    break;
                case "FUENTE DE FINANCIAMIENTO":
                    sessvars.fuenteFinanciamiento = true;
                    $scope.sharedService.fuenteFinanciamiento = sessvars.fuenteFinanciamiento;
                    break;
                case "USUARIOS":
                    sessvars.usuarios = true;
                    $scope.sharedService.usuarios = sessvars.usuarios;
                    break;
                case "ROLES":
                    sessvars.roles = true;
                    $scope.sharedService.roles = sessvars.roles;
                    break;
                case "ROL USUARIO":
                    sessvars.rolUsuario = true;
                    $scope.sharedService.rolUsuario = sessvars.rolUsuario;
                    break;
                case "PRIVILEGIOS":
                    sessvars.privilegio = true;
                    $scope.sharedService.privilegio = sessvars.privilegio;
                    break;
                case "ACTORES":
                    sessvars.actores = true;
                    $scope.sharedService.actores = sessvars.actores;
                    break;
                case "USUARIO ACTORES":
                    sessvars.usuarioActores = true;
                    $scope.sharedService.usuarioActores = sessvars.usuarioActores;
                    break;
                default:
                    console.log("PRIVILEGIO NO EXISTENTE :: ", value.snombrePrivilegio.toUpperCase());
            };
        });
    };
    
//    $scope.ShowMenuVertical = function(tipoActividad){
//        $scope.mostrarActividad = [false, false, false, false]; //case1 , case2, case3, case4
//        switch(tipoActividad.toUpperCase()) {
//            case "INVESTIGACION FORMATIVA":
//                $scope.mostrarActividad = [true, false, false, false];
//                break;
//            case "ASESORIA DE TESIS":
//                $scope.mostrarActividad = [false, true, false, false];
//                break;
//            case "INVESTIGACIONES BASICAS Y APLICADAS":
//                $scope.mostrarActividad = [false, false, true, false];
//                break;
//            case "PRODUCCION INTELECTUAL":
//                $scope.mostrarActividad = [false, false, false, true];
//                break;
//            default:
//                $scope.mostrarActividad = [false, false, false, false];
//        };
//    };
    
    
});

