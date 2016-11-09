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
                console.log("click en pagina # :: ", pagina);
                rangoPaginas(attrs.pageSize, attrs.total, attrs.currentPage);
            };
            
            var rangoPaginas = function(rango, total, currentPage){
                if(parseInt(rango) < parseInt(total)){
                    var rangoReal = parseInt(rango);
                } else {
                    var rangoReal = parseInt(total);
                }
                for(var i = 0; i < rangoReal; i++) {
                    var pagina = {};
                    pagina.active = "";
                    pagina.numero = parseInt(currentPage) + i;
                    scope.paginas.push(pagina);
                }
            };
            rangoPaginas(attrs.pageSize, attrs.total, attrs.currentPage);
        }
    };
}]);
