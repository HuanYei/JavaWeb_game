

var fangan="";
var jiaobentype="";
var jiaobenName="";
function fanganonclick(va) {
    if (va=="2853"){
        $("#fangandiv button").css("background-color","rgba(133, 133, 133, 0.6)");
        $("#fangandiv button:first").css("background-color","#f47c00");
    }else {
        $("#fangandiv button").css("background-color","#f47c00");
        $("#fangandiv button:first").css("background-color","rgba(133, 133, 133, 0.6)");
    }
    fangan=va;
    $("#fangandiv").hide(500);
    $("#jiaobendiv").show(500);
}

function jiaoben(va) {
    if (va=="public"){
        $("#jiaobendiv button").css("background-color","rgba(133, 133, 133, 0.6)");
        $("#jiaobendiv button:first").css("background-color","#f47c00");
    }else {
        $("#jiaobendiv button").css("background-color","#f47c00");
        $("#jiaobendiv button:first").css("background-color","rgba(133, 133, 133, 0.6)");
    }
    jiaobentype=va;
    jiaobenName=$("#jiaobentext").val();
    $("#jiaobendiv").hide(500);
}

function hideall(){
    $("#fangandiv").hide(500);
    $("#jiaobendiv").hide(500);
}

$(document).ready(function(){
    $("a").click(function(){
        $("a").css("background-color","transparent");
        $("a").css("color","green");
        $(this).css("background-color","#f47c00");
        $(this).css("color","white");
        if ($(this).html()=="方案"){
            hideall();
            $("#fangandiv").show(500);
        }
    });
});