/* Javascript Document */
'use strict';


$(document).ready(function () {

    $("#filePhoto").change(function (e){
            $('#badgePhoto').removeClass('badge-succes').addClass('badge-danger');
            $('#cover').val("");
            $("#filePhotoLabel").text(e.target.files[0].name);}
        );


    if ( $('#cover').val() != "")
        $('#badgePhoto').removeClass('badge-danger').addClass('badge-success');




    $('#uploadButton').click(function(event) {


        // You can directly create form data from the form element type: "POST",
        // (Or you could get the files from input element and append them to FormData as we did in vanilla javascript)
        var formData = new FormData();

        formData.append('file',$('#filePhoto')[0].files[0]);
        $.post({

            enctype: 'multipart/form-data',
            url: "/cover/upload",
            data: formData,
            processData: false,
            contentType: false,
            success: function (response) {
                if(response.id != "") {
                    $('#badgePhoto').removeClass('badge-danger').addClass('badge-success');
                    $('#cover').val(response.id);
                    $('#imgCover').attr('src','/cover/img/' + response.id );

                }

            },
            error: function (error) {
                console.log(error);
            }
        });
        event.preventDefault();

    });


})



