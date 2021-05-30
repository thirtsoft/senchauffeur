import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { PermisService } from './../../../services/permis.service';
import { Permis, PermisDto } from './../../../models/permis';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { ChauffeurService } from './../../../services/chauffeur.service';
import { Chauffeur, ChauffeurDto } from './../../../models/chauffeur';
import { Component, OnInit, Inject } from '@angular/core';

@Component({
  selector: 'app-create-chauffeur',
  templateUrl: './create-chauffeur.component.html',
  styleUrls: ['./create-chauffeur.component.scss']
})
export class CreateChauffeurComponent implements OnInit {

  formDataChauffeurDTO: ChauffeurDto = new ChauffeurDto();
  listPermisData: PermisDto[];

  constructor(private chauffeurService: ChauffeurService,
              private permisService: PermisService,
              private toastr: ToastrService,
              private router: Router,
              @Inject(MAT_DIALOG_DATA)  public data,
              public dialogRef:MatDialogRef<CreateChauffeurComponent>,
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

  public onAddChauffeur() {
    this.chauffeurService.addChauffeurDTO(this.formDataChauffeurDTO).subscribe(
      (response: ChauffeurDto) => {
        this.dialogRef.close();
        this.toastr.success("Chauffeur Ajouté avec Succès");
        this.router.navigate(['/backend/admin/chauffeurs']);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }


}
