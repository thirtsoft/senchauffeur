
import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { AnnonceService } from './../../../services/annonce.service';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { AnnonceDto } from './../../../models/annonce';

@Component({
  selector: 'app-list-job',
  templateUrl: './list-job.component.html',
  styleUrls: ['./list-job.component.scss']
})
export class ListJobComponent implements OnInit {

  annonceListDTO: AnnonceDto[];
  editAnnonceDTO: AnnonceDto;

  id : number;
  p : number=1;
  searchText;

  constructor(private annonceService: AnnonceService,
              private router: Router,
              private fb: FormBuilder
  ) { }

  ngOnInit(): void {
    this.getListAnnonceDTOs();
  }

  public getListAnnonceDTOs() {
    this.annonceService.getAnnonceDTOs().subscribe(
      (response: AnnonceDto[]) => {
        this.annonceListDTO = response;
        console.log(this.annonceListDTO);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  onAddNewJob() {
    this.router.navigate(['/createJob']);
  }

  onDeleteAnnonce(id: number): void{
  /*  this.dialogService.openConfirmDialog('Etes-vous sur de vouloir Supprimer cet donnée ?')
    .afterClosed().subscribe((response: any) =>{
      if(response){
        */
        this.annonceService.deleteAnnonceDTO(id).subscribe(data => {
   //       this.toastr.warning('Job supprimé avec succès!');
  //        this.annonceListDTO = this.annonceListDTO.filter(u => u !== annonceDTO);
          this.getListAnnonceDTOs();

    },
    (error: HttpErrorResponse) => {
      alert(error.message);
    }
    );
  }


}
