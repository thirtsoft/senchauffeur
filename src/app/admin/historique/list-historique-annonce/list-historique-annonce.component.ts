import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { HistoriqueAnnonceService } from './../../../services/historique-annonce.service';
import { HistoriqueAnnonceDto } from './../../../models/historique-annonce';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-list-historique-annonce',
  templateUrl: './list-historique-annonce.component.html',
  styleUrls: ['./list-historique-annonce.component.scss']
})
export class ListHistoriqueAnnonceComponent implements OnInit {

  historiqueAnnonceListDTO: HistoriqueAnnonceDto[];

  id : number;
  p : number=1;
  searchText;

  constructor(private crudApi: HistoriqueAnnonceService,
              private dialog: MatDialog,
              private router: Router,

     //         public toastr: ToastrService,
     //         private dialogService: DialogService,

  ){}

  ngOnInit(): void {
    this.getListHistoriqueAnnonceDTOs();
  }

  public getListHistoriqueAnnonceDTOs() {
    this.crudApi.getHistoriqueAnnonceDTOsOrderByIdDesc().subscribe(
      (response: HistoriqueAnnonceDto[]) => {
        this.historiqueAnnonceListDTO = response;
        console.log(this.historiqueAnnonceListDTO);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  onDeleteHistoriqueAnnonce(id: number): void{
    if (window.confirm('Etes-vous sure de vouloir supprimer cette annonce ?')) {
      this.crudApi.deleteHistoriqueAnnonceDTO(id)
        .subscribe(data => {
          this.getListHistoriqueAnnonceDTOs();
        },
        (error: HttpErrorResponse) => {
          alert(error.message);
        }
      );
    }
  }

}
