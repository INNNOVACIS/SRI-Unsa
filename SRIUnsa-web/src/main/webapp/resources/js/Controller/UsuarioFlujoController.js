investigacionApp.controller('UsuarioFlujoController', function($log, $scope, UsuarioFlujoService, FlujoActorService,
    UsuariosService) {

    $scope.usuarioActores = [];
    $scope.usuarioActor = {};
    $scope.usuarios = [];    
    $scope.actores = [];
    $scope.actoresById = [];
    $scope.actorById = {};

    var registrarUsuarioActorSuccess = function(response){
    	$log.debug("Registrar Usuario Actor - Success");
    	$scope.usuarioActores.push($scope.usuarioActor);
        UsuarioFlujoService.getUsuarioFlujoByIdUsuario($scope.usuarioActor.nidUsuario).then(getUsuarioFlujoByIdUsuarioSuccess, getUsuarioFlujoByIdUsuarioError);
    };

    var registrarUsuarioActorError = function(response){
        $log.debug("Registrar Usuario Actor - Error :: ", response);
    };

    var updateUsuarioActorSuccess = function(response){
    	$log.debug("Update Usuario Actor - Success");
    	$scope.usuarioActor = response;
    };

    var updateUsuarioActorError = function(response){
        $log.debug("Update Usuario Actor - Error :: ", response);
    };

    var deleteUsuarioActorSuccess = function(response){
    	$log.debug("Delete Usuario Actor - Success");
    	$scope.usuarioActor = response;
    };

    var deleteUsuarioActorError = function(response){
        $log.debug("Delete Usuario Actor - Error :: ", response);
    };
    
    var getUsuariosServiceSuccess = function(response){
        $log.debug("GetUsuarios -Success");
        console.log("Response :: ", response);
        $scope.usuarios = response;
    };
    
    var getUsuariosServiceError = function(response){
        $log.debug("GetUsuarios -Error");
        console.log("Response :: ", response);
    };
    
    var getFlujoActoresSuccess = function(response){
        $log.debug("GetUsuariosActores -Success");
        console.log("Response :: ", response);
        $scope.actores = response;
    };
    
    var getFlujoActoresError = function(response){
        $log.debug("GetUsuariosActores -Error");
        console.log("Response :: ", response);
    };
    
    var getUsuarioFlujoByIdUsuarioSuccess = function(response){
        $log.debug("getUsuarioFlujoByIdUsuario - Success");
        console.log("Response :: ", response);
        $scope.actoresById = [];
        $scope.actoresById = response;
    };
    
    var getUsuarioFlujoByIdUsuarioError = function(response){
        $log.debug("getUsuarioFlujoByIdUsuario - Error");
        console.log("Response :: ", response);
    };

    /********** CRUD ROLES ***********/
    
    $scope.getUsuarios = function(){
        UsuariosService.getUsuarios().then(getUsuariosServiceSuccess, getUsuariosServiceError);
    };

    $scope.getUsuarioActores = function(){
      	FlujoActorService.getActores().then(getFlujoActoresSuccess, getFlujoActoresError);
    };

    $scope.registrarUsuarioActor = function(){
        var usuarioFlujo = {
            nidFlujoActor : $scope.actor.nidFlujoActor,
            nidUsuario : $scope.usuario.nidUsuario === undefined ? $scope.usuarioActor.nidUsuario : $scope.usuario.nidUsuario
        };
	UsuarioFlujoService.registrarUsuarioActor(usuarioFlujo).then(registrarUsuarioActorSuccess, registrarUsuarioActorError);
    };

    $scope.updateUsuarioActor = function(){
    	UsuarioFlujoService.updateUsuarioActor($scope.actor).then(updateUsuarioActorSuccess, updateUsuarioActorError);
    };

    $scope.deleteUsuarioActor = function(usuarioActor){
    	$scope.usuarioActor = usuarioActor;
    	UsuarioFlujoService.deleteRol($scope.usuarioActor).then(deleteUsuarioActorSuccess. deleteUsuarioActorError);
    };

    $scope.addFlujoActor = function(actor){
        
    };

    $scope.update = function(usuarioActor){
    	$scope.usuarioActor = usuarioActor;
        UsuarioFlujoService.getUsuarioFlujoByIdUsuario(usuarioActor.nidUsuario).then(getUsuarioFlujoByIdUsuarioSuccess, getUsuarioFlujoByIdUsuarioError);
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
        $scope.getUsuarioFlujoByPagina();
    });
    
    /*********************************************/
    
    var getUsuarioFlujoByPaginaSuccess = function(response){
        $log.debug("Get Paginacion Usuarios Actores - Success");
        $scope.usuarioActores = response.lista;
        $scope.total = response.total;
    };
    
    var getUsuarioFlujoByPaginaError = function(response){
        console.log("Get Paginacion Usuario Actores - Error :: ", response);
    };
    
    $scope.getUsuarioFlujoByPagina = function(){
        var objPagina = { currentPage : $scope.currentPage, rango : $scope.currentRango, total : $scope.total, filtro : $scope.buscar};
        UsuarioFlujoService.getUsuarioFlujoByPagina(objPagina).then(getUsuarioFlujoByPaginaSuccess, getUsuarioFlujoByPaginaError);
    };
    
    $scope.clickBuscar = function(){
        $scope.getUsuarioFlujoByPagina();
    };
    
    $scope.getUsuarioActores();
    $scope.getUsuarios();
    $scope.getUsuarioFlujoByPagina();
});