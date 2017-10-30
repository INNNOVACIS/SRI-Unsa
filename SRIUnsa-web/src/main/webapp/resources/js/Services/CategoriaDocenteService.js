/**
 * Categoria Docente Service
 */
investigacionApp.service("CategoriaDocenteService", function(SRIUnsaConfig, $log, $http, $q) {
	
        this.getCategoriaDocenteByPagina = function(request) {
            $log.debug("Categoria Docente Service - getCategoriaDocenteByPagina");

            var deferred = $q.defer();
            $http({
                    method : 'POST',
                    url : SRIUnsaConfig.SRIUnsaUrlServicio + '/categoriaDocente/paginacionCategoriaDocente',
                    data : request
            }).success(function(response) {
                    deferred.resolve(response);
            }).error(function(response) {			
                    deferred.reject(response);
            });
            return deferred.promise;
	};
        
	this.getListaCategoriaDocente = function() {
		$log.debug("CategoriaDocente Service - get Lista CategoriaDocente");
		
		var deferred = $q.defer();
		$http({
			method : 'GET',
			url : SRIUnsaConfig.SRIUnsaUrlServicio + '/categoriaDocente/listarCategoriaDocente',
		}).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {			
			deferred.reject(response);
		});
		return deferred.promise;
	};

	this.registrarCategoriaDocente = function(request) {
		$log.debug("CategoriaDocente Service - Registrar Categoria Docente");
		
		var deferred = $q.defer();
		$http({
			method : 'POST',
			url : SRIUnsaConfig.SRIUnsaUrlServicio + '/categoriaDocente/registrarCategoriaDocente',
			data : request
		}).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {			
			deferred.reject(response);
		});
		return deferred.promise;
	};

	this.updateCategoriaDocente = function(request) {
		$log.debug("CategoriaDocente Service - Update Categoria Docente");
		
		var deferred = $q.defer();
		$http({
			method : 'PUT',
			url : SRIUnsaConfig.SRIUnsaUrlServicio + '/categoriaDocente/actualizarCategoriaDocente',
			data : request
		}).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {			
			deferred.reject(response);
		});
		return deferred.promise;
	};

	this.deleteCategoriaDocente = function(request) {
		$log.debug("CategoriaDocente Service - Delete CategoriaDocente");
		
		var deferred = $q.defer();
		$http({
			method : 'PUT',
			url : SRIUnsaConfig.SRIUnsaUrlServicio + '/categoriaDocente/eliminarCategoriaDocente',
			data : request
		}).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {			
			deferred.reject(response);
		});
		return deferred.promise;
	};
});