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
    
    this.GuardarInvestigacion = function(request) {
        $log.debug("HomeService - GuardarInvestigacion");
        /*registrar-actividad*/
        var deferred = $q.defer();
        $http({
            method : 'POST',
            url : SRIUnsaConfig.SRIUnsaUrlServicio + '/actividadInvestigacion/GuardarInvestigacion',
            data : request
        }).success(function(response) {
            deferred.resolve(response);
        }).error(function(response) {			
            deferred.reject(response);
        });
        return deferred.promise;
    };

    this.registrarInvestigacion = function(request) {
        $log.debug("HomeService - registrarInvestigacion");
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
    
    this.actualizarInvestigacion = function(request) {
        $log.debug("HomeService - actualizarInvestigacion");
        /*actualizar-actividad*/
        var deferred = $q.defer();
        $http({
            method : 'PUT',
            url : SRIUnsaConfig.SRIUnsaUrlServicio + '/actividadInvestigacion/updateActividadInvestigacion',/**/
            data : request
        }).success(function(response) {
            deferred.resolve(response);
        }).error(function(response) {			
            deferred.reject(response);
        });
        return deferred.promise;
    };

});