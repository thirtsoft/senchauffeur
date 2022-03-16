import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { JetonService } from './../../../services/jeton.service';
import { JetonDto } from './../../../models/jeton';
import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-list-jeton',
  templateUrl: './list-jeton.component.html',
  styleUrls: ['./list-jeton.component.scss']
})
export class ListJetonComponent implements OnInit {

  jetonListDTO: JetonDto[];

  id : number;
  p : number=1;
  searchText;

  constructor(private crudApi: JetonService,
              private dialog: MatDialog,
              private router: Router,

     //         public toastr: ToastrService,
     //         private dialogService: DialogService,

  ){}

  ngOnInit(): void {
    this.getListJetonDTOs();
  }

  public getListJetonDTOs() {
    this.crudApi.getJetonDTOsOrderByIdDesc().subscribe(
      (response: JetonDto[]) => {
        this.jetonListDTO = response;
        console.log(this.jetonListDTO);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  onAddJeton() {
    this.router.navigate(['/admin/accueil/jeton']);
  }

  onDeleteJeton(id: number): void{
    if (window.confirm('Etes-vous sure de vouloir supprimer cette annonce ?')) {
      this.crudApi.deleteJetonDTO(id).subscribe(data => {
        this.getListJetonDTOs();
        },
        (error: HttpErrorResponse) => {
          alert(error.message);
        }
      );
    }
  }


}
