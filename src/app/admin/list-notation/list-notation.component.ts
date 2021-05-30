import { FormBuilder } from '@angular/forms';
import { DialogService } from './../../services/dialog.service';
import { ToastrService } from 'ngx-toastr';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { NotationService } from './../../services/notation.service';
import { Notation, NotificationDto } from './../../models/notation';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-list-notation',
  templateUrl: './list-notation.component.html',
  styleUrls: ['./list-notation.component.scss']
})
export class ListNotationComponent implements OnInit {

  notationListDTO: NotificationDto[];
  addEditNotationDTO: NotificationDto;

  id : number;
  p : number=1;
  searchText;

  constructor(private noteService: NotationService,
              private router: Router,
              public toastr: ToastrService,
              private dialogService: DialogService,
              private fb: FormBuilder
  ){}

  ngOnInit(): void {
    this.getListNotificationDTOs();
  }

  public getListNotificationDTOs() {
    this.noteService.getNotationDTOs().subscribe(
      (response: NotificationDto[]) => {
        this.notationListDTO = response;
        console.log(this.notationListDTO);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );

  }

  addEditNotation(i) {}

  onDeleteNotation(note: NotificationDto): void{
    this.dialogService.openConfirmDialog('Etes-vous sur de vouloir Supprimer cet donnée ?')
    .afterClosed().subscribe((response: any) =>{
      if(response){
        this.noteService.deleteNotationDTO(note.id).subscribe(data => {
          this.toastr.warning('Notification supprimé avec succès!');
          this.notationListDTO = this.notationListDTO.filter(u => u !== note);
          this.getListNotificationDTOs();
        });
      }
    },
    (error: HttpErrorResponse) => {
      alert(error.message);
    }
    );
  }


}
