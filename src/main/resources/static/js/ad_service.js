'use strict';
angular.module('myApp').factory('AdService', ['$http', '$q', function($http, $q){
	var REST_SERVICE_URI = 'http://localhost:8080/';
	var LOGIN_URI='http://localhost:8080/user/';
	var ADREGISTER_URI='http://localhost:8080/ad/';
    var factory = {
        fetchAllAds: fetchAllAds,
        createAd: createAd,
        updateAd:updateAd,
        deleteAd:deleteAd,
		fetchAllCats:fetchAllCats,
		sendLoginData:sendLoginData,
		sendRegisterData:sendRegisterData,
		sendRegisterAdData:sendRegisterAdData,
		fetchAllcities:fetchAllcities,
		fetchAllstates:fetchAllstates
	};
    return factory;
	function fetchAllAds() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI+"/ad/")
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Ads');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
	function createAd(ad) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, ad)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while creating ad');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 function updateAd(ad, id) {
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+id, ad)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while updating ad');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 function deleteAd(id) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI+id)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while deleting ad');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
	/////az inja b bad jadide
 function fetchAllCats()
 {
	 var deferred = $q.defer();
        $http.get(REST_SERVICE_URI+"cat/")
            .then(
            function (response) {
                deferred.resolve(response.data);
				console.log(response.data);
            },
            function(errResponse){
                console.error('Error while fetching cats'+errResponse.data);
                deferred.reject(errResponse);
            }
        );
        return deferred.promise; }
		function sendLoginData(user){
		var deferred = $q.defer();

		$http({
			method: 'POST',
			url: LOGIN_URI+'login',
			data: $.param({ mobileNumber:user.mobileNumber, userPass:  user.userPass }),
			headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
		}).then(
			function(res) {
				console.log('succes !', res.data);
				deferred.resolve(res.data);
			},
			function(err) {
				console.log('error...', err);
				deferred.reject(err);
			}
		);
		return deferred.promise;
			}
				function sendRegisterData(user){
		var deferred = $q.defer();

		$http({
			method: 'POST',
			url: LOGIN_URI+'reg',
			data: $.param({ firstName:user.username, lastName:user.lastName, usermail: user.usermail, password: user.userpass, mobileNumber: user.usermobilenum }),
			headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
		}).then(
			function(res) {
				console.log('succes !', res.data);
				deferred.resolve(res.data);
			},
			function(err) {
				console.log('error...', err);
				deferred.resolve(err);
			}
		);
		return deferred.promise;
			}

    function sendRegisterAdData(ad, token) {
		var deferred = $q.defer();

		$http({
			method: 'POST',
			url: ADREGISTER_URI+"reg",
			data: $.param({ Describe:ad.adsDescribe,State:ad.adsState, City:ad.adsCity, district: 'blablabla',cat:ad.cat,manufacturer: ad.manufacturer,brand:ad.brand,poroductYear:ad.produceYear,title:ad.adsTitle,img:'ییلبلل'}),
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
                'Authorization': token
            }
		}).then(
			function(res) {
				console.log('succes !', res.data);
				deferred.resolve(res.data);
			},
			function(err) {
				console.log('error...', err);
				deferred.resolve(err);
			}
		);
		return deferred.promise;
			}
			 function fetchAllcities() {
        var deferred = $q.defer();
                 $http.get(REST_SERVICE_URI+"location/city")
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching cities');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
	 function fetchAllstates() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI+"location/state")
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching state');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
}]);