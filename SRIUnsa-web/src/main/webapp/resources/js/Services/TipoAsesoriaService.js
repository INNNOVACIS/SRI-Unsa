/**
 * TipoAsesoria  Service
 */
investigacionApp.service("TipoAsesoriaService", function(SRIUnsaConfig, $log, $http, $q) {
	
        this.getTipoAsesoriaByPagina = function(request) {
            $log.debug("Tipo Asesoria Service - getTipoAsesoriaByPagina");

            var deferred = $q.defer();
            $http({
                    method : 'POST',
                    url : SRIUnsaConfig.SRIUnsaUrlServicio + '/tipoAsesoria/paginacionTipoAsesoria',
                    data : request
            }).success(function(response) {
                    deferred.resolve(response);
            }).error(function(response) {			
                    deferred.reject(response);
            });
            return deferred.promise;
	};
        
	this.getAsesorias = function() {
		$log.debug("Tipo Asesoria Service - get Asesorias");
		
		var deferred = $q.defer();
		$http({
			method : 'GET',
			url : SRIUnsaConfig.SRIUnsaUrlServicio + '/tipoAsesoria/listarTipoAsesorias',
		}).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {			
			deferred.reject(response);
		});
		return deferred.promise;
	};

	this.registrarAsesoria = function(request) {
		$log.debug("Tipo Asesoria Service - Registrar Asesoria");
		
		var deferred = $q.defer();
		$http({
			method : 'POST',
			url : SRIUnsaConfig.SRIUnsaUrlServicio +  '/tipoAsesoria/registrarTipoAsesorias',
			data : request
		}).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {			
			deferred.reject(response);
		});
		return deferred.promise;
	};

	this.updateAsesoria = function(request) {
		$log.debug("Tipo Asesoria Service - Update Asesoria");
		
		var deferred = $q.defer();
		$http({
			method : 'PUT',
			url : SRIUnsaConfig.SRIUnsaUrlServicio + '/tipoAsesoria/updateTipoAsesorias',
			data : request
		}).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {			
			deferred.reject(response);
		});
		return deferred.promise;
	};

	this.deleteAsesoria = function(request) {
		$log.debug("Tipo Asesoria Service - Delete Asesoria");
		
		var deferred = $q.defer();
		$http({
			method : 'PUT',
			url : SRIUnsaConfig.SRIUnsaUrlServicio + '/tipoAsesoria/deleteTipoAsesorias',
			data : request
		}).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {			
			deferred.reject(response);
		});
		return deferred.promise;
	};
});