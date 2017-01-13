investigacionApp.controller('LoginController',['$scope', '$location', '$log', '$sce', 'SharedService', 
    'PrivilegioService', 'LoginService', 'UsuariosService', '$localStorage', function($scope, $location, $log, $sce, SharedService, 
    PrivilegioService, LoginService, UsuariosService, $localStorage) {
    
    $scope.sharedService = SharedService;
    $scope.loader = false;
    
    var loginServiceSuccess = function(response){
        $log.debug("loginService - Success");
        console.log("Respuesta :: ", response);
        
        if(response !== "") {
            $localStorage.usuarioLogin = response;
            $localStorage.autenticado = true;
            
            $scope.sharedService.usuarioLogin = $localStorage.usuarioLogin;
            $scope.sharedService.userAutenticado = $localStorage.autenticado;
            
            $scope.GetPrivilegiosByIdUsuario($scope.sharedService.usuarioLogin.idUsuario);                
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
    
    var GetPrivilegiosByIdUsuarioSuccess = function(response){
        $log.debug("GetPrivilegiosByIdUsuario - Success");
        console.log("Respuesta :: ", response);
        
        $localStorage.privilegios = response;
        $scope.sharedService.privilegios = $localStorage.privilegios;
        
        //Crea el Menu Horizontal
        $localStorage.stringMenu = CrearMenu($scope.sharedService.privilegios);
        $localStorage.htmlMenu = $sce.trustAsHtml($localStorage.stringMenu);
        $scope.sharedService.htmlMenu = $localStorage.htmlMenu;
        $scope.ShowMenuVertical($scope.sharedService.privilegios);
        
        $scope.GetActoresByIdUsuario($scope.sharedService.usuarioLogin.idUsuario);
    };
    var GetPrivilegiosByIdUsuarioError = function(response){
        $log.debug("GetPrivilegiosByIdUsuario - Error");
        console.log("Respuesta :: ", response);
        $scope.loader = false;
    };
    
    var GetActoresByIdUsuarioSuccess = function(response){
        $log.debug("GetActoresByIdUsuario - Success");
        console.log("Respuesta :: ", response);        
        $localStorage.locationHome = seleccionarHome(response.body);
        $localStorage.crearActividadHome = $localStorage.locationHome.replace("/", "#");
        
        $scope.sharedService.locationHome = $localStorage.locationHome;
        $scope.sharedService.crearActividadHome = $localStorage.crearActividadHome;
        $location.path($scope.sharedService.locationHome);
    };
    var GetActoresByIdUsuarioError = function(response){
        $log.debug("GetActoresByIdUsuario - Error");
        console.log("Respuesta :: ", response);
    };

    $scope.login = function(){
        $scope.loader = true;
        LoginService.Autenticar($scope.usuario).then(loginServiceSuccess, loginServiceError);
    };
    
    $scope.GetPrivilegiosByIdUsuario = function(id){
        PrivilegioService.GetPrivilegiosByIdUsuario(id).then(GetPrivilegiosByIdUsuarioSuccess, GetPrivilegiosByIdUsuarioError);
    };
    
    $scope.GetActoresByIdUsuario = function(idUsuario){
        UsuariosService.GetActoresByIdUsuario(idUsuario).then(GetActoresByIdUsuarioSuccess, GetActoresByIdUsuarioError);
    };
    
    var seleccionarHome = function(actores){
        var rango = 0;
        var location = "";
        angular.forEach(actores, function(value, key){
            if(value.scodigo === "DOCE" && rango === 0){
                location = "/homedocente";
                $localStorage.idUsuarioRegistrar = -1,
                $scope.sharedService.idUsuarioRegistrar = $localStorage.idUsuarioRegistrar;
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
            if(value.nidPadre === 0 && value.surlPrivilegio !== null && value.surlPrivilegio !== '#homeDirectorUnidad' && value.surlPrivilegio !== '#home'){ //or ""
                item = GetItemHtml(value.surlPrivilegio, value.sNombrePrivilegio);
            }
            // Item padre con DropDown
            if(value.nidPadre === 0 && value.surlPrivilegio === null && value.sNombrePrivilegio !== "Permisos"){
                item = '<li class="dropdown"><a class="dropdown-toggle SRI-Pointer" data-toggle="dropdown"><i class="fa fa-file-o fa-fw"></i> ' + value.sNombrePrivilegio + ' <i class="fa fa-caret-down"></i></a><ul class="dropdown-menu dropdown-user">';
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
        menuHorizontal = menuHorizontal + '<li class="dropdown"> <a class="dropdown-toggle SRI-Pointer" data-toggle="dropdown"   > <i class="fa fa-user fa-fw"></i> ' 
                                        + '{{ sharedService.usuarioLogin.nombre + " " + sharedService.usuarioLogin.apellido}}' 
                                        + ' <i class="fa fa-caret-down"></i></a><ul class="dropdown-menu dropdown-user">'
                                        + '<li><a ng-click="cambiarContrasena()"><i class="fa fa-refresh fa-fw"></i> Cambiar Contraseña</a></li>'
                                        + '<li><a ng-click="logout()"><i class="fa fa-sign-out fa-fw"></i> Salir</a></li> </ul> </li>';
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
        $localStorage.menuvertical = {};
        $scope.sharedService.menuvertical = {};
        angular.forEach(privilegios, function(value, key){
            switch(value.snombrePrivilegio.toUpperCase()) {
                case "VICERECTOR":
                    $localStorage.menuvertical.vicerector = true;
                    $scope.sharedService.menuvertical.vicerector = true;
                    break;
                case "HOME DOCENTE":
                    $localStorage.menuvertical.homeDocente = true;
                    $scope.sharedService.menuvertical.homeDocente = true;
                    break;
                case "HOME DIRECTOR UNIDAD":
                    $localStorage.menuvertical.homeDirector = true;
                    $scope.sharedService.menuvertical.homeDirector = true;
                    break;
                case "ACTIVIDAD DE INVESTIGACION":
                    $localStorage.menuvertical.actividadInvestigacion = true;
                    $scope.sharedService.menuvertical.actividadInvestigacion = true;
                    break;
                case "ACTIVIDAD DOCENTE":
                    $localStorage.menuvertical.actividadDocente = true;
                    $scope.sharedService.menuvertical.actividadDocente = true;
                    break;
                case "ACTIVIDADES":
                    $localStorage.menuvertical.actividades = true;
                    $scope.sharedService.menuvertical.actividades = true;
                    break;
                case "REPORTES":
                    $localStorage.menuvertical.reportes = true;
                    $scope.sharedService.menuvertical.reportes = true;
                    break;
                case "CONFIGURACION":
                    $localStorage.menuvertical.configuracion = true;
                    $scope.sharedService.menuvertical.configuracion = true;
                    break;
                case "PERMISOS":
                    $localStorage.menuvertical.permisos = true;
                    $scope.sharedService.menuvertical.permisos = true;
                    break;
                case "GENERADAS":
                    $localStorage.menuvertical.generadas = true;
                    $scope.sharedService.menuvertical.generadas = true;
                    break;
                case "PENDIENTES":
                    $localStorage.menuvertical.pendientes = true;
                    $scope.sharedService.menuvertical.pendientes = true;
                    break;
                case "REVISADAS":
                    $localStorage.menuvertical.revisadas = true;
                    $scope.sharedService.menuvertical.revisadas = true;
                    break;
                case "REVISADAS MASIVAS":
                    $localStorage.menuvertical.revisadasMasivas = true;
                    $scope.sharedService.menuvertical.revisadasMasivas = true;
                    break;
                case "RELACION DOCENTES":
                    $localStorage.menuvertical.relacionDocentes = true;
                    $scope.sharedService.menuvertical.relacionDocentes = true;
                    break;
                case "ARCHIVOS":
                    $localStorage.menuvertical.archivos = true;
                    $scope.sharedService.menuvertical.archivos = true;
                    break;
                case "TIPO INVESTIGACION":
                    $localStorage.menuvertical.showTipoInvestigacion = true;
                    $scope.sharedService.menuvertical.showTipoInvestigacion = true;
                    break;
                case "TIPO NIVEL":
                    $localStorage.menuvertical.tipoNivel = true;
                    $scope.sharedService.menuvertical.tipoNivel = true;
                    break;
                case "TIPO INVESTIGADOR":
                    $localStorage.menuvertical.tipoInvestigador = true;
                    $scope.sharedService.menuvertical.tipoInvestigador = true;
                    break;
                case "TIPO DE PRODUCCION":
                    $localStorage.menuvertical.tipoProduccion = true;
                    $scope.sharedService.menuvertical.tipoProduccion = true;
                    break;
                case "TIPO DE ASESORIA":
                    $localStorage.menuvertical.tipoAsesoria = true;
                    $scope.sharedService.menuvertical.tipoAsesoria = true;
                    break;
                case "SEMESTRES ":
                    $localStorage.menuvertical.semestres = true;
                    $scope.sharedService.menuvertical.semestres = true;
                    break;
                case "ESTRUCTURA DE LA ORGANIZACION":
                    $localStorage.menuvertical.estructuraOrganizacion = true;
                    $scope.sharedService.menuvertical.estructuraOrganizacion = true;
                    break;
                case "AREA DE INVESTIGACION":
                    $localStorage.menuvertical.areaInvestigacion = true;
                    $scope.sharedService.menuvertical.areaInvestigacion = true;
                    break;
                case "FLUJO ARISTA":
                    $localStorage.menuvertical.flujoArista = true;
                    $scope.sharedService.menuvertical.flujoArista = true;
                    break;
                case "GENERAR CAMPOS":
                    $localStorage.menuvertical.generarCampos = true;
                    $scope.sharedService.menuvertical.generarCampos = true;
                    break;
                case "FUENTE DE FINANCIAMIENTO":
                    $localStorage.menuvertical.fuenteFinanciamiento = true;
                    $scope.sharedService.menuvertical.fuenteFinanciamiento = true;
                    break;
                case "USUARIOS":
                    $localStorage.menuvertical.usuarios = true;
                    $scope.sharedService.menuvertical.usuarios = true;
                    break;
                case "ROLES":
                    $localStorage.menuvertical.roles = true;
                    $scope.sharedService.menuvertical.roles = true;
                    break;
                case "ROL USUARIO":
                    $localStorage.menuvertical.rolUsuario = true;
                    $scope.sharedService.menuvertical.rolUsuario = true;
                    break;
                case "PRIVILEGIOS":
                    $localStorage.menuvertical.privilegio = true;
                    $scope.sharedService.menuvertical.privilegio = true;
                    break;
                case "ACTORES":
                    $localStorage.menuvertical.actores = true;
                    $scope.sharedService.menuvertical.actores = true;
                    break;
                case "USUARIO ACTORES":
                    $localStorage.menuvertical.usuarioActores = true;
                    $scope.sharedService.menuvertical.usuarioActores = true;
                    break;
                default:
                    console.log("PRIVILEGIO NO EXISTENTE :: ", value.snombrePrivilegio.toUpperCase());
            };
        });
    };
    
    
}]);

