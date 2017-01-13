/**
 * Header Service
 */
investigacionApp.service("HeaderService", function(SRIUnsaConfig, $log, $http, $q) {

    this.verificarCodigo = function(request) {
        $log.debug("HeaderService - verificarCodigo");
        var deferred = $q.defer();
        $http({
                method : 'POST',
                url : SRIUnsaConfig.SRIUnsaUrlServicio + '/usuarios/verificarCodigo',
                data : request
        }).success(function(response) {
                deferred.resolve(response);
        }).error(function(response) {
                deferred.reject(response);
        });
        return deferred.promise;
    };
    
    this.enviarCodigo = function(idUsuario) {
        $log.debug("HeaderService - enviarCodigo");
        var deferred = $q.defer();
        $http({
                method : 'GET',
                url : SRIUnsaConfig.SRIUnsaUrlServicio + '/usuarios/enviarCodigo/' + idUsuario
        }).success(function(response) {
                deferred.resolve(response);
        }).error(function(response) {
                deferred.reject(response);
        });
        return deferred.promise;
    };
    
    this.updateUsuario = function(request) {
        $log.debug("Usuario Service - Update Usuario");

        var deferred = $q.defer();
        $http({
                method : 'PUT',
                url : SRIUnsaConfig.SRIUnsaUrlServicio + '/usuarios/updateUsuarios',
                data : request
        }).success(function(response) {
                deferred.resolve(response);
        }).error(function(response) {			
                deferred.reject(response);
        });
        return deferred.promise;
    };
    
});