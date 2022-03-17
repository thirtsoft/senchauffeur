import { Component, OnInit } from '@angular/core';
import { Chart } from 'chart.js';

import { AnnonceDto } from './../../../models/annonce';
import { DashboardService } from 'src/app/services/dashboard.service';

@Component({
  selector: 'app-list-number-of-annonce-by-month',
  templateUrl: './list-number-of-annonce-by-month.component.html',
  styleUrls: ['./list-number-of-annonce-by-month.component.scss']
})
export class ListNumberOfAnnonceByMonthComponent implements OnInit {

  Barchart: any = [];

  Linechart: any = [];

  NombreAnnonceParMois: number[] = [];
  AnnonceOfMonth: Date[] = [];

  listMois: any={}

  constructor(private crudApi: DashboardService) { }

  ngOnInit() {
    this.crudApi.countNumbersOfAnnoncePeerMonth().subscribe((result: AnnonceDto[]) => {
      this.listMois = result;
      const n = 1;
      const m = 0;
      console.log(this.listMois);
      for (let i=0; i<this.listMois.length; i++) {
        this.NombreAnnonceParMois.push(this.listMois[i][n]);
        this.AnnonceOfMonth.push(this.listMois[i][m]);
      }
    //  this
      this.Linechart = new Chart('lineChart', {
        type: 'line',
        data: {
          labels: this.AnnonceOfMonth,

          datasets: [
            {
              data: this.NombreAnnonceParMois,
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
