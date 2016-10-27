/**
 * Login Service
 */
investigacionApp.service("LoginService", function(SRIUnsaConfig, $log, $http, $q) {

    this.Autenticar = function(request) {
        $log.debug("Login Service - Autentication");

        var deferred = $q.defer();
        $http({
                method : 'POST',
                url : SRIUnsaConfig.SRIUnsaUrlServicio + '/usuarios/autenticarUsuarios',
                data : request
        }).success(function(response) {
                deferred.resolve(response);
        }).error(function(response) {
                deferred.reject(response);
        });
        return deferred.promise;
    };
});