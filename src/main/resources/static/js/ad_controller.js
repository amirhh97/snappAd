'use strict'; app.config(function($routeProvider,localStorageServiceProvider){

    $routeProvider

        .when('/',{
            templateUrl:'pages/home.html',
            controller:'AdController'
        })

        .when('/addReg',{
            templateUrl:'pages/addReg.html',
            controller:'AdController'
        })

        .when('/showReg',{
            templateUrl:'pages/showReg.html',
            controller:'AdController'
        })

        .otherwise({redirectTo:'/'});

    localStorageServiceProvider.setPrefix('demoPrefix');
    // localStorageServiceProvider.setStorageCookieDomain('example.com');
    // localStorageServiceProvider.setStorageType('sessionStorage');
});



angular.module('myApp').controller('AdController', ['$scope','localStorageService', 'AdService','$mdDialog','$http','$q', function($scope,localStorageService,AdService,$mdDialog,$http,$q,$interval){
    /*$scope.localStorageDemo = localStorageService.get('localStorageDemo');

     $scope.$watch('localStorageDemo', function(value){
     localStorageService.set('localStorageDemo',value);
     $scope.localStorageDemoValue = localStorageService.get('localStorageDemo');
     });

     $scope.storageType = 'Local storage';

     if (localStorageService.getStorageType().indexOf('session') >= 0) {
     $scope.storageType = 'Session storage';
     }

     if (!localStorageService.isSupported) {
     $scope.storageType = 'Cookie';
     }

     $scope.$watch(function(){
     return localStorageService.get('localStorageDemo');
     }, function(value){
     $scope.localStorageDemo = value;
     });*/

    //$scope.clearAll = localStorageService.clearAll;

    $scope.vm = {
        currentImagesIndex: 0,
        currentImgSrc: 'http://dummyimage.com/128x128/ccc/fff.png&text=loading...',
        images: [
            'http://dummyimage.com/128x96/4072b4/fff.png&text=one',
            'http://dummyimage.com/96x128/4072b4/fff.png&text=two',
            'http://dummyimage.com/96x64/4072b4/fff.png&text=three'
        ],
        loadImage: function() {
            this.currentImgSrc = this.images[this.currentImagesIndex];
        },
        navigateNext: function() {
            this.currentImagesIndex++;
            if (this.currentImagesIndex >= this.images.length) {
                this.currentImagesIndex = 0;
            }
            this.loadImage();
        },
        navigatePrevious: function() {
            this.currentImagesIndex--;
            if (this.currentImagesIndex <= 0) {
                this.currentImagesIndex = 0;
            }
            this.loadImage();
        }
    };
    $scope.vm.loadImage();


    $scope.showSearch=false;
    $scope.firstPage=true;
    $scope.adreg=false;
    $scope.setAdRegFlag=function(){
        $scope.firstPage=false;
        $scope.adreg=true;
    }
    var self = this;

    $scope.largecategory=[
        {
            "cat":"املاک",
            "catId":2,
            "totalNumber":"20,720",
            "icon":"home-5-32.ico",
            "childs":["فروش مسکونی","رهن و اجاره مسکونی","فروش اداری و تجاری","رهن و اجاره اداری و تجاری","زمین و باغ","سایر املاک"]
        },

        {
            "cat":"وسایل نقلیه",
            "catId":3,
            "icon":"car-4-32.ico",
            "totalNumber":"1,493",
            "childs":["خودرو","موتور سیکلت","خودرو کلاسیک","سنگین و نیمه سنگین","لوازم و قطعات وسایل نقلیه","کشاورزی و عمرانی"]
        },

        {
            "cat":"لوازم الکترونیکی",
            "catId":4,
            "icon":"laptop-3-32.ico",
            "totalNumber":"8,493",
            "childs":["لپ تاپ و کامپیوتر","موبایل و تبلت","دوربین عکاسی و فیلمبرداری و لوازم","کنسول بازی و لوازم","صوتی و تصویری","بازی های اینترنتی"]
        }];

    $scope.smallcategory=[
        {
            "cat":"استخدام وکاریابی",
            "catId":5,
            "totalNumber":"20,720",
            "icon":"user-8-32.ico"

        },

        {
            "cat":"لوازم خانه",
            "catId":6,
            "icon":"sofa-32.ico",
            "totalNumber":"1,493"
        },

        {
            "cat":"لوازم شخصی",
            "catId":7,
            "icon":"user-2-32.ico",
            "totalNumber":"493"
        },
        {
            "cat":"ورزش و فراغت",
            "catId":7,
            "icon":"umbrella-2-32.ico",
            "totalNumber":"493"
        }

    ]

    $scope.ad={id:null,produceYear:'',brand:'',kilometer:'',owner:'',adsCity:'',adsDescribe:'',adsState:'',adsDate:'',adsId:'',adsTitle:'',cat:'21',price:'',image_url:'',manufacturer:'',veichelColor:'',lenght:'',numberOfRooms:'',mortgage:'',rent:'',district:''};
    $scope.user={name:'',lastName:'',email:'',userPass:'',mobileNumber:''};
    $scope.ads=[];
    $scope.toppings=[];
    $scope.cities=[];
    $scope.states =[];
    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;
    fetchAllAds();
    fetchAllCats();
    fetchAllcities();
    fetchAllStates();
    function fetchAllAds(){
        AdService.fetchAllAds()
            .then(
                function(d) {
                    $scope.ads = d;
                },
                function(errResponse){
                    console.error('Error while fetching ads');
                }
            );
    }
    function fetchAllcities(){
        AdService.fetchAllcities()
            .then(
                function(d) {
                    $scope.cities = d;
                },
                function(errResponse){
                    console.error('Error while fetching ads');
                }
            );
    }
    function fetchAllStates(){
        AdService.fetchAllstates()
            .then(
                function(d) {
                    $scope.states = d;
                },
                function(errResponse){
                    console.error('Error while fetching ads');
                }
            );
    }
    function createAd(ad){
        AdService.createAd(ad)
            .then(
                fetchAllAds,
                function(errResponse){
                    console.error('Error while creating ad');
                }
            );
    }

    function updateAd(ad, id){
        AdService.updateAd(ad, id)
            .then(
                fetchAllAds,
                function(errResponse){
                    console.error('Error while updating ad');
                }
            );
    }

    function deleteAd(id){
        AdService.deleteAd(id)
            .then(
                fetchAllAds,
                function(errResponse){
                    console.error('Error while deleting User');
                }
            );
    }

    function submit() {
        if(self.ad.id===null){
            console.log('Saving New Ad', self.ad);
            createAd(self.ad);
        }else{
            updateAd(self.ad, self.ad.id);
            console.log('Ad updated with id ', self.ad.id);
        }
        reset();
    }

    function edit(id){
        console.log('id to be edited', id);
        for(var i = 0; i < self.ad.length; i++){
            if(self.ads[i].id === id) {
                self.ad = angular.copy(self.ads[i]);
                break;
            }
        }
    }

    function remove(id){
        console.log('id to be deleted', id);
        if(self.ad.id === id) {
            reset();
        }
        deleteAd(id);
    }

    function reset(){
        self.ad={id:null,username:'',address:'',email:''};
        $scope.myForm.$setPristine(); //reset Form
    }
    ///// az inja b bad jadide
    function fetchAllCats()
    {
        AdService.fetchAllCats()
            .then(
                function(data){
                    $scope.toppings=data;
                },
                function (eror)
                {
                    console.log("eror");
                }
            );}
    /*$scope.sendLoginData=function (){
     AdService.sendLoginData($scope.user).then(function (successResponse) {
     $scope.closePopUp(null);
     $cookies.put('anus',successResponse.token);
     },function (failResponse) {
     console.log("token",failResponse);
     });

     }
     $scope.sendRegisterData=function (){
     AdService.sendRegisterData($scope.user)
     .then(
     function(Response){
     console.error('Error while creating user');
     }
     ,function (errResponse) {
     console.error('Error while creating user');
     } );
     }

     $scope.sendRegisterAdData=function (){
     var token=$cookies.get("anus");
     if (token!== undefined) {
     AdService.sendRegisterAdData($scope.ad,token)
     .then(fetchAllAds,
     function(errResponse){
     console.error('Error while create Ad');
     }
     );
     } else {
     $scope.showPopUp();
     }
     }*/
    $scope.$watch('files02.length',function(newVal,oldVal){
        console.log($scope.files02);
    });
    $scope.optoin08 = {
        "browseIconCls":"myBrowse",
        "removeIconCls":"myRemove",
        "captionIconCls":"myCaption",
        "submitIconCls":"mySubmit",
        "unknowIconCls":"myUnknow"
    };
    $scope.onFileClick = function(obj,idx){
        console.log(obj);
    };

    $scope.onSubmitClick = function(files) {
        console.log(files);
    }

    $scope.onSubmitFilesChange = function() {
        console.log($scope.submitButtonFiles);
    }

    $scope.onFileRemove = function(obj,idx){
        console.log(obj);
    };

    $scope.homeImg = {};
    // $scope.homeImg.img = "../pics/80e855b02af8d33add046114c56447ae.jpg";
    $scope.homeImg.img = "../pics/house_animation.gif";

    $scope.carImag = {};
    $scope.carImag.img = "../pics/car1.png";
    // $scope.carImag.img = "../pics/car-purple-icon.png";
    // $scope.carImag.img = "../pics/b191053d9fe05191e9bac4415ad265d8.jpg";
    //$scope.carImag.img = "../pics/images(3).jpeg";

    $scope.laptopImg = {};
    $scope.laptopImg.img = "../pics/laptop.jpg";

    $scope.jobImg = {};
    // $scope.jobImg.img = "../pics/77597064f5b9eb0.jpg";
    $scope.jobImg.img = "../pics/png-interview-images--540.png";

    $scope.homeAppliancesImg = {};
    //$scope.homeAppliancesImg.img = "../pics/e8cabbe6-e20f-42e6-9efd-ba702f12e43d2_21_2_104.png";
    //$scope.homeAppliancesImg.img = "../pics/homee.jpeg";
    $scope.homeAppliancesImg.img = "../pics/mebel_vector.jpg";

    $scope.personalImg = {};
    $scope.personalImg.img = "home_page_home_page_home_613736.png";
    // $scope.personalImg.img = "../pics/1657b578384d85f.jpg";


    $scope.showRegDialog=function(ev){
        $mdDialog.show({
            contentElement: '#registerDialog',
            parent: angular.element(document.body),
            targetEvent: ev,
            clickOutsideToClose: false
        });
    }
    $scope.showPopUp=function(ev){
        $mdDialog.show({
            contentElement: '#loginDialog',
            parent: angular.element(document.body),
            targetEvent: ev,
            clickOutsideToClose: false
        });
    }
    $scope.closePopUp=function(ev){
        $mdDialog.hide({

        });
    }

    $scope.selectboxchange=function(a){
        //alert(a)
        //alert($scope.cities.length)
        $scope.b=$scope.cities;
        for(var i=0;i<$scope.cities.length;i++){
            //alert($scope.cities[0].state)
            if($scope.cities[i].state_stateid==a)
                alert("dghjs")
        }
    }

    $scope.showAd=function(selected){
        alert(JSON.stringify(selected))
        localStorageService.set('selected',selected)
    }
    $scope.initRecords=function(){
        $scope.selectedAd = localStorageService.get('selected')
    }



}]);
