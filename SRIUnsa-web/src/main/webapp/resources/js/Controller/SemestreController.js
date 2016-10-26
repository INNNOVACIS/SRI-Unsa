investigacionApp.controller('SemestreController', function($log, $scope, $location, $rootScope, $filter, 
    SemestreService, SharedService) {

    $scope.semestres = [];
    $scope.semestre = {};
    $scope.paginas = [];
    
    $scope.paginacion = {
        total : 1000,
        paginaActual : 1,
        rango : 6,
        rangoPaginas: 10,
        data: [
            {id : 1, nombre : "Ali David", usuario : "Alicito", clave : "12345"},
            {id : 2, nombre : "Miluska A", usuario : "Alicito", clave : "12345"},
            {id : 3, nombre : "David Mal", usuario : "Alicito", clave : "12345"},
            {id : 4, nombre : "Monica Hu", usuario : "Alicito", clave : "12345"},
            {id : 5, nombre : "Nolberto ", usuario : "Alicito", clave : "12345"},
            {id : 6, nombre : "Andres Ca", usuario : "Alicito", clave : "12345"}
        ]
    };
    
    var getNumeroPaginas = function(total) {
        
        var paginas = [];
        
        for(var i = 0; i < $scope.paginacion.rangoPaginas; i++) {
            var pagina = {};
            pagina.activo = false;
            pagina.numero = $scope.paginacion.paginaActual + i;
            paginas.push(pagina);
        }
        return paginas;
    };
    
    $scope.changePagina = function (paginaActual) {
        if(($scope.paginas[$scope.paginas.length - 1].numero - paginaActual.numero) < 1 ){
            $scope.paginas = [];
            if(paginaActual.numero + $scope.paginacion.rangoPaginas > $scope.paginacion.total / $scope.paginacion.rangoPaginas){
                $scope.paginacion.paginaActual = ($scope.paginacion.total / $scope.paginacion.rangoPaginas) - $scope.paginacion.rangoPaginas;
            } else {
                $scope.paginacion.paginaActual = paginaActual.numero;
            }
            $scope.paginas = getNumeroPaginas($scope.paginacion.total);
        } else {
            if((paginaActual.numero - $scope.paginas[0].numero) < 1 ){
                $scope.paginas = [];
                if(paginaActual.numero - $scope.paginacion.rangoPaginas <= 0){
                    $scope.paginacion.paginaActual = 1;
                } else {
                    $scope.paginacion.paginaActual = paginaActual.numero - $scope.paginacion.rangoPaginas;
                }
                $scope.paginas = getNumeroPaginas($scope.paginacion.total);
            }
        }
        angular.forEach($scope.paginas, function(value, key){
            if(value.numero == paginaActual.numero)
                value.activo = true;
            else
                value.activo = false;
        });
        console.log("mandamos la pagina actual :: ", paginaActual);
    };
	
    /********** Servicios Callback **********/
        
    var getSemestreServiceSuccess = function(response){
    	$log.debug("Get Semestre - Success");
    	$scope.semestres = response;
        $scope.paginas = getNumeroPaginas($scope.paginacion.total);
    };

    var getSemestreServiceError = function(response){
     	$log.debug("Get Semestre - Error"); 
    };

    var registrarSemestreSuccess = function(response){
        
    	$log.debug("Registrar Semestre - Success");
    	$scope.semestres.push($scope.semestre);
    	$scope.semestre = {};
    };

    var registrarSemestreError = function(response){
        $log.debug("Registrar Semestre - Error");
    };

    var updateSemestreSuccess = function(response){
    	$log.debug("Update Semestre - Success");
    	console.log("success :: ", response);
    	$scope.semestre = response;
    };

    var updateSemestreError = function(response){
        $log.debug("Update Semestre - Error");
    };

    var deleteSemestreSuccess = function(response){
    	$log.debug("Delete User - Success");
    	console.log("success :: ", response);
    	$scope.semestre = response;
    };

    var deleteSemestreError = function(response){

    };

    /********** CRUD SEMESTRES ***********/

    $scope.getSemestres = function(){
      	SemestreService.getSemestres().then(getSemestreServiceSuccess, getSemestreServiceError);
    };

    $scope.registrarSemestre = function(){
	SemestreService.registrarSemestre($scope.semestre).then(registrarSemestreSuccess, registrarSemestreError);
    };

    $scope.updateSemestre = function(){
    	SemestreService.updateSemestre($scope.semestre).then(updateSemestreSuccess, updateSemestreError);
    };

    $scope.deleteSemestre = function(semestre){
    	$scope.semestre = semestre;
    	SemestreService.deleteSemestre ($scope.semestre).then(deleteSemestreSuccess. deleteSemestreError);
    };

    $scope.update = function(semestre){
    	$scope.semestre = semestre;
    };

    $scope.getSemestres();
});