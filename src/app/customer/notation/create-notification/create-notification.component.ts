import { HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute, Router, NavigationEnd } from '@angular/router';
import { ChauffeurService } from './../../../services/chauffeur.service';
import { NotationService } from './../../../services/notation.service';
import { NgForm } from '@angular/forms';
import { ChauffeurDto } from './../../../models/chauffeur';
import { NotationDto } from './../../../models/notation';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-create-notification',
  templateUrl: './create-notification.component.html',
  styleUrls: ['./create-notification.component.scss']
})
export class CreateNotificationComponent implements OnInit {

  addEditNotationDTO: NotationDto = new NotationDto();
  listChauffeurDTOs: ChauffeurDto[];

  data;
  paramId :any = 0;
  mySubscription: any;
  addNotationForm: NgForm;

  constructor(private noteService: NotationService,
              private chauffService: ChauffeurService,
              private actRoute: ActivatedRoute,
              private router: Router
  ) {
     //--for reload componant
     this.router.routeReuseStrategy.shouldReuseRoute = () => false;
     this.mySubscription = this.router.events.subscribe((event) => {
       if (event instanceof NavigationEnd) {
         // Trick the Router into believing it's last link wasn't previously loaded
         this.router.navigated = false;
       }
     });
  }

  ngOnInit(): void {
    /*
    this.paramId = this.actRoute.snapshot.paramMap.get('id');
    console.log('Param--', this.paramId);
    if(this.paramId  && this.paramId  > 0){
      this.getNotationDTOById(this.paramId);
    }

    this.getListChauffeurDTOs();
    */
  }

  getNotationDTOById(id: number) {
    console.log('getOne');
    this.noteService.getNotationDTOById(id).subscribe(
      (response: NotationDto) => {
        console.log('data--', response);
        this.addEditNotationDTO = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );

  }

  getListChauffeurDTOs() {
    this.chauffService.getChauffeurDTOs().subscribe(
      (response: ChauffeurDto[]) => {
        this.listChauffeurDTOs = response;
      }, (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }

  public onAddNotation() {
    console.log(this.addEditNotationDTO);
    this.noteService.addNotationDTO(this.addEditNotationDTO).subscribe(
      (response: NotationDto) => {
        alert("Job Ajouté avec success");
        this.router.navigate(['/notations']);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );

  }

  public onUpdateNotation() {
    this.noteService.updateNotationDTO(
      this.addEditNotationDTO.id,
      this.addEditNotationDTO).subscribe(
      (response: NotationDto) => {
        alert("Job update avec success");
        this.router.navigate(['/notations']);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }


}
