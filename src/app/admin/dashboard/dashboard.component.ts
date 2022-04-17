import { HttpErrorResponse } from '@angular/common/http';
import { ChauffeurDto } from './../../models/chauffeur';
import { RecruteurDto } from './../../models/recruteur';
import { NotationDto } from './../../models/notation';
import { RecruteurService } from './../../services/recruteur.service';
import { NotationService } from './../../services/notation.service';
import { Router } from '@angular/router';
import { ChauffeurService } from './../../services/chauffeur.service';
import { AnnonceService } from './../../services/annonce.service';
import { Component, OnInit } from '@angular/core';
import { DashboardService } from 'src/app/services/dashboard.service';
import { UtilisateurService } from 'src/app/services/utilisateur.service';
import { UtilisateurDto } from 'src/app/models/utilisateur';
import { Login } from 'src/app/auth/security/login';
import { AuthService } from 'src/app/auth/security/auth.service';
import { TokenStorageService } from 'src/app/auth/security/token-storage.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  listNotificationDTO: NotationDto[];
  listChauffeurDTO: ChauffeurDto[];
  listUtilisateurDTO: UtilisateurDto[];

  numberOfChauffeur;
  numberOfAnnonce;
  numberOfValidatedAnnonces;
  numberOfAnnoncesInYear;
  numberOfRecruteur;

  form: any = {};
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];
  loginInfo: Login;

  showAdminBoard = false;
  showManagerBoard = false;
  showGestionnaireBoard = false;
  showUserBoard = false;

  constructor(public dasboardService: DashboardService,
              public noteService: NotationService,
              public UserService: UtilisateurService,
              public authService: AuthService,
              public tokenStorage: TokenStorageService,
              public chauffService: ChauffeurService,
              public recService: RecruteurService,
              public router: Router,

  ) {}

  ngOnInit(): void {
    if(this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.roles = this.tokenStorage.getUser().roles;

      this.showAdminBoard = this.roles.includes('ROLE_ADMIN');
      this.showGestionnaireBoard = this.roles.includes("ROLE_GESTIONNAIRE");
      this.showManagerBoard = this.roles.includes('ROLE_MANAGER');
      this.showUserBoard = this.roles.includes('ROLE_USER');
    }

    this.getNumberOfChauffeurs();

    this.getNumberOfRecruteurs();

 //   this.getNumberOfAnnonces();

    this.getNumberOfValidatedAnnonces();

    this.getNumberOfAnnoncesInYear();

    this.getListNotationDtos();

    this.getListRecruteurDTO();
  }

  getNumberOfChauffeurs(): void {
    this.dasboardService.countNumberOfChauffeurs().subscribe(data => {
      this.numberOfChauffeur = data;
    });
  }

  getNumberOfAnnonces(): void {
    this.dasboardService.countNumberOfAnnonces().subscribe(data => {
      this.numberOfAnnonce = data;
    });
  }

  getNumberOfValidatedAnnonces(): void {
    this.dasboardService.countNumberOfAnnonceByStatusValidated()
      .subscribe(data => {
        this.numberOfValidatedAnnonces = data;
      }
    );
  }

  getNumberOfAnnoncesInYear(): void {
    this.dasboardService.countNumberOfAnnoncesInYear()
      .subscribe(data => {
        this.numberOfAnnoncesInYear = data;
      }
    );
  }

  getNumberOfRecruteurs(): void {
    this.dasboardService.countNumberOfRecruteurs().subscribe(data => {
      this.numberOfRecruteur = data;
    });
  }

  public getListNotationDtos() {
    this.noteService.getNotationDTOs().subscribe(
      (response: NotationDto[]) => {
        this.listNotificationDTO = response;
        console.log(this.listNotificationDTO);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );

  }

  public getListRecruteurDTO() {
    this.UserService.getAllUtilisateurDtosOrderByIdDesc().subscribe(
      (response: UtilisateurDto[]) => {
        this.listUtilisateurDTO = response;
        console.log(this.listUtilisateurDTO);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );

  }


}
