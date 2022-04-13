import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { NotationService } from './../../services/notation.service';
import { NotationDto } from './../../models/notation';
import { ToastrService } from 'ngx-toastr';
import { TokenStorageService } from 'src/app/auth/security/token-storage.service';


@Component({
  selector: 'app-list-notation',
  templateUrl: './list-notation.component.html',
  styleUrls: ['./list-notation.component.scss']
})
export class ListNotationComponent implements OnInit {

  notationListDTO: NotationDto[];
  roles: string[];
  isLoggedIn = false;
  showAdminBoard = false;
  showManagerBoard = false;
  showGestionnaireBoard = false;
  showUserBoard = false;

  id : number;
  p : number=1;
  searchText;

  constructor(private noteService: NotationService,
              private tokenService: TokenStorageService,
              public toastr: ToastrService,
  ){}

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenService.getToken();
    if (this.isLoggedIn) {
      const user = this.tokenService.getUser();
      this.roles = user.roles;

      this.showAdminBoard = this.roles.includes('ROLE_ADMIN');
      this.showGestionnaireBoard = this.roles.includes("ROLE_GESTIONNAIRE");
      this.showManagerBoard = this.roles.includes('ROLE_MANAGER');
      this.showUserBoard = this.roles.includes('ROLE_USER');
    }
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
