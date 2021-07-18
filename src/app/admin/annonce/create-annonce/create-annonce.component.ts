import { Component, OnInit, Inject } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router, NavigationEnd } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { RecruteurService } from './../../../services/recruteur.service';
import { PermisService } from './../../../services/permis.service';
import { AnnonceService } from './../../../services/annonce.service';
import { RecruteurDto } from './../../../models/recruteur';
import { PermisDto } from './../../../models/permis';
import { AnnonceDto } from './../../../models/annonce';


@Component({
  selector: 'app-create-annonce',
  templateUrl: './create-annonce.component.html',
  styleUrls: ['./create-annonce.component.scss']
})
export class CreateAnnonceComponent implements OnInit {

  annonceDTO: AnnonceDto = new AnnonceDto();
  listPermisData: PermisDto[];
  listRecruteurData: RecruteurDto[];

  data;
  paramId :any = 0;
  mySubscription: any;

  constructor(private annonceService: AnnonceService,
              private permisService: PermisService,
              private recruteurService: RecruteurService,
//              private toastr: ToastrService,
              public dialog: MatDialog,
              private actRoute: ActivatedRoute,
              private router: Router,
  ){
    //--for reload componant
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;
    this.mySubscription = this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        // Trick the Router into believing it's last link wasn't previously loaded
        this.router.navigated = false;
      }
    });
  }

  ngOnInit(): void {
    this.paramId = this.actRoute.snapshot.paramMap.get('id');
    console.log('Param--', this.paramId);
    if(this.paramId  && this.paramId  > 0){
      this.getAnnonceDTOById(this.paramId);
    }

    this.getListPermisDTOs();

    this.getListRecruteurDTOs();

  }

  getAnnonceDTOById(id: number) {
    console.log('getOne');
    this.annonceService.getAnnonceDTOById(id).subscribe(
      (response: AnnonceDto) => {
        console.log('data--', response);
        this.annonceDTO = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );

  }

  getListPermisDTOs() {
    this.permisService.getPermisDTOs().subscribe(
      (response: PermisDto[]) => {
        this.listPermisData = response;
      }, (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }

  getListRecruteurDTOs() {
    this.recruteurService.getRecruteurDTOs().subscribe(
      (response: RecruteurDto[]) => {
        this.listRecruteurData = response;
      }, (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }

  public onAddAnnonce() {
    this.annonceService.addAnnonceDTO(this.annonceDTO).subscribe(
      (response: AnnonceDto) => {
        this.router.navigate(['/backend/admin/annonces']);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onUpdateAnnonce() {
    this.annonceService.updateAnnonceDTO(this.annonceDTO.id, this.annonceDTO).subscribe(
      (response: AnnonceDto) => {
        this.router.navigate(['/backend/admin/annonces']);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }


}
