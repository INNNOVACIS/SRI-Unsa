/**
 * Actividades Generadas Service
 */
investigacionApp.service("HomeVicerectorService", function(SRIUnsaConfig, $log, $http, $q) {
	
    this.GetTotalActividadesByTipoActividad = function() {
        $log.debug("Actividades Generadas Service - GetTotalActividadesByTipoActividad");

        var deferred = $q.defer();
        $http({
            method : 'GET',
            url : SRIUnsaConfig.SRIUnsaUrlServicio + '/actividadInvestigacion/GetTotalActividadesByTipoActividad',
        }).success(function(response) {
            deferred.resolve(response);
        }).error(function(response) {			
            deferred.reject(response);
        });
        return deferred.promise;
    };
    
    this.GetActivosInactivosByFacultad = function() {
        $log.debug("Actividades Generadas Service - GetTotalActividadesByTipoActividad");

        var deferred = $q.defer();
        $http({
            method : 'GET',
            url : SRIUnsaConfig.SRIUnsaUrlServicio + '/actividadInvestigacion/GetActivosInactivosByFacultad',
        }).success(function(response) {
            deferred.resolve(response);
        }).error(function(response) {			
            deferred.reject(response);
        });
        return deferred.promise;
    };

});