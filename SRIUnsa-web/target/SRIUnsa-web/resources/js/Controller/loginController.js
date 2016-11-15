investigacionApp.controller('loginController', function($scope, $rootScope, $location, SharedService, LoginService) {
    
    sessvars.autenticado = false;
    $scope.sharedService = SharedService;
    
    var loginServiceSuccess = function(response){
        console.log("autenticación success :: ", response);
        
        sessvars.susuarioLogin = response.susuarioLogin;
        sessvars.susuarioPassword = response.susuarioPassword;
        sessvars.nidUsuario = response.nidUsuario;
        sessvars.autenticado = true;
        
        $scope.sharedService.userAutenticado = sessvars.autenticado;
        $location.path("/home");
    };

    var loginServiceError = function(response){
        console.log("error");
        alert("Usuario no registrado");
    };

    $scope.login = function(){
        LoginService.Autenticar($scope.usuario).then(loginServiceSuccess, loginServiceError); ;
    };
});
