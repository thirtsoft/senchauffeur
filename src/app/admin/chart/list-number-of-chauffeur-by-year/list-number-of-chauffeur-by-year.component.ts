import { ChauffeurDto } from './../../../models/chauffeur';
import { Component, OnInit } from '@angular/core';
import { Chart } from 'chart.js';

import { DashboardService } from 'src/app/services/dashboard.service';


@Component({
  selector: 'app-list-number-of-chauffeur-by-year',
  templateUrl: './list-number-of-chauffeur-by-year.component.html',
  styleUrls: ['./list-number-of-chauffeur-by-year.component.scss']
})
export class ListNumberOfChauffeurByYearComponent implements OnInit {

  Barchart: any = [];

  Linechart: any = [];

  NombreChauffeurParAnnees: number[] = [];
  ChauffeurOfYear: Date[] = [];

  listYear: any={}

  constructor(private crudApi: DashboardService) { }

  ngOnInit() {
    this.crudApi.countNumbersOfChauffeursPeerYear()
    .subscribe((result: ChauffeurDto[]) => {
      this.listYear = result;
      const n = 1;
      const m = 0;
      console.log(this.listYear);
      for (let i=0; i<this.listYear.length; i++) {
        this.NombreChauffeurParAnnees.push(this.listYear[i][n]);
        this.ChauffeurOfYear.push(this.listYear[i][m]);
      }
    //  this
      this.Linechart = new Chart('lineChartChauffeur', {
        type: 'line',
        data: {
          labels: this.ChauffeurOfYear,

          datasets: [
            {
              data: this.NombreChauffeurParAnnees,
              borderColor: '#3cb371',
              backgroundColor: "#FF7F50",
            }
          ]
        },
        options: {
          legend: {
            display: false
          },
          responsive: true,
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
