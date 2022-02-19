import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { NewsleterService } from './../../../services/newsleter.service';
import { NewsleterDto } from './../../../models/newsleter';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-list-newsleter',
  templateUrl: './list-newsleter.component.html',
  styleUrls: ['./list-newsleter.component.scss']
})
export class ListNewsleterComponent implements OnInit {

  newsleterListDTO: NewsleterDto[];

  id : number;
  p : number=1;
  searchText;

  constructor(private crudApi: NewsleterService,
              private dialog: MatDialog,
              private router: Router,

     //         public toastr: ToastrService,
     //         private dialogService: DialogService,

  ){}

  ngOnInit(): void {
    this.getListNewsleterDTOs();
  }

  public getListNewsleterDTOs() {
    this.crudApi.getNewsleterDTOsOrderByIdDesc().subscribe(
      (response: NewsleterDto[]) => {
        this.newsleterListDTO = response;
        console.log(this.newsleterListDTO);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  onDeletegetNewsleterDTO(id: number): void{
    if (window.confirm('Etes-vous sure de vouloir supprimer cette annonce ?')) {
      this.crudApi.deleteNewsleterDTO(id)
        .subscribe(data => {
          this.getListNewsleterDTOs();
        },
        (error: HttpErrorResponse) => {
          alert(error.message);
        }
      );
    }
  }

}
