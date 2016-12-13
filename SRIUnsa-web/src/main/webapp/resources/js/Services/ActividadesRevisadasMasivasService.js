/**
 * Actividades Revisadas Service
 */
investigacionApp.service("ActividadesRevisadasMasivasService", function(SRIUnsaConfig, $log, $http, $q) {
	
    this.AprobarActividades = function(request) {
        $log.debug("ActividadesRevisadasMasivasService - AprobarActividades");

        var deferred = $q.defer();
        $http({
            method : 'POST',
            url : SRIUnsaConfig.SRIUnsaUrlServicio + '/actividadInvestigacionRevisada/aprobarActividades',
            data : request
        }).success(function(response) {
            deferred.resolve(response);
        }).error(function(response) {			
            deferred.reject(response);
        });
        return deferred.promise;
    };

});