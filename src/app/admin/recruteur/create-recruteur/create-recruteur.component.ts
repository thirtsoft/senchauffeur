import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { RecruteurService } from './../../../services/recruteur.service';
import { Recruteur, RecruteurDto } from './../../../models/recruteur';
import { Component, OnInit, Inject } from '@angular/core';

@Component({
  selector: 'app-create-recruteur',
  templateUrl: './create-recruteur.component.html',
  styleUrls: ['./create-recruteur.component.scss']
})
export class CreateRecruteurComponent implements OnInit {

  formDataRecruteurDTO: RecruteurDto = new RecruteurDto();

  constructor(private recruteurService: RecruteurService,
              private router: Router,
              private toastr: ToastrService,
              @Inject(MAT_DIALOG_DATA)  public data,
              public dialogRef:MatDialogRef<CreateRecruteurComponent>,
  ){}

  ngOnInit(): void {}

  public onAddRecruteur() {
    this.recruteurService.addRecruteurDTO(this.formDataRecruteurDTO).subscribe(
      (response: RecruteurDto) => {
        this.dialogRef.close();
        this.toastr.success("Recruteur Ajouté avec Succès");
        this.router.navigate(['/backend/admin/recruteurs']);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }


}
