import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { HistoriqueAnnonceService } from './../../../services/historique-annonce.service';
import { HistoriqueAnnonceDto } from './../../../models/historique-annonce';
import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { TokenStorageService } from 'src/app/auth/security/token-storage.service';

@Component({
  selector: 'app-list-historique-annonce',
  templateUrl: './list-historique-annonce.component.html',
  styleUrls: ['./list-historique-annonce.component.scss']
})
export class ListHistoriqueAnnonceComponent implements OnInit {

  historiqueAnnonceListDTO: HistoriqueAnnonceDto[];
  roles: string[];
  isLoggedIn = false;
  showAdminBoard = false;
  showManagerBoard = false;
  showGestionnaireBoard = false;
  showUserBoard = false;

  id : number;
  p : number=1;
  searchText;

  constructor(private crudApi: HistoriqueAnnonceService,
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
    this.getListHistoriqueAnnonceDTOs();
  }

  public getListHistoriqueAnnonceDTOs() {
    this.crudApi.getHistoriqueAnnonceDTOsOrderByIdDesc().subscribe(
      (response: HistoriqueAnnonceDto[]) => {
        this.historiqueAnnonceListDTO = response;
        console.log(this.historiqueAnnonceListDTO);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  onDeleteHistoriqueAnnonce(id: number): void{
    if (window.confirm('Etes-vous sure de vouloir supprimer cette annonce ?')) {
      this.crudApi.deleteHistoriqueAnnonceDTO(id)
        .subscribe(data => {
          this.getListHistoriqueAnnonceDTOs();
        },
        (error: HttpErrorResponse) => {
          alert(error.message);
        }
      );
    }
  }

}
