/**
 * ModuloExoneracion Service
 */
investigacionApp.service("ModuloExoneracionService", function(SRIUnsaConfig, $log, $http, $q) {
	
        this.getListaUsuarioExoneracion = function(request) {
            $log.debug("Exoneracion Service - getExoneracionByPagina");

            var deferred = $q.defer();
            $http({
                    method : 'POST',
                    url : SRIUnsaConfig.SRIUnsaUrlServicio + '/exoneracion/paginacionUsuarioExoneracion',
                    data : request
            }).success(function(response) {
                    deferred.resolve(response);
            }).error(function(response) {			
                    deferred.reject(response);
            });
            return deferred.promise;
	};
        
        
        ////////////////////////////////////////////////////////
    this.getDocentes = function () {
        $log.debug("Exoneracion Service - get Docentes");

        var deferred = $q.defer();
        $http({
            method: 'GET',
            url: SRIUnsaConfig.SRIUnsaUrlServicio + '/persona/getPersonas'
        }).success(function (response) {
            deferred.resolve(response);
        }).error(function (response) {
            deferred.reject(response);
        });
        return deferred.promise;
    };
    
    this.getExoneraciones = function () {
        $log.debug("Exoneracion Service - get Docentes");

        var deferred = $q.defer();
        $http({
            method: 'GET',
            url: SRIUnsaConfig.SRIUnsaUrlServicio + '/exoneracion/listarExoneracion'
        }).success(function (response) {
            deferred.resolve(response);
        }).error(function (response) {
            deferred.reject(response);
        });
        return deferred.promise;
    };
    
    this.RegistrarUsuarioExoneracion = function (usuarioExoneracion) {
        $log.debug("ModuloExoneracionService - RegistrarExoneracion");

        var deferred = $q.defer();
        $http({
            method: 'POST',
            url: SRIUnsaConfig.SRIUnsaUrlServicio + '/exoneracion/RegistrarUsuarioExoneracion',
            data: usuarioExoneracion
        }).success(function (response) {
            deferred.resolve(response);
        }).error(function (response) {
            deferred.reject(response);
        });
        return deferred.promise;
    };
    
    this.DeleteExoneracion = function (idUsuarioExoneracion) {
        $log.debug("ModuloExoneracionService - RegistrarExoneracion");

        var deferred = $q.defer();
        $http({
            method: 'GET',
            url: SRIUnsaConfig.SRIUnsaUrlServicio + '/exoneracion/EliminarUsuarioExoneracion/' + idUsuarioExoneracion
        }).success(function (response) {
            deferred.resolve(response);
        }).error(function (response) {
            deferred.reject(response);
        });
        return deferred.promise;
    };
});