investigacionApp.controller('HeaderController', function($scope, $sce, $location, SharedService) {

    $scope.sharedService = SharedService;
    $scope.isActivo = "Actividades de Investigacion";
    $scope.sharedService.htmlMenu = $sce.trustAsHtml($scope.sharedService.stringMenu);
    $scope.sharedService.htmlMenuVertical = $sce.trustAsHtml($scope.sharedService.stringMenuVertical);

    $scope.message = $scope.sharedService.nombreUsuario;

    $scope.logout = function(){
    	$scope.sharedService.userAutenticado = false;
        $scope.sharedService.nombreUsuario = "";
        $scope.sharedService.nombreRol = "";
        $scope.sharedService.idUsuario = 0;
        $scope.sharedService.idRol = 0;
        
        sessvars.autenticado = false;
        sessvars.nombreRol = "";
        sessvars.nombreUsuario = "";
        sessvars.idRol = 0;
        sessvars.idUsuario = 0;
        sessvars.privilegios = [];
        sessvars.htmlMenu = null;
        sessvars.stringMenu = "";
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
    

});