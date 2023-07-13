<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <%@include file="head.jsp" %>
    <title>blueGreen | Admin Dashboard</title>
    <link rel="stylesheet/less" href="./assets/css/admin.less">
</head>
<body>
    <%@include file="sidebar.jsp" %>

    <main>
        <div class="container py-4">
            <div class="row g-4 mb-5 statistical">
                <div class="col-lg-3 col-sm-6">
                    <div class="statistical-card" style="background-image: linear-gradient(230deg, #759bff, #843cf6);">
                        <p class="icon"><i class="fas fa-shopping-bag"></i></p>
                        <h1>${countProduct}</h1>
                        <h2>Products</h2>
                    </div>
                </div>
                <div class="col-lg-3 col-sm-6">
                    <div class="statistical-card" style="background-image: linear-gradient(230deg, #fc5286, #fbaaa2);">
                        <p class="icon"><i class="fas fa-money-bill-wave"></i></p>
                        <h1 class="price">${income}</h1>
                        <h2>Income</h2>
                    </div>
                </div>
                <div class="col-lg-3 col-sm-6">
                    <div class="statistical-card" style="background-image: linear-gradient(230deg, #ffc480, #ff763b);">
                        <p class="icon"><i class="fa fa-users"></i></p>
                        <h1>${countCustomer}</h1>
                        <h2>Customers</h2>
                    </div>
                </div>
                <div class="col-lg-3 col-sm-6">
                    <div class="statistical-card" style="background-image: linear-gradient(230deg, #0e4cfd, #6a8eff);">
                        <p class="icon"><i class="fas fa-file-invoice-dollar"></i></p>
                        <h1>${countOrder}</h1>
                        <h2>Orders</h2>
                    </div>
                </div>
                
            </div>

            <div class="row g-4">
                <div class="col-md-6">
                    <div class="chart-wrapper">
                        <div class="chart-header text-center mb-4">
                            <h2>Earning in 12 months</h2>
                        </div>
                        <canvas id="income-chart"></canvas>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="chart-wrapper">
                        <div class="chart-header text-center mb-4">
                            <h2>Orders in 12 months</h2>
                        </div>
                        <canvas id="order-chart"></canvas>
                    </div>
                </div>
            </div>

        </div>
    </main>
    
    <%@include file="script.html" %>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script>
        const month = ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec'];
        var chart1 = document.getElementById('income-chart').getContext('2d');
        var chart2 = document.getElementById('order-chart').getContext('2d');
        var lineChart = new Chart(chart1, {
            type: 'line',
            options: {
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            },
            data: {
                labels: month,
                datasets: [{
                    label: 'Earning in $',
                    data: ${chartMoney},
                    fill: true,
                    borderColor: '#ff6384',
                    backgroundColor: 'rgba(255, 99, 132, .5)',
                    tension: 0.4,
                }],
            },
        });

        var barChart = new Chart(chart2, {
            type: 'bar',
            options: {
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            },
            data: {
                labels: month,
                datasets: [{
                    label: 'Orders',
                    data: ${chartOrder},
                    borderColor: '#2d63fe',
                    borderWidth: 1,
                    backgroundColor: 'rgba(45, 99, 254, .5)',
                }],
            },
        });
    </script>
</body>
</html>
