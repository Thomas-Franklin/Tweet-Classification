$('input[name="userRadioGroup"]').click(function () {
    var target = $(this).data("target");
    $("a[href='" + target + "']").tab('show');
    $("a[href='" + target + "RawData']").tab('show');

    getUsersTable(target, 0);
    getUsersPieChart(target);
    getUsersBarChart(target);
});

function getUsersTable(target, page) {

    $.ajax({
        url: 'http://localhost:9000/users/' + target.substring(1, target.length) + '/' + page + '/10',
        dataType: 'json',
        async: true,
        success: function (data) {

            $("div" + target + "Table").empty();

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

            $("div" + target + "Table").append(table);

            $(target + "Pagination").twbsPagination({
                totalPages: $("div" + target + "RawData").data("size") / 10,
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