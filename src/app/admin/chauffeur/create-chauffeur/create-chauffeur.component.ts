import { PermisService } from './../../../services/permis.service';
import { Permis } from './../../../models/permis';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { ChauffeurService } from './../../../services/chauffeur.service';
import { Chauffeur } from './../../../models/chauffeur';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-create-chauffeur',
  templateUrl: './create-chauffeur.component.html',
  styleUrls: ['./create-chauffeur.component.scss']
})
export class CreateChauffeurComponent implements OnInit {

  formDataChauffeur: Chauffeur = new Chauffeur();
  listPermisData: Permis[];

  constructor(private chauffeurService: ChauffeurService,
              private permisService: PermisService,
              private router: Router
              )
              {}

  ngOnInit(): void {
    this.getListPermis();
  }

  getListPermis() {
    this.permisService.getPermis().subscribe(
      (response: Permis[]) => {
        this.listPermisData = response;
      }, (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }

  public onAddChauffeur() {
    this.chauffeurService.addChauffeur(this.formDataChauffeur).subscribe(
      (response: Chauffeur) => {
       console.log("Add Chauffeur successfully");
        this.router.navigate(['/chauffeurs']);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }


}
