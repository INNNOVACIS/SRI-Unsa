/**
 * Rol  Service
 */
investigacionApp.service("UsuarioFlujoService", function(SRIUnsaConfig, $log, $http, $q) {
        
        this.getUsuarioFlujoByPagina = function(request) {
            $log.debug("UsuarioFlujo Service - get UsuarioFlujoByPaginacion");
            
            var deferred = $q.defer();
		$http({
			method : 'POST',
			url : SRIUnsaConfig.SRIUnsaUrlServicio + '/usuarioflujo/paginacionUsuarioFlujo',
			data : request
		}).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {			
			deferred.reject(response);
		});
		return deferred.promise;
        };
        
        this.getUsuarioFlujoActorByIdUsuario = function(id) {
		$log.debug("UsuarioFlujo Service - getUsuarioFlujoActorByIdUsuario");
		
		var deferred = $q.defer();
		$http({
			method : 'GET',
			url : SRIUnsaConfig.SRIUnsaUrlServicio + '/usuarioflujo/listarUsuarios/' + id,
		}).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {			
			deferred.reject(response);
		});
		return deferred.promise;
	};
        
        this.GetUsuarioFlujoByIdUsuario = function(id) {
		$log.debug("UsuarioFlujo Service - getUsuarioFlujoByIdUsuario");
		
		var deferred = $q.defer();
		$http({
			method : 'GET',
			url : SRIUnsaConfig.SRIUnsaUrlServicio + '/usuarioflujo/listarUsuarioFlujo/' + id,
		}).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {			
			deferred.reject(response);
		});
		return deferred.promise;
	};
        
	this.getUsuarioFlujo = function() {
		$log.debug("UsuarioFlujo Service - get Usuario Flujo");
		
		var deferred = $q.defer();
		$http({
			method : 'GET',
			url : SRIUnsaConfig.SRIUnsaUrlServicio + '/usuarioflujo/listarUsuarioFlujo',
		}).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {			
			deferred.reject(response);
		});
		return deferred.promise;
	};

	this.registrarUsuarioActor = function(request) {
		$log.debug("UsuarioFlujo Service - Registrar UsuarioFlujo");
		
		var deferred = $q.defer();
		$http({
			method : 'POST',
			url : SRIUnsaConfig.SRIUnsaUrlServicio + '/usuarioflujo/registrarUsuarioFlujo',
			data : request
		}).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {			
			deferred.reject(response);
		});
		return deferred.promise;
	};

	this.updateUsuarioFlujo = function(request) {
		$log.debug("UsuarioFlujo Service - Update UsuarioFlujo");
		
		var deferred = $q.defer();
		$http({
			method : 'PUT',
			url : SRIUnsaConfig.SRIUnsaUrlServicio + '/usuarioflujo/updateUsuarioFlujo',
			data : request
		}).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {			
			deferred.reject(response);
		});
		return deferred.promise;
	};

	this.deleteUsuarioFlujo = function(request) {
		$log.debug("UsuarioFlujo Service - Delete UsuarioFlujo");
		
		var deferred = $q.defer();
		$http({
			method : 'PUT',
			url : SRIUnsaConfig.SRIUnsaUrlServicio + '/usuarioflujo/deleteUsuarioFlujo',
			data : request
		}).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {			
			deferred.reject(response);
		});
		return deferred.promise;
	};
        
        this.CreateAndGetUsuarioFlujo = function(request) {//request es un objeto UsuarioFlujo
            $log.debug("UsuarioFlujo Service - CreateAndGetUsuarioFlujo");
            var deferred = $q.defer();
            $http({
                method : 'POST',
                url : SRIUnsaConfig.SRIUnsaUrlServicio + '/usuarioflujo/createGetUsuarioFlujo',
                data : request
            }).success(function(response) {
                deferred.resolve(response);
            }).error(function(response) {			
                deferred.reject(response);
            });
            return deferred.promise;
        };
});