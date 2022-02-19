import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { HistoriqueLoginService } from './../../../services/historique-login.service';
import { HistoriqueLoginDto } from './../../../models/historique-login';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-list-historique-login',
  templateUrl: './list-historique-login.component.html',
  styleUrls: ['./list-historique-login.component.scss']
})
export class ListHistoriqueLoginComponent implements OnInit {

  historiqueLoginListDTO: HistoriqueLoginDto[];

  id : number;
  p : number=1;
  searchText;

  constructor(private crudApi: HistoriqueLoginService,
              private dialog: MatDialog,
              private router: Router,

     //         public toastr: ToastrService,
     //         private dialogService: DialogService,

  ){}

  ngOnInit(): void {
    this.getListHistoriqueLoginDTOs();
  }

  public getListHistoriqueLoginDTOs() {
    this.crudApi.getHistoriqueLoginsOrderByIdDesc().subscribe(
      (response: HistoriqueLoginDto[]) => {
        this.historiqueLoginListDTO = response;
        console.log(this.historiqueLoginListDTO);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  onDeleteHistoriqueLogin(id: number): void{
    if (window.confirm('Etes-vous sure de vouloir supprimer cette annonce ?')) {
      this.crudApi.deleteHistoriqueLogin(id)
        .subscribe(data => {
          this.getListHistoriqueLoginDTOs();
        },
        (error: HttpErrorResponse) => {
          alert(error.message);
        }
      );
    }
  }


}
