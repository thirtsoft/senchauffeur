import { Component, OnInit } from '@angular/core';
import { TarifService } from '../../../services/tarif.service';
import { TarifDto } from '../../../models/tarif';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';


@Component({
  selector: 'app-list-tarif-customer',
  templateUrl: './list-tarif-customer.component.html',
  styleUrls: ['./list-tarif-customer.component.scss']
})
export class ListTarifCustomerComponent implements OnInit {

  tarifListDTO: TarifDto[];

  constructor(private tafService: TarifService,
              private router: Router,
  ) { }

  ngOnInit(): void {

    this.getTarifListDTOs();


  }

  public getTarifListDTOs() {
    this.tafService.getTarifDTOss().subscribe(
      (response: TarifDto[]) => {
        this.tarifListDTO = response;
        console.log(this.tarifListDTO);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );

  }


}
