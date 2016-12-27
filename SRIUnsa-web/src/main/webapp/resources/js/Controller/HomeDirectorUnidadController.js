investigacionApp.controller('HomeDirectorUnidadController', function($log, $scope, UsuariosService, $location, 
    HomeVicerectorService, SharedService) {

    $scope.sharedService = SharedService;
    $scope.users = [];
    $scope.usuario = {};
    $scope.estados = ['A','I'];
    $scope.idTipoInvestigacion = 0;
    $scope.currentTipo = 0;
    
    var GetTotalActivosInactivosByFacultadSuccess = function(response){
        $log.debug("GetTotalActivosInactivosByFacultad - Success");
        console.log("Respuesta :: ", response);
        GetPorcentaje(response.body);
        $scope.docentes = response.body;
    };
    var GetTotalActivosInactivosByFacultadError = function(response){
        $log.debug("GetTotalActivosInactivosByFacultad - Error");
        console.log("Respuesta :: ", response.body);
    };
    
    var GetTotalActividadesByTipoActividadFacultadSuccess = function(response){
        $log.debug("GetTotalActividadesByTipoActividadFacultad - Success");
        console.log("Respuesta :: ", response);
        $scope.tipoInvestigaciones = response.body;
    };
    var GetTotalActividadesByTipoActividadFacultadError = function(response){
        $log.debug("GetTotalActividadesByTipoActividadFacultad - Error");
        console.log("Respuesta :: ", response);
    };
    
    var GetTotalActivosInactivosByDepartamentoSuccess = function(response){
        $log.debug("GetTotalActivosInactivosByDepartamento - Success");
        console.log("Respuesta :: ", response);
        $scope.docentesDepartamentos = response.body;
        angular.forEach($scope.docentesDepartamentos, function(value, key){
            value.porcentajeActivo = GetPorcentajeDepartamento(value.ntotal, value.nactivos);
            value.porcentajeInactivo = GetPorcentajeDepartamento(value.ntotal, value.ninactivos);
        });
    };
    var GetTotalActivosInactivosByDepartamentoError = function(response){
        $log.debug("GetTotalActivosInactivosByDepartamento - Error");
        console.log("Respuesta :: ", response);
    };
    
    
    $scope.GetTotalActivosInactivosByDepartamento = function(idFacultad, idTipoInvestigacion){
        HomeVicerectorService.GetTotalActivosInactivosByDepartamento(idFacultad, idTipoInvestigacion).then(GetTotalActivosInactivosByDepartamentoSuccess, GetTotalActivosInactivosByDepartamentoError);
    };
    $scope.GetTotalActivosInactivosByFacultad = function(idFacultad){
        UsuariosService.GetTotalActivosInactivosByFacultad(idFacultad).then(GetTotalActivosInactivosByFacultadSuccess, GetTotalActivosInactivosByFacultadError);
    };
        
    $scope.GetTotalActividadesByTipoActividadFacultad = function(idFacultad){
        HomeVicerectorService.GetTotalActividadesByTipoActividadFacultad(idFacultad).then(GetTotalActividadesByTipoActividadFacultadSuccess, GetTotalActividadesByTipoActividadFacultadError);
    };

    $scope.goHome = function(usuario){
        console.log("USUARIO :: ", usuario);
        sessvars.docente = usuario;
        sessvars.idDocente = usuario.nidUsuario;
        $scope.sharedService.idDocente = sessvars.idDocente;
        $scope.sharedService.docente = sessvars.docente;
        $location.path("/homedocente");
    };
    
    $scope.setTipoInvestigacion = function(tipoInvestigacion){
        $scope.currentTipo = tipoInvestigacion.nidTipoActividadInvestigacion;
        $scope.idTipoInvestigacion = tipoInvestigacion.nidTipoActividadInvestigacion;
        $scope.sharedService.tipoInvestigacion = tipoInvestigacion;
        $scope.getUsuariosByPagina();
    };
    
    var GetPorcentaje = function(docentes){
        var total = docentes.nactivos + docentes.ninactivos;
        $scope.activo = (docentes.nactivos * 100 / total) + "%";
        $scope.inactivo = (docentes.ninactivos * 100 / total) + "%";
    };
     var GetPorcentajeDepartamento = function(total, valor){
        var porcentaje = "50%";
        if(total !== 0 ){
            porcentaje = (valor * 100 / total) + "%";
        } else {
            
        }
        return porcentaje;
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
                          idFacultad : $scope.sharedService.usuario.nidEstructuraOrganizacion, idTipoInvestigacion : $scope.idTipoInvestigacion};
        UsuariosService.GetUsuariosColor(objPagina).then(paginacionUsuarioSuccess, paginacionUsuarioError);
    };
    
    $scope.clickBuscar = function(){
        $scope.getUsuariosByPagina();
    };
    
    $scope.getUsuariosByPagina();
    $scope.GetTotalActivosInactivosByDepartamento($scope.sharedService.usuario.nidEstructuraOrganizacion, 0);
    $scope.GetTotalActivosInactivosByFacultad($scope.sharedService.usuario.nidEstructuraOrganizacion);
    $scope.GetTotalActividadesByTipoActividadFacultad($scope.sharedService.usuario.nidEstructuraOrganizacion);
});