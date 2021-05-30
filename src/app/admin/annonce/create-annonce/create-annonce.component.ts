import { HttpErrorResponse } from '@angular/common/http';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { RecruteurService } from './../../../services/recruteur.service';
import { PermisService } from './../../../services/permis.service';
import { AnnonceService } from './../../../services/annonce.service';
import { RecruteurDto } from './../../../models/recruteur';
import { PermisDto } from './../../../models/permis';
import { AnnonceDto } from './../../../models/annonce';
import { Component, OnInit, Inject } from '@angular/core';

@Component({
  selector: 'app-create-annonce',
  templateUrl: './create-annonce.component.html',
  styleUrls: ['./create-annonce.component.scss']
})
export class CreateAnnonceComponent implements OnInit {

  annonceDTO: AnnonceDto = new AnnonceDto();
  listPermisData: PermisDto[];
  listRecruteurData: RecruteurDto[];

  constructor(private annonceService: AnnonceService,
              private permisService: PermisService,
              private recruteurService: RecruteurService,
              private toastr: ToastrService,
              private router: Router,
              @Inject(MAT_DIALOG_DATA)  public data,
              public dialogRef:MatDialogRef<CreateAnnonceComponent>,
  ){}

  ngOnInit(): void {
    this.getListPermisDTOs();
  }

  getListPermisDTOs() {
    this.permisService.getPermisDTOs().subscribe(
      (response: PermisDto[]) => {
        this.listPermisData = response;
      }, (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }

  getListRecruteurDTOs() {
    this.recruteurService.getRecruteurDTOs().subscribe(
      (response: RecruteurDto[]) => {
        this.listRecruteurData = response;
      }, (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }

  public onAddAnnonce() {
    this.annonceService.addAnnonceDTO(this.annonceDTO).subscribe(
      (response: AnnonceDto) => {
        this.dialogRef.close();
        this.toastr.success("Annonce Ajouté avec Succès");
        this.router.navigate(['/backend/admin/annonces']);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }


}
