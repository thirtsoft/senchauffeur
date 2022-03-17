import { Component, OnInit } from '@angular/core';
import { Chart } from 'chart.js';

import { DashboardService } from 'src/app/services/dashboard.service';
import { AnnonceDto } from './../../../models/annonce';

@Component({
  selector: 'app-list-number-of-annonce-by-year',
  templateUrl: './list-number-of-annonce-by-year.component.html',
  styleUrls: ['./list-number-of-annonce-by-year.component.scss']
})
export class ListNumberOfAnnonceByYearComponent implements OnInit {

  Barchart: any = [];

  NombreAnnonceParAnnees: number[] = [];
  AnnonceOfYear: Date[] = [];

  listYear: any={};


  constructor(private crupdApi: DashboardService) { }

  ngOnInit() {
    this.crupdApi.countNumbersOfAnnoncePeerYear()
      .subscribe((result: AnnonceDto[]) => {
      this.listYear = result;
      const n = 1;
      const m = 0;
      console.log(this.listYear);
      for (let i=0; i<this.listYear.length; i++) {
        this.NombreAnnonceParAnnees.push(this.listYear[i][n]);
        this.AnnonceOfYear.push(this.listYear[i][m]);
      }
    //  this
      this.Barchart = new Chart('barChartNumberAnnoncePeerYear', {
        type: 'bar',
        data: {
          labels: this.AnnonceOfYear,

          datasets: [
            {
              data: this.NombreAnnonceParAnnees,
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
