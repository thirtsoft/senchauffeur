import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { NotationService } from './../../services/notation.service';
import { NotationDto } from './../../models/notation';


@Component({
  selector: 'app-list-notation',
  templateUrl: './list-notation.component.html',
  styleUrls: ['./list-notation.component.scss']
})
export class ListNotationComponent implements OnInit {

  notationListDTO: NotationDto[];

  id : number;
  p : number=1;
  searchText;

  constructor(private noteService: NotationService,
              private router: Router,
  //            public toastr: ToastrService,
  //            private dialogService: DialogService,
              private fb: FormBuilder
  ){}

  ngOnInit(): void {
    this.getListNotationDtos();
  }

  public getListNotationDtos() {
    this.noteService.getNotationDTOs().subscribe(
      (response: NotationDto[]) => {
        this.notationListDTO = response;
        console.log(this.notationListDTO);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );

  }

  addEditNotation(i) {}

  onDeleteNotation(note: NotationDto): void{
    this.noteService.deleteNotationDTO(note.id).subscribe(data => {
      this.getListNotationDtos();
    },
    (error: HttpErrorResponse) => {
      alert(error.message);
    }
    );
  }


}
