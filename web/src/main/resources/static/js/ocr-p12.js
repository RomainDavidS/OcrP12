/* Javascript Document */


$(document).ready(function () {

    $('#button-search-siret').click(function(event) {

        $.post({
            url: "/entreprise/searchSiret",
            data: {
                siret : $('#siret').val()
            },
            success: function (response) {
                if(response.responseCode == 200 ) {
                    $('#adressPostaleComplement').val(response.adressPostaleComplement);
                    $('#adressPostaleAdress').val(response.adressPostaleAdress);
                    $('#adressPostaleCommune').val(response.adressPostaleCommune);
                    $('#name').val(response.name);
                }else {
                    $('#adressPostaleComplement').val("");
                    $('#adressPostaleAdress').val("");
                    $('#adressPostaleCommune').val("");
                    $('#name').val("");
                    alert( response.responseLibelle);
                }
            },
            error: function (error) {
                console.log(error);


            }

        });
        uploadFiles();
        event.preventDefault();
    });

    $('.uploadFile').click(function() { uploadFiles(  $(this).attr('id')  ); });


    $(".uploadFileButton").click(function(event) {

        let upload = $(this).attr('id').substring( 0, $(this).attr('id').length - 6 );
        // You can directly create form data from the form element type: "POST",
        // (Or you could get the files from input element and append them to FormData as we did in vanilla javascript)
        let formData = new FormData();


        formData.append('file',$("#" + upload)[0].files[0]);
        formData.append('typeFile',$("#"+ upload + "TypeFile").val() );

        $.post({

            enctype: 'multipart/form-data',
            url: "/uploadFile",
            data: formData,
            processData: false,
            contentType: false,
            success: function (response) {
                if(response.id != "") {
                    $("#" + upload + "BadgePhoto").removeClass('badge-danger').addClass('badge-success');
                    $("#" + upload + "Id").val(response.id);
                }

            },
            error: function (error) {
                console.log(error);
            }
        });
        event.preventDefault();
    });

    $(".uploadId").each(function () {
        if ($(this).val() != ""){
            let upload = $(this).attr('id').substring( 0, $(this).attr('id').length - 2 );
            $("#" + upload + "BadgePhoto").removeClass('badge-danger').addClass('badge-success');
        }
    });


});

function uploadFiles( upload) {

    $("#" + upload).change(function (e){
        $("#" + upload + "BadgePhoto").removeClass('badge-succes').addClass('badge-danger');
        $("#" + upload + "Id").val("");
        $("#" + upload + "Label").text(e.target.files[0].name);}
    );

    if (  $("#" + upload + "Id").val() != "")
        $("#" + upload + "BadgePhoto").removeClass('badge-danger').addClass('badge-success');


}