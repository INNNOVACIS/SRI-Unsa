/**
 * Rol  Service
 */
investigacionApp.service("FlujoAristaService", function(SRIUnsaConfig, $log, $http, $q) {
	
    this.getFlujoAristaByPagina = function(request) {
        $log.debug("Flujo Arista Service - getFlujoAristaByPagina");

        var deferred = $q.defer();
        $http({
            method : 'POST',
            url : SRIUnsaConfig.SRIUnsaUrlServicio + '/flujoarista/paginacionFlujoArista',
            data : request
        }).success(function(response) {
            deferred.resolve(response);
        }).error(function(response) {			
            deferred.reject(response);
        });
        return deferred.promise;
    };

    this.getFlujoAristas = function() {
//        $log.debug("Flujo Actor Service - get Roles");
//
//        var deferred = $q.defer();
//        $http({
//            method : 'GET',
//            url : SRIUnsaConfig.SRIUnsaUrlServicio + '/flujoactor/listarActores',
//        }).success(function(response) {
//            deferred.resolve(response);
//        }).error(function(response) {			
//            deferred.reject(response);
//        });
//        return deferred.promise;
    };

    this.RegistrarFlujoArista = function(request) {
        $log.debug("Flujo Arista Service - Registrar FlujoArista");

        var deferred = $q.defer();
        $http({
            method : 'POST',
            url : SRIUnsaConfig.SRIUnsaUrlServicio + '/flujoarista/registrarFlujoArista',
            data : request
        }).success(function(response) {
            deferred.resolve(response);
        }).error(function(response) {			
            deferred.reject(response);
        });
        return deferred.promise;
    };

    this.ActualizarFlujoArista = function(request) {
        $log.debug("Flujo Arista Service - Actualizar FlujoArista");

        var deferred = $q.defer();
        $http({
            method : 'PUT',
            url : SRIUnsaConfig.SRIUnsaUrlServicio + '/flujoarista/actualizarFlujoArista',
            data : request
        }).success(function(response) {
            deferred.resolve(response);
        }).error(function(response) {			
            deferred.reject(response);
        });
        return deferred.promise;
    };

    this.EliminarFlujoArista = function(request) {
        $log.debug("Flujo Arista Service - Eliminar FlujoArista");

        var deferred = $q.defer();
        $http({
            method : 'PUT',
            url : SRIUnsaConfig.SRIUnsaUrlServicio + '/flujoarista/eliminarFlujoArista',
            data : request
        }).success(function(response) {
            deferred.resolve(response);
        }).error(function(response) {			
            deferred.reject(response);
        });
        return deferred.promise;
    };
});