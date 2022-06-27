let app = angular.module("admin-app",["ngRoute"]);
app.config(function($routeProvider){
    $routeProvider
        .when("/category",{
            templateUrl:"/admin/assets/layout/category.html",
            controller:"category-ctrl"
        })
        .when("/brand",{
            templateUrl:"/admin/assets/layout/brand.html",
            controller:"brand-ctrl"
        })
        .when("/product",{
            templateUrl:"/admin/assets/layout/product.html",
            controller:"product-ctrl"
        })
        .otherwise({
            templateUrl:"/admin/assets/layout/category.html",
            controller:"category-ctrl"
        })
});