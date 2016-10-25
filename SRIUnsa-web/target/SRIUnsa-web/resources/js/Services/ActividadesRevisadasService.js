/**
 * Actividades Revisadas Service
 */
investigacionApp.service("ActividadesRevisadasService", function($log, $http, $q) {
	
    this.getInvestigaciones = function() {
        $log.debug("Actividades Revisadas Service - getInvestigaciones");

        var deferred = $q.defer();
        $http({
            method : 'GET',
            url : 'http://localhost:8080/SRIUnsa-web/rest/usuarios/listarUsuarios',
        }).success(function(response) {
            deferred.resolve(response);
        }).error(function(response) {			
            deferred.reject(response);
        });
        return deferred.promise;
    };
    
    this.Filtrar = function(request) {
        $log.debug("Filtrar Revisadas Service - Filtrar");
        
        console.log("string a enviar :: ", JSON.stringify(request));
        
        var deferred = $q.defer();
        $http({
            method : 'POST',
            url : 'http://localhost:8080/SRIUnsa-web/rest/actividades/filtrarRevisadas',
            data : JSON.stringify(request)
            //headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        }).success(function(response) {
            deferred.resolve(response);
        }).error(function(response) {			
            deferred.reject(response);
        });
        return deferred.promise;
    };

});