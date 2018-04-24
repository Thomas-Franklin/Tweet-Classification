$('input[name="userRadioGroup"]').click(function () {
    var target = $(this).data("target");
    $("a[href='" + target + "']").tab('show');
    $("a[href='" + target + "RawData']").tab('show');

    getUsersTable(target, 0);
    getUsersPieChart(target);
    getUsersBarChart(target);
    getUsersTimeLineChart(target);
    getWordCloudForUser(target);
});

function getWordCloudForUser(target) {

    $.ajax({
       url: 'http://localhost:9000/users/' + target.substring(1, target.length) + '/wordcloud',
        dataType: 'json',
        async: true,
        success: function (data) {
            $("div" + target + "WordCloud .word-cloud-container").empty();
            $("div" + target + "WordCloud .word-cloud-container").append("<img width='100%' src='data:image/png;base64,"+data.wordCloudImage+"'/>");
        }
    });
}

function getUsersTable(target, page) {

    $.ajax({
        url: 'http://localhost:9000/users/' + target.substring(1, target.length) + '/' + page + '/10',
        dataType: 'json',
        async: true,
        success: function (data) {

            $("div" + target + "Table").empty();

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

            $("div" + target + "Table").append(table);

            $(target + "Pagination").twbsPagination({
                totalPages: Math.ceil($("div" + target + "RawData").data("size") / 10),
                visiblePages: 4,
                onPageClick: function (event, page) {

                    var dbPage;

                    if (page === 1) {
                        // page 1 on pagination should relate to offset 0 on DB
                        dbPage = 0;
                    } else {
                        dbPage = (page * 10) - 10;
                    }

                    getUsersTable(target, dbPage);
                }
            });
        },
        error: function () {
            $("div" + target + "Table").empty();
            $("div" + target + "Table").append("<h3>Issue retrieving table for " + target.substring(1, target.length) + "</h3>");
        }
    })
}

function getUsersPieChart(target) {

    var canvas = $("canvas" + target + "PieChartCanvas");

    (new Chart(canvas, {
        type: 'doughnut',
        data: {
            labels: ["rumour", "non-rumour"],
            datasets: [{
                data: [$("div"+target+"").data("rumour"), $("div"+target+"").data("non-rumour")],
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
                text: target.substring(1, target.length) + " Pie Chart"
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

function getUsersBarChart(target) {

    var canvas = $("canvas" + target + "BarChartCanvas");

    (new Chart(canvas, {
        type: 'bar',
        data: {
            datasets: [{
                label: "rumour",
                data: [$("div"+target+"").data("rumour")],
                backgroundColor: 'rgb(255, 99, 132)'
            }, {
                label: "non-rumour",
                data: [$("div"+target+"").data("non-rumour")],
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
                text: target.substring(1, target.length) + " Bar Chart"
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

function getUsersTimeLineChart(target) {
    $.ajax({
        url: 'http://localhost:9000/users/'+target.substring(1, target.length)+'/timeline',
        dataType: 'json',
        async: true,
        success: function (data) {
            var canvas = $("canvas" + target + "TimeLineChartCanvas");

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
                        text: "Timeline of Rumours and Non-Rumours for " + target.substring(1, target.length) + " within last 5 hours"
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