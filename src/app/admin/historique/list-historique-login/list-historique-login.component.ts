import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { HistoriqueLoginService } from './../../../services/historique-login.service';
import { HistoriqueLoginDto } from './../../../models/historique-login';
import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { TokenStorageService } from 'src/app/auth/security/token-storage.service';

@Component({
  selector: 'app-list-historique-login',
  templateUrl: './list-historique-login.component.html',
  styleUrls: ['./list-historique-login.component.scss']
})
export class ListHistoriqueLoginComponent implements OnInit {

  historiqueLoginListDTO: HistoriqueLoginDto[];
  roles: string[];
  isLoggedIn = false;
  showAdminBoard = false;
  showManagerBoard = false;
  showGestionnaireBoard = false;
  showUserBoard = false;

  id : number;
  p : number=1;
  searchText;

  constructor(private crudApi: HistoriqueLoginService,
              private tokenService: TokenStorageService,
              public toastr: ToastrService,

  ){}

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenService.getToken();
    if (this.isLoggedIn) {
      const user = this.tokenService.getUser();
      this.roles = user.roles;

      this.showAdminBoard = this.roles.includes('ROLE_ADMIN');
      this.showGestionnaireBoard = this.roles.includes("ROLE_GESTIONNAIRE");
      this.showManagerBoard = this.roles.includes('ROLE_MANAGER');
      this.showUserBoard = this.roles.includes('ROLE_USER');
    }

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
