import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { RecruteurService } from './../../../services/recruteur.service';
import { Recruteur } from './../../../models/recruteur';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-create-recruteur',
  templateUrl: './create-recruteur.component.html',
  styleUrls: ['./create-recruteur.component.scss']
})
export class CreateRecruteurComponent implements OnInit {

  formDataRecruteur: Recruteur = new Recruteur();

  constructor(private recruteurService: RecruteurService,
              private router: Router
              )
              {}

  ngOnInit(): void {}

  public onAddRecruteur() {
    this.recruteurService.addRecruteur(this.formDataRecruteur).subscribe(
      (response: Recruteur) => {
       console.log("Add Recruteur successfully");
        this.router.navigate(['/recruteurs']);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }



}
