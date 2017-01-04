investigacionApp.controller('TipoInvestigadorController',['$log', '$scope', 'ngToast', '$location', '$rootScope', '$filter', 
    'TipoInvestigadorService', 'SharedService',
function($log, $scope, ngToast, $location, $rootScope, $filter, 
    TipoInvestigadorService, SharedService) {

    $scope.tipoInvestigadores = [];
    $scope.sharedService = SharedService;

    var registrarTipoInvestigadorSuccess = function(response){
    	$log.debug("Registrar TipoInvestigador - Success");
    	console.log("Respuesta :: ", response);
    	$scope.getTipoInvestigadorByPagina();
        $scope.cancel();
        $("#popNuevoTipoInvestigador").modal('toggle');
    };

    var registrarTipoInvestigadorError = function(response){
        $log.debug("Registrar Tipo Investigador - Error");
        console.log("Respuesta :: ", response);
        $scope.cancel();
    };

    var updateTipoInvestigadorSuccess = function(response){
    	$log.debug("Update User - Success");
    	console.log("Respuesta :: ", response);
        $scope.getTipoInvestigadorByPagina();
        $scope.cancel();
        $("#popUpdateTipoInvestigador").modal('toggle');
    };

    var updateTipoInvestigadorError = function(response){
        $log.debug("Actualizar Tipo Investigador - Error");
        console.log("Respuesta :: ", response);
    };

    var deleteTipoInvestigadorSuccess = function(response){
    	$log.debug("Delete User - Success");
    	console.log("Respuesta :: ", response);
    	$scope.tipoInvestigador = response;
    };

    var deleteTipoInvestigadorError = function(response){
        $log.debug("Eliminar Tipo Investigador - Error");
        console.log("Respuesta :: ", response);
    };

    /********** CRUD TIPO PRODUCCION ***********/

    $scope.registrarTipoInvestigador = function(){
        $scope.submitted = true;
        if($scope.formRegistroTipoInvestigador.$valid){
            var tipoInvestigador = {                
                snombreTipoInvestigador : $scope.nombre,
                sestado : "A",
                suserCreacion : $scope.sharedService.nombreUsuario,
                suserModificacion : $scope.sharedService.nombreUsuario
            };
            TipoInvestigadorService.registrarTipoInvestigador(tipoInvestigador)
                .then(registrarTipoInvestigadorSuccess, registrarTipoInvestigadorError);
            openNotice('Registrado!','success');
        }else {
            console.log("No se registro Tipo Investigador :: ", $scope.semestre);
            openNotice('Error al registrar!','danger');
        }
    };

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
    };

    $scope.deleteTipoInvestigador = function(tipoInvestigador){
    	$scope.tipoInvestigador = tipoInvestigador;
    	TipoInvestigadorService.deleteTipoInvestigador($scope.tipoInvestigador)
            .then(deleteTipoInvestigadorSuccess, deleteTipoInvestigadorError);
    };
    

    $scope.update = function(tipoInvestigador){
//        angular.copy(tipoInvestigador, $scope.tipoInvestigador);
    	$scope.tipoInvestigador = tipoInvestigador;
    };
    
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
    
    
     /**************** PAGINACION *****************/
    
    $scope.rangoPaginas = [5,10,20,100];
    $scope.currentPage = 1;
    $scope.currentRango = $scope.rangoPaginas[0];
    $scope.maxSize = 5;
    $scope.total = 0;

    $scope.numPages = function () {
      return Math.ceil($scope.total / $scope.currentRango);
    };

    $scope.$watch('currentPage + currentRango', function() {
        $scope.getTipoInvestigadorByPagina();
    });
    
    var getTipoInvestigadorByPaginaSuccess = function(response){
        $log.debug("getTipoInvestigadorByPagina - Success");
        console.log("Respuesta :: ", response);
        $scope.tipoInvestigadores = [];
        $scope.tipoInvestigadores = response.lista;
        $scope.total = response.total;
    };
    
    var getTipoInvestigadorByPaginaError = function(response){
        $log.debug("getTipoInvestigadorByPagina - Error");
        console.log("Respuesta :: ", response);
    };
    
    $scope.getTipoInvestigadorByPagina = function(){
        var objPagina = { currentPage : $scope.currentPage, rango : $scope.currentRango, total : $scope.total, filtro : $scope.buscar};
        TipoInvestigadorService.getTipoInvestigadorByPagina(objPagina).then(getTipoInvestigadorByPaginaSuccess, getTipoInvestigadorByPaginaError);
    };
    
    $scope.clickBuscar = function(){
        $scope.getTipoInvestigadorByPagina();
    };
        
    $scope.getTipoInvestigadorByPagina();
}]);