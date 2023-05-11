// Khi nào nội dung trang HTML đã được load thì sẽ chạy nội dung trong function

$(document).ready(function(){
    $(".btn-delete-user").click(function (){
        var id = $(this).attr("userId")
        var This = $(this)
        $.ajax({
            method: "GET",
            url: "http://localhost:8080/demoservlet/user/delete?id=" + id,
        })
            .done(function( result ) {
                This.closest("tr").remove()
                console.log("ket qua", result)
            });
    })
    // $(".btn-edit-user").click(function (){
    //     var id = $(this).attr("userId")
    //     var This = $(this)
    //     $.ajax({
    //         method: "POST",
    //         url: "http://localhost:8080/demoservlet/user/edit?id=" + id,
    //     })
    //         .done(function( result ) {
    //             This.update()
    //             console.log("ket qua", result)
    //         });
    // })

})