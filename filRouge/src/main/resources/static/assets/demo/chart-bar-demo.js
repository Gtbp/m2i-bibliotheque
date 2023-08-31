// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#292b2c';

// Bar Chart Example
var ctx = document.getElementById("myBarChart");
var myLineChart = new Chart(ctx, {
  type: 'bar',
  data: {
    labels: ["Poésie", "Histoire", "Science-Fiction", "Fantasy", "Policier", "Théatre"],
    datasets: [{
      label: "livres",
      backgroundColor: "#6096BA",
      borderColor: "rgba(2,117,216,1)",
      data: [10, 70, 45, 35, 120, 25],
    }],
  },
  options: {
    scales: {
      xAxes: [{
        time: {
          unit: 'number'
        },
        gridLines: {
          display: false
        },
        ticks: {
          maxTicksLimit: 6
        }
      }],
      yAxes: [{
        ticks: {
          min: 0,
          max: 100,
          maxTicksLimit: 5
        },
        gridLines: {
          display: true
        }
      }],
    },
    legend: {
      display: false
    }
  }
});
