angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8080/app/api/v1';

    $scope.loadProducts = function (pageIndex = 1) {
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params: {
                title_part: $scope.filter ? $scope.filter.title_part : null,
                min_price: $scope.filter ? $scope.filter.min_price : null,
                max_price: $scope.filter ? $scope.filter.max_price : null
            }
        }).then(function (response) {
            $scope.ProductsPage = response.data;
        });
    };
    $scope.addToCart = function (productId) {
        $http.get(contextPath + '/products/buy/' + productId)
            .then(function (response) {
            $scope.ProductsPage = response.data; // метод для примера, нужно изменить.
        });
    }
    $scope.loadProducts();
});