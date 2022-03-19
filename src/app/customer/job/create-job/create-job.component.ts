import { ToastrService } from 'ngx-toastr';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';
import { RecruteurService } from './../../../services/recruteur.service';
import { PermisService } from './../../../services/permis.service';
import { AnnonceService } from './../../../services/annonce.service';
import { RecruteurDto } from './../../../models/recruteur';
import { PermisDto } from './../../../models/permis';
import { AnnonceDto } from './../../../models/annonce';
import { AddressService } from './../../../services/address.service';
import { AddresseDto } from './../../../models/locality';
import { VilleService } from './../../../services/ville.service';
import { VilleDto } from './../../../models/ville';
import { NgbDateStruct, NgbCalendar } from '@ng-bootstrap/ng-bootstrap';


@Component({
  selector: 'app-create-job',
  templateUrl: './create-job.component.html',
  styleUrls: ['./create-job.component.scss']
})
export class CreateJobComponent implements OnInit {

  addEditAnnonceDTO: AnnonceDto = new AnnonceDto();
  listPermisDTOs: PermisDto[];
  listRecruteurDTOs: RecruteurDto[];
  listVilleDTOs: VilleDto[];
  listAddressDTOs: AddresseDto[];

  listTypeContrats = ["Stage", "CDD", "CDI"];

  data;
  paramId :any = 0;
  mySubscription: any;
  addJobForm: NgForm;

  model: NgbDateStruct;
  today = this.calendar.getToday();

  constructor(private annonceService: AnnonceService,
              private permisService: PermisService,
              private addressService: AddressService,
              private toastr: ToastrService,
              private actRoute: ActivatedRoute,
              private router: Router,
              private calendar: NgbCalendar
  ) {
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

  //  this.getListRecruteurDTOs();

    this.annonceService.getUserId();

    this.getListAddressesDTOs();

  }

  getAnnonceDTOById(id: number) {
    console.log('getOne');
    this.annonceService.getAnnonceDTOById(id).subscribe(
      (response: AnnonceDto) => {
        console.log('data--', response);
        this.addEditAnnonceDTO = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );

  }

  getListPermisDTOs() {
    this.permisService.getPermisDTOs().subscribe(
      (response: PermisDto[]) => {
        this.listPermisDTOs = response;
      }, (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }

  getListAddressesDTOs() {
    this.addressService.getAddresseDtos().subscribe(
      (response: AddresseDto[]) => {
        this.listAddressDTOs = response;
      }, (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }


  public onAddJob() {
    console.log(this.addEditAnnonceDTO);
    this.annonceService.addAnnonceDTOWithUser(this.addEditAnnonceDTO, this.annonceService.id).subscribe(
      (response: AnnonceDto) => {
        this.toastr.warning('avec succès','Annonce Ajoutée', {
          timeOut: 1500,
          positionClass: 'toast-top-right',
        });
        window.location.reload();
      //  alert("Job Ajouté avec success");
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );

  }

  onUpdateJob() {
    this.annonceService.updateAnnonceDTO(this.addEditAnnonceDTO.id, this.addEditAnnonceDTO).subscribe(
      (response: AnnonceDto) => {
        this.toastr.warning('avec succès','Annonce Modifiée', {
          timeOut: 1500,
          positionClass: 'toast-top-right',
        });
        window.location.reload();
    //    alert("Job update avec success");
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  logout() {

  }

}
