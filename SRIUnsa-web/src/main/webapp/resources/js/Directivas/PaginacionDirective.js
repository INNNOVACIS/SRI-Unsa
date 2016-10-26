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
                        +'    <li ng-class="pagina.activo ?' + "'active'" + ": 'none'" + '" ng-repeat="pagina in paginas">'
                        +'        <a ng-click="changePagina(pagina)">{{ pagina.numero }}</a>'
                        +'    </li>'
                        +'   <li>'
                        +'        <a aria-label="Siguiente">'
                        +'          <span aria-hidden="true">&raquo;</span>'
                        +'        </a>'
                        +'    </li>'
                        +'</ul>'
                    +'</nav>',
        link: function(scope, element, attrs) {
            console.log("Current Page :: ", attrs.page);
            console.log("Page Size    :: ", attrs.pageSize);
            console.log("Total        :: ", attrs.total);
            console.log("paginas      :: ", scope.paginas);
            scope.pages = [1,2,3,4,5,6,7,8,9];
        }
    };
}]);
