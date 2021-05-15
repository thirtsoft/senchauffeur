import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { LocalityService } from './../../../services/locality.service';
import { Locality } from './../../../models/locality';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-create-locality',
  templateUrl: './create-locality.component.html',
  styleUrls: ['./create-locality.component.scss']
})
export class CreateLocalityComponent implements OnInit {

  formDataLocality: Locality = new Locality();
  deleteLocality: Locality;

  constructor(private localiteService: LocalityService,
              private router: Router
              )
              {}

  ngOnInit(): void {}

  public onAddLocality() {
    this.localiteService.addLocality(this.formDataLocality).subscribe(
      (response: Locality) => {
       console.log("Add Locality successfully");
        this.router.navigate(['/localities']);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }


}
