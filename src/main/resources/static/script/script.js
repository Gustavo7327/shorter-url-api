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
                $("#shortenedUrl").append("<img src='imgs/file-copy-line.svg' alt='' width='20px' id='copy' onclick='copyUrl()'></img>");
                $("#resultMessage").text("URL encurtada com sucesso!");
            },
            error: function(error) {
                $("#shortenedUrl").empty();
                $("#resultMessage").text(error.responseJSON.url);          
            }
        });
    });
});

function copyUrl(){
    let pContent = $("#shortenedUrl").text();
    let copy = pContent.substring(pContent.indexOf("http"));
    navigator.clipboard.writeText(copy);
    $("#shortenedUrl").find("#copy").remove();
    $("#shortenedUrl").append("<img src='imgs/file-check-line.svg' alt='' width='20px' id='copy' onclick='copyUrl()'></img>");
}