/**
 * ModuloExoneracion Service
 */
investigacionApp.service("ModuloExoneracionService", function(SRIUnsaConfig, $log, $http, $q) {
	
        this.getExoneracionByPagina = function(request) {
            $log.debug("Exoneracion Service - getExoneracionByPagina");

            var deferred = $q.defer();
            $http({
                    method : 'POST',
                    url : SRIUnsaConfig.SRIUnsaUrlServicio + '/exoneracion/paginacionExoneracion',
                    data : request
            }).success(function(response) {
                    deferred.resolve(response);
            }).error(function(response) {			
                    deferred.reject(response);
            });
            return deferred.promise;
	};
        
	this.getListaExoneracion = function() {
		$log.debug("Exoneracion Service - get Lista Exoneracion");
		
		var deferred = $q.defer();
		$http({
			method : 'GET',
			url : SRIUnsaConfig.SRIUnsaUrlServicio + '/exoneracion/listarExoneracion',
		}).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {			
			deferred.reject(response);
		});
		return deferred.promise;
	};

	this.registrarExoneracion = function(request) {
		$log.debug("Exoneracion Service - Registrar Exoneracion");
		
		var deferred = $q.defer();
		$http({
			method : 'POST',
			url : SRIUnsaConfig.SRIUnsaUrlServicio + '/exoneracion/registrarExoneracion',
			data : request
		}).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {			
			deferred.reject(response);
		});
		return deferred.promise;
	};

	this.updateExoneracion = function(request) {
		$log.debug("Exoneracion Service - Update Exoneracion");
		
		var deferred = $q.defer();
		$http({
			method : 'PUT',
			url : SRIUnsaConfig.SRIUnsaUrlServicio + '/exoneracion/updateExoneracion',
			data : request
		}).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {			
			deferred.reject(response);
		});
		return deferred.promise;
	};

	this.deleteExoneracion = function(request) {
		$log.debug("Exoneracion Service - Delete Exoneracion");
		
		var deferred = $q.defer();
		$http({
			method : 'PUT',
			url : SRIUnsaConfig.SRIUnsaUrlServicio + '/exoneracion/deleteExoneracion',
			data : request
		}).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {			
			deferred.reject(response);
		});
		return deferred.promise;
	};
        
        
        
        ////////////////////////////////////////////////////////
    this.getDocentes = function () {
        $log.debug("Exoneracion Service - get Docentes");

        var deferred = $q.defer();
        $http({
            method: 'GET',
            url: SRIUnsaConfig.SRIUnsaUrlServicio + '/persona/getPersonas'
        }).success(function (response) {
            deferred.resolve(response);
        }).error(function (response) {
            deferred.reject(response);
        });
        return deferred.promise;
    };
    
    this.getExoneraciones = function () {
        $log.debug("Exoneracion Service - get Docentes");

        var deferred = $q.defer();
        $http({
            method: 'GET',
            url: SRIUnsaConfig.SRIUnsaUrlServicio + '/exoneracion/listarExoneracion'
        }).success(function (response) {
            deferred.resolve(response);
        }).error(function (response) {
            deferred.reject(response);
        });
        return deferred.promise;
    };
});