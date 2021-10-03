import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { AnnonceService } from './../../../services/annonce.service';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { AnnonceDto } from './../../../models/annonce';

import { DataTableDirective } from 'angular-datatables';
import { Subject } from 'rxjs';
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

  dtOptions: DataTables.Settings = {};
  dtTrigger: Subject<any> = new Subject();
  @ViewChild(DataTableDirective) dtElement: DataTableDirective;

  constructor(private annonceService: AnnonceService,
              private router: Router,
              private fb: FormBuilder
  ) {
    this.annonceService.listen().subscribe((m:any) => {
      console.log(m);
      this.rerender();
      this.getListAnnonceDTOs();
    })
   }

  ngOnInit(): void {
    this.dtOptions = {
      pagingType: 'full_numbers',
      pageLength: 5,
      processing: true,
      autoWidth: true,
      order: [['desc', 0]]
    };

    this.annonceService.getAnnonceDTOs().subscribe(
      response =>{
        this.annonceListDTO = response;
        this.dtTrigger.next();
      }
    );
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

   /**
   * methode pour recharger automatique le Datatable
   */
  rerender() {
    this.dtElement.dtInstance.then((dtInstance: DataTables.Api) => {
      // Destroy the table first in the current context
      dtInstance.destroy();
      // call the dtTrigger to rerender again
      this.dtTrigger.next();
    });
  }

  ngOnDestroy(): void {
    this.dtTrigger.unsubscribe();
  }



}
