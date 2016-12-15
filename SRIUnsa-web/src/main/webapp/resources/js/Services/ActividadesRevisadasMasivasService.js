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
    
    this.GetCabeceraMasiva = function(id){
        $log.debug("ActividadesRevisadasMasivasService - GetCabeceraMasiva");
		
        var deferred = $q.defer();
        $http({
                method : 'GET',
                url : SRIUnsaConfig.SRIUnsaUrlServicio + '/actividadInvestigacionRevisada/getCabeceraMasiva/' + id
        }).success(function(response) {
                deferred.resolve(response);
        }).error(function(response) {			
                deferred.reject(response);
        });
        return deferred.promise;
    };
    
    this.GetDetalleMasiva = function(request){
        $log.debug("ActividadesRevisadasMasivasService - GetDetalleMasiva");
		
        var deferred = $q.defer();
        $http({
                method : 'POST',
                url : SRIUnsaConfig.SRIUnsaUrlServicio + '/actividadInvestigacionRevisada/getDetalleMasiva',
                data : request
        }).success(function(response) {
                deferred.resolve(response);
        }).error(function(response) {			
                deferred.reject(response);
        });
        return deferred.promise;
    };
    
    this.GetActividadesRevisadasMasivas = function(request) {
        $log.debug("ActividadesRevisadasService - GetActividadesRevisadasMasivas");

        var deferred = $q.defer();
        $http({
            method : 'POST',
            url : SRIUnsaConfig.SRIUnsaUrlServicio + '/actividadInvestigacionRevisada/getActividadesRevisadasMasivas',
            data : request
        }).success(function(response) {
            deferred.resolve(response);
        }).error(function(response) {			
            deferred.reject(response);
        });
        return deferred.promise;
    };

});