investigacionApp.controller('UsuarioFlujoController',['$log', '$scope', 'UsuarioFlujoService', 'FlujoActorService',
    'UsuariosService', 'RolService', 'UsuarioRolService', 'SharedService', 
function($log, $scope, UsuarioFlujoService, FlujoActorService,
    UsuariosService, RolService, UsuarioRolService, SharedService) {

    $scope.sharedService = SharedService;
    $scope.usuarioActores = [];
    $scope.usuarioActor = {};
    $scope.usuarios = [];    
    $scope.actores = [];
    $scope.actoresById = [];
    $scope.actorById = {};
    $scope.rolesById = [];
    $scope.rolById = {};
    
    $scope.listaActores = [];

    var registrarUsuarioActorSuccess = function(response){
    	$log.debug("Registrar Usuario Actor - Success");
        console.log("Respuesta :: ", response);
        $scope.getUsuarioFlujoByPagina();
        var idUsuario = $scope.usuario === undefined ? $scope.usuarioActor.nidUsuario : $scope.usuario.nidUsuario;
        UsuarioFlujoService.getUsuarioFlujoActorByIdUsuario(idUsuario).then(getUsuarioFlujoActorByIdUsuarioSuccess, getUsuarioFlujoActorByIdUsuarioError);
    };
    var registrarUsuarioActorError = function(response){
        $log.debug("Registrar Usuario Actor - Error");
        console.log("Respuesta :: ", response);
    };

    var actualizarUsuarioActorSuccess = function(response){
    	$log.debug("actualizarUsuarioActor - Success");
        console.log("Respuesta :: ", response);
    	$scope.getUsuarioFlujoByPagina();
    };
    var actualizarUsuarioActorError = function(response){
        $log.debug("actualizarUsuarioActor - Error");
        console.log("Respuesta :: ", response);
    };
    
    var getUsuariosServiceSuccess = function(response){
        $log.debug("GetUsuarios - Success");
        console.log("Respuesta :: ", response);
        $scope.usuarios = response;
    };
    var getUsuariosServiceError = function(response){
        $log.debug("GetUsuarios - Error");
        console.log("Respuesta :: ", response);
    };
    
    var getFlujoActoresSuccess = function(response){
        $log.debug("GetUsuariosActores - Success");
        console.log("Respuesta :: ", response);
        $scope.actores = response;
    };
    var getFlujoActoresError = function(response){
        $log.debug("GetUsuariosActores - Error");
        console.log("Respuesta :: ", response);
    };
    
    var getUsuarioFlujoActorByIdUsuarioSuccess = function(response){
        $log.debug("getUsuarioFlujoActorByIdUsuario - Success");
        console.log("Respuesta :: ", response);
        $scope.actoresById = [];
        $scope.actoresById = response;
        $scope.listaActores = response;
    };
    var getUsuarioFlujoActorByIdUsuarioError = function(response){
        $log.debug("getUsuarioFlujoActorByIdUsuario - Error");
        console.log("Response :: ", response);
    };
    
    var deleteUsuarioFlujoSuccess = function(response){
        $log.debug("deleteUsuarioFlujo - Success");
        console.log("Respuesta :: ", response);
        if(response === true){
            UsuarioFlujoService.getUsuarioFlujoActorByIdUsuario($scope.usuarioActor.nidUsuario).then(getUsuarioFlujoActorByIdUsuarioSuccess, getUsuarioFlujoActorByIdUsuarioError);
        }
    };
    var deleteUsuarioFlujoError = function(response){
        $log.debug("deleteUsuarioFlujo - Error");
        console.log("Respuesta :: ", response);
    };


    /********** SERVICE FUNCTION ***********/
    
    $scope.getUsuarios = function(){
        UsuariosService.getUsuarios().then(getUsuariosServiceSuccess, getUsuariosServiceError);
    };

    $scope.getUsuarioActores = function(){
      	FlujoActorService.getActores().then(getFlujoActoresSuccess, getFlujoActoresError);
    };

    $scope.registrarUsuarioActor = function(){
        var usuarioFlujos = [];
        angular.forEach($scope.listaActores, function(value, key){
            var usuarioFlujo = {
                nidFlujoActor : value.nidFlujoActor,
                nidUsuario : $scope.usuario === undefined ? $scope.usuarioActor.nidUsuario : $scope.usuario.nidUsuario,
                suserCreacion : $scope.sharedService.nombreUsuario,
                sestado : 'A'
            };
            usuarioFlujos.push(usuarioFlujo);
        });
        UsuarioFlujoService.registrarUsuarioActor(usuarioFlujos).then(registrarUsuarioActorSuccess, registrarUsuarioActorError);
    };
    
    $scope.actualizarUsuarioActor = function(){
        var usuarioFlujos = [];
        if($scope.listaActores.length !== 0){
            $scope.emptyActor = false;
            $('#popUpdateUsuarioActor').modal('hide');
            angular.forEach($scope.listaActores, function(value, key){
            var usuarioFlujo = {
                nidFlujoActor : value.nidFlujoActor,
                nidUsuario : $scope.usuario === undefined ? $scope.usuarioActor.nidUsuario : $scope.usuario.nidUsuario,
                suserCreacion : $scope.sharedService.nombreUsuario,
                sestado : 'A'
            };
            usuarioFlujos.push(usuarioFlujo);
        });
        UsuarioFlujoService.actualizarUsuarioActor(usuarioFlujos).then(actualizarUsuarioActorSuccess, actualizarUsuarioActorError);
        } else {
            $scope.emptyActor = true;
        }
    };
      
    $scope.deleteUsuarioFlujo = function(actorById) {
        console.log($scope.usuarioActor);
        console.log(actorById);
        var usuarioflujo = {nidUsuarioFlujo : actorById.nidUsuarioFlujo, nidFlujoActor: actorById.nidFlujoActor, nidUsuario : $scope.usuarioActor.nidUsuario};
        UsuarioFlujoService.deleteUsuarioFlujo(usuarioflujo).then(deleteUsuarioFlujoSuccess, deleteUsuarioFlujoError);  
    };
    
    $scope.agregarUsuarioActorLista = function(){
        $scope.listaActores.push($scope.actor);
        $scope.emptyActor = false;
    };
    
    $scope.eliminarUsuarioActorLista = function(actor){
        var indice = $scope.listaActores.indexOf(actor);
        $scope.listaActores.splice( indice, 1 );
        if($scope.listaActores.length === 0){
            $scope.emptyActor = true;
        }
    };

    $scope.update = function(usuarioActor){
    	$scope.usuarioActor = usuarioActor;
        UsuarioFlujoService.getUsuarioFlujoActorByIdUsuario(usuarioActor.nidUsuario).then(getUsuarioFlujoActorByIdUsuarioSuccess, getUsuarioFlujoActorByIdUsuarioError);
    };
    
    $scope.updatePaginacion = function() {
        $scope.actor = {};
        $scope.usuario = {};
        $scope.listaActores = [];
        $scope.getUsuarioFlujoByPagina();
    };
    $scope.close = function(){
        $scope.emptyActor = false;
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
    
    var getUsuarioFlujoByPaginaSuccess = function(response){
        $log.debug("Get Paginacion Usuarios Actores - Success");
        console.log("Respuesta :: ", response);
        $scope.usuarioActores = [];
        $scope.usuarioActores = response.lista;
        $scope.total = response.total;
    };
    
    var getUsuarioFlujoByPaginaError = function(response){
        $log.debug("Get Paginacion Usuario Actores - Error");
        console.log("Respuesta :: ", response);
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
}]);