import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';
import { AddressService } from './../../../services/address.service';
import { AddresseDto } from './../../../models/address';

@Component({
  selector: 'app-list-locality',
  templateUrl: './list-locality.component.html',
  styleUrls: ['./list-locality.component.scss']
})
export class ListLocalityComponent implements OnInit {

  localityListDTO: AddresseDto[];

  constructor(public addService: AddressService,
              private router: Router,
  ) { }

  ngOnInit(): void {
    this.getListLocalityDTOs();
  }

  public getListLocalityDTOs() {
    this.addService.getAddresseDtos().subscribe(
      (response: AddresseDto[]) => {
        this.localityListDTO = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }



}
