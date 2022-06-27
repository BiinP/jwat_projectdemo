app.controller("category-ctrl",function($scope, $http){
	let url = "/api/categories";
    $scope.cates = [];
    $scope.cate = {};
    $scope.chon = false;
    
    //get danh sach Category
    $http.get(url).then(resp => {
        $scope.cates = resp.data;
    });
    
    //get 1 Category
    $scope.edit = function(id){
        var urlEdit = `${url}/${id}`;
        $http.get(urlEdit).then(resp => {
            $scope.cate = resp.data;
            $scope.chon = true;
        }).catch(error => {
            if(error.status == 404){
                alert("Không tồn tại category "+$scope.cate.id);
            }
        });
    }
    
    $scope.update = function(id){
        var urlUpdate = `${url}/${id}`;
        var data = angular.copy($scope.cate);
        var index = $scope.cates.findIndex(c => c.id == id);
        $http.put(urlUpdate, data).then(resp => {
            $scope.cates[index] = resp.data;
        }).catch(error => {
            if(error.status == 404){
                alert("Không tồn tại category "+$scope.cate.id);
            }
        });
    }
    
    $scope.create = function(){
        var data = angular.copy($scope.cate);
        $http.post(url, data).then(resp => {
            $scope.cates.push(resp.data);
            $scope.reset();
        }).catch(error => {   
            if(error.status == 400){
                alert("Đã tồn tại category "+$scope.cate.id);
            }
            console.log("error ",error);
        });
    };
    $scope.reset = function(){
        $scope.cate = {
            id: "",
            name: ""
        };
        $scope.chon = false;
    }
    
    $scope.delete = function(id){
        var urlDelete = `${url}/${id}`;
        var index = $scope.cates.findIndex(c => c.id == id);
        $http.delete(urlDelete).then(resp => {
            $scope.cates.splice(index, 1);
            $scope.reset();
        }).catch(error => {
            if(error.status == 404){
                alert("Không tồn tại category "+$scope.cate.id);
            }
        });
    }
    
    $scope.search = function(kw){
        if(kw != null){
            var urlSearch = `${url}/search?kw=${kw}`;
            $http.get(urlSearch).then(resp => {
                $scope.cates = resp.data;
            });
        }
    }
    
    $scope.reset();
});