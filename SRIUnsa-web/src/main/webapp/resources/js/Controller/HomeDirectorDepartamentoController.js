investigacionApp.controller('HomeDirectorDepartamentoController',['$log', '$scope', 'UsuariosService', '$location', 
    'HomeVicerectorService', 'SharedService', 'HomeDirectorDepartamentoService', function($log, $scope, UsuariosService, $location, 
    HomeVicerectorService, SharedService, HomeDirectorDepartamentoService) {

    $scope.sharedService = SharedService;
    $scope.users = [];
    $scope.usuario = {};
    $scope.estados = ['A','I'];
    $scope.idTipoInvestigacion = 0;
    $scope.idDepartamento = 0;
    $scope.currentTipo = 0;
    $scope.currentDepartamento = false;
    
    var GetTotalActivosInactivosHomeDepartamentoSuccess = function(response){
        $log.debug("GetTotalActivosInactivosHomeDepartamento - Success");
        console.log("Respuesta :: ", response);
        GetPorcentaje(response.body[0]);
        $scope.docentes = response.body[0];
    };
    var GetTotalActivosInactivosHomeDepartamentoError = function(response){
        $log.debug("GetTotalActivosInactivosHomeDepartamento - Error");
        console.log("Respuesta :: ", response.body);
    };
    
    var GetTotalActividadesByTipoActividadDepartamentoSuccess = function(response){
        $log.debug("GetTotalActividadesByTipoActividadDepartamento - Success");
        console.log("Respuesta :: ", response);
        $scope.tipoInvestigaciones = response.body;
    };
    var GetTotalActividadesByTipoActividadDepartamentoError = function(response){
        $log.debug("GetTotalActividadesByTipoActividadDepartamento - Error");
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
    
    
    $scope.GetTotalActivosInactivosByDepartamento = function(idDepartamento, idTipoInvestigacion){
        HomeDirectorDepartamentoService.GetTotalActivosInactivosHomeDepartamento(idDepartamento, idTipoInvestigacion).then(GetTotalActivosInactivosByDepartamentoSuccess, GetTotalActivosInactivosByDepartamentoError);
    };
    $scope.GetTotalActivosInactivosHomeDepartamento = function(idDepartamento, idTipoInvestigacion){
        HomeDirectorDepartamentoService.GetTotalActivosInactivosHomeDepartamento(idDepartamento, idTipoInvestigacion).then(GetTotalActivosInactivosHomeDepartamentoSuccess, GetTotalActivosInactivosHomeDepartamentoError);
    };
        
    $scope.GetTotalActividadesByTipoActividadDepartamento = function(idDepartamento){
        HomeDirectorDepartamentoService.GetTotalActividadesByTipoActividadDepartamento(idDepartamento).then(GetTotalActividadesByTipoActividadDepartamentoSuccess, GetTotalActividadesByTipoActividadDepartamentoError);
    };

    $scope.registrar = function(usuario){
        console.log("USUARIO :: ", usuario);
        sessvars.docente = usuario;
        sessvars.idDocente = usuario.nidUsuario;
        $scope.sharedService.idDocente = sessvars.idDocente;
        $scope.sharedService.docente = sessvars.docente;
        
        /*
         * Asignamos el id del docente para registrar su actividad
         * 
         * */
        sessvars.idUsuarioRegistrar = sessvars.idDocente;
//        sessvars.idUsuarioDirector = $scope.sharedService.usuarioLogin.idUsuario;
        $scope.sharedService.idUsuarioRegistrar = sessvars.idUsuarioRegistrar;
//        $scope.sharedService.idUsuarioDirector = sessvars.idUsuarioDirector;
        
        
        $location.path("/homedocente");
    };
    
    $scope.setTipoInvestigacion = function(tipoInvestigacion){
        if($scope.currentTipo === tipoInvestigacion.nidTipoActividadInvestigacion){
            $scope.currentTipo = 0;
            $scope.idTipoInvestigacion = 0;
        } else {
            $scope.currentTipo = tipoInvestigacion.nidTipoActividadInvestigacion;
            $scope.idTipoInvestigacion = tipoInvestigacion.nidTipoActividadInvestigacion;
        }
        
        $scope.sharedService.tipoInvestigacion = tipoInvestigacion;
        $scope.getUsuariosByPagina();
        $scope.GetTotalActivosInactivosByDepartamento($scope.sharedService.usuarioLogin.idDepartamento, $scope.idTipoInvestigacion);
        
    };
    
    $scope.filtrarDepartamento = function(docenteDepartamento){
        if(docenteDepartamento.nidEstructuraOrganizacion === $scope.currentDepartamento){
            $scope.currentDepartamento = 0;
            $scope.idDepartamento = 0;
        } else {
            $scope.currentDepartamento = docenteDepartamento.nidEstructuraOrganizacion;
            $scope.idDepartamento = docenteDepartamento.nidEstructuraOrganizacion;
        }
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
                          idFacultad : $scope.sharedService.usuarioLogin.idFacultad, idDepartamento : $scope.sharedService.usuarioLogin.idDepartamento, 
                          idTipoInvestigacion : $scope.idTipoInvestigacion};
        UsuariosService.GetUsuariosColor(objPagina).then(paginacionUsuarioSuccess, paginacionUsuarioError);
    };
    
    $scope.clickBuscar = function(){
        $scope.getUsuariosByPagina();
    };
    
    $scope.getUsuariosByPagina();
    $scope.GetTotalActivosInactivosByDepartamento($scope.sharedService.usuarioLogin.idDepartamento, 0);
    $scope.GetTotalActivosInactivosHomeDepartamento($scope.sharedService.usuarioLogin.idDepartamento, 0);
    $scope.GetTotalActividadesByTipoActividadDepartamento($scope.sharedService.usuarioLogin.idDepartamento);
    
    /******************* EXPORTAR ARCHIVOS *****************/
    
    var descargarPDFSuccess = function (response){
        $log.debug("descargarPDF - Success");
        console.log("Respuesta :: ", response);
    };
    var descargarPDFError = function (response){
        $log.debug("descargarPDF - Error");
        console.log("Respuesta :: ", response);
    };
    
    var descargarExcelSuccess = function (response){
        $log.debug("descargarExcel - Success");
        console.log("Respuesta :: ", response);
    };
    var descargarExcelError = function (response){
        $log.debug("descargarExcel - Error");
        console.log("Respuesta :: ", response);
    };
    
    $scope.descargarPDF = function(){
        console.log("Empezando descarga de PDF...");
        var pagina = { currentPage : $scope.currentPage, rango : $scope.currentRango, total : $scope.total, filtro : $scope.buscar, 
                          idFacultad : $scope.sharedService.usuarioLogin.idFacultad, idDepartamento : $scope.idDepartamento, 
                          idTipoInvestigacion : $scope.idTipoInvestigacion};
        UsuariosService.descargarPDF(pagina).then(descargarPDFSuccess, descargarPDFError);
    };
    
    $scope.descargarExcel = function(){
        console.log("Empezando descarga de Excel...");
        var pagina = { currentPage : $scope.currentPage, rango : $scope.currentRango, total : $scope.total, filtro : $scope.buscar, 
                          idFacultad : $scope.sharedService.usuarioLogin.idFacultad, idDepartamento : $scope.idDepartamento, 
                          idTipoInvestigacion : $scope.idTipoInvestigacion};
        UsuariosService.descargarExcel(pagina).then(descargarExcelSuccess, descargarExcelError);
    };
    
}]);
