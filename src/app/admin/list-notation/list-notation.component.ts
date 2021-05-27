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

  notationList: Notation[];
  notationListDTO: NotificationDto[];
  editNotation: Notation;
  deleteNotation: Notation;

  id : number;
  p : number=1;
  searchText;

  constructor(private noteService: NotationService,
              private router: Router){}

  ngOnInit(): void {
    this.getlistNotations();
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

  public getlistNotations(): void {
    this.noteService.getNotations().subscribe(
      (response: Notation[]) => {
        this.notationList = response;
        console.log(this.notationList);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  addEditNotation(i) {

  }
  public onDeleteNotation(noteId: number): void {
    this.noteService.deleteNotationDTO(noteId).subscribe(
      (response: void) => {
        console.log(response);
        this.getListNotificationDTOs();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

}
