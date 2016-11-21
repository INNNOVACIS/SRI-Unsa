investigacionApp.factory('SharedService', function() {

    return {
        userAutenticado : sessvars.autenticado,
        nombreRol : sessvars.nombreRol,
        nombreUsuario : sessvars.nombreUsuario,
        idRol : sessvars.idRol,
        idUsuario : sessvars.idUsuario,
        privilegios : sessvars.privilegios,
        htmlMenu : sessvars.htmlMenu,
        stringMenu : sessvars.stringMenu
    };
});