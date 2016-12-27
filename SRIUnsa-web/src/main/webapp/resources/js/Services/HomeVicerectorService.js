/**
 * Actividades Generadas Service
 */
investigacionApp.service("HomeVicerectorService", function(SRIUnsaConfig, $log, $http, $q) {
	
    this.GetTotalActividadesByTipoActividad = function() {
        $log.debug("HomeVicerectorService - GetTotalActividadesByTipoActividad");

        var deferred = $q.defer();
        $http({
            method : 'GET',
            url : SRIUnsaConfig.SRIUnsaUrlServicio + '/actividadInvestigacion/GetTotalActividadesByTipoActividad'
        }).success(function(response) {
            deferred.resolve(response);
        }).error(function(response) {			
            deferred.reject(response);
        });
        return deferred.promise;
    };
    
    this.GetTotalActividadesByTipoActividadFacultad = function(idFacultad) {
        $log.debug("HomeVicerectorService - GetTotalActividadesByTipoActividadFacultad");

        var deferred = $q.defer();
        $http({
            method : 'GET',
            url : SRIUnsaConfig.SRIUnsaUrlServicio + '/actividadInvestigacion/GetTotalActividadesByTipoActividadFacultad/' + idFacultad
        }).success(function(response) {
            deferred.resolve(response);
        }).error(function(response) {			
            deferred.reject(response);
        });
        return deferred.promise;
    };
    
    this.GetActivosInactivosByFacultad = function() {
        $log.debug("HomeVicerectorService - GetActivosInactivosByFacultad");

        var deferred = $q.defer();
        $http({
            method : 'GET',
            url : SRIUnsaConfig.SRIUnsaUrlServicio + '/actividadInvestigacion/GetActivosInactivosByFacultad'
        }).success(function(response) {
            deferred.resolve(response);
        }).error(function(response) {			
            deferred.reject(response);
        });
        return deferred.promise;
    };
    
    this.GetTotalActivosInactivosByDepartamento = function(idFacultad, idTipoInvestigacion) {
        $log.debug("HomeVicerectorService - GetTotalActivosInactivosByDepartamento");

        var deferred = $q.defer();
        $http({
            method : 'GET',
            url : SRIUnsaConfig.SRIUnsaUrlServicio + '/actividadInvestigacion/GetTotalActivosInactivosByDepartamento/' + idFacultad + '/' + idTipoInvestigacion
        }).success(function(response) {
            deferred.resolve(response);
        }).error(function(response) {			
            deferred.reject(response);
        });
        return deferred.promise;
    };

});