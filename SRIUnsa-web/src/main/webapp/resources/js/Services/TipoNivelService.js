/**
 * Tipo Nivel Service
 */
investigacionApp.service("TipoNivelService", function(SRIUnsaConfig, $log, $http, $q) {
	
        this.getTipoNivelByPagina = function(request) {
            $log.debug("Tipo Nivel Service - getTipoNivelByPagina");

            var deferred = $q.defer();
            $http({
                    method : 'POST',
                    url : SRIUnsaConfig.SRIUnsaUrlServicio + '/tipoNivel/paginacionTipoNivel',
                    data : request
            }).success(function(response) {
                    deferred.resolve(response);
            }).error(function(response) {			
                    deferred.reject(response);
            });
            return deferred.promise;
	};
        
	this.getListaTipoNivel = function() {
		$log.debug("TipoNivel Service - get Lista TipoNivel");
		
		var deferred = $q.defer();
		$http({
			method : 'GET',
			url : SRIUnsaConfig.SRIUnsaUrlServicio + '/tipoNivel/listarTipoNivel',
		}).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {			
			deferred.reject(response);
		});
		return deferred.promise;
	};

	this.registrarTipoNivel = function(request) {
		$log.debug("TipoNivel Service - Registrar Tipo Nivel");
		
		var deferred = $q.defer();
		$http({
			method : 'POST',
			url : SRIUnsaConfig.SRIUnsaUrlServicio + '/tipoNivel/registrarTipoNivel',
			data : request
		}).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {			
			deferred.reject(response);
		});
		return deferred.promise;
	};

	this.updateTipoNivel = function(request) {
		$log.debug("TipoNivel Service - Update Tipo Nivel");
		
		var deferred = $q.defer();
		$http({
			method : 'PUT',
			url : SRIUnsaConfig.SRIUnsaUrlServicio + '/tipoNivel/updateTipoNivel',
			data : request
		}).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {			
			deferred.reject(response);
		});
		return deferred.promise;
	};

	this.deleteTipoNivel = function(request) {
		$log.debug("TipoNivel Service - Delete TipoNivel");
		
		var deferred = $q.defer();
		$http({
			method : 'PUT',
			url : SRIUnsaConfig.SRIUnsaUrlServicio + '/tipoNivel/deleteTipoNivel',
			data : request
		}).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {			
			deferred.reject(response);
		});
		return deferred.promise;
	};
});