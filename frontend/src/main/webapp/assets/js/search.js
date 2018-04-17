$(document).ready(function () {

    var searchTerm = $("span.search-term").text();

    getTableForSearch(searchTerm, 0);
});

function getTableForSearch(searchTerm, page) {

    $.ajax({
        url: 'http://localhost:9000/search/' + searchTerm + '/' + page + '/10',
        dataType: 'json',
        async: true,
        success: function (data) {

            $("div#searchTermTable").empty();

            var table = "<table class=\"table table-striped\">" +
                "<thead>" +
                "<tr>" +
                "<th scope=\"col\">Tweet ID</th>" +
                "<th scope=\"col\">Classification Value</th>" +
                "<th scope=\"col\">Tweet Text</th>" +
                "</tr>" +
                "</thead>" +
                "<tbody>";

            data.forEach(function (tweet) {
                table += '<tr><th scope="row">' + tweet.id + '</th><td><p>' + tweet.classificationValue + '</p></td><td>' + tweet.tweetText + '</td></tr>'
            });

            table += "</tbody></table>";

            $("div#searchTermTable").append(table);

            $("#searchTermPagination").twbsPagination({
                totalPages: $("div#searchTermRawData").data("size") / 10,
                visiblePages: 4,
                onPageClick: function (event, page) {

                    var dbPage;

                    if (page === 1) {
                        // page 1 on pagination should relate to offset 0 on DB
                        dbPage = 0;
                    } else {
                        dbPage = (page * 10) - 10;
                    }

                    getTableForSearch(searchTerm, dbPage);
                }
            });
        },
        error: function () {
            $("div#searchTermTable").empty();
            $("div#searchTermTable").append("<h3>Issue retrieving table for " + searchTerm + "</h3>");
        }
    })
}
