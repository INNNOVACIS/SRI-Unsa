investigacionApp.controller('LoginController', function($scope, $location, $log, $sce, SharedService, 
    PrivilegioService, LoginService, UsuariosService) {
    
    $scope.sharedService = SharedService;
    $scope.loader = false;
    
    var loginServiceSuccess = function(response){
        console.log("autenticación success :: ", response);
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
        console.log("error");
        alert("Usuario no registrado");
        $scope.loader = false;
    };
    
    var getPrivilegiosByIdRolSuccess = function(response){
        $log.debug("getPrivilegiosByIdRolSuccess");        
        sessvars.privilegios = response;
        $scope.sharedService.privilegios = sessvars.privilegios;
        
        sessvars.stringMenu = CrearMenu($scope.sharedService.privilegios);
        sessvars.htmlMenu = $sce.trustAsHtml(sessvars.stringMenu);
        $scope.sharedService.htmlMenu = sessvars.htmlMenu;
        
        sessvars.stringMenuVertical = CrearMenuVertical($scope.sharedService.privilegios);
        sessvars.htmlMenuVertical = $sce.trustAsHtml(sessvars.stringMenuVertical);
        $scope.sharedService.htmlMenuVertical = sessvars.htmlMenuVertical;
        
        $scope.GetActoresByIdUsuario($scope.sharedService.idUsuario);
//        $location.path("/homeDirectorUnidad");
    };
    var getPrivilegiosByIdRolError = function(response){
        $scope.loader = false;
    };
    
    var GetActoresByIdUsuarioSuccess = function(response){
        $log.debug("GetActoresByIdUsuario - Success");
        console.log("Respuesta :: ", response);
        sessvars.locationHome = seleccionarHome(response.body);
        $scope.sharedService.locationHome = sessvars.locationHome;
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
    
    $scope.getPrivilegios = function(id){
        PrivilegioService.getPrivilegiosByIdRol(id).then(getPrivilegiosByIdRolSuccess, getPrivilegiosByIdRolError);
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
            }
            if(value.scodigo === "DIUN"){
                rango = 1;
                location = "/homeDirectorUnidad";
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
    
    $scope.ShowMenuVertical = function(tipoActividad){
        $scope.mostrarActividad = [false, false, false, false]; //case1 , case2, case3, case4
        switch(tipoActividad.toUpperCase()) {
            case "INVESTIGACION FORMATIVA":
                $scope.mostrarActividad = [true, false, false, false];
                break;
            case "ASESORIA DE TESIS":
                $scope.mostrarActividad = [false, true, false, false];
                break;
            case "INVESTIGACIONES BASICAS Y APLICADAS":
                $scope.mostrarActividad = [false, false, true, false];
                break;
            case "PRODUCCION INTELECTUAL":
                $scope.mostrarActividad = [false, false, false, true];
                break;
            default:
                $scope.mostrarActividad = [false, false, false, false];
        };
    };
    
    
});

