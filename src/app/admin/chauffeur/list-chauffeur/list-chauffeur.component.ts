import { Component, OnInit } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';

import { ToastrService } from 'ngx-toastr';
import { ChauffeurService } from './../../../services/chauffeur.service';
import { ChauffeurDto } from './../../../models/chauffeur';

@Component({
  selector: 'app-list-chauffeur',
  templateUrl: './list-chauffeur.component.html',
  styleUrls: ['./list-chauffeur.component.scss']
})
export class ListChauffeurComponent implements OnInit {

  chauffeurListDTO: ChauffeurDto[];
  addEditChauffeurDTO: ChauffeurDto;

  id : number;
  p : number=1;
  searchText;

  constructor(public chauffeurService: ChauffeurService,
              private router: Router,
              public toastr: ToastrService,
              private fb: FormBuilder,
  ){}

  ngOnInit(): void {
    this.getListChauffeurDTOs();
  }

  getListChauffeurDTOs() {
    this.chauffeurService.getChauffeurDTOOrderByIdDesc().subscribe(
      (response: ChauffeurDto[]) => {
        this.chauffeurListDTO = response;
        console.log(this.chauffeurListDTO);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  onAddChauffeur() {
    this.router.navigate(['/admin/accueil/chauffeur']);
  }

  onDeleteChauffeur(id: number){
  //  this.dialogService.openConfirmDialog('Etes-vous sur de vouloir Supprimer cet donnée ?')
    window.alert('Etes-vous sur de supprimer ce chauffeur ?')
    this.chauffeurService.deleteChauffeurDTO(id).subscribe(data => {
      this.toastr.error('avec succès','Chauffeur supprimé', {
        timeOut: 1500,
        positionClass: 'toast-top-right',
        });
         this.getListChauffeurDTOs();
      },
      (error: HttpErrorResponse) => {
        this.toastr.error('veuillez contactez administrateur','Chauffeur ne peut pas etre supprimé');
      }        
    )
  }

}
