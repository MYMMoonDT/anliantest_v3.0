'use strict';

/**
 * @ngdoc function
 * @name anliantestApp.controller:CustomerCtrl
 * @description
 * # CustomerCtrl
 * Controller of the anliantestApp
 */
angular.module('anliantestApp')
  .controller('CustomerCtrl', function ($scope, dialogs, Customer) {
    $scope.currPageNum = 1;
    $scope.totalItemNum = 0;
    $scope.pageChanged = function() {
      refreshData();
    };

    refreshData();

    $scope.showAddCustomerDialog = function () {
      var dialog = dialogs.create('template/at-customer-dialog.html', 'customerDialogCtrl', 
      {
        type: 'CREATE'
      }, 
      {
        size: 'lg',
        keyboard: true,
        backdrop: 'static',
        windowClass: 'model-overlay'
      });
      dialog.result.then(function (data) {
        var customer = new Customer();

        angular.extend(customer, data.item);
        
        customer.$save(function(){
          refreshData();
        });
      }, function () {

      });
    };

    $scope.editCustomer = function (customer) {
      var dialog = dialogs.create('template/at-customer-dialog.html', 'customerDialogCtrl', 
      {
        type: 'EDIT',
        item: customer
      }, 
      {
        size: 'lg',
        keyboard: true,
        backdrop: 'static',
        windowClass: 'model-overlay'
      });
      dialog.result.then(function (data) {
        var customer = new Customer();

        angular.extend(customer, data.item);
        
        customer.$update(function(){
          refreshData();
        });
      }, function () {

      });
    };
    
    $scope.removeCustomer = function (customer) {
      var dialog = dialogs.create('template/at-confirm-dialog.html', 'ConfirmCtrl', 
      {
        text: '确定要删除该客户?',
        type: 'DELETE'
      }, 
      {
        size: 'sm',
        keyboard: true,
        backdrop: 'static',
        windowClass: 'model-overlay'
      });
      dialog.result.then(function () {
        var _customer = new Customer();

        _customer.$delete({customerId:customer.id}, function(){
          refreshData();
        });

      }, function () {
      });
    };  

    function refreshData() {
      Customer.query({currPageNum:$scope.currPageNum}, function(data) {
        if(data != null) {
          $scope.customerList = data.data;
          $scope.currPageNum = data.currPageNum;
          $scope.totalItemNum = data.totalItemNum;
        }
      });
    }
  })
  .controller('customerDialogCtrl', function ($scope, $modalInstance, data) {
    $scope.data = data;

    if ($scope.data.type == 'CREATE') {
      $scope.data.item = {
        companyName: '',
        companyAddress: '',
        companyType: '',
        companyIndustry: '',
        manageEmployeeNum: '',
        manufactureEmployeeNum: '',

        customerHealthDep: {
          exist: false,
          healthDepName: '',
          healthEmpNum: 0
        },

        contactPersonItems: [],
        productItems: []
      };
    } else if ($scope.data.type == 'EDIT') {
      console.log($scope.data);
    }


    $scope.addContactPerson = function () {
      var item = {
        name: '',
        department: '',
        tel: ''
      };
      $scope.data.item.contactPersonItems.push(item);      
    };

    $scope.deleteContactPerson = function (item) {
      var index = $scope.data.item.contactPersonItems.indexOf(item);
      $scope.data.item.contactPersonItems.splice(index, 1);
    };

    $scope.addProduct = function () {
      var item = {
        name: '',
        annualOutput: '',
      };
      $scope.data.item.productItems.push(item);   
    };

    $scope.deleteProduct = function (item) {
      var index = $scope.data.item.productItems.indexOf(item);
      $scope.data.item.productItems.splice(index, 1);
    };


    $scope.cancel = function() {
      $modalInstance.dismiss('Canceled');
    };
    $scope.save = function() {
      $modalInstance.close($scope.data);
    };
  });
