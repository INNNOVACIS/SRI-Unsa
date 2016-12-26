investigacionApp.controller('HomeVicerectorController', function($log, $scope, UsuariosService, $location, 
    TipoInvestigacionService, SharedService) {

    $scope.sharedService = SharedService;
    $scope.users = [];
    $scope.usuario = {};
    $scope.estados = ['A','I'];
    $scope.idTipoInvestigacion = 0;
    $scope.currentTipo = 0;
    
    var GetTipoInvestigacionesSuccess = function(response){
        $log.debug("GetTipoInvestigaciones - Success");
        console.log("Respuesta :: ", response);
        $scope.tipoInvestigaciones = response;
        $scope.GetTotalDocentesByTipoActividad();
    };
    var GetTipoInvestigacionesError = function(response){
        $log.debug("GetTipoInvestigaciones - Error");
        console.log("Respuesta :: ", response);
    };
    
    var GetTotalDocentesActivosInactivosSuccess = function(response){
        $log.debug("GetTotalDocentesActivosInactivos - Success");
        console.log("Respuesta :: ", response);
        GetPorcentaje(response.body);
        $scope.docentes = response.body;
    };
    var GetTotalDocentesActivosInactivosError = function(response){
        $log.debug("GetTotalDocentesActivosInactivos - Error");
        console.log("Respuesta :: ", response);
    };
    
    var GetTotalDocentesByTipoActividadSuccess = function(response){
        $log.debug("GetTotalDocentesByTipoActividad - Success");
        console.log("Respuesta :: ", response);
        angular.forEach($scope.tipoInvestigaciones, function(tipoActividad, key){
            tipoActividad.total = 0;
            angular.forEach(response.lista, function(actividad, keyActividades){
                if(tipoActividad.nidTipoActividadInvestigacion === actividad.nidTipoActividadInvestigacion ){
                    tipoActividad.total = tipoActividad.total + 1;
                }//
            });
        });
        
    };
    var GetTotalDocentesByTipoActividadError = function(response){
        $log.debug("GetTotalDocentesByTipoActividad - Error");
        console.log("Respuesta :: ", response);
    };
    
    
    $scope.GetTotalDocentesByTipoActividad = function(){
        angular.forEach($scope.tipoInvestigaciones, function(value, key){
            var objPagina = { currentPage : $scope.currentPage, rango : $scope.currentRango, total : $scope.total, filtro : $scope.buscar, 
                          idFacultad : 0, idTipoInvestigacion : value.nidTipoActividadInvestigacion}; //para la facultad $scope.sharedService.usuario.nidEstructuraOrganizacion
            UsuariosService.GetUsuariosColor(objPagina).then(GetTotalDocentesByTipoActividadSuccess, GetTotalDocentesByTipoActividadError);
        });
    };
    
    $scope.GetTipoInvestigaciones = function(){
        TipoInvestigacionService.getInvestigaciones().then(GetTipoInvestigacionesSuccess, GetTipoInvestigacionesError);
    };
    $scope.goHome = function(usuario){
        console.log("USUARIO :: ", usuario);
        $scope.sharedService.idDocente = usuario.nidUsuario;
        $location.path("/home");
    };
    $scope.setTipoInvestigacion = function(tipoInvestigacion){
        $scope.currentTipo = tipoInvestigacion.nidTipoActividadInvestigacion;
        $scope.idTipoInvestigacion = tipoInvestigacion.nidTipoActividadInvestigacion;
        $scope.sharedService.tipoInvestigacion = tipoInvestigacion;
        $scope.getUsuariosByPagina();
    };
    $scope.GetTotalDocentesActivosInactivos = function(){
        UsuariosService.GetTotalDocentesActivosInactivos().then(GetTotalDocentesActivosInactivosSuccess, GetTotalDocentesActivosInactivosError);
    };
    
    var GetPorcentaje = function(docentes){
        var total = docentes.nactivos + docentes.ninactivos;
        $scope.activo = (docentes.nactivos * 100 / total) + "%";
        $scope.inactivo = (docentes.ninactivos * 100 / total) + "%";
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
        $scope.getUsuariosByPagina();
        $scope.row = ($scope.currentPage - 1) * $scope.currentRango + 1;
        console.log("FILA-INICIO :: ", $scope.idFila);
    });
    
    var paginacionUsuarioSuccess = function(response){
        $log.debug("Get paginacionUsuario - Success");
        console.log("Respuesta :: ", response);
        $scope.users = [];
        $scope.users = response.lista;
        $scope.total = response.total;
    };
    
    var paginacionUsuarioError = function(response){
        $log.debug("Get paginacionUsuario - Error");
        console.log("Respuesta :: ", response);
    };
    
    $scope.getUsuariosByPagina = function(){
        var objPagina = { currentPage : $scope.currentPage, rango : $scope.currentRango, total : $scope.total, filtro : $scope.buscar, 
                          idFacultad : 0, idTipoInvestigacion : $scope.idTipoInvestigacion}; //para la facultad $scope.sharedService.usuario.nidEstructuraOrganizacion
        UsuariosService.GetUsuariosColor(objPagina).then(paginacionUsuarioSuccess, paginacionUsuarioError);
    };
    
    $scope.clickBuscar = function(){
        $scope.getUsuariosByPagina();
    };
    
    $scope.getUsuariosByPagina();
    $scope.GetTipoInvestigaciones();
    $scope.GetTotalDocentesActivosInactivos();
});