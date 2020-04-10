$(document).ready(function(){

    $("button").click(function(){
        var url = "http://localhost:8080/api/breeze?";

        url += "lat="+$("#lat").val();
        url += "&lon="+$("#lon").val();

        var features = "";
        $.each($("input[name='feature']:checked"), function(){
            features+= $(this).val()+",";
        });
        url += "&features=" + features.slice(0, -1);
        console.log(url);
        $.ajax({
            type: "GET",
            url: url,
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data) {
                console.log(data);
            }
        });
    });


});
