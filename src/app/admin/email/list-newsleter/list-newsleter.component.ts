import { SendEmailToNewsleterComponent } from './../send-email-to-newsleter/send-email-to-newsleter.component';
import { FormBuilder } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { NewsleterService } from './../../../services/newsleter.service';
import { NewsleterDto } from './../../../models/newsleter';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-list-newsleter',
  templateUrl: './list-newsleter.component.html',
  styleUrls: ['./list-newsleter.component.scss']
})
export class ListNewsleterComponent implements OnInit {

  newsleterListDTO: NewsleterDto[];

  id : number;
  p : number=1;
  searchText;

  constructor(private crudApi: NewsleterService,
              public toastr: ToastrService,
              private matDialog: MatDialog,
              private fb: FormBuilder,
              private router: Router
  ){}

  ngOnInit(): void {
    this.getListNewsleterDTOs();
  }

  public getListNewsleterDTOs() {
    this.crudApi.getNewsleterDTOsOrderByIdDesc().subscribe(
      (response: NewsleterDto[]) => {
        this.newsleterListDTO = response;
        console.log(this.newsleterListDTO);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  sendMailToNewsleter(item: NewsleterDto) {
    this.crudApi.choixmenu = "M";
    this.crudApi.dataForm = this.fb.group(Object.assign({},item));
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.disableClose = true;
    dialogConfig.width="50%";
    this.matDialog.open(SendEmailToNewsleterComponent, dialogConfig);
  }

  onDeletegetNewsleterDTO(id: number): void{
    if (window.confirm('Etes-vous sure de vouloir supprimer cet visiteur ?')) {
      this.crudApi.deleteNewsleterDTO(id)
        .subscribe(data => {
          this.toastr.success("Email Envoyé avec Succès");
          this.router.navigate(['/admin/accueil/newsleters']);
        },
        (error: HttpErrorResponse) => {
          alert(error.message);
        }
      );
    }
  }

}
