/**
 * Actividades Revisadas Service
 */
investigacionApp.service("ActividadesRevisadasService", function(SRIUnsaConfig, $log, $http, $q) {
	
    this.getInvestigaciones = function() {//CAMBIAR NOMBRE A GET TIPOINVESTIGACIONES
        $log.debug("Actividades Revisadas Service - getInvestigaciones");

        var deferred = $q.defer();
        $http({
            method : 'GET',
            url : SRIUnsaConfig.SRIUnsaUrlServicio + '/actividadInvestigacionRevisada/listarActividadInvestigacion',
        }).success(function(response) {
            deferred.resolve(response);
        }).error(function(response) {			
            deferred.reject(response);
        });
        return deferred.promise;
    };
    
    this.GetActividadesRevisadas = function(request) {
        $log.debug("ActividadesRevisadasService - GetActividadesRevisadas");

        var deferred = $q.defer();
        $http({
            method : 'POST',
            url : SRIUnsaConfig.SRIUnsaUrlServicio + '/actividadInvestigacionRevisada/actividades',
            data : request
        }).success(function(response) {
            deferred.resolve(response);
        }).error(function(response) {			
            deferred.reject(response);
        });
        return deferred.promise;
    };

});