$(document).ready(function() {
    $("#urlForm").submit(function(event) {
        event.preventDefault();

        let url = $("#insert").val();

        // if (!/^http:\/\/$/.test(url)) {
        //     url = "http://" + url;
        // } 

        $.ajax({
            url: "/shorten-url",
            type: "POST",
            data: JSON.stringify({ url: url }),
            contentType: "application/json; charset=utf-8",

            success: function(response) {
                $("#shortenedUrl").text("Acesse a URL em: " + response.url);
                $("#resultMessage").text("URL encurtada com sucesso!");
            },
            error: function(jqXHR, textStatus, errorThrown) {
                // Handle errors (optional)
                console.error("Error shortening URL:", textStatus, errorThrown);
                $("#resultMessage").text("An error occurred. Please try again."); // Display error message
            }
        });
    });
});