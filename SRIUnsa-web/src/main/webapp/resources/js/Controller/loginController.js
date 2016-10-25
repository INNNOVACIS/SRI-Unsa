investigacionApp.controller('loginController', function($scope, $rootScope, $location, SharedService, LoginService) {

    $scope.sharedService = SharedService;
    
    var loginServiceSuccess = function(response){
      $scope.sharedService.userAutenticado = true;
      $location.path("/home");
    }

    var loginServiceError = function(response){
      console.log("error");
      alert("Usuario no registrado");
    }

    $scope.login = function(){
      LoginService.sendCrendential($scope.persona).then(loginServiceSuccess, loginServiceError); ;
    }
});
