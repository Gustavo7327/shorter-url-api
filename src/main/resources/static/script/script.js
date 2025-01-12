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
                $("#shortenedUrl").append("<img src='imgs/file-copy-line.svg' alt='' width='20px' id='copy'></img>");
                $("#resultMessage").text("URL encurtada com sucesso!");
                $("#shortenedUrl").on("click", "#copy", function() {
                    copyUrl();
                });                
            },
            error: function(error) {
                $("#shortenedUrl").empty();
                $("#resultMessage").text(error.responseJSON.url);          
            }
        });
    });
});

function copyUrl() {
    let pContent = $("#shortenedUrl").text();
    let copy = pContent.substring(pContent.indexOf("http"));

    let tempInput = document.createElement("textarea");
    tempInput.value = copy;
    document.body.appendChild(tempInput); 

    tempInput.select();
    tempInput.setSelectionRange(0, 99999); 
    document.execCommand("copy"); 

    document.body.removeChild(tempInput);

    $("#shortenedUrl").find("#copy").remove();
    $("#shortenedUrl").append("<img src='imgs/file-check-line.svg' alt='Copiado!' width='20px' id='copy' onclick='copyUrl()'></img>");
}
