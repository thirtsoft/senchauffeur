import { UpdateEtatJetonComponent } from './../update-etat-jeton/update-etat-jeton.component';
import { FormBuilder } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { MatDialog, MAT_DIALOG_DATA, MatDialogRef, MatDialogConfig } from '@angular/material/dialog';
import { JetonService } from './../../../services/jeton.service';
import { JetonDto } from './../../../models/jeton';
import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit, Inject } from '@angular/core';
import { TokenStorageService } from 'src/app/auth/security/token-storage.service';

@Component({
  selector: 'app-list-jeton',
  templateUrl: './list-jeton.component.html',
  styleUrls: ['./list-jeton.component.scss']
})
export class ListJetonComponent implements OnInit {

  jetonListDTO: JetonDto[];

  roles: string[];
  isLoggedIn = false;
  showAdminBoard = false;
  showManagerBoard = false;
  showGestionnaireBoard = false;
  showUserBoard = false;

  id : number;
  p : number=1;
  searchText;

  constructor(private crudApi: JetonService,
              public toastr: ToastrService,
              private tokenService: TokenStorageService,
              private matDialog: MatDialog,
              private router: Router,
              public fb: FormBuilder,
              @Inject(MAT_DIALOG_DATA) public data: any,
              public dialogRef:MatDialogRef<UpdateEtatJetonComponent>,
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
    this.getListJetonDTOs();
  }

  getListJetonDTOs() {
    this.crudApi.getJetonDTOsOrderByIdDesc().subscribe(
      (response: JetonDto[]) => {
        this.jetonListDTO = response;
        console.log(this.jetonListDTO);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  onAddJeton() {
    this.router.navigate(['/admin/accueil/jeton']);
  }

  addEditEtatJeton(item : JetonDto) {
    this.crudApi.choixmenu == 'M';
    this.crudApi.formData = this.fb.group(Object.assign({},item));
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.disableClose = true;
    dialogConfig.width="50%";
    this.matDialog.open(UpdateEtatJetonComponent, dialogConfig);

  }

  onDeleteJeton(id: number): void{
    if (window.confirm('Etes-vous sure de vouloir supprimer ce Jeton ?')) {
      this.crudApi.deleteJetonDTO(id).subscribe(data => {
        this.toastr.error('avec succès','Jeton supprimé', {
          timeOut: 1500,
          positionClass: 'toast-top-right',
          });
          this.getListJetonDTOs();
        },
        (error: HttpErrorResponse) => {
          alert(error.message);
        }
      );
    }
  }


}
