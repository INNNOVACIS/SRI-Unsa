/**
 * Actividades Pendientes Service
 */
investigacionApp.service("ActividadesPendientesService", function(SRIUnsaConfig, $log, $http, $q) {
    
    this.paginacionActividades = function(request) {
        $log.debug("Actividades Generadas Service - paginacionActividades");

        var deferred = $q.defer();
        $http({
            method : 'POST',
            url : SRIUnsaConfig.SRIUnsaUrlServicio + '/actividadInvestigacionPendiente/actividadesPaginacion',
            data : request
        }).success(function(response) {
            deferred.resolve(response);
        }).error(function(response) {			
            deferred.reject(response);
        });
        return deferred.promise;
    };
    
    this.AprobarActividad = function(request) {
        $log.debug("ActividadesPendientesService - AprobarActividad");

        var deferred = $q.defer();
        $http({
            method : 'POST',
            url : SRIUnsaConfig.SRIUnsaUrlServicio + '/actividadInvestigacionPendiente/aprobarActividad',
            data : request
        }).success(function(response) {
            deferred.resolve(response);
        }).error(function(response) {			
            deferred.reject(response);
        });
        return deferred.promise;
    };

});