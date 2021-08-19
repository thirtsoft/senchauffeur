import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ChauffeurService } from './../../../services/chauffeur.service';
import { ChauffeurDto } from './../../../models/chauffeur';
import { HttpErrorResponse } from '@angular/common/http';
import { AddresseDto } from './../../../models/locality';
import { PermisDto } from './../../../models/permis';
import { PermisService } from './../../../services/permis.service';
import { AddressService } from './../../../services/address.service';


@Component({
  selector: 'app-view-chauffeur',
  templateUrl: './view-chauffeur.component.html',
  styleUrls: ['./view-chauffeur.component.scss']
})
export class ViewChauffeurComponent implements OnInit {

  chauffeurDTO: ChauffeurDto;
  listPermisDTO: PermisDto[];
  listAddressDTO: AddresseDto[];

  paramId :any = 0;

  constructor(public chauffService: ChauffeurService,
              private addService: AddressService,
              private permService: PermisService,
              private router: Router,
              private route: ActivatedRoute,
  ) {}

  ngOnInit(): void {
    this.getListPermisDTO();
    this.getListAddressDTO();
    this.paramId = this.route.snapshot.paramMap.get('id');
    console.log('Param--', this.paramId);
    if(this.paramId  && this.paramId  > 0){
      this.getChauffeurDTOById(this.paramId);
    }
  }

  getChauffeurDTOById(id: number) {
    console.log('getOne');
    this.chauffService.getChauffeurDTOById(id).subscribe(
      (response: ChauffeurDto) => {
        console.log('data--', response);
        this.chauffeurDTO = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );

  }

  getListPermisDTO() {
    console.log('getOne');
    this.permService.getPermisDTOs().subscribe(
      (response: PermisDto[]) => {
        console.log('data--', response);
        this.listPermisDTO = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );

  }

  getListAddressDTO() {
    console.log('getOne');
    this.addService.getAddresseDtos().subscribe(
      (response: AddresseDto[]) => {
        console.log('data--', response);
        this.listAddressDTO = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );

  }


  onSelectCvFile(event){}

  onSelectPhotoFile(event) {}

}
