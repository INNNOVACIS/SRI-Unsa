investigacionApp.controller('HomeVicerectorController', function($log, $scope, UsuariosService, $location, 
    TipoInvestigacionService, SharedService, HomeVicerectorService) {

    $scope.sharedService = SharedService;
    $scope.users = [];
    $scope.usuario = {};
    $scope.estados = ['A','I'];
    $scope.idTipoInvestigacion = 0;
    $scope.currentTipo = 0;
    
    var GetTotalDocentesActivosInactivosSuccess = function(response){
        $log.debug("GetTotalDocentesActivosInactivos - Success");
        console.log("Respuesta :: ", response);
        GetPorcentaje(response.body);
        $scope.docentes = response.body;
    };
    var GetTotalDocentesActivosInactivosError = function(response){
        $log.debug("GetTotalDocentesActivosInactivos - Error");
        console.log("Respuesta :: ", response.body);
    };
    
    var GetTotalActividadesByTipoActividadSuccess = function(response){
        $log.debug("GetTotalActividadesByTipoActividad - Success");
        console.log("Respuesta :: ", response);
        $scope.tipoInvestigaciones = response.body;
    };
    var GetTotalActividadesByTipoActividadError = function(response){
        $log.debug("GetTotalActividadesByTipoActividad - Error");
        console.log("Respuesta :: ", response);
    };
    
    var GetActivosInactivosByFacultadSuccess = function(response){
        $log.debug("GetActivosInactivosByFacultad - Error");
        console.log("Respuesta :: ", response);
        $scope.docentesFacultades = response.body;
        angular.forEach($scope.docentesFacultades, function(value, key){
            value.porcentajeActivo = GetPorcentajeFacultad(value.ntotal, value.nactivos);
            value.porcentajeInactivo = GetPorcentajeFacultad(value.ntotal, value.ninactivos);
        });
    };
    var GetActivosInactivosByFacultadError = function(response){
        $log.debug("GetActivosInactivosByFacultad - Error");
        console.log("Respuesta :: ", response);
    };
    
    
    $scope.GetTotalActividadesByTipoActividad = function(){
        HomeVicerectorService.GetTotalActividadesByTipoActividad().then(GetTotalActividadesByTipoActividadSuccess, GetTotalActividadesByTipoActividadError);
    };
    $scope.GetActivosInactivosByFacultad = function(){
        HomeVicerectorService.GetActivosInactivosByFacultad().then(GetActivosInactivosByFacultadSuccess, GetActivosInactivosByFacultadError);
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
    
    var GetPorcentajeFacultad = function(total, valor){
        var porcentaje = "50%";
        if(total !== 0 ){
            porcentaje = (valor * 100 / total) + "%";
        }
        return porcentaje;
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
    $scope.GetTotalDocentesActivosInactivos();
    $scope.GetActivosInactivosByFacultad();
    $scope.GetTotalActividadesByTipoActividad();
});