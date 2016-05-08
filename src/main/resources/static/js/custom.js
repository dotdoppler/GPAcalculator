function submitInfo(){

    var user = $("#user").val();
    var pwd = $("#pwd").val();
    var sign = new Date().getTime();
    var signedpwd = hex_md5(user + sign + hex_md5(pwd.trim()));

    $.ajax({
        type : "POST",
        url : "gpa",
        data:   {
            "user" : user,
            "sign" : sign,
            "signedpwd" : signedpwd,
        },
        dataType : "json",
        success : function(data) {
            if(data[0] == "loginError")
                alert("密码或学号错误");
            else {
                $("#gpa").text(data[0]) ;
                $("#credits").text(data[1]) ;
            }

        }
    });
}