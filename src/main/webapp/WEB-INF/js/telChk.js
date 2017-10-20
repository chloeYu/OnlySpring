var telValidate = function(event) {
    var key = window.event ? event.keyCode : event.which;
    //alert(key);
    var keychar = String.fromCharCode(key);
    //alert(keychar);
    var reg = /[0-9]/;
    //alert(reg.test(keychar));
    return reg.test(keychar);
};