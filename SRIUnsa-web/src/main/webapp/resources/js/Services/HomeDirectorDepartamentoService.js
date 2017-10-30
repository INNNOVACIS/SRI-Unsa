/**
 * HomeDirectorDepartamento Service
 */
investigacionApp.service("HomeDirectorDepartamentoService", function(SRIUnsaConfig, $log, $http, $q) {
	
    this.GetActivosInactivosByFacultad = function(idTipoInvestigacion,idSemestre) {
        $log.debug("HomeDirectorDepartamentoService - GetActivosInactivosByFacultad");

        var deferred = $q.defer();
        $http({
            method : 'GET',
            url : SRIUnsaConfig.SRIUnsaUrlServicio + '/actividadInvestigacion/GetActivosInactivosByFacultad/' + idTipoInvestigacion+'/'+idSemestre
        }).success(function(response) {
            deferred.resolve(response);
        }).error(function(response) {			
            deferred.reject(response);
        });
        return deferred.promise;
    };
    
    this.GetTotalActivosInactivosHomeDepartamento = function(idFacultad, idTipoInvestigacion, idSemestre) {
        $log.debug("HomeDirectorDepartamentoService - GetTotalActivosInactivosHomeDepartamento");

        var deferred = $q.defer();
        $http({
            method : 'GET',
            url : SRIUnsaConfig.SRIUnsaUrlServicio + '/actividadInvestigacion/GetTotalActivosInactivosHomeDepartamento/' + idFacultad + '/' + idTipoInvestigacion + '/' + idSemestre
        }).success(function(response) {
            deferred.resolve(response);
        }).error(function(response) {			
            deferred.reject(response);
        });
        return deferred.promise;
    };
    
    this.GetTotalActividadesByTipoActividadDepartamento = function(idDepartamento, idSemestre) {
        $log.debug("HomeDirectorDepartamentoService - GetTotalActividadesByTipoActividadDepartamento");

        var deferred = $q.defer();
        $http({
            method : 'GET',
            url : SRIUnsaConfig.SRIUnsaUrlServicio + '/actividadInvestigacion/GetTotalActividadesByTipoActividadDepartamento/' + idDepartamento + '/' + idSemestre
        }).success(function(response) {
            deferred.resolve(response);
        }).error(function(response) {			
            deferred.reject(response);
        });
        return deferred.promise;
    };

});