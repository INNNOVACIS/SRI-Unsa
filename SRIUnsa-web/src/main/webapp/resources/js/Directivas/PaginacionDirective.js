investigacionApp.directive('paginacion', ['$parse', function ($parse) {
     return {
        restrict: 'AE',
        template: '<nav aria-label="Page navigation">'
                        +'<ul class="pagination">'
                        +'    <li class="disabled">'
                        +'        <a href="#" aria-label="Anterior">'
                        +'            <span aria-hidden="true">&laquo;</span>'
                        +'        </a>'
                        +'    </li>'
                        +'    <li class="{{pagina.active}}" ng-repeat="pagina in paginas">'
                        +'        <a ng-click="changePagina(pagina)">{{ pagina.numero }}</a>'
                        +'    </li>'
                        +'   <li>'
                        +'        <a aria-label="Siguiente">'
                        +'          <span aria-hidden="true">&raquo;</span>'
                        +'        </a>'
                        +'    </li>'
                        +'</ul>'
                    +'</nav>',
        scope: { 
            contenidoPaginacion: '@contenidoPaginacion',
            clickPaginacion: '&',
            method: '&' 
        },
        link: function(scope, element, attrs) {
            
            scope.paginas = [];
            
            console.log("Current Page :: ", attrs.currentPage);
            console.log("Page Size    :: ", attrs.pageSize);
            console.log("Total        :: ", attrs.total);
            console.log("Total        :: ", attrs.data);
            console.log("paginas      :: ", scope.paginas);
            
            scope.changePagina = function(pagina){
                $(element).on('click', function(e) {
                    console.log("directiva :: ", pagina);
                    scope.clickPaginacion({page : pagina.numero});
                });
                console.log("click en pagina # :: ", pagina);
                
                verificarPaginaActual(parseInt(attrs.pageSize), parseInt(attrs.total), parseInt(pagina.numero));
                setPaginaActual(pagina.numero);
                //rangoPaginas(attrs.pageSize, attrs.total, attrs.currentPage);
            };
            
            var verificarPaginaActual = function(rango, total, paginaActual){
                if((scope.paginas[rango-1].numero - paginaActual) < 1 ){
                    scope.paginas = [];
                    if(paginaActual + rango > total ){ // (total / rango)
                        paginaActual = (total - rango);
                    }
                    scope.paginas = getNumeroPaginas(paginaActual, rango);
                } else {
                    if((paginaActual - scope.paginas[0].numero) < 1 ){
                        scope.paginas = [];
                        if(paginaActual - rango <= 0){
                            paginaActual = 1;
                        } else {
                            paginaActual = paginaActual - rango + 1;
                        }
                        scope.paginas = getNumeroPaginas(paginaActual, rango);
                    }
                }
            };
            
            var getNumeroPaginas = function(paginaActual, rango){
                var paginas = [];
                for(var i = paginaActual; i < (paginaActual + rango); i++) {
                    var pagina = {};
                    pagina.active = "";
                    pagina.numero = i;
                    paginas.push(pagina);
                }
                return paginas;
            };
            
            var setPaginaActual = function(currentPage){
                angular.forEach(scope.paginas, function(value, key){
                    if(value.numero === currentPage)
                        value.active = "active";
                    else
                        value.active = "";
                });
            };
            
            scope.paginas = getNumeroPaginas(parseInt(attrs.currentPage), parseInt(attrs.pageSize));
            verificarPaginaActual(parseInt(attrs.pageSize), parseInt(attrs.total), parseInt(attrs.currentPage));
            setPaginaActual(parseInt(attrs.currentPage));
        }
    };
}]);
