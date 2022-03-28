import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { EmailService } from './../../../services/email.service';
import { EmailDto } from './../../../models/email';
import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-list-email',
  templateUrl: './list-email.component.html',
  styleUrls: ['./list-email.component.scss']
})
export class ListEmailComponent implements OnInit {

  listMailDTO: EmailDto[];

  id : number;
  p : number=1;
  searchText;

  constructor(public crudApi: EmailService,
              private router: Router,
              public toastr: ToastrService
  ){}

  ngOnInit(): void {
    this.getListMailDTOs();
  }

  getListMailDTOs() {
    this.crudApi.getEmailDTOsOrderByIdDesc().subscribe(
      (response: EmailDto[]) => {
        this.listMailDTO = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  onDeleteMailDTO(id: number): void{
    if (window.confirm('Etes-vous sure de vouloir supprimer cet Email ?')) {
      this.crudApi.deleteEmailDto(id).subscribe(data => {
        this.toastr.error('avec succès','Email supprimé', {
          timeOut: 1500,
          positionClass: 'toast-top-right',
          });
           this.router.navigateByUrl("admin/accueil/emails");
        },
        (error: HttpErrorResponse) => {
          alert(error.message);
        }
      );
    }
  }
}
