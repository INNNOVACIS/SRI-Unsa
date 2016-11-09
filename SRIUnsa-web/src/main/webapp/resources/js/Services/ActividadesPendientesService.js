/**
 * Actividades Pendientes Service
 */
investigacionApp.service("ActividadesPendientesService", function(SRIUnsaConfig, $log, $http, $q) {
	
    this.getAllActividadesPendientes = function() {
        $log.debug("Actividades Pendientes Service - getAllPendientes");

        var deferred = $q.defer();
        $http({
            method : 'GET',
            url : SRIUnsaConfig.SRIUnsaUrlServicio + '/actividadInvestigacionPendiente/listarActividadPendiente',
        }).success(function(response) {
            deferred.resolve(response);
        }).error(function(response) {			
            deferred.reject(response);
        });
        return deferred.promise;
    };

});