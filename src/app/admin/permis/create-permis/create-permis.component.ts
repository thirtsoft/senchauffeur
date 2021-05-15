import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { PermisService } from './../../../services/permis.service';
import { Permis } from './../../../models/permis';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-create-permis',
  templateUrl: './create-permis.component.html',
  styleUrls: ['./create-permis.component.scss']
})
export class CreatePermisComponent implements OnInit {

  formDataPermis: Permis = new Permis();
  deletePermis: Permis;

  constructor(private permisService: PermisService,
              private router: Router
              )
              {}

  ngOnInit(): void {}

  public onAddPermis() {
    this.permisService.addPermis(this.formDataPermis).subscribe(
      (response: Permis) => {
       console.log("Add Permis successfully");
        this.router.navigate(['/permis']);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }


}
