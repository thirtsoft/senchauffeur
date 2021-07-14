import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';
import { PermisService } from './../../../services/permis.service';
import { PermisDto } from './../../../models/permis';
@Component({
  selector: 'app-list-job-permis',
  templateUrl: './list-job-permis.component.html',
  styleUrls: ['./list-job-permis.component.scss']
})
export class ListJobPermisComponent implements OnInit {

  listPermisDTO: PermisDto[];

  constructor(public perService: PermisService,
              private router: Router,
  ) { }

  ngOnInit(): void {
    this.getListPermisDTOs();
  }

  public getListPermisDTOs() {
    this.perService.getPermisDTOs().subscribe(
      (response: PermisDto[]) => {
        this.listPermisDTO = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }


}
