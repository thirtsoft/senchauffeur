import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { VilleService } from './../../../services/ville.service';
import { VilleDto } from './../../../models/ville';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-list-ville',
  templateUrl: './list-ville.component.html',
  styleUrls: ['./list-ville.component.scss']
})
export class ListVilleComponent implements OnInit {

  villeListDTO: VilleDto[];

  id : number;
  p : number=1;
  searchText;

  constructor(private villeService: VilleService,
              private dialog: MatDialog,
              private router: Router
  ){}

  ngOnInit(): void {
    this.getListVillesDTOs();
  }

  public getListVillesDTOs(): void {
    this.villeService.getVilleDTOs().subscribe(
      (response: VilleDto[]) => {
        this.villeListDTO = response;
        console.log(this.villeListDTO);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  onAddVille() {
    this.router.navigate(['/backend/admin/ville']);
  }

  onDeleteVille(id: number): void{
    if (window.confirm('Etes-vous sure de vouloir supprimer cette Ville ?')) {
      this.villeService.deleteVille(id).subscribe(data => {
        this.getListVillesDTOs();
        },
        (error: HttpErrorResponse) => {
          alert(error.message);
        }
      );
    }
  }


}
