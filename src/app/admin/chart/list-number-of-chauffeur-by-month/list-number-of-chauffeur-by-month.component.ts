import { ChauffeurDto } from './../../../models/chauffeur';
import { Component, OnInit } from '@angular/core';
import { Chart } from 'chart.js';

import { DashboardService } from 'src/app/services/dashboard.service';
@Component({
  selector: 'app-list-number-of-chauffeur-by-month',
  templateUrl: './list-number-of-chauffeur-by-month.component.html',
  styleUrls: ['./list-number-of-chauffeur-by-month.component.scss']
})
export class ListNumberOfChauffeurByMonthComponent implements OnInit {

  Barchart: any = [];

  NombreChauffeurParMois: number[] = [];
  ChauffeurOfMonth: Date[] = [];

  listMonth: any={};


  constructor(private crupdApi: DashboardService) { }

  ngOnInit() {
    this.crupdApi.countNumbersOfChauffeursPeerMonth()
      .subscribe((result: ChauffeurDto[]) => {
      this.listMonth = result;
      const n = 1;
      const m = 0;
      console.log(this.listMonth);
      for (let i=0; i<this.listMonth.length; i++) {
        this.NombreChauffeurParMois.push(this.listMonth[i][n]);
        this.ChauffeurOfMonth.push(this.listMonth[i][m]);
      }
    //  this
      this.Barchart = new Chart('barChartChauffeurPeerMonth', {
        type: 'bar',
        data: {
          labels: this.ChauffeurOfMonth,

          datasets: [
            {
              data: this.NombreChauffeurParMois,
              borderColor: '#3cb371',
              backgroundColor: "#5F9EA0",

            }
          ]
        },
        options: {
          legend: {
            display: false
          },
          scales: {
            xAxes: [{
              display: true,
              ticks: {
                beginAtZero: true
              }
            }],
            yAxes: [{
              display: true,
              ticks: {
                beginAtZero: true
              }
            }],
          }
        }
      });
    });
  }


}
