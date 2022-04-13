import { HttpErrorResponse } from '@angular/common/http';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { TypeAnnonceService } from './../../../services/type-annonce.service';
import { TypeAnnonceDto } from './../../../models/type-annonce';
import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from 'src/app/auth/security/token-storage.service';

@Component({
  selector: 'app-list-type-annonce',
  templateUrl: './list-type-annonce.component.html',
  styleUrls: ['./list-type-annonce.component.scss']
})
export class ListTypeAnnonceComponent implements OnInit {

  listTypeAnnonceDTO: TypeAnnonceDto[];
  roles: string[];

  isLoggedIn = false;
  showAdminBoard = false;
  showManagerBoard = false;
  showGestionnaireBoard = false;
  showUserBoard = false;

  id : number;
  p : number=1;
  searchText;

  constructor(public crudApi: TypeAnnonceService,
              private router: Router,
              public toastr: ToastrService,
              private tokenService: TokenStorageService
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

    this.getListTypeAnnonceDTOs();
  }

  public getListTypeAnnonceDTOs() {
    this.crudApi.getTypeTypeAnnonceDtOOrderByIdDesc().subscribe(
      (response: TypeAnnonceDto[]) => {
        this.listTypeAnnonceDTO = response;
        console.log(this.listTypeAnnonceDTO);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  onAddTypeAnnonce() {
    this.router.navigate(['/admin/accueil/typeAnnonce']);
  }

  onDeleteTypeAnnonce(id: number): void{
    if (window.confirm('Etes-vous sure de vouloir supprimer ce TypeAnnonce ?')) {
      this.crudApi.deleteTypeAnnonceDtos(id).subscribe(data => {
        this.toastr.error('avec succès','TypeAnnonce supprimé', {
          timeOut: 1500,
          positionClass: 'toast-top-right',
          });
           this.getListTypeAnnonceDTOs();
        },
        (error: HttpErrorResponse) => {
          alert(error.message);
        }
      );
    }
  }

}
