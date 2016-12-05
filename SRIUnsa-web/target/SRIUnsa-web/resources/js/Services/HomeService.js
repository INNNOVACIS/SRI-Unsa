/**
 * Home Service
 */
investigacionApp.service("HomeService", function(SRIUnsaConfig, $log, $http, $q) {

    this.sendFile = function(request) {
        $log.debug("Home Service - SendFile");

        var deferred = $q.defer();
        $http({
                method : 'POST',
                url : SRIUnsaConfig.SRIUnsaUrlServicio + '/files',
                data : request,
                transformRequest: angular.identity,
                headers: {'Content-Type': undefined}
        }).success(function(response) {
                console.log("Response Success File:: ", response);
                deferred.resolve(response);
        }).error(function(response) {
                console.log("Response Error File:: ", response);
                deferred.reject(response);
        });
        return deferred.promise;
    };

    this.registrarInvestigacion = function(request) {
        $log.debug("Investigacion Service - Registrar Investigacion");
        /*registrar-actividad*/
        var deferred = $q.defer();
        $http({
            method : 'POST',
            url : SRIUnsaConfig.SRIUnsaUrlServicio + '/actividadInvestigacion/registrarActividad',/**/
            data : request
        }).success(function(response) {
            deferred.resolve(response);
        }).error(function(response) {			
            deferred.reject(response);
        });
        return deferred.promise;
    };

});