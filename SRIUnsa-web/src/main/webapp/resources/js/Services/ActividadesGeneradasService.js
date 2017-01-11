/**
 * Actividades Generadas Service
 */
investigacionApp.service("ActividadesGeneradasService", function(SRIUnsaConfig, $log, $http, $q) {
	
    this.getAllActividadesGeneradas = function() {
        $log.debug("Actividades Generadas Service - getAllActividadesGeneradas");

        var deferred = $q.defer();
        $http({
            method : 'GET',
            //url : SRIUnsaConfig.SRIUnsaUrlServicio + '/actividadInvestigacionGenerada/listarActividadGenerada',
            url : 'generadorpdfs/descargarPdf',
        }).success(function(response) {
            deferred.resolve(response);
        }).error(function(response) {			
            deferred.reject(response);
        });
        return deferred.promise;
    };
    
    this.GetActividadesGeneradas = function(request) {
        $log.debug("ActividadesGeneradasService - GetActividadesGeneradas");

        var deferred = $q.defer();
        $http({
            method : 'POST',
            url : SRIUnsaConfig.SRIUnsaUrlServicio + '/actividadInvestigacionGenerada/actividades',
            data : request
        }).success(function(response) {
            deferred.resolve(response);
        }).error(function(response) {			
            deferred.reject(response);
        });
        return deferred.promise;
    };
    
    this.GetActividadesGeneradasHomeDocente = function(request) {
        $log.debug("ActividadesGeneradasService - GetActividadesGeneradasHomeDocente");

        var deferred = $q.defer();
        $http({
            method : 'POST',
            url : SRIUnsaConfig.SRIUnsaUrlServicio + '/actividadInvestigacionGenerada/GetActividadesGeneradasHomeDocente',
            data : request
        }).success(function(response) {
            deferred.resolve(response);
        }).error(function(response) {			
            deferred.reject(response);
        });
        return deferred.promise;
    };
    
    this.EnviarEmail = function(request){
        var deferred = $q.defer();
        $http({
            method : 'POST',
            url : SRIUnsaConfig.SRIUnsaUrlServicio + '/actividadInvestigacion/enviarEmail',
            data : request
        }).success(function(response) {
            deferred.resolve(response);
        }).error(function(response) {			
            deferred.reject(response);
        });
        return deferred.promise;
    };
    
    // Funcion que descarga un PDF de actividades generadas
    this.descargarPDF = function(request) {
        $log.debug("ActividadesGeneradasService - descargarPDF");

        var deferred = $q.defer();
        $http({
            method : 'POST',
            //url : SRIUnsaConfig.SRIUnsaUrlServicio + '/actividadInvestigacionGenerada/descargarPdf',
            url : SRIUnsaConfig.SRIUnsaUrlServicio + '/generadorpdfs/descargarPdf',
            data : request
        }).success(function(response) {
            deferred.resolve(response);
        }).error(function(response) {
            deferred.reject(response);
        });
        return deferred.promise;
    };
});