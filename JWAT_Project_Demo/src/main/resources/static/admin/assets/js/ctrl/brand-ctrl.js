app.controller("brand-ctrl",function($scope, $http){
	let url = "/api/brands";
    $scope.brands = [];
    $scope.brand = {};
    $scope.chon = false;
    $scope.start = 0;
    $scope.pageSize = 5;
    
    //get danh sach brand
    $http.get(url).then(resp => {
        $scope.brands = resp.data;
    });
    
    //get 1 brand
    $scope.edit = function(id){
        var urlEdit = `${url}/${id}`;
        $http.get(urlEdit).then(resp => {
            $scope.brand = resp.data;
            $scope.chon = true;
        }).catch(error => {
            if(error.status == 404){
                alert("Không tồn tại brand "+id);
            }
        });
    }
    
    //upload img
    $scope.imageChanged = function(files){
        var urlUpload = "/api/upload/brand"
        var form = new FormData();
        form.append("file",files[0]);
        $http.post(urlUpload, form, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        }).then(resp => {
            $scope.brand.image = resp.data.filename;
        }).catch(error => {
            console.log("error",error);
        });
    }
    
    $scope.update = function(id){
        var urlUpdate = `${url}/${id}`;
        var data = angular.copy($scope.brand);
        var index = $scope.brands.findIndex(b => b.id == id);
        $http.put(urlUpdate, data).then(resp => {
            $scope.brands[index] = resp.data;
        }).catch(error => {
            if(error.status == 404){
                alert("Không tồn tại brand "+id);
            }
        });
    }
    
    $scope.create = function(){
        var data = angular.copy($scope.brand);
        $http.post(url, data).then(resp => {
            $scope.brands.push(resp.data);
            $scope.reset();
        }).catch(error => {   
            if(error.status == 400){
                alert("Đã tồn tại brand "+$scope.brand.id);
            }
            console.log("error ",error);
        });
    };
    $scope.reset = function(){
        $scope.brand = {
            id: "",
            name: "",
            image: "logo.jpg"
        };
        $scope.chon = false;
    }
    
    $scope.delete = function(id){
        var urlDelete = `${url}/${id}`;
        $http.delete(urlDelete).then(resp => {
            var index = $scope.brands.findIndex(b => b.id == id);
            $scope.brands.splice(index, 1);
            $scope.reset();
        }).catch(error => {
            if(error.status == 404){
                alert("Không tồn tại brand "+$scope.brand.id);
            }
        });
    }
    
    $scope.search = function(kw){
        if(kw != null){
            var urlSearch = `${url}/search?kw=${kw}`;
            $http.get(urlSearch).then(resp => {
                $scope.brands = resp.data;
            });
        }
    }
    
    $scope.next = function(){
        if($scope.start < $scope.brands.length - $scope.pageSize){
            $scope.start += $scope.pageSize;
        }
    }
    $scope.prev = function(){
        if($scope.start > 0){
            $scope.start -= $scope.pageSize;
        }
    }
    $scope.last = function(){
        var sotrang = Math.ceil($scope.brands.length / $scope.pageSize);
        $scope.start = (sotrang - 1) * $scope.pageSize;
    }
    $scope.first = function(){
        $scope.start = 0;
    }
    
    $scope.reset();
});