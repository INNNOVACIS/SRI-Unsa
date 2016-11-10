/**
 * Actividades Generadas Service
 */
investigacionApp.service("ActividadesGeneradasService", function(SRIUnsaConfig, $log, $http, $q) {
	
    this.getAllActividadesGeneradas = function() {
        $log.debug("Actividades Generadas Service - getAllActividadesGeneradas");

        var deferred = $q.defer();
        $http({
            method : 'GET',
            url : SRIUnsaConfig.SRIUnsaUrlServicio + '/actividadInvestigacionGenerada/listarActividadGenerada',
        }).success(function(response) {
            deferred.resolve(response);
        }).error(function(response) {			
            deferred.reject(response);
        });
        return deferred.promise;
    };
    
    this.paginacionActividades = function(request) {
        $log.debug("Actividades Generadas Service - paginacionActividades");

        var deferred = $q.defer();
        $http({
            method : 'POST',
            url : SRIUnsaConfig.SRIUnsaUrlServicio + '/actividadInvestigacionGenerada/actividadesPaginacion',
            data : request
        }).success(function(response) {
            deferred.resolve(response);
        }).error(function(response) {			
            deferred.reject(response);
        });
        return deferred.promise;
    };

});