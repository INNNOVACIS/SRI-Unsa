/**
 * Rol  Service
 */
investigacionApp.service("PlantillaDocumentoService", function(SRIUnsaConfig, $log, $http, $q) {
	
        this.RegistrarPlantillaDocumento = function(request) {
            $log.debug("PlantillaDocumento Service - RegistrarCampo");

            var deferred = $q.defer();
            $http({
                    method : 'POST',
                    url : SRIUnsaConfig.SRIUnsaUrlServicio + '/plantillaDocumento/registrarCampo',
                    data : request
            }).success(function(response) {
                    deferred.resolve(response);
            }).error(function(response) {			
                    deferred.reject(response);
            });
            return deferred.promise;
	};

	this.ActualizarPlantillaDocumento = function(request) {
            $log.debug("PlantillaDocumento Service - ActualizarCampo");

            var deferred = $q.defer();
            $http({
                    method : 'PUT',
                    url : SRIUnsaConfig.SRIUnsaUrlServicio + '/plantillaDocumento/actualizarCampo',
                    data : request
            }).success(function(response) {
                    deferred.resolve(response);
            }).error(function(response) {			
                    deferred.reject(response);
            });
            return deferred.promise;
	};

	this.EliminarPlantillaDocumento = function(request) {
            $log.debug("PlantillaDocumento Service - EliminarCampo");

            var deferred = $q.defer();
            $http({
                    method : 'PUT',
                    url : SRIUnsaConfig.SRIUnsaUrlServicio + '/plantillaDocumento/eliminarCampo',
                    data : request
            }).success(function(response) {
                    deferred.resolve(response);
            }).error(function(response) {			
                    deferred.reject(response);
            });
            return deferred.promise;
	};
        
        this.GetPlantillaDocumento = function(request){
            $log.debug("PlantillaDocumento Service - GetPlantillaDocumento");
		
            var deferred = $q.defer();
            $http({
                    method : 'POST',
                    url : SRIUnsaConfig.SRIUnsaUrlServicio + '/plantillaDocumento/GetPlantillaDocumento',
                    data : request
            }).success(function(response) {
                    deferred.resolve(response);
            }).error(function(response) {			
                    deferred.reject(response);
            });
            return deferred.promise;
        };
        
        this.GetPlantillaDocumentoByFacultad = function(request){
            $log.debug("PlantillaDocumento Service - GetPlantillaDocumento");
		
            var deferred = $q.defer();
            $http({
                    method : 'POST',
                    url : SRIUnsaConfig.SRIUnsaUrlServicio + '/plantillaDocumento/GetPlantillaDocumentoByFacultad',
                    data : request
            }).success(function(response) {
                    deferred.resolve(response);
            }).error(function(response) {			
                    deferred.reject(response);
            });
            return deferred.promise;
        };
});