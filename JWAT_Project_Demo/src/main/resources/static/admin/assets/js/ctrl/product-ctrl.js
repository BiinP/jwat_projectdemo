app.controller("product-ctrl", function ($scope, $http) {
	let url = "/api/products";
	let urlBrand = '/api/brands';
	let urlCate = '/api/categories';
    $scope.product = {};
    $scope.products = [];
    $scope.chon = false;
    $scope.start = 0;
    $scope.pageSize = 5;

	//LOAD DS PRODUCT
	$http.get(url).then(resp =>{
		$scope.products = resp.data;
		$scope.products.forEach(p => {
        	p.createDate = new Date(p.createDate);
        });
	});
	
	//LOAD DS BRAND
	$http.get(urlBrand).then(resp => {
		$scope.brands = resp.data;
	});
	
	//LOAD DS CATEGORY
	$http.get(urlCate).then(resp => {
		$scope.cates = resp.data;
	});
	
	$scope.reset = function(){
		$scope.chon = false;
		$scope.product = {
			"id": -1,
        	"name": "",
        	"price": 0,
	        "createDate": new Date(),
	        "available": true,
	        "images": "logo.jpg",
	        "brand": {
	            "id": "apple",
	        },
	        "category": {
	            "id": "DT",
	        }
		}
	}
	
	$scope.edit = function(id){
		var urlEdit = `${url}/${id}`;
		$http.get(urlEdit).then(resp => {
			$scope.product = resp.data;
			$scope.product.createDate = new Date($scope.product.createDate);
			$scope.chon = true;
		}).catch(error => {
			if(error.status == 404){
				alert('Không tìm thấy sản phẩm '+id);
			} 
		});
	}
	
	//upload img
    $scope.imageChanged = function(files){
        var urlUpload = "/api/upload/product"
        var form = new FormData();
        form.append("file",files[0]);
        $http.post(urlUpload, form, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        }).then(resp => {
            $scope.product.images = resp.data.filename;
        }).catch(error => {
            console.log("error",error);
        });
    }
	
    //HAM THEM MOI
    $scope.create = function () {
        var product = angular.copy($scope.product);
        $http.post(url, product).then(resp => {
            $scope.products.push(resp.data);
            alert("Thêm sản phẩm thành công")
            $scope.reset();
        }).catch(error => {
            alert("Thêm sản phẩm thất bại");
            console.log(error)
        })
    };
    //HAM UPDATE
    $scope.update = function (id) {
    	var urlUpdate = `${url}/${id}`;
        var data = angular.copy($scope.product);
        $http.put(urlUpdate, data).then(resp => {
            var index = $scope.products.findIndex(p => p.id == id);
            $scope.products[index] = resp.data;
            alert("Cập nhật sản phẩm thành công")
        }).catch(error => {
            if(error.status == 404){
            	alert("Không tìm thấy sản phẩm "+id);
            }
            alert("Cập nhật sản phẩm thất bại");
            console.log(error)
        })
    };
    //HAM DELETE
    $scope.delete = function (id) {
    	var urlDelete = `${url}/${id}`
        $http.delete(urlDelete).then(resp => {
            var index = $scope.products.findIndex(p => p.id == id);
            $scope.products.splice(index, 1);
            alert("Xóa sản phẩm thành công");
            $scope.reset();
        }).catch(error => {
            alert("Xóa sản phẩm thất bại");
            console.log(error);
        })
    }
    //TIM KIEM PRODUCT
    $scope.search = function (kw) {
    	if(kw != null){
	        $http.get(`${url}/search?kw=${kw}`).then(resp => {
	            $scope.products = resp.data;
	        });
    	}
    }
    
    $scope.next = function(){
        if($scope.start < $scope.products.length - $scope.pageSize){
            $scope.start += $scope.pageSize;
        }
    }
    $scope.prev = function(){
        if($scope.start > 0){
            $scope.start -= $scope.pageSize;
        }
    }
    $scope.last = function(){
        var sotrang = Math.ceil($scope.products.length / $scope.pageSize);
        $scope.start = (sotrang - 1) * $scope.pageSize;
    }
    $scope.first = function(){
        $scope.start = 0;
    }
    $scope.reset();
});