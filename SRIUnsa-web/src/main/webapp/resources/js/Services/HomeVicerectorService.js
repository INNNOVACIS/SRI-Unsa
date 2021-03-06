/**
 * Actividades Generadas Service
 */
investigacionApp.service("HomeVicerectorService", function(SRIUnsaConfig, $log, $http, $q) {
	
    this.GetTotalActividadesByTipoActividad = function(idSemestre) {
        $log.debug("HomeVicerectorService - GetTotalActividadesByTipoActividad");

        var deferred = $q.defer();
        $http({
            method : 'GET',
            url : SRIUnsaConfig.SRIUnsaUrlServicio + '/actividadInvestigacion/GetTotalActividadesByTipoActividad/' + idSemestre
        }).success(function(response) {
            deferred.resolve(response);
        }).error(function(response) {			
            deferred.reject(response);
        });
        return deferred.promise;
    };
    
    this.GetTotalActividadesByTipoActividadFacultad = function(idFacultad, idSemestre) {
        $log.debug("HomeVicerectorService - GetTotalActividadesByTipoActividadFacultad");

        var deferred = $q.defer();
        $http({
            method : 'GET',
            url : SRIUnsaConfig.SRIUnsaUrlServicio + '/actividadInvestigacion/GetTotalActividadesByTipoActividadFacultad/' + idFacultad + '/' + idSemestre
        }).success(function(response) {
            deferred.resolve(response);
        }).error(function(response) {			
            deferred.reject(response);
        });
        return deferred.promise;
    };
    
    this.GetActivosInactivosByFacultad = function(idTipoInvestigacion, idSemestre) {
        $log.debug("HomeVicerectorService - GetActivosInactivosByFacultad");

        var deferred = $q.defer();
        $http({
            method : 'GET',
            url : SRIUnsaConfig.SRIUnsaUrlServicio + '/actividadInvestigacion/GetActivosInactivosByFacultad/' + idTipoInvestigacion + '/' + idSemestre
        }).success(function(response) {
            deferred.resolve(response);
        }).error(function(response) {			
            deferred.reject(response);
        });
        return deferred.promise;
    };
    
    this.GetTotalActivosInactivosByDepartamento = function(idFacultad, idTipoInvestigacion, idSemestre) {
        $log.debug("HomeVicerectorService - GetTotalActivosInactivosByDepartamento");

        var deferred = $q.defer();
        $http({
            method : 'GET',
            url : SRIUnsaConfig.SRIUnsaUrlServicio + '/actividadInvestigacion/GetTotalActivosInactivosByDepartamento/' + idFacultad + '/' + idTipoInvestigacion + '/' + idSemestre
        }).success(function(response) {
            deferred.resolve(response);
        }).error(function(response) {			
            deferred.reject(response);
        });
        return deferred.promise;
    };

});