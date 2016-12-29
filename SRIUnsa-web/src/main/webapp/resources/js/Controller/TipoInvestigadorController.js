investigacionApp.controller('TipoInvestigadorController', function($log, $scope, ngToast, $location, $rootScope, $filter, 
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
    	
        $scope.cancel();
        
        $("#popNuevoTipoInvestigador").modal('toggle');
    }

    var registrarTipoInvestigadorError = function(response){
        $log.debug("Registrar Tipo Investigador - Error");
        console.log("Respuesta :: ", response);
        $scope.cancel();
    }

    var updateTipoInvestigadorSuccess = function(response){
    	$log.debug("Update User - Success");
    	console.log("success :: ", response);
        
        $scope.cancel();
        
        $("#popUpdateTipoInvestigador").modal('toggle');
    }

    var updateTipoInvestigadorError = function(response){
        $log.debug("Actualizar Tipo Investigador - Error");
        console.log("Respuesta :: ", response);
    }

    var deleteTipoInvestigadorSuccess = function(response){
    	$log.debug("Delete User - Success");
    	console.log("success :: ", response);
    	$scope.tipoInvestigador = response;
    }

    var deleteTipoInvestigadorError = function(response){
        $log.debug("Eliminar Tipo Investigador - Error");
        console.log("Respuesta :: ", response);
    }

    /********** CRUD TIPO PRODUCCION ***********/

    $scope.getListaTipoInvestigador = function(){
      	TipoInvestigadorService.getListaTipoInvestigador().then(getTipoInvestigadorServiceSuccess, getTipoInvestigadorServiceError);
    }

    $scope.registrarTipoInvestigador = function(){
        $scope.submitted = true;
        if($scope.formRegistroTipoInvestigador.$valid){
            console.log("TipoInvestigador Controller :: ", $scope.tipoInvestigador);
            TipoInvestigadorService.registrarTipoInvestigador($scope.tipoInvestigador)
                .then(registrarTipoInvestigadorSuccess, registrarTipoInvestigadorError);
            openNotice('Registrado!','success');
        }else {
            console.log("No se registro Semestre :: ", $scope.semestre);
            openNotice('Error al registrar!','danger');
        }
    }

    $scope.updateTipoInvestigador = function(){
    	$scope.submitted = true;
        if($scope.formUpdateTipoInvestigador.$valid){
            TipoInvestigadorService.updateTipoInvestigador($scope.tipoInvestigador)
                .then(updateTipoInvestigadorSuccess, updateTipoInvestigadorError);
            openNotice('Actualizado!','success');
        }else {
            console.log("No se registro Tipo Investigador :: ", $scope.semestre);
            openNotice('Error al actualizar!','danger');
        }
    }

    $scope.deleteTipoInvestigador = function(tipoInvestigador){
    	$scope.tipoInvestigador = tipoInvestigador;
    	TipoInvestigadorService.deleteTipoInvestigador($scope.tipoInvestigador).then(deleteTipoInvestigadorSuccess. deleteTipoInvestigadorError);
    }
    

    $scope.update = function(tipoInvestigador){
        angular.copy(tipoInvestigador, $scope.tipoInvestigador);
    	//$scope.tipoInvestigador = tipoInvestigador;
    }
    
    // Funcion que limpia el modelo del Semestre, ya que este es usado tanto para crear como para actualizar
    $scope.cancel = function(){
        $scope.tipoInvestigador = {};
    };
    
    /**************** NOTIFICACIONES *****************/
    var openNotice = function (text, type) {
        ngToast.create({
            className: type,
            content: '<span class="alert-link">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' + text +
                    '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>'
        });
    };
    
    $scope.getListaTipoInvestigador();
});