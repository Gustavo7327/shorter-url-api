$(document).ready(function() {
    $("#urlForm").submit(function(event) {
        event.preventDefault();

        let url = $("#insert").val();

        $.ajax({
            url: "/shorten-url",
            type: "POST",
            data: JSON.stringify({ url: url }),
            contentType: "application/json; charset=utf-8",

            success: function(response) {
                $("#shortenedUrl").text("Acesse a URL em: " + response.url);
                $("#resultMessage").text("URL encurtada com sucesso!");
            },
            error: function(error) {
                $("#shortenedUrl").empty();
                $("#resultMessage").text(error.responseJSON.url);          
            }
        });
    });
});