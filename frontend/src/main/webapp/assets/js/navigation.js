$(document).ready(function () {

    $("button#searchTerm").click(function (event) {
        console.log("Searching...");
        event.preventDefault();

        if ($("#searchTermValue").val() !== '') {

            var searchTerm = $("#searchTermValue").val();

            console.log(searchTerm);

            window.location.replace("/search/" + searchTerm);

        } else {

            $("#searchTermValue").addClass("alert-danger");

            $("section.messages").children().remove();

            $("section.messages").append("<div class='alert alert-warning alert-dismissible fade show' role='alert'>" +
                " <button type='button' class='close' data-dismiss='alert' aria-label='Close'>" +
                "   <span aria-hidden='true'>&times;</span>" +
                " </button>" +
                " Search term must not be empty" +
                "</div>");

            $("section.messages .alert").delay(10000).slideUp(200, function () {
                $(this).alert("close");
                $("#searchTermValue").removeClass("alert-danger");
            });
        }
    });
});
