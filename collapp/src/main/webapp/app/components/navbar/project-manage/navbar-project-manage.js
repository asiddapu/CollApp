(function () {
    var components = angular.module('collapp.components');

    components.component('lvgNavbarProjectManage', {
        templateUrl: 'app/components/navbar/project-manage/navbar-project-manage.html',
        bindings: {
            project: '<'
        },
        controller: function (User, $mdSidenav, $state, $window) {
            var ctrl = this;

            ctrl.$state = $state;

            User.currentCachedUser().then(function (u) {
                ctrl.navbarUser = u;
            });

            ctrl.toggleSidebar = function () {
                $mdSidenav('left').toggle();
            };

            ctrl.login = function () {
                $window.location.href = User.loginUrl();
            };
        }
    });
}());
