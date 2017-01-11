investigacionApp.controller('HeaderController',['$scope', '$sce', '$location', 'SharedService', '$window', '$localStorage',
function($scope, $sce, $location, SharedService, $window, $localStorage) {

    $scope.sharedService = SharedService;
    $scope.isActivo = "Actividades de Investigacion";
    $scope.sharedService.htmlMenu = $sce.trustAsHtml($scope.sharedService.stringMenu);
    $scope.sharedService.htmlMenuVertical = $sce.trustAsHtml($scope.sharedService.stringMenuVertical);

    $scope.nombreUsuario = $scope.sharedService.usuarioLogin.nombreUsuario;

    $scope.logout = function(){
        
        $window.localStorage.clear();
        $localStorage.usuarioLogin = "";
        $localStorage.autenticado = false;
        $localStorage.privilegios = "";
        $localStorage.htmlMenu = "";
        $localStorage.stringMenu = "";
        $localStorage.crearActividadHome = "";
        $localStorage.locationHome = "";
        $localStorage.idUsuarioRegistrar = "";
        $localStorage.docente = "";
        $localStorage.menuvertical = {};
        
        $scope.sharedService.usuarioLogin.nombreUsuario = "";
    	$scope.sharedService.userAutenticado = false;
        $scope.sharedService.privilegios = "";
        $scope.sharedService.htmlMenu = "";
        $scope.sharedService.stringMenu = "";
        $scope.sharedService.crearActividadHome = "";
        $scope.sharedService.locationHome = "";
        $scope.sharedService.idUsuarioRegistrar = "";
        $scope.sharedService.docente = "";
        $scope.sharedService.menuvertical = {};
        
//        sessvars.menuvertical = {};
//        sessvars.autenticado = false;
//        sessvars.nombreRol = "";
//        sessvars.nombreUsuario = "";
//        sessvars.idRol = 0;
//        sessvars.idUsuario = 0;
//        sessvars.privilegios = [];
//        sessvars.htmlMenu = null;
//        sessvars.stringMenu = "";
    	$location.path("/");
    };
    
    $scope.setMenuTab = function (event) {
        $scope.isActivo = event.target.innerText;
    };
    
    /*
     * Menu Vertical
     *  
     * */
    
    $scope.openReportes = false;
    $scope.openPermisos = false;
    $scope.openConfiguracion = false;
    
    $scope.openDropdownReportes = function(){
        if($scope.openReportes === false) {
            $scope.openReportes = true;
        } else {
            $scope.openReportes = false;
        }   
    };
    $scope.openDropdownConfiguracion = function(){
        if($scope.openConfiguracion === false) {
            $scope.openConfiguracion = true;
        } else {
            $scope.openConfiguracion = false;
        }   
    };
    $scope.openDropdownPermisos = function(){
        if($scope.openPermisos === false) {
            $scope.openPermisos = true;
        } else {
            $scope.openPermisos = false;
        }   
    };
    

}]);