investigacionApp.factory('SharedService', function($location) {

    return {
        userAutenticado : sessvars.autenticado,
        nombreRol : sessvars.nombreRol,
        nombreUsuario : sessvars.nombreUsuario,
        idRol : sessvars.idRol,
        idUsuario : sessvars.idUsuario,
        privilegios : sessvars.privilegios,
        htmlMenu : sessvars.htmlMenu,
        stringMenu : sessvars.stringMenu,
        isAutenticado : function(){
            return this.nombreUsuario === "" || this.nombreUsuario === undefined ? false : true;
        },
        isPermitido : function(url){
            var permitido = false;
            url = url.substring(1);
            angular.forEach(this.privilegios, function(valor, key){
                if(valor.surlPrivilegio !== null){
                    var privilegio = valor.surlPrivilegio.substring(1);
                    if(url.indexOf(privilegio) !== -1) {
                        permitido = true;
                    }
                }
            });
            return permitido;
        }
    };
});

investigacionApp.factory('SeguridadEnrutamiento', function($q, SharedService) {

    return {
        verificarPropiedades: function(){
            DataBase.getAllDatabaseDevice(function (results){
                $rootScope.load = true;
                for(var i= 0 ; i < results.length; i++){	
                    switch (results.item(i).propertyId) {  
                        case "token":localInstance.token = results.item(i).value;break;  
                        case "nombreCompleto":localInstance.nombreCompleto = results.item(i).value;break;  
                        case "lastLogin":localInstance.lastLogin = results.item(i).value;break;  
                        case "usuarioRol":localInstance.usuarioRol = results.item(i).value ;break;  
                        case "homepath":localInstance.homepath = results.item(i).value ;break;  
                    }
                }
                console.log(localInstance.token);
                console.log($location.path());
                if ($location.path() == "/" && !localInstance.token){
                    console.log("login path");
                    $rootScope.$broadcast("loaded-token-mpa");
                    deferred.resolve('Resuelto');
                }
                else if(localInstance.token && localInstance.token!=""){
                    console.log("############");
                    console.log($location.path());
                    $rootScope.$broadcast("loaded-token-mpa");
                    deferred.resolve('Resuelto');
                    // Hacer las verificaciones para los paths
                }else{
                    deferred.reject('Rechazado');
                    $location.path("/"); 
                }
            });
            
            return deferred.promise;
        }
    };
});