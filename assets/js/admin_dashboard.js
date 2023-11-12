document.addEventListener("DOMContentLoaded", () => {
    var budgetChart = echarts.init(document.querySelector("#budgetChart")).setOption({
        legend: {
            data: ['Đã chi', 'Đang chi']
        },
        radar: {
            // shape: 'circle',
            indicator: [{
                name: 'Doanh số',
                max: 6500
            },
                {
                    name: 'Hành chính',
                    max: 16000
                },
                {
                    name: 'Công nghệ thông tin',
                    max: 30000
                },
                {
                    name: 'Hỗ trợ khách hàng',
                    max: 38000
                },
                {
                    name: 'Phát triển',
                    max: 52000
                },
                {
                    name: 'Tiếp thị',
                    max: 25000
                }
            ]
        },
        series: [{
            name: 'Budget vs spending',
            type: 'radar',
            data: [{
                value: [4200, 3000, 20000, 35000, 50000, 18000],
                name: 'Đã chi'
            },
                {
                    value: [5000, 14000, 28000, 26000, 42000, 21000],
                    name: 'Đang chi'
                }
            ]
        }]
    });
});
document.addEventListener("DOMContentLoaded", () => {
    new ApexCharts(document.querySelector("#reportsChart"), {
        series: [{
            name: 'Doanh số',
            data: [31, 40, 28, 51, 42, 82, 56],
        }, {
            name: 'Doanh thu',
            data: [2000000, 3000000, 1000000, 5000000, 2500000, 1500000, 500000]
        }, {
            name: 'Khách hàng',
            data: [15, 11, 32, 18, 9, 24, 11]
        }],
        chart: {
            height: 350,
            type: 'area',
            toolbar: {
                show: false
            },
        },
        markers: {
            size: 4
        },
        colors: ['#4154f1', '#2eca6a', '#ff771d'],
        fill: {
            type: "gradient",
            gradient: {
                shadeIntensity: 1,
                opacityFrom: 0.3,
                opacityTo: 0.4,
                stops: [0, 90, 100]
            }
        },
        dataLabels: {
            enabled: false
        },
        stroke: {
            curve: 'smooth',
            width: 2
        },
        xaxis: {
            type: 'datetime',
            categories: ["2018-09-19T00:00:00.000Z", "2018-09-19T01:30:00.000Z", "2018-09-19T02:30:00.000Z", "2018-09-19T03:30:00.000Z", "2018-09-19T04:30:00.000Z", "2018-09-19T05:30:00.000Z", "2018-09-19T06:30:00.000Z"]
        },
        tooltip: {
            x: {
                format: 'dd/MM/yy HH:mm'
            },
        }
    }).render();
});