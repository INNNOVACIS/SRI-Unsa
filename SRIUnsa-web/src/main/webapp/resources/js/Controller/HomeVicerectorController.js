investigacionApp.controller('HomeVicerectorController',['$log', '$scope', 'UsuariosService', '$location', 
    'SharedService', 'HomeVicerectorService', '$localStorage', 'SemestreService',function($log, $scope, UsuariosService, $location, 
    SharedService, HomeVicerectorService, $localStorage, SemestreService) {

    $scope.sharedService = SharedService;
    $scope.users = [];
    $scope.usuario = {};
    $scope.estados = ['A','I'];
    $scope.idTipoInvestigacion = 0;
    $scope.idFacultad = 0;
    $scope.idDepartamento = 0;
    $scope.currentTipo = 0;
    $scope.currentFacultad = false;
    $scope.loader = false;
    
    var getSemestreServiceSuccess = function(response){
    	$log.debug("GetSemestre - Success");
        console.log("Respuesta :: ", response);
    	$scope.semestres = response;
        $scope.semestre = verificarSemestre($scope.semestres);
    };
    var getSemestreServiceError = function(response){
     	$log.debug("GetSemestre - Error");
        console.log("Respuesta :: ", response);
    };
    
    $scope.getSemestres = function(){
      	SemestreService.getSemestres().then(getSemestreServiceSuccess, getSemestreServiceError);
    };
    
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
        $log.debug("GetActivosInactivosByFacultad - Success");
        console.log("Respuesta :: ", response);
        $scope.docentesFacultades = [];
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
    
    
    $scope.GetTotalActividadesByTipoActividad = function(idSemestre){
        HomeVicerectorService.GetTotalActividadesByTipoActividad(idSemestre).then(GetTotalActividadesByTipoActividadSuccess, GetTotalActividadesByTipoActividadError);
    };
    
    $scope.GetActivosInactivosByFacultad = function(idTipoInvestigacion, idSemestre){
        HomeVicerectorService.GetActivosInactivosByFacultad(idTipoInvestigacion, idSemestre).then(GetActivosInactivosByFacultadSuccess, GetActivosInactivosByFacultadError);
    };
    
    $scope.verDetalleDocente = function(usuario){
        console.log("USUARIO :: ", usuario);
        $localStorage.docente = usuario;
        $scope.sharedService.docente = $localStorage.docente;
        
        $location.path("/actividadesDocente");
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
        $scope.GetActivosInactivosByFacultad($scope.idTipoInvestigacion);
    };
    
    $scope.filtrarFacultad = function(docenteFacultad){
        if(docenteFacultad.nidEstructuraOrganizacion === $scope.currentFacultad){
            $scope.currentFacultad = 0;
            $scope.idFacultad = 0;
        } else {
            $scope.currentFacultad = docenteFacultad.nidEstructuraOrganizacion;
            $scope.idFacultad = docenteFacultad.nidEstructuraOrganizacion;
        }
        $scope.getUsuariosByPagina();
    };
    
    $scope.GetTotalDocentesActivosInactivos = function(semestre){
        UsuariosService.GetTotalDocentesActivosInactivos(semestre).then(GetTotalDocentesActivosInactivosSuccess, GetTotalDocentesActivosInactivosError);
    };
        
    $scope.clickBuscar = function(){
        $scope.getUsuariosByPagina();
    };
   
    
    var GetPorcentajeFacultad = function(total, valor){
        var porcentaje = "50%";
        if(total !== 0 ){
            porcentaje = (valor * 100 / total) + "%";
        } else {
            
        }
        return porcentaje;
    };
    
    var GetPorcentaje = function(docentes){
        var total = docentes.nactivos + docentes.ninactivos;
        $scope.activo = (docentes.nactivos * 100 / total) + "%";
        $scope.inactivo = (docentes.ninactivos * 100 / total) + "%";
    };
    
    var verificarSemestre = function(semestres){
        var currentDate = new Date();
        var semestre = {};
        angular.forEach(semestres, function(value, key){
            if(value.dinicioSemestre < currentDate && value.dfinSemestre > currentDate){
                semestre = value;
            }
        });
        return semestre;
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
        $scope.loader = false;
    };
    
    var paginacionUsuarioError = function(response){
        $log.debug("Get paginacionUsuario - Error");
        console.log("Respuesta :: ", response);
        $scope.loader = false;
    };
    
    $scope.getUsuariosByPagina = function(){
        $scope.loader = true;
        var objPagina = { 
                currentPage : $scope.currentPage, 
                rango : $scope.currentRango, 
                total : $scope.total, 
                filtro : $scope.buscar, 
                idFacultad : $scope.idFacultad, 
                idDepartamento : $scope.idDepartamento, 
                idTipoInvestigacion : $scope.idTipoInvestigacion,
                idSemestre: $scope.sharedService.idSemestreActual
            }; //para la facultad $scope.sharedService.usuario.nidEstructuraOrganizacion
        UsuariosService.GetUsuariosColor(objPagina).then(paginacionUsuarioSuccess, paginacionUsuarioError);
    };
    
    $scope.changeSemestre = function(semestre){
        $scope.semestre = semestre;
        $scope.GetTotalDocentesActivosInactivos($scope.semestre.nidSemestre);
        $scope.GetTotalActividadesByTipoActividad($scope.semestre.nidSemestre);
        $scope.GetActivosInactivosByFacultad($scope.idTipoInvestigacion, $scope.semestre.nidSemestre);
        
         $scope.currentPage = 1;
        $scope.currentRango = $scope.rangoPaginas[0];
        $scope.getUsuariosByPagina();
    };
     
    $scope.GetTotalDocentesActivosInactivos($scope.sharedService.idSemestreActual);
    $scope.GetTotalActividadesByTipoActividad($scope.sharedService.idSemestreActual);
    $scope.GetActivosInactivosByFacultad($scope.idTipoInvestigacion, $scope.sharedService.idSemestreActual); //filtrar por actividad no filtra tipoActividad = 0
    $scope.getUsuariosByPagina();
    $scope.getSemestres();
    
    
    
    /******************* EXPORTAR ARCHIVOS *****************/
    
    var descargarPDFSuccess = function (response){
        $log.debug("descargarPDF - Success");
        console.log("Respuesta :: ", response);
        $scope.loader = false;
    };
    var descargarPDFError = function (response){
        $log.debug("descargarPDF - Error");
        console.log("Respuesta :: ", response);
        $scope.loader = false;
    };
    
    var descargarExcelSuccess = function (response){
        $log.debug("descargarExcel - Success");
        console.log("Respuesta :: ", response);
        $scope.loader = false;
    };
    var descargarExcelError = function (response){
        $log.debug("descargarExcel - Error");
        console.log("Respuesta :: ", response);
        $scope.loader = false;
    };
    
    $scope.descargarPDF = function(){
        $scope.loader = true;
        $scope.sharedService.scrollTop();
        var pagina = 
            { 
                currentPage : $scope.currentPage, 
                rango : $scope.currentRango, 
                total : $scope.total, 
                filtro : $scope.buscar, 
                idFacultad : $scope.idFacultad, 
                idDepartamento : $scope.idDepartamento, 
                idTipoInvestigacion : $scope.idTipoInvestigacion,
                idSemestre: $scope.semestre.nidSemestre
            };
        UsuariosService.descargarPDF(pagina).then(descargarPDFSuccess, descargarPDFError);
    };
    
    $scope.descargarExcel = function(){
        $scope.loader = true;
        $scope.sharedService.scrollTop();
        var pagina = { 
                currentPage : $scope.currentPage, 
                rango : $scope.currentRango, 
                total : $scope.total, 
                filtro : $scope.buscar, 
                idFacultad : $scope.idFacultad, 
                idDepartamento : $scope.idDepartamento, 
                idTipoInvestigacion : $scope.idTipoInvestigacion,
                idSemestre: $scope.semestre.nidSemestre
            };
        UsuariosService.descargarExcel(pagina).then(descargarExcelSuccess, descargarExcelError);
    };
}]);