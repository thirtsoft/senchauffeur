import { ToastrService } from 'ngx-toastr';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { TarifService } from './../../../services/tarif.service';
import { TarifDto } from './../../../models/tarif';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-list-tarif',
  templateUrl: './list-tarif.component.html',
  styleUrls: ['./list-tarif.component.scss']
})
export class ListTarifComponent implements OnInit {

  listTarifDTO: TarifDto[];

  id : number;
  p : number=1;
  searchText;

  constructor(public tarifService: TarifService,
              private router: Router,
              public toastr: ToastrService
  ){}

  ngOnInit(): void {
    this.getListTarifDTOs();
  }

  public getListTarifDTOs() {
    this.tarifService.getTarifDTOss().subscribe(
      (response: TarifDto[]) => {
        this.listTarifDTO = response;
        console.log(this.listTarifDTO);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  onAddTarif() {
    this.router.navigate(['/admin/accueil/tarif']);
  }

  onDeleteTarif(id: number): void{
    if (window.confirm('Etes-vous sure de vouloir supprimer ce Tarif ?')) {
      this.tarifService.deleteTarifDTOs(id).subscribe(data => {
        this.toastr.error('avec succès','Tarif supprimé', {
          timeOut: 1500,
          positionClass: 'toast-top-right',
          });
           this.getListTarifDTOs();
        },
        (error: HttpErrorResponse) => {
          alert(error.message);
        }
      );
    }
  }

}
