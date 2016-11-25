/**
 * ProcesoFlujo Service
 */
investigacionApp.service("ProcesoFlujoService", function(SRIUnsaConfig, $log, $http, $q) {
    
    this.GetProcesoFlujo = function() {
        $log.debug("ProcesoFlujo Service - GetProcesoFlujo");

        var deferred = $q.defer();
        $http({
            method : 'GET',
            url : SRIUnsaConfig.SRIUnsaUrlServicio + '/procesoflujo/listarProcesoFlujos'
        }).success(function(response) {
            deferred.resolve(response);
        }).error(function(response) {			
            deferred.reject(response);
        });
        return deferred.promise;
    };

    this.RegistrarProcesoFlujo = function(request) {
        $log.debug("ProcesoFlujo Service - RegistrarProcesoFlujo");

        var deferred = $q.defer();
        $http({
            method : 'POST',
            url : SRIUnsaConfig.SRIUnsaUrlServicio + '/procesoflujo/registrarProcesoFlujo',
            data : request
        }).success(function(response) {
            deferred.resolve(response);
        }).error(function(response) {			
            deferred.reject(response);
        });
        return deferred.promise;
    };

    this.ActualizarProcesoFlujo = function(request) {
        $log.debug("ProcesoFlujo Service - ActualizarProcesoFlujo");

        var deferred = $q.defer();
        $http({
            method : 'PUT',
            url : SRIUnsaConfig.SRIUnsaUrlServicio + '/procesoflujo/actualizarProcesoFlujo',
            data : request
        }).success(function(response) {
            deferred.resolve(response);
        }).error(function(response) {			
            deferred.reject(response);
        });
        return deferred.promise;
    };

    this.EliminarProcesoFlujo = function(request) {
        $log.debug("ProcesoFlujo Service - EliminarProcesoFlujo");

        var deferred = $q.defer();
        $http({
            method : 'PUT',
            url : SRIUnsaConfig.SRIUnsaUrlServicio + '/procesoflujo/eliminarProcesoFlujo',
            data : request
        }).success(function(response) {
            deferred.resolve(response);
        }).error(function(response) {			
            deferred.reject(response);
        });
        return deferred.promise;
    };
});