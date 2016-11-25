/**
 * Rol  Service
 */
investigacionApp.service("FlujoAristaService", function(SRIUnsaConfig, $log, $http, $q) {
	
    this.getFlujoAristaByPagina = function(request) {
        $log.debug("FlujoArista Service - getFlujoAristaByPagina");

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

    this.GetFlujoAristaByIdOrigenIdEstado = function(idOrigen, idEstado) {
        $log.debug("FlujoArista Service - GetFlujoAristaByIdOrigenIdEstado");

        var deferred = $q.defer();
        $http({
            method : 'GET',
            url : SRIUnsaConfig.SRIUnsaUrlServicio + '/flujoarista/listarFlujoArista/' + idOrigen + '/' + idEstado
        }).success(function(response) {
            deferred.resolve(response);
        }).error(function(response) {			
            deferred.reject(response);
        });
        return deferred.promise;
    };

    this.RegistrarFlujoArista = function(request) {
        $log.debug("FlujoArista Service - Registrar FlujoArista");

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
        $log.debug("FlujoArista Service - Actualizar FlujoArista");

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
        $log.debug("FlujoArista Service - Eliminar FlujoArista");

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