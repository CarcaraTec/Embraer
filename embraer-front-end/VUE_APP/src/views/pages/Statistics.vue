<template>
    <div>
      <div class="card">
        <h2>SB Status by Chassi</h2>
        <canvas ref="chartCanvas2"></canvas>
      </div>
  
      <div class="card">
        <h2>Chassis/Items</h2>
        <canvas ref="chartCanvas"></canvas>
      </div>
  
      <div class="card">
        <h2>Installed items by chassi</h2>
        <canvas ref="chartCanvas3"></canvas>
      </div>

      <div class="card">
        <h2>Applicable items by chassi</h2>
        <canvas ref="chartCanvas4"></canvas>
    </div>
    </div>
  </template>
  
  <script>
  import Chart from 'chart.js/auto';
  import axios from 'axios';
  
  export default {
    mounted() {
      this.getDataAndCreateSecondChart();
      this.getDataAndCreateChart();
      this.getDataAndCreateThirdChart();
      this.getDataAndCreateFourthChart();
    },
    methods: {
      async getDataAndCreateSecondChart() {
        try {
          const responseChassi = await axios.get('http://localhost:8081/chassi', {
        withCredentials: true,
      });
          const chassiData = responseChassi.data;
  
          const promises = chassiData.map(chassi => {
            return axios.get(`http://localhost:8081/statistics/statusBoletim?idChassi=${chassi.idChassi}`, {
        withCredentials: true,
      });
          });
  
          const responses = await Promise.all(promises);
          const chartData = [];
  
          for (let i = 0; i < chassiData.length; i++) {
            const statusData = responses[i].data[0];
  
            chartData.push({
              idChassi: chassiData[i].idChassi,
              qtdIncorporated: statusData.qtdIncorporated,
              qtdApplicable: statusData.qtdApplicable
            });
          }
  
          this.createSecondChart(chartData);
        } catch (error) {
          console.error(error);
        }
      },
  
      createSecondChart(chartData) {
        const ctx = this.$refs.chartCanvas2.getContext('2d');
        const labels = chartData.map(chassi => chassi.idChassi);
        const qtdIncorporated = chartData.map(chassi => chassi.qtdIncorporated);
        const qtdApplicable = chartData.map(chassi => chassi.qtdApplicable);
  
        new Chart(ctx, {
          type: 'bar',
          data: {
            labels: labels,
            datasets: [
              {
                label: 'Incorporated',
                data: qtdIncorporated,
                backgroundColor: 'rgba(75, 192, 192, 0.2)',
                borderColor: 'rgba(75, 192, 192, 1)',
                borderWidth: 1,
                stack: 'stack1'
              },
              {
                label: 'Applicable',
                data: qtdApplicable,
                backgroundColor: 'rgba(192, 75, 75, 0.2)',
                borderColor: 'rgba(192, 75, 75, 1)',
                borderWidth: 1,
                stack: 'stack1'
              }
            ]
          },
          options: {
            responsive: true,
            scales: {
              x: {
                stacked: true
              },
              y: {
                stacked: true
              }
            }
          }
        });
      },
  
      async getDataAndCreateChart() {
        try {
          const responseChassi = axios.get('http://localhost:8081/chassi', {
        withCredentials: true,
      });
          const responseItems = axios.get('http://localhost:8081/item', {
        withCredentials: true,
      });
  
          const [chassiData, itemsData] = await Promise.all([responseChassi, responseItems]);
  
          const applicablePromises = chassiData.data.map(chassi =>
            axios.get(`http://localhost:8081/statistics/applicable?idChassi=${chassi.idChassi}`, {
        withCredentials: true,
      })
          );
          const installedPromises = chassiData.data.map(chassi =>
            axios.get(`http://localhost:8081/statistics/installed?idChassi=${chassi.idChassi}`, {
        withCredentials: true,
      })
          );
  
          const [applicableResponses, installedResponses] = await Promise.all([
            Promise.all(applicablePromises),
            Promise.all(installedPromises)
          ]);
  
          const chartData = [];
  
          for (let i = 0; i < chassiData.data.length; i++) {
            const applicableData = applicableResponses[i].data;
            const installedData = installedResponses[i].data;
  
            for (const item of itemsData.data) {
              const applicable = applicableData.find(data => data.idItem === item.idItem);
              const installed = installedData.find(data => data.idItem === item.idItem);
  
              const existingItem = chartData.find(data => data.idItem === item.idItem);
  
              if (existingItem) {
                existingItem.qtdApplicable += applicable ? 1 : 0;
                existingItem.qtdInstalled += installed && installed.status === '✔' ? 1 : 0;
              } else {
                chartData.push({
                  idItem: item.idItem,
                  nome: item.nome,
                  qtdApplicable: applicable ? 1 : 0,
                  qtdInstalled: installed && installed.status === '✔' ? 1 : 0
                });
              }
            }
          }
  
          this.createChart(chartData);
        } catch (error) {
          console.error(error);
        }
      },
  
      createChart(chartData) {
        const ctx = this.$refs.chartCanvas.getContext('2d');
        const labels = chartData.map(data => data.nome);
        const qtdApplicable = chartData.map(data => data.qtdApplicable);
        const qtdInstalled = chartData.map(data => data.qtdInstalled);
  
        new Chart(ctx, {
          type: 'bar',
          data: {
            labels: labels,
            datasets: [
              {
                label: 'Applicable',
                data: qtdApplicable,
                backgroundColor: 'rgba(192, 75, 75, 0.2)',
                borderColor: 'rgba(192, 75, 75, 1)',
                borderWidth: 1,
                stack: 'stack1'
              },
              {
                label: 'Installed',
                data: qtdInstalled,
                backgroundColor: 'rgba(75, 192, 192, 0.2)',
                borderColor: 'rgba(75, 192, 192, 1)',
                borderWidth: 1,
                stack: 'stack1'
              }
            ]
          },
          options: {
            responsive: true,
            scales: {
              x: {
                stacked: true
              },
              y: {
                stacked: true
              }
            }
          }
        });
      },
  
      async getDataAndCreateThirdChart() {
  try {
    const responseChassi = await axios.get('http://localhost:8081/chassi', {
        withCredentials: true,
      });
    const chassiData = responseChassi.data;

    const promises = chassiData.map(chassi =>
      axios.get(`http://localhost:8081/statistics/installed?idChassi=${chassi.idChassi}`, {
        withCredentials: true,
      })
    );

    const installedResponses = await Promise.all(promises);
    const chartData = installedResponses.map((response, index) => ({
      idChassi: chassiData[index].idChassi,
      qtdInstalledItems: response.data.length
    }));

    this.createThirdChart(chartData);
  } catch (error) {
    console.error(error);
  }
},
  
      createThirdChart(chartData) {
        const ctx = this.$refs.chartCanvas3.getContext('2d');
        const labels = chartData.map(chassi => chassi.idChassi);
        const qtdInstalledItems = chartData.map(chassi => chassi.qtdInstalledItems);
  
        new Chart(ctx, {
          type: 'bar',
          data: {
            labels: labels,
            datasets: [
              {
                label: 'Installed Items',
                data: qtdInstalledItems,
                backgroundColor: 'rgba(75, 192, 192, 0.2)',
                borderColor: 'rgba(75, 192, 192, 1)',
                borderWidth: 1
              }
            ]
          },
          options: {
            responsive: true,
            scales: {
              x: {
                stacked: true
              },
              y: {
                beginAtZero: true
              }
            }
          }
        });
      },
      async getDataAndCreateFourthChart() {
      try {
        const responseChassi = await axios.get('http://localhost:8081/chassi', {
        withCredentials: true,
      });
        const chassiData = responseChassi.data;

        const promises = chassiData.map(chassi =>
          axios.get(`http://localhost:8081/statistics/applicable?idChassi=${chassi.idChassi}`, {
        withCredentials: true,
      })
        );

        const applicableResponses = await Promise.all(promises);
        const chartData = applicableResponses.map((response, index) => ({
          idChassi: chassiData[index].idChassi,
          qtdApplicableItems: response.data.length
        }));

        this.createFourthChart(chartData);
      } catch (error) {
        console.error(error);
      }
    },

    createFourthChart(chartData) {
      const ctx = this.$refs.chartCanvas4.getContext('2d');
      const labels = chartData.map(chassi => chassi.idChassi);
      const qtdApplicableItems = chartData.map(chassi => chassi.qtdApplicableItems);

      new Chart(ctx, {
        type: 'bar',
        data: {
          labels: labels,
          datasets: [
            {
              label: 'Applicable Items',
              data: qtdApplicableItems,
              backgroundColor: 'rgba(0, 0, 204, 0.2)',
              borderColor: 'rgba(0, 0, 204, 1)',
              borderWidth: 1
            }
          ]
        },
        options: {
          responsive: true,
          scales: {
            x: {
              stacked: true
            },
            y: {
              beginAtZero: true
            }
          }
        }
      });
    }
    }
  };
  </script>
  
  <style>
  .card {
    margin: 20px;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 4px;
  }
  </style>
  