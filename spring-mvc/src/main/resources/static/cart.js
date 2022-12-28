angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8080/app/api/v1';

    $scope.loadProducts = function (pageIndex = 1) {
        $http({
            url: contextPath + '/products/purchases',
            method: 'GET',
        }).then(function (response) {
            $scope.ProductsPage = response.data;
        });
    };

    $scope.loadProducts();
});