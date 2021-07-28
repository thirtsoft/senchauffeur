import { FormBuilder } from '@angular/forms';
import { DialogService } from './../../../services/dialog.service';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { CreateChauffeurComponent } from './../create-chauffeur/create-chauffeur.component';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { ChauffeurService } from './../../../services/chauffeur.service';
import { Chauffeur, ChauffeurDto } from './../../../models/chauffeur';

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
              private dialog: MatDialog,
              private router: Router,
        //      public toastr: ToastrService,
     //         private dialogService: DialogService,
              private fb: FormBuilder
  ){}

  ngOnInit(): void {
    this.getListChauffeurDTOs();
  }

  public getListChauffeurDTOs() {
    this.chauffeurService.getChauffeurDTOs().subscribe(
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
    this.router.navigate(['/backend/admin/chauffeur']);
  }

  addEditCvChauffeur(i) {}

  addEditPhotoChauffeur(i){}

  onDeleteChauffeur(id: number): void{
    if (window.confirm('Etes-vous sure de vouloir supprimer ce Chauffeur ?')) {
      this.chauffeurService.deleteChauffeurDTO(id).subscribe(data => {
        this.getListChauffeurDTOs();
        },
        (error: HttpErrorResponse) => {
          alert(error.message);
        }
      );
    }
  }


}
