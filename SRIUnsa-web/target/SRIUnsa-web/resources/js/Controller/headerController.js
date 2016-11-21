investigacionApp.controller('headerController', function($scope, $sce, $location, SharedService) {

    $scope.sharedService = SharedService;
    $scope.sharedService.htmlMenu = $sce.trustAsHtml($scope.sharedService.stringMenu);

    $scope.message = $scope.sharedService.nombreUsuario;

    $scope.logout = function(){
    	$scope.sharedService.userAutenticado = false;
        sessvars.autenticado = false;
        sessvars.nombreRol = "";
        sessvars.nombreUsuario = "";
        sessvars.idRol = "";
        sessvars.idUsuario = 0;
        sessvars.privilegios = [];
        sessvars.htmlMenu = null;
        sessvars.stringMenu = ""
    	$location.path("/");
    };

});