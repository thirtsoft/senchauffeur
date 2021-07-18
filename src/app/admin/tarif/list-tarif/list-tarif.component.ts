import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
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
              private dialog: MatDialog,
              private router: Router,
        //      public toastr: ToastrService,
     //         private dialogService: DialogService,

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
    this.router.navigate(['/backend/admin/tarif']);
  }

/*
  onAddChauffeur() {
    this.openNoteDialog(null);
  }

  openNoteDialog(data?: any){
    const dialogRef = this.dialog.open(CreateChauffeurComponent, {
      disableClose: true,
      autoFocus : true ,
      width : "50%",
      data: data
    } );

    dialogRef.afterClosed().subscribe(result => {
      if(result && data == null){
        this.chauffeurListDTO.push(result);
      }
    });
  }
*/

  onDeleteTarif(id: number): void{
    this.tarifService.deleteTarifDTOs(id).subscribe(data => {
      this.getListTarifDTOs();
        }
    ,
    (error: HttpErrorResponse) => {
      alert(error.message);
    }
    );
  }


}
