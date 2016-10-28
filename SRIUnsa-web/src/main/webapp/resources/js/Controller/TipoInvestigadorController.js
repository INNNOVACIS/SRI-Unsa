investigacionApp.controller('TipoInvestigadorController', function($log, $scope, $location, $rootScope, $filter, 
    TipoInvestigadorService, SharedService) {

    $scope.listarTipoInvestigador = [];
    $scope.tipoInvestigador = {};
	
    var getTipoInvestigadorServiceSuccess = function(response){
    	$log.debug("Get TipoInvestigador - Success");
    	console.log("Success :: ", response);
    	$scope.listarTipoInvestigador = response;
    }

    var getTipoInvestigadorServiceError = function(response){
     	$log.debug("Get TipoInvestigador - Error"); 
    }

    var registrarTipoInvestigadorSuccess = function(response){
    	$log.debug("Registrar TipoInvestigador - Success");
    	console.log("success :: ", response);
    	$scope.listarTipoInvestigador.push($scope.tipoInvestigador);
    	$scope.tipoInvestigador = {};
    }

    var registrarTipoInvestigadorError = function(response){

    }

    var updateTipoInvestigadorSuccess = function(response){
    	$log.debug("Update User - Success");
    	console.log("success :: ", response);
    	$scope.tipoInvestigador = response;
    }

    var updateTipoInvestigadorError = function(response){

    }

    var deleteTipoInvestigadorSuccess = function(response){
    	$log.debug("Delete User - Success");
    	console.log("success :: ", response);
    	$scope.tipoInvestigador = response;
    }

    var deleteTipoInvestigadorError = function(response){

    }

    /********** CRUD TIPO PRODUCCION ***********/

    $scope.getListaTipoInvestigador = function(){
      	TipoInvestigadorService.getListaTipoInvestigador().then(getTipoInvestigadorServiceSuccess, getTipoInvestigadorServiceError);
    }

    $scope.registrarTipoInvestigador = function(){
    	console.log("TipoInvestigador :: ", $scope.tipoInvestigador);
		TipoInvestigadorService.registrarTipoInvestigador($scope.tipoInvestigador).then(registrarTipoInvestigadorSuccess, registrarTipoInvestigadorError);
    }

    $scope.updateTipoInvestigador = function(){
    	
    	TipoInvestigadorService.updateTipoInvestigador($scope.tipoInvestigador).then(updateTipoInvestigadorSuccess, updateTipoInvestigadorError);
    }

    $scope.deleteTipoInvestigador = function(tipoInvestigador){
    	$scope.tipoInvestigador = tipoInvestigador;
    	TipoInvestigadorService.deleteTipoInvestigador($scope.tipoInvestigador).then(deleteTipoInvestigadorSuccess. deleteTipoInvestigadorError);
    }

    $scope.update = function(tipoInvestigador){
    	$scope.tipoInvestigador = tipoInvestigador;
    }

    $scope.getListaTipoInvestigador();
});