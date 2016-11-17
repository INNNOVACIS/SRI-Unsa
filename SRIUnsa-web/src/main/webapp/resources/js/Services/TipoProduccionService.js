/**
 * Tipo Produccion Service
 */
investigacionApp.service("TipoProduccionService", function(SRIUnsaConfig, $log, $http, $q) {
	
        this.getTipoProduccionByPagina = function(request) {
            $log.debug("Tipo Produccion Service - getTipoProduccionByPagina");

            var deferred = $q.defer();
            $http({
                    method : 'POST',
                    url : SRIUnsaConfig.SRIUnsaUrlServicio + '/tipoProduccion/paginacionTipoProduccion',
                    data : request
            }).success(function(response) {
                    deferred.resolve(response);
            }).error(function(response) {			
                    deferred.reject(response);
            });
            return deferred.promise;
	};
        
	this.getListaTipoProduccion = function() {
		$log.debug("TipoProduccion Service - get Lista TipoProduccion");
		
		var deferred = $q.defer();
		$http({
			method : 'GET',
			url : SRIUnsaConfig.SRIUnsaUrlServicio + '/tipoProduccion/listarTipoProduccion',
		}).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {			
			deferred.reject(response);
		});
		return deferred.promise;
	};

	this.registrarTipoProduccion = function(request) {
		$log.debug("TipoProduccion Service - Registrar TipoProduccion");
		
		var deferred = $q.defer();
		$http({
			method : 'POST',
			url : SRIUnsaConfig.SRIUnsaUrlServicio + '/tipoProduccion/registrarTipoProduccion',
			data : request
		}).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {			
			deferred.reject(response);
		});
		return deferred.promise;
	};

	this.updateTipoProduccion = function(request) {
		$log.debug("TipoProduccion Service - Update TipoProduccion");
		
		var deferred = $q.defer();
		$http({
			method : 'PUT',
			url : SRIUnsaConfig.SRIUnsaUrlServicio + '/tipoProduccion/updateTipoProduccion',
			data : request
		}).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {			
			deferred.reject(response);
		});
		return deferred.promise;
	};

	this.deleteTipoProduccion = function(request) {
		$log.debug("TipoProduccion Service - Delete TipoProduccion");
		
		var deferred = $q.defer();
		$http({
			method : 'PUT',
			url : SRIUnsaConfig.SRIUnsaUrlServicio + '/tipoProduccion/deleteTipoProduccion',
			data : request
		}).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {			
			deferred.reject(response);
		});
		return deferred.promise;
	};
});