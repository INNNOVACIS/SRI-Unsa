/**
 * ActividadesDocentesService
 */
investigacionApp.service("ActividadesDocentesService", function(SRIUnsaConfig, $log, $http, $q) {
	
    this.GetActividadesByDocente = function(request) {
        $log.debug("RelacionDocentesService - GetDocentes");

        var deferred = $q.defer();
        $http({
            method : 'POST',
            url : SRIUnsaConfig.SRIUnsaUrlServicio + '/reportes/getActividadesDocentes',
            data : request
        }).success(function(response) {
            deferred.resolve(response);
        }).error(function(response) {			
            deferred.reject(response);
        });
        return deferred.promise;
    };
    
    this.GetActividadesByDocentesDetalle = function() {
        $log.debug("RelacionDocentesService - GetActividadesByDocentesDetalle");

        var deferred = $q.defer();
        $http({
            method : 'GET',
            url : SRIUnsaConfig.SRIUnsaUrlServicio + '/reportes/getActividadesDocentesDetalle'
        }).success(function(response) {
            deferred.resolve(response);
        }).error(function(response) {			
            deferred.reject(response);
        });
        return deferred.promise;
    };
    
    this.descargarPDF = function(id) {
        var deferred = $q.defer();
            $http({
                method : 'GET',
                url : SRIUnsaConfig.SRIUnsaUrlServicio + '/files/916',
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
    };
    
    this.descargarExcel = function(id) {
        var deferred = $q.defer();
            $http({
                method : 'GET',
                url : SRIUnsaConfig.SRIUnsaUrlServicio + '/files/917',
                responseType: 'arraybuffer'
            }).success(function(data, status, headers) {
                headers = headers();

                var filename = headers['content-disposition'];
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
    };
    
});