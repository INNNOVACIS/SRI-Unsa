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
        popUp : {
            open:false,
            close:true,
            titulo:'',
            mensaje:'',
            url:'',
            op1:{open:false, txt:'', fun:function(){}},
            clearPopUp : function(){
                    this.open = false;
                    this.close = true;
                    this.titulo = '';
                    this.mensaje='';
                    this.op1.open= false;                  
                    this.op1.txt = '';
            },
            irPopUp : function(){
                this.clearPopUp();
                $location.path(this.url);
            },
            showPopUp : function(_titulo, _mensaje, _url, _op1){
                    this.open = true;
                    this.close = false;     
                    this.titulo = _titulo;
                    this.mensaje = _mensaje;
                    this.url = _url;
                    if(_op1){     
                        this.op1 = _op1;
                    }
            }
        },
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