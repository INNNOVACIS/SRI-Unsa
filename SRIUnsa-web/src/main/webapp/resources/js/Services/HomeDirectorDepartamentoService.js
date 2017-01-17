/**
 * HomeDirectorDepartamento Service
 */
investigacionApp.service("HomeDirectorDepartamentoService", function(SRIUnsaConfig, $log, $http, $q) {
	
    this.GetActivosInactivosByFacultad = function(idTipoInvestigacion) {
        $log.debug("HomeDirectorDepartamentoService - GetActivosInactivosByFacultad");

        var deferred = $q.defer();
        $http({
            method : 'GET',
            url : SRIUnsaConfig.SRIUnsaUrlServicio + '/actividadInvestigacion/GetActivosInactivosByFacultad/' + idTipoInvestigacion
        }).success(function(response) {
            deferred.resolve(response);
        }).error(function(response) {			
            deferred.reject(response);
        });
        return deferred.promise;
    };
    
    this.GetTotalActivosInactivosHomeDepartamento = function(idFacultad, idTipoInvestigacion) {
        $log.debug("HomeDirectorDepartamentoService - GetTotalActivosInactivosHomeDepartamento");

        var deferred = $q.defer();
        $http({
            method : 'GET',
            url : SRIUnsaConfig.SRIUnsaUrlServicio + '/actividadInvestigacion/GetTotalActivosInactivosHomeDepartamento/' + idFacultad + '/' + idTipoInvestigacion
        }).success(function(response) {
            deferred.resolve(response);
        }).error(function(response) {			
            deferred.reject(response);
        });
        return deferred.promise;
    };
    
    this.GetTotalActividadesByTipoActividadDepartamento = function(idDepartamento) {
        $log.debug("HomeDirectorDepartamentoService - GetTotalActividadesByTipoActividadDepartamento");

        var deferred = $q.defer();
        $http({
            method : 'GET',
            url : SRIUnsaConfig.SRIUnsaUrlServicio + '/actividadInvestigacion/GetTotalActividadesByTipoActividadDepartamento/' + idDepartamento
        }).success(function(response) {
            deferred.resolve(response);
        }).error(function(response) {			
            deferred.reject(response);
        });
        return deferred.promise;
    };

});