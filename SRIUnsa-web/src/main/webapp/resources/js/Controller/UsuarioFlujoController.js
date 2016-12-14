investigacionApp.controller('UsuarioFlujoController', function($log, $scope, UsuarioFlujoService, FlujoActorService,
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
        UsuarioFlujoService.getUsuarioFlujoActorByIdUsuario($scope.usuario.nidUsuario).then(getUsuarioFlujoActorByIdUsuarioSuccess, getUsuarioFlujoActorByIdUsuarioError);
    };
    var registrarUsuarioActorError = function(response){
        $log.debug("Registrar Usuario Actor - Error");
        console.log("Respuesta :: ", response);
    };
    
    var registrarUsuarioRolSuccess = function(response){
        $log.debug("RegistrarUsuarioRol - Success");
        console.log("Respuesta :: ", response);
        $scope.getUsuarioFlujoByPagina();
        UsuarioRolService.getUsuarioRolByIdUsuario($scope.usuario.nidUsuario).then(getUsuarioRolByIdUsuarioSuccess, getUsuarioRolByIdUsuarioError);
    };
    var registrarUsuarioRolError = function(response){
        $log.debug("RegistrarUsuarioRol - Error");
        console.log("Respuesta :: ", response);
    };
    
    var deleteUsuarioRolSuccess = function(response){
        $log.debug("deleteUsuarioRol - Success");
        console.log("Respuesta :: ", response);
        if(response === true){
            UsuarioRolService.getUsuarioRolByIdUsuario($scope.usuarioActor.nidUsuario).then(getUsuarioRolByIdUsuarioSuccess, getUsuarioRolByIdUsuarioError);
        }
    };
    var deleteUsuarioRolError = function(response){
        $log.debug("deleteUsuarioRol - Error");
        console.log("Respuesta :: ", response);
    };

    var updateUsuarioActorSuccess = function(response){
    	$log.debug("Update Usuario Actor - Success");
        console.log("Respuesta :: ", response);
    	$scope.usuarioActor = response;
    	$scope.getUsuarioFlujoByPagina();
    };
    var updateUsuarioActorError = function(response){
        $log.debug("Update Usuario Actor - Error");
        console.log("Respuesta :: ", response);
    };

    var deleteUsuarioActorSuccess = function(response){
    	$log.debug("Delete Usuario Actor - Success");
    	$scope.usuarioActor = response;
        $scope.getUsuarioFlujoByPagina();
        UsuarioFlujoService.getUsuarioFlujoActorByIdUsuario($scope.usuario.nidUsuario).then(getUsuarioFlujoActorByIdUsuarioSuccess, getUsuarioFlujoActorByIdUsuarioError);
    };
    var deleteUsuarioActorError = function(response){
        $log.debug("Delete Usuario Actor - Error");
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
    };
    var getUsuarioFlujoActorByIdUsuarioError = function(response){
        $log.debug("getUsuarioFlujoActorByIdUsuario - Error");
        console.log("Response :: ", response);
    };
    
    var getUsuarioRolByIdUsuarioSuccess = function(response) {
        $log.debug("getUsuarioRolByIdUsuario - Success");
        console.log("Respuesta :: ", response);
        $scope.rolesById = [];
        $scope.rolesById = response;
    };
    var getUsuarioRolByIdUsuarioError = function(response) {
        $log.debug("getUsuarioRolByIdUsuario - Error");
        console.log("Respuesta :: ", response);
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
    
    var getRolServiceSuccess = function(response){
        console.log("GetRol Success :: ", response);
        console.log("Respuesta :: ", response);
        $scope.roles = response;
    };
    var getRolServiceError = function(response){
        $log.debug("GetRol - Error");
        console.log("Respuesta :: ", response);
    };


    /********** CRUD ROLES ***********/
    
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
    
    $scope.agregarUsuarioActorLista = function(){
        $scope.listaActores.push($scope.actor);
        console.log("Agregar :: ", $scope.listaActores);
    };
    
    $scope.eliminarUsuarioActorLista = function(actor){
        var indice = $scope.listaActores.indexOf(actor);
        $scope.listaActores.splice( indice, 1 );
        console.log("Eliminar :: ", $scope.listaActores);
    };
    
    $scope.registrarUsuarioRol = function(){
        var usuarioRol = {
            nidRol : $scope.rol.nidRol,
            nidUsuario : $scope.usuario === undefined ? $scope.usuarioActor.nidUsuario : $scope.usuario.nidUsuario,
            suserCreacion : $scope.sharedService.nombreUsuario,
            sestado : 'A'
        };
        UsuarioRolService.registrarUsuarioRol(usuarioRol).then(registrarUsuarioRolSuccess, registrarUsuarioRolError);
    };

    $scope.updateUsuarioActor = function(){
        $scope.actor.suserModificacion = $scope.sharedService.nombreUsuario;
        $scope.actor.sestado = 'A';
    	UsuarioFlujoService.updateUsuarioActor($scope.actor).then(updateUsuarioActorSuccess, updateUsuarioActorError);
    };

    $scope.deleteUsuarioActor = function(usuarioActor){
    	$scope.usuarioActor = usuarioActor;
        $scope.actor.suserModificacion = $scope.sharedService.nombreUsuario;
    	UsuarioFlujoService.deleteRol($scope.usuarioActor).then(deleteUsuarioActorSuccess, deleteUsuarioActorError);
    };

    $scope.deleteUsuarioFlujo = function(actorById) {
        console.log($scope.usuarioActor);
        console.log(actorById);
        var usuarioflujo = {nidUsuarioFlujo : actorById.nidUsuarioFlujo, nidFlujoActor: actorById.nidFlujoActor, nidUsuario : $scope.usuarioActor.nidUsuario};
        UsuarioFlujoService.deleteUsuarioFlujo(usuarioflujo).then(deleteUsuarioFlujoSuccess, deleteUsuarioFlujoError);  
    };
    
    $scope.deleteUsuarioRol = function(rolById) {
        console.log(rolById);
        var usuarioRol = {nidUsuarioRol : rolById.nidUsuarioRol};
        UsuarioRolService.deleteUsuarioRol(usuarioRol).then(deleteUsuarioRolSuccess, deleteUsuarioRolError);
    };

    $scope.getRoles = function(){
      	RolService.getRoles().then(getRolServiceSuccess, getRolServiceError);
    };

    $scope.update = function(usuarioActor){
    	$scope.usuarioActor = usuarioActor;
        UsuarioRolService.getUsuarioRolByIdUsuario(usuarioActor.nidUsuario).then(getUsuarioRolByIdUsuarioSuccess, getUsuarioRolByIdUsuarioError);
        UsuarioFlujoService.getUsuarioFlujoActorByIdUsuario(usuarioActor.nidUsuario).then(getUsuarioFlujoActorByIdUsuarioSuccess, getUsuarioFlujoActorByIdUsuarioError);
    };
    
    $scope.updatePaginacion = function() {
        $scope.getUsuarioFlujoByPagina();
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
    
    $scope.getRoles();
    $scope.getUsuarioActores();
    $scope.getUsuarios();
    $scope.getUsuarioFlujoByPagina();
});