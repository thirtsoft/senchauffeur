import { Component, OnInit, ViewChild } from '@angular/core';

import { TokenStorageService } from './../../../auth/security/token-storage.service';
import { FormBuilder } from '@angular/forms';
import { AnnonceService } from './../../../services/annonce.service';
import { HttpErrorResponse } from '@angular/common/http';
import { Router, ActivatedRoute } from '@angular/router';
import { UtilisateurDto } from './../../../models/utilisateur';
import { UtilisateurService } from './../../../services/utilisateur.service';
import { AuthService } from './../../../auth/security/auth.service';
import { AnnonceDto } from './../../../models/annonce';

import { DataTableDirective } from 'angular-datatables';
import { Subject } from 'rxjs';
@Component({
  selector: 'app-list-job',
  templateUrl: './list-job.component.html',
  styleUrls: ['./list-job.component.scss']
})
export class ListJobComponent implements OnInit {

  annonceListDTO: AnnonceDto[];
  editAnnonceDTO: AnnonceDto;

  id : number;
  p : number=1;
  searchText;

  dtOptions: DataTables.Settings = {};
  dtTrigger: Subject<any> = new Subject();
  @ViewChild(DataTableDirective) dtElement: DataTableDirective;

  info: any;
  roles: string[];

  isLoggedIn = false;
  showAdminBoard = false;
  showUserBoard = false;
  showVendeurBoard = false;

  username: string;
  email: String;
  userId;
  photo;
  currentTime: number = 0;

  listAnnonceDataDTO: AnnonceDto[];
  listDataProfil: UtilisateurDto = new UtilisateurDto();

  currentPage: number = 1;
  totalPages: number;
  pages: Array<number>;

  customerName: string;
  customerUsername: string;
  customerEmail: string;
  customerMobile: string;
  customerPassword: string;

  currentUser;

  paramId :any = 0;
  comId: number;
  Errors = {status:false, msg:''};
  mySubscription: any;

  constructor(public annonceService: AnnonceService,
              public tokenService: TokenStorageService,
              public router: Router,
              public fb: FormBuilder,
  //            public toastr: ToastrService,
              public authService: AuthService,
              public userService: UtilisateurService,
              private route: ActivatedRoute,
  ) {
    this.annonceService.listen().subscribe((m:any) => {
      console.log(m);
      this.rerender();
  //    this.getListAnnonceDTOs();
    })
   }

  ngOnInit(): void {
    this.dtOptions = {
      pagingType: 'full_numbers',
      pageLength: 5,
      processing: true,
      autoWidth: true,
      order: [['desc', 0]]
    };

    /* this.annonceService.getAnnonceDTOs().subscribe(
      response =>{
        this.annonceListDTO = response;
        this.dtTrigger.next();
      }
    );
    this.getListAnnonceDTOs(); */

    this.paramId = this.route.snapshot.paramMap.get('id');
     console.log('Param--', this.paramId);
    if(this.paramId  && this.paramId  > 0){
      this.getAnnonceDTOByUserId(this.paramId);

      this.getUtilisateurDTOById(this.paramId);

    }

    this.isLoggedIn = !!this.tokenService.getToken();
    if (this.isLoggedIn) {
      const user = this.tokenService.getUser();
      this.roles = user.roles;

      this.showAdminBoard = this.roles.includes('ROLE_ADMIN');
      this.showVendeurBoard = this.roles.includes("ROLE_VENDEUR");
      this.showUserBoard = this.roles.includes('ROLE_USER');

      this.username = user.username;
      this.userId = user.id;
      this.photo = user.photo;

      console.log("Username : " + this.username);

      console.log("IdUser : " + this.userId);

    }
  }

  public getListAnnonceDTOs() {
    this.annonceService.getAnnonceDTOs().subscribe(
      (response: AnnonceDto[]) => {
        this.annonceListDTO = response;
        console.log(this.annonceListDTO);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  getAnnonceDTOByUserId(id: number) {
    this.annonceService.getAnnonceDtoByUserIdOrderDesc(id).subscribe(
      (response: AnnonceDto[]) => {
        console.log('data--', response);
        this.listAnnonceDataDTO = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );

  }


  getUtilisateurDTOById(id: number) {
    console.log('getOne');
    this.userService.getUtilisateurDTOById(id).subscribe(
      (response: UtilisateurDto) => {
        console.log('data--', response);
        this.listDataProfil = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );

  }

  onAddNewJob() {
    this.router.navigate(['/createJob']);
  //  this.router.navigate(['/jobs/' + this.userId]);
  }

  onDeleteAnnonce(id: number): void{
  /*  this.dialogService.openConfirmDialog('Etes-vous sur de vouloir Supprimer cet donnée ?')
    .afterClosed().subscribe((response: any) =>{
      if(response){
        */
        this.annonceService.deleteAnnonceDTO(id).subscribe(data => {
   //       this.toastr.warning('Job supprimé avec succès!');
  //        this.annonceListDTO = this.annonceListDTO.filter(u => u !== annonceDTO);
          this.getListAnnonceDTOs();

    },
    (error: HttpErrorResponse) => {
      alert(error.message);
    }
    );
  }

   /**
   * methode pour recharger automatique le Datatable
   */
  rerender() {
    this.dtElement.dtInstance.then((dtInstance: DataTables.Api) => {
      // Destroy the table first in the current context
      dtInstance.destroy();
      // call the dtTrigger to rerender again
      this.dtTrigger.next();
    });
  }

  ngOnDestroy(): void {
    this.dtTrigger.unsubscribe();
  }



}
