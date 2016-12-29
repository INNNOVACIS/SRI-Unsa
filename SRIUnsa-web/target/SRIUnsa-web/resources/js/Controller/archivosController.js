investigacionApp.controller('archivosController', function($log, $scope, $location, $rootScope, $filter, 
    ArchivosService, SharedService) {

    $scope.sharedService = SharedService;
    $scope.files = [];
    $scope.file = {};
	
    var getArchivoServiceSuccess = function(response){
    	$log.debug("GetArchivo - Success");
        console.log("Respuesta :: ", response);
    	$scope.files = response;
        console.log("Success :: ", $scope.files);
    };

    var getArchivoServiceError = function(response){
     	$log.debug("GetArchivo - Error");
        console.log("Respuesta :: ", response);
    };
    
    var descargarArchivoSuccess = function(response){
        $log.debug("DescargarArchivo - Success");
        console.log("Respuesta :: ", response);
    };
    
    var descargarArchivoError = function(response){
        $log.debug("DescargarArchivo - Error");
        console.log("Respuesta :: ", response);
    };
    
    var subirArchivoSuccess = function(response){
        $log.debug("SubirArchivo - Success");
        console.log("Respuesta :: ", response);
        $scope.getArchivosByPagina();
    };
    
    var subirArchivoError = function(response){
        $log.debug("SubirArchivo - Error");
        console.log("Respuesta :: ", response);
    };

    /********** CRUD ARCHIVOS ***********/

    $scope.getArchivos = function(){
      	ArchivosService.getArchivos().then(getArchivoServiceSuccess, getArchivoServiceError);
    };

    $scope.updateArchivo = function(){
    	var file = $scope.fileDirectiva;
        console.log("archivo ======> ", file);
        var formData = new FormData();
        formData.append('file', file);
//        formData.append('idArchivo',$scope.file.idArchivo);

        ArchivosService.subirArchivo(formData).then(subirArchivoSuccess, subirArchivoError);
    };
    
    $scope.descargarArchivo = function(archivo){
        ArchivosService.descargarArchivo(archivo.id).then(descargarArchivoSuccess, descargarArchivoError);
    };
    
    $scope.copiarArchivo = function(file){
    	$scope.file = file;
    };

    // Funcion que limpia el modelo del Semestre, ya que este es usado tanto para crear como para actualizar
    $scope.cancel = function(){
        $scope.privilegio = {};
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
        $scope.getArchivosByPagina();
    });
    
    /*********************************************/
    
    var getArchivosByPaginaSuccess = function(response){
        $log.debug("getArchivosByPagina - Success");
        console.log("Respuesta :: ", response);
        $scope.files = [];
        $scope.files = response.lista;
        $scope.total = response.total;
    };
    
    var getArchivosByPaginaError = function(response){
        $log.debug("getArchivosByPagina - Error");
        console.log("Respuesta :: ", response);
    };
    
    $scope.getArchivosByPagina = function(){
        var objPagina = { currentPage : $scope.currentPage, rango : $scope.currentRango, total : $scope.total, filtro : $scope.buscar};
        ArchivosService.getArchivosByPagina(objPagina).then(getArchivosByPaginaSuccess, getArchivosByPaginaError);
    };
    
    $scope.clickBuscar = function(){
        $scope.getArchivosByPagina();
    };
    
    $scope.getArchivosByPagina();
});