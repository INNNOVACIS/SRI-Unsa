/**
 * Rol  Service
 */
investigacionApp.service("FlujoActorService", function(SRIUnsaConfig, $log, $http, $q) {
	
    this.getActoresByPagina = function(request) {
        $log.debug("Flujo Actor Service - getActoresByPagina");

        var deferred = $q.defer();
        $http({
            method : 'POST',
            url : SRIUnsaConfig.SRIUnsaUrlServicio + '/flujoactor/paginacionActores',
            data : request
        }).success(function(response) {
            deferred.resolve(response);
        }).error(function(response) {			
            deferred.reject(response);
        });
        return deferred.promise;
    };

    this.getActores = function() {
        $log.debug("Flujo Actor Service - get Roles");

        var deferred = $q.defer();
        $http({
            method : 'GET',
            url : SRIUnsaConfig.SRIUnsaUrlServicio + '/flujoactor/listarActores',
        }).success(function(response) {
            deferred.resolve(response);
        }).error(function(response) {			
            deferred.reject(response);
        });
        return deferred.promise;
    };

    this.registrarActor = function(request) {
        $log.debug("Flujo Actor Service - Registrar Rol");

        var deferred = $q.defer();
        $http({
            method : 'POST',
            url : SRIUnsaConfig.SRIUnsaUrlServicio + '/flujoactor/registrarActores',
            data : request
        }).success(function(response) {
            deferred.resolve(response);
        }).error(function(response) {			
            deferred.reject(response);
        });
        return deferred.promise;
    };

    this.updateActor = function(request) {
        $log.debug("Flujo Actor Service - Update Rol");

        var deferred = $q.defer();
        $http({
            method : 'PUT',
            url : SRIUnsaConfig.SRIUnsaUrlServicio + '/flujoactor/updateActores',
            data : request
        }).success(function(response) {
            deferred.resolve(response);
        }).error(function(response) {			
            deferred.reject(response);
        });
        return deferred.promise;
    };

    this.deleteActor = function(request) {
        $log.debug("Flujo Actor Service - Delete Rol");

        var deferred = $q.defer();
        $http({
            method : 'PUT',
            url : SRIUnsaConfig.SRIUnsaUrlServicio + '/flujoactor/deleteActores',
            data : request
        }).success(function(response) {
            deferred.resolve(response);
        }).error(function(response) {			
            deferred.reject(response);
        });
        return deferred.promise;
    };
});