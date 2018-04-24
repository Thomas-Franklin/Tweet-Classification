$(document).ready(function () {

    var searchTerm = $("span.search-term").text();

    getTableForSearch(searchTerm, 0);
    getSearchResultsBarChart(searchTerm);
    getSearchResultsPieChart(searchTerm);
    getSearchTimeLineChart(searchTerm);
    getWordCloudForSearchTerm(searchTerm);
});

function getWordCloudForSearchTerm(searchTerm) {

    $.ajax({
        url: 'http://localhost:9000/search/' + searchTerm + '/wordcloud',
        dataType: 'json',
        async: true,
        success: function (data) {
            $("div#searchTermWordCloud .word-cloud-container").empty();
            $("div#searchTermWordCloud .word-cloud-container").append("<img width='100%' src='data:image/png;base64,"+data.wordCloudImage+"'/>");
        }
    });
}

function getSearchResultsPieChart(searchTerm) {

    var canvas = $("canvas#searchTermPieChartCanvas");

    (new Chart(canvas, {
        type: 'doughnut',
        data: {
            labels: ["rumour", "non-rumour"],
            datasets: [{
                data: [$("div#searchTerm").data("rumour"), $("div#searchTerm").data("non-rumour")],
                backgroundColor: [
                    'rgb(255, 99, 132)',
                    'rgb(75, 192, 192)'
                ]
            }]
        },

        options: {
            responsive: true,
            maintainAspectRatio: false,
            legend: {
                position: 'bottom'
            },
            title: {
                display: true,
                text: searchTerm + " Pie Chart"
            },
            tooltips: {
                callbacks: {
                    label: function(tooltipItem, data) {
                        var dataset = data.datasets[tooltipItem.datasetIndex];
                        var total = dataset.data.reduce(function (previousValue, currentValue) {
                            return previousValue + currentValue;
                        });
                        var currentValue = dataset.data[tooltipItem.index];
                        var percentage = Math.floor(((currentValue/total) * 100) + 0.5);
                        return currentValue + " (" + percentage + "%)";
                    }
                }
            }
        }
    }));
}

function getSearchResultsBarChart(searchTerm) {

    var canvas = $("canvas#searchTermBarChartCanvas");

    (new Chart(canvas, {
        type: 'bar',
        data: {
            datasets: [{
                label: "rumour",
                data: [$("div#searchTerm").data("rumour")],
                backgroundColor: 'rgb(255, 99, 132)'
            }, {
                label: "non-rumour",
                data: [$("div#searchTerm").data("non-rumour")],
                backgroundColor: 'rgb(75, 192, 192)'
            }]
        },

        options: {
            responsive: true,
            maintainAspectRatio: false,
            legend: {
                position: 'bottom'
            },
            title: {
                display: true,
                text: searchTerm + " Bar Chart"
            },
            tooltips: {
                callbacks: {
                    label: function(tooltipItem, data) {
                        var dataset = data.datasets;
                        var total = dataset.reduce(function (previousValue, currentValue) {
                            return previousValue.data[0] + currentValue.data[0];
                        });
                        var currentValue = tooltipItem.yLabel;
                        var percentage = Math.floor(((currentValue/total) * 100) + 0.5);
                        return currentValue + " (" + percentage + "%)";
                    }
                }
            },
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true
                    }
                }]
            }
        }
    }));
}



function getTableForSearch(searchTerm, page) {

    $.ajax({
        url: 'http://localhost:9000/search/' + searchTerm + '/' + page + '/10',
        dataType: 'json',
        async: true,
        success: function (data) {

            $("div#searchTermTable").empty();

            var table = "<table class=\"table table-striped table-sm\">" +
                "<thead>" +
                "<tr>" +
                "<th scope=\"col\">Tweet ID</th>" +
                "<th scope=\"col\">Classification Value</th>" +
                "<th scope=\"col\">Tweet Text</th>" +
                "<th scope=\"col\">Created On</th>" +
                "</tr>" +
                "</thead>" +
                "<tbody>";

            data.forEach(function (tweet) {
                table += '<tr><th scope="row">' + tweet.id + '</th><td><p>' + tweet.classificationValue + '</p></td><td>' + tweet.tweetText + '</td><td>' + tweet.createdOn + '</td></tr>'
            });

            table += "</tbody></table>";

            $("div#searchTermTable").append(table);

            $("#searchTermPagination").twbsPagination({
                totalPages: Math.ceil($("div#searchTermRawData").data("size") / 10),
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

function getSearchTimeLineChart(searchTerm) {
    $.ajax({
        url: 'http://localhost:9000/search/'+searchTerm+'/timeline',
        dataType: 'json',
        async: true,
        success: function (data) {
            var canvas = $("canvas#searchTermTimeLineChartCanvas");

            (new Chart(canvas, {
                type: 'line',
                data: {
                    labels: ["Within last hour", "1-2 hours ago", "2-3 hours ago", "3-4 hours ago", "4-5 hours ago"],
                    datasets: [{
                        label: "rumour",
                        data: [data.rumoursLastHour, data.rumoursOverOneHour, data.rumoursOverTwoHour, data.rumoursOverThreeHour, data.rumoursOverFourHour],
                        borderColor: 'rgb(255, 99, 132)',
                        backgroundColor: 'rgb(255, 99, 132)',
                        fill: false
                    }, {
                        label: "non-rumour",
                        data: [data.nonRumoursLastHour, data.nonRumoursOverOneHour, data.nonRumoursOverTwoHour, data.nonRumoursOverThreeHour, data.nonRumoursOverFourHour],
                        borderColor: 'rgb(75, 192, 192',
                        backgroundColor: 'rgb(75, 192, 192',
                        fill: false
                    }]
                },

                options: {
                    responsive: true,
                    maintainAspectRatio: false,
                    legend: {
                        position: 'bottom'
                    },
                    title: {
                        display: true,
                        text: "Timeline of Rumours and Non-Rumours for " + searchTerm + " within last 5 hours"
                    },
                    scales: {
                        yAxes: [{
                            ticks: {
                                beginAtZero: true
                            }
                        }]
                    }
                }
            }));
        }
    });
}
