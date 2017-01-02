investigacionApp.controller('SemestreController', function($log, $scope, ngToast, SemestreService, SharedService) {

    $scope.sharedService = SharedService;
    $scope.semestres = [];
    $scope.semestre = {};
    $scope.errorFecha = false;
    
    /********** Servicios Callback **********/
    
    // CallBack Get
    var getSemestreServiceSuccess = function(response){
    	$log.debug("Get Semestre - Success");
    	console.log("Respuesta :: ", response);
        $scope.semestre = response;
    };
    var getSemestreServiceError = function(response){
     	$log.debug("Get Semestre - Error");
        console.log("Respuesta :: ", response);
    };
    
    // CallBack Registrar
    var registrarSemestreSuccess = function(response){
    	$log.debug("Registrar Semestre - Success");
        console.log("Respuesta :: ", response);
    	$scope.getSemestresByPagina();
    	
        $scope.cancel();
        
        $("#popNuevoSemestre").modal('toggle');
    };
    var registrarSemestreError = function(response){
        $log.debug("Registrar Semestre - Error");
        console.log("Respuesta :: ", response);
        
        $scope.cancel();
    };
    
    // CallBack Registrar
    var updateSemestreSuccess = function(response){
    	$log.debug("Update Semestre - Success");
    	console.log("Respuesta :: ", response);
        $scope.getSemestresByPagina();
        
        $scope.cancel();
        
        $("#popUpdateSemestre").modal('toggle');
    };
    var updateSemestreError = function(response){
        $log.debug("Update Semestre - Error");
        console.log("Respuesta :: ", response);
        
        $scope.cancel();
    };
    
    // CallBack Delete
    var deleteSemestreSuccess = function(response){
    	$log.debug("DeleteSemestre - Success");
    	console.log("Respuesta :: ", response);
        $scope.getSemestresByPagina();
        
        $scope.cancel();

    };
    var deleteSemestreError = function(response){
        $log.debug("DeleteSemestre - Error");
    	console.log("Respuesta :: ", response);
        
        $scope.cancel();
    };

    /********** CRUD SEMESTRES ***********/

    $scope.getSemestres = function(){
      	SemestreService.getSemestres().then(getSemestreServiceSuccess, getSemestreServiceError);
    };

    $scope.registrarSemestre = function() {
        $scope.submitted = true;
        if($scope.formRegistroSemestre.$valid){
            $scope.semestre.suserCreacion = $scope.sharedService.nombreUsuario;
            $scope.semestre.suserModificacion = $scope.sharedService.nombreUsuario;
            $scope.semestre.sestado = 'A';
            $scope.semestre.dinicioSemestre = $scope.fechaInicio;
            $scope.semestre.dfinSemestre = $scope.fechaFin;
            SemestreService.registrarSemestre($scope.semestre).then(registrarSemestreSuccess, registrarSemestreError);
            openNotice('Registrado!','success');
        }else {
            console.log("No se registro Semestre :: ", $scope.semestre);
            openNotice('Error al registrar!','danger');
            $scope.cancel();
        }
    };

    // 
    $scope.updateSemestre = function(){
        $scope.submitted = true;
        if($scope.formRegistroSemestre.$valid){
            $scope.semestre.suserModificacion = $scope.sharedService.nombreUsuario;
            $scope.semestre.sestado = 'A';
            SemestreService.updateSemestre($scope.semestre).then(updateSemestreSuccess, updateSemestreError);
            openNotice('Actualizado!','success');
        }else {
            console.log("No se registro Semestre :: ", $scope.semestre);
            openNotice('Error al actualizar!','danger');
            $scope.cancel();
        }
        
    };
        
    $scope.deleteSemestre = function(semestre){
    	$scope.semestre = semestre;
        $scope.semestre.suserModificacion = $scope.sharedService.nombreUsuario;
    	SemestreService.deleteSemestre ($scope.semestre).then(deleteSemestreSuccess, deleteSemestreError);
    };
    
    $scope.update = function(semestre){
        angular.copy(semestre, $scope.semestre);
    	//$scope.semestre = semestre;
    };
    
    
    $scope.cancel = function(){
        $scope.semestre = {};
        $scope.fechaInicio = "";
        $scope.fechaFin = "";
        $scope.errorFecha = false;
    };
    
    $scope.changeFecha = function(){
        if($scope.fechaFin < $scope.fechaInicio){
            console.log("Error");
            $scope.errorFecha = true;
        } else {
            console.log("Success");
            $scope.errorFecha = false;
        }
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
        $scope.getSemestresByPagina();
    });
    
    /*********************************************/
    
    var getSemestresByPaginaSuccess = function(response){
        $log.debug("getSemestresByPagina - Success");
        console.log("Respuesta :: ", response);
        $scope.semestres = [];
        $scope.semestres = response.lista;
        $scope.total = response.total;
    };
    
    var getSemestresByPaginaError = function(response){
        $log.debug("getSemestresByPagina - Error");
        console.log("Respuesta :: ", response);
    };
    
    $scope.getSemestresByPagina = function(){
        var objPagina = { currentPage : $scope.currentPage, rango : $scope.currentRango, total : $scope.total, filtro : $scope.buscar};
        SemestreService.getSemestresByPagina(objPagina).then(getSemestresByPaginaSuccess, getSemestresByPaginaError);
    };
    
    $scope.clickBuscar = function(){
        $scope.getSemestresByPagina();
    };
    
    $scope.getSemestresByPagina();
    
    
    
    /********** DataPicker ************/
    
    $scope.today = function() {
        $scope.fechaRegistro = new Date();
    };
    $scope.today();

    $scope.inlineOptions = {
      customClass: getDayClass,
      minDate: new Date()
    };

    $scope.dateOptions = {
      dateDisabled: disabled,
      formatYear: "yy",
      maxDate: new Date(2020, 5, 22),
      minDate: new Date(),
      startingDay: 1,
      showWeeks:false,
      showButtonBar:false
    };

    // Disable weekend selection
    function disabled(data) {
      var date = data.date,
        mode = data.mode;
      return mode === 'day' && (date.getDay() === 0 || date.getDay() === 6);
    }

    $scope.toggleMin = function() {
      $scope.inlineOptions.minDate = $scope.inlineOptions.minDate ? null : new Date();
      $scope.dateOptions.minDate = $scope.inlineOptions.minDate;
    };

    $scope.toggleMin();

    $scope.open1 = function() {
      $scope.popup1.opened = true;
    };

    $scope.open2 = function() {
      $scope.popup2.opened = true;
    };

    $scope.setDate = function(year, month, day) {
      $scope.dtRegistro = new Date(year, month, day);
    };

    $scope.formats = ['dd/MMMM/yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
    $scope.format = $scope.formats[0];
    $scope.altInputFormats = ['M!/d!/yyyy'];

    $scope.popup1 = {
      opened: false
    };

    $scope.popup2 = {
      opened: false
    };
 

    function getDayClass(data) {
        var date = data.date;
        var mode = data.mode;
        if (mode === 'day') {
            var dayToCheck = new Date(date).setHours(0,0,0,0);
            for (var i = 0; i < $scope.events.length; i++) {
                var currentDay = new Date($scope.events[i].date).setHours(0,0,0,0);
                if (dayToCheck === currentDay) {
                    return $scope.events[i].status;
                }
            }
        }
        return '';
    }
    
});
