import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { PermisService } from './../../../services/permis.service';
import { Permis, PermisDto } from './../../../models/permis';
import { Component, OnInit, Inject } from '@angular/core';

@Component({
  selector: 'app-create-permis',
  templateUrl: './create-permis.component.html',
  styleUrls: ['./create-permis.component.scss']
})
export class CreatePermisComponent implements OnInit {

  formDataPermisDTO: PermisDto = new PermisDto();

  constructor(private permisService: PermisService,
              private router: Router,
              private toastr: ToastrService,
              @Inject(MAT_DIALOG_DATA)  public data,
              public dialogRef:MatDialogRef<CreatePermisComponent>,
  ){}

  ngOnInit(): void {}

  public onAddPermis() {
    this.permisService.addPermisDTO(this.formDataPermisDTO).subscribe(
      (response: PermisDto) => {
        this.dialogRef.close();
        this.toastr.success("Permis Ajouté avec Succès");
        this.router.navigate(['/backend/admin/permis']);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }


}
