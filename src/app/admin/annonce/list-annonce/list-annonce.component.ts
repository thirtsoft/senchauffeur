import { UpdateStatusOfAnnonceComponent } from './../update-status-of-annonce/update-status-of-annonce.component';
import { FormBuilder } from '@angular/forms';
import { Component, OnInit, Inject } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { MatDialog, MAT_DIALOG_DATA, MatDialogRef, MatDialogConfig } from '@angular/material/dialog';
import { AnnonceService } from './../../../services/annonce.service';
import { Annonce, AnnonceDto } from './../../../models/annonce';
import { HttpErrorResponse } from '@angular/common/http';
import { TokenStorageService } from 'src/app/auth/security/token-storage.service';

@Component({
  selector: 'app-list-annonce',
  templateUrl: './list-annonce.component.html',
  styleUrls: ['./list-annonce.component.scss']
})
export class ListAnnonceComponent implements OnInit {

  annonceListDTO: AnnonceDto[];
  roles: string[];
  currentTime: number = 0;

  isLoggedIn = false;
  showAdminBoard = false;
  showManagerBoard = false;
  showGestionnaireBoard = false;

  userId;

  id : number;
  p : number=1;
  searchText;

  constructor(private annonceService: AnnonceService,
              public toastr: ToastrService,
              private tokenService: TokenStorageService,
              private matDialog: MatDialog,
              private router: Router,
              public fb: FormBuilder,
              @Inject(MAT_DIALOG_DATA) public data: any,
              public dialogRef:MatDialogRef<UpdateStatusOfAnnonceComponent>,
  ){}

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenService.getToken();
    if (this.isLoggedIn) {
      const user = this.tokenService.getUser();
      this.roles = user.roles;

      this.showAdminBoard = this.roles.includes('ROLE_ADMIN');
      this.showGestionnaireBoard = this.roles.includes("ROLE_GESTIONNAIRE");
      this.showManagerBoard = this.roles.includes('ROLE_MANAGER');
      
      this.userId = user.id;
    }
    this.getListAnnonceDTOs();
  }

  getListAnnonceDTOs() {
    this.annonceService.getAnnonceDTOOrderByIdDesc().subscribe(
      (response: AnnonceDto[]) => {
        this.annonceListDTO = response;
        console.log(this.annonceListDTO);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  onAddAnnonce() {
    this.router.navigate(['/admin/accueil/annonce']);
  }

  addEditStatusAnnonce(item : AnnonceDto) {
    this.annonceService.choixmenu == 'M';
    this.annonceService.formData = this.fb.group(Object.assign({},item));
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.disableClose = true;
    dialogConfig.width="50%";
    this.matDialog.open(UpdateStatusOfAnnonceComponent, dialogConfig);

  }

  onDeleteAnnonce(id: number): void{
    if (window.confirm('Etes-vous sure de vouloir supprimer cette annonce ?')) {
      this.annonceService.deleteAnnonceDTO(id).subscribe(data => {
        this.toastr.error('avec succès','Annonce supprimé', {
          timeOut: 1500,
          positionClass: 'toast-top-right',
          });
           this.getListAnnonceDTOs();
        },
        (error: HttpErrorResponse) => {
          alert(error.message);
        }
      );
    }
  }



}
