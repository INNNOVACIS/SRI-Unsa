investigacionApp.controller('loginController', function($scope, $location, $log, $sce, SharedService, 
    PrivilegioService, LoginService) {
    
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
            
            $scope.sharedService.nombreUsuario = response.nombreUsuario;
            $scope.sharedService.nombreRol = response.nombreRol;
            $scope.sharedService.idUsuario = response.idUsuario;
            $scope.sharedService.idRol = response.idRol;
            $scope.sharedService.userAutenticado = sessvars.autenticado;
            $scope.getPrivilegios(response.idRol);                
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
        sessvars.stringMenu = buildMenu($scope.sharedService.privilegios);
        sessvars.htmlMenu = $sce.trustAsHtml(sessvars.stringMenu);
        
        $scope.sharedService.htmlMenu = sessvars.htmlMenu;
        console.log("htmlMenu :: ", sessvars.htmlMenu);
        console.log("stringMenu :: ", sessvars.stringMenu);
        $location.path("/home");
    };
    var getPrivilegiosByIdRolError = function(response){
        $scope.loader = false;
    };

    $scope.login = function(){
        $scope.loader = true;
        LoginService.Autenticar($scope.usuario).then(loginServiceSuccess, loginServiceError);
    };
    
    $scope.getPrivilegios = function(id){
        PrivilegioService.getPrivilegiosByIdRol(id).then(getPrivilegiosByIdRolSuccess, getPrivilegiosByIdRolError);
    };
    
    var buildMenu = function(privilegios){
        
        angular.forEach(privilegios, function(privilegio, key) {
            //privilegio.dropdown = 0;
            if(privilegio.nidPadre !== 0){
                angular.forEach(privilegios, function(valor, key){
                     if(valor.nidPrivilegio === privilegio.nidPadre){
                         valor.dropdown = 1;
                     }
                });
            }
        });
        console.log("dropdown :: ", privilegios);
        var menu = "";
        angular.forEach(privilegios, function(privilegio, key) {
            if(privilegio.nidPadre === 0 && privilegio.dropdown === undefined){
                var inicio = '<li ng-class="{active: isActivo == '+ "'" + privilegio.sNombrePrivilegio + "'" + '}"><a href="' + privilegio.surlPrivilegio + '" ng-click="setMenuTab($event)">';
                var fin = privilegio.sNombrePrivilegio + '</a></li>';
                menu = menu + inicio + fin;
            }
            if(privilegio.nidPadre === 0 && privilegio.dropdown === 1) {
                var inicio = '<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true">' + privilegio.sNombrePrivilegio + '<span class="caret"></span></a><ul class="dropdown-menu">';
                var contenido = getDropDown(privilegios, privilegio.nidPrivilegio);
                var fin = '</ul></li>';
                menu = menu + inicio + contenido + fin;
            }
        });
        return menu;
    };
    var getDropDown = function(privilegios, padre){
        var menuDropDown = "";
        angular.forEach(privilegios, function(valor, key){
            if(valor.nidPadre === padre){
                var contenido = '<li ng-class="{active: isActivo == ' + "'" + valor.sNombrePrivilegio + "'" + '}"><a href="' + valor.surlPrivilegio + '" ng-click="setMenuTab($event)">' + valor.sNombrePrivilegio + '</a></li>';
                menuDropDown = menuDropDown + contenido;
            }
        });
        return menuDropDown;
    };
});
