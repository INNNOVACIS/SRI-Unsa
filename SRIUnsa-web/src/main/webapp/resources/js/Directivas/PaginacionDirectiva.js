investigacionApp.directive('pagination', ['$parse', function ($parse) {
     return {
        restrict: 'EA',
        template: '<nav aria-label="Page navigation">'
                        +'<div class="col-md-1 col-sm-2 col-xs-2" style="margin-top:20px;">'
                        +'    <select style="width: 120%; padding: 6px 3px;" class="form-control" ng-model="rango" ng-options="opt as opt for opt in rangoPaginas" ng-init="rango=rangoPaginas[0]" ng-change=changeRango(rango)>'
                        +'    </select>'
                        +'</div>'
                        +'<div class="col-md-6 col-sm-5">'
                        +'  <ul class="pagination">'
                        +'    <li ng-class="{active: page.activo}" ng-repeat="page in paginas track by $index">'
                        +'        <a ng-click="selectPage(page.numero, $event)">{{page.texto}}</a>'
                        +'    </li>'
                        +'  </ul>'
                        +'</div>'
                        +'<div class="col-md-5 col-sm-5" style="margin-top:25px;">'
                        +'  <p style="text-align:right;">{{inicio}} - {{fin}} de {{total}} Registros<p>'
                        +'</div>'
                    +'</nav>',
        scope: { 
            rangoPaginas: "=",
            currentRango: "=",
            numPages: "=",
            currentPage: "=",
            maxSize: "=",
            total: "=",
            onSelectPage: "&"
        },
        link: function(scope, element, attrs) {
            scope.$watch("numPages + currentPage + maxSize", function(){
                scope.inicio = (scope.currentPage - 1) * scope.currentRango + 1;
                scope.fin = (scope.currentRango * scope.currentPage) <= scope.total ? (scope.currentRango * scope.currentPage) : scope.total;
                var asignar = function(numero, texto, activo, inactivo) {
                    return {
                        numero: numero,
                        texto: texto,
                        activo: activo,
                        inactivo: inactivo
                    };
                };
                scope.paginas = [];
                var inicio = 1;
                var final = scope.numPages;
                if(scope.maxSize < scope.numPages){
                    inicio = Math.max(scope.currentPage - Math.floor(scope.maxSize / 2), 1);
                    final = inicio + scope.maxSize - 1;
                    if (final > scope.numPages){ 
                        final = scope.numPages;
                        inicio = final - scope.maxSize + 1;
                    }
                }
                for (var i = inicio; final >= i; i++) {
                    var p = asignar(i, i, scope.isActive(i), false);
                    scope.paginas.push(p);
                }
                if(false){
                    var d = asignar(scope.currentPage - 1, "anterior", false, scope.noPrevious());
                    scope.pages.unshift(d);
                    var f = asignar(scope.currentPage + 1, "siguiente", false, scope.noNext());
                    scope.pages.push(f);
                }
                if(scope.currentPage > scope.numPages){
                    scope.selectPage(scope.numPages);
                }
                
            }), scope.noPrevious = function() {
                return 1 === scope.currentPage;
            }, scope.noNext = function() {
                return scope.currentPage === scope.numPages;
            }, scope.isActive = function(pagina) {
                return scope.currentPage === pagina;
            }, scope.isActiveRange = function(rango) {
                return scope.currentRango === rango;
            },scope.selectPage = function(pagina) {
                if(!scope.isActive(pagina) && pagina > 0 && scope.numPages >= pagina){
                    scope.currentPage = pagina;
                }
            }, scope.changeRango = function(rango) {
                if(!scope.isActiveRange(rango)){
                    scope.currentRango = rango;
                }
            };
        }
    };
}]);
