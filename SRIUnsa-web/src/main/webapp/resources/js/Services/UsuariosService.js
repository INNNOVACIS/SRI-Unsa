/**
 * Usuario Service
 */
investigacionApp.service("UsuariosService", function(SRIUnsaConfig, $log, $http, $q) {
	
        this.paginacionUsuario = function(request) {
            $log.debug("Usuario Service - PaginacionUsuario");

            var deferred = $q.defer();
            $http({
                    method : 'POST',
                    url : SRIUnsaConfig.SRIUnsaUrlServicio + '/usuarios/paginacionUsuarios',
                    data : request
            }).success(function(response) {
                    deferred.resolve(response);
            }).error(function(response) {			
                    deferred.reject(response);
            });
            return deferred.promise;
	};
        
	this.getUsuarios = function() {
            $log.debug("Usuario Service - get Usuarios");

            var deferred = $q.defer();
            $http({
                    method : 'GET',
                    url : SRIUnsaConfig.SRIUnsaUrlServicio + '/usuarios/listarUsuarios',
            }).success(function(response) {
                    deferred.resolve(response);
            }).error(function(response) {			
                    deferred.reject(response);
            });
            return deferred.promise;
	};

	this.registrarUsuario = function(request) {
            $log.debug("Usuario Service - Registrar Usuario");

            var deferred = $q.defer();
            $http({
                    method : 'POST',
                    url : SRIUnsaConfig.SRIUnsaUrlServicio + '/usuarios/registrarUsuarios',
                    data : request
            }).success(function(response) {
                    deferred.resolve(response);
            }).error(function(response) {			
                    deferred.reject(response);
            });
            return deferred.promise;
	};

	this.updateUsuario = function(request) {
            $log.debug("Usuario Service - Update Usuario");

            var deferred = $q.defer();
            $http({
                    method : 'PUT',
                    url : SRIUnsaConfig.SRIUnsaUrlServicio + '/usuarios/updateUsuarioPersona',
                    data : request
            }).success(function(response) {
                    deferred.resolve(response);
            }).error(function(response) {			
                    deferred.reject(response);
            });
            return deferred.promise;
	};

	this.deleteUsuario = function(request) {
            $log.debug("Usuario Service - Delete Usuario");

            var deferred = $q.defer();
            $http({
                    method : 'PUT',
                    url : SRIUnsaConfig.SRIUnsaUrlServicio + '/usuarios/deleteUsuarioPersona',
                    data : request
            }).success(function(response) {
                    deferred.resolve(response);
            }).error(function(response) {			
                    deferred.reject(response);
            });
            return deferred.promise;
	};
        
        this.GetByIdUsuario = function(idUsuario) {
            $log.debug("Usuario Service - get Usuarios");

            var deferred = $q.defer();
            $http({
                    method : 'GET',
                    url : SRIUnsaConfig.SRIUnsaUrlServicio + '/usuarios/getByIdUsuario/' + idUsuario
            }).success(function(response) {
                    deferred.resolve(response);
            }).error(function(response) {			
                    deferred.reject(response);
            });
            return deferred.promise;
	};
        
        this.GetActoresByIdUsuario = function(idUsuario) {
            $log.debug("Usuario Service - GetActoresByIdUsuario");

            var deferred = $q.defer();
            $http({
                    method : 'GET',
                    url : SRIUnsaConfig.SRIUnsaUrlServicio + '/usuarios/GetActoresByIdUsuario/' + idUsuario
            }).success(function(response) {
                    deferred.resolve(response);
            }).error(function(response) {			
                    deferred.reject(response);
            });
            return deferred.promise;
	};
        
        this.GetUsuariosColor = function(request) {
            $log.debug("UsuarioService - GetUsuariosColor");

            var deferred = $q.defer();
            $http({
                    method : 'POST',
                    url : SRIUnsaConfig.SRIUnsaUrlServicio + '/usuarios/GetUsuariosColor',
                    data : request
            }).success(function(response) {
                    deferred.resolve(response);
            }).error(function(response) {			
                    deferred.reject(response);
            });
            return deferred.promise;
	};
        
        this.GetTotalDocentesActivosInactivos = function() {
            $log.debug("Usuario Service - GetTotalDocentesActivosInactivos");

            var deferred = $q.defer();
            $http({
                    method : 'GET',
                    url : SRIUnsaConfig.SRIUnsaUrlServicio + '/usuarios/GetTotalDocentesActivosInactivos',
            }).success(function(response) {
                    deferred.resolve(response);
            }).error(function(response) {			
                    deferred.reject(response);
            });
            return deferred.promise;
	};
        
        this.GetTotalActivosInactivosByFacultad = function(idFacultad) {
            $log.debug("Usuario Service - GetTotalDocentesActivosInactivos");

            var deferred = $q.defer();
            $http({
                    method : 'GET',
                    url : SRIUnsaConfig.SRIUnsaUrlServicio + '/usuarios/GetTotalActivosInactivosByFacultad/' + idFacultad
            }).success(function(response) {
                    deferred.resolve(response);
            }).error(function(response) {			
                    deferred.reject(response);
            });
            return deferred.promise;
	};
        
        this.GetUsuarioHome = function(idUsuario) {
            $log.debug("Usuario Service - GetUsuarioHome");

            var deferred = $q.defer();
            $http({
                    method : 'GET',
                    url : SRIUnsaConfig.SRIUnsaUrlServicio + '/usuarios/GetUsuarioHome/' + idUsuario
            }).success(function(response) {
                    deferred.resolve(response);
            }).error(function(response) {			
                    deferred.reject(response);
            });
            return deferred.promise;
	};
});