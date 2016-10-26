/**
 * Archivo Service
 */
investigacionApp.service("ArchivosService", function(SRIUnsaConfig, $log, $http, $q) {
	
	this.getArchivos = function() {
		$log.debug("Archivo Service - get Archivos");
		
		var deferred = $q.defer();
		$http({
			method : 'GET',
			url : SRIUnsaConfig.SRIUnsaUrlServicio + '/files/listarArchivos',
		}).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {			
			deferred.reject(response);
		});
		return deferred.promise;
	};
        
        this.descargarArchivo = function(id) {
            var deferred = $q.defer();
		$http({
                    method : 'GET',
                    url : SRIUnsaConfig.SRIUnsaUrlServicio + '/files/' + id,
                    responseType: 'arraybuffer'
		}).success(function(data, status, headers) {
                    headers = headers();
 
                    var filename = headers['content-disposition'];//['x-filename'];
                    var contentType = headers['content-type'];

                    var linkElement = document.createElement('a');
                    try {
                        var blob = new Blob([data], { type: contentType });
                        var url = window.URL.createObjectURL(blob);

                        linkElement.setAttribute('href', url);
                        linkElement.setAttribute("download", filename);

                        var clickEvent = new MouseEvent("click", {
                            "view": window,
                            "bubbles": true,
                            "cancelable": false
                        });
                        linkElement.dispatchEvent(clickEvent);
                    } catch (ex) {
                        console.log(ex);
                    }
                    deferred.resolve(data);
		}).error(function(response) {			
                    deferred.reject(response);
		});
            return deferred.promise;
        }

	this.subirArchivo = function(request) {
            $log.debug("Home Service - SendFile");
		
            var deferred = $q.defer();
            $http({
                method : 'POST',
                url : SRIUnsaConfig.SRIUnsaUrlServicio + '/files/actualizarArchivos',
                data : request,
                transformRequest: angular.identity,
                headers: {'Content-Type': undefined}
            }).success(function(response) {                
                deferred.resolve(response);
            }).error(function(response) {
                deferred.reject(response);
            });
            return deferred.promise;
        }
});