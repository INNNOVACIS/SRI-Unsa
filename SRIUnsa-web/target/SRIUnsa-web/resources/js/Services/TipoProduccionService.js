/**
 * Tipo Produccion Service
 */
investigacionApp.service("TipoProduccionService", function($log, $http, $q) {
	
	this.getListaTipoProduccion = function() {
		$log.debug("TipoProduccion Service - get Lista TipoProduccion");
		
		var deferred = $q.defer();
		$http({
			method : 'GET',
			url : 'http://localhost:8080/SRIUnsa-web/rest/tipoproduccion/listaTipoProduccion',
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
			url : 'http://localhost:8080/SRIUnsa-web/rest/tipoproduccion/registrarTipoProduccion',
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
			url : 'http://localhost:8080/SRIUnsa-web/rest/tipoproduccion/updateTipoProduccion',
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
			url : 'http://localhost:8080/SRIUnsa-web/rest/tipoproduccion/deleteTipoProduccion',
			data : request
		}).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {			
			deferred.reject(response);
		});
		return deferred.promise;
	};
});