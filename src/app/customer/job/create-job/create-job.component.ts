import { AddressService } from './../../../services/address.service';
import { AddresseDto } from './../../../models/locality';
import { VilleService } from './../../../services/ville.service';
import { VilleDto } from './../../../models/ville';
import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';
import { RecruteurService } from './../../../services/recruteur.service';
import { PermisService } from './../../../services/permis.service';
import { AnnonceService } from './../../../services/annonce.service';
import { RecruteurDto } from './../../../models/recruteur';
import { PermisDto } from './../../../models/permis';
import { AnnonceDto } from './../../../models/annonce';

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

  data;
  paramId :any = 0;
  mySubscription: any;
  addJobForm: NgForm;

  constructor(private annonceService: AnnonceService,
              private permisService: PermisService,
              private recruteurService: RecruteurService,
              private villeService: VilleService,
              private addressService: AddressService,
              private actRoute: ActivatedRoute,
              private router: Router
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

    this.getListRecruteurDTOs();

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

  getListRecruteurDTOs() {
    this.recruteurService.getRecruteurDTOs().subscribe(
      (response: RecruteurDto[]) => {
        this.listRecruteurDTOs = response;
      }, (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }

  getListVilleDTOs() {
    this.villeService.getVilleDTOs().subscribe(
      (response: VilleDto[]) => {
        this.listVilleDTOs = response;
      }, (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }

  public onAddJob() {
    console.log(this.addEditAnnonceDTO);
    this.annonceService.addAnnonceDTO(this.addEditAnnonceDTO).subscribe(
      (response: AnnonceDto) => {
        alert("Job Ajouté avec success");
        this.router.navigate(['/jobs']);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );

  }

  public onUpdateJob() {
    this.annonceService.updateAnnonceDTO(this.addEditAnnonceDTO.id, this.addEditAnnonceDTO).subscribe(
      (response: AnnonceDto) => {
        alert("Job update avec success");
        this.router.navigate(['/jobs']);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

}
