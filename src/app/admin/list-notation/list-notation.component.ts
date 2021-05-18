import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { NotationService } from './../../services/notation.service';
import { Notation } from './../../models/notation';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-list-notation',
  templateUrl: './list-notation.component.html',
  styleUrls: ['./list-notation.component.scss']
})
export class ListNotationComponent implements OnInit {

  notationList: Notation[];
  editNotation: Notation;
  deleteNotation: Notation;

  id : number;
  p : number=1;
  searchText;

  constructor(private noteService: NotationService,
              private router: Router){}

  ngOnInit(): void {
    this.getlistNotations();
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
    this.noteService.deleteNotation(noteId).subscribe(
      (response: void) => {
        console.log(response);
        this.getlistNotations();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

}
